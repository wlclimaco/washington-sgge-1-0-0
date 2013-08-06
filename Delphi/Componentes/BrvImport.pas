unit BrvImport;

interface

uses Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, Math, DB,
     DBTables, BrvDicionario, ComCtrls, BrvDataSet, Provider, BrvClientDataSet,
     DBClient, SConnect, SqlExpr;
type
  TBrvImport = class(TComponent)
  private
    { Private declarations }
    CcImport  : TBrvClientDataSet;
    FDsDicion : TBrvDicionario;
    FrmProgre : TForm;
    BarProgre : TProgressBar;
    gSrvRemot : TCustomRemoteServer;

    procedure EncontrarChaveTabela(NmFile : String;  var NmTable  : String;
                             var QtRegist : Integer; var StlChave : TStrings);

    procedure MontarSelect(NmTable : String; StlColuna : TStrings; StlChave : TStrings);

    procedure ImportarRegistros(NmFile   : String;
                                QtRegist  : Integer;  StlChave : TStrings;
                                StlColImp : TStrings; NmTabela : String);

    procedure InserirRegistros(NmFile    : String;
                               StlChave : TStrings;  StlColImp : TStrings;
                               NmTabela : String);

    procedure AtualizarInserirRegistros(NmFile   : String;
                                        StlColImp : TStrings; SnInsert : Boolean);

    function  ReceberColunasImportacao(var StlColImp : TStrings; StlColuna : TStrings;
                                           StlChave  : TStrings) : Boolean;

    function  ImportarCampo(NmField : String; StlColImp : TStrings) : Boolean;

    procedure ReconcileError(DataSet    : TCustomClientDataSet; E : EReconcileError;
                             UpdateKind : TUpdateKind; var Action : TReconcileAction);
    procedure ImportarLinha(DsLinha  : string; StlColImp : TStrings; NmTabela : String);
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    procedure   ImportTable(NmTabAtu : string);
  published
    { Published declarations }
    property BrvDicionario    : TBrvDicionario      read FDsDicion write FDsDicion;
    property BrvSqlConnection : TCustomRemoteServer read gSrvRemot write gSrvRemot;
  end;

procedure Register;

implementation

uses BrvImportForm, BrvImpExpForm;

procedure Register;
begin
      RegisterComponents('Bravo Exporta', [TBrvImport]);
end;

constructor TBrvImport.Create(AOwner: TComponent);
begin
      inherited create(AOwner);
end;

destructor TBrvImport.Destroy;
begin
      inherited  destroy;
end;

procedure TBrvImport.ImportTable(NmTabAtu : String);
var NmTable   : String;
    QtRegist  : Integer;
    StlColuna : TStrings;
    StlColImp : TStrings;
    StlChave  : TStrings;
begin
      if FDsDicion = nil then
      begin
            raise Exception.Create(
                               'BrvImport: Dicionário de dados não definido. Verifique!');
      end;

      if FDsDicion = nil then
      begin
            raise Exception.Create('BrvImport: Servidor remoto não definido. Verifique!');
      end;

      CcImport                  := TBrvClientDataSet.Create(Self);
      CcImport.BrDicionario     := BrvDicionario;
      CcImport.RemoteServer     := gSrvRemot;
      CcImport.ProviderName     := 'DpImport';
      CcImport.OnReconcileError := ReconcileError;

      FrmImport := TFrmImport.Create(Self);

      try
          if  FrmImport.ShowModal = MrOk then
          begin
                StlChave := TStringList.Create;

                EncontrarChaveTabela(FrmImport.EdtNmOrigem.Text, NmTable,
                                     QtRegist, StlChave);

                if  Trim(NmTabAtu) <> Trim(NmTable) then
                begin
                      raise Exception.Create('Tabela a ser importada "'     + NmTable  +
                                             '" não corresponde a tabela "' + NmTabAtu +
                                             '" deste formulário.' + #13#13 +
                                             'Vefique se o arquivo de importação ' +
                                             'foi selecionado corretamente!');
                end;

                try
                    StlColuna := TStringList.Create;
                    FDsDicion.AtributosDaTabela(Trim(NmTable), StlColuna);
                    MontarSelect(NmTable, StlColuna, StlChave);

                    StlColimp := TStringList.Create;

                    if  ReceberColunasImportacao(StlColImp, StlColuna, StlChave) then
                    begin
                          ImportarRegistros(FrmImport.EdtNmOrigem.Text,
                                            QtRegist, StlChave, StlColImp, NmTabAtu);
                    end;
                finally
                    StlColuna.Destroy;
                    StlColImp.Destroy;
                    StlChave.Destroy;
                end;
          end;
      finally
            FreeAndNil(CcImport);
            FreeAndNil(FrmImport);
      end;
