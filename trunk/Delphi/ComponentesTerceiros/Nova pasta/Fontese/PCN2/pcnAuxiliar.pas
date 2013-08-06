////////////////////////////////////////////////////////////////////////////////
//                                                                            //
//              PCN - Projeto Cooperar NFe                                    //
//                                                                            //
//   Descri��o: Classes para gera��o/leitura dos arquivos xml da NFe          //
//                                                                            //
//        site: www.projetocooperar.org/nfe                                   //
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
|* 03/08/2011: Italo
|*  - Tipos para LayOut do CT-e
|* 09/04/2012: Italo
|*  - Eventos da NFe
|* 17/07/2012: Italo
|*  - Consulta de NFe pelo destinat�rio e download da NFe
|* 24/09/2012: Italo
|*  - Inclu�do Fun��o DateTimeTodhUTC
|* 23/10/2012: Alan - http://www.djsystem.com.br/acbr/forum/viewtopic.php?f=6&t=4754
|*  - Inclu�do Fun��o GetUTC
|*  - Inclu�do Fun��o IsHorarioDeVerao
|*  - Inclu�do Fun��o GetTerceiroDomingoDoMes
|*  - Inclu�do Fun��o GetInicioDoHorarioDeVerao
|*  - Inclu�do Fun��o GetFimDoHorarioDeVerao
|*  - Inclu�do Fun��o GetDataDoCarnaval
|*  - Inclu�do Fun��o GetDataDaPascoa
******************************************************************************}
{$IFDEF FPC}
  {$MODE DELPHI}
{$ENDIF}

unit pcnAuxiliar;

interface uses

  SysUtils, Classes,
{$IFNDEF VER130}
  Variants,
{$ENDIF}
  pcnConversao;

function CodigoParaUF(const codigo: integer): string;
function DateTimeTodh(DataHora: TDateTime): string;
function ExecutarAjusteTagNro(Corrigir: boolean; Nro: string): string;
function FiltrarTextoXML(const RetirarEspacos: boolean; aTexto: AnsiString; RetirarAcentos: boolean = True): AnsiString;
function IIf(const condicao: Boolean; const Verdadeiro, Falso: Variant): Variant;
function IntToStrZero(const Numero: integer; const tamanho: integer): string;
function GerarCodigoNumerico(numero: integer): integer;
function GerarChave(var chave: AnsiString; const codigoUF: integer; codigoNumerico: integer; const modelo, serie, numero, tpemi: integer; const emissao: TDateTime; const CNPJ: string): boolean;
function GerarChaveCTe(var chave: AnsiString; const codigoUF: integer; codigoNumerico: integer; const modelo, serie, numero: integer; const emissao: TDateTime; const CNPJ: string): boolean;
function GerarDigito(var Digito: integer; chave: string): boolean;
function SomenteNumeros(const s: string): string;
function RetornarCodigoNumerico(Chave: string; Versao : real): integer;
function RetornarCodigoNumericoCTe(Chave: string): integer;
function RetornarConteudoEntre(const Frase, Inicio, Fim: string): string;
function RetornarDigito(const chave: string): integer;
function RetornarVersaoLayout(const schema: TpcnSchema; const Layout: TpcnTipoLayout): string;
function ReverterFiltroTextoXML(aTexto: AnsiString): AnsiString;
function UFparaCodigo(const UF: string): integer;
function ValidarAAMM(const AAMM: string): boolean;
function ValidarCListServ(const cListServ: integer): boolean;
function ValidarChave(const chave: string): boolean;
function ValidarCodigoPais(const iPais: integer): smallint;
function ValidarCodigoUF(const Codigo: integer): boolean;
function ValidarCNPJ(const numero: string): boolean;
function ValidarCPF(const numero: string): boolean;
function ValidarMod(const modelo: integer): boolean;
function ValidarMunicipio(const Municipio: integer): boolean;
function ValidarNumeros(const s: string): boolean;
function ValidarUF(const UF: string): boolean;
function ValidarIE(IE, UF: string; ApenasDigitos: Boolean = True): boolean;
function ValidarISUF(const ISUF: string): boolean;
function SubStrEmSubStr(const SubStr1: string; SubStr2: string): boolean;
function xml4line(texto: AnsiString): AnsiString;
function RetornarPosEx(const SubStr, S: AnsiString; Offset: Cardinal = 1): Integer;
function DateTimeTodhUTC(DataHora: TDateTime; TZD: string): string;
function GetUTC(UF: string; const dataHora: TDateTime): string;
function IsHorarioDeVerao(const UF: string; const dataHora: TDateTime): Boolean;
function GetTerceiroDomingoDoMes(const ano, mes: Integer): TDateTime;
function GetInicioDoHorarioDeVerao(const ano: Integer): TDateTime;
function GetFimDoHorarioDeVerao(const ano: Integer): TDateTime;
function GetDataDoCarnaval(const ano: Integer): TDateTime;
function GetDataDaPascoa(const ano: Integer): TDateTime;

implementation

uses
  DateUtils;

function CodigoParaUF(const codigo: integer): string;
const
  (**)UFS = '.AC.AL.AP.AM.BA.CE.DF.ES.GO.MA.MT.MS.MG.PA.PB.PR.PE.PI.RJ.RN.RS.RO.RR.SC.SP.SE.TO.DF.DF';
  CODIGOS = '.12.27.16.13.29.23.53.32.52.21.51.50.31.15.25.41.26.22.33.24.43.11.14.42.35.28.17.90.91';
begin
  try
    result := copy(UFS, pos('.' + IntToStr(Codigo) + '.', CODIGOS) + 1, 2);
  except
    result := '';
  end;
  if not ValidarCodigoUF(Codigo) then
    result := '';
end;

function DateTimeTodh(DataHora: TDateTime): string;
var
  wAno, wMes, wDia, wHor, wMin, wSeg, wMil: word;
