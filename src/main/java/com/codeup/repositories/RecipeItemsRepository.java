package com.codeup.repositories;

import com.codeup.models.RecipeItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by roxana on 7/7/17.
 */
@Repository
public interface RecipeItemsRepository extends CrudRepository<RecipeItem, Long> {
}
