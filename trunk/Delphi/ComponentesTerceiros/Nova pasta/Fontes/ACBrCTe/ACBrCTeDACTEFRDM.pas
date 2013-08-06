{******************************************************************************}
{ Projeto: Componente ACBrCTe                                                  }
{ Biblioteca multiplataforma de componentes Delphi para emiss�o de Conhecimento}
{ Transporte eletr�nica - CTe - http://www.cte.fazenda.gov.br                  }
{                                                                              }
{ Direitos Autorais Reservados (c) 2008 Wemerson Souto                         }
{                                       Daniel Simoes de Almeida               }
{                                       Andr� Ferreira de Moraes               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do Projeto ACBr     }
{ Componentes localizado em http://www.sourceforge.net/projects/acbr           }
{                                                                              }
{                                                                              }
{  Esta biblioteca � software livre; voc� pode redistribu�-la e/ou modific�-la }
{ sob os termos da Licen�a P�blica Geral Menor do GNU conforme publicada pela  }
{ Free Software Foundation; tanto a vers�o 2.1 da Licen�a, ou (a seu crit�rio) }
{ qualquer vers�o posterior.                                                   }
{                                                                              }
{  Esta biblioteca � distribu�da na expectativa de que seja �til, por�m, SEM   }
{ NENHUMA GARANTIA; nem mesmo a garantia impl�cita de COMERCIABILIDADE OU      }
{ ADEQUA��O A UMA FINALIDADE ESPEC�FICA. Consulte a Licen�a P�blica Geral Menor}
{ do GNU para mais detalhes. (Arquivo LICEN�A.TXT ou LICENSE.TXT)              }
{                                                                              }
{  Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral Menor do GNU junto}
{ com esta biblioteca; se n�o, escreva para a Free Software Foundation, Inc.,  }
{ no endere�o 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA.          }
{ Voc� tamb�m pode obter uma copia da licen�a em:                              }
{ http://www.opensource.org/licenses/lgpl-license.php                          }
{                                                                              }
{ Daniel Sim�es de Almeida  -  daniel@djsystem.com.br  -  www.djsystem.com.br  }
{              Pra�a Anita Costa, 34 - Tatu� - SP - 18270-410                  }
{                                                                              }
{******************************************************************************}

{******************************************************************************
|* Historico
|*
|* 30/03/2011: Jeickson Gobeti
|*  - Inicio do desenvolvimento Dacte FastReport
******************************************************************************}
{$I ACBr.inc}

unit ACBrCTeDACTEFRDM;

interface

uses
  SysUtils, Classes, ACBrCTeDACTEClass, pcteCTe, frxClass, frxExportPDF, DB,
  DBClient, frxDBSet, pcnConversao, frxBarcode, MaskUtils, pcnCadEmiDFe;

type
  TdmACBrCTeFR = class(TDataModule)
    frxPDFExport: TfrxPDFExport;
    cdsIdentificacao: TClientDataSet;
    cdsEmitente: TClientDataSet;
    cdsDestinatario: TClientDataSet;
    cdsDadosNotasFiscais: TClientDataSet;
    cdsParametros: TClientDataSet;
    cdsInformacoesAdicionais: TClientDataSet;
    cdsVolumes: TClientDataSet;
    frxIdentificacao: TfrxDBDataset;
    frxEmitente: TfrxDBDataset;
    frxDestinatario: TfrxDBDataset;
    frxDadosNotasFiscais: TfrxDBDataset;
    frxParametros: TfrxDBDataset;
    frxVolumes: TfrxDBDataset;
    frxInformacoesAdicionais: TfrxDBDataset;
    frxBarCodeObject: TfrxBarCodeObject;
    frxReport: TfrxReport;
    cdsTomador: TClientDataSet;
    frxTomador: TfrxDBDataset;
    cdsExpedidor: TClientDataSet;
    frxExpedidor: TfrxDBDataset;
    cdsRecebedor: TClientDataSet;
    frxRecebedor: TfrxDBDataset;
    cdsRemetente: TClientDataSet;
    frxRemetente: TfrxDBDataset;
    cdsCalculoImposto: TClientDataSet;
    frxCalculoImposto: TfrxDBDataset;
    cdsComponentesPrestacao: TClientDataSet;
    frxComponentesPrestacao: TfrxDBDataset;
    cdsSeguro: TClientDataSet;
    frxSeguro: TfrxDBDataset;
    cdsModalRodoviario: TClientDataSet;
    frxModalRodoviario: TfrxDBDataset;
    cdsRodoVeiculos: TClientDataSet;
    frxRodoVeiculos: TfrxDBDataset;
    frxRodoValePedagio: TfrxDBDataset;
    cdsRodoValePedagio: TClientDataSet;
    cdsRodoMotorista: TClientDataSet;
    frxRodoMotorista: TfrxDBDataset;
    constructor Create(AOwner: TComponent); override;
  private
    { Private declarations }
    FDACTEClassOwner: TACBrCTeDACTEClass;
    FCTe: TCTe;
    procedure CarregaIdentificacao;
    procedure CarregaTomador;
    procedure CarregaEmitente;
    procedure CarregaRemetente;
    procedure CarregaDestinatario;
    procedure CarregaExpedidor;
    procedure CarregaRecebedor;
    procedure CarregaDadosNotasFiscais;
    procedure CarregaCalculoImposto;
    procedure CarregaParametros;
    procedure CarregaVolumes;
    procedure CarregaComponentesPrestacao;
    procedure CarregaSeguro;
    procedure CarregaModalRodoviario;
    procedure CarregaInformacoesAdicionais;

  public
    { Public declarations }
    property CTe: TCTe read FCTe write FCTe;
    property DACTEClassOwner: TACBrCTeDACTEClass read FDACTEClassOwner;
    procedure CarregaDados;
  end;

var
  dmACBrCTeFR: TdmACBrCTeFR;

implementation

uses ACBrCTe, ACBrCTeUtil, ACBrDFeUtil, StrUtils, Math;

{$R *.dfm}

type
  ArrOfStr = array of string;
  TSplitResult = array of string;

  { TdmACBrNFeFR }

function SubstrCount(const ASubString, AString: string): Integer;
var
  i: integer;
begin
  Result := -1;
  i := 0;
  repeat
    Inc(Result);
    i := PosEx(ASubString, AString, i + 1);
  until i = 0;
end;

function Split(const ADelimiter, AString: string): TSplitResult;
var
  Step: ^string;
  Chr: PChar;
  iPos, iLast, iDelLen, iLen, x: integer;
label
  EndLoop;
begin
  SetLength(Result, SubstrCount(ADelimiter, AString) + 1);
  if High(Result) = 0 then
    Result[0] := AString
  else
  begin
    iDelLen := PCardinal(Cardinal(ADelimiter) - SizeOf(Cardinal))^;
    iLen := PCardinal(Cardinal(AString) - SizeOf(Cardinal))^;
    Step := @Result[0];
    iLast := 0;
    iPos := 0;
    repeat
      if iPos + iDelLen > iLen then
      begin
        if iLast <> iPos then
          iPos := iLen;
      end else
        for x := 1 to iDelLen do
          if AString[iPos + x] <> ADelimiter[x] then
            goto EndLoop;

      if iPos - iLast > 0 then
      begin
        SetLength(Step^, iPos - iLast);
        Chr := PChar(Step^);
        for x := 1 to PCardinal(Cardinal(Step^) - SizeOf(Cardinal))^ do
        begin
          Chr^ := AString[iLast + x];
          Inc(Chr);
        end;
      end else
        Step^ := '';

      Cardinal(Step) := Cardinal(Step) + SizeOf(Cardinal);
      iLast := iPos + iDelLen;

      EndLoop:
      Inc(iPos);
    until iLast >= iLen;
  end;
end;

function Explode(sPart, sInput: string): ArrOfStr;
begin
  while Pos(sPart, sInput) <> 0 do
  begin
    SetLength(Result, Length(Result) + 1);
    Result[Length(Result) - 1] := Copy(sInput, 0, Pos(sPart, sInput) - 1);
    Delete(sInput, 1, Pos(sPart, sInput));
  end;

  SetLength(Result, Length(Result) + 1);
  Result[Length(Result) - 1] := sInput;
end;

function CollateBr(Str: string): string;
var
  Resultado, Temp: string;
  vChar: Char;
  Tamanho, i: integer;
begin
  Result := '';
  Tamanho := Length(str);
  i := 1;
  while i <= Tamanho do
  begin
    Temp := Copy(str, i, 1);
    vChar := Temp[1];
    case vChar of
      '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�': Resultado := 'A';
      '�', '�', '�', '�', '�', '�', '�', '�': Resultado := 'E';
      '�', '�', '�', '�', '�', '�', '�', '�': Resultado := 'I';
      '�', '�', '�', '�', '�', '�', '�', '�', '�', '�': Resultado := 'O';
      '�', '�', '�', '�', '�', '�', '�', '�': Resultado := 'U';
      '�', '�': Resultado := 'C';
      '�', '�': Resultado := 'N';
      '�', '�', '�', 'Y': Resultado := 'Y';
      else
        if vChar > #127 then Resultado := #32
{$IFDEF DELPHI12_UP}
        else if CharInset(vChar, ['a'..'z', 'A'..'Z', '0'..'9', '-', ' ']) then
{$ELSE}
        else if vChar in ['a'..'z', 'A'..'Z', '0'..'9', '-', ' '] then
{$ENDIF}
          Resultado := UpperCase(vCHAR);
    end;
    Result := Result + Resultado;
    i := i + 1;
  end;
end;

procedure TdmACBrCTeFR.CarregaCalculoImposto;
begin
  with cdsCalculoImposto do
  begin
    Close;
    FieldDefs.Clear;
    FieldDefs.Add('TXTSITTRIB', ftString, 60);
    FieldDefs.Add('VBC', ftFloat);
    FieldDefs.Add('PICMS', ftFloat);
    FieldDefs.Add('VICMS', ftFloat);
    FieldDefs.Add('pRedBC', ftFloat);
    FieldDefs.Add('VICMSST', ftFloat);
    FieldDefs.Add('VCREDITO', ftFloat);
    FieldDefs.Add('vIndSN', ftInteger);

    CreateDataSet;
    Append;

{$IFDEF PL_103}
    case FCTe.Imp.ICMS.SituTrib of
      cst00:
        begin
          FieldByName('TXTSITTRIB').AsString := CSTICMSToStr(cst00) + '-' + CSTICMSToStrTagPosText(cst00);
          FieldByName('vBC').AsFloat := FCTe.Imp.ICMS.CST00.vBC;
          FieldByName('pICMS').AsFloat := FCTe.Imp.ICMS.CST00.pICMS;
          FieldByName('vICMS').AsFloat := FCTe.Imp.ICMS.CST00.VICMS;
        end;
      cst45:
        begin
          FieldByName('TXTSITTRIB').AsString := CSTICMSToStr(cst45) + '-' + CSTICMSToStrTagPosText(cst45);
        end;
    end;
{$ENDIF}
{$IFDEF PL_104}
    case FCTe.Imp.ICMS.SituTrib of
      cst00:
        begin
          FieldByName('TXTSITTRIB').AsString := CSTICMSToStr(cst00) + '-' + CSTICMSToStrTagPosText(cst00);
          FieldByName('vBC').AsFloat := FCTe.Imp.ICMS.ICMS00.vBC;
          FieldByName('pICMS').AsFloat := FCTe.Imp.ICMS.ICMS00.pICMS;
          FieldByName('vICMS').AsFloat := FCTe.Imp.ICMS.ICMS00.VICMS;
        end;
      cst20:
        begin
          FieldByName('TXTSITTRIB').AsString := CSTICMSToStr(cst20) + '-' + CSTICMSToStrTagPosText(cst20);
          FieldByName('pRedBC').AsFloat := FCTe.Imp.ICMS.ICMS20.pRedBC;
          FieldByName('vBC').AsFloat := FCTe.Imp.ICMS.ICMS20.vBC;
          FieldByName('pICMS').AsFloat := FCTe.Imp.ICMS.ICMS20.pICMS;
          FieldByName('vICMS').AsFloat := FCTe.Imp.ICMS.ICMS20.VICMS;
        end;
      cst45:
        begin
          FieldByName('TXTSITTRIB').AsString := CSTICMSToStr(cst45) + '-' + CSTICMSToStrTagPosText(cst45);
        end;
      cst60:
        begin
          FieldByName('TXTSITTRIB').AsString := CSTICMSToStr(cst60) + '-' + CSTICMSToStrTagPosText(cst60);
          FieldByName('vBC').AsFloat := FCTe.Imp.ICMS.ICMS60.vBCSTRet;
          FieldByName('pICMS').AsFloat := FCTe.Imp.ICMS.ICMS60.pICMSSTRet;
          FieldByName('vICMS').AsFloat := FCTe.Imp.ICMS.ICMS60.vICMSSTRet;
          FieldByName('vCredito').AsFloat := FCTe.Imp.ICMS.ICMS60.vCred;
        end;
      cst90:
        begin
          FieldByName('TXTSITTRIB').AsString := CSTICMSToStr(cst90) + '-' + CSTICMSToStrTagPosText(cst90);
          FieldByName('pRedBC').AsFloat := FCTe.Imp.ICMS.ICMS90.pRedBC;
          FieldByName('vBC').AsFloat := FCTe.Imp.ICMS.ICMS90.vBC;
          // Alterado por Italo em 22/10/2012  
          FieldByName('pICMS').AsFloat := FCTe.Imp.ICMS.ICMS90.pICMS;
          FieldByName('vICMS').AsFloat := FCTe.Imp.ICMS.ICMS90.vICMS;
          FieldByName('vCredito').AsFloat := FCTe.Imp.ICMS.ICMS90.vCred;
        end;
      // Incluido por Italo em 05/12/2011 (contribui��o de Doni Dephi)
      cstICMSOutraUF:
        begin
          FieldByName('TXTSITTRIB').AsString := CSTICMSToStr(cstICMSOutraUF) + '-' + CSTICMSToStrTagPosText(cstICMSOutraUF);
          FieldByName('pRedBC').AsFloat := FCTe.Imp.ICMS.ICMSOutraUF.pRedBCOutraUF;
          FieldByName('vBC').AsFloat := FCTe.Imp.ICMS.ICMSOutraUF.vBCOutraUF;
          FieldByName('pICMS').AsFloat := FCTe.Imp.ICMS.ICMSOutraUF.pRedBCOutraUF;
          FieldByName('vICMS').AsFloat := FCTe.Imp.ICMS.ICMSOutraUF.vICMSOutraUF;
        end;
      cstICMSSN:
        begin
          FieldByName('TXTSITTRIB').AsString := CSTICMSToStr(cstICMSSN) + '-' + CSTICMSToStrTagPosText(cstICMSSN);
          FieldByName('vIndSN').AsFloat := FCTe.Imp.ICMS.ICMSSN.indSN;
        end;
    end;
{$ENDIF}
    Post;
  end;
end;

procedure TdmACBrCTeFR.CarregaComponentesPrestacao;
var
  I: Integer;
begin
  with cdsComponentesPrestacao do
  begin
    Close;
    FieldDefs.Clear;
    FieldDefs.Add('Nome', ftString, 60);
    FieldDefs.Add('Valor', ftFloat);
    FieldDefs.Add('TotalServico', ftFloat);
    FieldDefs.Add('TotalReceber', ftFloat);
    CreateDataSet;

    if CTe.vPrest.comp.Count > 0 then
    begin
      for I := 0 to CTe.vPrest.comp.Count - 1 do
      begin
        Append;
        FieldByName('Nome').AsString := CTe.vPrest.comp.Items[I].xNome;
        FieldByName('Valor').AsFloat := CTe.vPrest.comp.Items[I].vComp;
        FieldByName('TotalServico').AsFloat := CTe.vPrest.vTPrest;
        FieldByName('TotalReceber').AsFloat := CTe.vPrest.vRec;
        Post;
      end;
    end
    else
    begin
      Append;
      FieldByName('Nome').AsString := '';
      FieldByName('Valor').AsFloat := 0;
      FieldByName('TotalServico').AsFloat := 0;
      FieldByName('TotalReceber').AsFloat := 0;
      Post;
    end;
  end;

end;

procedure TdmACBrCTeFR.CarregaDados;
begin
  CarregaIdentificacao;
  CarregaTomador;
  CarregaEmitente;
  CarregaRemetente;
  CarregaDestinatario;
  CarregaExpedidor;
  CarregaRecebedor;
  CarregaDadosNotasFiscais;
  CarregaParametros;
  CarregaCalculoImposto;
  CarregaVolumes;
  CarregaComponentesPrestacao;
  CarregaInformacoesAdicionais;
  CarregaSeguro;
  CarregaModalRodoviario;

end;

procedure TdmACBrCTeFR.CarregaDadosNotasFiscais;
var
  i: Integer;
  DoctoRem: string;
begin
  { dados das Notas Fiscais }
  DoctoRem := FCTe.Rem.CNPJCPF;
  if Length(DoctoRem) > 11 then
    DoctoRem := FormatMaskText('##.###.###\/####-##;0;_', DoctoRem)
  else
    DoctoRem := FormatMaskText('###.###.###-##;0;_', DoctoRem);

  with cdsDadosNotasFiscais do
  begin
    Close;
    FieldDefs.Clear;
    FieldDefs.Add('tpDoc', ftString, 5); //Tipo Documento
    FieldDefs.Add('CNPJCPF', ftString, 18); //CNPJCPF
    FieldDefs.Add('Serie', ftString, 3); // Serie
    FieldDefs.Add('ChaveAcesso', ftString, 44); // Chave Acesso
    FieldDefs.Add('NotaFiscal', ftString, 9); // Numero Nota Fiscal
    FieldDefs.Add('TextoImpressao', ftString, 100); // Texto Impressao no Relatorio
    CreateDataSet;

    for i := 0 to CTe.Rem.InfNF.Count - 1 do
    begin
      Append;
      with FCTe.Rem.InfNF.Items[i] do
      begin
        FieldByName('tpDoc').AsString := 'NF';
        FieldByName('CNPJCPF').AsString := FCTe.Rem.CNPJCPF;
        FieldByName('Serie').AsString := serie;
        FieldByName('ChaveAcesso').AsString := '';
        FieldByName('NotaFiscal').AsString := nDoc;
        FieldByName('TextoImpressao').AsString := 'NF                 ' + DoctoRem + '                                      ' +
          FormatFloat('000', StrToInt(serie)) + '  /  ' + FormatFloat('000000000', StrToInt(nDoc));
      end;
      Post;
    end;

    for i := 0 to CTe.Rem.InfNFE.Count - 1 do
    begin
      Append;
      with FCTe.Rem.InfNFE.Items[i] do
      begin
        FieldByName('tpDoc').AsString := 'NFe';
        FieldByName('CNPJCPF').AsString := FCTe.Rem.CNPJCPF;
        FieldByName('Serie').AsString := '';
        FieldByName('ChaveAcesso').AsString := chave;
        FieldByName('NotaFiscal').AsString := '';
        FieldByName('TextoImpressao').AsString := 'NF-e            ' + chave;
      end;
      Post;
    end;
    //
    cdsDadosNotasFiscais.RecordCount;
  end;

end;

procedure TdmACBrCTeFR.CarregaDestinatario;
begin
  { destinat�rio }
  with cdsDestinatario do
  begin
    Close;
    FieldDefs.Clear;
    FieldDefs.Add('CNPJCPF', ftString, 18);
    FieldDefs.Add('XNome', ftString, 60);
    FieldDefs.Add('XLgr', ftString, 60);
    FieldDefs.Add('Nro', ftString, 60);
    FieldDefs.Add('XCpl', ftString, 60);
    FieldDefs.Add('XBairro', ftString, 60);
    FieldDefs.Add('CMun', ftString, 7);
    FieldDefs.Add('XMun', ftString, 60);
    FieldDefs.Add('UF', ftString, 2);
    FieldDefs.Add('CEP', ftString, 9);
    FieldDefs.Add('CPais', ftString, 4);
    FieldDefs.Add('XPais', ftString, 60);
    FieldDefs.Add('Fone', ftString, 15);
    FieldDefs.Add('IE', ftString, 14);

    CreateDataSet;
    Append;

    with FCTe.Dest do
    begin
      if DFeUtil.NaoEstaVazio(CNPJCPF) then
      begin
        if Length(CNPJCPF) > 11 then
          FieldByName('CNPJCPF').AsString := DFeUtil.FormatarCNPJ(CNPJCPF)
        else
          FieldByName('CNPJCPF').AsString := DFeUtil.FormatarCPF(CNPJCPF);
      end
      else
        FieldByName('CNPJCPF').AsString := '';

      FieldByName('XNome').AsString := XNome;
      with EnderDest do
      begin
        FieldByName('XLgr').AsString := XLgr;
        FieldByName('Nro').AsString := Nro;
        FieldByName('XCpl').AsString := XCpl;
        FieldByName('XBairro').AsString := XBairro;
        FieldByName('CMun').AsString := IntToStr(CMun);
        FieldByName('XMun').AsString := CollateBr(XMun);
        FieldByName('UF').AsString := UF;
        FieldByName('CEP').AsString := DFeUtil.FormatarCEP(DFeUtil.Poem_Zeros(CEP, 8));
        FieldByName('CPais').AsString := IntToStr(CPais);
        FieldByName('XPais').AsString := XPais;
        FieldByName('Fone').AsString := DFeUtil.FormatarFone(Fone);
      end;
      FieldByName('IE').AsString := IE;
    end;
    Post;
  end;
end;

procedure TdmACBrCTeFR.CarregaEmitente;
begin
  { emitente }
  with cdsEmitente do
  begin
    Close;
    FieldDefs.Clear;
    FieldDefs.Add('CNPJ', ftString, 18);
    FieldDefs.Add('XNome', ftString, 60);
    FieldDefs.Add('XFant', ftString, 60);
    FieldDefs.Add('XLgr', ftString, 60);
    FieldDefs.Add('Nro', ftString, 60);
    FieldDefs.Add('XCpl', ftString, 60);
    FieldDefs.Add('XBairro', ftString, 60);
    FieldDefs.Add('CMun', ftString, 7);
    FieldDefs.Add('XMun', ftString, 60);
    FieldDefs.Add('UF', ftString, 2);
    FieldDefs.Add('CEP', ftString, 9);
    FieldDefs.Add('CPais', ftString, 4);
    FieldDefs.Add('XPais', ftString, 60);
    FieldDefs.Add('Fone', ftString, 15);
    FieldDefs.Add('IE', ftString, 14);
    FieldDefs.Add('IM', ftString, 15);
    FieldDefs.Add('IEST', ftString, 15);
    FieldDefs.Add('CRT', ftString, 1);

    CreateDataSet;
    Append;

    with FCTE.Emit do
    begin
      FieldByName('CNPJ').AsString := DFeUtil.FormatarCNPJ(CNPJ);
      FieldByName('XNome').AsString := XNome;
      FieldByName('XFant').AsString := XFant;
      with EnderEmit do
      begin
        FieldByName('Xlgr').AsString := XLgr;
        FieldByName('Nro').AsString := Nro;
        FieldByName('XCpl').AsString := XCpl;
        FieldByName('XBairro').AsString := XBairro;
        FieldByName('CMun').AsString := IntToStr(CMun);
        FieldByName('XMun').AsString := CollateBr(XMun);
        FieldByName('UF').AsString := UF;
        FieldByName('CEP').AsString := DFeUtil.FormatarCEP(DFeUtil.Poem_Zeros(CEP, 8));
        //        FieldByName('CPais').AsString := IntToStr(CPais);
        //        FieldByName('XPais').AsString := XPais;
        FieldByName('Fone').AsString := DFeUtil.FormatarFone(Fone);
      end;
      FieldByName('IE').AsString := IE;
    end;

    Post;
  end;
end;

procedure TdmACBrCTeFR.CarregaExpedidor;
begin
  { Expedidor }
  with cdsExpedidor do
  begin
    Close;
    FieldDefs.Clear;
    FieldDefs.Add('CNPJ', ftString, 18);
    FieldDefs.Add('XNome', ftString, 60);
    FieldDefs.Add('XFant', ftString, 60);
    FieldDefs.Add('XLgr', ftString, 60);
    FieldDefs.Add('Nro', ftString, 60);
    FieldDefs.Add('XCpl', ftString, 60);
    FieldDefs.Add('XBairro', ftString, 60);
    FieldDefs.Add('CMun', ftString, 7);
    FieldDefs.Add('XMun', ftString, 60);
    FieldDefs.Add('UF', ftString, 2);
    FieldDefs.Add('CEP', ftString, 9);
    FieldDefs.Add('CPais', ftString, 4);
    FieldDefs.Add('XPais', ftString, 60);
    FieldDefs.Add('Fone', ftString, 15);
    FieldDefs.Add('IE', ftString, 14);
    FieldDefs.Add('IM', ftString, 15);
    FieldDefs.Add('IEST', ftString, 15);
    FieldDefs.Add('CRT', ftString, 1);

    CreateDataSet;
    Append;

    with FCTE.Exped do
    begin
      FieldByName('CNPJ').AsString := DFeUtil.FormatarCNPJ(CNPJCPF);
      FieldByName('XNome').AsString := XNome;
      with EnderExped do
      begin
        FieldByName('Xlgr').AsString := XLgr;
        FieldByName('Nro').AsString := Nro;
        FieldByName('XCpl').AsString := XCpl;
        FieldByName('XBairro').AsString := XBairro;
        FieldByName('CMun').AsString := IntToStr(CMun);
        FieldByName('XMun').AsString := CollateBr(XMun);
        FieldByName('UF').AsString := UF;
        FieldByName('CEP').AsString := DFeUtil.FormatarCEP(DFeUtil.Poem_Zeros(CEP, 8));
        FieldByName('CPais').AsString := IntToStr(CPais);
        FieldByName('XPais').AsString := XPais;
        FieldByName('Fone').AsString := DFeUtil.FormatarFone(Fone);
      end;
      FieldByName('IE').AsString := IE;
    end;

    Post;
  end;

end;

procedure TdmACBrCTeFR.CarregaIdentificacao;
begin
  with cdsIdentificacao do
  begin
    Close;
    FieldDefs.Clear;
    //FieldDefs.Add('Versao', ftString, 4);
    FieldDefs.Add('Id', ftString, 44);
    FieldDefs.Add('Chave', ftString, 60);
    FieldDefs.Add('CUF', ftString, 2);
    FieldDefs.Add('CCT', ftString, 9);
    FieldDefs.Add('CFOP', ftString, 4);
    FieldDefs.Add('NatOp', ftString, 60);
    FieldDefs.Add('forPag', ftString, 50);
    FieldDefs.Add('Mod_', ftString, 2);
    FieldDefs.Add('Serie', ftString, 3);
    FieldDefs.Add('NCT', ftString, 9);
    FieldDefs.Add('dhEmi', ftDate);
    FieldDefs.Add('TpImp', ftString, 1);
    FieldDefs.Add('TpEmis', ftString, 50);
    FieldDefs.Add('CDV', ftString, 1);
    FieldDefs.Add('TpAmb', ftString, 1);
    FieldDefs.Add('TpCT', ftString, 50);
    FieldDefs.Add('ProcEmi', ftString, 1);
    FieldDefs.Add('VerProc', ftString, 20);
    FieldDefs.Add('cMunEmi', ftString, 7);
    FieldDefs.Add('xMunEmi', ftString, 60);
    FieldDefs.Add('UFEmi', ftString, 2);
    FieldDefs.Add('modal', ftString, 1);
    FieldDefs.Add('tpServ', ftString, 50);
    FieldDefs.Add('cMunIni', ftString, 7);
    FieldDefs.Add('xMunIni', ftString, 60);
    FieldDefs.Add('UFIni', ftString, 2);
    FieldDefs.Add('cMunFim', ftString, 7);
    FieldDefs.Add('xMunFim', ftString, 60);
    FieldDefs.Add('UFFim', ftString, 2);
    FieldDefs.Add('retira', ftString, 1);
    FieldDefs.Add('xDetRetira', ftString, 160);
    FieldDefs.Add('toma', ftString, 50);
    CreateDataSet;
    Append;

    with FCTe.infCTe do
    begin
      //FieldByName('Versao').AsString := IntToStr(Versao);
      FieldByName('Id').AsString := DFeUtil.LimpaNumero(Id);
      FieldByName('Chave').AsString := CTeUtil.FormatarChaveAcesso(Id);
    end;

    with FCTe.Ide do
    begin
      FieldByName('CUF').AsString := IntToStr(CUF);
      FieldByName('CCT').AsString := IntToStr(CCT);
      FieldByName('CFOP').AsString := IntToStr(CFOP);
      FieldByName('NatOp').AsString := NatOp;

      case forPag of
        fpPago: FieldByName('forPag').AsString := 'Pago';
        fpAPagar: FieldByName('forPag').AsString := 'A Pagar';
        fpOutros: FieldByName('forPag').AsString := 'Outros';
      end;

      FieldByName('Mod_').AsString := modelo;
      FieldByName('Serie').AsString := IntToStr(Serie);
      FieldByName('NCT').AsString := CTeUtil.FormatarNumCTe(nCT);
      FieldByName('dhEmi').AsDateTime := dhEmi;

      case tpCTe of
        tcNormal: FieldByName('TpCT').AsString := 'Normal';
        tcComplemento: FieldByName('TpCT').AsString := 'Complemento';
        tcAnulacao: FieldByName('TpCT').AsString := 'Anula��o';
        tcSubstituto: FieldByName('TpCT').AsString := 'Substituto';
      end;
{$IFDEF PL_103}
      FieldByName('cMunEmi').AsString := IntToStr(cMunEmi);
      FieldByName('xMunEmi').AsString := xMunEmi;
      FieldByName('UFEmi').AsString := UFEmi;
{$ENDIF}
{$IFDEF PL_104}
      FieldByName('cMunEmi').AsString := IntToStr(cMunEnv);
      FieldByName('xMunEmi').AsString := xMunEnv;
      FieldByName('UFEmi').AsString := UFEnv;
{$ENDIF}

      FieldByName('modal').AsString := DFeUtil.SeSenao(modal = mdRodoviario, '0', '0');

      case tpServ of
        tsNormal: FieldByName('tpServ').AsString := 'Normal';
        tsSubcontratacao: FieldByName('tpServ').AsString := 'Subcontrata��o';
        tsRedespacho: FieldByName('tpServ').AsString := 'Redespacho';
        tsIntermediario: FieldByName('tpServ').AsString := 'Intermedi�rio';
      end;

      FieldByName('cMunIni').AsString := IntToStr(cMunIni);
      FieldByName('xMunIni').AsString := xMunIni;
      FieldByName('UFIni').AsString := UFIni;
      FieldByName('cMunFim').AsString := IntToStr(cMunFim);
      FieldByName('xMunFim').AsString := xMunFim;
      FieldByName('UFFim').AsString := UFFim;
      FieldByName('TpImp').AsString := DFeUtil.SeSenao(TpImp = tiRetrato, '1', '2');
      FieldByName('TpEmis').AsString := DFeUtil.SeSenao(TpEmis = teNormal, '1', '5');
      FieldByName('CDV').AsString := IntToStr(CDV);
      FieldByName('TpAmb').AsString := DFeUtil.SeSenao(TpAmb = taHomologacao, '2', '1');
      FieldByName('ProcEmi').AsString := DFeUtil.SeSenao(ProcEmi = peAplicativoContribuinte, '0', '');
      FieldByName('VerProc').AsString := VerProc;

      case Toma03.Toma of
        tmRemetente: FieldByName('Toma').AsString := 'Remetente';
        tmDestinatario: FieldByName('Toma').AsString := 'Destinat�rio';
        tmExpedidor: FieldByName('Toma').AsString := 'Expedidor';
        tmRecebedor: FieldByName('Toma').AsString := 'Recebedor';
      end;

      case Toma4.Toma of
        tmOutros: FieldByName('Toma').AsString := 'Outros';
      end;
    end;
    Post;
  end;
end;

procedure TdmACBrCTeFR.CarregaInformacoesAdicionais;
var
  vTemp: TStringList;
  IndexCampo: Integer;
  Campos: TSplitResult;
  BufferObs: string;
  TmpStr: string;
  wContingencia: string;
  wObs: string;
begin

  with cdsInformacoesAdicionais do
  begin
    Close;
    FieldDefs.Clear;
    FieldDefs.Add('OBS', ftString, 2000);
    CreateDataSet;
    Append;
    with FCTe.compl do
    begin
      wObs := xObs;

      //Contingencia
      if FCTe.Ide.tpEmis = teNORMAL then
        wContingencia := ''
      else
      begin
        if (FCTe.Ide.tpEmis = teContingencia) or (FCTe.Ide.tpEmis = teFSDA) or (FCTe.Ide.tpEmis = teSCAN) then
          wContingencia := 'DACTE EM CONTING�NCIA, IMPRESSO EM DECORR�NCIA DE PROBLEMAS T�CNICOS'
        else if FCTe.Ide.tpEmis = teDPEC then
          wContingencia := 'DACTE IMPRESSO EM CONTING�NCIA - DPEC REGULARMENTE RECEBIDA PELA RECEITA FEDERAL DO BRASIL';

        //            wContingencia := wContingencia + ';' +
        //            'DATA/HORA IN�CIO: ' + DFeUtil.SeSenao(FCTe.ide.dhCont = 0, ' ', DateTimeToStr(FCTe.ide.dhCont)) + ';'+
        //            'MOTIVO CONTING�NCIA: ' + DFeUtil.SeSenao(DFeUtil.EstaVazio(FCTe.ide.xJust), ' ', FCTe.ide.xJust);
      end;
      if Length(wObs) > 0 then
        wObs := wObs + ';';
      wObs := wObs + wContingencia;

      vTemp := TStringList.Create;
      try
        if Trim(wObs) <> '' then
        begin
          Campos := Split(';', wObs);
          for IndexCampo := 0 to Length(Campos) - 1 do
            vTemp.Add(Campos[IndexCampo]);

          TmpStr := vTemp.Text;
          BufferObs := TmpStr;
        end
        else
          BufferObs := '';

      finally
        vTemp.Free;
      end;
    end;
    FieldByName('OBS').AsString := BufferObs;

    Post;
  end;

end;

procedure TdmACBrCTeFR.CarregaModalRodoviario;
var
  i: integer;
begin
  with cdsModalRodoviario do
  begin
    Close;
    FieldDefs.Clear;
    FieldDefs.Add('RNTRC', ftString, 60);
    FieldDefs.Add('DATAPREVISTA', ftString, 60);
    FieldDefs.Add('LOTACAO', ftString, 60);
    FieldDefs.Add('CIOT', ftString, 12);
    FieldDefs.Add('LACRES', ftString, 255);
    CreateDataSet;
    Append;
    //
    case CTe.Rodo.Lota of
      ltNao: FieldByName('LOTACAO').AsString := 'N�o';
      ltSim: FieldByName('LOTACAO').AsString := 'Sim';
    end;
    FieldByName('RNTRC').AsString := CTe.Rodo.RNTRC;
    FieldByName('DATAPREVISTA').AsString := DateToStr(CTe.Rodo.dPrev);
{$IFDEF PL_104}
    FieldByName('CIOT').AsString := cte.Rodo.CIOT;
{$ENDIF}

    for I := 0 to CTe.Rodo.Lacres.Count - 1 do
    begin
      if Trim(FieldByName('LACRES').AsString) <> '' then
        FieldByName('LACRES').AsString := FieldByName('LACRES').AsString + '/';
      FieldByName('LACRES').AsString := FieldByName('LACRES').AsString + CTe.Rodo.Lacres.Items[i].nLacre;
    end;

    Post;
  end;

  with cdsRodoVeiculos do
  begin
    Close;
    FieldDefs.Clear;
    FieldDefs.Add('tpVeic', ftString, 10);
    FieldDefs.Add('placa', ftString, 7);
    FieldDefs.Add('UF', ftString, 2);
    FieldDefs.Add('RNTRC', ftString, 8);
    CreateDataSet;
    for i := 0 to CTe.Rodo.veic.Count - 1 do
    begin
      Append;
      case CTe.Rodo.veic.Items[i].tpVeic of
        tvTracao: FieldByName('tpVeic').AsString := 'Tra��o';
        tvReboque: FieldByName('tpVeic').AsString := 'Reboque';
      end;
      FieldByName('placa').AsString := CTe.Rodo.veic.Items[i].placa;
      FieldByName('UF').AsString := CTe.Rodo.veic.Items[i].UF;
      FieldByName('RNTRC').AsString := CTe.Rodo.veic.Items[i].Prop.RNTRC;
      Post;
    end;
  end;

  with cdsRodoValePedagio do
  begin
    Close;
    FieldDefs.Clear;
    FieldDefs.Add('CNPJPg', ftString, 18);
    FieldDefs.Add('CNPJForn', ftString, 18);
    FieldDefs.Add('nCompra', ftString, 14);
    CreateDataSet;

{$IFDEF PL_104}
    for i := 0 to CTe.Rodo.valePed.Count - 1 do
    begin
      Append;
      FieldByName('CNPJForn').AsString := DFeUtil.FormatarCNPJ(CTe.Rodo.valePed.Items[i].CNPJForn);
      FieldByName('CNPJPg').AsString := DFeUtil.FormatarCNPJ(CTe.Rodo.valePed.Items[i].CNPJPg);
      FieldByName('nCompra').AsString := CTe.Rodo.valePed.Items[i].nCompra;
      Post;
    end;
{$ENDIF}
  end;

  with cdsRodoMotorista do
  begin
    Close;
    FieldDefs.Clear;
    FieldDefs.Add('xNome', ftString, 60);
    FieldDefs.Add('CPF', ftString, 11);
    CreateDataSet;

    for i := 0 to CTe.Rodo.moto.Count - 1 do
    begin
      Append;
      FieldByName('xNome').AsString := CTe.Rodo.moto.Items[i].xNome;
      FieldByName('CPF').AsString := CTe.Rodo.moto.Items[i].CPF;
      Post;
    end;
  end;
end;

procedure TdmACBrCTeFR.CarregaParametros;
var
  vChave_Contingencia: string;
  vResumo: string;
begin
  { par�metros }
  with cdsParametros do
  begin
    Close;
    FieldDefs.Clear;
    FieldDefs.Add('ResumoCanhoto', ftString, 200);
    FieldDefs.Add('Mensagem0', ftString, 60);
    FieldDefs.Add('Versao', ftString, 5);
    FieldDefs.Add('Imagem', ftString, 256);
    FieldDefs.Add('Sistema', ftString, 60);
    FieldDefs.Add('Usuario', ftString, 60);
    FieldDefs.Add('Fax', ftString, 60);
    FieldDefs.Add('Site', ftString, 60);
    FieldDefs.Add('Email', ftString, 60);
    FieldDefs.Add('Desconto', ftString, 60);
    FieldDefs.Add('ChaveAcesso_Descricao', ftString, 90);
    FieldDefs.Add('Contingencia_ID', ftString, 36);
    FieldDefs.Add('Contingencia_Descricao', ftString, 60);
    FieldDefs.Add('Contingencia_Valor', ftString, 60);
    FieldDefs.Add('LinhasPorPagina', ftInteger);

    CreateDataSet;
    Append;

    vResumo := '';
    //    if DACTEClassOwner.ExibirResumoCanhoto then
    //    begin
    //       if NotaUtil.EstaVazio(DANFEClassOwner.ExibirResumoCanhoto_Texto) then
    //          vResumo := 'Emiss�o: ' + NotaUtil.FormatDate(DateToStr(FNFe.Ide.DEmi)) + '  Dest/Reme: ' + FNFe.Dest.XNome + '  Valor Total: ' + NotaUtil.FormatFloat(FNFe.Total.ICMSTot.VNF)
    //       else
    //          vResumo := DANFEClassOwner.ExibirResumoCanhoto_Texto;
    //    end;
    FieldByName('ResumoCanhoto').AsString := vResumo;
{$IFDEF PL_103}
    FieldByName('Versao').AsString := '1.03';
{$ENDIF}
{$IFDEF PL_104}
    FieldByName('Versao').AsString := '1.04';
{$ENDIF}
    if (FCTe.Ide.TpAmb = taHomologacao) then
      FieldByName('Mensagem0').AsString := 'CTe sem Valor Fiscal - HOMOLOGA��O'
    else
    begin
      if not (FCTe.Ide.tpEmis in [teContingencia, teFSDA]) then
      begin
        if ((DFeUtil.EstaVazio(FDACTEClassOwner.ProtocoloCTE)) and
          (DFeUtil.EstaVazio(FCTe.procCTe.nProt))) then
          FieldByName('Mensagem0').AsString := 'CTe sem Autoriza��o de Uso da SEFAZ'
        else
          if (not ((DFeUtil.EstaVazio(FDACTEClassOwner.ProtocoloCTE)) and
            (DFeUtil.EstaVazio(FCTe.procCTe.nProt)))) and
            (FCTe.procCTe.cStat = 101) then
            FieldByName('Mensagem0').AsString := 'CTe Cancelada'
          else
          begin
            if FDACTEClassOwner.CTeCancelada then
              FieldByName('Mensagem0').AsString := 'CTe Cancelada'
            else
              FieldByName('Mensagem0').AsString := '';
          end;
      end
      else
        FieldByName('Mensagem0').AsString := '';
    end;

    // Carregamento da imagem
    if DACTEClassOwner.Logo <> '' then
      FieldByName('Imagem').AsString := DACTEClassOwner.Logo;

    if FDACTEClassOwner.Sistema <> '' then
      FieldByName('Sistema').AsString := FDACTEClassOwner.Sistema
    else
      FieldByName('Sistema').AsString := 'Projeto ACBr - http://acbr.sf.net';

    if FDACTEClassOwner.Usuario <> '' then
      FieldByName('Usuario').AsString := ' - ' + FDACTEClassOwner.Usuario
    else
      FieldByName('Usuario').AsString := '';

    if FDACTEClassOwner.Fax <> '' then
      FieldByName('Fax').AsString := ' - FAX ' + FDACTEClassOwner.Fax
    else
      FieldByName('Fax').AsString := '';

    FieldByName('Site').AsString := FDACTEClassOwner.Site;
    FieldByName('Email').AsString := FDACTEClassOwner.Email;

    if FDACTEClassOwner.ImprimirDescPorc then
      FieldByName('Desconto').AsString := 'DESC %'
    else
      FieldByName('Desconto').AsString := 'V.DESC.';

    if ((FCTe.Ide.tpEmis = teNormal) or (FCTe.Ide.tpEmis = teSCAN)) then
    begin
      FieldByName('ChaveAcesso_Descricao').AsString := 'CHAVE DE ACESSO';
      FieldByName('Contingencia_ID').AsString := '';

      if ((FDACTEClassOwner.CTeCancelada) or (FCTe.procCTe.cStat = 101)) then
        FieldByName('Contingencia_Descricao').AsString := 'PROTOCOLO DE HOMOLOGA��O DO CANCELAMENTO'
      else
        FieldByName('Contingencia_Descricao').AsString := 'PROTOCOLO DE AUTORIZA��O DE USO';

      if DFeUtil.EstaVazio(FDACTEClassOwner.ProtocoloCTE) then
      begin
        if not (FCTe.Ide.tpEmis in [teContingencia, teFSDA]) and DFeUtil.EstaVazio(FCTe.procCTe.nProt) then
          FieldByName('Contingencia_Valor').AsString := 'CTe sem Autoriza��o de Uso da SEFAZ'
        else
          FieldByName('Contingencia_Valor').AsString := FCTe.procCTe.nProt + ' ' + DFeUtil.SeSenao(FCTe.procCTe.dhRecbto <> 0,
            DateTimeToStr(FCTe.procCTe.dhRecbto), '');
      end
      else
        FieldByName('Contingencia_Valor').AsString := FDACTEClassOwner.ProtocoloCTE;
    end
    else
    begin
      vChave_Contingencia := CTeUtil.GerarChaveContingencia(FCTe);
      FieldByName('ChaveAcesso_Descricao').AsString := 'CHAVE DE ACESSO';
      FieldByName('Contingencia_ID').AsString := vChave_Contingencia;

      if ((FCTe.Ide.tpEmis = teContingencia) or (FCTe.Ide.tpEmis = teFSDA)) then
      begin
        FieldByName('Contingencia_Descricao').AsString := 'DADOS DA CT-E';
        FieldByName('Contingencia_Valor').AsString := CTeUtil.FormatarChaveContingencia(vChave_Contingencia);
      end
      else
        if (FCTe.Ide.tpEmis = teDPEC) then
        begin
          FieldByName('Contingencia_Descricao').AsString := 'N�MERO DE REGISTRO DPEC';

          //precisa testar
  //        if DFeUtil.EstaVazio(FDACTEClassOwner.ProtocoloCTE) then
  //          raise EACBrCTeException.Create('Protocolo de Registro no DPEC n�o informado.')
  //        else
  //          FieldByName('Contingencia_Valor').AsString := FDACTEClassOwner.ProtocoloCTe;
        end;
    end;

    //    FieldByName('LinhasPorPagina').AsInteger := FDACTEClassOwner.ProdutosPorPagina;
    Post;
  end;
end;

procedure TdmACBrCTeFR.CarregaRecebedor;
begin
  { Recebedor }
  with cdsRecebedor do
  begin
    Close;
    FieldDefs.Clear;
    FieldDefs.Add('CNPJ', ftString, 18);
    FieldDefs.Add('XNome', ftString, 60);
    FieldDefs.Add('XFant', ftString, 60);
    FieldDefs.Add('XLgr', ftString, 60);
    FieldDefs.Add('Nro', ftString, 60);
    FieldDefs.Add('XCpl', ftString, 60);
    FieldDefs.Add('XBairro', ftString, 60);
    FieldDefs.Add('CMun', ftString, 7);
    FieldDefs.Add('XMun', ftString, 60);
    FieldDefs.Add('UF', ftString, 2);
    FieldDefs.Add('CEP', ftString, 9);
    FieldDefs.Add('CPais', ftString, 4);
    FieldDefs.Add('XPais', ftString, 60);
    FieldDefs.Add('Fone', ftString, 15);
    FieldDefs.Add('IE', ftString, 14);
    FieldDefs.Add('IM', ftString, 15);
    FieldDefs.Add('IEST', ftString, 15);
    FieldDefs.Add('CRT', ftString, 1);

    CreateDataSet;
    Append;

    with FCTE.Receb do
    begin
      FieldByName('CNPJ').AsString := DFeUtil.FormatarCNPJ(CNPJCPF);
      FieldByName('XNome').AsString := XNome;
      with EnderReceb do
      begin
        FieldByName('Xlgr').AsString := XLgr;
        FieldByName('Nro').AsString := Nro;
        FieldByName('XCpl').AsString := XCpl;
        FieldByName('XBairro').AsString := XBairro;
        FieldByName('CMun').AsString := IntToStr(CMun);
        FieldByName('XMun').AsString := CollateBr(XMun);
        FieldByName('UF').AsString := UF;
        FieldByName('CEP').AsString := DFeUtil.FormatarCEP(DFeUtil.Poem_Zeros(CEP, 8));
        FieldByName('CPais').AsString := IntToStr(CPais);
        FieldByName('XPais').AsString := XPais;
        FieldByName('Fone').AsString := DFeUtil.FormatarFone(Fone);
      end;
      FieldByName('IE').AsString := IE;
    end;

    Post;
  end;

end;

procedure TdmACBrCTeFR.CarregaRemetente;
begin
  { Remetente }
  with cdsRemetente do
  begin
    Close;
    FieldDefs.Clear;
    FieldDefs.Add('CNPJ', ftString, 18);
    FieldDefs.Add('XNome', ftString, 60);
    FieldDefs.Add('XFant', ftString, 60);
    FieldDefs.Add('XLgr', ftString, 60);
    FieldDefs.Add('Nro', ftString, 60);
    FieldDefs.Add('XCpl', ftString, 60);
    FieldDefs.Add('XBairro', ftString, 60);
    FieldDefs.Add('CMun', ftString, 7);
    FieldDefs.Add('XMun', ftString, 60);
    FieldDefs.Add('UF', ftString, 2);
    FieldDefs.Add('CEP', ftString, 9);
    FieldDefs.Add('CPais', ftString, 4);
    FieldDefs.Add('XPais', ftString, 60);
    FieldDefs.Add('Fone', ftString, 15);
    FieldDefs.Add('IE', ftString, 14);
    FieldDefs.Add('IM', ftString, 15);
    FieldDefs.Add('IEST', ftString, 15);
    FieldDefs.Add('CRT', ftString, 1);

    CreateDataSet;
    Append;
    with FCTE.Rem do
    begin
      FieldByName('CNPJ').AsString := DFeUtil.FormatarCNPJ(CNPJCPF);
      FieldByName('XNome').AsString := XNome;
      FieldByName('XFant').AsString := XFant;
      with EnderReme do
      begin
        FieldByName('Xlgr').AsString := XLgr;
        FieldByName('Nro').AsString := Nro;
        FieldByName('XCpl').AsString := XCpl;
        FieldByName('XBairro').AsString := XBairro;
        FieldByName('CMun').AsString := IntToStr(CMun);
        FieldByName('XMun').AsString := CollateBr(XMun);
        FieldByName('UF').AsString := UF;
        FieldByName('CEP').AsString := DFeUtil.FormatarCEP(DFeUtil.Poem_Zeros(CEP, 8));
        FieldByName('CPais').AsString := IntToStr(CPais);
        FieldByName('XPais').AsString := XPais;
        FieldByName('Fone').AsString := DFeUtil.FormatarFone(Fone);
      end;
      FieldByName('IE').AsString := IE;
    end;
    Post;
  end;
end;

procedure TdmACBrCTeFR.CarregaSeguro;
var
  i: Integer;
begin
  with cdsSeguro do
  begin
    Close;
    FieldDefs.Clear;
    FieldDefs.Add('RESPONSAVEL', ftString, 60);
    FieldDefs.Add('NOMESEGURADORA', ftString, 60);
    FieldDefs.Add('NUMEROAPOLICE', ftString, 60);
    FieldDefs.Add('NUMEROAVERBACAO', ftString, 60);
    CreateDataSet;
    Append;
    //
    if CTe.InfSeg.Count > 0 then
    begin
      for I := 0 to CTe.InfSeg.Count - 1 do
      begin
        case CTe.InfSeg.Items[I].respSeg of
          rsRemetente: FieldByName('RESPONSAVEL').AsString := 'Remetente';
          rsExpedidor: FieldByName('RESPONSAVEL').AsString := 'Expedidor';
          rsRecebedor: FieldByName('RESPONSAVEL').AsString := 'Recebedor';
          rsDestinatario: FieldByName('RESPONSAVEL').AsString := 'Destinat�rio';
          rsEmitenteCTe: FieldByName('RESPONSAVEL').AsString := 'Emitente';
          rsTomadorServico: FieldByName('RESPONSAVEL').AsString := 'Tomador';
        end;
        FieldByName('NOMESEGURADORA').AsString := CTe.InfSeg.Items[I].xSeg;
        FieldByName('NUMEROAPOLICE').AsString := CTe.InfSeg.Items[I].nApol;
        FieldByName('NUMEROAVERBACAO').AsString := CTe.InfSeg.Items[I].nAver;
        Post;
      end;
    end
    else
    begin
      FieldByName('RESPONSAVEL').AsString := '';
      FieldByName('NOMESEGURADORA').AsString := '';
      FieldByName('NUMEROAPOLICE').AsString := '';
      FieldByName('NUMEROAVERBACAO').AsString := '';
      Post;
    end;

  end;

end;

procedure TdmACBrCTeFR.CarregaTomador;
begin
  { Tomador Outros }
  with cdsTomador do
  begin
    Close;
    FieldDefs.Clear;
    FieldDefs.Add('CNPJ', ftString, 18);
    FieldDefs.Add('IE', ftString, 14);
    FieldDefs.Add('XNome', ftString, 60);
    FieldDefs.Add('XFant', ftString, 60);
    FieldDefs.Add('Fone', ftString, 12);
    FieldDefs.Add('XLgr', ftString, 255);
    FieldDefs.Add('Nro', ftString, 60);
    FieldDefs.Add('XCpl', ftString, 60);
    FieldDefs.Add('XBairro', ftString, 60);
    FieldDefs.Add('CMun', ftString, 7);
    FieldDefs.Add('XMun', ftString, 60);
    FieldDefs.Add('UF', ftString, 2);
    FieldDefs.Add('CEP', ftString, 9);
    FieldDefs.Add('CPais', ftString, 4);
    FieldDefs.Add('XPais', ftString, 60);
    CreateDataSet;
    Append;

    case FCTe.Ide.Toma03.Toma of
      tmRemetente:
        begin
          FieldByName('CNPJ').AsString := FCTe.Rem.CNPJCPF;
          FieldByName('XNome').AsString := FCTe.Rem.xNome;
          FieldByName('XFant').AsString := FCTe.Rem.xFant;
          FieldByName('IE').AsString := FCTe.Rem.IE;
          //
          FieldByName('Xlgr').AsString := FCTe.Rem.EnderReme.xLgr;
          FieldByName('Nro').AsString := FCTe.Rem.EnderReme.nro;
          FieldByName('XCpl').AsString := FCTe.Rem.EnderReme.xCpl;
          FieldByName('XBairro').AsString := FCTe.Rem.EnderReme.xBairro;
          FieldByName('CMun').AsString := IntToStr(FCTe.Rem.EnderReme.cMun);
          FieldByName('XMun').AsString := FCTe.Rem.EnderReme.xMun;
          FieldByName('UF').AsString := FCTe.Rem.EnderReme.UF;
          FieldByName('CEP').AsString := IntToStr(FCTe.Rem.EnderReme.CEP);
          FieldByName('CPais').AsString := IntToStr(FCTe.Rem.EnderReme.cPais);
          FieldByName('XPais').AsString := FCTe.Rem.EnderReme.xPais;
          FieldByName('Fone').AsString := '';
        end;

      tmDestinatario:
        begin
          FieldByName('CNPJ').AsString := FCTe.Dest.CNPJCPF;
          FieldByName('XNome').AsString := FCTe.Dest.xNome;
          FieldByName('IE').AsString := FCTe.Dest.IE;
          //
          FieldByName('Xlgr').AsString := FCTe.Dest.EnderDest.xLgr;
          FieldByName('XCpl').AsString := FCTe.Dest.EnderDest.xCpl;
          FieldByName('XBairro').AsString := FCTe.Dest.EnderDest.xBairro;
          FieldByName('CMun').AsString := IntToStr(FCTe.Dest.EnderDest.cMun);
          FieldByName('XMun').AsString := FCTe.Dest.EnderDest.xMun;
          FieldByName('UF').AsString := FCTe.Dest.EnderDest.UF;
          FieldByName('CEP').AsString := IntToStr(FCTe.Dest.EnderDest.CEP);
          FieldByName('CPais').AsString := IntToStr(FCTe.Dest.EnderDest.cPais);
          FieldByName('XPais').AsString := FCTe.Dest.EnderDest.xPais;
          FieldByName('Fone').AsString := '';
        end;

      tmExpedidor:
        begin
          FieldByName('CNPJ').AsString := FCTe.Exped.CNPJCPF;
          FieldByName('XNome').AsString := FCTe.Exped.xNome;
          FieldByName('IE').AsString := FCTe.Exped.IE;
          //
          FieldByName('Xlgr').AsString := FCTe.Exped.EnderExped.xLgr;
          FieldByName('XCpl').AsString := FCTe.Exped.EnderExped.xCpl;
          FieldByName('XBairro').AsString := FCTe.Exped.EnderExped.xBairro;
          FieldByName('CMun').AsString := IntToStr(FCTe.Exped.EnderExped.cMun);
          FieldByName('XMun').AsString := FCTe.Exped.EnderExped.xMun;
          FieldByName('UF').AsString := FCTe.Exped.EnderExped.UF;
          FieldByName('CEP').AsString := IntToStr(FCTe.Exped.EnderExped.CEP);
          FieldByName('CPais').AsString := IntToStr(FCTe.Exped.EnderExped.cPais);
          FieldByName('XPais').AsString := FCTe.Exped.EnderExped.xPais;
          FieldByName('Fone').AsString := '';
        end;

      tmRecebedor:
        begin
          FieldByName('CNPJ').AsString := FCTe.Receb.CNPJCPF;
          FieldByName('XNome').AsString := FCTe.Receb.xNome;
          FieldByName('IE').AsString := FCTe.Receb.IE;
          //
          FieldByName('Xlgr').AsString := FCTe.Receb.EnderReceb.xLgr;
          FieldByName('XCpl').AsString := FCTe.Receb.EnderReceb.xCpl;
          FieldByName('XBairro').AsString := FCTe.Receb.EnderReceb.xBairro;
          FieldByName('CMun').AsString := IntToStr(FCTe.Receb.EnderReceb.cMun);
          FieldByName('XMun').AsString := FCTe.Receb.EnderReceb.xMun;
          FieldByName('UF').AsString := FCTe.Receb.EnderReceb.UF;
          FieldByName('CEP').AsString := IntToStr(FCTe.Receb.EnderReceb.CEP);
          FieldByName('CPais').AsString := IntToStr(FCTe.Receb.EnderReceb.cPais);
          FieldByName('XPais').AsString := FCTe.Receb.EnderReceb.xPais;
          FieldByName('Fone').AsString := '';
        end;
    end;

    case FCTe.Ide.Toma4.Toma of
      tmOutros:
        begin
          FieldByName('CNPJ').AsString := FCTe.Ide.Toma4.CNPJCPF;
          FieldByName('XNome').AsString := FCTe.Ide.Toma4.xNome;
          FieldByName('IE').AsString := FCTe.Ide.Toma4.IE;
          //
          FieldByName('Xlgr').AsString := FCTe.Ide.Toma4.EnderToma.xLgr;
          FieldByName('XCpl').AsString := FCTe.Ide.Toma4.EnderToma.xCpl;
          FieldByName('XBairro').AsString := FCTe.Ide.Toma4.EnderToma.xBairro;
          FieldByName('CMun').AsString := IntToStr(FCTe.Ide.Toma4.EnderToma.cMun);
          FieldByName('XMun').AsString := FCTe.Ide.Toma4.EnderToma.xMun;
          FieldByName('UF').AsString := FCTe.Ide.Toma4.EnderToma.UF;
          FieldByName('CEP').AsString := IntToStr(FCTe.Ide.Toma4.EnderToma.CEP);
          FieldByName('CPais').AsString := IntToStr(FCTe.Ide.Toma4.EnderToma.cPais);
          FieldByName('XPais').AsString := FCTe.Ide.Toma4.EnderToma.xPais;
          FieldByName('Fone').AsString := '';
        end;
    end;
    Post;
  end;

end;

procedure TdmACBrCTeFR.CarregaVolumes;
var
  I, J: Integer;
  MCub, Volumes, VlrServico: Currency;
  ProdutoPred, OutrasCaract: string;
  TipoMedida: array of string;
  UnidMedida: array of string;
  QdtMedida: array of currency;
begin
  with cdsVolumes do
  begin
    Close;
    FieldDefs.Clear;

    FieldDefs.Add('Produto', ftString, 100);
    FieldDefs.Add('CaracteristicaCarga', ftString, 100);
    FieldDefs.Add('ValorServico', ftFloat);

    FieldDefs.Add('DescTipo', ftString, 60);
    FieldDefs.Add('UnMedida', ftString, 6);
    FieldDefs.Add('QMedida', ftFloat);
    FieldDefs.Add('MCub', ftFloat);
    FieldDefs.Add('QVol', ftFloat);

    CreateDataSet;
    j := 0;
    VlrServico := 0;
    MCub := 0;
    Volumes := 0;
    for I := 0 to CTe.InfCarga.InfQ.Count - 1 do
    begin
      ProdutoPred := CTe.InfCarga.proPred;
      OutrasCaract := CTe.InfCarga.xOutCat;
{$IFDEF PL_103}
      VlrServico := CTe.InfCarga.vMerc;
{$ENDIF}
{$IFDEF PL_104}
      VlrServico := CTe.InfCarga.vCarga;
{$ENDIF}
      case CTe.InfCarga.InfQ.Items[I].cUnid of
        uM3: MCub := CTe.InfCarga.InfQ.Items[I].qCarga;
        uUNIDADE: Volumes := CTe.InfCarga.InfQ.Items[I].qCarga;
        else
          begin
            Inc(J);
            SetLength(TipoMedida, j);
            SetLength(UnidMedida, j);
            SetLength(QdtMedida, j);
            TipoMedida[J - 1] := CTe.InfCarga.InfQ.Items[I].tpMed;
            QdtMedida[J - 1] := CTe.InfCarga.InfQ.Items[I].qCarga;
            case CTe.InfCarga.InfQ.Items[I].cUnid of
              uKG: UnidMedida[J - 1] := 'KG';
              uTON: UnidMedida[J - 1] := 'TON';
              uLITROS: UnidMedida[J - 1] := 'LT';
              uMMBTU: UnidMedida[J - 1] := 'MMBTU';
            end;
          end;
      end;
    end;
    for I := 0 to j - 1 do
    begin
      Append;
      FieldByName('Produto').AsString := ProdutoPred;
      FieldByName('CaracteristicaCarga').AsString := OutrasCaract;
      FieldByName('ValorServico').AsFloat := VlrServico;
      FieldByName('MCub').AsFloat := MCub;
      FieldByName('QVol').AsFloat := Volumes;
      FieldByName('UnMedida').AsString := UnidMedida[i]; 
      FieldByName('DescTipo').AsString := TipoMedida[i];
      FieldByName('QMedida').AsFloat := QdtMedida[i];
      Post;
    end;  
  end;
end;

constructor TdmACBrCTeFR.Create(AOwner: TComponent);
begin
  inherited;
  FDACTEClassOwner := TACBrCteDACTEClass(AOwner);
end;

end.

