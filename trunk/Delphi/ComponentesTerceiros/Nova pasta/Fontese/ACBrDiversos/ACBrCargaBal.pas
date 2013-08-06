{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2005 Anderson Rogerio Bejatto               }
{                                                                              }
{ Colaboradores nesse arquivo:          Daniel Simoes de Almeida               }
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
|* 19/09/2011: R�gys Borges da Silveira
|*  - Primeira Versao ACBrCargaBal
******************************************************************************}

unit ACBrCargaBal;

{$I ACBr.inc}

interface

uses
  ACBrBase,
  SysUtils, Classes, Contnrs;

type
  EACBrCargaBal = class(Exception);
  TACBrCargaBalTipoVenda = (tpvPeso, tpvUnidade, tpvEAN13);
  TACBrCargaBalModelo = (modFilizola, modToledo, modUrano, modToledoMGV5);
  TACBrCargaBalProgresso = procedure(Mensagem: String; ProgressoAtual, ProgressoTotal: Integer) of object;

  // nutricional
  TACBrCargaBalNutriUndPorcao = (tpGramas, tpMililitros, tpUnidades);
  TACBrCargaBalNutriPartdecimal = (tpPara0, tpPara14, tpPara13, tpPara12, tpPara23, tpPara34);
  TACBrCargaBalNutriMedCaseira = (tpColherSopa, tpColherCafe, tpColherCha, tpXicara, tpDeXicara, tpUnidade, tpPacote, tpFatia,
                                  tpFatiaFina, tpPedaco, tpFolha, tpPao, tpBiscoito, tpBisnaguinha, tpDisco, tpCopo, tpPorcao,
                                  tpTablete, tpSache, tpAlmodega, tpBife, tpFile, tpConcha, tpBala, tpPratoFundo, tpPitada, tpLata);


  TACBrCargaBalSetor = class
  private
    FCodigo: Integer;
    FDescricao: String;
  public
    constructor Create;
    property Codigo: Integer read FCodigo write FCodigo;
    property Descricao: String read FDescricao write FDescricao;
  end;

  TACBrCargaBalNutricional = class
  private
    fCodigo: Integer;
    FQtd: Integer;
    FUndPorcao: TACBrCargaBalNutriUndPorcao;
    FPartInteira: Integer;
    FPartDecimal: TACBrCargaBalNutriPartdecimal;
    FMedCaseira: TACBrCargaBalNutriMedCaseira;
    FValorEnergetico: Integer;
    FCarboidrato: Currency;
    FProteina: Currency;
    FGorduraTotal: Currency;
    FGorduraSaturada: Currency;
    FGorduraTrans: Currency;
    FFibra: Currency;
    FSodio: Currency;

  public
    constructor Create;
    property Codigo: Integer read FCodigo write FCodigo;    
    property Qtd: Integer read FQtd write FQtd;
    property UndPorcao: TACBrCargaBalNutriUndPorcao read FUndPorcao write FUndPorcao;
    property PartInteira: Integer read FPartInteira write FPartInteira;
    property PartDecimal: TACBrCargaBalNutriPartdecimal read FPartDecimal write FPartDecimal;
    property MedCaseira: TACBrCargaBalNutriMedCaseira read FMedCaseira write FMedCaseira;
    property ValorEnergetico: Integer read FValorEnergetico write FValorEnergetico;
    property Carboidrato: Currency read FCarboidrato write FCarboidrato;
    property Proteina: Currency read FProteina write FProteina;
    property GorduraTotal: Currency read FGorduraTotal write FGorduraTotal;
    property GorduraSaturada: Currency read FGorduraSaturada write FGorduraSaturada;
    property GorduraTrans: Currency read FGorduraTrans write FGorduraTrans;
    property Fibra: Currency read FFibra write FFibra;
    property Sodio: Currency read FSodio write FSodio;
  end;


  TACBrCargaBalItem = class
  private
    FTecla: Integer;
    FReceita: String;
    FValorVenda: Currency;
    FModeloEtiqueta: Smallint;
    FDescricao: String;
    FCodigo: Integer;
    FTipo: TACBrCargaBalTipoVenda;
    FValidade: Smallint;
    FSetor: TACBrCargaBalSetor;
    FNutricional: TACBrCargaBalNutricional;
  Public
    constructor Create;
    destructor Destroy; override;
    property Setor: TACBrCargaBalSetor read FSetor write FSetor;
    property ModeloEtiqueta: Smallint read FModeloEtiqueta write FModeloEtiqueta;
    property Tipo: TACBrCargaBalTipoVenda read FTipo write FTipo;
    property Codigo: Integer read FCodigo write FCodigo;
    property ValorVenda: Currency read FValorVenda write FValorVenda;
    property Validade: Smallint read FValidade write FValidade;
    property Descricao: String read FDescricao write FDescricao;
    property Receita: String read FReceita write FReceita;
    property Tecla: Integer read FTecla write FTecla;
    property Nutricional: TACBrCargaBalNutricional Read FNutricional Write FNutricional;
  end;

  TACBrCargaBalItens = class(TObjectList)
  private
    function GetItem(Index: Integer): TACBrCargaBalItem;
    procedure SetItem(Index: Integer; const Value: TACBrCargaBalItem);
  public
    constructor Create;
    destructor Destroy; Override;
    procedure Clear; override;
    function New: TACBrCargaBalItem;
    property Items[Index: Integer]: TACBrCargaBalItem read GetItem write SetItem; Default;
  end;

  TACBrCargaBal = class( TACBrComponent )
  private
    FOnProgresso: TACBrCargaBalProgresso;
    FProdutos: TACBrCargaBalItens;
    FModelo: TACBrCargaBalModelo;
    procedure Progresso(const AMensagem: String; const AContAtual, AContTotal: Integer);

    function RFill(Str: string; Tamanho: Integer = 0; Caracter: Char = ' '): string; overload;
    function LFIll(Str: string; Tamanho: Integer = 0; Caracter: Char = '0'): string; overload;
    function LFIll(Valor: Currency; Tamanho: Integer; Decimais: Integer = 2; Caracter: Char = '0'): string; overload;
    function LFIll(Valor: Integer; Tamanho: Integer; Caracter: Char = '0'): string; overload;


    function GetNomeArquivoNutricional: String;
    function GetNomeArquivoProduto: String;
    function GetNomeArquivoReceita: String;
    function GetNomeArquivoSetor: String;

    function GetTipoProdutoFilizola(Tipo: TACBrCargaBalTipoVenda): String;
    function GetTipoProdutoToledo(Tipo: TACBrCargaBalTipoVenda): String;
    function GetTipoProdutoUrano(Tipo: TACBrCargaBalTipoVenda): string;
    function CalcularSoma(const xStr: string): integer;
    function GetModeloStr: string;

    procedure PreencherFilizola(Arquivo, Setor: TStringList);
    procedure PreencherToledo(Arquivo, Nutricional, Receita: TStringList; Versao: integer = 0);
    procedure PreencherUrano(Arquivo: TStringList);

    function GetNutriUndPorcaoToledo(Tipo: TACBrCargaBalNutriUndPorcao): String;
    function GetNutriPartDecimalToledo(Tipo: TACBrCargaBalNutriPartdecimal): String;
    function GetNutriMedCaseiraToledo(Tipo: TACBrCargaBalNutriMedCaseira): String;
  public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure GerarArquivos(const ADiretorio: String);
  published
    property Modelo: TACBrCargaBalModelo read FModelo write FModelo;
    property ModeloStr: String read GetModeloStr;
    property Produtos: TACBrCargaBalItens read FProdutos write FProdutos;
    property OnProgresso: TACBrCargaBalProgresso read FOnProgresso write FOnProgresso;
  end;

