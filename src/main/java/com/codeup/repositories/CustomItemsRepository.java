package com.codeup.repositories;

import com.codeup.models.CustomItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by roxana on 7/1/17.
 */
@Repository
public interface CustomItemsRepository extends CrudRepository<CustomItem, Long> {
    public List<CustomItem> findByUser_Id(long user_id);
}
