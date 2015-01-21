package com.zerubeus.characters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.zerubeus.main.Main;

public class Spawn {
	
	private HUD hud;
	private int scoreKeep = 0;
	public Rectangle helpButton = new Rectangle(235, 190, 100, 50);
	
	
	public Spawn(HUD hud) {
		this.hud = hud;
	}
	
	public void tick() {
		scoreKeep++;
		if (scoreKeep >= 5000) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
		}
	}
	
	public void GameOver(Graphics g) {
		g.setFont(new Font("pokemon gb", Font.BOLD, 30));
		g.setColor(Color.WHITE);
		g.drawString("Game Over", Main.WIDTH / 4, 170);
		Graphics2D g2d = (Graphics2D) g;
		g.setFont(new Font("pokemon gb", Font.BOLD, 15));
		g.drawString("Menu", helpButton.x + 19, helpButton.y + 30);
		g2d.draw(helpButton);
	}
	

	
}
