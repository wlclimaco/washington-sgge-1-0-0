{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:   DOUGLAS TYBEL                                 }
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
{ Desenvolvedor desta unit: DOUGLAS TYBEL -  dtybel@yahoo.com.br  -  www.facilassim.com.br  }
{                                                                              }
{******************************************************************************}

{Somente � aceito o Conv�nio Carteira 1 Sem Registro} 

{$I ACBr.inc}

unit ACBrBancoob;

interface

uses
  Classes, SysUtils, ACBrBoleto,
  {$IFDEF COMPILER6_UP} DateUtils {$ELSE} ACBrD5, FileCtrl {$ENDIF};

type

  { TACBrBancoob}

  TACBrBancoob = class(TACBrBancoClass)
   protected
   private
      I: Int64;
   public
    Constructor create(AOwner: TACBrBanco);
    function CalcularDigitoVerificador(const ACBrTitulo: TACBrTitulo ): String; override;
    function MontarCodigoBarras(const ACBrTitulo : TACBrTitulo): String; override;
    function MontarCampoCodigoCedente(const ACBrTitulo: TACBrTitulo): String; override;
    function MontarCampoNossoNumero(const ACBrTitulo :TACBrTitulo): String; override;
    procedure GerarRegistroHeader400(NumeroRemessa : Integer; aRemessa: TStringList); override;
    procedure GerarRegistroTransacao400(ACBrTitulo :TACBrTitulo; aRemessa: TStringList); override;
    procedure GerarRegistroTrailler400( ARemessa: TStringList  ); override;
    function GerarRegistroHeader240(NumeroRemessa : Integer): String; override;
    function GerarRegistroTransacao240(ACBrTitulo : TACBrTitulo): String; override;
    function GerarRegistroTrailler240(ARemessa : TStringList): String;  override;
    Procedure LerRetorno400(ARetorno:TStringList); override;
    function TipoOcorrenciaToDescricao(const TipoOcorrencia: TACBrTipoOcorrencia) : String; override;
    function CodOcorrenciaToTipo(const CodOcorrencia:Integer): TACBrTipoOcorrencia; override;
    function TipoOCorrenciaToCod(const TipoOcorrencia: TACBrTipoOcorrencia):String; override;
    function CodMotivoRejeicaoToDescricao(const TipoOcorrencia:TACBrTipoOcorrencia; CodMotivo:Integer): String; override;

   end;

implementation

uses ACBrUtil, StrUtils, Variants,ACBrValidador,math;

constructor TACBrBancoob.create(AOwner: TACBrBanco);
begin
   inherited create(AOwner);
   fpDigito := 0;
   fpNome   := 'SICOOB';
   fpNumero:= 756;
   fpTamanhoMaximoNossoNum := 7;
   fpTamanhoCarteira := 1;
   fpTamanhoConta   := 7;
end;

function TACBrBancoob.CalcularDigitoVerificador(const ACBrTitulo: TACBrTitulo ): String;
var
  Num, Res :String;
  i, base, digito : Integer;
const
  indice = '319731973197319731973';
begin

   Result := '0';

   Num :=  PadR(ACBrTitulo.ACBrBoleto.Cedente.Agencia, 4, '0') +
           PadR(ACBrTitulo.ACBrBoleto.Cedente.CodigoCedente, 10, '0') +
           PadR(trim(ACBrTitulo.NossoNumero), 7, '0');


   base := 0;
   for i := 1 to Length(Num) do
     base := base + ( StrToInt(copy(Num,i,1)) * StrToInt(copy(indice,i,1)) );

   digito := 11-((  base )-( trunc(base/11) * 11));
   //(Se o Resto for igual a 0 ou 1 ent�o o DV � igual a 0)
   if (digito > 9) then
      digito := 0;

   Res    := IntToStr(digito);
   Result := Res;

   { Para o c�lculo do d�gito verificador do nosso n�mero, dever� ser utilizada
     a f�rmula abaixo:
     N�mero da Cooperativa    9(4) � 3009
     C�digo do Cliente   9(10) � cedente
     Nosso N�mero   9(7) � Iniciado contagem em 1

     Constante para c�lculo  = 3197


     a) Concatenar na seq��ncia completando com zero � esquerda.
        Ex.: N�mero da Cooperativa  = 0001
             N�mero do Cliente(cedente)  = 1-9
             Nosso N�mero  = 21
             000100000000190000021

     b) Alinhar a constante com a seq��ncia repetindo de traz para frente.
        Ex.: 000100000000190000021
             319731973197319731973

     c) Multiplicar cada componente da seq��ncia com o seu correspondente da
        constante e somar os resultados.
        Ex.: 1*7 + 1*3 + 9*1 + 2*7 + 1*3 = 36

     d) Calcular o Resto atrav�s do M�dulo 11.
        Ex.: 36/11 = 3, resto = 3

     e) O resto da divis�o dever� ser subtra�do de 11 achando assim o DV
        (Se o Resto for igual a 0 ou 1 ent�o o DV � igual a 0).
        Ex.: 11 � 3 = 8, ent�o Nosso N�mero + DV = 21-8


     Mem�ria de C�lculo
     Coop.(4)|Cliente(10)		    |Nosso N�mero(7)
     3	   0	 0	9	0	0	0	0	1	3	6	3	5	2	5	9	3	1	1	5	1
     3	   1 	 9	7	3	1	9	7	3	1	9	7	3	1	9	7	3	1	9	7	3
     9	   0	 0	63	0	0	0	0	3	3	54	21	15	2	45	63	9	1	9	35	3 = soma = 335

     digito = 11-((  soma )-( resto inteiro (trunc) da divisao da soma por 11 * 11))
     digito = 11-((  335 )-(30*11))
     digito = 6 }
