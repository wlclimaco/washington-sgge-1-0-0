unit UMov0007;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvEditNum,
  Mask, DBCtrls, BrvDbRetCon, BrvEditDate, BrvRetCon, DB, DBClient, BrvClientDataSet,
  BrvBitBtn, ComCtrls, BrvDbEdit, Grids, DBGrids, BrvDbGrids, BrvDbGrid, BrvListParam, ImgList,
  Menus;

type
  TMov0007 = class(TMov0000)
    Panel2: TPanel;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label10: TLabel;
    BntLimDep: TSpeedButton;
    BntLimCre: TSpeedButton;
    Panel3: TPanel;
    Label2: TLabel;
    Label3: TLabel;
    SbtCancel: TBrvSpeedButton;
    SbtGravar: TBrvSpeedButton;
    SbtDelete: TBrvSpeedButton;
    Panel1: TPanel;
    SbtPrimei: TBrvSpeedButton;
    SbtAnteri: TBrvSpeedButton;
    SbtProxim: TBrvSpeedButton;
    SbtUltimo: TBrvSpeedButton;
    Panel4: TPanel;
    Label1: TLabel;
    LblNrPlano: TLabel;
    BrvDBRetCon1: TBrvDBRetCon;
    BrvDBRetCon2: TBrvDBRetCon;
    BrvDBRetCon3: TBrvDBRetCon;
    BrvDBRetCon4: TBrvDBRetCon;
    QcLancto: TBrvClientDataSet;
    DsLancto: TDataSource;
    BrvDbEdit1: TBrvDbEdit;
    BrvDBRetCon5: TBrvDBRetCon;
    DbEdtMasConDeb: TBrvDBRetCon;
    BrvDbEdit2: TBrvDbEdit;
    BrvDBRetCon7: TBrvDBRetCon;
    DbEdtMasConCre: TBrvDBRetCon;
    DBEdit1: TDBEdit;
    BrvDbEdit3: TBrvDbEdit;
    BrvDbEdit4: TBrvDbEdit;
    BrvDBRetCon6: TBrvDBRetCon;
    DBMemo1: TDBMemo;
    Label8: TLabel;
    DbgDebito: TBrvDBGrid;
    QcCenCusCre: TBrvClientDataSet;
    QcCenCusDeb: TBrvClientDataSet;
    Label9: TLabel;
    DbgCredit: TBrvDBGrid;
    DsCenCusDeb: TDataSource;
    DsCenCusCre: TDataSource;
    procedure BntLimCreClick(Sender: TObject);
    procedure BrvDbEdit1BrOnBeforeConsulta(Sender: TObject);
    procedure BrvDbEdit1Exit(Sender: TObject);
    procedure BntLimDepClick(Sender: TObject);
    procedure BrvDbEdit2BrOnBeforeConsulta(Sender: TObject);
    procedure BrvDbEdit2Exit(Sender: TObject);
    procedure QcLanctoBeforePost(DataSet: TDataSet);
    procedure SbtPrimeiClick(Sender: TObject);
    procedure SbtAnteriClick(Sender: TObject);
    procedure SbtProximClick(Sender: TObject);
    procedure SbtUltimoClick(Sender: TObject);
    procedure SbtCancelClick(Sender: TObject);
    procedure SbtGravarClick(Sender: TObject);
    procedure SbtDeleteClick(Sender: TObject);
    procedure QcLanctoAfterCancel(DataSet: TDataSet);
    procedure QcLanctoAfterDelete(DataSet: TDataSet);
    procedure QcLanctoAfterEdit(DataSet: TDataSet);
    procedure QcLanctoAfterPost(DataSet: TDataSet);
    procedure QcLanctoAfterScroll(DataSet: TDataSet);
    procedure QcLanctoBeforeDelete(DataSet: TDataSet);
    procedure QcCenCusDebAfterInsert(DataSet: TDataSet);
    procedure QcCenCusDebBeforePost(DataSet: TDataSet);
    procedure QcCenCusDebAfterPost(DataSet: TDataSet);
  private
    procedure VerificarEdicao;
    procedure AbrirCentroCusto(pCdsCenCus : TBrvClientDataSet; pTpLancto : String;
                               pNrConta   : String);
    { Private declarations }
  public
    { Public declarations }
    gDsMasLim  : String;
    procedure AbrirLancamentos(gDsFiltro : String);
  end;

var
  Mov0007: TMov0007;

implementation

uses UDmSrvApl, ULogos, UDmCtb, UCon0010, UDmSis;

{$R *.dfm}

procedure TMov0007.AbrirLancamentos(gDsFiltro: String);
begin
      QcLancto.Close;
      QcLancto.BrParams.Clear;
      QcLancto.BrParams.Add('<%DsWhere%>;' + gDsFiltro);
      QcLancto.Open;
end;

procedure TMov0007.BntLimCreClick(Sender: TObject);
begin
      QcLancto.FieldByName('NrClaCre').AsString  := '';
      QcLancto.FieldByName('NrConCre').AsInteger := 0;
      QcLancto.FieldByName('NmConCre').AsString  := '';
end;

