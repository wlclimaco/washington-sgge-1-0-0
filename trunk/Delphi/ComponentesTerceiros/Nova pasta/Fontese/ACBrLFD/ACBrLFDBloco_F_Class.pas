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

unit ACBrLFDBloco_F_Class;

interface

uses SysUtils, Classes, DateUtils, ACBrLFD3505, ACBrLFDBlocos, ACBrLFDBloco_F,
     ACBrTXTClass;

type

  /// BLOCO F: LIVROS E MAPAS DE REGISTRO DAS OPERA��ES DE CONTROLE

  { TBloco_F }

  TBloco_F = class(TACBrLFD3505)
  private
    FRegistroF001: TRegistroF001;
    FRegistroF990: TRegistroF990;

    procedure CriaRegistros;
    procedure LiberaRegistros;
  public
    constructor Create;           /// Create
    destructor Destroy; override; /// Destroy
    procedure LimpaRegistros;

    procedure WriteRegistroF001;
    procedure WriteRegistroF990;

    property RegistroF001: TRegistroF001 read FRegistroF001 write FRegistroF001;
    property RegistroF990: TRegistroF990 read FRegistroF990 write FRegistroF990;
  end;

implementation

uses ACBrLFDUtils, StrUtils;

{ TBloco_F }

constructor TBloco_F.Create ;
begin
  inherited ;
  CriaRegistros;
end;

destructor TBloco_F.Destroy;
begin
  LiberaRegistros;
  inherited;
end;

procedure TBloco_F.CriaRegistros;
begin  
  FRegistroF001 := TRegistroF001.Create;
  FRegistroF990 := TRegistroF990.Create;

  FRegistroF990.QTD_LIN_F := 0;
end;

procedure TBloco_F.LiberaRegistros;
begin
  FRegistroF001.Free;
  FRegistroF990.Free;
end;

procedure TBloco_F.LimpaRegistros;
begin
  /// Limpa os Registros
  LiberaRegistros;
  Conteudo.Clear;

  /// Recriar os Registros Limpos
  CriaRegistros;
end;

procedure TBloco_F.WriteRegistroF001;
begin
  if Assigned(FRegistroF001) then
    with FRegistroF001 do
    begin
      Add( LFill('F001') +
           LFill(Integer(IND_MOV), 0) );

      FRegistroF990.QTD_LIN_F := FRegistroF990.QTD_LIN_F + 1;
    end;
end;

procedure TBloco_F.WriteRegistroF990;
begin
  if Assigned(FRegistroF990) then
    with FRegistroF990 do
    begin
      QTD_LIN_F := QTD_LIN_F + 1;

      Add( LFill('F990') +
           LFill(QTD_LIN_F, 0) );
    end;
end;

end.
