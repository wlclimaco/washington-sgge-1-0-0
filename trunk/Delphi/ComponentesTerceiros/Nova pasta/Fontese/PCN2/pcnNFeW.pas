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

{******************************************************************************
|* Historico
|*
|* 24/09/2012: Italo Jurisato Junior
|*  - Altera��es para funcionamento com NFC-e
******************************************************************************}
unit pcnNFeW;

interface uses

  SysUtils, Classes,
  pcnAuxiliar, pcnConversao, pcnGerador, pcnNFe, pcnLayoutTXT;

type

  TGeradorOpcoes = class;

  TNFeW = class(TPersistent)
  private
    FGerador: TGerador;
    FNFe: TNFe;
    FSchema: TpcnSchema;
    FOpcoes: TGeradorOpcoes;
    procedure GerarInfNFe;
    procedure GerarIde;
    procedure GerarIdeNFref;
    procedure GerarIdeNFrerefNFe(const i: integer);
    procedure GerarIdeNFrefRefNF(const i: integer);
    procedure GerarRefNFP(const i: integer);
    procedure GerarIdeNFrerefCTe(const i: integer);
    procedure GerarRefECF(const i: integer);
    procedure GerarEmit;
    procedure GerarEmitEnderEmit;
    procedure GerarAvulsa;
    procedure GerarDest;
    procedure GerarDestEnderDest(var UF: string);
    procedure GerarRetirada;
    procedure GerarEntrega;
    procedure GerarDet;
    procedure GerarDetProd(const i: integer);
    procedure GerarDetProdDI(const i: integer);
    procedure GerarDetProdDIadi(const i, j: integer);
    procedure GerarDetProdVeicProd(const i: integer);
    procedure GerarDetProdMed(const i: integer);
    procedure GerarDetProdArma(const i: integer);
    procedure GerarDetProdComb(const i: integer);
    procedure GerarDetProdCombCIDE(const i: integer);
    procedure GerarDetProdCombICMS(const i: integer);
    procedure GerarDetProdCombICMSInter(const i: integer);
    procedure GerarDetProdCombICMSCons(const i: integer);
    procedure GerarDetImposto(const i: integer);
    procedure GerarDetImpostoICMS(const i: integer);
    procedure GerarDetImpostoIPI(const i: integer);
    procedure GerarDetImpostoII(const i: integer);
    procedure GerarDetImpostoPIS(const i: integer);
    procedure GerarDetImpostoPISST(const i: integer);
    procedure GerarDetImpostoCOFINS(const i: integer);
    procedure GerarDetImpostoCOFINSST(const i: integer);
    procedure GerarDetImpostoISSQN(const i: integer);
    procedure GerarTotal;
    procedure GerarTotalICMSTotal;
    procedure GerarTotalISSQNtot;
    procedure GerarTotalretTrib;
    procedure GerarCobr;
    procedure GerarCobrFat;
    procedure GerarCobrDup;
    procedure Gerarpag;
    procedure GerarTransp;
    procedure GerarTranspTransporta;
    procedure GerarTranspRetTransp;
    procedure GerarTranspVeicTransp;
    procedure GerarTranspReboque;
    procedure GerarTranspVol;
    procedure GerarTranspVolLacres(i: integer);
    procedure GerarInfAdic;
    procedure GerarInfAdicObsCont;
    procedure GerarInfAdicObsFisco;
    procedure GerarInfAdicProcRef;
    procedure GerarExporta;
    procedure GerarCompra;
    procedure GerarCana;
    procedure GerarforDia;
    procedure GerarDeduc;

    //
    procedure AjustarMunicipioUF(var xUF: string; var xMun: string; var cMun: integer; cPais: integer; vxUF, vxMun: string; vcMun: integer);
    function ObterNomeMunicipio(const xMun, xUF: string; const cMun: integer): string;
  public
    constructor Create(AOwner: TNFe);
    destructor Destroy; override;
    function GerarXml: boolean;
    function ObterNomeArquivo: string;
  published
    property Gerador: TGerador read FGerador write FGerador;
    property NFe: TNFe read FNFe write FNFe;
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

{ TNFeW }

constructor TNFeW.Create(AOwner: TNFe);
begin
  FNFe := AOwner;
  FGerador := TGerador.Create;
  FGerador.FIgnorarTagNivel := '|?xml version|NFe xmlns|infNFe versao|obsCont|obsFisco|';
  FOpcoes := TGeradorOpcoes.Create;
  FOpcoes.FAjustarTagNro := True;
  FOpcoes.FGerarTXTSimultaneamente := False;
  FOpcoes.FGerarTagIPIparaNaoTributado := True;
  FOpcoes.FNormatizarMunicipios := False;
  FOpcoes.FGerarTagAssinatura := taSomenteSeAssinada;
  FOpcoes.FValidarInscricoes := False;
  FOpcoes.FValidarListaServicos := False;
end;

destructor TNFeW.Destroy;
begin
  FGerador.Free;
  FOpcoes.Free;
  inherited Destroy;
end;

////////////////////////////////////////////////////////////////////////////////

function TNFeW.ObterNomeArquivo: string;
begin
  Result := SomenteNumeros(nfe.infNFe.ID) + '-nfe.xml';
end;

function TNFeW.GerarXml: boolean;
var
  chave: AnsiString;
  Gerar: boolean;
  xProtNFe : String;
begin
  chave := '';
  if NFe.infNFe.Versao >= 2 then
   begin
     FSchema := TsPL006;
     if not GerarChave(Chave, nfe.ide.cUF, nfe.ide.cNF, nfe.ide.modelo, nfe.ide.serie,
       nfe.ide.nNF, StrToInt(TpEmisToStr(nfe.ide.tpEmis)), nfe.ide.dEmi, nfe.emit.CNPJCPF) then
       Gerador.wAlerta('A01', 'infNFe', DSC_CHAVE, ERR_MSG_GERAR_CHAVE);
   end
  else
   begin
     FSchema := TsPL005c;
     if not GerarChaveCTe(chave, nfe.ide.cUF, nfe.ide.cNF, nfe.ide.modelo, nfe.ide.serie,
       nfe.ide.nNF, nfe.ide.dEmi, nfe.emit.CNPJCPF) then
       Gerador.wAlerta('A01', 'infNFe', DSC_CHAVE, ERR_MSG_GERAR_CHAVE);
   end;

  if (Trim(nfe.infNFe.ID) = '') or (not ValidarChave(nfe.infNFe.ID)) then
     nfe.infNFe.ID := chave
  else
   begin
     NFe.infNFe.ID := StringReplace( UpperCase(NFe.infNFe.ID), 'NFE', '', [rfReplaceAll] ) ;;
     NFe.infNFe.ID := 'NFe'+NFe.infNFe.ID;
   end;
   
  nfe.ide.cDV := RetornarDigito(nfe.infNFe.ID);
  nfe.Ide.cNF := RetornarCodigoNumerico(nfe.infNFe.ID,NFe.infNFe.Versao);
  // Carrega Layout que sera utilizado para gera o txt
  Gerador.LayoutArquivoTXT.Clear;
  if FOpcoes.GerarTXTSimultaneamente then
    Gerador.LayoutArquivoTXT.Text := CarregarLayoutTXT(RetornarVersaoLayout(FSchema, tlNFe));
  //
  Gerador.ArquivoFormatoXML := '';
  Gerador.ArquivoFormatoTXT := '';
//  Gerador.wGrupo(ENCODING_UTF8_STD, '', False);
  if nfe.procNFe.nProt <> '' then
   begin
      Gerador.wGrupo(ENCODING_UTF8, '', False);
//      Gerador.wGrupo('nfeProc versao="' + NFe.infNFe.VersaoStr  + '" ' + NAME_SPACE, '');
      Gerador.wGrupo('nfeProc ' + NFe.infNFe.VersaoStr + ' ' + NAME_SPACE, '');
//      Gerador.wGrupo('nfeProc ' + V2_00 + ' ' + NAME_SPACE, '');
   end;
  Gerador.wGrupo('NFe ' + NAME_SPACE);
  Gerador.wGrupo('infNFe ' + NFe.infNFe.VersaoStr + ' Id="' + nfe.infNFe.ID + '"');
  (**)GerarInfNFe;
  Gerador.wGrupo('/infNFe');
  //
  if FOpcoes.GerarTagAssinatura <> taNunca then
  begin
    Gerar := true;
    if FOpcoes.GerarTagAssinatura = taSomenteSeAssinada then
      Gerar := ((NFe.signature.DigestValue <> '') and (NFe.signature.SignatureValue <> '') and (NFe.signature.X509Certificate <> ''));
    if FOpcoes.GerarTagAssinatura = taSomenteParaNaoAssinada then
      Gerar := ((NFe.signature.DigestValue = '') and (NFe.signature.SignatureValue = '') and (NFe.signature.X509Certificate = ''));
    if Gerar then
    begin
      FNFe.signature.URI := somenteNumeros(NFe.infNFe.ID);
      FNFe.signature.Gerador.Opcoes.IdentarXML := Gerador.Opcoes.IdentarXML;
      FNFe.signature.GerarXML;
      Gerador.ArquivoFormatoXML := Gerador.ArquivoFormatoXML + FNFe.signature.Gerador.ArquivoFormatoXML;
    end;
  end;
  Gerador.wGrupo('/NFe');

  if nfe.procNFe.nProt <> '' then
   begin
     xProtNFe :=
//       (**)'<protNFe versao="2.00">' +
       (**)'<protNFe ' + NFe.infNFe.VersaoStr + '>' +
     (******)'<infProt>'+
     (*********)'<tpAmb>'+TpAmbToStr(nfe.procNFe.tpAmb)+'</tpAmb>'+
     (*********)'<verAplic>'+nfe.procNFe.verAplic+'</verAplic>'+
     (*********)'<chNFe>'+nfe.procNFe.chNFe+'</chNFe>'+
     (*********)'<dhRecbto>'+FormatDateTime('yyyy-mm-dd"T"hh:nn:ss',nfe.procNFe.dhRecbto)+'</dhRecbto>'+
     (*********)'<nProt>'+nfe.procNFe.nProt+'</nProt>'+
     (*********)'<digVal>'+nfe.procNFe.digVal+'</digVal>'+
     (*********)'<cStat>'+IntToStr(nfe.procNFe.cStat)+'</cStat>'+
     (*********)'<xMotivo>'+nfe.procNFe.xMotivo+'</xMotivo>'+
     (******)'</infProt>'+
     {****}'</protNFe>';

     (**)Gerador.wTexto(xProtNFe);
     Gerador.wGrupo('/nfeProc');
   end;
  Gerador.gtAjustarRegistros(nfe.infNFe.ID);
  Result := (Gerador.ListaDeAlertas.Count = 0);
end;

procedure TNFeW.GerarInfNFe;
begin
  GerarIde;
  GerarEmit;
  GerarAvulsa;

  if (nfe.Dest.CNPJCPF <> '') or
     (nfe.Dest.idEstrangeiro <> '') or
     (nfe.Ide.modelo <> 65) then
     GerarDest;

  GerarRetirada;
  GerarEntrega;
  GerarDet;
  GerarTotal;
  GerarTransp;
  GerarCobr;

  if (nfe.infNFe.Versao >= 3) and (nfe.Ide.modelo = 65) then 
     Gerarpag;

  GerarInfAdic;
  GerarExporta;
  GerarCompra;
  GerarCana;
end;

procedure TNFeW.GerarIde;
begin
  Gerador.wGrupo('ide', 'B01');
  Gerador.wCampo(tcInt, 'B02', 'cUF    ', 02, 02, 1, nfe.ide.cUF, DSC_CUF);
  if not ValidarCodigoUF(nfe.ide.cUF) then Gerador.wAlerta('B02', 'cUF', DSC_CUF, ERR_MSG_INVALIDO);
  if nfe.infNFe.Versao < 2 then
     Gerador.wCampo(tcStr, 'B03', 'cNF    ', 09, 09, 1, IntToStrZero(RetornarCodigoNumerico(nfe.infNFe.ID,nfe.infNFe.Versao), 9), DSC_CNF)          
  else
     Gerador.wCampo(tcStr, 'B03', 'cNF    ', 08, 08, 1, IntToStrZero(RetornarCodigoNumerico(nfe.infNFe.ID,nfe.infNFe.Versao), 8), DSC_CNF);     
  Gerador.wCampo(tcStr, 'B04', 'natOp  ', 01, 60, 1, nfe.ide.natOp, DSC_NATOP);
  Gerador.wCampo(tcStr, 'B05', 'indPag ', 01, 01, 1, IndpagToStr(nfe.ide.indPag), DSC_INDPAG);
  Gerador.wCampo(tcInt, 'B06', 'mod    ', 02, 02, 1, nfe.ide.modelo, DSC_MOD);
  Gerador.wCampo(tcInt, 'B07', 'serie  ', 01, 03, 1, nfe.ide.serie, DSC_SERIE);
  Gerador.wCampo(tcInt, 'B08', 'nNF    ', 01, 09, 1, nfe.ide.nNF, DSC_NNF);

  if nfe.infNFe.Versao >= 3 then
   begin
     Gerador.wCampo(tcStr, 'B09', 'dhEmi   ', 10, 10, 1, DateTimeTodh(nfe.ide.dEmi) + GetUTC(CodigoParaUF(nfe.ide.cUF), nfe.ide.dEmi), DSC_DEMI);
     if nfe.ide.modelo = 55 then
       Gerador.wCampo(tcStr, 'B10', 'dhSaiEnt', 10, 10, 0, DateTimeTodh(nfe.ide.dSaiEnt) + GetUTC(CodigoParaUF(nfe.ide.cUF), nfe.ide.dSaiEnt), DSC_DSAIENT);
   end
  else
   begin
     Gerador.wCampo(tcDat, 'B09', 'dEmi   ', 10, 10, 1, nfe.ide.dEmi, DSC_DEMI);
     Gerador.wCampo(tcDat, 'B10', 'dSaiEnt', 10, 10, 0, nfe.ide.dSaiEnt, DSC_DSAIENT);
     if nfe.Ide.dSaiEnt>0 then
       Gerador.wCampo(tcHor, 'B10a','hSaiEnt', 08, 08, 0, nfe.ide.hSaiEnt, DSC_HSAIENT);
   end;

  Gerador.wCampo(tcStr, 'B11', 'tpNF   ', 01, 01, 1, tpNFToStr(nfe.ide.tpNF), DSC_TPNF);

  if nfe.infNFe.Versao >= 3 then  //Verificar
   begin
    if nfe.ide.modelo = 55 then 
       Gerador.wCampo(tcStr, 'B11a', 'idDest', 01, 01, 1, DestinoOperacaoToStr(nfe.Ide.idDest), 'erro')
    else 
       Gerador.wCampo(tcStr, 'B11a', 'idDest', 01, 01, 1, '1', 'erro');
   end;

  Gerador.wCampo(tcInt, 'B12', 'cMunFG ', 07, 07, 1, nfe.ide.cMunFG, DSC_CMUNFG);
  if not ValidarMunicipio(nfe.ide.cMunFG) then Gerador.wAlerta('B12', 'cMunFG', DSC_CMUNFG, ERR_MSG_INVALIDO);
  (**)GerarIdeNFref;
  Gerador.IDNivel := 'B01';
  Gerador.wCampo(tcStr, 'B21', 'tpImp  ', 01, 01, 1, tpImpToStr(nfe.Ide.tpImp), DSC_TPIMP);
  Gerador.wCampo(tcStr, 'B22', 'tpEmis ', 01, 01, 1, tpEmisToStr(nfe.Ide.tpEmis), DSC_TPEMIS);
  Gerador.wCampo(tcInt, 'B23', 'cDV    ', 01, 01, 1, nfe.Ide.cDV, DSC_CDV);
  Gerador.wCampo(tcStr, 'B24', 'tpAmb  ', 01, 01, 1, tpAmbToStr(nfe.Ide.tpAmb), DSC_TPAMB);
  Gerador.wCampo(tcStr, 'B25', 'finNFe ', 01, 01, 1, finNFeToStr(nfe.Ide.finNFe), DSC_FINNFE);

  if nfe.infNFe.Versao >= 3 then
   begin
    Gerador.wCampo(tcStr, 'B25a', 'indFinal', 01, 01, 1, ConsumidorFinalToStr(nfe.Ide.indFinal), 'erro');
    Gerador.wCampo(tcStr, 'B25b', 'indPres ', 01, 01, 1, PresencaCompradorToStr(nfe.Ide.indPres), 'erro');
   end;

  Gerador.wCampo(tcStr, 'B26', 'procEmi', 01, 01, 1, procEmiToStr(nfe.Ide.procEmi), DSC_PROCEMI);
  Gerador.wCampo(tcStr, 'B27', 'verProc', 01, 20, 1, nfe.Ide.verProc, DSC_VERPROC);

  if nfe.Ide.dhCont > 0 then 
   begin
    if nfe.infNFe.Versao >= 3 then 
       Gerador.wCampo(tcStr, 'B28', 'dhCont ', 19, 19, 0, DateTimeTodh(nfe.ide.dhCont) + GetUTC(CodigoParaUF(nfe.ide.cUF), nfe.ide.dhCont), DSC_DHCONT)
    else 
       Gerador.wCampo(tcStr, 'B28', 'dhCont ', 19, 19, 0, DateTimeTodh(nfe.Ide.dhCont), DSC_DHCONT);
   end;

  Gerador.wCampo(tcStr, 'B29', 'xJust  ', 01,256, 0, nfe.ide.xJust, DSC_XJUSTCONT);
  Gerador.wGrupo('/ide');
