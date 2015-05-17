package towerdefensegame.objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tower {
	private String name;
	private int damage, rateOfFire, range, cost, upgradeMultiplier, upgradeLevel;
	private Image sprite;
	private float X, Y;
	
	public Tower(String name, float xPos, float yPos) throws SlickException {
		this.sprite = new Image("res//" + name + ".png");
		this.name = name;
		this.upgradeLevel = 0;
		this.X = xPos;
		this.Y = yPos;
		
		if(name == "basic"){
			this.rateOfFire = 5;
			this.damage = 5;
			this.range = 32*3;
			this.cost = 100;
			this.upgradeMultiplier = 2;			
		}
		
		else if(name == "mortar"){
			this.rateOfFire = 1;
			this.damage = 100;
			this.range = 50;
			this.cost = 40;
			this.upgradeMultiplier = 3;
		}
		// else if jaadaaa jadaaa
	}
	
	public void act(){
		shoot();
	}
	
	private void shoot(){
		// TODO
		//if target within range
		//call target take damage method?
	}
	
	private void upgrade(){
		this.damage *= upgradeMultiplier;
		this.range *= upgradeMultiplier/2;
		this.cost *= upgradeMultiplier;
		this.upgradeLevel += 1;
	}
	public int getCost(){
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
	
	public int getRange(){
		return this.range;
	}
	
	public int getDamage(){
		return damage;
	}
}

