package com.qat.samples.sysmgmt.validation;

import java.util.List;

import com.qat.framework.model.MessageInfo;
import com.qat.framework.validation.IValidator;
import com.qat.framework.validation.ValidationContext;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.cliente.model.Cliente;

/**
 * The Class BundleDValidator.
 */
public class ClienteValidator implements IValidator
{

	private static final String SYSMGMT_BASE_BUNDLEVALIDATOR_PROCDESC_REQUIRED =
			"sysmgmt.base.bundlevalidator.bundledesc.required";
	private static final String SYSMGMT_BASE_BUNDLEVALIDATOR_PROCCODE_REQUIRED =
			"sysmgmt.base.bundlevalidator.bundlecode.required";
	private static final String SYSMGMT_BASE_BUNDLEVALIDATOR_ID_REQUIRED =
			"sysmgmt.base.bundlevalidator.id.required";

	@Override
	public void validate(ValidationContext validationContext)
	{
		Cliente bundle =
				(Cliente)validationContext.getObjectToBeValidated(Cliente.class.getSimpleName());

		switch (validationContext.getValidationContextIndicator())
		{
			case DELETE:
				validateCidadeId(validationContext.getMessages(), bundle);
				break;
			case UPDATE:
				validateCidadeId(validationContext.getMessages(), bundle);
				validateCidadeCidade(validationContext.getMessages(), bundle);
				validateCidadeEstado(validationContext.getMessages(), bundle);
				break;
			default:
				validateCidadeCidade(validationContext.getMessages(), bundle);
				validateCidadeEstado(validationContext.getMessages(), bundle);
				break;
		}
	}

	private void validateCidadeId(List<MessageInfo> list, Cliente bundle)
	{
		ValidationUtil.isNullOrZero(bundle.getClienteid(), SYSMGMT_BASE_BUNDLEVALIDATOR_ID_REQUIRED, list);
	}

	private void validateCidadeCidade(List<MessageInfo> list, Cliente bundle)
	{
		ValidationUtil.isNullOrEmpty(bundle.getEnderecos().get(0).getLogradouro(),
				SYSMGMT_BASE_BUNDLEVALIDATOR_PROCCODE_REQUIRED, list);
	}

	private void validateCidadeEstado(List<MessageInfo> list, Cliente bundle)
	{
		ValidationUtil.isNullOrEmpty(bundle.getDocumentos().get(0).getCpfCnpj(),
				SYSMGMT_BASE_BUNDLEVALIDATOR_PROCDESC_REQUIRED,
				list);
	}
}
