package ch.psi.pshell.plot;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import org.junit.Test;

/**
 *
 */
public class SurfacePlotJzy3dTest {
 
    final static double startX = 10;
    final static double stepSizeX = 1;
    final static int stepsX = 100;
    final static double startY = 300;
    final static double stepSizeY = 2;
    final static int stepsY = 200;
    final static MatrixPlotSeries data = new MatrixPlotSeries("", startX, startX + stepsX * stepSizeX, stepsX + 1, startY, startY + stepsY * stepSizeY, stepsY + 1);
    final static Gauss2D generator = new Gauss2D(50, 500, 40, 800, 2);
   
    public SurfacePlotJzy3dTest() {
    }

    @Test
    public void testSomeMethod() throws InterruptedException {
        final SurfacePlotJzy3dExtr plot = new SurfacePlotJzy3dExtr();
        plot.setTitle("Matrix Plot Test");        

        double[][] d= new double[201][101];
        int indexX=0,indexY=0;
        for (double y = startY;  y <= startY + stepsY * stepSizeY; y += stepSizeY, indexY++) {
            indexX=0;
            for (double x = startX;  x <= startX + stepsX * stepSizeX; x += stepSizeX, indexX++) {
                d[indexY][indexX]= generator.generate(x, y) + 5.0;
            }
        }
                
        plot.addSeries(data);        
        data.setData(d);
        
        final JFrame frame = new JFrame("");
        frame.setContentPane((JPanel)plot);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Close application if frame is closed
        frame.setVisible(true);               
    }
    
}
