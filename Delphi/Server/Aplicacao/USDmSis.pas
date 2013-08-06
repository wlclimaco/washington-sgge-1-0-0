unit USDmSis;

interface

uses
  SysUtils, Classes, DSServer, DBXOracle, FMTBcd, Provider, DB, SqlExpr, BrvProvider,
  DBClient, BrvClientDataSet, BrvDataSet, BrvString, BrvDb, BrvConnection;

const cQtConDin = 100;

type
  TConDinam = record
      ConProvider : TBrvProvider;
      ConDataSet  : TBrvDataSet;
      ConOcupado  : Boolean;
  end;

  TSDmSis = class(TDSServerModule)
    QcTabela: TBrvDataSet;
    DcTabela: TBrvProvider;
    QcTabAtr: TBrvDataSet;
    DcTabAtr: TBrvProvider;
    QcDomAtr: TBrvDataSet;
    DcDomAtr: TBrvProvider;
    QcChaPri: TBrvDataSet;
    DcChaPri: TBrvProvider;
    SqlConnSis: TBrvConnection;
    QExecute: TBrvDataSet;
    QcChaEst: TBrvDataSet;
    DcChaEst: TBrvProvider;
    QcColEst: TBrvDataSet;
    DcColEst: TBrvProvider;
    CcChaPri: TBrvClientDataSet;
    QSql: TBrvDataSet;
    QcSql: TBrvProvider;
    QcConfRelUsr: TBrvDataSet;
    DcConfRelUsr: TBrvProvider;
    QcConfColRelUsr: TBrvDataSet;
    DcConfColRelUsr: TBrvProvider;
    QcSqlUsr: TBrvDataSet;
    DcSqlUsr: TBrvProvider;
    QcTrigger: TBrvDataSet;
    DcTrigger: TBrvProvider;
    QcIndice: TBrvDataSet;
    DcIndice: TBrvProvider;
    QcColInd: TBrvDataSet;
    DcColInd: TBrvProvider;
    QcProced: TBrvDataSet;
    DcProced: TBrvProvider;
    QcExcess: TBrvDataSet;
    DcExcess: TBrvProvider;
    QcForDin: TBrvDataSet;
    DcForDin: TBrvProvider;
    QpAtrDin: TBrvClientDataSet;
    QcAtrDin: TBrvDataSet;
    DcAtrDin: TBrvProvider;
    QpDados: TBrvDataSet;
    DpDados: TDataSetProvider;
    CpDados: TBrvClientDataSet;
    QcS026: TBrvDataSet;
    DcS026: TBrvProvider;
    QpS026: TBrvClientDataSet;
    QcSubFor: TBrvDataSet;
    DcSubFor: TBrvProvider;
    QcMenu: TBrvDataSet;
    DcMenu: TBrvProvider;
    QcPerMenGru: TBrvDataSet;
    DcPerMenGru: TBrvProvider;
    QcPerMenUsu: TBrvDataSet;
    DcPerMenUsu: TBrvProvider;
    QcPerAtrGru: TBrvDataSet;
    DcPerAtrGru: TBrvProvider;
    QcPerAtrUsu: TBrvDataSet;
    DcPerAtrUsu: TBrvProvider;
    QcImport: TBrvDataSet;
    DpImport: TBrvProvider;
    DcView: TBrvProvider;
    QcView: TBrvDataSet;
    QcS045: TBrvDataSet;
    DcS045: TBrvProvider;
    QpS045: TBrvClientDataSet;
    DcValAtr: TBrvProvider;
    QcValAtr: TBrvDataSet;
    QcApoAten: TBrvDataSet;
    DcApoAten: TBrvProvider;
    QcClaRNC: TBrvDataSet;
    DcClaRNC: TBrvProvider;
    procedure SqlConnSisAfterConnect(Sender: TObject);
  private
    { Private declarations }
    gConDinam : array [1..cQtConDin] of  TConDinam;
    procedure AtualizarPosicaoChavePrimaria(var pDsResult: String; pNmTabela: String;
      pNrNewPos, pNrOldPos: Integer);
    procedure AtualizarPosicaoChaveEstrangeira(var pDsResult: String; pNmChaEst : String;
                                                   pNrOldPos, pNrNewPos: Integer);
    procedure AtualizarPosicaoIndiceSecundario(var pDsResult: String; pNmIndice : String;
                                                   pNrOldPos, pNrNewPos: Integer);
    function  SetarProviderFormDinamico(pDsDatSet: TBrvDataSet;
                                        pDsProvid: TBrvProvider) : Boolean;
    procedure CadastrarOrdenacaoInicial(pDsCreden: String; var pDsResult: String;
      pNrForDin: String; pDsOrdIni, pNmTabela: String);
    function LocalizarDataSetFormDinamico(pNmProvid: String): TBrvDataSet;
    function GravarLogImagens(pCdsImagem: OleVariant; pDsPath: String) : Integer;
  public
    { Public declarations }
    procedure ExcluirAtributoIndiceSecundario(pDsCreden: String; var pDsResult: String;
      pNmIndice, pNmAtribu: String);
    procedure ExcluirIndiceSecundario(pDsCreden: String; var pDsResult: String;
      pNmIndice: String);
    procedure ExcluirChaveEstrangeira(pDsCreden: String; var pDsResult: String;
                                                             pNmChaEst: String);
    procedure ExcluirAtributoChaveEstrangeira(pDsCreden: String; var pDsResult: String;
      pNmChaEst, pNmAtribu: String);
    procedure ExcluirTabela(pDsCreden: String; var pDsResult: String; pNmTabela: String);
    procedure ExcluirAtributo(pDsCreden: String; var pDsResult : String; pNmTabela: String;
                                                     pNmAtribu : String);
    procedure ExcluirAtributoPrimario(pDsCreden: String; var pDsResult: String; pNmTabela,
      pNmAtribu: String);
    procedure AtualizarPosicaoChave(pDsCreden : String; var pDsResult : String;
                                    pTpChave  : String;     pNmChaEst : String;
                                    pNmTabela : String;     pNrNewPos : Integer;
                                    pNrOldPos : Integer);
    procedure ExcluirFormularioDinamico(pDsCreden : String; var pDsResult : String;
                                        pNrForDin : String);
    procedure SalvarObjetosFormDinamico(pDsCreden : String; var pDsResult : String;
                                        pNrForDin : String;     pData     : OleVariant;
                                        pDsOrdIni : String;     pNmTabela : String;
                                        pCdsConsu : OleVariant; pNmFrmOri: String;
                                        pCdsRetCo : OleVariant);
    procedure AtribuirProviderFormDinamico(pDsCreden : String; var pDsResult : String;
                                       var pNmProvid: String;     pNmTabela: String;
                                           pNrForDin: Integer);
    procedure LiberarProviderFormDinamico(pDsCreden: String; var pDsResult: String;
                                          pNmProvid: String);
    function OrdemInicialFormDinamico(pDsCreden : String; var pDsResult: String;
                                      pNmTabela : String): OleVariant;
    procedure SalvarConfigConsultaUsuario(pDsCreden : String; var pDsResult : String;
                                          pNrQueCon : Integer;    pCdUsuari : Integer;
                                          pNmTabOrd : String;     pNmAtrOrd : String;
                                          pNmTabPes : String;     pNmAtrPes : String;
                                          pNmTabPe2 : String;     pNmAtrPe2 : String;
                                          pDsLocal2 : String;     pTpMaiMin : String;
                                          pTpWhere  : Integer);
    procedure SalvarColunasConsultaUsuario(pDsCreden : String; var pDsResult : String;
                                          pNrQueCon : Integer;    pCdUsuari : Integer;
                                          pData     : OleVariant; pNmFormul : String);
    procedure AtualizarUtilizacaoFormulario(pDsCreden : String; var pDsResult : String;
                                          pCdUsuari : Integer;      pNrForDin : Integer;
                                          pTpFormul : String;       pNrSeqFor : Integer);
    procedure ReorganizarOrdenacaoMenu(pDsCreden : String; var pDsResult : String;
                                       pNrMenPai : Integer);
    procedure MoverMenu(pDsCreden: String; var pDsResult: String; pNrMenPai, pNrOldOrd,
      pNrNewOrd: Integer);

    procedure AtualizaEmpresasSelecionadasUsuario(pDsCreden: String;
                  var pDsResult: String; pCdEmpres : String; pCdUsuari : Integer);
    procedure AtualizarLog(pDsCreden : String; var pDsResult : String;
                           pNmTabela : String;     pCdUsuari : Integer;
                           pVrChave  : String;     pTpOperac : Byte;
                           pNmFormul : String;     pCdsLog   : OleVariant);

    procedure AtualizarLogScreen(pDsCreden: String;  var pDsResult: String;
                                 pCdUsuari: Integer;     pNmFormul: String;
                                 pDtLogScr: String; pCdsLog  : OleVariant);

    function AtualizarPosicaoAtributo(pDsCreden, pNmTabela: String;
                                      pNrNewPos, pNrOldPos: Integer): AnsiString;
  end;

