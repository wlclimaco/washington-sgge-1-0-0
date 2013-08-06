unit UMov0027;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  BrvRetCon, StdCtrls, BrvLabel, BrvEdit, Mask, BrvEditDate, ComCtrls, Grids, BrvDbGrids, BrvDbGrid,
  BrvAlertProgress, BrvBitBtn, DB, DBClient, BrvClientDataSet, BrvServerProgress, BrvCustomMaskEdit,
  BrvCustomEdit;

type
  TMov0027 = class(TMov0000)
    PnlFilter: TPanel;
    EdtCdEmpres: TBrvEdit;
    BrvLabel2: TBrvLabel;
    LblDsEmpres: TBrvRetCon;
    EdtDtInicio: TBrvEditDate;
    BrvLabel1: TBrvLabel;
    BrvLabel3: TBrvLabel;
    EdtDtFim: TBrvEditDate;
    BrvAlertProgress: TBrvAlertProgress;
    BtnBuscar: TBrvBitBtn;
    CcReceitas: TClientDataSet;
    CcReceitasNRDOC: TStringField;
    CcReceitasDSMODENF: TStringField;
    CcReceitasDTEMINOT: TDateField;
    CcReceitasSTNOTA: TStringField;
    CcReceitasCDPRODUT: TIntegerField;
    CcReceitasDSOBSERV: TStringField;
    CcReceitasSTLANCTO: TStringField;
    CcReceitasNRCCRREC: TIntegerField;
    CcReceitasNRCDBREC: TIntegerField;
    CcReceitasCDHISREC: TIntegerField;
    CcReceitasNRCCRPIS: TIntegerField;
    CcReceitasNRCDBPIS: TIntegerField;
    CcReceitasCDHISPIS: TIntegerField;
    CcReceitasNRCCRCOF: TIntegerField;
    CcReceitasNRCDBCOF: TIntegerField;
    CcReceitasCDHISCOF: TIntegerField;
    CcReceitasNRCCRICM: TIntegerField;
    CcReceitasNRCDBICM: TIntegerField;
    CcReceitasCDHISICM: TIntegerField;
    CcReceitasNRCCRISS: TIntegerField;
    CcReceitasNRCDBISS: TIntegerField;
    CcReceitasCDHISISS: TIntegerField;
    CcReceitasNRCCRISR: TIntegerField;
    CcReceitasNRCDBISR: TIntegerField;
    CcReceitasCDHISISR: TIntegerField;
    CcReceitasNRCCRICR: TIntegerField;
    CcReceitasNRCDBICR: TIntegerField;
    CcReceitasCDHISICR: TIntegerField;
    CcReceitasVRCONNOT: TFloatField;
    CcReceitasVRICMS: TFloatField;
    CcReceitasVRICMSRET: TFloatField;
    CcReceitasVRISSQN: TFloatField;
    CcReceitasVRISSRET: TFloatField;
    CcReceitasVRIMPPIS: TFloatField;
    CcReceitasVRIMPCOF: TFloatField;
    CcReceitasNRNOTMIN: TIntegerField;
    CcReceitasNRNOTMAX: TIntegerField;
    CcErros: TClientDataSet;
    CcErrosDsLinha: TStringField;
    DtsErros: TDataSource;
    DtsReceitas: TDataSource;
    CcNotFisFat: TBrvClientDataSet;
    CcNotFisCan: TBrvClientDataSet;
    CcConfProdCan: TBrvClientDataSet;
    PnlDados: TPanel;
    PgcLancto: TPageControl;
    TbsLanctos: TTabSheet;
    DbgReceitas: TBrvDbGrid;
    TbsTotais: TTabSheet;
    Shape7: TShape;
    Shape4: TShape;
    Label28: TLabel;
    Label5: TLabel;
    Label4: TLabel;
    LblRegRec: TLabel;
    LblValorRec: TLabel;
    Shape5: TShape;
    Label29: TLabel;
    Label10: TLabel;
    Label9: TLabel;
    LblRegCan: TLabel;
    LblValorCan: TLabel;
    Shape6: TShape;
    Label30: TLabel;
    Label8: TLabel;
    Label7: TLabel;
    LblRegTT: TLabel;
    LblValorTT: TLabel;
    Shape8: TShape;
    Shape2: TShape;
    Shape3: TShape;
    Shape9: TShape;
    Label6: TLabel;
    Label12: TLabel;
    LblRecPIS: TLabel;
    LblCanPIS: TLabel;
    Label14: TLabel;
    Label11: TLabel;
    Label13: TLabel;
    Label15: TLabel;
    LblCanCOFINS: TLabel;
    LblRecCOFINS: TLabel;
    Label16: TLabel;
    Label19: TLabel;
    Label17: TLabel;
    Label18: TLabel;
    Label20: TLabel;
    Label22: TLabel;
    LblRecISSQn: TLabel;
    LblCanISSQn: TLabel;
    LblCanISSRet: TLabel;
    LblRecISSRet: TLabel;
    Label21: TLabel;
    Label25: TLabel;
    Label23: TLabel;
    Label24: TLabel;
    Label26: TLabel;
    Label27: TLabel;
    LblRecICMS: TLabel;
    LblCanICMS: TLabel;
    LblRecICMSRet: TLabel;
    LblCanICMSRet: TLabel;
    TbsErros: TTabSheet;
    BrvDbGrid1: TBrvDbGrid;
    PnlGravar: TPanel;
    BtnGravar: TBrvBitBtn;
    BtnCancelar: TBrvBitBtn;
    CcReceitasNRPLANO: TIntegerField;
    CpB002: TBrvClientDataSet;
    CcB004: TBrvClientDataSet;
    CcB008: TBrvClientDataSet;
    CpG008: TClientDataSet;
    BrvServerProgress: TBrvServerProgress;
    procedure BtnBuscarClick(Sender: TObject);
    procedure CcNotFisFatBeforeDelete(DataSet: TDataSet);
    procedure CcNotFisFatBeforeInsert(DataSet: TDataSet);
    procedure DbgReceitasDrawColumnCell(Sender: TObject; const Rect: TRect; DataCol: Integer;
                                                            Column: TColumn; State: TGridDrawState);
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BtnGravarClick(Sender: TObject);
    procedure BtnCancelarClick(Sender: TObject);
  private
    procedure BuscarDados;
    procedure VerificarEntradas;
    procedure PreparaDataSets;
    procedure PreparaExibicaoGrid;
    procedure InsereRegistro(pCcOrigem, pCcDestin: TClientDataSet; pSnCancel: Boolean);
    function VerificaConfiguracaoProduto(pCcConf: TClientDataSet): Boolean;
    procedure MoveParametros(pCcOrigem, pCcDestin: TClientDataSet);
    function Classificacao(pNrPlano, pNrConta: Integer): String;
    procedure IniciarLancamentos;
    procedure LancarContabilidade(pNrLancto, pNrPlano, pCdEmpres: Integer; pDtEmiNot: TDate;
             pNrConDeb, pNrConCre: Integer; pVrConNot: Extended; pNrDoc: String; pCdHisRec: Integer);
    function DescricaoHistorico(pCdHistor: Integer): String;
    procedure GravarLanctos;
    { Private declarations }
  public
    { Public declarations }
    gCdProMun    : String;
    gVrRecPIS    : Extended;
    gVrCanPIS    : Extended;
    gVrRecCOFINS : Extended;
    gVrCanCOFINS : Extended;
    gVrRecISSRet : Extended;
    gVrCanISSRet : Extended;
    gVrRecISSQn  : Extended;
    gVrCanISSQn  : Extended;
    gVrRecICMS   : Extended;
    gVrCanICMS   : Extended;
    gVrRecICMSRet: Extended;
    gVrCanICMSRet: Extended;
  end;

