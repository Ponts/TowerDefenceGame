package towerdefensegame;

import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

class LayerBasedMap implements TileBasedMap {

    private TiledMap map;
    private int blockingLayerId;

    public LayerBasedMap(TiledMap map, int blockingLayerId) {
        this.map = map;
        this.blockingLayerId = blockingLayerId;
    }

    @Override
    public boolean blocked(PathFindingContext ctx, int x, int y) {
        return map.getTileId(x, y, blockingLayerId) != 0;
    }

    @Override
    public float getCost(PathFindingContext ctx, int x, int y) {
        return 1.0f;
    }

    @Override
    public int getHeightInTiles() {
        return map.getHeight();
    }

    @Override
    public int getWidthInTiles() {
        return map.getWidth();
    }

    @Override
    public void pathFinderVisited(int arg0, int arg1) {}

	
}
