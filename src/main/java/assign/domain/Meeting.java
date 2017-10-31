package assign.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "meeting")
@XmlAccessorType(XmlAccessType.FIELD)
public class Meeting {
	
	//private String name;
	private String year;

//	public String getName() {
//		return name;
//	}
	
//	public void setName(String name) {
//		this.name = name;
//	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}

}


/*
XmlRootElement(name = "course")

public class Meeting {
	
	private String name;
	private String department;
	
	public String getName() {
		return name;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
*/