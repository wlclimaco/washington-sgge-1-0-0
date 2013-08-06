unit UCad0011;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCad0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  Grids, BrvDbGrids, BrvDbGrid, BrvRetCon, StdCtrls, BrvEditNum, BrvBitBtn, ComCtrls, Mask, DBCtrls,
  BrvDbEdit, DB, DBClient, BrvClientDataSet, BrvCustomEdit;

type
  TCad0011 = class(TCad0000)
    PageFundo: TPageControl;
    TabFiltro: TTabSheet;
    TabCadastro: TTabSheet;
    Label1: TLabel;
    EdtCdEmpres: TBrvEditNum;
    EdtDsEmpres: TBrvRetCon;
    Panel1: TPanel;
    BtnPesquisa: TBrvBitBtn;
    BrvDbGrid: TBrvDbGrid;
    Panel2: TPanel;
    Panel3: TPanel;
    Panel4: TPanel;
    BtnSalvar: TBrvSpeedButton;
    BtnCancelar: TBrvSpeedButton;
    BrvDbEdit1: TBrvDbEdit;
    Label2: TLabel;
    Label20: TLabel;
    LblDsFormul: TBrvRetCon;
    CcB012: TBrvClientDataSet;
    DtsB012: TDataSource;
    Panel5: TPanel;
    StgFiltros: TStringGrid;
    TmrInicia: TTimer;
    BrvListParam: TBrvListParam;
    CcB012CDEMPRES: TFMTBCDField;
    CcB012NRSEQPAR: TFMTBCDField;
    CcB012DSPARAME: TWideStringField;
    CcB012NRSEQFOR: TFMTBCDField;
    CcB012TPFORMUL: TWideStringField;
    CcB012NRPLANO: TFMTBCDField;
    CcB012DSPLANO: TWideStringField;
    CcB012CDHISTOR: TFMTBCDField;
    CcB012DSHISTOR: TWideStringField;
    CcB012NRCONCRE: TFMTBCDField;
    CcB012NMCONTAC: TWideStringField;
    CcB012NRCONDEB: TFMTBCDField;
    CcB012NMCONTAD: TWideStringField;
    CcB012CDCECUCR: TFMTBCDField;
    CcB012DSCENCUSC: TWideStringField;
    CcB012CDCECUDE: TFMTBCDField;
    CcB012DSCENCUSD: TWideStringField;
    BrvDbEdit2: TBrvDbEdit;
    BrvDbEdit3: TBrvDbEdit;
    BrvDbEdit4: TBrvDbEdit;
    Label3: TLabel;
    Label4: TLabel;
    Label6: TLabel;
    BrvDbEdit5: TBrvDbEdit;
    BrvDbEdit6: TBrvDbEdit;
    BrvDbEdit7: TBrvDbEdit;
    BrvDbEdit8: TBrvDbEdit;
    Label5: TLabel;
    Label7: TLabel;
    BrvDbEdit9: TBrvDbEdit;
    BrvDbEdit10: TBrvDbEdit;
    BrvDbEdit11: TBrvDbEdit;
    BrvDbEdit12: TBrvDbEdit;
    BtnVolta: TBrvBitBtn;
    procedure FormCreate(Sender: TObject);
    procedure TmrIniciaTimer(Sender: TObject);
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BtnPesquisaClick(Sender: TObject);
    procedure CcB012AfterCancel(DataSet: TDataSet);
    procedure BtnCancelarClick(Sender: TObject);
    procedure CcB012AfterDelete(DataSet: TDataSet);
    procedure CcB012AfterEdit(DataSet: TDataSet);
    procedure CcB012AfterInsert(DataSet: TDataSet);
    procedure CcB012AfterPost(DataSet: TDataSet);
    procedure CcB012BeforePost(DataSet: TDataSet);
    procedure BtnSalvarClick(Sender: TObject);
    procedure BtnVoltaClick(Sender: TObject);
    procedure BrvDbEdit9OnBeforeConsulta(Sender: TObject);
    procedure BrvDbEdit10BrOnBeforeConsulta(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
    gNrSeqFor: Integer;
    gTpFormul: String;
  end;

var
  Cad0011: TCad0011;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TCad0011.BrvDbEdit10BrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      if (CcB012NRPLANO.AsInteger > 0) then
      begin
            BrvDbEdit10.BrParams.Clear;
            BrvDbEdit10.BrParams.Add('<%NrPlano%>;' + CcB012NRPLANO.AsString);
      end else
      begin
            Raise Exception.Create('Informe um Plano de Contas válido!');
      end;
end;

procedure TCad0011.BrvDbEdit9OnBeforeConsulta(Sender: TObject);
begin
      inherited;
      if (CcB012NRPLANO.AsInteger > 0) then
      begin
            BrvDbEdit9.BrParams.Clear;
            BrvDbEdit9.BrParams.Add('<%NrPlano%>;' + CcB012NRPLANO.AsString);
      end else
      begin
            Raise Exception.Create('Informe um Plano de Contas válido!');
      end;
end;

procedure TCad0011.BtnCancelarClick(Sender: TObject);
begin
      inherited;
      CcB012.Cancel;
end;

procedure TCad0011.BtnPesquisaClick(Sender: TObject);
var lNrIdx : Integer;
    lNrSeqPar : Integer;
    lDsParame : String;
    lLnParame : String;
    lSnInclui : Boolean;
begin
      inherited;
      lSnInclui := False;

      if (Trim(EdtCdEmpres.Text) <> '') then
      begin
            BrvListParam.Clear;
            BrvListParam.Add('CdEmpres', 'Empresa',
                                                   EdtCdEmpres.Text + ' - ' + EdtDsEmpres.Text, '');
            BrvListParam.Add('NmFormul', 'Formulário', LblDsFormul.Text, '');
            BrvListParam.SetStgParam(StgFiltros);

            CcB012.Close;
            CcB012.BrParams.Clear;
            CcB012.BrParams.Add('<%TpFormul%>;' + gTpFormul);
            CcB012.BrParams.Add('<%NrSeqFor%>;' + FormatFloat('0', gNrSeqFor));
            CcB012.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.Text);
            CcB012.Open;

            if (CcB012.RecordCount = 0) then
            begin
                  try
                      CcB012.BeforePost := nil;

                      for lNrIdx := 0 to gStlParCon.Count-1 do
                      begin
                            lLnParame := gStlParCon.Strings[lNrIdx];
                            lNrSeqPar := StrToInt(Copy(lLnParame, 1, Pos(';', lLnParame)-1));
                            lDsParame := Copy(lLnParame, Pos(';', lLnParame)+1, Length(lLnParame));

                            CcB012.Append;
                            CcB012CDEMPRES.AsInteger := EdtCdEmpres.BrAsInteger;
                            CcB012NRSEQPAR.AsInteger := lNrSeqPar;
                            CcB012DSPARAME.AsString  := lDsParame;
                            CcB012TPFORMUL.AsString  := gTpFormul;
                            CcB012NRSEQFOR.AsInteger := gNrSeqFor;
                            CcB012NRPLANO.AsInteger  := 1;
                            CcB012.Post;
                      end;

                      lSnInclui := True;
                  finally
                      CcB012.BeforePost := CcB012BeforePost;
                  end;
            end else
            begin
                  try
                      CcB012.BeforePost := nil;
                      for lNrIdx := 0 to gStlParCon.Count-1 do
                      begin
                            lLnParame := gStlParCon.Strings[lNrIdx];
                            lNrSeqPar := StrToInt(Copy(lLnParame, 1, Pos(';', lLnParame)-1));
                            lDsParame := Copy(lLnParame, Pos(';', lLnParame)+1, Length(lLnParame));

                            if (not CcB012.Locate('NrSeqPar', lNrSeqPar, [loCaseInsensitive])) then
                            begin
                                  CcB012.Append;
                                  CcB012CDEMPRES.AsInteger := EdtCdEmpres.BrAsInteger;
                                  CcB012NRSEQPAR.AsInteger := lNrSeqPar;
                                  CcB012DSPARAME.AsString  := lDsParame;
                                  CcB012TPFORMUL.AsString  := gTpFormul;
                                  CcB012NRSEQFOR.AsInteger := gNrSeqFor;
                                  CcB012NRPLANO.AsInteger  := 1;
                                  CcB012.Post;

                                  lSnInclui := True;
                            end;
                      end;
                  finally
                      CcB012.BeforePost := CcB012BeforePost;
                  end;
            end;

            if (lSnInclui) then
            begin
                  MessageDlg('Favor preencher corretamente as informações!!!',
                                                                         mtConfirmation, [mbok], 0);
            end;

            PageFundo.ActivePageIndex := 1;
      end;
