/**
 * 
 */

    function checkAll(checkname, bx) {
        for (i = 0; i < checkname.length; i++){
            checkname[i].checked = bx.checked? true:false;
        }
    }
    function checkPage(bx){


        var bxs = document.getElementByTagName ( "table" ).getElementsByTagName ( "ids" ); 

        for(i = 0; i < bxs.length; i++){
            bxs[i].checked = bx.checked? true:false;
        }
    }
