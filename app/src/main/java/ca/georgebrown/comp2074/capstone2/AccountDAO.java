package ca.georgebrown.comp2074.capstone2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccountDAO {
    // INSERTS

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPersonal(PersonalAccount personalAccount);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDoctor(DoctorAccount doctorAccount);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSchool(SchoolAccount schoolAccount);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMember(MemberAccount memberAccount);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertImmunization(Immunization immunization);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertImmunizationUser(Immunization_User immunization_user);


    // GETS

    @Query("SELECT * FROM member_table WHERE accountID = :id ORDER BY NAME ASC")
    LiveData<List<MemberAccount>> getMembers(int id);

    @Query("SELECT * FROM member_table")
    LiveData<List<MemberAccount>> getMemberAccounts();

    @Query("SELECT * FROM personal_table")
    LiveData<List<PersonalAccount>> getPersonalAccounts();

    @Query("SELECT * FROM doctor_table")
    LiveData<List<DoctorAccount>> getDoctorAccounts();

    @Query("SELECT * FROM school_table")
    LiveData<List<SchoolAccount>> getSchoolAccounts();

    @Query("SELECT * FROM immunization_table ORDER BY NAME ASC")
    LiveData<List<Immunization>> getAllImmunizations();

    @Query("SELECT * FROM immunization_user WHERE userID = :userID")
    LiveData<List<Immunization_User>> getUserImmunizations(int userID);

    @Query("SELECT * FROM personal_table WHERE id = :id")
    LiveData<PersonalAccount> getPersonalUser(int id);

    @Query("SELECT * FROM doctor_table WHERE id = :id")
    LiveData<DoctorAccount> getDoctorUser(int id);

    @Query("SELECT * FROM school_table WHERE id = :id")
    LiveData<SchoolAccount> getSchoolUser(int id);

    // UPDATES

    @Query("UPDATE personal_table SET doctorID = :doctorID WHERE id = :id")
    void updatePersonalDoctor(int id, int doctorID);

    @Query("UPDATE personal_table SET schoolID = :schoolID WHERE id = :id")
    void updatePersonalSchool(int id, int schoolID);


    /* DELETES
    @Query("DELETE FROM personal_table")
    void deleteAllPersonal();

    @Query("DELETE FROM doctor_table")
    void deleteAllDoctor();

    @Query("DELETE FROM school_table")
    void deleteAllSchool();

    @Query("DELETE FROM immunization_table")
    void deleteAllImmunization();

    @Query("DELETE FROM immunization_user")
    void deleteAllImmunizationUsers();
    */
}
