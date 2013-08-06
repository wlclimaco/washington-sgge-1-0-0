{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2013 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:  Andr� Ferreira Moraes                          }
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

{$I ACBr.inc}

unit ACBrSATEmuladorSPstatic ;

interface

uses
  Classes, SysUtils, ACBrSATClass ;

function AssociarAssinatura( numeroSessao : Longint; codigoDeAtivacao: PAnsiChar; CNPJvalue : PAnsiChar; assinaturaCNPJs : PAnsiChar ) : PAnsiChar ; cdecl; External cLIBSAT;
function AtivarSAT( numeroSessao: Longint; subComando : Longint; codigoDeAtivacao: PAnsiChar; CNPJ: PAnsiChar; cUF : Longint ) : PAnsiChar ; cdecl; External cLIBSAT;
function AtualizarSoftwareSAT( numeroSessao : Longint; codigoDeAtivacao : PAnsiChar ) : PAnsiChar ; cdecl; External cLIBSAT;
function BloquearSAT( numeroSessao : Longint; codigoDeAtivacao : PAnsiChar ) : PAnsiChar ; cdecl; External cLIBSAT;
function CancelarUltimaVenda(numeroSessao : Longint; codigoAtivacao: PAnsiChar; chave: PAnsiChar; dadosCancelamento : PAnsiChar) : PAnsiChar ; cdecl;  External cLIBSAT;
function ComunicarCertificadoICPBRASIL( numeroSessao : Longint; codigoDeAtivacao : PAnsiChar; certificado : PAnsiChar ) : PAnsiChar ; cdecl; External cLIBSAT;
function ConfigurarInterfaceDeRede( numeroSessao : Longint; codigoDeAtivacao : PAnsiChar; dadosConfiguracao : PAnsiChar ) : PAnsiChar ; cdecl; External cLIBSAT;
function ConsultarNumeroSessao(numeroSessao : Longint; cNumeroDeSessao : Longint) : PAnsiChar ; cdecl;  External cLIBSAT;
function ConsultarSAT( numeroSessao : Longint ) : PAnsiChar ; cdecl; External cLIBSAT;
function ConsultarStatusOperacional( numeroSessao : Longint; codigoDeAtivacao : PAnsiChar ) : PAnsiChar ; cdecl; External cLIBSAT;
function DesbloquearSAT( numeroSessao : Integer; codigoDeAtivacao : PAnsiChar ) : PAnsiChar ; cdecl; External cLIBSAT;
function DesligarSAT : PAnsiChar ; cdecl; External cLIBSAT;
function EnviarDadosVenda(numeroSessao : Longint; codigoDeAtivacao: PAnsiChar; dadosVenda : PAnsiChar) : PAnsiChar ; cdecl; External cLIBSAT;
function ExtrairLogs( numeroSessao : Longint; codigoDeAtivacao : PAnsiChar ) : PAnsiChar ; cdecl; External cLIBSAT;
function TesteFimAFim(numeroSessao : Longint; codigoDeAtivacao: PAnsiChar; dadosVenda : PAnsiChar) : PAnsiChar ; cdecl; External cLIBSAT;
function TrocarCodigoDeAtivacao( numeroSessao : Longint; codigoDeAtivacao : PAnsiChar; opcao : Longint; novoCodigo : PAnsiChar; confNovoCodigo : PAnsiChar ) : PAnsiChar ; cdecl; External cLIBSAT;

implementation

end.

