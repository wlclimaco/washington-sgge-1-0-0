unit BrvAlertProgress;

interface

uses
  SysUtils, Classes, Forms, ComCtrls, ExtCtrls, StdCtrls, Graphics, Windows, Controls, DB,
  DBClient, SqlExpr;

type
  TBrvAlertProgress = class(TComponent)
  private
    FFrmProgressBar : TForm;
    FPrgProgresso   : TProgressBar;
    FPnlProgBar     : TPanel;
    FLblTituloApl   : TLabel;
    FLblDescProc    : TLabel;
    FImagemBravo    : TImage;
    FImagem         : TImage;
    FQuery          : TDataSet;
    FDsCaption      : String;
    FDsProcess      : String;
    procedure CriaFormAlerta(pDsTitPro, pDsProces: String);
    procedure SetQuery(Value : TDataSet);
    procedure Prepara;
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
    procedure ShowAlert;
    procedure BrStepIt(pDsCaption: String = '');
    procedure BrReset;
    procedure BrMax(pVrMax : Integer);
    procedure BrProgressInfinity(pVrMax, pTempo: Integer);
  published
    { Published declarations }
    property BrCaption      : String   read FDsCaption  write FDsCaption;
    property BrProcesso     : String   read FDsProcess  write FDsProcess;
    property BrQuery        : TDataSet read FQuery      write SetQuery;
  end;

{$R BrvAlertProgress.res}

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvAlertProgress]);
end;

procedure TBrvAlertProgress.ShowAlert;
var I, x: Integer;
    BarraIniciar: HWND; {Barra Iniciar}
    tmAltura: Integer;
    tmRect: TRect;
    xTop : Integer;
    xIniTop : Integer;
begin
      //localiza o Handle da janela iniciar
      BarraIniciar := FindWindow('Shell_TrayWnd', nil);
      //Pega o "retângulo" que envolve a barra e sua altura
      GetWindowRect(BarraIniciar, tmRect);
      tmAltura := tmRect.Bottom - tmRect.Top;
      //FrmProgressBar := TFrmProgressBar.Create(Self);
      CriaFormAlerta(BrCaption, BrProcesso);

      FFrmProgressBar.Left := Screen.Width - FFrmProgressBar.ClientWidth;

      if tmRect.Top = -2 then
      begin
            tmAltura := 30;
      end;

      //Pega o top final
      xTop := Screen.Height - FFrmProgressBar.ClientHeight - tmAltura;
      //Pega o top inicial
      xIniTop := Screen.Height + FFrmProgressBar.ClientHeight + tmAltura;
      FFrmProgressBar.Top := xIniTop;

      for I := xIniTop downto xTop do
      begin
            FFrmProgressBar.Top := FFrmProgressBar.Top - 1;
            FFrmProgressBar.Show;
            FFrmProgressBar.Update;
            Application.ProcessMessages;
            Sleep(1);
      end;
end;

