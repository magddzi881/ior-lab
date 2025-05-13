package pl.polsl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.model.Czytelnik;
import pl.polsl.model.Ocena;
import pl.polsl.repository.OcenaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OcenaService {
    private final OcenaRepository ocenaRepository;

    public OcenaService(OcenaRepository ocenaRepository) {
        this.ocenaRepository = ocenaRepository;
    }

    public List<Ocena> findAll() { return ocenaRepository.findAll(); }

    public Ocena findById(Long id) {
        return ocenaRepository.findById(id).orElseThrow(() -> new RuntimeException("Ocena nie znaleziona"));
    }

    public Ocena save(Ocena ocena) { return ocenaRepository.save(ocena); }

    public void deleteById(Long id) { ocenaRepository.deleteById(id); }
}
