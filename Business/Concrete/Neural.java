package Business.Concrete;

import static DataAccess.Concrete.Constant.*;

public class Neural {
    Factor factor;
    double hiddenLayerWeightBuffer = 0;
    double[][] hiddenLayerWeightReturn;
    double hiddenLayerBiasBuffer = 0;
    double[] hiddenLayerBiasReturn;
    double outputLayerWeightBuffer = 0;
    double[] outputLayerWeightReturn;
    double outputLayerBiasBuffer = 0;
    double outputLayerBiasReturn = 0;
    double bufferNeural = 0;

    public Neural(Factor factor) {
        this.factor = factor;
        hiddenLayerWeightReturn = new double[NEURON_SIZE][TOTAL_INPUT];
        hiddenLayerBiasReturn = new double[NEURON_SIZE];
        outputLayerWeightReturn = new double[NEURON_SIZE];
    }

    public double[] showHiddenLayer(int dataCounter, double[] bufferOutput) {
        for (int n = 0; n < NEURON_SIZE; n++) {
            bufferOutput[n] = neuron(matrixTrainDataSet[dataCounter], weightsInput[n], bias[n]);
        }
        return bufferOutput;
    }

    public double showOutputLayer(double[] bufferOutput) {
        double output;
        output = neuron(bufferOutput, weightsOutput, bias[NEURON_SIZE]);
        return output;
    }

    public double calculateLocalError(int dataCounter, double output) {
        return matrixTrainSolution[dataCounter][0] - output;
    }

    public double[][] hiddenLayerWeight(int dataCounter, double localError, double[] output) {
        double bufferError;
        for (int n = 0; n < NEURON_SIZE; n++) {
            bufferError = localError * weightsOutput[n];
            //bufferError = output[n] * (1 - output[n]) * weightsOutput[n] * bufferNeural;

            for (int w = 0; w < TOTAL_INPUT; w++) {
                hiddenLayerWeightBuffer = LEARNING_RATE * bufferError * matrixTrainDataSet[dataCounter][w] + MOMENTUM_RATE * hiddenLayerWeightReturn[n][w];
                hiddenLayerWeightReturn[n][w] = hiddenLayerWeightBuffer;
            }
        }
        return hiddenLayerWeightReturn;
    }

    public double[] hiddenLayerBias(double localError, double[] output) {
        double bufferError;
        for (int n = 0; n < NEURON_SIZE; n++) {
            bufferError = localError * weightsOutput[n];
            //bufferError = output[n] * (1 - output[n]) * weightsOutput[n] * bufferNeural;
            hiddenLayerBiasBuffer = LEARNING_RATE * bufferError + MOMENTUM_RATE * hiddenLayerBiasReturn[n];
            hiddenLayerBiasReturn[n] = hiddenLayerBiasBuffer;
        }
        return hiddenLayerBiasReturn;
    }

    public double[] outputLayerWeight(double[] bufferOutput, double localError, double output) {
        double bufferError;
        bufferError = localError;
        //bufferError = output * (1 - output) * localError;

        for (int n = 0; n < NEURON_SIZE; n++) {
            outputLayerWeightBuffer = LEARNING_RATE * bufferError * bufferOutput[n] + MOMENTUM_RATE * outputLayerWeightReturn[n];
            outputLayerWeightReturn[n] = outputLayerWeightBuffer;
        }
        bufferNeural = bufferError;
        return outputLayerWeightReturn;
    }

    public double outputLayerBias(double localError, double output) {
        double bufferError;
        bufferError = localError;
        //bufferError = output * (1 - output) * localError;
        outputLayerBiasBuffer = LEARNING_RATE * bufferError + MOMENTUM_RATE * outputLayerBiasReturn;
        outputLayerBiasReturn = outputLayerBiasBuffer;
        bufferNeural = bufferError;
        return outputLayerBiasReturn;
    }

    public double think(double[] data, double[][] weightsInput, double[] bias, double[] weightsOutput) {
        double[] bufferOutput = new double[NEURON_SIZE];
        double output = 0;
        for (int n = 0; n < NEURON_SIZE; n++) {
            bufferOutput[n] = neuron(data, weightsInput[n], bias[n]);
        }
        output = neuron(bufferOutput, weightsOutput, bias[NEURON_SIZE]);
        return output;
    }

    public double neuron(double[] dataPerLine, double[] weights, double bias) {
        double buffer = 0;
        for (int i = 0; i < dataPerLine.length; i++) {
            buffer += dataPerLine[i] * weights[i];
        }
        return sigmoid(buffer, bias);
    }

    public double sigmoid(double net, double bias) {
        return 1 / (1 + Math.exp(-(net + bias)));
        /*
         *   Sigmoid
         *   1/(1+Math.pow(Math.E, buffer))  ->  0.11920292202211757
         *   1/(1+Math.exp(buffer))          ->  0.11920292202211755
         *
         *   10^17'de %2'lik bir hata var.
         */
    }
}
