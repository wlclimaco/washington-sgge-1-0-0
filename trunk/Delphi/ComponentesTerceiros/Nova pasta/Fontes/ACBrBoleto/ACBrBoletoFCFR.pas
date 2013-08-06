{******************************************************************************}
{ Projeto: Componentes ACBr                                                 }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2009 http://acbr.sf.net                     }


{                                                                              }
{ Colaboradores nesse arquivo:  Isaque Pinheiro                                               }
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
|* 31/07/2011: Isaque Pinheiro
|*  - Inicio do desenvolvimento
|*
******************************************************************************}
{$I ACBr.inc}

unit ACBrBoletoFCFR;

interface

uses
  SysUtils, Classes, DB, DBClient, ACBrBase, ACBrBoleto, StrUtils,
  frxClass, frxDBSet, frxBarcode, frxExportHTML, frxExportPDF;

const
  CACBrBoletoFCFR_Versao = '0.0.12a';

type
  EACBrBoletoFCFR = class(Exception);

  TdmACBrBoletoFCFR = class;

  { TACBrBoletoFCFR }
  TACBrBoletoFCFR = class(TACBrBoletoFCClass)
  private
    { Private declarations }
    fFastReportFile: String;
    function PrepareBoletos(const DmBoleto: TdmACBrBoletoFCFR): Boolean;
    function PrepareReport(const DmBoleto: TdmACBrBoletoFCFR): Boolean;
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;

    procedure Imprimir; override;
    function PreparedReport: TfrxReport;
  published
    property FastReportFile: String read FFastReportFile write FFastReportFile;
  end;

  { TdmACbrBoletoFCFR }
  TdmACBrBoletoFCFR = class(TDataModule)
    frxPDFExport: TfrxPDFExport;
    cdsTitulo: TClientDataSet;
    frxTitulo: TfrxDBDataset;
    frxBarCodeObject: TfrxBarCodeObject;
    frxReport: TfrxReport;
    frxHTMLExport: TfrxHTMLExport;
    cdsCedente: TClientDataSet;
    frxCedente: TfrxDBDataset;
    cdsBanco: TClientDataSet;
    frxBanco: TfrxDBDataset;
    procedure DataModuleCreate(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

implementation

{$R *.dfm}

uses ACBrUtil, ACBrBanestes;

{ TdmACBrBoletoFCFR }

procedure TdmACBrBoletoFCFR.DataModuleCreate(Sender: TObject);
begin
   // Banco
   with cdsBanco do
   begin
     Close;
     FieldDefs.Clear;
     FieldDefs.Add('Numero', ftString, 20);
     FieldDefs.Add('Digito', ftString, 1);
     FieldDefs.Add('Nome', ftString, 100);
     FieldDefs.Add('DirLogo', ftString, 254);
     CreateDataSet;
   end;
   // Cedente
   with cdsCedente do
   begin
     Close;
     FieldDefs.Clear;
     FieldDefs.Add('Nome', ftString, 100);
     FieldDefs.Add('CodigoCedente', ftString, 20);
     FieldDefs.Add('CodigoTransmissao', ftString, 20);
     FieldDefs.Add('Agencia', ftString, 5);
     FieldDefs.Add('AgenciaDigito', ftString, 2);
     FieldDefs.Add('Conta', ftString, 20);
     FieldDefs.Add('ContaDigito', ftString, 2);
     FieldDefs.Add('Modalidade', ftString, 20);
     FieldDefs.Add('Convenio', ftString, 20);
     FieldDefs.Add('ResponEmissao', ftInteger);
     FieldDefs.Add('CNPJCPF', ftString, 18);
     FieldDefs.Add('TipoInscricao', ftInteger);
     FieldDefs.Add('Logradouro', ftString, 100);
     FieldDefs.Add('NumeroRes', ftString, 10);
     FieldDefs.Add('Complemento', ftString, 100);
     FieldDefs.Add('Bairro', ftString, 100);
     FieldDefs.Add('Cidade', ftString, 100);
     FieldDefs.Add('UF', ftString, 2);
     FieldDefs.Add('CEP', ftString, 8);
     CreateDataSet;
   end;
   // Titulo
   with cdsTitulo do
   begin
     Close;
     FieldDefs.Clear;
     FieldDefs.Add('NossoNum', ftString, 100);
     FieldDefs.Add('CodCedente', ftString, 100);
     FieldDefs.Add('CodBarras', ftString, 100);
     FieldDefs.Add('LinhaDigitavel', ftString, 100);
     FieldDefs.Add('TipoDoc', ftString, 10);
     FieldDefs.Add('Vencimento', ftDateTime);
     FieldDefs.Add('DataDocumento', ftDateTime);
     FieldDefs.Add('NumeroDocumento', ftString, 20);
     FieldDefs.Add('Parcela', ftInteger);
     FieldDefs.Add('EspecieDoc', ftString, 10);
     FieldDefs.Add('EspecieMod', ftString, 10);
     FieldDefs.Add('Aceite', ftInteger);
     FieldDefs.Add('DataProcessamento', ftDateTime);
     FieldDefs.Add('NossoNumero', ftString, 20);
     FieldDefs.Add('Carteira', ftString, 20);
     FieldDefs.Add('ValorDocumento', ftBCD, 18);
     FieldDefs.Add('LocalPagamento', ftString, 100);
     FieldDefs.Add('ValorMoraJuros', ftBCD, 18);
     FieldDefs.Add('ValorDesconto', ftBCD, 18);
     FieldDefs.Add('ValorAbatimento', ftBCD, 18);
     FieldDefs.Add('DataMoraJuros', ftDateTime);
     FieldDefs.Add('DataDesconto', ftDateTime);
     FieldDefs.Add('DataAbatimento', ftDateTime);
     FieldDefs.Add('DataProtesto', ftDateTime);
     FieldDefs.Add('PercentualMulta', ftFloat);
     FieldDefs.Add('Mensagem', ftString, 300);
     FieldDefs.Add('OcorrenciaOriginal', ftInteger);
     FieldDefs.Add('Instrucao1', ftString, 300);
     FieldDefs.Add('Instrucao2', ftString, 300);
     FieldDefs.Add('TextoLivre', ftMemo, 2000);
     FieldDefs.Add('Asbace', ftString, 40);
     // Sacado
     FieldDefs.Add('Sacado_NomeSacado', ftString, 100);
     FieldDefs.Add('Sacado_CNPJCPF', ftString, 18);
     FieldDefs.Add('Sacado_Logradouro', ftString, 100);
     FieldDefs.Add('Sacado_Complemento', ftString, 100);
     FieldDefs.Add('Sacado_Numero', ftString, 10);
     FieldDefs.Add('Sacado_Bairro', ftString, 100);
     FieldDefs.Add('Sacado_Cidade', ftString, 100);
     FieldDefs.Add('Sacado_UF', ftString, 2);
     FieldDefs.Add('Sacado_CEP', ftString, 8);
     FieldDefs.Add('Sacado_Avalista', ftString, 100);
     CreateDataSet;
   end;
end;

{ TACBrBoletoFCFR }

constructor TACBrBoletoFCFR.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  fpAbout := 'ACBRBoletoFCFR ver: ' + CACBrBoletoFCFR_Versao;
  fFastReportFile := '' ;
end;

function TACBrBoletoFCFR.PreparedReport: TfrxReport;
var
  DmBoleto: TdmACBrBoletoFCFR;
begin
  ACBrBoleto.ChecarDadosObrigatorios;
  inherited Imprimir; // Verifica se a lista de boletos est� vazia
  //
  DmBoleto := TdmACBrBoletoFCFR.Create(Self);
  try
  with DmBoleto do
  begin
     cdsBanco.EmptyDataSet;
     cdsCedente.EmptyDataSet;
     cdsTitulo.EmptyDataSet;

     if PrepareReport(DmBoleto) then
       Result := frxReport
     else
       Result := nil;
  end;
  finally
    // DmBoleto.Free;
  end;
end;

procedure TACBrBoletoFCFR.Imprimir;
var
  DmBoleto: TdmACBrBoletoFCFR;
begin
  inherited Imprimir; // Verifica se a lista de boletos est� vazia

  DmBoleto := TdmACBrBoletoFCFR.Create(Self);
  try
    with DmBoleto do
    begin
       cdsBanco.EmptyDataSet;
       cdsCedente.EmptyDataSet;
       cdsTitulo.EmptyDataSet;

       if PrepareReport(DmBoleto) then
       begin
          frxReport.PrintOptions.ShowDialog := MostrarSetup;

          case Filtro of
            fiNenhum:
            begin
               if MostrarPreview then
                  frxReport.ShowReport(False)
               else
                  frxReport.Print;
            end;
            fiPDF:
            begin
               frxPDFExport.FileName := NomeArquivo;
               frxReport.Export(DmBoleto.frxPDFExport);
            end;
            fiHTML:
            begin
               frxHTMLExport.FileName := NomeArquivo;
               frxReport.Export(DmBoleto.frxHTMLExport);
            end;
          else
            exit;
          end;
       end;
    end;
  finally
    DmBoleto.Free;
  end;
end;

function TACBrBoletoFCFR.PrepareReport(const DmBoleto: TdmACBrBoletoFCFR): Boolean;
begin
  Result := False;

  if Trim(fFastReportFile) <> '' then
  begin
    if FileExists(fFastReportFile) then
       DmBoleto.frxReport.LoadFromFile(fFastReportFile)
    else
      raise EACBrBoletoFCFR.CreateFmt('Caminho do arquivo de impress�o do boleto "%s" inv�lido.', [fFastReportFile]);
  end
  else
    raise EACBrBoletoFCFR.Create('Caminho do arquivo de impress�o do boleto n�o assinalado.');

  if PrepareBoletos(DmBoleto) then
  begin
     Result := DmBoleto.frxReport.PrepareReport;
  end;
end;

function TACBrBoletoFCFR.PrepareBoletos(const DmBoleto: TdmACBrBoletoFCFR): Boolean;
var
  iFor: Integer;
  sTipoDoc: String;
begin
   with ACBrBoleto do
   begin
      // Banco
      with DmBoleto.cdsBanco do
      begin
         Append;
         FieldByName('Numero').AsString  := FormatFloat('000', Banco.Numero);
         FieldByName('Digito').AsString := IfThen(Banco.Digito >= 10, 'X',
                                                  IntToStrZero(Banco.Digito, 1));
         FieldByName('Nome').AsString    := Banco.Nome;
         FieldByName('DirLogo').AsString := DirLogo;
         Post;
      end;
      // Cedente
      with DmBoleto.cdsCedente do
      begin
         Append;
         FieldByName('Nome').AsString              := Cedente.Nome;
         FieldByName('CodigoCedente').AsString     := Cedente.CodigoCedente;
         FieldByName('CodigoTransmissao').AsString := Cedente.CodigoTransmissao;
         FieldByName('Agencia').AsString           := Cedente.Agencia;
         FieldByName('AgenciaDigito').AsString     := Cedente.AgenciaDigito;
         FieldByName('Conta').AsString             := Cedente.Conta;
         FieldByName('ContaDigito').AsString       := Cedente.ContaDigito;
         FieldByName('Modalidade').AsString        := Cedente.Modalidade;
         FieldByName('Convenio').AsString          := Cedente.Convenio;
         FieldByName('ResponEmissao').AsInteger    := Integer(Cedente.ResponEmissao);
         FieldByName('CNPJCPF').AsString           := Cedente.CNPJCPF;
         FieldByName('TipoInscricao').AsInteger    := Integer(Cedente.TipoInscricao);
         FieldByName('Logradouro').AsString        := Cedente.Logradouro;
         FieldByName('NumeroRes').AsString         := Cedente.NumeroRes;
         FieldByName('Complemento').AsString       := Cedente.Complemento;
         FieldByName('Bairro').AsString            := Cedente.Bairro;
         FieldByName('Cidade').AsString            := Cedente.Cidade;
         FieldByName('UF').AsString                := Cedente.UF;
         FieldByName('CEP').AsString               := Cedente.CEP;
         Post;
      end;
      // Titulos
      for iFor := 0 to ListadeBoletos.Count - 1 do
      begin
         case ACBrBoleto.Cedente.TipoInscricao of
            pFisica   : sTipoDoc := 'CPF: ';
            pJuridica : sTipoDoc := 'CNPJ: ';
         else
            sTipoDoc := 'DOC.: ';
         end;
         // Monta mensagens de multa e juros
         AdicionarMensagensPadroes( ListadeBoletos[iFor], ListadeBoletos[iFor].Mensagem);

         with DmBoleto.cdsTitulo do
         begin
            Append;
            FieldByName('NossoNum').AsString            := Banco.MontarCampoNossoNumero( ListadeBoletos[iFor] );
            FieldByName('CodCedente').AsString          := Banco.MontarCampoCodigoCedente( ListadeBoletos[iFor] );
            FieldByName('CodBarras').AsString           := Banco.MontarCodigoBarras( ListadeBoletos[iFor] );
            FieldByName('LinhaDigitavel').AsString      := Banco.MontarLinhaDigitavel( FieldByName('CodBarras').AsString );
            FieldByName('TipoDoc').AsString             := sTipoDoc;
            FieldByName('Vencimento').AsDateTime        := ListadeBoletos[iFor].Vencimento;
            FieldByName('DataDocumento').AsDateTime     := ListadeBoletos[iFor].DataDocumento;
            FieldByName('NumeroDocumento').AsString     := ListadeBoletos[iFor].NumeroDocumento;
            FieldByName('Parcela').AsInteger            := ListadeBoletos[iFor].Parcela;
            FieldByName('EspecieMod').AsString          := ListadeBoletos[iFor].EspecieMod;
            FieldByName('EspecieDoc').AsString          := ListadeBoletos[iFor].EspecieDoc;
            FieldByName('Aceite').AsInteger             := Integer(ListadeBoletos[iFor].Aceite);
            FieldByName('DataProcessamento').AsDateTime := ListadeBoletos[iFor].DataProcessamento;
            FieldByName('NossoNumero').AsString         := ListadeBoletos[iFor].NossoNumero;
            FieldByName('Carteira').AsString            := ListadeBoletos[iFor].Carteira;
            FieldByName('ValorDocumento').AsCurrency    := ListadeBoletos[iFor].ValorDocumento;
            FieldByName('LocalPagamento').AsString      := ListadeBoletos[iFor].LocalPagamento;
            FieldByName('ValorMoraJuros').AsCurrency    := ListadeBoletos[iFor].ValorMoraJuros;
            FieldByName('ValorDesconto').AsCurrency     := ListadeBoletos[iFor].ValorDesconto;
            FieldByName('ValorAbatimento').AsCurrency   := ListadeBoletos[iFor].ValorAbatimento;
            FieldByName('DataMoraJuros').AsDateTime     := ListadeBoletos[iFor].DataMoraJuros;
            FieldByName('DataDesconto').AsDateTime      := ListadeBoletos[iFor].DataDesconto;
            FieldByName('DataAbatimento').AsDateTime    := ListadeBoletos[iFor].DataAbatimento;
            FieldByName('DataProtesto').AsDateTime      := ListadeBoletos[iFor].DataProtesto;
            FieldByName('PercentualMulta').AsFloat      := ListadeBoletos[iFor].PercentualMulta;
            FieldByName('Mensagem').AsString            := ListadeBoletos[iFor].Mensagem.Text;
            FieldByName('OcorrenciaOriginal').AsInteger := Integer(ListadeBoletos[iFor].OcorrenciaOriginal);
            FieldByName('Instrucao1').AsString          := ListadeBoletos[iFor].Instrucao1;
            FieldByName('Instrucao2').AsString          := ListadeBoletos[iFor].Instrucao2;
            FieldByName('TextoLivre').AsString          := ListadeBoletos[iFor].TextoLivre;
            if ACBrBoleto.Banco.Numero = 21 then
              FieldByName('Asbace').AsString            := TACBrBanestes(Banco).CalcularCampoASBACE(ListadeBoletos[iFor]);

            // Sacado
            FieldByName('Sacado_NomeSacado').AsString   := ListadeBoletos[iFor].Sacado.NomeSacado;
            FieldByName('Sacado_CNPJCPF').AsString      := ListadeBoletos[iFor].Sacado.CNPJCPF;
            FieldByName('Sacado_Logradouro').AsString   := ListadeBoletos[iFor].Sacado.Logradouro;
            FieldByName('Sacado_Complemento').AsString  := ListadeBoletos[iFor].Sacado.Complemento;
            FieldByName('Sacado_Numero').AsString       := ListadeBoletos[iFor].Sacado.Numero;
            FieldByName('Sacado_Bairro').AsString       := ListadeBoletos[iFor].Sacado.Bairro;
            FieldByName('Sacado_Cidade').AsString       := ListadeBoletos[iFor].Sacado.Cidade;
            FieldByName('Sacado_UF').AsString           := ListadeBoletos[iFor].Sacado.UF;
            FieldByName('Sacado_CEP').AsString          := ListadeBoletos[iFor].Sacado.CEP;
            FieldByName('Sacado_Avalista').AsString     := ListadeBoletos[iFor].Sacado.Avalista;
            Post;
         end;
      end;
   end;

   Result := True;
end;

end.
