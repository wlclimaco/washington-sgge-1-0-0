unit URel0007;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms, DB,
  Dialogs, URel0000, BrvGeraRelatorio, ExtCtrls, Buttons, BrvSpeedButton, BrvDbNavCop, StdCtrls,
  CheckLst, BrvCheckListBox, Mask, BrvEditDate, BrvRetCon, BrvEditNum, DBClient, BrvServerProgress,
  BrvListParam, ImgList, Menus, BrvCustomMaskEdit, BrvCustomEdit, BrvBitBtn;

type
  TRel0007 = class(TRel0000)
    LblCdEmpres: TLabel;
    CblCdEmpres: TBrvCheckListBox;
    Label1: TLabel;
    EdtCdGruBem: TBrvEditNum;
    LblDsGruBem: TBrvRetCon;
    Label13: TLabel;
    EdtCdBem: TBrvEditNum;
    LblDsBem: TBrvRetCon;
    Label22: TLabel;
    EdtDtInicia: TBrvEditDate;
    EdtDtFinal: TBrvEditDate;
    Label2: TLabel;
    BrvServerProgress: TBrvServerProgress;
    procedure FormCreate(Sender: TObject);
    procedure BtnGeraRelClick(Sender: TObject);
  private
    procedure ValidaData;
  public
    { Public declarations }
  end;

var
  Rel0007: TRel0007;

implementation

uses UDmSrvApl, BrvDominiosXE;

{$R *.dfm}

procedure TRel0007.BtnGeraRelClick(Sender: TObject);
var lCdsParams : TClientDataSet;
    lidx : Integer;
    lDsEmp: String;
begin
      inherited;
      try
          BrvGerRel.IniciarRelatorio('RLC0009', 'C', Name);

          lCdsParams := TClientDataSet.Create(Self);
          lCdsParams.FieldDefs.Clear;
          lCdsParams.FieldDefs.Add('NmParam',   ftString, 010, False);
          lCdsParams.FieldDefs.Add('DsParam',   ftString, 100, False);
          lCdsParams.CreateDataSet;

          if (CblCdEmpres.BrCheckedCount = 0) then
          begin
                CblCdEmpres.SetFocus;
                raise Exception.Create('Selecione a(s) ' + LblCdEmpres.Caption + '!');
          end else
          begin
                lDsEmp := CblCdEmpres.BrCheckedList;

                lCdsParams.Append;
                lCdsParams.FieldByName('NmParam').AsString := 'CdEmpres';
                lCdsParams.FieldByName('DsParam').AsString := lDsEmp;
                lCdsParams.Post;
          end;

          ValidaData;

          if (StrToIntDef(EdtCdGruBem.Text, 0) > 0)  then
          begin
                lCdsParams.Append;
                lCdsParams.FieldByName('NmParam').AsString := 'CdGruBem';
                lCdsParams.FieldByName('DsParam').AsString := 'and P002.CdGruBem = ' + EdtCdGruBem.Text;
                lCdsParams.Post;
          end else
          begin
                lCdsParams.Append;
                lCdsParams.FieldByName('NmParam').AsString := 'CdGruBem';
                lCdsParams.FieldByName('DsParam').AsString := '';
                lCdsParams.Post;
          end;

          if (StrToIntDef(EdtCdBem.Text, 0) > 0) then
          begin
                lCdsParams.Append;
                lCdsParams.FieldByName('NmParam').AsString := 'CdBem';
                lCdsParams.FieldByName('DsParam').AsString := 'and P002.CdBem = ' + EdtCdBem.Text;
                lCdsParams.Post;
          end else
          begin
                lCdsParams.Append;
                lCdsParams.FieldByName('NmParam').AsString := 'CdBem';
                lCdsParams.FieldByName('DsParam').AsString := '';
                lCdsParams.Post;
          end;

          lCdsParams.Append;
          lCdsParams.FieldByName('NmParam').AsString := 'dtlanini';
          lCdsParams.FieldByName('DsParam').AsString := EdtDtInicia.BrAsDataSQL;
          lCdsParams.Post;

          lCdsParams.Append;
          lCdsParams.FieldByName('NmParam').AsString := 'dtlanfin';
          lCdsParams.FieldByName('DsParam').AsString := EdtDtFinal.BrAsDataSQL;
          lCdsParams.Post;

          BrvGerRel.BrCdsData := lCdsParams;

          BrvServerProgress.Start(Self.Caption, 'Gerando relatório! Aguarde...', 100, 10);
          BrvGerRel.ImprimirRelatorio(BrvServerProgress);
      finally
          FreeAndNil(lCdsParams);
      end;
end;

procedure TRel0007.FormCreate(Sender: TObject);
begin
      inherited;
      CarregaEmpresas(CblCdEmpres, True);
end;

procedure TRel0007.ValidaData;
begin
      if not EdtDtInicia.BrDataValida then
      begin
            raise Exception.Create('Data inicial inválida!');
      end;

      if not EdtDtFinal.BrDataValida then
      begin
            raise Exception.Create('Data final inválida!');
      end;

      if EdtDtFinal.BrAsDate < EdtDtInicia.BrAsDate then
      begin
            raise Exception.Create('Data final menor do que data inicial!');
      end;
end;

initialization
      RegisterClass(TRel0007);

finalization
      UnRegisterClass(TRel0007);


end.
