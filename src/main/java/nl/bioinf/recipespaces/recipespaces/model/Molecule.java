package nl.bioinf.recipespaces.recipespaces.model;

import javax.persistence.*;

@Entity
@Table(name = "mol")
public class Molecule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "pubchem_id")
    private Integer pubChemId;

    @Column(name = "common_name")
    private String commonName;

    @Column(name = "flavor_profile")
    private String flavorProfile;

    public Molecule(String commonName, String flavorProfile) {
        this.commonName = commonName;
        this.flavorProfile = flavorProfile;
    }

    public Molecule(Integer pubChemId, String commonName, String flavorProfile) {
        this.pubChemId = pubChemId;
        this.commonName = commonName;
        this.flavorProfile = flavorProfile;
    }

    public Integer getPubChemId() {
        return pubChemId;
    }

    public void setPubChemId(Integer pubChemId) {
        this.pubChemId = pubChemId;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getFlavorProfile() {
        return flavorProfile;
    }

    public void setFlavorProfile(String flavorProfile) {
        this.flavorProfile = flavorProfile;
    }

    @Override
    public String toString() {
        return "Molecule{" +
                "pubChemId=" + pubChemId +
                ", commonName='" + commonName + '\'' +
                ", flavorProfile='" + flavorProfile + '\'' +
                '}';
    }

    public Molecule() {

    }

}
