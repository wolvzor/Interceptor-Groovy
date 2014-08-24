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
	
	protected void generateBeginningSkills() {
		Random rand = new Random()
		generatePilotingSkill(rand.nextInt(10))
		generateGunnerySkill(rand.nextInt(10))
	}
	
	protected void generatePilotingSkill(int randomNumber) {
		switch(randomNumber) {
			case 0:
				pilotingSkill = 6
				break
			case 1:
				pilotingSkill = 5
				break
			case 2:
				pilotingSkill = 5
				break
			case 3:
				pilotingSkill = 5
				break
			case 4:
				pilotingSkill = 5
				break
			case 5:
				pilotingSkill = 5
				break
			case 6:
				pilotingSkill = 5
				break
			case 7:
				pilotingSkill = 5
				break
			case 8:
				pilotingSkill = 4
				break
			case 9:
				pilotingSkill = 3
				break
		}
	}
	
	protected void generateGunnerySkill(int randomNumber) {
		switch(randomNumber) {
			case 0:
				gunnerySkill = 6
				break
			case 1:
				gunnerySkill = 5
				break
			case 2:
				gunnerySkill = 4
				break
			case 3:
				gunnerySkill = 4
				break
			case 4:
				gunnerySkill = 4
				break
			case 5:
				gunnerySkill = 4
				break
			case 6:
				gunnerySkill = 4
				break
			case 7:
				gunnerySkill = 4
				break
			case 8:
				gunnerySkill = 3
				break
			case 9:
				gunnerySkill = 2
				break
		}
	}
}
