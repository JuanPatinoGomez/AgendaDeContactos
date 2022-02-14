const myHttp = new myHTTP();

const agregarCampoTelefono = document.getElementById('agregarcampotelefono');
const agregarCampoEmail = document.getElementById('agregarcampoemail');

const contenedorTelefonos = document.getElementsByClassName('contenedor-telefonos');
const contenedorEmails = document.getElementsByClassName('contenedor-emails');

const botonesEliminarTelefonos = document.getElementsByClassName('eliminarT');
const botonesEliminarEmails = document.getElementsByClassName('eliminarE');
//Se agregran los eventos para eliminar telefonos y emails
recorrerBotonesTelefonos(botonesEliminarTelefonos);
recorrerBotonesEmails(botonesEliminarEmails);


//Se ponen los eventos para agregar más campos de telefono y email
agregarCampoTelefono.addEventListener('click', ()=>{
    
    const divTelefono = crearCampo(1);

    contenedorTelefonos[0].appendChild(divTelefono);

});

agregarCampoEmail.addEventListener('click', ()=>{

    const divEmail = crearCampo(2);

    contenedorEmails[0].appendChild(divEmail);

});


function crearCampo(opcion){

    const divCampo = document.createElement('div');
    const imagen1 = document.createElement('img');
    imagen1.classList.add('imagen-tipoC');
    
    const espacio = document.createElement('span');
    espacio.innerHTML = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';

    const inputHidden = document.createElement('input');
    inputHidden.type = 'hidden';
    inputHidden.value = 0;

    const input = document.createElement('input');

    const espacio2 = document.createElement('span');
    espacio2.innerHTML = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';

    const imagen2 = document.createElement('img');
    imagen2.src = '/img/Trazado13.png';
    imagen2.setAttribute('idEliminar', 0);

    switch (opcion) {
        case 1: //Para telefono
            
            divCampo.classList.add('telefono');
            imagen1.src = '/img/Trazado11.png';
            inputHidden.name = 'idtelefonos';
            input.type = 'tel';
            input.name = 'telefonos';
            imagen2.classList.add('eliminarT');
            agregarEventoEliminarTelefono(imagen2);

            break;

        case 2: //Para email

            divCampo.classList.add('email');
            imagen1.src = '/img/Trazado14.png';
            inputHidden.name = 'idemails';
            input.type = 'email';
            input.name = 'emails';
            imagen2.classList.add('eliminarE');
            agregarEventoEliminarEmail(imagen2);
            break;
    
        default:
            break;
    }

    //Juntamos todo
    
    divCampo.appendChild(imagen1);
    divCampo.appendChild(espacio);
    divCampo.appendChild(inputHidden);
    divCampo.appendChild(input);
    divCampo.appendChild(espacio2);
    divCampo.appendChild(imagen2);

    return divCampo;

}



//Peticion para eliminar


function recorrerBotonesTelefonos(botones){

    for(let i = 0; i < botones.length; i++){
        
        agregarEventoEliminarTelefono(botones[i]);
    }

}

function agregarEventoEliminarTelefono(boton){

    boton.addEventListener('click', ()=>{
        console.log('presionando el boton');

        const idTelefonoEliminar = parseInt(boton.getAttribute('idEliminar'));

        if(idTelefonoEliminar === 0 ){ //Si es igual a 0 solo vamos a elimiar e div ya que en base de datos aún no se encuentra registrado
            //elimianr div
            eliminarDivTelefonoEmail(boton)
        }else{
            peticionEliminarTelefono(idTelefonoEliminar);
            //Eliminardiv
            eliminarDivTelefonoEmail(boton)
        }

    })
}

async function peticionEliminarTelefono(idTelefonoEliminar){

    const data = {
        id : idTelefonoEliminar
    }
    const solicitud = await myHttp.POST('/contacto/eliminar/telefono', data);
    console.log(solicitud);
    
    return solicitud;
}

//-------------------------------------------------------------------------------
function recorrerBotonesEmails(botones){

    for(let i = 0; i < botones.length; i++){
        
        agregarEventoEliminarEmail(botones[i]);
    }

}

function agregarEventoEliminarEmail(boton){

    boton.addEventListener('click', ()=>{
        console.log('presionando el boton');

        const idEmailEliminar = parseInt(boton.getAttribute('idEliminar'));

        if(idEmailEliminar === 0 ){ //Si es igual a 0 solo vamos a elimiar e div ya que en base de datos aún no se encuentra registrado
            //elimianr div
            eliminarDivTelefonoEmail(boton)
        }else{
            peticionEliminarEmail(idEmailEliminar);
            //Eliminardiv
            eliminarDivTelefonoEmail(boton)
        }

    })
}

async function peticionEliminarEmail(idEmailEliminar){

    const data = {
        id : idEmailEliminar
    }
    const solicitud = await myHttp.POST('/contacto/eliminar/email', data);
    console.log(solicitud);

    return solicitud;
}

//---------------------------------------------------------------------------------------
function eliminarDivTelefonoEmail(boton){

    boton.parentElement.remove();
}