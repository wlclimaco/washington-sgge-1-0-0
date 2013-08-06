unit UMov0017;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, Grids, BrvDbGrids, BrvDbGrid,
  StdCtrls, BrvBitBtn, BrvRetCon, BrvEditNum, Menus, DB, DBClient, BrvAlertProgress, BrvCustomEdit,
  BrvListParam, ImgList;

type
  TMov0017 = class(TMov0000)
    PnlEmpres: TPanel;
    LblEmpres: TLabel;
    EdtCdEmpres: TBrvEditNum;
    LblDsEmpres: TBrvRetCon;
    BtnPesqui: TBrvBitBtn;
    PnlGrade: TPanel;
    DbgCargas: TBrvDbGrid;
    PnlRodape: TPanel;
    BtnGravar: TBrvBitBtn;
    BrvBitBtn4: TBrvBitBtn;
    DsCarga: TDataSource;
    CdsCarga: TClientDataSet;
    MenCtrc: TPopupMenu;
    Conhecimentos1: TMenuItem;
    procedure BtnPesquiClick(Sender: TObject);
    procedure BrvBitBtn4Click(Sender: TObject);
    procedure BtnGravarClick(Sender: TObject);
    procedure Conhecimentos1Click(Sender: TObject);
    procedure CdsCargaBeforePost(DataSet: TDataSet);
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure CdsCargaBeforeInsert(DataSet: TDataSet);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

implementation

uses UDmSrvApl, UClaSrv, UCon0004;

{$R *.dfm}

procedure TMov0017.BrvBitBtn4Click(Sender: TObject);
begin
      inherited;

      PnlGrade.Visible := False;
      PnlEmpres.Enabled := True;
end;

procedure TMov0017.BtnGravarClick(Sender: TObject);
var lConexao  : TSDmTraClient;
    lDsMenSer : String;
begin
      lConexao := TSDmTraClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          CdsCarga.DisableControls;
          CdsCarga.First;

          CdsCarga.Filtered := True;
          CdsCarga.Filter   := 'SnAltera = ' + QuotedStr('N') + ' or CdVeicul = 0 or CdMotori = 0';

          CdsCarga.First;
          while not CdsCarga.Eof do
          begin
                CdsCarga.Delete;
          end;

          CdsCarga.Filtered := False;
          CdsCarga.First;

          while not CdsCarga.Eof do
          begin
                lDsMenSer := lConexao.ValidarCarga(DmSrvApl.BrvDicionario.Credencial,
                                                   '0',
                                                   CdsCarga.FieldByName('CdVeicul').AsString,
                                                   CdsCarga.FieldByName('NrPeso').AsFloat,
                                                   '');
                CdsCarga.Next;
          end;

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsMenSer);

          lDsMenSer := lConexao.GravarDadosCarga(
                                            DmSrvApl.BrvDicionario.Credencial, CdsCarga.Data,
                                            'CdVeicul;CdMotori', Name);

          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsMenSer);

          ShowMessage('Cargas alteradas com sucesso!');
      finally
          BtnPesquiClick(BtnPesqui);
          CdsCarga.Filtered := False;
          CdsCarga.EnableControls;
          FreeAndNil(lConexao);
      end;
end;

procedure TMov0017.BtnPesquiClick(Sender: TObject);
var lDsParams : AnsiString;
begin
      inherited;
      if EdtCdEmpres.BrAsInteger = 0 then
      begin
            raise Exception.Create('Informe a empresa');
      end else
      begin
            lDsParams := '<%CdEmpres%>;' + EdtCdEmpres.Text;
            CdsCarga.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(153, lDsParams, Name);

            PnlGrade.Visible  := True;
            PnlEmpres.Enabled := False;
      end;
end;

procedure TMov0017.CdsCargaBeforeInsert(DataSet: TDataSet);
begin
      inherited;
      Abort;
end;

procedure TMov0017.CdsCargaBeforePost(DataSet: TDataSet);
begin
      inherited;
      CdsCarga.FieldByName('SnAltera').AsString := 'S';
end;

procedure TMov0017.Conhecimentos1Click(Sender: TObject);
var lCon004 : TCon0004;
begin
      inherited;
      try
          lCon004 := TCon0004.Create(Self);
          lCon004.BtnLocali.Visible := False;
          lCon004.EdtCdCarga.BrAsInteger := CdsCarga.FieldByName('CdCarga').AsInteger;
          lCon004.BrvBitBtn1Click(lCon004.BtnLocali);
          lCon004.FormStyle := fsNormal;
          lCon004.Visible   := False;
          lCon004.ShowModal;
      finally
          FreeAndNil(lCon004);
      end;
end;

procedure TMov0017.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      inherited;
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

initialization
      RegisterClass(TMov0017);

finalization
      UnRegisterClass(TMov0017);

end.
