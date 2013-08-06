unit BrvRelAsc;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  DbTables, stdctrls, BrvString;

type
  TRelQtColuna = (Rel080Col, Rel132Col);

  TBrvRelAsc = class(TBrvString)
  private
    FNrLinha   : Integer;
    FNrPagina  : Integer;
    FNrPagIni  : Integer;
    FDsEndere  : String;
    gEvCabeca  : TNotifyEvent;
    gEvComCab  : TNotifyEvent;
    gQtLimPag  : Integer;
    gQtColRel  : TRelQtColuna;
    gNmEmpres  : String;
    gDsTitRel  : String;
    gNmFormul  : String;
    gSnCabeca  : Boolean;
    procedure SetNumeroLinha(InValor : Integer);
    procedure SetNumeroPagina(InValor : Integer);
    procedure SetNumeroPaginaInicial(InValor : Integer);
    function  DataHora : String;
    function  ConfigurarImpressaoString(DsString : String) : String;
    procedure CriarRelatorio(CdUsuari : Integer);
    procedure Cabecalho;
    procedure Cabecalho080;
    procedure Cabecalho132;
  public
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    procedure   NovoRelatorio(lCdUsuari : Integer; pNmEmpres : String;
                              pNmFormul : String;  pDsTitRel : String);
    procedure   NovoRelatorioInvisivel(lCdUsuari : Integer; pNmEmpres : String;
                                       pNmFormul : String;  pDsTitRel : String);
    function    EncontrarNomeRelatorio(NmRelat : String; CdUsuari : Integer) : String;
    procedure   SalvarRelatorioComo(NmRelat : String);
    procedure   FecharListagem;
    procedure   MostrarListagem;
    procedure   MostrarListagemModal;
    function    PathRelatorio : String;
    procedure   NovaLinha(DsLinha : String);
    procedure   InserirTexto(TxRelato : WideString);
    procedure   VisualizarRelatorio(CdUsuari : Integer);
    function    QuantidadeLinhasImpressas : Integer;
    procedure   LinhasFixas(QtLinhas: Integer; QtCaract: Integer; DsCaract: Char);
    procedure   RelVisible(SnVisibl: boolean);
    procedure   RelGraficoNormal;
    procedure   RelGraficoNegrito;
    function    RetornaTextoGerado : String;
  published
    property Linha              : Integer      read FNrLinha  write SetNumeroLinha;
    property Pagina             : Integer      read FNrPagina write SetNumeroPagina;
    property PaginaInicial      : Integer      read FNrPagIni write SetNumeroPaginaInicial;
    property EnderecoParaSalvar : String       read FDsEndere write FDsEndere;
    property OnCabecalho        : TNotifyEvent read gEvCabeca write gEvCabeca;
    property CompletaCabecalho  : TNotifyEvent read gEvComCab write gEvComCab;
    property LinhasPorPagina    : Integer      read gQtLimPag write gQtLimPag;
    property ColunasPorLinha    : TRelQtColuna read gQtColRel write gQtColRel;
    property GerarCabecalho     : Boolean      read gSnCabeca write gSnCabeca;
  end;

procedure Register;

implementation

uses BrvRelAscII;

procedure Register;
begin
      RegisterComponents('Bravo Relatorio', [TBrvRelAsc]);
end;

constructor TBrvRelAsc.Create(AOwner: TComponent);
begin
      inherited Create(AOwner);
      gQtLimPag := 60;
      gSnCabeca := True;
end;

destructor TBrvRelAsc.Destroy;
begin
      inherited Destroy;
end;

procedure TBrvRelAsc.SetNumeroLinha(InValor : Integer);
begin
      FNrLinha  := inValor;
end;

procedure TBrvRelAsc.SetNumeroPagina(InValor : Integer);
begin
      FNrPagina := inValor;
end;

procedure TBrvRelAsc.SetNumeroPaginaInicial(InValor : Integer);
begin
      FNrPagIni := inValor;
end;

