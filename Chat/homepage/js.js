var historyList = [];
var oldMessage;
var messageIndex;
var newNick = "User";

function run() {
    alert("Nick!");
    newNick.value = restoreNick();
    alert("Nick!");
    document.getElementById("nick").value = newNick.value;
    alert("Nick!");
    historyList = restoreHistory();
    alert("History!");
    reloadHistory(); 
    alert("History!");
}
function storeNick(newNick) {
    if(typeof(Storage) == "undefined") {
	    alert('Failed to store nickname');
	    return;
    }
	localStorage.setItem("nick", JSON.stringify(newNick));
}

function restoreNick() {
    if (typeof (Storage) == "undefined") {
        alert('Failed to restore nickname');
        return;
    }
    var item = localStorage.getItem("nick");
    return item && JSON.parse(item);
}

function storeHistory(History) {
    if(typeof(Storage) == "undefined") {
	    alert('Failed to store history');
		return;
	}
    localStorage.setItem("history", JSON.stringify(history));
}

function restoreHistory() {
    if (typeof (Storage) == "undefined") {
        alert('Failed to restore history');
        return;
    }
    var item = localStorage.getItem("history");
    return item && JSON.parse(item);
}

function change_nick() {
    if (document.getElementById("nick").value != "") {
        newNick = document.getElementById("nick").value;
        var NickArea = document.getElementById("nick");
        var oldNick = document.getElementById("this_nick").value;
        document.getElementById("nick").value = "";
        document.getElementById("this_nick").value = newNick;
        alert("Nick changed. New nick is " + newNick);
        storeNick(newNick);
    }
}

function send() {
    if (document.getElementById("message").value != "") {
        var message = document.getElementById("message");
        var nick = document.getElementById("this_nick").value;        
        historyList.push("<" + nick + ">" + ": " + message.value);
        reloadHistory();
        message.value = "";
        storeHistory(historyList);
    }
}

function reloadHistory() {
    var history = document.getElementById("textarea");
    history.value = '';
    for (var i = 0; i < historyList.length; i++) {
        history.value += (i + 1) + ") " + historyList[i] + '\n';
    }
}

function deleteMessage() {
    if (document.getElementById("change").value != "") {
        var message = document.getElementById("change").value;
        if (message > historyList.length) {
            alert("Incorrect index!");
            document.getElementById("change").value = "";
            return;
        }
        if (isNaN(parseInt(message))) {
            alert("Incorrect input!");
            document.getElementById("change").value = "";
            return;
        }
        var del = historyList[message - 1];
        historyList[message - 1] = "Message deleted";
        alert("Message \"" + del + "\" deleted");
        document.getElementById("change").value = "";
        reloadHistory();
    }
}

function editMessage() {
    if (document.getElementById("change").value != "") {
        messageIndex = document.getElementById("change").value;
        if (messageIndex > historyList.length) {
            alert("Incorrect index!");
            document.getElementById("change").value = "";
            return;
        }
        if (isNaN(parseInt(messageIndex))) {
            alert("Incorrect input!");
            document.getElementById("change").value = "";
            return;
        }
        var edit = historyList[messageIndex - 1];
        oldMessage = document.getElementById("message").value;
        var editMessage = document.getElementById("message");
        editMessage.value = edit;
        document.getElementById("MessageButton").setAttribute("onclick", "endEdit(oldMessage);");
        document.getElementById("NickButton").setAttribute("disabled", "true");
        document.getElementById("edit").setAttribute("disabled", "true");
        document.getElementById("delete").setAttribute("disabled", "true");
    }
}

function endEdit() {
    historyList[messageIndex - 1] = document.getElementById("message").value + "      //Message edited by " + newNick + "//";
    document.getElementById("message").value = oldMessage;
    document.getElementById("MessageButton").setAttribute("onclick", "send();");
    document.getElementById("NickButton").removeAttribute("disabled");
    document.getElementById("edit").removeAttribute("disabled");
    document.getElementById("delete").removeAttribute("disabled");
    reloadHistory();
}