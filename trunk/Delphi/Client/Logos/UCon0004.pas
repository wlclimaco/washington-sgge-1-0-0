unit UCon0004;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, Mask, BrvEditDate, StdCtrls,
  BrvBitBtn, BrvRetCon, BrvEditNum, DB, DBClient, BrvClientDataSet, Grids, BrvDbGrids, BrvDbGrid,
  ComCtrls, BrvImgBot, Menus, BrvListParam, ImgList, BrvCustomMaskEdit, BrvCustomEdit;

type
  TCon0004 = class(TMov0000)
    pnlMestre: TPanel;
    Label2: TLabel;
    Label1: TLabel;
    Label11: TLabel;
    Label4: TLabel;
    Label6: TLabel;
    Label3: TLabel;
    Label8: TLabel;
    Label9: TLabel;
    Label7: TLabel;
    EdtCdEmpres: TBrvEditNum;
    EdtDsEmpres: TBrvRetCon;
    EdtCdCtrc: TBrvEditNum;
    EdtRsRemete: TBrvRetCon;
    EdtCdDestin: TBrvEditNum;
    EdtRsDestin: TBrvRetCon;
    EdtCdCarga: TBrvEditNum;
    EdtDsDescar: TBrvRetCon;
    EdtNrNotFis: TBrvEditNum;
    EdtNmMotori: TBrvRetCon;
    EdtCdMotori: TBrvEditNum;
    EdtDtInicio: TBrvEditDate;
    EdtDtFinal: TBrvEditDate;
    EdtNrFatura: TBrvEditNum;
    Label10: TLabel;
    EdtCdVeicul: TBrvEditNum;
    EdtDsVeicul: TBrvRetCon;
    BdgRegistros: TBrvDbGrid;
    DsT011: TDataSource;
    CPT011: TBrvClientDataSet;
    BtnLocali: TBrvBitBtn;
    EdtCdRemete: TBrvEditNum;
    Label5: TLabel;
    StbDetalhes: TStatusBar;
    EdtNrRps: TBrvEditNum;
    Label12: TLabel;
    PopDetCTRC: TPopupMenu;
    Detalhar1: TMenuItem;
    RdgCarga: TRadioGroup;
    Label13: TLabel;
    EdtCdTitula: TBrvEditNum;
    LblCdTitula: TLabel;
    LblRsTitula: TBrvRetCon;
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure EdtCdCargaBrOnBeforeConsulta(Sender: TObject);
    procedure BrvBitBtn1Click(Sender: TObject);
    procedure FormResize(Sender: TObject);
    procedure BdgRegistrosDblClick(Sender: TObject);
    procedure Detalhar1Click(Sender: TObject);
    procedure EdtCdVeiculChange(Sender: TObject);
  private
    procedure AjustaStatusBar;
    procedure Totaliza;
    procedure RecuperaListaConhecimentos;
    procedure RecuperaDominiosCampos;
    procedure DetalharCTRC;
    { Private declarations }
  public
    { Public declarations }
  end;

implementation

uses UDmSrvApl, BrvFuncoesXE, UDmTra, UClaSrv;

{$R *.dfm}

procedure TCon0004.AjustaStatusBar();
begin
      StbDetalhes.Panels.Items[0].Width := Self.Width - StbDetalhes.Panels.Items[1].Width -
                                                        StbDetalhes.Panels.Items[2].Width -
                                                        StbDetalhes.Panels.Items[3].Width;
end;


procedure TCon0004.BdgRegistrosDblClick(Sender: TObject);
begin
      inherited;
      DetalharCTRC;
end;

procedure TCon0004.BrvBitBtn1Click(Sender: TObject);
begin
      inherited;

      if BtnLocali.Caption = 'Localizar' then
      begin
            RecuperaListaConhecimentos;
            BtnLocali.Caption     := 'Voltar';
            BtnLocali.BrTipoBotao := BrBtnSetaEsquerda;
      end else
      begin
            BtnLocali.Caption     := 'Localizar';
            BtnLocali.BrTipoBotao := BrBtnLocalizar;
            CpT011.EmptyDataSet;
      end;

      BdgRegistros.Visible := (CpT011.RecordCount > 0);

end;

procedure TCon0004.Detalhar1Click(Sender: TObject);
begin
      inherited;
      DetalharCTRC;
end;

