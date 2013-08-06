{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:   Juliana Rodrigues Prado                       }
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

unit ACBrBancoItau;

interface

uses
  Classes, SysUtils, ACBrBoleto,
  {$IFDEF COMPILER6_UP} dateutils {$ELSE} ACBrD5 {$ENDIF};

type
  { TACBrBancoItau}

  TACBrBancoItau = class(TACBrBancoClass)
   protected
   public
    Constructor create(AOwner: TACBrBanco);
    function CalcularDigitoVerificador(const ACBrTitulo: TACBrTitulo ): String; override ;
    function MontarCodigoBarras(const ACBrTitulo : TACBrTitulo): String; override;
    function MontarCampoNossoNumero ( const ACBrTitulo: TACBrTitulo) : String; override;
    function MontarCampoCodigoCedente(const ACBrTitulo: TACBrTitulo): String; override;
    function GerarRegistroHeader240(NumeroRemessa : Integer): String; override;
    function GerarRegistroTransacao240(ACBrTitulo : TACBrTitulo): String; override;
    function GerarRegistroTrailler240(ARemessa : TStringList): String;  override;
    procedure GerarRegistroHeader400(NumeroRemessa : Integer; aRemessa: TStringList); override;
    procedure GerarRegistroTransacao400(ACBrTitulo : TACBrTitulo; aRemessa: TStringList); override;
    procedure GerarRegistroTrailler400(ARemessa : TStringList);  override;

    Procedure LerRetorno240(ARetorno:TStringList); override;
    Procedure LerRetorno400(ARetorno:TStringList); override;

    function TipoOcorrenciaToDescricao(const TipoOcorrencia: TACBrTipoOcorrencia) : String; override;
    function CodOcorrenciaToTipo(const CodOcorrencia:Integer): TACBrTipoOcorrencia; override;
    function TipoOCorrenciaToCod(const TipoOcorrencia: TACBrTipoOcorrencia):String; override;
    function CodMotivoRejeicaoToDescricao(const TipoOcorrencia:TACBrTipoOcorrencia; CodMotivo:Integer): String; override;
   end;

implementation

uses ACBrUtil, StrUtils, Variants,ACBrValidador;

constructor TACBrBancoItau.create(AOwner: TACBrBanco);
begin
   inherited create(AOwner);
   fpDigito := 7;
   fpNome   := 'Banco Itau';
   fpNumero:= 341;
   fpTamanhoMaximoNossoNum := 8;
   fpTamanhoAgencia := 4;
   fpTamanhoConta   := 5;
   fpTamanhoCarteira:= 3;
end;

function TACBrBancoItau.CalcularDigitoVerificador(const ACBrTitulo: TACBrTitulo ): String;
var
  Docto: String;
begin
   Result := '0';
   Docto := '';

   with ACBrTitulo do
   begin
      Docto := Carteira + padR(NossoNumero,TamanhoMaximoNossoNum,'0');
      if not (Carteira = '112') then
         if not ((Carteira = '126') or (Carteira = '131') or (Carteira = '146') or
                (Carteira = '150') or (Carteira = '168')) then
            Docto := ACBrBoleto.Cedente.Agencia + ACBrBoleto.Cedente.Conta + docto
         else
            Docto := ACBrTitulo.ACBrBoleto.Cedente.Agencia +
                     ACBrTitulo.ACBrBoleto.Cedente.Conta +
                     ACBrTitulo.Carteira +
                     padR(ACBrTitulo.NossoNumero,TamanhoMaximoNossoNum,'0')
   end;

   Modulo.MultiplicadorInicial := 1;
   Modulo.MultiplicadorFinal   := 2;
   Modulo.MultiplicadorAtual   := 2;
   Modulo.FormulaDigito := frModulo10;
   Modulo.Documento:= Docto;
   Modulo.Calcular;
   Result := IntToStr(Modulo.DigitoFinal);
 
end;

function TACBrBancoItau.MontarCodigoBarras(const ACBrTitulo : TACBrTitulo): String;
var
  CodigoBarras, FatorVencimento, DigitoCodBarras :String;
  ANossoNumero, aAgenciaCC : string;
begin
    {Codigo de Barras}
    with ACBrTitulo.ACBrBoleto do
    begin
       FatorVencimento := CalcularFatorVencimento(ACBrTitulo.Vencimento);

       ANossoNumero := ACBrTitulo.Carteira +
                       padR(ACBrTitulo.NossoNumero,8,'0') +
                       CalcularDigitoVerificador(ACBrTitulo);

       aAgenciaCC   := Cedente.Agencia +
                       Cedente.Conta   +
                       Cedente.ContaDigito;

       aAgenciaCC:= OnlyNumber(aAgenciaCC);

       CodigoBarras := IntToStr( Numero ) +
                       '9' +
                       FatorVencimento +
                       IntToStrZero(Round(ACBrTitulo.ValorDocumento * 100), 10) +
                       ANossoNumero +
                       aAgenciaCC +
                       '000';

       DigitoCodBarras := CalcularDigitoCodigoBarras(CodigoBarras);
    end;
    Result:= copy( CodigoBarras, 1, 4) + DigitoCodBarras + copy( CodigoBarras, 5, 39) ;
end;

function TACBrBancoItau.MontarCampoNossoNumero ( const ACBrTitulo: TACBrTitulo
   ) : String;
var
  NossoNr: String;
begin
  with ACBrTitulo do
  begin
    NossoNr := Carteira + padR(NossoNumero,TamanhoMaximoNossoNum,'0');
  end;

  Insert('/',NossoNr,4);  Insert('-',NossoNr,13);
  Result := NossoNr + CalcularDigitoVerificador(ACBrTitulo);
end;

function TACBrBancoItau.MontarCampoCodigoCedente (
   const ACBrTitulo: TACBrTitulo ) : String;
begin
   Result := ACBrTitulo.ACBrBoleto.Cedente.Agencia  +'/'+
             ACBrTitulo.ACBrBoleto.Cedente.Conta    +'-'+
             ACBrTitulo.ACBrBoleto.Cedente.ContaDigito;
end;

function TACBrBancoItau.GerarRegistroHeader240(NumeroRemessa : Integer): String;
var
  ATipoInscricao: String;
begin

   with ACBrBanco.ACBrBoleto.Cedente do
   begin
      case TipoInscricao of
         pFisica  : ATipoInscricao := '1';
         pJuridica: ATipoInscricao := '2';
      end;

          { GERAR REGISTRO-HEADER DO ARQUIVO }

      Result:= IntToStrZero(ACBrBanco.Numero, 3)        + //1 a 3 - C�digo do banco
               '0000'                                   + //4 a 7 - Lote de servi�o
               '0'                                      + //8 - Tipo de registro - Registro header de arquivo
               space(9)                                 + //9 a 17 Uso exclusivo FEBRABAN/CNAB
               ATipoInscricao                           + //18 - Tipo de inscri��o do cedente
               padR(OnlyNumber(CNPJCPF), 14, '0')       + //19 a 32 -N�mero de inscri��o do cedente
               space(20)                                + // 33 a 52 - Brancos
               '0'                                      + // 53 - Zeros
               padR(OnlyNumber(Agencia), 4, '0')        + //54 a 57 - C�digo da ag�ncia do cedente
               ' '                                      + // 58 - Brancos
               '0000000'                                + // 59 a 65 - Zeros
               padR(OnlyNumber(Conta), 5, '0')          + // 66 a 70 - N�mero da conta do cedente
               ' '                                      + // 71 - Branco
               padR(ContaDigito, 1, '0')                + // 72 - D�gito da conta do cedente
               padL(Nome, 30, ' ')                      + // 73 a 102 - Nome do cedente
               padL('BANCO ITAU SA', 30, ' ')           + // 103 a 132 - Nome do banco
               space(10)                                + // 133 A 142 - Brancos
               '1'                                      + // 143 - C�digo de Remessa (1) / Retorno (2)
               FormatDateTime('ddmmyyyy', Now)          + // 144 a 151 - Data do de gera��o do arquivo
               FormatDateTime('hhmmss', Now)            + // 152 a 157 - Hora de gera��o do arquivo
               '000000'                                 + // 158 a 163 - N�mero sequencial do arquivo retorno
               '040'                                    + // 164 a 166 - N�mero da vers�o do layout do arquivo
               '00000'                                  + // 167 a 171 - Zeros
               space(54)                                + // 172 a 225 - 54 Brancos
               '000'                                    + // 226 a 228 - zeros
               space(12);                                 // 229 a 240 - Brancos

     { GERAR REGISTRO HEADER DO LOTE }

      Result:= Result + #13#10 +
               IntToStrZero(ACBrBanco.Numero, 3)       + //1 a 3 - C�digo do banco
               '0001'                                  + //4 a 7 - Lote de servi�o
               '1'                                     + //8 - Tipo de registro - Registro header de arquivo
               'R'                                     + //9 - Tipo de opera��o: R (Remessa) ou T (Retorno)
               '01'                                    + //10 a 11 - Tipo de servi�o: 01 (Cobran�a)
               '00'                                    + //12 a 13 - Forma de lan�amento: preencher com ZEROS no caso de cobran�a
               '030'                                   + //14 a 16 - N�mero da vers�o do layout do lote
               ' '                                     + //17 - Uso exclusivo FEBRABAN/CNAB
               ATipoInscricao                          + //18 - Tipo de inscri��o do cedente
               padR(OnlyNumber(CNPJCPF), 15, '0')      + //19 a 33 -N�mero de inscri��o do cedente
               space(20)                               + //34 a 53 - Brancos
               '0'                                     + // 54 - Zeros
               padR(OnlyNumber(Agencia), 4, '0')       + //55 a 58 - C�digo da ag�ncia do cedente
               ' '                                     + // 59
               '0000000'                               + // 60 a 66
               padR(OnlyNumber(Conta), 5, '0')         + //67 a 71 - N�mero da conta do cedente
               ' '                                     + // 72
               ContaDigito                             + // 73 - D�gito verificador da ag�ncia / conta
               padL(Nome, 30, ' ')                     + //74 a 103 - Nome do cedente
               space(80)                               + // 104 a 183 - Brancos
               '00000000'                              + // 184 a 191 - N�mero sequ�ncia do arquivo retorno.
               FormatDateTime('ddmmyyyy', Now)         + //192 a 199 - Data de gera��o do arquivo
               padR('', 8, '0')                        + //200 a 207 - Data do cr�dito - S� para arquivo retorno
               space(33);                                //208 a 240 - Uso exclusivo FEBRABAN/CNAB
   end;
end;

function TACBrBancoItau.GerarRegistroTransacao240(ACBrTitulo : TACBrTitulo): String;
var
   ATipoInscricao, ATipoOcorrencia           :String;
   ADataMoraJuros, ADataDesconto,ATipoAceite :String;
