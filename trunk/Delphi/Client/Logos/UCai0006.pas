unit UCai0006;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, UCai0000, StdCtrls, Buttons, BrvBitBtn, ExtCtrls, BrvComboBox, CheckLst,
  BrvCheckListBox, Grids, DBGrids, BrvDbGrid, DB, DBClient, BrvClientDataSet,
  UClaSrv, BrvString, BrvSpeedButton, ComCtrls;

type
  TCai0006 = class(TCai0000)
    Panel3: TPanel;
    Label1: TLabel;
    CbxConDis: TBrvComboBox;
    QpChaEst: TBrvClientDataSet;
    BrvString: TBrvString;
    BrvString2: TBrvString;
    BrvBitBtn1: TBrvBitBtn;
    PgcConfig: TPageControl;
    TbsAtrDis: TTabSheet;
    TbsRetorn: TTabSheet;
    Panel8: TPanel;
    Label4: TLabel;
    CbxSepAnt: TBrvComboBox;
    Panel4: TPanel;
    Panel6: TPanel;
    CbxAtribu: TBrvCheckListBox;
    Panel5: TPanel;
    BtnAcima: TBrvSpeedButton;
    BtnAbaixo: TBrvSpeedButton;
    Panel7: TPanel;
    BrvClxRetorn: TListBox;
    Panel11: TPanel;
    BrvSpeedButton1: TBrvSpeedButton;
    Label2: TLabel;
    CbxAtrDes: TBrvComboBox;
    CbxAtrOri: TBrvComboBox;
    BrvSpeedButton2: TBrvSpeedButton;
    procedure FormCreate(Sender: TObject);
    procedure CbxConDisChange(Sender: TObject);
    procedure BtnAcimaClick(Sender: TObject);
    procedure BtnAbaixoClick(Sender: TObject);
    procedure BbtOkClick(Sender: TObject);
    procedure BrvBitBtn1Click(Sender: TObject);
    procedure BrvSpeedButton2Click(Sender: TObject);
    procedure BrvSpeedButton1Click(Sender: TObject);
  private
    procedure MostrarAtributosDisponiveis(pNmTabela : String);
    function DescricaoColuna(pNmTabela, pNmAtribu: String): String;
    procedure PosicionarAtributosDisplay(pDsAtrDis: String);
    procedure PosicaolistaAtributo(pDsAtribu: String);
  public
    { Public declarations }
    gDsAtrDis : String;
    gDsRetCon : String;
    gNmChaCon : String;
    procedure CarregarFormConfiguracao(pNmTabela : String;  pNmAtribu : String;
                                       pNrQueCon : Integer; pDsSepCon : String;
                                       pDsAtrCon : String;  pDsRetCon : String);

  end;

var
  Cai0006: TCai0006;

implementation

uses UDmSrvApl;

{$R *.dfm}

procedure TCai0006.CbxConDisChange(Sender: TObject);
begin
      if (CbxConDis.ItemIndex >= 0) and
         (QpChaEst.FindKey([CbxConDis.Values.Strings[CbxConDis.ItemIndex]])) then
      begin
            MostrarAtributosDisponiveis(QpChaEst.FieldByName('NmTabCon').AsString);
      end;
end;

procedure TCai0006.MostrarAtributosDisponiveis(pNmTabela : String);
var lStlAtrib : TStrings;
    lNrAtribu : Integer;
begin
      CbxAtribu.Items.Clear;
      CbxAtribu.Values.Clear;

      CbxAtrOri.Items.Clear;
      CbxAtrOri.Values.Clear;

      try
          lStlAtrib := TStringList.Create;
          DmSrvApl.BrvDicionario.AtributosDaTabela(pNmTabela, lStlAtrib);

          for lNrAtribu := 0 to lStlAtrib.Count -1 do
          begin
                CbxAtribu.Items.Add(pNmTabela  + '.' + lStlAtrib.Strings[lNrAtribu] +
                                    ' - '      +
                                    DescricaoColuna(pNmTabela,
                                                         lStlAtrib.Strings[lNrAtribu]));
                CbxAtribu.Values.Add(pNmTabela + '.' + lStlAtrib.Strings[lNrAtribu]);
          end;
      finally
          FreeAndNil(lStlAtrib);
      end;

      CbxAtrOri.Items.Text   := CbxAtribu.Items.Text;
      CbxAtrOri.Values.Text  := CbxAtribu.Values.Text;
