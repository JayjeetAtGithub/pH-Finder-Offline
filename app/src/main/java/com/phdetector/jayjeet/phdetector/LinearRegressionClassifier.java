package com.phdetector.jayjeet.phdetector;

import java.util.ArrayList;

public class LinearRegressionClassifier {
    private ArrayList<Double> Xdata;
    private ArrayList<Double> YData;
    private Double result1;
    private Double result2;

    public LinearRegressionClassifier (ArrayList xdata, ArrayList YData) {
        this.Xdata = xdata;
        this.YData = YData;
    }

    public Double predictValue ( Double inputValue ) {
        Double X1 = Xdata.get( 0 ) ;
        Double Y1 = YData.get( 0 ) ;
        Double Xmean = getXMean( Xdata ) ;
        Double Ymean = getYMean( YData ) ;
        Double lineSlope = getLineSlope( Xmean , Ymean , X1 , Y1 ) ;
        Double YIntercept = getYIntercept( Xmean , Ymean , lineSlope ) ;
        Double prediction = ( lineSlope * inputValue ) + YIntercept ;
        return prediction ;
    }

    public Double getLineSlope (Double Xmean, Double Ymean, Double X1, Double Y1) {
        double num1 = X1 - Xmean;
        double num2 = Y1 - Ymean;
        double denom = (X1 - Xmean) * (X1 - Xmean);
        return (num1 * num2) / denom;
    }

    public double getYIntercept (Double Xmean, Double Ymean, Double lineSlope) {
        return Ymean - (lineSlope * Xmean);
    }

    public Double getXMean (ArrayList<Double> Xdata) {
        Double result1 = 0.0 ;
        for (Integer i = 0; i < Xdata.size(); i++) {
            result1 = result1 + Xdata.get(i);
        }
        return result1;
    }

    public Double getYMean (ArrayList<Double> Ydata) {
        Double result2 = 0.0 ;
        for (Integer i = 0; i < Ydata.size(); i++) {
            result2 = result2 + Ydata.get(i);
        }
        return result2;
    }
}
