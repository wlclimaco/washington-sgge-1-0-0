unit UMov0030;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, Mask, BrvCustomMaskEdit, BrvEditDate, BrvCustomEdit, BrvEditNum, DBCtrls,
  BrvDbEdit, StdCtrls, BrvRetCon, Buttons, BrvBitBtn, BrvListParam, ImgList, Menus, ExtCtrls,
  BrvSpeedButton, BrvDbNavCop, BrvEdit, Grids, BrvDbGrids, BrvDbGrid, DB, DBClient, BrvClientDataSet,
  ComCtrls, BrvAlertProgress, BrvServerProgress, IdComponent, IdTCPConnection, IdTCPClient,
  IdExplicitTLSClientServerBase, IdMessageClient, IdSMTPBase, IdSMTP, IdBaseComponent, IdMessage,
  AppEvnts, BrvImgBot;

type
  TMov0030 = class(TMov0000)
    PnlRegistro: TPanel;
    PnlCabeca: TPanel;
    Label2: TLabel;
    LblNmUsuEmi: TBrvRetCon;
    Panel6: TPanel;
    Panel9: TPanel;
    BtnProsseg1: TBrvBitBtn;
    LblNrRNC: TLabel;
    BtnCancelar: TBrvBitBtn;
    BtnGravar: TBrvBitBtn;
    CpQ006: TBrvClientDataSet;
    DsQ006: TDataSource;
    CpQ005: TBrvClientDataSet;
    DsQ005: TDataSource;
    CcParams: TClientDataSet;
    CcParamsXmlQ004: TMemoField;
    CcParamsXmlQ005: TMemoField;
    CcParamsXmlQ006: TMemoField;
    CpQ004: TBrvClientDataSet;
    DsQ004: TDataSource;
    EdtCdUsuEmi: TBrvDbEdit;
    EdtNrRnc: TBrvEditNum;
    CpQ007: TBrvClientDataSet;
    DsQ007: TDataSource;
    CcParamsXmlAnexos: TBlobField;
    CcParamsNmForm: TStringField;
    BrvListParam: TBrvListParam;
    BrvServerProgress: TBrvServerProgress;
    IdMsgSend: TIdMessage;
    SMTP: TIdSMTP;
    Label13: TLabel;
    EdtDtEmiRnc: TBrvDbEdit;
    PnlDesRNC: TPanel;
    PgcDados: TPageControl;
    TbsNaoCon: TTabSheet;
    MemTxRnc: TDBMemo;
    Panel5: TPanel;
    LblCdClaRnc: TLabel;
    LblDsClaRnc: TBrvRetCon;
    LblDsContro: TBrvRetCon;
    TbsProEnv: TTabSheet;
    DbgProdutos: TBrvDbGrid;
    TbsParInt: TTabSheet;
    DbgUsuarios: TBrvDbGrid;
    TbsAnexos: TTabSheet;
    Panel7: TPanel;
    LblArquivo: TLabel;
    BtnAnexar: TBrvBitBtn;
    EdtArquivo: TBrvEdit;
    BtnRemover: TBrvBitBtn;
    DbgAnexos: TBrvDbGrid;
    Panel2: TPanel;
    LblCdUsuDes: TLabel;
    EdtCdUsuDes: TBrvDbEdit;
    LblNmUsuDes: TBrvRetCon;
    LblDsLogDes: TBrvRetCon;
    TbsDadCom: TTabSheet;
    Panel1: TPanel;
    Label8: TLabel;
    EdtCdTitula: TBrvDbEdit;
    LblRsTitula: TBrvRetCon;
    Label10: TLabel;
    EdtCdMotori: TBrvDbEdit;
    EdtNmMotori: TBrvDbEdit;
    Label12: TLabel;
    EdtNrPlaVei: TBrvDbEdit;
    Label5: TLabel;
    EdtCdVeicul: TBrvDbEdit;
    GroupBox1: TGroupBox;
    LblCdEmpres: TLabel;
    LblCdSetor: TLabel;
    EdtCdEmpres: TBrvDbEdit;
    EdtCdSetor: TBrvDbEdit;
    LblDsEmpres: TBrvRetCon;
    LblDsSetor: TBrvRetCon;
    BtnProsseg2: TBrvBitBtn;
    CdsClassRNC: TClientDataSet;
    EdtCdClaRnc: TBrvDbEdit;
    LblTxRnc: TLabel;
    procedure BtnProsseg1Click(Sender: TObject);
    procedure BtnCancelarClick(Sender: TObject);
    procedure BtnGravarClick(Sender: TObject);
    procedure BtnAnexarClick(Sender: TObject);
    procedure BtnRemoverClick(Sender: TObject);
    procedure CpQ007BeforeInsert(DataSet: TDataSet);
    procedure CpQ007AfterPost(DataSet: TDataSet);
    procedure CpQ006AfterInsert(DataSet: TDataSet);
    procedure EdtCdUsuDesBrOnBeforeConsulta(Sender: TObject);
    procedure BtnProsseg2Click(Sender: TObject);
    procedure EdtCdClaRncDragDrop(Sender, Source: TObject; X, Y: Integer);
    procedure EdtCdClaRncDragOver(Sender, Source: TObject; X, Y: Integer; State: TDragState;
      var Accept: Boolean);
    procedure EdtCdClaRncExit(Sender: TObject);
    procedure EdtCdClaRncBrOnBeforeConsulta(Sender: TObject);
    procedure CpQ005BeforePost(DataSet: TDataSet);
    procedure DbgProdutosKeyPress(Sender: TObject; var Key: Char);
    procedure EdtNmMotoriKeyPress(Sender: TObject; var Key: Char);
    procedure DbgUsuariosKeyPress(Sender: TObject; var Key: Char);
  private
    { Private declarations }
    procedure EnviarEmailDestinatario;
    procedure LimparCampos;
    function  ListarDestinatarioEmail:String;
    procedure AlterarCampos;
    procedure PesquisarClassificacao;
    procedure ValidarEntradaDados;
  public
    { Public declarations }
  end;

