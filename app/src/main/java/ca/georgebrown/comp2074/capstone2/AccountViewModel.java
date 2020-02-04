package ca.georgebrown.comp2074.capstone2;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountViewModel extends AndroidViewModel {

    private AccountRepository mRepository;

    private LiveData<List<PersonalAccount>> allPersonals;
    private LiveData<List<DoctorAccount>> allDoctors;
    private LiveData<List<SchoolAccount>> allSchools;
    private LiveData<List<MemberAccount>> allMembers;
    private LiveData<List<Immunization>> allImmunizations;

    public AccountViewModel (Application application) {
        super(application);
        mRepository = new AccountRepository(application);
        allPersonals = mRepository.getAllPersonals();
        allDoctors = mRepository.getAllDoctors();
        allSchools = mRepository.getAllSchools();
        allMembers = mRepository.getAllMembers();
        allImmunizations = mRepository.getAllImmunizations();
    }

    // GETS

    LiveData<List<PersonalAccount>> getAllPersonals() {
        return allPersonals;
    }

    LiveData<List<DoctorAccount>> getAllDoctors() {
        return allDoctors;
    }

    LiveData<List<SchoolAccount>> getAllSchools() {
        return allSchools;
    }

    LiveData<List<MemberAccount>> getAllMembers() {
        return allMembers;
    }

    LiveData<List<Immunization>> getAllImmunizations() {
        return allImmunizations;
    }

    LiveData<List<MemberAccount>> getMembers(int id) {
        return mRepository.getMembers(id);
    }

    LiveData<List<Immunization_User>> getUserImmunizations(int userID) {
        return mRepository.getUserImmunizations(userID);
    }

    LiveData<PersonalAccount> getPersonalUser(int id) {
        return mRepository.getPersonalUser(id);
    }

    LiveData<DoctorAccount> getDoctorUser(int id) {
        return mRepository.getDoctorUser(id);
    }

    LiveData<SchoolAccount> getSchoolUser(int id) {
        return mRepository.getSchoolUser(id);
    }

    // INSERTS

    void insertPersonal(PersonalAccount pa) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { mRepository.insertPersonal(pa); });
    }

    void insertDoctor(DoctorAccount da) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { mRepository.insertDoctor(da); });
    }

    void insertSchool(SchoolAccount sa) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { mRepository.insertSchool(sa); });
    }

    void insertMember(MemberAccount ma) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { mRepository.insertMember(ma); });
    }

    void insertImmunization(Immunization imm) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { mRepository.insertImmunization(imm); });
    }

    void insertImmunizationUser(Immunization_User iu) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { mRepository.insertImmunizationUser(iu); });
    }

    void updatePersonalDoctor(int id, int doctorID) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { mRepository.updatePersonalDoctor(id, doctorID); });
    }

    void updatePersonalSchool(int id, int schoolID) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { mRepository.updatePersonalSchool(id, schoolID); });
    }
}