unit UDmAdm;

interface

uses
  SysUtils, Classes, FMTBcd, DB, SqlExpr, BrvDataSet, Provider, BrvProvider, DBClient,
  BrvClientDataSet, DBXOracle, BrvConnection, BrvGestao, BrvContabil, BrvContas;

type
  TDmAdm = class(TDataModule)
    CcF013: TBrvClientDataSet;
    DcF013: TBrvProvider;
    QcF013: TBrvDataSet;
    SqlConnAdm: TBrvConnection;
    QpF013: TBrvClientDataSet;
    QpF014: TBrvClientDataSet;
    QcF014: TBrvDataSet;
    CcF014: TBrvClientDataSet;
    DcF014: TBrvProvider;
    QpF015: TBrvClientDataSet;
    CcF015: TBrvClientDataSet;
    QcF015: TBrvDataSet;
    DcF015: TBrvProvider;
    QcF013Aut: TBrvDataSet;
    CcF013Aut: TBrvClientDataSet;
    DcF013Aut: TBrvProvider;
    CcF013Lote: TBrvClientDataSet;
    QcF013Lote: TBrvDataSet;
    DcF013Lote: TBrvProvider;
    QcF012: TBrvDataSet;
    CcF012: TBrvClientDataSet;
    DcF012: TBrvProvider;
    QcF012Rec: TBrvDataSet;
    CcF012Rec: TBrvClientDataSet;
    DcF012Rec: TBrvProvider;
    QpF012: TBrvClientDataSet;
    QpF017: TBrvClientDataSet;
    QcF017: TBrvDataSet;
    CcF017: TBrvClientDataSet;
    DcF017: TBrvProvider;
    CpF018: TBrvClientDataSet;
    CpF001: TBrvClientDataSet;
    CpF002: TBrvClientDataSet;
    CpN002: TBrvClientDataSet;
    CpN003: TBrvClientDataSet;
    CpParCon: TBrvClientDataSet;
    CcF001: TBrvClientDataSet;
    DcF001: TBrvProvider;
    QcF001: TBrvDataSet;
    QcF002: TBrvDataSet;
    CcF002: TBrvClientDataSet;
    DcF002: TBrvProvider;
    QcN002: TBrvDataSet;
    CcN002: TBrvClientDataSet;
    DcN002: TBrvProvider;
    QcN003: TBrvDataSet;
    CcN003: TBrvClientDataSet;
    DcN003: TBrvProvider;
    BrvContabil: TBrvContabil;
    DcF013Fecha: TBrvProvider;
    CcF013Fecha: TBrvClientDataSet;
    QcF013Fecha: TBrvDataSet;
    QcB003: TBrvDataSet;
    CcB003: TBrvClientDataSet;
    DcB003: TBrvProvider;
    CpB003: TBrvClientDataSet;
    CcN006LancNfCc: TBrvClientDataSet;
    QcN006LancNfCc: TBrvDataSet;
    DcN006LancNfCc: TBrvProvider;
    CpN006NotaConta: TBrvClientDataSet;
    BrvContas: TBrvContas;
    QcQ004: TBrvDataSet;
    DcQ004: TBrvProvider;
    CcQ004: TBrvClientDataSet;
    QcQ005Rem: TBrvDataSet;
    QcQ005: TBrvDataSet;
    DcQ005: TBrvProvider;
    CcQ005: TBrvClientDataSet;
    QcQ006: TBrvDataSet;
    DcQ006: TBrvProvider;
    CcQ006: TBrvClientDataSet;
    QcQ006Rem: TBrvDataSet;
    QcQ007: TBrvDataSet;
    DcQ007: TBrvProvider;
    CcQ007: TBrvClientDataSet;
    QcQ007Rem: TBrvDataSet;
    CcT002: TBrvClientDataSet;
    DcT002: TBrvProvider;
    QcT002: TBrvDataSet;
    CcT021: TBrvClientDataSet;
    DcT021: TBrvProvider;
    QcT021: TBrvDataSet;
    QcT008: TBrvDataSet;
    DcT008: TBrvProvider;
    CcT008: TBrvClientDataSet;
    QcW005: TBrvDataSet;
    DcW005: TBrvProvider;
    CcW005: TBrvClientDataSet;
    QcS052Upd: TBrvDataSet;
    QcS052: TBrvDataSet;
    DcS052: TBrvProvider;
    CcS052: TBrvClientDataSet;
    DcF004: TBrvProvider;
    CcF004: TBrvClientDataSet;
    QcF004: TBrvDataSet;
    DcF003: TBrvProvider;
    QcF003: TBrvDataSet;
    CcF003: TBrvClientDataSet;
    QcQ004CadDis: TBrvDataSet;
    DcQ004CadDis: TBrvProvider;
    CcQ004CadDis: TBrvClientDataSet;
    procedure SqlConnAdmAfterConnect(Sender: TObject);
  private
    procedure GravarCabecalhoNFEntrada;
    procedure GravarItensNFEntrada;
    procedure GravarContasPagar;
    procedure AbreCdsFinalizarDigitacaoNota;
    procedure FixaCdsFinalizarDigitacaoNota;
    procedure EventoTitular(pCdTitula : Integer; var lCdEvento, lCdForPag : Integer);
    { Private declarations }
  public
    { Public declarations }
    function GravarPreLancamentoNFEntrada(pDtF013 : OleVariant; pDtF014 : OleVariant;
                                          pDtF015 : OleVariant; pDtF017 : OleVariant): AnsiString;

    function AutorizaPreLancamentoNFEntrada(pDtF013: OleVariant;
                                            pCdUsuari : Integer): AnsiString;

    function MontaLotePreLancamentoNFEntrada(pDtF013: OleVariant;
                                             pCdUsuari, pNrLote: Integer;
                                             pDsLote : AnsiString): AnsiString;

    function FinalizarPreLancamentoNFEntrada(pNrPleLan: Integer; pCdUsuari: Integer;
                                                                 pSnFixa: String = 'S'): AnsiString;

    function RecepcionaLotePreLancamentoNFEntrada(pDtF012: OleVariant;
                                                  pCdUsuari: Integer): AnsiString;

    function PeriodoContabilValido(pDtF018: OleVariant): AnsiString;

    function FinalizarDigitacaoNota(pDtF001, pDtF002, pDtN002,
                                    pDtN003, pDtParCon: OleVariant;
                                    pCdUsuari : Integer): AnsiString;

    function EfetuarLancamentosContabeis(pDtParCon: OleVariant): AnsiString;
    function GravarSaldoPlano(pDtSalCon: OleVariant; pNmFormul: String;
                                                                    pCdUsuari: Integer): AnsiString;
    function BaixarDocumento(pTpOperac: String; pDtN002: OleVariant; pDtParCon: OleVariant;
                             pCdUsuari: Integer; pCdHistor : Integer;
                             pDtBaixa: TDateTime): AnsiString;

    function Faturar(pDsCreden : String; pDtParam  : OleVariant) : AnsiString;

    function LancarContasPagarReceber(pNmFormul : String;       pCdHistor : Integer;
                                      pTpPagRec : String;       pVrLancto : Double;
                                      pCdForPag : Integer;      pCdTitula : Integer;
                                      pCdEmpres : Integer;      pNrDocto  : Integer;
                                      pDtEmissa : TDateTime;    pNrPreCar : Integer;
                                      pNrOrdem  : Integer;      pDtVencto : TDateTime;
                                      pCdEvento : Integer;      pTpPagto  : String;
                                      pNrConCon : Integer;      pDsComHis : String;
                                      pCdCenCus : Integer;      pNrPlano  : Integer): AnsiString;

    function GravarRNC(pDtParam: OleVariant; pCdUsuari: Integer): AnsiString;

    function GravarDisposicaoRNC(pDtParam: OleVariant; pCdUsuari: Integer): AnsiString;

    function GravarRevisoesDataEntrega(pDtParam: OleVariant; pCdUsuari: Integer): AnsiString;

    function LiberarDesmontarCarga(pDtParam: OleVariant; pCdUsuari: Integer): AnsiString;

    function AtualizarOperacaoArmazem(pDtParam: OleVariant; pCdUsuari: Integer): AnsiString;

    function AtualizarChaves(pDtParam: OleVariant; pCdUsuari: Integer): AnsiString;

    function ValidarChaveLiberacao(pDtParam: OleVariant; pCdUsuari: Integer): AnsiString;

    function AtualizarChaveLiberacao(pDtParam: OleVariant; pCdUsuari: Integer): AnsiString;

    function CancelarNotaFiscal(pDtF018: OleVariant): AnsiString;
    function GravarXMLNotaFiscal(pDtF003: OleVariant): AnsiString;

  end;

var
  DmAdm: TDmAdm;

implementation

uses UDmDicion, UFrmConSrv, UFrmLogos;

{$R *.dfm}

function TDmAdm.AutorizaPreLancamentoNFEntrada(pDtF013: OleVariant; pCdUsuari : Integer): AnsiString;
var lDsTransa : TTransactionDesc;
begin
      try
          lDsTransa := DmDicion.NumeroTransactionID;
          SqlConnAdm.StartTransaction(lDsTransa);

          QpF013.Data := pDtF013;

          while not QpF013.Eof do
          begin
                CcF013Aut.BrUserCode  := pCdUsuari;
                CcF013Aut.BrFormName  := 'MOV0022';
                CcF013Aut.Close;
                CcF013Aut.CommandText := 'Select F013.* From F013 Where NrPreLan = ' +
                                                            QpF013.FieldByName('NrPreLan').AsString;
                CcF013Aut.Open;

                CcF013Aut.Edit;
                CcF013Aut.FieldByName('CdUsuAut').AsInteger  := pCdUsuari;
                CcF013Aut.FieldByName('DtAutori').AsDateTime := Now();
                CcF013Aut.Post;
                CcF013Aut.BrApplyUpdates;

                QpF013.Next;
          end;

          SqlConnAdm.Commit(lDsTransa);
          Result := UFrmLogos.cDsMsgOk;
      except
          On E: exception do
             begin
                   SqlConnAdm.Rollback(lDsTransa);
                   Result := UFrmLogos.cDsMsgEr + E.Message;
             end;
      end;
end;

function TDmAdm.GravarSaldoPlano(pDtSalCon: OleVariant; pNmFormul: String;
                                                                    pCdUsuari: Integer): AnsiString;
