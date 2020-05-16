package com.exercise.savemyhero.data.remote

import dagger.Module
import dagger.Provides
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/v1/public/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
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

            val newUrl = urlBuilder
                .addQueryParameter("apikey", "boo")
                .build()

            val newRequest = request.buildNewUrl(newUrl)

            return chain.proceed(newRequest)
        }

        private fun Request.buildNewUrl(newUrl: HttpUrl): Request {
            return this.newBuilder().url(newUrl).build()
        }
    }
}