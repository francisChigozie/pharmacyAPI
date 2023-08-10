package com.ironhack.pharmacyapi.mappers;

import com.ironhack.pharmacyapi.dto.OrderDto;
import com.ironhack.pharmacyapi.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.web.bind.annotation.Mapping;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
                MedicationMapper.class
        })
public interface OrderMapper {
    OrderDto toDto(Order order);

    //@Mapping(target = "id", ignore = true)
    Order toEntity(OrderDto orderDto);
}