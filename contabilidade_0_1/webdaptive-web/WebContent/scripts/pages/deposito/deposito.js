var empresaModule = (function () {

    //Setup settings
    var settings = {
        jsFile: 'Empresa.js'
    };

    //Request Empresa default structure
    function EMPRESA(empresa) {
        try {
            var self = this;

            self.RP_HASHING_KEY = ko.observable( empresa ? (empresa.RP_HASHING_KEY || "") : "");
            self.RP_NO = ko.observable( empresa ? (empresa.RP_NO || "") : "");
            self.RP_EMPRESA_NO = ko.observable( empresa ? (empresa.RP_EMPRESA_NO || "") : "");
            self.RP_SUB_EMPRESA_NO = ko.observable( empresa ? (empresa.RP_SUB_EMPRESA_NO || "") : "");
            self.PO_NO = ko.observable( empresa ? (empresa.PO_NO || "") : "");
            self.PO_EMPRESA_NO = ko.observable( empresa ? (empresa.PO_EMPRESA_NO || "") : "");
            self.PO_SUB_EMPRESA_NO = ko.observable( empresa ? (empresa.PO_SUB_EMPRESA_NO || "") : "");
            self.FK_QTH_QT_NO = ko.observable( empresa ? (empresa.FK_QTH_QT_NO || "") : "");
            self.STATUS_CODE = ko.observable( empresa ? (empresa.STATUS_CODE || "") : "");
            self.STATUS_REASON_CODE = ko.observable( empresa ? (empresa.STATUS_REASON_CODE || "") : "");
            self.POCR_STATUS_CODE = ko.observable( empresa ? (empresa.POCR_STATUS_CODE || "") : "");
            self.LIFE_CYCLE_INDEX = ko.observable( empresa ? (empresa.LIFE_CYCLE_INDEX || "") : "");
            self.LOCATION_CODE = ko.observable( empresa ? (empresa.LOCATION_CODE || "") : "");
            self.LOCATION_DATE = ko.observable( empresa ? (mlisUtil.convertServerSideDate(empresa.LOCATION_DATE) || "") : "");
            self.EXPEDITE_CODE = ko.observable( empresa ? (empresa.EXPEDITE_CODE || "") : "");
            self.REQUEST_DOC_TYPE = ko.observable( empresa ? (empresa.REQUEST_DOC_TYPE || "") : "");
            self.LAST_ACT_DATE = ko.observable( empresa ? (mlisUtil.convertServerSideDate(empresa.LAST_ACT_DATE) || "") : "");
            self.OPEN_DATE = ko.observable( empresa ? (mlisUtil.convertServerSideDate(empresa.OPEN_DATE) || "") : "");
            self.CLOSE_DATE = ko.observable( empresa ? (mlisUtil.convertServerSideDate(empresa.CLOSE_DATE) || "") : "");
            self.NEED_DATE = ko.observable( empresa ? (mlisUtil.convertServerSideDate(empresa.NEED_DATE) || "") : "");
            self.DISPLAY_DATE = ko.observable( empresa ? (mlisUtil.convertServerSideDate(empresa.DISPLAY_DATE) || "") : "");
            self.DISPLAY_STATUS = ko.observable( empresa ? (empresa.DISPLAY_STATUS || "") : "");
            self.EMPRESA_TYPE_CODE = ko.observable( empresa ? (empresa.EMPRESA_TYPE_CODE || "") : "");
            self.EMPRESA_VALUE_LIMIT = ko.observable( empresa ? (empresa.EMPRESA_VALUE_LIMIT || 0.0) : 0.0);
            self.RECEIVABLE_EMPRESA_SW = ko.observable( empresa ? (empresa.RECEIVABLE_EMPRESA_SW || "") : "");
            self.REOCCUR_CHRG_CODE = ko.observable( empresa ? (empresa.REOCCUR_CHRG_CODE || "") : "");
            self.MPSS_FEED_CODE = ko.observable( empresa ? (empresa.MPSS_FEED_CODE || "") : "");
            self.QTY_REQUEST_UNSCH = ko.observable( empresa ? (empresa.QTY_REQUEST_UNSCH || 0.0) : 0.0);
            self.QTY_REQUESTED = ko.observable( empresa ? (empresa.QTY_REQUESTED || 0.0) : 0.0);
            self.QTY_ORDERED = ko.observable( empresa ? (empresa.QTY_ORDERED || 0.0) : 0.0);
            self.QTY_UNSCHEDULED = ko.observable( empresa ? (empresa.QTY_UNSCHEDULED || 0.0) : 0.0);
            self.QTY_PROMISE_UNSCH = ko.observable( empresa ? (empresa.QTY_PROMISE_UNSCH || 0.0) : 0.0);
            self.QTY_RECEIVED = ko.observable( empresa ? (empresa.QTY_RECEIVED || 0.0) : 0.0);
            self.QTY_RCVD_AT_DEST = ko.observable( empresa ? (empresa.QTY_RCVD_AT_DEST || 0.0) : 0.0);
            self.QTY_BALANCE_DUE = ko.observable( empresa ? (empresa.QTY_BALANCE_DUE || 0.0) : 0.0);
            self.UNIT_PRICE = ko.observable( empresa ? (empresa.UNIT_PRICE || 0.0) : 0.0);
            self.UNIT_OF_PRICE = ko.observable( empresa ? (empresa.UNIT_OF_PRICE || "") : "");
            self.EMPRESA_TOTAL_VALUE = ko.observable( empresa ? (empresa.EMPRESA_TOTAL_VALUE || 0.0) : 0.0);
            self.SUB_EMPRESA_TOT_VALUE = ko.observable( empresa ? (empresa.SUB_EMPRESA_TOT_VALUE || 0.0) : 0.0);
            self.FOB_CODE = ko.observable( empresa ? (empresa.FOB_CODE || "") : "");
            self.FOB_CITY = ko.observable( empresa ? (empresa.FOB_CITY || "") : "");
            self.FOB_STATE = ko.observable( empresa ? (empresa.FOB_STATE || "") : "");
            self.FOB_COUNTRY = ko.observable( empresa ? (empresa.FOB_COUNTRY || "") : "");
            self.SUP_SO_NO = ko.observable( empresa ? (empresa.SUP_SO_NO || "") : "");
            self.SUP_SO_EMPRESA_NO = ko.observable( empresa ? (empresa.SUP_SO_EMPRESA_NO || "") : "");
            self.REQ_SORT_KEY = ko.observable( empresa ? (empresa.REQ_SORT_KEY || "") : "");
            self.PART_REV_LETTER = ko.observable( empresa ? (empresa.PART_REV_LETTER || "") : "");
            self.DK_CORP_PART_NO = ko.observable( empresa ? (empresa.DK_CORP_PART_NO || "") : "");
            self.PART_QUALIFIER_1 = ko.observable( empresa ? (empresa.PART_QUALIFIER_1 || "") : "");
            self.PART_QUALIFIER_2 = ko.observable( empresa ? (empresa.PART_QUALIFIER_2 || "") : "");
            self.PART_QUALIFIER_3 = ko.observable( empresa ? (empresa.PART_QUALIFIER_3 || "") : "");
            self.PART_QUALIFIER_4 = ko.observable( empresa ? (empresa.PART_QUALIFIER_4 || "") : "");
            self.MFR_PART_NO = ko.observable( empresa ? (empresa.MFR_PART_NO || "") : "");
            self.MFR_NAME = ko.observable( empresa ? (empresa.MFR_NAME || "") : "");
            self.EMPRESA_ID_INDEX = ko.observable( empresa ? (empresa.EMPRESA_ID_INDEX || "") : "");
            self.DK_COMMODITY_CODE = ko.observable( empresa ? (empresa.DK_COMMODITY_CODE || "") : "");
            self.TARGET_PRICE = ko.observable( empresa ? (empresa.TARGET_PRICE || 0.0) : 0.0);
            self.DK_UNIT_OF_MEASURE = ko.observable( empresa ? (empresa.DK_UNIT_OF_MEASURE || "") : "");
            self.MATL_DROP_POINT = ko.observable( empresa ? (empresa.MATL_DROP_POINT || "") : "");
            self.PROCURE_QUAL_LEVEL = ko.observable( empresa ? (empresa.PROCURE_QUAL_LEVEL || "") : "");
            self.ACCOUNT_INDEX = ko.observable( empresa ? (empresa.ACCOUNT_INDEX || "") : "");
            self.MAINT_REQUEST_NO = ko.observable( empresa ? (empresa.MAINT_REQUEST_NO || "") : "");
            self.STOCK_SW = ko.observable( empresa ? (empresa.STOCK_SW || "") : "");
            self.STOCK_UOM = ko.observable( empresa ? (empresa.STOCK_UOM || "") : "");
            self.STK_TO_PO_UOM_FACT = ko.observable( empresa ? (empresa.STK_TO_PO_UOM_FACT || 0.0) : 0.0);
            self.SALES_TAX_CODE = ko.observable( empresa ? (empresa.SALES_TAX_CODE || "") : "");
            self.ORIG_ZIP_CODE = ko.observable( empresa ? (empresa.ORIG_ZIP_CODE || 0) : 0);
            self.ORIG_AIRPORT_CODE = ko.observable( empresa ? (empresa.ORIG_AIRPORT_CODE || "") : "");
            self.DEST_ZIP_CODE = ko.observable( empresa ? (empresa.DEST_ZIP_CODE || 0) : 0);
            self.DEST_AIRPORT_CODE = ko.observable( empresa ? (empresa.DEST_AIRPORT_CODE || "") : "");
            self.STD_RTG_MODE = ko.observable( empresa ? (empresa.STD_RTG_MODE || "") : "");
            self.ROUTE_CODE = ko.observable( empresa ? (empresa.ROUTE_CODE || "") : "");
            self.DK_AGREE_NO = ko.observable( empresa ? (empresa.DK_AGREE_NO || "") : "");
            self.AGREE_TYPE = ko.observable( empresa ? (empresa.AGREE_TYPE || "") : "");
            self.AGREE_AUTO_REL_SW = ko.observable( empresa ? (empresa.AGREE_AUTO_REL_SW || "") : "");
            self.SUP_SELECT_CODE = ko.observable( empresa ? (empresa.SUP_SELECT_CODE || "") : "");
            self.LARGE_SOLICITED = ko.observable( empresa ? (empresa.LARGE_SOLICITED || 0) : 0);
            self.SMALL_SOLICITED = ko.observable( empresa ? (empresa.SMALL_SOLICITED || 0) : 0);
            self.DIS_ADV_SOLICITED = ko.observable( empresa ? (empresa.DIS_ADV_SOLICITED || 0) : 0);
            self.WHY_SMALL_NOT_SOL = ko.observable( empresa ? (empresa.WHY_SMALL_NOT_SOL || "") : "");
            self.WHY_SMALL_NOT_USED = ko.observable( empresa ? (empresa.WHY_SMALL_NOT_USED || "") : "");
            self.WHY_DIS_ADV_NOT_SL = ko.observable( empresa ? (empresa.WHY_DIS_ADV_NOT_SL || "") : "");
            self.USED_ON = ko.observable( empresa ? (empresa.USED_ON || "") : "");
            self.CONTRACT_NO = ko.observable( empresa ? (empresa.CONTRACT_NO || "") : "");
            self.CONTRACT_PRIORITY = ko.observable( empresa ? (empresa.CONTRACT_PRIORITY || "") : "");
            self.INSPECTION_SW = ko.observable( empresa ? (empresa.INSPECTION_SW || "") : "");
            self.AP_LAST_ACT_DATE = ko.observable(empresa ? (moment(empresa.AP_LAST_ACT_DATE).format('MM-DD-YYYY') || "") : "");
            self.AP_CLOSE_DATE = ko.observable( empresa ? (mlisUtil.convertServerSideDate(empresa.AP_CLOSE_DATE) || "") : "");
            self.AP_VALUE_CODE = ko.observable( empresa ? (empresa.AP_VALUE_CODE || "") : "");
            self.AP_QTY_CODE = ko.observable( empresa ? (empresa.AP_QTY_CODE || "") : "");
            self.AP_AMT_INVOICED = ko.observable( empresa ? (empresa.AP_AMT_INVOICED || 0.0) : 0.0);
            self.AP_AMT_VOUCHERED = ko.observable( empresa ? (empresa.AP_AMT_VOUCHERED || 0.0) : 0.0);
            self.AP_AMT_PAID = ko.observable( empresa ? (empresa.AP_AMT_PAID || 0.0) : 0.0);
            self.AP_QTY_VOUCHERED = ko.observable( empresa ? (empresa.AP_QTY_VOUCHERED || 0.0) : 0.0);
            self.AP_SUSPENSE_WEEKS = ko.observable(empresa ? (empresa.AP_SUSPENSE_WEEKS || 0) : 0);
            self.AP_CLOSE_STATUS = ko.observable(empresa ? (empresa.AP_CLOSE_STATUS || "") : "");
            self.NEXT_OPN_SCH_DATE = ko.observable( empresa ? (mlisUtil.convertServerSideDate(empresa.NEXT_OPN_SCH_DATE) || "") : "");
            self.BUYER_SORT_KEY = ko.observable( empresa ? (empresa.BUYER_SORT_KEY || 0.0) : 0.0);
            self.VAR_VALUE_PLUS = ko.observable( empresa ? (empresa.VAR_VALUE_PLUS || 0.0) : 0.0);
            self.VAR_VALUE_MINUS = ko.observable( empresa ? (empresa.VAR_VALUE_MINUS || 0.0) : 0.0);
            self.VAR_QTY_PLUS_PCT = ko.observable( empresa ? (empresa.VAR_QTY_PLUS_PCT || 0.0) : 0.0);
            self.VAR_QTY_MINUS_PCT = ko.observable( empresa ? (empresa.VAR_QTY_MINUS_PCT || 0.0) : 0.0);
            self.VAR_PRC_PLUS_PCT = ko.observable( empresa ? (empresa.VAR_PRC_PLUS_PCT || 0.0) : 0.0);
            self.VAR_PRC_MINUS_PCT = ko.observable( empresa ? (empresa.VAR_PRC_MINUS_PCT || 0.0) : 0.0);
            self.STANDARD_COST_CODE = ko.observable( empresa ? (empresa.STANDARD_COST_CODE || "") : "");
            self.PART_LEAD_TIME = ko.observable( empresa ? (empresa.PART_LEAD_TIME || 0) : 0);
            self.SUP_PART_LEAD_TIME = ko.observable( empresa ? (empresa.SUP_PART_LEAD_TIME || 0) : 0);
            self.DK_REQ_USER_ID = ko.observable( empresa ? (empresa.DK_REQ_USER_ID || "") : "");
            self.DK_SHIP_TO_CODE = ko.observable( empresa ? (empresa.DK_SHIP_TO_CODE || "") : "");
            self.QUAL_PURCH_REQ_SW = ko.observable( empresa ? (empresa.QUAL_PURCH_REQ_SW || "") : "");
            self.INVOICE_APPR_AUDIT = ko.observable( empresa ? (empresa.INVOICE_APPR_AUDIT || "") : "");
            self.INVENT_QUAL_LEVEL = ko.observable( empresa ? (empresa.INVENT_QUAL_LEVEL || "") : "");
            self.QUOTE_REFERENCE_ID = ko.observable( empresa ? (empresa.QUOTE_REFERENCE_ID || "") : "");
            self.AMOUNT_ONLY_SW = ko.observable( empresa ? (empresa.AMOUNT_ONLY_SW || "") : "");
            self.DK_SUP_NO = ko.observable( empresa ? (empresa.DK_SUP_NO || "") : "");
            self.DK_SUP_ABBREV = ko.observable( empresa ? (empresa.DK_SUP_ABBREV || "") : "");
            self.DK_CURRENCY_CODE = ko.observable( empresa ? (empresa.DK_CURRENCY_CODE || "") : "");
            self.DK_BUYER_NO = ko.observable( empresa ? (empresa.DK_BUYER_NO || 0) : 0);
            self.RESV_PO_NO = ko.observable( empresa ? (empresa.RESV_PO_NO || "") : "");
            self.RESV_PO_EMPRESA_NO = ko.observable( empresa ? (empresa.RESV_PO_EMPRESA_NO || "") : "");
            self.CURRENCY_ABBREVIATION = ko.observable( empresa ? (empresa.CURRENCY_ABBREVIATION || "") : "");
            self.TOT_EMPRESA_PRICE = ko.observable( empresa ? (empresa.TOT_EMPRESA_PRICE || "") : "");
            self.NOT_TO_EXCEED_PRICE = ko.observable( empresa ? (empresa.NOT_TO_EXCEED_PRICE || "") : "");
            self.LONG_DESCR = ko.observable( empresa ? (empresa.LONG_DESCR || "") : "");
            self.SCH_UPDT_ALLWD_FLG = ko.observable( empresa ? (empresa.SCH_UPDT_ALLWD_FLG || "") : "");
            self.NEW_EMPRESA_FLG = ko.observable( empresa ? (empresa.NEW_EMPRESA_FLG || "") : "");
            self.NO_CHARGE_FLG = ko.observable( empresa ? (empresa.NO_CHARGE_FLG || "") : "");
            self.CRT_FROM_MDL_FLG = ko.observable( empresa ? (empresa.CRT_FROM_MDL_FLG || "") : "");
            self.USR_IDX_DISP_TXT = ko.observable( empresa ? (empresa.USR_IDX_DISP_TXT || "") : "");
            self.TOTAL_QTY = ko.observable( empresa ? (empresa.TOTAL_QTY || "") : "");
            self.LANGUAGE_CODE = ko.observable( empresa ? (empresa.LANGUAGE_CODE || "") : "");
            self.BUYER_OVRD_SW = ko.observable( empresa ? (empresa.BUYER_OVRD_SW || "") : "");
            self.LOW_DOLLAR_SW = ko.observable( empresa ? (empresa.LOW_DOLLAR_SW || "") : "");
            self.COMMENTS_TO_SUP_SW = ko.observable( empresa ? (empresa.COMMENTS_TO_SUP_SW || "") : "");
            self.QUOTES_TO_SUP_SW = ko.observable( empresa ? (empresa.QUOTES_TO_SUP_SW || "") : "");
            self.REQUISITIONER_SWITCH = ko.observable( empresa ? (empresa.REQUISITIONER_SWITCH || "") : "");
            self.BUYER_SWITCH = ko.observable( empresa ? (empresa.BUYER_SWITCH || "") : "");
            self.USER_UPDATE_CODE = ko.observable( empresa ? (empresa.USER_UPDATE_CODE || "") : "");
            self.USER_TYPE_CODE = ko.observable( empresa ? (empresa.USER_TYPE_CODE || "") : "");
            self.OPER_WK_USERID = ko.observable( empresa ? (empresa.OPER_WK_USERID || "") : "");
            self.CONTINGENT_FLAG = ko.observable( empresa ? (empresa.CONTINGENT_FLAG || "") : "");
            self.FIRST_MANAGER_ID = ko.observable( empresa ? (empresa.FIRST_MANAGER_ID || "") : "");
        } catch (e) {
            JL(settings.jsFile).fatalException('Exception!', e);
        }
    }

    //Default constructor
    return {
        EMPRESA: EMPRESA
    }

})();