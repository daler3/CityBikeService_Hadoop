package charts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;


public class Chart2 extends ApplicationFrame {
	

    public Chart2(final String title, String path) {
        super(title);
        final CategoryDataset dataset = createDataset(path);
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }


    private CategoryDataset createDataset(String path) {
        
        // row keys...
        //final String series1 = "Avg dur. of trips per week in 2015";
        final String series2 = "N. of CUSTOMERS using bikes per week in 2015";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();


		try {
			FileReader inputStreamReader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null){
				String splitted[] = line.split("\\t");
				//System.out.println("Ecco: "+splitted[0]); 
				String valuesSplit[] = splitted[1].split("-");
				//in posizione 0 di valueSplit ho la media, in posizione 1 i customer
				//dataset.addValue(Integer.parseInt(valuesSplit[0]), series1, splitted[0]);
				dataset.addValue(Integer.parseInt(valuesSplit[1]), series2, splitted[0]);	
			}
			

		} catch(Exception e){
			e.printStackTrace();
		}
        return dataset;
		      
    }
    

    private JFreeChart createChart(final CategoryDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createLineChart(
            "N. of CUSTOMERS using bikes per week in 2015",       // chart title
            "Weeks",                    // domain axis label
            "Value",                   // range axis label
            dataset,                   // data
            PlotOrientation.VERTICAL,  // orientation
            true,                      // include legend
            true,                      // tooltips
            false                      // urls
        );

        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);

        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);

        // customise the renderer...
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
//        renderer.setDrawShapes(true);

        renderer.setSeriesStroke(
            0, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {10.0f, 6.0f}, 0.0f
            )
        );
        renderer.setSeriesStroke(
            1, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {6.0f, 6.0f}, 0.0f
            )
        );
        renderer.setSeriesStroke(
            2, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {2.0f, 6.0f}, 0.0f
            )
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
    }

}
