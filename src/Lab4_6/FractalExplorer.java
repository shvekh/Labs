package Lab4_6;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    private int size;
    private JImageDisplay jimage;
    private FractalGenerator fgen = new Tricorn();
    private Rectangle2D.Double range = new Rectangle2D.Double();

    public FractalExplorer(int size) {
        this.size = size;
        fgen.getInitialRange(range);


    }

    public void createAndShowGUI() {
        JFrame jfrm = new JFrame("Fractal");
        JButton jbt = new JButton("Reset");
        JButton jbt1 = new JButton("Zoom");
        jfrm.add(jbt, BorderLayout.SOUTH);
        jfrm.add(jbt1, BorderLayout.NORTH);
        jimage = new JImageDisplay(600,600);
        jfrm.add(jimage, BorderLayout.CENTER);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();
        jfrm.setVisible(true);
        jfrm.setResizable(false);
        jbt.addActionListener(new TestActionListener());
        jbt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                fgen.recenterAndZoomRange(range,0.1,0.1,0.9);
                drawFractal();
            }
        });
        TestMouseListener cellHandler = new TestMouseListener();
        jimage.addMouseListener(cellHandler);

    }

    private void drawFractal() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width,
                        size, x);
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height,
                        size, y);
                if (fgen.numIterations(xCoord, yCoord) == -1) {
                    jimage.drawPixel(x, y, 0);
                } else {
                    float hue = 0.7f + (float) fgen.numIterations(xCoord, yCoord) / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    jimage.drawPixel(x, y, rgbColor);
                }
            }
        }
        jimage.repaint();
    }

    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae){
            fgen.getInitialRange(range);
            drawFractal();
        }

    }
    public class TestMouseListener implements MouseListener {

        public void mouseClicked(MouseEvent mouseEvent) {
            double mouseX = mouseEvent.getX()-size/2;
            double mouseY = mouseEvent.getY()-size/2;

            fgen.recenterAndZoomRange(range ,((mouseX/266)-0.55),mouseY/266,0.9);
            drawFractal();
        }

        public void mousePressed(MouseEvent mouseEvent) { }
        public void mouseReleased(MouseEvent mouseEvent) { }
        public void mouseEntered(MouseEvent mouseEvent) { }
        public void mouseExited(MouseEvent mouseEvent) { }
    }

    public static void main(String[] args){
        FractalExplorer f = new FractalExplorer(600);
        f.createAndShowGUI();
        f.drawFractal();
    }
}