procedure TMov0007.BntLimDepClick(Sender: TObject);
begin
      QcLancto.FieldByName('NrClaDeb').AsString  := '';
      QcLancto.FieldByName('NrConDeb').AsInteger := 0;
      QcLancto.FieldByName('NmConDeb').AsString  := '';
end;

procedure TMov0007.VerificarEdicao;
begin
      if not (QcLancto.State in [dsInsert, dsEdit]) then
      begin
            QcLancto.Edit;
      end;
end;

procedure TMov0007.BrvDbEdit1BrOnBeforeConsulta(Sender: TObject);
begin
      VerificarEdicao;

      DmCtb.ConsultarPlanoContas(StrToInt(LblNrPlano.Caption), 1,
                                 nil, nil, True,
                                 QcLancto.FieldByName('NrClaDeb'),
                                 QcLancto.FieldByName('NrConDeb'),
                                 QcLancto.FieldByName('CdEmpres').AsInteger);
      BrvDbEdit1Exit(nil);
end;

procedure TMov0007.BrvDbEdit1Exit(Sender: TObject);
var  lNrConta  : Integer;
     lNrClassi : String;
     lNmConta  : String;
begin
      if (QcLancto.FieldByName('NrConDeb').AsInteger <> 0) or
         (QcLancto.FieldByName('NrClaDeb').AsString  <> gDsMasLim) then
      begin
            VerificarEdicao;

            lNrConta  := QcLancto.FieldByName('NrConDeb').AsInteger;
            lNrClassi := QcLancto.FieldByName('NrClaDeb').AsString;

            try
                DmCtb.PesquisarConta(lNrConta, lNrClassi, lNmConta,
                                     StrToInt(LblNrPlano.Caption), gDsMasLim);
            finally
                QcLancto.FieldByName('NmConDeb').AsString  :=  lNmConta;
                QcLancto.FieldByName('NrConDeb').AsInteger :=  lNrConta;
                QcLancto.FieldByName('NrClaDeb').AsString  :=  lNrClassi;
            end;
      end;
end;

procedure TMov0007.BrvDbEdit2BrOnBeforeConsulta(Sender: TObject);
begin
      VerificarEdicao;
      DmCtb.ConsultarPlanoContas(StrToInt(LblNrPlano.Caption), 1,
                                 nil, nil, True,
                                 QcLancto.FieldByName('NrClaCre'),
                                 QcLancto.FieldByName('NrConCre'),
                                 QcLancto.FieldByName('CdEmpres').AsInteger);
      BrvDbEdit2Exit(nil);
end;

procedure TMov0007.BrvDbEdit2Exit(Sender: TObject);
var  lNrConta  : Integer;
     lNrClassi : String;
     lNmConta  : String;
begin
      if (QcLancto.FieldByName('NrConCre').AsInteger <> 0) or
         (QcLancto.FieldByName('NrClaCre').AsString  <> gDsMasLim) then
      begin
            VerificarEdicao;
            lNrConta    :=  QcLancto.FieldByName('NrConCre').AsInteger;
            lNrClassi   :=  QcLancto.FieldByName('NrClaCre').AsString;

            try
                DmCtb.PesquisarConta(lNrConta, lNrClassi, lNmConta,
                                     StrToInt(LblNrPlano.Caption), gDsMasLim);
            finally
                QcLancto.FieldByName('NmConCre').AsString  :=  lNmConta;
                QcLancto.FieldByName('NrConCre').AsInteger :=  lNrConta;
                QcLancto.FieldByName('NrClaCre').AsString  :=  lNrClassi;
            end;
      end;
end;

procedure TMov0007.QcCenCusDebAfterInsert(DataSet: TDataSet);
begin
      DataSet.FieldByName('NrLancto').AsInteger  :=
                                      QcLancto.FieldByName('NrLancto').AsInteger;

      if DataSet.Name = 'QcCenCusDeb' then
      begin
            DataSet.FieldByName('TpLancto').AsString  := 'D';
      end else
      begin
            DataSet.FieldByName('TpLancto').AsString  := 'C';
      end;
end;

procedure TMov0007.QcCenCusDebAfterPost(DataSet: TDataSet);
begin
      (DataSet as TBrvClientDataSet).BrApplyUpdates;
end;

procedure TMov0007.QcCenCusDebBeforePost(DataSet: TDataSet);
begin
      if DataSet.FieldByName('CdCenCus').AsInteger = 0 then
      begin
            raise Exception.Create('Informe o centro de custo');
      end;

      if DataSet.FieldByName('VrLancto').AsFloat = 0 then
      begin
            raise Exception.Create('Informe o valor do lançamento');
      end;
end;

procedure TMov0007.QcLanctoAfterCancel(DataSet: TDataSet);
begin
      SbtDelete.Enabled := not QcLancto.IsEmpty;
      SbtGravar.Enabled := False;
      SbtCancel.Enabled := False;

      QcLanctoAfterScroll(nil);
end;

procedure TMov0007.QcLanctoAfterDelete(DataSet: TDataSet);
begin
      try
          QcLancto.BrApplyUpdates;

          SbtDelete.Enabled := not QcLancto.IsEmpty;
          SbtGravar.Enabled := False;
          SbtCancel.Enabled := False;
      except
          raise;
      end;

