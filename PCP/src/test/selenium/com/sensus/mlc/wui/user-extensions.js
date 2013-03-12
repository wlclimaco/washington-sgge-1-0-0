// ************************************************* //
/* ## BEGIN Goto plugin functions ## */

/*
 (C) Copyright MetaCommunications, Inc. 2006.
     http://www.meta-comm.com
     http://engineering.meta-comm.com

Distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND.
*/

function map_list( list, for_func, if_func )
   {
    var mapped_list = [];
    for ( var i = 0; i < list.length; ++i )
        {
        var x = list[i];
        if( null == if_func || if_func( i, x ) ) 
            mapped_list.push( for_func( i, x ) );
        }
    return mapped_list;
    }

    
// Modified to initialize GoTo labels/cycles list
HtmlRunnerTestLoop.prototype.old_initialize = HtmlRunnerTestLoop.prototype.initialize;

HtmlRunnerTestLoop.prototype.initialize = function(htmlTestCase, metrics, seleniumCommandFactory)
    {
    this.gotoLabels  = {};
    this.whileLabels = { ends: {}, whiles: {} };
    
    this.old_initialize(htmlTestCase, metrics, seleniumCommandFactory);
    
    this.initialiseLabels();
    }

HtmlRunnerTestLoop.prototype.initialiseLabels = function()
    {
	//alert("A");
    var command_rows = map_list( this.htmlTestCase.getCommandRows() 
                               , function(i, x) { 
                                    return x.getCommand()
                                    }
                               );

    var cycles = [];
    for( var i = 0; i < command_rows.length; ++i )
        {
        switch( command_rows[i].command.toLowerCase() )
            {
            case "label":
                this.gotoLabels[ command_rows[i].target ] = i;
                break;
            case "while":
            case "endwhile":
                cycles.push( [command_rows[i].command.toLowerCase(), i] )
                break;
            }
        }        
        
    var i = 0;
    while( cycles.length )
        {
        if( i >= cycles.length )
            throw new Error( "non-matching while/endWhile found" );
            
        switch( cycles[i][0] )
            {
            case "while":
                if(    ( i+1 < cycles.length ) 
                    && ( "endwhile" == cycles[i+1][0] )
                    )
                    {
                    // pair found
                    this.whileLabels.ends[ cycles[i+1][1] ] = cycles[i][1]
                    this.whileLabels.whiles[ cycles[i][1] ] = cycles[i+1][1]
                    
                    cycles.splice( i, 2 );
                    i = 0;
                    }
                else
                    ++i;
                break;
            case "endwhile":
                ++i;
                break;
            }
        }
                    
    }    

HtmlRunnerTestLoop.prototype.continueFromRow = function( row_num ) 
    {
	//alert("E");
	//this.initialiseLabels();
    if(    row_num == undefined
        || row_num == null
        || row_num < 0
        )
        throw new Error( "Invalid row_num specified." );
        
    this.htmlTestCase.nextCommandRowIndex = row_num;
    }
    
// do nothing. simple label
Selenium.prototype.doLabel      = function(){};

Selenium.prototype.doGotolabel  = function( label ) {

    if( undefined == htmlTestRunner.currentTest.gotoLabels[label] ) 
        throw new Error( "Specified label '" + label + "' is not found." );
    
    htmlTestRunner.currentTest.continueFromRow( htmlTestRunner.currentTest.gotoLabels[ label ] );
    };
    
Selenium.prototype.doGoto = Selenium.prototype.doGotolabel;

Selenium.prototype.doGotoIf = function( condition, label ) {
    if( eval(condition) ) 
        this.doGotolabel( label );
    }
  
Selenium.prototype.doWhile = function( condition ) {
    if( !eval(condition) )
    {
        var last_row = htmlTestRunner.currentTest.htmlTestCase.nextCommandRowIndex - 1;
        var end_while_row = htmlTestRunner.currentTest.whileLabels.whiles[ last_row ];
        if( undefined == end_while_row ) {
            throw new Error( "Corresponding 'endWhile' is not found." );
        }
        htmlTestRunner.currentTest.continueFromRow( end_while_row + 1 );
    }
}

