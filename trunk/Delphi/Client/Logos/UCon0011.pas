unit UCon0011;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvEditNum,
  Mask, DBCtrls, BrvDbRetCon, BrvEditDate, BrvRetCon, DB, DBClient, BrvClientDataSet,
  BrvBitBtn, ComCtrls, Grids, BrvDbGrids, BrvDbGrid, DBGrids, BrvListParam, ImgList, Menus;

type
  TCon0011 = class(TMov0000)
    BtnAbrCon: TBrvSpeedButton;
    BtnFecCon: TBrvSpeedButton;
    PnlCabeca: TPanel;
    Label1: TLabel;
    LblNrPlano: TLabel;
    EdtCdEmpres: TBrvEditNum;
    EdtDsEmpres: TBrvRetCon;
    CdsPlaCon: TBrvClientDataSet;
    SbtLocali: TBrvSpeedButton;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label10: TLabel;
    BntLimDep: TSpeedButton;
    BntLimCre: TSpeedButton;
    EdtMasConCre: TMaskEdit;
    EdtMasConDeb: TMaskEdit;
    EdtNrDocto: TEdit;
    LblDsConDeb: TBrvRetCon;
    LblDsConCre: TBrvRetCon;
    LblDsHistor: TBrvRetCon;
    EdtNrConDeb: TBrvEditNum;
    EdtNrConCre: TBrvEditNum;
    EdtVrLancto: TBrvEditNum;
    EdtCdHistor: TBrvEditNum;
    EdtDtInicio: TBrvEditDate;
    Label2: TLabel;
    EdtDsComHis: TEdit;
    Label9: TLabel;
    ChkIgual: TCheckBox;
    Label3: TLabel;
    EdtDtFinal: TBrvEditDate;
    CdsLacto: TBrvClientDataSet;
    DBGLancto: TBrvDBGrid;
    DtsLancto: TDataSource;
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BtnAbrConClick(Sender: TObject);
    procedure BtnFecConClick(Sender: TObject);
    procedure EdtNrConDebBrOnBeforeConsulta(Sender: TObject);
    procedure EdtNrConDebDragOver(Sender, Source: TObject; X, Y: Integer;
      State: TDragState; var Accept: Boolean);
    procedure EdtNrConDebDragDrop(Sender, Source: TObject; X, Y: Integer);
    procedure BntLimDepClick(Sender: TObject);
    procedure BntLimCreClick(Sender: TObject);
    procedure EdtNrConDebExit(Sender: TObject);
    procedure EdtNrConCreBrOnBeforeConsulta(Sender: TObject);
    procedure EdtNrConCreDragDrop(Sender, Source: TObject; X, Y: Integer);
    procedure EdtNrConCreExit(Sender: TObject);
    procedure SbtLocaliClick(Sender: TObject);
  private
    { Private declarations }
    gDsMasLim  : String;
    procedure LimparFormulario;
    function FiltroPesquisa: String;
  protected
    gDsFiltro  : String;
    procedure AbrirPesquisa;
  public
    { Public declarations }
  end;

var
  Con0011: TCon0011;

implementation

uses UDmSrvApl, ULogos, UDmCtb, UCon0010, UDmSis;

{$R *.dfm}

procedure TCon0011.BntLimCreClick(Sender: TObject);
begin
      EdtNrConCre.BrAsInteger := 0;
      EdtMasConCre.Text       := '';
      LblDsConCre.Text        := '';
      EdtNrConCre.SetFocus;
end;

procedure TCon0011.BntLimDepClick(Sender: TObject);
begin
      EdtNrConDeb.BrAsInteger := 0;
      EdtMasConDeb.Text       := '';
      LblDsConDeb.Text        := '';
      EdtNrConDeb.SetFocus;
end;

procedure TCon0011.BtnAbrConClick(Sender: TObject);
begin
      LimparFormulario;

      NavBarra.SetFocus;

      if EdtDsEmpres.Text = '' then
      begin
           raise Exception.Create('Informe a empresa');
      end;

      if (LblNrPlano.Caption = '') or (LblNrPlano.Caption = '0') then
      begin
            raise Exception.Create('Não há plano de contas definido para essa empresa');
      end;

      CdsPlaCon.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(77,
                                               '<%NrPlano%>;' + LblNrPlano.Caption, Name);

      EdtMasConDeb.EditMask    := CdsPlaCon.FieldByName('DsMasCla').AsString + ';1; ';
      EdtMasConCre.EditMask    := EdtMasConDeb.EditMask;
      gDsMasLim                := EdtMasConDeb.Text;

      EdtMasConDeb.Width := (Length(CdsPlaCon.FieldByName('DsMasCla').AsString) * 8) + 5;
      EdtMasConCre.Width := EdtMasConDeb.Width;

      EdtCdEmpres.ReadOnly := True;
      BtnAbrCon.Enabled    := False;
      BtnFecCon.Enabled    := True;
      SbtLocali.Enabled    := True;
      PnlFundo.Visible     := True;
