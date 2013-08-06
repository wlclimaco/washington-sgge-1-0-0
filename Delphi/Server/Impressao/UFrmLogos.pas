unit UFrmLogos;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ExtCtrls, BrvEditNum, ComCtrls, ImgList, ShellApi, Menus, BrvString,
  DB, DBClient, Buttons, BrvSpeedButton, Grids, BrvDbGrids, BrvDbGrid, BrvCustomEdit;

const cDsMsgOk = '0; ';
      cDsMsgEr = '1; ';
      WM_MIDASICON = WM_USER + 1;

type
  TFrmLogos = class(TForm)
    Panel1: TPanel;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    EdtIpIntern: TEdit;
    EdtIpExtern: TEdit;
    EdtNoPorta: TBrvEditNum;
    EdtIpSerCon: TEdit;
    Label6: TLabel;
    EdtNoSerCon: TEdit;
    EdtDsOperac: TEdit;
    Label7: TLabel;
    PgbProcess: TProgressBar;
    TmrConexa: TTimer;
    Panel2: TPanel;
    TrvServer: TTreeView;
    ImgLista: TImageList;
    PopPrincip: TPopupMenu;
    PopExit: TMenuItem;
    N1: TMenuItem;
    PopMosCon: TMenuItem;
    MainMenu1: TMainMenu;
    Atualizardicionriodedados1: TMenuItem;
    Sistema1: TMenuItem;
    N2: TMenuItem;
    Encerraraplicao1: TMenuItem;
    BrvString1: TBrvString;
    PageControl1: TPageControl;
    TabSheet1: TTabSheet;
    Panel4: TPanel;
    BrvSpeedButton1: TBrvSpeedButton;
    TblUser: TClientDataSet;
    DtsUser: TDataSource;
    GroupBox1: TGroupBox;
    BrvDbGrid1: TBrvDbGrid;
    SbtManute: TSpeedButton;
    Label8: TLabel;
    EdtQtProAti: TBrvEditNum;
    TrmBPL: TTimer;
    EdtDsMenMan: TEdit;
    CdsAplCli: TClientDataSet;
    CdsAplCliNmArquiv: TStringField;
    CdsAplCliDtArquiv: TDateTimeField;
    CdsAplCliNrArquiv: TIntegerField;
    CdsAplCliNmArqCli: TStringField;
    CdsAplCliNmZipFil: TStringField;
    CdsAplCliTpArquiv: TStringField;
    CdsAplCliSnObriga: TStringField;
    CdsAplCliSnCompac: TStringField;
    CdsAplCliSnProces: TStringField;
    procedure FormCreate(Sender: TObject);
    procedure TmrConexaTimer(Sender: TObject);
    procedure FormCloseQuery(Sender: TObject; var CanClose: Boolean);
    procedure PopMosConClick(Sender: TObject);
    procedure PopExitClick(Sender: TObject);
    procedure Atualizardicionriodedados1Click(Sender: TObject);
    procedure Encerraraplicao1Click(Sender: TObject);
    procedure BrvSpeedButton1Click(Sender: TObject);
    procedure TblUserBeforePost(DataSet: TDataSet);
    procedure SbtManuteClick(Sender: TObject);
    procedure TrmBPLTimer(Sender: TObject);
  private
    { Private declarations }
    gNdNode    : PString;
    gNdPrinci  : TTreeNode;
    gIconData  : TNotifyIconData;
    procedure WMEndQuerySession(var Msg : TWMQueryEndSession); message WM_QueryENDSESSION;
    procedure InicializarTreeVeiew;
    procedure CriarTryIcon;
    procedure EliminarSysTry(pSnSair: Boolean);
    procedure EncerrarAplicacao;
    procedure CriarTabelaServidores;
    procedure CarregarUsuariosPermitidos;
    procedure SalvarUsuariosExclusivos;
  public
    { Public declarations }
    gNrProCli  : Integer; // Código de acesso do próximo cliente
    function CriarNode(pDsNode: String; pNrClient : Integer): TTreeNode;
    function LocalizarNodeConexaoTCP(pNrConexa: String): TTreeNode;
    function CredencialValida(pDsCreden: String; var pCdUsuari : Integer): Boolean;
    procedure ClienteAutenticado(pDsCreden: String; var pDsResult: String;
      pCdUsuari: Integer; pDsLogin, pNrConTcp: String);
  protected
    procedure WMMIDASIcon(var Message : TMessage); message WM_MIDASICON;
  end;

