unit BrvContabil;

interface

uses
  SysUtils, Classes, BrvGestao, DbClient, BrvString, BrvClientDataSet, BrvProvider,
  BrvDataSet, Provider, Db;

type
  TBrvContabil = class(TBrvGestao)
  private
    //Padrão Original
    procedure EfetuarLancamentoContabil(pCdEmpres : Integer;    pDtLancto : String;
                                        pNrConDeb : Integer;    pNrConCre : Integer;
                                        pVrLancto : Real;       pCdHistor : Integer;
                                        pDsHistor : AnsiString; pNmFormul : String;
                                        pNrDocto  : String;     pSnEncerr : String;
                                        pCdCeCuDe : Integer;    pCdCeCuCr : Integer;
                                        pNrPlano  : Integer;    pCdUsuari : Integer;
                                        pCdsB004  : TBrvClientDataSet;
                                        pCdsB008  : TbrvClientDataSet);
    //Padrão XML
    procedure EfetuarLancamentoContabilXML(pCdEmpres: Integer; pDtLancto: String;
                                           pNrConDeb: Integer; pNrConCre: Integer;
                                           pVrLancto: Real;    pCdHistor: Integer;
                                           pDsHistor: AnsiString;
                                           pNmFormul: String;
                                           pNrDocto : String;
                                           pSnEncerr: String;
                                           pNrPlano : Integer;
                                           pCdUsuari: Integer;
                                           pCdsB004 : TBrvClientDataSet;
                                           pCdsB008 : TBrvClientDataSet;
                                           pNrLancto: Integer;
                                           pCcCenCus: OleVariant);

    function CarregarContasRateadas(pNrClassi: String; pNrPlano: Integer): String;
    procedure EncontrarParentes(var pNrClassi : String; var pNrNivPai : String;
                                var pNrNivAtu : String; var pNrNivFil : String;
                                var pNrPosAtu : byte);
    function MesmoNivel(pNrClassi, pNrPai: String; pNrAtual: Byte): Boolean;
    procedure LancarCentroCusto(pCdEmpres : Integer; pDtLancto : String;
                                pNrLancto : Integer; pNrConDeb : Integer;
                                pNrConCre : Integer; pVrLancto : Real;
                                pCdCeCuDe : Integer; pCdCeCuCr : Integer;
                                pNrPlano  : Integer;
                                pCdsB008  : TBrvClientDataSet);

    procedure LancarCentroCustoXML(pCdEmpres : Integer; pDtLancto : String;
                                   pNrLancto : Integer; pNrConDeb : Integer;
                                   pNrConCre : Integer; pVrLancto : Real;
                                   pNrPlano  : Integer;
                                   pCdsB008  : TBrvClientDataSet;
                                   pNrLanCen : Integer;
                                   pCcCenCus : OleVariant);

    procedure LancarContasRateadas(pNrClaRat : TStrings;   pNrClassi : String;
                                   pTpNatOpe : String;     pCdEmpres : Integer;
                                   pDtLancto : String;     pVrLancto : Real;
                                   pNrDocto  : String;     pCdHistor : Integer;
                                   pDsHistor : AnsiString; pNmFormul : String;
                                   pSnRecerr : String;     pNrPlano  : Integer;
                                   pCdUsuari : Integer;
                                   pCdsB004  : TBrvClientDataSet;
                                   pCdsB008  : TBrvClientDataSet);
    function NumeroConta(pNrPlano: Integer; pNrClassi: String): Integer;

    procedure ValidarPeriodoContabil(pDtLancto: TDate; pCdEmpres: Integer);
    function PeriodoContabil(pCdEmpres : Integer; pDtMes : byte;
                             pDtAno    : Integer) : String;
    procedure ValidarContasRateada(pNrClaRat: TStrings; pNrClassi, pNrOutCla: String);
    procedure LancarCentroCustoRateio(pCdEmpres : Integer; pDtLancto : String;
                                      pNrLancto : Integer; pNrConCon : Integer;
                                      pTpLancto : String;  pVrLancto : Real);

    procedure CriarCdsB004(var pCdsB004: TBrvClientDataSet; var pProB004: TBrvProvider;
                           var pDtsB004: TBrvDataSet);

    procedure CriarCdsB008(var pCdsB008: TBrvClientDataSet; var pProB008: TBrvProvider;
                           var pDtsB008: TBrvDataSet);

    //Padrão Original
    procedure ValidarLancamentoContabil(pNrConDeb : Integer;
                                        pNrConCre : Integer;
                                        pCdHistor : Integer;
                                        pCdEmpres : Integer;
                                        pCdCeCuCr : Integer;
                                        pCdCeCuDe : Integer;
                                        pDtLancto : TDate;
                                        pNrClRaDe : TStrings; pNrClRaCr : TStrings;
                                        pNrClaCre : String;   pNrClaDeb : String;
                                        pNrPlano  : Integer);
    //Padrão XML
    procedure ValidarLancamentoContabilXML(pNrConDeb : Integer;
                                           pNrConCre : Integer;
                                           pCdHistor : Integer;
                                           pCdEmpres : Integer;
                                           pNrClRaDe : TStrings;
                                           pNrClRaCr : TStrings;
                                           pNrClaCre : String;
                                           pNrClaDeb : String;
                                           pNrPlano  : Integer;
                                           pNrLancto : Integer;
                                           pCcCenCus : OleVariant);

    //Padrão Original
    procedure ValidarCentroDeCustoContabil(pNrPlano  : Integer; pNrConCre : Integer;
                                           pNrConDeb : Integer; pCdCeCuCr : Integer;
                                           pCdCeCuDe : Integer);
    //Padrão XML
    procedure ValidarCentroDeCustoContabilXML(pNrPlano  : Integer;
                                              pNrConta  : Integer;
                                              pQtLanCen : Integer);
    procedure LancarContasRateadasXML(pNrClaRat: TStrings; pNrClassi, pTpNatOpe: String;
      pCdEmpres: Integer; pDtLancto: String; pVrLancto: Real; pNrDocto: String; pCdHistor: Integer;
      pDsHistor: AnsiString; pNmFormul, pSnRecerr: String; pNrPlano, pCdUsuari: Integer; pCdsB004,
      pCdsB008: TBrvClientDataSet; pCcCenCus : OleVariant);

    { Private declarations }
  protected
    { Protected declarations }
  public
    //Padrão Original
    procedure LancarContabilidade(pCdsLancto : OleVariant; pNrPlano  : Integer;
                                  pCdUsuari  : Integer;    pNmFormul : String);
    //Padrão XML
    procedure LancarContabilidadeXML(pDsParam: String);

    function  NumeroClassificacao(pNrPlano, pNrConta: Integer): String;
    procedure CriarTabelaLanctoContabil(pCdsLanCon: TClientDataSet);
  published

  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo gestao', [TBrvContabil]);
end;

{ TBrvContabil }

procedure TBrvContabil.CriarCdsB004(var pCdsB004 : TBrvClientDataSet;
                                    var pProB004 : TBrvProvider;
                                    var pDtsB004 : TBrvDataSet);
