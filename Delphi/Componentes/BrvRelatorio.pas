unit BrvRelatorio;

interface

uses Windows, Messages, SysUtils, Classes, Graphics, Controls, Dialogs, QuickRpt, Qrctrls,
     Db, Printers, BrvRelAsc, ExtCtrls, StdCtrls, Buttons, BrvProgressBar, Forms,
     BrvDicionario, BrvConnection, BrvString;

  type
  TQRLabel2  = array[1..20] of TQRLabel;
  TQRDBText2 = array[1..20] of TQRDBText;

  TRelatorio = class(TComponent)
  private
    { Private declarations }
    FRelAsc    : TBrvRelAsc;
    gBrvString : TBrvString;
    procedure GerarRelatorioGrafico(const DtsAtual : TDataSet;
                                          DsEmpres : String; DsTitulo : String);

    procedure GerarRelatorioAsc(const DtsAtual : TDataSet; CdUsuari : Integer;
                                      Dsempres : String;   NmFormul : String;
                                      DsTitulo : String;   FrmOrige : TForm);

    procedure DefinirCabecalhoCampoLinha(const DtsAtual : TDataSet;
                                        var StlDsComCab : TStrings;
                                        var StlDsCamLin : TStrings; QtColuna : Byte);

    procedure ProcessarColunaCabecalho(NrColuna : Integer;
                                var StlDsComCab : TStrings; var StlDsCamLin : TStrings;
                                var DsCabeca    : String;   var DsColuna    : String;
                                var TmLinha     : Byte;         QtColuna    : byte);
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
    procedure   Execute(const DtsAtual : TDataSet;
                              BrDicion : TBrvDicionario; DsCaptio : String;
                              DsSeleca : String;         DsOrdena : String;
                              FrmOrige : TForm;          NmTabela : String='');
  published
    { Published declarations }
  end;

procedure Register;

implementation

uses BrvRelatorioParamForm, // parâmetros para geração do relatório
     BrvRelQuickForm,       // Relatório gerado: Quick Report
     URELTR0010;

procedure Register;
begin
      RegisterComponents('Bravo Relatorio', [TRelatorio]);
end;

constructor TRelatorio.Create(AOwner: TComponent);
begin
      inherited Create(AOwner);
end;

destructor TRelatorio.Destroy;
begin
      inherited  destroy;
end;

procedure TRelatorio.Execute(const DtsAtual : TDataSet;
                             BrDicion  : TBrvDicionario;       DsCaptio : String;
                             DsSeleca  : String;               DsOrdena : String;
                             FrmOrige  : TForm;                NmTabela : String='');
begin
      RelParame             := TRelParame.Create(Self);
      RelParame.StlNmCampos := TStringList.Create;
      RelParame.StlTmCampos := TStringList.Create;
      RelParame.DtsAtual    := DtsAtual;
      RelParame.CdUsuari    := BrDicion.UserCode;

      RelParame.CcConfRelUsr.BrDicionario    := BrDicion;
      RelParame.CcConfColRelUsr.BrDicionario := BrDicion;

      if DsSeleca = '' then
      begin
            DsSeleca := 'Todos os registros';
      end;

      RelParame.MemObserv.Lines.Add('Usuário : ' + BrDicion.UserName);
      //RelParame.MemObserv.Lines.Add('Seleção : ' + DsSeleca);
      RelParame.MemObserv.Lines.Add('Ordem  : '  + DsOrdena);


      if NmTabela = '' then
      begin
            NmTabela := DsCaptio;
      end;

      RelParame.CarregarColunasCheckListBox(TBrvConnection(BrDicion.SqlConnection),
                                            BrDicion, NmTabela);

//      RelParame.CarregarColunasCheckListBox(DsCaptio);

      if  RelParame.ShowModal = MrOk then
      begin
            if  RelParame.RgbImpres.ItemIndex = 0 then
            begin
                  GerarRelatorioGrafico(DtsAtual, BrDicion.CorpName, DsCaptio);
            end else
            begin
                  GerarRelatorioAsc(DtsAtual, BrDicion.UserCode, BrDicion.CorpName,
                                    DsCaptio, RelParame.EdtDsTitulo.Text, FrmOrige);
            end;
      end;

      RelParame.StlNmCampos.Destroy;
      RelParame.StlTmCampos.Destroy;
      RelParame.Destroy;
end;

procedure TRelatorio.GerarRelatorioGrafico(const DtsAtual: TDataSet;
                                                 DsEmpres : String;
                                                 DsTitulo : String);
var i            : Integer;
    DsCompTitulo : TQRLabel2;
    DsCabec      : TQRLabel2;
    DsCabecAux   : TQRLabel;
    DsDetalhe    : TQRDBText2;
    DsDetalheAux : TQRDBText;
    NoLeft       : Integer;
    NoTop        : Integer;
    NoTam        : Integer;
begin
      RelQuick := TRelQuick.Create(Self);

      RelQuick.QrLblDsEmpres.Caption := DsEmpres;
      RelQuick.QrLblNmFormul.Caption := DsTitulo;
