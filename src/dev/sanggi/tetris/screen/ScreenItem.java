package dev.sanggi.tetris.screen;

/**
 * @@author sanggi-son
 * @created 2020/03/14
 */
public abstract class ScreenItem {
    protected ScreenPrinter printer;
    protected ScreenConfig config;

    private boolean visible = true;

    public ScreenItem(ScreenPrinter printer, final ScreenConfig config) {
        this.printer = printer;
        this.config = config;
    }

    public abstract void draw(boolean visible);

    public void show() {
        if(visible) draw(true);
    }

    public void hide() {
        if(visible) draw(false);
    }
}
