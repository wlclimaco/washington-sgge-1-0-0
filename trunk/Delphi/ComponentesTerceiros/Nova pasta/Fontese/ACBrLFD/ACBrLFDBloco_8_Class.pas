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

unit ACBrLFDBloco_8_Class;

interface

uses SysUtils, Classes, DateUtils, ACBrLFD3505, ACBrLFDBlocos, ACBrLFDBloco_8,
     ACBrTXTClass;


type
  /// Bloco_8 - INFORMA��ES COMPLEMENTARES DA SEFAZ/UF

  { TBloco_8 }

  TBloco_8 = class(TACBrLFD3505)
  private
    FRegistro8001: TRegistro8001;
    FRegistro8990: TRegistro8990;

    FRegistro8020Count: Integer;
    FRegistro8025Count: Integer;
    FRegistro8030Count: Integer;

    procedure WriteRegistro8020(Reg8001: TRegistro8001);
    procedure WriteRegistro8025(Reg8020: TRegistro8020);
    procedure WriteRegistro8030(Reg8025: TRegistro8025);

    procedure CriaRegistros;
    procedure LiberaRegistros;
  public
    constructor Create;           /// Create
    destructor Destroy; override; /// Destroy
    procedure LimpaRegistros;

    function Registro8001New: TRegistro8001;
    function Registro8020New: TRegistro8020;
    function Registro8025New: TRegistro8025;
    function Registro8030New: TRegistro8030;

    procedure WriteRegistro8001;
    procedure WriteRegistro8990;

    property Registro8001: TRegistro8001 read FRegistro8001 write FRegistro8001;
    property Registro8990: TRegistro8990 read FRegistro8990 write FRegistro8990;

    property Registro8020Count: Integer read FRegistro8020Count write FRegistro8020Count;
    property Registro8025Count: Integer read FRegistro8025Count write FRegistro8025Count;
    property Registro8030Count: Integer read FRegistro8030Count write FRegistro8030Count;
  end;

implementation

uses ACBrLFDUtils, StrUtils;

{ TBloco_8 }

constructor TBloco_8.Create ;
begin
  inherited ;
  CriaRegistros;
end;

destructor TBloco_8.Destroy;
begin
  LiberaRegistros;
  inherited;
end;

procedure TBloco_8.CriaRegistros;
begin
  FRegistro8001 := TRegistro8001.Create;
  FRegistro8990 := TRegistro8990.Create;
end;

procedure TBloco_8.LiberaRegistros;
begin
  FRegistro8001.Free;
  FRegistro8990.Free;
end;

procedure TBloco_8.LimpaRegistros;
begin
  /// Limpa os Registros
  LiberaRegistros;
  Conteudo.Clear;

  /// Recriar os Registros Limpos
  CriaRegistros;
end;

function TBloco_8.Registro8001New: TRegistro8001;
begin
  Result := FRegistro8001;
end;

function TBloco_8.Registro8020New: TRegistro8020;
begin
  Result := FRegistro8001.Registro8020;
end;

function TBloco_8.Registro8025New: TRegistro8025;
begin
  with FRegistro8001 do
    Result := Registro8020.Registro8025.New(Registro8020);
end;

function TBloco_8.Registro8030New: TRegistro8030;
var
  Reg8025: TRegistro8025;
begin
  with FRegistro8001.Registro8020.Registro8025 do
    Reg8025 := Items[ AchaUltimoPai('8025', '8030') ];
  Result := Reg8025.Registro8030.New(Reg8025);
end;

procedure TBloco_8.WriteRegistro8001;
begin
   if Assigned(FRegistro8001) then
    begin
       with FRegistro8001 do
       begin
          Add( LFill( '8001' ) +
               LFill( Integer(IND_MOV), 0 ) +
               LFill('DF')) ;

          if IND_MOV = imlComDados then
          begin
            WriteRegistro8020(FRegistro8001) ;
          end;
       end;

       Registro8990.QTD_LIN_8 := Registro8990.QTD_LIN_8 + 1;
    end;
end;

procedure TBloco_8.WriteRegistro8020(Reg8001: TRegistro8001);
begin
  if Assigned(Reg8001.Registro8020) then
    with Reg8001.Registro8020 do
    begin
      Add( LFill('8020') +
           LFill(Integer(imlComDados), 0) +
           LFill(DT_INI) +
           LFill(DT_FIN) +
           LFill(COMB, 4) );

      if IND_DAD = imlComDados then
      begin
        WriteRegistro8025(Reg8001.Registro8020);
      end;

      FRegistro8990.QTD_LIN_8 := FRegistro8990.QTD_LIN_8 + 1;
    end;
end;

procedure TBloco_8.WriteRegistro8025(Reg8020: TRegistro8020);
begin

end;

procedure TBloco_8.WriteRegistro8030(Reg8025: TRegistro8025);
begin

end;

procedure TBloco_8.WriteRegistro8990;
var
  strLinha: String;
begin
//--Before
   strLinha := '';

   if Assigned(Registro8990) then
   begin
      with Registro8990 do
      begin
        QTD_LIN_8 := QTD_LIN_8 + 1;
        ///
        strLinha := LFill('8990') +
                    LFill(QTD_LIN_8,0);
        Add(strLinha);
      end;
   end;
end;

end.
