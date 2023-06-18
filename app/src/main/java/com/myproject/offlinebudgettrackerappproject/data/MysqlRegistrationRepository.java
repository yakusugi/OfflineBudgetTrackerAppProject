package com.myproject.offlinebudgettrackerappproject.data;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.util.CsvConverter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class MysqlRegistrationRepository {
    private static final String SERVER_CONFIG_FILE = "server_config.properties";
    private static final String SERVER_URL_KEY = "server_url";
    private static final String INSERT_PHP_FILE_KEY = "insert_php_file";
    private static final String LOGIN_PHP_FILE_KEY = "login_php_file";

    private static final String CSV_FILE_NAME = "searched_spending.csv";

    private String serverUrl;
    private String insertPhpFile;
    private String loginPhpFile;

    public MysqlRegistrationRepository() {
        loadServerConfig();
    }

    private void loadServerConfig() {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(SERVER_CONFIG_FILE);
            properties.load(fileInputStream);

            serverUrl = properties.getProperty(SERVER_URL_KEY);
            insertPhpFile = properties.getProperty(INSERT_PHP_FILE_KEY);
            loginPhpFile = properties.getProperty(LOGIN_PHP_FILE_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendSearchedSpendingList(List<BudgetTrackerSpending> spendingList) {
        // Convert the spendingList to CSV format
        CsvConverter csvConverter = new CsvConverter();

        String csvData = csvConverter.convertListToCsv(spendingList);

        // Transfer the CSV file to the server
        transferCsvToServer(csvData);
    }

    private void transferCsvToServer(String csvData) {
        // Transfer the CSV data to the server using an HTTP POST request
        MysqlInsertDao mysqlInsertDao = new MysqlInsertDao();
        mysqlInsertDao.mySqlCsvInsert(csvData);
    }
}