var
  Mov0030: TMov0030;

implementation

uses UDmSrvApl, UClaSrv, UCon0025, UDmSis;

var gSvEmail : String;

{$R *.dfm}

procedure TMov0030.PesquisarClassificacao;
var  lNrClaRNC : String;
     lDsParams : String;
begin
      if (Trim(EdtCdClaRnc.Text) <> '') then
      begin
            lNrClaRNC := EdtCdClaRnc.Text;

            try
                CdsClassRNC.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro
                                                           (288, '<%CdClaRNC%>;' + lNrClaRNC, Name);
            finally
                if (CdsClassRNC.RecordCount > 0) then
                begin
                      CdsClassRNC.Open;
                      LblDsClaRnc.Text := CdsClassRNC.FieldByName('DsClaRNC').AsString;
                      LblDsContro.Text := CdsClassRNC.FieldByName('DsContro').AsString;
                      EdtCdClaRnc.Text := lNrClaRNC;
                end else
                begin
                      EdtCdClaRnc.Text := '';
                      raise Exception.Create('Esta ' + LblCdClaRnc.Caption +
                                                                   ' não pode receber lançamento!');
                end;
            end;
      end;

end;

procedure TMov0030.ValidarEntradaDados;
begin
      PgcDados.TabIndex :=  0;

      if (EdtCdEmpres.Text = '') then
      begin
            EdtCdEmpres.SetFocus;
            raise Exception.Create('É obrigatório informar uma ' + LblCdEmpres.Caption + '!');
      end;

      if (EdtCdSetor.Text  = '') then
      begin
            EdtCdSetor.SetFocus;
            raise Exception.Create('É obrigatório informar um ' + LblCdSetor.Caption + '!');
      end;

      if (EdtCdUsuDes.Text = '') then
      begin
            EdtCdUsuDes.SetFocus;
            raise Exception.Create('É obrigatório informar um ' + LblCdUsuDes.Caption + '!');
      end;

      if (EdtCdClaRnc.Text = '') then
      begin
            EdtCdClaRnc.SetFocus;
            raise Exception.Create('É obrigatório informar uma ' + LblCdClaRnc.Caption + '!');
      end;

      if (MemTxRnc.Text    = '') then
      begin
            MemTxRnc.SetFocus;
            raise Exception.Create('É obrigatório informar o ' + LblTxRnc.Caption + '!');
      end;
end;

procedure TMov0030.BtnGravarClick(Sender: TObject);
var lSrvAdm   : TSDmAdmClient;
    lStatus   : Boolean;
    lDsResult : String;
