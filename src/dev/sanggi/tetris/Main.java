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

        TetrisController tetrisController = new TetrisController(new ScreenPrinter());
        Reader reader = System.console().reader();

        Object lock = new Object();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                synchronized (lock) {
                    tetrisController.down();
                    tetrisController.flush();
                }
            }
        }).start();

        char key[] = {0, 0, 0};
        while (true) {

            if (key[2] == 27 && key[1] == '[') {
                key[0] = (char)reader.read();
            } else {
                key[0] = Character.toLowerCase((char)reader.read());
            }

            synchronized (lock) {
                switch (key[0]) {
                    case 'q':
                        tetrisController.exit();
                        break;
                    case 'a':
                        tetrisController.left();
                        break;
                    case 'd':
                        tetrisController.right();
                        break;
                    case 's':
                        tetrisController.down();
                        break;
                    default:
                        break;
                }
                tetrisController.flush();
            }
        }

    }
}
