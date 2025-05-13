package pl.polsl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.polsl.model.Autor;
import pl.polsl.service.AutorService;

import java.util.List;

@RestController
@RequestMapping("/api/autorzy")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public Page<Autor> getAllAutorzy(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "nazwisko,asc") String sort
    ) {
        String[] sortParams = sort.split(",");
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0])
        );
        return autorService.findAll(pageable);
    }


    @GetMapping("/{id}")
    public Autor getAutor(@PathVariable Long id) {
        return autorService.findById(id);
    }

    @PostMapping
    public Autor createAutor(@RequestBody Autor autor) {
        return autorService.save(autor);
    }

    @PutMapping("/{id}")
    public Autor updateAutor(@PathVariable Long id, @RequestBody Autor updated) {
        Autor autor = autorService.findById(id);
        autor.setImie(updated.getImie());
        autor.setNazwisko(updated.getNazwisko());
        return autorService.save(autor);
    }

    @DeleteMapping("/{id}")
    public void deleteAutor(@PathVariable Long id) {
        autorService.deleteById(id);
    }
}