implementation

uses UFrmLogos, UDmDicion;

{$R *.dfm}

procedure TSDmSis.SqlConnSisAfterConnect(Sender: TObject);
begin
      SqlConnSis.ExecuteDirect('alter session set nls_numeric_characters = ''.,''');
end;

procedure TSdmSis.AtualizarPosicaoChavePrimaria(
                                            var pDsResult : String; pNmTabela : String;
                                                pNrNewPos : Integer; pNrOldPos : Integer);
begin
      // Jogando um valor qualquer para a posição anterior
      try
          QExecute.CommandText :=
                   'Update S011 set NrSeqKey = 10000 '     +
                   ' Where NmTabela = '  + #39 + pNmTabela + #39 +
                   ' and   NrSeqKey = ' + IntToStr(pNrOldPos);

          QExecute.ExecSQL(True);
      except
          on E: Exception do
          begin
                pDsResult := UFrmLogos.cDsMsgEr + E.Message;
          end;
      end;

      // atualizando a nova posição
      if pDsResult = UFrmLogos.cDsMsgOk then
      begin
            try
                QExecute.CommandText :=
                         'Update S011 set NrSeqKey = ' + IntToStr(pNrOldPos) +
                         ' Where NmTabela = '  + #39 + pNmTabela + #39 +
                         ' and   NrSeqKey = '  + IntToStr(pNrNewPos);
                QExecute.ExecSQL(True);
            except
                on E: Exception do
                begin
                      pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                end;
            end;
      end;

      if pDsResult = UFrmLogos.cDsMsgOk then
      begin
            // atualizando a posição anterior
            try
                QExecute.CommandText :=
                         'Update S011 set NrSeqKey = ' + IntToStr(pNrNewPos) +
                         ' Where NmTabela = '  + #39 + pNmTabela + #39 +
                         ' and   NrSeqKey = 10000';
                QExecute.ExecSQL(True);
            except
                on E: Exception do
                begin
                      pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                end;
            end;
      end;
end;

function TSdmSis.AtualizarPosicaoAtributo(pDsCreden : String; pNmTabela : String; pNrNewPos : Integer;
                                          pNrOldPos : Integer): AnsiString;
var pDsResult: String;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      // Jogando um valor qualquer para a posição anterior
      try
          QExecute.CommandText :=
                   'Update S009 set NrSeqAtr = 10000 '     +
                   ' Where NmTabela = '  + #39 + pNmTabela + #39 +
                   ' and   NrSeqAtr = ' + IntToStr(pNrOldPos);

          QExecute.ExecSQL(True);
      except
          on E: Exception do
          begin
                pDsResult := UFrmLogos.cDsMsgEr + E.Message;
          end;
      end;

      // atualizando a nova posição
      if pDsResult = UFrmLogos.cDsMsgOk then
      begin
            try
                QExecute.CommandText :=
                         'Update S009 set NrSeqAtr = ' + IntToStr(pNrOldPos) +
                         ' Where NmTabela = '  + #39 + pNmTabela + #39 +
                         ' and   NrSeqAtr = '  + IntToStr(pNrNewPos);
                QExecute.ExecSQL(True);
            except
                on E: Exception do
                begin
                      pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                end;
            end;
      end;

      if pDsResult = UFrmLogos.cDsMsgOk then
      begin
            // atualizando a posição anterior
            try
                QExecute.CommandText :=
                         'Update S009 set NrSeqAtr = ' + IntToStr(pNrNewPos) +
                         ' Where NmTabela = '  + #39 + pNmTabela + #39 +
                         ' and   NrSeqAtr = 10000';
                QExecute.ExecSQL(True);
            except
                on E: Exception do
                begin
                      pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                end;
            end;
      end;

      Result := pDsResult;