end;

function TACBrBancoob.MontarCodigoBarras(const ACBrTitulo : TACBrTitulo): String;
var
  CodigoBarras, FatorVencimento, DigitoCodBarras, ANossoNumero,ACarteira :String;
  CampoLivre : String;
begin

    FatorVencimento := CalcularFatorVencimento(ACBrTitulo.Vencimento);

    ANossoNumero := ACBrTitulo.NossoNumero+CalcularDigitoVerificador(ACBrTitulo);

    if (Length(ACBrTitulo.Carteira) > 0 )then
       ACarteira := '1'
    else
       raise Exception.Create( ACBrStr('Carteira Inv�lida.'+sLineBreak) ) ;

    {Montando Campo Livre}
    CampoLivre    := padR(trim(ACBrTitulo.ACBrBoleto.Cedente.Modalidade), 2, '0') +
                     padR(trim(ACBrTitulo.ACBrBoleto.Cedente.CodigoCedente), 7, '0') +
                     padR(Copy(ANossoNumero,1,8), 8, '0') +  //7 Sequenciais + 1 do digito
                     IntToStrZero(Max(1,ACBrTitulo.Parcela),3);


    {Codigo de Barras}
    with ACBrTitulo.ACBrBoleto do
    begin
       CodigoBarras := IntToStrZero(Banco.Numero, 3) +
                       '9' +
                       FatorVencimento +
                       IntToStrZero(Round(ACBrTitulo.ValorDocumento * 100), 10) +
                       padR(ACarteira, 1, '0') +
                       padR(OnlyNumber(Cedente.Agencia),4,'0') +
                       CampoLivre;
    end;

    DigitoCodBarras := CalcularDigitoCodigoBarras(CodigoBarras);
    Result:= copy( CodigoBarras, 1, 4) + DigitoCodBarras + copy( CodigoBarras, 5, 44);
end;

function TACBrBancoob.MontarCampoCodigoCedente (
   const ACBrTitulo: TACBrTitulo ) : String;
begin
  Result := ACBrTitulo.ACBrBoleto.Cedente.Agencia + '/'+
            ACBrTitulo.ACBrBoleto.Cedente.CodigoCedente;
end;

function TACBrBancoob.MontarCampoNossoNumero (const ACBrTitulo: TACBrTitulo ) : String;
begin
    Result := ACBrTitulo.NossoNumero + '-' + CalcularDigitoVerificador(ACBrTitulo);
end;

procedure TACBrBancoob.GerarRegistroHeader400(NumeroRemessa : Integer; aRemessa:TStringList);
var
  wLinha: String;
begin
   with ACBrBanco.ACBrBoleto.Cedente do
   begin
      wLinha:= '0'                                        + // ID do Registro
               '1'                                        + // ID do Arquivo( 1 - Remessa)
               'REMESSA'                                  + // Literal de Remessa
               '01'                                       + // C�digo do Tipo de Servi�o
               padL( 'COBRAN�A', 8 )                      + // Descri��o do tipo de servi�o
               Space(7)                                   + // Brancos
               padR( OnlyNumber(Agencia), 4 )             + // Prefixo da Cooperativa
               padR( AgenciaDigito, 1 )                   + // D�gito Verificador do Prefixo
               padR( trim(CodigoCedente), 9,'0' )         + // C�digo do Cliente/Cedente
               Space(6)                                   + // Brancos
               padR( Nome, 30 )                           + // Nome do Cedente
               padL( '756BANCOOBCED', 18 )                + // Identifica��o do Banco: "756BANCOOBCED"  //Enviado pelo pessoal da homologa��o por email
               FormatDateTime('ddmmyy',Now)               + // Data de gera��o do arquivo
               IntToStrZero(NumeroRemessa,7)              + // Seq�encial da Remessa: n�mero seq�encial acrescido de 1 a cada remessa. Inicia com "0000001"
               Space(287)                                 + // Brancos
               IntToStrZero(1,6);                           // Contador de Registros

      aRemessa.Text:= aRemessa.Text + UpperCase(wLinha);
   end;
end;

procedure TACBrBancoob.GerarRegistroTransacao400(ACBrTitulo :TACBrTitulo; aRemessa: TStringList);
var
  DigitoNossoNumero, Ocorrencia,aEspecie :String;
  TipoSacado, ATipoAceite,MensagemCedente:String;
  TipoCedente, wLinha :String;
  I: Integer;
