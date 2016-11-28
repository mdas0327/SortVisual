package sortvisual;

import java.util.Random;

public class DataSetFactory {

    public DataSet CreateRandom(int size, int lowerBound, int upperBound) {
        DataSet dataSet = new DataSet();
        dataSet.data = GenerateRandomArray(size, lowerBound, upperBound);
        return dataSet;
    }

    private int[] GenerateRandomArray(int size, int lowerBound, int upperBound) {
        int[] data = new int[size];
        Random rng = new Random();
        for (int i = 0; i < size; ++i) {
            data[i] = lowerBound + rng.nextInt(upperBound - lowerBound);
        }

        return data;
    }

    public DataSet CreateNPercentReverse(int size, int lowerBound, int upperBound, double percent) {
        DataSet dataSet = new DataSet();
        dataSet.data = new int[size];

        int newUpper = (int) ((size) * percent * 0.01);
        int value = upperBound;

        for (int i = 0; i < newUpper - 1; ++i) {
            dataSet.data[i] = --value;
            if (value == 0) {
                value = upperBound;
            }

        }
        Random rng = new Random();
        for (int i = newUpper; i < dataSet.data.length; ++i) {
            dataSet.data[i] = lowerBound + rng.nextInt(upperBound - lowerBound);
        }

        return dataSet;
    }

    public DataSet CreateFromRealDataSet(int size, int lowerBound, int upperBound, double percent) {
        return CreateNPercentReverse(size, lowerBound, upperBound, percent);
    }
}
