unit UMov0015;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms, ComObj, ShellApi,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvBitBtn, BrvRetCon,
  BrvEdit, Mask, BrvEditDate, BrvComboBox, DB, DBClient, BrvClientDataSet, InvokeRegistry, Rio,
  SOAPHTTPClient, BrvString, BrvRelAsc, BrvAlertProgress, BrvImgBot, BrvImage, BrvListParam,
  ImgList, Menus, BrvCustomMaskEdit, BrvCustomEdit;

type
  TMov0015 = class(TMov0000)
    Label2: TLabel;
    EdtCdArmaze: TBrvEdit;
    EdtNmArmaze: TBrvRetCon;
    Label1: TLabel;
    EdtNmArquiv: TBrvEdit;
    Label22: TLabel;
    EdtDtIni: TBrvEditDate;
    Label3: TLabel;
    EdtDtFim: TBrvEditDate;
    BtnImport: TBrvBitBtn;
    CbxDsTipMov: TBrvComboBox;
    Label4: TLabel;
    CdsW008: TBrvClientDataSet;
    HTTPRIO: THTTPRIO;
    CbxEnvWs: TCheckBox;
    CbxVisualiza: TCheckBox;
    BrvRelAsc1: TBrvRelAsc;
    BrvAlertProgress: TBrvAlertProgress;
    procedure BtnImportClick(Sender: TObject);
  private
    procedure ValidaEntrada;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Mov0015: TMov0015;

implementation

uses UDmSrvApl, WSStatusPedidos;

{$R *.dfm}

procedure TMov0015.ValidaEntrada;
begin
      if (StrToIntDef(EdtCdArmaze.Text, 0) = 0) then
      begin
           raise Exception.Create('Informe o Armazém');
      end;

      if (EdtDtIni.BrDataVazia) then
      begin
            raise Exception.Create('Data inicial vazia');
      end;

      if (EdtDtFim.BrDataVazia) then
      begin
            raise Exception.Create('Data final vazia');
      end;

      if (not EdtDtIni.BrDataValida) then
      begin
            raise Exception.Create('Data inicial inválida');
      end;

      if (not EdtDtFim.BrDataValida) then
      begin
            raise Exception.Create('Data final inválida');
      end;

      if (EdtDtIni.BrAsDate > EdtDtFim.BrAsDate) then
      begin
            raise Exception.Create('Data final maior que inicial');
      end;
end;

procedure TMov0015.BtnImportClick(Sender: TObject);
var lSglXmlTrk : TStrings;
    lNrLinha   : Integer;
    lDsSenMov  : String;
    LNrConta   : Integer;
    CdsW008    : TClientDataSet;
    CdsQtProSid: TClientDataSet;
    lLogTrk    : TStrings;
    lNmArquivo : String;
    lSnRecepc  : Boolean;
    lDsMsgWeb  : WideString;

    lWSEnvio : ImportStringTrackTrace;
    lWSResp  : ImportStringTrackTraceResponse;

    function DH: String;
    begin
          Result := formatdatetime('dd/mm/yyyy hh:mm:ss', Now()) + ' ';
    end;
