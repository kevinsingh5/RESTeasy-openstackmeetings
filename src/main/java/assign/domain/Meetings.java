package assign.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "meetings")
@XmlAccessorType(XmlAccessType.FIELD)
public class Meetings { 

    private List<String> year = null;

    public List<String> getMeetings() {
        return year;
    }
 
    public void setMeetings(List<String> meetings) {
        this.year = meetings;
    }
}


/*
@XmlRootElement(name = "abc")
@XmlAccessorType
public class Courses {

    private List<Course> courseList = null;

    public List<Course> getCourseList() {
        return courseList;
    }
 
    public void setCourseList(List<Course> courses) {
        this.courseList = courses;
    }	
}
*/
