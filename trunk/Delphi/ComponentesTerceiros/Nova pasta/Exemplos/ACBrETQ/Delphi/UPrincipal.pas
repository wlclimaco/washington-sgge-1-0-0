unit UPrincipal;

interface

uses
  Classes, SysUtils, Forms, Controls, Graphics, Dialogs, StdCtrls,
  Buttons, ExtDlgs, ExtCtrls, ACBrDevice, ACBrETQ, ACBrBase;

type

  { TFPrincipal }

  TFPrincipal = class(TForm)
    ACBrETQ: TACBrETQ;
    bImprimirImagem : TButton;
    bCarregarImg : TButton;
    cbModelo: TComboBox;
    cbDPI : TComboBox ;
    ckMemoria : TCheckBox;
    eAvanco1 : TEdit ;
    Edit1 : TEdit;
    Image1 : TImage;
    Label1: TLabel;
    Label2: TLabel;
    cbPorta: TComboBox;
    eCopias: TEdit;
    Label3: TLabel;
    eAvanco: TEdit;
    Label4: TLabel;
    bEtqSimples: TButton;
    bEtqCarreiras: TButton;
    Label5 : TLabel;
    Label6 : TLabel ;
    Label7 : TLabel ;
    OpenPictureDialog1 : TOpenPictureDialog;
    rbStream : TRadioButton ;
    rbArquivo : TRadioButton ;
    procedure bEtqSimplesClick(Sender: TObject);
    procedure bEtqCarreirasClick(Sender: TObject);
    procedure bImprimirImagemClick(Sender : TObject);
    procedure bCarregarImgClick(Sender : TObject);
    procedure cbModeloChange(Sender : TObject) ;
    procedure eCopiasKeyPress(Sender : TObject ; var Key : char) ;
  private
     procedure AtivarACBrETQ ;
    { private declarations }
  public
    { public declarations }
  end; 

var
  FPrincipal: TFPrincipal;

implementation

{$R *.dfm}

procedure TFPrincipal.bEtqSimplesClick(Sender: TObject);
begin
  AtivarACBrETQ ;

  with ACBrETQ do
  begin
     if Modelo = etqPpla then
      begin
        ImprimirTexto(orNormal, 2, 2, 2, 190, 5, 'BISCOITO MARILAN RECH 335G');
        ImprimirTexto(orNormal, 2, 2, 1, 158, 5, 'CHOC BRANCO');
        ImprimirBarras(orNormal, 'F', '2', '2', 32, 0, '7896003701685', 90, becSIM);
        ImprimirTexto(orNormal, 3, 3, 2, 15, 300, 'R$');
        ImprimirTexto(orNormal, 3, 4, 4, 15, 450, '20.59');
      end
     else
      begin
        ImprimirTexto(orNormal, 2, 2, 2, 15, 55, 'BISCOITO MARILAN RECH 335G');
        ImprimirTexto(orNormal, 3, 2, 1, 60, 55, 'CHOC BRANCO');
        ImprimirBarras(orNormal, 'E30', '2', '2', 95, 55, '7896003701685', 90, becSIM);
        ImprimirTexto(orNormal, 3, 3, 2, 110, 355, 'R$');
        ImprimirTexto(orNormal, 3, 4, 5, 85, 515, '20.59');
      end ;

     Imprimir(StrToInt(eCopias.Text), StrToInt(eAvanco.Text));
     Desativar;
  end;
end;

