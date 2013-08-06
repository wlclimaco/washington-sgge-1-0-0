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

unit ACBrECDBloco_0;

interface

uses
  SysUtils, Classes, Contnrs, DateUtils, ACBrECDBlocos;

type
  /// Registro 0000 - ABERTURA  DO  ARQUIVO  DIGITAL  E  IDENTIFICA��O  DO
  ///                 EMPRES�RIO OU DA SOCIEDADE EMPRES�RIA

  TRegistro0000 = class
  private
    fDT_INI: TDateTime;       /// Data inicial das informa��es contidas no arquivo
    fDT_FIN: TDateTime;       /// Data final das informa��es contidas no arquivo
    fNOME: String;        /// Nome empresarial do empres�rio ou sociedade empres�ria.
    fCNPJ: String;        /// N�mero de inscri��o do empres�rio ou sociedade empres�ria no CNPJ.
    fUF: String;          /// Sigla da unidade da federa��o do empres�rio ou sociedade empres�ria.
    fIE: String;          /// Inscri��o Estadual do empres�rio ou sociedade empres�ria.
    fCOD_MUN: String;     /// C�digo do munic�pio do domic�lio fiscal do empres�rio ou sociedade empres�ria, conforme tabela do IBGE - Instituto Brasileiro de Geografia e Estat�stica.
    fIM: String;          /// Inscri��o Municipal do empres�rio ou sociedade empres�ria.
    fIND_SIT_ESP: String; /// Indicador de situa��o especial (conforme tabela publicada pelo Sped).
  public
    property DT_INI: TDateTime read FDT_INI write FDT_INI;
    property DT_FIN: TDateTime read FDT_FIN write FDT_FIN;
    property NOME: String read fNOME write fNOME;
    property CNPJ: String read fCNPJ write fCNPJ;
    property UF: String read fUF write fUF;
    property IE: String read fIE write fIE;
    property COD_MUN: String read fCOD_MUN write fCOD_MUN;
    property IM: String read fIM write fIM;
    property IND_SIT_ESP: String read fIND_SIT_ESP write fIND_SIT_ESP;
  end;

  /// Registro 0001 - ABERTURA DO BLOCO 0

  TRegistro0001 = class(TOpenBlocos)
  private
  public
  end;

  /// Rregistro 0007 � ESCRITURA��O CONT�BIL DESCENTRALIZADA

  TRegistro0007 = class
  private
    fCOD_ENT_REF: String; /// C�digo da institui��o respons�vel pela administra��o do cadastro (conforme tabela publicada pelo Sped).
    fCOD_INSCR: String;   /// C�digo cadastral do empres�rio ou sociedade empres�ria na institui��o identificada no campo 02.
  public
    property COD_ENT_REF: String read fCOD_ENT_REF write fCOD_ENT_REF;
    property COD_INSCR: String read fCOD_INSCR write fCOD_INSCR;
  end;

  /// Registro 0007 - Lista

  TRegistro0007List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistro0007;
    procedure SetItem(Index: Integer; const Value: TRegistro0007);
  public
    function New: TRegistro0007;
    property Items[Index: Integer]: TRegistro0007 read GetItem write SetItem;
  end;

  /// Rregistro 0020 � OUTRAS INSCRI��ES CADASTRAIS DO EMPRES�RIO OU
  ///                  SOCIEDADE EMPRES�RIA

  TRegistro0020 = class
  private
    fIND_DEC: Integer;        /// Indicador de descentraliza��o: 0 - escritura��o da matriz; 1 - escritura��o da filial.
    fCNPJ: String;        /// N�mero de inscri��o do empres�rio ou sociedade empres�ria no CNPJ da matriz ou da filial.
    fUF: String;          /// Sigla da unidade da federa��o da matriz ou da filial.
    fIE: String;          /// Inscri��o estadual da matriz ou da filial.
    fCOD_MUN: String;     /// C�digo do munic�pio do domic�lio da matriz ou da filial.
    fIM: String;          /// N�mero de Inscri��o Municipal da matriz ou da filial.
    fNIRE: String;        /// N�mero de Identifica��o do Registro de Empresas da matriz ou da filial na Junta Comercial.
  public
    property IND_DEC: Integer read fIND_DEC write fIND_DEC;
    property CNPJ: String read fCNPJ write fCNPJ;
    property UF: String read fUF write fUF;
    property IE: String read fIE write fIE;
    property COD_MUN: String read fCOD_MUN write fCOD_MUN;
    property IM: String read fIM write fIM;
    property NIRE: String read fNIRE write fNIRE;
  end;

  /// Registro 0020 - Lista

  TRegistro0020List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistro0020;
    procedure SetItem(Index: Integer; const Value: TRegistro0020);
  public
    function New: TRegistro0020;
    property Items[Index: Integer]: TRegistro0020 read GetItem write SetItem;
  end;

  /// Rregistro 0150 �  TABELA DE CADASTRO DO PARTICIPANTE

  TRegistro0150 = class
  private
    fCOD_PART: String;    /// C�digo de identifica��o do participante:
    fNOME: String;        /// Nome pessoal ou empresarial:
    fCOD_PAIS: String;    /// C�digo do pa�s do participante:
    fCNPJ: String;        /// CNPJ do participante:
    fCPF: String;         /// CPF do participante na unidade da federa��o do destinat�rio:
    fNIT: String;         /// N�mero de Identifica��o do Trabalhador, Pis, Pasep, SUS.
    fUF: String;          /// Sigla da unidade da federa��o do participante.
    fIE: String;          /// Inscri��o Estadual do participante:
    fIE_ST: String;       /// Inscri��o Estadual do participante na unidade da federa��o do destinat�rio, na condi��o de contribuinte substituto
    fCOD_MUN: integer;        /// C�digo do munic�pio:
    fIM: String;          /// Inscri��o Municipal do participante.
    fSUFRAMA: String;     /// N�mero de inscri��o na Suframa:
  public
    property COD_PART: String read fCOD_PART write fCOD_PART;
    property NOME: String read fNOME write fNOME;
    property COD_PAIS: String read fCOD_PAIS write fCOD_PAIS;
    property CNPJ: String read fCNPJ write fCNPJ;
    property CPF: String read fCPF write fCPF;
    property NIT: String read fNIT write fNIT;
    property UF: String read fUF write fUF;
    property IE: String read fIE write fIE;
    property IE_ST: String read fIE_ST write fIE_ST;
    property COD_MUN: integer read fCOD_MUN write fCOD_MUN;
    property IM: String read fIM write fIM;
    property SUFRAMA: String read fSUFRAMA write fSUFRAMA;
  end;

  /// Registro 0150 - Lista

  TRegistro0150List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistro0150;
    procedure SetItem(Index: Integer; const Value: TRegistro0150);
  public
    function New: TRegistro0150;
    function LocalizaRegistro(pCOD_PART: String): boolean;
    property Items[Index: Integer]: TRegistro0150 read GetItem write SetItem;
  end;

  /// Registro 0180 - IDENTIFICA��O DO RELACIONAMENTO COM O PARTICIPANTE

  TRegistro0180 = class
  private
    fCOD_REL: String;     /// C�digo do relacionamento conforme tabela publicada pelo Sped.
    fDT_INI_REL: TDateTime;   /// Data do in�cio do relacionamento.
    fDT_FIN_REL: TDateTime;   /// Data do t�rmino do relacionamento.
  public
    property COD_REL: String read fCOD_REL write fCOD_REL;
    property DT_INI_REL: TDateTime read fDT_INI_REL write fDT_INI_REL;
    property DT_FIN_REL: TDateTime read fDT_FIN_REL write fDT_FIN_REL;
  end;

  /// Registro 0180 - Lista

  TRegistro0180List = class(TObjectList)
  private
    function GetItem(Index: Integer): TRegistro0180;
    procedure SetItem(Index: Integer; const Value: TRegistro0180);
  public
    function New: TRegistro0180;
    property Items[Index: Integer]: TRegistro0180 read GetItem write SetItem;
  end;

  /// Registro 0990 - ENCERRAMENTO DO BLOCO 0

  TRegistro0990 = class
  private
    fQTD_LIN_0: Integer; /// Quantidade total de linhas do Bloco 0
  public
    property QTD_LIN_0: Integer read fQTD_LIN_0 write fQTD_LIN_0;
  end;

