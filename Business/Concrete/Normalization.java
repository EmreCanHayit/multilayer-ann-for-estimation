package Business.Concrete;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;

import static DataAccess.Concrete.Constant.*;


public class Normalization {
    static ArrayList<Double> sortHelper;
    double formatBuffer;

    public Normalization() {
        sortHelper = new ArrayList<>();
    }

    public void normalizeAll(){
        normalizedDataSet();
        normalizedSolution();
    }

    public void normalizedDataSet() {
        for (int j = 0; j < TOTAL_INPUT; j++) {
            sortHelper.clear();
            for (int i = 0; i < TOTAL_DATA; i++) {
                sortHelper.add(matrixDataSet[i][j]);
            }
            double minimum = Collections.min(sortHelper);
            double maximum = Collections.max(sortHelper);
            for (int i = 0; i < TOTAL_DATA; i++) {
                formatBuffer = (matrixDataSet[i][j] - minimum) / (maximum - minimum);
                matrixDataSet[i][j] = decimalFormat(formatBuffer);
            }
        }
    }

    public void normalizedSolution() {
        for (int j = 0; j < TOTAL_OUTPUT; j++) {
            sortHelper.clear();
            for (int i = 0; i < TOTAL_DATA; i++) {
                sortHelper.add(matrixSolution[i][j]);
            }
            double minimum = Collections.min(sortHelper);
            double maximum = Collections.max(sortHelper);
            for (int i = 0; i < TOTAL_DATA; i++) {
                formatBuffer = (matrixSolution[i][j] - minimum) / (maximum - minimum);
                matrixSolution[i][j] = decimalFormat(formatBuffer);
            }
        }
    }

    public double decimalFormat(double value){
        BigDecimal decimalFormat = new BigDecimal(value).setScale(DECIMAL_SCALE, RoundingMode.HALF_UP);
        return decimalFormat.doubleValue();
    }
}