begin

    if (Length(ACBrTitulo.Carteira) < 1 )then
       raise Exception.Create( ACBrStr('Carteira Inv�lida.'+sLineBreak) ) ;

   with ACBrTitulo do
   begin
      DigitoNossoNumero := CalcularDigitoVerificador(ACBrTitulo);

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

      { Pegando o Aceite do Titulo }
      case Aceite of
         atSim :  ATipoAceite := '1';
         atNao :  ATipoAceite := '0';
      end;

      {Pegando Especie}
      if trim(EspecieDoc) = 'DM' then
         aEspecie:= '01'
      else if trim(EspecieDoc) = 'NP' then
         aEspecie:= '02'
      else if trim(EspecieDoc) = 'NS' then
         aEspecie:= '03'
      else if trim(EspecieDoc) = 'CS' then
         aEspecie:= '04'
      else if trim(EspecieDoc) = 'ND' then
         aEspecie:= '11'
      else if trim(EspecieDoc) = 'DS' then
         aEspecie:= '12'
      else
         aEspecie := EspecieDoc;

      {Pegando Tipo de Sacado}
      case Sacado.Pessoa of
         pFisica   : TipoSacado := '01';
         pJuridica : TipoSacado := '02';
      else
         TipoSacado := '99';
      end;

      {Pegando Tipo de Cedente}
      if ACBrBoleto.Cedente.TipoInscricao  = pFisica then
         TipoCedente := '01'
      else
         TipoCedente := '02';

      with ACBrBoleto do
      begin
         MensagemCedente:= '';
         for I:= 0 to Mensagem.count-1 do
             MensagemCedente:= MensagemCedente + trim(Mensagem[i]);

         if length(MensagemCedente) > 40 then
            MensagemCedente:= copy(MensagemCedente,1,40);

         wLinha:= '1'                                                     +  // ID Registro
                  TipoCedente                                             +  // Identifica��o do Tipo de Inscri��o do Sacado 01 - CPF 02 - CNPJ
                  padR(onlyNumber(Cedente.CNPJCPF),14,'0')                +  // N�mero de Inscri��o do Cedente
                  padR(OnlyNumber(Cedente.Agencia), 4, '0')               +  // Ag�ncia
                  padR( Cedente.AgenciaDigito, 1, '0')                    +  // Ag�ncia digito
                  padR( OnlyNumber(Cedente.Conta)                         +  // Conta Corrente
                  Cedente.ContaDigito, 9, '0')                            +  // D�gito Conta Corrente
                  padR( '0', 6, '0')                                      +  // N�mero do Conv�nio de Cobran�a do Cedente fixo zeros: "000000"
                  Space(25)                                               +  // Brancos
                  padR( NossoNumero + DigitoNossoNumero, 12, '0')         +  // Nosso N�mero + //nosso numero com digito
                  '01'                                                    +  // N�mero da Parcela: "01" se parcela �nica
                  '00'                                                    +  // Grupo de Valor: "00"
                  Space(3)                                                +  // Brancos
                  Space(1)                                                +  // Indicativo de Mensagem ou Sacador/Avalista:
                  Space(3)                                                +  // Brancos
                  IntToStrZero( 0, 3)                                     +  // Varia��o da Carteira: "000"
                  IntToStrZero( 0, 1)                                     +  // Conta Cau��o: "0"
                  IntToStrZero( 0, 5)                                     +  // C�digo de responsabilidade: "00000"
                  IntToStrZero( 0, 1)                                     +  // DV do c�digo de responsabilidade: "0"
                  IntToStrZero( 0, 6)                                     +  // Numero do border�: �000000�
                  Space(5)                                                +  // Brancos
                  padR( trim(Cedente.Modalidade), 2, '0')                 +  // Carteira/Modalidade
                  Ocorrencia                                              +  // Ocorrencia (remessa)
                  padL( NumeroDocumento,  10)                             +  // N�mero do Documento
                  FormatDateTime( 'ddmmyy', Vencimento)                   +  // Data de Vencimento do T�tulo
                  IntToStrZero( Round( ValorDocumento * 100 ), 13)        +  // Valor do T�tulo
                  IntToStrZero( Banco.Numero, 3)                          +  // N�mero Banco: "756"
                  padR(OnlyNumber(Cedente.Agencia), 4, '0')               +  // Prefixo da Ag�ncia Cobradora: �0000�
                  padR( Cedente.AgenciaDigito, 1, ' ')                    +  // D�gito Verificador do Prefixo da Ag�ncia Cobradora: Brancos
                  padL(aEspecie,2)                                        +  // Esp�cie do T�tulo
                  ATipoAceite                                             +  // Identifica��o
                  FormatDateTime( 'ddmmyy', DataDocumento )               +  // 32 Data de Emiss�o
                  IntToStrZero( 0, 2)                                     +  // 33 Primeira instru��o (SEQ 34) = 00 e segunda (SEQ 35) = 00, n�o imprime nada.
                  IntToStrZero( 1, 2)                                     +  // 34 Primeira instru��o (SEQ 34) = 00 e segunda (SEQ 35) = 00, n�o imprime nada.
                  IntToStrZero( 0, 6)                                     +  // Taxa de mora m�s
                  IntToStrZero( 0, 6)                                     +  // Taxa de multa
                  Space(1)                                                +  // Brancos
                  IntToStrZero( 0, 6)                                     +  // Preencher com zeros quando n�o for concedido nenhum desconto.
                  IntToStrZero( 0, 13)                                    +  // Preencher com zeros quando n�o for concedido nenhum desconto.
                  IntToStrZero( 9 , 1)                                    +  // MOEDA 9 BRASIL
                  IntToStrZero( 0, 12)                                    +  // Valor IOF / Quantidade Monet�ria: "0000000000000"
                  IntToStrZero( 0, 13)                                    +  // Valor Abatimento
                  TipoSacado                                              +  // Tipo de Inscri��o do Sacado: 01 - CPF 02 - CNPJ
                  padR(onlyNumber(Sacado.CNPJCPF),14,'0')                 +  // N�mero de Inscri��o do Sacado
                  padL( Sacado.NomeSacado, 40, ' ')                       +  // Nome do Sacado
                  padL( Sacado.Logradouro +' '+ Sacado.Numero,37,' ')     +  // Endere�o Completo
                  padL( Sacado.Bairro,15,' ')                             +  // Endere�o Bairro
                  padL( Sacado.CEP,8,' ')                                 +  // Endere�o CEP
                  padL( Sacado.Cidade,15,' ')                             +  // Endere�o cidade
                  padL( Sacado.UF,2,' ')                                  +  // Endere�o uf
                  padL( trim(MensagemCedente) ,40,' ')                    +  // Observa��es/Mensagem ou Sacador/Avalista:
                  IntToStrZero( 0, 2)                                     +  // N�mero de Dias Para Protesto
                  Space(1)                                                +  // Brancos
                  IntToStrZero( aRemessa.Count + 1, 6 );                     // Contador de Registros;

         aRemessa.Text:= aRemessa.Text + UpperCase(wLinha);
      end;
   end;
