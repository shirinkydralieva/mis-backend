package itacademy.misbackend.repo;

import itacademy.misbackend.entity.Diagnosis;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosisRepo extends JpaRepository<Diagnosis, Long> {

    Diagnosis findByDeletedAtIsNullAndId (Long id);
    List<Diagnosis> findAllByDeletedAtIsNull();

}
