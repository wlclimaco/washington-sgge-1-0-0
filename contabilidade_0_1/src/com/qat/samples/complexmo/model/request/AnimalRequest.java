package com.qat.samples.complexmo.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.complexmo.model.Animal;

public class AnimalRequest extends Request
{
	private Animal animal;

	public Animal getAnimal()
	{
		return animal;
	}

	public void setAnimal(Animal animal)
	{
		this.animal = animal;
	}

	@Override
	public String toString()
	{
		return "AnimalRequest [animal=" + animal + ", toString()=" + super.toString() + "]";
	}

}
