import java.util.ArrayList;

public class Crew {

    /**
     * A string representation of the name of the crew
     */
    private String name;
    
    /**
     * An integer representation of the number of crew members
     */
    private int numMembers;

    /**
     * An array list of CrewMember instances
     */
    private ArrayList<CrewMember> members = new ArrayList<CrewMember>();

    /**
     * Crew constructor
     * @param name A string representation of the crew name
     * @param numMembers A integer representation of the number of members wanted
     */
    Crew(String name, int numMembers) {
        this.name = name;
        this.numMembers = numMembers;
    }

    /**
     * Adds a crew member to the array list
     * @param type A string representation of the type that calls the relevant subclass
     * @param name A string representation of the name of the crew member
     */
    public void addCrewMember(String type, String name) {
        switch (type) {
            case "Barter":
                members.add(new Barter(name));
            break;
            case "Mechanic":
                members.add(new Mechanic(name));
            break;
            case "Nerd":
                members.add(new Nerd(name));
            break;
            case "Scout":
                members.add(new Scout(name));
            break;
            case "Soldier":
                members.add(new Soldier(name));
            break;
            case "Medic":
                members.add(new Medic(name));
            break;
            default:
                throw new RuntimeException("Invalid member type");
        }
    }

    /**
     * Removes a crew member from array list
     * @param member The crew member instance to be removed
     */
    public void removeCrewMember(CrewMember member) {
        members.remove(member);
    }

    /**
     * Makes a crew member sick
     */
    public void makeMemberSick(CrewMember member) {
        member.makeSick();
    }

    public void makeMemberSleep(CrewMember member) {
        member.sleep();
        member.removeAction();
    }

    public boolean hasMembers() {
        if (members.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * Gets an array list of all non sick crew members
     * @return allNonInfectedMembers A CrewMember array list of all non sick members
     */
    public ArrayList<CrewMember> getAllNonSickMembers() {
        ArrayList<CrewMember> allNonInfectedMembers = new ArrayList<CrewMember>();
        for (CrewMember member: getMembers()) {
            if (!member.isSick()) {
                allNonInfectedMembers.add(member);
            }
        }
        return allNonInfectedMembers;
    }

    public boolean outOfActions() {
        for (CrewMember member: getMembers()) {
            if (member.hasActionsLeft()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Makes all crew members tired - used for a random event
     */
    public void makeAllMembersTired() {
        for (CrewMember member: getMembers()) {
            member.decrementTiredness(10);
        }
    }

    /**
     * Checks if number of members specified earlier in the game is within valid parameters
     * @return boolean
     */
    public boolean isValidNumMembers() {
        if (numMembers >= 2 && numMembers <= 4) {
            return true;
        }
        return false;
    }

    /**
     * Gets crew members
     * @return members array list of current members in the game
     */
    public ArrayList<CrewMember> getMembers() {
        return members;
    }
    
    /**
     * Gets members by name specified
     * @param name A string of the name to match match crew member
     * @return emptyCrewMember | member An empty instance of Crew Member or 
     *                          the crew member that matches the name given
     */
    public CrewMember getMemberByName(String name) {
    	CrewMember emptyCrewMember = new CrewMember();
    	for (CrewMember member: getMembers()) {
    		if (name.equals(member.getName())) {
    			return member;
    		}
    	}
    	return emptyCrewMember;
    }
    
    /**
     * Gets number of members
     * @return numMembers An integer of number of members
     */
    public int getNumMembers() {
    	return numMembers;
    }
}