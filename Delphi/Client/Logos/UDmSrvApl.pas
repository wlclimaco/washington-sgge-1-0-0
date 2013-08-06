unit UDmSrvApl;

interface

uses
  SysUtils, Classes, DBXDataSnap, DBXCommon, DBClient, DSConnect, DB, SqlExpr, Dialogs,
  BrvString, Forms, ExtCtrls, BrvIP, Controls, BrvDicionario, Windows;

type
  TDmSrvApl = class(TDataModule)
    PvcSDmSis: TDSProviderConnection;
    SrvAplica: TSQLConnection;
    BrvlIP: TBrvlIP;
    BrvDicionario: TBrvDicionario;
    PvcSDmTra: TDSProviderConnection;
    SrvImpres: TSQLConnection;
    PvcSDmCon: TDSProviderConnection;
    PvcSDmWms: TDSProviderConnection;
    procedure DataModuleCreate(Sender: TObject);
  private
    procedure AtualizarServidorAnterior(pDsLisSer: String);
    function ConectarEndereco(pNmServer, pNoIp, pNoPorta: String;
                                       var pStSerApl: String; pSqlConnec : TSqlConnection): Boolean;
    function ListaServidoresAnterior: String;
    function DesCriptografarCodigoAcesso(pNrClient: String): Integer;
    function IsDebugged: Boolean;
    { Private declarations }
  public
    { Public declarations }
    gNrClient : Integer; // numero da conexão com servidor de aplicação
    gDsCreden : String;  // numero da conexão com servidor de aplicação criptografado
    gNrConTCP : String;  // número da conexao com servidor de conexões
    gIpConTCP : String;  // número do endereço ip cliente (BrIp pega o primeiro não o conectado);
    gNmSerApl : String;  // nome do servidor de aplicação em que está conectado

    gNrCliImp : Integer; // numero da conexão com servidor de impressão
    gDsCreImp : String;  // numero da conexão com servidor de impressão criptografado
    gNrImpTCP : String;  // ok // número da conexao com servidor de conexões (para impressão)
    gIpImpTCP : String;  // ok // número do endereço ip cliente (BrIp pega o primeiro não o conectado);
    gNmSerImp : String;  // nome do servidor de aplicação em que está conectado

    procedure InicializarControleImpressao;
    function  ConectaServidorAplicacao(pDsLisSer : String) : Integer;
    procedure InformarEntradaCliente(pSnConIni : Boolean; pTpServer : String);
    procedure InformarClienteAutenticado(pTpServer : String);
    function  UsuarioAutenticado : Boolean;
    function  SelecionaEmpresa: Boolean;
    function ConectarServidorDataSnap(pDsLisSer: String; var pStSerApl: String;
                                      pSqlConnec : TSqlConnection;
                                  var pNmSerCon : String): Boolean;
    function AtualizarAplicacaoCliente : Boolean;

    Procedure GravaLogScreen(pCdUsuari : Integer;
                             pVrLogScr : Variant;
                             pNmFormul : String;
                             pDsCreden : String;
                             pDsPath   : String;
                             pConexao  : TSQLConnection);
  end;

var
  DmSrvApl: TDmSrvApl;


implementation

{$R *.dfm}

uses UClaSrv, UCai0001, UCai0009, UClaImp, UCai0011, UGravaLogScreen;

function TDmSrvApl.ConectaServidorAplicacao(pDsLisSer: String) : Integer;
var lDsStatus : String;
    lStSerApl : String;
begin
      Result := 0; /// não conectou
      lDsStatus := Copy(pDsLisSer, 1, 3);
      Delete(pDsLisSer, 1, 3);

      if lDsStatus = 'OK;' then
      begin
            lStSerApl := '';
            if ConectarServidorDataSnap(pDsLisSer, lStSerApl, SrvAplica,
                                        DmSrvApl.gNmSerApl) then
            begin
                  Result := 1; // conectou
            end;

            if Result = 1 then
            begin
                  AtualizarServidorAnterior(pDsLisSer);
            end;
      end else
      begin
            if lDsStatus = 'ER;' then
            begin
                  // não conseguiu conexão com servidor de conexões
                  lStSerApl := pDsLisSer;
                  if ConectarServidorDataSnap(ListaServidoresAnterior, lStSerApl,
                                              SrvAplica, DmSrvApl.gNmSerApl) then
                  begin
                        Result := 1; // conectou
                  end;
            end else
            begin
                  // conseguiu conexão com servidor de aplicações, mas retornou uma
                  // lista vazia
                  lStSerApl := pDsLisSer;
                  Result    := 0 // não conectou
            end;
      end;

      if Result = 0 then
      begin
            if lStSerApl <> '' then
            begin
                  if MessageDlg(
                         'Não foi possível conectar ao servidor de aplicações.' + #13#13 +
                         lStSerApl + #13#13 +
                         'Deseja tentar novamente?', mtConfirmation, [mbYes, mbNo], 0) =
                                                                                IdNo then
                  begin
                        Result := 2;
                  end;
            end;
      end;
