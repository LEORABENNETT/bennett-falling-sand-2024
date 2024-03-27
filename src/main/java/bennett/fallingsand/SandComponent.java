package bennett.fallingsand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SandComponent extends JComponent {

    private final Sand sand;

    public SandComponent(Sand sand) {
        this.sand = sand;

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sand.put(e.getX(), e.getY(), 10, 10, .3);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                sand.put(e.getX(), e.getY(), 10, 10, .3);

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        sand.fall();

        int cellWidth = getWidth() / sand.getWidth();
        int cellHeight = getHeight() / sand.getHeight();


        g.setColor(Color.BLUE);
        for (int y = 0; y < sand.getHeight(); y++) {
            for (int x = 0; x < sand.getWidth(); x++) {
                if (sand.get(x, y) == 1) {
                    g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                }
            }
        }

        repaint();

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
