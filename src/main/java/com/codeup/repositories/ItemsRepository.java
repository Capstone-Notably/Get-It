package com.codeup.repositories;

import com.codeup.models.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by roxana on 6/27/17.
 */
@Repository
public interface ItemsRepository  extends CrudRepository<Item, Long>{
    public Iterable<Item> findByCategory_Id(long category_id);
}
