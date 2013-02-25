<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="summary communication-summary">
	<div class="highlight clear hide">	
              <p><em><s:text name="commons.pages.your"/> <strong class="action-type"></strong> (<s:text name="commons.pages.id"/>: <span class="process-id"></span>) <s:text name="commons.pages.request"/>, <strong class="request-by"></strong> <s:text name="longRunning.table.header.completedIn"/> <span class="time"></span>.</em></p> 
              <p><s:text name="summary.text.actionInitiatedBy"/> &ldquo;<span class="initiate-by"></span>&rdquo;</p></p>
         	</div>
          <br>
          <div id="detail-header-container" class="ss-widget-table-summary-kpi">
              <table id="informationTable" class="summary-kpi">
                  <tbody>
					   <tr>
					   </tr>
	               </tbody>
              </table>
          </div>    
          <h4 class="fail hide"></h4>
           <div class="selected-points selected-point hide">
              <div class="export-select tools">
                  <ul class="link-list">
                      <li class="export-type last"><small>Displaying <span class="size"></span> of <span class="size"></span><strong> Export ALL</strong>: <a href="" class="csv">CSV</a></small></li>
                  </ul>
              </div>            
              <table id="tableCommunicationSummary" class="small-table">
                  <thead>                    
                  </thead>
                  <tbody>
             	  </tbody>
              </table>
         </div>
      </div>      