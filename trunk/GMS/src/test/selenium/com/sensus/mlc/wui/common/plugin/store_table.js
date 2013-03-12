/**
 * storeTableContent(searchText/searchCol/contentCol/contentPat/contentPos, storeVar)
 * storeTableContent allows to read the content from one column of a table
 * when a matching found in another column of the same same row. 
 *
 * This is a very useful functionality in a senario like:
 * Assume a table of users with an 'Edit' link infront of each name
 * We want to search through the name list and want to click the corresponding 
 * 'Edit' link in front of the name.
 * 
 * storeGlobalTableContent also can be enabled if you install 'global' 
 * user extension.
 *
 * example of use:
 * +------------------+---------------------------+-----------+
 * |storeTableContent | sumith/0/1/userId=(\w+)/1 | varUserId |
 * +------------------+---------------------------+-----------+
 * 
 * Note: Selenium accepts only two parameters first as input and second as
 *       output variable. But in this function we need to pass 5 parameters
 *       to the function. Simple work-arround I did is simply to separate
 *       parameters by forward-slash ("/"). This works fine for many general
 *       senarios (Thanks to the power of regular expressions). But there will 
 *       issues need to pay speciall attention if you use complex 'searchText'
 *       and complex 'contentPattern's.
 *
 * Five parameters: searchText/searchCol/contentCol/contentPat/contentPos
 *       searchText: Text you are looking in the Table
 *       searchcol: Searching column number (start count from 0)
 *       contentCol: Column number of the content 
 *       contentPat: Pattern used to grab only the required portion of 
 *                   the column content. (.* - catch all the content)
 *       contentPos: The matching group to return (0 - for complete match)
 *
 *
 * storeTableRow(searchText/searchCol/minCol, storeVar)
 * storeTableRow returens (store to a variable) row number in which we can a 
 * given text. (Please note that row count start from 0).
 *
 * storeGlobalTableRow also can be enabled if you install 'global' user extension.
 *
 * example of use:
 * +--------------+-------------------------+-------------+
 * |storeTableRow | contentToBeSearched/0/1 | varTableRow |
 * +--------------+-------------------------+-------------+
 * 
 * Note: Selenium accepts only two parameters first as input and second as
 *       output variable. But in this function we need to pass 3 parameters
 *       to the function (including the optional 3rd parameter). Simple 
 *       work-arround I did is simply to separate parameters by forward-slash 
 *       ("/"). This works fine for many general senarios (Thanks to the power 
 *       of regular expressions). But there will issues need to pay speciall 
 *       attention if you use complex 'searchText' and complex 'contentPattern's.
 *
 * Three parameters: searchText/searchCol/minCol
 *       searchText: Text you are looking in the Table
 *       searchcol: Searching column number (start count from 0)
 *       minCol: Tables having columns less than minCol with ignore
 *
 * @author Sumith Gamage
 */

// Search the row of a table with a given content and minimal number of colomns
function searchTableRow(searchText, searchCol, minCol) {
        
    // Get all table row objects
    rowObj = selenium.browserbot.getCurrentWindow().document.getElementsByTagName("TR"); 
    
    // Add a preceeding backslash to the special charactors.
    // This eleminates the problem of matching "${passVar}" 
    // like values. Currently we have only '$' sign which give 
    // troubles. But later we may have to add more charactors.
    // - Sumith (14th Dec, 2006)
    searchText = searchText.replace(/(\$)/g, "\\$1");
    
    // Loop through table rows
    var foundContent = false;
    for (rowNo=0; rowNo<rowObj.length; rowNo++) {
        // No need to read too short rows
        var maxCol = rowObj[rowNo].cells.length;
        if (maxCol <= searchCol || maxCol <= minCol) {continue;}
        
        // Read the content of the search colomn
        searchHtml = rowObj[rowNo].cells[searchCol].innerHTML;
        
        // Let's ignore <TD> inside <TD>s.
        // Due to some odd reasons 'm' switch does not work in 'match'
        // - Sumith (10th Dec, 2006)
        if (searchHtml.replace(/\n/g, " ").match(/<TD.*>.*<\/TD>/i)) continue;
        
        // Build the pattern to check search column
        var searchPat = new RegExp("^(.*<[^<>]*>)?\\s*"+searchText+"\\s*(<[^<>]*>.*)?$", "m");   
        
        // No more processing for unmatched rows
        if (!searchHtml.match(searchPat)) {continue;} 
        
        // Got the row! Let's simply return it
        return rowNo;
    }
    
    return -1; // No row matched the criteria
}

// Find the row of a table with a given content
function getTableRow(value) {
    
    // Seperating parameters passed in value
    var paraPart = [];
    paraPart = (value.match(/(.*)\/(\d+)\/(\d+)/)) ? value.match(/(.*)\/(\d+)\/(\d+)/) : value.match(/(.*)\/(\d+)/);
    var searchText = paraPart[1];
    var searchCol  = paraPart[2];
    var minCol = (paraPart.length > 2) ? paraPart[3]-1 : 0;
    
    return searchTableRow(searchText, searchCol, minCol);
}

function getTableContent(value) {
    
    // Seperating 5 parameters passed in value
    var paraPart = value.match(/(.*)\/(\d+)\/(\d+)\/(.*)\/(\d+)/);
    var searchText = paraPart[1];
    var searchCol  = paraPart[2];
    var contentCol = paraPart[3];
    var contentPatStr = paraPart[4];
    var contentPos = paraPart[5];
    
    var rowNo = searchTableRow(searchText, searchCol, contentCol);
    
    if (rowNo < 0) {return false;} // No row was matched
    
    // Get all table row objects
    var rowContent = selenium.browserbot.getCurrentWindow().document.getElementsByTagName("TR")[rowNo]; 
    
    // Get the corresponding content html
    contentHtml = rowContent.cells[contentCol].innerHTML; 
    
    // It was noted that when we have links <A ...> ... </A> in 
    // contentHtml, some funny charactors appers in the URL in href 
    // closure. Following two statements are a work arrond for it.
    // - Sumith (30th Nov, 2006)
    
    // This removes %20s & %0As at the begining of the URL. We will
    // need to modify this later, if we see any such charactor at 
    // the middle way of the URL too.
    contentHtml = contentHtml.replace(/(href=\"?)(%20|%0A)*/gi, "$1");
    // replaces "&amp;" with "&"
    contentHtml = contentHtml.replace(/&amp;/gi, "&"); 
    // merge multiple lines and remove spaces
    contentHtml = contentHtml.replace(/^\s+|\s+$|\n/g, " "); 
    
    var contentPat = new RegExp(contentPatStr); // Build the content pattern
    var foundContent = (contentHtml.match(contentPat)) ? contentHtml.match(contentPat)[contentPos] : false; // Retrieving the content
    
    return foundContent;
}

Selenium.prototype.doStoreTableContent = function(value, varName) {
    storedVars[varName] = getTableContent(value);
};

Selenium.prototype.doStoreTableRow = function(value, varName) {
    storedVars[varName] = getTableRow(value) + 1;
};

// Please uncomment following code-lines only if you have installed
// 'global' (http://wiki.openqa.org/display/SEL/global) user extension.
/*
Selenium.prototype.doStoreGlobalTableRow = function(value, varName) {
    globalStoredVars[varName] = getTableRow(value);
};

Selenium.prototype.doStoreGlobalTableContent = function(value, varName) {
    globalStoredVars[varName] = getTableContent(value);
};
*/
