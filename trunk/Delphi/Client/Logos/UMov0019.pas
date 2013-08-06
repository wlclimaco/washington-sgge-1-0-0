unit UMov0019;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, FileCtrl, ComCtrls,
  BrvEdit, BrvAlertProgress, DB, Grids, BrvDbGrids, BrvDbGrid, DBClient, Menus, BrvEditNum,
  BrvListParam, ImgList, BrvCustomEdit;

type
  TMov0019 = class(TMov0000)
    PgcAnalise: TPageControl;
    TbsDiretorio: TTabSheet;
    Panel1: TPanel;
    TbsOperacao: TTabSheet;
    Panel2: TPanel;
    RdgOper: TRadioGroup;
    TbsProcessamento: TTabSheet;
    Panel3: TPanel;
    FileListBox: TFileListBox;
    TbsResultado: TTabSheet;
    Panel4: TPanel;
    PnlNext1: TPanel;
    BtnNext: TBitBtn;
    BtnVoltar: TBitBtn;
    EdtLocal: TBrvEdit;
    Label1: TLabel;
    BrvAlertProgress: TBrvAlertProgress;
    CcLogOper: TClientDataSet;
    CcLogOperNrMapa: TIntegerField;
    CcLogOperDtProcess: TDateTimeField;
    BrvDbGrid: TBrvDbGrid;
    DtsLogOper: TDataSource;
    CcLogOperCdUsuari: TIntegerField;
    CcLogOperSnCancela: TStringField;
    CcLogOperQtSemID: TIntegerField;
    CcLogOperDsListPro: TStringField;
    CcW005: TClientDataSet;
    CcLogOperCdClient: TIntegerField;
    CcLogOperQtRegLis: TIntegerField;
    CcW008: TClientDataSet;
    BrvDbGrid1: TBrvDbGrid;
    CcMapa: TClientDataSet;
    DtsMapa: TDataSource;
    CcMapaNrMapa: TIntegerField;
    CcMapaNrFinaliza: TIntegerField;
    CcMapaNrFecha: TIntegerField;
    CcMapaNrCancel: TIntegerField;
    Splitter1: TSplitter;
    PopupMenu: TPopupMenu;
    MultiplasFinalizaes1: TMenuItem;
    CcMapaStOpeLog: TStringField;
    Aguardando1: TMenuItem;
    GroupBox7: TGroupBox;
    Label61: TLabel;
    EdtTTReg: TBrvEditNum;
    Label2: TLabel;
    EdtTTRegFil: TBrvEditNum;
    FinalizarOperao1: TMenuItem;
    CcLogOperSnLista: TStringField;
    PnlFiltros: TPanel;
    BrvListParam: TBrvListParam;
    StgFiltros: TStringGrid;
    procedure BtnNextClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);
    procedure CcMapaAfterScroll(DataSet: TDataSet);
    procedure MultiplasFinalizaes1Click(Sender: TObject);
    procedure Aguardando1Click(Sender: TObject);
    procedure FinalizarOperao1Click(Sender: TObject);
    procedure PopupMenuPopup(Sender: TObject);
  private
    procedure ProcessarEntrada;
    procedure ProcessarSaida;
    procedure StrToClientDataSet(pDsLista: String; CcDataTmp: TClientDataSet);
    { Private declarations }
  public
    { Public declarations }
    gnmarqini : String;
  end;

var
  Mov0019: TMov0019;

implementation

uses UDmSrvApl, UClaSrv;

{$R *.dfm}

procedure TMov0019.CcMapaAfterScroll(DataSet: TDataSet);
begin
      inherited;

      if (CcLogOper.Active) then
      begin
            if (CcMapa.Active) then
            begin
                  if (CcMapa.RecordCount > 0) then
                  begin
                        CcLogOper.Filtered := False;
                        CcLogOper.Filter   := 'NrMapa = ' + CcMapaNrMapa.AsString;
                        CcLogOper.Filtered := True;
                  end else
                  begin
                        CcLogOper.Filtered := False;
                        CcLogOper.Filter   := 'NrMapa = 0';
                        CcLogOper.Filtered := True;
                  end;
            end;
      end;
end;

procedure TMov0019.FinalizarOperao1Click(Sender: TObject);
var lConexao : TSDmWmsClient;
    lResult  : String;
    pCdUsuari: Integer; pNrMapa, pdslista, pCdClient, pSnCancel: WideString;
    pQtProSid: Integer;
