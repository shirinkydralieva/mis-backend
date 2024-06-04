package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.AppointmentDto;
import itacademy.misbackend.entity.Appointment;
import itacademy.misbackend.mapper.AppointmentMapper;
import itacademy.misbackend.repo.AppointmentRepo;
import itacademy.misbackend.repo.DoctorRepo;
import itacademy.misbackend.repo.PatientRepo;
import itacademy.misbackend.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final AppointmentMapper appointmentMapper;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;

    @Override
    public AppointmentDto create(AppointmentDto appointmentDto) {
        Appointment appointment = appointmentMapper.toEntity(appointmentDto);
        if (appointmentDto.getDoctorId() != null) {
            appointment.setDoctor(doctorRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(appointmentDto.getDoctorId()));
            //Нужно проверить, добавится ли автоматически у врача в лист прием после его создания
        }
        if (appointmentDto.getPatientId() != null) {
            appointment.setPatient(patientRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(appointmentDto.getPatientId()));
            //То же самое, что и с врачом
        }
        appointment = appointmentRepo.save(appointment);
        return appointmentMapper.toDto(appointment);
    }

    @Override
    public AppointmentDto getById(Long id) {
        Appointment appointment = appointmentRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);
        if (appointment == null) {
            return null;
        }
        return appointmentMapper.toDto(appointment);
    }

    @Override
    public List<AppointmentDto> getAll() {
        return appointmentMapper.toDtoList(appointmentRepo.findAllByDeletedAtIsNullAndDeletedByIsNull());
    }

    @Override
    public AppointmentDto update(Long id, AppointmentDto appointmentDto) {
        Appointment appointment = appointmentRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);
        if (appointment != null) {
            if (appointmentDto.getReason() != null) {
                appointment.setReason(appointmentDto.getReason());
            }
            if (appointmentDto.getNotes() != null) {
                appointment.setNotes(appointmentDto.getNotes());
            }
            if (appointmentDto.getStatus() != null) {
                appointment.setStatus(appointmentDto.getStatus());
            }
            if (appointmentDto.getAppointmentDate() != null) {
                appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
            }
            appointment = appointmentRepo.save(appointment);
            return appointmentMapper.toDto(appointment);
        }
        return null;
    }

    @Override
    public String delete(Long id) {
        Appointment appointment = appointmentRepo.findByDeletedAtIsNullAndDeletedByIsNullAndId(id);

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (appointment != null) {
            appointment.setDeletedAt(LocalDateTime.now());
            //appointment.setDeletedBy(authentication.getName());
            appointmentRepo.save(appointment);
            return "Запись приема с id " + id + " успешно удалена";
        }
        return "Запись приема с id " + id + " не найдена";
    }
}
