package com.example.demo1.service;

import com.example.demo1.model.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAllAddresss();

    Address getAddressById(Long id);

    Address createAddress(Address address);

    boolean updateAddress(Address address, Long id);

    boolean deleteAddress(Long id);

    List<Address> getAllByUserId(Long id);
}