begin
      inherited;
      try
          ValidarEntradaDados;

          if(CpQ004.State in [dsEdit,dsInsert]) then
          begin
                CpQ004.Post;
          end;
          if(CpQ005.State in [dsEdit,dsInsert]) then
          begin
                CpQ005.Post;
          end;
          if(CpQ006.State in [dsEdit,dsInsert]) then
          begin
                CpQ006.Post;
          end;
          if(CpQ007.State in [dsEdit,dsInsert]) then
          begin
                CpQ007.Post;
          end;
          if (CcParams.Active) then
          begin
                CcParams.EmptyDataSet;
                CcParams.Close;
          end;

          CcParams.CreateDataSet;
          CcParams.Append;
          CcParams.FieldByName('XmlQ004').AsString := CpQ004.XMLData;
          CcParams.FieldByName('XmlQ005').AsString := CpQ005.XMLData;
          CcParams.FieldByName('XmlQ006').AsString := CpQ006.XMLData;
          CcParams.FieldByName('XmlQ007').Value    := CpQ007.Data;
          CcParams.FieldByName('NmForm' ).AsString := Self.Name;
          CcParams.Post;

          try
              BrvServerProgress.Start(Self.Caption, 'Gravando informações', 100, 10);
              lSrvAdm   := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
              lDsResult := lSrvAdm.GravarRNC(DmSrvApl.BrvDicionario.Credencial, CcParams.Data);
          finally
              if Copy(lDsResult, 1, 3) = '1; ' then
              begin
                    BrvServerProgress.Stop;
              end;
          end;

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
          EdtNrRnc.Text := Copy(lDsResult, 4, Length(lDsResult));

          EnviarEmailDestinatario;

          MessageDlg('Gravado com sucesso!!!', mtInformation, [mbok], 0);
          LimparCampos;
      finally
          FreeAndNil(lSrvAdm);
      end;
end;

procedure TMov0030.EdtCdClaRncBrOnBeforeConsulta(Sender: TObject);
var
    lNdNovo   : TTreeNode;
    lNrFormul : Integer;
begin
        try
            if not DmSis.FormularioAberto('CON0025', lNrFormul) then
            begin
             Con0025 := TCon0025.Create(Self);
            end;
            lNdNovo := Con0025.TrvClaRnc.Items.AddChildObject
                                 (Con0025.TrvClaRnc.Selected, LblCdClaRnc.Caption, Con0025.gNdNode);
            lNdNovo.ImageIndex         := 1;
            lNdNovo.SelectedIndex      := 1;
            Con0025.TrvClaRnc.Selected := lNdNovo;
            Con0025.CarregarClassificacaoRNC(lNdNovo,0);
      finally
        //  FreeAndNil(Con0025);
      end;

end;

procedure TMov0030.EdtCdClaRncDragDrop(Sender, Source: TObject; X, Y: Integer);
begin
      if Con0025.TrvClaRnc.Selected <> nil then
      begin
            EdtCdClaRnc.Text := PString(Con0025.TrvClaRnc.Selected.Data)^;
            PesquisarClassificacao;
            Con0025.Close;
      end;

end;

procedure TMov0030.EdtCdClaRncDragOver(Sender, Source: TObject; X, Y: Integer; State: TDragState;
  var Accept: Boolean);
  var  Node: TTreeNode;
begin
      Node   := Con0025.TrvClaRnc.GetNodeAt(X, Y);
      Accept := (Source <> Sender)           and (Node <> nil)  and
                (Node.Parent <> Con0025.TrvClaRnc.Selected) and
                (Node <> Con0025.TrvClaRnc.Selected);
end;

procedure TMov0030.EdtCdClaRncExit(Sender: TObject);
begin
      PesquisarClassificacao;
end;

procedure TMov0030.EdtCdUsuDesBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdUsuDes.BrParams.Clear;
      EdtCdUsuDes.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.Text);
      EdtCdUsuDes.BrParams.Add('<%CdSetor%>;'  + EdtCdSetor.Text);
end;

procedure TMov0030.EdtNmMotoriKeyPress(Sender: TObject; var Key: Char);
begin
      inherited;
      if (Trim(EdtCdMotori.Text) <> '') then
      begin
            Abort;
      end;
end;

procedure TMov0030.EnviarEmailDestinatario;
var lSnEnviad : Boolean;
    lNrTentat : byte;
    lLsDesEma : String;
