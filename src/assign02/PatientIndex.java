package assign02;

import java.util.TreeMap;

/**
 * The class represents an index that maps patient IDs to their names.
 * It uses a TreeMap to store patient information with UHealthID as the key and the
 * concatenation of the patient's first and last names as the value.
 *
 * @author Bao Phuc Do and Khang Nguyen
 * @version Jan 25, 2024
 */
public class PatientIndex {
    private TreeMap<UHealthID, String> member;

    /**
     * Constructs a new object with an empty TreeMap.
     */
    public PatientIndex(){
        member = new TreeMap<>( ( id1,  id2) -> id1.toString().compareTo(id2.toString()) );
    }

    /**
     * Adds a patient to the index.
     *
     * @param p - The patient to be added.
     */
    public void addPatient(Patient p){
        UHealthID id = p.getUHealthID();
        String name = p.getFirstName() + " " + p.getLastName();

        member.put(id, name);
    }

    /**
     * Removes a patient from the index.
     *
     * @param p - The patient to be removed.
     */
    public void removePatient(Patient p){
        member.remove(p.getUHealthID());
    }

    /**
     * Retrieves the name associated with the given UHealthID.
     *
     * @param id - The UHealthID for which to retrieve the name.
     * @return The name associated with the given UHealthID, or null if the ID is not found.
     */
    public String getName(UHealthID id){
        return member.get(id);
    }
}
