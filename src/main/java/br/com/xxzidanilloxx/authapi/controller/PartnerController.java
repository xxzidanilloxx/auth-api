package br.com.xxzidanilloxx.authapi.controller;

import br.com.xxzidanilloxx.authapi.dto.PartnerRequestDTO;
import br.com.xxzidanilloxx.authapi.dto.PartnerResponseDTO;
import br.com.xxzidanilloxx.authapi.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @PostMapping
    public ResponseEntity<PartnerResponseDTO> savePartner(@RequestBody PartnerRequestDTO data) {
        PartnerResponseDTO result = partnerService.savePartner(data);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<PartnerResponseDTO>> getAllPartners() {
        List<PartnerResponseDTO> result = partnerService.getAllPartners();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartnerResponseDTO> updatePartner(@PathVariable Long id,
                                                            @RequestBody PartnerRequestDTO data) {
        PartnerResponseDTO result = partnerService.updatePartner(id, data);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartner(@PathVariable Long id) {
        partnerService.deletePartner(id);
        return ResponseEntity.noContent().build();
    }
}
