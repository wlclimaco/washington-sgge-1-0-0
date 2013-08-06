unit UFrmLogos;

interface

uses
  Windows,ActiveX, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ExtCtrls, BrvEditNum, ComCtrls, ImgList, ShellApi, Menus, BrvString,
  DB, DBClient, Buttons, BrvSpeedButton, Grids, BrvDbGrids, BrvDbGrid, BrvEdit, BrvRetCon,
  SevenZipVCL, MidasLib, Mask, BrvEditDate, BrvBitBtn, BrvImgBot, DateUtils, BrvCustomMaskEdit,
  BrvCustomEdit;

const cDsMsgOk = '0; ';
      cDsMsgEr = '1; ';
      WM_MIDASICON = WM_USER + 1;

type
  TBrvTarefa = record
       EdtDtUltExe : TBrvEditDate;
       EdtDtProExe : TBrvEditDate;
       CbxSnAtivo  : TCheckBox;
       LblStStatus : TLabel;
       QtInterv    : Integer;
       PrbStatus   : TProgressBar;
       BtnExecute  : TBrvBitBtn;
  end;

  TFrmLogos = class(TForm)
    Panel1: TPanel;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    EdtIpIntern: TBrvRetCon;
    EdtIpExtern: TBrvRetCon;
    EdtNoPorta: TBrvEditNum;
    EdtIpSerCon: TBrvRetCon;
    Label6: TLabel;
    EdtNoSerCon: TBrvRetCon;
    EdtDsOperac: TBrvRetCon;
    Label7: TLabel;
    PgbProcess: TProgressBar;
    TmrConexa: TTimer;
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
    TblUser: TClientDataSet;
    DtsUser: TDataSource;
    Label8: TLabel;
    SbtAtuCli: TSpeedButton;
    LblAplCli: TLabel;
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
    PgcSrvApl: TPageControl;
    TbsConexo: TTabSheet;
    Panel2: TPanel;
    TrvServer: TTreeView;
    PageControl1: TPageControl;
    TabSheet1: TTabSheet;
    Panel4: TPanel;
    BrvSpeedButton1: TBrvSpeedButton;
    GroupBox1: TGroupBox;
    BrvDbGrid1: TBrvDbGrid;
    TbsAgenda: TTabSheet;
    Panel3: TPanel;
    LblDsTarefa: TLabel;
    Label9: TLabel;
    BrvEditDate1: TBrvEditDate;
    Label10: TLabel;
    BrvEditDate2: TBrvEditDate;
    BtnExecut: TBrvBitBtn;
    CdsTarefa: TClientDataSet;
    CheckBox1: TCheckBox;
    Label11: TLabel;
    TmrTarefa: TTimer;
    ProgressBar1: TProgressBar;
    procedure FormCreate(Sender: TObject);
    procedure TmrConexaTimer(Sender: TObject);
    procedure FormCloseQuery(Sender: TObject; var CanClose: Boolean);
    procedure PopMosConClick(Sender: TObject);
    procedure PopExitClick(Sender: TObject);
    procedure Atualizardicionriodedados1Click(Sender: TObject);
    procedure Encerraraplicao1Click(Sender: TObject);
    procedure BrvSpeedButton1Click(Sender: TObject);
    procedure TblUserBeforePost(DataSet: TDataSet);
    procedure SbtAtuCliClick(Sender: TObject);
    procedure TmrTarefaTimer(Sender: TObject);
    procedure BtnExecutClick(Sender: TObject);
  private
    { Private declarations }
    gNdNode   : PString;
    gNdPrinci : TTreeNode;
    gIconData : TNotifyIconData;
    gDsTarefa : array of TBrvTarefa;
    procedure WMEndQuerySession(var Msg : TWMQueryEndSession); message WM_QueryENDSESSION;
    procedure InicializarTreeVeiew;
    procedure CriarTryIcon;
    procedure EliminarSysTry(pSnSair: Boolean);
    procedure EncerrarAplicacao;
    procedure CriarTabelaServidores;
    procedure CarregarUsuariosPermitidos;
    procedure SalvarUsuariosExclusivos;
    procedure CarregarTarefasAgendas;
    procedure ExecutarTarefa(pCdTarefa : Integer);
    function TarefaExecutando: Boolean;
  public
    { Public declarations }
    gNrProCli : Integer; // Código de acesso do próximo cliente
    function CaminhoApp : String;
    function CriarNode(pDsNode: String; pNrClient : Integer): TTreeNode;
    function LocalizarNodeConexaoTCP(pNrConexa: String): TTreeNode;
    function CredencialValida(pDsCreden: String; var pCdUsuari : Integer): Boolean;
    procedure ClienteAutenticado(pDsCreden: String; var pDsResult: String;
                                                   pCdUsuari: Integer; pDsLogin, pNrConTcp: String);
  protected
    procedure WMMIDASIcon(var Message : TMessage); message WM_MIDASICON;
  end;

  TTarefaThread = class(TThread)
  protected
    procedure Execute; override;
  public
    gCdTarefa    : Integer;
    gLblStStatus : TLabel;
    gEdtDtUltExe : TBrvEditDate;
    gEdtDtProExe : TBrvEditDate;
    gPrbStatus   : TProgressBar;
    gQtInterv    : Integer;
    gBtnExecute  : TBrvBitBtn;
    constructor Create(CreateSuspended: Boolean);
  end;

