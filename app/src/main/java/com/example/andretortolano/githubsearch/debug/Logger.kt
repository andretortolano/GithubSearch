package com.example.andretortolano.githubsearch.debug

import android.util.Log

class Logger {
    companion object {
        private val APPLICATION_TAG = "GITHUB_LOG_TAG"

        fun i(message: String?) {
            Log.i(APPLICATION_TAG, message);
        }

        fun e(message: String?) {
            Log.e(APPLICATION_TAG, message);
        }
    }
}
