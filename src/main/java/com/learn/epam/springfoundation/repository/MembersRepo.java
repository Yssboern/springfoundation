package com.learn.epam.springfoundation.repository;

import com.learn.epam.springfoundation.entity.MemberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersRepo extends CrudRepository<MemberEntity, Long> {
}
