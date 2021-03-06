                function setCookie(sName, sValue, oExpires, sPath, sDomain, bSecure) {
                    var sCookie = sName + "=" + encodeURIComponent(sValue);
               
                    if (oExpires) {
                        sCookie += "; expires=" + oExpires.toGMTString();
                    }
               
                    if (sPath) {
                        sCookie += "; path=" + sPath;
                    }
               
                    if (sDomain) {
                        sCookie += "; domain=" + sDomain;
                    }
               
                    if (bSecure) {
                        sCookie += "; secure";
                    }
               
                    document.cookie = sCookie;
                }
                               
                function getCookie(sName) {
               
                    var sRE = "(?:; )?" + sName + "=([^;]*);?";
                    var oRE = new RegExp(sRE);
                   
                    if (oRE.test(document.cookie)) {
                        return decodeURIComponent(RegExp["$1"]);
                    } else {
                        return null;
                    }
               
                }                

                function deleteCookie(sName, sPath, sDomain) {
                    var sCookie = sName + "=; expires=" + (new Date(0)).toGMTString();
                    if (sPath) {
                        sCookie += "; path=" + sPath;
                    }
               
                    if (sDomain) {
                        sCookie += "; domain=" + sDomain;
                    }
                   
                    document.cookie = sCookie;
                }