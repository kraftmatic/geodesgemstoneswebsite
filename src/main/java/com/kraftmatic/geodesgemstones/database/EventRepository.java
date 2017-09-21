package com.kraftmatic.geodesgemstones.database;

import com.kraftmatic.geodesgemstones.models.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long>{
}
