unit UCon0015;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, BrvDbGrids,
  BrvDbGrid,
  StdCtrls, Grids, DBGrids, CheckLst, BrvCheckListBox, BrvEdit, Mask,
  BrvEditDate, BrvBitBtn,
  BrvRetCon, BrvEditNum, ComCtrls, DB, DBClient, BrvProgressBar, BrvExcel,
  BrvMesAno,
  BrvListParam, BrvServerProgress, ImgList, Menus, BrvCustomMaskEdit,
  BrvCustomEdit;

type
  TCon0015 = class(TMov0000)
    PgcFundo: TPageControl;
    TbsFiltro: TTabSheet;
    TbsConsulta: TTabSheet;
    Panel3: TPanel;
    Panel2: TPanel;
    BtnExcel: TBrvBitBtn;
    BtnVoltar: TBrvBitBtn;
    Panel4: TPanel;
    Panel1: TPanel;
    BtnPesquisa: TBrvBitBtn;
    CcP002: TClientDataSet;
    DtsP002: TDataSource;
    BrvExcel: TBrvExcel;
    LblQtReg: TLabel;
    Panel5: TPanel;
    Splitter2: TSplitter;
    StgFiltros: TStringGrid;
    BrvListParam: TBrvListParam;
    BrvServerProgress: TBrvServerProgress;
    DbgFiltro: TBrvDbGrid;
    Panel7: TPanel;
    Panel6: TPanel;
    LblCdGruBem: TLabel;
    LblCdBem: TLabel;
    LblDtBase: TLabel;
    LblDtAnoEnt: TLabel;
    EdtCdGruBem: TBrvEditNum;
    LblDsGruBem: TBrvRetCon;
    EdtCdBem: TBrvEditNum;
    LblDsBem: TBrvRetCon;
    EdtDtBase: TBrvMesAno;
    EdtDtAnoEnt: TBrvEditNum;
    LblCdEmpres: TLabel;
    CblCdEmpres: TBrvCheckListBox;
    procedure FormCreate(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);
    procedure BtnExcelClick(Sender: TObject);
    procedure EdtDtBaseChange(Sender: TObject);
    procedure EdtDtAnoEntChange(Sender: TObject);
    procedure BtnPesquisaClick(Sender: TObject);
  private
    procedure ExecutaConsulta(pCtP002: TClientDataSet);
    procedure PreparaConsultaFormatada;
    procedure ValidaEntradaDados;
  public
    { Public declarations }
  end;

var
  Con0015: TCon0015;

implementation

uses UDmSrvApl, BrvFuncoesXE, BrvDominiosXE;

{$R *.dfm}

procedure TCon0015.BtnExcelClick(Sender: TObject);
var
    lLsColuna: TStringList;
    lCtP002: TClientDataSet;
    lNrIdx: Integer;
begin
      lLsColuna := TStringList.Create;

      for lNrIdx := 0 to DbgFiltro.Columns.Count - 1 do
      begin
            lLsColuna.Add(DbgFiltro.Columns[lNrIdx].FieldName);
      end;

      try
          Screen.Cursor := crHourGlass;
          lCtP002 := TClientDataSet.Create(Self);
          lCtP002.Data := CcP002.Data;

        for lNrIdx := 0 to DbgFiltro.Columns.Count - 1 do
        begin
              lLsColuna.Add(DbgFiltro.Columns[lNrIdx].FieldName);
              lCtP002.FieldByName(DbgFiltro.Columns[lNrIdx].FieldName).DisplayLabel :=
                                                            DbgFiltro.Columns[lNrIdx].Title.Caption;
        end;

        lCtP002.First;
        BrvExcel.BrDataSet := lCtP002;
        BrvExcel.Execute(lLsColuna, '', nil);
      finally
        FreeAndNil(lLsColuna);
        Screen.Cursor := crDefault;
      end;
end;

procedure TCon0015.ValidaEntradaDados;
var lDsEmpres : String;
    lNrUltDia : Integer;
