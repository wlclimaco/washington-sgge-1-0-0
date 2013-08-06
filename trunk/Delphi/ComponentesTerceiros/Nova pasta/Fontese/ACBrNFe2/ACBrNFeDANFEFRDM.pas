{******************************************************************************}
{ Projeto: Componente ACBrNFe                                                  }
{  Biblioteca multiplataforma de componentes Delphi para emiss�o de Nota Fiscal}
{ eletr�nica - NFe - http://www.nfe.fazenda.gov.br                          }
{                                                                              }
{ Direitos Autorais Reservados (c) 2008 Wemerson Souto                         }
{                                       Daniel Simoes de Almeida               }
{                                       Andr� Ferreira de Moraes               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do Projeto ACBr     }
{ Componentes localizado em http://www.sourceforge.net/projects/acbr           }
{                                                                              }
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
|* 11/08/2010: Itamar Luiz Bermond
|*  - Inicio do desenvolvimento
|* 24/08/2010: R�gys Silveira
|*  - Acerto nas diretivas de compila��o para Delphi 2009 e superior
|*  - Acertos gerais no DANFE
|        . Layout
|        . Exibi��o da logomarca
|        . Tamanho das colunas para conter valores grandes
|        . marca d'agua para ambiente de homologa��o
|        . Adicionado o complemento a descri��o da mercadoria
|        . Adicionado a origem no CST
|        . Acerto para mostrar o CST corretamente quando for Simples Nacional
|*  - Padroniza��o da logomarca para utilizar o caminho como nos outros DANFEs
|*  - Acerto no CST para o Simples Nacional
|*  - Acertos no DANFE para o Simples Nacional
|* 25/08/2010: R�gys Silveira
|*  - Configura��o do preview do DANFE.
|* 26/08/2010: R�gys Silveira, Itamar Bermond
|*  - Desmarcada a propriedade StoreInDFM do FastReport para n�o gravar
|*    o relat�rio no DFM e evitar o erro de compila��o em vers�o menores
|*    do delphi, favor utilizar o arquivo externo.
|* 26/02/2013: Jo�o Henrique de Souza
|*  - Foi realizado in�meras modifica��es para Normalizar o Danfe com o Manual
|*    e ter uma vers�o que fosse poss�vel imprimir com o FR que vem com o Delphi
******************************************************************************}
{$I ACBr.inc}

unit ACBrNFeDANFEFRDM;

interface

uses
  pcnEnvEventoNFe,
  SysUtils, Classes, ACBrNFeDANFEClass, pcnNFe, frxClass, frxExportPDF, DB,
  DBClient, frxDBSet, pcnConversao, frxBarcode;

type
  TdmACBrNFeFR = class(TDataModule)
    frxPDFExport: TfrxPDFExport;
    cdsIdentificacao: TClientDataSet;
    cdsEmitente: TClientDataSet;
    cdsDestinatario: TClientDataSet;
    cdsDadosProdutos: TClientDataSet;
    cdsParametros: TClientDataSet;
    cdsInformacoesAdicionais: TClientDataSet;
    cdsDuplicatas: TClientDataSet;
    cdsCalculoImposto: TClientDataSet;
    cdsTransportador: TClientDataSet;
    cdsVeiculo: TClientDataSet;
    cdsVolumes: TClientDataSet;
    cdsISSQN: TClientDataSet;
    cdsFatura: TClientDataSet;
    cdsLocalRetirada: TClientDataSet;
    cdsLocalEntrega: TClientDataSet;
    frxIdentificacao: TfrxDBDataset;
    frxEmitente: TfrxDBDataset;
    frxDestinatario: TfrxDBDataset;
    frxDadosProdutos: TfrxDBDataset;
    frxParametros: TfrxDBDataset;
    frxDuplicatas: TfrxDBDataset;
    frxCalculoImposto: TfrxDBDataset;
    frxTransportador: TfrxDBDataset;
    frxVeiculo: TfrxDBDataset;
    frxVolumes: TfrxDBDataset;
    frxISSQN: TfrxDBDataset;
    frxFatura: TfrxDBDataset;
    frxLocalRetirada: TfrxDBDataset;
    frxLocalEntrega: TfrxDBDataset;
    frxInformacoesAdicionais: TfrxDBDataset;
    frxBarCodeObject: TfrxBarCodeObject;
    frxReport: TfrxReport;
    cdsEventos: TClientDataSet;
    frxEventos: TfrxDBDataset;
    cdsIdentificacaoId: TStringField;
    cdsIdentificacaoChave: TStringField;
    cdsIdentificacaocUF: TStringField;
    cdsIdentificacaocNF: TStringField;
    cdsIdentificacaoNatOp: TStringField;
    cdsIdentificacaoIndPag: TStringField;
    cdsIdentificacaoMod_: TStringField;
    cdsIdentificacaoSerie: TStringField;
    cdsIdentificacaoNNF: TStringField;
    cdsIdentificacaoDEmi: TStringField;
    cdsIdentificacaoDSaiEnt: TStringField;
    cdsIdentificacaoTpNF: TStringField;
    cdsIdentificacaoCMunFG: TStringField;
    cdsIdentificacaoTpImp: TStringField;
    cdsIdentificacaoTpEmis: TStringField;
    cdsIdentificacaoCDV: TStringField;
    cdsIdentificacaoTpAmb: TStringField;
    cdsIdentificacaoFinNFe: TStringField;
    cdsIdentificacaoProcEmi: TStringField;
    cdsIdentificacaoVerProc: TStringField;
    cdsIdentificacaoHoraSaida: TStringField;
    cdsEmitenteCNPJ: TStringField;
    cdsEmitenteXNome: TStringField;
    cdsEmitenteXFant: TStringField;
    cdsEmitenteXLgr: TStringField;
    cdsEmitenteNro: TStringField;
    cdsEmitenteXCpl: TStringField;
    cdsEmitenteXBairro: TStringField;
    cdsEmitenteCMun: TStringField;
    cdsEmitenteXMun: TStringField;
    cdsEmitenteUF: TStringField;
    cdsEmitenteCEP: TStringField;
    cdsEmitenteCPais: TStringField;
    cdsEmitenteXPais: TStringField;
    cdsEmitenteFone: TStringField;
    cdsEmitenteIE: TStringField;
    cdsEmitenteIM: TStringField;
    cdsEmitenteIEST: TStringField;
    cdsEmitenteCRT: TStringField;
    cdsDestinatarioCNPJCPF: TStringField;
    cdsDestinatarioXNome: TStringField;
    cdsDestinatarioXLgr: TStringField;
    cdsDestinatarioNro: TStringField;
    cdsDestinatarioXCpl: TStringField;
    cdsDestinatarioXBairro: TStringField;
    cdsDestinatarioCMun: TStringField;
    cdsDestinatarioXMun: TStringField;
    cdsDestinatarioUF: TStringField;
    cdsDestinatarioCEP: TStringField;
    cdsDestinatarioCPais: TStringField;
    cdsDestinatarioXPais: TStringField;
    cdsDestinatarioFone: TStringField;
    cdsDestinatarioIE: TStringField;
    cdsDadosProdutosCProd: TStringField;
    cdsDadosProdutoscEAN: TStringField;
    cdsDadosProdutosXProd: TStringField;
    cdsDadosProdutosinfAdProd: TStringField;
    cdsDadosProdutosNCM: TStringField;
    cdsDadosProdutosEXTIPI: TStringField;
    cdsDadosProdutosgenero: TStringField;
    cdsDadosProdutosCFOP: TStringField;
    cdsDadosProdutosUCom: TStringField;
    cdsDadosProdutosQCom: TFloatField;
    cdsDadosProdutosVUnCom: TFloatField;
    cdsDadosProdutosVProd: TFloatField;
    cdsDadosProdutoscEANTrib: TStringField;
    cdsDadosProdutosUTrib: TStringField;
    cdsDadosProdutosQTrib: TFloatField;
    cdsDadosProdutosVUnTrib: TFloatField;
    cdsDadosProdutosvFrete: TFloatField;
    cdsDadosProdutosvSeg: TFloatField;
    cdsDadosProdutosvDesc: TStringField;
    cdsDadosProdutosORIGEM: TStringField;
    cdsDadosProdutosCST: TStringField;
    cdsDadosProdutosVBC: TFloatField;
    cdsDadosProdutosPICMS: TFloatField;
    cdsDadosProdutosVICMS: TFloatField;
    cdsDadosProdutosVIPI: TFloatField;
    cdsDadosProdutosPIPI: TFloatField;
    cdsParametrosResumoCanhoto: TStringField;
    cdsParametrosMensagem0: TStringField;
    cdsParametrosImagem: TStringField;
    cdsParametrosSistema: TStringField;
    cdsParametrosUsuario: TStringField;
    cdsParametrosFax: TStringField;
    cdsParametrosSite: TStringField;
    cdsParametrosEmail: TStringField;
    cdsParametrosDesconto: TStringField;
    cdsParametrosChaveAcesso_Descricao: TStringField;
    cdsParametrosContingencia_ID: TStringField;
    cdsParametrosContingencia_Descricao: TStringField;
    cdsParametrosContingencia_Valor: TStringField;
    cdsParametrosLinhasPorPagina: TIntegerField;
    cdsParametrosLogoExpandido: TStringField;
    cdsDuplicatasNDup: TStringField;
    cdsDuplicatasDVenc: TStringField;
    cdsDuplicatasVDup: TFloatField;
    cdsCalculoImpostoVBC: TFloatField;
    cdsCalculoImpostoVICMS: TFloatField;
    cdsCalculoImpostoVBCST: TFloatField;
    cdsCalculoImpostoVST: TFloatField;
    cdsCalculoImpostoVProd: TFloatField;
    cdsCalculoImpostoVFrete: TFloatField;
    cdsCalculoImpostoVSeg: TFloatField;
    cdsCalculoImpostoVDesc: TFloatField;
    cdsCalculoImpostoVII: TFloatField;
    cdsCalculoImpostoVIPI: TFloatField;
    cdsCalculoImpostoVPIS: TFloatField;
    cdsCalculoImpostoVCOFINS: TFloatField;
    cdsCalculoImpostoVOutro: TFloatField;
    cdsCalculoImpostoVNF: TFloatField;
    cdsTransportadorModFrete: TStringField;
    cdsTransportadorCNPJCPF: TStringField;
    cdsTransportadorXNome: TStringField;
    cdsTransportadorIE: TStringField;
    cdsTransportadorXEnder: TStringField;
    cdsTransportadorXMun: TStringField;
    cdsTransportadorUF: TStringField;
    cdsVeiculoPLACA: TStringField;
    cdsVeiculoUF: TStringField;
    cdsVeiculoRNTC: TStringField;
    cdsVolumesQVol: TFloatField;
    cdsVolumesEsp: TStringField;
    cdsVolumesMarca: TStringField;
    cdsVolumesNVol: TStringField;
    cdsVolumesPesoL: TFloatField;
    cdsVolumesPesoB: TFloatField;
    cdsISSQNvSERV: TFloatField;
    cdsISSQNvBC: TFloatField;
    cdsISSQNvISS: TFloatField;
    cdsFaturaPagamento: TStringField;
    cdsFaturanFat: TStringField;
    cdsFaturavOrig: TFloatField;
    cdsFaturavDesc: TFloatField;
    cdsFaturavLiq: TFloatField;
    cdsLocalRetiradaCNPJ: TStringField;
    cdsLocalRetiradaXLgr: TStringField;
    cdsLocalRetiradaNro: TStringField;
    cdsLocalRetiradaXCpl: TStringField;
    cdsLocalRetiradaXBairro: TStringField;
    cdsLocalRetiradaCMun: TStringField;
    cdsLocalRetiradaXMun: TStringField;
    cdsLocalRetiradaUF: TStringField;
    cdsLocalEntregaCNPJ: TStringField;
    cdsLocalEntregaXLgr: TStringField;
    cdsLocalEntregaNro: TStringField;
    cdsLocalEntregaXCpl: TStringField;
    cdsLocalEntregaXBairro: TStringField;
    cdsLocalEntregaCMun: TStringField;
    cdsLocalEntregaXMun: TStringField;
    cdsLocalEntregaUF: TStringField;
    cdsInformacoesAdicionaisOBS: TStringField;
    cdsInformacoesAdicionaisLinhasOBS: TIntegerField;
    cdsParametrosDESCR_CST: TStringField;
    cdsEmitenteDESCR_CST: TStringField;
    cdsEmitenteDADOS_ENDERECO: TStringField;
    cdsDadosProdutosDescricaoProduto: TStringField;
    cdsDadosProdutosChaveNFe: TStringField;
    cdsDuplicatasChaveNFe: TStringField;
    cdsParametrosConsultaAutenticidade: TStringField;
    cdsParametrosTotalLiquido: TStringField;
    cdsParametrosCasas_qCom: TIntegerField;
    cdsParametrosCasas_vUnCom: TIntegerField;
    cdsParametrosMask_qCom: TStringField;
    cdsParametrosMask_vUnCom: TStringField;
    cdsParametrosLogoCarregado: TBlobField;
    constructor Create(AOwner: TComponent); override;
  private
    FDANFEClassOwner: TACBrNFeDANFEClass;
    FNFe: TNFe;
    FEvento: TEventoNFe;
    procedure CarregaIdentificacao;
    procedure CarregaEmitente;
    procedure CarregaDestinatario;
    procedure CarregaDadosProdutos;
    procedure CarregaParametros;
    procedure CarregaCalculoImposto;
    procedure CarregaTransportador;
    procedure CarregaVeiculo;
    procedure CarregaVolumes;
    procedure CarregaDuplicatas;
    procedure CarregaISSQN;
    procedure CarregaLocalRetirada;
    procedure CarregaLocalEntrega;
    procedure CarregaFatura;
    procedure CarregaInformacoesAdicionais;
  public
    property NFe: TNFe read FNFe write FNFe;
    property Evento: TEventoNFe read FEvento write FEvento;
    property DANFEClassOwner: TACBrNFeDANFEClass read FDANFEClassOwner;
    procedure CarregaDadosNFe;
    procedure CarregaDadosEventos;
  end;

var
  dmACBrNFeFR: TdmACBrNFeFR;

implementation

uses ACBrNFe, ACBrNFeUtil, ACBrDFeUtil, StrUtils, Math;

{$R *.dfm}

type
  ArrOfStr = Array of String;
  TSplitResult = array of string;

{ TdmACBrNFeFR }

function SubstrCount(const ASubString, AString: string): Integer;
var
  i: integer;
begin
  Result := -1;
  i := 0;
  repeat
    Inc(Result);
    i := PosEx(ASubString, AString, i + 1);
  until i = 0;
end;

function Split(const ADelimiter, AString: string): TSplitResult;
var
  Step: ^String;
  Chr: PChar;
  iPos, iLast, iDelLen, iLen, x: integer;
label
  EndLoop;
begin
  SetLength(Result, SubstrCount(ADelimiter, AString) + 1);
  if High(Result) = 0 then
    Result[0] := AString
  else
  begin
    iDelLen := PCardinal(Cardinal(ADelimiter) - SizeOf(Cardinal))^;
    iLen := PCardinal(Cardinal(AString) - SizeOf(Cardinal))^;
    Step := @Result[0];
    iLast := 0;
    iPos := 0;
    repeat
      if iPos + iDelLen > iLen then
      begin
        if iLast <> iPos then
          iPos := iLen;
      end else
        for x := 1 to iDelLen do
          if AString[iPos + x] <> ADelimiter[x] then
            goto EndLoop;

      if iPos - iLast > 0 then
      begin
        SetLength(Step^, iPos - iLast);
        Chr := PChar(Step^);
        for x := 1 to PCardinal(Cardinal(Step^) - SizeOf(Cardinal))^ do
        begin
          Chr^ := AString[iLast + x];
          Inc(Chr);
        end;
      end else
        Step^ := '';

      Cardinal(Step) := Cardinal(Step) + SizeOf(Cardinal);
      iLast := iPos + iDelLen;

      EndLoop:
        Inc(iPos);
    until iLast >= iLen;
  end;
end;

function Explode(sPart, sInput: String): ArrOfStr;
begin
  while Pos(sPart, sInput) <> 0 do
    begin
      SetLength(Result, Length(Result) + 1);
      Result[Length(Result) - 1] := Copy(sInput, 0, Pos(sPart, sInput) - 1);
      Delete(sInput, 1, Pos(sPart, sInput));
    end;

  SetLength(Result, Length(Result) + 1);
  Result[Length(Result) - 1] := sInput;
end;

function CollateBr(Str: String): String;
var
  Resultado,Temp: string;
  vChar: Char;
  Tamanho, i: integer;
begin
  Result := '';
  Tamanho := Length(str);
  i := 1;
  while i <= Tamanho do
  begin
    Temp := Copy(str,i,1);
    vChar := Temp[1];
    case vChar of
      '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�': Resultado := 'A';
      '�', '�', '�', '�', '�', '�', '�', '�': Resultado := 'E';
      '�', '�', '�', '�', '�', '�', '�', '�': Resultado := 'I';
      '�', '�', '�', '�', '�', '�', '�', '�', '�', '�': Resultado := 'O';
      '�', '�', '�', '�', '�', '�', '�', '�': Resultado := 'U';
      '�', '�': Resultado := 'C';
      '�', '�': Resultado := 'N';
      '�', '�', '�', 'Y': Resultado := 'Y';
    else
      if vChar > #127 then Resultado := #32
      {$IFDEF DELPHI12_UP}
      else if CharInset(vChar, ['a'..'z','A'..'Z','0'..'9','-',' ']) then
      {$ELSE}
      else if vChar in ['a'..'z','A'..'Z','0'..'9','-',' '] then
      {$ENDIF}
        Resultado := UpperCase(vCHAR);
    end;
    Result := Result + Resultado;
    i := i + 1;
  end;
end;

procedure TdmACBrNFeFR.CarregaCalculoImposto;
begin
  with cdsCalculoImposto do
  begin
    Close;
    CreateDataSet;
    Append;

    with FNFe.Total.ICMSTot do
    begin
      FieldByName('VBC').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VBC), 0);
      FieldByName('VICMS').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VICMS), 0);
      FieldByName('VBCST').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VBCST), 0);
      FieldByName('VST').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VST), 0);
      FieldByName('VProd').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VProd), 0);
      FieldByName('VFrete').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VFrete), 0);
      FieldByName('VSeg').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VSeg), 0);
      FieldByName('VDesc').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VDesc), 0);
      FieldByName('VII').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VII), 0);
      FieldByName('VIPI').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VIPI), 0);
      FieldByName('VPIS').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VPIS), 0);
      FieldByName('VCOFINS').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VCOFINS), 0);
      FieldByName('VOutro').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VOutro), 0);
      FieldByName('VNF').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VNF), 0);
    end;

    Post;
  end;
