unit UCon0005;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, ComCtrls, StdCtrls, Grids,
  DBGrids, DBCtrls, Mask, DB, BrvDbGrids, BrvDbGrid, BrvEdit, BrvDbEdit, DBClient, BrvClientDataSet,
  BrvListParam, ImgList, Menus, BrvServerProgress;

type
  TCon0005 = class(TMov0000)
    PgcPrincipal: TPageControl;
    TbsCTRC: TTabSheet;
    TbsNotas: TTabSheet;
    TbsMovCtrc: TTabSheet;
    PnlLocalizar: TPanel;
    Label2: TLabel;
    Label48: TLabel;
    Label49: TLabel;
    Label50: TLabel;
    Label51: TLabel;
    Label43: TLabel;
    Label28: TLabel;
    Label72: TLabel;
    Label73: TLabel;
    Label46: TLabel;
    Label75: TLabel;
    Label76: TLabel;
    BrvDbEdit1: TBrvDbEdit;
    DsT013: TDataSource;
    CPT013: TBrvClientDataSet;
    BrvDbEdit2: TBrvDbEdit;
    BrvDbEdit3: TBrvDbEdit;
    EdtSnFrete: TBrvDbEdit;
    EdtTpTransp: TBrvDbEdit;
    EdtStCtrc: TBrvDbEdit;
    BrvDbEdit7: TBrvDbEdit;
    BrvDbEdit8: TBrvDbEdit;
    BrvDbEdit9: TBrvDbEdit;
    BrvDbEdit10: TBrvDbEdit;
    BrvDbEdit11: TBrvDbEdit;
    BrvDbEdit12: TBrvDbEdit;
    BrvDbEdit13: TBrvDbEdit;
    BrvDbEdit14: TBrvDbEdit;
    BrvDbEdit15: TBrvDbEdit;
    PgcDeReCo: TPageControl;
    TbsRemete: TTabSheet;
    Label3: TLabel;
    Label5: TLabel;
    Label7: TLabel;
    Label6: TLabel;
    Label8: TLabel;
    Label10: TLabel;
    Label12: TLabel;
    Label85: TLabel;
    BrvDbEdit17: TBrvDbEdit;
    BrvDbEdit18: TBrvDbEdit;
    BrvDbEdit20: TBrvDbEdit;
    BrvDbEdit21: TBrvDbEdit;
    BrvDbEdit22: TBrvDbEdit;
    BrvDbEdit23: TBrvDbEdit;
    BrvDbEdit19: TBrvDbEdit;
    BrvDbEdit24: TBrvDbEdit;
    BrvDbEdit25: TBrvDbEdit;
    BrvDbEdit26: TBrvDbEdit;
    TbsDestin: TTabSheet;
    Label9: TLabel;
    Label13: TLabel;
    Label16: TLabel;
    Label17: TLabel;
    Label19: TLabel;
    Label22: TLabel;
    Label24: TLabel;
    Label44: TLabel;
    DBEdit1: TBrvDbEdit;
    DBEdit2: TBrvDbEdit;
    DBEdit3: TBrvDbEdit;
    DBEdit4: TBrvDbEdit;
    DBEdit5: TBrvDbEdit;
    DBEdit6: TBrvDbEdit;
    DBEdit7: TBrvDbEdit;
    DBEdit8: TBrvDbEdit;
    DBEdit9: TBrvDbEdit;
    DBEdit10: TBrvDbEdit;
    TbsConsig: TTabSheet;
    Label71: TLabel;
    TabRedesp: TTabSheet;
    Label47: TLabel;
    TbsComFre: TTabSheet;
    Label32: TLabel;
    Label33: TLabel;
    Label34: TLabel;
    Label35: TLabel;
    Label37: TLabel;
    Label38: TLabel;
    Label40: TLabel;
    Label42: TLabel;
    Label55: TLabel;
    Label14: TLabel;
    Label56: TLabel;
    TabEntreg: TTabSheet;
    Label54: TLabel;
    Label57: TLabel;
    Label58: TLabel;
    Label59: TLabel;
    Label60: TLabel;
    Label77: TLabel;
    Label78: TLabel;
    Label79: TLabel;
    TabSheet1: TTabSheet;
    Label61: TLabel;
    Label62: TLabel;
    Label63: TLabel;
    Label64: TLabel;
    Label65: TLabel;
    Label66: TLabel;
    Label67: TLabel;
    Label68: TLabel;
    Label69: TLabel;
    Label70: TLabel;
    TbsEDI: TTabSheet;
    Label15: TLabel;
    Label21: TLabel;
    Label25: TLabel;
    Label26: TLabel;
    TbsOutros: TTabSheet;
    Label31: TLabel;
    Label39: TLabel;
    Label41: TLabel;
    Label74: TLabel;
    Label1: TLabel;
    Label11: TLabel;
    Label18: TLabel;
    Label20: TLabel;
    Label23: TLabel;
    Label27: TLabel;
    Label29: TLabel;
    Label86: TLabel;
    DBEdit11: TBrvDbEdit;
    DBEdit12: TBrvDbEdit;
    DBEdit13: TBrvDbEdit;
    DBEdit14: TBrvDbEdit;
    DBEdit15: TBrvDbEdit;
    DBEdit16: TBrvDbEdit;
    DBEdit17: TBrvDbEdit;
    DBEdit18: TBrvDbEdit;
    DBEdit19: TBrvDbEdit;
    DBEdit20: TBrvDbEdit;
    DBEdit21: TBrvDbEdit;
    Label87: TLabel;
    Label52: TLabel;
    DBEdit22: TBrvDbEdit;
    DBEdit23: TBrvDbEdit;
    Label53: TLabel;
    DBEdit24: TBrvDbEdit;
    DBEdit25: TBrvDbEdit;
    DBEdit26: TBrvDbEdit;
    DBEdit27: TBrvDbEdit;
    DBEdit28: TBrvDbEdit;
    DBEdit29: TBrvDbEdit;
    DBEdit30: TBrvDbEdit;
    DBEdit31: TBrvDbEdit;
    DBEdit32: TBrvDbEdit;
    DBEdit33: TBrvDbEdit;
    DBEdit34: TBrvDbEdit;
    DBEdit35: TBrvDbEdit;
    DBEdit36: TBrvDbEdit;
    DBEdit37: TBrvDbEdit;
    DBEdit38: TBrvDbEdit;
    DBEdit40: TBrvDbEdit;
    DBEdit41: TBrvDbEdit;
    DBEdit42: TBrvDbEdit;
    DBEdit43: TBrvDbEdit;
    DBEdit44: TBrvDbEdit;
    DBEdit45: TBrvDbEdit;
    DBEdit46: TBrvDbEdit;
    DBEdit47: TBrvDbEdit;
    DBEdit48: TBrvDbEdit;
    DBEdit49: TBrvDbEdit;
    DBEdit50: TBrvDbEdit;
    DBEdit51: TBrvDbEdit;
    DBEdit52: TBrvDbEdit;
    DBEdit53: TBrvDbEdit;
    DBEdit54: TBrvDbEdit;
    DBEdit55: TBrvDbEdit;
    DBEdit56: TBrvDbEdit;
    DBEdit57: TBrvDbEdit;
    DBEdit59: TBrvDbEdit;
    DBEdit60: TBrvDbEdit;
    DBEdit61: TBrvDbEdit;
    DBEdit62: TBrvDbEdit;
    DBEdit63: TBrvDbEdit;
    DBEdit64: TBrvDbEdit;
    Label4: TLabel;
    DBEdit67: TBrvDbEdit;
    DBEdit66: TBrvDbEdit;
    DBMemo1: TDBMemo;
    Label45: TLabel;
    DBEdit39: TBrvDbEdit;
    Label30: TLabel;
    DBEdit58: TBrvDbEdit;
    CBXSNIMBICM: TDBCheckBox;
    CBXSNSUBSTI: TDBCheckBox;
    DBXSNDEICFA: TDBCheckBox;
    DBEdit65: TBrvDbEdit;
    MEMOBCTRC: TDBMemo;
    DBGrid1: TBrvDbGrid;
    DBGrdRota: TBrvDbGrid;
    Label36: TLabel;
    CPT014: TBrvClientDataSet;
    DsT014: TDataSource;
    DsT015: TDataSource;
    DBEdit68: TDBEdit;
    DBEdit69: TDBEdit;
    DBEdit70: TDBEdit;
    Label80: TLabel;
    BrvDbEdit4: TBrvDbEdit;
    TabSheet2: TTabSheet;
    CPT015: TBrvClientDataSet;
    BrvDbGrid1: TBrvDbGrid;
    DsT016: TDataSource;
    CPT016: TBrvClientDataSet;
    BrvServerProgress: TBrvServerProgress;
    procedure FormCreate(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
    procedure CarregarDadosConhecimento(lCdCTRC, lCdEmpres, lDsModeNF, lNrSeriNF : AnsiString);
  end;

implementation

uses UDmSrvApl, BrvFuncoesXE;

{$R *.dfm}


procedure TCon0005.FormCreate(Sender: TObject);
begin
      inherited;
      PgcPrincipal.ActivePageIndex  :=  0;
      PgcDeReCo.ActivePageIndex     :=  0;
end;

procedure TCon0005.CarregarDadosConhecimento(lCdCTRC, lCdEmpres, lDsModeNF, lNrSeriNF : AnsiString);
var lDsWhere  : String;
begin
      try
          Caption := 'Con0005 - Detalhes do Conhecimento : ' +  lCdCTRC;

          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);

          CpT013.Close;
          CpT013.BrParams.Clear;
          lDsWhere  :=  '';
          lDsWhere  :=  lDsWhere + ' and T002.CdEmpres = ' + lCdEmpres;
          lDsWhere  :=  lDsWhere + ' and T002.DsModeNF = ' + QuotedStr(lDsModeNF);
          lDsWhere  :=  lDsWhere + ' and T002.NrSeriNF = ' + QuotedStr(lNrSeriNF);
          lDsWhere  :=  lDsWhere + ' and T002.CdCTRC   = ' + lCdCTRC;

          CpT013.Data :=  DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(50,
                                                   '<%DsComWhe%>;' + lDsWhere, Name);

          CpT014.Close;
          CpT014.BrParams.Clear;
          lDsWhere  :=  '';
          lDsWhere  :=  lDsWhere + ' and T008.CdEmpres = ' + lCdEmpres;
          lDsWhere  :=  lDsWhere + ' and T008.DsModeNF = ' + QuotedStr(lDsModeNF);
          lDsWhere  :=  lDsWhere + ' and T008.NrSeriNF = ' + QuotedStr(lNrSeriNF);
          lDsWhere  :=  lDsWhere + ' and T008.CdCTRC   = ' + lCdCTRC;

          CpT014.Data :=  DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(51,
                                                   '<%DsComWhe%>;' + lDsWhere, Name);

          CpT015.Close;
          CpT015.BrParams.Clear;
          lDsWhere  :=  '';
          lDsWhere  :=  lDsWhere + ' and T015.CdEmpres = ' + lCdEmpres;
          lDsWhere  :=  lDsWhere + ' and T015.DsModeNF = ' + QuotedStr(lDsModeNF);
          lDsWhere  :=  lDsWhere + ' and T015.NrSeriNF = ' + QuotedStr(lNrSeriNF);
          lDsWhere  :=  lDsWhere + ' and T015.CdCTRC   = ' + lCdCTRC;

          CpT015.Data :=  DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(52,
                                                   '<%DsComWhe%>;' + lDsWhere, Name);

          CpT016.Close;
          CpT016.BrParams.Clear;
          lDsWhere  :=  '';
          lDsWhere  :=  lDsWhere + ' and T021.CdEmpres = ' + lCdEmpres;
          lDsWhere  :=  lDsWhere + ' and T021.DsModeNF = ' + QuotedStr(lDsModeNF);
          lDsWhere  :=  lDsWhere + ' and T021.NrSeriNF = ' + QuotedStr(lNrSeriNF);
          lDsWhere  :=  lDsWhere + ' and T021.CdCTRC   = ' + lCdCTRC;

          CpT016.Data :=  DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(280, '<%DsComWhe%>;' +
                                                                                    lDsWhere, Name);

          { Função do sistema para Formatação }
          FormataCamposDecimais(CPT013);
          FormataCamposDecimais(CPT014);
          FormataCamposDecimais(CPT015);
      finally
          BrvServerProgress.Stop;
      end;

      BorderIcons := [biSystemMenu];
      ShowModal;

      CpT013.EmptyDataSet;
      CpT014.EmptyDataSet;
      CpT015.EmptyDataSet;
end;

initialization
      RegisterClass(TCon0005);

finalization
      UnRegisterClass(TCon0005);


end.
