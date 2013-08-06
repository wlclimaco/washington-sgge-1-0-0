unit UCai0011;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCai0000, StdCtrls, Buttons, BrvBitBtn, ExtCtrls, DB, DBClient, Grids, DBGrids,
  FileCtrl, UClaSrv, SevenZipVCL, ComCtrls;

type
  TCai0011 = class(TCai0000)
    CdsAplCli: TClientDataSet;
    CdsAplCliDtArquiv: TDateTimeField;
    CdsAplCliNmArqCli: TStringField;
    CdsAplCliTpArquiv: TStringField;
    CdsAplCliSnCompac: TStringField;
    CdsAplCliTpOperac: TStringField;
    CdsAplCliBiArquiv: TBlobField;
    DtsAplCli: TDataSource;
    FlbDireto: TFileListBox;
    CdsAplCliQtCaract: TIntegerField;
    PgcAtuali: TPageControl;
    TbsDesenv: TTabSheet;
    DBGrid1: TDBGrid;
    Button3: TButton;
    Button2: TButton;
    Button1: TButton;
    TbsClient: TTabSheet;
    LblProces: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Button4: TButton;
    AmtCopia: TAnimate;
    Label1: TLabel;
    PgbProces: TProgressBar;
    PgbCompac: TProgressBar;
    procedure FormCreate(Sender: TObject);
    procedure Button1Click(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure Button3Click(Sender: TObject);
    procedure Button4Click(Sender: TObject);
  private
    procedure ProcessarDiretorios(pNmDireto, pNmDirAbs: String);
    procedure ProcessarArquivos(pNmDireto, pNmDirAbs: String);
    procedure GravarRegistro(pNmFile, pDtFile, pTpFile, pNmDirAbs: String);
    function FormatarDataArquivo(pDtArquiv: TDateTime): TDateTime;
    procedure ListarArquivos;
    procedure ValidarArquivosServer;
    function  ProcessarAtualizacao : Boolean;
    procedure AtualizarExecutavel(pNmArqOri, pNmArqDes: String);
    function  ProcessarTodaAtualizacao : Boolean;
    { Private declarations }
  public
    { Public declarations }
    function ProcessarAtualizacaoCliente : Boolean;
    procedure ModoDesenvolvedor;
  end;

var
  Cai0011: TCai0011;

implementation

uses UDmSrvApl, UCai0012, UFrmConCli;

{$R *.dfm}


procedure TCai0011.FormCreate(Sender: TObject);
begin
      inherited;
      CdsAplCli.CreateDataSet;
end;

// =-=-=-= Listagem dos arquivos
procedure TCai0011.Button1Click(Sender: TObject);
begin
      ListarArquivos;
end;

procedure TCai0011.ListarArquivos;
var lNmDireto : String;
begin
      PgbProces.Visible := False;
      PgbCompac.Visible := False;

      LblProces.Caption := 'Coletando informações atuais ...';
      LblProces.Refresh;

      AmtCopia.Visible := True;
      AmtCopia.Active  := True;
      AmtCopia.Refresh;

      CdsAplCli.Tag := 1;
      CdsAplCli.Filtered := False;
      CdsAplCli.EmptyDataSet;

      lNmDireto := ExtractFilePath(Application.ExeName);

      processarDiretorios(lNmDireto, lNmDireto);
end;

procedure TCai0011.ModoDesenvolvedor;
begin
      TbsDesenv.TabVisible := False;
      TbsClient.TabVisible := False;
      PgcAtuali.ActivePage := TbsDesenv;
      ShowModal;
end;

procedure TCai0011.ProcessarDiretorios(pNmDireto : String; pNmDirAbs : String);
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
                pNmDireto := pNmDireto + '\';
                GravarRegistro(pNmDireto + lDsDireto.Items.Strings[lNrDireto], '', 'D', pNmDirAbs);
                ProcessarDiretorios(pNmDireto + lDsDireto.Items.Strings[lNrDireto], pNmDirAbs);
          end;

      finally
          FreeAndNil(lDsDireto);
      end;
end;

procedure TCai0011.GravarRegistro(pNmFile   : String; pDtFile : String; pTpFile : String;
                                  pNmDirAbs : String);
begin
      CdsAplCli.Append;
      Delete(pNmFile, 1, Length(pNmDirAbs));

      CdsAplCli.FieldByName('NmArqCli').AsString  := pNmFile;
      CdsAplCli.FieldByName('TpOperac').AsString  := 'NDA';
      CdsAplCli.FieldByName('SnCompac').AsString  := 'S';
      CdsAplCli.FieldByName('TpArquiv').AsString  := pTpFile;

      if pDtFile <> '' then
      begin
             CdsAplCli.FieldByName('DtArquiv').AsString := pDtFile;
      end;
      CdsAplCli.Post;
end;

procedure TCai0011.ProcessarArquivos(pNmDireto : String; pNmDirAbs : String);
var lNrArquiv : Integer;
    lNmFile   : String;
    lDsExtens : String;
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

           lDsExtens := UpperCase(ExtractFileExt(lNmFile));

           if lDsExtens <> '.INI' then
           begin
                 GravarRegistro(lNmFile,
                          DateTimeToStr(FormatarDataArquivo(
                                              FileDateToDateTime(FileAge(lNmFile)))), 'A',
                          pNmDirAbs);
           end;
      end;
end;

function TCai0011.FormatarDataArquivo(pDtArquiv : TDateTime) : TDateTime;
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

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// =-=-=-= Validando arquivos listados no server
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
procedure TCai0011.Button2Click(Sender: TObject);
begin
      ValidarArquivosServer;
end;

procedure TCai0011.ValidarArquivosServer;
var lConexao  : TSDmRwClient;
    lDsResult : String;
begin
      LblProces.Caption := 'Baixando atualizações ...';
      LblProces.Refresh;

      lConexao := TSDmRwClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          CdsAplCli.Data := lConexao.VerificarNovaVersaoCliente(
                            DmSrvApl.BrvDicionario.Credencial, lDsResult, CdsAplCli.Data);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// =-=-=-= Processar atualização dos arquivos
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
procedure TCai0011.Button3Click(Sender: TObject);
begin
      ProcessarAtualizacao;
end;

function TCai0011.ProcessarAtualizacao : Boolean;
var lNmArquiv : String;
    lNmArqCom : String;
    lNmArqDes : String;
    lNmDireto : String;
    lNmDirCom : String;
    lNmExecut : String; // nome do executável
begin
      AmtCopia.Active  := False;
      AmtCopia.Visible := False;

      LblProces.Caption := 'Finalizando atualização ...';
      LblProces.Refresh;

      PgbProces.Position := 0;
      PgbCompac.Position := 0;

      PgbProces.Visible := True;
      PgbProces.Max     := CdsAplCli.RecordCount;

      PgbCompac.Visible := True;

      Result := False;
      CdsAplCli.IndexDefs.Clear;
      CdsAplCli.IndexDefs.Add('Key1', 'NmArqCli', [ixCaseInsensitive]);
      CdsAplCli.IndexDefs.Add('Key2', 'QtCaract', [ixCaseInsensitive, ixDescending]);
      CdsAplCli.IndexName := 'Key1';
      CdsAplCli.SetKey;

      lNmDireto := ExtractFilePath(Application.ExeName);
      lNmExecut := ExtractFileName(Application.ExeName);
      CdsAplCli.Filtered := True;

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-= Criando os Novos Diretórios
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      CdsAplCli.Filter   := 'TpArquiv = ' + #39 + 'D'   + #39 +
                       ' and TpOperac = ' + #39 + 'NEW' + #39;
      CdsAplCli.First;
      while not CdsAplCli.Eof do
      begin
            lNmArquiv := lNmDireto + CdsAplCli.FieldByName('NmArqCli').AsString;
            try
                CreateDir(lNmArquiv);
            except
                on E: Exception do
                      begin
                            raise Exception.Create('Não foi possível criar diretório ' +
                                                   lNmArquiv + #13#13 +
                                                   E.Message);
                      end;
            end;
            CdsAplCli.Next;
            PgbProces.StepIt;
      end;
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-= Excluindo arquivos desnecessários
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      CdsAplCli.Filter   := 'TpArquiv = ' + #39 + 'A'   + #39 +
                       ' and TpOperac = ' + #39 + 'EXC' + #39;
      CdsAplCli.First;
      while not CdsAplCli.Eof do
      begin
            lNmArquiv := lNmDireto + CdsAplCli.FieldByName('NmArqCli').AsString;
            try
                DeleteFile(lNmArquiv);
            except
                on E: Exception do
                      begin
                            raise Exception.Create('Não foi possível excluir arquivo ' +
                                                   lNmArquiv + #13#13 +
                                                   E.Message);
                      end;
            end;
            CdsAplCli.Next;
            PgbProces.StepIt;
      end;
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-= Baixando novos arquivos não compactados
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      CdsAplCli.Filter   := 'TpArquiv = ' + #39 + 'A'   + #39 +
                       ' and SnCompac = ' + #39 + 'N'   + #39 +
                       ' and (   TpOperac = ' + #39 + 'NEW' + #39 +
                            ' or TpOperac = ' + #39 + 'ATU' + #39 + ')';
      CdsAplCli.First;
      while not CdsAplCli.Eof do
      begin
            lNmArquiv := lNmDireto + CdsAplCli.FieldByName('NmArqCli').AsString;

            try
                DeleteFile(lNmArquiv);
                (CdsAplCli.FieldByName('BiArquiv') as TBlobField).SaveToFile(lNmArquiv);
            except
                on E: Exception do
                      begin
                            raise Exception.Create('Não foi possível salvar arquivo ' +
                                                   lNmArquiv + #13#13 +
                                                   E.Message);
                      end;
            end;
            CdsAplCli.Next;
            PgbProces.StepIt;
      end;
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-= Baixando novos arquivos compactados
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      try
          Cai0012 := TCai0012.Create(Self);
          lNmDirCom := lNmDireto + 'BrvTmpComp\';
          CreateDir(lNmDirCom);

          CdsAplCli.Filter   := 'TpArquiv = ' + #39 + 'A'   + #39 +
                           ' and SnCompac = ' + #39 + 'S'   + #39 +
                           ' and (   TpOperac = ' + #39 + 'NEW' + #39 +
                                ' or TpOperac = ' + #39 + 'ATU' + #39 + ')';
          CdsAplCli.First;
          while not CdsAplCli.Eof do
          begin
                lNmArquiv := lNmDireto + CdsAplCli.FieldByName('NmArqCli').AsString;
                lNmArqDes := lNmDirCom + ExtractFileName(lNmArquiv);
                lNmArqCom := lNmDirCom + ExtractFileName(lNmArquiv) + '.Brv';

                try
                    (CdsAplCli.FieldByName('BiArquiv') as TBlobField).SaveToFile(lNmArqCom);

                    DeleteFile(lNmArquiv);

                    Cai0012.ExtrairArquivo(lNmArqCom, lNmDirCom, PgbCompac);

                    DeleteFile(lNmArquiv);

                    if UpperCase(lNmExecut) =
                       UpperCase(CdsAplCli.FieldByName('NmArqCli').AsString) then
                    begin
                          MoveFile(PChar(lNmArqDes), PChar(lNmArquiv + '.Brv'));
                          Result := True;
                    end else
                    begin
                          MoveFile(PChar(lNmArqDes), PChar(lNmArquiv));
                    end;
                    DeleteFile(lNmArqCom);
                except
                    on E: Exception do
                          begin
                                raise Exception.Create
                                      ('Não foi possível atualiza arquivo ' +
                                      lNmArquiv + #13#13 + E.Message);
                          end;
                end;
                CdsAplCli.Next;
                PgbProces.StepIt;
          end;

          RmDir(lNmDirCom);
      finally
          FreeAndNil(Cai0012);
      end;
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-= Excluindo diretórios desnecessários
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      CdsAplCli.Filter   := 'TpArquiv = ' + #39 + 'D'   + #39 +
                       ' and TpOperac = ' + #39 + 'EXC' + #39;
      CdsAplCli.First;
      while not CdsAplCli.Eof do
      begin
            CdsAplCli.Edit;
            CdsAplCli.FieldByName('QtCaract').AsInteger :=
                                       Length(CdsAplCli.FieldByName('NmArqCli').AsString);
            CdsAplCli.Post;
            CdsAplCli.Next;
      end;

      CdsAplCli.IndexName := 'Key2';
      CdsAplCli.SetKey;
      CdsAplCli.First;
      while not CdsAplCli.Eof do
      begin
            lNmArquiv := lNmDireto + CdsAplCli.FieldByName('NmArqCli').AsString;
            try
                RmDir(lNmArquiv);
            except
               // on E: Exception do
               //       begin
               //             raise Exception.Create('Não foi possível excluir diretório ' +
               //                                    lNmArquiv + #13#13 +
               //                                    E.Message);
               //       end;
            end;
            CdsAplCli.Next;
            PgbProces.StepIt;
      end;

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

      if Result then
      begin
            AtualizarExecutavel(lNmDireto + lNmExecut + '.Brv',
                                lNmDireto + lNmExecut);
      end;
end;

procedure TCai0011.AtualizarExecutavel(pNmArqOri : String; pNmArqDes : String);
var lSuExecuca : STARTUPINFO;
    lDsComand  : String;
    lproceexec : PROCESS_INFORMATION;
begin
     lSuExecuca.cb              := 4000;
     lSuExecuca.lpReserved      := pointer(#0);
     lSuExecuca.lpDesktop       := pointer(#0);
     lSuExecuca.lpTitle         := pointer(#0);
     lSuExecuca.dwX             := 0;
     lSuExecuca.dwY             := 0;
     lSuExecuca.dwXSize         := 0;
     lSuExecuca.dwYSize         := 0;
     lSuExecuca.dwXCountChars   := 0;
     lSuExecuca.dwYCountChars   := 0;
     lSuExecuca.dwFillAttribute := 0;
     lSuExecuca.dwFlags         := STARTF_USESHOWWINDOW;
     lSuExecuca.wShowWindow     := SW_SHOWNORMAL;
     lSuExecuca.cbReserved2     := 0;
     lSuExecuca.lpReserved2     := pointer(#0);
     lSuExecuca.hStdInput       := 0;
     lSuExecuca.hStdOutput      := 0;
     lSuExecuca.hStdError       := 0;
     lDsComand := 'upgrade.exe ' + pNmArqOri + ' ' + pNmArqDes;

     CreateProcess(Pointer(#0), PChar(lDsComand), Pointer(#0), Pointer(#0),
                   True, High_Priority_Class,     Pointer(#0), Pointer(#0),
                   lSuExecuca,  lProceexec);
end;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// =-=-=-= Realizando processo completo
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

procedure TCai0011.Button4Click(Sender: TObject);
begin
      PgcAtuali.ActivePage := TbsClient;
      ProcessarTodaAtualizacao;
      PgcAtuali.ActivePage := TbsDesenv;
      MessageDlg('Processo finalizado com sucesso!', mtInformation, [mbOk], 0);
end;

function TCai0011.ProcessarAtualizacaoCliente : Boolean;
begin
      TbsDesenv.TabVisible := False;
      TbsClient.TabVisible := False;
      PgcAtuali.ActivePage := TbsClient;

      Result := ProcessarTodaAtualizacao;
end;

function TCai0011.ProcessarTodaAtualizacao : Boolean;
begin
      ListarArquivos;
      ValidarArquivosServer;
      Result := ProcessarAtualizacao;
      Close;
end;

end.
