package com.shri.ecommercebackend.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* Note spring-mvc has built in support for creating Rest services and Spring-Rest is integrated 
 * with Jackson project. So when we add @RestController and we have the Jackson dependency there in pom.xml
 * then when we get json data from the client it gets automatically converted into java POJO and vice versa.
 * 
 */
@RestController
@RequestMapping("/test")
public class DemoRestController {

	// add code for the "/hello" endpoint
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World!";
	}
	
	@GetMapping("/get-fruits")
	public String[] getFruits() {
		 String fruits[] = {"Apple", "Banana", "Pear"};
		 return fruits;
	}
	
	@GetMapping("/student")
	public Student getStudentInfo() {
		 Student student = new Student("John");
		 student.addHobby("Coding");
		 student.addHobby("Singing");
		 
		 Course course1 = new Course("Data Structures and Algorithms", "CS101", 3);
		 Course course2 = new Course("Operating Systems", "CS103", 4);
		 
		 student.addCourse(course1);
		 student.addCourse(course2);
		 
		 return student;
	}
	
}

class Student {
	
	private String name;
	private List<String> hobbies;
	private List<Course> courses;
	
	// Constructor
    public Student(String name) {
        this.name = name;
        this.hobbies = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for hobbies
    public List<String> getHobbies() {
        return hobbies;
    }
    
    // Getter for courses
    public List<Course> getCourses() {
        return courses;
    }
    

    // Setter for hobbies that takes a String and adds it to the list
    public void addHobby(String hobby) {
        this.hobbies.add(hobby);
    }
	
    // Setter for courses that takes a Course and adds it to the list
    public void addCourse(Course course) {
        this.courses.add(course);
    }
}

class Course {
	
	private String courseName;
    private String courseCode;
    private int credits;

    // Constructor
    public Course(String courseName, String courseCode, int credits) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
    }

    // Getter and setter for courseName
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    // Getter and setter for courseCode
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    // Getter and setter for credits
    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
	
}