var lDsTransa : TTransactionDesc;
begin
      Result := UFrmLogos.cDsMsgOk;
      CpB003.Data := pDtSalCon;

      lDsTransa := DmDicion.NumeroTransactionID;
      SqlConnAdm.StartTransaction(lDsTransa);

      try
          QcB003.BrFormName := pNmFormul;
          QcB003.BrUserCode := pCdUsuari;
          CcB003.BrFormName := pNmFormul;
          CcB003.BrUserCode := pCdUsuari;

          CcB003.BrParams.Clear;
          CcB003.BrParams.Add('<%CdEmpres%>;' + CpB003.FieldByName('CdEmpres').AsString);
          CcB003.BrParams.Add('<%DtMes%>;'    + CpB003.FieldByName('DtMes').AsString);
          CcB003.BrParams.Add('<%DtAno%>;'    + CpB003.FieldByName('DtAno').AsString);
          CcB003.BrParams.Add('<%NrConta%>;'  + CpB003.FieldByName('NrConta').AsString);
          CcB003.BrParams.Add('<%NrPlano%>;'  + CpB003.FieldByName('NrPlano').AsString);
          CcB003.Open;

          if (CcB003.RecordCount > 0) then
          begin
                CcB003.Edit;
                CcB003.FieldByName('VrAbertu').AsFloat   := CpB003.FieldByName('VrAbertu').AsFloat;
                CcB003.FieldByName('VrEncDeb').AsFloat   := CpB003.FieldByName('VrEncDeb').AsFloat;
                CcB003.FieldByName('VrEncCre').AsFloat   := CpB003.FieldByName('VrEncCre').AsFloat;
                CcB003.Post;
          end else
          begin
                CcB003.Append;
                CcB003.FieldByName('CdEmpres').AsInteger := CpB003.FieldByName('CdEmpres').AsInteger;
                CcB003.FieldByName('DtMes'   ).AsInteger := CpB003.FieldByName('DtMes'   ).AsInteger;
                CcB003.FieldByName('DtAno'   ).AsInteger := CpB003.FieldByName('DtAno'   ).AsInteger;
                CcB003.FieldByName('NrConta' ).AsInteger := CpB003.FieldByName('NrConta' ).AsInteger;
                CcB003.FieldByName('NrPlano' ).AsInteger := CpB003.FieldByName('NrPlano' ).AsInteger;
                CcB003.FieldByName('VrAbertu').AsFloat   := CpB003.FieldByName('VrAbertu').AsFloat;
                CcB003.FieldByName('VrEncDeb').AsFloat   := CpB003.FieldByName('VrEncDeb').AsFloat;
                CcB003.FieldByName('VrEncCre').AsFloat   := CpB003.FieldByName('VrEncCre').AsFloat;
                CcB003.Post;
          end;

          CcB003.BrApplyUpdates;

          SqlConnAdm.Commit(lDsTransa);
      except
          On E: exception do
             begin
                   SqlConnAdm.Rollback(lDsTransa);
                   Result := UFrmLogos.cDsMsgEr + E.Message;
             end;
      end;
end;

function TDmAdm.EfetuarLancamentosContabeis(pDtParCon: OleVariant): AnsiString;
var lDsTransa : TTransactionDesc;
begin
      Result := UFrmLogos.cDsMsgOk;
      CpParCon.Data := pDtParCon;

      lDsTransa := DmDicion.NumeroTransactionID;
      SqlConnAdm.StartTransaction(lDsTransa);

      try
          BrvContabil.LancarContabilidadeXML(CpParCon.XMLData); //B004 - B008

          SqlConnAdm.Commit(lDsTransa);
      except
          On E: exception do
             begin
                   SqlConnAdm.Rollback(lDsTransa);
                   Result := UFrmLogos.cDsMsgEr + E.Message;
             end;
      end;
end;

procedure TDmAdm.GravarItensNFEntrada;
begin
      CpF002.First;
      while not CpF002.Eof do
      begin
            CcF002.Append;
            CcF002.FieldByName('NRSEQDOC').AsInteger := CpF002.FieldByName('NRSEQDOC').AsInteger;
            CcF002.FieldByName('NRSEQUEN').AsInteger := CpF002.FieldByName('NRSEQUEN').AsInteger;
            CcF002.FieldByName('CDPRODUT').AsInteger := CpF002.FieldByName('CDPRODUT').AsInteger;

            CcF002.FieldByName('NRSEQFIS').AsInteger := CpF002.FieldByName('NRSEQFIS').AsInteger;
            CcF002.FieldByName('CDOPEFIS').AsInteger := CpF002.FieldByName('CDOPEFIS').AsInteger;

            CcF002.FieldByName('QTPRODUT').AsFloat   := CpF002.FieldByName('QTPRODUT').AsFloat  ;
            CcF002.FieldByName('VRUNITAR').AsFloat   := CpF002.FieldByName('VRUNITAR').AsFloat  ;
            CcF002.FieldByName('PCDESCON').AsFloat   := CpF002.FieldByName('PCDESCON').AsFloat  ;
            CcF002.FieldByName('VRDESCON').AsFloat   := CpF002.FieldByName('VRDESCON').AsFloat  ;
            CcF002.FieldByName('VRTOTAL' ).AsFloat   := CpF002.FieldByName('VRTOTAL' ).AsFloat  ;
            CcF002.FieldByName('VRIPI'   ).AsFloat   := CpF002.FieldByName('VRIPI'   ).AsFloat  ;
            CcF002.FieldByName('BSSUBTRI').AsFloat   := CpF002.FieldByName('BSSUBTRI').AsFloat  ;
            CcF002.FieldByName('VRSUBTRI').AsFloat   := CpF002.FieldByName('VRSUBTRI').AsFloat  ;
            CcF002.FieldByName('PCALIISS').AsFloat   := CpF002.FieldByName('PCALIISS').AsFloat  ;
            CcF002.FieldByName('BSICMS'  ).AsFloat   := CpF002.FieldByName('BSICMS'  ).AsFloat  ;
            CcF002.FieldByName('PCALIICM').AsFloat   := CpF002.FieldByName('PCALIICM').AsFloat  ;
            CcF002.FieldByName('CDSITTRI').AsString  := CpF002.FieldByName('CDSITTRI').AsString ;
            CcF002.FieldByName('VRICMS'  ).AsFloat   := CpF002.FieldByName('VRICMS'  ).AsFloat  ;
            CcF002.FieldByName('PCDEBAIC').AsFloat   := CpF002.FieldByName('PCDEBAIC').AsFloat  ;
            CcF002.FieldByName('CDCSTCOF').AsFloat   := CpF002.FieldByName('CDCSTCOF').AsFloat  ;
            CcF002.FieldByName('BSIMPCOF').AsFloat   := CpF002.FieldByName('BSIMPCOF').AsFloat  ;
            CcF002.FieldByName('PCALICOF').AsFloat   := CpF002.FieldByName('PCALICOF').AsFloat  ;
            CcF002.FieldByName('VRIMPCOF').AsFloat   := CpF002.FieldByName('VRIMPCOF').AsFloat  ;
            CcF002.FieldByName('CDCSTPIS').AsString  := CpF002.FieldByName('CDCSTPIS').AsString ;
            CcF002.FieldByName('BSIMPPIS').AsFloat   := CpF002.FieldByName('BSIMPPIS').AsFloat  ;
            CcF002.FieldByName('PCALIPIS').AsFloat   := CpF002.FieldByName('PCALIPIS').AsFloat  ;
            CcF002.FieldByName('VRIMPPIS').AsFloat   := CpF002.FieldByName('VRIMPPIS').AsFloat  ;
            CcF002.FieldByName('VRISENTA').AsFloat   := CpF002.FieldByName('VRISENTA').AsFloat  ;
            CcF002.FieldByName('VROUTRA' ).AsFloat   := CpF002.FieldByName('VROUTRA' ).AsFloat  ;
            CcF002.FieldByName('TPFINENT').AsString  := CpF002.FieldByName('TPFINENT').AsString ;

            //chave do sistema antigo
            CcF002.FieldByName('NrSeqDoc').AsInteger := CpF002.FieldByName('NrSeqDoc').AsInteger;
            CcF002.FieldByName('CDTITULA').AsInteger := CpF002.FieldByName('CDTITULA').AsInteger;
            CcF002.FieldByName('CDEMPRES').AsInteger := CpF002.FieldByName('CDEMPRES').AsInteger;
            CcF002.FieldByName('NRNOTA'  ).AsInteger := CpF002.FieldByName('NRNOTA'  ).AsInteger;
            CcF002.FieldByName('DSMODENF').AsString  := CpF002.FieldByName('DSMODENF').AsString;
            CcF002.FieldByName('NRSERINF').AsString  := CpF002.FieldByName('NRSERINF').AsString;

            CcF002.Post;

            CpF002.Next;
      end;
end;

