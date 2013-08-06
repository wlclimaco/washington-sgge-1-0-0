unit URel0004;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, URel0000, BrvGeraRelatorio, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  BrvRetCon, StdCtrls, BrvEditNum, Mask, BrvMesAno, DB, DBClient, CheckLst,
  BrvCheckListBox, BrvEditDate, BrvClientDataSet, ComCtrls, BrvListParam, ImgList, Menus,
  BrvCustomMaskEdit, BrvCustomEdit, BrvBitBtn;

type
  TRel0004 = class(TREL0000)
    Panel1: TPanel;
    EdtNrPagIni: TBrvEditNum;
    TblTemp: TClientDataSet;
    TblTempNrPagIni: TIntegerField;
    GbxEmpres: TGroupBox;
    ClbEmpres: TBrvCheckListBox;
    EdtCdEmpres: TBrvEditNum;
    EdtDsEmpres: TBrvRetCon;
    LblNrPlano: TBrvRetCon;
    Label7: TLabel;
    Label1: TLabel;
    Label5: TLabel;
    Label8: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    CbxEmpDat: TCheckBox;
    CbxSalZer: TCheckBox;
    CbxTotDat: TCheckBox;
    EdtDtInicia: TBrvEditDate;
    EdtDtFinal: TBrvEditDate;
    EdtNrConIni: TBrvEditNum;
    BntLimConIni: TSpeedButton;
    LblDsConIni: TBrvRetCon;
    EdtMasConIni: TMaskEdit;
    EdtNrConFim: TBrvEditNum;
    BntLimConFim: TSpeedButton;
    LblDsConFim: TBrvRetCon;
    EdtMasConFim: TMaskEdit;
    EdtCdCenCus: TBrvEditNum;
    LblDsCenCus: TBrvRetCon;
    CdsPlaCon: TBrvClientDataSet;
    TblTempDsMasIni: TStringField;
    TblTempDsMasLim: TStringField;
    TblTempNrConIni: TIntegerField;
    TblTempDsMasFim: TStringField;
    TblTempNrConFim: TIntegerField;
    TblTempCdEmpres: TStringField;
    TblTempDtInicia: TDateField;
    TblTempDtFinal: TDateField;
    TblTempCdCenCus: TIntegerField;
    TblTempSnEmpDat: TBooleanField;
    TblTempSnSalZer: TBooleanField;
    TblTempDsCencus: TStringField;
    TblTempNrPlano: TStringField;
    TblTempSnTotDat: TBooleanField;
    BtnAbrCon: TBrvBitBtn;
    BtnFecCon: TBrvBitBtn;
    procedure BtnAbrConClick(Sender: TObject);
    procedure BtnFecConClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure EdtNrConIniBrOnBeforeConsulta(Sender: TObject);
    procedure EdtNrConIniDragDrop(Sender, Source: TObject; X, Y: Integer);
    procedure BntLimConIniClick(Sender: TObject);
    procedure EdtNrConIniExit(Sender: TObject);
    procedure EdtNrConIniDragOver(Sender, Source: TObject; X, Y: Integer;
      State: TDragState; var Accept: Boolean);
    procedure BntLimConFimClick(Sender: TObject);
    procedure EdtNrConFimBrOnBeforeConsulta(Sender: TObject);
    procedure EdtNrConFimDragDrop(Sender, Source: TObject; X, Y: Integer);
    procedure EdtNrConFimExit(Sender: TObject);
    procedure BtnGeraRelClick(Sender: TObject);
  private
    { Private declarations }
    gDsMasLim  : String;
    gCdTodEmp  : String;
    procedure EmpresasSelecionadas(var pCdPriEmp, pCdTodEmp: String);
  public
    { Public declarations }
  end;

var
  Rel0004: TRel0004;

implementation

uses UDmCtb, UDmSrvApl, UCon0010;

{$R *.dfm}

procedure TRel0004.FormCreate(Sender: TObject);
begin
      inherited;
      ClbEmpres.Items.CommaText  := DmSrvApl.BrvDicionario.CorpCommaCodesNames;
      ClbEmpres.Values.CommaText := DmSrvApl.BrvDicionario.CorpCommaCodes;
end;

