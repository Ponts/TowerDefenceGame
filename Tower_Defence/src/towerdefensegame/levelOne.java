package towerdefensegame;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import towerdefensegame.objects.Tower;

public class levelOne extends BasicGameState {
	private int ID;
	private Image theMap = null;
	private ArrayList<Tower> towers;
	// private ArrayList<ArrayList<Circle>> waves;
	private ArrayList<Circle> waves;

	private float circleX;
	private float circleY;
	private boolean roundStarted;
	private int baseHealth;

	public levelOne(int levelOne) {
		this.ID = levelOne;
	}

	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		theMap = new Image("res//MAP1.png");

		towers = new ArrayList<Tower>();

		waves = new ArrayList<Circle>();
		waves.add(new Circle(40, 0, 5));
		waves.add(new Circle(40, -30, 5));
		waves.add(new Circle(40, -60, 5));
		baseHealth = 3;
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		int xPos = Mouse.getX();
		int yPos = Mouse.getY();

		Input input = container.getInput();
		boolean rightMousePressed = input
				.isMousePressed(input.MOUSE_RIGHT_BUTTON);
		boolean leftMousePressed = input
				.isMousePressed(input.MOUSE_LEFT_BUTTON);
		if (input.isKeyPressed(input.KEY_ENTER)) {
			roundStarted = true;
		}
		if (input.isKeyPressed(input.KEY_ESCAPE)) {
			sbg.enterState(getID() - 1);
		}

		if (leftMousePressed) {
			towers.add(new Tower("basic", xPos, yPos));
		}

		if (roundStarted) {
			Circle c;
			for (int i = waves.size() - 1; i >= 0; i--) {

				c = waves.get(i);
				circleX = c.getCenterX();
				circleY = c.getCenterY();
				if (circleY < 45) {
					c.setCenterY(circleY + delta / 6f);
				} else if (circleX < 356 && circleY >= 45 && circleY < 47) {
					c.setCenterX(circleX + delta / 6f);
				} else if (circleX >= 356 && circleY < 118) {
					c.setCenterY(circleY + delta / 6f);
				} else if (circleX > 44 && circleY >= 118 && circleY < 121) {
					c.setCenterX(circleX - delta / 6f);
				} else if (circleX <= 44 && circleY <= 191) {
					c.setCenterY(circleY + delta / 6f);
				} else if (circleX < 355 && circleY >= 191 && circleY < 200) {
					c.setCenterX(circleX + delta / 6f);
				} else if (circleX >= 355 && circleY < 400) {
					c.setCenterY(circleY + delta / 6f);
				} else {
					waves.remove(c);
					baseHealth -= 1;

					if (baseHealth <= 0) {
						System.out.println("Game Over!");

						sbg.enterState(0);
					}
				}
			}

		}
	}

	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(theMap, 0, 0);
		for (Tower t : towers) {
			g.drawImage(t.getSprite(), t.getX(),
					container.getHeight() - t.getY());
		}

		g.setColor(Color.black);
		g.drawString("remaining life is: " + baseHealth, 5, 5);

		g.setColor(Color.blue);
		for (Circle c : waves) {
			g.fill(c);
			if (c.getCenterY() >= 400) {
				g.destroy();
			}
		}

	}

	@Override
	public int getID() {
		return this.ID;
	}
}