package assign.resources;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import assign.domain.ErrorHandler;
import assign.domain.Meeting;
import assign.domain.Meetings;
import assign.domain.Project;
import assign.domain.Projects;
import assign.services.OpenStackMeetingsService;
import assign.services.OpenStackMeetingsServiceImpl;

@Path("/projects")
public class OpenStackMeetingsResource {
	
	// Placeholder for OpenStackMeetings service;
	OpenStackMeetingsService osmService;
	
	// Example course that will be returned;
	String link = "http://eavesdrop.openstack.org/meetings";
	Meeting meeting1;
	
	public OpenStackMeetingsResource() {
		this.osmService = new OpenStackMeetingsServiceImpl();
//		this.meeting1 = new Meeting();
//		//this.meeting1.setDepartment("CS");
//		this.meeting1.setYear("2014");
	}
	
	// Use this for unit testing
	protected void setOpenStackMeetingsService(OpenStackMeetingsService osmService) {
		this.osmService = osmService;
	}
	
	// Default landing page for /projects - shows all projects
	@GET
	@Path("")
	@Produces("application/xml")
	public Projects getAllProjects() throws Exception {
		//String link = "http://eavesdrop.openstack.org/meetings";
		String value = "";
		
		Projects projects = new Projects();
		List<String> projectList = osmService.getData(link, value);
		projects.setProjects(projectList);
		
		return projects;	    
	}	
	
	// return the meetings for a specific project
	@GET
	@Path("/{project_id}/meetings")
	@Produces("application/xml")
	public Meetings getMeeting(@PathParam("project_id") String project_id) {
		Meetings meetings = new Meetings();
		//Projects project = new Projects();
		List<String> meetingList = osmService.getData(link, project_id);
		if(meetingList == null) {
			//error();
//			Meetings x = new Meetings();
//			x.setName("Project " + project_id + " does not exist");
//			return x;
		}
		meetings.setMeetings(meetingList);
		
		// This method uses resteasy's JAXB provider for marshalling the response
		return meetings;
	}
	
	
	@GET
	@Produces("application/xml")
	public ErrorHandler error() {
		ErrorHandler notFound = new ErrorHandler();
		notFound.setError("Project not found");
		
		return notFound;
	}
	
//	@GET
//	@Path("/courses")
//	@Produces("application/xml")
//	public Meetings getCourses() {
//		Meeting modernWebApps = new Meeting();
//		modernWebApps.setName("CS");
//		modernWebApps.setYear("Modern Web Applications");
//		
//		Meeting operatingSystems = new Meeting();
//		operatingSystems.setName("CS");
//		operatingSystems.setYear("Operating Systems");
//		
//		final Meetings meetings = new Meetings();
//		List<Meeting> courseList = new ArrayList<Meeting>();
//		courseList.add(modernWebApps);
//		courseList.add(operatingSystems);
//		meetings.setMeetingsList(courseList);
//
//		return meetings;
//	}
	
//	@GET
//	@Path("/courses1")
//	@Produces("application/xml")
//	public StreamingOutput getAllCourses() throws Exception {
//		Meeting modernWebApps = new Meeting();
//		modernWebApps.setDepartment("CS");
//		modernWebApps.setName("Modern Web Applications");
//		
//		Meeting operatingSystems = new Meeting();
//		operatingSystems.setDepartment("CS");
//		operatingSystems.setName("Operating Systems");
//		
//		final Meetings courses = new Meetings();
//		List<Meeting> courseList = new ArrayList<Meeting>();
//		courseList.add(modernWebApps);
//		courseList.add(operatingSystems);
//		courses.setCourseList(courseList);
//		
//	    return new StreamingOutput() {
//	         public void write(OutputStream outputStream) throws IOException, WebApplicationException {
//	            outputCourses(outputStream, courses);
//	         }
//	      }; 
//	}
	
	
	@GET
	@Path("/project")
	@Produces("application/xml")
	public StreamingOutput getProject() throws Exception {
		final Project heat = new Project();
		heat.setName("%23heat");
		
		heat.setLink(new ArrayList<String>());
		heat.getLink().add("l1");
		heat.getLink().add("l2");		
			    
	    return new StreamingOutput() {
	         public void write(OutputStream outputStream) throws IOException, WebApplicationException {
	            outputProject(outputStream, heat);
	         }
	      };	    
	}		
	
	protected void outputCourses(OutputStream os, Meetings courses) throws IOException {
		try { 
			JAXBContext jaxbContext = JAXBContext.newInstance(Meetings.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(courses, os);
		} catch (JAXBException jaxb) {
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}
	
	protected void outputProjects(OutputStream os, Projects projects) throws IOException {
		try { 
			JAXBContext jaxbContext = JAXBContext.newInstance(Projects.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(projects, os);
		} catch (JAXBException jaxb) {
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}	
	
	protected void outputProject(OutputStream os, Project project) throws IOException {
		try { 
			JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(project, os);
		} catch (JAXBException jaxb) {
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}
}