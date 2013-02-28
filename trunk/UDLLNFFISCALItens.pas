unit UDLLNFFISCALItens;

interface

type
 TNFISCALitens = Class
    private
    FDCNUMERO,FCOD_EMPRESA,FCOD_FILIAL,FCOD_ITEM,FCOD_CTA,FCDFORNECEDOR,FCODNFe :        INTEGER;
    FDESCR_COMPL,FCFOP,FDCTIPO,FDCORDEM,FDCSERIE,FREF,FCod_NCM,FNCM_COMPL,Frateio :STRING ;
    FQTD,FUNID,FVL_ITEM,FVL_DESC,FIND_MOV,FCST_ICMS,FVL_PIS,FALIQ_ICMS,FVL_ICMS,FVL_BC_ICMS,FVL_BC_ICMS_ST,FALIQ_ST,FVL_ICMS_ST,FIND_APUR,FCST_IPI,FCOD_ENQ,FVL_BC_IPI,FALIQ_IPI,FVL_IPI,FCST_PIS,FVL_BC_PIS,FALIQ_PIS,FQUANT_BC_PIS,FVL_PI,FCST_COFINS,FVL_BC_COFINS,FALIQ_COFINS,FQUANT_BC_COFINS,FVL_COFINS:DOUBLE;

    protected

    function  Getdcnumero         : Integer;
    function  GetDCSERIE      : String;
    function  GetDCORDEM      : String;
    function  GetDCTIPO      : String;
    function  GetCDFORNECEDOR      : INTEGER;
    function  GetCODNFe           : INTEGER;
    function  GetCOD_EMPRESA      : Integer;
    function  Getcod_filial       : Integer;
    function  GetCOD_ITEM         : Integer;
    function  GetDESCR_COMPL      : String;
    function  GetRef              : String;
    function  GetCod_NCM          : String;
    function  GetNCM_COMPL        : String;
    function  GetQTD              : Double;
    function  GetUNID             : double;
    function  GetVL_ITEM          : Double;
    function  GetVL_DESC          : Double;
    function  GetIND_MOV          : Double;
    function  GetCST_ICMS         : Double;
    function  GetCFOP             : String;
    function  GetRateio             : String;
    function  GetALIQ_ICMS        : Double;
    function  GetVL_ICMS          : Double;
    function  GetVL_BC_ICMS       : Double;
    function  GetVL_BC_ICMS_ST    : Double;
    function  GetALIQ_ST          : Double;
    function  GetVL_ICMS_ST       : Double;
    function  GetIND_APUR         : Double;
    function  GetCST_IPI          : Double;
    function  GetCOD_ENQ          : Double;
    function  GetVL_BC_IPI        : Double;
    function  GetALIQ_IPI         : Double;
    function  GetVL_IPI           : Double;
    function  GetCST_PIS          : Double;
    function  GetVL_BC_PIS        : Double;
    function  GetALIQ_PIS         : Double;
    function  GetQUANT_BC_PIS     : Double;
    function  GetVL_PIS           : Double;
    function  GetCST_COFINS       : Double;
    function  GetVL_BC_COFINS     : Double;
    function  GetALIQ_COFINS      : Double;
    function  GetQUANT_BC_COFINS  : Double;
    function  GetVL_COFINS        : Double;
    function  GetCOD_CTA          :INTEGER;




    Procedure  Setdcnumero         (Value : Integer);
    Procedure  SetDCSERIE          (Value : String);
    Procedure  SetDCORDEM          (Value : String);
    Procedure  SetDCTIPO           (Value : String);
    Procedure  SetCODNFe           (Value : INTEGER);
    Procedure  SetCDFORNECEDOR     (Value : INTEGER);
    Procedure  SetCOD_EMPRESA      (Value : Integer);
    Procedure  SetCOD_FILIAL       (Value : Integer);
    Procedure  SetCOD_ITEM         (Value : Integer);
    Procedure  SetDESCR_COMPL      (Value : String);

    Procedure  SetRef            (Value : String);
    Procedure  SetCod_NCM        (Value : String);
    Procedure  SetNCM_COMPL      (Value : String);


    Procedure  SetQTD              (Value : DOUBLE);
    Procedure  SetUNID             (Value : DOUBLE);
    Procedure  SetVL_ITEM          (Value : DOUBLE);
    Procedure  SetVL_DESC          (Value : DOUBLE);
    Procedure  SetIND_MOV          (Value : DOUBLE);
    Procedure  SetCST_ICMS         (Value : DOUBLE);
    Procedure  SetCFOP             (Value : String);
    Procedure  SetRateio           (Value : String);
    Procedure  SetALIQ_ICMS        (Value : DOUBLE);
    Procedure  SetVL_ICMS          (Value : DOUBLE);
    Procedure  SetVL_BC_ICMS_ST    (Value : DOUBLE);
    Procedure  SetVL_BC_ICMS       (Value : DOUBLE);
    Procedure  SetALIQ_ST          (Value : DOUBLE);
    Procedure  SetVL_ICMS_ST       (Value : DOUBLE);
    Procedure  SetIND_APUR         (Value : DOUBLE);
    Procedure  SetCST_IPI          (Value : DOUBLE);
    Procedure  SetCOD_ENQ          (Value : DOUBLE);
    Procedure  SetVL_BC_IPI        (Value : DOUBLE);
    Procedure  SetALIQ_IPI         (Value : DOUBLE);
    Procedure  SetVL_IPI           (Value : DOUBLE);
    Procedure  SetCST_PIS          (Value : DOUBLE);
    Procedure  SetVL_BC_PIS        (Value : DOUBLE);
    Procedure  SetALIQ_PIS         (Value : DOUBLE);
    Procedure  SetQUANT_BC_PIS     (Value : DOUBLE);
    Procedure  SetVL_PIS           (Value : DOUBLE);
    Procedure  SetCST_COFINS       (Value : DOUBLE);
    Procedure  SetVL_BC_COFINS     (Value : DOUBLE);
    Procedure  SetALIQ_COFINS      (Value : DOUBLE);
    Procedure  SetQUANT_BC_COFINS  (Value : DOUBLE);
    Procedure  SetVL_COFINS        (Value : DOUBLE);
    Procedure  SetCOD_CTA          (Value : INTEGER);


    public
      property  DCNUMERO          : Integer read GetDCNUMERO              write SetDCNUMERO;
      property  DCSERIE           : String  read GetDCSERIE               write SetDCSERIE;
      property  DCTIPO            : String  read GetDCTIPO                write SetDCTIPO;
      property  DCORDEM           : String  read GetDCORDEM               write SetDCORDEM;
      property  CDFORNECEDOR      : iNTEGER  read GetCDFORNECEDOR         write SetCDFORNECEDOR;
      property  Ref               : String  read GetRef                   write SetRef;
      property  Cod_NCM           : String  read GetCod_NCM               write SetCod_NCM;
      property  NCM_COMPL         : String  read GetNCM_COMPL             write SetDESCR_COMPL;
      property  COD_EMPRESA       : Integer read GetCOD_EMPRESA           write SetCOD_EMPRESA;
      property  CODNFe            : Integer read GetCODNFe                write SetCODNFe;
      property  COD_FILIAL        : Integer read GetCOD_FILIAL            write SetCOD_FILIAL;
      property  COD_ITEM          : Integer read GetCOD_ITEM              write SetCOD_ITEM;
      property  COD_CTA           : Integer read GetCOD_CTA               write SetCOD_CTA;
      property  CDESCR_COMPL      : String  read GetDESCR_COMPL           write SetDESCR_COMPL;
      property  rateio            : String  read Getrateio                   write SetRateio;
      property  QTD               : DOUBLE read GetQTD                    write SetQTD;
      property  UNID              : DOUBLE read GetUNID                   write SetUNID;
      property  VL_ITEM           : DOUBLE read GetVL_ITEM                write SetVL_ITEM;
      property  VL_DESC           : DOUBLE read GetVL_DESC                write SetVL_DESC;
      property  IND_MOV           : DOUBLE read GetIND_MOV                write SetIND_MOV;
      property  CST_ICMS          : DOUBLE read GetCST_ICMS               write SetCST_ICMS;
      property  CFOP              : String read GetCFOP                   write SetCFOP;
      property  ALIQ_ICMS         : DOUBLE read GetALIQ_ICMS              write SetALIQ_ICMS;
      property  VL_ICMS           : DOUBLE read GetVL_ICMS                write SetVL_ICMS;
      property  VL_BC_ICMS        : DOUBLE read GetVL_BC_ICMS             write SetVL_BC_ICMS;
      property  VL_BC_ICMS_ST     : DOUBLE read GetVL_BC_ICMS_ST          write SetVL_BC_ICMS_ST;
      property  ALIQ_ST           : DOUBLE read GetALIQ_ST                write SetALIQ_ST;
      property  VL_ICMS_ST        : DOUBLE read GetVL_ICMS_ST             write SetVL_ICMS_ST;
      property  IND_APUR          : DOUBLE read GetIND_APUR               write SetIND_APUR;
      property  CST_IPI           : DOUBLE read GetCST_IPI                write SetCST_IPI ;
      property  COD_ENQ           : DOUBLE read GetCOD_ENQ                write SetCOD_ENQ ;
      property  VL_BC_IPI         : DOUBLE read GetVL_BC_IPI              write SetVL_BC_IPI;
      property  ALIQ_IPI          : DOUBLE read GetALIQ_IPI               write SetALIQ_IPI;
      property  VL_IPI            : DOUBLE read GetVL_IPI                 write SetVL_IPI;
      property  CST_PIS           : DOUBLE read GetCST_PIS                write SetCST_PIS;
      property  VL_BC_PIS         : DOUBLE read GetVL_BC_PIS              write SetVL_BC_PIS;
      property  ALIQ_PIS          : DOUBLE read GetALIQ_PIS               write SetALIQ_PIS;
      property  QUANT_BC_PIS      : DOUBLE read GetQUANT_BC_PIS           write SetQUANT_BC_PIS;
      property  VL_PI             : DOUBLE read GetVL_IPI                 write SetVL_IPI;
      property  CST_COFINS        : DOUBLE read GetCST_COFINS             write SetCST_COFINS;
      property  VL_BC_COFINS      : DOUBLE read GetVL_BC_COFINS           write SetVL_BC_COFINS;
      property  ALIQ_COFINS       : DOUBLE read GetALIQ_COFINS            write SetALIQ_COFINS;
      property  QUANT_BC_COFINS   : DOUBLE read GetQUANT_BC_COFINS        write SetQUANT_BC_COFINS;
      property  VL_COFINS         : DOUBLE read GetVL_COFINS              write SetVL_COFINS;

  End;
