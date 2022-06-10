package com.eblis.root.checklist.checks;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class KnownRootCloakingApps extends PackageChecker {
    public static final String[] knownRootCloakingPackages = {
            "com.devadvance.rootcloak",
            "com.devadvance.rootcloakplus",
            "de.robv.android.xposed.installer",
            "com.saurik.substrate",
            "com.zachspong.temprootremovejb",
            "com.amphoras.hidemyroot",
            "com.amphoras.hidemyrootadfree",
            "com.formyhm.hiderootPremium",
            "com.formyhm.hideroot"
    };

    public KnownRootCloakingApps(Context context) {
        super(context);
    }

    public List<String> detectRootCloakingApps() {
        List<String> installed = new ArrayList<>();
        for (String candidate : knownRootCloakingPackages) {
            if (isPackageInstalled(candidate)) {
                installed.add(candidate);
            }
        }
        return installed;
    }
}
