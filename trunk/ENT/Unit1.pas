unit Unit1;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, Unit5, Menus, Buttons;

type
  TForm1 = class(TForm)
    MainMenu1: TMainMenu;
    SpeedButton1: TSpeedButton;
    Entrada1: TMenuItem;
    Produtos1: TMenuItem;
    Sintegra1: TMenuItem;
    Sair1: TMenuItem;
    EntradasNF1: TMenuItem;
    CadastroProdutos1: TMenuItem;
    Fornecedores1: TMenuItem;
    SpeedButton2: TSpeedButton;
    SpeedButton3: TSpeedButton;
    procedure Button1Click(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure EntradasNF1Click(Sender: TObject);
    procedure CadastroProdutos1Click(Sender: TObject);
    procedure Sintegra1Click(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
    procedure Fornecedores1Click(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);

  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;
  Classproduto : TProduto;
implementation

uses Unit4, Unit82, Unit8, Unit7, Unit11, Unit14, Unit13;

{$R *.dfm}

procedure TForm1.Button1Click(Sender: TObject);
begin
ModeloForm4.ShowModal;

end;

procedure TForm1.Button2Click(Sender: TObject);
begin
 Form82.Show;
end;

procedure TForm1.EntradasNF1Click(Sender: TObject);
begin
Form7.ShowModal;
end;

procedure TForm1.CadastroProdutos1Click(Sender: TObject);
begin
ModeloForm4.ShowModal;
end;

procedure TForm1.Sintegra1Click(Sender: TObject);
begin
Form82.ShowModal;
end;

procedure TForm1.SpeedButton2Click(Sender: TObject);
begin
ModeloForm11.ShowModal;
end;



procedure TForm1.Fornecedores1Click(Sender: TObject);
begin
   ModeloForm7.ShowModal;
end;

procedure TForm1.SpeedButton3Click(Sender: TObject);
begin
ModeloForm13.Show;
end;

end.
