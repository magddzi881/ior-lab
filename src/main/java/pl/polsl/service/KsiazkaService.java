package pl.polsl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.model.Czytelnik;
import pl.polsl.model.Ksiazka;
import pl.polsl.repository.KsiazkaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KsiazkaService {
    private final KsiazkaRepository ksiazkaRepository;

    public KsiazkaService(KsiazkaRepository ksiazkaRepository) {
        this.ksiazkaRepository = ksiazkaRepository;
    }

    public List<Ksiazka> findAll() { return ksiazkaRepository.findAll(); }

    public Ksiazka findById(Long id) {
        return ksiazkaRepository.findById(id).orElseThrow(() -> new RuntimeException("Czytelnik nie znaleziona"));
    }

    public Ksiazka save(Ksiazka ksiazka) { return ksiazkaRepository.save(ksiazka); }

    public void deleteById(Long id) { ksiazkaRepository.deleteById(id); }
}

