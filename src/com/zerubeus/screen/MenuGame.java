package com.zerubeus.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import com.zerubeus.main.Main;

public class MenuGame {
	
	public Rectangle playButton = new Rectangle(Main.WIDTH / 6, 190, 100, 50);
	public Rectangle helpButton = new Rectangle(260, 190, 100, 50);
	public Rectangle quitButton = new Rectangle(410, 190, 100, 50);
	
	public MenuGame() {
		
	}
	
	public void draw(Graphics g) {
		
		try {
            //create the font to use. Specify the size!
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/Pokemon_GB.ttf")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(FontFormatException e)
        {
            e.printStackTrace();
        }
		
		Graphics2D g2d = (Graphics2D) g;
		
		g.setFont(new Font("pokemon gb", Font.BOLD, 30));
		g.setColor(Color.WHITE);
		g.drawString("apocalypse 2D", Main.WIDTH / 6, 170);
		
		g.setFont(new Font("pokemon gb", Font.BOLD, 15));
		g.drawString("Play", playButton.x + 19, playButton.y + 30);
		g2d.draw(playButton);
		g.drawString("Help", helpButton.x + 19, helpButton.y + 30);
		g2d.draw(helpButton);
		g.drawString("Exit", quitButton.x + 19, quitButton.y + 30);
		g2d.draw(quitButton);
		
	}

}