var
  FrmLogos: TFrmLogos;

const gNrUsrOff = 1;
      gNrUsrOn  = 2;
      cDsArqUse = '\LogosImpUser.conf';
      cTpServer = 'I'; // Servidor de impressão

implementation

uses UFrmConSrv, UDmDicion;

{$R *.dfm}


procedure TFrmLogos.FormCloseQuery(Sender: TObject; var CanClose: Boolean);
begin
      CanClose := Tag = 0;

      if not CanClose then
      begin
            PopMosCon.Tag     := 0;
            PopMosCon.Caption := 'Mostrar Conexões';
            Visible := CanClose;
      end else
      begin
            if MessageDlg('Ao fechar essa aplicação, todos os clientes serão desconectados.' +
                          #13#13 +
                          'Confirma o encerramento?',
                          mtConfirmation, [mbYes, mbNo], 0) = IdYes then
            begin
                  CanClose := True;
                  EliminarSysTry(True);
            end else
            begin
                  CanClose := False;
            end;
      end;
end;

procedure TFrmLogos.EliminarSysTry(pSnSair: Boolean);
begin
      Shell_NotifyIcon(NIM_DELETE, @gIconData);

      if pSnSair then
      begin
            Application.Terminate;
      end;
end;

procedure TFrmLogos.FormCreate(Sender: TObject);
begin
      EdtQtProAti.BrAsInteger := 0;

      CriarTryIcon;

      CriarTabelaServidores;
      CarregarUsuariosPermitidos;

      EdtIpSerCon.Text := FrmConSrv.gIpSerCon;
      EdtNoSerCon.Text := IntToStr(FrmConSrv.gNoPorCon);
      EdtIpIntern.Text := FrmConSrv.gIpIntern;
      EdtIpExtern.Text := FrmConSrv.gIpExtern;
      EdtNoPorta.Text  := IntToStr(FrmConSrv.gNoPorta);
      EdtDsOperac.Text := FrmConSrv.LblProces.Caption;

      InicializarTreeVeiew;
end;

procedure TFrmLogos.BrvSpeedButton1Click(Sender: TObject);
begin
      SalvarUsuariosExclusivos;
      MessageDlg('Usuários salvos.', mtInformation, [mbok], 0);
end;

procedure TFrmLogos.SalvarUsuariosExclusivos;
var lDsCaminh  : String;
    lStlFile   : TStrings;
begin
      lDsCaminh := Application.ExeName;
      lDsCaminh := ExtractFileDir(lDsCaminh) + cDsArqUse;

      try
          lStlFile  := TStringList.Create;

          TblUser.First;
          while not TblUser.Eof do
          begin
                lStlFile.Add(Trim(TblUser.FieldByName('DsLogin').AsString));
                TblUser.Next;
          end;
          lStlFile.SaveToFile(lDsCaminh);
      finally
          FreeAndNil(lStlFile);
      end;
end;

procedure TFrmLogos.SbtManuteClick(Sender: TObject);
begin
      if SbtManute.Down then
      begin
            if MessageDlg('Confirma pausa no servidor para manutenção?', mtConfirmation,
                                                           [mbYes, mbNo], 0) = IdYes then
            begin
                  SbtManute.Tag     := 1;
                  SbtManute.Caption := 'Finalizando. Aguarde...';
                  TrmBPL.Enabled    := True;
            end else
            begin
                  SbtManute.Down := False;
            end;
      end else
      begin
            SbtManute.Tag     := 0;
            SbtManute.Caption := 'Parar para atualização';
      end;
