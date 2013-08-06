{******************************************************************************}
{ Projeto: Componente ACBrNFe                                                  }
{  Biblioteca multiplataforma de componentes Delphi para emiss�o de Nota Fiscal}
{ eletr�nica - NFe - http://www.nfe.fazenda.gov.br                             }
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
|* 16/12/2008: Wemerson Souto
|*  - Doa��o do componente para o Projeto ACBr
|* 20/08/2009: Caique Rodrigues
|*  - Doa��o units para gera��o do Danfe via QuickReport
|* 20/11/2009: Peterson de Cerqueira Matos
|*             E-mail: peterson161@yahoo.com - Tel: (11) 7197-1474 / 8059-4055
|*  - Componente e Units do QuickReport clonados
|*    e transformados em FORTES REPORT
|* 27/01/2010: Peterson de Cerqueira Matos
|*  - Acr�scimo dos par�metros "ALarguraCodProd" nas Class procedures
|*    "Imprimir" e "SavePDF"
|* 05/02/2010: Peterson de Cerqueira Matos
|*  - Tratamento das propriedades "Email", "ResumoCanhoto", "Fax", "NumCopias",
|*    "Ssitema", "Site", "Usuario" em "ACBrNFeDANFeClass"
|*  - Acr�scimo dos par�metros "AEmail", "AResumoCanhoto", "AFax", "ANumCopias",
|*    "ASsitema", "ASite", "AUsuario" nas Class procedures
|*    "Imprimir" e "SavePDF"
|* 13/02/2010: Peterson de Cerqueira Matos
|*  - Corre��o na exibi��o do 'Preview' para modo 'PREVIEWMODAL'
|* 15/03/2010: Felipe Feltes
|*  - Adequa��o na se��o 'USES' para ser utilizado em CLX
|* 19/03/2010: Peterson de Cerqueira Matos
|*  - Tratamento das propriedades "FormularioContinuo", "ExpandirLogoMarca" e
|*    "MostrarPreview" de "ACBrNFeDANFeClass"
|*  - Acr�scimo dos par�metros "APosCanhoto", "AFormularioContinuo",
|*    "AExpandirLogoMarca" e "AMostrarPreview" nas Class procedures
|*    "Imprimir" e "SavePDF" (esta �ltima sem o "AMostrarPreview")
|* 22/03/2010: Peterson de Cerqueira Matos
|*  - Tratamento das margens em "ACBrNFeDANFeClass"
|*  - Acr�scimo dos par�metros "AMargemSuperior", "AMargemInferior",
|*    "AMargemEsqurda", "AMargemDireita" e "AFonteDANFE"
|* 13/04/2010: Peterson de Cerqueira Matos
|*  - Adequa��o � NF-e 2.0, Manual de Integra��o do Contribuinte 4.0.1NT2009.006
|*  - Tratamento das casas decimais em "ACBrNFeDANFeClass"
|*  - Acr�scimo dos par�metros "ACasasDecimaisqCom" e "ACasasDecimaisvUnCom"
|* 06/07/2010: Peterson de Cerqueira Matos
|*  - Tratamento do formato de impress�o e da quantidade de produtos por
|*  - p�gina em "ACBrNFeDANFeClass"
|*  - Acr�scimo dos par�metros "ATipoDANFE" e "AProdutosPorPagina"
|* 20/07/2010: Peterson de Cerqueira Matos
|*  - Permite enviar o DANFe para a impressora informada em "Impressora"
|*  - Acr�scimo do par�metro "AImpressora"
|* 10/08/2010: Peterson de Cerqueira Matos
|*  - Acr�scimo do par�metro "ATamanhoFonte_RazaoSocial"
|* 25/11/2010: Peterson de Cerqueira Matos
|*  - Acr�scimo do par�metro "AExibirEAN"
|* 01/03/2011: Fernando Emiliano David Nunes
|*  - Acr�scimo do par�metro "AProtocoloNFe"
|* 20/05/2011: Peterson de Cerqueira Matos
|*  - Acr�scimo do par�metro "AResumoCanhoto_Texto"
|* 23/05/2011: Waldir Paim
|*  - In�cio da prepara��o para Lazarus: Somente utiliza TClientDataSet quando
|*    estiver no Delphi. Obrigat�ria a utiliza��o da vers�o 3.70B ou superior
|*    do Fortes Report. Download dispon�vel em
|*    http://sourceforge.net/projects/fortesreport/files/
|* 22/03/2013: Peterson de Cerqueira Matos
|*  - Impress�o dos detalhamentos espec�ficos e do desconto em percentual
******************************************************************************}
{$I ACBr.inc}
unit ACBrNFeDANFeRL;