var
  Mov0027: TMov0027;

implementation

uses UDmSrvApl, UClaSrv;

{$R *.dfm}


procedure TMov0027.VerificarEntradas;
begin
      if (StrToIntDef(EdtCdEmpres.Text, 0) = 0) then
      begin
            EdtCdEmpres.SetFocus;
            raise Exception.Create('Informar o código da empresa');
      end;

      if (not EdtDtInicio.BrDataValida) then
      begin
            EdtDtInicio.SetFocus;
            raise Exception.Create('Informar a data inicial');
      end;

      if (not EdtDtFim.BrDataValida) then
      begin
            EdtDtFim.SetFocus;
            raise Exception.Create('Informar a data final');
      end;

      if (EdtDtFim.BrAsDate < EdtDtInicio.BrAsDate) then
      begin
            EdtDtFim.SetFocus;
            raise Exception.Create('Data final menor do que data inicial');
      end;

      PnlFilter.Enabled := False;
end;

procedure TMov0027.PreparaDataSets;
begin
      if (CpG008.Active) then
      begin
            CpG008.EmptyDataSet;
            CpG008.Close;
      end;

      if (CcReceitas.Active) then
      begin
            CcReceitas.EmptyDataSet;
            CcReceitas.Close;
      end;

      if (CcErros.Active) then
      begin
            CcErros.EmptyDataSet;
            CcErros.Close;
      end;

      CcErros.CreateDataSet;
      CcReceitas.CreateDataSet;

      CpG008.FieldDefs.Clear;
      CpG008.FieldDefs.Add('CdHistor', ftInteger, 0);
      CpG008.FieldDefs.Add('DsHistor', ftString, 50);
      CpG008.CreateDataSet;
end;

procedure TMov0027.PreparaExibicaoGrid;
var lDsDomini: String;
    lVrDomini: String;
begin
      CcReceitasNRDOC.DisplayLabel    := 'Documento';
      CcReceitasDSMODENF.DisplayLabel := 'Modelo';
      CcReceitasDTEMINOT.DisplayLabel := 'Emissão';
      CcReceitasSTNOTA.DisplayLabel   := 'Situação';
      CcReceitasDSOBSERV.DisplayLabel := 'Observação';
      CcReceitasVRCONNOT.DisplayLabel := 'Vr. Contábil';

      DmSrvApl.BrvDicionario.CarregarDominiosDoAtributo('F004', 'STNOTA', lVrDomini, lDsDomini);

      DbgReceitas.Columns[3].PickList.Text    := lDsDomini;
      DbgReceitas.Columns[3].BrPickValue.Text := lVrDomini;
      DbgReceitas.Columns[3].Width            := 60;
end;

function TMov0027.VerificaConfiguracaoProduto(pCcConf : TClientDataSet): Boolean;
var lDsLinha: String;
begin
      Result := True;

      if (pCcConf.FieldByName('NRCCRREC').AsInteger = 0) or
         (pCcConf.FieldByName('NRCDBREC').AsInteger = 0) or
         (pCcConf.FieldByName('CDHISREC').AsInteger = 0) or
         (pCcConf.FieldByName('NRCCRPIS').AsInteger = 0) or
         (pCcConf.FieldByName('NRCDBPIS').AsInteger = 0) or
         (pCcConf.FieldByName('CDHISPIS').AsInteger = 0) or
         (pCcConf.FieldByName('NRCCRCOF').AsInteger = 0) or
         (pCcConf.FieldByName('NRCDBCOF').AsInteger = 0) or
         (pCcConf.FieldByName('CDHISCOF').AsInteger = 0) or
         (pCcConf.FieldByName('NRCCRICM').AsInteger = 0) or
         (pCcConf.FieldByName('NRCDBICM').AsInteger = 0) or
         (pCcConf.FieldByName('CDHISICM').AsInteger = 0) or
         (pCcConf.FieldByName('NRCCRISS').AsInteger = 0) or
         (pCcConf.FieldByName('NRCDBISS').AsInteger = 0) or
         (pCcConf.FieldByName('CDHISISS').AsInteger = 0) or
         (pCcConf.FieldByName('NRCCRISR').AsInteger = 0) or
         (pCcConf.FieldByName('NRCDBISR').AsInteger = 0) or
         (pCcConf.FieldByName('CDHISISR').AsInteger = 0) or
         (pCcConf.FieldByName('NRCCRICR').AsInteger = 0) or
         (pCcConf.FieldByName('NRCDBICR').AsInteger = 0) or
         (pCcConf.FieldByName('CDHISICR').AsInteger = 0) then
      begin
            Result := False;

            PnlGravar.Visible := False;

            lDsLinha := 'Verificar configuração Contas\Históricos para ' +
                         pCcConf.FieldByName('CDPRODUT').AsString + ' ' +
                         pCcConf.FieldByName('DsObserv').AsString;

            if (Not CcErros.Locate('DsLinha', lDsLinha, [loCaseInsensitive])) then
            begin
                  CcErros.Append;
                  CcErrosDsLinha.AsString := lDsLinha;
                  CcErros.Post;
            end;

      end;
end;

