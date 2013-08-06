unit URel0005;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, URel0000, BrvGeraRelatorio, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  BrvRetCon, StdCtrls, BrvEditNum, Mask, BrvMesAno, DB, DBClient, CheckLst,
  BrvCheckListBox, BrvEditDate, BrvClientDataSet, ComCtrls, UClaSrv, BrvExport, BrvServerProgress,
  BrvListParam, ImgList, Menus, BrvCustomMaskEdit, BrvCustomEdit, BrvBitBtn;

type
  TRel0005 = class(TREL0000)
    TblTemp: TClientDataSet;
    TblTempNrPagIni: TIntegerField;
    CdsPlaCon: TBrvClientDataSet;
    Panel2: TPanel;
    TrvPlano: TTreeView;
    Splitter1: TSplitter;
    Panel1: TPanel;
    GbxEmpres: TGroupBox;
    ClbEmpres: TBrvCheckListBox;
    EdtCdEmpres: TBrvEditNum;
    EdtDsEmpres: TBrvRetCon;
    LblNrPlano: TBrvRetCon;
    BrvExport: TBrvExport;
    TblTempDtMesAno: TStringField;
    TblTempDsPlano: TMemoField;
    Panel3: TPanel;
    Label7: TLabel;
    Label8: TLabel;
    EdtNrPagIni: TBrvEditNum;
    EdtDtMesAno: TBrvMesAno;
    RgpNivel: TRadioGroup;
    BrvServerProgress: TBrvServerProgress;
    BtnAbrCon: TBrvBitBtn;
    BtnFecCon: TBrvBitBtn;
    BtnProces: TBrvBitBtn;
    procedure BtnAbrConClick(Sender: TObject);
    procedure BtnFecConClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BtnProcesClick(Sender: TObject);
    procedure BtnGeraRelClick(Sender: TObject);
  private
    { Private declarations }
    gCdTodEmp  : String;
    procedure EmpresasSelecionadas(var pCdPriEmp, pCdTodEmp: String);
  public
    { Public declarations }
  end;

var
  Rel0005: TRel0005;

implementation

uses UDmCtb, UDmSrvApl;

{$R *.dfm}

procedure TRel0005.FormCreate(Sender: TObject);
begin
      inherited;
      ClbEmpres.Items.CommaText  := DmSrvApl.BrvDicionario.CorpCommaCodesNames;
      ClbEmpres.Values.CommaText := DmSrvApl.BrvDicionario.CorpCommaCodes;
end;

procedure TRel0005.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TRel0005.BtnAbrConClick(Sender: TObject);
var lCdPriEmp : String;
    lQtNivel  : Integer;
    lDsMascar : String;
    lNrPosPon : Integer;

    lDtMes    : Integer;
    lDtAno    : Integer;
begin
      NavBarra.SetFocus;

      EmpresasSelecionadas(lCdPriEmp, gCdTodEmp);

      if lCdPriEmp = '' then
      begin
            raise Exception.Create('Nenhuma empresa foi selecionada');
      end;

      // Considerar máscara/plano de contas da primeira empresa selecionada
      EdtCdEmpres.Text := lCdPriEmp;
      EdtCdEmpres.BrValidarEntrada;

      CdsPlaCon.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(77,
                                                  '<%NrPlano%>;' + LblNrPlano.Text, Name);

      lDsMascar             := CdsPlaCon.FieldByName('DsMasCla').AsString + ';1; ';

      //=-=-=-=-= Encontrando Número de Níveis da Máscara
      RgpNivel.Items.Clear;
      lQtNivel    :=  0;

      while lDsMascar <> '' do
      begin
            lNrPosPon  :=  Pos('.', lDsMascar);

            if  lNrPosPon <> 0 then
            begin
                  Delete(lDsMascar, 1, lNrPosPon);
            end else
            begin
                  lDsMascar  := '';
            end;
            Inc(lQtNivel);
            RgpNivel.Items.Add(IntToStr(lQtNivel) + '          ');
      end;

      RgpNivel.Items.Add('CC');

      RgpNivel.ItemIndex := 0;
      //=-=-=-=-= Encontrando primeiro período contábil aberto
      DmCtb.PesquisarPeriodoContabil(EdtCdEmpres.BrAsInteger, 'A', lDtAno, lDtMes);
      EdtDtMesAno.AsMes := lDtMes;
      EdtDtMesAno.AsAno := lDtAno;
      // =-=-=-=-=

      ClbEmpres.Enabled       := False;
      BtnAbrCon.Enabled       := False;
      BtnGeraRel.Enabled      := False;
      BtnProces.Enabled       := True;
      BtnFecCon.Enabled       := True;
      PnlFundo.Visible        := True;
