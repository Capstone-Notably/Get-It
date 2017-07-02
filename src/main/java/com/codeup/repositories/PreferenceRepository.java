package com.codeup.repositories;

import com.codeup.models.Preference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by roxana on 7/2/17.
 */
@Repository
public interface PreferenceRepository extends CrudRepository<Preference, Long> {
}
