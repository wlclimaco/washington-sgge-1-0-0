{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
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
|* 24/10/2008: Primeira Versao
|*    Daniel Simoes de Almeida
******************************************************************************}

{$I ACBr.inc}

unit ACBrSocket;

interface

uses SysUtils, Classes,
     blcksock, synsock, httpsend, ssl_openssl,  {Units da Synapse}
     {$IFDEF MSWINDOWS} windows, wininet, {$ENDIF}  { Units para a auto-detec��o de Proxy }
     ACBrBase ;

type

TACBrTCPServer = class ;

{ Evento disparada quando Conecta }
TACBrTCPServerConecta = procedure( const TCPBlockSocket : TTCPBlockSocket;
   var Enviar : AnsiString ) of object ;

{ Evento disparada quando DesConecta }
TACBrTCPServerDesConecta = procedure( const TCPBlockSocket : TTCPBlockSocket;
   Erro: Integer; ErroDesc : String ) of object ;

{ Evento disparado quando recebe dados }
TACBrTCPServerRecive = procedure( const TCPBlockSocket : TTCPBlockSocket;
   const Recebido : AnsiString; var Enviar : AnsiString) of object ;

{ TACBrTCPServerDaemon }

TACBrTCPServerDaemon = class(TThread)
  private
    fErroBind : Integer ;
    fsSock : TTCPBlockSocket;
    fsACBrTCPServer : TACBrTCPServer ;

  protected
    property ACBrTCPServer : TACBrTCPServer read fsACBrTCPServer ;

  public
    Constructor Create( const ACBrTCPServer : TACBrTCPServer );
    Destructor Destroy; override;
    procedure Execute; override;

    property TCPBlockSocket : TTCPBlockSocket read fsSock ;
    property ErroBind : Integer read fErroBind ;
  end;

TACBrTCPServerThread = class(TThread)
  private
    fsACBrTCPServerDaemon : TACBrTCPServerDaemon ;
    fsSock   : TTCPBlockSocket;
    fsStrRcv, fsStrToSend : AnsiString ;
    fsCSock  : TSocket;
    fsErro   : Integer ;
    function GetActive: Boolean;
  protected
    procedure CallOnRecebeDados ;
    procedure CallOnConecta ;
    procedure CallOnDesConecta ;
  public
    Constructor Create(ClientSocket: TSocket; ACBrTCPServerDaemon : TACBrTCPServerDaemon);
    procedure Execute; override;

    property Active : Boolean read GetActive ;
    property TCPBlockSocket : TTCPBlockSocket read fsSock ;
  end;


{ Componente ACBrTCPServer - Servidor TCP muito simples }

{ TACBrTCPServer }

TACBrTCPServer = class( TACBrComponent )
  private
    { Propriedades do Componente ACBrTCPServer }
    fsACBrTCPServerDaemon : TACBrTCPServerDaemon ;
    fsTimeOut: Integer;
    fsIP: String;
    fsPort: String;
    fsThreadList : TThreadList ;
    fsOnConecta: TACBrTCPServerConecta;
    fsOnRecebeDados: TACBrTCPServerRecive;
    fsOnDesConecta: TACBrTCPServerDesConecta;
    fsTerminador : String;
    fs_Terminador: AnsiString;
    function GetTCPBlockSocket: TTCPBlockSocket ;
    procedure SetAtivo(const Value: Boolean);
    procedure SetIP(const Value: String);
    procedure SetPort(const Value: String);
    procedure SetTerminador( const AValue: String) ;
    procedure SetTimeOut(const Value: Integer);

    procedure VerificaAtivo ;
    function GetAtivo: Boolean;
  public
    constructor Create(AOwner: TComponent); override;
    Destructor Destroy  ; override;

    procedure Ativar ;
    procedure Desativar ;
    procedure EnviarString(const AString : AnsiString; NumConexao : Integer = -1 ) ;
    procedure Terminar(NumConexao : Integer = -1 ) ;

    property Ativo : Boolean read GetAtivo write SetAtivo ;
    property ThreadList : TThreadList read fsThreadList ;
    property StrTerminador : AnsiString  read fs_Terminador  ;

     { Instancia do Componente TACBrTCPServerDaemon }
    property ACBrTCPServerDaemon : TACBrTCPServerDaemon read fsACBrTCPServerDaemon ;
    property TCPBlockSocket : TTCPBlockSocket read GetTCPBlockSocket ;
  published
    property IP         : String  read fsIP         write SetIP;
    property Port       : String  read fsPort       write SetPort ;
    property TimeOut    : Integer read fsTimeOut    write SetTimeOut
       default 5000 ;
    property Terminador : String  read fsTerminador write SetTerminador ;

    property OnConecta     : TACBrTCPServerConecta    read  fsOnConecta
                                                      write fsOnConecta ;
    property OnDesConecta  : TACBrTCPServerDesConecta read  fsOnDesConecta
                                                      write fsOnDesConecta ;
    property OnRecebeDados : TACBrTCPServerRecive     read  fsOnRecebeDados
                                                      write fsOnRecebeDados ;
