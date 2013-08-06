unit UMov0040;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, BrvDbGrids, BrvDbGrid,
  StdCtrls, Grids, DBGrids, CheckLst, BrvCheckListBox, BrvEdit, Mask, BrvEditDate, BrvBitBtn,
  BrvRetCon, BrvEditNum, ComCtrls, DB, DBClient, BrvProgressBar, BrvExcel, BrvMesAno,
  BrvListParam, BrvServerProgress, ImgList, Menus, BrvCustomMaskEdit, BrvCustomEdit, BrvComboBox;

type
  TMov0041 = class(TMov0000)
    PgcFundo: TPageControl;
    TbsFiltro: TTabSheet;
    Panel4: TPanel;
    Label12: TLabel;
    Panel1: TPanel;
    BtnPesquisa: TBrvBitBtn;
    CblCdEmpres: TBrvCheckListBox;
    EdtCdFornec: TBrvEditNum;
    EdtRsFornec: TBrvRetCon;
    TbsConsulta: TTabSheet;
    Panel3: TPanel;
    Splitter2: TSplitter;
    Panel2: TPanel;
    LblQtReg: TLabel;
    BtnExcel: TBrvBitBtn;
    BtnVoltar: TBrvBitBtn;
    Panel5: TPanel;
    StgFiltros: TStringGrid;
    BrvBitBtn1: TBrvBitBtn;
    Label2: TLabel;
    Label1: TLabel;
    EdtDtInicio: TBrvEditDate;
    Label3: TLabel;
    EdtDtFinal: TBrvEditDate;
    Label5: TLabel;
    Label6: TLabel;
    EdtDsModelo: TBrvEdit;
    EdtNrSerie: TBrvEdit;
    EdtNrNota: TBrvEdit;
    CmbTipAmb: TBrvComboBox;
    Label7: TLabel;
    Label9: TLabel;
    Label4: TLabel;
    EdtCdHistor: TBrvEditNum;
    EdtDsHistor: TBrvRetCon;
    CancelNFe: TBrvBitBtn;
    BrvBitBtn3: TBrvBitBtn;
    BrvListParam: TBrvListParam;
    BrvServerProgress: TBrvServerProgress;
    DtsP002: TDataSource;
    CcP002: TClientDataSet;
    PgcNFeCanc: TPageControl;
    TabSheet1: TTabSheet;
    TbsRetorn: TTabSheet;
    MemDsXmlNfe: TMemo;
    MmRetNFe: TMemo;
    DbgFiltro: TBrvDbGrid;
    procedure BrvBitBtn1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure CancelNFeClick(Sender: TObject);
    procedure BrvBitBtn3Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
    procedure ExecutaConsulta(pCtP002 : TClientDataSet);
  end;

var
  Mov0041: TMov0041;

implementation

{$R *.dfm}

uses UDmSrvApl, BrvFuncoesXE,UDmFis;

procedure TMov0041.BrvBitBtn3Click(Sender: TObject);
begin
      PgcFundo.ActivePage := TbsFiltro;

end;

procedure TMov0041.CancelNFeClick(Sender: TObject);
begin
     // MessageDlg('Favor procurar setor FISCAL/TI!!!', mtInformation, [mbok], 0);
      if Trim(EdtDsHistor.text) <> '' then
      begin
            MmRetNFe.Lines.Text :=  UDmFis.DmFis.CancelarNotaFiscal(CcP002,0,EdtDsHistor.Text,'E');
      end else
      begin
            MessageDlg('Nenhum historico informado!!!', mtInformation, [mbok], 0);
      end;
end;

procedure TMov0041.ExecutaConsulta(pCtP002 : TClientDataSet);
var lNrIdx    : Integer;
    lDsEmpres : String;