begin
  DecodeDate(DataHora, wAno, wMes, wDia);
  DecodeTime(DataHora, wHor, wMin, wSeg, wMil);
  Result := IntToStrZero(wAno, 4) + '-' +
            IntToStrZero(wMes, 2) + '-' +
            IntToStrZero(wDia, 2) + 'T' +
            IntToStrZero(wHor, 2) + ':' +
            IntToStrZero(wMin, 2) + ':' +
            IntToStrZero(wSeg, 2);
end;

function ExecutarAjusteTagNro(Corrigir: boolean; Nro: string): string;
begin
  Nro := trim(Nro);
  result := Nro;
  if not corrigir then
    exit;
  if (ValidarNumeros(Nro)) and (length(Nro) = 1) then
    Result := '00' + Nro;
  if (ValidarNumeros(Nro)) and (length(Nro) = 2) then
    Result := '0' + Nro;
end;

function FiltrarTextoXML(const RetirarEspacos: boolean; aTexto: AnsiString; RetirarAcentos: boolean = True): AnsiString;
var
  i: integer;
const
  COM_ACENTO = '����������������������������������������������';
  SEM_ACENTO = 'aaeouaoaeioucuiaeioeiouAAEOUAOAEIOUCUIAEIOEIOU';
begin
  if RetirarAcentos then
   begin
     for i := 1 to Length(aTexto) do
      begin
{$WARNINGS OFF}
        if Pos(aTexto[i], COM_ACENTO) <> 0 then
          aTexto[i] := AnsiChar(SEM_ACENTO[Pos(aTexto[i], COM_ACENTO)]);