end;

procedure TACBrBancoob.GerarRegistroTrailler400( ARemessa: TStringList );
var
  wLinha: String;
begin
   wLinha:= '9'                                  + // ID Registro
            Space(193)                           + // Brancos
            Space(40)                            + // Mensagem responsabilidade Cedente
            Space(40)                            + // Mensagem responsabilidade Cedente
            Space(40)                            +
            Space(40)                            +
            Space(40)                            +
            IntToStrZero( ARemessa.Count + 1, 6);  // Contador de Registros

   ARemessa.Text:= ARemessa.Text + UpperCase(wLinha);
end;


procedure TACBrBancoob.LerRetorno400(ARetorno: TStringList);
var
  ContLinha: Integer;
  Titulo   : TACBrTitulo;
  Linha, rCedente, rCNPJCPF : String;
begin
   ContLinha := 0;

   if (copy(ARetorno.Strings[0],1,9) <> '02RETORNO') then
      raise Exception.Create(ACBrStr(ACBrBanco.ACBrBoleto.NomeArqRetorno +
                             'n�o � um arquivo de retorno do '+ Nome));

   rCedente := trim(Copy(ARetorno[0],32,8));


   ACBrBanco.ACBrBoleto.DataArquivo   := StringToDateTimeDef(Copy(ARetorno[0],95,2)+'/'+
                                                             Copy(ARetorno[0],97,2)+'/'+
                                                             Copy(ARetorno[0],99,2),0, 'DD/MM/YY' );

   ACBrBanco.ACBrBoleto.DataCreditoLanc := StringToDateTimeDef(Copy(ARetorno[1],111,2)+'/'+
                                                               Copy(ARetorno[1],113,2)+'/'+
                                                               Copy(ARetorno[1],115,2),0, 'DD/MM/YY' );
   rCNPJCPF := trim( Copy(ARetorno[1],4,14)) ;

   with ACBrBanco.ACBrBoleto do
   begin
      Cedente.Nome    := rCedente;
      Cedente.CNPJCPF := rCNPJCPF;

      case StrToIntDef(Copy(ARetorno[1],2,2),0) of
         11: Cedente.TipoInscricao:= pFisica;
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
         OcorrenciaOriginal.Tipo     := CodOcorrenciaToTipo(StrToIntDef(
                                        copy(Linha,109,2),0));
         //05 = Liquida��o Sem Registro

         DataOcorrencia := StringToDateTimeDef( Copy(Linha,111,2)+'/'+
                                                Copy(Linha,113,2)+'/'+
                                                Copy(Linha,115,2),0, 'DD/MM/YY' );

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
         NossoNumero          := copy( Copy(Linha,63,11),Length( Copy(Linha,63,11) )-TamanhoMaximoNossoNum+1  ,TamanhoMaximoNossoNum);
         Carteira             := Copy(Linha,86,3);
         ValorDespesaCobranca := StrToFloatDef(Copy(Linha,182,13),0)/100;
         ValorOutrasDespesas  := StrToFloatDef(Copy(Linha,189,13),0)/100;

         if StrToIntDef(Copy(Linha,176,6),0) <> 0 then
            DataCredito:= StringToDateTimeDef( Copy(Linha,176,2)+'/'+
                                               Copy(Linha,178,2)+'/'+
                                               Copy(Linha,180,2),0, 'DD/MM/YY' );
      end;
   end;

end;

function TACBrBancoob.GerarRegistroHeader240(
  NumeroRemessa: Integer): String;
var
  ATipoInscricao: string;
