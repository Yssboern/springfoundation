package com.learn.epam.springfoundation.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//memid;surname;firstname;address;zipcode;telephone;recommendedby;joindate
@Entity
@Table(schema = "cd", name = "members")
public class MemberEntity {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public MemberEntity(Long memid, String surname, String firstname, String address, String zipcode, String telephone, Long recommendedby, LocalDateTime joindate) {
        this.memid = memid;
        this.surname = surname;
        this.firstname = firstname;
        this.address = address;
        this.zipcode = zipcode;
        this.telephone = telephone;
        this.recommendedby = recommendedby;
        this.joindate = joindate;
    }

    public MemberEntity(String[] columns) {

        this.memid = columns[0].equals("") ? null : Long.valueOf(columns[0]);
        this.surname = columns[1];
        this.firstname = columns[2];
        this.address = columns[3];
        this.zipcode = columns[4];
        this.telephone = columns[5];
        this.recommendedby = columns[6].isBlank() ? null : Long.valueOf(columns[6]);
        this.joindate = LocalDateTime.parse(columns[7], formatter);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memid", nullable = false)
    private Long memid;

    private String surname;
    private String firstname;
    private String address;
    private String zipcode;
    private String telephone;
    private Long recommendedby;
    private LocalDateTime joindate;

    public Long getMemid() {
        return memid;
    }

    public void setMemid(Long memid) {
        this.memid = memid;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getRecommendedby() {
        return recommendedby;
    }

    public void setRecommendedby(Long recommendedby) {
        this.recommendedby = recommendedby;
    }

    public LocalDateTime getJoindate() {
        return joindate;
    }

    public void setJoindate(LocalDateTime joindate) {
        this.joindate = joindate;
    }

    public MemberEntity() {

    }
}
