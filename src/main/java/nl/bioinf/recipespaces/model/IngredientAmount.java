package nl.bioinf.recipespaces.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "ingredient_amount")
public class IngredientAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id")
    private int id;

    @Column(name = "tag_value")
    private String tagValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public IngredientAmount() {

    }
}