var
  FrmLogos: TFrmLogos;

const gNrUsrOff = 1;
      gNrUsrOn  = 2;
      cDsArqUse = '\LogosUser.conf';
      cTpServer = 'A';

implementation

uses UFrmConSrv, USDmRW, USRA0001, UDmDicion, UDmTarefa, UDmTar003,
  UDmTar004;

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
var lDsCaminh : String;
begin

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

      CdsAplCli.CreateDataSet;
      lDsCaminh := ExtractFileDir(Application.ExeName);

      if FileExists(lDsCaminh + '\LogosAplAtu.bin') then
      begin
            CdsAplCli.LoadFromFile(lDsCaminh + '\LogosAplAtu.bin');
            CdsAplCli.IndexDefs.Add('Key1', 'NrArquiv', [ixCaseInsensitive]);
            CdsAplCli.IndexName := 'Key1';
            CdsAplCli.SetKey;
            LblAplCli.Caption     := 'Atualização carregada';
            LblAplCli.Font.Color  := clBlack;
      end;

      CarregarTarefasAgendas;

      PgcSrvApl.ActivePage := TbsConexo;
end;

function TFrmLogos.CaminhoApp: String;
begin
      Result := ExtractFilePath(Application.ExeName);
end;

procedure TFrmLogos.CarregarTarefasAgendas;
var lQtTarefa : Integer;
    lPnlFundo : TPanel;
    lLabel    : TLabel;
    lCdTarefa : Integer;
    lHrSistem : TDateTime;
begin
      TbsAgenda.DestroyComponents;

      CdsTarefa.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(66, '', Name);
      lQtTarefa := CdsTarefa.FieldByName('QtTarefa').AsInteger;

      SetLength(gDsTarefa, lQtTarefa + 2); // + 2 pra margem de erro

      CdsTarefa.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(67, '', Name);

      while not CdsTarefa.Eof do
      begin
            lCdTarefa := CdsTarefa.FieldByName('CdTarefa').AsInteger;

            lPnlFundo := TPanel.Create(TbsAgenda);
            lPnlFundo.Parent := TbsAgenda;
            lPnlFundo.Align  := alTop;
            lPnlFundo.Height := 65;

            lLabel := TLabel.Create(TbsAgenda);
            lLabel.Parent     := lPnlFundo;
            lLabel.Caption    := CdsTarefa.FieldByName('DsTarefa').AsString;
            lLabel.Left       := 6;
            lLabel.Top        := 4;
            lLabel.Font.Style := [fsBold];

            lLabel := TLabel.Create(TbsAgenda);
            lLabel.Parent     := lPnlFundo;
            lLabel.Caption    := 'Última execução';
            lLabel.Left       := 6;
            lLabel.Top        := 26;
            lLabel.Font.Style := [fsBold];

            lLabel := TLabel.Create(TbsAgenda);
            lLabel.Parent     := lPnlFundo;
            lLabel.Caption    := 'Próxima execução';
            lLabel.Left       := 257;
            lLabel.Top        := 26;
            lLabel.Font.Style := [fsBold];

            gDsTarefa[lCdTarefa].BtnExecute := TBrvBitBtn.Create(TbsAgenda);
            gDsTarefa[lCdTarefa].BtnExecute.Parent       := lPnlFundo;
            gDsTarefa[lCdTarefa].BtnExecute.Caption      := 'Executar';
            gDsTarefa[lCdTarefa].BtnExecute.Left         := 622;
            gDsTarefa[lCdTarefa].BtnExecute.Top          := 17;
            gDsTarefa[lCdTarefa].BtnExecute.BrTipoBotao  := BrBtnOk;
            gDsTarefa[lCdTarefa].BtnExecute.Tag          := lCdTarefa;
            gDsTarefa[lCdTarefa].BtnExecute.OnClick      :=  BtnExecutClick;

            gDsTarefa[lCdTarefa].EdtDtUltExe             := TBrvEditDate.Create(TbsAgenda);
            gDsTarefa[lCdTarefa].EdtDtUltExe.Parent      := lPnlFundo;
            gDsTarefa[lCdTarefa].EdtDtUltExe.ReadOnly    := True;
            gDsTarefa[lCdTarefa].EdtDtUltExe.TabStop     := False;
            gDsTarefa[lCdTarefa].EdtDtUltExe.ParentColor := True;
            gDsTarefa[lCdTarefa].EdtDtUltExe.Left        := 104;
            gDsTarefa[lCdTarefa].EdtDtUltExe.Top         := 22;
            gDsTarefa[lCdTarefa].EdtDtUltExe.Width       := 150;
            gDsTarefa[lCdTarefa].EdtDtUltExe.BrFunctionButton := TVdDataHora;
            gDsTarefa[lCdTarefa].EdtDtUltExe.BrDicionario := DmDicion.BrvDicionario;

            gDsTarefa[lCdTarefa].EdtDtProExe             := TBrvEditDate.Create(TbsAgenda);
            gDsTarefa[lCdTarefa].EdtDtProExe.Parent      := lPnlFundo;
            gDsTarefa[lCdTarefa].EdtDtProExe.Left        := 363;
            gDsTarefa[lCdTarefa].EdtDtProExe.Top         := 22;
            gDsTarefa[lCdTarefa].EdtDtProExe.Width       := 150;
            gDsTarefa[lCdTarefa].EdtDtProExe.BrFunctionButton := TVdDataHora;
            gDsTarefa[lCdTarefa].EdtDtProExe.BrDicionario := DmDicion.BrvDicionario;

            gDsTarefa[lCdTarefa].CbxSnAtivo := TCheckBox.Create(TbsAgenda);
            gDsTarefa[lCdTarefa].CbxSnAtivo.Parent     := lPnlFundo;
            gDsTarefa[lCdTarefa].CbxSnAtivo.Left       := 519;
            gDsTarefa[lCdTarefa].CbxSnAtivo.Top        := 26;
            gDsTarefa[lCdTarefa].CbxSnAtivo.Width      := 49;
            gDsTarefa[lCdTarefa].CbxSnAtivo.Font.Style := [fsBold];
            gDsTarefa[lCdTarefa].CbxSnAtivo.Caption    := 'Ativo';
            gDsTarefa[lCdTarefa].CbxSnAtivo.Checked    := False;
