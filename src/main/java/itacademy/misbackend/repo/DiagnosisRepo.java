package itacademy.misbackend.repo;

import itacademy.misbackend.entity.helper.Diagnosis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DiagnosisRepo extends JpaRepository<Diagnosis, Long> {

    Diagnosis findByDeletedAtIsNullAndId (Long id);
    List<Diagnosis> findAllByDeletedAtIsNull();

}
