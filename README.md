# fileio
file upload download and showcase


# Easy to use API

Try it out:
```
#upload
$ curl -X POST --header 'Content-Type: multipart/form-data' --header 'Accept: application/json' -F "file=@/data/test.txt" -i 'http://192.168.43.28/v1/api/fileupload'
#return
{"status":200,"data":"success"}
#download
$ wget http://192.168.43.28/download?f=/data/test.txt_0f2362a3ac534c2abfb201a61527f807
```