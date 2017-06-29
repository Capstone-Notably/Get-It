package com.codeup.repositories;

import com.codeup.models.Categories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by roxana on 6/28/17.
 */
@Repository
public interface CategoriesRepository extends CrudRepository<Categories, Long> {
}