begin
   with ACBrTitulo do
   begin
      {SEGMENTO P}

      {Pegando o Tipo de Ocorrencia}
      case OcorrenciaOriginal.Tipo of
         toRemessaBaixar                    : ATipoOcorrencia := '02';
         toRemessaConcederAbatimento        : ATipoOcorrencia := '04';
         toRemessaCancelarAbatimento        : ATipoOcorrencia := '05';
         toRemessaAlterarVencimento         : ATipoOcorrencia := '06';
         toRemessaSustarProtesto            : ATipoOcorrencia := '18';
         toRemessaCancelarInstrucaoProtesto : ATipoOcorrencia := '10';
      else
         ATipoOcorrencia := '01';
      end;

      { Pegando o Aceite do Titulo }
      case Aceite of
        atSim :  ATipoAceite := 'A';
        atNao :  ATipoAceite := 'N';
      end;

      {Mora Juros}
      if (ValorMoraJuros > 0) then
       begin
         if (DataMoraJuros <> Null) then
            ADataMoraJuros := FormatDateTime('ddmmyyyy', DataMoraJuros)
         else
            ADataMoraJuros := padR('', 8, '0');
       end
      else
         ADataMoraJuros := padR('', 8, '0');

      {Descontos}
      if (ValorDesconto > 0) then
       begin
         if (DataDesconto <> Null) then
            ADataDesconto := FormatDateTime('ddmmyyyy', DataDesconto)
         else
            ADataDesconto := padR('', 8, '0');
       end
      else
         ADataDesconto := padR('', 8, '0');

      Result:= IntToStrZero(ACBrBanco.Numero, 3)                          + //1 a 3 - C�digo do banco
               '0001'                                                     + //4 a 7 - Lote de servi�o
               '3'                                                        + //8 - Tipo do registro: Registro detalhe
               IntToStrZero(ACBrBoleto.ListadeBoletos.IndexOf(ACBrTitulo)+ 1 ,5) + //9 a 13 - N�mero seq�encial do registro no lote - Cada registro possui dois segmentos
               'P'                                                        + //14 - C�digo do segmento do registro detalhe
               ' '                                                        + //15 - Uso exclusivo FEBRABAN/CNAB: Branco
               ATipoOcorrencia                                            + //16 a 17 - C�digo de movimento
               '0'                                                        + // 18
               padR(OnlyNumber(ACBrBoleto.Cedente.Agencia),4,'0')         + //19 a 22 - Ag�ncia mantenedora da conta
               ' '                                                        + // 23
               '0000000'                                                  + //24 a 30 - Complemento de Registro
               padR(OnlyNumber(ACBrBoleto.Cedente.Conta),5,'0')           + //31 a 35 - N�mero da Conta Corrente
               ' '                                                        + // 36
               ACBrBoleto.Cedente.ContaDigito                             + //37 - D�gito verificador da ag�ncia / conta
               Carteira                                                   + // 38 a 40 - Carteira
               padR(NossoNumero, 8, '0')                                  + // 41 a 48 - Nosso n�mero - identifica��o do t�tulo no banco
               CalcularDigitoVerificador(ACBrTitulo)                      + // 49 - D�gito verificador da ag�ncia / conta preencher somente em cobran�a sem registro
               space(8)                                                   + // 50 a 57 - Brancos
               padL('', 5, '0')                                           + // 58 a 62 - Complemento
               padL(NumeroDocumento, 10, ' ')                             + // 63 a 72 - N�mero que identifica o t�tulo na empresa [ Alterado conforme instru��es da CSO Bras�lia ] {27-07-09}

               space(5)                                                   + // 73 a 77 - Brancos
               FormatDateTime('ddmmyyyy', Vencimento)                     + // 78 a 85 - Data de vencimento do t�tulo
               IntToStrZero( round( ValorDocumento * 100), 15)            + // 86 a 100 - Valor nominal do t�tulo
               '00000'                                                    + // 101 a 105 - Ag�ncia cobradora. // Ficando com Zeros o Ita� definir� a ag�ncia cobradora pelo CEP do sacado
               ' '                                                        + // 106 - D�gito da ag�ncia cobradora
               padL(EspecieDoc,2)                                                 + // 107 a 108 - Esp�cie do documento
               ATipoAceite                             + // 109 - Identifica��o de t�tulo Aceito / N�o aceito
               FormatDateTime('ddmmyyyy', DataDocumento)                  + // 110 a 117 - Data da emiss�o do documento
               '0'                                                        + // 118 - Zeros
               ADataMoraJuros                                             + //119 a 126 - Data a partir da qual ser�o cobrados juros
               IfThen(ValorMoraJuros > 0, IntToStrZero( round(ValorMoraJuros * 100), 15),
                padR('', 15, '0'))                                        + //127 a 141 - Valor de juros de mora por dia
               '0'                                                        + // 142 - Zeros
               ADataDesconto                                             + // 143 a 150 - Data limite para desconto
               IfThen(ValorDesconto > 0, IntToStrZero( round(ValorDesconto * 100), 15),
               padR('', 15, '0'))                                         + //151 a 165 - Valor do desconto por dia
               IntToStrZero( round(ValorIOF * 100), 15)                   + //166 a 180 - Valor do IOF a ser recolhido
               IntToStrZero( round(ValorAbatimento * 100), 15)            + //181 a 195 - Valor do abatimento
               padL(SeuNumero, 25, ' ')                                   + //196 a 220 - Identifica��o do t�tulo na empresa
               IfThen((DataProtesto <> null) and (DataProtesto > Vencimento), '1', '3') + //221 - C�digo de protesto: Protestar em XX dias corridos
               IfThen((DataProtesto <> null) and (DataProtesto > Vencimento),
                    padR(IntToStr(DaysBetween(DataProtesto, Vencimento)), 2, '0'), '00') + //222 a 223 - Prazo para protesto (em dias corridos)
               '0'                                                        + // 224 - C�digo de Baixa
               '00'                                                       + // 225 A 226 - Dias para baixa
               '0000000000000 ';

      {SEGMENTO Q}

      {Pegando tipo de pessoa do Sacado}
      case Sacado.Pessoa of
         pFisica  : ATipoInscricao := '1';
         pJuridica: ATipoInscricao := '2';
         pOutras  : ATipoInscricao := '9';
      end;

      Result:= Result + #13#10 +
               IntToStrZero(ACBrBanco.Numero, 3)                          + //C�digo do banco
               '0001'                                                     + //N�mero do lote
               '3'                                                        + //Tipo do registro: Registro detalhe
               IntToStrZero((ACBrBoleto.ListadeBoletos.IndexOf(ACBrTitulo))+ 1 ,5) + //N�mero seq�encial do registro no lote - Cada registro possui dois segmentos
               'Q'                                                        + //C�digo do segmento do registro detalhe
               ' '                                                        + //Uso exclusivo FEBRABAN/CNAB: Branco
               '01'                                                       + // 16 a 17
                        {Dados do sacado}
               ATipoInscricao                                             + // 18 a 18 Tipo inscricao
               padR(OnlyNumber(Sacado.CNPJCPF), 15, '0')                  + // 19 a 33
               padL(Sacado.NomeSacado, 30, ' ')                           + // 34 a 63
               space(10)                                                  + // 64 a 73
               padL(Sacado.Logradouro +' '+ Sacado.Numero +' '+ Sacado.Complemento , 40, ' ') + // 74 a 113
               padL(Sacado.Bairro, 15, ' ')                               +  // 114 a 128
               padR(Sacado.CEP, 8, '0')                                   +  // 129 a 136
               padL(Sacado.Cidade, 15, ' ')                               +  // 137 a 151
               padL(Sacado.UF, 2, ' ')                                    +  // 152 a 153
                        {Dados do sacador/avalista}
               '0'                                                        + //Tipo de inscri��o: N�o informado
               padR('', 15, '0')                                          + //N�mero de inscri��o
               padL('', 30, ' ')                                          + //Nome do sacador/avalista
               space(10)                                                  + //Uso exclusivo FEBRABAN/CNAB
               padL('0',3, '0')                                           + //Uso exclusivo FEBRABAN/CNAB
               space(28);                                            //Uso exclusivo FEBRABAN/CNAB
      end;
end;

function TACBrBancoItau.GerarRegistroTrailler240( ARemessa : TStringList ): String;
begin
          {REGISTRO TRAILER DO LOTE}
   Result:= IntToStrZero(ACBrBanco.Numero, 3)                          + //C�digo do banco
            '0001'                                                     + //N�mero do lote
            '5'                                                        + //Tipo do registro: Registro trailer do lote
            Space(9)                                                   + //Uso exclusivo FEBRABAN/CNAB
            IntToStrZero(ARemessa.Count, 6)                            + //Quantidade de Registro da Remessa
            padR('', 6, '0')                                           + // Quantidade de t�tulos em cobran�a simples
            padR('',17, '0')                                           + //Valor dos t�tulos em cobran�a simples
            padR('', 6, '0')                                           + //Quantidade t�tulos em cobran�a vinculada
            padR('',17, '0')                                           + //Valor dos t�tulos em cobran�a vinculada
            padR('',46, '0')                                           + //Complemento
            padL('', 8, ' ')                                           + //Referencia do aviso bancario
            space(117);

          {GERAR REGISTRO TRAILER DO ARQUIVO}
   Result:= Result + #13#10 +
            IntToStrZero(ACBrBanco.Numero, 3)                          + //C�digo do banco
            '9999'                                                     + //Lote de servi�o
            '9'                                                        + //Tipo do registro: Registro trailer do arquivo
            space(9)                                                   + //Uso exclusivo FEBRABAN/CNAB}
            '000001'                                                   + //Quantidade de lotes do arquivo}
            IntToStrZero(ARemessa.Count, 6)                            + //Quantidade de registros do arquivo, inclusive este registro que est� sendo criado agora}
            padR('', 6, '0')                                           + //Complemento
            space(205);
end;

procedure TACBrBancoItau.GerarRegistroHeader400(
  NumeroRemessa: Integer; aRemessa: TStringList);
var
   wLinha: String;
begin
   with ACBrBanco.ACBrBoleto.Cedente do
   begin

      { GERAR REGISTRO-HEADER DO ARQUIVO }
      wLinha:=    '0'                                  + // 1 a 1     - IDENTIFICA��O DO REGISTRO HEADER
                  '1'                                  + // 2 a 2     - TIPO DE OPERA��O - REMESSA
                  'REMESSA'                            + // 3 a 9     - IDENTIFICA��O POR EXTENSO DO MOVIMENTO
                  '01'                                 + // 10 a 11   - IDENTIFICA��O DO TIPO DE SERVI�O
                  padL('COBRANCA',15, ' ')             + // 12 a 26   - IDENTIFICA��O POR EXTENSO DO TIPO DE SERVI�O
                  padR(OnlyNumber(Agencia), 4, '0')    + // 27 a 30   - AG�NCIA MANTENEDORA DA CONTA
                  '00'                                 + // 31 a 32   - COMPLEMENTO DE REGISTRO
                  padR(Conta, 5, '0')                  + // 33 a 37   - N�MERO DA CONTA CORRENTE DA EMPRESA
                  padR(ContaDigito, 1, '0')            + // 38 a 38   - D�GITO DE AUTO CONFER�NCIA AG/CONTA EMPRESA
                  space(8)                             + // 39 a 46   - COMPLEMENTO DO REGISTRO
                  padL(Nome, 30, ' ')                  + // 47 a 76   - NOME POR EXTENSO DA "EMPRESA M�E"
                  IntToStrZero(ACBrBanco.Numero, 3)    + // 77 a 79   - N� DO BANCO NA C�MARA DE COMPENSA��O
                  padL('BANCO ITAU SA', 15, ' ')       + // 80 a 94   - NOME POR EXTENSO DO BANCO COBRADOR
                  FormatDateTime('ddmmyy', Now)        + // 95 a 100  - DATA DE GERA��O DO ARQUIVO
                  space(294)                           + // 101 a 394 - COMPLEMENTO DO REGISTRO
                  IntToStrZero(1,6);                     // 395 a 400 - N�MERO SEQ�ENCIAL DO REGISTRO NO ARQUIVO
      aRemessa.Text := aRemessa.Text + UpperCase(wLinha);
   end;
end;

