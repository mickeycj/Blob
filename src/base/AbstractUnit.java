package base;

import game.Game;
import processing.core.PVector;

public abstract class AbstractUnit implements Unit {

	protected Game game;
	
	protected PVector position;
	protected PVector velocity;
	
	private float radius;
	
	protected AbstractUnit(float x, float y, float r, Game game) {
		this.game = game;
		this.position = new PVector(x, y);
		this.velocity = new PVector(0, 0);
		this.radius = r;
	}
	
	@Override
	public PVector getPosition() {
		return position;
	}
	@Override
	public float getRadius() {
		return radius;
	}
	
	@Override
	public boolean eat(Unit other) {
		float dist = PVector.dist(position, other.getPosition());
		if (dist < radius * 2 / 3 + other.getRadius() && radius > other.getRadius()) {
			radius += .25f * other.getRadius();
			return true;
		}
		return false;
	}
	
	@Override
	public void update() {
		position.add(velocity);
	}
	
	@Override
	public void render() {
		game.ellipse(position.x, position.y, radius * 2, radius * 2);
	}
}