end;

procedure TdmACBrNFeFR.CarregaDadosNFe;
begin
  CarregaIdentificacao;
  CarregaEmitente;
  CarregaDestinatario;
  CarregaDadosProdutos;
  CarregaParametros;
  CarregaCalculoImposto;
  CarregaTransportador;
  CarregaVeiculo;
  CarregaVolumes;
  CarregaDuplicatas;
  CarregaISSQN;
  CarregaLocalRetirada;
  CarregaLocalEntrega;
  CarregaFatura;
  CarregaInformacoesAdicionais;
end;

procedure TdmACBrNFeFR.CarregaDadosProdutos;
var
  i: Integer;
  vTemp2: TStringList;
  IndexCampo2: Integer;
  Campos2: TSplitResult;
  BufferXInfProd: String;
  TmpStr: String;
  j: Integer;
  wInfAdProd: String;
begin
  if not cdsParametros.Active then
    CarregaParametros;

  { dados dos produtos }
  with cdsDadosProdutos do
  begin
    Close;
    CreateDataSet;

    for i := 0 to NFe.Det.Count - 1 do
    begin
      Append;

      with FNFe.Det.Items[i].Prod do
      begin
        FieldByName('ChaveNFe').AsString := FNFe.infNFe.ID;
        FieldByName('cProd').AsString := cProd;
        FieldByName('cEAN').AsString := cEAN;
        FieldByName('XProd').AsString := StringReplace(xProd, ';', #13, [rfReplaceAll]);

        wInfAdProd := FNFe.Det.Items[i].infAdProd;
        vTemp2 := TStringList.Create;
        try
          if FDANFEClassOwner.ImprimirDetalhamentoEspecifico then
          begin
            { detalhamento especifico de ve�culos }
            if Trim(veicProd.chassi) <> '' then
            begin
              vTemp2.Add(' CHASSI: ' + veicProd.chassi);
              vTemp2.Add(' COMBUST�VEL: ' + veicProd.tpComb);
              vTemp2.Add(' COR: ' + veicProd.xCor);
              vTemp2.Add(' FAB./MOD.: ' + IntToStr(veicProd.anoFab) + '/' + IntToStr(veicProd.anoMod));
//              vTemp2.Add(' RENAVAM: ' + veicProd.RENAVAM); // Essa propriedade foi comentada na class, por isso comentei aqui tambem.
              vTemp2.Add(' N� DO MOTOR: ' + veicProd.nMotor);

              if Trim(wInfAdProd) <> '' then
                 wInfAdProd := wInfAdProd + ';'; //insere quebra de linha antes do detalhamento

              wInfAdProd := wInfAdProd + vTemp2.Text;
              vTemp2.Clear;
            end;

            { detalhamento espec�fico de medicamentos }
            if med.Count > 0 then
            begin
              for j := 0 to med.Count - 1 do
              begin
                with med.Items[j] do
                begin
                  vTemp2.Add('-LOTE: ' + nLote);
                  vTemp2.Add(' QTDADE: ' + DFeUtil.FormatFloat(qLote));
                  vTemp2.Add(' FABR.: ' + DFeUtil.FormatDate(DateToStr(dFab)));
                  vTemp2.Add(' VAL.: ' + DFeUtil.FormatDate(DateToStr(dVal)));
                  vTemp2.Add(DFeUtil.SeSenao(vPMC > 0, ' PMC: ' + DFeUtil.FormatFloat(vPMC), ''));
                end;
              end;

              if (Trim(wInfAdProd) <> '') then
                wInfAdProd := wInfAdProd + ';'; //insere quebra de linha antes do detalhamento

              wInfAdProd := wInfAdProd + vTemp2.Text;
              vTemp2.Clear;
            end;
          end;

          if Trim(winfAdProd) <> '' then
          begin
            Campos2 := Split(';', winfAdProd);
            for IndexCampo2 := 0 to Length(Campos2) - 1 do
              vTemp2.Add(Trim(Campos2[IndexCampo2]));
            TmpStr := vTemp2.Text;

            // Inserir a quebra de linha para ficar abaixo da descri��o do produto
            BufferXInfProd := #13 + TmPStr;
          end
          else
            BufferXInfProd := '';

          FieldByName('infAdProd').AsString := BufferXInfProd;
        finally
          vTemp2.Free;
        end;

        FieldByName('NCM').AsString := NCM;
        FieldByName('EXTIPI').AsString := EXTIPI;
        FieldByName('genero').AsString := '';
        FieldByName('CFOP').AsString := CFOP;
        FieldByName('Ucom').AsString := UCom;
        FieldByName('QCom').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(QCom), 0);
        FieldByName('VUnCom').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VUnCom), 0);

        if FDANFEClassOwner.ImprimirTotalLiquido then
          FieldByName('VProd').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VProd - vDesc), 0)
        else
          FieldByName('VProd').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VProd), 0);

        FieldByName('cEANTrib').AsString := cEANTrib;
        FieldByName('UTrib').AsString := uTrib;
        FieldByName('QTrib').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(qTrib), 0);
        FieldByName('VUnTrib').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(vUnTrib), 0);
        FieldByName('vFrete').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(vFrete), 0);
        FieldByName('vSeg').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(vSeg), 0);

        if FDANFEClassOwner.ImprimirDescPorc then
        begin
          if vDesc > 0 then
            FieldByName('vDesc').AsString := DFeUtil.FormatFloat(((vDesc*100) / (VUnCom * QCom))) + '%'
            //FieldByName('vDesc').AsString := DFeUtil.FormatFloat(RoundTo(100 - ((((VUnCom * QCom) - vDesc) / (VUnCom * QCom)) * 100), -1)) + '%'
          else
            FieldByName('vDesc').AsString := DFeUtil.FormatFloat(vDesc);
        end
        else
          FieldByName('vDesc').AsString := DFeUtil.FormatFloat(vDesc);

        with FNFe.Det.Items[i].Imposto.ICMS do
        begin
          FieldByName('ORIGEM').AsString := OrigToStr(orig);

          case FNFe.Emit.CRT of
            crtSimplesNacional, crtSimplesExcessoReceita:
              begin
                case CSOSN of
                  csosn101, csosn102, csosn103, csosn201, csosn202, csosn203, csosn300, csosn400, csosn500, csosn900:
                    begin
                      FieldByName('CST').AsString  := CSOSNIcmsToStr(CSOSN);
                      FieldByName('VBC').AsFloat   := vBC;
                      FieldByName('PICMS').AsFloat := pICMS;
                      FieldByName('VICMS').AsFloat := vICMS;
                    end;
                end;
              end;
          else
            begin
              if CST = cst00 then
              begin
                FieldByName('CST').AsString := CSTICMSToStr(cst00);
                FieldByName('VBC').AsFloat := vBC;
                FieldByName('PICMS').AsFloat := pICMS;
                FieldByName('VICMS').AsFloat := vICMS;
              end
              else if CST = cst10 then
              begin
                FieldByName('CST').AsString := CSTICMSToStr(cst10);
                FieldByName('VBC').AsFloat := vBC;
                FieldByName('PICMS').AsFloat := pICMS;
                FieldByName('VICMS').AsFloat := vICMS;
              end
              else if CST = cst20 then
              begin
                FieldByName('CST').AsString := CSTICMSToStr(cst20);
                FieldByName('VBC').AsFloat := vBC;
                FieldByName('PICMS').AsFloat := pICMS;
                FieldByName('VICMS').AsFloat := vICMS;
              end
              else if CST = cst30 then
              begin
                FieldByName('CST').AsString := CSTICMSToStr(cst30);
                FieldByName('VBC').AsFloat := 0;
                FieldByName('PICMS').AsFloat := 0;
                FieldByName('VICMS').AsFloat := 0;
              end
              else if (CST = cst40) or (CST = cst41) or (CST = cst50) or (CST = cstRep41) then
              begin
                if (CST = cst40) then
                   FieldByName('CST').AsString := CSTICMSToStr(cst40)
                else if (CST = cst41) or (CST = cstRep41) then
                   FieldByName('CST').AsString := CSTICMSToStr(cst41)
                else if (CST = cst50) then
                   FieldByName('CST').AsString := CSTICMSToStr(cst50);
                FieldByName('VBC').AsFloat := 0;
                FieldByName('PICMS').AsFloat := 0;
                FieldByName('VICMS').AsFloat := 0;
              end
              else if (CST = cst51) then
              begin
                FieldByName('CST').AsString := CSTICMSToStr(cst51);
                FieldByName('VBC').AsFloat := vBC;
                FieldByName('PICMS').AsFloat := pICMS;
                FieldByName('VICMS').AsFloat := vICMS;
              end
              else if (CST = cst60) then
              begin
                FieldByName('CST').AsString := CSTICMSToStr(cst60);
                FieldByName('VBC').AsFloat := 0;
                FieldByName('PICMS').AsFloat := 0;
                FieldByName('VICMS').AsFloat := 0;
              end
              else if (CST = cst70) then
              begin
                FieldByName('CST').AsString := CSTICMSToStr(cst70);
                FieldByName('VBC').AsFloat := vBC;
                FieldByName('PICMS').AsFloat := pICMS;
                FieldByName('VICMS').AsFloat := vICMS;
              end
              else if (CST = cst90) then
              begin
                FieldByName('CST').AsString := CSTICMSToStr(cst90);
                FieldByName('VBC').AsFloat := vBC;
                FieldByName('PICMS').AsFloat := pICMS;
                FieldByName('VICMS').AsFloat := vICMS;
              end
              else if (CST = cstVazio) then
              begin
                FieldByName('CST').AsString := ' ';
                FieldByName('VBC').AsFloat := 0;
                FieldByName('PICMS').AsFloat := 0;
                FieldByName('VICMS').AsFloat := 0;
              end;
            end;
          end;
        end;

        with FNFe.Det.Items[i].Imposto.IPI do
        begin
          if (CST = ipi00) or (CST = ipi49) or (CST = ipi50) or (CST = ipi99) then
          begin
            FieldByName('VIPI').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VIPI), 0);
            FieldByName('PIPI').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(PIPI), 0);
          end
          else if (CST = ipi01) or (CST = ipi02) or (CST = ipi03) or (CST = ipi04) or (CST = ipi51) or (CST = ipi52) or (CST = ipi53) or (CST = ipi54) or (CST = ipi55) then
          begin
            FieldByName('VIPI').AsFloat := 0;
            FieldByName('PIPI').AsFloat := 0;
          end
          else
          begin
            FieldByName('VIPI').AsFloat := 0;
            FieldByName('PIPI').AsFloat := 0;
          end;
        end;
      end;

      cdsDadosProdutosDescricaoProduto.AsString := Trim(FieldByName('xProd').AsString);
      if Trim(FieldByName('InfAdProd').AsString) <> '' then
        cdsDadosProdutosDescricaoProduto.AsString := cdsDadosProdutosDescricaoProduto.AsString+#13+
                                                     trim(FieldByName('InfAdProd').AsString);

      Post;
    end;
  end;
