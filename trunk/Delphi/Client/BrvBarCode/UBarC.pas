unit UBarC;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, ExtCtrls, StdCtrls, Buttons, DB, DBClient,
  RpCon, RpConDS, RpBase, RpSystem, RpDefine, RpRave, QRCtrls, QuickRpt, BrvString;

type
  TFrmBarCode = class(TForm)
    Panel1: TPanel;
    Label1: TLabel;
    BtnImprim: TBitBtn;
    EdtDsArquiv: TEdit;
    SpeedButton1: TSpeedButton;
    DlgArquiv: TOpenDialog;
    TblOpeLog: TClientDataSet;
    QuickRep: TQuickRep;
    QRBand1: TQRBand;
    QRDBText1: TQRDBText;
    QRDBText2: TQRDBText;
    QRDBText5: TQRDBText;
    QRDBImage1: TQRDBImage;
    TblOpeLogCdEmpres: TIntegerField;
    TblOpeLogCdProdut: TIntegerField;
    TblOpeLogDsProdut: TStringField;
    TblOpeLogNrOrdem: TIntegerField;
    TblOpeLogDtOcorre: TDateTimeField;
    TblOpeLogDsRefCli: TStringField;
    TblOpeLogQtProdut: TFloatField;
    TblOpeLogDsUnidad: TStringField;
    TblOpeLogDsLote: TStringField;
    TblOpeLogDtValida: TDateField;
    TblOpeLogNrNota: TIntegerField;
    TblOpeLogRsClient: TStringField;
    TblOpeLogDsEndere: TStringField;
    TblOpeLogCdEndere: TIntegerField;
    TblOpeLogDsEndOri: TStringField;
    TblOpeLogImBarCod: TBlobField;
    QRLabel1: TQRLabel;
    QRDBText7: TQRDBText;
    QRLabel2: TQRLabel;
    QRDBText8: TQRDBText;
    QRLabel3: TQRLabel;
    QrDbtQtProdut: TQRDBText;
    QrDbtCdUnidad: TQRDBText;
    QRLabel4: TQRLabel;
    QRDBText3: TQRDBText;
    QRLabel5: TQRLabel;
    QRDBText4: TQRDBText;
    QRLabel6: TQRLabel;
    QRDBText6: TQRDBText;
    QRDBText11: TQRDBText;
    TblOpeLogDsSigCid: TStringField;
    QRDBText12: TQRDBText;
    QRDBText13: TQRDBText;
    QRLabel7: TQRLabel;
    QRDBText14: TQRDBText;
    QRLabel8: TQRLabel;
    BrvString: TBrvString;
    TblOpeLogCdClient: TIntegerField;
    procedure BtnImprimClick(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure QRBand1BeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
  private
    { Private declarations }
     procedure ImprimirEtiquetaBarrasArquivo(EdArquiv : String);
     procedure GeraTabelaTempImpEtiqBarrasArquivo(EdArquiv : String);
     procedure RetaurarConteudoEditDiretorio;
     procedure CarregarConteudoTemp(NmDirTmp : AnsiString);
     procedure GravarConteudoTemp(NmDirTmp : AnsiString);
     procedure SalvarEditDiretorio;
     function  SiglaCidade(CdEmpres : Integer) : AnsiString;
     function  GerarCdBarra2D(pDsConteu : AnsiString) : AnsiString;
     function  DiretorioTemp : AnsiString;
     function  DiretorioPrograma : AnsiString;
     function  ProximaColuna(var pDsLinha : AnsiString) : AnsiString;

  public
    { Public declarations }
  end;

var
  FrmBarCode: TFrmBarCode;

implementation

uses Math, QuricolCode;

{$R *.dfm}

procedure TFrmBarCode.BtnImprimClick(Sender: TObject);
begin
      ImprimirEtiquetaBarrasArquivo(EdtDsArquiv.Text);
end;

function TFrmBarCode.GerarCdBarra2D(pDsConteu : AnsiString) : AnsiString;
var lQrCode : TQRCode;
begin
      try
          Result  := DiretorioPrograma + 'QrCode.bmp';
          lQrCode := TQRCode.Create;
          lQrCode.GenerateBitmapFile(Result, pDsConteu, 0, 1);
      finally
          FreeAndNil(lQrCode);
      end;
end;


procedure TFrmBarCode.ImprimirEtiquetaBarrasArquivo(EdArquiv : String);
var NmArqRav : String;
begin
      if FileExists(EdArquiv) then
      begin
            try
                TblOpeLog.CreateDataSet;

                GeraTabelaTempImpEtiqBarrasArquivo(EdArquiv);

                TblOpeLog.First;
                QuickRep.Preview;
            finally
                TblOpeLog.Close;
            end;
      end else
      begin
            Raise Exception.Create('Arquivo ' + EdArquiv + ' não existe.' );
      end;
end;

procedure TFrmBarCode.GeraTabelaTempImpEtiqBarrasArquivo(EdArquiv : String);
var lCdEmpres : Integer;
    lCdProdut : Integer;
    lDsProdut : AnsiString;
    lNrOrdem  : Integer;
    lDtOcorre : TDateTime;
    lDsRefCli : AnsiString;
    lQtProdut : Real;
    lDsUnidad : AnsiString;
    lDsLote   : AnsiString;
    lDtValida : TDate;
    lNrNota   : Integer;
    lRsClient : AnsiString;
    lDsEndere : AnsiString;
    lCdEndere : Integer;
    lDsEndOri : AnsiString;

    lNrIndice : Integer;
    lTxArquiv : TStrings;
    lDsLinha  : AnsiString;

    lDtAuxili : TDateTime;
    lCdBarra  : AnsiString;

    lDsRua    : AnsiString;
    lDsPredio : AnsiString;
    lDsNivel  : AnsiString;
begin
      try
          lTxArquiv := TStringList.Create;
          lTxArquiv.LoadFromFile(EdArquiv);

          for lNrIndice := 0 to lTxArquiv.Count -1 do
          begin
                lDsLinha  := lTxArquiv[lNrIndice];

                if Length(lDsLinha) > 10 then
                begin
                      TblOpeLog.Append;

                      lCdBarra := lDsLinha;

                      TblOpeLog.FieldByName('CdEmpres').AsString := ProximaColuna(lDsLinha);
                      TblOpeLog.FieldByName('CdProdut').AsString := ProximaColuna(lDsLinha);
                      TblOpeLog.FieldByName('DsProdut').AsString := ProximaColuna(lDsLinha);
                      TblOpeLog.FieldByName('NrOrdem').AsString  := ProximaColuna(lDsLinha);
                      TblOpeLog.FieldByName('DtOcorre').AsDateTime :=
                                              StrToDateTimeDef(ProximaColuna(lDsLinha) + ' ' +
                                                               ProximaColuna(lDsLinha), 0);
                      TblOpeLog.FieldByName('DsRefCli').AsString  := ProximaColuna(lDsLinha);
                      TblOpeLog.FieldByName('QtProdut').AsString  :=
                            StringReplace(ProximaColuna(lDsLinha), '.', ',', [rfReplaceAll]);
                      TblOpeLog.FieldByName('DsUnidad').AsString  := ProximaColuna(lDsLinha);
                      TblOpeLog.FieldByName('DsLote').AsString    := ProximaColuna(lDsLinha);
                      TblOpeLog.FieldByName('DtValida').AsString  := ProximaColuna(lDsLinha);
                      TblOpeLog.FieldByName('NrNota').AsString    := ProximaColuna(lDsLinha);
                      TblOpeLog.FieldByName('RsClient').AsString  := ProximaColuna(lDsLinha);

                      lCdBarra := TblOpeLog.FieldByName('CdEmpres').AsString   + '|' +
                                  TblOpeLog.FieldByName('CdProdut').AsString   + '|' +
                                  TblOpeLog.FieldByName('DsProdut').AsString   + '|' +
                                  TblOpeLog.FieldByName('NrOrdem').AsString    + '|' +
                                  TblOpeLog.FieldByName('QtProdut').AsString   + '|' +
                                  TblOpeLog.FieldByName('DsUnidad').AsString   + '|' +
                                  TblOpeLog.FieldByName('DsLote').AsString     + '|' +
                                  TblOpeLog.FieldByName('DtValida').AsString   + '|' +
                                  TblOpeLog.FieldByName('NrNota').AsString     + '|';

                      lDsRua    := BrvString.FormatarNumero(ProximaColuna(lDsLinha), 2, True);
                      lDsPredio := BrvString.FormatarNumero(ProximaColuna(lDsLinha), 3, True);
                      lDsNivel  := BrvString.FormatarNumero(ProximaColuna(lDsLinha), 2, True);
                      lCdBarra  := lCdBarra + lDsRua + '|' + lDsPredio + '|' + lDsNivel + '|';

                      TblOpeLog.FieldByName('DsEndere').AsString  :=
                                                    lDsRua + '.' + lDsPredio + '.' + lDsNivel;

                      TblOpeLog.FieldByName('CdEndere').AsString  := ProximaColuna(lDsLinha);

                      lDsRua    := BrvString.FormatarNumero(ProximaColuna(lDsLinha), 2, True);
                      lDsPredio := BrvString.FormatarNumero(ProximaColuna(lDsLinha), 3, True);
                      lDsNivel  := BrvString.FormatarNumero(ProximaColuna(lDsLinha), 2, True);
                      lCdBarra  := lCdBarra + TblOpeLog.FieldByName('CdEndere').AsString + '|' +
                                   lDsRua   + '|' + lDsPredio + '|' + lDsNivel + '|';

                      TblOpeLog.FieldByName('DsEndOri').AsString  :=
                                                    lDsRua + '.' + lDsPredio + '.' + lDsNivel;

                      //TblOpeLog.FieldByName('CdClient').AsString  := ProximaColuna(lDsLinha);

                      lCdBarra  := lCdBarra + TblOpeLog.FieldByName('CdEndere').AsString;

                      TBlobField(TblOpeLog.FieldByName('ImBarCod')).LoadFromFile(
                                                                 GerarCdBarra2D(lCdBarra));

                      TblOpeLog.FieldByName('DsSigCid').AsString  :=
                                           SiglaCidade(TblOpeLog.FieldByName('CdEmpres').AsInteger);

                     TblOpeLog.FieldByName('DsEndere').AsString :=
                                                       TblOpeLog.FieldByName('DsEndere').AsString +
                                                       ProximaColuna(lDsLinha);

                     TblOpeLog.FieldByName('DsEndOri').AsString :=
                                                       TblOpeLog.FieldByName('DsEndOri').AsString +
                                                       ProximaColuna(lDsLinha);

                      TblOpeLog.Post;
                end;
          end;
      finally
          FreeAndNil(lTxArquiv);
      end;
end;

function TFrmBarCode.ProximaColuna(var pDsLinha : AnsiString) : AnsiString;
var lNrPosica : Integer;
begin
      lNrPosica := Pos(';', pDsLinha);

      if lNrPosica > 0 then
      begin
            Result := Trim(Copy(pDsLinha, 1, lNrPosica -1));
            Delete(pDsLinha, 1, lNrPosica);
      end else
      begin
            Result  := pDsLinha;
            pDsLinha := '';
      end;
end;

procedure TFrmBarCode.QRBand1BeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
begin
      if Length(TblOpeLog.FieldByName('QtProdut').AsString) > 6 then
      begin
            QrDbtQtProdut.Font.Size := 8;
            QrDbtCdUnidad.Font.Size := 8;
      end else
      begin
            QrDbtQtProdut.Font.Size := 10;
            QrDbtCdUnidad.Font.Size := 10;
      end;
end;

procedure TFrmBarCode.SpeedButton1Click(Sender: TObject);
begin
      if DlgArquiv.Execute then
      begin
            EdtDsArquiv.Text := DlgArquiv.FileName;
      end;
end;

procedure TFrmBarCode.RetaurarConteudoEditDiretorio;
begin
      CarregarConteudoTemp(DiretorioTemp);
end;

procedure TFrmBarCode.CarregarConteudoTemp(NmDirTmp : AnsiString);
var TxArqTmp : TStrings;
    NmArquiv : AnsiString;
begin
      try
          TxArqTmp  := TStringList.Create;
          NmArquiv  := NmDirTmp + Name + EdtDsArquiv.Name + '.txt';

          if FileExists(NmArquiv) then
          begin
                TxArqTmp.LoadFromFile(NmArquiv);
          end;

          EdtDsArquiv.Text := TxArqTmp[0];
      finally
          FreeAndNil(TxArqTmp);
      end;
end;

procedure TFrmBarCode.GravarConteudoTemp(NmDirTmp : AnsiString);
var TxArqTmp : TStrings;
begin
      try
          TxArqTmp := TStringList.Create;
          TxArqTmp.Clear;
          TxArqTmp.Add(EdtDsArquiv.Text);

          TxArqTmp.SaveToFile(NmDirTmp + Name + EdtDsArquiv.Name + '.txt');
      finally
          FreeAndNil(TxArqTmp);
      end;
end;

procedure TFrmBarCode.SalvarEditDiretorio;
begin
      GravarConteudoTemp(DiretorioTemp);
end;

function TFrmBarCode.DiretorioTemp : AnsiString;
var DsDirTmp : array[0..144] of Char;
begin
      GetTempPath(144, DsDirTmp);
      Result := StrPas(DsDirTmp);
end;

function TFrmBarCode.DiretorioPrograma : AnsiString;
begin
      Result := ExtractFilePath(Application.ExeName);
end;

function TFrmBarCode.SiglaCidade(CdEmpres : Integer) : AnsiString;
begin
      case CdEmpres of
           51     : Result := 'UBA';
           54, 59 : Result := 'CBA';
           81     : Result := 'IBI';
           53     : Result := 'PLI';
           52     : Result := 'LEM';
           57     : Result := 'APG';
           10     : Result := 'IGA';
      end;
end;

procedure TFrmBarCode.FormCreate(Sender: TObject);
begin
      RetaurarConteudoEditDiretorio;
      QuickRep.Top := -400;
end;

procedure TFrmBarCode.FormClose(Sender: TObject; var Action: TCloseAction);
begin
      SalvarEditDiretorio;
end;

end.
