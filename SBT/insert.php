<?php
$db_host = "localhost";
$db_user = "root";
$db_pass = "";
$db_db = "schoolbustracker";

$conn = mysql_connect($db_host,$db_user,$db_pass) or die("connection error");

mysql_select_db($db_db) or die("database selection error");

$latitude = $_POST['latitude'];
$longitude =$_POST['longitude'];
$zoom = $_POST['zoom'];

$query = mysql_query("INSERT INTO locations(latitude,longitude,zoom)  VALUES('$latitude','$longitude', '$zoom') ");

$result=$query;

print json_encode($result);
mysql_close(); 


?>