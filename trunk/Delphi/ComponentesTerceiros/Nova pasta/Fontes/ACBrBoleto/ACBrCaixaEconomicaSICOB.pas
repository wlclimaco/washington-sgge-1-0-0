{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:   Jo�o Elson                                    }
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

{
  Somente para SICOB
  by J�ter Rabelo Ferreira - 07/2011
}

{$I ACBr.inc}

unit ACBrCaixaEconomicaSICOB;

interface

uses
  Classes, SysUtils, ACBrBoleto,
  {$IFDEF COMPILER6_UP} DateUtils {$ELSE} ACBrD5, FileCtrl {$ENDIF};

type

  { TACBrCaixaEconomicaSICOB}

  TACBrCaixaEconomicaSICOB = class(TACBrBancoClass)
   protected
   private
    function FormataNossoNumero(const ACBrTitulo :TACBrTitulo): String;
    function CalcularDVAgCD(Header: Boolean = False): string;
   public
    Constructor create(AOwner: TACBrBanco);
    function CalcularDigitoVerificador(const ACBrTitulo: TACBrTitulo ): String; override;
    function CalcularDVCedente(const ACBrTitulo: TACBrTitulo; UsaAgencia: boolean = false ): String;
    function MontarCodigoBarras(const ACBrTitulo : TACBrTitulo): String; override;
    function MontarCampoCodigoCedente(const ACBrTitulo: TACBrTitulo): String; override;
    function MontarCampoNossoNumero(const ACBrTitulo :TACBrTitulo): String; override;
    function GerarRegistroHeader240(NumeroRemessa : Integer): String; override;
    function GerarRegistroTransacao240(ACBrTitulo : TACBrTitulo): String; override;
    function GerarRegistroTrailler240(ARemessa : TStringList): String;  override;
    procedure LerRetorno240(ARetorno:TStringList); override;

    function TipoOcorrenciaToDescricao(const TipoOcorrencia: TACBrTipoOcorrencia) : String; override;
    function CodOcorrenciaToTipo(const CodOcorrencia:Integer): TACBrTipoOcorrencia; override;
    function TipoOCorrenciaToCod(const TipoOcorrencia: TACBrTipoOcorrencia):String; override;
    function CodMotivoRejeicaoToDescricao(const TipoOcorrencia:TACBrTipoOcorrencia; CodMotivo:Integer): String; override;

    function CalcularTamMaximoNossoNumero(const Carteira : String; NossoNumero : String = ''): Integer; override;
   end;

implementation

uses ACBrUtil, StrUtils, Variants, math;

constructor TACBrCaixaEconomicaSICOB.create(AOwner: TACBrBanco);
begin
   inherited create(AOwner);
   fpDigito := 0;
   fpNome   := 'Caixa Economica Federal';
   fpNumero:= 104;
   fpTamanhoMaximoNossoNum := 0;
   fpTamanhoAgencia := 5;
   fpTamanhoConta   := 8;

   fpOrientacoesBanco.Clear;
   fpOrientacoesBanco.Add(ACBrStr('SAC CAIXA: 0800 726 0101 (informa��es,reclama��es e elogios) ' + sLineBreak+
                          'Para pessoas com defici�ncia auditiva ou de fala: 0800 726 2492 ' + sLineBreak +
                          'Ouvidoria: 0800 725 7474 (reclama��es n�o solucionadas e den�ncias)') + sLineBreak+
                          '     caixa.gov.br      ');
end;

function TACBrCaixaEconomicaSICOB.CalcularDigitoVerificador(const ACBrTitulo: TACBrTitulo ): String;
var
  Num, Res :String;
begin
   Result := '0';
   Num := OnlyNumber(FormataNossoNumero(ACBrTitulo));
   Modulo.CalculoPadrao;
   Modulo.MultiplicadorFinal   := 2;
   Modulo.MultiplicadorInicial := 9;
   Modulo.Documento := Num;
   Modulo.Calcular;

   Res:= IntToStr(Modulo.ModuloFinal);

   if Length(Res) > 1 then
      Result := '0'
   else
      Result := Res[1];
end;

function TACBrCaixaEconomicaSICOB.CalcularDVAgCD(Header: Boolean): string;
var
  Num, ACedente, Res :String;
begin
  Result := '0';
  with ACBrBanco.ACBrBoleto.Cedente do
  begin
    // Retirar o c�digo da oper�c�o do'c�digo do cedetnet,
    // sempre com 3 digitos, ex: 870
    // Sem o DV
    if Header then
      ACedente := padR(RightStr(CodigoCedente,8), 8, '0')
    else
      ACedente := padR(RightStr(CodigoCedente,8), 12, '0');

    Num := Agencia + ACedente;
    Modulo.CalculoPadrao;
    Modulo.MultiplicadorFinal   := 9;
    Modulo.MultiplicadorInicial := 2;
    Modulo.Documento := Num;
    Modulo.Calcular;

    Res:= IntToStr(Modulo.DigitoFinal);
    if Length(Res) > 1 then
       Result := '0'
    else
       Result := Res[1];
  end;
end;

function TACBrCaixaEconomicaSICOB.CalcularDVCedente(const ACBrTitulo: TACBrTitulo;
   UsaAgencia: boolean = false): String;
var
  Num, Res: string;
begin
  if UsaAgencia then
     Num:=  RightStr(ACBrTitulo.ACBrBoleto.Cedente.Agencia,4) +
            Copy(ACBrTitulo.ACBrBoleto.Cedente.CodigoCedente,
                 Length(ACBrTitulo.ACBrBoleto.Cedente.CodigoCedente)-10,11)
  else
     Num := copy(ACBrTitulo.ACBrBoleto.Cedente.CodigoCedente,
                 Length(ACBrTitulo.ACBrBoleto.Cedente.CodigoCedente)-7,8);

  Modulo.CalculoPadrao;
  Modulo.MultiplicadorFinal   := 9;
  Modulo.MultiplicadorInicial := 2;
  Modulo.Documento := Num;
  Modulo.Calcular;
  Res := intTostr(Modulo.DigitoFinal);

  if Length(Res) > 1 then
     Result := '0'
  else
     Result := Res[1];
end;

function TACBrCaixaEconomicaSICOB.CodMotivoRejeicaoToDescricao(
  const TipoOcorrencia: TACBrTipoOcorrencia; CodMotivo: Integer): String;
