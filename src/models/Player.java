package models;

import base.AbstractUnit;
import game.Game;
import processing.core.PVector;

public class Player extends AbstractUnit {

	public Player(float r, Game game) {
		super(game.width / 2, game.height / 2, r, game);
	}
	
	@Override
	public void update() {
		PVector newVelocity = new PVector(game.mouseX - position.x, game.mouseY - position.y);
		newVelocity.setMag(3);
		velocity.lerp(newVelocity, .2f);
		super.update();
	}
	
	@Override
	public void render() {
		game.fill(255);
		super.render();
	}
}
