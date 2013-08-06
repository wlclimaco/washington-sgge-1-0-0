unit UCon0019;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  BrvRetCon, Mask, BrvCustomMaskEdit, BrvEditDate, StdCtrls, BrvCustomEdit, BrvEditNum, BrvBitBtn,
  Spin, BrvDbGrids, BrvDbGrid, Grids, ComCtrls, BrvServerProgress, DB, DBClient, BrvClientDataSet,
  BrvEdit, BrvExcel, CheckLst, BrvCheckListBox;

type
  TCon0019 = class(TMov0000)
    Panel1: TPanel;
    BtnPesquisa: TBrvBitBtn;
    Panel7: TPanel;
    LblCdEmpres: TLabel;
    PgcFundo: TPageControl;
    TbsFiltro: TTabSheet;
    TbsConsulta: TTabSheet;
    Panel5: TPanel;
    Splitter2: TSplitter;
    Panel6: TPanel;
    LblQtReg: TLabel;
    BtnExcel: TBrvBitBtn;
    BtnVoltar: TBrvBitBtn;
    Panel8: TPanel;
    StgFiltros: TStringGrid;
    DbgFiltro: TBrvDbGrid;
    Panel4: TPanel;
    BrvListParam: TBrvListParam;
    BrvServerProgress: TBrvServerProgress;
    CpEstRnc: TBrvClientDataSet;
    DsEstRnc: TDataSource;
    CpQ001: TBrvClientDataSet;
    DsQ001: TDataSource;
    CbxSnClaZer: TCheckBox;
    BrvExcel: TBrvExcel;
    CblCdEmpres: TBrvCheckListBox;
    Panel3: TPanel;
    LblCdSetor: TLabel;
    EdtCdSetor: TBrvEditNum;
    LblDsSetor: TBrvRetCon;
    LblCdClaRnc: TLabel;
    EdtCdClaRnc: TBrvEditNum;
    LblDsContro: TBrvRetCon;
    LblDsClaRnc: TBrvRetCon;
    LblNrNivel: TLabel;
    EdtNrNivel: TSpinEdit;
    LblDtAnoRnc: TLabel;
    EdtDtAnoRnc: TBrvEditNum;
    procedure BtnPesquisaClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);
    procedure CbxSnClaZerClick(Sender: TObject);
    procedure BtnExcelClick(Sender: TObject);
  private
    procedure BuscarRCN(pCdClaZer: Integer; pCdClaRnc: Integer; pCdClaPai: Integer;
                        pDsContro: String;  pDsClaRnc: String;  pNrNivel : Integer);
    procedure ValidaEntradaDados;
  public
    { Public declarations }
  end;

var
  Con0019   : TCon0019;
  gNrSequen : Integer;

const
  gNmLisMes : Array[1..12]
              Of String = ('Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez');

implementation

uses UDmSrvApl, BrvDominiosXE;

{$R *.dfm}

procedure TCon0019.BtnExcelClick(Sender: TObject);
var lColunas  : TStringList;
    lClientDS : TClientDataSet;
    lNrIndice : Integer;
begin
      inherited;
      lColunas  :=  TStringList.Create;
      try
          Screen.Cursor  := crHourGlass;
          lClientDS      := TClientDataSet.Create(Self);
          lClientDS.Data := CpEstRnc.Data;

          for lNrIndice := 0 to DbgFiltro.Columns.Count - 1 do
          begin
                lColunas.Add(DbgFiltro.Columns[lNrIndice].FieldName);
                lClientDS.FieldByName(DbgFiltro.Columns[lNrIndice].FieldName).DisplayLabel :=
                                                         DbgFiltro.Columns[lNrIndice].Title.Caption;
          end;

          lClientDS.IndexDefs.Clear;
          lClientDS.IndexDefs.Add('IdxSequen', 'NrSequen', [ixUnique]);
          lClientDS.IndexName := 'IdxSequen';

          if (CbxSnClaZer.Checked) then
          begin
                lClientDS.Filtered := False;
                lClientDS.Filter   := 'SnVisive = ''S''';
                lClientDS.Filtered := True;
          end else
          begin
                lClientDS.Filtered := False;
                lClientDS.Filter   := 'SnClaZer = ''N'' and SnVisive = ''S''';
                lClientDS.Filtered := True;
          end;

          lClientDS.First;
          while not lClientDS.Eof do
          begin
                lClientDS.Edit;
                lClientDS.FieldByName('DsContro').AsString :=
                                                   #39 + lClientDS.FieldByName('DsContro').AsString;
                lClientDS.Post;
                lClientDS.Next;
          end;
          lClientDS.First;
          BrvExcel.BrDataSet := lClientDS;
          BrvExcel.Execute(lColunas, '', nil);
      finally
          FreeAndNil(lColunas);
          Screen.Cursor := crDefault;
      end;
