unit BrvRecordacao;

interface

uses
  SysUtils, Classes, BrvDicionario, Windows;

type
  TBrvRecordacao = class(TComponent)
  private
    gDsDicion     :  TBrvDicionario;

    function  NomeArquivoRecordacao(pNmForm : String; pNmEdit : String): String;
    { Private declarations }
  protected
    { Protected declarations }
  public
    { Public declarations }
    function  CarregarRecordacao(pNmForm : String; pNmEdit : String) : String;
    procedure GravarRecordacao(pDsTexto : String; pNmForm : String; pNmEdit : String);
  published
    { Published declarations }
    property BrDicionario       : TBrvDicionario read gDsDicion   write gDsDicion;
  end;

procedure Register;

implementation

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvRecordacao]);
end;

function TBrvRecordacao.CarregarRecordacao(pNmForm : String; pNmEdit : String) : String;
var lNmArqRec : String;
    lStlRecord : TStrings;
begin
      lNmArqRec := NomeArquivoRecordacao(pNmForm, pNmEdit);

      if FileExists(lNmArqRec) then
      begin
            try
                lStlRecord := TStringList.Create;
                lStlRecord.LoadFromFile(lNmArqRec);

                if lStlRecord.Count > 0 then
                begin
                      Result := lStlRecord.Strings[0];
                end;

            finally
                FreeAndNil(lStlRecord);
            end;
      end;
end;

function TBrvRecordacao.NomeArquivoRecordacao(pNmForm : String; pNmEdit : String) : String;
var lDsPatTem : array[0..255] of char;
    lCdUsuari : String;
begin
      if gDsDicion <> nil then
      begin
            lCdUsuari := IntToStr(gDsDicion.UserCode);
      end else
      begin
            lCdUsuari := '0';
      end;

      GetTempPath(255, lDsPatTem);

      Result := lDsPatTem;
      Result := Result + pNmForm + '_' + pNmEdit + '.' + lCdUsuari;
end;

procedure TBrvRecordacao.GravarRecordacao(pDsTexto : String; pNmForm : String;
                                          pNmEdit  : String);
var lNmArqRec : String;
    lStlRecord : TStrings;
begin
      lNmArqRec := NomeArquivoRecordacao(pNmForm, pNmEdit);

      try
          lStlRecord := TStringList.Create;
          lStlRecord.Text := pDsTexto;
          lStlRecord.SaveToFile(lNmArqRec);
      finally
          FreeAndNil(lStlRecord);
      end;
end;

end.
