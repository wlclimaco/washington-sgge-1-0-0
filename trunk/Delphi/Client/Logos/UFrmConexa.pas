//http://www.bravolog.com.br/srvconexa/
// http://note_jefferson/bravo/
unit UFrmConexa;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, IdBaseComponent, IdComponent, IdTCPConnection, IdTCPClient, BrvSrvConexao,
  StdCtrls, ExtCtrls, ComCtrls, BrvIP, BrvTCPClient, BrvString, IniFiles;

type
  TFrmConexa = class(TForm)
    BrvConexao: TBrvSrvConexao;
    Panel1: TPanel;
    LblProces: TLabel;
    PgbProgre: TProgressBar;
    Timer: TTimer;
    TmrConexa: TTimer;
    TmrRecImp: TTimer;
    procedure TimerTimer(Sender: TObject);
    procedure TCPClientExecute(Sender: TObject; pDsMensag: string);
    procedure TmrConexaTimer(Sender: TObject);
    procedure TmrRecImpTimer(Sender: TObject);
  private
    { Private declarations }
    gNrIPCnx  : String;
    gNrPorCnx : Integer;
    gQtRecImp : Integer;
    procedure ProcessarMensagemServerConexao(pDsMensag: String);
    procedure GravaEnderecoDeConexao;
    procedure CarregarEnderecoDeConexao;
    procedure ReconectarServidorImpressao(pQtTempo : Integer);
  public
    { Public declarations }
    gDsLisSer : AnsiString;
    gTcpImpCli: TBrvTCPClient;
    gTcpAplCli: TBrvTCPClient;
    procedure ReconectaServidorAplicacao;
    function  ConectaServidorConexao(pSnConIni : Boolean) : String;
    procedure EnviaMensagemConexaoEstabelecida(pSnConIni : Boolean);
    procedure IniciarProcessoReconexao(pQtSegund : Integer);
    procedure SolicitarServidorImpressao(pTpConexa : Byte);
  end;

var
  FrmConexa: TFrmConexa;

implementation

uses ULogos, UFrmErrCon, UDmSrvApl;

{$R *.dfm}

function TFrmConexa.ConectaServidorConexao(pSnConIni : Boolean) : String;
var lDsErro   : String;
    lNrContad : Integer;
    aux : string;
begin
      if gTcpAplCli <> nil then
      begin
            FreeAndNil(gTcpAplCli);
      end;

      gTcpAplCli := TBrvTCPClient.Create(Self);
      gTcpAplCli.OnExecute := TCPClientExecute;

      aux := '';
      Result  := '';
      lDsErro := '';
      gDsLisSer := '';

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // Acessando site para pegar endereços do servidor de conexao
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      LblProces.Caption := 'Obtendo informações da internet. Aguarde ...';
      LblProces.Refresh;
      PgbProgre.Position := 0;
      PgbProgre.Refresh;

      try
          BrvConexao.Execute; // tentando acesso ao www
          aux := aux + 'executou - ';
      except
          CarregarEnderecoDeConexao;
      end;
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // Tentando conexão com o servidor interno
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      LblProces.Caption :=
                          'Estabelecendo conexão com servidor interno. Aguarde ...';
      LblProces.Refresh;
      PgbProgre.Position := 0;
      PgbProgre.Refresh;

      if not gTcpAplCli.Connected then
      begin
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
                         lDsErro := 'Não foi possível conectar-se com servidor interno:' +
                                    gTcpAplCli.Host + ':' + IntToStr(gTcpAplCli.Port) +
                                    #13 + E.Message;
                   end;
            end;
      end;

      if not gTcpAplCli.Connected then
      begin
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // Tentando conexão com o servidor Externo
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            LblProces.Caption :=
                          'Estabelecendo conexão com servidor externo. Aguarde ...';
            LblProces.Refresh;
            PgbProgre.Position := 0;
            PgbProgre.Refresh;

            gNrIPCnx  := BrvConexao.BrIpInterno;
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
            Timer.Enabled := False;
            PgbProgre.Position := 0;
            LblProces.Caption  :=
                              'Conectado ao servidor de conexões. Aguardando instruções.';
            LblProces.Refresh;
            Timer.Enabled := True;
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

      TmrConexa.Enabled := True; // inicializando time para verificação do servidor de conexão;


            if Trim(Result) = '' then
            begin