begin
     try
          lLsDesEma := ListarDestinatarioEmail;
          if ((LblDsLogDes.Text <> '') or (lLsDesEma <> '')) then
          begin
                lSnEnviad := False;
                lNrTentat := 0;

                while (not lSnEnviad) and (lNrTentat < 3) do
                begin
                      Inc(lNrTentat);
                      IdMsgSend.Body.Clear;
                      IdMsgSend.MessageParts.Clear;

                      IdMsgSend.Body.Add('Foi criada/alterada a RNC ' + EdtNrRnc.Text + ' na data '+
                                   FormatDateTime('dd/mm/yyyy', Now()) + ' por '+ LblNmUsuEmi.Text +
                                                           ' destinada à '+ LblNmUsuDes.Text + '.');
                      IdMsgSend.Body.Add('');
                      IdMsgSend.Body.Add('');
                      IdMsgSend.Body.Add('');
                      IdMsgSend.Body.Add('"E-mail enviado automáticamente, não responder!"');

                      IdMsgSend.From.Text := 'sped.bravo@bravolog.com.br'; // Remetente
                      IdMsgSend.From.Name := 'Bravo Logos RNC';

                      if (LblDsLogDes.Text <> '') then
                      begin
                            IdMsgSend.Recipients.EMailAddresses := LblDsLogDes.Text + gSvEmail;
                      end;

                      if (lLsDesEma <> '') then
                      begin
                            IdMsgSend.CCList.EMailAddresses     := lLsDesEma;
                      end;

                      IdMsgSend.Subject  := 'RNC ' + EdtNrRnc.Text    + ' - ' +
                                                     LblDsContro.Text + ' - ' +
                                                     LblDsClaRnc.Text; //Assunto
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
          BrvServerProgress.Stop;
      end;
end;

function TMov0030.ListarDestinatarioEmail : String;
var lLsDesEma : String;
begin
      lLsDesEma := '';

      if (CpQ006.RecordCount > 0) then
      begin
            while not CpQ006.Eof do
            begin
                  lLsDesEma := lLsDesEma + CpQ006.FieldByName('DsLogin').Text + gSvEmail;
                  CpQ006.Next;
            end;
      end;
      Result := lLsDesEma;
end;

procedure TMov0030.BtnAnexarClick(Sender: TObject);
begin
      inherited;
      if FileExists(EdtArquivo.Text) then
      begin
             CpQ007.Append;
            (CpQ007.FieldByName('BnArquiv') as TBlobField).LoadFromFile(EdtArquivo.Text);
             CpQ007.FieldByName('NmArquiv').AsString := ExtractFileName(EdtArquivo.Text);
             CpQ007.FieldByName('StArquiv').AsString := 'N';
             CpQ007.Post;
             DbgAnexos.SetFocus;
      end else
      begin
            raise Exception.Create('Arquivo inválido!');
      end;
end;

procedure TMov0030.BtnCancelarClick(Sender: TObject);
begin
      inherited;
      if MessageDlg('Deseja realmente cancelar?', mtConfirmation, [mbYes, mbNo], 0) = mrYes then
      begin
            LimparCampos;
      end;
end;

procedure TMov0030.LimparCampos;
begin
      PnlRegistro.Visible       := False;
      PnlDesRNC.Visible         := False;
      BtnProsseg2.Caption         := '&Prosseguir';
      BtnProsseg2.Hint            := 'Prosseguir';
      BtnProsseg2.BrTipoBotao     := BrBtnOk;
      BtnProsseg1.Enabled       := True;
      EdtNrRnc.Text             := '0';
      EdtNrRnc.ReadOnly         := False;
      EdtNrRnc.Color            := clWhite;
      EdtNrRnc.BrVisibleButton  := True;
      EdtNrRnc.SetFocus;
end;

