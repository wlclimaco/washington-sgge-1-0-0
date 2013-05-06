unit Unit1;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, Mask, RLBoleto, ExtCtrls, ComCtrls, RLSaveDialog,
  RLFilters, RLPDFFilter, DateUtils, Math;

type
  TForm1 = class(TForm)
    RLBTitulo1: TRLBTitulo;
    Button2: TButton;
    Label1: TLabel;
    ComboBox1: TComboBox;
    Label2: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    Edit1: TEdit;
    Edit2: TEdit;
    Edit5: TEdit;
    Edit6: TEdit;
    Edit7: TEdit;
    Edit8: TEdit;
    Label6: TLabel;
    Label7: TLabel;
    Edit9: TEdit;
    Label9: TLabel;
    MaskEdit1: TMaskEdit;
    Edit3: TEdit;
    Label3: TLabel;
    Edit4: TEdit;
    Label10: TLabel;
    Label8: TLabel;
    Edit10: TEdit;
    Memo1: TMemo;
    Label11: TLabel;
    Edit11: TEdit;
    Label12: TLabel;
    ComboBox2: TComboBox;
    Edit12: TEdit;
    Label13: TLabel;
    Label14: TLabel;
    Edit13: TEdit;
    Label15: TLabel;
    Edit14: TEdit;
    Edit15: TEdit;
    Label16: TLabel;
    Edit16: TEdit;
    Label17: TLabel;
    CheckBox1: TCheckBox;
    Bevel1: TBevel;
    Label19: TLabel;
    MaskEdit2: TMaskEdit;
    RLPDFFilter1: TRLPDFFilter;
    Button1: TButton;
    RLBRemessa1: TRLBRemessa;
    Label18: TLabel;
    ComboBox3: TComboBox;
    procedure Button2Click(Sender: TObject);
    procedure FormShow(Sender: TObject);
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

function GeraBoleto(
    System_nQtdeBoletos,
    System_blPadrao:Integer;
    Cedente_DataProcessamento:TDate;
    Cedente_Banco_Codigo,
    Cedente_CodigoAgencia,
    Cedente_DigitoAgencia,
    Cedente_NumeroConta,
    Cedente_DigitoConta,
    Cedente_CodigoCedente,
    Cedente_DigitoCodigoCedente,
    Cedente_Carteira,
    Cedente_Convenio,
    Cedente_NomeCliente:String;
    Cedente_TipoInscricao:Integer;
    Cedente_NumeroCPFCGC,
    Cedente_Nome:String;
    //dados Sacado
    Sacado_Nome:String;
    Sacado_TipoInscricao:Integer;
    Sacado_NumeroCPFCGC,
    Sacado_Rua,
    Sacado_CEP,
    Sacado_Cidade,
    Sacado_Estado,
    //dados da cobranca
    Cobranca_NossoNumero:String;
    Cobranca_ValorDocumento:Currency;
    Cobranca_DataDocumento:TDate;
    Cobranca_DataVencimento:TDate;
    Cobranca_NumeroDocumento,
    Cobranca_InstrucoesText:String):String;stdcall;

function Remessa(
    System_nQtdeBoletos,
    System_blPadrao:Integer;
    System_diretorio:String;
    Cedente_DataProcessamento:TDate;
    Cedente_Banco_Codigo,
    Cedente_CodigoAgencia,
    Cedente_DigitoAgencia,
    Cedente_NumeroConta,
    Cedente_DigitoConta,
    Cedente_CodigoCedente,
    Cedente_DigitoCodigoCedente,
    Cedente_Carteira,
    Cedente_Convenio,
    Cedente_NomeCliente:String;
    Cedente_TipoInscricao:Integer;
    Cedente_NumeroCPFCGC,
    Cedente_Nome:String;
    //dados Sacado
    Sacado_Nome:String;
    Sacado_TipoInscricao:Integer;
    Sacado_NumeroCPFCGC,
    Sacado_Rua,
    Sacado_CEP,
    Sacado_Cidade,
    Sacado_Estado,
    //dados da cobranca
    Cobranca_NossoNumero:String;
    Cobranca_ValorDocumento:Currency;
    Cobranca_DataDocumento:TDate;
    Cobranca_DataVencimento:TDate;
    Cobranca_NumeroDocumento,
    Cobranca_InstrucoesText:String):String;stdcall;



var
  Form1: TForm1;

implementation

