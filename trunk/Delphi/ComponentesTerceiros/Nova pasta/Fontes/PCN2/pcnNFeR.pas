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

unit pcnNFeR;

interface uses

  SysUtils, Classes,
{$IFNDEF VER130}
  Variants,
{$ENDIF}
  pcnAuxiliar, pcnConversao, pcnLeitor, pcnNFe;

type

  TNFeR = class(TPersistent)
  private
    FLeitor: TLeitor;
    FNFe: TNFe;
    FSchema: TpcnSchema;
  public
    constructor Create(AOwner: TNFe);
    destructor Destroy; override;
    function LerXml: boolean;
  published
    property Leitor: TLeitor read FLeitor write FLeitor;
    property NFe: TNFe read FNFe write FNFe;
    property schema: TpcnSchema read Fschema write Fschema;
  end;

  ////////////////////////////////////////////////////////////////////////////////

implementation

uses StrUtils, ACBrConsts;

{ TNFeR }

constructor TNFeR.Create(AOwner: TNFe);
begin
  FLeitor := TLeitor.Create;
  FNFe := AOwner;
end;

destructor TNFeR.Destroy;
begin
  FLeitor.Free;
  inherited Destroy;
end;

////////////////////////////////////////////////////////////////////////////////

function TNFeR.LerXml: boolean;
var
  ok: boolean;
  i, j, k, z, nItem: integer;
  Arquivo, Itens, ItensTemp, VersaoInfNFe, Temp_VersaoInfNFe, NumItem: AnsiString;
  Aspas: String;

  Function VerificaParSt(const t: TpcnCSTIcms): TpcnCSTIcms;
  // 	Verifica se existe Partilha ou St
  begin
    Result := t;
    case t of
          // ICMSPart (N10a)
    cst10 : if ( nfe.Det[i].Imposto.ICMS.UFST <> '') then Result := cstPart10;
    cst90 : if ( nfe.Det[i].Imposto.ICMS.UFST <> '') then Result := cstPart90;
          //ICMSST (N10b)- Repasse de ICMS
    cst41 : if (	( nfe.Det[i].Imposto.ICMS.vBCSTRet    > 0) or
                  ( nfe.Det[i].Imposto.ICMS.vICMSSTRet  > 0) or
                  ( nfe.Det[i].Imposto.ICMS.vBCSTDest   > 0) or
                  ( nfe.Det[i].Imposto.ICMS.vICMSSTDest > 0) )
      then Result := cstRep41;
    end;
  end;
