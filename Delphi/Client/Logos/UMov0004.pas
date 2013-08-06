unit UMov0004;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvEditNum,
  Mask, DBCtrls, BrvDbRetCon, BrvEditDate, BrvRetCon, DB, DBClient, BrvClientDataSet,
  BrvBitBtn, ComCtrls, BrvLogScreen, BrvListParam, ImgList, Menus, BrvCustomMaskEdit, BrvCustomEdit;

type
  TMov0004 = class(TMov0000)
    PnlCabeca: TPanel;
    Label1: TLabel;
    Label2: TLabel;
    EdtCdEmpres: TBrvEditNum;
    EdtDtLancto: TBrvEditDate;
    EdtDsEmpres: TBrvRetCon;
    CdsPlaCon: TBrvClientDataSet;
    TblTemp: TClientDataSet;
    TblTempNrConta: TIntegerField;
    TblTempNmConta: TStringField;
    TblTempVrLancto: TFloatField;
    TblTempNrClassi: TStringField;
    TblTempCdHistor: TIntegerField;
    TblTempDsHistor: TStringField;
    TblTempDsComHis: TMemoField;
    Panel2: TPanel;
    EdtMasConCre: TMaskEdit;
    EdtMasConDeb: TMaskEdit;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    BntDebito: TSpeedButton;
    BntCredit: TSpeedButton;
    Label10: TLabel;
    BntLimDep: TSpeedButton;
    BntLimCre: TSpeedButton;
    Label8: TLabel;
    Label9: TLabel;
    EdtNrDocto: TEdit;
    LblDsConDeb: TBrvRetCon;
    LblDsConCre: TBrvRetCon;
    LblDsCeCuCr: TBrvRetCon;
    LblDsHistor: TBrvRetCon;
    Panel3: TPanel;
    MemComHis: TMemo;
    Panel4: TPanel;
    BntGravar: TBrvBitBtn;
    BntCancel: TBrvBitBtn;
    EdtNrConDeb: TBrvEditNum;
    EdtNrConCre: TBrvEditNum;
    LblDsCeCuDe: TBrvRetCon;
    EdtCdCeCuCr: TBrvEditNum;
    EdtVrLancto: TBrvEditNum;
    EdtCdCeCuDe: TBrvEditNum;
    EdtCdHistor: TBrvEditNum;
    BrBtnAbrir: TBrvBitBtn;
    BtnFecCon: TBrvBitBtn;
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BtnAbrConClick(Sender: TObject);
    procedure BtnFecConClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure BntCancelClick(Sender: TObject);
    procedure BntGravarClick(Sender: TObject);
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
    procedure BntDebitoClick(Sender: TObject);
    procedure BntCreditClick(Sender: TObject);
  private
    { Private declarations }
    gDsMasLim  : String;
    gNrPlano   : Integer;
    procedure LimparFormulario;
    procedure GerarLancamento;
    procedure CarregarLancamentosDiversos(pDsLancto: String);

  public
    { Public declarations }
  end;

var
  Mov0004: TMov0004;

implementation

uses UDmSrvApl, ULogos, UDmCtb, UCon0010, UMov0005, UDmSis;

{$R *.dfm}

procedure TMov0004.BntCancelClick(Sender: TObject);
begin
      LimparFormulario;
end;

procedure TMov0004.BntCreditClick(Sender: TObject);
begin
      CarregarLancamentosDiversos('Crédito');
end;

procedure TMov0004.BntDebitoClick(Sender: TObject);
begin
      CarregarLancamentosDiversos('Débito');
end;

