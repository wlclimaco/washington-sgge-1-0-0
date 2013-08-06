unit USDmImp;

interface

uses
  SysUtils, Classes, DSServer, DBXOracle, FMTBcd, Provider, DB, SqlExpr, BrvProvider,
  DBClient, BrvClientDataSet, BrvDataSet, BrvString, BrvDb, BrvConnection,
  URlc0000, UQrl0000, Forms, QuickRpt, SynPdf, Graphics, Printers;

type

  TSDmImp = class(TDSServerModule)
    QcTabela: TBrvDataSet;
    DcTabela: TBrvProvider;
    SqlConnImp: TBrvConnection;
    QExecute: TBrvDataSet;
    CdsPDF: TClientDataSet;
    CdsPDFBiRelato: TBlobField;
    CdsNmRelat: TClientDataSet;
    procedure SqlConnImpAfterConnect(Sender: TObject);
  private
    function GerarRelatorioCaracter(pNmRelato  : String;
                                    pCdsParams : TClientDataSet;
                                    pNmEmpres  : String;
                                    pData      : OleVariant) : OleVariant;
    function GerarRelatorioPDF(pNmRelato : String;     pCdsParams : TClientDataSet;
                               pNmEmpres : String;     pNmFrmOri  : String;
                           var pDsResult : String;
                               pCdUsuari : Integer;
                               pData     : OleVariant): OleVariant;
    procedure ExportAsPdf(QuickRep: TQuickRep; const aFileName: TFileName);
    function NomeArquivoPDFGerado(pCdUsuari : Integer): String;
    { Private declarations }
  public
    { Public declarations }
    function  GerarRelatorio(pDsCreden : String; var pDsResult : String;
                             pDsParams : OleVariant; pData : OleVariant) : OleVariant;
  end;

implementation

uses UFrmLogos, UDmDicion;


{$R *.dfm}

function TSDmImp.GerarRelatorio(pDsCreden: String; var pDsResult: String;
                                pDsParams: OleVariant; pData : OleVariant): OleVariant;
var lCdsParams : TClientDataSet;
    lCdUsuari  : Integer;
    lTpRelato  : String;
    lNmRelato  : String;
    lNmEmpres  : String;
    lNmFrmOri  : String;
    lDsSql     : String;
    lTpFormul  : String;
    lNrSeqFor  : String;
