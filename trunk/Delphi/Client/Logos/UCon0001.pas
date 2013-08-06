unit UCon0001;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvBitBtn, Mask,
  BrvEditDate, Grids, BrvDbGrids, BrvDbGrid, DB, DBClient, BrvClientDataSet, BrvEditNum, ComCtrls,
  Menus, BrvImgBot, BrvExcel, BrvListParam, ImgList, CheckLst, BrvCheckListBox, BrvRetCon,
  BrvCustomEdit, BrvCustomMaskEdit, BrvServerProgress;

type
  TCon0001 = class(TMov0000)
    CpT002: TBrvClientDataSet;
    DsT002: TDataSource;
    pgcConsulta: TPageControl;
    tbsRegistros: TTabSheet;
    tbsDetalhes: TTabSheet;
    DbgRegistro: TBrvDbGrid;
    DbgDetalhe: TBrvDbGrid;
    DsT003: TDataSource;
    CpT003: TBrvClientDataSet;
    BtnDetail: TBrvBitBtn;
    popMarcar: TPopupMenu;
    MarcarTodos1: TMenuItem;
    DesmarcarTodos1: TMenuItem;
    BrvExcelDados: TBrvExcel;
    BtnExcel: TBrvBitBtn;
    PopCtrc: TPopupMenu;
    Detalhar1: TMenuItem;
    PgcFundo: TPageControl;
    TbsFiltro: TTabSheet;
    TbsConsulta: TTabSheet;
    StgFiltros: TStringGrid;
    Panel1: TPanel;
    BtnPesquisa: TBrvBitBtn;
    Panel9: TPanel;
    Panel4: TPanel;
    Splitter1: TSplitter;
    Panel6: TPanel;
    LblQtReg: TLabel;
    Panel7: TPanel;
    BrvListParam: TBrvListParam;
    BrvServerProgress: TBrvServerProgress;
    BtnVoltar: TBrvBitBtn;
    BrvListParam2: TBrvListParam;
    pnlMestre: TPanel;
    LblCdEmpres: TLabel;
    LblTpTransp: TLabel;
    Label6: TLabel;
    CblTpTransp: TBrvCheckListBox;
    CblCdEmpres: TBrvCheckListBox;
    Panel2: TPanel;
    LblDtFinal: TLabel;
    LblDtInicia: TLabel;
    LblCpCidade: TLabel;
    LblCdDestin: TLabel;
    LblCdMotori: TLabel;
    EdtCdMotori: TBrvEditNum;
    EdtCdDestin: TBrvEditNum;
    EdtCpCidade: TBrvEditNum;
    EdtDtInicia: TBrvEditDate;
    EdtDtFinal: TBrvEditDate;
    LblNmCidade: TBrvRetCon;
    LblRsDestin: TBrvRetCon;
    LblNmMotori: TBrvRetCon;
    RdgTpData: TRadioGroup;
    procedure FormCreate(Sender: TObject);
    procedure BtnDetailClick(Sender: TObject);
    procedure MarcarTodos1Click(Sender: TObject);
    procedure DesmarcarTodos1Click(Sender: TObject);
    procedure Detalhar1Click(Sender: TObject);
    procedure DbgDetalheDblClick(Sender: TObject);
    function  MotoristasMarcados(pCPMotori : Variant) : String;
    procedure BtnPesquisaClick(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);
    procedure BtnExcelClick(Sender: TObject);
    procedure CpT002BeforeDelete(DataSet: TDataSet);
  private
    procedure DetalharCTRC();
    procedure ValidaEntradaDados;
  public
    { Public declarations }
  end;

var
  Con0001: TCon0001;

implementation

uses BrvDb, UDmSrvApl, UClaSrv, UDmTra, BrvDominiosXE;

{$R *.dfm}

procedure TCon0001.DesmarcarTodos1Click(Sender: TObject);
begin
      DbgRegistro.SetarTodasColunas('SnMarcad', 'N');
end;

procedure TCon0001.Detalhar1Click(Sender: TObject);
begin
      inherited;
      DetalharCTRC();
end;

procedure TCon0001.DetalharCTRC();
begin
      inherited;
      DmTra.VisualizarConhecimento(CpT003);
end;

procedure TCon0001.DbgDetalheDblClick(Sender: TObject);
begin
      inherited;
      DetalharCTRC();
end;

procedure TCon0001.BtnExcelClick(Sender: TObject);
Var lColunas  : TStringList;
    lClientDS : TClientDataSet;
    lNrIndice : Integer;