begin
      pCdsB004 := TBrvClientDataSet.Create(Self);
      pProB004 := TBrvProvider.Create(self);
      pDtsB004 := TBrvDataSet.Create(self);

      pProB004.DataSet       := pDtsB004;
      pProB004.Options       := [poAllowCommandText,poUseQuoteChar];
      pProB004.Name          := 'lProB004';
      pDtsB004.SQLConnection := gSqlConne;
      pDtsB004.BrDicionario  := gDsDicion;
      pCdsB004.ProviderName  := 'lProB004';
      pCdsB004.BrDicionario  := gDsDicion;
      pCdsB004.BrQueryCode   := 80;
      pCdsB004.BrGravaLog    := False;
      pCdsB004.Open;
end;

procedure TBrvContabil.CriarCdsB008(var pCdsB008 : TBrvClientDataSet;
                                    var pProB008 : TBrvProvider;
                                    var pDtsB008 : TBrvDataSet);
begin
      pCdsB008 := TBrvClientDataSet.Create(Self);
      pProB008 := TBrvProvider.Create(self);
      pDtsB008 := TBrvDataSet.Create(self);

      pProB008.DataSet       := pDtsB008;
      pProB008.Options       := [poAllowCommandText,poUseQuoteChar];
      pProB008.Name          := 'lProB008';
      pDtsB008.SQLConnection := gSqlConne;
      pDtsB008.BrDicionario  := gDsDicion;
      pCdsB008.ProviderName  := 'lProB008';
      pCdsB008.BrDicionario  := gDsDicion;
      pCdsB008.BrQueryCode   := 81;
      pCdsB008.BrGravaLog    := False;
      pCdsB008.Open;
end;

procedure TBrvContabil.LancarContabilidadeXML(pDsParam : String);
var lCpLancto : TClientDataSet;
    lcpDsParam: TClientDataSet;
    lCpCenCus : TClientDataSet;
    lNrClRaDe : TStrings;
    lNrClRaCr : TStrings;
    lBrvString : TBrvString;
    lDtLancto  : String;
    lCdsB004   : TBrvClientDataSet;
    lProB004   : TBrvProvider;
    lDtsB004   : TBrvDataSet;
    lCdsB008   : TBrvClientDataSet;
    lProB008   : TBrvProvider;
    lDtsB008   : TBrvDataSet;
begin
      ValidarObjetos(Owner.Name, Name);

      lCpDsParam         := TClientDataSet.Create(Self);
      lCpDsParam.XMLData := pDsParam;

      lCpLancto          := TClientDataSet.Create(Self);
      lCpLancto.XmlData  := lCpDsParam.FieldByName('B004').AsString;

      lCpCenCus           := TClientDataSet.Create(Self);
      lCpCenCus.XmlData   := lCpDsParam.FieldByName('B008').AsString;

      try
          CriarCdsB004(lCdsB004, lProB004, lDtsB004); // =-=-= Lançamentos Contabeis
          CriarCdsB008(lCdsB008, lProB008, lDtsB008); // =-=-= Lançamentos Centro de custo

          lNrClRaDe  := TStringList.Create;
          lNrClRaCr  := TStringList.Create;
          lBrvString := TBrvString.Create(self);

          lCpLancto.First;

          ValidarPeriodoContabil(lCpLancto.FieldByName('DtLancto').AsDateTime,
                                 lCpLancto.FieldByName('CdEmpres').AsInteger);

          while not lCpLancto.Eof do
          begin
                lNrClRaDe.Text := CarregarContasRateadas(lCpLancto.FieldByName('NrClaDeb').AsString,
                                                        lCpLancto.FieldByName('NrPlano').AsInteger);

                lNrClRaCr.Text := CarregarContasRateadas(lCpLancto.FieldByName('NrClaCre').AsString,
                                                        lCpLancto.FieldByName('NrPlano').AsInteger);

                ValidarLancamentoContabilXML(lCpLancto.FieldByName('NrConDeb').AsInteger,
                                             lCpLancto.FieldByName('NrConCre').AsInteger,
                                             lCpLancto.FieldByName('CdHistor').AsInteger,
                                             lCpLancto.FieldByName('CdEmpres').AsInteger,
                                             lNrClRaDe, lNrClRaCr,
                                             lCpLancto.FieldByName('NrClaCre').AsString,
                                             lCpLancto.FieldByName('NrClaDeb').AsString,
                                             lCpDsParam.FieldByName('NrPlano' ).AsInteger,
                                             lCpLancto.FieldByName('NrLancto').AsInteger,
                                             lCpCenCus.Data);

                lDtLancto := FormatDateTime(ShortDateFormat,
                                                      lCpLancto.FieldByName('DtLancto').AsDateTime);

                if  lNrClRaDe.Count > 0 then
                begin
                      LancarContasRateadasXML(lNrClRaDe,
                                  lCpLancto.FieldByName('NrClaCre').AsString,
                                  'D',
                                  lCpLancto.FieldByName('CdEmpres').AsInteger,
                                  lDtLancto,
                                  lCpLancto.FieldByName('VrLancto').AsFloat,
                                  lCpLancto.FieldByName('NrDocto').AsString,
                                  lCpLancto.FieldByName('CdHistor').AsInteger,
                                  lCpLancto.FieldByName('DsHistor').AsString,
                                  lCpLancto.FieldByName('NmFormul').AsString,
                                  lCpLancto.FieldByName('SnRecEnc').AsString,
                                  lCpDsParam.FieldByName('NrPlano').AsInteger,
                                  lCpDsParam.FieldByName('CdUsuari').AsInteger,
                                  lCdsB004, lCdsB008, lCpCenCus.Data);
                end else
                begin
                      if  lNrClRaCr.Count > 0 then
                      begin
                            LancarContasRateadasXML(lNrClRaCr,
                                  lCpLancto.FieldByName('NrClaDeb').AsString,
                                  'C',
                                  lCpLancto.FieldByName('CdEmpres').AsInteger,
                                  lDtLancto,
                                  lCpLancto.FieldByName('VrLancto').AsFloat,
                                  lCpLancto.FieldByName('NrDocto').AsString,
                                  lCpLancto.FieldByName('CdHistor').AsInteger,
                                  lCpLancto.FieldByName('DsHistor').AsString,
                                  lCpLancto.FieldByName('NmFormul').AsString,
                                  lCpLancto.FieldByName('SnRecEnc').AsString,
                                  lCpDsParam.FieldByName('NrPlano').AsInteger,
                                  lCpDsParam.FieldByName('CdUsuari').AsInteger,
                                  lCdsB004, lCdsB008, lCpCenCus.Data);
                      end else
                      begin
                            EfetuarLancamentoContabilXML(
                                                       lCpLancto.FieldByName('CdEmpres').AsInteger,
                                                       lDtLancto,
                                                       lCpLancto.FieldByName('NrConDeb').AsInteger,
                                                       lCpLancto.FieldByName('NrConCre').AsInteger,
                                                       lCpLancto.FieldByName('VrLancto').AsFloat,
                                                       lCpLancto.FieldByName('CdHistor').AsInteger,
                                                       lCpLancto.FieldByName('DsHistor').AsString,
                                                       lCpLancto.FieldByName('NmFormul').AsString,
                                                       lCpLancto.FieldByName('NrDocto').AsString,
                                                       lCpLancto.FieldByName('SnEncerr').AsString,
                                                       lCpDsParam.FieldByName('NrPlano').AsInteger,
                                                       lCpDsParam.FieldByName('CdUsuari').AsInteger,
                                                       lCdsB004,
                                                       lCdsB008,
                                                       lCpLancto.FieldByName('NrLancto').AsInteger,
                                                       lCpCenCus.Data);
                      end;
                end;

                lCpLancto.Next;
          end;
      finally
          FreeAndNil(lCpLancto);
          FreeAndNil(lcpDsParam);
          FreeAndNil(lCpCenCus);

          FreeAndNil(lNrClRaDe);
          FreeAndNil(lNrClRaCr);
          FreeAndNil(lBrvString);

          FreeAndNil(lCdsB004);
          FreeAndNil(lProB004);
          FreeAndNil(lDtsB004);

          FreeAndNil(lCdsB008);
          FreeAndNil(lProB008);
          FreeAndNil(lDtsB008);
      end;
