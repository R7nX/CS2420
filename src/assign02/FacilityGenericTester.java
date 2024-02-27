package assign02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests for FacilityGeneric.
 *
 * @author Eric Heisler and Bao Phuc Do and Khang Nguyen
 * @version Jan 25, 2024
 */
public class FacilityGenericTester {
    // Generic Facility
    private FacilityGeneric<Integer> uNIDFacility, emptyFacility, phase3Facility;
    private FacilityGeneric<UHealthID> uHIDFacility;
    private FacilityGeneric<String> nameFacility;
    private FacilityGeneric<Boolean> activeFacility;
    private FacilityGeneric<UHealthID> phase3uHIDFacility;
    private FacilityGeneric<String> phase3nameuHIDFacility;
    // IDs, dates, names
    private UHealthID[] uHIDs1, uHIDs2;
    private UHealthID[] uHIDs3;
    private GregorianCalendar[] dates;
    private GregorianCalendar[] dateOfPatient;
    private String[] firstNames, lastNames, physicianNames;
    private String[] patientFirstName, patientLastName;
    private boolean[] physician;
    // For phase 3
    private UHealthID p3id1, p3id2, p3id3, p3id4;
    private GregorianCalendar p3date1, p3date2, p3date3, p3date4;

    // Don't change these numbers. It will affect some tests.
    int nPatients = 20;
    int nPhysicians = 8;


    // A private helper to generate UHIDs
    private UHealthID[] generateUHIDs(String prefix, int howMany) {
        UHealthID[] ids = new UHealthID[howMany];
        for (int i = 0; i < howMany; i++)
            ids[i] = new UHealthID(prefix + "-" + String.format("%04d", i));
        return ids;
    }

    // A private helper to generate dates
    private GregorianCalendar[] generateDates(int howMany) {
        GregorianCalendar[] dates = new GregorianCalendar[howMany];
        for (int i = 0; i < howMany; i++)
            dates[i] = new GregorianCalendar(2000 + i % 22, i % 12, i % 28);
        return dates;
    }

    // A private helper to generate names
    private String[] generateNames(int howMany, int a, int b) {
        String[] names = new String[howMany];
        for (int i = 0; i < howMany; i++)
            names[i] = (char) ('A' + (i + a) % 26) + "" + (char) ('a' + (b * i) % 26);
        return names;
    }


