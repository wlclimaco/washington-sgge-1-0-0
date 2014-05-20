/* 
 * Timemap.js Copyright 2008 Nick Rabinowitz.
 * Licensed under the MIT License (see LICENSE.txt)
 */
(function(){var i=this,f,g=i.Timeline,h=g.DateTime,d=i.G_DEFAULT_MAP_TYPES,k=i.G_NORMAL_MAP,r=i.G_PHYSICAL_MAP,p=i.G_SATELLITE_MAP,n=i.GLatLng,c=i.GLatLngBounds,b=i.GEvent,j="http://www.google.com/intl/en_us/mapfiles/ms/icons/",l,q,o,m,e;l=function(t,v,u){var s=this,w={mapCenter:new n(0,0),mapZoom:0,mapType:r,mapTypes:[k,p,r],showMapTypeCtrl:true,showMapCtrl:true,syncBands:true,mapFilter:"hidePastFuture",centerOnItems:true,theme:"red"};s.mElement=v;s.tElement=t;s.datasets={};s.chains={};s.opts=u=a.merge(u,w);u.mergeOnly=["mergeOnly","theme","eventIconPath","openInfoWindow","closeInfoWindow","noPlacemarkLoad","noEventLoad","infoTemplate","templatePattern"];u.mapType=a.lookup(u.mapType,l.mapTypes);u.mapFilter=a.lookup(u.mapFilter,l.filters);u.theme=m.create(u.theme,u);s.initMap()};l.prototype.initMap=function(){var s=this.opts,u,t;if(GBrowserIsCompatible()){this.map=u=new GMap2(this.mElement);for(t=d.length-1;t>0;t--){u.removeMapType(d[t])}u.addMapType(s.mapTypes[0]);u.removeMapType(d[0]);for(t=1;t<s.mapTypes.length;t++){u.addMapType(s.mapTypes[t])}u.setCenter(s.mapCenter,s.mapZoom,s.mapType);u.enableDoubleClickZoom();u.enableScrollWheelZoom();u.enableContinuousZoom();if(s.showMapCtrl){u.addControl(new GLargeMapControl())}if(s.showMapTypeCtrl){u.addControl(new GMapTypeControl())}this.mapBounds=s.mapZoom>0?u.getBounds():new c()}};l.version="1.6";var a=l.util={};l.init=function(v){var C="TimeMap.init: No id for ",B={options:{},datasets:[],bands:false,bandInfo:false,bandIntervals:"wk",scrollTo:"earliest"},s=l.state,F,I,D=[],H,u,y,A,t,z=[],w,G;if(!("mapId" in v)||!v.mapId){throw C+"map"}if(!("timelineId" in v)||!v.timelineId){throw C+"timeline"}if(s){s.setConfigFromUrl(v)}v=a.merge(v,B);if(!v.bandInfo&&!v.bands){F=a.lookup(v.bandIntervals,l.intervals);v.bandInfo=[{width:"80%",intervalUnit:F[0],intervalPixels:70},{width:"20%",intervalUnit:F[1],intervalPixels:100,showEventText:false,overview:true,trackHeight:0.4,trackGap:0.2}]}I=new l(document.getElementById(v.timelineId),document.getElementById(v.mapId),v.options);for(H=0;H<v.datasets.length;H++){u=v.datasets[H];A={title:u.title,theme:u.theme,dateParser:u.dateParser};y=a.merge(u.options,A);t=u.id||"ds"+H;D[H]=I.createDataset(t,y);if(H>0){D[H].eventSource=D[0].eventSource}}I.eventSource=D[0].eventSource;w=(D[0]&&D[0].eventSource)||new g.DefaultEventSource();if(v.bands){z=v.bands;for(H=0;H<z.length;H++){if(z[H].eventSource!==null){z[H].eventSource=w}}}else{for(H=0;H<v.bandInfo.length;H++){G=v.bandInfo[H];if(!(("eventSource" in G)&&!G.eventSource)){G.eventSource=w}else{G.eventSource=null}z[H]=g.createBandInfo(G);if(H>0&&a.TimelineVersion()=="1.2"){z[H].eventPainter.setLayout(z[0].eventPainter.getLayout())}}}I.initTimeline(z);var E=l.loadManager;E.init(I,v.datasets.length,v);for(H=0;H<v.datasets.length;H++){(function(K){var O=v.datasets[K],L,N,P,M,J;L=O.data||O.options||{};N=O.type||L.type;P=function(){E.increment()};M=(typeof(N)=="string")?l.loaders[N]:N;J=new M(L);J.load(D[K],P)})(H)}return I};l.loadManager=new function(){var s=this;s.init=function(t,v,u){s.count=0;s.tm=t;s.target=v;s.opts=u||{}};s.increment=function(){s.count++;if(s.count>=s.target){s.complete()}};s.complete=function(){var t=s.tm,v=s.opts,u=v.dataLoadedFunction;if(u){u(t)}else{t.scrollToDate(v.scrollTo,true);if(t.initState){t.initState()}u=v.dataDisplayedFunction;if(u){u(t)}}}};l.prototype.parseDate=function(t){var v=new Date(),u=this.eventSource,x=o.hybridParser,w=u.getCount()>0?true:false;switch(t){case"now":break;case"earliest":case"first":if(w){v=u.getEarliestDate()}break;case"latest":case"last":if(w){v=u.getLatestDate()}break;default:v=x(t)}return v};l.prototype.scrollToDate=function(A,v){var A=this.parseDate(A),w=this.timeline,s,y=[],z,t,u;if(A){for(s=0;s<w.getBandCount();s++){z=w.getBand(s);t=z.getMinDate().getTime();u=z.getMaxDate().getTime();y[s]=(v&&A.getTime()>t&&A.getTime()<u)}w.getBand(0).setCenterVisibleDate(A);for(s=0;s<y.length;s++){if(y[s]){w.getBand(s).layout()}}}else{if(v){w.layout()}}};l.prototype.createDataset=function(x,t){var s=this,w=new o(s,t);s.datasets[x]=w;if(s.opts.centerOnItems){var v=s.map,u=s.mapBounds;b.addListener(w,"itemsloaded",function(){v.setCenter(u.getCenter(),v.getBoundsZoomLevel(u))})}return w};l.prototype.initTimeline=function(w){var u=this,s,t;for(s=1;s<w.length;s++){if(u.opts.syncBands){w[s].syncWith=(s-1)}w[s].highlight=true}u.timeline=g.create(u.tElement,w);u.timeline.getBand(0).addOnScrollListener(function(){u.filter("map")});for(s=0;s<u.timeline.getBandCount();s++){t=u.timeline.getBand(s).getEventPainter().constructor;t.prototype._showBubble=function(z,A,x){x.item.openInfoWindow()}}u.addFilterChain("map",function(x){x.showPlacemark()},function(x){x.hidePlacemark()});u.addFilter("map",function(x){return x.visible});u.addFilter("map",function(x){return x.dataset.visible});u.addFilter("map",u.opts.mapFilter);u.addFilterChain("timeline",function(x){x.showEvent()},function(x){x.hideEvent()},null,function(){var x=this.timemap;x.eventSource._events._index();x.timeline.layout()});u.addFilter("timeline",function(x){return x.visible});u.addFilter("timeline",function(x){return x.dataset.visible});var y=null,v=u.timeline;i.onresize=function(){if(y===null){y=i.setTimeout(function(){y=null;v.layout()},500)}}};l.prototype.each=function(t){var s=this,u;for(u in s.datasets){if(s.datasets.hasOwnProperty(u)){t(s.datasets[u])}}};l.prototype.eachItem=function(s){this.each(function(t){t.each(function(u){s(u)})})};l.prototype.getItems=function(){var s=[];this.eachItem(function(t){s.push(t)});return s};l.loaders={cb:{},cancelAll:function(){var s=l.loaders.cb,t;for(t in s){if(s.hasOwnProperty(t)){s[t]=function(){delete s[t]}}}},counter:0,base:function(t){var u=function(v){return v},s=this;s.parse=t.parserFunction||u;s.preload=t.preloadFunction||u;s.transform=t.transformFunction||u;s.scrollTo=t.scrollTo||"earliest";s.getCallbackName=function(x,y){var v=l.loaders.cb,w="_"+l.loaders.counter++,y=y||function(){x.timemap.scrollToDate(s.scrollTo,true)};v[w]=function(z){var A=s.parse(z);A=s.preload(A);x.loadItems(A,s.transform);y();delete v[w]};return w};s.getCallback=function(w,x){var v=s.getCallbackName(w,x);return l.loaders.cb[v]}},basic:function(t){var s=new l.loaders.base(t);s.data=t.items||t.value||[];s.load=function(u,v){(this.getCallback(u,v))(this.data)};return s},remote:function(t){var s=new l.loaders.base(t);s.url=t.url;s.load=function(u,v){GDownloadUrl(this.url,this.getCallback(u,v))};return s}};q=function(u,t,s,y,v){var w=this,x=function(z){};w.timemap=u;w.chain=[];w.on=t||x;w.off=s||x;w.pre=y||x;w.post=v||x};q.prototype.add=function(s){this.chain.push(s)};q.prototype.remove=function(u){var t=this.chain,s;if(!u){t.pop()}else{for(s=0;s<t.length;s++){if(t[s]==u){t.splice(s,1)}}}};q.prototype.run=function(){var t=this,s=t.chain;if(!s.length){return}t.pre();t.timemap.eachItem(function(w){var u=false;F_LOOP:while(!u){for(var v=s.length-1;v>=0;v--){if(!s[v](w)){t.off(w);break F_LOOP}}t.on(w);u=true}});t.post()};l.prototype.filter=function(t){var s=this.chains[t];if(s){s.run()}};l.prototype.addFilterChain=function(w,t,s,v,u){this.chains[w]=new q(this,t,s,v,u)};l.prototype.removeFilterChain=function(s){this.chains[s]=null};l.prototype.addFilter=function(u,t){var s=this.chains[u];if(s){s.add(t)}};l.prototype.removeFilter=function(u,t){var s=this.chains[u];if(s){s.remove(t)}};l.filters={hidePastFuture:function(v){var x=v.timeline.getBand(0),t=x.getMaxVisibleDate().getTime(),u=x.getMinVisibleDate().getTime(),s=v.getStartTime(),w=v.getEndTime();if(s!==f){return s<t&&(w>u||s>u)}return true},hideFuture:function(u){var t=u.timeline.getBand(0).getMaxVisibleDate().getTime(),s=u.getStartTime();if(s!==f){return s<t}return true},showMomentOnly:function(t){var v=t.timeline.getBand(0),w=v.getCenterVisibleDate().getTime(),s=t.getStartTime(),u=t.getEndTime();if(s!==f){return s<w&&(u>w||s>w)}return true},none:function(s){return true}};o=function(s,t){var u=this,v={title:"Untitled",dateParser:o.hybridParser};u.timemap=s;u.eventSource=new g.DefaultEventSource();u.items=[];u.visible=true;u.opts=t=a.merge(t,v,s.opts);t.dateParser=a.lookup(t.dateParser,l.dateParsers);t.theme=m.create(t.theme,t);u.getItems=function(w){if(w!==f){if(w<u.items.length){return u.items[w]}else{return null}}return u.items};u.getTitle=function(){return u.opts.title}};o.gregorianParser=function(u){if(!u||typeof(u)!="string"){return null}var v=Boolean(u.match(/b\.?c\.?/i)),t=parseInt(u,10),w;if(!isNaN(t)){if(v){t=1-t}w=new Date(0);w.setUTCFullYear(t);return w}else{return null}};o.hybridParser=function(t){if(t instanceof Date){return t}var v=new Date(typeof(t)=="number"?t:Date.parse(t));if(isNaN(v)){if(typeof(t)=="string"){if(t.match(/^-?\d{1,6} ?(a\.?d\.?|b\.?c\.?e?\.?|c\.?e\.?)?$/i)){v=o.gregorianParser(t)}else{try{v=h.parseIso8601DateTime(t)}catch(u){v=null}}if(!v&&t.match(/^\d{7,}$/)){v=new Date(parseInt(t))}}else{return null}}return v};o.prototype.each=function(t){for(var s=0;s<this.items.length;s++){t(this.items[s])}};o.prototype.loadItems=function(u,t){for(var s=0;s<u.length;s++){this.loadItem(u[s],t)}b.trigger(this,"itemsloaded")};o.prototype.loadItem=function(R,G){if(G!==f){R=G(R)}if(!R){return}var H=this,x=H.timemap,z=a.merge(R.options,H.opts),Q=z.theme=m.create(z.theme,z),w=H.opts.dateParser,E=g.DefaultEventSource.Event,C=R.start,A=R.end,u=Q.eventIcon,B=Q.eventTextColor,S=R.title,M=null,v,s=Q.icon,D=x.mapBounds,P=[],K=[],t=null,y="",L=null,N;C=C?w(C):null;A=A?w(A):null;v=!A;if(C!==null){if(a.TimelineVersion()=="1.2"){M=new E(C,A,null,null,v,S,null,null,null,u,Q.eventColor,Q.eventTextColor)}else{if(!B){B=(Q.classicTape&&!v)?"#FFFFFF":"#000000"}M=new E({start:C,end:A,instant:v,text:S,icon:u,color:Q.eventColor,textColor:B})}}var J=function(X){var W=null,Z="",ac=null;if(X.point){var Y=X.point.lat,U=X.point.lon;if(Y===f||U===f){return null}ac=new n(parseFloat(X.point.lat),parseFloat(X.point.lon));if(x.opts.centerOnItems){D.extend(ac)}W=new GMarker(ac,{icon:s,title:X.title});Z="marker";ac=W.getLatLng()}else{if(X.polyline||X.polygon){var ad=[],ae;if(X.polyline){ae=X.polyline}else{ae=X.polygon}if(ae&&ae.length){for(var aa=0;aa<ae.length;aa++){ac=new n(parseFloat(ae[aa].lat),parseFloat(ae[aa].lon));ad.push(ac);if(x.opts.centerOnItems){D.extend(ac)}}if("polyline" in X){W=new GPolyline(ad,Q.lineColor,Q.lineWeight,Q.lineOpacity);Z="polyline";ac=W.getVertex(Math.floor(W.getVertexCount()/2))}else{W=new GPolygon(ad,Q.polygonLineColor,Q.polygonLineWeight,Q.polygonLineOpacity,Q.fillColor,Q.fillOpacity);Z="polygon";ac=W.getBounds().getCenter()}}}else{if("overlay" in X){var ab=new n(parseFloat(X.overlay.south),parseFloat(X.overlay.west)),V=new n(parseFloat(X.overlay.north),parseFloat(X.overlay.east)),T=new c(ab,V);if(x.opts.centerOnItems){D.extend(ab);D.extend(V)}W=new GGroundOverlay(X.overlay.image,T);Z="overlay";ac=T.getCenter()}}}return{placemark:W,type:Z,point:ac}};if("placemarks" in R){K=R.placemarks}else{var F=["point","polyline","polygon","overlay"];for(N=0;N<F.length;N++){if(F[N] in R){t={title:S};t[F[N]]=R[F[N]];K.push(t)}}}if(K){for(N=0;N<K.length;N++){var I=J(K[N]);if(I&&I.placemark){L=L||I.point;y=y||I.type;P.push(I.placemark)}}}if(P.length>1){y="array"}z.title=S;z.type=y;if(z.infoPoint){z.infoPoint=new n(parseFloat(z.infoPoint.lat),parseFloat(z.infoPoint.lon))}else{z.infoPoint=L}var O=new e(P,M,H,z);if(M!==null){M.item=O;if(!H.opts.noEventLoad){H.eventSource.add(M)}}if(P.length>0){for(N=0;N<P.length;N++){P[N].item=O;b.addListener(P[N],"click",function(){O.openInfoWindow()});if(!H.opts.noPlacemarkLoad){x.map.addOverlay(P[N])}P[N].hide()}}H.items.push(O);return O};m=function(t){var v={color:"#FE766A",lineOpacity:1,lineWeight:2,fillOpacity:0.25,eventTextColor:null,eventIconPath:"timemap/images/",eventIconImage:"red-circle.png",classicTape:false,iconImage:j+"red-dot.png"};var u=a.merge(t,v);delete u.mergeOnly;if(!u.icon){var s=new GIcon(G_DEFAULT_ICON);s.image=u.iconImage;s.iconSize=new GSize(32,32);s.shadow=j+"msmarker.shadow.png";s.shadowSize=new GSize(59,32);s.iconAnchor=new GPoint(16,33);s.infoWindowAnchor=new GPoint(18,3);u.icon=s}v={lineColor:u.color,polygonLineColor:u.color,polgonLineOpacity:u.lineOpacity,polygonLineWeight:u.lineWeight,fillColor:u.color,eventColor:u.color,eventIcon:u.eventIconPath+u.eventIconImage};u=a.merge(u,v);return u};m.create=function(u,s){if(u){u=l.util.lookup(u,l.themes)}else{return new m(s)}var v=false,t;for(t in s){if(u.hasOwnProperty(t)){v={};break}}if(v){for(t in u){if(u.hasOwnProperty(t)){v[t]=s[t]||u[t]}}v.eventIcon=s.eventIcon||v.eventIconPath+v.eventIconImage;return v}else{return u}};e=function(t,v,x,s){var u=this,w={type:"none",title:"Untitled",description:"",infoPoint:null,infoHtml:"",infoUrl:"",infoTemplate:'<div class="infotitle">{{title}}</div><div class="infodescription">{{description}}</div>',templatePattern:/{{([^}]+)}}/g,closeInfoWindow:e.closeInfoWindowBasic};u.event=v;u.dataset=x;u.map=x.timemap.map;u.timeline=x.timemap.timeline;if(t&&a.isArray(t)&&t.length===0){t=null}if(t&&t.length==1){t=t[0]}u.placemark=t;u.opts=s=a.merge(s,w,x.opts);if(!s.openInfoWindow){if(s.infoUrl!==""){s.openInfoWindow=e.openInfoWindowAjax}else{s.openInfoWindow=e.openInfoWindowBasic}}u.getType=function(){return u.opts.type};u.getTitle=function(){return u.opts.title};u.getInfoPoint=function(){return u.opts.infoPoint||u.map.getCenter()};u.getStart=function(){if(u.event){return u.event.getStart()}};u.getEnd=function(){if(u.event){return u.event.getEnd()}};u.getStartTime=function(){var y=u.getStart();if(y){return y.getTime()}};u.getEndTime=function(){var y=u.getEnd();if(y){return y.getTime()}};u.selected=false;u.visible=true;u.placemarkVisible=false;u.eventVisible=true;u.openInfoWindow=function(){s.openInfoWindow.call(u);u.selected=true};u.closeInfoWindow=function(){s.closeInfoWindow.call(u);u.selected=false}};e.prototype.showPlacemark=function(){var t=this,s;if(t.placemark){if(t.getType()=="array"){for(s=0;s<t.placemark.length;s++){t.placemark[s].show()}}else{t.placemark.show()}t.placemarkVisible=true}};e.prototype.hidePlacemark=function(){var t=this,s;if(t.placemark){if(t.getType()=="array"){for(s=0;s<t.placemark.length;s++){t.placemark[s].hide()}}else{t.placemark.hide()}t.placemarkVisible=false}t.closeInfoWindow()};e.prototype.showEvent=function(){if(this.event){if(this.eventVisible===false){this.dataset.timemap.timeline.getBand(0).getEventSource()._events._events.add(this.event)}this.eventVisible=true}};e.prototype.hideEvent=function(){if(this.event){if(this.eventVisible){this.dataset.timemap.timeline.getBand(0).getEventSource()._events._events.remove(this.event)}this.eventVisible=false}};e.openInfoWindowBasic=function(){var v=this,u=v.opts,t=u.infoHtml,s;if(!t){t=u.infoTemplate;s=u.templatePattern.exec(t);while(s){t=t.replace(s[0],u[s[1]]);s=u.templatePattern.exec(t)}}if(v.placemark&&!v.placemarkVisible&&v.event){v.dataset.timemap.scrollToDate(v.event.getStart())}if(v.getType()=="marker"){v.placemark.openInfoWindowHtml(t)}else{v.map.openInfoWindowHtml(v.getInfoPoint(),t)}v.closeListener=b.addListener(v.map,"infowindowclose",function(){v.selected=false;b.removeListener(v.closeListener)})};e.openInfoWindowAjax=function(){var s=this;if(!s.opts.infoHtml){if(s.opts.infoUrl){GDownloadUrl(s.opts.infoUrl,function(t){s.opts.infoHtml=t;s.openInfoWindow()});return}}s.openInfoWindow=function(){e.openInfoWindowBasic.call(s);s.selected=true};s.openInfoWindow()};e.closeInfoWindowBasic=function(){if(this.getType()=="marker"){this.placemark.closeInfoWindow()}else{var s=this.map.getInfoWindow();if(s.getPoint()==this.getInfoPoint()&&!s.isHidden()){this.map.closeInfoWindow()}}};l.util.trim=function(s){s=s&&String(s)||"";return s.replace(/^\s\s*/,"").replace(/\s\s*$/,"")};l.util.isArray=function(s){return s&&!(s.propertyIsEnumerable("length"))&&typeof s==="object"&&typeof s.length==="number"};l.util.getTagValue=function(w,s,u){var v="",t=l.util.getNodeList(w,s,u);if(t.length>0){w=t[0].firstChild;while(w!==null){v+=w.nodeValue;w=w.nextSibling}}return v};l.util.nsMap={};l.util.getNodeList=function(v,t,u){var s=l.util.nsMap;if(u===f){return v.getElementsByTagName(t)}if(v.getElementsByTagNameNS&&s[u]){return v.getElementsByTagNameNS(s[u],t)}return v.getElementsByTagName(u+":"+t)};l.util.makePoint=function(u,v){var t=null,s=l.util.trim;if(u.lat&&u.lng){t=[u.lat(),u.lng()]}if(l.util.isArray(u)){t=u}if(!t){u=s(u);if(u.indexOf(",")>-1){t=u.split(",")}else{t=u.split(/[\r\n\f ]+/)}}if(t.length>2){t=t.slice(0,2)}if(v){t.reverse()}return{lat:s(t[0]),lon:s(t[1])}};l.util.makePoly=function(v,y){var u=[],t,w=l.util.trim(v).split(/[\r\n\f ]+/);if(w.length===0){return[]}for(var s=0;s<w.length;s++){t=(w[s].indexOf(",")>0)?w[s].split(","):[w[s],w[++s]];if(t.length>2){t=t.slice(0,2)}if(y){t.reverse()}u.push({lat:t[0],lon:t[1]})}return u};l.util.formatDate=function(y,x){x=x||3;var z="";if(y){var w=y.getUTCFullYear(),t=y.getUTCMonth(),A=y.getUTCDate();if(w<1000){return(w<1?(w*-1+"BC"):w+"")}if(y.toISOString&&x==3){return y.toISOString()}var s=function(C){return((C<10)?"0":"")+C};z+=w+"-"+s(t+1)+"-"+s(A);if(x>1){var u=y.getUTCHours(),v=y.getUTCMinutes(),B=y.getUTCSeconds();z+="T"+s(u)+":"+s(v);if(x>2){z+=s(B)}z+="Z"}}return z};l.util.TimelineVersion=function(){if(g.version){return g.version}if(g.DurationEventPainter){return"1.2"}else{return"2.2.0"}};l.util.getPlacemarkType=function(s){return"getIcon" in s?"marker":"getVertex" in s?("setFillStyle" in s?"polygon":"polyline"):false};l.util.merge=function(){var v={},t=arguments,z,u,s,A;var w=function(B,y,x){if(B.hasOwnProperty(x)&&y[x]===f){y[x]=B[x]}};for(s=0;s<t.length;s++){z=t[s];if(z){if(s>0&&"mergeOnly" in z){for(A=0;A<z.mergeOnly.length;A++){u=z.mergeOnly[A];w(z,v,u)}}else{for(u in z){w(z,v,u)}}}}return v};l.util.lookup=function(s,t){if(typeof(s)=="string"){return t[s]}else{return s}};l.intervals={sec:[h.SECOND,h.MINUTE],min:[h.MINUTE,h.HOUR],hr:[h.HOUR,h.DAY],day:[h.DAY,h.WEEK],wk:[h.WEEK,h.MONTH],mon:[h.MONTH,h.YEAR],yr:[h.YEAR,h.DECADE],dec:[h.DECADE,h.CENTURY]};l.mapTypes={normal:k,satellite:p,hybrid:G_HYBRID_MAP,physical:r};l.dateParsers={hybrid:o.hybridParser,iso8601:h.parseIso8601DateTime,gregorian:o.gregorianParser};l.themes={red:new m(),blue:new m({iconImage:j+"blue-dot.png",color:"#5A7ACF",eventIconImage:"blue-circle.png"}),green:new m({iconImage:j+"green-dot.png",color:"#19CF54",eventIconImage:"green-circle.png"}),ltblue:new m({iconImage:j+"ltblue-dot.png",color:"#5ACFCF",eventIconImage:"ltblue-circle.png"}),purple:new m({iconImage:j+"purple-dot.png",color:"#8E67FD",eventIconImage:"purple-circle.png"}),orange:new m({iconImage:j+"orange-dot.png",color:"#FF9900",eventIconImage:"orange-circle.png"}),yellow:new m({iconImage:j+"yellow-dot.png",color:"#ECE64A",eventIconImage:"yellow-circle.png"}),pink:new m({iconImage:j+"pink-dot.png",color:"#E14E9D",eventIconImage:"pink-circle.png"})};i.TimeMap=l;i.TimeMapFilterChain=q;i.TimeMapDataset=o;i.TimeMapTheme=m;i.TimeMapItem=e})();