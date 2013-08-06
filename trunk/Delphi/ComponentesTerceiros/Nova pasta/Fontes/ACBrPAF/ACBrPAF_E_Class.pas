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

unit ACBrPAF_E_Class;

interface

uses SysUtils, StrUtils, Classes, DateUtils, ACBrTXTClass, ACBrPAFRegistros,
     ACBrPAF_E;

type
  /// TPAF_E -
  TPAF_E = class(TACBrTXTClass)
  private
    FRegistroE1: TRegistroE1;       /// FRegistroE1
    FRegistroE2: TRegistroE2List;   /// Lista de FRegistroE2
    FRegistroE9: TRegistroE9;       /// FRegistroE9

    procedure CriaRegistros;
    procedure LiberaRegistros;
  public
    constructor Create; /// Create
    destructor Destroy; override; /// Destroy
    procedure LimpaRegistros;

    function WriteRegistroE1: string;
    function WriteRegistroE2: string;
    function WriteRegistroE9: string;

    property RegistroE1: TRegistroE1 read FRegistroE1 write FRegistroE1;
    property RegistroE2: TRegistroE2List read FRegistroE2 write FRegistroE2;
    property RegistroE9: TRegistroE9 read FRegistroE9 write FRegistroE9;
  end;

implementation

uses ACBrSPEDUtils;

{ TPAF_E }

constructor TPAF_E.Create;
begin
   CriaRegistros;
end;

procedure TPAF_E.CriaRegistros;
begin
  FRegistroE1 := TRegistroE1.Create;
  FRegistroE2 := TRegistroE2List.Create;
  FRegistroE9 := TRegistroE9.Create;

  FRegistroE9.TOT_REG := 0;
end;

destructor TPAF_E.Destroy;
begin
  LiberaRegistros;
  inherited;
end;

procedure TPAF_E.LiberaRegistros;
begin
  FRegistroE1.Free;
  FRegistroE2.Free;
  FRegistroE9.Free;
end;

procedure TPAF_E.LimpaRegistros;
begin
  /// Limpa os Registros
  LiberaRegistros;
  /// Recriar os Registros Limpos
  CriaRegistros;
end;

function TPAF_E.WriteRegistroE1: string;
begin
   if Assigned(FRegistroE1) then
   begin
      with FRegistroE1 do
      begin
        Check(funChecaCNPJ(CNPJ), '(E1) ESTABELECIMENTO: O CNPJ "%s" digitado � inv�lido!', [CNPJ]);
        Check(funChecaIE(IE, UF), '(E1) ESTABELECIMENTO: A Inscri��o Estadual "%s" digitada � inv�lida!', [IE]);

        Result := LFill('E1') +
                  LFill(CNPJ, 14) +
                  RFill(IE, 14) +
                  RFill(IM, 14) +
                  RFill(RAZAOSOCIAL, 50, ifThen(not InclusaoExclusao, ' ', '?')) +
                  RFill(NUM_FAB, 20) +
                  RFill(MF_ADICIONAL, 1) +
                  RFill(TIPO_ECF, 7) +
                  RFill(MARCA_ECF, 20) +
                  RFill(MODELO_ECF, 20,  ifThen(RegistroValido, ' ', '?')) +
                  LFill(DT_EST, 'yyyymmddhhmmss') +
                  sLineBreak;
      end;
   end;
end;

function OrdenarE2(AProd1, AProd2: Pointer): Integer;
begin
  Result := AnsiCompareText(
    TRegistroE2(AProd1).COD_MERC,
    TRegistroE2(AProd2).COD_MERC
  );
end;

function TPAF_E.WriteRegistroE2: string;
var
intFor: integer;
strRegistroE2: string;
begin
  strRegistroE2 := '';

  if Assigned(FRegistroE2) then
  begin
     FRegistroE2.Sort(@OrdenarE2);

     for intFor := 0 to FRegistroE2.Count - 1 do
     begin
        with FRegistroE2.Items[intFor] do
        begin
          Check(funChecaCNPJ(FRegistroE1.CNPJ), '(E2) ESTOQUE: O CNPJ "%s" digitado � inv�lido!', [FRegistroE1.CNPJ]);
          ///
          strRegistroE2 := strRegistroE2 + LFill('E2') +
                                           LFill(FRegistroE1.CNPJ, 14) +
                                           RFill(COD_MERC, 14) +
                                           RFill(DESC_MERC, 50) +
                                           RFill(UN_MED, 6, ifThen(RegistroValido, ' ', '?')) +
                                           LFill(ifThen(QTDE_EST < 0, '-', '+')) +
                                           LFill(ifThen(QTDE_EST < 0, (QTDE_EST * (-1)), QTDE_EST), 9, 3) +
                                           //LFill(DT_EST, 'yyyymmdd') +
                                           sLineBreak;
        end;
        ///
        FRegistroE9.TOT_REG := FRegistroE9.TOT_REG + 1;
     end;
     Result := strRegistroE2;
  end;
end;

function TPAF_E.WriteRegistroE9: string;
begin
   if Assigned(FRegistroE9) then
   begin
      with FRegistroE9 do
      begin
        Check(funChecaCNPJ(FRegistroE1.CNPJ),             '(E9) TOTALIZA��O: O CNPJ "%s" digitado � inv�lido!', [FRegistroE1.CNPJ]);
        Check(funChecaIE(FRegistroE1.IE, FRegistroE1.UF), '(E9) TOTALIZA��O: A Inscri��o Estadual "%s" digitada � inv�lida!', [FRegistroE1.IE]);
        ///
        Result := LFill('E9') +
                  LFill(FRegistroE1.CNPJ, 14) +
                  RFill(FRegistroE1.IE, 14) +
                  LFill(TOT_REG, 6, 0) +
                  #13#10;
      end;
   end;
end;

end.