begin
  I := 0;
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
               padL(AgenciaDigito, 1, '0')              + //58 - Digito ag�ncia do cedente
               padR(OnlyNumber(Conta), 12, '0')         + // 59 a 70 - N�mero da conta do cedente
               padL(ContaDigito, 1, '0')                + //71 - Digito conta do cedente
               ' '                                      + // 72 - D�gito verificador Ag/Conta (Brancos)
               padL(Nome, 30, ' ')                      + // 73 a 102 - Nome do cedente
               padL('SICOOB', 30, ' ')                  + // 103 a 132 - Nome do banco
               space(10)                                + // 133 A 142 - Brancos
               '1'                                      + // 143 - C�digo de Remessa (1) / Retorno (2)
               FormatDateTime('ddmmyyyy', Now)          + // 144 a 151 - Data do de gera��o do arquivo
               FormatDateTime('hhmmss', Now)            + // 152 a 157 - Hora de gera��o do arquivo
               '000001'                                 + // 158 a 163 - N�mero sequencial do arquivo retorno
               '081'                                    + // 164 a 166 - N�mero da vers�o do layout do arquivo
               '00000'                                  + // 167 a 171 - Zeros
               space(54)                                + // 172 a 225 - 54 Brancos
               space(3)                                 + // 226 a 228 - zeros
               space(12);                                 // 229 a 240 - Brancos
     { GERAR REGISTRO HEADER DO LOTE }
      Result:= Result + #13#10 +
               IntToStrZero(ACBrBanco.Numero, 3)       + //1 a 3 - C�digo do banco
               '0001'                                  + //4 a 7 - Lote de servi�o
               '1'                                     + //8 - Tipo de registro - Registro header de arquivo
               'R'                                     + //9 - Tipo de opera��o: R (Remessa) ou T (Retorno)
               '01'                                    + //10 a 11 - Tipo de servi�o: 01 (Cobran�a)
               '  '                                    + //12 a 13 - Forma de lan�amento: preencher com ZEROS no caso de cobran�a
               '040'                                   + //14 a 16 - N�mero da vers�o do layout do lote
               ' '                                     + //17 - Uso exclusivo FEBRABAN/CNAB
               ATipoInscricao                          + //18 - Tipo de inscri��o do cedente
               padR(OnlyNumber(CNPJCPF), 15, '0')      + //19 a 33 -N�mero de inscri��o do cedente
               space(20)                               + //34 a 53 - Brancos
               '0'                                     + // 54 - Zeros
               padR(OnlyNumber(Agencia), 4, '0')       + //55 a 58 - C�digo da ag�ncia do cedente
               padR(AgenciaDigito, 1, '0')             + //59 - Digito da agencia do cedente
               padR(OnlyNumber(Conta), 12, '0')        + //60 - 71  N�mero da conta do cedente
               padR(ContaDigito, 1, '0')               + //72 - Digito da conta
               ' '                                     + //73
               padL(Nome, 30, ' ')                     + //74 a 103 - Nome do cedente
               space(80)                               + // 104 a 183 - Brancos
               padR(IntToStr(NumeroRemessa) , 08, '0')       + // 184 a 191 - N�mero sequ�ncia do arquivo retorno.
               FormatDateTime('ddmmyyyy', Now)         + //192 a 199 - Data de gera��o do arquivo
               padR('', 8, '0')                        + //200 a 207 - Data do cr�dito - S� para arquivo retorno
               space(33);                                //208 a 240 - Uso exclusivo FEBRABAN/CNAB
    end;
end;

function TACBrBancoob.GerarRegistroTransacao240(
  ACBrTitulo: TACBrTitulo): String;
var AEspecieTitulo, ATipoInscricao, ATipoOcorrencia, ATipoBoleto, ADataMoraJuros,
    ADataDesconto,ATipoAceite,NossoNum : string;
