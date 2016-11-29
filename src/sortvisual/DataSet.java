package sortvisual;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DataSet {

    public int[] data;
    public XYSeriesCollection graphDataset;
    private JFreeChart chart;
    
    public void SetChart(JFreeChart chart_) {
        chart = chart_;
    }
    
    public void InitalizeXYDataSet() {

        int[] values = data;

        final XYSeries series = new XYSeries("Data");
        for (int i = 0; i < values.length; ++i) {
            series.add(i, values[i]);
        }
        //series.
        final XYSeriesCollection xyDataset = new XYSeriesCollection(series);
        //dataset.setIntervalPositionFactor(1.0);

        xyDataset.setAutoWidth(true);
        xyDataset.setIntervalWidth(0.25);

        graphDataset = xyDataset;
    }

    public void RefreshXYDataSet() {
        int[] values = data;
        graphDataset.getSeries(0).clear();
        for (int i = 0; i < values.length; ++i) {
            graphDataset.getSeries(0).add(i, values[i]);
        }
    }
}
