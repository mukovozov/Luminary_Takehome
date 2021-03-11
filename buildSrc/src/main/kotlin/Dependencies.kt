package cvdevelopers.takehome.buildSrc

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.1.2"

    object Kotlin {
        private const val version = "1.4.31"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Dagger {
        private const val version = "2.33"

        const val core = "com.google.dagger:dagger-android-support:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
        const val annotationProcessor = "com.google.dagger:dagger-android-processor:$version"
    }

    object Network {
        object Retrofit {
            private const val version = "2.9.0"
            val core = "com.squareup.retrofit2:retrofit:$version"
            val rxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava3:$version"
            val moshiAdapter = "com.squareup.retrofit2:converter-moshi:$version"
        }

        object Moshi {
            private const val version = "1.11.0"
            const val core = "com.squareup.moshi:moshi-kotlin:$version"
            const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"

        }

        const val rxJava = "io.reactivex.rxjava3:rxjava:3.0.11"
        const val rxAndroid = "io.reactivex.rxjava3:rxandroid:3.0.0"
    }

    object Debugging {
        private const val timberVersion = "4.7.1"

        const val timber = "com.jakewharton.timber:timber:$timberVersion"
    }


    object Ui {
        object Groupie {
            private const val version = "2.9.0"
            const val core = "com.xwray:groupie:$version"
            const val binding = "com.xwray:groupie-viewbinding:$version"
        }

        const val picasso = "com.squareup.picasso:picasso:2.5.2"
    }

    object AndroidX {

        const val coreKtx = "androidx.core:core-ktx:1.3.2"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.2.5"

        object Lifecycle {
            private const val version = "2.3.0"
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${version}"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${version}"
        }

        object Room {
            private const val version = "2.3.0-alpha02"

            const val runtime = "androidx.room:room-runtime:$version"
            const val rxJavaSupport = "androidx.room:room-rxjava3:$version"
            const val compiler = "androidx.room:room-compiler:$version"
        }

        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

        const val multidex = "androidx.multidex:multidex:2.0.1"
    }

    object Testing {
        const val junit = "junit:junit:4.13.2"
        const val assertJ = "org.assertj:assertj-core:3.10.0"
        const val archCore = "androidx.arch.core:core-testing:2.1.0"
        object Mockito {
            private const val version = "2.20.1"
            val core = "org.mockito:mockito-core:$version"
            val inline = "org.mockito:mockito-inline:$version"
            val kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
        }
    }
}