end ;

{ TACBrHTTP }

TACBrOnAntesAbrirHTTP = procedure( var AURL : String ) of object ;

EACBrHTTPError = class( Exception ) ;

TACBrHTTP = class( TACBrComponent )
  private
    fHTTPSend : THTTPSend ;
    fOnAntesAbrirHTTP : TACBrOnAntesAbrirHTTP ;
    fRespHTTP   : TStringList ;
    FParseText: Boolean;
    function GetProxyHost : string ;
    function GetProxyPass : string ;
    function GetProxyPort : string ;
    function GetProxyUser : string ;
    procedure SetProxyHost(const AValue : string) ;
    procedure SetProxyPass(const AValue : string) ;
    procedure SetProxyPort(const AValue : string) ;
    procedure SetProxyUser(const AValue : string) ;
  public
    constructor Create( AOwner : TComponent ) ; override ;
    destructor Destroy ; override ;

    function AjustaParam(AParam : String) : String ;
    procedure HTTPGet( AURL : String) ; virtual ;
    Procedure HTTPPost( AURL : String ) ; virtual ;
    procedure HTTPMethod( Method, AURL : String ); virtual ;
    function GetHeaderValue( AValue : String ) : String ;

    procedure LerConfiguracoesProxy; 

    property HTTPSend  : THTTPSend read fHTTPSend ;
    property RespHTTP  : TStringList read fRespHTTP ;
  published
    property ProxyHost : string read GetProxyHost write SetProxyHost ;
    property ProxyPort : string read GetProxyPort write SetProxyPort ;
    property ProxyUser : string read GetProxyUser write SetProxyUser ;
    property ProxyPass : string read GetProxyPass write SetProxyPass ;
    property ParseText : Boolean read FParseText write FParseText default False;

    property OnAntesAbrirHTTP : TACBrOnAntesAbrirHTTP
       read fOnAntesAbrirHTTP write fOnAntesAbrirHTTP ;
end ;

implementation

Uses ACBrUtil, synacode {$IFNDEF NOGUI},Controls, Forms {$ENDIF} ;

{ TACBrTCPServerDaemon }

constructor TACBrTCPServerDaemon.Create( const ACBrTCPServer : TACBrTCPServer );
begin
  fsACBrTCPServer := ACBrTCPServer ;
  fsSock    := TTCPBlockSocket.create ;
  fErroBind := -9999;   // Ainda nao inicializado

  inherited create(False) ;
  FreeOnTerminate := False ;
end;

destructor TACBrTCPServerDaemon.Destroy;
begin
  fsSock.Free ;
end;

procedure TACBrTCPServerDaemon.Execute;
var
  ClientSock : TSocket;
begin
  with fsSock do
  begin
//   CreateSocket ;
     SetLinger(True,10) ;
     Bind( fsACBrTCPServer.IP , fsACBrTCPServer.Port ) ;
     if LastError = 0 then
        Listen ;

     fErroBind := LastError;

     while (fErroBind = 0) do
     begin

        if Terminated or (not Assigned(fsSock)) then
           break ;

        if CanRead( 1000 ) then
        begin
           ClientSock := Accept;
           if lastError = 0 then
              TACBrTCPServerThread.create(ClientSock, Self);
        end;
     end ;
  end;
end;

{ TACBrTCPServerThread }

Constructor TACBrTCPServerThread.Create(ClientSocket: TSocket;
   ACBrTCPServerDaemon : TACBrTCPServerDaemon);
begin
  inherited create(false);

  fsACBrTCPServerDaemon := ACBrTCPServerDaemon ;
  fsCSock  := ClientSocket ;
  FreeOnTerminate := True ;
end;

