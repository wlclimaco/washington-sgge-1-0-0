unit UMov0022;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  StdCtrls, BrvBitBtn, ComCtrls, Grids, BrvDbGrids, BrvDbGrid, DB, DBClient, BrvClientDataSet,
  BrvLabel;

type
  TMov0022 = class(TMov0000)
    PnlCab: TPanel;
    PnlControl: TPanel;
    DbgF013: TBrvDbGrid;
    PgcDet: TPageControl;
    TbsContabil: TTabSheet;
    DbgF014: TBrvDbGrid;
    TbsCtPag: TTabSheet;
    DbgF015: TBrvDbGrid;
    GroupBox4: TGroupBox;
    LblTtCcPag: TLabel;
    Label41: TLabel;
    Label42: TLabel;
    LblTtContabil: TLabel;
    BtnGravar: TBrvBitBtn;
    BtnAtualizar: TBrvBitBtn;
    CpF013: TBrvClientDataSet;
    DsF013: TDataSource;
    DsF014: TDataSource;
    DsF015: TDataSource;
    CpF014: TBrvClientDataSet;
    CpF015: TBrvClientDataSet;
    PopF013: TPopupMenu;
    odos1: TMenuItem;
    Nenhum1: TMenuItem;
    Splitter1: TSplitter;
    Splitter2: TSplitter;
    DbgF017: TBrvDbGrid;
    CpF017: TBrvClientDataSet;
    DsF017: TDataSource;
    LblTtCenCus: TBrvLabel;
    BrvLabel1: TBrvLabel;
    procedure FormCreate(Sender: TObject);
    procedure BtnAtualizarClick(Sender: TObject);
    procedure CpF013AfterScroll(DataSet: TDataSet);
    procedure BtnGravarClick(Sender: TObject);
    procedure odos1Click(Sender: TObject);
    procedure Nenhum1Click(Sender: TObject);
    procedure CpF014AfterScroll(DataSet: TDataSet);
  private
    procedure TotalizaContabil;
    procedure TotalizaPagar;
    procedure TotalizaCentroCusto;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Mov0022: TMov0022;

implementation

uses UDmSrvApl, UClaSrv;

{$R *.dfm}

procedure TMov0022.BtnAtualizarClick(Sender: TObject);
begin
      inherited;
      CpF013.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(178,
                                '<%CdEmpres%>;' + DmSrvApl.BrvDicionario.CorpCommaCodes, self.Name);
end;

procedure TMov0022.TotalizaContabil;
var lVrContabil : Extended;
begin
      try
          CpF014.DisableControls;
          CpF014.First;
          lVrContabil := 0;

          while not CpF014.Eof do
          begin
                lVrContabil := lVrContabil + CpF014.FieldByName('VrLancto').AsFloat;
                CpF014.Next;
          end;

          LblTtContabil.Caption := FormatFloat('0.00', lVrContabil);

      finally
          CpF014.first;
          CpF014.EnableControls;
      end;
end;

procedure TMov0022.TotalizaCentroCusto;
var lVrCenCus : Extended;
begin
      try
          CpF017.DisableControls;
          CpF017.First;
          lVrCenCus := 0;

          while not CpF017.Eof do
          begin
                lVrCenCus := lVrCenCus + CpF017.FieldByName('VrLancto').AsFloat;
                CpF017.Next;
          end;

          LblTtCenCus.Caption := FormatFloat('0.00', lVrCenCus);

      finally
          CpF017.First;
          CpF017.EnableControls;
      end;
end;

procedure TMov0022.TotalizaPagar;
var lVrPagar : Extended;
begin
      try
          CpF015.DisableControls;
          CpF015.First;
          lVrPagar := 0;

          while not CpF015.Eof do
          begin
                lVrPagar := lVrPagar + CpF015.FieldByName('VrDocto').AsFloat;
                CpF015.Next;
          end;

          LblTtCcPag.Caption := FormatFloat('0.00', lVrPagar);

      finally
          CpF015.First;
          CpF015.EnableControls;
      end;
end;

procedure TMov0022.BtnGravarClick(Sender: TObject);
var lNrCount : Integer;
    lConexao  : TSDmAdmClient;
    lDsResult : AnsiString;
