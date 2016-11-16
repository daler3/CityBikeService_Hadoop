package charts;


import org.jfree.ui.RefineryUtilities;



public class MainChart {
	
	public static void main(String[] args) throws Exception {

	    System.setProperty("java.awt.headless", "false");
	    //System.out.println(java.awt.GraphicsEnvironment.isHeadless());

        final Chart1 chart1 = new Chart1("Avg dur. of trips per week in 2015", args[0]+"/part-00000");
        chart1.pack();
        RefineryUtilities.centerFrameOnScreen(chart1);
        chart1.setVisible(true);        
        
        final Chart2 chart2 = new Chart2("N. of CUSTOMERS using bikes per week in 2015", args[0]+"/part-00000");
        chart2.pack();
        RefineryUtilities.centerFrameOnScreen(chart2);
        chart2.setVisible(true);
        
        final Chart3 nTripsBikerAgeRange = new Chart3("N. of trips per biker age range", args[1]+"/part-00000");
        nTripsBikerAgeRange.pack();
        RefineryUtilities.centerFrameOnScreen(nTripsBikerAgeRange);
        nTripsBikerAgeRange.setVisible(true);
        
        final Chart4 avgDurTripBikerAgeRange = new Chart4("Avg. duration of trips per biker age range", args[1]+"/part-00000");
        avgDurTripBikerAgeRange.pack();
        RefineryUtilities.centerFrameOnScreen(avgDurTripBikerAgeRange);
        avgDurTripBikerAgeRange.setVisible(true);
        
	}

}
