unit UFrmSerCon;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms, Dialogs, IdContext,
  IdBaseComponent, IdComponent, IdCustomTCPServer, IdTCPServer, IdTCPConnection, IdHTTP, StdCtrls,
  Buttons, BrvBitBtn, ExtCtrls, BrvEditNum, BrvSrvConexao, IdTCPClient, ComCtrls, ImgList, DB,
  BrvString, DBClient, ShellAPI, Menus, Mask, DBCtrls, BrvSpeedButton, Grids, DBGrids,
  BrvDbGrid, BrvDbGrids, SyncObjs;

const
  WM_MIDASICON = WM_USER + 1;

type
  {ponteiro para o registro que armazena as informações dos clientes.
  Com este ponteiro é feita a alocação dinâmica dos dados dos clientes
  conectados. Quando um cliente disconecta, as informações dele
  que estavam sendo guardadas são liberadas dinamicamente da memória.}
  PConexaTcp = ^TConexaTcp;
  TConexaTcp = record
    Connection : TidTCPConnection;
    NmComput   : array[0..35] of char;
    NmServer   : array[0..35] of char;
    NrConTcp   : Integer;
    NrConCli   : Integer;
  end;

  TFrmSerCon = class(TForm)
    TCPServer: TIdTCPServer;
    Panel1: TPanel;
    Panel2: TPanel;
    Panel3: TPanel;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    EdtNoIpInte: TEdit;
    EdtNoIpExte: TEdit;
    BrvConexao: TBrvSrvConexao;
    TrvServer: TTreeView;
    ImgLista: TImageList;
    TblServer: TClientDataSet;
    PopPrincip: TPopupMenu;
    PopExit: TMenuItem;
    N1: TMenuItem;
    PopMosCon: TMenuItem;
    PgcConfig: TPageControl;
    TbsNada: TTabSheet;
    TbsServer: TTabSheet;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    Label9: TLabel;
    Label10: TLabel;
    Label11: TLabel;
    Panel4: TPanel;
    EdtNmSetor: TEdit;
    EdtNmServer: TEdit;
    EdtIpIntern: TEdit;
    EdtIpExtern: TEdit;
    EdtNoPorta: TEdit;
    Label12: TLabel;
    BrvSpeedButton1: TBrvSpeedButton;
    BrvSpeedButton2: TBrvSpeedButton;
    BrvString: TBrvString;
    TblIp: TClientDataSet;
    GroupBox1: TGroupBox;
    BrvDbGrid1: TBrvDbGrid;
    DtsIp: TDataSource;
    EdtNoPorCon: TEdit;
    EdtQtMaxCon: TEdit;
    EdtQtConAtu: TEdit;
    procedure TCPServerExecute(AContext: TIdContext);
    procedure FormCreate(Sender: TObject);
    procedure TCPServerConnect(AContext: TIdContext);
    procedure TCPServerDisconnect(AContext: TIdContext);
    procedure PopMosConClick(Sender: TObject);
    procedure PopExitClick(Sender: TObject);
    procedure FormCloseQuery(Sender: TObject; var CanClose: Boolean);
    procedure TrvServerChange(Sender: TObject; Node: TTreeNode);
    procedure BrvSpeedButton2Click(Sender: TObject);
    procedure BrvSpeedButton1Click(Sender: TObject);
    procedure TblServerAfterScroll(DataSet: TDataSet);
    procedure TblIpAfterInsert(DataSet: TDataSet);
  private
    { Private declarations }
    gNrConTcp : Integer;
    gIconData : TNotifyIconData;
    gNdNode   : PString;
    gNdSetApl : TTreeNode;
    gNdSerApl : TTreeNode;
    gNdSerImp : TTreeNode;
    gNdPrinci : TTreeNode;
    gNdSetImp : TTreeNode;
    gStlFluxo : TStrings; // lista para organizar o fluxo das requisições
    gNrFluxo  : Integer;
    gCsSectio : TCriticalSection;
    procedure CriarTryIcon;
    procedure EliminarSysTry(pSnSair : Boolean);
    procedure WMEndQuerySession(var Msg : TWMQueryEndSession); message WM_QueryENDSESSION;
    procedure ProcessarMensagemServidor(pDsMensag: AnsiString; pConAtu : PConexaTcp;
                                           pAContext: TIdContext);
    procedure CriarTabelaServidores;
    function EncontraNodeConexao(pNrConexa : Integer): TTreeNode;
    function EncontraNodeNome(pNmNode : AnsiString): TTreeNode;
    function CriarNode(pNdNode  : TTreeNode; pDsNode : AnsiString; pTpNode : Char;
                       pIdNode  : AnsiString): TTreeNode;
    procedure AtualizaStatusServidor(pNmServer : AnsiString;  pIpIntern : AnsiString;
                                     pIpExtern : AnsiString;  pNoPorta  : AnsiString;
                                     pQtMaxCon : Integer;     pNmSetor  : AnsiString;
                                     pStServer : AnsiString;  pTpServer : AnsiString);
    procedure SalvarServidores(pTblServer : OleVariant; pTblIp : OleVariant);
    procedure MoverServidorDeSetor(pNmServer, pNmSetAnt, pNmSetor: AnsiString;
                                   pTpServer : AnsiString);
    procedure CarregarServidores;
    procedure EnviarMensagemDesconexao;
    procedure ProcessarMensagemCliente(pDsMensag: AnsiString; pConAtuTcp : PConexaTcp;
                                       pAContext: TIdContext);
    function ListaDeServidoresParaConexao(pTblSerDat: OleVariant;
                  pTblClient: OleVariant; pIpClient : AnsiString;
                  pTpServer : AnsiString): AnsiString;
    procedure AtualizarDadosDoCliente(pNmServer: AnsiString; pNrConTCP: AnsiString;
                                      pNrConCli: AnsiString);
    function LocalizaConexaoServer(pNmServer : AnsiString) : PConexaTcp;
    procedure EnviarMenagemClienteServidorSaiu(pNmServer : AnsiString);
    procedure CarregarClientesDosServidores;
    procedure AtualizarQuantidadeConexoesAtuaisDoServidor(lNdServer: TTreeNode);
    function MensagemServidorDesativado: AnsiString;
    procedure EnviarMensagemAtualizarDicionarioCliente(pAContext: TIdContext);
    procedure OtimizarFluxo;
  public
    { Public declarations }
    function AtualizarEnderecoConexao : Boolean;
    procedure ConectarServer;
    procedure InicializarTreeVeiew;
  protected
    procedure WMMIDASIcon(var Message : TMessage); message WM_MIDASICON;
  end;

