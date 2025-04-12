package br.com.xxzidanilloxx.authapi.service;

import br.com.xxzidanilloxx.authapi.dto.PartnerRequestDTO;
import br.com.xxzidanilloxx.authapi.dto.PartnerResponseDTO;
import br.com.xxzidanilloxx.authapi.entity.Partner;
import br.com.xxzidanilloxx.authapi.mapper.PartnerMapper;
import br.com.xxzidanilloxx.authapi.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;

    @Transactional
    public PartnerResponseDTO savePartner(PartnerRequestDTO data) {
        Partner partner = Partner.toEntity(data);
        Partner result = partnerRepository.save(partner);
        return partnerMapper.toDTO(result);
    }

    @Transactional(readOnly = true)
    public List<PartnerResponseDTO> getAllPartners(){
        List<Partner> result = partnerRepository.findAll();
        return result.stream().map(partnerMapper::toDTO).toList();
    }

    @Transactional
    public PartnerResponseDTO updatePartner(Long id, PartnerRequestDTO data) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partner not found"));
        partner.setName(data.name());
        partner.setEmail(data.email());
        partner.setWebsite(data.website());
        Partner result = partnerRepository.save(partner);
        return partnerMapper.toDTO(result);
    }

    @Transactional
    public void deletePartner(Long id) {
        Partner result = partnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partner not found"));
        partnerRepository.delete(result);
    }
}
