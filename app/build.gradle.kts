
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    //id("androidx.navigation.safeargs.kotlin") version "2.7.7" apply false

    // id("com.android.application")
//    id("kotlin-android")
//    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.guessitapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.guessitapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        // multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        dataBinding = true
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    //implementation(libs.androidx.navigation.safe.args.gradle.plugin)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

   // val nav_version = "2.7.7"
//
//    // Jetpack Compose integration
//    implementation("androidx.navigation:navigation-compose:$nav_version")
//
//    // Views/Fragments integration
//    implementation("androidx.navigation:navigation-fragment:$nav_version")
//    implementation("androidx.navigation:navigation-ui:$nav_version")
//
//    // Feature module support for Fragments
//    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")
//
//    // Testing Navigation
//    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")
//
   // implementation("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")

//    // KTX
//    implementation("androidx.core:core-ktx:1.3.1")
//
//    // Navigation
//    implementation("android.arch.navigation:navigation-fragment-ktx:1.0.0-rc02")
//    implementation("android.arch.navigation:navigation-ui-ktx:1.0.0-rc02")
//
//    // Lifecycles
//    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.20")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0-alpha04")
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.0-rc01")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.0-rc01")

//    val nav_version = "2.7.7"
//    classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")

    //val nav_version = "2.7.7" // Replace with the latest version

    // Choose the appropriate dependencies based on your project setup:
//    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
//    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
//
//    // For Jetpack Compose:
//    implementation("androidx.navigation:navigation-compose:$nav_version")

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
//    implementation("com.android.support:multidex:1.0.0")
    //implementation("com.android.support:multidex:1.0.3")


}