unit LogosWSImpl;

interface

uses InvokeRegistry, Types, XSBuiltIns, LogosWSIntf, DbClient, Classes, SysUtils, DB,
     DBXCommon, BrvTCPClient, BrvString, IniFiles, BrvSrvConexao, DBXDataSnap, BrvDicionario,
     DSConnect, SqlExpr, ExtCtrls, BrvIP, Controls, BrvSenha;

type
  TLogosWS = class(TInvokableClass, ILogosWS)
  private
    function Conectar: Boolean;
    function ConectaServidorAplicacao(pDsLisSer: String): Integer;
    function ConectarServidorDataSnap(pDsLisSer: String; var pStSerApl: String;
                                        pSqlConnec: TSqlConnection; var pNmSerCon: String): Boolean;
    function ConectaServidorConexao(pSnConIni: Boolean): String;
    procedure TCPClientExecute(Sender: TObject; pDsMensag: string);
    procedure ProcessarMensagemServerConexao(pDsMensag: String);
    procedure CarregarEnderecoDeConexao;
    procedure EnviaMensagemConexaoEstabelecida(pSnConIni: Boolean);
    procedure InformarEntradaCliente(pSnConIni: Boolean; pTpServer: String);
    function DesCriptografarCodigoAcesso(pNrClient: String): Integer;
    procedure InformarClienteAutenticado(pTpServer: String; pcdusuari: Integer; nmusuari: string);
    procedure GravaEnderecoDeConexao;
    function ConectarEndereco(pNmServer, pNoIp, pNoPorta: String; var pStSerApl: String;
                                                               pSqlConnec: TSqlConnection): Boolean;
    procedure AtualizarServidorAnterior(pDsLisSer: String);
    function ListaServidoresAnterior: String;
    procedure ValidarLogin(pDsLogin: String; var pNrSenha, pCdUsuari: Integer; var pNmUsuari: String);
    procedure VerificarMensagemServidorAplicacao(pDsResult: String);
    function ConectarDataSnapDireto(): String;
    procedure StrToClientDataSet(DsLista: TStrings; CcDataTmp: TClientDataSet);
    function Autentica(pDsLogin, pDsSenha: WideString): String;
  public
    SrvAplica : TSQLConnection;
    lNrTentat : Integer;
    lSnConect : Integer;
    lDsLisSer : String;
    gNrConTCP : String;
    gIpConTCP : String;
    gDsLisSer : String;
    gNrIPCnx  : String;
    gNmSerApl : String;
    gNrPorCnx : Integer;
    gNrClient : Integer;
    gNrAcesso : String;
    gDsCreden : String;
    gNoIpServ : String;
    gNoPorta  : String;
    gNmServer : String;
    gNrSenha  : Integer;
    gCdUsuari : Integer;
    gTcpAplCli: TBrvTCPClient;
    BrvlIP    : TBrvlIP;
    BrvConexao: TBrvSrvConexao;
    BrvDicionario : TBrvDicionario;
    gNrLinLog : Integer;
    const     gQtMaxTen = 3;

    function AutenticaUsuario(pDsLogin : WideString;
                              pDsSenha : WideString;
                          var pCdUsuari: Integer): WideString; stdcall;

    function ProximaAtividade(pCdUsuari : Integer;
                          var pNrMapa   : WideString;
                          var pDsAtivid : WideString): WideString; stdcall;

    function AtividadeNaoConvocada(pCdUsuari: Integer;
                               var pDsLista : WideString): WideString; stdcall;

    function ConsultaAtividade(pNrFornec: WideString;
                               pSgTipAti: WideString;
                               var pNrOpeLog : WideString): WideString; stdcall;

    function ConferenciaEntradaIniciar(pCdUsuari : Integer;
                                       pNrMapa   : WideString;
                                   var pNrNota   : WideString;
                                   var pNrFornec : WideString;
                                   var pDsLista  : WideString): WideString; stdcall;

   function ConferenciaEntradaFinalizar(pCdUsuari  : Integer;
                                        pNrMapa    : WideString;
                                        pdslista   : WideString;
                                        pSnCancel  : WideString;
                                        pQtProSid  : Integer): WideString; stdcall;

    function ConferenciaSaidaIniciar(pCdUsuari : Integer;
                                     pNrMapa   : WideString;
                                 var pNrNota   : WideString;
                                 var pNrFornec : WideString;
                                 var pDsLista  : WideString): WideString; stdcall;

   function ConferenciaSaidaFinalizar(pCdUsuari  : Integer;
                                      pNrMapa    : WideString;
                                      pdslista   : WideString;
                                      pCdClient  : WideString;
                                      pSnCancel  : WideString;
                                      pQtProSid  : Integer): WideString; stdcall;

    function Ping: WideString; stdcall;

    function TestaConexao(pIDTest: WideString; pMsnTest: WideString): WideString; stdcall;

    function ConsultaPalletEnderecamento(pCdUsuari: Integer; pNrPallet: WideString;
                                         var pDsLista: WideString): WideString; stdcall;

    function FechaPalletEnderecamento(pLsPallet: AnsiString): AnsiString; stdcall;
  end;

