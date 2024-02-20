package com.learn.springfoundation.repository;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

//memid;surname;firstname;address;zipcode;telephone;recommendedby;joindate
@Entity
@Table(schema = "tgn", name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "memid", nullable = false)
    private Long memid;

    private String surname;
    private String firstname;
    private String address;
    private String zipcode;
    private String telephone;
    private LocalDateTime joindate;
    @ManyToOne(fetch = LAZY)
    private Member recommendedby;

    public Member(Long memid, String surname, String firstname, String address, String zipcode, String telephone, Member recommendedby, LocalDateTime joindate) {
        this.memid = memid;
        this.surname = surname;
        this.firstname = firstname;
        this.address = address;
        this.zipcode = zipcode;
        this.telephone = telephone;
        this.recommendedby = recommendedby;
        this.joindate = joindate;
    }

    public Member() {

    }

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

    public Member getRecommendedby() {
        return recommendedby;
    }

    public void setRecommendedby(Member recommendedby) {
        this.recommendedby = recommendedby;
    }

    public LocalDateTime getJoindate() {
        return joindate;
    }

    public void setJoindate(LocalDateTime joindate) {
        this.joindate = joindate;
    }

    public String recommender() {
        return firstname + " " + surname + " [" + memid + "]";
    }

    @Override
    public String toString() {
        return memid + ", " +
                surname + ", " +
                firstname + ", " +
                address + ", " +
                zipcode + ", " +
                telephone + ", " +
                recommender() + ", " +
                joindate;
    }
}
