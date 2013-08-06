unit USRA0001;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, USRA0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, DB, DBClient,
  StdCtrls, BrvRetCon, BrvBitBtn, Grids, DBGrids, FileCtrl, ComCtrls, SevenZipVCL,
  BrvDbGrids, BrvDbGrid;

type
  TSRA0001 = class(TSRA0000)
    CdsAplCli: TClientDataSet;
    DtsAplCli: TDataSource;
    Panel1: TPanel;
    Label8: TLabel;
    BrvSpeedButton2: TBrvSpeedButton;
    LblPasta: TBrvRetCon;
    BotProces: TBrvBitBtn;
    DbgAtuali: TBrvDBGrid;
    DlgDireto: TOpenDialog;
    FlbDireto: TFileListBox;
    PnlProces: TPanel;
    Panel3: TPanel;
    Panel4: TPanel;
    PgbCompac: TProgressBar;
    PgbGeral: TProgressBar;
    SevenZip: TSevenZip;
    SbrConfirm: TBrvSpeedButton;
    Label1: TLabel;
    procedure BrvSpeedButton2Click(Sender: TObject);
    procedure BotProcesClick(Sender: TObject);
    procedure SevenZipProgress(Sender: TObject; Filename: WideString; FilePosArc,
      FilePosFile: Int64);
    procedure SevenZipPreProgress(Sender: TObject; MaxProgress: Int64);
    procedure SbrConfirmClick(Sender: TObject);
    procedure CdsAplCliBeforeInsert(DataSet: TDataSet);
    procedure BrvSpeedButton1Click(Sender: TObject);
  private
    procedure ProcessarArquivos(pNmDireto: String; pNmDirAbs : String);
    procedure ProcessarDiretorios(pNmDireto: String; pNmDirAbs : String);
    procedure GravarRegistro(pNmFile   : String; pDtFile: String; pTpFile: String;
                             pNmDirAbs : String);
    procedure CompactarArquivo(pNmArquiv: String; pNmArqZip : String; pNmDireto: String);
    procedure ExcluirArquivos(pNmDireto: String);
    function FormatarDataArquivo(pDtArquiv: TDateTime): TDateTime;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  SRA0001: TSRA0001;

implementation

{$R *.dfm}

procedure TSRA0001.ProcessarArquivos(pNmDireto : String; pNmDirAbs : String);
var lNrArquiv : Integer;
    lNmFile   : String;
begin
      FlbDireto.Directory := pNmDireto;

      for lNrArquiv := 0 to FlbDireto.Items.Count -1 do
      begin
           if pNmDireto[Length(pNmDireto)] <> '\' then
           begin
                 lNmFile :=  pNmDireto + '\' + FlbDireto.Items.Strings[lNrArquiv];
           end else
           begin
                 lNmFile := pNmDireto + FlbDireto.Items.Strings[lNrArquiv];
           end;

           GravarRegistro(lNmFile,
                          DateTimeToStr(FormatarDataArquivo(
                                              FileDateToDateTime(FileAge(lNmFile)))), 'A',
                          pNmDirAbs);
      end;
end;

function TSRA0001.FormatarDataArquivo(pDtArquiv : TDateTime) : TDateTime;
var lDtAno   : Word;
    lDtMes   : Word;
    lDtDia   : Word;
    lHoHora  : Word;
    lHoMinuto: Word;
    lHoSegund: Word;
    lHoMilSeg: Word;

    lDsResult : String;
begin
      DecodeDate(pDtArquiv, lDtAno, lDtMes, lDtDia);
      DecodeTime(pDtArquiv, lHoHora, lHoMinuto, lHoSegund, lHoMilSeg);

      lDsResult := IntToStr(lDtDia)  + '/' + IntToStr(lDtMes)    + '/' + IntToStr(lDtAno)+
             ' ' + IntToStr(lHohora) + ':' + IntToStr(lHoMinuto) + ':00';

      Result := StrToDateTime(lDsResult);
end;

procedure TSRA0001.GravarRegistro(pNmFile   : String; pDtFile : String; pTpFile : String;
                                  pNmDirAbs : String);
begin
      CdsAplCli.Append;
      CdsAplCli.Tag := CdsAplCli.Tag + 1;
      CdsAplCli.FieldByName('NrArquiv').AsInteger := CdsAplCli.Tag;
      CdsAplCli.FieldByName('NmArquiv').AsString  := pNmFile;

      Delete(pNmFile, 1, Length(pNmDirAbs));


      CdsAplCli.FieldByName('NmArqCli').AsString  := pNmFile;
      CdsAplCli.FieldByName('TpArquiv').AsString  := pTpFile;
      CdsAplCli.FieldByName('SnCompac').AsString  := 'S';
      CdsAplCli.FieldByName('SnObriga').AsString  := 'N';

      if pDtFile <> '' then
      begin
             CdsAplCli.FieldByName('DtArquiv').AsString := pDtFile;
      end;
      CdsAplCli.Post;
end;

procedure TSRA0001.ProcessarDiretorios(pNmDireto : String; pNmDirAbs : String);
var lNrDireto : Integer;
    lDsDireto : TDirectoryListBox;
