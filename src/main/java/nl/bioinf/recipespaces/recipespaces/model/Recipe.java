package nl.bioinf.recipespaces.recipespaces.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String tag_value;

    public Recipe(String tag_value) {
        this.tag_value = tag_value;
    }

    public String getId() {
        return id.substring(4);
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

    public Recipe() {

    }
}
