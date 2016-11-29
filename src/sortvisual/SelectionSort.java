package sortvisual;

import java.awt.Paint;
import org.jfree.chart.renderer.xy.XYBarRenderer;

public class SelectionSort implements SortInterface {

    public class Renderer extends XYBarRenderer {

        private final Paint minIndex_color;
        private final Paint i_color;
        private final Paint j_color;
        private final Paint default_color;

        private int minIndex = -1;
        private int i = -1;
        private int j = -1;

        Renderer(Paint defaultColor, Paint minIndexColor, Paint iColor, Paint jColor) {
            default_color = defaultColor;
            minIndex_color = minIndexColor;
            i_color = iColor;
            j_color = jColor;
        }

        public void SetCurrent(int minIndex_, int i_, int j_) {
            minIndex = minIndex_;
            i = i_;
            j = j_;
        }
        

        public Paint getItemPaint(final int row, final int column) {
            if (column == minIndex) {
                return minIndex_color;
            } else if (column == i) {
                return i_color;
            }else if (column == j) {
                return j_color;
            } else {
                return default_color;
            }
        }
    }
    private final IRefresher refresher;
    private Renderer renderer;
    SelectionSort(IRefresher refresher_) {
        refresher = refresher_;
    }
    
    public void SetRenderer(Renderer r) {
        renderer = r;
    }

    public void Sort(DataSet dataset) {
        int[] data = dataset.data;

        for (int outer = 0; outer < data.length - 1; ++outer) {
            // Assuming min at first element initially.
            int minIndex = outer;

            int inner;
            for (inner = outer + 1; inner < data.length; ++inner) {

                renderer.SetCurrent(minIndex, outer, inner);
                refresher.RepaintAsyncAndWait(dataset);
                if (data[inner] < data[minIndex]) {
                    minIndex = inner; // new min found.
                }
            }

            if (minIndex != outer) {
                int temp = data[minIndex];
                data[minIndex] = data[outer];
                data[outer] = temp;
                
                renderer.SetCurrent(outer, minIndex, inner);
                refresher.RepaintAsyncAndWait(dataset);
            }
        }
    }

    @Override
    public String GetName() {
        return "SelectionSort";
    }

}