end;

function TCai0006.DescricaoColuna(pNmTabela : String; pNmAtribu : String) : String;
var lDsTemp : String;
    lNrTemp : Integer;
    lSnTemp : Boolean;
begin
      DmSrvApl.BrvDicionario.AtributoConfiguracao(
                             pNmTabela, pNmAtribu, Result,  lDsTemp, lDsTemp, lDsTemp,
                             lDsTemp,   lDsTemp,   lNrTemp, lNrTemp, lDsTemp, lDsTemp,
                             lSnTemp,   lDsTemp);
end;

procedure TCai0006.FormCreate(Sender: TObject);
var lDsDomini : TStrings;
    lVrDomini : TStrings;
begin
      inherited;
      DmSrvApl.BrvDicionario.AtributoDominioValores(
                                                'S026', 'DSSEPANT', lDsDomini, lVrDomini);
      CbxSepAnt.Items.Text  := lDsDomini.Text;
      CbxSepAnt.Values.Text := lVrDomini.Text;
end;

procedure TCai0006.BbtOkClick(Sender: TObject);
var lNrAtribu : Integer;
begin
      if CbxConDis.ItemIndex < 0 then
      begin
            raise Exception.Create('Nenhuma consulta foi informada');
      end;

      gDsAtrDis := '';
      gDsRetCon := '';

      for lNrAtribu := 0 to CbxAtribu.Values.Count -1 do
      begin
            if CbxAtribu.Checked[lNrAtribu] then
            begin
                  gDsAtrDis := gDsAtrDis + CbxAtribu.Values.Strings[lNrAtribu] + '@';
            end;
      end;

      for lNrAtribu := 0 to BrvClxRetorn.Items.Count - 1 do
      begin
            gDsRetCon := gDsRetCon + BrvClxRetorn.Items.Strings[lNrAtribu] + '@';
      end;

      if gDsAtrDis = '' then
      begin
            raise Exception.Create('Nenhum atributo de display foi selecionado');
      end;

      if CbxSepAnt.ItemIndex < 0 then
      begin
            raise Exception.Create('Informe o separador de campo');
      end;

      if not QpChaEst.FindKey([CbxConDis.Values.Strings[CbxConDis.ItemIndex]]) then
      begin
            raise Exception.Create('Falha na localização do nome da chave primária');
      end;

      gNmChaCon := QpChaEst.FieldByName('NmChaEst').AsString;

      ModalResult := mrOk;
end;

procedure TCai0006.BrvBitBtn1Click(Sender: TObject);
begin
      if MessageDlg('Confirma a exclusão dessa consulta?', mtConfirmation,
                                                           [mbYes, mbNo], 0) = idYes then
      begin
            gDsAtrDis := 'Excluir';
            ModalResult := MrOk;
      end;
end;

procedure TCai0006.BrvSpeedButton1Click(Sender: TObject);
var lNmTabela : String;
begin
      if BrvClxRetorn.ItemIndex >= 0 then
      begin
            BrvString.Split(BrvClxRetorn.Items.Strings[BrvClxRetorn.ItemIndex], '=');
            lNmTabela := Trim(BrvString.BrSplitLista[0]);
            BrvString.Split(lNmTabela, '.');
            CbxAtrDes.Items.Add(BrvString.BrSplitLista[0] + '.' +
                                BrvString.BrSplitLista[1]+
                              ' - '      +
                              DescricaoColuna(BrvString.BrSplitLista[0],
                                              BrvString.BrSplitLista[1]));

          CbxAtrDes.Values.Add(BrvString.BrSplitLista[0] + '.' +
                               BrvString.BrSplitLista[1]);

          BrvClxRetorn.DeleteSelected;
      end;
end;

procedure TCai0006.BrvSpeedButton2Click(Sender: TObject);
begin
      if (CbxAtrDes.Text = '') or (CbxAtrOri.Text = '') then
      begin
            raise Exception.Create('Igualdade não configurada corretamente');
      end;

      BrvClxRetorn.Items.Add(CbxAtrDes.Values[CbxAtrDes.ItemIndex] + ' = ' +
                             CbxAtrOri.Values[CbxAtrOri.ItemIndex]);

      CbxAtrDes.Values.Delete(CbxAtrDes.ItemIndex);
      CbxAtrDes.Items.Delete(CbxAtrDes.ItemIndex);
end;