end;

procedure TBrvImport.EncontrarChaveTabela(NmFile   : String;
                                      var NmTable  : String; var QtRegist : Integer;
                                      var StlChave : TStrings);
var DsImport : TextFile;
    DsLinha  : String;
begin
      QtRegist := 0;
      NmTable  := '';

      try
          AssignFile(DsImport, NmFile);
          Reset(DsImport);
          ReadLn(DsImport, DsLinha);

          NmTable  := Trim(Copy(DsLinha, 1, 15));
          QtRegist := StrToInt(Copy(DsLinha, 17, 6));
          FDsDicion.ChavePrimaria(NmTable, StlChave);
      finally
          CloseFile(DsImport);
      end;
end;

procedure TBrvImport.MontarSelect(NmTable   : String;
                                    StlColuna : TStrings;
                                    StlChave  : TStrings);
var DsSelect : String;
    NrField  : integer;
begin
      DsSelect := 'Select * from ' + NmTable;

      if StlChave.Count > 0 then
      begin
            DsSelect := DsSelect + ' Where ';

            for NrField := 0 to StlChave.Count -1 do
            begin
                  if  NrField > 0 then
                  begin
                        DsSelect := DsSelect + ' and ';
                  end;

                  DsSelect := DsSelect + StlChave.Strings[NrField] + ' <%' +
                                         StlChave.Strings[NrField] + '%> ';
            end;
      end;

      CcImport.Close;
      CcImport.CommandText := DsSelect;
end;

function  TBrvImport.ReceberColunasImportacao(var StlColImp : TStrings;
                               StlColuna : TStrings; StlChave : TStrings) : Boolean;
begin
      FrmImpExp := TFrmImpExp.Create(Self);
      FrmImpExp.Caption := 'Colunas para importação:';

      try
          FrmImpExp.LcbColunas.Items.Text := StlColuna.Text;
          FrmImpExp.StlChave.Text         := StlChave.Text;
          FrmImpExp.Marcartodos1Click(nil);

          if  FrmImpExp.ShowModal = MrOk then
          begin
                StlColImp.Text := FrmImpExp.StlColImp.Text;
                Result := True;
          end else
          begin
                Result := False;
          end;
      finally
          FrmImpExp.Destroy;
      end;
end;

procedure TBrvImport.ImportarRegistros(NmFile   : String;
                                       QtRegist  : Integer;   StlChave : TStrings;
                                       StlColImp : TStrings;  NmTabela : string);
//var DsTrnsct : TTransactionDesc;
begin
      FrmProgre               := TForm.CreateNew(Self);
      FrmProgre.FormStyle     := fsMDIChild;
      FrmProgre.Width         := 443;
      FrmProgre.Height        := 55;
      FrmProgre.BorderStyle   := bsSingle;
      FrmProgre.BorderIcons   := [biSystemMenu];
      FrmProgre.Caption       := 'Importando. Por favor aguarde!';
      FrmProgre.Position      := poScreenCenter;

      BarProgre               := TProgressBar.Create(Self);
      BarProgre.Parent        := FrmProgre;
      BarProgre.Top           := 5;
      BarProgre.Left          := 8;
      BarProgre.Width         := 417;
      BarProgre.Height        := 16;
      BarProgre.Step          := 1;
      BarProgre.Max           := QtRegist;

      Application.ProcessMessages;

      FrmProgre.Show;

      try
          case FrmImport.RgpOperac.ItemIndex of
               0: InserirRegistros(NmFile, StlChave, StlColImp, NmTabela);
               1: AtualizarInserirRegistros(NmFile, StlColImp, True);
               2: AtualizarInserirRegistros(NmFile, StlColImp, False);
          end;
      finally
          FrmProgre.Destroy;
      end;
