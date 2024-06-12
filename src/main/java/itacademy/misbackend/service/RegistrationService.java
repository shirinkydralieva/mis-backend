package itacademy.misbackend.service;

import itacademy.misbackend.dto.UserDoctorRequest;
import itacademy.misbackend.dto.UserPatientRequest;

public interface RegistrationService {
    void registerDoctor(UserDoctorRequest userDoctorRequest);
    void registerPatient(UserPatientRequest userPatientRequest);
    void confirm(String token, Long id);
}
