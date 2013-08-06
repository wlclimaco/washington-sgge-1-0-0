{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para interação com equipa- }
{ mentos de Automação Comercial utilizados no Brasil                           }

{ Direitos Autorais Reservados (c) 2009   http://www.produsys.com.br/          }

{ Colaboradores nesse arquivo:  Juliana Rodrigues Prado, Daniel Simoes Almeida }

{  Você pode obter a última versão desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }

{  Esta biblioteca é software livre; você pode redistribuí-la e/ou modificá-la }
{ sob os termos da Licença Pública Geral Menor do GNU conforme publicada pela  }
{ Free Software Foundation; tanto a versão 2.1 da Licença, ou (a seu critério) }
{ qualquer versão posterior.                                                   }

{  Esta biblioteca é distribuída na expectativa de que seja útil, porém, SEM   }
{ NENHUMA GARANTIA; nem mesmo a garantia implícita de COMERCIABILIDADE OU      }
{ ADEQUAÇÃO A UMA FINALIDADE ESPECÍFICA. Consulte a Licença Pública Geral Menor}
{ do GNU para mais detalhes. (Arquivo LICENÇA.TXT ou LICENSE.TXT)              }

{  Você deve ter recebido uma cópia da Licença Pública Geral Menor do GNU junto}
{ com esta biblioteca; se não, escreva para a Free Software Foundation, Inc.,  }
{ no endereço 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          }
{ Você também pode obter uma copia da licença em:                              }
{ http://www.opensource.org/licenses/lgpl-license.php                          }

{ Daniel Simões de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Praça Anita Costa, 34 - Tatuí - SP - 18270-410                  }

{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 01/04/2010: Juliana Rodrigues Prado Tamizou
|*  - Adaptação do Boleto do Projeto RLBoleto  ( http://www.produsys.com.br/ )
******************************************************************************}
{$I ACBr.inc}

unit ACBrBoletoFCLazReportDm;

interface

uses
  Classes, SysUtils, FileUtil, Forms, Controls, Graphics, Dialogs, ExtCtrls,
  ACBrBoleto, LR_Class, LR_DSet, LR_BarC, LR_Shape, LR_RRect, LR_E_HTM,
  lr_e_pdf, PrintersDlgs, Printers, strutils, LResources, PReport ;

const
  CACBrBoletoFCLazReport_Versao = '0.1.16a';

type

  { TdmACbrBoletoFCLazReport }

  TACBrBoletoFCLazReport = class(TACBrBoletoFCClass)
  private
    { Private declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;

    procedure Imprimir; override;
  published
  end;

  TdmACbrBoletoFCLazReport = class(TDataModule)
    frBarCodeObject1: TfrBarCodeObject;
    frHTMExport1 : TfrHTMExport ;
    frReport1: TfrReport;
    frShapeObject1: TfrShapeObject;
    frTNPDFExport1: TfrTNPDFExport;
    frUserDataset1: TfrUserDataset;
    PrintDialog1: TPrintDialog;
    procedure FormCreate(Sender: TObject);
    procedure FormDestroy(Sender: TObject);
    procedure frReport1GetValue(const ParName: string;
      var ParValue: variant);
    procedure frReport1EnterRect(Memo: TStringList; View: TfrView);{%h-}
    procedure frUserDataset1CheckEOF(Sender: TObject; var EOF: boolean);
    procedure frUserDataset1First(Sender: TObject);
    procedure frUserDataset1Next(Sender: TObject);
  private
    MensagemPadrao: TStringList;
    fBoletoFC: TACBrBoletoFCLazReport;
    fIndice: Integer;
    function GetACBrTitulo: TACBrTitulo;
    { private declarations }
  public
    { public declarations }
    property Indice   : Integer read fIndice;
    property BoletoFC : TACBrBoletoFCLazReport read fBoletoFC;
    property Titulo   : TACBrTitulo read GetACBrTitulo;
  end;

procedure Register;

implementation

uses ACBrUtil;

{$R *.lfm}

procedure Register;
begin
  RegisterComponents('ACBr', [TACBrBoletoFCLazReport]);
end;

{ TACBrBoletoFCLazReport }

constructor TACBrBoletoFCLazReport.Create(AOwner: TComponent);
begin
  inherited Create(AOwner);
  fpAbout := 'ACBRBoletoFCLazReport ver: ' + CACBrBoletoFCLazReport_Versao;
end;

procedure TACBrBoletoFCLazReport.Imprimir;
var
  frACBrBoletoLazReport : TdmACbrBoletoFCLazReport;
  RelBoleto, Dir : string;
  PageIni, PageFim, PInd : Integer;
  Res : TLResource ;
  MS  : TMemoryStream ;
begin
  inherited Imprimir;    // Verifica se a Lista de Boletos está vazia

  frACBrBoletoLazReport := TdmACbrBoletoFCLazReport.Create(self);
  try
     with frACBrBoletoLazReport do
     begin
        case LayOut of
           lCarne         : RelBoleto := 'FCLazReport_Carne';
           lPadraoEntrega : RelBoleto := 'FCLazReport_CompEntrega';
        else
           RelBoleto := 'FCLazReport_Padrao';
        end;

        // Verificando se o Relatório existe no disco //
        Dir := ExtractFilePath(Application.ExeName) ;
        if FileExists( Dir + RelBoleto + '.lrf' ) then
           frReport1.LoadFromFile( Dir + RelBoleto + '.lrf' )
        else
         begin
           // Lendo Relatório de Resource Interno //
           Res := LazarusResources.Find(RelBoleto,'LRF');  // Le de ACBrBoletoFCLazReport.lrs
           if Res = nil then
              raise Exception.Create('Resource: '+RelBoleto+' não encontrado');

           MS := TMemoryStream.Create ;
           try
              MS.Write(Pointer(Res.Value)^,Length(Res.Value)) ;
              MS.Position := 0;

              frReport1.LoadFromXMLStream( MS );
           finally
              MS.Free ;
           end;
         end ;

        PInd := Printer.PrinterIndex;
        frReport1.ChangePrinter( PInd, 0 );

        if not frReport1.PrepareReport then
           Exit;

        with PrintDialog1 do
        begin
           Options  := [poPageNums];
           Collate  := true;
           //FromPage := 0;
           //ToPage   := fBoletoFC.ACBrBoleto.ListadeBoletos.Count;
           //MaxPage  := ToPage;
           Copies   := NumCopias;
           //PageIni  := FromPage;
           //PageFim  := ToPage;
        end;

        Case Filtro of
          fiNenhum :
            begin
              if MostrarPreview then
                 frReport1.ShowReport
              else
              begin
                 if MostrarSetup then
                 begin
                    if PrintDialog1.Execute then
                    begin
                       if (Printer.PrinterIndex <> PInd ) or
                          frReport1.CanRebuild            or
                          frReport1.ChangePrinter( PInd, Printer.PrinterIndex ) then
                       begin
                          frReport1.PrepareReport
                       end ;

                       if PrintDialog1.PrintRange = prPageNums then
                       begin
                          PageIni := PrintDialog1.FromPage;
                          PageFim := PrintDialog1.ToPage;
                       end;
                    end;
                 end
                 else
                   frReport1.PrepareReport;

                frReport1.PrintPreparedReport( IntToStr(PageIni) + '-' +
                IntToStr(PageFim), NumCopias );
              end;
            end ;


          fiPDF :
            begin
              frReport1.ExportTo(TfrTNPDFExportFilter, NomeArquivo) ;
            end;

          fiHTML :
              raise Exception.Create('Geração de arquivo HTML ainda não implementada no LazReport.');
        end ;
     end;
  finally
     frACBrBoletoLazReport.Free;
  end;
end;

{ TdmACbrBoletoFCLazReport }

procedure TdmACbrBoletoFCLazReport.FormCreate(Sender: TObject);
begin
  fIndice        := 0;
  MensagemPadrao := TStringList.Create;
  fBoletoFC      := TACBrBoletoFCLazReport(Owner);
end;

procedure TdmACbrBoletoFCLazReport.FormDestroy(Sender: TObject);
begin
  MensagemPadrao.Free;
end;

procedure TdmACbrBoletoFCLazReport.frUserDataset1First(Sender: TObject);
begin
  fIndice := 0;
end;

procedure TdmACbrBoletoFCLazReport.frUserDataset1Next(Sender: TObject);
begin
  Inc( fIndice );
end;

procedure TdmACbrBoletoFCLazReport.frUserDataset1CheckEOF(Sender: TObject;
  var EOF: boolean);
begin
  EOF := (fIndice >= fBoletoFC.ACBrBoleto.ListadeBoletos.Count);
end;

procedure TdmACbrBoletoFCLazReport.frReport1GetValue(const ParName: string;
  var ParValue: variant);
var
  NossoNum, TipoDoc, CodCedente, CodBarras, LinhaDigitavel: string;
begin

  with fBoletoFC.ACBrBoleto do
  begin
    CodCedente     := Banco.MontarCampoCodigoCedente(Titulo);
    NossoNum       := Banco.MontarCampoNossoNumero(Titulo);
    CodBarras      := Banco.MontarCodigoBarras(Titulo);
    LinhaDigitavel := Banco.MontarLinhaDigitavel(CodBarras);

    MensagemPadrao.Clear;
    MensagemPadrao.Text := Titulo.Mensagem.Text;
    ACBrBoletoFC.ACBrBoleto.AdicionarMensagensPadroes( Titulo, MensagemPadrao );


    if ParName = 'Codbanco' then                                  {Código Banco}
       ParValue := IntToStrZero(Banco.Numero, 3) + '-' +
                   ifThen(Banco.Digito >= 10, 'X',
                   IntToStrZero(Banco.Digito, 1))

    else if ParName = 'LocalPagto' then                     {Local de Pagamento}
       ParValue := Titulo.LocalPagamento

    else if ParName = 'Vencimento' then                     {Data de Vencimento}
      ParValue := FormatDateTime('dd/mm/yyyy', Titulo.Vencimento)

    else if ParName = 'RazaoSocial' then          {Nome/Razão Social do Cedente}
     begin
       case Cedente.TipoInscricao of
          pFisica   : TipoDoc := 'CPF: ';
          pJuridica : TipoDoc := 'CNPJ: ';
       else
          TipoDoc := 'DOC.: ';
       end;

       ParValue := Cedente.Nome + ' - ' + TipoDoc + Cedente.CNPJCPF;
     end

    else if ParName = 'Endereco' then                         {Endereço Cedente}
       ParValue := Cedente.Logradouro  + ' ' + Cedente.NumeroRes + ' ' +
                   Cedente.Complemento + ' ' + Cedente.Bairro    + ' ' +
                   Cedente.Cidade      + ' ' + Cedente.UF

    else if ParName = 'Conta' then                              {Codigo Cedente}
       ParValue := CodCedente
    else if ParName = 'OrientacoesBanco' then {Orientações do Banco}
       ParValue:= Banco.OrientacoesBanco.Text

    else if ParName = 'DataDocto' then                       {Data do Documento}
       ParValue := FormatDateTime('dd/mm/yyyy', Titulo.DataDocumento)

    else if ParName = 'NumDocto' then                      {Número do Documento}
       ParValue := Titulo.NumeroDocumento

    else if ParName = 'Especie' then                   {Espécie do Documento}
       ParValue := Titulo.EspecieDoc

    else if ParName = 'Moeda' then                   {Espécie Moeda}
       ParValue := Titulo.EspecieMod

    else if ParName = 'Aceite' then                                     {Aceite}
       ParValue := IfThen(Titulo.Aceite = atSim, 'S', 'N')

    else if ParName = 'DataProcessamento' then           {Data de Processamento}
       ParValue := FormatDateTime('dd/mm/yyyy', titulo.DataProcessamento)

    else if ParName = 'NossoNumero' then                          {Nosso Número}
       ParValue := NossoNum

    else if ParName = 'Carteira' then                                 {Carteira}
       ParValue := Titulo.Carteira

    else if ParName = 'Valor' then                             {Valor Documento}
       ParValue := FormatFloat('###,###,##0.00', Titulo.ValorDocumento)

    else if ParName = 'Obs' then                                    {Instruções}
       ParValue := MensagemPadrao.Text

    else if ParName = 'Nome' then                               {Nome do Sacado}
       ParValue := Titulo.Sacado.NomeSacado

    else if ParName = 'EndSacado' then                      {Endereço do Sacado}
       ParValue := Titulo.Sacado.Logradouro  + ' ' +
                   Titulo.Sacado.Numero      + ' ' + Titulo.Sacado.Bairro + ' ' +
                   Titulo.Sacado.Complemento + ' ' +
                   Titulo.Sacado.CEP         + ' ' + Titulo.Sacado.Cidade + ' ' +
                   Titulo.Sacado.UF

    else if ParName = 'Docto' then                          {CPF/CNPJ do Sacado}
       ParValue := Titulo.Sacado.CNPJCPF

    else if ParName = 'Barras' then                           {Codigo de Barras}
       ParValue := CodBarras

    else if ParName = 'LinhaDigitavel' then                    {Linha Digitavel}
       ParValue := LinhaDigitavel

    else if ParName = 'Site' then                                {SoftwareHouse}
       ParValue := ACBrBoletoFC.SoftwareHouse

    else if ParName = 'UsoBanco' then
       ParValue := Titulo.UsoBanco

    else if ParName = 'Parcela' then
       ParValue:= Titulo.Parcela

    else if ParName = 'TotalParcelas' then
       ParValue:= Titulo.TotalParcelas;

  end;
end;

procedure TdmACbrBoletoFCLazReport.frReport1EnterRect(Memo: TStringList;
  View: TfrView);
begin
   if (View.Name = 'Picture1') or (View.Name = 'Picture2')  or (View.Name = 'Picture4') then
      fBoletoFC.CarregaLogo( TfrPictureView(View).Picture,
                             fBoletoFC.ACBrBoleto.Banco.Numero );
end;

function TdmACbrBoletoFCLazReport.GetACBrTitulo: TACBrTitulo;
begin
  Result := fBoletoFC.ACBrBoleto.ListadeBoletos[fIndice];
end;

initialization
{$I ACBrBoletoFCLazReport.lrs}

end.

