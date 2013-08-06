unit UMov0026;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  StdCtrls, BrvEditNum, BrvRetCon, BrvBitBtn, Grids, BrvDbGrids, BrvDbGrid, ComCtrls, DB, DBClient,
  BrvGeraRelatorio, BrvServerProgress, BrvConsulta, BrvLogScreen, BrvCustomEdit;

type
  TMov0026 = class(TMov0000)
    PnlEmpres: TPanel;
    EdtCdEmpres: TBrvEditNum;
    Label1: TLabel;
    PnlCarga: TPanel;
    LblCarga: TLabel;
    EdtCdCarga: TBrvEditNum;
    EdtKmPerCar: TBrvEditNum;
    EdtDsEmpres: TBrvRetCon;
    EdtDsDesCar: TBrvRetCon;
    EdTCdMotori: TBrvRetCon;
    EdtCdEmpMot: TBrvRetCon;
    EdtCdVeicul: TBrvRetCon;
    Label3: TLabel;
    Label4: TLabel;
    Label7: TLabel;
    Label11: TLabel;
    EdtNmMotori: TBrvRetCon;
    BtnProsse: TBrvBitBtn;
    BrvBitBtn1: TBrvBitBtn;
    BtnRetorn: TBrvBitBtn;
    PnlAcerto: TPanel;
    PgcAcerto: TPageControl;
    TbsAcerto: TTabSheet;
    TbsConCor: TTabSheet;
    Panel1: TPanel;
    DbgAceCar: TBrvDbGrid;
    DbgConCor: TBrvDbGrid;
    BtnConCor: TBrvBitBtn;
    BtnFinali: TBrvBitBtn;
    BtnGravar: TBrvBitBtn;
    BtnImprim: TBrvBitBtn;
    BtnRetCar: TBrvBitBtn;
    CPConCor: TClientDataSet;
    CPAceCarga: TClientDataSet;
    DsAcerto: TDataSource;
    DtsConCor: TDataSource;
    Panel2: TPanel;
    EdtTtCredit: TBrvEditNum;
    EdtTtDebito: TBrvEditNum;
    EdtVrSaldo: TBrvEditNum;
    Label2: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    LblStCarga: TLabel;
    BrvGerRel: TBrvGeraRelatorio;
    BrvServerProgress: TBrvServerProgress;
    CPContab: TClientDataSet;
    CPContabCdEmpres: TIntegerField;
    CPContabDtLancto: TDateField;
    CPContabNrConDeb: TIntegerField;
    CPContabNrConCre: TIntegerField;
    CPContabVrLancto: TFloatField;
    CPContabCdHistor: TIntegerField;
    CPContabNmFormul: TStringField;
    CPContabNrDocto: TStringField;
    CPContabSnEncerr: TStringField;
    CPContabCdCeCuDe: TIntegerField;
    CPContabCdCeCuCr: TIntegerField;
    CPContabNrLancto: TIntegerField;
    CPContabNrPlano: TIntegerField;
    CPCenCus: TClientDataSet;
    IntegerField7: TIntegerField;
    CPCenCusCdCenCus: TIntegerField;
    CPCenCusVrLancto: TFloatField;
    CPCenCusTpLancto: TStringField;
    CdsParams: TClientDataSet;
    CdsParamsCdCarga: TIntegerField;
    CdsParamsKmPerCar: TFloatField;
    CdsParamsNrPlano: TIntegerField;
    CdsParamsCCAceCar: TMemoField;
    CdsParamsCcConCor: TMemoField;
    CdsParamsCCContab: TMemoField;
    CdsParamsSnFinali: TStringField;
    CdsParamsNmFormul: TStringField;
    BrvLogScreen: TBrvLogScreen;
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BtnProsseClick(Sender: TObject);
    procedure EdtCdCargaBrOnBeforeConsulta(Sender: TObject);
    procedure BtnRetornClick(Sender: TObject);
    procedure BrvBitBtn1Click(Sender: TObject);
    procedure BtnRetCarClick(Sender: TObject);
    procedure CPAceCargaAfterPost(DataSet: TDataSet);
    procedure BtnConCorClick(Sender: TObject);
    procedure BtnImprimClick(Sender: TObject);
    procedure BtnFinaliClick(Sender: TObject);
    procedure BtnGravarClick(Sender: TObject);
    procedure DbgAceCarColumns0BrOnBeforeConsul(Sender: TObject);
    procedure DbgAceCarColumns4BrOnBeforeConsul(Sender: TObject);
    procedure CPAceCargaAfterInsert(DataSet: TDataSet);
    procedure EdtCdCargaExit(Sender: TObject);
  private
    { Private declarations }
    function  CalculaSaldoMotorista : Real;
    function  BuscarDadosContaCorrente(pTpSituac : String) : Real;
    procedure CarregarDadosAcerto;
    procedure CarregarDadosContaCorrentePendente;
    procedure TotalizaCargaMovAcerto;

    procedure CarregarContabilizacao;
    procedure GravarAcertoCarga(pSnFinali  : String);


  public
    { Public declarations }
  end;