begin
      inherited;
      lColunas  :=  TStringList.Create;
      try
          Screen.Cursor := crHourGlass;
          lClientDS := TClientDataSet.Create(Self);

          if pgcConsulta.ActivePage = tbsRegistros then
          begin
                lClientDS.Data :=  CPT002.Data;

                for lNrIndice := 0 to DbgRegistro.Columns.Count - 1 do
                begin
                      lColunas.Add(DbgRegistro.Columns[lNrIndice].FieldName);
                      lClientDS.FieldByName(DbgRegistro.Columns[lNrIndice].FieldName).DisplayLabel
                                                    := DbgRegistro.Columns[lNrIndice].Title.Caption;
                      BrvExcelDados.BrNomePadrao := 'ConhecimentosSemDataEntrega-Registros';
                end;

                lClientDS.IndexDefs.Clear;
                lClientDS.IndexDefs.Add('IdxMotori', 'CdMotori', [ixUnique]);
                lClientDS.IndexName := 'IdxMotori';
          end
          else
          begin
                lClientDS.Data :=  CPT003.Data;

                for lNrIndice := 0 to DbgDetalhe.Columns.Count - 1 do
                begin
                      lColunas.Add(DbgDetalhe.Columns[lNrIndice].FieldName);
                      lClientDS.FieldByName(DbgDetalhe.Columns[lNrIndice].FieldName).DisplayLabel
                                                   := DbgDetalhe.Columns[lNrIndice].Title.Caption;
                      BrvExcelDados.BrNomePadrao := 'ConhecimentosSemDataEntrega-Detalhes';
                end;
          end;

          lClientDS.First;
          BrvExcelDados.BrDataSet :=  lClientDS;
          BrvExcelDados.Execute(lColunas, '', nil);
      finally
          FreeAndNil(lColunas);
          Screen.Cursor := crDefault;
      end;
end;

procedure TCon0001.BtnDetailClick(Sender: TObject);
var lCdMotori : String;
    lNrCount  : Integer;
begin
      BrvListParam2.Clear;
      lCdMotori := MotoristasMarcados(CPT002.Data);

      if lCdMotori = '' then
      begin
            raise Exception.Create('É necessário selecionar um ou mais motoristas');
      end else
      begin
            BrvListParam.CopyListParam(BrvListParam2);
            BrvListParam2.Add('CdLstMot', 'Motorista(s)', lCdMotori,
                                                           ' and T009.CdMotori in ('+lCdMotori+')');
            Try
                try
                    BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
                    CpT003.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(39,
                                                                BrvListParam2.AsBrParam, Self.Name);
                finally
                    BrvServerProgress.Stop;
                end;

                BrvListParam2.SetStgParam(StgFiltros);
                LblQtReg.Caption := FormatFloat('0', CpT003.RecordCount) + ' Registro(s)';
                if (CpT003.RecordCount > 0) then
                begin
                      CpT003.FieldByName('CdEmpres').Alignment := taRightJustify;
                      CpT003.FieldByName('CdCTRC'  ).Alignment := taRightJustify;
                      CpT003.FieldByName('CdCarga' ).Alignment := taRightJustify;
                      CpT003.FieldByName('CdMotori').Alignment := taRightJustify;
                end;
            Finally
                pgcConsulta.ActivePage  :=  tbsDetalhes;
                BtnDetail.Visible       :=  False;
            end;
      end;
end;

procedure TCon0001.ValidaEntradaDados;
var lDsEmp      : String;
    lLsTpDomini : TStringList;
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

      if EdtCdMotori.BrAsInteger > 0 then
      begin
            BrvListParam.Add('CdMotori', LblCdMotori.Caption, EdtCdMotori.Text + ' - ' +
                                        LblNmMotori.Text, ' and T009.CdMotori = '+EdtCdMotori.Text);
      end else
      begin
            BrvListParam.Add('CdMotori', '', '', '');
      end;

      if EdtCdDestin.BrAsInteger > 0 then
      begin
            BrvListParam.Add('CdDestin', LblCdDestin.Caption, EdtCdDestin.Text + ' - ' +
                                        LblRsDestin.Text, ' and T002.CdDestin = '+EdtCdDestin.Text);
      end else
      begin
            BrvListParam.Add('CdDestin', '', '', '');
      end;

      if EdtCpCidade.BrAsInteger > 0 then
      begin
            BrvListParam.Add('CpCidade', LblCpCidade.Caption, EdtCpCidade.Text + ' - ' +
                                   LblNmCidade.Text, ' and G015.CpCidade = "'+EdtCpCidade.Text+'"');
      end else
      begin
            BrvListParam.Add('CpCidade', '', '', '');
      end;

      if (not EdtDtInicia.BrDataVazia) and (not EdtDtFinal.BrDataVazia)
                               and (EdtDtInicia.BrDataValida) and (EdtDtFinal.BrDataValida) then
      begin
            if EdtDtFinal.BrAsDate < EdtDtInicia.BrAsDate then
            begin
                  EdtDtFinal.SetFocus;
                  raise Exception.Create(LblDtFinal.Caption + ' deve ser maior ou igual à ' +
                                         LblDtInicia.Caption + '!');
            end;
      end;

      if not EdtDtInicia.BrDataVazia then
      begin
            if (not EdtDtInicia.BrDataVazia) and (not EdtDtInicia.BrDataValida) then
            begin
                  EdtDtInicia.SetFocus;
                  raise Exception.Create('Verifique a ' + LblDtInicia.Caption + ' informada!!');
            end else
            begin
                  BrvListParam.Add('DtInicia', LblDtInicia.Caption, EdtDtInicia.Text,
                                 ' and T002.DtEmissa >= <$hh"' + EdtDtInicia.BrAsDataSQL + '"hh$>');
            end;
      end else
      begin
            BrvListParam.Add('DtInicia', '', '', '');
      end;

      if not EdtDtFinal.BrDataVazia then
      begin
            if (not EdtDtFinal.BrDataVazia) and (not EdtDtFinal.BrDataValida) then
            begin
                  EdtDtFinal.SetFocus;
                  raise Exception.Create('Verifique a ' + LblDtFinal.Caption + ' informada!');
            end else
            begin
                  BrvListParam.Add('DtFinal', LblDtFinal.Caption, EdtDtFinal.Text,
                                  ' and T002.DtEmissa <= <$hh"' + EdtDtFinal.BrAsDataSQL + '"hh$>');
            end;
      end else
      begin
            BrvListParam.Add('DtFinal', '', '', '');
      end;

      try
          if (CblTpTransp.BrCheckedCount > 0) then
          begin
                lLsTpDomini := CblTpTransp.BrCheckedListDominio;
                BrvListParam.Add('TpTransp', LblTpTransp.Caption, lLsTpDomini[1],
                                                  ' and T002.TpTransp in (' + lLsTpDomini[0] + ')');
          end else
          begin
                BrvListParam.Add('TpTransp', '', '', '');
          end;
      finally
          FreeAndNil(lLsTpDomini);
      end;

      case RdgTpData.ItemIndex of
        0: BrvListParam.Add('NmColuna', RdgTpData.Caption, RdgTpData.Items[0], 'T002.DtEntMot');
        1: BrvListParam.Add('NmColuna', RdgTpData.Caption, RdgTpData.Items[1], 'T002.DtEntreg');
      end;
