package org.scenarioo.uitest.dummy.application.steps;

import java.util.Map;

import org.scenarioo.uitest.dummy.application.ApplicationsStateData;
import org.scenarioo.uitest.dummy.application.DummySimulationConfig;

import lombok.Data;

/**
 * Dummy example data for one step of the application to be simulated for the example.
 */
@Data
public class DummyApplicationStepData {
	
	private DummySimulationConfig simulationConfig;
	private String startBrowserUrl;
	private int index;
	
	private String screenshotFileName;
	private String browserUrl;
	private Map<String, String> elementTexts;
	private ApplicationsStateData applicationStateData;
	
	public String getTextFromElement(final String elementId) {
		String text = elementTexts.get(elementId);
		if (text == null) {
			throw new IllegalArgumentException("UI element does not exist: " + elementId);
		}
		return text;
	}
	
}
