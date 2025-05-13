package pl.polsl.repository;

import pl.polsl.model.Czytelnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CzytelnikRepository extends JpaRepository<Czytelnik, Long> {
}
