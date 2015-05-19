package towerdefensegame.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Bullet {
	private float x;
	private float y;
	private float goalX;
	private float goalY;
	private Image sprite;

	public Bullet(float x, float y, float goalX, float goalY, String name)
			throws SlickException {
		this.x = x;
		this.y = y;
		this.goalX = goalX;
		this.goalY = goalY;
		this.sprite = new Image("res//" + name + ".png");
	}

	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(sprite, x, y);
	}

	public void update(int delta) {
		if (x < goalX) {
			x += 2 * delta / 1f;
		} else {
			x -= 2 * delta / 1f;
		}
		if (y < goalY) {
			y += 2 * delta / 1f;
		} else {
			y -= 2 * delta / 1f;
		}
	}

	public boolean reached() {
		return Math.max(x, goalX) - Math.min(x, goalX) < 2
				&& Math.max(y, goalY) - Math.min(y, goalY) < 2;
	}

}