procedure TMov0027.MoveParametros(pCcOrigem, pCcDestin: TClientDataSet);
begin
      pCcDestin.Edit;
      pCcDestin.FieldByName('DSOBSERV').AsString  := pCcOrigem.FieldByName('DSOBSERV').AsString;
      pCcDestin.FieldByName('NRCCRREC').AsInteger := pCcOrigem.FieldByName('NRCCRREC').AsInteger;
      pCcDestin.FieldByName('NRCDBREC').AsInteger := pCcOrigem.FieldByName('NRCDBREC').AsInteger;
      pCcDestin.FieldByName('CDHISREC').AsInteger := pCcOrigem.FieldByName('CDHISREC').AsInteger;
      pCcDestin.FieldByName('NRCCRPIS').AsInteger := pCcOrigem.FieldByName('NRCCRPIS').AsInteger;
      pCcDestin.FieldByName('NRCDBPIS').AsInteger := pCcOrigem.FieldByName('NRCDBPIS').AsInteger;
      pCcDestin.FieldByName('CDHISPIS').AsInteger := pCcOrigem.FieldByName('CDHISPIS').AsInteger;
      pCcDestin.FieldByName('NRCCRCOF').AsInteger := pCcOrigem.FieldByName('NRCCRCOF').AsInteger;
      pCcDestin.FieldByName('NRCDBCOF').AsInteger := pCcOrigem.FieldByName('NRCDBCOF').AsInteger;
      pCcDestin.FieldByName('CDHISCOF').AsInteger := pCcOrigem.FieldByName('CDHISCOF').AsInteger;
      pCcDestin.FieldByName('NRCCRICM').AsInteger := pCcOrigem.FieldByName('NRCCRICM').AsInteger;
      pCcDestin.FieldByName('NRCDBICM').AsInteger := pCcOrigem.FieldByName('NRCDBICM').AsInteger;
      pCcDestin.FieldByName('CDHISICM').AsInteger := pCcOrigem.FieldByName('CDHISICM').AsInteger;
      pCcDestin.FieldByName('NRCCRISS').AsInteger := pCcOrigem.FieldByName('NRCCRISS').AsInteger;
      pCcDestin.FieldByName('NRCDBISS').AsInteger := pCcOrigem.FieldByName('NRCDBISS').AsInteger;
      pCcDestin.FieldByName('CDHISISS').AsInteger := pCcOrigem.FieldByName('CDHISISS').AsInteger;
      pCcDestin.FieldByName('NRCCRISR').AsInteger := pCcOrigem.FieldByName('NRCCRISR').AsInteger;
      pCcDestin.FieldByName('NRCDBISR').AsInteger := pCcOrigem.FieldByName('NRCDBISR').AsInteger;
      pCcDestin.FieldByName('CDHISISR').AsInteger := pCcOrigem.FieldByName('CDHISISR').AsInteger;
      pCcDestin.FieldByName('NRCCRICR').AsInteger := pCcOrigem.FieldByName('NRCCRICR').AsInteger;
      pCcDestin.FieldByName('NRCDBICR').AsInteger := pCcOrigem.FieldByName('NRCDBICR').AsInteger;
      pCcDestin.FieldByName('CDHISICR').AsInteger := pCcOrigem.FieldByName('CDHISICR').AsInteger;
      pCcDestin.FieldByName('NrPlano' ).AsInteger := pCcOrigem.FieldByName('NrPlano' ).AsInteger;
      pCcDestin.Post;
end;

procedure TMov0027.BtnCancelarClick(Sender: TObject);
begin
      inherited;
      PnlDados.Visible  := False;
      BtnGravar.Visible := True;
      PnlFilter.Enabled := True;
end;

function TMov0027.DescricaoHistorico(pCdHistor: Integer): String;
begin
      if (CpG008.Locate('CdHistor', pCdHistor, [loCaseInsensitive])) then
      begin
            Result := Trim(CpG008.FieldByName('DsHistor').AsString);
      end else
      begin
            Result := Trim(DmSrvApl.BrvDicionario.RetornaValorColunaTabela('G008', 'DsHistor',
                                                          'CdHistor', FormatFloat('0', pCdHistor)));
            CpG008.Append;
            CpG008.FieldByName('CdHistor').AsInteger := pCdHistor;
            CpG008.FieldByName('DsHistor').AsString  := Result;
            CpG008.Post;
      end;
end;

function TMov0027.Classificacao(pNrPlano, pNrConta : Integer): String;
var lCpB002 : TClientDataSet;
begin
      if not CpB002.Active then
      begin
            CpB002.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(213,
                                                    '<%NrPlano%>;' + IntToStr(pNrPlano) + #13 +
                                                    '<%NrConta%>;' + IntToStr(pNrConta), Self.Name);
            Result := CpB002.FieldByName('NrClassi').AsString;
      end else
      begin
            if CpB002.Locate('NrPlano;NrConta', VarArrayOf([pNrPlano, pNrConta]),
                                                                           [loCaseInsensitive]) then
            begin
                  Result := CpB002.FieldByName('NrClassi').AsString;
            end else
            begin
                  try
                      lCpB002 := TClientDataSet.Create(Self);
                      lCpB002.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(213,
                                                    '<%NrPlano%>;' + IntToStr(pNrPlano) + #13 +
                                                    '<%NrConta%>;' + IntToStr(pNrConta), Self.Name);
                      Result := lCpB002.FieldByName('NrClassi').AsString;

                      CpB002.Append;
                      CpB002.FieldByName('NrPlano' ).AsInteger :=
                                                          lCpB002.FieldByName('NrPlano' ).AsInteger;
                      CpB002.FieldByName('NrConta' ).AsInteger :=
                                                          lCpB002.FieldByName('NrConta' ).AsInteger;
                      CpB002.FieldByName('NrClassi').AsString  :=
                                                          lCpB002.FieldByName('NrClassi').AsString ;
                      CpB002.Post;
                  finally
                      FreeAndNil(lCpB002);
                  end;
            end;
      end;
end;

procedure TMov0027.IniciarLancamentos;
begin
      if (CcB004.Active) then
      begin
            CcB004.EmptyDataSet;
            CcB004.Close;
      end;

      CcB004.FieldDefs.Clear;
      CcB004.FieldDefs.Add('nrLancto', ftinteger , 0);
      CcB004.FieldDefs.Add('nrplano' , ftinteger , 0);
      CcB004.FieldDefs.Add('CdEmpres', ftinteger , 0);
      CcB004.FieldDefs.Add('dtlancto', ftDate    , 0);
      CcB004.FieldDefs.Add('nrcondeb', ftinteger , 0);
      CcB004.FieldDefs.Add('nrconcre', ftinteger , 0);
      CcB004.FieldDefs.Add('vrlancto', ftFloat   , 0);
      CcB004.FieldDefs.Add('nrdocto' , ftString  ,30);
      CcB004.FieldDefs.Add('cdhistor', ftinteger , 0);
      CcB004.FieldDefs.Add('dshistor', ftString  ,50);
      CcB004.FieldDefs.Add('nmformul', ftstring  ,20);
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

procedure TMov0027.LancarContabilidade(pNrLancto : Integer;  pNrPlano  : Integer;
                                       pCdEmpres : Integer;  pDtEmiNot : TDate;
                                       pNrConDeb : Integer;  pNrConCre : Integer;
                                       pVrConNot : Extended; pNrDoc    : String;
                                       pCdHisRec : Integer);
begin
      CcB004.Append;
      CcB004.FieldByName('nrLancto').Asinteger := pNrLancto;
      CcB004.FieldByName('nrplano' ).Asinteger := pNrPlano;
      CcB004.FieldByName('CdEmpres').Asinteger := pCdEmpres;
      CcB004.FieldByName('dtlancto').AsDateTime:= pDtEmiNot;
      CcB004.FieldByName('nrcondeb').Asinteger := pNrConDeb;
      CcB004.FieldByName('nrconcre').Asinteger := pNrConCre;
      CcB004.FieldByName('vrlancto').AsFloat   := pVrConNot;
      CcB004.FieldByName('nrdocto' ).AsString  := pNrDoc;
      CcB004.FieldByName('cdhistor').Asinteger := pCdHisRec;
      CcB004.FieldByName('dshistor').AsString  := DescricaoHistorico(pCdHisRec);
      CcB004.FieldByName('nmformul').Asstring  := Self.Name;
      CcB004.FieldByName('SnEncerr').Asstring  := 'N';
      CcB004.FieldByName('NrClaCre').Asstring  := Classificacao(pNrPlano, pNrConCre);
      CcB004.FieldByName('NrClaDeb').Asstring  := Classificacao(pNrPlano, pNrConDeb);
      CcB004.Post;
