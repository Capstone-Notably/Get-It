package com.codeup.repositories;

import com.codeup.models.UserItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by roxana on 7/1/17.
 */
@Repository
public interface UserItemsRepository extends CrudRepository<UserItem, Long> {
    public List<UserItem> findByUser_Id(long user_id);
    public UserItem findByUser_IdAndItem_Id(long user_id, long item_id);
}