implementation

uses UClaSrv;

const gQtMaxTen = 2;

{ TLogosWS }

//Procedimento e funções exclusivos para conexão com o Servidor de Aplicação Logos
procedure TLogosWS.ProcessarMensagemServerConexao(pDsMensag : String);
var lBrvString : TBrvString;
    lStServer  : String;
begin
      lBrvString := TBrvString.Create(nil);

      try
          lBrvString.Split(pDsMensag, ';');

          // =-=-= Servidor confirmando entrada
          if  lBrvString.BrSplitLista.Strings[0] = '10001' then
          begin
                gNrConTCP := lBrvString.BrSplitLista.Strings[1];
                gIpConTCP := lBrvString.BrSplitLista.Strings[2];
          end

          // =-=-= Servidor de conexão desconectado
          else if lBrvString.BrSplitLista.Strings[0] = '10002' then
               begin
                     //*IniciarProcessoReconexao(StrToInt(lBrvString.BrSplitLista.Strings[2]));
               end

          // =-=-= Lista de servidores de aplicaçao
          else if lBrvString.BrSplitLista.Strings[0] = '10004' then
               begin
                     if lBrvString.BrSplitLista.Count > 1 then
                     begin
                           if lBrvString.BrSplitLista.Strings[1] <> 'ER' then
                           begin
                                 gDsLisSer := 'OK;' + lBrvString.BrSplitLista.Strings[1];
                           end else
                           begin
                                 gDsLisSer := 'E1;' + lBrvString.BrSplitLista.Strings[2];
                           end;
                     end else
                     begin
                           gDsLisSer := 'ER;Nenhum servidor de aplicação disponível';
                     end;
               end;
      finally
          FreeAndNil(lBrvString);
      end;
end;

procedure TLogosWS.TCPClientExecute(Sender: TObject; pDsMensag: string);
var lNrMensag  : Integer;
    lBrvString : TBrvString;
begin
      if pDsMensag <> '' then
      begin
            lBrvString := TBrvString.Create(Nil);
            try
                lBrvString.Split(pDsMensag, '@@');

                for lNrMensag := 0 to lBrvString.BrSplitLista.Count -1 do
                begin
                      pDsMensag := lBrvString.BrSplitLista.Strings[lNrMensag];
                      if pDsMensag[1] = '1' then // originado do servidor de conexao
                      begin
                            ProcessarMensagemServerConexao(
                                                      lBrvString.BrSplitLista[lNrMensag]);
                      end;
                end;
            finally
                FreeAndNil(lBrvString);
            end;
      end;
end;

function TLogosWS.TestaConexao(pIDTest, pMsnTest: WideString): WideString;
var lDsArquiv : TStrings;
    lNmArquiv : String;
begin
      try
          lDsArquiv := TStringList.Create;

          if (Trim(pIDTest) = '') then
          begin
                lNmArquiv := 'TEST' + FormatDateTime('yymmdd_hhmm', Now()) + '.txt';
          end else
          begin
                lNmArquiv := pIDTest +  '.txt';
          end;

          lDsArquiv.Add(pMsnTest);
          lDsArquiv.SaveToFile(lNmArquiv);

      finally
          FreeAndNil(lDsArquiv);
      end;
end;

procedure TLogosWS.VerificarMensagemServidorAplicacao(pDsResult : String);
begin
      if Copy(pDsResult, 1, 3) = '1; ' then
      begin
            Delete(pDsResult, 1, 3);
            raise Exception.Create(pDsResult);
      end;
end;

procedure TLogosWS.ValidarLogin(pDsLogin : String; var pNrSenha : Integer;
                                                   var pCdUsuari : Integer;
                                                   var pNmUsuari : String);
var lConexao  : TSDmWmsClient;
    lDsResult : String;
