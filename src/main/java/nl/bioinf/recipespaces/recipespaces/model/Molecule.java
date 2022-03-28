package nl.bioinf.recipespaces.recipespaces.model;

import javax.persistence.*;

@Entity
@Table(name = "mol")
public class Molecule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer pubchem_id;
    private String common_name;
    private String flavor_profile;

    public Molecule(String common_name, String flavor_profile) {
        this.common_name = common_name;
        this.flavor_profile = flavor_profile;
    }

    public Integer getPubchem_id() {
        return pubchem_id;
    }

    public void setPubchem_id(Integer pubchem_id) {
        this.pubchem_id = pubchem_id;
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