implementation

{ TACBrCargaBalSetor }



constructor TACBrCargaBalSetor.Create;
begin
  FCodigo    := 0;
  FDescricao := EmptyStr;
end;

{ TACBrCargaBalNutricional }
constructor TACBrCargaBalNutricional.Create;
begin
  fCodigo := 0;
  FQtd    := 0;
  FUndPorcao := tpGramas;
  FPartInteira := 0;
  FPartDecimal := tpPara0;
  FMedCaseira := tpColherSopa;
  FValorEnergetico := 0;
  FCarboidrato := 0;
  FProteina := 0;
  FGorduraTotal := 0;
  FGorduraSaturada := 0;
  FGorduraTrans := 0;
  FFibra := 0;
  FSodio := 0;
end;


{ TACBrCargaBalItem }

constructor TACBrCargaBalItem.Create;
begin
  // Criacao da propriedade Setor
  FSetor := TACBrCargaBalSetor.Create;

  // Iniciar os campos de valores
  FCodigo          := 0;
  FDescricao       := EmptyStr;
  FTipo            := tpvPeso;
  FValorVenda      := 0.00;
  FModeloEtiqueta  := 0;
  FReceita         := EmptyStr;
  FValidade        := 0;

  FNutricional := TACBrCargaBalNutricional.Create;
