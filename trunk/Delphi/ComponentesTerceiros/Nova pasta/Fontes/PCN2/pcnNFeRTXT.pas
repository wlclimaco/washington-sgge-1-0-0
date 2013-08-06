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

unit pcnNFeRTXT;

interface uses

  SysUtils, Classes,
{$IFNDEF VER130}
  Variants,
{$ENDIF}
  pcnAuxiliar, pcnConversao, pcnNFe, pcnLayoutTXT,
  ACBrConsts;

type

  TNFeRTXT = class(TPersistent)
  private
    FNFe: TNFe;
    FConteudoArquivo: TStringList;
    FLayoutArquivoTXT: TStringList;
    FSchema: TpcnSchema;
    FRegistro: string;
    FID: string;
  public
    constructor Create(AOwner: TNFe);
    destructor Destroy; override;
    function LerTxt: boolean;
    function CarregarArquivo(const CaminhoArquivo: string): boolean;
    procedure LerRegistro(const Registro: string);
    function LerCampo(const Tipo: TpcnTipoCampo; TAG: string): variant;
    function RetornarConteudoTag(const TAG: string): string;
    function LocalizarPosicaoTAG(TAG: string; Conteudo: string): integer;
  published
    property NFe: TNFe read FNFe write FNFe;
    property ConteudoArquivo: TStringList read FConteudoArquivo write FConteudoArquivo;
    property LayoutArquivoTXT: TStringList read FLayoutArquivoTXT write FLayoutArquivoTXT;
    property schema: TpcnSchema read Fschema write Fschema;
    property Registro: string read FRegistro write FRegistro;
    property ID: string read FID write FID;
  end;

implementation

{ TNFeRTXT }

constructor TNFeRTXT.Create(AOwner: TNFe);
begin
  FNFe := AOwner;
  FConteudoArquivo := TStringList.create;
  FLayoutArquivoTXT := TStringList.create;
end;

destructor TNFeRTXT.Destroy;
begin
  inherited Destroy;
  FConteudoArquivo.Free;
  FLayoutArquivoTXT.Free;
end;

function TNFeRTXT.CarregarArquivo(const CaminhoArquivo: string): boolean;
begin
  Result := False;
  if not FileExists(caminhoArquivo) then
    exit;
  FConteudoArquivo.LoadFromFile(CaminhoArquivo);
  Result := True;
end;

function TNFeRTXT.LerTxt: boolean;
var
  i: integer;
  versao: string;
begin
  Result := False;
  // Ler o arquivo
  if FConteudoArquivo.Count = 0 then
    exit;
  if (copy(FconteudoArquivo[0], 1, 10) <> 'NOTAFISCAL') and (copy(FconteudoArquivo[0], 1, 11) <> 'NOTA FISCAL') then
    if (pos('NOTAFISCAL|',FconteudoArquivo[0]) = 0) then
      exit;
  versao := copy(FconteudoArquivo[1], 3, 4);
  if pos('|' + Versao + '|', VERSOES_VALIDAS_LAYOUT_TXT) = 0 then
    exit;
  FLayoutArquivoTXT.Text := CarregarLayoutTXT(versao);
  // Ler chave da NFe
  NFe.infNFe.ID := copy(FconteudoArquivo[1], 8, maxInt);
  // Ajustar Registros
  for i := 0 to FConteudoArquivo.Count - 1 do
    FConteudoArquivo[i] := '�' + FConteudoArquivo[i] + '|';
  for i := 0 to FLayoutArquivoTXT.Count - 1 do
    FLayoutArquivoTXT[i] := '�' + trim(copy(FLayoutArquivoTXT[i], pos('>', FLayoutArquivoTXT[i]) + 1, maxInt));
  // Ler Registros
  for i := 2 to FConteudoArquivo.Count - 1 do
  begin
    LerRegistro(FconteudoArquivo[i])
  end;
  Result := True;
end;

function TNFeRTXT.LerCampo(const Tipo: TpcnTipoCampo; TAG: string): variant;
var
  ConteudoTag: string;
begin
  ConteudoTag := RetornarConteudoTag(TAG);
  if copy(ConteudoTag,1,1) = '�' then
    ConteudoTag := '';

  case Tipo of
    tcStr     : result := ReverterFiltroTextoXML(ConteudoTag);
    tcDat     : begin
                  if length(ConteudoTag)>0 then
                    result := EncodeDate(StrToInt(copy(ConteudoTag, 01, 4)), StrToInt(copy(ConteudoTag, 06, 2)), StrToInt(copy(ConteudoTag, 09, 2)))
                  else
                    result:=0;
                  end;
    tcDatHor  : begin
                    if length(ConteudoTag)>0 then
                      result := EncodeDate(StrToInt(copy(ConteudoTag, 01, 4)), StrToInt(copy(ConteudoTag, 06, 2)), StrToInt(copy(ConteudoTag, 09, 2))) +
                      EncodeTime(StrToInt(copy(ConteudoTag, 12, 2)), StrToInt(copy(ConteudoTag, 15, 2)), StrToInt(copy(ConteudoTag, 18, 2)), 0)
                    else
                      result:=0;
                  end;
    tcHor     : begin
                    if length(ConteudoTag)>0 then
                    result := EncodeTime(StrToInt(copy(ConteudoTag, 1, 2)), StrToInt(copy(ConteudoTag, 4, 2)), StrToInt(copy(ConteudoTag, 7, 2)), 0)
                    else
                    result:=0;
                  end;
    tcDe2,
    tcDe3,
    tcDe4,
    tcDe10    : result := StrToFloat(StringReplace('0' + ConteudoTag, '.', DecimalSeparator, []));
    tcEsp     : result := ConteudoTag;
    tcInt     : result := StrToInt('0' + Trim(SomenteNumeros(ConteudoTag)));
    else
      raise Exception.Create('Tag <' + Tag + '> com conte�do inv�lido. '+ConteudoTag);
  end;
end;

function TNFeRTXT.LocalizarPosicaoTAG(TAG: string; Conteudo: string): integer;
var
  i, contador: integer;
begin
  TAG := Trim(TAG);
  conteudo := copy(conteudo, 1, pos('|' + TAG + '�', conteudo));
  contador := 0;
  for i := 1 to length(conteudo) do
    if conteudo[i] = '|' then
      inc(contador);
  result := contador;
end;

function TNFeRTXT.RetornarConteudoTag(const TAG: string): string;
var
  i, j, k, m: integer;
  s: string;
begin
  j := 0;
  for i := 0 to FLayoutArquivoTXT.count - 1 do
  begin
    s := FLayoutArquivoTXT[i];
    if (pos('�' + UpperCase(FID) + '|', s) > 0) and (j = 0) then
      j := LocalizarPosicaoTAG(UpperCase(TAG), s);
  end;
  k := 0;
  m := 0;
  for i := 1 to length(FRegistro) do
    if (FRegistro[i] = '|') and (k < j) then
    begin
      inc(k);
      m := i;
    end;
  s := copy(FRegistro, m + 1, maxint);
  result := copy(s, 1, pos('|', s) - 1);
end;

procedure TNFeRTXT.LerRegistro(const Registro: string);
var
  i, j, k: integer;
  ok: boolean;
