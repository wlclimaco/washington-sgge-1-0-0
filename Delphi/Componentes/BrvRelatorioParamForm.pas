unit BrvRelatorioParamForm;

interface

uses Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, StdCtrls,
     CheckLst, ExtCtrls, Buttons, Db, DBTables, SqlExpr, BrvDataSet, BrvConnection,
     FMTBcd, BrvClientDataSet, BrvDicionario, DBClient, Provider, Math, BrvBitBtn,
  BrvProvider;

type
  TRelParame = class(TForm)
    PnlFundo: TPanel;
    GbxColunas: TGroupBox;
    ClbColuna: TCheckListBox;
    GbxLayout: TGroupBox;
    CbxLayout: TComboBox;
    GbxPosica: TGroupBox;
    SbtAcima: TSpeedButton;
    SbtAbaixo: TSpeedButton;
    GbxMarcar: TGroupBox;
    SbtTodos: TSpeedButton;
    SbtNenhum: TSpeedButton;
    RgbFolha: TRadioGroup;
    GbxTitulo: TGroupBox;
    EdtDsTitulo: TEdit;
    GbxObserv: TGroupBox;
    MemObserv: TMemo;
    SbtOk: TBrvBitBtn;
    SbtCancel: TBrvBitBtn;
    SbtDelLay: TSpeedButton;
    RgbImpres: TRadioGroup;
    CcConfRelUsr: TBrvClientDataSet;
    CcConfColRelUsr: TBrvClientDataSet;
    procedure SbtTodosClick(Sender: TObject);
    procedure SbtNenhumClick(Sender: TObject);
    procedure SbtAcimaClick(Sender: TObject);
    procedure SbtAbaixoClick(Sender: TObject);
    procedure SbtOkClick(Sender: TObject);
    procedure FormKeyUp(Sender: TObject; var Key: Word;
      Shift: TShiftState);
    procedure CbxLayoutChange(Sender: TObject);
    procedure ClbColunaClick(Sender: TObject);
    procedure SbtDelLayClick(Sender: TObject);
    procedure EdtDsTituloKeyPress(Sender: TObject; var Key: Char);
    procedure FormCloseQuery(Sender: TObject; var CanClose: Boolean);
    procedure RgbImpresClick(Sender: TObject);
  private
    { Private declarations }
    StlTpFolha  : TStrings;
    StlDsTitulo : TStrings;
    StlTpImpres : TStrings;
    NmTabAtu    : String;
    SnModifi    : Boolean;
    FDbConnec   : TBrvConnection;
    FVsDicion   : TBrvDicionario;

    function  ConsisteRelatorio: Boolean;
    procedure CarregarConfiguracaoUsuario(pNmTabela : String);
    procedure CarregarColunasDefault;
    procedure CarregarColunasUsuario;
    procedure VerificarColunasNaoConfiguradas;
    procedure SetModificado(Value : Boolean);
    procedure GravarConfiguracoesUsuario;
    procedure AbrirTabelasParaAtualizacoes(NmLayOut : String);
    procedure ExcluirConfiguracaoAnterior;
    function  NomeNovoLayout(NmLayOut : String) : String;
    function  ColunaVisualizada(NmColuna : String) : Boolean;
    function  ColunaExiste(NmColuna : String) : Boolean;
    procedure NumeroTransactionID(var DsTrnAux : TTransactionDesc);
    procedure AbrirCcConfColRelUsr(CdUsuari : Integer; NmTabAtu : string;
                                                    DsLayRel : string);
    procedure AbrirCcConfRelUsr(CdUsuari : Integer; NmTabAtu : string;
                                                    DsLayRel : string);




  public
    { Public declarations }
    DtsAtual    : TDataSet;
    CdUsuari    : Integer;
    StlNmCampos : TStrings;
    StlTmCampos : TStrings;
    procedure CarregarColunasCheckListBox(DbConnec : TBrvConnection;
                                          VsDicion : TBrvDicionario;
                                          DsTitulo : String);
  end;

var
  RelParame: TRelParame;

implementation

uses BrvRelatorio;

{$R *.DFM}

