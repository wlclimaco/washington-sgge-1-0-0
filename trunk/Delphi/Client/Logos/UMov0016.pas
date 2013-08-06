unit UMov0016;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms, UClaSrv,
  Dialogs, UMov0000, Grids, BrvDbGrids, BrvDbGrid, StdCtrls, Buttons, BrvBitBtn, BrvRetCon,
  BrvEditNum, ExtCtrls, BrvSpeedButton, BrvDbNavCop, DB, DBClient, Menus, BrvImage, ImgList,
  BrvImgBot, BrvListParam, BrvCustomEdit;

type
  TMov0016 = class(TMov0000)
    PnlEmpres: TPanel;
    LblEmpres: TLabel;
    EdtCdEmpres: TBrvEditNum;
    LblDsEmpres: TBrvRetCon;
    CdsCarga: TClientDataSet;
    DsCarga: TDataSource;
    BtnPesqui: TBrvBitBtn;
    PnlRodape: TPanel;
    BrvBitBtn5: TBrvBitBtn;
    BrvBitBtn4: TBrvBitBtn;
    MenCtrc: TPopupMenu;
    Conhecimentos1: TMenuItem;
    PnlGrade: TPanel;
    DbgCargas: TBrvDbGrid;
    MarcarTodos1: TMenuItem;
    DesmarcarTodos1: TMenuItem;
    N1: TMenuItem;
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BtnPesquiClick(Sender: TObject);
    procedure BrvBitBtn5Click(Sender: TObject);
    procedure BrvBitBtn4Click(Sender: TObject);
    procedure Conhecimentos1Click(Sender: TObject);
    procedure MarcarTodos1Click(Sender: TObject);
    procedure DesmarcarTodos1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

//var
//  Mov0016: TMov0016;

implementation

uses UDmSrvApl, UCon0004;

{$R *.dfm}

procedure TMov0016.BrvBitBtn4Click(Sender: TObject);
begin
      CdsCarga.Close;

      PnlGrade.Visible  := False;
      PnlEmpres.Enabled := True;
end;

procedure TMov0016.BrvBitBtn5Click(Sender: TObject);
var lConexao  : TSDmTraClient;
    lDsResult : String;
begin
      lConexao := TSDmTraClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          CdsCarga.DisableControls;
          CdsCarga.First;

          while not CdsCarga.eof do
          begin
                if CdsCarga.FieldByName('SnMarcad').AsString = 'S' then
                begin
                      lDsResult := lConexao.CancelarCarga(DmSrvApl.BrvDicionario.Credencial,
                                                          CdsCarga.FieldByName('CdCarga').AsInteger,
                                                          Name);

                      DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
                end;

                CdsCarga.Next;
          end;

          BtnPesquiClick(Nil);

          ShowMessage('Cargas canceladas com sucesso!');
      finally
          CdsCarga.EnableControls;
          FreeAndNil(lConexao);
      end;

end;

procedure TMov0016.BtnPesquiClick(Sender: TObject);
var lDsParams : AnsiString;
begin
      inherited;
      if EdtCdEmpres.BrAsInteger = 0 then
      begin
            raise Exception.Create('Informe a empresa');
      end else
      begin
            lDsParams := '<%CdEmpres%>;' + EdtCdEmpres.Text;
            CdsCarga.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(152, lDsParams, Name);

            PnlGrade.Visible  := True;
            PnlEmpres.Enabled := False;
      end;

end;

procedure TMov0016.Conhecimentos1Click(Sender: TObject);
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

procedure TMov0016.DesmarcarTodos1Click(Sender: TObject);
begin
      inherited;
      DbgCargas.SetarTodasColunas('SnMarcad', 'N');
end;

procedure TMov0016.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

procedure TMov0016.MarcarTodos1Click(Sender: TObject);
begin
      inherited;
      DbgCargas.SetarTodasColunas('SnMarcad', 'S');
end;

initialization
      RegisterClass(TMov0016);

finalization
      UnRegisterClass(TMov0016);


end.
