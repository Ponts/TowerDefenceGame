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
	private int roundNo;
	private ArrayList<Tower> towers;
	private ArrayList<Enemy> enemies;
	private Player player;
	private PathFinder ph;
	private Path path;
	boolean startRound = false;
	private ArrayList<Bullet> bullets;
	private LayerBasedMap blockedMap;
	
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
		bullets = new ArrayList<>();
		blockedMap = new LayerBasedMap(map, 2, 32);
		newRound();
		
		// TODO pathing stuff
		ph = new AStarPathFinder(blockedMap, 1000, false);
		path = ph.findPath(null, 5, 0, 20, 16);
		
		
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
		
		if(startRound){
		for(Enemy e:enemies){
		e.render(container, sbg, g);
		
			}
		}
		
		for(Bullet b: bullets){
			b.render(container, sbg, g);
		}
		
		
		
		
	}
	

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = container.getInput();
		int xPos = Mouse.getX();
		int yPos = Mouse.getY();
		if(input.isKeyPressed(input.KEY_ENTER)){
			newRound();
			startRound = true;
			
		}
		
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
		
		
		towerAct(container);
		
		// Start the round
		if(startRound){
			Enemy c;
			for(int i = enemies.size() -1; i >= 0; i-- ){
				c=enemies.get(i);
				c.update( delta,path);
				if(c.getX() <= 20*32 && c.getY()>=16*31){
					enemies.remove(c);
					player.takeDamage(c.getDamage());
					if(player.getHealth()<=0){
						sbg.enterState(0);
						System.out.println("Game Over");
					}
				}
				
			}
		
		}
		
		Bullet b;
		for(int i = bullets.size()-1;i>= 0;i--){
			b = bullets.get(i);
			b.update(delta);
			if(b.reached()){
				bullets.remove(b);
			}
		}
		//Make enemies move TODO
		//enemy.move(path);
		
		
	}

	@Override
	public int getID() {
		
		return ID;
	}

	private void towerAct(GameContainer container) throws SlickException{
		for(Tower t:towers){
			Enemy e;
			t.reloading();
			for(int i = enemies.size() -1; i >= 0; i-- ){
				e = enemies.get(i);
				if(t.shootReady(e, container)){
					float X = e.getX();
					float Y = e.getY();	
					bullets.add(new Bullet(t.getX(),container.getHeight() - t.getY(),X,Y));
					t.reload();
					e.takeDamage((t.getDamage()));
					if(!e.isAlive()){
						player.getMoney(e.getBounty());
						enemies.remove(e);
					}
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
			
			float nextX = path.getX(c.getI())*32;
			float nextY = path.getY(c.getI())*32;
			
			
			if(c.getX()>nextX){
				c.setX(c.getX()-delta/6f);
			}
			else{
				c.setX(c.getX()+delta/6f);
			}
			
			if(c.getY()>nextY){
				c.setY(c.getY()-delta/6f);
			} else{
				c.setY(c.getY()+delta/6f);
			}
			
			if(Math.max(c.getX(), nextX)-Math.min(c.getX(), nextX) < 2&& Math.max(c.getY(),nextY)-Math.min(c.getY(), nextY)<2){
				c.setI(c.getI()+1);
			}
			
			if(c.getX() <= 20*32 && c.getY()>=16*31){
				enemies.remove(c);
			
			}
			
		}
	}
	
	
	private void newRound() throws SlickException{
		for(int i = 0; i<roundNo;i++){
			enemies.add(new Enemy("cactiball", 5*32, 0-(i*64), 10));
		}
		roundNo++;
	}
	
}
