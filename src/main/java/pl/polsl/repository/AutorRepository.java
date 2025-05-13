package pl.polsl.repository;

import pl.polsl.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.projection.AutorView;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<AutorView> findAllProjectedBy();

}