begin
   case TipoOcorrencia of
      toRetornoRegistroConfirmado,
      toRetornoRegistroRecusado,
      toRetornoInstrucaoRejeitada,
      toRetornoALteracaoOutrosDadosRejeitada:
      case CodMotivo of
        01: Result := '01-C�digo do banco inv�lido';
        02: Result := '02-C�digo do registro inv�lido.';
        03: Result := '03-C�digo do segmento inv�lido.';
        04: Result := '04-Transfer�ncia de Carteira/Entrada.';
        05: Result := '05-C�digo de movimento inv�lido.';
        06: Result := '06-Tipo/n�mero de inscri��o do cedente inv�lido.';
        07: Result := '07-Ag�ncia/Conta/DV inv�lido.';
        08: Result := '08-Nosso n�mero inv�lido.';
        09: Result := '09-Nosso n�mero duplicado.';
        10: Result := '10-Carteira inv�lida.';
        11: Result := '11-Forma de cadastramento do t�tulo inv�lido.';
        12: Result := '12-Tipo de documento inv�lido.';
        13: Result := '13-Identifica��o da emiss�o do bloqueto inv�lida.';
        14: Result := '14-Identifica��o da distribui��o do bloqueto inv�lida.';
        15: Result := '15-Caracter�sticas da cobran�a incompat�veis.';
        16: Result := '16-Data de vencimento inv�lida.';
        20: Result := '20-Valor do t�tulo inv�lido.';
        21: Result := '21-Esp�cie do t�tulo inv�lida.';
        23: Result := '23-Aceite inv�lido.';
        24: Result := '24-Data da emiss�o inv�lida.';
        26: Result := '26-C�digo de juros de mora inv�lido.';
        27: Result := '27-Valor/Taxa de juros de mora inv�lido.';
        28: Result := '28-C�digo do desconto inv�lido.';
        29: Result := '29-Valor do desconto maior ou igual ao valor do t�tulo.';
        30: Result := '30-Desconto a conceder n�o confere.';
        32: Result := '32-Valor do IOF inv�lido.';
        33: Result := '33-Valor do abatimento inv�lido.';
        37: Result := '37-C�digo para protesto inv�lido.';
        38: Result := '38-Prazo para protesto inv�lido.';
        40: Result := '40-T�tulo com ordem de protesto emitida.';
        42: Result := '42-C�digo para baixa/devolu��o inv�lido.';
        43: Result := '43-Prazo para baixa/devolu��o inv�lido.';
        44: Result := '44-C�digo da moeda inv�lido.';
        45: Result := '45-Nome do sacado n�o informado.';
        46: Result := '46-Tipo/n�mero de inscri��o do sacado inv�lido.';
        47: Result := '47-Endere�o do sacado n�o informado.';
        48: Result := '48-CEP inv�lido.';
        49: Result := '49-CEP sem pra�a de cobran�a (n�o localizado).';
        52: Result := '52-Unidade da federa��o inv�lida.';
        53: Result := '53-Tipo/n�mero de inscri��o do sacador/avalista inv�lido.';
        57: Result := '57-C�digo da multa inv�lido.';
        58: Result := '58-Data da multa inv�lida.';
        59: Result := '59-Valor/Percentual da multa inv�lido.';
        60: Result := '60-Movimento para t�tulo n�o cadastrado. Erro gen�rico para as situa��es:' + #13#10 +
                          '�Cedente n�o cadastrado� ou' + #13#10 +
                          '�Ag�ncia Cedente n�o cadastrada ou desativada�.';
        61: Result := '61-Ag�ncia cobradora inv�lida.';
        62: Result := '62-Tipo de impress�o inv�lido.';
        63: Result := '63-Entrada para t�tulo j� cadastrado.';
        68: Result := '68-Movimenta��o inv�lida para o t�tulo.';
        69: Result := '69-Altera��o de dados inv�lida.';
        70: Result := '70-Apelido do cliente n�o cadastrado.';
        71: Result := '71-Erro na composi��o do arquivo.';
        72: Result := '72-Lote de servi�o inv�lido.';
        73: Result := '73-C�digo do cedente inv�lido.';
        74: Result := '74-Cedente n�o pertence a cobran�a eletr�nica/apelido n�o confere com cedente.';
        75: Result := '75-Nome da empresa inv�lido.';
        76: Result := '76-Nome do banco inv�lido.';
        77: Result := '77-C�digo da remessa inv�lido';
        78: Result := '78-Data/Hora de gera��o do arquivo inv�lida.';
        79: Result := '79-N�mero seq�encial do arquivo inv�lido.';
        80: Result := '80-N�mero da vers�o do Layout do arquivo/lote inv�lido.';
        81: Result := '81-Literal �REMESSA-TESTE� v�lida somente para fase de testes.';
        82: Result := '82-Literal �REMESSA-TESTE� obrigat�rio para fase de testes.';
        83: Result := '83-Tipo/n�mero de inscri��o da empresa inv�lido.';
        84: Result := '84-Tipo de opera��o inv�lido.';
        85: Result := '85-Tipo de servi�o inv�lido.';
        86: Result := '86-Forma de lan�amento inv�lido.';
        87: Result := '87-N�mero da remessa inv�lido.';
        88: Result := '88-N�mero da remessa menor/igual que da remessa anterior.';
        89: Result := '89-Lote de servi�o divergente.';
        90: Result := '90-N�mero seq�encial do registro inv�lido.';
        91: Result := '91-Erro na seq��ncia de segmento do registro detalhe.';
        92: Result := '92-C�digo de movimento divergente entre grupo de segmentos.';
        93: Result := '93-Quantidade de registros no lote inv�lido.';
        94: Result := '94-Quantidade de registros no lote divergente.';
        95: Result := '95-Quantidade de lotes do arquivo inv�lido.';
        96: Result := '96-Quantidade de lotes no arquivo divergente.';
        97: Result := '97-Quantidade de registros no arquivo inv�lido.';
        98: Result := '98-Quantidade de registros no arquivo divergente.';
      end;
    toRetornoLiquidado,
    toRetornoBaixado,
    toRetornoLiquidadoAposBaixaOuNaoRegistro:
      case CodMotivo of
        02: Result := '02-Casas Lot�ricas.';
        03: Result := '03-Liquida��o no pr�prio Banco.';
        04: Result := '04-Compensa��o Eletr�nica.';
        05: Result := '05-Compensa��o Convencional.';
        06: Result := '06-Outros Canais.';
        07: Result := '07-Correspondente N�o Banc�rio.';
        08: Result := '08-Em Cart�rio.';
        09: Result := '09-Comandada Banco.';
        10: Result := '10-Comandada Cliente Arquivo.';
      end;
    toRetornoDebitoTarifas:
      case CodMotivo of
        01: Result := '01-Tarifa de Extrato de Posi��o';
        02: Result := '02-Tarifa de Manuten��o de T�tulo Vencido';
        03: Result := '03-Tarifa de Susta��o';
        04: Result := '04-Tarifa de Protesto';
        05: Result := '05-Tarifa de Outras Instru��es';
        06: Result := '06-Tarifa de Outras Ocorr�ncias';
        07: Result := '07-Tarifa de Envio de Duplicata ao Sacado';
        08: Result := '08-Custas de Protesto';
        09: Result := '09-Custas de Susta��o de Protesto';
        10: Result := '10-Custas de Cart�rio Distribuidor';
        11: Result := '11-Custas de Edital';
      end;
  end;
