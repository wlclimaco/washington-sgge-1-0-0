{******************************************************************************}
{ Projeto: TACBrConvenio115                                                    }
{                                                                              }
{ Fun��o: Gerar arquivo magn�tico para Notas Fiscais emitidas referentes aos   }
{         modelos abaixo relacionados, conforme estabelecido no Conv�nio       }
{         ICMS 115/03:                                                         }
{         - Nota Fiscal/Conta de Energia El�trica, modelo 6;                   }
{         - Nota Fiscal de Servi�o de Comunica��o, modelo 21;                  }
{         - Nota Fiscal de Servi�o de Telecomunica��es, modelo 22;             }
{         - Qualquer outro documento fiscal relativo � presta��o de servi�o de }
{           comunica��o ou ao fornecimento de energia el�trica.                }
{                                                                              }
{         As informa��es do referido conv�nio pode ser encontrada em:          }
{         www.fazenda.gov.br/confaz/confaz/convenios/icms/2003/CV115_03.htm    }
{                                                                              }
{         Programa validador e demais informa��es podem ser acessadas em:      }
{         www.fazenda.sp.gov.br/download/comunica_energia.shtm                 }
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
{******************************************************************************}

{******************************************************************************}
{ Direitos Autorais Reservados � 2013 - J�ter Rabelo Ferreira                  }
{ Contato: jeter.rabelo@jerasoft.com.br                                        }
{******************************************************************************}

{*******************************************************************************
|* Historico
|*
|* 19/02/2013: J�ter Rabelo Ferreira
|*  - Disponibiliza��o do componente para o projeto ACBr
*******************************************************************************}

unit ACBrConvenio115;

interface

uses
  SysUtils, Classes, Contnrs, ACBrBase, ACBrConsts, StrUtils;

{$R ACBrConvenio115.dcr}

