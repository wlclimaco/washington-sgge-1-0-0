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

unit pcnCFeR;

interface uses

  SysUtils, Classes,
{$IFNDEF VER130}
  Variants,
{$ENDIF}
  pcnAuxiliar, pcnConversao, pcnLeitor, pcnCFe;

type

  TCFeR = class(TPersistent)
  private
    FLeitor: TLeitor;
    FCFe: TCFe;
  public
    constructor Create(AOwner: TCFe);
    destructor Destroy; override;
    function LerXml: boolean;
  published
    property Leitor: TLeitor read FLeitor write FLeitor;
    property CFe: TCFe read FCFe write FCFe;
  end;

  ////////////////////////////////////////////////////////////////////////////////

implementation

uses ACBrConsts;

{ TCFeR }

constructor TCFeR.Create(AOwner: TCFe);
begin
  FLeitor := TLeitor.Create;
  FCFe := AOwner;
end;

destructor TCFeR.Destroy;
begin
  FLeitor.Free;
  inherited Destroy;
end;

function TCFeR.LerXml: boolean;
var
  ok: boolean;
  i, j, nItem: integer;
  Arquivo, Itens, ItensTemp, NumItem: AnsiString;
begin
  Result := False;

  i := 0;
  i := RetornarPosEx('Id=', Leitor.Arquivo, i + 6);
  if i = 0 then
    raise Exception.Create('N�o encontrei inicio do URI: Id=');
  i := RetornarPosEx('"', Leitor.Arquivo, i + 2);
  if i = 0 then
    raise Exception.Create('N�o encontrei inicio do URI: aspas inicial');
  j := RetornarPosEx('"', Leitor.Arquivo, i + 1);
  if j = 0 then
    raise Exception.Create('N�o encontrei inicio do URI: aspas final');

  CFe.infCFe.ID := copy(Leitor.Arquivo, I+1, J - (I+1));
  CFe.infCFe.ID := StringReplace( UpperCase(CFe.infCFe.ID), 'CFE', '', [rfReplaceAll] ) ;

  (* Grupo da TAG <ide> *******************************************************)
  if Leitor.rExtrai(1, 'ide') <> '' then
  begin
    ok := False;
    (*B02*) CFe.ide.cUF := Leitor.rCampo(tcInt, 'cUF');
    (*B03*) CFe.ide.cNF := Leitor.rCampo(tcInt, 'cNF');
    (*B04*) CFe.ide.modelo := Leitor.rCampo(tcInt, 'mod');
    (*B05*) CFe.ide.nserieSAT := Leitor.rCampo(tcInt, 'nserieSAT');
    (*B06*) CFe.ide.nCFe := Leitor.rCampo(tcInt, 'nCFe');
    (*B07*) CFe.ide.dEmi := Leitor.rCampo(tcDatCFe, 'dEmi');
    (*B08*) CFe.ide.hEmi := Leitor.rCampo(tcHorCFe, 'hEmi');
    (*B09*) CFe.Ide.cDV := Leitor.rCampo(tcInt, 'cDV');
    (*B10*) CFe.Ide.tpAmb := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
    (*B11*) CFe.Ide.CNPJ := Leitor.rCampo(tcEsp, 'CNPJ');
    (*B12*) CFe.Ide.signAC := Leitor.rCampo(tcStr, 'signAC');
    (*B13*) CFe.Ide.assinaturaQRCODE := Leitor.rCampo(tcStr, 'assinaturaQRCODE');
    (*B14*) CFe.ide.numeroCaixa := Leitor.rCampo(tcInt, 'numeroCaixa');
  end;

  (* Grupo da TAG <emit> ******************************************************)
  if Leitor.rExtrai(1, 'emit') <> '' then
  begin
    (*C02/C02a*)CFe.Emit.CNPJCPF := Leitor.rCampoCNPJCPF;
    (*C03*)CFe.Emit.xNome := Leitor.rCampo(tcStr, 'xNome');
    (*C04*)CFe.Emit.xFant := Leitor.rCampo(tcStr, 'xFant');
    (*C12*)CFe.Emit.IE := Leitor.rCampo(tcStr, 'IE');
    (*C13*)CFe.Emit.IM := Leitor.rCampo(tcStr, 'IM');
    (*C14*)CFe.Emit.cRegTrib := StrToRegTrib(ok, Leitor.rCampo(tcStr, 'cRegTrib'));
    (*C15*)CFe.Emit.cRegTribISSQN := StrToRegTribISSQN(ok, Leitor.rCampo(tcStr, 'cRegTribISSQN'));
    (*C16*)CFe.Emit.indRatISSQN := StrToindRatISSQN(ok, Leitor.rCampo(tcStr, 'indRatISSQN'));
  end;

  (* Grupo da TAG <emit><EnderEmit> *)
  if Leitor.rExtrai(1, 'emit') <> '' then
  begin
    if Leitor.rExtrai(2, 'enderEmit') <> '' then
    begin
      (*C06*)CFe.Emit.enderEmit.xLgr := Leitor.rCampo(tcStr, 'xLgr');
      (*C07*)CFe.Emit.enderEmit.nro := Leitor.rCampo(tcStr, 'nro');
      (*C08*)CFe.Emit.enderEmit.xCpl := Leitor.rCampo(tcStr, 'xCpl');
      (*C09*)CFe.Emit.enderEmit.xBairro := Leitor.rCampo(tcStr, 'xBairro');
      (*C10*)CFe.Emit.enderEmit.xMun := Leitor.rCampo(tcStr, 'xMun');
      (*C11*)CFe.Emit.enderEmit.CEP := Leitor.rCampo(tcInt, 'CEP');
    end;
  end;

  (* Grupo da TAG <dest> ******************************************************)
  if Leitor.rExtrai(1, 'dest') <> '' then
  begin
    (*E02/E03*)CFe.Dest.CNPJCPF := Leitor.rCampoCNPJCPF;
    (*E04*)CFe.Dest.xNome := Leitor.rCampo(tcStr, 'xNome');
  end;

  (* Grupo da TAG <entrega> ***************************************************)
  if Leitor.rExtrai(1, 'entrega') <> '' then
  begin
    (*G02*)CFe.Entrega.xLgr := Leitor.rCampo(tcStr, 'xLgr');
    (*G03*)CFe.Entrega.nro := Leitor.rCampo(tcStr, 'nro');
    (*G04*)CFe.Entrega.xCpl := Leitor.rCampo(tcStr, 'xCpl');
    (*G05*)CFe.Entrega.xBairro := Leitor.rCampo(tcStr, 'xBairro');
    (*G06*)CFe.Entrega.xMun := Leitor.rCampo(tcStr, 'xMun');
    (*G07*)CFe.Entrega.UF := Leitor.rCampo(tcStr, 'UF');
  end;

