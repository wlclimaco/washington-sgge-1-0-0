unit UCon0023;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  StdCtrls, Mask, DBCtrls, BrvDbRetCon, DB, DBClient, BrvClientDataSet, ComCtrls, Grids, BrvDbGrids,
  BrvDbGrid, BrvServerProgress;

type
  TCon0023 = class(TMov0000)
    BrvDBRetCon1: TBrvDBRetCon;
    PnlFiltros: TPanel;
    Label4: TLabel;
    Label1: TLabel;
    BrvDBRetCon2: TBrvDBRetCon;
    DsQ004: TDataSource;
    Label2: TLabel;
    BrvDBRetCon3: TBrvDBRetCon;
    BrvDBRetCon4: TBrvDBRetCon;
    GroupBox1: TGroupBox;
    Label3: TLabel;
    BrvDBRetCon6: TBrvDBRetCon;
    BrvDBRetCon5: TBrvDBRetCon;
    Label5: TLabel;
    BrvDBRetCon7: TBrvDBRetCon;
    BrvDBRetCon8: TBrvDBRetCon;
    Label6: TLabel;
    BrvDBRetCon9: TBrvDBRetCon;
    BrvDBRetCon10: TBrvDBRetCon;
    PgcDetalRNC: TPageControl;
    TbsNaoCon: TTabSheet;
    TbsProEnv: TTabSheet;
    TabParInt: TTabSheet;
    TbsAnexos: TTabSheet;
    Panel1: TPanel;
    CpQ004: TBrvClientDataSet;
    Label7: TLabel;
    BrvDBRetCon11: TBrvDBRetCon;
    BrvDBRetCon12: TBrvDBRetCon;
    BrvDBRetCon13: TBrvDBRetCon;
    DbgProdutos: TBrvDbGrid;
    DbgUsuarios: TBrvDbGrid;
    DbgAnexos: TBrvDbGrid;
    CpQ007: TBrvClientDataSet;
    DsQ007: TDataSource;
    CpQ005: TBrvClientDataSet;
    DsQ005: TDataSource;
    CpQ006: TBrvClientDataSet;
    DsQ006: TDataSource;
    BrvListParam: TBrvListParam;
    BrvServerProgress: TBrvServerProgress;
    DBMemo1: TDBMemo;
    TbsDadCom: TTabSheet;
    Panel2: TPanel;
    Label8: TLabel;
    BrvDBRetCon14: TBrvDBRetCon;
    BrvDBRetCon15: TBrvDBRetCon;
    Label9: TLabel;
    BrvDBRetCon16: TBrvDBRetCon;
    BrvDBRetCon17: TBrvDBRetCon;
    Label10: TLabel;
    BrvDBRetCon18: TBrvDBRetCon;
    Label11: TLabel;
    BrvDBRetCon19: TBrvDBRetCon;
  private
    { Private declarations }
  public
    procedure CarregarDadosRNC(pNrRNC: AnsiString);
  end;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TCon0023.CarregarDadosRNC(pNrRNC: AnsiString);
begin
      try
          FormStyle   := fsNormal;
          Visible     := False;
          Position    := poMainFormCenter;
          BorderIcons := [biSystemMenu];

          BrvListParam.Clear;
          BrvListParam.Add('NrRnc', '', '', pNrRNC);
          BrvServerProgress.Start(Self.Caption, 'Reunindo informações', 100, 10);
          CpQ004.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(245,
                                                                 BrvListParam.AsBrParam, Self.Name);
      finally
          BrvServerProgress.Stop;
      end;

      if (CpQ004.RecordCount > 0) then
      begin
            PgcDetalRNC.TabIndex := 0;

            CpQ005.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(247,
                                                                 BrvListParam.AsBrParam, Self.Name);

            CpQ006.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(248,
                                                                 BrvListParam.AsBrParam, Self.Name);

            CpQ007.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(249,
                                                                 BrvListParam.AsBrParam, Self.Name);

            Caption     :=  'Con0023 - Detalhes da RNC : ' +  pNrRNC;
            ShowModal;
      end else
      begin
            MessageDlg('Nenhum registro encontrado!!!', mtInformation, [mbok], 0);
      end;
end;

end.