end;

procedure TCon0019.ValidaEntradaDados;
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
            BrvListParam.Add('CdEmpres', LblCdEmpres.Caption, lDsEmp,
                                                          ' and Q004.CdEmpres in (' + lDsEmp + ')');
      end;

      if (StrToIntDef(EdtDtAnoRnc.Text, 0) > 0)  then
      begin
            BrvListParam.Add('DtIniRnc', LblDtAnoRnc.Caption, EdtDtAnoRnc.Text,
                               'and Q004.DtEmiRnc >= <$hh"01/01/' + EdtDtAnoRnc.Text + '"hh$>');
            BrvListParam.Add('DtFimRnc', '', EdtDtAnoRnc.Text,
                               'and Q004.DtEmiRnc <= <$hh"31/12/' + EdtDtAnoRnc.Text + '"hh$>');
      end else
      begin
            EdtDtAnoRnc.SetFocus;
            raise Exception.Create('Informe o ' + LblDtAnoRnc.Caption + ' para a consulta!');
      end;

      if (StrToIntDef(EdtCdSetor.Text, 0) > 0)  then
      begin
            BrvListParam.Add('CdSetor', LblCdSetor.Caption, EdtCdSetor.Text +' - '+ LblDsSetor.Text,
                                                           'and Q004.CdSetor = ' + EdtCdSetor.Text);
      end else
      begin
            BrvListParam.Add('CdSetor', '', '', '');
      end;

      if (StrToIntDef(EdtCdClaRnc.Text, 0) > 0)  then
      begin
            BrvListParam.Add('CdClaRnc', LblCdClaRnc.Caption, EdtCdClaRnc.Text + ' - ' +
                                                              LblDsContro.Text + ' - ' +
                                                              LblDsClaRnc.Text,
                                                         'and Q001.CdClaRnc = ' + EdtCdClaRnc.Text);
      end else
      begin
            BrvListParam.Add('CdClaRnc', '', '', '');
      end;

      if (StrToInt(EdtNrNivel.Text) < 0) then
      begin
            EdtNrNivel.SetFocus;
            raise Exception.Create('Informe um nível maior ou igual a zero!');
      end else
      begin
            if (StrToInt(EdtNrNivel.Text) > 0) then
            begin
                  BrvListParam.Add('', LblNrNivel.Caption, EdtNrNivel.Text, '');
            end else
            begin
                  BrvListParam.Add('', LblNrNivel.Caption, EdtNrNivel.Text + ' (Todos)', '');
            end;
      end;
end;

procedure TCon0019.BtnPesquisaClick(Sender: TObject);
var lVrCount  : Integer;
    lCpEstRnc : TClientDataSet;
    lNmMesRnc : String;
    lQtRncMes : Integer;
    lQtRncAux : Integer;
