package com.example.andretortolano.githubsearch.ui.screens

import com.example.andretortolano.githubsearch.ui.screens.searchuser.SearchUserContract
import com.example.andretortolano.githubsearch.ui.screens.searchuser.SearchUserPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class BasePresenterModule {
  @Binds abstract fun provideBasePresenter(presenter: SearchUserContract.Presenter): BasePresenter
}