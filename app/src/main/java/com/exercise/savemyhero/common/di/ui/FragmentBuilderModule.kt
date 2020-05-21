package com.exercise.savemyhero.common.di.ui

import com.exercise.savemyhero.common.di.module.ViewModelKey
import com.exercise.savemyhero.common.di.module.ViewModelProviderFactory
import com.exercise.savemyhero.ui.favorite.FavoriteFragment
import com.exercise.savemyhero.ui.favorite.FavoriteViewModel
import com.exercise.savemyhero.ui.hero.HeroFragment
import com.exercise.savemyhero.ui.hero.HeroViewModel
import com.exercise.savemyhero.ui.home.HomeFragment
import com.exercise.savemyhero.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBuilderModule {

    //region HomeFragment
    @ContributesAndroidInjector(modules = [ViewModelProviderFactory::class])
    abstract fun bindsHomeFragment(): HomeFragment

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel)

    //endregion

    //region Hero Fragment
    @ContributesAndroidInjector(modules = [ViewModelProviderFactory::class])
    abstract fun bindsHeroFragment(): HeroFragment

    @Binds
    @IntoMap
    @ViewModelKey(HeroViewModel::class)
    abstract fun bindHeroViewModel(viewModel: HeroViewModel)
    // endregion

    //region Favorite Fragment
    @ContributesAndroidInjector(modules = [ViewModelProviderFactory::class])
    abstract fun bindsFavoriteFragment(): FavoriteFragment

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: FavoriteViewModel)
    //endregion
}