begin
      inherited;

      CbxSnClaZer.Checked := True;

      if (CpEstRnc.Active) then
      begin
            CpEstRnc.EmptyDataSet;
            CpEstRnc.Close;
      end;
      CpEstRnc.FieldDefs.Clear;
      CpEstRnc.FieldDefs.Add('CdClaRnc', ftInteger, 0);
      CpEstRnc.FieldDefs.Add('CdClaPai', ftInteger, 0);
      CpEstRnc.FieldDefs.Add('DsContro', ftString, 50);
      CpEstRnc.FieldDefs.Add('DsClaRnc', ftString, 50);

      for lVrCount := 1 to 12 do
      begin
            CpEstRnc.FieldDefs.Add('Qt'+gNmLisMes[lVrCount]+'Rnc', ftInteger, 0);
            CpEstRnc.FieldDefs.Add('Pc'+gNmLisMes[lVrCount]+'Rnc', ftFloat,   0);
      end;

      CpEstRnc.FieldDefs.Add('SnVisive', ftString,  1);
      CpEstRnc.FieldDefs.Add('SnClaZer', ftString,  1);
      CpEstRnc.FieldDefs.Add('NrSequen', ftInteger, 0);
      CpEstRnc.CreateDataSet;

      ValidaEntradaDados;

      try
          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
          CpQ001.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(253,
                                                                 BrvListParam.AsBrParam, Self.Name);
      finally
          BrvServerProgress.Stop;
      end;

      if (CpQ001.RecordCount > 0) then
      begin
            try
                gNrSequen := 0;
                CpEstRnc.DisableControls;

                while not CpQ001.Eof do
                begin
                      BuscarRCN(CpQ001.FieldByName('CdClaRnc').AsInteger,
                                CpQ001.FieldByName('CdClaRnc').AsInteger,
                                CpQ001.FieldByName('CdClaPai').AsInteger,
                                CpQ001.FieldByName('DsContro').AsString,
                                CpQ001.FieldByName('DsClaRnc').AsString, 1);
                      CpQ001.Next;
                end;

                lCpEstRnc      := TClientDataSet.Create(Self);
                lCpEstRnc.Data := CpEstRnc.Data;

                // Calcular Quantidade de RNC por Mês das Classificações 'Pai'
                lCpEstRnc.Last;
                while not lCpEstRnc.Bof do
                begin
                      CpEstRnc.Filtered := False;
                      CpEstRnc.Filter   := 'CdClaPai = '+lCpEstRnc.FieldByName('CdClaRnc').Text;
                      CpEstRnc.Filtered := True;
                      CpEstRnc.Last;
                      while not CpEstRnc.Bof do
                      begin
                            lCpEstRnc.Edit;
                            for lVrCount := 1 to 12 do
                            begin
                                  lNmMesRnc := gNmLisMes[lVrCount]+'Rnc';
                                  lQtRncMes := CpEstRnc.FieldByName('Qt'+lNmMesRnc).AsInteger;
                                  lQtRncAux := lCpEstRnc.FieldByName('Qt'+lNmMesRnc).AsInteger;

                                  lQtRncAux := lQtRncAux + lQtRncMes;
                                  lCpEstRnc.FieldByName('Qt'+lNmMesRnc).AsInteger := lQtRncAux;
                            end;
                            lCpEstRnc.Post;
                            CpEstRnc.Prior;
                      end;
                      CpEstRnc.Data := lCpEstRnc.Data;
                      lCpEstRnc.Prior;
                end;

                // Calcular a porcentagem (%) de todas as RNCs
                lCpEstRnc.Last;
                while not lCpEstRnc.Bof do
                begin
                      CpEstRnc.Filtered := False;
                      if (lCpEstRnc.FieldByName('CdClaPai').Text = '0') then
                      begin
                            CpEstRnc.Filter :=
                                         'CdClaRnc = ' + lCpEstRnc.FieldByName('CdClaRnc').Text;
                      end else
                      begin
                            CpEstRnc.Filter :=
                                         'CdClaRnc = ' + lCpEstRnc.FieldByName('CdClaPai').Text;
                      end;
                      CpEstRnc.Filtered := True;
                      CpEstRnc.Last;
                      while not CpEstRnc.Bof do
                      begin
                            lCpEstRnc.Edit;
                            for lVrCount := 1 to 12 do
                            begin
                                  lNmMesRnc := gNmLisMes[lVrCount]+'Rnc';
                                  lQtRncMes := CpEstRnc.FieldByName('Qt'+lNmMesRnc).AsInteger;
                                  lQtRncAux := lCpEstRnc.FieldByName('Qt'+lNmMesRnc).AsInteger;

                                  if ((lQtRncMes > 0) and (lQtRncAux > 0)) then
                                  begin
                                        lCpEstRnc.FieldByName('Pc'+lNmMesRnc).Text :=
                                               FormatFloat('##0.00', (100/lQtRncMes)*lQtRncAux);
                                        lCpEstRnc.FieldByName('SnClaZer').AsString := 'N';
                                  end;
                            end;
                            lCpEstRnc.Post;
                            CpEstRnc.Prior;
                      end;
                      CpEstRnc.Data := lCpEstRnc.Data;
                      lCpEstRnc.Prior;
                end;
            finally
                CpEstRnc.IndexDefs.Clear;
                CpEstRnc.IndexDefs.Add('IdxSequen', 'NrSequen', [ixUnique]);
                CpEstRnc.IndexName := 'IdxSequen';
                CpEstRnc.EnableControls;
            end;

            BrvListParam.SetStgParam(StgFiltros);
            CpEstRnc.Filtered   := False;
            CpEstRnc.Filter     := 'SnVisive = ''S''';
            CpEstRnc.Filtered   := True;
            LblQtReg.Caption    := IntToStr(CpEstRnc.RecordCount) + ' Registro(s)';
            PgcFundo.ActivePage := TbsConsulta;
      end else
      begin
            MessageDlg('Nenhum registro encontrado!!!', mtInformation, [mbok], 0);
      end;
end;

procedure TCon0019.BtnVoltarClick(Sender: TObject);
begin
      inherited;
      PgcFundo.ActivePage := TbsFiltro;
      CblCdEmpres.SetFocus;