begin
      Result    := ' ';
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inv�lida';
      end else
      begin
            if FrmLogos.SbtManute.Tag <> 0 then
            begin
                  pDsResult := UFrmLogos.cDsMsgEr +
                               'Servidor de impress�o parado para manuten��o.' + #13#13 +
                               FrmLogos.EdtDsMenMan.Text + #13#13 +
                               'Aguarde alguns instantes e tente novamente.';
            end else
            begin
                  try
                      FrmLogos.EdtQtProAti.BrAsInteger := FrmLogos.EdtQtProAti.BrAsInteger + 1;

                      lCdsParams := TClientDataSet.Create(Self);
                      lCdsParams.Data := pDsParams;

                      // =-=-=-=-=-=-=-=-=-= Verificando o tipo do relat�rio a ser gerado
                      try
                          lCdsParams.First;
                          if lCdsParams.FieldByName('NmParam').AsString <> 'TpRelato' then
                          begin
                                pDsResult := UFrmLogos.cDsMsgEr +
                                                   'Parametro tipo do relat�rio faltando';
                          end else
                          begin
                                lTpRelato := lCdsParams.FieldByName('DsParam').AsString;
                          end;
                      except
                          pDsResult := UFrmLogos.cDsMsgEr +
                                                    'Tipo do relat�rio n�o est� definido';
                      end;

                      // =-=-=-=-=-=-=-=-=-= Verificando o nome do relat�rio a ser gerado
                      if pDsResult = UFrmLogos.cDsMsgOk then
                      begin
                            try
                                lCdsParams.Next;

                                if lCdsParams.FieldByName('NmParam').AsString <>
                                                                           'NmRelato' then
                                begin
                                      pDsResult := UFrmLogos.cDsMsgEr +
                                                   'Parametro nome do relat�rio faltando';
                                end else
                                begin
                                      lNmRelato :=
                                               lCdsParams.FieldByName('DsParam').AsString;
                                end;
                            except
                                pDsResult := UFrmLogos.cDsMsgEr +
                                                    'Nome do relat�rio n�o est� definido';
                            end;

                            lTpFormul  := Copy(lNmRelato, 1, 3);
                            lNrSeqFor  := Copy(lNmRelato, 4, 4);
                            lDsSql     := ' Select DsFormul From S006 ' +
                                          ' Where TpFormul = ' + #39 + lTpFormul + #39 +
                                          ' and   NrSeqFor = ' + lNrSeqFor;

                            CdsNmRelat.Data :=
                                       DmDicion.BrvDicionario.RetornaDadosSqlFixa(lDsSql);

                            if CdsNmRelat.Eof then
                            begin
                                  pDsResult := UFrmLogos.cDsMsgEr  + 'Relat�rio ' +
                                                         lNmRelato + ' n�o est� ' +
                                        'registrado no cadastro de formul�rios est�ticos';

                            end;
                      end;

                      // =-=-=-=-=-=-=-=-=-= Verificando o nome da empresa
                      if pDsResult = UFrmLogos.cDsMsgOk then
                      begin
                            try
                                lCdsParams.Next;

                                if lCdsParams.FieldByName('NmParam').AsString <>
                                                                           'NmEmpres' then
                                begin
                                      pDsResult := UFrmLogos.cDsMsgEr +
                                                   'Parametro nome da empresa faltando';
                                end else
                                begin
                                      lNmEmpres :=
                                               lCdsParams.FieldByName('DsParam').AsString;
                                end;
                            except
                                pDsResult := UFrmLogos.cDsMsgEr +
                                                    'Nome da empresa n�o est� definido';
                            end;
                      end;

                      // =-=-=-=-=-=-=-=-=-= Verificando o nome do Form de Origem
                      if pDsResult = UFrmLogos.cDsMsgOk then
                      begin
                            try
                                lCdsParams.Next;

                                if lCdsParams.FieldByName('NmParam').AsString <>
                                                                           'NmOrigem' then
                                begin
                                      pDsResult := UFrmLogos.cDsMsgEr +
                                                   'Parametro nome do formul�rio de ' +
                                                                  'origem est� faltando';
                                end else
                                begin
                                      lNmFrmOri :=
                                               lCdsParams.FieldByName('DsParam').AsString;
                                end;
                            except
                                pDsResult := UFrmLogos.cDsMsgEr +
                                         'Nome do formul�rio de origem n�o est� definido';
                            end;
                      end;
                      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                      if pDsResult = UFrmLogos.cDsMsgOk then
                      begin
                            if lTpRelato = 'C' then
                            begin
                                  Result := GerarRelatorioCaracter(lNmRelato, lCdsParams,
                                                                   lNmEmpres, pData);
                            end else
                            begin // lTpRelato = 'G' Gr�fico
                                  Result := GerarRelatorioPDF(lNmRelato, lCdsParams,
                                                              lNmEmpres, lNmFrmOri,
                                                              pDsResult, lCdUsuari,
                                                              pData);
                            end;
                      end;
                  finally
                      FreeAndNil(lCdsParams);
                      FrmLogos.EdtQtProAti.BrAsInteger :=
                                                     FrmLogos.EdtQtProAti.BrAsInteger - 1;

                      if FrmLogos.EdtQtProAti.BrAsInteger < 0 then
                      begin
                            FrmLogos.EdtQtProAti.BrAsInteger := 0;
                      end;
                  end;
            end;
      end;
end;

function TSDmImp.GerarRelatorioPDF(pNmRelato  : String;
                                   pCdsParams : TClientDataSet;
                                   pNmEmpres  : String; pNmFrmOri : String;
                               var pDsResult  : String;
                                   pCdUsuari  : Integer;
                                   pData      : OleVariant) : OleVariant;
var lQrl0000  : TQrl0000;
    lNmRelato : String;
    lDsResult : String;
    lNmPdf    : String;