end;

destructor TACBrCargaBalItem.Destroy;
begin
  FSetor.Free;
  FNutricional.Free;
  inherited;
end;

{ TACBrCargaBalItens }

procedure TACBrCargaBalItens.Clear;
begin
  inherited Clear;
end;

constructor TACBrCargaBalItens.create;
begin
  inherited Create(True);
end;

destructor TACBrCargaBalItens.destroy;
begin
  Clear;
  inherited Destroy;
end;

function TACBrCargaBalItens.GetItem(Index: Integer): TACBrCargaBalItem;
begin
  Result := TACBrCargaBalItem(inherited Items[Index]);
end;

function TACBrCargaBalItens.New: TACBrCargaBalItem;
begin
  Result := TACBrCargaBalItem.Create;
  Add(Result);
end;

procedure TACBrCargaBalItens.SetItem(Index: Integer;
  const Value: TACBrCargaBalItem);
begin
  Put(Index, Value);
end;

{ TACBrCargaBal }

constructor TACBrCargaBal.Create(AOwner: TComponent);
begin
  inherited;
  FProdutos := TACBrCargaBalItens.Create;
end;

destructor TACBrCargaBal.Destroy;
begin
  FProdutos.Free;
  inherited;
end;

function TACBrCargaBal.RFill(Str: string; Tamanho: Integer = 0;
  Caracter: Char = ' '): string;
begin
  if (Tamanho > 0) and (Length(Str) > Tamanho) then
    Result := Copy(Str, 1, Tamanho)
  else
    Result := Str + StringOfChar(Caracter, Tamanho - Length(Str));
end;

function TACBrCargaBal.LFill(Str: string; Tamanho: Integer = 0;
  Caracter: Char = '0'): string;
begin
  if (Tamanho > 0) and (Length(Str) > Tamanho) then
    Result := Copy(Str, 1, Tamanho)
  else
    Result := StringOfChar(Caracter, Tamanho - length(Str)) + Str;
end;

function TACBrCargaBal.LFill(Valor: Currency; Tamanho: Integer;
  Decimais: Integer = 2; Caracter: Char = '0'): string;
var
  i, p: Integer;
begin
  p := 1;

  for i := 1 to Decimais do
    p := p * 10;

  Result := LFill(Trunc(Valor * p), Tamanho, Caracter);
end;

function TACBrCargaBal.LFill(Valor: Integer; Tamanho: Integer;
  Caracter: Char = '0'): string;
begin
  Result := LFill(IntToStr(Valor), Tamanho, Caracter);
end;

function TACBrCargaBal.GetNomeArquivoProduto: String;
begin
  case FModelo of
    modFilizola : Result := 'CADTXT.TXT';
    modToledo   : Result := 'TXITENS.TXT';
    modUrano    : Result := 'PRODUTOS.TXT';
    modToledoMGV5   : Result := 'ITENSMGV.TXT';
  end;
