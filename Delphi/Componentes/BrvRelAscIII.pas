unit BrvRelAscIII;

interface

uses Windows, SysUtils, Messages, Classes, Graphics, Controls,
  StdCtrls, ExtCtrls, Forms, Quickrpt, QRCtrls, Db, DBTables, QRExport,
  DBClient, QRWebFilt;

type
  TRelAscIII = class(TQuickRep)
    Detail: TQRBand;
    QLblDsLinha: TQRDBText;
    QRHTMLFilter1: TQRHTMLFilter;
    QRCSVFilter1: TQRCSVFilter;
    QRTextFilter1: TQRTextFilter;
    TblRelAsc: TClientDataSet;
    procedure DetailBeforePrint(Sender: TQRCustomBand;
      var PrintBand: Boolean);
  private

  public
    procedure CriarTabelaTemporaria;
  end;

var
  RelAscIII: TRelAscIII;

implementation

{$R *.DFM}

procedure TRelAscIII.CriarTabelaTemporaria;
//var TmpPath : array [0..255] of char;
begin
{      GetTempPath(200, TmpPath); Requisição - 1310

      TblRelAsc.Active       := False;
      TblRelAsc.DatabaseName := TmpPath;
      TblRelAsc.TableName    := 'RelAsc.db';}

      TblRelAsc.FieldDefs.clear;
      TblRelAsc.FieldDefs.add ('DsLinha'             , FtString, 132, False);

      TblRelAsc.IndexDefs.Clear;

      TblRelAsc.CreateDataSet;//CreateTable; 1310
      TblRelAsc.Open;
end;

procedure TRelAscIII.DetailBeforePrint(Sender: TQRCustomBand;
  var PrintBand: Boolean);
begin
      if TblRelAsc.FieldByName('DsLinha').AsString = 'NovaPagina' then
      begin
            RelAscIII.NewPage;
            PrintBand := False;
      end;
end;

end.