procedure TDmAdm.GravarCabecalhoNFEntrada;
begin
      CcF001.Append;
      CcF001.FieldByName('NRSEQDOC').AsInteger  := CpF001.FieldByName('NRSEQDOC').AsInteger;
      CcF001.FieldByName('CDEMPRES').AsInteger  := CpF001.FieldByName('CDEMPRES').AsInteger;
      CcF001.FieldByName('NRNOTA'  ).AsInteger  := CpF001.FieldByName('NRNOTA'  ).AsInteger;
      CcF001.FieldByName('DSMODENF').AsString   := CpF001.FieldByName('DSMODENF').AsString;
      CcF001.FieldByName('NRSERINF').AsString   := CpF001.FieldByName('NRSERINF').AsString;
      CcF001.FieldByName('NRSUSENF').AsString   := CpF001.FieldByName('NRSUSENF').AsString;
      CcF001.FieldByName('CDTRANSP').AsInteger  := CpF001.FieldByName('CDTRANSP').AsInteger;
      CcF001.FieldByName('CJTITULA').AsString   := CpF001.FieldByName('CJTITULA').AsString;
      CcF001.FieldByName('CDEVENTO').AsInteger  := CpF001.FieldByName('CDEVENTO').AsInteger;
      CcF001.FieldByName('NRSEQFIS').AsInteger  := CpF001.FieldByName('NRSEQFIS').AsInteger;
      CcF001.FieldByName('DTENTRAD').AsDateTime := CpF001.FieldByName('DTENTRAD').AsDateTime;
      CcF001.FieldByName('DTEMINOT').AsDateTime := CpF001.FieldByName('DTEMINOT').AsDateTime;
      CcF001.FieldByName('NRPRELAN').AsInteger  := CpF001.FieldByName('NRPRELAN').AsInteger;
      CcF001.FieldByName('BSPIS'   ).AsFloat    := CpF001.FieldByName('BSPIS'   ).AsFloat;
      CcF001.FieldByName('PCPIS'   ).AsFloat    := CpF001.FieldByName('PCPIS'   ).AsFloat;
      CcF001.FieldByName('VRPIS'   ).AsFloat    := CpF001.FieldByName('VRPIS'   ).AsFloat;
      CcF001.FieldByName('BSCOFINS').AsFloat    := CpF001.FieldByName('BSCOFINS').AsFloat;
      CcF001.FieldByName('PCCOFINS').AsFloat    := CpF001.FieldByName('PCCOFINS').AsFloat;
      CcF001.FieldByName('VRCOFINS').AsFloat    := CpF001.FieldByName('VRCOFINS').AsFloat;
      CcF001.FieldByName('BSICMS'  ).AsFloat    := CpF001.FieldByName('BSICMS'  ).AsFloat;
      CcF001.FieldByName('PCALIICM').AsFloat    := CpF001.FieldByName('PCALIICM').AsFloat;
      CcF001.FieldByName('VRICMS'  ).AsFloat    := CpF001.FieldByName('VRICMS'  ).AsFloat;
      CcF001.FieldByName('BSISSQN' ).AsFloat    := CpF001.FieldByName('BSISSQN' ).AsFloat;
      CcF001.FieldByName('PCALIISS').AsFloat    := CpF001.FieldByName('PCALIISS').AsFloat;
      CcF001.FieldByName('VRISSQST').AsFloat    := CpF001.FieldByName('VRISSQST').AsFloat;
      CcF001.FieldByName('BSIRRF'  ).AsFloat    := CpF001.FieldByName('BSIRRF'  ).AsFloat;
      CcF001.FieldByName('VRIRRF'  ).AsFloat    := CpF001.FieldByName('VRIRRF'  ).AsFloat;
      CcF001.FieldByName('BSCSLL'  ).AsFloat    := CpF001.FieldByName('BSCSLL'  ).AsFloat;
      CcF001.FieldByName('VRCSLL'  ).AsFloat    := CpF001.FieldByName('VRCSLL'  ).AsFloat;
      CcF001.FieldByName('PCIRPJSE').AsFloat    := CpF001.FieldByName('PCIRRF'  ).AsFloat;
      CcF001.FieldByName('TXDADADI').Value      := CpF001.FieldByName('TXDADADI').Value;
      CcF001.FieldByName('BSINSS'  ).AsFloat    := CpF001.FieldByName('BSINSS'  ).AsFloat;
      CcF001.FieldByName('PCALIINS').AsFloat    := CpF001.FieldByName('PCALIINS').AsFloat;
      CcF001.FieldByName('VRINSS'  ).AsFloat    := CpF001.FieldByName('VRINSS'  ).AsFloat;
      CcF001.FieldByName('BSSUBTRI').AsFloat    := CpF001.FieldByName('BSSUBTRI').AsFloat;
      CcF001.FieldByName('VRSUBTRI').AsFloat    := CpF001.FieldByName('VRSUBTRI').AsFloat;
      CcF001.FieldByName('VRSEGURO').AsFloat    := CpF001.FieldByName('VRSEGURO').AsFloat;
      CcF001.FieldByName('VROUTNOT').AsFloat    := CpF001.FieldByName('VROUTNOT').AsFloat;
      CcF001.FieldByName('VRISENOT').AsFloat    := CpF001.FieldByName('VRISENOT').AsFloat;
      CcF001.FieldByName('VRIPI'   ).AsFloat    := CpF001.FieldByName('VRIPI'   ).AsFloat;
      CcF001.FieldByName('VRFRETE' ).AsFloat    := CpF001.FieldByName('VRFRETE' ).AsFloat;
      CcF001.FieldByName('VRCONNOT').AsFloat    := CpF001.FieldByName('VRCONNOT').AsFloat;
      CcF001.FieldByName('VRDESACE').AsFloat    := CpF001.FieldByName('VRDESACE').AsFloat;
      CcF001.FieldByName('SNFREICM').AsString   := CpF001.FieldByName('SNFREICM').AsString;
      CcF001.FieldByName('SNFREIPI').AsString   := CpF001.FieldByName('SNFREIPI').AsString;
      CcF001.FieldByName('TPFRETE' ).AsString   := CpF001.FieldByName('TPFRETE' ).AsString;
      CcF001.FieldByName('STNOTA'  ).AsString   := CpF001.FieldByName('STNOTA'  ).AsString;
      CcF001.FieldByName('CDTITULA').AsInteger  := CpF001.FieldByName('CDTITULA').AsInteger;
      CcF001.FieldByName('NRCHADOC').AsString   := CpF001.FieldByName('NRCHADOC').AsString;
      CcF001.FieldByName('SNDESPIC').AsString   := CpF001.FieldByName('SNDESPIC').AsString;
      CcF001.FieldByName('PCCSLLSE').AsFloat    := CpF001.FieldByName('PCCSLL'  ).AsFloat;
      CcF001.FieldByName('CDFISCAL').AsInteger  := CpF001.FieldByName('CDFISCAL').AsInteger;
      CcF001.FieldByName('CDESTEMI').AsString   := CpF001.FieldByName('CDESTEMI').AsString;
      CcF001.Post;
end;

procedure TDmAdm.GravarContasPagar;
begin
      CcN002.Append;
      CcN002.FieldByName('NrConta' ).asInteger := CpN002.FieldByName('NrConta' ).asInteger ;
      CcN002.FieldByName('CDEMPRES').asInteger := CpN002.FieldByName('CDEMPRES').asInteger ;
      CcN002.FieldByName('CDFORPAG').asInteger := CpN002.FieldByName('CDFORPAG').asInteger ;
      CcN002.FieldByName('CDTITULA').asInteger := CpN002.FieldByName('CDTITULA').asInteger ;
      CcN002.FieldByName('CDUSUARI').asInteger := CpN002.FieldByName('CDUSUARI').asInteger ;
      CcN002.FieldByName('DTEMIDOC').asDatetime:= CpN002.FieldByName('DTEMIDOC').asDatetime;
      CcN002.FieldByName('DTPROCES').asDatetime:= CpN002.FieldByName('DTPROCES').asDatetime;
      CcN002.FieldByName('NMARQEDI').asString  := CpN002.FieldByName('NMARQEDI').asString  ;
      CcN002.FieldByName('NRDOCTO' ).asInteger := CpN002.FieldByName('NRDOCTO' ).asInteger ;
      CcN002.FieldByName('NRPREFAT').asInteger := CpN002.FieldByName('NRPREFAT').asInteger ;
      CcN002.FieldByName('TPCONTA' ).asString  := CpN002.FieldByName('TPCONTA' ).asString  ;
      CcN002.FieldByName('VRORIGEM').asFloat   := CpN002.FieldByName('VRORIGEM').asFloat   ;
      CcN002.Post;

      CcN006LancNfCc.Append;
      CcN006LancNfCc.FieldByName('nrnotcon').AsInteger :=
                                   DmDicion.BrvDicionario.ProxNumeroSequencial('N006', 'NrNotCon');
      CcN006LancNfCc.FieldByName('cdempres').AsInteger := CpN002.FieldByName('CDEMPRES').asInteger;
      CcN006LancNfCc.FieldByName('dsmodenf').AsString  := CpF001.FieldByName('DSMODENF').AsString;
      CcN006LancNfCc.FieldByName('nrconta' ).AsInteger := CpN002.FieldByName('NrConta' ).asInteger;
      CcN006LancNfCc.FieldByName('nrnota'  ).AsInteger := CpN002.FieldByName('NRDOCTO' ).asInteger;
      CcN006LancNfCc.FieldByName('nrserinf').AsString  := CpF001.FieldByName('NRSERINF').AsString;
      CcN006LancNfCc.Post;

      CpN003.First;

      while not CpN003.Eof do
      begin
            CcN003.Append;
            CcN003.FieldByName('NrConta' ).asinteger  := CpN003.FieldByName('NrConta' ).asinteger;
            CcN003.FieldByName('NrOrdem' ).asinteger  := CpN003.FieldByName('NrOrdem' ).asinteger;
            CcN003.FieldByName('DtVendoc').asDateTime := CpN003.FieldByName('DtVencto').asDateTime;
            CcN003.FieldByName('VrDocto' ).asFloat    := CpN003.FieldByName('VrDocto' ).asFloat;
            CcN003.FieldByName('CdBarra' ).asString   := CpN003.FieldByName('CdBarra' ).asString;
            CcN003.Post;

            CpN003.Next;
      end;

end;

Procedure TDmAdm.AbreCdsFinalizarDigitacaoNota;
begin
      //Cabecalho Nota
      CcF001.Close;
      CcF001.Open;

      //Itens Nota
      CcF002.Close;
      CcF002.Open;

      //Contas a pagar Cabecalho
      CcN002.Close;
      CcN002.Open;

      //Contas a pagar Lanctos
      CcN003.Close;
      CcN003.Open;

      //Notas da Conta
      CcN006LancNfCc.Close;
      CcN006LancNfCc.Open;
end;

Procedure TDmAdm.FixaCdsFinalizarDigitacaoNota;
begin
      CcN002.BrApplyUpdates;
      CcN003.BrApplyUpdates;
      CcF001.BrApplyUpdates;
      CcF002.BrApplyUpdates;
      CcN006LancNfCc.BrApplyUpdates;
end;

function TDmAdm.FinalizarPreLancamentoNFEntrada(pNrPleLan: Integer; pCdUsuari: Integer;
                                                                 pSnFixa: String = 'S'): AnsiString;
var lDsTransa : TTransactionDesc;
begin
      try
          if (pSnFixa = 'S') then
          begin
                lDsTransa := DmDicion.NumeroTransactionID;
                SqlConnAdm.StartTransaction(lDsTransa);
          end;

          CcF013Fecha.BrUserCode  := pCdUsuari;
          CcF013Fecha.BrFormName  := 'MOV0023';
          CcF013Fecha.Close;
          CcF013Fecha.CommandText := 'Select F013.* From F013 Where NrPreLan = ' +
                                                                        FormatFloat('0', pNrPleLan);
          CcF013Fecha.Open;

          CcF013Fecha.Edit;
          CcF013Fecha.FieldByName('StPreLan').AsString := 'F';
          CcF013Fecha.Post;
          CcF013Fecha.BrApplyUpdates;

          if (pSnFixa = 'S') then
          begin
                SqlConnAdm.Commit(lDsTransa);
          end;

          Result := UFrmLogos.cDsMsgOk;
      except
          On E: exception do
             begin
                   if (pSnFixa = 'S') then
                   begin
                         SqlConnAdm.Rollback(lDsTransa);
                   end;

                   Result := UFrmLogos.cDsMsgEr + E.Message;
             end;
      end;
end;

function TDmAdm.GravarPreLancamentoNFEntrada(pDtF013, pDtF014, pDtF015,
                                                                   pDtF017: OleVariant): AnsiString;