end;

procedure TBrvContabil.LancarContabilidade(pCdsLancto : OleVariant; pNrPlano  : Integer;
                                           pCdUsuari  : Integer;    pNmFormul : String);
var lCdsLancto : TClientDataSet;
    lNrClRaDe  : TStrings;
    lNrClRaCr  : TStrings;

    lBrvString : TBrvString;
    lDsHistor  : AnsiString;

    lDtLancto  : String;

    lCdsB004   : TBrvClientDataSet;
    lProB004   : TBrvProvider;
    lDtsB004   : TBrvDataSet;

    lCdsB008   : TBrvClientDataSet;
    lProB008   : TBrvProvider;
    lDtsB008   : TBrvDataSet;
begin
      ValidarObjetos(Owner.Name, Name);

      lCdsLancto := TClientDataSet.Create(Self);

      // =-=-=-=-=-=-=-=-=-=
      // =-=-= Lançamentos contabeis
      // =-=-=-=-=-=-=-=-=-=

      try
          lCdsB004 := TBrvClientDataSet.Create(Self);
          lProB004 := TBrvProvider.Create(self);
          lDtsB004 := TBrvDataSet.Create(self);

          lProB004.DataSet       := lDtsB004;
          lProB004.Options       := [poAllowCommandText,poUseQuoteChar];
          lProB004.Name          := 'lProB004';
          lDtsB004.SQLConnection := gSqlConne;
          lDtsB004.BrDicionario  := gDsDicion;
          lCdsB004.ProviderName  := 'lProB004';
          lCdsB004.BrDicionario  := gDsDicion;
          lCdsB004.BrQueryCode   := 80;
          lCdsB004.BrFormName    := pNmFormul;
          lCdsB004.BrUserCode    := pCdUsuari;
          lCdsB004.Open;

          // =-=-=-=-=-=-=-=-=-=
          // =-=-= Lançamentos Centro de custo
          // =-=-=-=-=-=-=-=-=-=
          lCdsB008 := TBrvClientDataSet.Create(Self);
          lProB008 := TBrvProvider.Create(self);
          lDtsB008 := TBrvDataSet.Create(self);

          lProB008.DataSet       := lDtsB008;
          lProB008.Options       := [poAllowCommandText,poUseQuoteChar];
          lProB008.Name          := 'lProB008';
          lDtsB008.SQLConnection := gSqlConne;
          lDtsB008.BrDicionario  := gDsDicion;
          lCdsB008.ProviderName  := 'lProB008';
          lCdsB008.BrDicionario  := gDsDicion;
          lCdsB008.BrQueryCode   := 81;
          lCdsB008.BrFormName    := pNmFormul;
          lCdsB008.BrUserCode    := pCdUsuari;
          lCdsB008.Open;

          // =-=-=-=-=-=-=-=-=-=

          lNrClRaDe  := TStringList.Create;
          lNrClRaCr  := TStringList.Create;
          lBrvString := TBrvString.Create(self);

          lCdsLancto.Data := pCdsLancto;
          lCdsLancto.First;

          while not lCdsLancto.Eof do
          begin
                lNrClRaDe.Text := CarregarContasRateadas(
                                  lCdsLancto.FieldByName('NrClaDeb').AsString,
                                  pNrPlano);

                lNrClRaCr.Text := CarregarContasRateadas(
                                  lCdsLancto.FieldByName('NrClaCre').AsString,
                                  pNrPlano);

                ValidarLancamentoContabil(
                                  lCdsLancto.FieldByName('NrConDeb').AsInteger,
                                  lCdsLancto.FieldByName('NrConCre').AsInteger,
                                  lCdsLancto.FieldByName('CdHistor').AsInteger,
                                  lCdsLancto.FieldByName('CdEmpres').AsInteger,
                                  lCdsLancto.FieldByName('CdCeCuCr').AsInteger,
                                  lCdsLancto.FieldByName('CdCeCuDe').AsInteger,
                                  lCdsLancto.FieldByName('DtLancto').AsDateTime,
                                  lNrClRaDe, lNrClRaCr,
                                  lCdsLancto.FieldByName('NrClaCre').AsString,
                                  lCdsLancto.FieldByName('NrClaDeb').AsString,
                                  pNrPlano);

                lDsHistor := lBrvString.JustificarTexto(
                             lCdsLancto.FieldByName('DsHistor').AsString, 65);

                lDtLancto := FormatDateTime(ShortDateFormat,
                                 lCdsLancto.FieldByName('DtLancto').AsDateTime);

                if  lNrClRaDe.Count > 0 then
                begin
                      LancarContasRateadas(lNrClRaDe,
                                  lCdsLancto.FieldByName('NrClaCre').AsString,
                                  'D',
                                  lCdsLancto.FieldByName('CdEmpres').AsInteger,
                                  lDtLancto,
                                  lCdsLancto.FieldByName('VrLancto').AsFloat,
                                  lCdsLancto.FieldByName('NrDocto').AsString,
                                  lCdsLancto.FieldByName('CdHistor').AsInteger,
                                  lDsHistor,
                                  lCdsLancto.FieldByName('NmFormul').AsString,
                                  lCdsLancto.FieldByName('SnRecEnc').AsString,
                                  pNrPlano, pCdUsuari, lCdsB004, lCdsB008);
                end else
                begin
                      if  lNrClRaCr.Count > 0 then
                      begin
                            LancarContasRateadas(lNrClRaCr,
                                  lCdsLancto.FieldByName('NrClaDeb').AsString,
                                  'C',
                                  lCdsLancto.FieldByName('CdEmpres').AsInteger,
                                  lDtLancto,
                                  lCdsLancto.FieldByName('VrLancto').AsFloat,
                                  lCdsLancto.FieldByName('NrDocto').AsString,
                                  lCdsLancto.FieldByName('CdHistor').AsInteger,
                                  lDsHistor,
                                  lCdsLancto.FieldByName('NmFormul').AsString,
                                  lCdsLancto.FieldByName('SnRecEnc').AsString,
                                  pNrPlano, pCdUsuari, lCdsB004, lCdsB008);
                      end else
                      begin
                            EfetuarLancamentoContabil(
                                   lCdsLancto.FieldByName('CdEmpres').AsInteger,
                                   lDtLancto,
                                   lCdsLancto.FieldByName('NrConDeb').AsInteger,
                                   lCdsLancto.FieldByName('NrConCre').AsInteger,
                                   lCdsLancto.FieldByName('VrLancto').AsFloat,
                                   lCdsLancto.FieldByName('CdHistor').AsInteger,
                                   lDsHistor,
                                   lCdsLancto.FieldByName('NmFormul').AsString,
                                   lCdsLancto.FieldByName('NrDocto').AsString,
                                   lCdsLancto.FieldByName('SnEncerr').AsString,
                                   lCdsLancto.FieldByName('CdCeCuDe').AsInteger,
                                   lCdsLancto.FieldByName('CdCeCuCr').AsInteger,
                                   pNrPlano, pCdUsuari, lCdsB004, lCdsB008);
                      end;
                end;

                lCdsLancto.Next;
          end;
      finally
          FreeAndNil(lCdsLancto);
          FreeAndNil(lNrClRaDe);
          FreeAndNil(lNrClRaCr);
          FreeAndNil(lBrvString);

          FreeAndNil(lCdsB004);
          FreeAndNil(lProB004);
          FreeAndNil(lDtsB004);

          FreeAndNil(lCdsB008);
          FreeAndNil(lProB008);
          FreeAndNil(lDtsB008);
      end;
