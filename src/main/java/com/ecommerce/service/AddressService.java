package com.ecommerce.service;

import com.ecommerce.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO addAddress(String email, AddressDTO dto);
    List<AddressDTO> getUserAddresses(String email);
    AddressDTO updateAddress(String email, Long id, AddressDTO dto);
    void deleteAddress(String email, Long id);
}
