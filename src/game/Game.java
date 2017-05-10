package game;

import models.World;
import processing.core.PApplet;

public class Game extends PApplet {

	private World world;
	
	@Override
	public void settings() {
		size(600, 600);
	}
	
	@Override
	public void setup() {
		world = new World(this);
	}
	
	@Override
	public void draw() {
		background(128);
		if (needMoreEnemy()) {
			addRandomEnemy();
		}
		if (!update() || isGameEnded()) {
			world = new World(this);
		}
	}
	
	private boolean needMoreEnemy() {
		return world.isEnemyCountTooLow() && Math.random() < .025 && frameCount % 3 == 0;
	}
	
	private void addRandomEnemy() {
		world.addRandomEnemy();
	}
	
	private boolean update() {
		boolean playing = world.update();
		world.render();
		return playing;
	}
	
	private boolean isGameEnded() {
		return world.isPlayerWon();
	}
}
