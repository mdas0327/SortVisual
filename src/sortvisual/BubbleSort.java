package sortvisual;

public class BubbleSort implements SortInterface {

    @Override
    public void Sort(DataSet dataset) {

        int[] data = dataset.data;
        for (int i = 0; i < data.length; ++i) {
            for (int j = i + 1; j < data.length; ++j) {
                if (data[i] > data[j]) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

    @Override
    public String GetName() {
        return "BubbleSort";
    }

}