procedure TACBrBancoItau.GerarRegistroTransacao400( ACBrTitulo: TACBrTitulo; aRemessa: TStringList);
var
   ATipoCedente, ATipoSacado, ATipoOcorrencia    :String;
   ADataMoraJuros, ADataDesconto, ATipoAceite    :String;
   ATipoEspecieDoc, ANossoNumero,wLinha,wCarteira :String;

  function DoMontaInstrucoes1: string;
  begin
     Result := '';
     with ACBrTitulo, ACBrBoleto do
     begin
        {Nenhum mensagem especificada. Registro n�o ser� necess�rio gerar o registro}
        if Mensagem.Count = 0 then
           Exit;

        Result := sLineBreak + '6'                        +                 // IDENTIFICA��O DO REGISTRO
                  '2'                                     +                 // IDENTIFICA��O DO LAYOUT PARA O REGISTRO
                  Copy(padL(Mensagem[0], 69, ' '), 1, 69);                  // CONTE�DO DA 1� LINHA DE IMPRESS�O DA �REA "INSTRU��ES� DO BOLETO

        if Mensagem.Count >= 2 then
           Result := Result + Copy(padL(Mensagem[1], 69, ' '), 1, 69)       // CONTE�DO DA 2� LINHA DE IMPRESS�O DA �REA "INSTRU��ES� DO BOLETO
        else
           Result := Result + padL('', 69, ' ');                            // CONTE�DO DO RESTANTE DAS LINHAS

        if Mensagem.Count >= 3 then
           Result := Result + Copy(padL(Mensagem[2], 69, ' '), 1, 69)       // CONTE�DO DA 3� LINHA DE IMPRESS�O DA �REA "INSTRU��ES� DO BOLETO
      else
        Result := Result + padL('', 69, ' ');                               // CONTE�DO DO RESTANTE DAS LINHAS

      if Mensagem.Count >= 4 then
         Result := Result + Copy(padL(Mensagem[3], 69, ' '), 1, 69)         // CONTE�DO DA 4� LINHA DE IMPRESS�O DA �REA "INSTRU��ES� DO BOLETO
      else
         Result := Result + padL('', 69, ' ');                              // CONTE�DO DO RESTANTE DAS LINHAS

      if Mensagem.Count >= 5 then
         Result := Result + Copy(padL(Mensagem[4], 69, ' '), 1, 69)         // CONTE�DO DA 5� LINHA DE IMPRESS�O DA �REA "INSTRU��ES� DO BOLETO
      else
         Result := Result + padL('', 69, ' ');                              // CONTE�DO DO RESTANTE DAS LINHAS

      Result := Result    +
                space(47) +                                               // COMPLEMENTO DO REGISTRO
                IntToStrZero(aRemessa.Count + 2, 6);   // N� SEQ�ENCIAL DO REGISTRO NO ARQUIVO
    end;
  end;

  function DoMontaInstrucoes2(const AStr: string): string;
  begin
    // Implementado, mas opcional.
    // Ao utilizarmos a impress�o do boleto pelo componente, apenas 5 linhas
    // s�o impressas.
    // Por padr�o, essa rotina n�o ser� chamada
    
     Result := '';
     with ACBrTitulo, ACBrBoleto do
     begin
        {Nenhum mensagem especificada. Registro n�o ser� necess�rio gerar o registro}
        if Mensagem.Count <= 5 then
           Exit;

        Result := AStr + sLineBreak +
                  '6'                                     +                 // IDENTIFICA��O DO REGISTRO
                  '3'                                     +                 // IDENTIFICA��O DO LAYOUT PARA O REGISTRO
                  Copy(padL(Mensagem[5], 69, ' '), 1, 69);                  // CONTE�DO DA 6� LINHA DE IMPRESS�O DA �REA "INSTRU��ES� DO BOLETO

        if Mensagem.Count >= 7 then
           Result := Result + Copy(padL(Mensagem[6], 69, ' '), 1, 69)       // CONTE�DO DA 7� LINHA DE IMPRESS�O DA �REA "INSTRU��ES� DO BOLETO
        else
           Result := Result + padL('', 69, ' ');                            // CONTE�DO DO RESTANTE DAS LINHAS

        if Mensagem.Count >= 8 then
           Result := Result + Copy(padL(Mensagem[7], 69, ' '), 1, 69)       // CONTE�DO DA 8� LINHA DE IMPRESS�O DA �REA "INSTRU��ES� DO BOLETO
        else
           Result := Result + padL('', 69, ' ');                            // CONTE�DO DO RESTANTE DAS LINHAS

        if Mensagem.Count >= 9 then
           Result := Result + Copy(padL(Mensagem[8], 69, ' '), 1, 69)       // CONTE�DO DA 9� LINHA DE IMPRESS�O DA �REA "INSTRU��ES� DO BOLETO
        else
           Result := Result + padL('', 69, ' ');                            // CONTE�DO DO RESTANTE DAS LINHAS

        Result := Result +
                  space(116)                               +                 // COMPLEMENTO DO REGISTRO
                  IntToStrZero(ListadeBoletos.IndexOf(ACBrTitulo)+
                  ListadeBoletos.IndexOf(ACBrTitulo)+ 4, 6);                 // N� SEQ�ENCIAL DO REGISTRO NO ARQUIVO
    end;
  end;

begin
   with ACBrTitulo do
   begin
      {Pegando o Tipo de Ocorrencia}
      case OcorrenciaOriginal.Tipo of
        toRemessaBaixar                       : ATipoOcorrencia := '02';
        toRemessaConcederAbatimento           : ATipoOcorrencia := '04';
        toRemessaCancelarAbatimento           : ATipoOcorrencia := '05';
        toRemessaAlterarVencimento            : ATipoOcorrencia := '06';
        toRemessaAlterarUsoEmpresa            : ATipoOcorrencia := '07';
        toRemessaAlterarSeuNumero             : ATipoOcorrencia := '08';
        toRemessaProtestar                    : ATipoOcorrencia := '09';
        toRemessaNaoProtestar                 : ATipoOcorrencia := '10';
        toRemessaProtestoFinsFalimentares     : ATipoOcorrencia := '11';
        toRemessaSustarProtesto               : ATipoOcorrencia := '18';
        toRemessaOutrasAlteracoes             : ATipoOcorrencia := '31';
        toRemessaBaixaporPagtoDiretoCedente   : ATipoOcorrencia := '34';
        toRemessaCancelarInstrucao            : ATipoOcorrencia := '35';
        toRemessaAlterarVencSustarProtesto    : ATipoOcorrencia := '37';
        toRemessaCedenteDiscordaSacado        : ATipoOcorrencia := '38';
        toRemessaCedenteSolicitaDispensaJuros : ATipoOcorrencia := '47';
      else
        ATipoOcorrencia := '01';
      end;

      { Pegando o Aceite do Titulo }
      case Aceite of
        atSim :  ATipoAceite := 'A';
        atNao :  ATipoAceite := 'N';
      end;

      {Pegando o tipo de EspecieDoc }
      if trim(EspecieDoc) = 'DM' then
         ATipoEspecieDoc:= '01'
      else if trim(EspecieDoc) = 'NP' then
         ATipoEspecieDoc:= '02'
      else if trim(EspecieDoc) = 'NS' then
         ATipoEspecieDoc:= '03'
      else if trim(EspecieDoc) = 'ME' then
         ATipoEspecieDoc:= '04'
      else if trim(EspecieDoc) = 'RC' then
         ATipoEspecieDoc:= '05'
      else if trim(EspecieDoc) = 'CT' then
         ATipoEspecieDoc:= '06'
      else if trim(EspecieDoc) = 'CS' then
         ATipoEspecieDoc:= '07'
      else if trim(EspecieDoc) = 'DS' then
         ATipoEspecieDoc:= '08'
      else if trim(EspecieDoc) = 'LC' then
         ATipoEspecieDoc:= '09'
      else if trim(EspecieDoc) = 'ND' then
         ATipoEspecieDoc:= '13'
      else if trim(EspecieDoc) = 'DD' then
         ATipoEspecieDoc:= '15'
      else if trim(EspecieDoc) = 'EC' then
         ATipoEspecieDoc:= '16'
      else if trim(EspecieDoc) = 'PS' then
         ATipoEspecieDoc:= '17'
      else if trim(EspecieDoc) = 'DV' then
         ATipoEspecieDoc:= '99';


      {Mora Juros}
      if (ValorMoraJuros > 0) then
      begin
        if (DataMoraJuros <> Null) then
          ADataMoraJuros := FormatDateTime('ddmmyy', DataMoraJuros)
        else
          ADataMoraJuros := padR('', 6, '0');
      end
      else
        ADataMoraJuros := padR('', 6, '0');

      {Descontos}
      if (ValorDesconto > 0) then
      begin
        if (DataDesconto <> Null) then
          ADataDesconto := FormatDateTime('ddmmyy', DataDesconto)
        else
          ADataDesconto := padR('', 6, '0');
      end
      else
        ADataDesconto := padR('', 6, '0');

        {Pegando Tipo de Cedente}
      case ACBrBoleto.Cedente.TipoInscricao of
        pFisica   : ATipoCedente := '01';
        pJuridica : ATipoCedente := '02';
      end;

      {Pegando Tipo de Sacado}
      case Sacado.Pessoa of
        pFisica   : ATipoSacado := '01';
        pJuridica : ATipoSacado := '02';
      else
        ATipoSacado := '99';
      end;
    
    with ACBrBoleto do
    begin
       wCarteira:= Trim(Carteira);
       {Cobran�a sem registro com op��o de envio de arquivo remessa}
       if (wCarteira = '102') or (wCarteira = '103') or
          (wCarteira = '107') or (wCarteira = '172') or
          (wCarteira = '173') or (wCarteira = '196') then
        begin
          ANossoNumero := MontarCampoNossoNumero(ACBrTitulo);
          wLinha:= '6'                                                                            + // 6 - FIXO
                   '1'                                                                            + // 1 - FIXO
                   padR(OnlyNumber(Cedente.Agencia), 4, '0')                                      + // AG�NCIA MANTENEDORA DA CONTA
                   '00'                                                                           + // COMPLEMENTO DE REGISTRO
                   padR(OnlyNumber(Cedente.Conta), 5, '0')                                        + // N�MERO DA CONTA CORRENTE DA EMPRESA
                   padL(Cedente.ContaDigito, 1)                                                   + // D�GITO DE AUTO CONFER�NCIA AG/CONTA EMPRESA
                   Carteira                                                                       + // N�MERO DA CARTEIRA NO BANCO
                   padR(NossoNumero, 8, '0')                                                      + // IDENTIFICA��O DO T�TULO NO BANCO
                   Copy(ANossoNumero, Length(ANossoNumero), 1)                                    + // DAC DO NOSSO N�MERO
                   '0'                                                                            + // 0 - R$
                   padL('R$', 4, ' ')                                                             + // LITERAL DE MOEDA
                   IntToStrZero( round( ValorDocumento * 100), 13)                                + // VALOR NOMINAL DO T�TULO
                   padL(SeuNumero, 10, ' ')                                                       + // IDENTIFICA��O DO T�TULO NA EMPRESA
                   FormatDateTime('ddmmyy', Vencimento)                                           + // DATA DE VENCIMENTO DO T�TULO
                   padR(ATipoEspecieDoc, 2, '0')                                                  + // ESP�CIE DO T�TULO
                   ATipoAceite                                                                    + // IDENTIFICA��O DE TITILO ACEITO OU N�O ACEITO
                   FormatDateTime('ddmmyy', DataDocumento)                                        + // DATA DE EMISS�O
                   {Dados do sacado}
                   ATipoSacado                                                                    + // IDENTIFICA��O DO TIPO DE INSCRI��O/SACADO
                   padR(OnlyNumber(Sacado.CNPJCPF), 15, '0')                                      + // N� DE INSCRI��O DO SACADO  (CPF/CGC)
                   padL(Sacado.NomeSacado, 30, ' ')                                               + // NOME DO SACADO
                   space(9)                                                                       + // BRANCOS(COMPLEMENTO DE REGISTRO)
                   padL(Sacado.Logradouro +' '+ Sacado.Numero +' '+ Sacado.Complemento , 40, ' ') + // RUA, N�MERO E COMPLEMENTO DO SACADO
                   padL(Sacado.Bairro, 12, ' ')                                                   + // BAIRRO DO SACADO
                   padR(OnlyNumber(Sacado.CEP), 8, '0')                                           + // CEP DO SACADO
                   padL(Sacado.Cidade, 15, ' ')                                                   + // CIDADE DO SACADO
                   padL(Sacado.UF, 2, ' ')                                                        + // UF DO SACADO
                   {Dados do sacador/avalista}
                   padL('', 30, ' ')                                                              + // NOME DO SACADOR/AVALISTA
                   space(4)                                                                       + // COMPLEMENTO DO REGISTRO
                   padL(TiraAcentos(LocalPagamento), 55, ' ')                                                  + // LOCAL PAGAMENTO
                   padL(' ', 55, ' ')                                                              + // LOCAL PAGAMENTO 2
                   '01'                                                                           + // IDENTIF. TIPO DE INSCRI��O DO SACADOR/AVALISTA
                   padL('0', 15, '0')                                                             + // N�MERO DE INSCRI��O DO SACADOR/AVALISTA
                   space(31)                                                                      + // COMPLEMENTO DO REGISTRO
                   IntToStrZero(aRemessa.Count + 1 , 6);                                            // N� SEQ�ENCIAL DO REGISTRO NO ARQUIVO

          wLinha := wLinha + DoMontaInstrucoes1;
          //Result := DoMontaInstrucoes2(Result);               // opcional
        end
       else
        {Carteira com registro}
        begin
          wLinha:= '1'                                                                            + // 1 a 1 - IDENTIFICA��O DO REGISTRO TRANSA��O
                   ATipoCedente                                                                   + // TIPO DE INSCRI��O DA EMPRESA
                   padR(OnlyNumber(Cedente.CNPJCPF),14,'0')                                                   + // N� DE INSCRI��O DA EMPRESA (CPF/CGC)
                   padR(OnlyNumber(Cedente.Agencia), 4, '0')                                                  + // AG�NCIA MANTENEDORA DA CONTA
                   '00'                                                                           + // COMPLEMENTO DE REGISTRO
                   padR(OnlyNumber(Cedente.Conta), 5, '0')                                                    + // N�MERO DA CONTA CORRENTE DA EMPRESA
                   padL(Cedente.ContaDigito, 1)                                                   + // D�GITO DE AUTO CONFER�NCIA AG/CONTA EMPRESA
                   space(4)                                                                       + // COMPLEMENTO DE REGISTRO
                   '0000'                                                                         + // C�D.INSTRU��O/ALEGA��O A SER CANCELADA
                   padL(SeuNumero, 25, ' ')                                                       + // IDENTIFICA��O DO T�TULO NA EMPRESA
                   padR(NossoNumero, 8, '0')                                                      + // IDENTIFICA��O DO T�TULO NO BANCO
                   '0000000000000'                                                                + // QUANTIDADE DE MOEDA VARI�VEL
                   Carteira                                                                       + // N�MERO DA CARTEIRA NO BANCO
                   space(21)                                                                      + // IDENTIFICA��O DA OPERA��O NO BANCO
                   'I'                                                                            + // C�DIGO DA CARTEIRA
                   ATipoOcorrencia                                                                + // IDENTIFICA��O DA OCORR�NCIA
                   padL(NumeroDocumento, 10, ' ')                                                 + // N� DO DOCUMENTO DE COBRAN�A (DUPL.,NP ETC.)
                   FormatDateTime('ddmmyy', Vencimento)                                           + // DATA DE VENCIMENTO DO T�TULO
                   IntToStrZero( round( ValorDocumento * 100), 13)                                + // VALOR NOMINAL DO T�TULO
                   IntToStrZero(ACBrBanco.Numero, 3)                                              + // N� DO BANCO NA C�MARA DE COMPENSA��O
                   '00000'                                                                        + // AG�NCIA ONDE O T�TULO SER� COBRADO
                   padR(ATipoEspecieDoc, 2, '0')                                                  + // ESP�CIE DO T�TULO
                   ATipoAceite                                                                    + // IDENTIFICA��O DE TITILO ACEITO OU N�O ACEITO
                   FormatDateTime('ddmmyy', DataDocumento)                                        + // DATA DA EMISS�O DO T�TULO
                   padR(trim(ACBrStr(Instrucao1)), 2, '0')                                                       + // 1� INSTRU��O
                   padR(trim(ACBrStr(Instrucao2)), 2, '0')                                                       + // 2� INSTRU��O
                   IntToStrZero( round(ValorMoraJuros * 100 ), 13)                                + // VALOR DE MORA POR DIA DE ATRASO
                   ADataDesconto                                                                  + // DATA LIMITE PARA CONCESS�O DE DESCONTO
                   IfThen(ValorDesconto > 0, IntToStrZero( round(ValorDesconto * 100), 13),
                   padR('', 13, '0'))                                                             + // VALOR DO DESCONTO A SER CONCEDIDO
                   IntToStrZero( round(ValorIOF * 100), 13)                                       + // VALOR DO I.O.F. RECOLHIDO P/ NOTAS SEGURO
                   IntToStrZero( round(ValorAbatimento * 100), 13)                                + // VALOR DO ABATIMENTO A SER CONCEDIDO

                   {Dados do sacado}
                   ATipoSacado                                                                    + // IDENTIFICA��O DO TIPO DE INSCRI��O/SACADO
                   padR(OnlyNumber(Sacado.CNPJCPF), 14, '0')                                                  + // N� DE INSCRI��O DO SACADO  (CPF/CGC)
                   padL(Sacado.NomeSacado, 30, ' ')                                               + // NOME DO SACADO
                   space(10)                                                                      + // BRANCOS(COMPLEMENTO DE REGISTRO)
                   padL(Sacado.Logradouro +' '+ Sacado.Numero +' '+ Sacado.Complemento , 40, ' ') + // RUA, N�MERO E COMPLEMENTO DO SACADO
                   padL(Sacado.Bairro, 12, ' ')                                                   + // BAIRRO DO SACADO
                   padR(OnlyNumber(Sacado.CEP), 8, '0')                                           + // CEP DO SACADO
                   padL(Sacado.Cidade, 15, ' ')                                                   + // CIDADE DO SACADO
                   padL(Sacado.UF, 2, ' ')                                                        + // UF DO SACADO

                   {Dados do sacador/avalista}
                   padL('', 30, ' ')                                                              + // NOME DO SACADOR/AVALISTA
                   space(4)                                                                       + // COMPLEMENTO DO REGISTRO
                   ADataMoraJuros                                                                 + // DATA DE MORA
                   IfThen((DataProtesto <> null) and (DataProtesto > Vencimento),
                           padR(IntToStr(DaysBetween(DataProtesto, Vencimento)), 2, '0'), '00')      + // PRAZO
                   space(1)                                                                       + // BRANCOS
                   IntToStrZero(aRemessa.Count + 1, 6);                          // N� SEQ�ENCIAL DO REGISTRO NO ARQUIVO
        end;
    end;
    aRemessa.Text:= aRemessa.Text + UpperCase(wLinha);
  end;
