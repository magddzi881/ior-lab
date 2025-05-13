package pl.polsl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.polsl.model.Czytelnik;
import pl.polsl.service.CzytelnikService;

import java.util.List;

@RestController
@RequestMapping("/api/czytelnicy")
@RequiredArgsConstructor
public class CzytelnikController {

    private final CzytelnikService czytelnikService;

    public CzytelnikController(CzytelnikService czytelnikService) {
        this.czytelnikService = czytelnikService;
    }

    @GetMapping
    public List<Czytelnik> getAllCzytelnicy() {
        return czytelnikService.findAll();
    }

    @GetMapping("/{id}")
    public Czytelnik getCzytelnik(@PathVariable Long id) {
        return czytelnikService.findById(id);
    }

    @PostMapping
    public Czytelnik createCzytelnik(@RequestBody Czytelnik czytelnik) {
        return czytelnikService.save(czytelnik);
    }

    @PutMapping("/{id}")
    public Czytelnik updateCzytelnik(@PathVariable Long id, @RequestBody Czytelnik updated) {
        Czytelnik czytelnik = czytelnikService.findById(id);
        czytelnik.setImie(updated.getImie());
        czytelnik.setNazwisko(updated.getNazwisko());
        return czytelnikService.save(czytelnik);
    }

    @DeleteMapping("/{id}")
    public void deleteCzytelnik(@PathVariable Long id) {
        czytelnikService.deleteById(id);
    }
}
