package frame;

import jdk.jfr.Category;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class BarChart {
    private ChartPanel frame;
    private String studentAnalysis;
    private String exc, good, medium, at_avg, under_avg;

    public BarChart(String studentAnalysis){
        this.studentAnalysis = studentAnalysis;
        String[] targetSplit = studentAnalysis.split("-");
        CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart(
                "Student Score Bar Chart",
                "Level",
                "Number",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );

//        CategoryPlot plot = chart.getCategoryPlot();
//        CategoryAxis domainAxis = plot.getDomainAxis();
//        domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14));
//        domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12 ));
//        ValueAxis rangeAxis = plot.getRangeAxis();
//        rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
//        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
//        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));
        frame = new ChartPanel(chart, true);
    }

    private CategoryDataset getDataSet(){
        String[] targetSplit = studentAnalysis.split("-");
        exc = targetSplit[3];
        good = targetSplit[5];
        medium = targetSplit[7];
        at_avg = targetSplit[9];
        under_avg = targetSplit[11];
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(Integer.valueOf(under_avg), "fail", "fail");
        dataset.addValue(Integer.valueOf(at_avg), "pass", "pass");
        dataset.addValue(Integer.valueOf(medium), "medium", "medium");
        dataset.addValue(Integer.valueOf(good),"good", "good");
        dataset.addValue(Integer.valueOf(exc), "excellent", "excellent");
        return dataset;
    }

    public ChartPanel getChartPanel(){
        return frame;
    }

//    public static void main(String[] args){
//        JFrame chart = new JFrame();
//        BarChart barChart = new BarChart();
//        chart.add(barChart.getChartPanel());
//        chart.setDefaultCloseOperation(2);
//        chart.setVisible(true);
//        chart.setSize(500,500);
//    }
}