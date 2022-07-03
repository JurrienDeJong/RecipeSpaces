package nl.bioinf.recipespaces.helperClasses;

import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.model.MdsData;
import nl.bioinf.recipespaces.service.DistanceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In order to perform MDS, it needs to be fed with a 2d matrix containing dissimilarity data.
 * This class creates a MdsData object which then can be used for MDS, obviously.
 * It needs a list of ingredients to start, and creates a matrix from the distances between the
 * combination of all of them.
 * @author Jurriën de Jong
 */

public class Create2DMatrix {

    private final DistanceService distanceService;

    public Create2DMatrix(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    /**
     * Gets the data in order to put it in the matrix itself.
     * @author Jurriën de Jong
     */
    public MdsData generateMatrix(List<Ingredient> ingredients){
        int rowIndex = 0;
        List<String> ingredientNames = new ArrayList<>();
        List<List<Double>> matrixDistances = new ArrayList<>();

        for (Ingredient ing: ingredients) {
            Integer id = ing.getId();
            List<Double> rowDistances = new ArrayList<>();
            for (Ingredient ing2 : ingredients) {
                Integer target = ing2.getId();

                Optional<Double> distance = distanceService.getDistanceFromPair(id, target);
                if(distance.isPresent()){
                    double dist = 1 - distance.get();
                    rowDistances.add(dist);
                }
            }
            if(rowDistances.size() > 1){
                ingredientNames.add(ing.getTagValue());
                rowDistances.add(rowIndex, 0.0);
                matrixDistances.add(rowDistances);
                rowIndex++;
            }
        }


        double[][] matrix = create2DArrayMatrix(matrixDistances);

        return new MdsData(matrix, ingredientNames);
    }

    /**
     * static method which creates the 2d matrix double array
     * @author Jurriën de Jong
     */
    public static double[][] create2DArrayMatrix(List<List<Double>> a2DListMatrix){
        return a2DListMatrix.stream()
                .map(l -> l.stream().mapToDouble(Double::doubleValue).toArray())
                .toArray(double[][]::new);
    }

}
