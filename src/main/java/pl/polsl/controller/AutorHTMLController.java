package pl.polsl.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.polsl.model.Autor;
import pl.polsl.service.AutorService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AutorHTMLController {

    private final AutorService autorService;

    public AutorHTMLController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping("/autorzy")
    public String listAutorzy(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "nazwisko,asc") String sort,
            Model model) {

        String[] sortParams = sort.split(",");
        Pageable pageable = org.springframework.data.domain.PageRequest.of(
                page, size, Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0])
        );

        Page<Autor> autorzyPage = autorService.findAll(pageable);
        model.addAttribute("autorzyPage", autorzyPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("sort", sort);
        model.addAttribute("size", size);
        return "autorzy";
    }


    @GetMapping("/autorzy/nowy")
    public String showAddForm(Model model) {
        model.addAttribute("autor", new Autor());
        return "nowy-autor";
    }

    @PostMapping("/autorzy")
    public String saveAutor(@ModelAttribute Autor autor) {
        autorService.save(autor);
        return "redirect:/autorzy";
    }

    @GetMapping("/autorzy/edycja/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Autor autor = autorService.findById(id);
        model.addAttribute("autor", autor);
        return "edycja-autor";
    }

    @PostMapping("/autorzy/edycja/{id}")
    public String updateAutor(@PathVariable Long id, @ModelAttribute Autor autor) {
        autor.setId(id);
        autorService.save(autor);
        return "redirect:/autorzy";
    }

    @GetMapping("/autorzy/usun/{id}")
    public String deleteAutor(@PathVariable Long id) {
        autorService.deleteById(id);
        return "redirect:/autorzy";
    }
}