var
  FrmSerCon: TFrmSerCon;
  connL: TList;

implementation

uses UFrmAtuEnd;

{$R *.dfm}

const gNrImgSet = 1;
      gNrSrvOff = 2;
      gNrSrvOn  = 3;
      gNrUsrOff = 4;
      gNrUsrOn  = 5;
      gNrSrvApl = 6;
      gNrSrvImp = 7;

  { TFrmSerCon }

procedure TFrmSerCon.WMMIDASIcon(var Message : TMessage);
var Pt : TPoint;
begin
   // =-=-=-=-= procedimento chamado quando o mouse passa sobre
   // =-=-=-=-= o ícone do servidor
      case Message.LParam of
        WM_RBUTTONUP: begin
                            if not Visible then
                            begin
                               // =-=-= põe a janela especificada em primeiro
                               // =-=-= plano e a ativa
                                  SetForegroundWindow(Handle);
                               // =-=-= retorna a posição do cursor,
                               // =-=-= em coordenadas de tela
                                  GetCursorPos(pt);
                                  PopupMenu.Popup(pt.x, pt.y);
                            end;
                      end;
        WM_LBUTTONDBLCLK : if not Visible then
                           begin
                                 PopMosConClick(PopMosCon);
                           end else
                           begin
                                 SetForegroundWindow(Handle);
                           end;
      end;
end;

procedure TFrmSerCon.CriarTryIcon;
begin
   // =-=-=-=-= criando TrayIcon ao lado do relógio
      gIconData.CbSize           := SizeOf(gIconData);
      gIconData.Wnd              := Self.Handle;
      gIconData.uID              := $DEDB;
      gIconData.uFlags           := NIF_Message or NIF_ICON or NIF_TIP;
      gIconData.hIcon            := Application.Icon.Handle;
      gIconData.uCallbackMessage := WM_MIDASICON;
      StrCopy(gIconData.szTip, PChar(Caption));

      Shell_NotifyIcon(NIM_Add, @gIconData);
end;

procedure TFrmSerCon.EliminarSysTry(pSnSair: Boolean);
begin
      Shell_NotifyIcon(NIM_DELETE, @gIconData);

      if pSnSair then
      begin
            Application.Terminate;
      end;
end;

procedure TFrmSerCon.WMEndQuerySession(var Msg : TWMQueryEndSession);
begin
      Msg.Result := 1;
      Tag := 0;
      Close;
      inherited;
end;

procedure TFrmSerCon.FormCreate(Sender: TObject);
var lNrAba  : Integer;
begin
      gCsSectio := TCriticalSection.Create;
      gStlFluxo := TStringList.Create;
      gNrFluxo  := 0;

      // numero aleatório para não chocar clientes quando os servidores forem reiniciados
      Randomize;
      gNrConTcp := Random(100) * 1000000;

      CriarTryIcon;
      CriarTabelaServidores;
      connL := TList.Create;

      for lNrAba := 0 to PgcConfig.PageCount -1 do
      begin
            PgcConfig.Pages[lNrAba].TabVisible := False;
      end;
      PgcConfig.ActivePage := TbsNada;
end;

procedure TFrmSerCon.CriarTabelaServidores;
begin
      // =-=-=-=-=-= Criando Tabela de servidores
      TblServer.FieldDefs.Clear;

      TblServer.FieldDefs.Add('NmServer', ftString,   30, False);
      TblServer.FieldDefs.Add('IpIntern', ftString,   20, False);
      TblServer.FieldDefs.Add('IpExtern', ftString,   20, False);
      TblServer.FieldDefs.Add('NoPorta',  ftInteger,   0, False);
      TblServer.FieldDefs.Add('QtMaxCon', ftInteger,   0, False);
      TblServer.FieldDefs.Add('QtConAtu', ftInteger,   0, False);
      TblServer.FieldDefs.Add('NmSetor',  ftString,   30, False);
      TblServer.FieldDefs.Add('SnAtivo',  ftString,    1, False);
      TblServer.FieldDefs.Add('DsConexa', ftBlob,      0, False);
      TblServer.FieldDefs.Add('TpServer', ftString,    1, False);

      TblServer.IndexDefs.Clear;
      TblServer.IndexDefs.Add('Key1', 'NmServer', [ixPrimary, ixUnique]);
      TblServer.CreateDataSet;

      TblServer.IndexName := 'Key1';
      TblServer.SetKey;

      // =-=-=-=-=-= Criando tabela de clientes (IP)
      TblIp.FieldDefs.Clear;

      TblIp.FieldDefs.Add('NmServer', ftString,   30, False);
      TblIp.FieldDefs.Add('IpClient', ftString,   15, False);

      TblIp.IndexDefs.Clear;
      TblIp.IndexDefs.Add('Key1', 'NmServer;IpClient', [ixPrimary, ixUnique]);
      TblIp.CreateDataSet;

      TblIp.IndexName := 'Key1';
      TblIp.SetKey;
end;

procedure TFrmSerCon.TblIpAfterInsert(DataSet: TDataSet);
begin
      TblIp.FieldByName('NmServer').AsString := TblServer.FieldByName('NmServer').AsString;
end;

procedure TFrmSerCon.TblServerAfterScroll(DataSet: TDataSet);
begin
      TblIp.Filter := 'NmServer = ' + #39 + DataSet.FieldByName('NmServer').AsString + #39;
end;

procedure TFrmSerCon.FormCloseQuery(Sender: TObject; var CanClose: Boolean);
begin
      CanClose := Tag = 0;

      if not CanClose then
      begin
            PopMosCon.Tag     := 0;
            PopMosCon.Caption := 'Mostrar Conexões';
            Visible := CanClose;
      end else
      begin
            EnviarMensagemDesconexao;
            EliminarSysTry(True);
      end;
end;

procedure TFrmSerCon.EnviarMensagemDesconexao;
var lNrConexa  : Integer;
    lConexaTcp : PConexaTcp;
