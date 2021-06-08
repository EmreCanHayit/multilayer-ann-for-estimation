package Dev;

import javax.swing.*;
import java.awt.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class TechnologyTest extends JFrame {
    public TechnologyTest() throws HeadlessException {
        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createChartPanel(){
        String chartTitle = "Mape";
        String categoryAxisLabel = "epoch";
        String valueAxisLabel = "error";

        CategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createLineChart(chartTitle, categoryAxisLabel, valueAxisLabel, dataset);
        return new ChartPanel(chart);
    }

    private CategoryDataset createDataset(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String series = "data";
        double[] bufferData = new double[]{466.5262959553772,
                295.375450714382,
                264.6435743557602, // 31
                227.97924810701087, // 37
                191.63572515008784, // 36
                161.5866079703105, // 30
                139.62837533205118, // 22
                124.11277959113947,
                112.92381159020618,
                104.52290728010993,
                97.90797308855865,
                92.43428830344386,
                87.71346331657068,
                83.52435754552944,
                79.74636803208375,
                76.28783032823463,
                73.09334524631717,
                70.12535367657478,
                67.35452757350659,
                64.75618184016567,
                62.338268875727955,
                60.068307417963766,
                57.97446444680464,
                56.08093935947473,
                54.37006725166157,
                52.80592612997449,
                51.374190188772616,
                50.101858939940804,
                48.9582512557891,
                47.917550150405205,
                46.989795622790595,
                46.17656131774171,
                45.45018233302562,
                44.80831361814417,
                44.240091317416,
                43.74504587386016,
                43.31054541286029,
                42.93676815402407,
                42.60901386851131,
                42.32317110178122,
                42.075284454229035,
                41.8586967886322,
                41.66911457251603,
                41.50280202174244,
                41.359174107067375, // 0,143
                41.23563777035233, // 0, 124
                41.12818891706687, // 0,107
                41.034801691593465, // 0,094
                40.954996530174355, // 0,08
                40.886476537477826, // 0,068
                40.827852508422694, // 0,059
                40.77786421129848, // 0,05
                40.73568030542676, // 0,042
                40.700692126040565, // -0,265
                40.67176167684814, // 0,029
                40.648237361857724/*, // 0,042
                40.62954200230555, // 0,019
                40.61550008603537,
                40.60531468202649,
                40.59869209363852,
                40.59513370602431,
                40.59434511959743,
                40.596212764042555,
                40.600291736181745, // -0,004
                40.60662092846828, // -0,006
                40.61492019420697, // -0,008
                40.62481880563453, // -0,01
                40.636163948284036, // -0,012
                40.648973023016346, // -0,012
                40.66298632547601,
                40.67807375514485,
                40.69413870513325,
                40.71109475934246,
                40.72886453985625,
                40.74737869023613,
                40.76657497827416,
                40.78639750377606,
                40.80679599871318,
                40.82772520863387,
                40.84914434558592,
                40.87101660399716,
                40.893308732002694,
                40.91602398380381,
                40.93917391030142,
                40.962660628045285,
                40.9864617379278,
                41.01065373169496,
                41.0351209334255,
                41.059846300634575,
                41.084861249414054,
                41.110141582704095,
                41.13563531144122,
                41.161330665685,
                41.187250081190946,
                41.213381786710244,
                41.23975341210904,
                41.26628500278083,
                41.29296888256994, // -0,026
                41.31980768973117, // -0,027
                41.34682970961438*/};
        for(int i = 0; i<bufferData.length; i++){
            dataset.addValue(bufferData[i], series, ""+i);
        }
        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TechnologyTest().setVisible(true);
            }
        });
    }
}
