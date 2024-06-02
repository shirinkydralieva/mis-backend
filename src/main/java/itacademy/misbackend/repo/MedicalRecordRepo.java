package itacademy.misbackend.repo;

import itacademy.misbackend.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordRepo extends JpaRepository <MedicalRecord, Long> {

    MedicalRecord findByDeletedAtIsNullAndId (Long id);
    List<MedicalRecord> findAllByDeletedAtIsNull();

}