end;

procedure TNFeW.GerarIdeNFref;
var
  i: integer;
begin
  // Gera TAGs se N�O for uma NFe refer�ncia
  for i := 0 to nfe.ide.NFref.Count - 1 do
  begin
    Gerador.wGrupo('NFref', 'B12a');
    if nfe.ide.NFref[i].refNFe      <> '' then (**)GerarIdeNFrerefNFe(i);
    if nfe.Ide.NFref[i].RefNF.nNF    >  0 then (**)GerarIdeNFrefRefNF(i);
    if nfe.ide.NFref[i].RefNFP.nNF   >  0 then (**)GerarRefNFP(i);
    if nfe.ide.NFref[i].refCTe      <> '' then (**)GerarIdeNFrerefCTe(i);
    if nfe.ide.NFref[i].RefECF.nCOO <> '' then (**)GerarRefECF(i);
    Gerador.wGrupo('/NFref');
  end;
end;

procedure TNFeW.GerarIdeNFrerefNFe(const i: integer);
begin
  Gerador.wCampo(tcEsp, 'B13', 'refNFe', 44, 44, 1, SomenteNumeros(nfe.ide.NFref[i].refNFe), DSC_REFNFE);
  if not ValidarChave('NFe' + SomenteNumeros(nfe.ide.NFref[i].refNFe)) then Gerador.wAlerta('B13', 'refNFe', DSC_REFNFE, ERR_MSG_INVALIDO);
end;

procedure TNFeW.GerarIdeNFrefRefNF(const i: integer);
begin
  Gerador.wGrupo('refNF', 'B14');
  Gerador.wCampo(tcInt, 'B15', 'cUF   ', 02, 02, 1, nfe.Ide.NFref[i].RefNF.cUF, DSC_CUF);
  if not ValidarCodigoUF(nfe.Ide.NFref[i].RefNF.cUF) then Gerador.wAlerta('B15', 'cUF', DSC_CUF, ERR_MSG_INVALIDO);
  Gerador.wCampo(tcEsp, 'B16', 'AAMM  ', 04, 04, 1, nfe.Ide.NFref[i].RefNF.AAMM, DSC_AAMM);
  if not ValidarAAMM(nfe.Ide.NFref[i].RefNF.AAMM) then  Gerador.wAlerta('B16', 'AAMM', DSC_AAMM, 'Periodo inv�lido');
  Gerador.wCampoCNPJCPF('B17', 'B17', nfe.Ide.NFref[i].RefNF.CNPJ, CODIGO_BRASIL);
  Gerador.wCampo(tcInt, 'B18', 'mod   ', 02, 02, 1, nfe.Ide.NFref[i].RefNF.Modelo, DSC_MOD);
  if not ValidarMod(nfe.Ide.NFref[i].RefNF.Modelo) then Gerador.wAlerta('B18', 'mod', DSC_MOD, 'Modelo de documento inv�lido');
  Gerador.wCampo(tcInt, 'B19', 'serie ', 01, 03, 1, nfe.ide.NFref[i].RefNF.serie, DSC_SERIE);
  Gerador.wCampo(tcInt, 'B20', 'nNF   ', 01, 09, 1, nfe.Ide.NFref[i].RefNF.nNF, DSC_NNF);
  Gerador.wGrupo('/refNF');
end;

procedure TNFeW.GerarRefNFP(const i: integer);
begin
  Gerador.wGrupo('refNFP', 'B20a');
  Gerador.wCampo(tcInt, 'B20b', 'cUF   ', 02, 02, 1, nfe.Ide.NFref[i].RefNFP.cUF, DSC_CUF);
  if not ValidarCodigoUF(nfe.Ide.NFref[i].RefNFP.cUF) then Gerador.wAlerta('B20b', 'cUF', DSC_CUF, ERR_MSG_INVALIDO);
  Gerador.wCampo(tcEsp, 'B20c', 'AAMM  ', 04, 04, 1, nfe.Ide.NFref[i].RefNFP.AAMM, DSC_AAMM);
  if not ValidarAAMM(nfe.Ide.NFref[i].RefNFP.AAMM) then  Gerador.wAlerta('B20c', 'AAMM', DSC_AAMM, 'Periodo inv�lido');
  Gerador.wCampoCNPJCPF('B20d', 'B20e', nfe.Ide.NFref[i].RefNFP.CNPJCPF, nfe.Emit.EnderEmit.cPais);
  Gerador.wCampo(tcStr, 'B20f', 'IE   ', 01, 14, 1, nfe.Ide.NFref[i].RefNFP.IE, DSC_MOD);
  Gerador.wCampo(tcInt, 'B20f', 'mod   ', 02, 02, 1, nfe.Ide.NFref[i].RefNFP.Modelo, DSC_MOD);
  Gerador.wCampo(tcInt, 'B20g', 'serie ', 01, 03, 1, nfe.ide.NFref[i].RefNFP.serie, DSC_SERIE);
  Gerador.wCampo(tcInt, 'B20h', 'nNF   ', 01, 09, 1, nfe.Ide.NFref[i].RefNFP.nNF, DSC_NNF);
  Gerador.wGrupo('/refNFP');
end;

procedure TNFeW.GerarIdeNFrerefCTe(const i: integer);
begin
  Gerador.wCampo(tcEsp, 'B20i', 'refCTe', 44, 44, 1, SomenteNumeros(nfe.ide.NFref[i].refCTe), DSC_REFCTE);
  if not ValidarChave('NFe'+ SomenteNumeros(nfe.ide.NFref[i].refCTe)) then Gerador.wAlerta('B20i', 'refCTe',DSC_REFCTE, ERR_MSG_INVALIDO);
end;


procedure TNFeW.GerarRefECF(const i: integer);
begin
  Gerador.wGrupo('refECF', 'B20j');
  Gerador.wCampo(tcStr, 'B20k', 'mod   ', 02, 02, 1, ECFModRefToStr( nfe.Ide.NFref[i].RefECF.modelo ) , DSC_MOD);
  Gerador.wCampo(tcInt, 'B20l', 'nECF  ', 03, 03, 1, nfe.Ide.NFref[i].RefECF.nECF, DSC_NECF);
  Gerador.wCampo(tcInt, 'B20m', 'nCOO  ', 06, 06, 1, nfe.Ide.NFref[i].RefECF.nCOO, DSC_NCOO);
  Gerador.wGrupo('/refECF');
end;

procedure TNFeW.GerarEmit;
begin
  Gerador.wGrupo('emit', 'C01');
  Gerador.wCampoCNPJCPF('C02', 'C02a', nfe.Emit.CNPJCPF, nfe.Emit.enderEmit.cPais);
  Gerador.wCampo(tcStr, 'C03', 'xNome  ', 02, 60, 1, nfe.Emit.xNome, DSC_XNOME);
  Gerador.wCampo(tcStr, 'C04', 'xFant  ', 01, 60, 0, nfe.Emit.xFant, DSC_XFANT);
  (**)GerarEmitEnderEmit;
  Gerador.IDNivel := 'C01';
  Gerador.wCampo(tcStr, 'C17', 'IE     ', 00, 14, 1, SomenteNumeros(nfe.Emit.IE), DSC_IE);
  if (FOpcoes.ValidarInscricoes) and (nfe.Ide.procEmi <> peAvulsaFisco) then
  begin
    if Length(nfe.Emit.IE) = 0 then
      Gerador.wAlerta('C17', 'IE', DSC_IE, ERR_MSG_VAZIO)
    else
    begin
      if not ValidarIE(nfe.Emit.IE, CodigoParaUF(nfe.Ide.cUF)) then
        Gerador.wAlerta('C17', 'IE', DSC_IE, ERR_MSG_INVALIDO);
    end;
  end;
  Gerador.wCampo(tcStr, 'C18', 'IEST   ', 02, 14, 0, nfe.Emit.IEST, DSC_IEST);
  Gerador.wCampo(tcStr, 'C19', 'IM     ', 01, 15, 0, nfe.Emit.IM, DSC_IM);
  if Length(nfe.Emit.IM) > 0 then
    Gerador.wCampo(tcStr, 'C20', 'CNAE ', 07, 07, 1, nfe.Emit.CNAE, DSC_CNAE); //Est� definido como obrigat�rio porque foi informado IM (Conforme o manual)
  if NFe.infNFe.Versao >= 2 then
     Gerador.wCampo(tcStr, 'C21', 'CRT ', 01, 01, 1, CRTToStr(nfe.Emit.CRT), DSC_CRT);
  Gerador.wGrupo('/emit');
end;

procedure TNFeW.GerarEmitEnderEmit;
var
  cMun: integer;
  xMun: string;
  xUF: string;
begin
  AjustarMunicipioUF(xUF, xMun, cMun, nfe.Emit.enderEmit.cPais, nfe.Emit.enderEmit.UF, nfe.Emit.enderEmit.xMun, nfe.Emit.EnderEmit.cMun);
  Gerador.wGrupo('enderEmit', 'C05');
  Gerador.wCampo(tcStr, 'C06', 'xLgr   ', 02, 60, 1, nfe.Emit.enderEmit.xLgr, DSC_XLGR);
  Gerador.wCampo(tcStr, 'C07', 'nro    ', 01, 60, 1, ExecutarAjusteTagNro(FOpcoes.FAjustarTagNro, nfe.Emit.enderEmit.nro), DSC_NRO);
  Gerador.wCampo(tcStr, 'C08', 'xCpl   ', 01, 60, 0, nfe.Emit.enderEmit.xCpl, DSC_XCPL);
  Gerador.wCampo(tcStr, 'C09', 'xBairro', 02, 60, 1, nfe.Emit.enderEmit.xBairro, DSC_XBAIRRO);
  Gerador.wCampo(tcInt, 'C10', 'cMun   ', 01, 07, 1, cMun, DSC_CMUN);
  if not ValidarMunicipio(nfe.Emit.EnderEmit.cMun) then
    Gerador.wAlerta('C10', 'cMun', DSC_CMUN, ERR_MSG_INVALIDO);
  Gerador.wCampo(tcStr, 'C11', 'xMun   ', 02, 60, 1, xMun, DSC_XMUN);
  Gerador.wCampo(tcStr, 'C12', 'UF     ', 02, 02, 1, xUF, DSC_UF);
  if not ValidarUF(xUF) then
    Gerador.wAlerta('C12', 'UF', DSC_UF, ERR_MSG_INVALIDO);
  Gerador.wCampo(tcInt, 'C13', 'CEP    ', 08, 08, 1, nfe.Emit.enderEmit.CEP, DSC_CEP);
  Gerador.wCampo(tcInt, 'C14', 'cPais  ', 04, 04, 0, CODIGO_BRASIL, DSC_CPAIS); // Conforme NT-2009/01
  Gerador.wCampo(tcStr, 'C15', 'xPais  ', 01, 60, 0, nfe.Emit.enderEmit.xPais, DSC_XPAIS);
  Gerador.wCampo(tcStr, 'C16', 'fone   ', 06, 14, 0, somenteNumeros(nfe.Emit.enderEmit.fone), DSC_FONE);
  Gerador.wGrupo('/enderEmit');
end;

procedure TNFeW.GerarAvulsa;
begin
  if Trim(nfe.Avulsa.CNPJ) <> '' then
  begin
    Gerador.wGrupo('avulsa', 'D01');
    Gerador.wCampo(tcStr, 'D02', 'CNPJ   ', 14, 14, 1, nfe.Avulsa.CNPJ, DSC_CNPJ);
    Gerador.wCampo(tcStr, 'D03', 'xOrgao ', 01, 60, 1, nfe.Avulsa.xOrgao, DSC_XORGAO);
    Gerador.wCampo(tcStr, 'D04', 'matr   ', 01, 60, 1, nfe.Avulsa.matr, DSC_MATR);
    Gerador.wCampo(tcStr, 'D05', 'xAgente', 01, 60, 1, nfe.Avulsa.xAgente, DSC_XAGENTE);
    Gerador.wCampo(tcStr, 'D06', 'fone   ', 06, 14, 0, somenteNumeros(nfe.Avulsa.fone), DSC_FONE);
    Gerador.wCampo(tcStr, 'D07', 'UF     ', 02, 02, 1, nfe.Avulsa.UF, DSC_UF);
    if not ValidarUF(nfe.Avulsa.UF) then
      Gerador.wAlerta('D07', 'UF', DSC_UF, ERR_MSG_INVALIDO);
    Gerador.wCampo(tcStr, 'D08', 'nDAR   ', 01, 60, 0, nfe.Avulsa.nDAR, DSC_nDAR);
    Gerador.wCampo(tcDat, 'D09', 'dEmi   ', 10, 10, 0, nfe.Avulsa.dEmi, DSC_DEMI);
    Gerador.wCampo(tcDe2, 'D10', 'vDAR   ', 01, 15, 0, nfe.Avulsa.vDAR, DSC_VDAR);
    Gerador.wCampo(tcStr, 'D11', 'repEmi ', 01, 60, 1, nfe.Avulsa.repEmi, DSC_REPEMI);
    Gerador.wCampo(tcDat, 'D12', 'dPag   ', 10, 10, 1, nfe.Avulsa.dPag, DSC_DPAG);
    Gerador.wGrupo('/avulsa');
  end;
end;

procedure TNFeW.GerarDest;
var
  UF: string;
const
  HOM_NOME_DEST = 'NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL';
begin
  UF := '';
  Gerador.wGrupo('dest', 'E01');
//  Gerador.wCampoCNPJCPF('E02', 'E03', nfe.Dest.CNPJCPF, nfe.Dest.enderDest.cPais);

    if nfe.infNFe.Versao >= 3 then 
     begin
      if nfe.Dest.idEstrangeiro <> '' then 
         Gerador.wCampo(tcStr, 'E03a', 'idEstrangeiro', 01, 20, 1, nfe.Dest.idEstrangeiro, 'erro')
      else 
         Gerador.wCampoCNPJCPF('E02', 'E03', nfe.Dest.CNPJCPF, nfe.Dest.enderDest.cPais);
     end
    else 
       Gerador.wCampoCNPJCPF('E02', 'E03', nfe.Dest.CNPJCPF, nfe.Dest.enderDest.cPais);
  if nfe.Ide.tpAmb = taProducao then
    Gerador.wCampo(tcStr, 'E04', 'xNome  ', 02, 60, 1, nfe.Dest.xNome, DSC_XNOME)
  else
    Gerador.wCampo(tcStr, 'E04', 'xNome  ', 02, 60, 1, HOM_NOME_DEST, DSC_XNOME);

  (**)GerarDestEnderDest(UF);
  Gerador.IDNivel := 'E01';
  // Inscri��o Estadual
  if nfe.Dest.IE = 'ISENTO' then
    Gerador.wCampo(tcStr, 'E17', 'IE ', 00, 14, 1, nfe.Dest.IE, DSC_IE)
  else if (trim(nfe.Dest.IE) <> '') or (nfe.Ide.modelo <> 65)  then
    Gerador.wCampo(tcStr, 'E17', 'IE     ', 00, 14, 1, SomenteNumeros(nfe.Dest.IE), DSC_IE);

//  if (length(nfe.Dest.CNPJCPF) = 11) and (SomenteNumeros(nfe.Dest.IE) <> '') then
//    Gerador.wAlerta('E17', 'IE', DSC_IE, ERR_MSG_INVALIDO); // Para MG produtor rural possui CPF e IE
  if (FOpcoes.ValidarInscricoes) and (nfe.Dest.IE <> '') and (nfe.Dest.IE <> 'ISENTO') then
    if not ValidarIE(nfe.Dest.IE, UF) then
      Gerador.wAlerta('E17', 'IE', DSC_IE, ERR_MSG_INVALIDO);
  Gerador.wCampo(tcStr, 'E18', 'ISUF   ', 08, 09, 0, nfe.Dest.ISUF, DSC_ISUF);
  if (FOpcoes.ValidarInscricoes) and (nfe.Dest.ISUF <> '') then
    if not ValidarISUF(nfe.Dest.ISUF) then
      Gerador.wAlerta('E18', 'ISUF', DSC_ISUF, ERR_MSG_INVALIDO);
  Gerador.wCampo(tcStr, 'E19', 'email   ', 01, 60, 0, nfe.Dest.Email, DSC_EMAIL);
  Gerador.wGrupo('/dest');
end;

procedure TNFeW.GerarDestEnderDest(var UF: string);
var
  cMun: integer;
  xMun: string;
  xUF: string;
