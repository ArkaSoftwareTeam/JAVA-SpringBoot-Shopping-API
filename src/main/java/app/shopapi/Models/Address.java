package app.shopapi.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 5 , message = "Street name must contain at least 5 characters")
    private String street;
    @NotBlank
    @Size(min = 5 , message = "Building name must contain at least 5 characters")
    private String buildingName;
    @NotBlank
    @Size(min = 4 , message = "City name must contain at least 4 characters")
    private String city;
    @NotBlank
    @Size(min = 2 , message = "State name must contain at least 2 characters")
    private String state;
    @NotBlank
    @Size(min = 2 , message = "Country name must contain at least 2 characters")
    private String country;
    @NotBlank
    @Size(min = 6 , message = "Pin code must contain at least 6 characters")
    private String pinCode;


    @ManyToMany(mappedBy = "addresses")
    private List<User> users=new ArrayList<>();

    public Address(String country, String state, String city, String pincode, String street, String buildingName) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.pinCode = pincode;
        this.street = street;
        this.buildingName = buildingName;
    }
}