end;

procedure TdmACBrNFeFR.CarregaDestinatario;
begin
  { destinat�rio }
  with cdsDestinatario do
  begin
    Close;
    CreateDataSet;
    Append;

    with FNFe.Dest do
    begin
      if DFeUtil.NaoEstaVazio(CNPJCPF) then
       begin
         if Length(CNPJCPF) > 11 then
            FieldByName('CNPJCPF').AsString := DFeUtil.FormatarCNPJ(CNPJCPF)
         else
            FieldByName('CNPJCPF').AsString := DFeUtil.FormatarCPF(CNPJCPF);
       end
      else
         FieldByName('CNPJCPF').AsString := '';

      FieldByName('XNome').AsString := XNome;
      with EnderDest do
      begin
        FieldByName('XLgr').AsString := XLgr;
        FieldByName('Nro').AsString := Nro;
        FieldByName('XCpl').AsString := XCpl;
        FieldByName('XBairro').AsString := XBairro;
        FieldByName('CMun').AsString := IntToStr(CMun);
        FieldByName('XMun').AsString := CollateBr(XMun);
        FieldByName('UF').AsString := UF;
        FieldByName('CEP').AsString := DFeUtil.FormatarCEP(DFeUtil.Poem_Zeros(CEP, 8));
        FieldByName('CPais').AsString := IntToStr(CPais);
        FieldByName('XPais').AsString := XPais;
        FieldByName('Fone').AsString := DFeUtil.FormatarFone(Fone);
      end;
      FieldByName('IE').AsString := IE;
    end;

    Post;
  end;
