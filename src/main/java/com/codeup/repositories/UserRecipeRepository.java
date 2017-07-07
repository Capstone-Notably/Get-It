package com.codeup.repositories;

import com.codeup.models.UserRecipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by roxana on 7/7/17.
 */
@Repository
public interface UserRecipeRepository extends CrudRepository<UserRecipe, Long> {
}