end;

procedure TBrvImport.InserirRegistros(NmFile    : String;
                                      StlChave : TStrings; StlColImp : TStrings;
                                      NmTabela : String);
var DsImport : TextFile;
    DsLinha  : String;
    NrRegAtu : String;
    NrRegAnt : String;
    NrChave  : Integer;
    DsSql    : string;
    DsCapAnt : string;
begin
      try
          AssignFile(DsImport, NmFile);
          Reset(DsImport);
          ReadLn(DsImport, DsLinha);

          DsSql := CcImport.CommandText;

          for NrChave := 0 to StlChave.Count -1 do
          begin
                DsSql := StringReplace(DsSql,
                                       '<%'+ StlChave.Strings[NrChave] + '%>',
                                       ' is null', [rfReplaceAll]);
          end;

          CcImport.CommandText := DsSql;
          CcImport.Open;
          NrRegAnt := '';

          while not Eof(DsImport) do
          begin
                ReadLn(DsImport, DsLinha);

                NrRegAtu := Copy(DsLinha, 1, 6);

                if  NrRegAtu <> NrRegAnt then
                begin
                      if NrRegAnt <> '' then
                      begin
                            CcImport.Post;
                            if CcImport.RecordCount > 5000 then
                            begin
                                  DsCapAnt          := FrmProgre.Caption;
                                  FrmProgre.Caption :=
                                              'Fixando dados no banco. Por favor aguarde';
                                  FrmProgre.Refresh;

                                  CcImport.ApplyUpdates(0);
                                  CcImport.Close;
                                  CcImport.Open;
                                  
                                  FrmProgre.Caption := DsCapAnt;
                                  FrmProgre.Refresh
                            end;
                      end;

                      NrRegAnt := NrRegAtu;
                      CcImport.Append;
                      BarProgre.StepIt;
                end;

                if DsLinha[17] <> 'S' then // campo não é chave
                begin
                      ImportarLinha(DsLinha, StlColImp, '');
                end else
                begin
                      ImportarLinha(DsLinha, StlColImp, NmTabela);
                end;

                if Eof(DsImport) then
                begin
                      CcImport.Post;
                end;
          end;

          FrmProgre.Caption := 'Fixando dados no banco. Por favor aguarde';
          FrmProgre.Refresh;

          CcImport.ApplyUpdates(0);
      finally
          CcImport.Cancel;
          CloseFile(DsImport);
      end;
end;

procedure TBrvImport.AtualizarInserirRegistros(NmFile : String;
                                               StlColImp : TStrings; SnInsert  : Boolean);
var NrRegAtu : String;
    NrRegAnt : String;
    DsSqlIni : string;
    DsSql    : string;
    DsImport : TextFile;
    DsLinha  : string;
    NmField  : string;
    TmCampo  : string;
    VrCampo  : string;
    NrChave  : Integer;
    StlChave : TStrings;