var
  Mov0026: TMov0026;
  gCdHiCoMo : Integer;
  gCdHcSdMo : Integer;
  gNrPlano  : String;
  gDtServer : TDate;


implementation

uses UDmSrvApl, UDmCtb, UClaSrv;

{$R *.dfm}

procedure TMov0026.BrvBitBtn1Click(Sender: TObject);
begin
      inherited;

      CarregarDadosAcerto;

      TotalizaCargaMovAcerto;

      if LblStCarga.Caption <> 'E' then
      begin
            BtnFinali.Enabled := True;
            BtnGravar.Enabled := True;
            DbgAceCar.Enabled := True;
            DbgConCor.Enabled := True;
            BtnConCor.Enabled := True;
      end else
      begin
            BtnFinali.Enabled := False;
            BtnGravar.Enabled := False;
            DbgAceCar.Enabled := False;
            DbgConCor.Enabled := False;
            BtnConCor.Enabled := False;
      end;

      PnlAcerto.Visible := True;
      PnlCarga.Enabled  := False;
      TotalizaCargaMovAcerto;
end;

procedure TMov0026.BtnImprimClick(Sender: TObject);
begin
      inherited;

      BrvGerRel.IniciarRelatorio('RLC0010', 'C', Name);
      BrvGerRel.InserirParametroSQL('CdCarga', EdtCdCarga.Text);

      BrvServerProgress.Start(Self.Caption, 'Gerando impressão acerto! Aguarde...', 100, 10);
      BrvGerRel.ImprimirRelatorio(BrvServerProgress);
end;

procedure TMov0026.BtnRetornClick(Sender: TObject);
begin
      inherited;
      PnlEmpres.Enabled := True;
      PnlCarga.Visible  := False;
end;

procedure TMov0026.GravarAcertoCarga(pSnFinali  : String);
var lSrvTransp : TSDmTraClient;

begin
      try
          CdsParams.Close;
          CdsParams.CreateDataSet;
          CdsParams.Append;
          CdsParams.FieldByName('CdCarga').AsString  := EdtCdCarga.Text;
          CdsParams.FieldByName('KmPerCar').AsString := EdtKmPerCar.Text;
          CdsParams.FieldByName('NrPlano').AsString  := gNrPlano;
          CdsParams.FieldByName('CCAceCar').AsString := CPAceCarga.XMLData;
          CdsParams.FieldByName('CCConCor').AsString := CPConCor.XMLData;
          CdsParams.FieldByName('CCContab').AsString := DMCTB.QcLanCon.XMLData;
          CdsParams.FieldByName('SnFinali').AsString := pSnFinali;
          CdsParams.FieldByName('NmFormul').AsString := Name;

          CdsParams.Post;


          lSrvTransp  := TSDmTraClient.Create(DmSrvApl.SrvAplica.DBXConnection);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(
                                 lSrvTransp.GravarAcertoCarga(DmSrvApl.BrvDicionario.Credencial,
                                                              CdsParams.XMLData));

          if pSnFinali = 'S' then
          begin
                DmSrvApl.GravaLogScreen(DmSrvApl.BrvDicionario.UserCode,
                                        BrvLogScreen.Execute(PnlFundo, Self),
                                        Self.Name,
                                        DmSrvApl.BrvDicionario.Credencial,
                                        ExtractFilePath(Application.ExeName),
                                        DmSrvApl.SrvAplica);
          end;

          MessageDlg('Gravado com sucesso!', mtInformation, [mbok], 0);

          CdsParams.Close;
      finally
          FreeAndNil(lSrvTransp);
      end;

      BtnRetCarClick(nil);
      BtnRetornClick(nil);