begin
  AjustarMunicipioUF(xUF, xMun, cMun, nfe.Dest.enderDest.cPais, nfe.Dest.enderDest.UF, nfe.Dest.enderDest.xMun, nfe.Dest.enderDest.cMun);
  UF := xUF;
  Gerador.wGrupo('enderDest', 'E05');
  Gerador.wCampo(tcStr, 'E06', 'xLgr   ', 02, 60, 1, nfe.Dest.enderDest.xLgr, DSC_XLGR);
  Gerador.wCampo(tcStr, 'E07', 'nro    ', 01, 60, 1, ExecutarAjusteTagNro(FOpcoes.FAjustarTagNro, nfe.Dest.enderDest.nro), DSC_NRO);
  Gerador.wCampo(tcStr, 'E08', 'xCpl   ', 01, 60, 0, nfe.Dest.enderDest.xCpl, DSC_XCPL);
  Gerador.wCampo(tcStr, 'E09', 'xBairro', 01, 60, 1, nfe.Dest.enderDest.xBairro, DSC_XBAIRRO);
  Gerador.wCampo(tcInt, 'E10', 'cMun   ', 01, 07, 1, cMun, DSC_CMUN);
  if not ValidarMunicipio(cMun) then
    Gerador.wAlerta('E10', 'cMun', DSC_CMUN, ERR_MSG_INVALIDO);
  Gerador.wCampo(tcStr, 'E11', 'xMun   ', 02, 60, 1, xMun, DSC_XMUN);
  Gerador.wCampo(tcStr, 'E12', 'UF     ', 02, 02, 1, xUF, DSC_UF);
  if not ValidarUF(xUF) then
    Gerador.wAlerta('E12', 'UF', DSC_UF, ERR_MSG_INVALIDO);
  Gerador.wCampo(tcInt, 'E13', 'CEP    ', 08, 08, 0, nfe.Dest.enderDest.CEP, DSC_CEP);
  //Gerador.wCampo(tcStr, 'E14', 'cPais  ', 01, 04, 0, IntToStrZero(nfe.Dest.enderDest.cPais,4), DSC_CPAIS);
  Gerador.wCampo(tcStr, 'E14', 'cPais ', 01, 04, 0, IIf(nfe.Dest.enderDest.cPais <> 0, IntToStrZero(nfe.Dest.enderDest.cPais,4), ''), DSC_CPAIS);
  if not ValidarCodigoPais(nfe.Dest.enderDest.cPais) = -1 then
    Gerador.wAlerta('E14', 'cPais', DSC_CPAIS, ERR_MSG_INVALIDO);
  Gerador.wCampo(tcStr, 'E15', 'xPais  ', 02, 60, 0, nfe.Dest.enderDest.xPais, DSC_XPAIS);
  Gerador.wCampo(tcStr, 'E16', 'fone   ', 06, 14, 0, somenteNumeros(nfe.Dest.enderDest.fone), DSC_FONE);
  Gerador.wGrupo('/enderDest');
end;

procedure TNFeW.GerarRetirada;
var
  cMun: integer;
  xMun: string;
  xUF: string;
begin
  if trim(nfe.Retirada.xLgr) <> '' then
  begin
    AjustarMunicipioUF(xUF, xMun, cMun, nfe.Emit.EnderEmit.cPais, nfe.Retirada.UF, nfe.Retirada.xMun, nfe.Retirada.cMun);
    Gerador.wGrupo('retirada', 'F01');
    Gerador.wCampoCNPJCPF('F02', 'F02a', nfe.Retirada.CNPJCPF, nfe.Emit.EnderEmit.cPais);
    Gerador.wCampo(tcStr, 'F03', 'xLgr   ', 02, 60, 1, nfe.Retirada.xLgr, DSC_XLGR);
    Gerador.wCampo(tcStr, 'F04', 'nro    ', 01, 60, 1, ExecutarAjusteTagNro(FOpcoes.FAjustarTagNro, nfe.Retirada.nro), DSC_NRO);
    Gerador.wCampo(tcStr, 'F05', 'xCpl   ', 01, 60, 0, nfe.Retirada.xCpl, DSC_XCPL);
    Gerador.wCampo(tcStr, 'F06', 'xBairro', 01, 60, 1, nfe.Retirada.xBairro, DSC_XBAIRRO);
    Gerador.wCampo(tcInt, 'F07', 'cMun   ', 01, 07, 1, cMun, DSC_CMUN);
    if not ValidarMunicipio(cMun) then
      Gerador.wAlerta('F07', 'cMun', DSC_CMUN, ERR_MSG_INVALIDO);
    Gerador.wCampo(tcStr, 'F08', 'xMun   ', 02, 60, 1, xMun, DSC_XMUN);
    Gerador.wCampo(tcStr, 'F09', 'UF     ', 02, 02, 1, xUF, DSC_UF);
    if not ValidarUF(xUF) then
      Gerador.wAlerta('F09', 'UF', DSC_UF, ERR_MSG_INVALIDO);
    Gerador.wGrupo('/retirada');
  end;
end;

procedure TNFeW.GerarEntrega;
var
  cMun: integer;
  xMun: string;
  xUF: string;
begin
  if trim(nfe.Entrega.xLgr) <> '' then
  begin
    AjustarMunicipioUF(xUF, xMun, cMun, nfe.Dest.enderDest.cPais, nfe.Entrega.UF, nfe.Entrega.xMun, nfe.Entrega.cMun);
    Gerador.wGrupo('entrega', 'G01');
    Gerador.wCampoCNPJCPF('G02', 'G02a', nfe.Entrega.CNPJCPF, nfe.Dest.enderDest.cPais);
    Gerador.wCampo(tcStr, 'G03', 'xLgr   ', 02, 60, 1, nfe.Entrega.xLgr, DSC_XLGR);
    Gerador.wCampo(tcStr, 'G04', 'nro    ', 01, 60, 1, ExecutarAjusteTagNro(FOpcoes.FAjustarTagNro, nfe.Entrega.nro), DSC_NRO);
    Gerador.wCampo(tcStr, 'G05', 'xCpl   ', 01, 60, 0, nfe.Entrega.xCpl, DSC_XCPL);
    Gerador.wCampo(tcStr, 'G06', 'xBairro', 01, 60, 1, nfe.Entrega.xBairro, DSC_XBAIRRO);
    Gerador.wCampo(tcInt, 'G07', 'cMun   ', 01, 07, 1, cMun, DSC_CMUN);
    if not ValidarMunicipio(cMun) then
      Gerador.wAlerta('F07', 'cMun', DSC_CMUN, ERR_MSG_INVALIDO);
    Gerador.wCampo(tcStr, 'G08', 'xMun   ', 02, 60, 1, xMun, DSC_XMUN);
    Gerador.wCampo(tcStr, 'G09', 'UF     ', 02, 02, 1, xUF, DSC_UF);
    if not ValidarUF(xUF) then
      Gerador.wAlerta('G09', 'UF', DSC_UF, ERR_MSG_INVALIDO);
    Gerador.wGrupo('/entrega');
  end;
end;

procedure TNFeW.GerarDet;
var
  i: integer;
begin
  for i := 0 to nfe.Det.Count - 1 do
  begin
    Gerador.wGrupo('det nItem="' + IntToStr(nfe.Det[i].Prod.nItem) + '"', 'H01');
    Gerador.gtCampo('nItem', IntToStr(nfe.Det[i].Prod.nItem));
    (**)GerarDetProd(i);
    (**)GerarDetImposto(i);
    Gerador.IDNivel := 'H01';
    Gerador.wCampo(tcStr, 'V01', 'infAdProd', 01, 500, 0, nfe.Det[i].infAdProd, DSC_INFADPROD);
    Gerador.wGrupo('/det');
  end;
  if nfe.Det.Count > 990 then
    Gerador.wAlerta('H02', 'nItem', DSC_NITEM, ERR_MSG_MAIOR_MAXIMO + '990');
end;

procedure TNFeW.GerarDetProd(const i: integer);
begin
  Gerador.wGrupo('prod', 'I01');
  Gerador.wCampo(tcStr, 'I02 ', 'cProd   ', 01, 60, 1, nfe.Det[i].Prod.cProd, DSC_CPROD);
  Gerador.wCampo(tcStr, 'I03 ', 'cEAN    ', 00, 14, 1, nfe.Det[i].Prod.cEAN, DSC_CEAN);
  Gerador.wCampo(tcStr, 'I04 ', 'xProd   ', 1, 120, 1, nfe.Det[i].Prod.xProd, DSC_XPROD);
  Gerador.wCampo(tcStr, 'I05 ', 'NCM     ', 02, 08,   IIf(NFe.infNFe.Versao >= 2,1,0), nfe.Det[i].Prod.NCM, DSC_NCM);
  Gerador.wCampo(tcStr, 'I06 ', 'EXTIPI  ', 02, 03, 0, nfe.Det[i].Prod.EXTIPI, DSC_EXTIPI);
  //Gerador.wCampo(tcInt, 'I07 ', 'genero  ', 02, 02, 0, nfe.Det[i].Prod.genero, DSC_GENERO);
  Gerador.wCampo(tcEsp, 'I08 ', 'CFOP    ', 04, 04, 1, somenteNumeros(nfe.Det[i].Prod.CFOP), DSC_CFOP);
  Gerador.wCampo(tcStr, 'I09 ', 'uCom    ', 01, 06, 1, nfe.Det[i].Prod.uCom, DSC_UCOM);
  Gerador.wCampo(tcDe4, 'I10 ', 'qCom    ', 00, 15, 1, nfe.Det[i].Prod.qCom, DSC_QCOM);
  Gerador.wCampo(IIf(NFe.infNFe.Versao >= 2,tcDe10,tcDe4),'I10a', 'vUnCom  ', 00, 21, 1, nfe.Det[i].Prod.vUnCom, DSC_VUNCOM);
  Gerador.wCampo(tcDe2, 'I11 ', 'vProd   ', 00, 15, 1, nfe.Det[i].Prod.vProd, DSC_VPROD);
  Gerador.wCampo(tcStr, 'I12 ', 'cEANTrib', 00, 14, 1, nfe.Det[i].Prod.cEANTrib, DSC_CEANTRIB);
  Gerador.wCampo(tcStr, 'I13 ', 'uTrib   ', 01, 06, 1, nfe.Det[i].Prod.uTrib, DSC_UTRIB);
  Gerador.wCampo(tcDe4, 'I14 ', 'qTrib   ', 00, 15, 1, nfe.Det[i].Prod.qTrib, DSC_QTRIB);
  Gerador.wCampo(IIf(NFe.infNFe.Versao >= 2,tcDe10,tcDe4), 'I14a', 'vUnTrib ', 00, 21, 1, nfe.Det[i].Prod.vUnTrib, DSC_VUNTRIB);
  Gerador.wCampo(tcDe2, 'I15 ', 'vFrete  ', 00, 15, 0, nfe.Det[i].Prod.vFrete, DSC_VFRETE);
  Gerador.wCampo(tcDe2, 'I16 ', 'vSeg    ', 00, 15, 0, nfe.Det[i].Prod.vSeg, DSC_VSEG);
  Gerador.wCampo(tcDe2, 'I17 ', 'vDesc   ', 00, 15, 0, nfe.Det[i].Prod.vDesc, DSC_VDESC);
  Gerador.wCampo(tcDe2, 'I17a', 'vOutro  ', 00, 15, 0, nfe.Det[i].Prod.vOutro, DSC_VOUTRO);
  if NFe.infNFe.Versao >= 2 then
     Gerador.wCampo(tcStr, 'I17b', 'indTot  ', 01, 01, 1, indTotToStr(nfe.Det[i].Prod.IndTot), DSC_INDTOT);
  (**)GerarDetProdDI(i);
  Gerador.wCampo(tcStr, 'I30', 'xPed       ', 01, 15, 0, nfe.Det[i].Prod.xPed, DSC_XPED);
  Gerador.wCampo(tcInt, 'I31', 'nItemPed   ', 06, 06, 0, nfe.Det[i].Prod.nItemPed, DSC_NITEMPED);
  (**)GerarDetProdVeicProd(i);
  (**)GerarDetProdMed(i);
  (**)GerarDetProdArma(i);
  (**)GerarDetProdComb(i);
  Gerador.wGrupo('/prod');
end;

procedure TNFeW.GerarDetProdDI(const i: integer);
var
  j: integer;
begin
  for j := 0 to nfe.Det[i].Prod.DI.Count - 1 do
  begin
    Gerador.wGrupo('DI', 'I18');
    Gerador.wCampo(tcStr, 'I19', 'nDI        ', 01, 12, 1, nfe.Det[i].Prod.DI[j].nDI, DSC_NDI);
    Gerador.wCampo(tcDat, 'I20', 'dDI        ', 10, 10, 1, nfe.Det[i].Prod.DI[j].dDI, DSC_DDi);
    Gerador.wCampo(tcStr, 'I21', 'xLocDesemb ', 01, 60, 1, nfe.Det[i].Prod.DI[j].xLocDesemb, DSC_XLOCDESEMB);
    Gerador.wCampo(tcStr, 'I22', 'UFDesemb   ', 02, 02, 1, nfe.Det[i].Prod.DI[j].UFDesemb, DSC_UFDESEMB);
    if not ValidarUF(nfe.Det[i].Prod.DI[j].UFDesemb) then
      Gerador.wAlerta('I22', 'UFDesemb', DSC_UFDESEMB, ERR_MSG_INVALIDO);
    Gerador.wCampo(tcDat, 'I23', 'dDesemb    ', 10, 10, 1, nfe.Det[i].Prod.DI[j].dDesemb, DSC_DDESEMB);
    Gerador.wCampo(tcStr, 'I24', 'cExportador', 01, 60, 1, nfe.Det[i].Prod.DI[j].cExportador, DSC_CEXPORTADOR);
    (**)GerarDetProdDIadi(i, j);
    Gerador.wGrupo('/DI');
  end;
  if nfe.Det[i].Prod.DI.Count > 100 then
    Gerador.wAlerta('I18', 'DI', DSC_NITEM, ERR_MSG_MAIOR_MAXIMO + '100');
end;

procedure TNFeW.GerarDetProdDIadi(const i, j: integer);
var
  k: integer;
begin
  for k := 0 to nfe.Det[i].Prod.DI[j].adi.Count - 1 do
  begin
    Gerador.wGrupo('adi', 'I25');
    Gerador.wCampo(tcInt, 'I26', 'nAdicao    ', 01, 03, 1, nfe.Det[i].Prod.DI[j].adi[k].nAdicao, DSC_NADICAO);
    Gerador.wCampo(tcInt, 'I27', 'nSeqAdic   ', 01, 03, 1, nfe.Det[i].Prod.DI[j].adi[k].nSeqAdi, DSC_NSEQADIC);
    Gerador.wCampo(tcStr, 'I28', 'cFabricante', 01, 60, 1, nfe.Det[i].Prod.DI[j].adi[k].cFabricante, DSC_CFABRICANTE);
    Gerador.wCampo(tcDe2, 'I29', 'vDescDI    ', 00, 15, 0, nfe.Det[i].Prod.DI[j].adi[k].vDescDI, DSC_VDESCDI);
    Gerador.wGrupo('/adi');
  end;
  if nfe.Det[i].Prod.DI[j].adi.Count > 100 then
    Gerador.wAlerta('I25', 'adi', DSC_NITEM, ERR_MSG_MAIOR_MAXIMO + '100');
end;

procedure TNFeW.GerarDetProdVeicProd(const i: integer);
begin
  if trim(nfe.Det[i].Prod.veicProd.chassi) <> '' then
  begin
    Gerador.wGrupo('veicProd', 'J01');
    Gerador.wCampo(tcStr, 'J02', 'tpOp    ', 01, 01, 1, tpOPToStr(nfe.Det[i].Prod.veicProd.tpOP), DSC_TPOP);
    Gerador.wCampo(tcStr, 'J03', 'chassi  ', 17, 17, 1, nfe.Det[i].Prod.veicProd.chassi, DSC_CHASSI);
    Gerador.wCampo(tcStr, 'J04', 'cCor    ', 04, 04, 1, nfe.Det[i].Prod.veicProd.cCor, DSC_CCOR);
    Gerador.wCampo(tcStr, 'J05', 'xCor    ', 01, 40, 1, nfe.Det[i].Prod.veicProd.xCor, DSC_XCOR);
    Gerador.wCampo(tcStr, 'J06', 'pot     ', 04, 04, 1, nfe.Det[i].Prod.veicProd.pot, DSC_POT);
    if NFe.infNFe.Versao >= 2 then
       Gerador.wCampo(tcStr, 'J07', 'cilin   ', 04, 04, 1, nfe.Det[i].Prod.veicProd.cilin, DSC_CILIN)
    else
       Gerador.wCampo(tcStr, 'J07', 'CM3     ', 04, 04, 1, nfe.Det[i].Prod.veicProd.cilin, DSC_CILIN);
    Gerador.wCampo(tcStr, 'J08', 'pesoL   ', 00, 09, 1, nfe.Det[i].Prod.veicProd.pesoL, DSC_PESOL);
    Gerador.wCampo(tcStr, 'J09', 'pesoB   ', 00, 09, 1, nfe.Det[i].Prod.veicProd.pesoB, DSC_PESOB);
    Gerador.wCampo(tcStr, 'J10', 'nSerie  ', 00, 09, 1, nfe.Det[i].Prod.veicProd.nSerie, DSC_NSERIE);
    Gerador.wCampo(tcStr, 'J11', 'tpComb  ', 02, 02, 1, nfe.Det[i].Prod.veicProd.tpComb, DSC_TPCOMB);
    Gerador.wCampo(tcStr, 'J12', 'nMotor  ', 00, 21, 1, nfe.Det[i].Prod.veicProd.nMotor, DSC_NMOTOR);
    if NFe.infNFe.Versao >= 2 then
       Gerador.wCampo(tcStr, 'J13', 'CMT     ', 09, 09, 1, nfe.Det[i].Prod.veicProd.CMT, DSC_CMT)
    else
       Gerador.wCampo(tcStr, 'J13', 'CMKG    ', 09, 09, 1, nfe.Det[i].Prod.veicProd.CMT, DSC_CMT);
    Gerador.wCampo(tcStr, 'J14', 'dist    ', 00, 04, 1, nfe.Det[i].Prod.veicProd.dist, DSC_DIST);
