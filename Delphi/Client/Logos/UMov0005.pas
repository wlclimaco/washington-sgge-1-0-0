unit UMov0005;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvEditNum,
  BrvBitBtn, Grids, BrvDbGrids, BrvDbGrid, DBGrids, Db, DBClient, BrvListParam, ImgList, Menus;

type
  TMov0005 = class(TMov0000)
    PnlCabeca: TPanel;
    Label2: TLabel;
    LblDsDebCre: TLabel;
    Panel1: TPanel;
    BrvBitBtn1: TBrvBitBtn;
    BrvBitBtn2: TBrvBitBtn;
    Label1: TLabel;
    EdtTtRateio: TBrvEditNum;
    DBGLancto: TBrvDBGrid;
    DsTemp: TDataSource;
    TblTemp: TClientDataSet;
    TblTempNrConta: TIntegerField;
    TblTempNmConta: TStringField;
    TblTempVrLancto: TFloatField;
    TblTempNrClassi: TStringField;
    TblTempCdHistor: TIntegerField;
    TblTempDsHistor: TStringField;
    TblTempDsComHis: TMemoField;
    BrvBitBtn3: TBrvBitBtn;
    procedure DBGLanctoColumns0BrOnBeforeConsul(Sender: TObject);
    procedure BrvBitBtn1Click(Sender: TObject);
    procedure BrvBitBtn2Click(Sender: TObject);
    procedure TblTempAfterCancel(DataSet: TDataSet);
    procedure TblTempAfterDelete(DataSet: TDataSet);
    procedure TblTempAfterPost(DataSet: TDataSet);
    procedure TblTempBeforeDelete(DataSet: TDataSet);
    procedure TblTempBeforeEdit(DataSet: TDataSet);
    procedure TblTempBeforePost(DataSet: TDataSet);
    procedure DBGLanctoColumns0BrOnAfterConsul(Sender: TObject);
    procedure BrvBitBtn3Click(Sender: TObject);
    procedure DBGLanctoColExit(Sender: TObject);
  private
    { Private declarations }
    gVrLanAnt : Real;
  public
    { Public declarations }
    gCdHistor : Integer;
    gNrPlano  : Integer;
  end;

var
  Mov0005: TMov0005;

implementation

uses UDmSrvApl, UDmCtb, UDmSis, ULogos;

{$R *.dfm}

procedure TMov0005.BrvBitBtn1Click(Sender: TObject);
begin
      TblTemp.Cancel;
      TblTemp.EmptyDataSet;
      EdtTtRateio.BrAsFloat := 0;
end;

procedure TMov0005.BrvBitBtn2Click(Sender: TObject);
begin
      if TblTemp.State in [dsInsert, DsEdit] then
      begin
            TblTemp.Post;
      end;

      ModalResult := mrOk;
end;

procedure TMov0005.BrvBitBtn3Click(Sender: TObject);
begin
      if MessageDlg('Cancela todas as alterações e retorna para o formulário anterior?',
                                           mtConfirmation, [mbYes, mbNo], 0) = IdYes then
      begin
            ModalResult := mrCancel;
      end;
end;

procedure TMov0005.DBGLanctoColExit(Sender: TObject);
begin
      if UpperCase(DBGLancto.SelectedField.FieldName) = 'NRCONTA' then
      begin
            if not (TblTemp.State in [DsInsert, DsEdit]) then
            begin
                  TblTemp.Edit;
            end;

            TblTemp.FieldByName('NrClassi').AsString := '';
            TblTemp.FieldByName('NmConta').AsString  := '';
            DBGLanctoColumns0BrOnAfterConsul(nil);
      end;
end;

procedure TMov0005.DBGLanctoColumns0BrOnAfterConsul(Sender: TObject);
var  lNrConta  : Integer;
     lNrClassi : String;
     lNmConta  : String;
begin
      if (TblTemp.FieldByName('NrConta').AsInteger <> 0) or
         (TblTemp.FieldByName('NrClassi').AsString <> '') then
      begin
            lNrConta    :=  TblTemp.FieldByName('NrConta').AsInteger;
            lNrClassi   :=  TblTemp.FieldByName('NrClassi').AsString;

            DmCtb.PesquisarConta(lNrConta, lNrClassi, lNmConta, gNrPlano,
                                 TblTemp.FieldByName('NrClassi').EditMask);

            TblTemp.FieldByName('NmConta').AsString  :=  lNmConta;
            TblTemp.FieldByName('NrConta').AsInteger :=  lNrConta;
            TblTemp.FieldByName('NrClassi').AsString :=  lNrClassi;
      end;
end;

procedure TMov0005.DBGLanctoColumns0BrOnBeforeConsul(Sender: TObject);
begin
      if not (TblTemp.State in [DsInsert, DsEdit]) then
      begin
            TblTemp.Edit;
      end;

      DmCtb.ConsultarPlanoContas(gNrPlano, 1, nil, nil, True,
                                                         TblTempNrClassi, TblTempNrConta, 0);
end;

procedure TMov0005.TblTempAfterCancel(DataSet: TDataSet);
begin
      EdtTtRateio.BrAsFloat := EdtTtRateio.BrAsFloat + gVrLanAnt;
      gVrLanAnt    := 0;
end;

procedure TMov0005.TblTempAfterDelete(DataSet: TDataSet);
begin
      EdtTtRateio.BrAsFloat := EdtTtRateio.BrAsFloat - gVrLanAnt;
      gVrLanAnt    := 0;
end;

procedure TMov0005.TblTempAfterPost(DataSet: TDataSet);
begin
      EdtTtRateio.BrAsFloat := EdtTtRateio.BrAsFloat + TblTempVRLANCTO.AsFloat;
      gVrLanAnt    := 0;
end;

procedure TMov0005.TblTempBeforeDelete(DataSet: TDataSet);
begin
      gVrLanAnt := TblTempVRLANCTO.AsFloat;
end;

procedure TMov0005.TblTempBeforeEdit(DataSet: TDataSet);
begin
      gVrLanAnt  := TblTempVRLANCTO.AsFloat;
      EdtTtRateio.BrAsFloat := EdtTtRateio.BrAsFloat - TblTempVRLANCTO.AsFloat;
end;

procedure TMov0005.TblTempBeforePost(DataSet: TDataSet);
begin
      DBGLanctoColExit(nil);

      if  (TblTempNRCLASSI.AsString = '') and
          (TblTempNRCONTA.AsString = '') then
      begin
            raise Exception.Create('Informe a conta de ' + LblDsDebCre.caption);
      end;

      if  TblTempNMCONTA.AsString = '' then
      begin
            raise Exception.Create('Conta de ' + LblDsDebCre.caption + ' inválida');
      end;

      if  TblTempVRLANCTO.AsFloat <= 0 then
      begin
            raise Exception.Create('Informe um valor válido para a conta');
      end;

      if (gCdHistor = 0) and (TblTempCdHistor.AsInteger = 0) then
      begin
            raise Exception.Create('Informe um histórico contábil');
      end;
end;

end.
