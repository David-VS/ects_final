package be.ehb.ects.controllers;

import be.ehb.ects.dao.CourseContentDAO;
import be.ehb.ects.dao.CourseDAO;
import be.ehb.ects.entities.Course;
import be.ehb.ects.entities.CourseContent;
import be.ehb.ects.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class DetailsController {

    private CourseDAO courseDAO;
    private CourseContentDAO contentDAO;

    @Autowired
    public DetailsController(CourseDAO courseDAO, CourseContentDAO contentDAO){
        this.courseDAO = courseDAO;
        this.contentDAO = contentDAO;
    }

    @ModelAttribute("content")
    private CourseContent content(){
        return new CourseContent();
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String showDetailsForID(ModelMap map, @PathVariable(value = "id") int id){
        Optional<Course> foundCourse = courseDAO.findById(id);
        if(foundCourse.isPresent()){
            map.addAttribute("course", foundCourse.get());
            return "details";
        }else{
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = { "/details/{id}"}, method = RequestMethod.POST)
    public String saveCourse(@ModelAttribute("content") @Valid CourseContent newContent,
                             BindingResult bindingResult,
                             ModelMap map,
                             @PathVariable(value = "id") int id){

        Optional<Course> foundCourse = courseDAO.findById(id);
        if(foundCourse.isPresent()) {
            Course course = foundCourse.get();

            if(bindingResult.hasErrors())
                return "/details";

            newContent.setCourse(course);
            newContent.setId(0);
            contentDAO.save(newContent);
        }
        return "redirect:/details/" + id;
    }
}
