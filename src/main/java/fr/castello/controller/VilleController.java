package fr.castello.controller;

import fr.castello.entity.Ville;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleController {

    @GetMapping
    public List<Ville> getVilles(){
        Ville v1 = new Ville("totocity", 3);
        Ville v2 = new Ville("arles", 55000);

        return new ArrayList<>(Arrays.asList(v1, v2));
    }
}
