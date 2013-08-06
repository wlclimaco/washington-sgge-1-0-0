unit BrvLogScreen;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls,
  ExtCtrls, StdCtrls, ComCtrls, Jpeg, Forms, Db, DbClient;

type
  TBrvLogScreen = class(TComponent)
  private
    gCdsImagem : TClientDataSet;
    procedure CapturaTela(var pBmpScreen: TBitMap;
                              pNrPosLef : Integer; pNrPosTop : Integer; pNrPosWid : Integer;
                              pNrPosHei : Integer);

    procedure PrintScreen(pNrImagem: Integer;
                        pNrPosLef : Integer; pNrPosTop : Integer; pNrPosWid : Integer;
                        pNrPosHei : Integer);
    procedure ProcessarImagens(pObjPrinci: TObject; pForm: TForm; var pNrImagem: Integer);
    procedure CriarTabelaTemporaria;
    function AlturaDesktop: Integer;
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }    function Execute(pObjPrinci: TObject; pForm : TForm) : OleVariant;
  published
    { Published declarations }
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvLogScreen]);
end;

function TBrvLogScreen.AlturaDesktop: Integer;
var BarraIniciar: HWND; {Barra Iniciar}
    tmAltura: Integer;
    tmRect: TRect;
begin
      BarraIniciar := FindWindow('Shell_TrayWnd', nil);
      GetWindowRect(BarraIniciar, tmRect);
      tmAltura := tmRect.Bottom - tmRect.Top;

      if tmRect.Top = -2 then
      begin
            tmAltura := 30;
      end;

      Result := Screen.Height - tmAltura;
end;


function TBrvLogScreen.Execute(pObjPrinci : TObject; pForm : TForm) : OleVariant;
var lNrImagem  : Integer;
begin
      lNrImagem  := 0;

      pForm.Refresh;

      try
          CriarTabelaTemporaria;

          inc(lNrImagem);
          PrintScreen(lNrImagem, 0, 0, Screen.Width, AlturaDesktop {Screen.Height});

          ProcessarImagens(pObjPrinci, pForm, lNrImagem);

          Result := gCdsImagem.Data;
      finally
          FreeAndNil(gCdsImagem);
      end;
end;

procedure TBrvLogScreen.CriarTabelaTemporaria;
begin
      gCdsImagem := TClientDataSet.Create(Self);
      gCdsImagem.FieldDefs.Clear;
      gCdsImagem.FieldDefs.Add('NrImagem',   FtInteger, 0, False);
      gCdsImagem.FieldDefs.Add('ImImagem',   FtBlob,    0, False);
      gCdsImagem.CreateDataSet;
      gCdsImagem.Open;
end;

procedure TBrvLogScreen.ProcessarImagens(pObjPrinci : TObject; pForm : TForm;
                                     var pNrImagem  : Integer);
var lNrObjeto : Integer;
    lNrTabShe : Integer;
    lPageCont : TPageControl;
    lDsTabAtu : TTabSheet;

    lTtTop    : Integer;
    lTtLeft   : Integer;
begin
      for lNrObjeto := 0 to pForm.ComponentCount -1 do
      begin
            if (pForm.Components[lNrObjeto] is TControl) and
               ((pForm.Components[lNrObjeto] as TControl).Parent =
                                                           (pObjPrinci as TControl)) then
            begin
                  if (pForm.Components[lNrObjeto] is TPageControl) then
                  begin
                        lPageCont := (pForm.Components[lNrObjeto] as TPageControl);
                        lDsTabAtu := lPageCont.ActivePage;

                        for lNrTabShe := 0 to lPageCont.PageCount -1 do
                        begin
                              lPageCont.ActivePage := lPageCont.Pages[lNrTabShe];
                              inc(pNrImagem);

                              PrintScreen(pNrImagem, lPageCont.ClientOrigin.X,
                                                     lPageCont.ClientOrigin.Y,
                                                     lPageCont.Width,
                                                     lPageCont.Height);

                              ProcessarImagens(lPageCont.Pages[lNrTabShe],
                                                                        pForm, pNrImagem);
                        end;

                        lPageCont.ActivePage := lDsTabAtu;
                  end;
            end;
      end;
end;

procedure TBrvLogScreen.PrintScreen(pNrImagem : Integer;
                        pNrPosLef : Integer; pNrPosTop : Integer; pNrPosWid : Integer;
                        pNrPosHei : Integer);
var JPeg      : TJPEGImage;
    BmpScreen : TBitMap;
    lImage    : TMemoryStream;
begin
      try
           BmpScreen := TBitMap.Create;
           CapturaTela(BmpScreen, pNrPosLef, pNrPosTop, pNrPosWid, pNrPosHei);

           // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=
           // =-=-=- convertendo para JPG
           // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=
              try
                  lImage                  := TMemoryStream.Create;
                  JPeg                    := TJPegImage.Create;
                  JPeg.Grayscale          := True;
                  //JPeg.CompressionQuality := 7; // 7%
                    JPeg.CompressionQuality := 15; // 15%

                  JPeg.Assign(BmpScreen);
                  JPeg.Compress;
                  JPeg.SaveToStream(lImage);

                  gCdsImagem.Append;
                  gCdsImagem.FieldByName('NrImagem').AsInteger := pNrImagem;
                  (gCdsImagem.FieldByName('ImImagem') as TBlobField).LoadFromStream(lImage);
                  gCdsImagem.Post;
              finally
                  JPeg.Free;
                  lImage.Free;
              end;
      finally
           BmpScreen.Destroy;
      end;
end;

procedure TBrvLogScreen.CapturaTela(var pBmpScreen : TBitMap;
                        pNrPosLef : Integer; pNrPosTop : Integer; pNrPosWid : Integer;
                        pNrPosHei : Integer);
var ScreenDc : HDC;
    ARect    : TRect;
begin
   // =-=-=-=-= capturando o desktop como se estive sido pressionada a tecla PrintScreen
      ARect := Rect(pNrPosLef, pNrPosTop, pNrPosWid, pNrPosHei);

      with pBmpScreen, ARect do
      begin
            Width  := pNrPosWid;
            Height := pNrPosHei;
            ScreenDc := GetDc(0);

            try
                BitBlt(Canvas.Handle, 0, 0, Screen.Width, Screen.Height,
                       ScreenDc, Left, Top, SRCCOPY);
            finally
                ReleaseDc(0, ScreenDC);
            end;
      end;
end;


end.
