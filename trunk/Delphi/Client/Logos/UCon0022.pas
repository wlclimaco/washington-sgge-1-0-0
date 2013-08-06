unit UCon0022;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  StdCtrls, CheckLst, BrvCheckListBox, BrvDbGrids, BrvDbGrid, Grids, BrvRetCon, Mask,
  BrvCustomMaskEdit, BrvEditDate, BrvCustomEdit, BrvEditNum, BrvBitBtn, ComCtrls, BrvServerProgress,
  BrvExcel, DB, DBClient, BrvClientDataSet;

type
  TCon0022 = class(TMov0000)
    PgcFundo: TPageControl;
    TbsFiltro: TTabSheet;
    Panel1: TPanel;
    Panel4: TPanel;
    BtnPesquisar: TBrvBitBtn;
    PnlFiltros: TPanel;
    LblCdEmpres: TLabel;
    TbsConsulta: TTabSheet;
    Panel5: TPanel;
    Splitter1: TSplitter;
    Panel6: TPanel;
    LblQtReg: TLabel;
    BtnVoltar: TBrvBitBtn;
    BtnExcel: TBrvBitBtn;
    Panel8: TPanel;
    StgFiltros: TStringGrid;
    Panel3: TPanel;
    DbgLsRNC: TBrvDbGrid;
    CblCdEmpres: TBrvCheckListBox;
    BrvServerProgress: TBrvServerProgress;
    CpQ004: TBrvClientDataSet;
    DsQ004: TDataSource;
    BrvExcel: TBrvExcel;
    BrvListParam: TBrvListParam;
    PopDetRNC: TPopupMenu;
    Detalhar1: TMenuItem;
    Imprimir1: TMenuItem;
    Panel2: TPanel;
    LblCdUsuEmi: TLabel;
    EdtCdUsuEmi: TBrvEditNum;
    LblNmUsuEmi: TBrvRetCon;
    LblCdUsuDes: TLabel;
    EdtCdUsuDes: TBrvEditNum;
    LblNmUsuDes: TBrvRetCon;
    LblCdClaRnc: TLabel;
    EdtCdClaRnc: TBrvEditNum;
    LblDsContro: TBrvRetCon;
    LblDsClaRnc: TBrvRetCon;
    LblCdSetor: TLabel;
    EdtCdSetor: TBrvEditNum;
    LblDsSetor: TBrvRetCon;
    LblDtInicia: TLabel;
    EdtDtInicia: TBrvEditDate;
    LblDtFinal: TLabel;
    EdtDtFinal: TBrvEditDate;
    procedure FormCreate(Sender: TObject);
    procedure BtnPesquisarClick(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);
    procedure BtnExcelClick(Sender: TObject);
    procedure DbgLsRNCDblClick(Sender: TObject);
    procedure Detalhar1Click(Sender: TObject);
    procedure Imprimir1Click(Sender: TObject);
  private
    procedure DetalharRNC;
    procedure ValidaEntradaDados;
  public
    { Public declarations }
  end;

var
  Con0022: TCon0022;

implementation

uses UDmSrvApl, UDmTra, UBrvISO, BrvDominiosXE;

{$R *.dfm}

procedure TCon0022.BtnExcelClick(Sender: TObject);
var lColunas  : TStringList;
    lClientDS : TClientDataSet;
    lNrIndice : Integer;
begin
      inherited;
      lColunas  :=  TStringList.Create;
      try
          Screen.Cursor := crHourGlass;
          lClientDS := TClientDataSet.Create(Self);
          lClientDS.Data := CpQ004.Data;
          for lNrIndice := 0 to DbgLsRNC.Columns.Count - 1 do
          begin
                lColunas.Add(DbgLsRNC.Columns[lNrIndice].FieldName);
                lClientDS.FieldByName(DbgLsRNC.Columns[lNrIndice].FieldName).DisplayLabel :=
                                                          DbgLsRNC.Columns[lNrIndice].Title.Caption;
          end;
          lClientDS.First;
          BrvExcel.BrDataSet :=  lClientDS;
          BrvExcel.Execute(lColunas, '', nil);
      finally
          FreeAndNil(lColunas);
          Screen.Cursor := crDefault;
      end;
end;