end;

procedure TCon0011.BtnFecConClick(Sender: TObject);
var lNrFormul : Integer;
begin
      CdsLacto.EmptyDataSet;

      PnlFundo.Visible     := False;
      EdtCdEmpres.ReadOnly := False;
      BtnAbrCon.Enabled    := True;
      BtnFecCon.Enabled    := False;
      SbtLocali.Enabled    := False;

      for lNrFormul := 0 to (FrmLogos.MdiChildCount - 1) do
      begin
            if DmSis.FormularioAberto('Con0010', lNrFormul) then
            begin
                  FreeAndNil(Con0010);
            end;
      end;
end;

procedure TCon0011.LimparFormulario;
begin
      EdtDtInicio.BrAsDate    := Date;
      EdtDtFinal.BrAsDate     := Date;

      EdtNrConDeb.BrAsInteger := 0;
      EdtMasConDeb.Text       := '';
      EdtNrConCre.BrAsInteger := 0;
      EdtMasConCre.Text       := '';
      EdtNrDocto.Text         := '';
      EdtVrLancto.BrAsFloat   := 0;
      EdtCdHistor.BrAsInteger := 0;

      LblDsConDeb.Text        := '';
      LblDsConCre.Text        := '';
      LblDsHistor.Text        := '';

      EdtDsComHis.Text        := '';

      EdtNrConDeb.Enabled     := True;
      EdtMasConDeb.Enabled    := True;
      BntLimDep.Enabled       := True;
      EdtNrConCre.Enabled     := True;
      EdtMasConCre.Enabled    := True;
      BntLimCre.Enabled       := True;
      EdtNrDocto.Enabled      := True;
      EdtVrLancto.Enabled     := True;
      EdtCdHistor.Enabled     := True;
end;

procedure TCon0011.SbtLocaliClick(Sender: TObject);
begin
      NavBarra.SetFocus;

      if not EdtDtInicio.BrDataVazia then
      begin
            if not EdtDtInicio.BrDataValida then
            begin
                  raise Exception.Create('Data inicial está inválida');
            end;
      end;

      if not EdtDtFinal.BrDataVazia then
      begin
            if not EdtDtFinal.BrDataValida then
            begin
                  raise Exception.Create('Data final está inválida');
            end;
      end;

      if not EdtDtInicio.BrDataVazia and not EdtDtFinal.BrDataVazia then
      begin
            if EdtDtInicio.BrAsDate > EdtDtFinal.BrAsDate then
            begin
                  raise Exception.Create('Data inicial não pode ser menor que data final');
            end;
      end;

      AbrirPesquisa;
end;

procedure TCon0011.AbrirPesquisa;
begin
      gDsFiltro := FiltroPesquisa;
      CdsLacto.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(97,
                                               '<%DsWhere%>;' + gDsFiltro, Name);

      (CdsLacto.FieldByName('VrLancto') as TNumericField).EditMask      := '0.000';
      (CdsLacto.FieldByName('VrLancto') as TNumericField).DisplayFormat := '0.000';
end;

function TCon0011.FiltroPesquisa : String;
const lDsAspas = #39;
begin
      Result := 'B004.CdEmpres = ' + EdtCdEmpres.Text;

      if not EdtDtInicio.BrDataVazia then
      begin
            Result := Result + ' and B004.DtLancto >= ' + lDsAspas +
                                EdtDtInicio.BrAsDataSQL + lDsAspas;
      end;

      if not EdtDtFinal.BrDataVazia then
      begin
            Result := Result + ' and B004.DtLancto <= ' + lDsAspas +
                                 EdtDtFinal.BrAsDataSQL + lDsAspas;
      end;

      if LblDsConDeb.Text <> '' then
      begin
            Result := Result + ' and B004.NrConDeb = ' + EdtNrConDeb.Text;
      end;

      if LblDsConCre.Text <> '' then
      begin
            Result := Result + ' and B004.NrConCre = ' + EdtNrConCre.Text;
      end;

      if EdtVrLancto.BrAsFloat > 0 then
      begin
            Result := Result + ' and B004.VrLancto = ' + FloatToStr(EdtVrLancto.BrAsFloat);
      end;

      if LblDsHistor.Text <> '' then
      begin
            Result := Result + ' and B004.CdHistor = ' + EdtCdHistor.Text;
      end;

      if EdtNrDocto.Text <> '' then
      begin
            if ChkIgual.Checked = False then
            begin
                  Result := Result + ' and B004.NrDocLan like ' + lDsAspas + '%' +
                                          EdtNrDocto.Text + '%' + lDsAspas;
            end else
            begin
                  Result := Result + ' and B004.NrDocLan like ' +
                                                    lDsAspas + EdtNrDocto.Text + lDsAspas;
            end;
      end;

      if EdtDsComHis.Text <> '' then
      begin
             Result := Result  + ' and B004.DsComHis like ' + lDsAspas + '%' +
                                     EdtDsComHis.Text + '%' + lDsAspas;
      end;
