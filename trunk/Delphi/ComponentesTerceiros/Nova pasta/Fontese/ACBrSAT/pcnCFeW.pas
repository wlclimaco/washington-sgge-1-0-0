{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2013 Andr� Ferreira de Moraes               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }
{                                                                              }
{  Esse arquivo usa a classe  PCN (c) 2009 - Paulo Casagrande                  }
{  PCN - Projeto Cooperar NFe       (Found at URL:  www.projetocooperar.org)   }
{                                                                              }
{  Esta biblioteca � software livre; voc� pode redistribu�-la e/ou modific�-la }
{ sob os termos da Licen�a P�blica Geral Menor do GNU conforme publicada pela  }
{ Free Software Foundation; tanto a vers�o 2.1 da Licen�a, ou (a seu crit�rio) }
{ qualquer vers�o posterior.                                                   }
{                                                                              }
{  Esta biblioteca � distribu�da na expectativa de que seja �til, por�m, SEM   }
{ NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU      }
{ ADEQUA��O A UMA FINALIDADE ESPEC�FICA. Consulte a Licen�a P�blica Geral Menor}
{ do GNU para mais detalhes. (Arquivo LICEN�A.TXT ou LICENSE.TXT)              }
{                                                                              }
{  Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral Menor do GNU junto}
{ com esta biblioteca; se n�o, escreva para a Free Software Foundation, Inc.,  }
{ no endere�o 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          }
{ Voc� tamb�m pode obter uma copia da licen�a em:                              }
{ http://www.opensource.org/licenses/lgpl-license.php                          }
{                                                                              }
{ Daniel Sim�es de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Pra�a Anita Costa, 34 - Tatu� - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

{$I ACBr.inc}

unit pcnCFeW;

interface uses

  SysUtils, Classes,
  pcnAuxiliar, pcnConversao, pcnGerador, pcnCFe;

type

  TGeradorOpcoes = class;

  TCFeW = class(TPersistent)
  private
    FGerador: TGerador;
    FCFe: TCFe;
    FSchema: TpcnSchema;
    FOpcoes: TGeradorOpcoes;
    procedure GerarInfCFe;
    procedure GerarIde;
    procedure GerarEmit;
    procedure GerarEmitEnderEmit;
    procedure GerarDest;
    procedure GerarEntrega;
    procedure GerarDet;
    procedure GerarDetProd(const i: integer);
    procedure GerarDetobsFiscoDet(const i: integer);
    procedure GerarDetImposto(const i: integer);
    procedure GerarDetImpostoICMS(const i: integer);
    procedure GerarDetImpostoPIS(const i: integer);
    procedure GerarDetImpostoPISST(const i: integer);
    procedure GerarDetImpostoCOFINS(const i: integer);
    procedure GerarDetImpostoCOFINSST(const i: integer);
    procedure GerarDetImpostoISSQN(const i: integer);
    procedure GerarTotal;
    procedure GerarTotalICMSTotal;
    procedure GerarTotalISSQNtot;
    procedure GerarDescAcrEntr;
    procedure GerarPagto;
    procedure GerarInfAdic;
    procedure GerarInfAdicObsFisco;

  public
    constructor Create(AOwner: TCFe);
    destructor Destroy; override;
    function GerarXml: boolean;
    function ObterNomeArquivo: string;
  published
    property Gerador: TGerador read FGerador write FGerador;
    property CFe: TCFe read FCFe write FCFe;
    property schema: TpcnSchema read Fschema write Fschema;
    property Opcoes: TGeradorOpcoes read FOpcoes write FOpcoes;
  end;

  TGeradorOpcoes = class(TPersistent)
  private
    FAjustarTagNro: boolean;
    FGerarTagIPIparaNaoTributado: boolean;
    FGerarTXTSimultaneamente: boolean;
    FNormatizarMunicipios: boolean;
    FGerarTagAssinatura: TpcnTagAssinatura;
    FPathArquivoMunicipios: string;
    FValidarInscricoes: boolean;
    FValidarListaServicos: boolean;
  published
    property AjustarTagNro: boolean read FAjustarTagNro write FAjustarTagNro;
    property GerarTagIPIparaNaoTributado: boolean read FGerarTagIPIparaNaoTributado write FGerarTagIPIparaNaoTributado;
    property GerarTXTSimultaneamente: boolean read FGerarTXTSimultaneamente write FGerarTXTSimultaneamente;
    property NormatizarMunicipios: boolean read FNormatizarMunicipios write FNormatizarMunicipios;
    property GerarTagAssinatura: TpcnTagAssinatura read FGerarTagAssinatura write FGerarTagAssinatura;
    property PathArquivoMunicipios: string read FPathArquivoMunicipios write FPathArquivoMunicipios;
    property ValidarInscricoes: boolean read FValidarInscricoes write FValidarInscricoes;
    property ValidarListaServicos: boolean read FValidarListaServicos write FValidarListaServicos;
  end;

  ////////////////////////////////////////////////////////////////////////////////


implementation

{ TCFeW }

constructor TCFeW.Create(AOwner: TCFe);
begin
  FCFe := AOwner;
  FGerador := TGerador.Create;
  FGerador.FIgnorarTagNivel := '|?xml version|CFe xmlns|infCFe versao|obsCont|obsFisco|';
  FOpcoes := TGeradorOpcoes.Create;
  FOpcoes.FAjustarTagNro := True;
  FOpcoes.FGerarTXTSimultaneamente := False;
  FOpcoes.FGerarTagIPIparaNaoTributado := True;
  FOpcoes.FNormatizarMunicipios := False;
  FOpcoes.FGerarTagAssinatura := taSomenteSeAssinada;
  FOpcoes.FValidarInscricoes := False;
  FOpcoes.FValidarListaServicos := False;
end;

destructor TCFeW.Destroy;
begin
  FGerador.Free;
  FOpcoes.Free;
  inherited Destroy;
end;

procedure TCFeW.GerarInfCFe;
begin
  GerarIde;
  GerarEmit;
  GerarDest;
  GerarEntrega;
  GerarDet;
  GerarTotal;
  GerarPagto;
  GerarInfAdic;
end;

procedure TCFeW.GerarIde;
begin
  Gerador.wGrupo('ide', 'B01');
  Gerador.wCampo(tcInt, 'B02', 'cUF    ', 02, 02, 1, CFe.ide.cUF, DSC_CUF);
  if not ValidarCodigoUF(CFe.ide.cUF) then
     Gerador.wAlerta('B02', 'cUF', DSC_CUF, ERR_MSG_INVALIDO);
  Gerador.wCampo(tcStr, 'B03', 'cNF    ', 06, 06, 1, IntToStrZero(CFe.ide.cNF, 6), DSC_CNF);
  Gerador.wCampo(tcInt, 'B04', 'mod    ', 02, 02, 1, CFe.ide.modelo, DSC_MOD);
  Gerador.wCampo(tcInt, 'B05', 'nserieSAT', 09, 09, 1, CFe.ide.nserieSAT, DSC_SERIE);
  Gerador.wCampo(tcInt, 'B06', 'nCFe   ', 06, 06, 1, IntToStrZero(CFe.ide.nCFe,6), DSC_NCFE);
  Gerador.wCampo(tcDatCFe, 'B07', 'dEmi   ', 10, 10, 1, CFe.ide.dEmi, DSC_DEMI);
  Gerador.wCampo(tcHorCFe, 'B08', 'hEmi   ', 08, 08, 0, CFe.ide.hEmi, DSC_HEMI);
  Gerador.wCampo(tcInt, 'B09', 'cDV    ', 01, 01, 1, CFe.Ide.cDV, DSC_CDV);
  Gerador.wCampo(tcStr, 'B10', 'tpAmb  ', 01, 01, 1, tpAmbToStr(CFe.Ide.tpAmb), DSC_TPAMB);
  Gerador.wCampoCNPJCPF('B11', 'B11', CFe.Ide.CNPJ, 1058);
  Gerador.wCampo(tcStr, 'B12', 'signAC ',344, 344, 1, CFe.Ide.signAC, DSC_SIGNAC);
  Gerador.wCampo(tcStr, 'B13', 'assinaturaQRCODE', 441, 441, 1, CFe.Ide.assinaturaQRCODE, DSC_QRCODE);
  Gerador.wCampo(tcInt, 'B14', 'numeroCaixa', 03, 03, 1, CFe.ide.numeroCaixa, DSC_NUMEROCAIXA);
  Gerador.wGrupo('/ide');
end;

procedure TCFeW.GerarEmit;
begin
  Gerador.wGrupo('emit', 'C01');
  Gerador.wCampoCNPJCPF('C02', 'C02', CFe.Emit.CNPJCPF, 1058);
  Gerador.wCampo(tcStr, 'C03', 'xNome  ', 01, 60, 1, CFe.Emit.xNome, DSC_XNOME);
  Gerador.wCampo(tcStr, 'C04', 'xFant  ', 01, 60, 0, CFe.Emit.xFant, DSC_XNOME);
  (**)GerarEmitEnderEmit;
  Gerador.wCampo(tcStr, 'C12', 'IE      ', 12, 12, 1, SomenteNumeros(CFe.Emit.IE), DSC_IE);
  Gerador.wCampo(tcStr, 'C13', 'IM      ', 01, 15, 0, CFe.Emit.IM, DSC_IM);
  Gerador.wCampo(tcInt, 'C14', 'cRegTrib', 01, 01, 1, RegTribToStr(CFe.Emit.cRegTrib), DSC_REGTRIB);
  Gerador.wCampo(tcInt, 'C15', 'cRegTribISSQN', 01, 02, 0, RegTribISSQNToStr(CFe.Emit.cRegTribISSQN), DSC_REGISSQN);
  Gerador.wCampo(tcStr, 'C16', 'indRatISSQN', 01, 01, 1, indRatISSQNToStr(CFe.Emit.indRatISSQN), DSC_RATISSQN);

  Gerador.wGrupo('/emit');
end;

procedure TCFeW.GerarEmitEnderEmit;
begin
  Gerador.wGrupo('enderEmit', 'C05');
  Gerador.wCampo(tcStr, 'C06', 'xLgr    ', 02, 60, 1, CFe.Emit.EnderEmit.xLgr, DSC_XLGR);
  Gerador.wCampo(tcStr, 'C07', 'nro     ', 01, 60, 1, ExecutarAjusteTagNro(FOpcoes.FAjustarTagNro, CFe.Emit.enderEmit.nro), DSC_NRO);
  Gerador.wCampo(tcStr, 'C08', 'xCpl    ', 01, 60, 0, CFe.Emit.enderEmit.xCpl, DSC_XCPL);
  Gerador.wCampo(tcStr, 'C09', 'xBairro ', 02, 60, 1, CFe.Emit.enderEmit.xBairro, DSC_XBAIRRO);
  Gerador.wCampo(tcStr, 'C10', 'xMun    ', 02, 60, 1, CFe.Emit.enderEmit.xMun, DSC_XMUN);
  Gerador.wCampo(tcInt, 'C11', 'CEP     ', 08, 08, 1, CFe.Emit.enderEmit.CEP, DSC_CEP);
  Gerador.wGrupo('/enderEmit');
end;

procedure TCFeW.GerarDest;
begin
  Gerador.wGrupo('dest', 'E01');
  Gerador.wCampoCNPJCPF('E02', 'E03', CFe.Dest.CNPJCPF, 1058);
  Gerador.wCampo(tcStr, 'E04', 'xNome  ', 01, 60, 0, CFe.Dest.xNome, DSC_XNOME);
  Gerador.wGrupo('/dest');
end;

procedure TCFeW.GerarEntrega;
begin
 if (CFe.Entrega.xLgr <> '') or
    (CFe.Entrega.nro <> '') or
    (CFe.Entrega.xCpl <> '') or
    (CFe.Entrega.xBairro <> '') or
    (CFe.Entrega.xMun <> '') or
    (CFe.Entrega.UF <> '') then
 begin
    Gerador.wGrupo('entrega', 'G01');
    Gerador.wCampo(tcStr, 'G02', 'xLgr   ', 02, 60, 1, CFe.Entrega.xLgr, DSC_XLGR);
    Gerador.wCampo(tcStr, 'G03', 'nro    ', 01, 60, 1, ExecutarAjusteTagNro(FOpcoes.FAjustarTagNro, CFe.Entrega.nro), DSC_NRO);
    Gerador.wCampo(tcStr, 'G04', 'xCpl   ', 01, 60, 0, CFe.Entrega.xCpl, DSC_XCPL);
    Gerador.wCampo(tcStr, 'G05', 'xBairro', 01, 60, 1, CFe.Entrega.xBairro, DSC_XBAIRRO);
    Gerador.wCampo(tcStr, 'G06', 'xMun   ', 02, 60, 1, CFe.Entrega.xMun, DSC_XMUN);
    Gerador.wCampo(tcStr, 'G07', 'UF     ', 02, 02, 1, CFe.Entrega.UF, DSC_UF);
    if not ValidarUF(CFe.Entrega.UF) then
       Gerador.wAlerta('G07', 'UF', DSC_UF, ERR_MSG_INVALIDO);
    Gerador.wGrupo('/entrega');
 end;   
end;

procedure TCFeW.GerarDet;
var
  i: integer;
begin
  for i := 0 to CFe.Det.Count - 1 do
  begin

    Gerador.wGrupo('det nItem="' + IntToStr(CFe.Det[i].nItem) + '"', 'H01');
    Gerador.gtCampo('nItem', IntToStr(CFe.Det[i].nItem));
    (**)GerarDetProd(i);
    (**)GerarDetImposto(i);
    Gerador.IDNivel := 'H01';
    Gerador.wCampo(tcStr, 'V01', 'infAdProd', 01, 500, 0, CFe.Det[i].infAdProd, DSC_INFADPROD);
    Gerador.wGrupo('/det');
  end;
  if CFe.Det.Count > 500 then
    Gerador.wAlerta('H02', 'nItem', DSC_NITEM, ERR_MSG_MAIOR_MAXIMO + '500');
end;

procedure TCFeW.GerarDetProd(const i: integer);
begin
  Gerador.wGrupo('prod', 'I01');
  Gerador.wCampo(tcStr, 'I02 ', 'cProd   ', 01, 60, 1, CFe.Det[i].Prod.cProd, DSC_CPROD);
  Gerador.wCampo(tcStr, 'I03 ', 'cEAN    ', 00, 14, 0, CFe.Det[i].Prod.cEAN, DSC_CEAN);
  Gerador.wCampo(tcStr, 'I04 ', 'xProd   ', 1, 120, 1, CFe.Det[i].Prod.xProd, DSC_XPROD);
  Gerador.wCampo(tcStr, 'I05 ', 'NCM     ', 02, 08, 0, CFe.Det[i].Prod.NCM, DSC_NCM);
  Gerador.wCampo(tcEsp, 'I06 ', 'CFOP    ', 04, 04, 1, somenteNumeros(CFe.Det[i].Prod.CFOP), DSC_CFOP);
  Gerador.wCampo(tcStr, 'I07 ', 'uCom    ', 01, 06, 1, CFe.Det[i].Prod.uCom, DSC_UCOM);
  Gerador.wCampo(tcDe4, 'I08 ', 'qCom    ', 00, 15, 1, CFe.Det[i].Prod.qCom, DSC_QCOM);
  Gerador.wCampo(tcDe3, 'I09 ', 'vUnCom  ', 00, 21, 1, CFe.Det[i].Prod.vUnCom, DSC_VUNCOM);
  Gerador.wCampo(tcDe2, 'I10 ', 'vProd   ', 00, 15, 1, CFe.Det[i].Prod.vProd, DSC_VPROD);
  Gerador.wCampo(tcStr, 'I11 ', 'indRegra', 01, 01, 1, indRegraToStr(CFe.Det[i].Prod.indRegra), DSC_NITEMPED);
  Gerador.wCampo(tcDe2, 'I12 ', 'vDesc   ', 00, 15, 0, CFe.Det[i].Prod.vDesc, DSC_VDESC);
  Gerador.wCampo(tcDe2, 'I13 ', 'vOutro  ', 00, 15, 0, CFe.Det[i].Prod.vOutro, DSC_VOUTRO);
  Gerador.wCampo(tcDe2, 'I14 ', 'vItem   ', 00, 15, 1, CFe.Det[i].Prod.vItem, DSC_VITEM);
  Gerador.wCampo(tcDe2, 'I15 ', 'vRatDesc', 00, 15, 0, CFe.Det[i].Prod.vRatDesc, DSC_VRATDESC);
  Gerador.wCampo(tcDe2, 'I16 ', 'vRatAcr ', 00, 15, 0, CFe.Det[i].Prod.vRatAcr, DSC_VRATACR);
  (**)GerarDetobsFiscoDet(i);
  Gerador.wGrupo('/prod');
end;

procedure TCFeW.GerarDetobsFiscoDet(const i: integer);
var
  j: integer;
begin
  for j := 0 to CFe.Det[i].Prod.obsFiscoDet.Count - 1 do
  begin
    Gerador.wGrupo('obsFiscoDet xCampoDet"' + CFe.Det[i].Prod.obsFiscoDet.Items[j].xCampoDet + '"', 'I18');
    Gerador.wCampo(tcStr, 'I19', 'xTextoDet', 01, 60, 1, CFe.Det[i].Prod.obsFiscoDet.Items[j].xTextoDet, DSC_XTEXTO);
    Gerador.wGrupo('/obsFiscoDet');
  end;
end;

procedure TCFeW.GerarDetImposto(const i: integer);
begin
  Gerador.wGrupo('imposto', 'M01');
  Gerador.wCampo(tcDe2, 'M02', 'vItem12741', 03, 15, 0, CFe.Det[i].Imposto.vItem12741, DSC_VITEM12741);
  if CFe.Det[i].Imposto.ISSQN.cNatOp > 0 then
    (**)GerarDetImpostoISSQN(i)
  else
    (**)GerarDetImpostoICMS(i);

  (**)GerarDetImpostoPIS(i);
  (**)GerarDetImpostoPISST(i);
  (**)GerarDetImpostoCOFINS(i);
  (**)GerarDetImpostoCOFINSST(i);

  Gerador.wGrupo('/imposto');
end;

procedure TCFeW.GerarDetImpostoICMS(const i: integer);
begin
  Gerador.wGrupo('ICMS', 'N01');
  if CFe.Emit.cRegTrib = RTRegimeNormal then
  begin
    case CFe.Det[i].Imposto.ICMS.CST of
       cst00, cst20, cst90 :
          begin
            Gerador.wGrupo('ICMS00');
            Gerador.wCampo(tcStr, 'N06', 'Orig    ', 01, 01, 1, OrigTOStr(CFe.Det[i].Imposto.ICMS.orig), DSC_ORIG);
            Gerador.wCampo(tcStr, 'N07', 'CST     ', 02, 02, 1, CSTICMSTOStr(CFe.Det[i].Imposto.ICMS.CST), DSC_CST);
            Gerador.wCampo(tcDe2, 'N08', 'pICMS   ', 01, 05, 1, CFe.Det[i].Imposto.ICMS.pICMS, DSC_PICMS);
            Gerador.wCampo(tcDe2, 'N09', 'vICMS   ', 01, 15, 1, CFe.Det[i].Imposto.ICMS.vICMS, DSC_VICMS);
            Gerador.wGrupo('/ICMS00');
          end;
       cst40, cst41, cst50, cst60 :
          begin
            Gerador.wGrupo('ICMS40');
            Gerador.wCampo(tcStr, 'N06', 'Orig    ', 01, 01, 1, OrigTOStr(CFe.Det[i].Imposto.ICMS.orig), DSC_ORIG);
            Gerador.wCampo(tcStr, 'N07', 'CST     ', 02, 02, 1, CSTICMSTOStr(CFe.Det[i].Imposto.ICMS.CST), DSC_CST);
            Gerador.wGrupo('/ICMS40');
          end;
    end;
  end
  else
  begin
    case CFe.Det[i].Imposto.ICMS.CSOSN of
       csosn102, csosn300, csosn500 :
          begin
            Gerador.wGrupo('ICMSSN102');
            Gerador.wCampo(tcStr, 'N06', 'Orig    ', 01, 01, 1, OrigTOStr(CFe.Det[i].Imposto.ICMS.orig), DSC_ORIG);
            Gerador.wCampo(tcStr, 'N10 ', 'CSOSN', 03, 03, 1, CSOSNIcmsToStr(CFe.Det[i].Imposto.ICMS.CSOSN), DSC_CSOSN);
            Gerador.wGrupo('/ICMSSN102');
          end;
       csosn900 :
          begin
            Gerador.wGrupo('ICMSSN900');
            Gerador.wCampo(tcStr, 'N06', 'Orig    ', 01, 01, 1, OrigTOStr(CFe.Det[i].Imposto.ICMS.orig), DSC_ORIG);
            Gerador.wCampo(tcStr, 'N10', 'CSOSN', 03, 03, 1, CSOSNIcmsToStr(CFe.Det[i].Imposto.ICMS.CSOSN), DSC_CSOSN);
            Gerador.wCampo(tcDe2, 'N08', 'pICMS   ', 01, 05, 1, CFe.Det[i].Imposto.ICMS.pICMS, DSC_PICMS);
            Gerador.wCampo(tcDe2, 'N09', 'vICMS   ', 01, 15, 1, CFe.Det[i].Imposto.ICMS.vICMS, DSC_VICMS);
            Gerador.wGrupo('/ICMSSN900');
          end;
    end;
  end;
  Gerador.wGrupo('/ICMS');
end;

procedure TCFeW.GerarDetImpostoPIS(const i: integer);
begin
  Gerador.wGrupo('PIS', 'Q01');
  if CFe.Emit.cRegTrib = RTSimplesNacional then
  begin
    Gerador.wGrupo('PISSN', 'Q05');
    Gerador.wCampo(tcStr, 'Q07', 'CST      ', 02, 02, 1, CSTPISTOStr(CFe.Det[i].Imposto.PIS.CST), DSC_CST);
    Gerador.wGrupo('/PISSN');
  end
  else if CFe.Det[i].Imposto.PIS.CST in [pis01, pis02] then
  begin
    Gerador.wGrupo('PISAliq', 'Q02');
    Gerador.wCampo(tcStr, 'Q07', 'CST      ', 02, 02, 1, CSTPISTOStr(CFe.Det[i].Imposto.PIS.CST), DSC_CST);
    Gerador.wCampo(tcDe2, 'Q08', 'vBC      ', 01, 15, 1, CFe.Det[i].Imposto.PIS.vBC, DSC_VBC);
    Gerador.wCampo(tcDe4, 'Q09', 'pPIS     ', 01, 05, 1, CFe.Det[i].Imposto.PIS.pPIS, DSC_PPIS);
    Gerador.wCampo(tcDe2, 'Q10', 'vPIS     ', 01, 15, 1, CFe.Det[i].Imposto.PIS.vPIS, DSC_VPIS);
    Gerador.wGrupo('/PISAliq');
  end
  else if CFe.Det[i].Imposto.PIS.CST = pis03 then
  begin
    Gerador.wGrupo('PISQtde', 'Q03');
    Gerador.wCampo(tcStr, 'Q07', 'CST      ', 02, 02, 1, CSTPISTOStr(CFe.Det[i].Imposto.PIS.CST), DSC_CST);
    Gerador.wCampo(tcDe4, 'Q11', 'qBCProd  ', 01, 16, 1, CFe.Det[i].Imposto.PIS.qBCProd, DSC_QBCPROD);
    Gerador.wCampo(tcDe4, 'Q12', 'vAliqProd', 01, 15, 1, CFe.Det[i].Imposto.PIS.vAliqProd, DSC_VALIQPROD);
    Gerador.wCampo(tcDe2, 'Q10', 'vPIS     ', 01, 15, 1, CFe.Det[i].Imposto.PIS.vPIS, DSC_VPIS);
    Gerador.wGrupo('/PISQtde');
  end
  else if CFe.Det[i].Imposto.PIS.CST in [pis04, pis06, pis07, pis08, pis09] then
  begin
    Gerador.wGrupo('PISNT', 'Q04');
    Gerador.wCampo(tcStr, 'Q07', 'CST      ', 02, 02, 1, CSTPISTOStr(CFe.Det[i].Imposto.PIS.CST), DSC_CST);
    Gerador.wGrupo('/PISNT');
  end
  else if CFe.Det[i].Imposto.PIS.CST in [pis49, pis50, pis51, pis52, pis53, pis54, pis55, pis56, pis60, pis61, pis62, pis63, pis64, pis65, pis66, pis67, pis70, pis71, pis72, pis73, pis74, pis75, pis98, pis99] then
  begin

    if (CFe.Det[i].Imposto.PIS.vBC + CFe.Det[i].Imposto.PIS.pPIS > 0) and (CFe.Det[i].Imposto.PIS.qBCProd + CFe.Det[i].Imposto.PIS.vAliqProd > 0) then
      Gerador.wAlerta('Q06', 'PISOutr', DSC_PISOUTR, 'As TAG <vBC> e <pPIS> n�o podem ser informadas em conjunto com as TAG <qBCProd> e <vAliqProd>');

    if (CFe.Det[i].Imposto.PIS.qBCProd + CFe.Det[i].Imposto.PIS.vAliqProd > 0) then
    begin
      Gerador.wGrupo('PISOutr', 'Q06');
      Gerador.wCampo(tcStr, 'Q07', 'CST      ', 02, 02, 1, CSTPISTOStr(CFe.Det[i].Imposto.PIS.CST), DSC_CST);
      Gerador.wCampo(tcDe4, 'Q11', 'qBCProd  ', 01, 16, 1, CFe.Det[i].Imposto.PIS.qBCProd, DSC_QBCPROD);
      Gerador.wCampo(tcDe4, 'Q12', 'vAliqProd', 01, 15, 1, CFe.Det[i].Imposto.PIS.vAliqProd, DSC_VALIQPROD);
      Gerador.wCampo(tcDe2, 'Q10', 'vPIS     ', 01, 15, 1, CFe.Det[i].Imposto.PIS.vPIS, DSC_VPIS);
      Gerador.wGrupo('/PISOutr');
    end
    else
    begin
      Gerador.wGrupo('PISOutr', 'Q06');
      Gerador.wCampo(tcStr, 'Q06', 'CST      ', 02, 02, 1, CSTPISTOStr(CFe.Det[i].Imposto.PIS.CST), DSC_CST);
      Gerador.wCampo(tcDe2, 'Q08', 'vBC      ', 01, 15, 1, CFe.Det[i].Imposto.PIS.vBC, DSC_VBC);
      Gerador.wCampo(tcDe4, 'Q09', 'pPIS     ', 01, 05, 1, CFe.Det[i].Imposto.PIS.pPIS, DSC_PPIS);
      Gerador.wCampo(tcDe2, 'Q10', 'vPIS     ', 01, 15, 1, CFe.Det[i].Imposto.PIS.vPIS, DSC_VPIS);
      Gerador.wGrupo('/PISOutr');
    end;
  end;
  Gerador.wGrupo('/PIS');
end;

procedure TCFeW.GerarDetImpostoPISST(const i: integer);
begin
  if (CFe.Det[i].Imposto.PISST.vBc > 0) or
    (CFe.Det[i].Imposto.PISST.pPis > 0) or
    (CFe.Det[i].Imposto.PISST.qBCProd > 0) or
    (CFe.Det[i].Imposto.PISST.vAliqProd > 0) or
    (CFe.Det[i].Imposto.PISST.vPIS > 0) then
  begin
    Gerador.wGrupo('PISST', 'R01');
    if (CFe.Det[i].Imposto.PISST.qBCProd + CFe.Det[i].Imposto.PISST.vAliqProd > 0) then
     begin
       Gerador.wCampo(tcDe4, 'R04', 'qBCProd  ', 01, 16, 1, CFe.Det[i].Imposto.PISST.qBCProd, DSC_QBCPROD);
       Gerador.wCampo(tcDe4, 'R05', 'vAliqProd', 01, 15, 1, CFe.Det[i].Imposto.PISST.vAliqProd, DSC_VALIQPROD);
     end
    else
     begin
       Gerador.wCampo(tcDe2, 'R02', 'vBC      ', 01, 15, 1, CFe.Det[i].Imposto.PISST.vBc, DSC_VBC);
       Gerador.wCampo(tcDe2, 'R03', 'pPIS     ', 01, 05, 1, CFe.Det[i].Imposto.PISST.pPis, DSC_PPIS);
     end;
    Gerador.wCampo(tcDe2, 'R06', 'vPIS     ', 01, 15, 1, CFe.Det[i].Imposto.PISST.vPIS, DSC_VPIS);
    Gerador.wGrupo('/PISST');
  end;
end;

procedure TCFeW.GerarDetImpostoCOFINS(const i: integer);
begin
  Gerador.wGrupo('COFINS', 'S01');
  if CFe.Emit.cRegTrib = RTSimplesNacional then
  begin
    Gerador.wGrupo('COFINSSN', 'S05');
    Gerador.wCampo(tcStr, 'S07', 'CST      ', 02, 02, 1, CSTCOFINSToStr(CFe.Det[i].Imposto.COFINS.CST), DSC_CST);
    Gerador.wGrupo('/COFINSSN');
  end
  else if CFe.Det[i].Imposto.COFINS.CST in [cof01, cof02] then
  begin
    Gerador.wGrupo('COFINSAliq', 'S02');
    Gerador.wCampo(tcStr, 'S06', 'CST      ', 02, 02, 1, CSTCOFINSTOStr(CFe.Det[i].Imposto.COFINS.CST), DSC_CST);
    Gerador.wCampo(tcDe2, 'S07', 'vBC      ', 01, 15, 1, CFe.Det[i].Imposto.COFINS.vBC, DSC_VBC);
    Gerador.wCampo(tcDe2, 'S08', 'pCOFINS  ', 01, 05, 1, CFe.Det[i].Imposto.COFINS.pCOFINS, DSC_PCOFINS);
    Gerador.wCampo(tcDe2, 'S11', 'vCOFINS  ', 01, 15, 1, CFe.Det[i].Imposto.COFINS.vCOFINS, DSC_VCOFINS);
    Gerador.wGrupo('/COFINSAliq');
  end
  else if CFe.Det[i].Imposto.COFINS.CST = cof03 then
  begin
    Gerador.wGrupo('COFINSQtde', 'S03');
    Gerador.wCampo(tcStr, 'S06', 'CST      ', 02, 02, 1, CSTCOFINSTOStr(CFe.Det[i].Imposto.COFINS.CST), DSC_CST);
    Gerador.wCampo(tcDe4, 'S09', 'qBCProd  ', 01, 16, 1, CFe.Det[i].Imposto.COFINS.qBCProd, DSC_QBCPROD);
    Gerador.wCampo(tcDe4, 'S10', 'vAliqProd', 01, 15, 1, CFe.Det[i].Imposto.COFINS.vAliqProd, DSC_VALIQPROD);
    Gerador.wCampo(tcDe2, 'S11', 'vCOFINS  ', 01, 15, 1, CFe.Det[i].Imposto.COFINS.vCOFINS, DSC_VCOFINS);
    Gerador.wGrupo('/COFINSQtde');
  end
  else if CFe.Det[i].Imposto.COFINS.CST in [cof04, cof06, cof07, cof08, cof09] then
  begin
    Gerador.wGrupo('COFINSNT', 'S04');
    Gerador.wCampo(tcStr, 'S06', 'CST      ', 02, 02, 1, CSTCOFINSTOStr(CFe.Det[i].Imposto.COFINS.CST), DSC_CST);
    Gerador.wGrupo('/COFINSNT');
  end
  else if CFe.Det[i].Imposto.COFINS.CST in [cof49, cof50, cof51, cof52, cof53, cof54, cof55, cof56, cof60, cof61, cof62, cof63, cof64, cof65, cof66, cof67, cof70, cof71, cof72, cof73, cof74, cof75, cof98, cof99] then
  begin

    if (CFe.Det[i].Imposto.COFINS.vBC + CFe.Det[i].Imposto.COFINS.pCOFINS > 0) and (CFe.Det[i].Imposto.COFINS.qBCProd + CFe.Det[i].Imposto.COFINS.vAliqProd > 0) then
      Gerador.wAlerta('S05', 'COFINSOutr', DSC_PISOUTR, 'As TAG <vBC> e <pCOFINS> n�o podem ser informadas em conjunto com as TAG <qBCProd> e <vAliqProd>');

    if (CFe.Det[i].Imposto.COFINS.qBCProd + CFe.Det[i].Imposto.COFINS.vAliqProd > 0) then
    begin
      Gerador.wGrupo('COFINSOutr', 'S05');
      Gerador.wCampo(tcStr, 'S06', 'CST      ', 02, 02, 1, CSTCOFINSTOStr(CFe.Det[i].Imposto.COFINS.CST), DSC_CST);
      Gerador.wCampo(tcDe4, 'S09', 'qBCProd  ', 01, 16, 1, CFe.Det[i].Imposto.COFINS.qBCProd, DSC_QBCPROD);
      Gerador.wCampo(tcDe4, 'S10', 'vAliqProd', 01, 15, 1, CFe.Det[i].Imposto.COFINS.vAliqProd, DSC_VALIQPROD);
      Gerador.wCampo(tcDe2, 'S11', 'vCOFINS  ', 01, 15, 1, CFe.Det[i].Imposto.COFINS.vCOFINS, DSC_VCOFINS);
      Gerador.wGrupo('/COFINSOutr');
    end
    else
    begin
      Gerador.wGrupo('COFINSOutr', 'S05');
      Gerador.wCampo(tcStr, 'S06', 'CST      ', 02, 02, 1, CSTCOFINSTOStr(CFe.Det[i].Imposto.COFINS.CST), DSC_CST);
      Gerador.wCampo(tcDe2, 'S07', 'vBC      ', 01, 15, 1, CFe.Det[i].Imposto.COFINS.vBC, DSC_VBC);
      Gerador.wCampo(tcDe2, 'S08', 'pCOFINS  ', 01, 05, 1, CFe.Det[i].Imposto.COFINS.pCOFINS, DSC_PCOFINS);
      Gerador.wCampo(tcDe2, 'S11', 'vCOFINS  ', 01, 15, 1, CFe.Det[i].Imposto.COFINS.vCOFINS, DSC_VCOFINS);
      Gerador.wGrupo('/COFINSOutr');
    end;
  end;
  Gerador.wGrupo('/COFINS');
end;

procedure TCFeW.GerarDetImpostoCOFINSST(const i: integer);
begin
  if (CFe.Det[i].Imposto.COFINSST.vBC > 0) or
     (CFe.Det[i].Imposto.COFINSST.pCOFINS > 0) or
     (CFe.Det[i].Imposto.COFINSST.qBCProd > 0) or
     (CFe.Det[i].Imposto.COFINSST.vAliqProd > 0) or
     (CFe.Det[i].Imposto.COFINSST.vCOFINS > 0) then
  begin
    Gerador.wGrupo('COFINSST', 'T01');
    if (CFe.Det[i].Imposto.COFINSST.qBCProd + CFe.Det[i].Imposto.COFINSST.vAliqProd > 0) then
     begin
       Gerador.wCampo(tcDe4, 'T04', 'qBCProd    ', 01, 16, 1, CFe.Det[i].Imposto.COFINSST.qBCProd, DSC_QBCPROD);
       Gerador.wCampo(tcDe4, 'T05', 'vAliqProd  ', 01, 15, 1, CFe.Det[i].Imposto.COFINSST.vAliqProd, DSC_VALIQPROD);
     end
    else
     begin
       Gerador.wCampo(tcDe2, 'T02', 'vBC        ', 01, 15, 1, CFe.Det[i].Imposto.COFINSST.vBC, DSC_VBC);
       Gerador.wCampo(tcDe2, 'T03', 'pCOFINS    ', 01, 05, 1, CFe.Det[i].Imposto.COFINSST.pCOFINS, DSC_PCOFINS);
     end;
    Gerador.wCampo(tcDe2, 'T06', 'vCOFINS    ', 01, 15, 1, CFe.Det[i].Imposto.COFINSST.vCOFINS, DSC_VCOFINS);
    Gerador.wGrupo('/COFINSST');
  end;
end;

procedure TCFeW.GerarDetImpostoISSQN(const i: integer);
begin
  if (CFe.Det[i].Imposto.ISSQN.vDeducISSQN > 0) or
    (CFe.Det[i].Imposto.ISSQN.vBC > 0) or
    (CFe.Det[i].Imposto.ISSQN.vAliq > 0) or
    (CFe.Det[i].Imposto.ISSQN.vISSQN > 0) then
  begin
    Gerador.wGrupo('ISSQN', 'U01');
    Gerador.wCampo(tcDe2, 'U02', 'vDeducISSQN', 01, 15, 1, CFe.Det[i].Imposto.ISSQN.vDeducISSQN, DSC_VDEDUCISS);
    Gerador.wCampo(tcDe2, 'U03', 'vBC        ', 01, 05, 1, CFe.Det[i].Imposto.ISSQN.vBC, DSC_VBCISS);
    Gerador.wCampo(tcDe2, 'U04', 'vAliq      ', 01, 15, 1, CFe.Det[i].Imposto.ISSQN.vAliq, DSC_VALIQ);
    Gerador.wCampo(tcDe2, 'U05', 'vISSQN     ', 01, 15, 1, CFe.Det[i].Imposto.ISSQN.vISSQN, DSC_VISSQN);
    Gerador.wCampo(tcInt, 'U06', 'cMunFG     ', 07, 07, 0, CFe.Det[i].Imposto.ISSQN.cMunFG, DSC_CMUNFG);
    if not ValidarMunicipio(CFe.Det[i].Imposto.ISSQN.cMunFG) then
       Gerador.wAlerta('U06', 'cMunFG', DSC_CMUNFG, ERR_MSG_INVALIDO);
    Gerador.wCampo(tcInt, 'U07', 'cListServ  ', 05, 05, 0, CFe.Det[i].Imposto.ISSQN.cListServ, DSC_CLISTSERV);
    if (FOpcoes.ValidarListaServicos) and (CFe.Det[i].Imposto.ISSQN.cListServ <> 0) then
      if not ValidarCListServ(CFe.Det[i].Imposto.ISSQN.cListServ) then
         Gerador.wAlerta('U07', 'cListServ', DSC_CLISTSERV, ERR_MSG_INVALIDO);
    Gerador.wCampo(tcStr, 'U08', 'cServTribMun', 20, 20, 0, CFe.Det[i].Imposto.ISSQN.cServTribMun, DSC_CSERVTRIBMUN);
    Gerador.wCampo(tcInt, 'U09', 'cNatOp      ', 02, 02, 1, CFe.Det[i].Imposto.ISSQN.cNatOp, DSC_CNATOP);
    Gerador.wCampo(tcInt, 'U10', 'indIncFisc  ', 01, 01, 1, CFe.Det[i].Imposto.ISSQN.indIncFisc, DSC_INDINCFISC);
    Gerador.wGrupo('/ISSQN');
  end;
end;

procedure TCFeW.GerarTotal;
begin
  Gerador.wGrupo('total', 'W01');
  (**)GerarTotalICMSTotal;
  Gerador.wCampo(tcDe2, 'W11', 'vCFe', 01, 15, 1, CFe.Total.vCFe, DSC_VCFE);
  (**)GerarTotalISSQNtot;
  (**)GerarDescAcrEntr;
  Gerador.wCampo(tcDe2, 'W22', 'vCFeLei12741', 01, 15, 1, CFe.Total.vCFeLei12741, DSC_VCFELEI12741);  
  Gerador.wGrupo('/total');
end;

procedure TCFeW.GerarTotalICMSTotal;
begin
  if (CFe.Total.ICMSTot.vICMS > 0) or
     (CFe.Total.ICMSTot.vProd > 0) or
     (CFe.Total.ICMSTot.vDesc > 0) or
     (CFe.Total.ICMSTot.vPIS > 0) or
     (CFe.Total.ICMSTot.vCOFINS > 0) or
     (CFe.Total.ICMSTot.vPISST > 0) or
     (CFe.Total.ICMSTot.vCOFINSST > 0) or
     (CFe.Total.ICMSTot.vOutro > 0) then
  begin
     Gerador.wGrupo('ICMSTot', 'W02');
     Gerador.wCampo(tcDe2, 'W03', 'vICMS     ', 01, 15, 1, CFe.Total.ICMSTot.vICMS, DSC_VICMS);
     Gerador.wCampo(tcDe2, 'W04', 'vProd     ', 01, 15, 1, CFe.Total.ICMSTot.vProd, DSC_VPROD);
     Gerador.wCampo(tcDe2, 'W05', 'vDesc     ', 01, 15, 1, CFe.Total.ICMSTot.vDesc, DSC_VDESC);
     Gerador.wCampo(tcDe2, 'W06', 'vPIS      ', 01, 15, 1, CFe.Total.ICMSTot.vPIS, DSC_VPIS);
     Gerador.wCampo(tcDe2, 'W07', 'vCOFINS   ', 01, 15, 1, CFe.Total.ICMSTot.vCOFINS, DSC_VCOFINS);
     Gerador.wCampo(tcDe2, 'W08', 'vPISST    ', 01, 15, 1, CFe.Total.ICMSTot.vPISST, DSC_VPISST);
     Gerador.wCampo(tcDe2, 'W09', 'vCOFINSST ', 01, 15, 1, CFe.Total.ICMSTot.vCOFINSST, DSC_VCOFINSST);
     Gerador.wCampo(tcDe2, 'W10', 'vOutro    ', 01, 15, 1, CFe.Total.ICMSTot.vOutro, DSC_VOUTRO);
     Gerador.wGrupo('/ICMSTot');
  end;
end;

procedure TCFeW.GerarTotalISSQNtot;
begin
  if (CFe.Total.ISSQNtot.vBC > 0) or
     (CFe.Total.ISSQNtot.vISS > 0) or
     (CFe.Total.ISSQNtot.vPIS > 0) or
     (CFe.Total.ISSQNtot.vCOFINS > 0) or
     (CFe.Total.ISSQNtot.vPISST > 0) or
     (CFe.Total.ISSQNtot.vCOFINSST > 0) then
  begin
    Gerador.wGrupo('ISSQNtot', 'W12');
    Gerador.wCampo(tcDe2, 'W13', 'vBC       ', 01, 15, 1, CFe.Total.ISSQNtot.vBC, DSC_VBC);
    Gerador.wCampo(tcDe2, 'W14', 'vISS      ', 01, 15, 1, CFe.Total.ISSQNtot.vISS, DSC_VISS);
    Gerador.wCampo(tcDe2, 'W15', 'vPIS      ', 01, 15, 1, CFe.Total.ISSQNtot.vPIS, DSC_VPIS);
    Gerador.wCampo(tcDe2, 'W16', 'vCOFINS   ', 01, 15, 1, CFe.Total.ISSQNtot.vCOFINS, DSC_VCOFINS);
    Gerador.wCampo(tcDe2, 'W17', 'vPISST    ', 01, 15, 1, CFe.Total.ISSQNtot.vPISST, DSC_VPISST);
    Gerador.wCampo(tcDe2, 'W18', 'vCOFINSST ', 01, 15, 1, CFe.Total.ISSQNtot.vCOFINSST, DSC_VCOFINSST);
    Gerador.wGrupo('/ISSQNtot');
  end;
end;

procedure TCFeW.GerarDescAcrEntr;
begin
 if (CFe.Total.DescAcrEntr.vDescSubtot > 0) or
    (CFe.Total.DescAcrEntr.vAcresSubtot > 0) then
  begin
     Gerador.wGrupo('DescAcrEntr', 'W19');
     Gerador.wCampo(tcDe2, 'W20', 'vDescSubtot ', 01, 15, 0, CFe.Total.DescAcrEntr.vDescSubtot, DSC_VDESCSUBTOT);
     Gerador.wCampo(tcDe2, 'W21', 'vAcresSubtot ', 01, 15, 0, CFe.Total.DescAcrEntr.vAcresSubtot, DSC_VACRESSUBTOT);
     Gerador.wGrupo('DescAcrEntr', 'W19');
  end
end;

procedure TCFeW.GerarPagto;
var
  i: integer;
begin
  if CFe.Pagto.Count > 10 then
    Gerador.wAlerta('WA02', 'MP', DSC_MP, ERR_MSG_MAIOR_MAXIMO + '10');
  Gerador.wGrupo('pgto', 'WA01');
  for i := 0 to CFe.Pagto.Count - 1 do
  begin
    Gerador.wGrupo('MP', 'WA02');
    Gerador.wCampo(tcStr, 'WA03', 'cMP ', 02, 02, 0, CodigoMPToStr(CFe.Pagto[i].cMP), DSC_CMP);
    Gerador.wCampo(tcDe2, 'WA04', 'vMP ', 01, 15, 1, CFe.Pagto[i].vMP, DSC_VMP);
    Gerador.wCampo(tcInt, 'WA05', 'cAdmC', 03, 03, 0, CFe.Pagto[i].cAdmC, DSC_CADMC);
    Gerador.wGrupo('/MP');
  end;
  Gerador.wCampo(tcDe2, 'WA06', 'vTroco ', 01, 15, 1, CFe.Pagto.vTroco, DSC_VTROCO);
  Gerador.wGrupo('/pgto');
end;

procedure TCFeW.GerarInfAdic;
begin
  if (trim(CFe.InfAdic.infCpl) <> EmptyStr) or
    (CFe.InfAdic.obsFisco.Count > 0) then
  begin
    Gerador.wGrupo('infAdic', 'Z01');
    Gerador.wCampo(tcStr, 'Z02', 'infCpl    ', 01, 5000, 0, CFe.InfAdic.infCpl, DSC_INFCPL);
    (**)GerarInfAdicObsFisco;
    Gerador.wGrupo('/infAdic');
  end;
end;

procedure TCFeW.GerarInfAdicObsFisco;
var
  i: integer;
begin
  if CFe.InfAdic.obsFisco.Count > 10 then
    Gerador.wAlerta('Z03', 'obsFisco', DSC_OBSFISCO, ERR_MSG_MAIOR_MAXIMO + '10');
  for i := 0 to CFe.InfAdic.obsFisco.Count - 1 do
  begin
    Gerador.wGrupo('obsFisco xCampo="' + trim(CFe.InfAdic.obsFisco[i].xCampo) + '"', 'Z04');
    if length(trim(CFe.InfAdic.obsFisco[i].xCampo)) > 20 then
      Gerador.wAlerta('ZO4', 'xCampo', DSC_XCAMPO, ERR_MSG_MAIOR);
    if length(trim(CFe.InfAdic.obsFisco[i].xCampo)) = 0 then
      Gerador.wAlerta('ZO4', 'xCampo', DSC_XCAMPO, ERR_MSG_VAZIO);
    Gerador.wCampo(tcStr, 'Z05', 'xTexto', 01, 60, 1, CFe.InfAdic.obsFisco[i].xTexto, DSC_XTEXTO);
    Gerador.wGrupo('/obsFisco');
  end;
end;

function TCFeW.GerarXml: boolean;
var
  Gerar: boolean;
begin
  Gerador.LayoutArquivoTXT.Clear;

  Gerador.ArquivoFormatoXML := '';
  Gerador.ArquivoFormatoTXT := '';

  Gerador.wGrupo('CFe ' + NAME_SPACE_CFE);
  Gerador.wGrupo('infCFe Id="' + CFe.infCFe.ID + '" '
                 + V0_02 +
                 ' versaoDadosEnt="'+ StringReplace(FormatFloat('0.00',CFe.infCFe.versaoDadosEnt),',','.',[rfReplaceAll]) +
                 '" versaoSB="'+IntToStrZero(CFe.infCFe.versaoSB,6) + '"' );
  (**)GerarInfCFe;
  Gerador.wGrupo('/infCFe');
  //
  if FOpcoes.GerarTagAssinatura <> taNunca then
  begin
    Gerar := true;
    if FOpcoes.GerarTagAssinatura = taSomenteSeAssinada then
      Gerar := ((CFe.signature.DigestValue <> '') and (CFe.signature.SignatureValue <> '') and (CFe.signature.X509Certificate <> ''));
    if FOpcoes.GerarTagAssinatura = taSomenteParaNaoAssinada then
      Gerar := ((CFe.signature.DigestValue = '') and (CFe.signature.SignatureValue = '') and (CFe.signature.X509Certificate = ''));
    if Gerar then
    begin
      FCFe.signature.URI := somenteNumeros(CFe.infCFe.ID);
      FCFe.signature.Gerador.Opcoes.IdentarXML := Gerador.Opcoes.IdentarXML;
      FCFe.signature.GerarXML;
      Gerador.ArquivoFormatoXML := Gerador.ArquivoFormatoXML + FCFe.signature.Gerador.ArquivoFormatoXML;
    end;
  end;
  Gerador.wGrupo('/CFe');

  Result := (Gerador.ListaDeAlertas.Count = 0);
end;

function TCFeW.ObterNomeArquivo: string;
begin
  Result := SomenteNumeros(CFe.infCFe.ID) + '-cfe.xml';
end;

end.
 