end;

procedure TSDmSis.AtualizarPosicaoChaveEstrangeira(var pDsResult : String;
                                   pNmChaEst : String; pNrOldPos : Integer;
                                   pNrNewPos : Integer);
begin
      // Jogando um valor qualquer para a posição anterior
      try
          QExecute.CommandText :=
                   'Update S013 set NrSeqKey = 10000 '    +
                   ' Where NmChaEst = ' + #39 + pNmChaEst + #39 +
                   ' and   NrSeqKey = ' + IntToStr(pNrOldPos);
          QExecute.ExecSQL(True);
      except
           on E: Exception do
                 begin
                       pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                 end;
      end;

      // atualizando a nova posição
      if pDsResult = UFrmLogos.cDsMsgOk then
      begin
            try
                QExecute.CommandText :=
                         'Update S013 set NrSeqKey = ' + IntToStr(pNrOldPos) +
                         ' Where NmChaEst = ' + #39 + pNmChaEst + #39 +
                         ' and   NrSeqKey = '  + IntToStr(pNrNewPos);
                QExecute.ExecSQL(True);
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;
      end;

      if pDsResult = UFrmLogos.cDsMsgOk then
      begin
            // atualizando a posição anterior
            try
                QExecute.CommandText :=
                         'Update S013 set NrSeqKey = ' + IntToStr(pNrNewPos) +
                         ' Where NmChaEst = ' + #39 + pNmChaEst + #39 +
                         ' and   NrSeqKey = 10000';
                QExecute.ExecSQL(True);
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;
      end;
end;

procedure TSdmSis.AtualizarPosicaoIndiceSecundario(var pDsResult : String;
                                   pNmIndice : String;
                                   pNrOldPos : Integer; pNrNewPos : Integer);
begin
      // Jogando um valor qualquer para a posição anterior
      try
          QExecute.CommandText :=
                   'Update S019 set NrSeqInd = 10000 '    +
                   ' Where NmIndice = ' + #39 + pNmIndice + #39 +
                   ' and   NrSeqInd = ' + IntToStr(pNrOldPos);
          QExecute.ExecSQL(True);
      except
           on E: Exception do
                 begin
                       pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                 end;
      end;

      // atualizando a nova posição
      if pDsResult = UFrmLogos.cDsMsgOk then
      begin
            try
                QExecute.CommandText :=
                         'Update S019 set NrSeqInd = ' + IntToStr(pNrOldPos) +
                         ' Where NmIndice = ' + #39 + pNmIndice + #39 +
                         ' and   NrSeqInd = '  + IntToStr(pNrNewPos);
                QExecute.ExecSQL(True);
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;
      end;

      if pDsResult = UFrmLogos.cDsMsgOk then
      begin
            // atualizando a posição anterior
            try
                QExecute.CommandText :=
                         'Update S019 set NrSeqInd = ' + IntToStr(pNrNewPos) +
                         ' Where NmIndice = ' + #39 + pNmIndice + #39 +
                         ' and   NrSeqInd = 10000';
                QExecute.ExecSQL(True);
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;
      end;
end;

procedure TSDmSis.AtualizarUtilizacaoFormulario(pDsCreden: String; var pDsResult: String;
                   pCdUsuari, pNrForDin: Integer; pTpFormul: String; pNrSeqFor: Integer);
var lDsSql    : String;
    lNrProSec : Integer;
    lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            try
                lNrProSec :=
                          DmDicion.BrvDicionario.ProxNumeroSequencial('S030', 'NRSEQUTI');

                if pNrForDin <> 0 then
                begin
                      lDsSql :=
                        'Insert Into S030 (NrSeqUti, CdUsuari, NrForDin, DtUtiliz) ' +
                        ' values ' +
                        '(' + IntToStr(lNrProSec) + ', '      +
                              IntToStr(pCdUsuari) + ', '      +
                              IntToStr(pNrForDin) + ', <$hh"' +
                              DmDicion.BrvDicionario.DataHoraServerStr + '"hh$>)';
                end else
                begin
                      lDsSql :=
                        'Insert Into S030 ' +
                                  '(NrSeqUti, CdUsuari, TpFormul, NrSeqFor, DtUtiliz) ' +
                        ' values ' +
                        '(' + IntToStr(lNrProSec)   + ', '      +
                              IntToStr(pCdUsuari)   + ', '      +
                              #39 + pTpFormul + #39 + ', '      +
                              IntToStr(pNrSeqFor)   + ', <$hh"' +
                              DmDicion.BrvDicionario.DataHoraServerStr + '"hh$>)';
                end;
                QExecute.CommandText := lDsSql;
                QExecute.BrExecuteSQL(False);
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;
      end;
end;

function TSDmSis.LocalizarDataSetFormDinamico(pNmProvid : String) : TBrvDataSet;
var lNrConexa : Integer;
begin
      Result    := nil;
      lNrConexa := 1;
      while (lNrConexa <= cQtConDin) do
      begin
            if gConDinam[lNrConexa].ConProvider.Name = pNmProvid then
            begin
                  Result := gConDinam[lNrConexa].ConDataSet;
                  lNrConexa := cQtConDin;
            end;

            inc(lNrConexa);
      end;
end;

procedure TSDmSis.AtribuirProviderFormDinamico(pDsCreden: String; var pDsResult: String;
                                        var pNmProvid: String;     pNmTabela: String;
                                            pNrForDin: Integer);
var lDsDatSet : TBrvDataSet;
    lDsProvid : TBrvProvider;
    lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            pNmProvid := 'p' + pNmTabela + IntToStr(pNrForDin) + IntToStr(random(1000));

            lDsDatSet                := TBrvDataSet.Create(Self);
            lDsDatSet.GetMetadata    := False;
            lDsDatSet.SQLConnection  := SqlConnSis;
            lDsDatSet.BrDicionario   := DmDicion.BrvDicionario;

            lDsProvid                := TBrvProvider.Create(self);
            lDsProvid.DataSet        := lDsDatSet;
            lDsProvid.Options        := [poAllowCommandText,poUseQuoteChar];
            lDsProvid.Name           := pNmProvid;

            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  if not SetarProviderFormDinamico(lDsDatSet, lDsProvid) then
                  begin
                        pDsResult := UFrmLogos.cDsMsgEr +
                                    'Quantidade máxima de provider dinâmico ultrapassado';
                  end;
            end;
      end;
