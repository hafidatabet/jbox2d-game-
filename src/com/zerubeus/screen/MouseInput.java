package com.zerubeus.screen;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.event.MouseInputListener;

import com.zerubeus.main.Main;

public class MouseInput implements MouseInputListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

		int mx = e.getX();
		int my = e.getY();

		/* debug stuff */
		System.out.println("this x " + mx + " this y " + my);
		
		if (Main.State == Main.STATE.MENU) {
			if (mx >= 99 && mx <= 200) {
				if (my >= 191 && my <= 238) {
					Main.State = Main.STATE.GAME;
					
				}
			}

			if (mx >= 260 && mx <= 360) {
				if (my >= 190 && my <= 238) {
					openLinkInBrowser(e);
				}
			}

			if (mx >= 410 && mx <= 510) {
				if (my >= 190 && my <= 238) {
					System.exit(1);
				}
			}
		} else if (Main.State == Main.STATE.GAMEOVER) {
			if (mx >= 260 && mx <= 360) {
				if (my >= 190 && my <= 238) {
					Main.State = Main.STATE.MENU;
				}
			}
		}
		
		

		/*
		 * this x 459 this y 189 this x 459 this y 239 this x 510 this y 212
		 * this x 410 this y 212
		 */

	}
	
	public void openLinkInBrowser(MouseEvent e2){

	    try {
	        URI uri = new URI("https://github.com/zerubeus");
	        java.awt.Desktop.getDesktop().browse(uri);

	    } catch (URISyntaxException | IOException e) {
	        //System.out.println("THROW::: make sure we handle browser error");
	        e.printStackTrace();
	    }

	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
