package fr.castello.controller;

import fr.castello.entity.City;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/cities")
public class CityController implements CityControllerDoc {

    private List<City> cities = new ArrayList<>();

    @PostConstruct
    public void initData() {
        City v1 = new City("Totocity", 10);
        City v2 = new City("Arles", 55000);

        cities.addAll(Arrays.asList(v1, v2));
    }


    @GetMapping(params = {"!name", "!population", "!minPop", "!maxPop"})
    public List<City> getCities() {
        return cities;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCity(@PathVariable Long id) throws FunctionalException {
        Optional<City> city = cities.stream()
                .filter(el -> id.equals(el.getId()))
                .findFirst();

        if (city.isEmpty()) {
            throw new FunctionalException("Ville non trouvée");
        } else {
            return ResponseEntity.ok(city.get());
        }
    }


    @GetMapping(params = {"name", "!population", "!minPop", "!maxPop"})
    public ResponseEntity<?> getCitiesStartWith(@RequestParam String name) throws FunctionalException {
        List<City> result = cities.stream()
                .filter(el -> el.getName().toLowerCase().startsWith(name.toLowerCase()))
                .toList();

        if (result.isEmpty()) {
            throw new FunctionalException("Aucune ville trouvée pour avec les nom " + name);
        } else {
            return ResponseEntity.ok(result);
        }
    }


    @GetMapping(params = {"population", "!name", "!minPop", "!maxPop"})
    public ResponseEntity<?> getCitiesGreater(@RequestParam int population) throws FunctionalException {
        if (population < 0) {
            throw new FunctionalException("La population ne peut pas être négatif");
        }

        List<City> result = cities.stream()
                .filter(el -> el.getPopulation() > population)
                .toList();

        if (result.isEmpty()) {
            throw new FunctionalException(
                    "Ville non trouvée avec une population supérieure à " + population
            );
        } else {
            return ResponseEntity.ok(result);
        }
    }


    @GetMapping(params = {"minPop", "maxPop", "!population", "!name"})
    public ResponseEntity<?> getCitiesBetween(
            @RequestParam int minPop,
            @RequestParam int maxPop
    ) throws FunctionalException {

        List<City> result = cities.stream()
                .filter(el -> el.getPopulation() >= minPop && el.getPopulation() <= maxPop)
                .toList();

        if (result.isEmpty()) {
            throw new FunctionalException(
                    "Ville non trouvée entre " + minPop + " et " + maxPop
            );
        } else {
            return ResponseEntity.ok(result);
        }
    }


    @PostMapping
    public ResponseEntity<String> createCity(@RequestBody City city) throws FunctionalException {

        // Validation du nom avant de l'utiliser
        if (city.getName() == null || city.getName().length() < 3) {
            throw new FunctionalException(
                    "Le nom de la ville doit avoir au moins 3 caractère"
            );
        }

        if (city.getPopulation() < 10) {
            throw new FunctionalException(
                    "La population minimale est 10"
            );
        }

        // Vérification du doublon après validation du nom
        boolean exists = cities.stream()
                .anyMatch(el -> city.getName().equals(el.getName()));

        if (exists) {
            throw new FunctionalException("La ville existe déjà");
        }

        City newCity = new City(city.getName(), city.getPopulation());
        cities.add(newCity);

        return ResponseEntity.ok("La ville est créé avec succès !");
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateCity(
            @PathVariable Long id,
            @RequestBody City city
    ) throws FunctionalException {

        Optional<City> existingCity = cities.stream()
                .filter(el -> id.equals(el.getId()))
                .findFirst();

        if (existingCity.isPresent()) {

            // Validation du nom avant d'utiliser length()
            if (city.getName() == null || city.getName().length() < 3) {
                throw new FunctionalException(
                        "Le nom de la ville doit avoir au moins 3 caractère"
                );
            }

            if (city.getPopulation() < 10) {
                throw new FunctionalException(
                        "La population minimale est 10"
                );
            }

            ListIterator<City> it = cities.listIterator();

            while (it.hasNext()) {
                City currentCity = it.next();

                if (id.equals(currentCity.getId())) {
                    currentCity.setName(city.getName());
                    currentCity.setPopulation(city.getPopulation());
                }
            }

            return ResponseEntity.ok("La ville est modifiée avec succès !");
        }

        throw new FunctionalException("Ville non trouvée");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable Long id) throws FunctionalException {

        Optional<City> existingCity = cities.stream()
                .filter(el -> id.equals(el.getId()))
                .findFirst();

        if (existingCity.isPresent()) {
            cities.remove(existingCity.get());

            return ResponseEntity.ok(
                    "La ville a été supprimée avec succès !"
            );
        } else {
            throw new FunctionalException("Ville non trouvée");
        }
    }
}
