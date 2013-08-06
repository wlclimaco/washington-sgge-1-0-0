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
|*  - Acerto da exporta��o para PDF
|*  - Acerto para checar se o relat�rio foi realmente preparado
|     antes de continuar a imprir ou gerar o PDF
|*  - Acerto nas propriedades do arquivo PDF
|* 26/08/2010: R�gys Silveira / Itamar Bermond
|*  - Acerto na propriedade "PreparedReport"
******************************************************************************}
{$I ACBr.inc}

unit ACBrNFeDANFEFR;

interface

uses
  Forms, SysUtils, Classes, Graphics, ACBrNFeDANFEClass, ACBrNFeDANFEFRDM,
  pcnNFe, pcnConversao, frxClass;

type
  EACBrNFeDANFEFR = class(Exception);

  TACBrNFeDANFEFR = class( TACBrNFeDANFEClass )
   private
    FdmDanfe: TdmACBrNFeFR;
    FFastFile: String;
    FEspessuraBorda: Integer;
    FFastFileEvento: String;
    FShowDialog: Boolean;
    function GetPreparedReport: TfrxReport;
    function GetPreparedReportEvento: TfrxReport;
    function PrepareReport(NFE: TNFe = nil): Boolean;
    function PrepareReportEvento: Boolean;
   public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure ImprimirDANFE(NFE: TNFe = nil); override;
    procedure ImprimirDANFEPDF(NFE: TNFe = nil); override;
    procedure ImprimirEVENTO(NFE: TNFe = nil); override;
    procedure ImprimirEVENTOPDF(NFE: TNFe = nil); override;
  published
    property FastFile: String read FFastFile write FFastFile;
    property FastFileEvento: String read FFastFileEvento write FFastFileEvento;
    property dmDanfe: TdmACBrNFeFR read FdmDanfe write FdmDanfe;
    property EspessuraBorda: Integer read FEspessuraBorda write FEspessuraBorda;
    property PreparedReport: TfrxReport read GetPreparedReport;
    property PreparedReportEvento: TfrxReport read GetPreparedReportEvento;
    property ShowDialog: Boolean read FShowDialog write FShowDialog default false; // Isaque Pinheiro
  end;

implementation

uses ACBrNFe, ACBrNFeUtil, ACBrUtil, StrUtils, Dialogs;

constructor TACBrNFeDANFEFR.Create(AOwner: TComponent);
begin
  inherited create( AOwner );
  FdmDanfe := TdmACBrNFeFR.Create(Self);
  FFastFile := '' ;
  FEspessuraBorda := 1;
end;

destructor TACBrNFeDANFEFR.Destroy;
begin
  dmDanfe.Free;
  inherited Destroy;
end;

function TACBrNFeDANFEFR.GetPreparedReport: TfrxReport;
begin
  if Trim(FFastFile) = '' then
    Result := nil
  else
  begin
    if PrepareReport(nil) then
      Result := dmDanfe.frxReport
    else
      Result := nil;
  end;
end;

function TACBrNFeDANFEFR.GetPreparedReportEvento: TfrxReport;
begin
  if Trim(FFastFileEvento) = '' then
    Result := nil
  else
  begin
    if PrepareReportEvento then
      Result := dmDanfe.frxReport
    else
      Result := nil;
  end;
end;

function TACBrNFeDANFEFR.PrepareReport(NFE: TNFe): Boolean;
var
  I: Integer;
begin
  Result := False;

  if Trim(FastFile) <> '' then
  begin
    if FileExists(FastFile) then
      dmDanfe.frxReport.LoadFromFile(FastFile)
    else
      raise EACBrNFeDANFEFR.CreateFmt('Caminho do arquivo de impress�o do DANFE "%s" inv�lido.', [FastFile]);
  end
  else
    raise EACBrNFeDANFEFR.Create('Caminho do arquivo de impress�o do DANFE n�o assinalado.');

  dmDanfe.frxReport.PrintOptions.Copies := FNumCopias;
  dmDanfe.frxReport.PrintOptions.ShowDialog := FShowDialog;
  dmDanfe.frxReport.ShowProgress := FMostrarStatus;

  // Inclu�do por Luciano Enzweiler em 23/01/2013
  // Define a impressora
  if Length(Impressora) > 0 then
    dmDanfe.frxReport.PrintOptions.Printer := FImpressora;

  // preparar relatorio
  if Assigned(NFE) then
  begin
    dmDanfe.NFe := NFE;
    dmDanfe.CarregaDadosNFe;

    Result := dmDanfe.frxReport.PrepareReport;
  end
  else
  begin
    if Assigned(ACBrNFe) then
    begin
      for i := 0 to TACBrNFe(ACBrNFe).NotasFiscais.Count - 1 do
      begin
        dmDanfe.NFe := TACBrNFe(ACBrNFe).NotasFiscais.Items[i].NFe;
        dmDanfe.CarregaDadosNFe;

        if (i > 0) then
          Result := dmDanfe.frxReport.PrepareReport(False)
        else
          Result := dmDanfe.frxReport.PrepareReport;
      end;
    end
    else
      raise EACBrNFeDANFEFR.Create('Propriedade ACBrNFe n�o assinalada.');
  end;
end;

