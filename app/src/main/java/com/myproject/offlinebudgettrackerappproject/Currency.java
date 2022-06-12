package com.myproject.offlinebudgettrackerappproject;

import java.util.ArrayList;

public class Currency {

    private static ArrayList<Currency> currencyArrayList = new ArrayList<>();

    static {
        initCurrencies();
    }

    private String id;
    private String currency;

    public Currency(String id, String currency) {
        this.id = id;
        this.currency = currency;
    }

    public Currency() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public static void initCurrencies() {
        Currency usd = new Currency("0", "US Dollars");
        currencyArrayList.add(usd);

        Currency jpy = new Currency("1", "Japanese Yen");
        currencyArrayList.add(jpy);

        Currency euro = new Currency("2", "Euro");
        currencyArrayList.add(euro);


    }

    public int getCurrencyImage() {
        switch (getId()) {
            case "0":
                return R.drawable.ic_money;
            case "1":
                return R.drawable.ic_yen;
            case "2":
                return R.drawable.ic_euro;
        }
        return R.drawable.ic_money;
    }

    public static ArrayList<Currency> getCurrencyArrayList() {
        return currencyArrayList;
    }
}
