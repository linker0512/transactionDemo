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
            if(eth.getTransactionFromBlock(i,j).input.substring(0,10) == identification){
                console.log("the data is  "+
                web3.toAscii(eth.getTransactionFromBlock(i).input.substring(10))+
                "  the block number is  "+  i +
                "  the transaction index is  " +j);
            }           
        }
    }
}

function sendTransactionByAccount(sendIndex , receiveIndex , amount , _data ){
    eth.sendTransaction({from:eth.accounts[sendIndex] , to:eth.accounts[receiveIndex] , value:web3.toWei(amount, "ether") , input:_data});
}

function sendTransactionByAddress(_form , _to , amount , _data ){
    eth.sendTransaction({from:_form , to: _to , value: amount , input : _data});
}

function getAccounts(){    
    var accounts = eth.accounts;
    for(var i = 0 ; i<accounts.length ; i++){
       console.log(accounts[i]);     
    }

}