unit UMov0037;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  BrvEdit, BrvDbGrids, BrvDbGrid, Grids, BrvCustomEdit, BrvEditNum, StdCtrls, BrvRetCon, Mask,
  BrvCustomMaskEdit, BrvEditDate, BrvBitBtn, ComCtrls, DB, DBClient, BrvClientDataSet,
  BrvServerProgress;

type
  TMov0037 = class(TMov0000)
    PgcFundo: TPageControl;
    TbsFiltro: TTabSheet;
    Panel1: TPanel;
    Panel4: TPanel;
    BtnPesquisar: TBrvBitBtn;
    Panel7: TPanel;
    Label1: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    EdtDtFinal: TBrvEditDate;
    EdtDtInicia: TBrvEditDate;
    LblNmArmaze: TBrvRetCon;
    EdtCdArmaze: TBrvEditNum;
    TbsConsulta: TTabSheet;
    Panel5: TPanel;
    Splitter1: TSplitter;
    Panel6: TPanel;
    BtnGravar: TBrvBitBtn;
    BtnVoltar: TBrvBitBtn;
    Panel8: TPanel;
    StgFiltros: TStringGrid;
    Panel2: TPanel;
    DbgOrdens: TBrvDbGrid;
    Label2: TLabel;
    BrvServerProgress: TBrvServerProgress;
    BrvListParam: TBrvListParam;
    CpW005: TBrvClientDataSet;
    DsW005: TDataSource;
    CcParams: TClientDataSet;
    CcParamsNmForm: TStringField;
    CcParamsXmlW005: TMemoField;
    EdtDiretori: TBrvEdit;
    procedure EdtCdArmazeBrOnBeforeConsulta(Sender: TObject);
    procedure BtnPesquisarClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);
    procedure BtnGravarClick(Sender: TObject);
    procedure ValidarEntradaDados;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Mov0037   : TMov0037;
  gNmDirArq : String;

implementation

uses UClaSrv, UDmSrvApl, BrvFuncoesXE;

{$R *.dfm}

procedure TMov0037.BtnGravarClick(Sender: TObject);
var lDsArquiv : TStrings;
    lSrvAdm   : TSDmAdmClient;
    lDsResult : String;
begin
      inherited;
      if MessageDlg('Deseja realmente gravar?', mtConfirmation, [mbYes, mbNo], 0) = mrYes then
      begin
            if (CcParams.Active) then
            begin
                  CcParams.EmptyDataSet;
                  CcParams.Close;
            end;

            CcParams.CreateDataSet;
            CcParams.Append;
            CcParams.FieldByName('XmlW005').AsString := CpW005.XMLData;
            CcParams.FieldByName('NmForm' ).AsString := Self.Name;
            CcParams.Post;

            try
                BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
                lSrvAdm   := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
                lDsResult := lSrvAdm.AtualizarOperacaoArmazem(DmSrvApl.BrvDicionario.Credencial,
                                                                                     CcParams.Data);
            finally
                BrvServerProgress.Stop;
                FreeAndNil(lSrvAdm);
                DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
            end;

            try
                lDsArquiv       := TStringList.Create;
                lDsArquiv.Text  := BrvFuncoesXE.ClientDataSetToStr(CpW005);
                lDsArquiv.Delete(0);
                lDsArquiv.SaveToFile(gNmDirArq);
            finally
                FreeAndNil(lDsArquiv);
            end;

            MessageDlg('Gravado com sucesso!!!', mtInformation, [mbok], 0);
            PgcFundo.ActivePage := TbsFiltro;
      end;
end;