end;

procedure TMov0026.BtnConCorClick(Sender: TObject);
begin
      inherited;
      CarregarDadosContaCorrentePendente;
end;

procedure TMov0026.BtnFinaliClick(Sender: TObject);
begin
      inherited;

      if MessageDlg('Deseja realemnte finalizar o acerto?', mtConfirmation,
                                                                      [mbYes, mbNo], 0) = mrYes then
      begin
            BtnFinali.Enabled := False;
            try
                if EdtVrSaldo.BrAsFloat <> 0 then
                begin
                      Raise Exception.Create('Valor do saldo esta diferente de zero.');
                end;
                CarregarContabilizacao;

                GravarAcertoCarga('S');

                BtnImprimClick(nil);
            finally
                BtnRetCarClick(nil);
                BtnRetornClick(nil);
            end;
      end;
end;

procedure TMov0026.CarregarContabilizacao;
var lNrCDebAC : Integer; //-- Conta Débito lançamento acerto de carga
    lNrCCreAC : Integer; //-- Conta Crédito lançamento acerto de carga
    lNrCCDeAC : Integer; //-- Centro de Custo Débito lançamento acerto de carga
    lNrCCCrAC : Integer; //-- Centro de Custo Crédito lançamento acerto de carga
    lCdHistAC : Integer;
    lNrClDeAC : String;  //-- Classificação da conta débito do acerto de carga.
    lNrClCrAC : String;  //-- Classificação da conta crédito do acerto de carga.

    lNrCDebCC : Integer; //-- Conta Débito lançamento acerto de carga/Conta Corrente
    lNrCCreCC : Integer; //-- Conta Crédito lançamento acerto de carga/Conta Corrente
    lNrCCDeCC : Integer; //-- Centro de Custo Débito lançamento acerto de carga/Conta Corrente
    lNrCCCrCC : Integer; //-- Centro de Custo Crédito lançamento acerto de carga/Conta Corrente
    lCdHistCC : Integer;
    lNrClDeCC : String;  //-- Classificação da conta débito do acerto de carga/Conta Corrente.
    lNrClCrCC : String;  //-- Classificação da conta crédito do acerto de carga/Conta Corrente.

    lCPParCon : TClientDataSet;
    lTxComple : TStrings;