end;

procedure TCon0001.BtnPesquisaClick(Sender: TObject);
begin
      inherited;

      ValidaEntradaDados;

      try
          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
          CpT002.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(38,
                                                                 BrvListParam.AsBrParam, Self.Name);
      finally
          BrvServerProgress.Stop;
      end;

      BrvListParam.SetStgParam(StgFiltros);
      LblQtReg.Caption := FormatFloat('0', CpT002.RecordCount) + ' Registro(s)';
      if (CpT002.RecordCount > 0) then
      begin
            CpT002.FieldByName('CdMotori').Alignment := taRightJustify;
            TFloatField(CpT002.FieldByName('QtCarga' )).DisplayFormat := '0';
            CpT002.First;
            PgcFundo.ActivePage := TbsConsulta;
      end else
      begin
            MessageDlg('Nenhum registro encontrado!!!', mtInformation, [mbok], 0);
      end;
end;

procedure TCon0001.BtnVoltarClick(Sender: TObject);
begin
      inherited;
      if (BtnDetail.Visible = False) then
      begin
            pgcConsulta.ActivePage  :=  tbsRegistros;
            BtnDetail.Visible       :=  True;
            BrvListParam.SetStgParam(StgFiltros);
            LblQtReg.Caption := FormatFloat('0', CpT002.RecordCount) + ' Registro(s)';
      end else
      begin
            PgcFundo.ActivePage := TbsFiltro;
      end;
end;

procedure TCon0001.CpT002BeforeDelete(DataSet: TDataSet);
begin
      inherited;
      Abort;
end;

procedure TCon0001.FormCreate(Sender: TObject);
var lNrAba : Integer;
begin
      LblNmMotori.Text :=  '';
      pgcConsulta.ActivePageIndex :=  0;
      TbsFiltro.TabVisible := False;
      TbsConsulta.TabVisible := False;
      PgcFundo.ActivePage := TbsFiltro;

      for lNrAba := 0 to pgcConsulta.PageCount -1 do
      begin
            pgcConsulta.Pages[lNrAba].TabVisible := False;
      end;

      pgcConsulta.ActivePage := tbsRegistros;

      CarregaEmpresas(CblCdEmpres, True);
      CarregaDominios(CblTpTransp, 'T002', 'TPTRANSP');

      StgFiltros.ColWidths[0] := 170;
      StgFiltros.ColWidths[1] := 500;
end;

procedure TCon0001.MarcarTodos1Click(Sender: TObject);
begin
      DbgRegistro.SetarTodasColunas('SnMarcad', 'S');
end;

function TCon0001.MotoristasMarcados(pCPMotori: Variant): String;
var lCPMotori : TClientDataSet;
begin
      try
          lCPMotori      := TClientDataSet.Create(nil);
          lCPMotori.Data := pCPMotori;

          Result         := '';

          lCPMotori.Filtered := false;
          lCPMotori.Filter   := 'SnMarcad = ''S''';
          lCPMotori.Filtered := True;

          while Not lCPMotori.Eof do
          begin
                if Result <> '' then
                begin
                      Result := Result + ', '
                end;

                Result :=  Result + lCPMotori.FieldByName('CdMotori').AsString;

                lCPMotori.Next;
          end;
      finally

      end;
end;

initialization
      RegisterClass(TCon0001);

finalization
      UnRegisterClass(TCon0001);


end.
