{******************************************************************************}
{ Projeto: Componente ACBrCTe                                                  }
{  Biblioteca multiplataforma de componentes Delphi para emiss�o de Conhecimen-}
{ to de Transporte eletr�nico - CTe - http://www.cte.fazenda.gov.br            }
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
|*  - Doa��o units para gera��o do DACTe via QuickReport
|* 06/04/2010: Italo Jurisato Junior
|*  - Acr�scimo dos par�metros "FEmail", "FResumoCanhoto", "FFax", "FNumCopias",
|*    "FSistema", "FUsuario", "FImprimeHoraSaida", "FHoraSaida",
|*    "FMargemSuperior", "FMargemInferior", "FMargemEsquerda", "FMargemDireita",
|*    nas Class procedures "Imprimir" e "SavePDF"
|*  - Habilitada a funcionalidade da procedure "SavePDF";
|* 15/02/2012: Italo Jurisato Junior
******************************************************************************}

{$I ACBr.inc}

unit ACBrCTeDACTeQR;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, ExtCtrls, QuickRpt, QRCtrls,
  {$IFDEF QReport_PDF}
     QRPDFFilt, QRPrntr,
  {$ENDIF}
  ACBrCTeQRCodeBar, pcteCTe, ACBrCTe, ACBrCTeUtil, Printers, pcnConversao;

type

  TfrmDACTeQR = class(TForm)
    QRCTe: TQuickRep;
    procedure FormDestroy(Sender: TObject);
  private

  protected
    //BarCode : TBarCode128c;
    FACBrCTe            : TACBrCTe;
    FCTe                : TCTe;
    FLogo               : String;
    FEmail              : String;
    FImprimeHoraSaida   : Boolean;
    FHoraSaida          : String;
    FResumoCanhoto      : Boolean;
    FFax                : String;
    FNumCopias          : Integer;
    FSistema            : String;
    FUrl                : String;
    FUsuario            : String;
    AfterPreview        : Boolean;
    FExpandirLogoMarca  : Boolean;
    ChangedPos          : Boolean;
    FSemValorFiscal     : Boolean;
    FMargemSuperior     : double;
    FMargemInferior     : double;
    FMargemEsquerda     : double;
    FMargemDireita      : double;
    FImpressora         : String;
    FPosRecibo          : TPosRecibo;
    FCTeCancelada       : Boolean; //Incluido por Italo em  12/04/2013

    procedure qrlSemValorFiscalPrint(sender: TObject; var Value: string);
    procedure SetBarCodeImage(ACode: string; QRImage: TQRImage);
  public
    class procedure Imprimir(ACTe                : TCTe;
                             ALogo               : String    = '';
                             AEmail              : String    = '';
                             AImprimeHoraSaida   : Boolean   = False;
                             AExpandirLogoMarca  : Boolean   = False;
                             AHoraSaida          : String    = '';
                             AResumoCanhoto      : Boolean   = False;
                             AFax                : String    = '';
                             ANumCopias          : Integer   = 1;
                             ASistema            : String    = '';
                             AUrl                : String    = '';
                             AUsuario            : String    = '';
                             APreview            : Boolean   = True;
                             AMargemSuperior     : Double    = 0.8;
                             AMargemInferior     : Double    = 0.8;
                             AMargemEsquerda     : Double    = 0.6;
                             AMargemDireita      : Double    = 0.51;
                             AImpressora         : String    = '';
                             APosRecibo          : TPosRecibo = prCabecalho;
                             ACTeCancelada       : Boolean   = False);

    class procedure SavePDF(AFile: String;
                            ACTe                : TCTe;
                            ALogo               : String    = '';
                            AEmail              : String    = '';
                            AImprimeHoraSaida   : Boolean   = False;
                            AExpandirLogoMarca  : Boolean   = False;
                            AHoraSaida          : String    = '';
                            AResumoCanhoto      : Boolean   = False;
                            AFax                : String    = '';
                            ANumCopias          : Integer   = 1;
                            ASistema            : String    = '';
                            AUrl                : String    = '';
                            AUsuario            : String    = '';
                            AMargemSuperior     : Double    = 0.8;
                            AMargemInferior     : Double    = 0.8;
                            AMargemEsquerda     : Double    = 0.6;
                            AMargemDireita      : Double    = 0.51;
                            APosRecibo          : TPosRecibo = prCabecalho;
                            ACTeCancelada       : Boolean   = False);

  end;

implementation

uses MaskUtils;

var
  Printer: TPrinter;

{$R *.dfm}

class procedure TfrmDACTeQR.Imprimir(ACTe               : TCTe;
                                    ALogo               : String    = '';
                                    AEmail              : String    = '';
                                    AImprimeHoraSaida   : Boolean   = False;
                                    AExpandirLogoMarca  : Boolean   = False;
                                    AHoraSaida          : String    = '';
                                    AResumoCanhoto      : Boolean   = False;
                                    AFax                : String    = '';
                                    ANumCopias          : Integer   = 1;
                                    ASistema            : String    = '';
                                    AUrl                : String    = '';
                                    AUsuario            : String    = '';
                                    APreview            : Boolean   = True;
                                    AMargemSuperior     : Double    = 0.8;
                                    AMargemInferior     : Double    = 0.8;
                                    AMargemEsquerda     : Double    = 0.6;
                                    AMargemDireita      : Double    = 0.51;
                                    AImpressora         : String    = '';
                                    APosRecibo          : TPosRecibo = prCabecalho;
                                    ACTeCancelada       : Boolean   = False);