begin
      lConexao := TSDmWmsClient.Create(SrvAplica.DBXConnection);

      try
          lConexao.AutenticaUsuarioColetor(pDsLogin, pNrSenha, pCdUsuari, pNmUsuari);

          VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TLogosWS.CarregarEnderecoDeConexao;
var lIniFile  : TIniFile;
begin
      try
          lIniFile  := TIniFile.Create('Logos_Con.ini');

          BrvConexao.BrIpInterno   := lIniFile.ReadString('IpConexao', 'IpServer', '');
          BrvConexao.BrIpExterno   := BrvConexao.BrIpExterno;
          BrvConexao.BrNumeroPorta := lIniFile.ReadInteger('IpConexao', 'NoPorta', 0);
      finally
          lIniFile.Destroy;
      end;
end;

procedure TLogosWS.InformarEntradaCliente(pSnConIni : Boolean; pTpServer : String);
var lConexao  : TSDmRWClient;
begin
      BrvlIP  := TBrvlIP.Create(nil);
      BrvlIP.BrComputadorLocal := True;

      BrvDicionario := TBrvDicionario.Create(Nil);

      if pTpServer = 'A' then
      begin
            lConexao  := TSDmRWClient.Create(SrvAplica.DBXConnection);
      end;

      try
          if gIpConTCP = '' then
          begin
                gIpConTcp := BrvlIP.BrEnderecoIP;
          end;

          if pTpServer = 'A' then
          begin
                gNrAcesso := lConexao.EntradaCliente(gIpConTCP, BrvlIP.BrNomeComputador,
                                                     gNrConTCP, gDsCreden);

                if pSnConIni then
                begin
                      gNrClient := DesCriptografarCodigoAcesso(gNrAcesso);
                      gDsCreden := BrvDicionario.CriptografarCredencial(
                                                                    gNrClient, pTpServer);
                end;
          end;
      finally
          FreeAndNil(lConexao);
      end;
end;

function TLogosWS.DesCriptografarCodigoAcesso(pNrClient : String) : Integer;
begin
      Result := StrToInt(pNrClient);
end;


procedure TLogosWS.EnviaMensagemConexaoEstabelecida(pSnConIni : Boolean);
var lSnInicio : Boolean;
    lNrContad : Integer;
begin
      lSnInicio := gNrConTCP = 'zero';

      gTcpAplCli.Socket.WriteLn('30001;' + gNrConTCP + ';' + gNmSerApl + ';' +
                                           gIpConTCP + ';' + IntToStr(gNrClient) + ';A@@');

      lNrContad := 0;
      repeat
             inc(lNrContad);
      until (gNrConTCP <> 'zero') or (lNrContad > 100000000000);

      if gNrConTCP = 'zero' then
      begin
            Abort;
      end;

      if not pSnConIni then
      begin
            if lSnInicio then
            begin
                  InformarEntradaCliente(False, 'A');
            end;

            gTcpAplCli.Socket.WriteLn('20003;' +
                             gNrConTCP + ';' +
                             IntToStr(BrvDicionario.UserCode) + ';' +
                                      BrvDicionario.UserLogin + '@@');

            if lSnInicio then
            begin
                  InformarClienteAutenticado('A', 0, '');
            end;
      end;
end;

procedure TLogosWS.InformarClienteAutenticado(pTpServer : String; pcdusuari: Integer;
                                                                                  nmusuari: string);
var lConexao  : TSDmRWClient;
    lDsResult : String;
begin
      if pTpServer = 'A' then
      begin
            lConexao  := TSDmRWClient.Create(SrvAplica.DBXConnection);
      end;

      try
          if pTpServer = 'A' then
          begin
                lConexao.ClienteAutenticado(gDsCreden, lDsResult,
                                            pcdusuari, nmusuari,
                                            gNrConTcp);
          end;

          BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

      finally
          FreeAndNil(lConexao);
      end;
end;

function TLogosWS.ConectaServidorConexao(pSnConIni : Boolean) : String;
var lDsErro   : String;
    lNrContad : Integer;
    aux : string;
