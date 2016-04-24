
function titleize(text) {

    // Convertendo primeira letra em maiuscula.
    text = text.charAt(0).toUpperCase() + text.slice(1);

    for (var i = 0; i < text.length; i++) {
        if (text.charAt(i) ===" ") {

            // Convertendo letra após o ESPAÇO em maiuscula
            var charToUper = text.charAt(i+1).toUpperCase();

            // Colocando texto de antes do ESPAÇO na variável
            var sliceBegin = text.slice(0, (i+1));

            // colocando o texto de depois do ESPAÇO na variável
            var sliceEnd = text.slice(i + 2);

            // Juntando tudo
            text = sliceBegin + charToUper + sliceEnd;

        } else {

            // NAO CONSIGO PENSAR EM COMO TRANSFORMAR O RESTANTE DAS LETRAS EM MINUSCULA
        }   
    }
    return text;
}

implXML = function (oField,name){

	var text = "";
	
text = text + '<?xml version="1.0" encoding="UTF-8" ?>\n';
text = text + '\n';
text = text + '<!DOCTYPE mapper\n';
text = text + '   PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"\n';
text = text + '    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">\n';
text = text + '\n';
text = text + '<mapper namespace="'+name+'Map">\n';
text = text + '	<cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>\n';
text = text + '	<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>\n';
text = text + '\n';
text = text + '  <!-- Result maps describe the mapping between the columns returned\n';
text = text + '      from a query, and the class properties.  A result map isnt\n';
text = text + '      necessary if the columns (or aliases) match to the properties\n';
text = text + '       exactly. -->\n';
text = text + '  <resultMap id="'+name+'Result" type="'+name+'">\n';
for(i=0;i < oField.length;i++){
	if(oField[i].field.xml == true){
		if(oField[i].field.tipo.indexOf('List') > -1){
			text = text + '<collection property="'+oField[i].field.campo+'" column="id" select="DocumentoMap.fetchDocumentoByEmpresa"/>\n';
		}else{			
			text = text + '    <result property="'+oField[i].field.campo+'" column="'+oField[i].field.tipo+'"/>\n';
		}
	}

}
text = text + '  </resultMap>\n';
text = text + '\n';

text = text + '  <sql id="all'+name+'Columns">\n';
text = text + 'create_date\n';
text = text + ',create_user\n';
text = text + ',modify_date\n';
text = text + ',modify_user\n';
for(i=0;i < oField.length;i++){
	if(oField[i].field.xml == true){
		if(oField[i].field.tipo.indexOf('List') == -1){
			text = text + ','+oField[i].field.campo+'\n';
		}
	}
	
}
text = text + '\n';
text = text + '</sql>\n';
text = text + '<sql id="all'+name+'Values">\n';
for(i=0;i < oField.length;i++){
	if(oField[i].field.xml == true){
		if(oField[i].field.tipo.indexOf('List') == -1){
			text = text + '#{'+oField[i].field.campo+'},\n';
		}
	}
}
text = text + '		#{createDateUTC},\n';
text = text + '		#{createUser},\n';
text = text + '		#{modifyDateUTC},\n';
text = text + '		#{modifyUser}\n';

text = text + '  </sql>\n';
text = text + '\n';
 text = text + ' <sql id="all'+name+'ColumnsWithQualifier">\n';

text = text + '		c.id, c.'+name.toLowerCase()+'_desc\n';
for(i=0;i < oField.length;i++){
	if(oField[i].field.xml == true){
		if(oField[i].field.tipo.indexOf('List') == -1){
			text = text + 'c.'+oField[i].field.campo+',\n';
		}
	}
}
text = text + '		c.createDateUTC,\n';
text = text + '		c.createUser,\n';
text = text + '		c.modifyDateUTC,\n';
text = text + '		c.modifyUser\n';
 
 text = text + ' </sql>\n';
text = text + '\n';
 text = text + ' <select id="fetchAll'+name+'s" resultMap="'+name+'Result">\n';
text = text + '    SELECT\n';
text = text + '	<include refid="all'+name+'Columns" />\n';
 text = text + '   FROM '+name.toLowerCase()+' ORDER BY id ASC\n';
 text = text + ' </select>\n';
text = text + '\n';
 text = text + ' <select id="fetch'+name+'ById" parameterType="Integer" resultMap="'+name+'Result">\n';
text = text + '    SELECT\n';
text = text + '	<include refid="all'+name+'Columns" />\n';
 text = text + '   FROM '+name.toLowerCase()+' WHERE id = #{fetchid}\n';
 text = text + ' </select>\n';
text = text + '\n';
 text = text + ' <select id="fetchAll'+name+'sRequest" parameterType="'+name+'InquiryRequest" resultMap="'+name+'Result">\n';
 text = text + ' 	<if test="_databaseId == '+'Oracle'+'">\n';
text = text + '		SELECT * FROM (\n';
text = text + '		SELECT * FROM (\n';
text = text + '		SELECT <include refid="all'+name+'ColumnsWithQualifier" />,\n';
text = text + '		ROWNUM ROWNUM_ FROM '+name.toLowerCase()+' c ORDER BY id ASC\n';
text = text + '		) WHERE ROWNUM_ <![CDATA[ > ]]>\n';
text = text + '		( #{startPage} * #{pageSize} )\n';
text = text + '		) WHERE ROWNUM <![CDATA[ <= ]]>\n';
text = text + '		#{pageSize}\n';
 text = text + '   </if>\n';
text = text + '\n';
 text = text + '  	 <if test="_databaseId == '+'PostgreSQL'+'">\n';
text = text + '		SELECT <include refid="all'+name+'Columns" />\n';
 text = text + '  		  FROM '+name.toLowerCase()+' ORDER BY id ASC\n';
text = text + '		  OFFSET ( #{startPage} * #{pageSize} )\n';
text = text + '		  LIMIT #{pageSize}\n';
  text = text + '  </if>\n';
  text = text + '</select>\n';
text = text + '\n';
  text = text + '<select id="fetch'+name+'RowCount" resultType="Integer">\n';
  text = text + '    <if test="_databaseId == '+'PostgreSQL'+'">\n';
	text = text + '		SELECT COUNT(*) AS RECORD_COUNT FROM '+name.toLowerCase()+' WHERE id IS NOT NULL\n';
   text = text + '   </if>\n';
text = text + '\n';
   	text = text + '  <if test="_databaseId == '+'Oracle'+'">\n';
	text = text + '	 	SELECT COUNT(id) AS RECORD_COUNT FROM '+name.toLowerCase()+'\n';
    text = text + '  </if>\n';
 text = text + ' </select>\n';
text = text + '\n';
 text = text + ' <insert id="insert'+name+'" parameterType="'+name+'">\n';
 text = text + ' 	INSERT INTO '+name.toLowerCase()+' (\n';
text = text + '	<include refid="all'+name+'Columns" />\n';
 text = text + '     )\n';
 text = text + '   VALUES (<include refid="all'+name+'Values" /> )\n';
 text = text + ' </insert>\n';
text = text + '\n';
 text = text + ' <update id="update'+name+'" parameterType="'+name+'">\n';
 text = text + '   UPDATE '+name.toLowerCase()+' SET\n';
 text = text + '     '+name.toLowerCase()+'_desc = #{description}\n';
 for(i=0;i < oField.length;i++){
	if(oField[i].field.xml == true){
		if(oField[i].field.tipo.indexOf('List') == -1){
			text = text + '<if test="'+oField[i].field.campo+'!= null">'+oField[i].field.campo+' = #{'+oField[i].field.campo+'},</if>\n';
		}
	}
}
 text = text + '<if test="modifyDateUTC!= null"> modify_date=#{modifyDateUTC},</if>\n';
 text = text + '<if test="modifyUser!= null"> modify_user=#{modifyUser}</if>\n';
 text = text + '   WHERE\n';
  text = text + '    id = #{id}\n';
 text = text + ' </update>\n';
text = text + '\n';
  text = text + '<delete id="delete'+name+'ById" parameterType="'+name+'">\n';
  text = text + '  DELETE FROM '+name.toLowerCase()+' WHERE id = #{id}\n';
 text = text + ' </delete>\n';
text = text + '\n';
 text = text + ' <delete id="deleteAll'+name+'s">\n';
text = text + '	DELETE FROM '+name.toLowerCase()+'\n';
text = text + '  </delete>\n';
text = text + '</mapper>\n';
text = text + '\n';
text = text + "\n";

return text;
}
