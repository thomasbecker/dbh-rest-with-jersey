package com.dbh.training.rest.dto;

import com.dbh.training.rest.models.ShippingAddress;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingAddressDTO {
    
    @NotBlank(message = "Street address is required")
    @Size(max = 200, message = "Street address cannot exceed 200 characters")
    private String street;
    
    @Size(max = 100, message = "Address line 2 cannot exceed 100 characters")
    private String addressLine2;
    
    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City cannot exceed 100 characters")
    private String city;
    
    @NotBlank(message = "State/Province is required")
    @Size(max = 100, message = "State/Province cannot exceed 100 characters")
    private String state;
    
    @NotBlank(message = "Postal code is required")
    @Pattern(regexp = "^[A-Za-z0-9\\s-]{3,10}$", 
             message = "Invalid postal code format")
    private String postalCode;
    
    @NotBlank(message = "Country is required")
    @Size(max = 100, message = "Country cannot exceed 100 characters")
    private String country;
    
    private String formattedAddress;
    
    public ShippingAddressDTO() {
    }
    
    public static ShippingAddressDTO fromEntity(ShippingAddress address) {
        if (address == null) {
            return null;
        }
        
        ShippingAddressDTO dto = new ShippingAddressDTO();
        dto.street = address.getStreet();
        dto.addressLine2 = address.getAddressLine2();
        dto.city = address.getCity();
        dto.state = address.getState();
        dto.postalCode = address.getPostalCode();
        dto.country = address.getCountry();
        dto.formattedAddress = address.getFormattedAddress();
        return dto;
    }
    
    public ShippingAddress toEntity() {
        ShippingAddress address = new ShippingAddress();
        address.setStreet(this.street);
        address.setAddressLine2(this.addressLine2);
        address.setCity(this.city);
        address.setState(this.state);
        address.setPostalCode(this.postalCode);
        address.setCountry(this.country);
        return address;
    }
    
    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public String getAddressLine2() {
        return addressLine2;
    }
    
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getPostalCode() {
        return postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getFormattedAddress() {
        return formattedAddress;
    }
    
    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
}