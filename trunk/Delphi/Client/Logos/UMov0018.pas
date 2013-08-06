unit UMov0018;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, Mask, BrvEditDate, StdCtrls,
  BrvBitBtn, BrvRetCon, BrvEditNum, DB, DBClient, BrvClientDataSet, DateUtils, BrvListParam,
  ImgList, Menus, BrvCustomMaskEdit, BrvCustomEdit;

type
  TMov0018 = class(TMov0000)
    PnlOcorrencia: TPanel;
    PnlBusca: TPanel;
    PnlOperacao: TPanel;
    Panel4: TPanel;
    LblEmpres: TLabel;
    EdtCdMotori: TBrvEditNum;
    LblNmMotori: TBrvRetCon;
    BtnBuscar: TBrvBitBtn;
    Label1: TLabel;
    EdtCdEmpres: TBrvEditNum;
    LblDsEmpres: TBrvRetCon;
    Label2: TLabel;
    EdtCdCarga: TBrvEditNum;
    Label3: TLabel;
    EdtCdVeicul: TBrvEditNum;
    LblDsVeicul: TBrvRetCon;
    Label5: TLabel;
    Label6: TLabel;
    BtnCancelar: TBrvBitBtn;
    BtnGravar: TBrvBitBtn;
    Label4: TLabel;
    EdtCdTipoco: TBrvEditNum;
    LblDsTipoco: TBrvRetCon;
    EdtDtHrOcor: TBrvEditDate;
    EdtDtHrDig: TBrvEditDate;
    CcT018: TBrvClientDataSet;
    LblStOcorre: TLabel;
    procedure BtnBuscarClick(Sender: TObject);
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BtnCancelarClick(Sender: TObject);
    procedure EdtCdTipocoBrOnBeforeConsulta(Sender: TObject);
    procedure BtnGravarClick(Sender: TObject);
    procedure EdtCdCargaBrOnBeforeConsulta(Sender: TObject);
  private
    procedure ValidarEntrada;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Mov0018   : TMov0018;
  gDtOcorre : TDateTime;
  gDtOcoAux : TDateTime;

implementation

uses UDmSrvApl, UClaSrv;

{$R *.dfm}

procedure TMov0018.BtnCancelarClick(Sender: TObject);
begin
      inherited;
      PnlOcorrencia.Visible := False;
      PnlBusca.Enabled := True;
      EdtCdTipoco.Text := '0';
      EdtCdTipoco.BrValidarEntrada;
      EdtCdMotori.Text := '0';
      EdtCdMotori.BrValidarEntrada;
      EdtCdEmpres.Enabled := true;

      EdtCdCarga.Text  := '0';
      EdtCdVeicul.Text := '0';
      EdtCdVeicul.BrValidarEntrada;
      EdtDtHrOcor.Text := '';

      EdtCdMotori.SetFocus;
end;

procedure TMov0018.ValidarEntrada;
begin
      if (StrToIntDef(EdtCdEmpres.Text,0) = 0) then
      begin
            EdtCdEmpres.SetFocus;
            raise Exception.Create('Informe empresa!');
      end;

      try
          StrToDateTime(EdtDtHrOcor.Text);
      Except
          EdtDtHrOcor.SetFocus;
          raise Exception.Create('Data e Hora inválidos!');
      end;

      if (EdtDtHrOcor.BrAsDateTime < gDtOcoAux) then
      begin
          EdtDtHrOcor.SetFocus;
          raise Exception.Create('Data e Hora inválidos!' + Chr(13) +
                                 'Lançamento atual menor que o último.');
      end;

      if (gDtOcoAux = EdtDtHrOcor.BrAsDateTime) then
      begin
            EdtDtHrOcor.Text := FormatDateTime('dd/mm/yyyy hh:mm:ss',
                                               IncSecond(StrToDateTime(EdtDtHrOcor.Text), 1));
      end;

end;

procedure TMov0018.BtnGravarClick(Sender: TObject);
var lConexao : TSDmTraClient;
    lCcParam : TClientDataSet;
    lDsResult: String;
