package assign02;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * This class contains tests for Facility.
 *
 * @author Eric Heisler and Bao Phuc Do and Khang Nguyen
 * @version Jan 25, 2024
 */
public class FacilityTester {

	private Facility emptyFacility, verySmallFacility, smallFacility;
	private UHealthID uHID1, uHID2, uHID3;
	private GregorianCalendar date1, date2, date3;

	private Facility largeFacility;
	private ArrayList<String> randomUHID;
	private ArrayList<Integer> randomPhysician;
	private ArrayList<GregorianCalendar> randomDates;
	private ArrayList<CurrentPatient> randomPatients;

	@BeforeEach
	void setUp() throws Exception {

		uHID1 = new UHealthID("AAAA-1111");
		uHID2 = new UHealthID("BCBC-2323");
		uHID3 = new UHealthID("HRHR-7654");

		date1 = new GregorianCalendar(2023, 0, 1);
		date2 = new GregorianCalendar(2023, 3, 17);
		date3 = new GregorianCalendar(2022, 8, 21);

		emptyFacility = new Facility();

		verySmallFacility = new Facility();
		verySmallFacility.addPatient(new CurrentPatient("Jane", "Doe", uHID1, 1010101, date1));
		verySmallFacility.addPatient(new CurrentPatient("Drew", "Hall", uHID2, 3232323, date2));
		verySmallFacility.addPatient(new CurrentPatient("Riley", "Nguyen", uHID3, 9879876, date3));

		smallFacility = new Facility();
		smallFacility.addAll("src/assign02/small_patient_list.txt");

		largeFacility = new Facility();

		String[] firstNameList = { "Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Henry", "Sophia",
				"Ethan", "Liam", "Olivia", "Aiden", "Ava", "Jackson", "Lucas", "Emily", "Elijah", "Mia", "Adolf" };
		String[] lastNameList = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson",
				"Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia",
				"Martinez", "Robinson" };

		int numberOfuHID = 1000;
		randomUHID = new ArrayList<>();

		Random random = new Random();

		for (int j = 0; j < numberOfuHID; j++) {
			char[] randomChars = new char[4];
			for (int i = 0; i < 4; i++) {
				randomChars[i] = (char) (random.nextInt(26) + 'A');
			}
			String randomString = new String(randomChars);
			randomString += "-";
			for (int k = 0; k < 4; k++) {
				randomString += String.valueOf(random.nextInt(9));
			}
			if (!randomUHID.contains(randomString))
				randomUHID.add(randomString);

		}

		randomPhysician = new ArrayList<>();

		for (int i = 0; i < 31; i++) {
			randomPhysician.add(random.nextInt((9999999 - 1000000 + 1) + 100000));
		}


		Random randomDate = new Random(30);
		Random randomMonth = new Random(12);
		
