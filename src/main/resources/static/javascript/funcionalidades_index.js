const myHttp = new myHTTP();

const buscador = document.getElementById('nombre-contacto');
const btnBuscar = document.getElementById('btn-buscar');
const contenedorDeContactos = document.getElementsByClassName('contenedor-contactos');
agregarEventosContactos(contenedorDeContactos);

//----------------------------------------------------------------------------BUSCADOR DE CONTACTOS
btnBuscar.addEventListener('click', async ()=>{

    if(buscador.value !== " "){

        let valorBuscador = buscador.value;

        if(!buscador.value.length > 0){ //Si no tiene una longitud mayor a 0, se hara la peticion  con el valor all, trae todos los contactos
            valorBuscador = 'all';
        }

        const response = await realizarPeticionBuscarPersonaPorNombre(valorBuscador);


        console.log(response);
        
        const fragmentoDeContactos = crearDivsDeContactos(response);
    
        contenedorDeContactos[0].innerHTML = ''; //vaciamos el contenedor de contactos 
        contenedorDeContactos[0].appendChild(fragmentoDeContactos); //y lo volvemos a llenar
        agregarEventosContactos(contenedorDeContactos);
    }
    

});

async function realizarPeticionBuscarPersonaPorNombre(nombreBuscado){

    const data = {
        nombre : nombreBuscado
    }

    return await myHttp.POST('/contacto/busqueda/nombre', data);
}

function crearDivsDeContactos(informacion){

    const fragmento = document.createDocumentFragment();

    for(let i =0 ; i < informacion.length; i++){

        //Construimos los divs de contaco

        const divContacto = document.createElement('div');
        divContacto.classList.add('contacto');
        divContacto.setAttribute('idcontacto', informacion[i]['id']);

        const imagenContacto = document.createElement('img');
        imagenContacto.src = '/img/Trazado2.png';

        const nombreContacto = document.createElement('p');
        nombreContacto.textContent = informacion[i]['nombre'];

        const imagenEditar = document.createElement('img');
        imagenEditar.src = '/img/Trazado3.png';

        const imagenEliminar = document.createElement('img');
        imagenEliminar.src = '/img/Trazado4.png';

        //Agregamos la información al div
        divContacto.appendChild(imagenContacto);
        divContacto.appendChild(nombreContacto);
        divContacto.appendChild(imagenEditar);
        divContacto.appendChild(imagenEliminar);

        //Agregamos el div al fragmento
        fragmento.appendChild(divContacto);
    }

    return fragmento;
}


//----------------------------------------------------------------------------TRAER INFORMACION DE CONTACTO
//Agregar el evento a los contactos
//♠
function agregarEventosContactos(contenedorContactos){

    const contactos = [...contenedorContactos[0].children];

    contactos.forEach(contacto =>{

        //Evento de mostrar informacion de contacto

        contacto.addEventListener('click', async () =>{

            //Si ya esta activado elimina la clase activado y borra el div de informacion-contacto
            //de lo contrario agrega la clase activado y agrega el div de informacion-contacto
            if(contacto.classList.contains('activado')){
    
                contacto.parentNode.removeChild(contacto.nextSibling);
    
                contacto.classList.remove('activado');
    
            }else{
    
                contacto.classList.add('activado');
    
                const idContacto = contacto.getAttribute('idcontacto');
                const response = await realizarPeticion(idContacto);
                const divInformacion = crearContenedorDeInformacionContacto(response);
                
                //Añadimos despues del contacto
        
                contacto.parentNode.insertBefore(divInformacion, contacto.nextSibling);
        
                //contactos[indice].appendChild(divInformacion);
                //element.parentNode.insertBefore(newElement, element.nextSibling);
    
    
            }

        });

        //Agregamos el evento de eliminar

        contacto.lastElementChild.addEventListener('click', async (e) => {
            e.stopPropagation();
            console.log('presionando el boton de eliminar');

            const data = {
                id: parseInt(contacto.getAttribute('idcontacto'))
            }

            const response = await myHttp.POST('/contacto/eliminar/persona', data);
            console.log(response);

            //Luego el div de contacto lo vamos a eliminar

            //primero revisamos si estaba mostrando informacion
            //Si contiene la clase activado tenemos que eliminar el div de informacion y luego el div de contacto
            if(contacto.classList.contains('activado')){

                contacto.parentNode.removeChild(contacto.nextSibling); //Eliminamos el div de informacion

                contacto.remove(); //Eliminamos el div de contacto

            }else{

                contacto.remove(); //Eliminamos el div de contacto

            }


        });

    });

//♠
}

