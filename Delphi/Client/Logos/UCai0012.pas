unit UCai0012;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCai0000, SevenZipVCL, StdCtrls, Buttons, BrvBitBtn, ExtCtrls, ComCtrls;

type
  TCai0012 = class(TCai0000)
    SevenZip: TSevenZip;
    procedure SevenZipPreProgress(Sender: TObject; MaxProgress: Int64);
    procedure SevenZipProgress(Sender: TObject; Filename: WideString; FilePosArc,
      FilePosFile: Int64);
  private
    { Private declarations }
    gPgbProces : TProgressBar;
  public
    { Public declarations }
    procedure ExtrairArquivo(pNmArqCom  : String; pNmDirCom : String;
                             pPgbProces : TProgressBar);
  end;

var
  Cai0012: TCai0012;

implementation

{$R *.dfm}

procedure TCai0012.ExtrairArquivo(pNmArqCom : String; pNmDirCom: String;
                                  pPgbProces : TProgressBar);
begin
      gPgbProces := pPgbProces;

      SevenZip.SZFileName  := pNmArqCom;
      SevenZip.ExtrBaseDir := pNmDirCom;
      SevenZip.Files.clear;
      Sevenzip.Extract;
end;

procedure TCai0012.SevenZipPreProgress(Sender: TObject; MaxProgress: Int64);
begin
      if Maxprogress > 0 then
      begin
            gPgbProces.Max := Maxprogress;
      end;
end;

procedure TCai0012.SevenZipProgress(Sender: TObject; Filename: WideString; FilePosArc,
  FilePosFile: Int64);
begin
      gPgbProces.Position := FilePosFile;
end;

end.