type
  TTipoAssinanteConv115_Tab11_1 = (
    tac111None,
    tac111ComercialIndustrial,
    tac111PoderPublico,
    tac111ResidencialPessoaFisica,
    tac111Publico,
    tac111SemiPublico,
    tac111Outros);

  TProdutoConv115_Tab11_2 = (
    pc112None,
    pc112Telefonia,
    pc112ComunicacaoDados,
    pc112TVAssinatura,
    pc112ProvimentoAcessoInternet,
    pc112Multimidia,
    pc112Outros);

  TSituacaoNFConv115 = (
    stcv115Normal,
    stcv115Cancelado,
    stcv115Substituido);

  TStatusArquivoConv115 = (scv115Normal, scv115Substituto);

  TConvenio115AssinaturaMD5 = record
    Registro: string;
    Assinatura: string;
  end;

  TACBrConvenio115Item = class
  private
    /// //////////////////////////////////////////////////////
    // Vari�veis ser�o preenchidas pelo conte�do do MESTRE //
    /// //////////////////////////////////////////////////////
    FCnpjCpf: string;
    FUF: string;
    FDataEmissao: TDateTime;
    FModelo: SmallInt;
    FSerie: string;
    FNumeroNF: Integer;
    FReferenciaItemNF: Integer;
    FTipoAssinante: TTipoAssinanteConv115_Tab11_1;
    FTipoUtilizacao: TProdutoConv115_Tab11_2;
    /////////////////////////////////////////////////////////
    FCFOP: string;
    FItem: Integer;
    FCodigoServico: string;
    FDescricaoServico: string;
    FClassificacaoItem: string;
    FUnidade: string;
    FQtdeContratada: Currency;
    FQtdePrestada: Currency;
    FValorTotal: Currency;
    FDesconto: Currency;
    FAcrescimosDespAcessorias: Currency;
    FICMSBaseCalculo: Currency;
    FICMSValor: Currency;
    FIsentoNaoTributados: Currency;
    FOutrosValores: Currency;
    FICMSAliquota: Currency;
    FSituacao: TSituacaoNFConv115;
    FAnoMesApuracao: string;
    function GetGrupoTensao: string;
    procedure SetClassificacaoItem(const Value: string);
  public
    property GrupoTensao: string read GetGrupoTensao;
    property CFOP: string read FCFOP write FCFOP;
    property Item: Integer read FItem write FItem;
    property CodigoServico: string read FCodigoServico write FCodigoServico;
    property DescricaoServico: string read FDescricaoServico write FDescricaoServico;
    property ClassificacaoItem: string read FClassificacaoItem write SetClassificacaoItem;
    property Unidade: string read FUnidade write FUnidade;
    property QtdeContratada: Currency read FQtdeContratada write FQtdeContratada;
    property QtdePrestada: Currency read FQtdePrestada write FQtdePrestada;
    property ValorTotal: Currency read FValorTotal write FValorTotal;
    property Desconto: Currency read FDesconto write FDesconto;
    property AcrescimosDespAcessorias: Currency read FAcrescimosDespAcessorias write FAcrescimosDespAcessorias;
    property ICMSBaseCalculo: Currency read FICMSBaseCalculo write FICMSBaseCalculo;
    property ICMSValor: Currency read FICMSValor write FICMSValor;
    property ICMSAliquota: Currency read FICMSAliquota write FICMSAliquota;
    property IsentoNaoTributados: Currency read FIsentoNaoTributados write FIsentoNaoTributados;
    property OutrosValores: Currency read FOutrosValores write FOutrosValores;
    property Situacao: TSituacaoNFConv115 read FSituacao write FSituacao;
    property AnoMesApuracao: string read FAnoMesApuracao write FAnoMesApuracao;
    function RegistroEAssinatura: TConvenio115AssinaturaMD5;
  end;

  { Lista de objetos do tipo TConvenio115Mestre }
  TACBrConvenio115Items = class(TObjectList)
  protected
    procedure SetObject(Index: Integer; Item: TACBrConvenio115Item);
    function GetObject(Index: Integer): TACBrConvenio115Item;
    procedure Insert(Index: Integer; Obj: TACBrConvenio115Item);
  public
    function Add(Obj: TACBrConvenio115Item): Integer;
    property Objects[Index: Integer]: TACBrConvenio115Item read GetObject write SetObject; default;
  end;

  TACBrConvenio115Destinatario = class
  private
    FCnpjCpf: string;
    FInscricaoEstadual: string;
    FRazaoSocial: string;
    FLogradouro: string;
    FNumero: string;
    FComplemento: string;
    FUF: string;
    FCep: string;
    FBairro: string;
    FMunicipio: string;
    FTelefone: string;
    FCodigoConsumidor: string;
    procedure SetCnpjCpf(const Value: string);
  public
    property CnpjCpf: string read FCnpjCpf write SetCnpjCpf;
    property InscricaoEstadual: string read FInscricaoEstadual write FInscricaoEstadual;
    property RazaoSocial: string read FRazaoSocial write FRazaoSocial;
    property Logradouro: string read FLogradouro write FLogradouro;
    property Numero: string read FNumero write FNumero;
    property Complemento: string read FComplemento write FComplemento;
    property CEP: string read FCep write FCep;
    property Bairro: string read FBairro write FBairro;
    property Municipio: string read FMunicipio write FMunicipio;
    property UF: string read FUF write FUF;
    property Telefone: string read FTelefone write FTelefone;
    property CodigoConsumidor: string read FCodigoConsumidor write FCodigoConsumidor;
  end;

  TACBrConvenio115Mestre = class
  private
    FTipoAssinante: TTipoAssinanteConv115_Tab11_1;
    FTipoUtilizacao: TProdutoConv115_Tab11_2;
    FDataEmissao: TDateTime;
    FModelo: SmallInt;
    FSerie: string;
    FNumeroNF: Integer;
    FValorTotal: Currency;
    FICMS_BaseCalculo: Currency;
    FICMS_Valor: Currency;
    FIsentosNaoTributadas: Currency;
    FOutrosValores: Currency;
    FSituacaoDocumento: TSituacaoNFConv115;
    FAnoMesRefencia: string;
    FNumeroTerminalTelefonico: string;
    FUFTerminalTelefonico: string;
    FDestinatario: TACBrConvenio115Destinatario;
    FDetalhes: TACBrConvenio115Items;
    FReferenciaItemNF: Integer;
    function GetGrupoTensao: string;
    function GetAutenticacaoDocumentoFiscal: string;
    procedure SetTipoAssinante(const Value: TTipoAssinanteConv115_Tab11_1);
    procedure SetTipoUtilizacao(const Value: TProdutoConv115_Tab11_2);
  public
    constructor Create;
    destructor Destroy; override;
    property Destinatario: TACBrConvenio115Destinatario read FDestinatario write FDestinatario;
    property TipoAssinante: TTipoAssinanteConv115_Tab11_1 read FTipoAssinante write SetTipoAssinante;
    property TipoUtilizacao: TProdutoConv115_Tab11_2 read FTipoUtilizacao write SetTipoUtilizacao;
    property GrupoTensao: string read GetGrupoTensao;
    property DataEmissao: TDateTime read FDataEmissao write FDataEmissao;
    property Modelo: SmallInt read FModelo write FModelo;
    property Serie: string read FSerie write FSerie;
    property NumeroNF: Integer read FNumeroNF write FNumeroNF;
    property AutenticacaoDocumentoFiscal: string read GetAutenticacaoDocumentoFiscal; // Imprimir na Nota Fiscal
    property ValorTotal: Currency read FValorTotal write FValorTotal;
    property ICMS_BaseCalculo: Currency read FICMS_BaseCalculo write FICMS_BaseCalculo;
    property ICMS_Valor: Currency read FICMS_Valor write FICMS_Valor;
    property IsentosNaoTributadas: Currency read FIsentosNaoTributadas write FIsentosNaoTributadas;
    property OutrosValores: Currency read FOutrosValores write FOutrosValores;
    property SituacaoDocumento: TSituacaoNFConv115 read FSituacaoDocumento write FSituacaoDocumento;
    property AnoMesRefencia: string read FAnoMesRefencia write FAnoMesRefencia;
    property ReferenciaItemNF: Integer read FReferenciaItemNF;
    property NumeroTerminalTelefonico: string read FNumeroTerminalTelefonico write FNumeroTerminalTelefonico;
    property UFTerminalTelefonico: string read FUFTerminalTelefonico write FUFTerminalTelefonico;
    property Detalhes: TACBrConvenio115Items read FDetalhes;
    function RegistroEAssinatura: TConvenio115AssinaturaMD5;
  end;

  { Lista de objetos do tipo TConvenio115Mestre }
  TACBrConvenio115Mestres = class(TObjectList)
  protected
    procedure SetObject(Index: Integer; Item: TACBrConvenio115Mestre);
    function GetObject(Index: Integer): TACBrConvenio115Mestre;
    procedure Insert(Index: Integer; Obj: TACBrConvenio115Mestre);
  public
    function Add(Obj: TACBrConvenio115Mestre): Integer;
    property Objects[Index: Integer]: TACBrConvenio115Mestre read GetObject write SetObject; default;
  end;

  TACBrConvenio115Responsavel = class(TPersistent)
  private
    FEMail: string;
    FCargo: string;
    FResponsavel: string;
  public
    constructor Create(AOwner: TComponent);
  published
    property Nome: string read FResponsavel write FResponsavel;
    property Cargo: string read FCargo write FCargo;
    property EMail: string read FEMail write FEMail;
  end;

  TConvenio115TipoArquivo = (c115taMestre, c115taitem, c115taDestinatario);

  TACBrConvenio115 = class(TComponent)
  private
    FSalvarEm: string;
    FMestre: TACBrConvenio115Mestres;
    FUF: string;
    FSerie: string;
    FAno: SmallInt;
    FMes: SmallInt;
    FStatus: TStatusArquivoConv115;
    FResponsavel: TACBrConvenio115Responsavel;
    function GetVersao: string;
    procedure SetSalvarEm(const Value: string);
    procedure DoGerarMestre;
    procedure DoGerarItem;
    procedure DoGerarDestinatario;
    function DoNomeArquivo(TipoArquivo: TConvenio115TipoArquivo): string;
  public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure Clear;
    procedure Gerar;
    property Mestre: TACBrConvenio115Mestres read FMestre;
  published
    property Versao: string read GetVersao;
    property SalvarEm: string read FSalvarEm write SetSalvarEm;
    property UF: string read FUF write FUF;
    property Serie: string read FSerie write FSerie;
    property Ano: SmallInt read FAno write FAno;
    property Mes: SmallInt read FMes write FMes;
    property Status: TStatusArquivoConv115 read FStatus write FStatus;
    property Responsavel: TACBrConvenio115Responsavel read FResponsavel write FResponsavel;
  end;

