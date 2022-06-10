package com.eblis.root.checklist.checks;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class CustomOS {
    private String checkBuildTags() {
        String buildTags = android.os.Build.TAGS;
        if (buildTags != null && buildTags.contains("test-keys")) {
            return "Test keys found on system: " + buildTags;
        }
        return null;
    }

    private String checkOtaCertificates() {
        String OTAPath = "/etc/security/otacerts.zip";
        File f = new File(OTAPath);
        boolean fileExists = f.exists();
        if (fileExists) {
            return null;
        } else {
            return "OTA certificates DO NOT exist at " + OTAPath;
        }
    }

    public List<String> checkCustomOS() {
        return Arrays.asList(checkBuildTags(), checkOtaCertificates());
    }
}
