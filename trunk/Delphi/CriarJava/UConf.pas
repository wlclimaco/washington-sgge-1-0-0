unit UConf;

interface

uses  SysUtils,DBClient, DB,StdCtrls,BrvFuncoesXE;

function criarBcfApplication(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarBclApplication(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarDataAcessApplication(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
function criarValidationApplication(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
implementation

function criarBcfApplication(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
begin
     memo1.Lines.Clear;
     memo1.Lines.Add('');
     memo1.Lines.Add('<!-- ------------------Criado via Sistema '+BrvFuncoesXE.PrimeiraMaiscula(Txt)+'------------------------------------------------------------------------------------------------- -->');
     memo1.Lines.Add('	<bean id="'+Txt+'BCF" class="com.sensus.mlc.'+Txt+'.bcf.impl.'+BrvFuncoesXE.PrimeiraMaiscula(Txt)+'BCFImpl">');
		 memo1.Lines.Add('      <property name="'+Txt+'BCL" ref="'+Txt+'BCLTarget" />');
		 memo1.Lines.Add('      <property name="'+Txt+'ValidationController" ref="'+Txt+'ValidationController" />');
		 memo1.Lines.Add('      <property name="'+Txt+'RequestValidationController" ref="'+Txt+'RequestValidationController" />');
		 memo1.Lines.Add('      <property name="'+Txt+'ListValidationController" ref="'+Txt+'ListValidationController" />  ');
	   memo1.Lines.Add('  </bean>');
     memo1.Lines.Add('');
     memo1.Lines.Add('<!-- --------------------------------------------------------------------------------------------------------------------- -->');
end;
function criarBclApplication(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
begin
     memo1.Lines.Clear;
     memo1.Lines.Add('');
     memo1.Lines.Add('<!-- ------------------Criado via Sistema '+BrvFuncoesXE.PrimeiraMaiscula(Txt)+'------------------------------------------------------------------------------------------------- -->');
     memo1.Lines.Add('<bean id="'+Txt+'BCLTarget" class="com.sensus.mlc.'+Txt+'.bcl.impl.'+BrvFuncoesXE.PrimeiraMaiscula(Txt)+'BCLImpl">');
		 memo1.Lines.Add('     <property name="'+Txt+'DAC" ref="'+Txt+'DACTarget" />');
	   memo1.Lines.Add('  </bean>');
     memo1.Lines.Add('');
     memo1.Lines.Add('<!-- --------------------------------------------------------------------------------------------------------------------- -->');
end;
function criarDataAcessApplication(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
begin
     memo1.Lines.Clear;
     memo1.Lines.Add('');
     memo1.Lines.Add('<!-- ------------------Criado via Sistema '+BrvFuncoesXE.PrimeiraMaiscula(Txt)+'------------------------------------------------------------------------------------------------- -->');
     memo1.Lines.Add('<bean id="'+Txt+'-sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">');
     memo1.Lines.Add('    <property name="configLocation"');
		 memo1.Lines.Add('      value="classpath:com/sensus/mlc/'+Txt+'/dac/mybatis/map/'+Txt+'-sql-map-config.xml" />');
	   memo1.Lines.Add('    <property name="dataSource" ref="mlcDataSource" />');
     memo1.Lines.Add('</bean>');
     memo1.Lines.Add('');
     memo1.Lines.Add('');
     memo1.Lines.Add('<bean id="'+Txt+'DACTarget" class="com.sensus.mlc.dicionario.dac.mybatis.'+BrvFuncoesXE.PrimeiraMaiscula(Txt)+'DACImpl">');
     memo1.Lines.Add('    <property name="sqlSessionFactory" ref="'+Txt+'-sqlSessionFactory" />');
     memo1.Lines.Add('</bean>');
     memo1.Lines.Add('');
     memo1.Lines.Add('<!-- --------------------------------------------------------------------------------------------------------------------- -->');

end;
function criarValidationApplication(Txt:String;CcDataset:TClientDataSet;memo1 :TMemo):TMemo;
begin
      memo1.Lines.Clear;
      memo1.Lines.Add('<!-- ------------------Criado via Sistema '+BrvFuncoesXE.PrimeiraMaiscula(Txt)+'------------------------------------------------------------------------------------------------- -->');
      memo1.Lines.Add('<!-- '+BrvFuncoesXE.PrimeiraMaiscula(Txt)+' Validators -->');
      memo1.Lines.Add('<bean id="'+BrvFuncoesXE.PrimeiraMaiscula(Txt)+'Validator" class="com.sensus.mlc.'+Txt+'.validation.'+BrvFuncoesXE.PrimeiraMaiscula(Txt)+'Validator" />');
      memo1.Lines.Add('<bean id="'+Txt+'ValidationController" class="com.sensus.common.validation.ValidationController">');
      memo1.Lines.Add('	<property name="validators"> ');
      memo1.Lines.Add('		<list>  ');
      memo1.Lines.Add('			<ref bean="'+BrvFuncoesXE.PrimeiraMaiscula(Txt)+'Validator" />   ');
      memo1.Lines.Add('		</list>    ');
      memo1.Lines.Add('	</property>  ');
      memo1.Lines.Add('</bean>    ');
      memo1.Lines.Add('<bean id="'+BrvFuncoesXE.PrimeiraMaiscula(Txt)+'ListValidator" class="com.sensus.mlc.'+Txt+'.validation.'+BrvFuncoesXE.PrimeiraMaiscula(Txt)+'ListValidator" />');
	    memo1.Lines.Add('<bean id="'+Txt+'ListValidationController" class="com.sensus.common.validation.ValidationController">');
		  memo1.Lines.Add('   <property name="validators">');
			memo1.Lines.Add('       <list>');
			memo1.Lines.Add('	            <ref bean="'+BrvFuncoesXE.PrimeiraMaiscula(Txt)+'ListValidator" /> ');
			memo1.Lines.Add('       </list> ');
		  memo1.Lines.Add('   </property>  ');
	    memo1.Lines.Add('</bean> ');
      memo1.Lines.Add('<!-- --------------------------------------------------------------------------------------------------------------------- -->');
end;
end.
