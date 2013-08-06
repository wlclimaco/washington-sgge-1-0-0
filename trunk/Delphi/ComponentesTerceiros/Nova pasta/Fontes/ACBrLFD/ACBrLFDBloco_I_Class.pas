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

unit ACBrLFDBloco_I_Class;

interface

uses SysUtils, Classes, DateUtils, ACBrLFD3505, ACBrLFDBlocos, ACBrLFDBloco_I,
     ACBrTXTClass;

type

  /// BLOCO I: LAN�AMENTOS CONT�BEIS

  { TBloco_I }

  TBloco_I = class(TACBrLFD3505)
  private
    FRegistroI001: TRegistroI001;
    FRegistroI990: TRegistroI990;

    procedure CriaRegistros;
    procedure LiberaRegistros;
  public
    constructor Create;           /// Create
    destructor Destroy; override; /// Destroy
    procedure LimpaRegistros;

    procedure WriteRegistroI001;
    procedure WriteRegistroI990;

    property RegistroI001: TRegistroI001 read FRegistroI001 write FRegistroI001;
    property RegistroI990: TRegistroI990 read FRegistroI990 write FRegistroI990;
  end;

implementation

uses ACBrLFDUtils, StrUtils;

{ TBloco_F }

constructor TBloco_I.Create ;
begin
  inherited ;
  CriaRegistros;
end;

destructor TBloco_I.Destroy;
begin
  LiberaRegistros;
  inherited;
end;

procedure TBloco_I.CriaRegistros;
begin  
  FRegistroI001 := TRegistroI001.Create;
  FRegistroI990 := TRegistroI990.Create;

  FRegistroI990.QTD_LIN_I := 0;
end;

procedure TBloco_I.LiberaRegistros;
begin
  FRegistroI001.Free;
  FRegistroI990.Free;
end;

procedure TBloco_I.LimpaRegistros;
begin
  /// Limpa os Registros
  LiberaRegistros;
  Conteudo.Clear;

  /// Recriar os Registros Limpos
  CriaRegistros;
end;

procedure TBloco_I.WriteRegistroI001;
begin
  if Assigned(FRegistroI001) then
    with FRegistroI001 do
    begin
      Add( LFill('I001') +
           LFill(Integer(IND_MOV), 0) );

      FRegistroI990.QTD_LIN_I := FRegistroI990.QTD_LIN_I + 1;
    end;
end;

procedure TBloco_I.WriteRegistroI990;
begin
  if Assigned(FRegistroI990) then
    with FRegistroI990 do
    begin
      QTD_LIN_I := QTD_LIN_I + 1;

      Add( LFill('I990') +
           LFill(QTD_LIN_I, 0) );
    end;
end;

end.