procedure TRelParame.SbtTodosClick(Sender: TObject);
var NrColuna : Integer;
begin
      for NrColuna := 0 to ClbColuna.Items.Count - 1 do
      begin
            ClbColuna.Checked[NrColuna] := True;
      end;

      SetModificado(True);
end;

procedure TRelParame.SbtNenhumClick(Sender: TObject);
var NrColuna : Integer;
begin
      for NrColuna := 0 to ClbColuna.Items.Count - 1 do
      begin
            ClbColuna.Checked[NrColuna] := False;
      end;

      SetModificado(True);
end;

procedure TRelParame.SbtAcimaClick(Sender: TObject);
var VrLinha  : String;
    SnChecke : Boolean;
begin
      if (ClbColuna.ItemIndex  >  0) and (ClbColuna.ItemIndex <> -1) then
      begin
            VrLinha   := ClbColuna.Items[ClbColuna.ItemIndex - 1];
            SnChecke  := ClbColuna.Checked[ClbColuna.ItemIndex - 1];

            ClbColuna.Items[ClbColuna.ItemIndex - 1]   :=
                                         ClbColuna.Items[ClbColuna.ItemIndex];
            ClbColuna.Checked[ClbColuna.ItemIndex - 1] :=
                                         ClbColuna.Checked[ClbColuna.ItemIndex];
            ClbColuna.Items[ClbColuna.ItemIndex]       := VrLinha;
            ClbColuna.Checked[ClbColuna.ItemIndex]     := SnChecke;


            VrLinha := StlNmCampos.Strings[ClbColuna.ItemIndex - 1];
            StlNmCampos.Strings[ClbColuna.ItemIndex - 1]  :=
                              StlNmCampos.Strings[ClbColuna.ItemIndex];
            StlNmCampos.Strings[ClbColuna.ItemIndex]      := VrLinha;


            VrLinha := StlTmCampos.Strings[ClbColuna.ItemIndex - 1];
            StlTmCampos.Strings[ClbColuna.ItemIndex - 1]  :=
                              StlTmCampos.Strings[ClbColuna.ItemIndex];
            StlTmCampos.Strings[ClbColuna.ItemIndex]      := VrLinha;

            ClbColuna.ItemIndex := ClbColuna.ItemIndex - 1;

            SetModificado(True);
      end;
end;

procedure TRelParame.SbtAbaixoClick(Sender: TObject);
var VrLinha  : String;
    SnChecke : Boolean;
begin
      if ((ClbColuna.ItemIndex + 1) <  ClbColuna.Items.Count) and
         ( ClbColuna.ItemIndex      <> -1) then
      begin
            VrLinha := ClbColuna.Items[ClbColuna.ItemIndex + 1];
            SnChecke := ClbColuna.Checked[ClbColuna.ItemIndex + 1];

            ClbColuna.Items[ClbColuna.ItemIndex + 1]   :=
                                         ClbColuna.Items[ClbColuna.ItemIndex];
            ClbColuna.Checked[ClbColuna.ItemIndex + 1] :=
                                         ClbColuna.Checked[ClbColuna.ItemIndex];
            ClbColuna.Items[ClbColuna.ItemIndex]       := VrLinha;
            ClbColuna.Checked[ClbColuna.ItemIndex]     := SnChecke;


            VrLinha := StlNmCampos.Strings[ClbColuna.ItemIndex + 1];
            StlNmCampos.Strings[ClbColuna.ItemIndex + 1] :=
                              StlNmCampos.Strings[ClbColuna.ItemIndex];
            StlNmCampos.Strings[ClbColuna.ItemIndex]     := VrLinha;


            VrLinha := StlTmCampos.Strings[ClbColuna.ItemIndex + 1];
            StlTmCampos.Strings[ClbColuna.ItemIndex + 1] :=
                              StlTmCampos.Strings[ClbColuna.ItemIndex];
            StlTmCampos.Strings[ClbColuna.ItemIndex]     := VrLinha;

            ClbColuna.ItemIndex := ClbColuna.ItemIndex + 1;
            
            SetModificado(True);
      end;
