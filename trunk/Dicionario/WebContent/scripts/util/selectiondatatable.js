/**
 * @fileoverview Provides utilities for selection checkbox on datatable
 * @author Raphael Constantino
 */

/**
 * The main namespace for page-related functionality.
 */
sensus.util.selection = {
	
		/**
		 * Track the table rows the user selected across the pages.
		 */
		selectedRows : [],
		/**
		 * Track the all rows the user deselected across the pages.
		 * This functionality is diff from selectedRows because it keeps the deselected rows
		 */
		deselectedRows : [],
		/**
		 * Track if AllRows selection is enabled  
		 */
		isAllRows : false,
		/**
		 * Track the total amount of records for all page selection
		 */
		allRowsCount : {},	
		
		selectCheckbox : function() {
			//$('.checked_count').text($(".table-selection tbody input:checkbox:checked").length);
		},
		
		/**
		 * Select All checkbox in current page when click on the button
		 * Page
		 * @param e
		 */
		
		selectPageButton : function(e) {
			if(sensus.util.selection.isAllRows) {
				sensus.util.selection.clearSelects(e);
			}
			sensus.util.selection.isAllRows = false;
			e.preventDefault();
			$('.table-selection').find(':checkbox').attr('checked', true);
			//get the checked items from page
			$(".table-selection").find(":checked").each(function(i){
				if( $(this).attr("value").match('^(0|[1-9][0-9]*)$') ){
					sensus.util.selection.selectedRows.push($(this).attr("value"));
				}      
			   //Remove duplicates	
			   sensus.util.selection.selectedRows  = sensus.util.selection.selectedRows.unique();
			   //minus the top header checkbox
			   $('.checked-count').text($().numberFormat(sensus.util.selection.selectedRows.length));
			});		
			$('#actions .message').removeClass("ui-state-error").addClass("ui-state-highlight");		
			$('#actions .filter_select').show();
		},
		
		/**
		 * Select All checkbox in current page when click on the top checkbox
		 */
		
		selectPageCheckbox : function() {
		
			if($('#select-page').is(':checked')) {
			
				$(this).parents('table:eq(0)').find(':checkbox').prop('checked', true);
				$('#select-page').prop('checked', true);
				$('#actions .filter_select').hide();
				$('.table-selection').find(':checkbox').attr('checked', true);
				
				if(sensus.util.selection.isAllRows == true) {
				
					//get the unchecked items from page
					$('.table-selection').find(':checkbox').attr('checked', true).each(function(i){
					
						for (i in sensus.util.selection.deselectedRows) {
						
							if (sensus.util.selection.deselectedRows[i] == $(this).attr('value')) {
							
								sensus.util.selection.deselectedRows.splice(i, 1);
								break;
								
							}
							
						}
					   //Remove duplicates	
					   sensus.util.selection.deselectedRows  = sensus.util.selection.deselectedRows.unique();
					});
					$('.checked-count').text($().numberFormat(sensus.util.selection.allRowsCount - sensus.util.selection.deselectedRows.length));
					
				} else {
				
					//get the unchecked items from page
					$('.table-selection').find(':checkbox').attr('checked', true).each(function(i){
					
						if( $(this).attr("value").match('^(0|[1-9][0-9]*)$') ){
						
							sensus.util.selection.selectedRows.push($(this).attr("value"));
						}
					   //Remove duplicates	
					   sensus.util.selection.selectedRows  = sensus.util.selection.selectedRows.unique();
					});
					$('.checked-count').text($().numberFormat(sensus.util.selection.selectedRows.length));
					
				}
				//minus the top header checkbox
				
			} else {
			
				$(this).parents('table:eq(0)').find(':checkbox').attr('checked', '');
				$('#select-page').attr('checked', '');
				$('#actions .filter_select').hide();
				$('.table-selection').find(':checkbox').attr('checked', false);
			
				if(sensus.util.selection.isAllRows == true) {
				
					//get the unchecked items from page
					$('.table-selection').find(':checkbox').attr('checked', false).each(function(i){
					
						if( $(this).attr("value").match('^(0|[1-9][0-9]*)$') ){
							sensus.util.selection.deselectedRows.push($(this).attr("value"));
							
						}
					   //Remove duplicates	
					   sensus.util.selection.deselectedRows  = sensus.util.selection.deselectedRows.unique();
					   
					});
					
					$('.checked-count').text($().numberFormat(sensus.util.selection.allRowsCount - sensus.util.selection.deselectedRows.length));
					
				} else {
				
					//get the unchecked items from page
					$('.table-selection').find(':checkbox').attr('checked', false).each(function(i){
					
						for (i in sensus.util.selection.selectedRows) {
						
							if (sensus.util.selection.selectedRows[i] == $(this).attr('value')) {
							
								sensus.util.selection.selectedRows.splice(i, 1);
								break;
								
							}
							
						}
						
					});
					
					$('.checked-count').text($().numberFormat(sensus.util.selection.selectedRows.length));
					
				}
				
			}
			
			//minus the top header checkbox
			$('#actions .message').removeClass("ui-state-error").addClass("ui-state-highlight");	
			$('#actions .filter_select').show();
		},
		
		/**
		 * Select All Checkbox of the table
		 * @param e
		 */
		
		selectAll : function(e) {
		
			sensus.util.selection.clearSelects(e);
			e.preventDefault();
			$('.table-selection').find(':checkbox').attr('checked', true);
			
			//Enable allRows selection for server command to be sent
			sensus.util.selection.isAllRows = true;
			
			$(".table-selection").find(":checked").each(function(i){
			
				if( $(this).attr("value").match('^(0|[1-9][0-9]*)$') ){
				
					sensus.util.selection.selectedRows.push($(this).attr("value"));
				}   
				
			   //Remove duplicates	
			   sensus.util.selection.selectedRows  = sensus.util.selection.selectedRows.unique();
			   //minus the top header checkbox
			   $('.checked-count').text($().numberFormat(sensus.util.selection.selectedRows.length));
			   
			});		
			$('.checked-count').text($().numberFormat(sensus.util.selection.allRowsCount));
			$('#actions .message').removeClass("ui-state-error").addClass("ui-state-highlight");
			$('#actions .filter_select').show();
			
		},
		
		/**
		 * Clear All Checkbox of the table 
		 * @param e
		 */
		
		clearSelects : function(e) {
		
			if (e){
			
				e.preventDefault();
				
			}	
			
			$('.table-selection').find(':checkbox').attr('checked', false);
			
			//Enable allRows selection for server command to be sent
			sensus.util.selection.isAllRows = false;
			
			$('.checked-count').text('0');
			sensus.util.selection.selectedRows = [];
			sensus.util.selection.deselectedRows = [];
			$('#actions .message').removeClass("ui-state-error").removeClass("ui-state-highlight");	
			$('#actions .filter_select').hide();
		}
		
};