var lDsTransa : TTransactionDesc;
begin
      try
          lDsTransa := DmDicion.NumeroTransactionID;
          SqlConnAdm.StartTransaction(lDsTransa);

          QpF013.Data := pDtF013;
          QpF014.Data := pDtF014;
          QpF015.Data := pDtF015;
          QpF017.Data := pDtF017;

          CcF013.Close;
          CcF013.Open;

          CcF014.Close;
          CcF014.Open;

          CcF015.Close;
          CcF015.Open;

          CcF017.Close;
          CcF017.Open;

          CcF013.Append;
          CcF013.FieldByName('cdempres').AsInteger := QpF013.FieldByName('cdempres').AsInteger;
          CcF013.FieldByName('cdtitula').AsInteger := QpF013.FieldByName('cdtitula').AsInteger;
          CcF013.FieldByName('cdusuins').AsInteger := QpF013.FieldByName('cdusuins').AsInteger;
          CcF013.FieldByName('dtinserc').AsDateTime:= QpF013.FieldByName('dtinserc').AsDateTime;
          CcF013.FieldByName('nrchadoc').AsString  := QpF013.FieldByName('nrchadoc').AsString;
          CcF013.FieldByName('nrnota'  ).AsInteger := QpF013.FieldByName('nrnota'  ).AsInteger;
          CcF013.FieldByName('nrprelan').AsInteger := QpF013.FieldByName('nrprelan').AsInteger;
          CcF013.FieldByName('stprelan').AsString  := 'A';
          CcF013.Post;

          while not QpF014.Eof do
          begin
                CcF014.Append;
                CcF014.FieldByName('nrprelan').AsInteger:= QpF014.FieldByName('nrprelan').AsInteger;
                CcF014.FieldByName('nrlancto').AsInteger:= QpF014.FieldByName('nrlancto').AsInteger;
                CcF014.FieldByName('nrplano' ).AsInteger:= QpF014.FieldByName('nrplano' ).AsInteger;
                CcF014.FieldByName('nrconta' ).AsInteger:= QpF014.FieldByName('nrconta' ).AsInteger;
                CcF014.FieldByName('cdhistor').AsInteger:= QpF014.FieldByName('cdhistor').AsInteger;
                CcF014.FieldByName('vrlancto').AsFloat  := QpF014.FieldByName('vrlancto').AsFloat;
                CcF014.Post;

                QpF014.Next;
          end;

          while not QpF017.Eof do
          begin
                CcF017.Append;
                CcF017.FieldByName('nrprelan').AsInteger:= QpF017.FieldByName('nrprelan').AsInteger;
                CcF017.FieldByName('nrlancto').AsInteger:= QpF017.FieldByName('nrlancto').AsInteger;
                CcF017.FieldByName('nrlancCc').AsInteger:= QpF017.FieldByName('nrlancCc').AsInteger;
                CcF017.FieldByName('CdCenCus').AsInteger:= QpF017.FieldByName('CdCenCus').AsInteger;
                CcF017.FieldByName('vrlancto').AsFloat  := QpF017.FieldByName('vrlancto').AsFloat;
                CcF017.Post;

                QpF017.Next;
          end;

          while not QpF015.Eof do
          begin
                CcF015.Append;
                CcF015.FieldByName('NrPreLan').AsInteger  :=
                                               QpF015.FieldByName('NrPreLan').AsInteger;
                CcF015.FieldByName('NrParcel').AsInteger  :=
                                               QpF015.FieldByName('NrParcel').AsInteger;
                CcF015.FieldByName('DtVencto').AsDateTime :=
                                               QpF015.FieldByName('DtVencto').AsDateTime;
                CcF015.FieldByName('VrDocto' ).AsFloat    :=
                                               QpF015.FieldByName('VrDocto' ).AsFloat;
                CcF015.Post;

                QpF015.Next;
          end;

          CcF013.BrApplyUpdates;
          CcF014.BrApplyUpdates;
          CcF015.BrApplyUpdates;
          CcF017.BrApplyUpdates;

          SqlConnAdm.Commit(lDsTransa);
          Result := UFrmLogos.cDsMsgOk;
      except
          On E: exception do
             begin
                   SqlConnAdm.Rollback(lDsTransa);
                   Result := UFrmLogos.cDsMsgEr + E.Message;
             end;
      end;
end;

function TDmAdm.MontaLotePreLancamentoNFEntrada(pDtF013: OleVariant;
                                                pCdUsuari, pNrLote: Integer;
                                                pDsLote : AnsiString): AnsiString;
var lDsTransa : TTransactionDesc;
begin
      try
          lDsTransa := DmDicion.NumeroTransactionID;
          SqlConnAdm.StartTransaction(lDsTransa);

          CcF012.Close;
          CcF012.Open;
          CcF012.Append;
          CcF012.FieldByName('nrseqlot').AsInteger  := pNrLote;
          CcF012.FieldByName('dslote'  ).AsString   := pDsLote;
          CcF012.FieldByName('dtenvlot').AsDateTime := Now();
          CcF012.FieldByName('cdusuenv').AsInteger  := pCdUsuari;
          CcF012.BrApplyUpdates;

          QpF013.Data := pDtF013;
          QpF013.First;

          while not QpF013.Eof do
          begin
                CcF013Lote.BrUserCode  := pCdUsuari;
                CcF013Lote.BrFormName  := 'MOV0023';
                CcF013Lote.Close;
                CcF013Lote.CommandText := 'Select F013.* From F013 Where NrPreLan = ' +
                                                            QpF013.FieldByName('NrPreLan').AsString;
                CcF013Lote.Open;

                CcF013Lote.Edit;
                CcF013Lote.FieldByName('NrSeqLot').AsInteger  := pNrLote;
                CcF013Lote.Post;
                CcF013Lote.BrApplyUpdates;

                QpF013.Next;
          end;

          SqlConnAdm.Commit(lDsTransa);
          Result := UFrmLogos.cDsMsgOk;
      except
          On E: exception do
             begin
                   SqlConnAdm.Rollback(lDsTransa);
                   Result := UFrmLogos.cDsMsgEr + E.Message;
             end;
      end;
end;

function TDmAdm.RecepcionaLotePreLancamentoNFEntrada(pDtF012: OleVariant;
                                                                    pCdUsuari: Integer): AnsiString;
var lDsTransa : TTransactionDesc;
begin
      try
          lDsTransa := DmDicion.NumeroTransactionID;
          SqlConnAdm.StartTransaction(lDsTransa);

          QpF012.Data := pDtF012;

          while not QpF012.Eof do
          begin
                CcF012Rec.BrUserCode  := pCdUsuari;
                CcF012Rec.BrFormName  := 'MOV0024';
                CcF012Rec.Close;
                CcF012Rec.CommandText := 'Select F012.* From F012 Where NrSeqLot = ' +
                                                            QpF012.FieldByName('NrSeqLot').AsString;
                CcF012Rec.Open;

                CcF012Rec.Edit;
                CcF012Rec.FieldByName('CdUsuRec').AsInteger  := pCdUsuari;
                CcF012Rec.FieldByName('DtRecLot').AsDateTime := Now();
                CcF012Rec.Post;
                CcF012Rec.BrApplyUpdates;

                QpF012.Next;
          end;

          SqlConnAdm.Commit(lDsTransa);
          Result := UFrmLogos.cDsMsgOk;
      except
          On E: exception do
             begin
                   SqlConnAdm.Rollback(lDsTransa);
                   Result := UFrmLogos.cDsMsgEr + E.Message;
             end;
      end;
end;

function TDmAdm.PeriodoContabilValido(pDtF018: OleVariant): AnsiString;
var lCpF018 : TClientDataSet;
begin
      try
          try
              lCpF018 := TClientDataSet.Create(Self);
              lCpF018.Data := pDtF018;

              CpF018.Data  := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(191,
                             '<%CdEmpres%>;' + lCpF018.FieldByName('CdEmpres').AsString + #13 +
                             '<%NrAnoFis%>;' + lCpF018.FieldByName('NrAnoFis').AsString + #13 +
                             '<%NrMesFis%>;' + lCpF018.FieldByName('NrMesFis').AsString, Self.name);

              Result := UFrmLogos.cDsMsgOk;

              if (CpF018.RecordCount = 0) then
              begin
                    Result := UFrmLogos.cDsMsgEr + 'Período contábil não localizado!';
              end else
              begin
                    if (CpF018.FieldByName('StPerFis').AsString = 'F') then
                    begin
                          Result := UFrmLogos.cDsMsgEr + 'Período contábil fechado!';
                    end;
              end;
          except
              On E: exception do
                 begin
                       Result := UFrmLogos.cDsMsgEr + 'Erro ao consultar Periodo contábil' + #13 +
                                 E.Message;
                 end;
          end;
      finally
          FreeAndNil(lCpF018);
      end;
end;

function TDmAdm.BaixarDocumento(pTpOperac: String; pDtN002: OleVariant; pDtParCon: OleVariant;
                                pCdUsuari: Integer; pCdHistor : Integer;
                                pDtBaixa: TDateTime): AnsiString;
var lDsTransa : TTransactionDesc;
begin
      Result := UFrmLogos.cDsMsgOk;
      CpParCon.Data := pDtParCon;

      try
          lDsTransa := DmDicion.NumeroTransactionID;
          SqlConnAdm.StartTransaction(lDsTransa);
          DmDicion.BrvDicionario.SQLConnection := SqlConnAdm;

          BrvContas.BaixaDocumentos(pDtN002, pCdUsuari, pCdHistor, pDtBaixa, pTpOperac,
                                    CpParCon.FieldByName('NmFormul').AsString, pDtParCon);

          SqlConnAdm.Commit(lDsTransa);
          DmDicion.BrvDicionario.SQLConnection := DmDicion.SqlDicion;
      except
          On E: exception do
             begin
                   DmDicion.BrvDicionario.SQLConnection := DmDicion.SqlDicion;
                   SqlConnAdm.Rollback(lDsTransa);
                   Result := UFrmLogos.cDsMsgEr + E.Message;
             end;
      end;
end;

function TDmAdm.FinalizarDigitacaoNota(pDtF001, pDtF002, pDtN002, pDtN003,
                                       pDtParCon: OleVariant; pCdUsuari : Integer): AnsiString;
var lDsTransa : TTransactionDesc;
    lDsResult : AnsiString;
