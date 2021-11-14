package com.ryunen344.koin.mpp.sample.gc

import com.ryunen344.koin.mpp.sample.model.connpass.Group
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

private val charPool : List<Char> = ('a' .. 'z') + ('A' .. 'Z') + ('0' .. '9')

fun createGroupList() : List<Group> {
    return buildList {
        for (i in 0 .. 10000) {
            val id = Random.nextInt()
            val randomTitle = (1 .. 15)
                .map { i -> Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")

            val randomUrl = "https://" + (1 .. 24)
                .map { i -> Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")
            add(
                Group(
                    id = id,
                    title = randomTitle,
                    url = randomUrl
                )
            )
        }
    }
}

suspend fun createGroupListInBackGround() : List<Group> {
    return withContext(Dispatchers.Default) {
        buildList {
            for (i in 0 .. 100000)
                add(
                    Group(
                        id = Random.nextInt(),
                        title = (1 .. 15)
                            .map { i -> Random.nextInt(0, charPool.size) }
                            .map(charPool::get)
                            .joinToString(""),
                        url = "https://" + (1 .. 24)
                            .map { i -> Random.nextInt(0, charPool.size) }
                            .map(charPool::get)
                            .joinToString("")
                    )
                )
        }
    }
}
