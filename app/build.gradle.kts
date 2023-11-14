plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "fr.but.sae2024.edukid"
    compileSdk = 34

    defaultConfig {
        applicationId = "fr.but.sae2024.edukid"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.3")

    /*
    //ROOM
    implementation("android.arch.persistence.room:runtime:1.1.1")
    annotationProcessor("android.arch.persistence.room:compiler:1.1.1")
    implementation("android.arch.lifecycle:extensions:1.1.1")

    //RECYCLERVIEW
    implementation("androidx.recyclerview:recyclerview:1.2.1")

    //TESTING
    androidTestImplementation("android.arch.core:core-testing:1.1.1")

    //ANIMATION
    implementation("com.daimajia.androidanimations:library:2.4@aar")

    //DATABASE
    debugImplementation("com.amitshekhar.android:debug-db:1.0.6")

    //GRAPHIQUE
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation("com.kofigyan.stateprogressbar:stateprogressbar:1.0.0")
    implementation("com.makeramen:roundedimageview:2.3.0")

     */

    // Co Routine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("androidx.annotation:annotation:1.6.0")

    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}