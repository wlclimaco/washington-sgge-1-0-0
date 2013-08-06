unit UCai0004;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCai0000, StdCtrls, Buttons, BrvBitBtn, ExtCtrls, BrvComboBox;

type
  TCai0004 = class(TCai0000)
    Label1: TLabel;
    CbxTpOperac: TBrvComboBox;
    Label2: TLabel;
    CbxVrDefaul: TBrvComboBox;
    LblVrFixo: TLabel;
    EdtVrDefaul: TEdit;
    BrvBitBtn1: TBrvBitBtn;
    procedure FormCreate(Sender: TObject);
    procedure CbxVrDefaulChange(Sender: TObject);
    procedure BbtOkClick(Sender: TObject);
    procedure BrvBitBtn1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
      gTpDefaul  : String;
      gVrDefaul  : String;
  end;

var
  Cai0004: TCai0004;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TCai0004.BbtOkClick(Sender: TObject);
begin
      if CbxTpOperac.ItemIndex < 0 then
      begin
            raise Exception.Create('O tipo de operação deve ser informado');
      end;

      if CbxVrDefaul.ItemIndex < 0 then
      begin
            raise Exception.Create('O Valor defaul deve ser informado');
      end;

      if LblVrFixo.Font.Color = clBlue then
      begin
            if EdtVrDefaul.Text = '' then
            begin
                  raise Exception.Create('O valor fixo deve ser determinado');
            end;

            gVrDefaul := EdtVrDefaul.Text;
      end else
      begin
            gVrDefaul := CbxVrDefaul.Values.Strings[CbxVrDefaul.ItemIndex];
      end;

      gTpDefaul := CbxTpOperac.Values.Strings[CbxTpOperac.ItemIndex];

      ModalResult := mrOk;
end;

procedure TCai0004.BrvBitBtn1Click(Sender: TObject);
begin
      gVrDefaul := '';
      gTpDefaul := '';
      ModalResult := MrOk;
end;

procedure TCai0004.CbxVrDefaulChange(Sender: TObject);
begin
      if CbxVrDefaul.Values.Strings[CbxVrDefaul.ItemIndex] = '<%FIXO%>' then
      begin
            LblVrFixo.Font.Color := clBlue;
      end else
      begin
            LblVrFixo.Font.Color := clBlack;
      end;
end;

procedure TCai0004.FormCreate(Sender: TObject);
var lDsDomini : TStrings;
    lVrDomini : TStrings;
begin
      inherited;
      DmSrvApl.BrvDicionario.AtributoDominioValores(
                                                'S015', 'TPDEFAUL', lDsDomini, lVrDomini);

      CbxTpOperac.Items.Text  := lDsDomini.Text;
      CbxTpOperac.Values.Text := lVrDomini.Text;

      DmSrvApl.BrvDicionario.AtributoDominioValores(
                                                'S015', 'VRDEFAUL', lDsDomini, lVrDomini);

      CbxVrDefaul.Items.Text  := lDsDomini.Text;
      CbxVrDefaul.Values.Text := lVrDomini.Text;
end;

end.