end;

function TACBrCaixaEconomicaSICOB.CalcularTamMaximoNossoNumero(
  const Carteira: String; NossoNumero: String): Integer;
var
  wTamNossoNumero: Integer;
begin
   Result:= 15;

   wTamNossoNumero:= length(NossoNumero);

   if ((wTamNossoNumero >= 8)  and (wTamNossoNumero <= 10)) or
      ((wTamNossoNumero >= 14) and (wTamNossoNumero <= 15)) then
      Result := wTamNossoNumero;
end;

function TACBrCaixaEconomicaSICOB.CodOcorrenciaToTipo(
  const CodOcorrencia: Integer): TACBrTipoOcorrencia;
begin
  case CodOcorrencia of
    02: Result := toRetornoRegistroConfirmado;
    03: Result := toRetornoRegistroRecusado;
//'04-Transfer�ncia de Carteira/Entrada
//'05-Transfer�ncia de Carteira/Baixa
    06: Result := toRetornoLiquidado;
    09: Result := toRetornoBaixadoViaArquivo;
    12: Result := toRetornoAbatimentoConcedido;
    13: Result := toRetornoAbatimentoCancelado;
    14: Result := toRetornoVencimentoAlterado;
    17: Result := toRetornoLiquidadoAposBaixaouNaoRegistro;
    19: Result := toRetornoRecebimentoInstrucaoProtestar;
    20: Result := toRetornoRecebimentoInstrucaoSustarProtesto;
    23: Result := toRetornoEncaminhadoACartorio;
    24: Result := toRetornoRetiradoDeCartorio;
    25: Result := toRetornoProtestado;
    26: Result := toRetornoInstrucaoRejeitada;
    27: Result := toRetornoAlteracaoDadosNovaEntrada;
    28: Result := toRetornoDebitoTarifas;
    30: Result := toRetornoALteracaoOutrosDadosRejeitada;
//'36-Confirma��o de envio de e-mail/SMS
//'37-Envio de e-mail/SMS rejeitado
    43: Result := toRetornoProtestoSustado;
    44: Result := toRetornoProtestoSustado;
    45: Result := toRetornoAlteracaoDadosNovaEntrada;
//'51-T�tulo DDA reconhecido pelo sacado
//'52-T�tulo DDA n�o reconhecido pelo sacado
//'53-T�tulo DDA recusado pela CIP  else
  else
    Result := toRetornoOutrasOcorrencias;
  end;
end;

function TACBrCaixaEconomicaSICOB.FormataNossoNumero(const ACBrTitulo :TACBrTitulo): String;
var
  ANossoNumero: String;
  wTamNossoNum: Integer;
begin
   with ACBrTitulo do
   begin

      ANossoNumero := OnlyNumber(NossoNumero);
      wTamNossoNum := CalcularTamMaximoNossoNumero(Carteira,ANossoNumero);

      if (wTamNossoNum = 10) or (wTamNossoNum = 15) then
         ANossoNumero:= ANossoNumero
      else
       begin
         if Carteira = 'SR' then
          begin
            if wTamNossoNum = 14 then
               ANossoNumero:= '8'+ padr(Copy(ANossoNumero,Length(ANossoNumero)-13,14),14)
            else
              ANossoNumero:= '82'+ padr(Copy(ANossoNumero,Length(ANossoNumero)-7,8),8);
          end
         else
            ANossoNumero:= '9' + padR(Copy(ANossoNumero,Length(ANossoNumero)-8,9),9,'0');
       end;
   end;
   Result := ANossoNumero;
end;

function TACBrCaixaEconomicaSICOB.MontarCodigoBarras(const ACBrTitulo : TACBrTitulo): String;
var
  CodigoBarras, FatorVencimento, DigitoCodBarras :String;
  ANossoNumero, CampoLivre,aCodCedente :String;
  teste: String;
begin
   FatorVencimento := CalcularFatorVencimento(ACBrTitulo.Vencimento);

   ANossoNumero := FormataNossoNumero(ACBrTitulo);
   aCodCedente:= padR(RightStr(ACBrTitulo.ACBrBoleto.Cedente.CodigoCedente,11),11,'0');

   {Montando Campo Livre}
   CampoLivre := ANossoNumero +
                 RightStr(ACBrTitulo.ACBrBoleto.Cedente.Agencia, 4) +
                 aCodCedente;

   {Codigo de Barras}
   with ACBrTitulo.ACBrBoleto do
   begin
      CodigoBarras := IntToStrZero(Banco.Numero, 3) +
                      '9' +
                      FatorVencimento +
                      IntToStrZero(Round(ACBrTitulo.ValorDocumento * 100), 10) +
                      CampoLivre;
   end;

   DigitoCodBarras := CalcularDigitoCodigoBarras(CodigoBarras);
   Result:= copy( CodigoBarras, 1, 4) + DigitoCodBarras + copy( CodigoBarras, 5, 44);
end;

function TACBrCaixaEconomicaSICOB.TipoOCorrenciaToCod(
  const TipoOcorrencia: TACBrTipoOcorrencia): String;
