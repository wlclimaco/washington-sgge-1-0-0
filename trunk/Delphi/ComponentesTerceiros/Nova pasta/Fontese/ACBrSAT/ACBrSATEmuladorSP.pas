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

unit ACBrSATEmuladorSP ;

interface

uses
  Classes, SysUtils, ACBrSATClass, ACBrSATEmuladorSPstatic;

type

   { TACBrSATEmuladorSP }

   TACBrSATEmuladorSP = class( TACBrSATClass )
   private
     xSAT_AssociarAssinatura : function ( numeroSessao : LongInt;
        codigoDeAtivacao, CNPJvalue, assinaturaCNPJs : PAnsiChar ) : PAnsiChar ;
        stdcall;
     xSAT_AtivarSAT : function ( numeroSessao, subComando : LongInt;
        codigoDeAtivacao, CNPJ: PAnsiChar; cUF : LongInt ) : PAnsiChar ; cdecl;
     xSAT_AtualizarSoftwareSAT : function ( numeroSessao : LongInt;
        codigoDeAtivacao : PAnsiChar ) : PAnsiChar ; cdecl;
     xSAT_BloquearSAT : function ( numeroSessao : LongInt;
        codigoDeAtivacao : PAnsiChar ) : PAnsiChar ; cdecl;
     xSAT_CancelarUltimaVenda : function (numeroSessao : LongInt;
        codigoAtivacao, chave, dadosCancelamento : PAnsiChar ) : PAnsiChar ; cdecl;
     xSAT_ComunicarCertificadoICPBRASIL : function ( numeroSessao : LongInt;
        codigoDeAtivacao, certificado : PAnsiChar ) : PAnsiChar ; cdecl;
     xSAT_ConfigurarInterfaceDeRede : function ( numeroSessao : LongInt;
        codigoDeAtivacao, dadosConfiguracao : PAnsiChar ) : PAnsiChar ; cdecl;
     xSAT_ConsultarNumeroSessao : function (numeroSessao : LongInt;
        codigoDeAtivacao : PAnsiChar; cNumeroDeSessao : LongInt) : PAnsiChar ; cdecl;
     xSAT_ConsultarSAT : function ( numeroSessao : LongInt ) : PAnsiChar ; cdecl;
     xSAT_ConsultarStatusOperacional : function ( numeroSessao : LongInt;
        codigoDeAtivacao : PAnsiChar ) : PAnsiChar ; cdecl;
     xSAT_DesbloquearSAT : function ( numeroSessao : LongInt;
        codigoDeAtivacao : PAnsiChar ) : PAnsiChar ; cdecl;
     xSAT_DesligarSAT : function : PAnsiChar ; cdecl;
     xSAT_EnviarDadosVenda : function ( numeroSessao : LongInt;
        codigoDeAtivacao, dadosVenda : PAnsiChar) : PAnsiChar ; cdecl;
     xSAT_ExtrairLogs : function ( numeroSessao : LongInt;
        codigoDeAtivacao : PAnsiChar ) : PAnsiChar ; cdecl;
     xSAT_TesteFimAFim : function ( numeroSessao : LongInt;
        codigoDeAtivacao, dadosVenda : PAnsiChar) : PAnsiChar ; cdecl;
     xSAT_TrocarCodigoDeAtivacao : function ( numeroSessao : LongInt;
        codigoDeAtivacao : PAnsiChar; opcao : LongInt; novoCodigo,
        confNovoCodigo : PAnsiChar ) : PAnsiChar ; cdecl;

   protected
     procedure LoadDLLFunctions ; override;

   public
     constructor Create( AOwner : TComponent ) ; override;

     function AssociarAssinatura( CNPJvalue, assinaturaCNPJs : String ):
       String ; override;
     function AtivarSAT( subComando : Integer; CNPJ: String; cUF : Integer )
       : String ; override;
     function AtualizarSoftwareSAT : String ; override;
     function BloquearSAT : String ; override;
     function CancelarUltimaVenda( chave, dadosCancelamento : String ) :
       String ; override;
     function ComunicarCertificadoICPBRASIL( certificado : AnsiString ) :
       String ; override;
     function ConfigurarInterfaceDeRede( dadosConfiguracao : AnsiString ) :
       String ; override;
     function ConsultarNumeroSessao( cNumeroDeSessao : Integer) : String ;
       override;
     function ConsultarSAT : String ; override ;
     function ConsultarStatusOperacional : String ; override;
     function DesbloquearSAT : String ; override;
     function DesligarSAT : String ; override;
     function EnviarDadosVenda( dadosVenda : AnsiString ) : String ; override;
     function ExtrairLogs : String ; override;
     function TesteFimAFim( dadosVenda : AnsiString) : String ; override;
     function TrocarCodigoDeAtivacao( opcao : Integer; novoCodigo,
        confNovoCodigo : String ) : String ; override;
   end;

