function getAccounts(){
    console.log(222);
    $("#getAccounts").load('/accounts');
}

function unlockAccount(){
    new Url('/unlock',$("#resultUnlock")).
    start($('#accountIndex'),$('#unlockpasswd'),$('#length'));
}

function sendTransaction(){
    new Url('/transaction',$("#resultTransaction")).
    start($('#sendIndex'),$('#receiveIndex'),$('#sendAmount'),$('#sendData'),$('#sendIdentification'));
}

function getTransaction(){
    new Url('/getTransaction',$("#resultGetTransaction"))
    .start($('#identification'));
}

function createAccount(){
    console.log(111);
    new Url('/createAccount',$("#getAccounts"))
    .start($('#password_input'));
}

function Url(u,id){
    var url = new Object();
    url.u = u;
    url.id = u;
    url.exeid = id
    url.count = 0;
    url.start = function(){
        for(var i = 0 ; i<arguments.length ; i++)
            if(arguments[i].val() != ''){
                url.u = url.u + '/' + arguments[i].val();
                url.count++;
            }
            if(url.count == arguments.length){
                url.exeid.load(url.u);
            }else{
                url.exeid.load("/error"+url.id);
            }
    };
    return url;
}


function Send(sendId , renderBox){
    var send = new Object();
    send.id = sendId;
    send.renderbox = renderBox;
    send.start = function(){
        sendId.click(function(event) {
         　　event.preventDefault();
//             $('#accountLi').addClass("active");
         　　var paramUrl = send.id.attr("href");
             console.log(paramUrl);
         　　$.ajax({
         　　　　type : "GET",
         　　　　url : paramUrl,
         　　　　dataType : "html",
         　　　　success : function(data, status, xhr) {
         　　　　　　 send.renderbox.html(data);
                     console.log('success');
                         console.log(data);
         　　　　},
         　　　　error : function(XMLHttpRequest, status, errorThrown) {
         　　　　}
         　　});
         });
    }
    return send;
}