//    Gerador.wCampo(tcStr, 'J15', 'RENAVAM ', 00, 09, 0, nfe.Det[i].Prod.veicProd.RENAVAM, DSC_RENAVAM);
    Gerador.wCampo(tcInt, 'J16', 'anoMod  ', 00, 04, 1, nfe.Det[i].Prod.veicProd.anoMod, DSC_ANOMOD);
    Gerador.wCampo(tcInt, 'J17', 'anoFab  ', 00, 04, 1, nfe.Det[i].Prod.veicProd.anoFab, DSC_ANOFAB);
    Gerador.wCampo(tcStr, 'J18', 'tpPint  ', 00, 01, 1, nfe.Det[i].Prod.veicProd.tpPint, DSC_TPPINT);
    Gerador.wCampo(tcInt, 'J19', 'tpVeic  ', 00, 02, 1, nfe.Det[i].Prod.veicProd.tpVeic, DSC_TPVEIC);
    Gerador.wCampo(tcInt, 'J20', 'espVeic ', 00, 01, 1, nfe.Det[i].Prod.veicProd.espVeic, DSC_ESPVEIC);
    Gerador.wCampo(tcStr, 'J21', 'VIN     ', 00, 01, 1, nfe.Det[i].Prod.veicProd.VIN, DSC_VIN);
    Gerador.wCampo(tcStr, 'J22', 'condVeic', 00, 01, 1, condVeicToStr(nfe.Det[i].Prod.veicProd.condVeic), DSC_CONDVEIC);
    Gerador.wCampo(tcStr, 'J23', 'cMod    ', 00, 06, 1, nfe.Det[i].Prod.veicProd.cMod, DSC_CMOD);
    if NFe.infNFe.Versao >= 2 then
     begin
       Gerador.wCampo(tcStr, 'J24', 'cCorDENATRAN', 00, 2, 1, nfe.Det[i].Prod.veicProd.cCorDENATRAN, DSC_CCORDEN);
       Gerador.wCampo(tcInt, 'J25', 'lota    ', 01, 03, 1, nfe.Det[i].Prod.veicProd.lota, DSC_LOTA);
       Gerador.wCampo(tcInt, 'J26', 'tpRest  ', 01, 01, 1, nfe.Det[i].Prod.veicProd.tpRest, DSC_TPREST);
     end;
    Gerador.wGrupo('/veicProd');
  end;
end;

procedure TNFeW.GerarDetProdMed(const i: integer);
var
  j: integer;
begin
  for j := 0 to nfe.Det[i].Prod.med.Count - 1 do
  begin
    Gerador.wGrupo('med', 'K01');
    Gerador.wCampo(tcStr, 'K02', 'nLote', 01, 20, 1, nfe.Det[i].Prod.med[j].nLote, DSC_NLOTE);
    Gerador.wCampo(tcDe3, 'K03', 'qLote', 00, 11, 1, nfe.Det[i].Prod.med[j].qLote, DSC_QLOTE);
    Gerador.wCampo(tcDat, 'K04', 'dFab ', 10, 10, 1, nfe.Det[i].Prod.med[j].dFab, DSC_DFAB);
    Gerador.wCampo(tcDat, 'K05', 'dVal ', 10, 10, 1, nfe.Det[i].Prod.med[j].dVal, DSC_DVAL);
    Gerador.wCampo(tcDe2, 'K06', 'vPMC ', 00, 15, 1, nfe.Det[i].Prod.med[j].vPMC, DSC_VPMC);
    Gerador.wGrupo('/med');
  end;
end;

procedure TNFeW.GerarDetProdArma(const i: integer);
var
  j: integer;
begin
  for j := 0 to nfe.Det[i].Prod.arma.Count - 1 do
  begin
    Gerador.wGrupo('arma', 'L00');
    Gerador.wCampo(tcStr, 'L02', 'tpArma', 01, 001, 1, tpArmaToStr(nfe.Det[i].Prod.arma[j].tpArma), DSC_TPARMA);
    Gerador.wCampo(tcStr, 'L03', 'nSerie', 01, 009, 1, nfe.Det[i].Prod.arma[j].nSerie, DSC_NSERIE);
    Gerador.wCampo(tcStr, 'L04', 'nCano ', 01, 009, 1, nfe.Det[i].Prod.arma[j].nCano, DSC_NCANO);
    Gerador.wCampo(tcStr, 'L05', 'descr ', 01, 256, 1, nfe.Det[i].Prod.arma[j].descr, DSC_DESCR);
    Gerador.wGrupo('/arma');
  end;
end;

procedure TNFeW.GerarDetProdComb(const i: integer);
begin
  if (nfe.Det[i].Prod.comb.cProdANP > 0) then
  begin
    Gerador.wGrupo('comb', 'L01');
    Gerador.wCampo(tcInt, 'L102', 'cProdANP', 09, 09, 1, nfe.Det[i].Prod.comb.cProdANP, DSC_CPRODANP);

    if (trim(nfe.Det[i].Prod.comb.CODIF))  <> '' then Gerador.wCampo(tcEsp, 'L103', 'CODIF   ', 00, 21, 1, nfe.Det[i].Prod.comb.CODIF, DSC_CODIF);
    if nfe.Det[i].Prod.comb.qTemp         <> 0  then Gerador.wCampo(tcDe4, 'L104', 'qTemp   ', 01, 16, 1, nfe.Det[i].Prod.comb.qTemp, DSC_QTEMP);

    if  (nfe.infNFe.Versao < 2) and ((nfe.Det[i].Prod.comb.ICMS.vBCICMS    > 0) or
        (nfe.Det[i].Prod.comb.ICMS.vICMS      > 0) or
        (nfe.Det[i].Prod.comb.ICMS.vBCICMSST  > 0) or
        (nfe.Det[i].Prod.comb.ICMS.vICMSST    > 0) or
        (nfe.Det[i].Prod.comb.ICMSInter.vBCICMSSTDest > 0) or
        (nfe.Det[i].Prod.comb.ICMSInter.vICMSSTDest   > 0) or
        (nfe.Det[i].Prod.comb.ICMSCons.vBCICMSSTCons  > 0) or
        (nfe.Det[i].Prod.comb.ICMSCons.vICMSSTCons    > 0)) then
      begin
        // Manter Disponivel para versao 3.0
        (**)GerarDetProdCombCIDE(i);
        (**)GerarDetProdCombICMS(i);
        (**)GerarDetProdCombICMSInter(i);
        (**)GerarDetProdCombICMSCons(i);
      end
      else
      begin
       //  versao 4.01
        Gerador.wCampo(tcStr, 'L120', 'UFCons       ', 02, 02, 1, nfe.Det[i].Prod.comb.UFcons, DSC_UFCONS);
        if not ValidarUF(nfe.Det[i].Prod.comb.UFcons) then Gerador.wAlerta('L120', 'UFcons', DSC_UFCONS, ERR_MSG_INVALIDO);
        (**)GerarDetProdCombCIDE(i);
      end;

    Gerador.wGrupo('/comb');
  end;
end;

procedure TNFeW.GerarDetProdCombCIDE(const i: integer);
begin
  if (nfe.Det[i].Prod.comb.CIDE.qBCProd > 0) or
    (nfe.Det[i].Prod.comb.CIDE.vAliqProd > 0) or
    (nfe.Det[i].Prod.comb.CIDE.vCIDE > 0) then
  begin
    Gerador.wGrupo('CIDE', 'L105');
    Gerador.wCampo(tcDe4, 'L106', 'qBCProd  ', 01, 16, 1, nfe.Det[i].Prod.comb.CIDE.qBCprod, DSC_QBCPROD);
    Gerador.wCampo(tcDe4, 'L107', 'vAliqProd', 01, 15, 1, nfe.Det[i].Prod.comb.CIDE.vAliqProd, DSC_VALIQPROD);
    Gerador.wCampo(tcDe2, 'L108', 'vCIDE    ', 01, 15, 1, nfe.Det[i].Prod.comb.CIDE.vCIDE, DSC_VCIDE);
    Gerador.wGrupo('/CIDE');
  end;
end;

procedure TNFeW.GerarDetProdCombICMS(const i: integer);
begin
  Gerador.wGrupo('ICMSComb', 'L109');
  Gerador.wCampo(tcDe2, 'L110', 'vBCICMS  ', 01, 15, 1, nfe.Det[i].Prod.comb.ICMS.vBCICMS, DSC_VBCICMS);
  Gerador.wCampo(tcDe2, 'L111', 'vICMS    ', 01, 15, 1, nfe.Det[i].Prod.comb.ICMS.vICMS, DSC_VICMS);
  Gerador.wCampo(tcDe2, 'L112', 'vBCICMSST', 01, 15, 1, nfe.Det[i].Prod.comb.ICMS.vBCICMSST, DSC_VBCICMSST);
  Gerador.wCampo(tcDe2, 'L113', 'vICMSST  ', 01, 15, 1, nfe.Det[i].Prod.comb.ICMS.vICMSST, DSC_VICMSST);
  Gerador.wGrupo('/ICMSComb');
end;

procedure TNFeW.GerarDetProdCombICMSInter(const i: integer);
begin
  if (nfe.Det[i].Prod.comb.ICMSInter.vBCICMSSTDest > 0) or
    (nfe.Det[i].Prod.comb.ICMSInter.vICMSSTDest > 0) then
  begin
    Gerador.wGrupo('ICMSInter', 'L114');
    Gerador.wCampo(tcDe2, 'L115', 'vBCICMSSTDest', 01, 15, 1, nfe.Det[i].Prod.comb.ICMSInter.vBCICMSSTDest, DSC_VBCICMSSTDEST);
    Gerador.wCampo(tcDe2, 'L116', 'vICMSSTDest  ', 01, 15, 1, nfe.Det[i].Prod.comb.ICMSInter.vICMSSTDest, DSC_VICMSSTDEST);
    Gerador.wGrupo('/ICMSInter');
  end;
end;

procedure TNFeW.GerarDetProdCombICMSCons(const i: integer);
begin
  if (nfe.Det[i].Prod.comb.ICMSCons.vBCICMSSTCons > 0) or
    (nfe.Det[i].Prod.comb.ICMSCons.vICMSSTCons > 0) or
    (trim(nfe.Det[i].Prod.comb.ICMSCons.UFcons) <> '') then
  begin
    Gerador.wGrupo('ICMSCons', 'L117');
    Gerador.wCampo(tcDe2, 'L118', 'vBCICMSSTCons', 01, 15, 1, nfe.Det[i].Prod.comb.ICMSCons.vBCICMSSTCons, DSC_VBCICMSSTCONS);
    Gerador.wCampo(tcDe2, 'L119', 'vICMSSTCons  ', 01, 15, 1, nfe.Det[i].Prod.comb.ICMSCons.vICMSSTCons, DSC_VICMSSTCONS);
    Gerador.wCampo(tcStr, 'L120', 'UFCons       ', 02, 02, 1, nfe.Det[i].Prod.comb.ICMSCons.UFcons, DSC_UFCONS);
    if not ValidarUF(nfe.Det[i].Prod.comb.ICMSCons.UFcons) then
      Gerador.wAlerta('L120', 'UFcons', DSC_UFCONS, ERR_MSG_INVALIDO);
    Gerador.wGrupo('/ICMSCons');
  end;
end;

procedure TNFeW.GerarDetImposto(const i: integer);
begin
  Gerador.wGrupo('imposto', 'M01');

  if nfe.Det[i].Imposto.ISSQN.cSitTrib <> ISSQNcSitTribVazio then
      (**)GerarDetImpostoISSQN(i)
  else
  begin
      (**)GerarDetImpostoICMS(i);
      (**)GerarDetImpostoIPI(i);
      (**)GerarDetImpostoII(i);
  end;
  (**)GerarDetImpostoPIS(i);
  (**)GerarDetImpostoPISST(i);
  (**)GerarDetImpostoCOFINS(i);
  (**)GerarDetImpostoCOFINSST(i);

//
//  versao 3.01
  if NFe.infNFe.Versao < 2 then
     if nfe.Det[i].Imposto.ISSQN.cSitTrib = ISSQNcSitTribVazio then (**)GerarDetImpostoISSQN(i);

  Gerador.wGrupo('/imposto');
end;

procedure TNFeW.GerarDetImpostoICMS(const i: integer);
// Rotina melhorada por
// Perterson          - > peterson161@yahoo.com
// Henrique Leonardo  - > Hleonardopa@yahoo.com.br
// Jeandeson Merelis  - > jeanmerelis@yahoo.com
   var
    sTagTemp : string;

    function BuscaTag(const t: TpcnCSTIcms): string;
     begin
       case t of
          cst00		: result := '00';
          cst10		: result := '10';
          cst20		: result := '20';
          cst30		: result := '30';
          cst40	,
          cst41	,
          cst50		: result := '40';
          cst51		: result := '51';
          cst60		: result := '60';
          cst70		: result := '70';
          cst80		: result := '80';
          cst81		: result := '81';
          cst90		: result := '90';
          cstPart10 ,
          cstPart90 : result:= 'Part';
          cstRep41	: result:= 'ST';
       end;
     end;
