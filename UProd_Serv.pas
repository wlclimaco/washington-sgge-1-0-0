unit UProd_Serv;

interface

type
 TProduto = Class
    private
    FCOD_PRODUTO,FEMPRESA,FFILIAL,FUNID,FCOD_TIPO_ITEM ,FCOD_PROD_ESP,FGRUPO,FSUBGRUPO,FCD_TRIBUTACAO,FORIGEM,FOPERACAO,FCODSERV,FDERIVACAO : Integer;
    FDESCR_PRODUTO,FTAMANHO,FCOD_ANT_ITEM,FTIPO_ITEM,FCOD_NCM,FCOD_GEN,FEX_IPI,FCOD_LST,FINTEIRO,FREF,FSTATUS,FCompNCM :String;
    FALIQ_ICMS,fMVA,FRED_BASE_CALC,FALIQ_CONFINS,FALIQ_PIS,FVLAQUIS,FMARGEMPR,FPRECOVENDA,FESTOQUE,FIPI:Double;
    protected
    function  GetCOD_PRODUTO    :Integer;
    function  GetCODSERV        :INTEGER;
    function  GetEMPRESA        :INTEGER;
    function  GetFILIAL         :INTEGER;
    function  GetUNID           :INTEGER;
    function  GetCOD_TIPO_ITEM  :INTEGER;
    function  GetCOD_PROD_ESP   :INTEGER;
    function  GetDESCR_PRODUTO  :String;
    function  GetTAMANHO      :String;
    function  GetCOD_ANT_ITEM   :String;
    function  GetTIPO_ITEM      :String;
    function  GetCOD_NCM        :String;
    function  GetEX_IPI         :String;
    function  GetCOD_GEN        :String;
    function  GetCOD_LST        :String;
    function  GetALIQ_ICMS      :DOUBLE;
    function  GetMVA            :DOUBLE;
    function  GetGRUPO          :INTEGER;
    function  GetSUBGRUPO       :INTEGER;
    function  GetINTEIRO        :String;
    function  GetCD_TRIBUTACAO  :INTEGER;
    function  GetORIGEM         :INTEGER;
    function  GetOPERACAO       :INTEGER;
    function  GetRED_BASE_CALC  :DOUBLE;
    function  GetALIQ_CONFINS   :DOUBLE;
    function  GetALIQ_PIS       :DOUBLE;
    function  GetVLAQUIS        :DOUBLE;
    function  GetMARGEMPR       :DOUBLE;
    function  GetESTOQUE        :Double;
    function  GetPRECOVENDA     :DOUBLE;
    function  GetIPI            :DOUBLE;
    function  GetREF            :String;
    function  GetSTATUS         :String;
    function  GetDERIVACAO      :integer;
    function  GetCompNCM         :String;
    Procedure  SetCOD_PRODUTO    (Value : INTEGER);
    Procedure  SetEMPRESA        (Value : INTEGER);
    Procedure  SetFILIAL         (Value : INTEGER);
    Procedure  SetUNID           (Value : INTEGER);
    Procedure  SetCODSERV        (Value : INTEGER);
    Procedure  SetCOD_TIPO_ITEM  (Value : INTEGER);
    Procedure  SetCOD_PROD_ESP   (Value : INTEGER);
    Procedure  SetDESCR_PRODUTO  (Value : String);
    Procedure  SetTAMANHO      (Value : String);
    Procedure  SetCOD_ANT_ITEM   (Value : String);
    Procedure  SetTIPO_ITEM      (Value : String);
    Procedure  SetCOD_NCM        (Value : String);
    Procedure  SetEX_IPI         (Value : String);
    Procedure  SetCOD_GEN        (Value : String);
    Procedure  SetCOD_LST        (Value : String);
    Procedure  SetALIQ_ICMS      (Value : DOUBLE);
    Procedure  SetMVA            (Value : DOUBLE);
    Procedure  SetIPI            (Value : DOUBLE);
    Procedure  SetGRUPO          (Value : INTEGER);
    Procedure  SetSUBGRUPO       (Value : INTEGER);
    Procedure  SetINTEIRO        (Value : String);
    Procedure  SetCD_TRIBUTACAO  (Value : INTEGER);
    Procedure  SetORIGEM         (Value : INTEGER);
    Procedure  SetOPERACAO       (Value : INTEGER);
    Procedure  SetRED_BASE_CALC  (Value : DOUBLE);
    Procedure  SetALIQ_CONFINS   (Value : DOUBLE);
    Procedure  SetALIQ_PIS       (Value : DOUBLE);
    Procedure  SetVLAQUIS        (Value : DOUBLE);
    Procedure  SetMARGEMPR       (Value : DOUBLE);
    Procedure  SetESTOQUE        (Value : Double);
    Procedure  SetPRECOVENDA     (Value : DOUBLE);
    Procedure  SetREF            (Value : String);
    Procedure  SetSTATUS         (Value : String);
    Procedure  SetDERIVACAO      (Value : integer);
    Procedure  SetCompNCM         (Value : String);
    public
      property  COD_PRODUTO       : Integer read GetCOD_PRODUTO     write SetCOD_PRODUTO;
      property  UNID              : Integer read GetUNID            write SetUNID;
      property  EMPRESA           : Integer read GetEMPRESA         write SetEMPRESA;
      property  FILIAL            : Integer read GetFILIAL          write SetFILIAL;
      property  COD_TIPO_ITEM     : Integer read GetCOD_TIPO_ITEM   write SetCOD_TIPO_ITEM;
      property  CODSERV           : Integer read GetCODSERV         write SetCODSERV;
      property  COD_PROD_ESP      : Integer read GetCOD_PROD_ESP    write SetCOD_PROD_ESP;
      property  DESCR_PRODUTO     : string read GetDESCR_PRODUTO    write SetDESCR_PRODUTO;
      property  TAMANHO         : string read GetTAMANHO        write SetTAMANHO;
      property  COD_ANT_ITEM      : String read GetCOD_ANT_ITEM     write SetCOD_ANT_ITEM;
      property  TIPO_ITEM         : String read GetTIPO_ITEM        write SetTIPO_ITEM;
      property  COD_NCM           : String read GetCOD_NCM          write SetCOD_NCM;
      property  EX_IPI            : String read GetEX_IPI           write SetEX_IPI;
      property  COD_GEN           : String read GetCOD_GEN          write SetCOD_GEN;
      property  COD_LST           : string read GetCOD_LST          write SetCOD_LST;
      property  ALIQ_ICMS         : DOUBLE read GetALIQ_ICMS        write SetALIQ_ICMS;
      property  IPI               : DOUBLE read GetIPI              write SetIPI;
      property  MVA               : DOUBLE read GetMVA              write SetMVA;
      property  GRUPO             : Integer read GetGRUPO           write SetGRUPO;
      property  SUBGRUPO          : Integer read GetSUBGRUPO        write SetSUBGRUPO;
      property  INTEIRO           : string read GetINTEIRO          write SetINTEIRO;
      property  CD_TRIBUTACAO     : Integer read GetCD_TRIBUTACAO   write SetCD_TRIBUTACAO;
      property  ORIGEM            : Integer read GetORIGEM          write SetORIGEM;
      property  OPERACAO          : Integer read GetOPERACAO        write SetOPERACAO;
      property  RED_BASE_CALC     : DOUBLE read GetRED_BASE_CALC    write SetRED_BASE_CALC;
      property  ALIQ_CONFINS      : DOUBLE read GetALIQ_CONFINS     write SetALIQ_CONFINS;
      property  ALIQ_PIS          : DOUBLE read GetALIQ_PIS         write SetALIQ_PIS;
      property  VLAQUIS           : DOUBLE read GetVLAQUIS          write SetVLAQUIS;
      property  MARGEMPR          : DOUBLE read GetMARGEMPR         write SetMARGEMPR;
      property  ESTOQUE           : DOUBLE read GetESTOQUE          write SetESTOQUE;
      property  PRECOVENDA        : DOUBLE read GetPRECOVENDA       write SetPRECOVENDA;
      property  REF               : String read GetREF              write SetREF;
      property  STATUS            : String read GetSTATUS           write SetSTATUS;
      property  DERIVACAO         : integer read GetDERIVACAO        write SetDERIVACAO;
      property  CompNCM            : String read GetCompNCM           write SetCompNCM;
  End;