begin
      for lNrConexa := 0 to connL.Count -1 do
      begin
            try
                lConexaTcp := PConexaTcp(connL[lNrConexa]);
                lConexaTcp.Connection.Socket.WriteLn(MensagemServidorDesativado);
            finally
              //FreeMem(ConAux);
            end;
      end;
end;

function TFrmSerCon.MensagemServidorDesativado : AnsiString;
begin
      Result := '10002;Servidor de conexão desativado. Verificando conexão...;20@@';
end;

procedure TFrmSerCon.PopExitClick(Sender: TObject);
begin
      if MessageDlg('Confirma encerramento da aplicação?',
                                                      MtConfirmation, [MbYes, MbNo], 0) = IdYes then
      begin
            Tag := 0;
            Close;
      end;
end;

procedure TFrmSerCon.PopMosConClick(Sender: TObject);
begin
      if (Sender as TMenuItem).Tag = 0 then
      begin
            Tag := 1;
            (Sender as TMenuItem).Tag := 1;
            Visible := True;
            (Sender as TMenuItem).Caption := 'Ocultar Conexões';
      end else
      begin
            Tag := 1;
            (Sender as TMenuItem).Tag := 0;
            Close;
      end;
end;

function TFrmSerCon.AtualizarEnderecoConexao: Boolean;
var lDsParams : TStrings;
    lDsResult : AnsiString;
begin
      Result    := False;
      FrmAtuEnd := TFrmAtuEnd.Create(Self);
      lDsParams := TStringList.Create;
      //  =-=-= Carregando configurações anteriores
      try
          BrvConexao.Execute;
      except
          MessageDlg('Não foi possível encontrar configuração do servidor de conexão em ' +
                     BrvConexao.BrEnderecoSite , mtError, [mbOk], 0);
          EliminarSysTry(True);
          raise;
      end;
      FrmAtuEnd.EdtIpIntern.Text := BrvConexao.BrIpInterno;
      FrmAtuEnd.EdtIpExtern.Text := BrvConexao.BrIpExterno;
      FrmAtuEnd.EdtNoPorta.Text  := FormatFloat('0', BrvConexao.BrNumeroPorta);
      //  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

      lDsResult := '';

      try
          if FrmAtuEnd.ShowModal = mrOk then
          begin
                //  =-=-= Atualizando Endereços IP no site
                lDsParams.add('NoIpInte=' + FrmAtuEnd.EdtIpIntern.Text);
                lDsParams.add('NoIpExte=' + FrmAtuEnd.EdtIpExtern.Text);
                lDsParams.add('NoPorta='  + FrmAtuEnd.EdtNoPorta.Text);

                try
                    lDsResult := BrvConexao.Post(lDsParams);
                except
                     on E : Exception Do
                        begin
                              MessageDlg('Erro ao tentar gravar enderços IPs no site',
                                                                                mtError, [mbOk], 0);
                              lDsResult := E.Message;
                        end;
                end;

                if Trim(lDsResult) = 'OK' then
                begin
                      TCPServer.DefaultPort := StrToInt(FrmAtuEnd.EdtNoPorta.Text);
                      EdtNoIpInte.Text      := FrmAtuEnd.EdtIpIntern.Text;
                      EdtNoIpExte.Text      := FrmAtuEnd.EdtIpExtern.Text;
                      EdtNoPorCon.Text      := FrmAtuEnd.EdtNoPorta.Text;
                      Result := True;
                end else
                begin
                      MessageDlg(lDsResult, mtError, [mbOk], 0);
                end;
                //  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          end;
      finally
           FreeAndNil(FrmAtuEnd);
           FreeAndNil(lDsParams);
      end;
end;

procedure TFrmSerCon.ConectarServer;
begin
      try
          TCPServer.Active := True;
      except
          on E : Exception Do
             begin
                   MessageDlg('Não foi possível ativar o Servidor.' + #13#13 +
                                                                     E.Message, mtError, [mbOk], 0);
             end;
      end;
end;

procedure TFrmSerCon.InicializarTreeVeiew;
var lNdNovo : TTreeNode;
begin
      TrvServer.Items.Clear;
      new(gNdNode);
      lNdNovo := TrvServer.Items.AddObject(nil, 'Servidores', gNdNode);
      PString(lNdNovo.Data)^ := 'SERVERS';
      TrvServer.Selected     := lNdNovo;
      gNdPrinci              := lNdNovo;

      inc(gNrConTcp);
      lNdNovo   := CriarNode(gNdPrinci, 'Aplicação', 'A', IntToStr(gNrConTcp));
      gNdSerApl := lNdNovo;

      inc(gNrConTcp);
      lNdNovo   := CriarNode(gNdSerApl, 'Sem Setor', 'T', IntToStr(gNrConTcp));
      gNdSetApl := lNdNovo;

      inc(gNrConTcp);
      lNdNovo   := CriarNode(gNdPrinci, 'Impressão', 'I', IntToStr(gNrConTcp));
      gNdSerImp := lNdNovo;

      inc(gNrConTcp);
      lNdNovo   := CriarNode(gNdSerImp, 'Sem Setor', 'T', IntToStr(gNrConTcp));
      gNdSetImp := lNdNovo;

      CarregarServidores;
      CarregarClientesDosServidores;

      TrvServer.FullExpand;
end;

procedure TFrmSerCon.TCPServerConnect(AContext: TIdContext);
var lConexaTcp : PConexaTcp;
begin
       GetMem(lConexaTcp, SizeOf(TConexaTcp));
       lConexaTcp.Connection := AContext.Connection;

       inc(gNrConTcp);
       lConexaTcp.NrConTcp := gNrConTcp;
       BrvString.StrToChar('vazio', lConexaTcp.NmComput, 30);

       AContext.Data       := TObject(lConexaTcp);
       connL.Add(lConexaTcp);
end;

procedure TFrmSerCon.TCPServerDisconnect(AContext: TIdContext);
var lConexaTcp : PConexaTcp;
    lConServer : PConexaTcp;
    lNdConexa  : TTreeNode;
    lNdServer  : TTreeNode;
    lNrConCli  : Integer;
