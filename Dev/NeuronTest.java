package Dev;

import Business.Abstract.IEducate;
import Business.Abstract.IParser;
import Business.Concrete.*;
import DataAccess.Concrete.ScannerSCSV;

import static DataAccess.Concrete.Constant.*;


public class NeuronTest {
    public static void main(String[] args){
        IParser parser = new ParserSCSV(new ScannerSCSV("src/ENB2012_data_converted.scsv"));
        parser.parse();

        Normalization normalization = new Normalization();
        normalization.normalizeAll();

        Division division = new Division();
        division.split();

        for(int trial = 2; trial <= 20; trial++){
            NEURON_SIZE = trial;

            Factor factor = new Factor();
            Neural neural = new Neural(factor);
            IEducate train = new Train(neural);
            IEducate test = new Test(neural);

            System.out.println("---------->"+NEURON_SIZE+"<----------");
            factor.randomWeightsAndBias();
            train.run();
            test.run();
        }
    }
}
