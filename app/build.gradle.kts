import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

val key: String = gradleLocalProperties(rootDir).getProperty("open_weather_api_key")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.skysync"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.skysync"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

    }
    buildTypes {


        getByName("debug") {
            buildConfigField("String", "API_KEY", key)
        }
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
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    /*--------------------------------retrofit-------------------------------------*/
    val retrofitVersion = "2.9.0"
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    // Retrofit with Converter
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

    val moshiVersion = "1.15.0"
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")
    implementation("com.google.code.gson:gson:2.8.8")
    /*--------------------------------okhttp3-------------------------------------*/
    val okHttpVersion = "4.11.0"
    implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")

    /*----------------------------------hilt-dagger-----------------------*/
    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    /*---------------------------------------------room----------------------------*/
    val room_version = "2.6.0"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    // To use Kotlin Symbol Processing (KSP)
    kapt("androidx.room:room-compiler:$room_version")


    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    /*--------------------------------compose-------------------------------------*/
    //animation
    implementation("androidx.compose.animation:animation")
    //material Icons
    implementation("androidx.compose.material:material-icons-extended")

    /*--------------------------------viewModel-lifecycle-------------------------------------*/
    // ViewModel utilities for Compose
    val viewModelVersion = "2.6.1"

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$viewModelVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$viewModelVersion")

    /*--------------------------------compose-navigation-------------------------------------*/
    //compose-navigation
    val composeNavigationVersion = "2.7.1"
    implementation("androidx.navigation:navigation-compose:$composeNavigationVersion")

    // GMS - Google Mobile Services
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.31.1-alpha")



    /*--------------------------------Coil-------------------------------------*/
    val composeCoilVersion = "2.4.0"
    implementation("io.coil-kt:coil-compose:$composeCoilVersion")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}