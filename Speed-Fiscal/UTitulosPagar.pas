unit UTitulosPagar;

interface

type
 TTiTuLOsPagar = Class
    private
    FDCNUMERO,FPARCELA,FFORNECEDOR,FTIPO_TITULO ,FCOD_EMPRESA,FCOD_FILIAL :Integer;
    FDCSERIE,FDCORDEM,FDCTIPO,FSTATUS,FTPSITUACAO,FOBS :String;
    FDesconto,FVLPARCELA :Double;
    FDTVENCIMENTO,FDTLANCAMENTO,FDATAPAGAMENTO  :TdateTime;


    protected
     function  GetDCNUMERO       :Integer;
     function  GetDCSERIE        :String;
     function  GetDCORDEM        :String;
     function  GetDCTIPO         :String;
     function  GetPARCELA        :Integer;
     function  GetDTVENCIMENTO   :TdateTime;
     function  GetDTLANCAMENTO   :TdateTime;
     function  GetSTATUS         :String;
     function  GetTPSITUACAO     :String;
     function  GetVLPARCELA      :DOUBLE;
     function  GetFORNECEDOR     :integer;
     function  GetOBS            :String;
     function  GetDATAPAGAMENTO  :TdateTime;
     function  GetTIPO_TITULO    :Integer;
     function  GetCOD_EMPRESA    :Integer;
     function  GetCOD_FILIAL     :Integer;
     function  GetDesconto       :DOUBLE;

    Procedure  SetDCNUMERO       (Value : INTEGER);
    Procedure  SetDCSERIE        (Value : String);
    Procedure  SetDCORDEM        (Value : String);
    Procedure  SetDCTIPO         (Value : String);
    Procedure  SetPARCELA        (Value : Integer);
    Procedure  SetDTVENCIMENTO   (Value : TDateTime);
    Procedure  SetDTLANCAMENTO   (Value : TDateTime);
    Procedure  SetSTATUS         (Value : String);
    Procedure  SetTPSITUACAO     (Value : String);
    Procedure  SetVLPARCELA      (Value : DOUBLE);
    Procedure  SetFORNECEDOR     (Value : Integer);
    Procedure  SetOBS            (Value : String);
    Procedure  SetDATAPAGAMENTO  (Value : TDateTime);
    Procedure  SetTIPO_TITULO    (Value : INTEGER);
    Procedure  SetCOD_EMPRESA    (Value : INTEGER);
    Procedure  SetCOD_FILIAL     (Value : INTEGER);
    Procedure  SetDesconto       (Value : DOUBLE);

    public
      property  DCNUMERO        : Integer read GetDCNUMERO     write SetDCNUMERO;
      property  TIPO_TITULO     : Integer read GetTIPO_TITULO            write SetTIPO_TITULO;
      property  COD_EMPRESA     : Integer read GetCOD_EMPRESA         write SetCOD_EMPRESA;
      property  COD_FILIAL      : Integer read GetCOD_FILIAL          write SetCOD_FILIAL;
      property  Fornecedor      : Integer read GetFornecedor          write SetFornecedor;
      property  Parcela         : Integer read GetParcela          write SetParcela;
      property  DCSERIE         : string read GetDCSERIE    write SetDCSERIE;
      property  DCORDEM         : string read GetDCORDEM        write SetDCORDEM;
      property  DCTIPO          : String read GetDCTIPO     write SetDCTIPO;
      property  DTVENCIMENTO    : TdateTime read GetDTVENCIMENTO        write SetDTVENCIMENTO;
      property  DTLANCAMENTO    : TdateTime read GetDTLANCAMENTO          write SetDTLANCAMENTO;
      property  DATAPAGAMENTO   : TdateTime read GetDATAPAGAMENTO           write SetDATAPAGAMENTO;
      property  VLPARCELA       : DOUBLE read GetVLPARCELA        write SetVLPARCELA;
      property  Desconto        : DOUBLE read GetDesconto              write SetDesconto;

   End;
implementation

