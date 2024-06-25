package com.trofimov.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PositionDto {
    private Integer userId;
    private Integer productId;
    private Integer amount;
}
