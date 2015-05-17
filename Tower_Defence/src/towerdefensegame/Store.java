package towerdefensegame;
import java.util.*;

import towerdefensegame.objects.Tower;

public class Store {
	private ArrayList<Tower> towers;
	
	public Store(){
		towers = new ArrayList<>();
	}
	
	/**
	 * Adds a tower to the store, so it can be bought by the player.
	 * @param tower the tower to be added
	 */
	private void addToStore(Tower tower){
		towers.add(tower);
	}
	
	/**
	 * The player buys a tower from the store
	 * @param tower the tower to be bought
	 */
	private void buy(Tower tower){
		if(towers.contains(tower)) {
			// TODO
			//player.pay(tower.getCost());
		}
	}
}
