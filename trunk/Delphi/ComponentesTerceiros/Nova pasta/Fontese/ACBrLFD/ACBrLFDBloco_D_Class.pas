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

unit ACBrLFDBloco_D_Class;

interface

uses SysUtils, Classes, DateUtils, ACBrLFD3505, ACBrLFDBlocos, ACBrLFDBloco_D,
     ACBrTXTClass;


type
  /// TBloco_D - 

  { TBloco_D }

  TBloco_D = class(TACBrLFD3505)
  private
    FRegistroD001: TRegistroD001;
    FRegistroD990: TRegistroD990;

    procedure CriaRegistros;
    procedure LiberaRegistros;
  public
    constructor Create;           /// Create
    destructor Destroy; override; /// Destroy
    procedure LimpaRegistros;

    procedure WriteRegistroD001;
    procedure WriteRegistroD990;

    property RegistroD001: TRegistroD001 read FRegistroD001 write FRegistroD001;
    property RegistroD990: TRegistroD990 read FRegistroD990 write FRegistroD990;
  end;

implementation

uses ACBrLFDUtils, StrUtils;

{ TBloco_D }

constructor TBloco_D.Create ;
begin
  inherited ;
  CriaRegistros;
end;

destructor TBloco_D.Destroy;
begin
  LiberaRegistros;
  inherited;
end;

procedure TBloco_D.CriaRegistros;
begin
  FRegistroD001 := TRegistroD001.Create;
  FRegistroD990 := TRegistroD990.Create;
end;

procedure TBloco_D.LiberaRegistros;
begin
  FRegistroD001.Free;
  FRegistroD990.Free;
end;

procedure TBloco_D.LimpaRegistros;
begin
  /// Limpa os Registros
  LiberaRegistros;
  Conteudo.Clear;

  /// Recriar os Registros Limpos
  CriaRegistros;
end;

procedure TBloco_D.WriteRegistroD001;
begin
   if Assigned(FRegistroD001) then
   begin
      with FRegistroD001 do
      begin
         Add( LFill( 'D001' ) +
              LFill( Integer(IND_MOV), 0 ) ) ;

      end;

       RegistroD990.QTD_LIN_D := RegistroD990.QTD_LIN_D + 1;
    end;
end;

procedure TBloco_D.WriteRegistroD990;
  var
  strLinha: String;
begin
//--Before
  strLinha := '';

  if Assigned(RegistroD990) then
  begin
     with RegistroD990 do
     begin
       QTD_LIN_D := QTD_LIN_D + 1;
       strLinha := LFill('D990') +
                   LFill(QTD_LIN_D,0);
       Add(strLinha);
     end;
  end;
end;

end.
