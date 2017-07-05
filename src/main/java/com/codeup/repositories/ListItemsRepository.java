package com.codeup.repositories;

import com.codeup.models.ListItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by roxana on 7/3/17.
 */
@Repository
public interface ListItemsRepository extends CrudRepository<ListItem, Long> {
    public List<ListItem> findByGlist_Id(long glist_id);
}
