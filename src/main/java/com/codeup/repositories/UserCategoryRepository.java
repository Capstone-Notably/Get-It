package com.codeup.repositories;

import com.codeup.models.UserCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by roxana on 7/2/17.
 */
@Repository
public interface UserCategoryRepository extends CrudRepository<UserCategory, Long> {
}