begin
  NossoNum  := RemoveString('-', MontarCampoNossoNumero(ACBrTitulo));
  with ACBrTitulo do
    begin
      {SEGMENTO P}
      {Pegando o Tipo de Ocorrencia}
      case OcorrenciaOriginal.Tipo of
        toRemessaBaixar                    : ATipoOcorrencia := '02';
        toRemessaConcederAbatimento        : ATipoOcorrencia := '04';
        toRemessaCancelarAbatimento        : ATipoOcorrencia := '05';
        toRemessaAlterarVencimento         : ATipoOcorrencia := '06';
        toRemessaConcederDesconto          : ATipoOcorrencia := '07';
        toRemessaCancelarDesconto          : ATipoOcorrencia := '08';
        toRemessaCancelarInstrucaoProtesto : ATipoOcorrencia := '10';
      else
       ATipoOcorrencia := '01';
      end;
     {Pegando o Aceite do Titulo }
      case Aceite of
        atSim :  ATipoAceite := 'A';
        atNao :  ATipoAceite := 'N';
      end;
      {Pegando Tipo de Boleto} //Quem emite e quem distribui o boleto?
      case ACBrBoleto.Cedente.ResponEmissao of
        tbCliEmite        : ATipoBoleto := '2';
        tbBancoEmite      : ATipoBoleto := '1';
        tbBancoReemite    : ATipoBoleto := '3';
        tbBancoNaoReemite : ATipoBoleto := '4';
      end;
      {Pegando especie do titulo}
      if EspecieDoc = 'DM' then
        AEspecieTitulo := '02';
      {Mora Juros}
      if (ValorMoraJuros > 0) then
        begin
          if (DataMoraJuros <> Null) then
            ADataMoraJuros := FormatDateTime('ddmmyyyy', DataMoraJuros)
          else ADataMoraJuros := padR('', 8, '0');
        end
      else ADataMoraJuros := padR('', 8, '0');
     {Descontos}
     if (ValorDesconto > 0) then
       begin
         if(DataDesconto <> Null) then
           ADataDesconto := FormatDateTime('ddmmyyyy', DataDesconto)
         else  ADataDesconto := padR('', 8, '0');
       end
     else
       ADataDesconto := padR('', 8, '0');
      Result:= IntToStrZero(ACBrBanco.Numero, 3)                          + //1 a 3 - C�digo do banco
               '0001'                                                     + //4 a 7 - Lote de servi�o
               '3'                                                        +
               IntToStrZero((i)+ 1 ,5)                                    + //9 a 13 - N�mero seq�encial do registro no lote - Cada registro possui dois segmentos
               'P'                                                        + //14 - C�digo do segmento do registro detalhe
               ' '                                                        + //15 - Uso exclusivo FEBRABAN/CNAB: Branco
               ATipoOcorrencia                                            + //16 a 17 - C�digo de movimento
               '0'                                                        + // 18
               padR(OnlyNumber(ACBrBoleto.Cedente.Agencia),4,'0')         + //19 a 22 - Ag�ncia mantenedora da conta
               padR(ACBrBoleto.Cedente.AgenciaDigito, 1, '0')             + //23 Digito agencia
               padR(OnlyNumber(ACBrBoleto.Cedente.Conta), 12, '0')        + //24 a 35 - N�mero da Conta Corrente
               padR(ACBrBoleto.Cedente.ContaDigito , 1, '0')              + //36 - D�gito da Conta Corrente
               ' ';                                                        //37 - DV Ag�ncia/COnta Brancos
               if (ACBrBoleto.Cedente.ResponEmissao = tbCliEmite) then
                begin
                  Result := Result+padR(NossoNum, 10, '0')+ // 38 a 57 - Carteira
                            padR('01', 02, '0')+
                            padR('02', 02, '0')+
                            '4'+
                            Space(5);
                end
               else
                  Result := Result+Space(20);

               Result := Result+Carteira                                  +//58 a 58 carteira
                         '0'                                              +//59 Forma de cadastramento no banco
                         ' '                                              +//60 Brancos
                         ATipoBoleto                                      +// 61 Identifica��o da emiss�o do boleto
                         '2'                                              +//62  Identifica��o da distribui��o
                         padL(NumeroDocumento, 15, ' ')                   + // 63 a 77 - N�mero que identifica o t�tulo na empresa [ Alterado conforme instru��es da CSO Bras�lia ] {27-07-09}
                         FormatDateTime('ddmmyyyy', Vencimento)           + // 78 a 85 - Data de vencimento do t�tulo
                         IntToStrZero( round( ValorDocumento * 100), 15)  + // 86 a 100 - Valor nominal do t�tulo
                         '00000'                                          + // 101 a 105 - Ag�ncia cobradora. // Ficando com Zeros o Ita� definir� a ag�ncia cobradora pelo CEP do sacado
                         ' '                                              + // 106 - D�gito da ag�ncia cobradora
                         padL(AEspecieTitulo, 2)                          + // 107 a 108 - Esp�cie do documento
                         ATipoAceite                                      + // 109 - Identifica��o de t�tulo Aceito / N�o aceito
                         FormatDateTime('ddmmyyyy', DataDocumento)        + // 110 a 117 - Data da emiss�o do documento
                         '0'                                              + // 118 - Zeros
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
                         '1'                                                        + //221 - C�digo de protesto: Protestar em XX dias corridos
                         '00'                                                        + //222 a 223 - Prazo para protesto (em dias corridos)
                         '0'                                                        + // 224 - C�digo de Baixa
                         space(3)                                                   + // 225 A 227 - Dias para baixa
                         '09'                                                       +//
                         '0000000000'                                               +//Numero contrato da opera��o
                         ' ';
        Inc(i);
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
               IntToStrZero((i)+ 1 ,5)                                    + //9 a 13 - N�mero seq�encial do registro no lote - Cada registro possui dois segmentos
               'Q'                                                        + //C�digo do segmento do registro detalhe
               ' '                                                        + //Uso exclusivo FEBRABAN/CNAB: Branco
               '01'                                                       + // 16 a 17
              {Dados do sacado}
               ATipoInscricao                                             + // 18 a 18 Tipo inscricao
               padR(OnlyNumber(Sacado.CNPJCPF), 15, '0')                  + // 19 a 33
               padL(Sacado.NomeSacado, 40, ' ')                           + // 34 a 73
               padL(Sacado.Logradouro +' '+ Sacado.Numero +' '+ Sacado.Complemento , 40, ' ') + // 74 a 113
               padL(Sacado.Bairro, 15, ' ')                               +  // 114 a 128
               padR(Sacado.CEP, 8, '0')                                   +  // 129 a 136
               padL(Sacado.Cidade, 15, ' ')                               +  // 137 a 151
               padL(Sacado.UF, 2, ' ')                                    +  // 152 a 153
                        {Dados do sacador/avalista}
               '0'                                                        + //Tipo de inscri��o: N�o informado
               space(15)                                                  + //N�mero de inscri��o
               padL('', 30, ' ')                                          + //Nome do sacador/avalista
               space(10)                                                  + //Uso exclusivo FEBRABAN/CNAB
               padL('0',3, '0')                                           + //Uso exclusivo FEBRABAN/CNAB
               space(28);                                                   //Uso exclusivo FEBRABAN/CNAB
                Inc(i);
      //Registro detalhe R
      Result:= Result + #13#10 +
               IntToStrZero(ACBrBanco.Numero, 3)                          + //C�digo do banco
               '0001'                                                     + //N�mero do lote
               '3'                                                        + //Tipo do registro: Registro detalhe
               IntToStrZero((i)+ 1 ,5)                                    + //9 a 13 - N�mero seq�encial do registro no lote - Cada registro possui dois segmentos
               'R'                                                        + //C�digo do segmento do registro detalhe
               ' '                                                        + //Uso exclusivo FEBRABAN/CNAB: Branco
               '01'                                                       + // 16 a 17
               '0'                                                        +//18  tipo de desconto 2
               padR('0', 8, '0')                                          +//19 - 26 Numero da linha a ser impressa
               padR('0',15, '0')                                          +//27 - 41 Valor/Percentual
               '1'                                                        +//42
               padR('0', 8, '0')                                          +//43-50 data do desconto 3
               padR('0', 15, '0')                                         +//51-65 Valor ou percentual a ser concedido
               '1'                                                        +//66 C�digo da multa
               padR('0', 8, '0')                                          +//67-74 Data da multa
               padR('0', 15, '0')                                         +//75-89 Valor/Percentual a ser aplicado
               space(10)                                                  +//90-99 Informa��es do sacado
               space(40)                                                  +//100-139 Menssagem livre
               space(40)                                                  +//140-179 Menssagem livre
               space(20)                                                  +//180-199 Uso da FEBRABAN "Brancos"
               padR('0', 08, '0')                                         +//200-207 C�digo oco. sacado "0000000"
               padR('0', 3, '0')                                          +//208-210 C�digo do banco na conta de d�bito "000"
               padR('0', 5, '0')                                          +//211-215 C�digo da ag. debito
               ' '                                                        +//216 Digito da agencia
               padR('0', 12, '0')                                         +//217-228 Conta corrente para debito
               ' '                                                        +//229 Digito conta de debito
               ' '                                                        +//230 Dv agencia e conta
               '0'                                                        +//231 Aviso debito automatico
               space(9);                                                   //232-240 Uso FEBRABAN
           Inc(i);
      //Registro detalhe S
      Result:= Result + #13#10 +
               IntToStrZero(ACBrBanco.Numero, 3)                          + //C�digo do banco
               '0001'                                                     + //N�mero do lote
               '3'                                                        + //Tipo do registro: Registro detalhe
               IntToStrZero((i)+ 1 ,5)+ //9 a 13 - N�mero seq�encial do registro no lote - Cada registro possui dois segmentos
               'S'                                                        + //C�digo do segmento do registro detalhe
               ' '                                                        + //Uso exclusivo FEBRABAN/CNAB: Branco
               '01'                                                       + // 16 a 17
               '3'                                                        +//18 tipo impress�o
               space(222);                                                //217-228 Conta corrente para debito
               Inc(i);
  end;

