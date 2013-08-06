unit BrvCalculaDiasForm;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Buttons, Mask, BrvEditDate, Db, BrvExtenso, DBCtrls,
  ExtCtrls;

type
  TFrmCalDias = class(TForm)
    Panel1: TPanel;
    LblInicial: TLabel;
    LblFinal: TLabel;
    Extenso1: TBrvExtenso;
    BtnRetornaDias: TBitBtn;
    EdtDtFim: TBrvEditDate;
    EdtDtIni: TBrvEditDate;
    procedure BtnRetornaDiasClick(Sender: TObject);
  private
    Function DiasExtenso(QtDias:Integer):String;
    { Private declarations }
  public
    Msg:String;
    QtDias:Integer;
    { Public declarations }
  end;

var
  FrmCalDias: TFrmCalDias;

implementation


{$R *.DFM}

procedure TFrmCalDias.BtnRetornaDiasClick(Sender: TObject);
begin

     Try
        If EdtDtIni.BrAsDate > EdtDtFim.BrAsDate then
        begin

             Exception.Create('Data inicial maior que data final');

        end;
     Except
     end;

     QtDias := StrToInt(FloatToStr(EdtDtfim.BrAsDate - EdtDtini.BrAsDate));

     Msg   := DiasExtenso(QtDias);

     Close;

end;

Function TFrmCalDias.DiasExtenso(QtDias:Integer):String;
var
   aux:Integer;
begin
      if QtDias <= 0 then
      begin
            Result:='';
            Exit;
      end;

      Aux := StrToInt(FloatToStr(Int(QtDias/360)));

      if Aux > 0 then
      begin
            Extenso1.Moeda       := 'Ano(s)';
            Extenso1.PluralMoeda := 'Ano(S)';
            Extenso1.numero      := IntToStr(aux);
            Result               := Extenso1.extenso + '/';
            Aux                  := Aux * 360;
            QtDias               := QtDias - Aux;
      end;

      Aux := StrToInt(FloatToStr(Int(QtDias/30)));

      if Aux > 0 then
      begin
            Extenso1.Moeda       := ' Mes(s)';
            Extenso1.PluralMoeda := ' Mes(es)';
            Extenso1.numero      := IntToStr(aux);
            Result               := Extenso1.extenso + '/';
            Aux                  := Aux * 30;
            Aux                  := QtDias - Aux;
      end else
      begin
            Aux := QtDias;
      end;

      if Aux > 0 then
      begin
            Extenso1.Moeda       := ' Dia(s)';
            Extenso1.PluralMoeda := ' Dia(s)';
            Extenso1.numero      := IntToStr(Aux);
            Result               := Result + Extenso1.extenso;
      end;
end;

end.
