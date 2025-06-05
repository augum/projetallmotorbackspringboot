package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.HistoriqueStock;
import boutiqueProjet.org.Repository.HistoriqueStockRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class HistoriqueStockController {
    private HistoriqueStockRepository repository;
    @PostMapping("/historique")
    public HistoriqueStock createHistory(@RequestBody HistoriqueStock stock){
        //stock.setDate(LocalDate.now());
        return  repository.save(stock);
    }
    @GetMapping("/historique")
    public List<HistoriqueStock> findAllHistories(@RequestParam(name = "article") String article){
        List<HistoriqueStock> comms = new ArrayList<>();
        repository.findAllByarticle(article).forEach(comms::add);
        return comms;

    }
}
