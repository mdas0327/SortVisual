package sortvisual;

import java.awt.Paint;
import org.jfree.chart.renderer.xy.XYBarRenderer;

public class BubbleSort implements SortInterface {

    public class Renderer extends XYBarRenderer {

        private final Paint i_color;
        private final Paint j_color;
        private final Paint default_color;

        private int i = -1;
        private int j = -1;

        Renderer(Paint defaultColor, Paint iColor, Paint jColor) {
            default_color = defaultColor;
            i_color = iColor;
            j_color = jColor;
        }

        public void SetCurrent(int i_, int j_) {
            i = i_;
            j = j_;
        }

        public Paint getItemPaint(final int row, final int column) {
            if (column == i) {
                return i_color;
            } else if (column == j) {
                return j_color;
            } else {
                return default_color;
            }
        }
    }

    private final IRefresher refresher;
    private Renderer renderer;

    BubbleSort(IRefresher refresher_) {
        refresher = refresher_;
    }

    void SetRenderer(Renderer renderer_) {
        renderer = renderer_;
    }

    @Override
    public void Sort(DataSet dataset) {

        int[] data = dataset.data;
        for (int i = 0; i < data.length; ++i) {
            for (int j = i + 1; j < data.length; ++j) {
                renderer.SetCurrent(i, j);
                refresher.RepaintAsyncAndWait(dataset);

                if (data[i] > data[j]) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;

                    refresher.RepaintAsyncAndWait(dataset);
                }
            }
        }
    }

    @Override
    public String GetName() {
        return "BubbleSort";
    }

}
