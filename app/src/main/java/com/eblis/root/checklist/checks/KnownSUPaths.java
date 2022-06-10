package com.eblis.root.checklist.checks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class KnownSUPaths {
    static final String[] knownSUPaths = {
            "/system/bin/su",
            "/system/xbin/su",
            "/sbin/su",
            "/sbin/magisk",
            "/system/bin/magisk",
            "/sbin/magiskinit",
            "/system/bin/magiskinit",
            "/system/su",
            "/system/bin/.ext/.su",
            "/system/usr/we-need-root/su-backup",
            "/system/xbin/mu"};

    public List<String> checkSUPaths() {
        List<String> existingPaths = new ArrayList<>();

        for (String candidate : knownSUPaths) {
            File f = new File(candidate);
            boolean fileExists = f.exists();
            if (fileExists) {
                existingPaths.add(candidate);
            }
        }
        return existingPaths;
    }
}
