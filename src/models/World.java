package models;

import java.util.ArrayList;
import java.util.Random;

import base.Component;
import base.Unit;
import game.Game;

public class World implements Component {

	public static final Random random = new Random(System.currentTimeMillis());
	
	private Game game;
	
	private Player player;
	private ArrayList<Enemy> units;
	private ArrayList<Enemy> eaten;
	
	public World(Game game) {
		this.game = game;
		this.player = new Player(16, this.game);
		this.units = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			this.units.add(randomEnemy(8, 32));
		}
		this.eaten = new ArrayList<>();
	}
	
	public boolean isPlayerWon() {
		return units.isEmpty();
	}
	
	public boolean isEnemyCountTooLow() {
		return units.size() < 10;
	}
	
	public void addRandomEnemy() {
		units.add(randomEnemy(8, 24));
	}
	
	public boolean update() {
		for (int i = 0; i < units.size(); i++) {
			Enemy enemy = units.get(i);
			if (player.eat(enemy)) {
				eaten.add(enemy);
			} else {
				if (enemy.eat(player)) {
					return false;
				} else {
					if (game.frameCount % 90 == 0) {
						enemy.setDestination();
					}
				}
				enemy.update();
			}
		}
		units.removeAll(eaten);
		eaten.clear();
		player.update();
		return true;
	}
	
	@Override
	public void render() {
		units.stream().forEach(Unit::render);
		player.render();
	}
	
	private Enemy randomEnemy(int min, int max) {
		return new Enemy(this.game.random(this.game.width), this.game.random(this.game.height), this.game.random(min, max), this.game);
	}
}
