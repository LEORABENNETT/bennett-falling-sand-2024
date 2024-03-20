package bennett.fallingsand;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class SandTest {

    @Test
    public void string() {
        // given - set up paramaters
        Sand sand = new Sand(3, 3);

        // when - the one thing you are testing gets tested
        String actual = sand.toString();

        // then - check results
        assertEquals("000\n000\n000\n", actual);
    }

    @Test
    public void put() {
        // given
        Sand sand = new Sand(3, 3);

        // when
        sand.put(1, 0);

        // then
        assertEquals("010\n000\n000\n", sand.toString());
    }

    @Test
    public void fall() {
        // given
        Sand sand = new Sand(3, 3);
        sand.put(1, 0);

        // when
        sand.fall();

        // then
        assertEquals("000\n010\n000\n", sand.toString());
    }

    @Test
    public void fallOnGround() {
        // given
        Sand sand = new Sand(3, 3);
        sand.put(1, 2);

        // when
        sand.fall();

        // then
        assertEquals("000\n000\n010\n", sand.toString());
    }

    @Test
    public void fallToTheRight() {
        // given
        Sand sand = new Sand(3, 3);
        sand.put(1, 1);
        sand.put(1, 2);
        sand.put(0, 2); // left

        // when
        sand.fall();

        // then
        assertEquals("000\n000\n111\n", sand.toString());
    }


    @Test
    public void fallToTheLeft() {
        // given
        Sand sand = new Sand(3, 3);
        sand.put(1, 1);
        sand.put(1, 2);
        sand.put(2, 2); // right

        // when
        sand.fall();

        // then
        assertEquals("000\n000\n111\n", sand.toString());
    }

    @Test
    public void fallRandomDirectionRight() {
        // given
        Random random = mock();
        doReturn(true).when(random).nextBoolean();
        Sand sand = new Sand(3, 3, random);
        sand.put(1, 1);
        sand.put(1, 2);

        // when
        sand.fall();

        // then
        assertEquals("000\n000\n011\n", sand.toString());
    }

    @Test
    public void fallRandomDirectionLeft() {
        // given
        Random random = mock();
        doReturn(false).when(random).nextBoolean();
        Sand sand = new Sand(3, 3, random);
        sand.put(1, 1);
        sand.put(1, 2);

        // when
        sand.fall();

        // then
        assertEquals("000\n000\n110\n", sand.toString());
    }

    @Test
    public void fallSimultaneously() {
        // given
        Sand sand = new Sand(3, 3);
        sand.put(1, 0);
        sand.put(1, 1);

        // when
        sand.fall();

        // then
        assertEquals("000\n010\n010\n", sand.toString());
    }

    @Test
    public void fallLeftEdge() {
        // given
        Sand sand = new Sand(3, 3);
        sand.put(0, 1);
        sand.put(0, 2);
        sand.put(1, 2);

        // when
        sand.fall();

        // then
        assertEquals("000\n100\n110\n", sand.toString());
    }

    @Test
    public void fallRightEdge() {
        // given
        Sand sand = new Sand(3, 3);
        sand.put(2, 1);
        sand.put(1, 2);
        sand.put(2, 2);

        // when
        sand.fall();

        // then
        assertEquals("000\n001\n011\n", sand.toString());
    }


    /*@Test
    public void testResize() {
        Sand sand = new Sand(3, 3);
        sand.put(0, 0, 3, 3, 1);
        sand.resize(5, 5);
        assertEquals(5, sand.getHeight());
        assertEquals(5, sand.getWidth());
        assertEquals(1, sand.get(0, 0));
        assertEquals(1, sand.get(2, 2));
        assertEquals(0, sand.get(4, 4));
    }

    @Test
    public void testLoad() {
        Sand sand = new Sand(3, 3);
        sand.load("111\n000\n111\n");
        assertEquals(1, sand.get(0, 0));
        assertEquals(1, sand.get(2, 0));
        assertEquals(1, sand.get(0, 2));
        assertEquals(0, sand.get(1, 1));
    }

    @Test
    public void testPut() {
        int width = 5;
        int height = 5;
        double probability = 0.5;
        Random random = new Random(123);

        Sand sand = new Sand(width, height, random);
        sand.put(0, 0, width, height, probability);

        int sandCount = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (sand.get(x, y) == 1) {
                    sandCount++;
                }
            }
        }

        int expectedSandCount = (int) (width * height * probability);

        assertTrue(Math.abs(expectedSandCount - sandCount) <= 1, "Unexpected number of sand particles");
    }*/
}