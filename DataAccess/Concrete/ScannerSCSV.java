package DataAccess.Concrete;

import DataAccess.Abstract.IScan;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScannerSCSV implements IScan {
    File dataSet;
    Scanner dataReader;
    String path;
    String err = "Veri seti okunamadı!";
    ArrayList<String> listDataSet;

    public ScannerSCSV(String path) {
        this.path = path;
        listDataSet = new ArrayList<>();
    }

    @Override
    public ArrayList<String> scan() {
        try {
            dataSet = new File(path);
            dataReader = new Scanner(dataSet);
            while (dataReader.hasNextLine()) {
                listDataSet.add(dataReader.nextLine());
            }
            dataReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(err);
            e.printStackTrace();
        }
        return listDataSet;
    }
}
