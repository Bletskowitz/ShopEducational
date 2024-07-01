package com.trofimov.shop.services;

import com.trofimov.shop.dtos.OrderDto;
import com.trofimov.shop.dtos.PositionDto;
import com.trofimov.shop.dtos.UserDto;
import com.trofimov.shop.entities.Role;
import com.trofimov.shop.entities.User;
import com.trofimov.shop.enums.UserRole;
import com.trofimov.shop.repositories.RoleRepository;
import com.trofimov.shop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    public void createNewUser(UserDto userDto) {
        User entity = new User();
        entity.setUsername(userDto.getUsername());
        entity.setPassword(encoder.encode(userDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findFirstByName(UserRole.CUSTOMER));
        entity.setRoles(roles);
        userRepository.save(entity);
    }

    public void grantUserAuthorities(UserDto userDto) {
        User user = userRepository.findFirstById(userDto.getId());
        Role newRole = roleRepository.findFirstByName(UserRole.valueOf(userDto.getRole()));
        user.getRoles().add(newRole);
        userRepository.save(user);
    }

    public List<OrderDto> getAllOrders(User user) {
        return user.getOrders().stream().map((ord) -> {
            OrderDto dto = new OrderDto();
            dto.setId(ord.getId());
            dto.setPositions(ord.getPositions().stream().map((pos) -> {
                PositionDto positionDto = new PositionDto(ord.getId(), pos.getProduct().getId(), pos.getAmount());
                return positionDto;
            }).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    public User findUserByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }
}