end;

function TACBrCargaBal.GetNutriUndPorcaoToledo(Tipo: TACBrCargaBalNutriUndPorcao): String;
begin
   case Tipo of
    tpGramas : Result := '0';
    tpMililitros : Result := '1';
    tpUnidades : Result := '2';
   end;
end;

function TACBrCargaBal.GetNutriPartDecimalToledo(Tipo: TACBrCargaBalNutriPartdecimal): String;
begin
   case Tipo of
    tpPara0 : Result := '0';
    tpPara14 : Result := '1';
    tpPara13 : Result := '2';
    tpPara12 : Result := '3';
    tpPara23 : Result := '4';
    tpPara34 : Result := '5';
   end;
end;


function TACBrCargaBal.GetTipoProdutoToledo(Tipo: TACBrCargaBalTipoVenda): String;
begin
  case Tipo of
    tpvPeso    : Result := '0';
    tpvUnidade : Result := '1';
    tpvEAN13   : Result := '2';
  end;
end;

function TACBrCargaBal.GetNutriMedCaseiraToledo(Tipo: TACBrCargaBalNutriMedCaseira): String;
begin
  case tipo of
    tpColherSopa : Result := '00';
    tpColherCafe : Result := '01';
    tpColherCha : Result := '02';
    tpXicara : Result := '03';
    tpDeXicara : Result := '04';
    tpUnidade : Result := '05';
    tpPacote : Result := '06';
    tpFatia : Result := '07';
    tpFatiaFina : Result := '08';
    tpPedaco : Result := '09';
    tpFolha : Result := '10';
    tpPao : Result := '11';
    tpBiscoito : Result := '12';
    tpBisnaguinha : Result := '13';
    tpDisco : Result := '14';
    tpCopo : Result := '15';
    tpPorcao : Result := '16';
    tpTablete : Result := '17';
    tpSache : Result := '18';
    tpAlmodega : Result := '19';
    tpBife : Result := '20';
    tpFile : Result := '21';
    tpConcha : Result := '22';
    tpBala : Result := '23';
    tpPratoFundo : Result := '24';
    tpPitada : Result := '25';
    tpLata : Result := '26';
    end;
end;

function TACBrCargaBal.GetTipoProdutoFilizola(Tipo: TACBrCargaBalTipoVenda): String;
begin
  case Tipo of
    tpvPeso    : Result := 'P';
    tpvUnidade : Result := 'U';
  end;
end;

function TACBrCargaBal.GetTipoProdutoUrano(Tipo: TACBrCargaBalTipoVenda): string;
begin
  case Tipo of
    tpvPeso    : Result:='P';
    tpvUnidade : Result:='U';
  end;
end;

function TACBrCargaBal.GetNomeArquivoSetor: String;
begin
  // Toledo e Urano nao possuem arquivo de setor a parte
  case FModelo of
    modFilizola : Result := 'SETORTXT.TXT';
  end;
end;

function TACBrCargaBal.GetNomeArquivoReceita: String;
begin
  // Urano nao possue arquivo de Receita a parte
  case FModelo of
    modFilizola : Result := 'REC_ASS.TXT';
    modToledoMGV5: Result := 'TXINFO.TXT'
  end;
end;

function TACBrCargaBal.GetNomeArquivoNutricional: String;
begin
  // A filizola e urano nao possuem arquivo nutricional a parte das informa��es
  // s�o inclu�das no mesmo arquivo de itens.
  case FModelo of
    modToledo, modToledoMGV5 : Result := 'INFNUTRI.TXT';
  end;
end;

function TACBrCargaBal.CalcularSoma(const xStr: string): Integer;
var
  I, Vl: Integer;
begin
  result:=0;
  Vl:=0;
  if Length(xStr)<1 then
    exit;
  for I:=1 to Length(xStr) do
  begin
    Vl:=Vl+Ord(xStr[I]);
  end;
  result:=Vl;
