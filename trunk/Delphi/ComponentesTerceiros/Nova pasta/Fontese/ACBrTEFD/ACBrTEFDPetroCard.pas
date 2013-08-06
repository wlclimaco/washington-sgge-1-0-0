{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para interação com equipa- }
{ mentos de Automação Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
{                                                                              }
{  Você pode obter a última versão desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }
{                                                                              }
{  Esta biblioteca é software livre; você pode redistribuí-la e/ou modificá-la }
{ sob os termos da Licença Pública Geral Menor do GNU conforme publicada pela  }
{ Free Software Foundation; tanto a versão 2.1 da Licença, ou (a seu critério) }
{ qualquer versão posterior.                                                   }
{                                                                              }
{  Esta biblioteca é distribuída na expectativa de que seja útil, porém, SEM   }
{ NENHUMA GARANTIA; nem mesmo a garantia implícita de COMERCIABILIDADE OU      }
{ ADEQUAÇÃO A UMA FINALIDADE ESPECÍFICA. Consulte a Licença Pública Geral Menor}
{ do GNU para mais detalhes. (Arquivo LICENÇA.TXT ou LICENSE.TXT)              }
{                                                                              }
{  Você deve ter recebido uma cópia da Licença Pública Geral Menor do GNU junto}
{ com esta biblioteca; se não, escreva para a Free Software Foundation, Inc.,  }
{ no endereço 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          }
{ Você também pode obter uma copia da licença em:                              }
{ http://www.opensource.org/licenses/lgpl-license.php                          }
{                                                                              }
{ Daniel Simões de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Praça Anita Costa, 34 - Tatuí - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 21/11/2009: Daniel Simoes de Almeida
|*  - Primeira Versao: Criaçao e Distribuiçao da Primeira Versao
******************************************************************************}

{$I ACBr.inc}

unit ACBrTEFDPetroCard;

interface

uses
  Classes, SysUtils, ACBrTEFDClass;

const
  CACBrTEFDPetroCard_ArqTemp   = 'C:\CardTech\req\intpos.tmp' ;
  CACBrTEFDPetroCard_ArqReq    = 'C:\CardTech\req\intpos.001' ;
  CACBrTEFDPetroCard_ArqResp   = 'C:\CardTech\resp\intpos.001' ;
  CACBrTEFDPetroCard_ArqSTS    = 'C:\CardTech\resp\intpos.sts' ;
  CACBrTEFDPetroCard_GPExeName = 'C:\CardTech\sac.exe' ;


type
   { TACBrTEFDGoodCard }

   TACBrTEFDPetroCard = class( TACBrTEFDClassTXT )
   private
   public
     constructor Create( AOwner : TComponent ) ; override ;
   end;

implementation

Uses ACBrUtil, dateutils;

{ TACBrTEFDClass }

constructor TACBrTEFDPetroCard.Create(AOwner : TComponent);
begin
  inherited Create(AOwner);

  ArqReq    := CACBrTEFDPetroCard_ArqReq ;
  ArqResp   := CACBrTEFDPetroCard_ArqResp ;
  ArqSTS    := CACBrTEFDPetroCard_ArqSTS ;
  ArqTemp   := CACBrTEFDPetroCard_ArqTemp ;
  GPExeName := CACBrTEFDPetroCard_GPExeName ;
  fpTipo    := gpPetroCard;
  Name      := 'PetroCard' ;
end;

end.