end;

function TSDmSis.SetarProviderFormDinamico(pDsDatSet : TBrvDataSet;
                                           pDsProvid : TBrvProvider) : Boolean;
var lNrConexa : Integer;
begin
      Result    := False;
      lNrConexa := 1;
      while (lNrConexa <= cQtConDin) do
      begin
            if not gConDinam[lNrConexa].ConOcupado then
            begin
                  gConDinam[lNrConexa].ConOcupado  := True;
                  gConDinam[lNrConexa].ConDataSet  := pDsDatSet;
                  gConDinam[lNrConexa].ConProvider := pDsProvid;

                  lNrConexa := cQtConDin;
                  Result    := True;
            end;

            inc(lNrConexa);
      end;
end;

procedure TSDmSis.LiberarProviderFormDinamico(pDsCreden: String; var pDsResult: String;
                                              pNmProvid: String);
var lNrConexa : Integer;
    lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            lNrConexa := 1;

            while (lNrConexa <= cQtConDin) do
            begin
                  if gConDinam[lNrConexa].ConProvider.Name = pNmProvid then
                  begin
                        gConDinam[lNrConexa].ConOcupado  := False;
                        FreeAndNil(gConDinam[lNrConexa].ConDataSet);
                        FreeAndNil(gConDinam[lNrConexa].ConProvider);

                        lNrConexa := cQtConDin;
                  end;

                  inc(lNrConexa);
            end;
      end;
end;

procedure TSDmSis.AtualizaEmpresasSelecionadasUsuario(pDsCreden: String;
                            var pDsResult: String; pCdEmpres: String; pCdUsuari: Integer);
var lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            try
                QExecute.CommandText :=
                         'Update S005 set SnEmpIni = ' + #39 + 'N' + #39 +
                         ' Where CdUsuari = ' + IntToStr(pCdUsuari);
                QExecute.ExecSQL(True);
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;

            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Update S005 set SnEmpIni = ' + #39 + 'S' + #39 +
                               ' Where CdUsuari = ' + IntToStr(pCdUsuari) +
                               ' and   CdEmpres in (' + pCdEmpres + ')';
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;
      end;
end;

procedure TSdmSis.AtualizarLog(pDsCreden : String; var pDsResult : String;
                               pNmTabela : String;     pCdUsuari : Integer;
                               pVrChave  : String;     pTpOperac : Byte;
                               pNmFormul : String;     pCdsLog   : OleVariant);
var lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            try
                DmDicion.BrvDicionario.AtualizarLog(pNmTabela, pVrChave,  pTpOperac,
                                                    pCdsLog,   pNmFormul, pCdUsuari,
                                                    '');
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;
      end;
end;

procedure TSDmSis.AtualizarLogScreen(pDsCreden: String; var pDsResult: String; pCdUsuari: Integer;
                                     pNmFormul: String; pDtLogScr: String; pCdsLog: OleVariant);
var lCdUsuari : Integer;
    lCcLogScr : TClientDataSet;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            try
                lCcLogScr := TClientDataSet.Create(Self);
                lCcLogScr.Data := pCdsLog;
                ForceDirectories(FrmLogos.CaminhoApp + 'LogScr');

                lCcLogScr.SaveToFile(FrmLogos.CaminhoApp + 'LogScr\' + FormatFloat('0', pCdUsuari) +
                                                                           '_'+ pNmFormul + '_' +
                                                                           pDtLogScr + '.bin');
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;
      end;
end;

procedure TSDmSis.AtualizarPosicaoChave(pDsCreden : String; var pDsResult : String;
                                        pTpChave  : String;     pNmChaEst : String;
                                        pNmTabela : String;     pNrNewPos : Integer;
                                        pNrOldPos : Integer);
var lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            if pTpChave = 'P' then
            begin
                  AtualizarPosicaoChavePrimaria(pDsResult, pNmTabela, pNrNewPos, pNrOldPos);
            end else
            begin
                  if pTpChave = 'E' then
                  begin
                        AtualizarPosicaoChaveEstrangeira(pDsResult, pNmChaEst,
                                                         pNrOldPos, pNrNewPos);
                  end else
                  begin
                        if pTpChave = 'I' then
                        begin
                              AtualizarPosicaoIndiceSecundario(pDsResult, pNmChaEst,
                                                               pNrOldPos, pNrNewPos);
                        end;
                  end;
            end;
      end;
end;

procedure TSDmSis.ExcluirChaveEstrangeira(pDsCreden: String; var pDsResult: String;
                                          pNmChaEst: String);
var lCdUsuari : Integer;
    lDsTransa : TTransactionDesc;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            lDsTransa := DmDicion.NumeroTransactionID;
            SqlConnSis.StartTransaction(lDsTransa);
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Excluindo os atributos da chave
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            try
                QExecute.CommandText :=
                         'Delete From S013 ' +
                         'Where NmChaEst = ' + #39 + pNmChaEst + #39;
                QExecute.ExecSQL(True);
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // Excluindo a chave
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Delete From S012 ' +
                               'Where NmChaEst = ' + #39 + pNmChaEst + #39;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;

            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      SqlConnSis.Commit(lDsTransa);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end else
            begin
                  SqlConnSis.Rollback(lDsTransa);
            end;
      end;
end;

procedure TSDmSis.ExcluirFormularioDinamico(pDsCreden: String; var pDsResult: String;
                                            pNrForDin: String);
var lCdUsuari : Integer;
    lDsTransa : TTransactionDesc;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            lDsTransa := DmDicion.NumeroTransactionID;
            SqlConnSis.StartTransaction(lDsTransa);
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // Excluindo ordenação inicial
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Delete From S017 ' +
                               'Where NrForDin = ' + #39 + pNrForDin + #39;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // Excluindo atributos da consulta
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Delete From S026 ' +
                               'Where NrForDin = ' + #39 + pNrForDin + #39;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // Excluindo atributos de retorno consulta
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Delete From S045 ' +
                               'Where NrForDin = ' + #39 + pNrForDin + #39;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Excluindo os atributos do formulário
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Delete From S015 ' +
                               'Where NrForDin = ' + #39 + pNrForDin + #39;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // Excluindo o formulário
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Delete From S014 ' +
                               'Where NrForDin = ' + #39 + pNrForDin + #39;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;

            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      SqlConnSis.Commit(lDsTransa);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end else
            begin
                  SqlConnSis.Rollback(lDsTransa);
            end;
      end;
