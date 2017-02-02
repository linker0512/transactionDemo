function getAccounts(){
    var url = '/accounts';
    $("#resultAccounts").load(url);
}

function unlockAccount(){
    var url = Url('/unlock')
    url.connect($('#accountIndex')).connect($('#unlockpasswd')).connect($('#length'));
    console.log(url);
    if(url.count == 3)
        $("#resultUnlock").load(url);
    else
        $("#resultUnlock").load('/unlockerror');
}

function Url(url){
    this.url = url;
    var count = 0;
    function connect(ob){
        if(ob.val() != ''){
            url = url + '/' + ob.val();
            count++;
        }
        return url;
    }
}
