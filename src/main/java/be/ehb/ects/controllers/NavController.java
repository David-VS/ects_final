package be.ehb.ects.controllers;

import be.ehb.ects.dao.CourseDAO;
import be.ehb.ects.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class NavController {

    private CourseDAO dao;

    @Autowired
    public NavController(CourseDAO  dao){
        this.dao = dao;
    }

    @ModelAttribute("all")
    public Iterable<Course> findAll(){
        return dao.findAll();
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String showIndex(ModelMap map){
        map.addAttribute("new_course", new Course());
        return "index";
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.POST)
    public String saveCourse(@ModelAttribute("new_course") @Valid Course newCourse,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "index";

        dao.save(newCourse);
        return "redirect:/index";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String showAbout(){
        return "about";
    }
}
