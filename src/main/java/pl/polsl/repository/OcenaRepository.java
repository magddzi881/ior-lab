package pl.polsl.repository;

import pl.polsl.model.Ocena;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OcenaRepository extends JpaRepository<Ocena, Long> {
}