begin
      Result := UFrmLogos.cDsMsgOk;

      CpF001.Data   := pDtF001;
      CpF002.Data   := pDtF002;
      CpN002.Data   := pDtN002;
      CpN003.Data   := pDtN003;
      CpParCon.Data := pDtParCon;

      lDsTransa := DmDicion.NumeroTransactionID;
      SqlConnAdm.StartTransaction(lDsTransa);

      //Abre ClientDataSet's p/ gravação no BD
      AbreCdsFinalizarDigitacaoNota;

      try
          GravarCabecalhoNFEntrada; //F001
          GravarItensNFEntrada;     //F002
          GravarContasPagar;        //N002 - N003
          BrvContabil.LancarContabilidadeXML(CpParCon.XMLData); //B004 - B008

          FixaCdsFinalizarDigitacaoNota;

          lDsResult := FinalizarPreLancamentoNFEntrada(CpF001.FieldByName('NrPreLan').AsInteger,
                                                                                    pCdUsuari, 'N');
          if Copy(lDsResult, 1, 3) = '1; ' then
          begin
                Result := lDsResult;
          end else
          begin
                SqlConnAdm.Commit(lDsTransa);
          end;
      except
          On E: exception do
             begin
                   SqlConnAdm.Rollback(lDsTransa);
                   Result := UFrmLogos.cDsMsgEr + E.Message;
             end;
      end;
end;

function TDmAdm.LancarContasPagarReceber(pNmFormul : String;    pCdHistor : Integer;
                                         pTpPagRec : String;    pVrLancto : Double;
                                         pCdForPag : Integer;   pCdTitula : Integer;
                                         pCdEmpres : Integer;   pNrDocto  : Integer;
                                         pDtEmissa : TDateTime; pNrPreCar : Integer;
                                         pNrOrdem  : Integer;   pDtVencto : TDateTime;
                                         pCdEvento : Integer;   pTpPagto  : String;
                                         pNrConCon : Integer;   pDsComHis : String;
                                         pCdCenCus : Integer;   pNrPlano  : Integer): AnsiString;

var lCdUsuari : Integer;
    lDsTransa : TTransactionDesc;
    lCdsN006  : TClientDataSet;
begin
      try
          lDsTransa := DmDicion.NumeroTransactionID;
          SqlConnAdm.StartTransaction(lDsTransa);

          BrvContas.LancarContasPagarReceber(pNmFormul,
                                             pCdHistor,
                                             pTpPagRec,
                                             pVrLancto,
                                             pCdForPag,
                                             pCdTitula,
                                             pCdEmpres,
                                             pNrDocto,
                                             pDtEmissa,
                                             pNrPreCar,
                                             pNrOrdem,
                                             pDtVencto,
                                             pCdEvento,
                                             pTpPagto,
                                             pDsComHis,
                                             pNrPlano,
                                             lCdUsuari,
                                             BrvContas.CdsTempN006,
                                             0);

          SqlConnAdm.Commit(lDsTransa);
          Result := UFrmLogos.cDsMsgOk;
      except
           on E: Exception do
                 begin
                       SqlConnAdm.Rollback(lDsTransa);
                       Result := UFrmLogos.cDsMsgEr + E.Message;
                 end;
      end;
end;

procedure TDmAdm.EventoTitular(pCdTitula : Integer; var lCdEvento : Integer;
                                                     var lCdForPag : Integer);
var lCdsEvento : TClientDataSet;
begin
      lCdEvento := 0;
      lCdForPag := 0;

      lCdsEvento := TClientDataSet.Create(Self);

      try
          lCdsEvento.Data := DmDicion.BrvDicionario.RetornaDadosSqlFixa(
                              ' Select G011.CdEvento, G009.CdForPag ' +
                              ' From   G011 G011 ' +
                              ' Left Join G009 G009 on (G009.CdEvento = G011.CdEvento)' +
                              ' Where  G011.CdTitula = ' + IntToStr(pCdTitula));

          lCdEvento := lCdsEvento.FieldByName('CdEvento').AsInteger;
          lCdForPag := lCdsEvento.FieldByName('CdForPag').AsInteger;
      finally
           FreeAndNil(lCdsEvento);
      end;
end;

function TDmAdm.Faturar(pDsCreden: String; pDtParam: OleVariant): AnsiString;
var lCdUsuari : Integer;
    lDsTransa : TTransactionDesc;
    lCpParam  : TClientDataSet;
    lCpN006   : TClientDataSet;
    lCdEvento : Integer;
    lCdForPag : Integer;
    lCdHistor : Integer;
    lNrConta  : Integer;
begin
      try
          lDsTransa := DmDicion.NumeroTransactionID;
          SqlConnAdm.StartTransaction(lDsTransa);

          //Abrindo parametros
          lCpParam := TClientDataSet.Create(Self);
          lCpParam.Data := pDtParam;

          lCpN006.XMLData := lCpParam.FieldByName('pCdsN006').AsString;

          if (lCpParam.FieldByName('pDtFatura').AsDateTime >=
              DmDicion.BrvDicionario.DataProximoDiaUtil(
                                                  DmDicion.BrvDicionario.DataServer, -1)) then
          begin
                EventoTitular(lCpParam.FieldByName('pCdTitula').AsInteger,
                                                                        lCdEvento, lCdForPag);

                if lCdEvento = 0 then
                begin
                      raise Exception.Create('Não há evento cadastrado para esse titular.');
                end;

                lCdHistor := StrToInt(DmDicion.BrvDicionario.ParametroEmpresa(
                                            lCpParam.FieldByName('pCdEmpres').AsInteger, 5));

                lNrConta  := BrvContas.LancarContasPagarReceber(
                                    lCpParam.FieldByName('pNmFormul').AsString,
                                    lCdHistor,
                                    'R',
                                    lCpParam.FieldByName('pVrLancto').AsFloat,
                                    lCdForPag,
                                    lCpParam.FieldByName('pCdTitula').AsInteger,
                                    lCpParam.FieldByName('pCdEmpres').AsInteger,
                                    lCpParam.FieldByName('NrDocto'  ).AsInteger,
                                    lCpParam.FieldByName('pDtFatura').AsDateTime,
                                    lCpParam.FieldByName('pNrPreCar').AsInteger,
                                    0, //pNrOrdem,
                                    lCpParam.FieldByName('pDtFatura').AsDateTime,
                                    lCdEvento,
                                    '', // pTpPagto,
                                    '', // pDsComHis,
                                    lCpParam.FieldByName('pNrPlano').AsInteger,
                                    lCdUsuari,
                                    lCpN006.Data,
                                    lCpParam.FieldByName('pVrDescon').AsFloat);

                Result := FormatFloat('0', lNrConta);

                SqlConnAdm.Commit(lDsTransa);
          end else
          begin
                raise Exception.Create('Data do faturamento inválida! ' + #13#13 +
                                       'A data do faturamento deve ser no máximo 1 ' +
                                       'dia útil anterior a data atual!');
          end;
      except
           on E: Exception do
                 begin
                       SqlConnAdm.Rollback(lDsTransa);
                       Result := UFrmLogos.cDsMsgEr + E.Message;
                 end;
      end;
end;

function TDmAdm.GravarRNC(pDtParam: OleVariant; pCdUsuari: Integer): AnsiString;
var lCpParams : TClientDataSet;
    lCpQ004   : TClientDataSet;   // RNC
    lCpQ005   : TClientDataSet;   // Produtos da RNC
    lCpQ006   : TClientDataSet;   // Usuários/Partes Interessadas da RNC
    lCpQ007   : TClientDataSet;   // Anexos da RNC
    lDsTransa : TTransactionDesc;
    lNrProRNC : Integer;
    lNrRNC    : Integer;
    lNmDirSer : String;           // Nome da pasta do diretório no servidor
    lNmDirArq : String;           // Nome do arquivo na pasta do diretório no servidor
