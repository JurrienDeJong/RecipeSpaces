package nl.bioinf.recipespaces.mdsTest;

import nl.bioinf.recipespaces.model.Link;
import nl.bioinf.recipespaces.model.Node;
import nl.bioinf.recipespaces.model.RecipeSpace;
import smile.mds.MDS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class myMDS {
    public static void main(String[] args) {
        double[][] doubles = {
                {0, 159, 274, 131, 197},
                {159, 0, 230, 97, 89},
                {247, 230, 0, 309, 317},
                {131, 97, 309, 0, 68},
                {197, 89, 317, 68, 0}
        };
        MDS mds = MDS.of(doubles);

        List<Double> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();

        for (double[] cord: mds.coordinates) {
            xValues.add(cord[0]);
            yValues.add(cord[1]);
        }

        RecipeSpace recipeSpace = new RecipeSpace(xValues, yValues);
    }
}

