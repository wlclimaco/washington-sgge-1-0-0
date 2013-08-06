{******************************************************************************}
{ Projeto: Componente ACBrCTe                                                  }
{ Biblioteca multiplataforma de componentes Delphi para emiss�o de Conhecimento}
{ Transporte eletr�nica - CTe - http://www.cte.fazenda.gov.br                  }
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
|* 30/03/2011: Jeickson Gobeti
|*  - Inicio do desenvolvimento Dacte FastReport
******************************************************************************}
{$I ACBr.inc}

unit ACBrCTeDACTEFR;

interface

uses
  Forms, SysUtils, Classes, Graphics, ACBrCTeDACTEClass, ACBrCTeDACTEFRDM,
  pcteCTe, pcnConversao, frxClass;

type
  EACBrCTeDACTEFR = class(Exception);

  TACBrCTeDACTEFR = class( TACBrCTeDACTEClass )
   private
    FdmDacte: TdmACBrCTeFR;
    FFastFile: String;
    FEspessuraBorda: Integer;
    function GetPreparedReport: TfrxReport;
    function PrepareReport(CTE: TCTe = nil): Boolean;
   public
    constructor Create(AOwner: TComponent); override;
    destructor Destroy; override;
    procedure ImprimirDACTE(CTE: TCTe = nil); override;
    procedure ImprimirDACTEPDF(CTE: TCTe = nil); override;
  published
    property FastFile: String read FFastFile write FFastFile;
    property dmDacte: TdmACBrCTeFR read FdmDacte write FdmDacte;
    property EspessuraBorda: Integer read FEspessuraBorda write FEspessuraBorda;
    property PreparedReport: TfrxReport read GetPreparedReport;
  end;

implementation

uses ACBrCTe, ACBrCTeUtil, ACBrUtil, StrUtils, Dialogs;

constructor TACBrCTeDACTEFR.Create(AOwner: TComponent);
begin
  inherited create( AOwner );
  FdmDacte := TdmACBrCTeFR.Create(Self);
  FFastFile := '' ;
  FEspessuraBorda := 1;
end;

destructor TACBrCTeDACTEFR.Destroy;
begin
  dmDacte.Free;
  inherited Destroy;
end;

function TACBrCTeDACTEFR.GetPreparedReport: TfrxReport;
begin
  if Trim(FFastFile) = '' then
    Result := nil
  else
  begin
    if PrepareReport(nil) then
      Result := dmDacte.frxReport
    else
      Result := nil;
  end;
end;

procedure TACBrCTeDACTEFR.ImprimirDACTE(CTE: TCTe);
begin
  if PrepareReport(CTE) then
  begin
    if MostrarPreview then
      dmDacte.frxReport.ShowPreparedReport
    else
      dmDacte.frxReport.Print;
  end;
end;

procedure TACBrCTeDACTEFR.ImprimirDACTEPDF(CTE: TCTe);
const
  TITULO_PDF = 'Conhecimento de Transporte Eletr�nico';
var
  I: Integer;
begin
  if PrepareReport(CTE) then
  begin
    dmDacte.frxPDFExport.Author     := Sistema;
    dmDacte.frxPDFExport.Creator    := Sistema;
    dmDacte.frxPDFExport.Producer   := Sistema;
    dmDacte.frxPDFExport.Title      := TITULO_PDF;
    dmDacte.frxPDFExport.Subject    := TITULO_PDF;
    dmDacte.frxPDFExport.Keywords   := TITULO_PDF;
    dmDacte.frxPDFExport.ShowDialog := False;

    for I := 0 to TACBrCTe(ACBrCTe).Conhecimentos.Count - 1 do
    begin
      dmDacte.frxPDFExport.FileName := IncludeTrailingPathDelimiter(PathPDF) + dmDacte.CTe.procCTe.chCTe + '.pdf';
      dmDacte.frxReport.Export(dmDacte.frxPDFExport);
    end;
  end;
end;

function TACBrCTeDACTEFR.PrepareReport(CTE: TCTe): Boolean;
var
  i: Integer;
begin
  Result := False;

  if Trim(FastFile) <> '' then
  begin
    if FileExists(FastFile) then
      dmDacte.frxReport.LoadFromFile(FastFile)
    else
      raise EACBrCTeDACTEFR.CreateFmt('Caminho do arquivo de impress�o do DACTE "%s" inv�lido.', [FastFile]);
  end
  else
    raise EACBrCTeDACTEFR.Create('Caminho do arquivo de impress�o do DACTE n�o assinalado.');

  if Assigned(CTE) then
  begin
    dmDacte.CTe := CTE;
    dmDacte.CarregaDados;

    Result := dmDacte.frxReport.PrepareReport;
  end
  else
  begin
    if Assigned(ACBrCTe) then
    begin
      for i := 0 to TACBrCTe(ACBrCTe).Conhecimentos.Count - 1 do
      begin
        dmDacte.CTe := TACBrCTe(ACBrCTe).Conhecimentos.Items[i].CTe;
        dmDacte.CarregaDados;

        if (i > 0) then
          Result := dmDacte.frxReport.PrepareReport(False)
        else
          Result := dmDacte.frxReport.PrepareReport;
      end;
    end
    else
      raise EACBrCTeDACTEFR.Create('Propriedade ACBrCTe n�o assinalada.');
  end;
end;

end.