procedure TMov0030.BtnProsseg1Click(Sender: TObject);
var lCpQ007 : TClientDataSet;
begin
      inherited;
      if (Trim(EdtNrRnc.Text) <> '') then
      begin
            if (EdtNrRnc.ReadOnly = False) then
            begin
                  CpQ004.ReadOnly := False;
                  CpQ005.ReadOnly := False;
                  CpQ006.ReadOnly := False;
                  CpQ007.ReadOnly := False;

                  gSvEmail        := '@bravolog.com.br;';

                  if EdtNrRnc.BrAsInteger = 0 then
                  begin
                        AlterarCampos;

                        if (CpQ004.Active) then
                        begin
                              CpQ004.EmptyDataSet;
                              CpQ004.Close;
                        end;
                        CpQ004.FieldDefs.Clear;
                        CpQ004.FieldDefs.Add('NrRnc'   , ftInteger, 0);
                        CpQ004.FieldDefs.Add('CdEmpres', ftInteger, 0);
                        CpQ004.FieldDefs.Add('CdSetor' , ftInteger, 0);
                        CpQ004.FieldDefs.Add('CdUsuEmi', ftInteger, 0);
                        CpQ004.FieldDefs.Add('CdUsuDes', ftInteger, 0);
                        CpQ004.FieldDefs.Add('CdClaRnc', ftInteger, 0);
                        CpQ004.FieldDefs.Add('DtEmiRnc', ftDate,    0);
                        CpQ004.FieldDefs.Add('TxRnc'   , ftBlob,    0);
                        CpQ004.FieldDefs.Add('StRnc'   , ftString,  1);
                        CpQ004.FieldDefs.Add('CdTransp', ftInteger, 0);
                        CpQ004.FieldDefs.Add('CdMotori', ftInteger, 0);
                        CpQ004.FieldDefs.Add('NmMotori', ftString, 40);
                        CpQ004.FieldDefs.Add('NrPlaVei', ftString, 10);
                        CpQ004.FieldDefs.Add('CdVeicul', ftInteger, 0);
                        CpQ004.CreateDataSet;
                        CpQ004.Append;

                        CpQ004.FieldByName('CdUsuEmi').AsInteger := DmSrvApl.BrvDicionario.UserCode;
                        CpQ004.FieldByName('DtEmiRnc').AsString  := FormatDateTime('dd/mm/yyyy', Now());
                        CpQ004.FieldByName('StRnc'   ).AsString  := 'A';
                        CpQ004.FieldByName('NrRnc'   ).AsInteger := StrToInt(EdtNrRnc.Text);

                        LblNmUsuEmi.Text  := DmSrvApl.BrvDicionario.UserName;
                        LblDsEmpres.Text  := '';
                        LblDsSetor.Text   := '';
                        LblNmUsuDes.Text  := '';
                        LblDsContro.Text  := '';
                        LblDsClaRnc.Text  := '';
                        MemTxRnc.Text     := '';
                        LblRsTitula.Text  := '';
                        EdtNmMotori.Text  := '';
                        EdtNrPlaVei.Text  := '';
                        PgcDados.TabIndex :=  0;

                        if (CpQ005.Active) then
                        begin
                              CpQ005.EmptyDataSet;
                              CpQ005.Close;
                        end;
                        CpQ005.FieldDefs.Clear;
                        CpQ005.FieldDefs.Add('NrRnc',    ftInteger, 0);
                        CpQ005.FieldDefs.Add('CdProdut', ftInteger, 0);
                        CpQ005.FieldDefs.Add('DsProdut', ftString, 50);
                        CpQ005.FieldDefs.Add('QtProdut', ftInteger, 0);
                        CpQ005.FieldDefs.Add('NrLote'  , ftString, 15);
                        CpQ005.FieldDefs.Add('DtVencto', ftDate,    0);
                        CpQ005.FieldDefs.Add('NrNota',   ftInteger, 0);
                        CpQ005.FieldDefs.Add('CdTitula', ftInteger, 0);
                        CpQ005.FieldDefs.Add('RsTitula', ftString, 40);
                        CpQ005.FieldDefs.Add('NrProRNC', ftInteger, 0);
                        CpQ005.CreateDataSet;

                        TDateField(CpQ005.FieldByName('DtVencto')).EditMask := '!99/99/0000;1;_';

                        if (CpQ006.Active) then
                        begin
                              CpQ006.EmptyDataSet;
                              CpQ006.Close;
                        end;
                        CpQ006.FieldDefs.Clear;
                        CpQ006.FieldDefs.Add('NrRnc',    ftInteger, 0);
                        CpQ006.FieldDefs.Add('CdUsuari', ftInteger, 0);
                        CpQ006.FieldDefs.Add('NmComUsu', ftString, 50);
                        CpQ006.FieldDefs.Add('DsLogin',  ftString, 20);
                        CpQ006.CreateDataSet;
                        CpQ006.IndexDefs.Clear;
                        CpQ006.IndexDefs.Add('IdxUsuari', 'CdUsuari', [ixUnique]);
                        CpQ006.IndexName := 'IdxUsuari';

                        if (CpQ007.Active) then
                        begin
                              CpQ007.EmptyDataSet;
                              CpQ007.Close;
                        end;
                        CpQ007.FieldDefs.Clear;
                        CpQ007.FieldDefs.Add('BnArquiv', ftBlob,    0);
                        CpQ007.FieldDefs.Add('NmArquiv', ftString, 50);
                        CpQ007.FieldDefs.Add('StArquiv', ftString,  1);
                        CpQ007.CreateDataSet;
                        CpQ007.Filtered  := false;
                        CpQ007.Filter    := 'StArquiv <> ''D''';
                        CpQ007.Filtered  := True;
                  end else
                  begin
                        try
                            BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);

                            BrvListParam.Clear;
                            BrvListParam.Add('NrRnc', '', '', EdtNrRnc.Text);
                            CpQ004.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(245,
                                                                 BrvListParam.AsBrParam, Self.Name);
                            if (CpQ004.RecordCount > 0) then
                            begin
                                  AlterarCampos;

                                  LblDsEmpres.Text := CpQ004.FieldByName('DsEmpres').Text;
                                  LblDsSetor.Text  := CpQ004.FieldByName('DsSetor' ).Text;
                                  LblNmUsuEmi.Text := CpQ004.FieldByName('NmUsuEmi').Text;
                                  LblNmUsuDes.Text := CpQ004.FieldByName('NmUsuDes').Text;
                                  LblDsLogDes.Text := CpQ004.FieldByName('DsLogDes').Text;
                                  LblDsContro.Text := CpQ004.FieldByName('DsContro').Text;
                                  LblDsClaRnc.Text := CpQ004.FieldByName('DsClaRnc').Text;
                                  LblRsTitula.Text := CpQ004.FieldByName('RsTitula').Text;
                                  EdtNmMotori.Text := CpQ004.FieldByName('NmMotori').Text;
                                  EdtNrPlaVei.Text := CpQ004.FieldByName('NrPlaVei').Text;

                                  PgcDados.TabIndex := 0;

                                  CpQ005.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(247,
                                                                 BrvListParam.AsBrParam, Self.Name);
                                  
                                  TDateField(CpQ005.FieldByName('DtVencto')).EditMask :=
                                                                                  '!99/99/0000;1;_';

                                  CpQ006.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(248,
                                                                 BrvListParam.AsBrParam, Self.Name);
                                  CpQ006.IndexDefs.Clear;
                                  CpQ006.IndexDefs.Add('IdxUsuari', 'CdUsuari', [ixUnique]);
                                  CpQ006.IndexName := 'IdxUsuari';

                                  if (CpQ007.Active) then
                                  begin
                                        CpQ007.EmptyDataSet;
                                        CpQ007.Close;
                                  end;
                                  CpQ007.FieldDefs.Clear;
                                  CpQ007.FieldDefs.Add('BnArquiv', ftBlob,    0);
                                  CpQ007.FieldDefs.Add('NmArquiv', ftString, 50);
                                  CpQ007.FieldDefs.Add('StArquiv', ftString,  1);
                                  CpQ007.CreateDataSet;
                                  CpQ007.Filtered  := false;
                                  CpQ007.Filter    := 'StArquiv <> ''D''';
                                  CpQ007.Filtered  := True;

                                  lCpQ007 := TClientDataSet.Create(Self);
                                  lCpQ007.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(249,
                                                                 BrvListParam.AsBrParam, Self.Name);

                                  if (lCpQ007.RecordCount > 0) then
                                  begin
                                        while not lCpQ007.Eof do
                                        begin
                                              CpQ007.Append;
                                              CpQ007.FieldByName('NmArquiv').AsString :=
                                                           lCpQ007.FieldByName('NmArquiv').AsString;
                                              CpQ007.FieldByName('StArquiv').AsString := 'S';
                                              CpQ007.Post;

                                              lCpQ007.Next;
                                        end;
                                        CpQ007.First;
                                  end;
                            end else
                            begin
                                  MessageDlg('Nenhum registro encontrado!!!', mtInformation,
                                                                                         [mbok], 0);
                            end;

                        finally
                            BrvServerProgress.Stop;
                        end;
                  end;

                  if ((CpQ004.RecordCount > 0) and (CpQ004.FieldByName('CdUsuEmi').AsInteger <>
                                                              DmSrvApl.BrvDicionario.UserCode)) then
                  begin
                        CpQ004.ReadOnly := True;
                        CpQ005.ReadOnly := True;
                        CpQ006.ReadOnly := True;
                        CpQ007.ReadOnly := True;

                        EdtCdEmpres.Color           := $00DDE2E3;
                        EdtCdEmpres.BrVisibleButton := False;
                        EdtCdSetor.Color            := $00DDE2E3;
                        EdtCdSetor.BrVisibleButton  := False;

                        EdtCdUsuDes.Color           := $00DDE2E3;
                        EdtCdUsuDes.BrVisibleButton := False;
                        EdtCdClaRnc.Color           := $00DDE2E3;
                        EdtCdClaRnc.BrVisibleButton := False;
                        EdtArquivo.Color            := $00DDE2E3;
                        EdtArquivo.BrVisibleButton  := False;
                        EdtArquivo.Enabled          := False;
                        MemTxRnc.Color              := $00DDE2E3;
                        DbgProdutos.Color           := $00DDE2E3;
                        DbgProdutos.Enabled         := False;
                        DbgUsuarios.Color           := $00DDE2E3;
                        DbgUsuarios.Enabled         := False;
                        DbgAnexos.Color             := $00DDE2E3;
                        DbgAnexos.Enabled           := False;

                        EdtCdTitula.Color           := $00DDE2E3;
                        EdtCdTitula.BrVisibleButton := False;
                        EdtCdMotori.Color           := $00DDE2E3;
                        EdtCdMotori.BrVisibleButton := False;
                        EdtNmMotori.Color           := $00DDE2E3;
                        EdtNmMotori.Enabled         := False;
                        EdtNrPlaVei.Color           := $00DDE2E3;
                        EdtNrPlaVei.Enabled         := False;
                        EdtCdVeicul.Color           := $00DDE2E3;
                        EdtCdVeicul.BrVisibleButton := False;

                        BtnGravar.Enabled           := False;
                        BtnAnexar.Enabled           := False;
                        BtnRemover.Enabled          := False;

                        BtnProsseg2.Enabled         := False;
                        PnlDesRNC.Visible           := True;
                  end else
                  begin
                        EdtCdEmpres.Color           := clWhite;
                        EdtCdEmpres.BrVisibleButton := True;
                        EdtCdSetor.Color            := clWhite;
                        EdtCdSetor.BrVisibleButton  := True;

                        EdtCdUsuDes.Color           := clWhite;
                        EdtCdUsuDes.BrVisibleButton := True;
                        EdtCdClaRnc.Color           := clWhite;
                        EdtCdClaRnc.BrVisibleButton := True;
                        EdtArquivo.Color            := clWhite;
                        EdtArquivo.BrVisibleButton  := True;
                        EdtArquivo.Enabled          := True;
                        MemTxRnc.Color              := clWhite;
                        DbgProdutos.Color           := clWhite;
                        DbgProdutos.Enabled         := True;
                        DbgUsuarios.Color           := clWhite;
                        DbgUsuarios.Enabled         := True;
                        DbgAnexos.Color             := clWhite;
                        DbgAnexos.Enabled           := True;

                        EdtCdTitula.Color           := clWhite;
                        EdtCdTitula.BrVisibleButton := True;
                        EdtCdMotori.Color           := clWhite;
                        EdtCdMotori.BrVisibleButton := True;
                        EdtNmMotori.Color           := clWhite;
                        EdtNmMotori.Enabled         := True;
                        EdtNrPlaVei.Color           := clWhite;
                        EdtNrPlaVei.Enabled         := True;
                        EdtCdVeicul.Color           := clWhite;
                        EdtCdVeicul.BrVisibleButton := True;

                        BtnGravar.Enabled           := True;
                        BtnAnexar.Enabled           := True;
                        BtnRemover.Enabled          := True;

                        if EdtNrRnc.BrAsInteger > 0 then
                        begin
                              BtnProsseg2Click(nil);
                        end else
                        begin
                              EdtCdEmpres.SetFocus;
                        end;
                  end;
            end;
      end else
      begin
            EdtNrRnc.SetFocus;
            raise Exception.Create('É obrigatório informar o ' + LblNrRNC.Caption + '!');
      end;
