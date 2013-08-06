unit BrvProcesso;

interface

uses SysUtils, Classes, SqlExpr, BrvConnection, BrvClientDataSet, BrvDataSet,
     Provider, BrvDicionario;

type
  TBrvProcesso = class(TComponent)
  private
    { Private declarations }

     FQpProces : TBrvDataSet;
     FDpProces : TDataSetProvider;
     FCcProces : TBrvClientDataSet;

     FDsConnec : TBrvConnection;
     FDsDicion : TBrvDicionario;

     FCdProces : Integer;
     FCdProAnt : Integer;
     FCdProPos : Integer;
     FStProces : String;
     FStProAnt : String;
     FTpProces : String;

     Function  MontarSentecaSQL  : String;
     Procedure SetProcesso(Value : Integer);

  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    destructor  Destroy;                    override;
  published
    { Published declarations }
    property BrConnection           : TBrvConnection read FDsConnec write FDsConnec;
    property BrDicionario           : TBrvDicionario read FDsDicion write FDsDicion;

    property CodigoProcesso         : Integer read FCdProces write SetProcesso;
    property ProcessoAnterior       : Integer read FCdProAnt write FCdProAnt;
    property ProcessoPosterior      : Integer read FCdProPos write FCdProPos;
    property StatusProcesso         : String  read FStProces write FStProces;
    property StatusProcessoAnterior : String  read FStProAnt write FStProAnt;
    property TipoProcesso           : String  read FTpProces write FTpProces;
  end;

procedure Register;

implementation

procedure Register;
begin
       RegisterComponents('Bravo Utils', [TBrvProcesso]);
end;

constructor TBrvProcesso.Create(AOwner: TComponent);
begin
      inherited Create(AOwner);

      FQpProces := TBrvDataSet.Create(Self);
      FDpProces := TDataSetProvider.Create(Self);
      FCcProces := TBrvClientDataSet.Create(Self);
end;

destructor TBrvProcesso.Destroy;
begin
      FQpProces.Destroy;
      FDpProces.Destroy;
      FCcProces.Destroy;

      inherited  destroy;
end;

Procedure TBrvProcesso.SetProcesso(Value : Integer);
begin
      if (FDsConnec <> Nil) and (FDsDicion <> Nil) then
      begin
            FCdProces                  := Value;

            FQpProces.SQLConnection    := FDsConnec;
            FQpProces.BrDicionario     := FDsDicion;
            FQpProces.GetMetadata      := False;
            FQpProces.NumericMapping   := True;
            FQpProces.CommandText      := MontarSentecaSQL;

            FDpProces.DataSet          := FQpProces;
            FDpProces.Constraints      := True;
            FDpProces.Exported         := True;
            FDpProces.ResolveToDataSet := True;
            FDpProces.Name             := 'FDpProces';

            FCcProces.ProviderName     := 'FDpProces';
            FCcProces.ReadOnly         := True;

            FCcProces.Close;
            FCcProces.Open;

            FCdProAnt := FCcProces.FieldByName('CdProAnt').AsInteger;
            FCdProPos := FCcProces.FieldByName('CdProPos').AsInteger;
            FStProces := FCcProces.FieldByName('StProces').AsString;
            FStProAnt := FCcProces.FieldByName('StProAnt').AsString;
            FTpProces := FCcProces.FieldByName('TpProces').AsString;

            FCcProces.Close;
      end;
end;

Function TBrvProcesso.MontarSentecaSQL : String;
begin
      Result := 'Select P.CdProces, P.CdProCha, P.StProces, TpProces,        ' +
                '      (Select P2.CdProces                                   ' +
                '       From   Processos P2                                  ' +
                '       Where  P2.CdProCha = P.CdProCha and                  ' +
                '              P2.NrOrdem  = P.NrOrdem -1) as CdProAnt,      ' +

                '      (Select P2.StProces                                   ' +
                '       From   Processos P2                                  ' +
                '       Where  P2.CdProCha = P.CdProCha and                  ' +
                '              P2.NrOrdem  = P.NrOrdem -1) as StProAnt,      ' +

                '      (Select P2.TpProces                                   ' +
                '       From   Processos P2                                  ' +
                '       Where  P2.CdProCha = P.CdProCha and                  ' +
                '              P2.NrOrdem  = P.NrOrdem -1) as TpProAnt,      ' +

                '      (Select P2.CdProces                                   ' +
                '       From   Processos P2                                  ' +
                '       Where  P2.CdProCha = P.CdProCha and                  ' +
                '              P2.NrOrdem  = P.NrOrdem +1) as CdProPos       ' +

                'From   Processos P                                          ' +

                'Where CdProces = ' + IntToStr(FCdProces);
end;


end.
