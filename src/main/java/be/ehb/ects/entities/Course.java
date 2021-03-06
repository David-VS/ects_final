package be.ehb.ects.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 3, max = 40, message = "{validation.name}")
    private String name;
    @OneToMany(mappedBy = "course")
    private List<CourseContent> contents = new ArrayList<>();

    public Course() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CourseContent> getContents() {
        return contents;
    }

    public void setContents(List<CourseContent> contents) {
        this.contents = contents;
    }
}