//                  showmessage(aux);
            end;
end;

procedure TFrmConexa.EnviaMensagemConexaoEstabelecida(pSnConIni : Boolean);
var lSnInicio : Boolean;
    lNrContad : Integer;
begin
      lSnInicio := DmSrvApl.gNrConTCP = 'zero';

      gTcpAplCli.Socket.WriteLn('30001;' + DmSrvApl.gNrConTCP + ';' +
                                          DmSrvApl.gNmSerApl + ';' +
                                          DmSrvApl.gIpConTCP + ';' +
                                          IntToStr(DmSrvApl.gNrClient) + ';A@@');

      lNrContad := 0;
      repeat
             inc(lNrContad);
             PgbProgre.StepIt;
      until (DmSrvApl.gNrConTCP <> 'zero') or (lNrContad > 100000000000);

      if DmSrvApl.gNrConTCP = 'zero' then
      begin
            raise Exception.Create(
                           'Não foi possível receber credencial do servidor de conexões');
      end;

      if not pSnConIni then
      begin
            if lSnInicio then
            begin
                  DmSrvApl.InformarEntradaCliente(False, 'A');
            end;

            gTcpAplCli.Socket.WriteLn('20003;' +
                             DmSrvApl.gNrConTCP + ';' +
                             IntToStr(DmSrvApl.BrvDicionario.UserCode) + ';' +
                             DmSrvApl.BrvDicionario.UserLogin + '@@');

            if lSnInicio then
            begin
                  DmSrvApl.InformarClienteAutenticado('A');
            end;
      end;
end;

procedure TFrmConexa.CarregarEnderecoDeConexao;
var lIniFile  : TIniFile;
    lDsCaminh : String;
begin
      try
          lDsCaminh := Application.ExeName;
          lDsCaminh := ExtractFileDir(lDsCaminh);

          lIniFile := TIniFile.Create(lDsCaminh + '\Logos_Con.ini');

          BrvConexao.BrIpInterno   := lIniFile.ReadString('IpConexao', 'IpServer', '');
          BrvConexao.BrIpInterno   := BrvConexao.BrIpExterno;
          BrvConexao.BrNumeroPorta := lIniFile.ReadInteger('IpConexao', 'NoPorta', 0);
      finally
          lIniFile.Destroy;
      end;
end;

procedure TFrmConexa.GravaEnderecoDeConexao;
var lIniFile  : TIniFile;
    lDsCaminh : String;
begin
      try
          lDsCaminh := Application.ExeName;
          lDsCaminh := ExtractFileDir(lDsCaminh);

          lIniFile := TIniFile.Create(lDsCaminh + '\Logos_Con.ini');

          lIniFile.WriteString('IpConexao', 'IpServer', gTcpAplCli.Host);
          lIniFile.WriteString('IpConexao', 'NoPorta',  IntToStr(gTcpAplCli.Port));
      finally
          lIniFile.Destroy;
      end;
end;

procedure TFrmConexa.TmrConexaTimer(Sender: TObject);
begin
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// =-=-=-= Redundância com a função 10002
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      if (FrmLogos <> nil) and (not FrmLogos.TmrConexa.Enabled) then
      begin
            try
                if gTcpAplCli.Socket.Connected then
                begin
                      gTcpAplCli.Socket.WriteLn('teste@@');
                end;
            except
                IniciarProcessoReconexao(20);
            end;
      end;
end;

procedure TFrmConexa.TmrRecImpTimer(Sender: TObject);
begin
      Dec(gQtRecImp);
      FrmLogos.StbRodape.Panels[1].Text := 'SrvImp: Sem Conexão (Aguarde ' +
                                    IntToStr(gQtRecImp) + 's)';

     if gQtRecImp <= 0 then
     begin
           TmrRecImp.Enabled := False;
           FrmConexa.SolicitarServidorImpressao(1);
     end;
end;

