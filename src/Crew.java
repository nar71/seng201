import java.util.ArrayList;

public class Crew {
    private String name;
    private int numMembers;

    private ArrayList<CrewMember> members = new ArrayList<CrewMember>();

    Crew(String name, int numMembers) {
        this.name = name;
        this.numMembers = numMembers;
    }

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
            case "Tank":
                members.add(new Tank(name));
            break;
            default:
                throw new RuntimeException("Invalid member type");
        }
    }

    public void makeMemberSick(CrewMember member) {
        member.makeSick();
    }

    public ArrayList<CrewMember> getAllNonSickMembers() {
        ArrayList<CrewMember> allNonInfectedMembers = new ArrayList<CrewMember>();
        for (CrewMember member: getMembers()) {
            if (!member.isSick()) {
                allNonInfectedMembers.add(member);
            }
        }
        return allNonInfectedMembers;
    }

    public void makeAllMembersTired() {
        for (CrewMember member: getMembers()) {
            member.decrementTiredness(10);
        }
    }

    public boolean isValidNumMembers() {
        if (numMembers >= 2 && numMembers <= 4) {
            return true;
        }
        return false;
    }

    public ArrayList<CrewMember> getMembers() {
        return members;
    }
    
    public CrewMember getMemberByName(String name) {
    	CrewMember ret = new CrewMember("", "", "", 0, "");
    	for (CrewMember member: getMembers()) {
    		if (name.equals(member.getName())) {
    			return member;
    		}
    	}
    	return ret;
    }
    
    public int getNumMembers() {
    	return numMembers;
    }
}