end;

function TDmSrvApl.ListaServidoresAnterior : String;
var lIniFile  : TStrings;
    lDsCaminh : String;
begin
      try
          lDsCaminh := Application.ExeName;
          lDsCaminh := ExtractFileDir(lDsCaminh) + '\Logos_Apl.ini';

          lIniFile := TStringList.Create;
          lIniFile.LoadFromFile(lDsCaminh);
          Result   := lIniFile.Text;
      finally
          FreeAndNil(lIniFile);
      end;
end;

function TDmSrvApl.UsuarioAutenticado : Boolean;
begin
      Cai0001 := TCai0001.Create(Self);

      if Cai0001.ShowModal = MrOk then
      begin
            Result := True;
      end else
      begin
            Result := False;
      end;
end;

function TDmSrvApl.SelecionaEmpresa : Boolean;
begin
      Cai0009 := TCai0009.Create(Self);

      if Cai0009.ShowModal = MrOk then
      begin
            Result := True;
      end else
      begin
            Result := False;
      end;
end;

procedure TDmSrvApl.AtualizarServidorAnterior(pDsLisSer : String);
var lIniFile  : TStrings;
    lDsCaminh : String;
begin
      try
          lDsCaminh := Application.ExeName;
          lDsCaminh := ExtractFileDir(lDsCaminh) + '\Logos_Apl.ini';

          lIniFile := TStringList.Create;
          lIniFile.Text := pDsLisSer;
          lIniFile.SaveToFile(lDsCaminh);
      finally
          FreeAndNil(lIniFile);
      end;
end;

function TDmSrvApl.ConectarServidorDataSnap(pDsLisSer : String;
                                        var pStSerApl : String;
                                            pSqlConnec : TSqlConnection;
                                        var pNmSerCon : String) : Boolean;
var lStlServer : TStrings;
    lNrServer  : Integer;
    lBrvString : TBrvString;
begin
      lStlServer := TStringList.Create;
      lBrvString := TBrvString.Create(Self);

      try
          Result          := False;
          lStlServer.Text := pDsLisSer;
          lNrServer       := 0;

          while (not Result) and (lNrServer < lStlServer.Count) do
          begin
                if Trim(lStlServer.Strings[lNrServer]) <> '' then
                begin
                      lBrvString.Split(lStlServer.Strings[lNrServer], '%%');

                      Result := ConectarEndereco(lBrvString.BrSplitLista[0],  // Interno
                                                 lBrvString.BrSplitLista[1],
                                                 lBrvString.BrSplitLista[3],
                                                 pStSerApl,
                                                 pSqlConnec);

                      if not Result then
                      begin
                            Result := ConectarEndereco(lBrvString.BrSplitLista[0],  // Externo
                                                       lBrvString.BrSplitLista[1],
                                                       lBrvString.BrSplitLista[3],
                                                       pStSerApl, pSqlConnec);
                      end;

                      if Result then
                      begin
                            pNmSerCon := lBrvString.BrSplitLista[0];
                      end;
                end;

                inc(lNrServer);
          end;
      finally
           FreeAndNil(lStlServer);
           FreeAndNil(lBrvString);
      end;
end;

function TDmSrvApl.ConectarEndereco(pNmServer  : String; pNoIp : String; pNoPorta : String;
                                var pStSerApl  : String;
                                    pSqlConnec : TSqlConnection) : Boolean;
begin
      pSqlConnec.Params.Clear;
      pSqlConnec.Params.Add('DriverUnit=DBXDataSnap');
      pSqlConnec.Params.Add('HostName=' + pNoIp);
      pSqlConnec.Params.Add('Port=' + pNoPorta);
      pSqlConnec.Params.Add('CommunicationProtocol=tcp/ip');
      pSqlConnec.Params.Add('DatasnapContext=datasnap/');
      pSqlConnec.Params.Add('DriverAssemblyLoader=Borland.Data.TDBXClientDriverLoader,Borland.Data.DbxClientDriver,Version=15.0.0.0,Culture=neutral,PublicKeyToken=91d62ebb5b0d1b1b');
      pSqlConnec.Params.Add('Filters={}');

      try
           pSqlConnec.Connected := True;
      except
          on E : Exception do
             begin
                   pStSerApl := pStSerApl + #13#13 +
                                'Não foi possível conectar-se com servidor: ' +
                                pNmServer + ' IP: ' + pNoIp + ':' + pNoPorta   +
                                #13 + E.Message;
             end;
      end;

      Result := pSqlConnec.Connected;
