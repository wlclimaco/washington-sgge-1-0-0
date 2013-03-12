/**
* 
* Add class to element located
* @author QAT-Brazil
* 
* @param locator, a locator to be found by findElement function
* @param clazz, a string to be add on class atributte
*/
Selenium.prototype.doAddClass = function(locator, clazz) {
	var element = this.page().findElement(locator);
	element.setAttribute('class', clazz);
}