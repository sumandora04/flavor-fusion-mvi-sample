plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.shop.domain"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
}

dependencies {

    implementation(libs.androidx.core)
    //Coroutine
    implementation (libs.jetbrains.kotlinx.coroutines.android)
    implementation (libs.jetbrains.kotlinx.coroutines.core)
    //Hilt:
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    //Unit Test
    testImplementation(libs.test.junit)
    testImplementation (libs.mockk)
    testImplementation (libs.jetbrains.kotlinx.coroutines.test)

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}