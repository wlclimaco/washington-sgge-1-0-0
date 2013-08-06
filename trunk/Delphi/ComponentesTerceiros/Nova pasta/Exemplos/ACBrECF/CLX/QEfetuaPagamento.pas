unit QEfetuaPagamento;

interface

uses ACBrECF, 
  SysUtils, StrUtils, QTypes, QGraphics, QControls,
  QForms, QDialogs, QStdCtrls, QButtons, Classes;

type
  TfrPagamento = class(TForm)
    mFormas: TMemo;
    Label1: TLabel;
    edCod: TEdit;
    Label2: TLabel;
    edValor: TEdit;
    btImprimir: TButton;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    lTotalAPAGAR: TLabel;
    lTotalPago: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    cbVinc: TCheckBox;
    Label8: TLabel;
    edObs: TEdit;
    SpeedButton1: TSpeedButton;
    procedure edValorKeyPress(Sender: TObject; var Key: Char);
    procedure btImprimirClick(Sender: TObject);
    procedure FormShow(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
  private
    { Private declarations }
    procedure AtualizaVal ;
    Procedure CarregaFPG ;
  public
    { Public declarations }
    TipoCupom : Char ;
  end;

var
  frPagamento: TfrPagamento;

implementation

uses QECFTeste1, ACBrECFClass;

{$R *.xfm}

procedure TfrPagamento.edValorKeyPress(Sender: TObject; var Key: Char);
begin
  if Key in [',','.'] then
     Key := DecimalSeparator ;
end;

procedure TfrPagamento.btImprimirClick(Sender: TObject);
begin
  if TipoCupom <> 'N' then
   begin
     Form1.ACBrECF1.EfetuaPagamento( edCod.Text, StrToFloat( edValor.Text),
                                     edObs.Text ,cbVinc.Checked );
     Form1.mResp.Lines.Add( 'Efetua Pagamento: '+edCod.Text +
                            ' Valor: '+edValor.Text +
                            ' Obs: '+edObs.Text +
                            ' Vinc: '+StrUtils.IfThen(cbVinc.Checked,'S','N') );
   end
  else
   begin
     Form1.ACBrECF1.EfetuaPagamentoNaoFiscal( edCod.Text, StrToFloat( edValor.Text),
                                     edObs.Text ,cbVinc.Checked );
     Form1.mResp.Lines.Add( 'Efetua Pagamento N�o Fiscal: '+edCod.Text +
                            ' Valor: '+edValor.Text +
                            ' Obs: '+edObs.Text +
                            ' Vinc: '+StrUtils.IfThen(cbVinc.Checked,'S','N') );

   end ;

  Form1.AtualizaMemos ;
  AtualizaVal ;
end;

procedure TfrPagamento.FormShow(Sender: TObject);
begin
  AtualizaVal ;
  CarregaFPG ;
  TipoCupom := 'F' ;
end;

procedure TfrPagamento.AtualizaVal;
begin
  lTotalAPAGAR.Caption := FloatToStr( Form1.ACBrECF1.Subtotal ) ;
  lTotalPago.Caption   := FloatToStr( Form1.ACBrECF1.TotalPago ) ;
end;

procedure TfrPagamento.SpeedButton1Click(Sender: TObject);
Var Descricao : String ;
    FPG : TACBrECFFormaPagamento ;  { Necessita de uses ACBrECF }
begin
  if InputQuery('Pesquisa Descri��o Forma Pagamento',
                'Entre com a Descri��o a Localizar ou Cadastrar(Bematech)',
                Descricao) then
  begin
     FPG := Form1.ACBrECF1.AchaFPGDescricao( Descricao ) ;

     if FPG = nil then
        raise Exception.Create('Forma de Pagamento: '+Descricao+
                               ' n�o encontrada') ;

     edCod.Text := FPG.Indice ;

   { Bematech e NaoFiscal permitem cadastrar formas de Pagamento dinamicamente }
     if (Form1.ACBrECF1.Modelo in [ecfBematech,ecfNaoFiscal])  and
        (pos( FPG.Descricao, mFormas.Text ) = 0) then
        CarregaFPG ;
  end ;
        
end;

procedure TfrPagamento.CarregaFPG;
Var A : Integer ;
begin
  mFormas.Clear ;
  with Form1 do
  begin
     { Bematech e NaoFiscal permitem cadastrar formas de Pagamento dinamicamente }
     if (Form1.ACBrECF1.Modelo in [ecfBematech,ecfNaoFiscal])then
        ACBrECF1.CarregaFormasPagamento 
     else
        ACBrECF1.AchaFPGIndice('') ;  { for�a carregar, se ainda nao o fez }

     for A := 0 to ACBrECF1.FormasPagamento.Count -1 do
     begin
        mFormas.Lines.Add( ACBrECF1.FormasPagamento[A].Indice+' -> '+
              ACBrECF1.FormasPagamento[A].Descricao+' - '+StrUtils.IfThen(
              ACBrECF1.FormasPagamento[A].PermiteVinculado,'v',''));
     end ;
  end ;

end;

end.
