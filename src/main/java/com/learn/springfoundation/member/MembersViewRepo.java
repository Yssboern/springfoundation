package com.learn.springfoundation.member;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MembersViewRepo extends CrudRepository<MemberView, Long> {

}