end;

procedure TACBrBancoItau.GerarRegistroTrailler400(
  ARemessa: TStringList);
var
  wLinha: String;
begin
  wLinha:= '9' + Space(393) +                     // TIPO DE REGISTRO
           IntToStrZero( ARemessa.Count + 1, 6);  // N�MERO SEQ�ENCIAL DO REGISTRO NO ARQUIVO

  ARemessa.Text := ARemessa.Text + UpperCase(wLinha);
end;

procedure TACBrBancoItau.LerRetorno240(ARetorno: TStringList);
var
  Titulo: TACBrTitulo;
  TempData, Linha, rCedente, rCNPJCPF: String;
  ContLinha : Integer;
  idxMotivo: Integer;
begin
   ContLinha := 0;

   // informa��o do Header
   // Verifica se o arquivo pertence ao banco
   if StrToIntDef(copy(ARetorno.Strings[0], 1, 3),-1) <> Numero then
      raise Exception.create(ACBrStr(ACBrBanco.ACBrBoleto.NomeArqRetorno +
                             'n�o' + '� um arquivo de retorno do ' + Nome));

   ACBrBanco.ACBrBoleto.DataArquivo := StringToDateTimeDef(Copy(ARetorno[0],144,2)+'/'+
                                                           Copy(ARetorno[0],146,2)+'/'+
                                                           Copy(ARetorno[0],148,4),0, 'DD/MM/YYYY' );

   ACBrBanco.ACBrBoleto.NumeroArquivo := StrToIntDef(Copy(ARetorno[0],158,6),0);

   rCedente := trim(copy(ARetorno[0], 73, 30));
   rCNPJCPF := OnlyNumber( copy(ARetorno[0], 19, 14) );

   with ACBrBanco.ACBrBoleto do
   begin
      if (not LeCedenteRetorno) and (rCNPJCPF <> OnlyNumber(Cedente.CNPJCPF)) then
         raise Exception.create(ACBrStr('CNPJ\CPF do arquivo inv�lido'));

      Cedente.Nome := rCedente;
      Cedente.CNPJCPF := rCNPJCPF;

      case StrToIntDef(copy(ARetorno[0], 18, 1), 0) of
        01:
          Cedente.TipoInscricao := pFisica;
        else
          Cedente.TipoInscricao := pJuridica;
      end;

      ACBrBanco.ACBrBoleto.ListadeBoletos.Clear;
   end;

   ACBrBanco.TamanhoMaximoNossoNum := 8;

   for ContLinha := 1 to ARetorno.Count - 2 do
   begin
      Linha := ARetorno[ContLinha];

      if copy(Linha, 8, 1) <> '3' then // verifica se o registro (linha) � um registro detalhe (segmento J)
         Continue;

      if copy(Linha, 14, 1) = 'T' then // se for segmento T cria um novo titulo
         Titulo := ACBrBanco.ACBrBoleto.CriarTituloNaLista;

      with Titulo do
      begin
         if copy(Linha, 14, 1) = 'T' then
          begin
            SeuNumero := copy(Linha, 59, 10);
            NumeroDocumento := copy(Linha, 59, 10);
            Carteira := copy(Linha, 38, 3);

            TempData := copy(Linha, 74, 2) + '/'+copy(Linha, 76, 2)+'/'+copy(Linha, 78, 4);
            if TempData<>'00/00/0000' then
               Vencimento := StringToDateTimeDef(TempData, 0, 'DDMMYY');

            ValorDocumento := StrToFloatDef(copy(Linha, 82, 15), 0) / 100;

            NossoNumero := copy(Linha, 41, ACBrBanco.TamanhoMaximoNossoNum);
            ValorDespesaCobranca := StrToFloatDef(copy(Linha, 199, 15), 0) / 100;

            OcorrenciaOriginal.Tipo := CodOcorrenciaToTipo(StrToIntDef(copy(Linha, 16, 2), 0));

            IdxMotivo := 214;

            while (IdxMotivo < 221) do
            begin
               if (trim(Copy(Linha, IdxMotivo, 2)) <> '') and (trim(Copy(Linha, IdxMotivo, 2)) <> '00') then
               begin
                  MotivoRejeicaoComando.Add(Copy(Linha, IdxMotivo, 2));
                  DescricaoMotivoRejeicaoComando.Add(CodMotivoRejeicaoToDescricao(OcorrenciaOriginal.Tipo, StrToIntDef(Copy(Linha, IdxMotivo, 2), 0)));
               end;
               Inc(IdxMotivo, 2);
            end;
          end
         else // segmento U
          begin
            ValorIOF := StrToFloatDef(copy(Linha, 63, 15), 0) / 100;
            ValorAbatimento := StrToFloatDef(copy(Linha, 48, 15), 0) / 100;
            ValorDesconto := StrToFloatDef(copy(Linha, 33, 15), 0) / 100;
            ValorMoraJuros := StrToFloatDef(copy(Linha, 18, 15), 0) / 100;
            ValorOutrosCreditos := StrToFloatDef(copy(Linha, 108, 15), 0) / 100;
            ValorRecebido := StrToFloatDef(copy(Linha, 78, 15), 0) / 100;
            TempData := copy(Linha, 138, 2)+'/'+copy(Linha, 140, 2)+'/'+copy(Linha, 142, 4);
            if TempData<>'00/00/0000' then
                DataOcorrencia := StringToDateTimeDef(TempData, 0, 'DDMMYY');
            TempData := copy(Linha, 146, 2)+'/'+copy(Linha, 148, 2)+'/'+copy(Linha, 150, 4);
            if TempData<>'00/00/0000' then
                DataCredito := StringToDateTimeDef(TempData, 0, 'DDMMYYYY');
          end;
      end;
   end;

//   ACBrBanco.TamanhoMaximoNossoNum := 10;
end;

procedure TACBrBancoItau.LerRetorno400(ARetorno: TStringList);
var
  ContLinha, CodOCorrencia, I, MotivoLinha: Integer;
  Linha, rCedente, rDigitoConta: String ;
  rCNPJCPF,rAgencia,rConta: String;
  Titulo: TACBrTitulo;
