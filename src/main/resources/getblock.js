
function printBlockNumberHasTransaction(){
    var transactionsBlock = new Array();
    for(var i = 0 ; i<=eth.blockNumber ; i++){
        if(eth.getBlock(i).transactions.length > 0){
            //console.log(eth.getBlock(i));
            transactionsBlock.push(i);
        }
    }
    return transactionsBlock;
}

function getAllBlock(){
    var Blocks = new Array();
    for(var i = 0 ; i<=eth.blockNumber ; i++){
        Blocks.push('number'+i+'\n');
        Blocks.push(eth.getBlock(i));
    }
    return Blocks;
}

function listUncleBlock(){
    var uncleBlocks = new Array();
    for(var i = 0 ; i<=eth.blockNumber ; i++){
        if(eth.getBlock(i).uncles.length > 0){
            console.log(i); 
            uncleBlocks.push('number'+i+'\n');
            uncleBlocks.push(eth.getBlock(i));       
        }
    }
    return uncleBlocks;
}

function getTransactionDataByIdentification(identification){
    for(var i = 0 ; i<=eth.blockNumber ; i++){
        for(var j = 0; j < eth.getBlockTransactionCount(i) ; j++){
            if(web3.toAscii(eth.getTransactionFromBlock(i,j).input).substring(0,identification.length) == identification){
                console.log(i);
                console.log(j);
                console.log(web3.toAscii(eth.getTransactionFromBlock(i,j).input).substring(identification.length));
            }           
        }
    }
    end();
}

function sendTransactionByAccount(sendIndex , receiveIndex , amount , data ,identification){
    //var s = generate(sendIndex , receiveIndex , amount , data ,identification)
    eth.sendTransaction(s);
    end();
}

function generate(sendIndex , receiveIndex , amount , data ,identification){
    var s = new Object();
    s.from = eth.accounts[sendIndex];
    s.to = eth.accounts[receiveIndex];
    s.value = amount;
    s.data = web3.toHex(identification+data);
//    setTimeout(function(){console.log("ffffffff");
//    },100);
    end();
    return s;
}
function getAccounts(){  
   
    var accounts = eth.accounts;
    for(var i = 0 ; i<accounts.length ; i++){
       console.log(accounts[i]);     
    }
    end();
}
function getBalance(index){
    console.log(eth.getBalance(eth.accounts[index]));

}
function end(){
    console.log("ffffffff"); 
}
function begin(){
    console.log("eeeeeeee"); 
}