{$I ACBr.inc}

unit ACBrProvedorGinfesV3;

interface

uses
  Classes, SysUtils,
  pnfsConversao, pcnAuxiliar,
  ACBrNFSeConfiguracoes, ACBrNFSeUtil, ACBrUtil, ACBrDFeUtil,
  {$IFDEF COMPILER6_UP} DateUtils {$ELSE} ACBrD5, FileCtrl {$ENDIF};

type
  { TACBrProvedorGinfes }

 TProvedorGinfesV3 = class(TProvedorClass)
  protected
   { protected }
  private
   { private }
  public
   { public }
   Constructor Create;

   function GetConfigCidade(ACodCidade, AAmbiente: Integer): TConfigCidade; OverRide;
   function GetConfigSchema(ACodCidade: Integer): TConfigSchema; OverRide;
   function GetConfigURL(ACodCidade: Integer): TConfigURL; OverRide;
   function GetURI(URI: String): String; OverRide;
   function GetAssinarXML(Acao: TnfseAcao): Boolean; OverRide;
   // Sugest�o de Rodrigo Cantelli
   function GetValidarLote: Boolean; OverRide;

   function Gera_TagI(Acao: TnfseAcao; Prefixo3, Prefixo4, NameSpaceDad, Identificador, URI: String): AnsiString; OverRide;
   function Gera_CabMsg(Prefixo2, VersaoLayOut, VersaoDados, NameSpaceCab: String; ACodCidade: Integer): AnsiString; OverRide;
   function Gera_DadosSenha(CNPJ, Senha: String): AnsiString; OverRide;
   function Gera_TagF(Acao: TnfseAcao; Prefixo3: String): AnsiString; OverRide;

   function Gera_DadosMsgEnviarLote(Prefixo3, Prefixo4, Identificador,
                                    NameSpaceDad, VersaoDados, VersaoXML,
                                    NumeroLote, CNPJ, IM, QtdeNotas: String;
                                    Notas, TagI, TagF: AnsiString): AnsiString; OverRide;
   function Gera_DadosMsgConsSitLote(Prefixo3, Prefixo4, NameSpaceDad,
                                     VersaoXML, Protocolo, CNPJ, IM: String;
                                     TagI, TagF: AnsiString): AnsiString; OverRide;
   function Gera_DadosMsgConsLote(Prefixo3, Prefixo4, NameSpaceDad,
                                  VersaoXML, Protocolo, CNPJ, IM: String;
                                  TagI, TagF: AnsiString): AnsiString; OverRide;
   function Gera_DadosMsgConsNFSeRPS(Prefixo3, Prefixo4, NameSpaceDad, VersaoXML,
                                     NumeroRps, SerieRps, TipoRps, CNPJ, IM: String;
                                     TagI, TagF: AnsiString): AnsiString; OverRide;
   function Gera_DadosMsgConsNFSe(Prefixo3, Prefixo4, NameSpaceDad, VersaoXML,
                                  CNPJ, IM: String;
                                  DataInicial, DataFinal: TDateTime;
                                  TagI, TagF: AnsiString; NumeroNFSe: string = ''): AnsiString; OverRide;
   function Gera_DadosMsgCancelarNFSe(Prefixo4, NameSpaceDad, NumeroNFSe, CNPJ, IM,
                                      CodMunicipio, CodCancelamento: String;
                                      TagI, TagF: AnsiString): AnsiString; OverRide;
   function Gera_DadosMsgGerarNFSe(Prefixo3, Prefixo4, Identificador,
                                   NameSpaceDad, VersaoDados, VersaoXML,
                                   NumeroLote, CNPJ, IM, QtdeNotas: String;
                                   Notas, TagI, TagF: AnsiString): AnsiString; OverRide;
   function Gera_DadosMsgEnviarSincrono(Prefixo3, Prefixo4, Identificador,
                                        NameSpaceDad, VersaoDados, VersaoXML,
                                        NumeroLote, CNPJ, IM, QtdeNotas: String;
                                        Notas, TagI, TagF: AnsiString): AnsiString; OverRide;

   function GeraEnvelopeRecepcionarLoteRPS(URLNS: String; CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString; OverRide;
   function GeraEnvelopeConsultarSituacaoLoteRPS(URLNS: String; CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString; OverRide;
   function GeraEnvelopeConsultarLoteRPS(URLNS: String; CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString; OverRide;
   function GeraEnvelopeConsultarNFSeporRPS(URLNS: String; CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString; OverRide;
   function GeraEnvelopeConsultarNFSe(URLNS: String; CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString; OverRide;
   function GeraEnvelopeCancelarNFSe(URLNS: String; CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString; OverRide;
   function GeraEnvelopeGerarNFSe(URLNS: String; CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString; OverRide;
   function GeraEnvelopeRecepcionarSincrono(URLNS: String; CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString; OverRide;

   function GetSoapAction(Acao: TnfseAcao; NomeCidade: String): String; OverRide;
   function GetRetornoWS(Acao: TnfseAcao; RetornoWS: AnsiString): AnsiString; OverRide;

   function GeraRetornoNFSe(Prefixo: String; RetNFSe: AnsiString; NomeCidade: String): AnsiString; OverRide;
   function GetLinkNFSe(ACodMunicipio, ANumeroNFSe: Integer; ACodVerificacao: String; AAmbiente: Integer): String; OverRide;
  end;

implementation

{ TProvedorGinfes }

constructor TProvedorGinfesV3.Create;
begin
 {----}
end;

function TProvedorGinfesV3.GetConfigCidade(ACodCidade,
  AAmbiente: Integer): TConfigCidade;
var
 ConfigCidade: TConfigCidade;
begin
 ConfigCidade.VersaoSoap := '1.2';
 ConfigCidade.Prefixo2      := 'ns2:';
 ConfigCidade.Prefixo3      := 'ns3:';
 ConfigCidade.Prefixo4      := 'ns4:';
 ConfigCidade.Identificador := 'Id';

 case ACodCidade of
  2304400: begin // Fortaleza/CE
            if AAmbiente = 1
             then ConfigCidade.NameSpaceEnvelope := 'http://producao.issfortaleza.com.br'
             else ConfigCidade.NameSpaceEnvelope := 'http://homologacao.issfortaleza.com.br';
           end;
  else     begin // Demais cidades
            if AAmbiente = 1
             then ConfigCidade.NameSpaceEnvelope := 'http://producao.ginfes.com.br'
             else ConfigCidade.NameSpaceEnvelope := 'http://homologacao.ginfes.com.br';
           end;
 end;

 ConfigCidade.AssinaRPS  := False;
 ConfigCidade.AssinaLote := True;

 Result := ConfigCidade;
end;

function TProvedorGinfesV3.GetConfigSchema(ACodCidade: Integer): TConfigSchema;
var
 ConfigSchema: TConfigSchema;
begin
 ConfigSchema.VersaoCabecalho := '3';
 ConfigSchema.VersaoDados     := '3';
 ConfigSchema.VersaoXML       := '2';
 ConfigSchema.NameSpaceXML    := 'http://www.ginfes.com.br/';
 ConfigSchema.Cabecalho       := 'cabecalho_v03.xsd';
 ConfigSchema.ServicoEnviar   := 'servico_enviar_lote_rps_envio_v03.xsd';
 ConfigSchema.ServicoConSit   := 'servico_consultar_situacao_lote_rps_envio_v03.xsd';
 ConfigSchema.ServicoConLot   := 'servico_consultar_lote_rps_envio_v03.xsd';
 ConfigSchema.ServicoConRps   := 'servico_consultar_nfse_rps_envio_v03.xsd';
 ConfigSchema.ServicoConNfse  := 'servico_consultar_nfse_envio_v03.xsd';

 case ACodCidade of
  3300456: ConfigSchema.ServicoCancelar := 'servico_cancelar_nfse_envio_v03.xsd' // Schema usado por Belford Roxo/RJ
  else ConfigSchema.ServicoCancelar := 'servico_cancelar_nfse_envio_v02.xsd';
 end;

 ConfigSchema.DefTipos        := 'tipos_v03.xsd';

 Result := ConfigSchema;
end;

function TProvedorGinfesV3.GetConfigURL(ACodCidade: Integer): TConfigURL;
var
 ConfigURL: TConfigURL;
begin
 if ACodCidade = 2304400  // Fortaleza/CE
  then begin
   ConfigURL.HomNomeCidade         := '';
   ConfigURL.HomRecepcaoLoteRPS    := 'https://homologacao.issfortaleza.com.br/ServiceGinfesImpl';
   ConfigURL.HomConsultaLoteRPS    := 'https://homologacao.issfortaleza.com.br/ServiceGinfesImpl';
   ConfigURL.HomConsultaNFSeRPS    := 'https://homologacao.issfortaleza.com.br/ServiceGinfesImpl';
   ConfigURL.HomConsultaSitLoteRPS := 'https://homologacao.issfortaleza.com.br/ServiceGinfesImpl';
   ConfigURL.HomConsultaNFSe       := 'https://homologacao.issfortaleza.com.br/ServiceGinfesImpl';
   ConfigURL.HomCancelaNFSe        := 'https://homologacao.issfortaleza.com.br/ServiceGinfesImpl';

   ConfigURL.ProNomeCidade         := '';
   ConfigURL.ProRecepcaoLoteRPS    := 'https://producao.issfortaleza.com.br/ServiceGinfesImpl';
   ConfigURL.ProConsultaLoteRPS    := 'https://producao.issfortaleza.com.br/ServiceGinfesImpl';
   ConfigURL.ProConsultaNFSeRPS    := 'https://producao.issfortaleza.com.br/ServiceGinfesImpl';
   ConfigURL.ProConsultaSitLoteRPS := 'https://producao.issfortaleza.com.br/ServiceGinfesImpl';
   ConfigURL.ProConsultaNFSe       := 'https://producao.issfortaleza.com.br/ServiceGinfesImpl';
   ConfigURL.ProCancelaNFSe        := 'https://producao.issfortaleza.com.br/ServiceGinfesImpl';
  end
  else begin  // Demais Cidades
   ConfigURL.HomNomeCidade         := '';
   ConfigURL.HomRecepcaoLoteRPS    := 'https://homologacao.ginfes.com.br/ServiceGinfesImpl';
   ConfigURL.HomConsultaLoteRPS    := 'https://homologacao.ginfes.com.br/ServiceGinfesImpl';
   ConfigURL.HomConsultaNFSeRPS    := 'https://homologacao.ginfes.com.br/ServiceGinfesImpl';
   ConfigURL.HomConsultaSitLoteRPS := 'https://homologacao.ginfes.com.br/ServiceGinfesImpl';
   ConfigURL.HomConsultaNFSe       := 'https://homologacao.ginfes.com.br/ServiceGinfesImpl';
   ConfigURL.HomCancelaNFSe        := 'https://homologacao.ginfes.com.br/ServiceGinfesImpl';

   ConfigURL.ProNomeCidade         := '';
   ConfigURL.ProRecepcaoLoteRPS    := 'https://producao.ginfes.com.br/ServiceGinfesImpl';
   ConfigURL.ProConsultaLoteRPS    := 'https://producao.ginfes.com.br/ServiceGinfesImpl';
   ConfigURL.ProConsultaNFSeRPS    := 'https://producao.ginfes.com.br/ServiceGinfesImpl';
   ConfigURL.ProConsultaSitLoteRPS := 'https://producao.ginfes.com.br/ServiceGinfesImpl';
   ConfigURL.ProConsultaNFSe       := 'https://producao.ginfes.com.br/ServiceGinfesImpl';
   ConfigURL.ProCancelaNFSe        := 'https://producao.ginfes.com.br/ServiceGinfesImpl';
  end;

 Result := ConfigURL;
end;

function TProvedorGinfesV3.GetURI(URI: String): String;
begin
 // No provedor Ginfes a URI n�o � informada.
 Result := '';
end;

function TProvedorGinfesV3.GetAssinarXML(Acao: TnfseAcao): Boolean;
begin
 case Acao of
   acRecepcionar: Result := True;
   acConsSit:     Result := True;
   acConsLote:    Result := True;
   acConsNFSeRps: Result := True;
   acConsNFSe:    Result := True;
   acCancelar:    Result := True;
   acGerar:       Result := False;
   else           Result := False;
 end;
end;

function TProvedorGinfesV3.GetValidarLote: Boolean;
begin
 Result := True;
end;

function TProvedorGinfesV3.Gera_TagI(Acao: TnfseAcao; Prefixo3, Prefixo4,
  NameSpaceDad, Identificador, URI: String): AnsiString;
begin
 case Acao of
   acRecepcionar: Result := '<' + Prefixo3 + 'EnviarLoteRpsEnvio' + NameSpaceDad;
   acConsSit:     Result := '<' + Prefixo3 + 'ConsultarSituacaoLoteRpsEnvio' + NameSpaceDad;
   acConsLote:    Result := '<' + Prefixo3 + 'ConsultarLoteRpsEnvio' + NameSpaceDad;
   acConsNFSeRps: Result := '<' + Prefixo3 + 'ConsultarNfseRpsEnvio' + NameSpaceDad;
   acConsNFSe:    Result := '<' + Prefixo3 + 'ConsultarNfseEnvio' + NameSpaceDad;
   acCancelar:    Result := '<CancelarNfseEnvio' +
                               ' xmlns="http://www.ginfes.com.br/servico_cancelar_nfse_envio"' +
                               ' xmlns:' + stringReplace(Prefixo4, ':', '', []) + '="http://www.ginfes.com.br/tipos">';
   acGerar:       Result := '';
 end;
end;

function TProvedorGinfesV3.Gera_CabMsg(Prefixo2, VersaoLayOut, VersaoDados,
  NameSpaceCab: String; ACodCidade: Integer): AnsiString;
var
 Tag: String;
begin
 case ACodCidade of
  2304400: Tag := 'cabecalho'; // Fortaleza/CE
  else     Tag := 'cabecalho'; // Demais cidades
 end;

 Result := '<' + Prefixo2 + Tag + ' versao="'  + VersaoLayOut + '"' + NameSpaceCab +
            '<versaoDados>' + VersaoDados + '</versaoDados>'+
           '</' + Prefixo2 + Tag + '>';
end;

function TProvedorGinfesV3.Gera_DadosSenha(CNPJ, Senha: String): AnsiString;
begin
 Result := '';
end;

function TProvedorGinfesV3.Gera_TagF(Acao: TnfseAcao; Prefixo3: String): AnsiString;
begin
 case Acao of
   acRecepcionar: Result := '</' + Prefixo3 + 'EnviarLoteRpsEnvio>';
   acConsSit:     Result := '</' + Prefixo3 + 'ConsultarSituacaoLoteRpsEnvio>';
   acConsLote:    Result := '</' + Prefixo3 + 'ConsultarLoteRpsEnvio>';
   acConsNFSeRps: Result := '</' + Prefixo3 + 'ConsultarNfseRpsEnvio>';
   acConsNFSe:    Result := '</' + Prefixo3 + 'ConsultarNfseEnvio>';
   acCancelar:    Result := '</CancelarNfseEnvio>';
   acGerar:       Result := '';
 end;
end;

function TProvedorGinfesV3.Gera_DadosMsgEnviarLote(Prefixo3, Prefixo4,
  Identificador, NameSpaceDad, VersaoDados, VersaoXML, NumeroLote, CNPJ,
  IM, QtdeNotas: String; Notas, TagI, TagF: AnsiString): AnsiString;
var
 DadosMsg: AnsiString;
begin
 DadosMsg := '<' + Prefixo3 + 'LoteRps'+
               DFeUtil.SeSenao(Identificador <> '', ' ' + Identificador + '="' + NumeroLote + '"', '') + '>' +
              '<' + Prefixo4 + 'NumeroLote>' +
                NumeroLote +
              '</' + Prefixo4 + 'NumeroLote>' +

              DFeUtil.SeSenao(VersaoXML = '1',

                '<' + Prefixo4 + 'CpfCnpj>' +
                '<' + Prefixo4 + 'Cnpj>' +
                  Cnpj +
                '</' + Prefixo4 + 'Cnpj>' +
                '</' + Prefixo4 + 'CpfCnpj>',

                '<' + Prefixo4 + 'Cnpj>' +
                  Cnpj +
                '</' + Prefixo4 + 'Cnpj>') +

              '<' + Prefixo4 + 'InscricaoMunicipal>' +
                IM +
              '</' + Prefixo4 + 'InscricaoMunicipal>' +
              '<' + Prefixo4 + 'QuantidadeRps>' +
                QtdeNotas +
              '</' + Prefixo4 + 'QuantidadeRps>' +
              '<' + Prefixo4 + 'ListaRps>' +
               Notas +
              '</' + Prefixo4 + 'ListaRps>' +
             '</' + Prefixo3 + 'LoteRps>';

  Result := TagI + DadosMsg + TagF;
end;

function TProvedorGinfesV3.Gera_DadosMsgConsSitLote(Prefixo3, Prefixo4,
  NameSpaceDad, VersaoXML, Protocolo, CNPJ, IM: String; TagI,
  TagF: AnsiString): AnsiString;
var
 DadosMsg: AnsiString;
begin
 DadosMsg := '<' + Prefixo3 + 'Prestador>' +

               DFeUtil.SeSenao(VersaoXML = '1',

                 '<' + Prefixo4 + 'CpfCnpj>' +
                 '<' + Prefixo4 + 'Cnpj>' +
                   Cnpj +
                 '</' + Prefixo4 + 'Cnpj>' +
                 '</' + Prefixo4 + 'CpfCnpj>',

                 '<' + Prefixo4 + 'Cnpj>' +
                   Cnpj +
                 '</' + Prefixo4 + 'Cnpj>') +

               '<' + Prefixo4 + 'InscricaoMunicipal>' +
                 IM +
               '</' + Prefixo4 + 'InscricaoMunicipal>' +
              '</' + Prefixo3 + 'Prestador>' +
              '<' + Prefixo3 + 'Protocolo>' +
                Protocolo +
              '</' + Prefixo3 + 'Protocolo>';

 Result := DadosMsg;
end;

function TProvedorGinfesV3.Gera_DadosMsgConsLote(Prefixo3, Prefixo4,
  NameSpaceDad, VersaoXML, Protocolo, CNPJ, IM: String; TagI,
  TagF: AnsiString): AnsiString;
var
 DadosMsg: AnsiString;
begin
 DadosMsg := '<' + Prefixo3 + 'Prestador>' +

               DFeUtil.SeSenao(VersaoXML = '1',

                 '<' + Prefixo4 + 'CpfCnpj>' +
                 '<' + Prefixo4 + 'Cnpj>' +
                   Cnpj +
                 '</' + Prefixo4 + 'Cnpj>' +
                 '</' + Prefixo4 + 'CpfCnpj>',

                 '<' + Prefixo4 + 'Cnpj>' +
                   Cnpj +
                 '</' + Prefixo4 + 'Cnpj>') +

               '<' + Prefixo4 + 'InscricaoMunicipal>' +
                 IM +
               '</' + Prefixo4 + 'InscricaoMunicipal>' +
              '</' + Prefixo3 + 'Prestador>' +
              '<' + Prefixo3 + 'Protocolo>' +
                Protocolo +
              '</' + Prefixo3 + 'Protocolo>';

 Result := DadosMsg;
end;

function TProvedorGinfesV3.Gera_DadosMsgConsNFSe(Prefixo3, Prefixo4,
  NameSpaceDad, VersaoXML, CNPJ, IM: String; DataInicial, DataFinal: TDateTime; TagI,
  TagF: AnsiString; NumeroNFSe: string = ''): AnsiString;
var
 DadosMsg: AnsiString;
begin
 DadosMsg := '<' + Prefixo3 + 'Prestador>' +

               DFeUtil.SeSenao(VersaoXML = '1',

                 '<' + Prefixo4 + 'CpfCnpj>' +
                 '<' + Prefixo4 + 'Cnpj>' +
                  Cnpj +
                 '</' + Prefixo4 + 'Cnpj>' +
                 '</' + Prefixo4 + 'CpfCnpj>',

                 '<' + Prefixo4 + 'Cnpj>' +
                  Cnpj +
                 '</' + Prefixo4 + 'Cnpj>') +

               '<' + Prefixo4 + 'InscricaoMunicipal>' +
                IM +
               '</' + Prefixo4 + 'InscricaoMunicipal>' +
              '</' + Prefixo3 + 'Prestador>';

 if NumeroNFSe <> ''
  then DadosMsg := DadosMsg + '<' + Prefixo3 + 'NumeroNfse>' +
                               NumeroNFSe +
                              '</' + Prefixo3 + 'NumeroNfse>';

 if (DataInicial>0) and (DataFinal>0)
  then DadosMsg := DadosMsg + '<' + Prefixo3 + 'PeriodoEmissao>' +
                               '<' + Prefixo3 + 'DataInicial>' +
                                 FormatDateTime('yyyy-mm-dd', DataInicial) +
                               '</' + Prefixo3 + 'DataInicial>' +
                               '<' + Prefixo3 + 'DataFinal>' +
                                 FormatDateTime('yyyy-mm-dd', DataFinal) +
                               '</' + Prefixo3 + 'DataFinal>' +
                              '</' + Prefixo3 + 'PeriodoEmissao>';

 Result := DadosMsg;
end;

function TProvedorGinfesV3.Gera_DadosMsgConsNFSeRPS(Prefixo3, Prefixo4,
  NameSpaceDad, VersaoXML, NumeroRps, SerieRps, TipoRps, CNPJ, IM: String; TagI,
  TagF: AnsiString): AnsiString;
var
 DadosMsg: AnsiString;
begin
 DadosMsg := '<' + Prefixo3 + 'IdentificacaoRps>' +
              '<' + Prefixo4 + 'Numero>' +
                NumeroRps +
              '</' + Prefixo4 + 'Numero>' +
              '<' + Prefixo4 + 'Serie>' +
                SerieRps +
              '</' + Prefixo4 + 'Serie>' +
              '<' + Prefixo4 + 'Tipo>' +
                TipoRps +
              '</' + Prefixo4 + 'Tipo>' +
             '</' + Prefixo3 + 'IdentificacaoRps>' +
             '<' + Prefixo3 + 'Prestador>' +

              DFeUtil.SeSenao(VersaoXML = '1',

                '<' + Prefixo4 + 'CpfCnpj>' +
                '<' + Prefixo4 + 'Cnpj>' +
                  Cnpj +
                '</' + Prefixo4 + 'Cnpj>' +
                '</' + Prefixo4 + 'CpfCnpj>',

                '<' + Prefixo4 + 'Cnpj>' +
                  Cnpj +
                '</' + Prefixo4 + 'Cnpj>') +

              '<' + Prefixo4 + 'InscricaoMunicipal>' +
                IM +
              '</' + Prefixo4 + 'InscricaoMunicipal>' +
             '</' + Prefixo3 + 'Prestador>';

 Result := DadosMsg;
end;

function TProvedorGinfesV3.Gera_DadosMsgCancelarNFSe(Prefixo4, NameSpaceDad, NumeroNFSe,
  CNPJ, IM, CodMunicipio, CodCancelamento: String; TagI,
  TagF: AnsiString): AnsiString;
var
 DadosMsg: AnsiString;
begin
 DadosMsg := '<Prestador>' +
              '<' + Prefixo4 + 'Cnpj>' +
                Cnpj +
              '</' + Prefixo4 + 'Cnpj>' +
              '<' + Prefixo4 + 'InscricaoMunicipal>' +
                IM +
              '</' + Prefixo4 + 'InscricaoMunicipal>' +
              '</Prestador>' +
              '<NumeroNfse>' +
                NumeroNFSe +
              '</NumeroNfse>';

 Result := DadosMsg;
end;

function TProvedorGinfesV3.Gera_DadosMsgGerarNFSe(Prefixo3, Prefixo4,
  Identificador, NameSpaceDad, VersaoDados, VersaoXML, NumeroLote, CNPJ,
  IM, QtdeNotas: String; Notas, TagI, TagF: AnsiString): AnsiString;
begin
 Result := '';
end;

function TProvedorGinfesV3.GeraEnvelopeRecepcionarLoteRPS(URLNS: String;
  CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString;
var
 TagCab, TagDados: String;
begin
 if Pos('issfortaleza', URLNS) > 0
  then begin
   TagCab := 'Cabecalho';
   TagCab := 'arg0';
  end
  else begin
   TagDados := 'EnviarLoteRpsEnvio';
   TagDados := 'arg1';
  end;

 result := '<?xml version="1.0" encoding="UTF-8"?>' +
           '<s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/">' +
            '<s:Body>' +
             '<ns1:RecepcionarLoteRpsV3 xmlns:ns1="' + URLNS + '">' +
              '<' + TagCab + '>' + CabMsg + '</' + TagCab + '>' +
              '<' + TagDados + '>' + DadosMsg + '</' + TagDados + '>' +
             '</ns1:RecepcionarLoteRpsV3>' +
            '</s:Body>' +
           '</s:Envelope>';
end;

function TProvedorGinfesV3.GeraEnvelopeConsultarSituacaoLoteRPS(
  URLNS: String; CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString;
begin
 result := '<?xml version="1.0" encoding="UTF-8"?>' +
           '<s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/">' +
            '<s:Body>' +
             '<ns1:ConsultarSituacaoLoteRpsV3 xmlns:ns1="' + URLNS + '">' +
              '<arg0>' + CabMsg + '</arg0>' +
              '<arg1>' + DadosMsg + '</arg1>' +
             '</ns1:ConsultarSituacaoLoteRpsV3>' +
            '</s:Body>' +
           '</s:Envelope>';
end;

function TProvedorGinfesV3.GeraEnvelopeConsultarLoteRPS(URLNS: String;
  CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString;
begin
 result := '<?xml version="1.0" encoding="UTF-8"?>' +
           '<s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/">' +
            '<s:Body>' +
             '<ns1:ConsultarLoteRpsV3 xmlns:ns1="' + URLNS + '">' +
              '<arg0>' + CabMsg + '</arg0>' +
              '<arg1>' + DadosMsg + '</arg1>' +
             '</ns1:ConsultarLoteRpsV3>' +
            '</s:Body>' +
           '</s:Envelope>';
end;

function TProvedorGinfesV3.GeraEnvelopeConsultarNFSeporRPS(URLNS: String;
  CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString;
begin
 result := '<?xml version="1.0" encoding="UTF-8"?>' +
           '<s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/">' +
            '<s:Body>' +
             '<ns1:ConsultarNfsePorRpsV3 xmlns:ns1="' + URLNS + '">' +
              '<arg0>' + CabMsg + '</arg0>' +
              '<arg1>' + DadosMsg + '</arg1>' +
             '</ns1:ConsultarNfsePorRpsV3>' +
            '</s:Body>' +
           '</s:Envelope>';
end;

function TProvedorGinfesV3.GeraEnvelopeConsultarNFSe(URLNS: String; CabMsg,
  DadosMsg, DadosSenha: AnsiString): AnsiString;
begin
 result := '<?xml version="1.0" encoding="UTF-8"?>' +
           '<s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/">' +
            '<s:Body>' +
             '<ns1:ConsultarNfseV3 xmlns:ns1="' + URLNS + '">' +
              '<arg0>' + CabMsg + '</arg0>' +
              '<arg1>' + DadosMsg + '</arg1>' +
             '</ns1:ConsultarNfseV3>' +
            '</s:Body>' +
           '</s:Envelope>';
end;

function TProvedorGinfesV3.GeraEnvelopeCancelarNFSe(URLNS: String; CabMsg,
  DadosMsg, DadosSenha: AnsiString): AnsiString;
begin
 result := '<?xml version="1.0" encoding="UTF-8"?>' +
           '<s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/">' +
            '<s:Body>' +
             '<ns1:CancelarNfse xmlns:ns1="' + URLNS + '">' +
              '<arg0>' +
               '&lt;?xml version="1.0" encoding="UTF-8"?&gt;' +
               StringReplace(StringReplace(DadosMsg, '<', '&lt;', [rfReplaceAll]), '>', '&gt;', [rfReplaceAll]) +
              '</arg0>' +
             '</ns1:CancelarNfse>' +
            '</s:Body>' +
           '</s:Envelope>';
end;

function TProvedorGinfesV3.GeraEnvelopeGerarNFSe(URLNS: String; CabMsg,
  DadosMsg, DadosSenha: AnsiString): AnsiString;
begin
 Result := '';
 raise Exception.Create( 'Op��o n�o implementada para este provedor.' );
end;

function TProvedorGinfesV3.GetSoapAction(Acao: TnfseAcao; NomeCidade: String): String;
begin
 case Acao of
   acRecepcionar: Result := '';
   acConsSit:     Result := '';
   acConsLote:    Result := '';
   acConsNFSeRps: Result := '';
   acConsNFSe:    Result := '';
   acCancelar:    Result := '';
   acGerar:       Result := '';
 end;
end;

function TProvedorGinfesV3.GetRetornoWS(Acao: TnfseAcao; RetornoWS: AnsiString): AnsiString;
begin
 case Acao of
   acRecepcionar: Result := SeparaDados( RetornoWS, 'return' );
   acConsSit:     Result := SeparaDados( RetornoWS, 'return' );
   acConsLote:    Result := SeparaDados( RetornoWS, 'return' );
   acConsNFSeRps: Result := SeparaDados( RetornoWS, 'return' );
   acConsNFSe:    Result := SeparaDados( RetornoWS, 'return' );
   acCancelar:    Result := SeparaDados( RetornoWS, 'return' );
   acGerar:       Result := '';
 end;
end;

function TProvedorGinfesV3.GeraRetornoNFSe(Prefixo: String;
  RetNFSe: AnsiString; NomeCidade: String): AnsiString;
begin
 Result := '<?xml version="1.0" encoding="UTF-8"?>' +
           '<CompNfse xmlns:ns4="http://www.ginfes.com.br/tipos_v03.xsd">' +
            RetNFSe +
           '</CompNfse>';
end;

function TProvedorGinfesV3.GetLinkNFSe(ACodMunicipio, ANumeroNFSe: Integer;
  ACodVerificacao: String; AAmbiente: Integer): String;
begin
 if AAmbiente = 1
  then begin
   case ACodMunicipio of
    3143906: Result := 'http://muriae.ginfes.com.br/birt/frameset?__report=nfs_novo.rptdesign&cdVerificacao=' +
                       ACodVerificacao + '&numNota=' + IntToStr(ANumeroNFSe);
    3503208: Result := 'http://araraquara.ginfes.com.br/birt/frameset?_report=nfs_novo.rptdesign&cdVerificacao=' +
                       ACodVerificacao + '&numNota=' + IntToStr(ANumeroNFSe);
    3518800: Result := 'http://guarulhos.ginfes.com.br/birt/frameset?_report=nfs_ver4.rptdesign&cdVerificacao=' +
                       ACodVerificacao + '&numNota=' + IntToStr(ANumeroNFSe);
    3543402: Result := 'http://ribeiraopreto.ginfes.com.br/birt/frameset?_report=nfs_ribeirao_preto.rptdesign&cdVerificacao=' +
                       ACodVerificacao + '&numNota=' + IntToStr(ANumeroNFSe);
    3547809: Result := 'http://santoandre.ginfes.com.br/birt/frameset?_report=nfs_novo.rptdesign&cdVerificacao=' +
                       ACodVerificacao + '&numNota=' + IntToStr(ANumeroNFSe);
   else Result := '';
   end;
  end
  else begin
   case ACodMunicipio of
    2304400: Result := 'http://nfse.issfortaleza.com.br/report/consultarNota?__report=nfs_ver4&cdVerificacao=' +
                       ACodVerificacao + '&numNota=' + IntToStr(ANumeroNFSe) + '&cnpjPrestador=null';
    3143906: Result := 'http://muriae.ginfesh.com.br/birt/frameset?__report=nfs_novo.rptdesign&cdVerificacao=' +
                       ACodVerificacao + '&numNota=' + IntToStr(ANumeroNFSe);
    3503208: Result := 'http://araraquara.ginfesh.com.br/birt/frameset?_report=nfs_novo.rptdesign&cdVerificacao=' +
                       ACodVerificacao + '&numNota=' + IntToStr(ANumeroNFSe);
    3518800: Result := 'http://guarulhos.ginfesh.com.br/birt/frameset?_report=nfs_ver4.rptdesign&cdVerificacao=' +
                       ACodVerificacao + '&numNota=' + IntToStr(ANumeroNFSe);
    3543402: Result := 'http://ribeiraopreto.ginfesh.com.br/birt/frameset?_report=nfs_ribeirao_preto.rptdesign&cdVerificacao=' +
                       ACodVerificacao + '&numNota=' + IntToStr(ANumeroNFSe);
    3547809: Result := 'http://santoandre.ginfesh.com.br/birt/frameset?_report=nfs_novo.rptdesign&cdVerificacao=' +
                       ACodVerificacao + '&numNota=' + IntToStr(ANumeroNFSe);
   else Result := '';
   end;
  end;
end;

function TProvedorGinfesV3.Gera_DadosMsgEnviarSincrono(Prefixo3, Prefixo4,
  Identificador, NameSpaceDad, VersaoDados, VersaoXML, NumeroLote, CNPJ,
  IM, QtdeNotas: String; Notas, TagI, TagF: AnsiString): AnsiString;
begin
 Result := '';
end;

function TProvedorGinfesV3.GeraEnvelopeRecepcionarSincrono(URLNS: String;
  CabMsg, DadosMsg, DadosSenha: AnsiString): AnsiString;
begin
 Result := '';
end;

end.
