unit UCai0009;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCai0000, StdCtrls, Buttons, BrvBitBtn, ExtCtrls, DB, DBClient,
  BrvClientDataSet, Grids, BrvDbGrids, BrvDbGrid, UClaSrv, Menus;

type
  TCai0009 = class(TCai0000)
    Panel3: TPanel;
    Panel5: TPanel;
    Splitter1: TSplitter;
    Panel4: TPanel;
    Panel6: TPanel;
    QpGruEmp: TBrvClientDataSet;
    DbgGrupo: TBrvDbGrid;
    DpGruEmp: TDataSource;
    DbgEmpres: TBrvDbGrid;
    QpUsrEmp: TBrvClientDataSet;
    DpUsrEmp: TDataSource;
    procedure BbtOkClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure QpGruEmpBeforeInsert(DataSet: TDataSet);
    procedure QpGruEmpAfterPost(DataSet: TDataSet);
    procedure FormShow(Sender: TObject);
  private
    procedure MostrarEmpresasDoGrupo;
    procedure AtualizaEmpresasSelecionadas;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Cai0009: TCai0009;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TCai0009.BbtOkClick(Sender: TObject);
begin
      QpUsrEmp.DisableControls;
      QpUsrEmp.First;
      DmSrvApl.BrvDicionario.CorpCodes.Clear;
      DmSrvApl.BrvDicionario.CorpNames.Clear;

      while not QpUsrEmp.Eof do
      begin
            if QpUsrEmp.FieldByName('SnEmpIni').AsString = 'S' then
            begin
                  DmSrvApl.BrvDicionario.CorpCodes.Add(
                                         QpUsrEmp.FieldByName('CdEmpres').AsString);
                  DmSrvApl.BrvDicionario.CorpNames.Add(
                                         QpUsrEmp.FieldByName('DsEmpres').AsString);
            end;

            QpUsrEmp.Next;
      end;

      QpUsrEmp.First;
      QpUsrEmp.EnableControls;

      DmSrvApl.BrvDicionario.CorpCommaCodes := DmSrvApl.BrvDicionario.CorpCodes.CommaText;
      DmSrvApl.BrvDicionario.CorpName       := DmSrvApl.BrvDicionario.CorpNames.CommaText;

      if DmSrvApl.BrvDicionario.CorpCodes.Count <= 0 then
      begin
            raise Exception.Create('Nenhuma empresa foi selecionada.');
      end;

      AtualizaEmpresasSelecionadas;

      ModalResult := mrOk;
end;

procedure TCai0009.AtualizaEmpresasSelecionadas;
var lConexao     : TSDmSisClient;
    lDsResult    : String;
begin
      lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-=-= Criarndo Provider no servidor de aplicação
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          lConexao.AtualizaEmpresasSelecionadasUsuario(
                                   DmSrvApl.BrvDicionario.Credencial, lDsResult,
                                   DmSrvApl.BrvDicionario.CorpCodes.CommaText,
                                   DmSrvApl.BrvDicionario.UserCode);

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCai0009.FormCreate(Sender: TObject);
begin
      inherited;

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-= Carregando os grupos em que o usuário tem permissão
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      QpGruEmp.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(25,
                              '<%CdUsuari%>;' + IntToStr(DmSrvApl.BrvDicionario.UserCode),
                              Name);
      QpGruEmp.DisableControls;
      QpGruEmp.Tag := 1;
      QpGruEmp.First;
      while not QpGruEmp.Eof do
      begin
            if QpGruEmp.FieldByName('QtEmpSel').AsInteger > 0 then
            begin
                  QpGruEmp.Edit;
                  QpGruEmp.FieldByName('SnMarcad').AsString := 'S';
                  QpGruEmp.Post;
            end;

            QpGruEmp.Next;
      end;

      QpGruEmp.First;
      QpGruEmp.Tag := 0;
      QpGruEmp.EnableControls;

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-= Carregando as empresas em que o usuário tem permissão
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      QpUsrEmp.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(26,
                              '<%CdUsuari%>;' + IntToStr(DmSrvApl.BrvDicionario.UserCode),
                              Name);

      MostrarEmpresasDoGrupo;

end;

procedure TCai0009.FormShow(Sender: TObject);
begin
     inherited;
     BringToFront;
end;

procedure TCai0009.QpGruEmpAfterPost(DataSet: TDataSet);
begin
      if DataSet.Tag = 0 then
      begin
            MostrarEmpresasDoGrupo;
      end;
end;

procedure TCai0009.MostrarEmpresasDoGrupo;
var lDsFilEmp : String; // Filtro para mostrar empresas do usuário
begin
      lDsFilEmp     := 'CdGruEmp = 0';

      QpGruEmp.DisableControls;
      QpGruEmp.First;
      while not QpGruEmp.Eof do
      begin
            if QpGruEmp.FieldByName('SnMarcad').AsString = 'S' then
            begin
                  lDsFilEmp := lDsFilEmp + ' or CdGruEmp = ' +
                                           QpGruEmp.FieldByName('CdGruEmp').AsString;
            end;

            QpGruEmp.Next;
      end;

      QpGruEmp.First;
      QpGruEmp.EnableControls;

      QpUsrEmp.Filter := lDsFilEmp;
      QpUsrEmp.First;
end;

procedure TCai0009.QpGruEmpBeforeInsert(DataSet: TDataSet);
begin
      Abort;
end;

end.
