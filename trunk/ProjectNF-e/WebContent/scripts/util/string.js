/**
 * @fileoverview Common string-related functionality.
 * @author Anke Doerfel-Parker
 */
/**
 * Adds a .Net-type format function to string object.
 * 
 * @param parameter1,
 *            parameter2, ... the values to fill into the string
 * @return the string with parameters substituted.
 */
String.prototype.format = function() {
	var txt = this, i = arguments.length;
	while (i--) {
		txt = txt.replace(new RegExp('\\{' + i + '\\}', 'gm'), arguments[i]);
	}
	return txt;
};


/**
 * Add escape character function. 
 * This function format special characters to escape character.
 * In the future, add more escape characters to if block.
 * 
 * @param parameter1 - The special character
 * 
 * @return the string with parameters substituted.
 *   
 */
String.prototype.formatSpecial = function() {
	var txt = this,
	char = arguments[0],
	escape = "";
	
	if (char === "'"){
		escape = "&#39;";
	}
	
	if (txt.indexOf(char) !== -1){
		for (var i=0; i < txt.length; i++) {
			if (txt.charAt(i) === char){
				txt = txt.replace(char, escape);
			}
		} 
	}
	
	return txt;
};
