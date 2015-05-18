package towerdefensegame;

import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;
import org.newdawn.slick.tiled.TiledMap;

class LayerBasedMap implements TileBasedMap {
	//Define Variables
	private int tileSize;
    private TiledMap map;
    private int blockedTileId;
    private boolean[][] visited;
    private boolean[][] blocked;
    
    //Constructor
    public LayerBasedMap(TiledMap map, int blockedTileId, int tileSize) {
    	//Initialise Variables
        this.map = map;
        this.blockedTileId = blockedTileId;
        this.tileSize = tileSize;
        blocked = new boolean[map.getWidth()][map.getHeight()];
        visited = new boolean[map.getWidth()][map.getHeight()];
        
        /*Build 2d boolean array with tile block data
         * using tile blocked property set in Tiled Map 
         * editor
         */
        for (int x = 0; x < map.getWidth(); x++){
            for (int y = 0; y < map.getHeight(); y++){  	
           	 int tileID = map.getTileId(x, y, blockedTileId);
                String value = map.getTileProperty(tileID, "blocked", "false");
                if ("true".equals(value)){
               	 	blocked[x][y] = true;
                }
            }
        }
        
    }

    /*
     * Return true if blockedTileId at give coordinates
     * else return false
     */
    public boolean blocked(PathFindingContext ctx, int x, int y) {
        return map.getTileId(x, y, blockedTileId) != 0;
    }
    
    //Clear visited tile array
    public void clearVisited() {
		for (int x = 0;x < map.getWidth();x++) {
			for (int y = 0; y < map.getHeight();y++) {
				visited[x][y] = false;
			}
		}
	}
    

    //Return movement cost
    public float getCost(PathFindingContext ctx, int x, int y) {
        return 1.0f;
    }

    //Return map Height in tiles
    public int getHeightInTiles() {
        return map.getHeight();
    }

    //Return map width in tiles
    public int getWidthInTiles() {
    	return map.getWidth();
    }

    //Set if visited
    public void pathFinderVisited(int x, int y) {
    	visited[x][y] = true;
    }
    
    /*
     * Convert float coordinate to INT
     * Calculate x and y tile locations
     * using tile size divided by coordinate
     * return boolean value of blocked array
     */
    public boolean isBlocked(float x, float y){  
        try{
        	int xBlock = (int)x / tileSize;
            int yBlock = (int)y / tileSize;
            return blocked[xBlock][yBlock];
         }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            return blocked[0][0];
         }
    }
}

