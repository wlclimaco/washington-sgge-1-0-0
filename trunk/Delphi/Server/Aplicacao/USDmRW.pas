unit USDmRW;

interface

uses
  SysUtils, Classes, DSServer, DBXOracle, DB, SqlExpr, FMTBcd, DBClient, Provider,
  BrvDataSet, BrvClientDataSet, UDmDicion, BrvConnection, BrvOracle, BrvDb, BrvListParam;

type
  TSDmRW = class(TDSServerModule)
    SqlConnRW: TBrvConnection;
    QpDados: TSQLDataSet;
    DpDados: TDataSetProvider;
    CpDados: TClientDataSet;
    CpUsuario: TBrvClientDataSet;
    QpUsuario: TBrvDataSet;
    DpUsuario: TDataSetProvider;
    BrvOracle: TBrvOracle;
    CdsDirSer: TClientDataSet;
    CdsDirCli: TClientDataSet;
    QExecute: TBrvDataSet;
    procedure SqlConnRWAfterConnect(Sender: TObject);
  private
    { Private declarations }
    function CriptografarCodigoAcesso(pNrProCli: Integer) : String;
    function ProcessarAtualizacoCliente(pCdsClient: OleVariant): OleVariant;
  public
    { Public declarations }
    function EntradaCliente(pNoIp : String; pNmComput : String; pNrConTCP : String;
                            pDsCreden : String) : String;

    procedure ClienteAutenticado(pDsCreden: String; var pDsResult: String;
      pCdUsuari: Integer; pDsLogin: String; pNrConTcp : String);
    function RetornaQuantidadeRegistroTabela(pDsCreden : String; var pDsResult : String;
                                             pNmTabela : String) : Integer;
    function RetornaDadosSqlCadastro(pDsCreden : String;
                                 var pDsResult : String; pNrSql : Integer;
                                     pDsParams : String;
                                     pNmFormul : String) : OleVariant;
    function RetornaDadosSqlFixa(pDsCreden : String;
                             var pDsResult : String; pDsSql : String) : OleVariant;
    procedure AutenticarUsuario(pDsCreden : String;  var pDsResult : String;
                                pDsLogin  : String;  var pCdSenha  : Integer;
                            var pCdUsuari : Integer; var pNmUsuari : String);
    procedure AtualizarSenhaUsuario(pDsCreden : String; var pDsResult : String;
                                   pDsLogin  : String; pNrSenha : Integer);
    function ExecutarInstrucaoSql(pDsCreden: String; var pDsResult: String;
      pDsSql: String): Integer;
    procedure ChavePrimaria(pDsCreden : String;
                       var pDsResult : String; pNmTabela : String;
                       var pStlChave : String);
    function  ProxNumeroSequencial(pDsCreden : String; var pDsResult : String;
                                   pNmTabela : String; pNmAtribu : String) : Integer;
    procedure DataHoraServer(pDsCreden : String; var pDsResult : String;
                                                 var pDtDatHor: TDateTime);
    procedure ValorDefault(pDsCreden: String; var pDsResult: String;
                                             var pVrDefaul: String);
    procedure EncontrarInstrucaoSQLConsulta(pDsCreden : String; var pDsResult : String;
                                            pNrQueCon : Integer; var pDsSql : String);
    procedure EncontrarInstrucaoSQL(pDsCreden : String; var pDsResult : String;
                                    pNrQuery  : Integer; var pDsSql : String;
                                    pNmFormul : String);
    function  TipoBancoDados(pDsCreden : String;    var pDsResult : String;
                         var pDtForBco : String;    var pNrSrVers : Integer;
                         var pNrSrVeSu : Integer) : OleVariant;
    procedure AtualizarDicionarioDados(pDsCreden : String; var pDsResult : String);
    function  VerificarNovaVersaoCliente(pDsCreden : String;    var pDsResult : String;
                                         pCdsClient: OleVariant) : OleVariant;
    function ParametroDaEmpresa(pDsCreden : String;
                            var pDsResult : String; pNrParame : Integer;
                                                    pCdEmpres : Integer) : OleVariant;
    procedure GravarTelasAbertaUsuario(pDsCreden : String;    var pDsResult : String;
                                       pCdsTelas : OleVariant);


    //--- Transporte
    function ConsultaConhecimentos(pDsCreden : String;  var pDsResult : String;
                                   pCdEmpres : String;      pCdRemete : String;
                                   pCdDestin : String;      pCdCarga  : String;
                                   pCdVeicul : String;      pCdMotori : String;
                                   pDtEmiIni : String;      pDtEmiFim : String;
                                   pCdCtrc   : String;      pNrNota   : String;
                                   pNrFatura : String;      pNrRps    : String;
                                   pTpCarga  : String;      pCdTomado : String) : OleVariant;
    //--- Fim Transporte

  end;