//          aTexto[i] := SEM_ACENTO[Pos(aTexto[i], COM_ACENTO)];
        if not (aTexto[i] in [' '..'~']) then
          aTexto[i] := ' ';
{$WARNINGS ON}
      end;
   end;
  aTexto := StringReplace(aTexto, '&', '&amp;', [rfReplaceAll]);
  aTexto := StringReplace(aTexto, '<', '&lt;', [rfReplaceAll]);
  aTexto := StringReplace(aTexto, '>', '&gt;', [rfReplaceAll]);
  aTexto := StringReplace(aTexto, '"', '&quot;', [rfReplaceAll]);
  aTexto := StringReplace(aTexto, #39, '&#39;', [rfReplaceAll]);
  if RetirarEspacos then
    while pos('  ', aTexto) > 0 do
      aTexto := StringReplace(aTexto, '  ', ' ', [rfReplaceAll]);
  result := Trim(aTexto);
end;

function IIf(const condicao: Boolean; const Verdadeiro, Falso: Variant): Variant;
begin
  if condicao then
    Result := Verdadeiro
  else
    Result := Falso;
end;

function IntToStrZero(const Numero: integer; const tamanho: integer): string;
begin
  result := StringOfChar('0', tamanho) + IntToStr(Numero);
  result := copy(result, length(result) - tamanho + 1, tamanho);
end;

function GerarCodigoNumerico(numero: integer): integer;
var
  s: string;
  i, j, k: integer;
begin
  // Essa fun��o gera um c�digo numerico atrav�z de calculos realizados sobre o parametro numero
  s := intToStr(numero);
  for i := 1 to 9 do
    s := s + intToStr(numero);
  for i := 1 to 9 do
  begin
    k := 0;
    for j := 1 to 9 do
      k := k + StrToInt(s[j]) * (j + 1);
    s := IntToStr((k mod 11)) + s;
  end;
  Result := StrToInt(copy(s, 1, 8));
end;

function GerarChave(var chave: AnsiString; const codigoUF: integer; codigoNumerico: integer;
  const modelo, serie, numero, tpemi: integer; const emissao: TDateTime; const CNPJ: string): boolean;
var
  digito: integer;
  wAno, wMes, wDia: Word;
begin
  result := true;
  try
    // Se o usuario informar 0; o c�digo numerico sera gerado de maneira aleat�ria //
    while codigoNumerico = 0 do
    begin
      Randomize;
      codigoNumerico := Random(99999999);
    end;
    // se o usuario informar -1 o c�digo numerico ser� gerado atrav�z da fun��o
    // GerarC�digoNumerico baseado no numero do documento fiscal.
    if codigoNumerico = -1 then
      codigoNumerico := GerarCodigoNumerico(Numero)
    else if codigoNumerico = -2 then
      codigoNumerico := 0;
    //
    DecodeDate(emissao, wAno, wMes, wDia);
    chave := 'NFe' +
      IntToStrZero(codigoUF, 2) +
      Copy(FormatFloat('0000', wAno), 3, 2) +
      FormatFloat('00', wMes) +
      copy(SomenteNumeros(CNPJ) + '00000000000000', 1, 14) +
      IntToStrZero(modelo, 2) +
      IntToStrZero(serie, 3) +
      IntToStrZero(Numero, 9) +
      IntToStrZero(TPEmi, 1) +
      IntToStrZero(codigoNumerico, 8);
    GerarDigito(digito, chave);
    chave := chave + IntToStr(digito);
  except
    chave := '';
    result := false;
    exit;
  end;
end;

function GerarChaveCTe(var chave: AnsiString; const codigoUF: integer; codigoNumerico: integer;
  const modelo, serie, numero: integer; const emissao: TDateTime; const CNPJ: string): boolean;
var
  digito: integer;
  wAno, wMes, wDia: Word;
begin
  result := true;
  try
    // Se o usuario informar 0; o c�digo numerico sera gerado de maneira aleat�ria //
    while codigoNumerico = 0 do
    begin
      Randomize;
      codigoNumerico := Random(999999999);
    end;
    // se o usuario informar -1 o c�digo numerico ser� gerado atrav�z da fun��o
    // GerarC�digoNumerico baseado no numero do documento fiscal.
    if codigoNumerico = -1 then
      codigoNumerico := GerarCodigoNumerico(Numero);
    //
    DecodeDate(emissao, wAno, wMes, wDia);
    chave := 'CTe' +
      IntToStrZero(codigoUF, 2) +
      Copy(FormatFloat('0000', wAno), 3, 2) +
      FormatFloat('00', wMes) +
      copy(SomenteNumeros(CNPJ) + '00000000000000', 1, 14) +
      IntToStrZero(modelo, 2) +
      IntToStrZero(serie, 3) +
      IntToStrZero(Numero, 9) +
      IntToStrZero(codigoNumerico, 9);
    GerarDigito(digito, chave);
    chave := chave + IntToStr(digito);
  except
    chave := '';
    result := false;
    exit;
  end;
end;


function GerarDigito(var Digito: integer; chave: string): boolean;
var
  i, j: integer;
const
  PESO = '4329876543298765432987654329876543298765432';
begin
  // Manual Integracao Contribuinte v2.02a - P�gina: 70 //
  chave := somenteNumeros(chave);
  j := 0;
  Digito := 0;
  result := True;
  try
    for i := 1 to 43 do
      j := j + StrToInt(copy(chave, i, 1)) * StrToInt(copy(PESO, i, 1));
    Digito := 11 - (j mod 11);
    if (j mod 11) < 2 then
      Digito := 0;
  except
    result := False;
  end;
  if length(chave) <> 43 then
    result := False;
end;

function SomenteNumeros(const s: string): string;
var
  i: integer;
begin
  result := '';
  for i := 1 to length(s) do
    if pos(s[i], '0123456789') > 0 then
      result := result + s[i];
end;

function RetornarCodigoNumerico(chave: string; versao : real): integer;
begin
  chave := SomenteNumeros(chave);

  if versao < 2 then
     result := StrToInt(copy(chave, 35, 9))
  else
     result := StrToInt(copy(chave, 36, 8));
end;

function RetornarCodigoNumericoCTe(chave: string): integer;
begin
  result := RetornarCodigoNumerico(chave, 1);
end;

function RetornarDigito(const chave: string): integer;
begin
  result := StrToInt(chave[length(chave)]);
end;

function RetornarVersaoLayout(const schema: TpcnSchema; const Layout: TpcnTipoLayout): string;
begin
  Result := '0.00';
  case schema of
    TsPL005c : case Layout of
                tlAtuCadEmiDFe    : Result := '1.01';
                tlCadEmiDFe       : Result := '1.01';
                tlCancNFe         : Result := '1.07';
                tlConsCad         : Result := '1.01';
                tlConsReciNFe     : Result := '1.10';
                tlConsSitNFe      : Result := '1.07';
                tlConsStatServ    : Result := '1.07';
                tlInutNFe         : Result := '1.07';
                tlNFe             : Result := '1.10';
                tlProcNFe         : Result := '1.10';
                tlProcInutNFe     : Result := '1.07';
                tlRetAtuCadEmiDFe : Result := '1.01';
                tlRetCancNFe      : Result := '1.07';
                tlRetConsCad      : Result := '1.01';
                tlRetConsReciNFe  : Result := '1.10';
                tlRetConsStatServ : Result := '1.07';
                tlRetConsSitNFe   : Result := '1.07';
                tlRetEnvNFe       : Result := '1.10';
                tlRetInutNFe      : Result := '1.07';
                tlEnvNFe          : Result := '1.10';
                tlProcCancNFe     : Result := '1.07';
                tlEnvDPEC         : Result := '1.01';
                tlConsDPEC        : Result := '1.01';
                tlCCeNFe          : Result := '2.00';
              end;
    TsPL006 : case Layout of
                tlCancNFe         : Result := '2.00';
                tlConsCad         : Result := '2.00';
                tlConsReciNFe     : Result := '2.00';
                tlConsSitNFe      : Result := '2.01';
                tlConsStatServ    : Result := '2.00';
                tlInutNFe         : Result := '2.00';
                tlNFe             : Result := '2.00';
                tlProcNFe         : Result := '2.00';
                tlProcInutNFe     : Result := '2.00';
                tlRetAtuCadEmiDFe : Result := '2.00';
                tlRetCancNFe      : Result := '2.00';
                tlRetConsCad      : Result := '2.00';
                tlRetConsReciNFe  : Result := '2.00';
                tlRetConsStatServ : Result := '2.00';
                tlRetConsSitNFe   : Result := '2.01';
                tlRetEnvNFe       : Result := '2.00';
                tlRetInutNFe      : Result := '2.00';
                tlEnvNFe          : Result := '2.00';
                tlProcCancNFe     : Result := '2.00';
                tlEnvDPEC         : Result := '2.00';
                tlConsDPEC        : Result := '2.00';
                tlCCeNFe          : Result := '2.00';
                tlEnvEventoNFe    : Result := '2.00';
                tlConsNFeDest     : Result := '2.00';
                tlDownloadNFe     : Result := '2.00';
              end;

    // Tipos de LayOut para CT-e
    TsPL_CTe_103 :  case Layout of
                      tlConsStatServCTe : Result := '1.03';
                      tlCTe             : Result := '1.03';
                      tlEnvCTe          : Result := '1.03';
                      tlRetEnvCTe       : Result := '1.03';
                      tlProcCTe         : Result := '1.03';
                      tlConsReciCTe     : Result := '1.03';
                      tlRetConsReciCTe  : Result := '1.03';
                      tlConsSitCTe      : Result := '1.03';
                      tlRetConsSitCTe   : Result := '1.03';
                      tlCancCTe         : Result := '1.03';
                      tlProcCancCTe     : Result := '1.03';
                      tlRetCancCTe      : Result := '1.03';
                      tlInutCTe         : Result := '1.03';
                      tlProcInutCTe     : Result := '1.03';
                      tlRetInutCTe      : Result := '1.03';
                    end;
    TsPL_CTe_104 :  case Layout of
                      tlConsStatServCTe : Result := '1.04';
                      tlCTe             : Result := '1.04';
                      tlEnvCTe          : Result := '1.04';
                      tlRetEnvCTe       : Result := '1.04';
                      tlProcCTe         : Result := '1.04';
                      tlConsReciCTe     : Result := '1.04';
                      tlRetConsReciCTe  : Result := '1.04';
                      tlConsSitCTe      : Result := '1.04';
                      tlRetConsSitCTe   : Result := '1.04';
                      tlCancCTe         : Result := '1.04';
                      tlProcCancCTe     : Result := '1.04';
                      tlRetCancCTe      : Result := '1.04';
                      tlInutCTe         : Result := '1.04';
                      tlProcInutCTe     : Result := '1.04';
                      tlRetInutCTe      : Result := '1.04';
                    end;
  end;
end;

function HexToAscii(Texto: string): String;
var i : integer;
   function HexToInt(Hex: string): integer;
   begin
     Result := StrToInt('$' + Hex);
   end;
begin
  result := '';
  for i := 1 to Length(texto) do begin
    if i mod 2 <> 0 then
       result := result + chr(HexToInt(copy(texto,i,2)));
  end;
end;

function ReverterFiltroTextoXML(aTexto: AnsiString): AnsiString;
var p1,p2:Integer;
    vHex,vStr:String;
begin
  aTexto := StringReplace(aTexto, '&amp;', '&', [rfReplaceAll]);
  aTexto := StringReplace(aTexto, '&lt;', '<', [rfReplaceAll]);
  aTexto := StringReplace(aTexto, '&gt;', '>', [rfReplaceAll]);
  aTexto := StringReplace(aTexto, '&quot;', '"', [rfReplaceAll]);
  aTexto := StringReplace(aTexto, '&#39;', #39, [rfReplaceAll]);
  p1:=Pos('&#x',aTexto);
  while p1>0 do begin
    for p2:=p1 to Length(aTexto) do
        if aTexto[p2]=';' then
           break;
    vHex:=Copy(aTexto,p1,p2-p1+1);
    vStr:=StringReplace(vHex,'&#x','',[rfReplaceAll]);
    vStr:=StringReplace(vStr,';','',[rfReplaceAll]);
    vStr:=HexToAscii(vStr);
    aTexto:=StringReplace(aTexto,vHex,vStr,[rfReplaceAll]);
    p1:=Pos('&#x',aTexto);
  end;
  result := Trim(aTexto);
end;

{function ReverterFiltroTextoXML(aTexto: AnsiString): AnsiString;
begin
  aTexto := StringReplace(aTexto, '&amp;', '&', [rfReplaceAll]);
  aTexto := StringReplace(aTexto, '&lt;', '<', [rfReplaceAll]);
  aTexto := StringReplace(aTexto, '&gt;', '>', [rfReplaceAll]);
  aTexto := StringReplace(aTexto, '&quot;', '"', [rfReplaceAll]);
  aTexto := StringReplace(aTexto, '&#39;', #39, [rfReplaceAll]);
  result := Trim(aTexto);
end;}

function UFparaCodigo(const UF: string): integer;
const
  (**)UFS = '.AC.AL.AP.AM.BA.CE.DF.ES.GO.MA.MT.MS.MG.PA.PB.PR.PE.PI.RJ.RN.RS.RO.RR.SC.SP.SE.TO.';
  CODIGOS = '.12.27.16.13.29.23.53.32.52.21.51.50.31.15.25.41.26.22.33.24.43.11.14.42.35.28.17.';
begin
  try
    result := StrToInt(copy(CODIGOS, pos('.' + UF + '.', UFS) + 1, 2));
  except
    result := 0;
  end;
  if (not ValidarUF(UF)) or (UF = 'EX') then
    result := 0;
end;

function ValidarChave(const chave: string): boolean;
var
  i: integer;
begin
  result := false;
  if (copy(chave, 1, 3) <> 'NFe') and (copy(chave, 1, 3) <> 'CTe') then
    exit;
  try
    i := 0;
    if GerarDigito(i, copy(chave, 4, 43)) then
      result := i = StrToInt(chave[length(chave)]);
    if result then
      result := ValidarCodigoUF(StrToInt(copy(somenteNumeros(chave), 1, 2)));
    if result then
      result := ValidarAAMM(copy(somenteNumeros(chave), 3, 4));
    if result then
      result := ValidarCNPJ(copy(somenteNumeros(chave), 7, 14));
  except
    result := false;
  end;
end;

function ValidarAAMM(const AAMM: string): boolean;
begin
  result := (length(AAMM) = 4);
  if not validarNumeros(AAMM) then
    result := false;
  if (result) and (not (StrToInt(copy(AAMM, 3, 2)) in [01..12])) then
    result := false;
end;

function ValidarCodigoPais(const iPais: integer): smallint;
var
  i, soma: integer;
  sPais: string;
const
  MAXIMO = 4;
  PESO = '432';
  CODIGO = '|0132|7560|0175|0230|0370|0400|0418|0434|0477|0531|0590|0639|0647' +
    '|0655|0698|0728|0736|0779|0809|0817|0833|0850|0876|0884|2291|0906' +
    '|0973|0981|1015|1058|1082|1112|0310|1155|1198|1279|1457|1414|1490' +
    '|1511|1546|1376|1538|7889|1589|1600|1635|5118|7412|1651|1694|1732' +
    '|1775|8885|1830|1872|1902|1937|1961|1988|1953|1996|2321|7838|2356' +
    '|2402|6874|2445|2399|2437|2470|2461|2453|2496|2518|2534|2550|2593' +
    '|8702|2674|2712|1619|2755|2810|2852|2895|2917|2933|2976|3018|3050' +
    '|3093|3131|3174|1504|3379|3255|3298|3344|3310|3417|3450|3514|3557' +
    '|3573|3611|3654|3727|3697|3751|3794|3832|3867|3913|3999|1508|3964' +
    '|4030|4111|4200|4235|4260|4278|4316|4340|4383|4405|4421|4456|4472' +
    '|4499|4502|4525|4553|4588|4618|4642|4677|3595|4723|4740|4766|4774' +
    '|4855|4880|4936|0930|4995|4901|5053|4944|4952|4979|4985|5010|5070' +
    '|5088|5177|5215|5258|5282|5312|5355|5380|5428|5487|5568|5665|5738' +
    '|5754|5800|5452|5762|5860|5894|5932|5991|6033|6114|6076|6238|6254' +
    '|6289|6408|6475|6602|6700|6750|6769|6858|6781|6777|6904|6912|6971' +
    '|7102|7153|6955|7005|7056|7285|7358|7370|7315|7447|7480|7501|7544' +
    '|7595|7641|7676|7706|7722|7765|7803|7919|7820|7951|8001|8109|8052' +
    '|8150|8206|8230|8249|8273|8281|8311|8338|8451|8478|5517|8486|8508' +
    '|8583|8630|8664|8737|8907|6653|';
begin
  // Resultados possiveis:
  //  1 = Validou - O c�digo existia na lista.
  //  0 = Alerta  - O c�digo n�o estava na lista (mas o digito confere).
  // -1 = Erro    - O c�digo n�o estava na lista (o digito n�o confere).
  result := 1;
  sPais := copy('0000' + intToStr(iPais), length(intToStr(iPais)) + 1, 4);
  if pos('|' + sPais + '|', CODIGO) > 0 then
    exit;
  // Verificar o dig�to caso o c�digo n�o estaja na lista
  soma := 0;
  for i := 1 to MAXIMO - 1 do
    soma := soma + StrToInt(copy(sPais, i, 1)) * StrToInt(copy(PESO, i, 1));
  // Se o resto igual = 0 ou 1 o digito deve ser = '0'
  result := 0;
  if ((soma mod 11) < 2) and (sPais[MAXIMO] = '0') then
    exit;
  // Para resto maior que 1
  if IntToStr((11 - (soma mod 11))) <> sPais[MAXIMO] then
    result := -1;
end;

function ValidarCListServ(const cListServ: integer): boolean;
const
  CODIGO = '|101|102|103|104|105|106|107|108|201|302|303|' +
    '|304|305|401|402|403|404|405|406|407|408|409|' +
    '|410|411|412|413|414|415|416|417|418|419|420|' +
    '|421|422|423|501|502|503|504|505|506|507|508|' +
    '|509|601|602|603|604|605|701|702|703|704|705|' +
    '|706|707|708|709|710|711|712|713|716|717|718|' +
    '|719|720|721|722|801|802|901|902|903|' +
    '|1001|1002|1003|1004|1005|1006|1007|1008|1009|1010|1101|' +
    '|1102|1103|1104|1201|1202|1203|1204|1205|1206|1207|1208|' +
    '|1209|1210|1211|1212|1213|1214|1215|1216|1217|1302|1303|' +
    '|1304|1305|1401|1402|1403|1404|1405|1406|1407|1408|1409|' +
    '|1410|1411|1412|1413|1501|1502|1503|1504|1505|1506|1507|' +
    '|1508|1509|1510|1511|1512|1513|1514|1515|1516|1517|1518|' +
    '|1601|1701|1702|1703|1704|1705|1706|1708|1709|1710|1711|' +
    '|1712|1713|1714|1715|1716|1717|1718|1719|1720|1721|1722|' +
    '|1723|1724|1801|1901|2001|2002|2003|2101|2201|2301|2401|' +
    '|2501|2502|2503|2504|2601|2701|2801|2901|3001|3101|3201|' +
    '|3301|3401|3501|3601|3701|3801|3901|4001|';
begin
  result := pos('|' + IntToStr(cListServ) + '|', CODIGO) > 0;
end;

function ValidarCodigoUF(const Codigo: integer): boolean;
const
  CODIGOS = '.12.27.16.13.29.23.53.32.52.21.51.50.31.15.25.41.26.22.33.24.43.11.14.42.35.28.17.90.91';
begin
  result := pos('.' + IntToStr(Codigo) + '.', CODIGOS) > 0;
end;

function ValidarCNPJ(const numero: string): boolean;
var
  i, soma, digito1, digito2: SmallInt;
begin
  result := False;
  if length(numero) <> 14 then
    exit;
  soma := 0;
  for i := 1 to 12 do
    soma := soma + StrToInt(Copy(numero, i, 1)) *
      (StrToInt(Copy('5432987654320', i, 1)));
  digito1 := 11 - (soma mod 11);
  if digito1 > 9 then
    digito1 := 0;
  soma := 0;
  for i := 1 to 13 do
    soma := soma + StrToInt(Copy(numero, i, 1)) *
      (StrToInt(Copy('6543298765432', i, 1)));
  digito2 := 11 - (soma mod 11);
  if digito2 > 9 then
    digito2 := 0;
  result := (StrToInt(copy(numero, 13, 2)) = (digito1 * 10 + digito2));
end;

function ValidarCPF(const numero: string): boolean;
var
  i, soma, digito1, digito2: SmallInt;
begin
  result := False;
  if length(numero) <> 11 then
    exit;
  soma := 0;
  for i := 1 to 9 do
    soma := soma + StrToInt(Copy(numero, i, 1)) *
      (StrToInt(Copy('987654321', i, 1)) + 1);
  digito1 := 11 - (soma mod 11);
  if digito1 > 9 then
    digito1 := 0;
  soma := digito1 * 2;
  for i := 1 to 9 do
    soma := soma + StrToInt(Copy(numero, i, 1)) *
      (StrToInt(Copy('987654321', i, 1)) + 2);
  digito2 := 11 - (soma mod 11);
  if digito2 > 9 then
    digito2 := 0;
  result := (StrToInt(copy(numero, 10, 2)) = (digito1 * 10 + digito2));
end;

function ValidarMod(const modelo: integer): boolean;
const
  MODELOS = '|1|';
begin
  result := pos('|' + intToStr(modelo) + '|', MODELOS) > 0;
end;

function ValidarMunicipio(const Municipio: integer): boolean;
var
  i, Valor, Soma: integer;
  Codigo, Digito: string;
const
  TAMANHO: smallint = 7;
  PESO = '1212120';
  NAO_VALIDAR = '|2201919|2202251|2201988|2611533|3117836|3152131|4305871|5203939|5203962|';
begin
  result := true;
  if Municipio = 9999999 then
    exit;
  Codigo := IntToStr(Municipio);
  if pos('|' + copy(Codigo, 1, 6), NAO_VALIDAR) > 0 then
  begin
    result := pos('|' + Codigo + '|', NAO_VALIDAR) > 0;
    exit;
  end;
  result := false;
  if length(Codigo) <> TAMANHO then
    exit;
  if not ValidarCodigoUF(StrToInt(copy(Codigo, 1, 2))) then
    exit;
  if copy(Codigo, 3, 4) = '0000' then
    exit;
  soma := 0;
  for i := 1 to TAMANHO do
  begin
    valor := StrToInt(copy(IntToStr(Municipio), i, 1)) * StrToInt(copy(PESO, i, 1));
    if valor > 9 then
      soma := soma + StrToInt(copy(IntToStr(valor), 1, 1)) + StrToInt(copy(IntToStr(valor), 2, 1))
    else
      soma := soma + valor;
  end;
  digito := IntToStr((10 - (soma mod 10)));
  if ((soma mod 10) = 0) then
    digito := '0';
  result := (digito = Codigo[TAMANHO]);
end;

function ValidarModelo(s: string): boolean;
const
  MODELO = '|01|';
begin
  result := pos('|' + s + '|', MODELO) > 0;
end;

function ValidarNumeros(const s: string): boolean;
var
  i: integer;
begin
  result := true;
  for i := 1 to length(s) do
    if pos(s[i], '0123456789') = 0 then
      result := false;
end;

function ValidarUF(const UF: string): boolean;
const
  UFS: string = '.AC.AL.AP.AM.BA.CE.DF.ES.GO.MA.MT.MS.MG.PA.PB.PR.PE.PI.RJ.RN.RS.RO.RR.SC.SP.SE.TO.EX.';
begin
  result := pos('.' + UF + '.', UFS) > 0;
end;

function ValidarIE(IE, UF: string; ApenasDigitos: Boolean = True): boolean;
const
  NUMERO: smallint = 37;
  MASCARAS_: string = '     NNNNNNNNX- NNNNNNNNNNNXY-   NNNNNNNNNNX-NNNNNNNNNNNNNX-      NNNNNNYX-    NNNNNNNNXY-' +
  '    NNNNNNNNNX-  NNNNNNNNXNNY-  NNNNNNNNXNNN-     NNNNNNNXY';
  PESOS_: string = 'GFEDCJIHGFEDCA-FEDCJIHGFEDCAA-GFEDCJIHGFEDAC-AAAAAAAAGFEDCA-AAAAABCDEFGHIA-AAAJIAAHGFEDCA-' +
  'FEDCBJIHGFEDCA-IHGFEDCHGFEDCA-HGFEDCHGFEDCAA-ABCBBCBCBCBCAA-ADCLKJIHGFEDCA-AABDEFGHIKAAAA-AADCKJIHGFEDCA-' +
    'AAAAAJIHGFEDCA-AAAAAIHGFEDCAA-AAAAAJIHGFEDCA-AAAAKJIHGFEDCA-';
  PESO_: string = 'ABAAAAABBABAAAAAAJAAIGAHAADAEALLAFNOQ!A!!!!!CC!A!!!!!!K!!H!!!!!!!!!M!!!!P!';
  ALFA_: string = 'ABCDEFGHIJKLMNOPQRS';
  ROTINAS_: string = 'EE011EEEEEEEEEEEE2EEEEEE0EEEDEDDEEEE0!E!!!!!EE!E!!!!!!E!!E!!!!!!!!!D!!!!E!';
  MODULOS_: string = '99999998999999999899999999997999999990900000890900000090090000000009000090';
  INICIO_: string = '0020000AB000111X2X11X11X2XXX2XXXX2XX2114333XXXX7XCC2X8X56X89X0XXX4XXXX9XX0';
  MASCARA_: string = 'ABAAAAAEEABAAAACABAAFDAEAGADAAHIACAJG';
  FATORES_: string = '0000100000001000000001000011000000000';
  ESTADOS_: string = 'ACACALA1APA2AMBABACEDFESGOGOMAMTMSMGPAPBPRPEPIRJRNRSRORORRSCSPSPSET0TOPERN';
var
  c1, c2, alternativa, inicio, posicao, erros, fator, modulo, soma1, soma2, valor, digito: smallint;
  mascara, inscricao, a1, a2, peso, rotina: string;
begin
  // Copyright: www.cincobytes.net / suporte@cincobytes.net //
  IE := trim(uppercase(IE));
  result := ((IE = 'ISENTO') or (IE = 'EM ANDAMENTO') or ((UF = 'EX') and ((IE = '') or (IE = '00000000000000'))));
  posicao := 0;
  digito := 0;
  while ((not result) and (posicao < NUMERO) and (IE <> '')) do
  begin
    inc(posicao);
    if (UF = 'AP') and (StrToFloat(IE) <= 30170009) then
      UF := 'A1';
    if (UF = 'AP') and (StrToFloat(IE) >= 30190230) then
      UF := 'A2';
    if (copy(ESTADOS_, posicao * 2 - 1, 2)) <> UF then
      continue;
    inscricao := '';
    for C1 := 1 to 30 do
      if pos(copy(IE, C1, 1), '0123456789') <> 0 then
        inscricao := inscricao + copy(IE, C1, 1);
    mascara := copy(MASCARAS_, pos(copy(MASCARA_, posicao, 1), ALFA_) * 15 - 14, 14);
    if length(inscricao) <> length(trim(mascara)) then
     begin
      if length(inscricao) < length(trim(mascara)) then
       begin
         while length(inscricao) < length(trim(mascara)) do
           inscricao := '0'+inscricao;
       end
      else
         continue;
     end;
    inscricao := copy('00000000000000' + inscricao, length(inscricao) + 1, 14);
    erros := 0;
    alternativa := 0;
    while (alternativa < 2) do
    begin
      inc(alternativa);
      inicio := posicao + (alternativa * NUMERO) - NUMERO;
      peso := copy(PESO_, inicio, 1);
      if peso = '!' then
        continue;
      a1 := copy(INICIO_, inicio, 1);
      a2 := copy(copy(inscricao, 15 - length(trim(mascara)), length(trim(mascara))), alternativa, 1);
      if (not ApenasDigitos) and (((pos(a1, 'ABCX') = 0) and (a1 <> a2)) or
        ((pos(a1, 'ABCX') <> 0) and (pos(a2, copy('0123458888-6799999999-0155555555-0123456789',
        (pos(a1, 'ABCX') * 11 - 10), 10)) = 0))) then
        erros := 1;
      soma1 := 0;
      soma2 := 0;
      for C2 := 1 to 14 do
      begin
        valor := StrToInt(copy(inscricao, C2, 1)) *
          (pos(copy(copy(PESOS_, (pos(peso, ALFA_) * 15 - 14), 14), C2, 1), ALFA_) - 1);
        soma1 := soma1 + valor;
        if valor > 9 then
          valor := valor - 9;
        soma2 := soma2 + valor;
      end;
      rotina := copy(ROTINAS_, inicio, 1);
      modulo := StrToInt(copy(MODULOS_, inicio, 1)) + 2;
      fator := StrToInt(copy(FATORES_, posicao, 1));
      if pos(rotina, 'A22') <> 0 then
        soma1 := soma2;
      if pos(rotina, 'B00') <> 0 then
        soma1 := soma1 * 10;
      if pos(rotina, 'C11') <> 0 then
        soma1 := soma1 + (5 + 4 * fator);
      if pos(rotina, 'D00') <> 0 then
        digito := soma1 mod modulo;
      if pos(rotina, 'E12') <> 0 then
        digito := modulo - (soma1 mod modulo);
      if digito = 10 then
        digito := 0;
      if digito = 11 then
        digito := fator;
      if (copy(inscricao, pos(copy('XY', alternativa, 1), mascara), 1) <> IntToStr(digito)) then
        erros := 1;
    end;
    result := (erros = 0);
  end;
end;

function ValidarISUF(const ISUF: string): boolean;
var
  i: integer;
  Soma: integer;
  Digito: integer;
begin
  Result := False;
  if Length(SomenteNumeros(ISUF)) < 9 then
    exit;
  Soma := 0;
  for i := 1 to 9 do
    Soma := Soma + StrToInt(ISUF[i]) * (10 - i);
  Digito := 11 - (Soma mod 11);
  if Digito > 9 then
    Digito := 0;
  Result := StrToInt(ISUF[9]) = Digito;
end;

function SubStrEmSubStr(const SubStr1: string; SubStr2: string): boolean;
var
  s: string;
  i: integer;
begin
  i := 0;
  while (i = 0) and (length(SubStr2) > 0) do
  begin
    SubStr2 := copy(SubStr2, 2, maxInt);
    s := copy(SubStr2, 1, pos('|', SubStr2) - 1);
    SubStr2 := copy(SubStr2, pos('|', SubStr2), maxInt);
    if s <> '' then
      i := i + pos('|' + s, '|' + SubStr1);
  end;
  result := i > 0;
end;

function xml4line(texto: AnsiString): AnsiString;
var
  xml: TStringList;
  i: integer;
begin
  (* Esta fun��o insere um quebra de linha entre os caracteres >< do xml *)
  (* Usada para facilitar os teste de compara��o de arquivos             *)
  Texto := Texto + '<';
  Texto := stringreplace(Texto, #$D#$A, '', [rfReplaceAll]);
  Xml := TStringList.create;
  Result := '';
  while length(texto) > 1 do
  begin
    i := pos('><', Texto);
    Xml.Add(copy(Texto, 1, i));
    Texto := copy(Texto, i + 1, maxInt);
  end;
  Result := Xml.Text;
  Xml.Free;
end;

function RetornarPosEx(const SubStr, S: AnsiString; Offset: Cardinal = 1): Integer;
var
  I, X: Integer;
  Len, LenSubStr: Integer;
begin
  if Offset = 1 then
    Result := Pos(SubStr, S)
  else
  begin
    I := Offset;
    LenSubStr := Length(SubStr);
    Len := Length(S) - LenSubStr + 1;
    while I <= Len do
    begin
      if S[I] = SubStr[1] then
      begin
        X := 1;
        while (X < LenSubStr) and (S[I + X] = SubStr[X + 1]) do
          Inc(X);
        if (X = LenSubStr) then
        begin
          Result := I;
          exit;
        end;
      end;
      Inc(I);
    end;
    Result := 0;
  end;
end;

function RetornarConteudoEntre(const Frase, Inicio, Fim: string): string;
var
  i: integer;
  s: string;
begin
  result := '';
  i := pos(Inicio, Frase);
  if i = 0 then
    exit;
  s := Copy(Frase, i + length(Inicio), maxInt);
  result := Copy(s, 1, pos(Fim, s) - 1);
end;

function DateTimeTodhUTC(DataHora: TDateTime; TZD: string): string;
var
  wAno, wMes, wDia, wHor, wMin, wSeg, wMil: word;
begin
  DecodeDate(DataHora, wAno, wMes, wDia);
  DecodeTime(DataHora, wHor, wMin, wSeg, wMil);
  Result := IntToStrZero(wAno, 4) + '-' +
            IntToStrZero(wMes, 2) + '-' +
            IntToStrZero(wDia, 2) + 'T' +
            IntToStrZero(wHor, 2) + ':' +
            IntToStrZero(wMin, 2) + ':' +
            IntToStrZero(wSeg, 2) +
            TZD;
end;

function GetUTC(UF: string; const dataHora: TDateTime): string;
const
  UTC4 = '.AC.AM.RR.RO.MT.MS.';
  UTC3 = '.AP.PA.MA.PI.TO.GO.CE.RN.PB.PE.AL.SE.BA.MG.ES.RJ.SP.PR.SC.RS.DF.';
var
  HorarioDeVerao: Boolean;
begin
  if (UF = '90') or (UF = '91') or (UF = '') then
     UF := 'DF';  

  HorarioDeVerao := IsHorarioDeVerao(UF, dataHora);

  if AnsiPos('.' + UF + '.', UTC4) > 0 then
  begin
    Result := '-04:00';
    if HorarioDeVerao then
      Result := '-03:00';
  end
  else
  if AnsiPos('.' + UF + '.', UTC3) > 0 then
  begin
    Result := '-03:00';
    if IsHorarioDeVerao(UF, dataHora) then
      Result := '-02:00';
  end;
end;

function IsHorarioDeVerao(const UF: string; const dataHora: TDateTime): Boolean;
const
  UFHV = '.MT.MS.GO.MG.ES.RJ.SP.PR.SC.RS.DF.';
var
  dia: word;
  mes: word;
  ano: word;
  anoInicio: integer;
  anoFim: integer;
begin
  DecodeDate(dataHora, ano, mes, dia);

  { M�s de inicio do hor�rio de ver�o: Outubro;
    M�s de fim do hor�rio de ver�o: Fevereiro;

   * Se a data verificada for de um m�s menor que outubro: Ex: 10/08/2010 (Agosto)
       O inicio do hor�rio de ver�o ser� OUTUBRO do ano anterior (10/2009);
       O fim do hor�rio de ver�o ser� FEVEREIRO do mesmo ano (02/2010);

   * Se a data verificada for de um m�s maior ou igual outubro: Ex: 10/11/2010 (Novembro)
       O inicio do hor�rio de ver�o ser� OUTUBRO do mesmo ano (10/2010);
       O fim do hor�rio de ver�o ser� FEVEREIRO do ano seguinte (02/2011);      }

  anoInicio := ano;
  anoFim := ano;
  if mes < 10 then
    anoInicio := ano - 1
  else
    anoFim := ano + 1;

  Result := False;
  if (GetInicioDoHorarioDeVerao(anoInicio) <= dataHora) and
     (GetFimDoHorarioDeVerao(anoFim) >= dataHora) and
     (AnsiPos(UF, UFHV) > 0) then
    Result := True;
  
end;

function GetInicioDoHorarioDeVerao(const ano: Integer): TDateTime;
begin
  {O inicio do hor�rio de ver�o � no terceiro domingo do mes de outubro}
  Result := GetTerceiroDomingoDoMes(ano, 10);
end;

function GetTerceiroDomingoDoMes(const ano, mes: Integer): TDateTime;
var
  i: integer;
begin
  {O la�o vai at� 7 pois at� o dia 7 tem que ter passado pelo menos um domingo.
   Achado o primeiro domingo, � somado a ele 14 dias para encontrar o terceiro domingo.}
  result := 0;
  for i := 1 to 7 do begin
    if DayOfWeek(EncodeDate(ano, mes, i)) = 1 then
     begin
       result := EncodeDate(ano, mes, i + 14);
       break;
     end;
  end;
end;

function GetFimDoHorarioDeVerao(const ano: Integer): TDateTime;
var
  domingoCarnaval: TDateTime;
  terceiroDomingoFevereiro: TDateTime;
begin
  domingoCarnaval := getDataDoCarnaval(ano) - 2; //Carnaval � na ter�a - 2 = Domingo
  terceiroDomingoFevereiro := getTerceiroDomingoDoMes(ano, 2);
  if domingoCarnaval <> terceiroDomingoFevereiro then
    result := terceiroDomingoFevereiro
  else
    result := IncDay(terceiroDomingoFevereiro, 7);
end;

function GetDataDoCarnaval(const ano: Integer): TDateTime;
var
  pascoa: TDateTime;
begin
  pascoa := getDataDaPascoa(ano);
  result := IncDay(pascoa, -47);
end;

function GetDataDaPascoa(const ano: Integer): TDateTime;
var
  x: integer;
  y: integer;
  a: integer;
  b: integer;
  c: integer;
  d: integer;
  e: integer;
  dia: word;
  mes: word;
begin
  x := 24;
  y := 5;
  a := ano MOD 19;
  b := ano MOD 4;
  c := ano MOD 7;
  d := (19 * a + x) MOD 30;
  e := (2 * b + 4 * c + 6 * d + y) MOD 7;
  if (d + e) > 9 then
   begin
    dia := (d + e - 9);
    mes := 4;
   end
  else
   begin
    dia := (d + e + 22);
    mes := 3;
   end;
  result :=  EncodeDate(ano, mes, dia);
end;

end.
