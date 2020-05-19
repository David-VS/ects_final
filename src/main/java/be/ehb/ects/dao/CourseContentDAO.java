package be.ehb.ects.dao;

import be.ehb.ects.entities.CourseContent;
import org.springframework.data.repository.CrudRepository;

public interface CourseContentDAO extends CrudRepository<CourseContent, Integer> {
}
