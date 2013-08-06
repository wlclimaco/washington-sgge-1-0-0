unit BrvRelAscII;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Buttons, ExtCtrls, BrvDbNavCop, StdCtrls, ComCtrls, ExtDlgs,
  BrvDialogoImpressao, Menus, Db, DBTables, Spin, OleServer, BrvProgress,
  Word2000, Variants, BrvSpeedButton;

type
  TFrmRelAscII = class(TForm)
    DbNavCop: TBrvDbNavCop;
    btnSalvar: TBrvSpeedButton;
    btnExcluir: TBrvSpeedButton;
    btnBuscar: TBrvSpeedButton;
    btnImprimir: TBrvSpeedButton;
    btnFechar: TBrvSpeedButton;
    btnNovo: TBrvSpeedButton;
    DlgOpen: TOpenDialog;
    RchRelat: TRichEdit;
    BtnSavCom: TBrvSpeedButton;
    BtnCopiar: TBrvSpeedButton;
    BtnRecort: TBrvSpeedButton;
    BtnColar: TBrvSpeedButton;
    SbtLocali: TBrvSpeedButton;
    PopupMenu: TPopupMenu;
    Arquivo2: TMenuItem;
    Novo2: TMenuItem;
    Abrir2: TMenuItem;
    N4: TMenuItem;
    Salvar2: TMenuItem;
    Salvarcomo2: TMenuItem;
    N5: TMenuItem;
    Imprimir2: TMenuItem;
    N6: TMenuItem;
    Sair2: TMenuItem;
    Editar2: TMenuItem;
    MnuRecort: TMenuItem;
    MnuCopiar: TMenuItem;
    MnuColar: TMenuItem;
    N7: TMenuItem;
    Localizar2: TMenuItem;
    Localizarprxima1: TMenuItem;
    DlgImpres: TBrvDialogoImpressao;
    Label1: TLabel;
    CbxTmFonte: TComboBox;
    SbtRelGra: TBrvSpeedButton;
    DlgSave: TSaveDialog;
    CbxTpArquiv: TComboBox;
    WordApplication: TWordApplication;
    WordDocument: TWordDocument;
    procedure btnFecharClick(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure btnNovoClick(Sender: TObject);
    procedure btnSalvarClick(Sender: TObject);
    procedure btnBuscarClick(Sender: TObject);
    procedure btnExcluirClick(Sender: TObject);
    procedure btnImprimirClick(Sender: TObject);
    procedure BtnSavComClick(Sender: TObject);
    procedure BtnCopiarClick(Sender: TObject);
    procedure RchRelatKeyUp(Sender: TObject; var Key: Word; Shift: TShiftState);
    procedure BtnRecortClick(Sender: TObject);
    procedure RchRelatMouseMove(Sender: TObject; Shift: TShiftState; X, Y: Integer);
    procedure BtnColarClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure SbtLocaliClick(Sender: TObject);
    procedure Localizarprxima1Click(Sender: TObject);
    procedure CbxTmFonteChange(Sender: TObject);
    procedure SbtRelGraClick(Sender: TObject);
    procedure CbxTpArquivKeyPress(Sender: TObject; var Key: Char);
  private
    { Private declarations }
    DsLocali  : String;
    Function  SalvarRelatorio : Word;
    Function  SalvarComo      : Boolean;
    Procedure AbrirRelatorio;
    Procedure NovoArquivo;
    Procedure VerificarTextoSelecionado;
    procedure Localizar;
    procedure RelatorioQuickReport;
    procedure RelatorioWord;
  public
    Negrito : Boolean;
    procedure ImprimirRelatorio(NrCopias : Integer; NmImpres : String;
                                NrImpres : Integer; SnConden : Boolean);
    { Public declarations }
    function  Salvar          : Boolean;
  end;

var
  FrmRelAscII: TFrmRelAscII;

implementation

uses URELTR0001, BrvRelAscIII;

{$R *.DFM}

procedure TFrmRelAscII.FormCreate(Sender: TObject);
begin
      DsLocali              := '';
      CbxTmFonte.ItemIndex  := 4;
      CbxTpArquiv.ItemIndex := 0;
end;

//=-=-=-=-=-=-=-
//=-=-= ROTINAS EXECUTADAS PELOS EDITOR
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
procedure TFrmRelAscII.btnNovoClick(Sender: TObject);
begin
      case SalvarRelatorio of
           mrYes: begin
                        if Salvar then
                        begin
                              NovoArquivo;
                        end;
                  end;
           mrNo:  begin
                        NovoArquivo;
                  end;
      end;
end;

procedure TFrmRelAscII.NovoArquivo;
begin
      RchRelat.Lines.Clear;
      FrmRelAscII.Caption  := '';
      RchRelat.Modified    := False;
end;

function TFrmRelAscII.SalvarRelatorio : Word;
begin
      if  RchRelat.Modified then
      begin
            Result := MessageDlg('Deseja salvar alterações no relatório?',
                                     mtConfirmation, [mbYes, mbNo,mbCancel], 0);
      end else
      begin
            Result := MrNo;
      end;
end;

function TFrmRelAscII.Salvar : Boolean;
begin
      if  FrmRelAscII.Caption <> '' then
      begin
            try
                RchRelat.Lines.SaveToFile(FrmRelAscII.Caption);
                RchRelat.Modified := False;
                Result  := True;
            except
                result  := SalvarComo;
            end;
      end else
      begin
            result  := SalvarComo;
      end;
end;

function TFrmRelAscII.SalvarComo: boolean;
begin
      Result  :=  False;

      if  DlgSave.Execute then
      begin
{
            if (ofExtensionDifferent in DlgSave.Options) then
            begin
                  MessageDlg('Usuário sem permissão para alterar extensão do ' +
                                               'relatório', MtError, [MbOk], 0);
                  Result           :=  False;
            end else
            begin
}
                  FrmRelAscII.Caption  :=  DlgSave.FileName;
                  Result               :=  Salvar;
//            end;
      end;
end;

procedure TFrmRelAscII.btnSalvarClick(Sender: TObject);
begin
      Salvar;
end;

procedure TFrmRelAscII.BtnSavComClick(Sender: TObject);
begin
      SalvarComo;
end;

procedure TFrmRelAscII.btnBuscarClick(Sender: TObject);
begin
      case SalvarRelatorio of
           mrYes: begin
                        if Salvar then
                        begin
                              AbrirRelatorio;
                        end;
                  end;
           mrNo:  begin
                        AbrirRelatorio;
                  end;
      end;
end;

procedure TFrmRelAscII.AbrirRelatorio;
begin
      if  DlgOpen.Execute then
      begin
{
            if (ofExtensionDifferent in DlgOpen.Options) then
            begin
                  MessageDlg('Usuário sem permissão para alterar extensão do ' +
                                               'relatório', MtError, [MbOk], 0);
            end else
            begin
}
                  FrmRelAscII.Caption := DlgOpen.FileName;;
                  RchRelat.Lines.LoadFromFile(FrmRelAscII.Caption);
                  RchRelat.Modified   := False;
//            end;
      end;
end;

procedure TFrmRelAscII.btnExcluirClick(Sender: TObject);
begin
      if  FrmRelAscII.Caption <> '' then
      begin
            if  MessageDlg('Deseja realmente excluir o relatório?',
                              mtConfirmation, [mbYes, mbCancel], 0) = MrYes then
            begin
                  DeleteFile(FrmRelAscII.Caption);
                  NovoArquivo;
            end;
      end;
end;

procedure TFrmRelAscII.btnImprimirClick(Sender: TObject);
//var DsConfig  : String;
begin
      if  DlgImpres.Execute then
      begin
            ImprimirRelatorio(DlgImpres.NumeroCopias,     DlgImpres.Impressora,
                              DlgImpres.ImpressoraNumero, DlgImpres.ImprimeCondensado);
      end;
end;

procedure TFrmRelAscII.ImprimirRelatorio(NrCopias : Integer; NmImpres : String;
                                      NrImpres : Integer; SnConden : Boolean);
var DsImpres : RELTR0001;
begin
      DsImpres          := RELTR0001.Create(True);
      DsImpres.NrCopias := NrCopias;
      DsImpres.NmImpres := NmImpres;
      DsImpres.NrImpres := NrImpres;
      DsImpres.TxRelato := RchRelat.Lines;
      DsImpres.SnConden := SnConden;
      DsImpres.Resume;
end;

procedure TFrmRelAscII.btnFecharClick(Sender: TObject);
begin
      Close;
end;

procedure TFrmRelAscII.FormClose(Sender: TObject;
  var Action: TCloseAction);
begin
      case SalvarRelatorio of
           mrYes: begin
                        if Salvar then
                        begin
                              Action := caFree;
                        end;
                  end;
           mrNo:  begin
                        Action := caFree;
                  end;
           else Action := caNone;
      end;
end;

procedure TFrmRelAscII.BtnCopiarClick(Sender: TObject);
begin
      RchRelat.CopyToClipboard;
end;

procedure TFrmRelAscII.BtnRecortClick(Sender: TObject);
begin
      RchRelat.CutToClipboard;
end;

procedure TFrmRelAscII.BtnColarClick(Sender: TObject);
begin
      RchRelat.PasteFromClipboard;
end;

procedure TFrmRelAscII.SbtLocaliClick(Sender: TObject);
var DsString : String;
begin
      DsString := InputBox('Localizar', 'Localizar: ', '');

      if  DsString <> '' then
      begin
            DsLocali := DsString;
            Localizar;
      end;
end;

procedure TFrmRelAscII.Localizarprxima1Click(Sender: TObject);
begin
      if  DsLocali <> '' then
      begin
            Localizar;
      end else
      begin
            SbtLocaliClick(nil);
      end;
end;

procedure TFrmRelAscII.Localizar;
var NrPosica : LongInt;
begin
      NrPosica := RchRelat.FindText(
                  DsLocali,                                  // o quê
                  RchRelat.SelStart + RchRelat.SelLength,    // de
                  Length(RchRelat.Text) - RchRelat.SelStart, //até
                  []);

      if  NrPosica <> -1 then
      begin
            RchRelat.SelStart  := NrPosica;
            RchRelat.SelLength := Length(DsLocali);
      end else
      begin
            MessageDlg('Ocorrência "' + DsLocali + '" não encontrada',
                        MtInformation, [MbOK], 0);
      end;
end;

procedure TFrmRelAscII.RchRelatKeyUp(Sender: TObject; var Key: Word;
  Shift: TShiftState);
begin
      VerificarTextoSelecionado;
end;

procedure TFrmRelAscII.RchRelatMouseMove(Sender: TObject; Shift: TShiftState;
  X, Y: Integer);
begin
      VerificarTextoSelecionado;
end;

procedure TFrmRelAscII.VerificarTextoSelecionado;
begin
      if  RchRelat.SelLength > 0 then
      begin
            BtnCopiar.Enabled    :=  True;
            MnuCopiar.Enabled    :=  True;
            BtnRecort.Enabled    :=  True;
            MnuRecort.Enabled    :=  True;
      end else
      begin
            BtnCopiar.Enabled    :=  False;
            MnuCopiar.Enabled    :=  False;
            BtnRecort.Enabled    :=  False;
            MnuRecort.Enabled    :=  False;
      end;
end;

procedure TFrmRelAscII.CbxTmFonteChange(Sender: TObject);
begin
      RchRelat.Font.Size := StrToInt(CbxTmFonte.Text);
end;

procedure TFrmRelAscII.SbtRelGraClick(Sender: TObject);
begin
      case CbxTpArquiv.ItemIndex of
           0: RelatorioQuickReport();
           1: RelatorioWord();
      end;
end;

procedure TFrmRelAscII.RelatorioWord;
var DsWord  : _Document;
    NrLinha : Integer;
begin
      try
          FrmProgress := TFrmProgress.Create(Self);
          FrmProgress.Show;
          FrmProgress.Gauge.Max := RchRelat.Lines.Count;


          WordApplication.Connect;

          WordApplication.ScreenUpdating := True;

          DsWord := WordApplication.Documents.Add(EmptyParam, EmptyParam,
                                                  EmptyParam, EmptyParam);

          WordDocument.ConnectTo(DsWord);

          WordDocument.PageSetup.TopMargin    := 28;
          WordDocument.PageSetup.BottomMargin := 28;
          WordDocument.PageSetup.RightMargin  := 20;

          WordDocument.Range.Font.Bold        := 0;
          WordDocument.Range.Font.Name        := 'Courier New';

          if Length(RchRelat.Lines.Strings[0]) = 80 then
          begin
                WordDocument.PageSetup.LeftMargin         := 28;
                WordDocument.Range.Font.Size              := 10;
                WordDocument.Range.Paragraphs.LineSpacing := 13;
          end else
          begin
                WordDocument.PageSetup.LeftMargin         := 20;
                WordDocument.Range.Font.Size              := 07;
                WordDocument.Range.Paragraphs.LineSpacing := 18;
          end;

          FrmProgress.Caption := 'Gerando arquivo WORD ...';
          FrmProgress.Refresh;

          for NrLinha := 0 to RchRelat.Lines.Count - 1 do
          begin
                if  RchRelat.Lines.Strings[NrLinha] = '' then
                begin
                      WordDocument.Sections.Add(EmptyParam, EmptyParam);
                end else
                begin
                      WordDocument.Range.FormattedText.InsertAfter(RchRelat.Lines[NrLinha]);
                      WordDocument.Range.FormattedText.InsertParagraphAfter;
                end;

                FrmProgress.Gauge.StepIt;
          end;

          WordApplication.Visible := True;

          WordDocument.Disconnect;
          WordApplication.Disconnect;

      finally
          FrmProgress.Close;
          FreeAndNil(FrmProgress);
      end;
end;

procedure TFrmRelAscII.RelatorioQuickReport;
var NrLinha  : Integer;
    NrColExt : Integer;
    NmEmpres : String;
    NmFormul : String;
    DsTitulo : String;
begin
      if RchRelat.Lines.Count > 0 then
      begin
            try
                FrmProgress := TFrmProgress.Create(Self);
                FrmProgress.Show;
                FrmProgress.Gauge.Max := RchRelat.Lines.Count;

                RelAscIII := TRelAscIII.Create(Self);

                if Negrito then
                begin
                      RelAscIII.Detail.Font.Style := [fsBold];
                end;

                NrColExt := 0;

                if Length(RchRelat.Lines.Strings[0]) = 40 then
                begin
                      NrColExt := 26;
                end else
                begin
                      if Length(RchRelat.Lines.Strings[0]) = 80 then
                      begin
                            NmEmpres := Copy(RchRelat.Lines.Strings[1], 01, 40);
                            NmFormul := Copy(RchRelat.Lines.Strings[1], 51, 30);
                            DsTitulo := Copy(RchRelat.Lines.Strings[2], 01, 50);
                      end else
                      begin
                            if Length(RchRelat.Lines.Strings[0]) = 132 then
                            begin
                                  NmEmpres := Copy(RchRelat.Lines.Strings[1],  01, 40);
                                  NmFormul := Copy(RchRelat.Lines.Strings[1], 103, 30);
                                  DsTitulo := Copy(RchRelat.Lines.Strings[2],  01, 90);
                                  RelAscIII.QLblDsLinha.Font.Size := 7;
                            end;
                      end;
                end;

                RelAscIII.CriarTabelaTemporaria;

                FrmProgress.Caption         := 'Gerando arquivo QUICK REPORT ...';
                FrmProgress.Refresh;

                for NrLinha := 0 to RchRelat.Lines.Count - 1 do
                begin
                      RelAscIII.TblRelAsc.Append;

                      if  RchRelat.Lines.Strings[NrLinha] = ''  then
                      begin
                            if NrLinha < RchRelat.Lines.Count - 1 then
                            begin
                                  RelAscIII.TblRelAsc.FieldByName('DsLinha').AsString :=
                                                                             'NovaPagina';
                            end;
                      end else
                      begin

                            if NrColExt = 0 then
                               RelAscIII.TblRelAsc.FieldByName('DsLinha').AsString :=
                                                           RchRelat.Lines.Strings[NrLinha]
                            else
                               RelAscIII.TblRelAsc.FieldByName('DsLinha').AsString :=
                                                             StringOfChar(' ', NrColExt) +
                                                          RchRelat.Lines.Strings[NrLinha];

                      end;

                      RelAscIII.TblRelAsc.Post;
                      
                      FrmProgress.Gauge.StepIt;
                end;

                FrmProgress.Close;
                FreeAndNil(FrmProgress);

                RelAscIII.Preview;
            finally
                RelAscIII.TblRelAsc.Active := False;
                RelAscIII.Destroy;
            end;
      end;
end;

procedure TFrmRelAscII.CbxTpArquivKeyPress(Sender: TObject; var Key: Char);
begin
      Key := Char(0);
end;

end.