    @BeforeEach
    void setUp() throws Exception {

        uHIDs1 = generateUHIDs("PATS", nPatients); // for patients
        uHIDs2 = generateUHIDs("DOCS", nPhysicians); // for physicians

        dates = generateDates(nPatients);

        firstNames = generateNames(nPatients, 1, 2);
        lastNames = generateNames(nPatients, 5, 3);
        physicianNames = generateNames(nPhysicians, 10, 4);


        uNIDFacility = new FacilityGeneric<Integer>();
        uHIDFacility = new FacilityGeneric<UHealthID>();
        nameFacility = new FacilityGeneric<String>();
        emptyFacility = new FacilityGeneric<Integer>();
        phase3Facility = new FacilityGeneric<Integer>();
        phase3uHIDFacility = new FacilityGeneric<UHealthID>();
        phase3nameuHIDFacility = new FacilityGeneric<String>();

        for (int i = 0; i < nPatients; i++) {
            uNIDFacility.addPatient(new CurrentPatientGeneric<Integer>(
                    firstNames[i], lastNames[i],
                    uHIDs1[i], 1234567 + i % nPhysicians, dates[i]));
            uHIDFacility.addPatient(new CurrentPatientGeneric<UHealthID>(
                    firstNames[i], lastNames[i],
                    uHIDs1[i], uHIDs2[i % nPhysicians], dates[i]));
            nameFacility.addPatient(new CurrentPatientGeneric<String>(
                    firstNames[i], lastNames[i],
                    uHIDs1[i], physicianNames[i % nPhysicians], dates[i]));
        }

        p3id1 = new UHealthID("XXXX-1111");
        p3id2 = new UHealthID("BBBB-1111");
        p3id3 = new UHealthID("FFFF-1111");
        p3id4 = new UHealthID("BBBB-2222");
        p3date1 = new GregorianCalendar(2019, 1, 5);
        p3date2 = new GregorianCalendar(2019, 1, 4);
        p3date3 = new GregorianCalendar(2019, 1, 3);
        p3date4 = new GregorianCalendar(2019, 1, 2);

        phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "B", new UHealthID("XXXX-1111"), 7, new GregorianCalendar(2019, 1, 5)));
        phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "B", new UHealthID("BBBB-1111"), 7, new GregorianCalendar(2019, 1, 4)));
        phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "C", new UHealthID("FFFF-1111"), 7, new GregorianCalendar(2019, 1, 3)));
        phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("R", "T", new UHealthID("BBBB-2222"), 7, new GregorianCalendar(2019, 1, 2)));

        phase3uHIDFacility.addPatient(new CurrentPatientGeneric<UHealthID>("A1", "A2", new UHealthID("XXXX-1234"), uHIDs2[3], new GregorianCalendar(2020, 1, 1)));
        phase3uHIDFacility.addPatient(new CurrentPatientGeneric<UHealthID>("A0", "B1", new UHealthID("SSSS-2345"), uHIDs2[4], new GregorianCalendar(2020, 4, 2)));
        phase3uHIDFacility.addPatient(new CurrentPatientGeneric<UHealthID>("A3", "B0", new UHealthID("ADSD-3333"), uHIDs2[5], new GregorianCalendar(2020, 1, 5)));
        phase3uHIDFacility.addPatient(new CurrentPatientGeneric<UHealthID>("B1", "A1", new UHealthID("BDUQ-4313"), uHIDs2[6], new GregorianCalendar(2020, 1, 5)));

        phase3nameuHIDFacility.addPatient(new CurrentPatientGeneric<String>("C1", "D5", new UHealthID("AAAA-4321"), uHIDs2[3].toString(), new GregorianCalendar(2024, 2, 10)));
        phase3nameuHIDFacility.addPatient(new CurrentPatientGeneric<String>("C3", "D1", new UHealthID("KKKK-1678"), uHIDs2[4].toString(), new GregorianCalendar(2021, 10, 12)));
        phase3nameuHIDFacility.addPatient(new CurrentPatientGeneric<String>("C2", "D1", new UHealthID("DDDD-1324"), uHIDs2[5].toString(), new GregorianCalendar(2021, 12, 25)));
        phase3nameuHIDFacility.addPatient(new CurrentPatientGeneric<String>("D1", "D5", new UHealthID("XXXX-5005"), uHIDs2[6].toString(), new GregorianCalendar(2021, 9, 11)));

        int numberOfPatients = 100;
        activeFacility = new FacilityGeneric<Boolean>();
        uHIDs3 = generateUHIDs("BCSS", numberOfPatients);
        dateOfPatient = generateDates(numberOfPatients);
        patientFirstName = generateNames(numberOfPatients, 10, 10);
        patientLastName = generateNames(numberOfPatients, 1, 9);

        Random random = new Random();

        physician = new boolean[numberOfPatients];

        for (int i = 0; i < numberOfPatients; i++) {
            int num = random.nextInt();
            if (num % 2 == 0)
                physician[i] = true;
            else
                physician[i] = false;
        }

        for (int i = 0; i < numberOfPatients; i++) {
            activeFacility.addPatient(new CurrentPatientGeneric<Boolean>(patientFirstName[i], patientLastName[i], uHIDs3[i], physician[i], dateOfPatient[i]));
        }

    }

    // empty Facility tests --------------------------------------------------------

    @Test
    public void testEmptyLookupUHID() {
        assertNull(emptyFacility.lookupByUHID(uHIDs1[0]));
    }

    @Test
    public void testEmptyLookupPhysician() {
        ArrayList<CurrentPatientGeneric<Integer>> patients = emptyFacility.lookupByPhysician(1010101);
        assertEquals(0, patients.size());
    }

    @Test
    public void testEmptySetVisit() {
        emptyFacility.setLastVisit(uHIDs1[0], dates[3]);
    }

    @Test
    public void testEmptySetPhysician() {
        emptyFacility.setPhysician(uHIDs1[0], 1010101);
    }

    @Test
    public void testEmptyGetInactivePatients() {
        ArrayList<CurrentPatientGeneric<Integer>> patients = emptyFacility.getInactivePatients(dates[4]);
        assertEquals(0, patients.size());
    }

    @Test
    public void testLookUpByUHIDNull() {
        assertEquals(null, emptyFacility.lookupByUHID(null));
    }

    @Test
    public void testLookUpByPhysicianNull() {
        assertEquals(new ArrayList<CurrentPatientGeneric>(), emptyFacility.lookupByPhysician(null));
    }

    @Test
    public void testGetInactivePatientsWithNullDates() {
        assertEquals(new ArrayList<CurrentPatientGeneric>(), emptyFacility.getInactivePatients(null));
    }

    @Test
    public void testGetPhysicianListEmptyFacility() {
        assertEquals(new ArrayList<>(), emptyFacility.getPhysicianList());
    }

    @Test
    public void testSetPhysicianWithNullUHID() {
        emptyFacility.setPhysician(null, 101010);
        assertEquals(new ArrayList<CurrentPatientGeneric>(), emptyFacility.lookupByPhysician(101010));
    }

    @Test
    public void testOrderByUHealthIDEmptyFacility() {
        assertEquals(new ArrayList<CurrentPatientGeneric>(), emptyFacility.getOrderedByUHealthID());
    }

    @Test
    public void testRecentPatientEmptyFacility() {
        assertEquals(new ArrayList<CurrentPatientGeneric>(), emptyFacility.getRecentPatients(new GregorianCalendar(2005, 5, 14)));
    }

    // uNID Facility tests --------------------------------------------------------

    @Test
    public void testUNIDLookupUHID() {
        Patient expected = new Patient(firstNames[2], lastNames[2], new UHealthID(uHIDs1[2].toString()));
        CurrentPatientGeneric<Integer> actual = uNIDFacility.lookupByUHID(new UHealthID(uHIDs1[2].toString()));
        assertEquals(expected, actual);
    }

    @Test
    public void testUNIDLookupPhysicianCount() {
        ArrayList<CurrentPatientGeneric<Integer>> actualPatients = uNIDFacility.lookupByPhysician(1234568);
        assertEquals(3, actualPatients.size());
    }

    @Test
    public void testUNIDLookupPhysicianPatient() {
        Patient expectedPatient = new Patient(firstNames[1], lastNames[1], new UHealthID(uHIDs1[1].toString()));
        ArrayList<CurrentPatientGeneric<Integer>> actualPatients = uNIDFacility.lookupByPhysician(1234568);
        assertEquals(expectedPatient, actualPatients.get(0));
    }

    @Test
    public void testUNIDSetLastVisit() {
        uNIDFacility.setLastVisit(uHIDs1[2], dates[5]);
        assertEquals(uNIDFacility.lookupByUHID(uHIDs1[2]).getLastVisit(), dates[5]);
    }

    @Test
    public void testUNIDSetPhysician() {
        uNIDFacility.setPhysician(uHIDs1[3], 2341565);
        CurrentPatientGeneric<Integer> patient = uNIDFacility.lookupByUHID(uHIDs1[3]);
        assertEquals(2341565, patient.getPhysician());
    }


    @Test
    public void testUNIDAddNewPatient() {
        assertTrue(uNIDFacility
                .addPatient(new CurrentPatientGeneric<Integer>(firstNames[2], lastNames[2], uHIDs2[2], 123124, dates[2])));
    }

    @Test
    public void testUNIDGetInactivePatients() {
        ArrayList<CurrentPatientGeneric<Integer>> actual = uNIDFacility.getInactivePatients(dates[5]);
        assertEquals(5, actual.size());
    }

    @Test
    public void testUNIDGetPhysicianList() {
        ArrayList<Integer> actual = uNIDFacility.getPhysicianList();
        assertEquals(8, actual.size());
    }


    // UHealthID facility tests ---------------------------------------------------

    @Test
    public void testUHIDLookupUHID() {
        Patient expected = new Patient(firstNames[0], lastNames[0], new UHealthID(uHIDs1[0].toString()));
        CurrentPatientGeneric<UHealthID> actual = uHIDFacility.lookupByUHID(new UHealthID(uHIDs1[0].toString()));
        assertEquals(expected, actual);
    }

    @Test
    public void testUHIDLookupPhysicianCount() {
        ArrayList<CurrentPatientGeneric<UHealthID>> actualPatients = uHIDFacility.lookupByPhysician(uHIDs2[1]);
        assertEquals(3, actualPatients.size());
    }

    @Test
    public void testUHIDAddDuplicatePatient() {
        assertFalse(uHIDFacility.addPatient(new CurrentPatientGeneric<UHealthID>(firstNames[1], lastNames[1],
                new UHealthID(uHIDs1[1].toString()), uHIDs2[1], dates[1])));
    }

    @Test
    public void testUHIDAddNewPatient() {
        assertTrue(uHIDFacility.addPatient(new CurrentPatientGeneric<UHealthID>(firstNames[1], lastNames[1],
                new UHealthID("ZZZZ-9999"), uHIDs2[1], dates[1])));
    }

    @Test
    public void testUHIDUpdatePhysician() {
        uHIDFacility.lookupByUHID(uHIDs1[2]).updatePhysician(uHIDs2[0]);
        CurrentPatientGeneric<UHealthID> patient = uHIDFacility.lookupByUHID(uHIDs1[2]);
        assertEquals(uHIDs2[0], patient.getPhysician());
    }

    // add on
    @Test
    public void testUHIDGetInactivePatients() {
        ArrayList<CurrentPatientGeneric<UHealthID>> actual = uHIDFacility.getInactivePatients(dates[2]);
        assertEquals(2, actual.size());

    }

    @Test
    public void testUHIDGetPhysicianList() {
        ArrayList<UHealthID> actual = uHIDFacility.getPhysicianList();
        assertEquals(8, actual.size());
    }

    @Test
    public void testUHIDSetPhysician() {
        uHIDFacility.setPhysician(uHIDs1[3], uHIDs2[3]);
        CurrentPatientGeneric<UHealthID> patient = uHIDFacility.lookupByUHID(uHIDs1[3]);
        assertEquals(uHIDs2[3], patient.getPhysician());
    }

    @Test
    public void testUHIDSetLastVisit() {
        uHIDFacility.setLastVisit(uHIDs1[2], dates[5]);
        assertEquals(uHIDFacility.lookupByUHID(uHIDs1[2]).getLastVisit(), dates[5]);
    }


    // name facility tests
    // -------------------------------------------------------------------------

    @Test
    public void testNameLookupPhysician() {
        Patient expectedPatient1 = new Patient(firstNames[1], lastNames[1], new UHealthID(uHIDs1[1].toString()));
        Patient expectedPatient2 = new Patient(firstNames[9], lastNames[9], new UHealthID(uHIDs1[9].toString()));

        ArrayList<CurrentPatientGeneric<String>> actualPatients = nameFacility.lookupByPhysician(physicianNames[1]);
        assertTrue(actualPatients.contains(expectedPatient1) && actualPatients.contains(expectedPatient2));
    }

    @Test
    public void testNameGetInactivePatients() {
        ArrayList<CurrentPatientGeneric<String>> actual = nameFacility
                .getInactivePatients(new GregorianCalendar(2010, 0, 0));
        assertEquals(10, actual.size());
    }

    @Test
    public void testNameGetPhysicianList() {
        ArrayList<String> actual = nameFacility.getPhysicianList();
        assertEquals(8, actual.size());
    }

    @Test
    public void testNameUpdatePhysician() {
        nameFacility.lookupByUHID(uHIDs1[2]).updatePhysician(physicianNames[1]);
        CurrentPatientGeneric<String> patient = nameFacility.lookupByUHID(uHIDs1[2]);
        assertEquals(physicianNames[1], patient.getPhysician());
    }

    @Test
    public void testNameAddNewPatient() {
        assertTrue(nameFacility
                .addPatient(new CurrentPatientGeneric<String>(firstNames[2], lastNames[2], uHIDs2[2], physicianNames[1], dates[2])));
    }

    @Test
    public void testUNameGetPhysicianList() {
        ArrayList<String> actual = nameFacility.getPhysicianList();
        assertEquals(8, actual.size());
    }

    // phase 3 tests ---------------------------------------------------------------------------

    @Test
    public void testOrderedByUHIDCount() {
        ArrayList<CurrentPatientGeneric<Integer>> actual = phase3Facility.getOrderedByUHealthID();
        assertEquals(4, actual.size());
    }

    @Test
    public void testGetRecentPatientByUHID() {
        ArrayList<CurrentPatientGeneric<Integer>> actual = phase3Facility.getRecentPatients(p3date3);
        assertEquals(2, actual.size());
    }


    @Test
    public void testGetRecentPatientByphase3UHID() {
        ArrayList<CurrentPatientGeneric<UHealthID>> actual = phase3uHIDFacility.getRecentPatients(new GregorianCalendar(2020, 1, 3));
        assertEquals(3, actual.size());
    }

    @Test
    public void testGetRecentPatientByphase3NameUHID() {
        ArrayList<CurrentPatientGeneric<String>> actual = phase3nameuHIDFacility.getRecentPatients(new GregorianCalendar(2021, 12, 25));
        assertEquals(1, actual.size());
    }

    @Test
    public void testOrderedByUHIDOrder() {
        ArrayList<CurrentPatientGeneric<Integer>> actual = phase3Facility.getOrderedByUHealthID();
        assertTrue(actual.get(3).equals(new CurrentPatientGeneric<Integer>("A", "B", p3id1, 7, p3date1)) &&
                actual.get(0).equals(new CurrentPatientGeneric<Integer>("A", "B", p3id2, 7, p3date2)) &&
                actual.get(2).equals(new CurrentPatientGeneric<Integer>("A", "C", p3id3, 7, p3date3)) &&
                actual.get(1).equals(new CurrentPatientGeneric<Integer>("R", "T", p3id4, 7, p3date4)));
    }

    @Test
    public void testuHIDFacilityOrderedByUHIDOrder() {
        ArrayList<CurrentPatientGeneric<UHealthID>> actual = phase3uHIDFacility.getOrderedByUHealthID();
        assertTrue(actual.get(1).equals(new CurrentPatientGeneric<UHealthID>("B1", "A1", new UHealthID("BDUQ-4313"), uHIDs2[6], new GregorianCalendar(2020, 1, 5)))
                && actual.get(3).equals(new CurrentPatientGeneric<UHealthID>("A1", "A2", new UHealthID("XXXX-1234"), uHIDs2[3], new GregorianCalendar(2020, 1, 1)))
                && actual.get(0).equals(new CurrentPatientGeneric<UHealthID>("A3", "B0", new UHealthID("ADSD-3333"), uHIDs2[5], new GregorianCalendar(2020, 1, 5)))
                && actual.get(2).equals(new CurrentPatientGeneric<UHealthID>("A0", "B1", new UHealthID("SSSS-2345"), uHIDs2[4], new GregorianCalendar(2020, 4, 2))));
    }


    @Test
    public void testnameFacilityOrderedByUHIDOrder() {
        ArrayList<CurrentPatientGeneric<String>> actual = phase3nameuHIDFacility.getOrderedByUHealthID();
        assertTrue(actual.get(0).equals(new CurrentPatientGeneric<String>("C1", "D1", new UHealthID("AAAA-4321"), uHIDs2[3].toString(), new GregorianCalendar(2021, 1, 1)))
                && actual.get(1).equals(new CurrentPatientGeneric<String>("C3", "D5", new UHealthID("DDDD-1324"), uHIDs2[5].toString(), new GregorianCalendar(2021, 1, 1)))
                && actual.get(2).equals(new CurrentPatientGeneric<String>("C3", "D1", new UHealthID("KKKK-1678"), uHIDs2[4].toString(), new GregorianCalendar(2021, 1, 1)))
                && actual.get(3).equals(new CurrentPatientGeneric<String>("D1", "D5", new UHealthID("XXXX-5005"), uHIDs2[6].toString(), new GregorianCalendar(2021, 1, 1))));
    }

    @Test
    public void testuHIDFacilitygetRecentPatientSortByName() {
        ArrayList<CurrentPatientGeneric<UHealthID>> actual = phase3uHIDFacility.getRecentPatients(new GregorianCalendar(2019, 1, 1));
        assertTrue(actual.get(0).equals(new CurrentPatientGeneric<UHealthID>("B1", "A1", new UHealthID("BDUQ-4313"), uHIDs2[6], new GregorianCalendar(2020, 1, 5)))
                && actual.get(1).equals(new CurrentPatientGeneric<UHealthID>("A1", "A2", new UHealthID("XXXX-1234"), uHIDs2[3], new GregorianCalendar(2020, 1, 1)))
                && actual.get(2).equals(new CurrentPatientGeneric<UHealthID>("A3", "B0", new UHealthID("ADSD-3333"), uHIDs2[5], new GregorianCalendar(2020, 1, 5)))
                && actual.get(3).equals(new CurrentPatientGeneric<UHealthID>("A0", "B1", new UHealthID("SSSS-2345"), uHIDs2[4], new GregorianCalendar(2020, 4, 2))));
    }

    @Test
    public void testnameFacilitygetRecentPatientSortByName() {
        // Sort by firstName since lastName is equal
        ArrayList<CurrentPatientGeneric<String>> actual = phase3nameuHIDFacility.getRecentPatients(new GregorianCalendar(2021, 9, 11));
        assertTrue(actual.get(0).equals(new CurrentPatientGeneric<String>("C2", "D1", new UHealthID("DDDD-1324"), uHIDs2[5].toString(), new GregorianCalendar(2021, 12, 25)))
                && actual.get(1).equals(new CurrentPatientGeneric<String>("C3", "D1", new UHealthID("KKKK-1678"), uHIDs2[4].toString(), new GregorianCalendar(2021, 10, 12))));
    }

    @Test
    public void testphase3FacilitygetRecentPatientSortByName() {
        // Sort by uHIDs since last and first are equal
        ArrayList<CurrentPatientGeneric<Integer>> actual = phase3Facility.getRecentPatients(new GregorianCalendar(2019, 1, 3));
        assertTrue(actual.get(0).equals(new CurrentPatientGeneric<Integer>("A", "B", new UHealthID("BBBB-1111"), 7, new GregorianCalendar(2019, 1, 4)))
                && actual.get(1).equals(new CurrentPatientGeneric<Integer>("A", "B", new UHealthID("XXXX-1111"), 7, new GregorianCalendar(2019, 1, 5))));
    }

    // activeFacility tests ---------------------------------------------------------------------------
    @Test
    public void testActiveLookupByUHID() {
        Patient expected = new Patient(patientFirstName[1], patientLastName[1], new UHealthID(uHIDs3[1].toString()));
        CurrentPatientGeneric<Boolean> actual = activeFacility.lookupByUHID(uHIDs3[1]);

        assertEquals(expected, actual);
    }

    @Test
    public void testActiveLookupByUHIDNull() {
        assertNull(activeFacility.lookupByUHID(null));
    }

    @Test
    public void testActivelookupByPhysician() {
        ArrayList<CurrentPatientGeneric<Boolean>> actual = activeFacility.lookupByPhysician(physician[13]);
        int count = 0;
        for (int i = 0; i < physician.length; i++) {
            if (physician[i] == physician[13])
                count++;
        }
        assertEquals(count, actual.size());
    }

    @Test
    public void testActivelookupByPhysicianNull() {
        assertEquals(new ArrayList<CurrentPatientGeneric<Boolean>>(), activeFacility.lookupByPhysician(null));
    }

    @Test
    public void testActivegetInactivePatient() {
        ArrayList<CurrentPatientGeneric<Boolean>> actual = activeFacility.getInactivePatients(new GregorianCalendar(2020, 1, 2));
        int count = 0;
        for (int i = 0; i < dateOfPatient.length; i++) {
            if (dateOfPatient[i].compareTo(new GregorianCalendar(2020, 1, 2)) < 0)
                count++;
        }
        assertEquals(count, actual.size());
    }

    @Test
    public void testActivegetInactivePatientDateEqualLastVisit() {
        ArrayList<CurrentPatientGeneric<Boolean>> actual = activeFacility.getInactivePatients(dateOfPatient[4]);
        assertFalse(actual.contains(dateOfPatient[4]));
    }

    @Test
    public void testActivegetPhysicianList() {
        ArrayList<Boolean> actual = activeFacility.getPhysicianList();
        ArrayList<Boolean> expected = new ArrayList<>();
        for (int i = 0; i < physician.length; i++) {
            if (!(expected.contains(physician[i])))
                expected.add(physician[i]);
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testActivesetPhysician() {
        activeFacility.setPhysician(uHIDs3[9], physician[31]);
        assertEquals(physician[31], activeFacility.lookupByUHID(uHIDs3[9]).getPhysician());
    }

    @Test
    public void testActivesetPhysicianNull() {
        activeFacility.setPhysician(uHIDs3[34], null);
        assertNull(activeFacility.lookupByUHID(uHIDs3[34]).getPhysician());
    }

    @Test
    public void testActivesetPhysicianTheSame() {
        activeFacility.setPhysician(uHIDs3[85], physician[85]);
        assertEquals(physician[85], activeFacility.lookupByUHID(uHIDs3[85]).getPhysician());
    }

    @Test
    public void testActivesetPhysicianNonExistPatientID() {
        UHealthID[] nonExistID = generateUHIDs("AAAA", 1);
        activeFacility.setPhysician(nonExistID[0], physician[73]);
        assertFalse(activeFacility.lookupByPhysician(physician[73]).contains(nonExistID[0]));
    }

    @Test
    public void testActivesetLastVisit() {
        activeFacility.setLastVisit(uHIDs3[19], dateOfPatient[72]);
        assertEquals(dateOfPatient[72], activeFacility.lookupByUHID(uHIDs3[19]).getLastVisit());
    }

    @Test
    public void testActivesetLastVisitNull() {
        activeFacility.setLastVisit(uHIDs3[95], null);
        assertNull(activeFacility.lookupByUHID(uHIDs3[95]).getLastVisit());
    }

    @Test
    public void testActivesetLastVisitTheSame() {
        activeFacility.setLastVisit(uHIDs3[84], dateOfPatient[84]);
        assertEquals(dateOfPatient[84], activeFacility.lookupByUHID(uHIDs3[84]).getLastVisit());
    }

    @Test
    public void testActivesetLastVisitNonExistPatientID() {
        UHealthID[] nonExistID = generateUHIDs("BBBB", 1);
        activeFacility.setLastVisit(nonExistID[0], new GregorianCalendar(2020, 1, 1));
        assertFalse(activeFacility.getInactivePatients(new GregorianCalendar(2020, 2, 2)).contains(nonExistID[0]));
    }

}
