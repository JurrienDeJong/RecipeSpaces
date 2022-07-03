package nl.bioinf.recipespaces.model;

import lombok.Data;

import javax.persistence.*;
/**
 * POJO for recipe data with some JPA functionality
 * @author Jurriën de Jong
 */
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id")
    private Integer id;

    @Column(name = "tag_value")
    private String tagValue;

    public Recipe(Integer id, String tagValue) {
        this.id = id;
        this.tagValue = tagValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public Recipe() {

    }
}
