package boutiqueProjet.org.Controller;

import boutiqueProjet.org.Entity.Site;
import boutiqueProjet.org.Repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin("*")
@RestController
public class SiteController {
    @Autowired
    SiteRepository siteRepository ;
    @GetMapping("/site")
    public List<Site> listeSite(){
        List<Site> sites = new ArrayList<>();
        siteRepository.findAll().forEach(sites::add);
        return sites;
    }
    @GetMapping("/site/{id}")
    public ResponseEntity<Site> getListById(@PathVariable(value ="id") Long ListId)
            throws ResourceNotFoundException {
        Site site = siteRepository.findById(ListId)
                .orElseThrow(()-> new ResourceNotFoundException("Site not found::"+ ListId));
        return  ResponseEntity.ok().body(site);
    }
    @PostMapping("/site")
    public Site site  ( @RequestBody Site site ){
        return  siteRepository.save(site);
    }
    @DeleteMapping("/site/{id}")
    public Map<String, Boolean> deleteSite(@PathVariable(value="id") Long SiteId)
            throws  ResourceNotFoundException{
        Site site  = siteRepository.findById(SiteId)
                .orElseThrow(()->new ResourceNotFoundException("Article not found::"+ SiteId));
        siteRepository.delete(site);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return response;
    }
    @PutMapping("site/{id}")
    public ResponseEntity<Site> updateSite(@PathVariable("id") Long id,@RequestBody Site Site){
        Optional<Site> siteinfo = siteRepository.findById(id);
        if(siteinfo.isPresent()){
            Site site = siteinfo.get();
            site.setId(Site.getId());

            return new ResponseEntity<>(siteRepository.save(site), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
