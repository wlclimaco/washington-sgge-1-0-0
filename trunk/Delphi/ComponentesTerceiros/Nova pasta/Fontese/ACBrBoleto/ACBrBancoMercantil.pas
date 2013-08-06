{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:   Juliana Rodrigues Prado                       }
{                            :   Agnaldo Pedroni                               }
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

{$I ACBr.inc}

unit ACBrBancoMercantil;

interface

uses
  Classes, SysUtils,ACBrBoleto,
  {$IFDEF COMPILER6_UP} dateutils {$ELSE} ACBrD5 {$ENDIF};

type

  { TACBrBancoMercantil }

  TACBrBancoMercantil = class(TACBrBancoClass)
  private
    function FormataNossoNumero(const ACBrTitulo :TACBrTitulo): String;
  public
    Constructor create(AOwner: TACBrBanco);
    function CalcularDigitoVerificador(const ACBrTitulo:TACBrTitulo): String; override;
    function MontarCodigoBarras(const ACBrTitulo : TACBrTitulo): String; override;
    function MontarCampoNossoNumero(const ACBrTitulo :TACBrTitulo): String; override;
    function MontarCampoCodigoCedente(const ACBrTitulo: TACBrTitulo): String; override;
    procedure GerarRegistroHeader400(NumeroRemessa : Integer; aRemessa: TStringList); override;
    procedure GerarRegistroTransacao400(ACBrTitulo : TACBrTitulo; aRemessa: TStringList); override;
    procedure GerarRegistroTrailler400(ARemessa:TStringList);  override;
  end;

implementation

uses ACBrUtil, StrUtils;

{ TACBrBancoMercantil }

constructor TACBrBancoMercantil.create(AOwner: TACBrBanco);
begin
   inherited create(AOwner);
   fpDigito := 1;
   fpNome   := 'Banco Mercantil';
   fpNumero:= 389;
   fpTamanhoMaximoNossoNum := 6;
   fpTamanhoCarteira:= 2;
end;

function TACBrBancoMercantil.FormataNossoNumero(const ACBrTitulo :TACBrTitulo): String;
var
  ANossoNumero: string;
  aModalidade: String;
begin
   aModalidade:= IfThen(Length(trim(ACBrTitulo.ACBrBoleto.Cedente.Modalidade)) = 2,
                        ACBrTitulo.ACBrBoleto.Cedente.Modalidade,'22');

   ANossoNumero := padL(ACBrTitulo.ACBrBoleto.Cedente.Agencia,4,'0') + //4
                   aModalidade                                       + //6
                   ACBrTitulo.Carteira                               + //8
                   padL(ACBrTitulo.NossoNumero, 6, '0')              + //14
                   CalcularDigitoVerificador(ACBrTitulo);              //15

   Result := ANossoNumero;
end;

function TACBrBancoMercantil.CalcularDigitoVerificador(const ACBrTitulo: TACBrTitulo ): String;
var
  aModalidade: String;
begin
   Modulo.CalculoPadrao;
   Modulo.MultiplicadorAtual   := 2;

   aModalidade:= IfThen(Length(trim(ACBrTitulo.ACBrBoleto.Cedente.Modalidade)) = 2,
                        ACBrTitulo.ACBrBoleto.Cedente.Modalidade,'22');

   Modulo.Documento := ACBrTitulo.ACBrBoleto.Cedente.Agencia +
                       aModalidade +
                       ACBrTitulo.Carteira +
                       padL(ACBrTitulo.NossoNumero, 6, '0');

   Modulo.Calcular;
   Result:= IntToStr(Modulo.DigitoFinal);
   
end;

function TACBrBancoMercantil.MontarCodigoBarras ( const ACBrTitulo: TACBrTitulo) : String;
var
  FatorVencimento, DigitoCodBarras, CpObrigatorio , CpLivre:String;
