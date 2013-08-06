unit UCon0021;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvImport, BrvExcel, DB, DBClient, Grids, StdCtrls, Mask, BrvCustomMaskEdit,
  BrvEditDate, CheckLst, BrvCheckListBox, Buttons, BrvBitBtn, ComCtrls, ExtCtrls, BrvDbGrids,
  BrvDbGrid, BrvListParam, ImgList, Menus, BrvSpeedButton, BrvDbNavCop,UClaSrv, BrvServerProgress;


type
  TCON0021 = class(TMov0000)
    ImageList1: TImageList;
    PgcFundo: TPageControl;
    TbsFiltro: TTabSheet;
    Panel4: TPanel;
    Panel3: TPanel;
    BtnPesquisa: TBrvBitBtn;
    TbsConsulta: TTabSheet;
    Panel5: TPanel;
    Splitter2: TSplitter;
    Panel6: TPanel;
    LblQtReg: TLabel;
    BtnExcel: TBrvBitBtn;
    BtnVoltar: TBrvBitBtn;
    Panel7: TPanel;
    StgFiltros: TStringGrid;
    BrvDbGrid3: TBrvDbGrid;
    ImageList2: TImageList;
    CcP002: TClientDataSet;
    DtsP002: TDataSource;
    BrvExcel: TBrvExcel;
    BrvListParam: TBrvListParam;
    BrvImport1: TBrvImport;
    BrvServerProgress: TBrvServerProgress;
    Panel1: TPanel;
    Panel8: TPanel;
    LblDtIni: TLabel;
    LblDtFin: TLabel;
    EdtDtIni: TBrvEditDate;
    EdtDtFin: TBrvEditDate;
    CblCdEmpres: TBrvCheckListBox;
    LblCdEmpres: TLabel;
    procedure FormCreate(Sender: TObject);
    procedure BtnPesquisaClick(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);
    procedure BtnExcelClick(Sender: TObject);
  private
    procedure ValidaEntradaDados;
  public
    { Public declarations }
  end;

var
  CON0021: TCON0021;

implementation
uses UDmSrvApl, BrvDominiosXE;
{$R *.dfm}

procedure TCON0021.BtnExcelClick(Sender: TObject);
var lColunas  : TStringList;
    lClientDS : TClientDataSet;
    lNrIndice : Integer;
begin
      lColunas  :=  TStringList.Create;
      for lNrIndice := 0 to BrvDbGrid3.Columns.Count - 1 do
      begin
            lColunas.Add(BrvDbGrid3.Columns[lNrIndice].FieldName);
      end;
      try
          Screen.Cursor      := crHourGlass;
          lClientDS          := TClientDataSet.Create(Self);
          lClientDS.Data     := CcP002.Data;
          lClientDS.First;
          BrvExcel.BrDataSet :=  lClientDS;
          BrvExcel.Execute(lColunas, '', nil);
      finally
          FreeAndNil(lColunas);
          Screen.Cursor := crDefault;
      end;

end;

procedure TCon0021.ValidaEntradaDados;
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

      if (EdtDtIni.BrDataVazia) then
      begin
            EdtDtIni.SetFocus;
            raise Exception.Create('Verifique a ' + LblDtIni.Caption + ' informada!');
      end else
      begin
            BrvListParam.Add('DtIniLan', LblDtIni.Caption, EdtDtIni.Text, EdtDtIni.Text);
      end;

      if (EdtDtFin.BrDataVazia) then
      begin
            EdtDtFin.SetFocus;
            raise Exception.Create('Verifique a ' + LblDtFin.Caption + ' informada!');
      end else
      begin
            BrvListParam.Add('DtFinLan', LblDtFin.Caption, EdtDtFin.Text, EdtDtFin.Text);
      end;
end;

procedure TCON0021.BtnPesquisaClick(Sender: TObject);
Var lCcP002Aux : TClientDataSet;
    lCcP003Aux : TClientDataSet;
    lCdTomado  : Integer;
