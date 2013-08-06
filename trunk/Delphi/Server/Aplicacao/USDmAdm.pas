unit USDmAdm;

interface

uses
  SysUtils, Classes, DSServer, DbClient, DB;

type
  TSDmAdm = class(TDSServerModule)
  private
    { Private declarations }
  public
    { Public declarations }
    function GravarPreLancamentoNFEntrada(pDsCreden: AnsiString; pDtF013 : OleVariant;
                                          pDtF014: OleVariant; pDtF015: OleVariant;
                                          pDtF017: OleVariant): AnsiString;

    function FinalizarPreLancamentoNFEntrada(pDsCreden: AnsiString;
                                             pNrPleLan: Integer): AnsiString;

    function AutorizaPreLancamentoNFEntrada(pDsCreden: AnsiString; pDtF013: OleVariant): AnsiString;

    function MontaLotePreLancamentoNFEntrada(pDsCreden: AnsiString; pDtF013: OleVariant;
                                             pNrLote: Integer; pDsLote : AnsiString): AnsiString;

    function RecepcionaLotePreLancamentoNFEntrada(pDsCreden: AnsiString;
                                                  pDtF012: OleVariant): AnsiString;

    function PeriodoContabilValido(pDsCreden: AnsiString; pDtF018: OleVariant): AnsiString;

    function FinalizarDigitacaoNota(pDsCreden: AnsiString;
                                    pDtF001  : OleVariant;
                                    pDtF002  : OleVariant;
                                    pDtN002  : OleVariant;
                                    pDtN003  : OleVariant;
                                    pDtParCon: OleVariant): AnsiString;

    function EfetuarLancamentosContabeis(pDsCreden: String; pDtParCon: OleVariant): AnsiString;

    function GravarSaldoPlano(pDsCreden: String; pDtSalCon: OleVariant;
                                                                     pNmFormul: String): AnsiString;

    function BaixarDocumento(pDsCreden: String; pTpOperac: String; pDtN002 : OleVariant;
                             pDtParCon: OleVariant; pCdHistor : Integer;
                             pDtBaixa: TDateTime): AnsiString;

    function Faturar(pDsCreden : String; pDtParam  : OleVariant) : OleVariant;

    function LancarContasPagarReceber(pDsCreden : String;
                                      pNmFormul : String;       pCdHistor : Integer;
                                      pTpPagRec : String;       pVrLancto : Double;
                                      pCdForPag : Integer;      pCdTitula : Integer;
                                      pCdEmpres : Integer;      pNrDocto  : Integer;
                                      pDtEmissa : TDateTime;    pNrPreCar : Integer;
                                      pNrOrdem  : Integer;      pDtVencto : TDateTime;
                                      pCdEvento : Integer;      pTpPagto  : String;
                                      pNrConCon : Integer;      pDsComHis : String;
                                      pCdCenCus : Integer;      pNrPlano  : Integer): AnsiString;
    function BuscarNFeManifesto(pDsCreden: String;opStatus:integer;NrChadoc:String;cnpj:String;NrCertif:String;NrSenCer:String;CdEstado:String;CdEmpres:String):String;

    function GravarRNC(pDsCreden: String; pDtParam: OleVariant): AnsiString;

    function GravarDisposicaoRNC(pDsCreden: String; pDtParam: OleVariant):String;

    function GravarRevisoesDataEntrega(pDsCreden: String; pDtParam: OleVariant): AnsiString;

    function LiberarDesmontarCarga(pDsCreden: String; pDtParam: OleVariant): AnsiString;

    function AtualizarOperacaoArmazem(pDsCreden: String; pDtParam: OleVariant): AnsiString;

    function AtualizarChaves(pDsCreden: String; pDtParam: OleVariant): AnsiString;

    function ValidarChaveLiberacao(pDsCreden: String; pDtParam: OleVariant): AnsiString;

    function CancelarNotaFiscal(pDsCreden: String; pDtParam: OleVariant): AnsiString;

    function GravarXMLNotaFiscal(pDsCreden: String; pDtParam: OleVariant): AnsiString;
  end;