		randomDates = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			randomDates.add(new GregorianCalendar(2000 + i%22, randomMonth.nextInt(), randomDate.nextInt()));
		}

		randomPatients = new ArrayList<>();

		for (int i = 0; i < firstNameList.length; i++) {
			randomPatients.add(new CurrentPatient(firstNameList[i], lastNameList[i], new UHealthID(randomUHID.get(i)),
					randomPhysician.get(i), randomDates.get(i)));
		}

		for (CurrentPatient patient : randomPatients) {
			largeFacility.addPatient(patient);
		}


	}

	// Empty Facility tests --------------------------------------------------------

	@Test
	public void testEmptyLookupUHID() {
		assertNull(emptyFacility.lookupByUHID(uHID1));
	}

	@Test
	public void testEmptyLookupPhysician() {
		ArrayList<CurrentPatient> patients = emptyFacility.lookupByPhysician(1010101);
		assertEquals(0, patients.size());
	}

	@Test
	public void testEmptySetVisit() {
		emptyFacility.setLastVisit(uHID2, date3);
	}

	@Test
	public void testEmptySetPhysician() {
		emptyFacility.setPhysician(uHID2, 1010101);
	}

	@Test
	public void testEmptyGetInactivePatients() {
		ArrayList<CurrentPatient> patients = emptyFacility.getInactivePatients(date3);
		assertEquals(0, patients.size());
	}

	// Very small facility tests ---------------------------------------------------

	@Test
	public void testVerySmallLookupUHID() {
		Patient expected = new Patient("Drew", "Hall", new UHealthID("BCBC-2323"));
		CurrentPatient actual = verySmallFacility.lookupByUHID(new UHealthID("BCBC-2323"));
		assertEquals(expected, actual);
	}

	@Test
	public void testVerySmallLookupPhysicianCount() {
		ArrayList<CurrentPatient> actualPatients = verySmallFacility.lookupByPhysician(9879876);
		assertEquals(1, actualPatients.size());
	}

	@Test
	public void testVerySmallLookupPhysicianPatient() {
		Patient expectedPatient = new Patient("Riley", "Nguyen", new UHealthID("HRHR-7654"));
		ArrayList<CurrentPatient> actualPatients = verySmallFacility.lookupByPhysician(9879876);
		assertEquals(expectedPatient, actualPatients.get(0));
	}

	@Test
	public void testVerySmallAddNewPatient() {
		assertTrue(verySmallFacility
				.addPatient(new CurrentPatient("Jane", "Doe", new UHealthID("BBBB-2222"), 1010101, date1)));
	}

	@Test
	public void testVerySmallUpdatePhysician() {
		verySmallFacility.lookupByUHID(uHID1).updatePhysician(9090909);
		CurrentPatient patient = verySmallFacility.lookupByUHID(uHID1);
		assertEquals(9090909, patient.getPhysician());
	}

	@Test
	public void testVerySmallGetInactivePatients() {
		ArrayList<CurrentPatient> actual = verySmallFacility.getInactivePatients(new GregorianCalendar(2023, 0, 0));
		assertEquals(1, actual.size());
	}

	@Test
	public void testVerySmallGetPhysicianList() {
		ArrayList<Integer> actual = verySmallFacility.getPhysicianList();
		assertEquals(3, actual.size());
	}

	@Test
	public void testVerySmallAddNewPatientFalse() {
		assertFalse(verySmallFacility
				.addPatient(new CurrentPatient("Jane", "Doe", new UHealthID("AAAA-1111"), 1010101, date1)));
	}

	@Test
	public void testVerySmallSetPhysician() {
		verySmallFacility.setPhysician(uHID1, 1034569);
		CurrentPatient patient = verySmallFacility.lookupByUHID(uHID1);
		assertEquals(1034569, patient.getPhysician());
	}

	@Test
	public void testVerySmallSetLastVisit() {
		verySmallFacility.setLastVisit(uHID2, new GregorianCalendar(2024, 1, 23));
		GregorianCalendar expect = new GregorianCalendar(2024, 1, 23);
		GregorianCalendar actual = verySmallFacility.lookupByUHID(uHID2).getLastVisit();

		assertEquals(expect, actual);
	}

	@Test
	public void testAddAllFileCatch() {

		assertDoesNotThrow(() -> verySmallFacility.addAll("src/assign02/nonexistent_patient_list.txt"));

	}

	
	// Small facility tests -------------------------------------------------------------------------

	@Test
	public void testSmallLookupPhysician() {
		ArrayList<CurrentPatient> actualPatients = smallFacility.lookupByPhysician(8888888);
		assertEquals(2, actualPatients.size());
	}

	@Test
	public void testSmallLookupPhysicianPatient() {
		Patient expectedPatient1 = new Patient("Kennedy", "Miller", new UHealthID("QRST-3456"));
		Patient expectedPatient2 = new Patient("Taylor", "Miller", new UHealthID("UVWX-7890"));

		ArrayList<CurrentPatient> actualPatients = smallFacility.lookupByPhysician(8888888);
		assertTrue(actualPatients.contains(expectedPatient1) && actualPatients.contains(expectedPatient2));
	}

	@Test
	public void testSmallGetInactivePatients() {
		ArrayList<CurrentPatient> actual = smallFacility.getInactivePatients(new GregorianCalendar(2020, 0, 0));
		assertEquals(9, actual.size());
	}

	@Test
	public void testSmallGetPhysicianList() {
		ArrayList<Integer> actual = smallFacility.getPhysicianList();
		assertEquals(7, actual.size());
	}

	@Test
	public void testSmallAddNewPatient() {
		assertTrue(smallFacility.addPatient(new CurrentPatient("Ryan", "Do", new UHealthID("BAKA-1001"), 8888888,
				new GregorianCalendar(2023, 0, 0))));
	}

	@Test
	public void testSmallUpdatePhysician() {
		smallFacility.lookupByUHID(new UHealthID("UVWX-7890")).updatePhysician(9090909);
		CurrentPatient patient = smallFacility.lookupByUHID((new UHealthID("UVWX-7890")));
		assertEquals(9090909, patient.getPhysician());
	}

	@Test
	public void testSmallAddOldPatient() {
		assertFalse(smallFacility.addPatient(new CurrentPatient("Jin", "Young", new UHealthID("QWYU-0303"), 6786786,
				new GregorianCalendar(2017, 2, 2))));
	}
	
	@Test
	public void testSmallSetPhysician() {
		smallFacility.setPhysician(new UHealthID("AEHK-3524"), 1034569);
		CurrentPatient patient = smallFacility.lookupByUHID(new UHealthID("AEHK-3524"));
		assertEquals(1034569, patient.getPhysician());
	}

	@Test
	public void testSmallSetLastVisit() {
	    UHealthID uhid = new UHealthID("AEHK-3524");
		smallFacility.setLastVisit(uhid, new GregorianCalendar(2024, 1, 23));
		GregorianCalendar expect = new GregorianCalendar(2024, 1, 23);
		GregorianCalendar actual = smallFacility.lookupByUHID(uhid).getLastVisit();

		assertEquals(expect, actual);
	}
	

	// Large facility tests -------------------------------------------------------------------------
	@Test
	public void testLargeLookupPhysicianCount() {

		int count = 0;
		for (CurrentPatient patient : randomPatients) {
			if (randomPhysician.get(0).equals(patient.getPhysician()))
				count++;
		}

		ArrayList<CurrentPatient> actualPatient = largeFacility.lookupByPhysician(randomPhysician.get(0));
		assertEquals(count, actualPatient.size());
	}

	@Test
	public void testLargeLookupPhysicianPatient() {
		CurrentPatient expectedPatient1 = randomPatients.get(10);
		CurrentPatient expectedPatient2 = randomPatients.get(0);
		largeFacility.setPhysician(expectedPatient1.getUHealthID(), 8888888 );
		largeFacility.setPhysician(expectedPatient2.getUHealthID(), 8888888 );

		
		
		ArrayList<CurrentPatient> actualPatients = largeFacility.lookupByPhysician(expectedPatient1.getPhysician());
		assertTrue(actualPatients.contains(expectedPatient1) && actualPatients.contains(expectedPatient2));
	}
	
	@Test
	public void testLargeGetInactivePatients() {
		ArrayList<CurrentPatient> actual = largeFacility.getInactivePatients(randomDates.get(0));
		assertEquals(3, actual.size());
	}

	@Test
	public void testLargeGetPhysicianList() {
			ArrayList<Integer> actual = largeFacility.getPhysicianList();
			assertEquals(20, actual.size());
	}
	
	@Test
	public void testLargeAddOldPatient() {
		assertFalse(largeFacility.addPatient(new CurrentPatient((String)randomPatients.get(1).getFirstName(), (String)randomPatients.get(1).getLastName(), new UHealthID(randomUHID.get(1)), randomPhysician.get(1), randomDates.get(1))));
	}
	@Test
	public void testLargeAddNewPatient() {
		assertTrue(largeFacility.addPatient(new CurrentPatient("Ryan", "Do", new UHealthID("BAKA-1001"), 8888888,
				new GregorianCalendar(2023, 0, 0))));
	}
	
	@Test
	public void testLargeUpdatePhysician() {
		largeFacility.lookupByUHID(new UHealthID(randomUHID.get(1))).updatePhysician(randomPhysician.get(1));
		CurrentPatient patient = largeFacility.lookupByUHID((new UHealthID(randomUHID.get(1))));
		int physician = randomPhysician.get(1);
		assertEquals(physician, patient.getPhysician());
	}
	
	@Test
	public void testLargeSetPhysician() {
		largeFacility.setPhysician(new UHealthID(randomUHID.get(1)), 1034569);
		CurrentPatient patient = largeFacility.lookupByUHID(new UHealthID(randomUHID.get(1)));
		assertEquals(1034569, patient.getPhysician());
	}
	
	@Test
	public void testLargeSetLastVisit() {
	    UHealthID uhid = new UHealthID(randomUHID.get(10));
		largeFacility.setLastVisit(uhid, new GregorianCalendar(2024, 1, 23));
		GregorianCalendar expect = new GregorianCalendar(2024, 1, 23);
		GregorianCalendar actual = largeFacility.lookupByUHID(uhid).getLastVisit();

		assertEquals(expect, actual);
	}
	
}
