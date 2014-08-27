package com.wolviegames.interceptor.gameobject.fighter

import groovy.transform.ToString;

@ToString
class Armor{
	
	// Don't want people to set the armor tiles, but they can see it
	private List<List<Boolean>> armorTiles
	
	/* Fills out the armorTiles according to the rating
	 * 
	 * Ex: Rating of 10 would only fill out one row
	 * Rating 100 would fill them all out (it's a 10x10 grid)
	 */
	public Armor(Integer rating){
		armorTiles = new ArrayList()
		List<Boolean> fullRow = new ArrayList<Boolean>()
		List<Boolean> emptyRow = new ArrayList<Boolean>()
		for (i in 1..10){
			fullRow.add(true)
			emptyRow.add(false)
		}
		
		for (i in 1..rating/10){
			armorTiles.add(fullRow.clone())
		}
		
		if (rating<=90){
			for (i in rating/10..9){
				armorTiles.add(emptyRow.clone())
			}	
		}	
	}
	
	public List<List<Boolean>> getArmorTiles() {
		return armorTiles.clone()
	}
	
	// TODO: Remove this once the weapon templates have been established - this is for testing only
	public void destroyArmor(Integer x, Integer y){
		armorTiles[x][y] = false
	}

}