procedure TCon0022.ValidaEntradaDados;
var lDsEmp : String;
begin
      BrvListParam.Clear;

      if (CblCdEmpres.BrCheckedCount = 0) then
      begin
            CblCdEmpres.SetFocus;
            raise Exception.Create('Selecione a(s) ' + LblCdEmpres.Caption + '!');
      end else
      begin
            lDsEmp := CblCdEmpres.BrCheckedList;
            BrvListParam.Add('CdEmpres', LblCdEmpres.Caption, lDsEmp, lDsEmp);
      end;

      if EdtCdUsuEmi.BrAsInteger > 0 then
      begin
            BrvListParam.Add('CdUsuEmi', LblCdUsuEmi.Caption, EdtCdUsuEmi.Text+' - ' +
                                      LblNmUsuEmi.Text, ' and Q004.CdUsuEmi = ' + EdtCdUsuEmi.Text);
      end else
      begin
            BrvListParam.Add('CdUsuEmi', '', '', '');
      end;

      if EdtCdUsuDes.BrAsInteger > 0 then
      begin
            BrvListParam.Add('CdUsuDes', LblCdUsuDes.Caption, EdtCdUsuDes.Text + ' - ' +
                                        LblNmUsuDes.Text, ' and Q004.CdUsuDes = '+EdtCdUsuDes.Text);
      end else
      begin
            BrvListParam.Add('CdUsuDes', '', '', '');
      end;

      if EdtCdClaRnc.BrAsInteger > 0 then
      begin
            BrvListParam.Add('CdClaRnc', LblCdClaRnc.Caption, EdtCdClaRnc.Text + ' - ' +
                                                              LblDsContro.Text + ' - ' +
                                                              LblDsClaRnc.Text,
                                                         'and Q004.CdClaRnc = ' + EdtCdClaRnc.Text);
      end else
      begin
            BrvListParam.Add('CdClaRnc', '', '', '');
      end;

      if EdtCdSetor.BrAsInteger > 0 then
      begin
            BrvListParam.Add('CdSetor', LblCdSetor.Caption, EdtCdSetor.Text+' - ' + LblDsSetor.Text,
                                                      ' and Q004.CdSetor = ' + EdtCdSetor.Text);
      end else
      begin
            BrvListParam.Add('CdSetor', '', '', '');
      end;

      if (EdtDtInicia.BrDataVazia) then
      begin
            EdtDtInicia.SetFocus;
            raise Exception.Create('Informe uma ' + LblDtInicia.Caption + ' para a consulta!');
      end else
      begin
            if (not EdtDtInicia.BrDataValida) then
            begin
                  EdtDtInicia.SetFocus;
                  raise Exception.Create('Verifique a ' + LblDtInicia.Caption + ' informada!');
            end else
            begin
                  if (EdtDtFinal.BrDataVazia) then
                  begin
                        EdtDtFinal.SetFocus;
                        raise Exception.Create('Informe uma ' + LblDtFinal.Caption +
                                                                               ' para a consulta!');
                  end else
                  begin
                        if (not EdtDtFinal.BrDataValida) then
                        begin
                              EdtDtFinal.SetFocus;
                              raise Exception.Create('Verifique a ' + LblDtFinal.Caption +
                                                                                     ' informada!');
                        end else
                        begin
                              if (EdtDtFinal.BrAsDate < EdtDtInicia.BrAsDate) then
                              begin
                                    EdtDtFinal.SetFocus;
                                    raise Exception.Create(LblDtFinal.Caption +
                                         ' deve ser maior ou igual à ' + LblDtInicia.Caption + '!');
                              end else
                              begin
                                    BrvListParam.Add('DtInicia', LblDtInicia.Caption,
                                                            EdtDtInicia.Text, EdtDtInicia.Text);

                                    BrvListParam.Add('DtFinal', LblDtFinal.Caption,
                                                              EdtDtFinal.Text, EdtDtFinal.Text);
                              end;
                        end;
                  end;
            end;
      end;
end;

procedure TCon0022.BtnPesquisarClick(Sender: TObject);
begin
      inherited;

      ValidaEntradaDados;

      try
          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
          CpQ004.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(262,
                                                                 BrvListParam.AsBrParam, Self.Name);
      finally
          BrvServerProgress.Stop;
      end;

      BrvListParam.SetStgParam(StgFiltros);
      LblQtReg.Caption := FormatFloat('0', CpQ004.RecordCount) + ' Registro(s)';
      if (CpQ004.RecordCount > 0) then
      begin
            CpQ004.FieldByName('NrRnc'   ).Alignment := taRightJustify;
            CpQ004.FieldByName('CdEmpres').Alignment := taRightJustify;
            CpQ004.FieldByName('CdClaRnc').Alignment := taRightJustify;
            CpQ004.FieldByName('DsContro').Alignment := taRightJustify;
            CpQ004.FieldByName('CdSetor' ).Alignment := taRightJustify;
            CpQ004.FieldByName('CdUsuDes').Alignment := taRightJustify;
            CpQ004.FieldByName('CdUsuEmi').Alignment := taRightJustify;
            CpQ004.FieldByName('VrCusto' ).Alignment := taRightJustify;
            TFloatField(CpQ004.FieldByName('VrCusto')).DisplayFormat := '##,###,##0.00';
            CpQ004.First;
            PgcFundo.ActivePage := TbsConsulta;
      end else
      begin
            MessageDlg('Nenhum registro encontrado!!!', mtInformation, [mbok], 0);
      end;
end;

procedure TCon0022.BtnVoltarClick(Sender: TObject);
begin
      inherited;
      PgcFundo.ActivePage := TbsFiltro;
end;

procedure TCon0022.DbgLsRNCDblClick(Sender: TObject);
begin
      inherited;
      DetalharRNC;
end;

procedure TCon0022.DetalharRNC;
begin
      inherited;
      if CpQ004.RecordCount > 0  then
      begin
            DmTra.VisualizarRNCDetalhada(CpQ004.FieldByName('NrRNC').AsString);
      end;
end;

procedure TCon0022.Detalhar1Click(Sender: TObject);
begin
      inherited;
      DetalharRNC;
end;

procedure TCon0022.FormCreate(Sender: TObject);
var lNrIdx : Integer;
begin
      inherited;
      TbsFiltro.TabVisible    := False;
      TbsConsulta.TabVisible  := False;
      PgcFundo.ActivePage     := TbsFiltro;

      CarregaEmpresas(CblCdEmpres, True);

      StgFiltros.ColWidths[0] := 160;
      StgFiltros.ColWidths[1] := 500;
end;

procedure TCon0022.Imprimir1Click(Sender: TObject);
begin
      inherited;
      UBrvISO.GerarRelatorioRNC(CpQ004.FieldByName('NrRNC').AsString, Self.Name);
end;

initialization
      RegisterClass(TCon0022);

finalization
      UnRegisterClass(TCon0022);

end.