implementation

uses SysUtils;
    function  TNFISCALitens.GetRef      : String;
    Begin
      Result := Self.Fref;
      End;

     function  TNFISCALitens.GetCod_NCM      : String;
    Begin
      Result := Self.FCod_NCM;
      End;

    function  TNFISCALitens.GetNCM_COMPL      : String;
    Begin
      Result := Self.FNCM_COMPL;
      End;

    function  TNFISCALitens.GetDCTIPO      : String;
    Begin
      Result := Self.FDCTIPO;
      End;
          function  TNFISCALitens.GetDCORDEM      : String;
    Begin
      Result := Self.FDCORDEM;
      End;
          function  TNFISCALitens.GetDCSERIE      : String;
    Begin
      Result := Self.FDCSERIE;
      End;
          function  TNFISCALitens.GetCDFORNECEDOR      : iNTEGER;
    Begin
      Result := Self.FCDFORNECEDOR;
      End;

    function  TNFISCALitens.GetCODNFe         : Integer;
    Begin
      Result := Self.FCODNFe;
    End;


    function  TNFISCALitens.Getdcnumero         : Integer;
    Begin
      Result := Self.Fdcnumero;
    End;
    function  TNFISCALitens.GetCOD_EMPRESA      : Integer;
    Begin
      Result := Self.FCOD_EMPRESA;
      End;
    function  TNFISCALitens.Getcod_filial       : Integer;
    Begin
      Result := Self.Fcod_filial;
      End;
    function  TNFISCALitens.GetCOD_ITEM         : Integer;
    Begin
      Result := Self.FCOD_ITEM;
      End;
    function  TNFISCALitens.GetDESCR_COMPL      : String;
    Begin
      Result := Self.FDESCR_COMPL;
      End;

      function  TNFISCALitens.Getrateio      : String;
    Begin
      Result := Self.Frateio;
      End;

    function  TNFISCALitens.GetQTD              : Double;
    Begin
      Result := Self.FQTD;
    End;
    function  TNFISCALitens.GetUNID             : double;
    Begin
      Result := Self.FUNID;
    End;
    function  TNFISCALitens.GetVL_ITEM          : Double;
    Begin
      Result := Self.FVL_ITEM;
    End;
    function  TNFISCALitens.GetVL_DESC          : Double;
    Begin
      Result := Self.FVL_DESC;
    End;
    function  TNFISCALitens.GetIND_MOV          : Double;
    Begin
      Result := Self.FIND_MOV;
    End;
    function  TNFISCALitens.GetCST_ICMS         : Double;
    Begin
      Result := Self.FCST_ICMS;
    End;
    function  TNFISCALitens.GetCFOP             : String;
    Begin
      Result := Self.FCFOP;
    End;
    function  TNFISCALitens.GetALIQ_ICMS        : Double;
    Begin
      Result := Self.FALIQ_ICMS;
    End;
    function  TNFISCALitens.GetVL_ICMS          : Double;
    Begin
      Result := Self.FVL_ICMS;
    End;
    function  TNFISCALitens.GetVL_BC_ICMS_ST    : Double;
    Begin
      Result := Self.FVL_BC_ICMS_ST;
    End;
        function  TNFISCALitens.GetVL_BC_ICMS    : Double;
    Begin
      Result := Self.FVL_BC_ICMS;
    End;



    function  TNFISCALitens.GetALIQ_ST          : Double;
    Begin
      Result := Self.FALIQ_ST;
    End;
    function  TNFISCALitens.GetVL_ICMS_ST       : Double;
    Begin
      Result := Self.FVL_ICMS_ST;
    End;
    function  TNFISCALitens.GetIND_APUR         : Double;
    Begin
      Result := Self.FIND_APUR;
    End;
    function  TNFISCALitens.GetCST_IPI          : Double;
    Begin
      Result := Self.FCST_IPI ;
    End;
    function  TNFISCALitens.GetCOD_ENQ          : Double;
    Begin
      Result := Self.FCOD_ENQ;
    End;
    function  TNFISCALitens.GetVL_BC_IPI        : Double;
    Begin
      Result := Self.FVL_BC_IPI;
    End;
    function  TNFISCALitens.GetALIQ_IPI         : Double;
    Begin
      Result := Self.FALIQ_IPI;
    End;
    function  TNFISCALitens.GetVL_IPI           : Double;
    Begin
      Result := Self.FVL_IPI;
    End;
    function  TNFISCALitens.GetCST_PIS          : Double;
    Begin
      Result := Self.FCST_PIS;
    End;
    function  TNFISCALitens.GetVL_BC_PIS        : Double;
    Begin
      Result := Self.FVL_BC_PIS;
    End;
    function  TNFISCALitens.GetALIQ_PIS         : Double;
    Begin
      Result := Self.FALIQ_PIS;
    End;
    function  TNFISCALitens.GetQUANT_BC_PIS     : Double;
    Begin
      Result := Self.FQUANT_BC_PIS;
    End;
    function  TNFISCALitens.GetVL_PIS           : Double;
    Begin
      Result := Self.FVL_PIS;
    End;
    function  TNFISCALitens.GetCST_COFINS       : Double;
    Begin
      Result := Self.FCST_COFINS;
    End;
    function  TNFISCALitens.GetVL_BC_COFINS     : Double;
    Begin
      Result := Self.FVL_BC_COFINS;
    End;
    function  TNFISCALitens.GetALIQ_COFINS      : Double;
    Begin
      Result := Self.FALIQ_COFINS;
    End;
    function  TNFISCALitens.GetQUANT_BC_COFINS  : Double;
    Begin
      Result := Self.FQUANT_BC_COFINS;
    End;
    function  TNFISCALitens.GetVL_COFINS        : Double;
    Begin
      Result := Self.FVL_COFINS;
    End;
    function  TNFISCALitens.GetCOD_CTA          :INTEGER;
    Begin
      Result := Self.FCOD_CTA;
    End;
    Procedure  TNFISCALitens.SetREF     (Value : String);
    Begin
      Self.FRef := Value;
    End;
    Procedure  TNFISCALitens.SetCod_NCM     (Value : String);
    Begin
      Self.FCod_NCM := Value;
    End;
    Procedure  TNFISCALitens.SetNCM_COMPL     (Value : String);
    Begin
      Self.FNCM_COMPL := Value;
    End;




        Procedure  TNFISCALitens.SetDCSERIE     (Value : String);
    Begin
      Self.FDCSERIE := Value;
    End;
        Procedure  TNFISCALitens.SetDCORDEM      (Value : String);
    Begin
      Self.FDCORDEM := Value;
    End;
        Procedure  TNFISCALitens.SetDCTIPO      (Value : String);
    Begin
      Self.FDCTIPO := Value;
    End;
            Procedure  TNFISCALitens.Setrateio      (Value : String);
    Begin
      Self.Frateio := Value;
    End;
    Procedure  TNFISCALitens.SetCODNFe      (Value : INTEGER);
    Begin
      Self.FCODNFe := Value;
    End;

    Procedure  TNFISCALitens.SetCDFORNECEDOR      (Value : INTEGER);
    Begin
      Self.FCDFORNECEDOR := Value;
    End;
    Procedure  TNFISCALitens.Setdcnumero         (Value : Integer);
    Begin
      Self.FDCNUMERO := Value;
    End;
    Procedure  TNFISCALitens.SetCOD_EMPRESA      (Value : Integer);
    Begin
      Self.FCOD_EMPRESA := Value;
    End;
    Procedure  TNFISCALitens.SetCOD_FILIAL       (Value : Integer);
    Begin
      Self.FCOD_FILIAL := Value;
    End;
    Procedure  TNFISCALitens.SetCOD_ITEM         (Value : Integer);
    Begin
      Self.FCOD_ITEM := Value;
    End;
    Procedure  TNFISCALitens.SetDESCR_COMPL      (Value : String);
    Begin
      Self.FDESCR_COMPL := Value;
    End;
    Procedure  TNFISCALitens.SetQTD              (Value : DOUBLE);
    Begin
      Self.FQTD := Value;
    End;
    Procedure  TNFISCALitens.SetUNID             (Value : DOUBLE);
    Begin
      Self.FUNID := Value;
    End;
    Procedure  TNFISCALitens.SetVL_ITEM          (Value : DOUBLE);
    Begin
      Self.FVL_ITEM := Value;
    End;
    Procedure  TNFISCALitens.SetVL_DESC          (Value : DOUBLE);
    Begin
      Self.FVL_DESC := Value;
    End;
    Procedure  TNFISCALitens.SetIND_MOV          (Value : DOUBLE);
    Begin
      Self.FIND_MOV := Value;
    End;
    Procedure  TNFISCALitens.SetCST_ICMS         (Value : DOUBLE);
    Begin
      Self.FCST_ICMS := Value;
    End;
    Procedure  TNFISCALitens.SetCFOP             (Value : String);
    Begin
      Self.FCFOP := Value;
    End;
    Procedure  TNFISCALitens.SetALIQ_ICMS        (Value : DOUBLE);
    Begin
      Self.FALIQ_ICMS := Value;
    End;
    Procedure  TNFISCALitens.SetVL_ICMS          (Value : DOUBLE);
    Begin
      Self.FVL_ICMS := Value;
    End;
    Procedure  TNFISCALitens.SetVL_BC_ICMS_ST    (Value : DOUBLE);
    Begin
      Self.FVL_BC_ICMS_ST := Value;
    End;
        Procedure  TNFISCALitens.SetVL_BC_ICMS    (Value : DOUBLE);
    Begin
      Self.FVL_BC_ICMS := Value;
    End;
    Procedure  TNFISCALitens.SetALIQ_ST          (Value : DOUBLE);
    Begin
      Self.FALIQ_ST := Value;
    End;
    Procedure  TNFISCALitens.SetVL_ICMS_ST       (Value : DOUBLE);
    Begin
      Self.FVL_ICMS_ST := Value;
    End;
    Procedure  TNFISCALitens.SetIND_APUR         (Value : DOUBLE);
    Begin
      Self.FIND_APUR := Value;
    End;
    Procedure  TNFISCALitens.SetCST_IPI          (Value : DOUBLE);
    Begin
      Self.FCST_IPI := Value;
    End;
    Procedure  TNFISCALitens.SetCOD_ENQ          (Value : DOUBLE);
    Begin
      Self.FCOD_ENQ := Value;
    End;
    Procedure  TNFISCALitens.SetVL_BC_IPI        (Value : DOUBLE);
    Begin
      Self.FVL_BC_IPI := Value;
    End;
    Procedure  TNFISCALitens.SetALIQ_IPI         (Value : DOUBLE);
    Begin
      Self.FALIQ_IPI := Value;
    End;
    Procedure  TNFISCALitens.SetVL_IPI           (Value : DOUBLE);
    Begin
      Self.FVL_IPI := Value;
    End;
    Procedure  TNFISCALitens.SetCST_PIS          (Value : DOUBLE);
    Begin
      Self.FCST_PIS := Value;
    End;
    Procedure  TNFISCALitens.SetVL_BC_PIS        (Value : DOUBLE);
    Begin
      Self.FVL_BC_PIS := Value;
    End;
    Procedure  TNFISCALitens.SetALIQ_PIS         (Value : DOUBLE);
    Begin
      Self.FALIQ_PIS := Value;
    End;
    Procedure  TNFISCALitens.SetQUANT_BC_PIS     (Value : DOUBLE);
    Begin
      Self.FQUANT_BC_PIS := Value;
    End;
    Procedure  TNFISCALitens.SetVL_PIS           (Value : DOUBLE);
    Begin
      Self.FVL_PIS := Value;
    End;
    Procedure  TNFISCALitens.SetCST_COFINS       (Value : DOUBLE);
    Begin
      Self.FCST_COFINS := Value;
    End;
    Procedure  TNFISCALitens.SetVL_BC_COFINS     (Value : DOUBLE);
    Begin
      Self.FVL_BC_COFINS := Value;
    End;
    Procedure  TNFISCALitens.SetALIQ_COFINS      (Value : DOUBLE);
    Begin
      Self.FALIQ_COFINS := Value;
    End;
    Procedure  TNFISCALitens.SetQUANT_BC_COFINS  (Value : DOUBLE);
    Begin
      Self.FQUANT_BC_COFINS := Value;
    End;
    Procedure  TNFISCALitens.SetVL_COFINS        (Value : DOUBLE);
    Begin
      Self.FVL_COFINS := Value;
    End;
    Procedure  TNFISCALitens.SetCOD_CTA          (Value : INTEGER);
    Begin
      Self.FCOD_CTA := Value;
    End;
end.