end;

procedure TFrmLogos.TrmBPLTimer(Sender: TObject);
begin
      if EdtQtProAti.BrAsInteger <= 0 then
      begin
            TrmBPL.Enabled := False;
            SbtManute.Caption := 'Parado para atualização';
            MessageDlg('Pacote descarregado. Atualização liberada.', mtInformation,
                                                                               [mbOk], 0);
      end;
end;

procedure TFrmLogos.CarregarUsuariosPermitidos;
var lDsCaminh  : String;
    lStlFile   : TStrings;
    lNrLinha   : Integer;
begin
      lDsCaminh := Application.ExeName;
      lDsCaminh := ExtractFileDir(lDsCaminh);

      if FileExists(lDsCaminh + cDsArqUse) then
      begin
            try
                lStlFile  := TStringList.Create;
                lStlFile.LoadFromFile(lDsCaminh + cDsArqUse);

                for lNrLinha := 0 to lStlFile.Count -1 do
                begin
                      if Trim(lStlFile.Strings[lNrLinha]) <> '' then
                      begin
                            TblUser.Append;
                            TblUser.FieldByName('DsLogin').AsString :=
                                                         Trim(lStlFile.Strings[lNrLinha]);
                            TblUser.Post;
                      end;
                end;
            finally
                FreeAndNil(lStlFile);
            end;
      end;
end;

procedure TFrmLogos.CriarTabelaServidores;
begin
      // =-=-=-=-=-= Criando Tabela de servidores
      TblUser.FieldDefs.Clear;
      TblUser.FieldDefs.Add('DsLogin',  ftString,   30, False);

      TblUser.IndexDefs.Clear;
      TblUser.IndexDefs.Add('Key1', 'DsLogin', [ixPrimary, ixUnique]);
      TblUser.CreateDataSet;

      TblUser.IndexName := 'Key1';
      TblUser.SetKey;
end;

procedure TFrmLogos.CriarTryIcon;
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

procedure TFrmLogos.InicializarTreeVeiew;
var lNdNovo : TTreeNode;
begin
      TrvServer.Items.Clear;
      new(gNdNode);
      lNdNovo := TrvServer.Items.AddObject(nil, 'Usuários', gNdNode);
      PString(lNdNovo.Data)^ := 'SERVERS';
      TrvServer.Selected := lNdNovo;
      gNdPrinci          := lNdNovo;

      TrvServer.FullExpand;
end;

function TFrmLogos.CriarNode(pDsNode : String; pNrClient : Integer) : TTreeNode;
begin
      New(gNdNode);
      Result := TrvServer.Items.AddChildObject(gNdPrinci, pDsNode, gNdnode);
      PString(Result.Data)^ := IntToStr(pNrClient);
      Result.Text           := pDsNode;

      Result.ImageIndex    := gNrUsrOff;
      Result.SelectedIndex := gNrUsrOff;
end;

function TFrmLogos.CredencialValida(pDsCreden : String; var pCdUsuari : Integer) : Boolean;
var lNdClient  : TTreeNode;
    lDsCreden  : String;
    lBrvString : TBrvString;
begin
      pCdUsuari := 0;
      lDsCreden := IntToStr(DmDicion.BrvDicionario.DescriptografarCredencial(pDsCreden));
      lNdClient := LocalizarNodeConexaoTCP(lDsCreden);
      Result    := lNdClient <> nil;

      if lNdClient <> nil then
      begin
            lBrvString := TBrvString.Create(Self);
            try
                lBrvString.Split(lNdClient.Text, ' - ');

                if (lBrvString.BrSplitLista.Count > 1) and
                   (lBrvString.BrSplitLista.Strings[1] <> '') then
                begin
                      pCdUsuari := StrToInt(lBrvString.BrSplitLista.Strings[1]);
                end;
            finally
                FreeAndNil(lBrvString);
            end;
      end;