end;

procedure TRelParame.FormKeyUp(Sender: TObject; var Key: Word; Shift: TShiftState);
begin
      case key of
           27 : Close;
      end;
end;

procedure TRelParame.CarregarColunasCheckListBox(DbConnec  : TBrvConnection;
                                                 VsDicion  : TBrvDicionario;
                                                 DsTitulo  : String);
begin
      FDbConnec := DbConnec;
      FVsDicion := VsDicion;

      CarregarConfiguracaoUsuario(DsTitulo);
      CbxLayoutChange(nil);
end;

procedure TRelParame.CarregarConfiguracaoUsuario(pNmTabela : String);
var CPConUsu : TClientDataSet;
    lDsSql   : String;
begin
      NmTabAtu      := pNmTabela;
//      Delete(NmTabAtu, 1, 3);
//      Delete(NmTabAtu, Pos('-', NmTabAtu) -1, Length(NmTabAtu));
//      NmTabAtu  := Trim(NmTabAtu);

      try
          StlTpFolha   :=  TStringList.Create;
          StlDsTitulo  :=  TStringList.Create;
          StlTpImpres  :=  TStringList.Create;
          CPConUsu     := TClientDataSet.Create(nil);
          lDsSql       := ' Select DsLayRel, TpFolRel, DsTitRel, TpImpres '     +
                          ' From   S023 '                              +
                          ' Where CdUsuari = ' + IntToStr(CdUsuari)    + ' And' +
                          '       NmTabela = ' + #39 + NmTabAtu        + #39 +
                          ' Order by DsLayRel';

          CPConUsu.Data := FVsDicion.RetornaDadosSqlFixa(lDsSql);

          CbxLayout.Items.Clear;

          while not CPConUsu.Eof do
          begin
                CbxLayout.Items.Add(CPConUsu.FieldByName('DsLayRel').AsString);
                StlTpFolha.Add(CPConUsu.FieldByName('TpFolRel').AsString);
                StlDsTitulo.Add(CPConUsu.FieldByName('DsTitRel').AsString);
                StlTpImpres.Add(CPConUsu.FieldByName('TpImpres').AsString);

                CPConUsu.Next;
          end;

          CbxLayout.Items.Add('DEFAULT');
          StlTpFolha.Add('R');
          StlDsTitulo.Add('Relatório de ' + pNmTabela);
          StlTpImpres.Add('G');

          CbxLayout.ItemIndex := 0;
      finally
          CPConUsu.Close;
          FreeAndNil(CPConUsu);
      end;
end;

procedure TRelParame.CbxLayoutChange(Sender: TObject);
var SnModAnt : Boolean;
begin
      ClbColuna.Items.Clear;
      StlNmCampos.Clear;
      StlTmCampos.Clear;

      SetModificado(False);

      if  CbxLayout.Text = 'DEFAULT' then
      begin
            CarregarColunasDefault;
      end else
      begin
            CarregarColunasUsuario;
      end;

      if  ClbColuna.Items.Count >= 0 then
      begin
            SnModAnt := SnModifi;

            EdtDsTitulo.Text := StlDsTitulo.Strings[CbxLayout.ItemIndex];

            if  StlTpImpres.Strings[CbxLayout.ItemIndex] = 'G' then
            begin // Grafica
                  RgbImpres.ItemIndex := 0;
            end else
            begin // Caracter
                  RgbImpres.ItemIndex := 1;
            end;

            if  StlTpFolha.Strings[CbxLayout.ItemIndex] = 'R' then
            begin
                  RgbFolha.ItemIndex := 0;
            end else
            begin
                  RgbFolha.ItemIndex := 1;
            end;

            SetModificado(SnModAnt);
      end;
end;

procedure TRelParame.CarregarColunasDefault;
var NrColuna : Word;
    NrIndClb : Word;
