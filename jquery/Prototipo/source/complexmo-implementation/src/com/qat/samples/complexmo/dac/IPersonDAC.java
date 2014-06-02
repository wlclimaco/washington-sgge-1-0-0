package com.qat.samples.complexmo.dac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.complexmo.model.Person;
import com.qat.samples.complexmo.model.request.PersonFancyRequest;
import com.qat.samples.complexmo.model.request.PersonRequest;

/**
 * The Interface IPersonDAC.
 */
public interface IPersonDAC
{

	/**
	 * Gets the person by request.
	 * 
	 * @param personRequest the person request
	 * @return the internal results response
	 */
	InternalResultsResponse<Person> fetchPersonByRequest(PersonRequest personRequest);

	/**
	 * Fetch person using a fancy request.
	 * 
	 * @param personRequest the person request
	 * @return the internal results response
	 */
	InternalResultsResponse<Person> fetchPersonByFancyRequest(PersonFancyRequest personRequest);

	/**
	 * Gets the person by id.
	 * 
	 * @param person the person object
	 * @return the person object
	 */
	Person fetchPersonById(Person person);

	/**
	 * Gets the all persons.
	 * 
	 * @return the all persons
	 */
	InternalResultsResponse<Person> fetchAllPersons();

	/**
	 * Maintain person.
	 * 
	 * @param person the person
	 * 
	 * @return the internal results response
	 */
	InternalResultsResponse<Person> updatePerson(Person person);

	/**
	 * Insert person.
	 * 
	 * @param person the person
	 * @return the internal results response
	 */
	InternalResultsResponse<Person> insertPerson(Person person);

	/**
	 * Delete person.
	 * 
	 * @param person the person
	 * @return the internal response
	 */
	InternalResponse deletePerson(Person person);

}