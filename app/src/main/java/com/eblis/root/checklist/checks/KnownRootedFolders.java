package com.eblis.root.checklist.checks;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class KnownRootedFolders {
    private final static String[] knownRootedFolders = {
            "TitaniumBackup"
    };

    private String folderExists(String folder) {
        String[] candidates = {Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + folder, folder};
        for (String candidate : candidates) {
            File f = new File(candidate);
            if (f.exists()) {
                return candidate;
            }
        }

        return null;
    }

    public List<String> checkKnownRootedFolders() {
        List<String> results = new ArrayList<>();
        for (String candidate : knownRootedFolders) {
            if (folderExists(candidate) != null) {
                results.add(candidate);
            }
        }

        return results;
    }
}