begin
      NrIndClb := 0;

      for NrColuna := 0 to DtsAtual.FieldCount -1 do
      begin
            if DtsAtual.Fields[NrColuna].Visible then
            begin
                  if DtsAtual.Fields[NrColuna] is TGraphicField then
                  begin
                     {}
                  end else
                  begin
                        ClbColuna.Items.Add(DtsAtual.Fields[NrColuna].DisplayLabel);

                        ClbColuna.Checked[NrIndClb] := True;

                        StlNmCampos.Add(DtsAtual.Fields[NrColuna].FieldName);

                        StlTmCampos.Add(IntToStr(DtsAtual.Fields[NrColuna].DisplayWidth));

                        Inc(NrIndClb);
                  end;
            end;
      end;
end;

procedure TRelParame.CarregarColunasUsuario;
begin
      AbrirCcConfColRelUsr(CdUsuari, NmTabAtu, CbxLayout.Text);

      while not CcConfColRelUsr.Eof do
      begin
            if ColunaExiste(CcConfColRelUsr.FieldByName('NmAtribu').AsString) then
            begin
                  if  DtsAtual.FieldByName(
                           CcConfColRelUsr.FieldByName('NmAtribu').AsString).Visible then
                  begin
                        if DtsAtual.FieldByName(
                           CcConfColRelUsr.FieldByName('NmAtribu').AsString) is
                                                                        TGraphicField then
                        begin
                              {}
                        end else
                        begin
                              ClbColuna.Items.Add(DtsAtual.FieldByName(
                               CcConfColRelUsr.FieldByName('NmAtribu').AsString).DisplayName);

                              if CcConfColRelUsr.FieldByName('SnColSel').AsString = 'S' then
                              begin
                                    try
                                        ClbColuna.Checked[
                                         CcConfColRelUsr.FieldByName('NrOrdem').AsInteger] :=
                                                                                     True;
                                    except
                                         //-----
                                    end;
                              end;

                              StlNmCampos.Add(
                                            CcConfColRelUsr.FieldByName('NmAtribu').AsString);

                              StlTmCampos.Add(IntToStr(DtsAtual.FieldByName
                                      (CcConfColRelUsr.FieldByName('NmAtribu').AsString).
                                                                           DisplayWidth));
                        end;
                  end;
            end else
            begin
                  SetModificado(True);
            end;

            CcConfColRelUsr.Next;
      end;

      CcConfColRelUsr.Close;

      VerificarColunasNaoConfiguradas;
end;

function  TRelParame.ColunaExiste(NmColuna : String) : Boolean;
var NrColuna : Integer;
begin
      Result   := False;
      NrColuna := 0;

      while (not Result) and (NrColuna < DtsAtual.FieldCount) do
      begin
            if DtsAtual.Fields[NrColuna].FieldName = NmColuna then
            begin
                  Result := True;
            end;

            inc(NrColuna);
      end;
end;

procedure TRelParame.VerificarColunasNaoConfiguradas;
var NrColuna : Integer;
    NrColChe : Integer;
begin
      NrColChe := 0;
      for NrColuna := 0 to DtsAtual.FieldCount -1 do
      begin
            if DtsAtual.Fields[NrColuna].Visible then
            begin
                  if DtsAtual.Fields[NrColuna] is TGraphicField then
                  begin
                     {}
                  end else
                  begin
                        if not ColunaVisualizada(
                                       DtsAtual.Fields[NrColuna].FieldName) then
                        begin
                              ClbColuna.Items.Add(
                                         DtsAtual.Fields[NrColuna].DisplayName);

                              ClbColuna.Checked[NrColChe] := True;

                              StlNmCampos.Add(
                                           DtsAtual.Fields[NrColuna].FieldName);

                              StlTmCampos.Add(IntToStr(
                                       DtsAtual.Fields[NrColuna].DisplayWidth));

                              SetModificado(True);
                        end;
                  end;
                  Inc(NrColChe);
            end;
      end;
end;

function  TRelParame.ColunaVisualizada(NmColuna : String) : Boolean;
var NrColuna : Integer;
begin
      Result   := False;
      NrColuna := 0;

      while (not Result) and (NrColuna < StlNmCampos.Count) do
      begin
            if  NmColuna = StlNmCampos.Strings[NrColuna] then
            begin
                  Result := True;
            end;

            inc(NrColuna);
      end;
