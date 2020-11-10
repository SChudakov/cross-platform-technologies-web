$(document).ready(function () {
    $("#pathTextArea").prop("disabled", true);
    $.ajaxSetup({
        contentType: "application/json; charset=utf-8"
    });

    $("#submitButton").click(function () {
        let graphData = $("#graphTextArea").val();
        let source = $("#sourceVertex").val();
        let target = $("#targetVertex").val();
        let algorithmName = $("#algorithm").val();
        let graphObject = convertToJson(graphData);

        if (graphObject == null) {
            $("#pathTextArea").val("error");
        } else {
            let shortestPathRequest = {};
            shortestPathRequest["graph"] = graphObject;
            shortestPathRequest["source"] = source;
            shortestPathRequest["target"] = target;
            shortestPathRequest["algorithm"] = algorithmName;

            $.post("/api/path", JSON.stringify(shortestPathRequest), function (response, status) {
                if (status === "success") {
                    $("#pathTextArea").val(JSON.stringify(response["vertices"]));
                }
            }).fail(function (response) {
                $("#pathTextArea").val("error");
                let responseText = JSON.parse(response["responseText"]);
                alert("Exception while computing shortest path:\n" + responseText["message"]);
            });
        }
    });

    function convertToJson(graphData) {
        let result = {};
        let lines = graphData.match(/[^\r\n]+/g);

        for (const line of lines) {
            let sourceAndTarget = line.split(" ")
            if (sourceAndTarget.length !== 2) {
                alert("Invalid graph format in line " + line + " - should contain exactly 2 integer vertices");
                return null;
            }

            let edgeSource = sourceAndTarget[0];
            let edgeTarget = sourceAndTarget[1];
            if (!isInt(edgeSource)) {
                alert("Invalid graph vertex format " + edgeSource);
            }
            if (!isInt(edgeTarget)) {
                alert("Invalid graph vertex format " + edgeTarget);
            }

            if (!(edgeSource in result)) {
                result[edgeSource] = [];
            }
            if (!(edgeTarget in result)) {
                result[edgeTarget] = [];
            }
            result[edgeSource].push(edgeTarget);
        }
        return result;
    }

    function isInt(str) {
        return !isNaN(str) && Number.isInteger(parseFloat(str));
    }
});