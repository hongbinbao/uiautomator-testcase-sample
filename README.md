####Uiautomator test case example on AndroidStudio-2.0
==================================================

#####example environment
-------------------

> * ubuntu 14.04 64 bit
> * JDK 1.7
> * Android SDK
    * Android SDK api level: **23** (Android 6.0)
    * Android build tools version: **23.0.3**
> *	Android Studio 2.0 link : [Android Studio 2.0](http://developer.android.com/intl/zh-cn/tools/studio/index.html)
    * Gradle version for gradle wrapper: **https://services.gradle.org/distributions/gradle-2.10-all.zip**
    * Android gradle plugin (maven): **com.android.tools.build:gradle:2.0.0**

#####step
----
1. New Project
    * Phone and Tablet
    * Minimum SDK: Api 18 Android 4.3 (Jelly Bean)
    * no need to create activity
2. add Testing Support Library in "app/build.gradle" file. (or add all dependencies your need here)
```
    androidTestCompile 'com.android.support:support-annotations:23.3.0'
    androidTestCompile 'com.android.support.test:runner:0.4'
    // Set this dependency to use JUnit 4 rules
    androidTestCompile 'com.android.support.test:rules:0.4'
    // Set this dependency to build and run Espresso tests
    androidTestCompile 'com.android.support.test.espresso:espresso-core:2.2.1'
    // Set this dependency to build and run UI Automator tests
    androidTestCompile 'com.android.support.test.uiautomator:uiautomator-v18:2.1.2'
```
    * if you have no The Android Testing Support Library package. you can download through the Android SDK Manager.
    
3. add "AndroidJUnitRunner" in "app/build.gradle" file
```
    defaultConfig {
        applicationId "demo.example.com.demo"
        minSdkVersion 18
        targetSdkVersion 23
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
```

4. Auto install dependencies component of project
    Build -> Build APK

#####write test code
---------------
TODO
