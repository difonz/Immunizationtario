package ca.georgebrown.comp2074.capstone2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "immunization_user")
public class Immunization_User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "date")
    private String date;

    // we need 3 foreign keys: the user's id who got the vaccine, the doctor who gave it, and the id of the immunization itself
    // foreign key to the user's id this immunization is associated with
    @ForeignKey(entity = PersonalAccount.class, parentColumns = "id", childColumns = "userID", onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "userID")
    private int userID;

    @ForeignKey(entity = DoctorAccount.class, parentColumns = "id", childColumns = "doctorID", onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "doctorID")
    private int doctorID;

    @ForeignKey(entity = Immunization.class, parentColumns = "id", childColumns = "immunizationID", onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "immunizationID")
    private int immunizationID;


    public Immunization_User(String d, int uid, int did, int immid) {
        date = d;
        userID = uid;
        doctorID = did;
        immunizationID = immid;
    }

    public int getId() {
        return this.id;
    }

    @NonNull
    public String getDate() {
        return this.date;
    }

    public int getUserID() { return this.userID; }

    public int getDoctorID() { return this.doctorID; }

    public int getImmunizationID() { return this.immunizationID; }

    public void setDate(String newDate) {
        this.date = newDate;
    }

    public void setUserID(int newUID) {
        this.userID = newUID;
    }

    public void setDoctorID(int newDID) {
        this.doctorID = newDID;
    }

    public void setImmunizationID(int newImmID) {
        this.immunizationID = newImmID;
    }
}
