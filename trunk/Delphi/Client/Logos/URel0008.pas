unit URel0008;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, URel0000, BrvGeraRelatorio, BrvListParam, ImgList, Menus, ExtCtrls, Buttons,
  BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvCustomEdit, BrvEditNum, Grids, BrvDbGrids, BrvDbGrid,
  DB, DBClient, BrvClientDataSet, ComCtrls, QRCtrls, QuickRpt,RpCon, RpConDS, RpBase, RpSystem,
  RpDefine, RpRave, BrvQRImage, BrvQrBarCode, BrvEdit, BrvBitBtn, BrvRetCon;

type
  TRel0008 = class(TREL0000)
    QpEndere: TBrvClientDataSet;
    DsEndere: TDataSource;
    DlgArquiv: TOpenDialog;
    Label3: TLabel;
    Label5: TLabel;
    Label8: TLabel;
    Label9: TLabel;
    PgcEtiqueta: TPageControl;
    TbsFiltro: TTabSheet;
    Panel2: TPanel;
    Panel3: TPanel;
    BrvDbGrid2: TBrvDbGrid;
    TbsColMod1: TTabSheet;
    QrpColMod1: TQuickRep;
    QRBand2: TQRBand;
    QRDBText1: TQRDBText;
    BrvQRLblEndere1: TBrvQRImage;
    BrvQrBarCode1: TBrvQrBarCode;
    BrvQRLblEndere2: TBrvQRImage;
    BrvQrBarCode2: TBrvQrBarCode;
    ImgSetaEsq: TImage;
    ImgSetaDir: TImage;
    ImgSetaAbaixo: TImage;
    ImgSetaAcima: TImage;
    TbsLongMod1: TTabSheet;
    QrpLongMod1: TQuickRep;
    QRBand4: TQRBand;
    BrvQrBarCode3: TBrvQrBarCode;
    QRImgSetaLong: TQRImage;
    Label13: TLabel;
    EdtQtdeReg: TBrvRetCon;
    BtnFiltro: TBrvBitBtn;
    BrvQRLblEndere3: TQRLabel;
    BrvQRLblNivel: TQRLabel;
    ImgSemSeta: TImage;
    TabSheet1: TTabSheet;
    QuickRep1: TQuickRep;
    QRBand1: TQRBand;
    QRImage1: TQRImage;
    Label4: TLabel;
    Label2: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label10: TLabel;
    Label11: TLabel;
    Label12: TLabel;
    EdtRuaIni: TBrvEditNum;
    EdtRuaFin: TBrvEditNum;
    EdtPreIni: TBrvEditNum;
    EdtPreFin: TBrvEditNum;
    EdtNivIni: TBrvEditNum;
    EdtNivFin: TBrvEditNum;
    EdtBloco: TBrvEdit;
    RdgOrdena: TRadioGroup;
    RdgModelo: TRadioGroup;
    QRImage2: TQRImage;
    QRShape1: TQRShape;
    CpEndAux: TClientDataSet;
    TabSheet2: TTabSheet;
    EdtQtEtiq: TBrvEditNum;
    Label14: TLabel;
    Panel1: TPanel;
    Label1: TLabel;
    EdtDsArquiv: TBrvEdit;
    BtnImport: TBrvBitBtn;
    Panel4: TPanel;
    RGEtiqueta: TRadioGroup;
    procedure BtnImportClick(Sender: TObject);
    procedure QRBand2BeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
    procedure QRBand4BeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
    procedure BtnFiltroClick(Sender: TObject);
    procedure BtnGeraRelClick(Sender: TObject);
    procedure RGEtiquetaClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure EdtQtEtiqExit(Sender: TObject);
  private
    procedure AgrupaInformacoes;
    function EnderecoFormatado: String;
    function DefineAlturaEndereco(pDsBloco: String): Integer;

    function QuantidadeEnderecoPredio : Integer;
    { Private declarations }
  public
    { Public declarations }
  end;

var Rel0008: TRel0008;
    gNrPredio : Integer;
    gQtEndere : Integer;


implementation

uses BrvFuncoesXE;

{$R *.dfm}