begin
      inherited;
      lConexao := TSDmWmsClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      pCdUsuari := CcLogOperCdUsuari.AsInteger;
      pNrMapa   := CcLogOperNrMapa.AsString;
      pdslista  := CcLogOperDsListPro.AsString;
      pCdClient := CcLogOperCdClient.AsString;
      pSnCancel := 'N';
      pQtProSid := CcLogOperQtSemID.AsInteger;

      case RdgOper.ItemIndex of
        0 : lResult := lConexao.ConferenciaEntradaFinalizar(pCdUsuari, pNrMapa, pdslista, pSnCancel,
                                                                                         pQtProSid);
        1 : lResult := lConexao.ConferenciaSaidaFinalizar(pCdUsuari, pNrMapa,
                                                         pdslista, pCdClient, pSnCancel, pQtProSid);
      end;

      if (Copy(lResult,1,1) = '0') then
      begin
            ShowMessage('Finalizado com sucesso!');
            CcMapa.Edit;
            CcMapaStOpeLog.AsString := 'D';
            CcMapa.Post;
      end else
      begin
            ShowMessage(Copy(lResult,2, Length(lResult)));
      end;

      lConexao.Destroy;
end;

procedure TMov0019.FormCreate(Sender: TObject);
begin
      inherited;

      PgcAnalise.ActivePageIndex := 0;
end;

procedure TMov0019.MultiplasFinalizaes1Click(Sender: TObject);
begin
      if Aguardando1.Checked then
      begin
            if (not MultiplasFinalizaes1.Checked) then
            begin
                  MultiplasFinalizaes1.Checked := true;
                  CcMapa.Filtered := False;
                  CcMapa.Filter   := 'NrFinaliza > 1 and StOpeLog = ''A''';
                  CcMapa.Filtered := True;
                  EdtTTRegFil.Text := FormatFloat('0', CcMapa.RecordCount);
            end else
            begin
                  MultiplasFinalizaes1.Checked := false;
                  CcMapa.Filtered := False;
                  CcMapa.Filter   := 'StOpeLog = ''A''';
                  CcMapa.Filtered := True;
                  EdtTTRegFil.Text := FormatFloat('0', CcMapa.RecordCount);
            end;
      end else
      begin
            if (not MultiplasFinalizaes1.Checked) then
            begin
                  MultiplasFinalizaes1.Checked := true;
                  CcMapa.Filtered := False;
                  CcMapa.Filter   := 'NrFinaliza > 1';
                  CcMapa.Filtered := True;
                  EdtTTRegFil.Text := FormatFloat('0', CcMapa.RecordCount);
            end else
            begin
                  MultiplasFinalizaes1.Checked := false;
                  CcMapa.Filtered := False;
                  EdtTTRegFil.Text := '0';
            end;
      end;
end;

procedure TMov0019.Aguardando1Click(Sender: TObject);
begin
      inherited;
      if MultiplasFinalizaes1.Checked then
      begin
            if (not Aguardando1.Checked) then
            begin
                  Aguardando1.Checked := true;
                  CcMapa.Filtered := False;
                  CcMapa.Filter   := 'NrFinaliza > 1 and StOpeLog = ''A''';
                  CcMapa.Filtered := True;
                  EdtTTRegFil.Text := FormatFloat('0', CcMapa.RecordCount);
            end else
            begin
                  Aguardando1.Checked := false;
                  CcMapa.Filtered  := False;
                  CcMapa.Filter    := 'NrFinaliza > 1';
                  CcMapa.Filtered  := True;
                  EdtTTRegFil.Text := FormatFloat('0', CcMapa.RecordCount);
            end;
      end else
      begin
            if (not Aguardando1.Checked) then
            begin
                  Aguardando1.Checked := true;
                  CcMapa.Filtered  := False;
                  CcMapa.Filter    := 'StOpeLog = ''A''';
                  CcMapa.Filtered  := True;
                  EdtTTRegFil.Text := FormatFloat('0', CcMapa.RecordCount);
            end else
            begin
                  Aguardando1.Checked := false;
                  CcMapa.Filtered     := False;
                  EdtTTRegFil.Text    := '0';
            end;
      end;
end;

procedure TMov0019.BtnVoltarClick(Sender: TObject);
begin
      inherited;
      case PgcAnalise.ActivePageIndex of
      1 : begin
                BtnVoltar.Visible := False;
                PnlFiltros.Visible := False;
                PgcAnalise.ActivePageIndex := 0;
          end;
      2 : begin
                PgcAnalise.ActivePageIndex := 1;
                BtnNext.Visible := True;
          end;
      end;