end;

procedure TACBrCargaBal.PreencherFilizola(Arquivo, Setor: TStringList);
var
  i, Total: Integer;
begin
  Total := Produtos.Count;

  for i := 0 to Total - 1 do
  begin
    Arquivo.Add(
      LFIll(Produtos[i].Codigo, 6) +
      GetTipoProdutoFilizola(Produtos[i].Tipo) +
      RFIll(Produtos[i].Descricao, 22) +
      LFIll(Produtos[i].ValorVenda, 7, 2) +
      LFIll(Produtos[i].Validade, 3)
    );

    Setor.Add(
      RFill(Produtos[i].Setor.Descricao, 12) +
      LFIll(Produtos[i].Codigo, 6) +
      LFIll(i + 1, 4) +
      LFill(Produtos[i].Tecla, 3)
    );

    Progresso(Format('Gerando produto %6.6d %s', [Produtos[i].Codigo, Produtos[i].Descricao]), i, Total);
  end;
end;

procedure TACBrCargaBal.PreencherToledo(Arquivo, Nutricional, Receita: TStringList; versao:integer);
var
  i, Total: Integer;
  Anutri,areceita:string;
begin
  Total := Produtos.Count;

  for i := 0 to Total - 1 do
  begin
    if Versao=0 then
       begin
    Arquivo.Add(
      LFIll(Produtos[i].Setor.Codigo, 2) +
      LFIll(Produtos[i].ModeloEtiqueta, 2) +
      GetTipoProdutoToledo(Produtos[i].Tipo) +
      LFIll(Produtos[i].Codigo, 6) +
      LFIll(Produtos[i].ValorVenda, 6, 2) +
      LFIll(Produtos[i].Validade, 3) +
      RFIll(Produtos[i].Descricao, 50) +
      RFIll(Produtos[i].Receita, 250)
    );
    end else
    begin

    Arquivo.Add(
      LFIll(Produtos[i].Setor.Codigo, 2) +
      GetTipoProdutoToledo(Produtos[i].Tipo) +
      LFIll(Produtos[i].Codigo, 6) +
      LFIll(Produtos[i].ValorVenda, 6, 2) +
      LFIll(Produtos[i].Validade, 3) +
      RFIll(Produtos[i].Descricao, 50) +
      LFIll(Produtos[i].Codigo,6)+ // codigo inf extra
      LFIll('0',4)+ // codigo imagem
      LFIll(Produtos[i].Nutricional.Codigo,6)+ // codigo inf nutricional
      RFill('1',1)+ // imprime data de validade
      RFill('1',1)+ // imprime data embalagem
      LFIll('0',4)+ // codigo fornecedor
      lFill('0',12)+ // lote
      lFill('0',11)+ // codigo especial
      LFIll('0',1)+ // versao do preco
      LFIll('0',4)+ // codigo do som
      LFIll('0',4)+ // codigo da tara
      LFIll('0',4)+ // codigo da fracionador
      LFIll('0',4)+ // ce1
      LFIll('0',4)+ // ce2
      LFIll('0',4)+ // cons
      LFIll('0',12)
    );

    // receita
    areceita:=LFIll(Produtos[i].Codigo,6)+RFill('',100)+RFill(Produtos[i].Receita,840);
    if (Length(Produtos[i].Receita)>2) and (Receita.IndexOf(areceita)<0) then
       Receita.Add(areceita);

    Anutri:= 'N'+ LFIll(Produtos[i].Nutricional.Codigo,6)+'0'+LFIll(Produtos[i].FNutricional.Qtd,3)+
    GetNutriUndPorcaoToledo(Produtos[i].Nutricional.UndPorcao)+
    LFIll(Produtos[i].Nutricional.FPartInteira,2)+
    GetNutriPartDecimalToledo(Produtos[i].Nutricional.PartDecimal)+
    GetNutriMedCaseiraToledo(Produtos[i].Nutricional.MedCaseira)+
    LFIll(Produtos[i].Nutricional.ValorEnergetico,4)+
    LFIll(Produtos[i].Nutricional.Carboidrato,4,1)+
    LFIll(Produtos[i].Nutricional.Proteina,3,1)+
    LFIll(Produtos[i].Nutricional.GorduraTotal,3,1)+
    LFIll(Produtos[i].Nutricional.GorduraSaturada,3,1)+
    LFIll(Produtos[i].Nutricional.GorduraTrans,3,1)+
    LFIll(Produtos[i].Nutricional.Fibra,3,1)+
    LFIll(Produtos[i].Nutricional.Sodio,5,1);

    if (Produtos[i].Nutricional.Codigo>0) and (Nutricional.IndexOf(Anutri)<0) then
       Nutricional.Add(Anutri);

    end;

    Progresso(Format('Gerando produto %6.6d %s', [Produtos[i].Codigo, Produtos[i].Descricao]), i, Total);
  end;
