package bennett.fallingsand;

import javax.swing.*;
import java.awt.*;

public class SandComponent extends JComponent {

    private final Sand sand;

    public SandComponent(Sand sand) {
        this.sand = sand;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellWidth = getWidth() / sand.getWidth();
        int cellHeight = getHeight() / sand.getHeight();

        // iterate through each cell in the sand grid
        for (int y = 0; y < sand.getHeight(); y++) {
            for (int x = 0; x < sand.getWidth(); x++) {
                // if there's sand at this cell, draw it
                if (sand.get(x, y) == 1) {
                    g.setColor(Color.BLUE); // set color to represent sand
                    g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                }
            }
        }
    }
}