function SortMestre(Item1: Pointer; Item2: Pointer): Integer;

procedure Register;

const
  TTipoAssinanteConv115_Tab11_1DS: array [0 .. 6] of string =
    ('Sem Classifica��o',
    'Comercial / Industrial',
    'Poder P�blico',
    'Residencial / Pessoa F�sica',
    'P�blico',
    'Semi-P�blico',
    'Outros');

  TProdutoConv115_Tab11_2DS: array [0 .. 6] of string =
    ('Sem Classifica��o',
    'Telefonia',
    'Comunica��o de dados',
    'TV por Assinatura',
    'Provimento de acesso � Internet',
    'Multim�dia',
    'Outros');

  TSituacaoNFConv115DS: array [0 .. 2] of string =
    ('Normal',
    'Cancelado',
    'Substitu�do');

  TSituacaoNFConv115ID: array [0 .. 2] of string =
    ('N',
    'S',
    'R');

implementation

uses
  ACBrUtil, ACBrEAD, Variants, ACBrValidador;

procedure Register;
begin
  RegisterComponents('ACBr', [TACBrConvenio115]);
end;

function MD5String(const texto: string): string;
var
  MD5: TACBrEAD;
begin
  MD5 := TACBrEAD.Create(nil);
  try
    Result := UpperCase(MD5.MD5FromString(texto));
  finally
    FreeAndNil(MD5);
  end;