end;

procedure TACBrCargaBal.PreencherUrano(Arquivo: TStringList);
var
  i, Total, xtam: Integer;
  xnutric: string;
begin
  //modelo do arquivo: serve somente para as novas balan�as urano (linha top e topmax)
  //0x10+0x02+codigo[5]+pesagem[35]+chksum[4]+0x03+0x13+0x10
                //      pesagem[35]=tipoproduto[1]+descricao[20]+preco[9]+validade[4]+tipovalidade[1]

  Total := Produtos.Count;

  for i := 0 to Total - 1 do
  begin
    //linha do produto
    Arquivo.Add(#10#02 +
      LFIll(Produtos[i].Codigo, 5) +
      GetTipoProdutoUrano(Produtos[i].Tipo) +
      RFIll(Produtos[i].Descricao, 20) +
      FormatCurr('000000.00', Produtos[i].ValorVenda) +
      LFIll(Produtos[i].Validade, 4) +
      'D'
    );
    xtam := CalcularSoma(Arquivo[Arquivo.Count-1]);
    Arquivo[Arquivo.Count-1] := Arquivo[Arquivo.Count-1] + IntToHex(xtam, 4) + #03;

    if Produtos[i].Nutricional.FQtd>0 then
    begin
      //linha da informa��o nutricional
      //0x11+0x02+codigo[5]+pesagem[35]+informacoes nutricionais[258]+chksum[4]+0x03+0x13+0x10
                                      //informacoes nutricionais[258]=1 linha de 41 caracteres para por��o
                                      // e 8 linhas de 21 caracteres para [calorias,carboidratos,prote�nas,gorduras totais,
                                      //                                   gorduras saturadas,gordura trans,fibra alimentar,
                                      //                                   s�dio].

      Arquivo.Add(#11#02 +
        LFIll(Produtos[i].Codigo, 5) +
        GetTipoProdutoUrano(Produtos[i].Tipo) +
        RFIll(Produtos[i].Descricao, 20) +
        FormatCurr('000000.00', Produtos[i].ValorVenda) +
        LFIll(Produtos[i].Validade, 4) +
        'D' +
        RFIll('' {Produtos[i].Nutricional.FUndPorcao}, 209)
        );

      xtam := CalcularSoma(Arquivo[Arquivo.Count-1]);
      Arquivo[Arquivo.Count-1] := Arquivo[Arquivo.Count-1] + IntToHex(xtam, 4) + #03;
    end;

    if Produtos[i].Receita <> '' then
    begin
    //linha da receita
    //0x12+0x02+codigo[5]+pesagem[35]+informacoes adicionais[615]+chksum[4]+0x03+0x13+0x10
                                    //informacoes adicionais[615]=15 linhas de 41 caracteres.

      xnutric:='';

      if Produtos[i].Nutricional.FQtd<=0 then
        xnutric := RFill('', 209);

      Arquivo.Add(#12#02 +
        LFIll(Produtos[i].Codigo, 5) +
        GetTipoProdutoUrano(Produtos[i].Tipo) +
        RFIll(Produtos[i].Descricao, 20) +
        FormatCurr('000000.00', Produtos[i].ValorVenda) +
        LFIll(Produtos[i].Validade, 4) +
        'D' +
        xnutric +
        RFIll(Produtos[i].Receita, 615)
        );

      xtam := CalcularSoma(Arquivo[Arquivo.Count-1]);
      Arquivo[Arquivo.Count-1] := Arquivo[Arquivo.Count-1] + IntToHex(xtam, 4) + #03;
    end;

    Progresso(Format('Gerando produto %6.6d %s', [Produtos[i].Codigo, Produtos[i].Descricao]), i, Total);
  end;