interface

uses
  SysUtils, Variants, Classes, StrUtils,
  {$IFDEF CLX}
  QGraphics, QControls, QForms, QDialogs, QExtCtrls, Qt,
  {$ELSE}
    {$IFDEF MSWINDOWS}Windows, Messages, {$ENDIF}
      Graphics, Controls, Forms, Dialogs, ExtCtrls,
  {$ENDIF}
  MaskUtils, pcnNFe, pcnConversao, ACBrNFe, ACBrDFeUtil, ACBrNFeDANFeRLClass,
  RLReport, RLFilters, RLPrinters, RLPDFFilter, RLConsts,
  {$IFDEF BORLAND} DBClient, {$ELSE} BufDataset, {$ENDIF} DB;

type
  TfrlDANFeRL = class(TForm)
    RLNFe: TRLReport;
    RLPDFFilter1: TRLPDFFilter;
    DataSource1: TDataSource;
    procedure FormDestroy(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    function BuscaDireita(Busca, Text: String): Integer;
    function FormatarCEP(AValue: String): String;
    function FormatarFone(AValue: String): String;
    procedure InsereLinhas(sTexto: String; iLimCaracteres: Integer; rMemo: TRLMemo);

  private

    { Private declarations }
  protected
    FACBrNFe: TACBrNFe;
    FNFe: TNFe;
    FLogo: String;
    FMarcaDagua: String;
    FLarguraCodProd: Integer;
    FEmail: String;
    FResumoCanhoto: Boolean;
    FFax: String;
    FNumCopias: Integer;
    FSsitema: String;
    FSite: String;
    FUsuario: String;
    FPosCanhoto: TPosCanhoto;
    FFormularioContinuo: Boolean;
    FExpandirLogoMarca: Boolean;
    FMostrarPreview: Boolean;
    FNomeFonte: TNomeFonte;
    FNegrito: Boolean;
    FMargemSuperior: Double;
    FMargemInferior: Double;
    FMargemEsquerda: Double;
    FMargemDireita: Double;
    FCasasDecimaisqCom: Integer;
    FCasasDecimaisvUnCom: Integer;
    FProdutosPorPagina: Integer;
    FImpressora: String;
    FTamanhoFonte_RazaoSocial: Integer;
    FExibirEAN: Boolean;
    FProtocoloNFe : String;
    FResumoCanhoto_Texto: String;
    FNFeCancelada: Boolean; // Incluido em 22/02/2013 por Jorge Henrique
    FImprimirDetalhamentoEspecifico: Boolean;
    FImprimirDescPorc: Boolean;
    FDetVeiculos: TDetVeiculos;
    FDetMedicamentos: TDetMedicamentos;
    FDetArmamentos: TDetArmamentos;
    FDetCombustiveis: TDetCombustiveis;

    cdsItens:  {$IFDEF BORLAND} TClientDataSet {$ELSE} TBufDataset{$ENDIF};
    procedure ConfigDataSet;
  public
    { Public declarations }
    class procedure Imprimir(ANFe: TNFe; ALogo: String = '';
                    AMarcaDagua: String = ''; ALarguraCodProd: Integer = 54;
                    AEmail: String = ''; AResumoCanhoto: Boolean = False;
                    AFax: String = ''; ANumCopias: Integer = 1;
                    ASistema: String = ''; ASite: String = '';
                    AUsuario: String = '';
                    APosCanhoto: TPosCanhoto = pcCabecalho;
                    AFormularioContinuo: Boolean = False;
                    AExpandirLogoMarca: Boolean = False;
                    AMostrarPreview: Boolean = True;
                    ANomeFonte: TNomeFonte = nfTimesNewRoman;
                    ANegrito: Boolean = True;
                    AMargemSuperior: Double = 0.7;
                    AMargemInferior: Double = 0.7;
                    AMargemEsquerda: Double = 0.7;
                    AMargemDireita: Double = 0.7;
                    ACasasDecimaisqCom: Integer = 4;
                    ACasasDecimaisvUncCom: Integer = 4;
                    AProdutosPorPagina: Integer = 0;
                    AImpressora: String = '';
                    ATamanhoFonte_RazaoSocial: Integer = 8;
                    AExibirEAN: Boolean = False;
                    AProtocoloNFe: String = '';
                    AResumoCanhoto_Texto: String = '';
                    ANFECancelada: Boolean = False; // Incluido em 22/02/2013 por Jorge Henrique
                    AImprimirDetalhamentoEspecifico: Boolean = True;
                    AImprimirDescPorc: Boolean = False;
                    ADetVeiculos: TDetVeiculos = [];
                    ADetMedicamentos: TDetMedicamentos = [];
                    ADetArmamentos: TDetArmamentos = [];
                    ADetCombustiveis: TDetCombustiveis = []);

    class procedure SavePDF(ANFe: TNFe; ALogo: String = '';
                    AMarcaDagua: String = ''; ALarguraCodProd: Integer = 54;
                    AEmail: String = ''; AResumoCanhoto: Boolean = False;
                    AFax: String = ''; ANumCopias: Integer = 1;
                    ASistema: String = ''; ASite: String = '';
                    AUsuario: String = ''; AFile: String = '';
                    APosCanhoto: TPosCanhoto = pcCabecalho;
                    AFormularioContinuo: Boolean = False;
                    AExpandirLogoMarca: Boolean = False;
                    ANomeFonte: TNomeFonte = nfTimesNewRoman;
                    ANegrito: Boolean = True;
                    AMargemSuperior: Double = 0.7;
                    AMargemInferior: Double = 0.7;
                    AMargemEsquerda: Double = 0.7;
                    AMargemDireita: Double = 0.7;
                    ACasasDecimaisqCom: Integer = 4;
                    ACasasDecimaisvUncCom: Integer = 4;
                    AProdutosPorPagina: Integer = 0;
                    ATamanhoFonte_RazaoSocial: Integer = 8;
                    AExibirEAN: Boolean = False;
                    AProtocoloNFe: String = '';
                    AResumoCanhoto_Texto: String = '';
                    ANFECancelada: Boolean = False; // Incluido em 22/02/2013 por Jorge Henrique
                    AImprimirDetalhamentoEspecifico: Boolean = True;
                    AImprimirDescPorc: Boolean = False;
                    ADetVeiculos: TDetVeiculos = [];
                    ADetMedicamentos: TDetMedicamentos = [];
                    ADetArmamentos: TDetArmamentos = [];
                    ADetCombustiveis: TDetCombustiveis = []);

  end;

   const
    sDisplayFormat = '###,###,###,##0.%.*d';

var
  iLimiteLinhas, iLinhasUtilizadas, iLimiteCaracteresLinha,
  iLimiteCaracteresContinuacao: Integer;

implementation

{$R *.dfm}

procedure TfrlDANFeRL.ConfigDataSet;
begin
 if not Assigned( cdsItens ) then
 cdsItens:=  {$IFDEF BORLAND}  TClientDataSet.create(nil)  {$ELSE}  TBufDataset.create(nil) {$ENDIF};

  if cdsItens.Active then
 begin
 {$IFDEF BORLAND}
  if cdsItens is TClientDataSet then
  TClientDataSet(cdsItens).EmptyDataSet;
 {$ENDIF}
  cdsItens.Active := False;
 end;

 {$IFDEF BORLAND}
 if cdsItens is TClientDataSet then
  begin
  TClientDataSet(cdsItens).StoreDefs := False;
  TClientDataSet(cdsItens).IndexDefs.Clear;
  TClientDataSet(cdsItens).IndexFieldNames := '';
  TClientDataSet(cdsItens).IndexName := '';
  TClientDataSet(cdsItens).Aggregates.Clear;
  TClientDataSet(cdsItens).AggFields.Clear;
  end;
 {$ELSE}
 if cdsItens is TBufDataset then
  begin
  TBufDataset(cdsItens).IndexDefs.Clear;
  TBufDataset(cdsItens).IndexFieldNames:='';
  TBufDataset(cdsItens).IndexName:='';
  end;
 {$ENDIF}

 with cdsItens do
  if FieldCount = 0 then
  begin
    FieldDefs.Clear;
    Fields.Clear;
    FieldDefs.Add('CODIGO',ftString,60);
    FieldDefs.Add('EAN',ftString,14);
    FieldDefs.Add('DESCRICAO',ftWideString,2000);
    FieldDefs.Add('NCM',ftString,8);
    FieldDefs.Add('CFOP',ftString,4);
    FieldDefs.Add('UNIDADE',ftString,6);
    FieldDefs.Add('QTDE',ftString,18);
    FieldDefs.Add('VALOR',ftString,18);
    FieldDefs.Add('VALORDESC',ftString,18);
    FieldDefs.Add('TOTAL',ftString,18);
    FieldDefs.Add('CST',ftString,3);
    FieldDefs.Add('CSOSN',ftString,4);
    FieldDefs.Add('BICMS',ftString,18);
    FieldDefs.Add('ALIQICMS',ftString,6);
    FieldDefs.Add('VALORICMS',ftString,18);
    FieldDefs.Add('BICMSST',ftString,18);
    FieldDefs.Add('VALORICMSST',ftString,18);
    FieldDefs.Add('ALIQIPI',ftString,6);
    FieldDefs.Add('VALORIPI',ftString,18);

   {$IFDEF BORLAND}
    if cdsItens is TClientDataSet then
    TClientDataSet(cdsItens).CreateDataSet;
   {$ELSE}
    if cdsItens is TBufDataset then
    TBufDataset(cdsItens).CreateDataSet;
   {$ENDIF}
   end;

 {$IFDEF BORLAND}
  if cdsItens is TClientDataSet then
  TClientDataSet(cdsItens).StoreDefs := False;
 {$ENDIF}

   if not cdsItens.Active then
   cdsItens.Active := True;

  {$IFDEF BORLAND}
   if cdsItens is TClientDataSet then
   if cdsItens.Active then
   TClientDataSet(cdsItens).LogChanges := False;
 {$ENDIF}

 DataSource1.dataset := cdsItens;

end;

class procedure TfrlDANFeRL.Imprimir(ANFe: TNFe; ALogo: String = '';
                AMarcaDagua: String = ''; ALarguraCodProd: Integer = 54;
                AEmail: String = ''; AResumoCanhoto: Boolean = False;
                AFax: String = ''; ANumCopias: Integer = 1;
                ASistema: String = ''; ASite: String = '';
                AUsuario: String = '';
                APosCanhoto: TPosCanhoto = pcCabecalho;
                AFormularioContinuo: Boolean = False;
                AExpandirLogoMarca: Boolean = False;
                AMostrarPreview: Boolean = True;
                ANomeFonte: TNomeFonte = nfTimesNewRoman;
                ANegrito: Boolean = True;
                AMargemSuperior: Double = 0.7;
                AMargemInferior: Double = 0.7;
                AMargemEsquerda: Double = 0.7;
                AMargemDireita: Double = 0.7;
                ACasasDecimaisqCom: Integer = 4;
                ACasasDecimaisvUncCom: Integer = 4;
                AProdutosPorPagina: Integer = 0;
                AImpressora: String = '';
                ATamanhoFonte_RazaoSocial: Integer = 8;
                AExibirEAN: Boolean = False;
                AProtocoloNFe: String = '';
                AResumoCanhoto_Texto: String = '';
                ANFECancelada: Boolean = False; // Incluido em 22/02/2013 por Jorge Henrique
                AImprimirDetalhamentoEspecifico: Boolean = True;
                AImprimirDescPorc: Boolean = False;
                ADetVeiculos: TDetVeiculos = [];
                ADetMedicamentos: TDetMedicamentos = [];
                ADetArmamentos: TDetArmamentos = [];
                ADetCombustiveis: TDetCombustiveis = []);

begin
  with Create ( nil ) do
    try
      FNFe := ANFe;
      FLogo := ALogo;
      FMarcaDagua := AMarcaDagua;
      FLarguraCodProd := ALarguraCodProd;
      FEmail := AEmail;
      FResumoCanhoto := AResumoCanhoto;
      FFax := AFax;
      FNumCopias := ANumCopias;
      FSsitema := ASistema;
      FSite := ASite;
      FUsuario := AUsuario;
      FPosCanhoto := APosCanhoto;
      FFormularioContinuo := AFormularioContinuo;
      FExpandirLogoMarca := AExpandirLogoMarca;
      FMostrarPreview := AMostrarPreview;
      FNomeFonte := ANomeFonte;
      FNegrito := ANegrito;
      FMargemSuperior := AMargemSuperior;
      FMargemInferior := AMargemInferior;
      FMargemEsquerda := AMargemEsquerda;
      FMargemDireita := AMargemDireita;
      FCasasDecimaisqCom := ACasasDecimaisqCom;
      FCasasDecimaisvUnCom := ACasasDecimaisvUncCom;
      FProdutosPorPagina := AProdutosPorPagina;
      FImpressora := AImpressora;
      FTamanhoFonte_RazaoSocial := ATamanhoFonte_RazaoSocial;
      FExibirEAN := AExibirEAN;
      FProtocoloNFe := AProtocoloNFe;
      FResumoCanhoto_Texto := AResumoCanhoto_Texto;
      FNFeCancelada := ANFeCancelada; // Incluido em 22/02/2013 por Jorge Henrique
      FImprimirDetalhamentoEspecifico := AImprimirDetalhamentoEspecifico;
      FImprimirDescPorc := AImprimirDescPorc;
      FDetVeiculos := ADetVeiculos;
      FDetMedicamentos := ADetMedicamentos;
      FDetArmamentos := ADetArmamentos;
      FDetCombustiveis := ADetCombustiveis;

      if FImpressora > '' then
        RLPrinter.PrinterName := FImpressora;

      if FNumCopias > 0 then
        RLPrinter.Copies := FNumCopias
      else
        RLPrinter.Copies := 1;

      if FMostrarPreview = True then
        RLNFe.PreviewModal
      else
        RLNFe.Print;

    finally
      Free ;
    end ;
end;

class procedure TfrlDANFeRL.SavePDF(ANFe: TNFe; ALogo: String = '';
                    AMarcaDagua: String = ''; ALarguraCodProd: Integer = 54;
                    AEmail: String = ''; AResumoCanhoto: Boolean = False;
                    AFax: String = ''; ANumCopias: Integer = 1;
                    ASistema: String = ''; ASite: String = '';
                    AUsuario: String = '' ; AFile: String = '';
                    APosCanhoto: TPosCanhoto = pcCabecalho;
                    AFormularioContinuo: Boolean = False;
                    AExpandirLogoMarca: Boolean = False;
                    ANomeFonte: TNomeFonte = nfTimesNewRoman;
                    ANegrito: Boolean = True;
                    AMargemSuperior: Double = 0.7;
                    AMargemInferior: Double = 0.7;
                    AMargemEsquerda: Double = 0.7;
                    AMargemDireita: Double = 0.7;
                    ACasasDecimaisqCom: Integer = 4;
                    ACasasDecimaisvUncCom: Integer = 4;
                    AProdutosPorPagina: Integer = 0;
                    ATamanhoFonte_RazaoSocial: Integer = 8;
                    AExibirEAN: Boolean = False;
                    AProtocoloNFe: String = '';
                    AResumoCanhoto_Texto: String = '';
                    ANFECancelada: Boolean = False; // Incluido em 22/02/2013 por Jorge Henrique
                    AImprimirDetalhamentoEspecifico: Boolean = True;
                    AImprimirDescPorc: Boolean = False;
                    ADetVeiculos: TDetVeiculos = [];
                    ADetMedicamentos: TDetMedicamentos = [];
                    ADetArmamentos: TDetArmamentos = [];
                    ADetCombustiveis: TDetCombustiveis = []);

begin
  with Create ( nil ) do
    try
      FNFe := ANFe;
      FLogo := ALogo;
      FMarcaDagua := AMarcaDagua;
      FLarguraCodProd := ALarguraCodProd;
      FEmail := AEmail;
      FResumoCanhoto := AResumoCanhoto;
      FFax := AFax;
      FNumCopias := ANumCopias;
      FSsitema := ASistema;
      FSite := ASite;
      FUsuario := AUsuario;
      FPosCanhoto := APosCanhoto;
      FFormularioContinuo := AFormularioContinuo;
      FExpandirLogoMarca := AExpandirLogoMarca;
      FNomeFonte := ANomeFonte;
      FNegrito := ANegrito;
      FMargemSuperior := AMargemSuperior;
      FMargemInferior := AMargemInferior;
      FMargemEsquerda := AMargemEsquerda;
      FMargemDireita := AMargemDireita;
      FCasasDecimaisqCom := ACasasDecimaisqCom;
      FCasasDecimaisvUnCom := ACasasDecimaisvUncCom;
      FProdutosPorPagina := AProdutosPorPagina;
      FTamanhoFonte_RazaoSocial := ATamanhoFonte_RazaoSocial;
      FExibirEAN := AExibirEAN;
      FProtocoloNFe := AProtocoloNFe;
      FResumoCanhoto_Texto := AResumoCanhoto_Texto;
      FNFeCancelada := ANFeCancelada; // Incluido em 22/02/2013 por Jorge Henrique
      FImprimirDetalhamentoEspecifico := AImprimirDetalhamentoEspecifico;
      FImprimirDescPorc := AImprimirDescPorc;
      FDetVeiculos := ADetVeiculos;
      FDetMedicamentos := ADetMedicamentos;
      FDetArmamentos := ADetArmamentos;
      FDetCombustiveis := ADetCombustiveis;

      with RLPDFFilter1.DocumentInfo do
        begin
          Title := 'DANFE - Nota fiscal n� ' +
                                      FormatFloat('000,000,000', FNFe.Ide.nNF);
          KeyWords := 'N�mero:' + FormatFloat('000,000,000', FNFe.Ide.nNF) +
                      '; Data de emiss�o: ' + FormatDateTime('dd/mm/yyyy', FNFe.Ide.dEmi) +
                      '; Destinat�rio: ' + FNFe.Dest.xNome +
                      '; CNPJ: ' + FNFe.Dest.CNPJCPF +
                      '; Valor total: ' + FormatFloat('###,###,###,###,##0.00', FNFe.Total.ICMSTot.vNF);
        end;

      RLNFe.SaveToFile(AFile);
    finally
      Free ;
    end ;
end;

procedure TfrlDANFeRL.FormDestroy(Sender: TObject);
begin
  FreeAndNil( cdsItens );
end;

procedure TfrlDANFeRL.FormCreate(Sender: TObject);
begin
  ConfigDataSet;
end;

function TfrlDANFeRL.BuscaDireita(Busca, Text: String): Integer;
{Pesquisa um caractere � direita da string, retornando sua posi��o}
var n, retorno: integer;
begin
  retorno := 0;
    for n := length(Text) downto 1 do
      begin
        if Copy(Text, n, 1) = Busca then
          begin
            retorno := n;
            break;
         end;
      end;
  Result := retorno;
end;

{Fun��o original de ACBrNFeUtil modificada para exibir em outro formato}
function TfrlDANFeRL.FormatarCEP(AValue: String): String;
var i, iZeros: Integer;
sCep: String;
begin
  if Length(AValue) <= 8 then
    begin
      iZeros := 8 - Length(AValue);
      sCep := AValue;
      For i := 1 to iZeros do
        begin
          sCep := '0' + sCep;
        end;
      Result := copy(sCep,1,5) + '-' + copy(sCep,6,3);
    end
  else
    Result := copy(AValue,1,5) + '-' + copy(AValue,6,3);
end;

{Fun��o original de ACBrNFeUtil modificada para exibir em outro formato}
function TfrlDANFeRL.FormatarFone(AValue: String): String;
begin
  Result := AValue;
  if DFeUtil.NaoEstaVazio(AValue) then
  begin
    if Length(DFeUtil.LimpaNumero(AValue)) > 10 then AValue := copy(DFeUtil.LimpaNumero(AValue),2,10); //Casos em que o DDD vem com ZERO antes somando 3 digitos

    AValue := DFeUtil.Poem_Zeros(DFeUtil.LimpaNumero(AValue), 10);
    Result := '('+copy(AValue,1,2) + ') ' + copy(AValue,3,4) + '-' + copy(AValue,7,4);
  end;
end;

procedure TfrlDANFeRL.InsereLinhas(sTexto: String; iLimCaracteres: Integer;
                                                                 rMemo: TRLMemo);
var iTotalLinhas, iUltimoEspacoLinha, iPosAtual, iQuantCaracteres, i: Integer;
    sLinhaProvisoria, sLinha: String;
begin
  iPosAtual := 1;
  iQuantCaracteres := Length(sTexto);
  if iQuantCaracteres <= iLimiteLinhas then
    iTotalLinhas := 1
  else
    begin
      if (iQuantCaracteres mod iLimCaracteres) > 0 then
        iTotalLinhas := (iQuantCaracteres div iLimCaracteres) + 1
      else
        iTotalLinhas := iQuantCaracteres div iLimCaracteres;
    end;

  for i := 1 to (iTotalLinhas + 10) do
    begin
      sLinhaProvisoria := Copy(sTexto, iPosAtual, iLimCaracteres);
      iUltimoEspacoLinha := BuscaDireita(' ', sLinhaProvisoria);

      if iUltimoEspacoLinha = 0 then
        iUltimoEspacoLinha := iQuantCaracteres;

      if Pos(';', sLinhaProvisoria) = 0 then
        begin
          if (BuscaDireita(' ', sLinhaProvisoria) = iLimCaracteres)  or
             (BuscaDireita(' ', sLinhaProvisoria) = (iLimCaracteres + 1)) then
            sLinha := sLinhaProvisoria
          else
            begin
              if (iQuantCaracteres - iPosAtual) > iLimCaracteres then
                sLinha := Copy(sLinhaProvisoria, 1, iUltimoEspacoLinha)
              else
                begin
                  sLinha := sLinhaProvisoria;
                end;
            end;
          iPosAtual := iPosAtual + Length(sLinha);
        end // if Pos(';', sLinhaProvisoria) = 0
      else
        begin
          sLinha := Copy(sLinhaProvisoria, 1, Pos(';', sLinhaProvisoria));
          iPosAtual := iPosAtual + (Length(sLinha));
        end;

      if sLinha > '' then
        begin
          if LeftStr(sLinha, 1) = ' ' then
            sLinha := Copy(sLinha, 2, (Length(sLinha) - 1))
          else
            sLinha := sLinha;

          rMemo.Lines.Add(sLinha);
        end;

    end;
end;

// Descomentar este comando quando aparecer a mensagem do Fortes sobre
// vers�o diferente

{initialization
RLConsts.SetVersion(3,71,'B');}

end.
