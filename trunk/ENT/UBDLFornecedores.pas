unit UBDLFornecedores;



 interface

type
 TFornecedores = Class

    private
    FCOD_PART,FCOD_PAIS,FCOD_MUN,FNUM,Fcep,FCOD_EMPRESA,FCOD_FILIAL,FCODPREPR,FRAMO,FINDFORN :Integer;
    FNOME,FCNPJ,FCPF,FIE,FSUFRAMA,FENDE,FCIDADE,FINTERVALO_REPR,FCOMPL,FBAIRRO,FTPPESSOA,FSTATUS,FRAZAO,FTELCONTATO,FCONTATO,FUF,FCLIENTE,Ftransportador,FREPRESENTANTE,FFORNECEDOR,FCONT,FBANCO,FEMAIL,FEMAIL_NFE,FSITE,FTELEFONE,FFAX,FAGENCIA,fCONTA,FFANTASIA :sTRING;
    FDATA_CADASTRO:TDateTime;
    protected
    {
    COD_PART,COD_PAIS,COD_MUN,NUM,cep,INTERVALO_REPR
    NOME,CNPJ,CPF,IE,SUFRAMA,ENDE,COMPL,BAIRRO,CLIENTE,FORNECEDOR,CONT,BANCO,EMAIL,EMAIL_NFE,REPRESENTANTE,SITE,TELEFONE,FAX,AGENCIA
    }

      function  GetRAMO : Integer;
      function  GetINDFORN : Integer;
      function  GetCOD_REPRESENTANTE : Integer;
      function  GetCOD_EMPRESA     : Integer;
      function  GetCOD_FILIAL     : Integer;
      function  GetCOD_PART     : Integer;
      function  GetNOME         : String;
      function  GetCOD_PAIS     : Integer;
      function  GetStatus       : string;
      function  GetcIDADE       : string;
      function  GetRazao        : string;
      function  GetTelContato   : string;
      function  GetContato       : string;
      function  GetCNPJ         : string;
      function  GetCPF          : string;
      function  GetIE           : string;
      function  GetCOD_MUN      : Integer;
      function  GetSUFRAMA      : string;
      function  GetENDE         : string;
      function  GetNum          : Integer;
      function  GetCep          : Integer;
      function  GetTPPESSOA        : string;
      function  GetFantasia        : string;
      function  GetCOMPL        : string;
      function  GetRepresentante: string;
      function  Gettransportador : string;
      function  GetBAIRRO       : string;
      function  GetCLIENTE      : string;
      function  GetFORNECEDOR   : string;
      function  GetCONTA        : string;
      function  GetBANCO        : string;
      function  GetEMAIL_NFE    :String;
      function  GetINTERVALO_REPR :String;
      function  GetEMAIL          :String;
      function  GetSITE           :String;
      function  GetDATA_CADASTRO :TDateTime;
      function  GetTELEFONE      :String;
      function  GetFAX           :String;
      function  GetAGENCIA       :String;
      function  GetUF       :String;

       procedure  SetRAMO     (Value : Integer);
        procedure  SetINDFORN     (Value : Integer);
      procedure  SetCOD_REPRESENTANTE     (Value : Integer);
      procedure  SetCOD_EMPRESA     (Value : Integer);
      procedure  SetCOD_FILIAL     (Value : Integer);
      procedure  SetCOD_PART     (Value : Integer);
      procedure  SetNOME         (Value : string);
      procedure  SetCOD_PAIS     (Value : Integer);
      procedure  Setstatus         (Value : string);
      procedure  SetCIDADE         (Value : string);
      procedure  Setrazao         (Value : string);
      procedure  Settelcontato         (Value : string);
      procedure  Setcontato         (Value : string);
      procedure  SetCNPJ         (Value : string);
      procedure  SetCPF          (Value : string);
      procedure  SetTPPESSOA     (Value : string);
      procedure  SetIE           (Value : string);
      procedure  SetCOD_MUN      (Value : Integer);
      procedure  SetSUFRAMA      (Value : string);
      procedure  SetENDE         (Value : string);
      procedure  SetNum          (Value : Integer);
      procedure  SetCep          (Value : Integer);
      procedure  SetCOMPL        (Value : string);
      procedure  SetFantasia        (Value : string);
      procedure  SetBAIRRO       (Value : string);
      procedure  SetRepresentante(Value : string);
      procedure  Settransportador(Value : string);
      procedure  SetCLIENTE      (Value : string);
      procedure  SetFORNECEDOR   (Value : string);
      procedure  SetCONTA        (Value : string);
      procedure  SetBANCO        (Value : string);
      procedure  SetEMAIL        (Value : string);
      procedure  SetEMAIL_NFE    (Value : string);
      procedure  SetINTERVALO_REPR (Value : string);
      procedure  SetSITE           (Value : string);
      procedure  SetDATA_CADASTRO (Value : TDateTime);
      procedure  SetTELEFONE      (Value : string);
      procedure  SetFAX           (Value : string);
      procedure  SetAGENCIA       (Value : string);
      procedure  Setuf            (Value : string);

      public
        property  RAMO     : Integer read GetRAMO     write SetRAMO;
        property  INDFORN     : Integer read GetINDFORN     write SetINDFORN;
        property  COD_REPRESENTANTE     : Integer read GetCOD_REPRESENTANTE     write SetCOD_REPRESENTANTE;
        property  COD_EMPRESA     : Integer read GetCOD_EMPRESA     write SetCOD_EMPRESA;
        property  COD_FILIAL     : Integer read GetCOD_FILIAL     write SetCOD_FILIAL;
      property  COD_PART     : Integer read GetCOD_PART     write SetCOD_PART;
      property  NOME         : String read GetNOME     write SetNOME;
      property  CIDADE         : String read GetCIDADE     write SetCIDADE;
      property  COD_PAIS     : Integer read GetCOD_PAIS     write SetCOD_PAIS;
      property  status       : string read Getstatus     write Setstatus;
      property  razao        : string read Getrazao     write Setrazao ;
      property  telcontato   : string read Gettelcontato     write Settelcontato;
      property  contato      : string read Getcontato     write Setcontato;
      property  CNPJ         : string read GetCNPJ     write SetCNPJ;
      property  CPF          : string read GetCPF     write SetCPF;
      property  IE           : string read GetIE     write SetIE;
      property  COD_MUN      : Integer read GetCOD_MUN     write SetCOD_MUN;
      property  SUFRAMA      : string read GetSUFRAMA     write SetSUFRAMA;
      property  ENDE         : string read GetENDE     write SetENDE;
      property  Cep          : Integer read GetCep     write SetCep;
      property  Num          : Integer read GetNum     write SetNum;
      property  COMPL        : string read GetCOMPL     write SetCOMPL;
      property  Fantasia     : string read GetFANTASIA     write SetFANTASIA;
      property  BAIRRO       : string read GetBAIRRO     write SetBAIRRO;
      property  TPPESSOA       : string read GetTPPESSOA     write SetTPPESSOA;
      property  CLIENTE      : string read GetCLIENTE     write SetCLIENTE;
      property  FORNECEDOR   : string read GetFORNECEDOR     write SetFORNECEDOR;
      property  representante: string read Getrepresentante     write Setrepresentante;
      property  transportador: string read Gettransportador     write Settransportador;
      property  CONTA        : string read GetCONTA      write SetCONTA ;
      property  BANCO        : string read GetBANCO     write SetBANCO;
      property  EMAIL_NFE    :String read GetEMAIL_NFE     write SetEMAIL_NFE;
      property  INTERVALO_REPR :String read GetINTERVALO_REPR     write SetINTERVALO_REPR;
      property  EMAIL          :String read GetEMAIL     write SetEMAIL;
      property  SITE           :String read GetSITE     write SetSITE;
      property  DATA_CADASTRO :TDateTime read GetDATA_CADASTRO     write SetDATA_CADASTRO;
      property  TELEFONE      :String read GetTELEFONE     write SetTELEFONE;
      property  FAX           :String read GetFAX     write SetFAX;
      property  AGENCIA       :String read GetAGENCIA     write SetAGENCIA;
      property  UF            :String read GetUF     write SetUF;

  End;
