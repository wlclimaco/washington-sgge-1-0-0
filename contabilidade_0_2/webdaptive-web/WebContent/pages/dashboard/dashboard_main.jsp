<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>

<div class="dashboard">
    <div ng-app="myApp">
  <div ng-controller="demoController as demo">
    <h2 class="page-header">Real world example</h2>
    <div class="bs-callout bs-callout-info">
      <h4>Overview</h4>
      <p>Example of fetching data from the github api.</p>
      <p>
        Demonstrates <em>one</em> of the ways data can be loaded into the table asynchronously by supplying <code>NgTableParams</code> a custom <code>getData</code> function.
      </p>
    </div>
    <div loading-container="demo.tableParams.settings().$loading">
      <table ng-table="demo.tableParams" class="table table-condensed table-bordered table-striped">
        <tr ng-repeat="issue in $data">
          <td data-title="'#'">
            <a target="_blank" ng-href="{{issue.html_url}}">{{issue.number}}</a>
          </td>
          <td data-title="'Theme'">{{issue.title}}</td>
          <td data-title="'Opened by'">
            <a target="_blank" ng-href="{{issue.user.url}}">
              <nobr><img width="16" height="16" ng-src="{{issue.user.avatar_url}}" /> {{issue.user.login}}
              </nobr>
            </a>
          </td>
          <td data-title="'Updated'">
            <nobr>{{issue.updated_at | date:'medium'}}</nobr>
          </td>
        </tr>
      </table>
    </div>
  </div>
</div>
</div>

<jsp:include page="../../scripts/pages/dashboard/dashboard_init.js.jsp" flush="true" />