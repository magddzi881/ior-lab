package pl.polsl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.polsl.model.Czytelnik;
import pl.polsl.service.CzytelnikService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CzytelnikHTMLController {

    private final CzytelnikService czytelnikService;

    public CzytelnikHTMLController(CzytelnikService czytelnikService) {
        this.czytelnikService = czytelnikService;
    }

    @GetMapping("/czytelnicy")
    public String listCzytelnicy(Model model) {
        List<Czytelnik> czytelnicy = czytelnikService.findAll();
        model.addAttribute("czytelnicy", czytelnicy);
        return "czytelnicy";
    }

    @GetMapping("/czytelnicy/nowy")
    public String showAddForm(Model model) {
        model.addAttribute("czytelnik", new Czytelnik());
        return "nowy-czytelnik";
    }

    @PostMapping("/czytelnicy")
    public String saveCzytelnik(@ModelAttribute Czytelnik czytelnik) {
        czytelnikService.save(czytelnik);
        return "redirect:/czytelnicy";
    }

    @GetMapping("/czytelnicy/edycja/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Czytelnik czytelnik = czytelnikService.findById(id);
        model.addAttribute("czytelnik", czytelnik);
        return "edycja-czytelnik";
    }

    @PostMapping("/czytelnicy/edycja/{id}")
    public String updateCzytelnik(@PathVariable Long id, @ModelAttribute Czytelnik czytelnik) {
        czytelnik.setId(id);
        czytelnikService.save(czytelnik);
        return "redirect:/czytelnicy";
    }

    @GetMapping("/czytelnicy/usun/{id}")
    public String deleteCzytelnik(@PathVariable Long id) {
        czytelnikService.deleteById(id);
        return "redirect:/czytelnicy";
    }

    @GetMapping("/czytelnicy/wyszukaj")
    public String searchCzytelnicy(@RequestParam(required = false) String nazwisko, Model model) {
        List<Czytelnik> czytelnicy = null;

        if (nazwisko != null && !nazwisko.isEmpty()) {
            czytelnicy = czytelnikService.findByNazwisko(nazwisko);
        }

        model.addAttribute("czytelnicy", czytelnicy);
        model.addAttribute("nazwisko", nazwisko);

        return "czytelnik-search";
    }
}
