unit URELTR0010;

interface

uses
  Classes, Db, BrvRelAsc, SysUtils, ExtCtrls, BrvString;

type
  RELTR0010 = class(TThread)
  private
    { Private declarations }
    procedure GerarDetalheAscII;
    function  TamanhoCampo(NmCampo : String) : Byte;
  public
    DtsAtual    : TDataSet;
    FRelAsc     : TBrvRelAsc;
    gBrString   : TBrvString;
    StlDsCamLin : TStrings;
    DsLayOut    : String;
    StlDsComCab : TStrings;
    StlDsObserv : TStrings;
    StlNmCampos : TStrings;
    StlTmCampos : TStrings;
    PnlCancel   : TPanel;
    procedure GerarRelatorio;
    procedure CompletaCabecalhoAscII(Sender : TObject);
  protected
    procedure Execute; override;
  end;

implementation

{ Important: Methods and properties of objects in VCL can only be used in a
  method called using Synchronize, for example,

      Synchronize(UpdateCaption);

  and UpdateCaption could look like,

    procedure RELTR0010.UpdateCaption;
    begin
      Form1.Caption := 'Updated in a thread';
    end; }

{ RELTR0010 }

procedure RELTR0010.Execute;
begin
  { Place thread code here }
      GerarRelatorio;
end;

procedure RELTR0010.GerarRelatorio;
var lQtColuna : Integer;
begin
      DtsAtual.First;

      while not DtsAtual.Eof do
      begin
            GerarDetalheAscII;

            if  StlDsCamLin.Count > 1 then
            begin
                  FRelAsc.NovaLinha(' ');
            end;

            DtsAtual.Next;
      end;

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=  Gerando rodapé do relatório -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      if FRelAsc.ColunasPorLinha = Rel080Col then
      begin
            lQtColuna := 80;
      end else
      begin
            lQtColuna := 132;
      end;

      FRelAsc.NovaLinha(StringOfChar('-', lQtColuna));
      FRelAsc.NovaLinha('Total de registros : ' +
                        gBrString.FormatarStringSemAcento(FormatFloat('0',
                                               DtsAtual.RecordCount), 10));
      FRelAsc.NovaLinha(StringOfChar('-', lQtColuna));

   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      StlDsComCab.Destroy;
      StlDsCamLin.Destroy;
      StlDsObserv.Destroy;
      StlNmCampos.Destroy;
      StlTmCampos.Destroy;
end;

procedure RELTR0010.CompletaCabecalhoAscII(Sender : TObject);
var NrLinha : Byte;
var lQtColuna : Integer;
begin
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-= Complemento do título =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

      if FRelAsc.ColunasPorLinha = Rel080Col then
      begin
            lQtColuna := 80;
            FRelAsc.NovaLinha(gBrString.FormatarStringSemAcento(
                                      StlDsObserv.Strings[0], 40) + ' ' +
                              gBrString.FormatarStringSemAcento(StlDsObserv.Strings[1], 39));

            FRelAsc.NovaLinha(gBrString.FormatarStringSemAcento(
                                      StlDsObserv.Strings[2], 40) + ' ' +
                              gBrString.FormatarStringSemAcento(
                                      'Layout : ' + DsLayOut, 39));
            FRelAsc.NovaLinha(StringOfChar('-', lQtColuna));
      end else
      begin
            lQtColuna := 132;
            FRelAsc.NovaLinha(gBrString.FormatarStringSemAcento(
                                      StlDsObserv.Strings[0], 66) + ' ' +
                              gBrString.FormatarStringSemAcento(
                                      StlDsObserv.Strings[1], 65));

            FRelAsc.NovaLinha(gBrString.FormatarStringSemAcento(
                                      StlDsObserv.Strings[2], 66) + ' ' +
                              gBrString.FormatarStringSemAcento(
                                      'Layout : ' + DsLayOut, 65));

            FRelAsc.NovaLinha(StringOfChar('-', lQtColuna));
      end;
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=- Complemento do cabecalho =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   // =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
      NrLinha := 0;

      while NrLinha < StlDsComCab.Count do
      begin
            FRelAsc.NovaLinha(StlDsComCab.Strings[NrLinha]);
            Inc(NrLinha);
      end;

      FRelAsc.NovaLinha(StringOfChar('-', lQtColuna));
end;

procedure RELTR0010.GerarDetalheAscII;
var NrLinha  : Byte;
    NrPosica : Byte;
    DsCampos : String;
    NmCampo  : String;
    DsDetalh : String;
begin
      NrLinha := 0;

      while NrLinha < StlDsCamLin.Count do
      begin
            DsDetalh  := '';
            DsCampos  := StlDsCamLin.Strings[NrLinha];

            while DsCampos <> '' do
            begin
                  NrPosica := Pos(';', DsCampos);

                  if  NrPosica <> 0 then
                  begin
                        NmCampo := Copy(DsCampos, 1, NrPosica - 1);
                        Delete(DsCampos, 1, NrPosica);
                  end else
                  begin
                        NmCampo  := DsCampos;
                        DsCampos := '';
                  end;

                  case DtsAtual.FieldByName(NmCampo).DataType of
                       ftInteger : DsDetalh := DsDetalh +
                                         gBrString.FormatarNumero(
                                         DtsAtual.FieldByName(NmCampo).AsString,
                                         TamanhoCampo(NmCampo), False);

                       ftFloat : DsDetalh := DsDetalh +
                                         gBrString.FormatarNumero(FormatFloat('0.00',
                                         DtsAtual.FieldByName(NmCampo).AsFloat),
                                         TamanhoCampo(NmCampo), False);

                       else      DsDetalh := DsDetalh +
                                         gBrString.FormatarStringSemAcento(
                                         DtsAtual.FieldByName(NmCampo).AsString,
                                         TamanhoCampo(NmCampo));
                  end;


                  if  DsCampos <> '' then
                  begin
                        DsDetalh := DsDetalh + ' ';
                  end;
            end;

            try
                FRelAsc.NovaLinha(DsDetalh);
            except
            end;
            
            Inc(NrLinha);
      end;
end;

function  RELTR0010.TamanhoCampo(NmCampo : String) : Byte;
var NrColuna : Integer;
begin
      Result   := 0;
      NrColuna := 0;

      while (Result = 0) and (NrColuna < StlNmCampos.Count) do
      begin
            if  NmCampo = StlNmCampos.Strings[NrColuna] then
            begin
                  Result := StrToInt(StlTmCampos.Strings[NrColuna]);
            end;

            Inc(NrColuna);
      end;
end;

end.
