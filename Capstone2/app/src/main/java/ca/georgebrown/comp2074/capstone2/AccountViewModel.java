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

    LiveData<List<MemberAccount>> getMembers(long id) {
        return mRepository.getMembers(id);
    }

    LiveData<List<PatientAccount>> getPatients(long doctorID) {
        return mRepository.getPatients(doctorID);
    }

    LiveData<List<StudentAccount>> getStudents(long schoolID) {
        return mRepository.getStudents(schoolID);
    }

    LiveData<List<Immunization_User>> getUserImmunizations(long userID) {
        return mRepository.getUserImmunizations(userID);
    }

    LiveData<PersonalAccount> getPersonalById(long id) {
        return mRepository.getPersonalById(id);
    }

    LiveData<DoctorAccount> getDoctorById(long id) {
        return mRepository.getDoctorById(id);
    }

    LiveData<SchoolAccount> getSchoolById(long id) {
        return mRepository.getSchoolById(id);
    }

    LiveData<MemberAccount> getMemberById(long id) {
        return mRepository.getMemberById(id);
    }

    LiveData<PatientAccount> getPatientById(long id) {
        return mRepository.getPatientById(id);
    }

    LiveData<StudentAccount> getStudentById(long id) {
        return mRepository.getStudentById(id);
    }

    LiveData<PersonalAccount> getPersonalByEmail(String email) {
        return mRepository.getPersonalByEmail(email);
    }

    LiveData<DoctorAccount> getDoctorByEmail(String email) {
        return mRepository.getDoctorByEmail(email);
    }

    LiveData<SchoolAccount> getSchoolByEmail(String email) {
        return mRepository.getSchoolByEmail(email);
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

    void insertPatient(PatientAccount pa) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { mRepository.insertPatient(pa); });
    }

    void insertStudent(StudentAccount sa) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { mRepository.insertStudent(sa); });
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