package com.wolviegames.interceptor.gameobject.fighter

import com.wolviegames.interceptor.gameobject.Gunner
import com.wolviegames.interceptor.gameobject.Pilot
import groovy.transform.ToString;

@ToString
class Fighter {
	
	// TODO verify relationship between fighter and pilot/gunner
	Pilot pilot
	Gunner gunner
	
	String faction // (enumeration laters?)
	String name
	Integer mass
	Integer leftEngine
	Integer rightEngine
	Integer centerEngine
	Boolean antigravity
	Boolean streamlined
	
	// Shields
	Integer bowShield
	Integer rightShield
	Integer leftShield
	Integer sternShield
	
	// Armor
	Armor bowArmor 
	Armor rightArmor
	Armor leftArmor
	Armor sternArmor
	
	// Weapons
	
	Integer velocity
	Integer facing
	Boolean seriouslyOutOfControl
	Boolean destroyed
	
}
