package ca.georgebrown.comp2074.capstone2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "immunization_table")
public class Immunization {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "info")
    private String info;


    public Immunization(String n, String i) {
        name = n;
        info = i;
    }

    public int getId() {
        return this.id;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    public String getInfo() {
        return this.info;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setInfo(String newInfo) { this.info = newInfo; }

}
