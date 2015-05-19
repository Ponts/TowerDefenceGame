package towerdefensegame.objects;

import java.math.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Tower {
	private String name;
	private int damage, reloadTime, currentLoad, range, cost,
			upgradeMultiplier, upgradeLevel;
	private Image sprite;
	private float X, Y;
	private String bulletName;

	public Tower(String name, float xPos, float yPos) throws SlickException {
		this.sprite = new Image("res//" + name + ".png");
		this.setName(name);
		this.upgradeLevel = 0;
		this.X = xPos;
		this.Y = yPos;

		if (name == "basic") {
			this.reloadTime = 1000;
			this.currentLoad = 0;
			this.damage = 50;
			this.range = 32 * 4;
			this.cost = 100;
			this.upgradeMultiplier = 2;
			this.setBulletName("bullet-red");
		}

		else if (name == "rapid") {
			this.reloadTime = 50;
			this.currentLoad = 0;
			this.damage = 5;
			this.range = 32 * 3;
			this.cost = 200;
			this.upgradeMultiplier = 3;
			this.setBulletName("rapidBullet");
		}

		else if (name == "slow") {
			this.reloadTime = 500;
			this.currentLoad = 0;
			this.damage = 20;
			this.range = 32 * 3;
			this.cost = 300;
			this.upgradeMultiplier = 3;
			this.setBulletName("slowBullet");
		}
		// else if jaadaaa jadaaa
	}

	public int getReloadTime() {
		return reloadTime;
	}

	public void reloading() {
		currentLoad--;
	}

	public void reload() {
		currentLoad = reloadTime;
	}

	public boolean shootReady(Enemy e, GameContainer container) {
		float yPos = container.getHeight() - e.getY();

		return currentLoad <= 0
				&& distance(getX(), getY(), e.getX(), yPos) < getRange()
				&& e.getY() > 0 && e.getX() > 0;
	}

	private float distance(float x, float y, float X, float Y) {
		float dX = Math.max(x, X) - Math.min(x, X);
		float dY = Math.max(y, Y) - Math.min(y, Y);
		return (float) Math.hypot(dX, dY);
	}

	private void upgrade() {
		this.damage *= upgradeMultiplier;
		this.range *= upgradeMultiplier / 2;
		this.cost *= upgradeMultiplier;
		this.upgradeLevel += 1;
	}

	public int getCost() {
		return cost;
	}

	public Image getSprite() {
		return this.sprite;
	}

	public float getY() {
		return this.Y;
	}

	public float getX() {
		return this.X;
	}

	public int getRange() {
		return this.range;
	}

	public int getDamage() {
		return damage;
	}

	public String getBulletName() {
		return bulletName;
	}

	public void setBulletName(String bulletName) {
		this.bulletName = bulletName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
