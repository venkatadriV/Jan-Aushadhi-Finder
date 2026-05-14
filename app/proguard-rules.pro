# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in the SDK tools.

# Keep Room entities
-keep class com.janaushadhi.finder.data.model.** { *; }

# Keep Google Maps classes
-keep class com.google.android.gms.maps.** { *; }
