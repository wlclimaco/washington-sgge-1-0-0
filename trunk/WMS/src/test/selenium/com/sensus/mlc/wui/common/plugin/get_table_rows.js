Selenium.prototype.getTableRows = function(locator) {
  /**
   * Gets the number of rows in a table.
   *
   * @param locator element locator for table
   * @return number of rows in the table, 0 if none
   */

    var table = this.browserbot.findElement(locator);
    return table.rows.length.toString();
};
