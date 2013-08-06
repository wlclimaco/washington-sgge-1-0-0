{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Andr� Ferreira de Moraes               }
{                                       Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:  Cirilo Veloso  -  www.veloso.adm.br            }
{                               Aroldo Zanella -  www.forumweb.com.b           }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }
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

{******************************************************************************
|* Historico
|*
|* 06/01/2005: Andr� Ferreira de Moraes
|*  - Primeira Versao ACBrCMC7
|* 29/05/2006: Andr� Ferreira de Moraes
|*  - Corrigido Bug no calculo do digito Verificador 2
|* 29/05/2006: Diogo Augusto Pereira
|*  - Calculo de CMC7 compatiblizado com o Banrisul 
******************************************************************************}
unit ACBrCMC7;

interface

uses
  SysUtils, Classes, ACBrBase, ACBrUtil;

type
  TACBrCMC7 = class(TACBrComponent)
  private
    { Private declarations }
    FCMC7        : AnsiString;
    FCMC7Bloco1  : String;
    FCMC7Bloco3  : String;
    FCMC7Bloco2  : String;
    FBanco       : String;
    FAgencia     : String;
    FDvCCT       : AnsiChar;
    FComp        : String;
    FNumero      : String;
    FConta       : String;
    FTipificacao : AnsiChar;
    FDvBcoAg     : AnsiChar;
    FDvCMC7      : AnsiChar;
    FC1          : Integer;
    FC2          : Integer;
    FC3          : Integer;
    function DigitosaIgnorarConta(Banco: String) : integer;
    procedure SetCMC7(Banda: AnsiString);
    procedure ZeraCampos ;
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    Destructor Destroy  ; override ;
    Procedure MontaCMC7(pBanco,pAgencia,pConta,pNrCheque,
       pCamaraCompesacao : String; pTipificacao : String = '5') ; overload ;
    Procedure MontaCMC7(Bloco1, Bloco2, Bloco3 : String) ; overload ;
  published
    { Published declarations }
    property CMC7        : AnsiString read FCMC7  write SetCMC7 stored false ;
    property CMC7Bloco1  : String read FCMC7Bloco1  stored false ;
    property CMC7Bloco2  : String read FCMC7Bloco2  stored false ;
    property CMC7Bloco3  : String read FCMC7Bloco3  stored false ;
    property Banco       : String read FBanco       stored false ;
    property Agencia     : String read FAgencia     stored false ;
    property DvCCT       : AnsiChar read FDvCCT     stored false ; { D�g.Verif. Comp+Cheque+Tipifica��o }
    property Comp        : String read FComp        stored false ;
    property Numero      : String read FNumero      stored false ;
    property Conta       : String read FConta       stored false ;
    property Tipificacao : AnsiChar read FTipificacao stored false ; { Tipifica��o(5-Comum 6-Banc�rio 7-Sal�rio 8-Administr. 9-CPMF) }
    property DvBcoAg     : AnsiChar read FDvBcoAg   stored false ; { D�gito verificador do Banco+Ag�ncia: }
    property DvCMC7      : AnsiChar read FDvCMC7    stored false ;
    property C1          : Integer  read FC1        stored false ;
    property C2          : Integer  read FC2        stored false ;
    property C3          : Integer  read FC3        stored false ;
  end;

function ValidaCMC7(CMC7: String) : Boolean;
function FormataCMC7(const ACMC7: String): String;
function CalculaC1(Chave: String): Integer;
function CalculaC2(Chave: String): Integer;
function CalculaC3(Chave: String): Integer;

implementation

function FormataCMC7(const ACMC7: String): String;
var
  CMC7: String;
begin
  CMC7 := ACBrUtil.OnlyNumber(ACMC7);

  if Length(CMC7) <> 30 then
    raise Exception.Create('C�digo CMC7 Inv�lido!');

  Result := '<' +
    Copy(CMC7, 1, 8)   + '<' +
    Copy(CMC7, 9, 10)  + '>' +
    Copy(CMC7, 19, 12) + ':';