uses SysUtils;

     function  TTiTuLOsPagar.GetDCNUMERO       :Integer;
     Begin
      Result := Self.FDCNUMERO  ;
     End;
     function  TTiTuLOsPagar.GetDCSERIE        :String;
     Begin
      Result := Self.FDCSERIE   ;
     End;
     function  TTiTuLOsPagar.GetDCORDEM        :String;
     Begin
      Result := Self.FDCORDEM  ;
     End;
     function  TTiTuLOsPagar.GetDCTIPO         :String;
     Begin
      Result := Self.FDCTIPO  ;
     End;
     function  TTiTuLOsPagar.GetPARCELA        :Integer;
     Begin
      Result := Self.FPARCELA  ;
     End;
     function  TTiTuLOsPagar.GetDTVENCIMENTO   :TdateTime;
     Begin
      Result := Self.FDTVENCIMENTO  ;
     End;
     function  TTiTuLOsPagar.GetDTLANCAMENTO   :TdateTime;
     Begin
      Result := Self.FDTLANCAMENTO  ;
     End;
     function  TTiTuLOsPagar.GetSTATUS         :String;
     Begin
      Result := Self.FSTATUS  ;
     End;
     function  TTiTuLOsPagar.GetTPSITUACAO     :String;
     Begin
      Result := Self.FTPSITUACAO  ;
     End;
     function  TTiTuLOsPagar.GetVLPARCELA      :DOUBLE;
     Begin
      Result := Self.FVLPARCELA   ;
     End;
     function  TTiTuLOsPagar.GetFORNECEDOR     :integer;
     Begin
      Result := Self.FFORNECEDOR  ;
     End;
     function  TTiTuLOsPagar.GetOBS            :String;
     Begin
      Result := Self.FOBS  ;
     End;
     function  TTiTuLOsPagar.GetDATAPAGAMENTO  :TdateTime;
     Begin
      Result := Self.FDATAPAGAMENTO  ;
     End;
     function  TTiTuLOsPagar.GetTIPO_TITULO    :Integer;
     Begin
      Result := Self.FTIPO_TITULO  ;
     End;
     function  TTiTuLOsPagar.GetCOD_EMPRESA    :Integer;
     Begin
      Result := Self.FCOD_EMPRESA  ;
     End;
     function  TTiTuLOsPagar.GetCOD_FILIAL     :Integer;
     Begin
      Result := Self.FCOD_FILIAL  ;
     End;
     function  TTiTuLOsPagar.GetDesconto       :DOUBLE;
     Begin
      Result := Self.FDesconto  ;
     End;

    Procedure  TTiTuLOsPagar.SetDCNUMERO       (Value : INTEGER);
    Begin
      Self.FDCNUMERO := Value;
    End;
    Procedure  TTiTuLOsPagar.SetDCSERIE        (Value : String);
    Begin
      Self.FDCSERIE := Value;
    End;
    Procedure  TTiTuLOsPagar.SetDCORDEM        (Value : String);
    Begin
      Self.FDCORDEM := Value;
    End;
    Procedure  TTiTuLOsPagar.SetDCTIPO         (Value : String);
    Begin
      Self.FDCTIPO := Value;
    End;
    Procedure  TTiTuLOsPagar.SetPARCELA        (Value : Integer);
    Begin
      Self.FPARCELA := Value;
    End;
    Procedure  TTiTuLOsPagar.SetDTVENCIMENTO   (Value : TDateTime);
    Begin
      Self.FDTVENCIMENTO := Value;
    End;
    Procedure  TTiTuLOsPagar.SetDTLANCAMENTO   (Value : TDateTime);
    Begin
      Self.FDTLANCAMENTO := Value;
    End;
    Procedure  TTiTuLOsPagar.SetSTATUS         (Value : String);
    Begin
      Self.FSTATUS := Value;
    End;
    Procedure  TTiTuLOsPagar.SetTPSITUACAO     (Value : String);
    Begin
      Self.FTPSITUACAO := Value;
    End;
    Procedure  TTiTuLOsPagar.SetVLPARCELA      (Value : DOUBLE);
    Begin
      Self.FVLPARCELA := Value;
    End;
    Procedure  TTiTuLOsPagar.SetFORNECEDOR     (Value : Integer);
    Begin
      Self.FFORNECEDOR := Value;
    End;
    Procedure  TTiTuLOsPagar.SetOBS            (Value : String);
    Begin
      Self.FOBS := Value;
    End;
    Procedure  TTiTuLOsPagar.SetDATAPAGAMENTO  (Value : TDateTime);
    Begin
      Self.FDATAPAGAMENTO := Value;
    End;
    Procedure  TTiTuLOsPagar.SetTIPO_TITULO    (Value : INTEGER);
    Begin
      Self.FTIPO_TITULO := Value;
    End;
    Procedure  TTiTuLOsPagar.SetCOD_EMPRESA    (Value : INTEGER);
    Begin
      Self.FCOD_EMPRESA := Value;
    End;
    Procedure  TTiTuLOsPagar.SetCOD_FILIAL     (Value : INTEGER);
    Begin
      Self.FCOD_FILIAL := Value;
    End;
    Procedure  TTiTuLOsPagar.SetDesconto       (Value : DOUBLE);
    Begin
      Self.FDesconto := Value;
    End;
end.
