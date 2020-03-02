package ca.georgebrown.comp2074.capstone2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "member_table")
public class MemberAccount {

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
    @ForeignKey(entity = PersonalAccount.class, parentColumns = "id", childColumns = "accountID", onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "accountID")
    private long accountID;


    public MemberAccount(String name, String dob, String healthCard, long accountID) {
        this.name = name;
        this.dob = dob;
        this.healthCard = healthCard;
        this.accountID = accountID;
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

    public long getAccountID() { return this.accountID; }

    public void setId(long newID) { this.id = newID; }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setHealthCard(String newHC) {
        this.healthCard = newHC;
    }

    public void setDob(String newDob) {
        this.dob = newDob;
    }

    public void setAccountID(long newAccId) { this.accountID = newAccId; }

}
