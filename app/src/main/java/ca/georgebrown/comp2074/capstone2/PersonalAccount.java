package ca.georgebrown.comp2074.capstone2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "personal_table")
public class PersonalAccount {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private int id;

    @NonNull
    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="address")
    private String address;

    @ColumnInfo(name="phone")
    private String phone;

    @ColumnInfo(name="dob")
    private String dob;

    @ColumnInfo(name="healthCard")
    private String healthCard;

    // these 2 foreign key values will set to 0 if the doctor/school they are associated with is deleted
    @ForeignKey(entity = DoctorAccount.class, parentColumns = "id", childColumns = "doctorID", onDelete = ForeignKey.SET_DEFAULT)
    @ColumnInfo(name="doctorID", defaultValue = "0")
    private int doctorID;

    @ForeignKey(entity = SchoolAccount.class, parentColumns = "id", childColumns = "schoolID", onDelete = ForeignKey.SET_DEFAULT)
    @ColumnInfo(name="schoolID", defaultValue = "0")
    private int schoolID;


    public PersonalAccount(String n, String a, String p, String d, String hc) {
        name = n;
        address = a;
        phone = p;
        dob = d;
        healthCard = hc;
        doctorID = 0; // account starts unassociated with any doctor/school
        schoolID = 0; // the doctor/school is the entity that must initiate the foreign key association
    }

    public int getId() {
        return this.id;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getDob() {
        return this.dob;
    }

    public String getHealthCard() {
        return this.healthCard;
    }

    public int getDoctorID() {
        return this.doctorID;
    }

    public int getSchoolID() {
        return this.schoolID;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    public void setPhone(String newPhone) {
        this.phone = newPhone;
    }

    public void setDob(String newDob) {
        this.dob = newDob;
    }

    public void setHealthCard(String newHC) {
        this.healthCard = newHC;
    }

    public void setDoctorID(int dID) {
        this.doctorID = dID;
    }

    public void setSchoolID(int sID) {
        this.schoolID = sID;
    }
}
