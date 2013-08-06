unit BrvConsultaForm;

interface

uses Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, ExtCtrls,
     StdCtrls, Grids, DBGrids, Db, DBTables, Buttons, BrvEditNum, Mask, BrvEditDate,
     BrvDbNavCop, BrvComboBox, FMTBcd, DBClient, BrvClientDataSet,
     SqlExpr, BrvDataSet, BrvConnection, Provider, MConnect, SConnect, BrvDbGrids,
     BrvDbGrid, BrvCustomMaskEdit, BrvCustomEdit, UClaSrv;

type
    TFrmConsulta = class(TForm)
    PnlFiltro: TPanel;
    Label1: TLabel;
    CbxOrdem: TBrvComboBox;
    DbgConsul: TBrvDBGrid;
    DtsConsul: TDataSource;
    DbNavCop: TBrvDbNavCop;
    BtnColuna: TSpeedButton;
    Panel2: TPanel;
    GroupBox1: TGroupBox;
    EdtDtLocali: TBrvEditDate;
    EdtNrLocali: TBrvEditNum;
    EdtDsLocali: TEdit;
    CbxPesqui: TComboBox;
    Label2: TLabel;
    LblTpWhere: TLabel;
    GroupBox2: TGroupBox;
    Label4: TLabel;
    Label5: TLabel;
    EdtDtLocal2: TBrvEditDate;
    EdtNrLocal2: TBrvEditNum;
    EdtDsLocal2: TEdit;
    CbxPesqu2: TComboBox;
    CbxDsLocal2: TBrvComboBox;
    CbxDsLocali: TBrvComboBox;
    CbxMaiMin: TCheckBox;
    Label6: TLabel;
    CbxTpWhere: TComboBox;
    CsConsul: TBrvClientDataSet;
    DsConsul: TBrvDataSet;
    procedure FormCreate(Sender: TObject);
    procedure CbxOrdemChange(Sender: TObject);
    procedure CbxPesquiChange(Sender: TObject);
    procedure EdtDsLocaliKeyPress(Sender: TObject; var Key: Char);
    procedure FormKeyPress(Sender: TObject; var Key: Char);
    procedure BtnColunaClick(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure DbgConsulKeyPress(Sender: TObject; var Key: Char);
    procedure DbgConsulDblClick(Sender: TObject);
    procedure btnFecharClick(Sender: TObject);
    procedure CbxPesqu2Change(Sender: TObject);
    procedure EdtDsLocal2Change(Sender: TObject);
    procedure DbgConsulTitleClick(Column: TColumn);
    procedure CbxDsLocaliChange(Sender: TObject);
    procedure CbxDsLocal2Change(Sender: TObject);
    procedure CbxTpWhereChange(Sender: TObject);
    procedure CbxMaiMinClick(Sender: TObject);
  private
    { Private declarations }
    gStlTabSql : TStrings; // Tabelas das colunas do SQL
    gStlColSql : TStrings; // colunas que o usuário pode visualizar da sql
    gStlChaCol : TStrings; // Tipo de caracter
    gStlCamCol : TStrings; // Tipo de campo da coluna
    gStlDisLab : TStrings; // Display Label da coluna
    gStlVrDomi : TStrings; // Valor de domínio da coluna
    gStlDsDomi : TStrings; // Descrição do domínio da coluna
    gStlCombo  : TStrings; // Componente do tipo combobox
    gSnAtuCon  : Boolean;
    gDsOrdAtu  : String;
    gNmTabCon : String;   // Nome da tabela de pesquisa
    procedure AtualizarConfiguracaoUsuario;
    procedure AtualizarConfiguracaoColunasUsuario;
    procedure ColunasComPermissaoVisualizacao(pDsSql : String);
    procedure VerificarPermissaoVisualizacao(pNmTabela : String;
                                             pNmColuna : String);
    procedure ProximaTabela(var pDsSql    : String; var pNmTabela : String;
                            var pNmApelid : String; var pSnFrom   : Boolean);
    procedure ProximaPalavra(var pDsLexema : String; var pDsSql : String;
                             var pSnFrom   : Boolean);
    procedure ColunasVisualizadasPeloUsuario(var pStlColuna : TStrings;
                                             var pStlLagCom : TStrings);
    function  ColunaVisivelUsuario(pNmColuna : String) : Boolean;
    procedure AtributosDeOrdenacaoPesquisa;
    procedure AbrirQueryConsulta;
    procedure OrdenarConsulta;
    function  DisplayLabelColuna(pNmColuna : String): String;
    function  ColunaVisivelGrid(pNmColuna : String) : Boolean;
    procedure AtualizarPreferenciasUsuario;
    procedure CarregarRetornoConsulta;
    function  AliasColuna(PDsSelect : String; pNmColuna : String) : String;
    procedure SubstituirParametros(var pDsSql : String);
    procedure CriarTabelaConfiguracaoColunasUsuario(pCdsAuxili: TClientDataSet);
    function ColunaDeOrdenacao(pNmField: String): TColumn;
    procedure CarregarAtributosPrimeiroAcesso;
  public
    { Public declarations }
    gDsComWhe  : String;
    gNmForPai : String; // Nome do formulário pai, que chamou a consulta
    procedure MontarConsulta;
  end;

var
  FrmConsulta: TFrmConsulta;

implementation

uses BrvConColForm, BrvDicionario, BrvString;

{$R *.DFM}

procedure TFrmConsulta.FormCreate(Sender: TObject);
begin
      gStlTabSql := TStringList.Create;
      gStlColSql := TStringList.Create;
      gStlChaCol := TStringList.Create;
      gStlCamCol := TStringList.Create;
      gStlDisLab := TStringList.Create;
      gStlVrDomi := TStringList.Create;
      gStlDsDomi := TStringList.Create;
      gStlCombo  := TStringList.Create;

      gSnAtuCon  := False;
      gDsOrdAtu  := '';
      gDsComWhe  := '';

      EdtNrLocali.Left := EdtDsLocali.Left;
      EdtNrLocali.Top  := EdtDsLocali.Top;

      EdtDtLocali.Left := EdtDsLocali.Left;
      EdtDtLocali.Top  := EdtDsLocali.Top;

      CbxDsLocali.Left := EdtDsLocali.Left;
      CbxDsLocali.Top  := EdtDsLocali.Top;

      EdtNrLocal2.Left := EdtDsLocal2.Left;
      EdtNrLocal2.Top  := EdtDsLocal2.Top;

      EdtDtLocal2.Left := EdtDsLocal2.Left;
      EdtDtLocal2.Top  := EdtDsLocal2.Top;

      CbxDsLocal2.Left := EdtDsLocal2.Left;
      CbxDsLocal2.Top  := EdtDsLocal2.Top;
end;

procedure TFrmConsulta.FormKeyPress(Sender: TObject; var Key: Char);
begin
      case key of
           #27:  ModalResult := MrCancel;
      end;
end;

procedure TFrmConsulta.FormClose(Sender: TObject;
  var Action: TCloseAction);
begin
      AtualizarPreferenciasUsuario;

      gStlTabSql.Destroy;
      gStlColSql.Destroy;
      gStlChaCol.Destroy;
      gStlCamCol.Destroy;
      gStlDisLab.Destroy;
      gStlVrDomi.Destroy;
      gStlDsDomi.Destroy;
      gStlCombo.Destroy;
end;

procedure TFrmConsulta.AtualizarPreferenciasUsuario;
begin
      if gSnAtuCon then
      begin
            AtualizarConfiguracaoUsuario;
      end;

      AtualizarConfiguracaoColunasUsuario;
end;

procedure TFrmConsulta.AtualizarConfiguracaoUsuario;
var lNmTabOrd : String;
    lNmAtrOrd : String;
    lNmTabPes : String;
    lNmAtrPes : String;
    lNmTabPe2 : String;
    lNmAtrPe2 : String;
    lDsLocal2 : String;
    lTpMaiMin : String;
    lTpWhere  : Integer;

    lConexao   : TSDmSisClient;
    lDsResult  : String;
begin
      lNmTabOrd := '';
      lNmAtrOrd := '';
      lNmTabPes := '';
      lNmAtrPes := '';
      lNmTabPe2 := '';
      lNmAtrPe2 := '';
      lDsLocal2 := '';

      if CbxMaiMin.Checked then
      begin
            lTpMaiMin := '1';
      end else
      begin
            lTpMaiMin := '0';
      end;

      lTpWhere  := CbxTpWhere.ItemIndex;

      if CbxOrdem.ItemIndex >= 0 then
      begin
            lNmTabOrd := gNmTabCon;
            lNmAtrOrd := gStlColSql.Strings[CbxOrdem.ItemIndex];
      end;

      if CbxPesqui.ItemIndex >= 0 then
      begin
            lNmTabPes := gNmTabCon;
            lNmAtrPes := gStlColSql.Strings[CbxPesqui.ItemIndex];
      end;

      if CbxPesqu2.ItemIndex >= 0 then
      begin
            lNmTabPe2 := gNmTabCon;
            lNmAtrPe2 := gStlColSql.Strings[CbxPesqu2.ItemIndex];

            if EdtDsLocal2.Visible then
            begin
                  lDsLocal2 := EdtDsLocal2.Text;
            end
            else if EdtDtLocal2.Visible then
                 begin
                       lDsLocal2 := EdtDtLocal2.Text;
                 end
            else if CbxDsLocal2.Visible then
                 begin
                       lDsLocal2 := CbxDsLocal2.Values.Strings[CbxDsLocal2.ItemIndex];
                 end
            else if EdtNrLocal2.Visible then
                 begin
                       lDsLocal2 := EdtNrLocal2.Text;
                 end;
      end;

      lConexao := TSDmSisClient.Create(DsConsul.BrDicionario.SQLConnection.DBXConnection);

      try
          lConexao.SalvarConfigConsultaUsuario(DsConsul.BrDicionario.Credencial, lDsResult,
                                          DsConsul.BrQueryCode,
                                          DsConsul.BrDicionario.UserCode,
                                          lNmTabOrd, lNmAtrOrd, lNmTabPes, lNmAtrPes,
                                          lNmTabPe2, lNmAtrPe2, lDsLocal2, lTpMaiMin,
                                          lTpWhere);

          DsConsul.BrDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

procedure TFrmConsulta.CriarTabelaConfiguracaoColunasUsuario(pCdsAuxili : TClientDataSet);
begin
      pCdsAuxili.Close;
      pCdsAuxili.FieldDefs.Clear;

      pCdsAuxili.FieldDefs.Add('NrQueCon',   ftInteger, 0, False);
      pCdsAuxili.FieldDefs.Add('CdUsuari',   FtInteger, 0, False);
      pCdsAuxili.FieldDefs.Add('NrOrdAtr',   FtInteger, 0, False);
      pCdsAuxili.FieldDefs.Add('NmTabela',   FtString, 15, False);
      pCdsAuxili.FieldDefs.Add('NmAtribu',   FtString,  8, False);
      pCdsAuxili.FieldDefs.Add('VrLarCom',   FtInteger, 0, False);

      pCdsAuxili.CreateDataSet;
end;

procedure TFrmConsulta.AtualizarConfiguracaoColunasUsuario;
var lCdsAuxili : TClientDataSet;
    lNrColuna : Integer;

    lConexao   : TSDmSisClient;
    lDsResult  : String;
begin
      lConexao   := TSDmSisClient.Create(DsConsul.BrDicionario.SQLConnection.DBXConnection);
      lCdsAuxili := TClientDataSet.Create(Self);

      try
          CriarTabelaConfiguracaoColunasUsuario(lCdsAuxili);
        //=-=-=-=
        //= Inserindo a nova configuração
        //=-=-=-=-=-=-=
          for lNrColuna := 0 to DbgConsul.Columns.Count -1 do
          begin
                if (Trim(gNmTabCon) <> '') then
                begin
                      lCdsAuxili.Append;
                      lCdsAuxili.FieldByName('NrQueCon').AsInteger := DsConsul.BrQueryCode;
                      lCdsAuxili.FieldByName('CdUsuari').AsInteger :=
                                                         DsConsul.BrDicionario.UserCode;
                      lCdsAuxili.FieldByName('NrOrdAtr').AsInteger := lNrColuna;
                      lCdsAuxili.FieldByName('NmTabela').AsString  := gNmTabCon;
                      lCdsAuxili.FieldByName('NmAtribu').AsString  :=
                                             DbgConsul.Columns.Items[lNrColuna].FieldName;
                      lCdsAuxili.FieldByName('VrLarCom').AsInteger :=
                                             DbgConsul.Columns.Items[lNrColuna].Width;
                      lCdsAuxili.Post;
                end;
          end;

          lConexao.SalvarColunasConsultaUsuario(DsConsul.BrDicionario.Credencial,
                                           lDsResult, DsConsul.BrQueryCode,
                                           DsConsul.BrDicionario.UserCode,
                                           lCdsAuxili.Data, gNmForPai);

          DsConsul.BrDicionario.VerificarMensagemServidorAplicacao(lDsResult);
      finally
          FreeAndNil(lConexao);
      end;
end;

//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//=- Inicio das rotinas de exibição da consulta =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
procedure TFrmConsulta.MontarConsulta;
var lStlColUsr : TStrings;
    lStlColLar : TStrings;
    lNrColuna  : Integer;
    lNrColGri  : Integer;
begin
      lStlColUsr := TStringList.Create;
      lStlColLar := TStringList.Create;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // Seleciona colunas da instrução sql que o usuário tem permissão // de visualização
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      ColunasComPermissaoVisualizacao(UpperCase(
             DsConsul.BrDicionario.EncontrarInstrucaoSQLConsulta(DsConsul.BrQueryCode)));

      CbxPesqui.Items.Text := CbxOrdem.Items.Text;
      CbxPesqu2.Items.Text := CbxOrdem.Items.Text;
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // Carrega colunas configuradas pelo usuário na última consulta
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      ColunasVisualizadasPeloUsuario(lStlColUsr, lStlColLar);

      lNrColGri := 0;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // Criando na grid colunas que pode ser visualizadas pelo usuário
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      if lStlColUsr.Count > 0 then
      begin
            // adicionando na grid colunas configuradas pelo usuário anteriormente
            for lNrColuna := 0 to lStlColUsr.Count -1 do
            begin
                  if ColunaVisivelUsuario(lStlColUsr.Strings[lNrColuna]) then
                  begin
                        DbgConsul.Columns.Add;
                        DbgConsul.Columns.Items[lNrColGri].FieldName :=
                                          lStlColUsr.Strings[lNrColuna];
                        DbgConsul.Columns.Items[lNrColGri].Width :=
                                          StrToInt(lStlColLar.Strings[lNrColuna]);
                        DbgConsul.Columns.Items[lNrColGri].Title.Caption :=
                                          DisplayLabelColuna(lStlColUsr.Strings[lNrColuna]);
                        DbgConsul.Columns.Items[lNrColGri].Title.Font.Style := [fsBold];

                        Inc(lNrColGri);
                  end;
            end;
      end else
      begin
            CarregarAtributosPrimeiroAcesso;   // enio tirou

            // adicionando na grid todas colunas. Primeiro acesso a esta consulta
            for lNrColuna := 0 to gStlColSql.Count -1 do
            begin
                  DbgConsul.Columns.Add;
                  DbgConsul.Columns.Items[lNrColGri].FieldName :=
                                                            gStlColSql.Strings[lNrColuna];
                  DbgConsul.Columns.Items[lNrColGri].Title.Caption :=
                                    DisplayLabelColuna(gStlColSql.Strings[lNrColuna]);
                  DbgConsul.Columns.Items[lNrColGri].Title.Font.Style := [fsBold];
                  Inc(lNrColGri);
            end;

      end;

      if lNrColGri = 0 then
      begin
            MessageDlg('Mensagem da consulta número "' + IntToStr(DsConsul.BrQueryCode) +
                       '":' + #13#13 +
                       'Usuário sem permissões ou sem nenhum atributo selecionado para ' +
                       'visualização', MtError, [MbOk], 0);
      end else
      begin
            AtributosDeOrdenacaoPesquisa;

            if (DsConsul.BrDicionario.AbrirConsultaInicializacao(
                                                                DsConsul.BrQueryCode)) or
               (not PnlFiltro.Visible) then
            begin
                  AbrirQueryConsulta;
            end;
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // Destruindo listas de controle auxiliares
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      lStlColUsr.Destroy;
      lStlColLar.Destroy;
end;

procedure TFrmConsulta.CarregarAtributosPrimeiroAcesso;
var lDsColuna  : TBrvClientDataSet;
    lDsSql    : String;
    lNrColuna : Integer;
begin
      try
          lDsSql := 'Select NmTabcon From S016 Where NrQueCon = ' +
                                                          IntToStr(DsConsul.BrQueryCode);
          lDsColuna      := TBrvClientDataSet.Create(nil);
          lDsColuna.BrDicionario := DsConsul.BrDicionario;
          lDsColuna.Data         := DsConsul.BrDicionario.RetornaDadosSqlFixa(lDsSql);

          DsConsul.BrDicionario.AtributosDaTabela(
                                   lDsColuna.FieldByName('NmTabCon').AsString, gStlColSql);

          for lNrColuna := 0 to gStlColSql.Count -1 do
          begin
                gStlDisLab.Add(DsConsul.BrDicionario.AtributoDisplayLabel(
                                             lDsColuna.FieldByName('NmTabCon').AsString,
                                             gStlColSql.Strings[lNrColuna]));
          end
      finally
          lDsColuna.Close;
          FreeAndNil(lDsColuna);
      end;
end;

procedure TFrmConsulta.ColunasComPermissaoVisualizacao(pDsSql : String);
var lNmTabela : String;
    lNmApelid : String;
    lDsApelid : String;
    lNmColuna : String;
    lSnFrom   : Boolean;
    lDsSelect : String;
    lDsSQLIni : String;
begin
      lDsSqlIni := pDsSql;
      gStlColSql.Clear;
      lSnFrom   := False;

      while pDsSql <> '' do
      begin
            ProximaTabela(pDsSql, lNmTabela, lNmApelid, lSnFrom);

            lDsSelect := lDsSqlIni;
            lDsSelect := Copy(lDsSelect, 1, Pos('FROM', lDsSelect) - 1);
            lDsSelect := StringReplace(lDsSelect, #13, ' ', [rfReplaceAll]);
            lDsSelect := StringReplace(lDsSelect, #13, ' ', [rfReplaceAll]);
            lDsSelect := StringReplace(lDsSelect, #9,  ' ', [rfReplaceAll]);

            if lNmApelid = #0 then
            begin
                  lNmApelid := '';
            end;

            if lNmApelid <> '' then
            begin
                  lDsApelid := lNmApelid + '.';

                  while (lDsSelect <> '') and (Pos(lDsApelid, lDsSelect) > 0) do
                  begin
                        Delete(lDsSelect, 1, Pos(lDsApelid, lDsSelect) -1);
                        Delete(lDsSelect, 1, Length(lDsApelid));
                        lDsSelect := Trim(lDsSelect);

                        if Pos(' ', lDsSelect) > 0 then
                        begin
                              lNmColuna := Copy(lDsSelect, 1, Pos(' ', lDsSelect) -1);
                              Delete(lDsSelect, 1, Pos(' ', lDsSelect));
                        end else
                        begin
                              lNmColuna := Trim(lDsSelect);
                              lDsSelect := '';
                        end;

                        // retirando a virgula
                        lNmColuna := StringReplace(lNmColuna, ',', '', [rfReplaceAll]);

                        VerificarPermissaoVisualizacao(lNmTabela, lNmColuna);
                  end;
            end else
            begin
                  while (lDsSelect <> '') and (lNmTabela <> '') do
                  begin
                        lDsSelect := Trim(lDsSelect);

                        if Pos(' ', lDsSelect) > 0 then
                        begin
                              lNmColuna := Copy(lDsSelect, 1, Pos(' ', lDsSelect));
                              Delete(lDsSelect, 1, Pos(' ', lDsSelect));
                        end else
                        begin
                              lNmColuna := Trim(lDsSelect);
                              lDsSelect := '';
                        end;

                        if Pos(',', lNmColuna) > 0 then
                        begin
                              // retirando a virgula
                              Delete(lNmColuna, Pos(',', lNmColuna), 1);
                        end;

                        VerificarPermissaoVisualizacao(lNmTabela, lNmColuna);
                  end;
            end;
      end;
end;

procedure TFrmConsulta.VerificarPermissaoVisualizacao(pNmTabela: String; pNmColuna: String);
var lStlAtrTab : TStrings;
    lNrColuna  : Integer;
    lDsDisLab  : String;
    lDsDisFor  : String;
    lTpMascar  : String;
    lDsMascar  : String;
    lDsHelp    : String;
    lDsHint    : String;
    lTmAtribu  : Integer;
    lTpCampo   : String;
    lCdPermis  : Integer;
    lVrDomini  : String;
    lDsDomini  : String;
    lSnKey     : Boolean;
    lTpCompon : String;
begin
      pNmColuna := Trim(pNmColuna);
      if  pNmColuna = '*' then
      begin
            lStlAtrTab := TStringList.Create;
            DsConsul.BrDicionario.AtributosDaTabela(pNmTabela, lStlAtrTab);

            for lNrColuna := 0 to lStlAtrTab.Count -1 do
            begin
                  if DsConsul.BrDicionario.TemPermissaoVisualizacaoAtributo(
                                             pNmTabela, lStlAtrTab.Strings[lNrColuna]) then
                  begin
                        gStlColSql.Add(lStlAtrTab.Strings[lNrColuna]);
                        gStlTabSql.Add(pNmTabela);

                        DsConsul.BrDicionario.AtributoConfiguracao(
                                 pNmTabela, lStlAtrTab.Strings[lNrColuna],  lDsDisLab,
                                 lTpCampo,  lTpMascar, lDsMascar,
                                 lDsHelp,   lDsHint,   lTmAtribu,
                                 lCdPermis, lVrDomini, lDsDomini,
                                 lSnKey,    lDsDisFor);

                        if lDsDomini <> '' then
                        begin
                              lTpCompon := 'S';
                        end else
                        begin
                              lTpCompon := 'N';
                        end;

                        gStlChaCol.Add(lTpMascar);
                        gStlCamCol.Add(lTpCampo);
                        gStlDisLab.Add(lDsDisLab);
                        gStlVrDomi.Add(lVrDomini);
                        gStlDsDomi.Add(lDsDomini);
                        gStlCombo.Add(lTpCompon);
                        CbxOrdem.Items.Add(lDsDisLab);
                        CbxOrdem.Values.Add(lStlAtrTab.Strings[lNrColuna]);
                  end;
            end;

            lStlAtrTab.Destroy;
      end else
      begin
            if DsConsul.BrDicionario.TemPermissaoVisualizacaoAtributo(
                                                                pNmTabela, pNmColuna) then
            begin
                  gStlColSql.Add(pNmColuna);
                  gStlTabSql.Add(pNmTabela);

                  DsConsul.BrDicionario.AtributoConfiguracao(
                                 pNmTabela, pNmColuna, lDsDisLab,
                                 lTpCampo,  lTpMascar, lDsMascar,
                                 lDsHelp,   lDsHint,   lTmAtribu,
                                 lCdPermis, lVrDomini, lDsDomini,
                                 lSnKey,    lDsDisFor);

                  if lDsDomini <> '' then
                  begin
                        lTpCompon := 'S';
                  end else
                  begin
                        lTpCompon := 'N';
                  end;

                  gStlChaCol.Add(lTpMascar);
                  gStlCamCol.Add(lTpCampo);
                  gStlDisLab.Add(lDsDisLab);
                  gStlVrDomi.Add(lVrDomini);
                  gStlDsDomi.Add(lDsDomini);
                  gStlCombo.Add(lTpCompon);

                  CbxOrdem.Items.Add(lDsDisLab);
                  CbxOrdem.Values.Add(pNmColuna);
            end;
      end;
end;

procedure TFrmConsulta.ProximaTabela(var pDsSql    : String; var pNmTabela : String;
                                     var pNmApelid : String; var pSnFrom   : Boolean);
var lDsLexema : String;
begin
      pNmTabela := '';
      pNmApelid := '';

      while (pDsSql <> '') and (pNmTabela = '') do
      begin
            ProximaPalavra(lDsLexema, pDsSql, pSnFrom);

            if (pSnFrom) and (lDsLexema <> 'FROM') then
            begin
                  if Pos(',', lDsLexema) > 0 then
                  begin
                        Delete(lDsLexema, Pos(',', lDsLexema), 1);
                        pNmTabela := lDsLexema;
                  end else
                  begin
                        pNmTabela := lDsLexema;
                        ProximaPalavra(lDsLexema, pDsSql, pSnFrom);

                        if Pos(',', lDsLexema) > 0 then
                        begin
                              Delete(lDsLexema, Pos(',', lDsLexema), 1);
                              pNmApelid := lDsLexema;
                        end else
                        begin
                              if (lDsLexema <> 'WHERE') and (lDsLexema <> 'LEFT')  and
                                 (lDsLexema <> 'RIGTH') and (lDsLexema <> 'ORDER') and
                                 (lDsLexema <> 'GROUP') and (Pos('<%', lDsLexema) = 0) then
                              begin
                                    pNmApelid := lDsLexema;
                              end;
                        end;
                  end;
            end
            else if (lDsLexema = 'LEFT') or (lDsLexema = 'RIGHT') then
                 begin
                       repeat
                              ProximaPalavra(lDsLexema, pDsSql, pSnFrom);
                       until (pDsSql = '') or (lDsLexema = 'JOIN');

                       ProximaPalavra(lDsLexema, pDsSql, pSnFrom);
                       pNmTabela := lDsLexema;

                       ProximaPalavra(lDsLexema, pDsSql, pSnFrom);

                       if lDsLexema <> '0N' then
                       begin
                             pNmApelid := lDsLexema;
                       end;
                 end;
      end;

      pNmTabela := Trim(pNmTabela);
      pNmApelid := Trim(pNmApelid);
end;

procedure TFrmConsulta.ProximaPalavra(var pDsLexema : String; var pDsSql : String;
                                      var pSnFrom   : Boolean);
begin
      pDsLexema := '';

      while (pDsSql <> '') and (pDsSql[1] in [' ', #10, #13, #9, ',']) do
      begin
            Delete(pDsSql, 1, 1);
      end;

      while (pDsSql <> '') and (not (pDsSql[1] in [' ', #10, #13, #9])) do
      begin
            pDsLexema := pDsLexema + pDsSql[1];
            Delete(pDsSql, 1, 1);
      end;

      if (pDsLexema = 'SELECT') or (pDsLexema = 'LEFT')   or
         (pDsLexema = 'RIGHT')  or (pDsLexema = 'WHERE')  or
         (pDsLexema = 'ORDER')  or (pDsLexema = 'GROUP')  then
      begin
            pSnFrom   := False;
      end else
      begin
            if pDsLexema = 'FROM' then
            begin
                  pSnFrom := True;
            end;
      end;
end;

procedure TFrmConsulta.ColunasVisualizadasPeloUsuario(var pStlColuna : TStrings;
                                                      var pStlLagCom : TStrings);
var lDsColuna  : TBrvClientDataSet;
    lDsSql    : String;
begin
      try
          lDsSql := 'Select * From S028 Where CdUsuari = ' +
                    IntToStr(DsConsul.BrDicionario.UserCode) + ' and NrQueCon = ' +
                    IntToStr(DsConsul.BrQueryCode) + ' Order by NrOrdAtr';
          lDsColuna              := TBrvClientDataSet.Create(nil);
          lDsColuna.BrDicionario := DsConsul.BrDicionario;
          lDsColuna.Data         := DsConsul.BrDicionario.RetornaDadosSqlFixa(lDsSql);

          pStlColuna.Clear;
          pStlLagCom.Clear;

          while not lDsColuna.Eof do
          begin
                pStlColuna.Add(lDsColuna.FieldByName('NmAtribu').AsString);
                pStlLagCom.Add(lDsColuna.FieldByName('VrLarCom').AsString);

                lDsColuna.Next;
          end;
      finally
          lDsColuna.Close;
          FreeAndNil(lDsColuna);
      end;
end;

function  TFrmConsulta.ColunaVisivelUsuario(pNmColuna : String) : Boolean;
var lNrColuna : Integer;
begin
      Result   := False;
      lNrColuna := 0;

      while (lNrColuna < gStlColSql.Count) and (not Result) do
      begin
            if (UpperCase(pNmColuna) = UpperCase(gStlColSql.Strings[lNrColuna])) then
            begin
                  Result := True;
            end;

            inc(lNrColuna);
      end;
end;

procedure TFrmConsulta.AtributosDeOrdenacaoPesquisa;
var lDsOrdem   : TBrvClientDataSet;
    lNrColuna  : Integer;
    lNrValor   : Integer;
    lSnOrdem   : Boolean;
    lSnPesqui  : Boolean;
    lSnPesqu2  : Boolean;

    lNmAtrPes  : String;
    lNmAtrPe2  : String;
    lNmAtrOrd  : String;
    lDsLocal2  : String;

    lDsSql    : String;
begin
      try
          lNmAtrPes := '';
          lNmAtrPe2 := '';
          lNmAtrOrd := '';

          lDsSql := 'Select * From S027 Where CdUsuari = ' +
                    IntToStr(DsConsul.BrDicionario.UserCode) + ' and NrQueCon = ' +
                    IntToStr(DsConsul.BrQueryCode);
          lDsOrdem      := TBrvClientDataSet.Create(nil);
          lDsOrdem.BrDicionario := DsConsul.BrDicionario;
          lDsOrdem.Data := DsConsul.BrDicionario.RetornaDadosSqlFixa(lDsSql);

          if not lDsOrdem.IsEmpty then
          begin
                lNmAtrPes := lDsOrdem.FieldByName('NmAtrPes').AsString;
                lNmAtrPe2 := lDsOrdem.FieldByName('NmAtrPe2').AsString;
                lNmAtrOrd := lDsOrdem.FieldByName('NmAtrOrd').AsString;
                lDsLocal2 := lDsOrdem.FieldByName('DsLocal2').AsString;

                if lDsOrdem.FieldByName('TpMaiMin').AsInteger = 0 then
                begin
                      CbxMaiMin.Checked := False;
                end else
                begin
                      CbxMaiMin.Checked := True;
                end;

                CbxTpWhere.ItemIndex := lDsOrdem.FieldByName('TpWhere').AsInteger;
          end else
          begin
                gSnAtuCon            := True;
                CbxMaiMin.Checked    := True;
                CbxTpWhere.ItemIndex := 0;
          end;

          lDsOrdem.Close;

          lDsSql := 'Select NmAtrPes, NmAtrOrd, NmTabCon From S016 Where NrQueCon = ' +
                    IntToStr(DsConsul.BrQueryCode);
          lDsOrdem.Data := DsConsul.BrDicionario.RetornaDadosSqlFixa(lDsSql);
          gNmTabCon := lDsOrdem.FieldByName('NmTabCon').AsString;

          if  (lNmAtrPes = '') or (lNmAtrPe2 = '') or (lNmAtrOrd = '') then
          begin
                if lNmAtrPes = '' then
                begin
                      lNmAtrPes  := lDsOrdem.FieldByName('NmAtrPes').AsString;
                      lNmAtrPe2  := lDsOrdem.FieldByName('NmAtrPes').AsString;
                end;

                if lNmAtrOrd = '' then
                begin
                      lNmAtrOrd  := lDsOrdem.FieldByName('NmAtrOrd').AsString;
                end;
          end;

          lDsOrdem.Close;

          lSnOrdem   := False;
          lSnPesqui  := False;
          lSnPesqu2  := False;
          lNrColuna  := 0;

          while (lNrColuna < gStlColSql.Count) and
                ((not lSnOrdem) or (not lSnPesqui) or (not lSnPesqu2)) do
          begin
                if  gStlColSql.Strings[lNrColuna] = lNmAtrOrd then
                begin
                      CbxOrdem.ItemIndex := lNrColuna;
                      lSnOrdem            := True;
                end;

                if  gStlColSql.Strings[lNrColuna] = lNmAtrPes then
                begin
                      CbxPesqui.ItemIndex := lNrColuna;

                      if CbxPesqui.ItemIndex >= 0 then
                      begin
                            CbxPesquiChange(nil);
                            lSnPesqui            := True;
                      end;
                end;

                if  gStlColSql.Strings[lNrColuna] = lNmAtrPe2 then
                begin
                      CbxPesqu2.ItemIndex := lNrColuna;

                      if CbxPesqu2.ItemIndex >= 0 then
                      begin
                            CbxPesqu2Change(nil);
                      end;

                      if EdtNrLocal2.Visible then
                      begin
                            EdtNrLocal2.Text := lDsLocal2;
                      end else
                      if EdtDtLocal2.Visible then
                      begin
                            EdtDtLocal2.Text := lDsLocal2;
                      end else
                      if EdtDsLocal2.Visible then
                      begin
                            EdtDsLocal2.Text := lDsLocal2;
                      end else
                      if CbxDsLocal2.Visible then
                      begin
                            lNrValor := 0;
                            CbxDsLocal2.ItemIndex := -1;

                            while (lNrValor < CbxDsLocal2.Values.Count) and
                                  (CbxDsLocal2.ItemIndex < 0) do
                            begin
                                  if CbxDsLocal2.Values.Strings[lNrValor] = lDsLocal2 then
                                  begin
                                        CbxDsLocal2.ItemIndex := lNrValor;
                                  end;

                                  inc(lNrValor);
                            end;
                      end;

                      lSnPesqu2            := True;
                end;

                Inc(lNrColuna);
          end;
      finally
          FreeAndNil(lDsOrdem);
      end;
end;

procedure TFrmConsulta.AbrirQueryConsulta;
var lCdConsul : Integer;
    lDsSql    : String;
    lDsWhere  : String;
    lDsWhere2 : String;
    lDsAlias  : String;
    lDsParent : String;
    lDsSinal  : String;

    lDSParam  : Integer;
begin
      lCdConsul := DsConsul.BrQueryCode;
      lDsSql  := Trim(UpperCase(
                DsConsul.BrDicionario.EncontrarInstrucaoSQLConsulta(lCdConsul)));
      lDsParent := #39;

      case CbxTpWhere.ItemIndex of
           0: lDsSinal := ' >= ' + #39;
           1: lDsSinal := ' <= ' + #39;
           2: lDsSinal := ' = '  + #39;
           3: lDsSinal := ' > '  + #39;
           4: lDsSinal := ' < '  + #39;
           5: begin
                    lDsSinal  := ' LIKE ('  + #39 + '%';
                    lDsParent := '%' + #39 + ')';
              end;
           6: begin
                    lDsSinal  := ' LIKE ('  + #39;
                    lDsParent := '%' + #39 + ')';
              end;
      end;

      if CbxPesqui.ItemIndex >= 0 then
      begin
            lDsAlias := AliasColuna(lDsSql, gStlColSql.Strings[CbxPesqui.ItemIndex]);

            if EdtDsLocali.Visible then
            begin
                  if EdtDsLocali.Text = '' then
                  begin
                        EdtDsLocali.Text := ' ';
                  end;

                  if CbxMaiMin.Checked then
                  begin
                        lDsWhere := ' Upper(' + lDsAlias +
                                   gStlColSql.Strings[CbxPesqui.ItemIndex] +
                                   ') ' + lDsSinal + UpperCase(EdtDsLocali.Text) +
                                   lDsParent;
                  end else
                  begin
                        lDsWhere := lDsAlias +
                                   gStlColSql.Strings[CbxPesqui.ItemIndex] + lDsSinal  +
                                   EdtDsLocali.Text + lDsParent;
                  end;
            end else
            if EdtNrLocali.Visible then
            begin
                  if EdtNrLocali.Text = '' then
                  begin
                        EdtNrLocali.BrAsInteger := 0;
                  end else
                  begin
                        lDsWhere := lDsAlias +
                                   gStlColSql.Strings[CbxPesqui.ItemIndex] +
                                   lDsSinal + EdtNrLocali.Text + lDsParent;
                  end;
            end else
            if CbxDsLocali.Visible then
            begin
                  if CbxDsLocali.ItemIndex > 0 then
                  begin
                        lDsWhere := lDsAlias + gStlColSql.Strings[CbxPesqui.ItemIndex] +
                                    lDsSinal + CbxDsLocali.Values.Strings[
                                                        CbxDsLocali.ItemIndex] + lDsParent;
                  end;
            end else
            begin
                  if EdtDtLocali.BrAsDataSQL = '' then
                  begin
                        EdtDtLocali.BrAsDate := Date;
                  end;

                  lDsWhere := lDsAlias + gStlColSql.Strings[CbxPesqui.ItemIndex] +
                              lDsSinal + EdtDtLocali.BrAsDataSQL + lDsParent;
            end;
      end;

      if CbxPesqu2.ItemIndex >= 0 then
      begin
            lDsAlias := AliasColuna(lDsSql, gStlColSql.Strings[CbxPesqu2.ItemIndex]);

            if (EdtDsLocal2.Visible) and (EdtDsLocal2.Text <> '') then
            begin
                  if CbxMaiMin.Checked then
                  begin
                        lDsWhere2 := ' Upper(' + lDsAlias +
                                    gStlColSql.Strings[CbxPesqu2.ItemIndex] +
                                    ') = Upper(' + #39 + EdtDsLocal2.Text  + #39 + ')';
                  end else
                  begin
                        lDsWhere2 := lDsAlias +
                                   gStlColSql.Strings[CbxPesqu2.ItemIndex] + ' = ' + #39 +
                                   EdtDsLocal2.Text  + #39;
                  end;
            end else
            if (EdtNrLocal2.Visible) and (EdtNrLocal2.BrAsInteger <> 0) then
            begin
                  if  EdtNrLocal2.Text = '' then
                  begin
                        EdtNrLocal2.BrAsInteger := 0;
                  end else
                  begin
                        lDsWhere2 := lDsAlias +
                                    gStlColSql.Strings[CbxPesqu2.ItemIndex] +
                                    ' = ' + #39 + EdtNrLocal2.Text  + #39;
                  end;
            end else
            if CbxDsLocal2.Visible then
            begin
                  if CbxDsLocal2.ItemIndex > 0 then
                  begin
                        lDsWhere2 := lDsAlias +
                                    gStlColSql.Strings[CbxPesqu2.ItemIndex] +
                                    ' = '  + #39 + CbxDsLocal2.Values.Strings[
                                    CbxDsLocal2.ItemIndex] + #39;
                  end;
            end else
            begin
                  if (not EdtDtLocal2.BrDataVazia) and (EdtDtLocal2.BrDataValida) then
                  begin
                        lDsWhere2 := lDsAlias + gStlColSql.Strings[CbxPesqu2.ItemIndex] +
                                     ' = '  + #39 + EdtDtLocal2.BrAsDataSQL + #39;
                  end;
            end;
      end;

      if Trim(gDsComWhe) <> '' then
      begin
            if Pos('WHERE', lDsSql) > 0 then
            begin
                  lDsSql := lDsSql + ' and ' + gDsComWhe;
            end else
            begin
                  lDsSql := lDsSql + ' WHERE ' + gDsComWhe;
            end;
      end else
      begin
            if (lDsWhere <> '') or (lDsWhere2 <> '') then
            begin
                  if lDsWhere <> '' then
                  begin
                        if Pos('WHERE', lDsSql) > 0 then
                        begin
                              lDsSql := lDsSql + ' and ' + lDsWhere;
                        end else
                        begin
                              lDsSql := lDsSql + ' WHERE ' + lDsWhere;
                        end;
                  end;

                  if lDsWhere2 <> '' then
                  begin
                        if Pos('WHERE', lDsSql) > 0 then
                        begin
                              lDsSql := lDsSql + ' and ' + lDsWhere2;
                        end else
                        begin
                              lDsSql := lDsSql + ' WHERE ' + lDsWhere2;
                        end;
                  end;
            end;
      end;

      SubstituirParametros(lDsSql);

      CsConsul.Close;
      CsConsul.Tag          := DsConsul.BrQueryCode;
      CsConsul.Data         := DsConsul.BrDicionario.RetornaDadosSqlFixa(lDsSql);

      if CbxOrdem.ItemIndex >= 0 then
      begin
            OrdenarConsulta;
      end;

      DsConsul.BrQueryCode   := lCdConsul;
end;

procedure TFrmConsulta.SubstituirParametros(var pDsSql : String);
var lNrLinha  : Integer;
    lDsParam  : String;
    lDsConteu : String;
    lNrPosPon : Integer;
begin
      if Pos('%>', pDsSql) <> 0 then
      begin
            pDsSql := StringReplace(pDsSql, '<%EMPRESAS%>' ,
                                   DsConsul.BrDicionario.CorpCommaCodes, [rfReplaceAll]);

            for lNrLinha := 0 to DsConsul.BrParams.Count -1 do
            begin
                  lNrPosPon := Pos(';', DsConsul.BrParams.Strings[lNrLinha]);
                  lDsParam  := Copy(DsConsul.BrParams.Strings[lNrLinha], 1 , lNrPosPon -1);
                  lDsConteu := Copy(DsConsul.BrParams.Strings[lNrLinha], lNrPosPon + 1,
                                            Length(DsConsul.BrParams.Strings[lNrLinha]));

                  pDsSql := StringReplace(pDsSql, lDsParam, lDsConteu,
                                                             [rfReplaceAll,rfIgnoreCase]);
            end;
      end;
end;

procedure TFrmConsulta.OrdenarConsulta;
var lColumn  : TColumn;
    lNrIndex : Integer;
begin
      if CbxOrdem.ItemIndex >= 0 then
      begin
            lColumn := ColunaDeOrdenacao(CbxOrdem.Values.Strings[CbxOrdem.ItemIndex]);

            if lColumn = nil then
            begin
                  lNrIndex := 0;
            end else
            begin
                  lNrIndex := lColumn.Index;
            end;
            DbgConsul.OrdenarClientDataSet(CsConsul,
                                           CbxOrdem.Values.Strings[CbxOrdem.ItemIndex],
                                           True, lNrIndex);
      end;
end;

function TFrmConsulta.ColunaDeOrdenacao(pNmField : String) : TColumn;
var lNrColumn : Integer;
begin
      Result    := nil;
      lNrColumn := 0;

      while (lNrColumn < DbgConsul.Columns.Count) and (Result = nil) do
      begin
            if DbgConsul.Columns[lNrColumn].FieldName = pNmField then
            begin
                  Result := DbgConsul.Columns[lNrColumn];
            end;

            inc(lNrColumn);
      end;
end;

function TFrmConsulta.AliasColuna(pDsSelect : String; pNmColuna : String) : String;
var lNrAlias  : Integer;
    lDsSqlTab : String;
    lDsSqlIni : String;
    lNmTabela : String;
    lNmApelid : String;
    lSnFrom   : Boolean;
begin
      Result := '';

      lDsSqlIni := pDsSelect;

      Delete(pDsSelect, Pos('SELECT', pDsSelect), 6);
      Delete(pDsSelect, Pos('FROM', pDsSelect), Length(pDsSelect));

      pDsSelect := StringReplace(pDsSelect, #13, ' ', [rfReplaceAll]);
      pDsSelect := StringReplace(pDsSelect, #9,  ' ', [rfReplaceAll]);
      pDsSelect := StringReplace(pDsSelect, #10, ' ', [rfReplaceAll]);
      pDsSelect := Trim(pDsSelect);

      while (pDsSelect <> '') and (Result = '') do
      begin
            if pDsSelect[Pos(pNmColuna, pDsSelect) - 1] = '.' then
            begin
                  lNrAlias := Pos(pNmColuna, pDsSelect) -1;

                  while (lNrAlias > 0) and (pDsSelect[lNrAlias] <> ' ') and
                                           (pDsSelect[lNrAlias] <> '(') and
                                           (pDsSelect[lNrAlias] <> ',') do
                  begin
                        Result := pDsSelect[lNrAlias] + Result;
                        Dec(lNrAlias);
                  end;
                  Delete(pDsSelect, 1, Pos(pNmColuna, pDsSelect));
            end else
            begin
                  if pDsSelect[Pos('*', pDsSelect) - 1] = '.' then
                  begin
                        lNrAlias := Pos('*', pDsSelect) -1;

                        while (lNrAlias > 0) and
                              (pDsSelect[lNrAlias] <> ' ') and
                              (pDsSelect[lNrAlias] <> ',') do
                        begin
                              Result := pDsSelect[lNrAlias] + Result;
                              Dec(lNrAlias);
                        end;

                        Delete(pDsSelect, 1, Pos('*', pDsSelect));
                  end else
                  begin
                        pDsSelect := '';
                  end;
            end;

            pDsSelect := Trim(pDsSelect);

            if Result <> '' then
            begin
                  Delete(Result, pos('.', Result), 1);
                  lSnFrom   := False;
                  lDsSqlTab := lDsSqlIni;

                  while lDsSqlTab <> '' do
                  begin
                        ProximaTabela(lDsSqlTab, lNmTabela, lNmApelid, lSnFrom);

                        if lNmApelid = Result then
                        begin
                              if DsConsul.BrDicionario.AtributoExisteTabela(
                                                                lNmTabela, pNmColuna) then
                              begin
                                    lDsSqlTab := '';
                                    Result    := Result + '.';
                              end else
                              begin
                                    Result := '';
                              end;
                        end;
                  end;
            end;
      end;
end;

//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//=- Fim das rotinas de exibição da consulta -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//=- Inicio das rotinas de tratamento das opções de ordenação e pesquisa -=-=-=
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
procedure TFrmConsulta.DbgConsulTitleClick(Column: TColumn);
var lNrColuna : Integer;
    lSnAchou  : Boolean;
begin
      lNrColuna := 0;
      lSnAchou  := False;

      if CsConsul.Active then
      begin
            gSnAtuCon := True;
            while (lNrColuna < gStlColSql.Count) and (not lSnAchou) do
            begin
                  if Column.FieldName = gStlColSql.Strings[lNrColuna] then
                  begin
                        lSnAchou := True;
                        CbxOrdem.ItemIndex := lNrColuna;
                  end;

                  inc(lNrColuna);
            end;
      end;
end;

procedure TFrmConsulta.CbxOrdemChange(Sender: TObject);
begin
      gSnAtuCon := True;
      OrdenarConsulta;
end;

procedure TFrmConsulta.CbxPesquiChange(Sender: TObject);
begin
      gSnAtuCon := True;

      EdtDsLocali.Visible := False;
      EdtNrLocali.Visible := False;
      EdtDtLocali.Visible := False;
      CbxDsLocali.Visible := False;
      if Pos(gStlCamCol.Strings[CbxPesqui.ItemIndex], 'IF') > 0 then // integer Float
      begin
            if Pos(gStlCamCol.Strings[CbxPesqui.ItemIndex], 'F') > 0 then // Float
            begin
                  EdtNrLocali.BrCasasDecimais := 4;
            end else
            begin
                  EdtNrLocali.BrCasasDecimais := 0;
            end;

            EdtNrLocali.Visible   := True;
      end
      else if gStlCamCol.Strings[CbxPesqui.ItemIndex] = 'D' then //Date
           begin
                 EdtDtLocali.Visible := True;
           end
      else begin // Campo String
                 if gStlCombo.Strings[CbxPesqui.ItemIndex] = 'N' then
                 begin
                       if gStlChaCol.Strings[CbxPesqui.ItemIndex] = 'M' then
                       begin
                             EdtDsLocali.CharCase := ecUpperCase;
                       end
                       else if gStlChaCol.Strings[CbxPesqui.ItemIndex] = 'm' then
                            begin
                                  EdtDsLocali.CharCase := ecLowerCase;
                            end
                       else EdtDsLocali.CharCase := ecNormal;

                       EdtDsLocali.Visible := True;
                 end else
                 begin
                       CbxDsLocali.Items.Clear;
                       CbxDsLocali.Items.Text  := '' + #13 +
                                   gStlDsDomi.Strings[CbxPesqui.ItemIndex];
                       CbxDsLocali.Values.Text := '' + #13 +
                                   gStlVrDomi.Strings[CbxPesqui.ItemIndex];
                       CbxDsLocali.ItemIndex   := 0;
                       CbxDsLocali.Visible     := True;
                 end;
           end;
end;

procedure TFrmConsulta.CbxPesqu2Change(Sender: TObject);
begin
      gSnAtuCon := True;

      EdtDsLocal2.Visible := False;
      EdtNrLocal2.Visible := False;
      EdtDtLocal2.Visible := False;
      CbxDsLocal2.Visible := False; 

      if Pos(gStlCamCol.Strings[CbxPesqu2.ItemIndex], 'IF') > 0 then // Integer Float
      begin
            if Pos(gStlCamCol.Strings[CbxPesqu2.ItemIndex], 'F') > 0 then // Float
            begin
                  EdtNrLocal2.BrCasasDecimais := 4;
            end else
            begin
                  EdtNrLocal2.BrCasasDecimais := 0;
            end;

            EdtNrLocal2.Visible   := True;
      end
      else if gStlCamCol.Strings[CbxPesqu2.ItemIndex] = 'D' then // Date
           begin
                 EdtDtLocal2.Visible := True;
           end
      else begin
                 if gStlCombo.Strings[CbxPesqu2.ItemIndex] = 'N' then
                 begin
                       if gStlChaCol.Strings[CbxPesqu2.ItemIndex] = 'M' then
                       begin
                             EdtDsLocal2.CharCase := ecUpperCase;
                       end
                       else if gStlChaCol.Strings[CbxPesqu2.ItemIndex] = 'm' then
                            begin
                                  EdtDsLocal2.CharCase := ecLowerCase;
                            end
                       else EdtDsLocal2.CharCase := ecNormal;

                       EdtDsLocal2.Visible := True;
                 end else
                 begin
                       CbxDsLocal2.Items.Clear;
                       CbxDsLocal2.Items.Text  := '' + #13 +
                                          gStlDsDomi.Strings[CbxPesqu2.ItemIndex];
                       CbxDsLocal2.Values.Text := '' + #13 +
                                          gStlVrDomi.Strings[CbxPesqu2.ItemIndex];
                       CbxDsLocal2.ItemIndex   := 0;
                       CbxDsLocal2.Visible     := True;
                 end;
           end;
end;

procedure TFrmConsulta.EdtDsLocal2Change(Sender: TObject);
begin
      gSnAtuCon := True;
end;
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//=- Fim    das rotinas de tratamento das opções de ordenação e pesquisa -=-=-=
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//=- Inicio das rotinas de tratamento dos componentes de edição da pesquisa =-=
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
procedure TFrmConsulta.CbxDsLocal2Change(Sender: TObject);
begin
      AbrirQueryConsulta;
      gSnAtuCon := True;
end;

procedure TFrmConsulta.CbxDsLocaliChange(Sender: TObject);
begin
      AbrirQueryConsulta;
end;

procedure TFrmConsulta.EdtDsLocaliKeyPress(Sender: TObject; var Key: Char);
begin
      case key of
           #13: begin
                      AbrirQueryConsulta;
                      DbgConsul.SetFocus;
                end;
      end;
end;
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//=- Fim    das rotinas de tratamento dos componentes de edição ds pesquisa =-=
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//=- Inicio das rotinas mostrar/ocultar colunas da consulta =-=-=-=-=-=-=-=-=-=
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
procedure TFrmConsulta.BtnColunaClick(Sender: TObject);
var lNrColuna  : Integer;
    lNrColGri  : Integer;
    lStlColuna : TStrings;
    lStlLargur : TStrings;
begin
      lStlColuna   := TStringList.Create;
      lStlLargur   := TStringList.Create;
      FrmConCol    := TFrmConCol.Create(Self);

   // =-=-= Adicionando colunas visíveis na Grid

      for lNrColuna := 0 to DbgConsul.Columns.Count -1 do
      begin
            FrmConCol.CbxColuna.Items.Add(DisplayLabelColuna(
                                  DbgConsul.Columns.Items[lNrColuna].FieldName));
            FrmConCol.CbxColuna.Checked[lNrColuna] := True;
            lStlColuna.Add(DbgConsul.Columns.Items[lNrColuna].FieldName);
            lStlLargur.Add(IntToStr(DbgConsul.Columns.Items[lNrColuna].Width));
      end;

   // =-=-= Adicionando restante das colunas não visíveis na Grid

      for lNrColuna := 0 to gStlColSql.Count -1 do
      begin
            if not ColunaVisivelGrid(gStlColSql.Strings[lNrColuna]) then
            begin
                  FrmConCol.CbxColuna.Items.Add(gStlDisLab.Strings[lNrColuna]);
                  lStlColuna.Add(gStlColSql.Strings[lNrColuna]);
                  lStlLargur.Add('0');
            end;
      end;

      if FrmConCol.ShowModal = MrOk then
      begin
            DbgConsul.Columns.Clear;
            lNrColGri := 0;

            for lNrColuna := 0 to FrmConCol.CbxColuna.Items.Count - 1 do
            begin
                  if FrmConCol.CbxColuna.Checked[lNrColuna] then
                  begin
                        DbgConsul.Columns.Add;
                        DbgConsul.Columns.Items[lNrColGri].FieldName :=
                                  lStlColuna.Strings[lNrColuna];
                        DbgConsul.Columns.Items[lNrColGri].Title.Caption :=
                                  DisplayLabelColuna(lStlColuna.Strings[lNrColuna]);
                        DbgConsul.Columns.Items[lNrColGri].Title.Font.Style := [fsBold];

                        if StrToInt(lStlLargur.Strings[lNrColuna]) > 0 then
                        begin
                              DbgConsul.Columns.Items[lNrColGri].Width :=
                                          StrToInt(lStlLargur.Strings[lNrColuna]);
                        end;
                        inc(lNrColGri);
                  end;
            end;
      end;

      FrmConCol.Destroy;
      lStlColuna.Destroy;
      lStlLargur.Destroy;
end;

function TFrmConsulta.DisplayLabelColuna(pNmColuna : String) : String;
var lNrColuna : Integer;
begin
      Result   := '';
      lNrColuna := 0;

      while (lNrColuna < gStlColSql.Count) and (Result = '') do
      begin
            if gStlColSql.Strings[lNrColuna] = pNmColuna then
            begin
                  Result := gStlDisLab.Strings[lNrColuna];
            end;

            inc(lNrColuna);
      end;
end;

function TFrmConsulta.ColunaVisivelGrid(pNmColuna : String) : Boolean;
var lNrColuna : Integer;
begin
      Result   := False;
      lNrColuna := 0;

      while (lNrColuna < DbgConsul.Columns.Count) and (not Result) do
      begin
            if DbgConsul.Columns.Items[lNrColuna].FieldName = pNmColuna then
            begin
                  Result := True;
            end;

            inc(lNrColuna);
      end;
end;

//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//=-=-= Fim das rotinas mostrar/ocultar colunas da consulta =-=-=-=-=-=-=-=-=-=
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//=- Inicio das rotinas para retornar o valor da consulta =-=-=-=-=-=-=-=-=-=-=
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
procedure TFrmConsulta.DbgConsulKeyPress(Sender: TObject; var Key: Char);
begin
      if key = #13 then
      begin
             CarregarRetornoConsulta;
      end;
end;

procedure TFrmConsulta.DbgConsulDblClick(Sender: TObject);
begin
      CarregarRetornoConsulta;
end;

procedure TFrmConsulta.CarregarRetornoConsulta;
begin
      ModalResult := MrOk;
end;
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
//=- Final  das rotinas para retornar o valor da consulta =-=-=-=-=-=-=-=-=-=-=
//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
procedure TFrmConsulta.btnFecharClick(Sender: TObject);
begin
      Close;
end;

procedure TFrmConsulta.CbxTpWhereChange(Sender: TObject);
begin
      gSnAtuCon := True;
      LblTpWhere.Caption := CbxTpWhere.Text;
end;

procedure TFrmConsulta.CbxMaiMinClick(Sender: TObject);
begin
      gSnAtuCon := True;
end;

end.