Selenium.prototype.doEndWhile = function() {
	//htmlTestRunner.currentTest.initialiseLabels();
    var last_row = htmlTestRunner.currentTest.htmlTestCase.nextCommandRowIndex - 1;
    var while_row = htmlTestRunner.currentTest.whileLabels.ends[ last_row ];
    if( undefined == while_row ) {
        throw new Error( "Corresponding 'While' is not found." );
    }
    htmlTestRunner.currentTest.continueFromRow( while_row );
}

/* ## END Goto plugin functions ## */
// ************************************************* //

// $Id: includeCommand.js 211 2007-08-10 11:16:25Z rob $
/*extern document, window, XMLHttpRequest, ActiveXObject */
/*extern Selenium, htmlTestRunner, LOG, HtmlTestCaseRow, testFrame, storedVars, URLConfiguration */

/**
 * add the content of another test to the current test
 * target receives the page address (from selenium tests root)
 * text receives vars names and their values for this test
 * as a comma separated list of var_name=value
 *
 * nested include works
 *
 * Take a look at the supplied includeCommand TestSuite.html
 * for more examples
 *
 * example of use
 * in the test :
 * +------------------+----------------------+----------------------+
 * |include           | testpiece.html       | name=joe,userId=3445 |
 * +------------------+----------------------+----------------------+
 * where
 * testpiece.html contains
 * +---------------------------------------------+
 * | this is a piece of test                     |
 * +------------------+-----------------------+--+
 * |open              | myurl?userId=${userId}|  |
 * +------------------+-----------------------+--+
 * |verifyTextPresent | ${name}               |  |
 * +------------------+-----------------------+--+
 * as selenium reach the include commande, it will load 
 * seleniumRoot/tests/testpiece.html into you current test, replacing ${name} with joe and ${userId} with 3445
 * and your test wil become
 * +------------------+----------------------+----------------------+
 * |includeExpanded   | testpiece.html       | name=joe,userId=3445 |
 * +------------------+----------------------+----------------------+
 * |open              | myurl?userId=3445    |                      |
 * +------------------+----------------------+----------------------+
 * |verifyTextPresent | joe                  |                      |
 * +------------------+----------------------+----------------------+
 * moreover if you click on the line with "includeExpanded", it will show/hide included lines !
 *
 * Note on URL to get include document:
 *  relative url's (like testpiece.html in the example above) are relative to the TestSuite
 *
 * @author Alexandre Garel
 * @author Robert Zimmermann
 *
 * Note from Robert Zimmermann:
 *  One thing to the variable handling (that's "name=joe,userId=3445" in the example above)
 *  I recommend to use selenium built-in variables instead of parameters in includeCommand.
 *  Why?: There are escaping issues which selenium built-in variables handle better
 *  Though includeCommand variable-like substitution should work and I didn't remove it for backward compatibility
 *  
 *  Version: 2.3
 */

// The real include selenium-command is placed at the end of this file as
//  jslint complains about undefined functions otherwise
Selenium.prototype.doIncludeCollapsed = function(locator, paramString) {
    // do nothing, as rows are already included
};

Selenium.prototype.doIncludeExpanded = function(locator, paramString) {
    // do nothing, as rows are already included
};
 

function IncludeCommand() {
    // TODO targetRow is needed for fold/unfold, isn't there a better way without this member?
    this.targetRow = null;
}

IncludeCommand.EXPANDED_COMMAND_NAME = "includeExpanded";
IncludeCommand.COLLAPSED_COMMAND_NAME = "includeCollapsed";
IncludeCommand.LOG_PREFIX = "IncludeCommand: ";
IncludeCommand.VERSION = "2.3";

// use a closure here to keep each row actions by them self
// TODO think about example: http://www.jibbering.com/faq/faq_notes/closures.html#clObjI
IncludeCommand.prototype.onClickFactory = function(inclCmdRow, lastInclCmdRow) {
    return (function(e) {
        // change the trailing "Expanded" "Collapsed" of include
        // and choose the display style
        var cmdCol = (inclCmdRow.getElementsByTagName("td"))[0].firstChild;
        var displayMode;
        if ( cmdCol.nodeValue == IncludeCommand.EXPANDED_COMMAND_NAME ) {
            cmdCol.nodeValue = IncludeCommand.COLLAPSED_COMMAND_NAME;
            displayMode = "none";
        } else {
            cmdCol.nodeValue = IncludeCommand.EXPANDED_COMMAND_NAME;
            displayMode = inclCmdRow.style.display;
        }
        
        var ptrRow = inclCmdRow.nextSibling;
        while (ptrRow != lastInclCmdRow.nextSibling) {
            // when I unfold i shall unfold all nested include (no way to know which row they concern
            if (displayMode != "none") {
                cmdCol = (ptrRow.getElementsByTagName("td"))[0].firstChild;
                if (cmdCol.nodeValue == IncludeCommand.COLLAPSED_COMMAND_NAME) {
                    cmdCol.nodeValue = IncludeCommand.EXPANDED_COMMAND_NAME;
                }
            }
            // set display mode for rows
            if (ptrRow.style) {
                ptrRow.style.display = displayMode;
            }
            ptrRow = ptrRow.nextSibling;
        }
    });
};

