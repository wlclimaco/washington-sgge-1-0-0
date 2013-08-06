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

unit ACBrFContBloco_0;

interface

uses
  SysUtils, Classes, Contnrs, DateUtils, ACBrFContBlocos;

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
    fIND_SIT_INI_PER: String; //Indicador do in�cio do per�odo conforme Tabela de Indicador do in�cio do per�odo.
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
    property IND_SIT_INI_PER: String read fIND_SIT_INI_PER write fIND_SIT_INI_PER;
  end;

implementation

{ TRegistro0007List }

end.
