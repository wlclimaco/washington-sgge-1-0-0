/**
* 
* Create li element for Chosen Plugin jQuery
* @author QAT-Brazil
* 
* @param id, the represent value ID on database
* @param label, the label that represent the ID
*/
function createChosenElement(id, label) {
	
	var li = selenium.browserbot.getCurrentWindow().document.createElement("LI");
	li.id = "select_tags_chzn_c_" + id;
	li.setAttribute("class", "search-choice");
	
	var span = selenium.browserbot.getCurrentWindow().document.createElement("SPAN");
	span.innerHTML = label;
	
	var link = selenium.browserbot.getCurrentWindow().document.createElement("A");
	link.href = "javascript:void(0)";
	link.id = id;
	link.title = label;
	link.rel = id;
	link.setAttribute("class", "search-choice-close");
	
	li.appendChild(span);
	li.appendChild(link);
	return li;
}

/**
*
* Add new comand on Selenium IDE that makes easier work with Chosen Plugin jQuery Elements on page
* @param locator, the id locator of hide select html element used by Chosen Plugin jQuery
* @param label, the label of select option
*/
Selenium.prototype.doAddNewChosen = function(locator, label) {
	var idLocator = "id=" + locator;
	var ul = this.page().findElement("css=#" + locator + "_chzn ul.chzn-choices");
	var select = this.page().findElement(idLocator);
	
	this.doSelect(idLocator, label);
	var id = this.getSelectedValue(idLocator);
	
	if (!this.isElementPresent("id=" + locator + "_chzn_c_" + id)) {
		var chozen = createChosenElement(id, label);
		ul.appendChild(chozen);
	}
	
	if (this.isElementPresent("css=#" + locator + "_chzn li.search-field")) {
		var input = this.page().findElement("css=#" + locator + "_chzn li.search-field input");
		input.value = "";
		//ul.removeChild(search_li);
	}
}