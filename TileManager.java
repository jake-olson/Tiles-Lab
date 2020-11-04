import java.util.*;
import java.awt.*;

public class TileManager {

    private ArrayList<Tile> tileList;

    // Constructor to create new TileManager Object. tileList is initialized.
    public TileManager() {
        tileList = new ArrayList<Tile>();
    }

    // Takes in a Tile object and adds it to the tileList.
    public void addTile(Tile rect) {
        tileList.add(rect);
    }

    // Takes in a Graphics parameter and draws all the tiles in tileList.
    public void drawAll(Graphics g) {
        for (Tile tile : tileList) {
            tile.draw(g);
        }
    }

    // Shifts the topmost tile which was left-clicked on to the end of the tileList.
    // Takes in 2 ints to represent the coordinates of the click, which are used
    // to determine which tile to move.
    public void raise(int x, int y) {
        int index = identifyTileHelper(x, y);
        if (index == -1) {
            index = tileList.size() - 1;
        }
        Tile tempTile = tileList.get(index);
        tileList.remove(index);
        tileList.add(tempTile);
    }

    // Shifts the topmost tile which was shift-left-clicked on to
    // the beginning of the tileList. Takes in 2 ints which represent the 
    // coordinates of the click, which are used to determine which tile to move.
    public void lower(int x, int y) {
        int index = identifyTileHelper(x, y);
        if (index == -1) {
            index = 0;
        }
        tileList.add(0, tileList.get(index));
        tileList.remove(index + 1);
    }

    // Removes the topmost tile which was right-clicked on from the tileList.
    // Takes in 2 ints which represent the coordinates of the click, which are 
    // used to determine which tile to remove.
    public void delete(int x, int y) {
        int index = identifyTileHelper(x, y);
        if (index != -1) {
            tileList.remove(index);
        }
    }

    // Removes all tiles which were shift-right-clicked on from the tileList.
    // Takes in 2 ints representing the coordinates of the click, which are used
    // to determine which tiles to remove.
    public void deleteAll(int x, int y) {
        for (int i = tileList.size() - 1; i >= 0; i --) {
            if(tileList.get(i).getX() + tileList.get(i).getWidth() >= x && 
               tileList.get(i).getX() <= x && tileList.get(i).getY() + 
               tileList.get(i).getHeight() >= y && tileList.get(i).getY() <= y) {
                tileList.remove(i);
            }
        }
    }

    // Shuffles the order of the tiles in tileList and assigns each tile 
    // random new x/y coordinate values. Takes in 2 ints, the dimensions
    // of the window, to ensure each tile stays fully in the window.
    public void shuffle(int width, int height) {
        Collections.shuffle(tileList);
        for (int i = 0; i < tileList.size(); i ++) {
            Random rand = new Random();
            int x = rand.nextInt(width - tileList.get(i).getWidth());
            int y = rand.nextInt(height - tileList.get(i).getHeight());
            tileList.get(i).setX(x);
            tileList.get(i).setY(y);
        }
    }

    // Determines which tile needs to be modified, based on the x and y
    // coordinates that are passed in. Returns the index of that tile.
    private int identifyTileHelper(int x, int y) {
        int index = -1;
        for (Tile tile : tileList) {
            if(tile.getX() + tile.getWidth() >= x && tile.getX() <= x && 
               tile.getY() + tile.getHeight() >= y && tile.getY() <= y) {
                index = tileList.indexOf(tile);
            }
        }
        return index;
    }
}