end;


procedure TMov0019.BtnNextClick(Sender: TObject);
begin
      inherited;

      case PgcAnalise.ActivePageIndex of
      0 : begin
                BtnVoltar.Visible := True;
                PgcAnalise.ActivePageIndex := 1;
                PnlFiltros.Visible := True;
                BrvListParam.Clear;
                BrvListParam.Add('Diretório', 'Diretório', EdtLocal.Text, EdtLocal.Text);
                BrvListParam.SetStgParam(StgFiltros);
          end;
      1 : begin
                PgcAnalise.ActivePageIndex := 2;
                FileListBox.Drive := Char(EdtLocal.Text[1]);
                FileListBox.Directory := Trim(EdtLocal.Text);

                FileListBox.Mask := '*.log';

                case RdgOper.ItemIndex of
                  0 : begin
                            BrvListParam.Clear;
                            BrvListParam.Add('Diretório', 'Diretório', EdtLocal.Text, EdtLocal.Text);
                            BrvListParam.Add('Operação', 'Operação', 'Entrada', 'Entrada');
                            BrvListParam.SetStgParam(StgFiltros);
                            ProcessarEntrada;
                      end;
                  1 : begin
                            BrvListParam.Clear;
                            BrvListParam.Add('Diretório', 'Diretório', EdtLocal.Text, EdtLocal.Text);
                            BrvListParam.Add('Operação', 'Operação', 'Saída', 'Saída');
                            BrvListParam.SetStgParam(StgFiltros);
                            ProcessarSaida;
                      end;
                end;

                BtnNext.Visible := False;
          end;
      end;
end;

procedure TMov0019.PopupMenuPopup(Sender: TObject);
begin
      inherited;
      if (MultiplasFinalizaes1.Checked and Aguardando1.Checked) then
      begin
            FinalizarOperao1.Visible := True;
      end;
end;

procedure TMov0019.ProcessarEntrada;
Var lnridx    : Integer;
    lDsArqLog : TStrings;
    lNmArqLog : String;
    lDtHrLog  : String;
    lDsDomini : TStrings;
    lVrDomini : TStrings;