end;

procedure TSDmSis.ExcluirIndiceSecundario(pDsCreden: String; var pDsResult: String;
                                          pNmIndice: String);
var lCdUsuari : Integer;
    lDsTransa : TTransactionDesc;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            lDsTransa := DmDicion.NumeroTransactionID;
            SqlConnSis.StartTransaction(lDsTransa);
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Excluindo os atributos da chave
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            try
                QExecute.CommandText :=
                         'Delete From S019 ' +
                         'Where NmIndice = ' + #39 + pNmIndice + #39;
                QExecute.ExecSQL(True);
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // Excluindo a chave
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Delete From S018 ' +
                               'Where NmIndice = ' + #39 + pNmIndice + #39;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;

            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      SqlConnSis.Commit(lDsTransa);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end else
            begin
                  SqlConnSis.Rollback(lDsTransa);
            end;
      end;
end;

procedure TSdmSis.SalvarColunasConsultaUsuario(pDsCreden : String; var pDsResult : String;
                                               pNrQueCon : Integer;    pCdUsuari : Integer;
                                               pData     : OleVariant; pNmFormul : String);
var lCdsData  : TClientDataSet;
    lCdUsuari : Integer;
    lDsTransa : TTransactionDesc;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            try
                lCdsData      := TClientDataSet.Create(Self);
                lCdsData.Data := pData;

                lDsTransa := DmDicion.NumeroTransactionID;
                SqlConnSis.StartTransaction(lDsTransa);

                if pDsResult = UFrmLogos.cDsMsgOk then
                begin
                      try
                          CpDados.Close;
                          CpDados.CommandText := 'Select * From S029 ' +
                                                 'Where NrQueCon is null';
                          CpDados.Open;
                          CpDados.Append;
                          CpDados.FieldByName('NrQueCon').AsInteger  := pNrQueCon;
                          CpDados.FieldByName('NmFormul').AsString   := pNmFormul;
                          CpDados.FieldByName('DtExecut').AsDateTime :=
                                                    DmDicion.BrvDicionario.DataHoraServer;

                          CpDados.Post;
                          CpDados.BrApplyUpdates;
                      except
                           on E: Exception do
                                 begin
                                       pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                                 end;
                      end;
                end;

                if pDsResult = UFrmLogos.cDsMsgOk then
                begin
                      try
                          QExecute.CommandText := 'Delete From S028 ' +
                                               'Where NrQueCon = ' + IntToStr(pNrQueCon) +
                                               '  and CdUsuari = ' + IntToStr(pCdUsuari);
                          QExecute.ExecSQL(True);
                      except
                           on E: Exception do
                                 begin
                                       pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                                 end;
                      end;
                end;

                if pDsResult = UFrmLogos.cDsMsgOk then
                begin
                      CpDados.Close;
                      CpDados.CommandText := 'Select * From S028 ' +
                                             'Where NrQueCon = ' + IntToStr(pNrQueCon) +
                                             '  and CdUsuari = ' + IntToStr(pCdUsuari);
                      CpDados.Open;

                      while not lCdsData.Eof do
                      begin
                            CpDados.Append;
                            CpDados.FieldByName('NrQueCon').AsInteger :=
                                    lCdsData.FieldByName('NrQueCon').AsInteger;
                            CpDados.FieldByName('CdUsuari').AsInteger :=
                                    lCdsData.FieldByName('CdUsuari').AsInteger;
                            CpDados.FieldByName('NrOrdAtr').AsInteger :=
                                    lCdsData.FieldByName('NrOrdAtr').AsInteger;
                            CpDados.FieldByName('NmTabela').AsString  :=
                                    lCdsData.FieldByName('NmTabela').AsString;
                            CpDados.FieldByName('NmAtribu').AsString  :=
                                    lCdsData.FieldByName('NmAtribu').AsString;
                            CpDados.FieldByName('VrLarCom').AsInteger :=
                                    lCdsData.FieldByName('VrLarCom').AsInteger;
                            CpDados.Post;

                            lCdsData.Next;
                      end;

                      try
                          CpDados.BrApplyUpdates;
                      except
                           on E: Exception do
                                 begin
                                       pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                                 end;
                      end;
                end;

                if pDsResult = UFrmLogos.cDsMsgOk then
                begin
                      try
                          SqlConnSis.Commit(lDsTransa);
                      except
                           on E: Exception do
                                 begin
                                       pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                                 end;
                      end;
                end else
                begin
                      SqlConnSis.Rollback(lDsTransa);
                end;
            finally
                FreeAndNil(lCdsData);
            end;
      end;
end;

procedure TSDmSis.SalvarConfigConsultaUsuario(pDsCreden : String; var pDsResult : String;
                                              pNrQueCon : Integer;    pCdUsuari : Integer;
                                              pNmTabOrd : String;     pNmAtrOrd : String;
                                              pNmTabPes : String;     pNmAtrPes : String;
                                              pNmTabPe2 : String;     pNmAtrPe2 : String;
                                              pDsLocal2 : String;     pTpMaiMin : String;
                                              pTpWhere  : Integer);
var lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            CpDados.Close;
            CpDados.CommandText := 'Select * From S027 ' +
                         'Where NrQueCon = ' + IntToStr(pNrQueCon) +
                         '  and CdUsuari = ' + IntToStr(pCdUsuari);
            CpDados.Open;
            if CpDados.Eof then
            begin
                  CpDados.Append;
                  CpDados.FieldByName('NrQueCon').AsInteger := pNrQueCon;
                  CpDados.FieldByName('CdUsuari').AsInteger := pCdUsuari;
            end else
            begin
                  CpDados.Edit;
            end;

            CpDados.FieldByName('NmTabOrd').AsString := pNmTabOrd;
            CpDados.FieldByName('NmAtrOrd').AsString := pNmAtrOrd;
            CpDados.FieldByName('NmTabPes').AsString := pNmTabPes;
            CpDados.FieldByName('NmAtrPes').AsString := pNmAtrPes;
            CpDados.FieldByName('NmTabPe2').AsString := pNmTabPe2;
            CpDados.FieldByName('NmAtrPe2').AsString := pNmAtrPe2;
            CpDados.FieldByName('DsLocal2').AsString := pDsLocal2;
            CpDados.FieldByName('TpMaiMin').AsString := pTpMaiMin;
            CpDados.FieldByName('TpWhere').AsInteger := pTpWhere;
            CpDados.Post;

            try
                CpDados.BrApplyUpdates;
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;
      end;
