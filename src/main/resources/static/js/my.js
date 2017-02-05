function getAccounts(){
    $("#resultAccounts").load('/accounts');
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
    new Url('/createAccount',$("#resultCreateAccount"))
    .start($('#createAccountPasswd'));
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


