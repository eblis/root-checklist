package com.eblis.root.checklist.checks;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonRootedApps extends PackageChecker {
    public static final String[] knownDangerousAppsPackages = {
            "com.koushikdutta.rommanager",
            "com.koushikdutta.rommanager.license",
            "com.keramidas.TitaniumBackup",
            "com.jumobile.manager.systemapp",
            "com.eblis.root.checklist",
            "com.dimonvideo.luckypatcher",
            "com.chelpus.lackypatch",
            "com.ramdroid.appquarantine",
            "com.ramdroid.appquarantinepro",
            "com.android.vending.billing.InAppBillingService.COIN",
            "com.android.vending.billing.InAppBillingService.LUCK",
            "com.chelpus.luckypatcher",
            "com.blackmartalpha",
            "org.blackmart.market",
            "com.allinone.free",
            "com.repodroid.app",
            "org.creeplays.hack",
            "com.baseappfull.fwd",
            "com.zmapp",
            "com.dv.marketmod.installer",
            "org.mobilism.android",
            "com.android.wp.net.log",
            "com.android.camera.update",
            "cc.madkite.freedom",
            "com.solohsu.android.edxp.manager",
            "org.meowcat.edxposed.manager",
            "com.xmodgame",
            "com.cih.game_cih",
            "com.charles.lpoqasert",
            "catch_.me_.if_.you_.can_"};

    public CommonRootedApps(final Context context) {
        super(context);
    }

    public List<String> detectPotentiallyDangerousApps() {
        List<String> results = new ArrayList<>();
        List<String> packages = new ArrayList<>(Arrays.asList(knownDangerousAppsPackages));
        for (String candidate : packages) {
            if (isPackageInstalled(candidate)) {
                results.add(candidate);
            }
        }
        return results;
    }
}
