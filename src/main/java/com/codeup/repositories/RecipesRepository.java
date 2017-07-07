package com.codeup.repositories;

import com.codeup.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by roxana on 7/7/17.
 */
@Repository
public interface RecipesRepository extends CrudRepository<Recipe, Long> {
}