begin
   ContLinha := 0;

   if StrToIntDef(copy(ARetorno.Strings[0],77,3),-1) <> Numero then
      raise Exception.Create(ACBrStr(ACBrBanco.ACBrBoleto.NomeArqRetorno +
                             'n�o � um arquivo de retorno do '+ Nome));

   rCedente := trim(Copy(ARetorno[0],47,30));
   rAgencia := trim(Copy(ARetorno[0],27,4));
   rConta   := trim(Copy(ARetorno[0],33,5));
   rDigitoConta := Copy(ARetorno[0],38,1);

   ACBrBanco.ACBrBoleto.NumeroArquivo := StrToIntDef(Copy(ARetorno[0],109,5),0);

   ACBrBanco.ACBrBoleto.DataArquivo   := StringToDateTimeDef(Copy(ARetorno[0],95,2)+'/'+            //|
                                                             Copy(ARetorno[0],97,2)+'/'+            //|Implementado por Carlos Fitl - 27/12/2010
                                                             Copy(ARetorno[0],99,2),0, 'DD/MM/YY' );//|

   ACBrBanco.ACBrBoleto.DataCreditoLanc := StringToDateTimeDef(Copy(ARetorno[0],114,2)+'/'+            //|
                                                               Copy(ARetorno[0],116,2)+'/'+            //|Implementado por Carlos Fitl - 27/12/2010
                                                               Copy(ARetorno[0],118,2),0, 'DD/MM/YY' );//|

   case StrToIntDef(Copy(ARetorno[1],2,2),0) of
      1 : rCNPJCPF:= Copy(ARetorno[1],07,11);
      2 : rCNPJCPF:= Copy(ARetorno[1],04,14);
   else
      rCNPJCPF:= Copy(ARetorno[1],4,14);
   end;

   with ACBrBanco.ACBrBoleto do
   begin
      if (not LeCedenteRetorno) and (rCNPJCPF <> OnlyNumber(Cedente.CNPJCPF)) then
         raise Exception.Create(ACBrStr('CNPJ\CPF do arquivo inv�lido'));

      if (not LeCedenteRetorno) and ((rAgencia <> OnlyNumber(Cedente.Agencia)) or
          (rConta <> RightStr(OnlyNumber(Cedente.Conta), Length(rConta)))) then
         raise Exception.Create(ACBrStr('Agencia\Conta do arquivo inv�lido'));

      Cedente.Nome    := rCedente;
      Cedente.CNPJCPF := rCNPJCPF;
      Cedente.Agencia := rAgencia;
      Cedente.AgenciaDigito:= '0';
      Cedente.Conta   := rConta;
      Cedente.ContaDigito:= rDigitoConta;

      case StrToIntDef(Copy(ARetorno[1],2,2),0) of
         01: Cedente.TipoInscricao:= pFisica;
         else
            Cedente.TipoInscricao:= pJuridica;
      end;

      ACBrBanco.ACBrBoleto.ListadeBoletos.Clear;
   end;

   for ContLinha := 1 to ARetorno.Count - 2 do
   begin
      Linha := ARetorno[ContLinha] ;

      if Copy(Linha,1,1)<> '1' then
         Continue;

      Titulo := ACBrBanco.ACBrBoleto.CriarTituloNaLista;

      with Titulo do
      begin
         SeuNumero                   := copy(Linha,38,25);
         NumeroDocumento             := copy(Linha,117,10);
         Carteira                    := copy(Linha,83,3);

         OcorrenciaOriginal.Tipo     := CodOcorrenciaToTipo(StrToIntDef(copy(Linha,109,2),0));

         MotivoLinha := 378;
         for i := 0 to 3 do
         begin
            MotivoRejeicaoComando.Add(IfThen(copy(Linha,MotivoLinha,2) = '  ',
                                              '00',copy(Linha,MotivoLinha,2)));

            if MotivoRejeicaoComando[i] <> '00' then
            begin
               CodOCorrencia:= StrToIntDef(MotivoRejeicaoComando[i],0) ;
               DescricaoMotivoRejeicaoComando.Add(CodMotivoRejeicaoToDescricao(
                                                 OcorrenciaOriginal.Tipo,CodOCorrencia));
            end;

            MotivoLinha := MotivoLinha + 2;
         end;

         DataOcorrencia := StringToDateTimeDef( Copy(Linha,111,2)+'/'+
                                                Copy(Linha,113,2)+'/'+
                                                Copy(Linha,115,2),0, 'DD/MM/YY' );

         {Esp�cie do documento}
         if Trim(Copy(Linha,174,2)) = '' then
            EspecieDoc := '99'
         else
            case StrToIntDef(Copy(Linha,174,2),0) of
               01 : EspecieDoc := 'DM';
               02 : EspecieDoc := 'NP';
               03 : EspecieDoc := 'NS';
               04 : EspecieDoc := 'ME';
               05 : EspecieDoc := 'RC';
               06 : EspecieDoc := 'CT';
               07 : EspecieDoc := 'CS';
               08 : EspecieDoc := 'DS';
               09 : EspecieDoc := 'LC';
               13 : EspecieDoc := 'ND';
               15 : EspecieDoc := 'DD';
               16 : EspecieDoc := 'EC';
               17 : EspecieDoc := 'PS';
               99 : EspecieDoc := 'DV';
            else
               EspecieDoc := 'DV';
            end;

         Vencimento := StringToDateTimeDef( Copy(Linha,147,2)+'/'+
                                            Copy(Linha,149,2)+'/'+
                                            Copy(Linha,151,2),0, 'DD/MM/YY' );

         ValorDocumento       := StrToFloatDef(Copy(Linha,153,13),0)/100;
         ValorIOF             := StrToFloatDef(Copy(Linha,215,13),0)/100;
         ValorAbatimento      := StrToFloatDef(Copy(Linha,228,13),0)/100;
         ValorDesconto        := StrToFloatDef(Copy(Linha,241,13),0)/100;
         ValorMoraJuros       := StrToFloatDef(Copy(Linha,267,13),0)/100;
         ValorOutrosCreditos  := StrToFloatDef(Copy(Linha,280,13),0)/100;
         ValorRecebido        := StrToFloatDef(Copy(Linha,254,13),0)/100;
         NossoNumero          := Copy(Linha,63,8);
         Carteira             := Copy(Linha,83,3);
         ValorDespesaCobranca := StrToFloatDef(Copy(Linha,176,13),0)/100;

         if StrToIntDef(Copy(Linha,296,6),0) <> 0 then
            DataCredito:= StringToDateTimeDef( Copy(Linha,296,2)+'/'+
                                               Copy(Linha,298,2)+'/'+
                                               Copy(Linha,300,2),0, 'DD/MM/YY' );

         if StrToIntDef(Copy(Linha,111,6),0) <> 0 then
            DataBaixa := StringToDateTimeDef(Copy(Linha,111,2)+'/'+
                         Copy(Linha,113,2)+'/'+
                         Copy(Linha,115,2),0,'DD/MM/YY');

      end;
   end;
end;

function TACBrBancoItau.TipoOcorrenciaToDescricao(
  const TipoOcorrencia: TACBrTipoOcorrencia): String;
var
 CodOcorrencia: Integer;
begin

  CodOcorrencia := StrToIntDef(TipoOCorrenciaToCod(TipoOcorrencia),0);

  case CodOcorrencia of
    02: Result:='02-Entrada Confirmada' ;
    03: Result:='03-Entrada Rejeitada' ;
    04: Result:='04-Altera��o de Dados - Nova Entrada' ;
    05: Result:='05-Altera��o de Dados - Baixa' ;
    06: Result:='06-Liquida��o Normal' ;
    07: Result:='07-Liquida��o Parcial - Cobran�a Inteligente (B2b)' ;
    08: Result:='08-Liquida��o Em Cart�rio' ;
    09: Result:='09-Baixa Simples' ;
    10: Result:='10-Baixa Por Ter Sido Liquidado' ;
    11: Result:='11-Em Ser' ;
    12: Result:='12-Abatimento Concedido' ;
    13: Result:='13-Abatimento Cancelado' ;
    14: Result:='14-Vencimento Alterado' ;
    15: Result:='15-Baixas Rejeitadas' ;
    16: Result:='16-Instru��es Rejeitadas' ;
    17: Result:='17-Altera��o de Dados Rejeitados' ;
    18: Result:='18-Cobran�a Contratual - Instru��es/Altera��es Rejeitadas/Pendentes' ;
    19: Result:='19-Confirma Recebimento de Instru��o de Protesto' ;
    20: Result:='20-Confirma Recebimento de Instru��o de Susta��o de Protesto /Tarifa' ;
    21: Result:='21-Confirma Recebimento de Instru��o de N�o Protestar' ;
    23: Result:='23-T�tulo Enviado A Cart�rio/Tarifa' ;
    24: Result:='24-Instru��o de Protesto Rejeitada / Sustada / Pendente' ;
    25: Result:='25-Alega��es do Sacado' ;
    26: Result:='26-Tarifa de Aviso de Cobran�a' ;
    27: Result:='27-Tarifa de Extrato Posi��o (B40x)' ;
    28: Result:='28-Tarifa de Rela��o das Liquida��es' ;
    29: Result:='29-Tarifa de Manuten��o de T�tulos Vencidos' ;
    30: Result:='30-D�bito Mensal de Tarifas (Para Entradas e Baixas)' ;
    32: Result:='32-Baixa por ter sido Protestado' ;
    33: Result:='33-Custas de Protesto' ;
    34: Result:='34-Custas de Susta��o' ;
    35: Result:='35-Custas de Cart�rio Distribuidor' ;
    36: Result:='36-Custas de Edital' ;
    37: Result:='37-Tarifa de Emiss�o de Boleto/Tarifa de Envio de Duplicata' ;
    38: Result:='38-Tarifa de Instru��o' ;
    39: Result:='39-Tarifa de Ocorr�ncias' ;
    40: Result:='40-Tarifa Mensal de Emiss�o de Boleto/Tarifa Mensal de Envio De Duplicata' ;
    41: Result:='41-D�bito Mensal de Tarifas - Extrato de Posi��o (B4ep/B4ox)' ;
    42: Result:='42-D�bito Mensal de Tarifas - Outras Instru��es' ;
    43: Result:='43-D�bito Mensal de Tarifas - Manuten��o de T�tulos Vencidos' ;
    44: Result:='44-D�bito Mensal de Tarifas - Outras Ocorr�ncias' ;
    45: Result:='45-D�bito Mensal de Tarifas - Protesto' ;
    46: Result:='46-D�bito Mensal de Tarifas - Susta��o de Protesto' ;
    47: Result:='47-Baixa com Transfer�ncia para Desconto' ;
    48: Result:='48-Custas de Susta��o Judicial' ;
    51: Result:='51-Tarifa Mensal Ref a Entradas Bancos Correspondentes na Carteira' ;
    52: Result:='52-Tarifa Mensal Baixas na Carteira' ;
    53: Result:='53-Tarifa Mensal Baixas em Bancos Correspondentes na Carteira' ;
    54: Result:='54-Tarifa Mensal de Liquida��es na Carteira' ;
    55: Result:='55-Tarifa Mensal de Liquida��es em Bancos Correspondentes na Carteira' ;
    56: Result:='56-Custas de Irregularidade' ;
    57: Result:='57-Instru��o Cancelada' ;
    59: Result:='59-Baixa por Cr�dito em C/C Atrav�s do Sispag' ;
    60: Result:='60-Entrada Rejeitada Carn�' ;
    61: Result:='61-Tarifa Emiss�o Aviso de Movimenta��o de T�tulos (2154)' ;
    62: Result:='62-D�bito Mensal de Tarifa - Aviso de Movimenta��o de T�tulos (2154)' ;
    63: Result:='63-T�tulo Sustado Judicialmente' ;
    64: Result:='64-Entrada Confirmada com Rateio de Cr�dito' ;
    69: Result:='69-Cheque Devolvido' ;
    71: Result:='71-Entrada Registrada, Aguardando Avalia��o' ;
    72: Result:='72-Baixa por Cr�dito em C/C Atrav�s do Sispag sem T�tulo Correspondente' ;
    73: Result:='73-Confirma��o de Entrada na Cobran�a Simples - Entrada n�o Aceita na Cobran�a Contratual' ;
    76: Result:='76-Cheque Compensado' ;
  end;
end;

function TACBrBancoItau.CodOcorrenciaToTipo(
  const CodOcorrencia: Integer): TACBrTipoOcorrencia;
