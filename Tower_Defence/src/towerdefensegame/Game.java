package towerdefensegame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame{

	private static final String name = "TowerDefense";
	private static int meny = 0;
	private static int levelSelect = 1;
	private static int levelOne = 2;
	private static int levelTwo = 3;
	/*private static int levelThree = 4;*/
	
	public Game(String name) {
		super(name);
		this.addState(new Meny(meny));
		this.addState(new LevelSelect(levelSelect));
		this.addState(new levelOne(levelOne));
		
		this.addState(new LevelTwo(levelTwo));
		/*this.addState(new levelThree(levelThree));*/
		
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.enterState(meny);
	}

	
	public static void main(String[] args){
		try{
			Game game = new Game(name);
			AppGameContainer app = new AppGameContainer(game, 800, 600, false);
			app.start();
			
			
		}catch(SlickException e){
			e.printStackTrace();
		}
		
	}
	
}
