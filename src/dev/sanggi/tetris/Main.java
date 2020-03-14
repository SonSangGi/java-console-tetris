package dev.sanggi.tetris;

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

        Reader reader = System.console().reader();

        int key;
        while (true) {

            key = Character.toLowerCase((char) reader.read());
            System.out.print(key);

            synchronized (new Object()) {
                switch (key) {
                    case 3:
                    case 'p':
                        quit();
                        break;
                    default:
                        break;
                }
            }
        }

    }

    static void quit() {
        try {
            String[] cmd = new String[]{"/bin/sh", "-c", "stty sane </dev/tty"};
            Runtime.getRuntime().exec(cmd).waitFor();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
