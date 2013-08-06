////////////////////////////////////////////////////////////////////////////////
//                                                                            //
//              PCN - Projeto Cooperar NFe                                    //
//                                                                            //
//   Descri��o: Classes para gera��o/leitura dos arquivos xml da NFe          //
//                                                                            //
//        site: www.projetocooperar.org                                       //
//       email: projetocooperar@zipmail.com.br                                //
//       forum: http://br.groups.yahoo.com/group/projeto_cooperar_nfe/        //
//     projeto: http://code.google.com/p/projetocooperar/                     //
//         svn: http://projetocooperar.googlecode.com/svn/trunk/              //
//                                                                            //
// Coordena��o: (c) 2009 - Paulo Casagrande                                   //
//                                                                            //
//      Equipe: Vide o arquivo leiame.txt na pasta raiz do projeto            //
//                                                                            //
//      Vers�o: Vide o arquivo leiame.txt na pasta raiz do projeto            //
//                                                                            //
//     Licen�a: GNU Lesser General Public License (GNU LGPL)                  //
//                                                                            //
//              - Este programa � software livre; voc� pode redistribu�-lo    //
//              e/ou modific�-lo sob os termos da Licen�a P�blica Geral GNU,  //
//              conforme publicada pela Free Software Foundation; tanto a     //
//              vers�o 2 da Licen�a como (a seu crit�rio) qualquer vers�o     //
//              mais nova.                                                    //
//                                                                            //
//              - Este programa � distribu�do na expectativa de ser �til,     //
//              mas SEM QUALQUER GARANTIA; sem mesmo a garantia impl�cita de  //
//              COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM       //
//              PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter   //
//              mais detalhes. Voc� deve ter recebido uma c�pia da Licen�a    //
//              P�blica Geral GNU junto com este programa; se n�o, escreva    //
//              para a Free Software Foundation, Inc., 59 Temple Place,       //
//              Suite 330, Boston, MA - 02111-1307, USA ou consulte a         //
//              licen�a oficial em http://www.gnu.org/licenses/gpl.txt        //
//                                                                            //
//    Nota (1): - Esta  licen�a  n�o  concede  o  direito  de  uso  do nome   //
//              "PCN  -  Projeto  Cooperar  NFe", n�o  podendo o mesmo ser    //
//              utilizado sem previa autoriza��o.                             //
//                                                                            //
//    Nota (2): - O uso integral (ou parcial) das units do projeto esta       //
//              condicionado a manuten��o deste cabe�alho junto ao c�digo     //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////

unit pcnLayoutTXT;

interface uses

  SysUtils, Classes, pcnAuxiliar, pcnConversao;

function CarregarLayoutTXT(const versao: string): AnsiString;

const
  VERSOES_VALIDAS_LAYOUT_TXT = '|1.10|2.00|';

implementation