end;

{ TConvenio115Mestre }

constructor TACBrConvenio115Mestre.Create;
begin
  inherited;
  FDestinatario := TACBrConvenio115Destinatario.Create;
  FDetalhes := TACBrConvenio115Items.Create;
end;

destructor TACBrConvenio115Mestre.Destroy;
begin
  FreeAndNil(FDetalhes);
  FreeAndNil(FDestinatario);
  inherited;
end;

function TACBrConvenio115Mestre.GetAutenticacaoDocumentoFiscal: string;
begin
  Result := MD5String(
    padR(OnlyNumber(Destinatario.CnpjCpf), 14, '0') + { 01 - CNPJ/CPF }
    padR(IntToStr(FNumeroNF), 9, '0') + { 12 - Numero NF }
    padR(TiraPontos(FormatFloat('#,##0.00', FValorTotal)), 12, '0') + { 14 - Valor }
    padR(TiraPontos(FormatFloat('#,##0.00', ICMS_BaseCalculo)), 12, '0') + { 15 - Base ICMS }
    padR(TiraPontos(FormatFloat('#,##0.00', ICMS_Valor)), 12, '0') { 16 - Valor ICMS }
    );
end;

function TACBrConvenio115Mestre.GetGrupoTensao: string;
begin
  Result := '00';
end;

function TACBrConvenio115Mestre.RegistroEAssinatura: TConvenio115AssinaturaMD5;
begin
  Result.Registro :=
    {01} padR(OnlyNumber(Destinatario.CnpjCpf), 14, '0') +
    {02} padL(IfThen(OnlyNumber(Destinatario.InscricaoEstadual) = '', 'ISENTO', OnlyNumber(Destinatario.InscricaoEstadual)), 14) +
    {03} padL(TiraAcentos(Destinatario.RazaoSocial), 35) +
    {04} padL(UpperCase(Destinatario.UF), 2) +
    {05} IntToStr(Ord(TipoAssinante)) +
    {06} IntToStr(Ord(TipoUtilizacao)) +
    {07} GrupoTensao +
    {08} padL(Destinatario.CodigoConsumidor, 12) +
    {09} DtoS(DataEmissao) +
    {10} padR(IntToStr(Modelo), 2, '0') +
    {11} padR(Serie, 3) +
    {12} padR(IntToStr(NumeroNF), 9, '0') +
    {13} AutenticacaoDocumentoFiscal +
    {14} padR(TiraPontos(FormatFloat('#,##0.00', ValorTotal)), 12, '0') +
    {15} padR(TiraPontos(FormatFloat('#,##0.00', ICMS_BaseCalculo)), 12, '0') +
    {16} padR(TiraPontos(FormatFloat('#,##0.00', ICMS_Valor)), 12, '0') +
    {17} padR(TiraPontos(FormatFloat('#,##0.00', IsentosNaoTributadas)), 12, '0') +
    {18} padR(TiraPontos(FormatFloat('#,##0.00', OutrosValores)), 12, '0') +
    {19} TSituacaoNFConv115ID[Ord(SituacaoDocumento)] +
    {20} AnoMesRefencia +
    {21} padR(IntToStr(ReferenciaItemNF), 9, '0') +
    {22} padL(NumeroTerminalTelefonico, 12) +
    {23} padL('', 5);
  Result.Assinatura := MD5String(Result.Registro);
