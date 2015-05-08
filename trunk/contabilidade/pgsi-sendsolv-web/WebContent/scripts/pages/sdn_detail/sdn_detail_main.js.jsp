<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

	<script type="text/javascript">
		/**
		 * @namespace pgsi.pages.sdn
		 * @description The main namespace for the SDN View Page.
		 * @author Anke Doerfel-Parker
		 */
		pgsi.pages.sdn.detail = {

			fnFillSDNDetails : function(oMatchIndex) {

				// this is simply to make code less verbose
				var m = oMatchIndex;

				// NAME
				pgsi.pages.sdn.detail.fnFillName(m.NAME.otherSdn.firstName,
						m.NAME.otherSdn.lastName, $("#primaryname").find("ul"));
				var akas = {
					weak : [],
					strong : []
				};
				if (!$.pgsi.isNullOrUndefined(m.NAME.otherSdn.akaList)
						&& !$.pgsi
								.isNullOrUndefined(m.NAME.otherSdn.akaList.aka)) {
					for (var i = 0; i < m.NAME.otherSdn.akaList.aka.length; i++) {
						akas[m.NAME.otherSdn.akaList.aka[i].category]
								.push(m.NAME.otherSdn.akaList.aka[i]);
					}
				}
				if (akas.weak.length > 0) {
					var ul = $("#weakaka").find("ul");
					for (var ii = 0; ii < akas.weak.length; ii++) {
						pgsi.pages.sdn.detail.fnFillName(
								akas.weak[ii].firstName,
								akas.weak[ii].lastName, ul);
					}
				} else {
					$("#weakaka").remove();
				}

				if (akas.strong.length > 0) {
					var ul = $("#strongaka").find("ul");
					for (var ii = 0; ii < akas.strong.length; ii++) {
						pgsi.pages.sdn.detail.fnFillName(
								akas.strong[ii].firstName,
								akas.strong[ii].lastName, ul);
					}
				} else {
					$("#strongaka").remove();
				}

				if (!$.pgsi.isNullOrUndefined(m.NAME.topMatches)){
					$("#name-match").find("ul").append(
							"<li><span class='match'>"
									+ m.NAME.topMatches[0].sdnValue
									+ "</span></li>");
					$("#pgsi-name").text(m.NAME.otherPgsi.fullName);
					$("#pgsi-match-name").text(m.NAME.topMatches[0].systemValue);

					$(".name .matchpercent").text(
							pgsi.util.page.fnInsertMaskNumeric(
									pgsi.pages.sdn.view.percentMask,
									m.NAME.topMatches[0].matchProximity * 100));

					pgsi.pages.sdn.detail.fnFillNameMatchDetail(m.NAME.topMatches);
				}
				// ADDRESS

				pgsi.pages.sdn.detail.fnFillMultiRowSection(m.ADDRESS,
						$("tr.address"),
						pgsi.pages.sdn.detail.fnFillAddressSection);

				var removeSelfAndPrev = function(dom) {
					dom.prev().remove();
					dom.remove();
				}

				// Other
				var other = "";
				if (!$.pgsi.isNullOrUndefined(oEntry.programList)) {
					other = "<div><span class='header'>"
							+ $.pgsi.locale
									.get("pgsi.pages.sdn.view.match.programs")
							+ "</span>"
							+ (oEntry.programList.program.join(", "))
							+ "</div>";
				}
				if (other.length > 0) {
					$("tr.other .sdn").html($(other));
				}

				// Person-only fields
				// ID Docs
				var dom = $("tr.iddoc");
				if ($.pgsi.isNullOrUndefined(m.ID)) {
					removeSelfAndPrev(dom);
				} else {
					pgsi.pages.sdn.detail.fnFillMultiRowSection(m.ID, dom,
							pgsi.pages.sdn.detail.fnFillIdDocSection);
				}

				dom = $("tr.yob");
				if ($.pgsi.isNullOrUndefined(m.YEAR_OF_BIRTH)) {
					removeSelfAndPrev(dom);
				} else {
					pgsi.pages.sdn.detail.fnFillMultiRowSection(
							m.YEAR_OF_BIRTH, dom,
							pgsi.pages.sdn.detail.fnFillYOBSection);
				}

				dom = $("tr.pob");
				if ($.pgsi.isNullOrUndefined(m.PLACE_OF_BIRTH)) {
					removeSelfAndPrev(dom);
				} else {
					pgsi.pages.sdn.detail.fnFillMultiRowSection(
							m.PLACE_OF_BIRTH, dom,
							pgsi.pages.sdn.detail.fnFillPOBSection);
				}

				dom = $("tr.citizen");
				if ($.pgsi.isNullOrUndefined(m.CITIZENSHIP_COUNTRY)) {
					removeSelfAndPrev(dom);
				} else {
					pgsi.pages.sdn.detail.fnFillMultiRowSection(
							m.CITIZENSHIP_COUNTRY, dom,
							pgsi.pages.sdn.detail.fnFillCitizenshipSection);
				}

				dom = $("tr.nationality");
				if ($.pgsi.isNullOrUndefined(m.NATIONALITY_COUNTRY)) {
					removeSelfAndPrev(dom);
				} else {
					pgsi.pages.sdn.detail.fnFillMultiRowSection(
							m.NATIONALITY_COUNTRY, dom,
							pgsi.pages.sdn.detail.fnFillNationalitySection);
				}

			},

			/*
			 * This function indexes and normalized the SDN and PGSi data. The final index should look like this:
			 */
			/* var o = {
				// field with Matches
				FIELD1 : {
					// SDN UIDs of matches order by desc matchProximity
					topMatches : [ "UID2", "UID1" ],
					UID1 : {
						// If Field == ADDRESS, this is an array of matches
						matches: {}
						sdnEntry : {},
						pgsiEntry : {}
					},
					UID2 : {
						matches: {}
						sdnEntry : {},
						pgsiEntry : {}
					},
					otherSdn : {},
					// only for ids
					otherPgsi : {}
				},
				// Field with no match
				FIELD2 : {
					otherSdn : {},
					// only for ID
					otherPgsi : {}
				}
				// NAME is different
				NAME : {
					// list of Matches sorted by proximity (regardless of matching name field)
					topMatches: [{}, {}],
					// full sdn entry
					otherSdn : {},
					// full entity
					otherPgsi : {}
				}
			}; */
			fnBuildMatchIndex : function() {

				//--------DATA INIT ------------
				var matchMap = {};
				// prepare Data
				var address = null;
				for (var i = 0; i < oEntity.contactList.length; i++) {
					// Check for Contact of type Address
					if (oEntity.contactList[i].contactTypeValue == 7) {
						address = oEntity.contactList[i];
						break;
					}
				}

				// set pgsi country of citizenship
				var countryOfCitizenship = null;
				// set pgsi country of citizenship
				var countryOfBirth = null;
				if (!$.pgsi.isNullOrUndefined(oEntity.countryUsageList)) {
					for (var i = 0; i < oEntity.countryUsageList.length; i++) {
						if (oEntity.countryUsageList[i].usage == "CITIZENSHIP") {
							countryOfCitizenship = oEntity.countryUsageList[i].country.description;
							oEntry.countryOfCitizenship = countryOfCitizenship;
						} else if (oEntity.countryUsageList[i].usage == "BIRTH") {
							countryOfBirth = oEntity.countryUsageList[i].country.description;
							oEntry.countryOfBirth = countryOfBirth;
						}
					}
				}

				// Build basic index from match list
				//--------BEGIN PROCESSING FIELD ENTRIES  ------------
				for (var i = 0; i < aMatches.length; i++) {

					// Normalize Field names for Name section
					var fieldName = aMatches[i].sdnField;
					if (fieldName == 'FIRST_NAME' || fieldName == 'LAST_NAME'
							|| fieldName == 'FIRST_LAST_NAME') {
						fieldName = 'NAME';
					}

					// create match entry
					if (fieldName == "NAME") {
						if ($.pgsi.isNullOrUndefined(matchMap[fieldName])) {
							matchMap[fieldName] = {
								topMatches : [ aMatches[i] ]
							};
						} else {
							matchMap[fieldName].topMatches.push(aMatches[i]);
						}
					} else {
						if ($.pgsi.isNullOrUndefined(matchMap[fieldName])) {
							matchMap[fieldName] = {
								topMatches : [ aMatches[i].sdnUid ]
							};
						} else {
							matchMap[fieldName].topMatches
									.push(aMatches[i].sdnUid);
						}
						matchMap[fieldName][aMatches[i].sdnUid] = {
							matches : aMatches[i]
						};
					}

					var addDetails = function(sdnEntryList, pgsiData) {
						// find the matching SDN record and add it to the UID record
						for (var a = 0; a < sdnEntryList.length; a++) {
							if (aMatches[i].sdnUid == sdnEntryList[a].uid) {
								matchMap[fieldName][aMatches[i].sdnUid].sdnEntry = sdnEntryList[a];
								matchMap[fieldName][aMatches[i].sdnUid].pgsiEntry = pgsiData;
								break;
							}
						}
						// if there are no other SDN records, add all except above
						if ($.pgsi
								.isNullOrUndefined(matchMap[fieldName].otherSdn)) {
							matchMap[fieldName].otherSdn = []
							for (var a = 0; a < sdnEntryList.length; a++) {
								if ($.pgsi

										.isNullOrUndefined(matchMap[fieldName][sdnEntryList[a].uid])) {
									matchMap[fieldName].otherSdn
											.push(sdnEntryList[a]);
								}
							}
						} else {
							// remove match from other Sdn
							for (var aa = 0; aa < matchMap[fieldName].otherSdn.length; aa++) {
								if (matchMap[fieldName].otherSdn[aa].uid == aMatches[i].sdnUid) {
									matchMap[fieldName].otherSdn.splice(aa, 1);
									aa--;
								}
							}
						}

					}

					// find the matching SDN Entry
					// track matched id records (don't need to track anything else since name is handled differently and everything else has only 1 record at PGSi)
					var pgsiIds = [];
					switch (fieldName) {
					case "NAME":
						if ($.pgsi.isNullOrUndefined(matchMap.NAME.otherPgsi)) {
							matchMap.NAME.otherPgsi = oEntity;
							matchMap.NAME.otherSdn = oEntry;
						}
						break;
					case "CITY":
						if (!$.pgsi.isNullOrUndefined(oEntry.addressList)) {
							addDetails(oEntry.addressList.address, address);
						}
						break;
					case "ADDRESS_COUNTRY":
						if (!$.pgsi.isNullOrUndefined(oEntry.addressList)) {
							addDetails(oEntry.addressList.address, address);
						}
						break;
					case "ID":
						// find matching PGSi Id
						var pgsiId = oEntity.documentList[0];
						if (oEntity.documentList.length > 1) {
							for (var i = 0; i < oEntity.documentList.length; i++) {
								if (aMatches[i].systemValue == oEntity.documentList[i].value) {
									pgsiId = i;
									pgsiIds.push(i.id);
									break;
								}
							}
						}
						addDetails(oEntry.idList.id, pgsiId);
						break;
					case "YEAR_OF_BIRTH":
						if ($.pgsi.isNullOrUndefined(oEntry.dateOfBirthList)){
							if($.pgsi.isNullOrUndefined(oEntry.dateOfBirthList.dateOfBirthItem)) {
								addDetails(oEntry.dateOfBirthList.dateOfBirthItem,
									oEntity.dateOfBirth);
							}
						}
						break;
					case "CITIZENSHIP_COUNTRY":
						addDetails(oEntry.citizenshipList.citizenship,
								countryOfCitizenship);
						break;
					case "NATIONALITY_COUNTRY":
						addDetails(oEntry.nationalityList.nationality,
								countryOfCitizenship);
						break;
					default:
						// skip name fields - they are displayed differently
					}
				}

				//--------END PROCESSING FIELD ENTRIES  ------------

				//--------PROCESS NON-MATCHING ENTRIES ------------

				// Add unmatched  PGSI id list to match
				if (!$.pgsi.isNullOrUndefined(matchMap.ID)) {
					matchMap.ID.otherPgsi = [];
					if (oEntity.documentList.length > 1) {
						var present = false;
						for (var i = 0; i < oEntity.documentList.length; i++) {
							present = false;
							for (var ii = 0; ii < pgsiIds.length; ii++) {
								if (pgsiIds[ii] == oEntity.documentList[i].id) {
									present = true;
									break;
								}
							}
							if (!present) {
								matchMap.ID.otherPgsi
										.push(oEntity.documentList[i]);
							}
						}

					}
				}

				// Now add the Name details (all names)

				if ($.pgsi.isNullOrUndefined(matchMap.NAME)) {
					matchMap.NAME = {
						otherSdn : oEntry,
						otherPgsi : oEntity
					}
				}

				// Add Unmatched addresses (if no City/Country match present)
				var sdnVal = [];
				if (!$.pgsi.isNullOrUndefined(oEntry.addressList)) {
					sdnVal = oEntry.addressList.address;
				}

				if ($.pgsi.isNullOrUndefined(matchMap.CITY)) {
					matchMap.CITY = {
						otherSdn : sdnVal,
						otherPgsi : address
					};
				}
				if ($.pgsi.isNullOrUndefined(matchMap.ADDRESS_COUNTRY)) {
					matchMap.ADDRESS_COUNTRY = {
						otherSdn : sdnVal,
						otherPgsi : address
					};
				}

				// Programs
				sdnVal = [];
				if (!$.pgsi.isNullOrUndefined(oEntry.programList)
						&& !$.pgsi
								.isNullOrUndefined(oEntry.programList.program)) {
					sdnVal = oEntry.programList.program;
				}
				matchMap.PROGRAMS = {
					otherSdn : sdnVal
				}

				// SDN Remarks
				sdnVal = [];
				if (!$.pgsi.isNullOrUndefined(oEntry.remarks)) {
					sdnVal = [ oEntry.remarks ];
				}

				matchMap.REMARKS = {
					otherSdn : sdnVal
				}

				// Collate CITY and ADDRESS_COUNTRY to ADDRESS

				matchMap.ADDRESS = {
					topMatches : [],
					otherSdn : [],
					otherPgsi : [],
				}

				// copy all city matches
				if (!$.pgsi.isNullOrUndefined(matchMap.CITY.topMatches)) {
					for (var c = 0; c < matchMap.CITY.topMatches.length; c++) {
						var cityMatchUID = matchMap.CITY.topMatches[c];
						matchMap.ADDRESS.topMatches.push(cityMatchUID);
						matchMap.ADDRESS[cityMatchUID] = {
							matches : [ matchMap.CITY[cityMatchUID].matches ],
							sdnEntry : matchMap.CITY[cityMatchUID].sdnEntry,
							pgsiEntry : matchMap.CITY[cityMatchUID].pgsiEntry
						}
					}
				}
				var found = null;

				// add country matches (if not already present)
				if (!$.pgsi
						.isNullOrUndefined(matchMap.ADDRESS_COUNTRY.topMatches)) {
					for (var cc = 0; cc < matchMap.ADDRESS_COUNTRY.topMatches.length; cc++) {
						found = false;
						var countryMatchUID = matchMap.ADDRESS_COUNTRY.topMatches[cc];
						for (var ccc = 0; ccc < matchMap.ADDRESS.topMatches.length; ccc++) {
							if (matchMap.ADDRESS.topMatches[ccc] == countryMatchUID) {
								found = true;
								break;
							}
						}
						if (found) {
							// just add the match info
							matchMap.ADDRESS[countryMatchUID].matches
									.push(matchMap.ADDRESS_COUNTRY[countryMatchUID].matches);
						} else {

							// create a new UID entry
							matchMap.ADDRESS.topMatches.push(countryMatchUID);
							matchMap.ADDRESS[countryMatchUID] = {
								matches : [ matchMap.ADDRESS_COUNTRY[countryMatchUID].matches ],
								sdnEntry : matchMap.ADDRESS_COUNTRY[countryMatchUID].sdnEntry,
								pgsiEntry : matchMap.ADDRESS_COUNTRY[countryMatchUID].pgsiEntry
							}
						}
					}
				}

				// now add the unmatched SDN Entries
				var addUnmatched = function(list) {
					var found = false;
					if (!$.pgsi.isNullOrUndefined(list)){
						for (var j = 0; j < list.length; j++) {
							// has no UID Entry
							if ($.pgsi
									.isNullOrUndefined(matchMap.ADDRESS[list[j].uid])) {
								found = false;
								// is not in otherSdn list already
								for (var jj = 0; jj < matchMap.ADDRESS.otherSdn.length; jj++) {
									if (matchMap.ADDRESS.otherSdn[jj].uid == list[j].uid) {
										found = true;
										break;
									}
								}
								if (!found) {

									matchMap.ADDRESS.otherSdn.push(list[j]);
								}
							}
						}
					}
				}
				addUnmatched(matchMap.CITY.otherSdn);
				addUnmatched(matchMap.ADDRESS_COUNTRY.otherSdn);

				// add unmatched pgsi entries
				if (matchMap.ADDRESS.topMatches.length == 0) {
					matchMap.ADDRESS.otherPgsi = matchMap.CITY.otherPgsi;
				}

				// Add these only for persons
				if (entityType == "member" || entityType == "recipient"
						|| entityType == "liaison") {

					// Birth Year
					sdnVal = [];
					if (!$.pgsi.isNullOrUndefined(oEntry.dateOfBirthList)){
						if(!$.pgsi.isNullOrUndefined(oEntry.dateOfBirthList.dateOfBirthItem)) {
							sdnVal = oEntry.dateOfBirthList.dateOfBirthItem;
						}
					}
					if ($.pgsi.isNullOrUndefined(matchMap.YEAR_OF_BIRTH)) {
						matchMap.YEAR_OF_BIRTH = {
							otherSdn : sdnVal,
							otherPgsi : [ oEntity.dateOfBirth ]
						};
					}

					// Birth Place (not matched)
					sdnVal = [];
					if (!$.pgsi.isNullOrUndefined(oEntry.placeOfBirthList)
							&& !$.pgsi
									.isNullOrUndefined(oEntry.placeOfBirthList.placeOfBirth)) {
						sdnVal = placeOfBirth;
					}

					matchMap.PLACE_OF_BIRTH = {
						otherSdn : sdnVal,
						otherPgsi : [ countryOfBirth ]
					};

					// Citizenship country
					sdnVal = [];
					if (!$.pgsi.isNullOrUndefined(oEntry.citizenshipList)
							&& !$.pgsi
									.isNullOrUndefined(oEntry.citizenshipList.citizenship)) {
						sdnVal = oEntry.citizenshipList.citizenship;
					}

					matchMap.CITIZENSHIP_COUNTRY = {
						otherSdn : sdnVal,
						otherPgsi : [ countryOfBirth ]
					};

					// Nationality country
					sdnVal = [];
					if (!$.pgsi.isNullOrUndefined(oEntry.nationalityList)
							&& !$.pgsi
									.isNullOrUndefined(oEntry.nationalityList.nationality)) {
						sdnVal = oEntry.nationalityList.nationality;
					}

					matchMap.NATIONALITY_COUNTRY = {
						otherSdn : sdnVal,
						otherPgsi : [ countryOfCitizenship ]
					};

					// ID Documents
					sdnVal = [];
					if (!$.pgsi.isNullOrUndefined(oEntry.idList)
							&& !$.pgsi.isNullOrUndefined(oEntry.idList.id)) {
						sdnVal = oEntry.idList.id;
					}

					var pgsiVal = [];
					if (!$.pgsi.isNullOrUndefined(oEntity.documentList)) {
						pgsiVal = oEntity.documentList;
					}
					if ($.pgsi.isNullOrUndefined(matchMap.ID)) {
						matchMap.ID = {
							otherSdn : sdnVal,
							otherPgsi : pgsiVal
						}
					}
				}
				return matchMap;
			},

			fnFillMultiRowSection : function(oMatch, template,
					templateFillFunction) {
				if ($.pgsi.isNullOrUndefined(oMatch.otherPgsi)) {
					oMatch.otherPgsi = [];
				}
				if ($.pgsi.isNullOrUndefined(oMatch.otherSdn)) {
					oMatch.otherSdn = [];
				}
				var prevEl = template;
				var clone = true;
				var classes = "";
				var length = 0;
				var matchLength = 0;
				if (!$.pgsi.isNullOrUndefined(oMatch.topMatches)) {
					length = oMatch.topMatches.length;
					matchLength = oMatch.topMatches.length;
				}
				if (oMatch.otherSdn.length > oMatch.otherPgsi.length) {
					length += oMatch.otherSdn.length;
				} else {
					length += oMatch.otherPgsi.length;
				}

				for (var i = 0; i < length; i++) {
					var pgsiEntry = null;
					var sdnEntry = null;
					var matches = null;
					// show matches first, if necessary show pgsi entry multiple times
					if (!$.pgsi.isNullOrUndefined(oMatch.topMatches)
							&& i < oMatch.topMatches.length) {
						sdnEntry = oMatch[oMatch.topMatches[i]].sdnEntry;
						pgsiEntry = oMatch[oMatch.topMatches[i]].pgsiEntry;
						matches = oMatch[oMatch.topMatches[i]].matches;

					} else {
						// now add more rows until we're running out of other entries
						if (oMatch.otherPgsi.length > i - matchLength) {
							pgsiEntry = oMatch.otherPgsi[i - matchLength];
						}

						if (oMatch.otherSdn.length > i - matchLength) {
							sdnEntry = oMatch.otherSdn[i - matchLength];
						}

					}
					clone = true;
					classes = "";
					if (i == 0) {
						clone = false;
					}
					if (i == length - 1) {
						classes = "last";
					} else if (i > 0) {
						classes = "middle"
					}
					var dom = template;
					dom.removeClass("first last");
					if (clone) {
						dom = template.clone();
						dom.find(".section, .sdn, .pgsi").empty();
					}

					dom.addClass(classes);
					// TODO decide whether to send in oEntity based on match

					templateFillFunction(sdnEntry, pgsiEntry, matches, dom);
					dom.insertAfter(prevEl);
					prevEl = dom;

				}
				// do this last so the class doesn't carry over to the clones.
				template.addClass("first");
			},

			fnFillAddressSection : function(sdnData, oAddress, matches, dom) {
				var matchTypes = {};
				if (!$.pgsi.isNullOrUndefined(matches)) {
					for (var match = 0; match < matches.length; match++) {
						matchTypes[matches[match].sdnField] = matches[match];
					}
				}
				var isCountryMatch = !$.pgsi
						.isNullOrUndefined(matchTypes.ADDRESS_COUNTRY);
				var isCityMatch = !$.pgsi.isNullOrUndefined(matchTypes.CITY);

				if (!$.pgsi.isNullOrUndefined(sdnData)) {
					var address = "";
					address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
							sdnData.address1, address);
					address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
							sdnData.address2, address);
					address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
							sdnData.address3, address);
					if (isCityMatch) {
						address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
								"<span class='match'>" + sdnData.city
										+ "</span>", address, ", ");
					} else {
						address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
								sdnData.city, address, ", ");
					}
					address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
							sdnData.stateOrProvince, address, ", ");
					address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
							sdnData.postalCode, address);
					if (isCountryMatch) {
						address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
								"<span class='match'>" + sdnData.country
										+ "</span>", address, ", ");
					} else {
						address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
								sdnData.country, address, ", ");
					}
				}

				dom.find(".sdn").html(address);

				if (!$.pgsi.isNullOrUndefined(oAddress)) {
					address = "";
					address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
							oAddress.addressLine1, address);
					address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
							oAddress.addressLine2, address);
					address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
							oAddress.addressLine3, address);
					address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
							oAddress.addressLine4, address);
					if (isCityMatch) {
						address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
								"<span class='match'>" + oAddress.cityName
										+ "</span>", address, ", ");
					} else {
						address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
								oAddress.cityName, address, ", ");
					}
					address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
							oAddress.stateProvinceRegion.description, address,
							", ");
					address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
							oAddress.postalCode, address, ", ");
					if (isCountryMatch) {
						address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
								"<span class='match'>"
										+ oAddress.country.description
										+ "</span>", address, ", ");
					} else {
						address = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
								oAddress.country.description, address, ", ");
					}
					dom.find(".pgsi").html(address);
				}

				// fill in match section
				if ($.pgsi.isNullOrUndefined(matches)) {
					dom.find(".match").empty();
				} else {
					var matchsel = ".matchpercent";
					dom.find(matchsel).hide();
					if (matches.length < 2) {
						dom.find(".divide").hide();
					}
					var citysel = ".city";
					var countrysel = ".country";
					if (isCountryMatch) {
						dom.find(countrysel + matchsel).text(pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.sdn.view.percentMask,matchTypes.ADDRESS_COUNTRY.matchProximity * 100));
						dom.find(countrysel).show();
					}

					if (isCityMatch) {
						dom.find(citysel + matchsel).text(pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.sdn.view.percentMask,matchTypes.CITY.matchProximity * 100));
						dom.find(citysel).show();

					}

				}
			},

			fnFillIdDocSection : function(sdnData, idDoc, match, dom) {
				// SDN Section
				if (!$.pgsi.isNullOrUndefined(sdnData)) {
					var id = "";
					id = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
							sdnData.idType, id);
					if (!$.pgsi.isNullOrUndefined(match)) {
						id += "#<span class='match'>" + sdnData.idNumber
								+ "</span>"
					} else {
						id = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
								sdnData.idNumber, id, ", #");
					}
					id = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
							sdnData.idCountry, id, ", ");
					id = pgsi.pages.sdn.detail
							.fnAddStringIfAvailable($.pgsi.date.format(
									new Date(sdnData.issueDate), "mm/dd/yy",
									null), id, ", "
									+ $.pgsi.locale
											.get("pages.sdn.view.match.issued"));
					id = pgsi.pages.sdn.detail
							.fnAddStringIfAvailable(
									$.pgsi.date.format(new Date(
											sdnData.expirationDate),
											"mm/dd/yy", null),
									id,
									", "
											+ $.pgsi.locale
													.get("pages.sdn.view.match.expires")
											+ " ");

					dom.find(".sdn").html(id);
				}
				// PGSi Section
				if (!$.pgsi.isNullOrUndefined(idDoc)) {
					id = "";
					id = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
							idDoc.documentType.name, id);
					if (!$.pgsi.isNullOrUndefined(match)) {
						id += "#<span class='match'>" + idDoc.value + "</span>"
					} else {
						id = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
								idDoc.value, id, " #");
					}
					id = pgsi.pages.sdn.detail
							.fnAddStringIfAvailable(
									$.pgsi.date.format(new Date(
											idDoc.expirationDate), "mm/dd/yy",
											null),
									id,
									", "
											+ $.pgsi.locale
													.get("pages.sdn.view.match.expires")
											+ " ");
					id = pgsi.pages.sdn.detail.fnAddStringIfAvailable(
							idDoc.issueCountry.description, id, ", ");

					dom.find(".pgsi").html(id);
				}
				// match section
				pgsi.pages.sdn.detail.fnFillSingleMatch(dom, match);
			},

			fnFillYOBSection : function(sdn, pgsiEntry, match, dom) {

				if (!$.pgsi.isNullOrUndefined(sdn)) {
					var html = "<span class='header'>"
							+ pgsi.pages.sdn.detail.fnHeaderType(sdn)
							+ "</span>";

					if (!$.pgsi.isNullOrUndefined(match)) {
						html += sdn.dateOfBirth.replace(match.sdnValue,
								" <span class='match'>" + match.sdnValue
										+ "</span>")
					} else {
						html += sdn.dateOfBirth;
					}
					dom.find(".sdn").html(html);
				}
				if (!$.pgsi.isNullOrUndefined(pgsiEntry)) {
					if (!$.pgsi.isNullOrUndefined(match)) {
						dom.find(".pgsi").html(
								$.pgsi.date.format(new Date(pgsiEntry),
										"mm/dd/yy", null)
										.replace(
												match.systemValue,
												"<span class='match'>"
														+ match.systemValue
														+ "</span>"));
					} else {
						dom.find(".pgsi").text(
								$.pgsi.date.format(new Date(pgsiEntry),
										"mm/dd/yy", null));
					}
				}

				// match section
				pgsi.pages.sdn.detail.fnFillSingleMatch(dom, match);
			},

			fnFillBasicSection : function(sdn, sdnPropName, pgsiEntry, match,
					dom) {
				if (!$.pgsi.isNullOrUndefined(sdn)) {
					var sdnVal = sdn[sdnPropName];
					if (!$.pgsi.isNullOrUndefined(match)) {
						sdnPropName = sdnPropName.replace(match.sdnValue,
								"<span class='match'>" + match.sdnValue
										+ "</span>");
					}
					dom.find(".sdn").html(
							"<span class='header'>"
									+ pgsi.pages.sdn.detail.fnHeaderType(sdn)
									+ "</span>" + sdn[sdnPropName])
				}
				if (!$.pgsi.isNullOrUndefined(pgsiEntry)) {
					if (!$.pgsi.isNullOrUndefined(match)) {
						dom.find(".pgsi").html(
								"<span class='match'>" + pgsiEntry + "</span>");
					} else {
						dom.find(".pgsi").text(pgsiEntry);
					}
				}

				// match section
				pgsi.pages.sdn.detail.fnFillSingleMatch(dom, match);
			},

			fnFillPOBSection : function(sdn, pgsiEntry, match, dom) {
				pgsi.pages.sdn.detail.fnFillBasicSection(sdn,
						"placeOfBirthItem", pgsiEntry, match, dom);
			},
			fnFillCitizenshipSection : function(sdn, pgsiEntry, match, dom) {
				pgsi.pages.sdn.detail.fnFillBasicSection(sdn, "country",
						pgsiEntry, match, dom);
			},
			fnFillNationalitySection : function(sdn, pgsiEntry, match, dom) {
				pgsi.pages.sdn.detail.fnFillBasicSection(sdn, "country",
						pgsiEntry, match, dom);
			},

			fnAddStringIfAvailable : function(data, output, separator) {
				var sep = separator;
				// if the main string is empty, skip separator;
				if (output.length == 0) {
					sep = "";
				} else if ($.pgsi.isNullOrUndefined(sep)) {
					sep = " ";
				}
				if (!$.pgsi.isNullOrUndefined(data) && data.trim().length > 0) {
					return output + sep + data;
				}
				return output;
			},

			fnFillSingleMatch : function(dom, match) {
				if ($.pgsi.isNullOrUndefined(match) || match.length == 0) {
					dom.find(".match").empty();
				} else {
					dom.find(".matchpercent").text(
							pgsi.util.page.fnInsertMaskNumeric(
									pgsi.pages.sdn.view.percentMask,
									match.matchProximity * 100));
				}
			},
			fnHeaderType : function(sdn) {
				if (sdn.mainEntry) {
					return $.pgsi.locale.get("pages.sdn.view.detail.mainentry");
				}
				return $.pgsi.locale.get("pages.sdn.view.detail.other");
			},

			fnFillName : function(firstName, lastName, targetUlDom) {
				var name = "";
				name = pgsi.pages.sdn.detail.fnAddStringIfAvailable(firstName,
						name);
				name = pgsi.pages.sdn.detail.fnAddStringIfAvailable(lastName,
						name);
				targetUlDom.append("<li>" + name + "</li>");
			},

			fnFillNameMatchDetail : function(matches) {
				if (!$.pgsi.isNullOrUndefined(matches) && matches.length > 1) {
					var target = $("#name-match-detail tbody");
					var template = $("<tr><td></td><td></td><td></td></tr>");
					var clone = null;
					var values = null;
					for (var i = 0; i < matches.length; i++) {
						values = [ matches[i].sdnValue, matches[i].systemValue,
								matches[i].matchProximity ]
						clone = template.clone();
						clone.find("td").each(function(index) {
							$(this).text(values[index]);
						});
						target.append(clone);
					}
				} else {
					$("#link-name-match, #name-match-detail").remove();
				}
			}
		};
	</script>

</sec:authorize>