CREATE TABLE [dbo].[status](
(1,1,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,1,'dataStatus','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,1,'parentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,1,'status','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,1,'acaoType','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,1,'tabelaEnum','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,1,'note','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,1,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,1,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,1,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,1,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
CREATE TABLE [dbo].[regime](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'nome','Test',3,200,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'descricao','Test',3,250,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
CREATE TABLE [dbo].[empresa] (
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'nome','Test',3,100,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'regime','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
CREATE TABLE [dbo].[documento](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'tabela','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'parentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'type','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'description','Test',3,200,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'numero','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'data','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'estado','Test',3,2,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),

/** ---------------------------------------------------------------*/
CREATE TABLE [dbo].[email](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'tabela','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'parentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'type','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'email','Test',3,100,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'description','Test',3,200,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[endereco];
CREATE TABLE [dbo].[endereco](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'tabela','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'parentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'type','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'logradouro','Test',3,200,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'cidade','Test',3,100,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'estado','Test',3,2,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'bairro','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'numero','Test',3,10,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'cep','Test',3,15,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[telefone];
CREATE TABLE [dbo].[telefone] (
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'type','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'parentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'tabela','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'ddd','Test',3,5,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'telefone','Test',3,15,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[socio];
CREATE TABLE [dbo].[socio](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'parentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'nome','Test',3,200,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'cota]         real NULL,
(1,2,'porcentagem]  real NULL,
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[historico];
CREATE TABLE [dbo].[historico] (
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'parentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'tabela','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'type','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'acao','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'registro','Test',3,200,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'data','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'usuario','Test',3,20,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'empresa','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'status','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
CREATE TABLE [dbo].[CNAE] (
(1,2,'CODIGO','Test',3,6,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'CNAE','Test',3,10,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'DESCRICAO','Test',3,100,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'ABREVIADO','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'status','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
CREATE TABLE [dbo].[CSOSN] (
(1,2,'CODIGO','Test',3,3,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'DESCRICAO','Test',3,200,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'status','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
CREATE TABLE [dbo].[NCM] (
(1,2,'NCM','Test',3,10,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'DESCRICAO','Test',3,100,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'UNIDADE','Test',3,2,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'status','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** --------------------------------------------------------------- */
DROP TABLE  [dbo].[CIDADE];

CREATE TABLE [dbo].[CIDADE](
(1,2,'CODIGO','Test',3,6,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'CIDADE','Test',3,40,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'UF','Test',3,2,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'CEP','Test',3,10,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'IBGE','Test',3,10,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'ESTADO','Test',3,2,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'MUNICIPIO','Test',3,10,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'status','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),

/** --------------------------------------------------------------- */
DROP TABLE  [dbo].[cnaePorRelacionamento];
CREATE TABLE [dbo].[cnaePorRelacionamento](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'idCnae','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'idParentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'tabela','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[funcionario];
CREATE TABLE [dbo].[funcionario](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'cdEmpr','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'matricula','Test',3,20,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'nome','Test',3,200,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'sexo','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'dataAdm','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[horarios];
CREATE TABLE [dbo].[horarios](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'parentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'data','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'horarioEntr','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'horarioSair','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'tipo','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[Salario];
CREATE TABLE [dbo].[Salario](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'parentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'valor]        [real] NULL,
(1,2,'data','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[Beneficio];
CREATE TABLE [dbo].[Beneficio](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'nome','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'codigo','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'descricao','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'valor]        [real] NULL,
(1,2,'porcentagem]  [real] NULL,
(1,2,'tipo','Test',3,10,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[BeneficioFunc];
CREATE TABLE [dbo].[BeneficioFunc](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'idFunc','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'idBenef','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
CREATE TABLE [dbo].[BeneficioMesApp](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'data','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'idFuncBenef','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[Evento];
CREATE TABLE [dbo].[Evento](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'data','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'descricao','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'tipo','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'codigo','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'noteText','Test',3,200,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'valor]        [real] NULL,
(1,2,'porcentagem]  [real] NULL,
(1,2,'isMensal]     [bit] NULL,
(1,2,'isSistema]    [bit] NULL,
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[EventoFunc];
CREATE TABLE [dbo].[EventoFunc](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'data','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'idFunc','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'idEvent','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[EventoMesApp];
CREATE TABLE [dbo].[EventoMesApp](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'data','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'idFuncEnvent','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[Pessoa];
CREATE TABLE [dbo].[Pessoa](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'nome','Test',3,200,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'cdEmpr','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'sexo','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'type','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'dataNasc','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'nomePai','Test',3,200,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'nomeMae','Test',3,200,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'nomeConjugue','Test',3,200,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'estadoCivil','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[Profissao];
CREATE TABLE [dbo].[Profissao](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'parentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'profissao','Test',3,100,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'renda] 		 [real] NULL,
(1,2,'dataAdmissao','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
---------------------------------------------------------*/

DROP TABLE [dbo].[Convenio];
CREATE TABLE [dbo].[Convenio](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'nome','Test',3,100,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'dataini','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'dataFin','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'porcentagem] 	 [real] NULL,
(1,2,'valor] 		 [real] NULL,
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[ConvenioPessoa];
CREATE TABLE [dbo].[ConvenioPessoa](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'parentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'convId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[CondPag];
CREATE TABLE [dbo].[CondPag](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'nome','Test',3,100,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'dataini','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'dataFin','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'porcentagem] 	 [real] NULL,
(1,2,'valor] 		 [real] NULL,
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[CondPagPessoa];
CREATE TABLE [dbo].[CondPagPessoa](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'parentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'condPagId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[TipoPag];
CREATE TABLE [dbo].[TipoPag](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'descricao','Test',3,100,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[TipoPagReg];
CREATE TABLE [dbo].[TipoPagReg](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'parentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'tipoPagId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'tabela','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[banco];
CREATE TABLE [dbo].[banco](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'nome','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'logo','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[agencia];
CREATE TABLE [dbo].[agencia](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'nome','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'parentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'numero','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[BancoPesoa];
CREATE TABLE [dbo].[BancoPesoa](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'parentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'bancoId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'numCont','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'saldo]          	     [real] NOT NULL,
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[contato];
CREATE TABLE [dbo].[contato](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'parentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'motivo','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'contato_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[contatoItens];
CREATE TABLE [dbo].[contatoItens](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'parentId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'texto','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[ordemServico];
CREATE TABLE [dbo].[ordemServico](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'emprId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'userId','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'nome','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'data','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'typeId','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'assunto','Test',3,100,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[ordemServicoItens];
CREATE TABLE [dbo].[ordemServicoItens](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'idOrdemServico','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'status','Test',1,null,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'data','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'texto','Test',3,250,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[ordemServicoTypes];
CREATE TABLE [dbo].[ordemServicoTypes](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'type','Test',3,100,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[ordemServicoStatus];
CREATE TABLE [dbo].[ordemServicoStatus](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'status','Test',3,100,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[tabela];
CREATE TABLE [dbo].[tabela](
(1,2,'id','Test',1,null,1,1,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'nome','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'descricao','Test',3,250,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'create_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_date','Test',2,null,1,0,0,null,1432783357778,'system',14327833577780,'system'),
(1,2,'modify_user','Test',3,50,0,0,0,null,1432783357778,'system',14327833577780,'system'),
/** ---------------------------------------------------------------*/