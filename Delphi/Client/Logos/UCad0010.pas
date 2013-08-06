unit UCad0010;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCad0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, DB, DBClient, BrvClientDataSet,
  Grids, BrvDbGrids, BrvDbGrid, StdCtrls, DBCtrls, ImgList, Menus, BrvListParam, BrvRetCon;

type
  TCad0010 = class(TCad0000)
    Panel1: TPanel;
    MemTXALTERA: TDBMemo;
    QcS049: TBrvClientDataSet;
    DsS049: TDataSource;
    Splitter1: TSplitter;
    BtnNovo: TBrvSpeedButton;
    BtnSalvar: TBrvSpeedButton;
    BtnCancelar: TBrvSpeedButton;
    Panel2: TPanel;
    Panel3: TPanel;
    DbgS049: TBrvDbGrid;
    Label20: TLabel;
    LblDsFormul: TBrvRetCon;
    QcS049CDUSUARI: TFMTBCDField;
    QcS049DSRESUMO: TWideStringField;
    QcS049NRALTERA: TFMTBCDField;
    QcS049NRFORDIN: TFMTBCDField;
    QcS049NRSEQFOR: TFMTBCDField;
    QcS049TPFORMUL: TWideStringField;
    QcS049TXALTERA: TBlobField;
    QcS049DTALTERA: TSQLTimeStampField;
    QcS049NRATESAB: TFMTBCDField;
    QcS049DSFORMUL: TWideStringField;
    QcS049NMCOMUSU: TWideStringField;
    procedure QcS049AfterOpen(DataSet: TDataSet);
    procedure BtnNovoClick(Sender: TObject);
    procedure BtnSalvarClick(Sender: TObject);
    procedure BtnCancelarClick(Sender: TObject);
    procedure QcS049AfterInsert(DataSet: TDataSet);
    procedure QcS049BeforePost(DataSet: TDataSet);
    procedure QcS049AfterPost(DataSet: TDataSet);
    procedure QcS049AfterCancel(DataSet: TDataSet);
    procedure QcS049AfterEdit(DataSet: TDataSet);
    procedure QcS049AfterDelete(DataSet: TDataSet);
  private
    { Private declarations }
  public
    { Public declarations }
    gNRSEQFOR: Integer;
    gTPFORMUL: String;
  end;

var
  Cad0010: TCad0010;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TCad0010.BtnCancelarClick(Sender: TObject);
begin
      inherited;
      QcS049.Cancel;
end;

procedure TCad0010.BtnNovoClick(Sender: TObject);
begin
      inherited;
      QcS049.Append;
end;

procedure TCad0010.BtnSalvarClick(Sender: TObject);
begin
      inherited;
      QcS049.Post;
end;

procedure TCad0010.QcS049AfterCancel(DataSet: TDataSet);
begin
      inherited;
      BtnNovo.Enabled := True;
      BtnSalvar.Enabled := False;
      BtnCancelar.Enabled := False;

      DbgS049.Enabled := True;
      DbgS049.SetFocus;
end;

procedure TCad0010.QcS049AfterDelete(DataSet: TDataSet);
begin
      inherited;
      QcS049.BrApplyUpdates;
end;

procedure TCad0010.QcS049AfterEdit(DataSet: TDataSet);
begin
      inherited;
      BtnNovo.Enabled := False;
      BtnSalvar.Enabled := True;
      BtnCancelar.Enabled := True;
end;

procedure TCad0010.QcS049AfterInsert(DataSet: TDataSet);
begin
      inherited;
      BtnNovo.Enabled := False;
      BtnSalvar.Enabled := True;
      BtnCancelar.Enabled := True;

      QcS049CDUSUARI.AsInteger  := DmSrvApl.BrvDicionario.UserCode;
      QcS049NMCOMUSU.AsString   := DmSrvApl.BrvDicionario.UserName;
      QcS049DTALTERA.AsDateTime := Now();
      QcS049NRSEQFOR.AsInteger  := gNRSEQFOR;
      QcS049TPFORMUL.AsString   := gTPFORMUL;
      QcS049DSFORMUL.AsString   := LblDsFormul.Text;
      DbgS049.SetFocus;
      DbgS049.SelectedField     := QcS049NRATESAB;
end;

procedure TCad0010.QcS049AfterOpen(DataSet: TDataSet);
begin
      inherited;
      QcS049NRFORDIN.Visible := False;
      QcS049NRSEQFOR.Visible := False;
      QcS049TXALTERA.Visible := False;
      QcS049TPFORMUL.Visible := False;
      QcS049DSFORMUL.Visible := False;

      DbgS049.Columns[1].Width := 120;
      DbgS049.Columns[3].Width := 150;
end;

procedure TCad0010.QcS049AfterPost(DataSet: TDataSet);
begin
      inherited;
      QcS049.BrApplyUpdates;
      BtnNovo.Enabled := True;
      BtnSalvar.Enabled := False;
      BtnCancelar.Enabled := False;

      DbgS049.Enabled := True;
      DbgS049.SetFocus;
end;

procedure TCad0010.QcS049BeforePost(DataSet: TDataSet);
begin
      inherited;
      if (Trim(QcS049DSRESUMO.AsString) = '') then
      begin
            raise Exception.Create('Preencha o resumo da alteração!');
      end;

      if (QcS049NRATESAB.AsInteger = 0) then
      begin
            raise Exception.Create('Preencha o antedimento SAB!');
      end;

      if QcS049NRALTERA.AsInteger = 0 then
      begin
            QcS049NRALTERA.AsInteger :=
                                     DmSrvApl.BrvDicionario.ProxNumeroSequencial('S049','NRALTERA');
      end;
end;

end.
