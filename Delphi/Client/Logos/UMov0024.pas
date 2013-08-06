unit UMov0024;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UMov0000, BrvListParam, ImgList, Menus, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  ComCtrls, StdCtrls, BrvBitBtn, Grids, BrvDbGrids, BrvDbGrid, DB, DBClient, BrvClientDataSet;

type
  TMov0024 = class(TMov0000)
    PnlCab: TPanel;
    DbgF012: TBrvDbGrid;
    Splitter1: TSplitter;
    PnlControl: TPanel;
    BtnMontaLote: TBrvBitBtn;
    BtnAtualizar: TBrvBitBtn;
    Panel1: TPanel;
    DbgF013: TBrvDbGrid;
    DsF013: TDataSource;
    DsF012: TDataSource;
    CpF012: TBrvClientDataSet;
    CpF013: TBrvClientDataSet;
    PopF012: TPopupMenu;
    odos1: TMenuItem;
    Nenhum1: TMenuItem;
    procedure odos1Click(Sender: TObject);
    procedure CpF012AfterScroll(DataSet: TDataSet);
    procedure FormCreate(Sender: TObject);
    procedure Nenhum1Click(Sender: TObject);
    procedure BtnAtualizarClick(Sender: TObject);
    procedure BtnMontaLoteClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Mov0024: TMov0024;

implementation

uses UDmSrvApl, UClaSrv;

{$R *.dfm}

procedure TMov0024.BtnAtualizarClick(Sender: TObject);
begin
      inherited;
      CpF012.Data :=  DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(184, '', self.Name);
end;

procedure TMov0024.BtnMontaLoteClick(Sender: TObject);
var lNrCount  : Integer;
    lConexao  : TSDmAdmClient;
    lDsResult : AnsiString;
begin
      inherited;
      lNrCount := 0;

      try
          CpF012.DisableControls;
          CpF012.AfterScroll := nil;
          CpF012.First;

          while not CpF012.eof do
          begin

                if (CpF012.FieldByName('SnCheck').AsString = 'S') then
                begin
                      Inc(lNrCount);
                end;

                CpF012.Next;
          end;

      finally
          CpF012.AfterScroll := CpF012AfterScroll;
          CpF012.First;
          CpF012.EnableControls;
      end;

      if (lNrCount > 0) then
      begin
            if (MessageDlg('Deseja realmente recepcionar o(s) lote(s) selecionado(s)?',
                                                     mtConfirmation, [mbyes, mbno], 0) = mrYes) then
            begin
                  try
                      CpF012.DisableControls;
                      CpF012.AfterScroll := nil;
                      CpF012.First;

                      while not CpF012.eof do
                      begin

                            if (CpF012.FieldByName('SnCheck').AsString = 'N') then
                            begin
                                  CpF012.Delete;
                            end else
                            begin
                                  CpF012.Next;
                            end;
                      end;

                      lConexao  := TSDmAdmClient.Create(DmSrvApl.SrvAplica.DBXConnection);
                      lDsResult := lConexao.RecepcionaLotePreLancamentoNFEntrada(
                                                                  DmSrvApl.BrvDicionario.Credencial,
                                                                  CpF012.Data);

                      DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

                      MessageDlg('Lote(s) recepcionado(s) com com sucesso!',mtInformation,[mbok],0);
                      BtnAtualizarClick(BtnAtualizar);
                  finally
                      CpF012.AfterScroll := CpF012AfterScroll;
                      CpF012.First;
                      CpF012.EnableControls;
                  end;

            end;
      end else
      begin
            MessageDlg('Selecione ao menos um pré-lançamento!', mtConfirmation, [mbok], 0);
      end;
end;

procedure TMov0024.CpF012AfterScroll(DataSet: TDataSet);
begin
      inherited;
      if CpF012.RecordCount > 0 then
      begin
            CpF013.Data :=  DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(185, '<%nrseqlot%>;' +
                                CpF012.FieldByName('NrSeqLot').AsString + Chr(13) +
                                '<%CdEmpres%>;' + DmSrvApl.BrvDicionario.CorpCommaCodes, self.Name);
      end else
      begin
            CpF013.Close;
      end;
end;

procedure TMov0024.FormCreate(Sender: TObject);
begin
      inherited;
      CpF012.Data :=  DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(184, '', self.Name);
end;

procedure TMov0024.Nenhum1Click(Sender: TObject);
begin
      inherited;
      try
          CpF012.DisableControls;
          CpF012.AfterScroll := nil;
          CpF012.First;

          while not CpF012.eof do
          begin
                CpF012.Edit;
                CpF012.FieldByName('SnCheck').AsString := 'N';
                CpF012.Post;

                CpF012.Next;
          end;

      finally
          CpF012.AfterScroll := CpF012AfterScroll;
          CpF012.First;
          CpF012.EnableControls;
      end;
end;

procedure TMov0024.odos1Click(Sender: TObject);
begin
      inherited;
      try
          CpF012.DisableControls;
          CpF012.AfterScroll := nil;
          CpF012.First;

          while not CpF012.eof do
          begin
                CpF012.Edit;
                CpF012.FieldByName('SnCheck').AsString := 'S';
                CpF012.Post;

                CpF012.Next;
          end;

      finally
          CpF012.AfterScroll := CpF012AfterScroll;
          CpF012.First;
          CpF012.EnableControls;
      end;
end;

initialization
      RegisterClass(TMov0024);

finalization
      UnRegisterClass(TMov0024);

end.