begin
  case TipoOcorrencia of
    toRetornoRegistroConfirmado                 : Result := '02';
    toRetornoRegistroRecusado                   : Result := '03';
    toRetornoLiquidado                          : Result := '06';
    toRetornoBaixadoViaArquivo                  : Result := '09';
    toRetornoAbatimentoConcedido                : Result := '12';
    toRetornoAbatimentoCancelado                : Result := '13';
    toRetornoVencimentoAlterado                 : Result := '14';
    toRetornoLiquidadoAposBaixaouNaoRegistro    : Result := '17';
    toRetornoRecebimentoInstrucaoProtestar      : Result := '19';
    toRetornoRecebimentoInstrucaoSustarProtesto : Result := '20';
    toRetornoEncaminhadoACartorio               : Result := '23';
    toRetornoRetiradoDeCartorio                 : Result := '24';
    toRetornoProtestado                         : Result := '25';
    toRetornoInstrucaoRejeitada                 : Result := '26';
    toRetornoAlteracaoDadosNovaEntrada          : Result := '27';
    toRetornoDebitoTarifas                      : Result := '28';
    toRetornoALteracaoOutrosDadosRejeitada      : Result := '30';
    toRetornoProtestoSustado                    : Result := '43';
  else
    Result := '02';  
  end;
end;

function TACBrCaixaEconomicaSICOB.TipoOcorrenciaToDescricao(const TipoOcorrencia: TACBrTipoOcorrencia): String;
var
  CodOcorrencia: Integer;
begin
  CodOcorrencia := StrToIntDef(TipoOCorrenciaToCod(TipoOcorrencia),0);
  case CodOcorrencia of
    02: Result := '02-Entrada Confirmada';
    03: Result := '03-Entrada Rejeitada';
    04: Result := '04-Transfer�ncia de Carteira/Entrada';
    05: Result := '05-Transfer�ncia de Carteira/Baixa';
    06: Result := '06-Liquida��o';
    09: Result := '09-Baixa';
    12: Result := '12-Confirma��o Recebimento Instru��o de Abatimento';
    13: Result := '13-Confirma��o Recebimento Instru��o de Cancelamento Abatimento';
    14: Result := '14-Confirma��o Recebimento Instru��o Altera��o de Vencimento';
    17: Result := '17-Liquida��o Ap�s Baixa ou Liquida��o T�tulo N�o Registrado';
    19: Result := '19-Confirma��o Recebimento Instru��o de Protesto';
    20: Result := '20-Confirma��o Recebimento Instru��o de Susta��o/Cancelamento de Protesto';
    23: Result := '23-Remessa a Cart�rio (Aponte em Cart�rio)';
    24: Result := '24-Retirada de Cart�rio e Manuten��o em Carteira';
    25: Result := '25-Protestado e Baixado (Baixa por Ter Sido Protestado)';
    26: Result := '26-Instru��o Rejeitada';
    27: Result := '27-Confirma��o do Pedido de Altera��o de Outros Dados';
    28: Result := '28-D�bito de Tarifas/Custas';
    30: Result := '30-Altera��o de Dados Rejeitada';
    36: Result := '36-Confirma��o de envio de e-mail/SMS';
    37: Result := '37-Envio de e-mail/SMS rejeitado';
    43: Result := '43-Estorno de Protesto/Susta��o';
    44: Result := '44-Estorno de Baixa/Liquida��o';
    45: Result := '45-Altera��o de dados';
    51: Result := '51-T�tulo DDA reconhecido pelo sacado';
    52: Result := '52-T�tulo DDA n�o reconhecido pelo sacado';
    53: Result := '53-T�tulo DDA recusado pela CIP';
  end;
end;

function TACBrCaixaEconomicaSICOB.MontarCampoCodigoCedente (
   const ACBrTitulo: TACBrTitulo ) : String;
begin

  with ACBrTitulo.ACBrBoleto do
  begin
     Result := RightStr(Cedente.Agencia,4) + '.'+
               Copy(Cedente.CodigoCedente, Length(Cedente.CodigoCedente)-10,3) +
               '.'+ Copy(Cedente.CodigoCedente, Length(Cedente.CodigoCedente)-7,8) +
               '-' +CalcularDVCedente(ACBrTitulo,true);

  end;
end;

function TACBrCaixaEconomicaSICOB.MontarCampoNossoNumero (const ACBrTitulo: TACBrTitulo ) : String;
var ANossoNumero : string;
begin
  ANossoNumero := FormataNossoNumero(ACBrTitulo);
  Result := ANossoNumero + '-' + CalcularDigitoVerificador(ACBrTitulo);
end;

function TACBrCaixaEconomicaSICOB.GerarRegistroHeader240(NumeroRemessa : Integer): String;
var
  ATipoInscricao, ACodConvenio: String;
  ACodCedenteDV, aCodCedente, ACodCedenteDVAg: String;