end;

procedure TMov0030.AlterarCampos;
begin
      EdtNrRnc.ReadOnly        := True;
      EdtNrRnc.Color           := $00DDE2E3;
      EdtNrRnc.BrVisibleButton := False;
      PnlRegistro.Visible      := True;
      BtnProsseg1.Enabled      := False;

      PnlDesRNC.Visible           := False;
      BtnProsseg2.Enabled         := True;
      EdtCdEmpres.Color           := clWhite;
      EdtCdEmpres.BrVisibleButton := True;
      EdtCdSetor.Color            := clWhite;
      EdtCdSetor.BrVisibleButton  := True;
end;

procedure TMov0030.BtnProsseg2Click(Sender: TObject);
begin
      inherited;
      if ((Trim(EdtCdEmpres.Text) <> '') and (Trim(EdtCdSetor.Text) <> '')) then
      begin
            if (BtnProsseg2.Hint = 'Prosseguir') then
            begin
                  EdtCdEmpres.Color           := $00DDE2E3;
                  EdtCdEmpres.BrVisibleButton := False;
                  EdtCdSetor.Color            := $00DDE2E3;
                  EdtCdSetor.BrVisibleButton  := False;

                  BtnProsseg2.Caption         := '&Alterar';
                  BtnProsseg2.Hint            := 'Alterar';
                  BtnProsseg2.BrTipoBotao     := BrBtnLimpar;

                  PnlDesRNC.Visible           := True;
                  EdtCdUsuDes.SetFocus;
            end else
            if (BtnProsseg2.Hint = 'Alterar') then
            begin
                  EdtCdEmpres.Color           := clWhite;
                  EdtCdEmpres.BrVisibleButton := True;
                  EdtCdSetor.Color            := clWhite;
                  EdtCdSetor.BrVisibleButton  := True;

                  BtnProsseg2.Caption         := '&Prosseguir';
                  BtnProsseg2.Hint            := 'Prosseguir';
                  BtnProsseg2.BrTipoBotao     := BrBtnOk;

                  PnlDesRNC.Visible           := False;
                  EdtCdEmpres.SetFocus;
            end;
      end else
      begin
          if (Trim(EdtCdEmpres.Text) = '') then
          begin
                EdtCdEmpres.SetFocus;
                raise Exception.Create('É obrigatório informar uma ' + LblCdEmpres.Caption + '!');
          end;
          if (Trim(EdtCdSetor.Text)  = '') then
          begin
                EdtCdSetor.SetFocus;
                raise Exception.Create('É obrigatório informar um ' + LblCdSetor.Caption + '!');
          end;
      end;
