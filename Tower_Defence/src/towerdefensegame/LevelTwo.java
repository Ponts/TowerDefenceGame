package towerdefensegame;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.PathFinder;




import towerdefensegame.objects.*;

public class LevelTwo extends BasicGameState {
	private int ID;
	private TiledMap map = null;
	private int x = 0;
	private int y = 0;
	private ArrayList<Tower> towers;
	private ArrayList<Enemy> enemies;
	private Player player;
	private PathFinder ph;
	private Path path;
	
	public LevelTwo(int id){
		this.ID = id;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame arg1)
			throws SlickException {
		map = load();
		towers = new ArrayList<>();
		enemies = new ArrayList<>();
		player = new Player();
		
		LayerBasedMap blockedMap = new LayerBasedMap(map, 1);
		// Add enemies
		enemies.add(new Enemy("cactiball", 5*32, 0, 50));
		ph = new AStarPathFinder(blockedMap, 1000, false);
		// TODO
		path = ph.findPath(enemies.get(0), 5,0,20,16 );
		
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		map.render(0, 0);
		g.drawString("Health " + player.getHealth() + ", Money "+ player.getMoney(), 10,580);
		g.fillRect(x, y, 32, 32); // Current mouse location
		for(Tower t: towers){
			g.drawImage(t.getSprite(), t.getX(), container.getHeight() - t.getY());
		}
		
		
		for(Enemy e:enemies){
		e.render(container, sbg, g);
		g.destroy();
		}
		
		
		
		
	}
	

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = container.getInput();
		int xPos = Mouse.getX();
		int yPos = Mouse.getY();
		
		
		
		container.setMouseGrabbed(true);
		x = xPos;
		y = container.getHeight()-yPos;
		//go back to level select
		if(input.isKeyPressed(input.KEY_ESCAPE)) {
			sbg.enterState(1);
		}
		
		//spawn different towers
		//Basic Tower
		if(input.isKeyPressed(input.KEY_1) && map.getTileId((x+16)/32,(y+16)/32,1) != 0) {
			//Tile is a scenery tile
			
			Tower tower = new Tower("basic", xPos, yPos);
			if(player.getMoney() >= tower.getCost()){
			doTower(tower);
			}
			else{
				System.out.println("Not enough money");
			}
		}
		/*if(input.isKeyPressed(input.KEY_2)) {
			Tower tower = new Tower("mortar", xPos, yPos);
		}*/
		
		//Towers act TODO
		/*for(Tower tower : towers) {
			tower.act();
		}*/
		
		checkTowerRange(container);
		
		
		startRound(delta);
			
		
		//Make enemies move TODO
		//enemy.move(path);
		
	}

	@Override
	public int getID() {
		
		return ID;
	}

	private void checkTowerRange(GameContainer container){
		for(Tower t:towers){
			Enemy e;
			for(int i = enemies.size() -1; i >= 0; i-- ){
				e = enemies.get(i);
				float X = e.getX();
				float Y = e.getY();
				if(t.getX() -X<t.getRange() && container.getHeight() - t.getY() - Y<t.getRange()){
					enemies.remove(e);
				}
			}
		}
		
	}
	
	private TiledMap load() throws SlickException {
		return new TiledMap("res//Level2.tmx", "res//pictures//tileset.png");
	}
	
	private void doTower(Tower tower){
		towers.add(tower);
		player.pay(tower.getCost());
	}
	
	private void startRound(int delta){
		Enemy c;
		for(int i = enemies.size() -1; i >= 0; i-- ){
			
			
			c = enemies.get(i);
			float circleX = c.getX();
			float circleY = c.getY();
			if(circleY < 45){
				c.setY(circleY + delta/6f);
			}
			else if(circleX < 356 && circleY >= 45 && circleY < 47) {
				c.setX(circleX + delta/6f);
			}
			else if(circleX >= 356 && circleY < 118){
				c.setY(circleY + delta/6f);
			}
			else if(circleX > 44 && circleY >= 118 && circleY < 121){
				c.setX(circleX - delta/6f);
			}
			else if(circleX <= 44 && circleY <= 191){
				c.setY(circleY + delta/6f);
			}
			else if(circleX < 355 && circleY >= 191 && circleY < 200){
				c.setX(circleX + delta/6f);
			}
			else if(circleX >= 355 && circleY < 400){
				c.setY(circleY + delta/6f);
			}
			else{
				enemies.remove(c);
				
				
			}
			}
	}
}
