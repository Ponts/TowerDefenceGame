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
 * Här ska man välja vilken bana
 * @author Pontus
 *
 */
public class LevelSelect extends BasicGameState {
 private int ID;
 private boolean levelOne = false;
 private boolean levelTwo = false;
 private boolean levelThree = false;
 private boolean back = false;
 
	public LevelSelect(int state){
		ID = state; 
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(new Image("res\\LevelSelect.PNG"), 0, 0);
		
		if(levelOne) {
			g.drawImage(new Image("res\\LevelSelectLevelOne.PNG"), 0, 0);
		}
		
		if(levelTwo) {
			g.drawImage(new Image("res\\LevelSelectLevelTwo.PNG"), 0, 0);
		}
		
		if(levelThree) {
			g.drawImage(new Image("res\\LevelSelectLevelThree.PNG"), 0, 0);
		}
		
		if(back) {
			g.drawImage(new Image("res\\LevelSelectLevelBack.PNG"), 0, 0);
		}
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		int xPos = Mouse.getX();
		int yPos = Mouse.getY();
		
		Input input = container.getInput();
		
		// Level One
		if((xPos<550 && xPos>250) && (yPos<524 && yPos>424)){
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				levelOne = true;
			} else {
				if(levelOne){
					// TODO
					sbg.enterState(2);
				}
				levelOne = false;
			}
			
		} else {
			levelOne = false;
		}
		
		//Level two
		if((xPos<550 && xPos>250) && (yPos<390 && yPos>290)){
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				levelTwo = true;
			} else {
				if(levelTwo){
					// TODO
					sbg.enterState(3);
				}
				levelTwo = false;
			}
		} else {
			levelTwo = false;
		}
		
		//Level three
		if((xPos<550 && xPos>250) && (yPos<260 && yPos>160)){
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				levelThree = true;
			} else {
				if(levelThree){
					// TODO
					//sbg.enterState(4)
				}
				levelThree = false;
			}
		} else {
			levelThree = false;
		}
		
		//back
		if((xPos<550 && xPos>250) && (yPos<133 && yPos>33)){
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				back = true;
			} else {
				if(back) {
					sbg.enterState(0);
				}
				back = false;
			}
		} else {
			back = false;
		}
		container.setMouseGrabbed(false);
	}

	@Override
	public int getID() {

		return ID;
	}

}
