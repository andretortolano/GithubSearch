package com.example.andretortolano.githubsearch.ui.screens.searchuser

import dagger.Component

@Component(modules = arrayOf(SearchUserPresenterModule::class))
interface SearchUserPresenterComponent {
  fun inject(view: SearchUserView)
}