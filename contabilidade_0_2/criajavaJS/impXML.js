implXML = function(oField, name) {

    var text = '';
    text = text + '<?xml version="1.0" encoding="UTF-8" ?>\n';
    text = text + '\n';
    text = text + '\n';
    text = text + '<!-- create by system gera-java version 1.0.0 ' + dataAtualFormatada() + '-->\n';
    text = text + '\n';
    text = text + '<!DOCTYPE mapper\n';
    text = text + '   PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"\n';
    text = text + '    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">\n';
    text = text + '\n';
    text = text + '<mapper namespace="' + name + 'Map">\n';
    text = text + ' <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>\n';
    text = text + ' <cache type="org.mybatis.caches.ehcache.EhcacheCache"/>\n';
    text = text + '\n';
    text = text + '  <!-- Result maps describe the mapping between the columns returned\n';
    text = text + '      from a query, and the class properties.  A result map isnt\n';
    text = text + '      necessary if the columns (or aliases) match to the properties\n';
    text = text + '       exactly. -->\n';
    text = text + '  <resultMap id="' + name + 'Result" type="' + name + '">\n';
    for (i = 0; i < oField.length; i++) {
        if (oField[i].field.xml == true) {
            if (oField[i].field.tipo !== undefined) {
                if (oField[i].field.tipo.indexOf('List') > -1) {
                    text = text + '<collection property="' + oField[i].field.campo + '" column="id" select="DocumentoMap.fetchDocumentoByEmpresa"/>\n';
                } else {
                    if ((oField[i].field.tipo.toLowerCase() == 'boolean') || (oField[i].field.tipo.toLowerCase() == 'integer') || (oField[i].field.tipo.toLowerCase() == 'double') || (oField[i].field.tipo.toLowerCase() == 'string') || (oField[i].field.tipo.toLowerCase() == 'long') || (oField[i].field.tipo.toLowerCase() == 'float')) {
                        text = text + '    <result property="' + oField[i].field.campo + '" column="' + oField[i].field.campo + '"/>\n';
                    } else {
                        text = text + '    <collection property="' + oField[i].field.campo + '" column="' + oField[i].field.campo + '" select="RegimeMap.fetchRegimeById"/>\n';
                    }
                }
            }
        }

    }
    text = text + '<result property="parentId" column="parentId"/>\n';
    text = text + '<result property="tabelaEnumValue" column="tabelaEnumValue"/>\n';
    text = text + '<result property="emprId" column="emprId"/>\n';
    text = text + '<result property="processId" column="processId"/>\n';
    text = text + '<result property="createUser" column="create_user"/>\n';
    text = text + '<result property="createDateUTC" column="create_date"/>\n';
    text = text + '<result property="modifyUser" column="modify_user"/>\n';
    text = text + '<result property="modifyDateUTC" column="modify_date"/>\n';
    text = text + '  </resultMap>\n';
    text = text + '\n';

    text = text + '  <sql id="all' + name + 'Columns">\n';

    for (i = 0; i < oField.length; i++) {
        if (oField[i].field.xml == true) {
            if (oField[i].field.tipo !== undefined) {
                if ((oField[i].field.tipo.indexOf('List') == -1) && (oField[i].field.campo !== 'id')) {
                    if (i == 0) {
                        text = text + '' + oField[i].field.campo + '\n';
                    } else {
                        text = text + ',' + oField[i].field.campo + '\n';
                    }
                }
            }
        }

    }
    text = text + ',parentId\n';
    text = text + ',tabelaEnumValue\n';
    text = text + ',emprId\n';
    text = text + ',processId\n';
    text = text + ',create_date\n';
    text = text + ',create_user\n';
    text = text + ',modify_date\n';
    text = text + ',modify_user\n';
    text = text + '\n';
    text = text + '</sql>\n';
    text = text + '<sql id="all' + name + 'Values">\n';
    for (i = 0; i < oField.length; i++) {
        if (oField[i].field.xml == true) {
            if (oField[i].field.tipo !== undefined) {
                if ((oField[i].field.tipo.indexOf('List') == -1) && (oField[i].field.campo !== 'id')) {
                    if ((oField[i].field.tipo.toLowerCase() == 'boolean') || (oField[i].field.tipo.toLowerCase() == 'integer') || (oField[i].field.tipo.toLowerCase() == 'double') || (oField[i].field.tipo.toLowerCase() == 'string') || (oField[i].field.tipo.toLowerCase() == 'data') || (oField[i].field.tipo.toLowerCase() == 'float')) {
                        text = text + '#{' + oField[i].field.campo + '},\n';
                    } else {
                        text = text + '#{' + oField[i].field.campo + '.id},\n';
                    }

                }
            }
        }
    }
    text = text + '     #{parentId},\n';
    text = text + '     #{tabelaEnumValue},\n';
    text = text + '     #{emprId},\n';
    text = text + '     #{processId},\n';
    text = text + '     #{createDateUTC},\n';
    text = text + '     #{createUser},\n';
    text = text + '     #{modifyDateUTC},\n';
    text = text + '     #{modifyUser}\n';

    text = text + '  </sql>\n';
    text = text + '\n';

    text = text + '\n';
    text = text + ' <select id="fetchAll' + name + 's" resultMap="' + name + 'Result">\n';
    text = text + '    SELECT id,\n';
    text = text + ' <include refid="all' + name + 'Columns" />\n';
    text = text + '   FROM ' + name.toLowerCase() + ' ORDER BY id ASC\n';
    text = text + ' </select>\n';
    text = text + '\n';
    text = text + ' <select id="fetch' + name + 'ById" parameterType="Integer" resultMap="' + name + 'Result">\n';
    text = text + '    SELECT id,\n';
    text = text + ' <include refid="all' + name + 'Columns" />\n';
    text = text + '   FROM ' + name.toLowerCase() + ' WHERE id = #{fetchid}\n';
    text = text + ' </select>\n';
    text = text + '\n';
    text = text + ' <select id="fetchAll' + name + 'sRequest" parameterType="PagedInquiryRequest" resultMap="' + name + 'Result">\n';
    text = text + '\n';
    text = text + '     SELECT id,<include refid="all' + name + 'Columns" />\n';
    text = text + '           FROM ' + name.toLowerCase() + ' \n';
    text = text + '           where 0 = 0\n';
    text = text + '           <if test="permissaoTypeEnumValue == 1">\n';
    text = text + '               and id IS NOT NULL\n';
    text = text + '            </if>\n';
    text = text + '           <if test="permissaoTypeEnumValue != 1">\n';
    text = text + '               <if test="emprId == null">\n';
    text = text + '                   <if test="emprIds != null">\n';
    text = text + '                       and emprId in\n';
    text = text + '           \n';
    text = text + '                     <foreach item="item" index="index" collection="emprIds"\n';
    text = text + '                          open="(" separator="," close=")">\n';
    text = text + '                         #{item}\n';
    text = text + '                     </foreach>\n';
    text = text + '           \n';
    text = text + '                 </if>\n';
    text = text + '             </if>\n';
    text = text + '               <if test="emprId != null"> and emprId = #{emprId}</if>\n';
    text = text + '           </if>\n';
    text = text + '           ORDER BY id ASC\n';
    text = text + '</select>\n';
    text = text + '\n';
    text = text + '<select id="fetch' + name + 'RowCount" resultType="Integer">\n';
    text = text + '     SELECT COUNT(*) AS RECORD_COUNT FROM ' + name.toLowerCase() + ' WHERE id IS NOT NULL <if test="emprId != null"> and emprId = emprId</if>\n';
    text = text + '\n';
    text = text + ' </select>\n';
    text = text + '\n';
    text = text + ' <insert id="insert' + name + '" parameterType="' + name + '" useGeneratedKeys="true" keyProperty="id">\n';
    text = text + '     INSERT INTO ' + name.toLowerCase() + ' (\n';
    text = text + ' <if test="id != null"><if test="id > 0">id,</if></if><include refid="all' + name + 'Columns" />\n';
    text = text + '     )\n';
    text = text + '   VALUES (<if test="id != null"><if test="id > 0">#{id},</if></if><include refid="all' + name + 'Values" /> )\n';
    text = text + ' </insert>\n';
    text = text + '\n';
    text = text + ' <update id="update' + name + '" parameterType="' + name + '">\n';
    text = text + '   UPDATE ' + name.toLowerCase() + ' SET\n';

    for (i = 0; i < oField.length; i++) {
        if (oField[i].field.xml == true) {
            if (oField[i].field.tipo !== undefined) {
                if ((oField[i].field.tipo.indexOf('List') == -1) && (oField[i].field.campo !== 'id')) {
                    if ((oField[i].field.tipo.toLowerCase() == 'boolean') || (oField[i].field.tipo.toLowerCase() == 'integer') || (oField[i].field.tipo.toLowerCase() == 'double') || (oField[i].field.tipo.toLowerCase() == 'string') || (oField[i].field.tipo.toLowerCase() == 'data') || (oField[i].field.tipo.toLowerCase() == 'float')) {
                        text = text + '<if test="' + oField[i].field.campo + '!= null">' + oField[i].field.campo + ' = #{' + oField[i].field.campo + '},</if>\n';
                    } else {
                        text = text + '<if test="' + oField[i].field.campo + '!= null">' + oField[i].field.campo + ' = #{' + oField[i].field.campo + '.id},</if>\n';
                    }
                }
            }
        }
    }

    text = text + '<if test="parentId!= null"> parentId=#{parentId},</if>\n';
    text = text + '<if test="tabelaEnumValue!= null"> tabelaEnumValue=#{tabelaEnumValue},</if>\n';
    text = text + '<if test="emprId!= null"> emprId=#{emprId},</if>\n';
    text = text + '<if test="processId!= null"> processId=#{processId},</if>\n';
    text = text + '<if test="modifyDateUTC!= null"> modify_date=#{modifyDateUTC},</if>\n';
    text = text + '<if test="modifyUser!= null"> modify_user=#{modifyUser}</if>\n';
    text = text + '   WHERE\n';
    text = text + '    id = #{id}\n';
    text = text + ' </update>\n';
    text = text + '\n';
    text = text + '<delete id="delete' + name + 'ById" parameterType="' + name + '">\n';
    text = text + '  DELETE FROM ' + name.toLowerCase() + ' WHERE id = #{id}\n';
    text = text + ' </delete>\n';
    text = text + '\n';
    text = text + ' <delete id="deleteAll' + name + 's">\n';
    text = text + ' DELETE FROM ' + name.toLowerCase() + '\n';
    text = text + '  </delete>\n';
    text = text + '</mapper>\n';
    text = text + '\n';
    text = text + "\n";

    return text;
}


