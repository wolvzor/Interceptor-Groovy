package gameobject

import groovy.transform.ToString

@ToString
class Pilot {
	String pilotName
	String callsign
	Integer pilotingSkill
	Integer gunnerySkill
	Integer familiarityBonus
	
	public Pilot(String _pilotName, String _callsign) {
		pilotName = _pilotName
		callsign = _callsign
		familiarityBonus = 0
		generateBeginningSkills()
	}
	
	private void generateBeginningSkills() {
		Random rand = new Random()
		switch(rand.nextInt(10)) {
			case 0:
				pilotingSkill = 6
				gunnerySkill = 6
				break
			case 1:
				pilotingSkill = 5
				gunnerySkill = 5
				break
			case 2:
				pilotingSkill = 5
				gunnerySkill = 4
				break
			case 3:
				pilotingSkill = 5
				gunnerySkill = 4
				break
			case 4:
				pilotingSkill = 5
				gunnerySkill = 4
				break
			case 5:
				pilotingSkill = 5
				gunnerySkill = 4
				break
			case 6:
				pilotingSkill = 5
				gunnerySkill = 4
				break
			case 7:
				pilotingSkill = 5
				gunnerySkill = 4
				break
			case 8:
				pilotingSkill = 4
				gunnerySkill = 3
				break
			case 9:
				pilotingSkill = 3
				gunnerySkill = 2
				break
		}
	}
}
