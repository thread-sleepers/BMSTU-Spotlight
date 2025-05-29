import java.io.FileInputStream
import java.util.Properties


val localProperties = Properties().apply {
    val file = File(rootProject.projectDir, "local.properties")
    if (file.exists()) {
        load(FileInputStream(file))
    }
}
val apiKey4: String = localProperties.getProperty("API_KEY_4", "").takeIf {it.isNotEmpty()} ?:
throw GradleException("Missing API_KEY_4 in local.properties")
val apiKey3: String = localProperties.getProperty("API_KEY_3", "").takeIf {it.isNotEmpty()} ?:
throw GradleException("Missing API_KEY_3 in local.properties")
val apiKey2: String = localProperties.getProperty("API_KEY_2", "").takeIf {it.isNotEmpty()} ?:
throw GradleException("Missing API_KEY_2 in local.properties")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.devtools.ksp)
}

android {
    namespace = "com.example.bmstu_spotlight"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.bmstu_spotlight"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField ("String", "API_KEY_4", apiKey4)
        buildConfigField ("String", "API_KEY_3", apiKey3)
        buildConfigField ("String", "API_KEY_2", apiKey2)
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
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.common.jvm)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.rxjava2)
    testImplementation(libs.androidx.room.testing)
    implementation(libs.navigation.compose)
    implementation(libs.androidx.material)
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation (libs.koin.androidx.compose)
    implementation (libs.koin.android)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.preferences)

}