procedure TRel0004.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TRel0004.EdtNrConFimBrOnBeforeConsulta(Sender: TObject);
begin
      DmCtb.ConsultarPlanoContas(StrToInt(LblNrPlano.Text), 1,
                                 EdtMasConFim, EdtNrConFim, False, nil, nil, 0);
end;

procedure TRel0004.EdtNrConFimDragDrop(Sender, Source: TObject; X, Y: Integer);
begin
      if Con0010.TrvPlano.Selected <> nil then
      begin
            BntLimConFimClick(nil);
            EdtMasConFim.Text := PString(Con0010.TrvPlano.Selected.Data)^;
            EdtNrConFimExit(nil);
      end;
end;

procedure TRel0004.EdtNrConFimExit(Sender: TObject);
var  lNrConta  : Integer;
     lNrClassi : String;
     lNmConta  : String;
begin
      if (EdtNrConFim.BrAsInteger <> 0) or (EdtMasConFim.Text <> gDsMasLim) then
      begin
            lNrConta    :=  EdtNrConFim.BrAsInteger;
            lNrClassi   :=  EdtMasConFim.Text;

            try
                DmCtb.PesquisarConta(lNrConta, lNrClassi,
                                     lNmConta, StrToInt(LblNrPlano.Text), gDsMasLim);
            finally
                LblDsConFim.Text        :=  lNmConta;
                EdtNrConFim.BrAsInteger :=  lNrConta;
                EdtMasConFim.Text       :=  lNrClassi;
            end;
      end;
end;

procedure TRel0004.EdtNrConIniBrOnBeforeConsulta(Sender: TObject);
begin
      DmCtb.ConsultarPlanoContas(StrToInt(LblNrPlano.Text), 1,
                                                     EdtMasConIni, EdtNrConIni, False, nil, nil, 0);
end;

procedure TRel0004.EdtNrConIniDragDrop(Sender, Source: TObject; X, Y: Integer);
begin
      if Con0010.TrvPlano.Selected <> nil then
      begin
            BntLimConIniClick(nil);
            EdtMasConIni.Text := PString(Con0010.TrvPlano.Selected.Data)^;
            EdtNrConIniExit(nil);
      end;
end;

procedure TRel0004.EdtNrConIniDragOver(Sender, Source: TObject; X, Y: Integer;
  State: TDragState; var Accept: Boolean);
var  Node: TTreeNode;
begin
      Node   := Con0010.TrvPlano.GetNodeAt(X, Y);

      Accept := (Source <> Sender)           and (Node <> nil)  and
                (Node.Parent <> Con0010.TrvPlano.Selected) and
                (Node <> Con0010.TrvPlano.Selected);
end;

procedure TRel0004.EdtNrConIniExit(Sender: TObject);
var  lNrConta  : Integer;
     lNrClassi : String;
     lNmConta  : String;
begin
      if (EdtNrConIni.BrAsInteger <> 0) or (EdtMasConIni.Text <> gDsMasLim) then
      begin
            lNrConta    :=  EdtNrConIni.BrAsInteger;
            lNrClassi   :=  EdtMasConIni.Text;

            try
                DmCtb.PesquisarConta(lNrConta, lNrClassi, lNmConta,
                                     StrToInt(LblNrPlano.Text), gDsMasLim);
            finally
                LblDsConIni.Text        :=  lNmConta;
                EdtNrConIni.BrAsInteger :=  lNrConta;
                EdtMasConIni.Text       :=  lNrClassi;
            end;
      end;
end;

procedure TRel0004.BntLimConFimClick(Sender: TObject);
begin
      EdtNrConFim.BrAsInteger := 0;
      EdtMasConFim.Text       := '';
      LblDsConFim.Text        := '';
      EdtNrConFim.SetFocus;
end;

procedure TRel0004.BntLimConIniClick(Sender: TObject);
begin
      EdtNrConIni.BrAsInteger := 0;
      EdtMasConIni.Text       := '';
      LblDsConIni.Text        := '';
      EdtNrConIni.SetFocus;
end;

procedure TRel0004.BtnAbrConClick(Sender: TObject);
var lCdPriEmp : String;
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

      EdtMasConIni.EditMask := CdsPlaCon.FieldByName('DsMasCla').AsString + ';1; ';
      EdtMasConFim.EditMask := EdtMasConIni.EditMask;
      gDsMasLim             := EdtMasConIni.Text;

      EdtMasConIni.Width := (Length(CdsPlaCon.FieldByName('DsMasCla').AsString) * 8) + 5;
      EdtMasConFim.Width := EdtMasConIni.Width;

      ClbEmpres.Enabled       := False;
      BtnAbrCon.Enabled       := False;
      BtnGeraRel.Enabled      := True;
      BtnFecCon.Enabled       := True;
      PnlFundo.Visible        := True;
      EdtNrConIni.SetFocus;