begin

   with ACBrTitulo.ACBrBoleto do
   begin
      FatorVencimento := CalcularFatorVencimento(ACBrTitulo.Vencimento);

      // comum a todos bancos
      CpObrigatorio := IntToStr( Numero )+  //4
                       '9'               +  //5
                       FatorVencimento   +  //9
                       IntToStrZero(Round(ACBrTitulo.ValorDocumento*100),10);//19

                       // AG+22+01+123456
      CpLivre       := FormataNossoNumero(ACBrTitulo)    + //34
                       padL(Cedente.CodigoCedente,9,'0') + //43
                       ifthen(ACBrTitulo.ValorDesconto > 0,'2','0') ;// ?? indicador Desconto 2-Sem 0-Com  // 44

      DigitoCodBarras := CalcularDigitoCodigoBarras(CpObrigatorio+CpLivre);

   end;

   Result:= IntToStr(Numero) +
            '9'+
            DigitoCodBarras +
            Copy( (CpObrigatorio + CpLivre),5,39);

end;

function TACBrBancoMercantil.MontarCampoNossoNumero (
   const ACBrTitulo: TACBrTitulo ) : String;
begin
   Result := Copy(FormataNossoNumero(ACBrTitulo),5,11);
end;

// usado no carn�
function TACBrBancoMercantil.MontarCampoCodigoCedente (
   const ACBrTitulo: TACBrTitulo ) : String;
begin
   Result := ACBrTitulo.ACBrBoleto.Cedente.Agencia       + '-' +
             ACBrTitulo.ACBrBoleto.Cedente.AgenciaDigito + '/' +
             ACBrTitulo.ACBrBoleto.Cedente.CodigoCedente;
end;

procedure TACBrBancoMercantil.GerarRegistroHeader400(NumeroRemessa : Integer; aRemessa:TStringList);
var
  wLinha: String;
begin
   with ACBrBanco.ACBrBoleto.Cedente do
   begin
      wLinha:= '0'                                        + // ID do Registro
               '1'                                        + // ID do Arquivo( 1 - Remessa)
               'REMESSA'                                  + // Literal de Remessa
               '01'                                       + // C�digo do Tipo de Servi�o
               padL( 'COBRANCA', 15 )                     + // Descri��o do tipo de servi�o
               padL( OnlyNumber(Agencia), 4)              + // agencia origem
	       padR( OnlyNumber(CNPJCPF),15,'0')          + // CNPJ/CPF CEDENTE
	       ' '                                        + // BRANCO
	       padL( Nome, 30)                            + // Nome da Empresa
	       '389'                                      + // ID BANCO
	       'BANCO MERCANTIL'                          + // nome banco
	       FormatDateTime('ddmmyy',Now)               + // data gera��o
               Space(281)                                 + // espa�os branco
	       '01600   '                                 + // densidade da grava��o
	       IntToStrZero(NumeroRemessa,5)              + // nr. sequencial remessa
	       IntToStrZero(1,6);                           // Nr. Sequencial de Remessa

      aRemessa.Text:= aRemessa.Text + UpperCase(wLinha);
   end;
end;

procedure TACBrBancoMercantil.GerarRegistroTransacao400(ACBrTitulo :TACBrTitulo; aRemessa: TStringList);
var
  Ocorrencia,wLinha       :String;
  TipoSacado, ATipoAceite :String;