procedure TACBrTCPServerThread.Execute;
begin
  fsSock := TTCPBlockSocket.create;
  try
     fsSock.Socket := fsCSock ;
     fsSock.GetSins;
     with fsSock do
     begin
        fsStrToSend := '' ;
        fsErro      := 0 ;

        {$IFNDEF NOGUI}
         Synchronize( CallOnConecta );
        {$ELSE}
         CallOnConecta ;
        {$ENDIF}

        if fsStrToSend <> '' then
        begin
           SendString( fsStrToSend );
           fsErro := LastError ;
        end ;

        while fsErro = 0 do
        begin
           if terminated then
           begin
              fsErro := -1 ;
              break;
           end ;

           if not Assigned( fsACBrTCPServerDaemon ) then  // O Daemon ainda existe ?
           begin
              fsErro := -1 ;
              break ;
           end ;

           if fsACBrTCPServerDaemon.Terminated then   // O Daemon est� rodando ?
           begin
              fsErro := -1 ;
              break ;
           end ;

           // Se n�o tem nada para ler, re-inicia o loop //
           if not fsSock.CanRead( 1000 ) then
              Continue ;

           // Se tem Terminador, l� at� chagar o Terminador //
           if fsACBrTCPServerDaemon.ACBrTCPServer.StrTerminador <> '' then
              fsStrRcv := RecvTerminated(  fsACBrTCPServerDaemon.ACBrTCPServer.TimeOut,
                                           fsACBrTCPServerDaemon.ACBrTCPServer.StrTerminador )
           else
              fsStrRcv := RecvPacket( fsACBrTCPServerDaemon.ACBrTCPServer.TimeOut ) ;

           fsErro := LastError ;
           if fsErro <> 0 then
              break;

           if Assigned( fsACBrTCPServerDaemon.ACBrTCPServer.OnRecebeDados ) then
              {$IFNDEF NOGUI}
               Synchronize( CallOnRecebeDados );
              {$ELSE}
               CallOnRecebeDados ;
              {$ENDIF}

           if fsStrToSend <> '' then
           begin
              SendString( fsStrToSend );
              fsErro := LastError ;
           end ;
        end;

        // Chama o evento de Desconex�o...
        {$IFNDEF NOGUI}
         Synchronize( CallOnDesConecta );
        {$ELSE}
         CallOnDesConecta ;
        {$ENDIF}
     end;
  finally
     fsSock.CloseSocket ;
     fsSock.Free;
  end;
end;

procedure TACBrTCPServerThread.CallOnRecebeDados;
begin
  fsStrToSend := '' ;
  fsACBrTCPServerDaemon.ACBrTCPServer.OnRecebeDados( fsSock, fsStrRcv, fsStrToSend ) ;
end;

procedure TACBrTCPServerThread.CallOnConecta;
begin
  // Adiciona essa Thread na Lista de Threads
  fsACBrTCPServerDaemon.ACBrTCPServer.ThreadList.Add( Self ) ;

  // Chama o Evento, se estiver atribuido
  if Assigned( fsACBrTCPServerDaemon.ACBrTCPServer.OnConecta ) then
  begin
     fsStrToSend := '' ;
     fsACBrTCPServerDaemon.ACBrTCPServer.OnConecta( fsSock, fsStrToSend ) ;
  end ;
end;

procedure TACBrTCPServerThread.CallOnDesConecta;
 Var ErroDesc : String ;
begin
  // Remove essa Thread da Lista de Threads
  fsACBrTCPServerDaemon.ACBrTCPServer.ThreadList.Remove( Self ) ;

  // Chama o Evento, se estiver atribuido
  if Assigned( fsACBrTCPServerDaemon.ACBrTCPServer.OnDesConecta ) then
  begin
     ErroDesc := fsSock.GetErrorDesc( fsErro ) ;
     fsACBrTCPServerDaemon.ACBrTCPServer.OnDesConecta( fsSock, fsErro, ErroDesc ) ;
  end ;
end;

function TACBrTCPServerThread.GetActive: Boolean;
begin
  Result := (not self.Terminated) and (fsSock.LastError = 0) ;
end;


{ TACBrTCPServer }

constructor TACBrTCPServer.Create(AOwner: TComponent);
begin
  inherited create( AOwner );

  fsIP   := '0.0.0.0' ;
  fsPort := '0' ;
  fsTimeOut := 5000 ;
  fsTerminador  := '' ;
  fs_Terminador := '' ;

  fsACBrTCPServerDaemon := nil ;
  fsThreadList := TThreadList.Create ;
end;

destructor TACBrTCPServer.Destroy;
begin
  Desativar;
  fsThreadList.Free ;

  inherited Destroy;
end;

