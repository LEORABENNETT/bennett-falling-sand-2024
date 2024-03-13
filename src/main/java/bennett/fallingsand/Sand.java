package bennett.fallingsand;

import java.util.Random;

public class Sand {
    private final Random random;
    private int[][] field = new int[3][3];

    public Sand(int width, int height) {
        field = new int[height][width];
        this.random = new Random();
    }

    public Sand(int width, int height, Random random) {
        field = new int[height][width];
        this.random = random;
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

                if (field[y][x] == 1 && field[y + 1][x] == 0) {
                    field[y][x] = 0;
                    field[y + 1][x] = 1;

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
}