implementation

Uses ACBrUtil;

constructor TACBrSATEmuladorSP.Create(AOwner : TComponent) ;
begin
  inherited Create(AOwner) ;

  fpModeloStr := 'Emulador_SAT_SP' ;
end ;

function TACBrSATEmuladorSP.AssociarAssinatura(CNPJvalue,
  assinaturaCNPJs : String) : String ;
Var
  Resp : PAnsiChar;
begin
  Resp := xSAT_AssociarAssinatura( numeroSessao, PAnsiChar(codigoDeAtivacao),
                                     PAnsiChar(CNPJvalue), PAnsiChar(assinaturaCNPJs) ) ;
  Result := ACBrStr( String( Resp ) );
end ;

function TACBrSATEmuladorSP.AtivarSAT(subComando : Integer ;
  CNPJ : String ; cUF : Integer) : String ;
Var
  Resp : PAnsiChar;
begin
  {Resp := xSAT_AtivarSAT( numeroSessao, subComando,
                          PAnsiChar(codigoDeAtivacao), PAnsiChar(CNPJ), cUF);}
  Resp := ACBrSATEmuladorSPstatic.AtivarSAT( numeroSessao, subComando,
                          PAnsiChar(codigoDeAtivacao), PAnsiChar(CNPJ), cUF);
  Result := ACBrStr( String( Resp ) );
end ;

function TACBrSATEmuladorSP.AtualizarSoftwareSAT : String ;
Var
  Resp : PAnsiChar;
begin
  Resp := xSAT_AtualizarSoftwareSAT( numeroSessao, PAnsiChar(codigoDeAtivacao) ) ;
  Result := ACBrStr( String( Resp ) );
end ;

function TACBrSATEmuladorSP.BloquearSAT : String ;
Var
  Resp : PAnsiChar;
begin
  Resp := xSAT_BloquearSAT( numeroSessao, PAnsiChar(codigoDeAtivacao) ) ;
  Result := ACBrStr( String( Resp ) );
end ;

function TACBrSATEmuladorSP.CancelarUltimaVenda(chave,
  dadosCancelamento : String) : String ;
Var
  Resp : PAnsiChar;
begin
  Resp := xSAT_CancelarUltimaVenda( numeroSessao, PAnsiChar(codigoDeAtivacao),
                                      PAnsiChar(chave), PAnsiChar(dadosCancelamento) ) ;
  Result := ACBrStr( String( Resp ) );
end ;

function TACBrSATEmuladorSP.ComunicarCertificadoICPBRASIL(
  certificado : AnsiString) : String ;
Var
  Resp : PAnsiChar;
begin
  Resp := xSAT_ComunicarCertificadoICPBRASIL( numeroSessao,
                  PAnsiChar(codigoDeAtivacao), PAnsiChar(certificado) ) ;
  Result := ACBrStr( String( Resp ) );
end ;

function TACBrSATEmuladorSP.ConfigurarInterfaceDeRede(
  dadosConfiguracao : AnsiString) : String ;
Var
  Resp : PAnsiChar;
begin
  Resp := xSAT_ConfigurarInterfaceDeRede( numeroSessao,
                 PAnsiChar(codigoDeAtivacao), PAnsiChar(dadosConfiguracao) ) ;
  Result := ACBrStr( String( Resp ) );
end ;

function TACBrSATEmuladorSP.ConsultarNumeroSessao(cNumeroDeSessao : Integer
  ) : String ;
Var
  Resp : PAnsiChar;
begin
  Resp := xSAT_ConsultarNumeroSessao( numeroSessao, PAnsiChar(codigoDeAtivacao),
                                        cNumeroDeSessao) ;
  Result := ACBrStr( String( Resp ) );
end ;

function TACBrSATEmuladorSP.ConsultarSAT : String ;
Var
  Resp : PAnsiChar;
begin
  Resp := xSAT_ConsultarSAT( numeroSessao ) ;
  Result := ACBrStr( String( Resp ) );
end ;

function TACBrSATEmuladorSP.ConsultarStatusOperacional : String ;
Var
  Resp : PAnsiChar;
