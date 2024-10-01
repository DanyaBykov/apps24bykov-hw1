package ua.edu.ucu.tempseries;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.InputMismatchException;

import ua.edu.ucu.apps.tempseries.TemperatureSeriesAnalysis;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void test() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);

        double[] temperatureSeries2 = {1.0, 2.0, -274};
        try {
            TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis(temperatureSeries2);
        } catch (InputMismatchException e) {
            System.out.println("Caught an exception");
        }
        // TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis(new double[]{3.0, -5.0, 1.0, 5.0});
        // seriesAnalysis2.addTemps(1.0);
        // seriesAnalysis2.addTemps(2.0);
        // System.out.println(seriesAnalysis2.findTempClosestToZero());
        // System.out.println(seriesAnalysis2.getLength());
        // System.out.println(seriesAnalysis2.average());
        // System.out.println(Arrays.toString(seriesAnalysis2.sortTemps()));
    }

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.addTemps(2.0);
        double expResult = 1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {8.0, 4.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.addTemps(2.0);
        double expResult = 2.285218200133681;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);

        
        TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis();
        try {
            seriesAnalysis2.deviation();
        } catch (IllegalArgumentException e) {
            System.out.println("Caught an exception");
        }
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {3.0, 5.0, 1.0, -5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.addTemps(2.0);
        double expResult = -5.0;

        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);

        TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis();
        try {
            seriesAnalysis2.min();
        } catch (IllegalArgumentException e) {
            System.out.println("Caught an exception");
        }
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.addTemps(2.0);
        double expResult = 5.0;

        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);

        TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis();
        try {
            seriesAnalysis2.max();
        } catch (IllegalArgumentException e) {
            System.out.println("Caught an exception");
        }
    }

    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.addTemps(2.0);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.addTemps(2.0);
        double expResult = 2.0;

        double actualResult = seriesAnalysis.findTempClosestToValue(2.0);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {-5.0, 1.0};

        double[] actualResult = seriesAnalysis.findTempsLessThen(2.0);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.addTemps(2.0);
        double[] expResult = {3.0, 5.0, 2.0};

        double[] actualResult = seriesAnalysis.findTempsGreaterThen(2.0);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsInRange() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.addTemps(2.0);
        seriesAnalysis.sortTemps();
        double[] expResult = {-5.0, 1.0, 2.0, 3.0};

        double[] actualResult = seriesAnalysis.findTempsInRange(-5.0, 3.0);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testReset() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.addTemps(2.0);
        seriesAnalysis.reset();
        double[] expResult = {};

     
       assertEquals(expResult.length, seriesAnalysis.getLength());
    }

    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.addTemps(2.0);
        double expAvg = 1.2;
        double expDev = 3.2186953878862163;
        double expMin = -5.0;
        double expMax = 5.0;

        double actualAvg = seriesAnalysis.summaryStatistics().getAvgTemp();
        double actualDev = seriesAnalysis.summaryStatistics().getDevTemp();
        double actualMin = seriesAnalysis.summaryStatistics().getMinTemp();
        double actualMax = seriesAnalysis.summaryStatistics().getMaxTemp();

        assertEquals(expAvg, actualAvg, 0.00001);
        assertEquals(expDev, actualDev, 0.00001);
        assertEquals(expMin, actualMin, 0.00001);
        assertEquals(expMax, actualMax, 0.00001);

        TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis();
        try {
            seriesAnalysis2.summaryStatistics();
        } catch (IllegalArgumentException e) {
            System.out.println("Caught an exception");
        }
    }
}