begin
      lConexaTcp := PConexaTcp(AContext.Data);
      connL.Remove(lConexaTcp);
      lNrConCli  := lConexaTcp.NrConCli;
      lNdConexa  := EncontraNodeConexao(lConexaTcp.NrConTcp);

      if lNdConexa = nil then // não é cliente, possivelmente servidor
      begin
            lNdConexa  := EncontraNodeNome(BrvString.CharToStr(lConexaTcp.NmComput, 30));

            if lNdConexa <> nil then // é servidor
            begin
                  lNdConexa.ImageIndex    := gNrSrvOff;
                  lNdConexa.SelectedIndex := gNrSrvOff;
                  lNdConexa.DeleteChildren;

                  EnviarMenagemClienteServidorSaiu(lNdConexa.Text);

                  if TblServer.FindKey([lNdConexa.Text]) then
                  begin
                        TblServer.Edit;
                        TblServer.FieldByName('SnAtivo').AsString   := 'N';
                        TblServer.FieldByName('QtConAtu').AsInteger := 0;
                        TblServer.Post;
                  end;
            end;
      end else
      begin
            lNdServer  := lNdConexa.Parent;
            lConServer := LocalizaConexaoServer(BrvString.CharToStr(lConexaTcp.NmServer, 30));

            if lConServer <> nil  then
            begin
                  lConServer.Connection.Socket.WriteLn('10003;' + IntToStr(lNrConCli) + '@@');
            end;

            lNdConexa.Delete;
            AtualizarQuantidadeConexoesAtuaisDoServidor(lNdServer);
      end;

      FreeMem(lConexaTcp);
      AContext.Data := nil;
end;

procedure TFrmSerCon.EnviarMenagemClienteServidorSaiu(pNmServer : AnsiString);
var lNrConexa  : Integer;
    lConexaTcp : PConexaTcp;
begin
      lNrConexa := 0;
      while lNrConexa < connL.Count do
      begin
            lConexaTcp := PConexaTcp(connL[lNrConexa]);

            if BrvString.CharToStr(lConexaTcp.NmServer, 30) = pNmServer then
            begin
                  if Pos('IMP_', UpperCase(pNmServer)) > 0 then
                  begin
                        lConexaTcp.Connection.Socket.WriteLn(
                                             '10008;Servidor de impressão está desconectado.;10@@');
                  end else
                  begin
                        lConexaTcp.Connection.Socket.WriteLn(
                                             '10003;Servidor de aplicação está desconectado.;10@@');
                  end;
            end;

            inc(lNrConexa);
      end;
end;

function TFrmSerCon.LocalizaConexaoServer(pNmServer : AnsiString) : PConexaTcp;
var lNrConexa  : Integer;
    lConexaTcp : PConexaTcp;
begin
      Result    := nil;
      lNrConexa := 0;
      while lNrConexa < connL.Count do
      begin
            lConexaTcp := PConexaTcp(connL[lNrConexa]);

            if BrvString.CharToStr(lConexaTcp.NmComput, 30) = pNmServer then
            begin
                  Result := lConexaTcp;
                  lNrConexa := connL.Count + 10000;
            end;

            inc(lNrConexa);
      end;
end;

procedure TFrmSerCon.TCPServerExecute(AContext: TIdContext);
var lDsMensag  : AnsiString;
    lNrMensag  : Integer;
    lConexaTcp : PConexaTcp;
    lBrvString : TBrvString;
    lDsMsgOri  : AnsiString;
begin
      lDsMensag := Trim(AContext.Connection.Socket.ReadLn());
      lDsMsgOri := lDsMensag;

      if lDsMensag <> '' then
      begin
            OtimizarFluxo;

            lConexaTcp := PConexaTcp(AContext.Data);
            lBrvString := TBrvString.Create(self);

            try
                lBrvString.Split(lDsMensag, '@@');

                for lNrMensag := 0 to lBrvString.BrSplitLista.Count -1 do
                begin
                      lDsMensag := lBrvString.BrSplitLista.Strings[lNrMensag];
                      if (lDsMensag[1] = '2') or   // originado do servidor de aplicação
                         (lDsMensag[1] = '4') then // originado do servidor de impressao
                      begin
                            ProcessarMensagemServidor(lBrvString.BrSplitLista.Strings[lNrMensag],
                                                      lConexaTcp, AContext);
                      end else
                      begin
                            if lDsMensag[1] = '3' then // originado do cliente
                            begin
                                  ProcessarMensagemCliente(
                                                         lBrvString.BrSplitLista.Strings[lNrMensag],
                                                         lConexaTcp, AContext);
                            end
                      end;
                end;
            finally
                FreeAndNil(lBrvString);
            end;
      end;
end;

procedure TFrmSerCon.OtimizarFluxo;
var lNrFluxo : String;
begin
      gCsSectio.Enter;

      inc(gNrFluxo);
      lNrFluxo := IntToStr(gNrFluxo);

      if gNrFluxo > 1000000000 then
      begin
            gNrFluxo := 0;
      end;

      gStlFluxo.Add(lNrFluxo);

      repeat

      until gStlFluxo.Strings[0] = lNrFluxo;

      gStlFluxo.Delete(0);

      gCsSectio.Leave;
end;

procedure TFrmSerCon.ProcessarMensagemCliente(pDsMensag  : AnsiString;
                                              pConAtuTcp : PConexaTcp;
                                              pAContext: TIdContext);
var lBrvString : TBrvString;
    lNdServer  : TTreeNode;
    lNdNovo    : TTreeNode;
