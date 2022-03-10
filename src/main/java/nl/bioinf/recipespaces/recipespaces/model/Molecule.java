package nl.bioinf.recipespaces.recipespaces.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Molecule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private String id;
    private String common_name;
    private String flavor_profile;

    public Molecule(String common_name, String flavor_profile) {
        this.id = id;
        this.common_name = common_name;
        this.flavor_profile = flavor_profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

    public String getFlavor_profile() {
        return flavor_profile;
    }

    public void setFlavor_profile(String flavor_profile) {
        this.flavor_profile = flavor_profile;
    }

    public Molecule() {

    }

}
