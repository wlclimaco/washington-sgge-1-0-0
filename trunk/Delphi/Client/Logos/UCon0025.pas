unit UCon0025;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCad0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, ComCtrls, DB,
  DBClient, BrvClientDataSet, Menus, StdCtrls, ImgList, Mask, DBCtrls, BrvDBComboListBox,
  BrvDbEdit, UClaSrv, Grids, BrvDbGrids, BrvDbGrid, BrvListParam, BrvBitBtn, DBGrids, BrvString,
  UMov0000, BrvExport;

type
  TCon0025 = class(TMov0000)
    TrvClaRnc: TTreeView;
    BtnRecolh: TSpeedButton;
    BtnExpand: TSpeedButton;
    BrvExport: TBrvExport;
    procedure TrvClaRncMouseDown(Sender: TObject; Button: TMouseButton; Shift: TShiftState; X,
      Y: Integer);
    procedure TrvClaRncKeyPress(Sender: TObject; var Key: Char);
    procedure BtnRecolhClick(Sender: TObject);
    procedure BtnExpandClick(Sender: TObject);
  private
    { Private declarations }
  public
    gNrPlano     : Integer;
    gTpConta     : Byte;
    gCdEmpres    : Integer;

    gEdtNrClassi : TMaskEdit;

    gColNrClassi : TStringField;
    gColNrConta  : TFmTBcdField;
    gNdNode : PString;
    procedure CarregarClassificacaoRNC(lNdPai : TTreeNode;pNrMenPai : Integer);
  end;

var
  Con0025: TCon0025;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TCon0025.CarregarClassificacaoRNC(lNdPai : TTreeNode;pNrMenPai : Integer);
var lNdNovo      : TTreeNode;
    lQpClassRnc  : TclientDataset;
begin
      try
          lQpClassRnc      := TclientDataset.Create(self);
          lQpClassRnc.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro( 233, '<%DsWhere%>;' +
                                                                   ' Where coalesce(cdclapai,0) =  '
                                                                   + IntToStr(pNrMenPai), Name);
          while not lQpClassRnc.Eof do
          begin
                new(gNdNode);
                lNdNovo := TrvClaRnc.Items.AddChildObject(lNdPai,
                                       lQpClassRnc.FieldByName('DsContro').AsString + ' ' +
                                       lQpClassRnc.FieldByName('DsClaRNC').AsString, gNdNode);
                lNdNovo.ImageIndex     := 0;
                lNdNovo.SelectedIndex  := 0;
                PString(lNdNovo.Data)^ := lQpClassRnc.FieldByName('CdClaRNC').AsString;
                CarregarClassificacaoRNC(lNdNovo,lQpClassRnc.FieldByName('CdClaRNC').AsInteger);
                lQpClassRnc.Next;
          end;
      finally
            FreeAndNil(lQpClassRnc);
      end;
end;

procedure TCon0025.BtnExpandClick(Sender: TObject);
begin
      TrvClaRnc.FullExpand;
end;

procedure TCon0025.BtnRecolhClick(Sender: TObject);
begin
      TrvClaRnc.FullCollapse;
end;

procedure TCon0025.TrvClaRncKeyPress(Sender: TObject; var Key: Char);
begin
      if  key = #13 then
      begin

            if gEdtNrClassi <> nil then
            begin
                  gEdtNrClassi.Text := PString(TrvClaRnc.Selected.Data)^;
                  gEdtNrClassi.SetFocus;
            end;

            if gColNrConta <> nil then
            begin
                  gColNrConta.AsInteger := 0;
            end;

            if gColNrClassi <> nil then
            begin
                  gColNrClassi.AsString := PString(TrvClaRnc.Selected.Data)^;
            end;

            if FormStyle = fsNormal then
            begin
                  Close;
            end;
      end;
end;

procedure TCon0025.TrvClaRncMouseDown(Sender: TObject; Button: TMouseButton; Shift: TShiftState; X,
  Y: Integer);
begin
      if  Button = mbLeft then
      begin
            if  TrvClaRnc.GetNodeAt(X, Y) <> nil then
            begin
                  TrvClaRnc.BeginDrag(False);
            end;
      end;

end;

end.
