package com.eblis.root.checklist.checks;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class KnownRootApps extends PackageChecker {
    static final String[] knownRootAppsPackages = {
            "com.noshufou.android.su",
            "com.noshufou.android.su.elite",
            "eu.chainfire.supersu",
            "com.koushikdutta.superuser",
            "com.thirdparty.superuser",
            "com.yellowes.su",
            "com.topjohnwu.magisk",
            "com.kingroot.kinguser",
            "com.kingo.root",
            "com.smedialink.oneclickroot",
            "com.zhiqupk.root.global",
            "com.android.settings:cyanogenmod.superuser", // todo: support package + activity stuff
            "com.alephzain.framaroot"};

    public KnownRootApps(final Context context) {
        super(context);
    }

    public List<String> checkRootFilesAndPackages() {
        List<String> installed = new ArrayList<>();
        for (String candidate : knownRootAppsPackages) {
            if (isPackageInstalled(candidate)) {
                installed.add(candidate);
            }
        }
        return installed;
    }

}
