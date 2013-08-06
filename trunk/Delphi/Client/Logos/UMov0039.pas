unit UMov0039;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, Grids, BrvDbGrids, BrvDbGrid, StdCtrls, ComCtrls, Buttons, BrvBitBtn,
  BrvRetCon, BrvEditNum, BrvComboBox, BrvCustomEdit, BrvEdit, Mask, BrvCustomMaskEdit, BrvEditDate,
  ACBrNFe, BrvListParam, ImgList, Menus, ExtCtrls, BrvSpeedButton, BrvDbNavCop, DB, DBClient,
  BrvClientDataSet,pcnConversao, BrvXml, CheckLst, BrvCheckListBox, BrvServerProgress;
type
  TMov0039 = class(TMov0000)
    LblNmClient: TLabel;
    LblDsEmpres: TLabel;
    PgcFundo: TPageControl;
    TbsFiltro: TTabSheet;
    Panel4: TPanel;
    Panel1: TPanel;
    TbsConsulta: TTabSheet;
    Panel3: TPanel;
    Splitter2: TSplitter;
    LblCdHistor: TLabel;
    Panel2: TPanel;
    LblQtReg: TLabel;
    CancelNFe: TBrvBitBtn;
    BtnVoltar: TBrvBitBtn;
    Panel5: TPanel;
    StgFiltros: TStringGrid;
    EdtCdHistor: TBrvEditNum;
    EdtDsHistor: TBrvRetCon;
    PgcNFeCanc: TPageControl;
    TabSheet1: TTabSheet;
    DbgFiltro: TBrvDbGrid;
    TbsRetorn: TTabSheet;
    MemDsXmlNfe: TMemo;
    MmRetNFe: TMemo;
    BtnPesquisa: TBrvBitBtn;
    BrvListParam: TBrvListParam;
    BrvServerProgress: TBrvServerProgress;
    CcP002: TClientDataSet;
    DtsP002: TDataSource;
    Panel7: TPanel;
    LblCdEmpres: TLabel;
    CblCdEmpres: TBrvCheckListBox;
    Panel6: TPanel;
    LblCdTitula: TLabel;
    LblDtInicio: TLabel;
    LblDtFinal: TLabel;
    LblDsModelo: TLabel;
    LblNrSerie: TLabel;
    LblNrNota: TLabel;
    LblNrPedido: TLabel;
    Label9: TLabel;
    EdtCdTitula: TBrvEditNum;
    LblRsTitula: TBrvRetCon;
    EdtDtInicio: TBrvEditDate;
    EdtDtFinal: TBrvEditDate;
    EdtDsModelo: TBrvEdit;
    EdtNrSerie: TBrvEdit;
    EdtNrNota: TBrvEdit;
    EdtNrPedido: TBrvEdit;
    CmbTipAmb: TBrvComboBox;
    procedure BtnPesquisaClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure CancelNFeClick(Sender: TObject);
    procedure BtnVoltarClick(Sender: TObject);

  private
    { Private declarations }
  public
    { Public declarations }
    procedure ExecutaConsulta(pCtP002 : TClientDataSet);
  end;

var
  Mov0039: TMov0039;

implementation

uses UDmSrvApl,UClaSrv, UDmFis, BrvDominiosXE;

{$R *.dfm}

procedure TMov0039.BtnVoltarClick(Sender: TObject);
begin
      PgcFundo.ActivePage := TbsFiltro;
end;

procedure TMov0039.CancelNFeClick(Sender: TObject);
begin
    //  MessageDlg('Favor procurar setor FISCAL/TI!!!', mtInformation, [mbok], 0);
      if Trim(EdtDsHistor.Text) <> '' then
      begin
            MmRetNFe.Lines.Text :=  UDmFis.DmFis.CancelarNotaFiscal(CcP002,0,EdtDsHistor.Text,'S');
      end else
      begin
            MessageDlg('Nenhum ' + LblCdHistor.Caption + ' informado!!!', mtInformation, [mbok], 0);
      end;
end;

procedure TMov0039.ExecutaConsulta(pCtP002 : TClientDataSet);
var lNrIdx    : Integer;
    lDsEmpres : String;
