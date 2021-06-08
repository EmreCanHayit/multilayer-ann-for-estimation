package DataAccess.Concrete;

import java.util.ArrayList;

public class Constant {
    public static int TOTAL_DATA = 768;
    public static int TOTAL_INPUT = 8;
    public static int TOTAL_OUTPUT = 2;

    public static int MAX_EPOCH = 100;
    public static int NEURON_SIZE = 10;
    public static int TRAIN_DATA_PERCENT = 70;

    public static int DECIMAL_SCALE = 8;

    public static double LEARNING_RATE = 0.01;
    public static double MOMENTUM_RATE = 0.02;

    public static double[][] matrixDataSet;
    public static double[][] matrixSolution;

    public static double[][] matrixTrainDataSet;
    public static double[][] matrixTrainSolution;
    public static double[][] matrixTestDataSet;
    public static double[][] matrixTestSolution;

    public static double[][] weightsInput;
    public static double[] weightsOutput;
    public static double[] bias;

    public static ArrayList<Double> bufferTrainMSE;
    public static ArrayList<Double> bufferTestMSE;
}
