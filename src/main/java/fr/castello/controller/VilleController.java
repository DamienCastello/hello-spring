package fr.castello.controller;

import fr.castello.entity.Ville;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/villes")
public class VilleController {
    private List<Ville> villes = new ArrayList<>();

    @PostConstruct
    public void initData() {
        Ville v1 = new Ville( "Totocity", 3);
        Ville v2 = new Ville("Arles", 55000);

        villes.addAll(Arrays.asList(v1, v2));
    }


    @GetMapping
    public List<Ville> getVilles(){
        return villes;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVille(@PathVariable Long id){
            Optional<Ville> ville = villes.stream().filter(el -> id == el.getId()).findFirst();
            if(ville.isEmpty()) return ResponseEntity.notFound().build();
            else return ResponseEntity.ok(ville.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVille(
            @PathVariable Long id,
            @RequestBody Ville ville
    ) {
        Optional<Ville> villeExistante = villes.stream()
                .filter(el -> el.getId() == id)
                .findFirst();

        if (villeExistante.isPresent()) {

            ListIterator<Ville> it = villes.listIterator();

            while (it.hasNext()) {
                Ville villeCourante = it.next();

                if (villeCourante.getId() == id) {
                    villeCourante.setNom(ville.getNom());
                    villeCourante.setPopulation(ville.getPopulation());
                }
            }

            return ResponseEntity.ok("La ville est modifiée avec succès !");
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createVille(@RequestBody Ville ville){
        boolean exists = villes.stream()
                .anyMatch(el -> ville.getNom().equals(el.getNom()));

        if(exists) return ResponseEntity.badRequest().body("La ville existe déjà");

        Ville newVille = new Ville(ville.getNom(), ville.getPopulation());
        villes.add(newVille);

        return ResponseEntity.ok("La ville est créé avec succès !");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable Long id){
        Optional<Ville> villeExistante = villes.stream().filter(el -> id == el.getId()).findFirst();

        if(villeExistante.isPresent()) {
            villes.remove(villeExistante.get());
            return ResponseEntity.ok("La ville a été supprimée avec succès !");
        }
        else return ResponseEntity.notFound().build();
    }
}