end;

procedure TdmACBrNFeFR.CarregaDuplicatas;
var
  i: Integer;
begin
  with cdsDuplicatas do
  begin
    Close;
    CreateDataSet;

    for i := 0 to NFe.Cobr.Dup.Count - 1 do
    begin
      Append;
      with FNFe.Cobr.Dup[i] do
      begin
        FieldByName('ChaveNFe').AsString := FNFe.infNFe.ID;
        FieldByName('NDup').AsString := NDup;
        FieldByName('DVenc').AsString := DFeUtil.FormatDate(DateToStr(DVenc));
        FieldByName('VDup').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VDup), 0);
      end;

      Post;
    end;
  end;
end;

procedure TdmACBrNFeFR.CarregaEmitente;
begin
  { emitente }
  with cdsEmitente do
  begin
    Close;
    CreateDataSet;
    Append;

    with FNFe.Emit do
    begin
      FieldByName('CNPJ').AsString := DFeUtil.FormatarCNPJ(CNPJCPF);
      FieldByName('XNome').AsString := XNome;
      FieldByName('XFant').AsString := XFant;
      with EnderEmit do
      begin
        FieldByName('Xlgr').AsString := XLgr;
        FieldByName('Nro').AsString := Nro;
        FieldByName('XCpl').AsString := XCpl;
        FieldByName('XBairro').AsString := XBairro;
        FieldByName('CMun').AsString := IntToStr(CMun);
        FieldByName('XMun').AsString := CollateBr(XMun);
        FieldByName('UF').AsString := UF;
        FieldByName('CEP').AsString := DFeUtil.FormatarCEP(DFeUtil.Poem_Zeros(CEP, 8));
        FieldByName('CPais').AsString := IntToStr(CPais);
        FieldByName('XPais').AsString := XPais;
        FieldByName('Fone').AsString := DFeUtil.FormatarFone(Fone);
      end;
      FieldByName('IE').AsString := IE;
      FieldByName('IM').AsString := IM;
      FieldByName('IEST').AsString := IEST;
      FieldByName('CRT').AsString := CRTToStr(CRT);

      if Trim(FieldByName('CRT').AsString) = '3' then
        FieldByName('DESCR_CST').AsString := 'CST'
      else
        FieldByName('DESCR_CST').AsString  := 'CSOSN';

      cdsEmitenteDADOS_ENDERECO.AsString :=
        Trim(FieldByName('XLgr').AsString) + ', ' + trim(FieldByName('Nro').AsString) + #13 +
        Trim(FieldByName('XBairro').AsString) + ' - ' + Trim(FieldByName('XMun').AsString) + ' - ' + Trim(FieldByName('UF').AsString) + #13 +
        'Fone: ' + Trim(FieldByName('Fone').AsString) + ' CEP: ' + Trim(FieldByName('CEP').AsString);
      if trim(FDANFEClassOwner.Site) <> '' then
        cdsEmitenteDADOS_ENDERECO.AsString := cdsEmitenteDADOS_ENDERECO.AsString + #13 +
        trim(FDANFEClassOwner.Site);
      if trim(FDANFEClassOwner.Email) <> '' then
        cdsEmitenteDADOS_ENDERECO.AsString := cdsEmitenteDADOS_ENDERECO.AsString + #13 +
        trim(FDANFEClassOwner.Email);
    end;

    Post;
  end;
