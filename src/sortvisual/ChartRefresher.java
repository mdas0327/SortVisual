/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortvisual;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author mohammeddas
 */
public class ChartRefresher implements IRefresher {
    private final JFreeChart chart;
    
    ChartRefresher(JFreeChart chart_)
    {
        chart = chart_;
    }
    
    @Override
    public void Repaint(DataSet dataset) {
        dataset.RefreshXYDataSet();
        chart.getXYPlot().setDataset(dataset.graphDataset);

        chart.fireChartChanged();
    }

    @Override
    public void RepaintAsyncAndWait(DataSet dataset) {
        try {
            SwingUtilities.invokeAndWait(() -> {
                Repaint(dataset);
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(DataSet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
