package frame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PieChart {
    private ChartPanel frame;
    private String studentAnalysis;
    private String exc, good, medium, at_avg, under_avg;

    public PieChart(String studentAnalysis){
        this.studentAnalysis = studentAnalysis;
        DefaultPieDataset data = getDataSet();
        JFreeChart chart = ChartFactory.createPieChart3D(
                    "Student Score Pie Chart",
                    data,
                    true,
                    false,
                    false
                );
        PiePlot pieplot = (PiePlot)chart.getPlot();
        DecimalFormat df = new DecimalFormat("0.00%");
        NumberFormat nf = NumberFormat.getNumberInstance();
        StandardPieSectionLabelGenerator sp = new StandardPieSectionLabelGenerator("{0} {2}", nf, df);
        pieplot.setLabelGenerator(sp);
        frame = new ChartPanel(chart, true);
    }

    private DefaultPieDataset getDataSet(){
        String[] targetSplit = studentAnalysis.split("-");
        exc = targetSplit[3];
        good = targetSplit[5];
        medium = targetSplit[7];
        at_avg = targetSplit[9];
        under_avg = targetSplit[11];
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("excellent", Integer.valueOf(exc));
        dataset.setValue("good", Integer.valueOf(good));
        dataset.setValue("medium", Integer.valueOf(medium));
        dataset.setValue("pass", Integer.valueOf(at_avg));
        dataset.setValue("fail", Integer.valueOf(under_avg));
        return dataset;
    }

    public ChartPanel getChartPanel(){
        return frame;
    }

//    public static void main(String[] args){
//        PieChart pieChart = new PieChart();
//        JFrame frame = new JFrame();
//        frame.add(pieChart.getChartPanel());
//        frame.setSize(1000,600);
//        frame.setLocation(500, 300);
//        frame.setDefaultCloseOperation(2);
//        frame.setVisible(true);
//    }
}
