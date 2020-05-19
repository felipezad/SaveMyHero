package com.exercise.savemyhero.ui.favorite

import com.exercise.savemyhero.domain.hero.usecase.DeleteHeroInDataBaseUseCase
import com.exercise.savemyhero.domain.hero.usecase.GetFavoritesHeroesListUseCase
import com.exercise.savemyhero.domain.hero.usecase.SaveHeroInDataBaseUseCase
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class FavoriteHeroModule {

    @ContributesAndroidInjector
    abstract fun bind(): FavoriteFragment
}

@Module
object FavoriteHeroViewFactory {

    @Singleton
    @Provides
    @JvmStatic
    fun provideHeroViewModelFactory(
        getFavoritesHeroesListUseCase: GetFavoritesHeroesListUseCase,
        saveHeroInDataBaseUseCase: SaveHeroInDataBaseUseCase,
        deleteHeroInDataBaseUseCase: DeleteHeroInDataBaseUseCase
    ): FavoriteViewModel.Factory {
        return FavoriteViewModel.Factory(
            getFavoritesHeroesListUseCase,
            saveHeroInDataBaseUseCase,
            deleteHeroInDataBaseUseCase
        )
    }
}
