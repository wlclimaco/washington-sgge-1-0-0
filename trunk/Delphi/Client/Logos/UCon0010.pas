unit UCon0010;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, ComCtrls,
  UClaSrv, BrvExport, StdCtrls, Mask, BrvEditNum, Db, BrvListParam, ImgList, Menus;

type
  TCon0010 = class(TMov0000)
    TrvPlano: TTreeView;
    BtnExpand: TSpeedButton;
    BtnRecolh: TSpeedButton;
    BrvExport: TBrvExport;
    procedure TrvPlanoMouseDown(Sender: TObject; Button: TMouseButton; Shift: TShiftState;
      X, Y: Integer);
    procedure TrvPlanoKeyPress(Sender: TObject; var Key: Char);
    procedure BtnExpandClick(Sender: TObject);
    procedure BtnRecolhClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
    gNrPlano     : Integer;
    gTpConta     : Byte;
    gCdEmpres    : Integer;

    gEdtNrClassi : TMaskEdit;
    gEdtNrConta  : TBrvEditNum;

    gColNrClassi : TStringField;
    gColNrConta  : TFmTBcdField;
    procedure CarregarPlanoDeContas;
  end;

var
  Con0010: TCon0010;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TCon0010.CarregarPlanoDeContas;
var lConexao     : TSDmConClient;
    lDsResult    : String;
    lDsPlano     : String;
begin
      lConexao := TSDmConClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lDsPlano := lConexao.AbrirPlanoContas(DmSrvApl.BrvDicionario.Credencial,
                                                lDsResult, gNrPlano, gTpConta, 0, gCdEmpres);

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
          BrvExport.XMLToTreeView(lDsPlano, TrvPlano);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCon0010.BtnExpandClick(Sender: TObject);
begin
      TrvPlano.FullExpand;
end;

procedure TCon0010.BtnRecolhClick(Sender: TObject);
begin
      TrvPlano.FullCollapse;
end;

procedure TCon0010.TrvPlanoKeyPress(Sender: TObject; var Key: Char);
begin
      if  key = #13 then
      begin
            if gEdtNrConta <> nil then
            begin
                  gEdtNrConta.BrAsInteger := 0;
            end;

            if gEdtNrClassi <> nil then
            begin
                  gEdtNrClassi.Text := PString(TrvPlano.Selected.Data)^;
                  gEdtNrClassi.SetFocus;
            end;

            if gColNrConta <> nil then
            begin
                  gColNrConta.AsInteger := 0;
            end;

            if gColNrClassi <> nil then
            begin
                  gColNrClassi.AsString := PString(TrvPlano.Selected.Data)^;
            end;

            if FormStyle = fsNormal then
            begin
                  Close;
            end;
      end;
end;

procedure TCon0010.TrvPlanoMouseDown(Sender: TObject; Button: TMouseButton;
  Shift: TShiftState; X, Y: Integer);
begin
      if  Button = mbLeft then
      begin
            if  TrvPLano.GetNodeAt(X, Y) <> nil then
            begin
                  TrvPlano.BeginDrag(False);
            end;
      end;
end;

end.
