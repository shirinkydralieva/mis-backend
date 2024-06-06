package itacademy.misbackend.controller;

import itacademy.misbackend.dto.MedCardDto;
import itacademy.misbackend.service.MedCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medCards")
public class MedCardController {

    private final MedCardService service;

    @GetMapping()
    public ResponseEntity<List<MedCardDto>> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedCardDto> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
            return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
        }

}
