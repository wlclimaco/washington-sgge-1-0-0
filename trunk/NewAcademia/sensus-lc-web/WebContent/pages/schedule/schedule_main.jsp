<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
  <style>
    .cell-title {
      font-weight: bold;
    }

    .cell-effort-driven {
      text-align: center;
    }

    .cell-selection {
      border-right-color: silver;
      border-right-style: solid;
      background: #f5f5f5;
      color: gray;
      text-align: right;
      font-size: 10px;
    }

    .slick-row.selected .cell-selection {
      background-color: transparent; /* show default selected row background */
    }
  </style>
<div style="position:relative">
  <div id="grid_main" style="width:100%;">
    <div class="grid-header" style="width:100%">
      <label>SlickGrid</label>
      <span style="float:right" class="ui-icon ui-icon-search" title="Toggle search panel"
            onclick="toggleFilterRow()"></span>
    </div>
    <div id="myGrid" style="width:100%;height:500px;"></div>
    <div id="pager" style="width:100%;height:20px;"></div>
  </div>
</div>

<div id="inlineFilterPanel" style="display:none;background:#dddddd;padding:3px;color:black;">
  Show tasks with title including <input type="text" id="txtSearch2">
  and % at least &nbsp;
  <div style="width:100px;display:inline-block;" id="pcSlider2"></div>
</div>

<jsp:include page="../../scripts/pages/grupoMuscular/grupo_muscular_main.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/grupoMuscular/grupo_muscular_init.js.jsp" flush="true"/>

