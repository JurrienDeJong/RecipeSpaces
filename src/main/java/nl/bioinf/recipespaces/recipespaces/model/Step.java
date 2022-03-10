package nl.bioinf.recipespaces.recipespaces.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private String id;
    private String tag_value;

    public Step(String tag_value) {
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

    public void setTag_value(String tagValue) {
        this.tag_value = tagValue;
    }

    public Step() {

    }

}
