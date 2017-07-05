package com.codeup.repositories;

import com.codeup.models.UserCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by roxana on 7/2/17.
 */
@Repository
public interface UserCategoryRepository extends CrudRepository<UserCategory, Long> {
    public List<UserCategory> findByUser_Id(long user_id);
}