end;

procedure TACBrConvenio115Mestre.SetTipoAssinante(
  const Value: TTipoAssinanteConv115_Tab11_1);
begin
  if Value = tac111None then
    raise Exception.Create('Tipo de assinante inv�lido!');
  FTipoAssinante := Value;
end;

procedure TACBrConvenio115Mestre.SetTipoUtilizacao(
  const Value: TProdutoConv115_Tab11_2);
begin
  if Value = pc112None then
    raise Exception.Create('Tipo de utiliza��o inv�lido!');
  FTipoUtilizacao := Value;
end;

{ TConvenio115Mestres }

function TACBrConvenio115Mestres.Add(Obj: TACBrConvenio115Mestre): Integer;
begin
  Result := inherited Add(Obj);
end;

function TACBrConvenio115Mestres.GetObject(Index: Integer): TACBrConvenio115Mestre;
begin
  Result := inherited GetItem(Index) as TACBrConvenio115Mestre;
end;

procedure TACBrConvenio115Mestres.Insert(Index: Integer; Obj: TACBrConvenio115Mestre);
begin
  inherited SetItem(Index, Obj);
end;

procedure TACBrConvenio115Mestres.SetObject(Index: Integer;
  Item: TACBrConvenio115Mestre);
begin
  inherited SetItem(Index, Item);
end;

function SortMestre(Item1, Item2: Pointer): Integer;
var
  OItem1, OItem2: TACBrConvenio115Mestre;
begin
  OItem1 := TACBrConvenio115Mestre(Item1);
  OItem2 := TACBrConvenio115Mestre(Item2);
  if (OItem1.NumeroNF > OItem2.NumeroNF) and
     (OItem1.NumeroNF > OItem2.NumeroNF) then
    Result := 1
  else if (OItem1.NumeroNF = OItem2.NumeroNF) and
          (OItem1.NumeroNF = OItem2.NumeroNF) then
    Result := 0
  else
    Result := -1;
end;

{ TConvenio115 }

procedure TACBrConvenio115.Clear;
begin
  FMestre.Clear;
end;

constructor TACBrConvenio115.Create(AOwner: TComponent);
begin
  inherited;
  FMestre := TACBrConvenio115Mestres.Create;
  FResponsavel := TACBrConvenio115Responsavel.Create(Self);
end;

destructor TACBrConvenio115.Destroy;
begin
  Clear;
  FResponsavel.Free;
  FMestre.Free;
  inherited;
end;

procedure TACBrConvenio115.DoGerarDestinatario;
var
  OStr: TStringList;
  I: Integer;
  RRegistro: TConvenio115AssinaturaMD5;
