package pl.polsl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.polsl.model.Autor;
import pl.polsl.model.Ksiazka;
import pl.polsl.service.AutorService;
import pl.polsl.service.KsiazkaService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class KsiazkaHTMLController {

    private final KsiazkaService ksiazkaService;
    private final AutorService autorService;

    public KsiazkaHTMLController(KsiazkaService ksiazkaService, AutorService autorService) {
        this.ksiazkaService = ksiazkaService;
        this.autorService = autorService;
    }

    @GetMapping("/ksiazki")
    public String listKsiazki(Model model) {
        List<Ksiazka> ksiazki = ksiazkaService.findAll();
        model.addAttribute("ksiazki", ksiazki);
        return "ksiazki";
    }

    @GetMapping("/ksiazki/nowa")
    public String showAddForm(Model model) {
        model.addAttribute("ksiazka", new Ksiazka());
        model.addAttribute("autorzy", autorService.findAll());
        return "nowa-ksiazka";
    }

    @PostMapping("/ksiazki")
    public String saveKsiazka(@RequestParam("autor") Long autorId, @ModelAttribute Ksiazka ksiazka) {
        ksiazka.setAutor(autorService.findById(autorId));
        ksiazkaService.save(ksiazka);
        return "redirect:/ksiazki";
    }


    @GetMapping("/ksiazki/edycja/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Ksiazka ksiazka = ksiazkaService.findById(id);
        model.addAttribute("ksiazka", ksiazka);
        model.addAttribute("autorzy", autorService.findAll());
        return "edycja-ksiazka";
    }

    @PostMapping("/ksiazki/edycja/{id}")
    public String updateKsiazka(@PathVariable Long id, @RequestParam("autor") Long autorId, @ModelAttribute Ksiazka ksiazka) {
        ksiazka.setId(id);
        ksiazka.setAutor(autorService.findById(autorId));
        ksiazkaService.save(ksiazka);
        return "redirect:/ksiazki";
    }


    @GetMapping("/ksiazki/usun/{id}")
    public String deleteKsiazka(@PathVariable Long id) {
        ksiazkaService.deleteById(id);
        return "redirect:/ksiazki";
    }
}
