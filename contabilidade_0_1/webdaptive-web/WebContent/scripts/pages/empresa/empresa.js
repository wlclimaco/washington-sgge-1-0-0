var itemModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Item.js'
    };

    //Request Item default structure
    function ITEM(item) {
        try {
            var self = this;

            self.RP_HASHING_KEY = ko.observable( item ? (item.RP_HASHING_KEY || "") : "");
            self.RP_NO = ko.observable( item ? (item.RP_NO || "") : "");
            self.RP_ITEM_NO = ko.observable( item ? (item.RP_ITEM_NO || "") : "");
            self.RP_SUB_ITEM_NO = ko.observable( item ? (item.RP_SUB_ITEM_NO || "") : "");
            self.PO_NO = ko.observable( item ? (item.PO_NO || "") : "");
            self.PO_ITEM_NO = ko.observable( item ? (item.PO_ITEM_NO || "") : "");
            self.PO_SUB_ITEM_NO = ko.observable( item ? (item.PO_SUB_ITEM_NO || "") : "");
            self.FK_QTH_QT_NO = ko.observable( item ? (item.FK_QTH_QT_NO || "") : "");
            self.STATUS_CODE = ko.observable( item ? (item.STATUS_CODE || "") : "");
            self.STATUS_REASON_CODE = ko.observable( item ? (item.STATUS_REASON_CODE || "") : "");
            self.POCR_STATUS_CODE = ko.observable( item ? (item.POCR_STATUS_CODE || "") : "");
            self.LIFE_CYCLE_INDEX = ko.observable( item ? (item.LIFE_CYCLE_INDEX || "") : "");
            self.LOCATION_CODE = ko.observable( item ? (item.LOCATION_CODE || "") : "");
            self.LOCATION_DATE = ko.observable( item ? (mlisUtil.convertServerSideDate(item.LOCATION_DATE) || "") : "");
            self.EXPEDITE_CODE = ko.observable( item ? (item.EXPEDITE_CODE || "") : "");
            self.REQUEST_DOC_TYPE = ko.observable( item ? (item.REQUEST_DOC_TYPE || "") : "");
            self.LAST_ACT_DATE = ko.observable( item ? (mlisUtil.convertServerSideDate(item.LAST_ACT_DATE) || "") : "");
            self.OPEN_DATE = ko.observable( item ? (mlisUtil.convertServerSideDate(item.OPEN_DATE) || "") : "");
            self.CLOSE_DATE = ko.observable( item ? (mlisUtil.convertServerSideDate(item.CLOSE_DATE) || "") : "");
            self.NEED_DATE = ko.observable( item ? (mlisUtil.convertServerSideDate(item.NEED_DATE) || "") : "");
            self.DISPLAY_DATE = ko.observable( item ? (mlisUtil.convertServerSideDate(item.DISPLAY_DATE) || "") : "");
            self.DISPLAY_STATUS = ko.observable( item ? (item.DISPLAY_STATUS || "") : "");
            self.ITEM_TYPE_CODE = ko.observable( item ? (item.ITEM_TYPE_CODE || "") : "");
            self.ITEM_VALUE_LIMIT = ko.observable( item ? (item.ITEM_VALUE_LIMIT || 0.0) : 0.0);
            self.RECEIVABLE_ITEM_SW = ko.observable( item ? (item.RECEIVABLE_ITEM_SW || "") : "");
            self.REOCCUR_CHRG_CODE = ko.observable( item ? (item.REOCCUR_CHRG_CODE || "") : "");
            self.MPSS_FEED_CODE = ko.observable( item ? (item.MPSS_FEED_CODE || "") : "");
            self.QTY_REQUEST_UNSCH = ko.observable( item ? (item.QTY_REQUEST_UNSCH || 0.0) : 0.0);
            self.QTY_REQUESTED = ko.observable( item ? (item.QTY_REQUESTED || 0.0) : 0.0);
            self.QTY_ORDERED = ko.observable( item ? (item.QTY_ORDERED || 0.0) : 0.0);
            self.QTY_UNSCHEDULED = ko.observable( item ? (item.QTY_UNSCHEDULED || 0.0) : 0.0);
            self.QTY_PROMISE_UNSCH = ko.observable( item ? (item.QTY_PROMISE_UNSCH || 0.0) : 0.0);
            self.QTY_RECEIVED = ko.observable( item ? (item.QTY_RECEIVED || 0.0) : 0.0);
            self.QTY_RCVD_AT_DEST = ko.observable( item ? (item.QTY_RCVD_AT_DEST || 0.0) : 0.0);
            self.QTY_BALANCE_DUE = ko.observable( item ? (item.QTY_BALANCE_DUE || 0.0) : 0.0);
            self.UNIT_PRICE = ko.observable( item ? (item.UNIT_PRICE || 0.0) : 0.0);
            self.UNIT_OF_PRICE = ko.observable( item ? (item.UNIT_OF_PRICE || "") : "");
            self.ITEM_TOTAL_VALUE = ko.observable( item ? (item.ITEM_TOTAL_VALUE || 0.0) : 0.0);
            self.SUB_ITEM_TOT_VALUE = ko.observable( item ? (item.SUB_ITEM_TOT_VALUE || 0.0) : 0.0);
            self.FOB_CODE = ko.observable( item ? (item.FOB_CODE || "") : "");
            self.FOB_CITY = ko.observable( item ? (item.FOB_CITY || "") : "");
            self.FOB_STATE = ko.observable( item ? (item.FOB_STATE || "") : "");
            self.FOB_COUNTRY = ko.observable( item ? (item.FOB_COUNTRY || "") : "");
            self.SUP_SO_NO = ko.observable( item ? (item.SUP_SO_NO || "") : "");
            self.SUP_SO_ITEM_NO = ko.observable( item ? (item.SUP_SO_ITEM_NO || "") : "");
            self.REQ_SORT_KEY = ko.observable( item ? (item.REQ_SORT_KEY || "") : "");
            self.PART_REV_LETTER = ko.observable( item ? (item.PART_REV_LETTER || "") : "");
            self.DK_CORP_PART_NO = ko.observable( item ? (item.DK_CORP_PART_NO || "") : "");
            self.PART_QUALIFIER_1 = ko.observable( item ? (item.PART_QUALIFIER_1 || "") : "");
            self.PART_QUALIFIER_2 = ko.observable( item ? (item.PART_QUALIFIER_2 || "") : "");
            self.PART_QUALIFIER_3 = ko.observable( item ? (item.PART_QUALIFIER_3 || "") : "");
            self.PART_QUALIFIER_4 = ko.observable( item ? (item.PART_QUALIFIER_4 || "") : "");
            self.MFR_PART_NO = ko.observable( item ? (item.MFR_PART_NO || "") : "");
            self.MFR_NAME = ko.observable( item ? (item.MFR_NAME || "") : "");
            self.ITEM_ID_INDEX = ko.observable( item ? (item.ITEM_ID_INDEX || "") : "");
            self.DK_COMMODITY_CODE = ko.observable( item ? (item.DK_COMMODITY_CODE || "") : "");
            self.TARGET_PRICE = ko.observable( item ? (item.TARGET_PRICE || 0.0) : 0.0);
            self.DK_UNIT_OF_MEASURE = ko.observable( item ? (item.DK_UNIT_OF_MEASURE || "") : "");
            self.MATL_DROP_POINT = ko.observable( item ? (item.MATL_DROP_POINT || "") : "");
            self.PROCURE_QUAL_LEVEL = ko.observable( item ? (item.PROCURE_QUAL_LEVEL || "") : "");
            self.ACCOUNT_INDEX = ko.observable( item ? (item.ACCOUNT_INDEX || "") : "");
            self.MAINT_REQUEST_NO = ko.observable( item ? (item.MAINT_REQUEST_NO || "") : "");
            self.STOCK_SW = ko.observable( item ? (item.STOCK_SW || "") : "");
            self.STOCK_UOM = ko.observable( item ? (item.STOCK_UOM || "") : "");
            self.STK_TO_PO_UOM_FACT = ko.observable( item ? (item.STK_TO_PO_UOM_FACT || 0.0) : 0.0);
            self.SALES_TAX_CODE = ko.observable( item ? (item.SALES_TAX_CODE || "") : "");
            self.ORIG_ZIP_CODE = ko.observable( item ? (item.ORIG_ZIP_CODE || 0) : 0);
            self.ORIG_AIRPORT_CODE = ko.observable( item ? (item.ORIG_AIRPORT_CODE || "") : "");
            self.DEST_ZIP_CODE = ko.observable( item ? (item.DEST_ZIP_CODE || 0) : 0);
            self.DEST_AIRPORT_CODE = ko.observable( item ? (item.DEST_AIRPORT_CODE || "") : "");
            self.STD_RTG_MODE = ko.observable( item ? (item.STD_RTG_MODE || "") : "");
            self.ROUTE_CODE = ko.observable( item ? (item.ROUTE_CODE || "") : "");
            self.DK_AGREE_NO = ko.observable( item ? (item.DK_AGREE_NO || "") : "");
            self.AGREE_TYPE = ko.observable( item ? (item.AGREE_TYPE || "") : "");
            self.AGREE_AUTO_REL_SW = ko.observable( item ? (item.AGREE_AUTO_REL_SW || "") : "");
            self.SUP_SELECT_CODE = ko.observable( item ? (item.SUP_SELECT_CODE || "") : "");
            self.LARGE_SOLICITED = ko.observable( item ? (item.LARGE_SOLICITED || 0) : 0);
            self.SMALL_SOLICITED = ko.observable( item ? (item.SMALL_SOLICITED || 0) : 0);
            self.DIS_ADV_SOLICITED = ko.observable( item ? (item.DIS_ADV_SOLICITED || 0) : 0);
            self.WHY_SMALL_NOT_SOL = ko.observable( item ? (item.WHY_SMALL_NOT_SOL || "") : "");
            self.WHY_SMALL_NOT_USED = ko.observable( item ? (item.WHY_SMALL_NOT_USED || "") : "");
            self.WHY_DIS_ADV_NOT_SL = ko.observable( item ? (item.WHY_DIS_ADV_NOT_SL || "") : "");
            self.USED_ON = ko.observable( item ? (item.USED_ON || "") : "");
            self.CONTRACT_NO = ko.observable( item ? (item.CONTRACT_NO || "") : "");
            self.CONTRACT_PRIORITY = ko.observable( item ? (item.CONTRACT_PRIORITY || "") : "");
            self.INSPECTION_SW = ko.observable( item ? (item.INSPECTION_SW || "") : "");
            self.AP_LAST_ACT_DATE = ko.observable(item ? (moment(item.AP_LAST_ACT_DATE).format('MM-DD-YYYY') || "") : "");
            self.AP_CLOSE_DATE = ko.observable( item ? (mlisUtil.convertServerSideDate(item.AP_CLOSE_DATE) || "") : "");
            self.AP_VALUE_CODE = ko.observable( item ? (item.AP_VALUE_CODE || "") : "");
            self.AP_QTY_CODE = ko.observable( item ? (item.AP_QTY_CODE || "") : "");
            self.AP_AMT_INVOICED = ko.observable( item ? (item.AP_AMT_INVOICED || 0.0) : 0.0);
            self.AP_AMT_VOUCHERED = ko.observable( item ? (item.AP_AMT_VOUCHERED || 0.0) : 0.0);
            self.AP_AMT_PAID = ko.observable( item ? (item.AP_AMT_PAID || 0.0) : 0.0);
            self.AP_QTY_VOUCHERED = ko.observable( item ? (item.AP_QTY_VOUCHERED || 0.0) : 0.0);
            self.AP_SUSPENSE_WEEKS = ko.observable(item ? (item.AP_SUSPENSE_WEEKS || 0) : 0);
            self.AP_CLOSE_STATUS = ko.observable(item ? (item.AP_CLOSE_STATUS || "") : "");
            self.NEXT_OPN_SCH_DATE = ko.observable( item ? (mlisUtil.convertServerSideDate(item.NEXT_OPN_SCH_DATE) || "") : "");
            self.BUYER_SORT_KEY = ko.observable( item ? (item.BUYER_SORT_KEY || 0.0) : 0.0);
            self.VAR_VALUE_PLUS = ko.observable( item ? (item.VAR_VALUE_PLUS || 0.0) : 0.0);
            self.VAR_VALUE_MINUS = ko.observable( item ? (item.VAR_VALUE_MINUS || 0.0) : 0.0);
            self.VAR_QTY_PLUS_PCT = ko.observable( item ? (item.VAR_QTY_PLUS_PCT || 0.0) : 0.0);
            self.VAR_QTY_MINUS_PCT = ko.observable( item ? (item.VAR_QTY_MINUS_PCT || 0.0) : 0.0);
            self.VAR_PRC_PLUS_PCT = ko.observable( item ? (item.VAR_PRC_PLUS_PCT || 0.0) : 0.0);
            self.VAR_PRC_MINUS_PCT = ko.observable( item ? (item.VAR_PRC_MINUS_PCT || 0.0) : 0.0);
            self.STANDARD_COST_CODE = ko.observable( item ? (item.STANDARD_COST_CODE || "") : "");
            self.PART_LEAD_TIME = ko.observable( item ? (item.PART_LEAD_TIME || 0) : 0);
            self.SUP_PART_LEAD_TIME = ko.observable( item ? (item.SUP_PART_LEAD_TIME || 0) : 0);
            self.DK_REQ_USER_ID = ko.observable( item ? (item.DK_REQ_USER_ID || "") : "");
            self.DK_SHIP_TO_CODE = ko.observable( item ? (item.DK_SHIP_TO_CODE || "") : "");
            self.QUAL_PURCH_REQ_SW = ko.observable( item ? (item.QUAL_PURCH_REQ_SW || "") : "");
            self.INVOICE_APPR_AUDIT = ko.observable( item ? (item.INVOICE_APPR_AUDIT || "") : "");
            self.INVENT_QUAL_LEVEL = ko.observable( item ? (item.INVENT_QUAL_LEVEL || "") : "");
            self.QUOTE_REFERENCE_ID = ko.observable( item ? (item.QUOTE_REFERENCE_ID || "") : "");
            self.AMOUNT_ONLY_SW = ko.observable( item ? (item.AMOUNT_ONLY_SW || "") : "");
            self.DK_SUP_NO = ko.observable( item ? (item.DK_SUP_NO || "") : "");
            self.DK_SUP_ABBREV = ko.observable( item ? (item.DK_SUP_ABBREV || "") : "");
            self.DK_CURRENCY_CODE = ko.observable( item ? (item.DK_CURRENCY_CODE || "") : "");
            self.DK_BUYER_NO = ko.observable( item ? (item.DK_BUYER_NO || 0) : 0);
            self.RESV_PO_NO = ko.observable( item ? (item.RESV_PO_NO || "") : "");
            self.RESV_PO_ITEM_NO = ko.observable( item ? (item.RESV_PO_ITEM_NO || "") : "");
            self.CURRENCY_ABBREVIATION = ko.observable( item ? (item.CURRENCY_ABBREVIATION || "") : "");
            self.TOT_ITEM_PRICE = ko.observable( item ? (item.TOT_ITEM_PRICE || "") : "");
            self.NOT_TO_EXCEED_PRICE = ko.observable( item ? (item.NOT_TO_EXCEED_PRICE || "") : "");
            self.LONG_DESCR = ko.observable( item ? (item.LONG_DESCR || "") : "");
            self.SCH_UPDT_ALLWD_FLG = ko.observable( item ? (item.SCH_UPDT_ALLWD_FLG || "") : "");
            self.NEW_ITEM_FLG = ko.observable( item ? (item.NEW_ITEM_FLG || "") : "");
            self.NO_CHARGE_FLG = ko.observable( item ? (item.NO_CHARGE_FLG || "") : "");
            self.CRT_FROM_MDL_FLG = ko.observable( item ? (item.CRT_FROM_MDL_FLG || "") : "");
            self.USR_IDX_DISP_TXT = ko.observable( item ? (item.USR_IDX_DISP_TXT || "") : "");
            self.TOTAL_QTY = ko.observable( item ? (item.TOTAL_QTY || "") : "");
            self.LANGUAGE_CODE = ko.observable( item ? (item.LANGUAGE_CODE || "") : "");
            self.BUYER_OVRD_SW = ko.observable( item ? (item.BUYER_OVRD_SW || "") : "");
            self.LOW_DOLLAR_SW = ko.observable( item ? (item.LOW_DOLLAR_SW || "") : "");
            self.COMMENTS_TO_SUP_SW = ko.observable( item ? (item.COMMENTS_TO_SUP_SW || "") : "");
            self.QUOTES_TO_SUP_SW = ko.observable( item ? (item.QUOTES_TO_SUP_SW || "") : "");
            self.REQUISITIONER_SWITCH = ko.observable( item ? (item.REQUISITIONER_SWITCH || "") : "");
            self.BUYER_SWITCH = ko.observable( item ? (item.BUYER_SWITCH || "") : "");
            self.USER_UPDATE_CODE = ko.observable( item ? (item.USER_UPDATE_CODE || "") : "");
            self.USER_TYPE_CODE = ko.observable( item ? (item.USER_TYPE_CODE || "") : "");
            self.OPER_WK_USERID = ko.observable( item ? (item.OPER_WK_USERID || "") : "");
            self.CONTINGENT_FLAG = ko.observable( item ? (item.CONTINGENT_FLAG || "") : "");
            self.FIRST_MANAGER_ID = ko.observable( item ? (item.FIRST_MANAGER_ID || "") : "");
        } catch (e) {
            JL(settings.jsFile).fatalException('Exception!', e);
        }
    }

    //Default constructor
    return {
        ITEM: ITEM
    }

})();