end;

procedure TCon0011.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TCon0011.EdtNrConCreBrOnBeforeConsulta(Sender: TObject);
begin
      DmCtb.ConsultarPlanoContas(StrToInt(LblNrPlano.Caption), 1,
                                 EdtMasConCre, EdtNrConCre, False, nil, nil, 0);
end;

procedure TCon0011.EdtNrConCreDragDrop(Sender, Source: TObject; X, Y: Integer);
begin
      if Con0010.TrvPlano.Selected <> nil then
      begin
            BntLimCreClick(nil);
            EdtMasConCre.Text := PString(Con0010.TrvPlano.Selected.Data)^;
            EdtNrConCreExit(nil);
      end;
end;

procedure TCon0011.EdtNrConCreExit(Sender: TObject);
var  lNrConta  : Integer;
     lNrClassi : String;
     lNmConta  : String;
begin
      if (EdtNrConCre.BrAsInteger <> 0) or (EdtMasConCre.Text <> gDsMasLim) then
      begin
            lNrConta  := EdtNrConCre.BrAsInteger;
            lNrClassi := EdtMasConCre.Text;

            try
                DmCtb.PesquisarConta(lNrConta, lNrClassi,
                                     lNmConta, StrToInt(LblNrPlano.Caption), gDsMasLim);
            finally
                LblDsConCre.Text        := lNmConta;
                EdtNrConCre.BrAsInteger := lNrConta;
                EdtMasConCre.Text       := lNrClassi;
            end;
      end;
end;

procedure TCon0011.EdtNrConDebBrOnBeforeConsulta(Sender: TObject);
begin
      DmCtb.ConsultarPlanoContas(StrToInt(LblNrPlano.Caption), 1,
                                 EdtMasConDeb, EdtNrConDeb, False, nil, nil, 0);
end;

procedure TCon0011.EdtNrConDebDragDrop(Sender, Source: TObject; X, Y: Integer);
begin
      if Con0010.TrvPlano.Selected <> nil then
      begin
            BntLimDepClick(nil);
            EdtMasConDeb.Text := PString(Con0010.TrvPlano.Selected.Data)^;
            EdtNrConDebExit(nil);
      end;
end;

procedure TCon0011.EdtNrConDebDragOver(Sender, Source: TObject; X, Y: Integer;
  State: TDragState; var Accept: Boolean);
var  Node: TTreeNode;
begin
      Node   := Con0010.TrvPlano.GetNodeAt(X, Y);

      Accept := (Source <> Sender)           and (Node <> nil)  and
                (Node.Parent <> Con0010.TrvPlano.Selected) and
                (Node <> Con0010.TrvPlano.Selected);
end;

procedure TCon0011.EdtNrConDebExit(Sender: TObject);
var  lNrConta  : Integer;
     lNrClassi : String;
     lNmConta  : String;
begin
      if (EdtNrConDeb.BrAsInteger <> 0) or (EdtMasConDeb.Text <> gDsMasLim) then
      begin
            lNrConta    :=  EdtNrConDeb.BrAsInteger;
            lNrClassi   :=  EdtMasConDeb.Text;

            try
                DmCtb.PesquisarConta(lNrConta, lNrClassi,
                                     lNmConta, StrToInt(LblNrPlano.Caption), gDsMasLim);
            finally
                LblDsConDeb.Text        :=  lNmConta;
                EdtNrConDeb.BrAsInteger :=  lNrConta;
                EdtMasConDeb.Text       :=  lNrClassi;
            end;
      end;
end;

initialization
      RegisterClass(TCon0011);

finalization
      UnRegisterClass(TCon0011);

end.