begin

   with ACBrTitulo do
   begin
      {Pegando C�digo da Ocorrencia}
      case OcorrenciaOriginal.Tipo of
         toRemessaBaixar                         : Ocorrencia := '02'; {Pedido de Baixa}
         toRemessaConcederAbatimento             : Ocorrencia := '04'; {Concess�o de Abatimento}
         toRemessaCancelarAbatimento             : Ocorrencia := '05'; {Cancelamento de Abatimento concedido}
         toRemessaAlterarVencimento              : Ocorrencia := '06'; {Altera��o de vencimento}
         toRemessaAlterarNumeroControle          : Ocorrencia := '08'; {Altera��o de seu n�mero}
         toRemessaProtestar                      : Ocorrencia := '09'; {Pedido de protesto}
         toRemessaCancelarInstrucaoProtestoBaixa : Ocorrencia := '18'; {Sustar protesto e baixar}
         toRemessaCancelarInstrucaoProtesto      : Ocorrencia := '19'; {Sustar protesto e manter na carteira}
         toRemessaOutrasOcorrencias              : Ocorrencia := '31'; {Altera��o de Outros Dados}
      else
         Ocorrencia := '01';                                          {Remessa}
      end;

      {Pegando Tipo de Sacado}
      case Sacado.Pessoa of
         pFisica   : TipoSacado := '01';
         pJuridica : TipoSacado := '02';
      else
         TipoSacado := '99';
      end;

      { Pegando o Aceite do Titulo }
      case Aceite of
        atSim :  ATipoAceite := 'S';
        atNao :  ATipoAceite := 'N';
      end;

      with ACBrBoleto do
      begin
         wLinha:= '1'                                                     +  // ID Registro
                  IfThen( PercentualMulta > 0, '092', '000')              +  // Indica se exite Multa ou n�o
	          IntToStrZero( round( PercentualMulta * 100 ), 13)       +  // Percentual de Multa formatado com 2 casas decimais
		  FormatDateTime( 'ddmmyy', Vencimento + 1)               +  // data Multa
		  Space(5)                                                +
		  padR( Cedente.CodigoCedente, 9, '0')                    +  // numero do contrato ???
		  padR( SeuNumero,25,'0')                                 +  // Numero de Controle do Participante
                  FormataNossoNumero(ACBrTitulo)                          +
                  Space(5)                                                +
                  padR( OnlyNumber(Cedente.CNPJCPF), 15 , '0')            +
                  IntToStrZero( Round( ValorDocumento * 100 ), 10)        +  // qtde de moeda
                  '1'                                                     +  // Codigo Opera��o 1- Cobran�a Simples
                  Ocorrencia                                              +
                  padL( NumeroDocumento,  10)                             + // numero titulo atribuido pelo cliente
                  FormatDateTime( 'ddmmyy', Vencimento)                   +
                  IntToStrZero(Round( ValorDocumento * 100 ),13)          +  // valor nominal do titulo
                  '389'                                                   +  // banco conbrador
                  '00000'                                                 +  // agencia cobradora
                  '01'                                                    +  // codigo da especie, duplicata mercantil
                  ATipoAceite                                             +  // N
                  FormatDateTime( 'ddmmyy', DataDocumento )               +  // Data de Emiss�o
                  padL(Instrucao1,2,'0')                                  +  // instru�oes de cobran�a
                  padL(Instrucao2,2,'0')                                  +  // instru�oes de cobran�a
                  IntToStrZero(round(ValorMoraJuros * 100),13)            + // juros mora 11.2
                  FormatDateTime( 'ddmmyy', DataDesconto)                 + // data limite desconto
                  IntToStrZero(round(ValorDesconto * 100) ,13)            + // valor desconto
                  StringOfChar( '0', 13)                                  + // iof - caso seguro
                  StringOfChar( '0', 13)                                  + // valor abatimento ?????
                  TipoSacado                                              +
                  padR( OnlyNumber(Sacado.CNPJCPF),14,'0')                +
                  padL( Sacado.NomeSacado, 40, ' ')                       +
                  padL( Sacado.Logradouro + Sacado.Numero , 40)           +
                  padL( Sacado.Bairro ,12)                                +
                  padL( Sacado.CEP, 8 , '0' )                             +
                  padL( Sacado.Cidade ,15)                                +
                  padL( Sacado.UF, 2)                                     +
                  padL( Sacado.Avalista, 30)                              + // Avalista
                  Space(12)                                               +
                  '1'                                                     + // codigo moeda
                  IntToStrZero(aRemessa.Count + 1, 6 );

         aRemessa.Text:= aRemessa.Text + UpperCase(wLinha);
      end;
   end;
end;

procedure TACBrBancoMercantil.GerarRegistroTrailler400( ARemessa:TStringList );
var
  wLinha: String;
begin
   wLinha:= '9' + Space(393)                     + // ID Registro
            IntToStrZero( ARemessa.Count + 1, 6);  // Contador de Registros

   ARemessa.Text:= ARemessa.Text + UpperCase(wLinha);
end;


end.

