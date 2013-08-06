unit UCon0016;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons,
  BrvSpeedButton, BrvDbNavCop, Mask, BrvCustomMaskEdit, BrvEditDate, BrvRetCon,
  StdCtrls, BrvCustomEdit, BrvEditNum, CheckLst, BrvCheckListBox, BrvBitBtn,
  ComCtrls, Grids, DBGrids, DB, DBClient, BrvServerProgress, BrvClientDataSet, BrvExcel, BrvDbGrids,
  BrvDbGrid, mxExport;

type
  TCon0016 = class(TMov0000)
    PgcFundo: TPageControl;
    TbsFiltro: TTabSheet;
    Panel4: TPanel;
    Panel1: TPanel;
    BtnPesquisa: TBrvBitBtn;
    TbsConsulta: TTabSheet;
    Panel3: TPanel;
    Panel2: TPanel;
    BtnExcel: TBrvBitBtn;
    BtnVoltar: TBrvBitBtn;
    Panel5: TPanel;
    StgFiltros: TStringGrid;
    BrvListParam: TBrvListParam;
    BrvServerProgress: TBrvServerProgress;
    DtsVT002: TDataSource;
    CpVT002: TBrvClientDataSet;
    Panel6: TPanel;
    Panel8: TPanel;
    LblQtReg: TLabel;
    Label3: TLabel;
    Label8: TLabel;
    Label9: TLabel;
    Label10: TLabel;
    Label11: TLabel;
    Label13: TLabel;
    Label14: TLabel;
    Label15: TLabel;
    Label16: TLabel;
    Label17: TLabel;
    Label18: TLabel;
    Label19: TLabel;
    Label22: TLabel;
    Label23: TLabel;
    LblNrPeso: TBrvRetCon;
    LblNrPesLot: TBrvRetCon;
    LblVrMercad: TBrvRetCon;
    LblVrFreteP: TBrvRetCon;
    LblVrTotPre: TBrvRetCon;
    LblVrOutros: TBrvRetCon;
    LblVrFreteV: TBrvRetCon;
    LblVrSecCat: TBrvRetCon;
    LblVrDesPac: TBrvRetCon;
    LblVrPedagi: TBrvRetCon;
    LblVrSubTri: TBrvRetCon;
    LblVrIcms: TBrvRetCon;
    LblVrIssQn: TBrvRetCon;
    Label1: TLabel;
    Label20: TLabel;
    Label21: TLabel;
    Label24: TLabel;
    Label25: TLabel;
    Label26: TLabel;
    LblCoTotEnt: TBrvRetCon;
    LblCoForPra: TBrvRetCon;
    LblNoTotEnt: TBrvRetCon;
    LblCoEntLot: TBrvRetCon;
    LblCoDenPra: TBrvRetCon;
    LblNoDenPra: TBrvRetCon;
    LblNoForPra: TBrvRetCon;
    Panel7: TPanel;
    DbgFiltro: TBrvDbGrid;
    Splitter2: TSplitter;
    LblTpOcorre: TLabel;
    CblTpOcorre: TBrvCheckListBox;
    LblTpTransp: TLabel;
    CblTpTransp: TBrvCheckListBox;
    LblCdEmpres: TLabel;
    CblCdEmpres: TBrvCheckListBox;
    mxExcel: TmxDataSetExport;
    Panel9: TPanel;
    LblQtDiaEnt: TLabel;
    EdtQtDiaEnt: TBrvEditNum;
    RdgPosNota: TRadioGroup;
    LblDtFinal: TLabel;
    EdtDtFinal: TBrvEditDate;
    LblDtInicia: TLabel;
    EdtDtInicia: TBrvEditDate;
    LblCdTitula: TLabel;
    EdtCdTitula: TBrvEditNum;
    LblRsTitula: TBrvRetCon;
    Label2: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label12: TLabel;
    Label27: TLabel;
    Label28: TLabel;
    Label29: TLabel;
    Label30: TLabel;
    Label31: TLabel;
    Label32: TLabel;
    Label33: TLabel;
    procedure FormCreate(Sender: TObject);
    procedure BtnPesquisaClick(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);
    procedure BtnExcelClick(Sender: TObject);
  private
    procedure ValidarEntradaDados;
  public
    { Public declarations }
  end;