implementation

{ TRegistro0007List }

function TRegistro0007List.GetItem(Index: Integer): TRegistro0007;
begin
  Result := TRegistro0007(Inherited Items[Index]);
end;

function TRegistro0007List.New: TRegistro0007;
begin
  Result := TRegistro0007.Create;
  Add(Result);
end;

procedure TRegistro0007List.SetItem(Index: Integer; const Value: TRegistro0007);
begin
  Put(Index, Value);
end;

{ TRegistro0020List }

function TRegistro0020List.GetItem(Index: Integer): TRegistro0020;
begin
  Result := TRegistro0020(Inherited Items[Index]);
end;

function TRegistro0020List.New: TRegistro0020;
begin
  Result := TRegistro0020.Create;
  Add(Result);
end;

procedure TRegistro0020List.SetItem(Index: Integer; const Value: TRegistro0020);
begin
  Put(Index, Value);
end;

{ TRegistro0150List }

function TRegistro0150List.GetItem(Index: Integer): TRegistro0150;
begin
  Result := TRegistro0150(Inherited Items[Index]);
end;

function TRegistro0150List.LocalizaRegistro(pCOD_PART: String): boolean;
var
intFor: integer;
begin
   Result := false;
   for intFor := 0 to Self.Count - 1 do
   begin
      if Self.Items[intFor].COD_PART = pCOD_PART then
      begin
         Result := true;
         Break;
      end;
   end;
end;

function TRegistro0150List.New: TRegistro0150;
begin
  Result := TRegistro0150.Create;
  Add(Result);
end;

procedure TRegistro0150List.SetItem(Index: Integer; const Value: TRegistro0150);
begin
  Put(Index, Value);
end;

{ TRegistro0180List }

function TRegistro0180List.GetItem(Index: Integer): TRegistro0180;
begin
  Result := TRegistro0180(Inherited Items[Index]);
end;

function TRegistro0180List.New: TRegistro0180;
begin
  Result := TRegistro0180.Create;
  Add(Result);
end;

procedure TRegistro0180List.SetItem(Index: Integer; const Value: TRegistro0180);
begin
  Put(Index, Value);
end;

end.
