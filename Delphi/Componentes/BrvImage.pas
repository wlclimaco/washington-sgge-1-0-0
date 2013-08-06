unit BrvImage;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  ExtDlgs, ExtCtrls, Jpeg;   // utilizado para visualização da imagem

type
  TBrvImage = class(TComponent)
  private
    { Private declarations }
    procedure VisualizadorImagemKeyUp(Sender: TObject; var Key: Word;
                                                            Shift: TShiftState);
  protected
    { Protected declarations }
  public
    { Public declarations }
    procedure VisualizarImagem(NmImagem : String);
  published
    { Published declarations }
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvImage]);
end;

procedure TBrvImage.VisualizarImagem(NmImagem : String);
var    FrmImagem  : TForm;
       ImgFoto    : TImage;
begin
      if  NmImagem = '' then
      begin
            MessageDlg('Não foi informada nenhuma imagem para visualização',
                                                      mtInformation, [mbOk], 0)
      end else
      begin
            try
                FrmImagem               := TForm.CreateNew(Self);
                FrmImagem.Position      := poScreenCenter;
                FrmImagem.BorderIcons   := [biSystemMenu];
                FrmImagem.BorderStyle   := bsSingle;
                FrmImagem.KeyPreview    := True;
                FrmImagem.OnKeyUp       := VisualizadorImagemKeyUp;

                ImgFoto                 := TImage.Create(Self);
                ImgFoto.Parent          := FrmImagem;
                ImgFoto.Autosize        := True;

                try
                    ImgFoto.Picture.LoadFromFile(NmImagem);
                except
                    Raise Exception.Create('Não foi possível visualizar a ' +
                                           'imagem ' + #13#13 + NmImagem);
                end;

                FrmImagem.Width         := ImgFoto.Width  + 13;
                FrmImagem.Height        := ImgFoto.Height + 30;

                FrmImagem.ShowModal;
            finally
                FreeAndNil(ImgFoto);
                FreeAndNil(FrmImagem);
            end;
      end;
end;

procedure TBrvImage.VisualizadorImagemKeyUp(Sender: TObject; var Key: Word;
                                                            Shift: TShiftState);
begin
      case key of
           27 : TForm(Sender).Close;
      end;
end;

end.
