package sortvisual;

import java.awt.Paint;
import org.jfree.chart.renderer.xy.XYBarRenderer;

public class InsertionSort implements SortInterface {

    public class Renderer extends XYBarRenderer {

        private final Paint outer_color;
        private final Paint inner_color;
        private final Paint default_color;
        private final Paint innerPrev_color;

        private int outer = -1;
        private int inner = -1;
        private int innerPrev = -1;

        Renderer(Paint defaultColor, Paint keyColor, Paint iColor, Paint jColor) {
            default_color = defaultColor;
            outer_color = iColor;
            inner_color = jColor;
            innerPrev_color = keyColor;
        }

        public void SetCurrent(int outer_, int inner_, int innerPrev_) {
            innerPrev = innerPrev_;
            outer = outer_;
            inner = inner_;
        }
        
        
        public Paint getItemPaint(final int row, final int column) {
            if (column == innerPrev) {
                return innerPrev_color;
            }
            else if (column == outer) {
                return outer_color;
            } else if (column == inner) {
                return inner_color;
            } else {
                return default_color;
            }
        }
    }

    
    private final IRefresher refresher;
    private Renderer renderer;
    
    InsertionSort(IRefresher refresher_) {
        refresher = refresher_;
    }
    
    void SetRenderer(Renderer renderer_) {
        renderer = renderer_;
    }

    @Override
    public void Sort(DataSet dataset) {
        int[] data = dataset.data;

        int outer, key;

        for (outer = 1; outer < data.length; ++outer) {
            key = data[outer];

            int inner = outer;
            for (; inner > 0 && data[inner - 1] > key; --inner) {
                data[inner] = data[inner - 1];
                
                renderer.SetCurrent(outer, inner, inner - 1);
                refresher.RepaintAsyncAndWait(dataset);
            }

            renderer.SetCurrent(-1, inner, outer);
            data[inner] = key;
            
            refresher.RepaintAsyncAndWait(dataset);
        }
    }

    @Override
    public String GetName() {
        return "InsertionSort";
    }

}