begin
      lBrvString := TBrvString.Create(self);

      try
          lBrvString.Split(pDsMensag, ';');
          // =-=-= Servidor solicitando entrada

          if  lBrvString.BrSplitLista.Strings[0] = '30001' then
          begin
                if lBrvString.BrSplitLista.Strings[1] <> 'zero' then
                begin
                      pConAtuTcp.NrConTcp := StrToInt(lBrvString.BrSplitLista.Strings[1]);
                      lNdServer := EncontraNodeNome(lBrvString.BrSplitLista.Strings[2]);

                      if lNdServer <> nil then
                      begin
                            BrvString.StrToChar(
                             lBrvString.BrSplitLista.Strings[2], pConAtuTcp.NmServer, 30);

                            pConAtuTcp.NrConCli := StrToInt(lBrvString.BrSplitLista.Strings[4]);

                            lNdNovo := CriarNode(lNdServer, lBrvString.BrSplitLista.Strings[3], 'U',
                                                            lBrvString.BrSplitLista.Strings[1]);
                      end else
                      begin
                            pAContext.Connection.Socket.WriteLn(MensagemServidorDesativado);
                      end;
                end;

                if lBrvString.BrSplitLista.Strings[5] = 'A' then
                begin
                      pAContext.Connection.Socket.WriteLn('10004;' +
                                ListaDeServidoresParaConexao(
                                       TblServer.Data,
                                       TblIp.Data,
                                       pAContext.Connection.Socket.Binding.PeerIP,
                                       lBrvString.BrSplitLista.Strings[5]) +
                                       '@@');

                      pAContext.Connection.Socket.WriteLn('10001;' +
                                       IntToStr(pConAtuTcp.NrConTcp) + ';' +
                                       pAContext.Connection.Socket.Binding.PeerIP +
                                       '@@');
                end else
                begin
                      pAContext.Connection.Socket.WriteLn('10006;' +
                                       IntToStr(pConAtuTcp.NrConTcp) + ';' +
                                       pAContext.Connection.Socket.Binding.PeerIP +
                                       '@@');

                      pAContext.Connection.Socket.WriteLn('10007;' +
                                ListaDeServidoresParaConexao(
                                       TblServer.Data,
                                       TblIp.Data,
                                       pAContext.Connection.Socket.Binding.PeerIP,
                                       lBrvString.BrSplitLista.Strings[5]) +
                                       '@@');

                end;
          end;
      finally
           FreeAndNil(lBrvString);
      end;
end;

function TFrmSerCon.ListaDeServidoresParaConexao(pTblSerDat : OleVariant;
                        pTblClient : OleVariant; pIpClient  : AnsiString;
                        pTpServer  : AnsiString) : AnsiString;
var lTblServer : TClientDataSet;
    lTblClient : TClientDataSet;
begin
      try
          Result     := '';

          lTblServer           := TClientDataSet.Create(Self);
          lTblServer.Filtered  := True;
          lTblServer.Data      := pTblSerDat;


          // =-=-=-= Encontrando servidor onde o cliente deve se conectar
          lTblClient          := TClientDataSet.Create(self);
          lTblClient.Data     := pTblClient;
          lTblClient.Filtered := True;
          lTblClient.Filter   := 'IpClient = ' + #39 + pIpClient + #39;

          while not lTblClient.Eof do
          begin
                lTblServer.Filter := 'NmServer = ' + #39 +
                                     lTblClient.FieldByName('NmServer').AsString  + #39 +
                                     ' and TpServer = ' + #39 + pTpServer + #39;
                lTblServer.First;

                while (not lTblServer.Eof) do
                begin
                      if (lTblServer.FieldByName('SnAtivo').AsString = 'S') and

                         ((lTblServer.FieldByName('QtConAtu').AsInteger <
                           lTblServer.FieldByName('QtMaxCon').AsInteger) or
                          (lTblServer.FieldByName('QtMaxCon').AsInteger = 0)) then
                      begin
                            Result := Result +
                                lTblServer.FieldByName('NmServer').AsString + '%%' +
                                lTblServer.FieldByName('IpIntern').AsString + '%%' +
                                lTblServer.FieldByName('IpExtern').AsString + '%%' +
                                lTblServer.FieldByName('NoPorta').AsString  + '%%' + '##';
                      end;

                      lTblServer.Next;
                end;

                lTblClient.Next;
          end;

          // =-=-=-= Passando a lista de todos os servidores ativos
          lTblServer.Filtered := False;
          lTblServer.Filter   := 'TpServer = ' + #39 + pTpServer + #39;
          lTblServer.Filtered := True;
          lTblServer.First;

          while not lTblServer.Eof do
          begin
                if (lTblServer.FieldByName('SnAtivo').AsString = 'S') and

                   ((lTblServer.FieldByName('QtConAtu').AsInteger <
                     lTblServer.FieldByName('QtMaxCon').AsInteger) or
                    (lTblServer.FieldByName('QtMaxCon').AsInteger = 0)) then
                begin
                      Result := Result +
                                lTblServer.FieldByName('NmServer').AsString + '%%' +
                                lTblServer.FieldByName('IpIntern').AsString + '%%' +
                                lTblServer.FieldByName('IpExtern').AsString + '%%' +
                                lTblServer.FieldByName('NoPorta').AsString  + '%%' + '##';
                end;

                lTblServer.Next;
          end;

          if Result = '' then
          begin
                Result := 'ER; Não há servidores de aplicação disponíveis no momento';
          end;
      finally
          FreeAndNil(lTblServer);
          FreeAndNil(lTblClient);
      end;
end;

procedure TFrmSerCon.ProcessarMensagemServidor(pDsMensag : AnsiString; pConAtu : PConexaTcp;
                                               pAContext : TIdContext);
var lNdServer  : TTreeNode;
    lNdNovo    : TTreeNode;
    lBrvString : TBrvString;
    lNmServer  : AnsiString;
    lTpServer  : AnsiString;
