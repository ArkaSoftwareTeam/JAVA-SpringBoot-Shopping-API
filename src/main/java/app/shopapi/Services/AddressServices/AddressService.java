package app.shopapi.Services.AddressServices;

import app.shopapi.DTOs.AddressDTO;
import app.shopapi.Exceptions.APIException;
import app.shopapi.Exceptions.ResourceNotFoundException;
import app.shopapi.Models.Address;
import app.shopapi.Models.User;
import app.shopapi.Repositories.IAddressRepository;
import app.shopapi.Repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class AddressService implements IAddressService{

    private final IAddressRepository addressRepo;
    private final IUserRepository userRepo;
    private final ModelMapper modelMapper;


    @Autowired
    public AddressService(IAddressRepository addressRepo, IUserRepository userRepo, ModelMapper modelMapper) {
        this.addressRepo = addressRepo;
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO) {

        String country = addressDTO.getCountry();
        String city = addressDTO.getCity();
        String street = addressDTO.getStreet();
        Address addressFromDB = addressRepo.findByCountryAndCityAndStreet(country, city,street);

        if (addressFromDB != null) {
            throw new APIException("Address already exists with addressId: " + addressFromDB.getAddressId());
        }

        Address address = modelMapper.map(addressDTO, Address.class);

        Address savedAddress = addressRepo.save(address);

        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAddresses() {
        List<Address> addresses = addressRepo.findAll();

        List<AddressDTO> addressDTOs = addresses.stream().map(address -> modelMapper.map(address, AddressDTO.class))
                .collect(Collectors.toList());

        return addressDTOs;
    }

    @Override
    public AddressDTO getAddress(Long addressId) {
        Address address = addressRepo.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));

        return modelMapper.map(address, AddressDTO.class);
    }

    @Override
    public AddressDTO updateAddress(Long addressId, Address address) {
        Address addressFromDB = addressRepo.findByCountryAndCityAndStreet(
                address.getCountry(), address.getCity(), address.getStreet());

        if (addressFromDB == null) {
            addressFromDB = addressRepo.findById(addressId)
                    .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));

            addressFromDB.setCountry(address.getCountry());
            addressFromDB.setState(address.getState());
            addressFromDB.setCity(address.getCity());
            addressFromDB.setPinCode(address.getPinCode());
            addressFromDB.setStreet(address.getStreet());
            addressFromDB.setBuildingName(address.getBuildingName());

            Address updatedAddress = addressRepo.save(addressFromDB);

            return modelMapper.map(updatedAddress, AddressDTO.class);
        } else {
            List<User> users = userRepo.findByAddress(addressId);
            final Address a = addressFromDB;

            users.forEach(user -> user.getAddresses().add(a));

            deleteAddress(addressId);

            return modelMapper.map(addressFromDB, AddressDTO.class);
        }
    }

    @Override
    public String deleteAddress(Long addressId) {
        Address addressFromDB = addressRepo.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));

        List<User> users = userRepo.findByAddress(addressId);

        users.forEach(user -> {
            user.getAddresses().remove(addressFromDB);

            userRepo.save(user);
        });

        addressRepo.deleteById(addressId);

        return "Address deleted succesfully with addressId: " + addressId;
    }
}
