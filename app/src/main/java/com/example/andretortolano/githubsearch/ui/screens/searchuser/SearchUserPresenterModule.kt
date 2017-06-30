package com.example.andretortolano.githubsearch.ui.screens.searchuser

import com.example.andretortolano.githubsearch.api.github.GithubService
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class SearchUserPresenterModule constructor(val userView: SearchUserContract.View, val githubService: GithubService) {

  @Provides
  fun provideView() = userView

  @Provides
  fun provideService() = githubService
}