implementation

uses UFrmLogos, UFrmConSrv;

{$R *.dfm}

{ TSDmRW }


function TSDmRW.RetornaDadosSqlCadastro(pDsCreden : String;
                                    var pDsResult : String;
                                        pNrSql: Integer; pDsParams : String;
                                        pNmFormul : String): OleVariant;
var lDsSql    : String;
    lDsParams : TStrings;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if pNrSql = 0 then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Número da instrução SQL não foi informada';
      end else
      begin
            EncontrarInstrucaoSQL(pDsCreden, pDsResult, pNrSql, lDsSql, pNmFormul);

            try
                lDsParams      := TStringList.Create;
                lDsParams.Text := pDsParams;
                lDsSql         := DmDicion.BrvDicionario.SubstituirParametrosSQL(
                                                                       lDsSql, lDsParams);
            finally
                FreeAndNil(lDsParams);
            end;

            Result := RetornaDadosSqlFixa(pDsCreden, pDsResult, lDsSql);
      end;
end;

function TSDmRW.RetornaDadosSqlFixa(pDsCreden : String;
                                var pDsResult: String; pDsSql : String): OleVariant;
var lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            if pDsSql = ''  then
            begin
                  pDsResult := UFrmLogos.cDsMsgEr + 'Instrução SQL não informada.';
            end else
            begin
                  try
                      Result  := DmDicion.BrvDicionario.RetornaDadosSqlFixa(pDsSql);
                  except
                      on E: Exception do
                            begin
                                  pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                            end;
                  end;
            end;
      end;
end;

function TSDmRW.ExecutarInstrucaoSql(pDsCreden : String;
                                 var pDsResult: String; pDsSql : String): Integer;
var lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            if pDsSql = ''  then
            begin
                  pDsResult := UFrmLogos.cDsMsgEr + 'Instrução SQL não informada.';
            end else
            begin
                  try
                      QpDados.Close;
                      QpDados.CommandText := pDsSql;
                      Result := QpDados.ExecSQL(True);
                  except
                      on E: Exception do
                            begin
                                  pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                            end;
                  end;
            end;
      end;
end;

procedure TSDmRW.GravarTelasAbertaUsuario(pDsCreden: String; var pDsResult: String;
                                                                 pCdsTelas: OleVariant);
var lCdUsuari : Integer;
    lCdsTelas : TClientDataSet;
    lTpFormul : String;
    lNrFormul : String;
    lNrSequen : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            try
                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                // =-=-= Excluindo Telas anteriores
                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                QExecute.CommandText := 'delete from S044 Where CdUsuari = ' +
                                         IntToStr(lCdUsuari);
                QExecute.ExecSQL(True);

                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                // =-=-= Inserindo novamente as telas abertas
                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                lCdsTelas := TClientDataSet.Create(Self);
                lCdsTelas.Data := pCdsTelas;
                lCdsTelas.First;

                lNrSequen := 0;

                while not lCdsTelas.Eof do
                begin
                      inc(lNrSequen);
                      if lCdsTelas.FieldByName('NmFormul').AsString <> ''  then
                      begin
                            lNrFormul := lCdsTelas.FieldByName('NmFormul').AsString;
                            lTpFormul := Copy(lNrFormul, 1, 3);
                            Delete(lNrFormul, 1, 3);

                            QExecute.CommandText := 'Insert into S044 (' +
                                     'CdUsuari, NrSeqTel, TpFormul, NrSeqFor ' +
                                     ') values (' +
                                     IntToStr(lCdUsuari)   + ', ' +
                                     IntToStr(lNrSequen)   + ', ' +
                                     #39 + lTpFormul + #39 + ', ' +
                                     lNrFormul + ')';
                            QExecute.ExecSQL(True);
                      end else
                      begin
                            QExecute.CommandText := 'Insert into S044 (' +
                                     'CdUsuari, NrSeqTel, NrFordin ' +
                                     ') values (' +
                                     IntToStr(lCdUsuari)   + ', ' +
                                     IntToStr(lNrSequen)   + ', ' +
                                     lCdsTelas.FieldByName('NrFormul').AsString + ')';
                            QExecute.ExecSQL(True);
                      end;

                      lCdsTelas.Next;
                end;
            finally
                FreeAndNil(lCdsTelas);
            end;
      end;