procedure TFrmConexa.IniciarProcessoReconexao(pQtSegund : Integer);
begin
      TmrConexa.Enabled := False;

      try
          gTcpAplCli.Disconnect;
      except

      end;

      LblProces.Caption := 'Perda de sinal com o servidor de conexão...';
      FrmLogos.gQtRecCnx                := pQtSegund + 10;
      FrmLogos.StbRodape.Panels[3].Text := 'Sem sinal com servidor de conexão. (Aguarde ' +
                                        IntToStr(FrmLogos.gQtRecCnx) + 's)';
      FrmLogos.TmrConexa.Enabled     := True;
end;

procedure TFrmConexa.TCPClientExecute(Sender: TObject; pDsMensag: string);
var lNrMensag  : Integer;
    lBrvString : TBrvString;
begin
      if pDsMensag <> '' then
      begin
            lBrvString := TBrvString.Create(self);
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

procedure TFrmConexa.ProcessarMensagemServerConexao(pDsMensag : String);
var lBrvString : TBrvString;
    lStServer  : String;
begin
      lBrvString := TBrvString.Create(self);

      try
          lBrvString.Split(pDsMensag, ';');

          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-= Servidor confirmando entrada
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          if  lBrvString.BrSplitLista.Strings[0] = '10001' then
          begin
                DmSrvApl.gNrConTCP := lBrvString.BrSplitLista.Strings[1];
                DmSrvApl.gIpConTCP := lBrvString.BrSplitLista.Strings[2];
          end
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-= Servidor de conexão desconectado
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          else if lBrvString.BrSplitLista.Strings[0] = '10002' then
               begin
                      LblProces.Caption := lBrvString.BrSplitLista.Strings[1];

                     if FrmLogos <> nil then
                     begin
                           IniciarProcessoReconexao(StrToInt(
                                                     lBrvString.BrSplitLista.Strings[2]));
                     end;
               end
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-= Servidor de aplicação foi desconectado
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          else if lBrvString.BrSplitLista.Strings[0] = '10003' then
               begin
                     FrmErrCon := TFrmErrCon.Create(Application);
                     FrmErrCon.MenDsMsgErr.Text  := lBrvString.BrSplitLista.Strings[1];
                     FrmErrCon.PgbTempo.Position := 0;
                     FrmErrCon.PgbTempo.Max := StrToInt(
                                                      lBrvString.BrSplitLista.Strings[2]);
                     FrmErrCon.Timer.Enabled := True;
                     FrmErrCon.ShowModal;
                     FreeAndNil(FrmErrCon);
                     FrmLogos.Close;
                     FrmLogos.TrmClose.Enabled := True;
               end
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-= Lista de servidores de aplicaçao
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
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
               end
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-= Atualizar dicionário de dados
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          else if lBrvString.BrSplitLista.Strings[0] = '10005' then
               begin
                     DmSrvApl.BrvDicionario.CarregarDicionario(nil, nil,
                                                               DmSrvApl.gDsCreden);
//                     FrmLogos.MontarMenuSuspenso;
                     MessageDlg('Dicionário de dados foi atualizado por solicitação ' +
                                ' remota do servidor de aplicação',
                                mtInformation, [mbOk], 0);
               end
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-= Servidor confirmando entrada
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          else if  lBrvString.BrSplitLista.Strings[0] = '10006' then
               begin
                     DmSrvApl.gNrImpTCP := lBrvString.BrSplitLista.Strings[1];
                     DmSrvApl.gIpImpTCP := lBrvString.BrSplitLista.Strings[2];
               end
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-= Lista de servidores de impressão
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          else if lBrvString.BrSplitLista.Strings[0] = '10007' then
               begin
                     try
                         if lBrvString.BrSplitLista.Count > 1 then
                         begin
                               if lBrvString.BrSplitLista.Strings[1] <> 'ER' then
                               begin
                                     if DmSrvApl.ConectarServidorDataSnap(
                                                  lBrvString.BrSplitLista.Strings[1],
                                                  lStServer, DmSrvApl.SrvImpres,
                                                  DmSrvApl.gNmSerImp) then
                                     begin
                                           FrmLogos.gStSrvImp := 1;
                                           FrmLogos.StbRodape.Panels[1].Text :=
                                                          'SrvImp: ' + DmSrvApl.gNmSerImp;
                                           if gTcpImpCli.Tag = 1 then
                                           begin
                                                 DmSrvApl.InformarEntradaCliente(True, 'I');
                                           end;

                                           gTcpImpCli.Socket.WriteLn('40003;' +
                                               DmSrvApl.gNrImpTCP + ';' +
                                               IntToStr(DmSrvApl.BrvDicionario.UserCode)+
                                               ';' +
                                               DmSrvApl.BrvDicionario.UserLogin + '@@');

                                           DmSrvApl.InformarClienteAutenticado('I');
                                     end else
                                     begin
                                           ReconectarServidorImpressao(30);
                                     end;
                               end else
                               begin
                                     ReconectarServidorImpressao(30);
                               end;
                         end else
                         begin
                               ReconectarServidorImpressao(30);
                         end;
                     except
                           ReconectarServidorImpressao(30);
                     end;
               end
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-= Servidor de impressao foi desconectado
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          else if lBrvString.BrSplitLista.Strings[0] = '10008' then
               begin
                     ReconectarServidorImpressao(
                                            StrToInt(lBrvString.BrSplitLista.Strings[2]));
               end
          ;
      finally
          FreeAndNil(lBrvString);
      end;
