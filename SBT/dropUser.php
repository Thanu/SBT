<?php
$db_host = "localhost";
$db_user = "root";
$db_pass = "";
$db_db = "schoolbustracker";

$conn = mysql_connect($db_host,$db_user,$db_pass) or die("connection error");

mysql_select_db($db_db) or die("database selection error");

$uname = $_POST['username'];

$query = mysql_query("DELETE FROM sbt_users WHERE username = '$uname'");

print json_encode($query);

mysql_close($conn);


?>