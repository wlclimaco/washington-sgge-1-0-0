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

unit ACBrLFDBloco_H_Class;

interface

uses SysUtils, Classes, DateUtils, ACBrLFD3505, ACBrLFDBlocos, ACBrLFDBloco_H,
     ACBrTXTClass;


type
  /// Bloco H - LIVRO DE REGISTRO DO INVENT�RIO

  { TBloco_H }

  TBloco_H = class(TACBrLFD3505)
  private
    FRegistroH001: TRegistroH001;
    FRegistroH990: TRegistroH990;

    FRegistroH001Count: Integer;
    FRegistroH020Count: Integer;
    FRegistroH030Count: Integer;
    FRegistroH040Count: Integer;
    FRegistroH050Count: Integer;
    FRegistroH060Count: Integer;

    procedure WriteRegistroH020(RegH001: TRegistroH001);
    procedure WriteRegistroH030(RegH020: TRegistroH020);
    procedure WriteRegistroH040(RegH020: TRegistroH020);
    procedure WriteRegistroH050(RegH020: TRegistroH020);
    procedure WriteRegistroH060(RegH020: TRegistroH020);

    procedure CriaRegistros;
    procedure LiberaRegistros;
  public
    constructor Create;           /// Create
    destructor Destroy; override; /// Destroy
    procedure LimpaRegistros;

    function RegistroH001New: TRegistroH001;
    function RegistroH030New: TRegistroH030;
    function RegistroH040New: TRegistroH040;
    function RegistroH050New: TRegistroH050;
    function RegistroH060New: TRegistroH060;

    procedure WriteRegistroH001;
    procedure WriteRegistroH990;

    property RegistroH001: TRegistroH001 read FRegistroH001 write FRegistroH001;
    property RegistroH990: TRegistroH990 read FRegistroH990 write FRegistroH990;

    property RegistroH001Count: Integer read FRegistroH001Count write FRegistroH001Count;
    property RegistroH020Count: Integer read FRegistroH020Count write FRegistroH020Count;
    property RegistroH030Count: Integer read FRegistroH030Count write FRegistroH030Count;
    property RegistroH040Count: Integer read FRegistroH040Count write FRegistroH040Count;
    property RegistroH050Count: Integer read FRegistroH050Count write FRegistroH050Count;
    property RegistroH060Count: Integer read FRegistroH060Count write FRegistroH060Count;
  end;

implementation

uses ACBrLFDUtils, StrUtils;

{ TBloco_H }

constructor TBloco_H.Create ;
begin
  inherited ;
  CriaRegistros;
end;

destructor TBloco_H.Destroy;
begin
  LiberaRegistros;
  inherited;
end;

procedure TBloco_H.CriaRegistros;
begin
  FRegistroH001 := TRegistroH001.Create;
  FRegistroH990 := TRegistroH990.Create;

  FRegistroH001Count := 0;
  FRegistroH020Count := 0;
  FRegistroH030Count := 0;
  FRegistroH040Count := 0;
  FRegistroH050Count := 0;
  FRegistroH060Count := 0;

  FRegistroH990.QTD_LIN_H := 0;
end;

procedure TBloco_H.LiberaRegistros;
begin
  FRegistroH001.Free;
  FRegistroH990.Free;
end;

procedure TBloco_H.LimpaRegistros;
begin
  /// Limpa os Registros
  LiberaRegistros;
  Conteudo.Clear;

  /// Recriar os Registros Limpos
  CriaRegistros;
end;

function TBloco_H.RegistroH001New: TRegistroH001;
begin
  Result := FRegistroH001;
end;

function TBloco_H.RegistroH030New: TRegistroH030;
var
  H020: TRegistroH020;
begin
end;

function TBloco_H.RegistroH040New: TRegistroH040;
var
  H020: TRegistroH020;
begin
end;

function TBloco_H.RegistroH050New: TRegistroH050;
var
  H020: TRegistroH020;
begin
end;

function TBloco_H.RegistroH060New: TRegistroH060;
var
  H020: TRegistroH020;
begin
end;

procedure TBloco_H.WriteRegistroH001;
begin
   if Assigned(FRegistroH001) then
   begin
      with FRegistroH001 do
      begin
         Add( LFill( 'H001' ) +
              LFill( Integer(IND_MOV), 0 ) ) ;

      WriteRegistroH020(FRegistroH001);
    end;

      RegistroH990.QTD_LIN_H := RegistroH990.QTD_LIN_H + 1;
   end;
end;

procedure TBloco_H.WriteRegistroH020(RegH001: TRegistroH001);
begin
  if Assigned(RegH001.RegistroH020) then
      with RegH001.RegistroH020 do
      begin
         Add( LFill('H020') +
              LFill(DT_INV) +
              LFill(VL_ESTQ,0,0,false,'0','#0.##') );
      end;

      FRegistroH990.QTD_LIN_H := FRegistroH990.QTD_LIN_H + 1;
end;

procedure TBloco_H.WriteRegistroH030(RegH020: TRegistroH020);
begin

end;

procedure TBloco_H.WriteRegistroH040(RegH020: TRegistroH020);
begin

end;

procedure TBloco_H.WriteRegistroH050(RegH020: TRegistroH020);
begin

end;

procedure TBloco_H.WriteRegistroH060(RegH020: TRegistroH020);
begin

end;

procedure TBloco_H.WriteRegistroH990;
var
  strLinha: String;
begin
   //--Before
   strLinha := '';

   if Assigned(RegistroH990) then
   begin
      with RegistroH990 do
      begin
        QTD_LIN_H := QTD_LIN_H + 1;
        ///
        strLinha := LFill('H990') +
                    LFill(QTD_LIN_H,0);
        Add(strLinha);
     end;
  end;
end;

end.