end;

function TSDmRW.ProxNumeroSequencial(pDsCreden : String; var pDsResult: String; pNmTabela,
  pNmAtribu: String): Integer;
var lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            try
                Result := DmDicion.BrvDicionario.ProxNumeroSequencial(pNmTabela, pNmAtribu);
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;
      end;
end;

function TSDmRW.RetornaQuantidadeRegistroTabela(pDsCreden: String; var pDsResult: String;
                                                pNmTabela: String): Integer;
var lDsSql   : String;
    lCdsTemp : TClientDataSet;
begin
      pDsResult := UFrmLogos.cDsMsgOk;
      lDsSql    := 'Select count(*) as QtRegist From ' + pNmTabela;
      try
          lCdsTemp := TClientDataSet.Create(self);
          try
              lCdsTemp.Data := RetornaDadosSqlFixa(pDsCreden, pDsResult, lDsSql);

              if pDsResult[1] = '0' then
              begin
                    Result := lCdsTemp.FieldByName('QtRegist').AsInteger;
              end;
          except
              Result := 0;
          end;
      finally
          FreeAndnIl(lCdsTemp);
      end;
end;

procedure TSDmRW.SqlConnRWAfterConnect(Sender: TObject);
begin
      SqlConnRW.ExecuteDirect('alter session set nls_numeric_characters = ''.,''');
end;


function TSdmRw.TipoBancoDados(pDsCreden : String;    var pDsResult : String;
                           var pDtForBco : String;    var pNrSrVers : Integer;
                           var pNrSrVeSu : Integer) : OleVariant;
var lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            Result    := DmDicion.BrvDicionario.TipoBancoDados;
            pDtForBco := DmDicion.BrvDicionario.FormatoDataBanco;
            pNrSrVers := DmDicion.BrvDicionario.VersaoServidor;
            pNrSrVeSu := DmDicion.BrvDicionario.VersaoServidorSub;

      end;
end;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// =-=-=  Instruções para informação da entrada do cliente no sistema
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Function TSDmRW.CriptografarCodigoAcesso(pNrProCli : Integer) : String;
begin
      Result := IntToStr(pNrProCli);
end;

function TSDmRW.EntradaCliente(pNoIp     : String; pNmComput : String;
                               pNrConTCP : String; pDsCreden : String): String;
var lNrCreden : Integer;
begin
      if pDsCreden = '' then
      begin
            inc(FrmLogos.gNrProCli);
            lNrCreden := FrmLogos.gNrProCli;
            Result := CriptografarCodigoAcesso(lNrCreden);
            FrmLogos.CriarNode(pNmComput + ' IP:' + pNoIp, lNrCreden);
      end else
      begin
            Result := IntToStr(DmDicion.BrvDicionario.DescriptografarCredencial(pDsCreden));
      end;


      FrmConSrv.EnviaMensagemClienteConectado(StrToInt(Result), pNoIp + ';' +
                                              pNmComput + ';' + pNrConTCP);
end;

procedure TSDmRW.ChavePrimaria(pDsCreden: String; var pDsResult: String; pNmTabela: String;
                          var pStlChave: String);
  var lStlChave : TStrings;
      lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            lStlChave := TStringList.Create;

            try
                try
                    DmDicion.BrvDicionario.ChavePrimaria(pNmTabela, lStlChave);
                    pStlChave := lStlChave.CommaText;
                except
                     on E: Exception do
                           begin
                                 pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                           end;
                end;
            finally
                FreeAndNil(lStlChave);
            end;
      end;