implementation

uses SysUtils;
    function      TProduto.GetCODSERV     :INTEGER;
    Begin
      Result := Self.FCODSERV  ;
    End;
    function      TProduto.GetEMPRESA     :INTEGER;
    Begin
      Result := Self.FEMPRESA  ;
    End;
    function      TProduto.GetFILIAL     :INTEGER;
    Begin
      Result := Self.FFILIAL  ;
    End;
    function      TProduto.GetCOD_PRODUTO    :INTEGER;
    Begin
      Result := Self.FCOD_PRODUTO ;
    End;
    function      TProduto.GetUNID           :INTEGER;
    Begin
      Result := Self.FUNID;
    End;
    function      TProduto.GetCOD_TIPO_ITEM  :INTEGER;
    Begin
      Result := Self.FCOD_TIPO_ITEM;
    End;
    function      TProduto.GetCOD_PROD_ESP   :INTEGER;
    Begin
      Result := Self.FCOD_PROD_ESP;
    End;
    function      TProduto.GetDESCR_PRODUTO  :String;
    Begin
      Result := Self.FDESCR_PRODUTO;
    End;
    function      TProduto.GetTAMANHO      :String;
    Begin
      Result := Self.FTAMANHO;
    End;
    function      TProduto.GetCOD_ANT_ITEM   :String;
    Begin
      Result := Self.FCOD_ANT_ITEM;
    End;
    function      TProduto.GetTIPO_ITEM      :String;
    Begin
      Result := Self.FTIPO_ITEM;
    End;
    function      TProduto.GetCOD_NCM        :String;
    Begin
      Result := Self.FCOD_NCM;
    End;
    function      TProduto.GetEX_IPI         :String;
    Begin
      Result := Self.FEX_IPI;
    End;
    function      TProduto.GetCOD_GEN        :String;
    Begin
      Result := Self.FCOD_GEN;
    End;
    function      TProduto.GetCOD_LST        :String;
    Begin
      Result := Self.FCOD_LST;
    End;
    function      TProduto.GetALIQ_ICMS      :DOUBLE;
    Begin
      Result := Self.FALIQ_ICMS;
    End;
    function      TProduto.GetIPI     :DOUBLE;
    Begin
      Result := Self.FIPI;
    End;
    function      TProduto.GetMVA            :DOUBLE ;
    Begin
      Result := Self.FMVA;
    End;
    function      TProduto.GetGRUPO          :INTEGER;
    Begin
      Result := Self.FGRUPO;
    End;
    function      TProduto.GetSUBGRUPO       :INTEGER;
    Begin
      Result := Self.FSUBGRUPO;
    End;
    function      TProduto.GetINTEIRO        :String;
    Begin
      Result := Self.FINTEIRO ;
    End;
    function      TProduto.GetCD_TRIBUTACAO  :INTEGER;
    Begin
      Result := Self.FCD_TRIBUTACAO;
    End;
    function      TProduto.GetORIGEM         :INTEGER;
    Begin
      Result := Self.FORIGEM;
    End;
    function      TProduto.GetOPERACAO       :INTEGER;
    Begin
      Result := Self.FOPERACAO;
    End;
    function      TProduto.GetRED_BASE_CALC  :DOUBLE;
    Begin
      Result := Self.FRED_BASE_CALC;
    End;
    function      TProduto.GetALIQ_CONFINS   :DOUBLE;
    Begin
      Result := Self.FALIQ_CONFINS;
    End;
    function      TProduto.GetALIQ_PIS       :DOUBLE;
    Begin
      Result := Self.FALIQ_PIS;
    End;
    function      TProduto.GetVLAQUIS        :DOUBLE;
    Begin
      Result := Self.FVLAQUIS;
    End;
    function      TProduto.GetMARGEMPR       :DOUBLE;
    Begin
      Result := Self.FMARGEMPR;
    End;
    function      TProduto.GetESTOQUE        :Double;
    Begin
      Result := Self.FESTOQUE;
    End;
    function      TProduto.GetPRECOVENDA     :DOUBLE;
    Begin
      Result := Self.FPRECOVENDA;
    End;
    function      TProduto.GetREF            :String;
    Begin
      Result := Self.FREF;
    End;
    function      TProduto.GetSTATUS         :String;
    Begin
      Result := Self.FSTATUS;
    End;
    function      TProduto.GetDERIVACAO      :integer;
    Begin
      Result := Self.FDERIVACAO;
    End;
    function      TProduto.GetCompNCM      :String;
    Begin
      Result := Self.FCompNCM;
    End;