begin
  OStr := TStringList.Create;
  try
    for I := 0 to FMestre.Count - 1 do
    begin
      RRegistro.Registro :=
        {01} PadR(OnlyNumber(FMestre[I].Destinatario.CnpjCpf), 14, '0') +
        {02} padL(IfThen(OnlyNumber(FMestre[I].Destinatario.InscricaoEstadual) = '', 'ISENTO', OnlyNumber(FMestre[I].Destinatario.InscricaoEstadual)), 14) +
        {03} padL(TiraAcentos(FMestre[I].Destinatario.RazaoSocial), 35) +
        {04} padL(TiraAcentos(FMestre[I].Destinatario.Logradouro), 45) +
        {05} padR(OnlyNumber(FMestre[I].Destinatario.Numero), 5, '0') +
        {06} padL(TiraAcentos(FMestre[I].Destinatario.Complemento), 15) +
        {07} padR(OnlyNumber(FMestre[I].Destinatario.CEP), 8, '0') +
        {08} padL(TiraAcentos(FMestre[I].Destinatario.Bairro), 15) +
        {09} padL(TiraAcentos(FMestre[I].Destinatario.Municipio), 30) +
        {10} padL(UpperCase(FMestre[I].Destinatario.UF), 2) +
        {11} padR(OnlyNumber(FMestre[I].Destinatario.Telefone), 12, '0') +
        {12} padL(FMestre[I].Destinatario.CodigoConsumidor, 12) +
        {13} padL(FMestre[I].NumeroTerminalTelefonico, 12) +
        {14} padL(UpperCase(FMestre[I].UFTerminalTelefonico), 2) +
        {15} padL('', 5);

      RRegistro.Assinatura := MD5String(RRegistro.Registro);
      OStr.Add(RRegistro.Registro + RRegistro.Assinatura);
    end;
    OStr.SaveToFile(DoNomeArquivo(c115taDestinatario));
  finally
    FreeAndNil(OStr);
  end;
end;

procedure TACBrConvenio115.DoGerarItem;
var
  I: Integer;
  A: Integer;
  OStr: TStringList;
begin
  OStr := TStringList.Create;
  try
    for I := 0 to FMestre.Count - 1 do
    begin
      for A := 0 to FMestre[I].Detalhes.Count - 1 do
      begin
        OStr.Add(FMestre[I].Detalhes[A].RegistroEAssinatura.Registro +
          FMestre[I].Detalhes[A].RegistroEAssinatura.Assinatura);
      end;
    end;
    OStr.SaveToFile(DoNomeArquivo(c115taitem));
  finally
    OStr.Free;
  end;
end;

procedure TACBrConvenio115.DoGerarMestre;
var
  I: Integer;
  OStr: TStringList;
begin
  Mestre.Sort(SortMestre);
  OStr := TStringList.Create;
  try
    for I := 0 to FMestre.Count - 1 do
    begin
      OStr.Add(FMestre[I].RegistroEAssinatura.Registro +
        FMestre[I].RegistroEAssinatura.Assinatura);
    end;
    OStr.SaveToFile(DoNomeArquivo(c115taMestre));
  finally
    OStr.Free;
  end;
end;

function TACBrConvenio115.DoNomeArquivo(TipoArquivo: TConvenio115TipoArquivo): string;
begin
  { Composi��o do nome do arquivo:

    Nome do Arquivo                         |   |  Extens�o
    U F | S S S | A A | M M |   ST   |  T   | . | V V V
    UF  | s�rie | ano | m�s | Status | tipo | . | volume
  }
  Result :=
    FUF +
    padR(Serie, 3, '0') +
    Copy(IntToStr(Ano), 3, 2) +
    padR(IntToStr(Mes), 2, '0') +
    IfThen(Status = scv115Normal, 'N', 'S');

  case TipoArquivo of
    c115taMestre: Result := Result + 'M';
    c115taitem: Result := Result + 'I';
    c115taDestinatario:  Result := Result + 'D';
  end;

  Result := SalvarEm + Result + '.001'; // Pode ter 1 milh�o de registros
end;

procedure TACBrConvenio115.Gerar;
var
  I: Integer;
  A: Integer;
  ICount: Integer;