end;

procedure TRelParame.RgbImpresClick(Sender: TObject);
var NrIndex  : Byte;
begin
      NrIndex := RgbFolha.ItemIndex;

      RgbFolha.Items.Clear;

      if  RgbImpres.ItemIndex = 0 then
      begin
            RgbFolha.Items.Add('Retrato');
            RgbFolha.Items.Add('Paisagem');
      end else
      begin
            RgbFolha.Items.Add('80 colunas');
            RgbFolha.Items.Add('132 colunas');
      end;

      RgbFolha.ItemIndex := NrIndex;
end;

procedure TRelParame.ClbColunaClick(Sender: TObject);
begin
      SetModificado(True);
end;

procedure TRelParame.EdtDsTituloKeyPress(Sender: TObject; var Key: Char);
begin
      SetModificado(True);
end;

procedure TRelParame.SetModificado(Value : Boolean);
begin
      SnModifi := Value;
end;

procedure TRelParame.SbtOkClick(Sender: TObject);
begin
      if ConsisteRelatorio then
      begin
            if  (SnModifi)  then
            begin
                  if  MessageDlg('A configurações do relatório foram '    +
                                 'alteradas.' + #13#13 + 'Deseja gravar ' +
                                 'alterações ?', MtConfirmation,
                                 [MbYes,MbNo], 0) = IdYes then
                  begin
                        GravarConfiguracoesUsuario;
                  end;
            end;

            ModalResult := mrOk;
      end else
      begin
            MessageDlg('Nenhuma coluna foi selecionada para impressão.',
                                                            MtError, [mbOk], 0);
      end;
end;

function TRelParame.ConsisteRelatorio: Boolean;
var NrColuna : Integer;
begin
      Result   := False;
      NrColuna := 0;

      while (NrColuna < ClbColuna.Items.Count) and (not Result) do
      begin
            if  ClbColuna.Checked[NrColuna] then
            begin
                  Result := True;
            end;

            inc(NrColuna);
      end;
end;

procedure TRelParame.GravarConfiguracoesUsuario;
var NmLayOut : String;
    NrColuna : Integer;
begin
    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // =-=-=-=- Recebendo o novo nome de LayOut -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      if  CbxLayout.Text = 'DEFAULT' then
      begin
            NmLayOut := '';
      end else
      begin
            NmLayOut := CbxLayout.Text;
      end;

      NmLayOut := NomeNovoLayout(NmLayOut);

      if  NmLayOut <> '!@#$%&* --> ATUALIZACAO CANCELADA <-- !@#$%&*' then
      begin
            AbrirTabelasParaAtualizacoes(NmLayOut);

         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=- Atualizando configurações do cabeçalho =-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            CcConfRelUsr.Append;
            CcConfRelUsr.FieldByName('CdUsuari').AsInteger := CdUsuari;
            CcConfRelUsr.FieldByName('NMTABELA').AsString  := NmTabAtu;
            CcConfRelUsr.FieldByName('DSLAYREL').AsString  := NmLayOut;
            CcConfRelUsr.FieldByName('DSTITREL').AsString  := EdtDsTitulo.Text;

            if  RgbFolha.ItemIndex = 0 then
            begin
                  CcConfRelUsr.FieldByName('TPFOLREL').AsString := 'R';
            end else
            begin
                  CcConfRelUsr.FieldByName('TPFOLREL').AsString := 'P';
            end;

            if  RgbImpres.ItemIndex = 0 then
            begin
                  CcConfRelUsr.FieldByName('TPIMPRES').AsString := 'G';
            end else
            begin
                  CcConfRelUsr.FieldByName('TPIMPRES').AsString := 'C';
            end;

            CcConfRelUsr.Post;

         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=- Atualizando configurações das colunas -=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            for NrColuna := 0 to StlNmCampos.Count - 1 do
            begin
                  CcConfColRelUsr.Append;
                  CcConfColRelUsr.FieldByName('CdUsuari').AsInteger :=  CdUsuari;
                  CcConfColRelUsr.FieldByName('NmTabela').AsString  :=  NmTabAtu;
                  CcConfColRelUsr.FieldByName('DsLayRel').AsString  :=  NmLayOut;
                  CcConfColRelUsr.FieldByName('NrOrdem').AsInteger  :=  NrColuna;
                  CcConfColRelUsr.FieldByName('NmAtribu').AsString  :=
                                                            StlNmCampos.Strings[NrColuna];

                  if  ClbColuna.Checked[NrColuna] then
                  begin
                        CcConfColRelUsr.FieldByName('SnColSel').AsString  := 'S';
                  end else
                  begin
                        CcConfColRelUsr.FieldByName('SnColSel').AsString  := 'N';
                  end;

                  CcConfColRelUsr.Post;
            end;

         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=- Fixando Atualizações no Banco -=-=-=-=-=-=-=-=-=-=-=-=-=-=
         // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

            CcConfRelUsr.ApplyUpdates(0);
            CcConfColRelUsr.ApplyUpdates(0);

            CbxLayout.Items.Insert(0, NmLayOut);
            CbxLayout.ItemIndex := 0;
      end;
