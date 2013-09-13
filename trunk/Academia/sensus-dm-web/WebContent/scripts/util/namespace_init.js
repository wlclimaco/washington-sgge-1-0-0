/**
 * @fileoverview Initializes the main script namespaces.
 * @author Raphael Constantino
 */

/**
 * Initialize the main namespaces and constants.
 */
var sensus = {
	locale : {},
	pages  : {
		device 								: {},
		group  								: {},
		tag	   								: {},
		systemIntelligenceCreateActions		: {},
		scheduleEvent						: {},
		processtoday						: {},
		schedule							: {},
		longrunningprocess					: {},
		dm									: {},
		readings							: {},
		devicehistory						: {},
		devicecommunications				: {},
		action								: {},
		dashboard							: {},
		systemintelligence					: {},
		search								: {},
		smartpointdetail					: {},
		schedules							: {},
		detailTou							: {},
		systemsettings						: {}
	},
	modules : {},
	config  : {},
	widgets : {},
	commons : {
		lib 	: {},
		modules : {}
	},
	util : {
		systemsettings			: {},
		globalActions			: {},
		generatelinkaction		: {},
		exportcsv				: {},
		device					: {},
		combobox				: {},
		actiondialog			: {},
		dialogSettings			: {},
		mapopen					: {},
		module_operation		: {},
		page					: {},
		process					: {
			actions	: {}
		},
		session					: {}
	},
	constants : {
		services : {
			electric : {
				name : 'ELECTRIC',
				meter : {
					name : 'ELECTRIC_METER'
				},
				han : {
					name : 'HAN_DEVICE'
				},
				lcm : {
					name : 'LCM',
					flexNetLCM : 'FLEXNET_LCM'
				}
			},
			water : {
				name : 'WATER',
				meter : {
					name : 'WATER_METER'
				}
			},
			gas : {
				name : 'GAS',
				meter : {
					name : 'GAS_METER'
				}
			}
		}
	},
	settings : {
		currentTime : null
	}
};
