package com.ryunen344.koin.mpp.sample

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}