implementation

uses UFrmLogos, UDmAdm, UImpManif;

{$R *.dfm}

{ TSDmAdm }

function TSDmAdm.BaixarDocumento(pDsCreden: String; pTpOperac: String;
                                 pDtN002 : OleVariant; pDtParCon: OleVariant;
                                 pCdHistor : Integer; pDtBaixa: TDateTime): AnsiString;
var lCdUsuari: Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.BaixarDocumento(pTpOperac, pDtN002, pDtParCon, lCdUsuari, pCdHistor,
                                            pDtBaixa);
      end;
end;

function TSDmAdm.EfetuarLancamentosContabeis(pDsCreden: String; pDtParCon: OleVariant): AnsiString;
var lCdUsuari: Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.EfetuarLancamentosContabeis(pDtParCon);
      end;
end;

function TSDmAdm.Faturar(pDsCreden: String; pDtParam: OleVariant): OleVariant;
var lCcResult : TClientDataSet;
    lCdUsuari : Integer;
    lNrConta  : String;
begin
      lCcResult.FieldDefs.Clear;
      lCcResult.FieldDefs.Add('NrConta' , ftInteger,   0);
      lCcResult.FieldDefs.Add('DsResult', ftString , 100);
      lCcResult.CreateDataSet;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            lCcResult.Append;
            lCcResult.FieldByName('NrConta' ).AsInteger := 0;
            lCcResult.FieldByName('DsResult').AsString  := UFrmLogos.cDsMsgEr +
                                                                              'Credencial inválida';
            lCcResult.Post;
      end else
      begin
            lNrConta := DmAdm.Faturar(pDsCreden, pDtParam);

            if (Copy(lNrConta, 1, 3) = '1; ') then
            begin
                  lCcResult.Append;
                  lCcResult.FieldByName('NrConta' ).AsInteger := 0;
                  lCcResult.FieldByName('DsResult').AsString  := lNrConta;
                  lCcResult.Post;
            end else
            begin
                  lCcResult.Append;
                  lCcResult.FieldByName('NrConta' ).AsInteger := StrToInt(lNrConta);
                  lCcResult.FieldByName('DsResult').AsString  := UFrmLogos.cDsMsgOk;
                  lCcResult.Post;
            end;
      end;
end;

function TSDmAdm.FinalizarDigitacaoNota(pDsCreden: AnsiString; pDtF001, pDtF002, pDtN002,
                                        pDtN003, pDtParCon: OleVariant): AnsiString;
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.FinalizarDigitacaoNota(pDtF001, pDtF002,
                                                   pDtN002, pDtN003, pDtParCon, lCdUsuari);
      end;
end;

function TSDmAdm.FinalizarPreLancamentoNFEntrada(pDsCreden: AnsiString;
                                                 pNrPleLan: Integer): AnsiString;
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.FinalizarPreLancamentoNFEntrada(pNrPleLan, lCdUsuari);
      end;
end;

function TSDmAdm.GravarPreLancamentoNFEntrada(pDsCreden: AnsiString; pDtF013, pDtF014,
  pDtF015, pDtF017: OleVariant): AnsiString;
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.GravarPreLancamentoNFEntrada(pDtF013, pDtF014, pDtF015, pDtF017);
      end;
end;

function TSDmAdm.GravarSaldoPlano(pDsCreden: String; pDtSalCon: OleVariant;
                                                                     pNmFormul: String): AnsiString;
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.GravarSaldoPlano(pDtSalCon, pNmFormul, lCdUsuari);
      end;
end;

function TSDmAdm.LancarContasPagarReceber(pDsCreden: String; pNmFormul: String; pCdHistor: Integer;
                                          pTpPagRec: String; pVrLancto: Double; pCdForPag,pCdTitula,
                                          pCdEmpres, pNrDocto: Integer; pDtEmissa: TDateTime;
                                          pNrPreCar, pNrOrdem: Integer; pDtVencto: TDateTime;
                                          pCdEvento: Integer; pTpPagto: String; pNrConCon: Integer;
                                          pDsComHis: String; pCdCenCus, pNrPlano: Integer): AnsiString;
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.LancarContasPagarReceber(pNmFormul, pCdHistor, pTpPagRec, pVrLancto,
                                                     pCdForPag, pCdTitula, pCdEmpres, pNrDocto,
                                                     pDtEmissa, pNrPreCar, pNrOrdem, pDtVencto,
                                                     pCdEvento, pTpPagto, pNrConCon, pDsComHis,
                                                     pCdCenCus, pNrPlano);
      end;
