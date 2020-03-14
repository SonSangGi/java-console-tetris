package dev.sanggi.tetris.screen.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @@author sanggi-son
 * @created 2020/03/14
 */
public class Test {
    public static void main(String[] args) {
        List<List<Integer>> cells;

        cells = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < 10; j++)
                row.add(0);
            cells.add(row);
        }

        cells.forEach(cell -> {
            cell.forEach(System.out::print);
            System.out.println();
        });
    }
}
