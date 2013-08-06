unit UMov0032;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, StdCtrls, Mask, BrvCustomMaskEdit, BrvEditDate, AppEvnts, DB, DBClient,
  BrvClientDataSet, Grids, BrvDbGrids, BrvDbGrid, ExtCtrls, ComCtrls, Buttons, BrvBitBtn, BrvLabel,
  BrvListParam, ImgList, Menus, BrvSpeedButton, BrvDbNavCop, BrvRadioGroup, BrvCustomEdit,
  BrvEditNum, BrvRetCon,UClaSrv, DBCtrls, BrvDbEdit, BrvGeraRelatorio, BrvComboBox, IdHTTP, BrvEdit,
  BrvServerProgress, CheckLst, BrvCheckListBox, BrvDbRetCon;

type
  TMov0032 = class(TMov0000)
    CpQ004Sin: TBrvClientDataSet;
    SpQ004Sin: TDataSource;
    CpQ004Ana: TBrvClientDataSet;
    SpQ004Ana: TDataSource;
    CpQ004Det: TBrvClientDataSet;
    SpQ004Det: TDataSource;
    BrvListParam: TBrvListParam;
    PopDetalhe: TPopupMenu;
    Detalhe1: TMenuItem;
    CpQ005: TBrvClientDataSet;
    SpQ005: TDataSource;
    CpQ004Cab: TBrvClientDataSet;
    SpQ004Cab: TDataSource;
    PgcPrincipa: TPageControl;
    TbsRNCStatus: TTabSheet;
    Splitter2: TSplitter;
    DbgQ004Sin: TBrvDbGrid;
    DbgQ004Ana: TBrvDbGrid;
    Panel6: TPanel;
    BtnCancelar: TBrvBitBtn;
    TbsRNCDisp: TTabSheet;
    PgcDisposic: TPageControl;
    TbsProEnv: TTabSheet;
    PnlFiltro: TPanel;
    LblDtFinal: TLabel;
    LblDtInicia: TBrvLabel;
    BtnPesquisa: TBrvBitBtn;
    EdtDtInicia: TBrvEditDate;
    EdtDtFinal: TBrvEditDate;
    GrdProdutos: TBrvDbGrid;
    BrvServerProgress: TBrvServerProgress;
    CpQ007: TClientDataSet;
    SpQ007: TDataSource;
    CblTpUsuCor: TBrvCheckListBox;
    LblTpUsuCor: TBrvLabel;
    TbsAnexos: TTabSheet;
    DbgAnexos: TBrvDbGrid;
    Panel7: TPanel;
    Label1: TLabel;
    EdtDiretori: TBrvEdit;
    BtnSalvar: TBrvBitBtn;
    CpQ006: TClientDataSet;
    SpQ006: TDataSource;
    TbsParInt: TTabSheet;
    BrvDbGrid1: TBrvDbGrid;
    Panel5: TPanel;
    BtnVoltarPro: TBrvBitBtn;
    BtnGravar: TBrvBitBtn;
    BrvImprimir: TBrvBitBtn;
    PnlResult: TPanel;
    Panel9: TPanel;
    popMarcar: TPopupMenu;
    MarcarTodos1: TMenuItem;
    DesmarcarTodos1: TMenuItem;
    LblNrRNC: TBrvLabel;
    EdtNrRnc: TBrvEditNum;
    TbsDadCom: TTabSheet;
    Panel2: TPanel;
    Label8: TLabel;
    BrvDBRetCon14: TBrvDBRetCon;
    BrvDBRetCon15: TBrvDBRetCon;
    Label9: TLabel;
    BrvDBRetCon16: TBrvDBRetCon;
    BrvDBRetCon17: TBrvDBRetCon;
    Label10: TLabel;
    BrvDBRetCon18: TBrvDBRetCon;
    Label11: TLabel;
    BrvDBRetCon19: TBrvDBRetCon;
    Label2: TLabel;
    BrvDBRetCon1: TBrvDBRetCon;
    Label3: TLabel;
    BrvDBRetCon2: TBrvDBRetCon;
    Label4: TLabel;
    BrvDBRetCon3: TBrvDBRetCon;
    Label5: TLabel;
    BrvDBRetCon4: TBrvDBRetCon;
    Label7: TLabel;
    BrvDBRetCon11: TBrvDBRetCon;
    BrvDBRetCon13: TBrvDBRetCon;
    BrvDBRetCon12: TBrvDBRetCon;
    TabSheet1: TTabSheet;
    MemTxDisPos: TDBMemo;
    TbsApuVar: TTabSheet;
    Panel1: TPanel;
    GroupBox1: TGroupBox;
    Label16: TLabel;
    Label6: TLabel;
    Label12: TLabel;
    Label13: TLabel;
    Label14: TLabel;
    EdtVrOutDir: TBrvDbEdit;
    EdtVrMulta: TBrvDbEdit;
    EdtVrMatBra: TBrvDbEdit;
    EdtVrMatTer: TBrvDbEdit;
    GroupBox2: TGroupBox;
    Label18: TLabel;
    Label15: TLabel;
    Label22: TLabel;
    Label23: TLabel;
    Label24: TLabel;
    Label17: TLabel;
    Label19: TLabel;
    Label20: TLabel;
    Label21: TLabel;
    Label26: TLabel;
    Label27: TLabel;
    Label28: TLabel;
    Label29: TLabel;
    EdtVrHorPar: TBrvDbEdit;
    EdtVrHorCor: TBrvDbEdit;
    EdtVrCusInc: TBrvDbEdit;
    EdtVrMatSup: TBrvDbEdit;
    Label30: TLabel;
    Label31: TLabel;
    EdtVrKmRoda: TBrvDbEdit;
    Label32: TLabel;
    Label33: TLabel;
    EdtVrOutInd: TBrvDbEdit;
    EdtQtHorPar: TBrvDbEdit;
    Label35: TLabel;
    EdtQtHorCor: TBrvDbEdit;
    Label36: TLabel;
    Panel3: TPanel;
    BrvLabel1: TBrvLabel;
    LblTtCusDir: TBrvDBRetCon;
    Label25: TLabel;
    Panel4: TPanel;
    LblTtCusInd: TBrvDBRetCon;
    Label34: TLabel;
    BrvLabel2: TBrvLabel;
    CcParams: TClientDataSet;
    CcParamsXmlQ004: TMemoField;
    CcParamsNmForm: TStringField;
    Panel8: TPanel;
    BrvLabel7: TBrvLabel;
    LblVrCusto: TBrvDBRetCon;
    procedure FormCreate(Sender: TObject);
    procedure BtnCancelarClick(Sender: TObject);
    procedure BtnGravarClick(Sender: TObject);
    procedure BrvImprimirClick(Sender: TObject);
    procedure CpQ004SinAfterScroll(DataSet: TDataSet);
    procedure DetalharRNCClick(Sender: TObject);
    procedure DbgQ004AnaDblClick(Sender: TObject);
    procedure LoadStringFromFile(pDsCaminh: String);
    procedure BtnSalvarClick(Sender: TObject);
    procedure BtnVoltarProClick(Sender: TObject);
    procedure BtnPesquisaClick(Sender: TObject);
    procedure ValidarEntradaDados;
    procedure LimparCampos;
    procedure MarcarTodos1Click(Sender: TObject);
    procedure DesmarcarTodos1Click(Sender: TObject);
    procedure EdtVrMatTerKeyPress(Sender: TObject; var Key: Char);
    procedure EdtVrMatTerExit(Sender: TObject);
    procedure EdtVrHorParExit(Sender: TObject);
    procedure DbgAnexosKeyPress(Sender: TObject; var Key: Char);
  private
    procedure AbrirDetalharRNC;
    procedure SomarTotalCustoDireto;
    procedure SomarTotalCustoIndireto;
  public
    { Public declarations }
  end;

