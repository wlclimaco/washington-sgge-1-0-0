unit UCai0008;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  ExtCtrls, StdCtrls, Buttons, jpeg, ComCtrls, BrvBitBtn;

type
  TCai0008 = class(TForm)
    Panel1: TPanel;
    ImgIcone: TImage;
    Label6: TLabel;
    Label7: TLabel;
    LblDsSistem: TLabel;
    LblNrVersao: TLabel;
    BbtOk: TBrvBitBtn;
    Label8: TLabel;
    LblDtCompil: TLabel;
    Label1: TLabel;
    Label10: TLabel;
    LblDsServid: TLabel;
    LblNrVerSer: TLabel;
    Panel2: TPanel;
    Image2: TImage;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    Label9: TLabel;
    Label11: TLabel;
    Label12: TLabel;
    Label15: TLabel;
    Label16: TLabel;
    procedure FormCreate(Sender: TObject);
    procedure BbtOkClick(Sender: TObject);
    procedure FormKeyUp(Sender: TObject; var Key: Word;
      Shift: TShiftState);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Cai0008: TCai0008;

implementation

{$R *.DFM}

procedure TCai0008.FormCreate(Sender: TObject);
begin
      ImgIcone.Picture.Icon := Application.Icon;
end;

procedure TCai0008.BbtOkClick(Sender: TObject);
begin
      Close;
end;

procedure TCai0008.FormKeyUp(Sender: TObject; var Key: Word;
  Shift: TShiftState);
begin
      if  key = 27 then
      begin
            Close;
      end;
end;

initialization
      RegisterClass(TCai0008);

finalization
      UnRegisterClass(TCai0008);
end.
