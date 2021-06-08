package Business.Concrete;

import Business.Abstract.IParser;
import DataAccess.Abstract.IScan;

import java.util.ArrayList;
import java.util.Random;

import static DataAccess.Concrete.Constant.*;

public class ParserSCSV implements IParser {
    IScan scanner;
    ArrayList<String> listDataSet;
    int counterColumn, counterRow, randomBuffer;
    Random random;

    public ParserSCSV(IScan scan) {
        this.scanner = scan;
        listDataSet = scanner.scan();
        random = new Random();
        matrixDataSet = new double[TOTAL_DATA][TOTAL_INPUT];
        matrixSolution = new double[TOTAL_DATA][TOTAL_OUTPUT];
    }

    @Override
    public void parse() {
        counterRow = 0;
        do {
            randomBuffer = random.nextInt(listDataSet.size());
            String[] array = listDataSet.get(randomBuffer).split(";");
            counterColumn = 0;
            for (String piece : array) {
                if (counterColumn < TOTAL_INPUT) {
                    matrixDataSet[counterRow][counterColumn] = Double.parseDouble(piece);
                }
                if (counterColumn < TOTAL_OUTPUT + TOTAL_INPUT && counterColumn >= TOTAL_INPUT) {
                    matrixSolution[counterRow][counterColumn - TOTAL_INPUT] = Double.parseDouble(piece);
                }
                counterColumn++;
            }
            counterRow++;
            listDataSet.remove(randomBuffer);
        } while (!listDataSet.isEmpty());


        // sıralı
        /*counterRow = 0;
        for (String line : listDataSet) {
            String[] array = line.split(";");
            counterColumn = 0;
            for (String piece : array) {
                if (counterColumn < TOTAL_INPUT) {
                    matrixDataSet[counterRow][counterColumn] = Double.parseDouble(piece);
                }
                if (counterColumn < TOTAL_OUTPUT + TOTAL_INPUT && counterColumn >= TOTAL_INPUT) {
                    matrixSolution[counterRow][counterColumn - TOTAL_INPUT] = Double.parseDouble(piece);
                }
                counterColumn++;
            }
            counterRow++;
        }*/
    }
}
