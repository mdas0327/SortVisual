package sortvisual;

import java.awt.Paint;
import org.jfree.chart.renderer.xy.XYBarRenderer;

public class QuickSort implements SortInterface {

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
    
    QuickSort(IRefresher refresher_) {
        refresher = refresher_;
    }
    
    public void SetRenderer(Renderer renderer_) {
        renderer = renderer_;
    }

    @Override
    public void Sort(DataSet dataset) {

        SortRange(dataset, 0, dataset.data.length - 1);

    }

    @Override
    public String GetName() {
        return "QuickSort";
    }

    // rangeStart and rangeEnd are inclusive.
    private void SortRange(DataSet dataset, int rangeStart, int rangeEnd) {
        if (rangeEnd <= rangeStart) {
            return;
        }

        int rangeMid = partition(dataset, rangeStart, rangeEnd);
        renderer.SetCurrentRange(rangeStart, rangeEnd, rangeMid);
        refresher.RepaintAsyncAndWait(dataset);
        
        SortRange(dataset, rangeStart, rangeMid - 1);
        renderer.SetCurrentRange(rangeStart, rangeEnd, rangeMid);
        refresher.RepaintAsyncAndWait(dataset);
        
        SortRange(dataset, rangeMid + 1, rangeEnd);
        renderer.SetCurrentRange(rangeStart, rangeEnd, rangeMid);
        refresher.RepaintAsyncAndWait(dataset);
    }

    private int partition(DataSet dataset, int rangeStart, int rangeEnd) {
        renderer.SetCurrentRange(-1, -1, -1);
        renderer.SetTraversals(rangeStart, rangeEnd);
        refresher.RepaintAsyncAndWait(dataset);
        
        int[] data = dataset.data;

        int left = rangeStart;
        int right = rangeEnd + 1;
        int v = data[rangeStart];
        while (left < right) {

            // get smaller element than pivot from left.
            while (data[++left] < v) {
                if (left == rangeEnd) {
                    break;
                }
                renderer.SetTraversals(left, right);
                refresher.RepaintAsyncAndWait(dataset);
            }

            // get higher element than pivot from right.
            while (v < data[--right]) {
                if (right == rangeStart) {
                    break;
                }
                renderer.SetTraversals(left, right);
                refresher.RepaintAsyncAndWait(dataset);
            }

            if (left < right) {
                // swap at left and right.
                int temp = data[left];
                data[left] = data[right];
                data[right] = temp;
                
                refresher.RepaintAsyncAndWait(dataset);
            }
        }

        // swap first element with pivot position.
        int temp = data[rangeStart];
        data[rangeStart] = data[right];
        data[right] = temp;
        renderer.SetTraversals(-1, -1);
        refresher.RepaintAsyncAndWait(dataset);
        
        return right;
    }
}