end;

procedure TMov0027.GravarLanctos;
var lNrLancto : Integer;
    lCpParCon : TClientDataSet;
    lConexao  : TSDmAdmClient;
    lDsResult : AnsiString;
begin
      inherited;
      IniciarLancamentos;
      lNrLancto := 1;

      BrvAlertProgress.BrCaption  := Self.Caption;
      BrvAlertProgress.BrProcesso := 'Reunindo informações...';
      BrvAlertProgress.ShowAlert;
      BrvAlertProgress.BrQuery    := CcReceitas;

      CcReceitas.First;

      try
          CcReceitas.DisableControls;
          while not CcReceitas.Eof do
          begin
                //Receita
                LancarContabilidade(lNrLancto,
                                    CcReceitas.FieldByName('NrPlano').AsInteger,
                                    StrToInt(EdtCdEmpres.Text),
                                    CcReceitas.FieldByName('DtEmiNot').AsDateTime,
                                    CcReceitas.FieldByName('NrCdbRec').AsInteger,
                                    CcReceitas.FieldByName('NrCcrRec').AsInteger,
                                    CcReceitas.FieldByName('VrConNot').AsFloat,
                                    CcReceitas.FieldByName('NrDoc'   ).AsString,
                                    CcReceitas.FieldByName('CdHisRec').AsInteger);
                Inc(lNrLancto);

                //--Fase Um.

                if (CcReceitas.FieldByName('VrImpPIS').AsFloat > 0) then
                begin
                      //Lançamento PIS
                      LancarContabilidade(lNrLancto,
                                          CcReceitas.FieldByName('NrPlano').AsInteger,
                                          StrToInt(EdtCdEmpres.Text),
                                          CcReceitas.FieldByName('DtEmiNot').AsDateTime,
                                          CcReceitas.FieldByName('NRCDBPIS').AsInteger,
                                          CcReceitas.FieldByName('NRCCRPIS').AsInteger,
                                          CcReceitas.FieldByName('VrImpPIS').AsFloat,
                                          CcReceitas.FieldByName('NrDoc'   ).AsString,
                                          CcReceitas.FieldByName('CdHisPIS').AsInteger);
                      inc(lNrLancto);
                end;

                if (CcReceitas.FieldByName('VrImpCOF').AsFloat > 0) then
                begin
                      //Lançamento COFINS
                      LancarContabilidade(lNrLancto,
                                          CcReceitas.FieldByName('NrPlano').AsInteger,
                                          StrToInt(EdtCdEmpres.Text),
                                          CcReceitas.FieldByName('DtEmiNot').AsDateTime,
                                          CcReceitas.FieldByName('NRCDBCOF').AsInteger,
                                          CcReceitas.FieldByName('NRCCRCOF').AsInteger,
                                          CcReceitas.FieldByName('VrImpCOF').AsFloat,
                                          CcReceitas.FieldByName('NrDoc'   ).AsString,
                                          CcReceitas.FieldByName('CdHisCOF').AsInteger);
                      inc(lNrLancto);
                end;

                if (CcReceitas.FieldByName('VrISSQn').AsFloat > 0) then
                begin
                      //Lançamento ISS
                      LancarContabilidade(lNrLancto,
                                          CcReceitas.FieldByName('NrPlano').AsInteger,
                                          StrToInt(EdtCdEmpres.Text),
                                          CcReceitas.FieldByName('DtEmiNot').AsDateTime,
                                          CcReceitas.FieldByName('NRCDBISS').AsInteger,
                                          CcReceitas.FieldByName('NRCCRISS').AsInteger,
                                          CcReceitas.FieldByName('VrISSQn' ).AsFloat,
                                          CcReceitas.FieldByName('NrDoc'   ).AsString,
                                          CcReceitas.FieldByName('CdHisISS').AsInteger);
                      inc(lNrLancto);
                end;

                if (CcReceitas.FieldByName('VrICMS').AsFloat > 0) then
                begin
                      //Lançamento de ICMS
                      LancarContabilidade(lNrLancto,
                                          CcReceitas.FieldByName('NrPlano').AsInteger,
                                          StrToInt(EdtCdEmpres.Text),
                                          CcReceitas.FieldByName('DtEmiNot').AsDateTime,
                                          CcReceitas.FieldByName('NRCDBICM').AsInteger,
                                          CcReceitas.FieldByName('NRCCRICM').AsInteger,
                                          CcReceitas.FieldByName('VrICMS'  ).AsFloat,
                                          CcReceitas.FieldByName('NrDoc'   ).AsString,
                                          CcReceitas.FieldByName('CdHisICM').AsInteger);
                      inc(lNrLancto);
                end;

                //--Fase dois.

                if (CcReceitas.FieldByName('VrIssRet').AsFloat > 0) then
                begin
                      //Lançamento ISS Retido
                      LancarContabilidade(lNrLancto,
                                          CcReceitas.FieldByName('NrPlano').AsInteger,
                                          StrToInt(EdtCdEmpres.Text),
                                          CcReceitas.FieldByName('DtEmiNot').AsDateTime,
                                          CcReceitas.FieldByName('NRCDBISR').AsInteger,
                                          CcReceitas.FieldByName('NRCCRISR').AsInteger,
                                          CcReceitas.FieldByName('VrIssRet').AsFloat,
                                          CcReceitas.FieldByName('NrDoc'   ).AsString,
                                          CcReceitas.FieldByName('CdHisISR').AsInteger);
                      inc(lNrLancto);
                end;

                if (CcReceitas.FieldByName('VrIcmsRet').AsFloat > 0) then
                begin
                      //Lançamento ICMS Retido
                      LancarContabilidade(lNrLancto,
                                          CcReceitas.FieldByName('NrPlano').AsInteger,
                                          StrToInt(EdtCdEmpres.Text),
                                          CcReceitas.FieldByName('DtEmiNot' ).AsDateTime,
                                          CcReceitas.FieldByName('NRCDBICR' ).AsInteger,
                                          CcReceitas.FieldByName('NRCCRICR' ).AsInteger,
                                          CcReceitas.FieldByName('VrIcmsRet').AsFloat,
                                          CcReceitas.FieldByName('NrDoc'    ).AsString,
                                          CcReceitas.FieldByName('CdHisICR' ).AsInteger);
                      inc(lNrLancto);
                end;

                CcReceitas.Next;
                BrvAlertProgress.BrStepIt();
          end;
      finally
          CcReceitas.First;
          CcReceitas.EnableControls;
      end;

      lCpParCon := TClientDataSet.Create(Self);

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
      lCpParCon.FieldByName('NrPlano' ).AsString := DmSrvApl.BrvDicionario.RetornaValorColunaTabela(
                                                   'B013', 'NrPlano', 'CdEmpres', EdtCdEmpres.Text);
      lCpParCon.Post;

      try
          try
              BrvServerProgress.Start(Self.Name, 'Lançando receitas...', 100, 10);
              lConexao  := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
              lDsResult := lConexao.EfetuarLancamentosContabeis(DmSrvApl.BrvDicionario.Credencial,
                                                                                    lCpParCon.Data);
          finally
              BrvServerProgress.Stop;
          end;

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

          MessageDlg('Lançamentos efetuados com sucesso!', mtInformation, [mbOk], 0);
      finally
          FreeAndNil(lCpParCon);
          FreeAndNil(lConexao);
      end;

      PnlDados.Visible  := false;
      PnlFilter.Enabled := True;

      EdtCdEmpres.SetFocus;