begin
  case CodOcorrencia of
      02: Result := toRetornoRegistroConfirmado;
      03: Result := toRetornoRegistroRecusado;
      04: Result := toRetornoAlteracaoDadosNovaEntrada;
      05: Result := toRetornoAlteracaoDadosBaixa;
      06: Result := toRetornoLiquidado;
      07: Result := toRetornoLiquidadoParcialmente;
      08: Result := toRetornoLiquidadoEmCartorio;
      09: Result := toRetornoBaixaSimples;
      10: Result := toRetornoBaixaPorTerSidoLiquidado;
      11: Result := toRetornoTituloEmSer;
      12: Result := toRetornoAbatimentoConcedido;
      13: Result := toRetornoAbatimentoCancelado;
      14: Result := toRetornoVencimentoAlterado;
      15: Result := toRetornoBaixaRejeitada;
      16: Result := toRetornoInstrucaoRejeitada;
      17: Result := toRetornoAlteracaoDadosRejeitados;
      18: Result := toRetornoCobrancaContratual;
      19: Result := toRetornoRecebimentoInstrucaoProtestar;
      20: Result := toRetornoRecebimentoInstrucaoSustarProtesto;
      21: Result := toRetornoRecebimentoInstrucaoNaoProtestar;
      23: Result := toRetornoEncaminhadoACartorio;
      24: Result := toRetornoInstrucaoProtestoRejeitadaSustadaOuPendente;
      25: Result := toRetornoAlegacaoDoSacado;
      26: Result := toRetornoTarifaAvisoCobranca;
      27: Result := toRetornoTarifaExtratoPosicao;
      28: Result := toRetornoTarifaDeRelacaoDasLiquidacoes;
      29: Result := toRetornoTarifaDeManutencaoDeTitulosVencidos;
      30: Result := toRetornoDebitoTarifas;
      32: Result := toRetornoBaixaPorProtesto;
      33: Result := toRetornoCustasProtesto;
      34: Result := toRetornoCustasSustacao;
      35: Result := toRetornoCustasCartorioDistribuidor;
      36: Result := toRetornoCustasEdital;
      37: Result := toRetornoTarifaEmissaoBoletoEnvioDuplicata;
      38: Result := toRetornoTarifaInstrucao;
      39: Result := toRetornoTarifaOcorrencias;
      40: Result := toRetornoTarifaMensalEmissaoBoletoEnvioDuplicata;
      41: Result := toRetornoDebitoMensalTarifasExtradoPosicao;
      42: Result := toRetornoDebitoMensalTarifasOutrasInstrucoes;
      43: Result := toRetornoDebitoMensalTarifasManutencaoTitulosVencidos;
      44: Result := toRetornoDebitoMensalTarifasOutrasOcorrencias;
      45: Result := toRetornoDebitoMensalTarifasProtestos;
      46: Result := toRetornoDebitoMensalTarifasSustacaoProtestos;
      47: Result := toRetornoBaixaTransferenciaParaDesconto;
      48: Result := toRetornoCustasSustacaoJudicial;
      51: Result := toRetornoTarifaMensalRefEntradasBancosCorrespCarteira;
      52: Result := toRetornoTarifaMensalBaixasCarteira;
      53: Result := toRetornoTarifaMensalBaixasBancosCorrespCarteira;
      54: Result := toRetornoTarifaMensalLiquidacoesCarteira;
      55: Result := toRetornoTarifaMensalLiquidacoesBancosCorrespCarteira;
      56: Result := toRetornoCustasIrregularidade;
      57: Result := toRetornoInstrucaoCancelada;
      59: Result := toRetornoBaixaCreditoCCAtravesSispag;
      60: Result := toRetornoEntradaRejeitadaCarne;
      61: Result := toRetornoTarifaEmissaoAvisoMovimentacaoTitulos;
      62: Result := toRetornoDebitoMensalTarifaAvisoMovimentacaoTitulos;
      63: Result := toRetornoTituloSustadoJudicialmente;
      64: Result := toRetornoEntradaConfirmadaRateioCredito;
      69: Result := toRetornoChequeDevolvido;
      71: Result := toRetornoEntradaRegistradaAguardandoAvaliacao;
      72: Result := toRetornoBaixaCreditoCCAtravesSispagSemTituloCorresp;
      73: Result := toRetornoConfirmacaoEntradaCobrancaSimples;
      76: Result := toRetornoChequeCompensado;
   else
      Result := toRetornoOutrasOcorrencias;
   end;
end;

function TACBrBancoItau.TipoOCorrenciaToCod(
  const TipoOcorrencia: TACBrTipoOcorrencia): String;
begin
  case TipoOcorrencia of
      toRetornoRegistroConfirmado                           : Result:='02';
      toRetornoRegistroRecusado                             : Result:='03';
      toRetornoAlteracaoDadosNovaEntrada                    : Result:='04';
      toRetornoAlteracaoDadosBaixa                          : Result:='05';
      toRetornoLiquidado                                    : Result:='06';
      toRetornoLiquidadoParcialmente                        : Result:='07';
      toRetornoLiquidadoEmCartorio                          : Result:='08';
      toRetornoBaixaSimples                                 : Result:='09';
      toRetornoBaixaPorTerSidoLiquidado                     : Result:='10';
      toRetornoTituloEmSer                                  : Result:='11';
      toRetornoAbatimentoConcedido                          : Result:='12';
      toRetornoAbatimentoCancelado                          : Result:='13';
      toRetornoVencimentoAlterado                           : Result:='14';
      toRetornoBaixaRejeitada                               : Result:='15';
      toRetornoInstrucaoRejeitada                           : Result:='16';
      toRetornoAlteracaoDadosRejeitados                     : Result:='17';
      toRetornoCobrancaContratual                           : Result:='18';
      toRetornoRecebimentoInstrucaoProtestar                : Result:='19';
      toRetornoRecebimentoInstrucaoSustarProtesto           : Result:='20';
      toRetornoRecebimentoInstrucaoNaoProtestar             : Result:='21';
      toRetornoEncaminhadoACartorio                         : Result:='23';
      toRetornoInstrucaoProtestoRejeitadaSustadaOuPendente  : Result:='24';
      toRetornoAlegacaoDoSacado                             : Result:='25';
      toRetornoTarifaAvisoCobranca                          : Result:='26';
      toRetornoTarifaExtratoPosicao                         : Result:='27';
      toRetornoTarifaDeRelacaoDasLiquidacoes                : Result:='28';
      toRetornoTarifaDeManutencaoDeTitulosVencidos          : Result:='29';
      toRetornoDebitoTarifas                                : Result:='30';
      toRetornoBaixaPorProtesto                             : Result:='32';
      toRetornoCustasProtesto                               : Result:='33';
      toRetornoCustasSustacao                               : Result:='34';
      toRetornoCustasCartorioDistribuidor                   : Result:='35';
      toRetornoCustasEdital                                 : Result:='36';
      toRetornoTarifaEmissaoBoletoEnvioDuplicata            : Result:='37';
      toRetornoTarifaInstrucao                              : Result:='38';
      toRetornoTarifaOcorrencias                            : Result:='39';
      toRetornoTarifaMensalEmissaoBoletoEnvioDuplicata      : Result:='40';
      toRetornoDebitoMensalTarifasExtradoPosicao            : Result:='41';
      toRetornoDebitoMensalTarifasOutrasInstrucoes          : Result:='42';
      toRetornoDebitoMensalTarifasManutencaoTitulosVencidos : Result:='43';
      toRetornoDebitoMensalTarifasOutrasOcorrencias         : Result:='44';
      toRetornoDebitoMensalTarifasProtestos                 : Result:='45';
      toRetornoDebitoMensalTarifasSustacaoProtestos         : Result:='46';
      toRetornoBaixaTransferenciaParaDesconto               : Result:='47';
      toRetornoCustasSustacaoJudicial                       : Result:='48';
      toRetornoTarifaMensalRefEntradasBancosCorrespCarteira : Result:='51';
      toRetornoTarifaMensalBaixasCarteira                   : Result:='52';
      toRetornoTarifaMensalBaixasBancosCorrespCarteira      : Result:='53';
      toRetornoTarifaMensalLiquidacoesCarteira              : Result:='54';
      toRetornoTarifaMensalLiquidacoesBancosCorrespCarteira : Result:='55';
      toRetornoCustasIrregularidade                         : Result:='56';
      toRetornoInstrucaoCancelada                           : Result:='57';
      toRetornoBaixaCreditoCCAtravesSispag                  : Result:='59';
      toRetornoEntradaRejeitadaCarne                        : Result:='60';
      toRetornoTarifaEmissaoAvisoMovimentacaoTitulos        : Result:='61';
      toRetornoDebitoMensalTarifaAvisoMovimentacaoTitulos   : Result:='62';
      toRetornoTituloSustadoJudicialmente                   : Result:='63';
      toRetornoEntradaConfirmadaRateioCredito               : Result:='64';
      toRetornoChequeDevolvido                              : Result:='69';
      toRetornoEntradaRegistradaAguardandoAvaliacao         : Result:='71';
      toRetornoBaixaCreditoCCAtravesSispagSemTituloCorresp  : Result:='72';
      toRetornoConfirmacaoEntradaCobrancaSimples            : Result:='73';
      toRetornoChequeCompensado                             : Result:='76';
   else
      Result:= '02';
   end;
end;

function TACBrBancoItau.CodMotivoRejeicaoToDescricao(
  const TipoOcorrencia: TACBrTipoOcorrencia; CodMotivo: Integer): String;
