package com.trofimov.shop.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderDto {
    private Integer id;
    private List<PositionDto> positions;
}
