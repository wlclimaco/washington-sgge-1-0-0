unit BrvFuncoesXE;

interface

uses  SysUtils, Forms, Classes, Dialogs, Windows, ComCtrls, DBClient, DB,
      Variants, BrvExcel, BrvClientDataSet, Graphics, CheckLst
;

procedure StatusMSG(StbBarra : TStatusBar; Mensagem : String); Overload;
procedure StatusMSG(StbBarra : TStatusBar; Mensagem : String; Painel : Integer); Overload;
procedure MensagemErro(Mensagem : String);
procedure FormataCamposDecimais(CdsArg : TClientDataSet);

procedure RetornaListaEmpresas(pClbLista : TCheckListBox);

function  RetornaSelecionados(CdsArg : TClientDataSet; pCampo : String): WideString;
procedure AjustaForm(pForm : TForm);

procedure ExportaBrvExcel(pCdsData : OleVariant);
function  DlgSelecionarPasta(pTitulo : String): WideString;

function RetornaDominios(pTabela, pCampo : String ): WideString;

function MinutosEmHoras(pMinutos: Integer): String;

function UltimoDiaDoMes(pDtMesAno: String): Integer;

procedure StrToClientDataSet(pLsDados : String; pCcDataTmp: TClientDataSet;
                             pLsTipDad: Array of TFieldType);

function ClientDataSetToStr(CcDataTmp: TClientDataSet): String;

function RemoveCaracterEspecial(pStTexto: String): boolean;

implementation

uses ULogos, UDmSrvApl;

function RemoveCaracterEspecial(pStTexto: String): boolean;
Const
      ComAcento = '‡‚ÍÙ˚„ı·ÈÌÛ˙Á¸¿¬ ‘€√’¡…Õ”⁄«‹@#$%®&*()}{[][~^';
Var
      x : Integer;
Begin
      result := true;
      For x := 1 to Length(pStTexto) do
      if Pos(pStTexto[x],ComAcento)<>0 Then
      begin
            Result := false;
      end;

end;

function UltimoDiaDoMes(pDtMesAno: String): Integer;
var lNrMes: String;
    lNrAno: String;
begin
      lNrMes := Copy(pDtMesAno, 1, 2);
      lNrAno := Copy(pDtMesAno, 4, 4);

      if Pos(lNrMes, '01 03 05 07 08 10 12') > 0 then
      begin
            UltimoDiaDoMes := 31;
      end else
      begin
            if lNrMes <> '02' then
            begin
                  UltimoDiaDoMes := 30;
            end else
            begin
                if (StrToInt(lNrAno) mod 4) = 0 then
                begin
                      UltimoDiaDoMes := 29;
                end else
                begin
                      UltimoDiaDoMes := 28;
                end;
            end;
      end;
end;

procedure StatusMSG(StbBarra : TStatusBar; Mensagem : String);
begin
      if StbBarra <> nil then
      begin
            StbBarra.Panels[0].Text := Mensagem;
            Application.ProcessMessages;
      end;
end;

procedure StatusMSG(StbBarra : TStatusBar; Mensagem : String; Painel : Integer); Overload;
begin
      if StbBarra <> nil then
      begin
            StbBarra.Panels[Painel].Text := Mensagem;
            Application.ProcessMessages;
      end;
end;

function ClientDataSetToStr(CcDataTmp: TClientDataSet): String;
var nrindice : Integer;
    dstxtpro : string;
    DsLista  : TStrings;
begin
      try
          CcDataTmp.DisableControls;
          CcDataTmp.First;
          Result   := '';
          dstxtpro := '';
          DsLista  := TStringList.Create;

          for nrindice := 0 to CcDataTmp.Fields.Count -1 do
          begin
                dstxtpro := dstxtpro + UpperCase(CcDataTmp.Fields[nrindice].FieldName) + ';';
          end;

          DsLista.Add(copy(dstxtpro, 1, Length(dstxtpro)-1));

          while not CcDataTmp.Eof do
          begin
                dstxtpro := '';
                for nrindice := 0 to CcDataTmp.Fields.Count -1 do
                begin
                      dstxtpro := dstxtpro + StringReplace(UpperCase(
                                                     CcDataTmp.Fields[nrindice].AsString), ',', '.',
                                                                             [rfReplaceAll]) + ';';
                end;

                DsLista.Add(copy(dstxtpro, 1, Length(dstxtpro)-1));
                CcDataTmp.Next;
          end;

          CcDataTmp.First;
          Result := DsLista.Text;
      finally
          FreeAndNil(DsLista);
          CcDataTmp.EnableControls;
      end;
