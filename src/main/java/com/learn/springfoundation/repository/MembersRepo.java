package com.learn.springfoundation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersRepo extends CrudRepository<Member, Long> {

    Page<Member> findAll(Pageable pageable);
}
