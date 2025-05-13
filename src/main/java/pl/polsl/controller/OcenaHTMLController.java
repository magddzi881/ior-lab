package pl.polsl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.polsl.model.*;
import pl.polsl.service.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OcenaHTMLController {

    private final OcenaService ocenaService;
    private final KsiazkaService ksiazkaService;
    private final CzytelnikService czytelnikService;

    public OcenaHTMLController(OcenaService ocenaService, KsiazkaService ksiazkaService, CzytelnikService czytelnikService) {
        this.ocenaService = ocenaService;
        this.ksiazkaService = ksiazkaService;
        this.czytelnikService = czytelnikService;
    }

    @GetMapping("/oceny")
    public String listOceny(Model model) {
        model.addAttribute("oceny", ocenaService.findAll());
        return "oceny";
    }

    @GetMapping("/oceny/nowa")
    public String showAddForm(Model model) {
        model.addAttribute("ksiazki", ksiazkaService.findAll());
        model.addAttribute("czytelnicy", czytelnikService.findAll());
        return "nowa-ocena";
    }

    @PostMapping("/oceny")
    public String saveOcena(
            @RequestParam("wartosc") int wartosc,
            @RequestParam("komentarz") String komentarz,
            @RequestParam("ksiazka") Long ksiazkaId,
            @RequestParam("czytelnik") Long czytelnikId
    ) {
        Ocena ocena = new Ocena();
        ocena.setWartosc(wartosc);
        ocena.setKomentarz(komentarz);
        ocena.setKsiazka(ksiazkaService.findById(ksiazkaId));
        ocena.setCzytelnik(czytelnikService.findById(czytelnikId));
        ocenaService.save(ocena);
        return "redirect:/oceny";
    }

    @GetMapping("/oceny/edycja/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("ocena", ocenaService.findById(id));
        model.addAttribute("ksiazki", ksiazkaService.findAll());
        model.addAttribute("czytelnicy", czytelnikService.findAll());
        return "edycja-ocena";
    }

    @PostMapping("/oceny/edycja/{id}")
    public String updateOcena(
            @PathVariable Long id,
            @RequestParam("wartosc") int wartosc,
            @RequestParam("komentarz") String komentarz,
            @RequestParam("ksiazka") Long ksiazkaId,
            @RequestParam("czytelnik") Long czytelnikId
    ) {
        Ocena ocena = ocenaService.findById(id);
        ocena.setWartosc(wartosc);
        ocena.setKomentarz(komentarz);
        ocena.setKsiazka(ksiazkaService.findById(ksiazkaId));
        ocena.setCzytelnik(czytelnikService.findById(czytelnikId));
        ocenaService.save(ocena);
        return "redirect:/oceny";
    }

    @GetMapping("/oceny/usun/{id}")
    public String deleteOcena(@PathVariable Long id) {
        ocenaService.deleteById(id);
        return "redirect:/oceny";
    }
}
