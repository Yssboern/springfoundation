package com.learn.springfoundation.member;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "tgn", name = "members")
class MemberView {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "memid", nullable = false)
    private Long memid;

    private String surname;
    private String firstname;

    @Override
    public String toString() {
        return memid + ", " +
                surname + ", " +
                firstname;
    }
}
