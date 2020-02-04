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
    private int id;

    @NonNull
    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="address")
    private String address;

    @ColumnInfo(name="phone")
    private String phone;


    public DoctorAccount(String n, String a, String p) {
        name = n;
        address = a;
        phone = p;
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

    public void setName(String newName) {
        this.name = newName;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    public void setPhone(String newPhone) {
        this.phone = newPhone;
    }
}