end;

procedure TMov0027.BtnGravarClick(Sender: TObject);
begin
      if (CcReceitas.RecordCount > 0) then
      begin
            if (MessageDlg('Deseja realmetne continuar?', mtConfirmation,
                                                                     [mbyes, mbno], 0) = mrYes) then
            begin
                  GravarLanctos;
            end else
            begin
                  MessageDlg('Operação cancelada!', mtInformation, [mbok], 0);
            end;
      end else
      begin
            MessageDlg('Não existem lançamentos!', mtWarning, [mbok], 0);
      end;
end;

procedure TMov0027.BuscarDados;
var lVrLanRec : Extended;
    lVrLanCan : Extended;
begin
      inherited;
      VerificarEntradas;

      //Produto utilizado para transportes municipais
      gCdProMun := DmSrvApl.BrvDicionario.ParametroDaEmpresa(29, StrToInt(EdtCdEmpres.Text));

      PreparaDataSets;

      CcReceitas.BeforeInsert := Nil;
      DbgReceitas.Columns.Clear;

      //Busca documentos fiscais faturados ou digitados
      CcNotFisFat.Close;
      CcNotFisFat.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(
                                              209 ,     '<%CdEmpres%>;' + EdtCdEmpres.Text +
                                              Chr(13) + '<%DtIniNot%>;' + EdtDtInicio.BrAsDataSQL +
                                              Chr(13) + '<%DtFimNot%>;' + EdtDtFim.BrAsDataSQL,
                                              Self.Name);
      CcNotFisFat.Open;

      //Verificar
      //Busca documentos fiscais cancelados
      CcNotFisCan.Close;
      CcNotFisCan.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(
                                              210,     '<%CdEmpres%>;' + EdtCdEmpres.Text +
                                              Chr(13) + '<%DtIniNot%>;' + EdtDtInicio.BrAsDataSQL +
                                              Chr(13) + '<%DtFimNot%>;' + EdtDtFim.BrAsDataSQL,
                                              Self.Name);
      CcNotFisCan.Open;

      lVrLanRec := 0;
      lVrLanCan := 0;
      gVrRecPIS := 0;
      gVrCanPIS := 0;
      gVrRecCOFINS := 0;
      gVrCanCOFINS := 0;
      gVrRecISSRet := 0;
      gVrCanISSRet := 0;
      gVrRecISSQn  := 0;
      gVrCanISSQn  := 0;
      gVrRecICMS   := 0;
      gVrCanICMS   := 0;

      PreparaExibicaoGrid;

      if (CcNotFisFat.RecordCount > 0) then
      begin

            BrvAlertProgress.BrCaption  := Self.Caption;
            BrvAlertProgress.BrProcesso := 'Reunindo informações...';
            BrvAlertProgress.ShowAlert;
            BrvAlertProgress.BrQuery    := CcNotFisFat;

            CcNotFisFat.First;

            while not CcNotFisFat.Eof do
            begin
                  InsereRegistro(CcNotFisFat, CcReceitas, False);
                  BrvAlertProgress.BrStepIt();

                  if (CcNotFisFat.FieldByName('CDPRODUT').AsInteger <> 0) then
                  begin
                         if (Not VerificaConfiguracaoProduto(CcNotFisFat)) then
                         begin
                               CcReceitas.Edit;
                               CcReceitasSTLANCTO.AsString := 'E';
                               CcReceitas.Post;
                         end;
                  end else
                  begin
                        //Verificar
                        CcConfProdCan.Close;
                        CcConfProdCan.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(212,
                                                          '<%CdEmpres%>;' + EdtCdEmpres.Text + #13 +
                                                          '<%CdProdut%>;' + gCdProMun, Self.Name);
                        CcConfProdCan.Open;

                        if (CcConfProdCan.RecordCount > 0) then
                        begin
                              if (Not VerificaConfiguracaoProduto(CcConfProdCan)) then
                              begin
                                    CcReceitas.Edit;
                                    CcReceitasSTLANCTO.AsString := 'E';
                                    CcReceitas.Post;
                              end else
                              begin
                                    MoveParametros(CcConfProdCan, CcReceitas);
                              end;
                        end else
                        begin
                              if (Not CcErros.Locate('DsLinha',
                                                          'Verificar configuração do produto ' +
                                                           gCdProMun + ' em "Parametros Contabeis"',
                                                                          [loCaseInsensitive])) then
                              begin
                                    CcErros.Append;
                                    CcErrosDsLinha.AsString :=
                                                           'Verificar configuração do produto ' +
                                                           gCdProMun + ' em "Parametros Contabeis"';
                                    CcErros.Post;
                              end;

                              CcReceitas.Edit;
                              CcReceitasSTLANCTO.AsString := 'E';
                              CcReceitas.Post;
                        end;
                  end;

                  lVrLanRec := lVrLanRec + CcNotFisFat.FieldByName('VrConNot').Value;
                  CcNotFisFat.Next;
            end;
      end;

      //Ajustando números de documentos
      CcReceitas.First;
      while not CcReceitas.Eof do
      begin
            CcReceitas.Edit;
            CcReceitas.FieldByName('NRDOC').AsString := CcReceitas.FieldByName('NrNotMin').AsString
                                                + '-' + CcReceitas.FieldByName('NrNotMax').AsString;
            CcReceitas.Post;

            CcReceitas.Next;
      end;

      if (CcNotFisCan.RecordCount > 0) then
      begin
            BrvAlertProgress.BrCaption  := Self.Caption;
            BrvAlertProgress.BrProcesso := 'Localizando Cancelamentos...';
            BrvAlertProgress.ShowAlert;
            BrvAlertProgress.BrQuery    := CcNotFisCan;

            CcNotFisCan.First;

            while not CcNotFisCan.Eof do
            begin
                  InsereRegistro(CcNotFisCan, CcReceitas, True);
                  BrvAlertProgress.BrStepIt();

                  if (CcNotFisCan.FieldByName('CDPRODUT').AsInteger <> 0) then
                  begin
                         if (Not VerificaConfiguracaoProduto(CcNotFisCan)) then
                         begin
                               CcReceitas.Edit;
                               CcReceitasSTLANCTO.AsString := 'E';
                               CcReceitas.Post;
                         end;
                  end else
                  begin
                        //Verificar
                        CcConfProdCan.Close;
                        CcConfProdCan.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(212,
                                                          '<%CdEmpres%>;' + EdtCdEmpres.Text + #13 +
                                                          '<%CdProdut%>;' + gCdProMun,
                                                          Self.Name);
                        CcConfProdCan.Open;

                        if (CcConfProdCan.RecordCount > 0) then
                        begin
                              if (Not VerificaConfiguracaoProduto(CcConfProdCan)) then
                              begin
                                    CcReceitas.Edit;
                                    CcReceitasSTLANCTO.AsString := 'E';
                                    CcReceitas.Post;
                              end else
                              begin
                                    MoveParametros(CcConfProdCan, CcReceitas);
                              end;
                        end else
                        begin
                              if (Not CcErros.Locate('DsLinha',
                                                           'Verificar configuração do produto ' +
                                                           gCdProMun + ' em "Parametros Contabeis"',
                                                           [loCaseInsensitive])) then
                              begin
                                    CcErros.Append;
                                    CcErrosDsLinha.AsString :=
                                                           'Verificar configuração do produto ' +
                                                           gCdProMun + ' em "Parametros Contabeis"';
                                    CcErros.Post;
                              end;

                              CcReceitas.Edit;
                              CcReceitasSTLANCTO.AsString := 'E';
                              CcReceitas.Post;
                        end;
                  end;

                  lVrLanCan := lVrLanCan + CcNotFisCan.FieldByName('VrConNot').Value;

                  CcNotFisCan.Next;
            end;
      end;

      LblValorCan.Caption  := FormatFloat('###,###,##0.00', lVrLanCan);
      LblValorRec.Caption  := FormatFloat('###,###,##0.00', lVrLanRec);
      LblValorTT.Caption   := FormatFloat('###,###,##0.00', lVrLanCan + lVrLanRec);

      LblRecPIS.Caption    := FormatFloat('###,###,##0.00', gVrRecPIS);
      LblCanPIS.Caption    := FormatFloat('###,###,##0.00', gVrCanPIS);
      LblRecCOFINS.Caption := FormatFloat('###,###,##0.00', gVrRecCOFINS);
      LblCanCOFINS.Caption := FormatFloat('###,###,##0.00', gVrCanCOFINS);

      LblRecISSRet.Caption := FormatFloat('###,###,##0.00', gVrRecISSRet);
      LblCanISSRet.Caption := FormatFloat('###,###,##0.00', gVrCanISSRet);

      LblRecISSQn.Caption  := FormatFloat('###,###,##0.00', gVrRecISSQn);
      LblCanISSQn.Caption  := FormatFloat('###,###,##0.00', gVrCanISSQn);

      LblRecICMS.Caption   := FormatFloat('###,###,##0.00', gVrRecICMS);
      LblCanICMS.Caption   := FormatFloat('###,###,##0.00', gVrCanICMS);

      LblRecICMSRet.Caption:= FormatFloat('###,###,##0.00', gVrRecICMSRet);
      LblCanICMSRet.Caption:= FormatFloat('###,###,##0.00', gVrCanICMSRet);

      LblRegCan.Caption    := FormatFloat('0', CcNotFisCan.RecordCount);
      LblRegRec.Caption    := FormatFloat('0', CcNotFisFat.RecordCount);
      LblRegTT.Caption     := FormatFloat('0', CcNotFisCan.RecordCount + CcNotFisFat.RecordCount);

      CcNotFisCan.Close;
      CcNotFisFat.Close;

      CcReceitas.BeforeInsert := CcNotFisFatBeforeInsert;
      CcReceitas.IndexDefs.Clear;
      CcReceitas.IndexDefs.Add('IdxNotAux', 'DtEmiNot', [ixCaseInsensitive]);
      CcReceitas.IndexName := 'IdxNotAux';
      CcReceitas.First;

      if (CcErros.RecordCount > 0) then
      begin
            MessageDlg('O processo encontrou  ' + FormatFloat('0', CcErros.RecordCount) +
                       '  Inconsistencia(s)!!! '+ Chr(13) + 'Verifique...',
                       mtWarning, [mbOK], 0);

            BtnGravar.Visible    := False;
            PnlGravar.Visible    := True;
            PgcLancto.ActivePage := TbsErros;

      end else
      begin
            PgcLancto.ActivePage := TbsLanctos;
      end;