begin
      lNmRelato := 'T' + pNmRelato;

      try
            Application.CreateForm(TComponentClass(GetClass(lNmRelato)), lQrl0000);

            if not CdsPDF.Active then
            begin
                  CdsPDF.CreateDataSet;
            end else
            begin
                  CdsPDF.EmptyDataSet;
            end;

            try
                lDsResult := lQrl0000.GerarRelatorio(pCdsParams,
                                                     DmDicion.BrvDicionario, pNmEmpres,
                                                     pNmFrmOri, pData);
                if lDsResult <> '' then
                begin
                      pDsResult := UFrmLogos.cDsMsgEr + ' ' + lDsResult;
                end else
                begin
                      lNmPdf := NomeArquivoPDFGerado(pCdUsuari);
                      ExportAsPdf(lQrl0000, lNmPdf);

                      CdsPdf.Append;
                      (CdsPDF.FieldByName('BiRelato') as TBlobField).LoadFromFile(lNmPdf);
                      CdsPdf.Post;
                      DeleteFile(lNmPdf);
                end;
            finally
                FreeAndNil(lQrl0000);
            end;
      except
             pDsResult := UFrmLogos.cDsMsgEr +
                          pNmRelato + ' n�o encontrado no servidor.' + #13#13 +
                          'Verifique se a classe initialization foi declarada.';

      end;

      Result := CdsPDF.Data;
end;

function TSdmImp.NomeArquivoPDFGerado(pCdUsuari : Integer) : String;
var lNmDirApl : String;
    lDtAtual  : String;
begin
      lNmDirApl := Application.ExeName;
      lNmDirApl := ExtractFileDir(lNmDirApl) + '\Rel';

      if not DirectoryExists(lNmDirApl) then
      begin
            CreateDir(lNmDirApl);
      end;

      lDtAtual := DmDicion.BrvDicionario.DataHoraServerStr;
      lDtAtual := StringReplace(lDtAtual, '/', '', [rfReplaceAll]);
      lDtAtual := StringReplace(lDtAtual, ':', '', [rfReplaceAll]);
      lDtAtual := StringReplace(lDtAtual, ' ', '', [rfReplaceAll]);

      Result := lNmDirApl + '\SrvImp_' +
                   FormatFloat('000000', pCdUsuari) + '_' + lDtAtual + '.pdf';
end;

procedure TSDmImp.ExportAsPdf(QuickRep: TQuickRep; const aFileName: TFileName);
var Pdf    : TPdfDocument;
    aMeta  : TMetaFile;
    i      : integer;
begin
      Pdf := TPdfDocument.Create;

      try
          Pdf.DefaultPaperSize := psA4;

          if QuickRep.Page.Orientation = poPortrait then
          begin
                Pdf.DefaultPageLandscape := False;
          end else
          begin
                Pdf.DefaultPageLandscape := True;
          end;

          QuickRep.Prepare;

          for i := 1 to QuickRep.QRPrinter.PageCount do
          begin
                Pdf.AddPage;
                aMeta := QuickRep.QRPrinter.GetPage(i);

                try
                    // draw the page content
                    Pdf.Canvas.RenderMetaFile(aMeta,1,0,0);
                finally
                    aMeta.Free;
                end;
          end;
          Pdf.SaveToFile(aFileName);
      finally
        Pdf.free;
      end;
end;

function TSDmImp.GerarRelatorioCaracter(pNmRelato  : String;
                                        pCdsParams : TClientDataSet;
                                        pNmEmpres  : String;
                                        pData      : OleVariant) : OleVariant;
var lClaRel  : TPersistentClass;
    lRlc0000 : TRlc0000;
begin
      Result := ' ';
      lClaRel := GetClass('T' + pNmRelato);

      if lClaRel = nil then
      begin
            Result := 'Classe do relat�rio T' + pNmRelato + ' n�o encontrado.';
      end else
      begin
            try
                lRlc0000 := TPersistentClass(lClaRel).Create() as TRlc0000;
                Result := lRlc0000.GerarRelatorio(pCdsParams, DmDicion.BrvDicionario,
                                                  pNmEmpres, pData);
            finally
                FreeAndNil(lRlc0000);
            end;
      end;
end;

procedure TSDmImp.SqlConnImpAfterConnect(Sender: TObject);
begin
      SqlConnImp.ExecuteDirect('alter session set nls_numeric_characters = ''.,''');
end;

end.