begin
      lBrvString := TBrvString.Create(self);

      try
          lBrvString.Split(pDsMensag, ';');

          // =-=-= Servidor solicitando entrada
          if  (lBrvString.BrSplitLista.Strings[0] = '20001') or   // aplicação
              (lBrvString.BrSplitLista.Strings[0] = '40001') then // impressão
          begin
                if  (lBrvString.BrSplitLista.Strings[0] = '20001') then    // aplicação
                begin
                      lNmServer := 'Apl_' + lBrvString.BrSplitLista.Strings[4];
                end else
                begin
                      lNmServer := 'Imp_' + lBrvString.BrSplitLista.Strings[4];
                end;

                lNdServer := EncontraNodeNome(lNmServer);
                if lNdServer = nil then
                begin
                      inc(gNrConTcp);
                      if  (lBrvString.BrSplitLista.Strings[0] = '20001') then // aplicação
                      begin
                            lNdServer := CriarNode(gNdSetApl, lNmServer, 'S', lNmServer);
                      end else
                      begin // impressão
                            lNdServer := CriarNode(gNdSetImp, lNmServer, 'S', lNmServer);
                      end;
                end;

                lNdServer.ImageIndex    := gNrSrvOn;
                lNdServer.SelectedIndex := gNrSrvOn;

                if  (lBrvString.BrSplitLista.Strings[0] = '20001') then // aplicação
                begin
                      lTpServer := 'A';
                end else
                begin // impressão
                      lTpServer := 'I';
                end;

                AtualizaStatusServidor(lNmServer,
                                       lBrvString.BrSplitLista.Strings[1],
                                       lBrvString.BrSplitLista.Strings[2],
                                       lBrvString.BrSplitLista.Strings[3],
                                       -1,
                                       lNdServer.Parent.Text,
                                       'S',
                                       lTpServer);
                BrvString.StrToChar(lNmServer, pConAtu.NmComput, 30);

                pAContext.Connection.Socket.WriteLn(
                                            '10001;Conectado ao servidor de conexões;@@');
          end

          // =-=-= Informando a entrada de um cliente
          else if  (lBrvString.BrSplitLista.Strings[0] = '20002') or // aplicação
                   (lBrvString.BrSplitLista.Strings[0] = '40002') then // impressão
          begin
                if  (lBrvString.BrSplitLista.Strings[0] = '20002') then    // aplicação
                begin
                      lNmServer := 'Apl_' + lBrvString.BrSplitLista.Strings[1];
                end else
                begin
                      lNmServer := 'Imp_' + lBrvString.BrSplitLista.Strings[1];
                end;

                lNdServer := EncontraNodeNome(lNmServer);
                if lNdServer <> nil then
                begin
                      lNdNovo := CriarNode(lNdServer, lBrvString.BrSplitLista.Strings[3],
                                           'U',       lBrvString.BrSplitLista.Strings[5]);
                      AtualizarDadosDoCliente(lNmServer,
                                              lBrvString.BrSplitLista.Strings[5],
                                              lBrvString.BrSplitLista.Strings[2]);

                      AtualizarQuantidadeConexoesAtuaisDoServidor(lNdServer);
                end;
          end
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-= Informando a autenticação do cliente
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          else if  (lBrvString.BrSplitLista.Strings[0] = '20003') or // aplicação
                   (lBrvString.BrSplitLista.Strings[0] = '40003') then // impressão
          begin
                lNdServer := EncontraNodeNome(lBrvString.BrSplitLista.Strings[1]);
                if lNdServer <> nil then
                begin
                      if Pos(lBrvString.BrSplitLista.Strings[3], lNdServer.Text) = 0  then
                      begin
                            lNdServer.Text := lNdServer.Text + ' - ' +
                                              lBrvString.BrSplitLista.Strings[2] + ' - ' +
                                              lBrvString.BrSplitLista.Strings[3];
                      end;
                      lNdServer.ImageIndex    := gNrUsrOn;
                      lNdServer.SelectedIndex := gNrUsrOn;
                end;
          end
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-= Dicionário de dados do servidor atualizado.
          // =-=-= Atualizar dicionário dos clientes também
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          else if  lBrvString.BrSplitLista.Strings[0] = '20004' then
               begin
                     EnviarMensagemAtualizarDicionarioCliente(pAContext);
               end
          ;
      finally
           FreeAndNil(lBrvString);
      end;
end;

procedure TFrmSerCon.EnviarMensagemAtualizarDicionarioCliente(pAContext : TIdContext);
var lConexaTcp : PConexaTcp;
    lConServer : PConexaTcp;
    lNdConexa  : TTreeNode;
    lNdServer  : TTreeNode;
    lNrConCli  : Integer;
begin
      lConexaTcp := PConexaTcp(pAContext.Data);
      lNrConCli  := lConexaTcp.NrConCli;
      lNdConexa  := EncontraNodeNome(BrvString.CharToStr(lConexaTcp.NmComput, 30));

      if lNdConexa <> nil then // é servidor
      begin
            lNrConCli := 0;
            while lNrConCli < connL.Count do
            begin
                  lConexaTcp := PConexaTcp(connL[lNrConCli]);

                  if BrvString.CharToStr(lConexaTcp.NmServer, 30) = lNdConexa.Text then
                  begin
                        lConexaTcp.Connection.Socket.WriteLn('10005@@');
                  end;

                  inc(lNrConCli);
            end;
      end;
end;

procedure TFrmSerCon.AtualizarQuantidadeConexoesAtuaisDoServidor(lNdServer : TTreeNode);
begin
      if TblServer.FindKey([lNdServer.Text]) then
      begin
            TblServer.Edit;
            TblServer.FieldByName('QtConAtu').AsInteger := lNdServer.Count;
            TblServer.Post;
      end;
end;

procedure TFrmSerCon.AtualizarDadosDoCliente(pNmServer : AnsiString; pNrConTCP : AnsiString;
                                             pNrConCli : AnsiString);
var lNrConexa  : Integer;
    lConexaTcp : PConexaTcp;
begin
      lNrConexa := 0;
      while lNrConexa < connL.Count do
      begin
            lConexaTcp := PConexaTcp(connL[lNrConexa]);

            if lConexaTcp.NrConTcp = StrToInt(pNrConTCP)  then
            begin
                  BrvString.StrToChar(pNmServer, lConexaTcp.NmServer, 30);
                  lConexaTcp.NrConCli := StrToInt(pNrConCli);
                  lNrConexa           := connL.Count;
            end;

            inc(lNrConexa);
      end;
end;

procedure TFrmSerCon.TrvServerChange(Sender: TObject; Node: TTreeNode);
begin
      if Node.ImageIndex in [gNrSrvOff, gNrSrvOn] then
      begin
            if TblServer.FindKey([Node.Text]) then
            begin
                  EdtNmServer.Text := TblServer.FieldByName('NmServer').AsString;
                  EdtIpIntern.Text := TblServer.FieldByName('IpIntern').AsString;
                  EdtIpExtern.Text := TblServer.FieldByName('IpExtern').AsString;
                  EdtNoPorta.Text  := TblServer.FieldByName('NoPorta' ).AsString;
                  EdtNmSetor.Text  := TblServer.FieldByName('NmSetor' ).AsString;
                  EdtQtMaxCon.Text := TblServer.FieldByName('QtMaxcon').AsString;
                  EdtQtConAtu.Text := TblServer.FieldByName('QtConAtu').AsString;

                  PgcConfig.ActivePage := TbsServer;
            end else
            begin
                  PgcConfig.ActivePage := TbsNada;
            end;
      end else
      begin
            PgcConfig.ActivePage := TbsNada;
      end;
