package org.selenium.pom.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BillingAddress {
    private String firstName;
    private String lastName;
    private String addressLineOne;
    private String city;
    private String postalCode;
    private String email;
    private String country;

    private String state;
    private String company;
    private String phone;

    public BillingAddress(String firstName, String lastName, String addressLineOne, String city,
                          String postalCode, String email, String country, String state, String company, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLineOne = addressLineOne;
        this.city = city;
        this.postalCode = postalCode;
        this.email = email;
        this.country = country;
        this.state = state;
        this.company = company;
        this.phone = phone;
    }
}