end;

procedure TSDmSis.SalvarObjetosFormDinamico(pDsCreden : String; var pDsResult: String;
                                            pNrForDin : String;     pData    : OleVariant;
                                            pDsOrdIni : String;     pNmTabela: String;
                                            pCdsConsu : OleVariant; pNmFrmOri: String;
                                            pCdsRetCo : OleVariant);
var lCdsFormul : TClientDataSet;
    lNrColuna  : Integer;
    lNmColuna  : String;
    lCdUsuari  : Integer;
    lDsTransa  : TTransactionDesc;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            lDsTransa := DmDicion.NumeroTransactionID;
            SqlConnSis.StartTransaction(lDsTransa);

            QpAtrDin.BrUserCode := lCdUsuari;
            QpAtrDin.BrFormName := pNmFrmOri;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // Excluindo o formulário
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  if pDsResult = UFrmLogos.cDsMsgOk then
                  begin
                        try
                            QExecute.CommandText :=
                                     'Delete From S026 ' +
                                     'Where NrForDin = ' + #39 + pNrForDin + #39;
                            QExecute.ExecSQL(True);
                        except
                             on E: Exception do
                                   begin
                                         pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                                   end;
                        end;
                  end;

                  if pDsResult = UFrmLogos.cDsMsgOk then
                  begin
                        try
                            QExecute.CommandText :=
                                     'Delete From S045 ' +
                                     'Where NrForDin = ' + #39 + pNrForDin + #39;
                            QExecute.ExecSQL(True);
                        except
                             on E: Exception do
                                   begin
                                         pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                                   end;
                        end;
                  end;

                  if pDsResult = UFrmLogos.cDsMsgOk then
                  begin
                        try
                            QExecute.CommandText :=
                                     'Delete From S015 ' +
                                     'Where NrForDin = ' + #39 + pNrForDin + #39;
                            QExecute.ExecSQL(True);
                        except
                             on E: Exception do
                                   begin
                                         pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                                   end;
                        end;
                  end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // Cadastrando novamente o formulário dinâmico
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  QpAtrDin.Close;
                  QpAtrDin.CommandText := 'Select * From S015 Where NrForDin is null';
                  QpAtrDin.Open;

                  lCdsFormul := TClientDataSet.Create(Self);
                  lCdsFormul.Data := pData;

                  while not lCdsFormul.Eof do
                  begin
                        QpAtrDin.Append;

                        for lNrColuna := 0 to lCdsFormul.Fields.Count -1 do
                        begin
                              lNmColuna := lCdsFormul.Fields.Fields[lNrColuna].FieldName;

                              if UpperCase(lNmColuna) = 'NRQUECON' then
                              begin
                                    if lCdsFormul.FieldByName(lNmColuna).AsInteger >
                                                                                    0 then
                                    begin
                                          QpAtrDin.FieldByName(lNmColuna).AsString :=
                                               lCdsFormul.FieldByName(lNmColuna).AsString;
                                    end;
                              end else
                              begin
                                    QpAtrDin.FieldByName(lNmColuna).AsString :=
                                               lCdsFormul.FieldByName(lNmColuna).AsString;
                              end;
                        end;


                        QpAtrDin.Post;
                        lCdsFormul.Next;
                  end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // Cadastrando Atributos de diplay da consulta
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  QpS026.BrUserCode := lCdUsuari;
                  QpS026.BrFormName := pNmFrmOri;
                  QpS026.Close;
                  QpS026.CommandText := 'Select * From S026 Where NrForDin is null';
                  QpS026.Open;

                  lCdsFormul.Data := pCdsConsu;
                  lCdsFormul.First;
                  while not lCdsFormul.Eof do
                  begin
                        QpS026.Append;

                        for lNrColuna := 0 to lCdsFormul.Fields.Count -1 do
                        begin
                              lNmColuna := lCdsFormul.Fields.Fields[lNrColuna].FieldName;
                              QpS026.FieldByName(lNmColuna).AsString :=
                                               lCdsFormul.FieldByName(lNmColuna).AsString;
                        end;

                        QpS026.Post;
                        lCdsFormul.Next;
                  end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // Cadastrando Atributos de retorno da consulta
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  QpS045.BrUserCode := lCdUsuari;
                  QpS045.BrFormName := pNmFrmOri;
                  QpS045.Close;
                  QpS045.CommandText := 'Select * From S045 Where NrForDin is null';
                  QpS045.Open;

                  lCdsFormul.Data := pCdsRetCo;
                  lCdsFormul.First;
                  while not lCdsFormul.Eof do
                  begin
                        QpS045.Append;

                        for lNrColuna := 0 to lCdsFormul.Fields.Count -1 do
                        begin
                              lNmColuna := lCdsFormul.Fields.Fields[lNrColuna].FieldName;
                              QpS045.FieldByName(lNmColuna).AsString :=
                                               lCdsFormul.FieldByName(lNmColuna).AsString;
                        end;

                        QpS045.Post;
                        lCdsFormul.Next;
                  end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // Cadastrando Ordenação Inicial
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  CadastrarOrdenacaoInicial(pDsCreden, pDsResult, pNrForDin,
                                            pDsOrdIni, pNmTabela);
            end;

            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QpAtrDin.BrApplyUpdates;
                      QpS026.BrApplyUpdates;
                      QpS045.BrApplyUpdates;
                      SqlConnSis.Commit(lDsTransa);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end else
            begin
                  SqlConnSis.Rollback(lDsTransa);
            end;
      end;
end;

procedure TSDmSis.CadastrarOrdenacaoInicial(pDsCreden: String; var pDsResult: String;
                                            pNrForDin: String;
                                            pDsOrdIni: String; pNmTabela : String);
var lStrAtribu : TBrvString;
    lNrOrdIni  : Integer;
    lNrOrdem   : Integer;
