package com.kenzie.chat.webapi.repositories;

import com.kenzie.chat.webapi.repositories.model.CommentRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface CommentRepository extends CrudRepository<CommentRecord, String> {
    List<CommentRecord> findByOwner(String owner);
}