var
  Mov0032 : TMov0032;

implementation

uses UDmSrvApl, UBrvISO;

{$R *.dfm}

procedure TMov0032.BrvImprimirClick(Sender: TObject);
begin
      UBrvISO.GerarRelatorioRNC(CpQ004Ana.FieldByName('NrRnc').AsString, Self.Name);
end;

procedure TMov0032.DbgAnexosKeyPress(Sender: TObject; var Key: Char);
begin
      inherited;
      Abort;
end;

procedure TMov0032.DbgQ004AnaDblClick(Sender: TObject);
begin
      inherited;
      AbrirDetalharRNC;
end;

procedure TMov0032.BtnSalvarClick(Sender: TObject);
var lDsCaminh : String;
    lCpQ007   : TBrvClientDataSet;
begin
      inherited;
      if (CpQ007.RecordCount > 0) then
      begin
            try
                if EdtDiretori.Text = '' then
                begin
                      EdtDiretori.SetFocus;
                      raise Exception.Create(
                                      'É obrigatório informar um diretório para salvar os Anexos!');
                end;

                lCpQ007              := TBrvClientDataSet.Create(nil);
                lCpQ007.BrDicionario := DmSrvApl.BrvDicionario;
                lCpQ007.Data         := CpQ007.Data;

                lCpQ007.Filtered := False;
                lCpQ007.Filter   := 'SnMarcad = ''S''';
                lCpQ007.Filtered := True;

                if (lCpQ007.RecordCount > 0) then
                begin
                      BrvServerProgress.Start(Self.Caption, 'Salvando Anexo(s)', 100, 10);
                      try
                          lCpQ007.First;
                          while not lCpQ007.Eof do
                          begin
                                lDsCaminh := lCpQ007.FieldByName('NmDirSer').AsString + '\' +
                                             lCpQ007.FieldByName('NmArquiv').AsString;
                                LoadStringFromFile(lDsCaminh);
                                lCpQ007.Next;
                          end;
                      finally
                          BrvServerProgress.Stop;
                      end;
                      MessageDlg('Anexo(s) salvo(s) com sucesso!!!', mtInformation, [mbok], 0);
                end else
                begin
                      MessageDlg('Não há anexos marcados!', mtInformation, [mbok], 0);
                end;
            finally
                FreeAndNil(lCpQ007);
            end;
      end else
      begin
            raise Exception.Create('Não há anexos para salvar!');
      end;
