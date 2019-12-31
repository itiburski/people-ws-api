package br.com.jitec.people.util;

import br.com.jitec.people.exception.AppEntityNotFoundException;

public class BusinessPreconditions {

	/**
	 * Check if some value was found, otherwise throw exception.
	 * 
	 * @param expression has value true if found, otherwise false
	 * @throws MyResourceNotFoundException if expression is false, means value not
	 *                                     found.
	 */
	public static <T> T checkFound(final T resource) {
		if (resource == null) {
			throw new AppEntityNotFoundException();
		}

		return resource;
	}
}