end;

procedure TSDmRW.ClienteAutenticado(pDsCreden : String;  var pDsResult: String;
                                    pCdUsuari : Integer;     pDsLogin : String;
                                    pNrConTcp : String);
var lCdUsuari : Integer;
begin
      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            pDsCreden := IntToStr(
                             DmDicion.BrvDicionario.DescriptografarCredencial(pDsCreden));

            FrmLogos.ClienteAutenticado(pDsCreden, pDsResult, pCdUsuari, pDsLogin,
                                        pNrConTcp);

      end;
end;

procedure TSDmRW.AutenticarUsuario(pDsCreden : String;  var pDsResult: String;
                                   pDsLogin  : String;  var pCdSenha : Integer;
                               var pCdUsuari : Integer; var pNmUsuari : String);
var lDsSql   : String;
    lCdsTemp : TClientDataSet;
begin
      pDsResult := UFrmLogos.cDsMsgOk;
      lDsSql    := 'Select S001.NmComUsu, S001.CdUsuari, S001.CdSenha ' +
                   'From   S001 S001 '                                  +
                   'Where  S001.StUsuari = ' + #39 + 'A' + #39 + ' and ' +
                   '       S001.DsLogin  = ' + #39 + pDsLogin  + #39 ;
      try
          lCdsTemp := TClientDataSet.Create(self);
          try
              lCdsTemp.Data := RetornaDadosSqlFixa(pDsCreden, pDsResult, lDsSql);

              if lCdsTemp.Eof then
              begin
                    pDsResult := UFrmLogos.cDsMsgEr + 'Usuário não cadastrado ou inativo!';
              end else
              begin
                    if FrmLogos.TblUser.RecordCount > 0 then
                    begin
                          if not FrmLogos.TblUser.FindKey([pDsLogin]) then
                          begin
                                pDsResult := UFrmLogos.cDsMsgEr +
                                    'Servidor de aplicação ' + FrmLogos.EdtIpIntern.Text +
                                    ':' + FrmLogos.EdtNoPorta.Text  + #13#13 +
                                    'Este é um servidor exclusivo.' + #13#13 +
                                    'Sua conexão não foi aceita';

                          end;
                    end;

                    if pDsResult = UFrmLogos.cDsMsgOk then
                    begin
                          pCdSenha  := lCdsTemp.FieldByName('CdSenha').AsInteger;
                          pCdUsuari := lCdsTemp.FieldByName('CdUsuari').AsInteger;
                          pNmUsuari := lCdsTemp.FieldByName('NmComUsu').AsString;
                    end;
              end;
          except
              pCdSenha  := 0;
              pCdUsuari := 0;
              pNmUsuari := '';
          end;
      finally
          FreeAndnIl(lCdsTemp);
      end;
end;

procedure TSDmRW.AtualizarDicionarioDados(pDsCreden: String; var pDsResult: String);
var   lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            FrmConSrv.AtualizarDicionarioDados(False);
      end;
end;

procedure TSDmRW.AtualizarSenhaUsuario(pDsCreden: String; var pDsResult: String;
                                       pDsLogin: String; pNrSenha: Integer);
var lDsSql   : String;
begin
      pDsResult := UFrmLogos.cDsMsgOk;
      QpUsuario.CommandText    := ' Update S001 Set CdSenha = ' + IntToStr(pNrSenha) +
                                  ' Where  S001.DsLogin =  ' + #39 + pDsLogin + #39;

      try
          QpUsuario.ExecSQL;
      except
           on E: Exception do
                 begin
                       pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                 end;
      end;
end;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// =-=-=  Instruções para pegar Data e Hora do servidor
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
procedure TSDmRW.DataHoraServer(pDsCreden: String; var pDsResult: String;
                            var pDtDatHor: TDateTime);
var lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            pDtDatHor := DmDicion.BrvDicionario.DataHoraServer;
      end;
end;

// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// =-=-=  Valor Default
// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
procedure TSDmRW.ValorDefault(pDsCreden: String; var pDsResult: String;
                         var pVrDefaul: String);
var lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            pVrDefaul := DmDicion.BrvDicionario.ValorDefault(pVrDefaul)
      end;
end;

function TSDmRW.VerificarNovaVersaoCliente(pDsCreden : String;    var pDsResult : String;
                                           pCdsClient: OleVariant) : OleVariant;
var lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      // é possível que o cliente ainda não possua sua credencial, uma vez que
      // esta é passada somente após o login << Jefferson 05/09/2011 >>
      if pDsCreden <> '' then
      begin
            if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
            begin
                  pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
            end else
            begin
                  Result := ProcessarAtualizacoCliente(pCdsClient);
            end;
      end else
      begin
            Result := ProcessarAtualizacoCliente(pCdsClient);
      end;
end;

function TSDmRW.ParametroDaEmpresa(pDsCreden : String; var pDsResult: String;
                                   pNrParame : Integer; pCdEmpres: Integer): OleVariant;
var lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            try
                Result := DmDicion.BrvDicionario.ParametroDaEmpresa(pNrParame, pCdEmpres);
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;
      end;

end;

function TSdmRW.ProcessarAtualizacoCliente(pCdsClient : OleVariant) : OleVariant;
begin
      if FrmLogos.CdsAplCli.IsEmpty then
      begin
            CdsDirCli.Data := pCdsClient;
            CdsDirCli.EmptyDataSet;
      end else
      begin
            CdsDirSer.Data := FrmLogos.CdsAplCli.Data;
            CdsDirSer.IndexDefs.Clear;
            CdsDirSer.IndexDefs.Add('Key1', 'NmArqCli', [ixCaseInsensitive]);
            CdsDirSer.IndexName := 'Key1';
            CdsDirSer.SetKey;

            CdsDirCli.Data := pCdsClient;
            CdsDirCli.IndexDefs.Clear;
            CdsDirCli.IndexDefs.Add('Key1', 'NmArqCli', [ixCaseInsensitive]);
            CdsDirCli.IndexName := 'Key1';
            CdsDirCli.SetKey;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-=-= Verificando arquivos contidos no cliente
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            CdsDirCli.First;

            while not CdsDirCli.Eof do
            begin
                  if CdsDirSer.FindKey([CdsDirCli.FieldByName('NmArqCli').AsString]) then
                  begin
                        CdsDirSer.Edit;
                        CdsDirSer.FieldByName('SnProces').AsString := 'S';
                        CdsDirSer.Post;

                        if (CdsDirSer.FieldByName('TpArquiv').AsString <> 'A') then
                        begin
                              CdsDirCli.Delete;
                        end
                        else if (CdsDirSer.FieldByName('SnObriga').AsString = 'S') or

                                (CdsDirCli.FieldByName('DtArquiv').AsDateTime <
                                 CdsDirSer.FieldByName('DtArquiv').AsDateTime    ) then
                             begin
                                   CdsDirCli.Edit;
                                   CdsDirCli.FieldByName('TpOperac').AsString := 'ATU';
                                   CdsDirCli.FieldByName('SnCompac').AsString :=
                                             CdsDirSer.FieldByName('SnCompac').AsString;
                                   (CdsDirCli.FieldByName('BiArquiv') as
                                       TBlobField).LoadFromFile(
                                              CdsDirSer.FieldByName('NmZipFil').AsString);
                                   CdsDirCli.Post;
                                   CdsDirCli.Next;
                             end
                        else begin
                                   CdsDirCli.Delete;
                             end
                        ;
                  end else
                  begin
                        CdsDirCli.Edit;
                        CdsDirCli.FieldByName('TpOperac').AsString := 'EXC';
                        CdsDirCli.Post;
                        CdsDirCli.Next;
                  end;
            end;

            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            // =-=-= Verificando novos aquivos no servidor
            // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            CdsDirSer.First;

            while not CdsDirSer.Eof do
            begin
                  if CdsDirSer.FieldByName('SnProces').AsString <> 'S' then
                  begin
                        if not CdsDirCli.FindKey([
                                         CdsDirSer.FieldByName('NmArqCli').AsString]) then
                        begin
                              CdsDirCli.Append;
                              CdsDirCli.FieldByName('NmArqCli').AsString :=
                                        CdsDirSer.FieldByName('NmArqCli').AsString;
                              CdsDirCli.FieldByName('TpOperac').AsString := 'NEW';
                              CdsDirCli.FieldByName('SnCompac').AsString :=
                                        CdsDirSer.FieldByName('SnCompac').AsString;
                              CdsDirCli.FieldByName('TpArquiv').AsString :=
                                        CdsDirSer.FieldByName('TpArquiv').AsString;

                              if (CdsDirSer.FieldByName('TpArquiv').AsString = 'A') then
                              begin
                                    (CdsDirCli.FieldByName('BiArquiv') as
                                        TBlobField).LoadFromFile(
                                             CdsDirSer.FieldByName('NmZipFil').AsString);
                              end;

                              CdsDirCli.Post;
                        end;
                  end;

                  CdsDirSer.Next;
            end;

      end;

      Result := CdsDirCli.Data;