IncludeCommand.prototype.postProcessIncludeCommandRow = function(includeCmdRow) {
    /**
     * Alter the original include command row to add fold, unfold magic
     *
     * @param includeCmdRow TR DOM-element, the source of the current execution
     */
    // TODO names should be class-constants
    var foldUnfoldToolTipp = "click to fold/unfold included rows";
    var lastInclRow = this.targetRow;
    // command name is changed from 'include' to 'include<TAIL>' to avoid another inclusion during a second pass
    (includeCmdRow.getElementsByTagName("td"))[0].firstChild.nodeValue = IncludeCommand.EXPANDED_COMMAND_NAME;
    includeCmdRow.title = foldUnfoldToolTipp;
    includeCmdRow.alt = foldUnfoldToolTipp;
    
    // adding the fold/unfold trick
    includeCmdRow.onclick = this.onClickFactory(includeCmdRow, lastInclRow);
};

IncludeCommand.extendSeleniumExecutionStack = function(newRows) {
    /**
     * Put the new commands into the current position of the selenium execution stack
     *
     * @param newRows Array of HtmlTestCaseRows to be inserted in seleniums' execution stack
     */
    try {
        //(rz WEB.DE) changed to work with selenium 0.8.0
        // Leave previously run commands as they are
        var seleniumCmdRowsPrev = htmlTestRunner.currentTest.htmlTestCase.commandRows.slice(0, htmlTestRunner.currentTest.htmlTestCase.nextCommandRowIndex);
        var seleniumCmdRowsNext = htmlTestRunner.currentTest.htmlTestCase.commandRows.slice(htmlTestRunner.currentTest.htmlTestCase.nextCommandRowIndex);
        var newCommandRows = seleniumCmdRowsPrev.concat(newRows);
        htmlTestRunner.currentTest.htmlTestCase.commandRows = newCommandRows.concat(seleniumCmdRowsNext);
    } catch(e) {
        LOG.error(IncludeCommand.LOG_PREFIX + "Error adding included commandRows. exception=" + e);
        throw new Error("Error adding included commandRows. exception=" + e);
    }
};

