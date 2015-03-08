
 function change_nick() {
    if (document.getElementById("nick").value != "") {
        var newNick = document.getElementById("nick").value;
        var NickArea = document.getElementById("nick");
        document.getElementById("nick").value = "";
        document.getElementById("this_nick").value = newNick;
        alert("Nick changed. New nick is " + newNick);
    }
}
function send() {
    if (document.getElementById("message").value != "") {
        var message = document.getElementById("message");
        var nick = document.getElementById("this_nick").value;
        var history = document.getElementById("textarea");
        history.value = history.value + nick + ": " + message.value + "\n";
        message.value = "";
    }
}