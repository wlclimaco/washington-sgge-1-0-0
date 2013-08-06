unit UFrmConexa;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, IdBaseComponent, IdComponent, IdTCPConnection, IdTCPClient, BrvSrvConexao,
  StdCtrls, ExtCtrls, ComCtrls, BrvIP, BrvTCPClient, BrvString, IniFiles;

type
  TFrmConexa = class(TForm)
    BrvConexao: TBrvSrvConexao;
    TCPClient: TBrvTCPClient;
    Panel1: TPanel;
    LblProces: TLabel;
    PgbProgre: TProgressBar;
    TmrConexa: TTimer;
    Timer: TTimer;
    procedure TCPClientExecute(Sender: TObject; pDsMensag: string);
    procedure TimerTimer(Sender: TObject);
    procedure TmrConexaTimer(Sender: TObject);
  private
    { Private declarations }
    function  RecebeEnderecoServidor : Boolean;
    procedure ProcessarMensagemServerConexao(pDsMensag: String);
    procedure GravaEnderecoDeConexao;
    procedure CarregarEnderecoDeConexao;
  public
    { Public declarations }
    gNoPorta  : Integer;
    gIpIntern : String;
    gIpExtern : String;
    gIpSerCon : String;
    gNoPorCon : Integer;
    gNmComput : String;
    function  ConectaServidorConexao(pSnConIni : Boolean) : Boolean;
    procedure EnviaMensagemConexaoEstabelecida;
    procedure EnviaMensagemClienteConectado(pNrClient : Integer; pDsClient : String);
    procedure IniciarProcessoReconexao(pQtSegund : Integer);
    procedure EnviaMensagemClienteAutenticado(pCdUsuari: Integer; pDsLogin: String;
                                              pNrConTcp: String);
    procedure EnviaMensagemDicionarioAtualizado;
    procedure AtualizarDicionarioDados(pSnMensag : Boolean);
  end;

var
  FrmConexa: TFrmConexa;

implementation

uses UFrmAtuEnd, UFrmLogos, UDmDicion;

const cNmIniApl = '\LogosApl.ini';
      cNmIniImp = '\LogosImp.ini';

{$R *.dfm}

function TFrmConexa.ConectaServidorConexao(pSnConIni : Boolean) : Boolean;
var lDsErro : String;
begin
      Result  := False;
      lDsErro := '';

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // Acessando site para pegar endereços do servidor de conexao
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      LblProces.Caption := 'Obtendo informações da internet. Aguarde ...';
      LblProces.Refresh;
      PgbProgre.Position := 0;
      PgbProgre.Refresh;

      try
          BrvConexao.Execute;
      except
          on E : Exception do
             begin
                   if pSnConIni then
                   begin
                         MessageDlg('Não foi possível encontrar configuração do ' +
                                    'servidor de conexão em ' +
                                    BrvConexao.BrEnderecoSite , mtError, [mbOk], 0);
                   end;
                   lDsErro := E.Message;
             end;
      end;
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

      if lDsErro = '' then
      begin
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // Tentando conexão com o servidor interno
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            LblProces.Caption :=
                                'Estabelecendo conexão com servidor interno. Aguarde ...';
            LblProces.Refresh;
            PgbProgre.Position := 0;
            PgbProgre.Refresh;

            TCPClient.Port := BrvConexao.BrNumeroPorta;
            TCPClient.Host := BrvConexao.BrIpInterno;

            try
                TCPClient.Connect;
                Result := TCPClient.Connected;
            except
                on E : Exception do
                   begin
                         lDsErro := 'Não foi possível conectar-se com servidor interno:' +
                                    TCPClient.Host + ':' + IntToStr(TCPClient.Port) +
                                    #13 + E.Message;
                   end;
            end;

            if not TCPClient.Connected then
            begin
                  // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                  // Tentando conexão com o servidor Externo
                  // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                  LblProces.Caption :=
                                'Estabelecendo conexão com servidor externo. Aguarde ...';
                  LblProces.Refresh;
                  PgbProgre.Position := 0;
                  PgbProgre.Refresh;

                  TCPClient.Port := BrvConexao.BrNumeroPorta;
                  TCPClient.Host := BrvConexao.BrIpExterno;

                  try
                      TCPClient.Connect;
                      Result := TCPClient.Connected;
                  except
                      on E : Exception do
                         begin
                               lDsErro := lDsErro + #13#13 +
                                    'Não foi possível conectar-se com servidor externo' +
                                    TCPClient.Host + ':' + IntToStr(TCPClient.Port) +
                                    #13 + E.Message;
                         end;
                  end;
            end;

            if not Result then
            begin
                  if pSnConIni then
                  begin
                        MessageDlg(lDsErro, mtError, [mbOk], 0);
                  end;
            end else
            begin
                  Timer.Enabled     := False;
                  Visible := False;

                  if pSnConIni then
                  begin
                        Result  := RecebeEnderecoServidor;
                  end;
            end;
      end else
      begin
            if pSnConIni then
            begin
                  MessageDlg(lDsErro, mtError, [mbOk], 0);
            end;
      end;
