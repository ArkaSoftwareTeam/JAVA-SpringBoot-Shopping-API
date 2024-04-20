package app.shopapi.Services.AddressServices;

import app.shopapi.DTOs.AddressDTO;
import app.shopapi.Models.Address;
import java.util.List;

public interface IAddressService {
    AddressDTO createAddress(AddressDTO addressDTO);

    List<AddressDTO> getAddresses();

    AddressDTO getAddress(Long addressId);

    AddressDTO updateAddress(Long addressId, Address address);

    String deleteAddress(Long addressId);
}