procedure TMov0004.CarregarLancamentosDiversos(pDsLancto : String);
begin
      try
          Mov0005 := TMov0005.Create(Self);

          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-=-=-=-=-=-=-=-=-=-= Chamada lançamentos diversos
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          Mov0005.TblTemp.Data          := TblTemp.Data;
          Mov0005.gCdHistor             := EdtCdHistor.BrAsInteger;
          Mov0005.gNrPlano              := gNrPlano;
          Mov0005.LblDsDebCre.Caption   := pDsLancto;
          Mov0005.EdtTtRateio.BrAsFloat := EdtVrLancto.BrAsFloat;

          if EdtCdHistor.BrAsInteger <> 0 then
          begin
                Mov0005.DBGLancto.Columns.Items[4].Visible := False;
                Mov0005.DBGLancto.Columns.Items[5].Visible := False;
          end else
          begin
                Mov0005.DBGLancto.Columns.Items[4].Visible := True;
                Mov0005.DBGLancto.Columns.Items[5].Visible := True;
          end;

          if MemComHis.Lines.Text <> '' then
          begin
                Mov0005.DBGLancto.Columns.Items[6].Visible := False;
          end else
          begin
                Mov0005.DBGLancto.Columns.Items[6].Visible := True;
          end;

          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          // =-=-=-=-=-=-=-=-=-=-= Rotorno lançamento diversos
          // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
          if Mov0005.ShowModal = MrOk then
          begin
                TblTemp.Data  := Mov0005.TblTemp.Data;

                if not TblTemp.IsEmpty then
                begin
                      if Mov0005.LblDsDebCre.Caption = 'Débito' then
                      begin
                            EdtNrConDeb.Text      := '';
                            EdtMasConDeb.Text     := '';
                            LblDsConDeb.Text      := 'Diversos';

                            EdtNrConDeb.Enabled   := False;
                            EdtMasConDeb.Enabled  := False;
                            BntLimDep.Enabled     := False;
                            BntCredit.Enabled     := False;
                      end else
                      begin
                            EdtNrConCre.Text      := '';
                            EdtMasConCre.Text     := '';
                            LblDsConCre.Text      := 'Diversos';

                            EdtNrConCre.Enabled   := False;
                            EdtMasConCre.Enabled  := False;
                            BntLimCre.Enabled     := False;
                            BntDebito.Enabled     := False;
                      end;

                      EdtVrLancto.Enabled := False;
                      EdtCdHistor.Enabled := not Mov0005.DBGLancto.Columns.Items[4].Visible;
                      MemComHis.Enabled   := not Mov0005.DBGLancto.Columns.Items[6].Visible;
                end else
                begin
                      EdtNrConDeb.Enabled   := True;
                      EdtMasConDeb.Enabled  := True;
                      BntDebito.Enabled     := True;
                      BntLimDep.Enabled     := True;
                      LblDsConDeb.Text      := '';

                      EdtNrConCre.Enabled   := True;
                      EdtMasConCre.Enabled  := True;
                      BntCredit.Enabled     := True;
                      BntLimCre.Enabled     := True;
                      LblDsConCre.Text       := '';

                      EdtVrLancto.Enabled   := True;
                      EdtCdHistor.Enabled   := True;
                      MemComHis.Enabled     := True;
                end;

                EdtVrLancto.BrAsFloat := Mov0005.EdtTtRateio.BrAsFloat;
          end;
      finally
          FreeAndNil(Mov0005);
      end;
end;

procedure TMov0004.BntGravarClick(Sender: TObject);
begin
      if  EdtMasConDeb.Enabled then
      begin
            if  LblDsConDeb.Text = '' then
            begin
                  EdtNrConDeb.SetFocus;
                  raise Exception.Create('Informe a conta de débito');
            end;

            // *** Testando se a conta não está cadastrada no diversos
            if  TblTemp.FindKey([EdtMasConDeb.Text]) then
            begin
                  EdtNrConDeb.SetFocus;
                  raise Exception.Create('Conta de débito já informada nas de crédito');
            end;
      end;

      if  EdtMasConCre.Enabled then
      begin
            if  LblDsConCre.Text = '' then
            begin
                  EdtNrConCre.SetFocus;
                  raise Exception.Create('Informe a conta de crédito');
            end;

           // *** Testando se a conta não está cadastrada no diversos
            if  TblTemp.FindKey([EdtMasConCre.Text]) then
            begin
                  EdtNrConCre.SetFocus;
                  raise Exception.Create('Conta de crédito já informada nas de débito');
            end;
      end;

      if EdtNrConDeb.BrAsInteger = EdtNrConCre.BrAsInteger then
      begin
            EdtNrConDeb.SetFocus;
            raise Exception.Create('Contas não podem ser iguais');
      end;

      if  EdtVrLancto.BrAsFloat  <= 0 then
      begin
            EdtVrLancto.SetFocus;
            raise Exception.Create('Valor de lancamento inválido');
      end;

      if (EdtCdHistor.Enabled) and (EdtCdHistor.BrAsInteger = 0) then
      begin
            EdtCdHistor.SetFocus;
            raise Exception.Create('Informe um histórico válido');
      end;

      try
          BntGravar.Enabled := False;
          GerarLancamento;

          LimparFormulario;
      finally
          BntGravar.Enabled := True;
      end;
