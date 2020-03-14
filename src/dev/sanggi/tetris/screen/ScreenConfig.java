package dev.sanggi.tetris.screen;

/**
 * @@author SonSangGi
 * @created 2020/03/14
 */
public class ScreenConfig {
    private int width;
    private int height;
    private int x;
    private int y;

    public ScreenConfig(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
