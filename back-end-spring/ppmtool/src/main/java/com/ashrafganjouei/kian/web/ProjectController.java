package com.ashrafganjouei.kian.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.FieldError;

import com.ashrafganjouei.kian.domain.Project;
import com.ashrafganjouei.kian.services.MapValidationErrorService;
import com.ashrafganjouei.kian.services.ProjectService;


//What is REST? Web services that conform to the REST architectural style, called RESTful Web services, provide interoperability between computer systems on the internet. Pretty Much a way users interact with our servers through the web.
//ProjectController is pretty much the same thing as an API
//@RestController is a convenience annotation that combines @Controller and @ResponseBody. Very useful for REST APIs
//@RequestMapping Maps Hypertext Transfer Protocol (HTTP) requests to handler methods of MVC and REST controllers.
//What is HTTP? The browser sends an HTTP request message to the server, asking it to send a copy of the website to the client (you go to the shop and order your goods). This message, and all other data sent between the client and the server, is sent across your internet connection using TCP/IP.
//What is an MVC/REST handler? Using a handler mapping you can map incoming web requests to appropriate handlers.
//So we go from user -> site -> server(HTTP) -> ProjectController(Handler)
//If you forget the difference between MVC controllers and Rest controllers --> https://javarevisited.blogspot.com/2017/08/difference-between-restcontroller-and-controller-annotations-spring-mvc-rest.html#axzz6eqSmhh1P
@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid@RequestBody Project project, BindingResult result){
		
		ResponseEntity<?> errorMapEntity = mapValidationErrorService.MapValidationService(result);
		if (errorMapEntity!=null) return errorMapEntity;
		
		
		Project project1 = projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable String projectId){
		Project project = projectService.findProjectByIdentifier(projectId);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	public Iterable<Project> getAllProjects(){
		return projectService.findAllProject();
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId){
		projectService.deleteProjectByIdentifier(projectId.toUpperCase());
		return new ResponseEntity<String>("Project with ID: '"+projectId.toUpperCase()+"' was deleted",HttpStatus.OK);
	}
}
