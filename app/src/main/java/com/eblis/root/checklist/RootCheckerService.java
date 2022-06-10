package com.eblis.root.checklist;

import android.content.Context;
import android.widget.EditText;

import com.eblis.root.checklist.checks.CommonRootedApps;
import com.eblis.root.checklist.checks.CustomOS;
import com.eblis.root.checklist.checks.DangerousProperties;
import com.eblis.root.checklist.checks.KnownRootApps;
import com.eblis.root.checklist.checks.KnownRootCloakingApps;
import com.eblis.root.checklist.checks.KnownRootedFolders;
import com.eblis.root.checklist.checks.KnownSUPaths;
import com.eblis.root.checklist.checks.UnusualPermissions;

import java.util.ArrayList;
import java.util.List;

public class RootCheckerService {
    private final KnownRootApps knownRootApps;
    private final KnownSUPaths knownSUPaths;
    private final CustomOS customOS;
    private final DangerousProperties dangerousProperties;
    private final CommonRootedApps commonRootedApps;
    private final UnusualPermissions unusualPermissions;
    private final KnownRootCloakingApps knownRootCloakingApps;
    private final KnownRootedFolders knownRootedFolders;

    public RootCheckerService(final Context context) {
        this.knownRootApps = new KnownRootApps(context);
        this.knownSUPaths = new KnownSUPaths();
        this.customOS = new CustomOS();
        this.dangerousProperties = new DangerousProperties();
        this.commonRootedApps = new CommonRootedApps(context);
        this.unusualPermissions = new UnusualPermissions();
        this.knownRootCloakingApps = new KnownRootCloakingApps(context);
        this.knownRootedFolders = new KnownRootedFolders();
    }

    private List<String> buildResults(String message, List<String> items) {
        List<String> results = new ArrayList<>();
        for (String candidate : items) {
            if (candidate != null && !candidate.isEmpty()) {
                results.add(String.format(message, candidate));
            }
        }

        return results;

    }

    private List<String> getKnownRootApps() {
        return buildResults("Known ROOT application %s is installed on the system", knownRootApps.checkRootFilesAndPackages());
    }

    private List<String> getKnownSUPaths() {
        return buildResults("Known SU file %s is present on the system", knownSUPaths.checkSUPaths());
    }

    private List<String> getCustomOS() {
        return buildResults("Custom ROM check '%s' triggered", customOS.checkCustomOS());
    }

    private List<String> getDangerousProperties() {
        return buildResults("Dangerous property %s found", dangerousProperties.checkForDangerousProps());
    }

    private List<String> getCommonRootedApps() {
        return buildResults("Common application found on rooted devices %s found", commonRootedApps.detectPotentiallyDangerousApps());
    }

    private List<String> getUnusualPermissions() {
        return buildResults("Unusual permission found %s", unusualPermissions.checkForRWPaths());
    }

    private List<String> getRootCloakingApps() {
        return buildResults("Root cloaking application %s found", knownRootCloakingApps.detectRootCloakingApps());
    }

    private List<String> getKnownRootedFolders() {
        return buildResults("Known rooted folder %s found", knownRootedFolders.checkKnownRootedFolders());
    }

    private void addNotificationItems(String title, StringBuilder result, List<String> items) {
        result.append(title).append("\n");
        for (String candidate : items) {
            if (candidate != null && !candidate.isEmpty()) {
                result.append(" - ").append(candidate).append("\n");
            }
        }
        result.append("\n");
    }

    public void checkForItems(EditText notificationsView) {
        StringBuilder sb = new StringBuilder();
        addNotificationItems("Common rooted applications:", sb, getCommonRootedApps());
        addNotificationItems("Installed root applications:", sb, getKnownRootApps());
        addNotificationItems("Known SU file locations:", sb, getKnownSUPaths());
        addNotificationItems("Custom OS: ", sb, getCustomOS());
        addNotificationItems("Dangerous properties:", sb, getDangerousProperties());
        addNotificationItems("Unusual permissions:", sb, getUnusualPermissions());
        addNotificationItems("Root cloaking apps:", sb, getRootCloakingApps());
        addNotificationItems("Known rooted folders:", sb, getKnownRootedFolders());

        notificationsView.setText(sb.toString());
    }
}
