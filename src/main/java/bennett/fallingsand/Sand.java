package bennett.fallingsand;

import java.util.Random;
import java.util.Scanner;

public class Sand {
    private final int[][] field;
    private final Random random;

    public Sand(int width, int height) {
        field = new int[height][width];
        this.random = new Random();
    }

    public Sand(int width, int height, Random random) {
        field = new int[height][width];
        this.random = random;
    }

    public static void main(String[] args) {
        int width = 50;
        int height = 10;
        int initialSand = 50;

        Sand sand = new Sand(width, height);
        sand.randomSand(initialSand);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(sand.toString());
            System.out.println("please press enter so sand can fall");
            scanner.nextLine();
            sand.fall();
        }
    }

    /**
     * Adds random sand to our field
     *
     * @param n the amount of sand to add.
     */

    public void randomSand(int n) {
        int width = field[0].length;
        int height = field.length;

        for (int i = 0; i < n; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            field[y][x] = 1; // Place sand at the randomly generated position
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                builder.append(field[y][x]);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * @return the value in field
     */
    public int get(int x, int y) {
        return field[y][x];
    }

    /**
     * Sets the value in field to be 1
     */
    public void put(int x, int y) {
        field[y][x] = 1;
    }

    public void fall() {
        // moves all sand down one square
        for (int y = field.length - 2; y >= 0; y--) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == 1) {
                    if (field[y + 1][x] == 0) {
                        // does the sand fall straight down?
                        field[y][x] = 0;
                        field[y + 1][x] = 1;
                        continue;
                    }

                    boolean rightFirst = random.nextBoolean();
                    int direction1 = rightFirst ? +1 : -1;
                    int direction2 = rightFirst ? -1 : +1;

                    if (field[y + 1][x + direction1] == 0) {
                        field[y][x] = 0;
                        field[y + 1][x + direction1] = 1;
                    } else if (field[y + 1][x + direction2] == 0) {
                        field[y][x] = 0;
                        field[y + 1][x + direction2] = 1;
                    }
                }
            }
        }
    }
}