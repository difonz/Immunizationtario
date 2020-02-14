package ca.georgebrown.comp2074.capstone2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class StudentAccount {

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
    @ForeignKey(entity = SchoolAccount.class, parentColumns = "id", childColumns = "schoolID", onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "schoolID")
    private long schoolID;

    // All Students must be Members set up by a Personal Account
    @ForeignKey(entity = MemberAccount.class, parentColumns = "id", childColumns = "memberID", onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "memberID")
    private long memberID;


    public StudentAccount(String n, String d, String hc, long sID, long memID) {
        name = n;
        dob = d;
        healthCard = hc;
        schoolID = sID;
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

    public long getSchoolID() { return this.schoolID; }

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

    public void setSchoolID(long newSId) { this.schoolID = newSId; }

    public void setMemberID(long newMemId) { this.memberID = newMemId; }

}
