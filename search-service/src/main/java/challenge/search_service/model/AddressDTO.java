package challenge.search_service.model;

import challenge.search_service.domain.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private String street;

    private String city;

    private String state;

    private String zipcode;

    private String country;

    private Double latitude;

    private Double longitude;

    public static AddressDTO mapFromEntity(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.street = address.getStreet();
        addressDTO.city = address.getCity();
        addressDTO.state = address.getState();
        addressDTO.zipcode = address.getZipcode();
        addressDTO.country = address.getCountry();
        addressDTO.latitude = address.getLatitude();
        addressDTO.longitude = address.getLongitude();
        return addressDTO;
    }
}