begin
      ValidaEntrada;

      lNmArquivo := 'TrkTrc' + FormatDateTime('ddmmyy', EdtDtIni.BrAsDate) +
                                                  '_' + FormatDateTime('ddmmyy', EdtDtIni.BrAsDate);

      lNmArquivo := InputBox('Nome do arquivo', 'Digite o nome do arquivo', lNmArquivo);

      lNmArquivo := EdtNmArquiv.Text + '\' + lNmArquivo;

      try
          lLogTrk := TStringList.Create;
          lLogTrk.Clear;

          lLogTrk.Add(DH + 'Armazém.: ' + EdtCdArmaze.Text + ' - ' + EdtNmArmaze.Text);
          lLogTrk.Add(DH + 'Arquivo.: ' + lNmArquivo + ' (Xml, Err, Log)');
          lLogTrk.Add(DH + 'Período.: ' + EdtDtIni.Text + ' -> ' + EdtDtFim.Text);
          lLogTrk.Add(DH + 'Operação: ' + CbxDsTipMov.Text);

          try
              CdsW008     := TClientDataSet.Create(Self);
              CdsQtProSid := TClientDataSet.Create(Self);

              BrvAlertProgress.BrCaption  := Self.Caption;
              BrvAlertProgress.BrProcesso := 'Gerando XML...';
              BrvAlertProgress.ShowAlert;
              BrvAlertProgress.BrMax(5);

              BrvAlertProgress.BrStepIt('Localizando conferencias...');

              lLogTrk.Add(DH + 'Consulta SQL 1...');
              CdsW008.Close;
              CdsW008.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(140,
                                  '<%CdTipAti%>;' +
                                  CbxDsTipMov.Values[CbxDsTipMov.ItemIndex] + Chr(13)   +
                                  '<%DtOpeIni%>;' + EdtDtIni.BrAsDataSQL + ' 00:00:00'  + Chr(13) +
                                  '<%DtOpeFim%>;' + EdtDtFim.BrAsDataSQL + ' 23:59:59', Self.Name);
              CdsW008.Open;

              BrvAlertProgress.BrStepIt('Localizando conferencias... OK');

              lLogTrk.Add(DH + 'Consulta SQL 1: OK');

              CdsW008.First;

              lLogTrk.Add(DH + 'Registros: OK [' + FormatFloat('0', CdsW008.RecordCount) + ']');

              try
                  lSglXmlTrk := TStringList.Create;

                  lSglXmlTrk.Add('<?xml version="1.0" encoding="utf-8"?>');
                  lSglXmlTrk.Add('<transportadora dthrgeracao="' +
                                 FormatDateTime('dd/mm/yyyy hh:mm', Now()) +
                                 '" dthrrecebimento="">');

                  lLogTrk.Add(DH + 'XML1: OK');

                  lNrLinha := 1;
                  LNrConta := CdsW008.RecordCount;

                  //Processando Sem identificação das saídas
                  lLogTrk.Add(DH + 'Consulta SQL 2...');
                  BrvAlertProgress.BrStepIt('Localizando conferencias... (Sem ID).');

                  CdsQtProSid.Close;
                  CdsQtProSid.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(142,
                             '<%CdTipAti%>;' +
                             CbxDsTipMov.Values[CbxDsTipMov.ItemIndex] + Chr(13)   +
                             '<%DtOpeIni%>;' + EdtDtIni.BrAsDataSQL + ' 00:00:00'  + Chr(13) +
                             '<%DtOpeFim%>;' + EdtDtFim.BrAsDataSQL + ' 23:59:59', Self.Name);
                  CdsQtProSid.Open;

                  BrvAlertProgress.BrStepIt('Localizando conferencias... (Sem ID). OK');

                  lLogTrk.Add(DH + 'Consulta SQL 2: OK');

                  lLogTrk.Add(DH + 'Contador de Linha...');
                  if (CdsQtProSid.FieldByName('QtProSid').AsInteger > 0) then
                  begin
                        LNrConta := LNrConta + CdsQtProSid.RecordCount;
                        lLogTrk.Add(DH + 'Contador de Linha: Ok');
                  end;

                  if (LNrConta > 0) then
                  begin
                        BrvAlertProgress.BrMax(LNrConta + 2);
                        BrvAlertProgress.BrStepIt('Montando XML');

                        lLogTrk.Add(DH + 'Verificando Tipo...');
                        if (CdsW008.FieldByName('CdTipAti').AsInteger = 1) or
                           (CdsW008.FieldByName('CdTipAti').AsInteger = 4) then
                        begin
                              lDsSenMov := 'e';
                        end else
                        begin
                              lDsSenMov := 's';
                        end;
                        lLogTrk.Add(DH + 'Verificando Tipo: OK');

                        while not CdsW008.Eof do
                        begin
                              lSglXmlTrk.Add('<material sscc="' +
                                             BrvRelAsc1.FormatarNumero(
                                                 CdsW008.FieldByName('NRSSCC').AsString, 18, true) +
                                             '" linha="' + FormatFloat('0000', lNrLinha) +
                                             '" sentmov="' +
                                             lDsSenMov + '">');
                              lLogTrk.Add(DH + 'XML2: OK');

                              lSglXmlTrk.Add('<cliente numcli="' +
                                             FormatFloat('0000000000',
                                             CdsW008.FieldByName('CdClient').AsInteger) +
                                             '" nrforn="' +
                                             FormatFloat('0000000000',
                                             CdsW008.FieldByName('NrFornec').AsInteger) +
                                             '" contotal="' +
                                             FormatFloat('0000', LNrConta) + '" />');
                              lLogTrk.Add(DH + 'XML3: OK');

                              lSglXmlTrk.Add('<caixa gtin="' +
                                             BrvRelAsc1.FormatarNumero(
                                             CdsW008.FieldByName('CdEmbala').AsString, 14, True) +
                                             '" numlote="' +
                                             CdsW008.FieldByName('DsLote').AsString +
                                             '"  />');
                              lLogTrk.Add(DH + 'XML4: OK');

                              lSglXmlTrk.Add('<qtdsemsscc>0000</qtdsemsscc>');
                              lSglXmlTrk.Add('<dummy></dummy>');
                              lSglXmlTrk.Add('</material>');
                              Inc(lNrLinha);
                              lLogTrk.Add(DH + 'XML5: OK');

                              CdsW008.Next;
                              BrvAlertProgress.BrStepIt;
                              sleep(1);
                        end;

                        CdsQtProSid.First;

                        while not CdsQtProSid.Eof do
                        begin
                              if (CdsQtProSid.FieldByName('CdTipAti').AsInteger = 1) or
                                 (CdsQtProSid.FieldByName('CdTipAti').AsInteger = 4) then
                              begin
                                    lDsSenMov := 'e';
                              end else
                              begin
                                    lDsSenMov := 's';
                              end;

                              lSglXmlTrk.Add('<material sscc="000000000000000000" linha="' +
                                                  FormatFloat('0000', lNrLinha) + '" sentmov="'+
                                                  lDsSenMov + '">');
                              lSglXmlTrk.Add('<cliente numcli="' +
                                                    FormatFloat('0000000000',
                                                    CdsQtProSid.FieldByName('CdClient').AsInteger) +
                                             '" nrforn="' +
                                            CdsQtProSid.FieldByName('NrFornec').AsString +
                                            '" contotal="' +
                                            FormatFloat('0000', LNrConta) + '" />');

                              lSglXmlTrk.Add('<caixa gtin="00000000000000" numlote="" />');
                              lSglXmlTrk.Add('<qtdsemsscc>' + FormatFloat('0000',
                                                    CdsQtProSid.FieldByName('QtProSid').AsInteger) +
                                                                                   '</qtdsemsscc>');
                              lSglXmlTrk.Add('<dummy></dummy>');
                              lSglXmlTrk.Add('</material>');

                              CdsQtProSid.Next;
                              Inc(lNrLinha);
                              BrvAlertProgress.BrStepIt;
                              sleep(1);
                        end;

                        lSglXmlTrk.Add('</transportadora>');

                        lLogTrk.Add(DH + 'XML6: OK');

                        lSglXmlTrk.SaveToFile(lNmArquivo + '.xml');

                        if (CbxVisualiza.Checked) then
                        begin
                              ShellExecute(Handle, nil, Pchar(lNmArquivo + '.xml'), nil,
                                                                                nil, SW_SHOWNORMAL);
                        end;

                        lLogTrk.Add(DH + 'XML Backup da Operação');

                        BrvAlertProgress.BrMax(3);
                        BrvAlertProgress.BrStepIt('Processando XML');
                        Sleep(1000);

                        try
                            if (CbxEnvWs.Checked) then
                            begin
                                  lLogTrk.Add(DH + 'Envio para Web Service...');
                                  BrvAlertProgress.BrStepIt('Envio de XML. Aguardando resposta...');

                                  lDsMsgWeb := '';

                                  lWSEnvio := ImportStringTrackTrace.Create;
                                  lWSEnvio.str := UTF8Encode(lSglXmlTrk.Text);
                                  lWSEnvio.CarrierEDI := 'BRAV';
                                  lWSEnvio.password   := 'm4s@rxfk';

                                  lWSResp := WSStatusPedidos.GetWSTrackTraceSoap.
                                                                   ImportStringTrackTrace(lWSEnvio);

                                  lSnRecepc := lWSResp.ImportStringTrackTraceResult;
                                  lDsMsgWeb := lWSResp.mensagens;

                                  if not (lSnRecepc) then
                                  begin
                                        BrvAlertProgress.BrStepIt('Falha!!!');

                                        lLogTrk.Add(DH + 'Envio para Web Service: Falha!!!');

                                        lLogTrk.Add(DH + 'Verificar arquivo: ' +
                                                                               lNmArquivo + '.err');

                                        lLogTrk.Add(lDsMsgWeb);

                                        lSglXmlTrk.SaveToFile(lNmArquivo + '.err');
                                  end else
                                  begin
                                        BrvAlertProgress.BrStepIt('Envio para Web Service: OK');
                                        lLogTrk.Add(lDsMsgWeb);
                                        MessageDlg('Enviando XML OK', mtInformation, [mbok], 0);
                                  end;
                            end else
                            begin
                                  BrvAlertProgress.BrStepIt;
                                  BrvAlertProgress.BrStepIt;
                                  BrvAlertProgress.BrStepIt;
                            end;

                        Except
                            On E : Exception do
                            Begin
                                  lLogTrk.Add(DH + 'Envio para Web Service: Falha!!!');
                                  lLogTrk.Add(DH + 'Verificar arquivo: ' + lNmArquivo + '.err');

                                  lSglXmlTrk.Add('Erro: ' + E.Message);
                                  lSglXmlTrk.SaveToFile(lNmArquivo + '.err');
                            End;
                        end;
                  end else
                  begin
                        MessageDlg('Nenhum Registro Localizado!', mtInformation, [mbok], 0);
                  end;

              finally
                  FreeAndNil(lSglXmlTrk);
              end;

              CdsW008.Close;
          Except
              on e: exception do
              begin
                    lLogTrk.Add(DH + 'Erro:' + E.Message);
              end;
          end;

      finally
          lLogTrk.SaveToFile(lNmArquivo + '.log');
          FreeAndnil(CdsW008);
          FreeAndnil(CdsQtProSid);
      end;