end;

procedure TDmSrvApl.InformarEntradaCliente(pSnConIni : Boolean; pTpServer : String);
var lConexao  : TSDmRWClient;
    lNrAcesso : String;
begin
      BrvlIP.BrComputadorLocal := True;

      if pTpServer = 'A' then
      begin
            lConexao  := TSDmRWClient.Create(SrvAplica.DBXConnection);
      end else
      begin
            lConexao  := TSDmRWClient.Create(SrvImpres.DBXConnection);
      end;

      try
          if gIpConTCP = '' then
          begin
                gIpConTcp := BrvlIP.BrEnderecoIP;
          end;

          if pTpServer = 'A' then
          begin
                lNrAcesso := lConexao.EntradaCliente(gIpConTCP, BrvlIP.BrNomeComputador,
                                                     gNrConTCP, gDsCreden);

                if pSnConIni then
                begin
                      gNrClient := DesCriptografarCodigoAcesso(lNrAcesso);
                      gDsCreden := BrvDicionario.CriptografarCredencial(gNrClient, pTpServer);
                end;
          end else
          begin
                lNrAcesso := lConexao.EntradaCliente(gIpImpTCP, BrvlIP.BrNomeComputador,
                                                     gNrImpTCP, gDsCreImp);

                if pSnConIni then
                begin
                      gNrCliImp := DesCriptografarCodigoAcesso(lNrAcesso);
                      gDsCreImp := BrvDicionario.CriptografarCredencial(
                                                                    gNrCliImp, ptpServer);
                end;
          end;
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TDmSrvApl.InformarClienteAutenticado(pTpServer : String);
var lConexao  : TSDmRWClient;
    lDsResult : String;
begin
      if pTpServer = 'A' then
      begin
            lConexao  := TSDmRWClient.Create(SrvAplica.DBXConnection);
      end else
      begin
            lConexao  := TSDmRWClient.Create(SrvImpres.DBXConnection);
      end;

      try
          if pTpServer = 'A' then
          begin
                lConexao.ClienteAutenticado(gDsCreden, lDsResult,
                                            BrvDicionario.UserCode,
                                            BrvDicionario.UserLogin,
                                            gNrConTcp);
          end else
          begin
                lConexao.ClienteAutenticado(gDsCreImp, lDsResult,
                                            BrvDicionario.UserCode,
                                            BrvDicionario.UserLogin,
                                            gNrImpTCP);
          end;

          BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TDmSrvApl.DataModuleCreate(Sender: TObject);
begin
      gNrConTCP := 'zero';
      gDsCreden := '';

      InicializarControleImpressao;
end;

procedure TDmSrvApl.InicializarControleImpressao;
begin
      gNrImpTCP := 'zero';
      gDsCreImp := '';
end;

function TDmSrvApl.DesCriptografarCodigoAcesso(pNrClient : String) : Integer;
begin
      Result := StrToInt(pNrClient);
end;

procedure TDmSrvApl.GravaLogScreen(pCdUsuari: Integer; pVrLogScr: Variant; pNmFormul, pDsCreden,
                                                         pDsPath: String; pConexao: TSQLConnection);
var ThrLogScr : TGravaLogScreen;
begin
      ThrLogScr := TGravaLogScreen.Create(pCdUsuari, pVrLogScr, pNmFormul, pDsCreden,
                                                                                 pDsPath, pConexao);
      ThrLogScr.Execute;
end;

function TDmSrvApl.IsDebugged: Boolean;
var IsDebuggerPresent: function: Boolean; stdcall;
    KernelHandle: THandle;
    P: Pointer;
begin
      KernelHandle := GetModuleHandle(kernel32);
      @IsDebuggerPresent := GetProcAddress(KernelHandle, 'IsDebuggerPresent');

      if Assigned(IsDebuggerPresent) then // Win98+/NT4+ only
      begin
            Result := IsDebuggerPresent;
      end else
      begin // Win9x uses thunk pointer outside the module when under a debugger
            P := GetProcAddress(KernelHandle, 'GetProcAddress');
            Result := (DWORD(P) < KernelHandle);
      end;
end;

function TDmSrvApl.AtualizarAplicacaoCliente : Boolean;
begin
      result := false;
      if not IsDebugged then
      begin
            Cai0011 := TCai0011.Create(Self);

            try
                Cai0011.Show;
                Cai0011.Refresh;
                Application.ProcessMessages;
                Result := Cai0011.ProcessarAtualizacaoCliente;
            finally
                FreeAndNil(Cai0011);
            end;
      end;
end;

end.