end;

procedure TdmACBrNFeFR.CarregaFatura;
begin
  with cdsFatura do
  begin
    Close;
    CreateDataSet;

    if DFeUtil.NaoEstaVazio(FNFe.Cobr.Fat.nFat) then
    begin
      Append;

      if FNFe.Cobr.Dup.Count = 0 then
      begin
        if FNFe.Ide.indPag = ipVista then
          FieldByName('Pagamento').AsString := 'PAGAMENTO � VISTA'
        else if FNFe.Ide.indPag = ipPrazo then
          FieldByName('Pagamento').AsString := 'PAGAMENTO A PRAZO'
        else
          FieldByName('Pagamento').AsString := ''
      end
      else
        FieldByName('Pagamento').AsString := '';

      with FNFe.Cobr.Fat do
      begin
        FieldByName('nfat').AsString := nFat;
        FieldByName('vOrig').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(vOrig), 0);
        FieldByName('vDesc').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(vDesc), 0);
        FieldByName('vLiq').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(vLiq), 0);
      end;

      Post;
    end;
  end;
end;

procedure TdmACBrNFeFR.CarregaIdentificacao;
begin
  with cdsIdentificacao do
  begin
    Close;
    CreateDataSet;
    Append;

    with FNFe.infNFe do
    begin
      //FieldByName('Versao').AsString := IntToStr(Versao);
      FieldByName('Id').AsString := DFeUtil.LimpaNumero(Id);
      FieldByName('Chave').AsString := NotaUtil.FormatarChaveAcesso(Id);
    end;

    with FNFe.Ide do
    begin
      FieldByName('CUF').AsString := IntToStr(CUF);
      FieldByName('CNF').AsString := IntToStr(CNF);
      FieldByName('NatOp').AsString := NatOp;
      FieldByName('IndPag').AsString := DFeUtil.SeSenao(IndPag = ipVista, '0', DFeUtil.SeSenao(IndPag = ipPrazo, '1', '2'));
      FieldByName('Mod_').AsString := IntToStr(Modelo);
      FieldByName('Serie').AsString := IntToStr(Serie);
      FieldByName('NNF').AsString := DFeUtil.FormatarNumeroDocumentoFiscal(IntToStr(NNF));
      FieldByName('DEmi').AsString := DFeUtil.FormatDate(DateToStr(DEmi));
      FieldByName('DSaiEnt').AsString := IfThen(DSaiEnt <> 0, DFeUtil.FormatDate(DateToStr(DSaiEnt)));
      FieldByName('TpNF').AsString := DFeUtil.SeSenao(TpNF = tnEntrada, '0', '1');
      FieldByName('CMunFG').AsString := IntToStr(CMunFG);
      FieldByName('TpImp').AsString := DFeUtil.SeSenao(TpImp = tiRetrato, '1', '2');
      FieldByName('TpEmis').AsString := DFeUtil.SeSenao(TpEmis = teNormal, '1', '5');
      FieldByName('CDV').AsString := IntToStr(CDV);
      FieldByName('TpAmb').AsString := DFeUtil.SeSenao(TpAmb = taHomologacao, '2', '1');
      FieldByName('FinNFe').AsString := DFeUtil.SeSenao(FinNFe = fnNormal, '1', DFeUtil.SeSenao(FinNFe = fnComplementar, '2', '3'));
      FieldByName('ProcEmi').AsString := DFeUtil.SeSenao(ProcEmi = peAplicativoContribuinte, '0', '');
      FieldByName('VerProc').AsString := VerProc;
    end;

    if FNFe.Ide.hSaiEnt = 0 then
      FieldByName('HoraSaida').AsString := ''
    else
      FieldByName('HoraSaida').AsString := TimeToStr(FNFe.Ide.hSaiEnt);

    Post;
  end;
