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
|* 26/01/2013: Nilson Sergio
|*  - Cria��o e distribui��o da Primeira Versao
*******************************************************************************}

unit ACBrLFDBloco_9_Class;

interface

uses SysUtils, Classes, DateUtils, ACBrLFD3505, ACBrLFDBlocos, ACBrLFDBloco_9,
     ACBrTXTClass;


type
  /// TBloco_9 - 

  { TBloco_9 }

  TBloco_9 = class(TACBrLFD3505)
  private
    FRegistro9001: TRegistro9001;
    FRegistro9900: TRegistro9900List;
    FRegistro9990: TRegistro9990;
    FRegistro9999: TRegistro9999;

    FRegistro9020Count: Integer;
    FRegistro9030Count: Integer;
    FRegistro9040Count: Integer;

    procedure WriteRegistro9020(Reg9001: TRegistro9001);
    procedure WriteRegistro9030(Reg9020: TRegistro9020);
    procedure WriteRegistro9040(Reg9020: TRegistro9020);

    procedure CriaRegistros;
    procedure LiberaRegistros;
  public
    constructor Create;           /// Create
    destructor Destroy; override; /// Destroy
    procedure LimpaRegistros;

    procedure WriteRegistro9001;
    procedure WriteRegistro9900;
    procedure WriteRegistro9990;
    procedure WriteRegistro9999;

    property Registro9001: TRegistro9001 read FRegistro9001 write FRegistro9001;
    property Registro9900: TRegistro9900List read FRegistro9900 write FRegistro9900;
    property Registro9990: TRegistro9990 read FRegistro9990 write FRegistro9990;
    property Registro9999: TRegistro9999 read FRegistro9999 write FRegistro9999;
  end;

implementation

uses ACBrLFDUtils, StrUtils;

{ TBloco_9 }

constructor TBloco_9.Create ;
begin
  inherited ;
  CriaRegistros;
end;

destructor TBloco_9.Destroy;
begin
  LiberaRegistros;
  inherited;
end;

procedure TBloco_9.CriaRegistros;
begin
  FRegistro9001 := TRegistro9001.Create;
  FRegistro9900 := TRegistro9900List.Create;
  FRegistro9990 := TRegistro9990.Create;
  FRegistro9999 := TRegistro9999.Create;

  FRegistro9020Count := 0;
  FRegistro9030Count := 0;
  FRegistro9040Count := 0;
end;

procedure TBloco_9.LiberaRegistros;
begin
  FRegistro9001.Free;
  FRegistro9900.Free;
  FRegistro9990.Free;
  FRegistro9999.Free;
end;

procedure TBloco_9.LimpaRegistros;
begin
  /// Limpa os Registros
  LiberaRegistros;
  Conteudo.Clear;

  /// Recriar os Registros Limpos
  CriaRegistros;
end;

procedure TBloco_9.WriteRegistro9001;
begin
  if Assigned(FRegistro9001) then
    with FRegistro9001 do
    begin
      Add( LFill('9001') +
           LFill(Integer(IND_MOV), 0) );

      if IND_MOV = imlComDados then
      begin
        WriteRegistro9020(FRegistro9001);
      end;

      FRegistro9990.QTD_LIN_9 := FRegistro9990.QTD_LIN_9 + 1;
    end;
end;

procedure TBloco_9.WriteRegistro9020(Reg9001: TRegistro9001);
begin
  {
  WriteRegistro9030(FRegistro9001);
  WriteRegistro9030(FRegistro9001);
  }
end;

procedure TBloco_9.WriteRegistro9030(Reg9020: TRegistro9020);
begin

end;

procedure TBloco_9.WriteRegistro9040(Reg9020: TRegistro9020);
begin

end;

procedure TBloco_9.WriteRegistro9900;
var
  intFor: Integer;
begin
  if Assigned(FRegistro9900) then
    for intFor := 0 to FRegistro9900.Count - 1 do
      with FRegistro9900.Items[intFor] do
      begin
        Add( LFill('9900') +
             LFill(REG_BLC) +
             LFill(QTD_REG_BLC, 0) );

        FRegistro9990.QTD_LIN_9 := FRegistro9990.QTD_LIN_9 + 1;
      end;
end;

procedure TBloco_9.WriteRegistro9990;
begin
  if Assigned(FRegistro9990) then
    with FRegistro9990 do
    begin
      QTD_LIN_9 := QTD_LIN_9 + 2;

      Add( LFill('9990') +
           LFill(QTD_LIN_9, 0) );
    end;
end;

procedure TBloco_9.WriteRegistro9999;
begin
  if Assigned(FRegistro9999) then
    with FRegistro9999 do
    begin
      Add( LFill('9999') +
           LFill(QTD_LIN, 0) );
    end;
end;

end.