//                                          CdsTarefa.FieldByName('SnAtivo').AsString = 'S';

            gDsTarefa[lCdTarefa].LblStStatus := TLabel.Create(TbsAgenda);
            gDsTarefa[lCdTarefa].LblStStatus.Parent     := lPnlFundo;
            gDsTarefa[lCdTarefa].LblStStatus.Left       := 6;
            gDsTarefa[lCdTarefa].LblStStatus.Top        := 45;
            gDsTarefa[lCdTarefa].LblStStatus.Font.Style := [fsBold];
            gDsTarefa[lCdTarefa].lblStStatus.Caption    := 'Iniciando';

            gDsTarefa[lCdTarefa].PrbStatus := TProgressBar.Create(TbsAgenda);
            gDsTarefa[lCdTarefa].PrbStatus.Parent     := lPnlFundo;
            gDsTarefa[lCdTarefa].PrbStatus.Left       := 81;
            gDsTarefa[lCdTarefa].PrbStatus.Top        := 45;
            gDsTarefa[lCdTarefa].PrbStatus.Width      := 629;
            gDsTarefa[lCdTarefa].PrbStatus.Max        :=
                                              CdsTarefa.FieldByName('QtInterv').AsInteger;
            gDsTarefa[lCdTarefa].PrbStatus.Step       := 1;


            gDsTarefa[lCdTarefa].QtInterv := CdsTarefa.FieldByName('QtInterv').AsInteger;

            CdsTarefa.Next;
      end;

      lHrSistem := DmDicion.BrvDicionario.DataHoraServer;

      for lCdTarefa := 0 to High(gDsTarefa) do
      begin
            if gDsTarefa[lCdTarefa].CbxSnAtivo <> nil then
            begin
                  gDsTarefa[lCdTarefa].LblStStatus.Caption := 'Aguardando';
                  gDsTarefa[lCdTarefa].EdtDtProExe.BrAsDateTime :=
                                     IncMinute(lHrSistem, gDsTarefa[lCdTarefa].QtInterv);
            end;
      end;

      TmrTarefa.Enabled := True;
end;

procedure TFrmLogos.BtnExecutClick(Sender: TObject);
begin
      ExecutarTarefa((Sender as TBitBtn).Tag);
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

