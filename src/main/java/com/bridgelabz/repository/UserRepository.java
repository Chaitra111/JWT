package com.bridgelabz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.bridgelabz.model.User;

/**
 * @author Chaitra Ankolekar
 * Date : 10/07/2018
 * Purpose :UserRepository interface which extends MongoRepository which already have built-in all CRUD operations
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

}