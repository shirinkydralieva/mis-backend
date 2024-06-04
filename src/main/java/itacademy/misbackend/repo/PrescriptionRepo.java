package itacademy.misbackend.repo;

import itacademy.misbackend.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PrescriptionRepo extends JpaRepository<Prescription, Long> {

    Prescription findByDeletedAtIsNullAndId (Long id);
    List<Prescription> findAllByDeletedAtIsNull();

}