begin
      try
          CPAceCarga.First;

          lTxComple      := TStringList.Create;
          lCPParCon      := TClientDataSet.Create(self);

          ///comentei temporáriamente. Enio
          //lCPParCon.Data := DMCTB.ParametroContabilizacaoFormulario(
          //                                                 Name, gStlParCon.Text, EdtCdEmpMot.Text);

          lNrCDebCC := lCPParCon.FieldByName('NrConDeb').AsInteger;
          lNrCCreCC := lCPParCon.FieldByName('NrConCre').AsInteger;
          lNrCCDeCC := lCPParCon.FieldByName('CdCeCuDe').AsInteger;
          lNrCCCrCC := lCPParCon.FieldByName('CdCeCuCr').AsInteger;
          lCdHistCC := lCPParCon.FieldByName('CdHistor').AsInteger;
          lNrClDeCC := lCPParCon.FieldByName('NrClaDeb').AsString;
          lNrClCrCC := lCPParCon.FieldByName('NrClaCre').AsString;

          lCPParCon.Next;

          lNrCDebAC := lCPParCon.FieldByName('NrConDeb').AsInteger;
          lNrCCreAC := lCPParCon.FieldByName('NrConCre').AsInteger;
          lNrCCDeAC := lCPParCon.FieldByName('CdCeCuDe').AsInteger;
          lNrCCCrAC := lCPParCon.FieldByName('CdCeCuCr').AsInteger;
          lCdHistAC := lCPParCon.FieldByName('CdHistor').AsInteger;
          lNrClDeAC := lCPParCon.FieldByName('NrClaDeb').AsString;
          lNrClCrAC := lCPParCon.FieldByName('NrClaCre').AsString;

          DmCtb.IniciarLancamentos;

          while not CPAceCarga.Eof do
          begin
                if CPAceCarga.FieldByName('NrConta').AsInteger > 0 then
                begin
                      if CPAceCarga.FieldByName('TpHistor').AsString = 'C' then
                      begin
                            DmCtb.LancarContabilidade(
                                           {pNrLancto} 0, 0,
                                           {pCdEmpres} StrToInt(EdtCdEmpMot.Text),
                                           {pDtLancto} gDtServer,
                                           {pNrConDeb} CPAceCarga.FieldByName('NrConta').AsInteger,
                                           {pNrConCre} lNrCCreAC,
                                           {pVrLancto} CPAceCarga.FieldByName('VrLancto').AsFloat,
                                           {pNrDocto } '0',
                                           {pCdHistor} lCdHistAC,
                                           {pDsHistor} lTxComple,
                                           {pNmFormul} Name,
                                           {pSnEncerr} 'N',
                                           {pCdCeCuCr} 0,
                                           {pCdCeCuDe} CPAceCarga.FieldByName('CdCenCus').AsInteger,
                                           {pNrClaDeb} lNrClCrAC,
                                           {pNrClaCre} lNrClCrAC);
                      end else
                      begin
                            DmCtb.LancarContabilidade(
                                           {pNrLancto} 0, 0,
                                           {pCdEmpres} StrToInt(EdtCdEmpMot.Text),
                                           {pDtLancto} gDtServer,
                                           {pNrConDeb} lNrCCDeAC,
                                           {pNrConCre} CPAceCarga.FieldByName('NrConta').AsInteger,
                                           {pVrLancto} CPAceCarga.FieldByName('VrLancto').AsFloat,
                                           {pNrDocto } '0',
                                           {pCdHistor} lCdHistAC,
                                           {pDsHistor} lTxComple,
                                           {pNmFormul} Name,
                                           {pSnEncerr} 'N',
                                           {pCdCeCuCr} CPAceCarga.FieldByName('CdCenCus').AsInteger,
                                           {pCdCeCuDe} 0,
                                           {pNrClaDeb} lNrClDeAC,
                                           {pNrClaCre} lNrClDeAC);
                      end;
                end else
                begin
                      DmCtb.LancarContabilidade(
                                             {pNrLancto} 0, 0,
                                             {pCdEmpres} StrToInt(EdtCdEmpMot.Text),
                                             {pDtLancto} gDtServer,
                                             {pNrConDeb} lNrCDebCC,
                                             {pNrConCre} lNrCCreCC,
                                             {pVrLancto} CPAceCarga.FieldByName('VrLancto').AsFloat,
                                             {pNrDocto } '0',
                                             {pCdHistor} lCdHistCC,
                                             {pDsHistor} lTxComple,
                                             {pNmFormul} Name,
                                             {pSnEncerr} 'N',
                                             {pCdCeCuCr} lNrCCCrCC,
                                             {pCdCeCuDe} lNrCCDeCC,
                                             {pNrClaDeb} lNrClDeCC,
                                             {pNrClaCre} lNrClCrCC);

                end;
                CPAceCarga.Next;
          end;
      finally
          FreeAndNil(lCPParCon);
          FreeAndNil(lTxComple);
      end;
end;


procedure TMov0026.BtnGravarClick(Sender: TObject);
begin
      inherited;

      GravarAcertoCarga('N');
end;

procedure TMov0026.TotalizaCargaMovAcerto;
var lCdsTotal : TClientDataSet;
begin
      try
          lCdsTotal      := TClientDataSet.Create(nil);
          lCdsTotal.Data := CPAceCarga.Data;

          EdtTtCredit.BrAsFloat := 0;
          EdtTtDebito.BrAsFloat := 0;

          while not lCdsTotal.Eof do
          begin
                if lCdsTotal.FieldByName('TpHistor').AsString = 'C' then
                begin
                      EdtTtCredit.BrAsFloat := EdtTtCredit.BrAsFloat +
                                               lCdsTotal.FieldByName('VrLancto').AsFloat;
                end else
                begin
                      EdtTtDebito.BrAsFloat := EdtTtDebito.BrAsFloat +
                                               lCdsTotal.FieldByName('VrLancto').AsFloat;
                end;
                lCdsTotal.Next;
          end;

          EdtVrSaldo.BrAsFloat := EdtTtDebito.BrAsFloat - EdtTtCredit.BrAsFloat;
      finally

      end;