function TACBrTCPServer.GetAtivo: Boolean;
begin
  Result := Assigned( fsACBrTCPServerDaemon ) ;
end;

procedure TACBrTCPServer.SetAtivo(const Value: Boolean);
begin
  if Value then
     Ativar
  else
     Desativar ;
end;

function TACBrTCPServer.GetTCPBlockSocket: TTCPBlockSocket ;
begin
   Result := nil ;
   if Assigned( fsACBrTCPServerDaemon ) then
      Result := fsACBrTCPServerDaemon.TCPBlockSocket ;
end;

procedure TACBrTCPServer.Ativar;
Var
  Erro : Integer ;
  ErroDesc : String ;
begin
  if Assigned( fsACBrTCPServerDaemon ) then
     exit ;

  fsACBrTCPServerDaemon := TACBrTCPServerDaemon.Create( Self );
  // Aguarda termino do Bind //
  while fsACBrTCPServerDaemon.ErroBind = -9999 do
     sleep(100) ;

  Erro := fsACBrTCPServerDaemon.TCPBlockSocket.LastError ;
  ErroDesc := fsACBrTCPServerDaemon.TCPBlockSocket.LastErrorDesc;

  if Erro <> 0 then
  begin
     Desativar;
     raise Exception.Create( ACBrStr(AnsiString( 'Erro: '+IntToStr(Erro)+' - '+ErroDesc+sLineBreak+
                                     'N�o foi poss�vel criar servi�o na porta: '+Port ))) ;
  end ;
end;

procedure TACBrTCPServer.Desativar;
 Var I : Integer ;
begin
  with fsThreadList.LockList do
  try
     for I := 0 to Count-1 do
        if Assigned( Items[I] ) then
           TACBrTCPServerThread(Items[I]).Terminate;
  finally
     fsThreadList.UnlockList;
  end ;
  fsThreadList.Clear ;

  if not Assigned( fsACBrTCPServerDaemon )then
     exit ;

  fsACBrTCPServerDaemon.Terminate ;
  fsACBrTCPServerDaemon.WaitFor ;

  FreeAndNil( fsACBrTCPServerDaemon ) ;
end;

procedure TACBrTCPServer.SetIP(const Value: String);
begin
  VerificaAtivo ;
  fsIP := Value;
end;

procedure TACBrTCPServer.SetPort(const Value: String);
begin
  VerificaAtivo ;
  fsPort := Value;
end;

procedure TACBrTCPServer.SetTerminador( const AValue: String) ;
begin
  VerificaAtivo ;
  fsTerminador  := AValue;
  fs_Terminador := TraduzComando( AnsiString( fsTerminador ) ) ;
end;

procedure TACBrTCPServer.SetTimeOut(const Value: Integer);
begin
  VerificaAtivo ;
  fsTimeOut := Value;
end;


procedure TACBrTCPServer.VerificaAtivo;
begin
  if Ativo then
     raise Exception.Create( ACBrStr('N�o � poss�vel modificar as propriedades com '+
                             'o componente Ativo') );
end;

procedure TACBrTCPServer.EnviarString(const AString : AnsiString;
   NumConexao : Integer = -1 ) ;
Var
  I : Integer ;
begin
  if not Ativo then
     raise Exception.Create(ACBrStr('Componente ACBrTCPServer n�o est� ATIVO'));

  with fsThreadList.LockList do
  try
     if NumConexao < 0 then
      begin
        For I := 0 to Count-1 do
           TACBrTCPServerThread(Items[I]).TCPBlockSocket.SendString( AString );
      end
     else
      begin
        if (NumConexao >= Count) then
           raise Exception.Create(ACBrStr('Numero de conex�o inexistente: ')+IntToStr(NumConexao));

        TACBrTCPServerThread(Items[NumConexao]).TCPBlockSocket.SendString( AString );
      end ;
  finally
     fsThreadList.UnlockList;
  end ;
end;

procedure TACBrTCPServer.Terminar(NumConexao : Integer) ;
Var
  I : Integer ;
begin
  with fsThreadList.LockList do
  try
     if NumConexao < 0 then
      begin
        For I := 0 to Count-1 do
        begin
           TACBrTCPServerThread(Items[I]).TCPBlockSocket.CloseSocket ;
           TACBrTCPServerThread(Items[I]).Terminate ;
        end ;
      end
     else
      begin
        if (NumConexao >= Count) then
           raise Exception.Create(ACBrStr('Numero de conex�o inexistente: ')+IntToStr(NumConexao));

        TACBrTCPServerThread(Items[NumConexao]).TCPBlockSocket.CloseSocket;
        TACBrTCPServerThread(Items[NumConexao]).Terminate;
      end ;
  finally
     fsThreadList.UnlockList;
  end ;
