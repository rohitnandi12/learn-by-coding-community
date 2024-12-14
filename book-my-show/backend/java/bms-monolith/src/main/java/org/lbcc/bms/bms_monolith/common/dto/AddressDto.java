package org.lbcc.bms.bms_monolith.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lbcc.bms.bms_monolith.common.entity.Address;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    public static AddressDto addressToAddressDto(Address address) {
        return new AddressDto(address.getStreet(), address.getCity(), address.getState(), address.getZipCode(), address.getCountry());
    }

    public static Address addressDtoToAddress(AddressDto addressDto) {
        return new Address(null, addressDto.getStreet(), addressDto.getCity(), addressDto.getState(), addressDto.getZipCode(), addressDto.getCountry());
    }
}