end;

function TACBrBancoob.GerarRegistroTrailler240(
  ARemessa: TStringList): String;
begin
  {REGISTRO TRAILER DO LOTE}
  Result:= IntToStrZero(ACBrBanco.Numero, 3)                          + //C�digo do banco
           '0001'                                                     + //N�mero do lote
           '5'                                                        + //Tipo do registro: Registro trailer do lote
           Space(9)                                                   + //Uso exclusivo FEBRABAN/CNAB
           IntToStrZero((3 * ARemessa.Count-1), 6)                    + //Quantidade de Registro da Remessa
           IntToStrZero(ARemessa.Count-1, 6)                          + // Quantidade de t�tulos em cobran�a simples
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

function TACBrBancoob.CodMotivoRejeicaoToDescricao(
  const TipoOcorrencia: TACBrTipoOcorrencia; CodMotivo: Integer): String;
begin
  case TipoOcorrencia of

      //Tabela 1
      toRetornoRegistroRecusado, toRetornoEntradaRejeitadaCarne:
      case CodMotivo  of
         00: Result:='Outros Motivos';
      else
         Result := IntToStrZero(CodMotivo,2) +' - Outros Motivos';
      end;


      toRetornoLiquidado:
      case CodMotivo of
         01: Result := 'POR SALDO';
         02: Result := 'POR CONTA';
         03: Result := 'NO PROPRIO BANCO';
         04: Result := 'COMPENSA��O ELETRONICA';
         05: Result := 'COMPENSA��O CONVENCIONAL';
         06: Result := 'POR MEIO ELETR�NICO';
         07: Result := 'DEPOIS DE FERIADO LOCAL';
         08: Result := 'EM CART�RIO';

      else
         Result := IntToStrZero(CodMotivo,2) +' - Outros Motivos';
      end;

      toRetornoBaixado:
      case CodMotivo of
         09: Result := 'COMANDADA BANCO';
         10: Result := 'COMANDADA CLIENTE ARQUIVO';
         11: Result := 'COMANDADA CLIENTE ONLINE';
         12: Result := 'DECURSO PRAZO CLIENTE';
         13: Result := 'DECURSO PRAZO BANCO';
         14: Result := 'PROTESTADO';
         15: Result := 'TITULO EXCLUIDO';
      else
         Result := IntToStrZero(CodMotivo,2) +' - Outros Motivos';
      end;

  end;

end;

function TACBrBancoob.CodOcorrenciaToTipo(
  const CodOcorrencia: Integer): TACBrTipoOcorrencia;
