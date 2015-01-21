package com.zerubeus.characters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.zerubeus.main.Main;

public class HUD {

	public static int HEALTH = 104;
	private int greenValue = 255;

	private int score = 0;
	private int level = 1;

	public HUD() {

	}

	public void tick() {
		HEALTH -= 4;
		if(HEALTH <= 0) {
			Main.State = Main.STATE.GAMEOVER;
			resetAll();
		}
	}

	public void ticktack() {
		score++;
	}

	public void draw(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, HEALTH * 2, 32);
		g.setColor(Color.WHITE);
		g.drawRect(15, 15, 200, 32);
		
		g.setFont(new Font("Pokemon GB", Font.BOLD, 12));
		g.drawString("Score : " + score, 19, 64);
		g.drawString("Level : " + level, 19, 80);
	}

	public static int clamp(int val, int min, int max) {
		return Math.max(min, Math.min(max, val));
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public void resetAll() {
		setScore(0);
		setLevel(1);
		HEALTH = 104;
	}

}
