function getAccounts(){
    $("#resultAccounts").load('/accounts');
}

function unlockAccount(){
    var url = Url('/unlock');
     //console.log(url);
    url.connect($('#accountIndex')).connect($('#unlockpasswd')).connect($('#length'));
    console.log(url);
    if(url.count == 3){
        $("#resultUnlock").load(url.u);
        console.log(true);
    }
    else
        $("#resultUnlock").load("/error/unlock");
}

function sendTransaction(){
    var url = Url('/transaction');
    url.connect($('#sendIndex')).connect($('#receiveIndex')).connect($('#sendAmount'))
        .connect($('#sendData')).connect($('#sendIdentification'));
//        console.log(url);
    if(url.count == 5){
        $("#resultTransaction").load(url.u);
//        console.log(true);
    }
    else
        $("#resultTransaction").load("/error/transaction");
   
}

function getTransaction(){
    var url = Url('/getTransaction');
    console.log(url.u);
    url.connect($('#identification'));
    console.log(url.u);
    if(url.count == 1){
        $("#resultGetTransaction").load(url.u);
        console.log(true);
    }
    else
        $("#resultGetTransaction").load("/error/getTransaction");
}

function createAccount(){
    var url = Url('/createAccount');
    url.connect($('#createAccountPasswd'));
    if(url.count == 1){
        $("#resultCreateAccount").load(url.u);
        console.log(true);
        }
    else
        $("#resultCreateAccount").load("/error/createAccount");
}

function Url(u){
    var url = new Object();
    url.u = u;
    url.count = 0;
    url.connect = function(ob){
        if(ob.val() != ''){
            url.u = url.u + '/' + ob.val();
            url.count++;
        }
        return url;
    };
    return url;
}
