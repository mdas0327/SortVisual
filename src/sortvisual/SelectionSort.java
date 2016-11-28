package sortvisual;

public class SelectionSort implements SortInterface {

    public void Sort(DataSet dataset) {
        int[] data = dataset.data;
        
        for (int outer = 0; outer < data.length - 1; ++outer) {
            // Assuming min at first element initially.
            int minIndex = outer;

            int inner;
            for (inner = outer + 1; inner < data.length; ++inner) {

                if (data[inner] < data[minIndex]) {
                    minIndex = inner; // new min found.
                }
            }

            if (minIndex != outer) {
                int temp = data[minIndex];
                data[minIndex] = data[outer];
                data[outer] = temp;
            }
        }
    }

    @Override
    public String GetName() {
        return "SelectionSort";
    }

}
