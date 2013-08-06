{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:    Alexandre Rocha Lima e Marcondes             }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }
{                                                                              }
{ Esse arquivo usa a classe  SynaSer   Copyright (c)2001-2003, Lukas Gebauer   }
{  Project : Ararat Synapse     (Found at URL: http://www.ararat.cz/synapse/)  }
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
|* 26/05/2004: Primeira Versao
|*    Daniel Simoes de Almeida
|*    Cria�ao do componente ACBrDevice, que implementa comunica�ao com portas
|*    Seriais e Paralela e dever� ser usado por outros componentes ACBr*
|* 26/09/2004: Cria�ao da classe: TACBrThreadTimer
|*    Daniel e Alexandre
|*    Essa classe emula um TTimer, porem em uma Thread, evitando sobrecarregar
|*    o Application. Usada por ACBrLCB e ACBrDIS
|* 01/02/2005: Cria�ao da classe: TACBrThreadEnviaLPT
|*    Daniel Simoes de Almeida
|*    Essa classe � usada pela fun�ao EnviaStrThread para detectar se os Dados
|*    estao sendo "gravados" com sucesso na porta paralela ou arquivo.
|* 07/10/2005: Removido o TACBrThreadTimer,  Daniel Sim�es de Almeida
|*    N�o apresenta vantagens em rela��o ao TTimer (Delphi), problemas quando
|*    o componente est� dentro de DLLs
|* 27/10/2005: Daniel Simoes de Almeida
|*    Desativada a TACBrThreadEnviaLPT, comentando o  $DEFINE ThreadEnviaLPT
|*    devido a Problemas como "Erro gravando em LPTx"....  TODO: Corrigir...
|* 22/11/2005: Daniel Simoes de Almeida
|*    modificado grava��o em Arquivos Texto para verificar se o arquivo j�
|*    existe e adcionar dados no final, ao inves de sempre sobrescreve-lo
|* 13/03/2006: Daniel Simoes de Almeida
|* - Permite modificar alguns parametros da porta serial mesmo com ela aberta.
|* - Modifica�oes em run-time nos parametros da serial n�o eram efetivadas na
|*   Synaser
******************************************************************************}

{$I ACBr.inc}

//{$DEFINE ThreadEnviaLPT}  { Use // no inicio dessa linha para desabilitar a Thread}

{$IFDEF LINUX}
   { Thread TACBrThreadEnviaLPT n�o funciona muito bem no Linux }
   { infelizmente, Strings grandes nao funcionam bem no LINUX usando a Thread}
  {$UNDEF ThreadEnviaLPT}
{$ENDIF}

Unit ACBrBase ;

interface
uses Classes, SysUtils, Contnrs, ACBrConsts,
     {$IFDEF COMPILER6_UP}
        Types
     {$ELSE}
        Windows, ACBrD5
     {$ENDIF}
     {$IFNDEF NOGUI}
       {$IFDEF VisualCLX}
          ,QDialogs
       {$ELSE}
          ,Dialogs
       {$ENDIF}
     {$ENDIF};
type

TACBrAboutInfo = (ACBrAbout);

{ ACBrComponente cont�m apenas a propriedade ACBrAbout }
TACBrComponent = class( TComponent )
  private
    fsAbout: TACBrAboutInfo;
  published
     property AboutACBr : TACBrAboutInfo read fsAbout write fsAbout
                           stored false ;
  end ;

{ Essa classe emula um TTimer, porem em uma Thread, evitando sobrecarregar
  o Application. Usada por ACBrLCB e ACBrDIS quando em modo CONSOLE, ou NOGUI }
TACBrThreadTimer = class(TThread)
  private
    fsOnTimer : TNotifyEvent;
    fsEnabled: Boolean;
    fsInterval: Integer;
    procedure SetEnabled(const Value: Boolean);
    procedure SetInterval(const Value: Integer);
  protected
    procedure ChamarEvento;
    procedure Execute; override;
  public
    constructor Create ;

    property OnTimer  : TNotifyEvent read fsOnTimer write fsOnTimer ;
    property Interval : Integer read fsInterval write SetInterval ;
    property Enabled : Boolean read fsEnabled write SetEnabled ;
  end;

{ TACBrInformacao - est� classe emula campos TField, permitindo montar listas
de campos quando necess�rio}
  TACBrInformacao = class
  private
    fInformacao : AnsiString;
    FNome: AnsiString;
    function GetAsDate : TDateTime;
    function GetAsFloat : Double;
    function GetAsInteger : Integer;
    function GetAsString: AnsiString;
    function GetAsTime : TDateTime;
    function GetAsTimeStamp : TDateTime;
    function GetAsTimeStampSQL : TDateTime;
    //procedure SetAsAnsiString(const AValue: AnsiString);
    procedure SetAsDate(const AValue : TDateTime);
    procedure SetAsFloat(const AValue : Double);
    procedure SetAsInteger(const AValue : Integer);
    procedure SetAsString(const AValue: AnsiString);
    procedure SetAsTime(const AValue : TDateTime);
    procedure SetAsTimeStamp(const AValue : TDateTime);
    procedure SetAsTimeStampSQL(const AValue : TDateTime);
  public
    property Nome       : AnsiString read FNome          write FNome;
    property AsString   : AnsiString read GetAsString    write SetAsString ;
    property AsDate     : TDateTime  read GetAsDate      write SetAsDate ;
    property AsTime     : TDateTime  read GetAsTime      write SetAsTime ;
    property AsTimeStamp: TDateTime  read GetAsTimeStamp write SetAsTimeStamp ;
    property AsTimeStampSQL: TDateTime  read GetAsTimeStampSQL write SetAsTimeStampSQL ;
    property AsInteger  : Integer    read GetAsInteger   write SetAsInteger ;
    property AsFloat    : Double     read GetAsFloat     write SetAsFloat ;
  end ;

  { TACBrInformacoes }

  TACBrInformacoes = class(TObjectList)
  private
    function GetItem(Index: Integer): TACBrInformacao;
    procedure SetItem(Index: Integer; const Value: TACBrInformacao);
    function GetFields(Index: String): TAcbrInformacao;
  public
    function Add: TACBrInformacao;
    function AddField(const ANome, AValor: String): TACBrInformacao;
    function FieldByName(const AName: String): TACBrInformacao;

    procedure SaveToFile( AFileName: String) ;
    procedure LoadFromFile( AFileName: String) ;

    property Items[Index: Integer]: TACBrInformacao read GetItem write SetItem;
    property Fields[Index: String]: TAcbrInformacao read GetFields; default;
  end;

procedure ACBrAboutDialog ;

implementation

Uses
  ACBrUtil, DateUtils, Math ;

procedure ACBrAboutDialog ;
  var Msg : String ;
begin
  {$IFDEF NOGUI}
      Msg := 'Componentes ACBr CONSOLE'+sLineBreak+
             'Automa��o Comercial Brasil'+sLineBreak+
             'http://acbr.sourceforge.net' ;
      Msg := ACBrStr(Msg) ;
      writeln( Msg )
  {$ELSE}
    {$IFDEF VisualCLX}
      Msg := 'Componentes <b>ACBr CLX</b><BR>'+
              'Automa��o Comercial Brasil<BR><BR>'+
              '<A HREF="http://acbr.sourceforge.net">'+
              'http://acbr.sourceforge.net</A><BR><BR>' ;
    {$ELSE}
      Msg := 'Componentes ACBr '+{$IFDEF FPC}'Lazarus/FPC'{$ELSE}'VCL'{$ENDIF}+#10+
             'Automa��o Comercial Brasil'+#10+#10+
             'http://acbr.sourceforge.net' ;
      Msg := ACBrStr( AnsiString( Msg ) ) ;
    {$ENDIF}

     MessageDlg(Msg ,mtInformation ,[mbOk],0) ;
 {$ENDIF}
end;


{------------------------------ TACBrThreadTimer ------------------------------}
constructor TACBrThreadTimer.Create ;
begin
  inherited Create( true );   { CreateSuspended }

  fsInterval := 0 ;
  fsEnabled  := false ;
  fsOnTimer  := nil ;
end;

procedure TACBrThreadTimer.Execute;
begin                              
  while not Terminated do
  begin
     Sleep( fsInterval );

     if fsEnabled and Assigned( fsOntimer ) then
      {$IFNDEF NOGUI}
        Synchronize( ChamarEvento ) 
      {$ELSE}
        fsOnTimer( self )
      {$ENDIF}
     else
        Sleep( 100 );
  end ;
end;

procedure TACBrThreadTimer.ChamarEvento;
begin
  fsOnTimer( self ) ;
end;

procedure TACBrThreadTimer.SetEnabled(const Value: Boolean);
begin
  if fsEnabled = Value then exit ;

  fsEnabled := Value;

  if Value then
  begin
    {$IFDEF DELPHI14_UP}
      if Suspended then Start ;
    {$ELSE}
      if Suspended then Resume ;
    {$ENDIF}
  end
  else
  begin
    {$IFNDEF NOGUI}
      {$IFDEF DELPHI12_UP}
        if not Suspended then Terminate ;
      {$ELSE}
        if not Suspended then Suspend ;
      {$ENDIF}
    {$ENDIF}
  end;
end;

procedure TACBrThreadTimer.SetInterval(const Value: Integer);
begin
  fsInterval := Value;
  if Value = 0 then
     Enabled := false ;
end;
{ TACBrInformacao }

function TACBrInformacao.GetAsDate : TDateTime;
var
   DataStr : String;
begin
  DataStr := String( OnlyNumber(AnsiString( Trim(String(fInformacao)) )) );

  try
     Result := EncodeDate( StrToInt(copy(DataStr,5,4)),
                           StrToInt(copy(DataStr,3,2)),
                           StrToInt(copy(DataStr,1,2)) ) ;
  except
     Result := 0 ;
  end;
end;

function TACBrInformacao.GetAsFloat : Double;
Var
  Info : String ;
begin
  Info := StringReplace( Trim(String(fInformacao)), ',','',[rfReplaceAll] );
  Info := StringReplace( Info             , '.','',[rfReplaceAll] );

  Result := StrToIntDef( Info ,0) / 100 ;
end;

function TACBrInformacao.GetAsInteger : Integer;
begin
  Result := StrToIntDef(Trim(String(fInformacao)),0);
end;

function TACBrInformacao.GetAsString: AnsiString;
begin
   Result := fInformacao ;
end;

function TACBrInformacao.GetAsTime : TDateTime;
var
   TimeStr : String;
begin
  TimeStr := OnlyNumber(AnsiString( Trim(String(fInformacao))) );

  try
     Result := EncodeTime( StrToInt(copy(TimeStr,1,2)),
                           StrToInt(copy(TimeStr,3,2)),
                           StrToInt(copy(TimeStr,5,2)), 0) ;
  except
     Result := 0 ;
  end;
end;

function TACBrInformacao.GetAsTimeStamp : TDateTime;
var
   DateTimeStr : String;
begin
  DateTimeStr := OnlyNumber(AnsiString( Trim(String(fInformacao))) );

  try
     Result := EncodeDateTime( YearOf(now),
                               StrToInt(copy(DateTimeStr,3,2)),
                               StrToInt(copy(DateTimeStr,1,2)),
                               StrToInt(copy(DateTimeStr,5,2)),
                               StrToInt(copy(DateTimeStr,7,2)),
                               StrToInt(copy(DateTimeStr,9,2)), 0) ;
  except
     Result := 0 ;
  end;
end;

function TACBrInformacao.GetAsTimeStampSQL : TDateTime;
var
   DateTimeStr : String;
begin
  DateTimeStr := OnlyNumber(AnsiString( Trim(String(fInformacao))) );

  try
     Result := EncodeDateTime( StrToInt(copy(DateTimeStr,1,4)),
                               StrToInt(copy(DateTimeStr,5,2)),
                               StrToInt(copy(DateTimeStr,7,2)),
                               StrToInt(copy(DateTimeStr,9,2)),
                               StrToInt(copy(DateTimeStr,11,2)),
                               StrToInt(copy(DateTimeStr,13,2)), 0) ;
  except
     Result := 0 ;
  end;
end;
{
procedure TACBrInformacao.SetAsAnsiString(const AValue: AnsiString);
begin
   fInformacao := AValue;
end;
}
procedure TACBrInformacao.SetAsDate(const AValue : TDateTime);
begin
  if AValue = 0 then
     fInformacao := ''
  else
     fInformacao := AnsiString( FormatDateTime('DDMMYYYY',AValue) );
end;

procedure TACBrInformacao.SetAsFloat(const AValue : Double);
begin
  if AValue = 0 then
     fInformacao := ''
  else
   begin
     fInformacao := AnsiString( IntToStr(Trunc(SimpleRoundTo( AValue * 100 ,0))) );
     if Length(fInformacao) < 3 then
        fInformacao := PadR(fInformacao,3,'0') ;
   end ;
end;

procedure TACBrInformacao.SetAsInteger(const AValue : Integer);
begin
  if AValue = 0 then
     fInformacao := ''
  else
     fInformacao := AnsiString( IntToStr( AValue ) );
end;

procedure TACBrInformacao.SetAsString(const AValue: AnsiString);
begin
   fInformacao := AValue;
end;

procedure TACBrInformacao.SetAsTime(const AValue : TDateTime);
begin
  if AValue = 0 then
     fInformacao := ''
  else
     fInformacao := AnsiString(FormatDateTime('HHNNSS', AValue));
end;

procedure TACBrInformacao.SetAsTimeStamp(const AValue : TDateTime);
begin
  if AValue = 0 then
     fInformacao := ''
  else
     fInformacao := AnsiString(FormatDateTime('DDMMHHNNSS', AValue));
end;

procedure TACBrInformacao.SetAsTimeStampSQL(const AValue : TDateTime);
begin
  if AValue = 0 then
     fInformacao := ''
  else
     fInformacao := AnsiString(FormatDateTime('YYYYMMDDHHNNSS', AValue));
end;

{ TACBrInformacoes }

function TACBrInformacoes.AddField(const ANome,
  AValor: String): TACBrInformacao;
begin
  try
     Result := FieldByName(ANome);
     Result.AsString := AValor;
  except
     Result := Self.Add;
     with Result do
     begin
       Nome     := AnsiString(ANome);
       AsString := AnsiString(AValor);
     end;
  end;
end;

function TACBrInformacoes.FieldByName(const AName: String): TACBrInformacao;
var
  I: Integer;
begin
  Result := nil;
  for I := 0 to Self.Count - 1 do
  begin
    if AnsiSameText(String(Self.Items[I].Nome), String(AName)) then
    begin
      Result := Self.Items[I];
      Exit;
    end;
  end;

  if Result = nil then
    raise Exception.CreateFmt('Resposta "%s" n�o encontrada.', [AName]);
end;

procedure TACBrInformacoes.SaveToFile(AFileName : String) ;
var
  I  : Integer ;
  SL : TStringList ;
begin
  SL := TStringList.Create;
  try
    For I := 0 to Count-1 do
       SL.Values[ Items[I].Nome ] := Items[I].AsString;

    SL.SaveToFile( AFileName );
  finally
    SL.Free;
  end ;
end ;

procedure TACBrInformacoes.LoadFromFile(AFileName : String) ;
var
  I  : Integer ;
  SL : TStringList ;
begin
  SL := TStringList.Create;
  try
    Clear;

    SL.LoadFromFile( AFileName );
    For I := 0 to SL.Count-1 do
{$IFDEF COMPILER7_UP}
       AddField( SL.Names[ I ], SL.ValueFromIndex[ I ] );
{$ELSE}
       AddField( SL.Names[ I ], SL.Values[ SL.Names[ I ] ] );
{$ENDIF}
  finally
    SL.Free;
  end ;
end ;

function TACBrInformacoes.GetFields(Index: String): TAcbrInformacao;
begin
  Result := FieldByName(Index);
end;

function TACBrInformacoes.GetItem(
  Index: Integer): TACBrInformacao;
begin
  Result := TACBrInformacao(inherited Items[Index]);
end;

function TACBrInformacoes.Add: TACBrInformacao;
begin
  Result := TACBrInformacao.Create;
  inherited Add(Result);
end;

procedure TACBrInformacoes.SetItem(Index: Integer;
  const Value: TACBrInformacao);
begin
  Put(Index, Value);
end;

end.

