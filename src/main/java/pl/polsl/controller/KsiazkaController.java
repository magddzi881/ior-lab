package pl.polsl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.polsl.model.Ksiazka;
import pl.polsl.service.KsiazkaService;

import java.util.List;

@RestController
@RequestMapping("/api/ksiazki")
@RequiredArgsConstructor
public class KsiazkaController {

    private final KsiazkaService ksiazkaService;

    public KsiazkaController(KsiazkaService ksiazkaService) {
        this.ksiazkaService = ksiazkaService;
    }

    @GetMapping
    public List<Ksiazka> getAllKsiazki() {
        return ksiazkaService.findAll();
    }

    @GetMapping("/{id}")
    public Ksiazka getKsiazka(@PathVariable Long id) {
        return ksiazkaService.findById(id);
    }

    @PostMapping
    public Ksiazka createKsiazka(@RequestBody Ksiazka ksiazka) {
        return ksiazkaService.save(ksiazka);
    }

    @PutMapping("/{id}")
    public Ksiazka updateKsiazka(@PathVariable Long id, @RequestBody Ksiazka updated) {
        Ksiazka ksiazka = ksiazkaService.findById(id);
        ksiazka.setTytul(updated.getTytul());
        ksiazka.setAutor(updated.getAutor());
        return ksiazkaService.save(ksiazka);
    }

    @DeleteMapping("/{id}")
    public void deleteKsiazka(@PathVariable Long id) {
        ksiazkaService.deleteById(id);
    }
}
