package bennett.fallingsand;

import java.util.Random;
import java.util.Scanner;

public class Sand {
    private final Random random;
    private int[][] field;

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
            field[y][x] = 1;
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

    /**
     * Moves the sand from x1, y1, to x2, y2
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return true if the move was successful, otherwise false
     */
    public boolean move(int x1, int y1, int x2, int y2) {
        if (inBounds(x2, y2) && isSand(x1, y1) && !isSand(x2, y2)) {
            field[y1][x1] = 0;
            field[y2][x2] = 1;
            return true;
        }
        return false;
    }

    public boolean inBounds(int x, int y) {
        return x >= 0 && x < field[y].length;
    }

    public int getWidth() {
        return field[0].length;
    }

    public int getHeight() {
        return field.length;
    }

    public void fall() {
        // moves all sand down one square
        for (int y = field.length - 2; y >= 0; y--) {
            for (int x = 0; x < field[y].length; x++) {
                if (isSand(y, x)) {
                    moveSandDown(x, y);
                }
            }
        }
    }

    private void moveSandDown(int x, int y) {
        if (!isSand(x, y + 1)) {
            move(x, y, x, y + 1);
            return;
        }

        boolean rightFirst = random.nextBoolean();
        int direction = rightFirst ? +1 : -1;

        if (move(x, y, x + direction, y + 1)) {
            return;
        }
        move(x, y, x - direction, y + 1);
    }

    private boolean isSand(int x, int y) {
        return field[y][x] == 1;
    }

    /*public void resize(int width, int height) {
        int[][] newField = new int[height][width];
        int minHeight = Math.min(height, field.length);
        int minWidth = Math.min(width, field[0].length);

        for (int y = 0; y < minHeight; y++) {
            System.arraycopy(field[y], 0, newField[y], 0, minWidth);
        }
        field = newField;
    }

    public void load(String sandString) {
        String[] lines = sandString.split("\n");
        int height = lines.length;
        int width = lines[0].length();
        field = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (lines[y].charAt(x) == '1') {
                    field[y][x] = 1;
                } else {
                    field[y][x] = 0;
                }
            }
        }
    }

    public void put(int x, int y, int width, int height, double probability) {
        for (int i = y; i < y + height; i++) {
            for (int j = x; j < x + width; j++) {
                if (random.nextDouble() <= probability) {
                    field[i][j] = 1;
                }
            }
        }
    }*/
}