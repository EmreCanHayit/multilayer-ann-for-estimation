package Business.Concrete;

import Business.Abstract.IParser;
import DataAccess.Abstract.IScan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;

import static DataAccess.Concrete.Constant.*;

public class ParserExcel implements IParser {
    IScan scanner;
    ArrayList<String> listDataSet;
    ArrayList<ArrayList<String>> bufferArrayList;
    int counterColumn, counterRow, randomBuffer, counter;
    Random random;

    public ParserExcel(IScan scan) {
        this.scanner = scan;
        listDataSet = scanner.scan();
        random = new Random();
        bufferArrayList = new ArrayList<>();
        matrixDataSet = new double[TOTAL_DATA][TOTAL_INPUT];
        matrixSolution = new double[TOTAL_DATA][TOTAL_OUTPUT];
    }

    @Override
    public void parse() {
        String[] bufferArray = new String[TOTAL_INPUT + TOTAL_OUTPUT];
        counter = 0;
        for (String data : listDataSet) {
            bufferArray[counter] = data;
            counter++;
            if (counter == TOTAL_INPUT + TOTAL_OUTPUT) {
                counter = 0;
                bufferArrayList.add(new ArrayList<>(Arrays.asList(bufferArray)));
            }
        }

        if (isNumeric(bufferArrayList.get(0).get(0))) bufferArrayList.remove(0);

        counterRow = 0;
        do {
            randomBuffer = random.nextInt(bufferArrayList.size());
            String[] array = bufferArrayList.get(randomBuffer).toArray(new String[0]);
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
            bufferArrayList.remove(randomBuffer);
        } while (!bufferArrayList.isEmpty());
    }

    public static boolean isNumeric(String string) {
        String regex = "[a-zA-Z]+[0-9]{0,2}";
        return Pattern.matches(regex, string);
    }
}
