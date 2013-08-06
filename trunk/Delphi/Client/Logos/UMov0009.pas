unit UMov0009;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvRetCon,
  BrvEditNum, BrvEditDate, BrvBitBtn, BrvImgBot, BrvComboBox, UClaSrv, Mask, BrvCustomMaskEdit,
  BrvCustomEdit, BrvListParam, ImgList, Menus;

type
  TMov0009 = class(TMov0000)
    LblCliFor: TLabel;
    Label11: TLabel;
    Label15: TLabel;
    Label8: TLabel;
    Label5: TLabel;
    Label4: TLabel;
    Label3: TLabel;
    Label7: TLabel;
    Label9: TLabel;
    BtnSalvar: TBrvSpeedButton;
    LblNmConta: TBrvRetCon;
    EdtNrConta: TBrvEditNum;
    EdtCdHistor: TBrvEditNum;
    LblDsHistor: TBrvRetCon;
    EdtCdTitula: TBrvEditNum;
    EdtNrDocto: TBrvEditNum;
    EdtVrValor: TBrvEditNum;
    EdtNrOrdem: TBrvEditNum;
    EdtCdTipo: TBrvEditNum;
    LblDsTipo: TBrvRetCon;
    LblTpForPag: TBrvRetCon;
    EdtNrPreFat: TBrvEditNum;
    Label16: TLabel;
    MetDtEmissa: TBrvEditDate;
    MetDtVencim: TBrvEditDate;
    BtnAbrEmp: TBrvSpeedButton;
    BtnFecEmp: TBrvSpeedButton;
    LblRsTitula: TBrvRetCon;
    PnlCabeca: TPanel;
    Label1: TLabel;
    LblNrPlano: TLabel;
    EdtCdEmpres: TBrvEditNum;
    EdtDsEmpres: TBrvRetCon;
    Label10: TLabel;
    MemComHis: TMemo;
    Label17: TLabel;
    CbxTpLancto: TBrvComboBox;
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure EdtNrContaBrOnBeforeConsulta(Sender: TObject);
    procedure EdtCdHistorBrOnBeforeConsulta(Sender: TObject);
    procedure BtnSalvarClick(Sender: TObject);
    procedure BtnAbrEmpClick(Sender: TObject);
    procedure BtnFecEmpClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
  private
    { Private declarations }
    procedure InicializarFormulario;
    procedure GravarConta;
  public
    { Public declarations }
  end;

var
  Mov0009: TMov0009;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TMov0009.FormCreate(Sender: TObject);
var lDsDomini : TStrings;
    lVrDomini : TStrings;
begin
      inherited;
      DmSrvApl.BrvDicionario.AtributoDominioValores(
                                                'N002', 'TpConta', lDsDomini, lVrDomini);
      CbxTpLancto.Items.Text  := lDsDomini.Text;
      CbxTpLancto.Values.Text := lVrDomini.Text;
end;

procedure TMov0009.InicializarFormulario;
begin
      EdtNrConta.BrAsInteger  := 0;
      EdtCdHistor.BrAsInteger := 0;
      EdtCdTitula.BrAsInteger := 0;
      EdtNrDocto.BrAsInteger  := 0;
      EdtNrOrdem.BrAsInteger  := 0;
      EdtCdTipo.BrAsInteger   := 0;
      EdtVrValor.BrAsInteger  := 0;
      MetDtVencim.Clear;
      MetDtEmissa.BrAsDate    := DmSrvApl.BrvDicionario.DataServer;
      MetDtEmissa.Enabled     := DmSrvApl.BrvDicionario.
                                        TemPermissaoAlteracaoAtributo('N001', 'DTEMIDOC');
      EdtNrPreFat.BrAsInteger := 0;

      LblRsTitula.Text        := '';
      LblNmConta.Text         := '';
      LblDsHistor.Text        := '';
      LblDsHistor.Text        := '';
      LblDsTipo.Text          := '';
      LblTpForPag.Text        := '';

      MemComHis.Lines.Clear;

      if CbxTpLancto.Values[CbxTpLancto.ItemIndex] = 'R' then
      begin
            EdtCdTitula.BrQueryCode := 15;
            LblCliFor.Caption       := 'Cliente';
      end else
      begin
            EdtCdTitula.BrQueryCode := 19;
            LblCliFor.Caption       := 'Fornecedor';
      end;
end;

procedure TMov0009.BtnAbrEmpClick(Sender: TObject);
begin
      NavBarra.SetFocus;

      if EdtDsEmpres.Text = '' then
      begin
           raise Exception.Create('Informe a empresa');
      end;

      if (LblNrPlano.Caption = '') or (LblNrPlano.Caption = '0') then
      begin
            raise Exception.Create('Não há plano de contas definido para essa empresa');
      end;

      if (CbxTpLancto.ItemIndex < 0) or
         (CbxTpLancto.Values[CbxTpLancto.ItemIndex] = '') then
      begin
            raise Exception.Create('Informe o tipo de lançamento');
      end;

      InicializarFormulario;

      EdtCdEmpres.ReadOnly := True;
      CbxTpLancto.Enabled  := False;
      BtnAbrEmp.Enabled    := False;
      BtnFecEmp.Enabled    := True;
      BtnSalvar.Enabled    := True;
      PnlFundo.Visible     := True;