begin
  //Resp := xSAT_ConsultarStatusOperacional( numeroSessao, PAnsiChar(codigoDeAtivacao) ) ;
  Resp := ACBrSATEmuladorSPstatic.ConsultarStatusOperacional( numeroSessao, PAnsiChar(codigoDeAtivacao) ) ;

  Result := ACBrStr( String( Resp ) );
end ;

function TACBrSATEmuladorSP.DesbloquearSAT : String ;
Var
  Resp : PAnsiChar;
begin
  Resp := xSAT_DesbloquearSAT( numeroSessao, PAnsiChar(codigoDeAtivacao) );
  Result := ACBrStr( String( Resp ) );
end ;

function TACBrSATEmuladorSP.DesligarSAT : String ;
Var
  Resp : PAnsiChar;
begin
  Resp := xSAT_DesligarSAT ;
  Result := ACBrStr( String( Resp ) );
end ;

function TACBrSATEmuladorSP.EnviarDadosVenda(dadosVenda : AnsiString) : String ;
Var
  Resp : PAnsiChar;
begin
  {Resp := xSAT_EnviarDadosVenda( numeroSessao, PAnsiChar(codigoDeAtivacao),
                                   PAnsiChar(dadosVenda) ) ;}
  Resp := ACBrSATEmuladorSPstatic.EnviarDadosVenda( numeroSessao, PAnsiChar(codigoDeAtivacao),
                                   PAnsiChar(dadosVenda) ) ;
  Result := ACBrStr( String( Resp ) );
end ;

function TACBrSATEmuladorSP.ExtrairLogs : String ;
Var
  Resp : PAnsiChar;
begin
  Resp := xSAT_ExtrairLogs( numeroSessao, PAnsiChar(codigoDeAtivacao) ) ;
  Result := ACBrStr( String( Resp ) );
end ;

function TACBrSATEmuladorSP.TesteFimAFim(dadosVenda : AnsiString) : String ;
Var
  Resp : PAnsiChar;
begin
  { Resp := xSAT_TesteFimAFim( numeroSessao, PAnsiChar(codigoDeAtivacao),
                               PAnsiChar(dadosVenda) ); }
  Resp := ACBrSATEmuladorSPstatic.TesteFimAFim( numeroSessao, PAnsiChar(codigoDeAtivacao),
                               PAnsiChar(dadosVenda) );
  Result := ACBrStr( String( Resp ) );
end ;

function TACBrSATEmuladorSP.TrocarCodigoDeAtivacao(opcao : Integer ;
  novoCodigo, confNovoCodigo : String) : String ;
Var
  Resp : PAnsiChar;
begin
  Resp := xSAT_TrocarCodigoDeAtivacao( numeroSessao, PAnsiChar(codigoDeAtivacao),
                 opcao, PAnsiChar(novoCodigo), PAnsiChar(confNovoCodigo) ) ;
  Result := ACBrStr( String( Resp ) );
end ;

procedure TACBrSATEmuladorSP.LoadDLLFunctions;
begin
  FunctionDetectLibSAT( 'AssociarAssinatura', @xSAT_AssociarAssinatura );
  FunctionDetectLibSAT( 'AtivarSAT', @xSAT_AtivarSAT );
  FunctionDetectLibSAT( 'AtualizarSoftwareSAT', @xSAT_AtualizarSoftwareSAT) ;
  FunctionDetectLibSAT( 'BloquearSAT', @xSAT_BloquearSAT);
  FunctionDetectLibSAT( 'CancelarUltimaVenda', @xSAT_CancelarUltimaVenda);
  FunctionDetectLibSAT( 'ComunicarCertificadoICPBRASIL', @xSAT_ComunicarCertificadoICPBRASIL);
  FunctionDetectLibSAT( 'ConfigurarInterfaceDeRede', @xSAT_ConfigurarInterfaceDeRede);
  FunctionDetectLibSAT( 'ConsultarNumeroSessao', @xSAT_ConsultarNumeroSessao);
  FunctionDetectLibSAT( 'ConsultarSAT', @xSAT_ConsultarSAT);
  FunctionDetectLibSAT( 'DesbloquearSAT', @xSAT_DesbloquearSAT);
  FunctionDetectLibSAT( 'DesligarSAT', @xSAT_DesligarSAT);
  FunctionDetectLibSAT( 'ExtrairLogs', @xSAT_ExtrairLogs);
  FunctionDetectLibSAT( 'TesteFimAFim', @xSAT_TesteFimAFim) ;
  FunctionDetectLibSAT( 'TrocarCodigoDeAtivacao', @xSAT_TrocarCodigoDeAtivacao);
end;

end.

