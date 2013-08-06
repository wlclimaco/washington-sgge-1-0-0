{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009   Isaque Pinheiro                      }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
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
|* 10/04/2009: Isaque Pinheiro
|*  - Cria��o e distribui��o da Primeira Versao
*******************************************************************************}

{$I ACBr.inc}

unit ACBrPAF_P_Class;

interface

uses SysUtils, StrUtils, Classes, DateUtils, ACBrTXTClass, ACBrPAFRegistros,
     ACBrPAF_P;

type
  /// TPAF_P -
  TPAF_P = class(TACBrTXTClass)
  private
    FRegistroP1: TRegistroP1;       /// FRegistroP1
    FRegistroP2: TRegistroP2List;   /// Lista de FRegistroP2
    FRegistroP9: TRegistroP9;       /// FRegistroP9

    procedure CriaRegistros;
    procedure LiberaRegistros;
  public
    constructor Create;/// Create
    destructor Destroy; override; /// Destroy
    procedure LimpaRegistros;

    function WriteRegistroP1: string;
    function WriteRegistroP2: string;
    function WriteRegistroP9: string;

    property RegistroP1: TRegistroP1 read FRegistroP1 write FRegistroP1;
    property RegistroP2: TRegistroP2List read FRegistroP2 write FRegistroP2;
    property RegistroP9: TRegistroP9 read FRegistroP9 write FRegistroP9;
  end;

implementation

uses ACBrSPEDUtils;

{ TPAF_P }

constructor TPAF_P.Create;
begin
   CriaRegistros;
end;

procedure TPAF_P.CriaRegistros;
begin
  FRegistroP1 := TRegistroP1.Create;
  FRegistroP2 := TRegistroP2List.Create;
  FRegistroP9 := TRegistroP9.Create;

  FRegistroP9.TOT_REG := 0;
end;

destructor TPAF_P.Destroy;
begin
  LiberaRegistros;
  inherited;
end;

procedure TPAF_P.LiberaRegistros;
begin
  FRegistroP1.Free;
  FRegistroP2.Free;
  FRegistroP9.Free;
end;

procedure TPAF_P.LimpaRegistros;
begin
  /// Limpa os Registros
  LiberaRegistros;
  /// Recriar os Registros Limpos
  CriaRegistros;
end;

function TPAF_P.WriteRegistroP1: string;
begin
   if Assigned(FRegistroP1) then
   begin
      with FRegistroP1 do
      begin
        Check(funChecaCNPJ(CNPJ), '(P1) ESTABELECIMENTO: O CNPJ "%s" digitado � inv�lido!', [CNPJ]);
        Check(funChecaIE(IE, UF), '(P1) ESTABELECIMENTO: A Inscri��o Estadual "%s" digitada � inv�lida!', [IE]);
        ///
        Result := LFill('P1') +
                  LFill(CNPJ, 14) +
                  RFill(IE, 14) +
                  RFill(IM, 14) +
                  RFill(RAZAOSOCIAL, 50 ,ifThen(not InclusaoExclusao, ' ', '?')) +
                  sLineBreak;
      end;
   end;
end;

function OrdenarP2(AProd1, AProd2: Pointer): Integer;
begin
  Result := AnsiCompareText(
    TRegistroP2(AProd1).COD_MERC_SERV,
    TRegistroP2(AProd2).COD_MERC_SERV
  );
end;

function TPAF_P.WriteRegistroP2: string;
var
intFor: integer;
strRegistroP2: string;
begin
  strRegistroP2 := '';

  if Assigned(FRegistroP2) then
  begin
     FRegistroP2.Sort(@OrdenarP2);

     for intFor := 0 to FRegistroP2.Count - 1 do
     begin
        with FRegistroP2.Items[intFor] do
        begin
          Check(funChecaCNPJ(FRegistroP1.CNPJ), '(P2) ESTOQUE: O CNPJ "%s" digitado � inv�lido!', [FRegistroP1.CNPJ]);
          ///
          strRegistroP2 := strRegistroP2 + LFill('P2') +
                                           LFill(FRegistroP1.CNPJ, 14) +
                                           RFill(COD_MERC_SERV, 14) +
                                           RFill(DESC_MERC_SERV, 50) +
                                           RFill(UN_MED, 6, ifThen(RegistroValido, ' ', '?')) +
                                           RFill(IAT, 1) +
                                           RFill(IPPT, 1) +
                                           RFill(ST, 1) +
                                           LFill(ALIQ, 4) +
                                           LFill(VL_UNIT, 12, 2) +
                                           sLineBreak;
        end;
        ///
        FRegistroP9.TOT_REG := FRegistroP9.TOT_REG + 1;
     end;
     Result := strRegistroP2;
  end;
end;

function TPAF_P.WriteRegistroP9: string;
begin
   if Assigned(FRegistroP9) then
   begin
      with FRegistroP9 do
      begin
        Check(funChecaCNPJ(FRegistroP1.CNPJ),            '(P9) TOTALIZA��O: O CNPJ "%s" digitado � inv�lido!', [FRegistroP1.CNPJ]);
        Check(funChecaIE(FRegistroP1.IE, FRegistroP1.UF), '(P9) TOTALIZA��O: A Inscri��o Estadual "%s" digitada � inv�lida!', [FRegistroP1.IE]);
        ///
        Result := LFill('P9') +
                  LFill(FRegistroP1.CNPJ, 14) +
                  RFill(FRegistroP1.IE, 14) +
                  LFill(TOT_REG, 6, 0) +
                  sLineBreak;
      end;
   end;
end;

end.
