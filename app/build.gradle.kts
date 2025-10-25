plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.laecards.app"
    compileSdk = 36

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    defaultConfig {
        applicationId = "com.laecards.app"
        minSdk = 24
        targetSdk = 35
        versionCode = 3
        versionName = "3.0"

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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    packaging {
        resources {
            excludes += listOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md"
            )
        }
    }
}

dependencies {
    implementation(libs.coroutines)
    implementation(libs.coretesting)
    implementation(libs.mockk)
    implementation(libs.glide)
    implementation(libs.koin)
    implementation(libs.koincompat)
    implementation(libs.koinworkmanager)
    implementation(libs.koinnavigation)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.room)
    implementation(libs.roomruntime)
    ksp(libs.roomcompiler)
}