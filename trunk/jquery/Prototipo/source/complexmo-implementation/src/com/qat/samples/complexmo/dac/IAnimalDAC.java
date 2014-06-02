package com.qat.samples.complexmo.dac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.complexmo.model.Animal;
import com.qat.samples.complexmo.model.request.AnimalFancyRequest;
import com.qat.samples.complexmo.model.request.AnimalRequest;

/**
 * The Interface IAnimalDAC.
 */
public interface IAnimalDAC
{

	/**
	 * Gets the animal by request.
	 * 
	 * @param animalRequest the animal request
	 * @return the internal results response
	 */
	InternalResultsResponse<Animal> fetchAnimalByRequest(AnimalRequest animalRequest);

	/**
	 * Fetch animal using a fancy request.
	 * 
	 * @param animalRequest the animal request
	 * @return the internal results response
	 */
	InternalResultsResponse<Animal> fetchAnimalByFancyRequest(AnimalFancyRequest animalRequest);

	/**
	 * Gets the animal by id.
	 * 
	 * @param animal the animal object
	 * @return the animal object
	 */
	Animal fetchAnimalById(Animal animal);

	/**
	 * Gets the all animals.
	 * 
	 * @return the all animals
	 */
	InternalResultsResponse<Animal> fetchAllAnimals();

	/**
	 * Maintain animal.
	 * 
	 * @param animal the animal
	 * 
	 * @return the internal results response
	 */
	InternalResultsResponse<Animal> updateAnimal(Animal animal);

	/**
	 * Insert animal.
	 * 
	 * @param animal the animal
	 * @return the internal results response
	 */
	InternalResultsResponse<Animal> insertAnimal(Animal animal);

	/**
	 * Delete animal.
	 * 
	 * @param animal the animal
	 * @return the internal response
	 */
	InternalResponse deleteAnimal(Animal animal);

}