begin
      BrvListParam.Clear;

      if (CblCdEmpres.BrCheckedCount = 0) then
      begin
            CblCdEmpres.SetFocus;
            raise Exception.Create('Selecione a(s) ' + LblCdEmpres.Caption + '!');
      end else
      begin
            lDsEmpres := CblCdEmpres.BrCheckedList;
            BrvListParam.Add('CdEmpres', LblCdEmpres.Caption, lDsEmpres, lDsEmpres);
      end;

      if (StrToIntDef(EdtCdGruBem.Text, 0) > 0) then
      begin
            BrvListParam.Add('CdGruBem', LblCdGruBem.Caption, EdtCdGruBem.Text + ' - ' +
                                       LblDsGruBem.Text, 'and P002.CdGruBem = ' + EdtCdGruBem.Text);
      end else
      begin
            BrvListParam.Add('CdGruBem', '', '', '');
      end;

      if (StrToIntDef(EdtCdBem.Text, 0) > 0) then
      begin
            BrvListParam.Add('CdBem', LblCdBem.Caption, EdtCdBem.Text + ' - ' + LblDsBem.Text,
                                                               'and P002.CdBem = ' + EdtCdBem.Text);
      end else
      begin
            BrvListParam.Add('CdBem', '', '', '');
      end;

      if (((Trim(EdtDtBase.Text) = '/') or (Trim(EdtDtBase.Text) = '')) and
         ((Trim(EdtDtAnoEnt.Text) = '0') or (Trim(EdtDtAnoEnt.Text) = ''))) then
      begin
            EdtDtBase.SetFocus;
            raise Exception.Create('Informe a ' + LblDtBase.Caption + ' ou o ' +
                                                  LblDtAnoEnt.Caption + '!');
      end;

      if ((Trim(EdtDtBase.Text) <> '/') and (Trim(EdtDtBase.Text) <> '')) then
      begin
            if (not EdtDtBase.DataValida) then
            begin
                  EdtDtBase.SetFocus;
                  raise Exception.Create('Verifique a ' + LblDtBase.Caption + ' informada!');
            end
            else
            begin
                  BrvListParam.Add('DtBase', LblDtBase.Caption, EdtDtBase.Text,
                                        'and P002.CdBem in (Select P003.CdBem ' + 'From   P003 ' +
                                        'Where  P003.Dtlancto >= <$hh''01/' + EdtDtBase.Text +
                                        '''hh$> and P003.Dtlancto <= <$hh''' + FormatFloat('00',
                                        EdtDtBase.AsDiaFinal) + '/' + EdtDtBase.Text + '''hh$>)');
                  BrvListParam.Add('DtFinLan', '', '',
                                    FormatFloat('00', EdtDtBase.AsDiaFinal) + '/' + EdtDtBase.Text);
            end;
      end else
      begin
            lNrUltDia := BrvFuncoesXE.UltimoDiaDoMes(FormatDateTime('mm/yyyy', Now()));
            BrvListParam.Add('DtBase', LblDtBase.Caption, FormatDateTime('mm/yyyy', Now()) +
                                                                                ' (Mês Atual)', '');
            BrvListParam.Add('DtFinLan', '', '', FormatFloat('00', lNrUltDia) + '/' +
                                                                  FormatDateTime('mm/yyyy', Now()));
      end;

      if ((Trim(EdtDtAnoEnt.Text) <> '0') and (Trim(EdtDtAnoEnt.Text) <> '')) then
      begin
            BrvListParam.Add('DtIniEnt', LblDtAnoEnt.Caption, EdtDtAnoEnt.Text,
                                ' and F001.DtEntrad >= <$hh''01/01/' + EdtDtAnoEnt.Text + '''hh$>');
            BrvListParam.Add('DtFinEnt', '', '', ' and F001.DtEntrad <= <$hh''31/12/' +
                                                                       EdtDtAnoEnt.Text + '''hh$>');
      end
      else begin
            BrvListParam.Add('DtIniEnt', '', '', '');
            BrvListParam.Add('DtFinEnt', '', '', '');
      end;
end;

procedure TCon0015.ExecutaConsulta(pCtP002: TClientDataSet);
begin
      inherited;

      ValidaEntradaDados;

      try
          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
                                 pCtP002.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(167,
                                 BrvListParam.AsBrParam, Self.Name);
      finally
          BrvServerProgress.Stop;
      end;

      BrvListParam.SetStgParam(StgFiltros);
end;

procedure TCon0015.PreparaConsultaFormatada;
begin
      if (CcP002.Active) then
      begin
            CcP002.EmptyDataSet;
            CcP002.Close;
      end;

      CcP002.FieldDefs.Clear;
      CcP002.FieldDefs.Add('DtEntrad',  ftDateTime, 0);
      CcP002.FieldDefs.Add('CdBem',     ftInteger,  0);
      CcP002.FieldDefs.Add('CdProdut',  ftInteger,  0);
      CcP002.FieldDefs.Add('DsProdut',  ftString,  50);
      CcP002.FieldDefs.Add('CdFornec',  ftInteger,  0);
      CcP002.FieldDefs.Add('RsTitula',  ftString,  50);
      CcP002.FieldDefs.Add('VrBem',     ftFloat,    0);
      CcP002.FieldDefs.Add('VrBemDep',  ftFloat,    0);
      CcP002.FieldDefs.Add('VrDeprec',  ftFloat,    0);
      CcP002.FieldDefs.Add('QtParcela', ftFloat,    0);
      CcP002.FieldDefs.Add('aVrDeprec', ftFloat,    0);
      CcP002.FieldDefs.Add('VrImpPis',  ftFloat,    0);
      CcP002.FieldDefs.Add('VrPisApr',  ftFloat,    0);
      CcP002.FieldDefs.Add('QtParPis',  ftFloat,    0);
      CcP002.FieldDefs.Add('aVrPisApr', ftFloat,    0);
      CcP002.FieldDefs.Add('VrImpCof',  ftFloat,    0);
      CcP002.FieldDefs.Add('VrCofApr',  ftFloat,    0);
      CcP002.FieldDefs.Add('QtParCof',  ftFloat,    0);
      CcP002.FieldDefs.Add('aVrCofApr', ftFloat,    0);
      CcP002.CreateDataSet;

      TFloatField(CcP002.FieldByName('VrBem'    )).DisplayFormat := '##,###,##0.00';
      TFloatField(CcP002.FieldByName('VrBemDep' )).DisplayFormat := '##,###,##0.00';
      TFloatField(CcP002.FieldByName('VrDeprec' )).DisplayFormat := '##,###,##0.00';
      TFloatField(CcP002.FieldByName('QtParcela')).DisplayFormat := '##,###,##0.00';
      TFloatField(CcP002.FieldByName('aVrDeprec')).DisplayFormat := '##,###,##0.00';
      TFloatField(CcP002.FieldByName('VrImpPis' )).DisplayFormat := '##,###,##0.00';
      TFloatField(CcP002.FieldByName('VrPisApr' )).DisplayFormat := '##,###,##0.00';
      TFloatField(CcP002.FieldByName('QtParPis' )).DisplayFormat := '##,###,##0.00';
      TFloatField(CcP002.FieldByName('aVrPisApr')).DisplayFormat := '##,###,##0.00';
      TFloatField(CcP002.FieldByName('VrImpCof' )).DisplayFormat := '##,###,##0.00';
      TFloatField(CcP002.FieldByName('VrCofApr' )).DisplayFormat := '##,###,##0.00';
      TFloatField(CcP002.FieldByName('QtParCof' )).DisplayFormat := '##,###,##0.00';
      TFloatField(CcP002.FieldByName('aVrCofApr')).DisplayFormat := '##,###,##0.00';
end;

procedure TCon0015.BtnPesquisaClick(Sender: TObject);
Var
    lCtP002: TClientDataSet;
begin
      try
          lCtP002 := TClientDataSet.Create(Self);
          BrvListParam.Clear;

          ExecutaConsulta(lCtP002);

          if (lCtP002.RecordCount > 0) then
          begin
                LblQtReg.Caption := FormatFloat('0', lCtP002.RecordCount) + ' Registro(s)';

                PreparaConsultaFormatada;

                lCtP002.First;

                try
                    CcP002.DisableControls;
                    CcP002.ReadOnly := False;
                    while not lCtP002.Eof do
                    begin
                          CcP002.Append;
                          CcP002.FieldByName('DtEntrad' ).AsDateTime :=
                                                        lCtP002.FieldByName('DtEntrad' ).AsDateTime;
                          CcP002.FieldByName('CdBem'    ).AsInteger :=
                                                        lCtP002.FieldByName('CdBem'    ).AsInteger;
                          CcP002.FieldByName('CdProdut' ).AsInteger :=
                                                        lCtP002.FieldByName('CdProdut' ).AsInteger;
                          CcP002.FieldByName('DsProdut' ).AsString :=
                                                        lCtP002.FieldByName('DsProdut' ).AsString;
                          CcP002.FieldByName('CdFornec' ).AsInteger :=
                                                        lCtP002.FieldByName('CdFornec' ).AsInteger;
                          CcP002.FieldByName('RsTitula' ).AsString :=
                                                        lCtP002.FieldByName('RsTitula' ).AsString;
                          CcP002.FieldByName('VrBem'    ).AsFloat :=
                                                        lCtP002.FieldByName('VrBem'    ).AsFloat;
                          CcP002.FieldByName('VrBemDep' ).AsFloat :=
                                                        lCtP002.FieldByName('VrBemDep' ).AsFloat;
                          CcP002.FieldByName('VrDeprec' ).AsFloat :=
                                                        lCtP002.FieldByName('VrDeprec' ).AsFloat;
                          CcP002.FieldByName('QtParcela').AsInteger :=
                                                        lCtP002.FieldByName('QtParcela').AsInteger;
                          CcP002.FieldByName('aVrDeprec').AsFloat :=
                                                        lCtP002.FieldByName('VrBemDep' ).AsFloat -
                                                        lCtP002.FieldByName('VrDeprec' ).AsFloat;
                          CcP002.FieldByName('VrImpPis' ).AsFloat :=
                                                        lCtP002.FieldByName('VrImpPis' ).AsFloat;
                          CcP002.FieldByName('VrPisApr' ).AsFloat :=
                                                        lCtP002.FieldByName('VrPisApr' ).AsFloat;
                          CcP002.FieldByName('QtParPis' ).AsFloat :=
                                                        lCtP002.FieldByName('QtParPis' ).AsFloat;
                          CcP002.FieldByName('aVrPisApr').AsFloat :=
                                                        lCtP002.FieldByName('VrImpPis' ).AsFloat -
                                                        lCtP002.FieldByName('VrPisApr' ).AsFloat;
                          CcP002.FieldByName('VrImpCof' ).AsFloat :=
                                                        lCtP002.FieldByName('VrImpCof' ).AsFloat;
                          CcP002.FieldByName('VrCofApr' ).AsFloat :=
                                                        lCtP002.FieldByName('VrCofApr' ).AsFloat;
                          CcP002.FieldByName('QtParCof' ).AsFloat :=
                                                        lCtP002.FieldByName('QtParCof' ).AsFloat;
                          CcP002.FieldByName('aVrCofApr').AsFloat :=
                                                        lCtP002.FieldByName('VrImpCof' ).AsFloat -
                                                        lCtP002.FieldByName('VrCofApr' ).AsFloat;
                          CcP002.Post;

                          lCtP002.Next;
                    end;

                    CcP002.First;

                finally
                    CcP002.ReadOnly := True;
                    CcP002.EnableControls;
                end;

                PgcFundo.ActivePage := TbsConsulta;
          end else
          begin
                MessageDlg('Nenhum registro encontrado!!!', mtInformation, [mbok], 0);
          end;

      finally
          FreeAndNil(lCtP002);
      end;
end;

procedure TCon0015.BtnVoltarClick(Sender: TObject);
begin
      inherited;
      PgcFundo.ActivePage := TbsFiltro;
end;

procedure TCon0015.EdtDtAnoEntChange(Sender: TObject);
begin
      if ((Trim(EdtDtAnoEnt.Text) <> '0') and (Trim(EdtDtAnoEnt.Text) <> '')) then
      begin
            EdtDtBase.Text := '';
            EdtDtBase.Enabled := False;
            LblDtBase.Enabled := False;
      end else
      begin
            EdtDtBase.Enabled := True;
            LblDtBase.Enabled := True;
      end;
end;

procedure TCon0015.EdtDtBaseChange(Sender: TObject);
begin
      if ((Trim(EdtDtBase.Text) <> '/') and (Trim(EdtDtBase.Text) <> '')) then
      begin
            EdtDtAnoEnt.Text := '0';
            EdtDtAnoEnt.Enabled := False;
            LblDtAnoEnt.Enabled := False;
      end else
      begin
            EdtDtAnoEnt.Enabled := True;
            LblDtAnoEnt.Enabled := True;
      end;
end;

procedure TCon0015.FormCreate(Sender: TObject);
begin
      inherited;
      TbsFiltro.TabVisible := False;
      TbsConsulta.TabVisible := False;
      PgcFundo.ActivePage := TbsFiltro;

      CarregaEmpresas(CblCdEmpres, True);
end;

initialization

RegisterClass(TCon0015);

finalization

UnRegisterClass(TCon0015);

end.