procedure TFrmLogos.SbtAtuCliClick(Sender: TObject);
var lDsCaminh : String;
begin
      try
            SRA0001 := TSra0001.Create(Self);
            Sra0001.CdsAplCli.Data := CdsAplCli.Data;
            Sra0001.CdsAplCli.IndexDefs.Add('Key1', 'NrArquiv', [ixCaseInsensitive]);
            Sra0001.CdsAplCli.IndexName := 'Key1';
            Sra0001.CdsAplCli.SetKey;
            Sra0001.CdsAplCli.First;

            if Sra0001.ShowModal = MrOk then
            begin
                  CdsAplCli.Data        := Sra0001.CdsAplCli.Data;
                  LblAplCli.Caption     := 'Atualização carregada';
                  LblAplCli.Font.Color  := clBlack;

                  lDsCaminh := ExtractFileDir(Application.ExeName);
                  CdsAplCli.SaveToFile(lDsCaminh + '\LogosAplAtu.bin');
            end;
      finally
            FreeAndNil(SRA0001);
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

function TFrmLogos.TarefaExecutando : Boolean;
var lCdTarefa : Integer;
    lHrSistem : TDateTime;
begin
      Result := False;
      lCdTarefa := 0;

      while (lCdTarefa <  High(gDsTarefa)) and (not Result) do
      begin
            if gDsTarefa[lCdTarefa].BtnExecute <> nil then
            begin
                  Result := not(gDsTarefa[lCdTarefa].BtnExecute.Enabled);
            end;

            inc(lCdTarefa);
      end;
end;

procedure TFrmLogos.EncerrarAplicacao;
begin
      if TarefaExecutando then
      begin
            raise Exception.Create('Há tarefas agendadas em execução.' + #13#13 +
                                   'Aguarde a finalização antes de encerrar o servidor');
      end;

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
                                       LowerCase(TblUser.FieldByName('Dslogin').AsString);
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

procedure TFrmLogos.TmrTarefaTimer(Sender: TObject);
var lCdTarefa : Integer;
    lHrSistem : TDateTime;
begin
      TmrTarefa.Enabled := False;
      try
          lHrSistem := DmDicion.BrvDicionario.DataHoraServer;

          for lCdTarefa := 0 to High(gDsTarefa) do
          begin
                if (gDsTarefa[lCdTarefa].CbxSnAtivo <> nil) then
                begin
                      if (gDsTarefa[lCdTarefa].CbxSnAtivo.Checked) and
                         (gDsTarefa[lCdTarefa].LblStStatus.Tag = 0) then
                      begin
                            gDsTarefa[lCdTarefa].PrbStatus.StepIt;

                            if lHrSistem >=
                                        gDsTarefa[lCdTarefa].EdtDtProExe.BrAsDateTime then
                            begin
                                  ExecutarTarefa(lCdTarefa);
                            end;
                      end;
                end;
          end;
      finally
          TmrTarefa.Enabled := True;
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

procedure TFrmLogos.ExecutarTarefa(pCdTarefa : Integer);
var lTarThread : TTarefaThread;
begin
      lTarThread := TTarefaThread.Create(True);
      lTarThread.FreeOnTerminate:=True;

      lTarThread.gCdTarefa    := pCdTarefa;
      lTarThread.gLblStStatus := gDsTarefa[pCdTarefa].LblStStatus;
      lTarThread.gEdtDtUltExe := gDsTarefa[pCdTarefa].EdtDtUltExe;
      lTarThread.gEdtDtProExe := gDsTarefa[pCdTarefa].EdtDtProExe;
      lTarThread.gPrbStatus   := gDsTarefa[pCdTarefa].PrbStatus;
      lTarThread.gQtInterv    := gDsTarefa[pCdTarefa].QtInterv;
      lTarThread.gBtnExecute  := gDsTarefa[pCdTarefa].BtnExecute;

      lTarThread.Resume;
end;

{ TTarefaThread }

constructor TTarefaThread.Create(CreateSuspended: Boolean);
begin
      inherited Create(CreateSuspended);
      Priority        := tpIdle;
      FreeOnTerminate := True;
end;

procedure TTarefaThread.Execute;
var lDmTarefa : TDmTarefa;
    lNmTarefa : String;
begin
      inherited;

      gBtnExecute.Enabled  := False;
      gLblStStatus.Tag     := 1;
      gLblStStatus.Caption := 'Executando';

      lNmTarefa := 'TDmTar' + FormatFloat('000', gCdTarefa);

      try
          Application.CreateForm(TComponentClass(GetClass(lNmTarefa)), lDmTarefa);
          lDmTarefa.Executar(gPrbStatus);
      finally
          FreeAndNil(lDmTarefa);

          gEdtDtUltExe.BrAsDateTime := gEdtDtProExe.BrAsDateTime;
          gEdtDtProExe.BrAsDateTime := IncMinute(gEdtDtProExe.BrAsDateTime, gQtInterv);

          gLblStStatus.Caption := 'Aguardando';
          gLblStStatus.Tag     := 0;

          gPrbStatus.Max       := gQtInterv;
          gPrbStatus.Position  := 0;
          gBtnExecute.Enabled  := True;

          Terminate;
      end;
end;

end.

