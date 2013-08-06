////////////////////////////////////////////////////////////////////////////////
//                                                                            //
//              PCN - Projeto Cooperar NFe                                    //
//                                                                            //
//   Descri��o: Classes para gera��o/leitura dos arquivos xml da NFe          //
//                                                                            //
//        site: www.projetocooperar.org                                       //
//       email: projetocooperar@zipmail.com.br                                //
//       forum: http://br.groups.yahoo.com/group/projeto_cooperar_nfe/        //
//     projeto: http://code.google.com/p/projetocooperar/                     //
//         svn: http://projetocooperar.googlecode.com/svn/trunk/              //
//                                                                            //
// Coordena��o: (c) 2009 - Paulo Casagrande                                   //
//                                                                            //
//      Equipe: Vide o arquivo leiame.txt na pasta raiz do projeto            //
//                                                                            //
//      Vers�o: Vide o arquivo leiame.txt na pasta raiz do projeto            //
//                                                                            //
//     Licen�a: GNU Lesser General Public License (GNU LGPL)                  //
//                                                                            //
//              - Este programa � software livre; voc� pode redistribu�-lo    //
//              e/ou modific�-lo sob os termos da Licen�a P�blica Geral GNU,  //
//              conforme publicada pela Free Software Foundation; tanto a     //
//              vers�o 2 da Licen�a como (a seu crit�rio) qualquer vers�o     //
//              mais nova.                                                    //
//                                                                            //
//              - Este programa � distribu�do na expectativa de ser �til,     //
//              mas SEM QUALQUER GARANTIA; sem mesmo a garantia impl�cita de  //
//              COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM       //
//              PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter   //
//              mais detalhes. Voc� deve ter recebido uma c�pia da Licen�a    //
//              P�blica Geral GNU junto com este programa; se n�o, escreva    //
//              para a Free Software Foundation, Inc., 59 Temple Place,       //
//              Suite 330, Boston, MA - 02111-1307, USA ou consulte a         //
//              licen�a oficial em http://www.gnu.org/licenses/gpl.txt        //
//                                                                            //
//    Nota (1): - Esta  licen�a  n�o  concede  o  direito  de  uso  do nome   //
//              "PCN  -  Projeto  Cooperar  NFe", n�o  podendo o mesmo ser    //
//              utilizado sem previa autoriza��o.                             //
//                                                                            //
//    Nota (2): - O uso integral (ou parcial) das units do projeto esta       //
//              condicionado a manuten��o deste cabe�alho junto ao c�digo     //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////
unit pcnEventoNFe;

interface

uses SysUtils, Classes,
     {$IFNDEF VER130}
     Variants,
     {$ENDIF}
     pcnAuxiliar, pcnConversao;

type
  TInfEvento = class ;
  TDetEvento = class ;
  TRetInfEvento = class ;
  EventoException = class(Exception);

  TInfEvento = class
  private
    FID: String;
    FtpAmbiente: TpcnTipoAmbiente;
    FCNPJ: String;
    FcOrgao: integer;
    FChave: String;
    FDataEvento: TDateTime;
    FTpEvento: TpcnTpEvento;
    FnSeqEvento: Integer;
    FVersaoEvento: String;
    FDetEvento: TDetEvento;

    function getcOrgao: integer;
    function getVersaoEvento: String;
    function getDescEvento: string;
    function getTipoEvento: string;
  public
    constructor Create;
    destructor Destroy; override;
    function DescricaoTipoEvento(TipoEvento:TpcnTpEvento): String;

    property id: String              read FID         write FID;
    property cOrgao: integer         read getcOrgao   write FcOrgao;
    property tpAmb: TpcnTipoAmbiente read FtpAmbiente write FtpAmbiente;
    property CNPJ: String            read FCNPJ       write FCNPJ;
    property chNFe: String           read FChave      write FChave;
    property dhEvento: TDateTime     read FDataEvento write FDataEvento;
    property tpEvento: TpcnTpEvento  read FTpEvento   write FTpEvento;
    property nSeqEvento: Integer     read FnSeqEvento write FnSeqEvento;
    property versaoEvento: String    read getVersaoEvento write FversaoEvento;
    property detEvento: TDetEvento   read FDetEvento  write FDetEvento;
    property DescEvento: string      read getDescEvento;
    property TipoEvento: string      read getTipoEvento;
  end;

  TDetEvento = class
  private
    FVersao: String;
    FDescEvento: String;
    FCorrecao: String; //Carta de Corre��o
    FCondUso: String; //Carta de Corre��o
    FnProt: string; //Cancelamento
    FxJust: string; //Cancelamento e Manif. Destinatario
    procedure setCondUso(const Value: String);
  public
    property versao: string           read FVersao      write FVersao;
    property descEvento: string       read FDescEvento  write FDescEvento;
    property xCorrecao: String        read FCorrecao    write FCorrecao;
    property xCondUso: String         read FCondUso     write setCondUso;
    property nProt: String            read FnProt       write FnProt;
    property xJust: String            read FxJust       write FxJust;
  end;

  TRetInfEvento = class
  private
    FId: String;
    FtpAmb: TpcnTipoAmbiente;
    FverAplic: String;
    FcOrgao: Integer;
    FcStat: Integer;
    FxMotivo: String;
    FchNFe: String;
    FtpEvento: TpcnTpEvento;
    FxEvento: String;
    FnSeqEvento: Integer;
    FCNPJDest: String;
    FemailDest: String;
    FdhRegEvento: TDateTime;
    FnProt: String;
    FXML: AnsiString;
  public
  published
    property Id: string  read FId write FId;
    property tpAmb: TpcnTipoAmbiente read FtpAmb write FtpAmb;
    property verAplic: string  read FverAplic write FverAplic;
    property cOrgao: Integer read FcOrgao write FcOrgao;
    property cStat: integer read FcStat write FcStat;
    property xMotivo: string read FxMotivo write FxMotivo;
    property chNFe: String read FchNFe write FchNFe;
    property tpEvento: TpcnTpEvento read FtpEvento write FtpEvento;
    property xEvento: String read FxEvento write FxEvento;
    property nSeqEvento: Integer read FnSeqEvento write FnSeqEvento;
    property CNPJDest: string read FCNPJDest write FCNPJDest;
    property emailDest: String read FemailDest write FemailDest;
    property dhRegEvento: TDateTime read FdhRegEvento write FdhRegEvento;
    property nProt: String read FnProt write FnProt;
    property XML: AnsiString read FXML write FXML;
  end;

