# Root checklist notification application
Small Android application to help users find out why applications report that root is present on the system.

Has a few checks for stuff banking (and other types of ) applications use to check if ROOT is present on your device and reports on what it finds.

The list is not exhaustive, if you have any suggestions for other things the app could check for please let me know (or create a merge request).

## Checks
The application will report on the following things:
 - Applications that provide root
 - Common super user (SU) executables
 - Unusual permissions on mounts (read/write permissions where there shouldn't be any)
 - Root cloaking applications
 - Common applications found on devices that have root
 - Dangeous system properties
 - Custom OS giveaways and missing Google OTA certificates
 - Known folders created by applications requiring root

## What to do if issues are detected
**Do not** fix all issues reported by this tool if the applications do not report that root was found.
Also, this application may not report any issues but other applications might still detect root somehow (or assume it's present when it's not). 