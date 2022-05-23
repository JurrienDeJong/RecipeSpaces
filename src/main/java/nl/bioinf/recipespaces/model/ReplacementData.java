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

    private Boolean valid;

    public ReplacementData(Integer ingredientId, String commonName, Boolean valid) {
        this.ingredientId = ingredientId;
        this.commonName = commonName;
        this.valid = valid;
    }

    public ReplacementData() {

    }


    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Boolean getValid() {
        return valid;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }
}