end;

procedure TBrvContabil.EfetuarLancamentoContabilXML(pCdEmpres : Integer;    pDtLancto : String;
                                                    pNrConDeb : Integer;    pNrConCre : Integer;
                                                    pVrLancto : Real;       pCdHistor : Integer;
                                                    pDsHistor : AnsiString; pNmFormul : String;
                                                    pNrDocto  : String;     pSnEncerr : String;
                                                    pNrPlano  : Integer;    pCdUsuari : Integer;
                                                    pCdsB004  : TBrvClientDataSet;
                                                    pCdsB008  : TBrvClientDataSet;
                                                    pNrLancto : Integer;
                                                    pCcCenCus : OleVariant);
var lNrLancto : Integer;
    lCpLanCen : TClientDataSet;
begin
      lNrLancto := gDsDicion.ProximoValorSequencial(pCdEmpres, 2);

      pCdsB004.Append;
      pCdsB004.FieldByName('CdEmpres').AsInteger  := pCdEmpres;
      pCdsB004.FieldByName('DtLancto').AsString   := pDtLancto;
      pCdsB004.FieldByName('NrLancto').AsInteger  := lNrLancto;
      pCdsB004.FieldByName('NrConDeb').AsInteger  := pNrConDeb;
      pCdsB004.FieldByName('NrConCre').AsInteger  := pNrConCre;
      pCdsB004.FieldByName('VrLancto').AsString   := FormatFloat('0.00', pVrLancto);
      pCdsB004.FieldByName('CdHistor').AsInteger  := pCdHistor;
      pCdsB004.FieldByName('DsComHis').AsString   := pDsHistor;
      pCdsB004.FieldByName('CdUsuari').AsInteger  := pCdUsuari;
      pCdsB004.FieldByName('DsOriLan').AsString   := pNmFormul;
      pCdsB004.FieldByName('NrDocLan').AsString   := pNrDocto;
      pCdsB004.FieldByName('SnEncerr').AsString   := pSnEncerr;
      pCdsB004.FieldByName('DtProces').AsDateTime := gDsDicion.DataHoraServer;
      pCdsB004.FieldByName('NrPlano' ).AsInteger  := pNrPlano;
      pCdsB004.Post;
      pCdsB004.BrApplyUpdates;

      lCpLanCen := TClientDataSet.Create(Self);
      lCpLanCen.Data := pCcCenCus;
      lCpLanCen.Filtered := False;
      lCpLanCen.Filter   := 'NrLancto = ' + FormatFloat('0', pNrLancto);
      lCpLanCen.Filtered := True;

      if (lCpLanCen.RecordCount > 0) then
      begin
            LancarCentroCustoXML(pCdEmpres, pDtLancto, lNrLancto, pNrConDeb, pNrConCre,
                                 pVrLancto, pNrPlano, pCdsB008, pNrLancto, pCcCenCus);
      end;
end;


procedure TBrvContabil.EfetuarLancamentoContabil(
                                            pCdEmpres : Integer;    pDtLancto : String;
                                            pNrConDeb : Integer;    pNrConCre : Integer;
                                            pVrLancto : Real;       pCdHistor : Integer;
                                            pDsHistor : AnsiString; pNmFormul : String;
                                            pNrDocto  : String;     pSnEncerr : String;
                                            pCdCeCuDe : Integer;    pCdCeCuCr : Integer;
                                            pNrPlano  : Integer;    pCdUsuari : Integer;
                                            pCdsB004  : TBrvClientDataSet;
                                            pCdsB008  : TBrvClientDataSet);
var lNrLancto : Integer;
begin
      lNrLancto := gDsDicion.ProximoValorSequencial(pCdEmpres, 2);

      pCdsB004.Append;
      pCdsB004.FieldByName('CdEmpres').AsInteger  := pCdEmpres;
      pCdsB004.FieldByName('DtLancto').AsString   := pDtLancto;
      pCdsB004.FieldByName('NrLancto').AsInteger  := lNrLancto;
      pCdsB004.FieldByName('NrConDeb').AsInteger  := pNrConDeb;
      pCdsB004.FieldByName('NrConCre').AsInteger  := pNrConCre;
      pCdsB004.FieldByName('VrLancto').AsString   := FormatFloat('0.00', pVrLancto);
      pCdsB004.FieldByName('CdHistor').AsInteger  := pCdHistor;
      pCdsB004.FieldByName('DsComHis').AsString   := pDsHistor;
      pCdsB004.FieldByName('CdUsuari').AsInteger  := pCdUsuari;
      pCdsB004.FieldByName('DsOriLan').AsString   := pNmFormul;
      pCdsB004.FieldByName('NrDocLan').AsString   := pNrDocto;
      pCdsB004.FieldByName('SnEncerr').AsString   := pSnEncerr;
      pCdsB004.FieldByName('DtProces').AsDateTime := gDsDicion.DataHoraServer;
      pCdsB004.FieldByName('NrPlano').AsInteger   := pNrPlano;
      pCdsB004.Post;
      pCdsB004.BrApplyUpdates;

      if (pCdCeCuDe <> 0) or (pCdCeCuCr <> 0) then
      begin
            LancarCentroCusto(pCdEmpres, pDtLancto, lNrLancto,
                              pNrConDeb, pNrConCre, pVrLancto,
                              pCdCeCuDe, pCdCeCuCr, pNrPlano,
                              pCdsB008);
      end;
end;

function TBrvContabil.CarregarContasRateadas(pNrClassi : String;
                                             pNrPlano  : Integer) : String;
var NrClaAux : String;
    NrAtuAux : String;
    NrNivFil : String;
    NrNivAtu : String;
    NrNivPai : String;
    NrNivFim : String;
    NrPosAtu : byte;

    lNrClaRat  : TStrings;
    lCdsConRat : TClientDataSet;
    lDsParams  : String;
