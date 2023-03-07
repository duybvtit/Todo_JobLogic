package com.joblogic.todo.data.cache

object Keys  {
    init {
        System.loadLibrary("native-lib")
    }

    external fun baseUrl(): String
}