procedure TRel0008.AgrupaInformacoes;
begin
      QpEndere.Filtered := False;
      QpEndere.Filter   :=  ' (NrRua    >= ' + FormatFloat('0',
                                                  StrToIntDef(EdtRuaIni.Text,       0)) + '  and ' +
                            '  NrRua    <= ' + FormatFloat('0',
                                                  StrToIntDef(EdtRuaFin.Text, 9999999)) + ') and ' +
                            ' (NrPredio >= ' + FormatFloat('0',
                                                  StrToIntDef(EdtPreIni.Text,       0)) + '  and ' +
                            '  NrPredio <= ' + FormatFloat('0',
                                                  StrToIntDef(EdtPreFin.Text, 9999999)) + ') and ' +
                            ' (NrNivel  >= ' + FormatFloat('0',
                                                  StrToIntDef(EdtNivIni.Text,       0)) + '  and ' +
                            '  NrNivel  <= ' + FormatFloat('0',
                                                  StrToIntDef(EdtNivFin.Text, 9999999)) + ')';

      if (Trim(EdtBloco.Text) <> '') then
      begin
            QpEndere.Filter := QpEndere.Filter +
                                      ' and (DsBloco = ''' + UpperCase(Trim(EdtBloco.Text)) + ''')';
      end;

      QpEndere.IndexDefs.Clear;

      if (RdgOrdena.ItemIndex = 0) then
      begin
            QpEndere.IndexDefs.Add('IdxNrRuaC','DsBloco;NrRua;NrPredio;NrNivel',
                                                                               [ixDescending]);
            QpEndere.IndexName := 'IdxNrRuaC';
      end else
      begin
            QpEndere.IndexDefs.Add('IdxNrRuaD', 'DsBloco;NrRua;NrPredio;NrNivel', [ixDescending]);
            QpEndere.IndexName := 'IdxNrRuaD';
      end;

      QpEndere.Filtered := True;


      EdtQtdeReg.Text := FormatFloat('0', QpEndere.RecordCount);
end;

procedure TRel0008.EdtQtEtiqExit(Sender: TObject);
var
    lNrCount : Integer;
begin
      QpEndere.Close;
      QpEndere.FieldDefs.Clear;
      QpEndere.FieldDefs.Add('NrCount' , ftInteger,  0);
      QpEndere.CreateDataSet;
      for lNrCount := 0 to StrToInt(EdtQtEtiq.Text) - 1 do
      begin
            QpEndere.Append;
            QpEndere.FieldByName('NrCount').AsInteger := lNrCount;
            QpEndere.Post;
      end;
end;

procedure TRel0008.BtnFiltroClick(Sender: TObject);
begin
      AgrupaInformacoes;
end;

procedure TRel0008.BtnGeraRelClick(Sender: TObject);
begin
      inherited;
      gQtEndere := 0;
      gNrPredio := 0;
      if RGEtiqueta.ItemIndex = 1 then
      begin
            QuickRep1.Preview;
      end else
      begin
            try
                QpEndere.DisableControls;
                AgrupaInformacoes;
                case RdgModelo.ItemIndex of
                  0: begin
                           QrpColMod1.PreviewModal;
                     end;
                  1: begin
                           QrpLongMod1.PreviewModal;
                     end;
                end;
            finally
                QpEndere.EnableControls;
            end;
      end;
end;

procedure TRel0008.BtnImportClick(Sender: TObject);
var lDsLista : TStrings;
begin
      try
          lDsLista  := TStringList.Create;
          lDsLista.LoadFromFile(EdtDsArquiv.Text);
          lDsLista.Insert(0, 'NrCodigo;NrRua;NrPredio;NrNivel;DsBloco');
          BrvFuncoesXE.StrToClientDataSet(lDsLista.Text, QpEndere, [ftInteger, ftInteger, ftInteger,
                                                                              ftInteger, ftString]);
          TbsFiltro.TabVisible        := False;
          TbsColMod1.TabVisible       := False;
          TbsLongMod1.TabVisible      := False;
          PgcEtiqueta.ActivePageIndex := 0;
          PgcEtiqueta.Visible         := True;
          QpEndere.First;

          EdtQtdeReg.Text := FormatFloat('0', QpEndere.RecordCount);
       finally
          FreeAndNil(lDsLista);
       end;
end;

function TRel0008.EnderecoFormatado: String;
begin
      Result := FormatFloat('00',  QpEndere.FieldByName('NrRua'   ).AsInteger) + '.' +
                FormatFloat('000', QpEndere.FieldByName('NrPredio').AsInteger) + '.' +
                FormatFloat('00',  QpEndere.FieldByName('NrNivel' ).AsInteger) +
                QpEndere.FieldByName('DsBloco').AsString;
end;

procedure TRel0008.FormCreate(Sender: TObject);
begin
      TbsFiltro.TabVisible          := False;
      TbsColMod1.TabVisible         := False;
      PgcEtiqueta.ActivePage        := TbsFiltro;
      BtnFiltro.Visible             := false;
      TbsFiltro.PageControl.Visible := true;
      TbsColMod1.TabVisible         := false;
      TbsLongMod1.TabVisible        := false;
      TabSheet1.TabVisible          := false;
      TabSheet2.TabVisible          := false;
      PgcEtiqueta.ActivePage        := TbsFiltro;
      BtnFiltro.Visible             := true;
end;

function TRel0008.DefineAlturaEndereco(pDsBloco: String): Integer;
begin
      if (Trim(pDsBloco) <> '') then
      begin
            Result := 10;
      end else
      begin
            Result := 23;
      end;
end;

procedure TRel0008.QRBand2BeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
var lNrEndere : String;
    lNrCdBarr : String;
    lDsBloco :  String;
begin
      inherited;

      if gNrPredio <> QpEndere.FieldByName('NrPredio').AsInteger then
      begin
            gQtEndere := QuantidadeEnderecoPredio;
      end;

      if gQtEndere mod 2 > 0 then
      begin
            BrvQRLblEndere1.Text := '';
            BrvQrBarCode1.Digits := '';

            BrvQRLblEndere2.Top  := DefineAlturaEndereco(QpEndere.FieldByName('DsBloco').AsString);
            BrvQRLblEndere2.Text := EnderecoFormatado;
            BrvQrBarCode2.Digits := FormatFloat('0000000',
                                                        QpEndere.FieldByName('NrCodigo').AsInteger);
            gQtEndere            := 0;
      end else
      begin
            BrvQRLblEndere1.Top  := DefineAlturaEndereco(QpEndere.FieldByName('DsBloco').AsString);
            BrvQRLblEndere1.Text := EnderecoFormatado;
            BrvQrBarCode1.Digits := FormatFloat('0000000',
                                                        QpEndere.FieldByName('NrCodigo').AsInteger);

            QpEndere.Next;

            if not QpEndere.Eof then
            begin
                  BrvQRLblEndere2.Top  := DefineAlturaEndereco(
                                                          QpEndere.FieldByName('DsBloco').AsString);
                  BrvQRLblEndere2.Text := EnderecoFormatado;
                  BrvQrBarCode2.Digits := FormatFloat('0000000',
                                                        QpEndere.FieldByName('NrCodigo').AsInteger);
            end else
            begin
                  BrvQRLblEndere2.Text := '';
                  BrvQrBarCode2.Digits := '';
            end;
      end;
      gNrPredio := QpEndere.FieldByName('NrPredio').AsInteger;
end;


function TRel0008.QuantidadeEnderecoPredio : Integer;
begin
      CpEndAux.Data   := QpEndere.Data;
      CpEndAux.Filter :=  ' NrRua    = ' + QpEndere.FieldByName('NrRua').AsString    + ' and ' +
                          ' NrPredio = ' + QpEndere.FieldByName('NrPredio').AsString + ' and ' +
                          ' DsBloco  = ' + QuotedStr(QpEndere.FieldByName('DsBloco').AsString);
      CpEndAux.Filtered := True;

      Result := CpEndAux.RecordCount;

      CpEndAux.Close;
end;

procedure TRel0008.QRBand4BeforePrint(Sender: TQRCustomBand; var PrintBand: Boolean);
begin
      BrvQRLblNivel.Left    := 15;
      BrvQRLblEndere3.Left  := 15;
      BrvQrBarCode3.Left    := 55;

      case QpEndere.FieldByName('NrCount' ).AsInteger of
           10: begin
                     QRImgSetaLong.Picture := ImgSetaDir.Picture;
               end;
           20: begin
                     QRImgSetaLong.Picture := ImgSetaEsq.Picture;
               end;
           else
               begin
                     QRImgSetaLong.Picture := ImgSemSeta.Picture;
                     BrvQRLblNivel.Left    := 90;
                     BrvQRLblEndere3.Left  := 90;
                     BrvQrBarCode3.Left    := 125;
               end;
      end;

      //Enio Comentou manter até 20/06/2013
      //BrvQRLblEndere3.Top     := DefineAlturaEndereco(QpEndere.FieldByName('DsBloco').AsString);
//      BrvQRLblEndere3.Caption := EnderecoFormatado;
//      BrvQRLblNivel.Caption   := FormatFloat('00',  QpEndere.FieldByName('NrNivel' ).AsInteger);
//      BrvQrBarCode3.Digits    := FormatFloat('0000000', QpEndere.FieldByName('NrCodigo').AsInteger);
end;

procedure TRel0008.RGEtiquetaClick(Sender: TObject);
begin
      if RGEtiqueta.ItemIndex = 0 then
      begin
            TbsFiltro.PageControl.Visible := true;
            TbsColMod1.TabVisible         := false;
            TbsLongMod1.TabVisible        := false;
            TabSheet1.TabVisible          := false;
            TabSheet2.TabVisible          := false;
            PgcEtiqueta.ActivePage        := TbsFiltro;
            BtnFiltro.Visible             := true;
      end else
      begin
            TbsColMod1.TabVisible  := false;
            TbsLongMod1.TabVisible := false;
            TabSheet1.TabVisible   := false;
            TbsFiltro.TabVisible   := false;
            PgcEtiqueta.ActivePage := TabSheet2;
            BtnFiltro.Visible      := false;
      end;
end;

initialization
      RegisterClass(TRel0008);

finalization
      UnRegisterClass(TRel0008);




end.