{$R *.dfm}
function GeraBoleto(
    System_nQtdeBoletos,
    System_blPadrao:Integer;
    Cedente_DataProcessamento:TDate;
    Cedente_Banco_Codigo,
    Cedente_CodigoAgencia,
    Cedente_DigitoAgencia,
    Cedente_NumeroConta,
    Cedente_DigitoConta,
    Cedente_CodigoCedente,
    Cedente_DigitoCodigoCedente,
    Cedente_Carteira,
    Cedente_Convenio,
    Cedente_NomeCliente:String;
    Cedente_TipoInscricao:Integer;
    Cedente_NumeroCPFCGC,
    Cedente_Nome:String;
    //dados Sacado
    Sacado_Nome:String;
    Sacado_TipoInscricao:Integer;
    Sacado_NumeroCPFCGC,
    Sacado_Rua,
    Sacado_CEP,
    Sacado_Cidade,
    Sacado_Estado,
    //dados da cobranca
    Cobranca_NossoNumero:String;
    Cobranca_ValorDocumento:Currency;
    Cobranca_DataDocumento:TDate;
    Cobranca_DataVencimento:TDate;
    Cobranca_NumeroDocumento,
    Cobranca_InstrucoesText:String):String;external 'Project2.dll';

function Remessa(
    System_nQtdeBoletos,
    System_blPadrao:Integer;
    System_diretorio:String;
    Cedente_DataProcessamento:TDate;
    Cedente_Banco_Codigo,
    Cedente_CodigoAgencia,
    Cedente_DigitoAgencia,
    Cedente_NumeroConta,
    Cedente_DigitoConta,
    Cedente_CodigoCedente,
    Cedente_DigitoCodigoCedente,
    Cedente_Carteira,
    Cedente_Convenio,
    Cedente_NomeCliente:String;
    Cedente_TipoInscricao:Integer;
    Cedente_NumeroCPFCGC,
    Cedente_Nome:String;
    //dados Sacado
    Sacado_Nome:String;
    Sacado_TipoInscricao:Integer;
    Sacado_NumeroCPFCGC,
    Sacado_Rua,
    Sacado_CEP,
    Sacado_Cidade,
    Sacado_Estado,
    //dados da cobranca
    Cobranca_NossoNumero:String;
    Cobranca_ValorDocumento:Currency;
    Cobranca_DataDocumento:TDate;
    Cobranca_DataVencimento:TDate;
    Cobranca_NumeroDocumento,
    Cobranca_InstrucoesText:String):String;external 'Project2.dll';


procedure TForm1.Button2Click(Sender: TObject);
var
  nQtdeBoletos: Integer; //simular a quantidade de registros
  nI: Integer;
begin
    //Remessa(
    GeraBoleto(
    10,
    ComboBox3.ItemIndex,
    Date,
    Trim(Copy(ComboBox1.Text,1,3)),
    Trim(Edit1.Text),
    Trim(Edit2.Text),
    Trim(Edit5.Text),
    Trim(Edit6.Text),
    Trim(Edit5.Text),
    Trim(Edit6.Text),
    Trim(Edit7.Text),
    Trim(Edit8.Text),
    Trim(Edit9.Text),
    ComboBox2.ItemIndex,
    '01.001.001/0001-13',
    Trim(Edit9.Text),
    Trim(Edit12.Text),
    ComboBox2.ItemIndex,
    Trim(Edit12.Text),
    Trim(Edit13.Text),
    Trim(Edit14.Text),
    Trim(Edit15.Text),
    Trim(Edit16.Text),
    //dados da cobranca
    Trim(Edit4.Text),
    StrToCurr(Edit10.Text),
    Date,
    StrToDate(MaskEdit1.Text),
    Trim(Edit3.Text),
    Memo1.Lines.Text);

end;

procedure TForm1.FormShow(Sender: TObject);
begin
  MaskEdit1.Text := DateToStr(IncDay(Date,20)); //Vencimento
  Edit9.Text     := 'NOME DO CEDENTE LTDA.'; //Cedente
  Edit1.Text     := '1234'; //Agência
  Edit2.Text     := '56'; //Digito da agência não informado
  Edit5.Text     := '12345'; //Código Cedente
  Edit6.Text     := '6'; //Digito do código do cedente
  Edit3.Text     := '1000'; //Número do documento do cedente
  Edit7.Text     := '1'; //Carteira
  Edit8.Text     := '123456'; //Convênio, utilizado apenas para o banco do brasil
  Edit4.Text     := '1'; //Nosso Numero, vamos passar na hora de imprimir
  Edit10.Text    := '123,45'; //Valor
end;

procedure TForm1.Button1Click(Sender: TObject);
var
  nQtdeBoletos, nI: Integer;
