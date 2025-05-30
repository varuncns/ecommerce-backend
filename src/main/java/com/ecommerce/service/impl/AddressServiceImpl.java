package com.ecommerce.service.impl;

import com.ecommerce.dto.AddressDTO;
import com.ecommerce.entity.Address;
import com.ecommerce.entity.User;
import com.ecommerce.repository.AddressRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public AddressDTO addAddress(String email, AddressDTO dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address address = Address.builder()
                .street(dto.getStreet())
                .city(dto.getCity())
                .state(dto.getState())
                .postalCode(dto.getPostalCode())
                .country(dto.getCountry())
                .type(dto.getType())
                .user(user)
                .build();

        Address saved = addressRepository.save(address);
        return mapToDTO(saved);
    }

    @Override
    public List<AddressDTO> getUserAddresses(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return addressRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO updateAddress(String email, Long id, AddressDTO dto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized access");
        }

        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPostalCode(dto.getPostalCode());
        address.setCountry(dto.getCountry());
        address.setType(dto.getType());

        Address updated = addressRepository.save(address);
        return mapToDTO(updated);
    }

    @Override
    public void deleteAddress(String email, Long id) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized access");
        }

        addressRepository.delete(address);
    }

    private AddressDTO mapToDTO(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .type(address.getType())
                .build();
    }
}