end;

procedure TRel0004.EmpresasSelecionadas(var pCdPriEmp : String; var pCdTodEmp : String);
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

procedure TRel0004.BtnFecConClick(Sender: TObject);
begin
      ClbEmpres.Enabled  := True;
      BtnAbrCon.Enabled  := True;
      BtnGeraRel.Enabled := False;
      BtnFecCon.Enabled  := False;
      PnlFundo.Visible   := False;
end;

procedure TRel0004.BtnGeraRelClick(Sender: TObject);
begin
      inherited;

      if ((EdtNrConIni.BrAsInteger <> 0) or (EdtMasConIni.Text <> gDsMasLim)) and
         (LblDsConIni.Text = '') then
      begin
            raise Exception.Create('Conta inicial não cadastrada');
      end;

      if ((EdtNrConFim.BrAsInteger <> 0) or (EdtMasConFim.Text <> gDsMasLim)) and
         (LblDsConFim.Text = '') then
      begin
            raise Exception.Create('Conta final não cadastrada');
      end;

      if EdtNrPagIni.BrAsInteger  <= 0 then
      begin
            raise Exception.Create('Página inicial inválida');
      end;

      if not EdtDtInicia.BrDataValida then
      begin
            raise Exception.Create('Data inicial inválida.');
      end;

      if not EdtDtFinal.BrDataValida then
      begin
            raise Exception.Create('Data final inválida.');
      end;

      if EdtDtFinal.BrAsDate < EdtDtInicia.BrAsDate then
      begin
            raise Exception.Create('Data final menor do que data incial.');
      end;

      BrvGerRel.IniciarRelatorio('RLC0007', 'C', Name);

      BrvGerRel.InserirParametroSQL('NrPlano', LblNrPlano.Text);

      // =-=-=-=-=-=-=-=
      // Guardando parâmetros no DataSet auxiliar
      // =-=-=-=-=-=-=-=

      if not TblTemp.Active then
      begin
            TblTemp.CreateDataSet;
      end else
      begin
            TblTemp.EmptyDataSet;
      end;

      TblTemp.Append;
      TblTemp.FieldByName('NrPagIni').AsInteger  := EdtNrPagIni.BrAsInteger;
      TblTemp.FieldByName('DsMasLim').AsString   :=
                                      StringReplace(gDsMasLim, ' ', '_', [rfReplaceAll]);
      TblTemp.FieldByName('DsMasIni').AsString   := EdtMasConIni.Text;
      TblTemp.FieldByName('NrConIni').AsInteger  := EdtNrConIni.BrAsInteger;
      TblTemp.FieldByName('DsMasFim').AsString   := EdtMasConFim.Text;
      TblTemp.FieldByName('NrConFim').AsInteger  := EdtNrConFim.BrAsInteger;
      TblTemp.FieldByName('CdEmpres').AsString   := gCdTodEmp;
      TblTemp.FieldByName('DtInicia').AsDateTime := EdtDtInicia.BrAsDate;
      TblTemp.FieldByName('DtFinal').AsDateTime  := EdtDtFinal.BrAsDate;
      TblTemp.FieldByName('CdCenCus').AsInteger  := EdtCdCenCus.BrAsInteger;
      TblTemp.FieldByName('DsCenCus').AsString   := LblDsCenCus.Text;
      TblTemp.FieldByName('SnEmpDat').AsBoolean  := CbxEmpDat.Checked;
      TblTemp.FieldByName('SnSalZer').AsBoolean  := CbxSalZer.Checked;
      TblTemp.FieldByName('SnTotDat').AsBoolean  := CbxTotDat.Checked;
      TblTemp.FieldByName('NrPlano').AsString    := LblNrPlano.Text;
      TblTemp.Post;

      // =-=-=-=-=-=-=-=

      BrvGerRel.ImprimirRelatorio;
end;

initialization
      RegisterClass(TRel0004);

finalization
      UnRegisterClass(TRel0004);
end.