end;

function TMov0026.BuscarDadosContaCorrente(pTpSituac : String) : Real;
var lDsParams : String;
begin
      lDsParams := '<%CdMotori%>;' +  EdtCdMotori.Text + #13 +
                   '<%CdEmpres%>;' +  EdtCdEmpMot.Text + #13;

      if pTpSituac = 'C' then
      begin
            lDsParams := lDsParams + '<%CdCarga%>;T019.CdCarga = ' + EdtCdCarga.Text;
      end else
      begin
            lDsParams := lDsParams + '<%CdCarga%>;Coalesce(T019.CdCarga, ' +
                                     EdtCdCarga.Text + ') = ' + EdtCdCarga.Text;
      end;

      CPConCor.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(201, lDsParams, Name);

      if pTpSituac <> 'C' then
      begin
            while not CPConCor.Eof do
            begin
                  CPConCor.Edit;
                  CPConCor.FieldByName('CdCarga').AsInteger := EdtCdCarga.BrAsInteger;
                  CPConCor.FieldByName('StConCor').AsString := 'C';
                  CPConCor.Next;
            end;
      end;

      Result := CalculaSaldoMotorista;
      TNumericField(CPConCor.FieldByName('VrLancto')).DisplayFormat := '#0.00';
end;

function TMov0026.CalculaSaldoMotorista : Real;
begin
      CPConCor.First;

      Result   := 0;

      while not CPConCor.Eof do
      begin
            if CPConCor.FieldByName('TpHistor').AsString = 'D' then
            begin
                  Result := Result + CPConCor.FieldByName('VrLancto').AsFloat;
            end else
            begin
                  Result := Result - CPConCor.FieldByName('VrLancto').AsFloat;
            end;
            CPConCor.Next;
      end;
end;

procedure TMov0026.CarregarDadosContaCorrentePendente;
var lVrSaldo  : Real;
begin
      lVrSaldo := BuscarDadosContaCorrente('N');

      if lVrSaldo <> 0 then
      begin
            CPConCor.Append;
            CPConCor.FieldByName('CdEmpres').AsString   := EdtCdEmpMot.Text;
            CPConCor.FieldByName('CdMotori').AsString   := EdtCdMotori.Text;
            CPConCor.FieldByName('DtLancto').AsDateTime := DmSrvApl.BrvDicionario.DataServer;
            CPConCor.FieldByName('CdCarga').AsString    := EdtCdCarga.Text;
            CPConCor.FieldByName('VrLancto').AsFloat    := lVrSaldo;
            CPConCor.FieldByName('CdHistor').AsInteger  := gCdHcSdMo;

            CPConCor.FieldByName('DsHistor').AsString   :=
                      DmSrvApl.BrvDicionario.RetornaValorColunaTabela('G008', 'DsHistor',
                                                              'CdHistor', IntToStr(gCdHcSdMo));

            CPConCor.FieldByName('TpHistor').AsString   :=
                      DmSrvApl.BrvDicionario.RetornaValorColunaTabela('G008', 'TpHistor',
                                                              'CdHistor', IntToStr(gCdHcSdMo));

            CPConCor.FieldByName('StConCor').AsString   := 'C';
            CPConCor.FieldByName('NrSeqCon').AsString   := '0';

            //CPConCor.Post;

            CPAceCarga.Append;
            CPAceCarga.FieldByName('CdEmpres').AsString  := EdtCdEmpMot.Text;
            CPAceCarga.FieldByName('CdCarga').AsString   := EdtCdCarga.Text;
            CPAceCarga.FieldByName('CdHistor').AsInteger := gCdHiCoMo;
            CPAceCarga.FieldByName('DsHistor').AsString  :=
                      DmSrvApl.BrvDicionario.RetornaValorColunaTabela('G008', 'DsHistor',
                                                           'CdHistor', IntToStr(gCdHiCoMo));

            CPAceCarga.FieldByName('TpHistor').AsString  :=
                      DmSrvApl.BrvDicionario.RetornaValorColunaTabela('G008', 'TpHistor',
                                                           'CdHistor', IntToStr(gCdHiCoMo));

            CPAceCarga.FieldByName('NrSeqMov').AsInteger  := 0;
            CPAceCarga.FieldByName('DtLancto').AsDateTime := DmSrvApl.BrvDicionario.DataServer;
            CPAceCarga.FieldByName('VrLancto').AsFloat    := lVrSaldo;
            CPAceCarga.FieldByName('CdUsuari').AsInteger  := DmSrvApl.BrvDicionario.UserCode;
            CPAceCarga.FieldByName('TpLanMov').AsString   := 'C';//Acerto
            CPAceCarga.Post;
      end;
