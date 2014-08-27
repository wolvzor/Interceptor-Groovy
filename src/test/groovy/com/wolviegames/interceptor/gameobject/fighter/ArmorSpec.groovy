package com.wolviegames.interceptor.gameobject.fighter

import spock.lang.Specification
import spock.lang.Shared

class ArmorSpec extends Specification {
	
	List<List<Boolean>> testArmor
	@Shared
	List<Boolean> fullRow 
	@Shared
	List<Boolean> emptyRow
	
	def setupSpec() {
		fullRow = new ArrayList<Boolean>()
		emptyRow = new ArrayList<Boolean>()
		for(i in 1..10){
			fullRow.add(true)
			emptyRow.add(false)
		}		
	}
	
	def "Test armor creation"() {	
		expect:
		Armor armor = new Armor(armorRating)
		armor.getArmorTiles() == armorTiles
		
		where:
		armorRating || armorTiles
		10 || [fullRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow]
		20 || [fullRow,fullRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow]
		30 || [fullRow,fullRow,fullRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow]
		40 || [fullRow,fullRow,fullRow,fullRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow]
		50 || [fullRow,fullRow,fullRow,fullRow,fullRow,emptyRow,emptyRow,emptyRow,emptyRow,emptyRow]
		60 || [fullRow,fullRow,fullRow,fullRow,fullRow,fullRow,emptyRow,emptyRow,emptyRow,emptyRow]
		70 || [fullRow,fullRow,fullRow,fullRow,fullRow,fullRow,fullRow,emptyRow,emptyRow,emptyRow]
		80 || [fullRow,fullRow,fullRow,fullRow,fullRow,fullRow,fullRow,fullRow,emptyRow,emptyRow]
		90 || [fullRow,fullRow,fullRow,fullRow,fullRow,fullRow,fullRow,fullRow,fullRow,emptyRow]
		100 || [fullRow,fullRow,fullRow,fullRow,fullRow,fullRow,fullRow,fullRow,fullRow,fullRow]
		
	}
	
	def "Test armor destruction"() {
		when: "You blow away a piece of armor"
		Armor armor = new Armor(20)
		armor.destroyArmor(0, 0)
		
		then: "Only that one got blown away, the ones around it are ok"
		armor.getArmorTiles().get(0).get(0) == false
		armor.getArmorTiles().get(0).get(1) == true
		armor.getArmorTiles().get(1).get(0) == true
	}

}