begin
      inherited;
      ValidarEntrada;

      try
          lCcParam := TClientDataSet.Create(Self);
          lCcParam.FieldDefs.Add('NmFrmOri', ftString,  50);
          lCcParam.FieldDefs.Add('CdCarga' , ftInteger,  0);
          lCcParam.FieldDefs.Add('CdMotori', ftInteger,  0);
          lCcParam.FieldDefs.Add('CdTipOco', ftInteger,  0);
          lCcParam.FieldDefs.Add('CdVeicul', ftInteger,  0);
          lCcParam.FieldDefs.Add('CdEmpres', ftInteger,  0);
          lCcParam.FieldDefs.Add('DtOcorre', ftDateTime, 0);
          lCcParam.FieldDefs.Add('DtProces', ftDateTime, 0);
          lCcParam.FieldDefs.Add('DtUltOco', ftDateTime, 0);
          lCcParam.FieldDefs.Add('TpTipOco', ftString,   1);
          lCcParam.FieldDefs.Add('VrFatDSR', ftFloat,    0);
          lCcParam.FieldDefs.Add('HrIniJor', ftString,  10);
          lCcParam.FieldDefs.Add('QtHorJor', ftFloat,    0);
          lCcParam.CreateDataSet;

          lCcParam.Append;
          lCcParam.FieldByName('NmFrmOri').AsString  := Self.Name;
          lCcParam.FieldByName('CdCarga' ).AsInteger := StrToInt(EdtCdCarga.Text);
          lCcParam.FieldByName('CdMotori').AsInteger := StrToInt(EdtCdMotori.Text);
          lCcParam.FieldByName('CdTipOco').AsInteger := StrToInt(EdtCdTipoco.Text);
          lCcParam.FieldByName('CdVeicul').AsInteger := StrToInt(EdtCdVeicul.Text);
          lCcParam.FieldByName('CdEmpres').AsInteger := StrToInt(EdtCdEmpres.Text);
          lCcParam.FieldByName('DtOcorre').AsDateTime:= EdtDtHrOcor.BrAsDateTime;
          lCcParam.FieldByName('VrFatDSR').AsFloat   :=
                                        StrToFloat(DmSrvApl.BrvDicionario.ParametroDaEmpresa(16,0));

          lCcParam.FieldByName('HrIniJor').AsString  :=
                                                   DmSrvApl.BrvDicionario.ParametroDaEmpresa(17,0);
          lCcParam.FieldByName('QtHorJor').AsFloat   :=
                                        StrToFloat(DmSrvApl.BrvDicionario.ParametroDaEmpresa(18,0));

          EdtDtHrDig.BrAsDateTime := Now();
          lCcParam.FieldByName('DtProces').AsDateTime:= EdtDtHrDig.BrAsDateTime;
          lCcParam.FieldByName('TpTipOco').AsString  := LblStOcorre.Caption;
          lCcParam.FieldByName('DtUltOco').AsDateTime:= gDtOcorre;
          lCcParam.Post;

          lConexao := TSDmTraClient.Create(DmSrvApl.SrvAplica.DBXConnection);
          lDsResult:= lConexao.GravarOcorrenciaMotorista(DmSrvApl.BrvDicionario.Credencial,
                                                                                     lCcParam.Data);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
          BtnCancelarClick(BtnCancelar);
          MessageDlg('Ocorrência gravada com sucesso!!!', mtInformation, [mbok], 0);

      finally
          FreeAndNil(lCcParam);
      end;
end;

