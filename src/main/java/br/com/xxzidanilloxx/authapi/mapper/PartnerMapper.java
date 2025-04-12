package br.com.xxzidanilloxx.authapi.mapper;

import br.com.xxzidanilloxx.authapi.dto.PartnerResponseDTO;
import br.com.xxzidanilloxx.authapi.entity.Partner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PartnerMapper {
    PartnerResponseDTO toDTO(Partner partner);
}