begin
      if (BrvConexao = nil) then
      begin
            BrvConexao := TBrvSrvConexao.Create(Nil);
      end;

      BrvConexao.BrEnderecoSite := 'http://127.0.0.1/srvconexa/';

      if gTcpAplCli <> nil then
      begin
            FreeAndNil(gTcpAplCli);
      end;

      gTcpAplCli := TBrvTCPClient.Create(nil);
      gTcpAplCli.OnExecute := TCPClientExecute;

      aux := '';
      Result  := '';
      lDsErro := '';
      gDsLisSer := '';

      // Acessando site para pegar endereços do servidor de conexao
      try
          BrvConexao.Get; // tentando acesso ao www

          aux := aux + 'executou - ';
      except
          CarregarEnderecoDeConexao;
      end;

      if (not gTcpAplCli.Connected) and (BrvConexao.BrIpInterno <> '') then
      begin
            // Tentando conexão com o servidor interno
            gNrIPCnx  := BrvConexao.BrIpInterno;
            gNrPorCnx := BrvConexao.BrNumeroPorta;

            gTcpAplCli.Port := gNrPorCnx;
            gTcpAplCli.Host := gNrIpCnx;

            try
                gTcpAplCli.Connect;
                aux := aux + 'conectou 1 - ';
            except
                on E : Exception do
                   begin
                         raise exception.Create('Não foi possível conectar-se com servidor interno:' +
                                    gTcpAplCli.Host + ':' + IntToStr(gTcpAplCli.Port) +
                                    #13 + E.Message);
                   end;
            end;
      end;

      if (not gTcpAplCli.Connected) and (BrvConexao.BrIpExterno <> '') then
      begin
            // Tentando conexão com o servidor Externo

            gNrIPCnx  := BrvConexao.BrIpExterno;
            gNrPorCnx := BrvConexao.BrNumeroPorta;

            gTcpAplCli.Port := gNrPorCnx;
            gTcpAplCli.Host := gNrIpCnx;

            try
                gTcpAplCli.Connect;
                aux := aux + 'conectou 2 - ';
            except
                on E : Exception do
                   begin
                         lDsErro := lDsErro + #13#13 +
                              'Não foi possível conectar-se com servidor externo' +
                              gTcpAplCli.Host + ':' + IntToStr(gTcpAplCli.Port) +
                              #13 + E.Message;
                   end;
            end;
      end;

      if not gTcpAplCli.Connected then
      begin
            Result := 'ER;' + lDsErro;
      end else
      begin
            EnviaMensagemConexaoEstabelecida(pSnConIni);
            aux := aux + 'lista 1 - ';

            if pSnConIni then
            begin
                  lNrContad := 0;
                  aux := aux + 'antes repeat - ';

                  repeat
                         inc(lNrContad);
                  until (Trim(gDsLisSer) <> '') or (lNrContad > 10000);

                  aux := aux + 'depois repeat ' + Trim(gDsLisSer) + ' - ' ;
                  Result := Trim(gDsLisSer);
            end;

            GravaEnderecoDeConexao;
      end;
end;

procedure TLogosWS.GravaEnderecoDeConexao;
var lIniFile  : TStrings;
begin
      try
          lIniFile  := TStringList.Create;

          lIniFile.Add('[IpConexao]');
          lIniFile.Add('IpServer=' + gTcpAplCli.Host);
          lIniFile.Add('NoPorta=' + IntToStr(gTcpAplCli.Port));

          lIniFile.SaveToFile('Logos_Con.ini');
      finally
          FreeAndNil(lIniFile);
      end;
end;

function TLogosWS.Conectar: Boolean;
begin
      lNrTentat := 0;
      gNrConTCP := 'zero';
      result := false;

      if (SrvAplica = nil) then
      begin
            SrvAplica := TSQLConnection.Create(nil);
      end;

      repeat
             inc(lNrTentat);

             if lNrTentat > gQtMaxTen then
             begin
                   lSnConect := ConectaServidorAplicacao('ER;30');
             end else
             begin
                   lDsLisSer := ConectaServidorConexao(True);
                   lSnConect := ConectaServidorAplicacao(lDsLisSer);
             end;

             if  lSnConect = 1 then
             begin
                   InformarEntradaCliente(True, 'A');
                   result := true;
             end;
      until (lSnConect = 1) or (lSnConect = 2);

end;

function TLogosWS.ConectarEndereco(pNmServer  : String; pNoIp : String; pNoPorta : String;
                                var pStSerApl  : String;
                                    pSqlConnec : TSqlConnection) : Boolean;
begin
      gNoIpServ := pNoIp;
      gNoPorta  := pNoPorta;
      gNmServer := pNmServer;

      pSqlConnec.DriverName  := 'DataSnap';
      pSqlConnec.LoginPrompt := False;
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

function TLogosWS.ConectarServidorDataSnap(pDsLisSer : String;
                                        var pStSerApl : String;
                                            pSqlConnec : TSqlConnection;
                                        var pNmSerCon : String) : Boolean;
var lStlServer : TStrings;
    lNrServer  : Integer;
    lBrvString : TBrvString;
begin
      lStlServer := TStringList.Create;
      lBrvString := TBrvString.Create(nil);

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

procedure TLogosWS.AtualizarServidorAnterior(pDsLisSer : String);
var lIniFile  : TStrings;
    lDsCaminh : String;
begin
      try
          lDsCaminh := 'Logos_Apl.ini';

          lIniFile := TStringList.Create;
          lIniFile.Text := pDsLisSer;
          lIniFile.SaveToFile(lDsCaminh);
      finally
          FreeAndNil(lIniFile);
      end;
end;

function TLogosWS.ListaServidoresAnterior : String;
var lIniFile  : TStrings;
    lDsCaminh : String;
begin
      try
          lDsCaminh := 'Logos_Apl.ini';

          lIniFile := TStringList.Create;
          lIniFile.LoadFromFile(lDsCaminh);
          Result   := lIniFile.Text;
      finally
          FreeAndNil(lIniFile);
      end;
end;

function TLogosWS.ConectaServidorAplicacao(pDsLisSer: String) : Integer;
var lDsStatus : String;
    lStSerApl : String;
begin
      Result := 0; // não conectou
      lDsStatus := Copy(pDsLisSer, 1, 3);
      Delete(pDsLisSer, 1, 3);

      if lDsStatus = 'OK;' then
      begin

            lStSerApl := '';
            if (SrvAplica = nil) then
            begin
                  SrvAplica := TSQLConnection.Create(Nil);
            end;

            if ConectarServidorDataSnap(pDsLisSer, lStSerApl, SrvAplica, gNmSerApl) then
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
                                              SrvAplica, gNmSerApl) then
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
                  Result := 2;
            end;
      end;