procedure TMov0037.ValidarEntradaDados;
begin
      BrvListParam.Clear;

      if EdtCdArmaze.BrAsInteger > 0 then
      begin
            BrvListParam.Add('CdArmaze', 'Armazém', EdtCdArmaze.Text, EdtCdArmaze.Text);
      end else
      begin
            EdtCdArmaze.SetFocus;
            raise Exception.Create('É obrigatório informar um Armazém!');
      end;

      if (EdtDtInicia.BrDataVazia) then
      begin
            EdtDtInicia.SetFocus;
            raise Exception.Create('Informe uma data inicial para a consulta!');
      end else
      begin
            if (not EdtDtInicia.BrDataValida) then
            begin
                  EdtDtInicia.SetFocus;
                  raise Exception.Create('Verifique a data inicial informada!');
            end else
            begin
                  if (EdtDtFinal.BrDataVazia) then
                  begin
                        EdtDtFinal.SetFocus;
                        raise Exception.Create('Informe uma data final para a consulta!');
                  end else
                  begin
                        if (not EdtDtFinal.BrDataValida) then
                        begin
                              EdtDtFinal.SetFocus;
                              raise Exception.Create('Verifique a data final informada!');
                        end else
                        begin
                              if (EdtDtFinal.BrAsDate < EdtDtInicia.BrAsDate) then
                              begin
                                    EdtDtFinal.SetFocus;
                                    raise Exception.Create('Data final deve ser maior ou igual'+
                                                                            ' à data inicial!');
                              end else
                              begin
                                    BrvListParam.Add('DtInicia', 'Data Inicial',
                                                            EdtDtInicia.Text, EdtDtInicia.Text);

                                    BrvListParam.Add('DtFinal', 'Data Final',
                                                              EdtDtFinal.Text, EdtDtFinal.Text);
                              end;
                        end;
                  end;
            end;
      end;

      if EdtDiretori.Text = '' then
      begin
            EdtDiretori.SetFocus;
            raise Exception.Create('É obrigatório informar um Diretório!');
      end;
end;

procedure TMov0037.BtnPesquisarClick(Sender: TObject);
var lNmArquiv : String;
begin
      inherited;
      try
          ValidarEntradaDados;

          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);

          CpW005.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(271, BrvListParam.AsBrParam,
                                                                                         Self.Name);
      finally
          BrvServerProgress.Stop;
      end;
      if (CpW005.RecordCount > 0) then
      begin
            lNmArquiv := 'endere';
            CpW005.First;
            lNmArquiv := lNmArquiv + CpW005.FieldByName('NrOrdem').AsString + '-';
            CpW005.Last;
            lNmArquiv := lNmArquiv + CpW005.FieldByName('NrOrdem').AsString + '.txt';

            if (copy(EdtDiretori.Text, length(EdtDiretori.Text), 1) <> '\') then
            begin
                  lNmArquiv := '\' + lNmArquiv;
            end;

            gNmDirArq := EdtDiretori.Text + lNmArquiv;
            BrvListParam.Add('', 'Diretório', gNmDirArq, '');
            BrvListParam.SetStgParam(StgFiltros);

            CpW005.First;
            PgcFundo.ActivePage := TbsConsulta;
      end else
      begin
            MessageDlg('Nenhum registro encontrado!!!', mtInformation, [mbok], 0);
      end;
end;

procedure TMov0037.BtnVoltarClick(Sender: TObject);
begin
      inherited;
      PgcFundo.ActivePage := TbsFiltro;
end;

procedure TMov0037.EdtCdArmazeBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdArmaze.BrParams.Clear;
      EdtCdArmaze.BrParams.Add('<%CdEmpres%>;' + EdtCdArmaze.BrDicionario.CorpCommaCodes);
end;

procedure TMov0037.FormCreate(Sender: TObject);
begin
      inherited;
      TbsFiltro.TabVisible    := False;
      TbsConsulta.TabVisible  := False;
      PgcFundo.ActivePage     := TbsFiltro;
      StgFiltros.ColWidths[0] := 75;
      StgFiltros.ColWidths[1] := 600;
end;

initialization
      RegisterClass(TMov0037);

finalization
      UnRegisterClass(TMov0037);

end.
