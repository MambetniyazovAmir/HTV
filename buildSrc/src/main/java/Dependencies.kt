object Versions {
    val gradleAndroidPlugin = "3.2.0"
    val applicationId = "uz.kashtan.hamkortv"

    val compileSdk = 28
    val targetSdk = 28
    val minSdkVersion = 19
    val releaseVersionCode = 1
    val releaseVersionName = "1.0.0"

    val supportLibrary = "1.0.0"
    val design = "1.0.0-rc01"
    val kotlin = "1.2.70"
    val service = "4.1.0"
    val dynamic = "1.0.0"
    val lifecycle = "2.0.0"
    val rxStream = "2.0.0"
    val room = "2.1.0-alpha01"
    val retrofit2 = "2.5.0"
    val constrantLayout = "1.1.3"
    val recyclerview = "1.0.0"
    val cardstack = "2.2.0"
    val lottie = "2.8.0"
    val recyclerview_anim = "3.0.0"
    val dagger = "2.15"
    val okhttp = "3.12.0"
    val moshi = "2.4.0"
    val rxJava = "2.2.3"
    val rxKotlin = "2.3.0"
    val rxAndroid = "2.1.0"
    val rxBinding = "2.0.0"
    val diskCache = "2.0.2"
    val junit = "4.12"
    val mockito = "1.10.19"
    val truth = "0.42"
    val json = "20140107"
    val glide = "4.9.0"
    val leakcannary = "1.6.3"
    val chuck = "1.1.0"
    val multidex = "1.0.3"
    val zxing = "3.2.1"
    val zxingAndroid = "3.5.0@aar"
    val vision = "19.0.2"
    val rxPermission = "0.10.2"
    val firebase = "16.0.8"
    val crashlytics = "2.10.0"
    val gson = "2.8.5"
    val espresso = "3.2.0-alpha05"
    val rules = "1.2.0-alpha05"
    val runner = "1.1.1"
    val extJunit = "1.1.0"
    val testCore = "1.2.0-alpha05"
    val testKtx = "1.2.0-alpha05"
    val junitKtx = "1.1.1-alpha05"
    val espressoContrib = "3.0.2"
    val cordinate_layout = "1.0.0-alpha1"
    val kotlinKtx = "1.0.2"
}

object PluginDependencies {
    val android = "com.android.tools.build:gradle:${Versions.gradleAndroidPlugin}"
    val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val service = "com.google.gms:google-services:${Versions.service}"
}

object ProjectModules {
    val data = ":uz.click.evo.data"
}

object ProjectDependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.supportLibrary}"
    val design = "com.google.android.material:material:${Versions.design}"
    val dynamic = "androidx.dynamicanimation:dynamicanimation:${Versions.dynamic}"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constrantLayout}"
    val cardstack = "com.yuyakaido.android:card-stack-view:${Versions.cardstack}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleReactivestreams = "androidx.lifecycle:lifecycle-reactivestreams:${Versions.lifecycle}"
    val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    val recyclerview_anim = "jp.wasabeef:recyclerview-animators:${Versions.recyclerview_anim}"
    val room = "androidx.room:room-runtime:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    val roomRx = "androidx.room:room-rxjava2:${Versions.room}"
    val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
    val retrofitRX = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit2}"
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val okhttp_logger = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    val cordinate_layout = "androidx.coordinatorlayout:coordinatorlayout:${Versions.cordinate_layout}"
    val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.moshi}"
    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    val rxBinding = "com.jakewharton.rxbinding2:rxbinding-appcompat-v7-kotlin:${Versions.rxBinding}"
    val rxStream = "androidx.lifecycle:lifecycle-reactivestreams:${Versions.rxStream}"
    val diskCache = "com.jakewharton:disklrucache:${Versions.diskCache}"
    val junit = "junit:junit:${Versions.junit}"
    val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    val truth = "com.google.truth:truth:${Versions.truth}"
    val json = "org.json:json:${Versions.json}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideProccessor = "com.github.bumptech.glide:compiler:${Versions.glide}"
    val okhttpLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    val leakcannary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcannary}"
    val leakcannaryNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakcannary}"
    val leakcannaryFragment = "com.squareup.leakcanary:leakcanary-support-fragment:${Versions.leakcannary}"
    val chuck = "com.readystatesoftware.chuck:library:${Versions.chuck}"
    val chuckNoOp = "com.readystatesoftware.chuck:library-no-op:${Versions.chuck}"
    val multidex = "com.android.support:multidex:${Versions.multidex}"
    val rxPermission = "com.github.tbruyelle:rxpermissions:${Versions.rxPermission}"
    val zxing = "com.google.zxing:core:${Versions.zxing}"
    val zxingAndroid = "com.journeyapps:zxing-android-embedded:${Versions.zxingAndroid}"
    val vision = "com.google.firebase:firebase-ml-vision:${Versions.vision}"
    val firebaseCore = "com.google.firebase:firebase-core:${Versions.firebase}"
    val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val kotlinKtx = "androidx.core:core-ktx:${Versions.kotlinKtx}"
}

object TestDependencies {
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val rules = "androidx.test:rules:${Versions.rules}"
    val runner = "androidx.test:runner:${Versions.runner}"
    val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    val testCore = "androidx.test:core:${Versions.testCore}"
    val testKtx = "androidx.test:core-ktx:${Versions.testKtx}"
    val junitKtx = "androidx.test.ext:junit-ktx:${Versions.junitKtx}"
    val espressoContrib = "com.android.support.test.espresso:espresso-contrib:${Versions.espressoContrib}"
}