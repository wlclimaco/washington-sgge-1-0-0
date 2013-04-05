library Project1;

uses
  SysUtils,ACBrNFe,pcnConversao,
  Classes;

{declaração de uma função MaiorValor}

function MaiorValor(chNFe,CNPJ,nProt,xJust:String):String;stdcall;
var
    ACBrNFeSaida: TACBrNFe;
    NrSerCert: String;
begin
try
      ACBrNFeSaida := TACBrNFe.Create(nil);
      NrSerCert:= ACBrNFeSaida.Configuracoes.Certificados.SelecionarCertificado;


          // Visualizar Mensagem
          ACBrNFeSaida.Configuracoes.WebServices.Visualizar := False;
          ACBrNFeSaida.Configuracoes.WebServices.Ambiente := taHomologacao;
          ACBrNFeSaida.Configuracoes.WebServices.UF := 'SP';
          ACBrNFeSaida.EventoNFe.Evento.Clear;
          ACBrNFeSaida.NotasFiscais.Clear;
          with ACBrNFeSaida.EventoNFe.Evento.Add do
           begin
             infEvento.chNFe           := chNFe;
             infEvento.CNPJ            := CNPJ;
             infEvento.dhEvento        := now;
             infEvento.tpEvento        := teCancelamento;
             infEvento.detEvento.xJust := xJust;
             infEvento.detEvento.nProt := nProt;
           end;

          ACBrNFeSaida.EnviarEventoNFe(1);
          result := ACBrNFeSaida.WebServices.EnvEvento.RetornoWS;

finally
      ACBrNFeSaida.Free;
end;

end;

exports
MaiorValor;

Begin

end.

