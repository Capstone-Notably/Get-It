package com.codeup.repositories;

import com.codeup.models.UserGList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by roxana on 7/7/17.
 */
@Repository
public interface UserGListRepository extends CrudRepository<UserGList, Long> {
    public List<UserGList> findByUser_Id(long user_id);
}
