package ca.georgebrown.comp2074.capstone2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountRepository {

    private AccountDAO accountDAO;
    private LiveData<List<PersonalAccount>> allPersonals;
    private LiveData<List<DoctorAccount>> allDoctors;
    private LiveData<List<SchoolAccount>> allSchools;
    private LiveData<List<MemberAccount>> allMembers;
    private LiveData<List<Immunization>> allImmunizations;

    // Note that in order to unit test the Repository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    AccountRepository(Application application) {
        AccountRoomDatabase db = AccountRoomDatabase.getDatabase(application);
        accountDAO = db.accountDAO();
        allPersonals = accountDAO.getPersonalAccounts();
        allDoctors = accountDAO.getDoctorAccounts();
        allSchools = accountDAO.getSchoolAccounts();
        allMembers = accountDAO.getMemberAccounts();
        allImmunizations = accountDAO.getAllImmunizations();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
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
        return accountDAO.getMembers(id);
    }

    LiveData<List<Immunization_User>> getUserImmunizations(int userID) {
        return accountDAO.getUserImmunizations(userID);
    }

    LiveData<PersonalAccount> getPersonalById(int id) {
        return accountDAO.getPersonalById(id);
    }

    LiveData<DoctorAccount> getDoctorById(int id) {
        return accountDAO.getDoctorById(id);
    }

    LiveData<SchoolAccount> getSchoolById(int id) {
        return accountDAO.getSchoolById(id);
    }

    LiveData<PersonalAccount> getPersonalByEmail(String email) {
        return accountDAO.getPersonalByEmail(email);
    }

    LiveData<DoctorAccount> getDoctorByEmail(String email) {
        return accountDAO.getDoctorByEmail(email);
    }

    LiveData<SchoolAccount> getSchoolByEmail(String email) {
        return accountDAO.getSchoolByEmail(email);
    }



    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insertPersonal(PersonalAccount pa) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { accountDAO.insertPersonal(pa); });
    }

    void insertDoctor(DoctorAccount da) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { accountDAO.insertDoctor(da); });
    }

    void insertSchool(SchoolAccount sa) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { accountDAO.insertSchool(sa); });
    }

    void insertMember(MemberAccount ma) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { accountDAO.insertMember(ma); });
    }

    void insertImmunization(Immunization imm) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { accountDAO.insertImmunization(imm); });
    }

    void insertImmunizationUser(Immunization_User iu) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { accountDAO.insertImmunizationUser(iu); });
    }

    void updatePersonalDoctor(int id, int doctorID) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { accountDAO.updatePersonalDoctor(id, doctorID); });
    }

    void updatePersonalSchool(int id, int schoolID) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> { accountDAO.updatePersonalSchool(id, schoolID); });
    }
}