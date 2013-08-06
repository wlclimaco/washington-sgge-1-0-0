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

unit ACBrLFDBloco_L_Class;

interface

uses SysUtils, Classes, DateUtils, ACBrLFD3505, ACBrLFDBlocos, ACBrLFDBloco_L,
     ACBrTXTClass;

type

  /// BLOCO L: REGISTROS DE NATUREZA FINANCEIRA E OR�AMENT�RIA

  { TBloco_L }

  TBloco_L = class(TACBrLFD3505)
  private
    FRegistroL001: TRegistroL001;
    FRegistroL990: TRegistroL990;

    procedure CriaRegistros;
    procedure LiberaRegistros;
  public
    constructor Create;           /// Create
    destructor Destroy; override; /// Destroy
    procedure LimpaRegistros;

    procedure WriteRegistroL001;
    procedure WriteRegistroL990;

    property RegistroL001: TRegistroL001 read FRegistroL001 write FRegistroL001;
    property RegistroL990: TRegistroL990 read FRegistroL990 write FRegistroL990;
  end;

implementation

uses ACBrLFDUtils, StrUtils;

{ TBloco_L }

constructor TBloco_L.Create ;
begin
  inherited ;
  CriaRegistros;
end;

destructor TBloco_L.Destroy;
begin
  LiberaRegistros;
  inherited;
end;

procedure TBloco_L.CriaRegistros;
begin
  FRegistroL001 := TRegistroL001.Create;
  FRegistroL990 := TRegistroL990.Create;

  FRegistroL990.QTD_LIN_L:= 0;
end;

procedure TBloco_L.LiberaRegistros;
begin
  FRegistroL001.Free;
  FRegistroL990.Free;
end;

procedure TBloco_L.LimpaRegistros;
begin
  /// Limpa os Registros
  LiberaRegistros;
  Conteudo.Clear;

  /// Recriar os Registros Limpos
  CriaRegistros;
end;

procedure TBloco_L.WriteRegistroL001;
begin
  if Assigned(FRegistroL001) then
    with FRegistroL001 do
    begin
      Add( LFill('L001') +
           LFill(Integer(IND_MOV), 0) );

      FRegistroL990.QTD_LIN_L := FRegistroL990.QTD_LIN_L + 1;
    end;
end;

procedure TBloco_L.WriteRegistroL990;
begin
  if Assigned(FRegistroL990) then
    with FRegistroL990 do
    begin
      QTD_LIN_L := QTD_LIN_L + 1;

      Add( LFill('L990') +
           LFill(QTD_LIN_L, 0) );
    end;
end;

end.
