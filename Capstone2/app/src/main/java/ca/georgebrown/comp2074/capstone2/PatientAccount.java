package ca.georgebrown.comp2074.capstone2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "patient_table")
public class PatientAccount {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private long id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "dob")
    private String dob;

    @ColumnInfo(name = "healthCard")
    private String healthCard;

    // foreign key to the account id this member is associated with
    // CASCADE == if member's parent account is deleted, so is the member
    @ForeignKey(entity = DoctorAccount.class, parentColumns = "id", childColumns = "doctorID", onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "doctorID")
    private long doctorID;

    // All Patients must be Members set up by a Personal Account
    @ForeignKey(entity = MemberAccount.class, parentColumns = "id", childColumns = "memberID", onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "memberID")
    private long memberID;

    public PatientAccount(String n, String d, String hc, long docID, long memID) {
        name = n;
        dob = d;
        healthCard = hc;
        doctorID = docID;
        memberID = memID;
    }

    public long getId() {
        return this.id;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    public String getHealthCard() {
        return this.healthCard;
    }

    public String getDob() {
        return this.dob;
    }

    public long getDoctorID() { return this.doctorID; }

    public long getMemberID() { return this.memberID; }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setHealthCard(String newHC) {
        this.healthCard = newHC;
    }

    public void setDob(String newDob) {
        this.dob = newDob;
    }

    public void setDoctorID(long newDocId) { this.doctorID = newDocId; }

    public void setMemberID(long newMemID) { this.memberID = newMemID; }

}
