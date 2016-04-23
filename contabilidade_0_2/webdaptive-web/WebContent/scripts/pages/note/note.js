var noteModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Note.js'
    };

    //Request Note default structure
    function NOTE(note) {
        try {
            var self = this;
    		self.id = ko.observable( note ? (note.id || 0 ) : 0);
    		self.parentId = ko.observable( note ? (note.parentId || 0 ) : 0);
    		self.noteText = ko.observable( note ? (note.note_text || "" ) : "");
    		self.tabelaEnumValue = ko.observable( note ? (note.tabela || 0 ) : 0);
    		self.createDateUTC = ko.observable( note ? (note.create_date || 0 ) : 0);
    		self.createUser = ko.observable( note ? (note.create_user || 0 ) : 0);
    		self.modifyDateUTC = ko.observable( note ? (note.modify_date || 0 ) : 0);
    		self.modifyUser = ko.observable( note ? (note.modify_user || 0 ) : 0);
        } catch (e) {
        	console.log(e)
        }
    }

    //Default constructor
    return {
        NOTE: NOTE
    }

})();