begin
  case CodOcorrencia of
      02: Result := toRetornoRegistroConfirmado;
      03: Result := toRetornoRegistroRecusado;
      05: Result := toRetornoLiquidadoEmCartorio;
      06: Result := toRetornoLiquidado;
      07: Result := toRetornoRecebimentoInstrucaoConcederDesconto;
      08: Result := toRetornoRecebimentoInstrucaoCancelarDesconto;
      09: Result := toRetornoBaixaSimples;
      11: Result := toRetornoTituloEmSer;
      12: Result := toRetornoAbatimentoConcedido;
      13: Result := toRetornoAbatimentoCancelado;
      14: Result := toRetornoVencimentoAlterado;
      17: Result := toRetornoLiquidadoAposBaixaOuNaoRegistro;
      19: Result := toRetornoRecebimentoInstrucaoProtestar;
      20: Result := toRetornoRecebimentoInstrucaoSustarProtesto;
      21: Result := toRetornoRecebimentoInstrucaoProtestar;
      23: Result := toRetornoEncaminhadoACartorio;
      24: Result := toRetornoInstrucaoProtestoRejeitadaSustadaOuPendente;
      25: Result := toRetornoBaixaPorProtesto;
      26: Result := toRetornoInstrucaoRejeitada;
      27: Result := toRetornoDadosAlterados;
      28: Result := toRetornoDebitoTarifas;
      30: Result := toRetornoAlteracaoDadosRejeitados;
      40: Result := toRetornoRecebimentoInstrucaoAlterarTipoCobranca;
      42: Result := toRetornoRecebimentoInstrucaoAlterarTipoCobranca;
      43: Result := toRetornoRecebimentoInstrucaoAlterarTipoCobranca;
      51: Result := toRetornoTarifaMensalRefEntradasBancosCorrespCarteira;
      52: Result := toRetornoTarifaMensalBaixasCarteira;
      53: Result := toRetornoTarifaMensalBaixasBancosCorrespCarteira;
      98: Result := toRetornoProtestado;
      99: Result := toRetornoRegistroRecusado;

   else
      Result := toRetornoOutrasOcorrencias;
   end;

end;

function TACBrBancoob.TipoOCorrenciaToCod(
  const TipoOcorrencia: TACBrTipoOcorrencia): String;
begin
  case TipoOcorrencia of
      toRetornoRegistroConfirmado                           : Result :='02';
      toRetornoRegistroRecusado                             : Result :='03';
      toRetornoTransferenciaCarteira                        : Result :='04';
      toRetornoLiquidadoSemRegistro                         : Result :='05';
      toRetornoLiquidado                                    : Result :='06';
      toRetornoBaixaSimples                                 : Result :='09';
      toRetornoTituloEmSer                                  : Result :='11';
      toRetornoAbatimentoConcedido                          : Result :='12';
      toRetornoAbatimentoCancelado                          : Result :='13';
      toRetornoVencimentoAlterado                           : Result :='14';
      toRetornoLiquidadoEmCartorio                          : Result :='15';
      toRetornoRecebimentoInstrucaoProtestar                : Result :='19';
      toRetornoDebitoEmConta                                : Result :='20';
      toRetornoNomeSacadoAlterado                           : Result :='21';
      toRetornoEnderecoSacadoAlterado                       : Result :='22';
      toRetornoEncaminhadoACartorio                         : Result :='23';
      toRetornoInstrucaoProtestoRejeitadaSustadaOuPendente  : Result :='24';
      toRetornoRecebimentoInstrucaoDispensarJuros           : Result :='25';
      toRetornoInstrucaoRejeitada                           : Result :='26';
      toRetornoDadosAlterados                               : Result :='27';
      toRetornoManutencaoTituloVencido                      : Result :='28';
      toRetornoAlteracaoDadosRejeitados                     : Result :='30';
      toRetornoDespesasProtesto                             : Result :='96';
      toRetornoDespesasSustacaoProtesto                     : Result :='97';
      toRetornoDebitoCustasAntecipadas                      : Result :='98';
   else
      Result:= '02';
   end;

end;

function TACBrBancoob.TipoOcorrenciaToDescricao(
  const TipoOcorrencia: TACBrTipoOcorrencia): String;
var
   CodOcorrencia: Integer;
begin
   CodOcorrencia := StrToIntDef(TipoOCorrenciaToCod(TipoOcorrencia),0);

   case CodOcorrencia of
      02: Result:='02-CONFIRMA��O ENTRADA T�TULO' ;
      03: Result:='03-COMANDO RECUSADO' ;
      04: Result:='04-TRANSFERENCIA DE CARTEIRA - ENTRADA' ;
      05: Result:='05-LIQUIDA��O SEM REGISTRO';
      06: Result:='06-LIQUIDA��O NORMAL' ;
      09: Result:='09-BAIXA DE T�TULO' ;
      10: Result:='10-BAIXA SOLICITADA';
      11: Result:='11-T�TULOS EM SER' ;
      12: Result:='12-ABATIMENTO CONCEDIDO' ;
      13: Result:='13-ABATIMENTO CANCELADO' ;
      14: Result:='14-ALTERA��O DE VENCIMENTO' ;
      15: Result:='15-LIQUIDA��O EM CART�RIO' ;
      19: Result:='19-CONFIRMA��O INSTRU��O PROTESTO' ;
      20: Result:='20-D�BITO EM CONTA' ;
      21: Result:='21-ALTERA��O DE NOME DO SACADO' ;
      22: Result:='22-ALTERA��O DE ENDERE�O SACADO' ;
      23: Result:='23-ENCAMINHADO A PROTESTO' ;
      24: Result:='24-SUSTAR PROTESTO' ;
      25: Result:='25-DISPENSAR JUROS' ;
      26: Result:='26-INSTRU��O REJEITADA' ;
      27: Result:='27-CONFIRMA��O ALTERA��O DADOS' ;
      28: Result:='28-MANUTEN��O T�TULO VENCIDO' ;
      30: Result:='30-ALTERA��O DADOS REJEITADA' ;
      96: Result:='96-DESPESAS DE PROTESTO' ;
      97: Result:='97-DESPESAS DE SUSTA��O DE PROTESTO' ;
      98: Result:='98-DESPESAS DE CUSTAS ANTECIPADAS' ;
   end;

end;

end.