begin
      if (CblCdEmpres.BrCheckedCount = 0) then
      begin
            CblCdEmpres.SetFocus;
            raise Exception.Create('Selecione a(s) ' + LblCdEmpres.Caption + '!');
      end else
      begin
            lDsEmpres := CblCdEmpres.BrCheckedList;
            BrvListParam.Add('CdEmpres', LblCdEmpres.Caption, lDsEmpres, lDsEmpres);
      end;

      if EdtCdTitula.BrAsInteger <> 0  then
      begin
            BrvListParam.Add('CdClient', LblCdTitula.Caption, EdtCdTitula.Text,
                                                           'and NF.CdClient = ' + EdtCdTitula.Text);
      end else
      begin
            BrvListParam.Add('CdClient', '', '', '');
      end;

      if  not(EdtDtInicio.BrDataVazia) then
      begin
            BrvListParam.Add('DtEntrad', LblDtInicio.Caption, EdtDtInicio.BrAsDataSQL ,
                                      'and NF.DtEmiNot >= <$hh"' + EdtDtInicio.BrAsDataSQL+'"hh$>');
      end else
      begin
            BrvListParam.Add('DtEntrad', '', '', '');
      end;

      if  not(EdtDtFinal.BrDataVazia) then
      begin
            BrvListParam.Add('DtFinal', LblDtFinal.Caption, EdtDtFinal.BrAsDataSQL,
                                       'and NF.DtEmiNot <= <$hh"' + EdtDtFinal.BrAsDataSQL+'"hh$>');
      end else
      begin
            BrvListParam.Add('DtFinal', '', '', '');
      end;

      if  EdtDsModelo.Text <> '' then
      begin
            BrvListParam.Add('DsModelo', LblDsModelo.Caption, EdtDsModelo.Text,
                                                      'and NF.DsModeNF = "' + EdtDsModelo.Text+'"');
      end else
      begin
            BrvListParam.Add('DsModelo', '', '', '');
      end;

      if  (EdtNrSerie.Text <> '0') and (Trim(EdtNrSerie.Text) <> '') then
      begin
            BrvListParam.Add('NrSerie', LblNrSerie.Caption, EdtNrSerie.Text,
                                                          'and NF.NrSerie = "'+EdtNrSerie.Text+'"');
      end else
      begin
            BrvListParam.Add('NrSerie', '', '', '');
      end;

      if  (EdtNrNota.Text <> '0') and (EdtNrNota.Text <> '') then
      begin
            BrvListParam.Add('NrNota', LblNrNota.Caption, EdtNrNota.Text,
                                                               'and NF.NrNota = ' + EdtNrNota.Text);
      end else
      begin
            BrvListParam.Add('NrNota', '', '', '');
      end;

      if  (EdtNrPedido.Text <> '0') and (EdtNrPedido.Text <> '') then
      begin
            BrvListParam.Add('NrPedido', LblNrPedido.Caption, EdtNrPedido.Text,
                                                            'and P.NrPedido = ' + EdtNrPedido.Text);
      end else
      begin
            BrvListParam.Add('NrPedido', '', '', '');
      end;
      try
          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
          pCtP002.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(281,
                                                             BrvListParam.AsBrParam, Self.Name);
      finally
          BrvServerProgress.Stop;
      end;
      BrvListParam.SetStgParam(StgFiltros);
end;


procedure TMov0039.FormCreate(Sender: TObject);
var lNrIdx : Integer;
begin
      inherited;
      TbsFiltro.TabVisible   := False;
      TbsConsulta.TabVisible := False;
      PgcFundo.ActivePage    := TbsFiltro;

      CarregaEmpresas(CblCdEmpres, True);
end;

procedure TMov0039.BtnPesquisaClick(Sender: TObject);
Var lCtP002 : TClientDataSet;
begin
      try
          lCtP002 := TClientDataSet.Create(Self);
          BrvListParam.Clear;
          ExecutaConsulta(lCtP002);
          if lCtP002.RecordCount > 0 then
          begin
                LblQtReg.Caption := FormatFloat('0', lCtP002.RecordCount) + ' Registro(s)';
                CcP002.Data := lCtP002.Data;
                PgcFundo.ActivePage := TbsConsulta;
          end else
          begin
                MessageDlg('Nenhum registro encontrado!!!', mtInformation, [mbok], 0);
          end;

      finally
          FreeAndNil(lCtP002);
      end;


end;

initialization
      RegisterClass(TMov0039);

finalization
      UnRegisterClass(TMov0039);
end.