begin
      try
          lCpParams       := TClientDataSet.Create(Self);
          lCpParams.Data  := pDtParam;

          lCpQ004         := TClientDataSet.Create(Self);
          lCpQ004.XMLData := lCpParams.FieldByName('XmlQ004').AsString;

          lCpQ005         := TClientDataSet.Create(Self);
          lCpQ005.XMLData := lCpParams.FieldByName('XmlQ005').AsString;

          lCpQ006         := TClientDataSet.Create(Self);
          lCpQ006.XMLData := lCpParams.FieldByName('XmlQ006').AsString;

          lCpQ007         := TClientDataSet.Create(Self);
          lCpQ007.Data    := lCpParams.FieldByName('XmlQ007').Value;

          lDsTransa       := DmDicion.NumeroTransactionID;
          SqlConnAdm.StartTransaction(lDsTransa);

          CcQ004.BrFormName := lCpParams.FieldByName('NmForm').AsString;
          CcQ004.BrUserCode := pCdUsuari;

          lNrRNC := lCpQ004.FieldByName('NrRnc').AsInteger;

          if (lNrRNC > 0) then
          begin
                CcQ004.Close;
                CcQ004.BrParams.Clear;
                CcQ004.BrParams.Add('<%NrRnc%>;' + IntToStr(lNrRNC));
                CcQ004.Open;
                CcQ004.Edit;
          end else
          begin
                lNrRNC := DmDicion.BrvDicionario.ProxNumeroSequencial('Q004','NRRNC');
                CcQ004.Close;
                CcQ004.BrParams.Clear;
                CcQ004.BrParams.Add('<%NrRnc%>;' + IntToStr(lNrRNC));
                CcQ004.Open;
                CcQ004.Append;
                CcQ004.FieldByName('NrRnc').AsInteger := lNrRNC;
          end;
          CcQ004.FieldByName('CdEmpres').AsInteger  := lCpQ004.FieldByName('CdEmpres').AsInteger;
          CcQ004.FieldByName('CdSetor' ).AsInteger  := lCpQ004.FieldByName('CdSetor' ).AsInteger;
          CcQ004.FieldByName('CdUsuEmi').AsInteger  := lCpQ004.FieldByName('CdUsuEmi').AsInteger;
          CcQ004.FieldByName('CdUsuDes').AsInteger  := lCpQ004.FieldByName('CdUsuDes').AsInteger;
          CcQ004.FieldByName('CdClaRnc').AsInteger  := lCpQ004.FieldByName('CdClaRnc').AsInteger;
          CcQ004.FieldByName('DtEmiRnc').AsDateTime := lCpQ004.FieldByName('DtEmiRnc').AsDateTime;
          CcQ004.FieldByName('TxRnc'   ).AsString   := lCpQ004.FieldByName('TxRnc'   ).AsString;
          CcQ004.FieldByName('NmMotori').AsString   := lCpQ004.FieldByName('NmMotori').AsString;
          CcQ004.FieldByName('NrPlaVei').AsString   := lCpQ004.FieldByName('NrPlaVei').AsString;
          CcQ004.FieldByName('StRnc'   ).AsString   := lCpQ004.FieldByName('StRnc'   ).AsString;

          if (lCpQ004.FieldByName('CdTransp').AsInteger > 0) then
          begin
               CcQ004.FieldByName('CdTransp').AsInteger := lCpQ004.FieldByName('CdTransp').AsInteger;
          end;
          if (lCpQ004.FieldByName('CdMotori').AsInteger > 0) then
          begin
               CcQ004.FieldByName('CdMotori').AsInteger := lCpQ004.FieldByName('CdMotori').AsInteger;
          end;
          if (lCpQ004.FieldByName('CdVeicul').AsInteger > 0) then
          begin
               CcQ004.FieldByName('CdVeicul').AsInteger := lCpQ004.FieldByName('CdVeicul').AsInteger;
          end;

          CcQ004.Post;

          QcQ005Rem.BrFormName := lCpParams.FieldByName('NmForm').AsString;
          QcQ005Rem.BrUserCode := pCdUsuari;
          QcQ005Rem.BrParams.Clear;
          QcQ005Rem.BrParams.Add('<%NrRnc%>;' + IntToStr(lNrRNC));
          QcQ005Rem.BrExecuteSQL(True);

          CcQ005.BrFormName := lCpParams.FieldByName('NmForm').AsString;
          CcQ005.BrUserCode := pCdUsuari;
          CcQ005.Close;
          CcQ005.BrParams.Clear;
          CcQ005.BrParams.Add('<%NrRnc%>;' + IntToStr(lNrRNC));
          CcQ005.Open;

          while not lCpQ005.Eof do
          begin
                lNrProRNC := DmDicion.BrvDicionario.ProxNumeroSequencial('Q005','NRPRORNC');

                CcQ005.Append;
                CcQ005.FieldByName('NrProRNC').AsInteger := lNrProRNC;
                CcQ005.FieldByName('NrRnc'   ).AsInteger := lNrRNC;
                CcQ005.FieldByName('CdProdut').AsString  := lCpQ005.FieldByName('CdProdut').AsString;
                CcQ005.FieldByName('DsProdut').AsString  := lCpQ005.FieldByName('DsProdut').AsString;
                CcQ005.FieldByName('QtProdut').AsString  := lCpQ005.FieldByName('QtProdut').AsString;
                CcQ005.FieldByName('NrLote'  ).AsString  := lCpQ005.FieldByName('NrLote'  ).AsString;
                CcQ005.FieldByName('DtVencto').AsString  := lCpQ005.FieldByName('DtVencto').AsString;
                CcQ005.FieldByName('NrNota'  ).AsString  := lCpQ005.FieldByName('NrNota'  ).AsString;
                CcQ005.FieldByName('CdTitula').AsString  := lCpQ005.FieldByName('CdTitula').AsString;
                CcQ005.Post;

                lCpQ005.Next;
          end;

          QcQ006Rem.BrFormName := lCpParams.FieldByName('NmForm').AsString;
          QcQ006Rem.BrUserCode := pCdUsuari;
          QcQ006Rem.BrParams.Clear;
          QcQ006Rem.BrParams.Add('<%NrRnc%>;' + IntToStr(lNrRNC));
          QcQ006Rem.BrExecuteSQL(True);

          CcQ006.BrFormName := lCpParams.FieldByName('NmForm').AsString;
          CcQ006.BrUserCode := pCdUsuari;
          CcQ006.Close;
          CcQ006.BrParams.Clear;
          CcQ006.BrParams.Add('<%NrRnc%>;' + IntToStr(lNrRNC));
          CcQ006.Open;

          while not lCpQ006.Eof do
          begin
                CcQ006.Append;
                CcQ006.FieldByName('NrRnc'   ).AsInteger  := lNrRNC;
                CcQ006.FieldByName('CdUsuari').AsInteger  := lCpQ006.FieldByName('CdUsuari').AsInteger;
                CcQ006.Post;

                lCpQ006.Next;
          end;

          QcQ007Rem.BrFormName := lCpParams.FieldByName('NmForm').AsString;
          QcQ007Rem.BrUserCode := pCdUsuari;

          CcQ007.BrFormName := lCpParams.FieldByName('NmForm').AsString;
          CcQ007.BrUserCode := pCdUsuari;
          CcQ007.Close;
          CcQ007.BrParams.Clear;
          CcQ007.BrParams.Add('<%NrRnc%>;' + IntToStr(lNrRNC));
          CcQ007.Open;

          lNmDirSer := DmDicion.BrvDicionario.ParametroDaEmpresa(31, 0);
          lNmDirSer := lNmDirSer + '\' + IntToStr(lNrRNC);
          ForceDirectories(lNmDirSer);

          while not lCpQ007.Eof do
          begin
                lNmDirArq := lNmDirSer + '\' + lCpQ007.FieldByName('NmArquiv').AsString;

                if (lCpQ007.FieldByName('StArquiv').AsString = 'D') then
                begin
                      QcQ007Rem.BrParams.Clear;
                      QcQ007Rem.BrParams.Add('<%NrRnc%>;' + IntToStr(lNrRNC));
                      QcQ007Rem.BrParams.Add('<%NmArquiv%>;' +
                                                          lCpQ007.FieldByName('NmArquiv').AsString);
                      QcQ007Rem.BrExecuteSQL(True);

                      if FileExists(lNmDirArq) then
                      begin
                            DeleteFile(lNmDirArq);
                      end;
                end else if (lCpQ007.FieldByName('StArquiv').AsString = 'N') then
                begin
                      CcQ007.Append;
                      CcQ007.FieldByName('NrRnc'    ).AsInteger := lNrRNC;
                      CcQ007.FieldByName('NmArquiv' ).AsString  :=
                                                           lCpQ007.FieldByName('NmArquiv').AsString;
                      CcQ007.FieldByName('NmDirSer' ).AsString  := lNmDirSer;
                      CcQ007.Post;

                     (lCpQ007.FieldByName('BnArquiv') as TBlobField).SaveToFile(lNmDirArq);
                end;

                lCpQ007.Next;
          end;

          CcQ004.BrApplyUpdates;
          CcQ005.BrApplyUpdates;
          CcQ006.BrApplyUpdates;
          CcQ007.BrApplyUpdates;

          SqlConnAdm.Commit(lDsTransa);

          Result := UFrmLogos.cDsMsgOk + IntToStr(lNrRNC);
      except
          On E: exception do
             begin
                   SqlConnAdm.Rollback(lDsTransa);
                   Result := UFrmLogos.cDsMsgEr + E.Message;
             end;
      end;
end;

procedure TDmAdm.SqlConnAdmAfterConnect(Sender: TObject);
begin
      SqlConnAdm.ExecuteDirect('alter session set nls_numeric_characters = ''.,''');
end;

function TDmAdm.GravarDisposicaoRNC(pDtParam: OleVariant; pCdUsuari: Integer): AnsiString;
var
    lDsTransa : TTransactionDesc;
    lCpParams : TClientDataSet;
    lCpQ004   : TClientDataSet;   // RNC
begin
      try
          lCpParams       := TClientDataSet.Create(Self);
          lCpParams.Data  := pDtParam;

          lCpQ004         := TClientDataSet.Create(Self);
          lCpQ004.XMLData := lCpParams.FieldByName('XmlQ004').AsString;

          lDsTransa       := DmDicion.NumeroTransactionID;
          SqlConnAdm.StartTransaction(lDsTransa);

          CcQ004CadDis.BrFormName := lCpParams.FieldByName('NmForm').AsString;
          CcQ004CadDis.BrUserCode := pCdUsuari;

          CcQ004CadDis.Close;
          CcQ004CadDis.BrParams.Clear;
          CcQ004CadDis.BrParams.Add('<%NrRnc%>;' + lCpQ004.FieldByName('NrRnc').AsString);
          CcQ004CadDis.Open;
          CcQ004CadDis.Edit;

          CcQ004CadDis.FieldByName('CdUsuDis').AsInteger := pCdUsuari;
          CcQ004CadDis.FieldByName('DtDispos').AsString  := FormatDateTime('dd/mm/yyyy', Now());

          CcQ004CadDis.FieldByName('VrMatTer').AsFloat   := lCpQ004.FieldByName('VrMatTer').AsFloat;
          CcQ004CadDis.FieldByName('VrMatBra').AsFloat   := lCpQ004.FieldByName('VrMatBra').AsFloat;
          CcQ004CadDis.FieldByName('VrMulta' ).AsFloat   := lCpQ004.FieldByName('VrMulta' ).AsFloat;
          CcQ004CadDis.FieldByName('VrOutDir').AsFloat   := lCpQ004.FieldByName('VrOutDir').AsFloat;
          CcQ004CadDis.FieldByName('QtHorPar').AsInteger := lCpQ004.FieldByName('QtHorPar').AsInteger;
          CcQ004CadDis.FieldByName('VrHorPar').AsFloat   := lCpQ004.FieldByName('VrHorPar').AsFloat;
          CcQ004CadDis.FieldByName('QtHorCor').AsInteger := lCpQ004.FieldByName('QtHorCor').AsInteger;
          CcQ004CadDis.FieldByName('VrHorCor').AsFloat   := lCpQ004.FieldByName('VrHorCor').AsFloat;
          CcQ004CadDis.FieldByName('VrCusInc').AsFloat   := lCpQ004.FieldByName('VrCusInc').AsFloat;
          CcQ004CadDis.FieldByName('VrMatSup').AsFloat   := lCpQ004.FieldByName('VrMatSup').AsFloat;
          CcQ004CadDis.FieldByName('VrKmRoda').AsFloat   := lCpQ004.FieldByName('VrKmRoda').AsFloat;
          CcQ004CadDis.FieldByName('VrOutInd').AsFloat   := lCpQ004.FieldByName('VrOutInd').AsFloat;
          CcQ004CadDis.FieldByName('TtCusDir').AsFloat   := lCpQ004.FieldByName('TtCusDir').AsFloat;
          CcQ004CadDis.FieldByName('TtCusInd').AsFloat   := lCpQ004.FieldByName('TtCusInd').AsFloat;

          CcQ004CadDis.FieldByName('VrCusto' ).AsFloat   := lCpQ004.FieldByName('VrCusto' ).AsFloat;
          CcQ004CadDis.FieldByName('TxDisPos').AsString  := lCpQ004.FieldByName('TxDisPos').AsString;
          CcQ004CadDis.FieldByName('StRNC'   ).AsString  := lCpQ004.FieldByName('StRNC'   ).AsString;

          CcQ004CadDis.Post;

          CcQ004CadDis.BrApplyUpdates;

          SqlConnAdm.Commit(lDsTransa);

          Result := UFrmLogos.cDsMsgOk;
      except
           on E: Exception do
                 begin
                       SqlConnAdm.Rollback(lDsTransa);
                       Result := UFrmLogos.cDsMsgEr + E.Message;
                 end;
      end;

