package ua.edu.ucu.apps.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;

    public TemperatureSeriesAnalysis() {
        temperatureSeries = new double[0];
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        int numb = -273;
        for (double temp : temperatureSeries) {
            if (temp < numb) {
                this.temperatureSeries = new double[0];
                throw new InputMismatchException();
            }
        }
        this.temperatureSeries = Arrays.copyOf(
            temperatureSeries, temperatureSeries.length);
    }

    public int getLength() {
        double inf = Double.POSITIVE_INFINITY;
        int length = 0;
        for (double temp : this.temperatureSeries) {
            if (temp != inf) {
                length++;
            }
        }
        return length;
    }

    public double average() {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double sum = 0;
        for (double temp : this.temperatureSeries) {
            if (temp != Double.POSITIVE_INFINITY) {
                sum += temp;
            }
        }
        return sum / this.getLength();
    }

    public double deviation() {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double sum = 0;
        double avg = this.average();
        for (double temp : this.temperatureSeries) {
            if (temp != Double.POSITIVE_INFINITY) {
                sum += (temp - avg)*(temp - avg);
            }
        }
        return Math.sqrt(sum / this.getLength()-1);
    }

    public double min() {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double min = this.temperatureSeries[0];
        for (double temp : this.temperatureSeries) {
            if (temp == Double.POSITIVE_INFINITY) {
                continue;
            }
            if (temp < min) {
                min = temp;
            }
        }
        return min;
    }

    public double max() {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double max = 0;
        for (double temp : this.temperatureSeries) {
            if (temp == Double.POSITIVE_INFINITY) {
                continue;
            }
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        double close = this.temperatureSeries[0];
        for (double temp : this.temperatureSeries) {
            if (temp == Double.POSITIVE_INFINITY) {
                continue;
            }
            if (Math.abs(temp-tempValue) < close) {
                close = temp;
            }
        }
        return close;
    }

    public double[] findTempsLessThen(double tempValue) {
        int tempC = 0;
        for (double temp : this.temperatureSeries) {
            if (temp < tempValue) {
                tempC++;
            }
        }
        double[] min = new double[tempC];
        int c = 0;
        for (double temp : this.temperatureSeries) {
            if (temp < tempValue) {
                min[c] = temp;
                c++;
            }
        }
        return min;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int tempC = 0;
        for (double temp : this.temperatureSeries) {
            if (temp == Double.POSITIVE_INFINITY) {
                continue;
            }
            if (temp >= tempValue) {
                tempC++;
            }
        }
        double[] max = new double[tempC];
        int c = 0;
        for (double temp : this.temperatureSeries) {
            if (temp == Double.POSITIVE_INFINITY) {
                continue;
            }
            if (temp >= tempValue) {
                max[c] = temp;
                c++;
            }
        }
        return max;
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        int tempC = 0;
        for (double temp : this.temperatureSeries) {
            if (temp == Double.POSITIVE_INFINITY) {
                continue;
            }
            if (temp >= lowerBound && temp <= upperBound) {
                tempC++;
            }
        }
        double[] range = new double[tempC];
        int c = 0;
        for (double temp : this.temperatureSeries) {
            if (temp == Double.POSITIVE_INFINITY) {
                continue;
            }
            if (temp >= lowerBound && temp <= upperBound) {
                range[c] = temp;
                c++;
            }
        }
        return range;
    }

    public void reset() {
        this.temperatureSeries = new double[0];
    }

    public double[] sortTemps() {
        double[] sorted = this.temperatureSeries;
        Arrays.sort(sorted);
        return Arrays.copyOf(sorted, this.getLength());
    }

    public TempSummaryStatistics summaryStatistics() {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        return new TempSummaryStatistics(
            this.average(), this.deviation(), 
            this.min(), this.max());
    }

    public int addTemps(double... temps) {
        double inf = Double.POSITIVE_INFINITY;
        if (this.temperatureSeries.length == 0) {
            this.temperatureSeries = temps;
            return this.temperatureSeries.length;
        }
        int currentLength = this.getLength();
        int newLength = currentLength + temps.length;
        if (newLength > this.temperatureSeries.length) {
            double[] newSeries = new double[currentLength * 2];
            Arrays.fill(newSeries, inf);
            System.arraycopy(this.temperatureSeries, 0, newSeries,
             0, this.temperatureSeries.length);
            this.temperatureSeries = newSeries;
        }
        System.arraycopy(temps, 0, this.temperatureSeries,
         currentLength, temps.length);
        return newLength;
    }
}
