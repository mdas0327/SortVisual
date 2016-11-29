/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortvisual;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;


public class SortVisual {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            if (args == null || args.length != 1) {
                System.out.println("Incorrect paramters. Usage:");
                System.out.println(" > java -jar <path>/SortVisual.jar <AlgoName>");
                System.out.println("Possible AlgoName values: QuickSort, MergeSort, SelectionSort, InsertionSort, BubbleSort.");
                System.out.println("\n\n");
                return;
            }
            
            DataSet dataset = new DataSetFactory().CreateRandom(50, 30, 100);
            if (args[0].toLowerCase().equals("mergesort")) {
                MergeSortDataSet mergeSortDataSet = new MergeSortDataSet();
                mergeSortDataSet.data = dataset.data;
                mergeSortDataSet.mergeSpace = dataset.data.clone();
                
                dataset = mergeSortDataSet; 
                
            }
            dataset.InitalizeXYDataSet();
              
   
            
            final JFrame frame = new JFrame("Charts");
            final JFreeChart chart = ChartFactory.createXYBarChart("Chart",
                    "Index", false, "Value", dataset.graphDataset, PlotOrientation.VERTICAL, true, true, false);
            
            ChartRefresher refresher = new ChartRefresher(chart);
            
            String algo = args[0].toLowerCase();
            SortInterface sorterInput = null;
            if (algo.equals("bubblesort")) {
                
                BubbleSort bubbleSort = new BubbleSort(refresher);
                BubbleSort.Renderer renderer;
                renderer = bubbleSort.new Renderer(Color.GRAY, Color.ORANGE, Color.RED);
                chart.getXYPlot().setRenderer(renderer);
                bubbleSort.SetRenderer(renderer);
                
                sorterInput = bubbleSort;
                
            } else if (algo.equals("selectionsort")) {
                SelectionSort selectionSort = new SelectionSort(refresher);
                SelectionSort.Renderer renderer;
                renderer = selectionSort.new Renderer(Color.GRAY, Color.GREEN, Color.ORANGE, Color.RED);
                chart.getXYPlot().setRenderer(renderer);
                selectionSort.SetRenderer(renderer);
                
                sorterInput = selectionSort;
            } else if (algo.equals("insertionsort")) {
                
                InsertionSort insertionSort = new InsertionSort(refresher);
                InsertionSort.Renderer renderer;
                renderer = insertionSort.new Renderer(Color.GRAY, Color.GREEN, Color.ORANGE, Color.RED);
                chart.getXYPlot().setRenderer(renderer);
                insertionSort.SetRenderer(renderer);
                
                sorterInput = insertionSort;
            } else if (algo.equals("mergesort")) {
                MergeSort mergeSort = new MergeSort(refresher);
                
                MergeSort.Renderer renderer;
                renderer = mergeSort.new Renderer(Color.GRAY, Color.GREEN, Color.ORANGE, Color.BLUE, Color.RED);
                chart.getXYPlot().setRenderer(renderer);
                mergeSort.SetRenderer(renderer);
                
                sorterInput = mergeSort;
            } else if (algo.equals("quicksort")) {
                QuickSort quickSort = new QuickSort(refresher);
                
                QuickSort.Renderer renderer;
                renderer = quickSort.new Renderer(Color.GRAY, Color.GREEN, Color.ORANGE, Color.BLUE, Color.RED);
                chart.getXYPlot().setRenderer(renderer);
                quickSort.SetRenderer(renderer);
                
                sorterInput = quickSort;
            } else {
                System.out.println("Algo name unknow.");
                return;
            }
            
            final SortInterface sorter = sorterInput;
            chart.setTitle(sorter.GetName());
            
            final ChartPanel cp = new ChartPanel(chart);
            
            frame.getContentPane().add(cp);
            dataset.SetChart(chart);
            
            SwingUtilities.invokeAndWait(() -> {
                
                frame.setSize(1200, 800);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                
                chart.fireChartChanged();
                frame.repaint();
                
            });
            sorter.Sort(dataset);
            /*
            SwingUtilities.invokeAndWait(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SortVisual.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                sorter.Sort(dataset);
                dataset.RefreshXYDataSet();
                chart.getXYPlot().setDataset( dataset.graphDataset );
                //chart.getPlot().datasetChanged(new DatasetChangeEvent(chart.getPlot(), null));

                chart.fireChartChanged();
                //frame.repaint();
            });
             */
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(SortVisual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