begin
   with ACBrBanco.ACBrBoleto.Cedente do
   begin
      case TipoInscricao of
        pFisica  : ATipoInscricao := '1';
        pJuridica: ATipoInscricao := '2';
      end;

      ACodCedenteDVAg := CalcularDVCedente(ACBrBanco.ACBrBoleto.ListadeBoletos[0],True);
      ACodCedenteDV   := CalcularDVCedente(ACBrBanco.ACBrBoleto.ListadeBoletos[0]);
      ACodConvenio    := CodigoCedente + ACodCedenteDVAg;

      aCodCedente:= RightStr(CodigoCedente,8);


      { GERAR REGISTRO-HEADER DO ARQUIVO }
      Result:= IntToStrZero(ACBrBanco.Numero, 3)            + //   1 a   3 - C�digo do banco
               '0000'                                       + //   4 a   7 - Lote de servi�o
               '0'                                          + //   8 a   8 - Tipo de registro - Registro header de arquivo
               space(9)                                     + //   9 a  17 - Uso exclusivo FEBRABAN/CNAB
               ATipoInscricao                               + //  18 a  18 - Tipo de inscri��o do cedente
               padL(OnlyNumber(CNPJCPF), 14, '0')           + //  19 a  32 - N�mero de inscri��o do cedente
               padL(CodigoCedente, 15, '0')+ACodCedenteDVAg + //  33 a  48 - C�digo do conv�nio no banco - Cedente
               space(4)                                     + //  49 a  52 - Uso Exclusivo CAIXA
               padR(OnlyNumber(Agencia),5,'0')              +
               padR(AgenciaDigito,1,'0')                    +
               padR(aCodCedente,12, '0')                    + //  59 a  70 - C�digo do Cedente + DV C�digo Cedente
               ACodCedenteDV                                + //  71 a  71 - DV Codigo Cedente
               CalcularDVAgCD(True)                         + //  72 a  72 - Dig. Verif. Ag + Ced.
               padL(Nome, 30, ' ')                          + //  73 a 102 - Nome da Empresa
               padL('CAIXA ECONOMICA FEDERAL', 40, ' ')     + // 133 a 142 - Uso exclusivo FEBRABAN/CNAB
               '1'                                          + // 143 a 143 - C�digo de Remessa (1) / Retorno (2)
               FormatDateTime('ddmmyyyy', Now)              + // 144 a 151 - Data do de gera��o do arquivo
               FormatDateTime('hhmmss', Now)                + // 152 a 157 - Hora de gera��o do arquivo
               padR(IntToStr(NumeroRemessa), 6, '0')        + // 158 a 163 - N�mero seq�encial do arquivo
               '030'                                        + // 164 a 166 - N�mero da vers�o do layout do arquivo
               padL('',  5, '0')                            + // 167 a 171 - Densidade de grava��o do arquivo (BPI)
               space(20)                                    + // 172 a 191 - Uso reservado do banco
               padL('REMESSA-PRODUCAO', 20, ' ')            + // 192 a 211 - Uso reservado da empresa
  //             padL('REMESSA-PRODU��O', 20, ' ')          + // 192 a 211 - Uso reservado da empresa Quando for produ��o, desmarar essa linha e bloquear a linha anterior
               space(29);                                 // 212 a 240 - Uso Exclusivo FEBRABAN / CNAB

      { GERAR REGISTRO HEADER DO LOTE }
      Result:= Result + #13#10 +
               IntToStrZero(ACBrBanco.Numero, 3)        + //   1 a   3 - C�digo do banco
               '0001'                                   + //   4 a   7 - Lote de servi�o
               '1'                                      + //   8 a   8 - Tipo de registro - Registro header de arquivo
               'R'                                      + //   9 a   9 - Tipo de opera��o: R (Remessa) ou T (Retorno)
               '01'                                     + //  10 a  11 - Tipo de servi�o: 01 (Cobran�a)
               '00'                                     + //  12 a  13 - Forma de lan�amento
               '020'                                    + //  14 a  16 - N�mero da vers�o do layout do lote
               Space(1)                                 + //  17 a  17 - Uso exclusivo FEBRABAN/CNAB
               ATipoInscricao                           + //  18 a  18 - Tipo de inscri��o da Empresa
               padR(OnlyNumber(CNPJCPF), 15, '0')       + //  19 a  33 - N�mero de inscri��o da Empresa
               ACodConvenio                             + //  34 a  39 - C�digo do conv�nio no banco (c�digo do cedente)
               space(4)                                 + //  50 a  53 - Uso Exclusivo da CAIXA
               padR(OnlyNumber(Agencia), 5 , '0')       + //  54 a  58 - Ag�ncia Mantenedora da Conta
               padR(AgenciaDigito, 1 , '0')             + //  59 a  59 - D�gito Verificador da Ag�ncia
               padR(aCodCedente,12, '0')                + //  60 a  71 - C�d. Cedente + D�gito Verificador do Cedente
               ACodCedenteDV                            + //  72 a  72 - DV Codigo Cedente
               CalcularDVAgCD(True)                     + //  73 a  73 - Dig. Verif. Ag + Ced.
               padL(Nome, 30, ' ')                      + //  74 a 103 - Nome da Empresa
               space(40)                                + // 104 a 143 - Mensagem 1 para todos os boletos do lote
               space(40)                                + // 144 a 183 - Mensagem 2 para todos os boletos do lote
               padR(IntToStr(NumeroRemessa), 8, '0')    + // 184 a 191 - N�mero do arquivo
               FormatDateTime('ddmmyyyy', Now)          + // 192 a 199 - Data de gera��o do arquivo
               '00000000'                               + // 200 a 207 - Data do cr�dito - S� para arquivo retorno
               space(33);                                 // 208 a 240 - Uso exclusivo FEBRABAN/CNAB
  end;
end;

function TACBrCaixaEconomicaSICOB.GerarRegistroTransacao240(ACBrTitulo : TACBrTitulo): String;
var
   ATipoInscricao, ATipoOcorrencia, ATipoBoleto :String;
   ADataMoraJuros, ADataDesconto, ANossoNumero  :String;
   ATipoAceite, ACodCedenteDV, aCodCedente      :String;
   aEspecieDoc: String;