end;

function TFrmLogos.LocalizarNodeConexaoTCP(pNrConexa : String) : TTreeNode;
var lNrNode   : Integer;
begin
      Result  := nil;
      lNrNode := 0;

      while (lNrNode < TrvServer.Items.Count) and (Result = nil) do
      begin
            if PString(TrvServer.Items.Item[lNrNode].Data)^ = pNrConexa then
            begin
                  Result := TrvServer.Items.Item[lNrNode];
            end;

            inc(lNrNode);
      end;
end;

procedure TFrmLogos.PopExitClick(Sender: TObject);
begin
      EncerrarAplicacao;
end;

procedure TFrmLogos.EncerrarAplicacao;
begin
      if MessageDlg('Confirma encerramento da aplicação?',
                                            MtConfirmation, [MbYes, MbNo], 0) = IdYes then
      begin
            SalvarUsuariosExclusivos;
            Tag := 0;
            Close;
      end;
end;

procedure TFrmLogos.Encerraraplicao1Click(Sender: TObject);
begin
      EncerrarAplicacao;
end;

procedure TFrmLogos.PopMosConClick(Sender: TObject);
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

procedure TFrmLogos.TblUserBeforePost(DataSet: TDataSet);
begin
      TblUser.FieldByName('Dslogin').AsString :=
                                      UpperCase(TblUser.FieldByName('Dslogin').AsString);
end;

procedure TFrmLogos.TmrConexaTimer(Sender: TObject);
begin
      if PgbProcess.Position = PgbProcess.Max then
      begin
            TmrConexa.Enabled := False;
            PgbProcess.Position := 0;

            EdtDsOperac.Text  := 'Tentando reconectar ao servidor de conexões';
            try
                if FrmConSrv.ConectaServidorConexao(False) then
                begin
                      FrmConSrv.EnviaMensagemConexaoEstabelecida;
                end else
                begin
                      FrmConSrv.IniciarProcessoReconexao(20);
                end;
            except
                FrmConSrv.IniciarProcessoReconexao(20);
            end;
      end else
      begin
            PgbProcess.StepIt;
      end;
end;

procedure TFrmLogos.WMEndQuerySession(var Msg: TWMQueryEndSession);
begin
      Msg.Result := 1;
      Tag := 0;
      Close;
      inherited;
end;

procedure TFrmLogos.WMMIDASIcon(var Message: TMessage);
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
        WM_LBUTTONDBLCLK :
                           if not Visible then
                           begin
                                 PopMosConClick(PopMosCon);
                           end else
                           begin
                                 SetForegroundWindow(Handle);
                           end;
      end;
end;

procedure TFrmLogos.Atualizardicionriodedados1Click(Sender: TObject);
begin
      FrmConSrv.AtualizarDicionarioDados(True);
end;

procedure TFrmLogos.ClienteAutenticado(pDsCreden : String;  var pDsResult: String;
                                       pCdUsuari : Integer;     pDsLogin : String;
                                       pNrConTcp : String);
var lNdUsuari : TTreeNode;
begin
      lNdUsuari := FrmLogos.LocalizarNodeConexaoTCP(pDsCreden);

      if lNdUsuari <> nil then
      begin
            if lNdUsuari.ImageIndex <> gNrUsrOn then
            begin
                  lNdUsuari.Text := lNdUsuari.Text + ' - ' + IntToStr(pCdUsuari) + ' - ' +
                                                                      pDsLogin;
                  lNdUsuari.ImageIndex    := gNrUsrOn;
                  lNdUsuari.SelectedIndex := gNrUsrOn;
            end;

            FrmConSrv.EnviaMensagemClienteAutenticado(pCdUsuari, pDsLogin, pNrConTcp);
      end else
      begin
            pDsResult := cDsMsgEr + 'Usuário não encontrado na lista de conexões.';
      end;
end;

end.