end;

procedure TFrmSerCon.BrvSpeedButton1Click(Sender: TObject);
begin
      if TblServer.FindKey([EdtNmServer.Text]) then
      begin
            if EdtNmSetor.Text <>  TblServer.FieldByName('NmSetor').AsString then
            begin
                  MoverServidorDeSetor(EdtNmServer.Text,
                                       TblServer.FieldByName('NmSetor').AsString,
                                       EdtNmSetor.Text,
                                       TblServer.FieldByName('TpServer').AsString);
            end;

            TblServer.Edit;
            TblServer.FieldByName('NmServer').AsString  := EdtNmServer.Text;
            TblServer.FieldByName('IpIntern').AsString  := EdtIpIntern.Text;
            TblServer.FieldByName('IpExtern').AsString  := EdtIpExtern.Text;
            TblServer.FieldByName('NoPorta').AsString   := EdtNoPorta.Text;
            TblServer.FieldByName('NmSetor').AsString   := EdtNmSetor.Text;
            TblServer.FieldByName('QtMaxcon').AsInteger := StrToInt(EdtQtMaxCon.Text);
            TblServer.Post;

            PgcConfig.ActivePage := TbsNada;

            SalvarServidores(TblServer.Data, TblIp.Data);

            MessageDlg('Dados salvos com sucesso!', mtError, [mbOk], 0);
      end else
      begin
            MessageDlg('Não possível salvar dados. Registro não encontrado',
                                                                      mtError, [mbok], 0);
            PgcConfig.ActivePage := TbsNada;
      end;
end;

procedure TFrmSerCon.MoverServidorDeSetor(pNmServer : AnsiString; pNmSetAnt : AnsiString;
                                          pNmSetor  : AnsiString; pTpServer : AnsiString);
var lNdSetor  : TTreeNode;
    lNdServer : TTreeNode;
    lNdSetAnt : TTreeNode;
begin
      lNdSetor := EncontraNodeNome(pNmSetor);
      if lNdSetor = nil then
      begin
            if pTpServer = 'A' then
            begin
                 lNdSetor := CriarNode(gNdSerApl, pNmSetor, 'T', pNmSetor);
            end else
            begin
                 lNdSetor := CriarNode(gNdSerImp, pNmSetor, 'T', pNmSetor);
            end;
      end;

      lNdServer := EncontraNodeNome(pNmServer);
      if lNdServer = nil then
      begin
            lNdServer := CriarNode(lNdSetor, pNmSetor, 'S', pNmSetor);
      end else
      begin
            lNdServer.MoveTo(lNdSetor, naAddChild);
      end;

      if UpperCase(pNmSetAnt) <> 'SEM SETOR' then
      begin
            lNdSetAnt := EncontraNodeNome(pNmSetAnt);
            if (lNdSetAnt<> nil) and (lNdSetAnt.Count = 0) then
            begin
                  lNdSetAnt.Delete;
            end;
      end;
end;

procedure TFrmSerCon.BrvSpeedButton2Click(Sender: TObject);
begin
      PgcConfig.ActivePage := TbsServer;
end;

procedure TFrmSerCon.SalvarServidores(pTblServer : Olevariant; pTblIp : OleVariant);
var lTblServer : TClientDataSet;
    lDsCaminh  : AnsiString;
    lStlFile   : TStrings;
begin
      try
          // =-=-=-= Gravando informações do servidor

          lDsCaminh := Application.ExeName;
          lDsCaminh := ExtractFileDir(lDsCaminh);
          lStlFile  := TStringList.Create;

          lTblServer := TClientDataSet.Create(Self);
          lTblServer.Data := pTblServer;
          lTblServer.First;

          while not lTblServer.Eof do
          begin
                lStlFile.Add('INI');
                lStlFile.Add('NmSetor='  + lTblServer.FieldByName('NmSetor').AsString);
                lStlFile.Add('NmServer=' + lTblServer.FieldByName('NmServer').AsString);
                lStlFile.Add('QtMaxCon=' +
                             IntToStr(lTblServer.FieldByName('QtMaxCon').AsInteger));
                lStlFile.Add('TpServer=' + lTblServer.FieldByName('TpServer').AsString);
                lStlFile.Add('FIM');
                lStlFile.Add(' ');

                lTblServer.Next;
          end;

          lStlFile.SaveToFile(lDsCaminh + '\LogosCnx.conf');

          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-=-= Gravando informações dos clientes do servidor
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          lStlFile.Clear;

          lTblServer.Data     := pTblIp;
          lTblServer.Filtered := False;
          lTblServer.First;

          while not lTblServer.Eof do
          begin
                lStlFile.Add('INI');
                lStlFile.Add('NmServer=' + lTblServer.FieldByName('NmServer').AsString);
                lStlFile.Add('IpClient=' + lTblServer.FieldByName('IpClient').AsString);
                lStlFile.Add('FIM');
                lStlFile.Add(' ');

                lTblServer.Next;
          end;

          lStlFile.SaveToFile(lDsCaminh + '\LogosCnxCli.conf');
      finally
          FreeAndNil(lTblServer);
          FreeAndNil(lStlFile);
      end;
end;

procedure TFrmSerCon.CarregarServidores;
var lDsCaminh  : AnsiString;
    lStlFile   : TStrings;
    lNrLinha   : Integer;
    lNdSetor   : TTreeNode;