RepositorioImpl = function(teste, bar, local) {
    debugger
    var text = '/** create by system gera-java version 1.0.0 ' + dataAtualFormatada() + '*/\n';
    text = text + '\n';
    text = text + '\n';
    text = text + 'package com.nouhoun.springboot.jwt.integration.repository.impl.dicionario;\n';
    text = text + '\n';
    text = text + 'import java.util.Collection;\n';
    text = text + 'import java.util.List;\n';
    text = text + '\n';
    text = text + 'import javax.annotation.PostConstruct;\n';
    text = text + '\n';
    text = text + 'import org.hibernate.criterion.Order;\n';
    text = text + 'import org.slf4j.Logger;\n';
    text = text + 'import org.slf4j.LoggerFactory;\n';
    text = text + 'import org.springframework.stereotype.Repository;\n';
    text = text + '\n';
    text = text + 'import com.nouhoun.springboot.jwt.integration.data.BaseHibernateJPARepository;\n';
    text = text + 'import com.nouhoun.springboot.jwt.integration.domain.Category;\n';
    text = text + 'import com.nouhoun.springboot.jwt.integration.repository.CategoryRepository;\n';
    text = text + '\n';
    text = text + '/**\n';
    text = text + ' * Created by Y.Kamesh on 8/2/2015.\n';
    text = text + ' */\n';
    text = text + '@Repository\n';
    text = text + 'public class ' + bar + 'RepositoryImpl extends BaseHibernateJPARepository<' + bar + ', Long> implements ' + bar + 'Repository {\n';
    text = text + '    private static Logger LOG = LoggerFactory.getLogger(' + bar + 'RepositoryImpl.class);\n';
    text = text + '\n';
    text = text + '    @PostConstruct\n';
    text = text + '    public void setUp() {\n';
    text = text + '        LOG.info("' + bar + 'Repository created..!");\n';
    text = text + '    }\n';
    text = text + '}\n';

    return text;
}


