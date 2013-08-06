unit UCon0013;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls, BrvComboBox,
  BrvRetCon, BrvEditNum, Mask, BrvEditDate, BrvBitBtn, Grids, BrvDbGrids, BrvDbGrid, DB, DBClient,
  BrvExcel, mxExport, BrvListParam, ImgList, Menus, BrvCustomEdit, BrvCustomMaskEdit;

type
  TCon0013 = class(TMov0000)
    PnlCabeca: TPanel;
    Label22: TLabel;
    EdtDtIni: TBrvEditDate;
    Label3: TLabel;
    EdtDtFim: TBrvEditDate;
    Label1: TLabel;
    EdtCdArmaze: TBrvEditNum;
    EdtNmArmaze: TBrvRetCon;
    Label17: TLabel;
    CbxDsTipMov: TBrvComboBox;
    bdgRegistros: TBrvDbGrid;
    BtnLocali: TBrvBitBtn;
    CPW005: TClientDataSet;
    DsW005: TDataSource;
    PnlOperacao: TPanel;
    Label2: TLabel;
    LblQtdeNF: TLabel;
    BtnCancel: TBrvBitBtn;
    BrvBitBtn1: TBrvBitBtn;
    BrvExcelDados: TBrvExcel;
    procedure BtnLocaliClick(Sender: TObject);
    procedure BtnCancelClick(Sender: TObject);
    procedure BrvBitBtn1Click(Sender: TObject);
  private
    procedure ValidaEntrada;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Con0013: TCon0013;

implementation

uses UDmSrvApl, BrvFuncoesXE;

{$R *.dfm}

procedure TCon0013.ValidaEntrada;
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

procedure TCon0013.BrvBitBtn1Click(Sender: TObject);
var lNrIndice : Integer;
    lNmArquiv : String;
    lCampo    : String;
    lColunas  : TStrings;
begin
      inherited;
      lColunas  :=  TStringList.Create;

      for lNrIndice := 0 to CPW005.FieldCount - 1 do
      begin
            lCampo  :=  CPW005.Fields[lNrIndice].FieldName;
            lColunas.Add(lCampo);
      end;

      BrvExcelDados.BrDataSet :=  CPW005;
      BrvExcelDados.Execute(lColunas, DlgSelecionarPasta('Salvar Arquivo Excel'), nil);

      FreeAndNil(lColunas);
end;

procedure TCon0013.BtnCancelClick(Sender: TObject);
begin
      inherited;
      CPW005.Close;
      PnlCabeca.Enabled := True;
      PnlOperacao.Visible := False;
end;

procedure TCon0013.BtnLocaliClick(Sender: TObject);
begin
      inherited;
      ValidaEntrada;

      CPW005.Close;
      CPW005.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(134,
                                  '<%CdArmaze%>;' + EdtCdArmaze.Text + Chr(13) +
                                  '<%CdTipAti%>;' +
                                  CbxDsTipMov.Values[CbxDsTipMov.ItemIndex]+ Chr(13) +
                                  '<%DtOpeIni%>;' + EdtDtIni.BrAsDataSQL + ' 00:00:00'  + Chr(13) +
                                  '<%DtOpeFim%>;' + EdtDtFim.BrAsDataSQL + ' 23:59:59', Self.Name);
      CPW005.Open;

      bdgRegistros.SetFocus;

      LblQtdeNF.Caption := FormatFloat('0', CPW005.RecordCount);

      PnlCabeca.Enabled   := False;
      PnlOperacao.Visible := True;
end;

initialization
      RegisterClass(TCon0013);

finalization
      UnRegisterClass(TCon0013);


end.