end;

procedure TMov0007.QcLanctoAfterEdit(DataSet: TDataSet);
begin
      SbtDelete.Enabled := False;
      SbtGravar.Enabled := True;
      SbtCancel.Enabled := True;
end;

procedure TMov0007.QcLanctoAfterPost(DataSet: TDataSet);
begin
            try
                QcLancto.BrApplyUpdates;

                SbtDelete.Enabled := True;
                SbtGravar.Enabled := False;
                SbtCancel.Enabled := False;

                QcLanctoAfterScroll(nil);
            except
                raise;
            end;
end;

procedure TMov0007.QcLanctoAfterScroll(DataSet: TDataSet);
begin
      SbtPrimei.Enabled  := (not QcLancto.Bof)   and
                            (not (QcLancto.State in  [dsEdit, dsInsert]));
      SbtAnteri.Enabled  := SbtPrimei.Enabled;

      SbtProxim.Enabled  := (not QcLancto.Eof)   and
                            (not (QcLancto.State in  [dsEdit, dsInsert]));
      SbtUltimo.Enabled  := SbtProxim.Enabled;


      SbtDelete.Enabled  := (not QcLancto.IsEmpty) and
                            (not (QcLancto.State in  [dsEdit, dsInsert]));

      AbrirCentroCusto(QcCenCusDeb, 'D', QcLancto.FieldByName('NrConDeb').AsString);
      AbrirCentroCusto(QcCenCusCre, 'C', QcLancto.FieldByName('NrConCre').AsString);
end;

procedure TMov0007.AbrirCentroCusto(pCdsCenCus : TBrvClientDataSet; pTpLancto : String;
                                    pNrConta   : String);
begin
      pCdsCenCus.Close;
      pCdsCenCus.BrParams.Clear;
      pCdsCenCus.BrParams.Add('<%CdEmpres%>;' + QcLancto.FieldByName('CdEmpres').AsString);
      pCdsCenCus.BrParams.Add('<%DtLancto%>;' + QcLancto.FieldByName('DtLancto').AsString);
      pCdsCenCus.BrParams.Add('<%NrLancto%>;' + QcLancto.FieldByName('NrLancto').AsString);
      pCdsCenCus.BrParams.Add('<%NrPlano%>;'  + LblNrPlano.Caption);
      pCdsCenCus.BrParams.Add('<%NrConta%>;'  + pNrConta);
      pCdsCenCus.BrParams.Add('<%TpLancto%>;' + pTpLancto);
      pCdsCenCus.Open;

      (pCdsCenCus.FieldByName('VrLancto') as TNumericField).DisplayFormat := '0.000';
end;

procedure TMov0007.QcLanctoBeforeDelete(DataSet: TDataSet);
begin
      if MessageDlg('Confirma exclusão do registro?', MtConfirmation,
                                                            [MbYes, MbNo], 0) = IdNo then
      begin
            Abort;
      end;
end;

procedure TMov0007.QcLanctoBeforePost(DataSet: TDataSet);
begin
      if  DataSet.FieldByName('NrConDeb').AsInteger = 0 then
      begin
            raise Exception.Create('Informe a conta de débito');
      end;

      if  DataSet.FieldByName('NrConCre').AsInteger = 0 then
      begin
            raise Exception.Create('Informe a conta de crédito');
      end;

      if  DataSet.FieldByName('NrConDeb').AsInteger =
          DataSet.FieldByName('NrConCre').AsInteger then
      begin
            raise Exception.Create('Contas não podem ser iguais');
      end;

      if  DataSet.FieldByName('VrLancto').AsFloat = 0 then
      begin
            raise Exception.Create('Valor de lancamento inválido');
      end;

      if  DataSet.FieldByName('CdHistor').AsInteger = 0 then
      begin
            raise Exception.Create('Informe um histórico válido');
      end;
end;

procedure TMov0007.SbtAnteriClick(Sender: TObject);
begin
      QcLancto.Prior;
end;

procedure TMov0007.SbtCancelClick(Sender: TObject);
begin
      QcLancto.Cancel;
end;

procedure TMov0007.SbtDeleteClick(Sender: TObject);
begin
      QcCenCusDeb.First;
      while not QcCenCusDeb.Eof do
      begin
            QcCenCusDeb.Delete;
      end;

      QcCenCusCre.First;
      while not QcCenCusCre.Eof do
      begin
            QcCenCusCre.Delete;
      end;

      QcLancto.Delete;
end;

procedure TMov0007.SbtGravarClick(Sender: TObject);
begin
      QcLancto.Post;
end;

procedure TMov0007.SbtPrimeiClick(Sender: TObject);
begin
      QcLancto.First;
end;

procedure TMov0007.SbtProximClick(Sender: TObject);
begin
      QcLancto.Next;
end;

procedure TMov0007.SbtUltimoClick(Sender: TObject);
begin
      QcLancto.Last;
end;

initialization
      RegisterClass(TMov0007);

finalization
      UnRegisterClass(TMov0007);

end.
