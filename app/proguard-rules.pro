# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-ignorewarnings


-keepattributes *Annotation*
-dontwarn rx.**

-dontwarn okio.**

-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }

-dontwarn retrofit.**
-dontwarn retrofit.appengine.UrlFetchClient
-keep class retrofit.** { *; }


-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-keepclasseswithmembers interface * {
    @retrofit2.* <methods>;
}# Proguard rules that are applied to your test apk/code.


 -dontnote junit.framework.**
 -dontnote junit.runner.**

 -dontwarn android.test.**
 -dontwarn android.support.test.**
 -dontwarn org.junit.**
 -dontwarn org.hamcrest.**
 -dontwarn com.squareup.javawriter.JavaWriter
 # Uncomment this if you use Mockito
 -dontwarn org.mockito.**

-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-keep public class com.tahir.robartassignment.Models.**{*;}
-keep class com.tahir.robartassignment.Modules.**{*;}
-keep public class com.tahir.robartassignment.Enums.**{*;}
-keep public class com.tahir.robartassignment.Configurations.**{*;}

