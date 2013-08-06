unit UMov0036;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  Grids, BrvDbGrids, BrvDbGrid, StdCtrls, BrvBitBtn, BrvRetCon, BrvCustomEdit, BrvEditNum,
  BrvServerProgress, DB, DBClient, BrvClientDataSet;

type
  TMov0036 = class(TMov0000)
    PnlFiltros: TPanel;
    LblEmpres: TLabel;
    Label4: TLabel;
    EdtCdEmpAtu: TBrvEditNum;
    LblDsEmpAtu: TBrvRetCon;
    EdtCdCarga: TBrvEditNum;
    Panel2: TPanel;
    Panel1: TPanel;
    Label1: TLabel;
    EdtCdEmpDes: TBrvEditNum;
    LblDsEmpDes: TBrvRetCon;
    BtnCancelar: TBrvBitBtn;
    BtnLiberar: TBrvBitBtn;
    DbgDados: TBrvDbGrid;
    BtnPesquisa: TBrvBitBtn;
    CpT002: TBrvClientDataSet;
    DsT002: TDataSource;
    BrvServerProgress: TBrvServerProgress;
    BrvListParam: TBrvListParam;
    LblDsDesCar: TBrvRetCon;
    CcParams: TClientDataSet;
    CcParamsXmlQ004: TMemoField;
    CcParamsNmForm: TStringField;
    popMarcar: TPopupMenu;
    MarcarTodos1: TMenuItem;
    DesmarcarTodos1: TMenuItem;
    CcParamsCdEmpDes: TIntegerField;
    LblQtReg: TLabel;
    PnlResult: TPanel;
    procedure BtnPesquisaClick(Sender: TObject);
    procedure EdtCdEmpAtuBrOnBeforeConsulta(Sender: TObject);
    procedure EdtCdEmpDesBrOnBeforeConsulta(Sender: TObject);
    procedure BtnLiberarClick(Sender: TObject);
    procedure MarcarTodos1Click(Sender: TObject);
    procedure DesmarcarTodos1Click(Sender: TObject);
    procedure BtnCancelarClick(Sender: TObject);
  private
    procedure LimparCampos;
    procedure ValidarEntradaDadosFiltros;
  public
    { Public declarations }
  end;

var
  Mov0036: TMov0036;

implementation

uses UClaSrv, UDmSrvApl;

{$R *.dfm}

procedure TMov0036.BtnCancelarClick(Sender: TObject);
begin
      inherited;
      if MessageDlg('Deseja realmente cancelar?', mtConfirmation, [mbYes, mbNo], 0) = mrYes then
      begin
            LimparCampos;
      end;
end;

procedure TMov0036.BtnLiberarClick(Sender: TObject);
var lSrvAdm   : TSDmAdmClient;
    lDsResult : String;
    lCpDados  : TBrvClientDataSet;
    lCpT002   : TBrvClientDataSet;
begin
      inherited;
      try
          lCpDados              := TBrvClientDataSet.Create(nil);
          lCpDados.BrDicionario := DmSrvApl.BrvDicionario;
          lCpDados.FieldDefs.Clear;
          lCpDados.FieldDefs.Add('CdEmpres', ftInteger, 0);
          lCpDados.FieldDefs.Add('DsModeNF', ftString,  3);
          lCpDados.FieldDefs.Add('NrSeriNF', ftString,  6);
          lCpDados.FieldDefs.Add('CdCTRC',   ftInteger, 0);
          lCpDados.FieldDefs.Add('CdCarga',  ftInteger, 0);
          lCpDados.FieldDefs.Add('CdEmpAtu', ftInteger, 0);
          lCpDados.CreateDataSet;

          lCpT002              := TBrvClientDataSet.Create(nil);
          lCpT002.BrDicionario := DmSrvApl.BrvDicionario;
          lCpT002.Data         := CpT002.Data;

          lCpT002.Filtered := False;
          lCpT002.Filter   := 'SnMarcad = ''S''';
          lCpT002.Filtered := True;
          lCpT002.First;
          while not lCpT002.Eof do
          begin
                lCpDados.Append;
                lCpDados.FieldByName('CdEmpres').AsInteger :=
                                                          lCpT002.FieldByName('CdEmpres').AsInteger;
                lCpDados.FieldByName('DsModeNF').AsString  :=
                                                          lCpT002.FieldByName('DsModeNF').AsString;
                lCpDados.FieldByName('NrSeriNF').AsString  :=
                                                          lCpT002.FieldByName('NrSeriNF').AsString;
                lCpDados.FieldByName('CdCTRC'  ).AsInteger :=
                                                          lCpT002.FieldByName('CdCTRC'  ).AsInteger;
                lCpDados.FieldByName('CdCarga' ).AsInteger :=
                                                          lCpT002.FieldByName('CdCarga' ).AsInteger;
                lCpDados.FieldByName('CdEmpAtu').AsInteger :=
                                                          lCpT002.FieldByName('CdEmpAtu').AsInteger;
                lCpDados.Post;
                lCpT002.Next;
          end;

          if (lCpDados.RecordCount > 0) then
          begin
                if (EdtCdEmpDes.BrAsInteger = 0) then
                begin
                      EdtCdEmpDes.SetFocus;
                      raise Exception.Create('É obrigatório informar uma Empresa Destino!');
                end;
                try
                    if (CcParams.Active) then
                    begin
                          CcParams.EmptyDataSet;
                          CcParams.Close;
                    end;

                    CcParams.CreateDataSet;
                    CcParams.Append;
                    CcParams.FieldByName('XmlT002' ).AsString  := lCpDados.XMLData;
                    CcParams.FieldByName('NmForm'  ).AsString  := Self.Name;
                    CcParams.FieldByName('CdEmpDes').AsInteger := EdtCdEmpDes.BrAsInteger;
                    CcParams.Post;

                    BrvServerProgress.Start(Self.Caption, 'Gravando informações', 100, 10);
                    lSrvAdm   := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
                    lDsResult := lSrvAdm.LiberarDesmontarCarga(DmSrvApl.BrvDicionario.Credencial,
                                                                                     CcParams.Data);
                finally
                    FreeAndNil(lSrvAdm);
                    BrvServerProgress.Stop;
                end;
                DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
                MessageDlg('Liberado com sucesso!!!', mtInformation, [mbok], 0);
                LimparCampos;
          end else
          begin
                MessageDlg('Não há registros marcados!', mtInformation, [mbok], 0);
          end;
      finally
          FreeAndNil(lCpDados);
          FreeAndNil(lCpT002);
      end;