begin
      inherited;
      lNrCount := 0;

      try
          CpF013.DisableControls;
          CpF013.AfterScroll := nil;
          CpF013.First;

          while not CpF013.eof do
          begin

                if (CpF013.FieldByName('SnCheck').AsString = 'S') then
                begin
                      Inc(lNrCount);
                end;

                CpF013.Next;
          end;

      finally
          CpF013.AfterScroll := CpF013AfterScroll;
          CpF013.First;
          CpF013.EnableControls;
      end;

      if (lNrCount > 0) then
      begin
            if (MessageDlg('Deseja realmente autorizar estes pré-lançamentos?', mtConfirmation,
                                                                     [mbyes, mbno], 0) = mrYes) then
            begin
                  try
                      CpF013.DisableControls;
                      CpF013.AfterScroll := nil;
                      CpF013.First;

                      while not CpF013.eof do
                      begin

                            if (CpF013.FieldByName('SnCheck').AsString = 'N') then
                            begin
                                  CpF013.Delete;
                            end else
                            begin
                                  CpF013.Next;
                            end;
                      end;

                      lConexao := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
                      lDsResult := lConexao.AutorizaPreLancamentoNFEntrada(
                                                             DmSrvApl.BrvDicionario.Credencial,
                                                             CpF013.Data);

                      DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

                      MessageDlg('Pré-Lançamento autorizados com sucesso!', mtInformation, [mbok], 0);
                      BtnAtualizarClick(BtnAtualizar);

                  finally
                      CpF013.AfterScroll := CpF013AfterScroll;
                      CpF013.First;
                      CpF013.EnableControls;
                  end;

            end;
      end else
      begin
            MessageDlg('Selecione ao menos um pré-lançamento!', mtConfirmation, [mbok], 0);
      end;
end;

procedure TMov0022.CpF013AfterScroll(DataSet: TDataSet);
begin
      inherited;
      if (CpF013.RecordCount > 0) then
      begin
            CpF014.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(179, '<%nrprelan%>;' +
                                            CpF013.FieldByName('NrPreLan').AsString , self.Name);
            CpF015.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(180, '<%nrprelan%>;' +
                                            CpF013.FieldByName('NrPreLan').AsString , self.Name);
            TotalizaContabil;
            TotalizaPagar;
            TotalizaCentroCusto;
      end else
      begin
            CpF014.Close;
            CpF015.Close;
            CpF017.Close;
      end;

end;

procedure TMov0022.CpF014AfterScroll(DataSet: TDataSet);
begin
      inherited;
      if (CpF014.RecordCount > 0) then
      begin
            CpF017.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(188,
            '<%NrPreLan%>;' + CpF014.FieldByName('NrPreLan').AsString + Chr(13) +
            '<%NrLancto%>;' + CpF014.FieldByName('NrLancto').AsString, self.Name);
      end else
      begin
            CpF017.Close;
      end;
end;

procedure TMov0022.FormCreate(Sender: TObject);
begin
      inherited;
      PgcDet.ActivePageIndex := 0;
      CpF013.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(178,
                                '<%CdEmpres%>;' + DmSrvApl.BrvDicionario.CorpCommaCodes, self.Name);
end;

procedure TMov0022.Nenhum1Click(Sender: TObject);
begin
      inherited;
      try
          CpF013.DisableControls;
          CpF013.AfterScroll := nil;
          CpF013.First;

          while not CpF013.eof do
          begin
                CpF013.Edit;
                CpF013.FieldByName('SnCheck').AsString := 'N';
                CpF013.Post;
                CpF013.Next;
          end;

      finally
          CpF013.AfterScroll := CpF013AfterScroll;
          CpF013.First;
          CpF013.EnableControls;
      end;
end;

procedure TMov0022.odos1Click(Sender: TObject);
begin
      inherited;
      try
          CpF013.DisableControls;
          CpF013.AfterScroll := nil;
          CpF013.First;

          while not CpF013.eof do
          begin
                CpF013.Edit;
                CpF013.FieldByName('SnCheck').AsString := 'S';
                CpF013.Post;

                CpF013.Next;
          end;

      finally
          CpF013.AfterScroll := CpF013AfterScroll;
          CpF013.First;
          CpF013.EnableControls;
      end;
end;

initialization
      RegisterClass(TMov0022);

finalization
      UnRegisterClass(TMov0022);

end.