end;

procedure StrToClientDataSet(pLsDados : String; pCcDataTmp: TClientDataSet;
                             pLsTipDad: Array of TFieldType);
var lNrIndice : Integer;
    lNrIdxCmp : Integer;
    lDsTxtPro : String;
    lLsDados  : TStrings;
begin
      lLsDados := TStringList.Create;
      lLsDados.Text := pLsDados;

      if (pCcDataTmp.Active) then
      begin
            pCcDataTmp.EmptyDataSet;
            pCcDataTmp.Close;
            pCcDataTmp.Fields.Clear;
            pCcDataTmp.FieldDefs.Clear;
      end;

      lDsTxtPro := lLsDados.Strings[0] + ';';
      lNrIndice := 0;

      while (lDsTxtPro <> '') do
      begin
            if (High(pLsTipDad) > 0) then
            begin
                  if (pLsTipDad[lNrIndice] = ftString) then
                  begin
                        pCcDataTmp.FieldDefs.Add(copy(lDsTxtPro, 1, Pos(';', lDsTxtPro) -1),
                                                                         pLsTipDad[lNrIndice], 150);
                  end else
                  begin
                        pCcDataTmp.FieldDefs.Add(copy(lDsTxtPro, 1, Pos(';', lDsTxtPro) -1),
                                                                         pLsTipDad[lNrIndice], 0);
                  end;
            end else
            begin
                  pCcDataTmp.FieldDefs.Add(copy(lDsTxtPro, 1, Pos(';', lDsTxtPro) -1),
                                                                                     ftString, 150);
            end;

            lDsTxtPro := copy(lDsTxtPro, Pos(';', lDstxtpro) + 1, Length(lDsTxtPro));
            Inc(lNrIndice);
      end;

      pCcDataTmp.CreateDataSet;
      pCcDataTmp.Open;
      for lNrIndice := 1 to lLsDados.Count -1 do
      begin
            if RemoveCaracterEspecial(lLsDados.Strings[lNrIndice]) then
            begin
            lDsTxtPro := lLsDados.Strings[lNrIndice] + ';';
            lNrIdxCmp := 0;
            pCcDataTmp.Append;
            while (Trim(lDsTxtPro) <> '') do
            begin
                  pCcDataTmp.Fields[lNrIdxcmp].AsString := StringReplace(copy(lDsTxtPro, 1,
                                            Pos(';', lDsTxtPro)-1), '.', ',', [rfReplaceAll]);
                  lDsTxtPro := copy(lDsTxtPro, Pos(';', lDsTxtPro) + 1, Length(lDsTxtPro));
                  Inc(lNrIdxCmp);
            end;
            pCcDataTmp.Post;
            end;
      end;

end;
procedure AjustaForm(pForm : TForm);
var TamA, TamL : Integer;
begin
      { Coloca a janela filha dentro da janela pai quando ela È aberta }
      TamA := FrmLogos.ClientHeight;
      TamL := FrmLogos.ClientWidth;
      pForm.Top    := 0;
      pForm.Left   := 0;     // valores corretos comentados
      pForm.Width  := TamL - 04;//04;  // Largura
      pForm.Height := TamA - FrmLogos.StbRodape.Height - 4//78; // Altura
end;

function MinutosEmHoras(pMinutos: Integer): String;
begin
      Result   := Copy(FormatFloat('000.00', pMinutos / 60),1,3);
      Result   := Result + ':' + FormatFloat('00', pMinutos - (StrToInt(Result) * 60));
end;


procedure MensagemErro(Mensagem : String);
begin
      MessageDlg(Mensagem, mtError, [mbOk], 0);
end;

procedure FormataCamposDecimais(CdsArg : TClientDataSet);
var lNrIndice   : Integer;
    lLocalField : TField;
    lTipoField  : TFieldType;
    lNmCampo    : String;