end;

function TFrmConexa.RecebeEnderecoServidor : Boolean;
begin
      FrmAtuEnd := TFrmAtuEnd.Create(Self);

      try
          CarregarEnderecoDeConexao;
          FrmAtuEnd.EdtNoPorta.BrAsInteger := gNoPorta;
          FrmAtuEnd.EdtIpIntern.Text       := gIpIntern;
          FrmAtuEnd.EdtIpExtern.Text       := gIpExtern;

          if FrmAtuEnd.ShowModal = mrOk then
          begin
                Result := True;

                gNoPorta  := FrmAtuEnd.EdtNoPorta.BrAsInteger;
                gIpIntern := FrmAtuEnd.EdtIpIntern.Text;
                gIpExtern := FrmAtuEnd.EdtIpExtern.Text;
                gIpSerCon := TCPClient.Host;
                gNoPorCon := TCPClient.Port;
                gNmComput := FrmAtuEnd.BrvIp.BrNomeComputador;
                LblProces.Caption := 'Aguardando confirmação do servidor de conexão.';

                EnviaMensagemConexaoEstabelecida;
                GravaEnderecoDeConexao;
          end else
          begin
                Result := False;
          end;
      finally
          FreeAndNil(FrmAtuEnd);
      end;
end;

procedure TFrmConexa.EnviaMensagemDicionarioAtualizado;
begin
      if TCPClient.Connected then
      begin
            if uFrmLogos.cTpServer = 'A' then
            begin
                  TCPClient.Socket.WriteLn('20004@@');
            end else
            begin
                  TCPClient.Socket.WriteLn('40004@@');
            end;
      end;
end;

procedure TFrmConexa.EnviaMensagemClienteConectado(pNrClient: Integer; pDsClient: String);
begin
      if TCPClient.Connected then
      begin
            if uFrmLogos.cTpServer = 'A' then
            begin
                  TCPClient.Socket.WriteLn('20002'           + ';' +
                            gNmComput                        + ';' +
                            intToStr(pNrClient)              + ';' +
                            pDsClient                        + ';@@');
            end else
            begin
                  TCPClient.Socket.WriteLn('40002'           + ';' +
                            gNmComput                        + ';' +
                            intToStr(pNrClient)              + ';' +
                            pDsClient                        + ';@@');
            end;
      end;
end;

procedure TFrmConexa.EnviaMensagemConexaoEstabelecida;
begin
      TmrConexa.Enabled := True;

      if TCPClient.Connected then
      begin
            if uFrmLogos.cTpServer = 'A' then
            begin
                  TCPClient.Socket.WriteLn('20001'           + ';' +
                            gIpIntern                        + ';' +
                            gIpExtern                        + ';' +
                            IntToStr(gNoPorta)               + ';' +
                            gNmComput                        + ';@@');
            end else
            begin
                  TCPClient.Socket.WriteLn('40001'           + ';' +
                            gIpIntern                        + ';' +
                            gIpExtern                        + ';' +
                            IntToStr(gNoPorta)               + ';' +
                            gNmComput                        + ';@@');
            end;
      end;
end;

procedure TFrmConexa.EnviaMensagemClienteAutenticado(pCdUsuari : Integer;
                                 pDsLogin  : String; pNrConTcp : String);