begin
  // Incluido por Italo em 22/04/2013
  if Pos('Id="', Leitor.Arquivo) <> 0 then
    Aspas := '"'
   else
    Aspas := '''';

  I := 0;

  I := RetornarPosEx('Id=', Leitor.Arquivo, I + 6);
  if I = 0 then
    raise Exception.Create('N�o encontrei inicio do URI: Id=');
  I := RetornarPosEx(Aspas, Leitor.Arquivo, I + 2);
  if I = 0 then
    raise Exception.Create('N�o encontrei inicio do URI: aspas inicial');
  J := RetornarPosEx(Aspas, Leitor.Arquivo, I + 1);
  if J = 0 then
    raise Exception.Create('N�o encontrei inicio do URI: aspas final');

  z:=pos('infnfe', LowerCase(Leitor.Arquivo));
  VersaoInfNFe:=copy(LowerCase(Leitor.Arquivo),z,length(Leitor.Arquivo)-z);
  z:=Pos('versao='+Aspas,VersaoInfNFe)+8;

  VersaoInfNFe := copy(VersaoInfNFe,z,4);
  VersaoInfNFe := StringReplace(Trim(VersaoInfNFe),Aspas,'',[rfReplaceAll] ) ;

  if (DecimalSeparator = ',') then
      Temp_VersaoInfNFe := StringReplace(Trim(VersaoInfNFe),'.',',',[rfReplaceAll] )
  else
      Temp_VersaoInfNFe := Trim(VersaoInfNFe);
  NFe.infNFe.Versao := StrToFloat(Temp_VersaoInfNFe);

  NFe.infNFe.ID := copy(Leitor.Arquivo, I+1, J - (I+1));
  NFe.infNFe.ID := StringReplace( UpperCase(NFe.infNFe.ID), 'NFE', '', [rfReplaceAll] ) ;

  (* Grupo da TAG <ide> *******************************************************)
  if Leitor.rExtrai(1, 'ide') <> '' then
  begin
    (*B02*) NFe.ide.cUF := Leitor.rCampo(tcInt, 'cUF');
    (*B03*) NFe.ide.cNF := Leitor.rCampo(tcInt, 'cNF');
    if NFe.ide.cNF = 0 then
       NFe.ide.cNF := -2;
    (*B04*) NFe.ide.natOp := Leitor.rCampo(tcStr, 'natOp');
    (*B05*) NFe.ide.indPag := StrToIndpag(ok, Leitor.rCampo(tcStr, 'indPag'));
    (*B06*) NFe.ide.modelo := Leitor.rCampo(tcInt, 'mod');
    (*B07*) NFe.ide.serie := Leitor.rCampo(tcInt, 'serie');
    (*B08*) NFe.ide.nNF := Leitor.rCampo(tcInt, 'nNF');

    if NFe.infNFe.Versao >= 3 then
     begin
      (*B09*) NFe.ide.dEmi := Leitor.rCampo(tcDat, 'dhEmi');
      (*B10*) NFe.ide.dSaiEnt := Leitor.rCampo(tcDatHor, 'dhSaiEnt');
     end
    else
     begin
      (*B09*) NFe.ide.dEmi := Leitor.rCampo(tcDat, 'dEmi');
      (*B10*) NFe.ide.dSaiEnt := Leitor.rCampo(tcDat, 'dSaiEnt');
      (*B10a*)NFe.ide.hSaiEnt := Leitor.rCampo(tcHor, 'hSaiEnt');
     end;
    (*B11*) NFe.ide.tpNF := StrToTpNF(ok, Leitor.rCampo(tcStr, 'tpNF'));

    if NFe.infNFe.Versao >= 3 then
     (*B11a*)NFe.ide.idDest := StrToDestinoOperacao(ok, Leitor.rCampo(tcStr, 'idDest'));

    (*B12*) NFe.ide.cMunFG := Leitor.rCampo(tcInt, 'cMunFG');

    (*B21*) NFe.Ide.tpImp := StrToTpImp(ok, Leitor.rCampo(tcStr, 'tpImp'));
    (*B22*) NFe.Ide.tpEmis := StrToTpEmis(ok, Leitor.rCampo(tcStr, 'tpEmis'));
    (*B23*) NFe.Ide.cDV := Leitor.rCampo(tcInt, 'cDV');
    (*B24*) NFe.Ide.tpAmb := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
    (*B25*) NFe.Ide.finNFe := StrToFinNFe(ok, Leitor.rCampo(tcStr, 'finNFe'));

    if NFe.infNFe.Versao >= 3 then
     begin
      (*B25a*)NFe.ide.indFinal := StrToConsumidorFinal(ok, Leitor.rCampo(tcStr, 'indFinal'));
      (*B25b*)NFe.ide.indPres := StrToPresencaComprador(ok, Leitor.rCampo(tcStr, 'indPres'));
     end;

    (*B26*) NFe.Ide.procEmi := StrToProcEmi(ok, Leitor.rCampo(tcStr, 'procEmi'));
    (*B27*) NFe.Ide.verProc := Leitor.rCampo(tcStr, 'verProc');
    (*B28*) NFe.Ide.dhCont := Leitor.rCampo(tcDatHor, 'dhCont');
    (*B29*) NFe.Ide.xJust := Leitor.rCampo(tcStr, 'xJust');

    (* Grupo da TAG <ide><NFref> *)
    i := 0;
    Leitor.rExtrai(1, 'ide');
    while Leitor.rExtrai(2, 'NFref', '', i + 1) <> '' do
    begin
      NFe.Ide.NFref.Add;
      (*B13*) NFe.ide.NFref[i].refNFe         := Leitor.rCampo(tcEsp, 'refNFe');

      if Length(Trim(Leitor.rCampo(tcEsp,'refNF'))) > 0 then // Verifica��o adicionada
      begin
        (*B15*) NFe.Ide.NFref[i].RefNF.cUF      := Leitor.rCampo(tcInt, 'cUF');
        (*B16*) NFe.Ide.NFref[i].RefNF.AAMM     := Leitor.rCampo(tcEsp, 'AAMM');
        (*B17*) NFe.Ide.NFref[i].RefNF.CNPJ     := Leitor.rCampo(tcEsp, 'CNPJ');
        (*B18*) NFe.Ide.NFref[i].RefNF.Modelo   := StrToIntDef(Leitor.rCampo(tcInt, 'mod'),55);
        (*B19*) NFe.ide.NFref[i].RefNF.serie    := Leitor.rCampo(tcInt, 'serie');
        (*B20*) NFe.Ide.NFref[i].RefNF.nNF      := Leitor.rCampo(tcInt, 'nNF');
      end;

      //corre��o sugerida no MANTIS caso 814
      if Length(Trim(Leitor.rCampo(tcEsp,'refNFP'))) > 0 then
      begin
        (*B20b*) NFe.Ide.NFref[i].RefNFP.cUF     := Leitor.rCampo(tcInt, 'cUF');
        (*B20c*) NFe.Ide.NFref[i].RefNFP.AAMM    := Leitor.rCampo(tcEsp, 'AAMM');
   (*B20d/B20e*) NFe.Ide.NFref[i].RefNFP.CNPJCPF := Leitor.rCampoCNPJCPF;
        (*B20f*) NFe.Ide.NFref[i].RefNFP.IE      := Leitor.rCampo(tcEsp, 'IE');
        (*B20f*) NFe.Ide.NFref[i].RefNFP.Modelo  := Leitor.rCampo(tcInt, 'mod');
        (*B20g*) NFe.ide.NFref[i].RefNFP.serie   := Leitor.rCampo(tcInt, 'serie');
        (*B20h*) NFe.Ide.NFref[i].RefNFP.nNF     := Leitor.rCampo(tcInt, 'nNF');
      end;

      (*B20i*)NFe.ide.NFref[i].refCTe         := Leitor.rCampo(tcEsp, 'refCTe');

      (*B20k*) NFe.Ide.NFref[i].RefECF.modelo := StrToECFModRef(ok,Leitor.rCampo(tcStr, 'mod') ) ;
      (*B20l*) NFe.ide.NFref[i].RefECF.nECF   := Leitor.rCampo(tcStr, 'nECF');
      (*B20m*) NFe.Ide.NFref[i].RefECF.nCOO   := Leitor.rCampo(tcStr, 'nCOO');

      inc(i);
    end;

  end;

  (* Grupo da TAG <emit> ******************************************************)
  if Leitor.rExtrai(1, 'emit') <> '' then
  begin
    (*C02/C02a*)NFe.Emit.CNPJCPF := Leitor.rCampoCNPJCPF;
    (*C03*)NFe.Emit.xNome := Leitor.rCampo(tcStr, 'xNome');
    (*C04*)NFe.Emit.xFant := Leitor.rCampo(tcStr, 'xFant');
    (*C17*)NFe.Emit.IE := Leitor.rCampo(tcStr, 'IE');
    (*C18*)NFe.Emit.IEST := Leitor.rCampo(tcStr, 'IEST');
    (*C19*)NFe.Emit.IM := Leitor.rCampo(tcStr, 'IM');
    (*C20*)NFe.Emit.CNAE := Leitor.rCampo(tcStr, 'CNAE');
    (*C21*)NFe.Emit.CRT := StrToCRT(ok, Leitor.rCampo(tcStr, 'CRT'));
  end;

  (* Grupo da TAG <emit><EnderEmit> *)
  if Leitor.rExtrai(1, 'emit') <> '' then
  begin
    if Leitor.rExtrai(2, 'enderEmit') <> '' then
    begin
      (*C06*)NFe.Emit.enderEmit.xLgr := Leitor.rCampo(tcStr, 'xLgr');
      (*C07*)NFe.Emit.enderEmit.nro := Leitor.rCampo(tcStr, 'nro');
      (*C08*)NFe.Emit.enderEmit.xCpl := Leitor.rCampo(tcStr, 'xCpl');
      (*C09*)NFe.Emit.enderEmit.xBairro := Leitor.rCampo(tcStr, 'xBairro');
      (*C10*)NFe.Emit.EnderEmit.cMun := Leitor.rCampo(tcInt, 'cMun');
      (*C11*)NFe.Emit.enderEmit.xMun := Leitor.rCampo(tcStr, 'xMun');
      (*C12*)NFe.Emit.enderEmit.UF := Leitor.rCampo(tcStr, 'UF');
      (*C13*)NFe.Emit.enderEmit.CEP := Leitor.rCampo(tcInt, 'CEP');
      (*C14*)NFe.Emit.enderEmit.cPais := Leitor.rCampo(tcInt, 'cPais');
      (*C15*)NFe.Emit.enderEmit.xPais := Leitor.rCampo(tcStr, 'xPais');
      (*C16*)NFe.Emit.enderEmit.fone := Leitor.rCampo(tcStr, 'fone');
    end;
  end;

  (* Grupo da TAG <avulsa> ****************************************************)
  if Leitor.rExtrai(1, 'avulsa') <> '' then
  begin
    (*D02*)NFe.Avulsa.CNPJ := Leitor.rCampo(tcStr, 'CNPJ');
    (*D03*)NFe.Avulsa.xOrgao := Leitor.rCampo(tcStr, 'xOrgao');
    (*D04*)NFe.Avulsa.matr := Leitor.rCampo(tcStr, 'matr');
    (*D05*)NFe.Avulsa.xAgente := Leitor.rCampo(tcStr, 'xAgente');
    (*D06*)NFe.Avulsa.fone := Leitor.rCampo(tcStr, 'fone');
    (*D07*)NFe.Avulsa.UF := Leitor.rCampo(tcStr, 'UF');
    (*D08*)NFe.Avulsa.nDAR := Leitor.rCampo(tcStr, 'nDAR');
    (*D09*)NFe.Avulsa.dEmi := Leitor.rCampo(tcDat, 'dEmi');
    (*D10*)NFe.Avulsa.vDAR := Leitor.rCampo(tcDe2, 'vDAR');
    (*D11*)NFe.Avulsa.repEmi := Leitor.rCampo(tcStr, 'repEmi');
    (*D12*)NFe.Avulsa.dPag := Leitor.rCampo(tcDat, 'dPag');
  end;

  (* Grupo da TAG <dest> ******************************************************)
  if Leitor.rExtrai(1, 'dest') <> '' then
  begin
    (*E02/E03*)NFe.Dest.CNPJCPF := Leitor.rCampoCNPJCPF;

    if NFe.infNFe.Versao >= 3 then
     (*E03a*)NFe.Dest.idEstrangeiro := Leitor.rCampo(tcStr, 'idEstrangeiro');

    (*E04*)NFe.Dest.xNome := Leitor.rCampo(tcStr, 'xNome');
    (*E17*)NFe.Dest.IE := Leitor.rCampo(tcStr, 'IE');
    (*E18*)NFe.Dest.ISUF := Leitor.rCampo(tcStr, 'ISUF');
    (*E19*)NFe.Dest.Email := Leitor.rCampo(tcStr, 'email');
  end;

  (* Grupo da TAG <dest> <EnderDest> *)
  if Leitor.rExtrai(1, 'dest') <> '' then
  begin
    if Leitor.rExtrai(2, 'enderDest') <> '' then
    begin
      (*E06*)NFe.Dest.enderDest.xLgr := Leitor.rCampo(tcStr, 'xLgr');
      (*E07*)NFe.Dest.enderDest.nro := Leitor.rCampo(tcStr, 'nro');
      (*E08*)NFe.Dest.enderDest.xCpl := Leitor.rCampo(tcStr, 'xCpl');
      (*E09*)NFe.Dest.enderDest.xBairro := Leitor.rCampo(tcStr, 'xBairro');
      (*E10*)NFe.Dest.enderDest.cMun  := Leitor.rCampo(tcInt, 'cMun');
      (*E11*)NFe.Dest.enderDest.xMun  := Leitor.rCampo(tcStr, 'xMun');
      (*E12*)NFe.Dest.enderDest.UF    := Leitor.rCampo(tcStr, 'UF');
      (*E13*)NFe.Dest.enderDest.CEP   := Leitor.rCampo(tcInt, 'CEP');
      (*E14*)NFe.Dest.enderDest.cPais := Leitor.rCampo(tcInt, 'cPais');
      (*E15*)NFe.Dest.enderDest.xPais := Leitor.rCampo(tcStr, 'xPais');
      (*E16*)NFe.Dest.enderDest.fone  := Leitor.rCampo(tcStr, 'fone');
    end;
  end;

  (* Grupo da TAG <retirada> **************************************************)
  if Leitor.rExtrai(1, 'retirada') <> '' then
  begin
    (*F02/F02a*)NFe.Retirada.CNPJCPF := Leitor.rCampoCNPJCPF;
    (*F03*)NFe.Retirada.xLgr := Leitor.rCampo(tcStr, 'xLgr');
    (*F04*)NFe.Retirada.nro := Leitor.rCampo(tcStr, 'nro');
    (*F05*)NFe.Retirada.xCpl := Leitor.rCampo(tcStr, 'xCpl');
    (*F06*)NFe.Retirada.xBairro := Leitor.rCampo(tcStr, 'xBairro');
    (*F07*)NFe.Retirada.cMun := Leitor.rCampo(tcInt, 'cMun');
    (*F08*)NFe.Retirada.xMun := Leitor.rCampo(tcStr, 'xMun');
    (*F09*)NFe.Retirada.UF := Leitor.rCampo(tcStr, 'UF');
  end;

  (* Grupo da TAG <entrega> ***************************************************)
  if Leitor.rExtrai(1, 'entrega') <> '' then
  begin
    (*G02/G02a*)NFe.Entrega.CNPJCPF := Leitor.rCampoCNPJCPF;
    (*G03*)NFe.Entrega.xLgr := Leitor.rCampo(tcStr, 'xLgr');
    (*G04*)NFe.Entrega.nro := Leitor.rCampo(tcStr, 'nro');
    (*G05*)NFe.Entrega.xCpl := Leitor.rCampo(tcStr, 'xCpl');
    (*G06*)NFe.Entrega.xBairro := Leitor.rCampo(tcStr, 'xBairro');
    (*G07*)NFe.Entrega.cMun := Leitor.rCampo(tcInt, 'cMun');
    (*G08*)NFe.Entrega.xMun := Leitor.rCampo(tcStr, 'xMun');
    (*G09*)NFe.Entrega.UF := Leitor.rCampo(tcStr, 'UF');
  end;

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

    NumItem := copy(ItensTemp,Pos('nItem=',ItensTemp)+7,Pos(Aspas,ItensTemp));
    NumItem := copy(NumItem,1,Pos(Aspas,NumItem)-1);
    nItem := StrToInt(NumItem);
    Itens     := StringReplace(Itens, ItensTemp, '',[]);
    ItensTemp := copy(Itens,Pos('<det nItem=',Itens),(Pos('</det>',Itens)+6)-Pos('<det nItem=',Itens));

    Leitor.rExtrai(1, 'det nItem=' + Aspas + IntToStr(nItem) + Aspas, 'det');
    NFe.Det.Add;
    (*   *)NFe.Det[i].prod.nItem := i + 1;
    (*V01*)NFe.Det[i].infAdProd := Leitor.rCampo(tcStr, 'infAdProd');

    (* Grupo da TAG <det><prod> *)
    Leitor.rExtrai(2, 'prod');
    (*I02*)NFe.Det[i].Prod.cProd := Leitor.rCampo(tcStr, 'cProd');
    (*I03*)NFe.Det[i].Prod.cEAN := Leitor.rCampo(tcStr, 'cEAN');
    (*I04*)NFe.Det[i].Prod.xProd := Leitor.rCampo(tcStr, 'xProd');
    (*I05*)NFe.Det[i].Prod.NCM := Leitor.rCampo(tcStr, 'NCM');
    (*I06*)NFe.Det[i].Prod.EXTIPI := Leitor.rCampo(tcStr, 'EXTIPI');
    //(*I07*)NFe.Det[i].Prod.genero := Leitor.rCampo(tcInt, 'genero');
    (*I08*)NFe.Det[i].Prod.CFOP := Leitor.rCampo(tcEsp, 'CFOP');
    (*I09*)NFe.Det[i].Prod.uCom := Leitor.rCampo(tcStr, 'uCom');
    (*I10*)NFe.Det[i].Prod.qCom := Leitor.rCampo(tcDe4, 'qCom');
    (*I10a*)NFe.Det[i].Prod.vUnCom := Leitor.rCampo(tcDe10, 'vUnCom');
    (*I11*)NFe.Det[i].Prod.vProd := Leitor.rCampo(tcDe2, 'vProd');
    (*I12*)NFe.Det[i].Prod.cEANTrib := Leitor.rCampo(tcStr, 'cEANTrib');
    (*I13*)NFe.Det[i].Prod.uTrib := Leitor.rCampo(tcStr, 'uTrib');
    (*I14*)NFe.Det[i].Prod.qTrib := Leitor.rCampo(tcDe4, 'qTrib');
    (*I14a*)NFe.Det[i].Prod.vUnTrib := Leitor.rCampo(tcDe10, 'vUnTrib');
    (*I15*)NFe.Det[i].Prod.vFrete := Leitor.rCampo(tcDe2, 'vFrete');
    (*I16*)NFe.Det[i].Prod.vSeg := Leitor.rCampo(tcDe2, 'vSeg');
    (*I17*)NFe.Det[i].Prod.vDesc := Leitor.rCampo(tcDe2, 'vDesc');
    (*I17a*)NFe.Det[i].Prod.vOutro := Leitor.rCampo(tcDe2, 'vOutro');
    (*I17b*)NFe.Det[i].Prod.IndTot := StrToindTot(ok,Leitor.rCampo(tcDe2, 'indTot'));
    (*I30*)NFe.Det[i].Prod.xPed := Leitor.rCampo(tcStr, 'xPed');
    (*I31*)NFe.Det[i].Prod.nItemPed := Leitor.rCampo(tcInt, 'nItemPed');

    (* Grupo da TAG <det><prod><DI> *)
    j := 0;
    while Leitor.rExtrai(3, 'DI', '', j + 1) <> '' do
    begin
      NFe.Det[i].Prod.DI.Add;
      (*I19*)NFe.Det[i].Prod.DI[j].nDI := Leitor.rCampo(tcStr, 'nDI');
      (*I20*)NFe.Det[i].Prod.DI[j].dDI := Leitor.rCampo(tcDat, 'dDI');
      (*I21*)NFe.Det[i].Prod.DI[j].xLocDesemb := Leitor.rCampo(tcStr, 'xLocDesemb');
      (*I22*)NFe.Det[i].Prod.DI[j].UFDesemb := Leitor.rCampo(tcStr, 'UFDesemb');
      (*I23*)NFe.Det[i].Prod.DI[j].dDesemb := Leitor.rCampo(tcDat, 'dDesemb');
      (*I24*)NFe.Det[i].Prod.DI[j].cExportador := Leitor.rCampo(tcStr, 'cExportador');

      (* Grupo da TAG <det><prod><DI><adi> *)
      k := 0;
      while Leitor.rExtrai(4, 'adi', '', k + 1) <> '' do
      begin
        NFe.Det[i].Prod.DI[j].adi.Add;
        (*I26*)NFe.Det[i].Prod.DI[j].adi[k].nAdicao := Leitor.rCampo(tcInt, 'nAdicao');
        (*I27*)NFe.Det[i].Prod.DI[j].adi[k].nSeqAdi := Leitor.rCampo(tcInt, 'nSeqAdic');
        (*I28*)NFe.Det[i].Prod.DI[j].adi[k].cFabricante := Leitor.rCampo(tcStr, 'cFabricante');
        (*I29*)NFe.Det[i].Prod.DI[j].adi[k].vDescDI := Leitor.rCampo(tcDe2, 'vDescDI');
        inc(k);
      end;

      inc(j);
    end;

    (* Grupo da TAG <det><prod><veicProd> *)
    if Leitor.rExtrai(3, 'veicProd') <> '' then
    begin

      (*J02*)NFe.Det[i].Prod.veicProd.tpOP := StrToTpOP(ok, Leitor.rCampo(tcStr, 'tpOp'));
      (*J03*)NFe.Det[i].Prod.veicProd.chassi := Leitor.rCampo(tcStr, 'chassi');
      (*J04*)NFe.Det[i].Prod.veicProd.cCor := Leitor.rCampo(tcStr, 'cCor');
      (*J05*)NFe.Det[i].Prod.veicProd.xCor := Leitor.rCampo(tcStr, 'xCor');
      (*J06*)NFe.Det[i].Prod.veicProd.pot := Leitor.rCampo(tcStr, 'pot');
      (*J07*)NFe.Det[i].Prod.veicProd.Cilin := Leitor.rCampo(tcStr, 'cilin');
      (*J08*)NFe.Det[i].Prod.veicProd.pesoL := Leitor.rCampo(tcStr, 'pesoL');
      (*J09*)NFe.Det[i].Prod.veicProd.pesoB := Leitor.rCampo(tcStr, 'pesoB');
      (*J10*)NFe.Det[i].Prod.veicProd.nSerie := Leitor.rCampo(tcStr, 'nSerie');
      (*J11*)NFe.Det[i].Prod.veicProd.tpComb := Leitor.rCampo(tcStr, 'tpComb');
      (*J12*)NFe.Det[i].Prod.veicProd.nMotor := Leitor.rCampo(tcStr, 'nMotor');
      (*J13*)NFe.Det[i].Prod.veicProd.CMT := Leitor.rCampo(tcStr, 'CMT');
      (*J14*)NFe.Det[i].Prod.veicProd.dist := Leitor.rCampo(tcStr, 'dist');
      //(*J15*)NFe.Det[i].Prod.veicProd.RENAVAM := Leitor.rCampo(tcEsp, 'RENAVAM');
      (*J16*)NFe.Det[i].Prod.veicProd.anoMod := Leitor.rCampo(tcInt, 'anoMod');
      (*J17*)NFe.Det[i].Prod.veicProd.anoFab := Leitor.rCampo(tcInt, 'anoFab');
      (*J18*)NFe.Det[i].Prod.veicProd.tpPint := Leitor.rCampo(tcStr, 'tpPint');
      (*J19*)NFe.Det[i].Prod.veicProd.tpVeic := Leitor.rCampo(tcInt, 'tpVeic');
      (*J20*)NFe.Det[i].Prod.veicProd.espVeic := Leitor.rCampo(tcInt, 'espVeic');
      (*J21*)NFe.Det[i].Prod.veicProd.VIN := Leitor.rCampo(tcStr, 'VIN');
      (*J22*)NFe.Det[i].Prod.veicProd.condVeic := StrToCondVeic(ok, Leitor.rCampo(tcStr, 'condVeic'));
      (*J23*)NFe.Det[i].Prod.veicProd.cMod := Leitor.rCampo(tcStr, 'cMod');
      (*J24*)NFe.Det[i].Prod.veicProd.cCorDENATRAN := Leitor.rCampo(tcStr, 'cCorDENATRAN');
      (*J25*)NFe.Det[i].Prod.veicProd.lota := Leitor.rCampo(tcInt, 'lota');
      (*J26*)NFe.Det[i].Prod.veicProd.tpRest := Leitor.rCampo(tcInt, 'tpRest');
    end;

    (* Grupo da TAG <det><prod><med> *)
    j := 0;
    while Leitor.rExtrai(3, 'med', '', j + 1) <> '' do
    begin
      NFe.Det[i].Prod.med.Add;
      (*K02*)NFe.Det[i].Prod.med[j].nLote := Leitor.rCampo(tcStr, 'nLote');
      (*K03*)NFe.Det[i].Prod.med[j].qLote := Leitor.rCampo(tcDe3, 'qLote');
      (*K04*)NFe.Det[i].Prod.med[j].dFab := Leitor.rCampo(tcDat, 'dFab ');
      (*K05*)NFe.Det[i].Prod.med[j].dVal := Leitor.rCampo(tcDat, 'dVal ');
      (*K06*)NFe.Det[i].Prod.med[j].vPMC := Leitor.rCampo(tcDe2, 'vPMC ');
      inc(j);
    end;

    (* Grupo da TAG <det><prod><arma> *)
    j := 0;
    while Leitor.rExtrai(3, 'arma', '', j + 1) <> '' do
    begin
      NFe.Det[i].Prod.arma.add;
      (*L02*)NFe.Det[i].Prod.arma[j].tpArma := StrToTpArma(ok, Leitor.rCampo(tcStr, 'tpArma'));
      (*L03*)NFe.Det[i].Prod.arma[j].nSerie := Leitor.rCampo(tcStr, 'nSerie');
      (*L04*)NFe.Det[i].Prod.arma[j].nCano := Leitor.rCampo(tcStr, 'nCano');
      (*L05*)NFe.Det[i].Prod.arma[j].descr := Leitor.rCampo(tcStr, 'descr');
      inc(j);
    end;

    (* Grupo da TAG <det><prod><comb> *)
    if Leitor.rExtrai(3, 'comb') <> '' then
    begin
      (*L102*)NFe.Det[i].Prod.comb.cProdANP := Leitor.rCampo(tcInt, 'cProdANP');
      (*L103*)NFe.Det[i].Prod.comb.CODIF    := Leitor.rCampo(tcEsp, 'CODIF');
      (*L104*)NFe.Det[i].Prod.comb.qTemp    := Leitor.rCampo(tcDe4, 'qTemp');
      (*L120*)NFe.Det[i].Prod.comb.UFcons   := Leitor.rCampo(tcStr, 'UFCons');

      (*L120*)NFe.Det[i].Prod.comb.ICMSCons.UFcons := Leitor.rCampo(tcStr, 'UFcons');

      if Leitor.rExtrai(4, 'CIDE') <> '' then
      begin
        (*L106*)NFe.Det[i].Prod.comb.CIDE.qBCprod := Leitor.rCampo(tcDe4, 'qBCprod');
        (*L107*)NFe.Det[i].Prod.comb.CIDE.vAliqProd := Leitor.rCampo(tcDe4, 'vAliqProd');
        (*L108*)NFe.Det[i].Prod.comb.CIDE.vCIDE := Leitor.rCampo(tcDe2, 'vCIDE');
      end;
      if Leitor.rExtrai(4, 'ICMSComb') <> '' then
      begin
        (*L110*)NFe.Det[i].Prod.comb.ICMS.vBCICMS   := Leitor.rCampo(tcDe2, 'vBCICMS');
        (*L111*)NFe.Det[i].Prod.comb.ICMS.vICMS     := Leitor.rCampo(tcDe2, 'vICMS');
        (*L112*)NFe.Det[i].Prod.comb.ICMS.vBCICMSST := Leitor.rCampo(tcDe2, 'vBCICMSST');
        (*L113*)NFe.Det[i].Prod.comb.ICMS.vICMSST   := Leitor.rCampo(tcDe2, 'vICMSST');
      end;
      if Leitor.rExtrai(4, 'ICMSInter') <> '' then
      begin
        (*L115*)NFe.Det[i].Prod.comb.ICMSInter.vBCICMSSTDest := Leitor.rCampo(tcDe2, 'vBCICMSSTDest');
        (*L116*)NFe.Det[i].Prod.comb.ICMSInter.vICMSSTDest := Leitor.rCampo(tcDe2, 'vICMSSTDest');
      end;
      if Leitor.rExtrai(4, 'ICMSCons') <> '' then
      begin
        (*L118*)NFe.Det[i].Prod.comb.ICMSCons.vBCICMSSTCons := Leitor.rCampo(tcDe2, 'vBCICMSSTCons');
        (*L119*)NFe.Det[i].Prod.comb.ICMSCons.vICMSSTCons   := Leitor.rCampo(tcDe2, 'vICMSSTCons');
        (*L119*)NFe.Det[i].Prod.comb.ICMSCons.UFcons        := Leitor.rCampo(tcStr, 'UFCons');
      end;
    end;

    (* Grupo da TAG <det><imposto> ********************************************)
    Leitor.rExtrai(2, 'imposto');
    if Leitor.rExtrai(3, 'ICMS') <> '' then
    begin
      (*N11*)NFe.Det[i].Imposto.ICMS.orig         := StrToOrig(ok, Leitor.rCampo(tcStr, 'orig'));
      (*N12*)NFe.Det[i].Imposto.ICMS.CST          := StrToCSTICMS(ok, Leitor.rCampo(tcStr, 'CST'));
      (*N12a*)NFe.Det[i].Imposto.ICMS.CSOSN       := StrToCSOSNIcms( ok,Leitor.rCampo(tcInt, 'CSOSN'));
      (*N13*)NFe.Det[i].Imposto.ICMS.modBC        := StrToModBC(ok, Leitor.rCampo(tcStr, 'modBC'));
      (*N14*)NFe.Det[i].Imposto.ICMS.pRedBC       := Leitor.rCampo(tcDe2, 'pRedBC');
      (*N15*)NFe.Det[i].Imposto.ICMS.vBC          := Leitor.rCampo(tcDe2, 'vBC');
      (*N16*)NFe.Det[i].Imposto.ICMS.pICMS        := Leitor.rCampo(tcDe2, 'pICMS');
      (*N17*)NFe.Det[i].Imposto.ICMS.vICMS        := Leitor.rCampo(tcDe2, 'vICMS');
      (*N18*)NFe.Det[i].Imposto.ICMS.modBCST      := StrToModBCST(ok, Leitor.rCampo(tcStr, 'modBCST'));
      (*N19*)NFe.Det[i].Imposto.ICMS.pMVAST       := Leitor.rCampo(tcDe2, 'pMVAST');
      (*N20*)NFe.Det[i].Imposto.ICMS.pRedBCST     := Leitor.rCampo(tcDe2, 'pRedBCST');
      (*N21*)NFe.Det[i].Imposto.ICMS.vBCST        := Leitor.rCampo(tcDe2, 'vBCST');
      (*N22*)NFe.Det[i].Imposto.ICMS.pICMSST      := Leitor.rCampo(tcDe2, 'pICMSST');
      (*N23*)NFe.Det[i].Imposto.ICMS.vICMSST      := Leitor.rCampo(tcDe2, 'vICMSST');
      (*N24*)NFe.Det[i].Imposto.ICMS.UFST         := Leitor.rCampo(tcStr, 'UFST');
      (*N25*)NFe.Det[i].Imposto.ICMS.pBCOp        := Leitor.rCampo(tcDe2, 'pBCOp');
      (*N26*)NFe.Det[i].Imposto.ICMS.vBCSTRet     := Leitor.rCampo(tcDe2, 'vBCSTRet');
      (*N27*)NFe.Det[i].Imposto.ICMS.vICMSSTRet   := Leitor.rCampo(tcDe2, 'vICMSSTRet');
      (*N28*)NFe.Det[i].Imposto.ICMS.motDesICMS   := StrTomotDesICMS(ok, Leitor.rCampo(tcStr, 'motDesICMS'));
      (*N29*)NFe.Det[i].Imposto.ICMS.pCredSN      := Leitor.rCampo(tcDe2, 'pCredSN');
      (*N30*)NFe.Det[i].Imposto.ICMS.vCredICMSSN  := Leitor.rCampo(tcDe2, 'vCredICMSSN');
      (*N31*)NFe.Det[i].Imposto.ICMS.vBCSTDest    := Leitor.rCampo(tcDe2, 'vBCSTDest');
      (*N32*)NFe.Det[i].Imposto.ICMS.vICMSSTDest  := Leitor.rCampo(tcDe2, 'vICMSSTDest');

      (*N10a*)
      (*N10b*)
      (*N12*) NFe.Det[i].Imposto.ICMS.CST         := VerificaParSt( NFe.Det[i].Imposto.ICMS.CST );
    end;
    if Leitor.rExtrai(3, 'IPI') <> '' then
    begin
      (*O02*)NFe.Det[i].Imposto.IPI.clEnq := Leitor.rCampo(tcStr, 'clEnq');
      (*O03*)NFe.Det[i].Imposto.IPI.CNPJProd := Leitor.rCampo(tcStr, 'CNPJProd');
      (*O04*)NFe.Det[i].Imposto.IPI.cSelo := Leitor.rCampo(tcStr, 'cSelo');
      (*O05*)NFe.Det[i].Imposto.IPI.qSelo := Leitor.rCampo(tcInt, 'qSelo');
      (*O06*)NFe.Det[i].Imposto.IPI.cEnq := Leitor.rCampo(tcStr, 'cEnq');


      // Inicializa CST com sendo N�o tributada e conforme o TIPO entrada ou saida
      // Caso a Tag n�o seja informada sera gravada com sendo n�o tributada
      if NFe.ide.tpNF = tnEntrada then
        NFe.Det[i].Imposto.IPI.CST := ipi53;
      if NFe.ide.tpNF = tnSaida then
        NFe.Det[i].Imposto.IPI.CST := ipi03;

      if Leitor.rExtrai(3, 'IPITrib') <> '' then
      begin
        (*O09*)NFe.Det[i].Imposto.IPI.CST := StrToCSTIPI(ok, Leitor.rCampo(tcStr, 'CST'));
        (*O10*)NFe.Det[i].Imposto.IPI.vBC := Leitor.rCampo(tcDe2, 'vBC');
        (*O11*)NFe.Det[i].Imposto.IPI.qUnid := Leitor.rCampo(tcDe4, 'qUnid');
        (*O12*)NFe.Det[i].Imposto.IPI.vUnid := Leitor.rCampo(tcDe4, 'vUnid');
        (*O13*)NFe.Det[i].Imposto.IPI.pIPI := Leitor.rCampo(tcDe2, 'pIPI');
        (*O14*)NFe.Det[i].Imposto.IPI.vIPI := Leitor.rCampo(tcDe2, 'vIPI');
      end;
      if Leitor.rExtrai(3, 'IPINT') <> '' then
      begin
        (*O09*)NFe.Det[i].Imposto.IPI.CST := StrToCSTIPI(ok, Leitor.rCampo(tcStr, 'CST'));
      end;
    end;
    if Leitor.rExtrai(3, 'II') <> '' then
    begin
      (*P02*)NFe.Det[i].Imposto.II.vBc := Leitor.rCampo(tcDe2, 'vBC');
      (*P03*)NFe.Det[i].Imposto.II.vDespAdu := Leitor.rCampo(tcDe2, 'vDespAdu');
      (*P04*)NFe.Det[i].Imposto.II.vII := Leitor.rCampo(tcDe2, 'vII');
      (*P05*)NFe.Det[i].Imposto.II.vIOF := Leitor.rCampo(tcDe2, 'vIOF');
    end;
    if Leitor.rExtrai(3, 'PIS') <> '' then
    begin
      (*Q06*)NFe.Det[i].Imposto.PIS.CST := StrToCSTPIS(ok, Leitor.rCampo(tcStr, 'CST'));
      (*Q07*)NFe.Det[i].Imposto.PIS.vBC := Leitor.rCampo(tcDe2, 'vBC');
      (*Q08*)NFe.Det[i].Imposto.PIS.pPIS := Leitor.rCampo(tcDe2, 'pPIS');
      (*Q09*)NFe.Det[i].Imposto.PIS.vPIS := Leitor.rCampo(tcDe2, 'vPIS');
      (*Q10*)NFe.Det[i].Imposto.PIS.qBCProd := Leitor.rCampo(tcDe4, 'qBCProd');
      (*Q11*)NFe.Det[i].Imposto.PIS.vAliqProd := Leitor.rCampo(tcDe4, 'vAliqProd');
    end;
    if Leitor.rExtrai(3, 'PISST') <> '' then
    begin
      (*R02*)NFe.Det[i].Imposto.PISST.vBc := Leitor.rCampo(tcDe2, 'vBC');
      (*R03*)NFe.Det[i].Imposto.PISST.pPis := Leitor.rCampo(tcDe2, 'pPIS');
      (*R04*)NFe.Det[i].Imposto.PISST.qBCProd := Leitor.rCampo(tcDe4, 'qBCProd');
      (*R05*)NFe.Det[i].Imposto.PISST.vAliqProd := Leitor.rCampo(tcDe4, 'vAliqProd');
      (*R06*)NFe.Det[i].Imposto.PISST.vPIS := Leitor.rCampo(tcDe2, 'vPIS');
    end;
    if Leitor.rExtrai(3, 'COFINS') <> '' then
    begin
      (*S06*)NFe.Det[i].Imposto.COFINS.CST := StrToCSTCOFINS(ok, Leitor.rCampo(tcStr, 'CST'));
      (*S07*)NFe.Det[i].Imposto.COFINS.vBC := Leitor.rCampo(tcDe2, 'vBC');
      (*S08*)NFe.Det[i].Imposto.COFINS.pCOFINS := Leitor.rCampo(tcDe2, 'pCOFINS');
      (*S09*)NFe.Det[i].Imposto.COFINS.qBCProd := Leitor.rCampo(tcDe4, 'qBCProd');
      (*S10*)NFe.Det[i].Imposto.COFINS.vAliqProd := Leitor.rCampo(tcDe4, 'vAliqProd');
      (*S11*)NFe.Det[i].Imposto.COFINS.vCOFINS := Leitor.rCampo(tcDe2, 'vCOFINS');
    end;
    if Leitor.rExtrai(3, 'COFINSST') <> '' then
    begin
      (*T02*)NFe.Det[i].Imposto.COFINSST.vBC := Leitor.rCampo(tcDe2, 'vBC');
      (*T03*)NFe.Det[i].Imposto.COFINSST.pCOFINS := Leitor.rCampo(tcDe2, 'pCOFINS');
      (*T04*)NFe.Det[i].Imposto.COFINSST.qBCProd := Leitor.rCampo(tcDe4, 'qBCProd');
      (*T05*)NFe.Det[i].Imposto.COFINSST.vAliqProd := Leitor.rCampo(tcDe4, 'vAliqProd');
      (*T06*)NFe.Det[i].Imposto.COFINSST.vCOFINS := Leitor.rCampo(tcDe2, 'vCOFINS');
    end;
    if Leitor.rExtrai(3, 'ISSQN') <> '' then
    begin
      (*U02*)NFe.Det[i].Imposto.ISSQN.vBC       := Leitor.rCampo(tcDe2, 'vBC');
      (*U03*)NFe.Det[i].Imposto.ISSQN.vAliq     := Leitor.rCampo(tcDe2, 'vAliq');
      (*U04*)NFe.Det[i].Imposto.ISSQN.vISSQN    := Leitor.rCampo(tcDe2, 'vISSQN');
      (*U05*)NFe.Det[i].Imposto.ISSQN.cMunFG    := Leitor.rCampo(tcInt, 'cMunFG');
      (*U06*)NFe.Det[i].Imposto.ISSQN.cListServ := Leitor.rCampo(tcInt, 'cListServ');
      (*U07*)NFe.Det[i].Imposto.ISSQN.cSitTrib  := StrToISSQNcSitTrib( ok,  Leitor.rCampo(tcStr, 'cSitTrib') ) ;
    end;

    inc(i);
  end;

  Leitor.Arquivo := Arquivo;

  (* Grupo da TAG <total> *****************************************************)
  if Leitor.rExtrai(1, 'total') <> '' then
  begin
    if Leitor.rExtrai(2, 'ICMSTot') <> '' then
    begin
      (*W03*)NFe.Total.ICMSTot.vBC := Leitor.rCampo(tcDe2, 'vBC');
      (*W04*)NFe.Total.ICMSTot.vICMS := Leitor.rCampo(tcDe2, 'vICMS');
      (*W05*)NFe.Total.ICMSTot.vBCST := Leitor.rCampo(tcDe2, 'vBCST');
      (*W06*)NFe.Total.ICMSTot.vST := Leitor.rCampo(tcDe2, 'vST');
      (*W07*)NFe.Total.ICMSTot.vProd := Leitor.rCampo(tcDe2, 'vProd');
      (*W08*)NFe.Total.ICMSTot.vFrete := Leitor.rCampo(tcDe2, 'vFrete');
      (*W09*)NFe.Total.ICMSTot.vSeg := Leitor.rCampo(tcDe2, 'vSeg');
      (*W10*)NFe.Total.ICMSTot.vDesc := Leitor.rCampo(tcDe2, 'vDesc');
      (*W11*)NFe.Total.ICMSTot.vII := Leitor.rCampo(tcDe2, 'vII');
      (*W12*)NFe.Total.ICMSTot.vIPI := Leitor.rCampo(tcDe2, 'vIPI');
      (*W13*)NFe.Total.ICMSTot.vPIS := Leitor.rCampo(tcDe2, 'vPIS');
      (*W14*)NFe.Total.ICMSTot.vCOFINS := Leitor.rCampo(tcDe2, 'vCOFINS');
      (*W15*)NFe.Total.ICMSTot.vOutro := Leitor.rCampo(tcDe2, 'vOutro');
      (*W16*)NFe.Total.ICMSTot.vNF := Leitor.rCampo(tcDe2, 'vNF');
    end;
    if Leitor.rExtrai(2, 'ISSQNtot') <> '' then
    begin
      (*W18*)NFe.Total.ISSQNtot.vServ := Leitor.rCampo(tcDe2, 'vServ');
      (*W19*)NFe.Total.ISSQNtot.vBC := Leitor.rCampo(tcDe2, 'vBC');
      (*W20*)NFe.Total.ISSQNtot.vISS := Leitor.rCampo(tcDe2, 'vISS');
      (*W21*)NFe.Total.ISSQNtot.vPIS := Leitor.rCampo(tcDe2, 'vPIS');
      (*W22*)NFe.Total.ISSQNtot.vCOFINS := Leitor.rCampo(tcDe2, 'vCOFINS');
    end;
    if Leitor.rExtrai(2, 'retTrib') <> '' then
    begin
      (*W24*)NFe.Total.retTrib.vRetPIS := Leitor.rCampo(tcDe2, 'vRetPIS');
      (*W25*)NFe.Total.retTrib.vRetCOFINS := Leitor.rCampo(tcDe2, 'vRetCOFINS');
      (*W26*)NFe.Total.retTrib.vRetCSLL := Leitor.rCampo(tcDe2, 'vRetCSLL');
      (*W27*)NFe.Total.retTrib.vBCIRRF := Leitor.rCampo(tcDe2, 'vBCIRRF');
      (*W28*)NFe.Total.retTrib.vIRRF := Leitor.rCampo(tcDe2, 'vIRRF');
      (*W29*)NFe.Total.retTrib.vBCRetPrev := Leitor.rCampo(tcDe2, 'vBCRetPrev');
      (*W30*)NFe.Total.retTrib.vRetPrev := Leitor.rCampo(tcDe2, 'vRetPrev');
    end;
  end;

  (* Grupo da TAG <transp> ****************************************************)
  if Leitor.rExtrai(1, 'transp') <> '' then
  begin
    (*X02*)NFe.Transp.modFrete := StrToModFrete(ok, Leitor.rCampo(tcStr, 'modFrete'));
    (*X25a*)NFe.Transp.vagao := Leitor.rCampo(tcStr, 'vagao');
    (*X25b*)NFe.Transp.balsa := Leitor.rCampo(tcStr, 'balsa');
    if Leitor.rExtrai(2, 'transporta') <> '' then
    begin
      (*X04/X05*)NFe.Transp.Transporta.CNPJCPF := Leitor.rCampoCNPJCPF;
      (*X06*)NFe.Transp.Transporta.xNome := Leitor.rCampo(tcStr, 'xNome');
      (*X07*)NFe.Transp.Transporta.IE := Leitor.rCampo(tcStr, 'IE');
      (*X08*)NFe.Transp.Transporta.xEnder := Leitor.rCampo(tcStr, 'xEnder');
      (*X09*)NFe.Transp.Transporta.xMun := Leitor.rCampo(tcStr, 'xMun');
      (*X10*)NFe.Transp.Transporta.UF := Leitor.rCampo(tcStr, 'UF');
    end;
    if Leitor.rExtrai(2, 'retTransp') <> '' then
    begin
      (*X12*)NFe.Transp.retTransp.vServ := Leitor.rCampo(tcDe2, 'vServ');
      (*X13*)NFe.Transp.retTransp.vBCRet := Leitor.rCampo(tcDe2, 'vBCRet');
      (*X14*)NFe.Transp.retTransp.pICMSRet := Leitor.rCampo(tcDe2, 'pICMSRet');
      (*X15*)NFe.Transp.retTransp.vICMSRet := Leitor.rCampo(tcDe2, 'vICMSRet');
      (*X16*)NFe.Transp.retTransp.CFOP := Leitor.rCampo(tcEsp, 'CFOP');
      (*X17*)NFe.Transp.retTransp.cMunFG := Leitor.rCampo(tcStr, 'cMunFG');
    end;
    if Leitor.rExtrai(2, 'veicTransp') <> '' then
    begin
      (*X19*)NFe.Transp.veicTransp.placa := Leitor.rCampo(tcStr, 'placa');
      (*X20*)NFe.Transp.veicTransp.UF := Leitor.rCampo(tcStr, 'UF');
      (*X21*)NFe.Transp.veicTransp.RNTC := Leitor.rCampo(tcStr, 'RNTC');
    end;

    i := 0;
    while Leitor.rExtrai(2, 'reboque', '', i + 1) <> '' do
    begin
      NFe.Transp.Reboque.add;
      (*X23*) NFe.Transp.Reboque[i].placa := Leitor.rCampo(tcStr, 'placa');
      (*X24*) NFe.Transp.Reboque[i].UF := Leitor.rCampo(tcStr, 'UF');
      (*X25*) NFe.Transp.Reboque[i].RNTC := Leitor.rCampo(tcStr, 'RNTC');
      inc(i);
    end;

    i := 0;
    while Leitor.rExtrai(2, 'vol', '', i + 1) <> '' do
    begin
      NFe.Transp.Vol.add;
      (*X27*)NFe.Transp.Vol[i].qVol := Leitor.rCampo(tcInt, 'qVol');
      (*X28*)NFe.Transp.vol[i].esp := Leitor.rCampo(tcStr, 'esp');
      (*X29*)NFe.Transp.Vol[i].marca := Leitor.rCampo(tcStr, 'marca');
      (*X30*)NFe.Transp.Vol[i].nVol := Leitor.rCampo(tcStr, 'nVol');
      (*X31*)NFe.Transp.Vol[i].pesoL := Leitor.rCampo(tcDe3, 'pesoL');
      (*X32*)NFe.Transp.Vol[i].pesoB := Leitor.rCampo(tcDe3, 'pesoB');
      j := 0;
      while Leitor.rExtrai(3, 'lacres', '', j + 1) <> '' do
      begin
        NFe.transp.Vol[i].lacres.add;
        (*X34*)NFe.transp.Vol[i].lacres[j].nLacre := Leitor.rCampo(tcStr, 'nLacre');
        inc(j);
      end;
      inc(i);
    end;

  end;

  (* Grupo da TAG <cobr> ******************************************************)
  if Leitor.rExtrai(1, 'cobr') <> '' then
  begin
    if Leitor.rExtrai(1, 'fat') <> '' then
    begin
      (*Y03*)NFe.Cobr.Fat.nFat := Leitor.rCampo(tcStr, 'nFat');
      (*Y04*)NFe.Cobr.Fat.vOrig := Leitor.rCampo(tcDe2, 'vOrig');
      (*Y05*)NFe.Cobr.Fat.vDesc := Leitor.rCampo(tcDe2, 'vDesc');
      (*Y06*)NFe.Cobr.Fat.vLiq := Leitor.rCampo(tcDe2, 'vLiq');
    end;
    i := 0;
    while Leitor.rExtrai(1, 'dup', '', i + 1) <> '' do
    begin
      NFe.Cobr.Dup.Add;
      (*Y08*)NFe.Cobr.Dup[i].nDup := Leitor.rCampo(tcStr, 'nDup');
      (*Y09*)NFe.Cobr.Dup[i].dVenc := Leitor.rCampo(tcDat, 'dVenc');
      (*Y10*)NFe.Cobr.Dup[i].vDup := Leitor.rCampo(tcDe2, 'vDup');
      inc(i);
    end;
  end;

  if NFe.infNFe.Versao >= 3 then
   begin
    (* Grupo da TAG <pag> ******************************************************)
    i := 0;
    while Leitor.rExtrai(1, 'pag', '', i + 1) <> '' do
     begin
       NFe.pag.Add;
      (*YA02*)NFe.pag[i].tPag := StrToFormaPagamento(ok, Leitor.rCampo(tcStr, 'tPag'));
      (*YA03*)NFe.pag[i].vPag := Leitor.rCampo(tcDe2, 'vPag');
      if Leitor.rExtrai(2, 'card') <> '' then
       begin
        (*YA05*)NFe.pag[i].CNPJ := Leitor.rCampo(tcStr, 'CNPJ');
        (*YA06*)NFe.pag[i].tBand := StrToBandeiraCartao(ok, Leitor.rCampo(tcStr, 'tBand'));
        (*YA07*)NFe.pag[i].cAut := Leitor.rCampo(tcStr, 'cAut');
       end;
      inc(i);
    end;
   end;

  (* Grupo da TAG <InfAdic> ***************************************************)

  if Leitor.rExtrai(1, 'infAdic') <> '' then
  begin
    (*Z02*)NFe.InfAdic.infAdFisco := Leitor.rCampo(tcStr, 'infAdFisco');
    (*Z03*)NFe.InfAdic.infCpl := Leitor.rCampo(tcStr, 'infCpl');
    i := 0;
    while Leitor.rExtrai(2, 'obsCont', '', i + 1) <> '' do
    begin
      NFe.InfAdic.obsCont.Add;
      (*Z05*)NFe.InfAdic.obsCont[i].xCampo := Leitor.rAtributo('xCampo');
      (*Z06*)NFe.InfAdic.obsCont[i].xTexto := Leitor.rCampo(tcStr, 'xTexto');
      inc(i);
    end;
    i := 0;
    while Leitor.rExtrai(2, 'obsFisco', '', i + 1) <> '' do
    begin
      NFe.InfAdic.obsFisco.Add;
      (*Z08*)NFe.InfAdic.obsFisco[i].xCampo := Leitor.rAtributo('xCampo');
      (*Z09*)NFe.InfAdic.obsFisco[i].xTexto := Leitor.rCampo(tcStr, 'xTexto');
      inc(i)
    end;
    i := 0;
    while Leitor.rExtrai(2, 'procRef', '', i + 1) <> '' do
    begin
      NFe.InfAdic.procRef.Add;
      (*Z11*)NFe.InfAdic.procRef[i].nProc := Leitor.rCampo(tcStr, 'nProc');
      (*Z12*)NFe.InfAdic.procRef[i].indProc := StrToIndProc(ok, Leitor.rCampo(tcStr, 'indProc'));
      inc(i);
    end;
  end;

  (* Grupo da TAG <exporta> ***************************************************)
  if Leitor.rExtrai(1, 'exporta') <> '' then
  begin
    (*ZA02*)NFe.exporta.UFembarq := Leitor.rCampo(tcStr, 'UFEmbarq');
    (*ZA03*)NFe.exporta.xLocEmbarq := Leitor.rCampo(tcStr, 'xLocEmbarq');
  end;

  (* Grupo da TAG <compra> ****************************************************)
  if Leitor.rExtrai(1, 'compra') <> '' then
  begin
    (*ZB02*)NFe.compra.xNEmp := Leitor.rCampo(tcStr, 'xNEmp');
    (*ZB03*)NFe.compra.xPed := Leitor.rCampo(tcStr, 'xPed');
    (*ZB04*)NFe.compra.xCont := Leitor.rCampo(tcStr, 'xCont');
  end;

  (* Grupo da TAG <cana> ****************************************************)
  if Leitor.rExtrai(1, 'cana') <> '' then
  begin
    (*ZC02*) NFe.cana.safra := Leitor.rCampo(tcStr, 'safra');
    (*ZC03*) NFe.cana.ref := Leitor.rCampo(tcStr, 'ref');
    (*ZC07*) NFe.cana.qTotMes := Leitor.rCampo(tcDe10, 'qTotMes');
    (*ZC08*) NFe.cana.qTotAnt := Leitor.rCampo(tcDe10, 'qTotAnt');
    (*ZC09*) NFe.cana.qTotGer := Leitor.rCampo(tcDe10, 'qTotGer');
    (*ZC13*) NFe.cana.vFor := Leitor.rCampo(tcDe2, 'vFor');
    (*ZC14*) NFe.cana.vTotDed := Leitor.rCampo(tcDe2, 'vTotDed');
    (*ZC15*) NFe.cana.vLiqFor := Leitor.rCampo(tcDe2, 'vLiqFor');

    i := 0;
    while Leitor.rExtrai(2, 'forDia', '', i + 1) <> '' do
    begin
      NFe.cana.fordia.Add;
      (*ZC05*) NFe.cana.fordia[i].dia := Leitor.rAtributo('dia');
      (*ZC06*) NFe.cana.fordia[i].qtde := Leitor.rCampo(tcDe10, 'qtde');
      inc(i);
    end;

    i := 0;
    while Leitor.rExtrai(2, 'deduc', '', i + 1) <> '' do
    begin
      NFe.cana.deduc.Add;
      (*ZC11*) NFe.cana.deduc[i].xDed := Leitor.rCampo(tcStr, 'xDed');
      (*ZC12*) NFe.cana.deduc[i].vDed := Leitor.rCampo(tcDe2, 'vDed');
      inc(i);
    end;

  end;

  (* Grupo da TAG <signature> *************************************************)

  leitor.Grupo := Leitor.Arquivo;

  NFe.signature.URI := Leitor.rAtributo('Reference URI=');
  NFE.signature.DigestValue := Leitor.rCampo(tcStr, 'DigestValue');
  NFE.signature.SignatureValue := Leitor.rCampo(tcStr, 'SignatureValue');
  NFE.signature.X509Certificate := Leitor.rCampo(tcStr, 'X509Certificate');

  (* Grupo da TAG <protNFe> ****************************************************)
  if Leitor.rExtrai(1, 'protNFe') <> '' then
  begin
     NFe.procNFe.tpAmb    := StrToTpAmb(ok, Leitor.rCampo(tcStr, 'tpAmb'));
     NFe.procNFe.verAplic := Leitor.rCampo(tcStr, 'verAplic');
     NFe.procNFe.chNFe    := Leitor.rCampo(tcStr, 'chNFe');
     NFe.procNFe.dhRecbto := Leitor.rCampo(tcDatHor, 'dhRecbto');
     NFe.procNFe.nProt    := Leitor.rCampo(tcStr, 'nProt');
     NFe.procNFe.digVal   := Leitor.rCampo(tcStr, 'digVal');
     NFe.procNFe.cStat    := Leitor.rCampo(tcInt, 'cStat');
     NFe.procNFe.xMotivo  := Leitor.rCampo(tcStr, 'xMotivo');
  end;

  Result := true;

end;
end.

