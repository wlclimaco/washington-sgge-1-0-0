<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div id="profile-main">
	<fieldset>
		<legend><s:text name="profile.page.languagepreference" /></legend>
		<ul>
			<li>
				<select id="language">
					<option value="en_US">
						<s:text name="profile.page.englishEN" />
					</option>
					<option value="pt_BR">
						<s:text name="profile.page.portugueseBR" />
					</option>
					<option value="es_MX">
						<s:text name="profile.page.spanish" />
					</option>
				</select>
			</li>
		</ul>
	</fieldset>
	<fieldset>
		<legend><s:text name="profile.page.defaulttimezone" /></legend>
		<ul>
			<li>
				<select id="timezone" name="timezone" class="chzn-select" data-placeholder="Select Time Zone">
					<optgroup label="US">                                                      
						<option value="US/Alaska">Alaska </option>
						<option value="US/Aleutian">Aleutian </option>
						<option value="US/Arizona">Arizona </option>
						<option value="CST">Central </option>
						<option value="US/East-Indiana">East-Indiana </option>
						<option value="US/Eastern">Eastern </option>
						<option value="US/Hawaii0">Hawaii </option>
						<option value="US/Indiana-Starke">Indiana-Starke </option>
						<option value="US/Michigan">Michigan </option>
						<option value="MST">Mountain </option>
						<option value="SystemV/PST8">Pacific </option>
						<option value="US/Pacific-New">Pacific-New </option>
						<option value="Pacific/Samoa">Samoa </option>
					</optgroup>	                                                              
					<optgroup label="Canada">
						<option value="America/Toronto">Atlantic </option>
						<option value="Canada/Central">Central </option>
						<option value="Canada/East-Saskatchewan">East-Saskatchewan </option>
						<option value="Canada/Eastern">Eastern </option>
						<option value="Canada/Mountain">Mountain </option>
						<option value="Canada/Newfoundland">Newfoundland </option>
						<option value="Canada/Pacific">Pacific </option>
						<option value="Canada/Saskatchewan">Saskatchewan </option>
						<option value="Canada/Yukon">Yukon </option>
					</optgroup>	
					<optgroup label="Europe">
						<option value="Europe/Amsterdam">Amsterdam </option>
						<option value="Europe/Andorra">Andorra </option>
						<option value="Europe/Athens">Athens </option>
						<option value="Europe/Belfast">Belfast </option>
						<option value="Europe/Belgrade">Belgrade </option>
						<option value="Europe/Berlin">Berlin </option>
						<option value="Europe/Bratislava">Bratislava </option>
						<option value="Europe/Brussels">Brussels </option>
						<option value="Europe/Bucharest">Bucharest </option>
						<option value="Europe/Budapest">Budapest </option>
						<option value="Europe/Chisinau">Chisinau </option>
						<option value="Europe/Copenhagen">Copenhagen </option>
						<option value="Europe/Dublin">Dublin </option>
						<option value="Europe/Gibraltar">Gibraltar </option>
						<option value="Europe/Guernsey">Guernsey </option>
						<option value="Europe/Helsinki">Helsinki </option>
						<option value="Europe/Isle_of_Man">Isle_of_Man </option>
						<option value="Europe/Istanbul">Istanbul </option>
						<option value="Europe/Jersey">Jersey </option>
						<option value="Europe/Kaliningrad">Kaliningrad </option>
						<option value="Europe/Kiev">Kiev </option>
						<option value="Europe/Lisbon">Lisbon </option>
						<option value="Europe/Ljubljana">Ljubljana </option>
						<option value="Europe/London">London </option>
						<option value="Europe/Luxembourg">Luxembourg </option>
						<option value="Europe/Madrid">Madrid </option>
						<option value="Europe/Malta">Malta </option>
						<option value="Europe/Mariehamn">Mariehamn </option>
						<option value="Europe/Minsk">Minsk </option>
						<option value="Europe/Monaco">Monaco </option>
						<option value="Europe/Moscow">Moscow </option>
						<option value="Europe/Nicosia">Nicosia </option>
						<option value="Europe/Oslo">Oslo </option>
						<option value="Europe/Paris">Paris </option>
						<option value="Europe/Podgorica">Podgorica </option>
						<option value="Europe/Prague">Prague </option>
						<option value="Europe/Riga">Riga </option>
						<option value="Europe/Rome">Rome </option>
						<option value="Europe/Samara">Samara </option>
						<option value="Europe/San_Marino">San_Marino </option>
						<option value="Europe/Sarajevo">Sarajevo </option>
						<option value="Europe/Simferopol">Simferopol </option>
						<option value="Europe/Skopje">Skopje </option>
						<option value="Europe/Sofia">Sofia </option>
						<option value="Europe/Stockholm">Stockholm </option>
						<option value="Europe/Tallinn">Tallinn </option>
						<option value="Europe/Tirane">Tirane </option>
						<option value="Europe/Tiraspol">Tiraspol </option>
						<option value="Europe/Uzhgorod">Uzhgorod </option>
						<option value="Europe/Vaduz">Vaduz </option>
						<option value="Europe/Vatican">Vatican </option>
						<option value="Europe/Vienna">Vienna </option>
						<option value="Europe/Vilnius">Vilnius </option>
						<option value="Europe/Volgograd">Volgograd </option>
						<option value="Europe/Warsaw">Warsaw </option>
						<option value="Europe/Zagreb">Zagreb </option>
						<option value="Europe/Zaporozhye">Zaporozhye </option>
						<option value="Europe/Zurich">Zurich </option>
					</optgroup>	
					<optgroup label="Australia">
						<option value="Australia/ACT">ACT </option>
						<option value="Australia/Adelaide">Adelaide</option>
						<option value="Australia/Brisbane">Brisbane </option>
						<option value="Australia/Broken_Hill">Broken_Hill </option>
						<option value="Australia/Canberra">Canberra </option>
						<option value="Australia/Currie">Currie </option>
						<option value="Australia/Darwin">Darwin </option>
						<option value="Australia/Eucla">Eucla </option>
						<option value="Australia/Hobart">Hobart </option>
						<option value="Australia/LHI">LHI </option>
						<option value="Australia/Lindeman">Lindeman </option>
						<option value="Australia/Lord_Howe">Lord_Howe </option>
						<option value="Australia/Melbourne">Melbourne </option>
						<option value="Australia/NSW">NSW </option>
						<option value="Australia/North">North </option>
						<option value="Australia/Perth">Perth </option>
						<option value="Australia/Queensland">Queensland </option>
						<option value="Australia/South">South </option>
						<option value="Australia/Sydney">Sydney </option>
						<option value="Australia/Tasmania">Tasmania </option>
						<option value="Australia/Victoria">Victoria </option>
						<option value="Australia/West">West </option>
						<option value="Australia/Yancowinna">Yancowinna </option>
					</optgroup>	
				</select>
			</li>
		</ul>
	</fieldset>                                
	<fieldset>
		<legend><s:text name="profile.page.defaultdateformat" /></legend>
		<ul>
			<li>
				<select name="dateformat" id="dateformat">
				   <option value="<s:text name="profile.page.endataformat" />"><s:text name="profile.page.endataformat" /></option>
				   <option value="<s:text name="profile.page.ptdataformat" />"><s:text name="profile.page.ptdataformat" /></option>
				</select>
			</li>
		</ul>
	</fieldset>
	<fieldset>
    	<legend><s:text name="profile.page.pagesize" /></legend>
    	<ul>
        	<li>
        		<select name="pagesize" id="pagesize">
					<option value="<s:text name="option.pagesize.fifteen" />"><s:text name="option.pagesize.fifteen" /></option>
					<option value="<s:text name="option.pagesize.twentyFive" />"><s:text name="option.pagesize.twentyFive" /></option>
					<option value="<s:text name="option.pagesize.fifty" />"><s:text name="option.pagesize.fifty" /></option>
					<option value="<s:text name="option.pagesize.hundred" />"><s:text name="option.pagesize.hundred" /></option>
            	</select>
			</li>
		</ul>
	</fieldset>
	<fieldset>
		<legend><s:text name="profile.page.unitsConversion" /></legend>
		<ul>
			<li class="checkbox"><input id="unit-convert" type="checkbox" name="" /><label><s:text name="profile.page.consertUnitsDescription" /> </label></li>
			<li>
				<table id="units-table" class="list">
					<thead>	
						<tr>
							<th><s:text name="profile.page.convertUnit.value" /></th>	
							<th><s:text name="profile.page.convertUnit.symbol" /></th>
							<th><s:text name="profile.page.convertUnit.name" /></th>                                     	
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="unit-kwh" ><s:text name="profile.page.convertUnit.base" /><sup></sup></td>
							<td><s:text name="profile.page.convertUnit.kwh" /></td>
							<td><s:text name="profile.page.convertUnit.kilowattHhour" /></td>                                                    
						</tr>
						<tr class="alt">
							<td class="unit-mwh"><s:text name="profile.page.convertUnit.base" /><sup></sup></td>
							<td><s:text name="profile.page.convertUnit.MWh" /></td>
							<td><s:text name="profile.page.convertUnit.megawattHour" /></td>                                                    
						</tr>
						<tr>
                        	<td class="unit-gwh"><s:text name="profile.page.convertUnit.base" /><sup></sup></td>
                            <td><s:text name="profile.page.convertUnit.GWh" /></td>
                            <td><s:text name="profile.page.convertUnit.gigawattHour" /></td>                                                    
						</tr>
						<tr class="alt">
							<td class="unit-twh"><s:text name="profile.page.convertUnit.base" /><sup></sup></td>
                            <td><s:text name="profile.page.convertUnit.TWh" /></td>
                            <td><s:text name="profile.page.convertUnit.terawattHour" /></td>                                                    
						</tr>
						<tr>
							<td class="unit-pwh"><s:text name="profile.page.convertUnit.base" /><sup></sup></td>
                            <td><s:text name="profile.page.convertUnit.PWh" /></td>
                            <td><s:text name="profile.page.convertUnit.petawattHour" /></td>                                                    
						</tr>
					</tbody>				
				</table>
			</li>
		</ul>
        <ul>
			<li class="submit-row">
				<a id="save" href="#" class="button save-settings"><s:text name="profile.page.save" /></a> 
				<s:text name="profile.page.or" /> 
				<a id="cancel" href="systemsettings/ajax.list.action" class="cancel alist"><s:text name="profile.page.cancel" /></a>
			</li>
		</ul>                                 
	</fieldset> 

</div>