//       RelQuick.QrLblDsTitulo.Caption := RelParame.EdtDsTitulo.Text; 9999

      NoTop := 50;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-= Imprimindo complemento do título -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      RelQuick.PhbCabeca.Height     := RelQuick.PhbCabeca.Height + 30;
      for i := 0 to RelParame.MemObserv.Lines.Count - 1 do
      begin
            DsCabecAux                      := TQRLabel.Create(Self);
            DsCompTitulo[i + 1]             := DsCabecAux;
            DsCompTitulo[i + 1].Parent      := RelQuick.PhbCabeca;
            DsCompTitulo[i + 1].Caption     := RelParame.MemObserv.Lines[i];
            DsCompTitulo[i + 1].Top         := NoTop;
            DsCompTitulo[i + 1].AlignToBand := True;

            if i mod 2 = 0 then
            begin
                  DsCompTitulo[i + 1].Alignment := taLeftJustify;
            end else
            begin
                  DsCompTitulo[i + 1].Alignment := taRightJustify;
                  NoTop := NoTop + 20;
            end;
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-= Imprimindo descrição do LayOut no complemento do título =-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      DsCabecAux                      := TQRLabel.Create(Self);
      DsCompTitulo[i + 1]             := DsCabecAux;
      DsCompTitulo[i + 1].Parent      := RelQuick.PhbCabeca;
//       DsCompTitulo[i + 1].Caption     := 'Layout : ' + RelParame.CbxLayout.Text; 9999
      DsCompTitulo[i + 1].Top         := NoTop;
      DsCompTitulo[i + 1].AlignToBand := True;
      DsCompTitulo[i + 1].Alignment   := taRightJustify;

//      NoTop := NoTop + 20;
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-= Configurando o tipo da folha -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      if RelParame.RgbFolha.ItemIndex = 0 then
      begin
            RelQuick.QRRelatorio.Page.Orientation := poPortrait;
      end else
      begin
            RelQuick.QRRelatorio.Page.Orientation := poLandscape;
      end;

      RelQuick.QrLblDsPagina.Left := RelQuick.PhbCabeca.Width - 90;
      RelQuick.QrLblVitalSis.Left := RelQuick.PhbCabeca.Width - 314;
      RelQuick.QrsDtSistem.Left   := RelQuick.PhbCabeca.Width - 268;
      RelQuick.QrsLinha.Width     := RelQuick.PhbCabeca.Width - 1;
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-= Gerando relação =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      RelQuick.QRRelatorio.DataSet := DtsAtual;

      NoLeft := 0;
      NoTop  := 3;

      for i := 0 to RelParame.ClbColuna.Items.Count - 1 do
      begin
            if RelParame.ClbColuna.Checked[i] then
            begin
                  DsCabecAux     := TQRLabel.Create(Self);
                  DsCabec[i + 1] := DsCabecAux;

                  DsCabec[i + 1].Parent  := RelQuick.PhbComCab;
                  DsCabec[i + 1].Caption := RelParame.ClbColuna.Items[i];

                  if (StrToInt(RelParame.StlTmCampos.Strings[i]) * 10) >=
                                                    (DsCabec[i + 1].Width) then
                  begin
                        NoTam := (StrToInt(
                                   RelParame.StlTmCampos.Strings[i]) * 10) + 10;
                  end else
                  begin
                        NoTam := DsCabec[i + 1].Width + 10;
                  end;

                  if (NoLeft + NoTam) > RelQuick.PhbComCab.Width then
                  begin
                        NoLeft := 0;
                        NoTop  := NoTop + 20;

                        RelQuick.PhbComCab.Height :=
                                               RelQuick.PhbComCab.Height + 20;

                        RelQuick.Detail.Height := RelQuick.Detail.Height + 20;
                  end;

                  DsCabec[i + 1].Left        := NoLeft;
                  DsCabec[i + 1].Top         := NoTop;

                  DsDetalheAux               := TQRDBText.Create(Self);
                  DsDetalhe[i + 1]           := DsDetalheAux;

                  DsDetalhe[i + 1].Parent    := RelQuick.Detail;
                  DsDetalhe[i + 1].Width     :=
                                StrToInt(RelParame.StlTmCampos.Strings[i]) * 10;
                  DsDetalhe[i + 1].AutoSize  := False;
                  DsDetalhe[i + 1].Left      := DsCabec[i + 1].Left;
                  DsDetalhe[i + 1].Top       := NoTop;
                  DsDetalhe[i + 1].DataSet   := DtsAtual;
                  DsDetalhe[i + 1].DataField := RelParame.StlNmCampos.Strings[i];

                  NoLeft := NoLeft + NoTam;
            end;
      end;

      RelQuick.QRRelatorio.Preview;
      RelQuick.Free;
end;

procedure TRelatorio.GerarRelatorioAsc(const DtsAtual: TDataSet;
                                       CdUsuari : Integer; DsEmpres : String;
                                       NmFormul : String;  DsTitulo : String;
                                       FrmOrige : TForm);
