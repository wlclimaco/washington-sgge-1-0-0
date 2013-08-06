unit uCon0009;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvEditNum,
  ComCtrls, DB, DBClient, BrvClientDataSet, BrvXml, Grids, DBGrids, BrvString, BrvRetCon,
  BrvListParam, ImgList, Menus;

type
  TCon0009 = class(TMov0000)
    PnlChave: TPanel;
    Label1: TLabel;
    BrvSpeedButton2: TBrvSpeedButton;
    PgcSubIte: TPageControl;
    PgcCorpo: TPageControl;
    TbsNada: TTabSheet;
    TbsSubNfe: TTabSheet;
    TbsSubCte: TTabSheet;
    QpXML: TBrvClientDataSet;
    EdtNrChaXML: TEdit;
    BrvXML: TBrvXML;
    PgcNfe: TPageControl;
    TbsNfePro: TTabSheet;
    TabSheet1: TTabSheet;
    CdsNfeFat: TClientDataSet;
    DtsNfeFat: TDataSource;
    DBGrid1: TDBGrid;
    DBGrid2: TDBGrid;
    DtsNfePro: TDataSource;
    CdsNfePro: TClientDataSet;
    DtsNfe: TDataSource;
    CdsNfe: TClientDataSet;
    DtsCte: TDataSource;
    CdsCte: TClientDataSet;
    DtsCteCar: TDataSource;
    CdsCteCar: TClientDataSet;
    DtsCteNFs: TDataSource;
    CdsCteNFs: TClientDataSet;
    DtsCtePre: TDataSource;
    CdsCtePre: TClientDataSet;
    PgcCTE: TPageControl;
    TbsCteNfs: TTabSheet;
    DBGrid3: TDBGrid;
    TabSheet3: TTabSheet;
    DBGrid4: TDBGrid;
    TabSheet2: TTabSheet;
    DBGrid5: TDBGrid;
    SbtXML: TBrvSpeedButton;
    BrvString: TBrvString;
    procedure FormCreate(Sender: TObject);
    procedure BrvSpeedButton2Click(Sender: TObject);
    procedure SbtXMLClick(Sender: TObject);
    procedure EdtNrChaXMLKeyPress(Sender: TObject; var Key: Char);
  private
    procedure CriarCamposCorpoXML(pCdsCorpo : TClientDataSet);
    function CriarTabSheetCorpo(pDsCaption : String): TScrollBox;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Con0009: TCon0009;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TCon0009.BrvSpeedButton2Click(Sender: TObject);
begin
      inherited;

      if EdtNrChaXML.Text = '' then
      begin
            raise Exception.Create('Chave do XML deve ser informada.');
      end;

      QpXml.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(
                                            68, '<%NrChaDoc%>;' + EdtNrChaXML.Text, Name);

      if QpXml.Eof then
      begin
            raise Exception.Create('Chave não encontrada');
      end;

      BrvXML.BrXMLOriginal.Text := QpXml.FieldByName('TxXML').AsString;
      BrvXML.ProcessarXml;

      if QpXml.FieldByName('TpXML').AsString = 'NFE' then
      begin
            PgcNfe.ActivePage    := TbsNfePro;
            PgcSubIte.ActivePage := TbsSubNfe;
            CriarCamposCorpoXML(CdsNfe);
      end else
      begin
            PgcCTE.ActivePage    := TbsCteNfs;
            PgcSubIte.ActivePage := TbsSubCte;
            CriarCamposCorpoXML(CdsCte);
      end;

      SbtXML.Enabled := True;

      EdtNrChaXML.Text := '';
end;

procedure TCon0009.CriarCamposCorpoXML(pCdsCorpo : TClientDataSet);
var lNrCampo  : Integer;
    lNmCampo  : String;
    lNmTabAnt : String;
    lTabSheet : TScrollBox;
    lLabel    : TLabel;
    lEdit     : TBrvRetCon;
    lVrTop    : Integer;