var
  Con0016: TCon0016;

implementation

uses UDmSrvApl, BrvDominiosXE;

{$R *.dfm}

procedure TCon0016.BtnExcelClick(Sender: TObject);
var lNrIndice : Integer;
begin
      mxExcel.Captions.Clear;
      for lNrIndice := 0 to DbgFiltro.Columns.Count-1 do
      begin
            mxExcel.Captions.Add(DbgFiltro.Columns[lNrIndice].Title.Caption);
      end;
      mxExcel.Select;
end;

procedure TCon0016.ValidarEntradaDados;
var lDsEmp      : String;
    lLsTpDomini : TStringList;
begin
      BrvListParam.Clear;

      if (CblCdEmpres.BrCheckedCount = 0) then
      begin
            CblCdEmpres.SetFocus;
            raise Exception.Create('Selecione a(s) ' + LblCdEmpres.Caption + '!');
      end else
      begin
            lDsEmp := CblCdEmpres.BrCheckedList;
            BrvListParam.Add('CdEmpres', LblCdEmpres.Caption, lDsEmp, lDsEmp);
      end;

      try
          if (CblTpTransp.BrCheckedCount = 0) then
          begin
                CblTpTransp.SetFocus;
                raise Exception.Create('Selecione o(s) ' + LblTpTransp.Caption + '!');
          end else
          begin
                lLsTpDomini := CblTpTransp.BrCheckedListDominio;
                BrvListParam.Add('TpTransp', LblTpTransp.Caption, lLsTpDomini[1], lLsTpDomini[0]);
          end;

          if (CblTpOcorre.BrCheckedCount > 0) then
          begin
                lLsTpDomini.Clear;
                lLsTpDomini := CblTpOcorre.BrCheckedListDominio;
                BrvListParam.Add('TpOcorre', LblTpOcorre.Caption, lLsTpDomini[1],
                                        ' and oc.TpOcorre in (' + lLsTpDomini[0] + ')');
          end else
          begin
                BrvListParam.Add('TpOcorre', '', '', '');
          end;
      finally
          FreeAndNil(lLsTpDomini);
      end;

      if (StrToIntDef(EdtCdTitula.Text, 0) > 0)  then
      begin
            BrvListParam.Add('CdTitula', LblCdTitula.Caption, EdtCdTitula.Text + ' - ' +
              LblRsTitula.Text, ' and vc.CdTomado in (Select CdTitula From G011 Where CdMatriz = ' +
                                                                            EdtCdTitula.Text + ')');
      end else
      begin
            BrvListParam.Add('CdTitula', '', '', '');
      end;

      if (EdtDtInicia.BrDataVazia) then
      begin
            EdtDtInicia.SetFocus;
            raise Exception.Create('Informe uma ' + LblDtInicia.Caption + ' para a consulta!');
      end;
      if (not EdtDtInicia.BrDataValida) then
      begin
            EdtDtInicia.SetFocus;
            raise Exception.Create('Verifique a ' + LblDtInicia.Caption + ' informada!');
      end;
      if (EdtDtFinal.BrDataVazia) then
      begin
            EdtDtFinal.SetFocus;
            raise Exception.Create('Informe uma ' + LblDtFinal.Caption + ' para a consulta!');
      end;
      if (not EdtDtFinal.BrDataValida) then
      begin
            EdtDtFinal.SetFocus;
            raise Exception.Create('Verifique a ' + LblDtFinal.Caption + ' informada!');
      end;
      if (EdtDtFinal.BrAsDate < EdtDtInicia.BrAsDate) then
      begin
            EdtDtFinal.SetFocus;
            raise Exception.Create(LblDtFinal.Caption + ' deve ser maior ou igual à ' +
                                                                         LblDtInicia.Caption + '!');
      end;

      if (StrToIntDef(EdtQtDiaEnt.Text, 0) > 0) then
      begin
            BrvListParam.Add('QtDiaEnt', LblQtDiaEnt.Caption, EdtQtDiaEnt.Text ,
                                                          ' and vc.QtDiaEnt > ' + EdtQtDiaEnt.Text);
      end else
      begin
            BrvListParam.Add('QtDiaEnt', '', '', '');
      end;

      if RdgPosNota.ItemIndex = 0 then
      begin
            BrvListParam.Add('NrNotSct', RdgPosNota.Caption, RdgPosNota.Items[RdgPosNota.ItemIndex],
                          'NotasCTRC(vc.CdEmpres, vc.DsModeNf, vc.NrSeriNf, vc.CdCtrc) As NrNota,');
            BrvListParam.Add('NrNotGby', '', '', '');
      end else if RdgPosNota.ItemIndex = 1 then
      begin
            BrvListParam.Add('NrNotSct', RdgPosNota.Caption, RdgPosNota.Items[RdgPosNota.ItemIndex],
                          'nc.NrNota,');
            BrvListParam.Add('NrNotGby', '', '', 'nc.NrNota,');
      end;

      BrvListParam.Add('DtInicia', LblDtInicia.Caption, EdtDtInicia.Text, EdtDtInicia.Text);
      BrvListParam.Add('DtFinal',  LblDtFinal.Caption,  EdtDtFinal.Text,  EdtDtFinal.Text);
