package nl.bioinf.recipespaces.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReplacementData {
    @Id
    @Column(name = "ner_id")
    private Integer ingredientId;

    @Column(name = "common_name")
    private String commonName;

    public ReplacementData(Integer ingredientId, String commonName) {
        this.ingredientId = ingredientId;
        this.commonName = commonName;
    }

    public ReplacementData() {

    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }
}
