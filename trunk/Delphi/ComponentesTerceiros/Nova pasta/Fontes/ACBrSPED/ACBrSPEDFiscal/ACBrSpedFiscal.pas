{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009   Isaque Pinheiro                      }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }
{                                                                              }
{  Esta biblioteca � software livre; voc� pode redistribu�-la e/ou modific�-la }
{ sob os termos da Licen�a P�blica Geral Menor do GNU conforme publicada pela  }
{ Free Software Foundation; tanto a vers�o 2.1 da Licen�a, ou (a seu crit�rio) }
{ qualquer vers�o posterior.                                                   }
{                                                                              }
{  Esta biblioteca � distribu�da na expectativa de que seja �til, por�m, SEM   }
{ NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU      }
{ ADEQUA��O A UMA FINALIDADE ESPEC�FICA. Consulte a Licen�a P�blica Geral Menor}
{ do GNU para mais detalhes. (Arquivo LICEN�A.TXT ou LICENSE.TXT)              }
{                                                                              }
{  Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral Menor do GNU junto}
{ com esta biblioteca; se n�o, escreva para a Free Software Foundation, Inc.,  }
{ no endere�o 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          }
{ Voc� tamb�m pode obter uma copia da licen�a em:                              }
{ http://www.opensource.org/licenses/lgpl-license.php                          }
{                                                                              }
{ Daniel Sim�es de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Pra�a Anita Costa, 34 - Tatu� - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 10/04/2009: Isaque Pinheiro
|*  - Cria��o e distribui��o da Primeira Versao
|* 15/03/2010: Alessandro Yamasaki
|*  - Adicionado o REGISTRO 0500: PLANO DE CONTAS CONT�BEIS
*******************************************************************************}

{$I ACBr.inc}

unit ACBrSpedFiscal;

interface

uses
  SysUtils, Math, Classes,
{$IFNDEF Framework}
  {$IFDEF FPC}
    LResources,
  {$ENDIF}
{$ENDIF}
  DateUtils, ACBrSped, ACBrTXTClass, ACBrEFDBlocos,
  ACBrEFDBloco_0_Class, ACBrEFDBloco_1_Class, ACBrEFDBloco_9_Class,
  ACBrEFDBloco_C_Class, ACBrEFDBloco_D_Class, ACBrEFDBloco_E_Class,
  ACBrEFDBloco_G_Class, ACBrEFDBloco_H_Class,
  ACBrEFDBloco_0_Events, ACBrEFDBloco_C_Events, ACBrEFDBloco_D_Events,
  ACBrEFDBloco_E_Events;

const
  CACBrSpedFiscal_Versao = '1.01';

type
  /// ACBrSpedFiscal - Sitema Publico de Escritura��o Digital Fiscal

  { TACBrSPEDFiscal }

  TACBrSPEDFiscal = class(TComponent)
  private
    FACBrTXT: TACBrTXTClass;
    FArquivo: ansistring;
    FInicializado : boolean;
    FOnError: TErrorEvent;

    FEventsBloco_0: TEventsBloco_0;
    FEventsBloco_C: TEventsBloco_C;
    FEventsBloco_D: TEventsBloco_D;
    FEventsBloco_E: TEventsBloco_E;

    FDT_INI: TDateTime;           /// Data inicial das informa��es contidas no arquivo
    FDT_FIN: TDateTime;           /// Data final das informa��es contidas no arquivo

    FPath: ansistring;            /// Path do arquivo a ser gerado
    FDelimitador: ansistring;     /// Caracter delimitador de campos
    FTrimString: boolean;
    /// Retorna a string sem espa�os em branco iniciais e finais
    FCurMascara: ansistring;      /// Mascara para valores tipo currency

    FBloco_0: TBloco_0;
    FBloco_1: TBloco_1;
    FBloco_9: TBloco_9;
    FBloco_C: TBloco_C;
    FBloco_D: TBloco_D;
    FBloco_E: TBloco_E;
    FBloco_G: TBloco_G;
    FBloco_H: TBloco_H;

    function GetAbout: ansistring;
    function GetConteudo: TStringList;
    function GetDelimitador: ansistring;
    function GetLinhasBuffer: Integer;
    function GetTrimString: boolean;
    function GetCurMascara: ansistring;
    function GetDT_FIN: TDateTime;
    function GetDT_INI: TDateTime;
    procedure InicializaBloco(Bloco: TACBrSPED);
    procedure SetArquivo(const Value: ansistring);
    procedure SetDelimitador(const Value: ansistring);
    procedure SetLinhasBuffer(const Value: Integer);
    procedure SetPath(const Value: ansistring);
    procedure SetTrimString(const Value: boolean);
    procedure SetCurMascara(const Value: ansistring);
    procedure SetDT_FIN(const Value: TDateTime);
    procedure SetDT_INI(const Value: TDateTime);

    function GetOnError: TErrorEvent; /// M�todo do evento OnError
    procedure SetOnError(const Value: TErrorEvent); /// M�todo SetError

    procedure LimpaRegistros;
  protected
    /// BLOCO 0
    procedure WriteRegistro0000;
    procedure WriteRegistro0001;
    procedure WriteRegistro0990;
    /// BLOCO 1
    procedure WriteRegistro1001;
    procedure WriteRegistro1990;
    /// BLOCO C
    procedure WriteRegistroC001;
    procedure WriteRegistroC990;
    /// BLOCO D
    procedure WriteRegistroD001;
    procedure WriteRegistroD990;
    /// BLOCO E
    procedure WriteRegistroE001;
    procedure WriteRegistroE990;
    /// BLOCO G
    procedure WriteRegistroG001;
    procedure WriteRegistroG990;
    /// BLOCO H
    procedure WriteRegistroH001;
    procedure WriteRegistroH990;
    /// BLOCO 9
    procedure WriteRegistro9001;
    procedure WriteRegistro9900;
    procedure WriteRegistro9990;
    procedure WriteRegistro9999;
  public
    constructor Create(AOwner: TComponent); override; /// Create
    destructor Destroy; override; /// Destroy

    procedure SaveFileTXT;

    procedure IniciaGeracao;
    procedure WriteBloco_0;
    procedure WriteBloco_C( FechaBloco: Boolean );
    procedure WriteBloco_D;
    procedure WriteBloco_E;
    procedure WriteBloco_G;
    procedure WriteBloco_H;
    procedure WriteBloco_1;
    procedure WriteBloco_9;

    property Conteudo: TStringList read GetConteudo;

    property DT_INI: TDateTime read GetDT_INI write SetDT_INI;
    property DT_FIN: TDateTime read GetDT_FIN write SetDT_FIN;

    property Bloco_0: TBloco_0 read FBloco_0 write FBloco_0;
    property Bloco_1: TBloco_1 read FBloco_1 write FBloco_1;
    property Bloco_9: TBloco_9 read FBloco_9 write FBloco_9;
    property Bloco_C: TBloco_C read FBloco_C write FBloco_C;
    property Bloco_D: TBloco_D read FBloco_D write FBloco_D;
    property Bloco_E: TBloco_E read FBloco_E write FBloco_E;
    property Bloco_G: TBloco_G read FBloco_G write FBloco_G;
    property Bloco_H: TBloco_H read FBloco_H write FBloco_H;
  published
    property About: ansistring read GetAbout stored False;
    property Path: ansistring read FPath write SetPath;
    property Arquivo: ansistring read FArquivo write SetArquivo;
    property LinhasBuffer : Integer read GetLinhasBuffer write SetLinhasBuffer
      default 1000 ;

    ///
    property Delimitador: ansistring read GetDelimitador write SetDelimitador;
    property TrimString: boolean read GetTrimString write SetTrimString;
    property CurMascara: ansistring read GetCurMascara write SetCurMascara;

    property OnError: TErrorEvent read GetOnError write SetOnError;

    property EventsBloco_0: TEventsBloco_0 read FEventsBloco_0; // write FOnEventsBloco_0;
    property EventsBloco_C: TEventsBloco_C read FEventsBloco_C; // write FOnEventsBloco_C;
    property EventsBloco_D: TEventsBloco_D read FEventsBloco_D; // write FOnEventsBloco_D;
    property EventsBloco_E: TEventsBloco_E read FEventsBloco_E; // write FOnEventsBloco_E;
  end;

procedure Register;

implementation

uses ACBrUtil;

{$IFNDEF FPC}
 {$R ACBr_SPEDFiscal.dcr}
{$ENDIF}

procedure Register;
begin
  RegisterComponents('ACBr', [TACBrSPEDFiscal]);
end;

(* TACBrSPEDFiscal *)

constructor TACBrSPEDFiscal.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  FACBrTXT := TACBrTXTClass.Create;
  FACBrTXT.LinhasBuffer := 1000 ;

  FInicializado := False;

  FBloco_0 := TBloco_0.Create;
  FBloco_1 := TBloco_1.Create;
  FBloco_C := TBloco_C.Create;
  FBloco_D := TBloco_D.Create;
  FBloco_E := TBloco_E.Create;
  FBloco_G := TBloco_G.Create;
  FBloco_H := TBloco_H.Create;
  FBloco_9 := TBloco_9.Create;

  /// Objeto passado por refer�ncia para que possamos usa-lo para fazer pesquisa
  /// em seus registros.
  /// Ex: Do Bloco_C registro C425, pesquisar o Bloco_0 registro 0200.
  FBloco_1.Bloco_0 := FBloco_0;
  FBloco_C.Bloco_0 := FBloco_0;
  FBloco_D.Bloco_0 := FBloco_0;
  FBloco_E.Bloco_0 := FBloco_0;
  FBloco_G.Bloco_0 := FBloco_0;
  FBloco_H.Bloco_0 := FBloco_0;

  FPath := ExtractFilePath(ParamStr(0));
  FDelimitador := '|';
  FCurMascara := '#0.00';
  FTrimString := True;

  // Seta os valores defaults para todos os cdaBlocos
  SetDelimitador(FDelimitador);
  SetCurMascara(FCurMascara);
  SetTrimString(FTrimString);

  FEventsBloco_0 := TEventsBloco_0.Create(Self);
  FEventsBloco_0.Name := 'EventsBloco_0';
  FEventsBloco_0.SetSubComponent(True);

  FEventsBloco_C := TEventsBloco_C.Create(Self);
  FEventsBloco_C.Name := 'EventsBloco_C';
  FEventsBloco_C.SetSubComponent(True);

  FEventsBloco_D := TEventsBloco_D.Create(Self);
  FEventsBloco_D.Name := 'EventsBloco_D';
  FEventsBloco_D.SetSubComponent(True);

  FEventsBloco_E := TEventsBloco_E.Create(Self);
  FEventsBloco_E.Name := 'EventsBloco_E';
  FEventsBloco_E.SetSubComponent(True);
end;

destructor TACBrSPEDFiscal.Destroy;
begin
  FACBrTXT.Free;

  FEventsBloco_0.Free;
  FEventsBloco_C.Free;
  FEventsBloco_D.Free;
  FEventsBloco_E.Free;

  FBloco_0.Free;
  FBloco_1.Free;
  FBloco_C.Free;
  FBloco_D.Free;
  FBloco_E.Free;
  FBloco_G.Free;
  FBloco_H.Free;
  FBloco_9.Free;
  inherited;
end;

procedure TACBrSPEDFiscal.LimpaRegistros;
begin
  FBloco_0.LimpaRegistros;
  FBloco_1.LimpaRegistros;
  FBloco_C.LimpaRegistros;
  FBloco_D.LimpaRegistros;
  FBloco_E.LimpaRegistros;
  FBloco_G.LimpaRegistros;
  FBloco_H.LimpaRegistros;
  FBloco_9.LimpaRegistros;
end;

function TACBrSPEDFiscal.GetAbout: ansistring;
begin
   Result := 'ACBrSpedFiscal Ver: ' + CACBrSpedFiscal_Versao;
end;

function TACBrSPEDFiscal.GetConteudo: TStringList;
begin
  Result := FACBrTXT.Conteudo;
end;

function TACBrSPEDFiscal.GetDelimitador: ansistring;
begin
   Result := FDelimitador;
end;

function TACBrSPEDFiscal.GetLinhasBuffer: Integer;
begin
   Result := FACBrTXT.LinhasBuffer ;
end;

procedure TACBrSPEDFiscal.SetDelimitador(const Value: ansistring);
begin
  if Value = '' then
     raise Exception.Create('Campo n�o pode ser vazio!');

  FDelimitador := Value;

  FBloco_0.Delimitador := Value;
  FBloco_1.Delimitador := Value;
  FBloco_C.Delimitador := Value;
  FBloco_D.Delimitador := Value;
  FBloco_E.Delimitador := Value;
  FBloco_G.Delimitador := Value;
  FBloco_H.Delimitador := Value;
  FBloco_9.Delimitador := Value;
end;

procedure TACBrSPEDFiscal.SetLinhasBuffer(const Value: Integer);
begin
   FACBrTXT.LinhasBuffer := Value ;
end;

procedure TACBrSPEDFiscal.SetPath(const Value: ansistring);
begin
  if Value = '' then
     raise Exception.Create('Campo n�o pode ser vazio!');

  FPath := PathWithDelim( Value );
end;

function TACBrSPEDFiscal.GetCurMascara: ansistring;
begin
  Result := FCurMascara;
end;

procedure TACBrSPEDFiscal.SetCurMascara(const Value: ansistring);
begin
  if Value = '' then
     raise Exception.Create('Campo n�o pode ser vazio! Para deixar sem mascara digite #');

	 FCurMascara := Value;

  FBloco_0.CurMascara := Value;
  FBloco_1.CurMascara := Value;
  FBloco_C.CurMascara := Value;
  FBloco_D.CurMascara := Value;
  FBloco_E.CurMascara := Value;
  FBloco_G.CurMascara := Value;
  FBloco_H.CurMascara := Value;
  FBloco_9.CurMascara := Value;
end;

function TACBrSPEDFiscal.GetTrimString: boolean;
begin
  Result := FTrimString;
end;

procedure TACBrSPEDFiscal.SetTrimString(const Value: boolean);
begin
  FTrimString := Value;

  FBloco_0.TrimString := Value;
  FBloco_1.TrimString := Value;
  FBloco_C.TrimString := Value;
  FBloco_D.TrimString := Value;
  FBloco_E.TrimString := Value;
  FBloco_G.TrimString := Value;
  FBloco_H.TrimString := Value;
  FBloco_9.TrimString := Value;
end;

function TACBrSPEDFiscal.GetDT_INI: TDateTime;
begin
  Result := FDT_INI;
end;

procedure TACBrSPEDFiscal.InicializaBloco( Bloco: TACBrSPED ) ;
begin
   Bloco.NomeArquivo  := FACBrTXT.NomeArquivo;
   Bloco.LinhasBuffer := FACBrTXT.LinhasBuffer;
   Bloco.Gravado      := False ;
   Bloco.Conteudo.Clear;
end;

procedure TACBrSPEDFiscal.IniciaGeracao;
begin
  if FInicializado then exit ;

  if (Trim(FArquivo) = '') or (Trim(FPath) = '') then
    raise Exception.Create(ACBrStr('Caminho ou nome do arquivo n�o informado!'));

  FACBrTXT.NomeArquivo := FPath + FArquivo ;
  FACBrTXT.Reset;    // Apaga o Arquivo e limpa mem�ria

  InicializaBloco( Bloco_0 ) ;
  InicializaBloco( Bloco_C ) ;
  InicializaBloco( Bloco_D ) ;
  InicializaBloco( Bloco_E ) ;
  InicializaBloco( Bloco_G ) ;
  InicializaBloco( Bloco_H ) ;
  InicializaBloco( Bloco_1 ) ;
  InicializaBloco( Bloco_9 ) ;

  ///
  FACBrTXT.Check(FDT_INI > 0, 'CHECAGEM INICIAL: Informe a data '
    + 'inicial das informa��es contidas no arquivo!');
  FACBrTXT.Check(FDT_FIN > 0, 'CHECAGEM INICIAL: Informe a data '
    + 'final das informa��es contidas no arquivo!');
  FACBrTXT.Check(DayOf(FDT_INI) = 1, 'CHECAGEM INICIAL: A data inicial deve '
    + 'corresponder ao primeiro dia do m�s informado!');
  FACBrTXT.Check(FDT_FIN >= FDT_INI, 'CHECAGEM INICIAL: A data final deve se '
    + 'maior que a data inicial!');
  FACBrTXT.Check(FDT_FIN <= Date, 'CHECAGEM INICIAL: A data final "%s" '
    + 'n�o pode ser superior a data atual "%s"!',
    [DateToStr(FDT_FIN), DateToStr(Date)]);
  FACBrTXT.Check(DateOf(EndOfTheMonth(FDT_FIN)) = DateOf(FDT_FIN),
    'CHECAGEM ' + 'INICIAL: A data final deve corresponder ao �ltimo dia do m�s '
    + 'informado!');

  /// Prepara��o para totaliza��es de registros.
  Bloco_0.Registro0990.QTD_LIN_0 := 0;
  Bloco_1.Registro1990.QTD_LIN_1 := 0;
  Bloco_C.RegistroC990.QTD_LIN_C := 0;
  Bloco_D.RegistroD990.QTD_LIN_D := 0;
  Bloco_E.RegistroE990.QTD_LIN_E := 0;
  Bloco_G.RegistroG990.QTD_LIN_G := 0;
  Bloco_H.RegistroH990.QTD_LIN_H := 0;
  Bloco_9.Registro9990.QTD_LIN_9 := 0;
  Bloco_9.Registro9999.QTD_LIN   := 0;

  /// Limpa a lista
  Bloco_9.Registro9900.Clear;

  FInicializado := True ;
end;

procedure TACBrSPEDFiscal.SetArquivo(const Value: ansistring);
var
  APath : AnsiString;
begin
  if FArquivo = Value then
     exit;

  FArquivo := ExtractFileName( Value );
  APath    := ExtractFilePath( Value );

  if APath <> '' then
     Path := APath;
end;

procedure TACBrSPEDFiscal.SetDT_INI(const Value: TDateTime);
begin
  FDT_INI := Value;

  FBloco_0.DT_INI := Value;
  FBloco_1.DT_INI := Value;
  FBloco_9.DT_INI := Value;
  FBloco_C.DT_INI := Value;
  FBloco_D.DT_INI := Value;
  FBloco_E.DT_INI := Value;
  FBloco_G.DT_INI := Value;
  FBloco_H.DT_INI := Value;

  if Assigned(FBloco_0) then
  begin
    FBloco_0.Registro0000.DT_INI := Value;
    //     FBloco_E.RegistroE100.DT_INI := Value;
  end;
end;

function TACBrSPEDFiscal.GetDT_FIN: TDateTime;
begin
  Result := FDT_FIN;
end;

procedure TACBrSPEDFiscal.SetDT_FIN(const Value: TDateTime);
begin
  FDT_FIN := Value;

  FBloco_0.DT_FIN := Value;
  FBloco_1.DT_FIN := Value;
  FBloco_9.DT_FIN := Value;
  FBloco_C.DT_FIN := Value;
  FBloco_D.DT_FIN := Value;
  FBloco_E.DT_FIN := Value;
  FBloco_G.DT_FIN := Value;
  FBloco_H.DT_FIN := Value;

  if Assigned(FBloco_0) then
  begin
    FBloco_0.Registro0000.DT_FIN := Value;
    //     FBloco_E.RegistroE100.DT_FIN := Value;
  end;
end;

function TACBrSPEDFiscal.GetOnError: TErrorEvent;
begin
  Result := FOnError;
end;

procedure TACBrSPEDFiscal.SetOnError(const Value: TErrorEvent);
begin
  FOnError := Value;

  FBloco_0.OnError := Value;
  FBloco_1.OnError := Value;
  FBloco_C.OnError := Value;
  FBloco_D.OnError := Value;
  FBloco_E.OnError := Value;
  FBloco_G.OnError := Value;
  FBloco_H.OnError := Value;
  FBloco_9.OnError := Value;
end;

procedure TACBrSPEDFiscal.SaveFileTXT;
begin
  try
    IniciaGeracao;

    WriteBloco_0;
    WriteBloco_C( True );    // True = Fecha o Bloco

    WriteBloco_D;
    WriteBloco_E;
    WriteBloco_G;
    WriteBloco_H;

    WriteBloco_1;
    WriteBloco_9;
  finally
    /// Limpa de todos os Blocos as listas de todos os registros.
    LimpaRegistros;
    FACBrTXT.Conteudo.Clear;

    FInicializado := False ;
  end;
end;

procedure TACBrSPEDFiscal.WriteBloco_0;
begin
  if Bloco_0.Gravado then exit ;

  if not FInicializado then
     raise Exception.Create( 'M�todos "IniciaGeracao" n�o foi executado' );

  /// BLOCO 0
  WriteRegistro0000;
  WriteRegistro0001;
  WriteRegistro0990;
  Bloco_0.WriteBuffer;
  Bloco_0.Conteudo.Clear;
  Bloco_0.Gravado := True ;
end;

procedure TACBrSPEDFiscal.WriteBloco_C( FechaBloco : Boolean );
begin
   if Bloco_C.Gravado then exit ;

   if not Bloco_0.Gravado then
      WriteBloco_0 ;

   /// BLOCO C
   WriteRegistroC001;

   if Bloco_C.RegistroC001.IND_MOV = imSemDados then
      FechaBloco := True ;

   if FechaBloco then
      WriteRegistroC990;

   Bloco_C.WriteBuffer;
   Bloco_C.Conteudo.Clear;

   Bloco_C.Gravado := FechaBloco;
end;

procedure TACBrSPEDFiscal.WriteBloco_D;
begin
   if Bloco_D.Gravado then exit ;

   if not Bloco_C.Gravado then
      WriteBloco_C(True);

   /// BLOCO D
   WriteRegistroD001;
   WriteRegistroD990;
   Bloco_D.WriteBuffer;
   Bloco_D.Conteudo.Clear;
   Bloco_D.Gravado := True ;
end;

procedure TACBrSPEDFiscal.WriteBloco_E;
begin
   if Bloco_E.Gravado then exit ;

   if not Bloco_D.Gravado then
      WriteBloco_D;

   /// BLOCO E
   WriteRegistroE001;
   WriteRegistroE990;
   Bloco_E.WriteBuffer;
   Bloco_E.Conteudo.Clear;
   Bloco_E.Gravado := True ;
end;

procedure TACBrSPEDFiscal.WriteBloco_G;
begin
   if Bloco_G.Gravado then exit ;

   if not Bloco_E.Gravado then
      WriteBloco_E;

   /// Este ato entra em vigor na data de sua publica��o, produzindo efeitos
   /// para as escritura��es referentes aos per�odos a partir de 1� de  janeiro de 2010,
   /// --> exceto quanto ao BLOCO G e registros pertinentes ao Livro de
   /// Controle de Cr�dito de ICMS do Ativo Permanente cujos efeitos ser�o
   /// a partir de 1� de julho de 2010 <--.
   /// Exig�ncia do Art. 3� do AC 09/08
   ///
   /// Prorrogado para 01/01/2011 conforme Guia Pr�tico da EFD 2.01
   /// *Bloco G inclu�do para vigorar a partir do per�odo de apura��o de janeiro de 2011.
   if DT_INI >= EncodeDate(2011,01,01) then
   begin
     /// BLOCO G
     WriteRegistroG001;
     WriteRegistroG990;
     Bloco_G.WriteBuffer;
   end;

   Bloco_G.Conteudo.Clear;
   Bloco_G.Gravado := True ;
end;

procedure TACBrSPEDFiscal.WriteBloco_H;
begin
   if Bloco_H.Gravado then exit ;

   if not Bloco_G.Gravado then
      WriteBloco_G;

   /// BLOCO H
   WriteRegistroH001;
   WriteRegistroH990;
   Bloco_H.WriteBuffer;
   Bloco_H.Conteudo.Clear;
   Bloco_H.Gravado := True ;
end;

procedure TACBrSPEDFiscal.WriteBloco_1;
begin
   if Bloco_1.Gravado then exit ;

   if not Bloco_H.Gravado then
      WriteBloco_H;

   /// BLOCO 1
   WriteRegistro1001;
   WriteRegistro1990;
   Bloco_1.WriteBuffer;
   Bloco_1.Conteudo.Clear;
   Bloco_1.Gravado := True ;
end;

procedure TACBrSPEDFiscal.WriteBloco_9;
begin
   if Bloco_9.Gravado then exit ;

   if not Bloco_1.Gravado then
      WriteBloco_1 ;

   /// BLOCO 9
   WriteRegistro9001;
   WriteRegistro9900;
   WriteRegistro9990;
   WriteRegistro9999;
   Bloco_9.WriteBuffer;
   Bloco_9.Conteudo.Clear;
   Bloco_9.Gravado := True ;
end;

procedure TACBrSPEDFiscal.WriteRegistro0000;
begin
   with Bloco_9.Registro9900.New do
   begin
      REG_BLC := '0000';
      QTD_REG_BLC := 1;
   end;
   Bloco_0.WriteRegistro0000;
end;

procedure TACBrSPEDFiscal.WriteRegistro0001;
begin
   Bloco_0.WriteRegistro0001;
   //
   with Bloco_9.Registro9900 do
   begin
      with New do
      begin
         REG_BLC := '0001';
         QTD_REG_BLC := 1;
      end;
   end;
   if Bloco_0.Registro0001.IND_MOV = imComDados then
   begin
      with Bloco_9.Registro9900 do
      begin
         if Bloco_0.Registro0005Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '0005';
               QTD_REG_BLC := Bloco_0.Registro0005Count;
            end;
         end;
         if Bloco_0.Registro0015Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '0015';
               QTD_REG_BLC := Bloco_0.Registro0015Count;
            end;
         end;
         with New do
         begin
            REG_BLC := '0100';
            QTD_REG_BLC := 1;
         end;
         if Bloco_0.Registro0150Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '0150';
               QTD_REG_BLC := Bloco_0.Registro0150Count;
            end;
         end;
         if Bloco_0.Registro0175Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '0175';
               QTD_REG_BLC := Bloco_0.Registro0175Count;
            end;
         end;
         if Bloco_0.Registro0190Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '0190';
               QTD_REG_BLC := Bloco_0.Registro0190Count;
            end;
         end;
         if Bloco_0.Registro0200Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '0200';
               QTD_REG_BLC := Bloco_0.Registro0200Count;
            end;
         end;
         if Bloco_0.Registro0205Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '0205';
               QTD_REG_BLC := Bloco_0.Registro0205Count;
            end;
         end;
         if Bloco_0.Registro0206Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '0206';
               QTD_REG_BLC := Bloco_0.Registro0206Count;
            end;
         end;
         if Bloco_0.Registro0220Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '0220';
               QTD_REG_BLC := Bloco_0.Registro0220Count;
            end;
         end;
         /// Exig�ncia do Art. 3� do AC 09/08
         ///
         /// Prorrogado para 01/01/2011 conforme Guia Pr�tico da EFD 2.01
         /// *Bloco G inclu�do para vigorar a partir do per�odo de apura��o de janeiro de 2011.
         if DT_INI >= EncodeDate(2011,01,01) then
         begin
            if Bloco_0.Registro0300Count > 0 then
            begin
               with New do
               begin
                  REG_BLC := '0300';
                  QTD_REG_BLC := Bloco_0.Registro0300Count;
               end;
            end;
            if Bloco_0.Registro0305Count > 0 then
            begin
               with New do
               begin
                  REG_BLC := '0305';
                  QTD_REG_BLC := Bloco_0.Registro0305Count;
               end;
            end;
         end;
         if Bloco_0.Registro0400Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '0400';
               QTD_REG_BLC := Bloco_0.Registro0400Count;
            end;
         end;
         if Bloco_0.Registro0450Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '0450';
               QTD_REG_BLC := Bloco_0.Registro0450Count;
            end;
         end;
         if Bloco_0.Registro0460Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '0460';
               QTD_REG_BLC := Bloco_0.Registro0460Count;
            end;
         end;
         /// Exig�ncia do Art. 3� do AC 09/08
         ///
         /// Prorrogado para 01/01/2011 conforme Guia Pr�tico da EFD 2.01
         /// *Bloco G inclu�do para vigorar a partir do per�odo de apura��o de janeiro de 2011.
         if DT_INI >= EncodeDate(2011,01,01) then
         begin
            if Bloco_0.Registro0500Count > 0 then
            begin
               with New do
               begin
                  REG_BLC := '0500';
                  QTD_REG_BLC := Bloco_0.Registro0500Count;
               end;
            end;
            if Bloco_0.Registro0600Count > 0 then
            begin
               with New do
               begin
                  REG_BLC := '0600';
                  QTD_REG_BLC := Bloco_0.Registro0600Count;
               end;
            end;
         end;
      end;
   end;
end;

procedure TACBrSPEDFiscal.WriteRegistro0990;
begin
   with Bloco_9.Registro9900.New do
   begin
      REG_BLC := '0990';
      QTD_REG_BLC := 1;
   end;
   Bloco_0.WriteRegistro0990;
end;

procedure TACBrSPEDFiscal.WriteRegistro1001;
begin
   Bloco_1.WriteRegistro1001;
   //
   with Bloco_9.Registro9900 do
   begin
      with New do
      begin
         REG_BLC := '1001';
         QTD_REG_BLC := 1;
      end;
   end;
   if Bloco_1.Registro1001.IND_MOV = imComDados then
   begin
      with Bloco_9.Registro9900 do
      begin
         if Bloco_1.Registro1010Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1010';
               QTD_REG_BLC := Bloco_1.Registro1010Count;
            end;
         end;
         if Bloco_1.Registro1100Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1100';
               QTD_REG_BLC := Bloco_1.Registro1100Count;
            end;
         end;
         if Bloco_1.Registro1105Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1105';
               QTD_REG_BLC := Bloco_1.Registro1105Count;
            end;
         end;
         if Bloco_1.Registro1110Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1110';
               QTD_REG_BLC := Bloco_1.Registro1110Count;
            end;
         end;
         if Bloco_1.Registro1200Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1200';
               QTD_REG_BLC := Bloco_1.Registro1200Count;
            end;
         end;
         if Bloco_1.Registro1210Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1210';
               QTD_REG_BLC := Bloco_1.Registro1210Count;
            end;
         end;
         if Bloco_1.Registro1300Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1300';
               QTD_REG_BLC := Bloco_1.Registro1300Count;
            end;
         end;
         if Bloco_1.Registro1310Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1310';
               QTD_REG_BLC := Bloco_1.Registro1310Count;
            end;
         end;
         if Bloco_1.Registro1320Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1320';
               QTD_REG_BLC := Bloco_1.Registro1320Count;
            end;
         end;
         if Bloco_1.Registro1350Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1350';
               QTD_REG_BLC := Bloco_1.Registro1350Count;
            end;
         end;
         if Bloco_1.Registro1360Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1360';
               QTD_REG_BLC := Bloco_1.Registro1360Count;
            end;
         end;
         if Bloco_1.Registro1370Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1370';
               QTD_REG_BLC := Bloco_1.Registro1370Count;
            end;
         end;
         if Bloco_1.Registro1390Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1390';
               QTD_REG_BLC := Bloco_1.Registro1390Count;
            end;
         end;
         if Bloco_1.Registro1391Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1391';
               QTD_REG_BLC := Bloco_1.Registro1391Count;
            end;
         end;
         if Bloco_1.Registro1400Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1400';
               QTD_REG_BLC := Bloco_1.Registro1400Count;
            end;
         end;
         if Bloco_1.Registro1500Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1500';
               QTD_REG_BLC := Bloco_1.Registro1500Count;
            end;
         end;
         if Bloco_1.Registro1510Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1510';
               QTD_REG_BLC := Bloco_1.Registro1510Count;
            end;
         end;
         if Bloco_1.Registro1600Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1600';
               QTD_REG_BLC := Bloco_1.Registro1600Count;
            end;
         end;
         if Bloco_1.Registro1700Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1700';
               QTD_REG_BLC := Bloco_1.Registro1700Count;
            end;
         end;
         if Bloco_1.Registro1710Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1710';
               QTD_REG_BLC := Bloco_1.Registro1710Count;
            end;
         end;
         if Bloco_1.Registro1800Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1800';
               QTD_REG_BLC := Bloco_1.Registro1800Count;
            end;
         end;
         if Bloco_1.Registro1900Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1900';
               QTD_REG_BLC := Bloco_1.Registro1900Count;
            end;
         end;
         if Bloco_1.Registro1910Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1910';
               QTD_REG_BLC := Bloco_1.Registro1910Count;
            end;
         end;
         if Bloco_1.Registro1920Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1920';
               QTD_REG_BLC := Bloco_1.Registro1920Count;
            end;
         end;
         if Bloco_1.Registro1921Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1921';
               QTD_REG_BLC := Bloco_1.Registro1921Count;
            end;
         end;
         if Bloco_1.Registro1922Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1922';
               QTD_REG_BLC := Bloco_1.Registro1922Count;
            end;
         end;
         if Bloco_1.Registro1923Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1923';
               QTD_REG_BLC := Bloco_1.Registro1923Count;
            end;
         end;
         if Bloco_1.Registro1925Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1925';
               QTD_REG_BLC := Bloco_1.Registro1925Count;
            end;
         end;
         if Bloco_1.Registro1926Count > 0 then
         begin
            with New do
            begin
               REG_BLC := '1926';
               QTD_REG_BLC := Bloco_1.Registro1926Count;
            end;
         end;
      end;
   end;
end;

procedure TACBrSPEDFiscal.WriteRegistro1990;
begin
   with Bloco_9.Registro9900.New do
   begin
      REG_BLC := '1990';
      QTD_REG_BLC := 1;
   end;
   Bloco_1.WriteRegistro1990;
end;

procedure TACBrSPEDFiscal.WriteRegistroC001;
begin
  Bloco_C.WriteRegistroC001;
end;

procedure TACBrSPEDFiscal.WriteRegistroC990;
begin
  with Bloco_9.Registro9900 do
  begin
     with New do
     begin
        REG_BLC := 'C001';
        QTD_REG_BLC := 1;
     end;
  end;

  if Bloco_C.RegistroC001.IND_MOV = imComDados then
  begin
    with Bloco_9.Registro9900 do
    begin
       if Bloco_C.RegistroC100Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C100';
           QTD_REG_BLC := Bloco_C.RegistroC100Count;
         end;
       end;
       if Bloco_C.RegistroC110Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C110';
           QTD_REG_BLC := Bloco_C.RegistroC110Count;
         end;
       end;
       if Bloco_C.RegistroC111Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C111';
           QTD_REG_BLC := Bloco_C.RegistroC111Count;
         end;
       end;
       if Bloco_C.RegistroC112Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C112';
           QTD_REG_BLC := Bloco_C.RegistroC112Count;
         end;
       end;
       if Bloco_C.RegistroC113Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C113';
           QTD_REG_BLC := Bloco_C.RegistroC113Count;
         end;
       end;
       if Bloco_C.RegistroC114Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C114';
           QTD_REG_BLC := Bloco_C.RegistroC114Count;
         end;
       end;
       if Bloco_C.RegistroC115Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C115';
           QTD_REG_BLC := Bloco_C.RegistroC115Count;
         end;
       end;
       if Bloco_C.RegistroC120Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C120';
           QTD_REG_BLC := Bloco_C.RegistroC120Count;
         end;
       end;
       if Bloco_C.RegistroC130Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C130';
           QTD_REG_BLC := Bloco_C.RegistroC130Count;
         end;
       end;
       if Bloco_C.RegistroC140Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C140';
           QTD_REG_BLC := Bloco_C.RegistroC140Count;   {M�rcio Lopes 30Nov2009}
         end;
       end;
       if Bloco_C.RegistroC141Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C141';
           QTD_REG_BLC := Bloco_C.RegistroC141Count;  {M�rcio Lopes 30Nov2009}
         end;
       end;
       if Bloco_C.RegistroC160Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C160';
           QTD_REG_BLC := Bloco_C.RegistroC160Count;
         end;
       end;
       if Bloco_C.RegistroC165Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C165';
           QTD_REG_BLC := Bloco_C.RegistroC165Count;
         end;
       end;
       if Bloco_C.RegistroC170Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C170';
           QTD_REG_BLC := Bloco_C.RegistroC170Count;
         end;
       end;
       if Bloco_C.RegistroC171Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C171';
           QTD_REG_BLC := Bloco_C.RegistroC171Count;
         end;
       end;
       if Bloco_C.RegistroC172Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C172';
           QTD_REG_BLC := Bloco_C.RegistroC172Count;
         end;
       end;
       if Bloco_C.RegistroC173Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C173';
           QTD_REG_BLC := Bloco_C.RegistroC173Count;
         end;
       end;
       if Bloco_C.RegistroC174Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C174';
           QTD_REG_BLC := Bloco_C.RegistroC174Count;
         end;
       end;
       if Bloco_C.RegistroC175Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C175';
           QTD_REG_BLC := Bloco_C.RegistroC175Count;
         end;
       end;
       if Bloco_C.RegistroC176Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C176';
           QTD_REG_BLC := Bloco_C.RegistroC176Count;
         end;
       end;
       if Bloco_C.RegistroC177Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C177';
           QTD_REG_BLC := Bloco_C.RegistroC177Count;
         end;
       end;
       if Bloco_C.RegistroC178Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C178';
           QTD_REG_BLC := Bloco_C.RegistroC178Count;
         end;
       end;
       if Bloco_C.RegistroC179Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C179';
           QTD_REG_BLC := Bloco_C.RegistroC179Count;
         end;
       end;
       if Bloco_C.RegistroC190Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C190';
           QTD_REG_BLC := Bloco_C.RegistroC190Count;
         end;
       end;
       if Bloco_C.RegistroC195Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C195';
           QTD_REG_BLC := Bloco_C.RegistroC195Count;
         end;
       end;
       if Bloco_C.RegistroC197Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C197';
           QTD_REG_BLC := Bloco_C.RegistroC197Count;
         end;
       end;
       if Bloco_C.RegistroC300Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C300';
           QTD_REG_BLC := Bloco_C.RegistroC300Count;
         end;
       end;
       if Bloco_C.RegistroC310Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C310';
           QTD_REG_BLC := Bloco_C.RegistroC310Count;
         end;
       end;
       if Bloco_C.RegistroC320Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C320';
           QTD_REG_BLC := Bloco_C.RegistroC320Count;
         end;
       end;
       if Bloco_C.RegistroC321Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C321';
           QTD_REG_BLC := Bloco_C.RegistroC321Count;
         end;
       end;
       if Bloco_C.RegistroC350Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C350';
           QTD_REG_BLC := Bloco_C.RegistroC350Count;
         end;
       end;
       if Bloco_C.RegistroC370Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C370';
           QTD_REG_BLC := Bloco_C.RegistroC370Count;
         end;
       end;
       if Bloco_C.RegistroC390Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C390';
           QTD_REG_BLC := Bloco_C.RegistroC390Count;
         end;
       end;
       if Bloco_C.RegistroC400Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C400';
           QTD_REG_BLC := Bloco_C.RegistroC400Count;
         end;
       end;
       if Bloco_C.RegistroC405Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C405';
           QTD_REG_BLC := Bloco_C.RegistroC405Count;
         end;
       end;
       if Bloco_C.RegistroC410Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C410';
           QTD_REG_BLC := Bloco_C.RegistroC410Count;
         end;
       end;
       if Bloco_C.RegistroC420Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C420';
           QTD_REG_BLC := Bloco_C.RegistroC420Count;
         end;
       end;
       if Bloco_C.RegistroC425Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C425';
           QTD_REG_BLC := Bloco_C.RegistroC425Count;
         end;
       end;
       if Bloco_C.RegistroC460Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C460';
           QTD_REG_BLC := Bloco_C.RegistroC460Count;
         end;
       end;
       if Bloco_C.RegistroC470Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C470';
           QTD_REG_BLC := Bloco_C.RegistroC470Count;
         end;
       end;
       if Bloco_C.RegistroC490Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C490';
           QTD_REG_BLC := Bloco_C.RegistroC490Count;
         end;
       end;
       if Bloco_C.RegistroC495Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C495';
           QTD_REG_BLC := Bloco_C.RegistroC495Count;
         end;
       end;
       if Bloco_C.RegistroC500Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C500';
           QTD_REG_BLC := Bloco_C.RegistroC500Count;
         end;
       end;
       if Bloco_C.RegistroC510Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C510';
           QTD_REG_BLC := Bloco_C.RegistroC510Count;
         end;
       end;
       if Bloco_C.RegistroC590Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C590';
           QTD_REG_BLC := Bloco_C.RegistroC590Count;
         end;
       end;
       if Bloco_C.RegistroC600Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C600';
           QTD_REG_BLC := Bloco_C.RegistroC600Count;
         end;
       end;
       if Bloco_C.RegistroC601Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C601';
           QTD_REG_BLC := Bloco_C.RegistroC601Count;
         end;
       end;
       if Bloco_C.RegistroC610Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C610';
           QTD_REG_BLC := Bloco_C.RegistroC610Count;
         end;
       end;
       if Bloco_C.RegistroC690Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C690';
           QTD_REG_BLC := Bloco_C.RegistroC690Count;
         end;
       end;
       if Bloco_C.RegistroC700Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C700';
           QTD_REG_BLC := Bloco_C.RegistroC700Count;
         end;
       end;
       if Bloco_C.RegistroC790Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C790';
           QTD_REG_BLC := Bloco_C.RegistroC790Count;
         end;
       end;
       if Bloco_C.RegistroC791Count > 0 then
       begin
         with New do
         begin
           REG_BLC := 'C791';
           QTD_REG_BLC := Bloco_C.RegistroC791Count;
         end;
       end;

    end;
  end;

  with Bloco_9.Registro9900 do
  begin
     with New do
     begin
       REG_BLC := 'C990';
       QTD_REG_BLC := 1;
     end;
  end;

  Bloco_C.WriteRegistroC990;
end;

procedure TACBrSPEDFiscal.WriteRegistroD001;
begin
   Bloco_D.WriteRegistroD001;
   //
   with Bloco_9.Registro9900 do
   begin
      with New do
      begin
         REG_BLC := 'D001';
         QTD_REG_BLC := 1;
      end;
   end;
   if Bloco_D.RegistroD001.IND_MOV = imComDados then
   begin
      with Bloco_9.Registro9900 do
      begin
         if Bloco_D.RegistroD100Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D100';
               QTD_REG_BLC := Bloco_D.RegistroD100Count;
            end;
         end;
         if Bloco_D.RegistroD110Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D110';
               QTD_REG_BLC := Bloco_D.RegistroD110Count;
            end;
         end;
         if Bloco_D.RegistroD120Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D120';
               QTD_REG_BLC := Bloco_D.RegistroD120Count;
            end;
         end;
         if Bloco_D.RegistroD130Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D130';
               QTD_REG_BLC := Bloco_D.RegistroD130Count;
            end;
         end;
         if Bloco_D.RegistroD140Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D140';
               QTD_REG_BLC := Bloco_D.RegistroD140Count;
            end;
         end;
         if Bloco_D.RegistroD150Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D150';
               QTD_REG_BLC := Bloco_D.RegistroD150Count;
            end;
         end;
         if Bloco_D.RegistroD160Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D160';
               QTD_REG_BLC := Bloco_D.RegistroD160Count;
            end;
         end;
         if Bloco_D.RegistroD161Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D161';
               QTD_REG_BLC := Bloco_D.RegistroD161Count;
            end;
         end;
         if Bloco_D.RegistroD162Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D162';
               QTD_REG_BLC := Bloco_D.RegistroD162Count;
            end;
         end;
         if Bloco_D.RegistroD170Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D170';
               QTD_REG_BLC := Bloco_D.RegistroD170Count;
            end;
         end;
         if Bloco_D.RegistroD180Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D180';
               QTD_REG_BLC := Bloco_D.RegistroD180Count;
            end;
         end;
         if Bloco_D.RegistroD190Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D190';
               QTD_REG_BLC := Bloco_D.RegistroD190Count;
            end;
         end;
         if Bloco_D.RegistroD195Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D195';
               QTD_REG_BLC := Bloco_D.RegistroD195Count;
            end;
         end;
         if Bloco_D.RegistroD197Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D197';
               QTD_REG_BLC := Bloco_D.RegistroD197Count;
            end;
         end;
		 
         if Bloco_D.RegistroD300Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D300';
               QTD_REG_BLC := Bloco_D.RegistroD300Count;
            end;
         end;
         if Bloco_D.RegistroD301Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D301';
               QTD_REG_BLC := Bloco_D.RegistroD301Count;
            end;
         end;
         if Bloco_D.RegistroD310Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D310';
               QTD_REG_BLC := Bloco_D.RegistroD310Count;
            end;
         end;
         if Bloco_D.RegistroD350Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D350';
               QTD_REG_BLC := Bloco_D.RegistroD350Count;
            end;
         end;
         if Bloco_D.RegistroD355Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D355';
               QTD_REG_BLC := Bloco_D.RegistroD355Count;
            end;
         end;
         if Bloco_D.RegistroD360Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D360';
               QTD_REG_BLC := Bloco_D.RegistroD360Count;
            end;
         end;
         if Bloco_D.RegistroD365Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D365';
               QTD_REG_BLC := Bloco_D.RegistroD365Count;
            end;
         end;
         if Bloco_D.RegistroD370Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D370';
               QTD_REG_BLC := Bloco_D.RegistroD370Count;
            end;
         end;
         if Bloco_D.RegistroD390Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D390';
               QTD_REG_BLC := Bloco_D.RegistroD390Count;
            end;
         end;
         if Bloco_D.RegistroD400Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D400';
               QTD_REG_BLC := Bloco_D.RegistroD400Count;
            end;
         end;
         if Bloco_D.RegistroD410Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D410';
               QTD_REG_BLC := Bloco_D.RegistroD410Count;
            end;
         end;
         if Bloco_D.RegistroD411Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D411';
               QTD_REG_BLC := Bloco_D.RegistroD411Count;
            end;
         end;
         if Bloco_D.RegistroD420Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D420';
               QTD_REG_BLC := Bloco_D.RegistroD420Count;
            end;
         end;
         if Bloco_D.RegistroD500Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D500';
               QTD_REG_BLC := Bloco_D.RegistroD500Count;
            end;
         end;
         if Bloco_D.RegistroD510Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D510';
               QTD_REG_BLC := Bloco_D.RegistroD510Count;
            end;
         end;
         if Bloco_D.RegistroD530Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'D530';
               QTD_REG_BLC := Bloco_D.RegistroD530Count;
            end;
         end;
         if Bloco_D.RegistroD590Count > 0 then
         begin
            with New do
            begin
              REG_BLC := 'D590';
              QTD_REG_BLC := Bloco_D.RegistroD590Count;
            end;
         end;
         if Bloco_D.RegistroD600Count > 0 then
         begin
            with New do
            begin
              REG_BLC := 'D600';
              QTD_REG_BLC := Bloco_D.RegistroD600Count;
            end;
         end;
         if Bloco_D.RegistroD610Count > 0 then
         begin
            with New do
            begin
              REG_BLC := 'D610';
              QTD_REG_BLC := Bloco_D.RegistroD610Count;
            end;
         end;
         if Bloco_D.RegistroD690Count > 0 then
         begin
            with New do
            begin
              REG_BLC := 'D690';
              QTD_REG_BLC := Bloco_D.RegistroD690Count;
            end;
         end;
         if Bloco_D.RegistroD695Count > 0 then
         begin
            with New do
            begin
              REG_BLC := 'D695';
              QTD_REG_BLC := Bloco_D.RegistroD695Count;
            end;
         end;
         if Bloco_D.RegistroD696Count > 0 then
         begin
            with New do
            begin
              REG_BLC := 'D696';
              QTD_REG_BLC := Bloco_D.RegistroD696Count;
            end;
         end;
         if Bloco_D.RegistroD697Count > 0 then
         begin
            with New do
            begin
              REG_BLC := 'D697';
              QTD_REG_BLC := Bloco_D.RegistroD697Count;
            end;
         end;
      end;
   end;
end;

procedure TACBrSPEDFiscal.WriteRegistroD990;
begin
  with Bloco_9.Registro9900.New do
  begin
    REG_BLC := 'D990';
    QTD_REG_BLC := 1;
  end;
  Bloco_D.WriteRegistroD990;
end;

procedure TACBrSPEDFiscal.WriteRegistroE001;
begin
   Bloco_E.WriteRegistroE001;
   //
   with Bloco_9.Registro9900 do
   begin
      with New do
      begin
         REG_BLC := 'E001';
         QTD_REG_BLC := 1;
      end;
   end;
   if Bloco_E.RegistroE001.IND_MOV = imComDados then
   begin
      with Bloco_9.Registro9900 do
      begin
         with New do
         begin
            REG_BLC := 'E100';
            QTD_REG_BLC := Bloco_E.RegistroE100Count;
         end;
         with New do
         begin
            REG_BLC := 'E110';
            QTD_REG_BLC := Bloco_E.RegistroE110Count;
         end;
         if Bloco_E.RegistroE111Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'E111';
               QTD_REG_BLC := Bloco_E.RegistroE111Count;
            end;
         end;
         if Bloco_E.RegistroE112Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'E112';
               QTD_REG_BLC := Bloco_E.RegistroE112Count;
            end;
         end;
         if Bloco_E.RegistroE113Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'E113';
               QTD_REG_BLC := Bloco_E.RegistroE113Count;
            end;
         end;
         if Bloco_E.RegistroE115Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'E115';
               QTD_REG_BLC := Bloco_E.RegistroE115Count;
            end;
         end;
         if Bloco_E.RegistroE116Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'E116';
               QTD_REG_BLC := Bloco_E.RegistroE116Count;
            end;
         end;
         if Bloco_E.RegistroE200Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'E200';
               QTD_REG_BLC := Bloco_E.RegistroE200Count;
            end;
         end;
         if Bloco_E.RegistroE210Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'E210';
               QTD_REG_BLC := Bloco_E.RegistroE210Count;
            end;
         end;
         if Bloco_E.RegistroE220Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'E220';
               QTD_REG_BLC := Bloco_E.RegistroE220Count;
            end;
         end;
         if Bloco_E.RegistroE230Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'E230';
               QTD_REG_BLC := Bloco_E.RegistroE230Count;
            end;
         end;
         if Bloco_E.RegistroE240Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'E240';
               QTD_REG_BLC := Bloco_E.RegistroE240Count;
            end;
         end;
         if Bloco_E.RegistroE250Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'E250';
               QTD_REG_BLC := Bloco_E.RegistroE250Count;
            end;
         end;
         if Bloco_E.RegistroE500Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'E500';
               QTD_REG_BLC := Bloco_E.RegistroE500Count;
            end;
         end;
         if Bloco_E.RegistroE510Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'E510';
               QTD_REG_BLC := Bloco_E.RegistroE510Count;
            end;
         end;
         if Bloco_E.RegistroE520Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'E520';
               QTD_REG_BLC := Bloco_E.RegistroE520Count;
            end;
         end;
         if Bloco_E.RegistroE530Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'E530';
               QTD_REG_BLC := Bloco_E.RegistroE530Count;
            end;
         end;
      end;
   end;
end;

procedure TACBrSPEDFiscal.WriteRegistroE990;
begin
  with Bloco_9.Registro9900.New do
  begin
    REG_BLC := 'E990';
    QTD_REG_BLC := 1;
  end;
  Bloco_E.WriteRegistroE990;
end;

procedure TACBrSPEDFiscal.WriteRegistroG001;
begin
   Bloco_G.WriteRegistroG001;
   //
   with Bloco_9.Registro9900 do
   begin
      with New do
      begin
         REG_BLC := 'G001';
         QTD_REG_BLC := 1;
      end;
   end;
   if Bloco_G.RegistroG001.IND_MOV = imComDados then
   begin
      with Bloco_9.Registro9900 do
      begin
         if Bloco_G.RegistroG110Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'G110';
               QTD_REG_BLC := Bloco_G.RegistroG110Count;
            end;
         end;
         if Bloco_G.RegistroG125Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'G125';
               QTD_REG_BLC := Bloco_G.RegistroG125Count;
            end;
         end;
         if Bloco_G.RegistroG126Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'G126';
               QTD_REG_BLC := Bloco_G.RegistroG126Count;
            end;
         end;
         if Bloco_G.RegistroG130Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'G130';
               QTD_REG_BLC := Bloco_G.RegistroG130Count;
            end;
         end;
         if Bloco_G.RegistroG140Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'G140';
               QTD_REG_BLC := Bloco_G.RegistroG140Count;
            end;
         end;
      end;
   end;
end;

procedure TACBrSPEDFiscal.WriteRegistroG990;
begin
  with Bloco_9.Registro9900.New do
  begin
    REG_BLC := 'G990';
    QTD_REG_BLC := 1;
  end;
  Bloco_G.WriteRegistroG990;
end;

procedure TACBrSPEDFiscal.WriteRegistroH001;
begin
   Bloco_H.WriteRegistroH001;
   //
   with Bloco_9.Registro9900 do
   begin
      with New do
      begin
         REG_BLC := 'H001';
         QTD_REG_BLC := 1;
      end;
   end;
   if Bloco_H.RegistroH001.IND_MOV = imComDados then
   begin
      with Bloco_9.Registro9900 do
      begin
         if Bloco_H.RegistroH005Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'H005';
               QTD_REG_BLC := Bloco_H.RegistroH005Count;
            end;
         end;
         if Bloco_H.RegistroH010Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'H010';
               QTD_REG_BLC := Bloco_H.RegistroH010Count;
            end;
         end;
         if Bloco_H.RegistroH020Count > 0 then
         begin
            with New do
            begin
               REG_BLC := 'H020';
               QTD_REG_BLC := Bloco_H.RegistroH020Count;
            end;
         end;
      end;
   end;
end;

procedure TACBrSPEDFiscal.WriteRegistroH990;
begin
  with Bloco_9.Registro9900.New do
  begin
    REG_BLC := 'H990';
    QTD_REG_BLC := 1;
  end;
  Bloco_H.WriteRegistroH990;
end;

procedure TACBrSPEDFiscal.WriteRegistro9001;
begin
  with Bloco_9.Registro9900.New do
  begin
    REG_BLC := '9001';
    QTD_REG_BLC := 1;
  end;
  Bloco_9.WriteRegistro9001;
end;

procedure TACBrSPEDFiscal.WriteRegistro9900;
begin
  with Bloco_9.Registro9900 do
  begin
    with New do
    begin
      REG_BLC := '9900';
      QTD_REG_BLC := Bloco_9.Registro9900.Count + 2;
    end;
    with New do
    begin
      REG_BLC := '9990';
      QTD_REG_BLC := 1;
    end;
    with New do
    begin
      REG_BLC := '9999';
      QTD_REG_BLC := 1;
    end;
  end;
  Bloco_9.WriteRegistro9900;
end;

procedure TACBrSPEDFiscal.WriteRegistro9990;
begin
  Bloco_9.WriteRegistro9990;
end;

procedure TACBrSPEDFiscal.WriteRegistro9999;
begin
  Bloco_9.Registro9999.QTD_LIN := Bloco_9.Registro9999.QTD_LIN + Bloco_0.Registro0990.QTD_LIN_0 +
                                                                 Bloco_1.Registro1990.QTD_LIN_1 +
                                                                 Bloco_C.RegistroC990.QTD_LIN_C +
                                                                 Bloco_D.RegistroD990.QTD_LIN_D +
                                                                 Bloco_E.RegistroE990.QTD_LIN_E +
                                                                 ifThen(Bloco_G.DT_INI >= EncodeDate(2011,01,01), Bloco_G.RegistroG990.QTD_LIN_G, 0) +
                                                                 Bloco_H.RegistroH990.QTD_LIN_H +
                                                                 Bloco_9.Registro9990.QTD_LIN_9;
  Bloco_9.WriteRegistro9999;
end;

{$IFNDEF Framework}
{$IFDEF FPC}
initialization
   {$I ACBrSpedFiscal.lrs}
{$ENDIF}
{$ENDIF}

end.

