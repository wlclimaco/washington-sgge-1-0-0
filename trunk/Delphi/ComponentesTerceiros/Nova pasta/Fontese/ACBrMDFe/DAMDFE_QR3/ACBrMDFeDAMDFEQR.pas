{******************************************************************************}
{ Projeto: Componente ACBrMDFe                                                 }
{  Biblioteca multiplataforma de componentes Delphi                            }
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
|* 01/08/2012: Italo Jurisato Junior
|*  - Doa��o do componente para o Projeto ACBr
******************************************************************************}

{$I ACBr.inc}

unit ACBrMDFeDAMDFEQR;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, ExtCtrls, QuickRpt, QRCtrls,
  {$IFDEF QReport_PDF}
     QRPDFFilt, QRPrntr,
  {$ENDIF}
  ACBrMDFeDAMDFEQRCodeBar, pmdfeMDFe, ACBrMDFe, ACBrMDFeUtil, Printers;

type
  TfqrDAMDFEQR = class(TForm)
    QRMDFe: TQuickRep;
  private
    { Private declarations }
  protected
    //BarCode : TBarCode128c;
    FACBrMDFe           : TACBrMDFe;
    FMDFe               : TMDFe;
    FLogo               : String;
    FEmail              : String;
    FFax                : String;
    FNumCopias          : Integer;
    FSistema            : String;
    FSite               : String;
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

    procedure qrlSemValorFiscalPrint(sender: TObject; var Value: String);
    procedure SetBarCodeImage ( ACode : String; QRImage : TQRImage );
  public
    { Public declarations }
    class procedure Imprimir(AMDFe              : TMDFe;
                             ALogo              : String  = '';
                             AEmail             : String  = '';
                             AExpandirLogoMarca : Boolean = False;
                             AFax               : String  = '';
                             ANumCopias         : Integer = 1;
                             ASistema           : String  = '';
                             ASite              : String  = '';
                             AUsuario           : String  = '';
                             APreview           : Boolean = True;
                             AMargemSuperior    : Double  = 0.8;
                             AMargemInferior    : Double  = 0.8;
                             AMargemEsquerda    : Double  = 0.6;
                             AMargemDireita     : Double  = 0.51;
                             AImpressora        : String  = '');

    class procedure SavePDF(AFile              : String;
                            AMDFe              : TMDFe;
                            ALogo              : String  = '';
                            AEmail             : String  = '';
                            AExpandirLogoMarca : Boolean = False;
                            AFax               : String  = '';
                            ANumCopias         : Integer = 1;
                            ASistema           : String  = '';
                            ASite              : String  = '';
                            AUsuario           : String  = '';
                            AMargemSuperior    : Double  = 0.8;
                            AMargemInferior    : Double  = 0.8;
                            AMargemEsquerda    : Double  = 0.6;
                            AMargemDireita     : Double  = 0.51);

  end;

implementation

uses
 MaskUtils;

var
 Printer: TPrinter;

{$R *.dfm}

class procedure TfqrDAMDFEQR.Imprimir(AMDFe              : TMDFe;
                                      ALogo              : String  = '';
                                      AEmail             : String  = '';
                                      AExpandirLogoMarca : Boolean = False;
                                      AFax               : String  = '';
                                      ANumCopias         : Integer = 1;
                                      ASistema           : String  = '';
                                      ASite              : String  = '';
                                      AUsuario           : String  = '';
                                      APreview           : Boolean = True;
                                      AMargemSuperior    : Double  = 0.8;
                                      AMargemInferior    : Double  = 0.8;
                                      AMargemEsquerda    : Double  = 0.6;
                                      AMargemDireita     : Double  = 0.51;
                                      AImpressora        : String  = '');
