unit UXmlPlanoContas;

interface

Uses SysUtils, Classes, DateUtils, ComCtrls, BrvString, DBClient;

function AbrirPlanoContas(pTpConta: Integer; pQpPlanoContas: TClientDataSet): String;

procedure PreparaNiveis(pQpPlanoContas: TClientDataSet; pQtNivel: Integer);

implementation

var gSnFinali : Boolean;

function FechaNodes(pNrNivIni,  lNrNivfim : Integer): String;
var lFecNivel : Integer;
begin
      Result := '';

      for lFecNivel := pNrNivIni downto lNrNivfim do
      begin
            Result := Result +  '</N' + FormatFloat('0', lFecNivel) + '>';
      end;
end;

function Ocorrencias(T, S : ShortString) : Byte;
begin
      Result := Length(S)-Length(StringReplace(S, T, '', [rfReplaceAll]));
end;

function TpConta(pTipoCC : Integer; pNrClassi, pNrConta: String): String;
begin
      if pTipoCC = 1 then
      begin
            Result := pNrClassi;
      end else
      begin
            Result := pNrConta;
      end;
end;

function ExtraiClass(pNrClassi: String) : String;
var lTxAux : String;
begin
      lTxAux := pNrClassi + '.';
      Result := '';

      while Trim(lTxAux) <> '' do
      begin
            if (StrToInt(Copy(lTxAux, 1, Pos('.', lTxAux)-1)) > 0) then
            begin
                  Result := Result + Copy(lTxAux, 1, Pos('.', lTxAux)-1) + '.';
            end;

            lTxAux := Copy(lTxAux, Pos('.', lTxAux)+1, length(lTxAux));
      end;

      Result := Copy(Result, 1, Length(Result)-1);
end;

function PreparaXML(pXML: String): String;
var lBrvString : TBrvString;
begin
      try
          lBrvString := TBrvString.Create(nil);
          Result := lBrvString.PreparaXML(pXML);
      finally
          FreeAndNil(lBrvString);
      end;
end;

function MontaNivel(pNrNivel: Integer; pSnFecha: Boolean; pTpConta: Integer;
                                                             pQpPlanoContas: TClientDataSet): String;
var lNodeXml  : String;
    gNrNivAtu : Integer;
begin
      while (not pQpPlanoContas.Eof) and
             (pNrNivel = Ocorrencias('.', ExtraiClass(pQpPlanoContas.FieldByName('NrClassi').AsString))) do
      begin
            if (pNrNivel = 0) then
            begin
                  lNodeXml := '<N' + FormatFloat('0', pNrNivel) + ' VR1="' +
                        pQpPlanoContas.FieldByName('NrClassi').AsString + ' ' +
                        PreparaXML(pQpPlanoContas.FieldByName('NmConta').AsString) +
                        '" VR2="' + TpConta(pTpConta,
                                            pQpPlanoContas.FieldByName('NrClassi').AsString,
                                            pQpPlanoContas.FieldByName('NrConta').AsString) + '">';
            end else
            begin
                  lNodeXml := '<N' + FormatFloat('0', pNrNivel) + ' VR1="' +
                        ExtraiClass(pQpPlanoContas.FieldByName('NrClassi').AsString) + ' ' +
                        PreparaXML(pQpPlanoContas.FieldByName('NmConta').AsString) +
                        '" VR2="' + TpConta(pTpConta,
                                            pQpPlanoContas.FieldByName('NrClassi').AsString,
                                            pQpPlanoContas.FieldByName('NrConta').AsString) + '">';
            end;

            pQpPlanoContas.Next;

            gNrNivAtu := Ocorrencias('.', ExtraiClass(pQpPlanoContas.FieldByName('NrClassi').AsString));

            if gNrNivAtu > pNrNivel then
            begin
                  if (pSnFecha) then
                  begin
                        Result := Result + lNodeXml +
                                  MontaNivel(gNrNivAtu, false, pTpConta, pQpPlanoContas) +
                                                                     FechaNodes(pNrNivel, pNrNivel);
                  end else
                  begin
                        Result := Result + lNodeXml +
                                  MontaNivel(gNrNivAtu, false, pTpConta, pQpPlanoContas);
                  end;
            end else
            begin
                  if gNrNivAtu < pNrNivel then
                  begin
                        Result := Result + lNodeXml + FechaNodes(pNrNivel, gNrNivAtu) +
                                  MontaNivel(gNrNivAtu, false, pTpConta, pQpPlanoContas);
                  end else
                  begin
                        Result := Result + lNodeXml + FechaNodes(pNrNivel, pNrNivel) +
                                  MontaNivel(gNrNivAtu, False, pTpConta, pQpPlanoContas);
                  end;
            end;
      end;

      if (pQpPlanoContas.Eof) then
      begin
            if not gSnFinali then
            begin
                  Result := Result + FechaNodes(pNrNivel-1, 1);
                  gSnFinali := True;
            end;
      end;
end;

function AbrirPlanoContas(pTpConta: Integer; pQpPlanoContas: TClientDataSet): String;
begin
      gSnFinali := false;
      Result := '<Plano VR1="Plano de Contas">' +
                            MontaNivel(Ocorrencias('.',
                            ExtraiClass(pQpPlanoContas.FieldByName('NrClassi').AsString)), True,
                            pTpConta, pQpPlanoContas) + '</Plano>';
end;

procedure PreparaNiveis(pQpPlanoContas: TClientDataSet; pQtNivel: Integer);
begin
      if (pQtNivel > 0) then
      begin
            while not pQpPlanoContas.eof do
            begin
                  if Ocorrencias('.', ExtraiClass(
                    pQpPlanoContas.FieldByName('NrClassi').AsString)) < (pQtNivel) then
                  begin
                        pQpPlanoContas.Next;
                  end else
                  begin
                        pQpPlanoContas.Delete;
                  end;
            end;
      end;

      pQpPlanoContas.First;
end;

end.