begin
      lDsCaminh := Application.ExeName;
      lDsCaminh := ExtractFileDir(lDsCaminh);

      if FileExists(lDsCaminh + '\LogosCnx.conf') then
      begin
            try
                lStlFile  := TStringList.Create;
                lStlFile.LoadFromFile(lDsCaminh + '\LogosCnx.conf');

                for lNrLinha := 0 to lStlFile.Count -1 do
                begin
                      if Trim(lStlFile.Strings[lNrLinha]) <> '' then
                      begin
                            if Trim(lStlFile.Strings[lNrLinha]) = 'INI' then
                            begin
                                  TblServer.Append;
                            end
                            else if Trim(lStlFile.Strings[lNrLinha]) = 'FIM' then
                                 begin
                                       TblServer.Post;
                                 end
                            else begin
                                       BrvString.Split(Trim(lStlFile.Strings[lNrLinha]), '=');

                                       TblServer.FieldByName(BrvString.BrSplitLista[0]).AsString :=
                                                                          BrvString.BrSplitLista[1];
                                 end;
                      end;
                end;
            finally
                FreeAndNil(lStlFile);
            end;

            TblServer.First;
            while not TblServer.Eof do
            begin
                  lNdSetor := EncontraNodeNome(TblServer.FieldByName('NmSetor').AsString);

                  if lNdSetor = nil then
                  begin
                        if TblServer.FieldByName('TpServer').AsString = 'A' then
                        begin
                              lNdSetor := CriarNode(gNdSerApl,
                                              TblServer.FieldByName('NmSetor').AsString,
                                              'T',
                                              TblServer.FieldByName('NmSetor').AsString);
                        end else
                        begin
                              lNdSetor := CriarNode(gNdSerImp,
                                              TblServer.FieldByName('NmSetor').AsString,
                                              'T',
                                              TblServer.FieldByName('NmSetor').AsString);
                        end;
                  end;

                  CriarNode(lNdSetor, TblServer.FieldByName('NmServer').AsString, 'S',
                            TblServer.FieldByName('NmServer').AsString);

                  TblServer.Next;
            end;
      end;
end;

procedure TFrmSerCon.CarregarClientesDosServidores;
var lDsCaminh  : AnsiString;
    lStlFile   : TStrings;
    lNrLinha   : Integer;
begin
      lDsCaminh := Application.ExeName;
      lDsCaminh := ExtractFileDir(lDsCaminh);

      if FileExists(lDsCaminh + '\LogosCnxCli.conf') then
      begin
            try
                lStlFile  := TStringList.Create;
                lStlFile.LoadFromFile(lDsCaminh + '\LogosCnxCli.conf');

                for lNrLinha := 0 to lStlFile.Count -1 do
                begin
                      if Trim(lStlFile.Strings[lNrLinha]) <> '' then
                      begin
                            if Trim(lStlFile.Strings[lNrLinha]) = 'INI' then
                            begin
                                  TblIp.Append;
                            end
                            else if Trim(lStlFile.Strings[lNrLinha]) = 'FIM' then
                                 begin
                                       TblIp.Post;
                                 end
                            else begin
                                       BrvString.Split(Trim(lStlFile.Strings[lNrLinha]), '=');

                                       TblIp.FieldByName(BrvString.BrSplitLista[0]).AsString :=
                                                                          BrvString.BrSplitLista[1];
                                 end;
                      end;
                end;
            finally
                FreeAndNil(lStlFile);
            end;
      end;
end;

procedure TFrmSerCon.AtualizaStatusServidor(pNmServer : AnsiString;  pIpIntern : AnsiString;
                                            pIpExtern : AnsiString;  pNoPorta  : AnsiString;
                                            pQtMaxCon : Integer;     pNmSetor  : AnsiString;
                                            pStServer : AnsiString;  pTpServer : AnsiString);
begin
      if TblServer.FindKey([pNmServer]) then
      begin
            TblServer.Edit;
      end else
      begin
            TblServer.Append;
            TblServer.FieldByName('NmServer').AsString := pNmServer;
      end;

      TblServer.FieldByName('IpIntern').AsString := pIpIntern;
      TblServer.FieldByName('IpExtern').AsString := pIpExtern;
      TblServer.FieldByName('NoPorta').AsString  := pNoPorta;

      if pQtMaxCon >= 0 then
      begin
            TblServer.FieldByName('QtMaxCon').AsInteger := pQtMaxCon;
      end;

      TblServer.FieldByName('NmSetor').AsString  := pNmSetor;
      TblServer.FieldByName('SnAtivo').AsString  := pStServer;
      TblServer.FieldByName('TpServer').AsString := pTpServer;

      TblServer.Post;
end;

function TFrmSerCon.CriarNode(pNdNode: TTreeNode; pDsNode: AnsiString;
                              pTpNode: Char;      pIdNode: AnsiString) : TTreeNode;
begin
      New(gNdNode);
      Result := TrvServer.Items.AddChildObject(pNdNode, pDsNode, gNdNode);
      PString(Result.Data)^ := pIdNode;

      case pTpNode of
           'S' : begin // servidor
                       Result.ImageIndex    := gNrSrvOff;
                       Result.SelectedIndex := gNrSrvOff;
                 end;
           'T' : begin // setor
                       Result.ImageIndex    := gNrImgSet;
                       Result.SelectedIndex := gNrImgSet;
                 end;
           'U' : begin // Usuário
                       Result.ImageIndex    := gNrUsrOff;
                       Result.SelectedIndex := gNrUsrOff;
                 end;
           'A' : begin // Servidor de aplicação
                       Result.ImageIndex    := gNrSrvApl;
                       Result.SelectedIndex := gNrSrvApl;
                 end;
           'I' : begin // Servidor de impressão
                       Result.ImageIndex    := gNrSrvImp;
                       Result.SelectedIndex := gNrSrvImp;
                 end;
      end;
end;

function TFrmSercon.EncontraNodeNome(pNmNode : AnsiString): TTreeNode;
var lNrNode : Integer;
begin
      Result  := nil;
      lNrNode := 0;

      while (lNrNode < TrvServer.Items.Count) and (Result = nil) do
      begin
            if PString(TrvServer.Items.Item[lNrNode].Data)^ = pNmNode then
            begin
                  Result := TrvServer.Items.Item[lNrNode];
            end;

            inc(lNrNode);
      end;
end;

function TFrmSerCon.EncontraNodeConexao(pNrConexa : Integer) : TTreeNode;
var lNrNode : Integer;
begin
      Result  := nil;
      lNrNode := 0;
      while (lNrNode < TrvServer.Items.Count) and (Result = nil) do
      begin
            if PString(TrvServer.Items.Item[lNrNode].Data)^ = IntToStr(pNrConexa) then
            begin
                  Result := TrvServer.Items.Item[lNrNode];
            end;

            inc(lNrNode);
      end;
end;

end.
