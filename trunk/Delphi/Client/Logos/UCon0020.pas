unit UCon0020;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  Mask, BrvCustomMaskEdit, BrvEditDate, StdCtrls, BrvBitBtn, BrvRetCon, BrvCustomEdit, BrvEditNum,
  Grids, BrvDbGrids, BrvDbGrid, DB, DBClient, BrvClientDataSet, BrvServerProgress, BrvExcel,
  ComCtrls, mxExport;

type
  TCon0020 = class(TMov0000)
    DbgLsAteCli: TBrvDbGrid;
    BrvExcel: TBrvExcel;
    BrvListParam: TBrvListParam;
    BrvServerProgress: TBrvServerProgress;
    CpT010: TBrvClientDataSet;
    DsT010: TDataSource;
    PgcFundo: TPageControl;
    TbsFiltro: TTabSheet;
    Panel1: TPanel;
    Panel4: TPanel;
    BtnPesquisar: TBrvBitBtn;
    TbsConsulta: TTabSheet;
    Panel5: TPanel;
    Splitter1: TSplitter;
    Panel6: TPanel;
    Panel8: TPanel;
    Panel3: TPanel;
    PnlFiltros: TPanel;
    LblEmpres: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    Label4: TLabel;
    Label1: TLabel;
    EdtCdUsuari: TBrvEditNum;
    EdtCdTomado: TBrvEditNum;
    EdtDtFinal: TBrvEditDate;
    EdtDtInicia: TBrvEditDate;
    EdtCdEmpres: TBrvEditNum;
    LblDsEmpres: TBrvRetCon;
    LblRsTomado: TBrvRetCon;
    LblNmComUsu: TBrvRetCon;
    BtnVoltar: TBrvBitBtn;
    BtnExcel: TBrvBitBtn;
    StgFiltros: TStringGrid;
    LblQtReg: TLabel;
    mxExcel: TmxDataSetExport;
    procedure BtnPesquisarClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);
    procedure BtnExcelClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Con0020: TCon0020;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TCon0020.BtnExcelClick(Sender: TObject);
var lColunas  : TStringList;
    lClientDS : TClientDataSet;
    lNrIndice : Integer;
begin
      mxExcel.Captions.Clear;
      for lNrIndice := 0 to DbgLsAteCli.Columns.Count-1 do
      begin
            mxExcel.Captions.Add(DbgLsAteCli.Columns[lNrIndice].Title.Caption);
      end;
      mxExcel.Select;

//      inherited;
//      lColunas  :=  TStringList.Create;
//      try
//          Screen.Cursor := crHourGlass;
//          lClientDS := TClientDataSet.Create(Self);
//          lClientDS.Data := CpT010.Data;
//          for lNrIndice := 0 to DbgLsAteCli.Columns.Count - 1 do
//          begin
//                lColunas.Add(DbgLsAteCli.Columns[lNrIndice].FieldName);
//                lClientDS.FieldByName(DbgLsAteCli.Columns[lNrIndice].FieldName).DisplayLabel :=
//                                                       DbgLsAteCli.Columns[lNrIndice].Title.Caption;
//          end;
//          lClientDS.First;
//          BrvExcel.BrDataSet :=  lClientDS;
//          BrvExcel.Execute(lColunas, '', nil);
//      finally
//          FreeAndNil(lColunas);
//          Screen.Cursor := crDefault;
//      end;
end;

procedure TCon0020.BtnPesquisarClick(Sender: TObject);
begin
      inherited;

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

      if EdtCdTomado.BrAsInteger > 0 then
      begin
            BrvListParam.Add('CdTomado', 'Tomador', EdtCdTomado.Text+' - '+LblRsTomado.Text,
                                                      ' and T002.CdTomado = '+EdtCdTomado.Text);
      end else
      begin
            BrvListParam.Add('CdTomado', '', '', '');
      end;

      if EdtCdUsuari.BrAsInteger > 0 then
      begin
            BrvListParam.Add('CdUsuari', 'Atendente', EdtCdUsuari.Text+' - '+LblNmComUsu.Text,
                                                      ' and T010.CdUsuari = '+EdtCdUsuari.Text);
      end else
      begin
            BrvListParam.Add('CdUsuari', '', '', '');
      end;

      if (EdtDtInicia.BrDataVazia) then
      begin
            EdtDtInicia.SetFocus;
            raise Exception.Create('Informe uma data inicial para a consulta!');
      end else
      begin
            if (not EdtDtInicia.BrDataValida) then
            begin
                  EdtDtInicia.SetFocus;
                  raise Exception.Create('Verifique a data inicial informada!');
            end else
            begin
                  if (EdtDtFinal.BrDataVazia) then
                  begin
                        EdtDtFinal.SetFocus;
                        raise Exception.Create('Informe uma data final para a consulta!');
                  end else
                  begin
                        if (not EdtDtFinal.BrDataValida) then
                        begin
                              EdtDtFinal.SetFocus;
                              raise Exception.Create('Verifique a data final informada!');
                        end else
                        begin
                              if (EdtDtFinal.BrAsDate < EdtDtInicia.BrAsDate) then
                              begin
                                    EdtDtFinal.SetFocus;
                                    raise Exception.Create('Data final deve ser maior ou igual'+
                                                                            ' à data inicial!');
                              end else
                              begin
                                    BrvListParam.Add('DtInicia', 'Data Inicial',
                                                     EdtDtInicia.Text, EdtDtInicia.BrAsDataSQL);

                                    BrvListParam.Add('DtFinal', 'Data Final',
                                                     EdtDtFinal.Text, EdtDtFinal.BrAsDataSQL);
                              end;
                        end;
                  end;
            end;
      end;

      try
          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
          CpT010.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(259,
                                                                 BrvListParam.AsBrParam, Self.Name);
      finally
          BrvServerProgress.Stop;
      end;

      BrvListParam.SetStgParam(StgFiltros);
      if (CpT010.RecordCount > 0) then
      begin
            LblQtReg.Caption := FormatFloat('0', CpT010.RecordCount) + ' Registro(s)';

            CpT010.FieldByName('CdTipAte').Alignment := taRightJustify;
            CpT010.FieldByName('CdUsuari').Alignment := taRightJustify;
            CpT010.FieldByName('CdEmpres').Alignment := taRightJustify;
            CpT010.FieldByName('CdCTRC'  ).Alignment := taRightJustify;
            CpT010.FieldByName('CdRemete').Alignment := taRightJustify;
            CpT010.FieldByName('CdDestin').Alignment := taRightJustify;
            CpT010.FieldByName('NrNota'  ).Alignment := taRightJustify;
            CpT010.First;
            PgcFundo.ActivePage := TbsConsulta;
      end else
      begin
            MessageDlg('Nenhum registro encontrado!!!', mtInformation, [mbok], 0);
      end;
end;

procedure TCon0020.BtnVoltarClick(Sender: TObject);
begin
      inherited;
      PgcFundo.ActivePage := TbsFiltro;
end;

procedure TCon0020.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TCon0020.FormCreate(Sender: TObject);
begin
      inherited;
      TbsFiltro.TabVisible    := False;
      TbsConsulta.TabVisible  := False;
      PgcFundo.ActivePage     := TbsFiltro;
      StgFiltros.ColWidths[0] := 160;
      StgFiltros.ColWidths[1] := 500;
end;

initialization
      RegisterClass(TCon0020);

finalization
      UnRegisterClass(TCon0020);

end.