end;

procedure TMov0036.ValidarEntradaDadosFiltros;
begin
      if (EdtCdEmpAtu.BrAsInteger = 0) then
      begin
            EdtCdEmpAtu.SetFocus;
            raise Exception.Create('É obrigatório informar uma Empresa Atual!');
      end;

      if (EdtCdCarga.BrAsInteger = 0) then
      begin
            EdtCdCarga.SetFocus;
            raise Exception.Create('É obrigatório informar uma Carga!');
      end;
end;

procedure TMov0036.BtnPesquisaClick(Sender: TObject);
begin
      inherited;
      try
          ValidarEntradaDadosFiltros;
          BrvListParam.Clear;
          BrvListParam.Add('CdEmpAtu', '', '', EdtCdEmpAtu.Text);
          BrvListParam.Add('CdCarga',  '', '', EdtCdCarga.Text);

          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);

          CpT002.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(269, BrvListParam.AsBrParam,
                                                                                         Self.Name);
      finally
          BrvServerProgress.Stop;
      end;

      LblQtReg.Caption := FormatFloat('0', CpT002.RecordCount) + ' Registro(s)';
      if (CpT002.RecordCount > 0) then
      begin
            BtnPesquisa.Enabled         := False;
            EdtCdEmpAtu.Color           := $00DDE2E3;
            EdtCdEmpAtu.BrVisibleButton := False;
            EdtCdCarga.Color            := $00DDE2E3;
            EdtCdCarga.BrVisibleButton  := False;
            PnlResult.Visible := True;
            CpT002.First;
      end else
      begin
            MessageDlg('Nenhum registro encontrado!!!', mtInformation, [mbok], 0);
      end;
end;

procedure TMov0036.DesmarcarTodos1Click(Sender: TObject);
begin
      inherited;
      DbgDados.SetarTodasColunas('SnMarcad', 'N');
end;

procedure TMov0036.EdtCdEmpAtuBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdEmpAtu.BrParams.Clear;
      EdtCdEmpAtu.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpAtu.BrDicionario.CorpCommaCodes);
end;

procedure TMov0036.EdtCdEmpDesBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdEmpDes.BrParams.Clear;
      EdtCdEmpDes.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpDes.BrDicionario.CorpCommaCodes);
end;

procedure TMov0036.MarcarTodos1Click(Sender: TObject);
begin
      inherited;
      DbgDados.SetarTodasColunas('SnMarcad', 'S');
end;

procedure TMov0036.LimparCampos;
begin
      if (CpT002.Active) then
      begin
            CpT002.EmptyDataSet;
            CpT002.Close;
      end;
      LblQtReg.Caption            := '';
      PnlResult.Visible           := False;
      BtnPesquisa.Enabled         := True;
      EdtCdEmpAtu.BrAsInteger     := 0;
      EdtCdEmpAtu.BrValidarEntrada;
      EdtCdEmpAtu.Color           := clWhite;
      EdtCdEmpAtu.BrVisibleButton := True;
      EdtCdCarga.BrAsInteger      := 0;
      EdtCdCarga.BrValidarEntrada;
      EdtCdCarga.Color            := clWhite;
      EdtCdCarga.BrVisibleButton  := True;
      EdtCdEmpAtu.SetFocus;
end;

initialization
      RegisterClass(TMov0036);

finalization
      UnRegisterClass(TMov0036);

end.
