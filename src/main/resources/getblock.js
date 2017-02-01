
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
    console.log(eth.blockNumber);
    for(var i = 0 ; i<=eth.blockNumber ; i++){       
        for(var j = 0; j < eth.getBlockTransactionCount(i) ; j++){
            //console.log(web3.toAscii(eth.getTransactionFromBlock(i,j).input.substring(2,identification.length*2+2)));
            if(web3.toAscii(eth.getTransactionFromBlock(i,j).input.substring(2,identification.length*2+2)) == identification){
                //console.log(eth.getTransactionFromBlock(i,j).input.substring(2,identification.length+2));
                console.log("the data is  "+
                web3.toAscii(eth.getTransactionFromBlock(i).input.substring(identification.length*2+2))+
                "  the blxock number is  "+  i +
                "  the transaction index is  " +j);
            }           
        }
    }
    end();
}

function sendTransactionByAccount(sendIndex , receiveIndex , amount , data ,identification){
    eth.sendTransaction(generate(sendIndex , receiveIndex , amount , data ,identification));
    end();
}

function generate(sendIndex , receiveIndex , amount , data ,identification){
    var s = new Object();
    s.from = eth.accounts[sendIndex];
    s.to = eth.accounts[receiveIndex];
    s.value = web3.toWei(amount,"ether");
    s.data = web3.toHex(identification+data);   
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
    end();
}
function end(){
    console.log("ffffffff"); 
}
function begin(){
    console.log("eeeeeeee"); 
}