unit UCon0018;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  StdCtrls, BrvRetCon, Mask, BrvCustomMaskEdit, BrvEditDate, BrvCustomEdit, BrvEditNum, BrvBitBtn,
  BrvDbGrids, BrvDbGrid, Grids, ComCtrls, BrvServerProgress, DB, DBClient, BrvClientDataSet,
  BrvExcel;

type
  TCon0018 = class(TMov0000)
    Panel7: TPanel;
    Label1: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    EdtDtFinal: TBrvEditDate;
    EdtDtInicia: TBrvEditDate;
    LblDsEmpres: TBrvRetCon;
    RdgTpMovime: TRadioGroup;
    Label2: TLabel;
    EdtCdMotori: TBrvEditNum;
    LblNmMotori: TBrvRetCon;
    Label3: TLabel;
    Panel1: TPanel;
    PgcFundo: TPageControl;
    TbsFiltro: TTabSheet;
    TbsConsulta: TTabSheet;
    Panel5: TPanel;
    Splitter2: TSplitter;
    Panel6: TPanel;
    BtnExcel: TBrvBitBtn;
    BtnVoltar: TBrvBitBtn;
    Panel8: TPanel;
    StgFiltros: TStringGrid;
    DbgRegistro: TBrvDbGrid;
    Panel2: TPanel;
    DbgDetalhe: TBrvDbGrid;
    Panel4: TPanel;
    BtnPesquisar: TBrvBitBtn;
    Panel3: TPanel;
    EdtCdEmpres: TBrvEditNum;
    BrvListParam: TBrvListParam;
    LblVrLancam: TBrvRetCon;
    LblVrDebito: TBrvRetCon;
    LblVrCredit: TBrvRetCon;
    LblVrSaldo: TBrvRetCon;
    Label4: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    Label9: TLabel;
    BrvServerProgress: TBrvServerProgress;
    DsT009: TDataSource;
    CpT009: TBrvClientDataSet;
    Panel9: TPanel;
    Label10: TLabel;
    DsT019: TDataSource;
    CpT019: TBrvClientDataSet;
    BrvExcel: TBrvExcel;
    Splitter1: TSplitter;
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure CpT009AfterScroll(DataSet: TDataSet);
    procedure FormCreate(Sender: TObject);
    procedure BtnPesquisarClick(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);
    procedure CpT009BeforeInsert(DataSet: TDataSet);
    procedure BtnExcelClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Con0018: TCon0018;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TCon0018.BtnExcelClick(Sender: TObject);
var lColunas  : TStringList;
    lClientDS : TClientDataSet;
    lNrIndice : Integer;
begin
      inherited;
      lColunas  :=  TStringList.Create;
      try
          Screen.Cursor := crHourGlass;
          lClientDS := TClientDataSet.Create(Self);
          lClientDS.Data := CpT009.Data;
          for lNrIndice := 0 to DbgRegistro.Columns.Count - 1 do
          begin
                lColunas.Add(DbgRegistro.Columns[lNrIndice].FieldName);
                lClientDS.FieldByName(DbgRegistro.Columns[lNrIndice].FieldName).DisplayLabel :=
                                                       DbgRegistro.Columns[lNrIndice].Title.Caption;
          end;
          lClientDS.First;
          BrvExcel.BrDataSet :=  lClientDS;
          BrvExcel.Execute(lColunas, '', nil);
      finally
          FreeAndNil(lColunas);
          Screen.Cursor := crDefault;
      end;
end;