procedure TCai0006.BtnAbaixoClick(Sender: TObject);
var lDsProxim : String;
    lVrProxim : String;
    lNrAtual  : Integer;
    lNrProxim : Integer;

    lSnChePro : Boolean;
    lSnCheAtu : Boolean;
begin
      if (CbxAtribu.ItemIndex < (CbxAtribu.Items.Count - 1)) and
         (CbxAtribu.ItemIndex > -1) then
      begin
            lNrAtual  := CbxAtribu.ItemIndex;
            lNrProxim := CbxAtribu.ItemIndex + 1;

            lSnChePro := CbxAtribu.Checked[lNrProxim];
            lSnCheAtu := CbxAtribu.Checked[lNrAtual];

            lDsProxim := CbxAtribu.Items.Strings[lNrProxim];
            lVrProxim := CbxAtribu.Values.Strings[lNrProxim];

            CbxAtribu.Items.Strings[lNrProxim]  := CbxAtribu.Items.Strings[lNrAtual];
            CbxAtribu.Values.Strings[lNrProxim] := CbxAtribu.Values.Strings[lNrAtual];

            CbxAtribu.Items.Strings[lNrAtual]  := lDsProxim;
            CbxAtribu.Values.Strings[lNrAtual] := lVrProxim;

            CbxAtribu.Checked[lNrProxim] := lSnCheAtu;
            CbxAtribu.Checked[lNrAtual]  := lSnChePro;

            CbxAtribu.ItemIndex := lNrProxim;
      end;
end;

procedure TCai0006.BtnAcimaClick(Sender: TObject);
var lDsAnteri : String;
    lVrAnteri : String;
    lNrAtual  : Integer;
    lNrAnteri : Integer;

    lSnCheAnt : Boolean;
    lSnCheAtu : Boolean;
begin
      if CbxAtribu.ItemIndex > 0 then
      begin
            lNrAtual  := CbxAtribu.ItemIndex;
            lNrAnteri := CbxAtribu.ItemIndex - 1;

            lSnCheAnt := CbxAtribu.Checked[lNrAnteri];
            lSnCheAtu := CbxAtribu.Checked[lNrAtual];

            lDsAnteri := CbxAtribu.Items.Strings[lNrAnteri];
            lVrAnteri := CbxAtribu.Values.Strings[lNrAnteri];

            CbxAtribu.Items.Strings[lNrAnteri]  := CbxAtribu.Items.Strings[lNrAtual];
            CbxAtribu.Values.Strings[lNrAnteri] := CbxAtribu.Values.Strings[lNrAtual];

            CbxAtribu.Items.Strings[lNrAtual]  := lDsAnteri;
            CbxAtribu.Values.Strings[lNrAtual] := lVrAnteri;

            CbxAtribu.Checked[lNrAnteri] := lSnCheAtu;
            CbxAtribu.Checked[lNrAtual]  := lSnCheAnt;

            CbxAtribu.ItemIndex := lNrAnteri;
      end;
end;

procedure TCai0006.CarregarFormConfiguracao(pNmTabela : String;  pNmAtribu : String;
                                            pNrQueCon : Integer; pDsSepCon : String;
                                            pDsAtrCon : String;  pDsRetCon : String);
var lConexao  : TSDmRWClient;
    lDsResult : String;
    lDsSql    : String;
    lNmChaAnt : String;

    lStlAtrib : TStrings;
    lNrAtribu : Integer;
    lNrRetorn : Integer;
begin
      lConexao := TSDmRWClient.Create(DmSrvApl.SrvAplica.DBXConnection);

      try
          lDsSql := ' Select S013.NmChaEst, S016.NmTabCon, S016.NrQueCon, ' +
                    '        S016.DsConsul ' +
                    ' From   S013                                              ' +
                    ' Left Join S012 S012 on (S012.NmChaEst = S013.NmChaEst)   ' +
                    ' Left Join S016 S016 on (S016.NmTabCon = S012.NmTabEst)   ' +
                    ' Where  S013.NmTabela = ' + QuotedStr(pNmTabela)  + ' and ' +
                    '        S013.NmAtribu = ' + QuotedStr(pNmAtribu);