async function realizarPeticion(idcontacto){
    console.log('El id el contacto es: ' + idcontacto);

    const data = {
        id : parseInt(idcontacto)
    }

    const response = await myHttp.POST('/contacto/informacion_contacto', data)

    return response;
}


function crearContenedorDeInformacionContacto(informacion){

    const divInformacion = document.createElement('div');
    divInformacion.classList.add('informacion-contacto');
    
    //-----------------------------------------------------
    //Construccion de los telefonos
    const p1 = document.createElement('p');
    p1.innerHTML = `Telefonos <br><br>`;
    

    //Traemos los telefonos
    const telefonos = informacion.mapaTelefonos;

    //div que contendra los telefonos
    const informacionTelefonos = document.createElement('div');

    //Recorremos los telefonos
    const llaves1 = Object.keys(telefonos);
    for(let i = 0; i < llaves1.length; i++){
        let llave1 = llaves1[i];

        const divTelefono = document.createElement('div');
        divTelefono.classList.add('telefono');

        const imagenTel1 = document.createElement('img');
        imagenTel1.src = '/img/Trazado11.png';
        // imagenTel1.width = '30px';

        const espacio = document.createElement('span');
        espacio.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";


        const numeroTelefono = document.createElement('input');
        numeroTelefono.type = 'tel';
        numeroTelefono.value = telefonos[llave1];
        numeroTelefono.disabled = true;

        // const imagenTel2 = document.createElement('img');
        // imagenTel2.src = '/img/Trazado13.png';

        //Agregamos toda la informacion del telefono al div telefono
        divTelefono.appendChild(imagenTel1);
        divTelefono.appendChild(espacio);
        divTelefono.appendChild(numeroTelefono);
        //divTelefono.appendChild(imagenTel2);


        //Luego agregamos el telefono al div que contiene todos los telefonos

        informacionTelefonos.appendChild(divTelefono);
    }

    //--------------------------------------------------------------
    
    //Construccion de los telefonos
    
    const p2 = document.createElement('p');
    p2.innerHTML = `<br><br>Emails <br><br>`
    

    //Treaemos los emails
    const emails = informacion.mapaEmails;

    const informacionEmails = document.createElement('div');

    //Recorremos los emails
    const llaves2 = Object.keys(emails);
    for(let i = 0; i < llaves2.length; i++){
        let llave2 = llaves2[i];

        const divEmail = document.createElement('div');
        divEmail.classList.add('telefono');

        const imageEmail1 = document.createElement('img');
        imageEmail1.src = '/img/Trazado14.png';

        const espacio2 = document.createElement('span');
        espacio2.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        const direccionEmail = document.createElement('input');
        direccionEmail.type = 'email';
        direccionEmail.value = emails[llave2];
        direccionEmail.disabled = true;


        //Agregamos la informacion al div email
        divEmail.appendChild(imageEmail1);
        divEmail.appendChild(espacio2);
        divEmail.appendChild(direccionEmail);


        //Luego agregamos el email al div que contiene todos los telefonos

        informacionEmails.appendChild(divEmail);
    }

    divInformacion.appendChild(p1);
    divInformacion.appendChild(informacionTelefonos);
    divInformacion.appendChild(p2);
    divInformacion.appendChild(informacionEmails);

    return divInformacion;

}
