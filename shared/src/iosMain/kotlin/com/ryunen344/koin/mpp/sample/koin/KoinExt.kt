package com.ryunen344.koin.mpp.sample.koin

import com.ryunen344.koin.mpp.sample.di.apiModule
import com.ryunen344.koin.mpp.sample.di.expectModule
import com.ryunen344.koin.mpp.sample.di.frameworkModule
import com.ryunen344.koin.mpp.sample.di.qualifierModule
import com.ryunen344.koin.mpp.sample.di.repositoryModule
import com.ryunen344.koin.mpp.sample.di.scopedModule
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.ObjCProtocol
import kotlinx.cinterop.getOriginalKotlinClass
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.TypeQualifier

@Suppress("UNUSED")
fun initKoin() : KoinApplication {
    println("isExperimentalMM ${isExperimentalMM()}")
    return startKoin {
        napierLogger()
        modules(
            apiModule,
            expectModule,
            frameworkModule,
            qualifierModule,
            repositoryModule,
            scopedModule
        )
    }
}

fun Koin.get(objCClass : ObjCClass, qualifier : Qualifier? = null, vararg parameters : Any?) : Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, qualifier) { parametersOf(parameters) }
}

fun Koin.get(objCClass : ObjCClass, qualifier : Qualifier? = null) : Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, qualifier, null)
}

fun Koin.get(objCProtocol : ObjCProtocol, qualifier : Qualifier? = null, vararg parameters : Any?) : Any {
    val kClazz = getOriginalKotlinClass(objCProtocol)!!
    return get(kClazz, qualifier) { parametersOf(parameters) }
}

fun Koin.get(objCProtocol : ObjCProtocol, qualifier : Qualifier? = null) : Any {
    val kClazz = getOriginalKotlinClass(objCProtocol)!!
    return get(kClazz, qualifier, null)
}

fun stringQualifier(value : String) = StringQualifier(value = value)

fun typeQualifier(objCProtocol : ObjCProtocol) : TypeQualifier {
    val kClazz = getOriginalKotlinClass(objCProtocol)!!
    return TypeQualifier(type = kClazz)
}

fun typeQualifier(objCClass : ObjCClass) : TypeQualifier {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return TypeQualifier(type = kClazz)
}