procedure TMov0018.BtnBuscarClick(Sender: TObject);
var lcdtipoco : Integer;
begin
      inherited;

      gDtOcorre := 0;
      gDtOcoAux := 0;
      EdtCdEmpres.Enabled := true;
      EdtCdVeicul.Text := '0';

      if (StrToIntDef(EdtCdMotori.Text, 0) > 0) then
      begin
            if (StrToIntDef(LblStOcorre.Caption, 0) > 0) then
            begin
                  PnlOcorrencia.Visible := True;
                  PnlBusca.Enabled := False;

                  EdtDtHrDig.BrAsDateTime := Now();

                  if (StrToIntDef(EdtCdEmpres.Text, 0) > 0) then
                  begin
                        EdtCdEmpres.BrValidarEntrada;

                        CcT018.Close;
                        CcT018.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(155,
                                                          '<%cdmotori%>;' + EdtCdMotori.Text, Name);
                        CcT018.Open;

                        if (CcT018.RecordCount > 0) then
                        begin
                              gDtOcoAux := CcT018.FieldByName('DtOcorre').AsDateTime;

                              if (StrToInt(LblStOcorre.Caption) <>
                                                      CcT018.FieldByName('StOcorre').AsInteger) then
                              begin
                                    if (StrToInt(LblStOcorre.Caption) = 1) then
                                    begin
                                          if (CcT018.FieldByName('StOcorre').AsInteger <> 3) then
                                          begin
                                                MessageDlg('Última Ocorrência requer uma ENTRADA!',
                                                            mtWarning, [mbok], 0);

                                                PnlOcorrencia.Visible := False;
                                                PnlBusca.Enabled := True;

                                                EdtCdTipoco.SetFocus;
                                          end else
                                          begin
                                                if (CcT018.FieldByName('CdEmpres').AsInteger =
                                                                       EdtCdEmpres.BrAsInteger) then
                                                begin
                                                      EdtCdEmpres.Enabled := False;
                                                      EdtDtHrOcor.SetFocus;
                                                end else
                                                begin
                                                      MessageDlg('Problema ao lançar DSR!!!' +
                                                                  Chr(13)+
                                                                 'Verifique a empresa...',
                                                                  mtWarning, [mbok], 0);

                                                      PnlOcorrencia.Visible := False;
                                                      PnlBusca.Enabled := True;

                                                      EdtCdTipoco.SetFocus;
                                                end;
                                          end;
                                    end else
                                    begin
                                          if (StrToInt(LblStOcorre.Caption) = 3) then
                                          begin
                                                if (CcT018.FieldByName('StOcorre').AsInteger
                                                                                          <> 1) then
                                                begin
                                                     {EdtCdCarga.Text :=
                                                          CcT018.FieldByName('CdCarga').AsString;
                                                      EdtCdVeicul.Text:=
                                                          CcT018.FieldByName('CdVeicul').AsString;
                                                      EdtCdVeicul.BrValidarEntrada;}

                                                      gDtOcorre  :=
                                                          CcT018.FieldByName('DtOcorre').AsDateTime;
                                                      EdtCdEmpres.Text :=
                                                          CcT018.FieldByName('CdEmpres').AsString;

                                                      EdtCdEmpres.BrValidarEntrada;

                                                      EdtDtHrOcor.SetFocus;
                                                end else
                                                begin
                                                      MessageDlg(
                                                            'Última Ocorrência requer uma SAIDA!',
                                                            mtWarning, [mbok], 0);
                                                      PnlOcorrencia.Visible := False;
                                                      PnlBusca.Enabled := True;

                                                      EdtCdTipoco.SetFocus;
                                                end;

                                          end else
                                          begin
                                                EdtCdCarga.SetFocus;
                                               {CcT018.Close;
                                                CcT018.Data := DmSrvApl.BrvDicionario.
                                                          RetornaDadosSqlCadastro(156,
                                                          '<%cdmotori%>;' + EdtCdMotori.Text, Name);
                                                CcT018.Open;

                                                if (CcT018.RecordCount > 0) then
                                                begin
                                                      EdtCdCarga.Text :=
                                                            CcT018.FieldByName('CdCarga').AsString;
                                                      EdtCdVeicul.Text:=
                                                            CcT018.FieldByName('CdVeicul').AsString;

                                                      EdtCdVeicul.BrValidarEntrada;
                                                      EdtDtHrOcor.SetFocus;
                                                end else
                                                begin
                                                      EdtCdCarga.SetFocus;
                                                end;}
                                          end;
                                    end;

                              end else
                              begin
                                    MessageDlg('Ocorrência idêntica a anterior!', mtWarning,
                                                                                         [mbok], 0);
                                    PnlOcorrencia.Visible := False;
                                    PnlBusca.Enabled := True;

                                    EdtCdTipoco.SetFocus;
                              end;
                        end else
                        begin
                              if (StrToInt(LblStOcorre.Caption) = 2) then
                              begin
                                    EdtCdCarga.SetFocus;
                                   {CcT018.Close;
                                    CcT018.Data := DmSrvApl.BrvDicionario.
                                              RetornaDadosSqlCadastro(156,
                                              '<%cdmotori%>;' + EdtCdMotori.Text, Name);
                                    CcT018.Open;

                                    if (CcT018.RecordCount > 0) then
                                    begin
                                          EdtCdCarga.Text :=
                                                CcT018.FieldByName('CdCarga').AsString;
                                          EdtCdVeicul.Text:=
                                                CcT018.FieldByName('CdVeicul').AsString;

                                          EdtCdVeicul.BrValidarEntrada;
                                          EdtDtHrOcor.SetFocus;
                                    end else
                                    begin
                                          EdtCdCarga.SetFocus;
                                    end;}
                              end else
                              begin
                                    MessageDlg('Motorista sem ocorrências anteriores!!!' + Chr(13) +
                                                                        'Favor informar uma SAIDA.',
                                                                        mtInformation, [mbok], 0);
                                    PnlOcorrencia.Visible := False;
                                    PnlBusca.Enabled := True;

                                    EdtCdTipoco.SetFocus;
                              end;
                        end;

                  end else
                  begin
                        EdtCdEmpres.SetFocus;
                  end;
            end else
            begin
                  MessageDlg('Informe uma ocorrência!', mtWarning, [mbok], 0);
                  EdtCdTipoco.SetFocus;
            end;

      end else
      begin
            MessageDlg('Informe um motorista!', mtWarning, [mbok], 0);
            EdtCdMotori.SetFocus;
      end;
end;

procedure TMov0018.EdtCdCargaBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdCarga.BrParams.Clear;
      EdtCdCarga.BrParams.Add('<%CdMotori%>;'+ EdtCdMotori.Text);
end;

procedure TMov0018.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TMov0018.EdtCdTipocoBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdTipoco.BrParams.Clear;
      EdtCdTipoco.BrParams.Add('<%DsWhere%>;' + gVrParam);
end;

initialization
      RegisterClass(TMov0018);

finalization
      UnRegisterClass(TMov0018);

end.