begin
  //Para gerar a remessa temos que passar todos os boletos para o componente RLBRemessa1
  //Vamos colocar tudo em um for para facilitar
  nQtdeBoletos := StrToInt(Trim(MaskEdit2.Text));

  for nI := 1 to nQtdeBoletos do
  begin
    //Dados do Cedente
    RLBTitulo1.Cedente.ContaBancaria.Banco.Codigo := Trim(Copy(ComboBox1.Text,1,3));
    RLBTitulo1.Cedente.ContaBancaria.CodigoAgencia := Trim(Edit1.Text);
    RLBTitulo1.Cedente.ContaBancaria.DigitoAgencia := Trim(Edit2.Text);
    RLBTitulo1.Cedente.ContaBancaria.NumeroConta := Trim(Edit5.Text);
    RLBTitulo1.Cedente.ContaBancaria.DigitoConta := Trim(Edit6.Text);
    RLBTitulo1.Cedente.CodigoCedente := Trim(Edit5.Text);
    RLBTitulo1.Cedente.DigitoCodigoCedente := Trim(Edit6.Text); // Os dados do cedente foram repetidos com os dados da conta, mas em alguns bancos eles são diferentes, CUIDADO
    RLBTitulo1.Carteira := Trim(Edit7.Text);
    RLBTitulo1.Cedente.ContaBancaria.Convenio := Trim(Edit8.Text);
    RLBTitulo1.Cedente.ContaBancaria.NomeCliente := Trim(Edit9.Text);
    RLBTitulo1.Cedente.TipoInscricao := tiPessoaJuridica;
    RLBTitulo1.Cedente.NumeroCPFCGC := '01001001000113'; //CNPJ do Cedente é importante para a remessa
    RLBTitulo1.Cedente.Nome := Trim(Edit9.Text);
    //dados do sacado
    RLBTitulo1.Sacado.Nome := Trim(Edit11.Text);
    case ComboBox2.ItemIndex of
      0: RLBTitulo1.Sacado.TipoInscricao := tiPessoaFisica;
      1: RLBTitulo1.Sacado.TipoInscricao := tiPessoaJuridica;
    end;
    RLBTitulo1.Sacado.NumeroCPFCGC := Trim(Edit12.Text);
    RLBTitulo1.Sacado.Endereco.Rua := Trim(Edit13.Text);
    RLBTitulo1.Sacado.Endereco.CEP := Trim(Edit14.Text);
    RLBTitulo1.Sacado.Endereco.Cidade := Trim(Edit15.Text);
    RLBTitulo1.Sacado.Endereco.Estado := Trim(Edit16.Text);
    //dados da cobranca
    RLBTitulo1.TipoOcorrencia := toRemessaRegistrar;
    RLBTitulo1.EspecieDocumento := edDuplicataMercantil;
    Edit4.Text := IntToStr(StrToIntDef(Trim(Edit4.Text),0)+1); //Simulando o nosso numero
    RLBTitulo1.NossoNumero := Trim(Edit4.Text);
    RLBTitulo1.ValorDocumento := StrToCurr(Edit10.Text);
    RLBTitulo1.DataDocumento := Date;
    RLBTitulo1.DataVencimento := StrToDate(MaskEdit1.Text);
    RLBTitulo1.NumeroDocumento := Trim(Edit3.Text);
    RLBTitulo1.Instrucoes.Text := Memo1.Lines.Text;
    //Inserindo o boleto na remessa
    RLBRemessa1.Titulos.Add(RLBTitulo1);
  end;

  RLBRemessa1.DataArquivo := Date; //É a data que o arquivo está sendo gerado
  RLBRemessa1.LayoutArquivo := laCNAB400; { Layout do arquivo, é necessário ver com o banco qual é o indicado.
                                            CNAB400 é o mais comum. }
  RLBRemessa1.NomeArquivo := FormatDateTime('yymmdd',Date)+'.REM'; { Apenas o nome do arquivo, alguns bancos
                                                                     estipulam regras e outros não }
  RLBRemessa1.NumeroArquivo := 1; { Sequencia númerica de quandos arquivos já foram gerados para este banco }
  RLBRemessa1.TipoMovimento := tmRemessa;

  RLBRemessa1.NomeArquivo := ExtractFileDir(Application.ExeName)+'\'+RLBRemessa1.NomeArquivo; // Apenas colocando um caminho para o arquivo

  if RLBRemessa1.GerarRemessa then
    MessageDlg('O arquivo foi gerado com sucesso.'#13+RLBRemessa1.NomeArquivo,mtInformation,[mbOK],0);
end;

end.