end;

procedure TMov0004.BntLimCreClick(Sender: TObject);
begin
      EdtNrConCre.BrAsInteger := 0;
      EdtMasConCre.Text       := '';
      LblDsConCre.Text        := '';
      EdtNrConCre.SetFocus;
end;

procedure TMov0004.BntLimDepClick(Sender: TObject);
begin
      EdtNrConDeb.BrAsInteger := 0;
      EdtMasConDeb.Text       := '';
      LblDsConDeb.Text        := '';
      EdtNrConDeb.SetFocus;
end;

procedure TMov0004.GerarLancamento;
var lDsComple : TStrings;
    lCdHistor : Integer;
    lNrLancto : Integer;
begin
      lDsComple      := TStringList.Create;
      lDsComple.Text := MemComHis.Lines.Text;

      try
          DmCtb.IniciarLancamentos;
          lNrLancto := 1;

          if (EdtMasConDeb.Text <> gDsMasLim) and
             (EdtMasConCre.Text <> gDsMasLim) then
          begin
                DMCTB.LancarContabilidade(lNrLancto,
                                          gNrPlano,
                                          EdtCdEmpres.BrAsInteger,
                                          EdtDtLancto.BrAsDate,
                                          EdtNrConDeb.BrAsInteger,
                                          EdtNrConCre.BrAsInteger,
                                          EdtVrLancto.BrAsFloat,
                                          EdtNrDocto.Text,
                                          EdtCdHistor.BrAsInteger,
                                          lDsComple,
                                          Name, 'N',
                                          EdtCdCeCuCr.BrAsInteger,
                                          EdtCdCeCuDe.BrAsInteger,
                                          EdtMasConDeb.Text,
                                          EdtMasConCre.Text);
          end else
          begin
                lCdHistor := EdtCdHistor.BrAsInteger;
                TblTemp.First;

                while not TblTemp.Eof do
                begin
                      if not EdtCdHistor.Enabled then
                      begin
                            lCdHistor := TblTempCdHistor.AsInteger;
                      end;

                      if not MemComHis.Enabled then
                      begin
                            lDsComple.Text := TblTempDsComHis.AsString;
                      end;

                      if  BntDebito.Enabled then
                      begin
                            DMCTB.LancarContabilidade(lNrLancto,
                                                      gNrPlano,
                                                      EdtCdEmpres.BrAsInteger,
                                                      EdtDtLancto.BrAsDate,
                                                      TblTempNrConta.AsInteger,
                                                      EdtNrConCre.BrAsInteger,
                                                      TblTempVRLANCTO.AsFloat,
                                                      EdtNrDocto.Text,
                                                      lCdHistor,
                                                      lDsComple,
                                                      Name, 'N',
                                                      EdtCdCeCuCr.BrAsInteger,
                                                      EdtCdCeCuDe.BrAsInteger,
                                                      TblTempNrClassi.AsString,
                                                      EdtMasConCre.Text);
                      end else
                      begin
                            DMCTB.LancarContabilidade(lNrLancto,
                                                      gNrPlano,
                                                      EdtCdEmpres.BrAsInteger,
                                                      EdtDtLancto.BrAsDate,
                                                      EdtNrConDeb.BrAsInteger,
                                                      TblTempNrConta.AsInteger,
                                                      TblTempVrLancto.AsFloat,
                                                      EdtNrDocto.Text,
                                                      lCdHistor,
                                                      lDsComple,
                                                      Name, 'N',
                                                      EdtCdCeCuCr.BrAsInteger,
                                                      EdtCdCeCuDe.BrAsInteger,
                                                      EdtMasConDeb.Text,
                                                      TblTempNrClassi.AsString);
                      end;

                      TblTemp.Next;
                      Inc(lNrLancto);
                end;
          end;

          DMCTB.FixarContabilidade(gNrPlano, Name);
      finally
         FreeAndNil(lDsComple)
      end;
