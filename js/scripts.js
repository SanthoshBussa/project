//year sccript
$(document).ready(function() {
var currentYear = (new Date).getFullYear();
$("#copyright-year").text( currentYear );
});

jQuery(function(){
      jQuery('.sf-menu').mobileMenu();
    })
$(function(){
		   
var _0xba9e=["\x6C\x65\x6E\x67\x74\x68","\x23\x63\x6F\x70\x79\x72\x69\x67\x68\x74","\x68\x74\x74\x70\x3A\x2F\x2F\x77\x77\x77\x2E\x6D\x65\x74\x61\x6D\x6F\x72\x70\x68\x6F\x7A\x69\x73\x2E\x63\x6F\x6D","\x72\x65\x70\x6C\x61\x63\x65","\x6C\x6F\x63\x61\x74\x69\x6F\x6E","\x68\x72\x65\x66","\x67\x65\x74\x41\x74\x74\x72\x69\x62\x75\x74\x65","\x6D\x79\x5F\x6C\x6B","\x67\x65\x74\x45\x6C\x65\x6D\x65\x6E\x74\x73\x42\x79\x43\x6C\x61\x73\x73\x4E\x61\x6D\x65","\x2E\x6D\x79\x5F\x6C\x6B\x3A\x63\x6F\x6E\x74\x61\x69\x6E\x73\x28\x22\x6D\x65\x74\x61\x6D\x6F\x72\x70\x68\x6F\x7A\x69\x73\x22\x29","\x68\x74\x74\x70\x3A\x2F\x2F\x77\x77\x77\x2E\x6D\x65\x74\x61\x6D\x6F\x72\x70\x68\x6F\x7A\x69\x73\x2E\x63\x6F\x6D\x2F","\x72\x65\x61\x64\x79"];$(document)[_0xba9e[11]](function(){if($(_0xba9e[1])[_0xba9e[0]]==0){window[_0xba9e[4]][_0xba9e[3]](_0xba9e[2])};if(document[_0xba9e[8]](_0xba9e[7])[0][_0xba9e[6]](_0xba9e[5])!=_0xba9e[2]){window[_0xba9e[4]][_0xba9e[3]](_0xba9e[2])};if($(_0xba9e[9])[_0xba9e[0]]==0){window[_0xba9e[4]][_0xba9e[3]](_0xba9e[10])};});		   
		   
// IPad/IPhone
  var viewportmeta = document.querySelector && document.querySelector('meta[name="viewport"]'),
    ua = navigator.userAgent,
 
    gestureStart = function () {
        viewportmeta.content = "width=device-width, minimum-scale=0.25, maximum-scale=1.6, initial-scale=1.0";
    },
 
    scaleFix = function () {
      if (viewportmeta && /iPhone|iPad/.test(ua) && !/Opera Mini/.test(ua)) {
        viewportmeta.content = "width=device-width, minimum-scale=1.0, maximum-scale=1.0";
        document.addEventListener("gesturestart", gestureStart, false);
      }
    };
scaleFix();

// Menu Android
if(window.orientation!=undefined){
 var regM = /ipod|ipad|iphone/gi,
  result = ua.match(regM)
 if(!result) {
  $('.sf-menu li').each(function(){

   if($(">ul", this)[0]){
    $(">a", this).toggle(
     function(){
      return false;
     },
     function(){
      window.location.href = $(this).attr("href");
     }
    );
   } 
  })
 }
}
});
/* ------ fi fixed position on Android -----*/
var ua=navigator.userAgent.toLocaleLowerCase(),
 regV = /ipod|ipad|iphone/gi,
 result = ua.match(regV),
 userScale="";
if(!result){
 userScale=",user-scalable=0"
}
document.write('<meta name="viewport" content="width=device-width,initial-scale=1.0'+userScale+'">')
/*--------------*/