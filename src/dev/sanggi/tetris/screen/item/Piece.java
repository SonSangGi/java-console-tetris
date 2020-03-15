package dev.sanggi.tetris.screen.item;

import dev.sanggi.tetris.screen.ScreenConfig;
import dev.sanggi.tetris.screen.ScreenItem;
import dev.sanggi.tetris.screen.ScreenPrinter;

import java.util.Random;

/**
 * @@author SonSangGi
 * @created 2020/03/15
 */
public class Piece extends ScreenItem {
    private int[] data;
    private int[] position;
    private Random random = new Random();

    public Piece(ScreenPrinter printer, ScreenConfig config) {
        super(printer, config);
        data = pieceData[random.nextInt(pieceData.length)];
        position = new int[]{0, 0, random.nextInt(data.length)};
    }

    @Override
    public void draw(boolean visible) {
        for (int[] cell : getCells(null)) {
            int x = cell[0];
            int y = cell[1];

            printer.print(config.X + x * 2, config.Y + y, visible ? "[]" : " .");
        }
    }

    private static int[][] pieceData = {
            {0x1256}, // square
            {0x159d, 0x4567}, // line
            {0x4512, 0x0459}, // s
            {0x0156, 0x1548}, // z
            {0x159a, 0x8456, 0x0159, 0x2654}, // l
            {0x1598, 0x0456, 0x2159, 0xa654}, // inverted l
            {0x1456, 0x1596, 0x4569, 0x4159}  // t
    };

    public int[][] getCells(int[] newPosition) {
        int x = position[0];
        int y = position[1];
        int z = position[2];
        if (newPosition != null) {
            x = newPosition[0];
            y = newPosition[1];
            z = newPosition[2];
        }
        int currentData = data[z];
        int result[][] = {{0, 0}, {0, 0}, {0, 0}, {0, 0}};
        for (int i = 0; i < 4; i++) {
            result[i][0] = x + ((currentData >> 4 * i) & 3);
            result[i][1] = y + ((currentData >> 4 * i + 2) & 3);
        }
        return result;
    }

    public void setPosition(int[] p) {
        position = new int[]{p[0], p[1], p[2] < 0 ? position[2] : p[2]};
    }

    public int[] newPosition(int dx, int dy, int dz) {
        int x = position[0];
        int y = position[1];
        int z = position[2];
        return new int[]{x + dx, y + dy, (z + dz) % data.length};
    }

    public void setConfig(ScreenConfig config) {
        this.config = config;
    }
}