end;

procedure TSDmRW.EncontrarInstrucaoSQL(pDsCreden: String;  var pDsResult: String;
                                       pNrQuery:  Integer; var pDsSql: String;
                                       pNmFormul: String);
var lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            try
                CpDados.Close;
                CpDados.CommandText := 'Select * From S032 ' +
                                       'Where NrQuery  =   ' + IntToStr(pNrQuery) +
                                       ' and  NmFormul = ' + #39 + pNmFormul + #39;
                CpDados.Open;
                if not CpDados.Eof then
                begin
                      CpDados.Delete;
                end;
                CpDados.Append;
                CpDados.FieldByName('NrQuery').AsInteger   := pNrQuery;
                CpDados.FieldByName('NmFormul').AsString   := pNmFormul;
                CpDados.FieldByName('DtExecut').AsDateTime := DmDicion.BrvDicionario.DataHoraServer;

                CpDados.Post;
                CpDados.ApplyUpdates(0);
            except
                 on E: Exception do
                       begin
                             pDsResult := UFrmLogos.cDsMsgEr + E.Message;
                       end;
            end;

            if pDsResult = UFrmLogos.cDsMsgOk then
            begin
                  pDsSql := DmDicion.BrvDicionario.EncontrarInstrucaoSQL(pNrQuery, '');
            end;
      end;
end;

procedure TSDmRW.EncontrarInstrucaoSQLConsulta(pDsCreden : String; var pDsResult : String;
                                               pNrQueCon : Integer; var pDsSql : String);
var lCdUsuari : Integer;
begin
      pDsResult := UFrmLogos.cDsMsgOk;

      if not FrmLogos.CredencialValida(pDsCreden, lCdUsuari) then
      begin
            pDsResult := UFrmLogos.cDsMsgEr + 'Credencial inválida';
      end else
      begin
            pDsSql := DmDicion.BrvDicionario.EncontrarInstrucaoSQLConsulta(pNrQueCon);
      end;

end;

function TSDmRW.ConsultaConhecimentos(pDsCreden : String;  var pDsResult : String;
                                      pCdEmpres : String;      pCdRemete : String;
                                      pCdDestin : String;      pCdCarga  : String;
                                      pCdVeicul : String;      pCdMotori : String;
                                      pDtEmiIni : String;      pDtEmiFim : String;
                                      pCdCtrc   : String;      pNrNota   : String;
                                      pNrFatura : String;      pNrRps    : String;
                                      pTpCarga  : String;      pCdTomado : String) : OleVariant;
var lDsComple  : String;
    lDsCarga   : String;
    lCPUltCar  : TClientDataSet;