begin
  if FSalvarEm = '' then
    raise Exception.Create('Pasta n�o informada para gravar os arquivos!');

  if FMestre.Count = 0 then
    raise Exception.Create('Nenhum registro Mestre informado!');

  for I := 0 to FMestre.Count - 1 do
  begin
    if FMestre[I].Detalhes.Count = 0 then
      raise Exception.Create('Detalhe n�o informado para a Nota Fiscal: ' + IntToStr(FMestre[I].NumeroNF) +
        ' - Cliente: ' + FMestre[I].Destinatario.CodigoConsumidor + '/' + FMestre[I].Destinatario.RazaoSocial);

    if FMestre[I].TipoAssinante = tac111None then
      raise Exception.Create('Tipo de assinante inv�lido para a Nota Fiscal: ' + IntToStr(FMestre[I].NumeroNF) +
        ' - Cliente: ' + FMestre[I].Destinatario.CodigoConsumidor + '/' + FMestre[I].Destinatario.RazaoSocial);

    if FMestre[I].TipoUtilizacao = pc112None then
      raise Exception.Create('Tipo de utiliza��o inv�lido para a Nota Fiscal: ' + IntToStr(FMestre[I].NumeroNF) +
        ' - Cliente: ' + FMestre[I].Destinatario.CodigoConsumidor + '/' + FMestre[I].Destinatario.RazaoSocial);
  end;

  // Contador para fazer a refer�ncia do �tem Mestre->Detalhe
  ICount := 1;

  // Atribuir os valores que s�o iguais na classe Detalhe
  for I := 0 to FMestre.Count - 1 do
  begin
    FMestre[I].FReferenciaItemNF := ICount;
    for A := 0 to FMestre[I].Detalhes.Count -1 do
    begin
      FMestre[I].Detalhes[A].FTipoAssinante := FMestre[I].TipoAssinante;
      FMestre[I].Detalhes[A].FTipoUtilizacao := FMestre[I].TipoUtilizacao;
      FMestre[I].Detalhes[A].FCnpjCpf := FMestre[I].Destinatario.CnpjCpf;
      FMestre[I].Detalhes[A].FUF := FMestre[I].Destinatario.UF;
      FMestre[I].Detalhes[A].FDataEmissao := FMestre[I].DataEmissao;
      FMestre[I].Detalhes[A].FModelo := FMestre[I].Modelo;
      FMestre[I].Detalhes[A].FSerie := FMestre[I].Serie;
      FMestre[I].Detalhes[A].FNumeroNF := FMestre[I].NumeroNF;
      Inc(ICount);
    end;
  end;

  DoGerarMestre;
  DoGerarItem;
  DoGerarDestinatario;
end;

function TACBrConvenio115.GetVersao: string;
begin
  Result := '0.1.0.0';
end;

procedure TACBrConvenio115.SetSalvarEm(const Value: string);
begin
  FSalvarEm := IncludeTrailingPathDelimiter(Value);
end;

{ TConvenio115Responsavel }

constructor TACBrConvenio115Responsavel.Create(AOwner: TComponent);
begin
  inherited Create;
end;

{ TConvenio115Detalhe }

function TACBrConvenio115Item.GetGrupoTensao: string;
begin
  Result := '00';
end;

function TACBrConvenio115Item.RegistroEAssinatura: TConvenio115AssinaturaMD5;
begin
  Result.Registro :=
    {01} padR(OnlyNumber(FCnpjCpf), 14, '0') +
    {02} padL(UpperCase(FUF), 2) +
    {03} IntToStr(Ord(FTipoAssinante)) +
    {04} IntToStr(Ord(FTipoUtilizacao)) +
    {05} GrupoTensao +
    {06} DtoS(FDataEmissao) +
    {07} padR(IntToStr(FModelo), 2, '0') +
    {08} padR(FSerie, 3) +
    {09} padR(IntToStr(FNumeroNF), 9, '0') +
    {10} padR(CFOP, 4, '0') +
    {11} padR(IntToStr(Item), 3, '0') +
    {12} padL(CodigoServico, 10) +
    {13} padL(TiraAcentos(DescricaoServico), 40) +
    {14} padR(ClassificacaoItem, 4, '0') +
    {15} padL(Unidade, 6) +
    {16} padR(TiraPontos(FormatFloat('#,##0.000', QtdeContratada)), 11, '0') +
    {17} padR(TiraPontos(FormatFloat('#,##0.000', QtdePrestada)), 11, '0') +
    {18} padR(TiraPontos(FormatFloat('#,##0.00', ValorTotal)), 11, '0') +
    {19} padR(TiraPontos(FormatFloat('#,##0.00', Desconto)), 11, '0') +
    {20} padR(TiraPontos(FormatFloat('#,##0.00', AcrescimosDespAcessorias)), 11, '0') +
    {21} padR(TiraPontos(FormatFloat('#,##0.00', ICMSBaseCalculo)), 11, '0') +
    {22} padR(TiraPontos(FormatFloat('#,##0.00', ICMSValor)), 11, '0') +
    {23} padR(TiraPontos(FormatFloat('#,##0.00', IsentoNaoTributados)), 11, '0') +
    {24} padR(TiraPontos(FormatFloat('#,##0.00', OutrosValores)), 11, '0') +
    {25} padR(TiraPontos(FormatFloat('#,##0.00', ICMSAliquota)), 4, '0') +
    {26} TSituacaoNFConv115ID[Ord(Situacao)] +
    {27} AnoMesApuracao +
    {28} padR('', 5);
  Result.Assinatura := MD5String(Result.Registro);
