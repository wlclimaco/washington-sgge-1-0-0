unit URel0006;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, URel0000, BrvGeraRelatorio, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, Grids,
  BrvDbGrids, BrvDbGrid, StdCtrls, DB, DBClient, BrvClientDataSet, BrvBitBtn, BrvRetCon, BrvEditNum,
  BrvString, Mask, BrvEditDate, BrvCustomMaskEdit, BrvCustomEdit, BrvListParam, ImgList, Menus;

type
  TRel0006 = class(TREL0000)
    PnlFiltro: TPanel;
    Panel1: TPanel;
    BrvDbGrid1: TBrvDbGrid;
    LblEmpres: TLabel;
    EdtCdEmpres: TBrvEditNum;
    LblDsEmpres: TBrvRetCon;
    BtnPesqui: TBrvBitBtn;
    CPCarga: TBrvClientDataSet;
    RdgTpImpres: TRadioGroup;
    DsPCarga: TDataSource;
    BrvString: TBrvString;
    EdtDtCarga: TBrvEditDate;
    Label1: TLabel;
    procedure BtnPesquiClick(Sender: TObject);
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BtnGeraRelClick(Sender: TObject);
  private
    { Private declarations }
    procedure AtualizaSituacoCarga(pCdCarga : AnsiString);
    procedure ValidarEntradas;
  public
    { Public declarations }
  end;

var
  Rel0006: TRel0006;

implementation

uses UDmSrvApl, UClaSrv;

{$R *.dfm}

procedure TRel0006.BtnGeraRelClick(Sender: TObject);
var lDsParams : AnsiString;
    lBrGerRel : TBrvGeraRelatorio;
begin
      inherited;
      try
          if (CPCarga.Active) and (CPCarga.RecordCount > 0) then
          begin
                lBrGerRel                  := TBrvGeraRelatorio.Create(nil);
                lBrGerRel.BrDicionario     := DmSrvApl.BrvDicionario;
                lBrGerRel.SQLConnectionImp := DmSrvApl.SrvImpres;
                lBrGerRel.IniciarRelatorio('QRL0005','G', 'QRL0005');
                lBrGerRel.InserirParametroSQL('CdEmpres', EdtCdEmpres.Text);
                lBrGerRel.InserirParametroSQL('CdCarga' , CPCarga.FieldByName('CdCarga').AsString);
                lBrGerRel.ImprimirRelatorio;

                AtualizaSituacoCarga(CPCarga.FieldByName('CdCarga').AsString);
         end;
      finally
           FreeAndNil(lBrGerRel);
      end;
end;

procedure TRel0006.BtnPesquiClick(Sender: TObject);
var lDsParams : AnsiString;
begin
      inherited;

      ValidarEntradas;

      lDsParams := '<%CdEmpres%>;' + EdtCdEmpres.Text + #13;

      if RdgTpImpres.ItemIndex = 0 then
      begin
            lDsParams := lDsParams + '<%SnImpres%>;N';
      end else
      begin
            lDsParams := lDsParams + '<%SnImpres%>;S';
      end;

      if not EdtDtCarga.BrDataVazia then
      begin
            lDsParams := lDsParams + #13 +
                         '<%DsComWhe%>; and Coalesce(T003.DtCarga, "' +
                         EdtDtCarga.BrAsDataSQL + '") = "' + EdtDtCarga.BrAsDataSQL +
                         '"';
      end else
      begin
            lDsParams := lDsParams + #13 + '<%DsComWhe%>;';
      end;

      if not EdtDtCarga.BrDataVazia then
      begin
            lDsParams := lDsParams + #13 + '<%DsComWhe%>;' + EdtDtCarga.BrAsDataSQL;
      end;

      CPCarga.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(143, lDsParams, Name);
end;

procedure TRel0006.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      TBrvEditNum(Sender).BrParams.Clear;
      TBrvEditNum(Sender).BrParams.Add('<%CdEmpres%>;' +
                                                 EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TRel0006.ValidarEntradas;
begin
      if EdtCdEmpres.BrAsInteger = 0 then
      begin
            Raise Exception.Create('Informe a empresa');
      end;

      if (RdgTpImpres.ItemIndex = 1) and (EdtDtCarga.BrDataVazia) then
      begin
            Raise Exception.Create('A data da carga é obrigatório para reimpressão');
      end;
end;

procedure TRel0006.AtualizaSituacoCarga(pCdCarga : AnsiString);
var lDsSql    : AnsiString;
    lTxParams : TStrings;
    lDsResult : String;
    lConexao  : TSDmRWClient;
begin
      try
          lTxParams := TStringList.Create;
          lTxParams.Clear;
          lTxParams.Add('<%CdCarga%>;' + pCdCarga);

          lDsSql   := DmSrvApl.BrvDicionario.EncontrarInstrucaoSQL(145, Name);
          lDsSql   := DmSrvApl.BrvDicionario.SubstituirParametrosSQL(lDsSql, lTxParams);
          lConexao := TSDmRWClient.Create(DmSrvApl.SrvAplica.DBXConnection);
          lConexao.ExecutarInstrucaoSql(DmSrvApl.BrvDicionario.Credencial, lDsResult, lDsSql);
      finally
          FreeAndNil(lConexao);
          FreeAndNil(lTxParams);
      end;
end;

initialization
      RegisterClass(TRel0006);

finalization
      UnRegisterClass(TRel0006);

end.
