package com.qat.samples.complexmo.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.complexmo.dac.IAnimalDAC;
import com.qat.samples.complexmo.model.Animal;
import com.qat.samples.complexmo.model.EFT;
import com.qat.samples.complexmo.model.request.AnimalFancyRequest;
import com.qat.samples.complexmo.model.request.AnimalRequest;

/**
 * The Class AnimalDACImpl.
 */
public class AnimalDACImpl extends SqlSessionDaoSupport implements IAnimalDAC
{
	private static final String ANIMAL_NAMESPACE = "AnimalMap.";
	private static final String ANIMAL_STMT_FETCH_ALL = ANIMAL_NAMESPACE + "fetchAllAnimals";
	private static final String ANIMAL_STMT_FETCH_REQUEST = ANIMAL_NAMESPACE + "fetchAnimalByRequest";
	private static final String ANIMAL_STMT_FETCH_FANCY_REQUEST = ANIMAL_NAMESPACE + "fetchAnimalByFancyRequest";
	private static final String ANIMAL_STMT_FETCH_ID = ANIMAL_NAMESPACE + "fetchAnimalById";
	private static final String ANIMAL_STMT_INSERT = ANIMAL_NAMESPACE + "InsertAnimal";
	private static final String ANIMAL_STMT_UPDATE = ANIMAL_NAMESPACE + "UpdateAnimal";
	private static final String ANIMAL_STMT_DELETE = ANIMAL_NAMESPACE + "DeleteAnimal";
	private static final String ANIMAL_STMT_VERSION = ANIMAL_NAMESPACE + "fetchVersionNumber";
	private static final String CONTACT_NAMESPACE = "ContactMap.";
	private static final String CONTACT_STMT_UPDATE = CONTACT_NAMESPACE + "UpdateContact";
	private static final String CONTACT_STMT_UPDATE_PHONE = CONTACT_NAMESPACE + "UpdatePhone";
	private static final String CONTACT_STMT_UPDATE_EMAIL = CONTACT_NAMESPACE + "UpdateEmail";
	private static final String CONTACT_STMT_UPDATE_ADDRESS = CONTACT_NAMESPACE + "UpdateAddress";
	private static final String CONTACT_STMT_INSERT = CONTACT_NAMESPACE + "InsertContact";
	private static final String CONTACT_STMT_INSERT_PHONE = CONTACT_NAMESPACE + "InsertPhone";
	private static final String CONTACT_STMT_INSERT_EMAIL = CONTACT_NAMESPACE + "InsertEmail";
	private static final String CONTACT_STMT_INSERT_ADDRESS = CONTACT_NAMESPACE + "InsertAddress";
	private static final String EFT_NAMESPACE = "EFTMap.";
	private static final String EFT_STMT_INSERT = EFT_NAMESPACE + "InsertEft";
	private static final String EFT_STMT_UPDATE = EFT_NAMESPACE + "UpdateEft";

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory.getLogger(AnimalDACImpl.class);

