package nl.bioinf.recipespaces.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ner_distances")
public class IngredientDistance {
    @Id
    @Column(name = "target")
    private Integer ingredientTo;

    @Column(name = "ner_id")
    private Integer ingredientFrom;

    @Column(name = "afstand")
    private double distance;

    public IngredientDistance() {

    }

    public Integer getIngredientFrom() {
        return ingredientFrom;
    }

    public void setIngredientFrom(Integer ingredientFrom) {
        this.ingredientFrom = ingredientFrom;
    }

    public Integer getIngredientTo() {
        return ingredientTo;
    }

    public void setIngredientTo(Integer ingredientTo) {
        this.ingredientTo = ingredientTo;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public IngredientDistance(Integer ingredientFrom, Integer ingredientTo, double distance) {
        this.ingredientFrom = ingredientFrom;
        this.ingredientTo = ingredientTo;
        this.distance = distance;
    }
}
