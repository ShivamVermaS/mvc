package com.example.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.emp.domain.Student;
import com.example.emp.service.StudentService;

@Controller
public class StudentController {
	
	 @Autowired
	    private StudentService service;

	    @GetMapping("/")
	    public String viewHomePage(Model model) {
	    	try
	    	{
	    		List<Student> liststudent = service.listAll();
		        model.addAttribute("liststudent", liststudent);
		        System.out.print("Get / ");	
	    	}
	        catch (Exception j)
	    	{
	        	System.out.println(j);
	    	}
	        return "index";
	    }

	    @GetMapping("/new")
	    public String add(Model model) {
	    	try
	    	{
	    		 model.addAttribute("student", new Student());
	    	}
	    	catch(Exception k)
	    	{
	    		System.out.println(k);
	    	}
	       
	        return "new";
	    }

	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String saveStudent(@ModelAttribute("student") Student std) {
	    	try 
	    	{
	    		service.save(std);
	    	}
	    	catch(Exception l)
	    	{
	    		System.out.println();	 
	    	}
	        return "redirect:/";
	    }

	    @RequestMapping("/edit/{id}")
	    public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
	        ModelAndView mav = new ModelAndView("new");
	        Student std = service.get(id);
	        mav.addObject("student", std);
	        return mav;
	        
	    }
	    @RequestMapping("/delete/{id}")
	    public String deletestudent(@PathVariable(name = "id") int id) {
	    	try 
	    	{
	    		 service.delete(id);
	    	}
	       catch (Exception e)
	    	{
			System.out.println(e);
	    	}
	        return "redirect:/";
	    }
}
