package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.MedCardDto;
import itacademy.misbackend.entity.MedCard;
import itacademy.misbackend.entity.MedicalRecord;
import itacademy.misbackend.mapper.MedicalRecordMapper;
import itacademy.misbackend.repo.MedCardRepo;
import itacademy.misbackend.service.MedCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class MedCardServiceImpl implements MedCardService {
    private final MedCardRepo repo;
    private final MedicalRecordMapper recordMapper;

    @Override
    public MedCardDto getById(Long id) {
        log.info("СТАРТ: MedCardServiceImpl - getById(). Мед карта с id {}", id);
        MedCard medCard = repo.findByDeletedAtIsNullAndId(id);
        if (medCard == null) {
            log.error("Мед карта с id " + id + " не найдена!");
            throw new NullPointerException("Мед карта не найдена!");
        }
         MedCardDto medCardDto = MedCardDto.builder()
                 .id(medCard.getId())
                 .patient(medCard.getPatient())
                 .medicalRecords(recordMapper.toDtoList(medCard.getMedicalRecords()))
                 .lastUpdatedBy(medCard.getLastUpdatedBy())
                 .lastUpdatedAt(medCard.getLastUpdatedAt())
                 .build();
        log.info("КОНЕЦ: MedCardServiceImpl - getById(). Мед карта - {} ", medCardDto);
        return medCardDto;
    }

    @Override
    public List<MedCardDto> getAll() {
        log.info("СТАРТ: MedCardServiceImpl - getAll()");
        List<MedCard> medCardList = repo.findAllByDeletedAtIsNull();
        if (medCardList.isEmpty()) {
            log.error("Мед карт нет!");
            throw new NullPointerException("Мед карт нет!");
        }
        List<MedCardDto> medCardDtoList = new ArrayList<>();
        for (MedCard medCard : medCardList) {
            MedCardDto medCardDto = MedCardDto.builder()
                    .id(medCard.getId())
                    .patient(medCard.getPatient())
                    .medicalRecords(recordMapper.toDtoList(medCard.getMedicalRecords()))
                    .lastUpdatedBy(medCard.getLastUpdatedBy())
                    .lastUpdatedAt(medCard.getLastUpdatedAt())
                    .build();
            medCardDtoList.add(medCardDto);
        }
        log.info("КОНЕЦ: MedCardServiceImpl - getAll()");
        return medCardDtoList;
    }

    @Override
    public MedCardDto update(Long id, MedCardDto dto) {
        log.info("СТАРТ: MedCardServiceImpl - update(). Мед карта с id {}", id);

        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();*/

        MedCard medCard = repo.findByDeletedAtIsNullAndId(id);
        if (medCard == null) {
            log.error("Мед карта с id " + id + " не найдена!");
            throw new NullPointerException("Мед карта не найдена!");
        }
        log.info("Мед карта найдена. Исходные данные - {}", medCard);

        // что и как обновлять??

        medCard.setLastUpdatedAt(new Timestamp(System.currentTimeMillis()));
        // medCard.setLastUpdatedBy(username);

        repo.save(medCard);

        dto.setLastUpdatedAt(medCard.getLastUpdatedAt());
        dto.setLastUpdatedBy(medCard.getLastUpdatedBy());
        log.info("КОНЕЦ: MedicalRecordServiceImpl - update(). Обновленная мед карта - {}", medCard);
        return dto;
    }

    @Override
    public String delete(Long id) {
        log.info("СТАРТ: MedCardServiceImpl - delete(). Мед карта с id {}", id);
        //   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //   String username = authentication.getName();

        MedCard medCard = repo.findByDeletedAtIsNullAndId(id);
        if (medCard == null) {
            log.error("Мед карта с id " + id + " не найдена!");
            throw new NullPointerException("Мед карта не найдена!");
        }
        medCard.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        //  record.setDeletedBy(username);

        repo.save(medCard);
        log.info("КОНЕЦ: MedCardServiceImpl - delete(). Мед карта (id {}) удалена", id);
        return "Мед карта удалена";
    }


}
