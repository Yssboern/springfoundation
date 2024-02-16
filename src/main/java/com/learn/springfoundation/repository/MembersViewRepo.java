package com.learn.springfoundation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersViewRepo extends CrudRepository<MemberView, Long> {

}
