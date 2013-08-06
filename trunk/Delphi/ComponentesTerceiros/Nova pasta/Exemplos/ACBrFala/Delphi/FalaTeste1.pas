{$I ACBr.inc}
unit FalaTeste1;

interface

uses
  Windows, Messages, SysUtils,
  {$IFDEF Delphi6_UP} Variants, {$ELSE} ACBrD5, {$ENDIF}
  Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ACBrBase, ACBrFala, Buttons, FileCtrl;

type
  TForm1 = class(TForm)
    ACBrFala1: TACBrFala;
    edValor: TEdit;
    Label1: TLabel;
    Label2: TLabel;
    mFalar: TMemo;
    bFalar: TButton;
    chPromo: TCheckBox;
    chNaoDisp: TCheckBox;
    rbDir: TRadioButton;
    rbResource: TRadioButton;
    edDir: TEdit;
    sbDir: TSpeedButton;
    chDinheiro: TCheckBox;
    SpeedButton1: TSpeedButton;
    procedure edValorChange(Sender: TObject);
    procedure chNaoDispClick(Sender: TObject);
    procedure bFalarClick(Sender: TObject);
    procedure edValorExit(Sender: TObject);
    procedure rbDirClick(Sender: TObject);
    procedure edDirExit(Sender: TObject);
    procedure sbDirClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure chPromoClick(Sender: TObject);
    procedure edValorKeyPress(Sender: TObject; var Key: Char);
    procedure SpeedButton1Click(Sender: TObject);
    procedure chDinheiroClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;

implementation

{$R *.dfm}
{$R ..\..\..\ACBrFalaWaves\ACBrFala.RES }

procedure TForm1.FormCreate(Sender: TObject);
begin
  edDir.Text         := ACBrFala1.OrigemArquivos ;
  rbDir.Checked      := (ACBrFala1.LocalSons = lsDiretorio) ;
  rbResource.Checked := (ACBrFala1.LocalSons = lsRecurso) ;
end;

procedure TForm1.edValorChange(Sender: TObject);
begin
  mFalar.Lines.Clear ;
end;

procedure TForm1.chNaoDispClick(Sender: TObject);
begin
  mFalar.Text := 'Pre�oN�oDispon�vel' ;

  chPromo.Enabled := not chNaoDisp.Checked ;
  mFalar.Enabled  := not chNaoDisp.Checked ;
  edValor.Enabled := not chNaoDisp.Checked ;
end;

procedure TForm1.bFalarClick(Sender: TObject);
//var TextoFalar : String ;
begin
(*  if chNaoDisp.Checked then
     TextoFalar := 'Pre�oN�oDispon�vel'
  else
   begin
     Val := StrToFloatDef(edValor.Text,0) ;
     if Val > 0 then
     begin
        ACBrFala1.ValorFalar := Val ;
        mFalar.Text := ACBrFala1.StrFalar ;
     end ;

     TextoFalar := mFalar.Text ;
     if pos('Promo��o',TextoFalar) = 1 then
        TextoFalar := copy(TextoFalar,10,Length(TextoFalar)) ;

     if chPromo.Checked then
        TextoFalar := 'Promo��o '+TextoFalar ;
   end ;

  mFalar.Text := TextoFalar ;  { atualiza a tela }
*)
  bFalar.Enabled := False ;
  Application.ProcessMessages ;
  try
     ACBrFala1.StrFalar := mFalar.Text ;    //TextoFalar ;
     ACBrFala1.Falar ;
  finally
     bFalar.Enabled := True ;
  end ;
end;

procedure TForm1.edValorExit(Sender: TObject);
Var Val : Double ;
begin
  Val := StrToFloatDef(edValor.Text,0) ;

  if Val > 0 then
  begin
     ACBrFala1.ValorFalar := Val ;
     mFalar.Text := ACBrFala1.StrFalar ;
  end ;
end;

procedure TForm1.rbDirClick(Sender: TObject);
begin
  if rbDir.Checked then
     ACBrFala1.LocalSons := lsDiretorio
  else
     ACBrFala1.LocalSons := lsRecurso ;

  edDir.Enabled := rbDir.Checked ;
  sbDir.Enabled := rbDir.Checked ;
end;

procedure TForm1.edDirExit(Sender: TObject);
begin
  ACBrFala1.OrigemArquivos := edDir.Text ;
  ACBrFala1.CarregaArquivosSons ;
end;

procedure TForm1.sbDirClick(Sender: TObject);
Var Dir : String ;
begin
  Dir := edDir.Text ;
  if SelectDirectory(Dir,[],0) then
     edDir.Text := Dir ;

  ACBrFala1.OrigemArquivos := edDir.Text ;
end;

procedure TForm1.chPromoClick(Sender: TObject);
begin
  if pos('Promo��o',mFalar.Text) = 1 then
     mFalar.Text := copy(mFalar.Text,10,Length(mFalar.Text)) ;

  if chPromo.Checked then
     mFalar.Text := 'Promo��o '+mFalar.Text ;
end;

procedure TForm1.edValorKeyPress(Sender: TObject; var Key: Char);
begin
  if not ( Key in ['0'..'9',',','.',#13,#8] ) then
     Key := #0
  else
     if Key in [',','.'] then
        Key := DecimalSeparator ;
end;

procedure TForm1.chDinheiroClick(Sender: TObject);
begin
  ACBrFala1.ValorDinheiro := chDinheiro.checked ;
  edValorExit(Sender);
end;

procedure TForm1.SpeedButton1Click(Sender: TObject);
begin
  MessageDlg('Para cada palavra que o ACBrFala deva falar '+
             'deve exitir um arquivo de Som correspontente com o mesmo nome. '+
             'Exemplo: Se a palavra � "Reais" ele ir� procurar por "reais.wav".',
             mtInformation,[mbOk],0) ;
end;

end.
