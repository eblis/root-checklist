# Root checklist notification application
Small Android application to help users find out why applications report that root is present on the system.

Has a few checks for stuff banking (and other types of ) applications use to check if ROOT is present on your device and reports on what it finds.

The list is not exhaustive, if you have any suggestions for other things the app could check for please let me know (or create a merge request).

## Checks
The application will report on the following things:
 - Applications that provide root:
   - com.noshufou.android.su
   - com.noshufou.android.su.elite
   - eu.chainfire.supersu
   - com.koushikdutta.superuser
   - com.thirdparty.superuser
   - com.yellowes.su
   - com.topjohnwu.magisk
   - com.kingroot.kinguser
   - com.kingo.root
   - com.smedialink.oneclickroot
   - com.zhiqupk.root.global
   - com.android.settings:cyanogenmod.superuser // todo: support package + activity stuff
   - com.alephzain.framaroot
 - Common super user (SU) executables
   - /system/bin/su
   - /system/xbin/su
   - /sbin/su
   - /sbin/magisk
   - /system/bin/magisk
   - /sbin/magiskinit
   - /system/bin/magiskinit
   - /system/su
   - /system/bin/.ext/.su
   - /system/usr/we-need-root/su-backup
   - /system/xbin/mu
 - Unusual permissions on mounts (read/write permissions where there shouldn't be any)
 - Root cloaking applications
 - Common applications found on devices that have root
 - Dangeous system properties
 - Custom ROM giveaways and missing Google OTA certificates
 - Known folders created by applications requiring root

## What to do if issues are detected
**Do not** fix all issues reported by this tool if the applications do not report that root was found.
Also, this application may not report any issues but other applications might still detect root somehow (or assume it's present when it's not). 