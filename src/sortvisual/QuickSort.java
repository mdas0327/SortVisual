package sortvisual;

public class QuickSort implements SortInterface {

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
        SortRange(dataset, rangeStart, rangeMid - 1);
        SortRange(dataset, rangeMid + 1, rangeEnd);
    }

    private int partition(DataSet dataset, int rangeStart, int rangeEnd) {
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
            }

            // get higher element than pivot from right.
            while (v < data[--right]) {
                if (right == rangeStart) {
                    break;
                }
            }

            if (left < right) {
                // swap at left and right.
                int temp = data[left];
                data[left] = data[right];
                data[right] = temp;
            }
        }

        // swap first element with pivot position.
        int temp = data[rangeStart];
        data[rangeStart] = data[right];
        data[right] = temp;

        return right;
    }
}
