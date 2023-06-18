package com.myproject.offlinebudgettrackerappproject.util;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;

import java.util.List;

public class CsvConverter {

    public String convertListToCsv(List<BudgetTrackerSpending> spendingList) {
        StringBuilder csvData = new StringBuilder();
        csvData.append("Column1,Column2,Column3\n");  // CSV header

        for (BudgetTrackerSpending spending : spendingList) {
            csvData.append(spending.getColumn1())
                    .append(",")
                    .append(spending.getColumn2())
                    .append(",")
                    .append(spending.getColumn3())
                    .append(",")
                    .append(spending.getColumn3())
                    .append(",")
                    .append(spending.getColumn4())
                    .append(",")
                    .append(spending.getColumn5())
                    .append(",")
                    .append(spending.getColumn6())
                    .append(",")
                    .append(spending.getColumn7())
                    .append(",")
                    .append(spending.getColumn8())
                    .append("\n");
        }

        return csvData.toString();
    }

}
