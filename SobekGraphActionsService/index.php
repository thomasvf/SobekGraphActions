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

function makeJsonFileName(){
    $currentDate = date("d").date("m").date("y")."_".date("H").date("i");
    $uniqueId = substr(uniqid(rand(), true), 20);

    return $currentDate."_".$uniqueId;
}

function saveJson($jsonString){
    $fileName = makeJsonFileName();
    $jsonFileHandler = fopen(GRAPH_ACTIONS_DIRECTORY.$fileName.".json", "w");
    fwrite($jsonFileHandler, $jsonString);
    fclose($jsonFileHandler);
}

date_default_timezone_set('America/Sao_Paulo');
$jsonString = jsonFromPost();
saveJson($jsonString);