end;

function CalculaC1(Chave: String): Integer;
var
  I, Soma, Mult: integer;
begin
  if Length(Chave) <> 10 then
    raise Exception.Create('Par�metros inv�lidos para o c�lculo do C1.');
  Mult := 8;
  Soma := 0;
  for I := 1 to Length(Chave) do
  begin
    Soma := Soma + (StrToInt(Chave[I]) * Mult);
    Inc(Mult);
    if Mult = 10 then
      Mult := 2;
  end;
  Result := Soma mod 11;
end;

function CalculaC2(Chave: String): Integer;
var
  I, Soma, Mult: integer;
begin
  if Length(Chave) <> 10 then
    raise Exception.Create('Par�metros inv�lidos para o c�lculo do C2.');
  Mult := 11;
  Soma := 0;
  for I := 1 to Length(Chave) do
  begin
    Soma := Soma + (StrToInt(Chave[I]) * Mult);
    Dec(Mult);
  end;
  Soma := Soma mod 11;
  if (Soma = 0) or (Soma = 1) then
    Result := 0
  else
    Result := 11 - Soma;
end;

function CalculaC3(Chave: String): Integer;
var
  I, Soma, Mult: integer;
begin
  if Length(Chave) <> 6 then
    raise Exception.Create('Par�metros inv�lidos para o c�lculo do C3.');
  Mult := 7;
  Soma := 0;
  for I := 1 to Length(Chave) do
  begin
    Soma := Soma + (StrToInt(Chave[I]) * Mult);
    Dec(Mult);
  end;
  Soma := Soma mod 11;
  if (Soma = 0) or (Soma = 1) then
    Result := 0
  else
    Result := 11 - Soma;
end;

function CalcDigitoCMC7(Documento : String; Inicial, Final : integer) : String;
var
  I: Integer;
  vVal1, vVal2, vVal3, vSoma, vPeso : Real;
begin
  vSoma := 0;
  for I := 1 to Length(Documento) do
  begin
    if Odd(I) then
       vPeso := Inicial
    else
       vPeso := Final;

    if Documento[I] in ['0'..'9'] then
    begin
       vVal1 := StrToFloat(Documento[I])*vPeso;
       if (vVal1 > 9) then
          vVal2 := StrToFloat(copy(formatFloat('0',vVal1),1,1)) + StrToFloat(copy(formatFloat('0',vVal1),length(formatFloat('0',vVal1)),1))
       else
          vVal2 := vVal1;
       vSoma := vSoma+vVal2;
    end;
  end;
  vVal3 := round((10-(vSoma/10))*100)/100;

  Result := copy(formatFloat('0.000',frac(vVal3)),3,1);
end;

///Dica retirada do site www.delphi.eti.br e otimizada
function ValidaCMC7(CMC7: String): Boolean;
var Dv : string;
begin
  // contador: 123 4567 8 901 234567 8  9 0123456789 0
  // conteudo: 745 0030 2 018 000379 5  7 0030079144 9
  //           --- ---- - --- ------ -  - ---------- -
  //           |   |    |  |  |      |  | |          |
  //           |   |    |  |  |      |  | |          ---> digito verificador 3
  //           |   |    |  |  |      |  | -------------> conta corrente
  //           |   |    |  |  |      |  ---------------> digito verificador 1
  //           |   |    |  |  |      ------------------> Tipifica��o ( 5 padrao/normal, 8 ch tribut�rio, 9 administrativo )
  //           |   |    |  |  -------------------------> cheque
  //           |   |    |  ----------------------------> compe ( camara de compensa��o )
  //           |   |    -------------------------------> digito verificador 2
  //           |   ------------------------------------> ag�ncia
  //           ----------------------------------------> banco

  CMC7   := OnlyNumber(CMC7) ;  { Retirando marcadores }
  Result := (Length(CMC7) = 30) ;

  // calculo do digito (2)
  if Result then
  begin
     Dv     := CalcDigitoCMC7(copy(CMC7,9,3)+copy(CMC7,12,6)+copy(CMC7,18,1),1,2);
     Result := (Dv = copy(CMC7,8,1)) ;
  end ;

  // calculo do digito (1)
  if Result then
  begin
    Dv     := CalcDigitoCMC7(copy(CMC7,1,7),2,1);
    Result := (Dv = copy(CMC7,19,1)) ;
  end;

  // calculo do digito (3)
  if Result then
  begin
    Dv     := CalcDigitoCMC7(copy(CMC7,20,10),1,2);
    Result := (Dv = copy(CMC7,30,1)) ;
  end;
