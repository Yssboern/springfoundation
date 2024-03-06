package com.learn.springfoundation.member;

import java.time.LocalDateTime;

public record MemberDTO(Long memid,
                        String surname,
                        String firstname,
                        String address,
                        String zipcode,
                        String telephone,
                        Long recommendedby,
                        LocalDateTime joindate) {

}
