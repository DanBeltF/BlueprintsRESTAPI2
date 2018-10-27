var Modulo = (function () {
    var autor = "";
    var bpname = "";
    var modo = "PUT";
    var lista = [];
    var newPoints = [];
    var api = apiclient;
    var c = document.getElementById('myCanvas');
    
    
    
    var getBlueptint = function () {
        document.getElementById("autor").value = autor;
        var getpromise = Modulo.actualizarPlanos(autor);
        getpromise.then(
                function () {
                    console.info("OK");
                }
        ,
                function () {
                    console.info("ERROR");
                }
        );
        return getpromise;
    };
    var putBlueprint = function () {
        var newBp = function (author, name, points) {
            this.name = name;
            this.author = author;
            this.points = points;
        };
        var bp = new newBp(autor, bpname, newPoints);
        var putPromise = api.putBlueprintsByNameAndAuthor(bp);
        putPromise.then(
                function () {
                    console.info("OK");
                }
        ,
                function () {
                    console.info("ERROR");
                }
        );
        return putPromise;
    };
    var postBlueprint = function () {
        var newBp = function (author, name, points) {
            this.name = name;
            this.author = author;
            this.points = points;
        };
        var bp = new newBp(autor, bpname, newPoints);
        var postPromise = api.postBlueprints(bp);
        postPromise.then(
                function () {
                    console.info("OK");
                }
        ,
                function () {
                    console.info("ERROR");
                }
        );
        return postPromise;
    };
    var deleteBlueprint = function () {
        var deletePromise = api.deleteBlueprintsByNameAndAuthor(autor, bpname);
        deletePromise.then(
                function () {
                    console.info("OK");
                }
        ,
                function () {
                    console.info("ERROR");
                }
        );
        return deletePromise;
    };


    return {
        init: function () {
            var c = document.getElementById('myCanvas');
            var canvas = c.getContext('2d');
            var pos = $("#myCanvas").position();
            console.info('initialized');
            if (window.PointerEvent) {
                c.addEventListener("pointerdown", function (event) {
                    newPoints.push({"x": event.pageX - pos.left, "y": event.pageY - pos.top});
                    if (autor.length > 0) {
                        canvas.lineTo(event.pageX - pos.left, event.pageY - pos.top);
                        canvas.stroke();

                    }
                });


            } else {
                c.addEventListener("mousedown", function (event) {
                    newPoints.push({"x": event.clientX, "y": event.clientY});
                    if (autor.length > 0) {
                        canvas.lineTo(event.clientX - pos.left, event.clientY - pos.top);
                        canvas.stroke();

                    }
                }
                );
            }


        },
        cambioAutor: function () {
            return autor = document.getElementById("autor").value;
        },
        actualizarPlanos: function (autor) {
            cb = function (listabp) {
                reducir = function (objeto) {
                    contar = function (total, list) {
                        return total + 1;
                    };
                    var numpoints = objeto.points.reduce(contar, 0);
                    return objeto2 = {"name": objeto.name, "points": numpoints};
                };
                lista = listabp.map(reducir);
                $("#table1").find("tr:gt(0)").remove();
                document.getElementById("autorname").innerHTML = document.getElementById("autor").value + "'s blueprints:";
                anadir = function (objeto) {
                    $(document).ready(function () {

                        $('#table1').append("<tr><th>" + objeto.name + "</th><th>" + objeto.points + "</th><th><button class=btn btn-outline-primary onclick=Modulo.graficarPlanoPorAutor(&#34" + document.getElementById('autor').value + "&#34,&#34" + objeto.name + "&#34)>Draw</button></th></tr>");
                    });
                };
                lista.map(anadir);
                numpointstotal = lista.reduce(function (total, point2) {
                    return total + point2.points;
                }, 0);
                document.getElementById("totalpuntos").innerHTML = numpointstotal;
            };
            return api.getBlueprintsByAuthor(autor, cb);
        },
        graficarPlanoPorAutor: function (autor, name) {
            bpname = name;
            var c = document.getElementById('myCanvas');
            var canvas = c.getContext('2d');
            canvas.beginPath();
            canvas.clearRect(0, 0, c.width, c.height);
            cb2 = function (blueprint) {
                var index = 0;
                newPoints = blueprint.points;
                var array = blueprint.points;
                pintar = function (point, index, array) {
                    canvas.moveTo(array[index].x, array[index].y);
                    if (index + 1 < array.length) {
                        canvas.lineTo(array[index + 1].x, array[index + 1].y);
                    }
                    canvas.stroke();
                };
                blueprint.points.map(pintar);
                document.getElementById("currentBP").innerHTML = blueprint.name;
            };
            canvas.closePath();
            api.getBlueprintsByNameAndAuthor(autor, name, cb2);
        },
        saveUpdate: function () {
            if (modo === "PUT") {
                putBlueprint().then(getBlueptint);
            } else {
                postBlueprint().then(getBlueptint);
            }
            var c = document.getElementById('myCanvas');
            var canvas = c.getContext('2d');
            canvas.clearRect(0, 0, c.width, c.height);
            canvas.beginPath();
            modo = "PUT";
        },
        create: function () {
            modo = "POST";
            bpname = prompt("Please enter name from new blueprint", "Blueprint");
            autor = prompt("Please enter author from new blueprint", "author");
            var c = document.getElementById('myCanvas');
            var canvas = c.getContext('2d');
            canvas.clearRect(0, 0, c.width, c.height);
            canvas.beginPath();
            newPoints = [];
        },
        delete: function () {
            var c = document.getElementById('myCanvas');
            var canvas = c.getContext('2d');
            canvas.clearRect(0, 0, c.width, c.height);
            canvas.beginPath();
            deleteBlueprint().then(getBlueptint);
            bpname = "";
        }
    };
})();