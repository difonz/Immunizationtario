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
    private long userID;

    @ForeignKey(entity = DoctorAccount.class, parentColumns = "id", childColumns = "doctorID", onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "doctorID")
    private long doctorID;

    /*
    @ForeignKey(entity = Immunization.class, parentColumns = "id", childColumns = "immunizationID", onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "immunizationID")
    private int immunizationID;
    */

    @ColumnInfo(name = "name")
    private String name;


    public Immunization_User(String date, long userID, long doctorID, String name) {
        this.date = date;
        this.userID = userID;
        this.doctorID = doctorID;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    @NonNull
    public String getDate() {
        return this.date;
    }

    public long getUserID() { return this.userID; }

    public long getDoctorID() { return this.doctorID; }

    public String getName() { return this.name; }

    public void setId(int newID) { this.id = newID; }

    public void setDate(String newDate) {
        this.date = newDate;
    }

    public void setUserID(int newUID) {
        this.userID = newUID;
    }

    public void setDoctorID(int newDID) {
        this.doctorID = newDID;
    }

    public void setName(String newName) { this.name = newName; }
}