end;

procedure TMov0027.CcNotFisFatBeforeInsert(DataSet: TDataSet);
begin
      inherited;
      Abort;
end;

procedure TMov0027.DbgReceitasDrawColumnCell(Sender: TObject; const Rect: TRect; DataCol: Integer;
  Column: TColumn; State: TGridDrawState);
begin
      inherited;
      if ((CcReceitas.FieldByName('STLANCTO').AsString = 'E') and
                                                               (Column.FieldName = 'DSOBSERV')) then
      begin
            DbgReceitas.Canvas.Font.Color := clRed;
            DbgReceitas.Canvas.FillRect(Rect);
            DbgReceitas.DefaultDrawDataCell(Rect, Column.Field, State);
      end;
end;

procedure TMov0027.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TMov0027.CcNotFisFatBeforeDelete(DataSet: TDataSet);
begin
      inherited;
      Abort;
end;

procedure TMov0027.InsereRegistro(pCcOrigem, pCcDestin: TClientDataSet; pSnCancel: Boolean);
begin
      if (pCcDestin.Locate('DSMODENF;DTEMINOT;STNOTA;CDPRODUT',
          VarArrayOf([pCcOrigem.FieldByName('DSMODENF').AsString,
                      pCcOrigem.FieldByName('DTEMINOT').AsDateTime,
                      pCcOrigem.FieldByName('STNOTA'  ).AsString,
                      pCcOrigem.FieldByName('CDPRODUT').AsInteger]), [loCaseInsensitive])) then
      begin
            pCcDestin.Edit;

            if (pCcOrigem.FieldByName('NrNota').AsInteger <
                                                   pCcDestin.FieldByName('NRNOTMIN').AsInteger) then
            begin
                  pCcDestin.FieldByName('NRNOTMIN').AsString   :=
                                                           pCcOrigem.FieldByName('NrNota').AsString;
            end;

            if (pCcOrigem.FieldByName('NrNota').AsInteger >
                                                   pCcDestin.FieldByName('NRNOTMAX').AsInteger) then
            begin
                  pCcDestin.FieldByName('NRNOTMAX').AsString   :=
                                                           pCcOrigem.FieldByName('NrNota').AsString;
            end;

            pCcDestin.FieldByName('DSMODENF').AsString   :=
                                                       pCcOrigem.FieldByName('DSMODENF').AsString;
            pCcDestin.FieldByName('DTEMINOT').AsDateTime :=
                                                       pCcOrigem.FieldByName('DTEMINOT').AsDateTime;
            pCcDestin.FieldByName('STNOTA'  ).AsString   :=
                                                       pCcOrigem.FieldByName('STNOTA'  ).AsString;
            pCcDestin.FieldByName('CDPRODUT').AsInteger  :=
                                                       pCcOrigem.FieldByName('CDPRODUT').AsInteger;
            pCcDestin.FieldByName('DSOBSERV').AsString   :=
                                                       pCcOrigem.FieldByName('DSOBSERV').AsString;
            pCcDestin.FieldByName('STLANCTO').AsString   :=
                                                       pCcOrigem.FieldByName('STLANCTO').AsString;
            pCcDestin.FieldByName('NRPLANO').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRPLANO').AsInteger;
            pCcDestin.FieldByName('NRCCRREC').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCCRREC').AsInteger;
            pCcDestin.FieldByName('NRCDBREC').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCDBREC').AsInteger;
            pCcDestin.FieldByName('CDHISREC').AsInteger  :=
                                                       pCcOrigem.FieldByName('CDHISREC').AsInteger;
            pCcDestin.FieldByName('NRCCRPIS').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCCRPIS').AsInteger;
            pCcDestin.FieldByName('NRCDBPIS').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCDBPIS').AsInteger;
            pCcDestin.FieldByName('CDHISPIS').AsInteger  :=
                                                       pCcOrigem.FieldByName('CDHISPIS').AsInteger;
            pCcDestin.FieldByName('NRCCRCOF').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCCRCOF').AsInteger;
            pCcDestin.FieldByName('NRCDBCOF').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCDBCOF').AsInteger;
            pCcDestin.FieldByName('CDHISCOF').AsInteger  :=
                                                       pCcOrigem.FieldByName('CDHISCOF').AsInteger;
            pCcDestin.FieldByName('NRCCRICM').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCCRICM').AsInteger;
            pCcDestin.FieldByName('NRCDBICM').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCDBICM').AsInteger;
            pCcDestin.FieldByName('CDHISICM').AsInteger  :=
                                                       pCcOrigem.FieldByName('CDHISICM').AsInteger;
            pCcDestin.FieldByName('NRCCRISS').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCCRISS').AsInteger;
            pCcDestin.FieldByName('NRCDBISS').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCDBISS').AsInteger;
            pCcDestin.FieldByName('CDHISISS').AsInteger  :=
                                                       pCcOrigem.FieldByName('CDHISISS').AsInteger;
            pCcDestin.FieldByName('NRCCRISR').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCCRISR').AsInteger;
            pCcDestin.FieldByName('NRCDBISR').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCDBISR').AsInteger;
            pCcDestin.FieldByName('CDHISISR').AsInteger  :=
                                                       pCcOrigem.FieldByName('CDHISISR').AsInteger;
            pCcDestin.FieldByName('NRCCRICR').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCCRICR').AsInteger;
            pCcDestin.FieldByName('NRCDBICR').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCDBICR').AsInteger;
            pCcDestin.FieldByName('CDHISICR').AsInteger  :=
                                                       pCcOrigem.FieldByName('CDHISICR').AsInteger;

            pCcDestin.FieldByName('VRCONNOT' ).AsFloat :=
                                                         pCcDestin.FieldByName('VRCONNOT').AsFloat +
                                                         pCcOrigem.FieldByName('VRCONNOT').AsFloat;

            //ICMS - Desconto(VrIcmsRet)
            pCcDestin.FieldByName('VRICMS'   ).AsFloat :=
                                                        pCcDestin.FieldByName('VRICMS').AsFloat +
                                                       (pCcOrigem.FieldByName('VRICMS'   ).AsFloat -
                                                        pCcOrigem.FieldByName('VrIcmsRet').AsFloat);

            pCcDestin.FieldByName('VRICMSRET').AsFloat :=
                                                        pCcDestin.FieldByName('VRICMSRET').AsFloat +
                                                        pCcOrigem.FieldByName('VRICMSRET').AsFloat;

            //ISSQN ISSRET - Ok

            if (pCcOrigem.FieldByName('VRISSQN').AsFloat > 0) then
            begin
                  pCcDestin.FieldByName('VRISSQN').AsFloat :=
                                                     pCcDestin.FieldByName('VRISSQN').AsFloat +
                                                   (pCcOrigem.FieldByName('VRISSQN').AsFloat *
                                                   (pCcOrigem.FieldByName('PcAliIss').AsFloat/100));
            end;

            if (pCcOrigem.FieldByName('VRISSRET').AsFloat > 0) then
            begin
                  pCcDestin.FieldByName('VRISSRET').AsFloat :=
                                                     pCcDestin.FieldByName('VRISSRET').AsFloat +
                                                   (pCcOrigem.FieldByName('VRISSRET').AsFloat *
                                                   (pCcOrigem.FieldByName('PcAliIss').AsFloat/100));
            end;

            //Pis Cofins - Ok
            pCcDestin.FieldByName('VRIMPPIS' ).AsFloat :=
                                                        pCcDestin.FieldByName('VRIMPPIS' ).AsFloat +
                                                        pCcOrigem.FieldByName('VRIMPPIS' ).AsFloat;
            pCcDestin.FieldByName('VRIMPCOF' ).AsFloat :=
                                                        pCcDestin.FieldByName('VRIMPCOF' ).AsFloat +
                                                        pCcOrigem.FieldByName('VRIMPCOF' ).AsFloat;
            pCcDestin.Post;
      end else
      begin
            pCcDestin.Append;

            if (pSnCancel) then
            begin
                  pCcDestin.FieldByName('NRDOC').AsString :=
                                                           pCcOrigem.FieldByName('NrNota').AsString;
            end;

           {if (pCcOrigem.FindField('NrNotMin') <> nil) then
            begin
                  pCcDestin.FieldByName('NRDOC').AsString   := pCcOrigem.FieldByName('NrNotMin').AsString
                                                       + '-' + pCcOrigem.FieldByName('NrNotMax').AsString;
            end else
            begin
                  pCcDestin.FieldByName('NRDOC').AsString   := pCcOrigem.FieldByName('NrNota').AsString;
            end;}

            pCcDestin.FieldByName('NRPLANO' ).AsInteger  :=
                                                         pCcOrigem.FieldByName('NRPLANO').AsInteger;
            pCcDestin.FieldByName('NRNOTMIN').AsInteger  :=
                                                          pCcOrigem.FieldByName('NrNota').AsInteger;
            pCcDestin.FieldByName('NRNOTMAX').AsInteger  :=
                                                          pCcOrigem.FieldByName('NrNota').AsInteger;

            pCcDestin.FieldByName('DSMODENF').AsString   :=
                                                       pCcOrigem.FieldByName('DSMODENF').AsString;
            pCcDestin.FieldByName('DTEMINOT').AsDateTime :=
                                                       pCcOrigem.FieldByName('DTEMINOT').AsDateTime;
            pCcDestin.FieldByName('STNOTA'  ).AsString   :=
                                                       pCcOrigem.FieldByName('STNOTA'  ).AsString;
            pCcDestin.FieldByName('CDPRODUT').AsInteger  :=
                                                       pCcOrigem.FieldByName('CDPRODUT').AsInteger;
            pCcDestin.FieldByName('DSOBSERV').AsString   :=
                                                       pCcOrigem.FieldByName('DSOBSERV').AsString;
            pCcDestin.FieldByName('STLANCTO').AsString   :=
                                                       pCcOrigem.FieldByName('STLANCTO').AsString;
            pCcDestin.FieldByName('NRCCRREC').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCCRREC').AsInteger;
            pCcDestin.FieldByName('NRCDBREC').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCDBREC').AsInteger;
            pCcDestin.FieldByName('CDHISREC').AsInteger  :=
                                                       pCcOrigem.FieldByName('CDHISREC').AsInteger;
            pCcDestin.FieldByName('NRCCRPIS').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCCRPIS').AsInteger;
            pCcDestin.FieldByName('NRCDBPIS').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCDBPIS').AsInteger;
            pCcDestin.FieldByName('CDHISPIS').AsInteger  :=
                                                       pCcOrigem.FieldByName('CDHISPIS').AsInteger;
            pCcDestin.FieldByName('NRCCRCOF').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCCRCOF').AsInteger;
            pCcDestin.FieldByName('NRCDBCOF').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCDBCOF').AsInteger;
            pCcDestin.FieldByName('CDHISCOF').AsInteger  :=
                                                       pCcOrigem.FieldByName('CDHISCOF').AsInteger;
            pCcDestin.FieldByName('NRCCRICM').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCCRICM').AsInteger;
            pCcDestin.FieldByName('NRCDBICM').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCDBICM').AsInteger;
            pCcDestin.FieldByName('CDHISICM').AsInteger  :=
                                                       pCcOrigem.FieldByName('CDHISICM').AsInteger;
            pCcDestin.FieldByName('NRCCRISS').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCCRISS').AsInteger;
            pCcDestin.FieldByName('NRCDBISS').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCDBISS').AsInteger;
            pCcDestin.FieldByName('CDHISISS').AsInteger  :=
                                                       pCcOrigem.FieldByName('CDHISISS').AsInteger;
            pCcDestin.FieldByName('NRCCRISR').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCCRISR').AsInteger;
            pCcDestin.FieldByName('NRCDBISR').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCDBISR').AsInteger;
            pCcDestin.FieldByName('CDHISISR').AsInteger  :=
                                                       pCcOrigem.FieldByName('CDHISISR').AsInteger;
            pCcDestin.FieldByName('NRCCRICR').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCCRICR').AsInteger;
            pCcDestin.FieldByName('NRCDBICR').AsInteger  :=
                                                       pCcOrigem.FieldByName('NRCDBICR').AsInteger;
            pCcDestin.FieldByName('CDHISICR').AsInteger  :=
                                                       pCcOrigem.FieldByName('CDHISICR').AsInteger;
            pCcDestin.FieldByName('VRCONNOT' ).AsFloat :=
                                                         pCcOrigem.FieldByName('VRCONNOT' ).AsFloat;
            pCcDestin.FieldByName('VRICMS'   ).AsFloat :=
                                                         pCcOrigem.FieldByName('VRICMS'   ).AsFloat;
            pCcDestin.FieldByName('VRICMSRET').AsFloat :=
                                                         pCcOrigem.FieldByName('VRICMSRET').AsFloat;

            //ISSQN ISSRET - Ok

            if (pCcOrigem.FieldByName('VRISSQN').AsFloat > 0) then
            begin
                  pCcDestin.FieldByName('VRISSQN').AsFloat :=
                                                     pCcOrigem.FieldByName('VRISSQN').AsFloat *
                                                    (pCcOrigem.FieldByName('PcAliIss').AsFloat/100);
            end;

            if (pCcOrigem.FieldByName('VRISSRET').AsFloat > 0) then
            begin
                  pCcDestin.FieldByName('VRISSRET').AsFloat :=
                                                     pCcOrigem.FieldByName('VRISSRET').AsFloat *
                                                    (pCcOrigem.FieldByName('PcAliIss').AsFloat/100);
            end;

            //Pis Cofins - Ok
            pCcDestin.FieldByName('VRIMPPIS' ).AsFloat :=
                                                         pCcOrigem.FieldByName('VRIMPPIS' ).AsFloat;
            pCcDestin.FieldByName('VRIMPCOF' ).AsFloat :=
                                                         pCcOrigem.FieldByName('VRIMPCOF' ).AsFloat;
            pCcDestin.Post;
      end;

      if (pCcOrigem.FieldByName('STNOTA').AsString = 'C') then
      begin
            gVrCanPIS    := gVrCanPIS    + pCcOrigem.FieldByName('VRIMPPIS' ).AsFloat;
            gVrCanCOFINS := gVrCanCOFINS + pCcOrigem.FieldByName('VRIMPCOF' ).AsFloat;
            gVrCanISSRet := gVrCanISSRet + pCcDestin.FieldByName('VRISSRET' ).AsFloat;
            gVrCanISSQn  := gVrCanISSQn  + pCcDestin.FieldByName('VRISSQN'  ).AsFloat;
            gVrCanICMS   := gVrCanICMS   + pCcOrigem.FieldByName('VRICMS'   ).AsFloat;
            gVrCanICMSRet:= gVrCanICMSRet+ pCcOrigem.FieldByName('VRICMSRET').AsFloat;
      end else
      begin
            gVrRecPIS    := gVrRecPIS    + pCcOrigem.FieldByName('VRIMPPIS' ).AsFloat;
            gVrRecCOFINS := gVrRecCOFINS + pCcOrigem.FieldByName('VRIMPCOF' ).AsFloat;
            gVrRecISSRet := gVrRecISSRet + pCcDestin.FieldByName('VRISSRET' ).AsFloat;
            gVrRecISSQn  := gVrRecISSQn  + pCcDestin.FieldByName('VRISSQN'  ).AsFloat;
            gVrRecICMS   := gVrRecICMS   + pCcOrigem.FieldByName('VRICMS'   ).AsFloat;
            gVrRecICMSRet:= gVrRecICMSRet+ pCcOrigem.FieldByName('VRICMSRET').AsFloat;
      end;
end;

procedure TMov0027.BtnBuscarClick(Sender: TObject);
begin
     try
         BuscarDados;
         PnlDados.Visible  := True;
         PnlFilter.Enabled := False;
     except
         On E : Exception Do
         begin
               MessageDlg('Erro durante processamento: ' + Chr(13) + E.Message, mtError, [mbok],0);
         end;
     end;
end;

initialization
      RegisterClass(TMov0027);

finalization
      UnRegisterClass(TMov0027);

end.