function TACBrNFeDANFEFR.PrepareReportEvento: Boolean;
begin
  if Trim(FastFileEvento) <> '' then
  begin
    if FileExists(FastFileEvento) then
      dmDanfe.frxReport.LoadFromFile(FastFileEvento)
    else
      raise EACBrNFeDANFEFR.CreateFmt('Caminho do arquivo de impress�o do EVENTO "%s" inv�lido.', [FastFileEvento]);
  end
  else
    raise EACBrNFeDANFEFR.Create('Caminho do arquivo de impress�o do EVENTO n�o assinalado.');

  dmDanfe.frxReport.PrintOptions.Copies := NumCopias;

  // preparar relatorio
  if Assigned(ACBrNFe) then
  begin
    if assigned(TACBrNFe(ACBrNFe).EventoNFe) then
    begin
      dmDanfe.Evento := TACBrNFe(ACBrNFe).EventoNFe;
      dmDanfe.CarregaDadosEventos;
    end
    else
      raise EACBrNFeDANFEFR.Create('Evento n�o foi assinalado.');

    if TACBrNFe(ACBrNFe).NotasFiscais.Count > 0 then
    begin
      dmDanfe.frxReport.Variables['PossuiNFe'] := QuotedStr('S');
      dmDanfe.NFe := TACBrNFe(ACBrNFe).NotasFiscais.Items[0].NFe;
      dmDanfe.CarregaDadosNFe;
    end;

    Result := dmDanfe.frxReport.PrepareReport;
  end
  else
    raise EACBrNFeDANFEFR.Create('Propriedade ACBrNFe n�o assinalada.');
end;

procedure TACBrNFeDANFEFR.ImprimirDANFE(NFE: TNFe);
begin
  if PrepareReport(NFE) then
  begin
    if MostrarPreview then
      dmDanfe.frxReport.ShowPreparedReport
    else
      dmDanfe.frxReport.Print;
  end;
end;

procedure TACBrNFeDANFEFR.ImprimirDANFEPDF(NFE: TNFe);
const
  TITULO_PDF = 'Nota Fiscal Eletr�nica';
var
  I: Integer;
begin
  if PrepareReport(NFE) then
  begin
    dmDanfe.frxPDFExport.Author     := Sistema;
    dmDanfe.frxPDFExport.Creator    := Sistema;
    dmDanfe.frxPDFExport.Producer   := Sistema;
    dmDanfe.frxPDFExport.Title      := TITULO_PDF;
    dmDanfe.frxPDFExport.Subject    := TITULO_PDF;
    dmDanfe.frxPDFExport.Keywords   := TITULO_PDF;
    dmDanfe.frxPDFExport.ShowDialog := False;

    for I := 0 to TACBrNFe(ACBrNFe).NotasFiscais.Count - 1 do
    begin
      dmDanfe.frxPDFExport.FileName := PathPDF + dmDanfe.NFe.procNFe.chNFe + '.pdf';
      dmDanfe.frxReport.Export(dmDanfe.frxPDFExport);
    end;
  end;
end;

procedure TACBrNFeDANFEFR.ImprimirEVENTO(NFE: TNFe);
begin
  if PrepareReportEvento then
  begin
    if MostrarPreview then
      dmDanfe.frxReport.ShowPreparedReport
    else
      dmDanfe.frxReport.Print;
  end;
end;

procedure TACBrNFeDANFEFR.ImprimirEVENTOPDF(NFE: TNFe);
const
  TITULO_PDF = 'Eventos Nota Fiscal Eletr�nica';
var
  NomeArq: String;
begin
  if PrepareReportEvento then
  begin
    dmDanfe.frxPDFExport.Author     := Sistema;
    dmDanfe.frxPDFExport.Creator    := Sistema;
    dmDanfe.frxPDFExport.Producer   := Sistema;
    dmDanfe.frxPDFExport.Title      := TITULO_PDF;
    dmDanfe.frxPDFExport.Subject    := TITULO_PDF;
    dmDanfe.frxPDFExport.Keywords   := TITULO_PDF;
    dmDanfe.frxPDFExport.ShowDialog := False;

    {
    NomeArq := TACBrNFe(ACBrNFe).EventoNFe.Evento[0].InfEvento.chNFe;
    NomeArq := NomeArq + '-' + TACBrNFe(ACBrNFe).EventoNFe.Evento[0].InfEvento.TipoEvento;
    NomeArq := NomeArq + '-' + IntToStr(TACBrNFe(ACBrNFe).EventoNFe.Evento[0].InfEvento.nSeqEvento);
    }
    {
    NomeArq := TACBrNFe(ACBrNFe).EventoNFe.Evento[0].InfEvento.TipoEvento;
    NomeArq := NomeArq + TACBrNFe(ACBrNFe).EventoNFe.Evento[0].InfEvento.chNFe;
    }

    NomeArq := Copy(TACBrNFe(ACBrNFe).EventoNFe.Evento.Items[0].InfEvento.id, 3, 52);
    
    dmDanfe.frxPDFExport.FileName := PathWithDelim(Self.PathPDF) + NomeArq + 'evento.pdf';
    dmDanfe.frxReport.Export(dmDanfe.frxPDFExport);
  end;
end;

end.
