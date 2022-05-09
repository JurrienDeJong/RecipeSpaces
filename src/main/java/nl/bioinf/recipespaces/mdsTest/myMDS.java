package nl.bioinf.recipespaces.mdsTest;

import smile.mds.MDS;

import java.util.Arrays;

public class myMDS {
    public static void main(String[] args) {
        double[][] doubles = { {0, 159, 274, 131, 197}, {159,0,230,97,89}, {247,230,0,309,317}, {131,97,309,0,68}, {197,89,317,68,0} };
        MDS mds = MDS.of(doubles);
        for (double[] cord: mds.coordinates) {
            System.out.println(Arrays.toString(cord));
        }
    }
}