begin
      ProcessarArquivos(pNmDireto, pNmDirAbs);

      try
          lDsDireto           := TDirectoryListBox.Create(Self);
          lDsDireto.Visible   := False;
          lDsDireto.Parent    := Self;
          lDsDireto.Directory := pNmDireto;

          for lNrDireto := lDsDireto.ItemIndex + 1 to lDsDireto.Items.Count -1 do
          begin
                if pNmDireto[Length(pNmDireto)] <> '\' then
                begin
                       pNmDireto := pNmDireto + '\';
                end;

                GravarRegistro(pNmDireto + lDsDireto.Items.Strings[lNrDireto], '', 'D',
                                           pNmDirAbs);

                ProcessarDiretorios(pNmDireto + lDsDireto.Items.Strings[lNrDireto],
                                    pNmDirAbs);
          end;
      finally
          FreeAndNil(lDsDireto);
      end;
end;

procedure TSRA0001.SevenZipPreProgress(Sender: TObject; MaxProgress: Int64);
begin
       if Maxprogress > 0 then
       begin
             PgbCompac.Max := MaxProgress;
       end;
end;

procedure TSRA0001.SevenZipProgress(Sender: TObject; Filename: WideString; FilePosArc,
  FilePosFile: Int64);
begin
      PgbCompac.Position := FilePosFile;
end;

procedure TSRA0001.ExcluirArquivos(pNmDireto : String);
var lNrArquiv : Integer;
    lNmFile   : String;
begin
      FlbDireto.Directory := pNmDireto;

      for lNrArquiv := 0 to FlbDireto.Items.Count -1 do
      begin
           if pNmDireto[Length(pNmDireto)] <> '\' then
           begin
                 lNmFile :=  pNmDireto + '\' + FlbDireto.Items.Strings[lNrArquiv];
           end else
           begin
                 lNmFile := pNmDireto + FlbDireto.Items.Strings[lNrArquiv];
           end;

           DeleteFile(lNmFile);
      end;
end;

procedure TSRA0001.BotProcesClick(Sender: TObject);
var lNmDireto : String;
begin
      SelectDirectory(lNmDireto, [sdAllowCreate, sdPerformCreate, sdPrompt], 1);

      if lNmDireto <> '' then
      begin
            PgbGeral.Position := 0;
            PgbGeral.Max      := CdsAplCli.RecordCount;

            ExcluirArquivos(lNmDireto);

            PnlProces.Visible := True;
            PnlProces.Refresh;

            CdsAplCli.First;
            while not CdsAplCli.Eof  do
            begin
                  if CdsAplCli.FieldByName('TpArquiv').AsString = 'A' then
                  begin
                        CdsAplCli.Edit;

                        if CdsAplCli.FieldByName('SnCompac').AsString = 'S' then
                        begin
                              CdsAplCli.FieldByName('NmZipFil').AsString :=
                                    lNmDireto + '\' +
                                    FormatFloat('0000',
                                    CdsAplCli.FieldByName('NrArquiv').AsInteger ) + '.7z';


                              CompactarArquivo(CdsAplCli.FieldByName('NmArquiv').AsString,
                                               CdsAplCli.FieldByName('NmZipFil').AsString,
                                               lNmDireto);
                        end else
                        begin
                              CdsAplCli.FieldByName('NmZipFil').AsString :=
                                        lNmDireto + '\' +
                                        FormatFloat('0000',
                                        CdsAplCli.FieldByName('NrArquiv').AsInteger ) +
                                        '.brv';

                              CopyFile(PChar(CdsAplCli.FieldByName('NmArquiv').AsString),
                                       PChar(CdsAplCli.FieldByName('NmZipFil').AsString),
                                       False);
                        end;
                        CdsAplCli.Post;
                  end;

                  CdsAplCli.Next;
                  PgbGeral.StepIt;
            end;

            PnlProces.Visible  := False;
            SbrConfirm.Enabled := True;
      end;
end;

procedure TSRA0001.CdsAplCliBeforeInsert(DataSet: TDataSet);
begin
      if DataSet.Tag = 0 then
      begin
            Abort;
      end;
end;

procedure TSRA0001.CompactarArquivo(pNmArquiv : String; pNmArqZip : String;
                                    pNmDireto : String);
begin
      try
          PgbCompac.Position := 0;


          SevenZip.SZFileName := pNmArqZip;
          SevenZip.Files.Clear;
          SevenZip.Files.AddString(pNmArquiv);
          SevenZip.Add;
      except
          raise;
      end;
end;

procedure TSRA0001.BrvSpeedButton1Click(Sender: TObject);
begin
      if SbrConfirm.Enabled then
      begin
            if MessageDlg('Confirma cancelamento da atualização?', mtConfirmation,
                                                           [mbYes, mbNo], 0) = idYes then
            begin
                  inherited;
            end;
      end else
      begin
            inherited;
      end;
end;

procedure TSRA0001.BrvSpeedButton2Click(Sender: TObject);
var lNmDireto : String;
    lNrDireto : Integer;
begin
      SelectDirectory(lNmDireto, [sdAllowCreate, sdPerformCreate, sdPrompt], 1);

      if lNmDireto <> ''  then
      begin
             CdsAplCli.Tag := 1;
             CdsAplCli.EmptyDataSet;
             LblPasta.Text := lNmDireto;

             if lNmDireto[Length(lNmDireto)] <> '\' then
             begin
                   lNmDireto := lNmDireto + '\';
             end;
             processarDiretorios(lNmDireto, lNmDireto);
             CdsAplCli.Tag := 0;

             BotProces.Enabled := True;
      end;
end;

procedure TSRA0001.SbrConfirmClick(Sender: TObject);
begin
      if CdsAplCli.RecordCount = 0 then
      begin
            raise Exception.Create('Não há atualização definida');
      end;

      ModalResult := MrOk;
end;

end.
