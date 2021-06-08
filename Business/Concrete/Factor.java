package Business.Concrete;

import static DataAccess.Concrete.Constant.*;

public class Factor {
    double[][] bufferIn;
    double[] bufferOut;

    public Factor() {
        bufferIn = new double[NEURON_SIZE][TOTAL_INPUT];
        bufferOut = new double[NEURON_SIZE];
    }

    public void randomWeightsAndBias() {
        weightsInput = new double[NEURON_SIZE][TOTAL_INPUT];
        weightsOutput = new double[NEURON_SIZE];
        bias = new double[NEURON_SIZE + 1];

        for (int j = 0; j < NEURON_SIZE; j++) {
            weightsOutput[j] = Math.random();
            bias[j] = Math.random();

            for (int i = 0; i < TOTAL_INPUT; i++) {
                weightsInput[j][i] = Math.random();
            }
        }

        bias[NEURON_SIZE] = Math.random();
    }

    public void updateWeight(double[][] hidden, double[] output) {
        for (int n = 0; n < NEURON_SIZE; n++) {
            for (int w = 0; w < TOTAL_INPUT; w++) {
                weightsInput[n][w] += hidden[n][w];
                //weightsInput[n][w] = bufferIn[n][w] + hidden[n][w];
            }
            weightsOutput[n] += output[n];
            //weightsOutput[n] = bufferOut[n] + output[n];
        }
        bufferIn = hidden;
        bufferOut = output;
    }

    public void updateBias(double[] hidden, double output) {
        for (int b = 0; b < NEURON_SIZE; b++) {
            bias[b] += hidden[b];
        }
        bias[NEURON_SIZE] += output;
    }

    public double calculateSigmaMSE(double sigmaMSE, double localError) {
        sigmaMSE += Math.pow(localError, 2);
        return sigmaMSE;
    }

    public double calculateSigmaMAPE(double sigmaMAPE, double localError, int dataCounter) {
        if (matrixTrainSolution[dataCounter][0] != 0) {
            sigmaMAPE += Math.abs(localError) / matrixTrainSolution[dataCounter][0];
        }
        return sigmaMAPE;
    }

    public double calculateRateMSE(double rateMSE, double sigmaMSE) {
        rateMSE = sigmaMSE / matrixTrainDataSet.length;
        return rateMSE;
    }

    public double calculateRateMAPE(double rateMAPE, double sigmaMAPE) {
        rateMAPE = (sigmaMAPE / matrixTrainDataSet.length) * 100;
        return rateMAPE;
    }
}