begin
      if TCPClient.Connected then
      begin
            if uFrmLogos.cTpServer = 'A' then
            begin
                  TCPClient.Socket.WriteLn('20003'           + ';' +
                            pNrConTcp                        + ';' +
                            IntToStr(pCdUsuari)              + ';' +
                            pDsLogin                         + ';@@');
            end else
            begin
                  TCPClient.Socket.WriteLn('40003'           + ';' +
                            pNrConTcp                        + ';' +
                            IntToStr(pCdUsuari)              + ';' +
                            pDsLogin                         + ';@@');

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

          if UFrmLogos.cTpServer = 'I' then
          begin
                lIniFile := TIniFile.Create(lDsCaminh + cNmIniImp);
          end else
          begin
                lIniFile := TIniFile.Create(lDsCaminh + cNmIniApl);
          end;

          gIpIntern := lIniFile.ReadString('IPs', 'IpIntern', '');
          gIpExtern := lIniFile.ReadString('IPs', 'IpExtern', '');
          gNoPorta  := lIniFile.ReadInteger('IPs', 'NoPorta', 0);
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

          if UFrmLogos.cTpServer = 'I' then
          begin
                lIniFile := TIniFile.Create(lDsCaminh + cNmIniImp);
          end else
          begin
                lIniFile := TIniFile.Create(lDsCaminh + cNmIniApl);
          end;

          lIniFile.WriteString('IPs', 'IpIntern', gIpIntern);
          lIniFile.WriteString('IPs', 'IpExtern', gIpIntern);
          lIniFile.WriteString('IPs', 'NoPorta',  IntToStr(gNoPorta));
      finally
          lIniFile.Destroy;
      end;
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

procedure TFrmConexa.TimerTimer(Sender: TObject);
begin
      PgbProgre.StepIt;
      PgbProgre.Refresh;
end;

procedure TFrmConexa.TmrConexaTimer(Sender: TObject);
begin
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// =-=-=-= Redundância com a função 10002
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      if (FrmLogos <> nil) and (not FrmLogos.TmrConexa.Enabled) then
      begin
            try
                TCPClient.Socket.WriteLn('teste@@');
            except
                IniciarProcessoReconexao(20);
            end;
      end;
end;

procedure TFrmConexa.IniciarProcessoReconexao(pQtSegund : Integer);
begin
      TmrConexa.Enabled   := False;

      try
          TCPClient.Disconnect;
      except

      end;

      LblProces.Caption := 'Perda de sinal com o servidor de conexão...';
      FrmLogos.EdtDsOperac.Text    := LblProces.Caption;
      FrmLogos.PgbProcess.Position := 0;
      FrmLogos.PgbProcess.Max      := pQtSegund;
      FrmLogos.TmrConexa.Enabled   := True;
end;

procedure TFrmConexa.ProcessarMensagemServerConexao(pDsMensag : String);
var lBrvString : TBrvString;
    lNdAuxili  : TTreeNode;
begin
      lBrvString := TBrvString.Create(self);

      try
          lBrvString.Split(pDsMensag, ';');

          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-= Servidor confirmando entrada
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          if  lBrvString.BrSplitLista.Strings[0] = '10001' then
          begin
                LblProces.Caption := lBrvString.BrSplitLista.Strings[1];
          end
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-= Servidor de conexão saiu
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          else if  lBrvString.BrSplitLista.Strings[0] = '10002' then
               begin
                     LblProces.Caption := lBrvString.BrSplitLista.Strings[1];

                     if FrmLogos <> nil then
                     begin
                           IniciarProcessoReconexao(StrToInt(
                                                     lBrvString.BrSplitLista.Strings[2]));
                     end;
               end
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-= Cliente Desconectou-se
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          else if  lBrvString.BrSplitLista.Strings[0] = '10003' then
               begin
                     lNdAuxili := FrmLogos.LocalizarNodeConexaoTCP(
                                                     lBrvString.BrSplitLista.Strings[1]);
                     if lNdAuxili <> nil then
                     begin
                           lNdAuxili.Delete;
                     end;
               end;

          if FrmLogos <> nil then
          begin
                FrmLogos.EdtDsOperac.Text := LblProces.Caption;
          end;
      finally
          FreeAndNil(lBrvString);
      end;
end;

procedure TFrmConexa.AtualizarDicionarioDados(pSnMensag : Boolean);
begin
      DmDicion.BrvDicionario.CarregarDicionario(nil, nil, '');
      EnviaMensagemDicionarioAtualizado;

      if pSnMensag then
      begin
            MessageDlg('Dicionário de dados foi atualizado.', mtInformation, [mbOk], 0);
      end;
end;

end.
