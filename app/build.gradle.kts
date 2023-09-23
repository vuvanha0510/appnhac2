plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.appnhac"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.appnhac"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.squareup.retrofit2:retrofit:2.3.0")
    implementation("com.squareup.retrofit2:converter-gson:2.3.0")
    implementation("com.squareup.picasso:picasso:2.5.2")
    dependencies {
        implementation("me.relex:circleindicator:1.2.2@aar")
    }
    dependencies {
        implementation("com.eftimoff:android-viewpager-transformers:1.0.1@aar")
    }
    dependencies {
        implementation("androidx.viewpager2:viewpager2:1.0.0")
        implementation("me.relex:circleindicator:2.1.6")
    }
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("de.hdodenhof:circleimageview:2.2.0")

}