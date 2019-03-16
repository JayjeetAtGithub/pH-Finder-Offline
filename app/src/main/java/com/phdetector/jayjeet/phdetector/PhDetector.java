package com.phdetector.jayjeet.phdetector;

import java.util.ArrayList;
import java.util.Arrays;

public class PhDetector {

    private class RGBColor{
        int r;
        int g;
        int b;

        RGBColor(int r, int g, int b){
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }


    private double cubeRoot(double num){
        return Math.cbrt(num);
    }

    private ArrayList<Double> findLAB(double x, double y, double z, double xn, double yn, double zn){
        double lStar = (116 * cubeRoot(y/yn));
        double aStar = 500 * (cubeRoot(x/xn)-cubeRoot(y/yn));
        double bStar = 200 * (cubeRoot(y/yn)-cubeRoot(z/zn));
        ArrayList<Double> arrayList = new ArrayList<>();
        arrayList.add(lStar);
        arrayList.add(aStar);
        arrayList.add(bStar);
        return arrayList;
    }

    private double findDelE(double lStar, double aStar, double bStar, double loStar, double aoStar, double boStar){
        double dell = loStar - lStar;
        double dela = aoStar - aStar;
        double delb = boStar - bStar;
        double dele = Math.sqrt(Math.pow(dell,2) + Math.pow(dela,2) + Math.pow(delb,2));
        return dele;

    }

    public double phFromEq(double c, double m, double r, double g, double b, double xn, double yn , double zn, double loStar, double aoStar, double boStar){
        ArrayList<Double> arrayList = findLAB(r,g,b,xn,yn,zn);
        double delEInp = findDelE(arrayList.get(0),arrayList.get(1),arrayList.get(2),loStar,aoStar,boStar);
        double outputPH = (delEInp - c)/m;
        return outputPH;
    }

    public double phFromData(double r, double g, double b, double xn, double yn , double zn, double loStar, double aoStar, double boStar){
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<Double>(Arrays.<Double>asList(2.0,4.0,6.0,8.0,10.0,12.0));
        ArrayList<RGBColor> rgbValues = new ArrayList<>(Arrays.<RGBColor>asList(
                new RGBColor(195,151,178),
                new RGBColor(184, 130, 166),
                new RGBColor(179, 136, 163),
                new RGBColor(171,148,166),
                new RGBColor(153,139,156),
                new RGBColor(171,163,178)
        ));
        for (RGBColor rgbColor : rgbValues){
            ArrayList<Double> lab = findLAB(rgbColor.r,rgbColor.g,rgbColor.b,xn,yn,zn);
            double dele = findDelE(lab.get(0),lab.get(1),lab.get(2),loStar,aoStar,boStar);
            x.add(dele);
        }

        ArrayList<Double> labInp = findLAB(r,g,b,xn,yn,zn);
        Double delEInp = findDelE(labInp.get(0),labInp.get(1),labInp.get(2),loStar,aoStar,boStar);
        LinearRegressionClassifier classifier = new LinearRegressionClassifier(x,y);
        return  classifier.predictValue(delEInp);
    }
}
