unit UMov0031;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  StdCtrls, BrvRetCon, BrvCustomEdit, BrvEditNum, BrvComboBox, BrvDBComboListBox, Mask, DBCtrls,
  BrvDbEdit, BrvBitBtn, DB, DBClient, BrvClientDataSet;

type
  TMov0031 = class(TMov0000)
    PnlRegistro: TPanel;
    Label11: TLabel;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    ClbTpOpeNot: TBrvDBComboListBox;
    ClbTpCarga: TBrvDBComboListBox;
    ClbSnVazame: TBrvDBComboListBox;
    ClbTpVazEmb: TBrvDBComboListBox;
    ClbSnReemba: TBrvDBComboListBox;
    ClbTpDireci: TBrvDBComboListBox;
    Label8: TLabel;
    Label10: TLabel;
    EdtNmMotori: TBrvDbEdit;
    Label12: TLabel;
    EdtNrPlaVei: TBrvDbEdit;
    Label13: TLabel;
    ClbSnMotEnt: TBrvDBComboListBox;
    Label14: TLabel;
    Panel6: TPanel;
    BtnConfirmar: TBrvBitBtn;
    BtnCancelar: TBrvBitBtn;
    EdtCdTitula: TBrvDbEdit;
    EdtCdMotori: TBrvDbEdit;
    CpQ004: TBrvClientDataSet;
    DsQ004: TDataSource;
    LblRsTitula: TBrvRetCon;
    EdtCdVeicul: TBrvDbEdit;
    procedure BtnCancelarClick(Sender: TObject);
    procedure BtnConfirmarClick(Sender: TObject);
    procedure EdtCdMotoriBrOnAfterConsulta(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
    procedure AlimentaDominios();
    procedure AlimentaLabels();

  end;

var
  Mov0031: TMov0031;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TMov0031.BtnConfirmarClick(Sender: TObject);
begin
      inherited;
      ModalResult := mrOk;
end;

procedure TMov0031.BtnCancelarClick(Sender: TObject);
begin
      inherited;
      if MessageDlg('Deseja realmente cancelar?', mtConfirmation, [mbYes, mbNo], 0) = mrYes then
      begin
            ModalResult := mrCancel;
      end;
end;

procedure TMov0031.EdtCdMotoriBrOnAfterConsulta(Sender: TObject);
begin
      inherited;
      if ((EdtCdMotori.Text <> '') and (StrToInt(EdtCdMotori.Text) > 0)) then
      begin
            EdtNmMotori.ReadOnly := True;
            EdtNmMotori.Color    := $00DDE2E3;
      end else
      begin
            EdtNmMotori.ReadOnly := False;
            EdtNmMotori.Color    := clWhite;
      end;
end;

procedure TMov0031.AlimentaDominios;
var lSglDsDom: TStrings;
    lSglVrDom: TStrings;
begin
      inherited;
      lSglDsDom := TStringList.Create;
      lSglVrDom := TStringList.Create;
      try
          DmSrvApl.BrvDicionario.AtributoDominioValores('Q004', 'TPOPENOT', lSglDsDom, lSglVrDom);
          ClbTpOpeNot.Items.Text  := lSglDsDom.Text;
          ClbTpOpeNot.Values.Text := lSglVrDom.Text;

          lSglDsDom.Clear;
          lSglVrDom.Clear;
          DmSrvApl.BrvDicionario.AtributoDominioValores('Q004', 'TPCARGA', lSglDsDom, lSglVrDom);
          ClbTpCarga.Items.Text  := lSglDsDom.Text;
          ClbTpCarga.Values.Text := lSglVrDom.Text;

          lSglDsDom.Clear;
          lSglVrDom.Clear;
          DmSrvApl.BrvDicionario.AtributoDominioValores('Q004', 'SNVAZAME', lSglDsDom, lSglVrDom);
          ClbSnVazame.Items.Text  := lSglDsDom.Text;
          ClbSnVazame.Values.Text := lSglVrDom.Text;

          lSglDsDom.Clear;
          lSglVrDom.Clear;
          DmSrvApl.BrvDicionario.AtributoDominioValores('Q004', 'TPVAZEMB', lSglDsDom, lSglVrDom);
          ClbTpVazEmb.Items.Text  := lSglDsDom.Text;
          ClbTpVazEmb.Values.Text := lSglVrDom.Text;

          lSglDsDom.Clear;
          lSglVrDom.Clear;
          DmSrvApl.BrvDicionario.AtributoDominioValores('Q004', 'SNREEMBA', lSglDsDom, lSglVrDom);
          ClbSnReemba.Items.Text  := lSglDsDom.Text;
          ClbSnReemba.Values.Text := lSglVrDom.Text;

          lSglDsDom.Clear;
          lSglVrDom.Clear;
          DmSrvApl.BrvDicionario.AtributoDominioValores('Q004', 'TPDIRECI', lSglDsDom, lSglVrDom);
          ClbTpDireci.Items.Text  := lSglDsDom.Text;
          ClbTpDireci.Values.Text := lSglVrDom.Text;

          lSglDsDom.Clear;
          lSglVrDom.Clear;
          DmSrvApl.BrvDicionario.AtributoDominioValores('Q004', 'SNMOTENT', lSglDsDom, lSglVrDom);
          ClbSnMotEnt.Items.Text  := lSglDsDom.Text;
          ClbSnMotEnt.Values.Text := lSglVrDom.Text;

          if ((EdtCdMotori.Text <> '') and (StrToInt(EdtCdMotori.Text) > 0)) then
          begin
                EdtNmMotori.ReadOnly := True;
                EdtNmMotori.Color    := $00DDE2E3;
          end;

          if ((CpQ004.RecordCount > 0) and
              (CpQ004.FieldByName('CdUsuEmi').AsInteger <> DmSrvApl.BrvDicionario.UserCode)) then
          begin
                CpQ004.ReadOnly := True;

                PnlRegistro.Enabled         := False;
                EdtCdTitula.Color           := $00DDE2E3;
                EdtCdTitula.BrVisibleButton := False;
                EdtCdMotori.Color           := $00DDE2E3;
                EdtCdMotori.BrVisibleButton := False;
                EdtCdVeicul.Color           := $00DDE2E3;
                EdtCdVeicul.BrVisibleButton := False;
                EdtNmMotori.Color           := $00DDE2E3;
                EdtNrPlaVei.Color           := $00DDE2E3;
                ClbTpOpeNot.Color           := $00DDE2E3;
                ClbTpCarga.Color            := $00DDE2E3;
                ClbSnVazame.Color           := $00DDE2E3;
                ClbTpVazEmb.Color           := $00DDE2E3;
                ClbSnReemba.Color           := $00DDE2E3;
                ClbTpDireci.Color           := $00DDE2E3;
                ClbSnMotEnt.Color           := $00DDE2E3;
                BtnConfirmar.Enabled        := False;
          end else
          begin
                CpQ004.ReadOnly := False;

                PnlRegistro.Enabled         := True;
                EdtCdTitula.Color           := clWhite;
                EdtCdTitula.BrVisibleButton := True;
                EdtCdMotori.Color           := clWhite;
                EdtCdMotori.BrVisibleButton := True;
                EdtCdVeicul.Color           := clWhite;
                EdtCdVeicul.BrVisibleButton := True;
                EdtNmMotori.Color           := clWhite;
                EdtNrPlaVei.Color           := clWhite;
                ClbTpOpeNot.Color           := clWhite;
                ClbTpCarga.Color            := clWhite;
                ClbSnVazame.Color           := clWhite;
                ClbTpVazEmb.Color           := clWhite;
                ClbSnReemba.Color           := clWhite;
                ClbTpDireci.Color           := clWhite;
                ClbSnMotEnt.Color           := clWhite;
                BtnConfirmar.Enabled        := True;
          end;

          CpQ004.First;
      finally
           FreeAndNil(lSglDsDom);
           FreeAndNil(lSglVrDom);
      end;
end;

procedure TMov0031.AlimentaLabels;
begin
      LblRsTitula.Text := CpQ004.FieldByName('RsTitula').Text;
end;

initialization
      RegisterClass(TMov0031);

finalization
      UnRegisterClass(TMov0031);

end.
