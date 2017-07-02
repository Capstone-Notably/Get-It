package com.codeup.repositories;

import com.codeup.models.CustomItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by roxana on 7/1/17.
 */
@Repository
public interface CustomItemsRepository extends CrudRepository<CustomItem, Long> {
}
