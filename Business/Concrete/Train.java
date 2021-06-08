package Business.Concrete;

import Business.Abstract.IEducate;

import java.util.ArrayList;

import static DataAccess.Concrete.Constant.*;

public class Train implements IEducate {
    double sigmaMSE, sigmaMAPE, rateMSE, rateMAPE;
    int learnCounter;
    double learnBuffer = 0;
    Neural neural;

    public Train(Neural neural) {
        this.neural = neural;
        bufferTrainMSE = new ArrayList<>();
    }

    @Override
    public void run() {
        double[][] hiddenLayerVariation;
        double[] outputLayerVariation;
        double[] buffer = new double[NEURON_SIZE];
        double[] hiddenBiasVariation;
        double outputBiasVariation;
        double output, localError;
        int epoch = 0;
        do {
            epoch++;
            sigmaMSE = sigmaMAPE = rateMSE = rateMAPE = 0;
            for (int i = 0; i < matrixTrainDataSet.length; i++) {
                // Feed Forward
                buffer = neural.showHiddenLayer(i, buffer);
                output = neural.showOutputLayer(buffer);

                localError = neural.calculateLocalError(i, output);

                // Back Forward
                outputLayerVariation = neural.outputLayerWeight(buffer, localError, output);
                hiddenLayerVariation = neural.hiddenLayerWeight(i, localError, buffer);
                neural.factor.updateWeight(hiddenLayerVariation, outputLayerVariation);

                outputBiasVariation = neural.outputLayerBias(localError, output);
                hiddenBiasVariation = neural.hiddenLayerBias(localError, buffer);
                neural.factor.updateBias(hiddenBiasVariation, outputBiasVariation);

                sigmaMSE = neural.factor.calculateSigmaMSE(sigmaMSE, localError);
                sigmaMAPE = neural.factor.calculateSigmaMAPE(sigmaMAPE, localError, i);
                /*if((i%50==0 && i!=0) || i == matrixTrainDataSet.length-1){
                    rateMSE = sigmaMSE/i;
                    bufferTrainMSE.add(rateMSE);
                }*/
            }
            rateMSE = neural.factor.calculateRateMSE(rateMSE, sigmaMSE);
            rateMAPE = neural.factor.calculateRateMAPE(rateMAPE, sigmaMAPE);

            /*System.out.printf("%3.3s%-18.18s%s", epoch, ". epoch - MSE  -> ", rateMSE);
            System.out.println();
            System.out.printf("%3.3s%-18.18s%s", epoch, ". epoch - MAPE -> ", rateMAPE);
            System.out.println();*/

            bufferTrainMSE.add(rateMSE);
        } while (epoch < MAX_EPOCH && rateMAPE > 3 && isLearn(rateMAPE));
        System.out.printf("%3.3s%-18.18s%s", epoch, ". epoch - MSE  -> ", rateMSE);
        System.out.println();
        System.out.printf("%3.3s%-18.18s%s", epoch, ". epoch - MAPE -> ", rateMAPE);
        System.out.println();
    }

    public boolean isLearn(double rateMAPE) {
        if (learnBuffer - rateMAPE < 0) {
            learnCounter++;
            if (learnCounter > 5) {
                return false;
            }
        } else {
            learnCounter = 0;
        }
        learnBuffer = rateMAPE;
        return true;
    }
}
