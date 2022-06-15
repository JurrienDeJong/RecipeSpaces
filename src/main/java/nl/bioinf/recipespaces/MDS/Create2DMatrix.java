package nl.bioinf.recipespaces.MDS;

import nl.bioinf.recipespaces.model.Ingredient;
import nl.bioinf.recipespaces.model.MdsData;
import nl.bioinf.recipespaces.service.DistanceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Create2DMatrix {

    private final DistanceService distanceService;

    public Create2DMatrix(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    public MdsData generateMatrix(List<Ingredient> ingredients){
        int rowIndex = 0;
        List<String> ingredientNames = new ArrayList<>();
        List<List<Double>> matrixDistances = new ArrayList<>();
        for (Ingredient ingredient: ingredients) {
            Integer id = ingredient.getId();
            List<Double> rowDistances = new ArrayList<>();
            for (Ingredient ing : ingredients) {
                Integer target = ing.getId();

                Optional<Double> distance = distanceService.getDistanceFromPair(id, target);
                if(distance.isPresent()){
                    double dist = 1 - distance.get();
                    rowDistances.add(dist);
                }
            }
            if(rowDistances.size() > 1){
                ingredientNames.add(ingredient.getTagValue());
                rowDistances.add(rowIndex, 0.0);
                matrixDistances.add(rowDistances);
                rowIndex++;
            }
        }


        double[][] matrix = create2DArrayMatrix(matrixDistances);

        return new MdsData(matrix, ingredientNames);
    }

    public static double[][] create2DArrayMatrix(List<List<Double>> a2DListMatrix){
        return a2DListMatrix.stream()
                .map(l -> l.stream().mapToDouble(Double::doubleValue).toArray())
                .toArray(double[][]::new);
    }

}