begin

  FRegistro := Registro;
  FID := UpperCase(copy(Registro, 2, pos('|', FRegistro) - 2));
  if FID = '' then
    exit;

  if ID = 'B' then (* Grupo da TAG <ide> **************************************)
  begin
    (*B02*)NFe.ide.cUF := LerCampo(tcInt, 'cUF');
    (*B03*)NFe.ide.cNF := LerCampo(tcInt, 'cNF');
    (*B04*)NFe.ide.natOp := LerCampo(tcStr, 'natOp');
    (*B05*)NFe.ide.indPag := StrToIndpag(ok, LerCampo(tcStr, 'indPag'));
    (*B06*)NFe.ide.modelo := LerCampo(tcInt, 'mod');
    (*B07*)NFe.ide.serie := LerCampo(tcInt, 'serie');
    (*B08*)NFe.ide.nNF := LerCampo(tcInt, 'nNF');
    (*B09*)NFe.ide.dEmi := LerCampo(tcDat, 'dEmi');
    (*B10*)NFe.ide.dSaiEnt := LerCampo(tcDat, 'dSaiEnt');
   (*B10a*)NFe.ide.hSaiEnt := LerCampo(tcHor, 'hSaiEnt');
    (*B11*)NFe.ide.tpNF := StrToTpNF(ok, LerCampo(tcStr, 'tpNF'));
    (*B12*)NFe.ide.cMunFG := LerCampo(tcInt, 'cMunFG');

    (*B21*)NFe.Ide.tpImp := StrToTpImp(ok, LerCampo(tcStr, 'tpImp'));
    (*B22*)NFe.Ide.tpEmis := StrToTpEmis(ok, LerCampo(tcStr, 'tpEmis'));
    (*B23*)NFe.Ide.cDV := LerCampo(tcInt, 'cDV');
    (*B24*)NFe.Ide.tpAmb := StrToTpAmb(ok, LerCampo(tcStr, 'tpAmb'));
    (*B25*)NFe.Ide.finNFe := StrToFinNFe(ok, LerCampo(tcStr, 'finNFe'));
    (*B26*)NFe.Ide.procEmi := StrToprocEmi(ok, LerCampo(tcStr, 'procEmi'));
    (*B27*)NFe.Ide.verProc := LerCampo(tcStr, 'verProc');
    (*B28*)NFe.Ide.dhCont := LerCampo(tcDatHor, 'dhCont');
    (*B29*)NFe.Ide.xJust := LerCampo(tcStr, 'xJust');
  end;

  (* Grupo da TAG <ide><NFref><refNFe> ****************************************)
  if ID = 'B13' then
  begin
    NFe.Ide.NFref.Add;
    i := NFe.ide.NFref.Count - 1;
    (*B13*)NFe.ide.NFref[i].refNFe := LerCampo(tcEsp, 'refNFe');
  end;

  if ID = 'B14' then (* Grupo da TAG <ide><NFref><RefNF> **********************)
  begin
    NFe.Ide.NFref.Add;
    i := NFe.ide.NFref.Count - 1;
    (*B15*)NFe.Ide.NFref[i].RefNF.cUF := LerCampo(tcInt, 'cUF');
    (*B16*)NFe.Ide.NFref[i].RefNF.AAMM := LerCampo(tcEsp, 'AAMM');
    (*B17*)NFe.Ide.NFref[i].RefNF.CNPJ := LerCampo(tcEsp, 'CNPJ');
    (*B18*)NFe.Ide.NFref[i].RefNF.Modelo := StrToInt(LerCampo(tcInt, 'mod'));
    (*B19*)NFe.ide.NFref[i].RefNF.serie := LerCampo(tcInt, 'serie');
    (*B20*)NFe.Ide.NFref[i].RefNF.nNF := LerCampo(tcInt, 'nNF');
  end;

  if ID = 'B20A' then (* Grupo da TAG <ide><NFref><refNFP> **********************)
  begin
    NFe.Ide.NFref.Add;
    i := NFe.ide.NFref.Count - 1;
    (*B20b*)NFe.Ide.NFref[i].refNFP.cUF := LerCampo(tcInt, 'cUF');
    (*B20c*)NFe.Ide.NFref[i].refNFP.AAMM := LerCampo(tcEsp, 'AAMM');
    (*B20f*)NFe.Ide.NFref[i].refNFP.IE := LerCampo(tcStr, 'IE');
    (*B20f*)NFe.Ide.NFref[i].refNFP.modelo := LerCampo(tcStr, 'mod');
    (*B20g*)NFe.ide.NFref[i].refNFP.serie := LerCampo(tcInt, 'serie');
    (*B20h*)NFe.Ide.NFref[i].refNFP.nNF := LerCampo(tcInt, 'nNF');
  end;

  if (ID = 'B20D') and  (NFe.ide.NFref.Count > 0) then
   begin
     i := NFe.ide.NFref.Count - 1;
    (*B20d*)NFe.Ide.NFref[i].refNFP.CNPJCPF := LerCampo(tcEsp, 'CNPJ');
   end;

  if (ID = 'B20E') and  (NFe.ide.NFref.Count > 0) then
   begin
     i := NFe.ide.NFref.Count - 1;
    (*B20e*)NFe.Ide.NFref[i].refNFP.CNPJCPF := LerCampo(tcEsp, 'CPF');
   end;

  (* Grupo da TAG <ide><NFref><refCTe> ****************************************)
  if ID = 'B20I' then
  begin
    NFe.Ide.NFref.Add;
    i := NFe.ide.NFref.Count - 1;
    (*B13*)NFe.ide.NFref[i].refCTe := LerCampo(tcEsp, 'refCTe');
  end;

  if ID = 'B20J' then (* Grupo da TAG <ide><NFref><refECF> **********************)
  begin
    NFe.Ide.NFref.Add;
    i := NFe.ide.NFref.Count - 1;
    (*B20k*)NFe.Ide.NFref[i].RefECF.modelo := StrToECFModRef(ok, LerCampo(tcStr, 'mod'));
    (*B20i*)NFe.Ide.NFref[i].RefECF.nECF := LerCampo(tcStr, 'nECF');
    (*B20m*)NFe.Ide.NFref[i].RefECF.nCOO := LerCampo(tcStr, 'nCOO');
  end;

  if ID = 'C' then (* Grupo da TAG <emit> *************************************)
  begin
    (*C  *)NFe.Emit.xNome := LerCampo(tcStr, 'xNome');
    (*C  *)NFe.Emit.xFant := LerCampo(tcStr, 'xFant');
    (*C17*)NFe.Emit.IE := LerCampo(tcStr, 'IE');
    (*C18*)NFe.Emit.IEST := LerCampo(tcStr, 'IEST');
    (*C19*)NFe.Emit.IM := LerCampo(tcStr, 'IM');
    (*C20*)NFe.Emit.CNAE := LerCampo(tcStr, 'CNAE');
    (*C21*)NFe.Emit.CRT := StrToCRT(ok, LerCampo(tcStr, 'CRT'));
  end;
  if ID = 'C02' then
    (*C02*)NFe.Emit.CNPJCPF := LerCampo(tcStr, 'CNPJ');
  if ID = 'C02A' then
    (*C02A*)NFe.Emit.CNPJCPF := LerCampo(tcStr, 'CPF');

  if ID = 'C05' then (* Grupo da TAG <emit><EnderEmit> ************************)
  begin
    (*C06*)NFe.Emit.enderEmit.xLgr := LerCampo(tcStr, 'xLgr');
    (*C07*)NFe.Emit.enderEmit.nro := LerCampo(tcStr, 'nro');
    (*C08*)NFe.Emit.enderEmit.xCpl := LerCampo(tcStr, 'xCpl');
    (*C09*)NFe.Emit.enderEmit.xBairro := LerCampo(tcStr, 'xBairro');
    (*C10*)NFe.Emit.EnderEmit.cMun := LerCampo(tcInt, 'cMun');
    (*C11*)NFe.Emit.enderEmit.xMun := LerCampo(tcStr, 'xMun');
    (*C12*)NFe.Emit.enderEmit.UF := LerCampo(tcStr, 'UF');
    (*C13*)NFe.Emit.enderEmit.CEP := LerCampo(tcInt, 'CEP');
    (*C14*)NFe.Emit.enderEmit.cPais := LerCampo(tcInt, 'cPais');
    (*C15*)NFe.Emit.enderEmit.xPais := LerCampo(tcStr, 'xPais');
    (*C16*)NFe.Emit.enderEmit.fone := LerCampo(tcStr, 'fone');
    if NFe.Emit.enderEmit.cPais = 0 then
     begin
       (*C14*)NFe.Emit.enderEmit.cPais := 1058;
       (*C15*)NFe.Emit.enderEmit.xPais := 'BRASIL';
     end;
  end;

  if ID = 'D' then (* Grupo da TAG <avulsa> ***********************************)
  begin
    (*D02*)NFe.Avulsa.CNPJ := LerCampo(tcStr, 'CNPJ');
    (*D03*)NFe.Avulsa.xOrgao := LerCampo(tcStr, 'xOrgao');
    (*D04*)NFe.Avulsa.matr := LerCampo(tcStr, 'matr');
    (*D05*)NFe.Avulsa.xAgente := LerCampo(tcStr, 'xAgente');
    (*D06*)NFe.Avulsa.fone := LerCampo(tcStr, 'fone');
    (*D07*)NFe.Avulsa.UF := LerCampo(tcStr, 'UF');
    (*D08*)NFe.Avulsa.nDAR := LerCampo(tcStr, 'nDAR');
    (*D09*)NFe.Avulsa.dEmi := LerCampo(tcDat, 'dEmi');
    (*D10*)NFe.Avulsa.vDAR := LerCampo(tcDe2, 'vDAR');
    (*D11*)NFe.Avulsa.repEmi := LerCampo(tcStr, 'repEmi');
    (*D12*)NFe.Avulsa.dPag := LerCampo(tcDat, 'dPag');
  end;

  if ID = 'E' then (* Grupo da TAG <dest> *************************************)
  begin
    (*E04*)NFe.Dest.xNome := LerCampo(tcStr, 'xNome');
    (*E17*)NFe.Dest.IE := LerCampo(tcStr, 'IE');
    (*E18*)NFe.Dest.ISUF := LerCampo(tcStr, 'ISUF');
    (*E19*)NFe.Dest.Email := LerCampo(tcStr, 'email');
  end;
  if ID = 'E02' then
    (*E02*)NFe.Dest.CNPJCPF := LerCampo(tcStr, 'CNPJ');
  if ID = 'E03' then
    (*E03*)NFe.Dest.CNPJCPF := LerCampo(tcStr, 'CPF');

  if ID = 'E05' then (* Grupo da TAG <dest><EnderDest> ************************)
  begin
    (*E06*)NFe.Dest.enderDest.xLgr := LerCampo(tcStr, 'xLgr');
    (*E07*)NFe.Dest.enderDest.nro := LerCampo(tcStr, 'nro');
    (*E08*)NFe.Dest.enderDest.xCpl := LerCampo(tcStr, 'xCpl');
    (*E09*)NFe.Dest.enderDest.xBairro := LerCampo(tcStr, 'xBairro');
    (*E10*)NFe.Dest.enderDest.cMun := LerCampo(tcInt, 'cMun');
    (*E11*)NFe.Dest.enderDest.xMun := LerCampo(tcStr, 'xMun');
    (*E12*)NFe.Dest.enderDest.UF := LerCampo(tcStr, 'UF');
    (*E13*)NFe.Dest.enderDest.CEP := LerCampo(tcInt, 'CEP');
    (*E14*)NFe.Dest.enderDest.cPais := LerCampo(tcInt, 'cPais');
    (*E15*)NFe.Dest.enderDest.xPais := LerCampo(tcStr, 'xPais');
    (*E16*)NFe.Dest.enderDest.fone := LerCampo(tcStr, 'fone');
    if NFe.Dest.EnderDest.cPais = 0 then
     begin
       (*E14*)NFe.Dest.EnderDest.cPais := 1058;
       (*E15*)NFe.Dest.EnderDest.xPais := 'BRASIL';
     end;

  end;

  if ID = 'F' then (* Grupo da TAG <retirada> *********************************)
  begin
    (*F03*)NFe.Retirada.xLgr := LerCampo(tcStr, 'xLgr');
    (*F04*)NFe.Retirada.nro := LerCampo(tcStr, 'nro');
    (*F05*)NFe.Retirada.xCpl := LerCampo(tcStr, 'xCpl');
    (*F06*)NFe.Retirada.xBairro := LerCampo(tcStr, 'xBairro');
    (*F07*)NFe.Retirada.cMun := LerCampo(tcInt, 'cMun');
    (*F08*)NFe.Retirada.xMun := LerCampo(tcStr, 'xMun');
    (*F09*)NFe.Retirada.UF := LerCampo(tcStr, 'UF');
  end;
  if ID = 'F02' then
    (*F02*)NFe.Retirada.CNPJCPF := LerCampo(tcStr, 'CNPJ');
  if ID = 'F02A' then
    (*F02a*)NFe.Retirada.CNPJCPF := LerCampo(tcStr, 'CPF');

  if ID = 'G' then (* Grupo da TAG <entrega> **********************************)
  begin
    (*G03*)NFe.Entrega.xLgr := LerCampo(tcStr, 'xLgr');
    (*G04*)NFe.Entrega.nro := LerCampo(tcStr, 'nro');
    (*G05*)NFe.Entrega.xCpl := LerCampo(tcStr, 'xCpl');
    (*G06*)NFe.Entrega.xBairro := LerCampo(tcStr, 'xBairro');
    (*G07*)NFe.Entrega.cMun := LerCampo(tcInt, 'cMun');
    (*G08*)NFe.Entrega.xMun := LerCampo(tcStr, 'xMun');
    (*G09*)NFe.Entrega.UF := LerCampo(tcStr, 'UF');
  end;
  if ID = 'G02' then
    (*G02*)NFe.Entrega.CNPJCPF := LerCampo(tcStr, 'CNPJ');
  if ID = 'G02A' then
    (*G02a*)NFe.Entrega.CNPJCPF := LerCampo(tcStr, 'CPF');

  if ID = 'H' then (* Grupo da TAG <det> **************************************)
  begin
    NFe.Det.Add;
    i := NFe.Det.Count - 1;
    (*   *)NFe.Det[i].prod.nItem := i + 1;
    (*V01*)NFe.Det[i].infAdProd := LerCampo(tcStr, 'infAdProd');
  end;

  if ID = 'I' then (* Grupo da TAG <det><prod> ********************************)
  begin
    i := NFe.Det.Count - 1;
    (*I02*)NFe.Det[i].Prod.cProd := LerCampo(tcStr, 'cProd');
    (*I03*)NFe.Det[i].Prod.cEAN := LerCampo(tcStr, 'cEAN');
    (*I04*)NFe.Det[i].Prod.xProd := LerCampo(tcStr, 'xProd');
    (*I05*)NFe.Det[i].Prod.NCM := LerCampo(tcStr, 'NCM');
    (*I06*)NFe.Det[i].Prod.EXTIPI := LerCampo(tcStr, 'EXTIPI');
    //(*I07*)NFe.Det[i].Prod.genero := LerCampo(tcInt, 'genero');
    (*I08*)NFe.Det[i].Prod.CFOP := LerCampo(tcEsp, 'CFOP');
    (*I09*)NFe.Det[i].Prod.uCom := LerCampo(tcStr, 'uCom');
    (*I10*)NFe.Det[i].Prod.qCom := LerCampo(tcDe4, 'qCom');
    (*I10a*)NFe.Det[i].Prod.vUnCom := LerCampo(tcDe4, 'vUnCom');
    (*I11*)NFe.Det[i].Prod.vProd := LerCampo(tcDe2, 'vProd');
    (*I12*)NFe.Det[i].Prod.cEANTrib := LerCampo(tcStr, 'cEANTrib');
    (*I13*)NFe.Det[i].Prod.uTrib := LerCampo(tcStr, 'uTrib');
    (*I14*)NFe.Det[i].Prod.qTrib := LerCampo(tcDe4, 'qTrib');
    (*I14a*)NFe.Det[i].Prod.vUnTrib := LerCampo(tcDe4, 'vUnTrib');
    (*I15*)NFe.Det[i].Prod.vFrete := LerCampo(tcDe2, 'vFrete');
    (*I16*)NFe.Det[i].Prod.vSeg := LerCampo(tcDe2, 'vSeg');
    (*I17*)NFe.Det[i].Prod.vDesc := LerCampo(tcDe2, 'vDesc');
   (*I17a*)NFe.Det[i].Prod.vOutro := LerCampo(tcDe2, 'vOutro');
   (*I17b*)NFe.Det[i].Prod.IndTot := StrToIndTot(ok, LerCampo(tcStr, 'indTot'));
   (*I28a*)NFe.Det[i].Prod.xPed := LerCampo(tcStr, 'xPed');
   (*I28b*)NFe.Det[i].Prod.nItemPed := LerCampo(tcInt, 'nItemPed');
  end;

  if ID = 'I18' then (* Grupo da TAG <det><prod><DI> **************************)
  begin
    i := NFe.Det.Count - 1;
    NFe.Det[i].Prod.DI.Add;
    j := NFe.Det[i].Prod.DI.Count - 1;
    (*I19*)NFe.Det[i].Prod.DI[j].nDI := LerCampo(tcStr, 'nDI');
    (*I20*)NFe.Det[i].Prod.DI[j].dDI := LerCampo(tcDat, 'dDI');
    (*I21*)NFe.Det[i].Prod.DI[j].xLocDesemb := LerCampo(tcStr, 'xLocDesemb');
    (*I22*)NFe.Det[i].Prod.DI[j].UFDesemb := LerCampo(tcStr, 'UFDesemb');
    (*I23*)NFe.Det[i].Prod.DI[j].dDesemb := LerCampo(tcDat, 'dDesemb');
    (*I24*)NFe.Det[i].Prod.DI[j].cExportador := LerCampo(tcStr, 'cExportador');
  end;

  if ID = 'I25' then (* Grupo da TAG <det><prod><DI><adi> *********************)
  begin
    i := NFe.Det.Count - 1;
    j := NFe.Det[i].Prod.DI.Count - 1;
    NFe.Det[i].Prod.DI[j].adi.Add;
    k := NFe.Det[i].Prod.DI[j].adi.Count - 1;
    (*I26*)NFe.Det[i].Prod.DI[j].adi[k].nAdicao := LerCampo(tcInt, 'nAdicao');
    (*I27*)NFe.Det[i].Prod.DI[j].adi[k].nSeqAdi := LerCampo(tcInt, 'nSeqAdic');
    (*I28*)NFe.Det[i].Prod.DI[j].adi[k].cFabricante := LerCampo(tcStr, 'cFabricante');
    (*I29*)NFe.Det[i].Prod.DI[j].adi[k].vDescDI := LerCampo(tcDe2, 'vDescDI');
  end;

  if ID = 'J' then (* Grupo da TAG <det><prod><veicProd> **********************)
  begin
    i := NFe.Det.Count - 1;
    (*J02*)NFe.Det[i].Prod.veicProd.tpOP := StrToTpOP(ok, LerCampo(tcStr, 'tpOp'));
    (*J03*)NFe.Det[i].Prod.veicProd.chassi := LerCampo(tcStr, 'chassi');
    (*J04*)NFe.Det[i].Prod.veicProd.cCor := LerCampo(tcStr, 'cCor');
    (*J05*)NFe.Det[i].Prod.veicProd.xCor := LerCampo(tcStr, 'xCor');
    (*J06*)NFe.Det[i].Prod.veicProd.pot := LerCampo(tcStr, 'pot');
    (*J07*)NFe.Det[i].Prod.veicProd.Cilin := LerCampo(tcStr, 'Cilin');
    (*J08*)NFe.Det[i].Prod.veicProd.pesoL := LerCampo(tcStr, 'pesoL');
    (*J09*)NFe.Det[i].Prod.veicProd.pesoB := LerCampo(tcStr, 'pesoB');
    (*J10*)NFe.Det[i].Prod.veicProd.nSerie := LerCampo(tcStr, 'nSerie');
    (*J11*)NFe.Det[i].Prod.veicProd.tpComb := LerCampo(tcStr, 'tpComb');
    (*J12*)NFe.Det[i].Prod.veicProd.nMotor := LerCampo(tcStr, 'nMotor');
    (*J13*)NFe.Det[i].Prod.veicProd.CMT    := LerCampo(tcStr, 'CMT');
    (*J14*)NFe.Det[i].Prod.veicProd.dist := LerCampo(tcStr, 'dist');