Repositorio = function(teste, bar, local) {

    var text = '/** create by system gera-java version 1.0.0 ' + dataAtualFormatada() + '*/\n';
    text = text + '\n';
    text = text + '\n';
    text = text + 'package com.nouhoun.springboot.jwt.integration.repository;\n';
    text = text + '\n';
    text = text + 'import org.springframework.data.jpa.repository.JpaRepository;\n';
    text = text + 'import org.springframework.stereotype.Repository;\n';
    text = text + '\n';
    text = text + 'import com.nouhoun.springboot.jwt.integration.domain.' + bar + ';\n';
    text = text + '\n';
    text = text + '@Repository("' + bar + 'Repository")\n';
    text = text + 'public interface ' + bar + 'Repository extends JpaRepository<' + bar + ', Long> {\n';
    text = text + '\n';
    text = text + '' + bar + ' find' + bar + 'ById(Integer id);\n';
    text = text + '\n';
    text = text + 'List<' + bar + '> find' + bar + 'All(PaginationFilter filter);\n';
    text = text + '\n';
    text = text + '\n';
    text = text + '}\n';
    return text;
}

Servico = function(teste, name, local) {

    var text = '/** create by system gera-java version 1.0.0 ' + dataAtualFormatada() + '*/\n';
    text = text + ' package com.nouhoun.springboot.jwt.integration.service;\n';
    text = text + '\n';
    text = text + 'import java.util.List;\n';
    text = text + 'import com.nouhoun.springboot.jwt.integration.domain.security.PaginationFilter;\n';
    text = text + '\n';
    text = text + 'import com.nouhoun.springboot.jwt.integration.domain.' + name + ';\n';
    text = text + '\n';
    text = text + '\n';
    text = text + '\n';
    text = text + 'public interface ' + name + 'Service {\n';
    text = text + '\n';
    text = text + '\n';
    text = text + 'public ' + name + ' find' + name + 'ById(Integer id);\n';
    text = text + 'public ' + name + ' save' + name + '(' + name + ' ' + name.toLowerCase() + ');\n';
    text = text + 'public ' + name + ' update' + name + '(' + name + ' ' + name.toLowerCase() + ');\n';
    text = text + 'public List<' + name + '> find' + name + 'All(PaginationFilter filter);\n';
    text = text + '\n';
    text = text + '}\n';
    return text;
}

