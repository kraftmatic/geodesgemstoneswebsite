package com.kraftmatic.geodesgemstones.database;

import com.kraftmatic.geodesgemstones.models.Announcement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by nkraftor on 2/18/17.
 */

public interface AnnouncementRepository extends CrudRepository<Announcement, Long>{

}