begin
  with Create ( nil ) do
     try
        FMDFe              := AMDFe;
        FLogo              := ALogo;
        FEmail             := AEmail;
        FExpandirLogoMarca := AExpandirLogoMarca;
        FFax               := AFax;
        FNumCopias         := ANumCopias;
        FSistema           := ASistema;
        FSite              := ASite;
        FUsuario           := AUsuario;
        FMargemSuperior    := AMargemSuperior;
        FMargemInferior    := AMargemInferior;
        FMargemEsquerda    := AMargemEsquerda;
        FMargemDireita     := AMargemDireita;
        FImpressora        := AImpressora;

        Printer := TPrinter.Create;

        if FImpressora > '' then
          QRMDFe.PrinterSettings.PrinterIndex := Printer.Printers.IndexOf(FImpressora);

        if APreview
         then begin
           QRMDFe.PrinterSettings.Copies := FNumCopias;

         {$IFDEF QReport_PDF}
           QRMDFe.PrevShowSearch      := False;
           QRMDFe.PrevShowThumbs      := False;
           QRMDFe.PreviewInitialState := wsMaximized;
           QRMDFe.PrevInitialZoom     := qrZoomToWidth;

           QRExportFilterLibrary.AddFilter(TQRPDFDocumentFilter);
         {$ENDIF}

           QRMDFe.Prepare;
           QRMDFe.Preview;
         end
         else begin
           AfterPreview := True;
           QRMDFe.PrinterSettings.Copies := FNumCopias;
           QRMDFe.Prepare;
           QRMDFe.Print;
         end;

     finally
        Free;
     end;
end;

class procedure TfqrDAMDFEQR.SavePDF(AFile              : String;
                                     AMDFe              : TMDFe;
                                     ALogo              : String  = '';
                                     AEmail             : String  = '';
                                     AExpandirLogoMarca : Boolean = False;
                                     AFax               : String  = '';
                                     ANumCopias         : Integer = 1;
                                     ASistema           : String  = '';
                                     ASite              : String  = '';
                                     AUsuario           : String  = '';
                                     AMargemSuperior    : Double  = 0.8;
                                     AMargemInferior    : Double  = 0.8;
                                     AMargemEsquerda    : Double  = 0.6;
                                     AMargemDireita     : Double  = 0.51);
{$IFDEF QReport_PDF}
var
  qf: TQRPDFDocumentFilter;
  i: integer;
{$ENDIF}
begin
{$IFDEF QReport_PDF}
  with Create ( nil ) do
     try
        FMDFe              := AMDFe;
        FLogo              := ALogo;
        FEmail             := AEmail;
        FExpandirLogoMarca := AExpandirLogoMarca;
        FFax               := AFax;
        FNumCopias         := ANumCopias;
        FSistema           := ASistema;
        FSite              := ASite;
        FUsuario           := AUsuario;
        FMargemSuperior    := AMargemSuperior;
        FMargemInferior    := AMargemInferior;
        FMargemEsquerda    := AMargemEsquerda;
        FMargemDireita     := AMargemDireita;

        for i := 0 to ComponentCount -1 do
          begin
            if (Components[i] is TQRShape) and (TQRShape(Components[i]).Shape = qrsRoundRect) then
              begin
                TQRShape(Components[i]).Shape := qrsRectangle;
                TQRShape(Components[i]).Pen.Width := 1;
              end;
          end;
        AfterPreview := True;
        QRMDFe.Prepare;
        qf := TQRPDFDocumentFilter.Create(AFile);
        qf.CompressionOn := False;
        QRMDFe.QRPrinter.ExportToFilter( qf );
        qf.Free;
     finally
        Free;
     end;
{$ENDIF}
end;

procedure TfqrDAMDFEQR.qrlSemValorFiscalPrint(sender: TObject; var Value: String);
begin
  inherited;
  if FSemValorFiscal then
     Value := '';
end;

procedure TfqrDAMDFEQR.SetBarCodeImage(ACode: String; QRImage: TQRImage);
var
 b: TBarCode128c;
begin
   b := TBarCode128c.Create;
//      Width  := QRImage.Width;
   b.Code := ACode;
   b.PaintCodeToCanvas( ACode, QRImage.Canvas, QRImage.ClientRect );
   b.free;
end;

end.