end;

procedure TCon0019.FormCreate(Sender: TObject);
var lNrIdx : Integer;
begin
      inherited;
      TbsFiltro.TabVisible    := False;
      TbsConsulta.TabVisible  := False;
      PgcFundo.ActivePage     := TbsFiltro;

      CarregaEmpresas(CblCdEmpres, True);

      StgFiltros.ColWidths[0] := 130;
      StgFiltros.ColWidths[1] := 500;
end;

procedure TCon0019.BuscarRCN(pCdClaZer: Integer; pCdClaRnc: Integer; pCdClaPai: Integer;
                             pDsContro: String;  pDsClaRnc: String;  pNrNivel : Integer);
var lCpQ001   : TClientDataSet;
    lCpQ004   : TClientDataSet;
    lLtParPai : TBrvListParam;
    lLtParFil : TBrvListParam;
    lNrMesRnc : String;
    lQtMesRnc : Integer;
    lVrCount  : Integer;
begin
      CpEstRnc.Append;
      CpEstRnc.FieldByName('CdClaRnc').AsInteger := pCdClaRnc;
      CpEstRnc.FieldByName('CdClaPai').AsInteger := pCdClaPai;
      CpEstRnc.FieldByName('DsContro').AsString  := pDsContro;
      CpEstRnc.FieldByName('DsClaRnc').AsString  := pDsClaRnc;
      for lVrCount := 1 to 12 do
      begin
            CpEstRnc.FieldByName('Qt'+gNmLisMes[lVrCount]+'Rnc').AsInteger := 0;
            //CpEstRnc.FieldByName('Pc'+gNmLisMes[lVrCount]+'Rnc').ftFloat := 0;
      end;
      CpEstRnc.FieldByName('NrSequen').AsInteger := gNrSequen;
      Inc(gNrSequen);

      lLtParFil    := TBrvListParam.Create(Self);
      BrvListParam.CopyListParam(lLtParFil);
      lLtParFil.Add('CdClaFil', '', '', IntToStr(pCdClaRnc));
      lCpQ004      := TClientDataSet.Create(Self);
      lCpQ004.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(254,
                                                                    lLtParFil.AsBrParam, Self.Name);
      while not lCpQ004.Eof do
      begin
            lNrMesRnc := gNmLisMes[lCpQ004.FieldByName('NrMesRnc').AsInteger];
            lQtMesRnc := lCpQ004.FieldByName('QtMesRnc').AsInteger;

            CpEstRnc.FieldByName('Qt'+lNrMesRnc+'Rnc').AsInteger := lQtMesRnc;

            lCpQ004.Next;
      end;

      if ((StrToInt(EdtNrNivel.Text) = 0) or (pNrNivel <= StrToInt(EdtNrNivel.Text))) then
      begin
            CpEstRnc.FieldByName('SnVisive').AsString := 'S';
      end else
      begin
            CpEstRnc.FieldByName('SnVisive').AsString := 'N';
      end;

      CpEstRnc.Post;

      lLtParPai    := TBrvListParam.Create(Self);
      lLtParPai.Add('CdClaPai', '', '', IntToStr(pCdClaRnc));
      lCpQ001      := TClientDataSet.Create(Self);
      lCpQ001.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(255, lLtParPai.AsBrParam,
                                                                                         Self.Name);
      if (lCpQ001.RecordCount > 0) then
      begin
            while not lCpQ001.Eof do
            begin
                  BuscarRCN(pCdClaZer,
                            lCpQ001.FieldByName('CdClaRnc').AsInteger,
                            lCpQ001.FieldByName('CdClaPai').AsInteger,
                            pDsContro + '.' + lCpQ001.FieldByName('DsContro').AsString,
                            lCpQ001.FieldByName('DsClaRnc').AsString,
                            pNrNivel+1);
                  lCpQ001.Next;
            end;
      end;
end;

procedure TCon0019.CbxSnClaZerClick(Sender: TObject);
begin
      inherited;
      if (CbxSnClaZer.Checked) then
      begin
            CpEstRnc.Filtered := False;
            CpEstRnc.Filter   := 'SnVisive = ''S''';
            CpEstRnc.Filtered := True;
      end else
      begin
            CpEstRnc.Filtered := False;
            CpEstRnc.Filter   := 'SnClaZer = ''N'' and SnVisive = ''S''';
            CpEstRnc.Filtered := True;
      end;
      LblQtReg.Caption  := IntToStr(CpEstRnc.RecordCount) + ' Registro(s)';
end;

initialization
      RegisterClass(TCon0019);

finalization
      UnRegisterClass(TCon0019);

end.