begin
  case TipoOcorrencia of
  
      //Tabela 1
      toRetornoRegistroRecusado, toRetornoEntradaRejeitadaCarne:
      case CodMotivo  of
         03: Result := 'AG. COBRADORA -N�O FOI POSS�VEL ATRIBUIR A AG�NCIA PELO CEP OU CEP INV�LIDO';
         04: Result := 'ESTADO -SIGLA DO ESTADO INV�LIDA';
         05: Result := 'DATA VENCIMENTO -PRAZO DA OPERA��O MENOR QUE PRAZO M�NIMO OU MAIOR QUE O M�XIMO';
         07: Result := 'VALOR DO T�TULO -VALOR DO T�TULO MAIOR QUE 10.000.000,00';
         08: Result := 'NOME DO SACADO -N�O INFORMADO OU DESLOCADO';
         09: Result := 'AGENCIA/CONTA -AG�NCIA ENCERRADA';
         10: Result := 'LOGRADOURO -N�O INFORMADO OU DESLOCADO';
         11: Result := 'CEP -CEP N�O NUM�RICO';
         12: Result := 'SACADOR / AVALISTA -NOME N�O INFORMADO OU DESLOCADO (BANCOS CORRESPONDENTES)';
         13: Result := 'ESTADO/CEP -CEP INCOMPAT�VEL COM A SIGLA DO ESTADO';
         14: Result := 'NOSSO N�MERO -NOSSO N�MERO J� REGISTRADO NO CADASTRO DO BANCO OU FORA DA FAIXA';
         15: Result := 'NOSSO N�MERO -NOSSO N�MERO EM DUPLICIDADE NO MESMO MOVIMENTO';
         18: Result := 'DATA DE ENTRADA -DATA DE ENTRADA INV�LIDA PARA OPERAR COM ESTA CARTEIRA';
         19: Result := 'OCORR�NCIA -OCORR�NCIA INV�LIDA';
         21: Result := 'AG. COBRADORA - CARTEIRA N�O ACEITA DEPOSIT�RIA CORRESPONDENTE/'+
                       'ESTADO DA AG�NCIA DIFERENTE DO ESTADO DO SACADO/'+
                       'AG. COBRADORA N�O CONSTA NO CADASTRO OU ENCERRANDO';
         22: Result := 'CARTEIRA -CARTEIRA N�O PERMITIDA (NECESS�RIO CADASTRAR FAIXA LIVRE)';
         26: Result := 'AG�NCIA/CONTA -AG�NCIA/CONTA N�O LIBERADA PARA OPERAR COM COBRAN�A';
         27: Result := 'CNPJ INAPTO -CNPJ DO CEDENTE INAPTO';
         29: Result := 'C�DIGO EMPRESA -CATEGORIA DA CONTA INV�LIDA';
         30: Result := 'ENTRADA BLOQUEADA -ENTRADAS BLOQUEADAS, CONTA SUSPENSA EM COBRAN�A';
         31: Result := 'AG�NCIA/CONTA -CONTA N�O TEM PERMISS�O PARA PROTESTAR (CONTATE SEU GERENTE)';
         35: Result := 'VALOR DO IOF -IOF MAIOR QUE 5%';
         36: Result := 'QTDADE DE MOEDA -QUANTIDADE DE MOEDA INCOMPAT�VEL COM VALOR DO T�TULO';
         37: Result := 'CNPJ/CPF DO SACADO -N�O NUM�RICO OU IGUAL A ZEROS';
         42: Result := 'NOSSO N�MERO -NOSSO N�MERO FORA DE FAIXA';
         52: Result := 'AG. COBRADORA -EMPRESA N�O ACEITA BANCO CORRESPONDENTE';
         53: Result := 'AG. COBRADORA -EMPRESA N�O ACEITA BANCO CORRESPONDENTE - COBRAN�A MENSAGEM';
         54: Result := 'DATA DE VENCTO -BANCO CORRESPONDENTE - T�TULO COM VENCIMENTO INFERIOR A 15 DIAS';
         55: Result := 'DEP/BCO CORRESP -CEP N�O PERTENCE � DEPOSIT�RIA INFORMADA';
         56: Result := 'DT VENCTO/BCO CORRESP -VENCTO SUPERIOR A 180 DIAS DA DATA DE ENTRADA';
         57: Result := 'DATA DE VENCTO -CEP S� DEPOSIT�RIA BCO DO BRASIL COM VENCTO INFERIOR A 8 DIAS';
         60: Result := 'ABATIMENTO -VALOR DO ABATIMENTO INV�LIDO';
         61: Result := 'JUROS DE MORA -JUROS DE MORA MAIOR QUE O PERMITIDO';
         63: Result := 'DESCONTO DE ANTECIPA��O -VALOR DA IMPORT�NCIA POR DIA DE DESCONTO (IDD) N�O PERMITIDO';
         64: Result := 'DATA DE EMISS�O -DATA DE EMISS�O DO T�TULO INV�LIDA';
         65: Result := 'TAXA FINANCTO -TAXA INV�LIDA (VENDOR)';
         66: Result := 'DATA DE VENCTO -INVALIDA/FORA DE PRAZO DE OPERA��O (M�NIMO OU M�XIMO)';
         67: Result := 'VALOR/QTIDADE -VALOR DO T�TULO/QUANTIDADE DE MOEDA INV�LIDO';
         68: Result := 'CARTEIRA -CARTEIRA INV�LIDA';
         69: Result := 'CARTEIRA -CARTEIRA INV�LIDA PARA T�TULOS COM RATEIO DE CR�DITO';
         70: Result := 'AG�NCIA/CONTA -CEDENTE N�O CADASTRADO PARA FAZER RATEIO DE CR�DITO';
         78: Result := 'AG�NCIA/CONTA -DUPLICIDADE DE AG�NCIA/CONTA BENEFICI�RIA DO RATEIO DE CR�DITO';
         80: Result := 'AG�NCIA/CONTA -QUANTIDADE DE CONTAS BENEFICI�RIAS DO RATEIO MAIOR DO QUE O PERMITIDO (M�XIMO DE 30 CONTAS POR T�TULO)';
         81: Result := 'AG�NCIA/CONTA -CONTA PARA RATEIO DE CR�DITO INV�LIDA / N�O PERTENCE AO ITA�';
         82: Result := 'DESCONTO/ABATI-MENTO -DESCONTO/ABATIMENTO N�O PERMITIDO PARA T�TULOS COM RATEIO DE CR�DITO';
         83: Result := 'VALOR DO T�TULO -VALOR DO T�TULO MENOR QUE A SOMA DOS VALORES ESTIPULADOS PARA RATEIO';
         84: Result := 'AG�NCIA/CONTA -AG�NCIA/CONTA BENEFICI�RIA DO RATEIO � A CENTRALIZADORA DE CR�DITO DO CEDENTE';
         85: Result := 'AG�NCIA/CONTA -AG�NCIA/CONTA DO CEDENTE � CONTRATUAL / RATEIO DE CR�DITO N�O PERMITIDO';
         86: Result := 'TIPO DE VALOR -C�DIGO DO TIPO DE VALOR INV�LIDO / N�O PREVISTO PARA T�TULOS COM RATEIO DE CR�DITO';
         87: Result := 'AG�NCIA/CONTA -REGISTRO TIPO 4 SEM INFORMA��O DE AG�NCIAS/CONTAS BENEFICI�RIAS DO RATEIO';
         90: Result := 'NRO DA LINHA -COBRAN�A MENSAGEM - N�MERO DA LINHA DA MENSAGEM INV�LIDO';
         97: Result := 'SEM MENSAGEM -COBRAN�A MENSAGEM SEM MENSAGEM (S� DE CAMPOS FIXOS), POR�M COM REGISTRO DO TIPO 7 OU 8';
         98: Result := 'FLASH INV�LIDO -REGISTRO MENSAGEM SEM FLASH CADASTRADO OU FLASH INFORMADO DIFERENTE DO CADASTRADO';
         99: Result := 'FLASH INV�LIDO -CONTA DE COBRAN�A COM FLASH CADASTRADO E SEM REGISTRO DE MENSAGEM CORRESPONDENTE';
         91: Result := 'DAC -DAC AG�NCIA / CONTA CORRENTE INV�LIDO';
         92: Result := 'DAC -DAC AG�NCIA/CONTA/CARTEIRA/NOSSO N�MERO INV�LIDO';
         93: Result := 'ESTADO -SIGLA ESTADO INV�LIDA';
         94: Result := 'ESTADO -SIGLA ESTADA INCOMPAT�VEL COM CEP DO SACADO';
         95: Result := 'CEP -CEP DO SACADO N�O NUM�RICO OU INV�LIDO';
         96: Result := 'ENDERE�O -ENDERE�O / NOME / CIDADE SACADO INV�LIDO';
      else
         Result := IntToStrZero(CodMotivo,2) +' - Outros Motivos';
      end;

      //Tabela 2
      toRetornoAlteracaoDadosRejeitados:
      case CodMotivo of
         02: Result := 'AG�NCIA COBRADORA INV�LIDA OU COM O MESMO CONTE�DO';
         04: Result := 'SIGLA DO ESTADO INV�LIDA';
         05: Result := 'DATA DE VENCIMENTO INV�LIDA OU COM O MESMO CONTE�DO';
         06: Result := 'VALOR DO T�TULO COM OUTRA ALTERA��O SIMULT�NEA';
         08: Result := 'NOME DO SACADO COM O MESMO CONTE�DO';
         09: Result := 'AG�NCIA/CONTA INCORRETA';
         11: Result := 'CEP INV�LIDO';
         13: Result := 'SEU N�MERO COM O MESMO CONTE�DO';
         16: Result := 'ABATIMENTO/ALTERA��O DO VALOR DO T�TULO OU SOLICITA��O DE BAIXA BLOQUEADA';
         21: Result := 'AG�NCIA COBRADORA N�O CONSTA NO CADASTRO DE DEPOSIT�RIA OU EM ENCERRAMENTO';
         53: Result := 'INSTRU��O COM O MESMO CONTE�DO';
         54: Result := 'DATA VENCIMENTO PARA BANCOS CORRESPONDENTES INFERIOR AO ACEITO PELO BANCO';
         55: Result := 'ALTERA��ES IGUAIS PARA O MESMO CONTROLE (AG�NCIA/CONTA/CARTEIRA/NOSSO N�MERO)';
         56: Result := 'CGC/CPF INV�LIDO N�O NUM�RICO OU ZERADO';
         57: Result := 'PRAZO DE VENCIMENTO INFERIOR A 15 DIAS';
         60: Result := 'VALOR DE IOF - ALTERA��O N�O PERMITIDA PARA CARTEIRAS DE N.S. - MOEDA VARI�VEL';
         61: Result := 'T�TULO J� BAIXADO OU LIQUIDADO OU N�O EXISTE T�TULO CORRESPONDENTE NO SISTEMA';
         66: Result := 'ALTERA��O N�O PERMITIDA PARA CARTEIRAS DE NOTAS DE SEGUROS - MOEDA VARI�VEL';
         81: Result := 'ALTERA��O BLOQUEADA - T�TULO COM PROTESTO';
      else
         Result := IntToStrZero(CodMotivo,2) +' - Outros Motivos';
      end;

      //Tabela 3
      toRetornoInstrucaoRejeitada:
      case CodMotivo of
         01: Result := 'INSTRU��O/OCORR�NCIA N�O EXISTENTE';
         06: Result := 'NOSSO N�MERO IGUAL A ZEROS';
         09: Result := 'CGC/CPF DO SACADOR/AVALISTA INV�LIDO';
         10: Result := 'VALOR DO ABATIMENTO IGUAL OU MAIOR QUE O VALOR DO T�TULO';
         14: Result := 'REGISTRO EM DUPLICIDADE';
         15: Result := 'CGC/CPF INFORMADO SEM NOME DO SACADOR/AVALISTA';
         21: Result := 'T�TULO N�O REGISTRADO NO SISTEMA';
         22: Result := 'T�TULO BAIXADO OU LIQUIDADO';
         23: Result := 'INSTRU��O N�O ACEITA POR TER SIDO EMITIDO �LTIMO AVISO AO SACADO';
         24: Result := 'INSTRU��O INCOMPAT�VEL - EXISTE INSTRU��O DE PROTESTO PARA O T�TULO';
         25: Result := 'INSTRU��O INCOMPAT�VEL - N�O EXISTE INSTRU��O DE PROTESTO PARA O T�TULO';
         26: Result := 'INSTRU��O N�O ACEITA POR TER SIDO EMITIDO �LTIMO AVISO AO SACADO';
         27: Result := 'INSTRU��O N�O ACEITA POR N�O TER SIDO EMITIDA A ORDEM DE PROTESTO AO CART�RIO';
         28: Result := 'J� EXISTE UMA MESMA INSTRU��O CADASTRADA ANTERIORMENTE PARA O T�TULO';
         29: Result := 'VALOR L�QUIDO + VALOR DO ABATIMENTO DIFERENTE DO VALOR DO T�TULO REGISTRADO, OU VALOR'+
                       'DO ABATIMENTO MAIOR QUE 90% DO VALOR DO T�TULO';
         30: Result := 'EXISTE UMA INSTRU��O DE N�O PROTESTAR ATIVA PARA O T�TULO';
         31: Result := 'EXISTE UMA OCORR�NCIA DO SACADO QUE BLOQUEIA A INSTRU��O';
         32: Result := 'DEPOSIT�RIA DO T�TULO = 9999 OU CARTEIRA N�O ACEITA PROTESTO';
         33: Result := 'ALTERA��O DE VENCIMENTO IGUAL � REGISTRADA NO SISTEMA OU QUE TORNA O T�TULO VENCIDO';
         34: Result := 'INSTRU��O DE EMISS�O DE AVISO DE COBRAN�A PARA T�TULO VENCIDO ANTES DO VENCIMENTO';
         35: Result := 'SOLICITA��O DE CANCELAMENTO DE INSTRU��O INEXISTENTE';
         36: Result := 'T�TULO SOFRENDO ALTERA��O DE CONTROLE (AG�NCIA/CONTA/CARTEIRA/NOSSO N�MERO)';
         37: Result := 'INSTRU��O N�O PERMITIDA PARA A CARTEIRA';
      else
         Result := IntToStrZero(CodMotivo,2) +' - Outros Motivos';
      end;

      //Tabela 4
      toRetornoBaixaRejeitada:
      case CodMotivo of
         01: Result := 'CARTEIRA/N� N�MERO N�O NUM�RICO';
         04: Result := 'NOSSO N�MERO EM DUPLICIDADE NUM MESMO MOVIMENTO';
         05: Result := 'SOLICITA��O DE BAIXA PARA T�TULO J� BAIXADO OU LIQUIDADO';
         06: Result := 'SOLICITA��O DE BAIXA PARA T�TULO N�O REGISTRADO NO SISTEMA';
         07: Result := 'COBRAN�A PRAZO CURTO - SOLICITA��O DE BAIXA P/ T�TULO N�O REGISTRADO NO SISTEMA';
         08: Result := 'SOLICITA��O DE BAIXA PARA T�TULO EM FLOATING';
      else
         Result:= IntToStrZero(CodMotivo,2) +' - Outros Motivos';
      end;

      //Tabela 5
      toRetornoCobrancaContratual:
         case CodMotivo of
            16: Result:= 'ABATIMENTO/ALTERA��O DO VALOR DO T�TULO OU SOLICITA��O DE BAIXA BLOQUEADOS';
            40: Result:= 'N�O APROVADA DEVIDO AO IMPACTO NA ELEGIBILIDADE DE GARANTIAS';
            41: Result:= 'AUTOMATICAMENTE REJEITADA';
            42: Result:= 'CONFIRMA RECEBIMENTO DE INSTRU��O � PENDENTE DE AN�LISE';
         else
            Result:= IntToStrZero(CodMotivo,2) +' - Outros Motivos';
         end;

      //Tabela 6
      toRetornoAlegacaoDoSacado:
      case CodMotivo of
         1313: Result := 'SOLICITA A PRORROGA��O DO VENCIMENTO PARA';
         1321: Result := 'SOLICITA A DISPENSA DOS JUROS DE MORA';
         1339: Result := 'N�O RECEBEU A MERCADORIA';
         1347: Result := 'A MERCADORIA CHEGOU ATRASADA';
         1354: Result := 'A MERCADORIA CHEGOU AVARIADA';
         1362: Result := 'A MERCADORIA CHEGOU INCOMPLETA';
         1370: Result := 'A MERCADORIA N�O CONFERE COM O PEDIDO';
         1388: Result := 'A MERCADORIA EST� � DISPOSI��O';
         1396: Result := 'DEVOLVEU A MERCADORIA';
         1404: Result := 'N�O RECEBEU A FATURA';
         1412: Result := 'A FATURA EST� EM DESACORDO COM A NOTA FISCAL';
         1420: Result := 'O PEDIDO DE COMPRA FOI CANCELADO';
         1438: Result := 'A DUPLICATA FOI CANCELADA';
         1446: Result := 'QUE NADA DEVE OU COMPROU';
         1453: Result := 'QUE MANT�M ENTENDIMENTOS COM O SACADOR';
         1461: Result := 'QUE PAGAR� O T�TULO EM:';
         1479: Result := 'QUE PAGOU O T�TULO DIRETAMENTE AO CEDENTE EM:';
         1487: Result := 'QUE PAGAR� O T�TULO DIRETAMENTE AO CEDENTE EM:';
         1495: Result := 'QUE O VENCIMENTO CORRETO �:';
         1503: Result := 'QUE TEM DESCONTO OU ABATIMENTO DE:';
         1719: Result := 'SACADO N�O FOI LOCALIZADO; CONFIRMAR ENDERE�O';
         1727: Result := 'SACADO EST� EM REGIME DE CONCORDATA';
         1735: Result := 'SACADO EST� EM REGIME DE FAL�NCIA';
         1750: Result := 'SACADO SE RECUSA A PAGAR JUROS BANC�RIOS';
         1768: Result := 'SACADO SE RECUSA A PAGAR COMISS�O DE PERMAN�NCIA';
         1776: Result := 'N�O FOI POSS�VEL A ENTREGA DO BLOQUETO AO SACADO';
         1784: Result := 'BLOQUETO N�O ENTREGUE, MUDOU-SE/DESCONHECIDO';
         1792: Result := 'BLOQUETO N�O ENTREGUE, CEP ERRADO/INCOMPLETO';
         1800: Result := 'BLOQUETO N�O ENTREGUE, N�MERO N�O EXISTE/ENDERE�O INCOMPLETO';
         1818: Result := 'BLOQUETO N�O RETIRADO PELO SACADO. REENVIADO PELO CORREIO';
         1826: Result := 'ENDERE�O DE E-MAIL INV�LIDO. BLOQUETO ENVIADO PELO CORREIO';
      else
         Result := IntToStrZero(CodMotivo,2) +' - Outros Motivos';
      end;

      //Tabela 7
      toRetornoInstrucaoProtestoRejeitadaSustadaOuPendente:
      case CodMotivo of
         1610: Result := 'DOCUMENTA��O SOLICITADA AO CEDENTE';
         3111: Result := 'SUSTA��O SOLICITADA AG. CEDENTE';
         3228: Result := 'ATOS DA CORREGEDORIA ESTADUAL';
         3244: Result := 'PROTESTO SUSTADO / CEDENTE N�O ENTREGOU A DOCUMENTA��O';
         3269: Result := 'DATA DE EMISS�O DO T�TULO INV�LIDA/IRREGULAR';
         3301: Result := 'CGC/CPF DO SACADO INV�LIDO/INCORRETO';
         3319: Result := 'SACADOR/AVALISTA E PESSOA F�SICA';
         3327: Result := 'CEP DO SACADO INCORRETO';
         3335: Result := 'DEPOSIT�RIA INCOMPAT�VEL COM CEP DO SACADO';
         3343: Result := 'CGC/CPF SACADOR INVALIDO/INCORRETO';
         3350: Result := 'ENDERE�O DO SACADO INSUFICIENTE';
         3368: Result := 'PRA�A PAGTO INCOMPAT�VEL COM ENDERE�O';
         3376: Result := 'FALTA N�MERO/ESP�CIE DO T�TULO';
         3384: Result := 'T�TULO ACEITO S/ ASSINATURA DO SACADOR';
         3392: Result := 'T�TULO ACEITO S/ ENDOSSO CEDENTE OU IRREGULAR';
         3400: Result := 'T�TULO SEM LOCAL OU DATA DE EMISS�O';
         3418: Result := 'T�TULO ACEITO COM VALOR EXTENSO DIFERENTE DO NUM�RICO';
         3426: Result := 'T�TULO ACEITO DEFINIR ESP�CIE DA DUPLICATA';
         3434: Result := 'DATA EMISS�O POSTERIOR AO VENCIMENTO';
         3442: Result := 'T�TULO ACEITO DOCUMENTO N�O PROSTEST�VEL';
         3459: Result := 'T�TULO ACEITO EXTENSO VENCIMENTO IRREGULAR';
         3467: Result := 'T�TULO ACEITO FALTA NOME FAVORECIDO';
         3475: Result := 'T�TULO ACEITO FALTA PRA�A DE PAGAMENTO';
         3483: Result := 'T�TULO ACEITO FALTA CPF ASSINANTE CHEQUE';
      else
         Result:= IntToStrZero(CodMotivo,2) +' - Outros Motivos';
      end;

      //Tabela 8
      toRetornoInstrucaoCancelada:
      case CodMotivo of
         1156: Result := 'N�O PROTESTAR';
         2261: Result := 'DISPENSAR JUROS/COMISS�O DE PERMAN�NCIA';
      else
         Result:= IntToStrZero(CodMotivo,2) +' - Outros Motivos';
      end;

      //Tabela 9
      toRetornoChequeDevolvido:
      case CodMotivo of
         11: Result:= 'CHEQUE SEM FUNDOS - PRIMEIRA APRESENTA��O - PASS�VEL DE REAPRESENTA��O: SIM';
         12: Result:= 'CHEQUE SEM FUNDOS - SEGUNDA APRESENTA��O - PASS�VEL DE REAPRESENTA��O: N�O ';
         13: Result:= 'CONTA ENCERRADA - PASS�VEL DE REAPRESENTA��O: N�O';
         14: Result:= 'PR�TICA ESP�RIA - PASS�VEL DE REAPRESENTA��O: N�O';
         20: Result:= 'FOLHA DE CHEQUE CANCELADA POR SOLICITA��O DO CORRENTISTA - PASS�VEL DE REAPRESENTA��O: N�O';
         21: Result:= 'CONTRA-ORDEM (OU REVOGA��O) OU OPOSI��O (OU SUSTA��O) AO PAGAMENTO PELO EMITENTE OU PELO ' +
                      'PORTADOR - PASS�VEL DE REAPRESENTA��O: SIM';
         22: Result:= 'DIVERG�NCIA OU INSUFICI�NCIA DE ASSINATURAb - PASS�VEL DE REAPRESENTA��O: SIM';
         23: Result:= 'CHEQUES EMITIDOS POR ENTIDADES E �RG�OS DA ADMINISTRA��O P�BLICA FEDERAL DIRETA E INDIRETA, ' +
                      'EM DESACORDO COM OS REQUISITOS CONSTANTES DO ARTIGO 74, � 2�, DO DECRETO-LEI N� 200, DE 25.02.1967. - ' +
                      'PASS�VEL DE REAPRESENTA��O: SIM';
         24: Result:= 'BLOQUEIO JUDICIAL OU DETERMINA��O DO BANCO CENTRAL DO BRASIL - PASS�VEL DE REAPRESENTA��O: SIM';
         25: Result:= 'CANCELAMENTO DE TALON�RIO PELO BANCO SACADO - PASS�VEL DE REAPRESENTA��O: N�O';
         28: Result:= 'CONTRA-ORDEM (OU REVOGA��O) OU OPOSI��O (OU SUSTA��O) AO PAGAMENTO OCASIONADA POR FURTO OU ROUBO - ' +
                      'PASS�VEL DE REAPRESENTA��O: N�O';
         29: Result:= 'CHEQUE BLOQUEADO POR FALTA DE CONFIRMA��O DO RECEBIMENTO DO TALON�RIO PELO CORRENTISTA - ' +
                      'PASS�VEL DE REAPRESENTA��O: SIM';
         30: Result:= 'FURTO OU ROUBO DE MALOTES - PASS�VEL DE REAPRESENTA��O: N�O';
         31: Result:= 'ERRO FORMAL (SEM DATA DE EMISS�O, COM O M�S GRAFADO NUMERICAMENTE, AUS�NCIA DE ASSINATURA, ' +
                      'N�O-REGISTRO DO VALOR POR EXTENSO) - PASS�VEL DE REAPRESENTA��O: SIM';
         32: Result:= 'AUS�NCIA OU IRREGULARIDADE NA APLICA��O DO CARIMBO DE COMPENSA��O - PASS�VEL DE REAPRESENTA��O: SIM';
         33: Result:= 'DIVERG�NCIA DE ENDOSSO - PASS�VEL DE REAPRESENTA��O: SIM';
         34: Result:= 'CHEQUE APRESENTADO POR ESTABELECIMENTO BANC�RIO QUE N�O O INDICADO NO CRUZAMENTO EM PRETO, SEM O ' +
                      'ENDOSSO-MANDATO - PASS�VEL DE REAPRESENTA��O: SIM';
         35: Result:= 'CHEQUE FRAUDADO, EMITIDO SEM PR�VIO CONTROLE OU RESPONSABILIDADE DO ESTABELECIMENTO BANC�RIO ' +
                      '("CHEQUE UNIVERSAL"), OU AINDA COM ADULTERA��O DA PRA�A SACADA - PASS�VEL DE REAPRESENTA��O: N�O';
         36: Result:= 'CHEQUE EMITIDO COM MAIS DE UM ENDOSSO - PASS�VEL DE REAPRESENTA��O: SIM';
         40: Result:= 'MOEDA INV�LIDA - PASS�VEL DE REAPRESENTA��O: N�O';
         41: Result:= 'CHEQUE APRESENTADO A BANCO QUE N�O O SACADO - PASS�VEL DE REAPRESENTA��O: SIM';
         42: Result:= 'CHEQUE N�O-COMPENS�VEL NA SESS�O OU SISTEMA DE COMPENSA��O EM QUE FOI APRESENTADO - ' +
                      'PASS�VEL DE REAPRESENTA��O: SIM';
         43: Result:= 'CHEQUE, DEVOLVIDO ANTERIORMENTE PELOS MOTIVOS 21, 22, 23, 24, 31 OU 34, N�O-PASS�VEL ' +
                      'DE REAPRESENTA��O EM VIRTUDE DE PERSISTIR O MOTIVO DA DEVOLU��O - PASS�VEL DE REAPRESENTA��O: N�O';
         44: Result:= 'CHEQUE PRESCRITO - PASS�VEL DE REAPRESENTA��O: N�O';
         45: Result:= 'CHEQUE EMITIDO POR ENTIDADE OBRIGADA A REALIZAR MOVIMENTA��O E UTILIZA��O DE RECURSOS FINANCEIROS ' +
                      'DO TESOURO NACIONAL MEDIANTE ORDEM BANC�RIA - PASS�VEL DE REAPRESENTA��O: N�O';
         48: Result:= 'CHEQUE DE VALOR SUPERIOR AO ESTABELECIDO, EMITIDO SEM A IDENTIFICA��O DO BENEFICI�RIO, DEVENDO SER ' +
                      'DEVOLVIDO A QUALQUER TEMPO - PASS�VEL DE REAPRESENTA��O: SIM';
         49: Result:= 'REMESSA NULA, CARACTERIZADA PELA REAPRESENTA��O DE CHEQUE DEVOLVIDO PELOS MOTIVOS 12, 13, 14, 20, ' +
                      '25, 28, 30, 35, 43, 44 E 45, PODENDO A SUA DEVOLU��O OCORRER A QUALQUER TEMPO - PASS�VEL DE REAPRESENTA��O: N�O';
      else
         Result:= IntToStrZero(CodMotivo,2) +' - Outros Motivos';
      end;
   else
      Result:= IntToStrZero(CodMotivo,2) +' - Outros Motivos';
   end;
end;

end.
