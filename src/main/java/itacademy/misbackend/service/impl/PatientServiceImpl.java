package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.PatientDto;
import itacademy.misbackend.entity.Address;
import itacademy.misbackend.entity.Passport;
import itacademy.misbackend.entity.Patient;
import itacademy.misbackend.mapper.AddressMapper;
import itacademy.misbackend.mapper.PassportMapper;
import itacademy.misbackend.mapper.PatientMapper;
import itacademy.misbackend.repo.AddressRepo;
import itacademy.misbackend.repo.PassportRepo;
import itacademy.misbackend.repo.PatientRepo;
import itacademy.misbackend.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepo patientRepo;
    private final PatientMapper patientMapper;
    private final AddressRepo addressRepo;
    private final PassportRepo passportRepo;
    private final AddressMapper addressMapper;
    private final PassportMapper passportMapper;

    @Override
    public PatientDto create(PatientDto patientDto) {
        Patient patient = patientMapper.toEntity(patientDto);
        if (patientDto.getAddress() != null) {
            addressRepo.save(patient.getAddress());
        }
        if (patientDto.getPassport() != null) {
            passportRepo.save(patient.getPassport());
        }
        return patientMapper.toDto(patient);
    }

    @Override
    public PatientDto getById(Long id) {
        Patient patient = patientRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);
        if (patient == null) {
            return null;
        }
        return patientMapper.toDto(patient);
    }

    @Override
    public List<PatientDto> getAll() {
        return patientMapper.toDtoList(patientRepo.findAllByDeletedAtIsNullAndDeletedByIsNull());
    }

    @Override
    public PatientDto update(Long id, PatientDto patientDto) {
        Patient patient = patientRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);
        if (patient != null){
            if (patientDto.getFullName() != null){
                patient.setFullName(patientDto.getFullName());
            }
            if (patientDto.getDateOfBirth() != null){
                patient.setDateOfBirth(patientDto.getDateOfBirth());
            }
            if (patientDto.getGender() != null){
                patient.setGender(patientDto.getGender());
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
            if (patientDto.getPhoneNumber() != null){
                patient.setPhoneNumber(patientDto.getPhoneNumber());
            }
            if (patientDto.getPlaceOfWork() != null){
                patient.setPlaceOfWork(patientDto.getPlaceOfWork());
            }
            patient = patientRepo.save(patient);
            return patientMapper.toDto(patient);
        }
        return null;
    }

    @Override
    public String delete(Long id) {
        Patient patient = patientRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (patient != null) {
            patient.setDeletedAt(LocalDateTime.now());
         //   patient.setDeletedBy(authentication.getName());
            return "Пациент с id " + id + " удален";
        }
        return "Пациент с id " + id + " не найден";
    }
}
