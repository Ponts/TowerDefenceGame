package towerdefensegame.objects;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.PathFinder;

public class Enemy implements Mover {
	private int walkSpeed;
	private int attackDamage;
	private int slowCounter;
	private int slowDuration;
	private int health;
	private String special = null;
	private Image sprite;
	private float x, y;
	private String name;
	private int i = 0;
	private int bounty;

	public Enemy(String name, float xPos, float yPos, int bounty)
			throws SlickException {
		this.sprite = new Image("res//" + name + ".png");
		this.x = xPos;
		this.y = yPos;
		this.name = name;
		this.bounty = bounty;
		this.health = 100;
		this.i = 0;
		this.attackDamage = 10;
		this.walkSpeed = 2;
		this.slowDuration = 3000;
	}

	public void update(int delta, Path road) {
		float nextX = road.getX(getI()) * 32;
		float nextY = road.getY(getI()) * 32;

		if (getX() > nextX) {
			setX(getX() - walkSpeed * delta / 12f);
		} else {
			setX(getX() + walkSpeed * delta / 12f);
		}

		if (getY() > nextY) {
			setY(getY() - walkSpeed * delta / 12f);
		} else {
			setY(getY() + walkSpeed * delta / 12f);
		}

		if (Math.max(getX(), nextX) - Math.min(getX(), nextX) < 2
				&& Math.max(getY(), nextY) - Math.min(getY(), nextY) < 2) {
			setI(getI() + 1);
		}

		slowCounter--;
		if (slowCounter <= 0) {
			slowCounter = slowDuration;
			walkSpeed = 2;
		}
	}

	public Image getSprite() {
		return sprite;
	}

	public float getY() {
		return this.y;
	}

	public float getX() {
		return this.x;
	}

	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(sprite, x, y);
	}

	public void takeDamage(int damage) {
		health = health - damage;

	}

	public int getHealth() {
		return health;
	}

	public int getBounty() {
		return bounty;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public boolean isAlive() {
		return health > 0;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public void slow() {
		walkSpeed = 1;

	}
}
