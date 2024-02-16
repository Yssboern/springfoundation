package com.learn.springfoundation.repository;


import jakarta.persistence.*;

import java.time.LocalDateTime;

//memid;surname;firstname;address;zipcode;telephone;recommendedby;joindate
@Entity
@Table(schema = "tgn", name = "members")
public class MemberView {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "memid", nullable = false)
    private Long memid;

    private String surname;
    private String firstname;

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

    @Override
    public String toString() {
        return memid + ", " +
                surname + ", " +
                firstname;
    }
}
