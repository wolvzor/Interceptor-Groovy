package com.wolviegames.interceptor.gameobject

import groovy.transform.ToString;

@ToString
class Gunner {
	String pilotName
	String callsign
	Integer gunnerySkill
	Integer familiarityBonus
	
	public Gunner(String _pilotName, String _callsign) {
		pilotName = _pilotName
		callsign = _callsign
		familiarityBonus = 0
		generateBeginningSkills()
	}
	
	protected void generateBeginningSkills() {
		Random rand = new Random()
		generateGunnerySkill(rand.nextInt(10))
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
