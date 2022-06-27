package com.donovan.notebook.repository;

import com.donovan.notebook.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