end;

procedure TMov0030.BtnRemoverClick(Sender: TObject);
begin
      inherited;
      if ((CpQ007.RecordCount > 0) and (DbgAnexos.SelectedIndex > -1)) then
      begin
            if MessageDlg('Deseja realmente remover este ' + LblArquivo.Caption + '?', mtConfirmation,
                                                                      [mbYes, mbNo], 0) = mrYes then
            begin
                  CpQ007.Edit;
                  CpQ007.FieldByName('StArquiv').AsString  := 'D';
                  CpQ007.Post;
            end;
      end else
      begin
            MessageDlg('Não há ' + LblArquivo.Caption + ' selecionado para remover!', mtInformation,
                                                                                         [mbok], 0);
      end;
end;

procedure TMov0030.CpQ005BeforePost(DataSet: TDataSet);
begin
      inherited;
      if ((Trim(CpQ005.FieldByName('CdProdut').AsString) = '')  and
          (Trim(CpQ005.FieldByName('DsProdut').AsString) = '')  and
          (Trim(CpQ005.FieldByName('QtProdut').AsString) = '')  and
          (Trim(CpQ005.FieldByName('NrLote'  ).AsString) = '')  and
          (Trim(CpQ005.FieldByName('DtVencto').AsString) = '')  and
          (Trim(CpQ005.FieldByName('NrNota'  ).AsString) = '')  and
          (Trim(CpQ005.FieldByName('CdTitula').AsString) = '')  and
          (Trim(CpQ005.FieldByName('RsTitula').AsString) = '')) then
      begin
            Abort;
      end else
      begin
            if (CpQ005.FieldByName('DsProdut').AsString <> '') then
            begin
                  CpQ005.FieldByName('NrRnc'   ).AsInteger := StrToInt(EdtNrRnc.Text);
                  CpQ005.FieldByName('NrProRnc').AsInteger := 0;
            end else
            begin
                  raise Exception.Create('É obrigatório informar a Descrição do Produto!');
            end;
      end;
