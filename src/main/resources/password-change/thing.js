if (typeof OpenAjax !== "undefined" && typeof OpenAjax.hub.registerLibrary !== "undefined") { OpenAjax.hub.registerLibrary("jsf", "www.sun.com", "2.2", null) } if (!((jsf && jsf.specversion && jsf.specversion >= 20000) && (jsf.implversion && jsf.implversion >= 3))) {
    var jsf = {};
    jsf.ajax = function () {
        var eventListeners = [];
        var errorListeners = [];
        var delayHandler = null;
        var isIE = function isIE() {
            if (typeof isIECache !== "undefined") { return isIECache } isIECache = document.all && window.ActiveXObject && navigator.userAgent.toLowerCase().indexOf("msie") > -1 && navigator.userAgent.toLowerCase().indexOf("opera") == -1;
            return isIECache
        };
        var isIECache;
        var getIEVersion = function getIEVersion() { if (typeof IEVersionCache !== "undefined") { return IEVersionCache } if (/MSIE ([0-9]+)/.test(navigator.userAgent)) { IEVersionCache = parseInt(RegExp.$1) } else { IEVersionCache = -1 } return IEVersionCache };
        var IEVersionCache;
        var isAutoExec = function isAutoExec() {
            try {
                if (typeof isAutoExecCache !== "undefined") { return isAutoExecCache } var autoExecTestString = "<script>var mojarra = mojarra || {};
                mojarra.autoExecTest = true;
<\/script>";
                var tempElement = document.createElement("span");
                tempElement.innerHTML = autoExecTestString;
                var body = document.getElementsByTagName("body")[0];
                var tempNode = body.appendChild(tempElement);
                if (mojarra && mojarra.autoExecTest) {
                    isAutoExecCache = true;
                    delete mojarra.autoExecTest
                } else { isAutoExecCache = false } deleteNode(tempNode);
                return isAutoExecCache
            } catch (ex) { if (typeof isAutoExecCache === "undefined") { isAutoExecCache = false } return isAutoExecCache }
        };
        var isAutoExecCache;
        var getTransport = function getTransport(context) {
            var returnVal;
            if (typeof context !== "undefined" && context !== null && context.includesInputFile && context.form.enctype === "multipart/form-data") {
                returnVal = new FrameTransport(context);
                return returnVal
            } var methods = [function () { return new XMLHttpRequest() }, function () { return new ActiveXObject("Msxml2.XMLHTTP") }, function () { return new ActiveXObject("Microsoft.XMLHTTP") }];
            for (var i = 0, len = methods.length;
                i < len;
                i++) { try { returnVal = methods[i]() } catch (e) { continue } return returnVal } throw new Error("Could not create an XHR object.")
        };
        var FrameTransport = function FrameTransport(context) {
            this.context = context;
            this.frame = null;
            this.FRAME_ID = "JSFFrameId";
            this.FRAME_PARTIAL_ID = "Faces-Request";
            this.partial = null;
            this.aborted = false;
            this.responseText = null;
            this.responseXML = null;
            this.readyState = 0;
            this.requestHeader = {};
            this.status = null;
            this.method = null;
            this.url = null;
            this.requestParams = null
        };
        FrameTransport.prototype = {
            setRequestHeader: function (key, value) { if (typeof (value) !== "undefined") { this.requestHeader[key] = value } }, open: function (method, url, async) {
                this.method = method;
                this.url = url;
                this.async = async;
                this.frame = document.getElementById(this.FRAME_ID);
                if (this.frame) {
                    this.frame.parentNode.removeChild(this.frame);
                    this.frame = null
                } if (!this.frame) {
                    if ((!isIE() && !isIE9Plus())) {
                        this.frame = document.createElement("iframe");
                        this.frame.src = "about:blank";
                        this.frame.id = this.FRAME_ID;
                        this.frame.name = this.FRAME_ID;
                        this.frame.type = "content";
                        this.frame.collapsed = "true";
                        this.frame.style = "visibility:hidden";
                        this.frame.width = "0";
                        this.frame.height = "0";
                        this.frame.style = "border:0";
                        this.frame.frameBorder = 0;
                        document.body.appendChild(this.frame);
                        this.frame.onload = bind(this, this.callback)
                    } else {
                        var div = document.createElement("div");
                        div.id = "frameDiv";
                        div.innerHTML = "<iframe id='" + this.FRAME_ID + "' name='" + this.FRAME_ID + "' style='display:none;
                        ' src='about: blank' type='content' onload='this.onload_cb();
                        '  ></iframe>";
                        document.body.appendChild(div);
                        this.frame = document.getElementById(this.FRAME_ID);
                        this.frame.onload_cb = bind(this, this.callback)
                    }
                } this.partial = document.createElement("input");
                this.partial.setAttribute("type", "hidden");
                this.partial.setAttribute("id", this.FRAME_PARTIAL_ID);
                this.partial.setAttribute("name", this.FRAME_PARTIAL_ID);
                this.partial.setAttribute("value", "partial/ajax");
                this.context.form.appendChild(this.partial);
                this.readyState = 1
            }, send: function (data, namingContainerId) {
                var evt = {};
                this.context.form.target = this.frame.name;
                this.context.form.method = this.method;
                if (this.url) { this.context.form.action = this.url } this.readyState = 3;
                this.onreadystatechange(evt);
                var ddata = decodeURIComponent(data);
                var dataArray = ddata.split("&");
                var input;
                this.requestParams = new Array();
                for (var i = 0;
                    i < dataArray.length;
                    i++) {
                        var nameValue = dataArray[i].split("=");
                    if (nameValue[0] === namingContainerId + "javax.faces.source" || nameValue[0] === namingContainerId + "javax.faces.partial.event" || nameValue[0] === namingContainerId + "javax.faces.partial.execute" || nameValue[0] === namingContainerId + "javax.faces.partial.render" || nameValue[0] === namingContainerId + "javax.faces.partial.ajax" || nameValue[0] === namingContainerId + "javax.faces.behavior.event") {
                        input = document.createElement("input");
                        input.setAttribute("type", "hidden");
                        input.setAttribute("id", nameValue[0]);
                        input.setAttribute("name", nameValue[0]);
                        input.setAttribute("value", nameValue[1]);
                        this.context.form.appendChild(input);
                        this.requestParams.push(nameValue[0])
                    }
                } this.requestParams.push(this.FRAME_PARTIAL_ID);
                this.context.form.submit()
            }, abort: function () { this.aborted = true }, onreadystatechange: function (evt) { }, callback: function () {
                if (this.aborted) { return } var iFrameDoc;
                var docBody;
                try {
                    var evt = {};
                    iFrameDoc = this.frame.contentWindow.document || this.frame.contentDocument || this.frame.document;
                    docBody = iFrameDoc.body || iFrameDoc.documentElement;
                    this.responseText = docBody.innerHTML;
                    this.responseXML = iFrameDoc.XMLDocument || iFrameDoc;
                    this.status = 201;
                    this.readyState = 4;
                    this.onreadystatechange(evt)
                } finally { this.cleanupReqParams() }
            }, cleanupReqParams: function () {
                for (var i = 0;
                    i < this.requestParams.length;
                    i++) {
                        var elements = this.context.form.childNodes;
                    for (var j = 0;
                        j < elements.length;
                        j++) {
                            if (!elements[j].type === "hidden") { continue } if (elements[j].name === this.requestParams[i]) {
                                var node = this.context.form.removeChild(elements[j]);
                                node = null;
                                break
                            }
                    }
                }
            }
        };
        var bind = function (scope, fn) { return function () { fn.apply(scope, arguments) } };
        var hasInputFileControl = function (form) {
            var returnVal = false;
            var inputs = form.getElementsByTagName("input");
            if (inputs !== null && typeof inputs !== "undefined") {
                for (var i = 0;
                    i < inputs.length;
                    i++) {
                        if (inputs[i].type === "file") {
                            returnVal = true;
                            break
                        }
                }
            } return returnVal
        };
        var $ = function $() {
            var results = [], element;
            for (var i = 0;
                i < arguments.length;
                i++) {
                    element = arguments[i];
                if (typeof element == "string") { element = document.getElementById(element) } results.push(element)
            } return results.length > 1 ? results : results[0]
        };
        var getForm = function getForm(element) {
            if (element) {
                var form = $(element);
                while (form) { if (form.nodeName && (form.nodeName.toLowerCase() == "form")) { return form } if (form.form) { return form.form } if (form.parentNode) { form = form.parentNode } else { form = null } } return document.forms[0]
            } return null
        };
        var getFormForId = function getFormForId(id) {
            if (id) {
                var node = document.getElementById(id);
                while (node) { if (node.nodeName && (node.nodeName.toLowerCase() == "form")) { return node } if (node.form) { return node.form } if (node.parentNode) { node = node.parentNode } else { node = null } }
            } return null
        };
        var isInArray = function isInArray(array, value) {
            for (var i = 0;
                i < array.length;
                i++) { if (array[i] === value) { return true } } return false
        };
        var globalEval = function globalEval(src) {
            if (window.execScript) {
                window.execScript(src);
                return
            } var fn = function () { window.eval.call(window, src) };
            fn()
        };
        var getScripts = function getScripts(str) {
            var findscripts = /<script[^>]*>([\S\s]*?)<\/script>/igm;
            var findscript = /<script([^>]*)>([\S\s]*?)<\/script>/im;
            var findtype = /type="([\S]*?)"/im;
            var initialnodes = [];
            var scripts = [];
            initialnodes = str.match(findscripts);
            while (!!initialnodes && initialnodes.length > 0) {
                var scriptStr = [];
                scriptStr = initialnodes.shift().match(findscript);
                var type = [];
                type = scriptStr[1].match(findtype);
                if (!!type && type[1]) { if (type[1] !== "text/javascript") { continue } } scripts.push(scriptStr)
            } return scripts
        };
        var removeScripts = function removeScripts(str) { return str.replace(/<script[^>]*type="text\/javascript"[^>]*>([\S\s]*?)<\/script>/igm, "") };
        var runScripts = function runScripts(scripts) {
            if (!scripts || scripts.length === 0) { return } var loadedScripts = document.getElementsByTagName("script");
            var loadedScriptUrls = [];
            for (var i = 0;
                i < loadedScripts.length;
                i++) {
                    var scriptNode = loadedScripts[i];
                var url = scriptNode.getAttribute("src");
                if (url) { loadedScriptUrls.push(url) }
            } var head = document.head || document.getElementsByTagName("head")[0] || document.documentElement;
            runScript(head, loadedScriptUrls, scripts, 0)
        };
        var runScript = function runScript(head, loadedScriptUrls, scripts, index) {
            if (index >= scripts.length) { return } var findsrc = /src="([\S]*?)"/im;
            var stripStart = /^\s*(<!--)*\s*(\/\/)*\s*(\/\*)*\s*\n*\**\n*\s*\*.*\n*\s*\*\/(<!\[CDATA\[)*/;
            var scriptStr = scripts[index];
            var src = scriptStr[1].match(findsrc);
            var scriptLoadedViaUrl = false;
            if (!!src && src[1]) {
                var url = unescapeHTML(src[1]);
                if (loadedScriptUrls.indexOf(url) < 0) {
                    var scriptNode = document.createElement("script");
                    scriptNode.type = "text/javascript";
                    scriptNode.src = url;
                    scriptNode.onload = scriptNode.onreadystatechange = function (_, abort) {
                        if (abort || !scriptNode.readyState || /loaded|complete/.test(scriptNode.readyState)) {
                            scriptNode.onload = scriptNode.onreadystatechange = null;
                            scriptNode = null;
                            runScript(head, loadedScriptUrls, scripts, index + 1)
                        }
                    };
                    head.insertBefore(scriptNode, null);
                    scriptLoadedViaUrl = true
                }
            } else {
                if (!!scriptStr && scriptStr[2]) {
                    var script = scriptStr[2].replace(stripStart, "");
                    if (!!script) {
                        var scriptNode = document.createElement("script");
                        scriptNode.type = "text/javascript";
                        scriptNode.text = script;
                        head.appendChild(scriptNode);
                        head.removeChild(scriptNode)
                    }
                }
            } if (!scriptLoadedViaUrl) { runScript(head, loadedScriptUrls, scripts, index + 1) }
        };
        var runStylesheets = function runStylesheets(str) {
            var findlinks = /<link[^>]*\/>/igm;
            var findlink = /<link([^>]*)\/>/im;
            var findtype = /type="([\S]*?)"/im;
            var findhref = /href="([\S]*?)"/im;
            var stylesheets = [];
            var loadedStylesheetUrls = null;
            var head = document.head || document.getElementsByTagName("head")[0] || document.documentElement;
            var initialnodes = str.match(findlinks);
            while (!!initialnodes && initialnodes.length > 0) {
                var linkStr = initialnodes.shift().match(findlink);
                var type = linkStr[1].match(findtype);
                if (!type || type[1] !== "text/css") { continue } var href = linkStr[1].match(findhref);
                if (!!href && href[1]) {
                    if (loadedStylesheetUrls == null) {
                        var loadedLinks = document.getElementsByTagName("link");
                        loadedStylesheetUrls = [];
                        for (var i = 0;
                            i < loadedLinks.length;
                            i++) {
                                var linkNode = loadedLinks[i];
                            if (linkNode.getAttribute("type") === "text/css") {
                                var url = linkNode.getAttribute("href");
                                if (url) { loadedStylesheetUrls.push(url) }
                            }
                        }
                    } var url = unescapeHTML(href[1]);
                    if (loadedStylesheetUrls.indexOf(url) < 0) {
                        var linkNode = document.createElement("link");
                        linkNode.type = "text/css";
                        linkNode.rel = "stylesheet";
                        linkNode.href = url;
                        head.insertBefore(linkNode, null)
                    }
                }
            }
        };
        var elementReplaceStr = function elementReplaceStr(element, tempTagName, src) {
            var temp = document.createElement(tempTagName);
            if (element.id) { temp.id = element.id } if (element.nodeName.toLowerCase() === "head") { throw new Error("Attempted to replace a head element - this is not allowed.") } else {
                var scripts = [];
                if (isAutoExec()) { temp.innerHTML = src } else {
                    scripts = getScripts(src);
                    src = removeScripts(src);
                    temp.innerHTML = src
                }
            } replaceNode(temp, element);
            cloneAttributes(temp, element);
            runScripts(scripts)
        };
        var getText = function getText(oNode, deep) {
            var Node = { ELEMENT_NODE: 1, ATTRIBUTE_NODE: 2, TEXT_NODE: 3, CDATA_SECTION_NODE: 4, ENTITY_REFERENCE_NODE: 5, ENTITY_NODE: 6, PROCESSING_INSTRUCTION_NODE: 7, COMMENT_NODE: 8, DOCUMENT_NODE: 9, DOCUMENT_TYPE_NODE: 10, DOCUMENT_FRAGMENT_NODE: 11, NOTATION_NODE: 12 };
            var s = "";
            var nodes = oNode.childNodes;
            for (var i = 0;
                i < nodes.length;
                i++) {
                    var node = nodes[i];
                var nodeType = node.nodeType;
                if (nodeType == Node.TEXT_NODE || nodeType == Node.CDATA_SECTION_NODE) { s += node.data } else { if (deep === true && (nodeType == Node.ELEMENT_NODE || nodeType == Node.DOCUMENT_NODE || nodeType == Node.DOCUMENT_FRAGMENT_NODE)) { s += getText(node, true) } }
            } return s
        };
        var PARSED_OK = "Document contains no parsing errors";
        var PARSED_EMPTY = "Document is empty";
        var PARSED_UNKNOWN_ERROR = "Not well-formed or other error";
        var getParseErrorText;
        if (isIE()) {
            getParseErrorText = function (oDoc) {
                var parseErrorText = PARSED_OK;
                if (oDoc && oDoc.parseError && oDoc.parseError.errorCode && oDoc.parseError.errorCode !== 0) {
                    parseErrorText = "XML Parsing Error: " + oDoc.parseError.reason + "\nLocation: " + oDoc.parseError.url + "\nLine Number " + oDoc.parseError.line + ", Column " + oDoc.parseError.linepos + ":\n" + oDoc.parseError.srcText + "\n";
                    for (var i = 0;
                        i < oDoc.parseError.linepos;
                        i++) { parseErrorText += "-" } parseErrorText += "^\n"
                } else { if (oDoc.documentElement === null) { parseErrorText = PARSED_EMPTY } } return parseErrorText
            }
        } else {
            getParseErrorText = function (oDoc) {
                var parseErrorText = PARSED_OK;
                if ((!oDoc) || (!oDoc.documentElement)) { parseErrorText = PARSED_EMPTY } else {
                    if (oDoc.documentElement.tagName == "parsererror") {
                        parseErrorText = oDoc.documentElement.firstChild.data;
                        parseErrorText += "\n" + oDoc.documentElement.firstChild.nextSibling.firstChild.data
                    } else {
                        if (oDoc.getElementsByTagName("parsererror").length > 0) {
                            var parsererror = oDoc.getElementsByTagName("parsererror")[0];
                            parseErrorText = getText(parsererror, true) + "\n"
                        } else { if (oDoc.parseError && oDoc.parseError.errorCode !== 0) { parseErrorText = PARSED_UNKNOWN_ERROR } }
                    }
                } return parseErrorText
            }
        } if ((typeof (document.importNode) == "undefined") && isIE()) {
            try {
                document.importNode = function (oNode, bChildren) {
                    var tmp;
                    if (oNode.nodeName == "#text") { return document.createTextNode(oNode.data) } else { if (oNode.nodeName == "tbody" || oNode.nodeName == "tr") { tmp = document.createElement("table") } else { if (oNode.nodeName == "td") { tmp = document.createElement("tr") } else { if (oNode.nodeName == "option") { tmp = document.createElement("select") } else { tmp = document.createElement("div") } } } if (bChildren) { tmp.innerHTML = oNode.xml ? oNode.xml : oNode.outerHTML } else { tmp.innerHTML = oNode.xml ? oNode.cloneNode(false).xml : oNode.cloneNode(false).outerHTML } return tmp.getElementsByTagName("*")[0] }
                }
            } catch (e) { }
        } var Node = { ELEMENT_NODE: 1, ATTRIBUTE_NODE: 2, TEXT_NODE: 3, CDATA_SECTION_NODE: 4, ENTITY_REFERENCE_NODE: 5, ENTITY_NODE: 6, PROCESSING_INSTRUCTION_NODE: 7, COMMENT_NODE: 8, DOCUMENT_NODE: 9, DOCUMENT_TYPE_NODE: 10, DOCUMENT_FRAGMENT_NODE: 11, NOTATION_NODE: 12 };
        var clearEvents = function clearEvents(node) {
            if (!node) { return } if (node.nodeType == Node.TEXT_NODE || node.nodeType == Node.COMMENT_NODE) { return } var events = ["abort", "blur", "change", "error", "focus", "load", "reset", "resize", "scroll", "select", "submit", "unload", "keydown", "keypress", "keyup", "click", "mousedown", "mousemove", "mouseout", "mouseover", "mouseup", "dblclick"];
            try { for (var e in events) { if (events.hasOwnProperty(e)) { node[e] = null } } } catch (ex) { }
        };
        var isIE9Plus = function isIE9Plus() {
            var iev = getIEVersion();
            if (iev >= 9) { return true } else { return false }
        };
        var deleteNode = function deleteNode(node) {
            if (!node) { return } if (!node.parentNode) { return } if (!isIE() || (isIE() && isIE9Plus())) {
                node.parentNode.removeChild(node);
                return
            } if (node.nodeName.toLowerCase() === "body") {
                deleteChildren(node);
                try { node.outerHTML = "" } catch (ex) { } return
            } var temp = node.ownerDocument.createElement("div");
            var parent = node.parentNode;
            temp.appendChild(parent.removeChild(node));
            try { temp.outerHTML = "" } catch (ex) { }
        };
        var deleteChildren = function deleteChildren(node) {
            if (!node) { return } for (var x = node.childNodes.length - 1;
                x >= 0;
                x--) {
                    var childNode = node.childNodes[x];
                deleteNode(childNode)
            }
        };
        var copyChildNodes = function copyChildNodes(nodeFrom, nodeTo) {
            if ((!nodeFrom) || (!nodeTo)) { throw "Both source and destination nodes must be provided" } deleteChildren(nodeTo);
            var nodes = nodeFrom.childNodes;
            if (nodeFrom.ownerDocument == nodeTo.ownerDocument) { while (nodeFrom.firstChild) { nodeTo.appendChild(nodeFrom.firstChild) } } else {
                var ownerDoc = nodeTo.nodeType == Node.DOCUMENT_NODE ? nodeTo : nodeTo.ownerDocument;
                var i;
                if (typeof (ownerDoc.importNode) != "undefined") {
                    for (i = 0;
                        i < nodes.length;
                        i++) { nodeTo.appendChild(ownerDoc.importNode(nodes[i], true)) }
                } else {
                    for (i = 0;
                        i < nodes.length;
                        i++) { nodeTo.appendChild(nodes[i].cloneNode(true)) }
                }
            }
        };
        var replaceNode = function replaceNode(newNode, node) {
            if (isIE()) {
                node.parentNode.insertBefore(newNode, node);
                deleteNode(node)
            } else { node.parentNode.replaceChild(newNode, node) }
        };
        var propertyToAttribute = function propertyToAttribute(name) { if (name === "className") { return "class" } else { if (name === "xmllang") { return "xml:lang" } else { return name.toLowerCase() } } };
        var isFunctionNative = function isFunctionNative(func) { return /^\s*function[^{]+{\s*\[native code\]\s*}\s*$/.test(String(func)) };
        var detectAttributes = function detectAttributes(element) {
            if (element.hasAttribute && isFunctionNative(element.hasAttribute)) { return function (name) { return element.hasAttribute(name) } } else {
                try {
                    element.getAttribute;
                    var html = element.outerHTML;
                    var startTag = html.match(/^<[^>]*>/)[0];
                    return function (name) { return startTag.indexOf(name + "=") > -1 }
                } catch (ex) { return function (name) { return element.getAttribute(name) } }
            }
        };
        var cloneAttributes = function cloneAttributes(target, source) {
            var coreElementProperties = ["className", "title", "lang", "xmllang"];
            var inputElementProperties = ["name", "value", "size", "maxLength", "src", "alt", "useMap", "tabIndex", "accessKey", "accept", "type"];
            var inputElementBooleanProperties = ["checked", "disabled", "readOnly"];
            var listenerNames = ["onclick", "ondblclick", "onmousedown", "onmousemove", "onmouseout", "onmouseover", "onmouseup", "onkeydown", "onkeypress", "onkeyup", "onhelp", "onblur", "onfocus", "onchange", "onload", "onunload", "onabort", "onreset", "onselect", "onsubmit"];
            var sourceAttributeDetector = detectAttributes(source);
            var targetAttributeDetector = detectAttributes(target);
            var isInputElement = target.nodeName.toLowerCase() === "input";
            var propertyNames = isInputElement ? coreElementProperties.concat(inputElementProperties) : coreElementProperties;
            var isXML = !source.ownerDocument.contentType || source.ownerDocument.contentType == "text/xml";
            for (var iIndex = 0, iLength = propertyNames.length;
                iIndex < iLength;
                iIndex++) {
                    var propertyName = propertyNames[iIndex];
                var attributeName = propertyToAttribute(propertyName);
                if (sourceAttributeDetector(attributeName)) {
                    if (attributeName == "class") { if (isIE() && (source.getAttribute(propertyName) === source[propertyName])) { attributeName = propertyName } } var newValue = isXML ? source.getAttribute(attributeName) : source[propertyName];
                    var oldValue = target[propertyName];
                    if (oldValue != newValue) { target[propertyName] = newValue }
                } else { if (attributeName == "value" && (target.type != "checkbox" && target.type != "radio")) { target[propertyName] = "" } target.removeAttribute(attributeName) }
            } var booleanPropertyNames = isInputElement ? inputElementBooleanProperties : [];
            for (var jIndex = 0, jLength = booleanPropertyNames.length;
                jIndex < jLength;
                jIndex++) {
                    var booleanPropertyName = booleanPropertyNames[jIndex];
                var newBooleanValue = source[booleanPropertyName];
                var oldBooleanValue = target[booleanPropertyName];
                if (oldBooleanValue != newBooleanValue) { target[booleanPropertyName] = newBooleanValue }
            } if (sourceAttributeDetector("style")) {
                var newStyle;
                var oldStyle;
                if (isIE()) {
                    newStyle = source.style.cssText;
                    oldStyle = target.style.cssText;
                    if (newStyle != oldStyle) { target.style.cssText = newStyle }
                } else {
                    newStyle = source.getAttribute("style");
                    oldStyle = target.getAttribute("style");
                    if (newStyle != oldStyle) { target.setAttribute("style", newStyle) }
                }
            } else { if (targetAttributeDetector("style")) { target.removeAttribute("style") } } if (!isIE() && source.dir != target.dir) { if (sourceAttributeDetector("dir")) { target.dir = source.dir } else { if (targetAttributeDetector("dir")) { target.dir = "" } } } for (var lIndex = 0, lLength = listenerNames.length;
                lIndex < lLength;
                lIndex++) {
                    var name = listenerNames[lIndex];
                target[name] = source[name] ? source[name] : null;
                if (source[name]) { source[name] = null }
            } try {
                var targetDataset = target.dataset;
                var sourceDataset = source.dataset;
                if (targetDataset || sourceDataset) { for (var tp in targetDataset) { delete targetDataset[tp] } for (var sp in sourceDataset) { targetDataset[sp] = sourceDataset[sp] } }
            } catch (ex) { }
        };
        var elementReplace = function elementReplace(newElement, origElement) {
            copyChildNodes(newElement, origElement);
            origElement.innerHTML = origElement.innerHTML;
            try { cloneAttributes(origElement, newElement) } catch (ex) { if (jsf.getProjectStage() == "Development") { throw new Error("Error updating attributes") } } deleteNode(newElement)
        };
        var getBodyElement = function getBodyElement(docStr) {
            var doc;
            var body;
            if (typeof DOMParser !== "undefined") { doc = (new DOMParser()).parseFromString(docStr, "text/xml") } else {
                if (typeof ActiveXObject !== "undefined") {
                    doc = new ActiveXObject("MSXML2.DOMDocument");
                    doc.loadXML(docStr)
                } else { throw new Error("You don't seem to be running a supported browser") }
            } if (getParseErrorText(doc) !== PARSED_OK) { throw new Error(getParseErrorText(doc)) } body = doc.getElementsByTagName("body")[0];
            if (!body) { throw new Error("Can't find body tag in returned document.") } return body
        };
        var getEncodedUrlElement = function getEncodedUrlElement(form) {
            var encodedUrlElement = form["javax.faces.encodedURL"];
            if (encodedUrlElement) { return encodedUrlElement } else {
                var formElements = form.elements;
                for (var i = 0, length = formElements.length;
                    i < length;
                    i++) {
                        var formElement = formElements[i];
                    if (formElement.name && (formElement.name.indexOf("javax.faces.encodedURL") >= 0)) { return formElement }
                }
            } return undefined
        };
        var getViewStateElement = function getViewStateElement(form) {
            var viewStateElement = form["javax.faces.ViewState"];
            if (viewStateElement) { return viewStateElement } else {
                var formElements = form.elements;
                for (var i = 0, length = formElements.length;
                    i < length;
                    i++) {
                        var formElement = formElements[i];
                    if (formElement.name && (formElement.name.indexOf("javax.faces.ViewState") >= 0)) { return formElement }
                }
            } return undefined
        };
        var doUpdate = function doUpdate(element, context, partialResponseId) {
            var id, content, markup, state, windowId;
            var stateForm, windowIdForm;
            var scripts = [];
            id = element.getAttribute("id");
            var viewStateRegex = new RegExp("javax.faces.ViewState" + jsf.separatorchar + ".*$");
            var windowIdRegex = new RegExp("^.*" + jsf.separatorchar + "javax.faces.ClientWindow" + jsf.separatorchar + ".*$");
            if (id.match(viewStateRegex)) {
                state = element.firstChild;
                if (typeof context.formid !== "undefined" && context.formid !== null) { stateForm = getFormForId(context.formid) } else { stateForm = getFormForId(context.element.id) } if (!stateForm || !stateForm.elements) { return } var field = getViewStateElement(stateForm);
                if (typeof field == "undefined") {
                    field = document.createElement("input");
                    field.type = "hidden";
                    field.name = "javax.faces.ViewState";
                    stateForm.appendChild(field)
                } if (typeof state.wholeText !== "undefined") { field.value = state.wholeText } else { field.value = state.nodeValue } if (typeof context.render !== "undefined" && context.render !== null) {
                    var temp = context.render.split(" ");
                    for (var i = 0;
                        i < temp.length;
                        i++) {
                            if (temp.hasOwnProperty(i)) {
                                var f = document.forms[temp[i]];
                                if (typeof f !== "undefined" && f !== null && f.id !== context.formid) {
                                    field = getViewStateElement(f);
                                    if (typeof field === "undefined") {
                                        field = document.createElement("input");
                                        field.type = "hidden";
                                        field.name = "javax.faces.ViewState";
                                        f.appendChild(field)
                                    } if (typeof state.wholeText !== "undefined") { field.value = state.wholeText } else { field.value = state.nodeValue }
                                }
                            }
                    }
                } return
            } else {
                if (id.match(windowIdRegex)) {
                    windowId = element.firstChild;
                    windowIdForm = document.getElementById(context.formid);
                    if (!windowIdForm || !windowIdForm.elements) { return } var field = windowIdForm.elements["javax.faces.ClientWindow"];
                    if (typeof field == "undefined") {
                        field = document.createElement("input");
                        field.type = "hidden";
                        field.name = "javax.faces.ClientWindow";
                        windowIdForm.appendChild(field)
                    } field.value = windowId.nodeValue;
                    if (typeof context.render !== "undefined" && context.render !== null) {
                        var temp = context.render.split(" ");
                        for (var i = 0;
                            i < temp.length;
                            i++) {
                                if (temp.hasOwnProperty(i)) {
                                    var f = document.forms[temp[i]];
                                    if (typeof f !== "undefined" && f !== null && f.id !== context.formid) {
                                        field = f.elements["javax.faces.ClientWindow"];
                                        if (typeof field === "undefined") {
                                            field = document.createElement("input");
                                            field.type = "hidden";
                                            field.name = "javax.faces.ClientWindow";
                                            f.appendChild(field)
                                        } field.value = windowId.nodeValue
                                    }
                                }
                        }
                    } return
                }
            } markup = "";
            for (var j = 0;
                j < element.childNodes.length;
                j++) {
                    content = element.childNodes[j];
                markup += content.nodeValue
            } var src = markup;
            if (id === "javax.faces.ViewRoot" || id === "javax.faces.ViewBody") {
                var bodyStartEx = new RegExp("< *body[^>]*>", "gi");
                var bodyEndEx = new RegExp("< */ *body[^>]*>", "gi");
                var newsrc;
                var docBody = document.getElementsByTagName("body")[0];
                var bodyStart = bodyStartEx.exec(src);
                if (bodyStart !== null) {
                    try {
                        runStylesheets(src);
                        scripts = getScripts(src);
                        newsrc = removeScripts(src);
                        elementReplace(getBodyElement(newsrc), docBody);
                        runScripts(scripts)
                    } catch (e) {
                        var srcBody, bodyEnd;
                        bodyEnd = bodyEndEx.exec(src);
                        if (bodyEnd !== null) { srcBody = src.substring(bodyStartEx.lastIndex, bodyEnd.index) } else { srcBody = src.substring(bodyStartEx.lastIndex) } elementReplaceStr(docBody, "body", srcBody)
                    }
                } else { elementReplaceStr(docBody, "body", src) }
            } else {
                if (id === "javax.faces.ViewHead") { throw new Error("javax.faces.ViewHead not supported - browsers cannot reliably replace the head's contents") } else {
                    if (id === "javax.faces.Resource") {
                        runStylesheets(src);
                        scripts = getScripts(src);
                        runScripts(scripts)
                    } else {
                        var d = $(id);
                        if (!d) { throw new Error("During update: " + id + " not found") } var parent = d.parentNode;
                        var html = src.replace(/^\s+/g, "").replace(/\s+$/g, "");
                        var parserElement = document.createElement("div");
                        var tag = d.nodeName.toLowerCase();
                        var tableElements = ["td", "th", "tr", "tbody", "thead", "tfoot"];
                        var isInTable = false;
                        for (var tei = 0, tel = tableElements.length;
                            tei < tel;
                            tei++) {
                                if (tableElements[tei] == tag) {
                                    isInTable = true;
                                    break
                                }
                        } if (isInTable) {
                            if (isAutoExec()) { parserElement.innerHTML = "<table>" + html + "</table>" } else {
                                scripts = getScripts(html);
                                html = removeScripts(html);
                                parserElement.innerHTML = "<table>" + html + "</table>"
                            } var newElement = parserElement.firstChild;
                            while ((null !== newElement) && (id !== newElement.id)) { newElement = newElement.firstChild } parent.replaceChild(newElement, d);
                            runScripts(scripts)
                        } else {
                            if (d.nodeName.toLowerCase() === "input") {
                                parserElement = document.createElement("div");
                                parserElement.innerHTML = html;
                                newElement = parserElement.firstChild;
                                cloneAttributes(d, newElement);
                                deleteNode(parserElement)
                            } else {
                                if (html.length > 0) {
                                    if (isAutoExec()) { parserElement.innerHTML = html } else {
                                        scripts = getScripts(html);
                                        html = removeScripts(html);
                                        parserElement.innerHTML = html
                                    } replaceNode(parserElement.firstChild, d);
                                    deleteNode(parserElement);
                                    runScripts(scripts)
                                }
                            }
                        }
                    }
                }
            }
        };
        var doDelete = function doDelete(element) {
            var id = element.getAttribute("id");
            var target = $(id);
            deleteNode(target)
        };
        var doInsert = function doInsert(element) {
            var tablePattern = new RegExp("<\\s*(td|th|tr|tbody|thead|tfoot)", "i");
            var scripts = [];
            var target = $(element.firstChild.getAttribute("id"));
            var parent = target.parentNode;
            var html = element.firstChild.firstChild.nodeValue;
            var isInTable = tablePattern.test(html);
            if (!isAutoExec()) {
                scripts = getScripts(html);
                html = removeScripts(html)
            } var tempElement = document.createElement("div");
            var newElement = null;
            if (isInTable) {
                tempElement.innerHTML = "<table>" + html + "</table>";
                newElement = tempElement.firstChild;
                while ((null !== newElement) && ("" == newElement.id)) { newElement = newElement.firstChild }
            } else {
                tempElement.innerHTML = html;
                newElement = tempElement.firstChild
            } if (element.firstChild.nodeName === "after") { target = target.nextSibling } if (!!tempElement.innerHTML) { parent.insertBefore(newElement, target) } runScripts(scripts);
            deleteNode(tempElement)
        };
        var doAttributes = function doAttributes(element) {
            var id = element.getAttribute("id");
            var target = $(id);
            if (!target) { throw new Error("The specified id: " + id + " was not found in the page.") } var nodes = element.childNodes;
            for (var i = 0;
                i < nodes.length;
                i++) {
                    var name = nodes[i].getAttribute("name");
                var value = nodes[i].getAttribute("value");
                if (name === "disabled") {
                    target.disabled = value === "disabled" || value === "true";
                    return
                } else {
                    if (name === "checked") {
                        target.checked = value === "checked" || value === "on" || value === "true";
                        return
                    } else {
                        if (name == "readonly") {
                            target.readOnly = value === "readonly" || value === "true";
                            return
                        }
                    }
                } if (!isIE()) { if (name === "value") { target.value = value } else { target.setAttribute(name, value) } } else {
                    if (name === "class") { target.className = value } else {
                        if (name === "for") {
                            name = "htmlFor";
                            target.setAttribute(name, value, 0)
                        } else {
                            if (name === "style") { target.style.setAttribute("cssText", value, 0) } else {
                                if (name.substring(0, 2) === "on") {
                                    var c = document.body.appendChild(document.createElement("span"));
                                    try {
                                        c.innerHTML = "<span " + name + '="' + value + '"/>';
                                        target[name] = c.firstChild[name]
                                    } finally { document.body.removeChild(c) }
                                } else { if (name === "dir") { if (jsf.getProjectStage() == "Development") { throw new Error("Cannot set 'dir' attribute in IE") } } else { target.setAttribute(name, value, 0) } }
                            }
                        }
                    }
                }
            }
        };
        var doEval = function doEval(element) {
            var evalText = "";
            var childNodes = element.childNodes;
            for (var i = 0;
                i < childNodes.length;
                i++) { evalText += childNodes[i].nodeValue } globalEval(evalText)
        };
        var Queue = new function Queue() {
            var queue = [];
            var queueSpace = 0;
            this.getSize = function getSize() { return queue.length - queueSpace };
            this.isEmpty = function isEmpty() { return (queue.length === 0) };
            this.enqueue = function enqueue(element) { queue.push(element) };
            this.dequeue = function dequeue() {
                var element = undefined;
                if (queue.length) {
                    element = queue[queueSpace];
                    if (++queueSpace * 2 >= queue.length) {
                        queue = queue.slice(queueSpace);
                        queueSpace = 0
                    }
                } try { return element } finally { element = null }
            };
            this.getOldestElement = function getOldestElement() {
                var element = undefined;
                if (queue.length) { element = queue[queueSpace] } try { return element } finally { element = null }
            }
        }();
        var AjaxEngine = function AjaxEngine(context) {
            var req = {};
            req.url = null;
            req.context = context;
            req.context.sourceid = null;
            req.context.onerror = null;
            req.context.onevent = null;
            req.xmlReq = null;
            req.async = true;
            req.parameters = {};
            req.queryString = null;
            req.method = null;
            req.status = null;
            req.fromQueue = false;
            req.namingContainerId = null;
            req.que = Queue;
            req.xmlReq = getTransport(context);
            if (req.xmlReq === null) { return null } function noop() { } req.xmlReq.onreadystatechange = function () {
                if (req.xmlReq.readyState === 4) {
                    req.onComplete();
                    req.xmlReq.onreadystatechange = noop;
                    req.xmlReq = null
                }
            };
            req.onComplete = function onComplete() {
                if (req.xmlReq.status && (req.xmlReq.status >= 200 && req.xmlReq.status < 300)) {
                    sendEvent(req.xmlReq, req.context, "complete");
                    jsf.ajax.response(req.xmlReq, req.context)
                } else {
                    sendEvent(req.xmlReq, req.context, "complete");
                    sendError(req.xmlReq, req.context, "httpError")
                } var nextReq = req.que.getOldestElement();
                if (nextReq === null || typeof nextReq === "undefined") { return } while ((typeof nextReq.xmlReq !== "undefined" && nextReq.xmlReq !== null) && nextReq.xmlReq.readyState === 4) {
                    req.que.dequeue();
                    nextReq = req.que.getOldestElement();
                    if (nextReq === null || typeof nextReq === "undefined") { break }
                } if (nextReq === null || typeof nextReq === "undefined") { return } if ((typeof nextReq.xmlReq !== "undefined" && nextReq.xmlReq !== null) && nextReq.xmlReq.readyState === 0) {
                    nextReq.fromQueue = true;
                    nextReq.sendRequest()
                }
            };
            req.setupArguments = function (args) { for (var i in args) { if (args.hasOwnProperty(i)) { if (typeof req[i] === "undefined") { req.parameters[i] = args[i] } else { req[i] = args[i] } } } };
            req.sendRequest = function () {
                if (req.xmlReq !== null) {
                    if (!req.que.isEmpty()) {
                        if (!req.fromQueue) {
                            req.que.enqueue(req);
                            return
                        }
                    } if (!req.fromQueue) { req.que.enqueue(req) } if (req.generateUniqueUrl && req.method == "GET") { req.parameters.AjaxRequestUniqueId = new Date().getTime() + "" + req.requestIndex } var content = null;
                    for (var i in req.parameters) { if (req.parameters.hasOwnProperty(i)) { if (req.queryString.length > 0) { req.queryString += "&" } req.queryString += encodeURIComponent(i) + "=" + encodeURIComponent(req.parameters[i]) } } if (req.method === "GET") { if (req.queryString.length > 0) { req.url += ((req.url.indexOf("?") > -1) ? "&" : "?") + req.queryString } } req.xmlReq.open(req.method, req.url, req.async);
                    if (req.method === "POST") {
                        if (typeof req.xmlReq.setRequestHeader !== "undefined") {
                            req.xmlReq.setRequestHeader("Faces-Request", "partial/ajax");
                            req.xmlReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded;
charset = UTF - 8") } content = req.queryString } if (!req.async) { req.xmlReq.onreadystatechange = null } sendEvent(req.xmlReq, req.context, "begin");
 req.xmlReq.send(content, req.namingContainerId);
                            if (!req.async) { req.onComplete() }
                        }
                    };
                    return req
                };
                var sendError = function sendError(request, context, status, description, serverErrorName, serverErrorMessage) {
                    var sent = false;
                    var data = {};
                    data.type = "error";
                    data.status = status;
                    data.source = context.sourceid;
                    data.responseCode = request.status;
                    data.responseXML = request.responseXML;
                    data.responseText = request.responseText;
                    if (typeof data.source === "string") { data.source = document.getElementById(data.source) } if (description) { data.description = description } else { if (status == "httpError") { if (data.responseCode === 0) { data.description = "The Http Transport returned a 0 status code.  This is usually the result of mixing ajax and full requests.  This is usually undesired, for both performance and data integrity reasons." } else { data.description = "There was an error communicating with the server, status: " + data.responseCode } } else { if (status == "serverError") { data.description = serverErrorMessage } else { if (status == "emptyResponse") { data.description = "An empty response was received from the server.  Check server error logs." } else { if (status == "malformedXML") { if (getParseErrorText(data.responseXML) !== PARSED_OK) { data.description = getParseErrorText(data.responseXML) } else { data.description = "An invalid XML response was received from the server." } } } } } } if (status == "serverError") {
                        data.errorName = serverErrorName;
                        data.errorMessage = serverErrorMessage
                    } if (context.onerror) {
                        context.onerror.call(null, data);
                        sent = true
                    } for (var i in errorListeners) {
                        if (errorListeners.hasOwnProperty(i)) {
                            errorListeners[i].call(null, data);
                            sent = true
                        }
                    } if (!sent && jsf.getProjectStage() === "Development") { if (status == "serverError") { alert("serverError: " + serverErrorName + " " + serverErrorMessage) } else { alert(status + ": " + data.description) } }
                };
                var sendEvent = function sendEvent(request, context, status) {
                    var data = {};
                    data.type = "event";
                    data.status = status;
                    data.source = context.sourceid;
                    if (typeof data.source === "string") { data.source = document.getElementById(data.source) } if (status !== "begin") {
                        data.responseCode = request.status;
                        data.responseXML = request.responseXML;
                        data.responseText = request.responseText
                    } if (context.onevent) { context.onevent.call(null, data) } for (var i in eventListeners) { if (eventListeners.hasOwnProperty(i)) { eventListeners[i].call(null, data) } }
                };
                var unescapeHTML = function unescapeHTML(escapedHTML) {
                    return escapedHTML.replace(/&apos;
                        / g, "'").replace(/&quot;
                            / g, '"').replace(/&gt;
                                / g, ">").replace(/&lt;
                                    / g, "<").replace(/&amp;
                                        / g, "&")
                };
                return {
                    addOnError: function addOnError(callback) { if (typeof callback === "function") { errorListeners[errorListeners.length] = callback } else { throw new Error("jsf.ajax.addOnError:  Added a callback that was not a function.") } }, addOnEvent: function addOnEvent(callback) { if (typeof callback === "function") { eventListeners[eventListeners.length] = callback } else { throw new Error("jsf.ajax.addOnEvent: Added a callback that was not a function") } }, request: function request(source, event, options) {
                        var element, form;
                        var all, none;
                        var context = {};
                        if (typeof source === "undefined" || source === null) { throw new Error("jsf.ajax.request: source not set") } if (delayHandler) {
                            clearTimeout(delayHandler);
                            delayHandler = null
                        } if (typeof source === "string") { element = document.getElementById(source) } else { if (typeof source === "object") { element = source } else { throw new Error("jsf.request: source must be object or string") } } if (!element.name) { element.name = element.id } context.element = element;
                        if (typeof (options) === "undefined" || options === null) { options = {} } var onerror = false;
                        if (options.onerror && typeof options.onerror === "function") { onerror = options.onerror } else { if (options.onerror && typeof options.onerror !== "function") { throw new Error("jsf.ajax.request: Added an onerror callback that was not a function") } } var onevent = false;
                        if (options.onevent && typeof options.onevent === "function") { onevent = options.onevent } else { if (options.onevent && typeof options.onevent !== "function") { throw new Error("jsf.ajax.request: Added an onevent callback that was not a function") } } form = getForm(element);
                        if (!form) { throw new Error("jsf.ajax.request: Method must be called within a form") } context.form = form;
                        context.formid = form.id;
                        var viewState = jsf.getViewState(form);
                        var args = {};
                        var namingContainerId = options["com.sun.faces.namingContainerId"];
                        if (typeof (namingContainerId) === "undefined" || options === null) { namingContainerId = "" } args[namingContainerId + "javax.faces.source"] = element.id;
                        if (event && !!event.type) { args[namingContainerId + "javax.faces.partial.event"] = event.type } if ("resetValues" in options) { args[namingContainerId + "javax.faces.partial.resetValues"] = options.resetValues } if (options.execute) {
                            none = options.execute.search(/@none/);
                            if (none < 0) {
                                all = options.execute.search(/@all/);
                                if (all < 0) {
                                    options.execute = options.execute.replace("@this", element.id);
                                    options.execute = options.execute.replace("@form", form.id);
                                    var temp = options.execute.split(" ");
                                    if (!isInArray(temp, element.name)) { options.execute = element.name + " " + options.execute }
                                } else { options.execute = "@all" } args[namingContainerId + "javax.faces.partial.execute"] = options.execute
                            }
                        } else {
                            options.execute = element.name + " " + element.id;
                            args[namingContainerId + "javax.faces.partial.execute"] = options.execute
                        } if (options.render) {
                            none = options.render.search(/@none/);
                            if (none < 0) {
                                all = options.render.search(/@all/);
                                if (all < 0) {
                                    options.render = options.render.replace("@this", element.id);
                                    options.render = options.render.replace("@form", form.id)
                                } else { options.render = "@all" } args[namingContainerId + "javax.faces.partial.render"] = options.render
                            }
                        } var explicitlyDoNotDelay = ((typeof options.delay == "undefined") || (typeof options.delay == "string") && (options.delay.toLowerCase() == "none"));
                        var delayValue;
                        if (typeof options.delay == "number") { delayValue = options.delay } else {
                            var converted = parseInt(options.delay);
                            if (!explicitlyDoNotDelay && isNaN(converted)) { throw new Error("invalid value for delay option: " + options.delay) } delayValue = converted
                        } var checkForTypeFile;
                        context.includesInputFile = false;
                        var ids = options.execute.split(" ");
                        if (ids == "@all") { ids = [form.id] } if (ids) {
                            for (i = 0;
                                i < ids.length;
                                i++) {
                                    var elem = document.getElementById(ids[i]);
                                if (elem) {
                                    var nodeType = elem.nodeType;
                                    if (nodeType == Node.ELEMENT_NODE) {
                                        var elemAttributeDetector = detectAttributes(elem);
                                        if (elemAttributeDetector("type")) {
                                            if (elem.getAttribute("type") === "file") {
                                                context.includesInputFile = true;
                                                break
                                            }
                                        } else {
                                            if (hasInputFileControl(elem)) {
                                                context.includesInputFile = true;
                                                break
                                            }
                                        }
                                    }
                                }
                            }
                        } delete options.execute;
                        delete options.render;
                        delete options.onerror;
                        delete options.onevent;
                        delete options.delay;
                        for (var property in options) { if (options.hasOwnProperty(property)) { if (property != "com.sun.faces.namingContainerId") { args[namingContainerId + property] = options[property] } } } args[namingContainerId + "javax.faces.partial.ajax"] = "true";
                        args.method = "POST";
                        var encodedUrlField = getEncodedUrlElement(form);
                        if (typeof encodedUrlField == "undefined") { args.url = form.action } else { args.url = encodedUrlField.value } var sendRequest = function () {
                            var ajaxEngine = new AjaxEngine(context);
                            ajaxEngine.setupArguments(args);
                            ajaxEngine.queryString = viewState;
                            ajaxEngine.context.onevent = onevent;
                            ajaxEngine.context.onerror = onerror;
                            ajaxEngine.context.sourceid = element.id;
                            ajaxEngine.context.render = args[namingContainerId + "javax.faces.partial.render"];
                            ajaxEngine.namingContainerId = namingContainerId;
                            ajaxEngine.sendRequest();
                            element = null;
                            form = null;
                            sendRequest = null;
                            context = null
                        };
                        if (explicitlyDoNotDelay) { sendRequest() } else { delayHandler = setTimeout(sendRequest, delayValue) }
                    }, response: function response(request, context) {
                        if (!request) { throw new Error("jsf.ajax.response: Request parameter is unset") } if (typeof context.sourceid === "string") { context.sourceid = document.getElementById(context.sourceid) } var xml = request.responseXML;
                        if (xml === null) {
                            sendError(request, context, "emptyResponse");
                            return
                        } if (getParseErrorText(xml) !== PARSED_OK) {
                            sendError(request, context, "malformedXML");
                            return
                        } var partialResponse = xml.getElementsByTagName("partial-response")[0];
                        var partialResponseId = partialResponse.getAttribute("id");
                        var responseType = partialResponse.firstChild;
                        for (var i = 0;
                            i < partialResponse.childNodes.length;
                            i++) {
                                if (partialResponse.childNodes[i].nodeName === "error") {
                                    responseType = partialResponse.childNodes[i];
                                    break
                                }
                        } if (responseType.nodeName === "error") {
                            var errorName = "";
                            var errorMessage = "";
                            var element = responseType.firstChild;
                            if (element.nodeName === "error-name") { if (null != element.firstChild) { errorName = element.firstChild.nodeValue } } element = responseType.firstChild.nextSibling;
                            if (element.nodeName === "error-message") { if (null != element.firstChild) { errorMessage = element.firstChild.nodeValue } } sendError(request, context, "serverError", null, errorName, errorMessage);
                            sendEvent(request, context, "success");
                            return
                        } if (responseType.nodeName === "redirect") {
                            window.location = responseType.getAttribute("url");
                            return
                        } if (responseType.nodeName !== "changes") {
                            sendError(request, context, "malformedXML", "Top level node must be one of: changes, redirect, error, received: " + responseType.nodeName + " instead.");
                            return
                        } var changes = responseType.childNodes;
                        try {
                            for (var i = 0;
                                i < changes.length;
                                i++) {
                                    switch (changes[i].nodeName) {
                                        case "update": doUpdate(changes[i], context, partialResponseId);
                                            break;
                                        case "delete": doDelete(changes[i]);
                                            break;
                                        case "insert": doInsert(changes[i]);
                                            break;
                                        case "attributes": doAttributes(changes[i]);
                                            break;
                                        case "eval": doEval(changes[i]);
                                            break;
                                        case "extension": break;
                                        default: sendError(request, context, "malformedXML", "Changes allowed are: update, delete, insert, attributes, eval, extension.  Received " + changes[i].nodeName + " instead.");
                                            return
                                    }
                            }
                        } catch (ex) {
                            sendError(request, context, "malformedXML", ex.message);
                            return
                        } sendEvent(request, context, "success")
                    }
                }
            }();
            jsf.getProjectStage = function () {
                if (typeof mojarra !== "undefined" && typeof mojarra.projectStageCache !== "undefined") { return mojarra.projectStageCache } var a = document.getElementsByTagName("script");
                var b;
                var e = 0;
                var d;
                var c;
                while (e < a.length) {
                    if (typeof a[e].src === "string" && a[e].src.match("/javax.faces.resource/jsf.js?.*ln=javax.faces")) {
                        b = a[e].src;
                        break
                    } e++
                } if (typeof b == "string") {
                    c = b.match("stage=(.*)");
                    if (c) { d = c[1] }
                } if (typeof d === "undefined" || !d) { d = "Production" } mojarra = mojarra || {};
                mojarra.projectStageCache = d;
                return mojarra.projectStageCache
            };
            jsf.getViewState = function (b) {
                if (!b) { throw new Error("jsf.getViewState:  form must be set") } var d = b.elements;
                var g = d.length;
                var k = [];
                var a = function (l, m) {
                    var j = "";
                    if (k.length > 0) { j = "&" } j += encodeURIComponent(l) + "=" + encodeURIComponent(m);
                    k.push(j)
                };
                for (var f = 0;
                    f < g;
                    f++) {
                        var c = d[f];
                    if (c.name === "") { continue } if (!c.disabled) {
                        switch (c.type) {
                            case "submit": case "reset": case "image": case "file": break;
                            case "select-one": if (c.selectedIndex >= 0) { a(c.name, c.options[c.selectedIndex].value) } break;
                            case "select-multiple": for (var e = 0;
                                e < c.options.length;
                                e++) { if (c.options[e].selected) { a(c.name, c.options[e].value) } } break;
                            case "checkbox": case "radio": if (c.checked) { a(c.name, c.value || "on") } break;
                            default: var h = c.nodeName.toLowerCase();
                                if (h === "input" || h === "select" || h === "button" || h === "object" || h === "textarea") { a(c.name, c.value) } break
                        }
                    }
                } return k.join("")
            };
            jsf.getClientWindow = function (d) {
                var b = "form";
                var e = "javax.faces.ClientWindow";
                var h = function h(p) {
                    var q = p["javax.faces.ClientWindow"];
                    if (q) { return q } else {
                        var m = p.elements;
                        for (var l = 0, o = m.length;
                            l < o;
                            l++) {
                                var n = m[l];
                            if (n.name && (n.name.indexOf("javax.faces.ClientWindow") >= 0)) { return n }
                        }
                    } return undefined
                };
                var g = function (l) {
                    var n = {};
                    var t;
                    var o = 0;
                    for (var m = l.length - 1;
                        m >= 0;
                        m--) {
                            var p = "undefined";
                        var s = l[m];
                        var r = h(s);
                        var q = r && r.value;
                        if (p != typeof q) {
                            if (o > 0 && p == typeof n[q]) { throw Error("Multiple different windowIds found in document") } t = q;
                            n[q] = true;
                            o++
                        }
                    } return t
                };
                var f = function (l) {
                    if (!l) { return document.forms } var n = [];
                    if (!l.tagName) { return [] } else {
                        if (l.tagName.toLowerCase() == b) {
                            n.push(l);
                            return n
                        }
                    } if (l.querySelectorAll) { return l.querySelectorAll(b) } for (var m = l.childNodes.length - 1;
                        m >= 0;
                        m--) {
                            var o = l.childNodes[m];
                        n = n.concat(f(o, b))
                    } return n
                };
                var c = function () {
                    var m = window.location.href;
                    var l = "windowId";
                    var o = new RegExp("[\\?&]" + l + "=([^&#\\;
]*) ");
        var n = o.exec(m);
        if (n != null) { return n[1] } return null
    };
    var k = (d && (typeof d == "string" || d instanceof String)) ? document.getElementById(d) : (d || null);
    var a = f(k);
    var j = g(a);
    return (null != j) ? j : c()
};
jsf.util = {};
jsf.util.chain = function (g, d) {
    if (arguments.length < 3) { return true } var a = (typeof g === "object") ? g : null;
    for (var b = 2;
        b < arguments.length;
        b++) {
            var e = new Function("event", arguments[b]);
        var c = e.call(a, d);
        if (c === false) { return false }
    } return true
};
jsf.separatorchar = ":";
jsf.specversion = 22000;
jsf.implversion = 3 } if (typeof OpenAjax !== "undefined" && typeof OpenAjax.hub.registerLibrary !== "undefined") { OpenAjax.hub.registerLibrary("mojarra", "www.sun.com", "1.0", null) } var mojarra = mojarra || {};
mojarra.dpf = function dpf(c) {
    var b = c.adp;
    if (b !== null) {
        for (var a = 0;
            a < b.length;
            a++) { c.removeChild(b[a]) }
    }
};
mojarra.apf = function apf(e, c) {
    var d = new Array();
    e.adp = d;
    var b = 0;
    for (var a in c) {
        if (c.hasOwnProperty(a)) {
            var g = document.createElement("input");
            g.type = "hidden";
            g.name = a;
            g.value = c[a];
            e.appendChild(g);
            d[b++] = g
        }
    }
};
mojarra.jsfcljs = function jsfcljs(d, c, b) {
    mojarra.apf(d, c);
    var e = d.target;
    if (b) { d.target = b } var a = document.createElement("input");
    a.type = "submit";
    d.appendChild(a);
    a.click();
    d.removeChild(a);
    d.target = e;
    mojarra.dpf(d)
};
mojarra.jsfcbk = function jsfcbk(b, a, c) { return b.call(a, c) };
mojarra.ab = function ab(c, d, g, a, b, f) { if (!f) { f = {} } if (g) { f["javax.faces.behavior.event"] = g } if (a) { f.execute = a } if (b) { f.render = b } jsf.ajax.request(c, d, f) };
