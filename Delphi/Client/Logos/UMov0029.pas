unit UMov0029;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  StdCtrls, BrvBitBtn, DBCtrls, BrvDbRetCon, Mask, BrvEditDate, BrvEdit, BrvEditNum, BrvLabel,
  Grids, BrvDbGrids, BrvDbGrid, BrvDbEdit, ComCtrls, DB, DBClient, BrvClientDataSet, BrvRetCon,
  BrvServerProgress, BrvCustomMaskEdit, BrvCustomEdit;

type
  TContabPar = record
    pNrConCre: Integer;
    pNrConDeb: Integer;
    pNrClaCre: String;
    pNrClaDeb: String;
    pCdCeCuCr: Integer;
    pCdCeCuDb: Integer;
  end;

  TMov0029 = class(TMov0000)
    PnlAction: TPanel;
    BtnVoltar: TBrvBitBtn;
    BtnBaixar: TBrvBitBtn;
    PgcWork: TPageControl;
    TbsFiltros: TTabSheet;
    TbsDocs: TTabSheet;
    PnlFiltro: TPanel;
    BrvLabel1: TBrvLabel;
    BrvLabel2: TBrvLabel;
    BrvLabel3: TBrvLabel;
    BrvLabel4: TBrvLabel;
    BrvLabel5: TBrvLabel;
    BrvLabel6: TBrvLabel;
    BrvLabel7: TBrvLabel;
    EdtCdEmpres: TBrvEditNum;
    EdtDtBaixa: TBrvEditDate;
    EdtNrDocto: TBrvEditNum;
    EdtCdTitula: TBrvEditNum;
    EdtDtInicio: TBrvEditDate;
    EdtDtFinal: TBrvEditDate;
    EdtVrPrevisto: TBrvEditNum;
    Panel2: TPanel;
    Panel3: TPanel;
    StgFilFat: TStringGrid;
    TbsTitulares: TTabSheet;
    DbgTitulares: TBrvDbGrid;
    BtnAvancar: TBrvBitBtn;
    TmrOpen: TTimer;
    CpN002Tit: TBrvClientDataSet;
    DsN002Tit: TDataSource;
    PopSelTit: TPopupMenu;
    odos1: TMenuItem;
    Nenhum1: TMenuItem;
    StgFilTit: TStringGrid;
    BrvListParam: TBrvListParam;
    LblDsEmpres: TBrvRetCon;
    LblRsTitula: TBrvRetCon;
    CpN002Doc: TBrvClientDataSet;
    DsN002Doc: TDataSource;
    DbgDocs: TBrvDbGrid;
    EdtQtde: TBrvRetCon;
    BrvLabel9: TBrvLabel;
    BrvLabel8: TBrvLabel;
    BrvLabel10: TBrvLabel;
    BrvLabel12: TBrvLabel;
    EdtValor: TBrvRetCon;
    EdtJuros: TBrvRetCon;
    EdtTotSel: TBrvRetCon;
    CcB004: TBrvClientDataSet;
    CcB008: TBrvClientDataSet;
    TbsBaixa: TTabSheet;
    StgFilBaixa: TStringGrid;
    DbgDocsBaixa: TBrvDbGrid;
    Panel1: TPanel;
    BrvLabel14: TBrvLabel;
    BrvLabel15: TBrvLabel;
    BrvLabel16: TBrvLabel;
    EdtNrConta: TBrvEditNum;
    EdtCDHISTOR: TBrvEditNum;
    EdtCDCENCUS: TBrvEditNum;
    LblNmConta: TBrvRetCon;
    LblDSHISTOR: TBrvRetCon;
    LblDsCENCUS: TBrvRetCon;
    BrvLabel17: TBrvLabel;
    MemCompl: TMemo;
    Panel4: TPanel;
    BrvLabel18: TBrvLabel;
    BrvLabel19: TBrvLabel;
    BrvLabel20: TBrvLabel;
    BrvLabel22: TBrvLabel;
    EdtQtdeRp: TBrvRetCon;
    EdtValorRp: TBrvRetCon;
    EdtJurosRp: TBrvRetCon;
    EdtTotSelRp: TBrvRetCon;
    CpB012: TBrvClientDataSet;
    LblNrClassi: TBrvRetCon;
    BrvServerProgress: TBrvServerProgress;
    PopSelDoc: TPopupMenu;
    odos2: TMenuItem;
    Nenhum2: TMenuItem;
    N1: TMenuItem;
    PreencherValorPago1: TMenuItem;
    procedure FormCreate(Sender: TObject);
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure TmrOpenTimer(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);
    procedure BtnAvancarClick(Sender: TObject);
    procedure odos1Click(Sender: TObject);
    procedure Nenhum1Click(Sender: TObject);
    procedure DbgDocsCellClick(Column: TColumn);
    procedure CpN002DocAfterPost(DataSet: TDataSet);
    procedure BtnBaixarClick(Sender: TObject);
    procedure CpN002TitBeforeDelete(DataSet: TDataSet);
    procedure CpN002DocBeforeDelete(DataSet: TDataSet);
    procedure CpN002DocBeforeInsert(DataSet: TDataSet);
    procedure CpN002TitBeforeInsert(DataSet: TDataSet);
    procedure EdtNrContaBrOnBeforeConsulta(Sender: TObject);
    procedure CpN002DocBeforePost(DataSet: TDataSet);
    procedure odos2Click(Sender: TObject);
    procedure Nenhum2Click(Sender: TObject);
    procedure PreencherValorPago1Click(Sender: TObject);
    procedure EdtNrDoctoBrOnBeforeConsulta(Sender: TObject);
  private
    procedure VerificaEntradas;
    procedure AlteraSelecaoTitular(pSnMarcad: String);
    procedure Totaliza(pDtN002: Variant);
    procedure CriarTabelaContabilizacao;
    procedure VerificaParametros;
    procedure LocalizarParametro(pNrParam: String);
    procedure VerificaEntradasBaixa;
    procedure GerarContabilBaixaDocumentos;
    procedure GeraContabilizacoes;
    procedure LancaContabilizacao(pContabil: TContabPar; pVrContab: Extended);
    procedure AlteraSelecaoDocumento(pSnMarcad: String);
    procedure IniciaProcesso;
    procedure BaixarDocumentos;
    { Private declarations }
  public
    { Public declarations }

    gDsComple : String;
    gNrLancto : Integer;
    gDsCapFor : String;
    gNrPlano  : Integer;

    gConParBai : TContabPar;
    gConParJur : TContabPar;
    function RetTpConta: String;
    function RetTpOperac: String;
  end;

