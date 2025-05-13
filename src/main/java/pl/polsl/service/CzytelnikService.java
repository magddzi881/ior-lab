package pl.polsl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.model.Autor;
import pl.polsl.model.Czytelnik;
import pl.polsl.repository.CzytelnikRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CzytelnikService {
    private final CzytelnikRepository czytelnikRepository;

    public CzytelnikService(CzytelnikRepository czytelnikRepository) {
        this.czytelnikRepository = czytelnikRepository;
    }

    public List<Czytelnik> findAll() { return czytelnikRepository.findAll(); }

    public Czytelnik findById(Long id) {
        return czytelnikRepository.findById(id).orElseThrow(() -> new RuntimeException("Czytelnik nie znaleziony"));
    }

    public Czytelnik save(Czytelnik czytelnik) { return czytelnikRepository.save(czytelnik); }

    public void deleteById(Long id) { czytelnikRepository.deleteById(id); }
}
