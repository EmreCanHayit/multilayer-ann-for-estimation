package Dev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PerceptronBackup {
    public PerceptronBackup() {
        int totalData = 768, totalInput = 8, totalOutput = 2, counterColumn, counterRow;
        double[][] matrixDataSet = new double[totalData][totalInput];
        double[][] matrixSolution = new double[totalData][totalOutput];
        try {
            File dataSet = new File("src/ENB2012_data_converted.scsv");
            Scanner dataReader = new Scanner(dataSet);
            counterRow = 0;
            while (dataReader.hasNextLine()) {
                String dataLine = dataReader.nextLine();
                //System.out.println(dataLine);
                String[] array = dataLine.split(";");
                counterColumn = 0;
                for (String line : array) {
                    if (counterColumn < totalInput) {
                        matrixDataSet[counterRow][counterColumn] = Double.parseDouble(line);
                    }
                    if (counterColumn < totalOutput + totalInput && counterColumn >= totalInput) {
                        matrixSolution[counterRow][counterColumn - totalInput] = Double.parseDouble(line);
                    }
                    counterColumn++;
                }
                counterRow++;
            }
            dataReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Veri seti okunamadı!");
            e.printStackTrace();
        }

        for (int i = 0; i<totalData; i++){
            for (int j = 0; j<totalInput; j++){
                System.out.print(matrixDataSet[i][j]+" - ");
            }
            for (int j = 0; j<totalOutput; j++){
                System.out.print(matrixSolution[i][j]+" - ");
            }
            System.out.println();
        }
    }

    /*public static void normalization(){
        ArrayList<Double> sortHelper = new ArrayList<>();
        for (int j = 0; j<ParserSCSV.totalInput; j++) {
            for (int i = 0; i < ParserSCSV.totalData; i++) {
                sortHelper.add(ParserSCSV.matrixDataSet[i][j]);
            }
            double minimum = Collections.min(sortHelper);
            double maximum = Collections.max(sortHelper);
            for (int i = 0; i < ParserSCSV.totalData; i++) {
                ParserSCSV.matrixDataSet[i][j] = (ParserSCSV.matrixDataSet[i][j] - minimum) / (maximum - minimum);
            }
        }
        for (int j = 0; j<ParserSCSV.totalOutput; j++) {
            for (int i = 0; i < ParserSCSV.totalData; i++) {
                sortHelper.add(ParserSCSV.matrixSolution[i][j]);
            }
            double minimum = Collections.min(sortHelper);
            double maximum = Collections.max(sortHelper);
            for (int i = 0; i < ParserSCSV.totalData; i++) {
                ParserSCSV.matrixSolution[i][j] = (ParserSCSV.matrixSolution[i][j] - minimum) / (maximum - minimum);
            }
        }
    }*/




    /*public static void layer() {
        double[] bufferOutput = new double[NEURON_SIZE];
        int epoch = 0;
        double mse=0, mape=0;
        do {
            epoch++;
            for (int i = 0; i < 25; i++) {
                updateWeight(hiddenLayer(i, bufferOutput), outputLayer(i, bufferOutput));


                *//*if (matrixTrainSolution[i][0] != 0) {
                    mse += Math.pow(localError, 2);
                    System.out.println("MSE -> " + mse / (i+1));
                    mape += Math.abs(localError) / matrixTrainSolution[i][0];
                    System.out.println("MAPE -> " + (mape / (i+1))*100);
                }*//*
            }
        } while (epoch < MAX_EPOCH);
    }

    public static double[] hiddenLayer(int dataCounter, double[] bufferOutput) {
        double error;
        double currentChange = 0;
        double[] previousChange = new double[NEURON_SIZE];

        for (int n = 0; n < NEURON_SIZE; n++) {
            bufferOutput[n] = neuron(matrixTrainDataSet[dataCounter], weightsInput[n], bias[n]);
            error = bufferOutput[n] * (1 - bufferOutput[n]) * totalError;
            currentChange = LEARNING_RATE * error * bufferOutput[n] + MOMENTUM_RATE * previousChange[n];

            for (int w = 0; w < TOTAL_INPUT; w++) {
                totalError += error * weightsInput[n][w];
            }

            previousChange[n] = currentChange;
        }
        return previousChange;
    }

    public static double outputLayer(int dataCounter, double[] bufferOutput) {
        double output, error;
        double currentChange = 0, previousChange = 0;

        output = neuron(bufferOutput, weightsOutput, bias[NEURON_SIZE]);
        localError = matrixTrainSolution[dataCounter][0] - output;
        error = output * (1 - output) * localError;
        currentChange = LEARNING_RATE * error * output + MOMENTUM_RATE * previousChange;

        for (int n = 0; n < NEURON_SIZE; n++) {
            totalError += error * weightsOutput[n];
        }

        previousChange = currentChange;
        return previousChange;
    }*/

    /*public static void updateWeight(double[] hidden, double output) {
        for (int n = 0; n < NEURON_SIZE; n++) {
            for (int w = 0; w < TOTAL_INPUT; w++) {
                weightsInput[n][w] += hidden[n];
            }
            weightsOutput[n] += output;
        }
    }*/

    /*public static double think(double[] data, double[] bias) {
        double[] bufferOutput = new double[NEURON_SIZE];
        double output = 0;
        for (int n = 0; n < NEURON_SIZE; n++) {
            bufferOutput[n] = neuron(data, weightsInput[n], bias[n]);
        }
        output = neuron(bufferOutput, weightsOutput, bias[NEURON_SIZE]);
        return output;
    }*/

    /*public static void randomWeightsAndBias() {
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
    }*/

    /*public static double neuron(double[] dataPerLine, double[] weights, double bias) {
        double buffer = 0;
        for (int i = 0; i < dataPerLine.length; i++) {
            buffer += dataPerLine[i] * weights[i];
        }
        return sigmoid(buffer, bias);
    }

    public static double sigmoid(double net, double bias) {
        return 1 / (1 + Math.exp(-(net + bias)));
        *//*
     *   Sigmoid
     *   1/(1+Math.pow(Math.E, buffer))  ->  0.11920292202211757
     *   1/(1+Math.exp(buffer))          ->  0.11920292202211755
     *
     *   10^17'de %2'lik bir hata var.
     *//*
    }*/


    /*public static void bigLayer() {
        BigDecimal[] bufferOutput = new BigDecimal[NEURON_SIZE];
        BigDecimal output = new BigDecimal(BigInteger.ZERO);
        BigDecimal localError = new BigDecimal(BigInteger.ZERO);
        BigDecimal nextEDBD = new BigDecimal(BigInteger.ZERO);
        BigDecimal lastEDBD = new BigDecimal(BigInteger.ZERO);
        BigDecimal[] lastEDBDArray = new BigDecimal[NEURON_SIZE];
        BigDecimal nextError = new BigDecimal(BigInteger.ZERO);
        BigDecimal nextErrorBuffer = new BigDecimal(BigInteger.ZERO);

        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < NEURON_SIZE; n++) {
                bufferOutput[n] = BigDecimal.valueOf(neuron(matrixTrainDataSet[i], weightsInput[n], bias[n]));
                nextError = bufferOutput[n].multiply(BigDecimal.ONE.subtract(bufferOutput[n])).multiply(nextErrorBuffer);
                nextEDBD = BigDecimal.valueOf(LEARNING_RATE).multiply(nextError).multiply(bufferOutput[n]);
                nextEDBD = nextEDBD.add(BigDecimal.valueOf(MOMENTUM_RATE).multiply(lastEDBDArray[n]));
                for (int w = 0; w < TOTAL_INPUT; w++) {
                    weightsInput[n][w] += nextEDBD.doubleValue();
                    nextErrorBuffer = nextErrorBuffer.add(nextError.multiply(BigDecimal.valueOf(weightsInput[n][w])));
                }
                lastEDBDArray[n] = nextEDBD;
            }
            double[] bufferOutputBuffer = new double[NEURON_SIZE];
            for (int s = 0; s < bufferOutputBuffer.length; s++) {
                bufferOutputBuffer[s] = bufferOutput[s].doubleValue();
            }
            output = BigDecimal.valueOf(neuron(bufferOutputBuffer, weightsOutput, bias[NEURON_SIZE]));
            localError = BigDecimal.valueOf(matrixTrainSolution[i][0]).subtract(output);
            nextError = output.multiply(BigDecimal.ONE.subtract(output)).multiply(localError);
            nextEDBD = BigDecimal.valueOf(LEARNING_RATE).multiply(nextError).multiply(output).add(BigDecimal.valueOf(MOMENTUM_RATE).multiply(lastEDBD));
            for (int w = 0; w < NEURON_SIZE; w++) {
                weightsOutput[w] += nextEDBD.doubleValue();
                nextErrorBuffer = nextErrorBuffer.add(nextError.multiply(BigDecimal.valueOf(weightsOutput[w])));
            }
            lastEDBD = nextEDBD;
        }
    }*/


    public static void train(double[][] data, double[] weights) {
        int epoch = 0;
        double bufferOutput = 0;
        double localError = 0, globalError = 0;
        /*BigDecimal ss = new BigDecimal(BigInteger.ZERO);
        MathContext cont = new MathContext(1, RoundingMode.FLOOR);*/
//        do {
//            epoch++;
//            for (int i = 0; i < data.length; i++) {
//                bufferOutput = 0;//neuron(data[i], weights, bias[0]);
//                localError = matrixSolution[i][0] - bufferOutput;
//                for (int w = 0; w < TOTAL_INPUT; w++) {
//                    weights[w] += LEARNING_RATE * localError * data[i][w];
//                }
//                globalError += Math.pow(localError, 2);
//
//                /*if (matrixSolution[i][0] != 0) {
//                    ss = ss.add(BigDecimal.valueOf(Math.abs(matrixSolution[i][0] - bufferOutput))).divide(BigDecimal.valueOf(matrixSolution[i][0]),cont);
//                }
//
//                //ss = Math.abs(matrixSolution[i][0] - bufferOutput)/matrixSolution[i][0];*/
//            }
//            //System.out.println("Epoch " + epoch + " : MSE = " + globalError / data.length + " MAPE = "/* +ss.divide(BigDecimal.valueOf(data.length), cont).multiply(BigDecimal.valueOf(100))*/);
//        } while (globalError != 0 && epoch < MAX_EPOCH);
    }
}
