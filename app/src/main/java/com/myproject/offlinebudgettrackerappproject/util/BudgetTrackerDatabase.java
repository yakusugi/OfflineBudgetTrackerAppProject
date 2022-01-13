package com.myproject.offlinebudgettrackerappproject.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerDao;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {BudgetTracker.class}, version = 1, exportSchema = false)
public abstract class BudgetTrackerDatabase extends RoomDatabase {
    public abstract BudgetTrackerDao budgetTrackerDao();
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
                        budgetTrackerDao.deleteAll();

                        BudgetTracker budgetTracker = new BudgetTracker("2020-01-01", "DMM", "Kaguya", "e-book", 1200);
                        budgetTrackerDao.insert(budgetTracker);
                        budgetTracker = new BudgetTracker("2020-01-02", "Google Store", "Pixel 6 Pro", "gadget", 125000);
                        budgetTrackerDao.insert(budgetTracker);
                    });
                }
            };
}