begin
      try
          lCcP002Aux := TClientDataSet.Create(Self);
          lCcP003Aux := TClientDataSet.Create(Self);

          ValidaEntradaDados;

          try
              BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
              lCcP002Aux.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(261,
                                                            BrvListParam.AsBrParam, Self.Name);
          finally
              BrvServerProgress.Stop;
          end;

          lCcP003Aux.Fields.Clear;
          lCcP003Aux.FieldDefs.Add('CdTomado',ftString, 10);
          lCcP003Aux.FieldDefs.Add('RsTomado',ftString, 150);
          lCcP003Aux.FieldDefs.Add('QtXml',ftInteger);
          lCcP003Aux.FieldDefs.Add('QtSXml',ftInteger);
          lCcP003Aux.FieldDefs.Add('%Xml',ftFloat);
          lCcP003Aux.FieldDefs.Add('QtNotFis',ftInteger);
          lCcP003Aux.FieldDefs.Add('QtSNotFis',ftInteger);
          lCcP003Aux.FieldDefs.Add('%NotFis',ftFloat);
          lCcP003Aux.CreateDataSet;

          lCdTomado := 0;

          lCcP002Aux.First;

          while not(lCcP002Aux.eof) do
          begin
                if lCcP002Aux.FieldByName('CdTomado').AsInteger <> lCdTomado  then
                begin
                      lCcP003Aux.Append;

                      lCcP003Aux.FieldByName('CdTomado').AsString      :=
                                                        lCcP002Aux.FieldByName('CdTomado').AsString;
                      lCcP003Aux.FieldByName('RsTomado').AsString      :=
                                                        lCcP002Aux.FieldByName('RsTomado').AsString;

                      lCcP003Aux.FieldByName('%Xml').AsFloat           := 0;
                      lCcP003Aux.FieldByName('%NotFis').AsFloat        := 0;
                      lCcP003Aux.FieldByName('QtSXml').AsInteger       := 0;
                      lCcP003Aux.FieldByName('QtXml').AsInteger        := 0;
                      lCcP003Aux.FieldByName('QtSNotFis').AsInteger    := 0;
                      lCcP003Aux.FieldByName('QtNotFis').AsInteger     := 0;
                end else
                begin
                      lCcP003Aux.Edit;
                end;

                if lCcP002Aux.FieldByName('SnXml').AsString = 'N' then
                begin
                      lCcP003Aux.FieldByName('QtSXml').AsInteger :=
                                              lCcP003Aux.FieldByName('QtSXml').AsInteger +
                                              lCcP002Aux.FieldByName('NrSnXML').AsInteger;
                end else
                begin
                      lCcP003Aux.FieldByName('QtXml').AsInteger :=
                                              lCcP003Aux.FieldByName('QtXml').AsInteger +
                                              lCcP002Aux.FieldByName('NrSnXML').AsInteger;
                end;

                if lCcP002Aux.FieldByName('SnNotFis').AsString = 'N' then
                begin
                      lCcP003Aux.FieldByName('QtSNotFis').AsInteger :=
                                                lCcP003Aux.FieldByName('QtSNotFis').AsInteger +
                                                lCcP002Aux.FieldByName('NrSnNotFis').AsInteger;
                end else
                begin
                      lCcP003Aux.FieldByName('QtNotFis').AsInteger :=
                                                lCcP003Aux.FieldByName('QtNotFis').AsInteger +
                                                lCcP002Aux.FieldByName('NrSnNotFis').AsInteger;
                end;

                lCdTomado := lCcP002Aux.FieldByName('CdTomado').AsInteger;

                lCcP002Aux.Next;

                if (lCcP002Aux.FieldByName('CdTomado').AsInteger <>
                    lCcP003Aux.FieldByName('CdTomado').AsInteger) then
                begin
                      lCcP003Aux.FieldByName('%Xml').AsString :=
                            FormatFloat('0.00',
                                 (lCcP003Aux.FieldByName('QtXml').AsInteger * 100) /

                                  (lCcP003Aux.FieldByName('QtXml').AsInteger +
                                   lCcP003Aux.FieldByName('QtSXml').AsInteger));

                      lCcP003Aux.FieldByName('%NotFis').AsString :=
                            FormatFloat('0.00',
                                 (lCcP003Aux.FieldByName('QtNotFis').AsInteger * 100) /

                                  (lCcP003Aux.FieldByName('QtNotFis').AsInteger +
                                   lCcP003Aux.FieldByName('QtSNotFis').AsInteger));
                end;
          end;

          LblQtReg.Caption := FormatFloat('0', lCcP003Aux.RecordCount) + ' Registro(s)';
          BrvListParam.SetStgParam(StgFiltros);
          if (lCcP003Aux.RecordCount > 0) then
          begin
                PgcFundo.ActivePage := TbsConsulta;
                CcP002.Data :=  lCcP003Aux.Data;
          end else
          begin
                MessageDlg('Nenhum registro encontrado!!!', mtInformation, [mbok], 0);
          end;

      finally
          FreeAndNil(lCcP002Aux);
          FreeAndNil(lCcP003Aux);
      end;
end;

procedure TCON0021.BtnVoltarClick(Sender: TObject);
begin
      inherited;
      PgcFundo.ActivePage := TbsFiltro;
end;

procedure TCON0021.FormCreate(Sender: TObject);
var lRrIdx : Integer;
begin
      inherited;
      TbsFiltro.TabVisible := False;
      TbsConsulta.TabVisible := False;
      PgcFundo.ActivePage := TbsFiltro;

      CarregaEmpresas(CblCdEmpres, True);

      EdtDtIni.Text := FormatDateTime('DD/mm/yyyy', Now());
      EdtDtFin.Text := FormatDateTime('DD/mm/yyyy', Now());
end;

initialization
      RegisterClass(TCon0021);

finalization
      UnRegisterClass(TCon0021);

end.
