/**
 * String Object
 */
if (!String.prototype.contains) {
	String.prototype.contains = function(substr) {
		return this.indexOf(substr) !== -1;
	};
}
if (typeof String.prototype.trim !== 'function') {
	String.prototype.trim = function() {
    	return this.replace(/^\s+|\s+$/g, '');
	}
}