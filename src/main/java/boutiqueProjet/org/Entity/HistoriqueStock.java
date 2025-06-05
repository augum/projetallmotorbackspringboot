package boutiqueProjet.org.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity @AllArgsConstructor @NoArgsConstructor @Data
public class HistoriqueStock {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String article;
    private int qte;
    private LocalDate date;

}
