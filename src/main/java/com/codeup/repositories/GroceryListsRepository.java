package com.codeup.repositories;

import com.codeup.models.GroceryList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by roxana on 7/3/17.
 */
@Repository
public interface GroceryListsRepository extends CrudRepository<GroceryList, Long> {
}
