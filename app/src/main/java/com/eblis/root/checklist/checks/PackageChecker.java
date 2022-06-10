package com.eblis.root.checklist.checks;

import android.content.Context;
import android.content.pm.PackageManager;

public class PackageChecker {
    private final Context context;

    public PackageChecker(final Context context) {
        this.context = context;
    }

    protected boolean isPackageInstalled(String packageName) {
        PackageManager pm = context.getPackageManager();
        if (packageName.contains(":")) {
            // todo: split into package + activity
        }
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            try {
                pm.getPackageInfo(packageName, 0);
                return true;
            } catch (PackageManager.NameNotFoundException ex) {
                //pass
            }

            return false;
        }
    }
}
