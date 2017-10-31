package assign.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "output")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorHandler {
	
	//String error = null;
	List<String> error = null;
	
	public ErrorHandler() {
		
	}
	
	public void setError(String error) {
		this.error.add(error);
	}
	
	public List<String> getError() {
		return this.error;
	}
	
}