begin
      for lNrIndice := 0 to (CdsArg.Fields.Count - 1) do
      begin
            lLocalField :=  CdsArg.Fields[lNrIndice];
            lTipoField  :=  CdsArg.Fields[lNrIndice].DataType;
            lNmCampo    :=  CdsArg.Fields[lNrIndice].FieldName;


            { Este if serve apenas para DEBUGAR  }
            {
            if lNmCampo  =  'CDMOTORI'then
            begin
                  lNmCampo := lNmCampo;
            end;
            }

            if (lTipoField in [ftFloat, ftBCD, ftCurrency, ftFMTBcd]) and
               (lLocalField.Size > 0)  then
            begin
                  (lLocalField as TNumericField).DisplayFormat  :=  '#,#0.00';
                  (lLocalField as TNumericField).Alignment      :=  taRightJustify;
            end;
      end;
end;

function RetornaSelecionados(CdsArg : TClientDataSet; pCampo : String): WideString;
begin
      Result := '';
      CdsArg.First;
      while not CdsArg.Eof do
      begin
            if CdsArg.FieldByName('SnMarcad').AsString = 'S' then
            begin
                  Result := Result + CdsArg.FieldByName(pCampo).AsString + ',';
            end;
            CdsArg.Next;
      end;
      Delete(Result, Length(Result), 1);
end;

procedure ExportaBrvExcel(pCdsData : OleVariant);
var lColunas      : TStringList;
    lNrIndice     : Integer;
    lCampo        : String;
    lNomeArquivo  : String;
    lCdsDados     : TBrvClientDataSet;
    BrvExcelDados : TBrvExcel;

begin
      lNrIndice := 0;
      lColunas  :=  TStringList.Create;
      try
          lCdsDados               :=  TBrvClientDataSet.Create(nil);
          lCdsDados.BrDicionario  :=  DmSrvApl.BrvDicionario;
          BrvExcelDados           :=  TBrvExcel.Create(Nil);
          lCdsDados.Data          :=  pCdsData;
          BrvExcelDados.BrDataSet :=  lCdsDados;


          for lNrIndice := lNrIndice to lCdsDados.FieldCount - 1 do
          begin
                lCampo  :=  lCdsDados.Fields[lNrIndice].FieldName;
                lColunas.Add(lCampo);
          end;
          BrvExcelDados.Execute(lColunas,
                                DlgSelecionarPasta('Salvar Arquivo Excel'),
                                nil);
      finally
          FreeAndNil(lColunas);
          FreeAndNil(lCdsDados);
          FreeAndNil(BrvExcelDados);
      end;
end;

function DlgSelecionarPasta(pTitulo : String): WideString;
var  lDlgPasta  : TOpenDialog;
begin
      lDlgPasta :=  TOpenDialog.Create(nil);
      try
          lDlgPasta.Title       :=  pTitulo;
          lDlgPasta.DefaultExt  :=  '*.xls';
          lDlgPasta.Filter      :=  'Arquivos Excel XLS (*.XLS)|*.XLS|Arquivos Excel (*.xls)|*.xls|Todos os Arquivos (*.*)|*.*';
          lDlgPasta.Execute();
      finally
          Result                :=  lDlgPasta.FileName;
          lDlgPasta.Free;
      end;
end;

procedure RetornaListaEmpresas(pClbLista : TCheckListBox);
var NrIndice   : Integer;
begin
      pClbLista.Items.Clear;

      for NrIndice := 0 to DmSrvApl.BrvDicionario.CorpCodes.Count - 1 do
      begin

            pClbLista.Items.Add(FormatFloat('000',
                      StrToInt(DmSrvApl.BrvDicionario.CorpCodes.Strings[NrIndice])) +
                           ' ' + DmSrvApl.BrvDicionario.CorpNames.Strings[NrIndice]);


      end;

end;

function RetornaDominios(pTabela, pCampo : String ): WideString;
var DsDomini : String;
    VrDomini : String;
begin
      DmSrvApl.BrvDicionario.CarregarDominiosDoAtributo(pTabela, pCampo, VrDomini, DsDomini);
      Result   := DsDomini;
end;


end.
