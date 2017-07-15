package com.codeup.repositories;

import com.codeup.models.UserRecipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by roxana on 7/7/17.
 */
@Repository
public interface UserRecipeRepository extends CrudRepository<UserRecipe, Long> {
    public List<UserRecipe> findByUser_Id(long user_id);
    public UserRecipe findByUser_IdAndRecipe_Id(long user_id, long recipe_id);

}
