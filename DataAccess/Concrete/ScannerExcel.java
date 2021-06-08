package DataAccess.Concrete;

import DataAccess.Abstract.IScan;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ScannerExcel implements IScan {
    File filePath;
    FileInputStream file;
    HSSFWorkbook hssfWorkbook;
    HSSFSheet hssfSheet;
    XSSFWorkbook xssfWorkbook;
    XSSFSheet xssfSheet;
    ArrayList<String> listDataSet;
    String path;
    String err = "Veri seti okunamadı!";

    public ScannerExcel(String path) {
        this.path = path;
        listDataSet = new ArrayList<>();
    }


    @Override
    public ArrayList<String> scan() {
        String fileName = new File(path).getName();
        switch (fileName.substring(fileName.lastIndexOf(".") + 1)) {
            case "xls":
                readXLS(path);
                break;
            case "xlsx":
                readXLSX(path);
                break;
            default:
                System.out.print("Tanımsız dosya.");
        }
        return listDataSet;
    }

    public void readXLS(String path) {
        try {
            filePath = new File(path);
            file = new FileInputStream(filePath);

            hssfWorkbook = new HSSFWorkbook(file);
            int sheetCounter = 0;
            do {
                hssfSheet = hssfWorkbook.getSheetAt(sheetCounter);
                Iterator<Row> iteratorRow = hssfSheet.iterator();
                readLoop(iteratorRow);

                sheetCounter++;
            } while (sheetCounter < hssfWorkbook.getNumberOfSheets());

            file.close();
        } catch (Exception e) {
            System.out.println(err);
            e.printStackTrace();
        }
    }

    public void readXLSX(String path) {
        try {
            filePath = new File(path);
            file = new FileInputStream(filePath);

            xssfWorkbook = new XSSFWorkbook(file);
            int sheetCounter = 0;
            do {
                xssfSheet = xssfWorkbook.getSheetAt(sheetCounter);
                Iterator<Row> iteratorRow = xssfSheet.iterator();
                readLoop(iteratorRow);

                sheetCounter++;
            } while (sheetCounter < xssfWorkbook.getNumberOfSheets());

            file.close();
        } catch (Exception e) {
            System.out.println(err);
            e.printStackTrace();
        }
    }

    public void readLoop(Iterator<Row> iteratorRow) {
        while (iteratorRow.hasNext()) {
            Row row = iteratorRow.next();
            Iterator<Cell> iteratorCell = row.cellIterator();

            while (iteratorCell.hasNext()) {
                Cell cell = iteratorCell.next();
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        listDataSet.add(String.valueOf(cell.getNumericCellValue()));
                        break;
                    case Cell.CELL_TYPE_STRING:
                        listDataSet.add(cell.getStringCellValue());
                        break;
                }
            }
        }
    }
}
