	//FetchAllRequest Object
	
var notEmptyValid = {
        message: 'Este Campo o preenchimento e obrigatorio'
    },
    stringMinMax = {
        min: 6,
        max: 30,
        message: 'O valor tem que ter no maximo 6 caracter'
    },
    notCaracterSpecial = {
        regexp: /^[a-zA-Z0-9_\.]+$/,
        message: 'O valor informado não pode ter "!@#$%¨&*()_+" apenas letras'
    },
    integerValue = {
        message: 'O valor informado tem que ser INTEIRO',
        // The default separators
        thousandsSeparator: '',
        decimalSeparator: '.'
    },
    email = {
        message: 'The input is not a valid email address'
    },
    telefone = {
        message: 'The input is not a valid UK phone number',
        country: 'BR'
    },
    cep =  {
        country: 'BR',
        message: 'The input is not a valid US zip code'
    }
    //==================================================

	var notEmptyStringMinMaxRegexp = {
            message: 'O valor informado não e valido',
            validators: {
                notEmpty: notEmptyValid,
                stringLength: stringMinMax,
                regexp: notCaracterSpecial
            }
        },
        notEmptyStringMinMax = {
            message: 'O valor informado não e valido',
            validators: {
                notEmpty: notEmptyValid,
                stringLength: stringMinMax
            }
        },
        notEmpty = {
            
            message: 'O valor informado não e valido',
            validators: {
                notEmpty: notEmptyValid,
                stringLength: stringMinMax,
                regexp: notCaracterSpecial
            }
        },
        integerNotEmptyValidation = {
        	message: 'O valor informado não e valido',
            validators: {
            	notEmpty : notEmptyValid,
                integer: integerValue
            }
        },
        integerValidation = {
        	message: 'O valor informado não e valido',
            validators: {
                integer: integerValue
            }
        },
        emailValidation = {
            validators: {
            	notEmpty : notEmptyValid,
                emailAddress: email
            }
        },
        telefoneValidation = {
            validators: {
                phone: telefone
            }
        },
        telefoneNotEmptyValidation = {
            validators: {
            	notEmpty : notEmptyValid,
                 phone: telefone
            }
        },
        cepNotEmptyValidation = {
            validators: {
            	notEmpty : notEmptyValid,
                 zipCode: cep
            }
        },
        cepValidation = {
            validators: {
                 zipCode: cep
            }
        };
        