end;

procedure TdmACBrNFeFR.CarregaInformacoesAdicionais;
var
  i: Integer;
  vTemp: TStringList;
  IndexCampo:Integer;
  Campos: TSplitResult;
  BufferInfCpl: String;
  TmpStr: String;
  wContingencia: string;
  wObs:string;
  wLinhasObs: integer;
begin
  with cdsInformacoesAdicionais do
  begin
    Close;
    CreateDataSet;
    Append;

    wLinhasObs := 0;
    with FNFe.InfAdic do
    begin
      TmpStr := '';
      //Fisco
      if Length(InfAdFisco) = 0 then
        InfAdFisco := '';

      for i := 0 to ObsFisco.Count - 1 do
      begin
        with ObsFisco.Items[i] do
          TmpStr := TmpStr + XCampo + ': ' + XTexto + ';';
      end;
      wObs := TmpStr + InfAdFisco;
      TmpStr := '';

      //Inf. Complementar
      if Length(InfCpl) = 0 then
        InfCpl := '';

      for i := 0 to ObsCont.Count - 1 do
      begin
        with ObsCont.Items[i] do
          TmpStr := TmpStr + XCampo + ': ' + XTexto + ';';
      end;
      if Length(wObs) > 0 then
        wObs := wObs + ';';
      wObs := wObs + TmpStr + InfCpl;
      TmpStr := '';

      //Contingencia
      if FNFe.Ide.tpEmis=teNORMAL then
        wContingencia := ''
      else
      begin
        if (FNFe.Ide.tpEmis = teContingencia) or (FNFe.Ide.tpEmis = teFSDA) or (FNFe.Ide.tpEmis = teSCAN) then
          wContingencia := 'DANFE EM CONTING�NCIA, IMPRESSO EM DECORR�NCIA DE PROBLEMAS T�CNICOS'
        else if FNFe.Ide.tpEmis = teDPEC then
          wContingencia := 'DANFE IMPRESSO EM CONTING�NCIA - DPEC REGULARMENTE RECEBIDA PELA RECEITA FEDERAL DO BRASIL';
          wContingencia := wContingencia + ';' +
                           'DATA/HORA IN�CIO: ' + DFeUtil.SeSenao(FNFe.ide.dhCont = 0, ' ', DateTimeToStr(FNFe.ide.dhCont)) + ';'+
                           'MOTIVO CONTING�NCIA: ' + DFeUtil.SeSenao(DFeUtil.EstaVazio(FNFe.ide.xJust), ' ', FNFe.ide.xJust);
      end;
      if Length(wObs) > 0 then
        wObs := wObs + ';';
      wObs := wObs + wContingencia;

      vTemp := TStringList.Create;
      try
        if Trim(wObs) <> '' then
        begin
          Campos := Split(';', wObs);
          for IndexCampo := 0 to Length(Campos) - 1 do
              vTemp.Add(Campos[IndexCampo]);
           wLinhasObs := 1; //TotalObS(vTemp.Text);
           TmpStr := vTemp.Text;

           BufferInfCpl := TmpStr;
        end
        else
           BufferInfCpl := '';

      finally
        vTemp.Free;
      end;
    end;

    FieldByName('OBS').AsString        := BufferInfCpl;
    FieldByName('LinhasOBS').AsInteger := wLinhasObs;

    Post;
  end;
end;

procedure TdmACBrNFeFR.CarregaISSQN;
begin
  with cdsISSQN do
  begin
    Close;
    CreateDataSet;
    Append;
    with FNFe.Total.ISSQNtot do
    begin
      FieldByName('vSERV').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VServ), 0);
      FieldByName('vBC').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VBC), 0);
      FieldByName('vISS').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(VISS), 0);
    end;
    Post;
  end;
end;

procedure TdmACBrNFeFR.CarregaLocalEntrega;
begin
  { local de entrega }
  with cdsLocalEntrega do
  begin
    Close;
    CreateDataSet;

    if DFeUtil.NaoEstaVazio(FNFe.Entrega.CNPJCPF) then
    begin
      Append;

      with FNFe.Entrega do
      begin
        if DFeUtil.NaoEstaVazio(CNPJCPF) then
        begin
          if Length(CNPJCPF) > 11 then
             FieldByName('CNPJ').AsString := DFeUtil.FormatarCNPJ(CNPJCPF)
          else
             FieldByName('CNPJ').AsString := DFeUtil.FormatarCPF(CNPJCPF);
        end
        else
           FieldByName('CNPJ').AsString := DFeUtil.FormatarCNPJ(DFeUtil.Poem_Zeros(0, 18));

        FieldByName('Xlgr').AsString := XLgr;
        FieldByName('Nro').AsString := Nro;
        FieldByName('XCpl').AsString := XCpl;
        FieldByName('XBairro').AsString := XBairro;
        FieldByName('CMun').AsString := inttostr(CMun);
        FieldByName('XMun').AsString := CollateBr(XMun);
        FieldByName('UF').AsString := UF;
      end;
      Post;
    end;
  end;
end;

procedure TdmACBrNFeFR.CarregaLocalRetirada;
begin
  { local de retirada }
  with cdsLocalRetirada do
  begin
    Close;
    CreateDataSet;

    if DFeUtil.NaoEstaVazio(FNFe.Retirada.CNPJCPF) then
    begin
      Append;

      with FNFe.Retirada do
      begin
        if DFeUtil.NaoEstaVazio(CNPJCPF) then
        begin
          if Length(CNPJCPF) > 11 then
             FieldByName('CNPJ').AsString := DFeUtil.FormatarCNPJ(CNPJCPF)
          else
             FieldByName('CNPJ').AsString := DFeUtil.FormatarCPF(CNPJCPF);
        end
        else
           FieldByName('CNPJ').AsString := DFeUtil.FormatarCNPJ(DFeUtil.Poem_Zeros(0, 18));

        FieldByName('Xlgr').AsString := XLgr;
        FieldByName('Nro').AsString := Nro;
        FieldByName('XCpl').AsString := XCpl;
        FieldByName('XBairro').AsString := XBairro;
        FieldByName('CMun').AsString := inttostr(CMun);
        FieldByName('XMun').AsString := CollateBr(XMun);
        FieldByName('UF').AsString := UF;
      end;
      Post;
    end;
  end;
end;

procedure TdmACBrNFeFR.CarregaParametros;
var
  vChave_Contingencia: String;
  vResumo: String;
  vStream: TMemoryStream;
  vStringStream: TStringStream;
