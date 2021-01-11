package ca.georgebrown.comp2074.capstone2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "doctor_table")
public class DoctorAccount {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private long id;

    @NonNull
    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="address")
    private String address;

    @ColumnInfo(name="phone")
    private String phone;

    @ColumnInfo(name="email")
    private String email;

    @ColumnInfo(name="password")
    private String password;

    @ColumnInfo(name="pracID")
    private String pracID;


    public DoctorAccount(String name, String address, String phone, String email, String password, String pracID) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.pracID = pracID;
    }

    public long getId() {
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

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPracID() {
        return this.pracID;
    }

    public void setId(long newID) { this.id = newID; }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    public void setPhone(String newPhone) {
        this.phone = newPhone;
    }

    public void setEmail(String newEmail) {
        this.phone = newEmail;
    }

    public void setPassword(String newPass) {
        this.password = newPass;
    }

    public void setPracID(String newPracID) {
        this.password = newPracID;
    }
}