	/**
	 * Maintain animal.
	 * 
	 * @param animal the animal
	 * 
	 * @return the animal
	 * 
	 * @see com.qat.samples.IAnimalDAC.complexmoupdate.DAC.IAnimalDAC#maintainAnimal(com.qat.samples.complexmoupdate.model.Animal)
	 */
	@Override
	public InternalResultsResponse<Animal> insertAnimal(Animal animal)
	{
		int insertCount = 0;
		InternalResultsResponse<Animal> response = new InternalResultsResponse<Animal>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), ANIMAL_STMT_INSERT, animal, response);

		if (!response.isInError())
		{
			// Next traverse the object graph and "maintain" the associations
			insertCount += maintainAnimalAssociations(animal, response);

			// Finally, if something was inserted then add the Animal to the result.
			if (insertCount > 0)
			{
				response.addResult(animal);
			}
		}
		return response;
	}

	/**
	 * Update animal.
	 * 
	 * @param animal the animal
	 * 
	 * @return the animal
	 */
	@Override
	public InternalResultsResponse<Animal> updateAnimal(Animal animal)
	{
		int updateCount = 0;
		InternalResultsResponse<Animal> response = new InternalResultsResponse<Animal>();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(animal.getModelAction())
				&& (animal.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), ANIMAL_STMT_UPDATE, animal, response);

			// If the update failed because the keys have changed, which is indicated by the NoRowsUpdatedError status,
			// then we should probably provide some context. Logging of information is optional it just depends on how
			// critical it that the updated failed to actually update any rows.
			// information.
			if (response.getStatus() == InternalResponse.Status.NoRowsUpdatedError)
			{
				LOG.error("NoRowsUpdatedError has occured for Animal model object with keys[" + animal.getId() + ", "
						+ animal + "]");
			}
		}

		if (!response.isInError())
		{
			// Next traverse the object graph and "maintain" the associations
			updateCount += maintainAnimalAssociations(animal, response);

			// Finally, if something was updated then add the Animal to the result.
			if (updateCount > 0)
			{
				response.addResult(animal);
			}
		}
		return response;
	}

	/**
	 * Maintain animal associations.
	 * 
	 * @param animal the animal
	 * @param response the response
	 * @return the int
	 */
	private int maintainAnimalAssociations(Animal animal, InternalResultsResponse<Animal> response)
	{
		int count = 0;
		// First Maintain Contacts
		// if (!ValidationUtil.isNullOrEmpty(animal.getContactList()))
		// {
		// // For Each Contact...
		// for (Contact contact : animal.getContactList())
		// {
		// // Make sure we set the parent key
		// contact.setParentKey(animal.getId());
		//
		// if (!ValidationUtil.isNull(contact.getModelAction()))
		// {
		// switch (contact.getModelAction())
		// {
		// case UPDATE:
		// count = updateContact(contact, response);
		// break;
		// case INSERT:
		// count = insertContact(contact, response);
		// break;
		// default:
		// break;
		// }
		// }
		// }
		// }

		// Next Maintain other associations

		// Make sure we set the parent key on the other associations so we are sure it will match in case there is an
		// insert.
		// if (!ValidationUtil.isNull(animal.getEft()) && !ValidationUtil.isNull(animal.getEft().getModelAction()))
		// {
		// // Set the parent key value
		// EFT eft = animal.getEft();
		// eft.setParentKey(animal.getId());
		//
		// if (!ValidationUtil.isNull(eft.getModelAction()))
		// {
		// switch (eft.getModelAction())
		// {
		// case UPDATE:
		// count = updateEft(eft, response);
		// break;
		// case INSERT:
		// count = insertEft(eft, response);
		// break;
		// default:
		// break;
		// }
		// }
		// }

		return count;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.qat.samples.complexmoupdate.DAC.IAnimalDAC#fetchAnimalByRequest(com.qat.samples.complexmoupdate
	 * .model.Animal)
	 */
	@Override
	public InternalResultsResponse<Animal> fetchAnimalByRequest(AnimalRequest animalRequest)
	{
		InternalResultsResponse<Animal> response = new InternalResultsResponse<Animal>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), ANIMAL_STMT_FETCH_REQUEST, animalRequest, response);

		return response;
	}

	@Override
	public InternalResultsResponse<Animal> fetchAnimalByFancyRequest(AnimalFancyRequest animalRequest)
	{
		InternalResultsResponse<Animal> response = new InternalResultsResponse<Animal>();

		// Need to "prepare" the dynamic search strings to make sure their properties render correctly in the SQL.
		QATMyBatisDacHelper.prepareDynamicSearchString(animalRequest.getName());

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), ANIMAL_STMT_FETCH_FANCY_REQUEST, animalRequest, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.complexmo.DAC.IAnimalDAC#fetchAnimalById(com.qat.samples.complexmo.model.Animal)
	 */
	@Override
	public Animal fetchAnimalById(Animal animal)
	{
		return (Animal)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), ANIMAL_STMT_FETCH_ID, animal);
	}

	/**
	 * Fetch all animals.
	 * 
	 * @return the list
	 * @see com.qat.samples.IAnimalDAC.DAC.IAnimalDAC#getAllCounties()
	 */
	@Override
	public InternalResultsResponse<Animal> fetchAllAnimals()
	{
		InternalResultsResponse<Animal> response = new InternalResultsResponse<Animal>();

		System.out.println("d");
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), ANIMAL_STMT_FETCH_ALL, response);

		return response;
	}

	/**
	 * Update contact.
	 * 
	 * @param animal the contact
	 * @param response the response
	 * @return the int
	 */
	private int updateContact(Animal animal, InternalResultsResponse<Animal> response)
	{
		int updateCount = 0;

		// First update the root if necessary.
		if (!ValidationUtil.isNull(animal.getModelAction())
				&& (animal.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CONTACT_STMT_UPDATE, animal, response);

			if (updateCount == 1)
			{
				animal.setModelAction(QATModel.PersistanceActionEnum.NONE);
			}
		}

		// Next traverse the Contact object graph and
		maintainContactAssociations(animal, response);

		return updateCount;
	}

	/**
	 * Insert contact.
	 * 
	 * @param animal the contact
	 * @param resp the resp
	 * @return the counter
	 */
	private int insertContact(Animal animal, InternalResultsResponse<Animal> resp)
	{
		int insertCount = 0;
		// First insert the root contact data
		insertCount = QATMyBatisDacHelper.doInsert(getSqlSession(), CONTACT_STMT_INSERT, animal, resp);

		// Then the associations.
		// We can use the "maintain" method for this.(empty right now)
		maintainContactAssociations(animal, resp);

		return insertCount;
	}

	/**
	 * Maintain contact associations.
	 * 
	 * @param contact the contact
	 * @param response the response
	 * @return the counter
	 */
	private int maintainContactAssociations(Animal animal, InternalResultsResponse<Animal> response)
	{
		int count = 0;

		// Make sure we set the parent key on the other associations so we are sure it will match in case there is an
		// insert.
		// Never finished the code for State, County and Country on purpose...this is a sample but it would look like
		// eft code

		return count;
	}

	/**
	 * Insert eft.
	 * 
	 * @param eft the eft
	 * @param response the response
	 */
	private int insertEft(EFT eft, InternalResultsResponse<Animal> response)
	{
		return QATMyBatisDacHelper.doInsert(getSqlSession(), EFT_STMT_INSERT, eft, response);
	}

	/**
	 * Update eft.
	 * 
	 * @param eft the eft
	 * @param response the response
	 * @return the int
	 */
	private int updateEft(EFT eft, InternalResultsResponse<Animal> response)
	{
		int updateCount = 0;

		if ((eft.getModelAction() != null) && (eft.getModelAction() == QATModel.PersistanceActionEnum.UPDATE))
		{
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), EFT_STMT_UPDATE, eft, response);
		}

		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.complexmo.DAC.IAnimalDAC#deleteAnimal(com.qat.samples.complexmo.model.Animal)
	 */
	@Override
	public InternalResponse deleteAnimal(Animal animal)
	{
		InternalResponse response = new InternalResultsResponse<Animal>();

		QATMyBatisDacHelper.doRemove(getSqlSession(), ANIMAL_STMT_DELETE, animal, response);

		return response;
	}

}
