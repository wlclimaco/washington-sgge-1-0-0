$.validationEngineLanguage = {
    newLang: function(){
        $.validationEngineLanguage.allRules = {
            "required": { // Add your regex rules here, you can take telephone as an example
                "regex": "none",
                "alertText": sensus.locale.get("commons.pages.fieldRequiredAsterisk"),
                "alertTextCheckboxMultiple": sensus.locale.get("commons.pages.pleaseSelectOption"),
                "alertTextCheckboxe": sensus.locale.get("commons.pages.thisCheckboxRequired"),
                "alertTextDateRange": sensus.locale.get("commons.pages.dateRangeRequired")
            },
            "dateRange": {
                "regex": "none",
                "alertText": "*" + sensus.locale.get("summary.text.invalid") + " ",
                "alertText2": sensus.locale.get("commons.pages.date.range")
            },
            "dateTimeRange": {
                "regex": "none",
                "alertText": "*" + sensus.locale.get("summary.text.invalid") + " ",
                "alertText2": sensus.locale.get("commons.pages.dateTime.range")
            },
            "minSize": {
                "regex": "none",
                "alertText": sensus.locale.get("commons.pages.minimum") + " ",
                "alertText2": " " + sensus.locale.get("commons.pages.charactersAllowed")
            },
            "maxSize": {
                "regex": "none",
                "alertText": sensus.locale.get("commons.pages.maximum") + " ",
                "alertText2": " " + sensus.locale.get("commons.pages.charactersAllowed")
            },
			"groupRequired": {
                "regex": "none",
                "alertText": sensus.locale.get("commons.pages.groupRequiredValidation")
            },
            "min": {
                "regex": "none",
                "alertText": sensus.locale.get("commons.pages.minimumValue") + " "
            },
            "max": {
                "regex": "none",
                "alertText": sensus.locale.get("commons.pages.maximumValue") + " "
            },
            "past": {
                "regex": "none",
                "alertText": sensus.locale.get("commons.pages.datePrior") + " "
            },
            "future": {
                "regex": "none",
                "alertText": sensus.locale.get("commons.pages.datePast") + " "
            },
            "maxCheckbox": {
                "regex": "none",
                "alertText": sensus.locale.get("commons.pages.maximum") + " ",
                "alertText2": sensus.locale.get("commons.pages.optionAllowed")
            },
            "minCheckbox": {
                "regex": "none",
                "alertText": sensus.locale.get("commons.pages.pleaseSelect") + " ",
                "alertText2": sensus.locale.get("commons.pages.options")
            },
            "equals": {
                "regex": "none",
                "alertText": sensus.locale.get("commons.pages.fieldsNotMatch")
            },
            "integer": {
                "regex": /^[\-\+]?\d+$/,
                "alertText": sensus.locale.get("commons.pages.notValidInteger")
            },
            "number": {
                // Number, including positive, negative, and floating decimal. credit: orefalo
                "regex": /^[\-\+]?(([0-9]+)([\.,]([0-9]+))?|([\.,]([0-9]+))?)$/,
                "alertText": sensus.locale.get("commons.pages.invalidFloating")
            },
            "date": {
                "regex": /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/,
                "alertText": sensus.locale.get("commons.pages.invalidDateYyyyMm")
            },

            "dateEn": {
            	// Validation only date format "regex": /(?:0[1-9]|1[0-2])\/(?:0[1-9]|[12][0-9]|3[01])\/(?:19|20\d{2})$/
                "regex": /^(?:(?:(?:0?[13578]|1[02])(\/|-|\.)31)\1|(?:(?:0?[1,3-9]|1[0-2])(\/|-|\.)(?:29|30)\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:0?2(\/|-|\.)29\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:(?:0?[1-9])|(?:1[0-2]))(\/|-|\.)(?:0?[1-9]|1\d|2[0-8])\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$/,
                "alertText": sensus.locale.get("commons.pages.invalidDateMmYyyy")
            },

            "datePt": {
        		// Validation only date format "regex": /(?:0[1-9]|[12][0-9]|3[01])\/(?:0[1-9]|1[0-2])\/(?:19|20\d{2})$/
               "regex": /^(((0[1-9]|[12][0-9]|3[01])([-.\/])(0[13578]|10|12)([-.\/])(\d{4}))|(([0][1-9]|[12][0-9]|30)([-.\/])(0[469]|11)([-.\/])(\d{4}))|((0[1-9]|1[0-9]|2[0-8])([-.\/])(02)([-.\/])(\d{4}))|((29)(\.|-|\/)(02)([-.\/])([02468][048]00))|((29)([-.\/])(02)([-.\/])([13579][26]00))|((29)([-.\/])(02)([-.\/])([0-9][0-9][0][48]))|((29)([-.\/])(02)([-.\/])([0-9][0-9][2468][048]))|((29)([-.\/])(02)([-.\/])([0-9][0-9][13579][26])))$/,
               "alertText": sensus.locale.get("commons.pages.invalidDateDdMmYyyy")
            },

            "onlyNumberSp": {
                "regex": /^[0-9\ ]+$/,
                "alertText": sensus.locale.get("commons.pages.onlyNumbers")
            },
            "onlyLetterSp": {
                "regex": /^[a-zA-Z\ \']+$/,
                "alertText": sensus.locale.get("commons.pages.onlyLetters")
            },
            "onlyLetterNumber": {
                "regex": /^[0-9a-zA-Z]+$/,
                "alertText": sensus.locale.get("commons.pages.noSpecialCharacters")
            },

            //tls warning:homegrown not fielded
            "dateFormat":{
                "regex": /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$|^(?:(?:(?:0?[13578]|1[02])(\/|-)31)|(?:(?:0?[1,3-9]|1[0-2])(\/|-)(?:29|30)))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^(?:(?:0?[1-9]|1[0-2])(\/|-)(?:0?[1-9]|1\d|2[0-8]))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^(0?2(\/|-)29)(\/|-)(?:(?:0[48]00|[13579][26]00|[2468][048]00)|(?:\d\d)?(?:0[48]|[2468][048]|[13579][26]))$/,
                "alertText": "*" + sensus.locale.get("tag.page.error.invalidDate")
            },
            //tls warning:homegrown not fielded
			"dateTimeFormat": {
                "regex": /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])\s+(1[012]|0?[1-9]){1}:(0?[1-5]|[0-6][0-9]){1}:(0?[0-6]|[0-6][0-9]){1}\s+(am|pm|AM|PM){1}$|^(?:(?:(?:0?[13578]|1[02])(\/|-)31)|(?:(?:0?[1,3-9]|1[0-2])(\/|-)(?:29|30)))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^((1[012]|0?[1-9]){1}\/(0?[1-9]|[12][0-9]|3[01]){1}\/\d{2,4}\s+(1[012]|0?[1-9]){1}:(0?[1-5]|[0-6][0-9]){1}:(0?[0-6]|[0-6][0-9]){1}\s+(am|pm|AM|PM){1})$/,
                "alertText": "* Invalid Date or Date Format",
                "alertText2": "Expected Format: ",
                "alertText3": "mm/dd/yyyy hh:mm:ss AM|PM or ",
                "alertText4": "yyyy-mm-dd hh:mm:ss AM|PM"
            },
            "noSpecialCaracters": {
                "regex": /^(?:(?:[a-z0-9#]*(?:(?:(?:\s?-\s?|'s\s?|[(?:s)]'\s?|\s)[a-z0-9#]*)?))\s?)*$/i,
                "alertText": sensus.locale.get("commons.pages.specialCharacterInvalid")
            },
            "noSpecialCaractersOfSearch": {
            	"regex": /^(?:(?:[a-z0-9*_]*(?:(?:(?:\s?-\s?|'s\s?|[(?:s)]'\s?|\s)[a-z0-9#]*_)?))\s?)*$/i,
                "alertText": sensus.locale.get("commons.pages.specialCharacterInvalid")
            },
            "onlyCsv": {
            	"regex": /.csv$/i,
            	"alertText": sensus.locale.get("commons.pages.uploadFileCsv")
            },
            "timeFormat": {
            	"regex": /^(0?[1-9]|(10)|(11)|(12)):(0[0-9]|([1-5][0-9]))[ap]m$/i,
            	"alertText": sensus.locale.get("commons.pages.ExpectedFormat")
            }
        };
    }
};

$.validationEngineLanguage.newLang();