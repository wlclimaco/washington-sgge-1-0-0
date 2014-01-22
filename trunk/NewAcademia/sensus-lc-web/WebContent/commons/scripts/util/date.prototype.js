if (!Date.prototype.toISOString) {

	Date.prototype.toISOString = function() {
	    function pad(n) { return n < 10 ? '0' + n : n }
	    return this.getUTCFullYear() + '-'
	        + pad(this.getUTCMonth() + 1) + '-'
	        + pad(this.getUTCDate()) + 'T'
	        + pad(this.getUTCHours()) + ':'
	        + pad(this.getUTCMinutes()) + ':'
	        + pad(this.getUTCSeconds()) + '.'
			+ pad(this.getMilliseconds()) + 'Z';
	};

}

Date.isLeapYear = function (year) {
    return (((year % 4 === 0) && (year % 100 !== 0)) || (year % 400 === 0));
};

Date.getDaysInMonth = function (year, month) {
    return [31, (Date.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month];
};

Date.prototype.isLeapYear = function () {
    var y = this.getFullYear();
    return (((y % 4 === 0) && (y % 100 !== 0)) || (y % 400 === 0));
};

Date.prototype.getDaysInMonth = function () {
    return Date.getDaysInMonth(this.getFullYear(), this.getMonth());
};

Date.prototype.addMonths = function (value) {
    var n = this.getDate();
    this.setDate(1);
    this.setMonth(this.getMonth() + value);
    this.setDate(Math.min(n, this.getDaysInMonth()));
    return this;
};

Date.prototype.toSensusString = function()
{
	var sNewDate = this.toJSON().replace(/[.:T]+/g, "-").replace("Z", "");

	// IE8 workaround
	if(sNewDate.split("-").length < 7){
		sNewDate += "-000";
	}
	return sNewDate;
}