end;

function TDmAdm.GravarRevisoesDataEntrega(pDtParam: OleVariant; pCdUsuari: Integer): AnsiString;
var lCpParams : TClientDataSet;
    lCpT002   : TClientDataSet;   // Conhecimentos
    lDsTransa : TTransactionDesc;
begin
      try
          lCpParams       := TClientDataSet.Create(Self);
          lCpParams.Data  := pDtParam;

          lCpT002         := TClientDataSet.Create(Self);
          lCpT002.XMLData := lCpParams.FieldByName('XmlT002').AsString;

          lDsTransa       := DmDicion.NumeroTransactionID;
          SqlConnAdm.StartTransaction(lDsTransa);

          CcT002.BrFormName := lCpParams.FieldByName('NmForm').AsString;
          CcT002.BrUserCode := pCdUsuari;

          CcT021.BrFormName := lCpParams.FieldByName('NmForm').AsString;
          CcT021.BrUserCode := pCdUsuari;

          while not lCpT002.Eof do
          begin
                CcT002.Close;
                CcT002.BrParams.Clear;
                CcT002.BrParams.Add('<%CdEmpres%>;' + lCpT002.FieldByName('CdEmpres').AsString);
                CcT002.BrParams.Add('<%DsModeNF%>;' + lCpT002.FieldByName('DsModeNF').AsString);
                CcT002.BrParams.Add('<%NrSeriNF%>;' + lCpT002.FieldByName('NrSeriNF').AsString);
                CcT002.BrParams.Add('<%CdCTRC%>;'   + lCpT002.FieldByName('CdCTRC'  ).AsString);
                CcT002.Open;
                CcT002.Edit;
                CcT002.FieldByName('DtAgenda').AsString := lCpT002.FieldByName('DtAgenda').AsString;
                CcT002.FieldByName('DtPreCal').AsString := lCpT002.FieldByName('DtPreCal').AsString;
                CcT002.Post;
                CcT002.BrApplyUpdates;

                CcT021.Close;
                CcT021.BrParams.Clear;
                CcT021.BrParams.Add('<%CdEmpres%>;' + lCpT002.FieldByName('CdEmpres').AsString);
                CcT021.BrParams.Add('<%DsModeNF%>;' + lCpT002.FieldByName('DsModeNF').AsString);
                CcT021.BrParams.Add('<%NrSeriNF%>;' + lCpT002.FieldByName('NrSeriNF').AsString);
                CcT021.BrParams.Add('<%CdCTRC%>;'   + lCpT002.FieldByName('CdCTRC'  ).AsString);
                CcT021.Open;
                CcT021.Append;
                CcT021.FieldByName('CdEmpres').AsInteger := lCpT002.FieldByName('CdEmpres').AsInteger;
                CcT021.FieldByName('DsModeNF').AsString  := lCpT002.FieldByName('DsModeNF').AsString;
                CcT021.FieldByName('NrSeriNF').AsString  := lCpT002.FieldByName('NrSeriNF').AsString;
                CcT021.FieldByName('CdCTRC'  ).AsInteger := lCpT002.FieldByName('CdCTRC'  ).AsInteger;
                CcT021.FieldByName('CdUsuari').AsInteger := pCdUsuari;
                CcT021.FieldByName('DtAltera').AsString  := FormatDateTime('dd/mm/yyyy hh:mm:ss', Now());
                CcT021.FieldByName('DtAgenda').AsString  := lCpT002.FieldByName('DtAgenda').AsString;
                CcT021.FieldByName('DtPreCal').AsString  := lCpT002.FieldByName('DtPreCal').AsString;
                CcT021.FieldByName('DsMotivo').AsString  := lCpT002.FieldByName('DsMotivo').AsString;
                CcT021.Post;
                CcT021.BrApplyUpdates;

                lCpT002.Next;
          end;

          SqlConnAdm.Commit(lDsTransa);

          Result := UFrmLogos.cDsMsgOk;
      except
          On E: exception do
             begin
                   SqlConnAdm.Rollback(lDsTransa);
                   Result := UFrmLogos.cDsMsgEr + E.Message;
             end;
      end;
end;

function TDmAdm.LiberarDesmontarCarga(pDtParam: OleVariant; pCdUsuari: Integer): AnsiString;
var lCpParams : TClientDataSet;
    lCpT002   : TClientDataSet;   // Conhecimentos
    lDsTransa : TTransactionDesc;
begin
      try
          lCpParams       := TClientDataSet.Create(Self);
          lCpParams.Data  := pDtParam;

          lCpT002         := TClientDataSet.Create(Self);
          lCpT002.XMLData := lCpParams.FieldByName('XmlT002').AsString;

          lDsTransa       := DmDicion.NumeroTransactionID;
          SqlConnAdm.StartTransaction(lDsTransa);

          CcT008.BrFormName := lCpParams.FieldByName('NmForm').AsString;
          CcT008.BrUserCode := pCdUsuari;

          while not lCpT002.Eof do
          begin
                CcT008.Close;
                CcT008.BrParams.Clear;
                CcT008.BrParams.Add('<%CdEmpres%>;' + lCpT002.FieldByName('CdEmpres').AsString);
                CcT008.BrParams.Add('<%DsModeNF%>;' + lCpT002.FieldByName('DsModeNF').AsString);
                CcT008.BrParams.Add('<%NrSeriNF%>;' + lCpT002.FieldByName('NrSeriNF').AsString);
                CcT008.BrParams.Add('<%CdCTRC%>;'   + lCpT002.FieldByName('CdCTRC'  ).AsString);
                CcT008.BrParams.Add('<%CdEmpAtu%>;' + lCpT002.FieldByName('CdEmpAtu').AsString);
                CcT008.BrParams.Add('<%CdCarga%>;'  + lCpT002.FieldByName('CdCarga' ).AsString);
                CcT008.Open;
                CcT008.Edit;
                CcT008.FieldByName('StMovime').AsString := 'F';
                CcT008.Post;
                CcT008.BrApplyUpdates;

                CcT008.Append;
                CcT008.FieldByName('CdEmpres').AsInteger :=
                                     lCpT002.FieldByName('CdEmpres').AsInteger;
                CcT008.FieldByName('DsModeNF').AsString  :=
                                     lCpT002.FieldByName('DsModeNF').AsString;
                CcT008.FieldByName('NrSeriNF').AsString  :=
                                     lCpT002.FieldByName('NrSeriNF').AsString;
                CcT008.FieldByName('CdCTRC'  ).AsInteger :=
                                     lCpT002.FieldByName('CdCTRC'  ).AsInteger;
                CcT008.FieldByName('NrSequen').AsInteger :=
                                     DmDicion.BrvDicionario.ProxNumeroSequencial('T008','NrSequen');
                CcT008.FieldByName('CdEmpAtu').AsInteger :=
                                     lCpParams.FieldByName('CdEmpDes').AsInteger;
                CcT008.FieldByName('DtMovTo').AsString   :=
                                     FormatDateTime('dd/mm/yyyy hh:mm:ss', Now());
                CcT008.FieldByName('StMovime').AsString  := 'P';
                CcT008.Post;
                CcT008.BrApplyUpdates;

                lCpT002.Next;
          end;

          SqlConnAdm.Commit(lDsTransa);

          Result := UFrmLogos.cDsMsgOk;
      except
          On E: exception do
             begin
                   SqlConnAdm.Rollback(lDsTransa);
                   Result := UFrmLogos.cDsMsgEr + E.Message;
             end;
      end;
end;

function TDmAdm.AtualizarOperacaoArmazem(pDtParam: OleVariant; pCdUsuari: Integer): AnsiString;
var lCpParams : TClientDataSet;
    lCpW005   : TClientDataSet;   // OPERAÇÃO LOGÍSTICA
    lDsTransa : TTransactionDesc;
begin
      try
          lCpParams       := TClientDataSet.Create(Self);
          lCpParams.Data  := pDtParam;

          lCpW005         := TClientDataSet.Create(Self);
          lCpW005.XMLData := lCpParams.FieldByName('XmlW005').AsString;

          lDsTransa       := DmDicion.NumeroTransactionID;
          SqlConnAdm.StartTransaction(lDsTransa);

          CcW005.BrFormName := lCpParams.FieldByName('NmForm').AsString;
          CcW005.BrUserCode := pCdUsuari;

          while not lCpW005.Eof do
          begin
                CcW005.Close;
                CcW005.BrParams.Clear;
                CcW005.BrParams.Add('<%CdArmaze%>;' + lCpW005.FieldByName('CdArmaze').AsString);
                CcW005.BrParams.Add('<%NrOpeLog%>;' + lCpW005.FieldByName('NrOpeLog').AsString);
                CcW005.Open;
                CcW005.Edit;
                CcW005.FieldByName('StOpeLog').AsString := 'E';
                CcW005.Post;
                CcW005.BrApplyUpdates;

                lCpW005.Next;
          end;

          SqlConnAdm.Commit(lDsTransa);

          Result := UFrmLogos.cDsMsgOk;
      except
          On E: exception do
             begin
                   SqlConnAdm.Rollback(lDsTransa);
                   Result := UFrmLogos.cDsMsgEr + E.Message;
             end;
      end;
end;

function TDmAdm.AtualizarChaves(pDtParam: OleVariant; pCdUsuari: Integer): AnsiString;
var lCpParams : TClientDataSet;
    lDsTransa : TTransactionDesc;
begin
      try
          lCpParams       := TClientDataSet.Create(Self);
          lCpParams.Data  := pDtParam;

          lDsTransa       := DmDicion.NumeroTransactionID;
          SqlConnAdm.StartTransaction(lDsTransa);

          QcS052Upd.BrFormName := lCpParams.FieldByName('NmForm').AsString;
          QcS052Upd.BrUserCode := pCdUsuari;

          QcS052Upd.BrParams.Clear;
          QcS052Upd.BrParams.Add('<%CdUsuari%>;' + IntToStr(pCdUsuari));
          QcS052Upd.BrParams.Add('<%NrLibera%>;' + lCpParams.FieldByName('NrLibera').AsString);
          QcS052Upd.BrExecuteSQL(True);

          SqlConnAdm.Commit(lDsTransa);

          Result := UFrmLogos.cDsMsgOk;
      except
          On E: exception do
             begin
                   SqlConnAdm.Rollback(lDsTransa);
                   Result := UFrmLogos.cDsMsgEr + E.Message;
             end;
      end;
end;

function TDmAdm.ValidarChaveLiberacao(pDtParam: OleVariant; pCdUsuari: Integer): AnsiString;
var lCcParam  : TBrvClientDataSet;
    lCcS052   : TBrvClientDataSet;
    lCcS053   : TBrvClientDataSet;
    lTpForMul : String;
    lNrSeqFor : Integer;
    lNmForm   : String;