//  ****** DET
  (* Grupo da TAG <det> *******************************************************)
  i := 0;
  Arquivo := Leitor.Arquivo;

  Itens := copy(
    Arquivo,
    Pos('<det nItem=', Arquivo),
    Pos('<total', Arquivo) - Pos('<det nItem=',Arquivo)
  );

  ItensTemp := copy(
    Itens,
    Pos('<det nItem=', Itens),
    (Pos('</det>', Itens) + 6) - Pos('<det nItem=', Itens)
  );  

  while pos('<det nItem=',ItensTemp) <> 0 do
  begin
    Leitor.Arquivo := 'Item '+ItensTemp;

    NumItem := copy(ItensTemp,Pos('nItem=',ItensTemp)+7,Pos('"',ItensTemp));
    NumItem := copy(NumItem,1,Pos('"',NumItem)-1);
    nItem := StrToInt(NumItem);
    Itens     := StringReplace(Itens, ItensTemp, '',[]);
    ItensTemp := copy(Itens,Pos('<det nItem=',Itens),(Pos('</det>',Itens)+6)-Pos('<det nItem=',Itens));

    Leitor.rExtrai(1, 'det nItem="' + IntToStr(nItem) + '"', 'det');
    CFe.Det.Add;
    (*   *)CFe.Det[i].nItem := nItem;
    (*V01*)CFe.Det[i].infAdProd := Leitor.rCampo(tcStr, 'infAdProd');

    (* Grupo da TAG <det><prod> *)
    Leitor.rExtrai(2, 'prod');
    (*I02*)CFe.Det[i].Prod.cProd := Leitor.rCampo(tcStr, 'cProd');
    (*I03*)CFe.Det[i].Prod.cEAN := Leitor.rCampo(tcStr, 'cEAN');
    (*I04*)CFe.Det[i].Prod.xProd := Leitor.rCampo(tcStr, 'xProd');
    (*I05*)CFe.Det[i].Prod.NCM := Leitor.rCampo(tcStr, 'NCM');
    (*I06*)CFe.Det[i].Prod.CFOP := Leitor.rCampo(tcEsp, 'CFOP');
    (*I07*)CFe.Det[i].Prod.uCom := Leitor.rCampo(tcStr, 'uCom');
    (*I08*)CFe.Det[i].Prod.qCom := Leitor.rCampo(tcDe4, 'qCom');
    (*I09*)CFe.Det[i].Prod.vUnCom := Leitor.rCampo(tcDe10, 'vUnCom');
    (*I10*)CFe.Det[i].Prod.vProd := Leitor.rCampo(tcDe2, 'vProd');
    (*I11*)CFe.Det[i].Prod.indRegra := StrToindRegra(ok,Leitor.rCampo(tcStr, 'indRegra'));
    (*I12*)CFe.Det[i].Prod.vDesc := Leitor.rCampo(tcDe2, 'vDesc');
    (*I13*)CFe.Det[i].Prod.vOutro := Leitor.rCampo(tcDe2, 'vOutro');
    (*I14*)CFe.Det[i].Prod.vItem := Leitor.rCampo(tcDe2, 'vItem');
    (*I15*)CFe.Det[i].Prod.vRatDesc := Leitor.rCampo(tcDe2, 'vRatDesc');
    (*I16*)CFe.Det[i].Prod.vRatAcr := Leitor.rCampo(tcDe2, 'vRatAcr');

    (* Grupo da TAG <det><prod><obsFiscoDet> *)
    j := 0;
    while Leitor.rExtrai(3, 'obsFiscoDet', '', j + 1) <> '' do
    begin
      CFe.Det[i].Prod.obsFiscoDet.Add;
      (*I18*)CFe.Det[i].Prod.obsFiscoDet[j].xCampoDet := Leitor.rAtributo('xCampoDet');
      (*I19*)CFe.Det[i].Prod.obsFiscoDet[j].xTextoDet := Leitor.rCampo(tcStr, 'xTextoDet');
    end;

    (* Grupo da TAG <det><imposto> ********************************************)
    Leitor.rExtrai(2, 'imposto');
    (*M02*)CFe.Det[i].Imposto.vItem12741 := Leitor.rCampo(tcDe2, 'vItem12741');
    
    if Leitor.rExtrai(3, 'ICMS') <> '' then
    begin
      (*N06*)CFe.Det[i].Imposto.ICMS.orig         := StrToOrig(ok, Leitor.rCampo(tcStr, 'Orig'));
      (*N07*)CFe.Det[i].Imposto.ICMS.CST          := StrToCSTICMS(ok, Leitor.rCampo(tcStr, 'CST'));
      (*N10*)CFe.Det[i].Imposto.ICMS.CSOSN       := StrToCSOSNIcms( ok,Leitor.rCampo(tcInt, 'CSOSN'));
      (*N08*)CFe.Det[i].Imposto.ICMS.pICMS        := Leitor.rCampo(tcDe2, 'pICMS');
      (*N09*)CFe.Det[i].Imposto.ICMS.vICMS        := Leitor.rCampo(tcDe2, 'vICMS');
    end;

    if Leitor.rExtrai(3, 'PIS') <> '' then
    begin
      (*Q07*)CFe.Det[i].Imposto.PIS.CST := StrToCSTPIS(ok, Leitor.rCampo(tcStr, 'CST'));
      (*Q08*)CFe.Det[i].Imposto.PIS.vBC := Leitor.rCampo(tcDe2, 'vBC');
      (*Q09*)CFe.Det[i].Imposto.PIS.pPIS := Leitor.rCampo(tcDe2, 'pPIS');
      (*Q10*)CFe.Det[i].Imposto.PIS.vPIS := Leitor.rCampo(tcDe2, 'vPIS');
      (*Q11*)CFe.Det[i].Imposto.PIS.qBCProd := Leitor.rCampo(tcDe4, 'qBCProd');
      (*Q12*)CFe.Det[i].Imposto.PIS.vAliqProd := Leitor.rCampo(tcDe4, 'vAliqProd');
    end;
    if Leitor.rExtrai(3, 'PISST') <> '' then
    begin
      (*R02*)CFe.Det[i].Imposto.PISST.vBc := Leitor.rCampo(tcDe2, 'vBC');
      (*R03*)CFe.Det[i].Imposto.PISST.pPis := Leitor.rCampo(tcDe2, 'pPIS');
      (*R04*)CFe.Det[i].Imposto.PISST.qBCProd := Leitor.rCampo(tcDe4, 'qBCProd');
      (*R05*)CFe.Det[i].Imposto.PISST.vAliqProd := Leitor.rCampo(tcDe4, 'vAliqProd');
      (*R06*)CFe.Det[i].Imposto.PISST.vPIS := Leitor.rCampo(tcDe2, 'vPIS');
    end;
    if Leitor.rExtrai(3, 'COFINS') <> '' then
    begin
      (*S07*)CFe.Det[i].Imposto.COFINS.CST := StrToCSTCOFINS(ok, Leitor.rCampo(tcStr, 'CST'));
      (*S08*)CFe.Det[i].Imposto.COFINS.vBC := Leitor.rCampo(tcDe2, 'vBC');
      (*S09*)CFe.Det[i].Imposto.COFINS.pCOFINS := Leitor.rCampo(tcDe2, 'pCOFINS');
      (*S11*)CFe.Det[i].Imposto.COFINS.qBCProd := Leitor.rCampo(tcDe4, 'qBCProd');
      (*S12*)CFe.Det[i].Imposto.COFINS.vAliqProd := Leitor.rCampo(tcDe4, 'vAliqProd');
      (*S10*)CFe.Det[i].Imposto.COFINS.vCOFINS := Leitor.rCampo(tcDe2, 'vCOFINS');
    end;
    if Leitor.rExtrai(3, 'COFINSST') <> '' then
    begin
      (*T02*)CFe.Det[i].Imposto.COFINSST.vBC := Leitor.rCampo(tcDe2, 'vBC');
      (*T03*)CFe.Det[i].Imposto.COFINSST.pCOFINS := Leitor.rCampo(tcDe2, 'pCOFINS');
      (*T04*)CFe.Det[i].Imposto.COFINSST.qBCProd := Leitor.rCampo(tcDe4, 'qBCProd');
      (*T05*)CFe.Det[i].Imposto.COFINSST.vAliqProd := Leitor.rCampo(tcDe4, 'vAliqProd');
      (*T06*)CFe.Det[i].Imposto.COFINSST.vCOFINS := Leitor.rCampo(tcDe2, 'vCOFINS');
    end;

    if Leitor.rExtrai(3, 'ISSQN') <> '' then
    begin
      (*U02*)CFe.Det[i].Imposto.ISSQN.vDeducISSQN  := Leitor.rCampo(tcDe2, 'vDeducISSQN');
      (*U03*)CFe.Det[i].Imposto.ISSQN.vBC       := Leitor.rCampo(tcDe2, 'vBC');
      (*U04*)CFe.Det[i].Imposto.ISSQN.vAliq     := Leitor.rCampo(tcDe2, 'vAliq');
      (*U05*)CFe.Det[i].Imposto.ISSQN.vISSQN    := Leitor.rCampo(tcDe2, 'vISSQN');
      (*U06*)CFe.Det[i].Imposto.ISSQN.cMunFG    := Leitor.rCampo(tcInt, 'cMunFG');
      (*U07*)CFe.Det[i].Imposto.ISSQN.cListServ := Leitor.rCampo(tcInt, 'cListServ');
      (*U08*)CFe.Det[i].Imposto.ISSQN.cServTribMun := Leitor.rCampo(tcStr, 'cServTribMun') ;
      (*U09*)CFe.Det[i].Imposto.ISSQN.cNatOp    := Leitor.rCampo(tcInt, 'cNatOp');
      (*U10*)CFe.Det[i].Imposto.ISSQN.indIncFisc    := Leitor.rCampo(tcInt, 'indIncFisc');
    end;

    inc(i);
  end;

  Leitor.Arquivo := Arquivo;

  (* Grupo da TAG <total> *****************************************************)
  if Leitor.rExtrai(1, 'total') <> '' then
  begin
    (*W11*)CFe.Total.vCFe := Leitor.rCampo(tcDe2, 'vCFe');
    (*W22*)CFe.Total.vCFeLei12741 := Leitor.rCampo(tcDe2, 'vCFeLei12741');    
    if Leitor.rExtrai(2, 'ICMSTot') <> '' then
    begin
      (*W03*)CFe.Total.ICMSTot.vICMS := Leitor.rCampo(tcDe2, 'vICMS');
      (*W04*)CFe.Total.ICMSTot.vProd := Leitor.rCampo(tcDe2, 'vProd');
      (*W05*)CFe.Total.ICMSTot.vDesc := Leitor.rCampo(tcDe2, 'vDesc');
      (*W06*)CFe.Total.ICMSTot.vPIS := Leitor.rCampo(tcDe2, 'vPIS');
      (*W07*)CFe.Total.ICMSTot.vCOFINS := Leitor.rCampo(tcDe2, 'vCOFINS');
      (*W08*)CFe.Total.ICMSTot.vPISST := Leitor.rCampo(tcDe2, 'vPISST');
      (*W09*)CFe.Total.ICMSTot.vCOFINSST := Leitor.rCampo(tcDe2, 'vCOFINSST');
      (*W10*)CFe.Total.ICMSTot.vOutro := Leitor.rCampo(tcDe2, 'vOutro');
    end;
    if Leitor.rExtrai(2, 'ISSQNtot') <> '' then
    begin
      (*W13*)CFe.Total.ISSQNtot.vBC := Leitor.rCampo(tcDe2, 'vBC');
      (*W14*)CFe.Total.ISSQNtot.vISS := Leitor.rCampo(tcDe2, 'vISS');
      (*W15*)CFe.Total.ISSQNtot.vPIS := Leitor.rCampo(tcDe2, 'vPIS');
      (*W16*)CFe.Total.ISSQNtot.vCOFINS := Leitor.rCampo(tcDe2, 'vCOFINS');
      (*W17*)CFe.Total.ISSQNtot.vPISST := Leitor.rCampo(tcDe2, 'vPISST');
      (*W18*)CFe.Total.ISSQNtot.vCOFINSST := Leitor.rCampo(tcDe2, 'vCOFINSST');
    end;
    if Leitor.rExtrai(2, 'DescAcrEntr') <> '' then
    begin
      (*W20*)CFe.Total.DescAcrEntr.vDescSubtot := Leitor.rCampo(tcDe2, 'vDescSubtot');
      (*W21*)CFe.Total.DescAcrEntr.vAcresSubtot := Leitor.rCampo(tcDe2, 'vAcresSubtot');
    end;
  end;

  (* Grupo da TAG <total> *****************************************************)
  if Leitor.rExtrai(1, 'pgto') <> '' then
  begin
    i := 0;
   (*WA06*)CFe.Pagto.vTroco := Leitor.rCampo(tcDe2, 'vTroco');
    while Leitor.rExtrai(1, 'MP', '', i + 1) <> '' do
    begin
      CFe.Pagto.Add;
      (*WA03*)CFe.Pagto[i].cMP := StrToCodigoMP(ok, Leitor.rCampo(tcStr, 'cMP'));
      (*WA04*)CFe.Pagto[i].vMP := Leitor.rCampo(tcDe2, 'vMP');
      (*WA05*)CFe.Pagto[i].cAdmC := Leitor.rCampo(tcInt, 'cAdmC');
      inc(i);
    end;
  end;

  (* Grupo da TAG <InfAdic> ***************************************************)
  if Leitor.rExtrai(1, 'infAdic') <> '' then
  begin
    (*Z02*)CFe.InfAdic.infCpl := Leitor.rCampo(tcStr, 'infCpl');
    i := 0;
    while Leitor.rExtrai(2, 'obsFisco', '', i + 1) <> '' do
    begin
      CFe.InfAdic.obsFisco.Add;
      (*Z04*)CFe.InfAdic.obsFisco[i].xCampo := Leitor.rAtributo('xCampo');
      (*Z05*)CFe.InfAdic.obsFisco[i].xTexto := Leitor.rCampo(tcStr, 'xTexto');
      inc(i)
    end;
  end;

  (* Grupo da TAG <signature> *************************************************)
  leitor.Grupo := Leitor.Arquivo;

  CFe.signature.URI := Leitor.rAtributo('Reference URI=');
  CFe.signature.DigestValue := Leitor.rCampo(tcStr, 'DigestValue');
  CFe.signature.SignatureValue := Leitor.rCampo(tcStr, 'SignatureValue');
  CFe.signature.X509Certificate := Leitor.rCampo(tcStr, 'X509Certificate');

  Result := True;
end;

end.
