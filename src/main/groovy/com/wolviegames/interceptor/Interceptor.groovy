package com.wolviegames.interceptor

import com.wolviegames.interceptor.display.GamePanel

import javax.swing.JFrame

public class Interceptor{

	public static void main(String[] args) {
		println("Interceptor!")

		int fps = 30
		long period = 1000.0/fps
		int PWIDTH=800
		int PHEIGHT=600

		// Generate a gamepanel to start rendering
		GamePanel gamePanel = new GamePanel(period,PWIDTH, PHEIGHT)

		JFrame jFrame = new JFrame("Interceptor Demo");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(PWIDTH,PHEIGHT);
		jFrame.add(gamePanel)
		jFrame.setVisible(true);

		gamePanel.run()
		
	}
}