begin
      lNrClaRat  := TStringList.Create;
      lCdsConRat := TClientDataSet.Create(self);

      try
          NrClaAux       := pNrClassi;
          EncontrarParentes(pNrClassi, NrNivPai, NrNivAtu, NrNivFil, NrPosAtu);

          lDsParams := '<%NrPlano%>;' + IntToStr(pNrPlano);


          if  NrNivAtu <> '' then
          begin
                NrAtuAux  := NrNivAtu;
                NrNivFim  := IntToStr(StrToInt(NrNivAtu) + 1);
                lDsParams := lDsParams + #13 +
                             '<%NrClaIni%>;' + pNrClassi + NrNivPai + '.'+
                                               NrNivAtu  + '.' + NrNivFil + '.' + #13 +

                             '<%NrClaFim%>;' + pNrClassi + NrNivPai + '.' + NrNivFim + '.';
          end else
          begin
                NrAtuAux := NrNivPai;
                NrNivFim := IntToStr(StrToInt(NrNivPai) + 1);

                lDsParams := lDsParams + #13 +
                             '<%NrClaIni%>;' +  pNrClassi + NrNivPai + '.' + #13 +
                             '<%NrClaFim%>;' +  pNrClassi + NrNivFim + '.';
          end;

          lCdsConRat.Data := gDsDicion.RetornaDadosSqlCadastro(83, lDsParams, Name);

          while not lCdsConRat.Eof do
          begin
                if  (MesmoNivel(lCdsConRat.FieldByName('NrClassi').AsString, NrAtuAux,
                     NrPosAtu -1))                                                    and

                    (NrClaAux <> lCdsConRat.FieldByName('NrClassi').AsString) then
                begin
                      if  lCdsConRat.FieldByName('SnRecLan').AsString <> 'S' then
                      begin
                            raise Exception.Create('Conta ' +
                                            lCdsConRat.FieldByName('NrClassi').AsString +
                                            ' não pode receber lançamento');
                      end;

                      lNrClaRat.Add(lCdsConRat.FieldByName('NrClassi').AsString);
                end;

                lCdsConRat.Next;
          end;
      finally
          Result := lNrClaRat.Text;
          FreeAndNil(lNrClaRat);
          FreeAndNil(lCdsConRat);
      end;
end;

function TBrvContabil.MesmoNivel(pNrClassi : String; pNrPai : String;
                                 pNrAtual  : Byte): Boolean;
var lNrNivFil : String;
    lNrNivAtu : String;
    lNrNivPai : String;
    lNrPosAtu : byte;
begin
      EncontrarParentes(pNrClassi, lNrNivPai, lNrNivAtu, lNrNivFil, lNrPosAtu);

      if  (lNrNivPai = pNrPai) and
          (lNrPosAtu = pNrAtual) then
      begin
            Result := True;
      end else
      begin
            Result := False;
      end;
end;

procedure TBrvContabil.EncontrarParentes(var pNrClassi : String; var pNrNivPai : String;
                                         var pNrNivAtu : String; var pNrNivFil : String;
                                         var pNrPosAtu : byte);
var lSnPai   : Boolean;
    lSnAtual : Boolean;
    x        : byte;
begin
      lSnPai    := False;
      lSnAtual  := False;
      pNrPosAtu := 1;
      x        := Length(pNrClassi);

      while (not lSnPai) and (x > 0) do
      begin
            if  pNrClassi[x] = '.' then
            begin
                  if  StrToInt(pNrNivPai) <> 0 then
                  begin
                        if lSnAtual then
                        begin
                              lSnPai := True;
                        end else
                        begin
                              lSnAtual    := True;
                              pNrNivAtu   := pNrNivPai;
                              pNrNivPai   :=  '';
                              Delete(pNrClassi, x, 1);
                        end;
                  end else
                  begin
                        if  not lSnAtual then
                        begin
                              inc(pNrPosAtu);
                              pNrNivFil := pNrNivPai;
                        end;

                        pNrNivPai    :=  '';
                        Delete(pNrClassi, x, 1);
                  end;
            end else
            begin
                  pNrNivPai    := pNrClassi[x] + pNrNivPai;
                  Delete(pNrClassi, x, 1);
            end;

            dec(x);
      end;
end;

procedure TBrvContabil.LancarCentroCusto(pCdEmpres : Integer; pDtLancto : String;
                                         pNrLancto : Integer; pNrConDeb : Integer;
                                         pNrConCre : Integer; pVrLancto : Real;
                                         pCdCeCuDe : Integer; pCdCeCuCr : Integer;
                                         pNrPlano  : Integer;
                                         pCdsB008  : TBrvClientDataSet);
var lNrIndice : Integer;
    lCdCenCus : Integer;
    lNrConCon : Integer;
    lTpLancto : String;
begin
      lCdCenCus := pCdCeCuDe;
      lTpLancto := 'D';
      lNrConCon := pNrConDeb ;

      for lNrIndice := 1 to 2 do
      begin
            if lNrIndice = 2 then
            begin
                  lCdCenCus := pCdCeCuCr;
                  lTpLancto := 'C';
                  lNrConCon := pNrConCre;
            end;

            if lCdCenCus > 0 then
            begin
                  pCdsB008.Append;
                  pCdsB008.FieldByName('NrLancto').AsInteger  := pNrLancto;
                  pCdsB008.FieldByName('TpLancto').AsString   := lTpLancto;
                  pCdsB008.FieldByName('CdCenCus').AsInteger  := lCdCenCus;
                  pCdsB008.FieldByName('PcRateio').AsInteger  := 100;
                  pCdsB008.FieldByName('VrLancto').AsFloat    := pVrLancto;
                  pCdsB008.Post;
                  pCdsB008.BrApplyUpdates;
            end else
            begin
                  LancarCentroCustoRateio(pCdEmpres, pDtLancto, pNrLancto, lNrConCon,
                                          lTpLancto, pVrLancto);
            end;
      end;
end;

procedure TBrvContabil.LancarCentroCustoXML(pCdEmpres : Integer; pDtLancto : String;
                                            pNrLancto : Integer; pNrConDeb : Integer;
                                            pNrConCre : Integer; pVrLancto : Real;
                                            pNrPlano  : Integer;
                                            pCdsB008  : TBrvClientDataSet;
                                            pNrLanCen : Integer;
                                            pCcCenCus : OleVariant);
var lCcCenCus : TClientDataSet;
begin
      try
          lCcCenCus := TClientDataSet.Create(Self);
          lCcCenCus.Data := pCcCenCus;


          lCcCenCus.Data     := pCcCenCus;
          lCcCenCus.Filtered := False;
          lCcCenCus.Filter   := 'NrLancto = ' + FormatFloat('0', pNrLanCen);
          lCcCenCus.Filtered := True;
          lCcCenCus.First;

          while not lCcCenCus.Eof do
          begin
                pCdsB008.Append;
                pCdsB008.FieldByName('NrLancto').AsInteger  := pNrLancto;
                pCdsB008.FieldByName('TpLancto').AsString   :=
                                                        lCcCenCus.FieldByName('TpLancto').AsString;
                pCdsB008.FieldByName('CdCenCus').AsInteger  :=
                                                        lCcCenCus.FieldByName('CdCenCus').AsInteger;
                pCdsB008.FieldByName('PcRateio').AsInteger  := 100;
                pCdsB008.FieldByName('VrLancto').AsFloat    := pVrLancto;
                pCdsB008.Post;
                pCdsB008.BrApplyUpdates;

                lCcCenCus.Next;
          end;

      finally
          FreeAndNil(lCcCenCus);
      end;