end;

procedure TCon0016.BtnPesquisaClick(Sender: TObject);
var lSglCdCon : TStringList;
begin
      inherited;

      LblNrPeso.Text   := '0';
      LblNrPesLot.Text := '0';
      LblVrMercad.Text := '0';
      LblVrFreteP.Text := '0';
      LblVrTotPre.Text := '0';
      LblVrFreteV.Text := '0';
      LblVrSecCat.Text := '0';
      LblVrDesPac.Text := '0';
      LblVrPedagi.Text := '0';
      LblVrOutros.Text := '0';
      LblVrSubTri.Text := '0';
      LblVrIcms.Text   := '0';
      LblVrIssQn.Text  := '0';
      LblCoTotEnt.Text := '0';
      LblNoTotEnt.Text := '0';
      LblCoDenPra.Text := '0';
      LblNoDenPra.Text := '0';
      LblCoForPra.Text := '0';
      LblNoForPra.Text := '0';
      LblCoEntLot.Text := '0';

      ValidarEntradaDados;

      try
          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
          CpVT002.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(222,
                                                                 BrvListParam.AsBrParam, Self.Name);
      finally
          BrvServerProgress.Stop;
      end;

      BrvListParam.SetStgParam(StgFiltros);
      if (CpVT002.RecordCount > 0) then
      begin
            LblQtReg.Caption := FormatFloat('0', CpVT002.RecordCount) + ' Registro(s)';

            lSglCdCon := TStringList.Create;
            lSglCdCon.Sorted := true;
            lSglCdCon.Duplicates := dupIgnore;   // ignora valores duplicados

            while not CpVT002.Eof do
            begin
                  if (CpVT002.FieldByName('NrPeso').Text <> '') then
                  begin
                        LblNrPeso.Text   :=  FloatToStr(StrToFloat(LblNrPeso.Text)+
                                                    StrToFloat(CpVT002.FieldByName('NrPeso').Text));
                        if (CpVT002.FieldByName('NrPesLot').Text <> '') then
                        begin
                              LblNrPesLot.Text :=  FloatToStr(StrToFloat(LblNrPesLot.Text)+
                                          StrToFloat(CpVT002.FieldByName('NrPeso').Text)+
                                         (StrToFloat(CpVT002.FieldByName('NrPesLot').Text)-
                                          StrToFloat(CpVT002.FieldByName('NrPeso').Text)));
                              LblCoEntLot.Text :=  FloatToStr(StrToFloat(LblCoEntLot.Text)+1);
                        end else
                        begin
                              LblNrPesLot.Text :=  FloatToStr(StrToFloat(LblNrPesLot.Text) +
                                                    StrToFloat(CpVT002.FieldByName('NrPeso').Text));
                        end;
                  end;
                  if (CpVT002.FieldByName('VrMercad').Text <> '') then
                  begin
                        LblVrMercad.Text :=  FloatToStr(StrToFloat(LblVrMercad.Text) +
                                                  StrToFloat(CpVT002.FieldByName('VrMercad').Text));
                  end;
                  if (CpVT002.FieldByName('VrFreteP').Text <> '') then
                  begin
                        LblVrFreteP.Text :=  FloatToStr(StrToFloat(LblVrFreteP.Text) +
                                                  StrToFloat(CpVT002.FieldByName('VrFreteP').Text));
                  end;
                  if (CpVT002.FieldByName('VrTotPre').Text <> '') then
                  begin
                        LblVrTotPre.Text :=  FloatToStr(StrToFloat(LblVrTotPre.Text) +
                                                  StrToFloat(CpVT002.FieldByName('VrTotPre').Text));
                  end;
                  if (CpVT002.FieldByName('VrFreteV').Text <> '') then
                  begin
                        LblVrFreteV.Text :=  FloatToStr(StrToFloat(LblVrFreteV.Text) +
                                                  StrToFloat(CpVT002.FieldByName('VrFreteV').Text));
                  end;
                  if (CpVT002.FieldByName('VrSecCat').Text <> '') then
                  begin
                        LblVrSecCat.Text :=  FloatToStr(StrToFloat(LblVrSecCat.Text) +
                                                  StrToFloat(CpVT002.FieldByName('VrSecCat').Text));
                  end;
                  if (CpVT002.FieldByName('VrDesPac').Text <> '') then
                  begin
                        LblVrDesPac.Text :=  FloatToStr(StrToFloat(LblVrDesPac.Text) +
                                                  StrToFloat(CpVT002.FieldByName('VrDesPac').Text));
                  end;
                  if (CpVT002.FieldByName('VrPedagi').Text <> '') then
                  begin
                        LblVrPedagi.Text :=  FloatToStr(StrToFloat(LblVrPedagi.Text) +
                                                  StrToFloat(CpVT002.FieldByName('VrPedagi').Text));
                  end;
                  if (CpVT002.FieldByName('VrOutros').Text <> '') then
                  begin
                        LblVrOutros.Text :=  FloatToStr(StrToFloat(LblVrOutros.Text) +
                                                  StrToFloat(CpVT002.FieldByName('VrOutros').Text));
                  end;
                  if (CpVT002.FieldByName('VrSubTri').Text <> '') then
                  begin
                        LblVrSubTri.Text :=  FloatToStr(StrToFloat(LblVrSubTri.Text) +
                                                  StrToFloat(CpVT002.FieldByName('VrSubTri').Text));
                  end;
                  if (CpVT002.FieldByName('VrIcms').Text <> '') then
                  begin
                        LblVrIcms.Text   :=  FloatToStr(StrToFloat(LblVrIcms.Text) +
                                                  StrToFloat(CpVT002.FieldByName('VrIcms').Text));
                  end;
                  if (CpVT002.FieldByName('VrIssQn').Text <> '') then
                  begin
                        LblVrIssQn.Text  :=  FloatToStr(StrToFloat(LblVrIssQn.Text) +
                                                  StrToFloat(CpVT002.FieldByName('VrIssQn').Text));
                  end;

                  if (CpVT002.FieldByName('QtAtrazo').Text <> '') then
                  begin
                        if (StrToFloat(CpVT002.FieldByName('QtAtrazo').Text) >= 1) then
                        begin
                              LblCoForPra.Text := FloatToStr(StrToFloat(LblCoForPra.Text)+1);
                        end;
                  end;
                  if (CpVT002.FieldByName('QtNf').Text <> '') then
                  begin
                        if (StrToFloat(CpVT002.FieldByName('QtNf').Text) > 0) then
                        begin
                              LblNoTotEnt.Text := FloatToStr(StrToFloat(LblNoTotEnt.Text)+
                                              StrToFloat(CpVT002.FieldByName('QtNf').Text));
                              if (CpVT002.FieldByName('QtAtrazo').Text <> '') then
                              begin
                                    if (StrToFloat(CpVT002.FieldByName('QtAtrazo').Text) >= 1) then
                                    begin
                                          LblNoForPra.Text := FloatToStr(StrToFloat(
                                                      LblNoForPra.Text)+StrToFloat(
                                                      CpVT002.FieldByName('QtNf').Text));
                                    end;
                              end;
                        end;
                  end;
                  lSglCdCon.Add(CpVT002.FieldByName('CdEmpres').Text +','+
                                CpVT002.FieldByName('CdCTRC'  ).Text +','+
                                CpVT002.FieldByName('DsModeNf').Text +','+
                                CpVT002.FieldByName('NrSeriNf').Text);
                  CpVT002.Next;
            end;
            LblNrPeso.Text   := FormatFloat('##,###,##0.00', StrToFloat(LblNrPeso.Text));
            LblNrPesLot.Text := FormatFloat('##,###,##0.00', StrToFloat(LblNrPesLot.Text));
            LblVrMercad.Text := FormatFloat('##,###,##0.00', StrToFloat(LblVrMercad.Text));
            LblVrFreteP.Text := FormatFloat('##,###,##0.00', StrToFloat(LblVrFreteP.Text));
            LblVrTotPre.Text := FormatFloat('##,###,##0.00', StrToFloat(LblVrTotPre.Text));
            LblVrFreteV.Text := FormatFloat('##,###,##0.00', StrToFloat(LblVrFreteV.Text));
            LblVrSecCat.Text := FormatFloat('##,###,##0.00', StrToFloat(LblVrSecCat.Text));
            LblVrDesPac.Text := FormatFloat('##,###,##0.00', StrToFloat(LblVrDesPac.Text));
            LblVrPedagi.Text := FormatFloat('##,###,##0.00', StrToFloat(LblVrPedagi.Text));
            LblVrOutros.Text := FormatFloat('##,###,##0.00', StrToFloat(LblVrOutros.Text));
            LblVrSubTri.Text := FormatFloat('##,###,##0.00', StrToFloat(LblVrSubTri.Text));
            LblVrIcms.Text   := FormatFloat('##,###,##0.00', StrToFloat(LblVrIcms.Text));
            LblVrIssQn.Text  := FormatFloat('##,###,##0.00', StrToFloat(LblVrIssQn.Text));

            LblCoTotEnt.Text := FormatFloat('0', lSglCdCon.Count);
            lSglCdCon.Free;

            LblNoTotEnt.Text := FormatFloat('0', StrToFloat(LblNoTotEnt.Text));

            LblCoDenPra.Text := FormatFloat('0', StrToFloat(LblCoTotEnt.Text)-
                                                                  StrToFloat(LblCoForPra.Text));
            LblNoDenPra.Text := FormatFloat('0', StrToFloat(LblNoTotEnt.Text)-
                                                                  StrToFloat(LblNoForPra.Text));
            LblCoForPra.Text := FormatFloat('0', StrToFloat(LblCoForPra.Text));
            LblNoForPra.Text := FormatFloat('0', StrToFloat(LblNoForPra.Text));
            LblCoDenPra.Text := LblCoDenPra.Text+' / '+
                        FormatFloat('##0.00',
                           (100/StrToFloat(LblCoTotEnt.Text))*StrToFloat(LblCoDenPra.Text))+'%';
            LblNoDenPra.Text := LblNoDenPra.Text+' / '+
                        FormatFloat('##0.00',
                           (100/StrToFloat(LblNoTotEnt.Text))*StrToFloat(LblNoDenPra.Text))+'%';
            LblCoForPra.Text := LblCoForPra.Text+' / '+
                        FormatFloat('##0.00',
                           (100/StrToFloat(LblCoTotEnt.Text))*StrToFloat(LblCoForPra.Text))+'%';
            LblNoForPra.Text := LblNoForPra.Text+' / '+
                        FormatFloat('##0.00',
                           (100/StrToFloat(LblNoTotEnt.Text))*StrToFloat(LblNoForPra.Text))+'%';

            TFloatField(CpVT002.FieldByName('CdEmpres')).DisplayFormat := '000';
            TFloatField(CpVT002.FieldByName('CdCtrc'  )).DisplayFormat := '00000000';
            TFloatField(CpVT002.FieldByName('NrFatura')).DisplayFormat := '00000000';
            TFloatField(CpVT002.FieldByName('CdConsig')).DisplayFormat := '00000000';
            TFloatField(CpVT002.FieldByName('NrConRas')).DisplayFormat := '00000000';
            TFloatField(CpVT002.FieldByName('NrPeso'  )).DisplayFormat := '##,###,##0.00';
            TFloatField(CpVT002.FieldByName('NrPesLot')).DisplayFormat := '##,###,##0.00';
            TFloatField(CpVT002.FieldByName('VrMercad')).DisplayFormat := '##,###,##0.00';
            TFloatField(CpVT002.FieldByName('VrFreteP')).DisplayFormat := '##,###,##0.00';
            TFloatField(CpVT002.FieldByName('VrTotPre')).DisplayFormat := '##,###,##0.00';
            TFloatField(CpVT002.FieldByName('VrFreteV')).DisplayFormat := '##,###,##0.00';
            TFloatField(CpVT002.FieldByName('VrSecCat')).DisplayFormat := '##,###,##0.00';
            TFloatField(CpVT002.FieldByName('VrDesPac')).DisplayFormat := '##,###,##0.00';
            TFloatField(CpVT002.FieldByName('VrPedagi')).DisplayFormat := '##,###,##0.00';
            TFloatField(CpVT002.FieldByName('VrOutros')).DisplayFormat := '##,###,##0.00';
            TFloatField(CpVT002.FieldByName('VrSubTri')).DisplayFormat := '##,###,##0.00';
            TFloatField(CpVT002.FieldByName('VrIcms'  )).DisplayFormat := '##,###,##0.00';
            TFloatField(CpVT002.FieldByName('VrIssQn' )).DisplayFormat := '##,###,##0.00';

            PgcFundo.ActivePage := TbsConsulta;
            CpVT002.First;
      end else
      begin
            MessageDlg('Nenhum registro encontrado!!!', mtInformation, [mbok], 0);
      end;
end;

procedure TCon0016.BtnVoltarClick(Sender: TObject);
begin
      inherited;
      PgcFundo.ActivePage := TbsFiltro;
end;

procedure TCon0016.FormCreate(Sender: TObject);
begin
      inherited;
      TbsFiltro.TabVisible   := False;
      TbsConsulta.TabVisible := False;
      PgcFundo.ActivePage    := TbsFiltro;

      CarregaEmpresas(CblCdEmpres, True);
      CarregaDominios(CblTpTransp, 'T002', 'TPTRANSP', True);
      CarregaDominios(CblTpOcorre, 'G005', 'TPOCORRE');

      EdtDtInicia.Text := FormatDateTime('dd/mm/yyyy', Now());
      EdtDtFinal.Text  := FormatDateTime('dd/mm/yyyy', Now());
      StgFiltros.ColWidths[0] := 200;
      StgFiltros.ColWidths[1] := 500;
end;

initialization
      RegisterClass(TCon0016);

finalization
      UnRegisterClass(TCon0016);

end.