var
  Mov0029: TMov0029;

implementation

uses UDmSrvApl, BrvFuncoesXE, UDmCtb, UClaSrv;

{$R *.dfm}

procedure TMov0029.VerificaEntradas;
begin
      if (not EdtDtBaixa.BrDataValida) then
      begin
            EdtDtBaixa.SetFocus;
            Raise Exception.Create('Informe uma Data Válida!');
      end;

      if ((EdtDtInicio.BrDataValida) and (EdtDtFinal.BrDataValida)) then
      begin
            if (EdtDtFinal.BrAsDate < EdtDtInicio.BrAsDate) then
            begin
                  Raise Exception.Create('Verifique o periodo informado!');
            end;
      end;

      if (EdtCdEmpres.BrAsInteger = 0) then
      begin
            Raise Exception.Create('Informe uma empresa!');
      end;
end;

procedure TMov0029.BtnAvancarClick(Sender: TObject);
var lNrDocto  : String;
    lCdTitula : String;
    lDtVenDoc : String;
    lCdEmpres : String;
    lVrDomini : TStrings;
    lDsDomini : TStrings;
    lDsDocErr : String;
begin
      inherited;

      if (PgcWork.ActivePageIndex = 0) then
      begin
            if ((RetTpConta = 'P') and (RetTpOperac = 'C')) then
            begin
                  raise Exception.Create('Operação não permitida!');
            end;

            VerificaEntradas;

            gNrPlano := DmCtb.PlanoContasEmpresa(EdtCdEmpres.BrAsInteger, EdtDtBaixa.BrAsDate);
            gDsComple := '';

            BrvListParam.Clear;

            if (Trim(gVrParam) <> '') then
            begin
                  if (RetTpConta <> 'P') and (RetTpConta <> 'R') then
                  begin
                        raise Exception.Create('Verifique os parâmetros do item de menu!' + Chr(13)+
                                               'Tipo de documento inválido...');
                  end;

                  if (RetTpOperac <> 'B') and (RetTpOperac <> 'C') then
                  begin
                        raise Exception.Create('Verifique os parâmetros do item de menu!' + Chr(13)+
                                               'Tipo de operação inválida...');
                  end;

                  if (RetTpOperac = 'C') then
                  begin
                        DbgDocs.Columns[0].ReadOnly := False;
                        DbgDocs.Columns[1].ReadOnly := True;
                        DbgDocs.Columns[2].ReadOnly := True;
                        DbgDocs.Columns[3].ReadOnly := True;
                        DbgDocs.Columns[4].ReadOnly := True;
                        DbgDocs.Columns[5].ReadOnly := True;
                        DbgDocs.Columns[6].ReadOnly := True;
                        DbgDocs.Columns[7].ReadOnly := True;
                        DbgDocs.Columns[8].Visible  := False;
                        DbgDocs.Columns[9].Visible  := False;
                        DbgDocs.Columns[10].Visible := False;
                        DbgDocs.Columns[11].Visible := False;

                        DbgDocsBaixa.Columns[7].Visible  := False;
                        DbgDocsBaixa.Columns[8].Visible  := False;
                        DbgDocsBaixa.Columns[9].Visible  := False;
                        DbgDocsBaixa.Columns[10].Visible := False;
                  end else
                  begin
                        DbgDocs.Columns[0].ReadOnly := False;
                        DbgDocs.Columns[1].ReadOnly := False;
                        DbgDocs.Columns[2].ReadOnly := False;
                        DbgDocs.Columns[3].ReadOnly := False;
                        DbgDocs.Columns[4].ReadOnly := False;
                        DbgDocs.Columns[5].ReadOnly := False;
                        DbgDocs.Columns[6].ReadOnly := False;
                        DbgDocs.Columns[7].ReadOnly := False;
                        DbgDocs.Columns[8].Visible  := True;
                        DbgDocs.Columns[9].Visible  := True;
                        DbgDocs.Columns[10].Visible := True;
                        DbgDocs.Columns[11].Visible := True;

                        DbgDocsBaixa.Columns[7].Visible  := True;
                        DbgDocsBaixa.Columns[8].Visible  := True;
                        DbgDocsBaixa.Columns[9].Visible  := True;
                        DbgDocsBaixa.Columns[10].Visible := True;
                  end;

            end else
            begin
                  raise Exception.Create('Este formulário necessita de parâmetros de inicialização!'
                                         + 'Verifique junto ao setor de TI...');
            end;

            if (EdtNrDocto.BrAsInteger > 0) then
            begin
                  lNrDocto := '<%NrDocto%>; and N002.NrDocto = "' + EdtNrDocto.Text + '"';
                  BrvListParam.Add('NrDocto','Documento', EdtNrDocto.Text, EdtNrDocto.Text);
            end else
            begin
                  lNrDocto := '<%NrDocto%>;';
            end;

            if (EdtCdTitula.BrAsInteger > 0) then
            begin
                  lCdTitula := '<%CdTitula%>; and N002.CdTitula = ' + EdtCdTitula.Text;
                  BrvListParam.Add('CdTitular','Titular', EdtCdTitula.Text + ' : ' +
                                                          LblRsTitula.Text , '');
            end else
            begin
                  lCdTitula := '<%CdTitula%>;';
            end;

            if ((EdtDtInicio.BrDataValida) and (EdtDtFinal.BrDataValida)) then
            begin
                  lDtVenDoc := '<%DtVenDoc%>; and N003.DtVenDoc >= <$hh"' +EdtDtInicio.BrAsDataSQL +
                               '"hh$> and N003.DtVenDoc <= <$hh"' + EdtDtFinal.BrAsDataSQL+ '"hh$>';
                  BrvListParam.Add('DtVenDoc','Periodo', EdtDtInicio.BrAsDataSQL + '->' +
                                                         EdtDtFinal.BrAsDataSQL,
                                                         EdtDtInicio.BrAsDataSQL + '->' +
                                                         EdtDtFinal.BrAsDataSQL);
            end else
            begin
                  lDtVenDoc := '<%DtVenDoc%>;';
            end;

            BrvListParam.Add('CdEmpres','Empresa', EdtCdEmpres.Text + ' : ' +
                                                         LblDsEmpres.Text, '');
            lCdEmpres := '<%CdEmpres%>;' + EdtCdEmpres.Text;


            BrvListParam.SetStgParam(StgFilTit);
            BrvListParam.SetStgParam(StgFilFat);

            CpN002Tit.Close;
            CpN002Tit.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(217,
                                    '<%TpConta%>;' + RetTpConta + Chr(13) +
                                    lCdEmpres + Chr(13) +
                                    lNrDocto  + Chr(13) +
                                    lCdTitula + Chr(13) +
                                    lDtVenDoc, self.Name);
            CpN002Tit.Open;

            BtnVoltar.Enabled  := True;
            BtnAvancar.Enabled := (CpN002Tit.RecordCount > 0);
            PgcWork.ActivePageIndex := 1;
            Caption := gDsCapFor + ' [ Titulares ]';
      end else
      begin
            if (PgcWork.ActivePageIndex = 1) then
            begin
                  BrvListParam.Clear;

                  if (EdtNrDocto.BrAsInteger > 0) then
                  begin
                        lNrDocto := '<%NrDocto%>; and N002.NrDocto = "' + EdtNrDocto.Text + '"';
                        BrvListParam.Add('NrDocto','Documento', EdtNrDocto.Text, EdtNrDocto.Text);
                  end else
                  begin
                        lNrDocto := '<%NrDocto%>;';
                  end;

                  lCdTitula := '';

                  try
                      CpN002Tit.First;
                      CpN002Tit.DisableControls;

                      while not CpN002Tit.Eof do
                      begin
                            if (CpN002Tit.FieldByName('SnMarcad').AsString = 'S') then
                            begin
                                  lCdTitula := lCdTitula +
                                                   CpN002Tit.FieldByName('CdTitula').AsString + ',';
                            end;

                            CpN002Tit.Next;
                      end;

                      lCdTitula := copy(lCdTitula,1,length(lCdTitula)-1);
                  finally
                      CpN002Tit.First;
                      CpN002Tit.EnableControls;
                  end;

                  if (Trim(lCdTitula) <> '') then
                  begin
                        BrvListParam.Add('CdTitular','Titular(es)', lCdTitula, '');
                        lCdTitula := '<%CdTitula%>; and N002.CdTitula in ( ' + lCdTitula + ' )';
                  end else
                  begin
                        lCdTitula := '<%CdTitula%>;';
                  end;

                  if ((EdtDtInicio.BrDataValida) and (EdtDtFinal.BrDataValida)) then
                  begin
                        lDtVenDoc := '<%DtVenDoc%>; and N003.DtVenDoc >= <$hh"' +
                                      EdtDtInicio.BrAsDataSQL +
                                     '"hh$> and N003.DtVenDoc <= <$hh"' +
                                     EdtDtFinal.BrAsDataSQL + '"hh$>';
                        BrvListParam.Add('DtVenDoc','Periodo', EdtDtInicio.BrAsDataSQL + '->' +
                                                               EdtDtFinal.BrAsDataSQL,
                                                               EdtDtInicio.BrAsDataSQL + '->' +
                                                               EdtDtFinal.BrAsDataSQL);
                  end else
                  begin
                        lDtVenDoc := '<%DtVenDoc%>;';
                  end;

                  if (EdtCdEmpres.BrAsInteger = 0) then
                  begin
                        BrvListParam.Add('CdEmpres','Empresa',
                                                       EdtCdEmpres.BrDicionario.CorpCommaCodes, '');
                        lCdEmpres := '<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes;
                  end else
                  begin
                        BrvListParam.Add('CdEmpres','Empresa', EdtCdEmpres.Text + ' : ' +
                                                               LblDsEmpres.Text, '');
                        lCdEmpres := '<%CdEmpres%>;' + EdtCdEmpres.Text;
                  end;

                  BrvListParam.SetStgParam(StgFilFat);
                  BrvListParam.SetStgParam(StgFilBaixa);

                  CpN002Doc.Close;
                  CpN002Doc.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(218,
                                          '<%TpConta%>;' + RetTpConta + Chr(13) +
                                          lCdEmpres + Chr(13) +
                                          lNrDocto  + Chr(13) +
                                          lCdTitula + Chr(13) +
                                          lDtVenDoc, self.Name);
                  CpN002Doc.Open;

                  TFloatField(CpN002Doc.FieldByName('VrTotal')).DisplayFormat := '#0.00';
                  TFloatField(CpN002Doc.FieldByName('VrDocto')).DisplayFormat := '#0.00';
                  TFloatField(CpN002Doc.FieldByName('VrJuros')).DisplayFormat := '#0.00';
                  TFloatField(CpN002Doc.FieldByName('VrPago')).DisplayFormat  := '#0.00';

                  DmSrvApl.BrvDicionario.AtributoDominioValores('N004', 'TpPagto',
                                                                lDsDomini, lVrDomini);

                  DbgDocs.Columns[11].BrPickValue := lVrDomini;
                  DbgDocs.Columns[11].PickList    := lDsDomini;

                  DbgDocsBaixa.Columns[10].BrPickValue := lVrDomini;
                  DbgDocsBaixa.Columns[10].PickList    := lDsDomini;

                  try
                      CpN002Doc.DisableControls;
                      CpN002Doc.First;

                      while not CpN002Doc.Eof do
                      begin
                            CpN002Doc.Edit;
                            CpN002Doc.FieldByName('VrTotal').AsFloat :=
                                                           CpN002Doc.FieldByName('VrDocto').AsFloat;
                            CpN002Doc.Post;

                            CpN002Doc.Next;
                      end;

                  finally
                      CpN002Doc.First;
                      CpN002Doc.EnableControls;
                  end;


                  Totaliza(CpN002Doc.Data);

                  BtnVoltar.Enabled  := True;
                  PgcWork.ActivePageIndex := 2;
                  Caption := gDsCapFor + ' [ Documentos ]';
            end else
            begin
                  CpN002Doc.Filtered := False;
                  CpN002Doc.Filter   := 'SnMarcad = ' + QuotedStr('S');
                  CpN002Doc.Filtered := True;

                  if (RetTpOperac <> 'C') then
                  begin
                        try
                            CpN002Doc.DisableControls;
                            CpN002Doc.First;
                            lDsDocErr := '';

                            while not CpN002Doc.eof do
                            begin
                                  if (Trim(CpN002Doc.FieldByName('TpPagto').AsString) = '') then
                                  begin
                                        lDsDocErr := lDsDocErr +
                                                         CpN002Doc.FieldByName('NrDocto').AsString +
                                                         '-' +
                                                         CpN002Doc.FieldByName('NrOrdem').AsString +
                                                         '   ';
                                  end;
                                  CpN002Doc.Next;
                            end;
                        finally
                            CpN002Doc.First;
                            CpN002Doc.EnableControls;
                        end;

                        if ((lDsDocErr) <> '') then
                        begin
                              CpN002Doc.Filtered := False;

                              raise Exception.Create('Documentos sem tipo de pagamento informado!' +
                                                     #13 + 'Verifique ' + lDsDocErr);
                        end;
                  end;

                  BtnBaixar.Enabled := (CpN002Doc.RecordCount > 0);

                  BtnAvancar.Enabled := False;
                  PgcWork.ActivePageIndex := 3;
                  Caption := gDsCapFor + ' [ Baixa ]';
            end;
      end;
end;

procedure TMov0029.CriarTabelaContabilizacao;
begin
      gnrLancto := 1;
      if (CcB004.Active) then
      begin
            CcB004.EmptyDataSet;
            CcB004.Close;
      end;

      CcB004.FieldDefs.Clear;
      CcB004.FieldDefs.Add('nrLancto', ftinteger , 0);
      CcB004.FieldDefs.Add('nrplano' , ftinteger , 0);
      CcB004.FieldDefs.Add('nrconcre', ftinteger , 0);
      CcB004.FieldDefs.Add('nrcondeb', ftinteger , 0);
      CcB004.FieldDefs.Add('cdhistor', ftinteger , 0);
      CcB004.FieldDefs.Add('dshistor', ftString  ,50);
      CcB004.FieldDefs.Add('vrlancto', ftFloat   , 0);
      CcB004.FieldDefs.Add('CdEmpres', ftinteger , 0);
      CcB004.FieldDefs.Add('nrdocto' , ftString  ,30);
      CcB004.FieldDefs.Add('nmformul', ftstring  ,20);
      CcB004.FieldDefs.Add('dtlancto', ftDate    , 0);
      CcB004.FieldDefs.Add('SnEncerr', ftstring  , 1);
      CcB004.FieldDefs.Add('NrClaCre', ftstring  ,60);
      CcB004.FieldDefs.Add('NrClaDeb', ftstring  ,60);
      CcB004.CreateDataSet;

      if (CcB008.Active) then
      begin
            CcB008.EmptyDataSet;
            CcB008.Close;
      end;

      CcB008.FieldDefs.Clear;
      CcB008.FieldDefs.Add('nrLancto', ftinteger , 0);
      CcB008.FieldDefs.Add('CdCenCus', ftinteger , 0);
      CcB008.FieldDefs.Add('VrLancto', ftFloat   , 0);
      CcB008.FieldDefs.Add('TpLancto', ftString  , 1);
      CcB008.CreateDataSet;
end;

procedure TMov0029.VerificaParametros;
var
    lNrIdx    : Integer;
    lLnParame : String;
    lNrSeqPar : String;
begin
      for lNrIdx := 0 to gStlParCon.Count-1 do
      begin
            lLnParame := gStlParCon.Strings[lNrIdx];
            lNrSeqPar := lNrSeqPar + Copy(lLnParame, 1, Pos(';', lLnParame)-1) + ',';
      end;

      lNrSeqPar := Copy(lNrSeqPar, 1, length(lNrSeqPar)-1);

      CpB012.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(177,
                              '<%TpFormul%>;' + UpperCase(copy(Self.Name, 1,3)) + Chr(13) +
                              '<%NrSeqFor%>;' + copy(Self.Name, 4,4) + Chr(13) +
                              '<%CdEmpres%>;' + EdtCdEmpres.Text + Chr(13) +
                              '<%NrSeqPar%>;' + lNrSeqPar, UpperCase(Self.Name));

      if (gStlParCon.Count <> CpB012.RecordCount) then
      begin
            raise Exception.Create('Parâmetros de contabilização não cadastrados!' + Chr(13) +
                                   'Verifique...');
      end;

      LocalizarParametro('1');
      LocalizarParametro('2');
      LocalizarParametro('3');
      LocalizarParametro('4');
      LocalizarParametro('5');
      LocalizarParametro('6');
end;

procedure TMov0029.LocalizarParametro(pNrParam : String);
begin
      if (not CpB012.Locate('NrSeqPar', pNrParam, [loCaseInsensitive])) then
      begin
            raise Exception.Create('Parâmetros de contabilização não localizado!' + Chr(13) +
                             'Verifique...');
      end;
end;

procedure TMov0029.LancaContabilizacao(pContabil: TContabPar; pVrContab: Extended);
begin
      if (pVrContab > 0) then
      begin
            CcB004.Append;
            CcB004.FieldByName('nrLancto').Asinteger := gnrLancto;
            CcB004.FieldByName('nrplano' ).Asinteger := gNrPlano;
            CcB004.FieldByName('nrconcre').Asinteger := pContabil.pNrConCre;
            CcB004.FieldByName('nrcondeb').Asinteger := pContabil.pNrConDeb;
            CcB004.FieldByName('cdhistor').Asinteger := EdtCDHISTOR.BrAsInteger;
            CcB004.FieldByName('dshistor').AsString  := MemCompl.Lines.Text;
            CcB004.FieldByName('vrlancto').AsFloat   := pVrContab;
            CcB004.FieldByName('CdEmpres').Asinteger := EdtCdEmpres.BrAsInteger;
            CcB004.FieldByName('nrdocto' ).AsString  := CpN002Doc.FieldByName('NrDocto').AsString;
            CcB004.FieldByName('nmformul').Asstring  := Self.Name;
            CcB004.FieldByName('dtlancto').AsDateTime:= EdtDtBaixa.BrAsDate;
            CcB004.FieldByName('SnEncerr').Asstring  := 'N';
            CcB004.FieldByName('NrClaCre').Asstring  := pContabil.pNrClaCre;
            CcB004.FieldByName('NrClaDeb').Asstring  := pContabil.pNrClaDeb;
            CcB004.Post;

            if (pContabil.pCdCeCuCr > 0) then
            begin
                  CcB008.Append;
                  CcB008.FieldByName('nrLancto').AsInteger := gNrLancto;
                  CcB008.FieldByName('CdCenCus').AsInteger := pContabil.pCdCeCuCr;
                  CcB008.FieldByName('VrLancto').AsFloat   := pVrContab;
                  CcB008.FieldByName('TpLancto').AsString  := 'C';
                  CcB008.Post;
            end;

            if (pContabil.pCdCeCuDb > 0) then
            begin
                  CcB008.Append;
                  CcB008.FieldByName('nrLancto').AsInteger := gNrLancto;
                  CcB008.FieldByName('CdCenCus').AsInteger := pContabil.pCdCeCuDb;
                  CcB008.FieldByName('VrLancto').AsFloat   := pVrContab;
                  CcB008.FieldByName('TpLancto').AsString  := 'D';
                  CcB008.Post;
            end;

            inc(gnrLancto);
      end;
end;

procedure TMov0029.GerarContabilBaixaDocumentos;
begin
      CpN002Doc.First;

      while not CpN002Doc.Eof do
      begin
            LancaContabilizacao(gConParBai, CpN002Doc.FieldByName('VrDocto').AsFloat);
            LancaContabilizacao(gConParJur, CpN002Doc.FieldByName('VrJuros').AsFloat);

            CpN002Doc.Next;
      end;
end;

procedure TMov0029.GeraContabilizacoes;

      procedure MoveValoresContabPagar(var pConPar : TContabPar);
      begin
            pConPar.pNrConCre := CpB012.FieldByName('NrConCre').Asinteger;
            pConPar.pNrConDeb := EdtNrConta.BrAsInteger;
            pConPar.pNrClaCre := CpB012.FieldByName('NrClaCre').AsString;
            pConPar.pNrClaDeb := LblNrClassi.Text;
            pConPar.pCdCeCuCr := CpB012.FieldByName('CdCeCuCr').AsInteger;
            pConPar.pCdCeCuDb := EdtCDCENCUS.BrAsInteger;
      end;

      procedure MoveValoresContabReceber(var pConPar : TContabPar);
      begin
            pConPar.pNrConCre := EdtNrConta.BrAsInteger;
            pConPar.pNrConDeb := CpB012.FieldByName('NrConDeb').Asinteger;
            pConPar.pNrClaCre := LblNrClassi.Text;
            pConPar.pNrClaDeb := CpB012.FieldByName('NrClaDeb').AsString;
            pConPar.pCdCeCuCr := EdtCDCENCUS.BrAsInteger;
            pConPar.pCdCeCuDb := CpB012.FieldByName('CdCeCuDe').AsInteger;
      end;

begin
      VerificaParametros;

      CriarTabelaContabilizacao;

      if (RetTpConta = 'P') then
      begin
            //Documentos Pagar
            if (RetTpOperac = 'B') then
            begin
                  //Baixa
                  LocalizarParametro('1');
                  MoveValoresContabPagar(gConParBai);
                  //Juro
                  LocalizarParametro('2');
                  MoveValoresContabPagar(gConParJur);
            end else
            begin
                  //Cancelamento
                  LocalizarParametro('3');
                  MoveValoresContabPagar(gConParBai);
            end;

            GerarContabilBaixaDocumentos;
      end else
      begin
            //Documentos Receber
            if (RetTpOperac = 'B') then
            begin
                  //Baixa
                  LocalizarParametro('4');
                  MoveValoresContabReceber(gConParBai);
                  //Juro
                  LocalizarParametro('5');
                  MoveValoresContabReceber(gConParJur);
            end else
            begin
                  //Cancelamento
                  LocalizarParametro('6');
                  MoveValoresContabReceber(gConParBai);
            end;

           GerarContabilBaixaDocumentos;
      end;
end;

procedure TMov0029.VerificaEntradasBaixa;
begin
      if  (EdtVrPrevisto.BrAsFloat > 0)        and
          (EdtVrPrevisto.BrAsFloat <> StrToFloat(EdtTotSel.Text)) then
      begin
            raise Exception.Create('Total selecionado está diferente do total previsto');
      end;

      if (EdtNrConta.BrAsInteger = 0) then
      begin
            raise Exception.Create('Informe uma conta válida!');
      end;

      if (EdtCDHISTOR.BrAsInteger = 0) then
      begin
            raise Exception.Create('Informe um histórico válido!');
      end;
end;

procedure TMov0029.BaixarDocumentos;
var lCpParCon : TClientDataSet;
    lCcDocBai : TClientDataSet;
    lConexao  : TSDmAdmClient;
    lDsResult : AnsiString;
begin
      try
          VerificaEntradasBaixa;
          GeraContabilizacoes;

          lCpParCon := TClientDataSet.Create(Self);
          lCcDocBai := TClientDataSet.Create(Self);

          lCpParCon.FieldDefs.Clear;
          lCpParCon.FieldDefs.Add('B004'    ,   ftMemo,  0);
          lCpParCon.FieldDefs.Add('B008'    ,   ftMemo,  0);
          lCpParCon.FieldDefs.Add('NmFormul', ftString, 20);
          lCpParCon.FieldDefs.Add('CdUsuari', ftInteger, 0);
          lCpParCon.FieldDefs.Add('NrPlano' , ftInteger, 0);
          lCpParCon.CreateDataSet;

          lCpParCon.Append;
          lCpParCon.FieldByName('B004'    ).Value    := CcB004.XMLData;
          lCpParCon.FieldByName('B008'    ).Value    := CcB008.XMLData;
          lCpParCon.FieldByName('NmFormul').AsString := Self.Name;
          lCpParCon.FieldByName('CdUsuari').AsInteger:= DmSrvApl.BrvDicionario.UserCode;
          lCpParCon.FieldByName('NrPlano' ).AsString := FormatFloat('0', gNrPlano);
          lCpParCon.Post;

          lCcDocBai.Data := CpN002Doc.Data;
          lCcDocBai.First;

          while not lCcDocBai.Eof do
          begin
                if (lCcDocBai.FieldByName('SnMarcad').AsString = 'S') then
                begin
                      lCcDocBai.Next;
                end else
                begin
                      lCcDocBai.Delete;
                end;
          end;

          try
              BrvServerProgress.Start(Self.Caption, 'Gravando Nota', 100, 100);
              lConexao  := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
              lDsResult := lConexao.BaixarDocumento(DmSrvApl.BrvDicionario.Credencial,
                                                    RetTpOperac,
                                                    CpN002Doc.Data,
                                                    lCpParCon.Data,
                                                    EdtCDHISTOR.BrAsInteger,
                                                    EdtDtBaixa.BrAsDate);
          finally
              BrvServerProgress.Stop;
          end;

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

          MessageDlg('Baixa(s) realizada(s) com sucesso!!', mtInformation, [mbOk], 0);

          IniciaProcesso;
      finally
          FreeAndNil(lCpParCon);
          FreeAndNil(lConexao);
      end;
end;

procedure TMov0029.BtnBaixarClick(Sender: TObject);
begin
      if (MessageDlg('Deseja continuar?', mtConfirmation, [mbyes, mbno], 0) = MrYes) then
      begin
            BaixarDocumentos;
      end else
      begin
            MessageDlg('Operação cancelada!', mtError, [mbOk], 0)
      end;
end;

procedure TMov0029.IniciaProcesso;
begin
      EdtNrConta.BrAsInteger  := 0;
      EdtCDHISTOR.BrAsInteger := 0;
      EdtCDCENCUS.BrAsInteger := 0;
      LblNmConta.Text  := '';
      LblDSHISTOR.Text := '';
      LblDsCENCUS.Text := '';
      LblNrClassi.Text := '';
      MemCompl.Lines.Text := '';
      PgcWork.ActivePageIndex := 0;
      BtnVoltar.Enabled  := False;
      BtnAvancar.Enabled := True;
      Caption            := gDsCapFor;
      EdtCdEmpres.BrAsInteger := 0;
      EdtNrDocto.BrAsInteger  := 0;
      EdtCdTitula.BrAsInteger := 0;
      EdtDtInicio.Text        := '';
      EdtDtFinal.Text         := '';
      EdtVrPrevisto.BrAsFloat := 0;
      EdtDtBaixa.BrAsDate     := DmSrvApl.BrvDicionario.DataServer;
      TmrOpen.Enabled         := True;
end;

procedure TMov0029.BtnVoltarClick(Sender: TObject);
begin
      inherited;
      BtnBaixar.Enabled := False;

      if (PgcWork.ActivePageIndex = 1) then
      begin
            BtnVoltar.Enabled  := False;
            BtnAvancar.Enabled := True;
            PgcWork.ActivePageIndex := 0;
            TmrOpen.Enabled    := True;
            Caption            := gDsCapFor;
      end else
      begin
            if (PgcWork.ActivePageIndex = 2) then
            begin
                  BtnVoltar.Enabled  := True;
                  BtnAvancar.Enabled := True;
                  PgcWork.ActivePageIndex := 1;
                  Caption := gDsCapFor + ' [ Titulares ]';
            end else
            begin
                  BtnVoltar.Enabled  := True;
                  BtnAvancar.Enabled := True;
                  PgcWork.ActivePageIndex := 2;
                  CpN002Doc.Filtered := False;
                  Caption := gDsCapFor + ' [ Documentos ]';
            end;
      end;
end;

procedure TMov0029.CpN002DocAfterPost(DataSet: TDataSet);
begin
      inherited;
      Totaliza(CpN002Doc.Data);
end;

procedure TMov0029.CpN002DocBeforeDelete(DataSet: TDataSet);
begin
      inherited;
      Abort;
end;

procedure TMov0029.CpN002DocBeforeInsert(DataSet: TDataSet);
begin
      inherited;
      Abort;
end;

procedure TMov0029.CpN002DocBeforePost(DataSet: TDataSet);
begin
      inherited;
      CpN002Doc.FieldByName('VrTotal').AsFloat :=
                                                CpN002Doc.FieldByName('VrDocto').AsFloat +
                                                CpN002Doc.FieldByName('VrJuros').AsFloat;
end;

procedure TMov0029.CpN002TitBeforeDelete(DataSet: TDataSet);
begin
      inherited;
      Abort;
end;

procedure TMov0029.CpN002TitBeforeInsert(DataSet: TDataSet);
begin
      inherited;
      Abort;
end;

procedure TMov0029.DbgDocsCellClick(Column: TColumn);
begin
      inherited;
      if (UpperCase(Column.FieldName) = 'SNMARCAD') then
      begin
            if (CpN002Doc.State in [dsEdit]) then
            begin
                  CpN002Doc.Post;
            end;
      end;
end;

procedure TMov0029.Totaliza(pDtN002: Variant);
var lCpN002 : TClientDataSet;
    lQtRegDoc : Integer;
    lTtPagDoc : Extended;
    lTtJurDoc : Extended;
    lTtSelec  : Extended;
begin
      try
          lCpN002 := TClientDataSet.Create(Self);
          lCpN002.Close;
          lCpN002.Data := pDtN002;
          lCpN002.Open;

          lQtRegDoc := 0;
          lTtPagDoc := 0;
          lTtJurDoc := 0;
          lTtSelec  := 0;

          lCpN002.First;
          while not lCpN002.Eof do
          begin
                if (lCpN002.FieldByName('SnMarcad').AsString = 'S') then
                begin
                      lQtRegDoc := lQtRegDoc + 1;
                      lTtPagDoc := lTtPagDoc + lCpN002.FieldByName('VrDocto').AsFloat;
                      lTtJurDoc := lTtJurDoc + lCpN002.FieldByName('VrJuros').AsFloat;
                      lTtSelec  := (lTtPagDoc + lTtJurDoc);
                end;

                lCpN002.Next;
          end;

          EdtQtde.Text       := FormatFloat('0'   , lQtRegDoc);
          EdtValor.Text      := FormatFloat('0.00', lTtPagDoc);
          EdtJuros.Text      := FormatFloat('0.00', lTtJurDoc);
          EdtTotSel.Text     := FormatFloat('0.00', lTtSelec );
          EdtQtdeRp.Text     := FormatFloat('0'   , lQtRegDoc);
          EdtValorRp.Text    := FormatFloat('0.00', lTtPagDoc);
          EdtJurosRp.Text    := FormatFloat('0.00', lTtJurDoc);
          EdtTotSelRp.Text   := FormatFloat('0.00', lTtSelec );
      finally
          FreeAndNil(lCpN002);
      end;
end;

procedure TMov0029.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TMov0029.EdtNrContaBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      if (gNrPlano > 0) then
      begin
            EdtNrConta.BrParams.Clear;
            EdtNrConta.BrParams.Add('<%NrPlano%>;' + FormatFloat('0', gNrPlano));
      end else
      begin
            Raise Exception.Create('Informe um Plano de Contas válido!');
      end;
end;

procedure TMov0029.EdtNrDoctoBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtNrDocto.BrParams.Clear;
      EdtNrDocto.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.Text);
      EdtNrDocto.BrParams.Add('<%TpConta%>;'  + RetTpConta);
