import React, { useEffect, useState } from 'react'
import axios from 'axios';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';
import { show_alerta } from '../services/UsuariosServices';

const ShowUsuarios = () => {
    const url = 'http://localhost:8090/usuarios/';
    const [usuarios, setUsuarios] = useState([])
    const [id, setId] = useState('');
    const [nombre, setNombre] = useState('');
    const [apellido, setApellido] = useState('');
    const [email, setEmail] = useState('');
    const [estado, setEstado] = useState('');
    const [fechaCreacion, setFechaCreacion] = useState('');
    const [operation, SetOperation] = useState(1);
    const [title, setTitle] = useState('');

    useEffect(() => {
        getUsuarios();
    }, []);

    const getUsuarios = async () => {
        const respuesta = await axios.get(url);
        setUsuarios(respuesta.data)
    }
//Registrar y Editar producto
    const openModal = (op,id,nombre,apellido,email,estado,fechaCreacion) =>{
        setId();
        setNombre('');
        setApellido('');
        setEmail('');
        setEstado('');
        setFechaCreacion('');
        SetOperation(op);
        if(op === 1){
            setTitle('Registrar Paciente');
        }
        else if(op === 2){
            setTitle('Editar Paciente');
            setId(id);
            setNombre(nombre);
            setApellido(apellido);
            setEmail(email);
            setEstado(estado);
            setFechaCreacion(fechaCreacion);
        }
        window.setTimeout(function(){
            document.getElementById('nombre').focus();
        },500);

    }
        const validar = () => {
            var parametros;
            var metodo;
         if(nombre.trim() === ''){
            show_alerta('escribir un nombre','warning');
         }   
            else if(apellido.trim() === ''){
                show_alerta('escribir un apellido','warning');
             }   
             else if(email.trim() === ''){
                show_alerta('escribir un email','warning');
             }  
             else if(estado.trim() === ''){
                show_alerta('escribir un estado','warning');
             } 
             else if(fechaCreacion.trim() === ''){
                show_alerta('escribir un fecha','warning');
             }  
              else{
                if(operation === 1){
                    parametros= {nombre:nombre.trim(),apellido:apellido.trim(), email:email.trim(), estado:estado.trim(), fechaCreacion:fechaCreacion.trim()};
                    metodo= 'POST';
                } 
                else{
                    parametros= {id:id,nombre:nombre.trim(),apellido:apellido.trim(), email:email.trim(), estado:estado.trim(), fechaCreacion:fechaCreacion.trim()};
                    metodo= 'PUT';
                }
                enviarSolicitud(metodo,parametros);
            }
        
        }
        //actualizar
    const enviarSolicitud = async(metodo,parametros) => {
        await axios({ method:metodo, url:url, data:parametros}).then(function(respuesta){
            var tipo = respuesta.data[0];
            var msj = respuesta.data[1];
            show_alerta(msj,tipo);
            if(tipo === 'success'){
                document.getElementById('btnCerrar').click();
                getUsuarios();
            }
        })
        .catch(function(error){
            show_alerta('error en la solicitud');
            console.log(error);
        });
    }

    //eliminar
    const deleteUsuario= (id,nombre) =>{
        const MySwal = withReactContent(Swal);
        MySwal.fire({
            title: '¿Seguro desea eliminar a'+nombre,
            icon: 'question',text:'No se podra deshacer',
            showCancelButton:true,confirmButtonText:'Si,eliminar',cancelButtonText:'cancelar'
        }).then((result) =>{
            if(result.isConfirmed){
                setId(id);
                enviarSolicitud('DELETE',{id:id});
            }
            else{
                show_alerta('el usuario fue no eliminado','info');
            }

        });
    }

    return (
        <div className='App'>
            <div className='container-fluid'>
                <div className='row mt-3'>
                    <div className='col-md-4 offset-4'>
                        <div className='d-grid mx-auto'>
                            <button onClick={()=> openModal(1)} className='btn btn-dark' data-bs-toggle='modal' data-bs-target='#modalUsuarios'>
                                <i className='fa-solid fa-circle-plus'></i>añadir
                            </button>
                        </div>
                    </div>
                </div>
                <div className='row mt-3'>
                    <div className='col-12 col-lg-8 offset-0 offset-lg-2'>
                        <div className='table-responsive'>
                            <table className='table table-bordered'>
                            <thead>
                                <tr><th>#</th><th>NOMBRE</th><th>APELLIDO</th><th>EMAIL</th><th>ESTADO</th><th>FECHA</th><th></th></tr>
                            </thead>
                            <tbody className='table-group-divider'>
                                {usuarios.map( (usuario,i)=>(
                                    <tr key={usuario.id}>
                                        <td>{i+1}</td>
                                        <td>{usuario.nombre}</td>
                                        <td>{usuario.apellido}</td>
                                        <td>{usuario.email}</td>
                                        <td>{usuario.estado}</td>
                                        <td>{usuario.fechaCreacion}</td>
                                        <td>
                                            <button onClick={() => openModal(2,usuario.id,usuario.nombre,usuario.apellido,usuario.email,usuario.estado,usuario.fechaCreacion)} 
                                            className='btn btn-warning' data-bs-toggle='modal' data-bs-target='#modalUsuarios'>
                                                <i className='fa-solid fa-edit'></i>
                                            </button>
                                            &nbsp;
                                            <button onClick={()=>deleteUsuario(usuario.id,usuario.nombre)} className='btn btn-danger'>
                                                <i className='fa-solid fa-trash'></i>
                                            </button>
                                        </td>
                                    </tr>
                                ))

                                }
                            </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div id='modalUsuarios' className='modal fade' aria-hidden='true'>
                <div className='modal-dialog'>
                    <div className='modal-content'>
                        <div className='modal-header'>
                            <label className='h5'>{title}</label>
                            <button type='button' className='btn-close' data-bs-dismiss='modal' aria-label='Close'></button>
                        </div>
                        <div className='modal-body'>

                            <input type='hidden' id='id'></input>
                            <div className='input-group mb-3'>
                                <span className='input-group-text'><i className='fa-solid fa-gift'></i></span>
                                <input type='text' id='nombre' className='form-control'placeholder='Nombre' value={nombre}
                                onChange={(e)=> setNombre(e.target.value)}></input>
                            </div>

                            <div className='input-group mb-3'>
                                <span className='input-group-text'><i className='fa-solid fa-gift'></i></span>
                                <input type='text' id='apellido' className='form-control'placeholder='Apellido' value={apellido}
                                onChange={(e)=> setApellido(e.target.value)}></input>
                            </div>

                            <div className='input-group mb-3'>
                                <span className='input-group-text'><i className='fa-solid fa-gift'></i></span>
                                <input type='text' id='email' className='form-control'placeholder='Email' value={email}
                                onChange={(e)=> setEmail(e.target.value)}></input>
                            </div>

                            <div className='input-group mb-3'>
                                <span className='input-group-text'><i className='fa-solid fa-gift'></i></span>
                                <input type='text' id='estado' className='form-control'placeholder='Estado' value={estado}
                                onChange={(e)=> setEstado(e.target.value)}></input>
                            </div>

                            <div className='input-group mb-3'>
                                <span className='input-group-text'><i className='fa-solid fa-gift'></i></span>
                                <input type='text' id='fechaCreacion' className='form-control'placeholder='Fecha' value={fechaCreacion}
                                onChange={(e)=> setFechaCreacion(e.target.value)}></input>
                            </div>
                            <div className='d-grid col-6 mx-auto'>
                                <button onClick={() => validar()} className='btn btn-success'>
                                    <i className='fa-solid fa-floppy-disk'></i>
                                </button>
                            </div>

                        </div>
                        <div className='modal-footer'>
                            <button type='button' id='btnCerrar' className='btn btn-secondary' data-bs-dismiss='modal'>cerrar</button>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    )
}

export default ShowUsuarios;