implementation

{ TInfEvento }

constructor TInfEvento.Create;
begin
  inherited Create;
  FDetEvento := TDetEvento.Create;
end;

destructor TInfEvento.Destroy;
begin
  FDetEvento.Free;
  inherited;
end;

function TInfEvento.getcOrgao: integer;
//  (AC,AL,AP,AM,BA,CE,DF,ES,GO,MA,MT,MS,MG,PA,PB,PR,PE,PI,RJ,RN,RS,RO,RR,SC,SP,SE,TO);
//  (12,27,16,13,29,23,53,32,52,21,51,50,31,15,25,41,26,22,33,24,43,11,14,42,35,28,17);
begin
  if FcOrgao <> 0 then
    Result := FcOrgao
  else
     Result := StrToIntDef(copy(FChave,1,2),0);

  if Result = 0 then
    raise EventoException.Create('Campo cOrgao n�o informado');
end;

function TInfEvento.getDescEvento: string;
begin
  case fTpEvento of
    teCCe                      : Result := 'Carta de Correcao';
    teCancelamento             : Result := 'Cancelamento';
    teManifDestConfirmacao     : Result := 'Confirmacao da Operacao';
    teManifDestCiencia         : Result := 'Ciencia da Operacao';
    teManifDestDesconhecimento : Result := 'Desconhecimento da Operacao';
    teManifDestOperNaoRealizada: Result := 'Opera��o nao Realizada';
  else
    raise EventoException.Create('Descri��o do Evento n�o Implementado!');
  end;
end;

function TInfEvento.getTipoEvento: string;
begin
  case FTpEvento of
    teCCe: Result              := '110110';//CCe
    teCancelamento: Result     := '110111';//Cancelamento
    teManifDestConfirmacao     : Result := '210200';//Manif. Destinatario: Confirmacao da Operacao
    teManifDestCiencia         : Result := '210210';//Manif. Destinatario: Ciencia da Operacao
    teManifDestDesconhecimento : Result := '210220';//Manif. Destinatario: Desconhecimento da Operacao
    teManifDestOperNaoRealizada: Result := '210240';//Manif. Destinatario: Opera��o nao Realizada
  else
    raise EventoException.Create('Tipo do Evento n�o Implementado!');
  end;
end;

function TInfEvento.getVersaoEvento: String;
begin
  Result := '1.00';
end;

function TInfEvento.DescricaoTipoEvento(TipoEvento: TpcnTpEvento): String;
begin
  case TipoEvento of
    teCCe                      : Result := 'CARTA DE CORRE��O ELETR�NICA';
    teCancelamento             : Result := 'CANCELAMENTO DE NF-e';
    teManifDestConfirmacao     : Result := 'CONFIRMA��O DA OPERA��O';
    teManifDestCiencia         : Result := 'CI�NCIA DA OPERA��O';
    teManifDestDesconhecimento : Result := 'DESCONHECIMENTO DA OPERA��O';
    teManifDestOperNaoRealizada: Result := 'OPERA��O N�O REALIZADA';
  else
    Result := 'N�o Definido';
  end;
end;

{ TDetEvento }

procedure TDetEvento.setCondUso(const Value: String);
begin
  FCondUso := Value;

  if FCondUso = '' then
    FCondUso := 'A Carta de Correcao e disciplinada pelo paragrafo 1o-A do' +
                ' art. 7o do Convenio S/N, de 15 de dezembro de 1970 e' +
                ' pode ser utilizada para regularizacao de erro ocorrido na' +
                ' emissao de documento fiscal, desde que o erro nao esteja' +
                ' relacionado com: I - as variaveis que determinam o valor' +
                ' do imposto tais como: base de calculo, aliquota, diferenca' +
                ' de preco, quantidade, valor da operacao ou da prestacao;' +
                ' II - a correcao de dados cadastrais que implique mudanca' +
                ' do remetente ou do destinatario; III - a data de emissao ou' +
                ' de saida.'
end;

end.