end;

procedure TMov0009.BtnFecEmpClick(Sender: TObject);
begin
      if MessageDlg('Confirma cancelamento do lançamento?', mtConfirmation,
                                                           [mbYes, mbNo], 0) = IdYes then
      begin
            EdtCdEmpres.ReadOnly := False;
            CbxTpLancto.Enabled  := True;
            BtnAbrEmp.Enabled    := True;
            BtnFecEmp.Enabled    := False;
            BtnSalvar.Enabled    := False;
            PnlFundo.Visible     := False;
      end;
end;

procedure TMov0009.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TMov0009.EdtCdHistorBrOnBeforeConsulta(Sender: TObject);
begin
      EdtCdHistor.BrParams.Clear;
      EdtCdHistor.BrParams.Add('<%NrPlano%>;' + LblNrPlano.Caption);
      EdtCdHistor.BrParams.Add('<%NrConta%>;' + EdtNrConta.Text);

      if CbxTpLancto.Values[CbxTpLancto.ItemIndex] = 'R' then
      begin
            EdtCdHistor.BrParams.Add('<%CdClaHis%>;RC');
      end else
      begin
            EdtCdHistor.BrParams.Add('<%CdClaHis%>;PT');
      end;
end;

procedure TMov0009.EdtNrContaBrOnBeforeConsulta(Sender: TObject);
begin
      EdtNrConta.BrParams.Clear;
      EdtNrConta.BrParams.Add('<%NrPlano%>;' + LblNrPlano.Caption);

      if CbxTpLancto.Values[CbxTpLancto.ItemIndex] = 'R' then
      begin
            EdtNrConta.BrParams.Add('<%CdClaHis%>;RC');
      end else
      begin
            EdtNrConta.BrParams.Add('<%CdClaHis%>;PT');
      end;
end;

procedure TMov0009.BtnSalvarClick(Sender: TObject);
begin
      NavBarra.SetFocus;

      if LblNmConta.Text = '' then
      begin
            raise Exception.Create('Informe o número da conta contábil');
      end;

      if LblDsHistor.Text = '' then
      begin
            raise Exception.Create('Informe o histórico');
      end;

      if LblRsTitula.Text = '' then
      begin
            raise Exception.Create('Informe o ' + LblCliFor.Caption);
      end;

      if EdtNrDocto.BrAsInteger = 0 then
      begin
            raise Exception.Create('Informe o número de documento');
      end;

      if EdtNrOrdem.BrAsInteger = 0 then
      begin
            raise Exception.Create('Informe a ordem do documento');
      end;

      if LblDsTipo.Text = '' then
      begin
            raise Exception.Create('Informe o tipo');
      end;

      if EdtVrValor.BrAsFloat = 0 then
      begin
            raise Exception.Create('Valor do lançamento deve ser informado');
      end;

      if MetDtEmissa.BrDataVazia then
      begin
            raise Exception.Create('Informe a data da emissão');
      end;

      if not MetDtEmissa.BrDataValida then
      begin
            raise Exception.Create('Data de emissão não é válida');
      end;

      if MetDtVencim.BrDataVazia then
      begin
            raise Exception.Create('Informe a data de vencimento');
      end;

      if not MetDtVencim.BrDataValida then
      begin
            raise Exception.Create('Data de vencimento não é válida');
      end;

      GravarConta;

      InicializarFormulario;
      EdtNrConta.SetFocus;
end;

procedure TMov0009.GravarConta;
var lConexao     : TSDmAdmClient;
    lDsResult    : String;
begin
      lConexao := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
 {         lDsResult := lConexao.LancarContasPagarReceber(DmSrvApl.BrvDicionario.Credencial,
                                            Name,
                                            EdtCdHistor.BrAsInteger,
                                            CbxTpLancto.Values[CbxTpLancto.ItemIndex],
                                            EdtVrValor.BrAsFloat,
                                            EdtCdTipo.BrAsInteger,
                                            EdtCdTitula.BrAsInteger,
                                            EdtCdEmpres.BrAsInteger,
                                            EdtNrDocto.BrAsInteger,
                                            MetDtEmissa.BrAsDate,
                                            EdtNrPreFat.BrAsInteger,
                                            EdtNrOrdem.BrAsInteger,
                                            MetDtVencim.BrAsDate,
                                            0,
                                            '',
                                            EdtNrConta.BrAsInteger,
                                            MemComHis.Lines.Text,
                                            0,
                                            StrToInt(LblNrPlano.Caption));
}
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
          InicializarFormulario;
      finally
          FreeAndNil(lConexao);
      end;
end;

initialization
      RegisterClass(TMov0009);

finalization
      UnRegisterClass(TMov0009);
end.
