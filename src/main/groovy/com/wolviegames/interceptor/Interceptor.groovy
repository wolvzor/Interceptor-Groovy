package com.wolviegames.interceptor
import com.wolviegames.interceptor.gameobject.Pilot;

public class Interceptor {
	public static void main(String[] args) {
		println("Interceptor!")
		
		// Generate a pilot for fun
		Pilot newPilot = new Pilot("Hello","World")
		println(newPilot)
		
	}
}
