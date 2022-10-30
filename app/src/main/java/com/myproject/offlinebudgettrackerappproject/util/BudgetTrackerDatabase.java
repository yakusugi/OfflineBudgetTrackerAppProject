package com.myproject.offlinebudgettrackerappproject.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerAliasDao;
import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerBankDao;
import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerBankingDao;
import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerConverterDao;
import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerDao;
import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerIncomeDao;
import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerIncomeTypeDao;
import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerIncomesDao;
import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerProductTypeDao;
import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerSpendingAliasDao;
import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerSpendingDao;
import com.myproject.offlinebudgettrackerappproject.data.CurrencyConverterDao;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerAlias;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBank;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBanking;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerConverter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncome;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomeType;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomes;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerProductType;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingAlias;
import com.myproject.offlinebudgettrackerappproject.model.CurrencyConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {BudgetTracker.class, BudgetTrackerIncome.class, BudgetTrackerBank.class, BudgetTrackerAlias.class, BudgetTrackerProductType.class, BudgetTrackerIncomeType.class, BudgetTrackerSpending.class, BudgetTrackerSpendingAlias.class, BudgetTrackerBanking.class, BudgetTrackerIncomes.class, CurrencyConverter.class, BudgetTrackerConverter.class}, version = 14, exportSchema = false
)
public abstract class BudgetTrackerDatabase extends RoomDatabase {
    public abstract BudgetTrackerDao budgetTrackerDao();
    public abstract BudgetTrackerIncomeDao budgetTrackerIncomeDao();
    public abstract BudgetTrackerBankDao budgetTrackerBankDao();
    public abstract BudgetTrackerAliasDao budgetTrackerAliasDao();
    public abstract BudgetTrackerProductTypeDao budgetTrackerProductTypeDao();
    public abstract BudgetTrackerIncomeTypeDao budgetTrackerIncomeTypeDao();
    public abstract BudgetTrackerSpendingDao budgetTrackerSpendingDao();
    public abstract BudgetTrackerSpendingAliasDao budgetTrackerSpendingAliasDao();
    public abstract BudgetTrackerBankingDao budgetTrackerBankingDao();
    public abstract BudgetTrackerIncomesDao budgetTrackerIncomesDao();
    public abstract CurrencyConverterDao currencyConverterDao();
    public abstract BudgetTrackerConverterDao budgetTrackerConverterDao();

    public static final int NUMBER_OF_THREADS = 4;

    private static volatile BudgetTrackerDatabase INSTANCE;
    public static final ExecutorService dataWritableExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BudgetTrackerDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BudgetTrackerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BudgetTrackerDatabase.class, "budget_tracker_database")
                            .allowMainThreadQueries()
                            .addMigrations(MIGRATION_1_2)
                            .addMigrations(MIGRATION_2_3)
                            .addMigrations(MIGRATION_3_4)
                            .addMigrations(MIGRATION_4_5)
                            .addMigrations(MIGRATION_5_6)
                            .addMigrations(MIGRATION_6_5)
                            .addMigrations(MIGRATION_5_6)
                            .addMigrations(MIGRATION_6_7)
                            .addMigrations(MIGRATION_7_8)
                            .addMigrations(MIGRATION_8_9)
                            .addMigrations(MIGRATION_9_10)
                            .addMigrations(MIGRATION_10_11)
                            .addMigrations(MIGRATION_11_12)
                            .addMigrations(MIGRATION_12_13)
                            .addMigrations(MIGRATION_13_14)
                            .addCallback(sRoomDatabaseSpendingCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    dataWritableExecutor.execute(() -> {
                        BudgetTrackerDao budgetTrackerDao = INSTANCE.budgetTrackerDao();
                        BudgetTrackerIncomeDao budgetTrackerIncomeDao = INSTANCE.budgetTrackerIncomeDao();
                        budgetTrackerDao.deleteAll();
                    });
                }
            };

    private static final RoomDatabase.Callback sRoomDatabaseSpendingCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    dataWritableExecutor.execute(() -> {
                        BudgetTrackerSpendingDao budgetTrackerSpendingDao = INSTANCE.budgetTrackerSpendingDao();
                        budgetTrackerSpendingDao.deleteAll();
                    });
                }
            };

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE budget_tracker_income_table (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "date TEXT," +
                    "category TEXT," +
                    "amount INTEGER NOT NULL)");
        }
    };

    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE budget_tracker_bank_table (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "bank_name TEXT," +
                    "bank_balance INTEGER NOT NULL)");
        }
    };

    public static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE budget_tracker_table_alias (" +
                    "product_type_alias TEXT," +
                    "store_name_alias TEXT," +
                    "product_type_percentage TEXT)");
        }
    };

    public static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE budget_tracker_table_alias");
        }
    };



    public static final Migration MIGRATION_6_5 = new Migration(6, 5) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE budget_tracker_table_alias");
        }
    };

    public static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE budget_tracker_table_alias (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "date_alias TEXT," +
                    "store_name_alias TEXT," +
                    "product_name_alias TEXT," +
                    "product_type_alias TEXT," +
                    "price_alias INTEGER NOT NULL," +
                    "product_type_percentage REAL NOT NULL)");
        }
    };

    public static final Migration MIGRATION_6_7 = new Migration(6, 7) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE budget_tracker_product_type_table (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "product_type TEXT)");
        }
    };

    public static final Migration MIGRATION_7_8 = new Migration(7, 8) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE budget_tracker_income_type_table (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "income_type TEXT)");
        }
    };

    public static final Migration MIGRATION_8_9 = new Migration(8, 9) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE budget_tracker_spending_table (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "date TEXT," +
                    "store_name TEXT," +
                    "product_name TEXT," +
                    "product_type TEXT," +
                    "price REAL," +
                    "is_tax INTEGER," +
                    "tax_rate REAL," +
                    "notes TEXT)");
        }
    };

    public static final Migration MIGRATION_9_10 = new Migration(9, 10) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE budget_tracker_spending_alias_table (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "date_alias TEXT," +
                    "store_name_alias TEXT," +
                    "product_name_alias TEXT," +
                    "product_type_alias TEXT," +
                    "price_alias REAL NOT NULL," +
                    "is_tax_alias INTEGER," +
                    "tax_rate_alias REAL," +
                    "alias_percentage REAL NOT NULL)");
        }
    };

    public static final Migration MIGRATION_10_11 = new Migration(10, 11) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE budget_tracker_banking_table (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "bank_name TEXT," +
                    "bank_balance REAL NOT NULL)");
        }
    };

    public static final Migration MIGRATION_11_12 = new Migration(11, 12) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE budget_tracker_incomes_table (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "date TEXT," +
                    "category TEXT," +
                    "amount REAL NOT NULL," +
                    "notes TEXT)");
        }
    };

    public static final Migration MIGRATION_12_13 = new Migration(12, 13) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE bank_currency_converter_table (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "date TEXT," +
                    "bank_name TEXT," +
                    "original_currency TEXT," +
                    "original_currency_balance REAL," +
                    "converted_currency TEXT," +
                    "converted_currency_balance REAL)");
        }
    };

    public static final Migration MIGRATION_13_14 = new Migration(13, 14) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE budget_tracker_converter_table (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "date TEXT," +
                    "store_name TEXT," +
                    "product_name TEXT," +
                    "product_type TEXT," +
                    "is_tax INTEGER," +
                    "tax_rate REAL," +
                    "notes TEXT," +
                    "original_currency TEXT," +
                    "original_currency_price REAL," +
                    "target_currency TEXT," +
                    "target_currency_price REAL," +
                    "converted_date TEXT)");
        }
    };

}
