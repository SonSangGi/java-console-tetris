package dev.sanggi.tetris;

import dev.sanggi.tetris.controller.TetrisController;
import dev.sanggi.tetris.screen.ScreenPrinter;

import java.io.Reader;

/**
 * @@author sanggi-son
 * @created 2020/03/14
 */
public class Main {

    public static void main(String[] args) throws Exception {

        if (System.console() == null) {
            System.out.println("콘솔 터미널에서 실행 해주세요. \n- IDE 터미널 X");
            System.exit(0);
        }

        // /bin/sh 실행 및 터미널 설정 변경 (입력내용 미표시)
        String[] cmd = {"/bin/sh", "-c", "stty raw -echo </dev/tty"};
        Runtime.getRuntime().exec(cmd).waitFor();
        ScreenPrinter screenPrinter = new ScreenPrinter();
        TetrisController tetrisController = new TetrisController(screenPrinter);
        Reader reader = System.console().reader();

        Object lock = new Object();

        new Thread(() -> {
            final int KB = 1024;
            int delay = 1000;

            while (true) {
                synchronized (lock) {
                    screenPrinter.print(0, 4, "Used memory: " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / KB) + "/kb");
                    tetrisController.down();
                    tetrisController.flush();
                }
                try {
                    Thread.sleep(delay -= 2);
                } catch (InterruptedException e) {
                }
            }
        }).start();

        char key;

        while (true) {

            key = (char) reader.read();

            screenPrinter.print(55, 2, String.valueOf(key));

            synchronized (lock) {
                switch (key) {
                    case 'q':
                        tetrisController.exit();
                        break;
                    case 'a':
                    case 'D':
                        tetrisController.left();
                        break;
                    case 'd':
                    case 'C':
                        tetrisController.right();
                        break;
                    case 's':
                    case 'B':
                        tetrisController.down();
                        break;
                    case 'w':
                    case 'A':
                        tetrisController.rotate();
                        break;
                    case ' ':
                        tetrisController.drop();
                        break;
                    default:
                        screenPrinter.print(55, 4, String.valueOf(key));
                        break;
                }
                tetrisController.flush();
            }
        }

    }
}
