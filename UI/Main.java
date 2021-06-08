package UI;

import Business.Abstract.IEducate;
import Business.Abstract.IParser;
import Business.Concrete.*;
import DataAccess.Concrete.ScannerExcel;
import DataAccess.Concrete.ScannerSCSV;
import org.apache.commons.lang3.StringUtils;

import static DataAccess.Concrete.Constant.*;

public class Main {
    public static void main(String[] args) {
        IParser parser = new ParserSCSV(new ScannerSCSV("src/ENB2012_data_converted.scsv"));
        //IParser parser = new ParserExcel(new ScannerExcel("src/ENB2012_data.xlsx"));
        parser.parse();
        //showDataset(matrixDataSet, matrixSolution, 7);

        Normalization normalization = new Normalization();
        normalization.normalizeAll();
        //showDataset(matrixDataSet, matrixSolution, DECIMAL_SCALE + 7);

        Division division = new Division();
        division.split();

        Factor factor = new Factor();
        Neural neural = new Neural(factor);
        IEducate train = new Train(neural);
        IEducate test = new Test(neural);

        factor.randomWeightsAndBias();
        train.run();
        test.run();

        //showWeight();

        think(neural, "head", 0, 0);
        think(neural, "train", 0, 7);
        think(neural, "train", 0, 5);
        think(neural, "train", 0, 8);
        think(neural, "test", 0, 12);
        think(neural, "test", 0, 3);
        think(neural, "test", 0, 0);

        Chart chart = new Chart();
    }

    public static void showDataset(double[][] matrixInput, double[][] matrixOutput, int space) {
        String format = "%-" + space + "." + space + "s";
        for (int i = 0; i < TOTAL_DATA; i++) {
            for (int j = 0; j < TOTAL_INPUT; j++) {
                System.out.printf(format, matrixInput[i][j]);
            }
            for (int j = 0; j < TOTAL_OUTPUT; j++) {
                System.out.printf(format, matrixOutput[i][j]);
            }
            System.out.println();
        }
    }

    public static void think(Neural neural, String type, int solution, int index) {
        String line = "------------------------------------------------------";
        String typeName = "Type";
        String wanted = "Wanted";
        String result = "Result";

        switch (type) {
            case "head":
                System.out.println(line);
                System.out.printf(
                        "|%s|%s|%s|%n",
                        StringUtils.center(typeName, 8),
                        StringUtils.center(wanted, 19),
                        StringUtils.center(result, 23));
                System.out.println(line);
                break;
            case "train":
                System.out.printf(
                        "|%s|%s|%s|%n",
                        StringUtils.center(type, 8),
                        StringUtils.center(String.valueOf(matrixTrainSolution[index][solution]), 19),
                        StringUtils.center(String.valueOf(neural.think(matrixTrainDataSet[index], weightsInput, bias, weightsOutput)), 23));
                System.out.println(line);
                break;
            case "test":
                System.out.printf(
                        "|%s|%s|%s|%n",
                        StringUtils.center(type, 8),
                        StringUtils.center(String.valueOf(matrixTestSolution[index][solution]), 19),
                        StringUtils.center(String.valueOf(neural.think(matrixTestDataSet[index], weightsInput, bias, weightsOutput)), 23));
                System.out.println(line);
                break;
            default:
                System.out.println("Error");
        }
    }

    public static void showWeight() {
        String line = "------------------------------------------------------";
        String neuron = "Neuron";
        String weight = "Weight";
        String rate = "Rate";

        System.out.println(line);
        System.out.printf(
                "|%s|%s|%s|%n",
                StringUtils.center(neuron, 10),
                StringUtils.center(weight, 10),
                StringUtils.center(rate, 30));
        for (int n = 0; n < NEURON_SIZE; n++) {
            System.out.println(line);
            for (int w = 0; w < TOTAL_INPUT; w++) {
                System.out.printf(
                        "|%s|%s|%s|%n",
                        StringUtils.center(String.valueOf(n + 1), 10),
                        StringUtils.center(String.valueOf(w+1), 10),
                        StringUtils.center(String.valueOf(weightsInput[n][w]), 30));
            }
        }

        System.out.println(line);
        for (int w = 0; w < NEURON_SIZE; w++) {
            System.out.printf(
                    "|%s|%s|%s|%n",
                    StringUtils.center("output", 10),
                    StringUtils.center(String.valueOf(w+1), 10),
                    StringUtils.center(String.valueOf(weightsOutput[w]), 30));
        }
        System.out.println(line);
    }
}