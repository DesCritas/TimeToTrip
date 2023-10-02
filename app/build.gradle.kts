plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")



}

android {
    namespace = "com.descritas.timetotrip"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.descritas.timetotrip"
        minSdk = 26
        targetSdk = 33
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
    val lifecycle_version = "2.6.2"
    val core_version = "1.10.0"
    val appcompat_version = "1.6.1"
    val constraintlayout_version = "2.1.4"
    val recyclerview_version = "1.3.1"
    val junit_version = "4.13.2"
    val ext_junit_version = "1.1.5"
    val espresso_core_version = "3.5.1"
    val mdc_version = "1.9.0"
    val gson_version = "2.10.1"
    val nav_version = "2.6.0"
    val okhttp_version = "4.11.0"
    val retrofit_version = "2.9.0"
    val retrofitgson_version = "2.9.0"
    val okhttplogging_version = "4.10.0"


    implementation("androidx.core:core-ktx:$core_version")
    implementation("androidx.appcompat:appcompat:$appcompat_version")
    implementation("com.google.android.material:material:$mdc_version")
    implementation("androidx.constraintlayout:constraintlayout:$constraintlayout_version")
    implementation ("com.squareup.okhttp3:okhttp:$okhttp_version")
    implementation ("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation ("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation ("com.google.code.gson:gson:$gson_version")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")
    implementation ("androidx.recyclerview:recyclerview:$recyclerview_version")
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitgson_version")
    implementation ("com.squareup.okhttp3:logging-interceptor:$okhttplogging_version")
    implementation ("com.google.code.gson:gson:2.8.9")



    testImplementation("junit:junit:$junit_version")
    androidTestImplementation("androidx.test.ext:junit:$ext_junit_version")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espresso_core_version")
}

