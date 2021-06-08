package Business.Concrete;

import static DataAccess.Concrete.Constant.*;

public class Division {
    int percentBuffer;

    public Division() {
        percentBuffer = TOTAL_DATA * TRAIN_DATA_PERCENT / 100;
        matrixTrainDataSet = new double[percentBuffer][TOTAL_INPUT];
        matrixTrainSolution = new double[percentBuffer][TOTAL_OUTPUT];
        matrixTestDataSet = new double[TOTAL_DATA - percentBuffer][TOTAL_INPUT];
        matrixTestSolution = new double[TOTAL_DATA - percentBuffer][TOTAL_OUTPUT];
    }

    public void split() {
        for (int i = 0; i < percentBuffer; i++) {
            if (TOTAL_INPUT >= 0) System.arraycopy(matrixDataSet[i], 0, matrixTrainDataSet[i], 0, TOTAL_INPUT);
            if (TOTAL_OUTPUT >= 0) System.arraycopy(matrixSolution[i], 0, matrixTrainSolution[i], 0, TOTAL_OUTPUT);
        }
        for (int i = percentBuffer; i < TOTAL_DATA; i++) {
            if (TOTAL_INPUT >= 0) System.arraycopy(matrixDataSet[i], 0, matrixTestDataSet[i - percentBuffer], 0, TOTAL_INPUT);
            if (TOTAL_OUTPUT >= 0) System.arraycopy(matrixSolution[i], 0, matrixTestSolution[i - percentBuffer], 0, TOTAL_OUTPUT);
        }
    }
}