end;

procedure TBrvContabil.LancarContasRateadasXML(pNrClaRat : TStrings;   pNrClassi : String;
                                               pTpNatOpe : String;     pCdEmpres : Integer;
                                               pDtLancto : String;     pVrLancto : Real;
                                               pNrDocto  : String;     pCdHistor : Integer;
                                               pDsHistor : AnsiString; pNmFormul : String;
                                               pSnRecerr : String;     pNrPlano  : Integer;
                                               pCdUsuari : Integer;
                                               pCdsB004  : TBrvClientDataSet;
                                               pCdsB008  : TBrvClientDataSet;
                                               pCcCenCus : OleVariant);
var x        : Integer;
    lVrTotLan : Real;
    lVrParLan : Real;
    lNrLancto : Integer;

    lNrConCre : Integer;
    lNrConDeb : Integer;
begin
      lVrTotLan := 0;
      lVrParLan := pVrLancto / pNrClaRat.Count;

      for x := 0 to pNrClaRat.Count -1 do
      begin
            if  pTpNatOpe = 'D' then
            begin
                  lNrConDeb := NumeroConta(pNrPlano, pNrClaRat.Strings[x]);
                  lNrConCre := NumeroConta(pNrPlano, pNrClassi);
            end else
            begin
                  lNrConDeb := NumeroConta(pNrPlano, pNrClassi);
                  lNrconCre := NumeroConta(pNrPlano, pNrClaRat.Strings[x]);
            end;

            lVrTotLan := lVrTotLan  +  StrToFloat(FormatFloat('0.00', lVrParLan));

            if x = (pNrClaRat.Count -1) then
            begin
                  if  lVrTotLan <> pVrLancto then
                  begin
                        lVrParLan := lVrParLan + (pVrLancto - lVrTotLan);
                  end;
            end;

            EfetuarLancamentoContabilXML(pCdEmpres,  pDtLancto, lNrConDeb, lNrConCre,
                                         lVrParLan,  pCdHistor, pDsHistor, pNmFormul,
                                         pNrDocto ,  pSnRecerr, pNrPlano , pCdUsuari,
                                         pCdsB004 ,  pCdsB008 , 9999     , pCcCenCus);
      end;
end;

procedure TBrvContabil.LancarContasRateadas(pNrClaRat : TStrings;   pNrClassi : String;
                                            pTpNatOpe : String;     pCdEmpres : Integer;
                                            pDtLancto : String;     pVrLancto : Real;
                                            pNrDocto  : String;     pCdHistor : Integer;
                                            pDsHistor : AnsiString; pNmFormul : String;
                                            pSnRecerr : String;     pNrPlano  : Integer;
                                            pCdUsuari : Integer;
                                            pCdsB004  : TBrvClientDataSet;
                                            pCdsB008  : TBrvClientDataSet);
var x        : Integer;
    lVrTotLan : Real;
    lVrParLan : Real;
    lNrLancto : Integer;

    lNrConCre : Integer;
    lNrConDeb : Integer;
begin
      lVrTotLan := 0;
      lVrParLan := pVrLancto / pNrClaRat.Count;

      for x := 0 to pNrClaRat.Count -1 do
      begin
            if  pTpNatOpe = 'D' then
            begin
                  lNrConDeb := NumeroConta(pNrPlano, pNrClaRat.Strings[x]);
                  lNrConCre := NumeroConta(pNrPlano, pNrClassi);
            end else
            begin
                  lNrConDeb := NumeroConta(pNrPlano, pNrClassi);
                  lNrconCre := NumeroConta(pNrPlano, pNrClaRat.Strings[x]);
            end;

            lVrTotLan := lVrTotLan  +  StrToFloat(FormatFloat('0.00', lVrParLan));

            if x = (pNrClaRat.Count -1) then
            begin
                  if  lVrTotLan <> pVrLancto then
                  begin
                        lVrParLan := lVrParLan + (pVrLancto - lVrTotLan);
                  end;
            end;

            EfetuarLancamentoContabil(pCdEmpres,  pDtLancto, lNrConDeb, lNrConCre,
                                      lVrParLan,  pCdHistor, pDsHistor, pNmFormul,
                                      pNrDocto,   pSnRecerr, 0, 0, pNrPlano, pCdUsuari,
                                      pCdsB004,   pCdsB008);
      end;
end;

function TBrvContabil.NumeroConta(pNrPlano : Integer; pNrClassi : String) : Integer;
var lDsParams  : TStrings;
    lCdsPlaCon : TClientDataSet;
begin
      Result := 0;
      try
          lDsParams := TStringList.Create;
          lDsParams.Clear;

          lDsParams.Add('<%NrPlano%>;'  + IntToStr(pNrPlano));
          lDsParams.Add('<%NrClassi%>;' + pNrClassi);


          lCdsPlaCon      := TClientDataSet.Create(Self);
          lCdsPlaCon.Data := gDsDicion.RetornaDadosSqlCadastro(82, lDsParams.Text, Name);

          Result           :=  lCdsPlaCon.FieldByName('NrConta').AsInteger;
      except
          FreeAndNil(lCdsPlaCon);
          FreeAndNil(lDsParams);
      end;
end;

procedure TBrvContabil.ValidarLancamentoContabil(
                                               pNrConDeb : Integer;  pNrConCre : Integer;
                                               pCdHistor : Integer;  pCdEmpres : Integer;
                                               pCdCeCuCr : Integer;  pCdCeCuDe : Integer;
                                               pDtLancto : TDate;
                                               pNrClRaDe : TStrings; pNrClRaCr : TStrings;
                                               pNrClaCre : String;   pNrClaDeb : String;
                                               pNrPlano  : Integer);
begin
      if  pCdEmpres <= 0 then
      begin
            raise Exception.Create('Empresa não informada');
      end;

      if pNrConDeb = 0 then
      begin
            raise Exception.Create('Conta de débito não foi informada');
      end;

      if pNrConCre = 0 then
      begin
            raise Exception.Create('Conta de crédito não foi informada');
      end;

      ValidarHistorico(pCdHistor, '"CT", "PT", "RC"');

      ValidarCentroDeCustoContabil(pNrPlano, pNrConCre, pNrConDeb,
                                   pCdCeCuCr, pCdCeCuDe);

      ValidarPeriodoContabil(pDtLancto, pCdEmpres);

      if (pNrClRaDe.Count > 0) and (pNrClRaCr.Count > 0) then
      begin
            raise Exception.Create('Ambas contas: ' + IntToStr(pNrConDeb) + ' e ' +
                                                      IntToStr(pNrConCre) +
                                   ', não podem ser rateadas');
      end;

      if  pNrClRaDe.Count > 0 then
      begin
            ValidarContasRateada(pNrClRaDe, pNrClaCre, pNrClaDeb);
      end;

      if  pNrClRaCr.Count > 0 then
      begin
            ValidarContasRateada(pNrClRaCr, pNrClaDeb, pNrClaCre);
      end;