end;

function TLogosWS.ConectarDataSnapDireto() : String;
var lArqCfg : TStrings;
begin
      Result := '';

      lArqCfg := TStringList.Create;
      lArqCfg.LoadFromFile('LogosWS.ini');

      SrvAplica := TSQLConnection.Create(nil);
      BrvDicionario := TBrvDicionario.Create(nil);

      SrvAplica.DriverName  := 'DataSnap';
      SrvAplica.LoginPrompt := False;
      SrvAplica.Params.Clear;
      SrvAplica.Params.Add('DriverUnit=DBXDataSnap');
      SrvAplica.Params.Add('HostName=' + lArqCfg.Strings[0]);
      SrvAplica.Params.Add('Port=' + lArqCfg.Strings[1]);
      SrvAplica.Params.Add('CommunicationProtocol=tcp/ip');
      SrvAplica.Params.Add('DatasnapContext=datasnap/');
      SrvAplica.Params.Add('DriverAssemblyLoader=Borland.Data.TDBXClientDriverLoader,Borland.Data.DbxClientDriver,Version=15.0.0.0,Culture=neutral,PublicKeyToken=91d62ebb5b0d1b1b');
      SrvAplica.Params.Add('Filters={}');

      try
           SrvAplica.Connected := True;
      except
          on E : Exception do
             begin
                   Result := 'Erro durante conexao: ' + lArqCfg.Strings[0] + ':' +
                                                        lArqCfg.Strings[1] + ' ' + E.Message;
             end;
      end;
end;

function TLogosWS.Autentica(pDsLogin : WideString; pDsSenha : WideString): String;
var BrvSenha  : TBrvSenha;
    lnmusuari : string;
    lCdUsuari : Integer;
    lDsResult : string;
begin
      gNrLinLog := 1;
      lDsResult := ConectarDataSnapDireto;

      if (lDsResult = EmptyStr) then
      begin
            Result := '';

            try
                BrvSenha  := TBrvSenha.Create(nil);
                ValidarLogin(pDsLogin, gNrSenha, lCdUsuari, lnmusuari);

                gCdUsuari := lCdUsuari;

                BrvSenha.Login := pDsLogin;
                BrvSenha.Senha := pDsSenha;

                if BrvSenha.Codigo <> gNrSenha then
                begin
                      Result := 'Senha ou Usuario invalidos Calculada ' +
                                FormatFloat('0', brvsenha.Codigo) + ' Resgatada ' +
                                FormatFloat('0', gNrSenha);
                end else
                begin
                      //InformarClienteAutenticado('A', lCdUsuari, pDsLogin);
                end;

            finally
                FreeAndNil(BrvSenha);
            end;
      end else
      begin
            result := 'Erro durante conexao: ' + lDsResult;
      end;
