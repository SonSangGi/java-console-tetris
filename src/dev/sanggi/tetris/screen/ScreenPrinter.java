package dev.sanggi.tetris.screen;

import dev.sanggi.tetris.type.ColorType;

/**
 * @@author sanggi-son
 * @created 2020/03/14
 */
public class ScreenPrinter {
    private StringBuffer sb = new StringBuffer();

    public void print(String s) {
        sb.append(s);
    }

    public void print(int x, int y, String s) {
        sb.append("\u001B[" + y + ";" + x + "H" + s);
    }

    public void showCursor() {
        sb.append("\u001B[?25h");
    }

    public void hideCursor() {
        sb.append("\u001B[?25l");
    }

    public void setFg(ColorType c) {
            sb.append("\u001B[3" + c.value + "m");
    }

    public void setBg(ColorType c) {
            sb.append("\u001B[4" + c.value + "m");
    }

    public void resetColor() {
        sb.append("\u001B[0m");
    }

    public void clear() {
        sb.append("\u001B[2J");
    }

    public void flush() {
        System.out.print(sb.toString());
        sb.setLength(0);
    }

}
