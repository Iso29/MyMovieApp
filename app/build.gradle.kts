plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.example.mymovieapp"
    compileSdk {
        version = release(37) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.mymovieapp"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            buildConfigField(
                "Boolean",
                "IS_DEBUG",
                "false"
            )
            buildConfigField(
                "String",
                "API_KEY",
                ""
            )
            signingConfig = signingConfigs.getByName("debug")
            optimization {
                enable = false
            }
        }
        debug {
            buildConfigField(
                "String",
                "API_KEY",
                "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ZmFlNjc4ZTBiZjBjYTBjZTI2ZjY4ZWZhNjllMzMyOCIsIm5iZiI6MTY3NTc2Nzc3MS4wNzEsInN1YiI6IjYzZTIyZmRiNzczOTQxMDBlOTEwNmU5NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Kqi0_h1m32c_M1ViB7U_9dgOP9_o7d5HDNTxg6pPDOY\""
            )
            buildConfigField(
                "Boolean",
                "IS_DEBUG",
                "true"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures{
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    val hilt = "2.60.1"
    implementation("com.google.dagger:hilt-android:${hilt}")
    ksp("com.google.dagger:hilt-compiler:${hilt}")

    implementation("androidx.fragment:fragment-ktx:1.9.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")

    // Gson converter
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:5.5.0")

    // Logging interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    implementation("com.github.bumptech.glide:glide:4.16.0")
    ksp("com.github.bumptech.glide:compiler:4.16.0")

    debugImplementation("com.github.chuckerteam.chucker:library:4.3.1")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.3.1")
}