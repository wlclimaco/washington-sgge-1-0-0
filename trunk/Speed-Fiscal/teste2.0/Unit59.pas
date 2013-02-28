unit Unit59;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, DB, ADODB, ComCtrls;

type
  TForm59 = class(TForm)
    Button1: TButton;
    Button2: TButton;
    Memo1: TMemo;
    ProgressBar1: TProgressBar;
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form59: TForm59;

implementation

uses Unit2;

{$R *.dfm}

procedure TForm59.Button1Click(Sender: TObject);
var
  cont,soma :Integer;
begin
 cont := DataModule2.tblprodutos.Fields.Count ;
  ProgressBar1.Min := 0 ;
  ProgressBar1.Max := cont;
  soma := 0;
while(not DataModule2.tblprodutos.eof)do
//insert into produtos                                                                                                                                (cdproduto,dsproduto,unidademed,dtcadastro,cdbarra,icms,precounit,ref) values (:cdproduto,:dsproduto,:unidademed,:dtcadastro,:cdbarra,:icms,:ipi,:precounit,:ref,:CODIND)
begin
  DataModule2.qryprodutos.SQL.Add('insert into produtos (cdproduto,dsproduto,unidademed,dtcadastro,icms,ipi,precounit,ref,CODIND) values ('+FloatToStr(DataModule2.tblprodutosCODPRO.Value)+','+ DataModule2.tblprodutosPRODUTO.Value +','+DataModule2.tblprodutosUNIDADE.Value +','+DateToStr(now) +','+ FloatToStr(DataModule2.tblprodutosICMS.value) +','+FloatToStr(DataModule2.tblprodutosIPI.Value)+','+FloatToStr(DataModule2.tblprodutosCUSTO.Value) +','+DataModule2.tblprodutosREF.Value +','+ FloatToStr(DataModule2.tblprodutosCODIND.Value) + ')');
  Memo1.Lines.Add(DataModule2.qryprodutos.SQL.Text);
  ProgressBar1.Position := soma ;
  soma := soma + 1;
  DataModule2.tblprodutos.Next;
end;
end ;
end.