procedure TBrvRelAsc.NovoRelatorio(lCdUsuari : Integer; pNmEmpres : String;
                                   pNmFormul : String;  pDsTitRel : String);
begin
      gNmEmpres := ConfigurarImpressaoString(pNmEmpres);
      gDsTitRel := ConfigurarImpressaoString(pDsTitRel);
      gNmFormul := ConfigurarImpressaoString(pNmFormul);

      FrmRelAscII := TFrmRelAscII.Create(Self);
      FrmRelAscII.FormStyle := fsMDIChild;
      FrmRelAscII.Show;

      SendMessage(FrmRelAscII.RchRelat.Handle, EM_LIMITTEXT, 0, $FFFFFF);

      CriarRelatorio(lCdUsuari);
end;

procedure TBrvRelAsc.NovoRelatorioInvisivel(lCdUsuari : Integer; pNmEmpres : String;
                                            pNmFormul : String;  pDsTitRel : String);
begin
      gNmEmpres := ConfigurarImpressaoString(pNmEmpres);
      gDsTitRel := ConfigurarImpressaoString(pDsTitRel);
      gNmFormul := ConfigurarImpressaoString(pNmFormul);
      FrmRelAscII           := TFrmRelAscII.Create(Self);

      SendMessage(FrmRelAscII.RchRelat.Handle, EM_LIMITTEXT, 0, $FFFFFF);

      CriarRelatorio(lCdUsuari);
end;

procedure TBrvRelAsc.CriarRelatorio(CdUsuari : Integer);
var NmDireto  : String;
begin
      FNrLinha    := 99;
      FNrPagina   := FNrPagIni;

      NmDireto    := PathRelatorio;

      FrmRelAscII.DlgSave.Filter     := 'Usuário ' +
                                     FormatFloat('000', CdUsuari) + '|*.' +
                                     FormatFloat('000', CdUsuari){ +
                                     '|Arquivo texto|*.txt'};

      FrmRelAscII.DlgSave.DefaultExt := 'Usuário ' +
                                     FormatFloat('000', CdUsuari) + '|*.' +
                                     FormatFloat('000', CdUsuari){ +
                                     '|Arquivo texto|*.txt'};

      FrmRelAscII.DlgSave.InitialDir := NmDireto;

      FrmRelAscII.DlgOpen.Filter     := 'Usuário ' +
                                     FormatFloat('000', CdUsuari) + '|*.' +
                                     FormatFloat('000', CdUsuari){ +
                                     '|Arquivo texto|*.txt'};

      FrmRelAscII.DlgOpen.DefaultExt := FormatFloat('000', CdUsuari);
      FrmRelAscII.DlgOpen.InitialDir := NmDireto;
end;

function TBrvRelAsc.PathRelatorio : String;
//var QPParSistema : TQuery;
var Temp: array[0..144] of Char;
begin
      GetTempPath(144, Temp);
      Result := StrPas(Temp);

//corrigir depois