procedure TFPrincipal.bEtqCarreirasClick(Sender: TObject);
begin
  AtivarACBrETQ;

  with ACBrETQ do
  begin
     if Modelo = etqPpla then
      begin
        ImprimirTexto(orNormal, 2, 1, 2, 180, 15, 'BISCOITO REC 335G');
        ImprimirTexto(orNormal, 2, 1, 1, 140, 15, 'CHOC BRANCO');
        ImprimirBarras(orNormal, 'F', '2', '2', 20, 10, '7896003701685', 70);

        ImprimirTexto(orNormal, 2, 1, 2, 180, 315, 'BISCOITO RECH 335G');
        ImprimirTexto(orNormal, 2, 1, 1, 140, 315, 'CHOC BRANCO');
        ImprimirBarras(orNormal, 'F', '2', '2', 20, 315, '7896003701685', 70);

        ImprimirTexto(orNormal, 2, 1, 2, 180, 620, 'BISCOITO RECH 335G');
        ImprimirTexto(orNormal, 2, 1, 1, 140, 620, 'CHOC BRANCO');
        ImprimirBarras(orNormal, 'F', '2', '2', 20, 620, '7896003701685', 70);
      end
     else
      begin
        ImprimirTexto(orNormal, 2, 1, 3, 15, 55, 'BISCOITO REC 335G');
        ImprimirTexto(orNormal, 2, 1, 1, 80, 55, 'CHOC BRANCO');
        ImprimirBarras(orNormal, 'E30', '2', '2', 120, 55, '7896003701685', 080, becSIM);

        ImprimirTexto(orNormal, 2, 1, 3, 15, 365, 'BISCOITO RECH 335G');
        ImprimirTexto(orNormal, 2, 1, 1, 80, 365, 'CHOC BRANCO');
        ImprimirBarras(orNormal, 'E30', '2', '2', 120, 365, '7896003701685', 080, becSIM);

        ImprimirTexto(orNormal, 2, 1, 3, 15, 670, 'BISCOITO RECH 335G');
        ImprimirTexto(orNormal, 2, 1, 1, 80, 670, 'CHOC BRANCO');
        ImprimirBarras(orNormal, 'E30', '2', '2', 120, 670, '7896003701685', 080, becSIM);
      end ;

     Imprimir(StrToInt(eCopias.Text), StrToInt(eAvanco.Text));
     Desativar;
  end;
end;

procedure TFPrincipal.bImprimirImagemClick(Sender : TObject);
begin
  AtivarACBrETQ;

  with ACBrETQ do
  begin
     ImprimirImagem(1,10,10,Edit1.Text);
     Imprimir(StrToInt(eCopias.Text), StrToInt(eAvanco.Text));
     Desativar;
  end ;
end;

procedure TFPrincipal.bCarregarImgClick(Sender : TObject);
var
   MS : TMemoryStream ;
begin
  AtivarACBrETQ;

  OpenPictureDialog1.InitialDir := ExtractFileDir(Application.ExeName);

  if rbStream.Checked then
   begin
     OpenPictureDialog1.Filter := 'BMP MonoCromático|*.bmp' ;
     if OpenPictureDialog1.Execute then
     begin
        Image1.Picture.LoadFromFile(OpenPictureDialog1.FileName) ;
        MS := TMemoryStream.Create;
        try
           Image1.Picture.Bitmap.SaveToStream( MS );
           ACBrETQ.CarregarImagem( MS, Edit1.Text, True,
                                   ExtractFileExt(OpenPictureDialog1.FileName) );
        finally
           MS.Free ;
        end ;
     end ;
   end
  else
   begin
      OpenPictureDialog1.Filter := 'PCX|*.pcx|BMP MonoCromático|*.bmp|IMG|*.img' ;
      if OpenPictureDialog1.Execute then
      begin
         try
            Image1.Picture.LoadFromFile(OpenPictureDialog1.FileName);
         except
            Image1.Picture := nil;
         end ;
         ACBrETQ.CarregarImagem( OpenPictureDialog1.FileName, Edit1.Text );
      end ;
   end ;

  ACBrETQ.Desativar;
end;

procedure TFPrincipal.cbModeloChange(Sender : TObject) ;
begin
   eAvanco.Enabled := (cbModelo.ItemIndex = 1);
   ACBrETQ.Desativar;
end;

procedure TFPrincipal.eCopiasKeyPress(Sender : TObject ; var Key : char) ;
begin
   if not (Key in ['0'..'9',#8,#13]) then
      Key := #0 ;
end;

procedure TFPrincipal.AtivarACBrETQ ;
begin
  with ACBrETQ do
  begin
     DPI           := TACBrETQDPI(cbDPI.ItemIndex);
     Modelo        := TACBrETQModelo(cbModelo.ItemIndex) ;
     Porta         := cbPorta.Text ;
     LimparMemoria := ckMemoria.Checked ;

     Ativar ;
  end ;
end ;

end.

