package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.PatientDto;
import itacademy.misbackend.entity.MedCard;
import itacademy.misbackend.entity.helper.Address;
import itacademy.misbackend.entity.helper.Passport;
import itacademy.misbackend.entity.Patient;
import itacademy.misbackend.exception.NotFoundException;
import itacademy.misbackend.mapper.AddressMapper;
import itacademy.misbackend.mapper.PassportMapper;
import itacademy.misbackend.mapper.PatientMapper;
import itacademy.misbackend.repo.*;
import itacademy.misbackend.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {
    private final PatientRepo patientRepo;
    private final PatientMapper patientMapper;
    private final AddressRepo addressRepo;
    private final PassportRepo passportRepo;
    private final AddressMapper addressMapper;
    private final PassportMapper passportMapper;
    private final MedicalRecordRepo recordRepo;
    private final UserRepo userRepo;
    private final UserRepo userRepo;
    private final MedCardRepo medCardRepo;

    @Override
    public PatientDto create(PatientDto patientDto) {
        log.info("СТАРТ: PatientServiceImpl - create() {}", patientDto);
        Patient patient = patientMapper.toEntity(patientDto);
        if (patientDto.getAddress() != null) {
            addressRepo.save(patient.getAddress());
        }
        if (patientDto.getPassport() != null) {
            passportRepo.save(patient.getPassport());
        }
        if (userRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(patientDto.getUserId()) == null) {
            throw new NotFoundException("Пользователь с id " + patientDto.getUserId() + " не найден");
        }
        patient.setUser(userRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(patientDto.getUserId()));
        patient = patientRepo.save(patient);

        MedCard medCard = new MedCard();
        medCard.setPatient(patient); // Связываем медицинскую карту с пациентом
        medCardRepo.save(medCard);   // Сохраняем медицинскую карту

        patient.setMedCard(medCard);
        patientRepo.save(patient);
        log.info("КОНЕЦ: PatientServiceImpl - create {} ", patientMapper.toDto(patient));
        return patientMapper.toDto(patient);
    }

    @Override
    public PatientDto getById(Long id) {
        log.info("СТАРТ: PatientServiceImpl - getById({})", id);
        Patient patient = patientRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);
        if (patient == null) {
            log.error("Пациент с id " + id + " не найден!");
            throw new NullPointerException("Пациент не найден!");
        }
        log.info("КОНЕЦ: PatientServiceImpl - getById(). Пациент - {} ", patient);
        return patientMapper.toDto(patient);
    }

    @Override
    public List<PatientDto> getAll() {
        log.info("СТАРТ: PatientServiceImpl - getAll()");
        var patientList = patientRepo.findAllByDeletedAtIsNullAndDeletedByIsNull();
        if (patientList.isEmpty()) {
            log.error("Пациентов нет!");
            throw new NullPointerException("Пациентов нет!");
        }
        log.info("КОНЕЦ: PatientServiceImpl - getAll()");
        return patientMapper.toDtoList(patientList);
    }

    @Override
    public PatientDto update(Long id, PatientDto patientDto) {
        log.info("СТАРТ: PatientServiceImpl - update(). Пациент с id {}", id);
        Patient patient = patientRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);
        if (patient != null){
            if (patientDto.getDateOfBirth() != null){
                patient.setDateOfBirth(patientDto.getDateOfBirth());
            }
            if (patientDto.getSex() != null){
                patient.setSex(patientDto.getSex());
            }
            if (patientDto.getPassport() != null){
                Passport passport = passportRepo.save(passportMapper.toEntity(patientDto.getPassport()));
                patient.setPassport(passport);
            }
            if (patientDto.getTaxId() != null){
                patient.setTaxId(patientDto.getTaxId());
            }
            if (patientDto.getAddress() != null){
                Address address = addressRepo.save(addressMapper.toEntity(patientDto.getAddress()));
                patient.setAddress(address);
            }
            if (patientDto.getPlaceOfWork() != null){
                patient.setPlaceOfWork(patientDto.getPlaceOfWork());
            }
            patient = patientRepo.save(patient);
            log.info("КОНЕЦ: PatientServiceImpl - update(). Обновленная запись - {}", patient);
            return patientMapper.toDto(patient);
        }
        return null;
    }

    @Override
    public String delete(Long id) {
        log.info("СТАРТ: PatientServiceImpl - delete(). Пациент с id {}", id);
        Patient patient = patientRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (patient != null) {
            patient.setDeletedAt(LocalDateTime.now());
            patient.setDeletedBy(authentication.getName());
            return "Пациент с id " + id + " удален";
        }
        log.error("Пациент с id " + id + " не найден!");
        return "Пациент с id " + id + " не найден";
    }
}
