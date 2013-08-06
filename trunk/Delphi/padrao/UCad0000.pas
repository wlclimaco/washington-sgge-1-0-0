unit UCad0000;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, ExtCtrls, BrvDbNavCop, Buttons, BrvSpeedButton, UClaSrv, DB, DBClient,
  BrvClientDataSet, Menus, BrvImage, ImgList, BrvImgBot, Grids, BrvDbGrids, BrvDbGrid, BrvListParam;

type
  TCad0000 = class(TForm)
    NavBarra: TBrvDbNavCop;
    BrvSpeedButton1: TBrvSpeedButton;
    SbtAjuda: TBrvSpeedButton;
    PnlFundo: TPanel;
    PopCfgFrm: TPopupMenu;
    Ajuda1: TMenuItem;
    Apontamento1: TMenuItem;
    ImlPopFrm: TImageList;
    LspS049: TBrvListParam;
    ParmetrosdeContabilizao1: TMenuItem;
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure BrvSpeedButton1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure Apontamento1Click(Sender: TObject);
    procedure SbtAjudaClick(Sender: TObject);
    procedure Ajuda1Click(Sender: TObject);
    procedure ParmetrosdeContabilizao1Click(Sender: TObject);
  private
    function LogValorChavePrimaria(pCdsChave: TClientDataSet; pNmTabela : String): String;
    procedure ProcessarAjuda;
    function Contabiliza(pNrSeqFor, pTpFormul: string): Boolean;
    { Private declarations }
  public
    { Public declarations }
    gVrParam  : String;
    gStlParCon : TStrings;
  protected
    procedure VerLog(pCdsLog: TClientDataSet; pNmTabela : String);
  end;

var
  Cad0000: TCad0000;

implementation

uses UDmSrvApl, UCai0010, UCad0010, UCad0011;

{$R *.dfm}

procedure TCad0000.ParmetrosdeContabilizao1Click(Sender: TObject);
var lDsWhere : String;
    lNrSeqfor: String;
    lTpFormul: String;
begin
      Cad0011           := TCad0011.Create(Self);
      Cad0011.FormStyle := fsNormal;
      Cad0011.Visible   := False;
      Cad0011.Position  := poMainFormCenter;

      lTpFormul := UpperCase(Copy(Self.Name, 1, 3));
      lNrSeqfor := Copy(Self.Name, 4, 4);

      Cad0011.gNRSEQFOR   := StrToInt(lNrSeqfor);
      Cad0011.gTPFORMUL   := lTpFormul;
      Cad0011.LblDsFormul.Text := Self.Caption;
      Cad0011.TmrInicia.Enabled := True;
      Cad0011.gStlParCon := gStlParCon;
      Cad0011.ShowModal;
end;

procedure TCad0000.ProcessarAjuda;
begin
      MessageDlg('Em Desenvolvimento...', mtInformation, [mbok], 0);
end;

procedure TCad0000.Ajuda1Click(Sender: TObject);
begin
      ProcessarAjuda;
end;

procedure TCad0000.Apontamento1Click(Sender: TObject);
var lDsWhere : String;
    lNrSeqfor: String;
    lTpFormul: String;
begin
      Cad0010           := TCad0010.Create(Self);
      Cad0010.FormStyle := fsNormal;
      Cad0010.Visible   := False;
      Cad0010.Position  := poMainFormCenter;

      //CAD0001
      lTpFormul := UpperCase(Copy(Self.Name, 1, 3));
      lNrSeqfor := Copy(Self.Name, 4, 4);

      Cad0010.gNRSEQFOR   := StrToInt(lNrSeqfor);
      Cad0010.gTPFORMUL   := lTpFormul;
      Cad0010.LblDsFormul.Text := Self.Caption;

      lDsWhere := 'Where S049.NrSeqFor =  ' + lNrSeqfor + ' and ' +
                  '      S049.TpFormul = "' + lTpFormul + '"';

      LspS049.Clear;
      LspS049.Add('DsWhere', '', lDsWhere, lDsWhere);

      Cad0010.QcS049.BrParams.Text := LspS049.AsBrParam;
      Cad0010.QcS049.Open;
      Cad0010.ShowModal;
end;

procedure TCad0000.BrvSpeedButton1Click(Sender: TObject);
begin
      Close;
end;

procedure TCad0000.FormClose(Sender: TObject; var Action: TCloseAction);
begin
      Action := caFree;