{      try
          if Trim(FDsEndere) = '' then
          begin
                QPParSistema              := TQuery.Create(Self);
                QPParSistema.DatabaseName := 'BDPadrao';
                QPParSistema.SQL.Text     := 'Select * from ParSistema';
                QPParSistema.Close;
                QPParSistema.Open;

                Result := QPParSistema.FieldByName('DsPatRel').AsString;

                QPParSistema.Close;
                QPParSistema.Destroy;

                if  Result = '' then
                begin
                      Result := 'c:';
                      MessageDlg('Caminho para salvar o arquivo não foi encontrado.' +
                                 #13#13 + '"C:\" será assumido.', MtInformation, [MbOk], 0);
                end;
          end else
          begin
                Result := FDsEndere;
          end;
      except
          MessageDlg('Caminho para salvar o arquivo não foi encontrado.' +
                     #13#13 + '"C:\" será assumido.', MtInformation, [MbOk], 0);
      end;}
end;

procedure TBrvRelAsc.Cabecalho;
begin
      case gQtColRel of
           Rel080Col : Cabecalho080;
           Rel132Col : Cabecalho132;
      end;

      if Assigned(gEvComCab) then
      begin
            gEvComCab(Self);
      end;
end;

procedure TBrvRelAsc.Cabecalho080;
begin
      if FNrPagIni < 1 then
      begin
            FNrPagIni := 1;
      end;

      if  FNrPagina > FNrPagIni then
      begin
            FrmRelAscII.RchRelat.Lines.Add('');
      end;

      FrmRelAscII.RchRelat.Lines.Add(StringOfChar('-', 80));

      FrmRelAscII.RchRelat.Lines.Add(FormatarStringSemAcento(gNmEmpres, 40) +
                                     '  BRAVO   ' +
                                     FormatarStringSemAcento(
                                              AlinharDireita(gNmFormul, 30), 30));

      FrmRelAscII.RchRelat.Lines.Add(FormatarStringSemAcento(gDsTitRel, 50) +
                                  ' ' +
                                  DataHora + ' PAG. ' +
                                  FormatarNumero(IntToStr(FNrPagina), 4, True));

      FrmRelAscII.RchRelat.Lines.Add(StringOfChar('-', 80));

      Inc(FNrPagina);
      FNrLinha := 5;
end;

procedure TBrvRelAsc.Cabecalho132;
begin
      if FNrPagIni < 1 then
      begin
            FNrPagIni := 1;
      end;

      if  FNrPagina > FNrPagIni then
      begin
            FrmRelAscII.RchRelat.Lines.Add('');
      end;

      if Assigned(gEvCabeca) then
      begin
            FNrLinha := 0;
            gEvCabeca(Self);
      end else
      begin
            FrmRelAscII.RchRelat.Lines.Add(StringOfChar('-', 132));

            FrmRelAscII.RchRelat.Lines.Add(FormatarStringSemAcento(gNmEmpres, 40) +
                                        StringOfChar(' ', 51) + '   BRAVO   ' +
                                        FormatarStringSemAcento(
                                                    AlinharDireita(gNmFormul, 30), 30));

            FrmRelAscII.RchRelat.Lines.Add(FormatarStringSemAcento(gDsTitRel, 90) +
                                        StringOfChar(' ', 10) +
                                        DataHora + '    PAG. ' +
                                        FormatarNumero(IntToStr(FNrPagina), 4, True));

            FrmRelAscII.RchRelat.Lines.Add(StringOfChar('-', 132));

            FNrLinha := 5;
      end;

      Inc(FNrPagina);
end;

function TBrvRelAsc.ConfigurarImpressaoString(DsString : String) : String;
begin
      Result := RetirarAcentos(DsString);
      Result := UpperCase(Result);
end;

function TBrvRelAsc.DataHora : String;
begin
      Result := FormatDateTime('dd/mm/yyyy HH:MM:ss', Now);
end;

function TBrvRelAsc.EncontrarNomeRelatorio(NmRelat : String; CdUsuari : Integer) : String;
var x        : Integer;
    NmArquiv : String;
    SnArqOk  : Boolean;
begin
      Result := '';
      if  FrmRelAscII.RchRelat.Lines.Count < 1 then
      begin
            MessageDlg('Relatório gerado sem conteúdo', MtInformation, [MbOk], 0);
            FreeAndNil(FrmRelAscII);
      end else
      begin
            x        := 0;
            SnArqOk  := False;

            repeat
                   inc(x);

                   NmArquiv := FrmRelAscII.DlgSave.InitialDir     + '\' +
                               NmRelat + FormatFloat('000', x) + '.' +
                               FormatFloat('000', CdUsuari);

                   if not (FileExists(NmArquiv)) then
                   begin
                         SnArqOk := True;
                   end;
            until SnArqOk;

            FrmRelAscII.Caption := NmArquiv;
            FrmRelAscII.Salvar;
            MostrarListagem;
            Result           := NmArquiv;
      end;
end;

procedure   TBrvRelAsc.SalvarRelatorioComo(NmRelat : String);
begin
      FrmRelAscII.RchRelat.Lines.SaveToFile(NmRelat);
end;

procedure   TBrvRelAsc.FecharListagem;
begin
      FreeAndNil(FrmRelAscII);
end;

procedure   TBrvRelAsc.MostrarListagem;
begin
      FrmRelAscII.RchRelat.Lines.Add('');
      FrmRelAscII.Show;
end;

procedure   TBrvRelAsc.MostrarListagemModal;
begin
      FrmRelAscII.FormStyle := fsNormal;
      FrmRelAscII.Visible   := False;
      FrmRelAscII.RchRelat.Lines.Add('');
      FrmRelAscII.ShowModal;
end;

procedure   TBrvRelAsc.NovaLinha(DsLinha : String);
begin
      if FNrLinha > gQtLimPag then
      begin
            if gSnCabeca then
            begin
                  Cabecalho;
            end;
      end;

      FrmRelAscII.RchRelat.Lines.Add(Dslinha);
      inc(FNrLinha);
end;

procedure   TBrvRelAsc.InserirTexto(TxRelato : WideString);
begin
      FrmRelAscII.RchRelat.Lines.Text := TxRelato;
end;

procedure   TBrvRelAsc.VisualizarRelatorio(CdUsuari : Integer);
var NmDireto  : String;
begin
      FrmRelAscII    := TFrmRelAscII.Create(Self);
      NmDireto    := PathRelatorio;

      SendMessage(FrmRelAscII.RchRelat.Handle, EM_LIMITTEXT, 0, $FFFFFF);

      FrmRelAscII.DlgSave.Filter     := 'Usuário ' + FormatFloat('000', CdUsuari) + '|*.' +
                                                  FormatFloat('000', CdUsuari){ +
                                                 '|Arquivo texto|*.txt'};

      FrmRelAscII.DlgSave.DefaultExt := 'Usuário ' + FormatFloat('000', CdUsuari) + '|*.' +
                                                  FormatFloat('000', CdUsuari){ +
                                                 '|Arquivo texto|*.txt'};

      FrmRelAscII.DlgSave.InitialDir := NmDireto;

      FrmRelAscII.DlgOpen.Filter     := 'Usuário ' + FormatFloat('000', CdUsuari) + '|*.' +
                                                  FormatFloat('000', CdUsuari){ +
                                                 '|Arquivo texto|*.txt'};
      FrmRelAscII.DlgOpen.DefaultExt := FormatFloat('000', CdUsuari);
      FrmRelAscII.DlgOpen.InitialDir := NmDireto;
      FrmRelAscII.Show;
end;

function    TBrvRelAsc.QuantidadeLinhasImpressas : Integer;
begin
      Result := FrmRelAscII.RchRelat.Lines.Count - 1;
end;

procedure   TBrvRelAsc.LinhasFixas(QtLinhas: Integer; QtCaract: Integer; DsCaract: Char);
var QtLinAux: Integer;
begin
      for QtLinAux := 1 to QtLinhas do
      begin
            NovaLinha(StringOfChar(DsCaract, QtCaract));
      end;
end;

procedure TBrvRelAsc.RelVisible(SnVisibl: boolean);
begin
      FrmRelAscII.RchRelat.Visible := SnVisibl;
end;

function TBrvRelAsc.RetornaTextoGerado: String;
begin
      if FrmRelAscII.RchRelat.Lines.Text <> '' then
      begin
            Result := FrmRelAscII.RchRelat.Lines.Text;
      end else
      begin
            Result := 'Relatório gerado sem conteúdo';
      end;
end;

procedure TBrvRelAsc.RelGraficoNegrito;
begin
      FrmRelAscII.Negrito := True;
      FrmRelAscII.SbtRelGraClick(Self);
end;

procedure TBrvRelAsc.RelGraficoNormal;
begin
      FrmRelAscII.Negrito := False;
      FrmRelAscII.SbtRelGraClick(Self);
end;

end.