end;

procedure TMov0029.FormCreate(Sender: TObject);
begin
      inherited;
      EdtDtBaixa.BrAsDate     := DmSrvApl.BrvDicionario.DataServer;
      TbsFiltros.TabVisible   := False;
      TbsTitulares.TabVisible := False;
      TbsBaixa.TabVisible     := False;
      TbsDocs.TabVisible      := False;
      PgcWork.ActivePageIndex := 0;
      TmrOpen.Enabled         := True;
      gDsCapFor               := '';
end;

procedure TMov0029.AlteraSelecaoTitular(pSnMarcad: String);
begin
      try
          CpN002Tit.DisableControls;
          CpN002Tit.First;

          while not CpN002Tit.eof do
          begin
                CpN002Tit.Edit;
                CpN002Tit.FieldByName('SnMarcad').AsString := pSnMarcad;
                CpN002Tit.Post;
                CpN002Tit.Next;
          end;

      finally
          CpN002Tit.First;
          CpN002Tit.EnableControls;
      end;
end;

procedure TMov0029.Nenhum1Click(Sender: TObject);
begin
      AlteraSelecaoTitular('N');
end;

procedure TMov0029.Nenhum2Click(Sender: TObject);
begin
      inherited;
      AlteraSelecaoDocumento('N');