end;

(*procedure TMov0015.BtnImportClick(Sender: TObject);
var lSglXmlTrk : TStrings;
    lNrLinha   : Integer;
    lDsSenMov  : String;
    LNrConta   : Integer;
begin
      inherited;
      ValidaEntrada;

      CdsW008.Close;
      CdsW008.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(140,
                               '<%CdTipAti%>;' +
                               CbxDsTipMov.Values[CbxDsTipMov.ItemIndex] + Chr(13)   +
                               '<%DtOpeIni%>;' + EdtDtIni.BrAsDataSQL + ' 00:00:00'  + Chr(13) +
                               '<%DtOpeFim%>;' + EdtDtFim.BrAsDataSQL + ' 23:59:59', Self.Name);
      CdsW008.Open;

      CdsW008.First;

      if (CdsW008.RecordCount > 0) then
      begin

            try
                lSglXmlTrk := TStringList.Create;

                lSglXmlTrk.Add('<?xml version="1.0" encoding="utf-8"?>');
                lSglXmlTrk.Add('<transportadora dthrgeracao="' +
                               FormatDateTime('dd/mm/yyyy hh:mm', Now()) +
                               '" dthrrecebimento="">');

                lNrLinha := 1;
                LNrConta := CdsW008.RecordCount+1;

                if (CbxDsTipMov.ItemIndex = 0) then
                begin
                      lDsSenMov := 'e';
                end else
                begin
                      lDsSenMov := 's';
                end;

                while not CdsW008.Eof do
                begin

                      lSglXmlTrk.Add('<material sscc="' + CdsW008.FieldByName('NRSSCC').AsString +
                                     '" linha="' + FormatFloat('0000', lNrLinha) + '" sentmov="' +
                                     lDsSenMov + '">');

                      lSglXmlTrk.Add('<cliente numcli="' +
                              FormatFloat('0000000000', CdsW008.FieldByName('CdClient').AsInteger) +
                              '" nrforn="' +
                              FormatFloat('0000000000', CdsW008.FieldByName('NrFornec').AsInteger) +
                              '" contotal="' +
                              FormatFloat('0000', LNrConta) + '" />');

                      lSglXmlTrk.Add('<caixa gtin="' + CdsW008.FieldByName('CdEmbala').AsString +
                                  '" numlote="' + CdsW008.FieldByName('DsLote').AsString + '"  />');

                      lSglXmlTrk.Add('<qtdsemsscc>0000</qtdsemsscc>');

                      lSglXmlTrk.Add('<dummy></dummy>');

                      lSglXmlTrk.Add('</material>');
                      Inc(lNrLinha);

                      CdsW008.Next;
                end;

                //Produtos sem identificação
                CdsW008.Close;
                CdsW008.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(142,
                                 '<%CdTipAti%>;' +
                                 CbxDsTipMov.Values[CbxDsTipMov.ItemIndex] + Chr(13)   +
                                 '<%DtOpeIni%>;' + EdtDtIni.BrAsDataSQL + ' 00:00:00'  + Chr(13) +
                                 '<%DtOpeFim%>;' + EdtDtFim.BrAsDataSQL + ' 23:59:59', Self.Name);
                CdsW008.Open;

                lSglXmlTrk.Add('<material sscc="" linha="' + FormatFloat('0000', lNrLinha) +
                           '" sentmov="' + lDsSenMov + '">');
                lSglXmlTrk.Add('<cliente numcli="0000000000" nrforn="0000000000" contotal="' +
                           FormatFloat('0000', LNrConta) + '" />');
                lSglXmlTrk.Add('<caixa gtin="" numlote=""  />');
                lSglXmlTrk.Add('<qtdsemsscc>' + FormatFloat('0000',
                                    CdsW008.FieldByName('QtProSid').AsFloat) + '</qtdsemsscc>');
                lSglXmlTrk.Add('<dummy></dummy>');
                lSglXmlTrk.Add('</material>');

                lSglXmlTrk.Add('</transportadora>');
                lSglXmlTrk.SaveToFile(EdtNmArquiv.Text);

                MessageDlg('Arquivo gerado com sucesso!', mtInformation, [MbOk], 0);
            finally
                FreeAndNil(lSglXmlTrk);
            end;

      end else
      begin
            MessageDlg('Nenhum registro encontrado!', mtWarning, [MbOk], 0);
      end;

      CdsW008.Close;
end;*)

initialization
      RegisterClass(TMov0015);

finalization
      UnRegisterClass(TMov0015);

end.
