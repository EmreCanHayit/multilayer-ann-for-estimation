package Business.Concrete;

import Business.Abstract.IEducate;

import java.util.ArrayList;

import static DataAccess.Concrete.Constant.*;

public class Test implements IEducate {
    double sigmaMSE, sigmaMAPE, rateMSE, rateMAPE;
    Neural neural;

    public Test(Neural neural) {
        this.neural = neural;
        bufferTestMSE = new ArrayList<>();
    }

    @Override
    public void run() {
        double[] buffer = new double[NEURON_SIZE];
        double output, localError;
        sigmaMSE = sigmaMAPE = rateMSE = rateMAPE = 0;

        for (int i = 0; i < matrixTestDataSet.length; i++) {
            buffer = neural.showHiddenLayer(i, buffer);
            output = neural.showOutputLayer(buffer);

            localError = neural.calculateLocalError(i, output);

            sigmaMSE = neural.factor.calculateSigmaMSE(sigmaMSE, localError);
            sigmaMAPE = neural.factor.calculateSigmaMAPE(sigmaMAPE, localError, i);

            if((i%50==0&& i!=0)||i == matrixTestDataSet.length-1){
                rateMSE = sigmaMSE/i;
                bufferTestMSE.add(rateMSE);
            }
        }
        rateMSE = neural.factor.calculateRateMSE(rateMSE, sigmaMSE);
        rateMAPE = neural.factor.calculateRateMAPE(rateMAPE, sigmaMAPE);

        System.out.printf("%21.21s%s", "Test - MSE  -> ", rateMSE);
        System.out.println();
        System.out.printf("%21.21s%s", "Test - MAPE -> ", rateMAPE);
        System.out.println();
    }
}
