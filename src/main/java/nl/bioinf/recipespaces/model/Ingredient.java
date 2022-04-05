package nl.bioinf.recipespaces.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "ner")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id")
    private Integer id;

    @Column(name = "tag_value")
    private String tagValue;

    public Ingredient(String tagValue) {
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

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", tagValue='" + tagValue + '\'' +
                '}';
    }

    public Ingredient() {

    }
}
