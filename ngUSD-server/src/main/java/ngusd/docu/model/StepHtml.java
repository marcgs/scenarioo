package ngusd.docu.model;

import lombok.Data;

@Data
public class StepHtml {
	
	private String htmlSource = "";
	
	public StepHtml() {
	}
	
	public StepHtml(final String htmlSource) {
		this.htmlSource = htmlSource;
	}
	
}
