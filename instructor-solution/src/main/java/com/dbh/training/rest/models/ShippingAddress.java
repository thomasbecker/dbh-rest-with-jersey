package com.dbh.training.rest.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * ShippingAddress represents the delivery address for an order.
 * 
 * This is an embedded value object demonstrating:
 * - Address validation patterns
 * - Embedded object handling in REST/JSON
 */
public class ShippingAddress {
    
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
    
    // Constructors
    public ShippingAddress() {
    }
    
    public ShippingAddress(String street, String city, String state, 
                          String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }
    
    // Format address for display
    public String getFormattedAddress() {
        StringBuilder sb = new StringBuilder();
        sb.append(street);
        if (addressLine2 != null && !addressLine2.trim().isEmpty()) {
            sb.append(", ").append(addressLine2);
        }
        sb.append(", ").append(city);
        sb.append(", ").append(state);
        sb.append(" ").append(postalCode);
        sb.append(", ").append(country);
        return sb.toString();
    }
    
    // Getters and Setters
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingAddress that = (ShippingAddress) o;
        return Objects.equals(street, that.street) &&
               Objects.equals(addressLine2, that.addressLine2) &&
               Objects.equals(city, that.city) &&
               Objects.equals(state, that.state) &&
               Objects.equals(postalCode, that.postalCode) &&
               Objects.equals(country, that.country);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(street, addressLine2, city, state, postalCode, country);
    }
    
    @Override
    public String toString() {
        return "ShippingAddress{" +
               "street='" + street + '\'' +
               ", city='" + city + '\'' +
               ", state='" + state + '\'' +
               ", postalCode='" + postalCode + '\'' +
               ", country='" + country + '\'' +
               '}';
    }
}