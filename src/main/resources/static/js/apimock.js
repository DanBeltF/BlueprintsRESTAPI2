//@author hcadavid

apimock = (function () {

    var mockdata = [];

    mockdata["johnconnor"] = [{author: "johnconnor", "points": [{"x": 150, "y": 120}, {"x": 215, "y": 115}], "name": "house"},
        {author: "johnconnor", "points": [{"x": 56, "y": 8}, {"x": 56, "y": 5}, {"x": 56, "y": 23}], "name": "gear"},
        {author: "johnconnor", "points": [{"x": 737, "y": 72}, {"x": 45, "y": 9}, {"x": 12, "y": 0}], "name": "bp1"},
        {author: "johnconnor", "points": [{"x": 89, "y": 23}, {"x": 6, "y": 56}, {"x": 23, "y": 2}], "name": "bp2"},
        {author: "johnconnor", "points": [{"x": 23, "y": 123}, {"x": 23, "y": 65}, {"x": 45, "y": 78}], "name": "bp3"}];
    
    mockdata["josh"] = [{author: "josh", "points": [{"x": 45, "y": 89}, {"x": 425, "y": 115}], "name": "bp4"},
        {author: "josh", "points": [{"x": 42, "y": 8}, {"x": 53, "y": 32}, {"x": 3, "y": 115}], "name": "bp5"},
        {author: "josh", "points": [{"x": 3, "y": 45}, {"x": 78, "y": 75}, {"x": 78, "y": 86}], "name": "bp6"},
        {author: "josh", "points": [{"x": 12, "y": 5}, {"x": 2, "y": 3}, {"x": 45, "y": 56}], "name": "bp7"},
        {author: "josh", "points": [{"x": 7453, "y": 4}, {"x": 185, "y": 45}, {"x": 56, "y": 56}], "name": "bp8"}];
    
    mockdata["steve"] = [{author: "steve", "points": [{"x": 140, "y": 140}, {"x": 115, "y": 115}], "name": "bp9"},
        {author: "steve", "points": [{"x": 140, "y": 140}, {"x": 115, "y": 115}], "name": "gear2"}];
    
    mockdata["clark"] = [{author: "clark", "points": [{"x": 140, "y": 140}, {"x": 115, "y": 115}], "name": "house2"},
        {author: "clark", "points": [{"x": 140, "y": 140}, {"x": 115, "y": 115}], "name": "gear2"}];
    
    mockdata["maryweyland"] = [{author: "maryweyland", "points": [{"x": 140, "y": 140}, {"x": 115, "y": 115}], "name": "house2"},
        {author: "maryweyland", "points": [{"x": 141, "y": 141}, {"x": 115, "y": 115}], "name": "gear2"}];


    return {
        getBlueprintsByAuthor: function (authname, callback) {
            callback(
                    mockdata[authname]
                    );
        },
        getBlueprintsByNameAndAuthor: function (authname, bpname, callback) {

            callback(
                    mockdata[authname].find(function (e) {
                return e.name === bpname;
            })
                    );
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