var StlDsComCab : TStrings; // Completa Cabecalho
    StlDsCamLin : TStrings; // Campos na Linha
    QtColuna    : Byte;     // Largura do relatorio em colunas
    DsImpres    : RELTR0010;
    StlDsObserv : TStrings;
    StlNmCampos : TStrings;
    StlTmCampos : TStrings;
begin
     gBrvString        := TBrvString.Create(Self);
      FRelAsc          := TBrvRelAsc.Create(Self);
      StlDsComCab      := TStringList.Create;
      StlDsCamLin      := TStringList.Create;
      StlDsObserv      := TStringList.Create;
      StlDsObserv.Text := RelParame.MemObserv.Lines.Text;

      StlNmCampos      := TStringList.Create;
      StlNmCampos.Text := RelParame.StlNmCampos.Text;

      StlTmCampos      := TStringList.Create;
      StlTmCampos.Text := RelParame.StlTmCampos.Text;

      if  RelParame.RgbFolha.ItemIndex = 0 then
      begin
            QtColuna := 80;
      end else
      begin
            QtColuna := 132;
      end;

      DefinirCabecalhoCampoLinha(DtsAtual, StlDsComCab, StlDsCamLin, QtColuna);

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=  Gerando relatóriio =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      FrmOrige.Show;

      FRelAsc.NovoRelatorio(CdUsuari, DsEmpres, NmFormul, DsTitulo);
      DsImpres             := RELTR0010.Create(True);
      DsImpres.DtsAtual    := DtsAtual;
      DsImpres.FRelAsc     := FRelAsc;
      DsImpres.StlDsCamLin := StlDsCamLin;

      if QtColuna = 80 then
      begin
            DsImpres.FRelAsc.ColunasPorLinha := Rel080Col;
      end else
      begin
            DsImpres.FRelAsc.ColunasPorLinha := Rel132Col;
      end;
      DsImpres.FRelAsc.CompletaCabecalho := DsImpres.CompletaCabecalhoAscII;
      DsImpres.StlDsComCab := StlDsComCab;
      DsImpres.StlDsObserv := StlDsObserv;
      DsImpres.DsLayOut    := RelParame.CbxLayout.Text;
      DsImpres.StlNmCampos := StlNmCampos;
      DsImpres.StlTmCampos := StlTmCampos;

      DsImpres.GerarRelatorio;

      FRelAsc.MostrarListagemModal;
//      DsImpres.Resume;
      DsImpres.Terminate;
end;

procedure TRelatorio.DefinirCabecalhoCampoLinha(const DtsAtual : TDataSet;
                                                var StlDsComCab : TStrings;
                                                var StlDsCamLin : TStrings;
                                                    QtColuna    : Byte);
var NrColuna : Integer;
    DsCabeca : String;
    DsColuna : String;
    TmLinha  : byte;
begin
      StlDsComCab.Clear;
      StlDsCamLin.Clear;

      DsCabeca := '';
      DsColuna := '';
      TmLinha  := 0;
      for NrColuna := 0 to RelParame.ClbColuna.Items.Count - 1 do
      begin
            if RelParame.ClbColuna.Checked[NrColuna] then
            begin
                  ProcessarColunaCabecalho(NrColuna,    StlDsComCab,
                                           StlDsCamLin, DsCabeca,
                                           DsColuna,    TmLinha,
                                           QtColuna);
            end;
      end;

      if  DsCabeca <> '' then
      begin
            Delete(DsColuna, Length(DsColuna), 1);
            StlDsComCab.Add(DsCabeca);
            StlDsCamLin.Add(DsColuna);
      end;
end;

procedure TRelatorio.ProcessarColunaCabecalho(NrColuna : Integer;
                     var StlDsComCab : TStrings; var StlDsCamLin : TStrings;
                     var DsCabeca    : String;   var DsColuna    : String;
                     var TmLinha     : Byte;         QtColuna    : byte);
var QtMaiCol : Integer;
begin
       if StrToInt(RelParame.StlTmCampos.Strings[NrColuna]) >
          Length(RelParame.ClbColuna.Items.Strings[NrColuna]) then
       begin
             QtMaiCol := StrToInt(RelParame.StlTmCampos.Strings[NrColuna]);
       end else
       begin
             QtMaiCol := Length(RelParame.ClbColuna.Items.Strings[NrColuna]);
       end;

       if QtMaiCol + TmLinha > QtColuna then
       begin
             Delete(DsColuna, Length(DsColuna), 1);
             StlDsComCab.Add(DsCabeca);
             StlDsCamLin.Add(DsColuna);
             TmLinha  := 0;
             DsCabeca := '';
             DsColuna := '';
       end;

       TmLinha := TmLinha + QtMaiCol + 1;
       DsCabeca := DsCabeca + gBrvString.FormatarStringSemAcento(
                              RelParame.ClbColuna.Items.Strings[NrColuna],
                              QtMaiCol) + ' ';

       RelParame.StlTmCampos.Strings[NrColuna] := IntToStr(QtMaiCol);

       DsColuna := DsColuna + RelParame.StlNmCampos.Strings[NrColuna] + ';';
end;


end.