//*******************************************************************
    procedure   TProduto.SetCOD_PRODUTO    (Value : INTEGER);
    Begin
      Self.FCOD_PRODUTO  := Value;
    End;
    procedure   TProduto.SetEMPRESA    (Value : INTEGER);
    Begin
      Self.FEMPRESA  := Value;
    End;
    procedure   TProduto.SetFILIAL    (Value : INTEGER);
    Begin
      Self.FFILIAL  := Value;
    End;
    procedure   TProduto.SetCODSERV           (Value : INTEGER);
    Begin
      Self.FCODSERV := Value;
    End;
    procedure   TProduto.SetUNID           (Value : INTEGER);
    Begin
      Self.FUNID := Value;
    End;
    procedure   TProduto.SetCOD_TIPO_ITEM  (Value : INTEGER);
    Begin
      Self.FCOD_TIPO_ITEM := Value;
    End;
    procedure   TProduto.SetCOD_PROD_ESP   (Value : INTEGER);
    Begin
      Self.FCOD_PROD_ESP := Value;
    End;
    procedure   TProduto.SetDESCR_PRODUTO  (Value : String);
    Begin
      Self.FDESCR_PRODUTO := Value;
    End;
    procedure   TProduto.SetTAMANHO      (Value : String);
    Begin
      Self.FTAMANHO := Value;
    End;
    procedure   TProduto.SetCOD_ANT_ITEM   (Value : String);
    Begin
      Self.FCOD_ANT_ITEM := Value;
    End;
    procedure   TProduto.SetTIPO_ITEM      (Value : String);
    Begin
      Self.FTIPO_ITEM := Value;
    End;
    procedure   TProduto.SetCOD_NCM        (Value : String);
    Begin
      Self.FCOD_NCM := Value;
    End;
    procedure   TProduto.SetEX_IPI         (Value : String);
    Begin
      Self.FEX_IPI := Value;
    End;
    procedure   TProduto.SetCOD_GEN        (Value : String);
    Begin
      Self.FCOD_GEN := Value;
    End;
    procedure   TProduto.SetCOD_LST        (Value : String);
    Begin
      Self.FCOD_LST := Value;
    End;
    procedure   TProduto.SetALIQ_ICMS      (Value : DOUBLE);
    Begin
      Self.FALIQ_ICMS := Value;
    End;
        procedure   TProduto.SetIPI      (Value : DOUBLE);
    Begin
      Self.FIPI := Value;
    End;
    procedure   TProduto.SetMVA            (Value : DOUBLE);
    Begin
      Self.FMVA := Value;
    End;
    procedure   TProduto.SetGRUPO          (Value : INTEGER);
    Begin
      Self.FGRUPO := Value;
    End;
    procedure   TProduto.SetSUBGRUPO       (Value : INTEGER);
    Begin
      Self.FSUBGRUPO := Value;
    End;
    procedure   TProduto.SetINTEIRO        (Value : String);
    Begin
      Self.FINTEIRO  := Value;
    End;
    procedure   TProduto.SetCD_TRIBUTACAO  (Value : INTEGER);
    Begin
      Self.FCD_TRIBUTACAO := Value;
    End;
    procedure   TProduto.SetORIGEM         (Value : INTEGER);
    Begin
      Self.FORIGEM := Value;
    End;
    procedure   TProduto.SetOPERACAO       (Value : INTEGER);
    Begin
      Self.FOPERACAO := Value;
    End;
    procedure   TProduto.SetRED_BASE_CALC  (Value : DOUBLE);
    Begin
      Self.FRED_BASE_CALC := Value;
    End;
    procedure   TProduto.SetALIQ_CONFINS   (Value : DOUBLE);
    Begin
      Self.FALIQ_CONFINS := Value;
    End;
    procedure   TProduto.SetALIQ_PIS       (Value : DOUBLE);
    Begin
      Self.FALIQ_PIS := Value;
    End;
    procedure   TProduto.SetVLAQUIS        (Value : DOUBLE);
    Begin
      Self.FVLAQUIS := Value;
    End;
    procedure   TProduto.SetMARGEMPR       (Value : DOUBLE);
    Begin
      Self.FMARGEMPR := Value;
    End;
    procedure   TProduto.SetESTOQUE        (Value : Double);
    Begin
      Self.FESTOQUE := Value;
    End;
    procedure   TProduto.SetPRECOVENDA     (Value : DOUBLE);
    Begin
      Self.FPRECOVENDA := Value;
    End;
    procedure   TProduto.SetREF            (Value : String);
    Begin
      Self.FREF := Value;
    End;
    procedure   TProduto.SetSTATUS         (Value : String);
    Begin
      Self.FSTATUS := Value;
    End;
    procedure   TProduto.SetDERIVACAO      (Value : integer);
    Begin
      Self.FDERIVACAO := Value;
    End;
    procedure   TProduto.SetCompNCM      (Value : String);
    Begin
      Self.FCompNCM := Value;
    End;

end.
