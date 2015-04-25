
function refresh_crosstable(change_field)
{var i,j,grfunc_value,grfunc,shref,pos;grfunc_value=0;grfunc=$("input[name=group_func]");for(i=0;i<grfunc.length;i++)
{if(grfunc[i].checked)
grfunc_value=grfunc[i].value;}
if($("#print_friendly")[0])
{shref=$("#print_friendly")[0].href;pos=shref.indexOf("field=",0);if(pos>=0)
shref=shref.substr(0,pos);$("#print_friendly")[0].href=shref+"?field="+$("#select_data")[0].value+"&group_func="+grfunc_value;}
if($("#cross_print_all")[0])
{shref=$("#cross_print_all")[0].href;pos=shref.indexOf("field=",0);if(pos>=0)
shref=shref.substr(0,pos);$("#cross_print_all")[0].href=shref+"?field="+$("#select_data")[0].value+"&group_func="+grfunc_value;$("#cross_print_all_pict")[0].href=shref+"?field="+$("#select_data")[0].value+"&group_func="+grfunc_value;}
if($("#export_to_excel")[0])
{shref=$("#export_to_excel")[0].href;pos=shref.indexOf("field=",0);if(pos>=0)
shref=shref.substr(0,pos);$("#export_to_excel")[0].href=shref+"?field="+$("#select_data")[0].value+"&group_func="+grfunc_value;}
if($("#export_to_word")[0])
{shref=$("#export_to_word")[0].href;pos=shref.indexOf("field=",0);if(pos>=0)
shref=shref.substr(0,pos);$("#export_to_word")[0].href=shref+"?field="+$("#select_data")[0].value+"&group_func="+grfunc_value;}
shref=window.location.href;pos=shref.indexOf("&axis_x",0);pos2=shref.indexOf("&rname",0);if(pos>=0)
{if(pos2<0)
shref=shref.substr(0,pos);else
shref=shref.substr(0,pos)+shref.substr(pos2);}
$.get(shref,{axis_x:$("#select_group_x")[0].value,axis_y:$("#select_group_y")[0].value,field:$("#select_data")[0].value,group_func:grfunc_value,crosstable_refresh:true,rndVal:(new Date().getTime())},function(txt)
{var obj=JSON.parse(txt);for(var i in obj[0])
{if(!isNaN(i))
{var subobj=obj[0][i]["row_record"]["data"];for(var j=0;j<subobj.length;j++)
$("#"+subobj[j]["id_data"]).html(subobj[j]["row_value"]);$("#"+obj[0][i]["id_row_summary"]).html(obj[0][i]["row_summary"]);}}
for(var i in obj[1]["data"])
{$("#"+obj[1]["data"][i]["id_col_summary"]).html(obj[1]["data"][i]["col_summary"]);}
$("#id_total_summary").html(obj[2]);$("#group_func").html(obj[3]);$("#totals1").html(obj[4]);$("#totals2").html(obj[4]);});}
function refresh_group(rname)
{var grfunc=$("input[name=group_func]");var select_data="";var grfunc_value="";if($("#select_data")[0])
select_data=$("#select_data")[0].value
for(var i=0;i<grfunc.length;i++)
{if(grfunc[i].checked)
grfunc_value=grfunc[i].value;}
window.location.href=report_filename+"?rname="+rname+"&axis_x="+$("#select_group_x")[0].value+"&axis_y="+$("#select_group_y")[0].value+"&field="+select_data+"&group_func="+grfunc_value;}