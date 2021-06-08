package UI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

import static DataAccess.Concrete.Constant.*;

public class Chart extends JFrame {
    public Chart() throws HeadlessException {
        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createChartPanel() {
        String chartTitle = "Error";
        String categoryAxisLabel = bufferTrainMSE.size()+" epoch";
        String valueAxisLabel = "Mean Square Error (MSE)";

        Color gridColor = Color.black;
        Color bgColor = Color.darkGray;
        Color lineColor = Color.yellow;
        BasicStroke strokeOne = new BasicStroke(1.0f);
        BasicStroke strokeTwo = new BasicStroke(2.0f);

        CategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createLineChart(chartTitle, categoryAxisLabel, valueAxisLabel, dataset);

        // chart ref
        CategoryPlot chartPlot = chart.getCategoryPlot();
        CategoryAxis chartAxis = chartPlot.getDomainAxis();

        // chart lower & upper margin
        chartAxis.setLowerMargin(0);
        chartAxis.setUpperMargin(0);

        // x-axis vertical label
        chartAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

        // line point
        LineAndShapeRenderer chartRenderer = new LineAndShapeRenderer();
        chartPlot.setRenderer(chartRenderer);

        // line color & line size
        chartRenderer.setSeriesPaint(0, lineColor);
        chartRenderer.setSeriesStroke(0, strokeOne);

        // border
        chartPlot.setOutlinePaint(gridColor);
        chartPlot.setOutlineStroke(strokeTwo);

        // bg
        chartPlot.setBackgroundPaint(bgColor);

        // grid
        chartPlot.setRangeGridlinesVisible(true);
        chartPlot.setRangeGridlinePaint(gridColor);
        chartPlot.setDomainGridlinesVisible(true);
        chartPlot.setDomainGridlinePaint(gridColor);

        return new ChartPanel(chart);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String train = "Train";
        String test = "Test";

        for (int i = 0; i < bufferTrainMSE.size(); i++) {
            dataset.addValue(bufferTrainMSE.get(i), train, "" + (i+1));
        }
        /*for (int i = 0; i<bufferTestMSE.size(); i++){
            dataset.addValue(bufferTestMSE.get(i), test, ""+ (i+1));
        }*/
        return dataset;
    }
}