end;

procedure TFrmConexa.ReconectarServidorImpressao(pQtTempo : Integer);
begin
      gQtRecImp := pQtTempo;
      FrmLogos.StbRodape.Panels[1].Text := 'SrvImp: Sem Conexão (Aguarde ' +
                                                               IntToStr(gQtRecImp) + 's)';
      FrmLogos.gStSrvImp                   := 0;
      DmSrvApl.SrvImpres.Connected         := False;
      DmSrvApl.InicializarControleImpressao;

      TmrRecImp.Enabled             := True;
end;

procedure TFrmConexa.TimerTimer(Sender: TObject);
begin
      PgbProgre.StepIt;
      PgbProgre.Refresh;
end;

procedure TFrmConexa.SolicitarServidorImpressao(pTpConexa : Byte);
begin
      if gTcpImpCli <> nil then
      begin
            FreeAndNil(gTcpImpCli);
      end;

      gTcpImpCli := TBrvTCPClient.Create(Self);
      gTcpImpCli.Port      := gNrPorCnx;
      gTcpImpCli.Host      := gNrIpCnx;
      gTcpImpCli.OnExecute := TCPClientExecute;
      gTcpImpCli.Connect;
      gTcpImpCli.Tag       := pTpConexa; // 1: conexão inicial
                                         // 2: reconexão

      if gTcpImpCli.Connected then
      begin
            gTcpImpCli.Socket.WriteLn('30001;' + DmSrvApl.gNrImpTCP + ';' +
                                                 DmSrvApl.gNmSerImp + ';' +
                                                 DmSrvApl.gIpImpTCP + ';' +
                                                 IntToStr(DmSrvApl.gNrCliImp) + ';I@@');
      end;
end;

procedure TFrmConexa.ReconectaServidorAplicacao;
begin
      if gTcpAplCli <> nil then
      begin
            FreeAndNil(gTcpAplCli);
      end;

      gTcpAplCli := TBrvTCPClient.Create(Self);
      gTcpAplCli.Port := gNrPorCnx;
      gTcpAplCli.Host := gNrIpCnx;
      gTcpAplCli.OnExecute := FrmConexa.TCPClientExecute;
      try
          gTcpAplCli.Connect;
      except

      end;

      if gTcpAplCli.Connected then
      begin
            gTcpAplCli.Socket.WriteLn('30001;' +
                                      DmSrvApl.gNrConTCP + ';' +
                                      DmSrvApl.gNmSerApl + ';' +
                                      DmSrvApl.gIpConTCP + ';' +
                                      IntToStr(DmSrvApl.gNrClient) + ';A@@');


           gTcpAplCli.Socket.WriteLn('20003;' +
                                     DmSrvApl.gNrConTCP + ';' +
                                     IntToStr(DmSrvApl.BrvDicionario.UserCode) + ';' +
                                     DmSrvApl.BrvDicionario.UserLogin + '@@');
      end;

end;

end.