begin
      try
          lCcParam := TBrvClientDataSet.Create(Self);
          lCcS052  := TBrvClientDataSet.Create(Self);
          lCcS053  := TBrvClientDataSet.Create(Self);

          lCcParam.BrDicionario := DmDicion.BrvDicionario;
          lCcS052.BrDicionario  := DmDicion.BrvDicionario;
          lCcS053.BrDicionario  := DmDicion.BrvDicionario;

          lCcParam.Close;
          lCcParam.Data := pDtParam;
          lCcParam.Open;

          lCcS052.Close;
          lCcS052.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(275, '<%NrLibera%>;' +
                                              lCcParam.FieldByName('NrLibera').AsString, Self.name);
          lCcS052.Open;

          //Verifica se senha existe na relação cadastrada
          if (lCcS052.RecordCount > 0) then
          begin
                //Verifica se senha foi atribuida para algum usuário
                if (lCcS052.FieldByName('CdUsuari').AsInteger > 0) then
                begin
                      if (lCcS052.FieldByName('DTUTILIZ').AsDateTime > 0) then
                      begin
                            Result := '1Chave de Autorização já utilizada!';
                      end else
                      begin
                            lNmForm   := lCcParam.FieldByName('NmForm').AsString;
                            lTpForMul := copy(lNmForm, 1, 3);
                            lNrSeqFor := StrToInt(copy(lNmForm, 4, 7));

                            lCcS053.Close;
                            lCcS053.BrParams.Clear;
                            lCcS053.BrParams.Add('<%CdUsuari%>;' +
                                                          lCcS052.FieldByName('CdUsuari').AsString);
                            lCcS053.BrParams.Add('<%TpForMul%>;' + UpperCase(lTpForMul));
                            lCcS053.BrParams.Add('<%NrSeqFor%>;' + IntToStr(lNrSeqFor));
                            lCcS053.Data := DmDicion.BrvDicionario.RetornaDadosSqlCadastro(276,
                                                                  lCcS053.BrParams.Text, Self.name);
                            lCcS053.Open;

                            if (lCcS053.RecordCount = 0) then
                            begin
                                  Result := '1Chave de Autorização inválida para esta atividade.';
                            end else
                            begin
                                  Result := '0Chave de Autorização Ok';
                            end;

                      end;

                end else
                begin
                      Result := '1Chave de Autorização inválida ou não atribuída...';
                end;
          end else
          begin
                Result := '1Chave de Autorização inexistente...';
          end;

      finally
          FreeAndNil(lCcParam);
          FreeAndNil(lCcS052);
          FreeAndNil(lCcS053);
      end;
end;

function TDmAdm.AtualizarChaveLiberacao(pDtParam: OleVariant; pCdUsuari: Integer): AnsiString;
var lCpParams : TClientDataSet;
    lDsTransa : TTransactionDesc;
begin
      try
          lDsTransa := DmDicion.NumeroTransactionID;
          SqlConnAdm.StartTransaction(lDsTransa);

          try
              lCpParams := TClientDataSet.Create(Self);
              lCpParams.Close;
              lCpParams.Data := pDtParam;
              lCpParams.Open;

              CcS052.BrFormName := lCpParams.FieldByName('NmForm').AsString;
              CcS052.BrUserCode := pCdUsuari;

              CcS052.Close;
              CcS052.BrParams.Clear;
              CcS052.BrParams.Add('<%NrLibera%>;' + lCpParams.FieldByName('NrLibera').AsString);
              CcS052.Open;

              if (CcS052.RecordCount > 0) then
              begin
                    CcS052.Edit;
                    CcS052.FieldByName('CdUsuUti').AsInteger :=
                                                       lCpParams.FieldByName('CdUsuUti').AsInteger;
                    CcS052.FieldByName('DtUtiliz').AsString  :=
                                                       FormatDateTime('dd/mm/yyyy hh:mm:ss', Now());
                    CcS052.FieldByName('TxDadMov').AsString  :=
                                                       lCpParams.FieldByName('TxDadMov').AsString;
                    CcS052.Post;
                    CcS052.BrApplyUpdates;

                    SqlConnAdm.Commit(lDsTransa);
                    Result := UFrmLogos.cDsMsgOk;
              end else
              begin
                    Result := '1Registro não encontrado!';
              end;

          except
              On E: exception do
                 begin
                       SqlConnAdm.Rollback(lDsTransa);
                       Result := UFrmLogos.cDsMsgEr + E.Message;
                 end;
          end;

      finally
          FreeAndNil(lCpParams);
      end;
end;

function TDmAdm.CancelarNotaFiscal(pDtF018: OleVariant): AnsiString;
var lDsTransa : TTransactionDesc;
    CcF013Aut : TBrvClientDataSet;
begin
      try
          lDsTransa := DmDicion.NumeroTransactionID;
          SqlConnAdm.StartTransaction(lDsTransa);

          QpF013.Data := pDtF018;


          while not QpF013.Eof do
          begin
                if QpF013.FieldByName('TpNFe').AsString = 'S' then
                begin
                      CcF004.Close;
                      CcF004.BrParams.Clear;
                      CcF004.BrParams.Add('<%CdEmpres%>;' + QpF013.FieldByName('CdEmpres').AsString);
                      CcF004.BrParams.Add('<%DsModeNf%>;' + QpF013.FieldByName('DsModeNf').AsString);
                      CcF004.BrParams.Add('<%NrSeriNF%>;' + QpF013.FieldByName('NrSeriNF').AsString);
                      CcF004.BrParams.Add('<%NrNota%>;'   + QpF013.FieldByName('NrNota').AsString);
                      CcF004.Open;

                      if CcF004.RecordCount > 0 then
                      begin
                            CcF004.Edit;
                            CcF004.FieldByName('StNota').AsString   :=
                                                           QpF013.FieldByName('StNota').AsString;
                            CcF004.FieldByName('TxRetCan').AsString :=
                                                           QpF013.FieldByName('TxRetCan').AsString;
                            CcF004.Post;
                            CcF004.BrApplyUpdates;
                      end;
                end else
                begin
                      CcF001.Close;
                      CcF001.BrQueryCode := 285;
                      CcF001.BrParams.Clear;
                      CcF001.BrParams.Add('<%CdEmpres%>;' + QpF013.FieldByName('CdEmpres').AsString);
                      CcF001.BrParams.Add('<%CdTitula%>;' + QpF013.FieldByName('CdTitula').AsString);
                      CcF001.BrParams.Add('<%DsModeNf%>;' + QpF013.FieldByName('DsModeNf').AsString);
                      CcF001.BrParams.Add('<%NrSeriNF%>;' + QpF013.FieldByName('NrSeriNF').AsString);
                      CcF001.BrParams.Add('<%NrNota%>;'   + QpF013.FieldByName('NrNota').AsString);
                      CcF001.Open;

                      if CcF001.RecordCount > 0 then
                      begin
                            CcF001.Edit;
                            CcF001.FieldByName('StNota').AsString   :=
                                                            QpF013.FieldByName('StNota').AsString;
                            CcF001.FieldByName('TxRetCan').AsString :=
                                                            QpF013.FieldByName('TxRetCan').AsString;
                            CcF001.Post;
                            CcF001.BrApplyUpdates;
                      end;

                end;
                QpF013.Next;
          end;

          SqlConnAdm.Commit(lDsTransa);
          Result := UFrmLogos.cDsMsgOk;
      except
          On E: exception do
             begin
                   SqlConnAdm.Rollback(lDsTransa);
                   Result := UFrmLogos.cDsMsgEr + E.Message;
             end;
      end;


end;
function TDmAdm.GravarXMLNotaFiscal(pDtF003: OleVariant): AnsiString;
var lDsTransa : TTransactionDesc;
    CcF013Aut : TBrvClientDataSet;
begin
      try
          lDsTransa := DmDicion.NumeroTransactionID;
          SqlConnAdm.StartTransaction(lDsTransa);

          QpF013.Data := pDtF003;


          while not QpF013.Eof do
          begin
                CcF003.Close;
                CcF003.BrQueryCode :=  224 ;
                CcF003.BrParams.Clear;

                CcF003.BrParams.Add('<%NrChaDoc%>;' + QpF013.FieldByName('NrChaDoc').AsString);

                CcF003.Open;

                if CcF003.RecordCount > 0 then
                begin
                      CcF003.Edit;
                      CcF003.FieldByName('txxml').AsString      := QpF013.FieldByName('txxml').AsString;
                      CcF003.FieldByName('dtemissa').AsDateTime := QpF013.FieldByName('dtemissa').AsDateTime;
                      CcF003.FieldByName('tpxml').AsString      := QpF013.FieldByName('tpxml').AsString;
                      CcF003.FieldByName('sntransp').AsString   := QpF013.FieldByName('sntransp').AsString;
                      CcF003.FieldByName('cdempres').AsInteger  := QpF013.FieldByName('cdempres').AsInteger;
                      CcF003.FieldByName('stmanife').AsInteger  := QpF013.FieldByName('stmanife').AsInteger;
                      CcF003.Post;
                      CcF003.BrApplyUpdates;
                end else
                begin
                      CcF003.Append;
                      CcF003.FieldByName('nrchadoc').AsString   := QpF013.FieldByName('nrchadoc').AsString;
                      CcF003.FieldByName('txxml').AsString      := QpF013.FieldByName('txxml').AsString;
                      CcF003.FieldByName('dtemissa').AsDateTime := QpF013.FieldByName('dtemissa').AsDateTime;
                      CcF003.FieldByName('tpxml').AsString      := QpF013.FieldByName('tpxml').AsString;
                      CcF003.FieldByName('sntransp').AsString   := QpF013.FieldByName('sntransp').AsString;
                      CcF003.FieldByName('cdempres').AsInteger  := QpF013.FieldByName('cdempres').AsInteger;
                      CcF003.FieldByName('stmanife').AsInteger  := QpF013.FieldByName('stmanife').AsInteger;
                      CcF003.FieldByName('NrSeqXml').AsInteger := DmDicion.BrvDicionario.ProxNumeroSequencial('F003', 'NRSEQXML');
                      CcF003.Post;
                      CcF003.BrApplyUpdates;
                end;
                QpF013.Next;
          end;

          SqlConnAdm.Commit(lDsTransa);
          Result := UFrmLogos.cDsMsgOk;
      except
          On E: exception do
             begin
                   SqlConnAdm.Rollback(lDsTransa);
                   Result := UFrmLogos.cDsMsgEr + E.Message;
             end;
      end;


end;

end.