end;


constructor TACBrCMC7.Create(AOwner: TComponent);
begin
 inherited Create( AOwner );

 ZeraCampos ;
end;

procedure TACBrCMC7.ZeraCampos;
begin
  FCMC7       := '';
  FCMC7Bloco1 := '';
  FCMC7Bloco2 := '';
  FCMC7Bloco3 := '';
  FBanco      := '';
  FAgencia    := '';
  FComp       := '';
  FNumero     := '';
  FConta      := '';
  FDvCCT      := ' ';
  FTipificacao:= ' ';
  FDvBcoAg    := ' ';
  FDvCMC7     := ' ';
  FC1         := 0;
  FC2         := 0;
  FC3         := 0;
end;

destructor TACBrCMC7.Destroy;
begin
  { Nada a fazer aqui por enquanto :) }
  
  inherited Destroy ;
end;

function TACBrCMC7.DigitosaIgnorarConta(Banco: String) : integer;
var
  CodBanco : Integer;
begin
  CodBanco := StrToIntDef(Banco,0);
  case CodBanco of
      1: Result := 2;    // 001 - Banco do Brasil
     33: Result := 4;    // 033 - Santander / Banespa
     41: Result := 0;    // 041 - Banrisul Obs: Este banco utiliza todo o campo para o n�mero da conta
    104: Result := 0;    // 104 - CEF. Utiliza apenas 7, mas os 3 primeiros s�o necess�rios para calcular o dv
//  237: Result := 3;    // 237 - Bradesco
    341: Result := 4;    // 341 - Itau
    389: Result := 1;    // 389 - Mercantil
    399: Result := 4;    // 399 - HSBC
//  409: Result := 3;    // 409 - Unibanco
    479: Result := 2;    // 479 - Bank of Boston
  else
    Result := 3;
  end;
end;

procedure TACBrCMC7.SetCMC7(Banda: AnsiString);
Const  vDigitos = AnsiString('<99999999<9999999999>999999999999:') ;
var
  Ignorar : Integer;
  I : Integer ;
begin
  ZeraCampos ;

  Banda := Trim(Banda) ;
  if Banda = '' then
    exit ;

  if Length( Banda ) <> 34 then
     raise Exception.Create(ACBrStr('Banda CMC7 deve ter 34 caracteres'));