begin
   Gerador.wGrupo('ICMS', 'N01');
   case nfe.Emit.CRT of
      crtRegimeNormal, crtSimplesExcessoReceita :
         begin

            if (nfe.Det[i].Imposto.ICMS.CST = cst41) and       //Ajuste para funcionar no ACBrNFeMonitor
               ((nfe.Det[i].Imposto.ICMS.vBCSTRet <> 0) or     //Qdo passar CST 41 e algum campo de repasse de ICMS ST
                (nfe.Det[i].Imposto.ICMS.vICMSSTRet <> 0) or   //estiver preenchido ser� trocado o cst para cstRep41
                (nfe.Det[i].Imposto.ICMS.vBCSTDest <> 0) or
                (nfe.Det[i].Imposto.ICMS.vICMSSTDest <> 0)) then
               nfe.Det[i].Imposto.ICMS.CST := cstRep41;

            if (nfe.Det[i].Imposto.ICMS.CST = cst10) and       //Ajuste para funcionar no ACBrNFeMonitor
               ((nfe.Det[i].Imposto.ICMS.UFST <> '') or        //Qdo passar CST 10 e algum campo de partilha de ICMS ST
                (nfe.Det[i].Imposto.ICMS.pBCOp <> 0)) then     //estiver preenchido ser� trocado o cst para cstPart10
               nfe.Det[i].Imposto.ICMS.CST := cstPart10;

            if (nfe.Det[i].Imposto.ICMS.CST = cst90) and       //Ajuste para funcionar no ACBrNFeMonitor
               ((nfe.Det[i].Imposto.ICMS.UFST <> '') or        //Qdo passar CST 90 e algum campo de partilha de ICMS ST
                (nfe.Det[i].Imposto.ICMS.pBCOp <> 0)) then     //estiver preenchido ser� trocado o cst para cstPart90
               nfe.Det[i].Imposto.ICMS.CST := cstPart90;
               
	          sTagTemp := BuscaTag( nfe.Det[i].Imposto.ICMS.CST );

            Gerador.wGrupo('ICMS' + sTagTemp, 'N' + CSTICMSTOStrTagPos(nfe.Det[i].Imposto.ICMS.CST));

            Gerador.wCampo(tcStr, 'N11', 'orig    ', 01, 01, 1, OrigTOStr(nfe.Det[i].Imposto.ICMS.orig), DSC_ORIG);
            Gerador.wCampo(tcStr, 'N12', 'CST     ', 02, 02, 1, CSTICMSTOStr(nfe.Det[i].Imposto.ICMS.CST), DSC_CST);

            case nfe.Det[i].Imposto.ICMS.CST of
               cst00 :
                  begin
                     Gerador.wCampo(tcStr, 'N13', 'modBC   ', 01, 01, 1, modBCToStr(nfe.Det[i].Imposto.ICMS.modBC), DSC_MODBC);
                     Gerador.wCampo(tcDe2, 'N15', 'vBC     ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBC, DSC_VBC);
                     Gerador.wCampo(tcDe2, 'N16', 'pICMS   ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pICMS, DSC_PICMS);
                     Gerador.wCampo(tcDe2, 'N17', 'vICMS   ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMS, DSC_VICMS);
                  end;
               cst10,
               cstPart10 :
				          begin
                     Gerador.wCampo(tcStr, 'N13', 'modBC   ', 01, 01, 1, modBCToStr(nfe.Det[i].Imposto.ICMS.modBC), DSC_MODBC);
                     Gerador.wCampo(tcDe2, 'N15', 'vBC     ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBC, DSC_VBC);
                     if (nfe.Det[i].Imposto.ICMS.UFST <> '') or
                        (nfe.Det[i].Imposto.ICMS.pBCOp <> 0) or
                        (nfe.Det[i].Imposto.ICMS.CST = cstPart10) then
                        Gerador.wCampo(tcDe2, 'N14', 'pRedBC  ', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pRedBC, DSC_PREDBC);
                     Gerador.wCampo(tcDe2, 'N16', 'pICMS   ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pICMS, DSC_PICMS);
                     Gerador.wCampo(tcDe2, 'N17', 'vICMS   ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMS, DSC_VICMS);
                     Gerador.wCampo(tcStr, 'N18', 'modBCST ', 01, 01, 1, modBCSTToStr(nfe.Det[i].Imposto.ICMS.modBCST), DSC_MODBCST);
                     Gerador.wCampo(tcDe2, 'N19', 'pMVAST  ', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pMVAST, DSC_PMVAST);
                     Gerador.wCampo(tcDe2, 'N20', 'pRedBCST', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pRedBCST, DSC_PREDBCST);
                     Gerador.wCampo(tcDe2, 'N21', 'vBCST   ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBCST, DSC_VBCST);
                     Gerador.wCampo(tcDe2, 'N22', 'pICMSST ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pICMSST, DSC_PICMSST);
                     Gerador.wCampo(tcDe2, 'N23', 'vICMSST ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMSST, DSC_VICMSST);
                     if (nfe.Det[i].Imposto.ICMS.UFST <> '') or
                        (nfe.Det[i].Imposto.ICMS.pBCOp <> 0) or
                        (nfe.Det[i].Imposto.ICMS.CST = cstPart10) then
                      begin
                        Gerador.wCampo(tcDe2, 'N25', 'pBCOp   ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pBCOp, DSC_PBCOP);
                        Gerador.wCampo(tcStr, 'N24', 'UFST    ', 02, 02, 1, nfe.Det[i].Imposto.ICMS.UFST, DSC_UFST);
                      end;
                  end;
               cst20 :
                  begin
                     Gerador.wCampo(tcStr, 'N13', 'modBC   ', 01, 01, 1, modBCToStr(nfe.Det[i].Imposto.ICMS.modBC), DSC_MODBC);
                     Gerador.wCampo(tcDe2, 'N14', 'pRedBC  ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pRedBC, DSC_PREDBC);
                     Gerador.wCampo(tcDe2, 'N15', 'vBC     ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBC, DSC_VBC);
                     Gerador.wCampo(tcDe2, 'N16', 'pICMS   ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pICMS, DSC_PICMS);
                     Gerador.wCampo(tcDe2, 'N17', 'vICMS   ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMS, DSC_VICMS);
                  end;
               cst30 :
                  begin
                     Gerador.wCampo(tcStr, 'N18', 'modBCST ', 01, 01, 1, modBCSTToStr(nfe.Det[i].Imposto.ICMS.modBCST), DSC_MODBCST);
                     Gerador.wCampo(tcDe2, 'N19', 'pMVAST  ', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pMVAST, DSC_PMVAST);
                     Gerador.wCampo(tcDe2, 'N20', 'pRedBCST', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pRedBCST, DSC_PRedBCST);
                     Gerador.wCampo(tcDe2, 'N21', 'vBCST   ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBCST, DSC_VBCST);
                     Gerador.wCampo(tcDe2, 'N22', 'pICMSST ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pICMSST, DSC_PICMSST);
                     Gerador.wCampo(tcDe2, 'N23', 'vICMSST ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMSST, DSC_VICMSST);
                  end;
               cst40,
               cst41,
	             cst50 :
                  begin
                     //Esse bloco fica a crit�rio de cada UF a obriga��o das informa��es, conforme o manual
                     Gerador.wCampo(tcDe2, 'N17', 'vICMS     ', 01, 15, 0, nfe.Det[i].Imposto.ICMS.vICMS, DSC_VICMS);
                     if (nfe.Det[i].Imposto.ICMS.vICMS > 0) then
                        Gerador.wCampo(tcStr, 'N28', 'motDesICMS', 01, 01, 0, motDesICMSToStr(nfe.Det[i].Imposto.ICMS.motDesICMS), DSC_MOTDESICMS);
                  end;
               cst51 :
                  begin
                     //Esse bloco fica a crit�rio de cada UF a obriga��o das informa��es, conforme o manual
                     Gerador.wCampo(tcStr, 'N13', 'modBC   ', 01, 01, 0, modBCToStr(nfe.Det[i].Imposto.ICMS.modBC), DSC_MODBC);
                     Gerador.wCampo(tcDe2, 'N14', 'pRedBC  ', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pRedBC, DSC_PREDBC);
                     Gerador.wCampo(tcDe2, 'N15', 'vBC     ', 01, 15, 0, nfe.Det[i].Imposto.ICMS.vBC, DSC_VBC);
                     Gerador.wCampo(tcDe2, 'N16', 'pICMS   ', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pICMS, DSC_PICMS);
                     Gerador.wCampo(tcDe2, 'N17', 'vICMS   ', 01, 15, 0, nfe.Det[i].Imposto.ICMS.vICMS, DSC_VICMS);
                  end;
               cst60 :
                  begin
                     if NFe.infNFe.Versao >= 2 then
                      begin
                        Gerador.wCampo(tcDe2, 'N26', 'vBCSTRet  ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBCSTRET, DSC_VBCSTRET);
                        Gerador.wCampo(tcDe2, 'N27', 'vICMSSTRet', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMSSTRET, DSC_VICMSSTRET);
                      end
                     else
                      begin
                        Gerador.wCampo(tcDe2, 'N21', 'vBCST   ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBCST, DSC_VBCST);
                        Gerador.wCampo(tcDe2, 'N23', 'vICMSST ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMSST, DSC_VICMSST);
                      end;
                  end;
               cst70 :
                  begin
                     Gerador.wCampo(tcStr, 'N13', 'modBC   ', 01, 01, 1, modBCToStr(nfe.Det[i].Imposto.ICMS.modBC), DSC_MODBC);
                     Gerador.wCampo(tcDe2, 'N14', 'pRedBC  ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pRedBC, DSC_PREDBC);
                     Gerador.wCampo(tcDe2, 'N15', 'vBC     ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBC, DSC_VBC);
                     Gerador.wCampo(tcDe2, 'N16', 'pICMS   ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pICMS, DSC_PICMS);
                     Gerador.wCampo(tcDe2, 'N17', 'vICMS   ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMS, DSC_VICMS);
                     Gerador.wCampo(tcStr, 'N18', 'modBCST ', 01, 01, 1, modBCSTToStr(nfe.Det[i].Imposto.ICMS.modBCST), DSC_MODBCST);
                     Gerador.wCampo(tcDe2, 'N19', 'pMVAST  ', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pMVAST, DSC_PMVAST);
                     Gerador.wCampo(tcDe2, 'N20', 'pRedBCST', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pRedBCST, DSC_PREDBCST);
                     Gerador.wCampo(tcDe2, 'N21', 'vBCST   ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBCST, DSC_VBCST);
                     Gerador.wCampo(tcDe2, 'N22', 'pICMSST ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pICMSST, DSC_PICMSST);
                     Gerador.wCampo(tcDe2, 'N23', 'vICMSST ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMSST, DSC_VICMSST);
                  end;
               cst90,
	             cstPart90 :
                  begin
                     Gerador.wCampo(tcStr, 'N13', 'modBC   ', 01, 01, 1, modBCToStr(nfe.Det[i].Imposto.ICMS.modBC), DSC_MODBC);
                     Gerador.wCampo(tcDe2, 'N15', 'vBC     ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBC, DSC_VBC);
                     Gerador.wCampo(tcDe2, 'N14', 'pRedBC  ', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pRedBC, DSC_PREDBC);
                     Gerador.wCampo(tcDe2, 'N16', 'pICMS   ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pICMS, DSC_PICMS);
                     Gerador.wCampo(tcDe2, 'N17', 'vICMS   ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMS, DSC_VICMS);
                     Gerador.wCampo(tcStr, 'N18', 'modBCST ', 01, 01, 1, modBCSTToStr(nfe.Det[i].Imposto.ICMS.modBCST), DSC_MODBCST);
                     Gerador.wCampo(tcDe2, 'N19', 'pMVAST  ', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pMVAST, DSC_PMVAST);
                     Gerador.wCampo(tcDe2, 'N20', 'pRedBCST', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pRedBCST, DSC_PREDBCST);
                     Gerador.wCampo(tcDe2, 'N21', 'vBCST   ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBCST, DSC_VBCST);
                     Gerador.wCampo(tcDe2, 'N22', 'pICMSST ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pICMSST, DSC_PICMSST);
                     Gerador.wCampo(tcDe2, 'N23', 'vICMSST ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMSST, DSC_VICMSST);
                     if (nfe.Det[i].Imposto.ICMS.UFST <> '') or
                        (nfe.Det[i].Imposto.ICMS.pBCOp <> 0) or
                        (nfe.Det[i].Imposto.ICMS.CST = cstPart90) then
                     begin
                        Gerador.wCampo(tcDe2, 'N25', 'pBCOp  ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pBCOp, DSC_PBCOP);
                        Gerador.wCampo(tcStr, 'N24', 'UFST   ', 02, 02, 1, nfe.Det[i].Imposto.ICMS.UFST, DSC_UFST);
                     end;
                  end;
	            	cstRep41 :
		               begin
                    // ICMSST - Repasse
                      Gerador.wCampo(tcDe2, 'N26', 'vBCSTRet   ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBCSTRet, DSC_VBCICMSST );
                      Gerador.wCampo(tcDe2, 'N27', 'vICMSSTRet ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMSSTRet, DSC_VICMSSTRET);
                      Gerador.wCampo(tcDe2, 'N31', 'vBCSTDest  ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBCSTDest, DSC_VBCICMSSTDEST);
                      Gerador.wCampo(tcDe2, 'N32', 'vICMSSTDest', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMSSTDest, DSC_VBCICMSSTDEST);
		               end;
            end;
            Gerador.wGrupo('/ICMS' + sTagTemp );
         end;
      crtSimplesNacional :
         begin
            //Grupo do Simples Nacional
            sTagTemp  := CSOSNTOStrTagPos(nfe.Det[i].Imposto.ICMS.CSOSN);
            Gerador.wGrupo('ICMSSN' + sTagTemp, 'N' + CSOSNToStrID(nfe.Det[i].Imposto.ICMS.CSOSN));
            Gerador.wCampo(tcStr, 'N11' , 'orig ', 01, 01, 1, OrigTOStr(nfe.Det[i].Imposto.ICMS.orig), DSC_ORIG);
            Gerador.wCampo(tcStr, 'N12a', 'CSOSN', 03, 03, 1, CSOSNIcmsToStr(nfe.Det[i].Imposto.ICMS.CSOSN), DSC_CSOSN);
            case  nfe.Det[i].Imposto.ICMS.CSOSN of
               csosn101 :
                  begin
                     Gerador.wCampo(tcDe2, 'N29', 'pCredSN    ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pCredSN, DSC_PCREDSN);
                     Gerador.wCampo(tcDe2, 'N30', 'vCredICMSSN', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vCredICMSSN, DSC_VCREDICMSSN);
                  end;
               csosn102,
               csosn103,
               csosn300,
               csosn400:
                  begin
                     //Tags ORIG e CSON j� criadas antes do case
                  end;
               csosn201 :
                  begin  //10e
                     Gerador.wCampo(tcStr, 'N18', 'modBCST ', 01, 01, 1, modBCSTToStr(nfe.Det[i].Imposto.ICMS.modBCST), DSC_MODBCST);
                     Gerador.wCampo(tcDe2, 'N19', 'pMVAST  ', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pMVAST, DSC_PMVAST);
                     Gerador.wCampo(tcDe2, 'N20', 'pRedBCST', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pRedBCST, DSC_PREDBCST);
                     Gerador.wCampo(tcDe2, 'N21', 'vBCST   ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBCST, DSC_VBCST);
                     Gerador.wCampo(tcDe2, 'N22', 'pICMSST ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pICMSST, DSC_PICMSST);
                     Gerador.wCampo(tcDe2, 'N23', 'vICMSST ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMSST, DSC_VICMSST);
                     Gerador.wCampo(tcDe2, 'N29', 'pCredSN    ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pCredSN, DSC_PCREDSN);
                     Gerador.wCampo(tcDe2, 'N30', 'vCredICMSSN', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vCredICMSSN, DSC_VCREDICMSSN);
                  end;
               csosn202,
               csosn203 :
                  begin   //10f
                     Gerador.wCampo(tcStr, 'N18', 'modBCST ', 01, 01, 1, modBCSTToStr(nfe.Det[i].Imposto.ICMS.modBCST), DSC_MODBCST);
                     Gerador.wCampo(tcDe2, 'N19', 'pMVAST  ', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pMVAST, DSC_PMVAST);
                     Gerador.wCampo(tcDe2, 'N20', 'pRedBCST', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pRedBCST, DSC_PREDBCST);
                     Gerador.wCampo(tcDe2, 'N21', 'vBCST   ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBCST, DSC_VBCST);
                     Gerador.wCampo(tcDe2, 'N22', 'pICMSST ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pICMSST, DSC_PICMSST);
                     Gerador.wCampo(tcDe2, 'N23', 'vICMSST ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMSST, DSC_VICMSST);
                  end;
               csosn500 :
                  begin //10g
                     Gerador.wCampo(tcDe2, 'N26', 'vBCSTRet  ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBCSTRET, DSC_VBCSTRET);
                     Gerador.wCampo(tcDe2, 'N27', 'vICMSSTRet', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMSSTRET, DSC_VICMSSTRET);
                  end;
               csosn900:
                 begin //10h
                   Gerador.wCampo(tcStr, 'N13', 'modBC   ', 01, 01, 1, modBCToStr(nfe.Det[i].Imposto.ICMS.modBC), DSC_MODBC);
                   Gerador.wCampo(tcDe2, 'N15', 'vBC     ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBC, DSC_VBC);
                   Gerador.wCampo(tcDe2, 'N14', 'pRedBC  ', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pRedBC, DSC_PREDBC);
                   Gerador.wCampo(tcDe2, 'N16', 'pICMS   ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pICMS, DSC_PICMS);
                   Gerador.wCampo(tcDe2, 'N17', 'vICMS   ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMS, DSC_VICMS);
                   Gerador.wCampo(tcStr, 'N18', 'modBCST ', 01, 01, 1, modBCSTToStr(nfe.Det[i].Imposto.ICMS.modBCST), DSC_MODBCST);
                   Gerador.wCampo(tcDe2, 'N19', 'pMVAST  ', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pMVAST, DSC_PMVAST);
                   Gerador.wCampo(tcDe2, 'N20', 'pRedBCST', 01, 05, 0, nfe.Det[i].Imposto.ICMS.pRedBCST, DSC_PREDBCST);
                   Gerador.wCampo(tcDe2, 'N21', 'vBCST   ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vBCST, DSC_VBCST);
                   Gerador.wCampo(tcDe2, 'N22', 'pICMSST ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pICMSST, DSC_PICMSST);
                   Gerador.wCampo(tcDe2, 'N23', 'vICMSST ', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vICMSST, DSC_VICMSST);
                   Gerador.wCampo(tcDe2, 'N29', 'pCredSN    ', 01, 05, 1, nfe.Det[i].Imposto.ICMS.pCredSN, DSC_PCREDSN);
                   Gerador.wCampo(tcDe2, 'N30', 'vCredICMSSN', 01, 15, 1, nfe.Det[i].Imposto.ICMS.vCredICMSSN, DSC_VCREDICMSSN);
                 end;
            end;

            Gerador.wGrupo('/ICMSSN' + sTagTemp );
         end;
	end;
   //N10a
   //N10b
   //N10c - Simples Nacional
   //N10d - Simples Nacional
   //N10e - Simples Nacional
   //N10f - Simples Nacional
   //N10g - Simples Nacional
   //N10h - Simples Nacional
   Gerador.wGrupo('/ICMS');
end;

procedure TNFeW.GerarDetImpostoIPI(const i: integer);
var
  CST00495099: Boolean;
begin

  // variavel CST00495099 usada para Ignorar Tag <IPI>
  // se GerarTagIPIparaNaoTributado = False e CST00495099 = False

  CST00495099 := (nfe.Det[i].Imposto.IPI.CST in [ipi00, ipi49, ipi50, ipi99]);

  if (not FOpcoes.FGerarTagIPIparaNaoTributado) and (not CST00495099) then
    exit;

  //se valores padr�o de quando n�o foi preenchido a TAG IPI
  if ((nfe.Det[i].Imposto.IPI.cEnq  = '') and
      (nfe.Det[i].Imposto.IPI.CST   = ipi00) and
      (nfe.Det[i].Imposto.IPI.vBC   = 0) and
      (nfe.Det[i].Imposto.IPI.qUnid = 0) and
      (nfe.Det[i].Imposto.IPI.vUnid = 0) and
      (nfe.Det[i].Imposto.IPI.pIPI  = 0) and
      (nfe.Det[i].Imposto.IPI.vIPI  = 0)) then
    EXIT; //n�o deve preencher a TAG

  Gerador.wGrupo('IPI', 'O01');
  Gerador.wCampo(tcStr, 'O02', 'clEnq   ', 05, 05, 0, nfe.Det[i].Imposto.IPI.clEnq, DSC_CLENQ);
  Gerador.wCampo(tcStr, 'O03', 'CNPJProd', 14, 14, 0, nfe.Det[i].Imposto.IPI.CNPJProd, DSC_CNPJPROD);
  Gerador.wCampo(tcStr, 'O04', 'cSelo   ', 01, 60, 0, nfe.Det[i].Imposto.IPI.cSelo, DSC_CSELO);
  Gerador.wCampo(tcInt, 'O05', 'qSelo   ', 01, 12, 0, nfe.Det[i].Imposto.IPI.qSelo, DSC_QSELO);
  if nfe.Det[i].Imposto.IPI.cEnq = '' then
    nfe.Det[i].Imposto.IPI.cEnq := '999';
  Gerador.wCampo(tcStr, 'O06', 'cEnq    ', 03, 03, 1, nfe.Det[i].Imposto.IPI.cEnq, DSC_CENQ);
  if CST00495099 then
  begin

    if (nfe.Det[i].Imposto.IPI.vBC + nfe.Det[i].Imposto.IPI.pIPI > 0) and (nfe.Det[i].Imposto.IPI.qUnid + nfe.Det[i].Imposto.IPI.vUnid > 0) then
      Gerador.wAlerta('O07', 'IPITrib', DSC_IPITrib, 'As TAG <vBC> e <pIPI> n�o podem ser informadas em conjunto com as TAG <qUnid> e <vUnid>');

    if (nfe.Det[i].Imposto.IPI.qUnid + nfe.Det[i].Imposto.IPI.vUnid > 0) then
    begin
      Gerador.wGrupo('IPITrib', 'O07');
      Gerador.wCampo(tcStr, 'O09', 'CST     ', 02, 02, 1, CSTIPITOStr(nfe.Det[i].Imposto.IPI.CST), DSC_CST);
      Gerador.wCampo(tcDe4, 'O11', 'qUnid   ', 01, 16, 1, nfe.Det[i].Imposto.IPI.qUnid, DSC_QUNID);
      Gerador.wCampo(tcDe4, 'O12', 'vUnid   ', 01, 15, 1, nfe.Det[i].Imposto.IPI.vUnid, DSC_VUNID);
      Gerador.wCampo(tcDe2, 'O14', 'vIPI    ', 01, 15, 1, nfe.Det[i].Imposto.IPI.vIPI, DSC_VIPI);
      Gerador.wGrupo('/IPITrib');
    end
    else
    begin
      Gerador.wGrupo('IPITrib', 'O07');
      Gerador.wCampo(tcStr, 'O09', 'CST     ', 02, 02, 1, CSTIPITOStr(nfe.Det[i].Imposto.IPI.CST), DSC_CST);
      Gerador.wCampo(tcDe2, 'O10', 'vBC     ', 01, 15, 1, nfe.Det[i].Imposto.IPI.vBC, DSC_VBC);
      Gerador.wCampo(tcDe2, 'O13', 'pIPI    ', 01, 05, 1, nfe.Det[i].Imposto.IPI.pIPI, DSC_PIPI);
      Gerador.wCampo(tcDe2, 'O14', 'vIPI    ', 01, 15, 1, nfe.Det[i].Imposto.IPI.vIPI, DSC_VIPI);
      Gerador.wGrupo('/IPITrib');
    end;
  end
  else (* Quando CST/IPI for 01,02,03,04,51,52,53,54 ou 55 *)
  begin
    Gerador.wGrupo('IPINT', 'O08');
    Gerador.wCampo(tcStr, 'O09', 'CST     ', 02, 02, 1, CSTIPITOStr(nfe.Det[i].Imposto.IPI.CST), DSC_CST);
    Gerador.wGrupo('/IPINT');
  end;
  Gerador.wGrupo('/IPI');
end;

procedure TNFeW.GerarDetImpostoII(const i: integer);
begin
  if (nfe.Det[i].Imposto.II.vBc > 0) or
     (nfe.Det[i].Imposto.II.vDespAdu > 0) or
     (nfe.Det[i].Imposto.II.vII > 0) or
     (nfe.Det[i].Imposto.II.vIOF > 0) or
     (Copy(nfe.Det[i].Prod.CFOP,1,1) = '3') then
  begin
    Gerador.wGrupo('II', 'P01');
    Gerador.wCampo(tcDe2, 'P02', 'vBC     ', 01, 15, 1, nfe.Det[i].Imposto.II.vBc, DSC_VBC);
    Gerador.wCampo(tcDe2, 'P03', 'vDespAdu', 01, 15, 1, nfe.Det[i].Imposto.II.vDespAdu, DSC_VDESPADU);
    Gerador.wCampo(tcDe2, 'P04', 'vII     ', 01, 15, 1, nfe.Det[i].Imposto.II.vII, DSC_VII);
    Gerador.wCampo(tcDe2, 'P04', 'vIOF    ', 01, 15, 1, nfe.Det[i].Imposto.II.vIOF, DSC_VIOF);
    Gerador.wGrupo('/II');
  end;
end;

procedure TNFeW.GerarDetImpostoPIS(const i: integer);
begin
  Gerador.wGrupo('PIS', 'Q01');
  if nfe.Det[i].Imposto.PIS.CST in [pis01, pis02] then
  begin
    Gerador.wGrupo('PISAliq', 'Q02');
    Gerador.wCampo(tcStr, 'Q06', 'CST      ', 02, 02, 1, CSTPISTOStr(nfe.Det[i].Imposto.PIS.CST), DSC_CST);
    Gerador.wCampo(tcDe2, 'Q07', 'vBC      ', 01, 15, 1, nfe.Det[i].Imposto.PIS.vBC, DSC_VBC);
    Gerador.wCampo(tcDe2, 'Q08', 'pPIS     ', 01, 05, 1, nfe.Det[i].Imposto.PIS.pPIS, DSC_PPIS);
    Gerador.wCampo(tcDe2, 'Q09', 'vPIS     ', 01, 15, 1, nfe.Det[i].Imposto.PIS.vPIS, DSC_VPIS);
    Gerador.wGrupo('/PISAliq');
  end
  else if nfe.Det[i].Imposto.PIS.CST = pis03 then
  begin
    Gerador.wGrupo('PISQtde', 'Q03');
    Gerador.wCampo(tcStr, 'Q06', 'CST      ', 02, 02, 1, CSTPISTOStr(nfe.Det[i].Imposto.PIS.CST), DSC_CST);
    Gerador.wCampo(tcDe4, 'Q10', 'qBCProd  ', 01, 16, 1, nfe.Det[i].Imposto.PIS.qBCProd, DSC_QBCPROD);
    Gerador.wCampo(tcDe4, 'Q11', 'vAliqProd', 01, 15, 1, nfe.Det[i].Imposto.PIS.vAliqProd, DSC_VALIQPROD);
    Gerador.wCampo(tcDe2, 'Q09', 'vPIS     ', 01, 15, 1, nfe.Det[i].Imposto.PIS.vPIS, DSC_VPIS);
    Gerador.wGrupo('/PISQtde');
  end
  else if nfe.Det[i].Imposto.PIS.CST in [pis04, pis06, pis07, pis08, pis09] then
  begin
    Gerador.wGrupo('PISNT', 'Q04');
    Gerador.wCampo(tcStr, 'Q06', 'CST      ', 02, 02, 1, CSTPISTOStr(nfe.Det[i].Imposto.PIS.CST), DSC_CST);
    Gerador.wGrupo('/PISNT');
  end
  else if nfe.Det[i].Imposto.PIS.CST in [pis49, pis50, pis51, pis52, pis53, pis54, pis55, pis56, pis60, pis61, pis62, pis63, pis64, pis65, pis66, pis67, pis70, pis71, pis72, pis73, pis74, pis75, pis98, pis99] then
  begin

    if (NFe.Det[i].Imposto.PIS.vBC + NFe.Det[i].Imposto.PIS.pPIS > 0) and (NFe.Det[i].Imposto.PIS.qBCProd + NFe.Det[i].Imposto.PIS.vAliqProd > 0) then
      Gerador.wAlerta('Q05', 'PISOutr', DSC_PISOUTR, 'As TAG <vBC> e <pPIS> n�o podem ser informadas em conjunto com as TAG <qBCProd> e <vAliqProd>');

    if (NFe.Det[i].Imposto.PIS.qBCProd + NFe.Det[i].Imposto.PIS.vAliqProd > 0) then
    begin
      Gerador.wGrupo('PISOutr', 'Q05');
      Gerador.wCampo(tcStr, 'Q06', 'CST      ', 02, 02, 1, CSTPISTOStr(nfe.Det[i].Imposto.PIS.CST), DSC_CST);
      Gerador.wCampo(tcDe4, 'Q10', 'qBCProd  ', 01, 16, 1, nfe.Det[i].Imposto.PIS.qBCProd, DSC_QBCPROD);
      Gerador.wCampo(tcDe4, 'Q11', 'vAliqProd', 01, 15, 1, nfe.Det[i].Imposto.PIS.vAliqProd, DSC_VALIQPROD);
      Gerador.wCampo(tcDe2, 'Q09', 'vPIS     ', 01, 15, 1, nfe.Det[i].Imposto.PIS.vPIS, DSC_VPIS);
      Gerador.wGrupo('/PISOutr');
    end
    else
    begin
      Gerador.wGrupo('PISOutr', 'Q05');
      Gerador.wCampo(tcStr, 'Q06', 'CST      ', 02, 02, 1, CSTPISTOStr(nfe.Det[i].Imposto.PIS.CST), DSC_CST);
      Gerador.wCampo(tcDe2, 'Q07', 'vBC      ', 01, 15, 1, nfe.Det[i].Imposto.PIS.vBC, DSC_VBC);
      Gerador.wCampo(tcDe2, 'Q08', 'pPIS     ', 01, 05, 1, nfe.Det[i].Imposto.PIS.pPIS, DSC_PPIS);
      Gerador.wCampo(tcDe2, 'Q09', 'vPIS     ', 01, 15, 1, nfe.Det[i].Imposto.PIS.vPIS, DSC_VPIS);
      Gerador.wGrupo('/PISOutr');
    end;
  end;
  Gerador.wGrupo('/PIS');
end;

procedure TNFeW.GerarDetImpostoPISST(const i: integer);
begin
  if (nfe.Det[i].Imposto.PISST.vBc > 0) or
    (nfe.Det[i].Imposto.PISST.pPis > 0) or
    (nfe.Det[i].Imposto.PISST.qBCProd > 0) or
    (nfe.Det[i].Imposto.PISST.vAliqProd > 0) or
    (nfe.Det[i].Imposto.PISST.vPIS > 0) then
  begin

    if (nfe.Det[i].Imposto.PISST.vBc + nfe.Det[i].Imposto.PISST.pPis > 0) and (nfe.Det[i].Imposto.PISST.qBCProd + nfe.Det[i].Imposto.PISST.vAliqProd > 0) then
      Gerador.wAlerta('R01', 'PISST', DSC_PISOUTR, 'As TAG <vBC> e <pPIS> n�o podem ser informadas em conjunto com as TAG <qBCProd> e <vAliqProd>');

    if (nfe.Det[i].Imposto.PISST.vBc + nfe.Det[i].Imposto.PISST.pPis > 0) then
    begin
      Gerador.wGrupo('PISST', 'R01');
      Gerador.wCampo(tcDe2, 'R02', 'vBC      ', 01, 15, 1, nfe.Det[i].Imposto.PISST.vBc, DSC_VBC);
      Gerador.wCampo(tcDe2, 'R03', 'pPIS     ', 01, 05, 1, nfe.Det[i].Imposto.PISST.pPis, DSC_PPIS);
      Gerador.wCampo(tcDe2, 'R06', 'vPIS     ', 01, 15, 1, nfe.Det[i].Imposto.PISST.vPIS, DSC_VPIS);
      Gerador.wGrupo('/PISST');
    end;
    if (nfe.Det[i].Imposto.PISST.qBCProd + nfe.Det[i].Imposto.PISST.vAliqProd > 0) then
    begin
      Gerador.wGrupo('PISST', 'R01');
      Gerador.wCampo(tcDe4, 'R04', 'qBCProd  ', 01, 16, 1, nfe.Det[i].Imposto.PISST.qBCProd, DSC_QBCPROD);
      Gerador.wCampo(tcDe4, 'R05', 'vAliqProd', 01, 15, 1, nfe.Det[i].Imposto.PISST.vAliqProd, DSC_VALIQPROD);
      Gerador.wCampo(tcDe2, 'R06', 'vPIS     ', 01, 15, 1, nfe.Det[i].Imposto.PISST.vPIS, DSC_VPIS);
      Gerador.wGrupo('/PISST');
    end;
  end;
end;

procedure TNFeW.GerarDetImpostoCOFINS(const i: integer);
begin
  Gerador.wGrupo('COFINS', 'S01');
  if nfe.Det[i].Imposto.COFINS.CST in [cof01, cof02] then
  begin
    Gerador.wGrupo('COFINSAliq', 'S02');
    Gerador.wCampo(tcStr, 'S06', 'CST      ', 02, 02, 1, CSTCOFINSTOStr(nfe.Det[i].Imposto.COFINS.CST), DSC_CST);
    Gerador.wCampo(tcDe2, 'S07', 'vBC      ', 01, 15, 1, nfe.Det[i].Imposto.COFINS.vBC, DSC_VBC);
    Gerador.wCampo(tcDe2, 'S08', 'pCOFINS  ', 01, 05, 1, nfe.Det[i].Imposto.COFINS.pCOFINS, DSC_PCOFINS);
    Gerador.wCampo(tcDe2, 'S11', 'vCOFINS  ', 01, 15, 1, nfe.Det[i].Imposto.COFINS.vCOFINS, DSC_VCOFINS);
    Gerador.wGrupo('/COFINSAliq');
  end
  else if nfe.Det[i].Imposto.COFINS.CST = cof03 then
  begin
    Gerador.wGrupo('COFINSQtde', 'S03');
    Gerador.wCampo(tcStr, 'S06', 'CST      ', 02, 02, 1, CSTCOFINSTOStr(nfe.Det[i].Imposto.COFINS.CST), DSC_CST);
    Gerador.wCampo(tcDe4, 'S09', 'qBCProd  ', 01, 16, 1, nfe.Det[i].Imposto.COFINS.qBCProd, DSC_QBCPROD);
    Gerador.wCampo(tcDe4, 'S10', 'vAliqProd', 01, 15, 1, nfe.Det[i].Imposto.COFINS.vAliqProd, DSC_VALIQPROD);
    Gerador.wCampo(tcDe2, 'S11', 'vCOFINS  ', 01, 15, 1, nfe.Det[i].Imposto.COFINS.vCOFINS, DSC_VCOFINS);
    Gerador.wGrupo('/COFINSQtde');
  end
  else if nfe.Det[i].Imposto.COFINS.CST in [cof04, cof06, cof07, cof08, cof09] then
  begin
    Gerador.wGrupo('COFINSNT', 'S04');
    Gerador.wCampo(tcStr, 'S06', 'CST      ', 02, 02, 1, CSTCOFINSTOStr(nfe.Det[i].Imposto.COFINS.CST), DSC_CST);
    Gerador.wGrupo('/COFINSNT');
  end
  else if nfe.Det[i].Imposto.COFINS.CST in [cof49, cof50, cof51, cof52, cof53, cof54, cof55, cof56, cof60, cof61, cof62, cof63, cof64, cof65, cof66, cof67, cof70, cof71, cof72, cof73, cof74, cof75, cof98, cof99] then
  begin

    if (nfe.Det[i].Imposto.COFINS.vBC + nfe.Det[i].Imposto.COFINS.pCOFINS > 0) and (nfe.Det[i].Imposto.COFINS.qBCProd + nfe.Det[i].Imposto.COFINS.vAliqProd > 0) then
      Gerador.wAlerta('S05', 'COFINSOutr', DSC_PISOUTR, 'As TAG <vBC> e <pCOFINS> n�o podem ser informadas em conjunto com as TAG <qBCProd> e <vAliqProd>');

    if (nfe.Det[i].Imposto.COFINS.qBCProd + nfe.Det[i].Imposto.COFINS.vAliqProd > 0) then
    begin
      Gerador.wGrupo('COFINSOutr', 'S05');
      Gerador.wCampo(tcStr, 'S06', 'CST      ', 02, 02, 1, CSTCOFINSTOStr(nfe.Det[i].Imposto.COFINS.CST), DSC_CST);
      Gerador.wCampo(tcDe4, 'S09', 'qBCProd  ', 01, 16, 1, nfe.Det[i].Imposto.COFINS.qBCProd, DSC_QBCPROD);
      Gerador.wCampo(tcDe4, 'S10', 'vAliqProd', 01, 15, 1, nfe.Det[i].Imposto.COFINS.vAliqProd, DSC_VALIQPROD);
      Gerador.wCampo(tcDe2, 'S11', 'vCOFINS  ', 01, 15, 1, nfe.Det[i].Imposto.COFINS.vCOFINS, DSC_VCOFINS);
      Gerador.wGrupo('/COFINSOutr');
    end
    else
    begin
      Gerador.wGrupo('COFINSOutr', 'S05');
      Gerador.wCampo(tcStr, 'S06', 'CST      ', 02, 02, 1, CSTCOFINSTOStr(nfe.Det[i].Imposto.COFINS.CST), DSC_CST);
      Gerador.wCampo(tcDe2, 'S07', 'vBC      ', 01, 15, 1, nfe.Det[i].Imposto.COFINS.vBC, DSC_VBC);
      Gerador.wCampo(tcDe2, 'S08', 'pCOFINS  ', 01, 05, 1, nfe.Det[i].Imposto.COFINS.pCOFINS, DSC_PCOFINS);
      Gerador.wCampo(tcDe2, 'S11', 'vCOFINS  ', 01, 15, 1, nfe.Det[i].Imposto.COFINS.vCOFINS, DSC_VCOFINS);
      Gerador.wGrupo('/COFINSOutr');
    end;
  end;
  Gerador.wGrupo('/COFINS');
end;

procedure TNFeW.GerarDetImpostoCOFINSST(const i: integer);
begin
  if (nfe.Det[i].Imposto.COFINSST.vBC > 0) or
    (nfe.Det[i].Imposto.COFINSST.pCOFINS > 0) or
    (nfe.Det[i].Imposto.COFINSST.qBCProd > 0) or
    (nfe.Det[i].Imposto.COFINSST.vAliqProd > 0) or
    (nfe.Det[i].Imposto.COFINSST.vCOFINS > 0) then
  begin

    if (nfe.Det[i].Imposto.COFINSST.vBC + nfe.Det[i].Imposto.COFINSST.pCOFINS > 0) and (nfe.Det[i].Imposto.COFINSST.qBCProd + nfe.Det[i].Imposto.COFINSST.vAliqProd > 0) then
      Gerador.wAlerta('T01', 'COFINSST', DSC_COFINSST, 'As TAG <vBC> e <pCOFINS> n�o podem ser informadas em conjunto com as TAG <qBCProd> e <vAliqProd>');

    if (nfe.Det[i].Imposto.COFINSST.vBC + nfe.Det[i].Imposto.COFINSST.pCOFINS > 0) then
    begin
      Gerador.wGrupo('COFINSST', 'T01');
      Gerador.wCampo(tcDe2, 'T02', 'vBC        ', 01, 15, 1, nfe.Det[i].Imposto.COFINSST.vBC, DSC_VBC);
      Gerador.wCampo(tcDe2, 'T03', 'pCOFINS    ', 01, 05, 1, nfe.Det[i].Imposto.COFINSST.pCOFINS, DSC_PCOFINS);
      Gerador.wCampo(tcDe2, 'T06', 'vCOFINS    ', 01, 15, 1, nfe.Det[i].Imposto.COFINSST.vCOFINS, DSC_VCOFINS);
      Gerador.wGrupo('/COFINSST');
    end;
    if (nfe.Det[i].Imposto.COFINSST.qBCProd + nfe.Det[i].Imposto.COFINSST.vAliqProd > 0) then
    begin
      Gerador.wGrupo('COFINSST', 'T01');
      Gerador.wCampo(tcDe4, 'T04', 'qBCProd    ', 01, 16, 1, nfe.Det[i].Imposto.COFINSST.qBCProd, DSC_QBCPROD);
      Gerador.wCampo(tcDe4, 'T05', 'vAliqProd  ', 01, 15, 1, nfe.Det[i].Imposto.COFINSST.vAliqProd, DSC_VALIQPROD);
      Gerador.wCampo(tcDe2, 'T06', 'vCOFINS    ', 01, 15, 1, nfe.Det[i].Imposto.COFINSST.vCOFINS, DSC_VCOFINS);
      Gerador.wGrupo('/COFINSST');
    end;
  end;
end;

procedure TNFeW.GerarDetImpostoISSQN(const i: integer);
begin
  if (nfe.Det[i].Imposto.ISSQN.vBC > 0) or
    (nfe.Det[i].Imposto.ISSQN.vAliq > 0) or
    (nfe.Det[i].Imposto.ISSQN.vISSQN > 0) or
    (nfe.Det[i].Imposto.ISSQN.cMunFG > 0) or
    (nfe.Det[i].Imposto.ISSQN.cListServ > 0) then
  begin
    Gerador.wGrupo('ISSQN', 'U01');
    Gerador.wCampo(tcDe2, 'U02', 'vBC        ', 01, 15, 1, nfe.Det[i].Imposto.ISSQN.vBC, DSC_VBCISS);
    Gerador.wCampo(tcDe2, 'U03', 'vAliq      ', 01, 05, 1, nfe.Det[i].Imposto.ISSQN.vAliq, DSC_VAliq);
    Gerador.wCampo(tcDe2, 'U04', 'vISSQN     ', 01, 15, 1, nfe.Det[i].Imposto.ISSQN.vISSQN, DSC_VISSQN);
    Gerador.wCampo(tcInt, 'U05', 'cMunFG     ', 07, 07, 1, nfe.Det[i].Imposto.ISSQN.cMunFG, DSC_CMUNFG);
    if not ValidarMunicipio(nfe.Det[i].Imposto.ISSQN.cMunFG) then
      Gerador.wAlerta('U05', 'cMunFG', DSC_CMUNFG, ERR_MSG_INVALIDO);
    Gerador.wCampo(tcInt, 'U06', 'cListServ  ', 03, 04, 1, nfe.Det[i].Imposto.ISSQN.cListServ, DSC_CLISTSERV);
    if (FOpcoes.ValidarListaServicos) and (nfe.Det[i].Imposto.ISSQN.cListServ <> 0) then
      if not ValidarCListServ(nfe.Det[i].Imposto.ISSQN.cListServ) then
      Gerador.wAlerta('U06', 'cListServ', DSC_CLISTSERV, ERR_MSG_INVALIDO);
    if NFe.infNFe.Versao >= 2 then
       Gerador.wCampo(tcStr, 'U07', 'cSitTrib', 01, 01, 1, ISSQNcSitTribToStr( nfe.Det[i].Imposto.ISSQN.cSitTrib ) , DSC_CSITTRIB);
    Gerador.wGrupo('/ISSQN');
  end;
end;

procedure TNFeW.GerarTotal;
begin
  Gerador.wGrupo('total', 'W01');
  (**)GerarTotalICMSTotal;
  (**)GerarTotalISSQNtot;
  (**)GerarTotalretTrib;
  Gerador.wGrupo('/total');
end;

procedure TNFeW.GerarTotalICMSTotal;
begin
  Gerador.wGrupo('ICMSTot', 'W02');
  Gerador.wCampo(tcDe2, 'W03', 'vBC       ', 01, 15, 1, nfe.Total.ICMSTot.vBC, DSC_VBC);
  Gerador.wCampo(tcDe2, 'W04', 'vICMS     ', 01, 15, 1, nfe.Total.ICMSTot.vICMS, DSC_VICMS);
  Gerador.wCampo(tcDe2, 'W05', 'vBCST     ', 01, 15, 1, nfe.Total.ICMSTot.vBCST, DSC_VBCST);
  Gerador.wCampo(tcDe2, 'W06', 'vST       ', 01, 15, 1, nfe.Total.ICMSTot.vST, DSC_VST);
  Gerador.wCampo(tcDe2, 'W07', 'vProd     ', 01, 15, 1, nfe.Total.ICMSTot.vProd, DSC_VPROD);
  Gerador.wCampo(tcDe2, 'W08', 'vFrete    ', 01, 15, 1, nfe.Total.ICMSTot.vFrete, DSC_VFRETE);
  Gerador.wCampo(tcDe2, 'W09', 'vSeg      ', 01, 15, 1, nfe.Total.ICMSTot.vSeg, DSC_VSEG);
  Gerador.wCampo(tcDe2, 'W10', 'vDesc     ', 01, 15, 1, nfe.Total.ICMSTot.vDesc, DSC_VDESC);
  Gerador.wCampo(tcDe2, 'W11', 'vII       ', 01, 15, 1, nfe.Total.ICMSTot.vII, DSC_VII);
  Gerador.wCampo(tcDe2, 'W12', 'vIPI      ', 01, 15, 1, nfe.Total.ICMSTot.vIPI, DSC_VIPI);
  Gerador.wCampo(tcDe2, 'W13', 'vPIS      ', 01, 15, 1, nfe.Total.ICMSTot.vPIS, DSC_VPIS);
  Gerador.wCampo(tcDe2, 'W14', 'vCOFINS   ', 01, 15, 1, nfe.Total.ICMSTot.vCOFINS, DSC_VCOFINS);
  Gerador.wCampo(tcDe2, 'W15', 'vOutro    ', 01, 15, 1, nfe.Total.ICMSTot.vOutro, DSC_VOUTRO);
  Gerador.wCampo(tcDe2, 'W16', 'vNF       ', 01, 15, 1, nfe.Total.ICMSTot.vNF, DSC_VNF);
  Gerador.wGrupo('/ICMSTot');
end;

procedure TNFeW.GerarTotalISSQNtot;
begin
  if (nfe.Total.ISSQNtot.vServ > 0) or
    (nfe.Total.ISSQNtot.vBC > 0) or
    (nfe.Total.ISSQNtot.vISS > 0) or
    (nfe.Total.ISSQNtot.vPIS > 0) or
    (nfe.Total.ISSQNtot.vCOFINS > 0) then
  begin
    Gerador.wGrupo('ISSQNtot', 'W17');
    Gerador.wCampo(tcDe2, 'W18', 'vServ     ', 01, 15, 0, nfe.Total.ISSQNtot.vServ, DSC_VSERV);
    Gerador.wCampo(tcDe2, 'W19', 'vBC       ', 01, 15, 0, nfe.Total.ISSQNtot.vBC, DSC_VBC);
    Gerador.wCampo(tcDe2, 'W20', 'vISS      ', 01, 15, 0, nfe.Total.ISSQNtot.vISS, DSC_VISS);
    Gerador.wCampo(tcDe2, 'W21', 'vPIS      ', 01, 15, 0, nfe.Total.ISSQNtot.vPIS, DSC_VPIS);
    Gerador.wCampo(tcDe2, 'W22', 'vCOFINS   ', 01, 15, 0, nfe.Total.ISSQNtot.vCOFINS, DSC_VCOFINS);
    Gerador.wGrupo('/ISSQNtot');
  end;
end;

procedure TNFeW.GerarTotalretTrib;
begin
  if (nfe.Total.retTrib.vRetPIS > 0) or
    (nfe.Total.retTrib.vRetCOFINS > 0) or
    (nfe.Total.retTrib.vRetCSLL > 0) or
    (nfe.Total.retTrib.vBCIRRF > 0) or
    (nfe.Total.retTrib.vIRRF > 0) or
    (nfe.Total.retTrib.vBCRetPrev > 0) or
    (nfe.Total.retTrib.vRetPrev > 0) then
  begin
    Gerador.wGrupo('retTrib', 'W23');
    Gerador.wCampo(tcDe2, 'W24', 'vRetPIS   ', 01, 15, 0, nfe.Total.retTrib.vRetPIS, DSC_VRETPIS);
    Gerador.wCampo(tcDe2, 'W25', 'vRetCOFINS', 01, 15, 0, nfe.Total.retTrib.vRetCOFINS, DSC_VRETCOFINS);
    Gerador.wCampo(tcDe2, 'W26', 'vRetCSLL  ', 01, 15, 0, nfe.Total.retTrib.vRetCSLL, DSC_VRETCSLL);
    Gerador.wCampo(tcDe2, 'W27', 'vBCIRRF   ', 01, 15, 0, nfe.Total.retTrib.vBCIRRF, DSC_VBCIRRF);
    Gerador.wCampo(tcDe2, 'W28', 'vIRRF     ', 01, 15, 0, nfe.Total.retTrib.vIRRF, DSC_VIRRF);
    Gerador.wCampo(tcDe2, 'W29', 'vBCRetPrev', 01, 15, 0, nfe.Total.retTrib.vBCRetPrev, DSC_VBCRETPREV);
    Gerador.wCampo(tcDe2, 'W30', 'vRetPrev  ', 01, 15, 0, nfe.Total.retTrib.vRetPrev, DSC_VRETPREV);
    Gerador.wGrupo('/retTrib');
  end;
end;

procedure TNFeW.GerarCobr;
begin
  if (trim(nfe.Cobr.Fat.nFat) <> '') or
    (nfe.Cobr.Fat.vOrig > 0) or
    (nfe.Cobr.Fat.vDesc > 0) or
    (nfe.Cobr.Fat.vLiq > 0) or
    (nfe.Cobr.Dup.Count > 0) then
  begin
    Gerador.wGrupo('cobr', 'Y01');
    (**)GerarCobrFat;
    (**)GerarCobrDup;
    Gerador.wGrupo('/cobr');
  end;
end;

procedure TNFeW.GerarCobrFat;
begin
  if (trim(nfe.Cobr.Fat.nFat) <> '') or
    (nfe.Cobr.Fat.vOrig > 0) or
    (nfe.Cobr.Fat.vDesc > 0) or
    (nfe.Cobr.Fat.vLiq > 0) then
  begin
    Gerador.wGrupo('fat', 'Y02');
    Gerador.wCampo(tcStr, 'Y03', 'nFat   ', 01, 60, 0, nfe.Cobr.Fat.nFat, DSC_NFAT);
    Gerador.wCampo(tcDe2, 'Y04', 'vOrig  ', 01, 15, 0, nfe.Cobr.Fat.vOrig, DSC_VORIG);
    Gerador.wCampo(tcDe2, 'Y05', 'vDesc  ', 01, 15, 0, nfe.Cobr.Fat.vDesc, DSC_VDESC);
    Gerador.wCampo(tcDe2, 'Y06', 'vLiq   ', 01, 15, 0, nfe.Cobr.Fat.vLiq, DSC_VLIQ);
    Gerador.wGrupo('/fat');
  end;
end;

procedure TNFeW.GerarCobrDup;
var
  i: integer;
begin
  for i := 0 to nfe.Cobr.Dup.Count - 1 do
  begin
    Gerador.wGrupo('dup', 'Y07');
    Gerador.wCampo(tcStr, 'Y08', 'nDup ', 01, 60, 0, nfe.Cobr.Dup[i].nDup, DSC_NDUP);
    Gerador.wCampo(tcDat, 'Y09', 'dVenc', 10, 10, 0, nfe.Cobr.Dup[i].dVenc, DSC_DVENC);
    Gerador.wCampo(tcDe2, 'Y10', 'vDup ', 01, 15, 1, nfe.Cobr.Dup[i].vDup, DSC_VDUP);
    Gerador.wGrupo('/dup');
  end;
  if nfe.Cobr.Dup.Count > 120 then
    Gerador.wAlerta('Y07', 'dup', DSC_NITEM, ERR_MSG_MAIOR_MAXIMO + '120');
end;

procedure TNFeW.GerarTransp;
begin
  Gerador.wGrupo('transp', 'X01');
  Gerador.wCampo(tcStr, 'X02', 'modFrete', 01, 01, 1, modFreteToStr(nfe.Transp.modFrete), DSC_MODFRETE);
  (**)GerarTranspTransporta;
  (**)GerarTranspRetTransp;
  (**)GerarTranspVeicTransp;
  (**)GerarTranspReboque;
  Gerador.wCampo(tcStr, 'X25a','vagao ', 01, 20, 0, nfe.Transp.vagao, DSC_VAGAO);
  Gerador.wCampo(tcStr, 'X25b','balsa ', 01, 20, 0, nfe.Transp.balsa, DSC_BALSA);
  (**)GerarTranspVol;
  Gerador.wGrupo('/transp');
end;

procedure TNFeW.GerarTranspTransporta;
begin
  if (trim(nfe.Transp.Transporta.CNPJCPF) <> '') or
    (trim(nfe.Transp.Transporta.xNome) <> '') or
    (trim(nfe.Transp.Transporta.IE) <> '') or
    (trim(nfe.Transp.Transporta.xEnder) <> '') or
    (trim(nfe.Transp.Transporta.xMun) <> '') or
    (trim(nfe.Transp.Transporta.UF) <> '') then
  begin
    Gerador.wGrupo('transporta', 'X03');
    if trim(nfe.Transp.Transporta.CNPJCPF) <> '' then
       Gerador.wCampoCNPJCPF('X04', 'X05', nfe.Transp.Transporta.CNPJCPF, CODIGO_BRASIL);
    Gerador.wCampo(tcStr, 'X06', 'xNome   ', 01, 60, 0, nfe.Transp.Transporta.xNome, DSC_XNOME);
    if trim(nfe.Transp.Transporta.IE) = 'ISENTO' then
       Gerador.wCampo(tcStr, 'X07', 'IE      ', 02, 14, 0, nfe.Transp.Transporta.IE, DSC_IE)
    else
     begin
       Gerador.wCampo(tcStr, 'X07', 'IE      ', 02, 14, 0, SomenteNumeros(nfe.Transp.Transporta.IE), DSC_IE);
       if (FOpcoes.ValidarInscricoes) and (nfe.Transp.Transporta.IE <> '') then
         if not ValidarIE(nfe.Transp.Transporta.IE, nfe.Transp.Transporta.UF) then
           Gerador.wAlerta('X07', 'IE', DSC_IE, ERR_MSG_INVALIDO);
     end;
    Gerador.wCampo(tcStr, 'X08', 'xEnder  ', 01, 60, 0, nfe.Transp.Transporta.xEnder, DSC_XENDER);
    Gerador.wCampo(tcStr, 'X09', 'xMun    ', 01, 60, 0, nfe.Transp.Transporta.xMun, DSC_XMUN);
    if trim(nfe.Transp.Transporta.UF) <> '' then
     begin
       Gerador.wCampo(tcStr, 'X10', 'UF      ', 01, 02, 0, nfe.Transp.Transporta.UF, DSC_UF);
       if not ValidarUF(nfe.Transp.Transporta.UF) then
         Gerador.wAlerta('X10', 'UF', DSC_UF, ERR_MSG_INVALIDO);
     end;
    Gerador.wGrupo('/transporta');
  end;
end;

procedure TNFeW.GerarTranspRetTransp;
begin
  if (nfe.Transp.retTransp.vServ > 0) or
    (nfe.Transp.retTransp.vBCRet > 0) or
    (nfe.Transp.retTransp.pICMSRet > 0) or
    (nfe.Transp.retTransp.vICMSRet > 0) or
    (trim(nfe.Transp.retTransp.CFOP) <> '') or
    (nfe.Transp.retTransp.cMunFG > 0) then
  begin
    Gerador.wGrupo('retTransp', 'X11');
    Gerador.wCampo(tcDe2, 'X12', 'vServ   ', 01, 15, 1, nfe.Transp.retTransp.vServ, DSC_VSERV);
    Gerador.wCampo(tcDe2, 'X13', 'vBCRet  ', 01, 15, 1, nfe.Transp.retTransp.vBCRet, DSC_VBCRET);
    Gerador.wCampo(tcDe2, 'X14', 'pICMSRet', 01, 05, 1, nfe.Transp.retTransp.pICMSRet, DSC_PICMSRET);
    Gerador.wCampo(tcDe2, 'X15', 'vICMSRet', 01, 15, 1, nfe.Transp.retTransp.vICMSRet, DSC_VICMSRET);
    Gerador.wCampo(tcEsp, 'X16', 'CFOP    ', 04, 04, 1, SomenteNumeros(nfe.Transp.retTransp.CFOP), DSC_CFOP);
    Gerador.wCampo(tcStr, 'X17', 'cMunFG  ', 07, 07, 1, nfe.Transp.retTransp.cMunFG, DSC_CMUNFG);
    if not ValidarMunicipio(nfe.Transp.retTransp.cMunFG) then
      Gerador.wAlerta('X17', 'cMunFG', DSC_CMUNFG, ERR_MSG_INVALIDO);
    Gerador.wGrupo('/retTransp');
  end;
end;

procedure TNFeW.GerarTranspVeicTransp;
begin
  if (trim(nfe.Transp.veicTransp.placa) <> '') or
    (trim(nfe.Transp.veicTransp.UF) <> '') or
    (trim(nfe.Transp.veicTransp.RNTC) <> '') then
  begin
    Gerador.wGrupo('veicTransp', 'X18');
    Gerador.wCampo(tcStr, 'X19', 'placa   ', 06, 07, 1, nfe.Transp.veicTransp.placa, DSC_PLACA);
    Gerador.wCampo(tcStr, 'X20', 'UF      ', 02, 02, 1, nfe.Transp.veicTransp.UF, DSC_UF);
    if not ValidarUF(nfe.Transp.veicTransp.UF) then
      Gerador.wAlerta('X20', 'UF', DSC_UF, ERR_MSG_INVALIDO);
    Gerador.wCampo(tcStr, 'X21', 'RNTC    ', 01, 20, 0, nfe.Transp.veicTransp.RNTC, DSC_RNTC);
    Gerador.wGrupo('/veicTransp');
  end;
end;

procedure TNFeW.GerarTranspReboque;
var
  i: integer;
begin
  if nfe.Transp.Reboque.Count > 5 then
    Gerador.wAlerta('X22', 'reboque', DSC_REBOQUE, ERR_MSG_MAIOR_MAXIMO + '5');
  for i := 0 to nfe.Transp.Reboque.Count - 1 do
  begin
    Gerador.wGrupo('reboque', 'X22');
    Gerador.wCampo(tcStr, 'X23', 'placa ', 06, 07, 1, nfe.Transp.Reboque[i].placa, DSC_PLACA);
    Gerador.wCampo(tcStr, 'X24', 'UF    ', 02, 02, 1, nfe.Transp.Reboque[i].UF, DSC_UF);
    if not ValidarUF(nfe.Transp.Reboque[i].UF) then
      Gerador.wAlerta('X24', 'UF', DSC_UF, ERR_MSG_INVALIDO);
    Gerador.wCampo(tcStr, 'X25', 'RNTC  ', 01, 20, 0, nfe.Transp.Reboque[i].RNTC, DSC_RNTC);
    Gerador.wGrupo('/reboque');
  end;
end;

procedure TNFeW.GerarTranspVol;
var
  i: integer;
begin
  for i := 0 to nfe.Transp.Vol.Count - 1 do
  begin
    Gerador.wGrupo('vol', 'X26');
    Gerador.wCampo(tcInt, 'X27', 'qVol  ', 01, 15, 1, nfe.Transp.Vol[i].qVol, DSC_QVOL);
    Gerador.wCampo(tcStr, 'X28', 'esp   ', 01, 60, 0, nfe.Transp.vol[i].esp, DSC_ESP);
    Gerador.wCampo(tcStr, 'X29', 'marca ', 01, 60, 0, nfe.Transp.Vol[i].marca, DSC_MARCA);
    Gerador.wCampo(tcStr, 'X30', 'nVol  ', 01, 60, 0, nfe.Transp.Vol[i].nVol, DSC_NVOL);
    Gerador.wCampo(tcDe3, 'X31', 'pesoL ', 01, 15, 0, nfe.Transp.Vol[i].pesoL, DSC_PESOL);
    Gerador.wCampo(tcDe3, 'X32', 'pesoB ', 01, 15, 0, nfe.Transp.Vol[i].pesoB, DSC_PESOB);
    (**)GerarTranspVolLacres(i);
    Gerador.wGrupo('/vol');
  end;
end;

procedure TNFeW.GerarTranspVolLacres(i: integer);
var
  j: integer;
begin
  for j := 0 to nfe.transp.Vol[i].lacres.Count - 1 do
  begin
    Gerador.wGrupo('lacres', 'X33');
    Gerador.wCampo(tcStr, 'X34', 'nLacre', 01, 60, 1, nfe.transp.Vol[i].lacres[j].nLacre, DSC_NLACRE);
    Gerador.wGrupo('/lacres');
  end;
end;

procedure TNFeW.GerarInfAdic;
begin
  if (trim(nfe.InfAdic.infAdFisco) <> EmptyStr) or
    (trim(nfe.InfAdic.infCpl) <> EmptyStr) or
    (nfe.InfAdic.obsCont.Count > 0) or
    (nfe.InfAdic.obsFisco.Count > 0) or
    (nfe.InfAdic.procRef.Count > 0) then
  begin
    Gerador.wGrupo('infAdic', 'Z01');
    Gerador.wCampo(tcStr, 'Z02', 'infAdFisco', 01, 2000, 0, nfe.InfAdic.infAdFisco, DSC_INFADFISCO);
    Gerador.wCampo(tcStr, 'Z03', 'infCpl    ', 01, 5000, 0, nfe.InfAdic.infCpl, DSC_INFCPL);
    (**)GerarInfAdicObsCont;
    (**)GerarInfAdicObsFisco;
    (**)GerarInfAdicProcRef;
    Gerador.wGrupo('/infAdic');
  end;
end;

procedure TNFeW.GerarInfAdicObsCont;
var
  i: integer;
begin
  if nfe.InfAdic.obsCont.Count > 10 then
    Gerador.wAlerta('Z04', 'obsCont', DSC_OBSCONT, ERR_MSG_MAIOR_MAXIMO + '10');
  for i := 0 to nfe.InfAdic.obsCont.Count - 1 do
  begin
    Gerador.wGrupo('obsCont xCampo="' + nfe.InfAdic.obsCont[i].xCampo + '"', 'Z04');
    if length(trim(nfe.InfAdic.obsCont[i].xCampo)) > 20 then
      Gerador.wAlerta('ZO5', 'xCampo', DSC_XCAMPO, ERR_MSG_MAIOR);
    if length(trim(nfe.InfAdic.obsCont[i].xCampo)) = 0 then
      Gerador.wAlerta('ZO5', 'xCampo', DSC_XCAMPO, ERR_MSG_VAZIO);
    Gerador.wCampo(tcStr, 'Z06', 'xTexto', 01, 60, 1, nfe.InfAdic.obsCont[i].xTexto, DSC_XTEXTO);
    Gerador.wGrupo('/obsCont');
  end;
end;

procedure TNFeW.GerarInfAdicObsFisco;
var
  i: integer;
begin
  if nfe.InfAdic.obsFisco.Count > 10 then
    Gerador.wAlerta('Z07', 'obsFisco', DSC_OBSFISCO, ERR_MSG_MAIOR_MAXIMO + '10');
  for i := 0 to nfe.InfAdic.obsFisco.Count - 1 do
  begin
    Gerador.wGrupo('obsFisco xCampo="' + trim(nfe.InfAdic.obsFisco[i].xCampo) + '"', 'Z07');
    if length(trim(nfe.InfAdic.obsFisco[i].xCampo)) > 20 then
      Gerador.wAlerta('ZO8', 'xCampo', DSC_XCAMPO, ERR_MSG_MAIOR);
    if length(trim(nfe.InfAdic.obsFisco[i].xCampo)) = 0 then
      Gerador.wAlerta('ZO8', 'xCampo', DSC_XCAMPO, ERR_MSG_VAZIO);
    Gerador.wCampo(tcStr, 'Z09', 'xTexto', 01, 60, 1, nfe.InfAdic.obsFisco[i].xTexto, DSC_XTEXTO);
    Gerador.wGrupo('/obsFisco');
  end;
end;

procedure TNFeW.GerarInfAdicProcRef;
var
  i: integer;
begin
  if nfe.InfAdic.procRef.Count > 0 then
  begin
    for i := 0 to nfe.InfAdic.procRef.Count - 1 do
    begin
      Gerador.wGrupo('procRef', 'Z10');
      Gerador.wCampo(tcStr, 'Z11', 'nProc  ', 01, 60, 1, nfe.InfAdic.procRef[i].nProc, DSC_NPROC);
      Gerador.wCampo(tcStr, 'Z12', 'indProc', 01, 01, 1, indProcToStr(nfe.InfAdic.procRef[i].indProc), DSC_INDPROC);
      Gerador.wGrupo('/procRef');
    end;
  end;
end;

procedure TNFeW.GerarExporta;
begin
  if trim(nfe.exporta.UFembarq) + trim(nfe.exporta.xLocEmbarq) <> '' then
  begin
    Gerador.wGrupo('exporta', 'ZA01');
    Gerador.wCampo(tcStr, 'ZA02', 'UFEmbarq', 02, 02, 1, nfe.exporta.UFembarq, DSC_UFEMBARQ);
    if not ValidarUF(nfe.exporta.UFembarq) then
      Gerador.wAlerta('ZA02', 'UFEmbarq', DSC_UFEMBARQ, ERR_MSG_INVALIDO);
    Gerador.wCampo(tcStr, 'ZA03', 'xLocEmbarq', 01, 60, 1, nfe.exporta.xLocEmbarq, DSC_XLOCEMBARQ);
    Gerador.wGrupo('/exporta');
  end;
end;

procedure TNFeW.GerarCompra;
begin
  if trim(nfe.compra.xNEmp) + trim(nfe.compra.xPed) + trim(nfe.compra.xCont) <> '' then
  begin
    Gerador.wGrupo('compra', 'ZB01');
    Gerador.wCampo(tcStr, 'ZB02', 'xNEmp', 01, 22, 0, nfe.compra.xNEmp, DSC_XNEMP);
    Gerador.wCampo(tcStr, 'ZB03', 'xPed ', 01, 60, 0, nfe.compra.xPed, DSC_XPED);
    Gerador.wCampo(tcStr, 'ZB04', 'xCont', 01, 60, 0, nfe.compra.xCont, DSC_XCONT);
    Gerador.wGrupo('/compra');
  end;
end;

procedure TNFeW.GerarCana;
begin
  if not(Trim(nfe.cana.safra) = '') or not(Trim(nfe.cana.ref) = '') or
     (nfe.cana.fordia.Count > 0) or (nfe.cana.deduc.Count > 0) then
   begin
     Gerador.wGrupo('cana', 'ZC01');
     Gerador.wCampo(tcStr, 'ZC02', 'safra', 04, 09, 0, nfe.cana.safra, DSC_SAFRA);
     Gerador.wCampo(tcStr, 'ZC03', 'ref  ', 04, 09, 0, nfe.cana.ref, DSC_REF);
     (**)GerarforDia;
    Gerador.wCampo(tcDe10,'ZC07','qTotMes', 01, 21, 1, nfe.cana.qTotMes, DSC_QTOTMES);
    Gerador.wCampo(tcDe10,'ZC08','qTotAnt', 01, 21, 1, nfe.cana.qTotAnt, DSC_QTOTANT);
    Gerador.wCampo(tcDe10,'ZC09','qTotGer', 01, 21, 1, nfe.cana.qTotGer, DSC_TOTGER);
     (**)GerarDeduc;
    Gerador.wCampo(tcDe2,'ZC13','vFor   ', 01, 15, 1, nfe.cana.vFor, DSC_VFOR);
    Gerador.wCampo(tcDe2,'ZC14','vTotDed', 01, 15, 1, nfe.cana.vTotDed, DSC_VTOTDED);
    Gerador.wCampo(tcDe2,'ZC15','vLiqFor', 01, 15, 1, nfe.cana.vLiqFor, DSC_VLIQFOR);
     Gerador.wGrupo('/cana');
   end;
end;

procedure TNFeW.GerarforDia;
var
  i: integer;
begin
  if nfe.cana.fordia.Count > 31 then
    Gerador.wAlerta('ZC04', 'forDia', DSC_FORDIA, ERR_MSG_MAIOR_MAXIMO + '31');
  for i := 0 to nfe.cana.fordia.Count - 1 do
  begin
    Gerador.wGrupo('forDia dia='+'"'+IntToStr(nfe.cana.fordia[i].dia)+'"', 'ZC04');
    Gerador.wCampo(tcDe10,'ZC06','qtde   ', 11, 21, 1, nfe.cana.fordia[i].qtde, DSC_QTDE);
    Gerador.wGrupo('/forDia');
  end;
end;

procedure TNFeW.GerarDeduc;
var
  i: integer;
begin
  if nfe.cana.deduc.Count > 10 then
    Gerador.wAlerta('ZC10', 'deduc', DSC_DEDUC, ERR_MSG_MAIOR_MAXIMO + '10');
  for i := 0 to nfe.cana.deduc.Count - 1 do
  begin
    Gerador.wGrupo('deduc', 'ZC10');
    Gerador.wCampo(tcStr,'ZC11','xDed   ', 01, 60, 1, nfe.cana.deduc[i].xDed, DSC_XDED);
    Gerador.wCampo(tcDe2,'ZC12','vDed   ', 01, 15, 1, nfe.cana.deduc[i].vDed, DSC_VDED);
    Gerador.wGrupo('/deduc');
  end;
end;

// Outras //////////////////////////////////////////////////////////////////////

procedure TNFeW.AjustarMunicipioUF(var xUF: string; var xMun: string; var cMun: integer; cPais: integer; vxUF, vxMun: string; vcMun: integer);
var
  PaisBrasil: boolean;
begin
  PaisBrasil := cPais = CODIGO_BRASIL;
  cMun := IIf(PaisBrasil, vcMun, CMUN_EXTERIOR);
  xMun := IIf(PaisBrasil, vxMun, XMUN_EXTERIOR);
  xUF := IIf(PaisBrasil, vxUF, UF_EXTERIOR);
  xMun := ObterNomeMunicipio(xMun, xUF, cMun);
end;

function TNFeW.ObterNomeMunicipio(const xMun, xUF: string; const cMun: integer): string;
var
  i: integer;
  PathArquivo, Codigo: string;
  List: TstringList;
begin
  result := '';
  if (FOpcoes.NormatizarMunicipios) and (cMun <> CMUN_EXTERIOR) then
  begin
    PathArquivo := FOpcoes.FPathArquivoMunicipios + 'MunIBGE-UF' + InttoStr(UFparaCodigo(xUF)) + '.txt';
    if FileExists(PathArquivo) then
    begin
      List := TstringList.Create;
      List.LoadFromFile(PathArquivo);
      Codigo := IntToStr(cMun);
      i := 0;
      while (i < list.count) and (result = '') do
      begin
        if pos(Codigo, List[i]) > 0 then
          result := Trim(stringReplace(list[i], codigo, '', []));
        inc(i);
      end;
      List.free;
    end;
  end;
  if result = '' then
    result := xMun;
end;

procedure TNFeW.Gerarpag;
var
  i: integer;
begin
  for i := 0 to nfe.pag.Count - 1 do
  begin
    Gerador.wGrupo('pag', 'YA01');
    Gerador.wCampo(tcStr, 'YA02', 'tPag', 02, 02, 1, FormaPagamentoToStr(nfe.pag[i].tPag), 'erro');
    Gerador.wCampo(tcDe2, 'YA03', 'vPag', 01, 15, 1, nfe.pag[i].vPag, 'erro');
    if nfe.pag[i].CNPJ <> '' then
     begin
       Gerador.wGrupo('card', 'YA04');
       Gerador.wCampo(tcStr, 'YA05', 'CNPJ ', 14, 14, 1, nfe.pag[i].CNPJ, DSC_CNPJ);
       Gerador.wCampo(tcStr, 'YA06', 'tBand', 02, 02, 1, BandeiraCartaoToStr(nfe.pag[i].tBand), 'erro');
       Gerador.wCampo(tcStr, 'YA07', 'cAut ', 01, 20, 1, nfe.pag[i].cAut, 'erro');
       Gerador.wGrupo('/card');
     end;
    Gerador.wGrupo('/pag');
  end;
  if nfe.pag.Count > 100 then
    Gerador.wAlerta('YA01', 'pag', '', ERR_MSG_MAIOR_MAXIMO + '100');
end;

end.

