package com.example.BanRyeohaedyuo.domain.embedded;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Address {

    private String city;
    private String street;
    private String detailAddress;

    @Builder
    public Address(String city, String street, String detailAddress) {
        this.city = city;
        this.street = street;
        this.detailAddress = detailAddress;
    }
}
