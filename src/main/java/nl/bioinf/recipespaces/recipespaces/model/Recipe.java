package nl.bioinf.recipespaces.recipespaces.model;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Recipe {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ner_id;
    private Long mol_id;

    public Recipe(String ner_id, Long mol_id) {
        this.ner_id = ner_id;
        this.mol_id = mol_id;
    }

}
