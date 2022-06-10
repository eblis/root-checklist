package com.eblis.root.checklist.checks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnusualPermissions {
    private static String[] pathsThatShouldNotBeWritable = {
            "/data",
            "/",
            "/system",
            "/system/bin",
            "/system/sbin",
            "/system/xbin",
            "/vendor/bin",
            "/sys",
            "/sbin",
            "/etc",
            "/proc",
            "/dev",
    };

    private static List<String> mountReader() {
        File f = new File("/proc/self/mountinfo");
        boolean fileExists = f.exists();
        List<String> lines = new ArrayList<>();
        if (fileExists) {
            if (f.canRead()) {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }
                    reader.close();

                } catch (IOException e) {
                    // nothing
                }
            }
        }
        return lines;
    }

    public List<String> checkForRWPaths() {
        List<String> results = new ArrayList<>();
        List<String> lines = mountReader();
        if (lines == null || lines.isEmpty()) {
            return Collections.emptyList();
        }
        int sdkVersion = android.os.Build.VERSION.SDK_INT;
        for (String line : lines) {
            String[] args = line.split(" ");
            if ((sdkVersion <= android.os.Build.VERSION_CODES.M && args.length < 4) || (sdkVersion > android.os.Build.VERSION_CODES.M && args.length < 6)) {
                continue;
            }
            String mountPoint;
            String mountOptions;
            if (sdkVersion > android.os.Build.VERSION_CODES.M) {
                mountPoint = args[2];
                mountOptions = args[5];
            } else {
                mountPoint = args[1];
                mountOptions = args[3];
            }
            for (String pathToCheck : pathsThatShouldNotBeWritable) {
                if (mountPoint.equalsIgnoreCase(pathToCheck)) {
                    if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.M) {
                        mountOptions = mountOptions.replace("(", "");
                        mountOptions = mountOptions.replace(")", "");
                    }
                    for (String option : mountOptions.split(",")) {
                        if (option.equalsIgnoreCase("rw")) {
                            results.add("Mount " + mountPoint + " is mounted as " + mountOptions);
                        }
                    }
                }
            }
        }
        return results;
    }
}