end;

//Métodos publicados do Web Service
function TLogosWS.AutenticaUsuario(pDsLogin : WideString;
                                   pDsSenha : WideString;
                               var pCdUsuari: Integer): WideString;
var CcLisAux  : TClientDataSet;
    lConexao  : TSDmWmsClient;
    lResult   : AnsiString;
    lStAutent : String;
begin
      lStAutent := Autentica(pDsLogin, pDsSenha);

      if (lStAutent = '') then
      begin
            pCdUsuari := gCdUsuari;
            Result    := '';
      end else
      begin
            Result := lStAutent;
      end;
end;

function TLogosWS.ConferenciaEntradaFinalizar(pCdUsuari  : Integer;
                                              pNrMapa    : WideString;
                                              pdslista   : WideString;
                                              pSnCancel  : WideString;
                                              pQtProSid  : Integer): WideString;
var lConexao  : TSDmWmsClient;
    lResult   : AnsiString;
    lDsResult : String;
begin
      try
          lDsResult := ConectarDataSnapDireto;

          if (lDsResult = EmptyStr) then
          begin
                lConexao := TSDmWmsClient.Create(SrvAplica.DBXConnection);

                try
                    lResult  := lConexao.ConferenciaEntradaFinalizar(pCdUsuari, pNrMapa, pdslista,
                                                                     pSnCancel, pQtProSid);

                    if (Copy(lResult,1,1) = '0') then
                    begin
                          Result := '';
                    end else
                    begin
                          Result := Copy(lResult,2, Length(lResult));
                    end;

                finally
                    FreeAndNil(lConexao);
                end;
          end else
          begin
                result := 'Erro durante conexao: ' + lDsResult;
          end;
      finally
         SrvAplica.Close;
      end;
end;

function TLogosWS.ConferenciaEntradaIniciar(pCdUsuari : Integer;
                                            pNrMapa   : WideString;
                                        var pNrNota   : WideString;
                                        var pNrFornec : WideString;
                                        var pDsLista  : WideString): WideString;
var lConexao  : TSDmWmsClient;
    lDsLista  : AnsiString;
    lResult   : AnsiString;
    lNrNota   : AnsiString;
    lNrFornec : AnsiString;
    lDsResult : string;
begin
      try
          lDsResult := ConectarDataSnapDireto;

          if (lDsResult = EmptyStr) then
          begin
                lConexao := TSDmWmsClient.Create(SrvAplica.DBXConnection);

                try
                    lResult  := lConexao.ConferenciaEntradaIniciar(pCdUsuari, pNrMapa,
                                                                   lNrNota, lNrFornec, lDsLista);
                    if (Copy(lResult,1,1) = '0') then
                    begin
                          Result := '';
                          pNrNota   := lNrNota;
                          pDsLista  := lDsLista;
                          pNrFornec := lNrFornec;
                    end else
                    begin
                          Result := Copy(lResult,2, Length(lResult));
                    end;

                finally
                    FreeAndNil(lConexao);
                end;
          end else
          begin
                result := 'Erro durante conexao: ' + lDsResult;
          end;
      finally
          SrvAplica.Close;
      end;
end;

function TLogosWS.Ping: WideString;
begin
      Result := 'OK';
end;

function TLogosWS.ProximaAtividade(pCdUsuari : Integer;
                               var pNrMapa   : WideString;
                               var pDsAtivid : WideString): WideString;
var lConexao  : TSDmWmsClient;
    lResult   : AnsiString;
    lnrmapa   : AnsiString;
    lDsAtivid : AnsiString;
    BrvSenha  : TBrvSenha;
    lDsCreden : Integer;
    lDsResult : string;
begin
      try
          lDsResult := ConectarDataSnapDireto;

          if (lDsResult = EmptyStr) then
          begin
                lConexao := TSDmWmsClient.Create(SrvAplica.DBXConnection);

                try
                    lResult  := lConexao.ProximaAtividade(pCdUsuari, lnrmapa, lDsAtivid);

                    if (Copy(lResult,1,1) = '0') then
                    begin
                          Result := '';
                    end else
                    begin
                          Result := Copy(lResult, 2, Length(lResult));
                    end;

                    pNrMapa   := lnrmapa;
                    pDsAtivid := lDsAtivid;
                finally
                    FreeAndNil(lConexao);
                    SrvAplica.Close;
                end;
          end else
          begin
                Result := 'Erro durante conexao: ' + lDsResult;
          end;
      finally
          SrvAplica.Close;
      end;
