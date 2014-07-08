/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr.language;

import aorta.kr.language.generic.GenericLoader;
import aorta.kr.language.generic.GenericOrganizationalLanguage;

/**
 *
 * @author ascje
 */
public enum OrganizationType {
	
	GENERIC("generic", new GenericLoader(), new GenericOrganizationalLanguage());
	
	private String name;
	private OrganizationLoader loader;
	private OrganizationalLanguage language;

	private OrganizationType(String name, OrganizationLoader loader, OrganizationalLanguage language) {
		this.name = name;
		this.loader = loader;
		this.language = language;
	}

	public OrganizationLoader getLoader() {
		return loader;
	}

	public OrganizationalLanguage getLanguage() {
		return language;
	}
		
	public static OrganizationType get(String type) {
		for (OrganizationType t : values()) {
			if (t.name.equals(type)) {
				return t;
			}
		}
		return null;
	}
	
}
