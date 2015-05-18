package towerdefensegame.objects;

import java.math.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Tower {
	private String name;
	private int damage, reloadTime, currentLoad, range, cost, upgradeMultiplier, upgradeLevel;
	private Image sprite;
	private float X, Y;
	
	public Tower(String name, float xPos, float yPos) throws SlickException {
		this.sprite = new Image("res//" + name + ".png");
		this.name = name;
		this.upgradeLevel = 0;
		this.X = xPos;
		this.Y = yPos;
		
		if(name == "basic"){
			this.reloadTime = 1000;
			this.currentLoad=0;
			this.damage = 5;
			this.range = 32*3;
			this.cost = 100;
			this.upgradeMultiplier = 2;			
		}
		
		else if(name == "mortar"){
			this.reloadTime = 50;
			this.currentLoad=0;
			this.damage = 100;
			this.range = 50;
			this.cost = 40;
			this.upgradeMultiplier = 3;
		}
		// else if jaadaaa jadaaa
	}
	

	public int getReloadTime(){
		return reloadTime;
	}
	
	public void reloading(){
		currentLoad--;
	}
	
	public void reload(){
		currentLoad=reloadTime;
	}
	
	public boolean shootReady(Enemy e, GameContainer container){
		float yPos = container.getHeight()-getY();
		//TODO range is weird
		return currentLoad<=0 &&Math.max(X, e.getX() - Math.min(X, e.getX())) > getRange()&&Math.max(yPos, e.getY()) - Math.min(yPos,e.getX()) < getRange() && e.getY()>0 && e.getX()>0;
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