end;

procedure TACBrConvenio115Item.SetClassificacaoItem(const Value: string);
  function StrInVarArray(Str: string; lista: Variant): Boolean;
  var
    I: Integer;
  begin
    Result := False;
    if VarIsArray(lista) then
    begin
      for I := 0 to VarArrayHighBound(lista, 1) do
        if lista[I] = Str then
        begin
          Result := True;
          Break;
        end;
    end
    else if not VarIsNull(lista) then
    begin
      if VarIsStr(lista) then
        Result := lista = Str;
    end;
  end;

begin
  FClassificacaoItem := '';
  if not StrInVarArray(Value, VarArrayOf([
    // Assinatura
    '0101', '0102', '0103', '0104', '0105', '0199',
    // Habilita��o
    '0201', '0202', '0203', '0204', '0205', '0299',
    // Servi�o Medido
    '0301', '0302', '0303', '0304', '0305', '0306', '0307', '0308', '0309', '0310', '0311', '0312', '0313', '0314', '0315', '0399',
    // Servi�o Pr�-pago
    '0401', '0402', '0403', '0404', '0405', '0406', '0407', '0499',
    // Outros servi�os
    '0501', '0502', '0599',
    // Energia El�trica
    '0601', '0602', '0603', '0604', '0605', '0606', '0607', '0608', '0609', '0610', '0699',
    // Disponibiliza��o de meios ou equipamento
    '0701', '0702', '0703', '0704', '0705', '0706', '0707', '0708', '0709', '0799',
    // Cobran�as
    '0801', '0802', '0803', '0804', '0805', '0806', '0807', '0808', '0899',
    // Dedu��es
    '0901', '0902', '0903', '0904', '0905', '0906', '0999',
    // Servi�o n�o medido
    '1001', '1002', '1003', '1004', '1005', '1099',
    // Cess�o de Meios de Rede
    '1101', '1102', '1103', '1104', '1199'
    ])) then
    raise Exception.Create('Classifica��o do �tem inv�lida');
  FClassificacaoItem := Value;
end;

{ TConvenio115Detalhes }

function TACBrConvenio115Items.Add(Obj: TACBrConvenio115Item): Integer;
begin
  Result := inherited Add(Obj);
end;

function TACBrConvenio115Items.GetObject(Index: Integer): TACBrConvenio115Item;
begin
  Result := inherited GetItem(Index) as TACBrConvenio115Item;
end;

procedure TACBrConvenio115Items.Insert(Index: Integer; Obj: TACBrConvenio115Item);
begin
  inherited SetItem(Index, Obj);
end;

procedure TACBrConvenio115Items.SetObject(Index: Integer;
  Item: TACBrConvenio115Item);
begin
  inherited SetItem(Index, Item);
end;

{ TConvenio115Destinatario }

procedure TACBrConvenio115Destinatario.SetCnpjCpf(const Value: string);
var
  OValidador: TACBrValidador;
begin
  OValidador := TACBrValidador.Create(nil);
  try
    with OValidador do
    begin
      if Length(OnlyNumber(Value)) <= 11 then
        TipoDocto := docCPF
      else
        TipoDocto := docCNPJ;
      Documento := Value;
      if not Validar then
        raise Exception.Create('CNPJ/CPF: ' + Value + ' inv�lido');
      FCnpjCpf := Value;
    end;
  finally
    OValidador.Free;
  end;
end;

end.
