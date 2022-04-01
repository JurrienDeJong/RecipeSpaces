package nl.bioinf.recipespaces.recipespaces.model;

import javax.persistence.*;

@Entity
@Table(name = "step")
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id")
    private Integer id;

    @Column(name = "tag_value")
    private String tagValue;

    public Step(String tagValue) {
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

    public Step() {

    }

}