begin
      try
          StlChave := TStringList.Create;
          DsSqlIni := CcImport.CommandText;
          AssignFile(DsImport, NmFile);
          Reset(DsImport);
          ReadLn(DsImport, DsLinha);

          NrRegAnt := '';

          while not Eof(DsImport) do
          begin
                ReadLn(DsImport, DsLinha);

                NrRegAtu := Copy(DsLinha, 1, 6);

                if  NrRegAtu <> NrRegAnt then
                begin
                      if (NrRegAnt <> '') and (CcImport.State in [dsEdit, dsInsert]) then
                      begin
                            CcImport.Post;
                            CcImport.ApplyUpdates(0);
                      end;

                      DsSql    := DsSqlIni;
                      NrRegAnt := NrRegAtu;
                      StlChave.Clear;
                      BarProgre.StepIt;
                end;

                if DsLinha[17] = 'S' then // é campo chave
                begin
                      NmField  := Trim(Copy(DsLinha,  8, 8));
                      TmCampo  := Copy(DsLinha, 21, 6);
                      VrCampo  := Copy(DsLinha, 28, StrToInt(TmCampo));

                      DsSql := StringReplace(DsSql,
                                             '<%'+ NmField + '%>',
                                             ' = "' + VrCampo + '"',
                                             [rfReplaceAll]);
                      StlChave.Add(NmField + VrCampo);
                end else
                begin
                      if not (CcImport.State in [dsEdit, dsInsert]) then
                      begin
                            CcImport.Close;
                            CcImport.CommandText := DsSql;
                            CcImport.Open;

                            if not CcImport.Eof then
                            begin
                                  CcImport.Edit;
                            end else
                            begin
                                  if SnInsert then
                                  begin
                                        CcImport.Append;

                                        for NrChave := 0 To StlChave.Count - 1 do
                                        begin
                                              VrCampo := StlChave[NrChave];
                                              NmField := Copy(VrCampo, 1, 8);
                                              Delete(VrCampo, 1, 8);

                                              ccImport.FieldByName(NmField).AsString :=
                                                                                  VrCampo;
                                        end;
                                  end;
                            end;
                      end;
                end;

                if CcImport.State in [dsEdit, dsInsert] then
                begin
                      ImportarLinha(DsLinha, StlColImp, '');

                      if Eof(DsImport) then
                      begin
                            CcImport.Post;
                            CcImport.ApplyUpdates(0);
                      end;
                end;
          end;
      finally
          CcImport.Close;
          CloseFile(DsImport);
          FreeAndNil(StlChave);
      end;
end;

procedure TBrvImport.ImportarLinha(DsLinha  : string;
                                   StlColImp : TStrings; NmTabela : String);
var NmField  : String;
    TmCampo  : String;
begin
      NmField  := Trim(Copy(DsLinha,  8, 8));
      TmCampo  := Copy(DsLinha, 21, 6);

      if NmTabela <> '' then
      begin
            CcImport.FieldByName(NmField).AsInteger :=
                                        FDsDicion.ProxNumeroSequencial(NmTabela, NmField);
      end else
      begin
            if  ImportarCampo(NmField, StlColImp) then
            begin
                  if Copy(DsLinha, 19, 1) = 'S' then // campo memo
                  begin
                        if CcImport.FieldByName(NmField).AsString <> '' then
                        begin
                              CcImport.FieldByName(NmField).AsString :=
                                   CcImport.FieldByName(NmField).AsString +
                                   #13;
                        end;

                        CcImport.FieldByName(NmField).AsString :=
                                  CcImport.FieldByName(NmField).AsString +
                                  Copy(DsLinha, 28, StrToInt(TmCampo));

                  end else
                  begin
                        CcImport.FieldByName(NmField).AsString :=
                                       Copy(DsLinha, 28, StrToInt(TmCampo));
                  end;
            end;
      end;
end;

function  TBrvImport.ImportarCampo(NmField : String; StlColImp : TStrings) : Boolean;
var NrField : Integer;
begin
      Result  := False;
      NrField := 0;

      while (NrField < StlColImp.Count) and (not Result) do
      begin
            if  NmField = StlColImp.Strings[NrField] then
            begin
                  Result := True;
            end;

            inc(NrField);
      end;
end;

procedure TBrvImport.ReconcileError(DataSet: TCustomClientDataSet; E: EReconcileError;
                                   UpdateKind: TUpdateKind; var Action: TReconcileAction);
begin
      Raise Exception.Create(E.Message);
end;

end.