//                    ' Order by S013.NmChaEst';

          QpChaEst.Data := lConexao.RetornaDadosSqlFixa(DmSrvApl.BrvDicionario.Credencial,
                                                        lDsResult, lDsSql);
          DmSrvApl.BrvDicionario.VerificarMensagemServidorAplicacao(lDsResult);

          CbxConDis.Items.Clear;
          CbxConDis.Values.Clear;

          lNmChaAnt := '';

          while not QpChaEst.Eof do
          begin
                //if lNmChaAnt <> QpChaEst.FieldByName('NmChaEst').AsString then
                //begin
                      lNmChaAnt := QpChaEst.FieldByName('NmChaEst').AsString;
                      CbxConDis.Items.Add(QpChaEst.FieldByName('NrQueCon').AsString +
                                          ' - ' +
                                          QpChaEst.FieldByName('DsConsul').AsString);
                      CbxConDis.Values.Add(QpChaEst.FieldByName('NrQueCon').AsString);
                //end;

                QpChaEst.Next;
          end;

          QpChaEst.IndexDefs.Add('Index', 'NRQUECON', [ixCaseInsensitive]);
          QpChaEst.IndexName := 'Index';
          QpChaEst.First;
      finally
          FreeAndNil(lConexao);
      end;

      if pNrQueCon > 0 then
      begin
            CbxConDis.BrvPosicionarValor(IntToStr(pNrQueCon));
            CbxSepAnt.BrvPosicionarValor(pDsSepCon);
            CbxConDisChange(CbxConDis);

            PosicionarAtributosDisplay(pDsAtrCon);
      end;

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-= Atributos para retorno
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      CbxAtrDes.Items.Clear;
      CbxAtrDes.Values.Clear;
      try
          lStlAtrib := TStringList.Create;
          DmSrvApl.BrvDicionario.AtributosDaTabela(pNmTabela, lStlAtrib);

          for lNrAtribu := 0 to lStlAtrib.Count-1 do
          begin
                CbxAtrDes.Items.Add(pNmTabela  + '.' + lStlAtrib.Strings[lNrAtribu] +
                                    ' - '      +
                                    DescricaoColuna(pNmTabela,
                                                           lStlAtrib.Strings[lNrAtribu]));
                CbxAtrDes.Values.Add(pNmTabela + '.' + lStlAtrib.Strings[lNrAtribu]);
          end;
      finally
          FreeAndNil(lStlAtrib);
      end;
      BrvString.Split(pDsRetCon, '@');
      BrvClxRetorn.Items.Text := BrvString.BrSplitLista.Text;

      for lNrRetorn := 0 to BrvClxRetorn.Items.Count-1 do
      begin
            BrvString.Split(BrvClxRetorn.Items.Strings[lNrRetorn], ' = ');

            lNrAtribu := 0;
            while lNrAtribu < CbxAtrDes.Items.Count do
            begin
                  if CbxAtrDes.Values.Strings[lNrAtribu] =
                                                    BrvString.BrSplitLista.Strings[0] then
                  begin
                        CbxAtrDes.Items.Delete(lNrAtribu);
                        CbxAtrDes.Values.Delete(lNrAtribu);
                        lNrAtribu := CbxAtrDes.Items.Count;
                  end;

                  inc(lNrAtribu);
            end;
      end;

      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      PgcConfig.ActivePage := TbsAtrDis;
end;

procedure TCai0006.PosicionarAtributosDisplay(pDsAtrDis : String);
var lNrAtrLis : Integer;
    lNrIndex  : Integer;
begin
      BrvString.Split(pDsAtrDis, '@');

      for lNrAtrLis := 0 to BrvString.brSplitLista.Count -1 do
      begin
            if BrvString.brSplitLista.Strings[lNrAtrLis] <> '' then
            begin
                  PosicaoListaAtributo(BrvString.brSplitLista.Strings[lNrAtrLis]);

                  if CbxAtribu.ItemIndex >= 0 then
                  begin
                        while CbxAtribu.ItemIndex <> lNrAtrLis do
                        begin
                              BtnAcimaClick(BtnAcima);
                        end;
                  end;
            end;
      end;
end;

procedure TCai0006.PosicaolistaAtributo(pDsAtribu : String);
var lNrAtribu : Integer;
begin
      lNrAtribu := 0;

      while (lNrAtribu < CbxAtribu.Values.Count) and
            (CbxAtribu.Values.Strings[lNrAtribu] <> pDsAtribu) do
      begin
            inc(lNrAtribu);
      end;

      if lNrAtribu < CbxAtribu.Values.Count then
      begin
            CbxAtribu.ItemIndex := lNrAtribu;
            CbxAtribu.Checked[lNrAtribu] := True;
      end;
end;

end.
