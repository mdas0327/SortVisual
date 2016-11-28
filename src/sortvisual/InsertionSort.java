package sortvisual;

public class InsertionSort implements SortInterface {

    @Override
    public void Sort(DataSet dataset) {
        int[] data = dataset.data;
        
        int outer, key;

        for (outer = 1; outer < data.length; ++outer) {
            key = data[outer];

            int inner = outer;
            for (; inner > 0 && data[inner - 1] > key; --inner) {
                data[inner] = data[inner - 1];
            }

            data[inner] = key;
        }
    }

    @Override
    public String GetName() {
        return "InsertionSort";
    }

}