begin
   with ACBrTitulo do
   begin
      ANossoNumero := FormataNossoNumero(ACBrTitulo)+CalcularDigitoVerificador(ACBrTitulo);

      {SEGMENTO P}
      ACodCedenteDV := CalcularDVCedente(ACBrBanco.ACBrBoleto.ListadeBoletos[0]);

      {Pegando tipo de pessoa do Cendente}
      case Sacado.Pessoa of
         pFisica  : ATipoInscricao := '1';
         pJuridica: ATipoInscricao := '2';
         pOutras  : ATipoInscricao := '9';
      end;

      {Pegando o Tipo de Ocorrencia}
      case OcorrenciaOriginal.Tipo of
         toRemessaBaixar                    : ATipoOcorrencia := '02';
         toRemessaConcederAbatimento        : ATipoOcorrencia := '04';
         toRemessaCancelarAbatimento        : ATipoOcorrencia := '05';
         toRemessaAlterarVencimento         : ATipoOcorrencia := '06';
         toRemessaConcederDesconto          : ATipoOcorrencia := '07';
         toRemessaCancelarDesconto          : ATipoOcorrencia := '08';
         toRemessaProtestar                 : ATipoOcorrencia := '09';
         toRemessaCancelarInstrucaoProtesto : ATipoOcorrencia := '10';
         toRemessaDispensarJuros            : ATipoOcorrencia := '31';
      else
         ATipoOcorrencia := '01';
      end;

      { Pegando o Aceite do Titulo }
      case Aceite of
         atSim :  ATipoAceite := 'A';
         atNao :  ATipoAceite := 'N';
      end;

      {Pegando a Esp�cie do Documento}
      if EspecieDoc = 'CH' then
        aEspecieDoc:= '01'
      else if EspecieDoc = 'DM' then
        aEspecieDoc:= '02'
      else if EspecieDoc = 'DMI' then
        aEspecieDoc:= '03'
      else if EspecieDoc = 'DS' then
        aEspecieDoc:= '04'
      else if EspecieDoc = 'DSI' then
        aEspecieDoc:= '05'
      else if EspecieDoc = 'DR' then
        aEspecieDoc:= '06'
      else if EspecieDoc = 'LC' then
        aEspecieDoc:= '07'
      else if EspecieDoc = 'NCC' then
        aEspecieDoc:= '08'
      else if EspecieDoc = 'NCE' then
        aEspecieDoc:= '09'
      else if EspecieDoc = 'NCI' then
        aEspecieDoc:= '10'
      else if EspecieDoc = 'NCR' then
        aEspecieDoc:= '11'
      else if EspecieDoc = 'NP' then
        aEspecieDoc:= '12'
      else if EspecieDoc = 'NPR' then
        aEspecieDoc:= '13'
      else if EspecieDoc = 'TM' then
        aEspecieDoc:= '14'
      else if EspecieDoc = 'TS' then
        aEspecieDoc:= '15'
      else if EspecieDoc = 'NS' then
        aEspecieDoc:= '16'
      else if EspecieDoc = 'RC' then
        aEspecieDoc:= '17'
      else if EspecieDoc = 'FAT' then
        aEspecieDoc:= '18'
      else if EspecieDoc = 'ND' then
        aEspecieDoc:= '19'
      else if EspecieDoc = 'AP' then
        aEspecieDoc:= '20'
      else if EspecieDoc = 'ME' then
        aEspecieDoc:= '21'
      else if EspecieDoc = 'PC' then
        aEspecieDoc:= '22'
      else
        aEspecieDoc:= '99';

      {Pegando Tipo de Boleto} //Quem emite e quem distribui o boleto?
      case ACBrBoleto.Cedente.ResponEmissao of
           tbBancoEmite      : ATipoBoleto := '1' + '1';
           tbCliEmite        : ATipoBoleto := '2' + '2';
           tbBancoReemite    : ATipoBoleto := '4' + '1';
           tbBancoNaoReemite : ATipoBoleto := '5' + '2';
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

      aCodCedente:= RightStr(ACBrBoleto.Cedente.CodigoCedente,8);

      Result:= IntToStrZero(ACBrBanco.Numero, 3)                          + //   1 a   3 - C�digo do banco
               '0001'                                                     + //   4 a   7 - Lote de Servi�o
               '3'                                                        + //   8 a   8 - Tipo do registro: Registro detalhe
               IntToStrZero((2 * ACBrBoleto.ListadeBoletos.IndexOf(ACBrTitulo))+ 1 ,5)  + //   9 a  13 - N� Sequencial do Registro no Lote
               'P'                                                        + //  14 a  14 - C�d. Segmento do Registro Detalhe
               ' '                                                        + //  15 a  15 - Uso Exclusivo FEBRABAN/CNAB
               ATipoOcorrencia                                            + //  16 a  17 - C�digo de Movimento Remessa
               padR(OnlyNumber(ACBrBoleto.Cedente.Agencia), 5, '0')       + //  18 a  22 - Ag�ncia mantenedora da conta
               padR(ACBrBoleto.Cedente.AgenciaDigito, 1 , '0')            + //  23 a  23 - D�gito verificador da ag�ncia
               padR(aCodCedente, 12, '0')                                 + //  24 a  35 - C�digo do Cedente
               ACodCedenteDV                                              + //  36 a  36 - Digito Verificador do Cedente
               CalcularDVAgCD                                             + //  37 a  37 - Digito Verificador da Ag. + Cedente
               space(9)                                                   + //  38 a  46 - Uso Exclusivo da CAIXA
               padR(ANossoNumero, 11, '0')                                + //  47 a  57 - Nosso n�mero - identifica��o do t�tulo no banco
               '1'                                                        + //  58 a  58 - C�digo da Carteira: 10Cobran�a Simples; 3-Cobran�a Caucionada; 4-Cobran�a Descontada
               '1'                                                        + //  59 a  59 - Forma de cadastramento do t�tulo no banco:  1-cobran�a Registrada | 2-Cobran�a sem registro
               '2'                                                        + //  60 a  60 - Tipo de documento: 1-Tradicional; 2-Escritural (Padr�o 2)
               ATipoBoleto                                                + //  61 a  62 - Identifica��o da Emiss�o do Bloqueto
               padL(NumeroDocumento, 11, ' ')                             + //  63 a  73 - N�mero do Documento de Cobran�a
               space(4)                                                   + //  74 a  77 - Uso Exclusivo CAIXA
               FormatDateTime('ddmmyyyy', Vencimento)                     + //  78 a  85 - Data de vencimento do t�tulo
               IntToStrZero(Round(ValorDocumento * 100), 15)              + //  86 a 100 - Valor nominal do t�tulo
               '00000'                                                    + // 101 a 105 - Ag�ncia cobradora. Se ficar em branco, a caixa determina automaticamente pelo CEP do sacado
               '0'                                                        + // 106 a 106 - DV Ag�ncia cobradora
               padL(aEspecieDoc,2)                                        + // 107 a 108 - Esp�cie do documento
               ATipoAceite                                                + // 109 a 109 - Identifica��o de t�tulo Aceito / N�o aceito
               FormatDateTime('ddmmyyyy', DataDocumento)                  + // 110 a 117 - Data da Emiss�o do T�tulo
               IfThen(ValorMoraJuros > 0, '1', '0')                       + // 118 a 118 - C�digo de juros de mora: Valor por dia
               ADataMoraJuros                                             + // 119 a 126 - Data a partir da qual ser�o cobrados juros
               IfThen(ValorMoraJuros > 0,
                      IntToStrZero( round(ValorMoraJuros * 100), 15),
                      padR('', 15, '0'))                                  + // 127 a 141 - Valor de juros de mora por dia
               IfThen(ValorDesconto > 0, '1', '0')                        + // 142 a 142 - C�digo de desconto: Valor fixo at� a data informada
               ADataDesconto                                              + // 143 a 150 - Data do desconto
               IfThen(ValorDesconto > 0,
                      IntToStrZero( round(ValorDesconto * 100), 15),
                      padR('', 15, '0'))                                  + // 151 a 165 - Valor do desconto por dia
               IntToStrZero( round(ValorIOF * 100), 15)                   + // 166 a 180 - Valor do IOF a ser recolhido
               IntToStrZero( round(ValorAbatimento * 100), 15)            + // 181 a 195 - Valor do abatimento
               padL(IfThen(Trim(SeuNumero) = '', NumeroDocumento,SeuNumero), 25, ' ')                                   + // 196 a 220 - Identifica��o do t�tulo na empresa
               IfThen((DataProtesto <> null) and
                      (DataProtesto > Vencimento), '1', '3')              + // 221 a 221 - C�digo de protesto: Protestar em XX dias corridos
               IfThen((DataProtesto <> null) and
                      (DataProtesto > Vencimento),
                       padR(IntToStr(DaysBetween(DataProtesto,
                       Vencimento)), 2, '0'), '00')                       + // 222 a 223 - Prazo para protesto (em dias corridos)
               '2'                                                        + // 224 a 224 - C�digo para baixa/devolu��o: N�o baixar/n�o devolver
               padL('',3,'0')                                             + // 225 a 227 - Prazo para baixa/devolu��o (em dias corridos)
               '09'                                                       + // 228 a 229 - C�digo da moeda: Real
               Space(10)                                                  + // 230 a 239 - Uso Exclusivo FEBRABAN/CNAB
               Space(1);                                                    // 240 a 240 - Uso exclusivo FEBRABAN/CNAB
      {SEGMENTO Q}
      Result:= Result + #13#10 +
               IntToStrZero(ACBrBanco.Numero, 3)                          + //   1 a   3 - C�digo do banco
               '0001'                                                     + // 4 a 7 - Lote de Servi�o
               '3'                                                        + //   8 a   8 - Tipo do registro: Registro detalhe
               IntToStrZero((2 * ACBrBoleto.ListadeBoletos.IndexOf(ACBrTitulo))+ 2 ,5) + //   9 a  13 - N�mero do lote
               'Q'                                                        + //  14 a  14 - C�digo do segmento do registro detalhe
               ' '                                                        + //  15 a  15 - Uso exclusivo FEBRABAN/CNAB: Branco
               ATipoOcorrencia                                            + //  16 a  17 - C�digo de movimento
               {Dados do sacado}
               ATipoInscricao                                             + //  18 a  18 - Tipo inscricao
               padR(OnlyNumber(Sacado.CNPJCPF), 15, '0')                  + //  19 a  33 - N�mero de Inscri��o
               padL(Sacado.NomeSacado, 40, ' ')                           + //  34 a  73 - Nome sacado
               padL(Sacado.Logradouro +' '+
                    Sacado.Numero +' '+
                    Sacado.Complemento , 40, ' ')                         + //  74 a 113 - Endere�o
               padL(Sacado.Bairro, 15, ' ')                               + // 114 a 128 - bairro sacado
               padR(OnlyNumber(Sacado.CEP), 8, '0')                       + // 129 a 133 e 134 a 136- cep sacado prefixo e sufixo sem o tra�o"-" somente numeros
               padL(Sacado.Cidade, 15, ' ')                               + // 137 a 151 - cidade sacado
               padL(Sacado.UF, 2, ' ')                                    + // 152 a 153 - UF sacado
               {Dados do sacador/avalista}
               ATipoInscricao                                             + // 154 a 154  - Tipo de inscri��o: N�o informado {campo obrigatorio segunto manual da caixa}
               '000000000000000'                                          + // 155 a 169 - N�mero de inscri��o
               space(40)                                                  + // 170 a 209 - Nome do sacador/avalista
               space(3)                                                   + // 210 a 212 - Uso exclusivo FEBRABAN/CNAB
               space(20)                                                  + // 213 a 232 - Uso exclusivo FEBRABAN/CNAB
               space(8);                                                    // 233 a 240 - Uso exclusivo FEBRABAN/CNAB
    end;