end;

procedure TMov0029.odos1Click(Sender: TObject);
begin
      AlteraSelecaoTitular('S');
end;

procedure TMov0029.AlteraSelecaoDocumento(pSnMarcad: String);
begin
      try
          CpN002Doc.DisableControls;
          CpN002Doc.First;

          while not CpN002Doc.eof do
          begin
                CpN002Doc.Edit;
                CpN002Doc.FieldByName('SnMarcad').AsString := pSnMarcad;
                CpN002Doc.Post;
                CpN002Doc.Next;
          end;

      finally
          CpN002Doc.First;
          CpN002Doc.EnableControls;
      end;
end;

procedure TMov0029.odos2Click(Sender: TObject);
begin
      inherited;
      AlteraSelecaoDocumento('S');
end;

procedure TMov0029.PreencherValorPago1Click(Sender: TObject);
begin
      inherited;
      try
          CpN002Doc.DisableControls;
          CpN002Doc.First;

          while not CpN002Doc.eof do
          begin
                CpN002Doc.Edit;
                CpN002Doc.FieldByName('VrPago').AsFloat := CpN002Doc.FieldByName('VrTotal').AsFloat;
                CpN002Doc.Post;
                CpN002Doc.Next;
          end;

      finally
          CpN002Doc.First;
          CpN002Doc.EnableControls;
      end;
end;

function TMov0029.RetTpConta: String;
begin
      Result := Copy(gVrParam,1,1); // (P)agar (R)eceber
end;

function TMov0029.RetTpOperac: String;
begin
      Result := Copy(gVrParam,2,1); // (B)aixar (C)ancelar
end;

procedure TMov0029.TmrOpenTimer(Sender: TObject);
begin
      inherited;
      TmrOpen.Enabled := False;

      if (Trim(gDsCapFor) = '') then
      begin
            gDsCapFor := Self.Caption;
      end;

      EdtCdEmpres.SetFocus;
end;

initialization
      RegisterClass(TMov0029);

finalization
      UnRegisterClass(TMov0029);

end.
