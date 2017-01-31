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

function getTransactionsData(array){
    var transactionsData = new Array();
    for(var i = 0; i<array.length ; i++){
	    //console.log(i);
        var obj = {"transactionIndex":array[i],"data":eth.getTransactionFromBlock(array[i]).input};
        transactionsData.push(obj);
    }
    return transactionsData;
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

// function getTransactionDataByIdentification(identification){
//     for(var i = 0 ; i< identification.length ; i++){
//         if(identification[i].data.substring(0,10) == "0x11111111"){
//             console.log("the data is   "+
//             web3.toAscii(identification[i].data.substring(10))+
//             "  the block number is   "+
//             identification[i].transactionIndex);
//         }

//     }
// }

// function main(){
//     getTransactionDataByIdentification(
//         getTransactionsData(
//             printBlockNumberHasTransaction()
//         )
//     );
// }


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
