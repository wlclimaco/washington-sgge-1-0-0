jUnitFront = function(oField, name) {
debugger
    var text = '';
    text = text + '\n';
    text = text + '\n';
    text = text + '// create by system gera-java version 1.0.0 ' + dataAtualFormatada() + '//\n';
    text = text + '\n';
    text = text + '@Test\n';
    text = text + 'public void listAll' +titleize(name)+'() throws JsonParseException, JsonMappingException, IOException{\n';
    text = text + ' \n';   
    text = text + '    Integer count =0;\n';
    text = text + '    Integer id =10000;\n';
    text = text + '    RestTemplate restTemplate = new RestTemplate();\n';
        text = text + '\n';
    text = text + '    HttpHeaders headers = new HttpHeaders();\n';
    text = text + '    headers.set("Header", "value");\n';
    text = text + '    headers.setContentType(MediaType.APPLICATION_JSON);\n';
    text = text + '    headers.set("Other-Header", "othervalue");\n';
    text = text + '    headers.set("username", "taz@qat.com" );\n';
    text = text + '\n';
    text = text + '    Map<String, String> params = new HashMap<String, String>();\n';
    text = text + '    params.put("username", "taz@qat.com");\n';
    text = text + '    params.put("password", "taz@qat.com");\n';
    text = text + '\n';
    text = text + '    RestTemplate rest = new RestTemplate();\n';
    text = text + '    rest.setMessageConverters(Arrays.asList(new StringHttpMessageConverter(), new FormHttpMessageConverter()));\n';
    text = text + '    MultiValueMap<String, String> paramss = new LinkedMultiValueMap<String, String>();\n';
    text = text + '    paramss.set("username", "taz@qat.com");\n';
    text = text + '    paramss.set("password", "devil");\n';
    text = text + '    URI tgtUrl = rest.postForLocation(REST_SERVICE_URI + "auth/api/authenticate", paramss, Collections.emptyMap());\n';
    text = text + '    System.out.println("[" + tgtUrl + "]");\n';
    text = text + '\n';
    text = text + '\n';
    text = text + '    System.out.println("[" + tgtUrl + "]");\n';
    text = text + '\n';
    text = text + '\n';
    text = text + '    ResponseEntity<String> st = rest.postForEntity(REST_SERVICE_URI + "auth/api/authenticate", paramss, String.class);\n';
    text = text + '    System.out.println("[" + st.getBody() + "]");\n';
    text = text + '    System.out.println("[" + st + "]");\n';
    text = text + '    String tk = st.getBody();\n';
    text = text + '    Class<? extends String> mt = tk.getClass();\n';
    text = text + '    System.out.println("[" + mt + "]");\n';
    text = text + '    ObjectMapper mapper = new ObjectMapper();\n';
    text = text + '    ModelToken obj = mapper.readValue(st.getBody(), ModelToken.class);\n';
    text = text + '\n';
    text = text + '    System.out.println("[" + obj.getToken() + "]");\n';
    text = text + '\n';
    text = text + '    headers = new HttpHeaders();\n';
    text = text + '    headers.set("Header", "value");\n';
    text = text + '    headers.setContentType(MediaType.APPLICATION_JSON);\n';
    text = text + '    headers.set("Other-Header", "othervalue");\n';
    text = text + '    headers.set("X-Auth-Token", obj.getToken() );\n';
    text = text + '    String a ="request:{pageSize: 20, startPage: 2, sortExpressions: null, preQueryCount: true, maxPreQueryCount: 0}, token:taz@qat.com:1469815365580:33f9281620d9dc7df079e056ad235420, url:fiscal/api/cfop/fetchPage/";\n';
    text = text + '    HttpEntity<String> entity = new HttpEntity<String>("{}",headers);\n';
    text = text + '\n';
     text = text + ' '+titleize(name)+' objeto = new ' +titleize(name)+'();\n';
for (i = 0; i < oField.length; i++) {
        if (oField[i].field.xml == true) {
            if (oField[i].field.tipo !== undefined) {
                if (oField[i].field.tipo.indexOf('List') > -1) {
                    text = text + 'objeto.set' + titleize(oField[i].field.campo) + '(new ArrayList<'+titleize(oField[i].field.tipo)+'> ())\n';
                    text = text + 'objeto.get().add(new ' + titleize(oField[i].field.tipo) + '());\n';
                } else {
                    if ((oField[i].field.tipo.toLowerCase() == 'boolean') ||(oField[i].field.tipo.toLowerCase() == 'integer') || (oField[i].field.tipo.toLowerCase() == 'double')||(oField[i].field.tipo.toLowerCase() == 'string')||(oField[i].field.tipo.toLowerCase() == 'data')||(oField[i].field.tipo.toLowerCase() == 'float')) 
                    {

                        if(oField[i].field.tipo.toLowerCase() == 'string')
                        {
                             text = text + 'objeto.set' + titleize(oField[i].field.campo) + '("'+convertModules(oField[i].field.tipo,i,oField[i].field.campo)+' - INSERT"); \n';
                         }
                         else
                        {
                            debugger
                             if(oField[i].field.campo.toLowerCase() == 'id')
                             {
                                    text = text + 'objeto.set' + titleize(oField[i].field.campo) + '(id); \n';
                             }
                             else
                            {
                                text = text + 'objeto.set' + titleize(oField[i].field.campo) + '('+convertModules(oField[i].field.tipo,i,oField[i].field.campo)+'); \n';
                            }
                        }
                       
                    }else {
                         text = text + 'objeto.set' + titleize(oField[i].field.campo) + '(new '+titleize(oField[i].field.tipo)+'()); \n';
                    }
                }
            }
        }

    }
text = text + 'objeto.setModelAction(PersistenceActionEnum.INSERT);\n'
    text = text + '\n';
        text = text + '\n';
text = text + '//=========== fetch ================================================================\n';
text = text + '        System.out.println("==================================FetchALL==============================================");\n';
text = text + '        String jsonInString = mapper.writeValueAsString(new '+titleize(name)+'InquiryRequest());\n';
text = text + '        System.out.println(jsonInString);\n';
text = text + '        HttpEntity<String> entitys = new HttpEntity<String>(jsonInString,headers);\n';
text = text + '        '+titleize(name)+'Response result = restTemplate.postForObject( REST_SERVICE_URI + "fiscal/api/'+name.toLowerCase()+'/fetchPage/",entitys,  '+titleize(name)+'Response.class);\n';
text = text + '        Assert.assertEquals(result.isOperationSuccess(), true);\n';
text = text + '        count = result.get'+titleize(name)+'List().size();\n';
    text = text + '\n';
        text = text + '\n';
text = text + '      //=========== Insert ================================================================\n';
text = text + '        System.out.println("==================================INSERT==============================================");\n';
text = text + '        jsonInString = mapper.writeValueAsString(objeto);\n';
text = text + '        System.out.println(jsonInString);\n';

text = text + '        String requestJson = "{\\"'+name.toLowerCase()+'\\":"+jsonInString+"}";\n';
text = text + '        entitys = new HttpEntity<String>(requestJson,headers);\n';

text = text + '        result = restTemplate.postForObject( REST_SERVICE_URI + "fiscal/api/'+name.toLowerCase()+'/insert/",entitys,  '+titleize(name)+'Response.class);\n';
text = text + '        Assert.assertEquals(result.isOperationSuccess(), true);\n';
    text = text + '\n';
        text = text + '\n';
text = text + '      //=========== Update ================================================================\n';
text = text + '        System.out.println("==================================UPDATE==============================================");\n';
    text = text + '\n';
text = text + ' objeto = new ' +titleize(name)+'();\n';
   for (i = 0; i < oField.length; i++) {
        if (oField[i].field.xml == true) {
            if (oField[i].field.tipo !== undefined) {
                if (oField[i].field.tipo.indexOf('List') > -1) {
                    text = text + 'objeto.set' + titleize(oField[i].field.campo) + '(new ArrayList<'+titleize(oField[i].field.tipo)+'> ())\n';
                    text = text + 'objeto.get().add(new ' + titleize(oField[i].field.tipo) + '());\n';
                } else {
                    if ((oField[i].field.tipo.toLowerCase() == 'boolean') ||(oField[i].field.tipo.toLowerCase() == 'integer') || (oField[i].field.tipo.toLowerCase() == 'double')||(oField[i].field.tipo.toLowerCase() == 'string')||(oField[i].field.tipo.toLowerCase() == 'data')||(oField[i].field.tipo.toLowerCase() == 'float')) 
                    {

                        if(oField[i].field.tipo.toLowerCase() == 'string')
                        {
                             text = text + 'objeto.set' + titleize(oField[i].field.campo) + '("'+convertModules(oField[i].field.tipo,i,oField[i].field.campo)+' - UPDATE"); \n';
                         }
                         else
                        {
                             if(oField[i].field.campo.toLowerCase() == 'id')
                             {
                                    text = text + 'objeto.set' + titleize(oField[i].field.campo) + '(id); \n';
                             }
                             else
                            {
                                text = text + 'objeto.set' + titleize(oField[i].field.campo) + '('+convertModules(oField[i].field.tipo,i,oField[i].field.campo)+'); \n';
                            }
                        }
                       
                    }else {
                         text = text + 'objeto.set' + titleize(oField[i].field.campo) + '(new '+titleize(oField[i].field.tipo)+'()); \n';
                    }
                }
            }
        }

    }
    text = text + 'objeto.setModelAction(PersistenceActionEnum.UPDATE);\n'
text = text + '        \n';
text = text + '        objeto.setModifyDateUTC((new Date()).getTime());\n';
text = text + '        objeto.setModifyUser("rod");\n';

text = text + '        jsonInString = mapper.writeValueAsString(objeto);\n';
text = text + '        requestJson = "{\\"'+name.toLowerCase()+'\\":"+jsonInString+"}";\n';
text = text + '        entitys = new HttpEntity<String>(requestJson,headers);\n';

text = text + '        result = restTemplate.postForObject( REST_SERVICE_URI + "fiscal/api/'+name.toLowerCase()+'/update/",entitys,  '+titleize(name)+'Response.class);\n';
text = text + '        Assert.assertEquals(result.isOperationSuccess(), true);\n';
    text = text + '\n';
        text = text + '\n';
text = text + '       //===========  FetchbyID  ================================================================\n';
text = text + '        System.out.println("==================================FetchID==============================================");\n';
    text = text + '\n';
    text = text + '\n';
text = text + '        '+titleize(name)+'InquiryRequest request001 = new '+titleize(name)+'InquiryRequest();\n';
text = text + '        request001.setId(id);\n';
text = text + '        jsonInString = mapper.writeValueAsString(request001);\n';
text = text + '        System.out.println(jsonInString);\n';
text = text + '        entitys = new HttpEntity<String>(jsonInString,headers);\n';
text = text + '        result = restTemplate.postForObject( REST_SERVICE_URI + "fiscal/api/'+name.toLowerCase()+'/fetchPage/",entitys,  '+titleize(name)+'Response.class);\n';
text = text + '        Assert.assertEquals(result.isOperationSuccess(), true);\n';
text = text + '        Assert.assertEquals(result.get'+titleize(name)+'List().size(), 1);\n';
    text = text + '\n';
        text = text + '\n';
for (i = 0; i < oField.length; i++) {
        if (oField[i].field.xml == true) {
            if (oField[i].field.tipo !== undefined) {
                if (oField[i].field.tipo.indexOf('List') > -1) {
                    text = text + 'objeto.set' + titleize(oField[i].field.campo) + '(new ArrayList<'+titleize(oField[i].field.tipo)+'> ())\n';
                    text = text + 'objeto.get().add(new ' + titleize(oField[i].field.tipo) + '());\n';
                } else {
                    if ((oField[i].field.tipo.toLowerCase() == 'boolean') ||(oField[i].field.tipo.toLowerCase() == 'integer') || (oField[i].field.tipo.toLowerCase() == 'double')||(oField[i].field.tipo.toLowerCase() == 'string')||(oField[i].field.tipo.toLowerCase() == 'data')||(oField[i].field.tipo.toLowerCase() == 'float')) 
                    {

                        if(oField[i].field.tipo.toLowerCase() == 'string')
                        {
                             text = text + 'Assert.assertEquals(result.get' + titleize(name) + 'List().get(0).get' + titleize(oField[i].field.campo) + '(),"'+convertModules(oField[i].field.tipo,i,oField[i].field.campo)+' - UPDATE"); \n';
                         }
                         else
                        {
                             if(oField[i].field.campo.toLowerCase() != 'id')
                             {
                             
                                text = text + 'Assert.assertEquals(result.get' + titleize(name) + 'List().get(0).get' + titleize(oField[i].field.campo) + '(),('+convertModules(oField[i].field.tipo,i,oField[i].field.campo)+'); \n';
                            }
                        }
                       
                    }else {
                         text = text + 'Assert.assertEquals(result.get' + titleize(name) + 'List().get(0).get' + titleize(oField[i].field.campo) + '(),(new '+titleize(oField[i].field.tipo)+'()); \n';
                    }
                }
            }
        }

    }
    text = text + '\n';
    text = text + '\n';
text = text + '        //=======================\n';
text = text + '        System.out.println("==================================DELETE==============================================");\n';
text = text + '        objeto.setModelAction(PersistenceActionEnum.DELETE);\n'
text = text + '        jsonInString = mapper.writeValueAsString(objeto);\n';
text = text + '        requestJson = "{\\\"'+name.toLowerCase()+'\\\":"+jsonInString+"}";\n';
text = text + '        entitys = new HttpEntity<String>(requestJson,headers);\n';
text = text + '        result = restTemplate.postForObject( REST_SERVICE_URI + "fiscal/api/'+name.toLowerCase()+'/delete/",entitys,  '+titleize(name)+'Response.class);\n';
text = text + '        Assert.assertEquals(result.isOperationSuccess(), true);\n';
text = text + '        Assert.assertEquals(result.get'+titleize(name)+'List().size(), count.intValue());\n';
    text = text + '\n';
    text = text + '\n';
text = text + '    }\n';


    text = text + '\n';
    text = text + "\n";

    return text;
}