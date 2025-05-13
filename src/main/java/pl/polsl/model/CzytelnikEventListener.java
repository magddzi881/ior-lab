package pl.polsl.model;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PostRemove;
import org.springframework.stereotype.Component;

@Component
public class CzytelnikEventListener {

    @PrePersist
    public void handleBeforeSave(Czytelnik czytelnik) {
        System.out.println("Dodano Czytelnika: " + czytelnik.getImie() + " " + czytelnik.getNazwisko());
    }

    @PostRemove
    public void handleAfterDelete(Czytelnik czytelnik) {
        System.out.println("Usunieto Czytelnika: " + czytelnik.getImie() + " " + czytelnik.getNazwisko());
    }
}