end;

function TCad0000.Contabiliza(pNrSeqFor: string; pTpFormul: string): Boolean;
var lCpS006 : TClientDataSet;
begin
      ParmetrosdeContabilizao1.Visible := False;

      try
          lCpS006 := TClientDataSet.Create(Self);
          lCpS006.Data := DmSrvApl.BrvDicionario.RetornaDadosSqlCadastro(174,
                                                 '<%TpFormul%>;' + pTpFormul + Chr(13) +
                                                 '<%NrSeqFor%>;' + pNrSeqFor, Self.Name);

          gStlParCon := TStringList.Create;
          gStlParCon.Text := Trim(lCpS006.FieldByName('DsParCon').AsString);

          ParmetrosdeContabilizao1.Visible := (gStlParCon.Text <> '');

          if (not DmSrvApl.BrvDicionario.UserGroupAdm) then
          begin
                ParmetrosdeContabilizao1.Visible :=
                            DmSrvApl.BrvDicionario.TemPermissaoTotalAtributo('B012', 'CDEMPRES') and
                            DmSrvApl.BrvDicionario.TemPermissaoTotalAtributo('B012', 'NRPLANO' ) and
                            DmSrvApl.BrvDicionario.TemPermissaoTotalAtributo('B012', 'TPFORMUL') and
                            DmSrvApl.BrvDicionario.TemPermissaoTotalAtributo('B012', 'NRSEQFOR') and
                            DmSrvApl.BrvDicionario.TemPermissaoTotalAtributo('B012', 'NRSEQPAR');
          end;

      finally
          FreeAndNil(lCpS006);
      end;
end;

procedure TCad0000.FormCreate(Sender: TObject);
var lConexao     : TSDmSisClient;
    lDsResult    : String;
    lTpFormul    : String;
    lNrSeqFor    : String;
begin
      SbtAjuda.Left := NavBarra.Width - 52;
      BrvSpeedButton1.Left := NavBarra.Width - 27;

      Apontamento1.Visible := DmSrvApl.BrvDicionario.UserGroupAdm;

      if Pos('CAD0003', UpperCase(Name)) = 0 then
      begin
            lConexao := TSDmSisClient.Create(DmSrvApl.SrvAplica.DBXConnection);

            try
                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                // =-=-=-= Criarndo Provider no servidor de aplicação
                // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                lNrSeqFor := UpperCase(Name);
                lTpFormul := Copy(lNrSeqFor, 1, 3);
                Delete(lNrSeqfor, 1, 3);

                Contabiliza(lNrSeqFor, lTpFormul);

                lConexao.AtualizarUtilizacaoFormulario(
                                         DmSrvApl.BrvDicionario.Credencial, lDsResult,
                                         DmSrvApl.BrvDicionario.UserCode,
                                         0, lTpFormul, StrToInt(lNrSeqFor));

                DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);
            finally
                FreeAndNil(lConexao);
            end;
      end;
end;

procedure TCad0000.VerLog(pCdsLog : TClientDataSet; pNmTabela : String);
var lDsChaPri : String;
begin
      lDsChaPri := LogValorChavePrimaria(pCdsLog, pNmTabela);
      try
          Cai0010 := TCai0010.Create(Self);
          Cai0010.CarregarAtributos(pNmTabela, lDsChaPri);
          Cai0010.MostrarLog('');
          Cai0010.ShowModal;
      finally
          FreeAndNil(Cai0010);
      end;
end;

function TCad0000.LogValorChavePrimaria(pCdsChave : TClientDataSet;
                                        pNmTabela : String) : String;
var  lStlChave : TStrings;
     lNrChave  : Integer;
begin
      try
          lStlChave   := TStringList.Create;
          DmSrvApl.BrvDicionario.ChavePrimaria(pNmTabela, lStlChave);
          Result      := '';

          for lNrChave := 0 to lStlChave.Count - 1 do
          begin
                Result := Result +
                          pCdsChave.FieldByName(lStlChave.Strings[lNrChave]).AsString +
                          '@';
          end;
      finally
          FreeAndNil(lStlChave);
      end;
end;

procedure TCad0000.SbtAjudaClick(Sender: TObject);
var lPoint : TPoint;
begin
      GetCursorPos(lPoint);
      PopCfgFrm.Popup(lPoint.X, lPoint.Y);
end;

end.
