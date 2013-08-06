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

unit ACBrLFDBloco_K_Class;

interface

uses SysUtils, Classes, DateUtils, ACBrLFD3505, ACBrLFDBlocos, ACBrLFDBloco_K,
     ACBrTXTClass;

type

  /// BLOCO K: FOLHA DE PAGAMENTO

  { TBloco_K }

  TBloco_K = class(TACBrLFD3505)
  private
    FRegistroK001: TRegistroK001;
    FRegistroK990: TRegistroK990;

    procedure CriaRegistros;
    procedure LiberaRegistros;
  public
    constructor Create;           /// Create
    destructor Destroy; override; /// Destroy
    procedure LimpaRegistros;

    procedure WriteRegistroK001;
    procedure WriteRegistroK990;

    property RegistroK001: TRegistroK001 read FRegistroK001 write FRegistroK001;
    property RegistroK990: TRegistroK990 read FRegistroK990 write FRegistroK990;
  end;

implementation

uses ACBrLFDUtils, StrUtils;

{ TBloco_K }

constructor TBloco_K.Create ;
begin
  inherited ;
  CriaRegistros;
end;

destructor TBloco_K.Destroy;
begin
  LiberaRegistros;
  inherited;
end;

procedure TBloco_K.CriaRegistros;
begin
  FRegistroK001 := TRegistroK001.Create;
  FRegistroK990 := TRegistroK990.Create;

  FRegistroK990.QTD_LIN_K:= 0;
end;

procedure TBloco_K.LiberaRegistros;
begin
  FRegistroK001.Free;
  FRegistroK990.Free;
end;

procedure TBloco_K.LimpaRegistros;
begin
  /// Limpa os Registros
  LiberaRegistros;
  Conteudo.Clear;

  /// Recriar os Registros Limpos
  CriaRegistros;
end;

procedure TBloco_K.WriteRegistroK001;
begin
  if Assigned(FRegistroK001) then
    with FRegistroK001 do
    begin
      Add( LFill('K001') +
           LFill(Integer(IND_MOV), 0) );

      FRegistroK990.QTD_LIN_K := FRegistroK990.QTD_LIN_K + 1;
    end;
end;

procedure TBloco_K.WriteRegistroK990;
begin
  if Assigned(FRegistroK990) then
    with FRegistroK990 do
    begin
      QTD_LIN_K := QTD_LIN_K + 1;

      Add( LFill('K990') +
           LFill(QTD_LIN_K, 0) );
    end;
end;

end.
