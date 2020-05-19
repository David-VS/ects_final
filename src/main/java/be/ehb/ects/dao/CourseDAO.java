package be.ehb.ects.dao;

import be.ehb.ects.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseDAO extends CrudRepository<Course, Integer> {
}