end;

function TLogosWS.ConferenciaSaidaFinalizar(pCdUsuari: Integer; pNrMapa, pdslista, pCdClient,
                                            pSnCancel: WideString; pQtProSid: Integer): WideString;
var lConexao  : TSDmWmsClient;
    lDsCreden : AnsiString;
    lResult   : AnsiString;
    lDsResult : String;
begin
      try
          lDsResult := ConectarDataSnapDireto;

          if (lDsResult = EmptyStr) then
          begin
                lConexao := TSDmWmsClient.Create(SrvAplica.DBXConnection);

                try
                    lResult  := lConexao.ConferenciaSaidaFinalizar(pCdUsuari, pNrMapa,
                                                         pdslista, pCdClient, pSnCancel, pQtProSid);
                    if (Copy(lResult,1,1) = '0') then
                    begin
                          Result := '';
                    end else
                    begin
                          Result := Copy(lResult,2, Length(lResult));
                    end;

                finally
                    FreeAndNil(lConexao);
                end;
          end else
          begin
                Result := 'Erro durante conexao: ' + lDsResult;
          end;
      finally
          SrvAplica.Close;
      end;
end;

function TLogosWS.FechaPalletEnderecamento(pLsPallet: AnsiString): AnsiString;
var lConexao  : TSDmWmsClient;
    lResult   : AnsiString;
    lDsResult : string;
begin
      try
          lDsResult := ConectarDataSnapDireto;

          if (lDsResult = EmptyStr) then
          begin
                lConexao := TSDmWmsClient.Create(SrvAplica.DBXConnection);

                try
                    lResult := lConexao.FechaPalletEnderecamento(pLsPallet);

                    if (Copy(lResult,1,1) = '0') then
                    begin
                          Result := '';
                    end else
                    begin
                          Result := Copy(lResult,3, Length(lResult));
                    end;

                finally
                    FreeAndNil(lConexao);
                end;
          end else
          begin
                Result := 'Erro durante conexao: ' + lDsResult;
          end;
      except
          on E : Exception do
          begin
                Result := 'Erro:' + E.Message;
          end;
      end;
end;

function TLogosWS.ConsultaPalletEnderecamento(pCdUsuari: Integer; pNrPallet: WideString;
                                              var pDsLista: WideString): WideString;
var lConexao  : TSDmWmsClient;
    lDsLista  : AnsiString;
    lDsCreden : AnsiString;
    lResult   : AnsiString;
    lDsResult : string;
begin
      try
          lDsResult := ConectarDataSnapDireto;

          if (lDsResult = EmptyStr) then
          begin
                lConexao := TSDmWmsClient.Create(SrvAplica.DBXConnection);

                try
                    lResult := lConexao.ConsultaPalletEnderecamento(pCdUsuari, pNrPallet, lDsLista);

                    if (Copy(lResult,1,1) = '0') then
                    begin
                          Result := '';
                          pDsLista  := lDsLista;
                    end else
                    begin
                          Result := Copy(lResult,3, Length(lResult));
                    end;

                finally
                    FreeAndNil(lConexao);
                end;
          end else
          begin
                Result := 'Erro durante conexao: ' + lDsResult;
          end;
      except
          on E : Exception do
          begin
                Result := 'Erro:' + E.Message;
          end;
      end;
end;

function TLogosWS.ConferenciaSaidaIniciar(pCdUsuari: Integer; pNrMapa: WideString;
                                      var pNrNota, pNrFornec, pDsLista: WideString): WideString;
var lConexao  : TSDmWmsClient;
    lDsLista  : AnsiString;
    lDsCreden : AnsiString;
    lResult   : AnsiString;
    lNrNota   : AnsiString;
    lNrFornec : AnsiString;
    lDsResult : string;