begin
  with Create ( nil ) do
     try
        FCTe                := ACTe;
        FLogo               := ALogo;
        FEmail              := AEmail;
        FImprimeHoraSaida   := AImprimeHoraSaida;
        FExpandirLogoMarca  := AExpandirLogoMarca;
        FHoraSaida          := AHoraSaida;
        FResumoCanhoto      := AResumoCanhoto;
        FFax                := AFax;
        FNumCopias          := ANumCopias;
        FSistema            := ASistema;
        FUrl                := AUrl;
        FUsuario            := AUsuario;
        FMargemSuperior     := AMargemSuperior;
        FMargemInferior     := AMargemInferior;
        FMargemEsquerda     := AMargemEsquerda;
        FMargemDireita      := AMargemDireita;
        FImpressora         := AImpressora;
        FPosRecibo          := APosRecibo;
        FCTeCancelada       := ACTeCancelada;

        Printer := TPrinter.Create;

        if FImpressora > '' then
          QRCTe.PrinterSettings.PrinterIndex := Printer.Printers.IndexOf(FImpressora);

        if APreview then
         begin
           QRCTe.PrinterSettings.Copies := FNumCopias;

         {$IFDEF QReport_PDF}
           QRCTe.PrevShowSearch      := False;
           QRCTe.PrevShowThumbs      := False;
           QRCTe.PreviewInitialState := wsMaximized;
           QRCTe.PrevInitialZoom     := qrZoomToWidth;

           // Incluido por Italo em 14/02/2012
           // QRCTe.PreviewDefaultSaveType := stPDF;
           // Incluido por Italo em 16/02/2012
           QRExportFilterLibrary.AddFilter(TQRPDFDocumentFilter);
         {$ENDIF}

           QRCTe.Prepare;
           QRCTe.Preview;
           // Incluido por Italo em 11/04/2013
           // Segundo o Rodrigo Chiva resolveu o problema de travamento
           // ap�s o fechamento do Preview
           Application.ProcessMessages;
         end else
         begin
           AfterPreview := True;
           QRCTe.PrinterSettings.Copies := FNumCopias;
           QRCTe.Prepare;
           QRCTe.Print;
         end;
     finally
        // Incluido por Rodrigo Fernandes em 11/03/2013
        // Liberando o objeto Printer da memoria
        Printer.Free;
        Free;
     end;
end;

class procedure TfrmDACTeQR.SavePDF(AFile               : String;
                                    ACTe                : TCTe;
                                    ALogo               : String    = '';
                                    AEmail              : String    = '';
                                    AImprimeHoraSaida   : Boolean   = False;
                                    AExpandirLogoMarca  : Boolean   = False;
                                    AHoraSaida          : String    = '';
                                    AResumoCanhoto      : Boolean   = False;
                                    AFax                : String    = '';
                                    ANumCopias          : Integer   = 1;
                                    ASistema            : String    = '';
                                    AUrl                : String    = '';
                                    AUsuario            : String    = '';
                                    AMargemSuperior     : Double    = 0.8;
                                    AMargemInferior     : Double    = 0.8;
                                    AMargemEsquerda     : Double    = 0.6;
                                    AMargemDireita      : Double    = 0.51;
                                    APosRecibo          : TPosRecibo = prCabecalho;
                                    ACTeCancelada       : Boolean   = False);
{$IFDEF QReport_PDF}
 var
  qf : TQRPDFDocumentFilter;
  i  : Integer;
{$ENDIF}
begin
{$IFDEF QReport_PDF}
  with Create ( nil ) do
     try
        FCTe                := ACTe;
        FLogo               := ALogo;
        FEmail              := AEmail;
        FImprimeHoraSaida   := AImprimeHoraSaida;
        FHoraSaida          := AHoraSaida;
        FResumoCanhoto      := AResumoCanhoto;
        FFax                := AFax;
        FNumCopias          := ANumCopias;
        FSistema            := ASistema;
        FUrl                := AUrl;
        FUsuario            := AUsuario;
        FMargemSuperior     := AMargemSuperior;
        FMargemInferior     := AMargemInferior;
        FMargemEsquerda     := AMargemEsquerda;
        FMargemDireita      := AMargemDireita;
        FExpandirLogoMarca  := AExpandirLogoMarca;
        FPosRecibo          := APosRecibo;
        FCTeCancelada       := ACTeCancelada;

        for i := 0 to ComponentCount -1 do
          begin
            if (Components[i] is TQRShape) and (TQRShape(Components[i]).Shape = qrsRoundRect) then
              begin
                TQRShape(Components[i]).Shape := qrsRectangle;
                TQRShape(Components[i]).Pen.Width := 1;
              end;
          end;
        AfterPreview := True;
        QRCTe.Prepare;
        qf := TQRPDFDocumentFilter.Create(AFile);
        qf.CompressionOn := False;
        QRCTe.QRPrinter.ExportToFilter( qf );
        qf.Free;
     finally
        Free;
     end;
{$ENDIF}
end;

procedure TfrmDACTeQR.qrlSemValorFiscalPrint(sender: TObject;  var Value: string);
begin
  inherited;

  if FSemValorFiscal then
    Value := '';
end;

procedure TfrmDACTeQR.SetBarCodeImage(ACode: string; QRImage: TQRImage);
var
  b : TBarCode128c;
begin
  b := TBarCode128c.Create;
  //      Width  := QRImage.Width;
  b.Code := ACode;
  b.PaintCodeToCanvas(ACode, QRImage.Canvas, QRImage.ClientRect);
  b.free;
end;

// Incluido por Rodrigo Fernandes em 11/03/2013
procedure TfrmDACTeQR.FormDestroy(Sender: TObject);
begin
  QRCTe.QRPrinter.Free;
  QRCTe.Free;
end;

end.

