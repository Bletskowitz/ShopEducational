package com.trofimov.shop.services;

import com.trofimov.shop.dtos.PositionDto;
import com.trofimov.shop.entities.Order;
import com.trofimov.shop.entities.OrderPosition;
import com.trofimov.shop.entities.Product;
import com.trofimov.shop.entities.User;
import com.trofimov.shop.repositories.OrdersRepository;
import com.trofimov.shop.repositories.PositionsRepository;
import com.trofimov.shop.repositories.ProductsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopingService {
    private final AuthService authService;
    private final ProductsRepository productsRepository;
    private final PositionsRepository positionsRepository;
    private final OrdersRepository ordersRepository;

    public void deletePosition(Integer posId) {
        positionsRepository.delete(positionsRepository.findById(posId).orElseThrow());
    }

    @Transactional
    public void finishCurrentUsersOrder(Integer orderId) {
        User user = authService.getAuthenticatedUser();
        user.getOrders().stream().map((order) -> {if(order.getId().equals(orderId)){order.setFinished(true);} return order;}).close();
    }

    public void addProductToCurrentOrder(PositionDto dto) {
        Product product = productsRepository.findFirstById(dto.getProductId());

        OrderPosition position = new OrderPosition();
        position.setProduct(product);
        position.setAmount(dto.getAmount());
        position = positionsRepository.save(position);

        Order order = ordersRepository.findById(dto.getOrderId()).orElse(new Order());
        if(!order.getFinished() && order.getId() != null) {
            order.getPositions().add(position);
            ordersRepository.save(order);
        } else {
            order = new Order();
            order.setUser(authService.getAuthenticatedUser());
            List<OrderPosition> list = new ArrayList<>();
            list.add(position);
            order.setPositions(list);
            ordersRepository.save(order);
        }
    }
}
