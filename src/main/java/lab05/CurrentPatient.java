package lab05;

import assign02.Patient;
import assign02.UHealthID;

import java.util.GregorianCalendar;

/**
 * The class represents a patient with additional attributes,
 * including an assigned physician and the date of their last visit.
 *
 * @author Bao Phuc Do and Khang Nguyen
 * @version Jan 25, 2024
 */
public class CurrentPatient extends Patient {

	private int assignedPhysician;
	private GregorianCalendar lastVisit;


	/**
	 * Constructs a new object with the specified attributes.
	 *
	 * @param firstName - The first name of the patient.
	 * @param lastName  - The last name of the patient.
	 * @param uHealthID - The unique health identifier of the patient.
	 * @param physician - The identifier of the assigned physician.
	 * @param lastVisit - The date of the last visit.
	 */
	public CurrentPatient(String firstName, String lastName, UHealthID uHealthID, int physician, GregorianCalendar lastVisit) {
		super(firstName, lastName, uHealthID);
		this.assignedPhysician = physician;
		this.lastVisit = lastVisit;

	}

	/**
	 * Retrieves the identifier of the assigned physician.
	 *
	 * @return The identifier of the assigned physician.
	 */
	public int getPhysician() {
		return assignedPhysician;
	}

	/**
	 * Retrieves the date of the last visit.
	 *
	 * @return The date of the last visit.
	 */
	public GregorianCalendar getLastVisit() {
		return lastVisit;
		
	}
	/**
	 * Updates the assigned physician for the patient.
	 *
	 * @param newPhysician - The identifier of the new assigned physician.
	 */

	public void updatePhysician(int newPhysician) {
		this.assignedPhysician = newPhysician;
	}

	/**
	 * Updates the date of the last visit for the patient.
	 *
	 * @param date - The new date of the last visit.
	 */
	public void updateLastVisit(GregorianCalendar date) {
		this.lastVisit = date;
	}
}
