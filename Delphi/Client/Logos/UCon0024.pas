unit UCon0024;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, StdCtrls, ExtCtrls, BrvDbGrids, BrvDbGrid, Grids, BrvCustomEdit, BrvEditNum,
  BrvRetCon, Mask, BrvCustomMaskEdit, BrvEditDate, Buttons, BrvBitBtn, ComCtrls, BrvListParam,
  ImgList, Menus, BrvSpeedButton, BrvDbNavCop, BrvEdit, DB, IdMessage, IdBaseComponent, IdComponent,
  IdTCPConnection, IdTCPClient, IdExplicitTLSClientServerBase, IdMessageClient, IdSMTPBase, IdSMTP,
  BrvServerProgress, DBClient, BrvClientDataSet, BrvString, IdAttachmentFile;

type
  TCon0024 = class(TMov0000)
    PgcFundo: TPageControl;
    TbsFiltro: TTabSheet;
    Panel1: TPanel;
    Panel4: TPanel;
    BtnPesquisar: TBrvBitBtn;
    PnlFiltros: TPanel;
    LblCdEmpres: TLabel;
    LblDtInicia: TLabel;
    LblDtFinal: TLabel;
    LblNrDocume: TLabel;
    EdtDtFinal: TBrvEditDate;
    EdtDtInicia: TBrvEditDate;
    LblDsEmpres: TBrvRetCon;
    EdtNrDocume: TBrvEditNum;
    EdtCdEmpres: TBrvEditNum;
    TbsConsulta: TTabSheet;
    RdgTpXML: TRadioGroup;
    LblTpXML: TLabel;
    LblCdTitula: TLabel;
    EdtCdTitula: TBrvEditNum;
    LblRsTitula: TBrvRetCon;
    Panel5: TPanel;
    Splitter1: TSplitter;
    Panel6: TPanel;
    BtnVoltar: TBrvBitBtn;
    Panel3: TPanel;
    Panel9: TPanel;
    BtnEnviar: TBrvBitBtn;
    Label4: TLabel;
    BtnAdicionar: TBrvBitBtn;
    EdtDsEmail: TBrvEdit;
    DsT002: TDataSource;
    CpT002: TBrvClientDataSet;
    BrvServerProgress: TBrvServerProgress;
    BrvListParam: TBrvListParam;
    SMTP: TIdSMTP;
    IdMsgSend: TIdMessage;
    CpEmails: TClientDataSet;
    CpF004: TBrvClientDataSet;
    DsF004: TDataSource;
    LblQtReg: TLabel;
    DsEmails: TDataSource;
    CpEmailsDsEmail: TStringField;
    BrvString: TBrvString;
    BtnRemover: TBrvBitBtn;
    BtnListar: TBrvBitBtn;
    popMarcar: TPopupMenu;
    MarcarTodos1: TMenuItem;
    DesmarcarTodos1: TMenuItem;
    LblTxMsgEma: TLabel;
    Splitter2: TSplitter;
    DbgNotas: TBrvDbGrid;
    DbgEmails: TBrvDbGrid;
    StgFiltros: TStringGrid;
    MemTxMsgEma: TMemo;
    Splitter3: TSplitter;
    procedure FormCreate(Sender: TObject);
    procedure BtnPesquisarClick(Sender: TObject);
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure RdgTpXMLClick(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);
    procedure BtnAdicionarClick(Sender: TObject);
    procedure BtnRemoverClick(Sender: TObject);
    procedure BtnEnviarClick(Sender: TObject);
    procedure BtnListarClick(Sender: TObject);
    procedure MarcarTodos1Click(Sender: TObject);
    procedure DesmarcarTodos1Click(Sender: TObject);
  private
    procedure ValidarEntradaDados;
    procedure CarregarDados(pCpDados: TBrvClientDataSet);
    procedure ListarEmails(pCpDados: TBrvClientDataSet);
    procedure EnviarEmailDestinatario(pCpDados: TBrvClientDataSet);
    procedure ExcluirArquivos(pDsLisArq: String);
    procedure AtivarEnviarEmail(pCpDados: TBrvClientDataSet);
    function  ValidarEmail(pDsEmail: String): Boolean;
    function  ListarDestinatarioEmail: String;
    function  ListarXMLAnexo(pCpDados: TBrvClientDataSet): String;
    function  RetornarDiretorioTemp: String;
  public
    { Public declarations }
  end;

