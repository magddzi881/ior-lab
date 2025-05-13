package pl.polsl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.polsl.model.Autor;
import pl.polsl.projection.AutorView;
import pl.polsl.repository.AutorRepository;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Page<Autor> findAll(Pageable pageable) {
        return autorRepository.findAll(pageable);
    }

    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    public Autor findById(Long id) {
        return autorRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor nie znaleziony"));
    }

    public List<AutorView> findAllProjected() {
        return autorRepository.findAllProjectedBy();
    }


    public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

    public void deleteById(Long id) {
        autorRepository.deleteById(id);
    }
}
