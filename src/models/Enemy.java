package models;

import base.AbstractUnit;
import game.Game;
import processing.core.PVector;

public class Enemy extends AbstractUnit {

	private PVector destination;
	
	public Enemy(float x, float y, float r, Game game) {
		super(x, y, r, game);
		this.setDestination();
	}
	
	public void setDestination() {
		destination = new PVector(World.random.nextInt(game.width), World.random.nextInt(game.height));
	}
	
	@Override
	public void update() {
		PVector newVelocity = new PVector(destination.x - position.x, destination.y - position.y);
		newVelocity.setMag(1);
		velocity.lerp(newVelocity, .2f);
		super.update();
	}
	
	@Override
	public void render() {
		game.fill(255, 0, 0);
		super.render();
	}
}
