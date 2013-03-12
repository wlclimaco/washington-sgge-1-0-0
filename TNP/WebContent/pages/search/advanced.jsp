<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

	<div class="yui-ge point-type-navigation header rounded-top-corners">
		<div class="yui-u first">
			<ul class="tabs">
				<li><a class="afilter" href="smartpoint/ajax.list.action?sd=true"><s:text name="search.tabs.search" /></a></li>
				<li><a id="ajax-button" href="search/ajax.advanced.action" class="active gradient-vert"><s:text name="search.tabs.advanced" /></a></li>
				<li><a href="search/ajax.list.action" class="gradient-vert alist"><s:text name="search.tabs.saved" /></a></li>
			</ul>
		</div>
	</div>
	<%-- START Main Page Content --%>
	<div class="content-inner">
		<!-- Messaging -->
		<div id="messaging-main" class="messaging" style="display: none;"><span	class="message"></span><a href="" class="remove"><s:text name="message.action.close" /></a></div>
		<%-- START Main Content Container --%>
		<div class="yui-main">
			<div id="advanced-form">
			<div id="yui-main">
				<%-- Header --%>
				<div class="content-header">
					<h1><s:text name="advanced.page.advancedSearch" /></h1>
					<p class="description"><s:text name="advanced.page.description" /></p>
				</div>
				
				<div class="advanced_search_container highlight yui-gf">
						<div class="yui-u first">                                
							<h5><s:text name="advanced.page.flexnetId" />
								<small class="advanced_small"><s:text name="advanced.page.tipParcialMatchs" /></small>
							</h5>
						</div>
						<div class="yui-u">
							<input type="text" id="spSearch" class="adv_search_input" />
						</div>        			
				</div>
				<fieldset>
					<!-- START: Alerts Search -->
					<div class="advanced_search_container alerts yui-gf">
						<div class="yui-u first">
							<h5><s:text name="advanced.page.alerts" />
								<small><s:text name="advanced.page.tipAlertTypes" /></small>
							</h5>
						</div>
						<div class="yui-g">
								<div id="alarm-container" class="yui-u first">
									<h6><s:text name="advanced.page.alarms" /></h6>
										<ul>
											<s:iterator value="alarmList">
												<li class="checkbox"><input type="checkbox" id="id" value="<s:property value='id' />" /><label for=""><s:property value="value" /></label></li>
											</s:iterator>
										</ul>
								</div>
								<div id="warning-container" class="yui-u">
									<h6><s:text name="advanced.page.warnings" /></h6>
										<ul>
											<s:iterator value="warningList">
												<li class="checkbox"><input type="checkbox" id="id" value="<s:property value='id' />" /><label for=""><s:property value="value" /></label></li>
											</s:iterator>
										</ul>
								</div>
						   </div>	
					</div>
					<!-- END: Alerts Search -->
					<!-- START: Group Search -->
					<div class="advanced_search_container group yui-gf">
						<div class="yui-u first">
							<h5><s:text name="advanced.page.group" />
								<small><s:text name="advanced.page.tipGroups" /></small>
							</h5>
						</div>
						<div id="group-container" class="collapse checkBoxUl yui-u">
							<ul class="ui-listcombobox"></ul>
							<s:select id="combobox" list="groupList" listKey="id" listValue="value" cssClass="listcombobox" /> 
						</div>
					</div>
					<!-- END: Group Search -->

					<!-- START: Tags Search -->
					<div class="advanced_search_container group yui-gf">
						<div class="yui-u first">
							<h5><s:text name="advanced.page.tags" />
								<small><s:text name="advanced.page.tipTags" /></small>
							</h5>
						</div>
						<div id="tag-container" class="collapse checkBoxUl yui-u">
							<ul class="ui-listcombobox"></ul>
							<s:select id="comboboxTag" list="tagList" listKey="id" listValue="value" cssClass="listcombobox" /> 
						</div>
					</div>
					<!-- END: Tags Search -->
					<!-- START: Schedules Search -->
					<div class="advanced_search_container schedule yui-gf">
						<div class="yui-u first">
							<h5><s:text name="advanced.page.schedules" />
								<small><s:text name="advanced.page.tipSchedules" /></small>
							</h5>
						</div>
						<div class="yui-g">    
							<div id="event-container" class="collapse checkBoxUl yui-u first">
								<h6><s:text name="advanced.page.event" /></h6>
								<ul class="ui-listcombobox"></ul>
								<s:select id="comboboxTag" list="eventScheduleList" listKey="id" listValue="value" cssClass="listcombobox" />                  
							</div>
							<div id="offset-container" class="collapse checkBoxUl yui-u">
								<h6><s:text name="advanced.page.offset" /></h6>
								<ul class="ui-listcombobox"></ul>
								<s:select id="comboboxTag" list="offsetScheduleList" listKey="id" listValue="value" cssClass="listcombobox" />                
							</div>
					   </div>	
					</div>
					<!-- END: Schedules Search -->
					<!-- START: Address Search -->
					<div class="advanced_search_container address yui-gf">
						<div class="yui-u first">
							<h5><s:text name="advanced.page.address" />
								<small><s:text name="advanced.page.tipAddress" /></small>
							</h5>
						</div>
						<div class="yui-u">
							<ul class="collapse">
								<li><label for="filterStreet"><s:text name="advanced.page.street" /></label><input id="filter-street" type="text" /> </li>
								<li><label for="filterCity"><s:text name="advanced.page.city" /></label><input id="filter-city" type="text" /></li>
								<li><label for="filterZip"><s:text name="advanced.page.zip" /></label><input id="filter-zip" type="text" class="short" /></li>
							</ul>
						</div>
					</div>
					<!-- END: Address Search -->
				</fieldset>
				<fieldset>
					<legend><a href="" class="text_button spindown"><span class="ui-icon-triangle-1-e ui-icon"></span> <s:text name="advanced.page.status" /></a></legend>   
					<div class="spindown_child">      
						<!-- START: messages Search -->
						<div class="advanced_search_container messages yui-gf">
							<div class="yui-u first">
								<h5><s:text name="advanced.page.messages" />
									<small><s:text name="advanced.page.tipMessages" /></small>
								</h5>
							</div>
							<div id="frequency" class="yui-u">
								<label><s:text name="advanced.page.noMessagesReceived" /> </label>
								<select>
									<option value="1"><s:text name="option.frequency.hour" /></option>
									<option value="7"><s:text name="option.frequency.week" /></option>
									<option value="30"><s:text name="option.frequency.month" /></option>
								</select>
							</div>
						</div>
						<!-- END: messages Search -->
						<!-- START: Metrology Search -->
						<div class="advanced_search_container metrology yui-gf">
							<div class="yui-u first">
								<h5><s:text name="advanced.page.metrology" />
									<small><s:text name="advanced.page.tipMetrology" /></small>
								</h5>
							</div>
							<div class="yui-u">
								<ul>
									<li class="voltage">
										<label><s:text name="advanced.page.voltage" /></label>
										<select class="short">
											<option value="<s:text name="gt" />"><s:text name="option.operator.greater" /></option>
											<option value="<s:text name="lt" />"><s:text name="option.operator.less" /></option>
										</select>
										<input type="text" id="voltage" class="short" />
									</li>
									<li class="current">
										<label><s:text name="advanced.page.current" /></label>
										<select class="short">
											<option value="<s:text name="gt" />"><s:text name="option.operator.greater" /></option>
											<option value="<s:text name="lt" />"><s:text name="option.operator.less" /></option>                       
										</select>
										<input type="text" id="current" class="short" />
									</li>
									<li class="consumption">
										<label><s:text name="advanced.page.consumption" /></label>
										<select class="short">
											<option value="<s:text name="gt" />"><s:text name="option.operator.greater" /></option>
											<option value="<s:text name="lt" />"><s:text name="option.operator.less" /></option>                             
										</select>
										<input type="text" id="consumption" class="short" />
									</li>
								</ul>
							</div>
						</div>
					<!-- END: Metrology Search -->
					</div>
				</fieldset>
				<fieldset>
					<legend><a href="" class="text_button spindown"><span class="ui-icon-triangle-1-e ui-icon"></span> <s:text name="advanced.page.configuration" /></a></legend>                    
					<div class="spindown_child">      
						<!-- START: Configuration Search -->
						<div class="advanced_search_container configuration yui-gf">
							<div class="yui-u first">
								<h5><s:text name="advanced.page.configuration" />
									<small><s:text name="advanced.page.tipConfiguration" /></small>
								</h5>
							</div>
							<div class="yui-u">
								<ul class="collapse">
									<li id="locked"><label for=""><s:text name="advanced.page.locked" /></label> 
										<div class="radio"><input type="checkbox" /> <label for=""><s:text name="advanced.page.yes" /></label></div> 
									</li>
									<li><label for=""><s:text name="advanced.page.serialNumber" /></label><input id="serial" type="text" /></li>
									<li id="light-type"><label for=""><s:text name="advanced.page.lightType" /></label>
										<s:select list="lampTypeList" listKey="id" listValue="value" multiple="false" size="1" required="false" />
									</li>
									<li id="wattage"><label for=""><s:text name="advanced.page.wattage" /></label>
										<s:select list="wattageList" listKey="id" listValue="value"	multiple="false" size="1" required="false" />
									</li>
									<li id="range"><label for=""><s:text name="advanced.page.inputVoltageRange" /></label>
										<s:select list="inputVoltageList" listKey="id" listValue="value" multiple="false" size="1" required="false" />
									</li>
									<li id="color-temperature"><label for=""><s:text name="advanced.page.colorTemperature" /></label>
										<s:select list="colorTemperatureList" listKey="id" listValue="value"	multiple="false" size="1" required="false" />
									</li>
									<li><label for=""><s:text name="advanced.page.sensusPartNumber" /></label><input id="part-number" type="text" /></li>
									<li id="firmware"><label for=""><s:text name="advanced.page.firmwareVersion" /></label>
										<s:select list="firmwareVersionList" listKey="id" listValue="value"	multiple="false" size="1" required="false" />
									</li>
								</ul>
							</div>
						</div>
						<!-- END: Address Search -->                        
					 </div>
			   </fieldset>
			   <fieldset>
					<legend><a href="" class="text_button spindown"><span class="ui-icon-triangle-1-e ui-icon"></span> <s:text name="advanced.page.dateSortPresentation" /></a></legend> 
					<div class="spindown_child">      
						 <!-- START: Date Search -->
						<div class="advanced_search_container Date yui-gf">
							<div class="yui-u first">
								<h5><s:text name="advanced.page.date" />
									<small><s:text name="advanced.page.tipDate" /> </small>
								</h5>
							</div>
							<div class="yui-u">
								<ul>
									<li>
										<label><s:text name="advanced.page.dateAdded" /> <br/><small><s:text name="advanced.page.onlyFindResults" /> <strong><s:text name="advanced.page.added" /></strong></small></label>
										<ul class="list_input">
											 <li id="after" class="checkbox"><label><input type="checkbox"/> <s:text name="advanced.page.after" /></label> <input type="text" class="datepicker after"></li>
											 <li id="before" class="checkbox"><label><input type="checkbox"/> <s:text name="advanced.page.before" /></label> <input type="text" class="datepicker before"></li>
										</ul>
									</li>
								</ul>
							</div>
						</div>
						<!-- END: Alerts Search -->                                     
						 <!-- START: Date Search -->
						<div class="advanced_search_container view yui-gf">
							<div class="yui-u first">
								<h5><s:text name="advanced.page.presentation" />
									<small><s:text name="advanced.page.tipPresentation" /></small>
								</h5>
							</div>
							<div class="yui-u">
								<ul>
									<li id="sort"><label><s:text name="advanced.page.sort" /></label>
										<select>
											<option value="flexnetId"><s:text name="option.sort.flexnetId" /></option>
											<option value="poleId"><s:text name="option.sort.poleId" /></option>
										</select>
									</li>
									<li id="display"><label><s:text name="advanced.page.display" /></label>
										<select>
											<option value="<s:text name="option.pagesize.fifteen" />"><s:text name="option.pagesize.fifteen" /></option>
											<option value="<s:text name="option.pagesize.twentyFive" />"><s:text name="option.pagesize.twentyFive" /></option>
											<option value="<s:text name="option.pagesize.fifty" />"><s:text name="option.pagesize.fifty" /></option>
											<option value="<s:text name="option.pagesize.hundred" />"><s:text name="option.pagesize.hundred" /></option>
										</select>
									</li>
								</ul>
						   </div>
						</div>
					<!-- END: Alerts Search -->   
					</div>                                  
			   </fieldset>
				<!-- START: Submit Row -->
				<div class="yui-gf">
					<div class="yui-u first"></div>
					<div class="submit-row yui-u"><a href="#" id="search" class="button"><s:text name="advanced.page.search" /></a> <a href="#" id="save_search" class="button save_nostyle"><s:text name="advanced.page.saveAndSearch" /></a></div>
				</div>
				</div>
				<!-- END: Submit Row -->
			</div>
		</div>            
	</div>    	