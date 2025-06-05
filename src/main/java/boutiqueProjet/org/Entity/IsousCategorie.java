package boutiqueProjet.org.Entity;

import org.springframework.data.rest.core.config.Projection;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@Projection(name = "p1",types = {boutiqueProjet.org.Entity.Scategorie.class})
public interface IsousCategorie {
    public Long getId();
    public String getCode();
    public String getLibelle();
    public Categorie getCategorie();
}
