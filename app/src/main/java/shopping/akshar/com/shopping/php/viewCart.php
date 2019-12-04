<?php

require "connection.php";

$query = "SELECT * FROM cart,product WHERE cart.product_id = product.product_id";

$result = mysqli_query($connect,$query);

while($row = mysqli_fetch_assoc($result)){
    $data[] = $row;
}

echo json_encode($data);

?>





<?php
if($_SERVER["REQUEST_METHOD"]== "GET"){

    require 'connection.php';
    getCart();
}

Function getCart(){
    global $connect;
     $userid = $_GET['userid'];

    $query = "SELECT * FROM cart,product WHERE cart.product_id = product.product_id and cart.token = '{$userid}'";
    $result = mysqli_query($connect,$query);

    while($row = mysqli_fetch_assoc($result)){

    $data['data'][]=$row;


    }

    echo json_encode($data);

    }?>
