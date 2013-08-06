unit UCon0014;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvBitBtn, BrvRetCon,
  BrvEditNum, Grids, BrvDbGrids, BrvDbGrid, DB, DBClient, DateUtils, BrvExcel, mxExport, DBGrids,
  BrvAlertProgress, BrvCustomEdit, BrvListParam, ImgList, Menus;

type
  TCon0014 = class(TMov0000)
    PnlCabeca: TPanel;
    Label1: TLabel;
    EdtCdEmpres: TBrvEditNum;
    EdtDsEmpres: TBrvRetCon;
    Panel1: TPanel;
    BtnLocaliza: TBrvBitBtn;
    bdgRegistros: TBrvDbGrid;
    CcT009: TClientDataSet;
    DtsT009: TDataSource;
    CcT009Aux: TClientDataSet;
    BtnExporta: TBrvBitBtn;
    mxExcel: TmxDataSetExport;
    CbxComMov: TCheckBox;
    BrvAlertProgress: TBrvAlertProgress;
    procedure BtnLocalizaClick(Sender: TObject);
    procedure BtnExportaClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Con0014: TCon0014;

implementation

uses UDmSrvApl, BrvFuncoesXE;

{$R *.dfm}

procedure TCon0014.BtnLocalizaClick(Sender: TObject);
var lPrEmpres : String;
    lMinutes  : Integer;