begin
  { par�metros }
  with cdsParametros do
  begin
    Close;
    CreateDataSet;
    Append;

    vResumo := '';
    if DANFEClassOwner.ExibirResumoCanhoto then
    begin
       if DFeUtil.EstaVazio(DANFEClassOwner.ExibirResumoCanhoto_Texto) then
          vResumo := 'Emiss�o: ' + DFeUtil.FormatDate(DateToStr(FNFe.Ide.DEmi)) + '  Dest/Reme: ' + FNFe.Dest.XNome + '  Valor Total: ' + DFeUtil.FormatFloat(FNFe.Total.ICMSTot.VNF)
       else
          vResumo := DANFEClassOwner.ExibirResumoCanhoto_Texto;
    end;
    FieldByName('ResumoCanhoto').AsString := vResumo;

    if (FNFe.Ide.TpAmb = taHomologacao) then
      FieldByName('Mensagem0').AsString := 'NFe sem Valor Fiscal - HOMOLOGA��O'
    else
    begin
      if not (FNFe.Ide.tpEmis in [teContingencia, teFSDA]) then
      begin
        if ((DFeUtil.EstaVazio(FDANFEClassOwner.ProtocoloNFe)) and
            (DFeUtil.EstaVazio(FNFe.procNFe.nProt))) then
          FieldByName('Mensagem0').AsString := 'NFe sem Autoriza��o de Uso da SEFAZ'
        else
        if (not ((DFeUtil.EstaVazio(FDANFEClassOwner.ProtocoloNFe)) and
           (DFeUtil.EstaVazio(FNFe.procNFe.nProt)))) and
           (FNFe.procNFe.cStat in [101,151,155]) then
          FieldByName('Mensagem0').AsString := 'NFe Cancelada'
        else
        begin
          if FDANFEClassOwner.NFeCancelada then
            FieldByName('Mensagem0').AsString := 'NFe Cancelada'
          else
            FieldByName('Mensagem0').AsString := '';
        end;
      end
      else
        FieldByName('Mensagem0').AsString := '';
    end;

    // Carregamento da imagem
    if DFeUtil.NaoEstaVazio(DANFEClassOwner.Logo) then
    begin
      FieldByName('Imagem').AsString := DANFEClassOwner.Logo;
      vStream := TMemoryStream.Create;
      try
        if FileExists(DANFEClassOwner.Logo) then
           vStream.LoadFromFile(DANFEClassOwner.Logo)
        else
        begin
           vStringStream:= TStringStream.Create(DANFEClassOwner.Logo);
           try
              vStream.LoadFromStream(vStringStream);
           finally
              vStringStream.Free;
           end;
        end;

        vStream.Position := 0;
        cdsParametrosLogoCarregado.LoadFromStream(vStream);
      finally
        vStream.Free;
      end;
    end;

    if DANFEClassOwner.ExpandirLogoMarca then
      FieldByName('LogoExpandido').AsString := '1'
    else
      FieldByName('LogoExpandido').AsString := '0';

    if FDANFEClassOwner.Sistema <> '' then
      FieldByName('Sistema').AsString := FDANFEClassOwner.Sistema
    else
      FieldByName('Sistema').AsString := 'Projeto ACBr - http://acbr.sf.net';

    if FDANFEClassOwner.Usuario <> '' then
      FieldByName('Usuario').AsString := ' - ' + FDANFEClassOwner.Usuario
    else
      FieldByName('Usuario').AsString := '';

    if FDANFEClassOwner.Fax <> '' then
      FieldByName('Fax').AsString := ' - FAX ' + FDANFEClassOwner.Fax
    else
      FieldByName('Fax').AsString := '';

    FieldByName('Site').AsString := FDANFEClassOwner.Site;
    FieldByName('Email').AsString := FDANFEClassOwner.Email;

    if FDANFEClassOwner.ImprimirDescPorc then
      FieldByName('Desconto').AsString := '%'
    else
      FieldByName('Desconto').AsString := 'VALOR';

    if FDANFEClassOwner.ImprimirTotalLiquido then
      FieldByName('TotalLiquido').AsString := 'L�QUIDO'
    else
      FieldByName('TotalLiquido').AsString := 'TOTAL';

    FieldByName('Contingencia_ID').AsString := '';
    FieldByName('ConsultaAutenticidade').AsString := 'Consulta de autenticidade no portal nacional da NF-e'+#13+
                                                     'www.nfe.fazenda.gov.br/portal ou no site da Sefaz autorizadora';
    if ((FNFe.Ide.tpEmis=teNormal) or (FNFe.Ide.tpEmis = teSCAN)) then
    begin
      FieldByName('ChaveAcesso_Descricao').AsString := 'CHAVE DE ACESSO';
      FieldByName('Contingencia_ID').AsString := '';

      if ((FDANFEClassOwner.NFeCancelada) or (FNFe.procNFe.cStat in [101,151,155])) then
        FieldByName('Contingencia_Descricao').AsString := 'PROTOCOLO DE HOMOLOGA��O DO CANCELAMENTO'
      else
        FieldByName('Contingencia_Descricao').AsString := 'PROTOCOLO DE AUTORIZA��O DE USO';

      if DFeUtil.EstaVazio(FDANFEClassOwner.ProtocoloNFe) then
      begin
        if not (FNFe.Ide.tpEmis in [teContingencia, teFSDA]) and DFeUtil.EstaVazio(FNFe.procNFe.nProt) then
          FieldByName('Contingencia_Valor').AsString := 'NFe sem Autoriza��o de Uso da SEFAZ'
        else
          FieldByName('Contingencia_Valor').AsString := FNFe.procNFe.nProt + ' ' + DFeUtil.SeSenao(FNFe.procNFe.dhRecbto <> 0, DateTimeToStr(FNFe.procNFe.dhRecbto), '');
      end
      else
        FieldByName('Contingencia_Valor').AsString := FDANFEClassOwner.ProtocoloNFe;
    end
    else
    begin
      vChave_Contingencia := NotaUtil.GerarChaveContingencia(FNFe);
      FieldByName('ChaveAcesso_Descricao').AsString := 'CHAVE DE ACESSO';

      if ((FNFe.Ide.tpEmis = teContingencia) or (FNFe.Ide.tpEmis = teFSDA)) then
      begin
        FieldByName('Contingencia_ID').AsString := vChave_Contingencia;
        FieldByName('Contingencia_Descricao').AsString := 'DADOS DA NF-E';
        FieldByName('Contingencia_Valor').AsString := NotaUtil.FormatarChaveContigencia(vChave_Contingencia);
        FieldByName('ConsultaAutenticidade').AsString := '';
      end
      else
      if (FNFe.Ide.tpEmis = teDPEC) then
      begin
        FieldByName('Contingencia_Descricao').AsString := 'N�MERO DE REGISTRO DPEC';

        //precisa testar
        if DFeUtil.EstaVazio(FDANFEClassOwner.ProtocoloNFe) then
          raise EACBrNFeException.Create('Protocolo de Registro no DPEC n�o informado.')
        else
          FieldByName('Contingencia_Valor').AsString := FDANFEClassOwner.ProtocoloNFe;
      end;
    end;

    FieldByName('LinhasPorPagina').AsInteger := FDANFEClassOwner.ProdutosPorPagina;

    FieldByName('Mask_qCom').AsString     := FDANFEClassOwner.CasasDecimais._Mask_qCom;
    FieldByName('Mask_vUnCom').AsString   := FDANFEClassOwner.CasasDecimais._Mask_vUnCom;
    FieldByName('Casas_qCom').AsInteger   := FDANFEClassOwner.CasasDecimais._qCom;
    FieldByName('Casas_vUnCom').AsInteger := FDANFEClassOwner.CasasDecimais._vUnCom;

    Post;
  end;