procedure TBrvAlertProgress.CriaFormAlerta(pDsTitPro : String; pDsProces: String);
begin
      FFrmProgressBar := TForm.Create(Self);
      FFrmProgressBar.Left := 530;
      FFrmProgressBar.Top := 273;
      FFrmProgressBar.BorderStyle := bsNone;
      FFrmProgressBar.Caption := 'Progresso';
      FFrmProgressBar.Height := 74;
      FFrmProgressBar.Width := 350;
      FFrmProgressBar.Color := clWhite;
      FFrmProgressBar.Font.Charset := DEFAULT_CHARSET;
      FFrmProgressBar.Font.Color := clWindowText;
      FFrmProgressBar.Font.Height := -11;
      FFrmProgressBar.Font.Name := 'MS Sans Serif';
      FFrmProgressBar.FormStyle := fsStayOnTop;
      FFrmProgressBar.OldCreateOrder := False;
      FFrmProgressBar.PixelsPerInch := 96;
      FFrmProgressBar.DoubleBuffered := True;

      FPnlProgBar := TPanel.Create(FFrmProgressBar);
      FPnlProgBar.Parent := FFrmProgressBar;
      FPnlProgBar.Left := 0;
      FPnlProgBar.Top := 0;
      FPnlProgBar.Width := 300;
      FPnlProgBar.Height := 92;
      FPnlProgBar.Align := alClient;
      FPnlProgBar.BevelOuter := bvNone;
      FPnlProgBar.BorderStyle := bsSingle;
      FPnlProgBar.Color := clWhite;
      FPnlProgBar.Ctl3D := False;
      FPnlProgBar.ParentCtl3D := False;
      FPnlProgBar.TabOrder := 0;

      FImagemBravo         := TImage.Create(FFrmProgressBar);
      FImagemBravo.Parent  := FPnlProgBar;
      FImagemBravo.Left    := 8;
      FImagemBravo.Top     := 0;
      FImagemBravo.Width   := 32;
      FImagemBravo.Height  := 32;
      FImagemBravo.Picture.Bitmap.LoadFromResourceName(hInstance,'BRAVO');

      FImagem         := TImage.Create(FFrmProgressBar);
      FImagem.Parent  := FPnlProgBar;
      FImagem.Left    := 8;
      FImagem.Top     := 33;
      FImagem.Width   := 32;
      FImagem.Height  := 32;
      FImagem.Picture.Bitmap.LoadFromResourceName(hInstance,'PROGRESS');

      FPrgProgresso := TProgressBar.Create(FFrmProgressBar);
      FPrgProgresso.Parent := FPnlProgBar;
      FPrgProgresso.Left   := 48;
      FPrgProgresso.Top    := 48;
      FPrgProgresso.Width  := 265;
      FPrgProgresso.Height := 17;
      FPrgProgresso.Smooth := True;
      FPrgProgresso.TabOrder := 0;
      FPrgProgresso.Step   := 1;

      FLblTituloApl := TLabel.Create(FFrmProgressBar);
      FLblTituloApl.Parent := FPnlProgBar;
      FLblTituloApl.Left := 48;
      FLblTituloApl.Top := 5;
      FLblTituloApl.Width := 58;
      FLblTituloApl.Height := 24;
      FLblTituloApl.Caption := pDsTitPro;
      FLblTituloApl.Font.Charset := DEFAULT_CHARSET;
      FLblTituloApl.Font.Color := clNavy;
      FLblTituloApl.Font.Height := -13;
      FLblTituloApl.Font.Name := 'MS Sans Serif';
      FLblTituloApl.Font.Style := [fsBold];
      FLblTituloApl.ParentFont := False;

      FLblDescProc := TLabel.Create(FFrmProgressBar);
      FLblDescProc.Parent := FPnlProgBar;
      FLblDescProc.Left := 48;
      FLblDescProc.Top := 33;
      FLblDescProc.Width := 53;
      FLblDescProc.Height := 13;
      FLblDescProc.Caption := pDsProces;
      FLblDescProc.Font.Charset := DEFAULT_CHARSET;
      FLblDescProc.Font.Color := clWindowText;
      FLblDescProc.Font.Height := -11;
      FLblDescProc.Font.Name := 'MS Sans Serif';
      FLblDescProc.Font.Style := [fsBold];
      FLblDescProc.ParentFont := False;

end;

procedure TBrvAlertProgress.BrStepIt(pDsCaption: String = '');
begin
      if (Trim(pDsCaption) <> '') then
      begin
            BrProcesso := pDsCaption;
            FLblDescProc.Caption := pDsCaption;
            FLblDescProc.Refresh;
      end;

      FPrgProgresso.StepIt;
      FPrgProgresso.Refresh;

      if FPrgProgresso.Position = FPrgProgresso.Max then
      begin
            FreeAndNil(FFrmProgressBar);
      end;
end;

procedure TBrvAlertProgress.BrProgressInfinity(pVrMax, pTempo: Integer);
begin
      ShowAlert;
      BrMax(pVrMax);

      while true do
      begin
            BrStepIt();

            if (FPrgProgresso.Max = (FPrgProgresso.Position + 1)) then
            begin
                  Sleep(100);
                  BrReset;
            end;

            Application.ProcessMessages;
            Sleep(pTempo);
      end;

end;

procedure TBrvAlertProgress.BrReset;
begin
      FPrgProgresso.Position := 0;
      Prepara;
end;

procedure TBrvAlertProgress.BrMax(pVrMax : Integer);
begin
      BrQuery := nil;

      if (pVrMax = 0) then
      begin
            FreeAndNil(FFrmProgressBar);
      end else
      begin
            FPrgProgresso.Max := pVrMax;
            BrReset;
      end;
end;

procedure TBrvAlertProgress.Prepara;
begin
      if (FQuery <> nil) then
      begin
            if FQuery.Active then
            begin
                  try
                      FQuery.Last;
                      FQuery.First;

                      FPrgProgresso.Max := FQuery.RecordCount;
                  except
                      FQuery.Close;
                      FQuery.Open;
                  end;
            end else
            begin
                  BrReset;
            end;
      end;
end;

procedure TBrvAlertProgress.SetQuery(Value : TDataSet);
begin
      FQuery := Value;
      Prepara;
end;

end.