//    (*J15*)NFe.Det[i].Prod.veicProd.RENAVAM := LerCampo(tcEsp, 'RENAVAM');
    (*J16*)NFe.Det[i].Prod.veicProd.anoMod := LerCampo(tcInt, 'anoMod');
    (*J17*)NFe.Det[i].Prod.veicProd.anoFab := LerCampo(tcInt, 'anoFab');
    (*J18*)NFe.Det[i].Prod.veicProd.tpPint := LerCampo(tcStr, 'tpPint');
    (*J19*)NFe.Det[i].Prod.veicProd.tpVeic := LerCampo(tcInt, 'tpVeic');
    (*J20*)NFe.Det[i].Prod.veicProd.espVeic := LerCampo(tcInt, 'espVeic');
    (*J21*)NFe.Det[i].Prod.veicProd.VIN := LerCampo(tcStr, 'VIN');
    (*J22*)NFe.Det[i].Prod.veicProd.condVeic := StrToCondVeic(ok, LerCampo(tcStr, 'condVeic'));
    (*J23*)NFe.Det[i].Prod.veicProd.cMod := LerCampo(tcStr, 'cMod');
    (*J24*)NFe.Det[i].Prod.veicProd.cCorDENATRAN := LerCampo(tcStr, 'cCorDenatran');
    (*J25*)NFe.Det[i].Prod.veicProd.lota := LerCampo(tcInt, 'lota');
    (*J26*)NFe.Det[i].Prod.veicProd.tpRest := LerCampo(tcInt, 'tpRest');
  end;

  if ID = 'K' then (* Grupo da TAG <det><prod><med> ***************************)
  begin
    i := NFe.Det.Count - 1;
    NFe.Det[i].Prod.med.Add;
    j := NFe.Det[i].Prod.med.Count - 1;
    (*K02*)NFe.Det[i].Prod.med[j].nLote := LerCampo(tcStr, 'nLote');
    (*K03*)NFe.Det[i].Prod.med[j].qLote := LerCampo(tcDe3, 'qLote');
    (*K04*)NFe.Det[i].Prod.med[j].dFab := LerCampo(tcDat, 'dFab');
    (*K05*)NFe.Det[i].Prod.med[j].dVal := LerCampo(tcDat, 'dVal');
    (*K06*)NFe.Det[i].Prod.med[j].vPMC := LerCampo(tcDe2, 'vPMC');
  end;

  if ID = 'L' then (* Grupo da TAG <det><prod><arma> **************************)
  begin
    i := NFe.Det.Count - 1;
    NFe.Det[i].Prod.arma.add;
    j := NFe.Det[i].Prod.arma.count - 1;
    (*L02*)NFe.Det[i].Prod.arma[j].tpArma := StrToTpArma(ok, LerCampo(tcStr, 'tpArma'));
    (*L03*)NFe.Det[i].Prod.arma[j].nSerie := LerCampo(tcInt, 'nSerie');
    (*L04*)NFe.Det[i].Prod.arma[j].nCano := LerCampo(tcInt, 'nCano');
    (*L05*)NFe.Det[i].Prod.arma[j].descr := LerCampo(tcStr, 'descr');
  end;

  if ID = 'L01' then (* Grupo da TAG <det><prod><comb> ************************)
  begin
    i := NFe.Det.Count - 1;
    (*L102*)NFe.Det[i].Prod.comb.cProdANP := LerCampo(tcInt, 'cProdANP');
    (*L103*)NFe.Det[i].Prod.comb.CODIF := LerCampo(tcEsp, 'CODIF');
    (*L104*)NFe.Det[i].Prod.comb.qTemp := LerCampo(tcDe4, 'qTemp');
    (*L120*)NFe.Det[i].Prod.comb.UFcons:= LerCampo(tcStr, 'UFCons');
  end;

  if ID = 'L105' then (* Grupo da TAG <det><prod><comb><CIDE> *****************)
  begin
    i := NFe.Det.Count - 1;
    (*L106*)NFe.Det[i].Prod.comb.CIDE.qBCprod := LerCampo(tcDe4, 'qBCprod');
    (*L107*)NFe.Det[i].Prod.comb.CIDE.vAliqProd := LerCampo(tcDe4, 'vAliqProd');
    (*L108*)NFe.Det[i].Prod.comb.CIDE.vCIDE := LerCampo(tcDe2, 'vCIDE');
  end;

  if ID = 'L109' then (* Grupo da TAG <det><prod><comb><ICMS> *****************)
  begin
    i := NFe.Det.Count - 1;
    (*L110*)NFe.Det[i].Prod.comb.ICMS.vBCICMS := LerCampo(tcDe2, 'vBCICMS');
    (*L111*)NFe.Det[i].Prod.comb.ICMS.vICMS := LerCampo(tcDe2, 'vICMS');
    (*L112*)NFe.Det[i].Prod.comb.ICMS.vBCICMSST := LerCampo(tcDe2, 'vBCICMSST');
    (*L113*)NFe.Det[i].Prod.comb.ICMS.vICMSST := LerCampo(tcDe2, 'vICMSST');
  end;

  if ID = 'L114' then (* Grupo da TAG <det><prod><comb><ICMSInter> ************)
  begin
    i := NFe.Det.Count - 1;
    (*L115*)NFe.Det[i].Prod.comb.ICMSInter.vBCICMSSTDest := LerCampo(tcDe2, 'vBCICMSSTDest');
    (*L116*)NFe.Det[i].Prod.comb.ICMSInter.vICMSSTDest := LerCampo(tcDe2, 'vICMSSTDest');
  end;

  if ID = 'L117' then (* Grupo da TAG <det><prod><comb><ICMSCons> *************)
  begin
    i := NFe.Det.Count - 1;
    (*L118*)NFe.Det[i].Prod.comb.ICMSCons.vBCICMSSTCons := LerCampo(tcDe2, 'vBCICMSSTCons');
    (*L119*)NFe.Det[i].Prod.comb.ICMSCons.vICMSSTCons := LerCampo(tcDe2, 'vICMSSTCons');
    (*L120*)NFe.Det[i].Prod.comb.ICMSCons.UFcons := LerCampo(tcStr, 'UFcons');
  end;

  if (ID = 'N02') or (ID = 'N03') or (ID = 'N04') or (ID = 'N05') or
     (ID = 'N06') or (ID = 'N07') or (ID = 'N08') or (ID = 'N09') or
     (ID = 'N10') or (ID = 'N10A') or (ID = 'N10B') or (ID = 'N10C') or
     (ID = 'N10D') or (ID = 'N10E') or (ID = 'N10F') or (ID = 'N10G') or
     (ID = 'N10H') then (* Grupo da TAG <det><imposto><ICMS> ********************)
  begin
    i := NFe.Det.Count - 1;
    (*N11*)NFe.Det[i].Imposto.ICMS.orig := StrToOrig(ok, LerCampo(tcStr, 'orig'));
    (*N12*)NFe.Det[i].Imposto.ICMS.CST := StrToCSTICMS(ok, LerCampo(tcStr, 'CST'));
   (*N12a*)NFe.Det[i].Imposto.ICMS.CSOSN := StrToCSOSNICMS(ok, LerCampo(tcStr, 'CSOSN'));
    (*N13*)NFe.Det[i].Imposto.ICMS.modBC := StrToModBC(ok, LerCampo(tcStr, 'modBC'));
    (*N14*)NFe.Det[i].Imposto.ICMS.pRedBC := LerCampo(tcDe2, 'pRedBC');
    (*N15*)NFe.Det[i].Imposto.ICMS.vBC := LerCampo(tcDe2, 'vBC');
    (*N16*)NFe.Det[i].Imposto.ICMS.pICMS := LerCampo(tcDe2, 'pICMS');
    (*N17*)NFe.Det[i].Imposto.ICMS.vICMS := LerCampo(tcDe2, 'vICMS');
    (*N18*)NFe.Det[i].Imposto.ICMS.modBCST := StrToModBCST(ok, LerCampo(tcStr, 'modBCST'));
    (*N19*)NFe.Det[i].Imposto.ICMS.pMVAST := LerCampo(tcDe2, 'pMVAST');
    (*N20*)NFe.Det[i].Imposto.ICMS.pRedBCST := LerCampo(tcDe2, 'pRedBCST');
    (*N21*)NFe.Det[i].Imposto.ICMS.vBCST := LerCampo(tcDe2, 'vBCST');
    (*N22*)NFe.Det[i].Imposto.ICMS.pICMSST := LerCampo(tcDe2, 'pICMSST');
    (*N23*)NFe.Det[i].Imposto.ICMS.vICMSST := LerCampo(tcDe2, 'vICMSST');
    (*N24*)NFe.Det[i].Imposto.ICMS.UFST := LerCampo(tcStr, 'UFST');
    (*N25*)NFe.Det[i].Imposto.ICMS.pBCOp := LerCampo(tcDe2, 'pBCOp');
    (*N26*)NFe.Det[i].Imposto.ICMS.vBCSTRet := LerCampo(tcDe2, 'vBCSTRet');
    (*N27*)NFe.Det[i].Imposto.ICMS.vICMSSTRet := LerCampo(tcDe2, 'vICMSSTRet');
    (*N28*)NFe.Det[i].Imposto.ICMS.motDesICMS := StrToMotDesICMS(ok, LerCampo(tcStr, 'motDesICMS'));
    (*N29*)NFe.Det[i].Imposto.ICMS.pCredSN := LerCampo(tcDe2, 'pCredSN');
    (*N30*)NFe.Det[i].Imposto.ICMS.vCredICMSSN := LerCampo(tcDe2, 'vCredICMSSN');
    (*N31*)NFe.Det[i].Imposto.ICMS.vBCSTDest := LerCampo(tcDe2, 'vBCSTDest');
    (*N32*)NFe.Det[i].Imposto.ICMS.vICMSSTDest := LerCampo(tcDe2, 'vICMSSTDest');
  end;

  if ID = 'O' then (* Grupo da TAG <det><imposto><IPI> **********************)
  begin
    i := NFe.Det.Count - 1;
    (*O02*)NFe.Det[i].Imposto.IPI.clEnq := LerCampo(tcStr, 'clEnq');
    (*O03*)NFe.Det[i].Imposto.IPI.CNPJProd := LerCampo(tcStr, 'CNPJProd');
    (*O04*)NFe.Det[i].Imposto.IPI.cSelo := LerCampo(tcStr, 'cSelo');
    (*O05*)NFe.Det[i].Imposto.IPI.qSelo := LerCampo(tcInt, 'qSelo');
    (*O06*)NFe.Det[i].Imposto.IPI.cEnq := LerCampo(tcStr, 'cEnq');
  end;

  if ID = 'O07' then (* Grupo da TAG <det><imposto><IPI> **********************)
  begin
    i := NFe.Det.Count - 1;
    (*O09*)NFe.Det[i].Imposto.IPI.CST := StrToCSTIPI(ok, LerCampo(tcStr, 'CST'));
    (*O14*)NFe.Det[i].Imposto.IPI.vIPI := LerCampo(tcDe2, 'vIPI');
  end;

  if ID = 'O08' then (* Grupo da TAG <det><imposto><IPI> **********************)
  begin
    i := NFe.Det.Count - 1;
    (*O09*)NFe.Det[i].Imposto.IPI.CST := StrToCSTIPI(ok, LerCampo(tcStr, 'CST'));
  end;

  if ID = 'O10' then (* Grupo da TAG <det><imposto><IPI> **********************)
  begin
    i := NFe.Det.Count - 1;
    (*O10*)NFe.Det[i].Imposto.IPI.vBC := LerCampo(tcDe2, 'vBC');
    (*O13*)NFe.Det[i].Imposto.IPI.pIPI := LerCampo(tcDe2, 'pIPI');
  end;

  if ID = 'O11' then (* Grupo da TAG <det><imposto><IPI> **********************)
  begin
    i := NFe.Det.Count - 1;
    (*O11*)NFe.Det[i].Imposto.IPI.qUnid := LerCampo(tcDe4, 'qUnid');
    (*O12*)NFe.Det[i].Imposto.IPI.vUnid := LerCampo(tcDe4, 'vUnid');
  end;

  if ID = 'P' then (* Grupo da TAG <det><imposto><II> *************************)
  begin
    i := NFe.Det.Count - 1;
    (*P02*)NFe.Det[i].Imposto.II.vBc := LerCampo(tcDe2, 'vBC');
    (*P03*)NFe.Det[i].Imposto.II.vDespAdu := LerCampo(tcDe2, 'vDespAdu');
    (*P04*)NFe.Det[i].Imposto.II.vII := LerCampo(tcDe2, 'vII');
    (*P05*)NFe.Det[i].Imposto.II.vIOF := LerCampo(tcDe2, 'vIOF');
  end;

  if ID = 'Q02' then (* Grupo da TAG <det><imposto><pis><pisaliq> *************)
  begin
    i := NFe.Det.Count - 1;
    (*Q06*)NFe.Det[i].Imposto.PIS.CST := StrToCSTPIS(ok, LerCampo(tcStr, 'CST'));
    (*Q07*)NFe.Det[i].Imposto.PIS.vBC := LerCampo(tcDe2, 'vBC');
    (*Q08*)NFe.Det[i].Imposto.PIS.pPIS := LerCampo(tcDe2, 'pPIS');
    (*Q09*)NFe.Det[i].Imposto.PIS.vPIS := LerCampo(tcDe2, 'vPIS');
  end;

  if ID = 'Q03' then (* Grupo da TAG <det><imposto><pis><pisqtde> *************)
  begin
    i := NFe.Det.Count - 1;
    (*Q06*)NFe.Det[i].Imposto.PIS.CST := StrToCSTPIS(ok, LerCampo(tcStr, 'CST'));
    (*Q10*)NFe.Det[i].Imposto.PIS.qBCProd := LerCampo(tcDe4, 'qBCProd');
    (*Q11*)NFe.Det[i].Imposto.PIS.vAliqProd := LerCampo(tcDe4, 'vAliqProd');
    (*Q09*)NFe.Det[i].Imposto.PIS.vPIS := LerCampo(tcDe2, 'vPIS');
  end;

  if ID = 'Q04' then (* Grupo da TAG <det><imposto><pis><pisNT> *************)
  begin
    i := NFe.Det.Count - 1;
    (*Q06*)NFe.Det[i].Imposto.PIS.CST := StrToCSTPIS(ok, LerCampo(tcStr, 'CST'));
  end;

  if ID = 'Q05' then (* Grupo da TAG <det><imposto><pis><pisPOutr> *************)
  begin
    i := NFe.Det.Count - 1;
    (*Q06*)NFe.Det[i].Imposto.PIS.CST := StrToCSTPIS(ok, LerCampo(tcStr, 'CST'));
    (*Q09*)NFe.Det[i].Imposto.PIS.vPIS := LerCampo(tcDe2, 'vPIS');
  end;

  if ID = 'Q07' then (* Grupo da TAG <det><imposto><pis><pisqtde> *************)
  begin
    i := NFe.Det.Count - 1;
    (*Q07*)NFe.Det[i].Imposto.PIS.vBC := LerCampo(tcDe2, 'vBC');
    (*Q08*)NFe.Det[i].Imposto.PIS.pPIS := LerCampo(tcDe2, 'pPIS');
  end;

  if ID = 'Q10' then (* Grupo da TAG <det><imposto><pis><pisqtde> *************)
  begin
    i := NFe.Det.Count - 1;
    (*Q10*)NFe.Det[i].Imposto.PIS.qBCProd := LerCampo(tcDe4, 'qBCProd');
    (*Q11*)NFe.Det[i].Imposto.PIS.vAliqProd := LerCampo(tcDe4, 'vAliqProd');
  end;

  if ID = 'R' then (* Grupo da TAG <det><imposto><pisST> **********************)
  begin
    i := NFe.Det.Count - 1;
    (*R06*)NFe.Det[i].Imposto.PISST.vPIS := LerCampo(tcDe2, 'vPIS');
  end;

  if ID = 'R02' then (* Grupo da TAG <det><imposto><pisST> ********************)
  begin
    i := NFe.Det.Count - 1;
    (*R02*)NFe.Det[i].Imposto.PISST.vBc := LerCampo(tcDe2, 'vBC');
    (*R03*)NFe.Det[i].Imposto.PISST.pPis := LerCampo(tcDe2, 'pPIS');
  end;

  if ID = 'R04' then (* Grupo da TAG <det><imposto><pisST> ********************)
  begin
    i := NFe.Det.Count - 1;
    (*R04*)NFe.Det[i].Imposto.PISST.qBCProd := LerCampo(tcDe4, 'qBCProd');
    (*R05*)NFe.Det[i].Imposto.PISST.vAliqProd := LerCampo(tcDe4, 'vAliqProd');
  end;

  if ID = 'S02' then (* Grupo da TAG <det><imposto><COFINS> *******************)
  begin
    i := NFe.Det.Count - 1;
    (*S06*)NFe.Det[i].Imposto.COFINS.CST := StrToCSTCOFINS(ok, LerCampo(tcStr, 'CST'));
    (*S07*)NFe.Det[i].Imposto.COFINS.vBC := LerCampo(tcDe2, 'vBC');
    (*S08*)NFe.Det[i].Imposto.COFINS.pCOFINS := LerCampo(tcDe2, 'pCOFINS');
    (*S11*)NFe.Det[i].Imposto.COFINS.vCOFINS := LerCampo(tcDe2, 'vCOFINS');
  end;

  if ID = 'S03' then (* Grupo da TAG <det><imposto><COFINS> *******************)
  begin
    i := NFe.Det.Count - 1;
    (*S06*)NFe.Det[i].Imposto.COFINS.CST := StrToCSTCOFINS(ok, LerCampo(tcStr, 'CST'));
    (*S07*)NFe.Det[i].Imposto.COFINS.vBC := LerCampo(tcDe2, 'vBC');
    (*S09*)NFe.Det[i].Imposto.COFINS.qBCProd := LerCampo(tcDe4, 'qBCProd');
    (*S10*)NFe.Det[i].Imposto.COFINS.vAliqProd := LerCampo(tcDe4, 'vAliqProd');
    (*S11*)NFe.Det[i].Imposto.COFINS.vCOFINS := LerCampo(tcDe2, 'vCOFINS');
  end;

  if ID = 'S04' then (* Grupo da TAG <det><imposto><COFINS> *******************)
  begin
    i := NFe.Det.Count - 1;
    (*S06*)NFe.Det[i].Imposto.COFINS.CST := StrToCSTCOFINS(ok, LerCampo(tcStr, 'CST'));
  end;

  if ID = 'S05' then (* Grupo da TAG <det><imposto><COFINS> *******************)
  begin
    i := NFe.Det.Count - 1;
    (*S06*)NFe.Det[i].Imposto.COFINS.CST := StrToCSTCOFINS(ok, LerCampo(tcStr, 'CST'));
    (*S11*)NFe.Det[i].Imposto.COFINS.vCOFINS := LerCampo(tcDe2, 'vCOFINS');
  end;

  if ID = 'S07' then (* Grupo da TAG <det><imposto><COFINS> *******************)
  begin
    i := NFe.Det.Count - 1;
    (*S07*)NFe.Det[i].Imposto.COFINS.vBC := LerCampo(tcDe2, 'vBC');
    (*S08*)NFe.Det[i].Imposto.COFINS.pCOFINS := LerCampo(tcDe2, 'pCOFINS');
  end;

  if ID = 'S09' then (* Grupo da TAG <det><imposto><COFINS> *******************)
  begin
    i := NFe.Det.Count - 1;
    (*S09*)NFe.Det[i].Imposto.COFINS.qBCProd := LerCampo(tcDe4, 'qBCProd');
    (*S10*)NFe.Det[i].Imposto.COFINS.vAliqProd := LerCampo(tcDe4, 'vAliqProd');
  end;

  if ID = 'T' then (* Grupo da TAG <det><imposto><COFINSST> *******************)
  begin
    i := NFe.Det.Count - 1;
    (*T06*)NFe.Det[i].Imposto.COFINSST.vCOFINS := LerCampo(tcDe2, 'vCOFINS');
  end;

  if ID = 'T02' then (* Grupo da TAG <det><imposto><COFINSST> *****************)
  begin
    i := NFe.Det.Count - 1;
    (*T02*)NFe.Det[i].Imposto.COFINSST.vBC := LerCampo(tcDe2, 'vBC');
    (*T03*)NFe.Det[i].Imposto.COFINSST.pCOFINS := LerCampo(tcDe2, 'pCOFINS');
  end;

  if ID = 'T04' then (* Grupo da TAG <det><imposto><COFINSST> *****************)
  begin
    i := NFe.Det.Count - 1;
    (*T04*)NFe.Det[i].Imposto.COFINSST.qBCProd := LerCampo(tcDe4, 'qBCProd');
    (*T05*)NFe.Det[i].Imposto.COFINSST.vAliqProd := LerCampo(tcDe4, 'vAliqProd');
  end;

  if ID = 'U' then (* Grupo da TAG <det><imposto><ISSQN> **********************)
  begin
    i := NFe.Det.Count - 1;
    (*U02*)NFe.Det[i].Imposto.ISSQN.vBC := LerCampo(tcDe2, 'vBC');
    (*U03*)NFe.Det[i].Imposto.ISSQN.vAliq := LerCampo(tcDe2, 'vAliq');
    (*U04*)NFe.Det[i].Imposto.ISSQN.vISSQN := LerCampo(tcDe2, 'vISSQN');
    (*U05*)NFe.Det[i].Imposto.ISSQN.cMunFG := LerCampo(tcInt, 'cMunFG');
    (*U06*)NFe.Det[i].Imposto.ISSQN.cListServ := LerCampo(tcInt, 'cListServ');
    (*U07*)NFe.Det[i].Imposto.ISSQN.cSitTrib := StrToISSQNcSitTrib(Ok, LerCampo(tcStr, 'cSitTrib'));
  end;

  if ID = 'W02' then (* Grupo da TAG <total><ICMSTot> *************************)
  begin
    (*W03*)NFe.Total.ICMSTot.vBC := LerCampo(tcDe2, 'vBC');
    (*W04*)NFe.Total.ICMSTot.vICMS := LerCampo(tcDe2, 'vICMS');
    (*W05*)NFe.Total.ICMSTot.vBCST := LerCampo(tcDe2, 'vBCST');
    (*W06*)NFe.Total.ICMSTot.vST := LerCampo(tcDe2, 'vST');
    (*W07*)NFe.Total.ICMSTot.vProd := LerCampo(tcDe2, 'vProd');
    (*W08*)NFe.Total.ICMSTot.vFrete := LerCampo(tcDe2, 'vFrete');
    (*W09*)NFe.Total.ICMSTot.vSeg := LerCampo(tcDe2, 'vSeg');
    (*W10*)NFe.Total.ICMSTot.vDesc := LerCampo(tcDe2, 'vDesc');
    (*W11*)NFe.Total.ICMSTot.vII := LerCampo(tcDe2, 'vII');
    (*W12*)NFe.Total.ICMSTot.vIPI := LerCampo(tcDe2, 'vIPI');
    (*W13*)NFe.Total.ICMSTot.vPIS := LerCampo(tcDe2, 'vPIS');
    (*W14*)NFe.Total.ICMSTot.vCOFINS := LerCampo(tcDe2, 'vCOFINS');
    (*W15*)NFe.Total.ICMSTot.vOutro := LerCampo(tcDe2, 'vOutro');
    (*W16*)NFe.Total.ICMSTot.vNF := LerCampo(tcDe2, 'vNF');
  end;

  if ID = 'W17' then (* Grupo da TAG <total><ISSQNtot> ************************)
  begin
    (*W18*)NFe.Total.ISSQNtot.vServ := LerCampo(tcDe2, 'vServ');
    (*W19*)NFe.Total.ISSQNtot.vBC := LerCampo(tcDe2, 'vBC');
    (*W20*)NFe.Total.ISSQNtot.vISS := LerCampo(tcDe2, 'vISS');
    (*W21*)NFe.Total.ISSQNtot.vPIS := LerCampo(tcDe2, 'vPIS');
    (*W22*)NFe.Total.ISSQNtot.vCOFINS := LerCampo(tcDe2, 'vCOFINS');
  end;

  if ID = 'W23' then (* Grupo da TAG <total><retTrib> *************************)
  begin
    (*W24*)NFe.Total.retTrib.vRetPIS := LerCampo(tcDe2, 'vRetPIS');
    (*W25*)NFe.Total.retTrib.vRetCOFINS := LerCampo(tcDe2, 'vRetCOFINS');
    (*W26*)NFe.Total.retTrib.vRetCSLL := LerCampo(tcDe2, 'vRetCSLL');
    (*W27*)NFe.Total.retTrib.vBCIRRF := LerCampo(tcDe2, 'vBCIRRF');
    (*W28*)NFe.Total.retTrib.vIRRF := LerCampo(tcDe2, 'vIRRF');
    (*W29*)NFe.Total.retTrib.vBCRetPrev := LerCampo(tcDe2, 'vBCRetPrev');
    (*W30*)NFe.Total.retTrib.vRetPrev := LerCampo(tcDe2, 'vRetPrev');
  end;

  if ID = 'X' then (* Grupo da TAG <transp> ***********************************)
    (*X02*)NFe.Transp.modFrete := StrTomodFrete(ok, LerCampo(tcStr, 'modFrete'));

  if ID = 'X03' then (* Grupo da TAG <transp><TRansportadora> *****************)
  begin
    (*X06*)NFe.Transp.Transporta.xNome := LerCampo(tcStr, 'xNome');
    (*X07*)NFe.Transp.Transporta.IE := LerCampo(tcStr, 'IE');
    (*X08*)NFe.Transp.Transporta.xEnder := LerCampo(tcStr, 'xEnder');
    (*X09*)NFe.Transp.Transporta.xMun := LerCampo(tcStr, 'xMun');
    (*X10*)NFe.Transp.Transporta.UF := LerCampo(tcStr, 'UF');
  end;
  if ID = 'X04' then
    (*X04*)NFe.Transp.Transporta.CNPJCPF := LerCampo(tcStr, 'CNPJ');
  if ID = 'X05' then
    (*X05*)NFe.Transp.Transporta.CNPJCPF := LerCampo(tcStr, 'CPF');

  if ID = 'X11' then (* Grupo da TAG <transp><retTransp> **********************)
  begin
    (*X12*)NFe.Transp.retTransp.vServ := LerCampo(tcDe2, 'vServ');
    (*X13*)NFe.Transp.retTransp.vBCRet := LerCampo(tcDe2, 'vBCRet');
    (*X14*)NFe.Transp.retTransp.pICMSRet := LerCampo(tcDe2, 'pICMSRet');
    (*X15*)NFe.Transp.retTransp.vICMSRet := LerCampo(tcDe2, 'vICMSRet');
    (*X16*)NFe.Transp.retTransp.CFOP := LerCampo(tcEsp, 'CFOP');
    (*X17*)NFe.Transp.retTransp.cMunFG := LerCampo(tcStr, 'cMunFG');
  end;

  if ID = 'X18' then (* Grupo da TAG <transp><veicTransp> *********************)
  begin
    (*X19*)NFe.Transp.veicTransp.placa := LerCampo(tcStr, 'placa');
    (*X20*)NFe.Transp.veicTransp.UF := LerCampo(tcStr, 'UF');
    (*X21*)NFe.Transp.veicTransp.RNTC := LerCampo(tcStr, 'RNTC');
  end;

  if ID = 'X22' then (* Grupo da TAG <transp><reboque> ************************)
  begin
    NFe.Transp.Reboque.add;
    i := NFe.Transp.Reboque.Count - 1;
    (*X23*)NFe.Transp.Reboque[i].placa := LerCampo(tcStr, 'placa');
    (*X24*)NFe.Transp.Reboque[i].UF := LerCampo(tcStr, 'UF');
    (*X25*)NFe.Transp.Reboque[i].RNTC := LerCampo(tcStr, 'RNTC');
  end;

  if ID = 'X26' then (* Grupo da TAG <transp><vol> ****************************)
  begin
    NFe.Transp.Vol.add;
    i := NFe.Transp.Vol.Count - 1;
    (*X27*)NFe.Transp.Vol[i].qVol := LerCampo(tcInt, 'qVol');
    (*X28*)NFe.Transp.vol[i].esp := LerCampo(tcStr, 'esp');
    (*X29*)NFe.Transp.Vol[i].marca := LerCampo(tcStr, 'marca');
    (*X30*)NFe.Transp.Vol[i].nVol := LerCampo(tcStr, 'nVol');
    (*X31*)NFe.Transp.Vol[i].pesoL := LerCampo(tcDe3, 'pesoL');
    (*X32*)NFe.Transp.Vol[i].pesoB := LerCampo(tcDe3, 'pesoB');
  end;

  if ID = 'X33' then (* Grupo da TAG <transp><vol><lacres> ********************)
  begin
    i := NFe.Transp.Vol.Count - 1;
    NFe.transp.Vol[i].lacres.add;
    j := NFe.transp.Vol[i].lacres.Count - 1;
    (*X34*)NFe.transp.Vol[i].lacres[j].nLacre := LerCampo(tcStr, 'nLacre');
  end;

  if ID = 'Y02' then (* Grupo da TAG <cobr> ***********************************)
  begin
    (*Y03*)NFe.Cobr.Fat.nFat := LerCampo(tcStr, 'nFat');
    (*Y04*)NFe.Cobr.Fat.vOrig := LerCampo(tcDe2, 'vOrig');
    (*Y05*)NFe.Cobr.Fat.vDesc := LerCampo(tcDe2, 'vDesc');
    (*Y06*)NFe.Cobr.Fat.vLiq := LerCampo(tcDe2, 'vLiq');
  end;

  if ID = 'Y07' then (* Grupo da TAG <cobr><dup> ******************************)
  begin
    NFe.Cobr.Dup.Add;
    i := NFe.Cobr.Dup.Count - 1;
    (*Y08*)NFe.Cobr.Dup[i].nDup := LerCampo(tcStr, 'nDup');
    (*Y09*)NFe.Cobr.Dup[i].dVenc := LerCampo(tcDat, 'dVenc');
    (*Y10*)NFe.Cobr.Dup[i].vDup := LerCampo(tcDe2, 'vDup');
  end;

  if ID = 'Z' then (* Grupo da TAG <InfAdic> **********************************)
  begin
    (*Z02*)NFe.InfAdic.infAdFisco := LerCampo(tcStr, 'infAdFisco');
    (*Z03*)NFe.InfAdic.infCpl := LerCampo(tcStr, 'infCpl');
  end;

  if ID = 'Z04' then (* Grupo da TAG <infAdic><obsCont> ***********************)
  begin
    NFe.InfAdic.obsCont.Add;
    i := NFe.InfAdic.obsCont.Count - 1;
    (*Z05*)NFe.InfAdic.obsCont[i].xCampo := LerCampo(tcStr, 'xCampo');
    (*Z06*)NFe.InfAdic.obsCont[i].xTexto := LerCampo(tcStr, 'xTexto');
  end;

  if ID = 'Z07' then (* Grupo da TAG <infAdic><obsFisco> **********************)
  begin
    NFe.InfAdic.obsFisco.Add;
    i := NFe.InfAdic.obsFisco.Count - 1;
    (*Z08*)NFe.InfAdic.obsFisco[i].xCampo := LerCampo(tcStr, 'xCampo');
    (*Z09*)NFe.InfAdic.obsFisco[i].xTexto := LerCampo(tcStr, 'xTexto');
  end;

  if ID = 'Z10' then (* Grupo da TAG <infAdic><procRef> ***********************)
  begin
    NFe.InfAdic.procRef.Add;
    i := NFe.InfAdic.procRef.Count - 1;
    (*Z11*)NFe.InfAdic.procRef[i].nProc := LerCampo(tcStr, 'nProc');
    (*Z12*)NFe.InfAdic.procRef[i].indProc := StrToindProc(ok, LerCampo(tcStr, 'indProc'));
  end;

  if ID = 'ZA' then (* Grupo da TAG <exporta> *********************************)
  begin
    (*ZA02*)NFe.exporta.UFembarq := LerCampo(tcStr, 'UFembarq');
    (*ZA03*)NFe.exporta.xLocEmbarq := LerCampo(tcStr, 'xLocEmbarq');
  end;

  if ID = 'ZB' then (* Grupo da TAG <compra> **********************************)
  begin
    (*ZB02*)NFe.compra.xNEmp := LerCampo(tcStr, 'xNEmp');
    (*ZB03*)NFe.compra.xPed := LerCampo(tcStr, 'xPed');
    (*ZB04*)NFe.compra.xCont := LerCampo(tcStr, 'xCont');
  end;

end;

end.

