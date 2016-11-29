package sortvisual;

public class MergeSort implements SortInterface {

    private final IRefresher refresher;

    MergeSort(IRefresher refresher_) {
        refresher = refresher_;
    }

    private int[] mergeSpace;

    @Override
    public void Sort(DataSet dataset) {

        mergeSpace = new int[dataset.data.length];
        SortRange(dataset, 0, dataset.data.length - 1);
    }

    @Override
    public String GetName() {
        return "MergeSort";
    }

    private void SortRange(DataSet dataset, int left, int right) {

        if (left < right) {
            int middle = (left + right) / 2;

            SortRange(dataset, left, middle);
            SortRange(dataset, middle + 1, right);

            mergeSortedSubArrays(dataset, left, middle, right);
        }
    }

    private void mergeSortedSubArrays(DataSet dataset, int left, int middle, int right) {
        int[] data = dataset.data;

        int rangeOneIter = left;
        int rangeOneEnd = middle;
        int rangeTwoIter = middle + 1;
        int rangeTwoEnd = right;
        int j = left;

        while (rangeOneIter <= middle && rangeTwoIter <= rangeTwoEnd) {
            if (data[rangeOneIter] <= data[rangeTwoIter]) {
                mergeSpace[j++] = data[rangeOneIter++];
            } else {
                mergeSpace[j++] = data[rangeTwoIter++];
            }
        }

        while (rangeOneIter <= rangeOneEnd) {
            mergeSpace[j++] = data[rangeOneIter++];
        }

        while (rangeTwoIter <= rangeTwoEnd) {
            mergeSpace[j++] = data[rangeTwoIter++];
        }

        for (int k = left; k <= right; k++) {
            data[k] = mergeSpace[k];
        }
    }

}
