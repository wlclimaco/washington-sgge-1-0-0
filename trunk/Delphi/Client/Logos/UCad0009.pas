unit UCad0009;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCad0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, BrvDbEdit, ComCtrls, StdCtrls,
  BrvDBComboListBox, Mask, DBCtrls, BrvBitBtn;

type
  TCad0009 = class(TCad0000)
    Panel1: TPanel;
    Splitter1: TSplitter;
    PnlRodape: TPanel;
    BtnExpand: TBitBtn;
    BtnColaps: TBitBtn;
    PnlConta: TPanel;
    Label3: TLabel;
    Label4: TLabel;
    Label8: TLabel;
    DBENmConta: TDBEdit;
    CmbCdNatCon: TBrvDBComboListBox;
    BtnGravar: TBrvBitBtn;
    BtnCancel: TBrvBitBtn;
    BotExclui: TBrvBitBtn;
    TrvDRE: TTreeView;
    DBENrConta: TDBEdit;
    Label1: TLabel;
    DBEdit1: TDBEdit;
    Label2: TLabel;
    BrvDbEdit1: TBrvDbEdit;
    procedure BtnExpandClick(Sender: TObject);
    procedure BtnColapsClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Cad0009: TCad0009;

implementation

{$R *.dfm}

procedure TCad0009.BtnColapsClick(Sender: TObject);
begin
      inherited;
      TrvDRE.FullCollapse;
end;

procedure TCad0009.BtnExpandClick(Sender: TObject);
begin
      inherited;
      TrvDRE.FullExpand;
end;

end.
