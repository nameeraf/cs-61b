package byow.lab12;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    public static final int HEIGHT = 30, WIDTH = 28;

    // Bottom right: 3n - 2 right, n down
    // Bottom left: 2n - 1 left, n down
    // Bottom: 2n down

    /**
     * Calculate the width of the hexagon given its current relative row and its length.
     */
    public static int calculateWidth(int rowN, int size) {
        int width;
        if (rowN == 0 || rowN == size*2 - 1) {
            width = size;
        }
        else if (rowN < size) {
            width = rowN*2 + size;
        }
        else {
            width = 2* (2*size - 1 - rowN) + size;
        }
        return width;
    }

    /**
     * Draw a row starting at x,y of a certain length.
     */
    public static void drawRow(int startX, int startY, int length, TETile[][] world, TETile tileType) {
        for (int i = startX; i < startX + length; i++) {
            world[i][startY] = tileType;
        }
    }

    /**
     * Draw the entire hexagon.
     */
    public static void drawHexagon(int upperLX, int upperLY, int length, TETile tileType, TERenderer ter, TETile[][] world) {

        for (int i = 0, currX = upperLX, currY = upperLY; i <= 2 * length - 1; i++, currY--) {

            int rowL = calculateWidth(i, length);
            drawRow(currX, currY, rowL, world, tileType);

            if (i < length - 1) {
                currX--;
            } else if (i > length - 1) {
                currX++;
            }
        }

        ter.renderFrame(world);
    }

    /**
     * Create a column with specified hexagonal regions.
     */
    public static void createCol(int numHex, int startX, int startY, TETile[] configs, int size, TERenderer ter, TETile[][] world) {

        for (int i = 0; i < numHex; i++) {
            drawHexagon(startX, startY - 2 * size * i, size, configs[i], ter, world);
        }

    }

    /**
     * Draws the plane.
     */
    public static void initPlane(TERenderer ter, TETile[][] world) {
        ter.initialize(WIDTH, HEIGHT);

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args) {

        TERenderer ter = new TERenderer();
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        initPlane(ter, world);

        TETile[][] colConfigs = new TETile[][] {{Tileset.MOUNTAIN, Tileset.GRASS, Tileset.GRASS},
                {Tileset.GRASS, Tileset.MOUNTAIN, Tileset.MOUNTAIN, Tileset.FLOWER},
                {Tileset.TREE, Tileset.MOUNTAIN, Tileset.MOUNTAIN, Tileset.MOUNTAIN, Tileset.MOUNTAIN},
                {Tileset.FLOWER, Tileset.SAND, Tileset.TREE, Tileset.MOUNTAIN},
                {Tileset.FLOWER, Tileset.TREE, Tileset.SAND}};

        createCol(3, 2, 23, colConfigs[0],3, ter, world);
        createCol(4, 7, 26, colConfigs[1],3, ter, world);
        createCol(5, 12, 29, colConfigs[2],3, ter, world);
        createCol(4, 17, 26, colConfigs[3], 3, ter, world);
        createCol(3, 22, 23,colConfigs[4], 3, ter, world);
    }
}