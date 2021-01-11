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
    private long id;

    @NonNull
    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="email")
    private String email;

    @ColumnInfo(name="password")
    private String password;

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


    public PersonalAccount(String name, String email, String password, String phone, String dob, String healthCard) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.dob = dob;
        this.healthCard = healthCard;
        this.doctorID = 0; // account starts unassociated with any doctor/school
        this.schoolID = 0; // the doctor/school is the entity that must initiate the foreign key association
    }

    public long getId() {
        return this.id;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
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

    public String getPassword() {
        return this.password;
    }

    public void setId(long newID) { this.id = newID; }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
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

    public void setPassword(String newPass) {
        this.password = newPass;
    }
}
