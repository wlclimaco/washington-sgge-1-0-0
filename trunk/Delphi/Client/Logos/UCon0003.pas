unit UCon0003;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCad0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, Grids, BrvDbGrids, BrvDbGrid,
  BrvDbRetCon, StdCtrls, ComCtrls, DBCtrls, Mask, BrvDbEdit, DB, DBClient, BrvClientDataSet,
  BrvListParam, ImgList, Menus;

type
  TCon0003 = class(TCad0000)
    DsT010: TDataSource;
    QcT010: TBrvClientDataSet;
    DsT011: TDataSource;
    QcT011: TBrvClientDataSet;
    DsT012: TDataSource;
    QcT012: TBrvClientDataSet;
    pnlBody: TPanel;
    pnlAtend: TPanel;
    lblDescricao: TLabel;
    lblnicio: TLabel;
    Label1: TLabel;
    Label2: TLabel;
    lblTxt: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    EdtDsAtendi: TBrvDbEdit;
    EdtDtAbertu: TBrvDbEdit;
    EdtDtFecham: TBrvDbEdit;
    EdtCdEmpres: TBrvDbEdit;
    EdtDsEmpr: TBrvDBRetCon;
    EdtCdTipAte: TBrvDbEdit;
    EdtDsTipAte: TBrvDBRetCon;
    EdtCdCarga: TBrvDbEdit;
    EdtDsDescar: TBrvDBRetCon;
    PnlOcorrencias: TPanel;
    BdgConhec: TBrvDbGrid;
    EdtNotas: TBrvDbEdit;
    BdgOcorrencias: TBrvDbGrid;
    DBMemo1: TDBMemo;
  private
    { Private declarations }
  public
    { Public declarations }
    procedure CarregarDadosAtendimento(pNrAtendi : AnsiString);
  end;

var
  Con0003: TCon0003;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TCon0003.CarregarDadosAtendimento(pNrAtendi : AnsiString);
var lDsWhere  : String;
begin
      QcT010.Close;
      QcT010.BrParams.Clear;
      lDsWhere    :=  ' and T010.NrAtendi = ' + pNrAtendi;
      QcT010.Data :=  DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(46,
                                                                  '<%DsComWhe%>;' + lDsWhere, Name);

      // Abrindo Conhecimentos
      QcT011.Close;
      lDsWhere    := ' and T011.NrAtendi = ' + pNrAtendi;
      QcT011.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(47,
                                                                  '<%DsComWhe%>;' + lDsWhere, Name);

      // Abrindo Ocorrencias
      QcT012.Close;
      lDsWhere := ' and T012.NrAtendi = ' + pNrAtendi;
      QcT012.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(48,
                                                                  '<%DsComWhe%>;' + lDsWhere, Name);
      PnlBody.Visible :=  True;

      ShowModal;

      QcT010.EmptyDataSet;
      QcT011.EmptyDataSet;
      QcT012.EmptyDataSet;
end;


initialization
      RegisterClass(TCon0003);

finalization
      UnRegisterClass(TCon0003);

end.
