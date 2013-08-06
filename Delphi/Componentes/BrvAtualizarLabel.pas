unit BrvAtualizarLabel;

interface

uses Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, stdctrls,
     dbctrls;

type
  TBrvAtualizarLabel = class(TComponent)
  private
    { Private declarations }
    FExecutar : Boolean;
  protected
    { Protected declarations }
  public
    { Public declarations }
    constructor Create(AOwner: TComponent); override;
    procedure Execute(Sender: TObject);
  published
    { Published declarations }
    property Executar : Boolean read FExecutar write FExecutar default True;
  end;

procedure Register;

implementation

constructor TBrvAtualizarLabel.Create(AOwner: TComponent);
begin
   inherited Create(AOwner);
   Executar := True;
end;

procedure TBrvAtualizarLabel.Execute(Sender: TObject);
var i: Integer;
begin
      if FExecutar then
      begin
            with (Sender as TComponent) do
            begin
                  for i := 0 to ComponentCount - 1 do
                  begin
                        if (Components[i] is TLabel) then
                        begin
                              if (((Components[i] as TLabel).FocusControl) is TDBEdit) 
                              then begin
                                  (Components[i] as TLabel).Caption :=
                                    (((Components[i] as TLabel).FocusControl) as TDBEdit).
                                   DataSource.DataSet.FindField((((Components[i] as TLabel).
                                   FocusControl) as TDBEdit).DataField).DisplayLabel;
                               end else begin
                               if (((Components[i] as TLabel).FocusControl) is TDBMemo)
                               then begin
                                  (Components[i] as TLabel).Caption :=
                                   (((Components[i] as TLabel).FocusControl) as TDBMemo).
                                   DataSource.DataSet.FindField((((Components[i] as TLabel).
                                   FocusControl) as TDBMemo).DataField).DisplayLabel;
                               end else begin
                               if (((Components[i] as TLabel).FocusControl) is TDBImage)
                               then begin
                                  (Components[i] as TLabel).Caption :=
                                   (((Components[i] as TLabel).FocusControl) as TDBImage).
                                   DataSource.DataSet.FindField((((Components[i] as TLabel).
                                   FocusControl) as TDBImage).DataField).DisplayLabel;
                               end else begin
                               if (((Components[i] as TLabel).FocusControl) is TDBListBox)
                               then begin
                                  (Components[i] as TLabel).Caption :=
                                   (((Components[i] as TLabel).FocusControl) as TDBListBox).
                                   DataSource.DataSet.FindField((((Components[i] as TLabel).
                                   FocusControl) as TDBListBox).DataField).DisplayLabel;
                               end else begin
                               if (((Components[i] as TLabel).FocusControl) is TDBComboBox)
                               then begin
                                  (Components[i] as TLabel).Caption :=
                                   (((Components[i] as TLabel).FocusControl) as TDBComboBox).
                                   DataSource.DataSet.FindField((((Components[i] as TLabel).
                                   FocusControl) as TDBComboBox).DataField).DisplayLabel;
                               end else begin
                               if (((Components[i] as TLabel).FocusControl) is TDBCheckBox)
                              then begin
                                  (Components[i] as TLabel).Caption :=
                                   (((Components[i] as TLabel).FocusControl) as TDBCheckBox).
                                  DataSource.DataSet.FindField((((Components[i] as TLabel).
                                   FocusControl) as TDBCheckBox).DataField).DisplayLabel;
                               end else begin
                               if (((Components[i] as TLabel).FocusControl) is TDBRadioGroup)
                               then begin
                                  (Components[i] as TLabel).Caption :=
                                   (((Components[i] as TLabel).FocusControl) as TDBRadioGroup).
                                   DataSource.DataSet.FindField((((Components[i] as TLabel).
                                   FocusControl) as TDBRadioGroup).DataField).DisplayLabel;
                               end else begin
                               if (((Components[i] as TLabel).FocusControl) is TDBLookupListBox)
                               then begin
                                  (Components[i] as TLabel).Caption :=
                                   (((Components[i] as TLabel).FocusControl) as TDBLookupListBox).
                                   DataSource.DataSet.FindField((((Components[i] as TLabel).
                                   FocusControl) as TDBLookupListBox).DataField).DisplayLabel;
                               end else begin
                               if (((Components[i] as TLabel).FocusControl) is TDBLookupComboBox)
                               then begin
                                  (Components[i] as TLabel).Caption :=
                                  (((Components[i] as TLabel).FocusControl) as TDBLookupComboBox).
                                   DataSource.DataSet.FindField((((Components[i] as TLabel).
                                   FocusControl) as TDBLookupComboBox).DataField).DisplayLabel;
                               end else begin
                               if (((Components[i] as TLabel).FocusControl) is TDBRichEdit)
                               then begin
                                  (Components[i] as TLabel).Caption :=
                                   (((Components[i] as TLabel).FocusControl) as TDBRichEdit).
                                   DataSource.DataSet.FindField((((Components[i] as TLabel).
                                   FocusControl) as TDBRichEdit).DataField).DisplayLabel;
                               end; end; end; end; end; end; end; end; end; end;
                            end else begin
                               if (Components[i] is TDBCheckBox)
                               then begin
                                  (Components[i] as TDBCheckBox).Caption :=
                                   (Components[i] as TDBCheckBox).DataSource.DataSet.
                                   FindField((Components[i] as TDBCheckBox).DataField).DisplayLabel;
                               end else begin
                                  if (Components[i] is TDBRadioGroup)
                                  then begin
                                     (Components[i] as TDBRadioGroup).Caption :=
                                      (Components[i] as TDBRadioGroup).DataSource.DataSet.
                                      FindField((Components[i] as TDBRadioGroup).DataField).DisplayLabel;
                                  end;
                               end;
                        end;
                  end;
            end;
      end;    
end;

procedure Register;
begin
  RegisterComponents('Bravo Utils', [TBrvAtualizarLabel]);
end;

end.
