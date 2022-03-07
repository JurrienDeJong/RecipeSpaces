package nl.bioinf.recipespaces.recipespaces.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private String id;
    private String tag_value;

    public Ingredient(String tag_value) {
        this.tag_value = tag_value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag_value() {
        return tag_value;
    }

    public void setTag_value(String tag_value) {
        this.tag_value = tag_value;
    }

    public Ingredient() {

    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @ManyToMany
    @JoinTable(name="recipe_ner",
            joinColumns=@JoinColumn(name="ner_id"),
            inverseJoinColumns=@JoinColumn(name="recipe_id"))
    private List<Ingredient> ingredients;
}
