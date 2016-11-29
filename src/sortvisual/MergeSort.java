package sortvisual;

import java.awt.Paint;
import org.jfree.chart.renderer.xy.XYBarRenderer;

public class MergeSort implements SortInterface {

    public class Renderer extends XYBarRenderer {

        private final Paint leftColor;
        private final Paint rightColor;
        private final Paint endsColor;
        private final Paint midColor;
        private final Paint default_color;

        private int start = -1;
        private int end = -1;
        private int mid = -1;
        private int left = -1;
        private int right = -1;

        Renderer(Paint defaultColor, Paint leftColor_, Paint rightColor_, Paint endsColor_, Paint midColor_) {
            default_color = defaultColor;
            leftColor = leftColor_;
            rightColor = rightColor_;
            endsColor = endsColor_;
            midColor = midColor_;
        }
        
        public void SetCurrentRange(int start_, int end_, int mid_) {
            start = start_;
            end = end_;
            mid = mid_;
        }

        public void SetTraversals(int left_, int right_) {
            left = left_;
            right = right_;
        }
        
        

        public Paint getItemPaint(final int row, final int column) {
            if (column == start) {
                return endsColor;
            } else if (column == end) {
                return endsColor;
            }else if (column == left) {
                return leftColor;
            } else if (column == right) {
                return rightColor;
            }else if (column == mid) {
                return midColor;
            } else {
                return default_color;
            }
        }
    }
    
    private final IRefresher refresher;
    private Renderer renderer;
    
    MergeSort(IRefresher refresher_) {
        refresher = refresher_;
    }
    
    public void SetRenderer(Renderer renderer_) {
        renderer = renderer_;
    }

    @Override
    public void Sort(DataSet dataset) {

        MergeSortDataSet mergeSortDataSet = (MergeSortDataSet)dataset;
        if (mergeSortDataSet == null) {
            throw new NullPointerException();
        }
        SortRange(mergeSortDataSet, 0, dataset.data.length - 1);
    }

    @Override
    public String GetName() {
        return "MergeSort";
    }

    private void SortRange(MergeSortDataSet dataset, int left, int right) {

        if (left < right) {
            int middle = (left + right) / 2;

            SortRange(dataset, left, middle);
            renderer.SetCurrentRange(left, -1, middle);
            refresher.RepaintAsyncAndWait(dataset);
        
            SortRange(dataset, middle + 1, right);
            renderer.SetCurrentRange(-1, right, middle+1);
            refresher.RepaintAsyncAndWait(dataset);
            
            mergeSortedSubArrays(dataset, left, middle, right);
            renderer.SetCurrentRange(-1, -1, -1);
            refresher.RepaintAsyncAndWait(dataset);
            
        }
    }

    private void mergeSortedSubArrays(MergeSortDataSet dataset, int left, int middle, int right) {
        int[] data = dataset.data;
        int[] mergeSpace = dataset.mergeSpace;
        
        int rangeOneIter = left;
        int rangeOneEnd = middle;
        int rangeTwoIter = middle + 1;
        int rangeTwoEnd = right;
        int j = left;
        renderer.SetCurrentRange(left, right, middle);
        renderer.SetTraversals(rangeOneIter, rangeTwoIter);
        refresher.RepaintAsyncAndWait(dataset);

        while (rangeOneIter <= middle && rangeTwoIter <= rangeTwoEnd) {
            if (data[rangeOneIter] <= data[rangeTwoIter]) {
                mergeSpace[j++] = data[rangeOneIter++];
                renderer.SetTraversals(rangeOneIter, rangeTwoIter);
                refresher.RepaintAsyncAndWait(dataset);
            } else {
                mergeSpace[j++] = data[rangeTwoIter++];
                renderer.SetTraversals(rangeOneIter, rangeTwoIter);
                refresher.RepaintAsyncAndWait(dataset);
            }
        }

        while (rangeOneIter <= rangeOneEnd) {
            mergeSpace[j++] = data[rangeOneIter++];
            
            renderer.SetTraversals(rangeOneIter, rangeTwoIter);
            refresher.RepaintAsyncAndWait(dataset);
        }

        while (rangeTwoIter <= rangeTwoEnd) {
            mergeSpace[j++] = data[rangeTwoIter++];

            renderer.SetTraversals(rangeOneIter, rangeTwoIter);
            refresher.RepaintAsyncAndWait(dataset);
        }

        for (int k = left; k <= right; k++) {
            data[k] = mergeSpace[k];
        }
    }

}
