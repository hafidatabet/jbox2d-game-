package com.zerubeus.characters;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import com.zerubeus.screen.Animation;
import com.zerubeus.screen.Sprite;


/**
 * @author Jebali Ala Eddine
 * ps : the code will always in the disorder because i'm all the time experimenting and finding a new 
 * way to do the same think so i put both of them one commented and the other coded 
 */

public class Player {
	
	private static final float RATE = 30;
	private Body characBody;
	private boolean playerMoveLeft = false;
	private boolean playerMoveRight = false;
	/*private Color Playercolor = Color.GREEN;*/
	@SuppressWarnings("unused")
	private String PlayerName;
	
	// a variable to load our sprite sheet image
	/*private BufferedImage spriteSheet;*/
	
	// Images for each animation
	private BufferedImage[] walKingLeft = {Sprite.getSprite(0, 1), Sprite.getSprite(2, 1)};
	private BufferedImage[] walKingRight = {Sprite.getSprite(0, 2), Sprite.getSprite(2, 2)};
	private BufferedImage[] standing_normal = {Sprite.getSprite(1, 0)};	
	
	// These are animation states
	private Animation walkLeft = new Animation(walKingLeft, 10);
	private Animation walkRight = new Animation(walKingRight, 10);
	private Animation standing = new Animation(standing_normal, 10);
	
	// This is the actual animation
	private Animation animation = standing;
	
	public Player(World world)
	{
		
		BodyDef bodyDef = new BodyDef();
	    bodyDef.type = BodyType.DYNAMIC;
	    bodyDef.fixedRotation = true;
	    bodyDef.position.set(20.0f/RATE, 375.0f/RATE);
	    characBody = world.createBody(bodyDef);
	    
	    PolygonShape box = new PolygonShape();
	    box.setAsBox(10.0f/RATE, 25.0f/RATE);
	    
	    FixtureDef fixtureDef = new FixtureDef();
	    fixtureDef.shape = box;
	    fixtureDef.density = 2.0f;
	    fixtureDef.friction = 0.3f;
	    characBody.createFixture(fixtureDef);
	    characBody.getFixtureList().setUserData("Playron");
	    
	    
	    
	}
	
	/* other method to load sprite sheets but i will not use it due to memory issue */
	
	/*public Sprite chargeImage() {
		ImageLoader loader = new ImageLoader();
		spriteSheet = loader.load("/mhaf.png");
		Sprite ss = new Sprite(spriteSheet);
		return ss;
	}*/
	
	public void animplayer() {
		animation.update();
	}
	
	public Fixture characFixtur() {
		return characBody.getFixtureList();
	}
	
	
	
	public void draw(Graphics g)
	{
		
		
		Vec2 position = characBody.getPosition();
		
		/* prototype for my character */
		
		/*g.setColor (Playercolor);
		g.fillRect((int) (position.x*RATE - 10), 370, 20, 30);
		g.fillOval ((int) (position.x*RATE - 10), 350, 20, 20);*/
		
		
		/* the method who display our sprite sheet to the screen ( i love doing this crazy stuff :D ) */
		
		g.drawImage(animation.getSprite(), (int) (position.x*RATE - 19), 350, 40, 50, null);
		
		// move the character in this method because it gets call every time
		Vec2 vel = characBody.getLinearVelocity();
		if (playerMoveLeft == true){
			vel.x = -5;
		}else if (playerMoveRight == true){
			vel.x = 5;
		}else{
			vel.x = 0;
		}
		characBody.setLinearVelocity(vel);
	}
	
	public void keyPress (KeyEvent key){
		
		if (key.getKeyCode() == KeyEvent.VK_LEFT) {
			playerMoveLeft = true;
			animation = walkLeft;
			animation.start();

		} else if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
			playerMoveRight = true;
			animation = walkRight;
			animation.start();
		}
		  
	}
	
	
	public void keyRelease (KeyEvent key){

		if (key.getKeyCode() == KeyEvent.VK_LEFT) {
			playerMoveLeft = false;
			animation.stop();
			animation.reset();
			animation = standing;

		} else if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
			playerMoveRight = false;
			animation.stop();
			animation.reset();
			animation = standing;
		}
		
	}

}