begin
      lNmTabAnt := '';
      PgcCorpo.DestroyComponents;

      for lNrCampo := 0 to pCdsCorpo.Fields.Count -1 do
      begin
            lNmCampo := pCdsCorpo.Fields.Fields[lNrCampo].FieldName;

            if pos('_', lNmCampo) = 0 then
            begin
                  if lNmTabAnt = '' then
                  begin
                        lNmTabAnt := 'x';
                        lTabSheet := CriarTabSheetCorpo('.');
                        lVrTop    := 8;
                  end;
            end else
            begin
                  BrvString.Split(lNmCampo, '_');

                  if lNmTabAnt <> BrvString.BrSplitLista.Strings[0] then
                  begin
                        lNmTabAnt := BrvString.BrSplitLista.Strings[0];
                        lTabSheet := CriarTabSheetCorpo(lNmTabAnt);
                        lVrTop    := 8;
                  end;
            end;

            lLabel         := TLabel.Create(lTabSheet);
            lLabel.Parent  := lTabSheet;
            lLabel.Caption := lNmCampo;
            lLabel.Left    := 8;
            lLabel.Top     := lVrTop;

            lEdit          := TBrvRetCon.Create(lTabSheet);
            lEdit.Parent   := lTabSheet;
            lEdit.Left     := lLabel.Left + lLabel.Width + 5;
            lEdit.Top      := lVrTop;
            lEdit.Text     := pCdsCorpo.Fields.Fields[lNrCampo].AsString;
            lEdit.Width    := pCdsCorpo.Fields.Fields[lNrCampo].Size * 5;

            if lEdit.Width  = 0 then
            begin
                  lEdit.Width := PgcCorpo.Width - lEdit.Left - 10;
            end;

            inc(lVrTop, 23);
      end;
end;

function TCon0009.CriarTabSheetCorpo(pDsCaption : String) : TScrollBox;
var lTabSheet : TTabSheet;
begin
      lTabSheet := TTabSheet.Create(PgcCorpo);
      lTabSheet.PageControl := PgcCorpo;
      lTabSheet.Caption     := pDsCaption;

      Result := TScrollBox.Create(lTabSheet);
      Result.Parent      := lTabSheet;
      Result.Align       := alClient;
      Result.BorderStyle := bsNone;
end;

procedure TCon0009.EdtNrChaXMLKeyPress(Sender: TObject; var Key: Char);
begin
      if key = #13 then
      begin
            BrvSpeedButton2Click(BrvSpeedButton2);
      end;
end;

procedure TCon0009.FormCreate(Sender: TObject);
var lNrAba : Integer;
begin
      inherited;

      for lNrAba := 0 to PgcSubIte.PageCount - 1 do
      begin
            PgcSubIte.Pages[lNrAba].TabVisible := False;
      end;

      PgcSubIte.ActivePage := TbsNada;
end;

procedure TCon0009.SbtXMLClick(Sender: TObject);
var lFrmXml : TForm;
    lMemXml : TMemo;
begin
      lFrmXml := TForm.Create(Self);

      try
          lFrmXml.BorderIcons := [biSystemMenu,biMaximize];
          lFrmXml.Width       := 800;
          lFrmXml.Height      := 600;
          lFrmXml.Position    := poScreenCenter;

          lMemXml := TMemo.Create(Self);
          lMemXml.Parent      := lFrmXml;
          lMemXml.Align       := alClient;
          lMemXml.ScrollBars  := ssBoth;
          lMemXml.Font.Size   := 10;
          lMemXml.Font.Name   := 'Courier New';
          lMemXml.Lines.Text  := BrvXML.BrXMLFinal.Text;

          lFrmXml.ShowModal;
      finally
            FreeAndNil(lFrmXml);
      end;
end;

initialization
      RegisterClass(TCon0009);

finalization
      UnRegisterClass(TCon0009);

end.