end;

function  TRelParame.NomeNovoLayout(NmLayOut : String) : String;
var FrmLayOut   : TForm;
    PnlFundo    : TPanel;
    LblDsLayOut : TLabel;
    EdtDsLayOut : TEdit;
    SbtOkLayout : TBitBtn;
    SbtCaLayOut : TBitBtn;
begin
      FrmLayOut               := TForm.CreateNew(Self);
      FrmLayOut.Position      := poScreenCenter;
      FrmLayOut.Caption       := 'Descrição do Layout';
      FrmLayOut.Width         := 360;
      FrmLayOut.Height        := 130;
      FrmLayOut.BorderStyle   := bsSingle;
      FrmLayOut.BorderIcons   := [biSystemMenu];

      PnlFundo                := TPanel.Create(Self);
      PnlFundo.Parent         := FrmLayOut;
      PnlFundo.Caption        := '';
      PnlFundo.Align          := alClient;
      PnlFundo.BorderStyle    := bsSingle;

      LblDsLayOut             := TLabel.Create(Self);
      LblDsLayOut.Parent      := PnlFundo;
      LblDsLayOut.Caption     := 'Descrição do Layout:';
      LblDsLayOut.Font.Color  := clBlue;
      LblDsLayOut.Font.Style  := [fsBold];
      LblDsLayOut.Left        := 8;
      LblDsLayOut.Top         := 8;

      EdtDsLayOut             := TEdit.Create(Self);
      EdtDsLayOut.Parent      := PnlFundo;
      EdtDsLayOut.MaxLength   := 30;
      EdtDsLayOut.Top         := 24;
      EdtDsLayOut.Left        := 7;
      EdtDsLayOut.Width       := 330;
      EdtDsLayOut.Text        := NmLayOut;
      EdtDsLayOut.CharCase    := ecUpperCase;

      SbtOkLayout             := TBitBtn.Create(Self);
      SbtOkLayout.Parent      := PnlFundo;
      SbtOkLayout.Kind        := BkOk;
      SbtOkLayout.Caption     := '&Ok';
      SbtOkLayout.Top         := 56;
      SbtOkLayout.Left        := 96;

      SbtCaLayOut             := TBitBtn.Create(Self);
      SbtCaLayOut.Parent      := PnlFundo;
      SbtCaLayOut.Kind        := BkCancel;
      SbtCaLayOut.Caption     := '&Cancelar';
      SbtCaLayOut.Top         := 56;
      SbtCaLayOut.Left        := 175;

      Result                  := '';

      repeat
             if  FrmLayOut.ShowModal = MrOk then
             begin
                   if  (EdtDsLayOut.Text = '') or
                       (EdtDsLayOut.Text = 'DEFAULT') then
                   begin
                         MessageDlg('A descrição do layout deve ser informada',
                                    MtError, [mbOk], 0);
                   end else
                   begin
                         Result := EdtDsLayOut.Text;
                   end;
             end else
             begin
                   Result := '!@#$%&* --> ATUALIZACAO CANCELADA <-- !@#$%&*';
             end;
      until Result <> '';

      FrmLayOut.Destroy;
