package pl.polsl.repository;

import pl.polsl.model.Ksiazka;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KsiazkaRepository extends JpaRepository<Ksiazka, Long> {
}