end;

function TACBrCaixaEconomicaSICOB.GerarRegistroTrailler240( ARemessa : TStringList ): String;
begin
   {REGISTRO TRAILER DO LOTE}
   Result:= IntToStrZero(ACBrBanco.Numero, 3)                          + //    1 a   3 - C�digo do banco
            '0001'                                                     + //    7 a   4 - Lote de Servi�o
            '5'                                                        + //    8 a   8 - Tipo do registro: Registro trailer do lote
            Space(9)                                                   + //    9 a  17 - Uso exclusivo FEBRABAN/CNAB
            IntToStrZero((2 * ARemessa.Count), 6)                  + //   18 a  23 - Quantidade de Registro no Lote
            // Totaliza��o Cobran�a Simples
            padR('', 6, '0')                                           + //   24 a  29 - Quantidade t�tulos em cobran�a (Somente retorno)
            padR('',17, '0')                                           + //   30 a  46 - Valor dos t�tulos em carteiras (Somente retorno)
            // CNAB
            Space(6)                                                   + //   47 a  52 - Uso Exclusivo FEBRABAN/CNAB
            space(17)                                                  + //   53 a  69 - Uso Exclusivo FEBRABAN/CNAB
            // Totaliza��o Cobran�a Caucionada
            padR('', 6, '0')                                           + //   70 a  75 - Quantidade t�tulos em cobran�a (Somente retorno)
            padR('',17, '0')                                           + //   76 a  92 - Valor dos t�tulos em carteiras (Somente retorno)
            // Totaliza��o Cobran�a Descontada
            space(6)                                                   + //   93 a  98 - Quantidade t�tulos em cobran�a (Somente retorno)
            space(17)                                                  + //   99 a 115 - Valor dos t�tulos em carteiras (Somente retorno)
            space(8)                                                   + //  116 a 123 - Uso exclusivo FEBRABAN/CNAB
            space(117);                                                  //  124 a 240 - Uso exclusivo FEBRABAN/CNAB

   {GERAR REGISTRO TRAILER DO ARQUIVO}
   Result:= Result + #13#10 +
            IntToStrZero(ACBrBanco.Numero, 3)                          + //    1 a   3 - C�digo do banco
            '9999'                                                     + //    4 a   7 - Lote de servi�o
            '9'                                                        + //    8 a   8 - Tipo do registro: Registro trailer do arquivo
            space(9)                                                   + //    9 a  17 - Uso exclusivo FEBRABAN/CNAB}
            '000001'                                                   + //   18 a  23 - Quantidade de lotes do arquivo}
            IntToStrZero((2 * ARemessa.Count + 2), 6)                  + //   24 a  29 - Quantidade de registros do arquivo, inclusive este registro que est� sendo criado agora}
            space(6)                                                   + //   30 a  35 - Uso exclusivo FEBRABAN/CNAB}
            space(205);                                                  //   36 a 240 - Uso exclusivo FEBRABAN/CNAB}
end;

procedure TACBrCaixaEconomicaSICOB.LerRetorno240(ARetorno: TStringList);
var
  ContLinha: Integer;
  Titulo   : TACBrTitulo;
  Linha, rCedente, rCNPJCPF: String;
  rAgencia, rConta,rDigitoConta: String;
