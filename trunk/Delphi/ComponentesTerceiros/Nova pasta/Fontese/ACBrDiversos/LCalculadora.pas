{******************************************************************************}
{ Projeto: Componentes ACBr                                                    }
{  Biblioteca multiplataforma de componentes Delphi para intera��o com equipa- }
{ mentos de Automa��o Comercial utilizados no Brasil                           }
{                                                                              }
{ Direitos Autorais Reservados (c) 2004 Daniel Simoes de Almeida               }
{                                                                              }
{ Colaboradores nesse arquivo:                                                 }
{                                                                              }
{  Voc� pode obter a �ltima vers�o desse arquivo na pagina do  Projeto ACBr    }
{ Componentes localizado em      http://www.sourceforge.net/projects/acbr      }
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

{$I ACBr.inc}

unit LCalculadora;

interface

uses
  ACBrCalculadora,          
  Classes, SysUtils, FileUtil, LResources, Forms, Controls, Graphics, Dialogs,
  Menus, StdCtrls, ExtCtrls, Buttons ;

type

  { TFrCalculadora }

  TFrCalculadora = class(TForm)
    b1: TButton;
    b0: TButton;
    b2: TButton;
    b3: TButton;
    b4: TButton;
    b5: TButton;
    b6: TButton;
    b7: TButton;
    b8: TButton;
    b9: TButton;
    Bevel1 : TBevel;
    pValor : TLabel;
    bponto: TButton;
    bigual: TButton;
    bmais: TButton;
    bmenos: TButton;
    bmulti: TButton;
    bdiv: TButton;
    bapaga: TButton;
    bc: TButton;
    bce: TButton;
    bporc: TButton;
    mBobina: TMemo;
    PopupMenu1: TPopupMenu;
    Limpar1: TMenuItem;
    Salvar1: TMenuItem;
    SaveDialog1: TSaveDialog;
    Copias1: TMenuItem;
    procedure ExecOnCalcKey(Sender : TObject; Key : Char ) ;
    procedure ExecOnDisplayChange(Sender : TObject ) ;
    procedure ZeraDisplay( Sender : TObject ) ;
    
    procedure b1Click(Sender: TObject);
    procedure FormKeyPress(Sender: TObject; var Key: Char);
    procedure bapagaClick(Sender: TObject);
    procedure bpontoClick(Sender: TObject);
    procedure bcClick(Sender: TObject);
    procedure b0Click(Sender: TObject);
    procedure bceClick(Sender: TObject);
    procedure AcaoClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure Salvar1Click(Sender: TObject);
    procedure bporcClick(Sender: TObject);
    procedure Copiar1Click(Sender: TObject);
    procedure FormKeyUp(Sender: TObject; var Key: Word;
      Shift: TShiftState);
  private
    { Private declarations }
    fValor : Double ;
    fOperacao : String ;

    function GetValorDisplay: String;
    procedure SetValorDisplay(const Value: String);
  public
    { Public declarations }
    pPrecisao  : Integer ;
    pSaiComEsc : Boolean ;
    pOnCalKey  : TKeyPressEvent ; 
    pOnDisplayChange : TACBrCalculadoraDisplayChange ;

    Property ValorDisplay : String read GetValorDisplay write SetValorDisplay ;
  end;

var
  FrCalculadora: TFrCalculadora;

implementation

uses ACBrUtil, Math, ACBrConsts;

{$R *.lfm}

{$I incCalculadora.pas}

end.