begin
      try
          lDsResult := ConectarDataSnapDireto;

          if (lDsResult = EmptyStr) then
          begin
                lConexao := TSDmWmsClient.Create(SrvAplica.DBXConnection);

                try
                    lResult  := lConexao.ConferenciaSaidaIniciar(pCdUsuari, pNrMapa,
                                                                 lNrNota, lNrFornec, lDsLista);
                    if (Copy(lResult,1,1) = '0') then
                    begin
                          Result := '';
                          pNrNota   := lNrNota;
                          pNrFornec := lNrFornec;
                          pDsLista  := lDsLista;
                    end else
                    begin
                          Result := Copy(lResult,2, Length(lResult));
                    end;

                finally
                    FreeAndNil(lConexao);
                end;
          end else
          begin
                Result := 'Erro durante conexao: ' + lDsResult;
          end;
      except
          on E : Exception do
          begin
                Result := 'Erro:' + E.Message;
          end;
      end;
end;

function TLogosWS.ConsultaAtividade(pNrFornec: WideString;
                                    pSgTipAti: WideString;
                                var pNrOpeLog: WideString): WideString;
var lConexao  : TSDmWmsClient;
    lNrOpeLog : AnsiString;
    lResult   : AnsiString;
    lDsResult : string;
begin
      try
          lDsResult := ConectarDataSnapDireto;

          if (lDsResult = EmptyStr) then
          begin
                lConexao := TSDmWmsClient.Create(SrvAplica.DBXConnection);

                try
                    lResult := lConexao.ConsultaAtividade(pNrFornec, pSgTipAti, lNrOpeLog);

                    if (Trim(lResult) = '') then
                    begin
                          Result := '';
                          pNrOpeLog := lNrOpeLog;
                    end else
                    begin
                          Result := lResult;
                    end;

                finally
                    FreeAndNil(lConexao);
                end;
          end else
          begin
                Result := 'Erro durante conexao: ' + lDsResult;
          end;
      finally
          SrvAplica.Close;
      end;
end;

function TLogosWS.AtividadeNaoConvocada(pCdUsuari: Integer;
                                    var pDsLista : WideString): WideString;
var lConexao  : TSDmWmsClient;
    lDsLista  : AnsiString;
    lResult   : AnsiString;
    lDsResult : string;
begin
      try
          lDsResult := ConectarDataSnapDireto;

          if (lDsResult = EmptyStr) then
          begin
                lConexao := TSDmWmsClient.Create(SrvAplica.DBXConnection);

                try
                    lResult  := lConexao.AtividadeNaoConvocada(pCdUsuari, lDsLista);
                    pDsLista := lDsLista;

                    if (Trim(lResult) = '') then
                    begin
                          Result := '';
                    end else
                    begin
                          Result := lResult;
                    end;
                finally
                    FreeAndNil(lConexao);
                end;
          end else
          begin
                Result := 'Erro AtividadeNaoConvocada: ' + lDsResult;
          end;
      finally
          SrvAplica.Close;
      end;
end;


// Funções Auxiliares para os métodos do Web Service

procedure TLogosWS.StrToClientDataSet(DsLista : TStrings; CcDataTmp: TClientDataSet);
var nrindice : Integer;
    nridxcmp : Integer;
    dstxtpro : string;
begin
      if (CcDataTmp.Active) then
      begin
            CcDataTmp.EmptyDataSet;
            CcDataTmp.Close;
            CcDataTmp.Fields.Clear;
            CcDataTmp.FieldDefs.Clear;
      end;

      dstxtpro := DsLista.Strings[0] + ';';

      while (dstxtpro <> '') do
      begin
            CcDataTmp.FieldDefs.Add(copy(dstxtpro, 1, Pos(';', dstxtpro)-1), ftString, 150);
            dstxtpro := copy(dstxtpro, Pos(';', dstxtpro)+1, Length(dstxtpro));
      end;

      CcDataTmp.CreateDataSet;
      CcDataTmp.Open;

      for nrindice := 1 to DsLista.Count-1 do
      begin
            dstxtpro := DsLista.Strings[nrindice] + ';';
            nridxcmp := 0;

            CcDataTmp.Append;
            while (dstxtpro <> '') do
            begin
                  CcDataTmp.Fields[nridxcmp].AsString := StringReplace(copy(dstxtpro, 1,
                                                   Pos(';', dstxtpro)-1), '.', ',', [rfReplaceAll]);
                  dstxtpro := copy(dstxtpro, Pos(';', dstxtpro)+1, Length(dstxtpro));
                  Inc(nridxcmp);
            end;
            CcDataTmp.Post;
      end;

end;

initialization
   InvRegistry.RegisterInvokableClass(TLogosWS);

end.
