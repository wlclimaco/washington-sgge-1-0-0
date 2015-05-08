<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.security.question
 * @fileoverview The main namespace for the Security.
 */

pgsi.pages.security.question = {

	form : {

		setFieldSizes : function() {
			$("#security-question-template").find(".security-question").nextAll(".ui-selectmenu-button").outerWidth(300);
		},

		// Layout setup for input fields
	 	fnInitForm : function() {

	 		var $securityQuestionTemplate = $("#security-question-template");
	 		var $container = $securityQuestionTemplate.find(".container");

	 		// Multiple questions functionality
			$("#add-question").on("click", function() {

				// Create new node
				var $copy = $(pgsi.pages.security.question.form.createNewNode());
				$container = $securityQuestionTemplate.find(".container");

				$container.append($copy);
				$container = $securityQuestionTemplate.find(".container");

				var $last = $container.find(".row-form").last();

				pgsi.util.page.form.fnInitForm($last);
				$last.find('.security-question').nextAll(".ui-selectmenu-button").outerWidth(300);
				$last.find('.model-action').val('INSERT');
			});

	 		var $row = $container.find('.row-form');
			$row.find('.model-action').val("INSERT");
		},

	 	fillObject : function() {

			var aQuestions = [];
			var oPersonSecurityAnswer;
			var sQuestionId;
			var sModelAction;

			// Security Question
			$("#security-question-template").find(".row-form").each(function() {

				oPersonSecurityAnswer = new PersonSecurityAnswer({
					modelAction 	 : $(this).find('.model-action').val(),
					id 				 : $(this).find('.answer-id').val(),
					answerText 		 : $(this).find('.security-answer').val(),
					parentKey 		 : $(this).find('.security-question').val(),
					version		     : $(this).find('.version').val(),
					securityQuestion : {
						id : $(this).find('.security-question').val()
					}
				});

				if (oPersonSecurityAnswer.modelAction.length !== 0) {
					aQuestions.push(oPersonSecurityAnswer);
				}

			});

			return aQuestions;
		},
		/*
	 	* Returns a new row for the security question template
	 	*/
	 	createNewNode : function() {
	 		var $rows = $("#security-question-template").find(".container").find(".row-form");
	 		var $last = $rows.last();
	 		var $copy = $rows.first().clone();
	 		var $parent = $last.parent();

			$copy.find('input').val('');
			$copy.find('select').removeAttr("id");
			$copy.find('.model-action').val("INSERT");
			$copy.find(".close-button-form").removeClass("hide");
			$copy.find(".ui-selectmenu-button").remove();
			$copy.find("select, input.security-answer").addClass("required");

			return "<div class='row-form'>" + $copy.html() + "</div>";
 		},

		fillFormFields : function(oPersonSecurityAnswerList) {

			var oPersonSecurityAnswer;
			var $securityQuestionTemplate = $("#security-question-template");
			var $copy;
			var $personSecurityAnswer;
			var iIndex;
			var $container;
			var sQuestionName = "";
			var sAnswerName = "";

			// Iterates over the Person Security Answer list and adds new rows
			for (var i=0; i < oPersonSecurityAnswerList.length; i++) {
				$copy = $(pgsi.pages.security.question.form.createNewNode());

				sQuestionName = $copy.find(".security-question").attr("name");
				sAnswerName = $copy.find(".security-answer").attr("name");
				oPersonSecurityAnswer = oPersonSecurityAnswerList[i];

				if (i > 0) {
					$copy.find(".security-answer").attr("name", sAnswerName + i);
					$copy.find(".security-question").attr("name", sQuestionName + i);
				}

				if (i == 0) {
					$securityQuestionTemplate.find(".row-form").remove();
				}

				$copy.find(".answer-id").val(oPersonSecurityAnswer.id);
				$copy.find(".model-action").val("UPDATE");
				$copy.find(".security-question").val(oPersonSecurityAnswer.securityQuestion.id);
				$copy.find(".security-answer").val(oPersonSecurityAnswer.answerText);
				$copy.find(".version").val(oPersonSecurityAnswer.version);

				$container = $securityQuestionTemplate.find(".container");
				$container.append($copy);
			}

			if (oPersonSecurityAnswerList.length > 0) {
				$container = $securityQuestionTemplate.find(".container");

				$container = $("#security-question-template").find('.container');

				pgsi.util.page.form.fnInitForm($container.find('.row-form'));

				$container = $("#security-question-template").find('.container');

				$container.find(".ui-selectmenu-button").outerWidth(250);

				$container.find('.row-form').find('.icon-remove').on("click", function() {
					var $row = $(this).parents(".row-form");
					$row.addClass("hide");
					$(this).parents(".row-form").find("input, select").removeClass("required");

					if ($(this).parents('.row-form').find('.answer-id').val().length > 0) {
						$row.find(".model-action").val("DELETE");
					}
					else {
						$row.find(".model-action").val("");
					}
				});
			}

		}
	}

}


</script>

</sec:authorize>