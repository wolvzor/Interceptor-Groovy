package com.wolviegames.interceptor.gameobject

import spock.lang.Specification

class GunnerSpec extends Specification {
	
	def "Test gunnery skill generation"() {
		setup:
		Gunner gunner = new Gunner("test","dummy")
		
		expect:
		gunner.generateGunnerySkill(randomNumber)
		gunner.getGunnerySkill() == gunnerySkill
		
		where:
		randomNumber || gunnerySkill
		0 || 6
		1 || 5
		2 || 4
		3 || 4
		4 || 4
		5 || 4
		6 || 4
		7 || 4
		8 || 3
		9 || 2
	}

}