end;


procedure TMov0026.CarregarDadosAcerto;
var lCdHiCoMo : Integer;
    lCdHcSdMo : Integer;
    lVrSaldo  : Real;
begin
      gCdHiCoMo := DmSrvApl.BrvDicionario.ParametroDaEmpresa(28,0);
      gCdHcSdMo := DmSrvApl.BrvDicionario.ParametroDaEmpresa(27,0);

      CPAceCarga.Close;
      CPAceCarga.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(
                                                       203, '<%CdCarga%>;' + EdtCdCarga.Text, Name);

      TNumericField(CPAceCarga.FieldByName('VrLancto')).DisplayFormat := '#0.00';

      BuscarDadosContaCorrente('C');
end;

procedure TMov0026.CPAceCargaAfterInsert(DataSet: TDataSet);
begin
      inherited;
      CPAceCarga.FieldByName('CdCarga').AsInteger   := EdtCdCarga.BrAsInteger;
      CPAceCarga.FieldByName('CdEmpres').AsInteger  := EdtCdEmpres.BrAsInteger;
      CPAceCarga.FieldByName('NrSeqMov').AsInteger  := 0;
      CPAceCarga.FieldByName('NrPlano').AsString    := gNrPlano;
      CPAceCarga.FieldByName('TpLanMov').AsString   := 'C';//Acerto
      CPAceCarga.FieldByName('DtLancto').AsDateTime := gDtServer;//Acerto

end;

procedure TMov0026.CPAceCargaAfterPost(DataSet: TDataSet);
begin
      inherited;
      TotalizaCargaMovAcerto;
end;

procedure TMov0026.DbgAceCarColumns0BrOnBeforeConsul(Sender: TObject);
begin
      inherited;
      DbgAceCar.Columns[0].BrParams.Clear;
      DbgAceCar.Columns[0].BrParams.Add('<%CdEmpres%>;' + DmSrvApl.BrvDicionario.CorpCommaCodes);
end;

procedure TMov0026.DbgAceCarColumns4BrOnBeforeConsul(Sender: TObject);
begin
      inherited;
      DbgAceCar.Columns[4].BrParams.Clear;
      DbgAceCar.Columns[4].BrParams.Add('<%NrPlano%>;' + gNrPlano);
end;

procedure TMov0026.BtnProsseClick(Sender: TObject);
begin
      inherited;
      if EdtCdEmpres.BrAsInteger > 0 then
      begin
            PnlEmpres.Enabled := False;
            PnlCarga.Visible  := True;

            gDtServer := EdtCdEmpres.BrDicionario.DataServer;
            gNrPlano  := IntToStr(DMCTB.PlanoContasEmpresa(EdtCdEmpres.BrAsInteger, gDtServer));
      end else
      begin
            raise Exception.Create('Empresa inválida!');
      end;
end;

procedure TMov0026.BtnRetCarClick(Sender: TObject);
begin
      inherited;
      PnlAcerto.Visible := False;
      PnlCarga.Enabled  := True;
      CPAceCarga.Close;
      CPConCor.Close;

end;

procedure TMov0026.EdtCdCargaBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdCarga.BrParams.Clear;
      EdtCdCarga.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.Text);
end;

procedure TMov0026.EdtCdCargaExit(Sender: TObject);
begin
      if LblStCarga.Caption <> 'E' then
      begin
            EdtKmPerCar.Enabled := True;
      end else
      begin
            EdtKmPerCar.Enabled := False;
      end;

end;

procedure TMov0026.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      (Sender as TBrvEditNum).BrParams.Clear;
      (Sender as TBrvEditNum).BrParams.Add('<%CdEmpres%>;' +
                                                 EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

initialization
      RegisterClass(TMov0026);

finalization
      UnRegisterClass(TMov0026);

end.
