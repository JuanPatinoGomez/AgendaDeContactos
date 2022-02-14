function myHTTP(){

}

myHTTP.prototype.POST = async function(url, data){

    const request = await fetch(url,{
        method: 'POST',
        headers: {'Content-type':'application/json'},
        body: JSON.stringify(data)
    });

    const response = await request.json();

    return response;
}
