# SkyeDSProject1

`Client`: the client side, send `DictionaryQueryRequestEntity` to the server and wait for the `DictionaryQueryResponseEntity`.

`Server`: the server side, handles each socket as a separate thread and process the request based on the `OPERATION` type.

`DictionaryQueryRequestEntity`: the request entity sent from the client to the server.

`DictionaryQueryResponseEntity`: the request entity sent from the server to the client.

`DictQueryThread`: handles the request, perform the operation, then send back the response.

`MyDictionaryEntity`: the dictionary entity, provides the dictionary operation - add, delete, query, feed the dictionary by reading the file.