begin
   ContLinha := 0;

   if (copy(ARetorno.Strings[0],143,1) <> '2') then
      raise Exception.Create(ACBrStr(ACBrBanco.ACBrBoleto.NomeArqRetorno +
                             'n�o � um arquivo de retorno do '+ Nome));

   rAgencia     := trim(Copy(ARetorno[0],53,5));
   rConta       := trim(Copy(ARetorno[0],63,8));
   rDigitoConta := Copy(ARetorno[0],72,1);
   rCedente     := trim(Copy(ARetorno[0],73,30));

   ACBrBanco.ACBrBoleto.DataArquivo   := StringToDateTimeDef(Copy(ARetorno[0],144,2)+'/'+
                                                             Copy(ARetorno[0],146,2)+'/'+
                                                             Copy(ARetorno[0],148,4),0, 'DD/MM/YYYY' );
   ACBrBanco.ACBrBoleto.NumeroArquivo := StrToIntDef(Copy(ARetorno[0],158,6),0);                                                             

   if StrToIntDef(Copy(ARetorno[1],200,8),0) <> 0 then
      ACBrBanco.ACBrBoleto.DataCreditoLanc := StringToDateTimeDef(Copy(ARetorno[1],200,2)+'/'+
                                                                  Copy(ARetorno[1],202,2)+'/'+
                                                                  Copy(ARetorno[1],204,4),0, 'DD/MM/YYYY' );

   case StrToIntDef(Copy(ARetorno[1],18,1),0) of
     1: ACBrBanco.ACBrBoleto.Cedente.TipoInscricao:= pFisica;
     else
        ACBrBanco.ACBrBoleto.Cedente.TipoInscricao:= pJuridica;
   end;

   if ACBrBanco.ACBrBoleto.Cedente.TipoInscricao = pJuridica then
    begin
      rCNPJCPF := trim( Copy(ARetorno[1],19,15)) ;
      rCNPJCPF := RightStr(rCNPJCPF,14) ;
    end
   else
    begin
      rCNPJCPF := trim( Copy(ARetorno[1],23,11));
      rCNPJCPF := RightStr(rCNPJCPF,11) ;
    end;

   with ACBrBanco.ACBrBoleto do
   begin
     if (not LeCedenteRetorno) and (rCNPJCPF <> OnlyNumber(Cedente.CNPJCPF)) then
        raise Exception.Create(ACBrStr('CNPJ\CPF do arquivo inv�lido'));

     if (not LeCedenteRetorno) and ((rAgencia <> OnlyNumber(Cedente.Agencia)) or
         (rConta <> OnlyNumber(Cedente.Conta))) then
        raise Exception.Create(ACBrStr('Agencia\Conta do arquivo inv�lido'));

     if LeCedenteRetorno then
     begin
        Cedente.Nome    := rCedente;
        Cedente.CNPJCPF := rCNPJCPF;
        Cedente.Agencia := rAgencia;
        Cedente.AgenciaDigito:= '0';
        Cedente.Conta   := rConta;
        Cedente.ContaDigito:= rDigitoConta;

        case StrToIntDef(Copy(ARetorno[1],18,1),0) of
          1: Cedente.TipoInscricao:= pFisica;
        else
             Cedente.TipoInscricao:= pJuridica;
        end;
     end;
     ListadeBoletos.Clear;
   end;

   for ContLinha := 1 to ARetorno.Count - 2 do
   begin
      Linha := ARetorno[ContLinha] ;

      if Copy(Linha,14,1)= 'T' then //segmento T - S� cria ap�s passar pelo seguimento T depois U
         Titulo := ACBrBanco.ACBrBoleto.CriarTituloNaLista;

      with Titulo do
      begin
         if Copy(Linha,14,1)= 'T' then //segmento T
          begin
            SeuNumero        := copy(Linha,59,11);
            NumeroDocumento  := copy(Linha,106,25);

            NossoNumero := Copy(Copy(Linha,47,10), // sem o DV
                                Length(Copy(Linha,47,10))-TamanhoMaximoNossoNum ,
                                TamanhoMaximoNossoNum);
            OcorrenciaOriginal.Tipo := CodOcorrenciaToTipo(StrToIntDef(copy(Linha,16,2),0));

            if (Trim(Copy(Linha,214,2)) <> '00') then
            begin
               MotivoRejeicaoComando.Add(copy(Linha,214,2));
               DescricaoMotivoRejeicaoComando.Add(CodMotivoRejeicaoToDescricao(OcorrenciaOriginal.Tipo,StrToIntDef(Copy(Linha,214,2),0)));
            end;
            
            Vencimento := StringToDateTimeDef( Copy(Linha,74,2)+'/'+
                                               Copy(Linha,76,2)+'/'+
                                               Copy(Linha,78,4),0, 'DD/MM/YYYY' );
            ValorDocumento       := StrToFloatDef(Copy(Linha,82,15),0)/100;

          
            ValorDespesaCobranca := StrToFloatDef(Copy(Linha,199,15),0)/100;
            // Carteira             := Copy(Linha,40,2);
            // No SICOB n�o retorna o numero da carteira. Retorna o seguinte:
            // 1 = Cobran�a Simples
            // 3 = Cobran�a Caucionada
            // 4 = Cobran�a Descontada
         end
        {segmento U}
        else if Copy(Linha,14,1)= 'U' then
         begin
           if StrToIntDef(Copy(Linha,138,8),0) <> 0 then
              DataOcorrencia := StringToDateTimeDef( Copy(Linha,138,2)+'/'+
                                                     Copy(Linha,140,2)+'/'+
                                                     Copy(Linha,142,4),0, 'DD/MM/YYYY' );

           if StrToIntDef(Copy(Linha,146,8),0) <> 0 then
              DataCredito:= StringToDateTimeDef( Copy(Linha,146,2)+'/'+
                                                 Copy(Linha,148,2)+'/'+
                                                 Copy(Linha,150,4),0, 'DD/MM/YYYY' );

           ValorMoraJuros       := StrToFloatDef(Copy(Linha,18,15),0)/100;
           ValorDesconto        := StrToFloatDef(Copy(Linha,33,15),0)/100;
           ValorAbatimento      := StrToFloatDef(Copy(Linha,48,15),0)/100;
           ValorIOF             := StrToFloatDef(Copy(Linha,63,15),0)/100;
           ValorOutrasDespesas  := StrToFloatDef(Copy(Linha,108,15),0)/100;
           ValorOutrosCreditos  := StrToFloatDef(Copy(Linha,123,15),0)/100;

          
           ValorRecebido        := StrToFloatDef(Copy(Linha,78,15),0)/100;
        end;
      end;
   end;
end;


end.
