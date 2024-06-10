package itacademy.misbackend.service;

import itacademy.misbackend.dto.MedCardDto;

import java.util.List;

public interface MedCardService {
  //  MedCardDto create (MedCardDto recordDto);

    MedCardDto getById (Long id);

    List<MedCardDto> getAll();

    MedCardDto update (Long id, MedCardDto dto);

    String delete (Long id);


}
