package ca.georgebrown.comp2074.capstone2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {PersonalAccount.class, DoctorAccount.class, SchoolAccount.class, Immunization.class, Immunization_User.class,
        MemberAccount.class, PatientAccount.class, StudentAccount.class},
        version = 3, exportSchema = false)
public abstract class AccountRoomDatabase extends RoomDatabase {

    public abstract AccountDAO accountDAO();

    private static volatile AccountRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AccountRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AccountRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AccountRoomDatabase.class, "account_database")
                            .allowMainThreadQueries()
                            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // removed immunizationID foreign key table from Immunization_User class
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // SQLite does not support dropping columns to to drop the immunizationID column
            // we need to drop the whole table and then recreate it
            database.execSQL("DROP TABLE Immunization_User");
            database.execSQL("CREATE TABLE `Immunization_User` (" +
                    "`id` INTEGER PRIMARY KEY NOT NULL," +
                    "`date` TEXT NOT NULL," +
                    "`userID` INTEGER NOT NULL," +
                    "`doctorID` INTEGER NOT NULL," +
                    "`name` TEXT NOT NULL)");
        }
    };

    // removed memberID foreign key column from Patient and Student tables
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE patient_table");
            database.execSQL("CREATE TABLE `patient_table` (" +
                    "`id` INTEGER PRIMARY KEY NOT NULL," +
                    "`name` TEXT NOT NULL," +
                    "`dob` TEXT," +
                    "`healthCard` TEXT," +
                    "`doctorID` INTEGER NOT NULL)");

            database.execSQL("DROP TABLE student_table");
            database.execSQL("CREATE TABLE `student_table` (" +
                    "`id` INTEGER PRIMARY KEY NOT NULL," +
                    "`name` TEXT NOT NULL," +
                    "`dob` TEXT," +
                    "`healthCard` TEXT," +
                    "`schoolID` INTEGER NOT NULL)");
        }
    };
}
