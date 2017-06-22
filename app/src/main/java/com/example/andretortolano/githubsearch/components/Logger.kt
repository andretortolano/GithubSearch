package com.example.andretortolano.githubsearch.components

import android.util.Log

class Logger {
  companion object {
    private val APPLICATION_TAG = "GITHUB_LOG_TAG"

    fun i(message: String){
      Log.i(APPLICATION_TAG, message);
    }
  }
}