end;

procedure TdmACBrNFeFR.CarregaTransportador;
var
  wFrete: String;
begin
  with cdsTransportador do
  begin
    Close;
    CreateDataSet;
    Append;

    with FNFe.Transp do
    begin
      case ModFrete of
        mfContaEmitente: wFrete:='0-EMITENTE';
        mfContaDestinatario: wFrete:='1-DEST/REM';
        mfContaTerceiros: wFrete:='2-TERCEIROS';
        mfSemFrete: wFrete:='9-SEM FRETE';
      end;
      FieldByName('ModFrete').AsString := wFrete;

      with Transporta do
      begin
        if DFeUtil.NaoEstaVazio(CNPJCPF) then
        begin
          if Length(CNPJCPF) > 11 then
            FieldByName('CNPJCPF').AsString := DFeUtil.FormatarCNPJ(CNPJCPF)
          else
            FieldByName('CNPJCPF').AsString := DFeUtil.FormatarCPF(CNPJCPF);
        end
        else
          FieldByName('CNPJCPF').AsString := '';

        FieldByName('XNome').AsString := XNome;
        FieldByName('IE').AsString := IE;
        FieldByName('XEnder').AsString := XEnder;
        FieldByName('XMun').AsString := CollateBr(XMun);
        FieldByName('UF').AsString := UF;
      end;
    end;

    Post;
  end;
end;

procedure TdmACBrNFeFR.CarregaVeiculo;
begin
  with cdsVeiculo do
  begin
    Close;
    CreateDataSet;
    Append;

    with FNFe.Transp.VeicTransp do
    begin
      FieldByName('PLACA').AsString := Placa;
      FieldByName('UF').AsString := UF;
      FieldByName('RNTC').AsString := RNTC;
    end;

    Post;
  end;
end;

procedure TdmACBrNFeFR.CarregaVolumes;
var
  i: Integer;
begin
  with cdsVolumes do
  begin
    Close;
    CreateDataSet;

    for i := 0 to NFe.Transp.Vol.Count - 1 do
    begin
      Append;
      with FNFe.Transp.Vol[i] do
      begin
        FieldByName('QVol').AsFloat := DFeUtil.StringToFloatDef(IntToStr(QVol), 0);
        FieldByName('Esp').AsString := Esp;
        FieldByName('Marca').AsString := Marca;
        FieldByName('NVol').AsString := NVol;
        FieldByName('PesoL').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(PesoL), 0);
        FieldByName('PesoB').AsFloat := DFeUtil.StringToFloatDef(FloatToStr(PesoB), 0);
      end;
      Post;
    end;

  end;
end;

procedure TdmACBrNFeFR.CarregaDadosEventos;
var
  i: Integer;
  CondicoesUso, Correcao: String;
begin
  with cdsEventos do
  begin
    Close;

    FieldDefs.Clear;
    FieldDefs.Add('DescricaoTipoEvento', ftString, 150);
    FieldDefs.Add('Modelo', ftString, 2);
    FieldDefs.Add('Serie', ftString, 3);
    FieldDefs.Add('Numero', ftString, 9);
    FieldDefs.Add('MesAno', ftString, 5);
    FieldDefs.Add('Barras', ftString, 44);
    FieldDefs.Add('ChaveAcesso', ftString, 60);
    FieldDefs.Add('cOrgao', ftInteger);
    FieldDefs.Add('tpAmb', ftString, 100);
    FieldDefs.Add('dhEvento', ftDateTime);
    FieldDefs.Add('TipoEvento', ftString, 6);
    FieldDefs.Add('DescEvento', ftString, 100);
    FieldDefs.Add('nSeqEvento', ftInteger);
    FieldDefs.Add('versaoEvento', ftString, 10);
    FieldDefs.Add('cStat', ftInteger);
    FieldDefs.Add('xMotivo', ftString, 100);
    FieldDefs.Add('nProt', ftString, 20);
    FieldDefs.Add('dhRegEvento', ftDateTime);
    FieldDefs.Add('xJust', ftBlob);
    FieldDefs.Add('xCondUso', ftBlob);
    FieldDefs.Add('xCorrecao', ftBlob);

    CreateDataSet;

    for i := 0 to FEvento.Evento.Count - 1 do
    begin
      Append;

      with Evento.Evento[i] do
      begin
        FieldByName('DescricaoTipoEvento').AsString := InfEvento.DescricaoTipoEvento(InfEvento.tpEvento);

        // nota fiscal eletronica
        FieldByName('Modelo').AsString      := Copy(InfEvento.chNFe, 21, 2);
        FieldByName('Serie').AsString       := Copy(InfEvento.chNFe, 23, 3);
        FieldByName('Numero').AsString      := Copy(InfEvento.chNFe, 26, 9);
        FieldByName('MesAno').AsString      := Copy(InfEvento.chNFe, 05, 2) + '/' + copy(InfEvento.chNFe, 03, 2);
        FieldByName('Barras').AsString      := InfEvento.chNFe;
        FieldByName('ChaveAcesso').AsString := NotaUtil.FormatarChaveAcesso(InfEvento.chNFe);

        // Carta de corre��o eletr�nica
        FieldByName('cOrgao').AsInteger := InfEvento.cOrgao;

        case InfEvento.tpAmb of
          taProducao:    FieldByName('tpAmb').AsString := 'PRODU��O';
          taHomologacao: FieldByName('tpAmb').AsString := 'HOMOLOGA��O - SEM VALOR FISCAL';
        end;

        FieldByName('dhEvento').AsDateTime    := InfEvento.dhEvento;
        FieldByName('TipoEvento').AsString    := InfEvento.TipoEvento;
        FieldByName('DescEvento').AsString    := InfEvento.DescEvento;
        FieldByName('nSeqEvento').AsInteger   := InfEvento.nSeqEvento;
        FieldByName('versaoEvento').AsString  := InfEvento.versaoEvento;
        FieldByName('cStat').AsInteger        := RetInfEvento.cStat;
        FieldByName('xMotivo').AsString       := RetInfEvento.xMotivo;
        FieldByName('nProt').AsString         := RetInfEvento.nProt;
        FieldByName('dhRegEvento').AsDateTime := RetInfEvento.dhRegEvento;

        if InfEvento.tpEvento <> teCCe then
        begin
          FieldByName('xJust').AsString := InfEvento.detEvento.xJust;
        end
        else
        begin
          CondicoesUso := InfEvento.detEvento.xCondUso;
          CondicoesUso := StringReplace(CondicoesUso, 'com: I', 'com:'+#13+' I', [rfReplaceAll]);
          CondicoesUso := StringReplace(CondicoesUso, ';', ';' + #13, [rfReplaceAll]);

          Correcao := InfEvento.detEvento.xCorrecao;
          Correcao := StringReplace(InfEvento.detEvento.xCorrecao, ';', #13, [rfReplaceAll]);

          FieldByName('xCondUso').AsString  := CondicoesUso;
          FieldByName('xCorrecao').AsString := Correcao;
        end;
      end;

      Post;
    end;
  end;
end;

constructor TdmACBrNFeFR.Create(AOwner: TComponent);
begin
  inherited;
  FDANFEClassOwner := TACBrNFeDANFEClass(AOwner);
end;

end.

