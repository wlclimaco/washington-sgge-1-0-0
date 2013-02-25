<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<ul class="ui-state-highlight summary">

	<li class="hide"><span id="id-process"> </span></li>
	<li>
		<em> 
			<s:text name="process.page.text"> 
				<s:param >
					<strong><span id="action-name"></span></strong>
				</s:param>
				<s:param >
					<span id="hour-completed"></span>
				</s:param>
				<s:param >
					<span id="minute-completed"></span>
				</s:param>
			</s:text>
			<br />
		</em>
		<!--  
		<p>
			<s:text name="process.page.initiateAction">
				<s:param><span id="action-type"></span></s:param>
				<s:param><span id="action-name-group"></span></s:param>
			</s:text>
		</p>
		-->
	</li>
	<li class="success">
		<span class="icon-small"></span> 
		<span id="count-success"></span> 
		(<span id="percent-success"></span>%) 
		<s:text name="process.page.succeeded" /> 
		<small>
			<a class="launch export export-success" href="#"> <s:text name="process.page.export"/> </a>
		</small>
	</li>
	<li class="fail">
		<span class="icon-small"></span>
		<span id="count-failure"></span> 
		(<span id="percent-failure"></span>%) 
		<s:text name="process.page.failed" />	
		<small><a class="launch export export-fail" href="#"><s:text name="process.page.export"/></a></small>
		<ul id="summary-container" class="summary-container"></ul>
	</li>
	
	<li>  

		<div class="selected-points">
			<div class="export-select tools">
				<ul class="link-list">
					<li class="export-type last">
						<small>
							<span id="displaying"> Displaying 50 of 504 </span> - 
							<strong>Export</strong>: <a class="launch export export-fail" href="#">CSV</a>
						</small>
					</li>
				</ul>
			</div>            
			<table id="summary-meters-table">
				<tbody>
					
				</tbody>
			</table>
		</div>
		
	</li> 
	
</ul>