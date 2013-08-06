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

unit ACBrLFDBloco_Z_Class;

interface

uses SysUtils, Classes, DateUtils, ACBrLFD3505, ACBrLFDBlocos, ACBrLFDBloco_Z,
     ACBrTXTClass;

type

  /// BLOCO Z: REGISTROS COMPLEMENTARES

  { TBloco_Z }

  TBloco_Z = class(TACBrLFD3505)
  private
    FRegistroZ001: TRegistroZ001;
    FRegistroZ990: TRegistroZ990;

    procedure CriaRegistros;
    procedure LiberaRegistros;
  public
    constructor Create;           /// Create
    destructor Destroy; override; /// Destroy
    procedure LimpaRegistros;

    procedure WriteRegistroZ001;
    procedure WriteRegistroZ990;

    property RegistroZ001: TRegistroZ001 read FRegistroZ001 write FRegistroZ001;
    property RegistroZ990: TRegistroZ990 read FRegistroZ990 write FRegistroZ990;
  end;

implementation

uses ACBrLFDUtils, StrUtils;

{ TBloco_Z }

constructor TBloco_Z.Create ;
begin
  inherited ;
  CriaRegistros;
end;

destructor TBloco_Z.Destroy;
begin
  LiberaRegistros;
  inherited;
end;

procedure TBloco_Z.CriaRegistros;
begin  
  FRegistroZ001 := TRegistroZ001.Create;
  FRegistroZ990 := TRegistroZ990.Create;

  FRegistroZ990.QTD_LIN_Z := 0;
end;

procedure TBloco_Z.LiberaRegistros;
begin
  FRegistroZ001.Free;
  FRegistroZ990.Free;
end;

procedure TBloco_Z.LimpaRegistros;
begin
  /// Limpa os Registros
  LiberaRegistros;
  Conteudo.Clear;

  /// Recriar os Registros Limpos
  CriaRegistros;
end;

procedure TBloco_Z.WriteRegistroZ001;
begin
  if Assigned(FRegistroZ001) then
    with FRegistroZ001 do
    begin
      Add( LFill('Z001') +
           LFill(Integer(IND_MOV), 0) );

      FRegistroZ990.QTD_LIN_Z := FRegistroZ990.QTD_LIN_Z + 1;
    end;
end;

procedure TBloco_Z.WriteRegistroZ990;
begin
  if Assigned(FRegistroZ990) then
    with FRegistroZ990 do
    begin
      QTD_LIN_Z := QTD_LIN_Z + 1;

      Add( LFill('Z990') +
           LFill(QTD_LIN_Z, 0) );
    end;
end;

end.