// 1234567890123456789012345678901234
// <00100049<0030000061>900000000109:

  for I := 1 to 33 do // Desprezando �ltimo caracter
  begin
    if vDigitos[I] = '9' then
     begin
       if not CharIsNum(Banda[I]) then
           raise Exception.CreateFmt(ACBrStr('Caracter da posi��o %d da Banda deve ser num�rico'),[I]);
     end
    else
       if vDigitos[I] <> Banda[I] then
          raise Exception.CreateFmt(ACBrStr('Caracter da posi��o %d da Banda deve ser %s'),[I,vDigitos[I]]);

  end ;

  if not ValidaCMC7(Banda) then
     raise Exception.Create(ACBrStr('CMC7 Inv�lido'));

  try
  // '<' + Banco(3) + Agencia(4) + DV2(1) + '<' + CamaraCompesacao(3) +
  //    NrCheque(6) + Tipificacao(1) + '>' + DV1(1) + Conta(10) + DV3(1) + ':'
  // 1234567890123456789012345678901234
  // <00100049<0030000065>900000000109:

     FCMC7Bloco1 := copy(Banda,2 , 8) ;
     FCMC7Bloco2 := copy(Banda,11,10) ;
     FCMC7Bloco3 := copy(Banda,22,12) ;

     FBanco      := Copy(Banda,2 ,3);
     FAgencia    := Copy(Banda,5 ,4);
     FDvCCT      := Copy(Banda,9 ,1)[1];
     FComp       := Copy(Banda,11,3);
     FNumero     := Copy(Banda,14,6);
     FTipificacao:= Copy(Banda,20,1)[1];
     FDvBcoAg    := Copy(Banda,22,1)[1];

     Ignorar     := DigitosaIgnorarConta(FBanco);
     FConta      := Copy(Banda,23+Ignorar,10-Ignorar);

     FDvCMC7     := Copy(Banda,33,1)[1];
     FC1         := CalculaC1(FComp + FBanco + FAgencia);
     FC2         := CalculaC2(padR(FConta, 10, '0'));
     FC3         := CalculaC3(padR(FNumero, 6, '0'));
     FCMC7       := Banda
  except
     ZeraCampos ;
  end ;
end;


Procedure TACBrCMC7.MontaCMC7(pBanco,pAgencia,pConta,pNrCheque,pCamaraCompesacao,pTipificacao : String) ;
// Dica retirada do site http://www.ramosdainformatica.com.br/art_recentes01.php?CDA=297 e ajustado conforme necessidade
var
  vDv1, vDv2, vDv3 : string;
  Tip : Integer ;
begin
  // zeros a esquerda do banco
  pBanco := Poem_Zeros(pBanco,3);
  // zeros a esquerda da agencia
  pAgencia := Poem_Zeros(pAgencia,4);
  // zeros a esquerda da conta
  pConta := Poem_Zeros(pConta,10);
  // zeros a esquerda do NrCheque
  pNrCheque := Poem_Zeros(pNrCheque,6);
  // zeros a esquerda do CamaraCompesacao
  pCamaraCompesacao := Poem_Zeros(pCamaraCompesacao,3);
  Tip := StrToIntDef(pTipificacao,0) ;
  if (Tip < 5) or (Tip > 9) then
     raise Exception.Create(ACBrStr('Campo Tipifica��o deve estar na faixa 5..9')) ;

  // calculo do digito (2)
  vDv2 := CalcDigitoCMC7(pCamaraCompesacao+pNrCheque+pTipificacao,1,2);
   // calculo do digito (1)
  vDv1 := CalcDigitoCMC7(pBanco+pAgencia,2,1);
  // calculo do digito (3)
  vDv3 := CalcDigitoCMC7(pConta,1,2);

  CMC7 := '<'+pBanco+pAgencia+vDV2+'<'+pCamaraCompesacao+pNrCheque+pTipificacao+'>'+vDV1+pConta+vDV3+':';
  // '<' + Banco(3) + Agencia(4) + DV2(1) + '<' + CamaraCompesacao(3) +
  //    NrCheque(6) + Tipificacao(1) + '>' + DV1(1) + Conta(10) + DV3(1) + ':'
  // 1234567890123456789012345678901234
  // <00100049<0030000065>900000000109:
end;

procedure TACBrCMC7.MontaCMC7(Bloco1, Bloco2, Bloco3: String);
begin
  Bloco1 := Poem_Zeros(Bloco1, 8) ;
  Bloco2 := Poem_Zeros(Bloco2,10) ;
  Bloco3 := Poem_Zeros(Bloco3,12) ;
  
  CMC7 := '<'+Bloco1+'<'+Bloco2+'>'+Bloco3+':';
end;

end.