begin
      lDsCarga   := '';
      lDsComple  := lDsComple + ' and Conhec.CdEmpres in ( ' + pCdEmpres +  ')';


      if pCdCarga <> '0' then
      begin
            lDsComple := lDsComple + ' and MvCtrc.CdCarga  = ' + pCdCarga;
      end;

      if pCdTomado <> '0' then
      begin
            lDsComple := lDsComple + ' and Conhec.CdTomado  = ' + pCdTomado;
      end;

      if pCdRemete <> '0' then
      begin
            lDsComple := lDsComple + ' and Conhec.CdRemete = ' + pCdRemete;
      end;

      if pCdDestin <> '0' then
      begin
            lDsComple := lDsComple + ' and Conhec.CdDestin = ' + pCdDestin;
      end;

      if pCdCtrc <> '0' then
      begin
            lDsComple := lDsComple + ' and Conhec.CdCtrc = ' + pCdCtrc;
      end;

      if pNrFatura <> '0' then
      begin
            lDsComple := lDsComple + ' and Conhec.NrFatura = ' + pNrFatura;
      end;

      if pNrNota <> '0' then
      begin
            lDsComple := lDsComple + ' and Conhec.CdCtrc in (Select F.CdCtrc '                  +
                                                     'From NotaCTRC F '                  +
                                                     'Where F.CdEmpres = Conhec.CdEmpres and' +
                                                     '      F.DsModeNf = Conhec.DsModeNf and' +
                                                     '      F.NrSeriNf = Conhec.NrSeriNf and' +
                                                     '      F.CdCtrc   = Conhec.CdCtrc   and' +
                                                     '      F.NrNota = ' + pNrNota + ')';
      end;

      if (pCdVeicul <> '0') or (pCdMotori <> '0') then
      begin
            if pCdVeicul <> '0' then
            begin
                  lDsComple := lDsComple + ' and (Carga.CdVeicul = ' + pCdVeicul +
                                           ' or   Carga.CdVeicu1 = ' + pCdVeicul +
                                           ' or   Carga.CdVeicu2 = ' + pCdVeicul +
                                           ' or   Carga.CdVeicu3 = ' + pCdVeicul + ')';

                  lDsCarga  := ' and (Carga.CdVeicul = ' + pCdVeicul +
                               ' or   Carga.CdVeicu1 = ' + pCdVeicul +
                               ' or   Carga.CdVeicu2 = ' + pCdVeicul +
                               ' or   Carga.CdVeicu3 = ' + pCdVeicul + ')';
            end;

            if pCdMotori <> '0' then
            begin
                  lDsComple := lDsComple + ' and (Carga.CdMotori = ' + pCdMotori +
                                           ' or   Carga.CdMotor1 = ' + pCdMotori +
                                           ' or   Carga.CdMotor2 = ' + pCdMotori +
                                           ' or   Carga.CdMotor3 = ' + pCdMotori + ')';

                  lDsCarga  := ' and (Carga.CdMotori = ' + pCdMotori +
                               ' or   Carga.CdMotor1 = ' + pCdMotori +
                               ' or   Carga.CdMotor2 = ' + pCdMotori +
                               ' or   Carga.CdMotor3 = ' + pCdMotori + ')';
            end;
      end;

      if pDtEmiIni <> '' then
      begin
            lDsComple := lDsComple + ' and Conhec.DtEmissa >= "' + pDtEmiIni + '"';
      end;

      if pDtEmiFim <> '' then
      begin
            lDsComple := lDsComple + ' and Conhec.DtEmissa <= "' + pDtEmiFim + '"';
      end;

      if pNrRps <> '0' then
      begin
            lDsComple := lDsComple + ' and F004.NrRps = ' + pNrRps;
      end;

      if (pCdCtrc = '0') and (pTpCarga = 'U') then
      begin
            try
                lCPUltCar      := TClientDataSet.Create(Self);
                lCPUltCar.Data := RetornaDadosSqlCadastro(pDsCreden, pDsResult, 186,
                                                         '<%DsComple%>;' + lDsComple, Name);
                if not lCPUltCar.Eof then
                begin
                      lDsComple := lDsComple + ' and MvCtrc.CdCarga = ' +
                                                lCPUltCar.Fields[0].AsString;
                end;

                lCPUltCar.Close;
            finally
                FreeAndNil(lCPUltCar);
            end;
      end;

      Result := RetornaDadosSqlCadastro(pDsCreden, pDsResult, 45,
                                        '<%DsComple%>;' + lDsComple + #13 +
                                        '<%DsCarga%>; ' + lDsCarga, Name);
end;

end.

