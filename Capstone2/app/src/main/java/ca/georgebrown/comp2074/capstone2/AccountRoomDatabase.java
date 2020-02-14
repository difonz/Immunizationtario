package ca.georgebrown.comp2074.capstone2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {PersonalAccount.class, DoctorAccount.class, SchoolAccount.class, Immunization.class, Immunization_User.class,
        MemberAccount.class, PatientAccount.class, StudentAccount.class},
        version = 1, exportSchema = false)
public abstract class AccountRoomDatabase extends RoomDatabase {

    public abstract AccountDAO accountDAO();

    private static volatile AccountRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AccountRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AccountRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AccountRoomDatabase.class, "account_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
