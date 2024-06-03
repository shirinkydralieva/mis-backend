package itacademy.misbackend.service;

import itacademy.misbackend.dto.PrescriptionDto;

import java.util.List;

public interface PrescriptionService {

    PrescriptionDto create (PrescriptionDto dto);

    PrescriptionDto getById (Long id);

    List<PrescriptionDto> getAll();

    PrescriptionDto update (Long id, PrescriptionDto dto);

    String delete (Long id);
}
