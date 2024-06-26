package com.trofimov.shop.services;

import com.trofimov.shop.dtos.PositionDto;
import com.trofimov.shop.dtos.ProductDto;
import com.trofimov.shop.dtos.UserDto;
import com.trofimov.shop.entities.Order;
import com.trofimov.shop.entities.OrderPosition;
import com.trofimov.shop.entities.Product;
import com.trofimov.shop.entities.User;
import com.trofimov.shop.repositories.OrdersRepository;
import com.trofimov.shop.repositories.PositionsRepository;
import com.trofimov.shop.repositories.ProductsRepository;
import com.trofimov.shop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopingService {
    private final UserRepository userRepository;
    private final ProductsRepository productsRepository;
    private final PositionsRepository positionsRepository;

    public void deletePosition(Integer posId) {
        positionsRepository.delete(positionsRepository.findById(posId).orElseThrow());
    }

    @Transactional
    public void finishCurrentOrder(Integer userId) {
        User user = userRepository.findFirstById(userId);
        user.getOrders().get(user.getOrders().size()-1).setFinished(true);
    }

    @Transactional
    public void addProductToCurrentOrder(PositionDto dto) {
        User user = userRepository.findFirstById(dto.getUserId());
        Product product = productsRepository.findFirstById(dto.getProductId());

        OrderPosition position = new OrderPosition();
        position.setProduct(product);
        position.setAmount(dto.getAmount());
        position = positionsRepository.save(position);

        Order order = user.getOrders().get(user.getOrders().size()-1);
        if(!order.getFinished()) {
            order.getPositions().add(position);
        } else {
            order = new Order();
            order.setUser(user);
            List<OrderPosition> list = new ArrayList<>();
            list.add(position);
            order.setPositions(list);
        }
    }
}