end ;

{ TACBrHTTP }

constructor TACBrHTTP.Create(AOwner : TComponent) ;
begin
  inherited Create( AOwner ) ;

  fHTTPSend := THTTPSend.Create;
  fRespHTTP   := TStringList.Create;
  fOnAntesAbrirHTTP := nil ;
end ;

destructor TACBrHTTP.Destroy ;
begin
  fHTTPSend.Free;
  fRespHTTP.Free;

  inherited Destroy;
end ;

function TACBrHTTP.AjustaParam(AParam: String): String;
begin
  Result := Trim( AParam ) ;

  if (Result <> '') then
  begin
     Result := String( ACBrStrToAnsi( Result ) ) ;
     Result := String( EncodeURLElement( AnsiString( Result ) ) ) ;
  end ;
end ;

procedure TACBrHTTP.HTTPGet(AURL: String);
begin
  HTTPSend.Clear;
  HTTPMethod( 'GET', AURL );
end ;

Procedure TACBrHTTP.HTTPPost( AURL : String ) ;
begin
  HTTPMethod( 'POST', AURL ) ;
end ;

procedure TACBrHTTP.HTTPMethod(Method, AURL: String);
var
  OK : Boolean ;
  {$IFNDEF NOGUI}
   OldCursor : TCursor ;
  {$ENDIF}
   CT : String ;
begin
  {$IFNDEF NOGUI}
   OldCursor := Screen.Cursor ;
   Screen.Cursor := crHourGlass;
  {$ENDIF}
  try
    RespHTTP.Clear;

    {$IFDEF UNICODE}
     HTTPSend.Headers.Add('Accept-Charset: utf-8;q=*;q=0.7') ;
    {$ENDIF}

    if Assigned( OnAntesAbrirHTTP ) then
       OnAntesAbrirHTTP( AURL ) ;

    // DEBUG //
    //HTTPSend.Document.SaveToFile( 'c:\temp\HttpSend.txt' );

    HTTPSend.HTTPMethod(Method, AURL);
    OK := HTTPSend.ResultCode = 200;
    RespHTTP.LoadFromStream( HTTPSend.Document ) ;

    // DEBUG //
    //WriteToTXT( 'c:\temp\HttpResp.txt', RespHTTP.Text );
    //WriteToTXT( 'c:\temp\HeaderResp.txt', HTTPSend.Headers.Text );

    if ParseText then
      RespHTTP.Text := ACBrUtil.ParseText( RespHTTP.Text, True, False );

    // Verifica se a Resposta est� em ANSI //
    CT := LowerCase( GetHeaderValue('Content-Type:') );
    RespHTTP.Text := DecodeToSys( RespHTTP.Text, (pos('utf-8', CT) > 0) );

    if not OK then
       raise EACBrHTTPError.Create( 'Erro HTTP: '+IntToStr(HTTPSend.ResultCode)+' '+
                                     HTTPSend.ResultString + sLineBreak +
                                     'URL: '+AURL + sLineBreak + sLineBreak +
                                     'Resposta HTTP:' + sLineBreak +
                                     String(AjustaLinhas( AnsiString(RespHTTP.Text), 80, 20) )) ;
  finally
    {$IFNDEF NOGUI}
     Screen.Cursor := OldCursor;
    {$ENDIF}
  end;
end;

function TACBrHTTP.GetHeaderValue(AValue : String) : String ;
var
  I : Integer ;
  LinhaHeader : string ;
begin
  Result := '' ;
  I      := 0 ;

  while (Result = '') and (I < HTTPSend.Headers.Count) do
  begin
     LinhaHeader := HTTPSend.Headers[I];

     if (pos(AValue, LinhaHeader) = 1) then
        Result := copy(LinhaHeader, Length(AValue)+1, Length(LinhaHeader) ) ;

     Inc( I ) ;
  end ;
end ;