var
  Con0024: TCon0024;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TCon0024.BtnAdicionarClick(Sender: TObject);
begin
      inherited;
      if (Trim(EdtDsEmail.Text) <> '') then
      begin
            if (ValidarEmail(EdtDsEmail.Text)) then
            begin
                  CpEmails.Append;
                  CpEmails.FieldByName('DsEmail').AsString := EdtDsEmail.Text;
                  CpEmails.Post;
                  EdtDsEmail.Text := '';
                  EdtDsEmail.SetFocus;
            end else
            begin
                  EdtDsEmail.SetFocus;
                  raise Exception.Create('E-mail inválido!');
            end;
      end else
      begin
            EdtDsEmail.SetFocus;
            raise Exception.Create('Informe um E-mail para adicionar!');
      end;
end;

function TCon0024.ValidarEmail(pDsEmail: String): Boolean;
begin
      pDsEmail := Trim(UpperCase(pDsEmail));
      if Pos('@', pDsEmail) > 1 then
      begin
            Delete(pDsEmail, 1, pos('@', pDsEmail));
            Result := (Length(pDsEmail) > 0) and (Pos('.', pDsEmail) > 2);
      end else
      Result := False;
end;

procedure TCon0024.BtnEnviarClick(Sender: TObject);
begin
      if MessageDlg('Deseja realmente enviar o E-mail?',mtConfirmation,[mbYes, mbNo],0)=mrYes then
      begin
            if (RdgTpXML.ItemIndex = 0) then
            begin
                  AtivarEnviarEmail(CpT002);
            end else
            begin
                  AtivarEnviarEmail(CpF004);
            end;
      end;
end;

procedure TCon0024.AtivarEnviarEmail(pCpDados: TBrvClientDataSet);
var lCpDados: TBrvClientDataSet;
begin
      inherited;
      try
          lCpDados              := TBrvClientDataSet.Create(nil);
          lCpDados.BrDicionario := DmSrvApl.BrvDicionario;
          lCpDados.Data         := pCpDados.Data;

          lCpDados.Filtered := False;
          lCpDados.Filter   := 'SnMarcad = ''S''';
          lCpDados.Filtered := True;
          if (lCpDados.RecordCount > 0) then
          begin
                if (CpEmails.RecordCount > 0) then
                begin
                      if (Trim(MemTxMsgEma.Text) <> '') then
                      begin
                            EnviarEmailDestinatario(lCpDados);
                            MessageDlg('Enviado com sucesso!!!', mtInformation, [mbok], 0);
                            PgcFundo.ActivePage := TbsFiltro;
                      end else
                      begin
                            MemTxMsgEma.SetFocus;
                            MessageDlg('Escreva a '+LblTxMsgEma.Caption+'!',mtInformation,[mbok],0);
                      end;
                end else
                begin
                      EdtDsEmail.SetFocus;
                      MessageDlg('Informe um ou mais E-mails para enviar!', mtInformation, [mbok], 0);
                end;
          end else
          begin
                EdtDsEmail.SetFocus;
                MessageDlg('Marque um ou mais registros para enviar!', mtInformation, [mbok], 0);
          end;
      finally
          FreeAndNil(lCpDados);
      end;
end;

procedure TCon0024.BtnListarClick(Sender: TObject);
begin
      inherited;
      if MessageDlg('Deseja listar abaixo os E-mails dos registros marcados?', mtConfirmation,
                                                                      [mbYes, mbNo], 0) = mrYes then
      begin
            if (RdgTpXML.ItemIndex = 0) then
            begin
                  ListarEmails(CpT002);
            end else
            begin
                  ListarEmails(CpF004);
            end;
      end;
end;