end;

procedure TRelParame.AbrirTabelasParaAtualizacoes(NmLayOut : String);
begin
      if  CbxLayout.Text <> 'DEFAULT' then
      begin
            AbrirCcConfRelUsr(CdUsuari, NmTabAtu, NmLayOut);
            AbrirCcConfColRelUsr(CdUsuari, NmTabAtu, NmLayOut);

            ExcluirConfiguracaoAnterior;
      end else
      begin
            AbrirCcConfRelUsr(-1, ' ', ' ');
            AbrirCcConfColRelUsr(-1, ' ', ' ');
      end;
end;

procedure TRelParame.ExcluirConfiguracaoAnterior;
begin
      while not CcConfColRelUsr.Eof do
      begin
            CcConfColRelUsr.Delete;
      end;

      while not CcConfRelUsr.Eof do
      begin
            CcConfRelUsr.Delete;
      end;

      CcConfColRelUsr.ApplyUpdates(0);
      CcConfRelUsr.ApplyUpdates(0);
end;

procedure TRelParame.SbtDelLayClick(Sender: TObject);
var NrPosAtu : Integer;
begin
      if  CbxLayout.Text <> 'DEFAULT' then
      begin
            if  MessageDlg('Confirma exclusão do layout "' + CbxLayout.Text +
                           '"?', MtConfirmation, [MbYes,MbNo], 0) = IdYes then
            begin
                  NrPosAtu := CbxLayOut.ItemIndex;

                  // aqui mesmo é deletado
                  AbrirTabelasParaAtualizacoes(CbxLayout.Text);

                  CcConfRelUsr.Close;
                  CcConfColRelUsr.Close;

                  CbxLayOut.Items.Delete(NrPosAtu);
                  StlTpFolha.Delete(NrPosAtu);
                  StlDsTitulo.Delete(NrPosAtu);
                  StlTpImpres.Delete(NrPosAtu);

                  CbxLayOut.ItemIndex := 0;
                  CbxLayoutChange(nil);
            end;
      end else
      begin
            MessageDlg('Layout DEFAULT não pode ser removido', MtInformation,
                                                                    [MbOk], 0);
      end;
end;

procedure TRelParame.AbrirCcConfColRelUsr(CdUsuari : Integer; NmTabAtu : string;
                                                           DsLayRel : string);
begin
      CcConfColRelUsr.Close;
      CcConfColRelUsr.CommandText :=
                                   'Select * From S024 '           +
                                   'Where  CdUsuari = ' + IntToStr(CdUsuari)    + ' and '+
                                   '       NmTabela = ' + #39 + NmTabAtu  + #39 + ' and '+
                                   '       DsLayRel = ' + #39 + DsLayRel  + #39 + ' ' +
                                   'Order by NrOrdem';
      CcConfColRelUsr.Open;
end;

procedure TRelParame.AbrirCcConfRelUsr(CdUsuari : Integer; NmTabAtu : string;
                                                           DsLayRel : string);
begin
      CcConfRelUsr.Close;
      CcConfRelUsr.CommandText   :=
                            'Select * From   S023 '            +
                            'Where  CdUsuari = ' + IntToStr(CdUsuari) +       ' and ' +
                            '       NmTabela = ' + #39 + NmTabAtu     + #39 + ' and ' +
                            '       DsLayRel = ' + #39 + DsLayRel     + #39;
      CcConfRelUsr.Open;
end;

procedure TRelParame.NumeroTransactionID(var DsTrnAux : TTransactionDesc);
begin
      Randomize();
      DsTrnAux.TransactionID  := RandomRange(1, 1000);

      Randomize();
      DsTrnAux.GlobalID       := RandomRange(1, 1000);

      DsTrnAux.IsolationLevel := xilREADCOMMITTED;
end;


procedure TRelParame.FormCloseQuery(Sender: TObject;
  var CanClose: Boolean);
begin
      StlTpFolha.Destroy;
      StlDsTitulo.Destroy;
      StlTpImpres.Destroy;
end;


end.
