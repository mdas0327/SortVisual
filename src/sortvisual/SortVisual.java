/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortvisual;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author mohammeddas
 */
public class SortVisual {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args == null || args.length != 1) {
            System.out.println("Incorrect paramters. Usage:");
            System.out.println(" > java -jar <path>/SortVisual.jar <AlgoName>");
            System.out.println("Possible AlgoName values: QuickSort, MergeSort, SelectionSort, InsertionSort, BubbleSort.");
            System.out.println("\n\n");
            return;
        }

        String algo = args[0].toLowerCase();
        SortInterface sorterInput = null;
        if (algo.equals("bubblesort")) {
            sorterInput = new BubbleSort();
        } else if (algo.equals("selectionsort")) {
            sorterInput = new SelectionSort();
        } else if (algo.equals("insertionsort")) {
            sorterInput = new InsertionSort();
        } else if (algo.equals("mergesort")) {
            sorterInput = new MergeSort();
        } else if (algo.equals("quicksort")) {
            sorterInput = new QuickSort();
        } else {
            System.out.println("Algo name unknow.");
            return;
        }

        final SortInterface sorter = sorterInput;
        
        DataSet dataset = new DataSetFactory().CreateRandom(70, 30, 100);
        
        SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame("Charts");
            
            frame.setSize(1200, 800);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            
            final IntervalXYDataset ds = createDataset(dataset.data);
            
            final JFreeChart chart = ChartFactory.createXYBarChart("Chart",
                    "Index", false, "Value", ds, PlotOrientation.HORIZONTAL, true, true, false);
            
            final ChartPanel cp = new ChartPanel(chart);
            
            frame.getContentPane().add(cp);
            
            chart.fireChartChanged();
            frame.repaint();
            
            
            
            SwingUtilities.invokeLater(() -> {
                try {
                Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SortVisual.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                sorter.Sort(dataset);
                chart.getXYPlot().setDataset( createDataset(dataset.data) );
                chart.getPlot().datasetChanged(new DatasetChangeEvent(chart.getPlot(), null));


                chart.fireChartChanged();
                frame.repaint();
            });
        });
    }

    static private IntervalXYDataset createDataset(int[] values) {
        final XYSeries series = new XYSeries("Random Data");
        for (int i = 0; i < values.length; ++i) {
            series.add(i, values[i]);
        }
        //series.
        final XYSeriesCollection dataset = new XYSeriesCollection(series);
        //dataset.setIntervalPositionFactor(1.0);

        dataset.setAutoWidth(true);
        dataset.setIntervalWidth(0.25);
        return dataset;
    }
}
