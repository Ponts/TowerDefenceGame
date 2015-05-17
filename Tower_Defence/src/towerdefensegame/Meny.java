package towerdefensegame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
/**
 * Det här är main menyn i spelet. 
 * 
 *
 */
public class Meny extends BasicGameState {
	 private int ID;
	 
	 private boolean playButton = false;
	 private boolean exitButton = false;
	 
	public Meny(int state){
		ID = state; 
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(new Image("res\\MainMenu.PNG"), 0, 0);
		
		if(playButton){
			g.drawImage(new Image("res\\MainMenuPlayPressed.PNG"), 0, 0);
			
		}
		
		if(exitButton){
			g.drawImage(new Image("res\\MainMenuExitPressed.PNG"), 0, 0);
		}
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		int xPos = Mouse.getX();
		int yPos = Mouse.getY();
		
		Input input = container.getInput();
		
		
		// Play
		if((xPos<550 && xPos>250) && (yPos<490 && yPos>390)){
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				playButton = true;
			}
			else {
				if(playButton) { //playButton has been pressed, enter levelSelect scene
					sbg.enterState(1);
				}
				playButton = false;
			}
			
		} else {
			playButton = false;
		}
		
		
		// Exit
		if((xPos<550 && xPos>250) && (yPos<280 && yPos>194)){
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				exitButton = true;
			}
			else {
				if(exitButton) {
					System.exit(0);
				}
				exitButton = false;
			}
		}
		else {
			exitButton = false;
		}
	}

	@Override
	public int getID() {

		return ID;
	}

}
