package itacademy.misbackend.repo;

import itacademy.misbackend.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepo extends JpaRepository<Prescription, Long> {

    Prescription findByDeletedAtIsNullAndId (Long id);
    List<Prescription> findAllByDeletedAtIsNull();

}