end;

function TSDmAdm.AutorizaPreLancamentoNFEntrada(pDsCreden: AnsiString;
                                                                   pDtF013: OleVariant): AnsiString;

var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.AutorizaPreLancamentoNFEntrada(pDtF013, lCdUsuari);
      end;
end;

function TSDmAdm.MontaLotePreLancamentoNFEntrada(pDsCreden: AnsiString; pDtF013: OleVariant;
                                                pNrLote: Integer; pDsLote : AnsiString): AnsiString;
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.MontaLotePreLancamentoNFEntrada(pDtF013, lCdUsuari, pNrLote, pDsLote);
      end;
end;

function TSDmAdm.PeriodoContabilValido(pDsCreden: AnsiString; pDtF018: OleVariant): AnsiString;
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.PeriodoContabilValido(pDtF018);
      end;
end;

function TSDmAdm.RecepcionaLotePreLancamentoNFEntrada(pDsCreden: AnsiString;
                                                                   pDtF012: OleVariant): AnsiString;
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.RecepcionaLotePreLancamentoNFEntrada(pDtF012, lCdUsuari);
      end;
end;

function TSDmAdm.BuscarNFeManifesto(pDsCreden: String;opStatus:integer;NrChadoc:String;cnpj:String;NrCertif:String;NrSenCer:String;CdEstado:String;CdEmpres:String):String;
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := BoolToStr(UImpManif.BuscarNFeManifestoWS(opStatus,NrChadoc,cnpj,NrCertif,NrSenCer,CdEstado,CdEmpres));
      end;
end;

function TSDmAdm.GravarRNC(pDsCreden: String; pDtParam: OleVariant): AnsiString;
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.GravarRNC(pDtParam, lCdUsuari);
      end;
end;

function  TSDmAdm.GravarDisposicaoRNC(pDsCreden: String; pDtParam: OleVariant):String;
var lCdUsuari : Integer;
    lCcResult : TClientDataSet;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
           Result := DmAdm.GravarDisposicaoRNC(pDtParam, lCdUsuari);
      end;
end;

function TSDmAdm.GravarRevisoesDataEntrega(pDsCreden: String; pDtParam: OleVariant): AnsiString;
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.GravarRevisoesDataEntrega(pDtParam, lCdUsuari);
      end;
end;

function TSDmAdm.LiberarDesmontarCarga(pDsCreden: String; pDtParam: OleVariant): AnsiString;
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.LiberarDesmontarCarga(pDtParam, lCdUsuari);
      end;
end;

function TSDmAdm.AtualizarOperacaoArmazem(pDsCreden: String; pDtParam: OleVariant): AnsiString;
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.AtualizarOperacaoArmazem(pDtParam, lCdUsuari);
      end;
end;

function TSDmAdm.AtualizarChaves(pDsCreden: String; pDtParam: OleVariant): AnsiString;
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.AtualizarChaves(pDtParam, lCdUsuari);
      end;
end;

function TSDmAdm.ValidarChaveLiberacao(pDsCreden: String; pDtParam: OleVariant): AnsiString;
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.ValidarChaveLiberacao(pDtParam, lCdUsuari);
      end;
end;

function TSDmAdm.CancelarNotaFiscal(pDsCreden: String; pDtParam: OleVariant): AnsiString;
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.CancelarNotaFiscal(pDtParam);
      end;
end;
function TSDmAdm.GravarXMLNotaFiscal(pDsCreden: String; pDtParam: OleVariant): AnsiString;
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            Result := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result := DmAdm.GravarXMLNotaFiscal(pDtParam);
      end;
end;

end.

