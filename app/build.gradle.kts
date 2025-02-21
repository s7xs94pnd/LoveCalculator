plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    //safe args
    id("androidx.navigation.safeargs.kotlin")
    //Parcelize
    id("kotlin-parcelize")

    //dagger-hilt
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

    //Room
    //ksp
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.mvplovecalculator"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mvplovecalculator"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"https://love-calculator.p.rapidapi.com/\"")
        buildConfigField("String", "HOST", "\"love-calculator.p.rapidapi.com\"")
        buildConfigField("String", "KEY", "\"d914dd82cemsha5bbe2f4f6739c6p1f9aeajsnbed826fd95e3\"")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // gson converter
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //navigation
    val nav_version = "2.8.4"
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")
//lottie
    val lottieVersion = "3.4.0"
    implementation("com.airbnb.android:lottie:$lottieVersion")
    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    //Room
    val room_version = "2.6.1"
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    //Dots tab layout
    implementation("com.tbuonomo:dotsindicator:5.1.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}