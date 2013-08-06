unit UCai0005;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCai0000, StdCtrls, Buttons, BrvBitBtn, ExtCtrls, BrvSpeedButton, CheckLst,
  BrvCheckListBox;

type
  TCai0005 = class(TCai0000)
    Panel3: TPanel;
    SbtOrdem: TBrvSpeedButton;
    BrvSpeedButton1: TBrvSpeedButton;
    LbxAtribu: TBrvCheckListBox;
    procedure SbtOrdemClick(Sender: TObject);
    procedure BrvSpeedButton1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Cai0005: TCai0005;

implementation

{$R *.dfm}

procedure TCai0005.BrvSpeedButton1Click(Sender: TObject);
var lDsProxim : String;
    lVrProxim : String;
    lNrAtual  : Integer;
    lNrProxim : Integer;

    lSnChePro : Boolean;
    lSnCheAtu : Boolean;
begin
      if (LbxAtribu.ItemIndex < (LbxAtribu.Items.Count - 1)) and
         (LbxAtribu.ItemIndex > -1) then
      begin
            lNrAtual  := LbxAtribu.ItemIndex;
            lNrProxim := LbxAtribu.ItemIndex + 1;

            lSnChePro := LbxAtribu.Checked[lNrProxim];
            lSnCheAtu := LbxAtribu.Checked[lNrAtual];

            lDsProxim := LbxAtribu.Items.Strings[lNrProxim];
            lVrProxim := LbxAtribu.Values.Strings[lNrProxim];

            LbxAtribu.Items.Strings[lNrProxim]  := LbxAtribu.Items.Strings[lNrAtual];
            LbxAtribu.Values.Strings[lNrProxim] := LbxAtribu.Values.Strings[lNrAtual];

            LbxAtribu.Items.Strings[lNrAtual]  := lDsProxim;
            LbxAtribu.Values.Strings[lNrAtual] := lVrProxim;

            LbxAtribu.Checked[lNrProxim] := lSnCheAtu;
            LbxAtribu.Checked[lNrAtual]  := lSnChePro;

            LbxAtribu.ItemIndex := lNrProxim;
      end;
end;

procedure TCai0005.SbtOrdemClick(Sender: TObject);
var lDsAnteri : String;
    lVrAnteri : String;
    lNrAtual  : Integer;
    lNrAnteri : Integer;

    lSnCheAnt : Boolean;
    lSnCheAtu : Boolean;
begin
      if LbxAtribu.ItemIndex > 0 then
      begin
            lNrAtual  := LbxAtribu.ItemIndex;
            lNrAnteri := LbxAtribu.ItemIndex - 1;

            lSnCheAnt := LbxAtribu.Checked[lNrAnteri];
            lSnCheAtu := LbxAtribu.Checked[lNrAtual];

            lDsAnteri := LbxAtribu.Items.Strings[lNrAnteri];
            lVrAnteri := LbxAtribu.Values.Strings[lNrAnteri];

            LbxAtribu.Items.Strings[lNrAnteri]  := LbxAtribu.Items.Strings[lNrAtual];
            LbxAtribu.Values.Strings[lNrAnteri] := LbxAtribu.Values.Strings[lNrAtual];

            LbxAtribu.Items.Strings[lNrAtual]  := lDsAnteri;
            LbxAtribu.Values.Strings[lNrAtual] := lVrAnteri;

            LbxAtribu.Checked[lNrAnteri] := lSnCheAtu;
            LbxAtribu.Checked[lNrAtual]  := lSnCheAnt;

            LbxAtribu.ItemIndex := lNrAnteri;
      end;
end;

end.