begin
      if (CblCdEmpres.BrCheckedCount = 0) then
      begin
            Raise Exception.Create('Nenhuma empresa foi selecionada');
      end else
      begin
            for lNrIdx := 0 to CblCdEmpres.Items.Count -1 do
            begin
                  if (CblCdEmpres.Checked[lNrIdx]) then
                  begin
                        lDsEmpres := lDsEmpres + CblCdEmpres. Values[lNrIdx] + ',';
                  end;
            end;

            lDsEmpres := copy(lDsEmpres, 1, length(lDsEmpres)-1);
            BrvListParam.Add('CdEmpres', 'Empresa(s)', lDsEmpres, lDsEmpres);
      end;

      if (StrToIntDef(EdtCdFornec.Text, 0) > 0)  then
      begin
            BrvListParam.Add('CdFornec', 'Fornecedor(s)', EdtCdFornec.Text + ' - ' +
                                           EdtCdFornec.Text,'and N.CdFornec = ' + EdtCdFornec.Text);
      end else
      begin
            BrvListParam.Add('CdFornec', '', '', '');
      end;

      if  not(EdtDtInicio.BrDataVazia) then
      begin
            BrvListParam.Add('DtEntrad', 'Data Inicial',EdtDtInicio.Text,
                                      'and N.DtEntrad >= <$hh"' + EdtDtInicio.BrAsDataSQL+'"hh$>');
      end else
      begin
            BrvListParam.Add('DtEntrad', '', '', '');
      end;

      if  not(EdtDtFinal.BrDataVazia) then
      begin
            BrvListParam.Add('DtFinal', 'Data Final',EdtDtFinal.Text ,
                                       'and N.DtEntrad <= <$hh"' + EdtDtFinal.BrAsDataSQL+'"hh$>');
      end else
      begin
            BrvListParam.Add('DtFinal', '', '', '');
      end;

      if  EdtDsModelo.Text <> '' then
      begin
            BrvListParam.Add('DsModelo', 'Modelo', EdtDsModelo.Text, 'and N.DsModeNF = "'
                                                                            + EdtDsModelo.Text+'"');
      end else
      begin
            BrvListParam.Add('DsModelo', '', '', '');
      end;

      if  (EdtNrSerie.Text <> '0') and (Trim(EdtNrSerie.Text) <> '') then
      begin
            BrvListParam.Add('NrSerie', 'Serie', EdtNrSerie.Text,  'and N.NrSerie = "'
                                                                              +EdtNrSerie.Text+'"');
      end else
      begin
            BrvListParam.Add('NrSerie', '', '', '');
      end;

      if  (EdtNrNota.Text <> '0') and (EdtNrNota.Text <> '') then
      begin
            BrvListParam.Add('NrNota', 'Nota(s)', EdtNrNota.Text,  'and N.NrNota = '
                                                                                  + EdtNrNota.Text);
      end else
      begin
            BrvListParam.Add('NrNota', '', '', '');
      end;
      try
          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
          pCtP002.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(284,
                                                             BrvListParam.AsBrParam, Self.Name);
      finally
          BrvServerProgress.Stop;
      end;

      BrvListParam.SetStgParam(StgFiltros);
end;


procedure TMov0041.FormCreate(Sender: TObject);
var lNrIdx : Integer;
begin
      inherited;
      TbsFiltro.TabVisible   := False;
      TbsConsulta.TabVisible := False;
      PgcFundo.ActivePage    := TbsFiltro;

      CblCdEmpres.Items.Clear;
      CblCdEmpres.Values.Clear;

      for lnridx := 0 to DmSrvApl.BrvDicionario.CorpCodes.Count-1 do
      begin
            CblCdEmpres.Items.Add(FormatFloat('000', StrToInt(
                                  DmSrvApl.BrvDicionario.CorpCodes.Strings[lnridx])) + ' ' +
                                  DmSrvApl.BrvDicionario.CorpNames.Strings[lnridx]);
            CblCdEmpres.Checked[lnridx] := True;
      end;

      CblCdEmpres.Values.Text := DmSrvApl.BrvDicionario.CorpCodes.Text;

end;

procedure TMov0041.BrvBitBtn1Click(Sender: TObject);
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
      RegisterClass(TMov0041);

finalization
      UnRegisterClass(TMov0041);
end.