begin
     //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
     //=-=-= Excluindo ordenação inicial
     //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
     try
         QExecute.CommandText :=
                  'Delete From S017 ' +
                  'Where NrForDin = ' + #39 + pNrForDin + #39;
         QExecute.ExecSQL(True);
     except
          on E: Exception do
                begin
                      pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                end;
     end;

     if (pDsResult = UFrmLogos.cDsMsgOk) and
        (pDsOrdIni <> '') then // teste feio aqui e não na chamada, para poder limpar
     begin
          //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          //=-=-= Cadastrando ordenação inicial
          //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          lStrAtribu := TBrvString.Create(Self);

          try
              lStrAtribu.Split(pDsOrdIni, '@');
              lNrOrdem := 0;

              for lNrOrdIni := 0 to lStrAtribu.BrSplitLista.Count -1 do
              begin
                    if lStrAtribu.BrSplitLista.Strings[lNrOrdIni] <> '' then
                    begin
                          inc(lNrOrdem);
                          try
                              QExecute.CommandText :=
                                 'Insert Into S017 (NmTabela, NrForDin, '   +
                                                   'NmAtribu, NrOrdem) values (' +
                                  #39 + pNmTabela + #39 + ', ' +
                                  #39 + pNrForDin + #39 + ', ' +
                                  #39 + lStrAtribu.BrSplitLista.Strings[lNrOrdIni] + #39 +
                                  ', ' +
                                  #39 + IntToStr(lNrOrdem) + #39 +
                                  ')';
                              QExecute.ExecSQL(True);
                          except
                               on E: Exception do
                                     begin
                                           pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                                     end;
                          end;
                    end;
              end;
          finally
              FreeAndNil(lStrAtribu);
          end;
     end;
end;

procedure TSDmSis.ExcluirAtributoIndiceSecundario(pDsCreden: String; var pDsResult: String;
                                                  pNmIndice: String;     pNmAtribu: String);
var lCdUsuari : Integer;
    lDsTransa : TTransactionDesc;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            lDsTransa := DmDicion.NumeroTransactionID;
            SqlConnSis.StartTransaction(lDsTransa);
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Excluindo os atributos da chave
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            try
                QExecute.CommandText :=
                         'Delete From S019 ' +
                         'Where NmIndice = ' + #39 + pNmIndice + #39 +
                         '  and NmAtribu = ' + #39 + pNmAtribu + #39;
                QExecute.ExecSQL(True);
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;

            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      SqlConnSis.Commit(lDsTransa);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end else
            begin
                  SqlConnSis.Rollback(lDsTransa);
            end;
      end;
end;

procedure TSDmSis.ExcluirAtributoChaveEstrangeira(pDsCreden: String; var pDsResult: String;
                                                  pNmChaEst: String;     pNmAtribu: String);
var lCdUsuari : Integer;
    lDsTransa : TTransactionDesc;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            lDsTransa := DmDicion.NumeroTransactionID;
            SqlConnSis.StartTransaction(lDsTransa);
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Excluindo os atributos da chave
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            try
                QExecute.CommandText :=
                         'Delete From S013 ' +
                         'Where NmChaEst = ' + #39 + pNmChaEst + #39 +
                         '  and NmAtribu = ' + #39 + pNmAtribu + #39;
                QExecute.ExecSQL(True);
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;

            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      SqlConnSis.Commit(lDsTransa);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end else
            begin
                  SqlConnSis.Rollback(lDsTransa);
            end;
      end;
end;

procedure TSDmSis.ExcluirAtributoPrimario(pDsCreden: String; var pDsResult: String;
                                          pNmTabela, pNmAtribu: String);
var lNrKey    : Integer;
    lCdUsuari : Integer;
    lDsTransa : TTransactionDesc;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            lDsTransa := DmDicion.NumeroTransactionID;
            SqlConnSis.StartTransaction(lDsTransa);
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Excluindo a Chave -=-=-=-=-=-=-=
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            try
                QExecute.CommandText :=
                         'Delete From S011 ' +
                         'Where NmTabela = ' + #39 + pNmTabela + #39 +
                         '  and NmAtribu = ' + #39 + pNmAtribu + #39;
                QExecute.ExecSQL(True);
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // Atualizando as posições da chave primária
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Update S011 Set NrSeqKey = NrSeqKey + 10000 ' +
                               'Where NmTabela = ' + #39 + pNmTabela + #39 +
                               '  and NmAtribu = ' + #39 + pNmAtribu + #39;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;

            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  lNrKey := 0;
                  CcChaPri.CommandText := 'Select * From S011 ' +
                               'Where NmTabela = ' + #39 + pNmTabela + #39 +
                               '  and NmAtribu = ' + #39 + pNmAtribu + #39;
                  CcChaPri.Open;
                  while not CcChaPri.Eof do
                  begin
                        CcChaPri.Edit;
                        CcChaPri.FieldByName('NrSeqKey').AsInteger := lNrKey;
                        CcChaPri.Post;
                  end;

                  try
                      CcChaPri.BrApplyUpdates;
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      SqlConnSis.Commit(lDsTransa);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end else
            begin
                  SqlConnSis.Rollback(lDsTransa);
            end;
      end;
end;

procedure TSDmSis.ExcluirAtributo(pDsCreden: String; var pDsResult: String; pNmTabela,
  pNmAtribu: String);
var lCdUsuari : Integer;
    lDsTransa : TTransactionDesc;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            lDsTransa := DmDicion.NumeroTransactionID;
            SqlConnSis.StartTransaction(lDsTransa);
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Domínio da coluna =-=-=-=-=-=-=-=
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            try
                QExecute.CommandText :=
                         'Delete From S010 ' +
                         'Where NmTabela = ' + #39 + pNmTabela + #39 +
                         '  and NmAtribu = ' + #39 + pNmAtribu + #39;
                QExecute.ExecSQL(True);
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Excluindo a coluna -=-=-=-=-=-=-=
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Delete From S009 ' +
                               'Where NmTabela = ' + #39 + pNmTabela + #39 +
                               '  and NmAtribu = ' + #39 + pNmAtribu + #39;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;

            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      SqlConnSis.Commit(lDsTransa);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end else
            begin
                  SqlConnSis.Rollback(lDsTransa);
            end;
      end;
end;

procedure TSDmSis.ExcluirTabela(pDsCreden : String; var pDsResult : String;
                                pNmTabela : String);
