unit URel0003;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, URel0000, BrvGeraRelatorio, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop,
  BrvRetCon, StdCtrls, BrvEditNum, Mask, BrvMesAno, DB, DBClient, BrvCustomEdit, BrvCustomMaskEdit,
  BrvListParam, ImgList, Menus, BrvBitBtn;

type
  TRel0003 = class(TRel0000)
    Panel1: TPanel;
    Label2: TLabel;
    EdtCdEmpres: TBrvEditNum;
    EdtDsEmpres: TBrvRetCon;
    Label7: TLabel;
    Label1: TLabel;
    Label3: TLabel;
    EdtDtMesAno: TBrvMesAno;
    EdtDtDiaIni: TBrvEditNum;
    EdtDtDiaFim: TBrvEditNum;
    Label4: TLabel;
    EdtNrPagIni: TBrvEditNum;
    TblTemp: TClientDataSet;
    TblTempNrPagIni: TIntegerField;
    BtnAbrCon: TBrvBitBtn;
    BtnFecCon: TBrvBitBtn;
    procedure BtnAbrConClick(Sender: TObject);
    procedure EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
    procedure BtnGeraRelClick(Sender: TObject);
    procedure BtnFecConClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Rel0003: TRel0003;

implementation

uses UDmCtb, UDmSrvApl;

{$R *.dfm}

procedure TRel0003.BtnFecConClick(Sender: TObject);
begin
      inherited;
      EdtCdEmpres.Enabled     := True;
      BtnAbrCon.Enabled       := True;
      BtnGeraRel.Enabled      := False;
      BtnFecCon.Enabled       := False;
      PnlFundo.Visible        := False;
end;

procedure TRel0003.BtnAbrConClick(Sender: TObject);
var lDtAno : Integer;
    lDtMes : Integer;
begin
      NavBarra.SetFocus;

      if EdtDsEmpres.Text = '' then
      begin
            raise Exception.Create('Empresa deve ser informada');
      end;

      DmCtb.PesquisarPeriodoContabil(EdtCdEmpres.BrAsInteger, 'A', lDtAno, lDtMes);
      EdtDtMesAno.AsMes := lDtMes;
      EdtDtMesAno.AsAno := lDtAno;

      EdtDtDiaIni.BrAsInteger := 1;
      EdtDtDiaFim.BrAsInteger := EdtDtMesAno.AsDiaFinal;

      EdtCdEmpres.Enabled     := False;
      BtnAbrCon.Enabled       := False;
      BtnGeraRel.Enabled      := True;
      BtnFecCon.Enabled       := True;
      PnlFundo.Visible        := True;
      EdtDtMesAno.SetFocus;
end;

procedure TRel0003.BtnGeraRelClick(Sender: TObject);
var lDtInicio : TDate;
    lDtFinal  : TDate;
begin
      inherited;

      if  not EdtDtMesAno.DataValida then
      begin
            raise Exception.Create('Período inválido');
      end;

      if  (EdtDtDiaIni.BrAsInteger <= 0) or
          (EdtDtDiaIni.BrAsInteger > EdtDtMesAno.AsDiaFinal) then
      begin
            raise Exception.Create('Dia inicial inválido');
      end;

      if  (EdtDtDiaFim.BrAsInteger <= 0) or
          (EdtDtDiaFim.BrAsInteger > EdtDtMesAno.AsDiaFinal) then
      begin
            raise Exception.Create('Dia final inválido');
      end;

      if  EdtDtDiaIni.BrAsInteger > EdtDtDiaFim.BrAsInteger then
      begin
            raise Exception.Create('Dia inicial não pode ser maior que final');
      end;

      if  EdtNrPagIni.BrAsInteger  < 0 then
      begin
            raise Exception.Create('Página inicial inválida');
      end;

      lDtInicio := EncodeDate(EdtDtMesAno.AsAno, EdtDtMesAno.AsMes,
                                                 EdtDtDiaIni.BrAsInteger);
      lDtFinal  := EncodeDate(EdtDtMesAno.AsAno, EdtDtMesAno.AsMes,
                                                    EdtDtDiaFim.BrAsInteger);

      BrvGerRel.IniciarRelatorio('RLC0006', 'C', Name);

      BrvGerRel.InserirParametroSQL('CdEmpres', EdtCdEmpres.Text);
      BrvGerRel.InserirParametroSQL('DtInicio', DateToStr(lDtInicio));
      BrvGerRel.InserirParametroSQL('DtFinal',  DateToStr(lDtFinal));

      // =-=-=-=-=-=-=-=
      // Guardando pagina inicial no dataset auxiliar
      // =-=-=-=-=-=-=-=

      if not TblTemp.Active then
      begin
            TblTemp.CreateDataSet;
      end else
      begin
            TblTemp.EmptyDataSet;
      end;

      TblTemp.Append;
      TblTemp.FieldByName('NrPagIni').AsInteger := EdtNrPagIni.BrAsInteger;
      TblTemp.Post;

      // =-=-=-=-=-=-=-=

      BrvGerRel.ImprimirRelatorio;
end;

procedure TRel0003.EdtCdEmpresBrOnBeforeConsulta(Sender: TObject);
begin
      EdtCdEmpres.BrParams.Clear;
      EdtCdEmpres.BrParams.Add('<%CdEmpres%>;' + EdtCdEmpres.BrDicionario.CorpCommaCodes);
end;

initialization
      RegisterClass(TRel0003);

finalization
      UnRegisterClass(TRel0003);
end.
