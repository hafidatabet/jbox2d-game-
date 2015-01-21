package com.zerubeus.characters;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import com.zerubeus.screen.Animation;
import com.zerubeus.screen.Sprite;

public class Ball {
	
	
	private static final float RATE = 30;
	private Body Ballbody;
	private int x;
	private int y;
	private World world;
	private int radius;
	private String name;

	
		// Images for each animation
		private BufferedImage[] standing_normal = {Sprite.getSprite(0, 11), Sprite.getSprite(1, 11), Sprite.getSprite(2, 11)};	
		
		// These are animation states
		private Animation orbiting = new Animation(standing_normal, 10);
		
		// This is the actual animation
		private Animation animation = orbiting;
	
	/*
	 * This class holds the info for a ball.
	 * Pass the Box2D world to the constructor.
	 */
	public Ball (World world){
		
		
		this.world = world;
		
		
		BodyDef bodyDef = new BodyDef();
	    bodyDef.type = BodyType.DYNAMIC;
	    bodyDef.position.set(0.0f/RATE, 0.0f/RATE);
	    Ballbody = world.createBody(bodyDef);
	    
	    CircleShape circle = new CircleShape();
	    radius = (int) (Math.random()*30+15);
	    circle.m_radius = radius/RATE;
	    
	    FixtureDef fixtureDef = new FixtureDef();
	    fixtureDef.shape = circle;
	    fixtureDef.restitution = 0.8f;
	    fixtureDef.density = 2.0f;
	    fixtureDef.friction = 0.3f;
	    fixtureDef.filter.groupIndex = -1;
	    Ballbody.createFixture(fixtureDef);
	    Ballbody.getFixtureList().setUserData("Ballounaton");
	    
		Vec2 ballVec = new Vec2((float) (Math.random()*8+2),0.0f);
		Ballbody.setLinearVelocity(ballVec);

	}
	
	
	public Fixture ballfixtur() {
		return this.Ballbody.getFixtureList();
	}
	
	public void animBalls() {
		animation.update();
		animation.start();
		
	}
	
	/*
	 * This method draws the ball to the screen
	 */
	public void DrawBall (Graphics g)
	{
		Vec2 position = Ballbody.getPosition();
		x = (int)(position.x*RATE-radius);
		y = (int)(position.y*RATE-radius);
	    
		/*g.setColor (Color.RED);
	    g.fillOval(x, y, radius*2, radius*2);*/
	    
	   g.drawImage(animation.getSprite(), x, y, radius*2, radius*2, null);
	   
	  
	    
	}

	
	/*
	 * This method sees if the ball should be removed if it is off screen
	 * If it returns true then it will be deleted
	 */
	public boolean shouldDelete(){
		if (y > 500){
			// remove ball from Box2D world before removing Ball instance.
			world.destroyBody(Ballbody);
			return true;
		}else{
			return false;
		}
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	

}
