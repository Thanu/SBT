<?php
$db_host = "localhost";
$db_user = "root";
$db_pass = "";
$db_db = "schoolbustracker";

$conn = mysql_connect($db_host,$db_user,$db_pass) or die("connection error");

mysql_select_db($db_db) or die("database selection error");

$uname = $_POST['username'];
$fname = $_POST['fullname'];
$gender = $_POST['gender'];
$address = $_POST['address'];
$phone =$_POST['phone'];
$pword =$_POST['pword'];
$email = $_POST['email'];
$bus_hault =$_POST['bus_hault'];

// $uname = "101052";//$_POST['username'];
// $fname = "Premini Barosingam";//$_POST['fullname'];
// $gender = "Female";//$_POST['gender'];
// $address = "Moratuwa";//$_POST['address'];
// $phone ="0775371646";//$_POST['phone'];
// $pword ="mini";//$_POST['pword'];
// $email = "mini@yahoo.com";//$_POST['email'];
// $bus_hault ="Moratuwa";//$_POST['bus_hault'];


$query = mysql_query("INSERT INTO sbt_users(username, full_name, gender, address, phone_no, password, email,bus_hault)  VALUES('$uname','$fname', '$gender', '$address', '$phone', '$pword', '$email', '$bus_hault') ");

$result=$query;

print json_encode($result);
mysql_close(); 


?>