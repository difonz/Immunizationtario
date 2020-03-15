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

    private PersonalAccount personalAccountLiveData;
    private DoctorAccount doctorAccountLiveData;
    private SchoolAccount schoolAccountLiveData;
    private MemberAccount memberAccountLiveData;
    private PatientAccount patientAccountLiveData;
    private StudentAccount studentAccountLiveData;

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

    PersonalAccount getPersonalById(long id) {
        if (personalAccountLiveData == null) {
            personalAccountLiveData = mRepository.getPersonalById(id);
        }
        return personalAccountLiveData;
    }

    DoctorAccount getDoctorById(long id) {
        if (doctorAccountLiveData == null) {
            doctorAccountLiveData = mRepository.getDoctorById(id);
        }
        return doctorAccountLiveData;
    }

    SchoolAccount getSchoolById(long id) {
        if (schoolAccountLiveData == null) {
            schoolAccountLiveData = mRepository.getSchoolById(id);
        }
        return schoolAccountLiveData;
    }

    MemberAccount getMemberById(long id) {
        if (memberAccountLiveData == null) {
            memberAccountLiveData = mRepository.getMemberById(id);
        }
        return memberAccountLiveData;
    }

    MemberAccount getMemberByHC(String healthCard) {
        if (memberAccountLiveData == null) {
            memberAccountLiveData = mRepository.getMemberByHC(healthCard);
        }
        return memberAccountLiveData;
    }

    PatientAccount getPatientById(long id) {
        if (patientAccountLiveData == null) {
            patientAccountLiveData = mRepository.getPatientById(id);
        }
        return patientAccountLiveData;
    }

    StudentAccount getStudentById(long id) {
        if (studentAccountLiveData == null) {
            studentAccountLiveData = mRepository.getStudentById(id);
        }
        return studentAccountLiveData;
    }

    PersonalAccount getPersonalByEmail(String email) {
        if (personalAccountLiveData == null) {
            personalAccountLiveData = mRepository.getPersonalByEmail(email);
        }
        return personalAccountLiveData;
    }

    DoctorAccount getDoctorByEmail(String email) {
        if (doctorAccountLiveData == null) {
            doctorAccountLiveData = mRepository.getDoctorByEmail(email);
        }
        return doctorAccountLiveData;
    }

    SchoolAccount getSchoolByEmail(String email) {
        if (schoolAccountLiveData == null) {
            schoolAccountLiveData = mRepository.getSchoolByEmail(email);
        }
        return schoolAccountLiveData;
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