begin
      inherited;

      DmSrvApl.BrvDicionario.AtributoDominioValores('W005', 'StOpeLog', lDsDomini, lVrDomini);

      BrvDbGrid1.Columns[4].PickList.Text    := lDsDomini.Text;
      BrvDbGrid1.Columns[4].BrPickValue.Text := lVrDomini.Text;
      BrvDbGrid.Columns[6].Visible          := True;

      if CcMapa.Active then
      begin
            CcMapa.EmptyDataSet;
            CcMapa.Close;
      end;

      if (CcLogOper.Active) then
      begin
            CcLogOper.EmptyDataSet;
            CcLogOper.Close;
      end;

      CcMapa.CreateDataSet;

      CcMapa.AfterScroll := nil;

      CcLogOper.CreateDataSet;

      BrvAlertProgress.BrCaption  := Self.Caption;
      BrvAlertProgress.BrProcesso := 'Processando arquivos...';
      BrvAlertProgress.ShowAlert;
      BrvAlertProgress.BrMax(FileListBox.Items.Count);

      lDsArqLog := TStringList.Create;
      lDsArqLog.Clear;

      EdtTTReg.Text := FormatFloat('0', FileListBox.Items.Count);

      for lnridx := 0 to FileListBox.Items.Count -1 do
      begin
            BrvAlertProgress.BrStepIt;

            lDsArqLog.LoadFromFile(FileListBox.Items.Strings[lnridx]);

            lNmArqLog := FileListBox.Items.Strings[lnridx];
            lNmArqLog := Copy(lNmArqLog, 14, Length(lNmArqLog));


            lDtHrLog  := Copy(lNmArqLog, 10, Length(lNmArqLog));
            lDtHrLog  := Copy(lDtHrLog, 5,2) + '/' + Copy(lDtHrLog, 3,2) + '/20' +
                         Copy(lDtHrLog, 1,2) + ' ' + Copy(lDtHrLog, 8,2) + ':' +
                         Copy(lDtHrLog,10,2) + ':' + '00';


            if (CcMapa.Locate('NrMapa', Copy(lNmArqLog,1,8), [loCaseInsensitive])) then
            begin
                  CcMapa.Edit;
                  CcMapaNrFinaliza.AsInteger := CcMapaNrFinaliza.AsInteger + 1;

                  if (Trim(Copy(lDsArqLog.Strings[4],14,10)) = 'S') then
                  begin
                        CcMapaNrCancel.AsInteger := CcMapaNrCancel.AsInteger + 1;
                  end else
                  begin
                        CcMapaNrFecha.AsInteger := CcMapaNrFecha.AsInteger + 1;
                  end;

                  CcMapa.Post;
            end else
            begin
                  CcMapa.Append;
                  CcMapaNrMapa.AsInteger     := StrToInt(Copy(lNmArqLog,1,8));
                  CcMapaNrFinaliza.AsInteger := 1;

                  if (Trim(Copy(lDsArqLog.Strings[4],14,10)) = 'S') then
                  begin
                        CcMapaNrCancel.AsInteger := 1;
                        CcMapaNrFecha.AsInteger  := 0;
                  end else
                  begin
                        CcMapaNrFecha.AsInteger  := 1;
                        CcMapaNrCancel.AsInteger := 0;
                  end;

                  CcW005.Close;
                  CcW005.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(170,
                                                       '<%NrOpeLog%>;' + Copy(lNmArqLog,1,8), Name);
                  CcW005.Open;

                  if (CcW005.RecordCount > 0) then
                  begin
                        CcMapaStOpeLog.AsString := CcW005.FieldByName('StOpeLog').AsString;
                  end;

                  CcMapa.Post;
            end;

            CcLogOper.Append;
            CcLogOperNrMapa.AsInteger     := StrToInt(Copy(lNmArqLog,1,8));
            CcLogOperDtProcess.AsDateTime := StrToDateTime(lDtHrLog);
            CcLogOperCdUsuari.AsInteger   := StrToInt(Copy(lDsArqLog.Strings[3],14,10));
            CcLogOperSnCancela.AsString   := Copy(lDsArqLog.Strings[4],14,10);
            CcLogOperQtSemID.AsInteger    := StrToInt(Copy(lDsArqLog.Strings[5],14,10));
            CcLogOperDsListPro.AsString   := Trim(Copy(lDsArqLog.Text,
                                                  Pos('Lista', lDsArqLog.Text) + 5,
                                                  Length(lDsArqLog.Text)));

            if (CcLogOperDsListPro.AsString <> '') then
            begin
                  CcLogOperSnLista.AsString := 'S';
            end else
            begin
                  CcLogOperSnLista.AsString := 'N';
            end;

            CcLogOperQtRegLis.AsInteger := 0;

            if (Trim(CcLogOperDsListPro.AsString) <> '') then
            begin
                  StrToClientDataSet(CcLogOperDsListPro.AsString, CcW008);
                  CcLogOperQtRegLis.AsInteger := CcW008.RecordCount;
            end;

            CcLogOper.Post;
      end;

      CcMapa.AfterScroll := CcMapaAfterScroll;
      CcLogOper.First;
      CcMapa.First;
end;

procedure TMov0019.ProcessarSaida;
Var lnridx    : Integer;
    lDsArqLog : TStrings;
    lNmArqLog : String;
    lDtHrLog  : String;
    lDsDomini : TStrings;
    lVrDomini : TStrings;
