package com.jpasecurity.repository;

import org.springframework.data.repository.CrudRepository;

import com.jpasecurity.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

}
