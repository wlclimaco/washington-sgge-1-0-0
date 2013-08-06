unit UCon0008;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, DB, DBClient, BrvClientDataSet,
  StdCtrls, BrvBitBtn, BrvRetCon, BrvEditNum, ComCtrls, BrvImgBot, Mask, DBCtrls, BrvDbEdit, Grids,
  BrvDbGrids, BrvDbGrid, BrvCustomEdit, BrvListParam, ImgList, Menus;

type
  TCon0008 = class(TMov0000)
    DsT016: TDataSource;
    CPT016: TBrvClientDataSet;
    PgcDados: TPageControl;
    TbsConhec: TTabSheet;
    BdgRegistros: TBrvDbGrid;
    DsT017: TDataSource;
    CPT017: TBrvClientDataSet;
    TbsEmpres: TTabSheet;
    BdgCarEmp: TBrvDbGrid;
    CPT014: TBrvClientDataSet;
    DsT014: TDataSource;
    BrvListParam: TBrvListParam;
    Label1: TLabel;
    BrvDbEdit8: TBrvDbEdit;
    Label76: TLabel;
    BrvDbEdit7: TBrvDbEdit;
    BrvDbEdit1: TBrvDbEdit;
    Label43: TLabel;
    BrvDbEdit9: TBrvDbEdit;
    BrvDbEdit10: TBrvDbEdit;
    Label2: TLabel;
    EdtDtFecham: TBrvDbEdit;
    Label3: TLabel;
    BrvDbEdit2: TBrvDbEdit;
    Label4: TLabel;
    BrvDbEdit3: TBrvDbEdit;
    Label6: TLabel;
    BrvDbEdit4: TBrvDbEdit;
    Label7: TLabel;
    BrvDbEdit5: TBrvDbEdit;
    Label8: TLabel;
    BrvDbEdit6: TBrvDbEdit;
    Label9: TLabel;
    BrvDbEdit11: TBrvDbEdit;
    BrvDbEdit14: TBrvDbEdit;
    Label12: TLabel;
    Label10: TLabel;
    Label17: TLabel;
    Label15: TLabel;
    BrvDbEdit12: TBrvDbEdit;
    BrvDbEdit20: TBrvDbEdit;
    BrvDbEdit17: TBrvDbEdit;
    Label13: TLabel;
    Label16: TLabel;
    BrvDbEdit18: TBrvDbEdit;
    BrvDbEdit16: TBrvDbEdit;
    BrvDbEdit19: TBrvDbEdit;
    Label11: TLabel;
    Label18: TLabel;
    BrvDbEdit13: TBrvDbEdit;
    BrvDbEdit21: TBrvDbEdit;
    Label14: TLabel;
    Label19: TLabel;
    BrvDbEdit15: TBrvDbEdit;
    BrvDbEdit22: TBrvDbEdit;
    PnlCabec: TPanel;
    Label5: TLabel;
    EdtCdCarga: TBrvEditNum;
    EdtDsDescar: TBrvRetCon;
    BtnLocali: TBrvBitBtn;
    PopCTRC: TPopupMenu;
    Detalhar1: TMenuItem;
    procedure BtnLocaliClick(Sender: TObject);
    procedure PgcDadosChange(Sender: TObject);
    procedure BdgRegistrosDblClick(Sender: TObject);
    procedure Detalhar1Click(Sender: TObject);
  private
    pDsCarga  : WideString;
    procedure RecuperaDados;
    procedure DetalharCTRC;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Con0008: TCon0008;

implementation

uses BrvFuncoesXE, UDmSrvApl, UDmTra;

{$R *.dfm}

//***********************************************************************************************//
procedure TCon0008.RecuperaDados();
var lCdCarga  : String;
    lCdEmpres : String;
begin
      lCdCarga  := '';

      if EdtCdCarga.BrAsInteger = 0 then
      begin
            raise Exception.Create('É obrigatório informar uma Carga');
      end;

      if EdtCdCarga.BrAsInteger > 0 then
      begin
            lCdCarga := EdtCdCarga.Text;
      end;

      lCdEmpres    := DmSrvApl.BrvDicionario.CorpCommaCodes;
      CpT016.Data  := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(58,
                                              '<%CdEmpres%>;'  + lCdEmpres + '  ' + #13 + ' '  +
                                              '<%CdCarga%>; '  + lCdCarga  + ' ', Name);
end;

//***********************************************************************************************//

procedure TCon0008.BtnLocaliClick(Sender: TObject);
begin
      if BtnLocali.Caption = 'Localizar' then
      begin
            RecuperaDados;
            if (CpT016.RecordCount >  0)  then
            begin
                  FormataCamposDecimais(CpT016);
                  BtnLocali.Caption        := 'Voltar';
                  BtnLocali.Hint           := 'Voltar';
                  BtnLocali.BrTipoBotao    := BrBtnSetaEsquerda;
                  PnlFundo.Visible         := True;
                  EdtCdCarga.Enabled       := False;
                  PgcDados.ActivePageIndex := 0;
                  PgcDadosChange(PgcDados);
            end
      end else
      begin
            BtnLocali.Caption     := 'Localizar';
            BtnLocali.Hint        := 'Localizar';
            BtnLocali.BrTipoBotao := BrBtnLocalizar;
            PnlFundo.Visible      := False;
            EdtCdCarga.Enabled    := True;
            CpT016.EmptyDataSet;
      end;

      if EdtCdCarga.Enabled then
      begin
            EdtCdCarga.SetFocus;
      end;

end;

//***********************************************************************************************//

procedure TCon0008.PgcDadosChange(Sender: TObject);
var  lCdEmpres : WideString;
     lDsComple : WideString;
     lDsCarga  : WideString;
begin
      if PgcDados.ActivePageIndex = 0 then
      begin
            lDsCarga  :=  pDsCarga;
            pDsCarga  :=  '';

            lCdEmpres := CPT016.FieldByName('CdEmpres').AsString;
            pDsCarga  := pDsCarga    + ' and MvCtrc.CdCarga  = ' + EdtCdCarga.Text;
            lDsComple := lDsComple   + ' and MvCtrc.CdEmpres = ' + lCdEmpres + ' ' ;
            lDsComple := lDsComple   + ' and Carga.CdCarga   = ' + EdtCdCarga.Text;

            if pDsCarga <> lDsCarga then
            begin
                  CpT017.Data  := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(45,
                                                          '<%DsCarga%>;'   + pDsCarga + #13 +
                                                          '<%DsComple%>; ' + lDsComple
                                                           , Name);
                  FormataCamposDecimais(CpT017);
            end;
      end;

      if PgcDados.ActivePageIndex = 1 then
      begin
            BrvListParam.Clear;
            BrvListParam.Add('CdEmpres', '', '', CPT016.FieldByName('CdEmpres').AsString);
            BrvListParam.Add('CdCarga', '', '', 'And ce.CdCarga  = ' + EdtCdCarga.Text);

            CpT014.Data  := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(226,
                                                           BrvListParam.AsBrParam, Self.Name);
      end;
end;

//***********************************************************************************************//

procedure TCon0008.BdgRegistrosDblClick(Sender: TObject);
begin
      inherited;
      DetalharCTRC;
end;

procedure TCon0008.Detalhar1Click(Sender: TObject);
begin
      inherited;
      DetalharCTRC();
end;

procedure TCon0008.DetalharCTRC();
begin
      inherited;
      DmTra.VisualizarConhecimento(CPT017);
end;

//***********************************************************************************************//

initialization
      RegisterClass(TCon0008);

finalization
      UnRegisterClass(TCon0008);

end.
