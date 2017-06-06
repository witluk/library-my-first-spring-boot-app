package com.witluk.repository;

import com.witluk.model.Users;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Witek on 02/06/2017.
 */
public interface UserRepository extends CrudRepository<Users, Integer> {

}