procedure TCon0024.ValidarEntradaDados;
var lTpTitula : String;
begin
      BrvListParam.Clear;

      BrvListParam.Add('TpXML', LblTpXML.Caption, RdgTpXML.Items[RdgTpXML.ItemIndex], '');

      if EdtCdEmpres.BrAsInteger > 0 then
      begin
            BrvListParam.Add('CdEmpres', LblCdEmpres.Caption, EdtCdEmpres.Text + ' - ' +
                                                            LblDsEmpres.Text, EdtCdEmpres.Text);
      end else
      begin
            BrvListParam.Add('CdEmpres', LblCdEmpres.Caption + '(s)',
              EdtCdEmpres.BrDicionario.CorpCommaCodes, EdtCdEmpres.BrDicionario.CorpCommaCodes);
      end;

      if EdtCdTitula.BrAsInteger > 0 then
      begin
            lTpTitula := ' and VT002.CdTomado = ';
            if (LblCdTitula.Caption = 'Cliente') then
            begin
                  lTpTitula := ' and F004.CdClient = ';
            end;
            BrvListParam.Add('CdTitula', LblCdTitula.Caption, EdtCdTitula.Text + ' - ' +
                                         LblRsTitula.Text,    lTpTitula + EdtCdTitula.Text);
      end else
      begin
            EdtCdTitula.SetFocus;
            raise Exception.Create('É obrigatório informar um ' + LblCdTitula.Caption + '!');
      end;

      if EdtNrDocume.BrAsInteger > 0 then
      begin
            lTpTitula := ' and VT002.CdCTRC = ';
            if (LblCdTitula.Caption = 'Cliente') then
            begin
                  lTpTitula := ' and F004.NrNota = ';
            end;
            BrvListParam.Add('NrDocume', LblNrDocume.Caption, EdtNrDocume.Text,
                                                                      lTpTitula + EdtNrDocume.Text);
      end else
      begin
            BrvListParam.Add('NrDocume', '', '', '');
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
                                                            EdtDtFinal.Text,  EdtDtFinal.Text);
                              end;
                        end;
                  end;
            end;
      end;
end;

procedure TCon0024.BtnPesquisarClick(Sender: TObject);
begin
      inherited;
      try
          ValidarEntradaDados;
          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
          if (RdgTpXML.ItemIndex = 0) then
          begin
                CpF004.Close;
                DbgNotas.DataSource := DsT002;
                CpT002.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(278,
                                                                 BrvListParam.AsBrParam, Self.Name);
                CarregarDados(CpT002);
          end else
          begin
                CpT002.Close;
                DbgNotas.DataSource := DsF004;
                CpF004.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(279,
                                                                 BrvListParam.AsBrParam, Self.Name);
                CarregarDados(CpF004);
          end;
      finally
      end;
end;

procedure TCon0024.BtnRemoverClick(Sender: TObject);
begin
      inherited;
      if ((CpEmails.RecordCount > 0) and (DbgEmails.SelectedIndex > -1)) then
      begin
            if MessageDlg('Deseja realmente remover este E-mail?', mtConfirmation,
                                                                      [mbYes, mbNo], 0) = mrYes then
            begin
                  CpEmails.Delete;
            end;
      end else
      begin
            MessageDlg('Não há E-mail selecionado para remover!', mtInformation, [mbok], 0);
      end;
end;

procedure TCon0024.BtnVoltarClick(Sender: TObject);
begin
      inherited;
      PgcFundo.ActivePage := TbsFiltro;
end;

procedure TCon0024.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TCon0024.FormCreate(Sender: TObject);
begin
      inherited;
      TbsFiltro.TabVisible    := False;
      TbsConsulta.TabVisible  := False;
      PgcFundo.ActivePage     := TbsFiltro;
      StgFiltros.ColWidths[0] := 160;
      StgFiltros.ColWidths[1] := 560;
end;

procedure TCon0024.RdgTpXMLClick(Sender: TObject);
begin
      inherited;
      if (RdgTpXML.ItemIndex = 0) then
      begin
            LblCdTitula.Caption := 'Tomador';
      end else
      begin
            LblCdTitula.Caption := 'Cliente';
      end;
      DbgNotas.Columns[7].Title.Caption := LblCdTitula.Caption;
      DbgNotas.Columns[8].Title.Caption := 'Nome do ' + LblCdTitula.Caption;
end;

