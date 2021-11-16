package com.ryunen344.koin.mpp.sample.gc

import kotlin.native.internal.GC

fun updateThresholdOfG(value : Int) {
    GC.threshold = value
}

fun updateThresholdAllocations(value : Long) {
    GC.thresholdAllocations = value
}

fun unsafePerformGC() = GC.collect()

fun performGCOnCleanerWorker() = kotlin.native.internal.performGCOnCleanerWorker()