procedure TCon0018.BtnPesquisarClick(Sender: TObject);
begin
      inherited;

      LblVrLancam.Text := '0';
      LblVrDebito.Text := '0';
      LblVrCredit.Text := '0';
      LblVrSaldo.Text  := '0';
      BrvListParam.Clear;

      if EdtCdEmpres.BrAsInteger > 0 then
      begin
            BrvListParam.Add('CdEmpres', 'Empresa', EdtCdEmpres.Text + ' - ' + LblDsEmpres.Text,
                                                                              EdtCdEmpres.Text);
      end else
      begin
            BrvListParam.Add('CdEmpres', 'Empresa(s)', EdtCdEmpres.BrDicionario.CorpCommaCodes,
                                                       EdtCdEmpres.BrDicionario.CorpCommaCodes);
      end;

      if EdtCdMotori.BrAsInteger > 0 then
      begin
            BrvListParam.Add('CdMotori', 'Motorista', EdtCdMotori.Text+' - '+LblNmMotori.Text,
                                                      ' and T019.CdMotori = '+EdtCdMotori.Text);
      end else
      begin
            BrvListParam.Add('CdMotori', '', '', '');
      end;

      if (not EdtDtInicia.BrDataVazia) and (not EdtDtFinal.BrDataVazia)
                               and (EdtDtInicia.BrDataValida) and (EdtDtFinal.BrDataValida) then
      begin
            if EdtDtFinal.BrAsDate < EdtDtInicia.BrAsDate then
            begin
                  raise Exception.Create('Data final deve ser maior ou igual a inicial');
            end;
      end;

      if not EdtDtInicia.BrDataVazia then
      begin
            if (not EdtDtInicia.BrDataVazia) and (not EdtDtInicia.BrDataValida) then
            begin
                  raise Exception.Create('Data inicial está inválida');
            end else
            begin
                  BrvListParam.Add('DtInicia', 'Data Inicial', EdtDtInicia.Text,
                                  ' and T019.DtLancto >= <$hh"'+EdtDtInicia.BrAsDataSQL+'"hh$>');
            end;
      end else
      begin
            BrvListParam.Add('DtInicia', '', '', '');
      end;

      if not EdtDtFinal.BrDataVazia then
      begin
            if (not EdtDtFinal.BrDataVazia) and (not EdtDtFinal.BrDataValida) then
            begin
                  raise Exception.Create('Data final está inválida');
            end else
            begin
                  BrvListParam.Add('DtFinal', 'Data Final', EdtDtFinal.Text,
                                   ' and T019.DtLancto <= <$hh"'+EdtDtFinal.BrAsDataSQL+'"hh$>');
            end;
      end else
      begin
            BrvListParam.Add('DtFinal', '', '', '');
      end;

      if ((EdtCdEmpres.BrAsInteger = 0) and (EdtCdMotori.BrAsInteger = 0) and
          (EdtDtInicia.BrDataVazia) and (EdtDtFinal.BrDataVazia)) then
      begin
            raise Exception.Create('Informe pelo menos um filtro de pesquisa.');
      end;

      case RdgTpMovime.ItemIndex of
          0: BrvListParam.Add('StConCor', 'Movimentos', RdgTpMovime.Items[0],
                                                                    ' and T019.StConCor = "P"');
          1: BrvListParam.Add('StConCor', 'Movimentos', RdgTpMovime.Items[1],
                                                                    ' and T019.StConCor = "A"');
          2: BrvListParam.Add('StConCor', 'Movimentos', RdgTpMovime.Items[2], '');
      end;

      try
          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
          CpT009.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(227,
                                                                 BrvListParam.AsBrParam, Self.Name);
          CpT019.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(228,
                                                                 BrvListParam.AsBrParam, Self.Name);
      finally
          BrvServerProgress.Stop;
      end;

      BrvListParam.SetStgParam(StgFiltros);
      LblVrLancam.Text := FormatFloat('0', CpT009.RecordCount);
      if (CpT009.RecordCount > 0) then
      begin
            CpT009.ReadOnly := False;

            while not CpT009.Eof do
            begin
                  CpT009.Edit;

                  if (CpT009.FieldByName('VrTotDeb').Text = '') then
                  begin
                        CpT009.FieldByName('VrTotDeb').Text := '0';
                  end;
                  if (CpT009.FieldByName('VrTotCre').Text = '') then
                  begin
                        CpT009.FieldByName('VrTotCre').Text := '0';
                  end;

                  CpT009.FieldByName('VrSaldo').AsFloat := CpT009.FieldByName('VrTotDeb').AsFloat -
                                                           CpT009.FieldByName('VrTotCre').AsFloat;
                  LblVrDebito.Text := FloatToStr(StrToFloat(LblVrDebito.Text) + StrToFloat(
                                                              CpT009.FieldByName('VrTotDeb').Text));
                  LblVrCredit.Text := FloatToStr(StrToFloat(LblVrCredit.Text) + StrToFloat(
                                                              CpT009.FieldByName('VrTotCre').Text));
                  LblVrSaldo.Text  := FloatToStr(StrToFloat(LblVrDebito.Text) - StrToFloat(
                                                                                 LblVrCredit.Text));
                  CpT009.Next;
            end;

            LblVrDebito.Text  := FormatFloat('##,###,##0.00', StrToFloat(LblVrDebito.Text));
            LblVrCredit.Text  := FormatFloat('##,###,##0.00', StrToFloat(LblVrCredit.Text));
            LblVrSaldo.Text   := FormatFloat('##,###,##0.00', StrToFloat(LblVrSaldo.Text));

            TFloatField(CpT009.FieldByName('VrTotDeb')).DisplayFormat := '##,###,##0.00';
            TFloatField(CpT009.FieldByName('VrTotCre')).DisplayFormat := '##,###,##0.00';
            TFloatField(CpT009.FieldByName('VrSaldo' )).DisplayFormat := '##,###,##0.00';

            TFloatField(CpT019.FieldByName('CdCarga' )).DisplayFormat := '0';
            TFloatField(CpT019.FieldByName('VrLancto')).DisplayFormat := '##,###,##0.00';

            CpT009.FieldByName('CdMotori').Alignment := taRightJustify;
            CpT009.FieldByName('VrTotDeb').Alignment := taRightJustify;
            CpT009.FieldByName('VrTotCre').Alignment := taRightJustify;
            CpT009.FieldByName('VrSaldo' ).Alignment := taRightJustify;

            CpT019.FieldByName('CdHistor').Alignment := taRightJustify;
            CpT019.FieldByName('CdCarga' ).Alignment := taRightJustify;
            CpT019.FieldByName('TpHistor').Alignment := taRightJustify;
            CpT019.FieldByName('VrLancto').Alignment := taRightJustify;

            CpT009.ReadOnly := True;
            CpT009.First;
      end;

      PgcFundo.ActivePage := TbsConsulta;
end;

procedure TCon0018.CpT009AfterScroll(DataSet: TDataSet);
begin
      CpT019.Filtered := False;
      CpT019.Filter   := ' CdMotori = ' + CpT009.FieldByName('CdMotori').AsString;
      CpT019.Filtered := True;
end;

procedure TCon0018.CpT009BeforeInsert(DataSet: TDataSet);
begin
      inherited;
      Abort;
end;

procedure TCon0018.BtnVoltarClick(Sender: TObject);
begin
      inherited;
      PgcFundo.ActivePage := TbsFiltro;
end;

procedure TCon0018.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TCon0018.FormCreate(Sender: TObject);
begin
      inherited;
      TbsFiltro.TabVisible   := False;
      TbsConsulta.TabVisible := False;
      PgcFundo.ActivePage    := TbsFiltro;
end;

initialization
      RegisterClass(TCon0018);

finalization
      UnRegisterClass(TCon0018);

end.