procedure TCon0024.CarregarDados(pCpDados: TBrvClientDataSet);
begin
      BrvServerProgress.Stop;
      if (pCpDados.RecordCount > 0) then
      begin
            pCpDados.IndexDefs.Clear;
            pCpDados.IndexDefs.Add('IdxSequen', 'NrSequen', [ixUnique]);
            pCpDados.IndexName := 'IdxSequen';
            if (CpEmails.Active) then
            begin
                  CpEmails.EmptyDataSet;
                  CpEmails.Close;
            end;
            CpEmails.CreateDataSet;
            BrvListParam.SetStgParam(StgFiltros);
            LblQtReg.Caption := FormatFloat('0', pCpDados.RecordCount) + ' Registro(s)';
            EdtDsEmail.Text  := '';
            MemTxMsgEma.Text := '';
            PgcFundo.ActivePage := TbsConsulta;
      end else
      begin
            MessageDlg('Nenhum registro encontrado!!!', mtInformation, [mbok], 0);
      end;
end;

procedure TCon0024.DesmarcarTodos1Click(Sender: TObject);
begin
      inherited;
      DbgNotas.SetarTodasColunas('SnMarcad', 'N');
end;

procedure TCon0024.ListarEmails(pCpDados: TBrvClientDataSet);
var lVrCount : Integer;
    lDsEmail : String;
    lCpDados : TBrvClientDataSet;
begin
      inherited;
      try
          lCpDados              := TBrvClientDataSet.Create(nil);
          lCpDados.BrDicionario := DmSrvApl.BrvDicionario;
          lCpDados.Data         := pCpDados.Data;

          lCpDados.Filtered := False;
          lCpDados.Filter   := 'SnMarcad = ''S''';
          lCpDados.Filtered := True;
          if (lCpDados.RecordCount > 0) then
          begin
                lCpDados.First;
                while not lCpDados.Eof do
                begin
                      if (lCpDados.FieldByName('DsEmail').AsString <> '') then
                      begin
                            BrvString.Split(lCpDados.FieldByName('DsEmail').AsString, ';');
                            for lVrCount := 0 to BrvString.BrSplitLista.Count -1 do
                            begin
                                  lDsEmail := Trim(BrvString.BrSplitLista.Strings[lVrCount]);
                                  if (lDsEmail <> ';') then
                                  begin
                                        if not (CpEmails.Locate('DsEmail', lDsEmail,
                                                                 [Lopartialkey,LoCaseInsensitive])) then
                                        begin
                                              CpEmails.Append;
                                              CpEmails.FieldByName('DsEmail').AsString := lDsEmail;
                                              CpEmails.Post;
                                        end;
                                  end;
                            end;
                      end;
                      lCpDados.Next;
                end;
                if (CpEmails.RecordCount = 0) then
                begin
                      MessageDlg('Não há E-mail para listar!', mtInformation, [mbok], 0);
                end;
          end else
          begin
                MessageDlg('Não há registros marcados!', mtInformation, [mbok], 0);
          end;
      finally
          FreeAndNil(lCpDados);
      end;
end;

procedure TCon0024.MarcarTodos1Click(Sender: TObject);
begin
      inherited;
      DbgNotas.SetarTodasColunas('SnMarcad', 'S');
end;

procedure TCon0024.EnviarEmailDestinatario(pCpDados: TBrvClientDataSet);
var lSnEnviad : Boolean;
    lNrTentat : byte;
    lLsDesEma : String;
    lDsLisAux : TStringList;
    lNrIndice : Integer;
