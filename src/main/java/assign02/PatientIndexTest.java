package assign02;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the PatientIndex
 *
 * @author Bao Phuc Do and Khang Nguyen
 * @version Jan 25, 2024
 */
class PatientIndexTest {

    private Patient patient1;
    private Patient patient2;
    private Patient patient3;
    private PatientIndex patientIndex;

    @BeforeEach
    void setUp() throws Exception{
        patient1 = new Patient("Ryan", "Do", new UHealthID("AAAA-1231"));
        patient2 = new Patient("Khang", "Nguyen", new UHealthID("ADAC-9345"));
        patient3 = new Patient("Nhi", "Nguyen", new UHealthID("ABDU-6941"));

        patientIndex = new PatientIndex();
    }

    @Test
    public void addPatientTest(){
        patientIndex.addPatient(patient3);

        assertEquals("Nhi Nguyen", patientIndex.getName(new UHealthID("ABDU-6941")));
    }

    @Test
    public void removePatientTest(){
        patientIndex.removePatient(patient3);

        assertFalse("Nhi Nguyen".equals(patientIndex.getName(new UHealthID("ABDU-6941"))));
    }

    @Test
    public void getNameTest(){
        patientIndex.addPatient(patient1);
        patientIndex.addPatient(patient2);
        patientIndex.addPatient(patient3);

        assertEquals("Khang Nguyen", patientIndex.getName(new UHealthID("ADAC-9345")));
    }

    @Test
    public void getNameNullTest(){
        assertEquals(null, patientIndex.getName(new UHealthID("AAAA-1111")));
    }

    @Test
    public void addNameAlreadyExistTest(){
        Patient patient1 = new Patient("Phuc", "Do", new UHealthID("AAAA-1231"));
        patientIndex.addPatient(patient1);

        assertEquals("Phuc Do", patientIndex.getName(new UHealthID("AAAA-1231")));
    }

    @Test
    public void removeNonExistantPatientTest(){
        assertDoesNotThrow(() -> patientIndex.removePatient(new Patient("John", "Doe", new UHealthID("ADAD-1234"))));
    }


    @Test
    public void addMultiplePatientTest(){
        Patient patient4 = new Patient("Dan", "Howard", new UHealthID("ADVF-3458"));
        Patient patient5 = new Patient("Tyler", "Okonma", new UHealthID("ASGF-1353"));
        Patient patient6 = new Patient("Travis", "Scott", new UHealthID("SFJW-1249"));

        patientIndex.addPatient(patient4);
        patientIndex.addPatient(patient5);
        patientIndex.addPatient(patient6);
    }

    @Test
    public void removeMultiplePatientTest(){
        patientIndex.removePatient(patient2);
        patientIndex.removePatient(patient3);

        assertFalse("Khang Nguyen".equals(patientIndex.getName(new UHealthID("ADAC-9345"))));
        assertFalse("Nhi Nguyen".equals(patientIndex.getName(new UHealthID("ABDU-6941"))));
    }
}