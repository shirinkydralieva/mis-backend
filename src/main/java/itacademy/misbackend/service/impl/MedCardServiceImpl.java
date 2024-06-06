package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.MedCardDto;
import itacademy.misbackend.entity.MedCard;
import itacademy.misbackend.mapper.MedicalRecordMapper;
import itacademy.misbackend.repo.MedCardRepo;
import itacademy.misbackend.service.MedCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class MedCardServiceImpl implements MedCardService {
    private final MedCardRepo repo;
    private final MedicalRecordMapper recordMapper;

    @Override
    public MedCardDto getById(Long id) {
        MedCard medCard = repo.findByDeletedAtIsNullAndId(id);
        if (medCard == null) {
            throw new NullPointerException("Мед карта с id " + id + " не найдена!");
        }
         MedCardDto medCardDto = MedCardDto.builder()
                 .id(medCard.getId())
                 .patient(medCard.getPatient())
                 .medicalRecords(recordMapper.toDtoList(medCard.getMedicalRecords()))
                 .lastUpdatedBy(medCard.getLastUpdatedBy())
                 .lastUpdatedAt(medCard.getLastUpdatedAt())
                 .build();
        return medCardDto;
    }

    @Override
    public List<MedCardDto> getAll() {
        List<MedCard> medCardList = repo.findAllByDeletedAtIsNull();
        if (medCardList.isEmpty()) {
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
        return medCardDtoList;
    }

    @Override
    public MedCardDto update(Long id, MedCardDto dto) {
        return null;
    }

    @Override
    public String delete(Long id) {
        MedCard medCard = repo.findByDeletedAtIsNullAndId(id);
        if (medCard == null) {
            throw new NullPointerException("Мед карта запись с id " + id + " не найдена!");
        }
        medCard.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        //  record.setDeletedBy(username);

        return "Мед карта удалена";
    }


}
