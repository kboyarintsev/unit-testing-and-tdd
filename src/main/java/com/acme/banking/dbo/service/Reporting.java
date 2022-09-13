package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Branch;

public class Reporting {

    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        var header = "Bank\n" + "========\n";

        if (rootBranch.getChildren().isEmpty()) {
            return header + "<absent>";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<rootBranch.getChildren().size(); i++) {
            sb.append("Branch # ").append(i+1).append("\n------\n");
            sb.append("<absent>");
            if (i != rootBranch.getChildren().size() - 1) {
                sb.append("\n");
            }
        }
        return header + sb;
    }
}