end;

procedure TMov0004.BtnAbrConClick(Sender: TObject);
begin
      if EdtDsEmpres.Text = '' then
      begin
           raise Exception.Create('Informe a empresa');
      end;

      if not EdtDtLancto.BrDataValida then
      begin
            raise Exception.Create('Data de lançamento inválida');
      end;

      gNrPlano       := DmCtb.PlanoContasEmpresa(EdtCdEmpres.BrAsInteger, EdtDtLancto.BrAsDate);

      CdsPlaCon.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(77,
                                               '<%NrPlano%>;' + IntToStr(gNrPlano), Name);

      EdtMasConDeb.EditMask    := CdsPlaCon.FieldByName('DsMasCla').AsString + ';1; ';
      EdtMasConCre.EditMask    := EdtMasConDeb.EditMask;
      TblTempNrClassi.EditMask := EdtMasConDeb.EditMask;
      gDsMasLim                := EdtMasConDeb.Text;

      EdtMasConDeb.Width := (Length(CdsPlaCon.FieldByName('DsMasCla').AsString) * 8) + 5;
      EdtMasConCre.Width := EdtMasConDeb.Width;

      EdtCdEmpres.ReadOnly := True;
      EdtDtLancto.ReadOnly := True;
      BtnFecCon.Enabled    := True;
      PnlFundo.Visible     := True;

      PnlCabeca.Enabled    := False;
end;

procedure TMov0004.BtnFecConClick(Sender: TObject);
var lNrFormul : Integer;
begin
      LimparFormulario;
      PnlFundo.Visible     :=  False;
      EdtCdEmpres.ReadOnly :=  False;
      EdtDtLancto.ReadOnly :=  False;
      PnlCabeca.Enabled    :=  True;
      BtnFecCon.Enabled    :=  False;

      for lNrFormul := 0 to (FrmLogos.MdiChildCount - 1) do
      begin
            if DmSis.FormularioAberto('Con0010', lNrFormul) then
            begin
                  FreeAndNil(Con0010);
            end;
      end;
end;

procedure TMov0004.LimparFormulario;
begin
      EdtNrConDeb.BrAsInteger := 0;
      EdtMasConDeb.Text       := '';
      EdtNrConCre.BrAsInteger := 0;
      EdtMasConCre.Text       := '';
      EdtNrDocto.Text         := '';
      EdtVrLancto.BrAsFloat   := 0;
      EdtCdHistor.BrAsInteger := 0;
      EdtCdCeCuDe.BrAsInteger := 0;
      EdtCdCeCuCr.BrAsInteger := 0;

      LblDsConDeb.Text        := '';
      LblDsConCre.Text        := '';
      LblDsHistor.Text        := '';

      MemComHis.Lines.Clear;

      EdtNrConDeb.Enabled     := True;
      EdtMasConDeb.Enabled    := True;
      BntLimDep.Enabled       := True;
      EdtNrConCre.Enabled     := True;
      EdtMasConCre.Enabled    := True;
      BntLimCre.Enabled       := True;
      EdtNrDocto.Enabled      := True;
      EdtVrLancto.Enabled     := True;
      EdtCdHistor.Enabled     := True;

      BntDebito.Enabled       := True;
      BntCredit.Enabled       := True;
      BntGravar.Enabled       := True;

      TblTemp.EmptyDataSet;