begin
      inherited;

      DmSrvApl.BrvDicionario.AtributoDominioValores('W005', 'StOpeLog', lDsDomini, lVrDomini);

      BrvDbGrid1.Columns[4].PickList.Text    := lDsDomini.Text;
      BrvDbGrid1.Columns[4].BrPickValue.Text := lVrDomini.Text;
      BrvDbGrid.Columns[6].Visible          := True;

      if (CcMapa.Active) then
      begin
            CcMapa.EmptyDataSet;
            CcMapa.Close;
      end;

      if (CcLogOper.Active) then
      begin
            CcLogOper.EmptyDataSet;
            CcLogOper.Close;
      end;

      CcMapa.CreateDataSet;

      CcMapa.AfterScroll := nil;

      CcLogOper.CreateDataSet;

      BrvAlertProgress.BrCaption  := Self.Caption;
      BrvAlertProgress.BrProcesso := 'Processando arquivos...';
      BrvAlertProgress.ShowAlert;
      BrvAlertProgress.BrMax(FileListBox.Items.Count);

      lDsArqLog := TStringList.Create;
      lDsArqLog.Clear;

      EdtTTReg.Text := FormatFloat('0', FileListBox.Items.Count);

      for lnridx := 0 to FileListBox.Items.Count -1 do
      begin
            BrvAlertProgress.BrStepIt;

            lDsArqLog.LoadFromFile(FileListBox.Items.Strings[lnridx]);

            lNmArqLog := FileListBox.Items.Strings[lnridx];
            lNmArqLog := Copy(lNmArqLog, 12, Length(lNmArqLog));


            lDtHrLog  := Copy(lNmArqLog, 10, Length(lNmArqLog));
            lDtHrLog  := Copy(lDtHrLog, 5,2) + '/' + Copy(lDtHrLog, 3,2) + '/20' +
                         Copy(lDtHrLog, 1,2) + ' ' + Copy(lDtHrLog, 8,2) + ':' +
                         Copy(lDtHrLog,10,2) + ':' + '00';


            if (CcMapa.Locate('NrMapa', Copy(lNmArqLog,1,8), [loCaseInsensitive])) then
            begin
                  CcMapa.Edit;
                  CcMapaNrFinaliza.AsInteger := CcMapaNrFinaliza.AsInteger + 1;

                  if (Trim(Copy(lDsArqLog.Strings[4],14,10)) = 'S') then
                  begin
                        CcMapaNrCancel.AsInteger := CcMapaNrCancel.AsInteger + 1;
                  end else
                  begin
                        CcMapaNrFecha.AsInteger := CcMapaNrFecha.AsInteger + 1;
                  end;

                  CcMapa.Post;
            end else
            begin
                  CcMapa.Append;
                  CcMapaNrMapa.AsInteger     := StrToInt(Copy(lNmArqLog,1,8));
                  CcMapaNrFinaliza.AsInteger := 1;

                  if (Trim(Copy(lDsArqLog.Strings[4],14,10)) = 'S') then
                  begin
                        CcMapaNrCancel.AsInteger := 1;
                        CcMapaNrFecha.AsInteger  := 0;
                  end else
                  begin
                        CcMapaNrFecha.AsInteger  := 1;
                        CcMapaNrCancel.AsInteger := 0;
                  end;

                  CcW005.Close;
                  CcW005.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(170,
                                                       '<%NrOpeLog%>;' + Copy(lNmArqLog,1,8), Name);
                  CcW005.Open;

                  if (CcW005.RecordCount > 0) then
                  begin
                        CcMapaStOpeLog.AsString := CcW005.FieldByName('StOpeLog').AsString;
                  end;

                  CcMapa.Post;
            end;


            CcLogOper.Append;
            CcLogOperNrMapa.AsInteger     := StrToInt(Copy(lNmArqLog,1,8));
            CcLogOperDtProcess.AsDateTime := StrToDateTime(lDtHrLog);
            CcLogOperCdUsuari.AsInteger   := StrToInt(Copy(lDsArqLog.Strings[3],14,10));
            CcLogOperSnCancela.AsString   := Copy(lDsArqLog.Strings[4],14,10);
            CcLogOperCdClient.AsInteger   := StrToIntDef(Copy(lDsArqLog.Strings[5],14,10), 0);
            CcLogOperQtSemID.AsInteger    := StrToInt(Copy(lDsArqLog.Strings[6],14,10));
            CcLogOperDsListPro.AsString   := Trim(Copy(lDsArqLog.Text,
                                                  Pos('Lista', lDsArqLog.Text) + 5,
                                                  Length(lDsArqLog.Text)));

            if (CcLogOperDsListPro.AsString <> '') then
            begin
                  CcLogOperSnLista.AsString := 'S';
            end else
            begin
                  CcLogOperSnLista.AsString := 'N';
            end;

            CcLogOperQtRegLis.AsInteger := 0;

            if (Trim(CcLogOperDsListPro.AsString) <> '') then
            begin
                  StrToClientDataSet(CcLogOperDsListPro.AsString, CcW008);
                  CcLogOperQtRegLis.AsInteger := CcW008.RecordCount;
            end;

            CcLogOper.Post;
      end;

      CcMapa.AfterScroll := CcMapaAfterScroll;
      CcLogOper.First;
      CcMapa.First;
end;

procedure TMov0019.StrToClientDataSet(pDsLista : String; CcDataTmp: TClientDataSet);
var nrindice : Integer;
    nridxcmp : Integer;
    dstxtpro : string;
    DsLista  : TStrings;
begin
      DsLista := TStringList.Create;
      DsLista.Text := pDsLista;

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
      RegisterClass(TMov0019);

finalization
      UnRegisterClass(TMov0019);

end.
