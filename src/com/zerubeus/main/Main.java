package com.zerubeus.main;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

import com.zerubeus.characters.Ball;
import com.zerubeus.characters.BallRight;
import com.zerubeus.characters.Ground;
import com.zerubeus.characters.HUD;
import com.zerubeus.characters.Player;
import com.zerubeus.characters.Spawn;
import com.zerubeus.screen.MenuGame;
import com.zerubeus.screen.MouseInput;
import com.zerubeus.screen.Sprite;
import com.zerubeus.screen.Window;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Main extends Canvas implements Runnable, KeyListener,
		ContactListener {
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 600, HEIGHT = WIDTH / 12 * 9;
	
	// rate to convert meters to pixels in the physics engine
	public static final float RATE = 30;
	
	// counts how many loops are made in the main thread
	private int counter = 49;
	
	@SuppressWarnings("unused")
	private int CollusionCounter;
	
	// load the bgImage using the static method loadSprite in the Sprite class
	private BufferedImage bgImage = Sprite.loadSprite("bgImage");
	// image and graphics used for double buffering
	private Image dbImage;
	private Graphics dbg;

	/* boolean to define when the game will be started and stopped */
	
	private boolean running = false;
	
	private Thread th;
	
	public static enum STATE {
		MENU, GAME, GAMEOVER
	};
	
	public static STATE State = STATE.MENU;
	
	MenuGame menu = new MenuGame();
	
	// variables for the Box2D world
	Vec2 gravity = new Vec2(0.0f, 10.0f);
	boolean doSleep = true;
	World world = new World(gravity, doSleep);
	
	// new array list to hold Ball references
	ArrayList<Ball> balls = new ArrayList<Ball>();
	
	// create a new player
	Player character = new Player(world);
	
	// create a new scoreBar
	HUD hud = new HUD();
	
	Spawn spawner = new Spawn(hud);

	ArrayList<BallRight> ballRight = new ArrayList<BallRight>();

	public Main() {

		addKeyListener(this);
		world.setContactListener(this);
		this.addMouseListener(new MouseInput());

		new Ground(world, RATE);

		/**
		 * @WIDHT : width of jpanel screen
		 * @HEIGHT : height of the jpanel screen
		 * @param : title of jpanel screen
		 * @this : refered to our main game instance
		 */

		new Window(WIDTH, HEIGHT, "Apocalypse 2D v0.0", this);

	}

	@Override
	public int getWidth() { // Width of the CustomPanel
		return WIDTH;
	}

	@Override
	public int getHeight() { // Height of the CustomPanel
		return HEIGHT;
	}

	@Override
	public Dimension getPreferredSize() { // Dimension of the CustomPanel
		return new Dimension(getWidth(), getHeight());
	}

	public synchronized void start() {

		do { running = false; } while (State == STATE.GAME);
		th = new Thread(this);
		th.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			th.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void destroy() {

	}

	public void run() {
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		
		while (running) {
			
			counter++;

			// Simulate the world
			float timeStep = 1.0f / 60.0f;
			int velocityIterations = 6;
			int positionIterations = 2;
			world.step(timeStep, velocityIterations, positionIterations);

			// add new balls to the world every 50th loop
			if (counter % 80 == 0) {
				balls.add(new Ball(world));
				if (hud.getLevel() == 2) {
					ballRight.add(new BallRight(world));
				}
			}

			repaint();

			// pause for 10 milliseconds
			try {
				Thread.sleep(10);
			} catch (InterruptedException ex) {
				// do nothing
			}

			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		}
	}

	public void paint(Graphics g) {

		/* g.setColor(Color.black); */
		/* g.fillRect(0, 0, getWidth(), getHeight()); */

		// fill the background Image to the Jframe Panel starting from x and y
		// position to 0
		g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);

		if (State == STATE.GAME) {

			// loop through each ball and call it's draw method
			Iterator<Ball> itr = balls.iterator();
			while (itr.hasNext()) {
				Ball b = itr.next();
				b.DrawBall(g);

				// if the ball should be removed then remove it
				if (b.shouldDelete())
					itr.remove();
			}

			Iterator<BallRight> itr2 = ballRight.iterator();
			while (itr2.hasNext()) {
				BallRight b2 = itr2.next();
				b2.DrawBall(g);

				if (b2.shouldDelete())
					itr2.remove();

			}

			hud.draw(g);

			// draw the main character.
			character.draw(g);

		} else if (State == STATE.MENU) {
			menu.draw(g);
		} else {
				
			spawner.GameOver(g);
				
		}

	}

	// sets up double buffering for graphics
	public void update(Graphics g) {
		if (dbImage == null) {
			dbImage = createImage(this.getSize().width, this.getSize().height);
			dbg = dbImage.getGraphics();
		}

		dbg.setColor(getBackground());
		dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

		dbg.setColor(getForeground());
		paint(dbg);

		g.drawImage(dbImage, 0, 0, this);

		character.animplayer();

		for (Ball ball : balls) {
			ball.animBalls();
		}

		for (BallRight ballRight2 : ballRight) {
			ballRight2.animBalls();
		}
		
		if (State == STATE.GAME) {
			hud.ticktack();
			spawner.tick();
		}
		
		
	}

	@Override
	public void beginContact(Contact contact) {

		String result = (String) contact.getFixtureA().getBody()
				.getFixtureList().getUserData();
		if (result == "Playron")
			hud.tick();

	}

	@Override
	public void endContact(Contact arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent key) {
		character.keyPress(key);
		if(key.getKeyCode() == KeyEvent.VK_ESCAPE) {
			State = STATE.MENU;
		}
	}

	public void keyReleased(KeyEvent key) {
		character.keyRelease(key);
	}

	public void keyTyped(KeyEvent key) {
		
	}

	public static void main(String[] args) {
		new Main();
	}

}
