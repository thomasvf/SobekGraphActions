<?php
/**
 * Created by PhpStorm.
 * User: thoma
 * Date: 18/03/2017
 * Time: 14:57
 */

const GRAPH_ACTIONS_DIRECTORY = "";

function receivedJson(){
    return isset($_POST["USER_ACTIONS"]) && !is_null($_POST["USER_ACTIONS"]);
}

function jsonFromPost(){
    if(receivedJson()){
        return $_POST["USER_ACTIONS"];
    }else{
        return null;
    }
}

function saveJson($jsonString){
    $jsonFileHandler = fopen(GRAPH_ACTIONS_DIRECTORY.uniqid(rand()).".json", "w");
    fwrite($jsonFileHandler, $jsonString);
    fclose($jsonFileHandler);
}

$jsonString = jsonFromPost();
saveJson($jsonString);