var lCdUsuari : Integer;
    lDsTransa : TTransactionDesc;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            lDsTransa  := DmDicion.NumeroTransactionID;
            SqlConnSis.StartTransaction(lDsTransa);
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Domínio da coluna =-=-=-=-=-=-=-=
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            try
                QExecute.CommandText :=
                         'Delete From S010 Where NmTabela = ' + #39 + pNmTabela + #39;
                QExecute.ExecSQL(True);
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Excluindo a Chave Primária -=-=-=
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Delete From S011 Where NmTabela = ' + #39 + pNmTabela + #39;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Excluindo Colunas da Chave Est.-=
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Delete From S013 Where NmTabela = ' + #39 + pNmTabela + #39;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Excluindo a Chave Estrangeira =-=
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Delete From S012 Where NmTabela = ' + #39 + pNmTabela + #39;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Excluindo a coluna -=-=-=-=-=-=-=
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Delete From S009 Where NmTabela = ' + #39 + pNmTabela + #39;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Excluindo as Triggers
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Delete From S020 Where NmTabela = ' + #39 +
                                                                         pNmTabela + #39;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Excluindo os atributos do índice
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Delete From S019 Where NmTabela = ' + #39 +
                                                                         pNmTabela + #39;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Excluindo os índices
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Delete From S018 Where NmTabela = ' + #39 +
                                                                         pNmTabela + #39;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-= Aplicando Atualizações no banco
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      QExecute.CommandText :=
                               'Delete From S008 Where NmTabela = ' + #39 + pNmTabela + #39;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;

            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      SqlConnSis.Commit(lDsTransa);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end else
            begin
                  SqlConnSis.Rollback(lDsTransa);
            end;
      end;
end;

function TSDmSis.OrdemInicialFormDinamico(pDsCreden : String; var pDsResult : String;
                                          pNmTabela : String) : OleVariant;
var lDsSql    : String;
    lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            lDsSql :=
               ' Select S009.NmAtribu, S009.DsAtribu, S017.NmTabela, S017.NrOrdem ' +
               ' From   S009 S009 ' +
               ' Left Join S017 S017 on (S017.NmTabela = S009.NmTabela and ' +
               '                         S017.NmAtribu = S009.NmAtribu)    ' +
               ' Where  S009.NmTabela = ' + #39 + pNmTabela + #39 +
               ' Order by S017.NrOrdem, S009.DsAtribu ';

            try
                CpDados.Close;
                CpDados.CommandText := lDsSql;
                CpDados.Open;

                Result    := CpDados.Data;
            except
                on E: Exception do
                      begin
                            pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                      end;
            end;
      end;
end;

procedure TSDmSis.ReorganizarOrdenacaoMenu(pDsCreden: String; var pDsResult: String;
                                           pNrMenPai: Integer);
var lDsSql    : String;
    lNrOrdem  : Integer;
    lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            lDsSql :=
               ' Select S033.* ' +
               ' From   S033 S033 ' +
               ' Where  S033.NrMenPai = ' + IntToStr(pNrMenPai) +
               ' Order by S033.NrOrdem';

            try
                CpDados.Close;
                CpDados.CommandText := lDsSql;
                CpDados.Open;
                CpDados.First;
                lNrOrdem := 0;

                while not CpDados.Eof do
                begin
                      inc(lNrOrdem);

                      CpDados.Edit;
                      CpDados.FieldByName('NrOrdem').AsInteger := lNrOrdem;
                      CpDados.Post;
                      CpDados.Next;
                end;

                CpDados.BrApplyUpdates;
            except
                on E: Exception do
                      begin
                            pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                      end;
            end;
      end;
end;

procedure TSDmSis.MoverMenu(pDsCreden: String; var pDsResult: String;
                            pNrMenPai: Integer; pNrOldOrd : Integer;
                            pNrNewOrd: Integer);
var lDsSql    : String;
    lNrOrdem  : Integer;
    lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            try
                QExecute.CommandText :=
                         'Update S033 set NrOrdem = 99999' +
                         ' Where NrMenPai = ' + IntToStr(pNrMenPai)   +
                         ' and   NrOrdem  = ' + IntToStr(pNrOldOrd);
                QExecute.ExecSQL(True);
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;

            // atualizando a nova posição
            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                      if pNrOldOrd > pNrNewOrd then
                      begin
                            lDsSql :=
                               'Update S033 set NrOrdem = NrOrdem + 1' +
                               ' Where NrMenPai = ' + IntToStr(pNrMenPai) +
                               ' and   NrOrdem >= ' + IntToStr(pNrNewOrd) +
                               ' and   NrOrdem <  ' + IntToStr(pNrOldOrd);
                      end else
                      begin
                            lDsSql :=
                               'Update S033 set NrOrdem = NrOrdem - 1' +
                               ' Where NrMenPai = ' + IntToStr(pNrMenPai) +
                               ' and   NrOrdem <= ' + IntToStr(pNrNewOrd) +
                               ' and   NrOrdem >  ' + IntToStr(pNrOldOrd);
                      end;
                      QExecute.CommandText := lDsSql;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;

            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  try
                            lDsSql :=
                               'Update S033 set NrOrdem = ' + IntToStr(pNrNewOrd) +
                               ' Where NrMenPai = ' + IntToStr(pNrMenPai) +
                               ' and   NrOrdem  = 99999';

                      QExecute.CommandText := lDsSql;
                      QExecute.ExecSQL(True);
                  except
                       on E: Exception do
                             begin
                                   pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                             end;
                  end;
            end;
      end;
end;

function TSDmSis.GravarLogImagens(pCdsImagem : OleVariant; pDsPath : String) : Integer;
var lCdsImagem : TClientDataSet;
begin
      Result := DmDicion.BrvDicionario.ProxNumeroSequencial('S043', 'NRSEQLOG');

      try
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-= Carregando o CDS de imagens
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          lCdsImagem := TClientDataSet.Create(Self);
          lCdsImagem.Data := pCdsImagem;
          lCdsImagem.First;

          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-= Gravando registro de log no banco
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          QExecute.CommandText := 'Insert Into S043 (NrSeqLog, QtImgLog) values (' +
                                   #39 + IntToStr(Result) + #39 + ', ' +
                                   #39 + IntToStr(lCdsImagem.RecordCount) + #39 +
                                   ')';
          QExecute.ExecSQL(True);

          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-= Gravando imagens em disco
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          pDsPath := pDsPath + FormatFloat('000000', Result) + '_';

          while not lCdsImagem.Eof do
          begin
                (lCdsImagem.FieldByName('ImImagem') as TBlobField).SaveToFile(
                            pDsPath +
                            FormatFloat('000000', +
                                 lCdsImagem.FieldByName('NrImagem').AsInteger) + '.jpg');
                lCdsImagem.Next;
          end;
      finally
          FreeAndNil(lCdsImagem);
      end;
end;

end.