end;

procedure TACBrCargaBal.Progresso(const AMensagem: String; const AContAtual,
  AContTotal: Integer);
begin
  if Assigned(FOnProgresso) then
    FOnProgresso(AMensagem, AContAtual, AContTotal);
end;

procedure TACBrCargaBal.GerarArquivos(const ADiretorio: String);
var
  Produto, Setor, Receita, Nutricional: TStringList;
  NomeArquivo: TFileName;
  Total: integer;
begin
  if Trim(ADiretorio) = EmptyStr then
    raise EACBrCargaBal.Create('Informe o diret�rio onde ser�o gerados os arquivos de carga!');

  if not DirectoryExists(ADiretorio) then
    raise EACBrCargaBal.Create('Diretorio informado n�o existe!');

  if Self.Produtos.Count = 0 then
    raise EACBrCargaBal.Create('N�o foram informados os produtos para a gera��o!');

  Produto := TStringList.Create;
  Produto.Clear;

  Setor := TStringList.Create;
  Setor.Clear;

  Receita := TStringList.Create;
  Receita.Clear;

  Nutricional := TStringList.Create;
  Nutricional.Clear;

  try
    Total := Self.Produtos.Count;
    Progresso('Iniciando a gera��o dos arquivos', 0, Total);

    // Varre os registros gerando o arquivo em lista
    case FModelo of
      modFilizola: PreencherFilizola(Produto, Setor);
      modToledo  : PreencherToledo(Produto, Nutricional, Receita);
      modUrano   : PreencherUrano(Produto);
      modToledoMGV5  : PreencherToledo(Produto, Nutricional, Receita ,1);
    end;

    // Monta o nome do arquivo de produtos seguindo o padrao da balanca
    if Produto.Count > 0 then
    begin
      NomeArquivo := IncludeTrailingPathDelimiter(ADiretorio) + GetNomeArquivoProduto;
      Produto.SaveToFile(NomeArquivo);
    end;

    // Gerar arquivo de setores se houverem dados e o arquivo for separado
    if Setor.Count > 0 then
    begin
      NomeArquivo := IncludeTrailingPathDelimiter(ADiretorio) + GetNomeArquivoSetor;
      Setor.SaveToFile(NomeArquivo);
    end;

    // Gerar arquivo de receitas se houverem dados e o arquivo for separado
    if Receita.Count > 0 then
    begin
      NomeArquivo := IncludeTrailingPathDelimiter(ADiretorio) + GetNomeArquivoReceita;
      Receita.SaveToFile(NomeArquivo);
    end;

    // Gerar arquivo de Nutricionais se houverem dados e o arquivo for separado
    if Nutricional.Count > 0 then
    begin
      NomeArquivo := IncludeTrailingPathDelimiter(ADiretorio) + GetNomeArquivoNutricional;
      Nutricional.SaveToFile(NomeArquivo);
    end;

    Progresso('Terminado', Total, Total);
  finally
    FreeAndNil(Produto);
    FreeAndNil(Setor);
    FreeAndNil(Receita);
    FreeAndNil(Nutricional);
  end;
end;

function TACBrCargaBal.GetModeloStr: string;
begin
 case fModelo of
   modFilizola : result := 'Filizola';
   modToledo : result := 'Toledo';
   modUrano : result := 'Urano';
 end;
end;

end.
