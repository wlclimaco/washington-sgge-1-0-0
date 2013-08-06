{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009   Juliana Tamizou                      }
{                                                                              }
{ Colaboradores nesse arquivo: Isaque Pinheiro                                 }
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
|* 31/01/2013: Nilson Sergio
|*  - Cria��o e distribui��o da Primeira Versao
*******************************************************************************}

unit ACBrLFDBloco_G_Class;

interface

uses SysUtils, Classes, DateUtils, ACBrLFD3505, ACBrLFDBlocos, ACBrLFDBloco_G,
     ACBrTXTClass;

type

  /// BLOCO G: INFORMA��ES ECON�MICO-FISCAIS

  { TBloco_G }

  TBloco_G = class(TACBrLFD3505)
  private
    FRegistroG001: TRegistroG001;
    FRegistroG990: TRegistroG990;

    procedure CriaRegistros;
    procedure LiberaRegistros;
  public
    constructor Create;           /// Create
    destructor Destroy; override; /// Destroy
    procedure LimpaRegistros;

    procedure WriteRegistroG001;
    procedure WriteRegistroG990;

    property RegistroG001: TRegistroG001 read FRegistroG001 write FRegistroG001;
    property RegistroG990: TRegistroG990 read FRegistroG990 write FRegistroG990;
  end;

implementation

uses ACBrLFDUtils, StrUtils;

{ TBloco_G }

constructor TBloco_G.Create ;
begin
  inherited ;
  CriaRegistros;
end;

destructor TBloco_G.Destroy;
begin
  LiberaRegistros;
  inherited;
end;

procedure TBloco_G.CriaRegistros;
begin
  FRegistroG001 := TRegistroG001.Create;
  FRegistroG990 := TRegistroG990.Create;

  FRegistroG990.QTD_LIN_G := 0;
end;

procedure TBloco_G.LiberaRegistros;
begin
  FRegistroG001.Free;
  FRegistroG990.Free;
end;

procedure TBloco_G.LimpaRegistros;
begin
  /// Limpa os Registros
  LiberaRegistros;
  Conteudo.Clear;

  /// Recriar os Registros Limpos
  CriaRegistros;
end;

procedure TBloco_G.WriteRegistroG001;
begin
  if Assigned(FRegistroG001) then
    with FRegistroG001 do
    begin
      Add( LFill('G001') +
           LFill(Integer(IND_MOV), 0) );

      FRegistroG990.QTD_LIN_G := FRegistroG990.QTD_LIN_G + 1;
    end;
end;

procedure TBloco_G.WriteRegistroG990;
begin
  if Assigned(FRegistroG990) then
    with FRegistroG990 do
    begin
      QTD_LIN_G := QTD_LIN_G + 1;

      Add( LFill('G990') +
           LFill(QTD_LIN_G, 0) );
    end;
end;

end.
