/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortvisual;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author mohammeddas
 */
public class MergeSortDataSet extends DataSet {
    // only used in merge-sort
    public int[] mergeSpace;

    @Override
    public void RefreshXYDataSet() {
        int[] values = mergeSpace;
        graphDataset.getSeries(0).clear();
        for (int i = 0; i < values.length; ++i) {
            graphDataset.getSeries(0).add(i, values[i]);
        }
        
        super.RefreshXYDataSet();
    }
}