begin
      try
          BrvServerProgress.Start(Self.Caption, 'Enviando E-mail', 100, 10);
          lLsDesEma := ListarDestinatarioEmail;
          if (lLsDesEma <> '') then
          begin
                lSnEnviad := False;
                lNrTentat := 0;

                while (not lSnEnviad) and (lNrTentat < 3) do
                begin
                      Inc(lNrTentat);
                      IdMsgSend.Body.Clear;
                      IdMsgSend.MessageParts.Clear;

                      IdMsgSend.Body.Add(MemTxMsgEma.Text);

                      lDsLisAux      := TStringList.Create;
                      lDsLisAux.Text := ListarXMLAnexo(pCpDados);

                      if (lDsLisAux.Count > 0) then
                      begin
                            for lNrIndice := 0 to lDsLisAux.Count-1 do
                            begin
                                  TIdAttachmentFile.Create(IdMsgSend.MessageParts, lDsLisAux[lNrIndice]);
                            end;
                      end;

                      IdMsgSend.From.Text := 'sped.bravo@bravolog.com.br'; // Remetente
                      IdMsgSend.From.Name := 'Envio de XML Avulso';

                      IdMsgSend.Recipients.EMailAddresses := lLsDesEma;

                      IdMsgSend.Subject  := 'XML(s) Tipo ' + RdgTpXML.Items[RdgTpXML.ItemIndex]; //Assunto
                      IdMsgSend.Priority := mpNormal;

                      SMTP.AuthType := satDefault;
                      SMTP.Username := 'sped.bravo@bravolog.com.br';
                      SMTP.Password := '12bra34';

                      SMTP.Host     := 'smtp.uba.terra.com.br';
                      SMTP.Port     := 25;

                      try
                          SMTP.Connect;
                          SMTP.Send(IdMsgSend);
                          lSnEnviad := True;
                      except
                          SMTP.Disconnect;
                      end;
                end;
          end;
      finally
          SMTP.Disconnect;
          ExcluirArquivos(lDsLisAux.Text);
          BrvServerProgress.Stop;
      end;
end;

function TCon0024.ListarDestinatarioEmail: String;
var lLsDesEma : String;
begin
      lLsDesEma := '';
      if (CpEmails.RecordCount > 0) then
      begin
            while not CpEmails.Eof do
            begin
                  if (CpEmails.FieldByName('DsEmail').Text <> '') then
                  begin
                        lLsDesEma := lLsDesEma + CpEmails.FieldByName('DsEmail').Text + ';';
                  end;
                  CpEmails.Next;
            end;
      end;
      Result := lLsDesEma;
end;

function TCon0024.ListarXMLAnexo(pCpDados: TBrvClientDataSet): String;
var lNmDireto : String;
    lNmArquiv : String;
    lDsLisArq : TStringList;
    lDsXmlAux : TStringList;
begin
      try
          lNmDireto := RetornarDiretorioTemp;
          lNmDireto := lNmDireto + RdgTpXML.Items[RdgTpXML.ItemIndex];
          if not DirectoryExists(lNmDireto) then
          begin
                ForceDirectories(lNmDireto);
          end;
          lDsLisArq := TStringList.Create;
          lDsXmlAux := TStringList.Create;

          pCpDados.First;
          while not pCpDados.Eof do
          begin
                lNmArquiv := lNmDireto +
                             IntToStr((pCpDados.FieldByName('CdEmpres').AsInteger * 10000000) +
                                       pCpDados.FieldByName('NrDocume').AsInteger) + '.xml';
                lDsXmlAux.Text := pCpDados.FieldByName('DsXmlDoc').AsString;
                lDsXmlAux.SaveToFile(lNmArquiv);
                lDsLisArq.Add(lNmArquiv);

                pCpDados.Next;
          end;
          Result := lDsLisArq.Text;
      finally
          FreeAndNil(lDsLisArq);
          FreeAndNil(lDsXmlAux);
      end;
end;

function TCon0024.RetornarDiretorioTemp: String;
var lDsDirTmp : array[0..144] of Char;
begin
      GetTempPath(144, lDsDirTmp);
      Result := StrPas(lDsDirTmp);
end;

procedure TCon0024.ExcluirArquivos(pDsLisArq: String);
var lDsLisAux : TStringList;
    lNrIndice : Integer;
begin
      try
          lDsLisAux      := TStringList.Create;
          lDsLisAux.Text := pDsLisArq;;
          for lNrIndice := 0 to lDsLisAux.Count-1 do
          begin
                DeleteFile(lDsLisAux[lNrIndice]);
          end;
      finally
          FreeAndNil(lDsLisAux);
      end;
end;

initialization
      RegisterClass(TCon0024);

finalization
      UnRegisterClass(TCon0024);

end.