ServicoImp = function(teste, name, local) {

    var text = '/** create by system gera-java version 1.0.0 ' + dataAtualFormatada() + '*/\n';
    text = text + '\n';
    text = text + '\n';
    text = text + 'package com.nouhoun.springboot.jwt.integration.service.impl;\n';
    text = text + 'import com.nouhoun.springboot.jwt.integration.domain.security.PaginationFilter;\n';
    text = text + '\n';
    text = text + 'import java.util.Date;\n';
    text = text + 'import java.util.List;\n';
    text = text + '\n';
    text = text + 'import javax.servlet.http.HttpServletRequest;\n';
    text = text + '\n';
    text = text + 'import org.springframework.beans.factory.annotation.Autowired;\n';
    text = text + 'import org.springframework.stereotype.Service;\n';
    text = text + '\n';
    text = text + 'import com.nouhoun.springboot.jwt.integration.repository.' + name + 'Repository;\n';
    text = text + 'import com.nouhoun.springboot.jwt.integration.domain.' + name + ';\n';
    text = text + 'import com.nouhoun.springboot.jwt.integration.service.' + name + 'Service;\n';
    text = text + '\n';
    text = text + 'public class ' + name + 'ServiceImpl implements ' + name + 'Service {\n';
    text = text + '\n';
    text = text + '     @Autowired\n';
    text = text + '     private ' + name + 'Repository ' + name.toLowerCase() + 'Repository;\n';
    text = text + '\n';
    text = text + '     @Override\n';
    text = text + '     public ' + name + ' update' + name + '(' + name + ' ' + name.toLowerCase() + ') {\n';
    text = text + '          return ' + name.toLowerCase() + 'Repository.save(' + name.toLowerCase() + ');\n';
    text = text + '     }\n';
    text = text + '     @Override\n';
    text = text + '     public ' + name + ' save' + name + '(' + name + ' ' + name.toLowerCase() + ') {\n';
    text = text + '         return ' + name.toLowerCase() + 'Repository.save(' + name.toLowerCase() + ');\n';
    text = text + '     }\n';
    text = text + '\n';
    text = text + '\n';
    text = text + '     @Override\n';
    text = text + '     public ' + name + ' find' + name + 'ById(Integer id) {\n';
    text = text + '         return ' + name.toLowerCase() + 'Repository.find' + name + 'ById(id);\n';
    text = text + '     }\n';
    text = text + '\n';
    text = text + '      @Override\n';
    text = text + '      public List < ' + name + ' > find' + name + 'All(PaginationFilter filter) {\n';
    text = text + '          return (List <' + name + '> ) ' + name.toLowerCase() + 'Repository.find' + name + 'All(filter);\n';
    text = text + '      }\n';
    text = text + '\n';
    text = text + '\n';
    text = text + '\n';
    text = text + '}\n';
    return text;
}