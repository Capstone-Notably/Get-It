package com.codeup.repositories;

import com.codeup.models.Recipe;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by roxana on 7/7/17.
 */
@Repository
public interface RecipesRepository extends CrudRepository<Recipe, Long> {
    public List<Recipe> findByUser_Id(long user_id);
    public Recipe findByUser_IdAndName(long user_id, String name);
}