procedure TCon0004.RecuperaListaConhecimentos();
var lConexao  : TSDmRWClient;
    lDsResult : String;
    lTpCarga  : String;
    lDtInicio : String;
    lDtFinal  : String;
    lCdEmpres : String;

begin
      try
          lTpCarga  := 'T';
          if ((EdtCdMotori.BrAsInteger > 0) or (EdtCdVeicul.BrAsInteger > 0)) and
             (RdgCarga.ItemIndex = 0) then
          begin
                lTpCarga := 'U';
          end;

          lDtInicio := '';
          if not EdtDtInicio.BrDataVazia then
          begin
                lDtInicio := EdtDtInicio.BrAsDataSQL;
          end;

          lDtFinal  := '';
          if not EdtDtFinal.BrDataVazia then
          begin
                lDtFinal := EdtDtFinal.BrAsDataSQL;
          end;

          if EdtCdEmpres.BrAsInteger > 0 then
          begin
                lCdEmpres := EdtCdEmpres.Text;
          end else
          begin
                lCdEmpres := EdtCdEmpres.BrDicionario.CorpCommaCodes;
          end;

          lConexao    := TSDmRWClient.Create(DmSrvApl.SrvAplica.DBXConnection);
          CpT011.Data := lConexao.ConsultaConhecimentos(
                                          DmSrvApl.BrvDicionario.Credencial,  lDsResult,
                                          lCdEmpres,        EdtCdRemete.Text, EdtCdDestin.Text,
                                          EdtCdCarga.Text,  EdtCdVeicul.Text, EdtCdMotori.Text,
                                          lDtInicio,        lDtFinal,         EdtCdCtrc.Text,
                                          EdtNrNotFis.Text, EdtNrFatura.Text, EdtNrRps.Text,
                                          lTpCarga,EdtCdTitula.Text);
          Totaliza;

      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TCon0004.Totaliza();
var  lVlMercad, lVlPeso  : Double ;
begin
      // Totalizador de Peso e Valor - Manual - Não Recomendado
      CpT011.DisableControls;
      CpT011.First;

      while not CpT011.Eof do
      begin
            lVlMercad := lVlMercad + CpT011.FieldByName('VrMercad').AsFloat;
            lVlPeso   := lVlPeso   + CpT011.FieldByName('NrPeso').AsFloat;

            CpT011.Next;
      end;

      StatusMSG(StbDetalhes, 'Peso Total : '  + FormatFloat('#,###,##0.00',  lVlPeso) , 2);
      StatusMSG(StbDetalhes, 'Valor Total : ' + FormatFloat('#,###,##0.00', lVlMercad), 1);

      CpT011.First;
      CpT011.EnableControls;

end;

procedure TCon0004.EdtCdCargaBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdCarga.BrParams.Add('<%StCarga%>; ');
end;

procedure TCon0004.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TCon0004.EdtCdVeiculChange(Sender: TObject);
begin
      inherited;
      if (EdtCdVeicul.BrAsInteger > 0) or
         (EdtCdMotori.BrAsInteger > 0) then
      begin
            RdgCarga.Enabled := True;
      end else
      begin
            RdgCarga.Enabled := False;
      end;
end;

procedure TCon0004.FormCreate(Sender: TObject);
begin
      inherited;
      try
          Position  :=  poMainFormCenter;
      finally
          Top     :=  0;
          Height  :=  640;
          Width   :=  900;
      end;
      RecuperaDominiosCampos
end;

procedure TCon0004.RecuperaDominiosCampos();
var lDsValues : String;
    lDsItems  : String;
begin
      try
          DmSrvApl.BrvDicionario.CarregarDominiosDoAtributo(
                                                     'T002', 'STCTRC', lDsValues, lDsItems);
          BdgRegistros.Columns.Items[4].PickList.CommaText      :=  lDsItems;
          BdgRegistros.Columns.Items[4].BrPickValue.CommaText   :=  lDsValues;
      except
          raise Exception.Create('Erro ao buscar dados do domínio T002.STCTRC');
      end;
end;

procedure TCon0004.FormResize(Sender: TObject);
begin
      inherited;
      AjustaStatusBar;
end;

procedure TCon0004.DetalharCTRC();
begin
      inherited;
      DmTra.VisualizarConhecimento(CpT011);
end;

initialization
      RegisterClass(TCon0004);

finalization
      UnRegisterClass(TCon0004);

end.