end;

procedure TMov0030.CpQ006AfterInsert(DataSet: TDataSet);
begin
      inherited;
      CpQ006.FieldByName('NrRnc').AsInteger := StrToInt(EdtNrRnc.Text);
end;

procedure TMov0030.CpQ007AfterPost(DataSet: TDataSet);
begin
      inherited;
      EdtArquivo.Text := '';
end;

procedure TMov0030.CpQ007BeforeInsert(DataSet: TDataSet);
begin
      inherited;
      if (CpQ007.RecordCount > 0) then
      begin
            while not CpQ007.Eof do
            begin
                   if (ExtractFileName(EdtArquivo.Text) = CpQ007.FieldByName('NmArquiv').Text) then
                   begin
                         MessageDlg('Já existe arquivo anexado com o mesmo nome!', mtInformation,
                                                                                         [mbok], 0);
                         Abort;
                   end;
                   CpQ007.Next;
            end;
      end;

end;

procedure TMov0030.DbgProdutosKeyPress(Sender: TObject; var Key: Char);
begin
      inherited;
      if ( ( ( DbgProdutos.Fields[0].Text <> '' ) and
             ( DbgProdutos.Columns[DbgProdutos.SelectedIndex].FieldName = 'DsProdut' ) ) or
           (   DbgProdutos.Columns[DbgProdutos.SelectedIndex].FieldName = 'RsTitula'   ) ) then
      begin
            Abort;
      end;
end;

procedure TMov0030.DbgUsuariosKeyPress(Sender: TObject; var Key: Char);
begin
      inherited;
      if (DbgUsuarios.Columns[DbgUsuarios.SelectedIndex].FieldName = 'NmComUsu') then
      begin
            Abort;
      end;
end;

initialization
      RegisterClass(TMov0030);

finalization
      UnRegisterClass(TMov0030);

end.