end;

procedure TBrvContabil.ValidarLancamentoContabilXML(pNrConDeb : Integer;  pNrConCre : Integer;
                                                    pCdHistor : Integer;  pCdEmpres : Integer;
                                                    pNrClRaDe : TStrings; pNrClRaCr : TStrings;
                                                    pNrClaCre : String;   pNrClaDeb : String;
                                                    pNrPlano  : Integer;  pNrLancto : Integer;
                                                    pCcCenCus : OleVariant);
var lCpCenVal : TClientDataSet;
begin
      if  pCdEmpres <= 0 then
      begin
            raise Exception.Create('Empresa não informada');
      end;

      if pNrConDeb = 0 then
      begin
            raise Exception.Create('Conta de débito não foi informada');
      end;

      if pNrConCre = 0 then
      begin
            raise Exception.Create('Conta de crédito não foi informada');
      end;

      ValidarHistorico(pCdHistor, '"CT", "PT", "RC"');

      lCpCenVal := TClientDataSet.Create(Self);
      lCpCenVal.Data := pCcCenCus;

      lCpCenVal.Filtered := False;
      lCpCenVal.Filter   := 'NrLancto = ' + FormatFloat('0', pNrLancto) + ' and TpLancto = ' +
                                                                                     QuotedStr('C');
      lCpCenVal.Filtered := True;
      ValidarCentroDeCustoContabilXML(pNrPlano, pNrConCre, lCpCenVal.RecordCount);

      lCpCenVal.Filtered := False;
      lCpCenVal.Filter   := 'NrLancto = ' + FormatFloat('0', pNrLancto) + ' and TpLancto = ' +
                                                                                     QuotedStr('D');
      lCpCenVal.Filtered := True;
      ValidarCentroDeCustoContabilXML(pNrPlano, pNrConDeb, lCpCenVal.RecordCount);

      if (pNrClRaDe.Count > 0) and (pNrClRaCr.Count > 0) then
      begin
            raise Exception.Create('Ambas contas: ' + IntToStr(pNrConDeb) + ' e ' +
                                                      IntToStr(pNrConCre) +
                                   ', não podem ser rateadas');
      end;

      if  pNrClRaDe.Count > 0 then
      begin
            ValidarContasRateada(pNrClRaDe, pNrClaCre, pNrClaDeb);
      end;

      if  pNrClRaCr.Count > 0 then
      begin
            ValidarContasRateada(pNrClRaCr, pNrClaDeb, pNrClaCre);
      end;
end;

procedure TBrvContabil.ValidarCentroDeCustoContabil(
                                               pNrPlano  : Integer; pNrConCre : Integer;
                                               pNrConDeb : Integer; pCdCeCuCr : Integer;
                                               pCdCeCuDe : Integer);
var lCdsCenCus : TClientDataSet;
    lDsParams  : String;
begin
      try
          lDsParams  := '<%NrPlano%>;'  + IntToStr(pNrPlano) + #13 +
                        '<%NrConCre%>;' + IntToStr(pNrConCre) + #13 +
                        '<%NrConDeb%>;' + IntToStr(pNrConDeb);

          lCdsCenCus := TClientDataSet.Create(Self);
          lCdsCenCus.Data := gDsDicion.RetornaDadosSqlCadastro(85, lDsParams, Name);

          if lCdsCenCus.FieldByName('SnConCre').AsString = 'S' then
          begin
                if pCdCeCuCr = 0 then
                begin
                      raise Exception.Create('Conta contábil crédito [' +
                                             IntToStr(pNrConCre)         +
                                             '] exige centro de custo.');
                end;
          end else
          begin
                if pCdCeCuCr <> 0 then
                begin
                      raise Exception.Create('Conta contábil crédito [' +
                                             IntToStr(pNrConCre)         +
                                             '] não exige centro de custo.');
                end;
          end;

          if lCdsCenCus.FieldByName('SnConDeb').AsString = 'S' then
          begin
                if pCdCeCuDe = 0 then
                begin
                      raise Exception.Create('Conta contábil débito [' +
                                             IntToStr(pNrConDeb)       +
                                             '] exige centro de custo.');
                end;
          end else
          begin
                if pCdCeCuDe <> 0 then
                begin
                      raise Exception.Create('Conta contábil débito ['    +
                                             IntToStr(pNrConDeb)           +
                                             '] não exige centro de custo.');
                end;
          end;
      finally
          FreeAndNil(lCdsCenCus);
      end;
end;

procedure TBrvContabil.ValidarCentroDeCustoContabilXML(pNrPlano  : Integer; pNrConta  : Integer;
                                                       pQtLanCen : Integer);
var lCdsCenCus : TClientDataSet;
begin
      try
          lCdsCenCus := TClientDataSet.Create(Self);
          lCdsCenCus.Data := gDsDicion.RetornaDadosSqlCadastro(204,
                                                        '<%NrPlano%>;' + IntToStr(pNrPlano)  + #13 +
                                                        '<%NrConta%>;' + IntToStr(pNrConta), Name);

          if (lCdsCenCus.FieldByName('SnConCen').AsString = 'S') then
          begin
                if pQtLanCen = 0 then
                begin
                      raise Exception.Create('Lançamento contábil exige centro de custo.' + #13 +
                                              IntToStr(pNrPlano) + ':' + IntToStr(pNrConta));
                end;
          end else
          begin
                if pQtLanCen > 0 then
                begin
                      raise Exception.Create('Lançamento contábil não exige centro de custo.' + #13+
                                              IntToStr(pNrPlano) + ':' + IntToStr(pNrConta));
                end;
          end;
      finally
          FreeAndNil(lCdsCenCus);
      end;
end;

procedure TBrvContabil.ValidarPeriodoContabil(pDtLancto : TDate; pCdEmpres : Integer);
var lDtAno    : Word;
    lDtMes    : Word;
    lDtDia    : Word;
    lStPeriod : String;
begin
      DecodeDate(pDtLancto, lDtAno, lDtMes, lDtDia);
      lStPeriod := PeriodoContabil(pCdEmpres, lDtMes, lDtAno);

      if  lStPeriod = '' then
      begin
            raise Exception.Create('Período contábil '                        +
                                    FormatFloat('00', lDtMes) + DateSeparator +
                                    FormatFloat('0000', lDtAno)               +
                                    ' não está aberto para empresa '          +
                                    IntToStr(pCdEmpres)                       +
                                    '. Não pode receber lançamentos');
      end else
      begin
            if  lStPeriod = 'F' then
            begin
                  raise Exception.Create('Período ' +
                             FormatFloat('00', lDtMes) + DateSeparator +
                             FormatFloat('0000', lDtAno) +
                             ' para empresa ' + IntToStr(pCdEmpres) +
                             ' já encerrado. Não pode receber lançamentos');
            end;
      end;
end;

function TBrvContabil.PeriodoContabil(pCdEmpres : Integer; pDtMes : Byte;
                                      pDtAno    : Integer) : String;
