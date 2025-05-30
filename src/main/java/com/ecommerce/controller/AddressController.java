package com.ecommerce.controller;

import com.ecommerce.dto.AddressDTO;
import com.ecommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<AddressDTO> addAddress(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody AddressDTO dto
    ) {
        return ResponseEntity.ok(addressService.addAddress(userDetails.getUsername(), dto));
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(addressService.getUserAddresses(userDetails.getUsername()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id,
            @RequestBody AddressDTO dto
    ) {
        return ResponseEntity.ok(addressService.updateAddress(userDetails.getUsername(), id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id
    ) {
        addressService.deleteAddress(userDetails.getUsername(), id);
        return ResponseEntity.ok("Address deleted successfully");
    }
}