end;

procedure TRel0005.EmpresasSelecionadas(var pCdPriEmp : String; var pCdTodEmp : String);
var lNrLinha : Integer;
begin
      pCdPriEmp := '';
      pCdTodEmp := '';

      for lNrLinha := 0 to ClbEmpres.Items.Count -1 do
      begin
            if ClbEmpres.Checked[lNrLinha] then
            begin
                  if pCdPriEmp = '' then
                  begin
                        pCdPriEmp := ClbEmpres.Values.Strings[lNrLinha];
                  end;

                  if pCdTodEmp <> '' then
                  begin
                        pCdTodEmp := pCdTodEmp + ', ';
                  end;

                  pCdTodEmp := pCdTodEmp + ClbEmpres.Values.Strings[lNrLinha];
            end;
      end;
end;

procedure TRel0005.BtnFecConClick(Sender: TObject);
begin
      ClbEmpres.Enabled  := True;
      BtnAbrCon.Enabled  := True;
      BtnProces.Enabled  := False;
      BtnGeraRel.Enabled := False;
      BtnFecCon.Enabled  := False;
      PnlFundo.Visible   := False;
      TrvPlano.Visible   := False;
end;

procedure TRel0005.BtnGeraRelClick(Sender: TObject);
begin
      inherited;

      if EdtNrPagIni.BrAsInteger  <= 0 then
      begin
            raise Exception.Create('Página inicial inválida');
      end;

      // Guardando parâmetros no DataSet auxiliar

      if not TblTemp.Active then
      begin
            TblTemp.CreateDataSet;
      end else
      begin
            TblTemp.EmptyDataSet;
      end;

      TblTemp.Append;
      TblTemp.FieldByName('NrPagIni').AsInteger := EdtNrPagIni.BrAsInteger;
      TblTemp.FieldByName('DtMesAno').AsString  := EdtDtMesAno.Text;
      TblTemp.FieldByName('DsPlano').AsString   := BrvExport.ExportarTreeView(TrvPlano);
      TblTemp.Post;

      // =-=-=-=-=-=-=-=

      BrvGerRel.IniciarRelatorio('RLC0008', 'C', Name);
      BrvGerRel.ImprimirRelatorio;
end;

procedure TRel0005.BtnProcesClick(Sender: TObject);
var lConexao     : TSDmConClient;
    lDsResult    : String;
    lDsPlano     : String;
    lQtNivel     : Integer;

    lDtInicio    : TDate;
    lDtFinal     : TDate;
begin
      if not EdtDtMesAno.DataValida then
      begin
            raise Exception.Create('Período está inválido');
      end;

      lQtNivel := StrToInt(Trim(RgpNivel.Items.Strings[RgpNivel.ItemIndex]));

      lConexao := TSDmConClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          // =-=-=-= Criarndo Provider no servidor de aplicação

          try
              BrvServerProgress.Start(Self.Caption, 'Gerando Balencete...', 10, 100);

              lDtInicio := EncodeDate(EdtDtMesAno.AsAno, EdtDtMesAno.AsMes, 1);
              lDtFinal  := EncodeDate(EdtDtMesAno.AsAno, EdtDtMesAno.AsMes,
                                      EdtDtMesAno.AsDiaFinal);

              lDsPlano := lConexao.Balancete(DmSrvApl.BrvDicionario.Credencial, lDsResult,
                                             StrToInt(LblNrPlano.Text), 1, lQtNivel,
                                             gCdTodEmp, lDtInicio, lDtFinal);
          finally
              BrvServerProgress.Stop;
          end;

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
          BrvExport.XMLToTreeView(lDsPlano, TrvPlano);
          //BrvExport.MontarTreeView(lDsPlano, TrvPlano);
      finally
          FreeAndNil(lConexao);
      end;

      TrvPlano.Visible   := True;
      BtnGeraRel.Enabled  := True;
end;

initialization
      RegisterClass(TRel0005);

finalization
      UnRegisterClass(TRel0005);
end.