implementation

uses SysUtils,DB, IBCustomDataSet, IBQuery;

var
   IBQuery1: TIBQuery;
{
function    TFornecedores.GravarFornecedores():String;
var SQL :String;
begin
  sql := 'INSERT INTO FORNECEDORES (CDFORNECEDOR, SITUACAO, STATUS, RAZAO, COM, SIGLA, FANTASIA, CGC, INCR, ENDERECO, BAIRRO, MUNICIPIO, UF, CEP, FONE, FONEFAX, FONECEL, CONTATO, EMAIL, SITE, OBS) VALUES ('+Fcdfornecedor+', '''+A+''', '''+A+''', '''+TESTE+''', '''+13+''', '''+TE+''', '''+TESTE+''', '''+1234567+''', '''+12346+''', '''+MARIA+''', '''+TESTE+''', '''+NULL+''', '''+NULL+''', '''+NULL+''', '''+NULL+''', '''+NULL+''', '''+NULL+''', '''+NULL+''', '''+NULL+''', '''+NULL+''', '''+NULL+''')';
end; }
      function    TFornecedores.GetRAMO     : Integer;
      Begin
      Result := Self.FRAMO;
      End;

      function    TFornecedores.GetINDFORN     : Integer;
      Begin
      Result := Self.FINDFORN;
      End;


      function    TFornecedores.GetCOD_REPRESENTANTE     : Integer;
      Begin
      Result := Self.FCODPREPR;
      End;

      function    TFornecedores.GetCOD_EMPRESA     : Integer;
      Begin
      Result := Self.FCOD_EMPRESA;
      End;
      function    TFornecedores.GetCOD_FILIAL     : Integer;
      Begin
      Result := Self.FCOD_FILIAL;
      End;
      function    TFornecedores.GetCOD_PART     : Integer;
      Begin
      Result := Self.FCOD_PART;
      End;
      function    TFornecedores.GetcEP     : Integer;
      Begin
      Result := Self.FCEP;
      End;
            function    TFornecedores.Getrazao        : String;
      Begin
      Result := Self.Frazao;
      End;
      function    TFornecedores.GetCIDADE        : String;
      Begin
      Result := Self.FCIDADE;
      End;
      function    TFornecedores.Getstatus        : String;
      Begin
      Result := Self.Fstatus;
      End;
      function    TFornecedores.GetTPPESSOA         : String;
      Begin
      Result := Self.FTPPESSOA;
      End;
            function    TFornecedores.Gettelcontato         : String;
      Begin
      Result := Self.Ftelcontato;
      End;
            function    TFornecedores.Getcontato         : String;
      Begin
      Result := Self.Fcontato;
      End;
      function    TFornecedores.Getuf        : String;
      Begin
      Result := Self.Fuf;
      End;

      function    TFornecedores.GetNOME         : String;
      Begin
      Result := Self.FNOME;
      End;
      function    TFornecedores.GetCOD_PAIS     : Integer;
      Begin
      Result := Self.FCOD_PAIS;
      End;
      function    TFornecedores.GetCNPJ         : string;
      Begin
      Result := Self.FCNPJ ;
      End;
      function    TFornecedores.GetCPF          : string;
      Begin
      Result := Self.FCPF;
      End;
      function    TFornecedores.GetIE           : string;
      Begin
      Result := Self.FIE;
      End;
      function    TFornecedores.GetCOD_MUN      : Integer;
      Begin
      Result := Self.FCOD_MUN;
      End;
      function    TFornecedores.GetSUFRAMA      : string;
      Begin
      Result := Self.FSUFRAMA;
      End;
      function    TFornecedores.GetENDE         : string;
      Begin
      Result := Self.FENDE;
      End;
      function    TFornecedores.GetNum          : Integer;
      Begin
      Result := Self.FNum;
      End;
      function    TFornecedores.GetFantasia        : string;
      Begin
      Result := Self.Ffantasia;
      End;
      function    TFornecedores.GetCOMPL        : string;
      Begin
      Result := Self.FCOMPL;
      End;
      function    TFornecedores.GetBAIRRO       : string;
      Begin
      Result := Self.FBAIRRO;
      End;
      function    TFornecedores.GetCLIENTE      : string;
      Begin
      Result := Self.FCLIENTE;
      End;
      function    TFornecedores.GetFORNECEDOR     : string;
      Begin
      Result := Self.FFORNECEDOR ;
      End;
      function    TFornecedores.Getrepresentante      : string;
      Begin
      Result := Self.Frepresentante;
      End;
      function    TFornecedores.Gettransportador   : string;
      Begin
      Result := Self.Ftransportador;
      End;
      function    TFornecedores.GetCONTA        : string;
      Begin
      Result := Self.FCONTA;
      End;
      function    TFornecedores.GetBANCO        : string;
      Begin
      Result := Self.FBANCO;
      End;
      function    TFornecedores.GetEMAIL        : string;
      Begin
      Result := Self.FEMAIL;
      End;
      function    TFornecedores.GetEMAIL_NFE    :String;
      Begin
      Result := Self.FEMAIL_NFE;
      End;
      function    TFornecedores.GetINTERVALO_REPR :String;
      Begin
      Result := Self.FINTERVALO_REPR;
      End;
      function    TFornecedores.GetSITE           :String;
      Begin
      Result := Self.FSITE;
      End;

      function    TFornecedores.GetDATA_CADASTRO :TDateTime;
      Begin
      Result := Self.FDATA_CADASTRO;
      End;
      function    TFornecedores.GetTELEFONE      :String;
      Begin
      Result := Self.FTELEFONE;
      End;
      function    TFornecedores.GetFAX           :String;
      Begin
      Result := Self.FFAX;
      End;

      function    TFornecedores.GetAGENCIA       :String;
      Begin
      Result := Self.FAGENCIA;
      End;
      procedure  TFornecedores.SetRAMO     (Value : Integer);
      Begin
        Self.FRAMO := Value;
      End;

      procedure  TFornecedores.SetINDFORN     (Value : Integer);
      Begin
        Self.FINDFORN := Value;
      End;

      procedure  TFornecedores.SetCOD_REPRESENTANTE     (Value : Integer);
      Begin
        Self.FCODPREPR := Value;
      End;
      procedure  TFornecedores.SetCOD_FILIAL     (Value : Integer);
      Begin
        Self.FCOD_FILIAL := Value;
      End;
      procedure  TFornecedores.SetCOD_EMPRESA     (Value : Integer);
      Begin
        Self.FCOD_EMPRESA := Value;
      End;
      procedure  TFornecedores.SetCEP     (Value : Integer);
      Begin
        Self.FCEP := Value;
      End;
      procedure  TFornecedores.SetTPPESSOA     (Value : String);
      Begin
        Self.FTPPESSOA := Value;
      End;
            procedure  TFornecedores.SetCIDADE    (Value : String);
      Begin
        Self.FCIDADE := Value;
      End;
      procedure  TFornecedores.SetCOD_PART     (Value : Integer);
      Begin
        Self.FCOD_PART := Value;
      End;
      procedure  TFornecedores.SetUf         (Value : string);
      Begin
        Self.Fuf := Value;
      End;
      procedure  TFornecedores.Setstatus         (Value : string);
      Begin
        Self.Fstatus := Value;
      End;
            procedure  TFornecedores.Setrazao         (Value : string);
      Begin
        Self.Frazao := Value;
      End;
            procedure  TFornecedores.Settelcontato         (Value : string);
      Begin
        Self.Ftelcontato := Value;
      End;
            procedure  TFornecedores.Setcontato         (Value : string);
      Begin
        Self.Fcontato := Value;
      End;


      procedure  TFornecedores.SetNOME         (Value : string);
      Begin
        Self.FNOME := Value;
      End;
      procedure  TFornecedores.SetFantasia         (Value : string);
      Begin
        Self.FFantasia := Value;
      End;
      procedure  TFornecedores.SetCOD_PAIS     (Value : Integer);
      Begin
        Self.FCOD_PAIS := Value;
      End;
      procedure  TFornecedores.SetCNPJ         (Value : string);
      Begin
        Self.FCNPJ := Value;
      End;
      procedure  TFornecedores.SetCPF          (Value : string);
      Begin
        Self.FCPF := Value;
      End;
      procedure  TFornecedores.SetIE           (Value : string);
      Begin
        Self.FIE := Value;
      End;
      procedure  TFornecedores.SetCOD_MUN      (Value : Integer);
      Begin
        Self.FCOD_MUN := Value;
      End;
      procedure  TFornecedores.SetSUFRAMA      (Value : string);
      Begin
        Self.FSUFRAMA := Value;
      End;
      procedure  TFornecedores.SetENDE         (Value : string);
      Begin
        Self.FENDE := Value;
      End;
      procedure  TFornecedores.SetNum          (Value : Integer);
      Begin
        Self.FNum := Value;
      End;
      procedure  TFornecedores.SetCOMPL        (Value : string);
      Begin
        Self.FCOMPL := Value;
      End;
      procedure  TFornecedores.SetBAIRRO       (Value : string);
      Begin
        Self.FBAIRRO := Value;
      End;
      procedure  TFornecedores.SetCLIENTE      (Value : string);
      Begin
        Self.FCLIENTE := Value;
      End;
      procedure  TFornecedores.Setrepresentante      (Value : string);
      Begin
        Self.Frepresentante := Value;
      End;
      procedure  TFornecedores.Settransportador      (Value : string);
      Begin
        Self.Ftransportador := Value;
      End;
      procedure  TFornecedores.SetFORNECEDOR   (Value : string);
      Begin
        Self.FFORNECEDOR := Value;
      End;
      procedure  TFornecedores.SetCONTA        (Value : string);
      Begin
        Self.FCONTA := Value;
      End;
      procedure  TFornecedores.SetBANCO        (Value : string);
      Begin
        Self.FBANCO := Value;
      End;
      procedure  TFornecedores.SetEMAIL        (Value : string);
      Begin
        Self.FEMAIL := Value;
      End;
      procedure  TFornecedores.SetEMAIL_NFE    (Value : string);
      Begin
        Self.FEMAIL_NFE := Value;
      End;
      procedure  TFornecedores.SetINTERVALO_REPR (Value : string);
      Begin
        Self.FINTERVALO_REPR:= Value;
      End;
      procedure  TFornecedores.SetSITE           (Value : string);
      Begin
        Self.FSITE := Value;
      End;
      procedure  TFornecedores.SetDATA_CADASTRO (Value : TDateTime);
      Begin
        Self.FDATA_CADASTRO := Value;
      End;
      procedure  TFornecedores.SetTELEFONE      (Value : string);
      Begin
        Self.FTELEFONE := Value;
      End;
      procedure  TFornecedores.SetFAX           (Value : string);
      Begin
        Self.FFAX := Value;
      End;
      procedure  TFornecedores.SetAGENCIA       (Value : string);
      Begin
        Self.FAGENCIA := Value;
      End;



end.
