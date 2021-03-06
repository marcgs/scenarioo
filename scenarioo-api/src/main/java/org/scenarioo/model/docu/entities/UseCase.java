package org.scenarioo.model.docu.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.scenarioo.model.docu.entities.generic.Details;

import lombok.Data;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class UseCase {
	
	private String name;
	private String description;
	private String status;
	private Details details = new Details();
	
	public UseCase() {
	}
	
	public UseCase(final String name, final String description) {
		this();
		this.name = name;
		this.description = description;
		this.status = "";
	}
	
	public void addDetail(final String key, final Object value) {
		details.addDetail(key, value);
	}
}
