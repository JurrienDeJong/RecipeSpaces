package nl.bioinf.recipespaces.recipespaces.model;

import java.util.List;

public record UploadData(Recipe recipe, List<Ingredient> ingredients) {
}
