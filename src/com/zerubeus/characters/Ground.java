package com.zerubeus.characters;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class Ground {

	private Body groundBody;
	
	public Ground(World world, float RATE) {
		
		// add a ground floor to our Box2D world
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(300.0f / RATE, 400.0f / RATE);
		Body groundBody = world.createBody(groundBodyDef);
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(300.0f / RATE, 0);
		groundBody.createFixture(groundBox, 0.0f);

		// wall fixture
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = groundBox;
		fixtureDef.density = 2.0f;
		fixtureDef.filter.groupIndex = -1;

		// left wall
		groundBodyDef.position.set(0.0f / RATE, 350.0f / RATE);
		groundBody = world.createBody(groundBodyDef);
		groundBox.setAsBox(0, 50.0f / RATE);
		groundBody.createFixture(fixtureDef);

		// right wall
		groundBodyDef.position.set(600.0f / RATE, 350.0f / RATE);
		groundBody = world.createBody(groundBodyDef);
		groundBox.setAsBox(0, 50.0f / RATE);
		groundBody.createFixture(fixtureDef);
		groundBody.getFixtureList().setUserData("7iton");
	}
	
	public Fixture wallFixture() {
		return this.groundBody.getFixtureList();
	}


	

}
