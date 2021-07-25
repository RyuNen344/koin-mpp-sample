package com.ryunen344.koin.mpp.sample.koin

import com.ryunen344.koin.mpp.sample.di.apiModule
import com.ryunen344.koin.mpp.sample.di.expectModule
import com.ryunen344.koin.mpp.sample.di.frameworkModule
import com.ryunen344.koin.mpp.sample.di.repositoryModule
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.ObjCProtocol
import kotlinx.cinterop.getOriginalKotlinClass
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier

@Suppress("UNUSED")
fun initKoin() : KoinApplication {
    return startKoin {
        napierLogger()
        modules(
            apiModule,
            expectModule,
            frameworkModule,
            repositoryModule
        )
    }
}

fun Koin.get(objCClass : ObjCClass, qualifier : Qualifier?, parameter : Any) : Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, qualifier) { parametersOf(parameter) }
}

fun Koin.get(objCClass : ObjCClass, parameter : Any) : Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, null) { parametersOf(parameter) }
}

fun Koin.get(objCClass : ObjCClass, qualifier : Qualifier?) : Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, qualifier, null)
}

fun Koin.get(objCProtocol : ObjCProtocol, qualifier : Qualifier?, parameter : Any) : Any {
    val kClazz = getOriginalKotlinClass(objCProtocol)!!
    return get(kClazz, qualifier) { parametersOf(parameter) }
}

fun Koin.get(objCProtocol : ObjCProtocol, parameter : Any) : Any {
    val kClazz = getOriginalKotlinClass(objCProtocol)!!
    return get(kClazz, null) { parametersOf(parameter) }
}

fun Koin.get(objCProtocol : ObjCProtocol, qualifier : Qualifier?) : Any {
    val kClazz = getOriginalKotlinClass(objCProtocol)!!
    return get(kClazz, qualifier, null)
}