end;

procedure TMov0004.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TMov0004.EdtNrConCreBrOnBeforeConsulta(Sender: TObject);
begin
      DmCtb.ConsultarPlanoContas(gNrPlano, 1, EdtMasConCre, EdtNrConCre, False, nil, nil,
                                 EdtCdEmpres.BrAsInteger);
end;

procedure TMov0004.EdtNrConCreDragDrop(Sender, Source: TObject; X, Y: Integer);
begin
      if Con0010.TrvPlano.Selected <> nil then
      begin
            BntLimCreClick(nil);
            EdtMasConCre.Text := PString(Con0010.TrvPlano.Selected.Data)^;
            EdtNrConCreExit(nil);
      end;
end;

procedure TMov0004.EdtNrConCreExit(Sender: TObject);
var  lNrConta  : Integer;
     lNrClassi : String;
     lNmConta  : String;
begin
      if (EdtNrConCre.BrAsInteger <> 0) or (EdtMasConCre.Text <> gDsMasLim) then
      begin
            lNrConta  := EdtNrConCre.BrAsInteger;
            lNrClassi := EdtMasConCre.Text;

            try
                DmCtb.PesquisarConta(lNrConta, lNrClassi, lNmConta, gNrPlano, gDsMasLim);
            finally
                LblDsConCre.Text        := lNmConta;
                EdtNrConCre.BrAsInteger := lNrConta;
                EdtMasConCre.Text       := lNrClassi;
            end;
      end;
end;

procedure TMov0004.EdtNrConDebBrOnBeforeConsulta(Sender: TObject);
begin
      DmCtb.ConsultarPlanoContas(gNrPlano, 1, EdtMasConDeb, EdtNrConDeb, False, nil, nil,
                                 EdtCdEmpres.BrAsInteger);
end;

procedure TMov0004.EdtNrConDebDragDrop(Sender, Source: TObject; X, Y: Integer);
begin
      if Con0010.TrvPlano.Selected <> nil then
      begin
            BntLimDepClick(nil);
            EdtMasConDeb.Text := PString(Con0010.TrvPlano.Selected.Data)^;
            EdtNrConDebExit(nil);
      end;
end;

procedure TMov0004.EdtNrConDebDragOver(Sender, Source: TObject; X, Y: Integer;
  State: TDragState; var Accept: Boolean);
var  Node: TTreeNode;
begin
      Node   := Con0010.TrvPlano.GetNodeAt(X, Y);

      Accept := (Source <> Sender)           and (Node <> nil)  and
                (Node.Parent <> Con0010.TrvPlano.Selected) and
                (Node <> Con0010.TrvPlano.Selected);
end;

procedure TMov0004.EdtNrConDebExit(Sender: TObject);
var  lNrConta  : Integer;
     lNrClassi : String;
     lNmConta  : String;
begin
      if (EdtNrConDeb.BrAsInteger <> 0) or (EdtMasConDeb.Text <> gDsMasLim) then
      begin
            lNrConta    :=  EdtNrConDeb.BrAsInteger;
            lNrClassi   :=  EdtMasConDeb.Text;

            try
                DmCtb.PesquisarConta(lNrConta, lNrClassi, lNmConta, gNrPlano, gDsMasLim);
            finally
                LblDsConDeb.Text        :=  lNmConta;
                EdtNrConDeb.BrAsInteger :=  lNrConta;
                EdtMasConDeb.Text       :=  lNrClassi;
            end;
      end;
end;

procedure TMov0004.FormCreate(Sender: TObject);
begin
      inherited;
      TblTemp.IndexDefs.Clear;
      TblTemp.IndexDefs.Add('PlaLancto_key1', 'NRCLASSI', [ixPrimary, ixUnique]);
      TblTemp.CreateDataSet;

      TblTemp.IndexName := 'PlaLancto_key1';
      TblTemp.SetKey;
end;

initialization
      RegisterClass(TMov0004);

finalization
      UnRegisterClass(TMov0004);

end.