end;

procedure TMov0032.LoadStringFromFile(pDsCaminh: String);
var lStlFile  : TStrings;
    lNmArquiv : String;
begin
      if FileExists(pDsCaminh) then
      begin
            try
                lNmArquiv := EdtDiretori.Text;
                if (copy(lNmArquiv, length(lNmArquiv), 1) <> '\') then
                begin
                      lNmArquiv := lNmArquiv + '\';
                end;
                lStlFile  := TStringList.Create;
                lStlFile.LoadFromFile(pDsCaminh);
                lStlFile.SaveToFile(lNmArquiv + ExtractFileName(pDsCaminh));
            finally
                FreeAndNil(lStlFile);
            end;
    end;
end;

procedure TMov0032.MarcarTodos1Click(Sender: TObject);
begin
      inherited;
      DbgAnexos.SetarTodasColunas('SnMarcad', 'S');
end;

procedure TMov0032.BtnCancelarClick(Sender: TObject);
begin
      if MessageDlg('Deseja realmente cancelar?', mtConfirmation, [mbYes, mbNo], 0) = mrYes then
      begin
            LimparCampos;
      end;
end;

procedure TMov0032.LimparCampos;
begin
      PgcPrincipa.Visible      := False;
      PnlFiltro.Enabled        := True;

      EdtNrRnc.Text            := '0';
      EdtNrRnc.ReadOnly        := False;
      EdtNrRnc.Color           := clWhite;
      EdtNrRnc.BrVisibleButton := True;

      EdtDtInicia.Color        := clWhite;
      EdtDtInicia.Text         := '';
      EdtDtFinal.Color         := clWhite;
      EdtDtFinal.Text          := '';
      CblTpUsuCor.Color        := clWhite;

      BtnPesquisa.Enabled      := True;

      CblTpUsuCor.CheckAll(cbChecked);

      if (CpQ004Sin.Active) then
      begin
            CpQ004Sin.EmptyDataSet;
            CpQ004Sin.Close;
      end;
      if (CpQ004Ana.Active) then
      begin
            CpQ004Ana.EmptyDataSet;
            CpQ004Ana.Close;
      end;
      if (CpQ004Det.Active) then
      begin
            CpQ004Det.EmptyDataSet;
            CpQ004Det.Close;
      end;
      if (CpQ004Cab.Active) then
      begin
            CpQ004Cab.EmptyDataSet;
            CpQ004Cab.Close;
      end;
      if (CpQ005.Active) then
      begin
            CpQ005.EmptyDataSet;
            CpQ005.Close;
      end;
      if (CpQ007.Active) then
      begin
            CpQ007.EmptyDataSet;
            CpQ007.Close;
      end;

      EdtNrRnc.SetFocus;
end;

procedure TMov0032.BtnVoltarProClick(Sender: TObject);
begin
      inherited;
      PgcPrincipa.ActivePageIndex := 0;
end;

procedure TMov0032.BtnGravarClick(Sender: TObject);
var  lConexao  : TSDmAdmClient;
     lDsResult : AnsiString;
begin
      if (Trim(MemTxDisPos.Text) = '') then
      begin
            PgcDisposic.ActivePageIndex := 0;
            MemTxDisPos.SetFocus;
            raise Exception.Create('É obrigatório informar uma Disposição da Não Conformidade!');
      end;

      if (CpQ004Det.State in [dsEdit,dsInsert]) then
      begin
            CpQ004Det.Post;
      end;
      CpQ004Det.Edit;
      CpQ004Det.FieldByName('StRNC').AsString := 'F';
      CpQ004Det.Post;

      if (CcParams.Active) then
      begin
            CcParams.EmptyDataSet;
            CcParams.Close;
      end;

      CcParams.CreateDataSet;
      CcParams.Append;
      CcParams.FieldByName('XmlQ004').AsString := CpQ004Det.XMLData;
      CcParams.FieldByName('NmForm' ).AsString := Self.Name;
      CcParams.Post;

      try
          BrvServerProgress.Start(Self.Caption, 'Gravando informações', 100, 10);
          lConexao  := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
          lDsResult := lConexao.GravarDisposicaoRNC(DmSrvApl.BrvDicionario.Credencial,
                                                                                     CcParams.Data);
      finally
          FreeAndNil(lConexao);
          BrvServerProgress.Stop;
      end;
      DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      MessageDlg('Gravado com sucesso!!!', mtInformation, [mbok], 0);
      AbrirDetalharRNC;
end;

procedure TMov0032.ValidarEntradaDados;
var lNrIndice : Integer;
    lDsUsuCor : String;
    lDsSqlCor : String;
    lDsLefJoi : String;
    lCdUsuari : String;
begin
      BrvListParam.Clear;

      if ((EdtNrRnc.BrAsInteger = 0)  and
          (EdtDtInicia.BrDataVazia )  and
          (EdtDtFinal.BrDataVazia  )) then
      begin
            EdtNrRnc.SetFocus;
            raise Exception.Create('Informe um ' + LblNrRNC.Caption +
                                   ' e/ou uma '  + LblDtInicia.Caption +
                                   ' e/ou uma '  + LblDtFinal.Caption  + '!');
      end;

      if ((not EdtDtInicia.BrDataVazia) and (not EdtDtInicia.BrDataValida)) then
      begin
            EdtDtInicia.SetFocus;
            raise Exception.Create('Verifique a ' + LblDtInicia.Caption + ' informada!');
      end;

      if ((not EdtDtFinal.BrDataVazia) and (not EdtDtFinal.BrDataValida)) then
      begin
            EdtDtFinal.SetFocus;
            raise Exception.Create('Verifique a ' + LblDtFinal.Caption + ' informada!');
      end;

      if ((not EdtDtInicia.BrDataVazia) and (not EdtDtFinal.BrDataVazia) and
                                                  (EdtDtFinal.BrAsDate < EdtDtInicia.BrAsDate)) then
      begin
            EdtDtFinal.SetFocus;
            raise Exception.Create(LblDtFinal.Caption + ' deve ser maior ou igual à '+
                                                                         LblDtInicia.Caption + '!');
      end;

      if (CblTpUsuCor.BrCheckedCount = 0) then
      begin
            CblTpUsuCor.SetFocus;
            raise Exception.Create('Nenhum tipo de ' + LblTpUsuCor.Caption + ' foi selecionado!');
      end;

      lDsSqlCor := ' (';
      lDsLefJoi := '';
      lCdUsuari := IntToStr(DmSrvApl.BrvDicionario.UserCode);
      for lNrIndice := 0 to CblTpUsuCor.Items.Count -1 do
      begin
            if (CblTpUsuCor.Checked[lNrIndice]) then
            begin
                  lDsUsuCor := lDsUsuCor + CblTpUsuCor.Items[lNrIndice] + ',';
                  if (LNrIndice = 0) then
                  begin
                        lDsSqlCor := lDsSqlCor + '(Q004.CdUsuEmi  = '+lCdUsuari+')';
                        if (((CblTpUsuCor.Items.Count > lNrIndice+1)   and
                             (CblTpUsuCor.Checked[lNrIndice+1]     ))  or
                            ((CblTpUsuCor.Items.Count > lNrIndice+2)   and
                             (CblTpUsuCor.Checked[lNrIndice+2]     ))) then
                        begin
                              lDsSqlCor := lDsSqlCor + ' or ';
                        end;
                  end else
                  begin
                        if (LNrIndice = 1) then
                        begin
                              lDsSqlCor := lDsSqlCor + '(Q004.CdUsuDes  = '+lCdUsuari+')';
                              if ((CblTpUsuCor.Items.Count > lNrIndice+1)  and
                                  (CblTpUsuCor.Checked[lNrIndice+1]     )) then
                              begin
                                    lDsSqlCor := lDsSqlCor + ' or ';
                              end;
                        end  else
                        begin
                              if (LNrIndice = 2) then
                              begin
                                    lDsSqlCor := lDsSqlCor + '(Q006.CdUsuari = '+lCdUsuari+')';
                                    lDsLefJoi := 'Left Join Q006 Q006 on ' +
                                                          '(Q006.NrRNC = Q004.NrRNC and ' +
                                                           'Q006.CdUsuari = '+lCdUsuari+')';
                              end
                        end;
                  end;
            end;
      end;
      lDsSqlCor := lDsSqlCor + ') ';
      lDsUsuCor := copy(lDsUsuCor, 1, length(lDsUsuCor)-1);

      BrvListParam.Add('DsSqlCor', '', '', lDsSqlCor);
      BrvListParam.Add('DsLefJoi', '', '', lDsLefJoi);

      if (EdtNrRnc.BrAsInteger > 0) then
      begin
            BrvListParam.Add('NrRNC', '', '', ' and Q004.NrRNC = ' + EdtNrRnc.Text);
      end else
      begin
            BrvListParam.Add('NrRNC', '', '', '');
      end;

      if (not EdtDtInicia.BrDataVazia) then
      begin
            BrvListParam.Add('DtIniLan', '', '',
                                      ' and Q004.DtEmiRNC >= <$hh''' + EdtDtInicia.Text + '''hh$>');
      end else
      begin
            BrvListParam.Add('DtIniLan', '', '', '');
      end;

      if (not EdtDtFinal.BrDataVazia) then
      begin
            BrvListParam.Add('DtFinLan', '', '',
                                      ' and Q004.DtEmiRNC <= <$hh''' + EdtDtFinal.Text  + '''hh$>');
      end else
      begin
            BrvListParam.Add('DtFinLan', '', '', '');
      end;
end;

procedure TMov0032.BtnPesquisaClick(Sender: TObject);
begin
      inherited;
      ValidarEntradaDados;

      try
          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
          CpQ004Sin.Close;
          CpQ004Sin.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(236,
                                                                 BrvListParam.AsBrParam, Self.Name);
      finally
          BrvServerProgress.Stop;
      end;
      if (CpQ004Sin.RecordCount > 0) then
      begin
            CpQ004Sin.Open;

            PnlFiltro.Enabled        := False;

            EdtNrRnc.ReadOnly        := True;
            EdtNrRnc.Color           := $00DDE2E3;
            EdtNrRnc.BrVisibleButton := False;

            EdtDtInicia.Color        := $00DDE2E3;
            EdtDtFinal.Color         := $00DDE2E3;
            CblTpUsuCor.Color        := $00DDE2E3;

            BtnPesquisa.Enabled := False;
            PgcPrincipa.Visible := True;
            PgcPrincipa.ActivePageIndex := 0;
      end else
      begin
            MessageDlg('Nenhum registro encontrado!!!', mtInformation, [mbok], 0);
      end;
end;

procedure TMov0032.CpQ004SinAfterScroll(DataSet: TDataSet);
begin
      ValidarEntradaDados;
      BrvListParam.Add('StRNC', '', '', CpQ004Sin.FieldByName('StRNC').AsString);
      CpQ004Ana.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(235,
                                                                 BrvListParam.AsBrParam, Self.Name);
      if (CpQ004Ana.RecordCount > 0) then
      begin
            CpQ004Ana.ReadOnly := False;
            while not CpQ004Ana.Eof do
            begin
                  CpQ004Ana.Edit;
                  if (CpQ004Ana.FieldByName('QtAnexo').AsInteger = 0) then
                  begin
                        CpQ004Ana.FieldByName('SnAnexo').Text := '';
                  end else
                  begin
                        CpQ004Ana.FieldByName('SnAnexo').Text := 'R';
                  end;
                  CpQ004Ana.Post;
                  CpQ004Ana.Next;
            end;
            CpQ004Ana.FieldByName('SnAnexo').Alignment := taCenter;
            CpQ004Ana.ReadOnly := True;
      end;
end;

procedure TMov0032.AbrirDetalharRNC;
var lTxMask : String;
begin
      try
          BrvListParam.Clear;
          if not (CpQ004Ana.State in [dsInactive]) then
          begin
                BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);

                BrvListParam.Add('NrRNC', '', '', CpQ004Ana.FieldByName('NrRNC').AsString);

                if (CpQ004Det.Active) then
                begin
                      CpQ004Det.EmptyDataSet;
                      CpQ004Det.Close;
                end;

                CpQ004Det.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(237,
                                                                 BrvListParam.AsBrParam, Self.Name);
                if (CpQ004Det.RecordCount > 0) then
                begin
                      lTxMask := '###,###,##0.00';

                      TFloatField(CpQ004Det.FieldByName('VrCusto' )).DisplayFormat := lTxMask;

                      TFloatField(CpQ004Det.FieldByName('VrMatTer')).DisplayFormat := lTxMask;
                      TFloatField(CpQ004Det.FieldByName('VrMatBra')).DisplayFormat := lTxMask;
                      TFloatField(CpQ004Det.FieldByName('VrMulta' )).DisplayFormat := lTxMask;
                      TFloatField(CpQ004Det.FieldByName('VrOutDir')).DisplayFormat := lTxMask;
                      TFloatField(CpQ004Det.FieldByName('TtCusDir')).DisplayFormat := lTxMask;

                      TFloatField(CpQ004Det.FieldByName('VrHorPar')).DisplayFormat := lTxMask;
                      TFloatField(CpQ004Det.FieldByName('QtHorPar')).DisplayFormat := '0';
                      TFloatField(CpQ004Det.FieldByName('VrHorCor')).DisplayFormat := lTxMask;
                      TFloatField(CpQ004Det.FieldByName('QtHorCor')).DisplayFormat := '0';
                      TFloatField(CpQ004Det.FieldByName('VrCusInc')).DisplayFormat := lTxMask;
                      TFloatField(CpQ004Det.FieldByName('VrMatSup')).DisplayFormat := lTxMask;
                      TFloatField(CpQ004Det.FieldByName('VrKmRoda')).DisplayFormat := lTxMask;
                      TFloatField(CpQ004Det.FieldByName('VrOutInd')).DisplayFormat := lTxMask;
                      TFloatField(CpQ004Det.FieldByName('TtCusInd')).DisplayFormat := lTxMask;

                      CpQ004Cab.Data     := CpQ004Ana.Data;
                      CpQ004Cab.Filtered := False;
                      CpQ004Cab.Filter   := 'NrRNC = ' + CpQ004Ana.FieldByName('NrRNC').AsString;
                      CpQ004Cab.Filtered := True;

                      BrvListParam.Clear;
                      BrvListParam.Add('NrRNC', '', '', CpQ004Det.FieldByName('NrRNC').AsString);

                      if (CpQ005.Active) then
                      begin
                            CpQ005.EmptyDataSet;
                            CpQ005.Close;
                      end;
                      CpQ005.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(247,
                                                                 BrvListParam.AsBrParam, Self.Name);

                      if (CpQ006.Active) then
                      begin
                            CpQ006.EmptyDataSet;
                            CpQ006.Close;
                      end;
                      CpQ006.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(248,
                                                                 BrvListParam.AsBrParam, Self.Name);

                      if (CpQ007.Active) then
                      begin
                            CpQ007.EmptyDataSet;
                            CpQ007.Close;
                      end;
                      CpQ007.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(283,
                                                                 BrvListParam.AsBrParam, Self.Name);

                      PgcPrincipa.ActivePageIndex := 1;
                      PgcDisposic.ActivePageIndex := 0;

                      if ((CpQ004Det.FieldByName('CdUsuDes').AsInteger =
                                            DmSrvApl.BrvDicionario.UserCode)  and
                          (CpQ004Det.FieldByName('StRNC'   ).AsString = 'A')) then
                      begin
                            CpQ004Det.ReadOnly    := False;
                            BtnGravar.Enabled     := True;
                            MemTxDisPos.SetFocus;
                      end else
                      begin
                            CpQ004Det.ReadOnly    := True;
                            BtnGravar.Enabled     := False;
                      end;
                      EdtDiretori.Enabled := True;
                      BtnSalvar.Enabled   := True;
                end;
          end;
      finally
          BrvServerProgress.Stop;
      end;
end;

procedure TMov0032.DesmarcarTodos1Click(Sender: TObject);
begin
      inherited;
      DbgAnexos.SetarTodasColunas('SnMarcad', 'N');
end;

procedure TMov0032.DetalharRNCClick(Sender: TObject);
begin
      inherited;
      AbrirDetalharRNC;
end;

procedure TMov0032.EdtVrHorParExit(Sender: TObject);
begin
      inherited;
      if not CpQ004Det.ReadOnly then
      begin
            SomarTotalCustoIndireto;
      end;
end;

procedure TMov0032.EdtVrMatTerExit(Sender: TObject);
begin
      inherited;
      if not CpQ004Det.ReadOnly then
      begin
            SomarTotalCustoDireto;
      end;
end;

procedure TMov0032.EdtVrMatTerKeyPress(Sender: TObject; var Key: Char);
begin
      inherited;
      if not (CharInSet(Key,['0'..'9',#8,#9])) then
      begin
            Abort;
      end;
end;

procedure TMov0032.SomarTotalCustoDireto;
begin
      if(CpQ004Det.State in [dsEdit,dsInsert]) then
      begin
            CpQ004Det.Post;
      end;
      CpQ004Det.Edit;

      CpQ004Det.FieldByName('TtCusDir').AsFloat := 0;

      if CpQ004Det.FieldByName('VrMatTer').AsString <> '' then
      begin
            CpQ004Det.FieldByName('TtCusDir').AsFloat := CpQ004Det.FieldByName('TtCusDir').AsFloat +
                                                         CpQ004Det.FieldByName('VrMatTer').AsFloat;
      end;
      if CpQ004Det.FieldByName('VrMatBra').AsString <> '' then
      begin
            CpQ004Det.FieldByName('TtCusDir').AsFloat := CpQ004Det.FieldByName('TtCusDir').AsFloat +
                                                         CpQ004Det.FieldByName('VrMatBra').AsFloat;
      end;
      if CpQ004Det.FieldByName('VrMulta').AsString <> '' then
      begin
            CpQ004Det.FieldByName('TtCusDir').AsFloat := CpQ004Det.FieldByName('TtCusDir').AsFloat +
                                                         CpQ004Det.FieldByName('VrMulta').AsFloat;
      end;
      if CpQ004Det.FieldByName('VrOutDir').AsString <> '' then
      begin
            CpQ004Det.FieldByName('TtCusDir').AsFloat := CpQ004Det.FieldByName('TtCusDir').AsFloat +
                                                         CpQ004Det.FieldByName('VrOutDir').AsFloat;
      end;
      CpQ004Det.Post;
      CpQ004Det.Edit;
      CpQ004Det.FieldByName('VrCusto').AsFloat := CpQ004Det.FieldByName('TtCusDir').AsFloat +
                                                  CpQ004Det.FieldByName('TtCusInd').AsFloat;
      CpQ004Det.Post;
end;

procedure TMov0032.SomarTotalCustoIndireto;
begin
      if(CpQ004Det.State in [dsEdit,dsInsert]) then
      begin
            CpQ004Det.Post;
      end;
      CpQ004Det.Edit;
      CpQ004Det.FieldByName('TtCusInd').AsFloat := 0;
      if CpQ004Det.FieldByName('VrHorPar').AsString <> '' then
      begin
            CpQ004Det.FieldByName('TtCusInd').AsFloat := CpQ004Det.FieldByName('TtCusInd').AsFloat +
                                                         CpQ004Det.FieldByName('VrHorPar').AsFloat;
      end;
      if CpQ004Det.FieldByName('VrHorCor').AsString <> '' then
      begin
            CpQ004Det.FieldByName('TtCusInd').AsFloat := CpQ004Det.FieldByName('TtCusInd').AsFloat +
                                                         CpQ004Det.FieldByName('VrHorCor').AsFloat;
      end;
      if CpQ004Det.FieldByName('VrCusInc').AsString <> '' then
      begin
            CpQ004Det.FieldByName('TtCusInd').AsFloat := CpQ004Det.FieldByName('TtCusInd').AsFloat +
                                                         CpQ004Det.FieldByName('VrCusInc').AsFloat;
      end;
      if CpQ004Det.FieldByName('VrMatSup').AsString <> '' then
      begin
            CpQ004Det.FieldByName('TtCusInd').AsFloat := CpQ004Det.FieldByName('TtCusInd').AsFloat +
                                                         CpQ004Det.FieldByName('VrMatSup').AsFloat;
      end;
      if CpQ004Det.FieldByName('VrKmRoda').AsString <> '' then
      begin
            CpQ004Det.FieldByName('TtCusInd').AsFloat := CpQ004Det.FieldByName('TtCusInd').AsFloat +
                                                         CpQ004Det.FieldByName('VrKmRoda').AsFloat;
      end;
      if CpQ004Det.FieldByName('VrOutInd').AsString <> '' then
      begin
            CpQ004Det.FieldByName('TtCusInd').AsFloat := CpQ004Det.FieldByName('TtCusInd').AsFloat +
                                                         CpQ004Det.FieldByName('VrOutInd').AsFloat;
      end;
      CpQ004Det.Post;
      CpQ004Det.Edit;
      CpQ004Det.FieldByName('VrCusto').AsFloat := CpQ004Det.FieldByName('TtCusDir').AsFloat +
                                                  CpQ004Det.FieldByName('TtCusInd').AsFloat;
      CpQ004Det.Post;
end;

procedure TMov0032.FormCreate(Sender: TObject);
var lNrIndice : Integer;
begin
      inherited;
      TbsRNCStatus.TabVisible := false;
      TbsRNCDisp.TabVisible   := false;
      for lNrIndice := 0 to CblTpUsuCor.Items.Count -1 do
      begin
            CblTpUsuCor.Checked[lNrIndice] := True;
      end;
end;

initialization
      RegisterClass(TMov0032);

finalization
      UnRegisterClass(TMov0032);

end.
