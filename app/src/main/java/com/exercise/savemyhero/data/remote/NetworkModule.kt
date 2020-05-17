package com.exercise.savemyhero.data.remote

import dagger.Module
import dagger.Provides
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideRetrofit(okHttpClient: OkHttpClient): MarvelService {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/v1/public/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MarvelService::class.java)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(ApiInterceptor())
            .build()
    }

    private class ApiInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val urlBuilder = request.url().newBuilder()

            val currentTime = System.currentTimeMillis().toString()
            // TODO hide this in a better place
            val privateApiKey = "d6d16713cfd2ba7aee39a6a5ed03fb2be1b3313c"
            val publicApiKey = "4d61c99bc5708b7da816aa78e4cbb4d3"
            val hash = currentTime + privateApiKey + publicApiKey
            val newUrl = urlBuilder
                .addQueryParameter("apikey", publicApiKey)
                .addQueryParameter("hash", hash)
                .addQueryParameter("ts", currentTime)
                .build()

            val newRequest = request.buildNewUrl(newUrl)

            return chain.proceed(newRequest)
        }

        private fun Request.buildNewUrl(newUrl: HttpUrl): Request {
            return this.newBuilder().url(newUrl).build()
        }

        private fun String.md5(): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
        }
    }
}