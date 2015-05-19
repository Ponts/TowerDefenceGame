package towerdefensegame;

public class Player {
	private int health, money, kills;

	public Player() {
		health = 100;
		money = 500;
		kills = 0;
	}

	public void pay(int cost) {
		money = money - cost;
	}

	/**
	 * Player killed a enemy, increase no of kills.
	 */
	public void killed() {
		kills++;
	}

	public void takeDamage(int damage) {
		health = health - damage;
	}

	public int getMoney() {
		return money;
	}

	public void getMoney(int money) {
		this.money += money;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