procedure TACBrHTTP.LerConfiguracoesProxy;
{$IFDEF MSWINDOWS}
var
  Len: DWORD;
  i, j: Integer;

  Server, Port, User, Password: String;

  function GetProxyServer: String;
  var
     ProxyInfo: PInternetProxyInfo;
  begin
     Result := '';
     Len    := 0;
     if not InternetQueryOption(nil, INTERNET_OPTION_PROXY, nil, Len) then
     begin
        if GetLastError = ERROR_INSUFFICIENT_BUFFER then
        begin
           GetMem(ProxyInfo, Len);
           try
              if InternetQueryOption(nil, INTERNET_OPTION_PROXY, ProxyInfo, Len) then
              begin
                 if ProxyInfo^.dwAccessType = INTERNET_OPEN_TYPE_PROXY then
                    Result := String(ProxyInfo^.lpszProxy);
              end;
           finally
              FreeMem(ProxyInfo);
           end;
        end;
     end;
  end;

  function GetOptionString(Option: DWORD): String;
  begin
     Len := 0;
     if not InternetQueryOption(nil, Option, nil, Len) then
     begin
        if GetLastError = ERROR_INSUFFICIENT_BUFFER then
        begin
           SetLength(Result, Len);
           if InternetQueryOption(nil, Option, Pointer(Result), Len) then
              Exit;
        end;
     end;

     Result := '';
  end;

begin
  Port     := '';
  Server   := GetProxyServer;
  User     := GetOptionString(INTERNET_OPTION_PROXY_USERNAME);
  Password := GetOptionString(INTERNET_OPTION_PROXY_PASSWORD);

  if Server <> '' then
  begin
     i := Pos('http=', Server);
     if i > 0 then
     begin
        Delete(Server, 1, i+5);
        j := Pos(';', Server);
        if j > 0 then
           Server := Copy(Server, 1, j-1);
     end;

     i := Pos(':', Server);
     if i > 0 then
     begin
        Port   := Copy(Server, i+1, MaxInt);
        Server := Copy(Server, 1, i-1);
     end;
  end;

  ProxyHost := Server;
  ProxyPort := Port;
  ProxyUser := User;
  ProxyPass := Password;
end;
{$ELSE}
Var
  Arroba, DoisPontos, Barras : Integer ;
  http_proxy, Password, User, Server, Port : String ;
begin
{ http_proxy=http://user:password@proxy:port/
  http_proxy=http://proxy:port/                    }

  http_proxy := Trim(GetEnvironmentVariable( 'http_proxy' )) ;
  if http_proxy = '' then exit ;

  if RightStr(http_proxy,1) = '/' then
     http_proxy := copy( http_proxy, 1, Length(http_proxy)-1 );

  Barras := pos('//', http_proxy);
  if Barras > 0 then
     http_proxy := copy( http_proxy, Barras+2, Length(http_proxy) ) ;

  Arroba     := pos('@', http_proxy) ;
  DoisPontos := pos(':', http_proxy) ;
  Password   := '' ;
  User       := '' ;

  if (Arroba > 0) then
  begin
     if (DoisPontos < Arroba) then
        Password := copy( http_proxy, DoisPontos+1, Arroba-DoisPontos-1 )
     else
        DoisPontos := Arroba;

     User := copy( http_proxy, 1, DoisPontos-1) ;

     http_proxy := copy( http_proxy, Arroba+1, Length(http_proxy) );
  end ;

  DoisPontos := pos(':', http_proxy+':') ;

  Server := copy( http_proxy, 1, DoisPontos-1) ;
  Port   := copy( http_proxy, DoisPontos+1, Length(http_proxy) );

  ProxyHost := Server;
  ProxyPort := Port;
  ProxyUser := User;
  ProxyPass := Password;
end ;
{$ENDIF}

function TACBrHTTP.GetProxyHost : string ;
begin
  Result := fHTTPSend.ProxyHost;
end;

function TACBrHTTP.GetProxyPass : string ;
begin
  Result := fHTTPSend.ProxyPass;
end;

function TACBrHTTP.GetProxyPort : string ;
begin
  Result := fHTTPSend.ProxyPort;
end;

function TACBrHTTP.GetProxyUser : string ;
begin
  Result := fHTTPSend.ProxyUser;
end;

procedure TACBrHTTP.SetProxyHost(const AValue : string) ;
begin
  fHTTPSend.ProxyHost := AValue;
end;

procedure TACBrHTTP.SetProxyPass(const AValue : string) ;
begin
  fHTTPSend.ProxyPass := AValue;
end;

procedure TACBrHTTP.SetProxyPort(const AValue : string) ;
begin
  fHTTPSend.ProxyPort := AValue;
end;

procedure TACBrHTTP.SetProxyUser(const AValue : string) ;
begin
  fHTTPSend.ProxyUser := AValue;
end;

end.
