package ca.georgebrown.comp2074.capstone2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "school_table")
public class SchoolAccount {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private long id;

    @NonNull
    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="schoolName")
    private String schoolName;

    @ColumnInfo(name="address")
    private String address;

    @ColumnInfo(name="phone")
    private String phone;

    @ColumnInfo(name="email")
    private String email;

    @ColumnInfo(name="password")
    private String password;


    public SchoolAccount(String n, String sN, String a, String p, String e, String pass) {
        name = n;
        schoolName = sN;
        address = a;
        phone = p;
        email = e;
        password = pass;
    }

    public long getId() {
        return this.id;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String newEmail) {
        this.phone = newEmail;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setSchoolName(String newName) {
        this.schoolName = newName;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    public void setPhone(String newPhone) {
        this.phone = newPhone;
    }
}
