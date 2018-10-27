//@author hcadavid

apiclient = (function () {

    return {
        getBlueprintsByAuthor: function (authname, callback) {
            //$ajax({type:"GET",url: "/blueprints/"+ authname",success: finction(response){callback(response);}});
            return $.get("/blueprints/"+ authname, callback);
        },
        getBlueprintsByNameAndAuthor: function (authname, bpname, callback) {
            //$ajax({type:"GET",url: "/blueprints/"+ authname+ "/" + bpname",success: finction(response){callback(response);}});
            return $.get("/blueprints/"+ authname+ "/" + bpname,callback);
        },
        putBlueprintsByNameAndAuthor: function (bp) {
            return $.ajax({type: "PUT", url: "/blueprints/" + bp.author + "/" + bp.name, data: JSON.stringify(bp), contentType: "application/json"});
            
        },
        postBlueprints: function (bp) {
            return $.ajax({type: "POST", url: "/blueprints", data: JSON.stringify(bp), contentType: "application/json"});
            
        },
        deleteBlueprintsByNameAndAuthor: function (authname, bpname) {
            return $.ajax({type: "DELETE", url: "/blueprints/"+ authname+ "/" + bpname});
            
        }
    };

})();

/*
 Example of use:
 var fun=function(list){
 console.info(list);
 }
 
 apimock.getBlueprintsByAuthor("johnconnor",fun);
 apimock.getBlueprintsByNameAndAuthor("johnconnor","house",fun);*/