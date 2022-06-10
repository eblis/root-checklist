package com.eblis.root.checklist.checks;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DangerousProperties {
    private String[] propsReader() {
        try {
            InputStream inputstream = Runtime.getRuntime().exec("getprop").getInputStream();
            if (inputstream == null) return null;
            String propVal = new Scanner(inputstream).useDelimiter("\\A").next();
            return propVal.split("\n");
        } catch (IOException | NoSuchElementException e) {
            return null;
        }
    }

    public List<String> checkForDangerousProps() {
        final Map dangerousProps = new HashMap<>();
        dangerousProps.put("ro.debuggable", "1");
        dangerousProps.put("ro.secure", "0");
        boolean result = false;
        String[] lines = propsReader();
        if (lines == null) {
            return Collections.emptyList();
        }
        List<String> results = new ArrayList<>();

        for (String line : lines) {
            for (Object candidate : dangerousProps.keySet()) {
                if (candidate instanceof String) {
                    final String key = (String) candidate;
                    if (line.contains(key)) {
                        String badValue = (String) dangerousProps.get(candidate);
                        badValue = "[" + badValue + "]";
                        if (line.contains(badValue)) {
                            results.add(line);
                        }
                    }
                }
            }
        }
        return results;
    }

}
