package Lab4;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

public class FractalExplorer {
    private int size;
    private JImageDisplay jimage;
    private FractalGenerator fgen;
    private JComboBox<Object> box;
    private JButton btnReset;
    private JButton btnSave;
    private Rectangle2D.Double range;

    public FractalExplorer(int size) {
        this.size = size;
        this.fgen = new Mandelbrot();
        this.range = new Rectangle2D.Double();
        fgen.getInitialRange(this.range);
        createAndShowGUI();
        drawFractal();


    }

    public void createAndShowGUI() {
        JFrame jfrm = new JFrame("Fractals");
        btnReset = new JButton("Reset");
        btnSave = new JButton("Save");
        jimage = new JImageDisplay(size, size);
        JLabel label = new JLabel("Fractal: ");
        box = new JComboBox<>();// сoздание выпадающего списка
        box.addItem(new Mandelbrot());
        JPanel panelBox = new JPanel();
        panelBox.add(label);
        panelBox.add(box);
        JPanel panelBtn = new JPanel();
        panelBtn.add(btnReset);
        panelBtn.add(btnSave);
        jfrm.add(jimage, BorderLayout.CENTER);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.add(panelBtn, BorderLayout.SOUTH);
        jfrm.add(panelBox, BorderLayout.NORTH);

        ActionHandler handler = new ActionHandler();
        btnReset.addActionListener(handler);
        btnSave.addActionListener(handler);
        box.addActionListener(handler);
        jimage.addMouseListener(new MouseHandler());
        jfrm.pack();
        jfrm.setVisible(true);
        jfrm.setResizable(false);

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
    public class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnReset) {
                fgen.getInitialRange(range);
                drawFractal();
            } else if (e.getSource() == btnSave) {
                JFileChooser chooser = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("PNG Images", "PNG");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        ImageIO.write(jimage.getBufferedImage(), "png", new File(chooser.getSelectedFile() + ".PNG"));
                    } catch (IOException ex) {
                        System.out.println("Failed to save image!");
                    }
                } else {
                    System.out.println("No file chosen!");
                }
            } else if (e.getSource() == box) {
                fgen = (FractalGenerator) box.getSelectedItem();
                fgen.getInitialRange(range);
                drawFractal();
            }
        }
    }


    public class MouseHandler implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            double mouseX = FractalGenerator.getCoord(range.x, range.x + range.width, size, e.getX());
            double mouseY = FractalGenerator.getCoord(range.y, range.y + range.width, size, e.getY());
            System.out.println(mouseX + " " + mouseY);
            if (e.getButton() == MouseEvent.BUTTON1) {
                fgen.recenterAndZoomRange(range, mouseX, mouseY, 0.5);
                drawFractal();
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                fgen.recenterAndZoomRange(range, mouseX, mouseY, 1.5);
                drawFractal();
            }
        }

        public void mousePressed(MouseEvent mouseEvent) { }
        public void mouseReleased(MouseEvent mouseEvent) { }
        public void mouseEntered(MouseEvent mouseEvent) { }
        public void mouseExited(MouseEvent mouseEvent) { }
    }

    public static void main(String[] args) {
        new FractalExplorer(600);
    }
}