end;

procedure TCad0011.BtnSalvarClick(Sender: TObject);
begin
      inherited;
      CcB012.Post;
end;

procedure TCad0011.BtnVoltaClick(Sender: TObject);
begin
      inherited;
      CcB012.Close;
      PageFundo.ActivePageIndex := 0;
      EdtCdEmpres.SelectAll;
      EdtCdEmpres.SetFocus;
end;

procedure TCad0011.CcB012AfterCancel(DataSet: TDataSet);
begin
      inherited;
      BtnSalvar.Enabled := False;
      BtnCancelar.Enabled := False;
end;

procedure TCad0011.CcB012AfterDelete(DataSet: TDataSet);
begin
      inherited;
      CcB012.BrApplyUpdates;
end;

procedure TCad0011.CcB012AfterEdit(DataSet: TDataSet);
begin
      inherited;
      BtnSalvar.Enabled := True;
      BtnCancelar.Enabled := True;
end;

procedure TCad0011.CcB012AfterInsert(DataSet: TDataSet);
begin
      inherited;
      BtnSalvar.Enabled := True;
      BtnCancelar.Enabled := True;

      CcB012NRSEQFOR.AsInteger  := gNRSEQFOR;
      CcB012TPFORMUL.AsString   := gTPFORMUL;
      CcB012CDEMPRES.AsInteger  := EdtCdEmpres.BrAsInteger;
end;

procedure TCad0011.CcB012AfterPost(DataSet: TDataSet);
begin
      inherited;
      CcB012.BrApplyUpdates;
      BtnSalvar.Enabled := False;
      BtnCancelar.Enabled := False;
end;

procedure TCad0011.CcB012BeforePost(DataSet: TDataSet);
begin
      inherited;
      if (CcB012NRPLANO.AsInteger = 0) then
      begin
            raise Exception.Create('Informe o plano de contas!');
      end;

      if (CcB012CDHISTOR.AsInteger = 0) then
      begin
            raise Exception.Create('Informe o histórico!');
      end;

      if (CcB012NRCONCRE.AsInteger = 0) and (CcB012NRCONDEB.AsInteger = 0) then
      begin
            raise Exception.Create('Informe ao menos uma conta (Crédito ou Débito)!');
      end;
end;

procedure TCad0011.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TCad0011.FormCreate(Sender: TObject);
begin
      inherited;
      TabFiltro.TabVisible := False;
      TabCadastro.TabVisible := False;
      PageFundo.ActivePageIndex := 0;
end;

procedure TCad0011.TmrIniciaTimer(Sender: TObject);
begin
      inherited;
      EdtCdEmpres.SetFocus;
      TmrInicia.Enabled := False;
end;

end.
