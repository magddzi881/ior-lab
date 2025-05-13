package pl.polsl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.polsl.model.Ocena;
import pl.polsl.model.Ksiazka;
import pl.polsl.model.Czytelnik;
import pl.polsl.service.OcenaService;
import pl.polsl.service.KsiazkaService;
import pl.polsl.service.CzytelnikService;

import java.util.List;

@RestController
@RequestMapping("/api/oceny")
@RequiredArgsConstructor
public class OcenaController {

    private final OcenaService ocenaService;
    private final KsiazkaService ksiazkaService;
    private final CzytelnikService czytelnikService;

    public OcenaController(OcenaService ocenaService, KsiazkaService ksiazkaService, CzytelnikService czytelnikService) {
        this.ocenaService = ocenaService;
        this.ksiazkaService = ksiazkaService;
        this.czytelnikService = czytelnikService;
    }

    @GetMapping
    public List<Ocena> getAllOceny() {
        return ocenaService.findAll();
    }

    @GetMapping("/{id}")
    public Ocena getOcena(@PathVariable Long id) {
        return ocenaService.findById(id);
    }

    @PostMapping
    public Ocena createOcena(@RequestParam int wartosc,
                             @RequestParam String komentarz,
                             @RequestParam Long ksiazkaId,
                             @RequestParam Long czytelnikId) {
        Ksiazka ksiazka = ksiazkaService.findById(ksiazkaId);
        Czytelnik czytelnik = czytelnikService.findById(czytelnikId);

        Ocena ocena = new Ocena();
        ocena.setWartosc(wartosc);
        ocena.setKomentarz(komentarz);
        ocena.setKsiazka(ksiazka);
        ocena.setCzytelnik(czytelnik);

        return ocenaService.save(ocena);
    }

    @PutMapping("/{id}")
    public Ocena updateOcena(@PathVariable Long id,
                             @RequestParam int wartosc,
                             @RequestParam String komentarz,
                             @RequestParam Long ksiazkaId,
                             @RequestParam Long czytelnikId) {
        Ocena ocena = ocenaService.findById(id);
        ocena.setWartosc(wartosc);
        ocena.setKomentarz(komentarz);
        ocena.setKsiazka(ksiazkaService.findById(ksiazkaId));
        ocena.setCzytelnik(czytelnikService.findById(czytelnikId));

        return ocenaService.save(ocena);
    }

    @DeleteMapping("/{id}")
    public void deleteOcena(@PathVariable Long id) {
        ocenaService.deleteById(id);
    }
}