var lCdsPerCon : TClientDataSet;
    lDsParams  : String;
    lDtsExecut : TBrvDataSet;
begin
      Result  := '';

      try
          lDtsExecut := TBrvDataSet.Create(Self);
          lDtsExecut.SQLConnection := gSqlConne;
          lDtsExecut.BrDicionario  := gDsDicion;

          lDsParams  := '<%CdEmpres%>;' + IntToStr(pCdEmpres) + #13 +
                        '<%DtMes%>;'    + IntToStr(pDtMes)    + #13 +
                        '<%DtAno%>;'    + IntToStr(pDtAno);

          lCdsPerCon      := TClientDataSet.Create(Self);
          lCdsPerCon.Data := gDsDicion.RetornaDadosSqlCadastro(86, lDsParams, Name);

          if  not lCdsPerCon.IsEmpty then
          begin
                Result := lCdsPerCon.FieldByName('StAbeFec').AsString;
          end else
          begin
                lDtsExecut.CommandText :=
                         'Insert Into B009 (CdEmpres, DtMes, DtAno, StAbeFec) values (' +
                         IntToStr(pCdEmpres) + ', ' + IntToStr(pDtMes) + ', ' +
                         IntToStr(pDtAno)    + ', ' + #39 + 'A' + #39 + ')';
                lDtsExecut.ExecSQL(True);

                Result := 'A';
          end;
      finally
          FreeAndNil(lCdsPerCon);
          FreeAndNil(lDtsExecut);
      end;
end;

procedure TBrvContabil.ValidarContasRateada(pNrClaRat : TStrings; pNrClassi: String;
                                            pNrOutCla : String);
var x : Integer;
begin
      x := 0;

      while (x <=  pNrClaRat.Count - 1) do
      begin
            if  pNrClaRat.Strings[x] = pNrClassi then
            begin
                  raise Exception.Create('Conta ' + pNrClassi +
                        ' também está presente no grupo de rateio da conta ' + pNrOutCla);
            end;
            inc(x);
      end;
end;

procedure TBrvContabil.LancarCentroCustoRateio(pCdEmpres : Integer; pDtLancto : String;
                                               pNrLancto : Integer; pNrConCon : Integer;
                                               pTpLancto : String;  pVrLancto : Real);
var lVrTotal   : Real;
    lCdsPlaCus : TClientDataSet;
    lDsParams  : String;
begin
      lVrTotal := 0;

      lDsParams       := '<%CdEmpres%>;' + IntToStr(pCdEmpres) + #39 +
                         '<%NrConta%>;'  + IntToStr(pNrConCon);

{
  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
  =-=-=-= Uberaba, 23 de fevereiro de 2012 << Jefferson >>
  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
  =-=-=-= procedimento utilizado para fazer lançamento rateado por centro de custo
  =-=-=-= Até o momento, a Bravo não utiliza o recurso de rateio
  =-=-=-= Toda funcionalidade (procedimentos e funções) foram mantidas
  =-=-=-= Este procedimento está comentado pelo fato da tabela PlanoCentroCus não ter sido
  =-=-=-= migrada
  =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

      try
          VitalCode do sistema antigo = 1211

          lCdsPlaCus      := TClientDataSet.Create(Self);
          lCdsPlaCus.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(9999,
                                                                         lDsParams, Name);

          while not lCdsPlaCus.Eof do
          begin
                CcLanCen.Append;
                CcLanCen.FieldByName('CdEmpres').AsInteger  := pCdEmpres;
                CcLanCen.FieldByName('DtLancto').AsDateTime := pDtLancto;
                CcLanCen.FieldByName('NrLancto').AsInteger  := pNrLancto;
                CcLanCen.FieldByName('TpLancto').AsString   := pTpLancto;
                CcLanCen.FieldByName('NrConCon').AsInteger  := pNrConCon;
                CcLanCen.FieldByName('CdCenCus').AsInteger  :=
                         lCdsPlaCus.FieldByName('CdCenCus').AsInteger;
                CcLanCen.FieldByName('PcRateio').AsInteger  :=
                         lCdsPlaCus.FieldByName('PcRateio').AsInteger;
                CcLanCen.FieldByName('VrLancto').AsFloat  :=
                         lCdsPlaCus.FieldByName('PcRateio').AsInteger * (pVrLancto / 100);

                lVrTotal := lVrTotal + CcLanCen.FieldByName('VrLancto').AsFloat;

                lCdsPlaCus.Next;
          end;
          // Jogar a diferença de centavos para o último lançamento

          if (pVrLancto <> lVrTotal) and (CcLanCen.State = dsInsert) then
          begin
                CcLanCen.FieldByName('VrLancto').AsFloat :=
                        CcLanCen.FieldByName('VrLancto').AsFloat + (pVrLancto - lVrTotal);
          end;
      finally
          FreeAndNil(lCdsPlaCus);
      end;
}
end;

function TBrvContabil.NumeroClassificacao(pNrPlano, pNrConta: Integer): String;
var lDsParams  : String;
    lCdsPlaCon : TClientDataSet;
begin
      Result := '';
      try
          lDsParams := '<%NrPlano%>;'  + IntToStr(pNrPlano) + #13 +
                       '<%NrConta%>;'  + IntToStr(pNrConta);

          lCdsPlaCon      := TClientDataSet.Create(Self);
          lCdsPlaCon.Data := gDsDicion.RetornaDadosSqlCadastro(108, lDsParams, Name);

          Result           :=  lCdsPlaCon.FieldByName('NrClassi').AsString;
      except
          FreeAndNil(lCdsPlaCon);
      end;
end;

procedure TBrvContabil.CriarTabelaLanctoContabil(pCdsLanCon : TClientDataSet);
begin
      pCdsLanCon.FieldDefs.Clear;

      pCdsLanCon.FieldDefs.Add('CdEmpres',   ftInteger, 0, False);
      pCdsLanCon.FieldDefs.Add('DtLancto',   ftDate,    0, False);
      pCdsLanCon.FieldDefs.Add('NrConDeb',   FtFloat,   0, False);
      pCdsLanCon.FieldDefs.Add('NrConCre',   FtInteger, 0, False);
      pCdsLanCon.FieldDefs.Add('VrLancto',   FtFloat,   0, False);
      pCdsLanCon.FieldDefs.Add('NrDocto',    FtString, 30, False);
      pCdsLanCon.FieldDefs.Add('CdHistor',   FtInteger, 0, False);
      pCdsLanCon.FieldDefs.Add('DsHistor',   FtMemo,    0, False);
      pCdsLanCon.FieldDefs.Add('NmFormul',   FtString, 20, False);
      pCdsLanCon.FieldDefs.Add('CdCeCuDe',   FtInteger, 0, False);
      pCdsLanCon.FieldDefs.Add('CdCeCuCr',   FtInteger, 0, False);
      pCdsLanCon.FieldDefs.Add('NrClaDeb',   FtString, 60, False);
      pCdsLanCon.FieldDefs.Add('NrClaCre',   FtString, 60, False);
      pCdsLanCon.FieldDefs.Add('SnEncerr',   FtString,  1, False);


      pCdsLanCon.IndexDefs.Clear;
      pCdsLanCon.CreateDataSet;
end;


end.
