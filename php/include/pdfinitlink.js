
if($('.pdflink').length>0)
$('.pdflink').attr("target","_blank").click(function(){var maxwidth,maxheight,minleft,mintop;maxwidth=maxheight=0;minleft=mintop=999999;if($('[name="page"]').length>0){$('[name="page"]').each(function(ind,element){if($('table[class*="runner-c-grid"]',element).length>0)
element=$('table[class*="runner-c-grid"]',element).eq(0)[0];if(element.offsetWidth>maxwidth)
maxwidth=element.offsetWidth;if(element.offsetHeight>maxheight)
maxheight=element.offsetHeight;});}
else{$('div[class^="runner-s-"]').each(function(index,element){var el=$(element).children(':eq(0)');if(el.hasClass('runner-c-pdf')||el[0]==undefined)
return;if(el[0].offsetLeft+el[0].offsetWidth>maxwidth)
maxwidth=el[0].offsetLeft+el[0].offsetWidth;if(el[0].offsetTop+el[0].offsetHeight>maxheight)
maxheight=el[0].offsetTop+el[0].offsetHeight;if(el[0].offsetLeft<minleft)
minleft=el[0].offsetLeft;if(el[0].offsetTop<mintop)
mintop=el[0].offsetTop;});}
if(!maxwidth)
maxwidth=document.body.scrollWidth;if(!maxheight)
maxheight=document.body.scrollHeight;if(mintop<999999)
maxheight-=mintop;if(minleft<999999)
maxwidth-=minleft;$(this).attr("href","buildpdf.php?url="+encodeURIComponent(window.location)
+'&width='+maxwidth+'&height='+maxheight+'&rndval='+Math.random());return true;});