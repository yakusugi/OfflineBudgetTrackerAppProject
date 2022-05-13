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
import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerDao;
import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerIncomeDao;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerAlias;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBank;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncome;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {BudgetTracker.class, BudgetTrackerIncome.class, BudgetTrackerBank.class, BudgetTrackerAlias.class}, version = 6, exportSchema = false
)
public abstract class BudgetTrackerDatabase extends RoomDatabase {
    public abstract BudgetTrackerDao budgetTrackerDao();
    public abstract BudgetTrackerIncomeDao budgetTrackerIncomeDao();
    public abstract BudgetTrackerBankDao budgetTrackerBankDao();
    public abstract BudgetTrackerAliasDao budgetTrackerAliasDao();

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
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries()
                            .addMigrations(MIGRATION_1_2)
                            .addMigrations(MIGRATION_2_3)
                            .addMigrations(MIGRATION_3_4)
                            .addMigrations(MIGRATION_4_5)
                            .addMigrations(MIGRATION_5_6)
                            .addMigrations(MIGRATION_6_5)
                            .addMigrations(MIGRATION_5_6)
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

                        BudgetTracker budgetTracker = new BudgetTracker("2020-01-01", "DMM", "Kaguya", "e-book", 1200);
                        budgetTrackerDao.insert(budgetTracker);
                        budgetTracker = new BudgetTracker("2020-01-02", "Google Store", "Pixel 6 Pro", "gadget", 125000);
                        budgetTrackerDao.insert(budgetTracker);
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

}