IncludeCommand.prototype.injectIncludeTestrows = function(includeCmdRow, testDocument, testRows) {
    /**
     * Insert new (included) commad rows into current testcase (inject them)
     * This is the part visible in the middle frame of testrunner 
     * Selenium inner execution stack is still to be extended which is done later
     *
     * @param includeCmdRow TR Element of the include commad row wich called this include extension (from here the included rows have to be inserted)
     * @param testDocument DOM-document of the current testcase (needed to copy included command rows)
     * @param testRows prepared testrows to be included
     * @return newRows Array of HtmlTestCaseRow objects ready to be used by selenium
     */
    this.targetRow = includeCmdRow;
    var newRows = new Array();
    
    // TODO: use selenium methods to get to the inner test-rows (tr-elements) of an testcase.
    //       here it is the testcase to be included
    // skip first element as it is empty or <tbody>
    for (var i = 1 ; i < testRows.length; i++) {
        var newRow = testDocument.createElement("tr");
        var newText = testRows[i];
        // inserting
        this.targetRow = this.targetRow.parentNode.insertBefore(newRow, this.targetRow.nextSibling);
        // innerHTML permits us not to interpret the rest of html code
        // note: innerHTML is to be filled after insertion of the element in the document
        // note2 : does not work with internet explorer
        try {
            this.targetRow.innerHTML = newText;
        } catch (e) {
            // doing it the hard way for ie
            // parsing column, doing column per column insertion
            // remove < td>
            newText = newText.replace(/<\s*td[^>]*>/ig,"");
            //Lance: remove </tbody>
            newText = newText.replace(/<\/*tbody*>|<br>/ig,"");
            // split on < td>
            var testCols = newText.split(/<\/\s*td[^>]*>/i);
            for (var j = 0 ; j < testCols.length; j++) {
                var newCol = testDocument.createElement("td");
                var colText = testCols[j];
                newCol = this.targetRow.appendChild(newCol);
                newCol.innerHTML = colText;
            }
        }
        // TODO try to use original HtmlTestCase class instead copying parts of it
        if (newRow.cells.length >= 3) {
            var seleniumRow = new HtmlTestCaseRow(newRow);
            seleniumRow.addBreakpointSupport();
            newRows.push(seleniumRow);
        }
    }
    return newRows;
};

IncludeCommand.getCurrentTestDocument = function() {
    /**
     * Get the current test-case document from selenium
     *
     * @return testDocument the document object of the testcase-frame
     */
    var testDocument;
    try {
        testDocument = testFrame.getDocument();
    }
    catch(e) {
        throw new Error("testDocument not avalaible. Selenium API changed?");
    }
    return testDocument;
};

IncludeCommand.prepareTestCaseAsText = function(responseAsText, paramsArray) {
    /**
     * Prepare the HTML to be included in as text into the current testcase-HTML
     * Strip all but the testrows (tr)
     * Stripped will be:
     *  - whitespace (also new lines and tabs, so be careful wirt parameters relying on this),
     *  - comments (xml comments)                 
     * Replace variable according to include-parameters 
     * note: the include-variables are replaced literally. selenium does it at execution time
     * also note: all selenium-variables are available to the included commands, so mostly no include-parameters are necessary
     *
     * @param responseAsText table to be included as text (string)
     * @return testRows array of tr elements (as string!) containing the commands to be included
     * 
     * TODO:
     *  - selenium already can handle testcase-html. use selenium methods or functions instead
     *  - find better name for requester
     */
    // removing new lines, carret return and tabs from response in order to work with regexp
    var pageText = responseAsText.replace(/\r|\n|\t/g,"");
    // remove comments
    // begin comment, not a dash or if it's a dash it may not be followed by -> repeated, end comment
    pageText = pageText.replace(/<!--(?:[^-]|-(?!->))*-->/g,"");
    // find the content of the test table = <[spaces]table[char but not >]>....< /[spaces]table[chars but not >]>
    var testText = pageText.match(/<\s*table[^>]*>(.*)<\/\s*table[^>]*>/i)[1];

    // Replace <td></td> with <td>&nbsp;</td> for iE - credits Chris Astall
    // rz: somehow in my IE 7 this is not needed but is not bad as well
    testText = testText.replace(/<\s*td[^>]*>\s*<\s*\/td[^>]*>/ig,"<td>&nbsp;</td>");

    // replace vars with their values in testText
    for ( var k = 0 ; k < paramsArray.length ; k++ ) {
        var pair = paramsArray[k];
        testText = testText.replace(pair[0],pair[1]);
    }

    // removes all  < /tr> 
    // in order to split on < tr>
    testText = testText.replace(/<\/\s*tr[^>]*>/ig,"");
    // split on <tr>
    var testRows = testText.split(/<\s*tr[^>]*>/i);
    return testRows;
};

IncludeCommand.getIncludeDocumentBySynchronRequest = function(includeUrl) {
    /**
     * Prepare and do the XMLHttp Request synchronous as selenium should not continue execution meanwhile
     *
     * note: the XMLHttp requester is returned (instead of e.g. its text) to let the caller decide to use xml or text
     *
     * selenium-dependency: uses extended String from htmlutils
     *
     *  TODO use Ajax from prototype like this:
     *   var sjaxRequest = new Ajax.Request(url, {asynchronous:false});
     *   there is discussion about getting rid of prototype.js in developer forum.
     *   the ajax impl in xmlutils.js is not active by default in 0.8.2
     *
     * @param includeUrl URI to the include-document (document has to be from the same domain)
     * @return XMLHttp requester after receiving the response
     */
    var url = IncludeCommand.prepareUrl(includeUrl);
    // the xml http requester to fetch the page to include
    var requester = IncludeCommand.newXMLHttpRequest();
    if (!requester) {
        throw new Error("XMLHttp requester object not initialized");
    }
    requester.open("GET", url, false); // synchron mode ! (we don't want selenium to go ahead)
    try {
        requester.send(null);
    } catch(e) {
      throw new Error("Error while fetching url '" + url + "' details: " + e);
    }
    if ( requester.status != 200 && requester.status !== 0 ) {
        throw new Error("Error while fetching " + url + " server response has status = " + requester.status + ", " + requester.statusText );
    }
    return requester;
};

IncludeCommand.prepareUrl = function(includeUrl) {
    /** Construct absolute URL to get include document
     * using selenium-core handling of urls (see absolutify in htmlutils.js)
     */
    var prepareUrl;
    // htmlSuite mode of SRC? TODO is there a better way to decide whether in SRC mode?
    if (window.location.href.indexOf("selenium-server") >= 0) {
        LOG.debug(IncludeCommand.LOG_PREFIX + "we seem to run in SRC, do we?");
        preparedUrl = absolutify(includeUrl, htmlTestRunner.controlPanel.getTestSuiteName());
    } else {
        preparedUrl = absolutify(includeUrl, selenium.browserbot.baseUrl);
    }
    LOG.debug(IncludeCommand.LOG_PREFIX + "using url to get include '" + preparedUrl + "'");
    return preparedUrl;
};

IncludeCommand.newXMLHttpRequest = function() {
    // TODO should be replaced by impl. in prototype.js or xmlextras.js
    //     but: there is discussion of getting rid of prototype.js
    //     and: currently xmlextras.js is not activated in testrunner of 0.8.2 release
    var requester = 0;
    var exception = '';
    // see http://developer.apple.com/internet/webcontent/xmlhttpreq.html
    // changed order of native and activeX to get it working with native
    //  xmlhttp in IE 7. credits dhwang
    try {
        // for IE/ActiveX
        if(window.ActiveXObject) {
            try {
                requester = new ActiveXObject("Msxml2.XMLHTTP");
            }
            catch(e) {
                requester = new ActiveXObject("Microsoft.XMLHTTP");
            }
        }
        // Native XMLHttp
        else if(window.XMLHttpRequest) {
            requester = new XMLHttpRequest();
        }
    }
    catch(e) {
        throw new Error("Your browser has to support XMLHttpRequest in order to use include \n" + e);
    }
    return requester;
};

IncludeCommand.splitParamStrIntoVariables = function(paramString) {
    /**
     * Split include Parameters-String into an 2-dim array containing Variable-Name and -Value
     *
     * selenium-dependency: uses extended String from htmlutils
     *
     * TODO: write jsunit tests - this could be easy (if there were not the new RegExp)
     *
     * @param includeParameters string the parameters from include call
     * @return new 2-dim Array containing regExpName (to find a matching variablename) and value to be substituted for
     */
    var newParamsArray = new Array();
    // paramString shall contains a list of var_name=value
    var paramListPattern = /([^=,]+=[^=,]*,)*([^=,]+=[^=,]*)/;
    if (! paramString || paramString === "") {
        return newParamsArray;
    } else if (paramString.match( paramListPattern )) {
        // parse parameters to fill newParamsArray
        var pairs = paramString.split(",");
        for ( var i = 0 ; i < pairs.length ; i++ ) {
            var pair = pairs[i];
            var nameValue = pair.split("=");
            //rz: use String.trim from htmlutils.js of selenium to get rid of whitespace in variable-name(s)
            var trimmedNameValue = new String(nameValue[0]).trim();
            // the pattern to substitute is ${var_name}
            var regExpName = new RegExp("\\$\\{" + trimmedNameValue + "\\}", "g");
            
            if (nameValue.length < 3) {
               newParamsArray.push(new Array(regExpName,nameValue[1]));
            } else {
                var varValue = new String(nameValue[1]);
                for (var j = 2; j < nameValue.length; j++) {
                    varValue=varValue.concat("="+nameValue[j]);
                }
                newParamsArray.push(new Array(regExpName,varValue));
            }
        }
    } else {
        throw new Error("Bad format for parameters list : '" + paramString + "'");
    }
    return newParamsArray;
};

IncludeCommand.prototype.doInclude = function(locator, paramString) {
    // TODO check if reordering of these calls can help to "fail fast/early"

    // ask selenium for the current row (<tr> Element of the include command)
    // credits: dhwang
    var currentSelHtmlTestcase = testFrame.getCurrentTestCase();
    var includeCmdRow = currentSelHtmlTestcase.commandRows[currentSelHtmlTestcase.nextCommandRowIndex - 1].trElement;

    if (!includeCmdRow) {
        throw new Error("includeCommand: failed to find include-row in source testtable");
    }

    var paramsArray = IncludeCommand.splitParamStrIntoVariables(paramString);

    // rz: TODO add use of selenium timeout
    var inclDoc = IncludeCommand.getIncludeDocumentBySynchronRequest(locator);

    var includedTestCaseHtml = IncludeCommand.prepareTestCaseAsText(inclDoc.responseText, paramsArray);

    var testDocument = IncludeCommand.getCurrentTestDocument();

    // only member method because targetRow member is set
    var newRows = this.injectIncludeTestrows(includeCmdRow, testDocument, includedTestCaseHtml);

    IncludeCommand.extendSeleniumExecutionStack(newRows);

    // only member method because targetRow member is accessed
    this.postProcessIncludeCommandRow(includeCmdRow);
};


Selenium.prototype.doInclude = function(locator, paramString) {
    LOG.debug(IncludeCommand.LOG_PREFIX + " Version " + IncludeCommand.VERSION);
    var includeCommand = new IncludeCommand();
    includeCommand.doInclude(locator, paramString);
	
	try {
		//alert("B");
		//HtmlRunnerTestLoop.initialiseLabels();
		htmlTestRunner.currentTest.initialiseLabels();
        //this.initialiseLabels();
    } 
    catch (e) {
    	LOG.debug("Goto Script not used.");
    }
};

// ************************************************* //

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
};

// Find the row of a table with a given content
function getTableRow(value) {
    
    // Seperating parameters passed in value
    var paraPart = [];
    paraPart = (value.match(/(.*)\/(\d+)\/(\d+)/)) ? value.match(/(.*)\/(\d+)\/(\d+)/) : value.match(/(.*)\/(\d+)/);
    var searchText = paraPart[1];
    var searchCol  = paraPart[2];
    var minCol = (paraPart.length > 2) ? paraPart[3]-1 : 0;
    
    return searchTableRow(searchText, searchCol, minCol);
};

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
};

Selenium.prototype.doStoreTableContent = function(value, varName) {
    storedVars[varName] = getTableContent(value);
};

Selenium.prototype.doStoreTableRow = function(value, varName) {
    storedVars[varName] = getTableRow(value) + 1;
};

// ************************************************* //
 
globalStoredVars = new Object();

/*
 * Globally store the value of a form input in a variable
 */
Selenium.prototype.doStoreValueGlobal = function(target, varName) {
    if (!varName) {
        // Backward compatibility mode: read the ENTIRE text of the page
        // and stores it in a variable with the name of the target
        value = this.page().bodyText();
        globalStoredVars[target] = value;
        return;
    }
    var element = this.page().findElement(target);
    globalStoredVars[varName] = getInputValue(element);
};

/*
 * Globally store the text of an element in a variable
 */
Selenium.prototype.doStoreTextGlobal = function(target, varName) {
    var element = this.page().findElement(target);
    globalStoredVars[varName] = getText(element);
};

/*
 * Globally store the value of an element attribute in a variable
 */
Selenium.prototype.doStoreAttributeGlobal = function(target, varName) {
    globalStoredVars[varName] = this.page().findAttribute(target);
};

/*
 * Globally store the result of a literal value
 */
Selenium.prototype.doStoreGlobal = function(value, varName) {
    globalStoredVars[varName] = value;
};

/*
 * Search through str and replace all variable references ${varName} with their
 * value in storedVars (or globalStoredVars).
 */
Selenium.prototype.replaceVariables = function(str) {
    var stringResult = str;

    // Find all of the matching variable references
    var match = stringResult.match(/\$\{\w+\}/g);
    if (!match) {
        return stringResult;
    }

    // For each match, lookup the variable value, and replace if found
    for (var i = 0; match && i < match.length; i++) {
        var variable = match[i]; // The replacement variable, with ${}
        var name = variable.substring(2, variable.length - 1); // The replacement variable without ${}
        var replacement = storedVars[name];
        if (replacement != undefined) {
            stringResult = stringResult.replace(variable, replacement);
        }
        var replacement = globalStoredVars[name];
        if (replacement != undefined) {
            stringResult = stringResult.replace(variable, replacement);
        }
    }
    return stringResult;
};

// ************************************************* //

/** Copyright (c) 2007, Andrew Lambourne
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*     * Redistributions of source code must retain the above copyright
*       notice, this list of conditions and the following disclaimer.
*     * Redistributions in binary form must reproduce the above copyright
*       notice, this list of conditions and the following disclaimer in the
*       documentation and/or other materials provided with the distribution.
*     * Neither the name of the <organization> nor the
*       names of its contributors may be used to endorse or promote products
*       derived from this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY <copyright holder> ``AS IS'' AND ANY
* EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL <copyright holder> BE LIABLE FOR ANY
* DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/

Selenium.prototype.assertEquals = function(elementOne, elementTwo) 
{
    if(elementOne != elementTwo) 
    {
        Assert.fail("" + elementOne + " is not equal to " + elementTwo);
    }
}

Selenium.prototype.assertNotEquals = function(elementOne, elementTwo) 
{
    if(elementOne == elementTwo) 
    {
        Assert.fail("" + elementOne + " is not equal to " + elementTwo);
    }
}

// ************************************************* //

/*
* Set of mthods to handle Dates in selenium.
*
* @author Nikhil Wanpal
*/
var mySetDateFormat = "mm/dd/yyyy";

/**
* Gets the system date and sets in the provided variable. If date 
* format is provided, the date will be converted to given date format and
* set in the variable. Default date format is mm/dd/yyyy where
* d= Date, m= Month and y = Year.
*
* @param varName, name of the variable date to be set to.
* @param dtFormat, optional, format for the date.
*/
Selenium.prototype.doGetSysDate = function(varName,dtFormat) {
	try {
	
		//Get the system date
		var currDate = new Date();
		
		// Get the provided date format, if not provided
		// set the default format
		if (!dtFormat){
			currDate = Selenium.prototype.doFormatDate(currDate,mySetDateFormat);
		} else {
			currDate = Selenium.prototype.doFormatDate(currDate,dtFormat);
		}
		
		//Set the date to the provided variable
		storedVars[varName] = currDate;
		
	} catch(e){
		throw new SeleniumError("Exception occured in getSysDate: "+e.message);
	}
}

/**
* Does reformatting of the given date. Gets the value of the passed
* variable, parses to date, converts to the provided format and sets
* back in the variable. Default date format is mm/dd/yyyy where
* d= Date, m= Month and y = Year.
* Limitation: The passed date variable should have date in 
* format as: mm/dd/yyyy.
*
* @param varName, name of the variable having the date.
* @param dtFormat, optional, format for the date.
*/
Selenium.prototype.doReFormatDate = function(varDate, format) {
	try {
		// Get the value of the passed date variable
		var varDateVal = Selenium.prototype.replaceVariables("\${"+varDate+"}");
		
		// Parse the passed date string to date object.
		var parsedDate = new Date(varDateVal);
		if (parsedDate.toString() == "Invalid Date"){
			throw new SeleniumError("Failed to parse string as date. Invalid dateformat.");
		}
		
		// If format not provided, set the default dateformat.
		if (!format){
			parsedDate = Selenium.prototype.doFormatDate(parsedDate,mySetDateFormat);
		} else {
			parsedDate = Selenium.prototype.doFormatDate(parsedDate,format);
		}
		
		//Set the date to the provided variable
		storedVars[varDate] = parsedDate;
		
	} catch(e) {
		throw new SeleniumError("Exception occured in reFormatDate: "+e.message);
	}
}

/**
* Does Formatting of given date, the passed variable must be an instance of
* JavaScript date object.
* This method is not expected to be called directly in a test case.
*
* @param varDate, instance of Date object with date to be changed.
* @param dateFormat, format date to be changed to.
*/
Selenium.prototype.doFormatDate = function(varDate, dateFormat) {
	// check if the passed param is a valid date
	if (varDate instanceof Date){
				
		// Get year values.
		var fullYr = varDate.getFullYear();
		var year = fullYr.toString().substring(2);
		
		// Set all month strings
		var monthArray = new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
		// Get month values
		var month = varDate.getMonth()+1;
		var twoDigitMonth = month < 10 ? "0"+month : month;
		var strMonth = monthArray[month-1];

		// Get date values
		var dt = varDate.getDate();
		var twoDigitdt = dt < 10 ? "0"+dt : dt;
		
		// Convert date to given format
		dateFormat = dateFormat.replace(/yyyy/i, fullYr);
		dateFormat = dateFormat.replace(/yy/i, year);
		dateFormat = dateFormat.replace(/mmm/i, strMonth);
		dateFormat = dateFormat.replace(/mm/i, twoDigitMonth);
		dateFormat = dateFormat.replace(/m/i, month);
		dateFormat = dateFormat.replace(/dd/i, twoDigitdt);
		dateFormat = dateFormat.replace(/d/i, dt);
		
	} else {
		throw new SeleniumError("Passed parameter is not a valid date. To change format"+
		" of date stored in a variable, use reFormatDate method.");
	}
	
	return dateFormat;
	
}

/**
* Increments / decrements , the date value of the passed variable by the provided
* increment value. Increment value is always 'number of Days'.
* returned value is always the default dateformat which is mm/dd/yyyy where
* d= Date, m= Month and y = Year.
* Limitation: The passed date variable should have date in 
* format as: mm/dd/yyyy. This is critical, as formats like dd/mm/yyyy can cause
* unpredictable date values to be returned.
*
* @param varDate, variable with date to be incremented.
* @param optional, incValue, Number of days the date to be incremented/ decremented by
*/
Selenium.prototype.doIncrementDate = function(varDate, incValue) {
	try {
		// Get the string value of date
		var varDateVal = Selenium.prototype.replaceVariables("\${"+varDate+"}");
		
		// Convert the string to Date object
		var parsedDate = new Date(varDateVal);
		if (parsedDate.toString() == "Invalid Date"){
			throw new SeleniumError("Failed to parse string as date. Invalid dateformat.");
		}
		
		var milliSecs = 0;
		
		// Check if increment value entred
		if (!incValue){
			milliSecs = 86400000;
		} else {
		
			// Parse the increment value provided
			var newIncVal = parseInt(incValue);
			
			//Check if output is NaN, if so throw error.
			if(isNaN(newIncVal)){
				throw new SeleniumError("Provided increment value is not a number.");
			}
			
			// Get the number of milliseconds for the days
			milliSecs = newIncVal*86400000;
		}
		
		// Calculate the new date
		var newDate = new Date(parsedDate.getTime()+milliSecs);
		
		// Set the default date format
		newDate = Selenium.prototype.doFormatDate(newDate,mySetDateFormat);
		
		//Set the date to the provided variable
		storedVars[varDate] = newDate;
		
	} catch(e) {
		throw new SeleniumError("Exception occured in incrementDate: "+e.message);
	}
}

/**
* Gets the system date, Increments / decrements it by the provided
* increment value. Increment value is always 'number of Days'.
* 
* This method is added specifically to overcome the limitations of
* incrementDate and reFormatDate methods.
*
* @param varDate, variable to set date value to.
* @param incValue, Number of days the date to be incremented/decremented by
*/
Selenium.prototype.doGetIncrementedSysDate = function(varDate, incValue) {
	try {
		// Get the system date in default format
		Selenium.prototype.doGetSysDate(varDate,null);
		
		// Increment it by passed increment value.
		Selenium.prototype.doIncrementDate(varDate, incValue);
	
	} catch(e) {
		throw new SeleniumError("Exception occured in incrementDate: "+e.message);
	}
}

// ************************************************* //

/**
* Handle Time in selenium 
*
* @author QAT-Brazil
*
* @param varName, name of the variable date to be set to.
* @param timeFormat, optional, format for the time.
*/
Selenium.prototype.doGetSysTime = function(varName) {
	try {
	
		//Get the system time
		var currTime = Selenium.prototype.doFormatTime(new Date());
		
		//Set the date to the provided variable
		if (globalStoredVars) {
			globalStoredVars[varName] = currTime;
		} else {
			storedVars[varName] = currTime;
		}
		
	} catch(e) {
		throw new SeleniumError("Exception occured in getSysTime: " + e.message);
	}
}

/**
*
* @param varDate, instance of Date object with date to be changed.
* @param timeFormat, format time to be changed to.
*/
Selenium.prototype.doFormatTime = function(varDate) {
	// check if the passed param is a valid date
	if (varDate instanceof Date) {
	
		var timeFormat = varDate.toLocaleTimeString();
		timeFormat = timeFormat.replace(/(\d{1,2}:\d\d):\d\d( .M)/, "\$1\$2");
		
	} else {
		throw new SeleniumError("Passed parameter is not a valid date.");
	}
	
	return timeFormat;
}

// ************************************************* //

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

// ************************************************* //

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