begin
      inherited;
      BtnExporta.Visible := True;

      if (StrToIntDef(EdtCdEmpres.Text, 0) = 0) then
      begin
            lPrEmpres := '<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes;
      end else
      begin;
            lPrEmpres := '<%CdEmpres%>;' + EdtCdEmpres.Text;
      end;

      BrvAlertProgress.BrCaption  := self.Caption;
      BrvAlertProgress.BrProcesso := 'Reunindo informações';
      //BrvAlertProgress.BrShowInfinity := True;

      CcT009Aux.Close;
      CcT009Aux.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(160, lPrEmpres, Self.Name);
      CcT009Aux.Open;

      CcT009Aux.First;

      {Somente para testes
       CcT009Aux.Filtered := False;
       CcT009Aux.Filter   := 'CdMotori = 1000';
       CcT009Aux.Filtered := True;}

      try
          if CcT009.Active then
          begin
                CcT009.EmptyDataSet;
          end;

          CcT009.Close;

          CcT009.FieldDefs.Clear;
          CcT009.FieldDefs.Add('CdEmpMot', ftInteger ,  0);
          CcT009.FieldDefs.Add('CdMotori', ftInteger ,  0);
          CcT009.FieldDefs.Add('NmMotori', ftString  , 50);
          CcT009.FieldDefs.Add('CdTipOco', ftInteger ,  0);
          CcT009.FieldDefs.Add('DsTipOco', ftString  , 50);
          CcT009.FieldDefs.Add('DtOcorre', ftDateTime,  0);
          CcT009.FieldDefs.Add('DtRetorn', ftDateTime,  0);
          CcT009.FieldDefs.Add('DtDSR'   , ftDateTime,  0);
          CcT009.FieldDefs.Add('QtHrDSR' , ftString  , 20);
          CcT009.FieldDefs.Add('QtHrRet' , ftString  , 20);
          CcT009.FieldDefs.Add('SnAtraso', ftString  ,  1);
          CcT009.CreateDataSet;

          CcT009Aux.DisableControls;
          CcT009Aux.First;

          while not CcT009Aux.Eof do
          begin
                CcT009.Append;
                      CcT009.FieldByName('CdEmpMot').AsInteger  :=
                                                       CcT009Aux.FieldByName('CdEmpMot').AsInteger;
                      CcT009.FieldByName('CdMotori').AsInteger  :=
                                                       CcT009Aux.FieldByName('CdMotori').AsInteger;
                      CcT009.FieldByName('NmMotori').AsString   :=
                                                       CcT009Aux.FieldByName('NmMotori').AsString;
                      CcT009.FieldByName('CdTipOco').AsInteger  :=
                                                       CcT009Aux.FieldByName('CdTipOco').AsInteger;

                      if (CcT009Aux.FieldByName('DtDSR').AsFloat > 0) then
                      begin
                            CcT009.FieldByName('DtDSR').AsDateTime :=
                                                          CcT009Aux.FieldByName('DtDSR').AsDateTime;
                      end;

                      CcT009.FieldByName('QtHrRet' ).AsString := '...';
                      CcT009.FieldByName('QtHrDSR' ).AsString := '...';
                      CcT009.FieldByName('SnAtraso').AsString := 'N';

                      if (CcT009Aux.FieldByName('CdTipOco').AsInteger = 1) then
                      begin
                            CcT009.FieldByName('DtRetorn').AsDateTime := IncMinute(
                                              CcT009Aux.FieldByName('DtDSR').AsDateTime,
                                              (StrToInt(
                                               DmSrvApl.BrvDicionario.ParametroDaEmpresa(15,0)) *60)
                                               + CcT009Aux.FieldByName('QtSalJor').AsInteger);

                            if (Now() <= CcT009.FieldByName('DtRetorn').AsDateTime) then
                            begin
                                  CcT009.FieldByName('QtHrRet').AsString :=
                                              BrvFuncoesXE.MinutosEmHoras(
                                                        MinutesBetween(Now(),
                                                        CcT009.FieldByName('DtRetorn').AsDateTime));
                            end else
                            begin
                                  CcT009.FieldByName('QtHrRet').AsString := '-' +
                                              BrvFuncoesXE.MinutosEmHoras(
                                              MinutesBetween(Now(),
                                              CcT009.FieldByName('DtRetorn').AsDateTime));
                                  CcT009.FieldByName('SnAtraso').AsString := 'S';
                            end;

                      end else
                      begin
                            if (CcT009Aux.FieldByName('CdTipOco').AsInteger > 0) then
                            begin
                                  if (CcT009Aux.FieldByName('DtDSR').AsFloat > 0) then
                                  begin
                                        CcT009.FieldByName('DtRetorn').AsDateTime := IncMinute(
                                               CcT009Aux.FieldByName('DtDSR').AsDateTime,
                                              (StrToInt(
                                               DmSrvApl.BrvDicionario.ParametroDaEmpresa(14,0))
                                               * 60) - CcT009Aux.FieldByName('QtMinSal').AsInteger);

                                        lMinutes := MinutesBetween(Now(),
                                                         CcT009.FieldByName('DtRetorn').AsDateTime);

                                        if (lMinutes > 0) then
                                        begin
                                              if (Now() <=
                                                     CcT009.FieldByName('DtRetorn').AsDateTime) then
                                              begin
                                                     CcT009.FieldByName('QtHrDSR').AsString :=
                                                              BrvFuncoesXE.MinutosEmHoras(lMinutes);
                                              end else
                                              begin
                                                    CcT009.FieldByName('QtHrDSR').AsString := '-' +
                                                              BrvFuncoesXE.MinutosEmHoras(lMinutes);
                                                    CcT009.FieldByName('SnAtraso').AsString := 'S';
                                              end;
                                        end;
                                  end;
                            end;
                      end;

                      if (CcT009Aux.FieldByName('CdTipOco').AsInteger > 0) then
                      begin
                            CcT009.FieldByName('DsTipOco').AsString   :=
                             DmSrvApl.BrvDicionario.RetornaValorColunaTabela('T017', 'DsTipOco',
                                            'CdTipOco', CcT009Aux.FieldByName('CdTipOco').AsString);
                            CcT009.FieldByName('DtOcorre').AsDateTime :=
                                                       CcT009Aux.FieldByName('DtOcorre').AsDateTime;
                      end;

                      CcT009.Post;

                CcT009Aux.Next;
          end;

          if (CbxComMov.Checked) then
          begin
                CcT009.Filtered := False;
                CcT009.Filter   := 'DtOcorre is not null';
                CcT009.Filtered := True;
          end else
          begin
                CcT009.Filtered := False;
                CcT009.Filter   := '';
          end;

          CcT009.First;

          //BrvAlertProgress.BrShowInfinity := False;
      finally
          CcT009Aux.EnableControls;
      end;

end;

procedure TCon0014.BtnExportaClick(Sender: TObject);
begin
      inherited;
      mxExcel.Select;
end;

initialization
      RegisterClass(TCon0014);

finalization
      UnRegisterClass(TCon0014);

end.