function CarregarLayoutTXT(const versao: string): AnsiString;
var
  Layout: AnsiString;
  procedure LoadLayout(s: string);
  begin
    Layout := Layout + UpperCase(s + #10 + #13);
  end;
begin
  if versao = '1.10' then
  begin
    LoadLayout('<B01>       NOTA FISCAL|1');
    LoadLayout('<B01>     A|1.10|^id^'); //ok
    LoadLayout('<B01>     B|cUF�|cNF�|NatOp�|indPag�|mod�|serie�|nNF�|dEmi�|dSaiEnt�|tpNF�|cMunFG�|TpImp�|TpEmis�|CDV�|TpAmb�|FinNFe�|ProcEmi�|VerProc�'); //ok
    LoadLayout('<B12a>  B13|refNFe�'); //ok
    LoadLayout('<B14>   B14|cUF�|AAMM�|CNPJ�|Mod�|serie�|nNF�'); //ok
    LoadLayout('<C01>     C|XNome�|XFant�|IE�|IEST�|IM�|CNAE�'); //ok
    LoadLayout('<C01>   C02|CNPJ�'); //ok
    LoadLayout('<C01>  C02a|CPF�'); //ok
    LoadLayout('<C05>   C05|XLgr�|Nro�|xCpl�|xBairro�|CMun�|XMun�|UF�|CEP�|CPais�|XPais�|Fone�'); //ok
    LoadLayout('<D01>     D|CNPJ�|XOrgao�|Matr�|XAgente�|Fone�|UF�|NDAR�|DEmi�|VDAR�|RepEmi�|DPag�'); //ok
    LoadLayout('<E01>     E|XNome�|IE�|ISUF�'); //ok
    LoadLayout('<E01>   E02|CNPJ�'); //ok
    LoadLayout('<E01>   E03|CPF�'); //ok
    LoadLayout('<E05>   E05|XLgr�|Nro�|XCpl�|XBairro�|CMun�|XMun�|UF�|CEP�|CPais�|XPais�|Fone�'); //ok
    LoadLayout('<F01>     F|CNPJ�|XLgr�|Nro�|XCpl�|XBairro�|CMun�|XMun�|UF�'); //ok
    LoadLayout('<G01>     G|CNPJ�|XLgr�|Nro�|XCpl�|XBairro�|CMun�|XMun�|UF�'); //ok
    LoadLayout('<H01>     H|NItem�|InfAdProd�'); //ok
    LoadLayout('<I01>     I|CProd�|CEAN�|XProd�|NCM�|EXTIPI�|Genero�|CFOP�|UCom�|QCom�|VUnCom�|VProd�|CEANTrib�|UTrib�|QTrib�|VUnTrib�|VFrete�|VSeg�|VDesc�'); //ok
    LoadLayout('<I18>   I18|NDI�|DDI�|XLocDesemb�|UFDesemb�|DDesemb�|CExportador�'); //ok
    LoadLayout('<I25>   I25|NAdicao�|NSeqAdic�|CFabricante�|VDescDI�'); //ok
    LoadLayout('<J01>     J|TpOp�|Chassi�|CCor�|XCor�|Pot�|CM3�|PesoL�|PesoB�|NSerie�|TpComb�|NMotor�|CMKG�|Dist�|RENAVAM�|AnoMod�|AnoFab�|TpPint�|TpVeic�|EspVeic�|VIN�|CondVeic�|CMod�'); //ok
    LoadLayout('<K01>     K|NLote�|QLote�|DFab�|DVal�|VPMC�'); //ok
    LoadLayout('<L00>     L|TpArma�|NSerie�|NCano�|Descr�'); //ok
    LoadLayout('<L01>   L01|CProdANP�|CODIF�|QTemp�'); //ok
    LoadLayout('<L105> L105|QBCProd�|VAliqProd�|VCIDE�'); //ok
    LoadLayout('<L109> L109|VBCICMS�|VICMS�|VBCICMSST�|VICMSST�'); //ok
    LoadLayout('<L114> L114|VBCICMSSTDest�|VICMSSTDest�'); //ok
    LoadLayout('<L117> L117|VBCICMSSTCons�|VICMSSTCons�|UFCons�'); //ok
    LoadLayout('<M01>     M'); //ok
    LoadLayout('<N01>     N'); //ok
    LoadLayout('<N02>   N02|Orig�|CST�|ModBC�|VBC�|PICMS�|VICMS�'); //ok
    LoadLayout('<N03>   N03|Orig�|CST�|ModBC�|VBC�|PICMS�|VICMS�|ModBCST�|PMVAST�|PRedBCST�|VBCST�|PICMSST�|VICMSST�'); //ok
    LoadLayout('<N04>   N04|Orig�|CST�|ModBC�|PRedBC�|VBC�|PICMS�|VICMS�'); //ok
    LoadLayout('<N05>   N05|Orig�|CST�|ModBCST�|PMVAST�|PRedBCST�|VBCST�|PICMSST�|VICMSST�'); //ok
    LoadLayout('<N06>   N06|Orig�|CST�'); //ok
    LoadLayout('<N07>   N07|Orig�|CST�|ModBC�|PRedBC�|VBC�|PICMS�|VICMS�'); //ok
    LoadLayout('<N08>   N08|Orig�|CST�|VBCST�|VICMSST�'); //ok
    LoadLayout('<N09>   N09|Orig�|CST�|ModBC�|PRedBC�|VBC�|PICMS�|VICMS�|ModBCST�|PMVAST�|PRedBCST�|VBCST�|PICMSST�|VICMSST�'); //ok
    LoadLayout('<N10>   N10|Orig�|CST�|ModBC�|VBC�|PRedBC�|PICMS�|VICMS�|ModBCST�|PMVAST�|PRedBCST�|VBCST�|PICMSST�|VICMSST�'); //ok
    LoadLayout('<O01>     O|ClEnq�|CNPJProd�|CSelo�|QSelo�|CEnq�'); //ok
    LoadLayout('<O07>   O07|CST�|VIPI�'); //ok
    LoadLayout('<O07>   O10|VBC�|PIPI�'); //ok
    LoadLayout('<O07>   O11|QUnid�|VUnid�'); //ok
    LoadLayout('<O08>   O08|CST�'); //ok
    LoadLayout('<P01>     P|VBC�|VDespAdu�|VII�|VIOF�'); //ok
    LoadLayout('<Q01>     Q'); //ok
    LoadLayout('<Q02>   Q02|CST�|VBC�|PPIS�|VPIS�'); //ok
    LoadLayout('<Q03>   Q03|CST�|QBCProd�|VAliqProd�|VPIS�'); //ok
    LoadLayout('<Q04>   Q04|CST�'); //ok
    LoadLayout('<Q05>   Q05|CST�|VPIS�'); //ok
    LoadLayout('<Q05>   Q07|VBC�|PPIS�'); //ok
    LoadLayout('<Q05>   Q10|QBCProd�|VAliqProd�'); //ok
    LoadLayout('<R01>     R|VPIS�'); //ok
    LoadLayout('<R01>   R02|VBC�|PPIS�'); //ok
    LoadLayout('<R01>   R04|QBCProd�|VAliqProd�'); //ok
    LoadLayout('<S01>     S'); //ok
    LoadLayout('<S02>   S02|CST�|VBC�|PCOFINS�|VCOFINS�'); //ok
    LoadLayout('<S03>   S03|CST�|QBCProd�|VAliqProd�|VCOFINS�'); //ok
    LoadLayout('<S04>   S04|CST�'); //ok
    LoadLayout('<S05>   S05|CST�|VCOFINS�'); //ok
    LoadLayout('<S05>   S07|VBC�|PCOFINS�'); //ok
    LoadLayout('<S05>   S09|QBCProd�|VAliqProd�'); //ok
    LoadLayout('<T01>     T|VCOFINS�'); //ok
    LoadLayout('<T01>   T02|VBC�|PCOFINS�'); //ok
    LoadLayout('<T01>   T04|QBCProd�|VAliqProd�'); //ok
    LoadLayout('<U01>     U|VBC�|VAliq�|VISSQN�|CMunFG�|CListServ�'); //ok
    LoadLayout('<W01>     W'); //ok
    LoadLayout('<W02>   W02|VBC�|VICMS�|VBCST�|VST�|VProd�|VFrete�|VSeg�|VDesc�|VII�|VIPI�|VPIS�|VCOFINS�|VOutro�|VNF�'); //ok
    LoadLayout('<W17>   W17|VServ�|VBC�|VISS�|VPIS�|VCOFINS�'); //ok
    LoadLayout('<W23>   W23|VRetPIS�|VRetCOFINS�|VRetCSLL�|VBCIRRF�|VIRRF�|VBCRetPrev�|VRetPrev�'); //ok
    LoadLayout('<X01>     X|ModFrete�'); //ok
    LoadLayout('<X03>   X03|XNome�|IE�|XEnder�|UF�|XMun�'); //ok
    LoadLayout('<X03>   X04|CNPJ�'); //ok
    LoadLayout('<X03>   X05|CPF�'); //ok
    LoadLayout('<X11>   X11|VServ�|VBCRet�|PICMSRet�|VICMSRet�|CFOP�|CMunFG�'); //ok
    LoadLayout('<X18>   X18|Placa�|UF�|RNTC�'); //ok
    LoadLayout('<X22>   X22|Placa�|UF�|RNTC�'); //ok
    LoadLayout('<X26>   X26|QVol�|Esp�|Marca�|NVol�|PesoL�|PesoB�'); //ok
    LoadLayout('<X33>   X33|NLacre�'); //ok
    LoadLayout('<Y01>     Y'); //ok
    LoadLayout('<Y02>   Y02|NFat�|VOrig�|VDesc�|VLiq�'); //ok
    LoadLayout('<Y07>   Y07|NDup�|DVenc�|VDup�'); //ok
    LoadLayout('<Z01>     Z|InfAdFisco�|InfCpl�'); //ok
    LoadLayout('<Z04>   Z04|XCampo�|XTexto�'); //ok
    //adLayout('<Z07>   Z07|XCampo�|XTexto�'); //ok - ?
    LoadLayout('<Z10>   Z10|NProc�|IndProc�'); //ok
    LoadLayout('<ZA01>   ZA|UFEmbarq�|XLocEmbarq�'); //ok
    LoadLayout('<ZB01>   ZB|XNEmp�|XPed�|XCont�'); //ok
  end;

  if versao = '2.00' then
  begin
    LoadLayout('<B01>       NOTA FISCAL|1');
    LoadLayout('<B01>     A|2.00|^id^'); //ok
    LoadLayout('<B01>     B|cUF�|cNF�|NatOp�|indPag�|mod�|serie�|nNF�|dEmi�|dSaiEnt�|hSaiEnt�|tpNF�|cMunFG�|TpImp�|TpEmis�|CDV�|TpAmb�|FinNFe�|ProcEmi�|VerProc�|dhCont�|xJust�'); //ok
    LoadLayout('<B12a>  B13|refNFe�'); //ok
    LoadLayout('<B14>   B14|cUF�|AAMM�|CNPJ�|Mod�|serie�|nNF�'); //ok
    LoadLayout('<B20a> B20a|cUF�|AAMM�|IE�|Mod�|serie�|nNF�'); //
    LoadLayout('<B20d> B20d|CNPJ�'); //
    LoadLayout('<B20e> B20e|CPF�'); //
    LoadLayout('<B20i> B20i|refCTe�'); //
    LoadLayout('<B20j> B20j|mod�|nECF�|nCOO�'); //
    LoadLayout('<C01>     C|XNome�|XFant�|IE�|IEST�|IM�|CNAE�|CRT�'); //ok
    LoadLayout('<C01>   C02|CNPJ�'); //ok
    LoadLayout('<C01>  C02a|CPF�'); //ok
    LoadLayout('<C05>   C05|XLgr�|Nro�|xCpl�|xBairro�|CMun�|XMun�|UF�|CEP�|CPais�|XPais�|Fone�'); //ok
    LoadLayout('<D01>     D|CNPJ�|XOrgao�|Matr�|XAgente�|Fone�|UF�|NDAR�|DEmi�|VDAR�|RepEmi�|DPag�'); //ok
    LoadLayout('<E01>     E|XNome�|IE�|ISUF�|EMAIL�'); //ok
    LoadLayout('<E01>   E02|CNPJ�'); //ok
    LoadLayout('<E01>   E03|CPF�'); //ok
    LoadLayout('<E05>   E05|XLgr�|Nro�|XCpl�|XBairro�|CMun�|XMun�|UF�|CEP�|CPais�|XPais�|Fone�'); //ok
    LoadLayout('<F01>     F|XLgr�|Nro�|XCpl�|XBairro�|CMun�|XMun�|UF�'); //ok
    LoadLayout('<F01>   F02|CNPJ�'); //ok
    LoadLayout('<F01>  F02a|CPF�');  //ok
    LoadLayout('<G01>     G|XLgr�|Nro�|XCpl�|XBairro�|CMun�|XMun�|UF�'); //ok
    LoadLayout('<G01>   G02|CNPJ�'); //ok
    LoadLayout('<G01>  G02a|CPF�');  //ok
    LoadLayout('<H01>     H|NItem�|InfAdProd�'); //ok
    LoadLayout('<I01>     I|CProd�|CEAN�|XProd�|NCM�|EXTIPI�|CFOP�|UCom�|QCom�|VUnCom�|VProd�|CEANTrib�|UTrib�|QTrib�|VUnTrib�|VFrete�|VSeg�|VDesc�|VOutro�|indTot�|xPed�|nItemPed�'); //ok
    LoadLayout('<I18>   I18|NDI�|DDI�|XLocDesemb�|UFDesemb�|DDesemb�|CExportador�'); //ok
    LoadLayout('<I25>   I25|NAdicao�|NSeqAdic�|CFabricante�|VDescDI�'); //ok
    LoadLayout('<J01>     J|tpOp�|chassi�|cCor�|xCor�|pot�|Cilin�|pesoL�|pesoB�|NSerie�|TpComb�|NMotor�|CMT�|Dist�|AnoMod�|AnoFab�|TpPint�|TpVeic�|EspVeic�|VIN�|CondVeic�|CMod�|cCorDENATRAN�|lota�|tpRest�'); //NFe 2.0
    LoadLayout('<K01>     K|NLote�|QLote�|DFab�|DVal�|VPMC�'); //ok
    LoadLayout('<L00>     L|TpArma�|NSerie�|NCano�|Descr�'); //ok
    LoadLayout('<L01>   L01|CProdANP�|CODIF�|QTemp�|UFCons�'); //ok
    LoadLayout('<L105> L105|QBCProd�|VAliqProd�|VCIDE�'); //ok
    LoadLayout('<L109> L109|VBCICMS�|VICMS�|VBCICMSST�|VICMSST�'); //ok
    LoadLayout('<L114> L114|VBCICMSSTDest�|VICMSSTDest�'); //ok
    LoadLayout('<L117> L117|VBCICMSSTCons�|VICMSSTCons�|UFCons�'); //ok
    LoadLayout('<M01>     M'); //ok
    LoadLayout('<N01>     N'); //ok
    LoadLayout('<N02>   N02|Orig�|CST�|ModBC�|VBC�|PICMS�|VICMS�'); //ok
    LoadLayout('<N03>   N03|Orig�|CST�|ModBC�|VBC�|PICMS�|VICMS�|ModBCST�|PMVAST�|PRedBCST�|VBCST�|PICMSST�|VICMSST�'); //ok
    LoadLayout('<N04>   N04|Orig�|CST�|ModBC�|PRedBC�|VBC�|PICMS�|VICMS�'); //ok
    LoadLayout('<N05>   N05|Orig�|CST�|ModBCST�|PMVAST�|PRedBCST�|VBCST�|PICMSST�|VICMSST�'); //ok
    LoadLayout('<N06>   N06|Orig�|CST�'); //ok
    LoadLayout('<N07>   N07|Orig�|CST�|ModBC�|PRedBC�|VBC�|PICMS�|VICMS�'); //ok
    LoadLayout('<N08>   N08|Orig�|CST�|VBCSTRet�|VICMSSTRet�'); //ok
    LoadLayout('<N09>   N09|Orig�|CST�|ModBC�|PRedBC�|VBC�|PICMS�|VICMS�|ModBCST�|PMVAST�|PRedBCST�|VBCST�|PICMSST�|VICMSST�'); //ok
    LoadLayout('<N10>   N10|Orig�|CST�|ModBC�|PRedBC�|VBC�|PICMS�|VICMS�|ModBCST�|PMVAST�|PRedBCST�|VBCST�|PICMSST�|VICMSST�'); //ok
    LoadLayout('<N10a> N10a|Orig�|CST�|ModBC�|PRedBC�|VBC�|PICMS�|VICMS�|ModBCST�|PMVAST�|PRedBCST�|VBCST�|PICMSST�|VICMSST�|pBCOp�|UFST�');
    LoadLayout('<N10b> N10b|Orig�|CST�|vBCSTRet�|vICMSSTRet�|vBCSTDest�|vICMSSTDest�');
    LoadLayout('<N10c> N10c|Orig�|CSOSN�|pCredSN�|vCredICMSSN�');
    LoadLayout('<N10d> N10d|Orig�|CSOSN�');
    LoadLayout('<N10e> N10e|Orig�|CSOSN�|modBCST�|pMVAST�|pRedBCST�|vBCST�|pICMSST�|vICMSST�|pCredSN�|vCredICMSSN�');
    LoadLayout('<N10f> N10f|Orig�|CSOSN�|modBCST�|pMVAST�|pRedBCST�|vBCST�|pICMSST�|vICMSST�');
    LoadLayout('<N10g> N10g|Orig�|CSOSN�|modBCST�|vBCSTRet�|vICMSSTRet�');
    LoadLayout('<N10h> N10h|Orig�|CSOSN�|modBC�|vBC�|pRedBC�|pICMS�|vICMS�|modBCST�|pMVAST�|pRedBCST�|vBCST�|pICMSST�|vICMSST�|pCredSN�|vCredICMSSN�');
    LoadLayout('<O01>     O|ClEnq�|CNPJProd�|CSelo�|QSelo�|CEnq�'); //ok
    LoadLayout('<O07>   O07|CST�|VIPI�'); //ok
    LoadLayout('<O07>   O10|VBC�|PIPI�'); //ok
    LoadLayout('<O07>   O11|QUnid�|VUnid�'); //ok
    LoadLayout('<O08>   O08|CST�'); //ok
    LoadLayout('<P01>     P|VBC�|VDespAdu�|VII�|VIOF�'); //ok
    LoadLayout('<Q01>     Q'); //ok
    LoadLayout('<Q02>   Q02|CST�|VBC�|PPIS�|VPIS�'); //ok
    LoadLayout('<Q03>   Q03|CST�|QBCProd�|VAliqProd�|VPIS�'); //ok
    LoadLayout('<Q04>   Q04|CST�'); //ok
    LoadLayout('<Q05>   Q05|CST�|VPIS�'); //ok
    LoadLayout('<Q05>   Q07|VBC�|PPIS�'); //ok
    LoadLayout('<Q05>   Q10|QBCProd�|VAliqProd�'); //ok
    LoadLayout('<R01>     R|VPIS�'); //ok
    LoadLayout('<R01>   R02|VBC�|PPIS�'); //ok
    LoadLayout('<R01>   R04|QBCProd�|VAliqProd�'); //ok
    LoadLayout('<S01>     S'); //ok
    LoadLayout('<S02>   S02|CST�|VBC�|PCOFINS�|VCOFINS�'); //ok
    LoadLayout('<S03>   S03|CST�|QBCProd�|VAliqProd�|VCOFINS�'); //ok
    LoadLayout('<S04>   S04|CST�'); //ok
    LoadLayout('<S05>   S05|CST�|VCOFINS�'); //ok
    LoadLayout('<S05>   S07|VBC�|PCOFINS�'); //ok
    LoadLayout('<S05>   S09|QBCProd�|VAliqProd�'); //ok
    LoadLayout('<T01>     T|VCOFINS�'); //ok
    LoadLayout('<T01>   T02|VBC�|PCOFINS�'); //ok
    LoadLayout('<T01>   T04|QBCProd�|VAliqProd�'); //ok
    LoadLayout('<U01>     U|VBC�|VAliq�|VISSQN�|CMunFG�|CListServ�|CSitTrib�'); //ok
    LoadLayout('<W01>     W'); //ok
    LoadLayout('<W02>   W02|VBC�|VICMS�|VBCST�|VST�|VProd�|VFrete�|VSeg�|VDesc�|VII�|VIPI�|VPIS�|VCOFINS�|VOutro�|VNF�'); //ok
    LoadLayout('<W17>   W17|VServ�|VBC�|VISS�|VPIS�|VCOFINS�'); //ok
    LoadLayout('<W23>   W23|VRetPIS�|VRetCOFINS�|VRetCSLL�|VBCIRRF�|VIRRF�|VBCRetPrev�|VRetPrev�'); //ok
    LoadLayout('<X01>     X|ModFrete�'); //ok
    LoadLayout('<X03>   X03|XNome�|IE�|XEnder�|UF�|XMun�'); //ok
    LoadLayout('<X03>   X04|CNPJ�'); //ok
    LoadLayout('<X03>   X05|CPF�'); //ok
    LoadLayout('<X11>   X11|VServ�|VBCRet�|PICMSRet�|VICMSRet�|CFOP�|CMunFG�'); //ok
    LoadLayout('<X18>   X18|Placa�|UF�|RNTC�'); //ok
    LoadLayout('<X22>   X22|Placa�|UF�|RNTC�'); //ok
    LoadLayout('<X26>   X26|QVol�|Esp�|Marca�|NVol�|PesoL�|PesoB�'); //ok
    LoadLayout('<X33>   X33|NLacre�'); //ok
    LoadLayout('<Y01>     Y'); //ok
    LoadLayout('<Y02>   Y02|NFat�|VOrig�|VDesc�|VLiq�'); //ok
    LoadLayout('<Y07>   Y07|NDup�|DVenc�|VDup�'); //ok
    LoadLayout('<Z01>     Z|InfAdFisco�|InfCpl�'); //ok
    LoadLayout('<Z04>   Z04|XCampo�|XTexto�'); //ok
    LoadLayout('<Z07>   Z07|XCampo�|XTexto�'); //ok - ?
    LoadLayout('<Z10>   Z10|NProc�|IndProc�'); //ok
    LoadLayout('<ZA01>   ZA|UFEmbarq�|XLocEmbarq�'); //ok
    LoadLayout('<ZB01>   ZB|XNEmp�|XPed�|XCont�'); //ok
  end;

  Result := Layout;
end;

end.

