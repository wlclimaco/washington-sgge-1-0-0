/**
 * @fileoverview Initialize the main namespaces and constants.
 * @author Flavio Tosta
 */

var pgsi = {
	locale : {},
	location : {
		locationId   : null,
		locationName : null,
		organizationId:null,
	},
	pages : {

		address : {
			form : {},
			view : {}
		},

		empresa : {
			form : {},
			view : {}
		},

		entidade : {
			form : {},
			view : {}
		},

		batches : {
			view : {}
		},

		business : {

		},

		employment : {
			view : {}
		},

		identification: {},

		location: {
			form : {},
			view : {}
		},

		member: {
			create : {}
		},

		organization : {
			view : {},
			form : {}
		},

		payments : {

		},

		phone : {
			view : {},
			form : {}
		},

		pricing : {

		},

		recipient: {
			create : {},
			// member name
			sMemberName:null,
			iMemberId:null

		},

		sdn: {
			create : {},
			tabs:{},


		},

		security: {
			question : {},

		},

		sendsolv : {

		},

		transfer: {
			create : {},
			view :{}
		},

		transaction : {
			form : {}
		}
	},

	settings : {
		storage: {},
		user : {
			language : {

			}
		}
	},
	util : {
		page : {

		}
	},

	version :{
			versionAddres:null,
			versionPhone:null,
			versionEmail:null,
			versionBusiness:null,
			versionMember:null,
			versionRecipient:null,
			versionCountryUsage:null,
			versionTransfer:null
	}
};